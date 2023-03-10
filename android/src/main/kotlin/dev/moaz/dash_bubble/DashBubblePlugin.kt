package dev.moaz.dash_bubble

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
    PluginRegistry.ActivityResultListener {

    private lateinit var bubbleManager: BubbleManager
    private lateinit var channel: MethodChannel
    private lateinit var requestPermissionResultHandler: Result
    private lateinit var activityBinding: ActivityPluginBinding
    private lateinit var mActivity: Activity

    /** This broadcast receiver is used to listen for the tap on the bubble
     * It calls the onBubbleTap method when the bubble is tapped
     */
    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            onBubbleTap()
        }
    }

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
                Constants.REQUEST_PERMISSION -> {
                    if (bubbleManager.requestPermission() == true) {
                        result.success(true)
                        return
                    }

                    requestPermissionResultHandler = result
                }
                Constants.HAS_PERMISSION -> result.success(bubbleManager.hasPermission())
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
     * In this method we set the method call handler to null
     */
    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    /** This method is called when the activity is created
     * It does the following:
     * 1. Initializes the activity binding
     * 2. Registers the activity result listener
     * 3. Initializes the activity
     * 4. Initializes the bubble manager
     * 5. Registers the broadcast receiver
     */
    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activityBinding = binding
        activityBinding.addActivityResultListener(this);
        mActivity = activityBinding.activity

        bubbleManager = BubbleManager(mActivity)

        LocalBroadcastManager.getInstance(mActivity)
            .registerReceiver(messageReceiver, IntentFilter(Constants.BUBBLE_TAP_INTENT))
    }

    /** This method is called when the activity is recreated */
    override fun onDetachedFromActivityForConfigChanges() {}

    /** This method is called when the activity is recreated */
    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {}

    /** This method is called when the activity is destroyed
     * It removes the activity result listener and unregisters the broadcast receiver
     */
    override fun onDetachedFromActivity() {
        activityBinding.removeActivityResultListener(this);

        LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(messageReceiver)
    }

    /** This method is called when the permission request is completed */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == Constants.PERMISSION_REQUEST_CODE) {
            requestPermissionResultHandler.success(bubbleManager.hasPermission())
            return true
        }
        return false
    }

    /** This method is called when the bubble is tapped */
    private fun onBubbleTap() {
        channel.invokeMethod(Constants.ON_BUBBLE_TAP, null)
    }
}
