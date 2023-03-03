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

import 'dash_bubble_platform_interface.dart';
import 'src/bubble_options.dart';

export 'src/bubble_options.dart';

/// The main class of the plugin.
class DashBubble {
  /// Private constructor to prevent instantiation.
  DashBubble._();

  /// Singleton Pattern
  static final DashBubble instance = DashBubble._();

  /// Request permission to draw over other apps.
  ///
  /// Returns `true` if the permission is granted, `false` otherwise.
  ///
  /// If the permission is already granted, this method will return `true` without asking the user.
  ///
  /// In Android versions prior to `Android 6.0 (M)`, this method will return `true` without asking the user.
  Future<bool> requestPermission() {
    return DashBubblePlatform.instance.requestPermission();
  }

  /// Check if the permission to draw over other apps is granted.
  ///
  /// Returns `true` if the permission is granted, `false` otherwise.
  Future<bool> hasPermission() {
    return DashBubblePlatform.instance.hasPermission();
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
  Future<bool> startBubble({
    BubbleOptions? options,
    Function()? onBubbleTap,
  }) {
    return DashBubblePlatform.instance.startBubble(
      options: options,
      onBubbleTap: onBubbleTap,
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
