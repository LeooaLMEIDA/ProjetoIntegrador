// ignore_for_file: file_names

import 'package:bullkapp/theme/BullkTheme.dart' show myTheme;
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
      },
    );
  }
}
