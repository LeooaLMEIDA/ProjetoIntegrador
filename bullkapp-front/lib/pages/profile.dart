import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: "",
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            Container(
              height: 140,
              color: Colors.amberAccent,
              child: Stack(
                children: [
                  Positioned(
                    top: 10,
                    left: 10,
                    child: Container(
                      width: 120,
                      height: 120,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        border: Border.all(
                          color: Color.fromARGB(250, 1, 30, 62),
                          width: 2.0,
                        ),
                      ),
                      clipBehavior: Clip.antiAlias,
                      child: Image.asset(
                        'images/avatar.png',
                        width: 100,
                        height: 100,
                        fit: BoxFit.fill,
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Container(
              height: 140,
              color: const Color.fromARGB(255, 73, 66, 43),
            ),
          ],
        ),
      ),
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}
