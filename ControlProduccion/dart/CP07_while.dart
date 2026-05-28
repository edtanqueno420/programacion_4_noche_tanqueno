void main() {
  int piezasProcesadas = 0;
  int piezasRestantes = 100;

  while (piezasRestantes > 0) {
    final lote = piezasRestantes > 30 ? 30 : piezasRestantes;
    piezasProcesadas++;
    piezasRestantes -= lote;
    print('Lote $piezasProcesadas: $lote piezas (restante: $piezasRestantes)');
  }

  // do-while
  int intentos = 0;
  bool produccionIniciada = false;

  do {
    intentos++;
    print('Intento de inicio #$intentos...');
    if (intentos == 3) produccionIniciada = true;
  } while (!produccionIniciada && intentos < 5);

  print(produccionIniciada
      ? 'Produccion iniciada tras $intentos intentos'
      : 'No se pudo iniciar la produccion');
}
