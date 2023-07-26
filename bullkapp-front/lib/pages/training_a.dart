// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/pages/training.dart';
import 'package:bullkapp/views/training_list.dart';
import 'package:flutter/material.dart';

class TrainingA extends StatelessWidget {
  const TrainingA({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          Container(
            width: 400,
            height: 220,
            color: Colors.white,
            child: TrainingScreen(
              showBottomBar: false,
            ),
          ),
          Expanded(
            child: Container(
              color: Colors.white,
              child: TrainingList(),
            ),
          ),
        ],
      ),
      bottomNavigationBar: CustomBottomAppBar(),
    );
  }
}
