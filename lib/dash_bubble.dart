/// Dash Bubble is a flutter plugin that allows you to create a floating bubble on the screen.
///
/// The plugin is currently only supports Android Platform and is built on top of [Floating-Bubble-View](https://github.com/TorryDo/Floating-Bubble-View) library.
///
/// To use this plugin, add `dash_bubble` as a [dependency in your pubspec.yaml file](https://flutter.io/platform-plugins/) and import it in your dart code.
///
/// ```dart
/// import 'package:dash_bubble/dash_bubble.dart';
/// ```
///
/// For more information on how to use this plugin, please refer to the [API Reference](https://pub.dev/documentation/dash_bubble/latest/) or the [Example Project](https://github.com/moazelsawaf/dash_bubble/tree/main/example)
library dash_bubble;

import 'src/method_channel/dash_bubble_platform_interface.dart';
import 'src/models/models.dart';

export 'src/models/models.dart';
export 'src/enums/enums.dart';

/// The main class of the plugin.
class DashBubble {
  /// Private constructor to prevent instantiation.
  DashBubble._();

  /// Singleton Pattern
  static final DashBubble instance = DashBubble._();

  /// Request permission to draw over other apps (overlay permission).
  ///
  /// Returns `true` if the permission is granted, `false` otherwise.
  ///
  /// If the permission is already granted, this method will return `true` without asking the user.
  ///
  /// In Android versions prior to `Android 6.0 (M)`, this method will return `true` without asking the user.
  Future<bool> requestOverlayPermission() {
    return DashBubblePlatform.instance.requestOverlayPermission();
  }

  /// Check if draw over other apps (overlay) permission is granted or not.
  ///
  /// Returns `true` if the permission is granted, `false` otherwise.
  ///
  /// In Android versions prior to `Android 6.0 (M)`, this method will always return `true`.
  Future<bool> hasOverlayPermission() {
    return DashBubblePlatform.instance.hasOverlayPermission();
  }

  /// Request post notifications permission (Used to customize the service notification)
  ///
  /// Returns `true` if the permission is granted, `false` otherwise.
  ///
  /// The user can be asked for the permission only twice, if the user denied
  /// the permission twice, the function would not be able to ask for the
  /// permission anymore and it will return false, however, the user can still
  /// grant the permission manually from the app settings page.
  ///
  /// In Android versions prior to `Android 13 (Tiramisu)`, this method will return `true` without asking the user.
  Future<bool> requestPostNotificationsPermission() {
    return DashBubblePlatform.instance.requestPostNotificationsPermission();
  }

  /// Check if the post notifications permission is granted or not.
  ///
  /// Returns `true` if the permission is granted, `false` otherwise.
  ///
  /// In Android versions prior to `Android 13 (Tiramisu)`, this method will always return `true`.
  Future<bool> hasPostNotificationsPermission() {
    return DashBubblePlatform.instance.hasPostNotificationsPermission();
  }

  /// Check if the bubble is currently running.
  ///
  /// Returns `true` if the bubble is running, `false` otherwise.
  ///
  /// The bubble is considered running if it is visible on the screen.
  ///
  /// If the bubble is not running, you can start it by calling [startBubble].
  ///
  /// If the bubble is running, you can stop it by calling [stopBubble].
  Future<bool> isRunning() {
    return DashBubblePlatform.instance.isRunning();
  }

  /// Start the bubble.
  ///
  /// Returns `true` if the bubble is started successfully, `false` otherwise.
  ///
  /// If the bubble is already running or the permission is not granted, this method will return `false`.
  ///
  /// [options] is and optional parameter that allows you to customize the bubble.
  ///
  /// [onTap] is an optional callback that will be called when the bubble is tapped.
  ///
  /// [onTapDown] is an optional callback that will be called when the bubble is tapped down (pressed), it will receive the `x` and `y` coordinates of the bubble when it is tapped down (pressed).
  ///
  /// [onTapUp] is an optional callback that will be called when the bubble is tapped up (released), it will receive the `x` and `y` coordinates of the bubble when it is tapped up (released).
  ///
  /// [onMove] is an optional callback that will be called when the bubble is moved, it will receive the new `x` and `y` coordinates of the bubble after it is moved.
  ///
  Future<bool> startBubble({
    BubbleOptions? bubbleOptions,
    NotificationOptions? notificationOptions,
    Function()? onTap,
    Function(double x, double y)? onTapDown,
    Function(double x, double y)? onTapUp,
    Function(double x, double y)? onMove,
  }) {
    return DashBubblePlatform.instance.startBubble(
      bubbleOptions: bubbleOptions,
      notificationOptions: notificationOptions,
      onTap: onTap,
      onTapDown: onTapDown,
      onTapUp: onTapUp,
      onMove: onMove,
    );
  }

  /// Stop the bubble.
  ///
  /// Returns `true` if the bubble is stopped successfully, `false` otherwise.
  ///
  /// If the bubble is not running, this method will return `false`.
  Future<bool> stopBubble() {
    return DashBubblePlatform.instance.stopBubble();
  }
}
