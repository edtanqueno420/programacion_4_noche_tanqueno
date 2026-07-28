from django.test import TestCase
from rest_framework import status

from .helpers import create_user, create_staff, auth_client, create_linea


class LineaProduccionPermissionTests(TestCase):

    def setUp(self):
        self.user  = create_user('eve')
        self.staff = create_staff()
        self.linea = create_linea()

    def test_authenticated_user_can_list(self):
        resp = auth_client(self.user).get('/api/lineas/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)

    def test_unauthenticated_returns_401(self):
        from rest_framework.test import APIClient
        resp = APIClient().get('/api/lineas/')
        self.assertEqual(resp.status_code, status.HTTP_401_UNAUTHORIZED)

    def test_regular_user_cannot_create(self):
        resp = auth_client(self.user).post('/api/lineas/', {
            'name': 'Test', 'slug': 'test'
        })
        self.assertEqual(resp.status_code, status.HTTP_403_FORBIDDEN)

    def test_staff_can_create(self):
        resp = auth_client(self.staff).post('/api/lineas/', {
            'name': 'Mecanica', 'slug': 'mecanica', 'is_active': True
        })
        self.assertEqual(resp.status_code, status.HTTP_201_CREATED)

    def test_staff_can_delete(self):
        resp = auth_client(self.staff).delete(f'/api/lineas/{self.linea.id}/')
        self.assertEqual(resp.status_code, status.HTTP_204_NO_CONTENT)


class LineaProduccionFilterTests(TestCase):

    def setUp(self):
        self.client = auth_client(create_user('filters'))
        create_linea('Electronica', 'electronica', is_active=True)
        create_linea('Mecanica',    'mecanica',    is_active=False)

    def test_filter_by_active(self):
        resp = self.client.get('/api/lineas/?is_active=true')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['count'], 1)
        self.assertEqual(resp.data['results'][0]['name'], 'Electronica')

    def test_search_by_name(self):
        resp = self.client.get('/api/lineas/?search=electro')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        self.assertEqual(resp.data['count'], 1)

    def test_stats_returns_expected_fields(self):
        resp = self.client.get('/api/lineas/stats/')
        self.assertEqual(resp.status_code, status.HTTP_200_OK)
        for field in ['total', 'active', 'inactive', 'detail']:
            self.assertIn(field, resp.data)
