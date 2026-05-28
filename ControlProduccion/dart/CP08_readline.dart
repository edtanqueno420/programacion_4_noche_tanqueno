import 'dart:io';

void main() {
  print('Ingrese el nombre del operador:');
  String? nombre = stdin.readLineSync();
  print('Operador: $nombre');

  print('Ingrese cantidad de piezas producidas:');
  int piezas = int.parse(stdin.readLineSync()!);
  print('Piezas producidas: $piezas');

  print("Ingrese peso por pieza (kg):");
  double peso = double.parse(stdin.readLineSync()!);
  print("Peso unitario: ${peso}kg");

  print('Peso total producido: ${piezas * peso}kg');
}
