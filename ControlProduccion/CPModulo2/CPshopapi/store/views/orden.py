from rest_framework import viewsets, status
from rest_framework.decorators import action
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from rest_framework.filters import OrderingFilter
from django_filters.rest_framework import DjangoFilterBackend

from store.models import OrdenProduccion, OrdenPieza, Pieza
from store.serializers.orden import OrdenProduccionSerializer, AddItemSerializer
from store.permissions import IsOwnerOrSupervisor
from store.filters    import OrdenProduccionFilter
from store.pagination import StandardPagination


class OrdenProduccionViewSet(viewsets.ModelViewSet):
    serializer_class   = OrdenProduccionSerializer
    permission_classes = [IsAuthenticated, IsOwnerOrSupervisor]
    pagination_class   = StandardPagination
    filter_backends    = [DjangoFilterBackend, OrderingFilter]
    filterset_class    = OrdenProduccionFilter
    ordering_fields    = ['created_at', 'total']
    ordering           = ['-created_at']
    http_method_names  = ['get', 'post', 'patch', 'delete', 'head', 'options']

    def get_queryset(self):
        if self.request.user.is_staff:
            return (
                OrdenProduccion.objects
                .select_related('operario')
                .prefetch_related('items__pieza')
                .all()
            )
        return (
            OrdenProduccion.objects
            .filter(operario=self.request.user)
            .prefetch_related('items__pieza')
        )

    def perform_create(self, serializer):
        serializer.save(operario=self.request.user)

    @action(detail=True, methods=['post'], url_path='add-item')
    def add_item(self, request, pk=None):
        orden = self.get_object()
        if orden.status != 'pendiente':
            return Response(
                {'error': f'No se puede modificar una orden con estado "{orden.status}".'},
                status=status.HTTP_400_BAD_REQUEST,
            )
        serializer = AddItemSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)

        pieza    = Pieza.objects.get(pk=serializer.validated_data['pieza_id'])
        cantidad = serializer.validated_data['cantidad']

        item, created = OrdenPieza.objects.get_or_create(
            orden=orden,
            pieza=pieza,
            defaults={'costo_unitario': pieza.costo_unitario, 'cantidad': cantidad},
        )
        if not created:
            item.cantidad += cantidad
            item.save(update_fields=['cantidad'])

        pieza.stock -= cantidad
        pieza.save(update_fields=['stock'])
        orden.calculate_total()

        return Response(OrdenProduccionSerializer(orden).data)

    @action(detail=True, methods=['post'], url_path='confirm')
    def confirm(self, request, pk=None):
        orden = self.get_object()
        if orden.status != 'pendiente':
            return Response(
                {'error': 'Solo las ordenes pendientes pueden ser confirmadas.'},
                status=status.HTTP_400_BAD_REQUEST,
            )
        if not orden.items.exists():
            return Response(
                {'error': 'No se puede confirmar una orden sin piezas.'},
                status=status.HTTP_400_BAD_REQUEST,
            )
        orden.status = 'en_proceso'
        orden.save(update_fields=['status'])
        return Response(OrdenProduccionSerializer(orden).data)

    @action(
        detail=True,
        methods=['post'],
        permission_classes=[IsAdminUser],
        url_path='update-status',
    )
    def update_status(self, request, pk=None):
        orden         = self.get_object()
        new_status    = request.data.get('status')
        valid_statuses = [s[0] for s in OrdenProduccion.STATUS_CHOICES]

        if new_status not in valid_statuses:
            return Response(
                {'error': f'Estado invalido. Opciones validas: {valid_statuses}'},
                status=status.HTTP_400_BAD_REQUEST,
            )
        orden.status = new_status
        orden.save(update_fields=['status'])
        return Response(OrdenProduccionSerializer(orden).data)

    @action(
        detail=False,
        methods=['get'],
        permission_classes=[IsAdminUser],
        url_path='stats',
    )
    def stats(self, request):
        from django.db.models import Count, Sum
        qs     = OrdenProduccion.objects.all()
        totals = qs.aggregate(
            total_ordenes = Count('id'),
            total_costo   = Sum('total'),
        )
        by_status = {
            s: qs.filter(status=s).count()
            for s, _ in OrdenProduccion.STATUS_CHOICES
        }
        return Response({
            'total_ordenes': totals['total_ordenes'],
            'total_costo':   float(totals['total_costo'] or 0),
            'by_status':     by_status,
        })
