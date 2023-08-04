import 'package:bullkapp/models/workout.dart';
import 'package:dio/dio.dart';
import 'package:dio_http_cache/dio_http_cache.dart';

import '../data/constants.dart';

class WorkoutRepository {
  final Dio dio = Dio();
  final DioCacheManager dioCacheManager = DioCacheManager(CacheConfig());
  final String url = '$apiBaseURL/treino';

  WorkoutRepository() {
    dio.interceptors.add(dioCacheManager.interceptor);
  }

  Future<List<Workout>> getWorkoutsByWorkoutCode(String workoutCode) async {
    try {
      final response = await dio.get(
        '$url/filter?cdTreino=$workoutCode',
        options: buildCacheOptions(
          const Duration(minutes: 2),
        ),
      );

      if (response.statusCode == 200) {
        List<Workout> workouts = (response.data as List)
            .map((json) => Workout.fromJson(json))
            .toList();
        return workouts;
      } else {
        throw Exception(
            'Erro ao obter os treinos. Código de status: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erro ao obter os treinos: $e');
    }
  }

  // Future<List<Workout>> getWorkouts() async {
  //   // dioCacheManager.clearAll(); //caso precise forçar a zerar o cache em testes
  //   try {
  //     final response = await dio.get(
  //       url,
  //       options: buildCacheOptions(
  //         const Duration(minutes: 2),
  //       ),
  //     );

  //     if (response.statusCode == 200) {
  //       List<Workout> workouts = (response.data as List)
  //           .map((json) => Workout.fromJson(json))
  //           .toList();
  //       return workouts;
  //     } else {
  //       throw Exception(
  //           'Erro ao obter os treinos. Código de status: ${response.statusCode}');
  //     }
  //   } catch (e) {
  //     throw Exception('Erro ao obter os treinos: $e');
  //   }
  // }
}
