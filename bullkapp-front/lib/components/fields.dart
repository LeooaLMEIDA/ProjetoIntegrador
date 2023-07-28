// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors, avoid_unnecessary_containers

import 'package:flutter/material.dart';

String email = "";
String senha = "";

class CustomField extends StatelessWidget {
  final String? label;
  final String? tip;
  final IconData? icon;
  final TextInputType inputType;

  const CustomField({this.icon, this.label, this.tip, required this.inputType});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          TextField(
            onChanged: (inputEmail) {
              email = inputEmail;
            },
            keyboardType: inputType,
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
              hintText: tip,
            ),
          ),
        ],
      ),
    );
  }
}

class CustomReadOnlyField extends StatelessWidget {
  final String label;
  final TextInputType inputType;

  const CustomReadOnlyField(
      {super.key, required this.label, required this.inputType});

  @override
  Widget build(BuildContext context) {
    return TextField(
      keyboardType: inputType,
      style: TextStyle(
        color: Colors.black,
        fontSize: 16.0,
      ),
      decoration: InputDecoration(
        labelText: label,
        labelStyle: TextStyle(
          color: Colors.black,
        ),
      ),
    );
  }
}
