from rest_framework import serializers
from store.models import Pieza
from store.serializers.linea import LineaProduccionSerializer


class PiezaSummarySerializer(serializers.ModelSerializer):

    class Meta:
        model  = Pieza
        fields = ['id', 'name', 'costo_unitario', 'stock', 'is_active']


class PiezaSerializer(serializers.ModelSerializer):
    linea              = LineaProduccionSerializer(read_only=True)
    linea_id           = serializers.PrimaryKeyRelatedField(
        source='linea',
        write_only=True,
        queryset=Pieza.objects.none(),
    )
    costo_con_impuesto = serializers.SerializerMethodField()
    in_stock           = serializers.SerializerMethodField()

    class Meta:
        model  = Pieza
        fields = [
            'id', 'name', 'description',
            'costo_unitario', 'costo_con_impuesto',
            'stock', 'in_stock', 'is_active',
            'linea', 'linea_id',
            'created_at', 'updated_at',
        ]
        read_only_fields = ['id', 'created_at', 'updated_at']

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        from store.models import LineaProduccion
        self.fields['linea_id'].queryset = LineaProduccion.objects.filter(is_active=True)

    def get_costo_con_impuesto(self, obj):
        return obj.costo_con_impuesto

    def get_in_stock(self, obj):
        return obj.in_stock

    def validate_costo_unitario(self, value):
        if value <= 0:
            raise serializers.ValidationError('El costo debe ser mayor a 0.')
        return value

    def validate_stock(self, value):
        if value < 0:
            raise serializers.ValidationError('El stock no puede ser negativo.')
        return value
