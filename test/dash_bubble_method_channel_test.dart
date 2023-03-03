import 'package:dash_bubble/src/constants.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  const MethodChannel channel = MethodChannel(Constants.methodChannel);

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {});
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
}
