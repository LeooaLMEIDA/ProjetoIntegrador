import 'package:bullkapp/theme/bullkTheme.dart';
import 'package:flutter/material.dart';

import 'login.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "BullkApp",
      theme: myTheme,
      home: const Login(),
    );
  }
} 
