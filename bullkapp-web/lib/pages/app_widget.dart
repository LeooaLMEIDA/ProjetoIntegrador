import 'package:bullkappweb/pages/home.dart';
import 'package:bullkappweb/theme/bullkTheme.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';


class AppWidget extends StatelessWidget {
  const AppWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: "BullkApp",
      theme: myTheme,
      home: const HomeScreen(),//LoginScreen()
      debugShowCheckedModeBanner: false,
    );
  }
}
