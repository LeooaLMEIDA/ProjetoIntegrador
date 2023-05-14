// ignore: unused_import
// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors, avoid_print, dead_code, unused_element

import 'package:bullkapp/bullkTheme.dart';
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
      home: Login(),
    );
  }
} 

void _verificaLogin(BuildContext context) {
  @override
  String toString() {
    return 'Login OK'.toString();
  }
}

class CampoChar extends StatelessWidget {
  final String? rotulo;
  final String? dica;
  final IconData? icon;

  const CampoChar({this.rotulo, this.dica, this.icon});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextField(
        style: TextStyle(
          fontSize: 24.0,
        ),
        decoration: InputDecoration(
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(20)),
          ),
          suffixIcon: icon != null ? Icon(icon) : null,
          labelText: rotulo,
          hintText: dica,
        ),
        keyboardType: TextInputType.text,
      ),
    );
  }
}

class CampoPassword extends StatelessWidget {
  final String? rotulo;
  final String? dica;
  final IconData? icon;

  const CampoPassword({this.rotulo, this.dica, this.icon});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextField(
        style: TextStyle(
          fontSize: 24.0,
        ),
        decoration: InputDecoration(
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(20),
            ),
          ),
          suffixIcon: icon != null ? Icon(icon) : null,
          labelText: rotulo,
          hintText: dica,
        ),
        keyboardType: TextInputType.visiblePassword,
      ),
    );
  }
}
