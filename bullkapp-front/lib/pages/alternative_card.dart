import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../repositories/exercise_repository.dart';
import 'alternative_training_detail.dart';

class AlternativeWorkoutCard extends StatelessWidget {
  final String description;
  final String imgIllustration;
  final int repetitions;
  final int series;
  final String rest;
  final String peso;

  const AlternativeWorkoutCard({
    super.key,
    required this.series,
    required this.description,
    required this.imgIllustration,
    required this.repetitions,
    required this.rest,
    required this.peso,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(30),
      ),
      clipBehavior: Clip.hardEdge,
      borderOnForeground: true,
      color: const Color.fromARGB(250, 1, 30, 62),
      child: InkWell(
        onTap: () async {
          final exerciseGif = await _getGifExercise(3);
          return await Get.to(
            AlternativeWorkoutDetail(
              description: description,
              imgIllustration: exerciseGif ?? "",
              repetitions: repetitions,
              rest: rest,
              series: series,
              peso: double.parse(peso),
            ),
          );
        },
        splashColor: const Color.fromARGB(250, 1, 30, 62).withAlpha(255),
        child: SizedBox(
          width: MediaQuery.of(context).size.width - 25,
          height: 46,
          child: Stack(
            children: [
              Positioned(
                bottom: 10,
                left: 10,
                child: Text(
                  description,
                  style: const TextStyle(fontSize: 20, color: Colors.white),
                ),
              ),
              Positioned(
                bottom: 10,
                right: 12,
                child: Text(
                  '$series X $repetitions',
                  style: const TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Future<String?> _getGifExercise(int id) async {
    try {
      ExerciseRepository exerciseRepository = ExerciseRepository();
      final exerciseGif = await exerciseRepository.getGif(id);
      exerciseGif?.replaceAll(RegExp(r'\s+'), '');
      return exerciseGif?.split(',').last;
    } catch (e) {
      throw Exception(e);
    }
  }
}
