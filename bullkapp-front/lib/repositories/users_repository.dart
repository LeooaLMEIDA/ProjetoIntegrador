import 'package:dio/dio.dart';
import '../models/user.dart';

class UserRepository {
  final Dio dio = Dio();
  final String url = 'https://9daf-189-113-55-132.ngrok-free.app/usuario';

  Future<List<User>> getUsers() async {
    try {
      final response = await dio.get(url);
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
