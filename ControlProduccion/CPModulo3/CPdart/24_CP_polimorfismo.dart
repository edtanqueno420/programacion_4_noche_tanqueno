abstract class FiguraMecanica {
  String get nombre;
  double calcularArea();
}

class CuadradoMec extends FiguraMecanica {
  final double lado;
  CuadradoMec(this.lado);
  @override String get nombre => 'Cuadrado';
  @override double calcularArea() => lado * lado;
}

class TrianguloMec extends FiguraMecanica {
  final double base, altura;
  TrianguloMec(this.base, this.altura);
  @override String get nombre => 'Triángulo';
  @override double calcularArea() => (base * altura) / 2;
}

class CirculoMec extends FiguraMecanica {
  final double radio;
  CirculoMec(this.radio);
  @override String get nombre => 'Círculo';
  @override double calcularArea() => 3.1416 * radio * radio;
}

void imprimirArea(FiguraMecanica figura) {
  print('${figura.nombre}: ${figura.calcularArea().toStringAsFixed(2)} mm²');
}

void main() {
  final figuras = <FiguraMecanica>[
    CuadradoMec(4),
    TrianguloMec(6, 3),
    CirculoMec(5),
  ];

  for (final f in figuras) {
    imprimirArea(f);
  }

  final mayor = figuras.reduce((a, b) => a.calcularArea() > b.calcularArea() ? a : b);
  print('\nFigura más grande: ${mayor.nombre}');
}
