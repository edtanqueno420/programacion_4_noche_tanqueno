class LineaProduccion {
  final String nombre;
  final String codigo;
  final int capacidad;
  final bool activa;

  LineaProduccion({
    required this.nombre,
    required this.codigo,
    required this.capacidad,
    this.activa = false,
  });

  LineaProduccion.standby()
      : nombre = 'Linea de reserva',
        codigo = 'LR-000',
        capacidad = 100,
        activa = false;

  LineaProduccion.principal({required this.nombre, required this.codigo})
      : capacidad = 500,
        activa = true;

  factory LineaProduccion.desdeCodigo(String codigo) {
    final partes = codigo.split('-');
    return LineaProduccion(
      nombre: 'Linea ${partes[1]}',
      codigo: codigo,
      capacidad: 300,
      activa: partes[1] == '01',
    );
  }

  @override
  String toString() =>
      '${activa ? "ACTIVA" : "INACTIVA"} - $nombre ($codigo) cap: $capacidad';
}

void main() {
  final l1 = LineaProduccion(nombre: 'Linea Principal', codigo: 'LP-001', capacidad: 500);
  final l2 = LineaProduccion.standby();
  final l3 = LineaProduccion.principal(nombre: 'Linea Norte', codigo: 'LN-001');
  final l4 = LineaProduccion.desdeCodigo('LN-02');

  print(l1);
  print(l2);
  print(l3);
  print(l4);
}
