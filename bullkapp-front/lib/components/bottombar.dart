import 'package:bullkapp/pages/home.dart';
import 'package:bullkapp/pages/login.dart';
import 'package:bullkapp/pages/workout.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../data/constants.dart';
import '../pages/profile.dart';

class CustomBottomAppBar extends StatelessWidget {
  final bool isProfile;
  const CustomBottomAppBar({super.key, this.isProfile = false});

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: const BorderRadius.only(
        topLeft: Radius.circular(38.0),
        topRight: Radius.circular(38.0),
      ),
      child: BottomAppBar(
        height: 70,
        color: defaultColor,
        child: IconTheme(
          data: const IconThemeData(color: Colors.white),
          child: Padding(
            padding: const EdgeInsets.all(12),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Expanded(
                  child: GestureDetector(
                    child: Image.asset(
                      "images/home.png",
                      width: 60,
                      height: 40,
                    ),
                    onTap: () async {
                      await Get.to(
                        () => const HomeScreen(),
                      );
                    },
                  ),
                ),
                Expanded(
                  child: GestureDetector(
                    child: Image.asset(
                      "images/weight.png",
                      width: 40,
                      height: 60,
                    ),
                    onTap: () async {
                      await Get.to(() => const WorkoutScreen(
                            showBottomBar: true,
                          ));
                    },
                  ),
                ),
                Expanded(
                  child: GestureDetector(
                    child: Image.asset(
                      isProfile ? "images/Out.png" : "images/user.png",
                      width: 60,
                      height: 40,
                    ),
                    onTap: () async {
                      isProfile
                          ? toGoOut()
                          : await Get.to(() => const ProfileScreen());
                    },
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

void toGoOut() async {
  Get.defaultDialog(
    title: 'Confirmação de Saída',
    content: const Text('Você realmente deseja sair da aplicação?'),
    textConfirm: 'Sim',
    textCancel: 'Não',
    confirmTextColor: const Color.fromARGB(255, 255, 255, 255),
    buttonColor: Colors.red,
    cancelTextColor: const Color.fromARGB(255, 0, 0, 0),
    onConfirm: () {
      Get.to(() => const Login());
    },
  );
}
