// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, use_key_in_widget_constructors

import 'package:flutter/material.dart';

/// Flutter code sample for [Card].

// void main() => runApp(const CardExample());

// class CardExample extends StatelessWidget {
//   const CardExample({super.key});

//   @override
//   Widget build(BuildContext context) {
//     return ListView.builder(
//       itemCount: 10, // Número de cards que deseja exibir na lista
//       itemBuilder: (context, index) {
//         return Padding(
//           padding: const EdgeInsets.all(4.0),
//           child:
//               CardExampleItem(), // Use o CardExampleItem para criar cada card
//         );
//       },
//     );
//   }
// }

class CustomCard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(16),
      ),
      clipBehavior: Clip.hardEdge,
      borderOnForeground: true,
      color: Color.fromARGB(255, 11, 27, 83),
      child: InkWell(
        splashColor: Color.fromARGB(255, 11, 27, 83).withAlpha(100),
        onTap: () {/*função*/},
        child: SizedBox(
          width: 300,
          height: 120,
          child: Stack(
            children: [
              Positioned(
                top: 20,
                left: 20,
                child: Text(
                  'Flexão',
                  style: TextStyle(fontSize: 22, color: Colors.white),
                ),
              ),
              Positioned(
                bottom: 10,
                right: 10,
                child: Text(
                  '2 X 15',
                  style: TextStyle(fontSize: 16, color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
