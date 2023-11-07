import 'package:bullkapp/models/workout.dart';
import 'package:dio/dio.dart';
import 'package:dio_http_cache/dio_http_cache.dart';
import 'package:get/get.dart';

import '../controllers/user_controller.dart';
import '../data/constants.dart';
import '../models/photo.dart';

class WorkoutRepository {
  final Dio dio = Dio();
  final DioCacheManager dioCacheManager = DioCacheManager(CacheConfig());
  final String url = '$apiBaseURL/treino';

  UserController userController = Get.find();

  int id = 0;

  WorkoutRepository() {
    dio.interceptors.add(dioCacheManager.interceptor);
  }

  Future<List<Workout>> getWorkoutsByWorkoutCode(String workoutCode) async {
    try {
      final response = await dio.get(
        '$url/filter/usuario?cdTreino=$workoutCode&usuario_id=${userController.id}',
        options: buildCacheOptions(
          const Duration(minutes: 4),
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

  Future<Workout> getWorkout(int? exerciseId) async {
    try {
      final response = await dio.get(
        '$url/$exerciseId',
        options: buildCacheOptions(
          const Duration(minutes: 2),
        ),
      );

      if (response.statusCode == 200) {
        Workout workout = Workout.fromJson(response.data);
        return workout;
      } else {
        throw Exception(
            "Erro ao buscar Exercício. Código de Status: ${response.statusCode}");
      }
    } catch (e) {
      throw Exception("Houve um problema para requerir o Exercício $e");
    }
  }

  Future<Workout> getAlternativeWorkout(int? code) async {
    try {
      final response = await dio.get(
        '$url/filter/alternativo/$code',
        options: buildCacheOptions(
          const Duration(minutes: 2),
        ),
      );

      if (response.statusCode == 200) {
        Workout workout = Workout.fromJson(response.data);
        return workout;
      } else {
        throw Exception(
            'Erro ao obter o treino. Código de status: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erro ao obter os treinos: $e');
    }
  }

  Future<String?> getGif(int id) async {
    dioCacheManager.clearAll();
    final String url = "$apiBaseURL/usuario/getGif/$id";

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
