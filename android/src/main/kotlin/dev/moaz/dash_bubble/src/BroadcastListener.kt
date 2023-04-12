package dev.moaz.dash_bubble.src

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.flutter.plugin.common.MethodChannel

/** This broadcast listener is used to listen for the bubble callbacks */
class BroadcastListener(channel: MethodChannel) : BroadcastReceiver() {
    private val channel = channel

    override fun onReceive(context: Context?, intent: Intent) {
        when (intent.action) {

            /** This method is called when the bubble is tapped */
            Constants.ON_TAP -> {
                channel.invokeMethod(Constants.ON_TAP, null)
            }

            /** This method is called when the bubble is tapped down (pressed) */
            Constants.ON_TAP_DOWN -> {
                channel.invokeMethod(
                    Constants.ON_TAP_DOWN, getCoordinatesValues(intent)
                )
            }

            /** This method is called when the bubble is tapped up (released) */
            Constants.ON_TAP_UP -> {
                channel.invokeMethod(
                    Constants.ON_TAP_UP, getCoordinatesValues(intent)
                )
            }

            /** This method is called when the bubble is moved */
            Constants.ON_MOVE -> {
                channel.invokeMethod(
                    Constants.ON_MOVE, getCoordinatesValues(intent)
                )
            }
        }
    }

    /** This method is used to get the coordinates from the intent extras. */
    private fun getCoordinatesValues(intent: Intent): HashMap<String, Float> {
        return hashMapOf(
            Constants.X_AXIS_VALUE to intent.getFloatExtra(Constants.X_AXIS_VALUE, 0f),
            Constants.Y_AXIS_VALUE to intent.getFloatExtra(Constants.Y_AXIS_VALUE, 0f)
        )
    }
}