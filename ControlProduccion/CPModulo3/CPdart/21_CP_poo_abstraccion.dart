abstract class Chapa {
  String get nombre;
  double calcularArea();
  double calcularPerimetro();

  void describir() {
    print('$nombre — área: ${calcularArea().toStringAsFixed(2)}, '
          'perímetro: ${calcularPerimetro().toStringAsFixed(2)}');
  }
}

class ChapaCircular extends Chapa {
  final double radio;
  ChapaCircular(this.radio);

  @override String get nombre => 'Chapa Circular (r=$radio)';
  @override double calcularArea() => 3.1416 * radio * radio;
  @override double calcularPerimetro() => 2 * 3.1416 * radio;
}

class ChapaRectangular extends Chapa {
  final double ancho, alto;
  ChapaRectangular(this.ancho, this.alto);

  @override String get nombre => 'Chapa Rectangular (${ancho}x$alto)';
  @override double calcularArea() => ancho * alto;
  @override double calcularPerimetro() => 2 * (ancho + alto);
}

void main() {
  final chapas = <Chapa>[ChapaCircular(5), ChapaRectangular(4, 7)];
  for (final c in chapas) {
    c.describir();
  }
}
