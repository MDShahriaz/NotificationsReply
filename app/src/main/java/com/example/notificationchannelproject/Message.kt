package com.example.notificationchannelproject

import java.sql.Timestamp

class Message {
    private lateinit var text:CharSequence
    private var timestamp: Long = 0
    private  var sender:CharSequence
    constructor(text:CharSequence,sender:CharSequence){
        this.text = text
        this.sender = sender
        timestamp = System.currentTimeMillis()
    }

    fun getText():CharSequence{
        return text
    }

    fun getTimeStamp():Long{
        return timestamp
    }

    fun getSender():CharSequence{
        return sender
    }

}