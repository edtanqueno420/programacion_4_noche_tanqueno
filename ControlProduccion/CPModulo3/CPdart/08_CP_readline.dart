import 'dart:io';

void main() {
  int valor = 0;
  int cantidad = 0;
  int suma = 0;

  while (valor == 0) {
    print('Ingrese el precio de la pieza:');
    valor = int.parse(stdin.readLineSync()!);
    cantidad += cantidad;
    suma += valor;
  }
  print('$cantidad');
  print('$suma');
}
