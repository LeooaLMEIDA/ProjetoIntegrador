import 'package:bullkapp/components/appbar.dart' show CustomAppBar;
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/models/evaluation.dart';
import 'package:bullkapp/repositories/evaluation_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../components/evaluation_card.dart';

EvaluationRepository _evaluationRepository = EvaluationRepository();

class BodyEvaluationScreen extends StatefulWidget {
  const BodyEvaluationScreen({super.key});

  @override
  State<BodyEvaluationScreen> createState() => _BodyEvaluationScreenState();
}

class _BodyEvaluationScreenState extends State<BodyEvaluationScreen> {
  bool _isLoading = false;
  List<Evaluation> evaluations = [];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            const Padding(
              padding: EdgeInsets.all(8.0),
              child: Row(
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
            const SearchField(),
            Expanded(
              child: Padding(
                padding: const EdgeInsets.all(12.0),
                child: Center(
                  child: GridView.builder(
                    gridDelegate:
                        const SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 2,
                      crossAxisSpacing: 2,
                      mainAxisSpacing: 4,
                    ),
                    itemCount: 4,
                    itemBuilder: (context, index) {
                      return const Row(
                        children: [
                          EvaluationCard(mainLabel: "31/07/2023"),
                        ],
                      );
                    },
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }

  Future<void> _getAllEvaluations(int id) async {
    setState(() {
      _isLoading = true;
    });
    try {
      List<Evaluation> fetchedEvaluation =
          await _evaluationRepository.getAllEvaluation(id);
      setState(() {
        evaluations = fetchedEvaluation;
      });
    } catch (e) {
      Get.snackbar(
        'ERRO',
        'Erro ao obter os Treinos!',
        snackPosition: SnackPosition.BOTTOM,
        backgroundColor: Colors.red,
        colorText: Colors.white,
      );
    }
    setState(() {
      _isLoading = false;
    });
  }
}

class SearchField extends StatelessWidget {
  const SearchField({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return TextField(
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


