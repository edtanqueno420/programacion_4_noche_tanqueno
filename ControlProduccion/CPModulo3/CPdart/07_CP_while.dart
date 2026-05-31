void main() {
  int piezas = 0;
  int stock = 500;

  while (stock > 0) {
    final lote = stock > 100 ? 100 : stock;
    piezas++;
    stock -= lote;
    print('Lote $piezas: $lote piezas (stock restante: $stock)');
  }

  int intentos = 0;
  bool maquinaEncendida = false;

  do {
    intentos++;
    print('Intento de arranque #$intentos...');
    if (intentos == 3) maquinaEncendida = true;
  } while (!maquinaEncendida && intentos < 5);

  print(maquinaEncendida
      ? 'Máquina arrancada tras $intentos intentos'
      : 'No se pudo arrancar la máquina');
}
