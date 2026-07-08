// lib/presentation/screens/catalog/cart_screen.dart

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../providers/cart_provider.dart';

class CartScreen extends ConsumerWidget {
  const CartScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final cart = ref.watch(cartProvider);
    final notifier = ref.read(cartProvider.notifier);

    return Scaffold(
      appBar: AppBar(title: const Text('Carrito')),
      body: cart.items.isEmpty
          ? const Center(child: Text('Tu carrito está vacío'))
          : ListView(
              padding: const EdgeInsets.all(16),
              children: [
                ...cart.items.map((item) => Card(
                  child: ListTile(
                    title: Text(item.product.name),
                    subtitle: Text('S/ ${item.product.price.toStringAsFixed(2)}'),
                    trailing: SizedBox(
                      width: 140,
                      child: Row(
                        children: [
                          IconButton(icon: const Icon(Icons.remove), onPressed: () => notifier.updateQuantity(item.product.id, item.quantity - 1)),
                          Text('${item.quantity}'),
                          IconButton(icon: const Icon(Icons.add), onPressed: () => notifier.updateQuantity(item.product.id, item.quantity + 1)),
                        ],
                      ),
                    ),
                  ),
                )),
                const SizedBox(height: 16),
                Card(
                  child: Padding(
                    padding: const EdgeInsets.all(16),
                    child: Column(
                      children: [
                        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [const Text('Subtotal'), Text('S/ ${cart.subtotal.toStringAsFixed(2)}')]),
                        const SizedBox(height: 8),
                        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [const Text('Total con impuesto'), Text('S/ ${cart.totalWithTax.toStringAsFixed(2)}')]),
                        const SizedBox(height: 12),
                        FilledButton(onPressed: () {}, child: const Text('Continuar compra')),
                      ],
                    ),
                  ),
                ),
              ],
            ),
    );
  }
}
