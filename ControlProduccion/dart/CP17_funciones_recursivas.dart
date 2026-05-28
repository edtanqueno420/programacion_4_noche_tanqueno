int factorial(int n) {
  if (n <= 1) return 1;
  return n * factorial(n - 1);
}

int fibonacci(int n) {
  if (n <= 1) return n;
  return fibonacci(n - 1) + fibonacci(n - 2);
}

int contarComponentes(Map<String, dynamic> ensamble) {
  int total = 0;
  for (final entrada in ensamble.entries) {
    if (entrada.value is Map) {
      total += contarComponentes(entrada.value as Map<String, dynamic>);
    } else {
      total++;
    }
  }
  return total;
}

void main() {
  print(factorial(6));
  print(fibonacci(10));

  final estructuraProducto = {
    'chasis': {
      'soldadura': {'viga_principal': true, 'viga_secundaria': true},
      'tornilleria': {'tornillo_m8': true, 'tuerca_m8': true},
    },
    'motor': {'bloque': true, 'piston': true, 'biela': true},
    'electronica': {'cableado': true, 'sensor': true, 'actuador': true},
  };

  print('Total de componentes: ${contarComponentes(estructuraProducto)}');
}
