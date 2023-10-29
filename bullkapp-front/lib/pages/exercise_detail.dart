import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/components/image.dart';
import 'package:bullkapp/data/constants.dart';
import 'package:bullkapp/models/workout.dart';
import 'package:bullkapp/repositories/workout_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/long_card.dart';
import '../components/small_card.dart';
import 'alternative_training_detail.dart';

class ExerciseDetail extends StatefulWidget {
  final int? exerciseId;
  final String description;
  final String orientation;
  final String imgIllustration;
  final int repetitions;
  final int series;
  final String rest;
  final bool hasAlternative;

  const ExerciseDetail(
      {super.key,
      this.exerciseId,
      required this.description,
      required this.orientation,
      required this.imgIllustration,
      required this.repetitions,
      required this.series,
      required this.rest,
      required this.hasAlternative});

  @override
  State<ExerciseDetail> createState() => _ExerciseDetailState();
}

class _ExerciseDetailState extends State<ExerciseDetail> {
  Workout returnWorkout = Workout();
  get _description => widget.description;

  @override
  void initState() {
    super.initState();
    _fetchAlternativeWorkout();
  }

  _fetchAlternativeWorkout() async {
    try {
      Workout workout =
          await WorkoutRepository().getAlternativeWorkout(widget.exerciseId);
      setState(() {
        returnWorkout = workout;
      });
    } catch (e) {
      throw Exception(
          "Ocorreu um erro ao carregar as informações do Treino Alternativo $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    int lengthExercice = _description.length;
    int iSeries = widget.series;
    return Scaffold(
      appBar: const CustomAppBar(),
      body: Padding(
        padding: const EdgeInsets.only(
          top: 8.0,
          left: 8.0,
          right: 8.0,
        ),
        child: SingleChildScrollView(
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    Text(
                      _description,
                      style: TextStyle(
                        fontSize: lengthExercice < 26
                            ? 34
                            : lengthExercice < 32
                                ? 26
                                : 0,
                        fontFamily: 'Voltaire',
                      ),
                      softWrap: true,
                    ),
                  ],
                ),
              ),
              ClipRRect(
                borderRadius: const BorderRadius.all(
                  Radius.circular(20),
                ),
                child: Container(
                  color: Colors.black38,
                  height: 200,
                  child: Stack(
                    children: [
                      LoadImage(
                        url: widget.imgIllustration,
                        defaultImage: defaultImageWorkout,
                        widthImage: 250,
                      ),
                      Positioned(
                        bottom: 1,
                        right: 1,
                        child: Builder(
                          builder: (BuildContext context) {
                            return IconButton(
                              onPressed: () {
                                showDialog(
                                  context: context,
                                  builder: (BuildContext context) {
                                    return AlertDialog(
                                      title: const Text(
                                        'Informações do Exercício',
                                      ),
                                      content: Text(
                                        widget.orientation,
                                      ),
                                      actions: [
                                        TextButton(
                                          onPressed: () {
                                            Get.back();
                                          },
                                          child: const Text('Fechar'),
                                        ),
                                      ],
                                    );
                                  },
                                );
                              },
                              icon: const Icon(
                                Icons.info_outline,
                                size: 25,
                              ),
                            );
                          },
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 8),
                child: Row(
                  children: [
                    CustomSmallCard(
                      mainLabel: "Séries e Repetições",
                      secondLabel: "${widget.series} X ${widget.repetitions}",
                    ),
                    const Spacer(),
                    CustomSmallCard(
                      mainLabel: "Intervalo",
                      secondLabel: widget.rest,
                    ),
                  ],
                ),
              ),
              Column(
                children: [
                  for (int i = 1; i <= iSeries; i++)
                    CustomLongCard(
                      serie: i.toString(),
                      repetition: widget.repetitions.toString(),
                    )
                ],
              ),
              if (widget.hasAlternative != false)
                const Padding(
                  padding: EdgeInsets.all(8.0),
                  child: Row(
                    children: [
                      Text(
                        'Exercício Alternativo',
                        style: TextStyle(
                          fontSize: 22,
                          fontFamily: 'Voltaire',
                        ),
                      ),
                    ],
                  ),
                ),
              //if (returnWorkout.id != -1)
              if (widget.hasAlternative != false)
                Padding(
                  padding: const EdgeInsets.only(top: 4.0),
                  child: Row(
                    children: [
                      AlternativeWorkoutCard(
                        description: returnWorkout.exercicio?.description ?? "",
                        series: returnWorkout.series ?? 0,
                        repetitions: returnWorkout.repeticoes ?? 0,
                        imgIllustration:
                            returnWorkout.exercicio?.imgIlustracao ?? "",
                        rest: returnWorkout.descanso ?? "",
                      ),
                    ],
                  ),
                ),
            ],
          ),
        ),
      ),
      //extendBody: false,
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}

class AlternativeWorkoutCard extends StatelessWidget {
  final String description;
  final String imgIllustration;
  final int repetitions;
  final int series;
  final String rest;

  const AlternativeWorkoutCard({
    super.key,
    required this.series,
    required this.description,
    required this.imgIllustration,
    required this.repetitions,
    required this.rest,
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
        onTap: () async => await Get.to(
          AlternativeWorkoutDetail(
            description: description,
            imgIllustration: imgIllustration,
            repetitions: repetitions,
            rest: rest,
            series: series,
          ),
        ),
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
}
