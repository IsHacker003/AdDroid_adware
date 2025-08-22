package com.addroid.adware

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.google.android.gms.ads.MobileAds

class AdsService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addAction(Intent.ACTION_USER_PRESENT)
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED)
        intentFilter.addDataScheme("package")
        val rec = ShowAds()
        registerReceiver(rec, intentFilter)
        MobileAds.initialize(this@AdsService)
        return TODO("Provide the return value")
    }
}
