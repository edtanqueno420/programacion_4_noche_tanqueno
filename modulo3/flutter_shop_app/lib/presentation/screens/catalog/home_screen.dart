// lib/presentation/screens/catalog/home_screen.dart

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../providers/catalog_provider.dart';
import '../../widgets/product_card.dart';
import 'product_detail_screen.dart';

class HomeScreen extends ConsumerWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final catalog = ref.watch(catalogProvider);
    final categoriesAsync = ref.watch(categoriesProvider);

    return Scaffold(
      appBar: AppBar(title: const Text('Inicio')),
      body: RefreshIndicator(
        onRefresh: () async => ref.read(catalogProvider.notifier).refresh(),
        child: ListView(
          padding: const EdgeInsets.all(16),
          children: [
            Text('Catálogo', style: Theme.of(context).textTheme.headlineSmall),
            const SizedBox(height: 8),
            Text('Descubre productos destacados y gestiona tu carrito.', style: Theme.of(context).textTheme.bodyMedium),
            const SizedBox(height: 16),
            categoriesAsync.when(
              data: (categories) => Wrap(
                spacing: 8,
                runSpacing: 8,
                children: [
                  ChoiceChip(
                    label: const Text('Todos'),
                    selected: catalog.categoryId == null,
                    onSelected: (_) => ref.read(catalogProvider.notifier).setCategory(null),
                  ),
                  ...categories.map((c) => ChoiceChip(
                    label: Text(c.name),
                    selected: catalog.categoryId == c.id,
                    onSelected: (_) => ref.read(catalogProvider.notifier).setCategory(c.id),
                  ))
                ],
              ),
              loading: () => const Center(child: CircularProgressIndicator()),
              error: (_, __) => const Text('No se pudieron cargar las categorías'),
            ),
            const SizedBox(height: 16),
            if (catalog.isLoading)
              const Center(child: CircularProgressIndicator())
            else if (catalog.error != null)
              Text(catalog.error!, style: const TextStyle(color: Colors.red))
            else if (catalog.products.isEmpty)
              const Center(child: Text('No hay productos disponibles'))
            else
              GridView.builder(
                physics: const NeverScrollableScrollPhysics(),
                shrinkWrap: true,
                itemCount: catalog.products.length,
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  childAspectRatio: 0.82,
                  crossAxisSpacing: 8,
                  mainAxisSpacing: 8,
                ),
                itemBuilder: (_, index) => ProductCard(
                  product: catalog.products[index],
                  onTap: () {
                    Navigator.of(context).push(MaterialPageRoute(
                      builder: (_) => ProductDetailScreen(productId: catalog.products[index].id),
                    ));
                  },
                ),
              ),
          ],
        ),
      ),
    );
  }
}
