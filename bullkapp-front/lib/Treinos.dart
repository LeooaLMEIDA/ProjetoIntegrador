// ignore_for_file: avoid_unnecessary_containers, prefer_const_constructors

import 'package:bullkapp/theme/bullkTheme.dart';
import 'package:flutter/material.dart';

class Treinos extends StatelessWidget {
  const Treinos({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: myTheme,
      home: const Scaffold(
        body: TelaTreinos(),
      ),
    );
  }
}

class TelaTreinos extends StatelessWidget {
  const TelaTreinos({super.key});

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
        padding: EdgeInsets.all(16),
        alignment: Alignment.topCenter,
        //color: Colors.cyan,
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Row(
                mainAxisSize: MainAxisSize.max,
                children: [
                  Expanded(
                    child: Text("Treino A" , textAlign: TextAlign.center),
                  ),
                  Expanded(
                    child: Text("Treino B" , textAlign: TextAlign.center),
                  ),
                  Expanded(
                    child: Text("Treino C" , textAlign: TextAlign.center),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
