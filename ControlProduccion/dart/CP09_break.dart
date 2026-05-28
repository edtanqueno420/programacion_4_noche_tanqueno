void main() {
  final lotesProduccion = [100, 200, 500, -1, 300, 800, -1, 50];

  print('=== Procesando con continue ===');
  for (final lote in lotesProduccion) {
    if (lote < 0) {
      print('Lote defectuoso ignorado');
      continue;
    }
    print('Procesando lote de $lote piezas');
  }

  print('\n=== Procesando con break ===');
  for (final lote in lotesProduccion) {
    if (lote < 0) {
      print('Error critico - deteniendo produccion');
      break;
    }
    print('Procesando lote de $lote piezas');
  }
}
