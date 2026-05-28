void main() {
  final material = 'Acero';
  final cantidad = 500;

  print('Material: $material');
  print('${material.toUpperCase()} - ${cantidad + 100} unidades disponibles');

  final orden = '''
Material: $material
Cantidad: $cantidad
Disponible: ${cantidad >= 100 ? 'Si' : 'No'}
  ''';
  print(orden);

  final ruta = r'C:\Fabrica\Produccion';

  final saludo = 'Produccion de ' + material;

  print('produccion'.toUpperCase());
  print('  Produccion  '.trim());
  print('Produccion'.contains('ucc'));
  print('Produccion'.replaceAll('o', 'O'));
  print('acero,aluminio,cobre'.split(','));
  print('Produccion'.substring(0, 6));
  print('Produccion'.startsWith('Prod'));
  print('abc'.padLeft(5, '0'));
}
