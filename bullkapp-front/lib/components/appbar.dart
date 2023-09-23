import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  final String? title;

  const CustomAppBar({super.key, this.title = ""});

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(title!),
      leading: Padding(
        padding: const EdgeInsets.only(left: 50),
        child: Transform.scale(
          scale: 15.0,
          child: Image.asset('images/Logo.png'),
        ),
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
