double Function(double) crearMultiplicador(double factor) {
  return (double n) => n * factor;
}

void main() {
  final doble = crearMultiplicador(2);
  final triple = crearMultiplicador(3);
  final decuplo = crearMultiplicador(10);

  print(doble(5));
  print(triple(5));
  print(decuplo(5));

  bool Function(double) crearValidadorMedida(double min, double max) {
    return (medida) => medida >= min && medida <= max;
  }

  final esEstandar = crearValidadorMedida(10, 50);
  final esGrande = crearValidadorMedida(100, double.infinity);

  print(esEstandar(35.0));
  print(esGrande(249.99));
  print(esGrande(45.0));
}
