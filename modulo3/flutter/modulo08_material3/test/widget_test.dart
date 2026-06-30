// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility in the flutter_test package. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter_test/flutter_test.dart';

import 'package:modulo08_material3/main.dart';

void main() {
  testWidgets('Counter increments smoke test', (WidgetTester tester) async {
    // Build the app and verify the theme selection screen appears.
    await tester.pumpWidget(const AppMonitoreo());

    expect(find.text('Modo de tema'), findsOneWidget);
    expect(find.text('Apariencia'), findsOneWidget);
  });
}
