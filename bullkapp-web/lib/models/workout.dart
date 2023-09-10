
import 'package:bullkappweb/models/user.dart';
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
  User? usuario;

  Workout(
      {this.id,
      this.cdTreino,
      this.series,
      this.repeticoes,
      this.descanso,
      this.status,
      this.peso,
      this.exercicio,
      this.usuario});

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
    usuario = json['usuario'] != null ? User.fromJson(json['usuario']) : null;
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
    if (usuario != null) {
      data['usuario'] = usuario!.toJson();
    }
    return data;
  }
}
