// ignore_for_file: library_private_types_in_public_api, avoid_print

import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../components/list_card.dart';
import '../models/user.dart';
import '../pages/workout_detail.dart';
import '../repositories/user_repository.dart'; // Importe o UserRepository

final userRepository = UserRepository();

class WorkoutList extends StatefulWidget {
  const WorkoutList({super.key});

  @override
  _WorkoutListState createState() => _WorkoutListState();
}

class _WorkoutListState extends State<WorkoutList> {
  List<User> users = [];

  @override
  void initState() {
    super.initState();
    _fetchUsers();
  }

  Future<void> _fetchUsers() async {
    try {
      List<User> fetchedUsers = await userRepository.getUsers();
      setState(() {
        users = fetchedUsers;
      });
    } catch (e) {
      print('Erro ao obter os usuários: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: users.length,
      itemBuilder: (context, index) {
        if (index < users.length) {
          final user = users[index];
          return Padding(
            padding: const EdgeInsets.all(4.0),
            child: CustomListCard(
              exerciceName: user.nome,
              onTap: () async => await Get.to(
                () => const WorkoutDetail(),
              ),
              repetition: '2',
              series: '15',
            ),
          );
        } else {
          return Container();
        }
      },
    );
  }
}

void selectWorkout(int index) {
  print('Treino $index tapped.');
}
