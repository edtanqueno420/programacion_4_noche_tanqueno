from django.test import TestCase
from rest_framework import status

from .helpers import (
    create_user, create_staff, auth_client,
    create_pieza, create_orden, add_item,
)


class OrdenProduccionCRUDTests(TestCase):

    def setUp(self):
        self.user    = create_user('ivan')
        self.client  = auth_client(self.user)
        self.pieza   = create_pieza(stock=20)

    def test_create_empty_orden(self):
        resp = self.client.post('/api/ordenes/', {})
        self.assertEqual(resp.status_code, status.HTTP_201_CREATED)
        self.assertEqual(resp.data['status'], 'pendiente')
        self.assertEqual(resp.data['num_items'], 0)

    def test_add_item_reduces_stock(self):
        orden        = create_orden(self.user)
        stock_before = self.pieza.stock
        resp = self.client.post(
            f'/api/ordenes/{orden.id}/add-item/',
            {'pieza_id': self.pieza.id, 'cantidad': 3}
        )
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.pieza.refresh_from_db()
        self.assertEqual(self.pieza.stock, stock_before - 3)

    def test_add_same_item_increments_cantidad(self):
        orden = create_orden(self.user)
        self.client.post(
            f'/api/ordenes/{orden.id}/add-item/',
            {'pieza_id': self.pieza.id, 'cantidad': 2}
        )
        resp = self.client.post(
            f'/api/ordenes/{orden.id}/add-item/',
            {'pieza_id': self.pieza.id, 'cantidad': 3}
        )
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['num_items'], 1)

    def test_insufficient_stock_returns_400(self):
        orden = create_orden(self.user)
        resp  = self.client.post(
            f'/api/ordenes/{orden.id}/add-item/',
            {'pieza_id': self.pieza.id, 'cantidad': 999}
        )
        self.assertEqual(resp.status_code, status.HTTP_400_BAD_REQUEST)

    def test_confirm_orden_with_items(self):
        orden = create_orden(self.user)
        add_item(orden, self.pieza)
        resp  = self.client.post(f'/api/ordenes/{orden.id}/confirm/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['status'], 'en_proceso')

    def test_confirm_empty_orden_returns_400(self):
        orden = create_orden(self.user)
        resp  = self.client.post(f'/api/ordenes/{orden.id}/confirm/')
        self.assertEqual(resp.status_code, status.HTTP_400_BAD_REQUEST)

    def test_cannot_add_item_to_confirmed_orden(self):
        orden = create_orden(self.user, status='en_proceso')
        resp  = self.client.post(
            f'/api/ordenes/{orden.id}/add-item/',
            {'pieza_id': self.pieza.id, 'cantidad': 1}
        )
        self.assertEqual(resp.status_code, status.HTTP_400_BAD_REQUEST)


class OrdenProduccionPermissionTests(TestCase):

    def setUp(self):
        self.user1   = create_user('julia')
        self.user2   = create_user('kevin')
        self.staff   = create_staff()
        self.pieza   = create_pieza(stock=20)
        self.orden   = create_orden(self.user1)

    def test_user_cannot_see_other_users_orden(self):
        resp = auth_client(self.user2).get(f'/api/ordenes/{self.orden.id}/')
        self.assertEqual(resp.status_code, status.HTTP_404_NOT_FOUND)

    def test_staff_can_see_any_orden(self):
        resp = auth_client(self.staff).get(f'/api/ordenes/{self.orden.id}/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)

    def test_staff_can_update_status(self):
        add_item(self.orden, self.pieza)
        self.orden.status = 'en_proceso'
        self.orden.save()
        resp = auth_client(self.staff).post(
            f'/api/ordenes/{self.orden.id}/update-status/',
            {'status': 'completada'}
        )
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['status'], 'completada')

    def test_regular_user_cannot_update_status(self):
        resp = auth_client(self.user1).post(
            f'/api/ordenes/{self.orden.id}/update-status/',
            {'status': 'completada'}
        )
        self.assertEqual(resp.status_code, status.HTTP_403_FORBIDDEN)


class OrdenProduccionFilterTests(TestCase):

    def setUp(self):
        self.staff  = create_staff()
        self.client = auth_client(self.staff)
        user = create_user('laura')
        create_orden(user, status='pendiente')
        create_orden(user, status='en_proceso')
        create_orden(user, status='completada')

    def test_filter_by_status(self):
        resp = self.client.get('/api/ordenes/?status=en_proceso')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        for orden in resp.data['results']:
            self.assertEqual(orden['status'], 'en_proceso')

    def test_stats_staff_only(self):
        resp = self.client.get('/api/ordenes/stats/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        for field in ['total_ordenes', 'total_costo', 'by_status']:
            self.assertIn(field, resp.data)

    def test_stats_regular_user_returns_403(self):
        resp = auth_client(create_user('mario')).get('/api/ordenes/stats/')
        self.assertEqual(resp.status_code, status.HTTP_403_FORBIDDEN)
