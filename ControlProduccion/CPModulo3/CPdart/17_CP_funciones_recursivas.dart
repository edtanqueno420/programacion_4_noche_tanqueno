int factorial(int n) {
  if (n <= 1) return 1;
  return n * factorial(n - 1);
}

int fibonacci(int n) {
  if (n <= 1) return n;
  return fibonacci(n - 1) + fibonacci(n - 2);
}

int contarSubcategorias(Map<String, dynamic> categoria) {
  int total = 0;
  for (final entrada in categoria.entries) {
    if (entrada.value is Map) {
      total += contarSubcategorias(entrada.value as Map<String, dynamic>);
    } else {
      total++;
    }
  }
  return total;
}

void main() {
  print(factorial(6));
  print(fibonacci(10));

  final categoriasProducto = {
    'Fijación': {
      'Tornillos': {'M8': true, 'M10': true, 'M12': true},
      'Tuercas': {'M8': true, 'M10': true},
      'Arandelas': {'Planas': true, 'Grower': true},
    },
    'Transmisión': {'Engranajes': true, 'Correas': true},
    'Hidráulico': {'Bombas': true},
  };

  print('Total de subcategorías: ${contarSubcategorias(categoriasProducto)}');
}
