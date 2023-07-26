import 'package:bullkapp/models/user.dart';
import 'package:bullkapp/repositories/users_repository.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  test('Teste de consulta de usuários', () async {
    UserRepository userRepository = UserRepository();
    try {
      List<User> users = await userRepository.getUsers();
      print(users[1]);
    } catch (e) {
      print('Erro ao obter os usuários: $e');
    }
  });
}
