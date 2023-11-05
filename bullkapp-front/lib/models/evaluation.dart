class Evaluation {
  int? id;
  String? descricao;
  String? observacao;
  String? arqAvaliacao;

  Evaluation({this.id, this.descricao, this.observacao, this.arqAvaliacao});

  Evaluation.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    descricao = json['descricao'];
    observacao = json['observacao'];
    arqAvaliacao = json['arqAvaliacao'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['descricao'] = descricao;
    data['observacao'] = observacao;
    data['arqAvaliacao'] = arqAvaliacao;
    return data;
  }
}
