class Maquina {
  final String id;
  final String nombre;
  int velocidadRPM;
  bool _encendida = false;

  Maquina({
    required this.id,
    required this.nombre,
    required this.velocidadRPM,
  });

  bool get encendida => _encendida;
  String get estado => _encendida ? 'activa' : 'inactiva';

  set estadoEncendido(bool valor) {
    _encendida = valor;
    print('$nombre: ${valor ? "encendida" : "apagada"}');
  }

  void arrancar() {
    _encendida = true;
    print('$nombre arrancada a ${velocidadRPM}RPM');
  }

  void detener() {
    _encendida = false;
    print('$nombre detenida');
  }

  String resumen() => 'ID: $id | Nombre: $nombre | RPM: $velocidadRPM | Estado: $estado';

  @override
  String toString() => 'Maquina($nombre, ${velocidadRPM}RPM, $estado)';
}

void main() {
  final torno = Maquina(
    id: 'TOR-001',
    nombre: 'Torno CNC Principal',
    velocidadRPM: 1500,
  );

  torno.arrancar();
  print(torno.estado);
  print(torno.resumen());
  print(torno);

  torno.estadoEncendido = false;
  print(torno.encendida);
}
