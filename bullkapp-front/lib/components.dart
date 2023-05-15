// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors

import 'package:flutter/material.dart';

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
          filled: true,
          fillColor: Color.fromARGB(250, 1, 30, 62),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(20)),
          ),
          suffixIcon: icon != null ? Icon(icon) : null,
          labelStyle: TextStyle(
            fontSize: 20,
            color: Color.fromARGB(249, 255, 255, 255),
          ),
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
          filled: true,
          fillColor: Color.fromARGB(250, 1, 30, 62),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(20),
            ),
          ),
          suffixIcon: icon != null ? Icon(icon) : null,
          labelStyle: TextStyle(
            fontSize: 20,
            color: Color.fromARGB(249, 255, 255, 255),
          ),
          labelText: rotulo,
          hintText: dica,
        ),
        keyboardType: TextInputType.visiblePassword,
      ),
    );
  }
}
