class Maquina {
  final String id;
  final String nombre;
  String ip;
  bool _encendido = false;

  Maquina({
    required this.id,
    required this.nombre,
    required this.ip,
  });

  bool get encendido => _encendido;
  String get estado => _encendido ? 'activo' : 'inactivo';

  set estadoEncendido(bool valor) {
    _encendido = valor;
    print('$nombre: ${valor ? "encendido" : "apagado"}');
  }

  void conectar() {
    _encendido = true;
    print('$nombre conectada en $ip');
  }

  void desconectar() {
    _encendido = false;
    print('$nombre desconectada');
  }

  String resumen() => 'ID: $id | Nombre: $nombre | IP: $ip | Estado: $estado';

  @override
  String toString() => 'Maquina($nombre, $ip, $estado)';
}

void main() {
  final torno = Maquina(
    id: 'M-001',
    nombre: 'Torno CNC-3000',
    ip: '192.168.1.100',
  );

  torno.conectar();
  print(torno.estado);
  print(torno.resumen());
  print(torno);

  torno.estadoEncendido = false;
  print(torno.encendido);
}
