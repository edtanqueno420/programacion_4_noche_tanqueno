from rest_framework import viewsets
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework.filters import SearchFilter, OrderingFilter
from django_filters.rest_framework import DjangoFilterBackend
from django.db.models import Count

from store.models                import LineaProduccion
from store.serializers.linea     import LineaProduccionSerializer
from store.permissions           import IsSupervisorOrReadOnly
from store.filters               import LineaProduccionFilter
from store.pagination            import StandardPagination


class LineaProduccionViewSet(viewsets.ModelViewSet):
    queryset           = LineaProduccion.objects.all()
    serializer_class   = LineaProduccionSerializer
    permission_classes = [IsSupervisorOrReadOnly]
    pagination_class   = StandardPagination
    filter_backends    = [DjangoFilterBackend, SearchFilter, OrderingFilter]
    filterset_class    = LineaProduccionFilter
    search_fields      = ['name', 'description']
    ordering_fields    = ['name', 'created_at']
    ordering           = ['name']

    @action(detail=True, methods=['get'], url_path='piezas')
    def piezas_activas(self, request, pk=None):
        from store.models import Pieza
        from store.serializers.pieza import PiezaSummarySerializer
        linea = self.get_object()
        qs   = linea.piezas.filter(is_active=True).order_by('name')
        page = self.paginate_queryset(qs)
        if page is not None:
            return self.get_paginated_response(
                PiezaSummarySerializer(page, many=True).data
            )
        return Response(PiezaSummarySerializer(qs, many=True).data)

    @action(detail=False, methods=['get'], url_path='stats')
    def stats(self, request):
        qs = LineaProduccion.objects.annotate(num_piezas=Count('piezas', distinct=True))
        return Response({
            'total':    qs.count(),
            'active':   qs.filter(is_active=True).count(),
            'inactive': qs.filter(is_active=False).count(),
            'detail': [
                {
                    'id':          c.id,
                    'name':        c.name,
                    'num_piezas':  c.num_piezas,
                    'is_active':   c.is_active,
                }
                for c in qs.order_by('name')
            ],
        })
