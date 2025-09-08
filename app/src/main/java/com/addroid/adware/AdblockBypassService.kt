package com.addroid.adware

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.addroid.adware.MainActivity.Companion.AD_UNIT_ID
import com.addroid.adware.MainActivity.Companion.TAG
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdblockBypassService : Service() {
    private var interstitialAd: InterstitialAd? = null
    private var adIsLoading: Boolean = false

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        loadAd()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("LocalService", "Received start id " + startId + ": " + intent)
        return START_STICKY
    }

    private fun loadAd() {
        // Request a new ad if one isn't already loaded.
        if (adIsLoading || interstitialAd != null) {
            return
        }
        adIsLoading = true

        // [START load_ad]
        InterstitialAd.load(
            this,
            AD_UNIT_ID,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    interstitialAd = ad
                    // [START_EXCLUDE silent]
                    adIsLoading = false
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        applicationContext.startForegroundService(Intent(this@AdblockBypassService, AdsService::class.java))
                    } else {
                        applicationContext.startService(Intent(this@AdblockBypassService, AdsService::class.java))
                    }
                    // [END_EXCLUDE]
                    applicationContext.stopService(Intent(this@AdblockBypassService,
                        AdblockBypassService::class.java))
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    interstitialAd = null
                    // [START_EXCLUDE silent]
                    adIsLoading = false
                    Log.d(TAG, "Ad was not loaded.")
                    loadAd()
                    // [END_EXCLUDE]
                }
            },
        )
        // [END load_ad]
    }
}