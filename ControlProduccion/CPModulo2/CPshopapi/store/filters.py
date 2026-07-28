import django_filters
from store.models import LineaProduccion, Pieza, OrdenProduccion


class LineaProduccionFilter(django_filters.FilterSet):
    name = django_filters.CharFilter(lookup_expr='icontains')

    class Meta:
        model  = LineaProduccion
        fields = ['is_active']


class PiezaFilter(django_filters.FilterSet):
    name          = django_filters.CharFilter(lookup_expr='icontains')
    costo_min     = django_filters.NumberFilter(field_name='costo_unitario', lookup_expr='gte')
    costo_max     = django_filters.NumberFilter(field_name='costo_unitario', lookup_expr='lte')
    stock_min     = django_filters.NumberFilter(field_name='stock', lookup_expr='gte')
    stock_max     = django_filters.NumberFilter(field_name='stock', lookup_expr='lte')
    linea_name    = django_filters.CharFilter(
        field_name='linea__name', lookup_expr='icontains'
    )

    class Meta:
        model  = Pieza
        fields = ['is_active', 'linea']


class OrdenProduccionFilter(django_filters.FilterSet):
    from_date = django_filters.DateFilter(field_name='created_at', lookup_expr='date__gte')
    to_date   = django_filters.DateFilter(field_name='created_at', lookup_expr='date__lte')

    class Meta:
        model  = OrdenProduccion
        fields = ['status']
