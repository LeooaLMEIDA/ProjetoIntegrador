import 'package:bullkappweb/components/appbar.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  bool _drawerOn = true;

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
            width: _drawerOn ? 300 : 60,
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
                                  _drawerOn = !_drawerOn;
                                },
                              );
                            },
                          ),
                          if (_drawerOn)
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
}
