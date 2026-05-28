abstract class Pieza {
  String get nombre;
  double calcularPeso();
  double calcularCosto();

  void describir() {
    print('$nombre - peso: ${calcularPeso().toStringAsFixed(2)} kg, '
        'costo: \$${calcularCosto().toStringAsFixed(2)}');
  }
}

class Engranaje extends Pieza {
  final double radio;
  Engranaje(this.radio);

  @override String get nombre => 'Engranaje (r=$radio)';
  @override double calcularPeso() => 3.1416 * radio * radio * 0.00785;
  @override double calcularCosto() => calcularPeso() * 2.5;
}

class Placa extends Pieza {
  final double ancho, alto;
  Placa(this.ancho, this.alto);

  @override String get nombre => 'Placa (${ancho}x$alto)';
  @override double calcularPeso() => ancho * alto * 0.01 * 7.85;
  @override double calcularCosto() => calcularPeso() * 1.8;
}

void main() {
  final piezas = <Pieza>[Engranaje(5), Placa(4, 7)];
  for (final p in piezas) {
    p.describir();
  }
}
