package dev.moaz.dash_bubble.src

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat.startForegroundService
import com.torrydo.floatingbubbleview.FloatingBubbleService

/** BubbleManager is the main class that you will use to manage the bubble. */
class BubbleManager(private val activity: Activity) {

    /** Request permission to draw over other apps.
     * @return true if permission is granted, null if the permission is being requested.
     *
     * If the permission is not granted, the user will be redirected to the settings page to grant the permission and it will return null.
     *
     * If the current android version is lower than 6 (android M), it will return true immediately.
     */
    fun requestPermission(): Boolean? {
        if (hasPermission()) return true

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.packageName))
            activity.startActivityForResult(intent, Constants.PERMISSION_REQUEST_CODE)
            null
        } else {
            true
        }
    }

    /** Check if the permission is granted or not.
     * @return true if permission is granted, false if not.
     *
     * If the current android version is lower than 6 (android M), it will return true immediately.
     */
    fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(activity)
        } else true
    }

    /** Check if the bubble is running or not.
     * @return true if the bubble is running, false if not.
     */
    fun isRunning(): Boolean {
        return FloatingBubbleService.isRunning()
    }

    /** Start the bubble.
     * @param bubbleOptions BubbleOptions object that contains the options for the bubble.
     * @return true if the bubble is started successfully, false if not.
     *
     * If the bubble is already running, it will return false.
     *
     * If the permission is not granted, it will return false.
     */
    fun startBubble(bubbleOptions: BubbleOptions) : Boolean {
        if (!hasPermission()) return false

        if (isRunning()) return false

        val intent = Intent(activity, BubbleService::class.java)
        intent.putExtra(Constants.BUBBLE_OPTIONS_INTENT_EXTRA, bubbleOptions)

        startForegroundService(activity, intent)

        return true
    }

    /** Stop the bubble.
     * @return true if the bubble is stopped successfully, false if not.
     *
     * If the bubble is not running, it will return false.
     */
    fun stopBubble() : Boolean {
        if (!FloatingBubbleService.isRunning()) return false

        val intent = Intent(activity, BubbleService::class.java)
        activity.stopService(intent)

        return true
    }
}