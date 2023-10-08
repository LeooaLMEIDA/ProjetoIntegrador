import 'package:bullkapp/models/equipment.dart';

class Exercise {
  int? id;
  String? description;
  String? orientation;
  String? imgIlustracao;
  bool? status;
  Equipment? aparelho;
  String? grpMusculos;

  Exercise(
      {this.id,
      this.description,
      this.orientation,
      this.imgIlustracao,
      this.status,
      this.aparelho,
      this.grpMusculos});

  Exercise.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    description = json['descricao'];
    orientation = json['orientacao'];
    imgIlustracao = json['imgIlustracao'];
    status = json['status'];
    aparelho =
        json['aparelho'] != null ? Equipment.fromJson(json['aparelho']) : null;
    grpMusculos = json['grpMusculos'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['descricao'] = description;
    data['imgIlustracao'] = imgIlustracao;
    data['status'] = status;
    if (aparelho != null) {
      data['aparelho'] = aparelho!.toJson();
    }
    data['grpMusculos'] = grpMusculos;
    return data;
  }
}
