// ignore_for_file: file_names
// import 'package:bullkapp/components/appbar.dart';
// import 'package:bullkapp/components/bottombar.dart';
// import 'package:bullkapp/components/image.dart';
// import 'package:bullkapp/models/workout.dart';
// import 'package:bullkapp/repositories/workout_repository.dart';
// import 'package:flutter/material.dart';
// import 'package:get/get.dart';
// import '../components/long_card.dart';
// import '../components/small_card.dart';
// import '../data/infoWorkouts.dart';
// import 'alternative_training_detail.dart';

// class ExerciseDetail extends StatefulWidget {
//   final int? exerciseId;
//   const ExerciseDetail({super.key, this.exerciseId});

//   @override
//   State<ExerciseDetail> createState() => _ExerciseDetailState();
// }

// class _ExerciseDetailState extends State<ExerciseDetail> {
//   Workout returnWorkout = Workout();

//   @override
//   void initState() {
//     super.initState();
//     _fetchWorkout();
//   }

//   _fetchWorkout() async {
//     try {
//       Workout workout = await WorkoutRepository().getWorkout(widget.exerciseId);
//       setState(() {
//         returnWorkout = workout;
//       });
//     } catch (e) {
//       throw Exception(
//           "Ocorreu um erro ao carregar as informações do Usuário $e");
//     }
//   }

//   @override
//   Widget build(BuildContext context) {
//     int iSeries = returnWorkout.series ?? 0;
//     return Scaffold(
//       appBar: const CustomAppBar(title: ""),
//       body: Padding(
//         padding: const EdgeInsets.only(
//           top: 8.0,
//           left: 8.0,
//           right: 8.0,
//         ),
//         child: SingleChildScrollView(
//           child: Column(
//             children: [
//               Padding(
//                 padding: const EdgeInsets.all(8.0),
//                 child: Row(
//                   children: [
//                     Text(
//                       returnWorkout.exercicio?.descricao ?? "",
//                       style:
//                           const TextStyle(fontSize: 34, fontFamily: 'Voltaire'),
//                     ),
//                   ],
//                 ),
//               ),
//               ClipRRect(
//                 borderRadius: const BorderRadius.all(Radius.circular(20)),
//                 child: Container(
//                   color: Colors.black38,
//                   height: 200,
//                   child: Stack(
//                     children: [
//                       LoadImage(
//                           url: returnWorkout.exercicio?.imgIlustracao ?? "",
//                           defaultImage: "images/exercicios/legpress.gif"),
//                       Positioned(
//                         bottom: 1,
//                         right: 1,
//                         child: Builder(
//                           builder: (BuildContext context) {
//                             return IconButton(
//                               onPressed: () {
//                                 showDialog(
//                                   context: context,
//                                   builder: (BuildContext context) {
//                                     return AlertDialog(
//                                       title: const Text(
//                                           'Informações do Exercício'),
//                                       content: Text(detailFlexao),
//                                       actions: [
//                                         TextButton(
//                                           onPressed: () {
//                                             Navigator.of(context)
//                                                 .pop(); // Fechar o modal
//                                           },
//                                           child: const Text('Fechar'),
//                                         ),
//                                       ],
//                                     );
//                                   },
//                                 );
//                               },
//                               icon: const Icon(Icons.info_outline, size: 25),
//                             );
//                           },
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               ),
//               Padding(
//                 padding: const EdgeInsets.only(top: 8),
//                 child: Row(
//                   children: [
//                     CustomSmallCard(
//                       mainLabel: "Repetições e Séries",
//                       secondLabel:
//                           "${returnWorkout.repeticoes} X ${returnWorkout.series}",
//                     ),
//                     const Spacer(),
//                     CustomSmallCard(
//                       mainLabel: "Intervalo",
//                       secondLabel: "${returnWorkout.descanso}",
//                     ),
//                   ],
//                 ),
//               ),
//               Column(
//                 children: [
//                   for (int i = 1; i <= iSeries; i++)
//                     CustomLongCard(
//                       serie: i.toString(),
//                       repetition: returnWorkout.repeticoes.toString(),
//                     )
//                 ],
//               ),
//               const Padding(
//                 padding: EdgeInsets.all(8.0),
//                 child: Row(
//                   children: [
//                     Text(
//                       'Exercício Alternativo',
//                       style: TextStyle(fontSize: 22, fontFamily: 'Voltaire'),
//                     ),
//                   ],
//                 ),
//               ),
//               const Padding(
//                 padding: EdgeInsets.only(top: 4.0),
//                 child: Row(
//                   children: [
//                     AlternativeWorkoutCard(
//                       workoutName: 'Tríceps Supinado',
//                       series: '2',
//                       repetition: '15',
//                     ),
//                   ],
//                 ),
//               ),
//             ],
//           ),
//         ),
//       ),
//       //extendBody: false,
//       bottomNavigationBar: const CustomBottomAppBar(),
//     );
//   }
// }

// class AlternativeWorkoutCard extends StatelessWidget {
//   final String workoutName;
//   final String series;
//   final String repetition;

//   const AlternativeWorkoutCard({
//     super.key,
//     required this.workoutName,
//     required this.series,
//     required this.repetition,
//   });

//   @override
//   Widget build(BuildContext context) {
//     return Card(
//       shape: RoundedRectangleBorder(
//         borderRadius: BorderRadius.circular(30),
//       ),
//       clipBehavior: Clip.hardEdge,
//       borderOnForeground: true,
//       color: const Color.fromARGB(250, 1, 30, 62),
//       child: InkWell(
//         onTap: () async => await Get.to(
//           const AlternativeWorkoutDetail(),
//         ),
//         splashColor: const Color.fromARGB(250, 1, 30, 62).withAlpha(255),
//         child: SizedBox(
//           width: MediaQuery.of(context).size.width - 25,
//           height: 46,
//           child: Stack(
//             children: [
//               Positioned(
//                 bottom: 10,
//                 left: 10,
//                 child: Text(
//                   workoutName,
//                   style: const TextStyle(fontSize: 20, color: Colors.white),
//                 ),
//               ),
//               Positioned(
//                 bottom: 10,
//                 right: 12,
//                 child: Text(
//                   '$series X $repetition',
//                   style: const TextStyle(fontSize: 18, color: Colors.white),
//                 ),
//               ),
//             ],
//           ),
//         ),
//       ),
//     );
//   }
// }
