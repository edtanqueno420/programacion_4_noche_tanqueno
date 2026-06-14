class Inventario {
  final String producto;
  double _stock;

  Inventario(this.producto, double stockInicial)
      : _stock = stockInicial;

  double get stock => _stock;

  void ingresar(double cantidad) {
    if (cantidad <= 0) throw ArgumentError('La cantidad debe ser positiva');
    _stock += cantidad;
    print('Ingreso de $cantidad. Stock actual de $producto: $_stock');
  }

  void retirar(double cantidad) {
    if (cantidad <= 0) throw ArgumentError('La cantidad debe ser positiva');
    if (cantidad > _stock) throw StateError('Stock insuficiente');
    _stock -= cantidad;
    print('Retiro de $cantidad. Stock actual de $producto: $_stock');
  }
}

void main() {
  final inv = Inventario('Tornillo M8', 500.0);

  inv.ingresar(200.0);
  inv.retirar(150.0);
  print(inv.stock);
}
