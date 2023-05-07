// ignore: unused_import
// ignore_for_file: use_key_in_widget_constructors, prefer_const_constructors

import 'package:bullkapp/bullkTheme.dart';
import 'package:flutter/material.dart';

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
      home: const Scaffold(
        body: Teste(),
      ),
    );
  }
}

class Teste extends StatelessWidget {
  const Teste({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(38.0),
            bottomRight: Radius.circular(38.0),
          ),
        ),
      ),
      body: Column(
        children: [
          Image.asset(
            'images/Logo.png',
            alignment: Alignment.center,
            width: 300,
            height: 320,
          ),
          Editor(dica: "bullkapp@bullkapp.com.br", rotulo: "E-mail"),
          Editor(dica: "Senha", rotulo: "Senha")
        ],
      ),
      
    );
  }
}

class Editor extends StatelessWidget {
  final String? rotulo;
  final String? dica;
  final IconData? icon;

  const Editor({this.rotulo, this.dica, this.icon});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextField(
        style: TextStyle(
          fontSize: 24.0,
        ),
        decoration: InputDecoration(
          icon: icon != null ? Icon(icon) : null,
          labelText: rotulo,
          hintText: dica,
        ),
        keyboardType: TextInputType.text,
      ),
    );
  }
}