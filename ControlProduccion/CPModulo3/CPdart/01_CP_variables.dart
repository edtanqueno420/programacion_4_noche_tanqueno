void main() {
  var producto = 'Tornillo M8';
  var cantidad = 500;
  var peso = 1.25;
  var disponible = true;

  String codigo = 'TOR-M8-001';
  int stock = 1000;
  double precioUnitario = 0.45;
  bool enProduccion = false;

  final maquinaId = 'CNC-04';
  final lineaProduccion = 'L01';

  const gravedad = 9.8;
  const densidadAcero = 7.85;

  final horaInicio = DateTime.now();

  print('$producto ($codigo) - Stock: $stock - Máquina: $maquinaId');
}
