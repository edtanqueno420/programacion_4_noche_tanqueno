from rest_framework.permissions import BasePermission, SAFE_METHODS


class IsSupervisorOrReadOnly(BasePermission):
    def has_permission(self, request, view):
        if request.method in SAFE_METHODS:
            return bool(request.user and request.user.is_authenticated)
        return bool(request.user and request.user.is_staff)


class IsOwnerOrSupervisor(BasePermission):
    def has_object_permission(self, request, view, obj):
        return obj.operario == request.user or request.user.is_staff
