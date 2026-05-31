import 'dart:io';

Future<String> consultarEstadoProduccion() async {
  await Future.delayed(Duration(milliseconds: 200));
  return 'RUN';
}

void main() async {
  print('Consultando estado de producción...');
  final estado = await consultarEstadoProduccion();
  print('Estado de máquina: $estado');
  print('Consulta completada');
}
