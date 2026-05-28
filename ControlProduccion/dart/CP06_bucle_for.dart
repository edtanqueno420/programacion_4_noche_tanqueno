void main() {
  for (int i = 0; i < 5; i++) {
    print('Lote ${i + 1} terminado');
  }

  for (int i = 0; i <= 100; i += 25) {
    print('Progreso: $i%');
  }

  for (int i = 5; i >= 1; i--) {
    print('Cuenta regresiva: $i');
  }
}
