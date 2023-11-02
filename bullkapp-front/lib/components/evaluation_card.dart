import 'package:bullkapp/repositories/evaluation_repository.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

final EvaluationRepository evaluationRepository = EvaluationRepository();

class EvaluationCard extends StatefulWidget {
  final String mainLabel;

  const EvaluationCard({super.key, required this.mainLabel});

  @override
  State<EvaluationCard> createState() => _EvaluationCardState();
}

class _EvaluationCardState extends State<EvaluationCard> {
  @override
  Widget build(BuildContext context) {
    return Card(
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
          child: GestureDetector(
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
            onTap: () {
              abrirURL();
            },
          ),
        ),
      ),
    );
  }

  void abrirURL() async {
    String teste = await evaluationRepository.getEvaluation(1);

    if (await canLaunchUrl(Uri.parse(teste))) {
      await launchUrl(Uri.parse(teste));
    }
  }
}
