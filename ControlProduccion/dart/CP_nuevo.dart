import 'dart:io';

void main() {

  /*EJERCICIO 1
  Una fabrica registra la cantidad de piezas producidas por cada maquina durante el dia.
  Realiza un programa en Dart que use readLineSync() y un ciclo while para ingresar las piezas producidas de cada maquina.
  Reglas de negocio:
  Menos de 50 piezas -> "Produccion baja"
  Entre 50 y 150 piezas -> "Produccion normal"
  Mas de 150 piezas -> "Produccion alta"
  El programa debe seguir solicitando datos mientras se ingresen valores mayores a 0.
  Cuando el usuario ingrese 0, mostrar:
  Total de piezas producidas
  Cantidad de maquinas registradas
  Promedio de piezas por maquina
  */

  int piezas = 1;
  int total = 0;
  int maquinas = 0;

  while (piezas > 0) {

    print("Ingrese piezas producidas:");
    piezas = int.parse(stdin.readLineSync()!);

    if (piezas > 0) {
      total = total + piezas;
      maquinas = maquinas + 1;
      if (piezas < 50) {
        print("Produccion baja");
      } else if (piezas <= 150) {
        print("Produccion normal");
      } else {
        print("Produccion alta");
      }
    }
  }
  print("Total piezas: $total");
  print("Maquinas: $maquinas");
  print("Promedio: ${total / maquinas}");


  /*EJERCICIO 2
  Una empresa registra la cantidad de productos ensamblados por cada trabajador durante la jornada.
  Realiza un programa en Dart que use readLineSync() y un ciclo while para ingresar la cantidad de productos ensamblados por empleado.
  Reglas de negocio:
  Menos de 5 productos -> "Rendimiento bajo"
  Entre 5 y 15 productos -> "Rendimiento normal"
  Mas de 15 productos -> "Rendimiento alto"
  El programa debe continuar solicitando datos mientras se ingresen valores mayores a 0.
  Cuando el usuario ingrese 0, mostrar:
  Total de productos ensamblados
  Cantidad de trabajadores registrados
  Promedio de productos por trabajador
  */
  int productos = 1;
  int total1 = 0;
  int trabajadores = 0;

  while (productos > 0) {

    print("Ingrese cantidad de productos ensamblados:");
    productos = int.parse(stdin.readLineSync()!);

    if (productos > 0) {
      total1 = total1 + productos;
      trabajadores = trabajadores + 1;

      if (productos < 5) {
        print("Rendimiento bajo");
      } else if (productos <= 15) {
        print("Rendimiento normal");
      } else {
        print("Rendimiento alto");
      }
    }
  }

  print("Total de productos ensamblados: $total1");
  print("Cantidad de trabajadores: $trabajadores");
  print("Promedio: ${total1 / trabajadores}");

  /*EJERCICIO 3
  Una empresa de manufactura controla las horas de operacion de cada maquina durante el dia.
  Realiza un programa en Dart que use readLineSync() y un ciclo while para ingresar las horas trabajadas por maquina.
  Reglas de negocio:
  Menos de 4 horas -> "Uso bajo"
  Entre 4 y 10 horas -> "Uso normal"
  Mas de 10 horas -> "Uso intensivo"
  El programa debe seguir solicitando datos mientras se ingresen valores mayores a 0.
  Cuando el usuario ingrese 0, mostrar:
  Total de horas de operacion
  Cantidad de maquinas registradas
  Promedio de horas por maquina
  */
  int horas = 1;
  int total2 = 0;
  int maquinasReg = 0;

  while (horas > 0) {

    print("Ingrese horas de operacion:");
    horas = int.parse(stdin.readLineSync()!);

    if (horas > 0) {
      total2 = total2 + horas;
      maquinasReg = maquinasReg + 1;

      if (horas < 4) {
        print("Uso bajo");
      } else if (horas <= 10) {
        print("Uso normal");
      } else {
        print("Uso intensivo");
      }
    }
  }

  print("Total de horas de operacion: $total2");
  print("Cantidad de maquinas registradas: $maquinasReg");
  print("Promedio: ${total2 / maquinasReg}");

  /*EJERCICIO 4
  Una planta registra la cantidad de materia prima procesada y las horas de operacion por cada linea.
  Realiza un programa en Dart que use readLineSync() y un ciclo while para ingresar:
  Horas de operacion
  Cantidad de materia prima procesada (kg)
  Dentro del ciclo, calcular la materia prima procesada por hora.
  Reglas de negocio:
  Menos de 20 kg/h -> "Procesamiento lento"
  Entre 20 y 50 kg/h -> "Procesamiento normal"
  Mas de 50 kg/h -> "Procesamiento rapido"
  El programa debe continuar mientras las horas de operacion sean mayores a 0.
  Al finalizar, mostrar:
  Total de materia prima procesada
  Cantidad de lineas registradas
  Promedio de kg por linea
  */

  int horasOp = 1;
  int kg;
  int totalKg = 0;
  int lineas = 0;

  while (horasOp > 0) {

    print("Ingrese horas de operacion:");
    horasOp = int.parse(stdin.readLineSync()!);

    if (horasOp > 0) {

      print("Ingrese kg de materia prima procesada:");
      kg = int.parse(stdin.readLineSync()!);

      double porHora = kg / horasOp;

      totalKg = totalKg + kg;
      lineas = lineas + 1;

      if (porHora < 20) {
        print("Procesamiento lento");
      } else if (porHora <= 50) {
        print("Procesamiento normal");
      } else {
        print("Procesamiento rapido");
      }
    }
  }

  print("Total de kg procesados: $totalKg");
  print("Cantidad de lineas registradas: $lineas");
  print("Promedio de kg por linea: ${totalKg / lineas}");

  /*EJERCICIO 5
  Una fabrica controla la operacion diaria de sus supervisores de linea, registrando lotes, piezas y tiempos muertos.
  Realiza un programa en Dart que use readLineSync() y un ciclo while para ingresar por cada supervisor:
  Cantidad de lotes supervisados
  Cantidad de piezas inspeccionadas
  Minutos totales de tiempo muerto
  Dentro del ciclo, calcular:
  Piezas por lote
  Indice de tiempo muerto por lote
  Reglas de negocio:
  Menos de 30 piezas por lote -> "Baja eficiencia"
  Entre 30 y 80 piezas por lote -> "Eficiencia normal"
  Mas de 80 piezas por lote -> "Alta eficiencia"
  El programa debe continuar mientras la cantidad de lotes sea mayor a 0.
  Al finalizar, mostrar:
  Total de piezas inspeccionadas
  Total de minutos de tiempo muerto acumulados
  Cantidad de supervisores registrados
  Promedio de piezas por supervisor
  Promedio general de tiempo muerto por lote
  */

  int lotes = 1;
  int piezasInspeccionadas;
  int tiempoMuerto;

  int totalPiezas = 0;
  int totalTiempoMuerto = 0;
  int supervisores = 0;
  double totalTiempoMuertoLote = 0;

  while (lotes > 0) {

    print("Ingrese cantidad de lotes:");
    lotes = int.parse(stdin.readLineSync()!);

    if (lotes > 0) {

      print("Ingrese cantidad de piezas inspeccionadas:");
      piezasInspeccionadas = int.parse(stdin.readLineSync()!);

      print("Ingrese minutos de tiempo muerto:");
      tiempoMuerto = int.parse(stdin.readLineSync()!);

      double piezasLote = piezasInspeccionadas / lotes;
      double tiempoMuertoLote = tiempoMuerto / lotes;

      totalPiezas = totalPiezas + piezasInspeccionadas;
      totalTiempoMuerto = totalTiempoMuerto + tiempoMuerto;
      totalTiempoMuertoLote = totalTiempoMuertoLote + tiempoMuertoLote;
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

  print("Total de piezas inspeccionadas: $totalPiezas");
  print("Total de minutos de tiempo muerto: $totalTiempoMuerto");
  print("Cantidad de supervisores registrados: $supervisores");
  print("Promedio de piezas por supervisor: ${totalPiezas / supervisores}");
  print("Promedio general de tiempo muerto por lote: ${totalTiempoMuertoLote / supervisores}");

  /*EJERCICIO 6
  Una planta registra la produccion diaria de una linea durante una semana para cada turno.
  Realiza un programa en Dart que use readLineSync() y un ciclo for para ingresar la produccion de 7 dias por cada turno.
  Dentro del ciclo, calcular:
  Total de produccion por turno
  Promedio diario de produccion
  Reglas de negocio:
  Menos de 100 unidades diarias -> "Bajo rendimiento"
  Entre 100 y 300 unidades diarias -> "Rendimiento normal"
  Mas de 300 unidades diarias -> "Alto rendimiento"
  Al finalizar cada turno, mostrar:
  Total de produccion
  Promedio de produccion
  Clasificacion de rendimiento
  */

  int totalProd = 0;
  double promedioProd = 0;

  for (int dia = 1; dia <= 7; dia++) {

    print("Ingrese produccion del dia $dia:");
    int produccion = int.parse(stdin.readLineSync()!);

    totalProd = totalProd + produccion;
  }

  promedioProd = totalProd / 7;

  print("Total de produccion: $totalProd");
  print("Promedio de produccion: $promedioProd");

  if (promedioProd < 100) {
    print("Bajo rendimiento");
  } else if (promedioProd <= 300) {
    print("Rendimiento normal");
  } else {
    print("Alto rendimiento");
  }

  /* EJERCICIO 7
  Una empresa de manufactura registra los costos de produccion de 5 ordenes de fabricacion.
  Realiza un programa en Dart que use readLineSync() y un ciclo for para ingresar el costo de cada orden.
  Dentro del ciclo, calcular:
  Total de costos acumulados
  Costo estimado de materiales (60% del costo total)
  Reglas de negocio:
  Menos de $500 por orden -> "Orden pequena"
  Entre $500 y $2000 por orden -> "Orden mediana"
  Mas de $2000 por orden -> "Orden grande"
  Al finalizar, mostrar:
  Total de costos acumulados
  Costo estimado de materiales
  Promedio de costo por orden
  */

  int totalCosto = 0;

  for (int orden = 1; orden <= 5; orden++) {

    print("Ingrese costo de la orden $orden:");
    int costo = int.parse(stdin.readLineSync()!);

    totalCosto = totalCosto + costo;

    if (costo < 500) {
      print("Orden pequena");
    } else if (costo <= 2000) {
      print("Orden mediana");
    } else {
      print("Orden grande");
    }
  }

  double materiales = totalCosto * 0.6;
  double promedio = totalCosto / 5;

  print("Total de costos: $totalCosto");
  print("Costo estimado de materiales: $materiales");
  print("Promedio de costo por orden: $promedio");

}
