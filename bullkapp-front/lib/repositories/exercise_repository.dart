import 'package:bullkapp/data/constants.dart';
import 'package:bullkapp/models/exercise.dart';
import 'package:dio/dio.dart';

class ExerciseRepository {
  final Dio dio = Dio();
  final String url = '$apiBaseURL/exercicio';

  Future<Exercise> getExercise(int? exerciseId) async {
    try {
      final response = await dio.get('$url/$exerciseId');

      if (response.statusCode == 200) {
        Exercise exercise = Exercise.fromJson(response.data);
        return exercise;
      } else {
        throw Exception(
            "Erro ao buscar Exercício. Código de Status: ${response.statusCode}");
      }
    } catch (e) {
      throw Exception("Houve um problema para requerir o Exercício $e");
    }
  }
}
