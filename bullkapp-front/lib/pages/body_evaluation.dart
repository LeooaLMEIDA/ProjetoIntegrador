import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/search.dart';

class BodyEvaluationScreen extends StatelessWidget {
  const BodyEvaluationScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: '',
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            const Text(
              'Avaliações',
              style: TextStyle(
                fontFamily: 'Voltaire',
                fontSize: 30,
              ),
            ),
            TextField(
              keyboardType: TextInputType.text,
              style: const TextStyle(
                color: Colors.black,
                fontSize: 20.0,
              ),
              decoration: InputDecoration(
                filled: true,
                fillColor: const Color.fromARGB(255, 255, 212, 9),
                border: const OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                ),
                contentPadding:
                    const EdgeInsets.symmetric(vertical: 12, horizontal: 12),
                suffixIcon: IconButton(
                  onPressed: () async {
                    final result = await showSearch(
                      context: context,
                      delegate: Search(),
                    );
                  },
                  icon: const Icon(
                    Icons.search,
                    size: 32,
                  ),
                ),
                suffixIconColor: Colors.black,
                hintStyle: const TextStyle(
                  color: Colors.black,
                ),
                hintText: "Buscar",
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}

class Search extends SearchDelegate {
  final List<String> evaluationList = [
    '31/07/2023',
    '01/08/2023',
    '01/09/2023'
  ];

  @override
  List<Widget>? buildActions(BuildContext context) {
    return [
      IconButton(
        onPressed: () {
          query = '';
        },
        icon: const Icon(Icons.clear),
      ),
    ];
  }

  @override
  Widget? buildLeading(BuildContext context) {
    return IconButton(
      onPressed: () {
        close(context, null);
      },
      icon: const Icon(Icons.arrow_back),
    );
  }

  @override
  Widget buildResults(BuildContext context) {
    final List<String> filteredList = evaluationList
        .where((evaluation) => evaluation.contains(query))
        .toList();

    return ListView.builder(
      itemCount: filteredList.length,
      itemBuilder: (context, index) {
        final evaluation = filteredList[index];
        return ListTile(
          title: Text(evaluation),
          onTap: () {
            close(context, evaluation);
          },
        );
      },
    );
  }

  @override
  Widget buildSuggestions(BuildContext context) {
    final List<String> filteredList = evaluationList
        .where((evaluation) => evaluation.contains(query))
        .toList();

    return ListView.builder(
      itemCount: filteredList.length,
      itemBuilder: (context, index) {
        final evaluation = filteredList[index];
        return ListTile(
          title: Text(evaluation),
          onTap: () {
            query = evaluation;
          },
        );
      },
    );
  }
}
