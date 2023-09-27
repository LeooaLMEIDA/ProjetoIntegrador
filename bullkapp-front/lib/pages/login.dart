// ignore_for_file: prefer_const_constructors, avoid_unnecessary_containers, prefer_const_literals_to_create_immutables, unused_element, use_key_in_widget_constructors

import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/repositories/login_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/fields.dart';
import '../models/user.dart';
import 'home.dart';
import 'profile.dart';

final LoginRepository loginRepository = LoginRepository();

class Login extends StatefulWidget {
  const Login();

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  bool _viewPassword = false;
  late User user;

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async => false,
      child: Scaffold(
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
                          SizedBox(height: 8),
                          Container(
                            child: Column(
                              children: [
                                Row(
                                  children: [
                                    Padding(
                                      padding: const EdgeInsets.only(
                                          left: 8.0, right: 8.0),
                                      child: Text(
                                        "Senha",
                                        style: TextStyle(
                                            fontFamily: "Voltaire",
                                            fontSize: 28,
                                            fontWeight: FontWeight.bold),
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
                              Size(160, 20),
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
                            final String email = _emailController.text.trim();
                            final String senha =
                                _passwordController.text.trim();
                            String message;
                            if (email.isNotEmpty && senha.isNotEmpty) {
                              try {
                                await _doLogin();
                                Get.to(() => HomeScreen());
                                Get.snackbar(
                                  'SUCESSO',
                                  "BEM VINDO(A)",
                                  snackPosition: SnackPosition.TOP,
                                  duration: Duration(seconds: 1),
                                  backgroundColor: Colors.green,
                                  colorText: Color.fromARGB(255, 255, 255, 255),
                                );
                              } catch (e) {
                                message = e.toString();
                                Get.snackbar(
                                  'Credenciais Incorretas',
                                  message.substring(22),
                                  snackPosition: SnackPosition.BOTTOM,
                                  backgroundColor: Colors.red,
                                  colorText: Colors.white,
                                );
                              }
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
      ),
    );
  }

  _doLogin() async {
    final email = _emailController.text.trim();
    final password = _passwordController.text.trim();

    if (email.isNotEmpty || password.isNotEmpty) {
      try {
        final response = await loginRepository.postLogin(email, password);
        user = response;
        userController.setId(user.id ?? 0);
        userController.setName(user.nome ?? "");
        userController.setEmail(user.email);
        userController.setPhone(user.celular);
        userController.setDtBirth(user.dtNascimento);
        userController.setGender(user.sexo);
      } catch (e) {
        throw Exception(e);
      }
    }
  }

  // _getPhotoUser() async {

  // }
}
