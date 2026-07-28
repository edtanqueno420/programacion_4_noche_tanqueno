from django.db import models
from .linea import LineaProduccion


class Pieza(models.Model):
    name            = models.CharField(max_length=200)
    description     = models.TextField(blank=True, default='')
    costo_unitario  = models.DecimalField(max_digits=10, decimal_places=2)
    stock           = models.PositiveIntegerField(default=0)
    is_active       = models.BooleanField(default=True)
    linea           = models.ForeignKey(
        LineaProduccion,
        on_delete=models.PROTECT,
        related_name='piezas',
    )
    created_at      = models.DateTimeField(auto_now_add=True)
    updated_at      = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ['name']

    def __str__(self):
        return self.name

    @property
    def costo_con_impuesto(self):
        return round(float(self.costo_unitario) * 1.15, 2)

    @property
    def in_stock(self):
        return self.stock > 0
