// ignore_for_file: avoid_unnecessary_containers, prefer_const_constructors, prefer_const_literals_to_create_immutables, file_names, avoid_print

import 'package:bullkapp/theme/bullkTheme.dart';
import 'package:flutter/material.dart';

import 'listaTreinos.dart';

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
      body: Container(
        padding: EdgeInsets.all(5),
        alignment: Alignment.topCenter,
        //color: Colors.cyan,
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 10),
              child: Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoA.png", width: 92),
                      onTap: () {
                        print("Treino A");
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) {
                              return ListaTreinos();
                            },
                          ),
                        );
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoB.png", width: 92),
                      onTap: () {
                        print("Treino B");
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: GestureDetector(
                      child: Image.asset("images/TreinoC.png", width: 92),
                      onTap: () {
                        print("Treino C");
                      },
                    ),
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
