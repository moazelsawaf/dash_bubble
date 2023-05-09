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
  /// This method will catch any [PlatformException] or [MissingPluginException], log the error, and rethrow it.
  ///
  /// returns `false` if the platform is not Android.
  Future<bool> _invokeMethod(String method, [dynamic arguments]) async {
    try {
      // If the platform is not Android, return false.
      if (!Platform.isAndroid) return false;

      // The bang operator is used here because the method channel will always return a boolean from the native implementation.
      return (await methodChannel.invokeMethod<bool>(method, arguments))!;
    } on PlatformException catch (_) {
      logError(
        'PlatformException: $method\n\nPlease report this issue on GitHub via: https://github.com/moazelsawaf/dash_bubble/issues/new',
      );
      rethrow;
    } on MissingPluginException catch (_) {
      logError(
        'MissingPluginException: $method\n\nIf you have not restarted your app after adding the plugin, please do and try again.',
      );
      rethrow;
    }
  }

  /// Invokes the method channel to request permission to draw over other apps.
  @override
  Future<bool> requestOverlayPermission() async {
    return (await _invokeMethod(Constants.requestOverlayPermission));
  }

  /// Invokes the method channel to check if the permission to draw over other apps is granted.
  @override
  Future<bool> hasOverlayPermission() async {
    return (await _invokeMethod(Constants.hasOverlayPermission));
  }

  /// Invokes the method channel to request post notifications permission.
  @override
  Future<bool> requestPostNotificationsPermission() async {
    return (await _invokeMethod(Constants.requestPostNotificationsPermission));
  }

  /// Invokes the method channel to check if the post notifications permission is granted.
  @override
  Future<bool> hasPostNotificationsPermission() async {
    return (await _invokeMethod(Constants.hasPostNotificationsPermission));
  }

  /// Invokes the method channel to check if the bubble is currently running.
  @override
  Future<bool> isRunning() async {
    return (await _invokeMethod(Constants.isRunning));
  }

  /// Invokes the method channel to start the bubble.
  @override
  Future<bool> startBubble({
    BubbleOptions? options,
    VoidCallback? onTap,
    Function(double x, double y)? onTapDown,
    Function(double x, double y)? onTapUp,
    Function(double x, double y)? onMove,
  }) async {
    methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case Constants.onTap:
          onTap?.call();
          break;
        case Constants.onTapDown:
          onTapDown?.call(
            call.arguments[Constants.xAxisValue],
            call.arguments[Constants.yAxisValue],
          );
          break;
        case Constants.onTapUp:
          onTapUp?.call(
            call.arguments[Constants.xAxisValue],
            call.arguments[Constants.yAxisValue],
          );
          break;
        case Constants.onMove:
          onMove?.call(
            call.arguments[Constants.xAxisValue],
            call.arguments[Constants.yAxisValue],
          );
          break;
        default:
          throw UnimplementedError(
            'Method ${call.method} is not implemented',
          );
      }
    });

    return (await _invokeMethod(
      Constants.startBubble,
      (options ?? BubbleOptions()).toMap(),
    ));
  }

  /// Invokes the method channel to stop the bubble.
  @override
  Future<bool> stopBubble() async {
    return (await _invokeMethod(Constants.stopBubble));
  }

  /// Logs an error message.
  void logError(String message) {
    log(message, name: Constants.errorLogName);
  }
}
