// ignore_for_file: avoid_unnecessary_containers, prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/pages/workout.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/bottombar.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(title: ""),
      body: Container(
        alignment: Alignment.topCenter,
        padding: EdgeInsets.all(8),
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
                        await Get.to(
                          () => WorkoutScreen(showBottomBar: true),
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
      bottomNavigationBar: CustomBottomAppBar(),
    );
  }
}
