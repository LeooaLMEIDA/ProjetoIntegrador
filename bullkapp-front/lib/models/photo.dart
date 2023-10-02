class Photo {
  String? _imagem;

  Photo({String? imagem}) {
    if (imagem != null) {
      this._imagem = imagem;
    }
  }

  String? get imagem => _imagem;
  set imagem(String? imagem) => _imagem = imagem;

  Photo.fromJson(Map<String, dynamic> json) {
    _imagem = json['imagem'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['imagem'] = this._imagem;
    return data;
  }
}
