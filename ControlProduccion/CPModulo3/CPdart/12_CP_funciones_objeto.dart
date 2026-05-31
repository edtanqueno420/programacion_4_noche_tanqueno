double duplicar(double n) => n * 2;
double triplicar(double n) => n * 3;

void main() {
  double Function(double) operacion;

  operacion = duplicar;
  print(operacion(5.5));

  operacion = triplicar;
  print(operacion(5.5));

  final transformaciones = <double Function(double)>[duplicar, triplicar];
  for (final fn in transformaciones) {
    print(fn(10.0));
  }
}
