import 'dart:async';
import 'package:flutter/material.dart';

class Reloj extends StatefulWidget {
  const Reloj({super.key});

  @override
  State<Reloj> createState() => _RelojState();
}

class _RelojState extends State<Reloj> {
  Timer? _timer;          // ahora nullable para practicar null safety
  int _segundos = 0;
  bool _pausado = false;
  int _vueltas = 0;
  final List<int> _tiemposVuelta = [];

  @override
  void initState() {
    super.initState();    // ← siempre primero
    _iniciarTimer();
  }

  void _iniciarTimer() {
    _timer = Timer.periodic(const Duration(milliseconds: 100), (_) {
      if (!mounted) return;   // ← protege setState en callbacks
      setState(() => _segundos++);
    });
  }

  void _togglePausa() {
    setState(() {
      _pausado = !_pausado;
      if (_pausado) {
        _timer?.cancel();      // pausa: cancela el timer actual si existe
      } else {
        _iniciarTimer();      // reanuda: crea un timer nuevo
      }
    });
  }

  @override
  void dispose() {
    // Comentando el cancel aquí provoca un warning de fuga de memoria cuando el widget se desmonta.
    _timer?.cancel();          // ← SIEMPRE liberar en dispose si el timer existe
    super.dispose();          // ← siempre al final
  }

  String get _formato {
    final h = _segundos ~/ 3600;
    final m = (_segundos % 3600) ~/ 60;
    final s = _segundos % 60;
    return '$h:${m.toString().padLeft(2, '0')}:${s.toString().padLeft(2, '0')}';
  }

  // Color cambia según el tiempo transcurrido
  Color get _colorTiempo {
    if (_segundos > 120) return Colors.deepPurple;
    if (_segundos > 60) return Colors.red;
    if (_segundos > 30) return Colors.orange;
    return Colors.green;
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text(
          _formato,
          style: TextStyle(
            fontSize: 40,
            fontFamily: 'monospace',
            fontWeight: FontWeight.bold,
            color: _colorTiempo, // cambia automáticamente con el tiempo
          ),
        ),
        const SizedBox(height: 16),
        Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            FilledButton.icon(
              onPressed: _togglePausa,
              icon: Icon(_pausado ? Icons.play_arrow : Icons.pause),
              label: Text(_pausado ? 'Reanudar' : 'Pausar'),
            ),
            const SizedBox(width: 8),
            FilledButton.icon(
              onPressed: () => setState(() {
                _timer?.cancel();
                _segundos = 0;
                _pausado = false;
                _iniciarTimer();
                _vueltas = 0;
                _tiemposVuelta.clear();
              }),
              icon: const Icon(Icons.restart_alt),
              label: const Text('Reiniciar'),
            ),
            const SizedBox(width: 8),
            FilledButton.icon(
              onPressed: _pausado
                  ? null
                  : () {
                      setState(() {
                        _vueltas++;
                        _tiemposVuelta.add(_segundos);
                      });
                    },
              icon: const Icon(Icons.flag),
              label: const Text('Vuelta'),
            ),
          ],
        ),
        const SizedBox(height: 8),
        Text(
          _pausado ? 'Pausado' : 'Corriendo',
          style: TextStyle(fontSize: 12, color: Colors.grey.shade600),
        ),
        if (_tiemposVuelta.isNotEmpty) ...[
          const SizedBox(height: 12),
          Text(
            'Última vuelta: ${_tiemposVuelta.last} seg',
            style: const TextStyle(fontSize: 14, color: Colors.black87),
          ),
        ],
      ],
    );
  }
}