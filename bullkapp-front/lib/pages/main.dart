import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:sqflite_common_ffi/sqflite_ffi.dart';
import 'app_widget.dart';

void main() {
  
  sqfliteFfiInit();
  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setPreferredOrientations([DeviceOrientation.portraitUp]);
  getSharedPreferences();
  runApp(const AppWidget());
}

void getSharedPreferences() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  if (prefs.getString('treino') == "" || prefs.getString('treino') == null) {
    prefs.setString('treino', "A");
  } 
}
