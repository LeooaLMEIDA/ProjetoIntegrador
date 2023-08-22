import 'package:flutter/material.dart';
import '../components/appbar.dart';
import '../views/_exercise_list.dart';

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
      return const ExerciseList(workoutCode: "A");
    } else if (_trainingSelected == "B") {
      return const ExerciseList(workoutCode: "B");
    } else if (_trainingSelected == "C") {
      return const ExerciseList(workoutCode: "C");
    } else {
      return null;
    }
  }
}
