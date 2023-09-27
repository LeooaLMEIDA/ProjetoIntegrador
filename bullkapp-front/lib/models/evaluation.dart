class Evaluation {
  int? id;
  String? descricao;
  String? observacao;

  Evaluation({this.id, this.descricao, this.observacao});

  Evaluation.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    descricao = json['descricao'];
    observacao = json['observacao'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['descricao'] = descricao;
    data['observacao'] = observacao;
    return data;
  }
}
