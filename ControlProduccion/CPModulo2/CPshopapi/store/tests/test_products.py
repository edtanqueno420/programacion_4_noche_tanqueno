from django.test import TestCase
from rest_framework import status
from rest_framework.test import APIClient

from .helpers import create_user, create_staff, auth_client, create_linea, create_pieza


class PiezaPermissionTests(TestCase):

    def setUp(self):
        self.user    = create_user('frank')
        self.staff   = create_staff()
        self.linea   = create_linea()
        self.pieza   = create_pieza(linea=self.linea)

    def test_authenticated_can_list(self):
        resp = auth_client(self.user).get('/api/piezas/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertIn('results', resp.data)

    def test_unauthenticated_returns_401(self):
        resp = APIClient().get('/api/piezas/')
        self.assertEqual(resp.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_regular_user_cannot_create(self):
        resp = auth_client(self.user).post('/api/piezas/', {
            'name': 'Test', 'costo_unitario': '10.00',
            'stock': 5, 'linea_id': self.linea.id,
        })
        self.assertEqual(resp.status_code, status.HTTP_403_FORBIDDEN)

    def test_staff_can_create(self):
        resp = auth_client(self.staff).post('/api/piezas/', {
            'name': 'Engranaje', 'costo_unitario': '79.00',
            'stock': 12, 'linea_id': self.linea.id,
        })
        self.assertEqual(resp.status_code, status.HTTP_201_CREATED)

    def test_costo_con_impuesto_is_15_percent(self):
        resp = auth_client(self.user).get(f'/api/piezas/{self.pieza.id}/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        expected = round(float(self.pieza.costo_unitario) * 1.15, 2)
        self.assertEqual(float(resp.data['costo_con_impuesto']), expected)


class PiezaFilterTests(TestCase):

    def setUp(self):
        self.client = auth_client(create_user('gina'))
        linea = create_linea()
        create_pieza('Motor',     costo_unitario=850, stock=5,  linea=linea)
        create_pieza('Barata',    costo_unitario=20,  stock=0,  linea=linea)
        create_pieza('Inactiva',  costo_unitario=50,  stock=10, linea=linea, is_active=False)

    def test_filter_by_max_costo(self):
        resp = self.client.get('/api/piezas/?costo_max=100')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['count'], 1)
        self.assertEqual(resp.data['results'][0]['name'], 'Barata')

    def test_filter_by_min_stock(self):
        resp = self.client.get('/api/piezas/?stock_min=1')
        names = [p['name'] for p in resp.data['results']]
        self.assertIn('Motor', names)
        self.assertNotIn('Barata', names)

    def test_search_by_name(self):
        resp = self.client.get('/api/piezas/?search=motor')
        self.assertEqual(resp.data['count'], 1)

    def test_available_is_public(self):
        resp = APIClient().get('/api/piezas/available/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        names = [p['name'] for p in resp.data['results']]
        self.assertIn('Motor', names)
        self.assertNotIn('Barata', names)


class PiezaActionTests(TestCase):

    def setUp(self):
        self.staff   = create_staff()
        self.user    = create_user('henry')
        self.pieza   = create_pieza(stock=10)

    def test_restock_adds_stock(self):
        resp = auth_client(self.staff).post(
            f'/api/piezas/{self.pieza.id}/restock/',
            {'cantidad': 5}
        )
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['new_stock'], 15)

    def test_restock_regular_user_returns_403(self):
        resp = auth_client(self.user).post(
            f'/api/piezas/{self.pieza.id}/restock/',
            {'cantidad': 5}
        )
        self.assertEqual(resp.status_code, status.HTTP_403_FORBIDDEN)

    def test_restock_invalid_cantidad(self):
        resp = auth_client(self.staff).post(
            f'/api/piezas/{self.pieza.id}/restock/',
            {'cantidad': -1}
        )
        self.assertEqual(resp.status_code, status.HTTP_400_BAD_REQUEST)

    def test_stats_returns_expected_fields(self):
        resp = auth_client(self.user).get('/api/piezas/stats/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        for field in ['total_active', 'avg_costo', 'total_stock', 'out_of_stock']:
            self.assertIn(field, resp.data)
