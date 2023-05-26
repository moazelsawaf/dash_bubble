package dev.moaz.dash_bubble.src

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.startForegroundService
import com.torrydo.floatingbubbleview.FloatingBubbleService

/** BubbleManager is the main class that you will use to manage the bubble. */
class BubbleManager(private val activity: Activity) {

    /** Request permission to draw over other apps (overlay permission).
     * @return true if permission is granted, null if the permission is being requested.
     *
     * If the permission is not granted, the user will be redirected to the settings page to grant the permission and it will return null.
     *
     * If the current android version is lower than 6 (android M), it will return true immediately.
     */
    fun requestOverlayPermission(): Boolean? {
        if (hasOverlayPermission()) return true

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + activity.packageName)
            )
            activity.startActivityForResult(intent, Constants.OVERLAY_PERMISSION_REQUEST_CODE)
            null
        } else {
            true
        }
    }

    /** Check if draw over other apps (overlay) permission is granted or not.
     * @return true if permission is granted, false if not.
     *
     * If the current android version is lower than 6 (android M), it will return true immediately.
     */
    fun hasOverlayPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(activity)
        } else true
    }

    /** Request post notifications permission.
     * @return true if permission is granted, null if the permission is being requested.
     *
     * If the permission is not granted, the user will be asked to grant the permission.
     *
     * The user can be asked for the permission only twice, if the user denied the permission twice,
     * the function would not be able to ask for the permission anymore and it will return false,
     * however, the user can still grant the permission manually from the app settings page.
     *
     * If the current android version is lower than 13 (android Tiramisu), it will return true without asking the user.
     */
    fun requestPostNotificationsPermission(): Boolean? {
        if (hasPostNotificationsPermission()) return true

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                Constants.POST_NOTIFICATIONS_PERMISSION_REQUEST_CODE
            )
            null
        } else {
            true
        }
    }

    /** Check if the post notifications permission is granted or not.
     * @return true if permission is granted, false if not.
     *
     * If the current android version is lower than 13 (android Tiramisu), it will return true immediately.
     */
    fun hasPostNotificationsPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
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
    fun startBubble(
        bubbleOptions: BubbleOptions,
        notificationOptions: NotificationOptions
    ): Boolean {
        if (!hasOverlayPermission()) return false

        if (isRunning()) return false

        val intent = Intent(activity, BubbleService::class.java)
        intent.putExtra(Constants.BUBBLE_OPTIONS_INTENT_EXTRA, bubbleOptions)
        intent.putExtra(Constants.NOTIFICATION_OPTIONS_INTENT_EXTRA, notificationOptions)

        startForegroundService(activity, intent)

        return true
    }

    /** Stop the bubble.
     * @return true if the bubble is stopped successfully, false if not.
     *
     * If the bubble is not running, it will return false.
     */
    fun stopBubble(): Boolean {
        if (!FloatingBubbleService.isRunning()) return false

        val intent = Intent(activity, BubbleService::class.java)
        activity.stopService(intent)

        return true
    }
}