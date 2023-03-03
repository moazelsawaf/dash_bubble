package dev.moaz.dash_bubble.src

/** Constants is the class that contains all the constants used in the plugin. */
class Constants {
    companion object {
        const val METHOD_CHANNEL = "dash_bubble"
        const val PERMISSION_REQUEST_CODE = 7000
        const val BUBBLE_TAP_INTENT = "bubble_tap_intent"
        const val BUBBLE_OPTIONS_INTENT_EXTRA = "bubble_options_intent_extra"
        const val ERROR_CODE = "DASH_BUBBLE"

        // Methods
        const val REQUEST_PERMISSION = "requestPermission"
        const val HAS_PERMISSION = "hasPermission"
        const val IS_RUNNING = "isRunning"
        const val START_BUBBLE = "startBubble"
        const val STOP_BUBBLE = "stopBubble"
        const val ON_BUBBLE_TAP = "onBubbleTap"

        // Bubble Options Arguments
        const val NOTIFICATION_TITLE = "notificationTitle"
        const val NOTIFICATION_TEXT = "notificationText"
        const val NOTIFICATION_ICON = "notificationIcon"
        const val BUBBLE_ICON = "bubbleIcon"
        const val CLOSE_ICON = "closeIcon"
        const val START_LOCATION_X = "startLocationX"
        const val START_LOCATION_Y = "startLocationY"
        const val BUBBLE_SIZE = "bubbleSize"
        const val ENABLE_CLOSE = "enableClose"
        const val ENABLE_ANIMATE_TO_EDGE = "enableAnimateToEdge"
        const val ENABLE_BOTTOM_SHADOW = "enableBottomShadow"
        const val KEEP_ALIVE_WHEN_APP_EXIT = "keepAliveWhenAppExit"
    }
}