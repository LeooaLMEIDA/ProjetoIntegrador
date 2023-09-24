// class EvaluationRepository {
//   final Dio dio = Dio();
//   final String url = '$apiBaseURL/exercicio';

//   Future<Evaluation> getExercise(int? exerciseId) async {
//     try {
//       final response = await dio.get('$url/$exerciseId');

//       if (response.statusCode == 200) {
//         Evaluation evaluation = Evaluation.fromJson(response.data);
//         return evaluation;
//       } else {
//         throw Exception(
//             "Erro ao buscar Exercício. Código de Status: ${response.statusCode}");
//       }
//     } catch (e) {
//       throw Exception("Houve um problema para requerir a Avaliação $e");
//     }
//   }
// }