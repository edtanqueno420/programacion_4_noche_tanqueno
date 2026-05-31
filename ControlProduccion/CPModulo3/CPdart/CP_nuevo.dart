import 'dart:io';

void main() {
  int minutos = 1;
  int total = 0;
  int piezas = 0;

  while (minutos > 0) {
    print("Ingrese minutos por pieza:");
    minutos = int.parse(stdin.readLineSync()!);

    if (minutos > 0) {
      total = total + minutos;
      piezas = piezas + 1;
      if (minutos < 5) {
        print("Producción rápida");
      } else if (minutos <= 15) {
        print("Producción normal");
      } else {
        print("Producción lenta");
      }
    }
  }
  print("Total minutos: $total");
  print("Piezas: $piezas");
  print("Promedio: ${total / piezas}");

  int muebles = 1;
  int total1 = 0;
  int trabajadores = 0;

  while (muebles > 0) {
    print("Ingrese cantidad de muebles:");
    muebles = int.parse(stdin.readLineSync()!);

    if (muebles > 0) {
      total1 = total1 + muebles;
      trabajadores = trabajadores + 1;

      if (muebles < 3) {
        print("Producción baja");
      } else if (muebles <= 7) {
        print("Producción normal");
      } else {
        print("Producción alta");
      }
    }
  }

  print("Total de muebles fabricados: $total1");
  print("Cantidad de trabajadores: $trabajadores");
  print("Promedio: ${total / trabajadores}");

  int tablas = 1;
  int total2 = 0;
  int operarios = 0;

  while (tablas > 0) {
    print("Ingrese cantidad de tablas cortadas:");
    tablas = int.parse(stdin.readLineSync()!);

    if (tablas > 0) {
      total2 = total2 + tablas;
      operarios = operarios + 1;

      if (tablas < 15) {
        print("Trabajo lento");
      } else if (tablas <= 40) {
        print("Trabajo eficiente");
      } else {
        print("Trabajo sobresaliente");
      }
    }
  }

  print("Total de tablas cortadas: $total2");
  print("Cantidad de operarios registrados: $operarios");
  print("Promedio: ${total2 / operarios}");

  int horas = 1;
  int pacientes;
  int totalPiezas = 0;
  int operarios2 = 0;

  while (horas > 0) {
    print("Ingrese horas trabajadas:");
    horas = int.parse(stdin.readLineSync()!);

    if (horas > 0) {
      print("Ingrese cantidad de piezas producidas:");
      pacientes = int.parse(stdin.readLineSync()!);

      double porHora = pacientes / horas;

      totalPiezas = totalPiezas + pacientes;
      operarios2 = operarios2 + 1;

      if (porHora < 5) {
        print("Ritmo lento");
      } else if (porHora <= 15) {
        print("Ritmo normal");
      } else {
        print("Ritmo rápido");
      }
    }
  }

  print("Total de piezas producidas: $totalPiezas");
  print("Cantidad de operarios registrados: $operarios2");
  print("Promedio de piezas por operario: ${totalPiezas / operarios2}");

  int lotes = 1;
  int pasajeros;
  int retraso;

  int totalPasajeros = 0;
  int totalRetraso = 0;
  int supervisores = 0;
  double totalRetrasoLote = 0;

  while (lotes > 0) {
    print("Ingrese cantidad de lotes:");
    lotes = int.parse(stdin.readLineSync()!);

    if (lotes > 0) {
      print("Ingrese cantidad de piezas:");
      pasajeros = int.parse(stdin.readLineSync()!);

      print("Ingrese minutos de retraso:");
      retraso = int.parse(stdin.readLineSync()!);

      double piezasLote = pasajeros / lotes;
      double retrasoLote = retraso / lotes;

      totalPasajeros = totalPasajeros + pasajeros;
      totalRetraso = totalRetraso + retraso;
      totalRetrasoLote = totalRetrasoLote + retrasoLote;
      supervisores = supervisores + 1;

      if (piezasLote < 30) {
        print("Baja eficiencia");
      } else if (piezasLote <= 80) {
        print("Eficiencia normal");
      } else {
        print("Alta eficiencia");
      }
    }
  }

  print("Total de piezas procesadas: $totalPasajeros");
  print("Total de minutos de retraso: $totalRetraso");
  print("Cantidad de supervisores registrados: $supervisores");
  print("Promedio de piezas por supervisor: ${totalPasajeros / supervisores}");
  print("Promedio general de retraso por lote: ${totalRetrasoLote / supervisores}");

  int totalVentas = 0;
  double promedioVentas = 0;

  for (int dia = 1; dia <= 7; dia++) {
    print("Ingrese piezas del día $dia:");
    int ventas = int.parse(stdin.readLineSync()!);

    totalVentas = totalVentas + ventas;
  }

  promedioVentas = totalVentas / 7;

  print("Total de piezas: $totalVentas");
  print("Promedio de piezas: $promedioVentas");

  if (promedioVentas < 30) {
    print("Baja producción");
  } else if (promedioVentas <= 80) {
    print("Producción normal");
  } else {
    print("Alta producción");
  }

  int totalKm = 0;

  for (int viaje = 1; viaje <= 5; viaje++) {
    print("Ingrese kilómetros del recorrido $viaje:");
    int km = int.parse(stdin.readLineSync()!);

    totalKm = totalKm + km;

    if (km < 50) {
      print("Recorrido corto");
    } else if (km <= 150) {
      print("Recorrido medio");
    } else {
      print("Recorrido largo");
    }
  }

  double combustible = totalKm / 12;
  double promedio = totalKm / 5;

  print("Total de kilómetros: $totalKm");
  print("Total de combustible estimado: $combustible litros");
  print("Promedio de kilómetros por recorrido: $promedio");
}
