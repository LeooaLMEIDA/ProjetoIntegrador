import 'package:bullkappweb/components/appbar.dart';
import 'package:bullkappweb/controllers/user_controller.dart';
import 'package:bullkappweb/models/user.dart';
import 'package:bullkappweb/repositories/user_repository.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

final userRepository = UserRepository();
UserController userController = Get.find();
List<User> users = [];
List<String> list = [];

class MaintenanceUserScreen extends StatefulWidget {
  const MaintenanceUserScreen({super.key});

  @override
  State<MaintenanceUserScreen> createState() => _UserScreenState();
}

class _UserScreenState extends State<MaintenanceUserScreen> {
  late User user;
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    _fetchGender();
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
            const Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisSize: MainAxisSize.max,
              children: [
                Text(
                  'Manutenção de Usuários',
                  textAlign: TextAlign.left,
                  style: TextStyle(
                    fontSize: 40,
                    fontFamily: 'Voltaire',
                  ),
                ),
              ],
            ),
            const Padding(padding: EdgeInsets.all(30.0)),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: Container(
                width: 850,
                height: 325,
                decoration: BoxDecoration(
                  color: const Color.fromRGBO(129, 154, 178, 1),
                  borderRadius: BorderRadius.circular(15.0),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(15.0),
                  child: Column(
                    children: [
                      const Row(
                        children: [
                          Text(
                            'Dados Pessoais',
                            textAlign: TextAlign.left,
                            style: TextStyle(
                              fontSize: 30,
                              fontFamily: 'Voltaire',
                            ),
                          ),
                        ],
                      ),
                      Padding(
                        padding: const EdgeInsets.all(15.0),
                        child: Column(
                          children: [
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                ConstrainedBox(
                                  constraints:
                                      BoxConstraints.tight(const Size(350, 50)),
                                  child: const TextField(
                                    decoration: InputDecoration(
                                      hintText: 'Nome',
                                      //labelText: 'Nome *',
                                      label: Text('Nome *'),
                                    ),
                                  ),
                                ),
                                ConstrainedBox(
                                  constraints:
                                      BoxConstraints.tight(const Size(170, 50)),
                                  child: DropdownMenu<String>(
                                    width: 170,
                                    
                                    label: const Text("Sexo *"),
                                    initialSelection: list.first,
                                    dropdownMenuEntries: list
                                        .map<DropdownMenuEntry<String>>(
                                            (String value) {
                                      return DropdownMenuEntry<String>(
                                          value: value, label: value);
                                    }).toList(),
                                  ),
                                ),
                                /*
                                ConstrainedBox(
                                  constraints:
                                      BoxConstraints.tight(const Size(170, 50)),
                                  child: TextField(
                                    decoration: InputDecoration(
                                      hintText: 'Sexo',
                                      labelText: 'Sexo *',
                                    ),
                                  ),
                                ),
                                */
                                ConstrainedBox(
                                  constraints:
                                      BoxConstraints.tight(const Size(170, 50)),
                                  child: TextField(
                                    decoration: const InputDecoration(
                                        icon: Icon(Icons
                                            .calendar_today), //icon of text field
                                        labelText:
                                            "Data Nascimento" //label text of field
                                        ),
                                    readOnly:
                                        true, //set it true, so that user will not able to edit text
                                    onTap: () async {
                                      DateTime? pickedDate = await showDatePicker(
                                          context: context,
                                          initialDate: DateTime.now(),
                                          firstDate: DateTime(
                                              2000), //DateTime.now() - not to allow to choose before today.
                                          lastDate: DateTime(2101));
                                    },
                                  ),
                                ),
                                /*
                                ConstrainedBox(
                                  constraints:
                                      BoxConstraints.tight(const Size(170, 50)),
                                  child: const TextField(
                                    decoration: InputDecoration(
                                      hintText: 'Data Nascmento',
                                      labelText: 'Data Nascmento *',
                                    ),
                                  ),
                                ),
                                */
                              ],
                            ),
                          ],
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(15.0),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            ConstrainedBox(
                              constraints:
                                  BoxConstraints.tight(const Size(150, 50)),
                              child: const TextField(
                                decoration: InputDecoration(
                                  hintText: 'Celular',
                                  labelText: 'Celular *',
                                ),
                              ),
                            ),
                            ConstrainedBox(
                              constraints:
                                  BoxConstraints.tight(const Size(235, 50)),
                              child: const TextField(
                                decoration: InputDecoration(
                                  hintText: 'E-mail',
                                  labelText: 'E-mail *',
                                ),
                              ),
                            ),
                            ConstrainedBox(
                              constraints:
                                  BoxConstraints.tight(const Size(150, 50)),
                              child: const TextField(
                                decoration: InputDecoration(
                                  hintText: 'Tipo Usuário',
                                  labelText: 'Tipo Usuário *',
                                ),
                              ),
                            ),
                            ConstrainedBox(
                              constraints:
                                  BoxConstraints.tight(const Size(150, 50)),
                              child: const TextField(
                                decoration: InputDecoration(
                                  hintText: 'Status',
                                  labelText: 'Status *',
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(15.0),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            ConstrainedBox(
                              constraints:
                                  BoxConstraints.tight(const Size(150, 50)),
                              child: const TextField(
                                decoration: InputDecoration(
                                  hintText: 'Senha',
                                  labelText: 'Senha *',
                                ),
                              ),
                            ),
                            ConstrainedBox(
                              constraints:
                                  BoxConstraints.tight(const Size(400, 50)),
                              child: const TextField(
                                decoration: InputDecoration(
                                  hintText: 'Url Avatar',
                                  labelText: 'Url Avatar *',
                                ),
                              ),
                            ),
                            GestureDetector(
                              child: Image.asset(
                                "images/EnviarArquivo.png",
                                width: 150,
                              ),
                              onTap: () async {
                                await Get.to(
                                  () => null,
                                );
                              },
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Container(
              width: 850,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: GestureDetector(
                      child: Image.asset(
                        "images/Excluir.png",
                        width: 150,
                      ),
                      onTap: () async {
                        await Get.to(
                          () => null,
                        );
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: GestureDetector(
                      child: Image.asset(
                        "images/Cancelar.png",
                        width: 150,
                      ),
                      onTap: () async {
                        await Get.to(
                          () => null,
                        );
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: GestureDetector(
                      child: Image.asset(
                        "images/Salvar.png",
                        width: 150,
                      ),
                      onTap: () async {
                        await Get.to(
                          () => null,
                        );
                      },
                    ),
                  ),
                ],
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

  Future<List<String>> _fetchGender() async {
    setState(() {
      _isLoading = true;
    });
    try {
      List<String> fetchedGender = await userRepository.getAllGender();
      setState(() {
        list = fetchedGender;
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
    return list;
  }
}
