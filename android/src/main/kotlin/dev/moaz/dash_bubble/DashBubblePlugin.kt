package dev.moaz.dash_bubble

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dev.moaz.dash_bubble.src.BroadcastListener
import dev.moaz.dash_bubble.src.BubbleManager
import dev.moaz.dash_bubble.src.Constants
import dev.moaz.dash_bubble.src.Helpers
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry

/** DashBubblePlugin
 *  This is the main plugin class that handles all the method calls from dart side
 */
class DashBubblePlugin : FlutterPlugin, MethodCallHandler, ActivityAware,
    PluginRegistry.ActivityResultListener, PluginRegistry.RequestPermissionsResultListener {

    private var activityBinding: ActivityPluginBinding? = null
    private lateinit var mActivity: Activity
    private lateinit var channel: MethodChannel
    private lateinit var delayedResultHandler: Result
    private lateinit var broadcastListener: BroadcastListener
    private lateinit var bubbleManager: BubbleManager

    /** This method is called when the plugin is attached to the flutter engine
     * It initializes the method channel and sets the method call handler
     */
    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, Constants.METHOD_CHANNEL)
        channel.setMethodCallHandler(this)
    }

    /** This method is the handler for the method calls from dart side
     * It handles all the method calls and calls the appropriate method from the bubble manager
     */
    override fun onMethodCall(call: MethodCall, result: Result) {
        try {
            when (call.method) {
                Constants.REQUEST_OVERLAY_PERMISSION -> {
                    if (bubbleManager.requestOverlayPermission() == true) {
                        result.success(true)
                        return
                    }

                    delayedResultHandler = result
                }
                Constants.HAS_OVERLAY_PERMISSION -> result.success(bubbleManager.hasOverlayPermission())
                Constants.REQUEST_POST_NOTIFICATIONS_PERMISSION -> {
                    if (bubbleManager.requestPostNotificationsPermission() == true) {
                        result.success(true)
                        return
                    }

                    delayedResultHandler = result
                }
                Constants.HAS_POST_NOTIFICATIONS_PERMISSION -> result.success(bubbleManager.hasPostNotificationsPermission())
                Constants.IS_RUNNING -> result.success(bubbleManager.isRunning())
                Constants.START_BUBBLE -> result.success(
                    bubbleManager.startBubble(Helpers.getBubbleOptionsFromMethodCall(call))
                )
                Constants.STOP_BUBBLE -> result.success(bubbleManager.stopBubble())
                else -> result.notImplemented()
            }
        } catch (e: Exception) {
            result.error(Constants.ERROR_TAG, e.message, null)
        }
    }

    /** This method is called when the plugin is detached from the engine
     * It removes the method call handler
     */
    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    /** This method is called when the activity is created
     * It does the following:
     * 1. Initializes the activity binding
     * 2. Initializes the activity
     * 3. Registers the activity result listener
     * 4. Registers the permission result listener
     * 5. Initializes the bubble manager
     * 6. Registers the broadcast receiver
     */
    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activityBinding = binding
        mActivity = binding.activity

        binding.addActivityResultListener(this);
        binding.addRequestPermissionsResultListener(this)

        bubbleManager = BubbleManager(mActivity)
        broadcastListener = BroadcastListener(channel)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.ON_TAP);
        intentFilter.addAction(Constants.ON_TAP_DOWN);
        intentFilter.addAction(Constants.ON_TAP_UP);
        intentFilter.addAction(Constants.ON_MOVE);

        LocalBroadcastManager.getInstance(mActivity)
            .registerReceiver(broadcastListener, intentFilter)
    }

    /** This method is called when the activity is recreated */
    override fun onDetachedFromActivityForConfigChanges() {
        activityBinding?.removeActivityResultListener(this);
        activityBinding?.removeRequestPermissionsResultListener(this)
        
        activityBinding = null
    }

    /** This method is called when the activity is recreated */
    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activityBinding = binding

        binding.addActivityResultListener(this);
        binding.addRequestPermissionsResultListener(this)
    }

    /** This method is called when the activity is destroyed
     * It removes the activity result listener and unregisters the broadcast receiver
     */
    override fun onDetachedFromActivity() {
        activityBinding?.removeActivityResultListener(this);
        activityBinding?.removeRequestPermissionsResultListener(this)

        activityBinding = null

        LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(broadcastListener)
    }

    /** This method is called whenever an action that has an activity result is completed */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == Constants.OVERLAY_PERMISSION_REQUEST_CODE) {
            delayedResultHandler.success(bubbleManager.hasOverlayPermission())
            return true
        }

        return false
    }

    /** This method is called whenever a permission request is completed */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode == Constants.POST_NOTIFICATIONS_PERMISSION_REQUEST_CODE) {
            delayedResultHandler.success(bubbleManager.hasPostNotificationsPermission())
            return true
        }

        return false
    }
}
