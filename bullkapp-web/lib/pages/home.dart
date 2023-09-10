import 'package:bullkappweb/components/appbar.dart';
import 'package:bullkappweb/pages/view_user.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  //bool _drawerOn = true;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: "",
      ),
      body: Row(
        children: [
          AnimatedContainer(
            duration: const Duration(
              milliseconds: 300,
            ),
            width: 300, //_drawerOn ? 300 : 60,
            color: const Color.fromARGB(255, 4, 53, 101),
            child: Column(
              children: [
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        top: 16.0,
                        left: 16.0,
                      ),
                      child: Row(
                        children: [
                          GestureDetector(
                            child: Image.asset(
                              "assets/images/iconbar.png",
                              width: 20,
                            ),
                            onTap: () {
                              setState(
                                () {
                                  true; //_drawerOn = !_drawerOn;
                                },
                              );
                            },
                          ),
                          const Padding(
                            padding: EdgeInsets.only(
                              left: 24.0,
                            ),
                            child: Text(
                              "Menu",
                              style: TextStyle(
                                color: Colors.white,
                                fontFamily: "Voltaire",
                                fontSize: 16,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        top: 16.0,
                        left: 36.0,
                      ),
                      child: Row(
                        children: [
                          GestureDetector(
                            child: Image.asset(
                              "assets/images/Users.png",
                              width: 20,
                            ),
                            onTap: () async {
                                await Get.to(
                                  () => const UserScreen(),
                                );
                              },
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                              left: 24.0,
                            ),
                            child: GestureDetector(
                              child: const Text(
                                "Usuários",
                                style: TextStyle(
                                  color: Colors.white,
                                  fontFamily: "Voltaire",
                                  fontSize: 16,
                                ),
                              ),
                              onTap: () async {
                                await Get.to(
                                  () => const UserScreen(),
                                );
                              },
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        top: 16.0,
                        left: 36.0,
                      ),
                      child: Row(
                        children: [
                          GestureDetector(
                            child: Image.asset(
                              "assets/images/evaluation.png",
                              width: 20,
                            ),
                            onTap: () {
                              setState(
                                () {
                                  true;
                                },
                              );
                            },
                          ),
                          const Padding(
                            padding: EdgeInsets.only(
                              left: 24.0,
                            ),
                            child: Text(
                              "Avaliações",
                              style: TextStyle(
                                color: Colors.white,
                                fontFamily: "Voltaire",
                                fontSize: 16,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        top: 16.0,
                        left: 36.0,
                      ),
                      child: Row(
                        children: [
                          GestureDetector(
                            child: Image.asset(
                              "assets/images/equipment.png",
                              width: 20,
                            ),
                            onTap: () {
                              setState(
                                () {
                                  true;
                                },
                              );
                            },
                          ),
                          const Padding(
                            padding: EdgeInsets.only(
                              left: 24.0,
                            ),
                            child: Text(
                              "Aparelhos",
                              style: TextStyle(
                                color: Colors.white,
                                fontFamily: "Voltaire",
                                fontSize: 16,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        top: 16.0,
                        left: 36.0,
                      ),
                      child: Row(
                        children: [
                          GestureDetector(
                            child: Image.asset(
                              "assets/images/excercise.png",
                              width: 20,
                            ),
                            onTap: () {
                              setState(
                                () {
                                  true;
                                },
                              );
                            },
                          ),
                          const Padding(
                            padding: EdgeInsets.only(
                              left: 24.0,
                            ),
                            child: Text(
                              "Exercícios",
                              style: TextStyle(
                                color: Colors.white,
                                fontFamily: "Voltaire",
                                fontSize: 16,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        top: 16.0,
                        left: 36.0,
                      ),
                      child: Row(
                        children: [
                          GestureDetector(
                            child: Image.asset(
                              "assets/images/training.png",
                              width: 20,
                            ),
                            onTap: () {
                              setState(
                                () {
                                  true;
                                },
                              );
                            },
                          ),
                          const Padding(
                            padding: EdgeInsets.only(
                              left: 24.0,
                            ),
                            child: Text(
                              "Treinos",
                              style: TextStyle(
                                color: Colors.white,
                                fontFamily: "Voltaire",
                                fontSize: 16,
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
          Expanded(
            child: Center(
              child: Padding(
                padding: const EdgeInsets.only(top: 16.0),
                child: Column(
                  children: [
                    Image.asset(
                      "assets/images/Logo2.png",
                      width: 280,
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  /*
  void callScreen(StatefulWidget statefulWidget) async {
    await Get.to(
      () => const statefulWidget(),
    );
  }
  */
}
