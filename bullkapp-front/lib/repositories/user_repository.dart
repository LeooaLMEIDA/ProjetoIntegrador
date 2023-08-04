import 'package:dio/dio.dart';
import 'package:dio_http_cache/dio_http_cache.dart';
import '../data/constants.dart';
import '../models/user.dart';

class UserRepository {
  final Dio dio = Dio();
  final DioCacheManager dioCacheManager = DioCacheManager(CacheConfig());

  UserRepository() {
    dio.interceptors.add(dioCacheManager.interceptor);
  }

  Future<User> getByEmail(String email) async {
    final String url = "$apiBaseURL/usuario/email?email=$email";
    try {
      final response = await dio.get(
        url,
        options: buildCacheOptions(
          const Duration(minutes: 120),
        ),
      );

      if (response.statusCode == 200 && response.data != null) {
        User user = User.fromJson(response.data);
        return user;
      } else {
        throw Exception(
            "Erro ao buscar o usuário. Código de status: ${response.statusCode}");
      }
    } catch (e) {
      throw Exception("Houve um problema para requerir o usuário $e");
    }
  }

  Future<List<User>> getAllUsers() async {
    const String url = '$apiBaseURL/usuario';
    try {
      final response = await dio.get(
        url,
        options: buildCacheOptions(
          const Duration(minutes: 120),
        ),
      );

      if (response.statusCode == 200) {
        List<User> users =
            (response.data as List).map((json) => User.fromJson(json)).toList();
        return users;
      } else {
        throw Exception(
            'Erro ao obter os usuários. Código de status: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erro ao obter os usuários: $e');
    }
  }
}
