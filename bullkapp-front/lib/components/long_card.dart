// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, use_key_in_widget_constructors, prefer_const_constructors_in_immutables

import 'package:flutter/material.dart';

class CustomLongCard extends StatelessWidget {
  final String serie;
  final String repetition;
  final String peso;

  CustomLongCard({
    required this.serie,
    required this.repetition,
    required this.peso,
  });

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
        child: SizedBox(
          width: MediaQuery.of(context).size.width - 25,
          height: 50,
          child: Stack(
            children: [
              Positioned(
                top: 12,
                left: 10,
                child: Text(
                  '$serie ª X $repetition - $peso KG',
                  style: TextStyle(fontSize: 22, color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}