void main() {
  int lotes = 10;
  double pesoTotal = lotes.toDouble();
  String texto = lotes.toString();

  int stock = int.parse('150');
  double medida = double.parse('12.75');

  int? stockSeguro = int.tryParse('abc');
  double? medidaSegura = double.tryParse('99');

  Object valor = 'pieza123';
  if (valor is String) {
    print(valor.length);
  }

  Object obj = 'Engranaje';
  String str = obj as String;

  String? codigoNullable = null;
  int longitud = codigoNullable?.length ?? 0;
  print(longitud);

  print(double.infinity);
  print(double.nan);
  print(double.maxFinite);
}
