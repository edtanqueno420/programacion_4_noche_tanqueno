from django.contrib.auth.models import User
from rest_framework.test import APIClient
from rest_framework_simplejwt.tokens import RefreshToken

from store.models import LineaProduccion, Pieza, OrdenProduccion, OrdenPieza


def create_user(username='user', email=None, password='Pass1234!', **kwargs):
    email = email or f'{username}@test.com'
    return User.objects.create_user(
        username=username, email=email, password=password, **kwargs
    )


def create_staff(username='staff', email=None, password='Admin1234!'):
    email = email or f'{username}@test.com'
    return User.objects.create_user(
        username=username, email=email, password=password, is_staff=True
    )


def get_tokens(user):
    refresh = RefreshToken.for_user(user)
    return str(refresh.access_token), str(refresh)


def auth_client(user):
    client = APIClient()
    access, _ = get_tokens(user)
    client.credentials(HTTP_AUTHORIZATION=f'Bearer {access}')
    return client


def create_linea(name='Electronica', slug='electronica', is_active=True):
    return LineaProduccion.objects.create(name=name, slug=slug, is_active=is_active)


def create_pieza(name='Motor', costo_unitario=850, stock=10, linea=None, is_active=True):
    if linea is None:
        linea = create_linea()
    return Pieza.objects.create(
        name=name, costo_unitario=costo_unitario,
        stock=stock, linea=linea, is_active=is_active,
    )


def create_orden(operario, status='pendiente'):
    return OrdenProduccion.objects.create(operario=operario, status=status)


def add_item(orden, pieza=None, cantidad=1):
    if pieza is None:
        pieza = create_pieza()
    return OrdenPieza.objects.create(
        orden=orden,
        pieza=pieza,
        cantidad=cantidad,
        costo_unitario=pieza.costo_unitario,
    )
