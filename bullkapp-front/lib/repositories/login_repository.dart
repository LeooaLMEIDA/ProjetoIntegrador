import 'package:dio/dio.dart';
import 'package:get/get.dart';

import '../models/login.dart';

class LoginRepository {
  final Dio dio = Dio();
  final String url = 'https://9652-170-82-83-40.ngrok-free.app/login';

  Future<Login> postLogin(String email, String password) async {
    try {
      final response = await dio.post(
        url,
        data: {
          "email": email,
          "senha": password,
        },
      );

      if (response.statusCode == 200 && response.data == true) {
        return Login(
          email: email,
          password: password,
        );
      } else {
        throw Exception(
          'Ocorreu um erro ao realizar o Login ${response.statusCode}',
        );
      }
    } catch (e) {
      throw Exception(
        'Houve um erro ao realizar o Login $e',
      );
    }
  }
}
