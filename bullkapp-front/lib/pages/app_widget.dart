import 'package:bullkapp/controllers/user_controller.dart';
import 'package:bullkapp/pages/login.dart';
import 'package:bullkapp/theme/BullkTheme.dart' show myTheme;
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class AppWidget extends StatelessWidget {
  const AppWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: "BullkApp",
      theme: myTheme,
      home: const Login(),
      debugShowCheckedModeBanner: false,
      initialBinding: BindingsBuilder(() {
        Get.put<UserController>(UserController(), permanent: true);
      }),
    );
  }
}
