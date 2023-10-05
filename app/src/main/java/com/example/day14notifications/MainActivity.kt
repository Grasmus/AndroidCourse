package com.example.day14notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 101
        const val NOTIFICATION_ID_INBOX = 202
        const val NOTIFICATION_ID_MESSAGING = 302
        const val CHANNEL_ID = "Cat`s channel"
    }

    private var counter = NOTIFICATION_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MainActivity::class.java)

        intent.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val buttonShowNotification: Button = findViewById(R.id.button_show_notification)
        val buttonShowInboxNotification: Button = findViewById(R.id.button_show_notification_inbox)
        val buttonCancelAllNotification: Button = findViewById(R.id.button_cancel_alll_notifications)
        val buttonShowMessagingNotification: Button = findViewById(R.id.button_show_notification_messaging)

        createNotificationChannel()

        buttonShowNotification.setOnClickListener {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notification_important_24)
                .setContentTitle("Notification")
                .setContentText("It`s time to feed a cat!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                //.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.hungrycat))
                //.setTicker("The last warning!")
                .setAutoCancel(true)
                .setProgress(100, Random.nextInt(0, 100), false)
                .setDefaults(Notification.DEFAULT_SOUND and Notification.DEFAULT_VIBRATE)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setColor(Color.YELLOW)
                .addAction(R.drawable.baseline_food_bank_24, "Feed the cat", pendingIntent)
                .addAction(R.drawable.baseline_front_hand_24, "Pet the cat", pendingIntent)
                .addAction(R.drawable.baseline_block_24, "Decline", pendingIntent)
                .setStyle(NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.hungrycat))
                    .setBigContentTitle("Beautiful Cat")
                    .setSummaryText("Hungry cat"))

            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(NOTIFICATION_ID, builder.build())
                }
            }
        }

        buttonCancelAllNotification.setOnClickListener {
            NotificationManagerCompat.from(this).cancelAll()
        }

        buttonShowInboxNotification.setOnClickListener {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notification_important_24)
                .setContentTitle("Notification")
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine("This is first line")
                        .addLine("This is second line")
                        .addLine("This is third line")
                        .addLine("This is fourth line")
                        .addLine("This is fifth line")
                        .setBigContentTitle("This is Content Title.")
                        .setSummaryText("This is summary text.")
                )

            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(NOTIFICATION_ID_INBOX, builder.build())
                }
            }
        }

        buttonShowMessagingNotification.setOnClickListener {
            val sender = Person.Builder()
                .setName("Mursik")
                .build()

            val messagingStyle = NotificationCompat.MessagingStyle(sender)
                .addMessage("Owner, where is food?", Date().time, sender)

            val builder = NotificationCompat.Builder(this, "Cat channel")
                .setSmallIcon(R.drawable.pets_black_24dp)
                .setStyle(messagingStyle)

            val channel = NotificationChannel("Cat channel", "channel", NotificationManager
                .IMPORTANCE_DEFAULT).apply {
                description = "Feed cat"
            }

            with(NotificationManagerCompat.from(this)) {
                createNotificationChannel(channel)
                notify(NOTIFICATION_ID_MESSAGING, builder.build())
            }
        }
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "General notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        channel.description = "This is default channel used for all other notifications"

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
