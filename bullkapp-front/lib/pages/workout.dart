import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/pages/workout_exercise.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/bottombar.dart';

class WorkoutScreen extends StatelessWidget {
  final bool showBottomBar;

  const WorkoutScreen({super.key, required this.showBottomBar});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: "",
      ),
      body: Container(
        padding: const EdgeInsets.all(5),
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
                        await Get.to(
                          () => const WorkoutExercisesScreen(
                            workoutCode: 'A',
                          ),
                        );
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoB.png", width: 92),
                      onTap: () async {
                        await Get.to(
                          () => const WorkoutExercisesScreen(
                            workoutCode: 'B',
                          ),
                        );
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoC.png", width: 92),
                      onTap: () async {
                        await Get.to(
                          () => const WorkoutExercisesScreen(
                            workoutCode: 'B',
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
      bottomNavigationBar: showBottomBar ? const CustomBottomAppBar() : null,
    );
  }
}
