void saludar() {
  print('Bienvenido al sistema de produccion');
}

void saludoConParametro(String nombre) {
  print('Bienvenido $nombre');
}

int obtenerPiezas() {
  return 100;
}

int sumarProduccion(int a, int b) {
  return a + b;
}

int multiplicarPiezas(int a, int b) {
  return a * b;
}

void funcionConParametrosOpcionales(String nombre, [String turno = 'Matutino']) {
  print('$nombre - Turno: $turno');
}

void registroOperario({required String nombre, required String turno, required int piezas}) {
  print('$nombre - Turno: $turno - Piezas: $piezas');
}

String formatearPeso(double peso) => '${peso.toStringAsFixed(2)} kg';

formatearPesoSinTipo(double peso) => '${peso.toStringAsFixed(2)} kg';

void main() {
  saludar();
  saludoConParametro('Juan Perez');
  int numero = obtenerPiezas();
  print(numero);
  print('Total: ${obtenerPiezas()}');
  print('Suma: ${sumarProduccion(150, 80)}');
  print('Produccion total: ${multiplicarPiezas(100, 5)}');
}
