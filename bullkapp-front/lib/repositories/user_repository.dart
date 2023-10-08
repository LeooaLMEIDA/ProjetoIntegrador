import 'dart:typed_data';

import 'package:dio/dio.dart';
import 'package:dio_http_cache/dio_http_cache.dart';
import '../data/constants.dart';
import '../models/photo.dart';
import '../models/user.dart';

class UserRepository {
  final Dio dio = Dio();
  final DioCacheManager dioCacheManager = DioCacheManager(CacheConfig());

  Future<String?> getPhoto(int id) async {
    dioCacheManager.clearAll();
    final String url = "$apiBaseURL/usuario/getPhoto/$id";

    try {
      final response = await dio.get(url);

      if (response.statusCode == 200 && response.data != null) {
        Photo photo = Photo.fromJson(response.data);
        return photo.image;
      } else {
        throw Exception(
          "Erro ao buscar o usuário. Código de status: ${response.statusCode}",
        );
      }
    } catch (e) {
      throw Exception("Houve um problema para requerir o usuário $e");
    }
  }

  Future<Uint8List> fetchImage(int id) async {
    final String url = "$apiBaseURL/usuario/getPhoto/$id";
    try {
      Response response = await Dio()
          .get(url, options: Options(responseType: ResponseType.bytes));
      return Uint8List.fromList(response.data);
    } catch (error) {
      throw Exception('Falha ao carregar a imagem: $error');
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
