void main() {
  final cuadrado = (int n) => n * n;
  print(cuadrado(7));

  final calcularDescuento = (double precio, double pct) {
    final descuento = precio * (pct / 100);
    return precio - descuento;
  };
  print(calcularDescuento(1500.0, 10.0));

  final lotes = [30, 10, 40, 10, 50, 90, 20, 60];
  lotes.sort((a, b) => b.compareTo(a));
  print(lotes);
}
