import 'package:bullkappweb/components/appbar.dart';
import 'package:flutter/material.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(title: ""),
      body: Column(
        children: [
          Container(
            height: 200,
            color: Colors.brown,
          ),
        ],
      ),
    );
  }
}

/*
TextFormField(
  controller: _passwordController,
  obscureText: !_viewPassword,
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
    suffixIcon: IconButton(
      icon: Icon(
        _viewPassword
            ? Icons.visibility_off_outlined
            : Icons.visibility_outlined,
      ),
      onPressed: () {
        setState(() {
          _viewPassword = !_viewPassword;
        });
      },
    ),
    suffixIconColor: Colors.white,
    hintStyle: TextStyle(
      color: Color.fromARGB(113, 255, 255, 255),
    ),
    hintText: "Senha",
  ),
),
*/