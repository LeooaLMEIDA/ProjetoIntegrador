class Workout {
  String? cdTreino;
  int? series;
  int? repeticoes;
  String? descanso;
  bool? status;
  int? peso;
  Exercicio? exercicio;
  Exercicio? usuario;

  Workout(
      {this.cdTreino,
      this.series,
      this.repeticoes,
      this.descanso,
      this.status,
      this.peso,
      this.exercicio,
      this.usuario});

  Workout.fromJson(Map<String, dynamic> json) {
    cdTreino = json['cdTreino'];
    series = json['series'];
    repeticoes = json['repeticoes'];
    descanso = json['descanso'];
    status = json['status'];
    peso = json['peso'];
    exercicio = json['exercicio'] != null
        ? Exercicio.fromJson(json['exercicio'])
        : null;
    usuario =
        json['usuario'] != null ? Exercicio.fromJson(json['usuario']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
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

class Exercicio {
  int? id;

  Exercicio({this.id});

  Exercicio.fromJson(Map<String, dynamic> json) {
    id = json['id'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    return data;
  }
}
