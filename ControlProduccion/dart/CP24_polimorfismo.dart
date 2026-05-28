abstract class Componente {
  String get nombre;
  double calcularPeso();
}

class EngranajePoli extends Componente {
  final double radio;
  EngranajePoli(this.radio);
  @override String get nombre => 'Engranaje';
  @override double calcularPeso() => 3.1416 * radio * radio * 0.00785;
}

class EjePoli extends Componente {
  final double largo, diametro;
  EjePoli(this.largo, this.diametro);
  @override String get nombre => 'Eje';
  @override double calcularPeso() => largo * diametro * 0.006;
}

class SoportePoli extends Componente {
  final double base, altura;
  SoportePoli(this.base, this.altura);
  @override String get nombre => 'Soporte';
  @override double calcularPeso() => (base * altura) / 2 * 0.008;
}

void imprimirPeso(Componente componente) {
  print('${componente.nombre}: ${componente.calcularPeso().toStringAsFixed(3)} kg');
}

void main() {
  final componentes = <Componente>[
    EngranajePoli(4),
    EjePoli(50, 2),
    SoportePoli(6, 3),
  ];

  for (final c in componentes) {
    imprimirPeso(c);
  }

  final masPesado = componentes.reduce(
      (a, b) => a.calcularPeso() > b.calcularPeso() ? a : b);
  print('\nComponente mas pesado: ${masPesado.nombre}');
}
