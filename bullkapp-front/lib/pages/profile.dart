import 'dart:convert';
import 'dart:typed_data';
import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/controllers/user_controller.dart';
import 'package:bullkapp/data/constants.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/fields.dart';
import '../repositories/user_repository.dart';
import 'body_evaluation.dart';

UserController userController = Get.find();

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  Uint8List photo = Uint8List(0);

  @override
  void initState() {
    super.initState();
    setPhoto();
  }

  Future<void> setPhoto() async {
    final photoData = await _getPhotoUser(userController.id);
    if (photoData != null) {
      setState(() {
        photo = base64Decode(photoData);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    DateTime dtNascTime = DateTime.parse(userController.dtBirth);
    String dtNascimento =
        "${dtNascTime.day.toString().padLeft(2, '0')}/${dtNascTime.month.toString().padLeft(2, '0')}/${dtNascTime.year}";
    return Scaffold(
      appBar: const CustomAppBar(),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Stack(
            children: [
              Column(
                children: [
                  Align(
                    alignment: Alignment.center,
                    child: Padding(
                      padding: const EdgeInsets.all(10.0),
                      child: CircleAvatar(
                        backgroundColor: defaultColor,
                        radius: 60,
                        child: CircleAvatar(
                          backgroundImage: MemoryImage(photo),
                          radius: 57,
                          onBackgroundImageError: (exception, stackTrace) {
                            Image.asset(
                              defaultImageProfile,
                              fit: BoxFit.cover,
                              width: 45,
                              height: 45,
                            );
                          },
                        ),
                      ),
                    ),
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: CustomReadOnlyField(
                          label: "Nome",
                          inputType: TextInputType.name,
                          content: userController.name,
                        ),
                      ),
                      const SizedBox(
                        width: 8.0,
                      ),
                      Expanded(
                        child: CustomReadOnlyField(
                          label: "Telefone",
                          inputType: TextInputType.phone,
                          content: userController.phone,
                        ),
                      ),
                    ],
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: CustomReadOnlyField(
                          label: "E-mail",
                          inputType: TextInputType.emailAddress,
                          content: userController.email,
                        ),
                      ),
                    ],
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: CustomReadOnlyField(
                          label: "Data Nascimento",
                          inputType: TextInputType.datetime,
                          content: dtNascimento,
                        ),
                      ),
                      const SizedBox(width: 8.0),
                      Expanded(
                        child: CustomReadOnlyField(
                          label: "Sexo",
                          inputType: TextInputType.text,
                          content: userController.gender,
                        ),
                      ),
                    ],
                  ),
                  const Padding(
                    padding: EdgeInsets.only(top: 16.0),
                    child: Row(
                      children: [
                        Text(
                          "Recursos",
                          style: TextStyle(
                            fontFamily: 'Voltaire',
                            fontSize: 30,
                          ),
                        ),
                      ],
                    ),
                  ),
                  const Row(
                    children: [
                      AlternativeWorkoutCard(),
                    ],
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
      extendBody: false,
      bottomNavigationBar: const CustomBottomAppBar(isProfile: true),
    );
  }

  Future<String?> _getPhotoUser(int id) async {
    try {
      UserRepository userRepository = UserRepository();
      final userPhoto = await userRepository.getPhoto(id);
      userPhoto?.replaceAll(RegExp(r'\s+'), '');
      return userPhoto?.split(',').last;
    } catch (e) {
      throw Exception(e);
    }
  }
}

class AlternativeWorkoutCard extends StatelessWidget {
  const AlternativeWorkoutCard({super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(30),
      ),
      clipBehavior: Clip.hardEdge,
      borderOnForeground: true,
      color: const Color.fromARGB(250, 1, 30, 62),
      child: InkWell(
        onTap: () async => await Get.to(
          const BodyEvaluationScreen(),
        ),
        splashColor: const Color.fromARGB(250, 1, 30, 62).withAlpha(255),
        child: SizedBox(
          width: 200,
          height: 80,
          child: Stack(
            children: [
              Positioned(
                bottom: 12,
                left: 15,
                child: Image.asset(
                  'images/Balanca.png',
                  width: 50,
                  height: 55,
                ),
              ),
              const Positioned(
                bottom: 25,
                right: 15,
                child: Text(
                  'Avaliações',
                  style: TextStyle(
                    fontFamily: 'Voltaire',
                    fontSize: 30,
                    color: Colors.white,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
