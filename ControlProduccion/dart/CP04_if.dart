void main() {
  int temperatura = 120;

  if (temperatura > 200) {
    print('Sobrecalentamiento');
  } else if (temperatura > 80) {
    print('Operacion normal');
  } else {
    print('Temperatura baja');
  }

  String estado = temperatura > 100 ? 'Caliente' : 'Frio';
  print(estado);

  String? material;
  String display = material != null ? material.toUpperCase() : 'Sin material';

  String display2 = material?.toUpperCase() ?? 'Sin material';
  print(display2);
}
