// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors, avoid_unnecessary_containers

import 'package:flutter/material.dart';

String email = "";
String senha = "";

class CampoChar extends StatelessWidget {
  final String? rotulo;
  final String? dica;
  final IconData? icon;

  const CampoChar({this.rotulo, this.dica, this.icon});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          TextField(
            onChanged: (inputEmail) {
              email = inputEmail;
            },
            keyboardType: TextInputType.emailAddress,
            style: TextStyle(
              color: Colors.white,
              fontSize: 24.0,
            ),
            decoration: InputDecoration(
              filled: true,
              fillColor: Color.fromARGB(250, 1, 30, 62),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.all(Radius.circular(20)),
              ),
              suffixIcon: icon != null ? Icon(icon) : null,
              suffixIconColor: Colors.white,
              hintStyle: TextStyle(
                color: Color.fromARGB(255, 255, 255, 255),
              ),
              //labelText: rotulo,
              hintText: dica,
            ),
          ),
        ],
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
    return Container(
      child: TextField(
        onChanged: (inputPassword) {
          senha = inputPassword;
        },
        style: TextStyle(
          color: Colors.white,
          fontSize: 24.0,
        ),
        obscureText: true,
        decoration: InputDecoration(
          filled: true,
          fillColor: Color.fromARGB(250, 1, 30, 62),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(20),
            ),
          ),
          suffixIcon: icon != null ? Icon(icon) : null,
          suffixIconColor: Colors.white,
          hintStyle: TextStyle(
            color: Color.fromARGB(255, 255, 255, 255),
          ),
          //labelText: rotulo,
          hintText: dica,
        ),
      ),
    );
  }
}
