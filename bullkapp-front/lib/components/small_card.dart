// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, use_key_in_widget_constructors, prefer_const_constructors_in_immutables

import 'package:flutter/material.dart';

class CustomSmallCard extends StatelessWidget {
  final String mainLabel;
  final String secondLabel;

  CustomSmallCard({required this.mainLabel, required this.secondLabel});

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
          width: 160,
          height: 70,
          child: Stack(
            children: [
              Positioned(
                top: 10,
                left: 10,
                child: Text(
                  mainLabel,
                  style: TextStyle(fontSize: 16, color: Colors.white),
                ),
              ),
              Positioned(
                bottom: 10,
                right: 10,
                child: Text(
                  secondLabel,
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
