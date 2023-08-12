import 'package:bullkappweb/theme/bullkTheme.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'login.dart';

class AppWidget extends StatelessWidget {
  const AppWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: "BullkApp",
      theme: myTheme,
      home: const LoginScreen(),
      debugShowCheckedModeBanner: false,
    );
  }
}
