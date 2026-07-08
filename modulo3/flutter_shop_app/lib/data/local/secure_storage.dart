// lib/data/local/secure_storage.dart

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class SecureStorage {
  static const _storage = FlutterSecureStorage();

  Future<String?> getAccess() async => _storage.read(key: 'access_token');

  Future<String?> getRefresh() async => _storage.read(key: 'refresh_token');

  Future<bool> isLoggedIn() async {
    final access = await getAccess();
    return access != null && access.isNotEmpty;
  }

  Future<Map<String, String>?> getUser() async {
    final id = await _storage.read(key: 'user_id');
    final username = await _storage.read(key: 'username');
    final email = await _storage.read(key: 'email');
    final isStaff = await _storage.read(key: 'is_staff');

    if (id == null || username == null || email == null || isStaff == null) {
      return null;
    }

    return {
      'id': id,
      'username': username,
      'email': email,
      'is_staff': isStaff,
    };
  }

  Future<void> saveAccessToken(String token) async =>
      _storage.write(key: 'access_token', value: token);

  Future<void> saveTokens(String access, String refresh) async {
    await _storage.write(key: 'access_token', value: access);
    await _storage.write(key: 'refresh_token', value: refresh);
  }

  Future<void> saveUser({
    required int id,
    required String username,
    required String email,
    required bool isStaff,
  }) async {
    await _storage.write(key: 'user_id', value: id.toString());
    await _storage.write(key: 'username', value: username);
    await _storage.write(key: 'email', value: email);
    await _storage.write(key: 'is_staff', value: isStaff.toString());
  }

  Future<void> clearSession() async {
    await _storage.delete(key: 'access_token');
    await _storage.delete(key: 'refresh_token');
    await _storage.delete(key: 'user_id');
    await _storage.delete(key: 'username');
    await _storage.delete(key: 'email');
    await _storage.delete(key: 'is_staff');
  }
}

final secureStorageProvider = Provider<SecureStorage>((ref) => SecureStorage());
