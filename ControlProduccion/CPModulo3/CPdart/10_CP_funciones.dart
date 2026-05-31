void saludar() {
  print('Iniciando control de producción');
}

void saludoConParametro(String linea) {
  print('Línea $linea');
}

int obtenerLotes() {
  return 10;
}

int sumarPiezas(int a, int b) {
  return a + b;
}

int multiplicarLotes(int a, int b) {
  return a * b;
}

void funcionConParametrosOpcionales(String producto, [String codigo = 'Sin código']) {
  print('Producto $producto - $codigo');
}

void registroLote({required String producto, required int cantidad, required int lote}) {
  print('$producto Lote N°$lote - $cantidad piezas');
}

String formatearCosto(double costo) => '\$${costo.toStringAsFixed(2)}';

formatearCostoSinTipo(double costo) => '\$${costo.toStringAsFixed(2)}';

void main() {
  saludar();
  saludoConParametro('L01');
  int num = obtenerLotes();
  print(num);
  print('Total piezas: ${sumarPiezas(150, 200)}');
  print('Producción total: ${multiplicarLotes(4, 500)}');
}
