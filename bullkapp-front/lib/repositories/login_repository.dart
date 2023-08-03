import 'package:dio/dio.dart';

import '../data/constants.dart';

class LoginRepository {
  final Dio dio = Dio();
  final String url = '$apiBaseURL/login';
  bool isAllowed = false;

  Future<bool> postLogin(String email, String password) async {
    try {
      final response = await dio.post(
        url,
        data: {
          "email": email,
          "senha": password,
        },
      );

      if (response.statusCode == 200 && response.data == true) {
        isAllowed = response.data;
      }
    } catch (e) {
      isAllowed = false;
      throw Exception(
        'Houve um erro ao realizar o Login $e',
      );
    }
    return isAllowed;
  }
}
