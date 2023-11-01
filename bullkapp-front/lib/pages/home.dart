import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/pages/login.dart';
import 'package:bullkapp/pages/workout.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../components/bottombar.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async => _toGoOut(),
      child: Scaffold(
        appBar: const CustomAppBar(title: ""),
        body: Container(
          alignment: Alignment.topCenter,
          padding: const EdgeInsets.all(8),
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.only(top: 10),
                child: Row(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(10.0),
                      child: GestureDetector(
                        child: Image.asset("images/Treino.png",
                            width: MediaQuery.of(context).size.width - 60),
                        onTap: () async {
                          SharedPreferences prefs =
                              await SharedPreferences.getInstance();
                          String training = prefs.getString('treino') ?? '';
                          await Get.to(
                            () => WorkoutScreen(
                              showBottomBar: true,
                              activeTraining: training,
                            ),
                          );
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
        extendBody: true,
        bottomNavigationBar: const CustomBottomAppBar(),
      ),
    );
  }

  Future<bool> _toGoOut() async {
    bool out = false;
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
    return out;
  }
}
