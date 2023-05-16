// ignore_for_file: file_names, use_key_in_widget_constructors

import 'package:flutter/material.dart';

class ListaTreinos extends StatelessWidget {
  //const ListaTreinos({super.key});

  final listaTreinos = [
    
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Image.asset('images/Logo3.png'),
        ),
        toolbarHeight: 60,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(38.0),
            bottomRight: Radius.circular(38.0),
          ),
        ),
      ),
    );
  }
}