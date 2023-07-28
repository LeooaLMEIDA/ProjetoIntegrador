import 'package:bullkapp/components/appbar.dart';
import 'package:bullkapp/components/bottombar.dart';
import 'package:flutter/material.dart';

import '../components/fields.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(
        title: "",
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Stack(
          children: [
            Column(
              children: [
                SizedBox(
                  height: 120,
                  child: Align(
                    alignment: Alignment.center,
                    child: Container(
                      width: 120,
                      height: 120,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        border: Border.all(
                          color: const Color.fromARGB(250, 1, 30, 62),
                          width: 3.0,
                        ),
                      ),
                      clipBehavior: Clip.antiAlias,
                      child: Image.asset(
                        'images/avatar.png',
                        width: 100,
                        height: 100,
                        fit: BoxFit.fill,
                      ),
                    ),
                  ),
                ),
                const Row(
                  children: [
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "Nome",
                        inputType: TextInputType.name,
                      ),
                    ),
                    SizedBox(
                      width: 8.0,
                    ),
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "Telefone",
                        inputType: TextInputType.phone,
                      ),
                    ),
                  ],
                ),
                const Row(
                  children: [
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "E-mail",
                        inputType: TextInputType.emailAddress,
                      ),
                    ),
                  ],
                ),
                const Row(
                  children: [
                    Expanded(
                      child: CustomReadOnlyField(
                          label: "Data Nascimento",
                          inputType: TextInputType.datetime),
                    ),
                    SizedBox(
                      width: 8.0,
                    ),
                    Expanded(
                      child: CustomReadOnlyField(
                        label: "Sexo",
                        inputType: TextInputType.text,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ],
        ),
      ),
      extendBody: false,
      bottomNavigationBar: const CustomBottomAppBar(),
    );
  }
}
