List<double> filtrar(List<double> lista, bool Function(double) criterio) {
  return lista.where(criterio).toList();
}

bool esDefectuosa(double n) => n < 10.0 || n > 50.0;
bool esGrande(double n) => n > 100.0;

void main() {
  final datos = [12.0, 7.5, 200.0, 4.0, 150.0, 33.0, 88.0, 301.0];

  print(filtrar(datos, esDefectuosa));
  print(filtrar(datos, esGrande));

  print(filtrar(datos, (n) => n > 20.0 && n < 100.0));
}
