import 'package:flutter/material.dart';

import 'components/appbar.dart';
import 'components/fields.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(title: ""),
      body: Column(
        children: [
          Container(
            padding: const EdgeInsets.all(16),
            child: Image.asset(
              'images/Logo2.png',
              alignment: Alignment.bottomCenter,
              width: 220,
              height: 250,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(
              left: 10.0,
              right: 10.0,
            ),
            child: Column(
              children: [
                Column(
                  children: [
                    const Row(
                      children: [
                        Padding(
                          padding: EdgeInsets.only(
                            left: 8.0,
                            right: 8.0,
                          ),
                          child: Text(
                            'E-mail',
                            style: TextStyle(
                              fontFamily: "Voltaire",
                              fontSize: 28,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                    CustomField(
                      controller: _emailController,
                      tip: "E-mail",
                      icon: Icons.account_circle,
                      inputType: TextInputType.emailAddress,
                    ),
                  ],
                ),
                const SizedBox(
                  height: 8,
                ),
                Column(
                  children: [
                    const Row(
                      children: [
                        Padding(
                          padding: EdgeInsets.only(
                            left: 8.0,
                            right: 8.0,
                          ),
                          child: Text(
                            "Senha",
                            style: TextStyle(
                              fontFamily: "Voltaire",
                              fontSize: 28,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                    TextFormField(
                      controller: _passwordController,
                      obscureText: !_viewPassword,
                      style: const TextStyle(
                        color: Colors.white,
                        fontSize: 20.0,
                      ),
                      decoration: InputDecoration(
                        filled: true,
                        fillColor: const Color.fromARGB(250, 1, 30, 62),
                        border: const OutlineInputBorder(
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
                        hintStyle: const TextStyle(
                          color: Color.fromARGB(113, 255, 255, 255),
                        ),
                        hintText: "Senha",
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
