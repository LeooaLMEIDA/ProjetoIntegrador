import 'package:bullkapp/models/user.dart';
import 'package:dio/dio.dart';
import '../data/constants.dart';

class LoginRepository {
  final Dio dio = Dio();
  final String url = '$apiBaseURL/login/mobile';

  Future<User> postLogin(String email, String password) async {
    Response response;
    try {
      response = await dio.post(
        url,
        data: {
          "email": email,
          "senha": password,
        },
      );

      if (response.statusCode == 200) {
        User user = User.fromJson(response.data);
        return user;
      } else {
        final error = response.data;
        throw Exception(error['message']);
      }
    } on DioError catch (e) {
      if (e.response != null) {
        final errorData = e.response?.data;
        if (errorData != null) {
          final errorMessage =
              errorData['errors'][0] ?? 'Erro de autenticação desconhecido';
          throw Exception(errorMessage);
        }
      }
      throw Exception(e.message);
    }
  }
}
