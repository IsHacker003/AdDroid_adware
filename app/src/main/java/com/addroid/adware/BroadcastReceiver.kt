package com.addroid.adware

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.BroadcastReceiver
import android.os.Build

class ShowAds : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        val i : Intent = Intent(context, MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context?.startActivity(i);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context?.startForegroundService(Intent(context, AdsService::class.java))
        } else {
            context?.startService(Intent(context, AdsService::class.java))
        }
    }
}
