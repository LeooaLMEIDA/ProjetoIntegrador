import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  final String? title;
  final bool? isAlternative;

  const CustomAppBar({
    super.key,
    this.title = "",
    this.isAlternative = false,
  });

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(title!),
      centerTitle: true,
      leading: Padding(
        padding: const EdgeInsets.only(left: 50),
        child: Transform.scale(
          scale: 15.0,
          child: Image.asset(
            'images/Logo.png',
          ),
        ),
      ),
      toolbarHeight: 60,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.only(
          bottomLeft: Radius.circular(38.0),
          bottomRight: Radius.circular(38.0),
        ),
      ),
      actions: [
        Padding(
          padding: const EdgeInsets.only(right: 16),
          child: isAlternative!
              ? const Tooltip(
                  message: "Treino Alternativo",
                  child: Icon(
                    Icons.brightness_auto_rounded,
                    color: Colors.amber,
                    size: 35,
                  ),
                )
              : Container(),
        ),
      ],
    );
  }
}
