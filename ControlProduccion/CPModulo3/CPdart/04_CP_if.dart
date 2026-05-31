void main() {
  double temperatura = 85;

  if (temperatura > 90) {
    print('Sobrecalentamiento');
  } else if (temperatura > 70) {
    print('Temperatura normal');
  } else {
    print('Temperatura baja');
  }

  String estado = temperatura > 90 ? 'Con sobrecalentamiento' : 'Sin sobrecalentamiento';
  print(estado);

  String? pieza;
  String display = pieza != null ? pieza.toUpperCase() : 'Sin pieza';

  String display2 = pieza?.toUpperCase() ?? 'Sin pieza';
  print(display2);
}
