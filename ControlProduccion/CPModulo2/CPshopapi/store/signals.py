import logging
from django.db.models.signals import post_save
from django.dispatch import receiver
from django.contrib.auth.models import User
from store.models.profile import UserProfile
from store.models.orden import OrdenProduccion

logger = logging.getLogger(__name__)


@receiver(post_save, sender=User)
def user_post_save(sender, instance, created, **kwargs):
    if created:
        UserProfile.objects.create(user=instance)
        _send_welcome(instance)


@receiver(post_save, sender=OrdenProduccion)
def orden_post_save(sender, instance, created, **kwargs):
    if created:
        _send_orden_confirmation(instance)


def _send_welcome(user):
    if not user.email:
        return
    try:
        from store.services.email import send_welcome_email
        send_welcome_email(user)
    except Exception:
        logger.exception('Error enviando correo de bienvenida a %s', user.email)


def _send_orden_confirmation(orden):
    if not orden.operario.email:
        return
    try:
        from store.services.email import send_orden_confirmation_email
        send_orden_confirmation_email(orden)
    except Exception:
        logger.exception('Error enviando confirmacion de orden de produccion #%s', orden.id)
