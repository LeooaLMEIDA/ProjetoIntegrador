// ignore_for_file: library_private_types_in_public_api, avoid_print

import 'package:bullkapp/models/workout.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/list_card.dart';
import '../pages/workout_detail.dart';
import '../repositories/workout_repository.dart'; // Importe o UserRepository

final workoutRepository = WorkoutRepository();

class WorkoutList extends StatefulWidget {
  const WorkoutList({super.key});

  @override
  _WorkoutListState createState() => _WorkoutListState();
}

class _WorkoutListState extends State<WorkoutList> {
  List<Workout> workouts = [];

  @override
  void initState() {
    super.initState();
    _fetchWorkout();
  }

  Future<void> _fetchWorkout() async {
    try {
      List<Workout> fetchedWorkout = await workoutRepository.getWorkouts();
      setState(() {
        workouts = fetchedWorkout;
      });
    } catch (e) {
      print('Erro ao obter os Treinos: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: workouts.length,
      itemBuilder: (context, index) {
        if (index < workouts.length) {
          final workout = workouts[index];
          return Padding(
            padding: const EdgeInsets.all(4.0),
            child: CustomListCard(
              exerciceName: workout.exercicio?.descricao ?? '',
              onTap: () async => await Get.to(
                () => const WorkoutDetail(),
              ),
              repetition: workout.repeticoes.toString(),
              series: workout.series.toString(),
            ),
          );
        } else {
          return Container();
        }
      },
    );
  }
}

void selectWorkout(int index) {
  print('Treino $index tapped.');
}
