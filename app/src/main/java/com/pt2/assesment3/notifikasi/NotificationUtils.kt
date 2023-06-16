import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.pt2.assesment3.R

object NotificationUtils {
    private const val CHANNEL_ID = "my_channel_id"
    private const val NOTIFICATION_ID = 0

    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification(context: Context, title: String, message: String) {
        createNotificationChannel(context)

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)

        if (checkNotificationPermission(context)) {
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    private fun checkNotificationPermission(context: Context): Boolean {
        val permission = Manifest.permission.VIBRATE
        val result = ContextCompat.checkSelfPermission(context, permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context) {
        val channelName = "My Channel"
        val channelDescription = "Description of My Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.RED
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
