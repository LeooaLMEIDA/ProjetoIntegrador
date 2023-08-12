import 'package:bullkappweb/components/appbar.dart';
import 'package:bullkappweb/data/constants.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: "",
      ),
      body: Drawer(
        backgroundColor: defaultColor,
        child: ListView(
          children: [
            ListTile(
              leading: Image.asset("assets/images/home.png"),
              titleTextStyle: const TextStyle(
                fontFamily: "Voltaire",
                fontSize: 18,
                color: Colors.white,
              ),
              title: const Text(
                "Web",
              ),
              onTap: () {},
            )
          ],
        ),
      ),
    );
  }
}
