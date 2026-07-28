from rest_framework import serializers
from django.utils.text import slugify
from store.models import LineaProduccion


class LineaProduccionSerializer(serializers.ModelSerializer):
    total_piezas = serializers.SerializerMethodField()

    class Meta:
        model  = LineaProduccion
        fields = [
            'id', 'name', 'slug', 'description',
            'is_active', 'total_piezas', 'created_at',
        ]
        read_only_fields = ['id', 'created_at']

    def get_total_piezas(self, obj):
        return obj.piezas.filter(is_active=True).count()

    def validate_slug(self, value):
        return slugify(value)

    def validate_name(self, value):
        qs = LineaProduccion.objects.filter(name__iexact=value)
        if self.instance:
            qs = qs.exclude(pk=self.instance.pk)
        if qs.exists():
            raise serializers.ValidationError('Ya existe una linea de produccion con este nombre.')
        return value
