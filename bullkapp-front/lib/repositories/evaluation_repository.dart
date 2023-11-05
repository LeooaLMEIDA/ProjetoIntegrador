import 'package:dio/dio.dart';
import '../data/constants.dart';
import '../models/evaluation.dart';

class EvaluationRepository {
  final Dio dio = Dio();
  final String url = '$apiBaseURL/avaliacao';

  Future<String> getEvaluation(int? evaluationId) async {
    return '$url/downloadFile/$evaluationId';
  }

  Future<List<Evaluation>> getEvaluationByUser(int id) async {
    try {
      final response = await dio.get('$url/filter/usuario/$id');

      if (response.statusCode == 200) {
        List<Evaluation> evaluations = (response.data as List)
            .map((json) => Evaluation.fromJson(json))
            .toList();
        return evaluations;
      } else {
        throw Exception(
          "Erro ao buscar Avaliação. Código de Status: ${response.statusCode}",
        );
      }
    } catch (e) {
      throw Exception("Houve um problema para requerir a Avaliação $e");
    }
  }

  Future<List<Evaluation>> getAllEvaluation(int id) async {
    try {
      final response = await dio.get(url);

      if (response.statusCode == 200) {
        List<Evaluation> workouts = (response.data as List)
            .map((json) => Evaluation.fromJson(json))
            .toList();
        return workouts;
      } else {
        throw Exception(
          "Erro ao buscar Avaliação. Código de Status: ${response.statusCode}",
        );
      }
    } catch (e) {
      throw Exception("Houve um problema para requerir a Avaliação $e");
    }
  }
}
