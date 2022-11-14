package com.example.notificationchannelproject

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.example.notificationchannelproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val noti = NotificationChannel()
    var messages = ArrayList<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createChannel()
        messages.add(Message("Good morning!","tag"))
        messages.add(Message("Hello",""))
        messages.add(Message("Hi!","Jenny"))
        val remoteInput = RemoteInput.Builder("key_text_reply").setLabel("Your answer...")
            .build()

        val replyIntent = Intent(this,DirectReplyReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(this,0,replyIntent,0)

        val replyAction = NotificationCompat.Action.Builder(
            R.drawable.ic_baseline_reply_24,
            "Reply",
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()

        val messagingStyle = NotificationCompat.MessagingStyle("Me")
        messagingStyle.setConversationTitle("Group chat")

        for(mess in messages){
            val notificationMessage = NotificationCompat.MessagingStyle.Message(mess.getText(),mess.getTimeStamp(),mess.getSender())
            messagingStyle.addMessage(notificationMessage)
        }
        val notif1 = NotificationCompat.Builder(this,noti.CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setStyle(messagingStyle)
            .addAction(replyAction)
            .setColor(Color.BLUE)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setGroup("Group")
            .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_SUMMARY)

        val notif2 = NotificationCompat.Builder(this,noti.CHANNEL_1_ID)
            .setContentTitle("Sample title two")
            .setContentText("This is sample body for two")
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setGroup("Group")
            .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_SUMMARY)
        val notifManager = NotificationManagerCompat.from(this)
        binding.button1.setOnClickListener {
            notifManager.notify(1,notif1.build())
        }

    }

    fun createChannel(){
        val manager = getSystemService(NotificationManager::class.java)
        noti.createNotificationChannels()
        manager.createNotificationChannel(noti.channel1)
//        manager.createNotificationChannel(noti.channel2)
    }
}