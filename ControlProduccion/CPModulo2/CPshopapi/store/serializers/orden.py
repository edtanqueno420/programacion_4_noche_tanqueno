from rest_framework import serializers
from store.models import OrdenProduccion, OrdenPieza, Pieza
from store.serializers.pieza import PiezaSummarySerializer


class OrdenPiezaSerializer(serializers.ModelSerializer):
    pieza    = PiezaSummarySerializer(read_only=True)
    subtotal = serializers.SerializerMethodField()

    class Meta:
        model  = OrdenPieza
        fields = ['id', 'pieza', 'cantidad', 'costo_unitario', 'subtotal']
        read_only_fields = ['id', 'costo_unitario']

    def get_subtotal(self, obj):
        return obj.subtotal


class OrdenProduccionSerializer(serializers.ModelSerializer):
    items         = OrdenPiezaSerializer(many=True, read_only=True)
    username      = serializers.CharField(source='operario.username', read_only=True)
    num_items     = serializers.SerializerMethodField()

    class Meta:
        model  = OrdenProduccion
        fields = [
            'id', 'username', 'status', 'total',
            'num_items', 'items', 'created_at', 'updated_at',
        ]
        read_only_fields = ['id', 'total', 'created_at', 'updated_at']

    def get_num_items(self, obj):
        return obj.items.count()


class AddItemSerializer(serializers.Serializer):
    pieza_id  = serializers.IntegerField()
    cantidad  = serializers.IntegerField(min_value=1)

    def validate_pieza_id(self, value):
        try:
            Pieza.objects.get(pk=value, is_active=True)
        except Pieza.DoesNotExist:
            raise serializers.ValidationError(
                f'Pieza {value} no encontrada o inactiva.'
            )
        return value

    def validate(self, data):
        pieza = Pieza.objects.get(pk=data['pieza_id'])
        if pieza.stock < data['cantidad']:
            raise serializers.ValidationError(
                f'Stock insuficiente: solo {pieza.stock} unidades disponibles.'
            )
        return data
