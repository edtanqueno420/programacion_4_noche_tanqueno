void main() {
  final costos = [29.99, 49.50, 15.00, 99.99];

  final costosConRecargo = costos.map((c) => c * 1.15);
  print(costosConRecargo.toList());

  final piezas = ['TOR-M8', 'FRE-E4', 'RECT-P1'];
  final codigos = piezas.map((p) => 'CP-$p');
  print(codigos.toList());

  final medidas = [36.1, 37.8, 39.2, 36.5, 38.7, 35.9];

  final fueraRango = medidas.where((m) => m > 38.0);
  print(fueraRango.toList());

  final dentroRango = medidas.where((m) => m >= 36.0 && m <= 38.0);
  print(dentroRango.toList());

  final produccion = [150.0, 230.0, 980.0, 3100.0, 750.0];

  final total = produccion.reduce((acum, prod) => acum + prod);
  print('Total: \$${total.toStringAsFixed(2)}');

  final totalFold = produccion.fold(0.0, (acum, prod) => acum + prod);
  print('Total (fold): \$${totalFold.toStringAsFixed(2)}');

  final maximo = produccion.reduce((a, b) => a > b ? a : b);
  print('Mayor producción: \$$maximo');
}
