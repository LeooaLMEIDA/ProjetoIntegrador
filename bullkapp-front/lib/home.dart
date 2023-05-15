// ignore_for_file: avoid_unnecessary_containers, prefer_const_constructors

import 'package:bullkapp/theme/bullkTheme.dart';
import 'package:flutter/material.dart';

import 'Treinos.dart';

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: myTheme,
      home: const Scaffold(
        body: TelaHome(),
      ),
    );
  }
}

class TelaHome extends StatelessWidget {
  const TelaHome({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          Image.asset(
            'images/Logo2.png',
            width: 45,
            height: 45,
            alignment: AlignmentDirectional(-700,0),
          ),
        ],
        toolbarHeight: 60,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(38.0),
            bottomRight: Radius.circular(38.0),
          ),
        ),
      ),
      body: Container(
        alignment: Alignment.topCenter,
        padding: EdgeInsets.all(16),
        //color: Colors.cyan,
        child: Column(
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return Treinos();
                }));
              },
              child: Text("Tela Treinos"),
            ),
          ],
        ),
      ),
    );
  }
}
