import 'package:flutter_test/flutter_test.dart';
import 'package:modulo08_material3/CP_main.dart';

void main() {
  testWidgets('App compila correctamente', (WidgetTester tester) async {
    await tester.pumpWidget(const AppMonitoreo());
    expect(find.byType(AppMonitoreo), findsOneWidget);
  });
}
