import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import '../../../core/utils/formatters.dart';
import '../../../domain/model/order.dart';
import '../../../theme/app_colors.dart';
import '../../providers/orders_provider.dart';
import '../../widgets/status_badge.dart';

class OrderDetailScreen extends ConsumerWidget {
  final int id;
  const OrderDetailScreen({super.key, required this.id});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final orderAsync = ref.watch(orderDetailProvider(id));

    return Scaffold(
      appBar: AppBar(
        title: const Text('Detalle del pedido'),
        leading: IconButton(onPressed: () => context.pop(), icon: const Icon(Icons.arrow_back_ios_new_rounded)),
      ),
      body: orderAsync.when(
        data: (order) => _OrderDetailBody(order: order),
        loading: () => const Center(child: CircularProgressIndicator(color: AppColors.accent)),
        error: (e, _) => Center(
          child: Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text('⚠️', style: TextStyle(fontSize: 40)),
                const SizedBox(height: 12),
                Text(e.toString(), textAlign: TextAlign.center, style: const TextStyle(color: AppColors.error)),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class _OrderDetailBody extends StatelessWidget {
  final Order order;
  const _OrderDetailBody({required this.order});

  @override
  Widget build(BuildContext context) {
    final tt = Theme.of(context).textTheme;
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        Container(
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            color: AppColors.surface,
            borderRadius: BorderRadius.circular(16),
            border: Border.all(color: AppColors.border),
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(crossAxisAlignment: CrossAxisAlignment.start, children: [Text('Pedido #${order.id}', style: tt.titleMedium), Text(formatDate(order.createdAt), style: tt.bodySmall)]),
                  StatusBadge(status: order.status),
                ],
              ),
              const SizedBox(height: 12),
              Text('Estado: ${order.status.label}', style: tt.bodyMedium),
              const SizedBox(height: 4),
              Text('Cliente: ${order.username}', style: tt.bodyMedium),
              const SizedBox(height: 4),
              Text('Actualizado: ${formatDate(order.updatedAt)}', style: tt.bodyMedium),
            ],
          ),
        ),
        const SizedBox(height: 16),
        Text('Productos', style: tt.titleMedium),
        const SizedBox(height: 8),
        ...order.items.map((item) => Container(
              margin: const EdgeInsets.only(bottom: 10),
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                color: AppColors.surface,
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: AppColors.border),
              ),
              child: Row(
                children: [
                  Expanded(
                    child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [Text(item.productName, style: tt.bodyMedium), Text('${item.quantity} unidad${item.quantity != 1 ? 'es' : ''}', style: tt.bodySmall)]),
                  ),
                  Text(formatPrice(item.unitPrice * item.quantity), style: const TextStyle(fontWeight: FontWeight.bold, color: AppColors.accent)),
                ],
              ),
            )),
        const SizedBox(height: 16),
        Container(
          padding: const EdgeInsets.all(16),
          decoration: BoxDecoration(
            color: AppColors.surface,
            borderRadius: BorderRadius.circular(16),
            border: Border.all(color: AppColors.border),
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _SummaryRow(label: 'Subtotal', value: formatPrice(_subtotal(order))),
              _SummaryRow(label: 'Envío', value: formatPrice(_shippingCost(order))),
              _SummaryRow(label: 'Total', value: formatPrice(order.total), isTotal: true),
            ],
          ),
        ),
      ],
    );
  }
}

double _subtotal(Order order) => order.items.fold(0.0, (sum, item) => sum + item.subtotal);

double _shippingCost(Order order) {
  final subtotal = _subtotal(order);
  final diff = order.total - subtotal;
  return diff > 0 ? diff : 0.0;
}

class _SummaryRow extends StatelessWidget {
  final String label;
  final String value;
  final bool isTotal;
  const _SummaryRow({required this.label, required this.value, this.isTotal = false});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(label, style: TextStyle(fontWeight: isTotal ? FontWeight.bold : FontWeight.normal, fontSize: isTotal ? 15 : 13)),
          Text(value, style: TextStyle(fontWeight: isTotal ? FontWeight.bold : FontWeight.normal, color: isTotal ? AppColors.accent : AppColors.textPrimary)),
        ],
      ),
    );
  }
}
