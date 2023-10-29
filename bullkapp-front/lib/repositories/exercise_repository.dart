import 'package:bullkapp/data/constants.dart';
import 'package:bullkapp/models/exercise.dart';
import 'package:dio/dio.dart';

import '../models/photo.dart';

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

  Future<String?> getGif(int id) async {
    final String url = "$apiBaseURL/exercicio/getGif/$id";

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

}
