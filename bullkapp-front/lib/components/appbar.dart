import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  const CustomAppBar({super.key, this.title});

  final String? title;

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(title!),
      leading: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Image.asset('images/Logo3.png'),
      ),
      toolbarHeight: 60,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.only(
          bottomLeft: Radius.circular(38.0),
          bottomRight: Radius.circular(38.0),
        ),
      ),
    );
  }
}
