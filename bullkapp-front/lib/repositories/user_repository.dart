import 'package:dio/dio.dart';
import 'package:dio_http_cache/dio_http_cache.dart';
import '../models/user.dart';

class UserRepository {
  final Dio dio = Dio();
  final DioCacheManager dioCacheManager = DioCacheManager(CacheConfig());
  final String url = 'https://9652-170-82-83-40.ngrok-free.app/usuario';

  UserRepository() {
    dio.interceptors.add(dioCacheManager.interceptor);
  }

  Future<List<User>> getUsers() async {
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
