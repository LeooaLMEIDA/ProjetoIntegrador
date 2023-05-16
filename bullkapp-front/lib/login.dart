// ignore_for_file: prefer_const_constructors, avoid_unnecessary_containers, prefer_const_literals_to_create_immutables, unused_element

import 'package:bullkapp/home.dart';
import 'package:flutter/material.dart';

import 'components.dart';

class Login extends StatefulWidget {
  const Login({super.key});

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 20,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(38.0),
            bottomRight: Radius.circular(38.0),
          ),
        ),
      ),
      body: Container(
        alignment: Alignment.topCenter,
        //padding: EdgeInsets.all(16),
        child: SingleChildScrollView(
          child: Column(
            children: [
              Container(
                padding: EdgeInsets.all(16),
                child: Image.asset(
                  'images/Logo2.png',
                  alignment: Alignment.bottomCenter,
                  width: 220,
                  height: 250,
                ),
              ),
              Container(
                child: Column(
                  children: [
                    CampoChar(
                        dica: "E-mail",
                        rotulo: "",
                        icon: Icons.account_circle),
                    CampoPassword(
                      dica: "Senha",
                      rotulo: "",
                      icon: Icons.password,
                    ),
                    ElevatedButton(
                      child: const Text('Confirmar'),
                      onPressed: () {
                        Navigator.push(context,
                            MaterialPageRoute(builder: (context) {
                          return Home();
                        }));
                      },
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

void _verificaLogin(BuildContext context) {
  @override
  String toString() {
    return 'Login OK'.toString();
  }
}
