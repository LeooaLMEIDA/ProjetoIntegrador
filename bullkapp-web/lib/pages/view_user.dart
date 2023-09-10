import 'package:bullkappweb/components/appbar.dart';
import 'package:bullkappweb/controllers/user_controller.dart';
import 'package:bullkappweb/models/user.dart';
import 'package:bullkappweb/pages/maintenance_user.dart';
import 'package:bullkappweb/repositories/user_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

final userRepository = UserRepository();
UserController userController = Get.find();
List<User> users = [];

class UserScreen extends StatefulWidget {
  const UserScreen({super.key});

  @override
  State<UserScreen> createState() => _UserScreenState();
}

class _UserScreenState extends State<UserScreen> {
  late User user;
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    //_getUsers();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(title: ""),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisSize: MainAxisSize.max,
              children: [
                const Text(
                  'Manutenção de Usuários',
                  textAlign: TextAlign.left,
                  style: TextStyle(
                    fontSize: 40,
                    fontFamily: 'Voltaire',
                  ),
                ),
                Expanded(child: Container()),
                GestureDetector(
                  child: Image.asset(
                    "images/NovoUsuario.png",
                    width: 150,
                  ),
                  onTap: () async {
                    await Get.to(
                      () => const MaintenanceUserScreen(),
                    );
                  },
                ),
              ],
            ),
            Center(
              child: Container(
                width: 1500,
                height: 500,
                child: SingleChildScrollView(
                  scrollDirection: Axis.vertical,
                  child: SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    child: DataTable(
                      columns: const [
                        DataColumn(label: Text('ID')),
                      ],
                      rows: const [
                        DataRow(cells: [
                          DataCell(Text("nome")),
                        ]),
                      ],
                    ), //CustomDataTable(),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Future<List<User>> _fetchUsers() async {
    setState(() {
      _isLoading = true;
    });
    try {
      List<User> fetchedUser = await userRepository.getAllUsers();
      setState(() {
        users = fetchedUser;
      });
    } catch (e) {
      Get.snackbar(
        'ERRO',
        'Erro ao obter os Usuários!',
        snackPosition: SnackPosition.BOTTOM,
        backgroundColor: Colors.red,
        colorText: Colors.white,
      );
    }
    setState(() {
      _isLoading = false;
    });
    return users;
  }
}
