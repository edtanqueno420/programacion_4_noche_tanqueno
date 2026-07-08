// lib/presentation/screens/catalog/orders_screen.dart

import 'package:flutter/material.dart';

class OrdersScreen extends StatelessWidget {
  const OrdersScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Pedidos')),
      body: const Center(child: Text('Tus pedidos aparecerán aquí')),
    );
  }
}
