class Inventario {
  final String material;
  double _cantidad;

  Inventario(this.material, double cantidadInicial)
      : _cantidad = cantidadInicial;

  double get cantidad => _cantidad;

  void agregar(double monto) {
    if (monto <= 0) throw ArgumentError('La cantidad debe ser positiva');
    _cantidad += monto;
    print('Ingreso de $monto kg de $material. Stock: $_cantidad kg');
  }

  void retirar(double monto) {
    if (monto <= 0) throw ArgumentError('La cantidad debe ser positiva');
    if (monto > _cantidad) throw StateError('Stock insuficiente');
    _cantidad -= monto;
    print('Retiro de $monto kg de $material. Stock: $_cantidad kg');
  }
}

void main() {
  final inv = Inventario('Acero', 500.0);

  inv.agregar(200.0);
  inv.retirar(150.0);
  print(inv.cantidad);
}
