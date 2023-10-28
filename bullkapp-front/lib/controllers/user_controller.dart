import 'package:get/get.dart';

class UserController extends GetxController {
  final RxInt _id = 0.obs;
  final RxString _name = ''.obs;
  final RxString _phone = ''.obs;
  final RxString _email = ''.obs;
  final RxString _dtBirth = ''.obs;
  final RxString _gender = ''.obs;
  final RxString _photo = ''.obs;

  int get id => _id.value;
  String get name => _name.value;
  String get phone => _phone.value;
  String get email => _email.value;
  String get dtBirth => _dtBirth.value;
  String get gender => _gender.value;
  String get photo => _photo.value;

  void setId(int id) {
    _id.value = id;
  }

  void setEmail(String email) {
    _email.value = email;
  }

  void setName(String name) {
    _name.value = name;
  }

  void setPhone(String phone) {
    _phone.value = phone;
  }

  void setDtBirth(String dtBirth) {
    _dtBirth.value = dtBirth;
  }

  void setGender(String gender) {
    _gender.value = gender;
  }

  void setPhoto(String photo) {
    _photo.value = photo;
  }
}
