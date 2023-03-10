package dev.moaz.dash_bubble.src

import android.app.Notification
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.torrydo.floatingbubbleview.FloatingBubble
import com.torrydo.floatingbubbleview.FloatingBubbleService
import dev.moaz.dash_bubble.R

/** BubbleService is the service that will be started when the bubble is started. */
class BubbleService : FloatingBubbleService() {

    private lateinit var bubbleOptions: BubbleOptions

    /** This method is called when the service is started
     * It initializes the bubble with the options passed to from the intent and starts the service.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bubbleOptions = intent?.getParcelableExtra(Constants.BUBBLE_OPTIONS_INTENT_EXTRA)!!

        return super.onStartCommand(intent, flags, startId)
    }

    /** This method defines the notification that will be shown when the bubble is running.
     * It is called when the service is started.
     *
     * It works only for android 8 and higher
     */
    override fun setupNotificationBuilder(channelId: String): Notification {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            return super.setupNotificationBuilder(channelId)
        }

        val notificationTitle =
            bubbleOptions.notificationTitle ?: Helpers.getApplicationName(applicationContext)

        val notificationIcon = Helpers.getDrawableId(
            applicationContext,
            bubbleOptions.notificationIcon,
            R.drawable.default_bubble_icon
        )

        return NotificationCompat.Builder(this, channelId)
            .setOngoing(true)
            .setContentTitle(notificationTitle)
            .setContentText(bubbleOptions.notificationText)
            .setSmallIcon(notificationIcon)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
    }

    /** This method defines the main setup of the bubble. */
    override fun setupBubble(action: FloatingBubble.Action): FloatingBubble.Builder {
        val bubbleIcon = Helpers.getDrawableId(
            applicationContext,
            bubbleOptions.bubbleIcon,
            R.drawable.default_bubble_icon
        )

        val closeIcon = Helpers.getDrawableId(
            applicationContext,
            bubbleOptions.closeIcon,
            R.drawable.ic_close_bubble
        )

        return FloatingBubble.Builder(this)
            .bubble(
                bubbleIcon,
                bubbleOptions.bubbleSize!!.toInt(),
                bubbleOptions.bubbleSize!!.toInt()
            )
            .bubbleStyle(null)
            .startLocation(
                bubbleOptions.startLocationX!!.toInt(),
                bubbleOptions.startLocationY!!.toInt()
            )
            .enableAnimateToEdge(bubbleOptions.enableAnimateToEdge!!)
            .closeBubble(
                closeIcon,
                bubbleOptions.bubbleSize!!.toInt(),
                bubbleOptions.bubbleSize!!.toInt()
            )
            .closeBubbleStyle(null)
            .enableCloseBubble(bubbleOptions.enableClose!!)
            .bottomBackground(bubbleOptions.enableBottomShadow!!)
            .addFloatingBubbleListener(object : FloatingBubble.Listener {
                override fun onDestroy() {}
                override fun onClick() {
                    onBubbleTap()
                    // Helpers.bringAppToForeground(applicationContext);
                }

                override fun onMove(x: Int, y: Int) {}
                override fun onUp(x: Int, y: Int) {}
                override fun onDown(x: Int, y: Int) {}
            })
            .opacity(1f)
    }

    /** This method is called when the bubble is tapped.
     * It sends a broadcast to the app to handle the tap.
     */
    private fun onBubbleTap() {
        val intent = Intent(Constants.BUBBLE_TAP_INTENT)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    /** This method is called when the app is closed.
     * It stops the service if the keepAliveWhenAppExit option is false.
     */
    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        if (!bubbleOptions.keepAliveWhenAppExit!!) stopSelf()
    }
}