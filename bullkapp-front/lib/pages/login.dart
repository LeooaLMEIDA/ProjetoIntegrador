// ignore_for_file: prefer_const_constructors, avoid_unnecessary_containers, prefer_const_literals_to_create_immutables, unused_element, use_key_in_widget_constructors

import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/repositories/login_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/fields.dart';
import 'home.dart';

class Login extends StatefulWidget {
  const Login();

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  bool _viewPassword = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        title: "",
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
                  padding: const EdgeInsets.only(
                    left: 10.0,
                    right: 10.0,
                  ),
                  child: Container(
                    child: Column(
                      children: [
                        Container(
                          child: Column(
                            children: [
                              Row(
                                children: [
                                  Padding(
                                    padding: const EdgeInsets.only(
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
                        ),
                        SizedBox(
                          height: 8,
                        ),
                        Container(
                          child: Column(
                            children: [
                              Row(
                                children: [
                                  Padding(
                                    padding: const EdgeInsets.only(
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
                                    icon: Icon(_viewPassword
                                        ? Icons.visibility_off_outlined
                                        : Icons.visibility_outlined),
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
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 8,
              ),
              Container(
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: ElevatedButton(
                        style: ButtonStyle(
                          minimumSize: MaterialStateProperty.all(
                            Size(
                              160,
                              20,
                            ),
                          ),
                          backgroundColor: MaterialStateProperty.all(
                            Color.fromARGB(255, 255, 195, 1),
                          ),
                          foregroundColor: MaterialStateProperty.all(
                            Color.fromARGB(255, 0, 0, 0),
                          ),
                          elevation: MaterialStateProperty.all(4.0),
                          padding: MaterialStateProperty.all(
                            EdgeInsets.all(16.0),
                          ),
                          shape: MaterialStateProperty.all(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(50.0),
                            ),
                          ),
                        ),
                        onPressed: () async {
                          var isAllowed = _DoLogin();

                          if (isAllowed) {
                            await Get.to(
                              () => HomeScreen(),
                            );
                          } else {
                            showDialog(
                              context: context,
                              builder: (_) => AlertDialog(
                                title: Text('Erro de login'),
                                content: Text('Usuário ou senha inválidos.'),
                                actions: [
                                  ElevatedButton(
                                    onPressed: () => Navigator.pop(context),
                                    child: Text('OK'),
                                  ),
                                ],
                              ),
                            );
                          }
                        },
                        child: const Text(
                          'Entrar',
                          style: TextStyle(
                            fontSize: 18,
                          ),
                        ),
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

  bool _DoLogin() {
    final email = _emailController.text.trim();
    final password = _passwordController.text.trim();
    bool isAllowed = false;
    final LoginRepository loginRepository = LoginRepository();

    if (email.isEmpty || password.isEmpty) {
      return false;
    }

    try {
      final response = loginRepository.postLogin(email, password);
      print(response);
      // isAllowed = true;
    } catch (e) {
      // isAllowed = false;
    }
    return isAllowed;
  }
}

// class LoginButton extends StatelessWidget {
//   const LoginButton({
//     super.key,
//   });

//   @override
//   Widget build(BuildContext context) {
//     return ElevatedButton(
//       style: ButtonStyle(
//         minimumSize: MaterialStateProperty.all(
//           Size(
//             160,
//             20,
//           ),
//         ),
//         backgroundColor: MaterialStateProperty.all(
//           Color.fromARGB(255, 255, 195, 1),
//         ),
//         foregroundColor: MaterialStateProperty.all(
//           Color.fromARGB(255, 0, 0, 0),
//         ),
//         elevation: MaterialStateProperty.all(4.0),
//         padding: MaterialStateProperty.all(
//           EdgeInsets.all(16.0),
//         ),
//         shape: MaterialStateProperty.all(
//           RoundedRectangleBorder(
//             borderRadius: BorderRadius.circular(50.0),
//           ),
//         ),
//       ),
//       onPressed: () async {
//         if (_DoLogin = true) {}
//         await Get.to(
//           () => HomeScreen(),
//         );
//       },
//       child: const Text(
//         'Entrar',
//         style: TextStyle(
//           fontSize: 18,
//         ),
//       ),
//     );
//   }
// }
