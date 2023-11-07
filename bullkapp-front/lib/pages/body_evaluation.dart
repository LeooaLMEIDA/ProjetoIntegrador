import 'package:bullkapp/components/appbar.dart' show CustomAppBar;
import 'package:bullkapp/components/bottombar.dart';
import 'package:bullkapp/models/evaluation.dart';
import 'package:flutter/material.dart';
import '../components/evaluation_card.dart';

class BodyEvaluationScreen extends StatefulWidget {
  List<Evaluation> evaluations;
  BodyEvaluationScreen({
    super.key,
    required this.evaluations,
  });

  @override
  State<BodyEvaluationScreen> createState() => _BodyEvaluationScreenState();
}

class _BodyEvaluationScreenState extends State<BodyEvaluationScreen> {
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
            TextField(
              onChanged: (value) {
                var filtered = filterList(filter: value.toString());
                setState(() {
                  widget.evaluations = filtered;
                });
              },
              keyboardType: TextInputType.datetime,
              style: const TextStyle(
                color: Colors.black,
                fontSize: 20.0,
              ),
              decoration: const InputDecoration(
                filled: true,
                fillColor: Color.fromARGB(255, 255, 212, 9),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                ),
                contentPadding:
                    EdgeInsets.symmetric(vertical: 12, horizontal: 12),
                suffixIcon: Icon(
                  Icons.search,
                  size: 32,
                ),
                suffixIconColor: Colors.black,
                hintStyle: TextStyle(
                  color: Colors.black,
                ),
                hintText: "Buscar",
              ),
            ),
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
                    itemCount: widget.evaluations.length,
                    itemBuilder: (context, index) {
                      return Row(
                        children: [
                          EvaluationCard(
                            evaluations: widget.evaluations,
                            mainLabel: widget.evaluations[index].descricao!,
                          ),
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

  List<Evaluation> _originalList = [];

  List<Evaluation> filterList({
    String? filter,
  }) {
    if (_originalList.isEmpty) {
      _originalList = widget.evaluations.toList();
    }

    if (filter != null && filter.isNotEmpty) {
      final filteredList = _originalList
          .where((element) =>
              element.descricao!.toLowerCase().contains(filter.toLowerCase()))
          .toList();
      return filteredList;
    } else {
      return _originalList;
    }
  }
}
