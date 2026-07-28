from django.urls import path, include
from rest_framework.routers import DefaultRouter
from rest_framework_simplejwt.views import TokenRefreshView, TokenVerifyView

from store.views.health    import health_check
from store.views.auth      import RegisterView, LogoutView, PasswordResetRequestView, PasswordResetConfirmView
from store.views.user      import UserViewSet
from store.views.linea     import LineaProduccionViewSet
from store.views.pieza     import PiezaViewSet
from store.views.orden     import OrdenProduccionViewSet
from store.views.email     import SendNotificationView
from store.serializers.auth import CustomTokenView

router = DefaultRouter()
router.register('users',      UserViewSet,             basename='user')
router.register('lineas',     LineaProduccionViewSet,  basename='linea')
router.register('piezas',     PiezaViewSet,            basename='pieza')
router.register('ordenes',    OrdenProduccionViewSet,  basename='orden')

urlpatterns = [
    path('health/',                     health_check),
    path('auth/register/',              RegisterView.as_view()),
    path('auth/login/',                 CustomTokenView.as_view()),
    path('auth/token/refresh/',         TokenRefreshView.as_view()),
    path('auth/token/verify/',          TokenVerifyView.as_view()),
    path('auth/logout/',                LogoutView.as_view()),
    path('auth/password-reset/',        PasswordResetRequestView.as_view(), name='auth-password-reset'),
    path('auth/password-reset/confirm/', PasswordResetConfirmView.as_view(), name='auth-password-reset-confirm'),
    path('emails/send/',                SendNotificationView.as_view(), name='email-send-notification'),
    path('', include(router.urls)),
]
