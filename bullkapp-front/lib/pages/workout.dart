import 'package:bullkapp/components/appbar.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/bottombar.dart';
import '../components/list_card.dart';
import '../models/workout.dart';
import '../repositories/workout_repository.dart';
import 'exercise_detail.dart';

final workoutRepository = WorkoutRepository();

class WorkoutScreen extends StatefulWidget {
  final bool showBottomBar;

  const WorkoutScreen({super.key, required this.showBottomBar});

  @override
  State<WorkoutScreen> createState() => _WorkoutScreenState();
}

class _WorkoutScreenState extends State<WorkoutScreen> {
  bool _isLoading = false;
  String _trainingSelected = "";
  List<Workout> workouts = [];

  final Map<String, String> _trainingIcons = {
    "A": "images/TreinoA.png",
    "B": "images/TreinoB.png",
    "C": "images/TreinoC.png",
  };

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
                children: _trainingIcons.entries.map((entry) {
                  final trainingCode = entry.key;
                  final iconPath = entry.value;
                  return Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset(
                        iconPath,
                        width: 92,
                      ),
                      onTap: () {
                        setState(() {
                          _trainingSelected = trainingCode;
                        });
                        _fetchWorkout();
                      },
                    ),
                  );
                }).toList(),
              ),
            ),
            Expanded(
              child: Container(
                color: const Color.fromARGB(255, 255, 255, 255),
                child: _isLoading
                    ? const Center(
                        child: CircularProgressIndicator(),
                      )
                    : _getSelectedList(),
              ),
            ),
          ],
        ),
      ),
      extendBody: false,
      bottomNavigationBar:
          widget.showBottomBar ? const CustomBottomAppBar() : null,
    );
  }

  Widget _getSelectedList() {
    return ListView.builder(
      itemCount: workouts.length,
      itemBuilder: (context, index) {
        final workout = workouts[index];
        return Padding(
          padding: const EdgeInsets.all(4.0),
          child: CustomListCard(
            exerciceName: workout.exercicio?.descricao ?? '',
            onTap: () async {
              await Get.to(
                () => ExerciseDetail(
                  exerciseId: workout.id ?? 0,
                  description: workout.exercicio?.descricao ?? '',
                  imgIllustration: workout.exercicio?.imgIlustracao ?? '',
                  repetitions: workout.repeticoes ?? 0,
                  rest: workout.descanso ?? "",
                  series: workout.series ?? 0,
                ),
              );
            },
            repetition: workout.repeticoes.toString(),
            series: workout.series.toString(),
          ),
        );
      },
    );
  }

  Future<void> _fetchWorkout() async {
    setState(() {
      _isLoading = true;
    });
    try {
      List<Workout> fetchedWorkout =
          await workoutRepository.getWorkoutsByWorkoutCode(_trainingSelected);
      setState(() {
        workouts = fetchedWorkout;
      });
    } catch (e) {
      Get.snackbar(
        'ERRO',
        'Erro ao obter os Treinos!',
        snackPosition: SnackPosition.BOTTOM,
        backgroundColor: Colors.red,
        colorText: Colors.white,
      );
    }
    setState(() {
      _isLoading = false;
    });
  }
}
