import 'package:flutter_test/flutter_test.dart';
import 'package:flutter/material.dart';
import 'package:modulo06_widgets/CP_main.dart';

void main() {
  testWidgets('App compila correctamente', (WidgetTester tester) async {
    await tester.pumpWidget(MaterialApp(
      home: switch (paso) {
        1 => const Scaffold(body: Center(child: Saludo())),
        _ => const Scaffold(body: Center(child: Text('ok'))),
      },
    ));
    expect(find.byType(Scaffold), findsOneWidget);
  });
}
