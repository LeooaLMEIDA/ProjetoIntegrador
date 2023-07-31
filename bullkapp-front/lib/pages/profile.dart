import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/fields.dart';
import 'body_evaluation.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: "",
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Stack(
          children: [
            Column(
              children: [
                SizedBox(
                  height: 120,
                  child: Align(
                    alignment: Alignment.center,
                    child: Container(
                      width: 120,
                      height: 120,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        border: Border.all(
                          color: const Color.fromARGB(250, 1, 30, 62),
                          width: 3.0,
                        ),
                      ),
                      clipBehavior: Clip.antiAlias,
                      child: Image.asset(
                        'images/Avatar.png',
                        width: 100,
                        height: 100,
                        fit: BoxFit.fill,
                      ),
                    ),
                  ),
                ),
                const Row(
                  children: [
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "Nome",
                        inputType: TextInputType.name,
                      ),
                    ),
                    SizedBox(
                      width: 8.0,
                    ),
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "Telefone",
                        inputType: TextInputType.phone,
                      ),
                    ),
                  ],
                ),
                const Row(
                  children: [
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "E-mail",
                        inputType: TextInputType.emailAddress,
                      ),
                    ),
                  ],
                ),
                const Row(
                  children: [
                    Expanded(
                      child: CustomReadOnlyField(
                          label: "Data Nascimento",
                          inputType: TextInputType.datetime),
                    ),
                    SizedBox(
                      width: 8.0,
                    ),
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "Sexo",
                        inputType: TextInputType.text,
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
      extendBody: false,
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}

class AlternativeWorkoutCard extends StatelessWidget {
  const AlternativeWorkoutCard({
    super.key,
  });

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
                      color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
