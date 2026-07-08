import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import '../../../theme/app_colors.dart';
import '../../providers/auth_provider.dart';

class ProfileScreen extends ConsumerWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final auth = ref.watch(authProvider);

    return Scaffold(
      appBar: AppBar(title: const Text('Perfil')),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                padding: const EdgeInsets.all(20),
                decoration: BoxDecoration(
                  color: AppColors.surface,
                  borderRadius: BorderRadius.circular(16),
                  border: Border.all(color: AppColors.border),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const CircleAvatar(radius: 30, backgroundColor: AppColors.accent, child: Icon(Icons.person, color: Colors.white, size: 28)),
                    const SizedBox(height: 12),
                    Text(auth.user?.username ?? 'Usuario', style: Theme.of(context).textTheme.titleLarge),
                    const SizedBox(height: 4),
                    Text(auth.user?.email ?? 'Sin correo', style: const TextStyle(color: AppColors.textSecondary)),
                  ],
                ),
              ),
              const SizedBox(height: 20),
              if (auth.user?.isStaff == true) ...[
                SizedBox(
                  width: double.infinity,
                  child: ElevatedButton.icon(
                    onPressed: () => context.go('/admin'),
                    icon: const Icon(Icons.admin_panel_settings_outlined),
                    label: const Text('Panel Admin'),
                  ),
                ),
                const SizedBox(height: 12),
              ],
              ListTile(
                leading: const Icon(Icons.lock_outline_rounded),
                title: const Text('Cerrar sesión'),
                trailing: const Icon(Icons.chevron_right_rounded),
                onTap: () async {
                  await ref.read(authProvider.notifier).logout();
                  if (context.mounted) context.go('/login');
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
