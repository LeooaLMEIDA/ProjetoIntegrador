// ignore_for_file: file_names

import 'package:bullkapp/theme/BullkTheme.dart' show myTheme;
import 'package:bullkapp/training.dart';
import 'package:bullkapp/views/training_list.dart';
import 'package:flutter/material.dart';

import 'home.dart';
import 'login.dart';

class AppWidget extends StatelessWidget {
  const AppWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "BullkApp",
      theme: myTheme,
      initialRoute: '/',
      routes: {
        '/': (context) => const Login(),
        '/home': (context) => const Home(),
        '/training': (context) => const Training(),
        '/traininglist': (context) => const TrainingList(),
      },
    );
  }
}
