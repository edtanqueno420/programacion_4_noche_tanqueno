String construirCodigo(String tipo, String numero, [int? version]) {
  if (version != null) {
    return '$tipo-$numero-v$version';
  }
  return '$tipo-$numero';
}

String construirCodigoV2(String tipo, String numero, [int version = 1]) {
  return '$tipo-$numero-v$version';
}

void configurarMaquina({
  required String maquinaId,
  required int velocidadRPM,
  bool refrigerante = true,
  int tiempoSeg = 30,
}) {
  final ref = refrigerante ? 'con refrigerante' : 'sin refrigerante';
  print('Máquina $maquinaId a ${velocidadRPM}RPM ($ref, tiempo: ${tiempoSeg}s)');
}

void main() {
  print(construirCodigo('TOR', 'M8'));
  print(construirCodigo('TOR', 'M10', 2));
  print(construirCodigoV2('FRE', 'E4'));

  configurarMaquina(
    maquinaId: 'CNC-04',
    velocidadRPM: 1500,
    refrigerante: true,
    tiempoSeg: 60,
  );

  configurarMaquina(
    maquinaId: 'FRESA-02',
    velocidadRPM: 800,
  );
}
