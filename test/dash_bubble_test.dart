import 'dart:ui';

import 'package:flutter_test/flutter_test.dart';
import 'package:dash_bubble/dash_bubble.dart';
import 'package:dash_bubble/dash_bubble_platform_interface.dart';
import 'package:dash_bubble/dash_bubble_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockDashBubblePlatform
    with MockPlatformInterfaceMixin
    implements DashBubblePlatform {
  @override
  Future<bool> hasPermission() {
    throw UnimplementedError();
  }

  @override
  Future<bool> isRunning() {
    throw UnimplementedError();
  }

  @override
  Future<bool> requestPermission() {
    throw UnimplementedError();
  }

  @override
  Future<bool> startBubble(
      {BubbleOptions? options, VoidCallback? onBubbleTap}) {
    throw UnimplementedError();
  }

  @override
  Future<bool> stopBubble() {
    throw UnimplementedError();
  }
}

void main() {
  final DashBubblePlatform initialPlatform = DashBubblePlatform.instance;

  test('$MethodChannelDashBubble is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelDashBubble>());
  });
}
