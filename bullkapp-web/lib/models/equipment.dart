class Equipment {
  int? id;
  String? descricao;
  bool? status;

  Equipment({this.id, this.descricao, this.status});

  Equipment.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    descricao = json['descricao'];
    status = json['status'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['descricao'] = descricao;
    data['status'] = status;
    return data;
  }
}
