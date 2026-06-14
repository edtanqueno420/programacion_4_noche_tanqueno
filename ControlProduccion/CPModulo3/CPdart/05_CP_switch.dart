void main() {
  String estadoMaquina = 'STOP';

  switch (estadoMaquina) {
    case 'RUN':
      print('Máquina en funcionamiento');
    case 'IDLE':
      print('Máquina en espera');
    case 'STOP':
      print('Máquina detenida');
    case 'ALARM':
      print('Alarma activa');
    case 'MAINT':
      print('Mantenimiento en curso');
    default:
      print('Estado desconocido');
  }

  String estado = 'STOP';

  String descripcion = switch (estado) {
    'RUN' => 'Produciendo — operación normal',
    'IDLE' => 'En espera — sin carga de trabajo',
    'STOP' => 'Detenida — intervención requerida',
    'ALARM' => 'Alarma — error crítico detectado',
    'MAINT' => 'Mantenimiento — fuera de línea',
    _ => 'Estado de máquina desconocido',
  };

  print(descripcion);
}
