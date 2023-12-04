import 'dart:convert';
import 'dart:typed_data';
import 'package:bullkapp/pages/evaluation_file.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../models/evaluation.dart';

class EvaluationCard extends StatefulWidget {
  final String mainLabel;
  final int index;
  final List<Evaluation> evaluations;

  const EvaluationCard({
    super.key,
    required this.mainLabel,
    required this.evaluations,
    required this.index,
  });

  @override
  State<EvaluationCard> createState() => _EvaluationCardState();
}

class _EvaluationCardState extends State<EvaluationCard> {
  Uint8List? pdf;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(16),
        ),
        clipBehavior: Clip.hardEdge,
        borderOnForeground: true,
        color: const Color.fromARGB(160, 4, 53, 101),
        child: InkWell(
          splashColor: const Color.fromARGB(160, 4, 53, 101).withAlpha(255),
          child: SizedBox(
            width: MediaQuery.of(context).size.width * .37,
            child: Stack(
              children: [
                Positioned(
                  top: 110,
                  left: 92,
                  child: Image.asset(
                    "images/download.png",
                    width: 35,
                    height: 35,
                  ),
                ),
                Center(
                  child: Text(
                    widget.mainLabel,
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
      onTap: () {
        convertPDF();
        Get.to(
          () => EvaluationFile(
            pdf: pdf,
          ),
        );
      },
    );
  }

  void convertPDF() {
    final pdfEvaluation = widget.evaluations[widget.index].arqAvaliacao;
    if (pdfEvaluation != null) {
      final cleanedPdf = pdfEvaluation.replaceAll(RegExp(r'\s+'), '');
      final pdfData = base64Decode(cleanedPdf);
      setState(() {
        pdf = pdfData;
      });
    }
  }
}
