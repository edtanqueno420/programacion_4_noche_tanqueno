class Lote {
  final String codigoProducto;
  final int cantidad;
  final String linea;
  final bool esPrioritario;

  Lote({
    required this.codigoProducto,
    required this.cantidad,
    required this.linea,
    this.esPrioritario = false,
  });

  Lote.standar()
      : codigoProducto = 'TOR-M8',
        cantidad = 100,
        linea = 'L01',
        esPrioritario = false;

  Lote.urgente({required this.codigoProducto, required this.cantidad})
      : linea = 'L00',
        esPrioritario = true;

  factory Lote.desdePedido(String pedido) {
    final partes = pedido.split('|');
    return Lote(
      codigoProducto: partes[0],
      cantidad: int.parse(partes[1]),
      linea: partes.length > 2 ? partes[2] : 'L99',
      esPrioritario: false,
    );
  }

  @override
  String toString() =>
      '${esPrioritario ? "URGENTE" : "Normal"} | $codigoProducto x $cantidad en $linea';
}

void main() {
  final l1 = Lote(codigoProducto: 'FRE-E4', cantidad: 50, linea: 'L02');
  final l2 = Lote.standar();
  final l3 = Lote.urgente(codigoProducto: 'RECT-P1', cantidad: 20);
  final l4 = Lote.desdePedido('TOR-M10|200|L03');

  print(l1);
  print(l2);
  print(l3);
  print(l4);
}
