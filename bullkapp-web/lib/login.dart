import 'package:bullkappweb/components/appbar.dart';
import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  bool _viewPassword = false;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(title: ""),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            SizedBox(
              child: Center(
                child: Image.asset(
                  "assets/images/Logo2.png",
                  width: 180,
                ),
              ),
            ),
            Column(
              children: [
                SizedBox(
                  width: 400,
                  child: Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextFormField(
                          decoration: const InputDecoration(
                            filled: true,
                            fillColor: Color.fromARGB(250, 1, 30, 62),
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.all(
                                Radius.circular(20),
                              ),
                            ),
                            suffixIcon: Icon(Icons.account_circle),
                            suffixIconColor: Colors.white,
                            hintStyle: TextStyle(
                              color: Color.fromARGB(113, 255, 255, 255),
                            ),
                            hintText: "E-mail",
                          ),
                          style: const TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextFormField(
                          controller: _passwordController,
                          obscureText: !_viewPassword,
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
                          style: const TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      ),
                      ElevatedButton(
                        style: ButtonStyle(
                          minimumSize: MaterialStateProperty.all(
                            const Size(160, 20),
                          ),
                          backgroundColor: MaterialStateProperty.all(
                            const Color.fromARGB(255, 255, 195, 1),
                          ),
                          foregroundColor: MaterialStateProperty.all(
                            const Color.fromARGB(255, 0, 0, 0),
                          ),
                          elevation: MaterialStateProperty.all(4.0),
                          padding: MaterialStateProperty.all(
                            const EdgeInsets.all(16.0),
                          ),
                          shape: MaterialStateProperty.all(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(50.0),
                            ),
                          ),
                        ),
                        onPressed: () async {},
                        child: const Text(
                          'Entrar',
                          style: TextStyle(
                            fontSize: 18,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
