// ignore_for_file: prefer_const_constructors, use_key_in_widget_constructors, avoid_unnecessary_containers

import 'package:flutter/material.dart';

class CustomField extends StatelessWidget {
  final String? tip;
  final IconData? icon;
  final TextInputType inputType;
  final TextEditingController controller;

  const CustomField({
    this.icon,
    this.tip,
    required this.inputType,
    required this.controller,
  });

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: controller,
      keyboardType: inputType,
      style: TextStyle(
        color: Colors.white,
        fontSize: 20.0,
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
        suffixIconColor: Colors.white,
        hintStyle: TextStyle(
          color: Color.fromARGB(113, 255, 255, 255),
        ),
        hintText: tip,
      ),
    );
  }
}

class CustomReadOnlyField extends StatelessWidget {
  final String label;
  final TextInputType inputType;
  final String? content;

  const CustomReadOnlyField(
      {super.key, required this.label, required this.inputType, this.content});

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: TextEditingController(text: content),
      readOnly: true,
      enabled: false,
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
