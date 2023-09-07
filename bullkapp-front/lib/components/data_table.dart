import 'package:flutter/material.dart';
import '../models/user.dart';

class CustomDataTable extends StatelessWidget {
  final List<String> columns;
  final List<List<User>> dataList;

  const CustomDataTable(
      {super.key, required this.columns, required this.dataList});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      scrollDirection: Axis.vertical,
      child: SingleChildScrollView(
        scrollDirection: Axis.horizontal,
        child: DataTable(
          columns: columns.map((column) {
            return DataColumn(
              label: Text(column),
            );
          }).toList(),
          rows: dataList.map((rowData) {
            return DataRow(
              cells: rowData.map((cellData) {
                return DataCell(
                  Text(cellData.toString()),
                );
              }).toList(),
            );
          }).toList(),
        ),
      ),
    );
  }
}
