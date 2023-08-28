import 'package:flutter/material.dart';

class LoadImage extends StatelessWidget {
  final String url;
  final String defaultImage;
  const LoadImage({super.key, required this.url, required this.defaultImage});

  @override
  Widget build(BuildContext context) {
    return Image.network(
      url,
      errorBuilder: (context, exception, stackTrace) {
        return Image.asset(
          defaultImage,
          width: 80,
          height: 80,
        );
      },
    );
  }
}
