package dev.moaz.dash_bubble.src

/** Constants is the class that contains all the constants used in the plugin. */
class Constants {
    companion object {
        const val METHOD_CHANNEL = "dash_bubble"
        const val OVERLAY_PERMISSION_REQUEST_CODE = 7000
        const val POST_NOTIFICATIONS_PERMISSION_REQUEST_CODE = 8000
        const val BUBBLE_OPTIONS_INTENT_EXTRA = "bubbleOptionsIntentExtra"
        const val ERROR_TAG = "DASH_BUBBLE"

        // Bubble Callbacks
        const val ON_TAP = "onTap"
        const val ON_TAP_DOWN = "onTapDown"
        const val ON_TAP_UP = "onTapUp"
        const val ON_MOVE = "onMove"
        const val X_AXIS_VALUE = "xAxisValue"
        const val Y_AXIS_VALUE = "yAxisValue"

        // Methods
        const val REQUEST_OVERLAY_PERMISSION = "requestOverlayPermission"
        const val HAS_OVERLAY_PERMISSION = "hasOverlayPermission"
        const val REQUEST_POST_NOTIFICATIONS_PERMISSION = "requestPostNotificationsPermission"
        const val HAS_POST_NOTIFICATIONS_PERMISSION = "hasPostNotificationsPermission"
        const val IS_RUNNING = "isRunning"
        const val START_BUBBLE = "startBubble"
        const val STOP_BUBBLE = "stopBubble"

        // Bubble Options Arguments
        const val NOTIFICATION_TITLE = "notificationTitle"
        const val NOTIFICATION_TEXT = "notificationText"
        const val NOTIFICATION_ICON = "notificationIcon"
        const val BUBBLE_ICON = "bubbleIcon"
        const val CLOSE_ICON = "closeIcon"
        const val START_LOCATION_X = "startLocationX"
        const val START_LOCATION_Y = "startLocationY"
        const val BUBBLE_SIZE = "bubbleSize"
        const val OPACITY = "opacity"
        const val ENABLE_CLOSE = "enableClose"
        const val CLOSE_BEHAVIOR = "closeBehavior"
        const val DISTANCE_TO_CLOSE = "distanceToClose"
        const val ENABLE_ANIMATE_TO_EDGE = "enableAnimateToEdge"
        const val ENABLE_BOTTOM_SHADOW = "enableBottomShadow"
        const val KEEP_ALIVE_WHEN_APP_EXIT = "keepAliveWhenAppExit"
    }
}