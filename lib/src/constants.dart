/// This class contains all the constants used in the plugin.
class Constants {
  /// Private constructor to prevent instantiation.
  Constants._();

  // Method Channel
  static const methodChannel = 'dash_bubble';

  // Miscellaneous
  static const errorLogName = 'DashBubble';

  // Native Methods Names
  static const requestPermission = 'requestPermission';
  static const hasPermission = 'hasPermission';
  static const isRunning = 'isRunning';
  static const startBubble = 'startBubble';
  static const stopBubble = 'stopBubble';
  static const onBubbleTap = 'onBubbleTap';
}
