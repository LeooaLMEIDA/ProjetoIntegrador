// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:bullkapp/components/appbar.dart';
import 'package:flutter/material.dart';
import '../components/long_card.dart';
import '../components/small_card.dart';
import '../components/youtube_player.dart';

class AlternativeTrainingDetail extends StatelessWidget {
  const AlternativeTrainingDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(title: ""),
      body: Padding(
        padding: const EdgeInsets.only(left: 8.0, right: 8.0),
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
              )
            ],
          ),
        ),
      ),
    );
  }
}
