void main() {
  for (int i = 0; i < 5; i++) {
    print('Lote N° ${i + 1}');
  }

  for (int i = 0; i <= 100; i += 25) {
    print('Progreso de producción: $i%');
  }

  for (int i = 5; i >= 1; i--) {
    print('Minutos restantes de turno: $i');
  }
}
