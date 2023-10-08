import 'package:flutter/material.dart';

class LoadImage extends StatelessWidget {
  final String url;
  final String defaultImage;
  final double? widthImage;
  const LoadImage(
      {super.key,
      required this.url,
      required this.defaultImage,
      this.widthImage = 100});

  @override
  Widget build(BuildContext context) {
    return Image.network(
      url,
      errorBuilder: (context, exception, stackTrace) {
        return Image.asset(
          defaultImage,
          width: widthImage,
        );
      },
    );
  }
}
