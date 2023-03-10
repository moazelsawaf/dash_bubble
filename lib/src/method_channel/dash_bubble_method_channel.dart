import 'dart:developer';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'dash_bubble_platform_interface.dart';
import '../constants.dart';
import '../bubble_options.dart';

/// An implementation of [DashBubblePlatform] that uses method channels.
class MethodChannelDashBubble extends DashBubblePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel(Constants.methodChannel);

  /// The main wrapper for invoking methods on the method channel.
  ///
  /// This method will catch any [PlatformException] or [MissingPluginException]
  ///
  /// If this method called from a platform different from Android it will do nothing.
  Future<T?> _invokeMethod<T>(String method, [dynamic arguments]) async {
    try {
      if (!Platform.isAndroid) return null;

      return await methodChannel.invokeMethod<T>(method, arguments);
    } on PlatformException catch (_) {
      logError(
        '''PlatformException: $method
        
        Please report this issue on GitHub via: https://github.com/moazelsawaf/dash_bubble/issues/new''',
      );
      rethrow;
    } on MissingPluginException catch (_) {
      logError(
        '''MissingPluginException: $method

        If you have not restarted your app after adding the plugin, please do and try again.''',
      );
      rethrow;
    }
  }

  /// Invokes the method channel to request permission to draw over other apps.
  @override
  Future<bool> requestPermission() async {
    return (await _invokeMethod<bool>(Constants.requestPermission))!;
  }

  /// Invokes the method channel to check if the permission to draw over other apps is granted.
  @override
  Future<bool> hasPermission() async {
    return (await _invokeMethod<bool>(Constants.hasPermission))!;
  }

  /// Invokes the method channel to check if the bubble is currently running.
  @override
  Future<bool> isRunning() async {
    return (await _invokeMethod<bool>(Constants.isRunning))!;
  }

  /// Invokes the method channel to start the bubble.
  @override
  Future<bool> startBubble({
    BubbleOptions? options,
    VoidCallback? onBubbleTap,
  }) async {
    methodChannel.setMethodCallHandler((call) async {
      if (call.method == Constants.onBubbleTap) {
        onBubbleTap?.call();
      } else {
        throw UnimplementedError('Method ${call.method} is not implemented');
      }
    });

    return (await _invokeMethod<bool>(
      Constants.startBubble,
      options?.toMap() ?? BubbleOptions().toMap(),
    ))!;
  }

  /// Invokes the method channel to stop the bubble.
  @override
  Future<bool> stopBubble() async {
    return (await _invokeMethod<bool>(Constants.stopBubble))!;
  }

  /// Logs an error message.
  void logError(String message) {
    log(message, name: Constants.errorLogName);
  }
}
