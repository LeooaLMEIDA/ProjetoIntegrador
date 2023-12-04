import 'dart:typed_data';
import 'package:bullkapp/components/appbar.dart';
import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_pdfviewer/pdfviewer.dart';

class EvaluationFile extends StatelessWidget {
  final Uint8List? pdf;
  const EvaluationFile({super.key, this.pdf});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(),
      body: Center(
        child: SfPdfViewer.memory(
          pdf!,
          initialZoomLevel: 1.2,
        ),
      ),
    );
  }
}
