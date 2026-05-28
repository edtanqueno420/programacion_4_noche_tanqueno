void main() {
  int piezas = 150;
  double peso = piezas.toDouble();
  String texto = piezas.toString();

  int lote = int.parse('4501');
  double temperatura = double.parse('98.5');

  int? codigo = int.tryParse('abc');
  double? medida = double.tryParse('99');

  Object valor = 'maquina';
  if (valor is String) {
    print(valor.length);
  }

  Object obj = 'Torno';
  String str = obj as String;

  String? turno = null;
  int longitud = turno?.length ?? 0;
  print(longitud);

  print(double.infinity);
  print(double.nan);
  print(double.maxFinite);
}
