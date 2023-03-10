import 'package:flutter/material.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'dash_bubble_method_channel.dart';
import '../bubble_options.dart';

abstract class DashBubblePlatform extends PlatformInterface {
  /// Constructs a DashBubblePlatform.
  DashBubblePlatform() : super(token: _token);

  static final Object _token = Object();

  static DashBubblePlatform _instance = MethodChannelDashBubble();

  /// The default instance of [DashBubblePlatform] to use.
  ///
  /// Defaults to [MethodChannelDashBubble].
  static DashBubblePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [DashBubblePlatform] when
  /// they register themselves.
  static set instance(DashBubblePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  /// Request permission to draw over other apps.
  Future<bool> requestPermission() async {
    throw UnimplementedError('requestPermission() has not been implemented.');
  }

  /// Check if the permission to draw over other apps is granted.
  Future<bool> hasPermission() async {
    throw UnimplementedError('hasPermission() has not been implemented.');
  }

  /// Check if the bubble is currently running.
  Future<bool> isRunning() async {
    throw UnimplementedError('isRunning() has not been implemented.');
  }

  /// Start the bubble.
  Future<bool> startBubble({
    BubbleOptions? options,
    VoidCallback? onBubbleTap,
  }) async {
    throw UnimplementedError('startBubble() has not been implemented.');
  }

  /// Stop the bubble.
  Future<bool> stopBubble() async {
    throw UnimplementedError('stopBubble() has not been implemented.');
  }
}
