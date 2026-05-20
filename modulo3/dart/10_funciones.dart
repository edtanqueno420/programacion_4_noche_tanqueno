//Funcion sin parametros y sin retorno
void saludar() {
  print('hola mundo');
}

//Funcion con parametros y sin retorno
void saludoConParametro(String nombre) {
  print('hola $nombre');
}

//Funcion sin parametros y con retorno
int obtenerNumero() {
  return 10;
}

//Funcion con parametros y con retorno
int sumar(int a, int b) {
  return a + b;
}

//Funcion flecha (arrow function)
int multiplicar(int a, int b) {
  return a * b;
}

//Funcion con parametros opcionales
void funcionConParametrosOpcionales(String nombre, [String apellido='Sin apellido'] ) {
  print('hola $nombre $apellido');
}

//Funcion con parametros nombrados 
void registroUsuario({required String nombre, required String apellido, required int edad}) {
  print('hola $nombre $apellido, tienes $edad años');
}

void main() {
  saludar();
  saludoConParametro('Pedro Perez');
  int numero = obtenerNumero();
  print(numero);
  print('el numero es: ${obtenerNumero()}');
  print('la suma es: ${sumar(5,5)}');
  print('la multiplicacion es : ${multiplicar(5,5)}');
}