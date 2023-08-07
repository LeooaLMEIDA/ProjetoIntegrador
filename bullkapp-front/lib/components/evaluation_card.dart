// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, use_key_in_widget_constructors, prefer_const_constructors_in_immutables

import 'package:flutter/material.dart';

class EvaluationCard extends StatelessWidget {
  final String mainLabel;

  EvaluationCard({required this.mainLabel});

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(16),
      ),
      clipBehavior: Clip.hardEdge,
      borderOnForeground: true,
      color: Color.fromARGB(160, 4, 53, 101),
      child: InkWell(
        splashColor: Color.fromARGB(160, 4, 53, 101).withAlpha(255),
        child: SizedBox(
          width: MediaQuery.of(context).size.width - 220,
          height: 160,
          child: Center(
            child: Text(
              mainLabel,
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
                color: Colors.white,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
