// ignore_for_file: file_names, prefer_const_constructors
import 'package:bullkapp/theme/BullkTheme.dart' show myTheme;
import 'package:flutter/material.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

import 'login.dart';

class AppWidget extends StatelessWidget {
  const AppWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: "BullkApp",
      theme: myTheme,
      home: Login(),
      debugShowCheckedModeBanner: false,
    );
  }
}
