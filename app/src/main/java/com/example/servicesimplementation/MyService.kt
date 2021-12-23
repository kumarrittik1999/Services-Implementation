package com.example.servicesimplementation

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "ServiceDemo"

    private var isRandomNumberGeneratorOn = false
    private var generatedRandomNumber = 0

    override fun onCreate() {
        Log.i(TAG, "Thread running on " + Thread.currentThread().name)
        Log.i(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        isRandomNumberGeneratorOn = true
        Thread(Runnable {
            generateRandomNumber()
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i(TAG, "onBind")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        isRandomNumberGeneratorOn = false
        Log.i(TAG, "onDestroy")
    }

    private fun generateRandomNumber() {
        while(isRandomNumberGeneratorOn) {
            Thread.sleep(1000)
            generatedRandomNumber = (0..100).random()
        }
    }

    fun getRandomNumber(): Int {
        return generatedRandomNumber
    }

    /*
        We return an IBinder interface object from OnBind method which helps in intercommunication
        between component bound to the service and the service

        We expose a method from the Binder class which returns the instance of MyService
    */
    inner class MyBinder : Binder() {
        fun getMyService(): MyService {
            return this@MyService
        }
    }

}