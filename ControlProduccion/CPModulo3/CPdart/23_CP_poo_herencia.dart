class MaquinaBase {
  final String nombre;
  final int anios;

  MaquinaBase(this.nombre, this.anios);

  String operar() => '...';

  void presentarse() {
    print('Soy $nombre, tengo $anios años de servicio y opero: ${operar()}');
  }
}

class Torno extends MaquinaBase {
  Torno(super.nombre, super.anios);

  @override
  String operar() => 'Tornería de precisión';

  void taladrar() => print('$nombre taladrando pieza');
}

class Fresa extends MaquinaBase {
  Fresa(super.nombre, super.anios);

  @override
  String operar() => 'Fresado de superficies';

  void rectificar() => print('$nombre rectificando superficie');
}

void main() {
  final torno = Torno('Torno CNC-01', 3);
  final fresa = Fresa('Fresa Universal-02', 5);

  torno.presentarse();
  fresa.presentarse();

  torno.taladrar();
  fresa.rectificar();
}
