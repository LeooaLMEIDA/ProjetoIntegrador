// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/pages/workout.dart';
import 'package:bullkapp/views/workout_list.dart';
import 'package:flutter/material.dart';

class WorkoutA extends StatelessWidget {
  const WorkoutA({super.key});

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
            child: WorkoutScreen(
              showBottomBar: false,
            ),
          ),
          Expanded(
            child: Container(
              color: Colors.white,
              child: WorkoutList(),
            ),
          ),
        ],
      ),
      bottomNavigationBar: CustomBottomAppBar(),
    );
  }
}