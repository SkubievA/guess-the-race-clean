package skubyev.anton.guesstherace.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import skubyev.anton.guesstherace.R

@RequiresApi(Build.VERSION_CODES.O)
class NotificationUtils(base: Context) : ContextWrapper(base) {

    private var mManager: NotificationManager? = null

    val manager: NotificationManager
        get() {
            if (mManager == null) {
                mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return mManager as NotificationManager
        }

    init {
        createChannels()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannels() {

        // create android channel
        val androidChannel = NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true)
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true)
        // Sets the notification light color for notifications posted to this channel
        androidChannel.lightColor = Color.GREEN
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        manager.createNotificationChannel(androidChannel)
    }

    fun getAndroidChannelNotification(title: String, body: String): Notification.Builder {
        return Notification.Builder(applicationContext, ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_chevron_down)
                .setAutoCancel(true)
    }

    companion object {
        val ANDROID_CHANNEL_ID = "skubyev.anton.guesstherace.ANDROID"
        val ANDROID_CHANNEL_NAME = "ANDROID CHANNEL"
    }
}