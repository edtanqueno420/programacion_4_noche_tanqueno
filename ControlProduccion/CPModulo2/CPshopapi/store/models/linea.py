from django.db import models


class LineaProduccion(models.Model):
    name        = models.CharField(max_length=100, unique=True)
    slug        = models.SlugField(unique=True)
    description = models.TextField(blank=True, default='')
    is_active   = models.BooleanField(default=True)
    created_at  = models.DateTimeField(auto_now_add=True)

    class Meta:
        verbose_name        = 'Linea de Produccion'
        verbose_name_plural = 'Lineas de Produccion'
        ordering            = ['name']

    def __str__(self):
        return self.name
