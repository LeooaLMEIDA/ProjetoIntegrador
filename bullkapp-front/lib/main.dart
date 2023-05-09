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

/*
class Login extends StatelessWidget {
  const Login({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 10,
        centerTitle: true,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(38.0),
            bottomRight: Radius.circular(38.0),
          ),
        ),
      ),
      body: 
      SizedBox(
        child: SingleChildScrollView(
          child: Column(
            children: [
              Image.asset(
                'images/Logo2.png',
                alignment: Alignment.bottomCenter,
                width: 220,
                height: 250,
              ),
              Editor(
                  dica: "bullkapp@bullkapp.com.br",
                  rotulo: "E-mail",
                  icon: Icons.account_circle),
              Editor(
                dica: "Senha",
                rotulo: "Senha",
                icon: Icons.password,
              ),
              ElevatedButton(
                child: const Text('Entrar'),
                onPressed: () => _verificaLogin(context),
              ),
            ],
          ),
        ),
      ),
    );
  }
} */

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
