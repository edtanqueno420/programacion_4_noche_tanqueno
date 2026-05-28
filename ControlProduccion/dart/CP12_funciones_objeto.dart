int doblarPiezas(int n) => n * 2;
int triplicarPiezas(int n) => n * 3;

void main() {
  int Function(int) operacion;

  operacion = doblarPiezas;
  print(operacion(50));

  operacion = triplicarPiezas;
  print(operacion(50));

  final transformaciones = <int Function(int)>[doblarPiezas, triplicarPiezas];
  for (final fn in transformaciones) {
    print(fn(100));
  }
}
