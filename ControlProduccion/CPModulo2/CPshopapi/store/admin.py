from django.contrib import admin
from store.models import LineaProduccion, Pieza, OrdenProduccion, OrdenPieza


@admin.register(LineaProduccion)
class LineaProduccionAdmin(admin.ModelAdmin):
    list_display        = ['id', 'name', 'slug', 'is_active', 'created_at']
    list_filter         = ['is_active']
    search_fields       = ['name']
    prepopulated_fields = {'slug': ('name',)}


@admin.register(Pieza)
class PiezaAdmin(admin.ModelAdmin):
    list_display  = ['id', 'name', 'costo_unitario', 'stock', 'is_active', 'linea']
    list_filter   = ['is_active', 'linea']
    search_fields = ['name', 'description']
    list_editable = ['costo_unitario', 'stock', 'is_active']


class OrdenPiezaInline(admin.TabularInline):
    model  = OrdenPieza
    extra  = 0
    fields = ['pieza', 'cantidad', 'costo_unitario']


@admin.register(OrdenProduccion)
class OrdenProduccionAdmin(admin.ModelAdmin):
    list_display    = ['id', 'operario', 'status', 'total', 'created_at']
    list_filter     = ['status']
    search_fields   = ['operario__username']
    inlines         = [OrdenPiezaInline]
    readonly_fields = ['total', 'created_at', 'updated_at']
