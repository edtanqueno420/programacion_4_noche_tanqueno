import 'dart:io';

Future<String> obtenerEstadoProduccion() async {
  await Future.delayed(Duration(milliseconds: 200));
  return 'LINEA_ACTIVA';
}

void main() async {
  print('Consultando estado de produccion...');
  final estado = await obtenerEstadoProduccion();
  print('Estado: $estado');
  print('Consulta completada');
}
