class MaquinaIndustrial {
  final String nombre;
  final int antiguedadAnios;

  MaquinaIndustrial(this.nombre, this.antiguedadAnios);

  String hacerSonido() => '...';

  void presentarse() {
    print('Soy $nombre, tengo $antiguedadAnios años y sueno: ${hacerSonido()}');
  }
}

class Torno extends MaquinaIndustrial {
  Torno(super.nombre, super.antiguedadAnios);

  @override
  String hacerSonido() => 'Zzzzz!';

  void cortarMetal() => print('$nombre cortando metal');
}

class Prensa extends MaquinaIndustrial {
  Prensa(super.nombre, super.antiguedadAnios);

  @override
  String hacerSonido() => 'Pum!';

  void estampar() => print('$nombre estampando piezas');
}

void main() {
  final torno = Torno('Torno CNC', 3);
  final prensa = Prensa('Prensa Hidraulica', 5);

  torno.presentarse();
  prensa.presentarse();

  torno.cortarMetal();
  prensa.estampar();
}
