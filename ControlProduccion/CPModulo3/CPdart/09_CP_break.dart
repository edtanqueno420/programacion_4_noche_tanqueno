void main() {
  final piezas = [12.5, 15.0, 14.2, -1.0, 13.8, 16.1, -1.0, 12.0];

  print('=== Inspeccionando con continue ===');
  for (final pieza in piezas) {
    if (pieza < 0) {
      print('Pieza defectuosa ignorada');
      continue;
    }
    print('Pieza de ${pieza} mm procesada');
  }

  print('\n=== Inspeccionando con break ===');
  for (final pieza in piezas) {
    if (pieza < 0) {
      print('Error crítico — deteniendo línea de producción');
      break;
    }
    print('Pieza de ${pieza} mm procesada');
  }
}
