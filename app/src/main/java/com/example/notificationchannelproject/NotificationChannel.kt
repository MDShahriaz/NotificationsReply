package com.example.notificationchannelproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService

 class NotificationChannel{

    val CHANNEL_1_ID = "channel1";
    val CHANNEL_2_ID = "channel2"
    lateinit var channel1: NotificationChannel
    lateinit var channel2: NotificationChannel
    public fun createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
             channel1 = NotificationChannel(CHANNEL_1_ID,"Channel 1", NotificationManager.IMPORTANCE_HIGH)
                .apply {
                    lightColor = Color.BLUE
                    enableLights(true)
                    enableVibration(true)
                }
            channel1.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            channel1.description = "This is Channel 1"

             channel2 = NotificationChannel(CHANNEL_2_ID,"Channel 2",NotificationManager.IMPORTANCE_LOW)
                .apply {
                    lightColor = Color.BLUE
                    enableLights(true)
                    enableVibration(true)
                }
            channel2.description = "This is channel 2"
            channel2.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
    }
}