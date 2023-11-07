import 'package:bullkapp/models/user.dart';
import 'exercise.dart';

class Workout {
  int? id;
  String? cdTreino;
  int? series;
  int? repeticoes;
  String? descanso;
  bool? status;
  int? peso;
  Exercise? exercicio;

  Workout({
    this.id,
    this.cdTreino,
    this.series,
    this.repeticoes,
    this.descanso,
    this.status,
    this.peso,
    this.exercicio,
  });

  Workout.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    cdTreino = json['cdTreino'];
    series = json['series'];
    repeticoes = json['repeticoes'];
    descanso = json['descanso'];
    status = json['status'];
    peso = json['peso'];
    exercicio =
        json['exercicio'] != null ? Exercise.fromJson(json['exercicio']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['cdTreino'] = cdTreino;
    data['series'] = series;
    data['repeticoes'] = repeticoes;
    data['descanso'] = descanso;
    data['status'] = status;
    data['peso'] = peso;
    if (exercicio != null) {
      data['exercicio'] = exercicio!.toJson();
    }
    return data;
  }
}
