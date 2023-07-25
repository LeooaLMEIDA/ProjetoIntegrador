// ignore_for_file: prefer_const_constructors

import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/card.dart';
import 'package:flutter/material.dart';

class TrainingList extends StatelessWidget {
  const TrainingList({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(title: ""),
      body: ListView.builder(
        itemCount: 2,
        itemBuilder: (context, index) {
          return Padding(
            padding: EdgeInsets.all(4),
            child: CustomCard(),
          );
        },
      ),
    );
  }
}
