import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/components/image.dart';
import 'package:flutter/material.dart';
import '../components/long_card.dart';
import '../components/small_card.dart';

class AlternativeWorkoutDetail extends StatelessWidget {
  const AlternativeWorkoutDetail({super.key});

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
                      'Tríceps Supinado',
                      style: TextStyle(fontSize: 34, fontFamily: 'Voltaire'),
                    ),
                  ],
                ),
              ),
              ClipRRect(
                borderRadius: const BorderRadius.all(Radius.circular(20)),
                child: Container(
                  color: Colors.black38,
                  height: 200,
                  // child: const CustomYoutubePlayer(
                  //   videoUrl: 'https://youtu.be/XkEA4xT34jg',
                  child: Stack(
                    children: [
                      LoadImage(
                          url:
                              "https://www.seiu1000.org/sites/main/files/main-images/camera_lense_0.jpeg",
                          defaultImage: "images/exercicios/flexao.gif"),
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
                                            Navigator.of(context)
                                                .pop(); // Fechar o modal
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
              )
            ],
          ),
        ),
      ),
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}
