from django.db import models
from django.contrib.auth.models import User
from .pieza import Pieza


class OrdenProduccion(models.Model):
    STATUS_CHOICES = [
        ('pendiente',    'Pendiente'),
        ('en_proceso',   'En Proceso'),
        ('completada',   'Completada'),
        ('entregada',    'Entregada'),
        ('cancelada',    'Cancelada'),
    ]

    operario    = models.ForeignKey(User, on_delete=models.CASCADE, related_name='ordenes_produccion')
    status      = models.CharField(max_length=20, choices=STATUS_CHOICES, default='pendiente')
    total       = models.DecimalField(max_digits=12, decimal_places=2, default=0)
    created_at  = models.DateTimeField(auto_now_add=True)
    updated_at  = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ['-created_at']

    def __str__(self):
        return f'Orden #{self.id} — {self.operario.username} ({self.status})'

    def calculate_total(self):
        self.total = sum(
            item.costo_unitario * item.cantidad
            for item in self.items.all()
        )
        self.save(update_fields=['total'])


class OrdenPieza(models.Model):
    orden         = models.ForeignKey(OrdenProduccion, on_delete=models.CASCADE, related_name='items')
    pieza         = models.ForeignKey(Pieza, on_delete=models.PROTECT)
    cantidad      = models.PositiveIntegerField(default=1)
    costo_unitario = models.DecimalField(max_digits=10, decimal_places=2)

    @property
    def subtotal(self):
        return float(self.costo_unitario) * self.cantidad

    def __str__(self):
        return f'{self.cantidad}x {self.pieza.name}'
