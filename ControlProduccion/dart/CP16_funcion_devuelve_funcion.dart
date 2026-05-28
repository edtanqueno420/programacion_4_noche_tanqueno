int Function(int) crearMultiplicador(int factor) {
  return (int n) => n * factor;
}

void main() {
  final doble = crearMultiplicador(2);
  final triple = crearMultiplicador(3);
  final decuplo = crearMultiplicador(10);

  print(doble(5));
  print(triple(5));
  print(decuplo(5));

  bool Function(double) crearValidadorPeso(double min, double max) {
    return (peso) => peso >= min && peso <= max;
  }

  final esLigero = crearValidadorPeso(0, 50);
  final esPesado = crearValidadorPeso(200, double.infinity);

  print(esLigero(35.0));
  print(esPesado(249.99));
  print(esPesado(45.0));
}
