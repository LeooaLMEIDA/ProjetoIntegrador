// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:bullkapp/components/appbar.dart';

import 'package:flutter/material.dart';

import '../components/long_card.dart';
import '../components/small_card.dart';
import '../components/youtube_player.dart';

class TrainingDetail extends StatelessWidget {
  const TrainingDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(title: ""),
      body: Padding(
        padding: EdgeInsets.all(8.0),
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Row(
                children: [
                  Text(
                    'Flexão',
                    style: TextStyle(fontSize: 20),
                  ),
                ],
              ),
            ),
            ClipRRect(
              borderRadius: BorderRadius.all(Radius.circular(20)),
              child: Container(
                color: Colors.black38,
                height: 200,
                child: CustomYoutubePlayer(
                  videoUrl: 'https://youtu.be/XkEA4xT34jg',
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 8),
              child: Row(
                children: [
                  CustomSmallCard(
                    repetition: '2',
                    series: '15',
                  ),
                  Spacer(),
                  CustomSmallCard(
                    repetition: '2',
                    series: '15',
                  )
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 8),
              child: Row(
                children: [
                  Column(
                    children: [
                      CustomLongCard(serie: '1', repetition: '15'),
                      CustomLongCard(serie: '1', repetition: '15'),
                    ],
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
