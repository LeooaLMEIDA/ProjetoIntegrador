import 'package:bullkapp/models/user.dart';
import 'package:dio/dio.dart';
import '../data/constants.dart';

class LoginRepository {
  final Dio dio = Dio();
  final String url = '$apiBaseURL/login/mobile';

  Future<User> postLogin(String email, String password) async {
    try {
      final response = await dio.post(
        url,
        data: {
          "email": email,
          "senha": password,
        },
      );

      if (response.statusCode == 200) {
        return User.fromJson(response.data);
      } else {
        final errorData = response.data;
        throw _getErrorMessage(errorData);
      }
    } on DioError catch (e) {
      final errorData = e.response?.data;
      if (errorData != null) {
        throw _getErrorMessage(errorData);
      } else {
        throw ('Erro de autenticação desconhecido');
      }
    }
  }

  String _getErrorMessage(Map<String, dynamic> errorData) {
    final errorMessage = errorData['message'] ??
        (errorData['errors'][0] ?? 'Erro de autenticação desconhecido');
    return errorMessage;
  }
}
