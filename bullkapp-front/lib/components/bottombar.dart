// ignore_for_file: prefer_const_constructors

import 'package:bullkapp/pages/home.dart';
import 'package:bullkapp/pages/training.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../pages/training_detail.dart';

class CustomBottomAppBar extends StatelessWidget {
  const CustomBottomAppBar({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.only(
        topLeft: Radius.circular(38.0),
        topRight: Radius.circular(38.0),
      ),
      child: BottomAppBar(
        height: 70,
        color: Color.fromARGB(250, 1, 30, 62),
        child: IconTheme(
          data: IconThemeData(color: Colors.white),
          child: Padding(
            padding: EdgeInsets.all(12),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Expanded(
                  child: GestureDetector(
                    child: Image.asset(
                      "images/home.png",
                      width: 60,
                      height: 40,
                    ),
                    onTap: () async {
                      await Get.to(
                        () => HomeScreen(),
                      );
                    },
                  ),
                ),
                Expanded(
                  child: GestureDetector(
                    child: Image.asset(
                      "images/weight.png",
                      width: 40,
                      height: 60,
                    ),
                    onTap: () async {
                      await Get.to(() => TrainingScreen(
                            showBottomBar: true,
                          ));
                    },
                  ),
                ),
                Expanded(
                  child: GestureDetector(
                    child: Image.asset(
                      "images/user.png",
                      width: 60,
                      height: 40,
                    ),
                    onTap: () async {
                      await Get.to(() => TrainingDetail());
                    },
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
