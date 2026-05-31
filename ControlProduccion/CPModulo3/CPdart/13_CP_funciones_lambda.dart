void main() {
  final cuadrado = (int n) => n * n;
  print(cuadrado(7));

  final calcularCosto = (double precioBase, double pct) {
    final recargo = precioBase * (pct / 100);
    return precioBase + recargo;
  };
  print(calcularCosto(100.0, 15.0));

  final lotes = [3, 1, 4, 1, 5, 9, 2, 6];
  lotes.sort((a, b) => b.compareTo(a));
  print(lotes);
}
