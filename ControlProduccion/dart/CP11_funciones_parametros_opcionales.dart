String construirOrden(String material, String pieza, [int? cantidad]) {
  if (cantidad != null) {
    return 'Orden: $material - $pieza x $cantidad';
  }
  return 'Orden: $material - $pieza';
}

String construirOrdenV2(String material, String pieza, [int cantidad = 50]) {
  return 'Orden: $material - $pieza x $cantidad';
}

void configurarProduccion({
  required String material,
  required int cantidad,
  bool calidad = true,
  int tiempoMin = 30,
}) {
  final control = calidad ? 'con control' : 'sin control';
  print('Produccion: $material x $cantidad ($control, tiempo: ${tiempoMin}min)');
}

void main() {
  print(construirOrden('Acero', 'Engranaje'));
  print(construirOrden('Acero', 'Engranaje', 100));
  print(construirOrdenV2('Aluminio', 'Soporte'));

  configurarProduccion(
    material: 'Acero',
    cantidad: 500,
    calidad: true,
    tiempoMin: 45,
  );

  configurarProduccion(
    material: 'Aluminio',
    cantidad: 200,
  );
}
