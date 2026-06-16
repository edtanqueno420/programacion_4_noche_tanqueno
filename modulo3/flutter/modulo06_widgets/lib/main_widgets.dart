// lib/main.dart
import 'package:flutter/material.dart';

void main() => runApp(const MaterialApp(
  home: Scaffold(body: Center
  (child: SizedBox(width: double.infinity, child: Saludo()))),
));

class Saludo extends StatelessWidget {
  const Saludo({super.key});

  @override
  Widget build(BuildContext context) {   // describe cómo se ve
    return const Text('Hola Flutter',textAlign: TextAlign.left,
    style: TextStyle(
      fontSize: 32,
      fontWeight: FontWeight.bold,
      letterSpacing: 4,
      color: Colors.deepOrange,
      shadows: [Shadow(color: Colors.black26, blurRadius: 4, offset: Offset(2,2))
      ],
      ),
      maxLines: 1,
      );
  }
}