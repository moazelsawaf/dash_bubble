package dev.moaz.dash_bubble.src

import android.app.Notification
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.torrydo.floatingbubbleview.BubbleBehavior
import com.torrydo.floatingbubbleview.FloatingBubble
import com.torrydo.floatingbubbleview.FloatingBubbleService
import com.torrydo.floatingbubbleview.Route
import dev.moaz.dash_bubble.R

/** BubbleService is the service that will be started when the bubble is started. */
class BubbleService : FloatingBubbleService() {
    private lateinit var bubbleOptions: BubbleOptions

    /** This method is called when the service is started
     * It initializes the bubble with the options passed to from the intent and starts the service.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bubbleOptions = intent?.getParcelableExtra(Constants.BUBBLE_OPTIONS_INTENT_EXTRA)!!

        showBubbles()
        showNotification()

        return super.onStartCommand(intent, flags, startId)
    }

    /** This method is called when the service is created.
     * It is setting the initial route of the bubble to be empty to avoid calling setupBubble method automatically.
     */
    override fun initialRoute(): Route {
        return Route.Empty
    }

    /** This method is called when the service is created.
     * It defines the initial configuration of the notification that will be shown when the bubble is running.
     * It works only for android 8 and higher
     */
    override fun initialNotification(): Notification? {
        return null;
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
            .opacity(bubbleOptions.opacity!!.toFloat())
            .behavior(BubbleBehavior.values()[bubbleOptions.closeBehavior!!])
            .distanceToClose(bubbleOptions.distanceToClose!!.toInt())
            .addFloatingBubbleListener(BubbleCallbackListener(this))
    }

    /** This method defines the notification configuration and shows it. */
    private fun showNotification() {
        val notificationTitle =
            bubbleOptions.notificationTitle ?: Helpers.getApplicationName(applicationContext)

        val notificationIcon = Helpers.getDrawableId(
            applicationContext,
            bubbleOptions.notificationIcon,
            R.drawable.default_bubble_icon
        )

        val notification = NotificationCompat.Builder(this, channelId())
            .setOngoing(true)
            .setContentTitle(notificationTitle)
            .setContentText(bubbleOptions.notificationText)
            .setSmallIcon(notificationIcon)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()

        notify(notification)
    }

    /** This method is called when the app is closed.
     * It stops the service if the keepAliveWhenAppExit option is false.
     */
    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        if (!bubbleOptions.keepAliveWhenAppExit!!) stopSelf()
    }
}