import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';
import '../components/long_card.dart';
import '../components/small_card.dart';
import '../components/youtube_player.dart';

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
                  child: Image.asset("images/flexao.gif"),
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
