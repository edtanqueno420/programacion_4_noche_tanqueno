import 'package:flutter/material.dart';
import 'widgets/CP_catalogo_basico.dart';
import 'widgets/CP_etiqueta.dart';
import 'widgets/CP_servicio_estado.dart';
import 'widgets/CP_contador_limitado.dart';
import 'widgets/CP_reloj.dart';
import 'widgets/CP_pantalla_contexto.dart';
import 'widgets/CP_indicador.dart';

const int paso = 8;

void main() => runApp(
  MaterialApp(
    debugShowCheckedModeBanner: false,
    theme: ThemeData(
      useMaterial3: true,
      colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple, brightness: Brightness.dark),
      brightness: Brightness.dark,
    ),
    home: switch (paso) {
      1 => const Scaffold(body: Center(child: Saludo())),
      2 => const CatalogoBasicos(),
      3 => const Scaffold(
        body: Center(
          child: Wrap(
            spacing: 12,
            runSpacing: 8,
            children: [
              Etiqueta(texto: 'Operativa', color: Colors.green),
              Etiqueta(texto: 'Fallo', color: Colors.red, relleno: true),
              Etiqueta(texto: 'En mantenimiento', color: Colors.orange),
              Etiqueta(
                texto: 'Critico',
                color: Colors.red,
                fontSize: 16,
                relleno: true,
              ),
              Etiqueta(texto: 'Info', color: Colors.blue, fontSize: 11),
            ],
          ),
        ),
      ),
      4 => const Scaffold(
        body: Center(child: ServicioEstado(nombre: 'Linea de Produccion A')),
      ),
      5 => Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ContadorLimitado(
                etiqueta: 'Ordenes de Produccion',
                limite: 3,
                color: Colors.red,
                onLimite: () => debugPrint('Capacidad maxima alcanzada!'),
              ),
              const SizedBox(height: 40),
              ContadorLimitado(
                etiqueta: 'Maquinas Activas',
                limite: 10,
                color: Colors.indigo,
              ),
            ],
          ),
        ),
      ),
      6 => Scaffold(
        appBar: AppBar(title: const Text('Tiempo de Ciclo')),
        body: const Center(child: Reloj()),
      ),
      7 => const PantallaContexto(),
      _ => Scaffold(
        body: Center(child: Text('Paso $paso: crea el widget primero')),
      ),
      8 => Scaffold(
        body: Center(
          child: Wrap(
            spacing: 32,
            runSpacing: 24,
            alignment: WrapAlignment.center,
            children: const [
              Indicador(
                label: 'Lineas Activas',
                valor: '8',
                color: Colors.green,
                icono: Icons.dns,
              ),
              Indicador(
                label: 'Fallos Criticos',
                valor: '2',
                color: Colors.red,
                icono: Icons.warning_amber,
                subtitulo: 'Requieren atencion',
              ),
              Indicador(
                label: 'Produccion',
                valor: '4.2 Ton',
                color: Colors.indigo,
              ),
              Indicador(
                label: 'Eficiencia',
                valor: '99.8%',
                color: Colors.teal,
                subtitulo: 'Ultimos 30 dias',
              ),
            ],
          ),
        ),
      ),
      int() => throw UnimplementedError(),
    },
  ),
);

class Saludo extends StatelessWidget {
  const Saludo({super.key});
  @override
  Widget build(BuildContext context) =>
      const Text('Control de Produccion', style: TextStyle(fontSize: 32));
}