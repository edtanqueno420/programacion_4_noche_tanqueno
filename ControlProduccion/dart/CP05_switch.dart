void main() {
  String codigoPieza = 'E-404';

  switch (codigoPieza) {
    case 'E-200':
      print('Engranaje recto');
    case 'E-201':
      print('Engranaje helicoidal');
    case 'T-300':
      print('Tornillo M8');
    case 'T-301':
      print('Tornillo M10');
    case 'E-404':
      print('Eje de transmision');
    case 'S-500':
      print('Soporte ajustable');
    default:
      print('Pieza desconocida');
  }
}

void main2() {
  String codigoPieza = 'E-404';

  String descripcion = switch (codigoPieza) {
    'E-200' => 'Engranaje recto - 15mm',
    'E-201' => 'Engranaje helicoidal - 20mm',
    'E-202' => 'Engranaje conico - 18mm',
    'T-300' => 'Tornillo M8 - acero inoxidable',
    'T-301' => 'Tornillo M10 - acero templado',
    'E-404' => 'Eje de transmision - 50cm',
    'S-500' => 'Soporte ajustable - hierro fundido',
    _ => 'Codigo de pieza desconocido',
  };

  print(descripcion);
}
