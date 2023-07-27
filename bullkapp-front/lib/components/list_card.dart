// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, use_key_in_widget_constructors, prefer_const_constructors_in_immutables

import 'package:flutter/material.dart';

class CustomListCard extends StatelessWidget {
  final VoidCallback onTap;
  final String exerciceName;
  final String series;
  final String repetition;

  CustomListCard(
      {required this.onTap,
      required this.exerciceName,
      required this.series,
      required this.repetition});

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(16),
      ),
      clipBehavior: Clip.hardEdge,
      borderOnForeground: true,
      color: Color.fromARGB(250, 1, 30, 62),
      child: InkWell(
        splashColor: Color.fromARGB(250, 1, 30, 62).withAlpha(255),
        onTap: onTap,
        child: SizedBox(
          width: 300,
          height: 120,
          child: Stack(
            children: [
              Positioned(
                top: 20,
                left: 20,
                child: Text(
                  exerciceName,
                  style: TextStyle(fontSize: 22, color: Colors.white),
                ),
              ),
              Positioned(
                bottom: 10,
                right: 10,
                child: Text(
                  '$series X $repetition',
                  style: TextStyle(fontSize: 16, color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
