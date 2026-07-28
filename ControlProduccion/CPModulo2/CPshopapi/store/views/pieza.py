from rest_framework import viewsets, status
from rest_framework.decorators import action
from rest_framework.permissions import IsAdminUser, AllowAny
from rest_framework.response import Response
from rest_framework.filters import SearchFilter, OrderingFilter
from django_filters.rest_framework import DjangoFilterBackend
from django.db.models import Avg, Max, Min, Sum, Count

from store.models              import Pieza
from store.serializers.pieza   import PiezaSerializer, PiezaSummarySerializer
from store.permissions         import IsSupervisorOrReadOnly
from store.filters             import PiezaFilter
from store.pagination          import StandardPagination


class PiezaViewSet(viewsets.ModelViewSet):
    queryset           = Pieza.objects.select_related('linea').filter(is_active=True)
    serializer_class   = PiezaSerializer
    permission_classes = [IsSupervisorOrReadOnly]
    pagination_class   = StandardPagination
    filter_backends    = [DjangoFilterBackend, SearchFilter, OrderingFilter]
    filterset_class    = PiezaFilter
    search_fields      = ['name', 'description', 'linea__name']
    ordering_fields    = ['name', 'costo_unitario', 'stock', 'created_at']
    ordering           = ['name']

    @action(
        detail=True,
        methods=['post'],
        permission_classes=[IsAdminUser],
        url_path='restock',
    )
    def restock(self, request, pk=None):
        pieza = self.get_object()
        try:
            cantidad = int(request.data.get('cantidad', 0))
            if cantidad <= 0:
                raise ValueError
        except (ValueError, TypeError):
            return Response(
                {'error': 'La cantidad debe ser un entero positivo.'},
                status=status.HTTP_400_BAD_REQUEST,
            )
        pieza.stock += cantidad
        pieza.save(update_fields=['stock'])
        return Response({
            'id':        pieza.id,
            'name':      pieza.name,
            'new_stock': pieza.stock,
        })

    @action(
        detail=False,
        methods=['get'],
        permission_classes=[AllowAny],
        url_path='available',
    )
    def available(self, request):
        qs   = self.filter_queryset(
            self.get_queryset().filter(stock__gt=0, is_active=True)
        )
        page = self.paginate_queryset(qs)
        if page is not None:
            return self.get_paginated_response(
                PiezaSummarySerializer(page, many=True).data
            )
        return Response(PiezaSummarySerializer(qs, many=True).data)

    @action(
        detail=False,
        methods=['get'],
        url_path='stats',
    )
    def stats(self, request):
        qs      = Pieza.objects.all()
        active  = qs.filter(is_active=True)
        data    = active.aggregate(
            total_active    = Count('id'),
            avg_costo       = Avg('costo_unitario'),
            max_costo       = Max('costo_unitario'),
            min_costo       = Min('costo_unitario'),
            total_stock     = Sum('stock'),
        )
        data['total_inactive'] = qs.filter(is_active=False).count()
        data['out_of_stock']   = active.filter(stock=0).count()
        if data['avg_costo']:
            data['avg_costo'] = round(float(data['avg_costo']), 2)
        return Response(data)
