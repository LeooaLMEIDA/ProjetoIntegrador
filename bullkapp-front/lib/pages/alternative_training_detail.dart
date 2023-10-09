import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/components/image.dart';
import 'package:bullkapp/data/constants.dart';
import 'package:flutter/material.dart';
import '../components/long_card.dart';
import '../components/small_card.dart';
import 'package:get/get.dart';

class AlternativeWorkoutDetail extends StatelessWidget {
  final String description;
  final String imgIllustration;
  final int repetitions;
  final int series;
  final String rest;

  const AlternativeWorkoutDetail(
      {super.key,
      required this.description,
      required this.imgIllustration,
      required this.repetitions,
      required this.series,
      required this.rest});

  @override
  Widget build(BuildContext context) {
    int iSeries = series;
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
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    Text(
                      description,
                      style: const TextStyle(
                        fontSize: 34,
                        fontFamily: 'Voltaire',
                      ),
                    ),
                  ],
                ),
              ),
              ClipRRect(
                borderRadius: const BorderRadius.all(Radius.circular(20)),
                child: Container(
                  color: Colors.black38,
                  height: 200,
                  child: Stack(
                    children: [
                      const LoadImage(
                        url: "",
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
                                          'Informações do Exercício'),
                                      content: const Text('Nome Do Exercício:'),
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
                              icon: const Icon(Icons.info_outline, size: 25),
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
                      secondLabel: "$series X $repetitions",
                    ),
                    const Spacer(),
                    CustomSmallCard(
                      mainLabel: "Intervalo",
                      secondLabel: rest,
                    ),
                  ],
                ),
              ),
              Column(
                children: [
                  for (int i = 1; i <= iSeries; i++)
                    CustomLongCard(
                      serie: i.toString(),
                      repetition: repetitions.toString(),
                    ),
                ],
              )
            ],
          ),
        ),
      ),
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}
