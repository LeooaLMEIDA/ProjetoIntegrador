// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/long_card.dart';
import '../components/small_card.dart';
import '../components/youtube_player.dart';
import 'alternative_training_detail.dart';

class ExerciseDetail extends StatelessWidget {
  const ExerciseDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(title: ""),
      body: Padding(
        padding: const EdgeInsets.only(
          top: 8.0,
          left: 8.0,
          right: 8.0,
        ),
        child: SingleChildScrollView(
          child: Column(
            children: [
              const Padding(
                padding: EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    Text(
                      'Flexão',
                      style: TextStyle(fontSize: 34, fontFamily: 'Voltaire'),
                    ),
                  ],
                ),
              ),
              ClipRRect(
                borderRadius: BorderRadius.all(Radius.circular(20)),
                child: Container(
                  color: Colors.black38,
                  height: 200,
                  child: const CustomYoutubePlayer(
                    videoUrl: 'https://youtu.be/XkEA4xT34jg',
                  ),
                ),
              ),
              Padding(
                padding: EdgeInsets.only(top: 8),
                child: Row(
                  children: [
                    CustomSmallCard(
                      mainLabel: "Repetições e Séries",
                      secondLabel: "15 X 2",
                    ),
                    const Spacer(),
                    CustomSmallCard(
                      mainLabel: "Intervalo",
                      secondLabel: "30 Segundos",
                    ),
                  ],
                ),
              ),
              Column(
                children: [
                  CustomLongCard(serie: '1', repetition: '15'),
                  CustomLongCard(serie: '2', repetition: '15'),
                  CustomLongCard(serie: '3', repetition: '15'),
                  CustomLongCard(serie: '3', repetition: '15'),
                ],
              ),
              Padding(
                padding: EdgeInsets.all(8.0),
                child: Row(
                  children: const [
                    Text(
                      'Exercício Alternativo',
                      style: TextStyle(fontSize: 22, fontFamily: 'Voltaire'),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: EdgeInsets.only(top: 4.0),
                child: Row(
                  children: [
                    AlternativeWorkoutCard(
                      workoutName: 'Tríceps Supinado',
                      series: '2',
                      repetition: '15',
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
      //extendBody: false,
      bottomNavigationBar: CustomBottomAppBar(),
    );
  }
}

class AlternativeWorkoutCard extends StatelessWidget {
  final String workoutName;
  final String series;
  final String repetition;

  const AlternativeWorkoutCard({
    super.key,
    required this.workoutName,
    required this.series,
    required this.repetition,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(30),
      ),
      clipBehavior: Clip.hardEdge,
      borderOnForeground: true,
      color: Color.fromARGB(250, 1, 30, 62),
      child: InkWell(
        onTap: () async => await Get.to(
          AlternativeWorkoutDetail(),
        ),
        splashColor: Color.fromARGB(250, 1, 30, 62).withAlpha(255),
        child: SizedBox(
          width: MediaQuery.of(context).size.width - 25,
          height: 46,
          child: Stack(
            children: [
              Positioned(
                bottom: 10,
                left: 10,
                child: Text(
                  workoutName,
                  style: TextStyle(fontSize: 20, color: Colors.white),
                ),
              ),
              Positioned(
                bottom: 10,
                right: 12,
                child: Text(
                  '$series X $repetition',
                  style: TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
