import 'package:bullkapp/models/equipment.dart';

class Exercise {
  int? id;
  String? descricao;
  String? imgIlustracao;
  String? vdInstrucao;
  bool? status;
  Equipment? aparelho;
  String? grpMusculos;

  Exercise(
      {this.id,
      this.descricao,
      this.imgIlustracao,
      this.vdInstrucao,
      this.status,
      this.aparelho,
      this.grpMusculos});

  Exercise.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    descricao = json['descricao'];
    imgIlustracao = json['imgIlustracao'];
    vdInstrucao = json['vdInstrucao'];
    status = json['status'];
    aparelho =
        json['aparelho'] != null ? Equipment.fromJson(json['aparelho']) : null;
    grpMusculos = json['grpMusculos'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['descricao'] = descricao;
    data['imgIlustracao'] = imgIlustracao;
    data['vdInstrucao'] = vdInstrucao;
    data['status'] = status;
    if (aparelho != null) {
      data['aparelho'] = aparelho!.toJson();
    }
    data['grpMusculos'] = grpMusculos;
    return data;
  }
}
