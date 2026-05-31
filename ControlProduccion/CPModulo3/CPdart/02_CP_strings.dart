void main() {
  final producto = 'Tornillo';
  final lote = 45;

  print('Producto: $producto');
  print('${producto.toUpperCase()} - Lote N° ${lote + 100}');

  final orden = '''
Producto: $producto
Lote: $lote
Calidad: ${lote >= 50 ? 'Aprobado' : 'Pendiente'}
  ''';
  print(orden);

  final ruta = r'C:\Fabrica\Planos\Tornillo_M8.dxf';
  print(ruta);

  final descripcion = 'Código: ' + codigoProducto();

  print('pieza mecanizada'.toUpperCase());
  print('  Engranaje  '.trim());
  print('Engranaje'.contains('grane'));
  print('Engranaje'.replaceAll('e', 'E'));
  print('torno,fresa,rectificadora'.split(','));
  print('Engranaje'.substring(0, 5));
  print('Engranaje'.startsWith('Eng'));
  print('123'.padLeft(6, '0'));
}

String codigoProducto() => 'TOR-M8-001';
