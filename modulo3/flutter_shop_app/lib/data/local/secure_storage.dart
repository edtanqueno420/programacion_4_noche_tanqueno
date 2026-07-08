// lib/data/local/secure_storage.dart

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class SecureStorage {
  static const _storage = FlutterSecureStorage();

  Future<String?> getAccess() async => _storage.read(key: 'access_token');

  Future<String?> getRefresh() async => _storage.read(key: 'refresh_token');

  Future<void> saveAccessToken(String token) async =>
      _storage.write(key: 'access_token', value: token);

  Future<void> saveTokens(String access, String refresh) async {
    await _storage.write(key: 'access_token', value: access);
    await _storage.write(key: 'refresh_token', value: refresh);
  }

  Future<void> clearSession() async {
    await _storage.delete(key: 'access_token');
    await _storage.delete(key: 'refresh_token');
  }
}

final secureStorageProvider = Provider<SecureStorage>((ref) => SecureStorage());
