import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';

class BodyEvaluationScreen extends StatelessWidget {
  const BodyEvaluationScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: CustomAppBar(
        title: '',
      ),
      body: Padding(
        padding: EdgeInsets.all(8.0),
        child: Column(
          children: [
            Text(
              'Avaliações',
              style: TextStyle(
                fontFamily: 'Voltaire',
                fontSize: 30,
              ),
            ),
            
          ],
        ),
      ),
      bottomNavigationBar: CustomBottomAppBar(),
    );
  }
}
