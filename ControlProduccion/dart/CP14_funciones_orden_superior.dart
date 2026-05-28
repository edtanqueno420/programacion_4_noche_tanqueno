void main() {
  final pesos = [29.99, 49.50, 15.00, 99.99];

  final pesosConIva = pesos.map((p) => p * 1.15);
  print(pesosConIva.toList());

  final piezas = ['/engranajes', '/ejes', '/soportes'];
  final urls = piezas.map((e) => 'https://fabrica.com$e');
  print(urls.toList());

  final temperaturas = [80.0, 120.0, 250.0, 90.0, 200.0, 60.0];

  final sobrecalentadas = temperaturas.where((t) => t > 200);
  print(sobrecalentadas.toList());

  final normales = temperaturas.where((t) => t >= 60.0 && t <= 150.0);
  print(normales.toList());

  final produccion = [1500.0, 2300.0, 980.0, 3100.0, 750.0];

  final total = produccion.reduce((acum, venta) => acum + venta);
  print('Total: \$${total.toStringAsFixed(2)}');

  final totalFold = produccion.fold(0.0, (acum, venta) => acum + venta);
  print('Total (fold): \$${totalFold.toStringAsFixed(2)}');

  final maximo = produccion.reduce((a, b) => a > b ? a : b);
  print('Mayor produccion: \$$maximo');
}
