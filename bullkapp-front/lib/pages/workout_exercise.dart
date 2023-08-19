import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/pages/workout.dart';
import 'package:bullkapp/views/exercise_list.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/appbar.dart';

class WorkoutExercisesScreen extends StatefulWidget {
  final String workoutCode;
  const WorkoutExercisesScreen({super.key, required this.workoutCode});

  @override
  State<WorkoutExercisesScreen> createState() => _WorkoutExercisesScreenState();
}

class _WorkoutExercisesScreenState extends State<WorkoutExercisesScreen> {
  String _trainingSelected = "";
  Widget? _selectedList;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    _selectedList = _getSelectedList();

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
                      child: Image.asset(
                        "images/TreinoA.png",
                        width: 92,
                      ),
                      onTap: () {
                        setState(() {
                          _trainingSelected = "A";
                        });
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoB.png", width: 92),
                      onTap: () {
                        setState(() {
                          _trainingSelected = "B";
                        });
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoC.png", width: 92),
                      onTap: () {
                        setState(() {
                          _trainingSelected = "C";
                        });
                      },
                    ),
                  ),
                ],
              ),
            ),
            Text(_trainingSelected),
            if (_trainingSelected != null)
              Expanded(
                child: Container(
                  color: Colors.white,
                  child: _selectedList,
                ),
              ),
          ],
        ),
      ),
    );
  }

  Widget? _getSelectedList() {
    if (_trainingSelected == "A") {
      return ExerciseList(workoutCode: "A");
    } else if (_trainingSelected == "B") {
      return ExerciseList(workoutCode: "B");
    } else if (_trainingSelected == "C") {
      return ExerciseList(workoutCode: "C");
    } else {
      return null;
    }
  }
}
