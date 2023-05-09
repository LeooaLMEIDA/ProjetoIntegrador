// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

import 'main.dart';

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
        toolbarHeight: 10,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(38.0),
            bottomRight: Radius.circular(38.0),
          ),
        ),
      ),
      body: SizedBox(
        child: SingleChildScrollView(
          child: Column(
            children: [
              Image.asset(
                'images/Logo2.png',
                alignment: Alignment.bottomCenter,
                width: 220,
                height: 250,
              ),
              CampoChar(
                  dica: "bullkapp@bullkapp.com.br",
                  rotulo: "E-mail",
                  icon: Icons.account_circle),
              CampoPassword(
                dica: "Senha",
                rotulo: "Senha",
                icon: Icons.password,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
