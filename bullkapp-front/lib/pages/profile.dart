import 'dart:convert';
import 'dart:typed_data';
import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/controllers/user_controller.dart';
import 'package:bullkapp/data/constants.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/fields.dart';
import '../models/evaluation.dart';
import '../repositories/evaluation_repository.dart';
import '../repositories/user_repository.dart';
import 'body_evaluation.dart';

UserController userController = Get.find();

class ProfileScreen extends StatefulWidget {
  final String? photoProfile;
  const ProfileScreen({super.key, this.photoProfile});

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  Uint8List photo = Uint8List(0);
  late bool error = false;

  @override
  void initState() {
    super.initState();
    setPhoto();
  }

  Future<void> setPhoto() async {
    if (widget.photoProfile != null) {
      photo = base64Decode(widget.photoProfile!);
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
                        child: photo.isNotEmpty
                            ? CircleAvatar(
                                backgroundImage: MemoryImage(photo),
                                radius: 57,
                              )
                            : const CircleAvatar(
                                backgroundImage:
                                    AssetImage(defaultImageProfile),
                                radius: 57,
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
                      BodyEvaluationCard(),
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

}

class BodyEvaluationCard extends StatefulWidget {
  const BodyEvaluationCard({super.key});

  @override
  State<BodyEvaluationCard> createState() => _BodyEvaluationCardState();
}

class _BodyEvaluationCardState extends State<BodyEvaluationCard> {
  List<Evaluation> evaluations = [];

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
        onTap: () async {
          await _fetchEvaluationByUser(1);
          return await Get.to(
            BodyEvaluationScreen(
              evaluations: evaluations,
            ),
          );
        },
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

  Future<void> _fetchEvaluationByUser(int id) async {
    try {
      List<Evaluation> fetchedEvaluations =
          await EvaluationRepository().getEvaluationByUser(id);
      setState(() {
        evaluations = fetchedEvaluations;
      });
    } catch (e) {
      throw Exception(
        "Ocorreu um erro ao carregar as informações do Treino Alternativo $e",
      );
    }
  }
}
