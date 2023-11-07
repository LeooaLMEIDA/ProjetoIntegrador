import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/repositories/exercise_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../components/bottombar.dart';
import '../components/list_card.dart';
import '../models/workout.dart';
import '../repositories/workout_repository.dart';
import 'exercise_detail.dart';
import 'home.dart';

final workoutRepository = WorkoutRepository();

class WorkoutScreen extends StatefulWidget {
  final bool showBottomBar;
  final String? activeTraining;
  const WorkoutScreen({
    super.key,
    required this.showBottomBar,
    required this.activeTraining,
  });

  @override
  State<WorkoutScreen> createState() => _WorkoutScreenState();
}

class _WorkoutScreenState extends State<WorkoutScreen> {
  bool _isLoading = false;
  bool hasAlternative = false;
  List<Workout> workouts = [];
  Workout alternativeWorkout = Workout();

  @override
  void initState() {
    super.initState();
    _fetchWorkout();
  }

  final Map<String, String> _trainingIcons = {
    "A": "images/TreinoA.png",
    "B": "images/TreinoB.png",
    "C": "images/TreinoC.png",
  };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(),
      body: Container(
        color: Colors.white,
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
                      child: widget.activeTraining == entry.key
                          ? Image.asset(
                              iconPath,
                              width: 92,
                            )
                          : Opacity(
                              opacity: 0.5,
                              child: Image.asset(
                                iconPath,
                                width: 92,
                              ),
                            ),
                      onTap: () {
                        if (trainingCode == widget.activeTraining) {
                          setState(() {});
                          _fetchWorkout();
                        } else {
                          if (!Get.isSnackbarOpen) {
                            Get.snackbar(
                              'Atenção',
                              'Para trocar o Treino, precisa concluir o que está ativo',
                              snackPosition: SnackPosition.BOTTOM,
                              backgroundColor: Colors.red,
                              colorText: Colors.white,
                              icon: const Icon(Icons.warning),
                              duration: const Duration(seconds: 6),
                            );
                          }
                        }
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
            Container(
              color: const Color.fromARGB(255, 255, 255, 255),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: () {
                      Get.defaultDialog(
                        title: 'Confirmação de Saída',
                        content: const Text(
                            'Você realmente deseja sair da aplicação?'),
                        textConfirm: 'Sim',
                        textCancel: 'Não',
                        confirmTextColor:
                            const Color.fromARGB(255, 255, 255, 255),
                        buttonColor: Colors.red,
                        cancelTextColor: const Color.fromARGB(255, 0, 0, 0),
                        onConfirm: () {
                          setState(() {
                            _setProximoTreino(widget.activeTraining!);
                          });
                          Get.snackbar(
                            'SUCESSO',
                            'Treino finalizado com sucesso!',
                            icon: const Icon(Icons.done_outline_rounded),
                            snackPosition: SnackPosition.BOTTOM,
                            backgroundColor: Colors.green,
                            duration: const Duration(seconds: 5),
                            colorText: Colors.white,
                          );
                          Get.to(() => const HomeScreen());
                        },
                      );
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.red,
                      minimumSize: const Size(150, 50),
                    ),
                    child: const Text(
                      'Concluir Treino',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                      ),
                    ),
                  ),
                ],
              ),
            )
          ],
        ),
      ),
      extendBody: false,
      bottomNavigationBar:
          widget.showBottomBar ? const CustomBottomAppBar() : null,
    );
  }

  void finishTraining() {
    Get.defaultDialog(
      title: 'Confirmação de Saída',
      content: const Text('Você realmente deseja sair da aplicação?'),
      textConfirm: 'Sim',
      textCancel: 'Não',
      confirmTextColor: const Color.fromARGB(255, 255, 255, 255),
      buttonColor: Colors.red,
      cancelTextColor: const Color.fromARGB(255, 0, 0, 0),
      onConfirm: () {
        setState(() {
          _setProximoTreino(widget.activeTraining!);
        });
        // Get.back();
      },
    );
  }

  void _setProximoTreino(String training) async {
    final prefs = await SharedPreferences.getInstance();
    switch (training) {
      case "A":
        prefs.setString("treino", "B");
        break;
      case "B":
        prefs.setString("treino", "C");
        break;
      case "C":
        prefs.setString("treino", "A");
        break;
      default:
        break;
    }
  }

  Widget _getSelectedList() {
    return ListView.builder(
      itemCount: workouts.length,
      itemBuilder: (context, index) {
        final workout = workouts[index];
        return Padding(
          padding: const EdgeInsets.all(4.0),
          child: CustomListCard(
            exerciceName: workout.exercicio?.description ?? '',
            equipment:
                "Aparelho: ${workout.exercicio?.aparelho?.descricao ?? ''}",
            onTap: () async {
              await _fetchAlternativeWorkout(workout.id ?? 0);

              if (alternativeWorkout.id == -1) {
                hasAlternative = false;
              } else {
                hasAlternative = true;
              }

              final exerciseGif =
                  await _getGifExercise(workout.exercicio?.id ?? 0);

              await Get.to(
                () => ExerciseDetail(
                  exerciseId: workout.id ?? 0,
                  description: workout.exercicio?.description ?? '',
                  orientation: workout.exercicio?.orientation ?? '',
                  imgIllustration: exerciseGif ?? "",
                  repetitions: workout.repeticoes ?? 0,
                  rest: workout.descanso ?? "",
                  series: workout.series ?? 0,
                  hasAlternative: hasAlternative,
                  peso: double.parse(workout.peso.toString()),
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
      List<Workout> fetchedWorkout = await workoutRepository
          .getWorkoutsByWorkoutCode(widget.activeTraining ?? "");
      setState(() {
        workouts = fetchedWorkout;
      });
    } catch (e) {
      if (!Get.isSnackbarOpen) {
        Get.snackbar(
          'ERRO',
          'Erro ao obter os Treinos!',
          snackPosition: SnackPosition.BOTTOM,
          backgroundColor: Colors.red,
          colorText: Colors.white,
        );
      }
    }
    setState(() {
      _isLoading = false;
    });
  }

  Future<void> _fetchAlternativeWorkout(int id) async {
    try {
      Workout workout = await WorkoutRepository().getAlternativeWorkout(id);
      setState(() {
        alternativeWorkout = workout;
      });
    } catch (e) {
      throw Exception(
        "Ocorreu um erro ao carregar as informações do Treino Alternativo $e",
      );
    }
  }

  Future<String?> _getGifExercise(int id) async {
    try {
      ExerciseRepository exerciseRepository = ExerciseRepository();
      final exerciseGif = await exerciseRepository.getGif(id);
      exerciseGif?.replaceAll(RegExp(r'\s+'), '');
      exerciseGif?.split(',').last;
      return exerciseGif;
    } catch (e) {
      throw Exception(e);
    }
  }
}
