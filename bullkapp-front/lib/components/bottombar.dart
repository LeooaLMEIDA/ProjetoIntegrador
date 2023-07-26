// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class bottomappbar extends StatelessWidget {
  const bottomappbar({
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
        color: Color.fromARGB(250, 1, 30, 62),
        child: IconTheme(
          data: IconThemeData(color: Colors.white),
          child: Padding(
            padding: EdgeInsets.all(12),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                IconButton(
                  icon: const Icon(Icons.accessible_forward),
                  onPressed: () {},
                ),
                IconButton(
                  icon: const Icon(Icons.accessible_forward),
                  onPressed: () {},
                ),
                IconButton(
                  icon: const Icon(Icons.accessible_forward),
                  onPressed: () {},
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
