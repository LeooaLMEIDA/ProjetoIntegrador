import 'package:bullkappweb/theme/bullkTheme.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'home.dart';

class AppWidget extends StatelessWidget {
  const AppWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: "BullkApp",
      theme: myTheme,
      home: HomeScreen(),
      debugShowCheckedModeBanner: false,
    );
  }
}
