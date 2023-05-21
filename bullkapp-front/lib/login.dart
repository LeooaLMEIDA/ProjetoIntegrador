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
                child: Padding(
                  padding: const EdgeInsets.all(10.0),
                  child: Container(
                    //Container dos Campos
                    child: Column(
                      children: [
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Container(
                            //color: Colors.indigo,
                            child: CampoChar(
                                dica: "E-mail",
                                rotulo: "",
                                icon: Icons.account_circle),
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Container(
                            //color: Colors.deepOrangeAccent,
                            child: CampoPassword(
                              dica: "Senha",
                              rotulo: "",
                              icon: Icons.password,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
              Container(
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: ElevatedButton(
                        style: ButtonStyle(
                          minimumSize: MaterialStateProperty.all(Size(160, 20)),
                          backgroundColor:
                              MaterialStateProperty.all(Color.fromARGB(255, 255, 195 , 1)),
                          foregroundColor:
                              MaterialStateProperty.all(Color.fromARGB(255, 0, 0, 0)),
                          elevation: MaterialStateProperty.all(5.0),
                          padding:
                              MaterialStateProperty.all(EdgeInsets.all(16.0)),
                          shape: MaterialStateProperty.all(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(50.0),
                            ),
                          ),
                        ),
                        onPressed: () {
                          //Navigator.pushReplacement(context,
                            //  MaterialPageRoute(builder: (context) {
                           // return Home();
                          //}));
                          Navigator.push(context,
                              MaterialPageRoute(builder: (context) {
                            return Home();
                          }));
                        },
                        child: const Text('Entrar'),
                      ),
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
