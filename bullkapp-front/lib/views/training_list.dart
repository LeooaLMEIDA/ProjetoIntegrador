// ignore_for_file: prefer_const_constructors
import 'package:bullkapp/components/card.dart';
import 'package:flutter/material.dart';

class TrainingList extends StatelessWidget {
  const TrainingList({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: 10,
      itemBuilder: (context, index) {
        return Padding(
          padding: const EdgeInsets.all(4.0),
          child: CustomCard(
            exerciceName: 'Flexão',
            onTap: () => selectTraining(index),
            repetition: '2',
            series: '15',
          ),
        );
      },
    );
  }
}

void selectTraining(int index) {
  print('Treino $index tapped.');
}
