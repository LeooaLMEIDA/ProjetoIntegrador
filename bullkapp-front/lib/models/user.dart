// ignore_for_file: unnecessary_this

class User {
  int? id;
  String? nome = "";
  String dtNascimento = "";
  String sexo = "";
  String celular = "";
  String email = "";
  String tpUsuario = "";
  String urlAvatar = "";
  bool status = true;
  String? senha;

  User(
      {this.id,
      this.nome,
      required this.dtNascimento,
      required this.sexo,
      required this.celular,
      required this.email,
      required this.tpUsuario,
      required this.urlAvatar,
      required this.status,
      this.senha});

  User.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nome = json['nome'];
    dtNascimento = json['dtNascimento'];
    sexo = json['sexo'];
    celular = json['celular'];
    email = json['email'];
    tpUsuario = json['tpUsuario'];
    urlAvatar = json['urlAvatar'];
    status = json['status'];
    senha = json['senha'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = this.id;
    data['nome'] = this.nome;
    data['dtNascimento'] = this.dtNascimento;
    data['sexo'] = this.sexo;
    data['celular'] = this.celular;
    data['email'] = this.email;
    data['tpUsuario'] = this.tpUsuario;
    data['urlAvatar'] = this.urlAvatar;
    data['status'] = this.status;
    data['senha'] = this.senha;
    return data;
  }
}
