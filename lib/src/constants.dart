/// This class contains all the constants used in the plugin.
class Constants {
  /// Private constructor to prevent instantiation.
  Constants._();

  // Method Channel
  static const methodChannel = 'dash_bubble';

  // Miscellaneous
  static const errorLogName = 'DashBubble';
  static const xAxisValue = 'xAxisValue';
  static const yAxisValue = 'yAxisValue';

  // Native Methods Names
  static const requestPermission = 'requestPermission';
  static const hasPermission = 'hasPermission';
  static const isRunning = 'isRunning';
  static const startBubble = 'startBubble';
  static const stopBubble = 'stopBubble';
  static const onTap = 'onTap';
  static const onTapDown = 'onTapDown';
  static const onTapUp = 'onTapUp';
  static const onMove = 'onMove';
}
