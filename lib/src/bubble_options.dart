/// [BubbleOptions] is a class that contains all the customizable options for the bubble passed to the [DashBubble.startBubble] method.
class BubbleOptions {
  /// The title of the service notification.
  final String? notificationTitle;

  /// The text of the service notification.
  final String? notificationText;

  /// The icon of the service notification.
  final String? notificationIcon;

  /// The icon of the bubble.
  final String? bubbleIcon;

  /// The icon of the close button.
  final String? closeIcon;

  /// The initial starting position of the bubble on the x axis.
  final int startLocationX;

  /// The initial starting position of the bubble on the y axis.
  final int startLocationY;

  /// The size of the bubble.
  final int bubbleSize;

  /// Whether to show the close button or not.
  final bool enableClose;

  /// Whether to animate the bubble to the edge of the screen when it is dragged to the edge of the screen or not.
  final bool enableAnimateToEdge;

  /// Whether to show the bottom shadow behind the close button of the bubble or not.
  final bool enableBottomShadow;

  /// Whether to keep the bubble alive when the app is closed or not.
  final bool keepAliveWhenAppExit;

  BubbleOptions({
    this.notificationTitle,
    this.notificationText,
    this.notificationIcon,
    this.bubbleIcon,
    this.closeIcon,
    this.startLocationX = 0,
    this.startLocationY = 200,
    this.bubbleSize = 60,
    this.enableClose = true,
    this.enableAnimateToEdge = true,
    this.enableBottomShadow = true,
    this.keepAliveWhenAppExit = false,
  });

  Map<String, dynamic> toMap() {
    return {
      'notificationTitle': notificationTitle,
      'notificationText': notificationText,
      'notificationIcon': notificationIcon,
      'bubbleIcon': bubbleIcon,
      'closeIcon': closeIcon,
      'startLocationX': startLocationX,
      'startLocationY': startLocationY,
      'bubbleSize': bubbleSize,
      'enableClose': enableClose,
      'enableAnimateToEdge': enableAnimateToEdge,
      'enableBottomShadow': enableBottomShadow,
      'keepAliveWhenAppExit': keepAliveWhenAppExit,
    };
  }
}
