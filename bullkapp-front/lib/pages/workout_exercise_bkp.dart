import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/pages/workout.dart';
import 'package:bullkapp/views/exercise_list.dart';
import 'package:flutter/material.dart';

class WorkoutExercisesScreen extends StatelessWidget {
  final String workoutCode;
  const WorkoutExercisesScreen({super.key, required this.workoutCode});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          Container(
            width: 400,
            height: 220,
            color: Colors.white,
            child: const WorkoutScreen(
              showBottomBar: false,
            ),
          ),
          Expanded(
            child: Container(
              color: Colors.white,
              child: ExerciseList(
                workoutCode: workoutCode,
              ),
            ),
          ),
        ],
      ),
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}
