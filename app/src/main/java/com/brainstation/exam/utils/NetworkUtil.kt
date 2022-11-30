package com.brainstation.exam.utils

import android.util.Log

class NetworkUtil {

    companion object{
        fun internetIsConnected(): Boolean {
            return try {
                val command = "ping -c 1 google.com"
                Log.d("atif##", "internet Available")
                Runtime.getRuntime().exec(command).waitFor() == 0
            } catch (e: Exception) {
                Log.d("atif##", "internet Unavailable")
                false
            }
        }
    }
}