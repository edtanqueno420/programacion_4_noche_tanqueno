List<int> filtrar(List<int> lista, bool Function(int) criterio) {
  return lista.where(criterio).toList();
}

bool esAltaProduccion(int n) => n > 100;
bool esBajaProduccion(int n) => n < 50;

void main() {
  final datos = [30, 70, 200, 40, 150, 33, 88, 301];

  print(filtrar(datos, esAltaProduccion));
  print(filtrar(datos, esBajaProduccion));

  print(filtrar(datos, (n) => n % 3 == 0));
}
