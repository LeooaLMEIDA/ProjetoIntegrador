// ignore_for_file: avoid_unnecessary_containers, prefer_const_constructors, prefer_const_literals_to_create_immutables, file_names, avoid_print
import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/training_a.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class TrainingScreen extends StatelessWidget {
  const TrainingScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        title: "",
      ),
      body: Container(
        padding: EdgeInsets.all(5),
        alignment: Alignment.topCenter,
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
                      child: Image.asset("images/TreinoA.png", width: 92),
                      onTap: () async {
                        await Get.to(() => TrainingA());
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoB.png", width: 92),
                      onTap: () {
                        print("Treino B");
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoC.png", width: 92),
                      onTap: () {
                        print("Treino C");
                      },
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
