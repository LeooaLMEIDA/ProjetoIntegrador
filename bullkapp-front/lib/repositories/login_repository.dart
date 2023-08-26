import 'package:bullkapp/models/user.dart';
import 'package:dio/dio.dart';
import '../data/constants.dart';

class LoginRepository {
  final Dio dio = Dio();
  final String url = '$apiBaseURL/login';

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
        print(e.response?.data);
        print(e.response?.headers);
        print(e.response?.requestOptions);
        final errorData = e.response?.data;
        if (errorData != null) {
          final errorMessage =
              errorData['message'] ?? 'Erro de autenticação desconhecido';
          throw Exception(errorMessage);
        }
      }
      throw Exception(e.message);
    }
  }
}
