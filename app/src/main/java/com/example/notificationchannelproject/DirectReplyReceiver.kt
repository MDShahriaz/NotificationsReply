package com.example.notificationchannelproject

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

class DirectReplyReceiver :BroadcastReceiver() {
    val mainActivity = MainActivity()
    override fun onReceive(context: Context?, intent: Intent?) {
       val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput != null){
             var replyText:CharSequence
             replyText = remoteInput.getCharSequence("key_text_reply").toString()
             val answer = Message(replyText,"")
            mainActivity.messages.add(answer)

        }
    }
}