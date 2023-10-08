class Photo {
  String? image;

  Photo({this.image});

  Photo.fromJson(Map<String, dynamic> json) {
    image = json['imagem'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['imagem'] = image;
    return data;
  }
}
