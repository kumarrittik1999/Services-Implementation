package com.example.servicesimplementation

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "MyService"

    private var isBound = false
    private var generatedRandomNumber = 0

    override fun onCreate() {
        Log.i(TAG, "Thread running on " + Thread.currentThread().name)
        Log.i(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i(TAG, "onBind")
        isBound = true
        Thread(Runnable {
            generateRandomNumber()
        }).start()
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind")
        isBound = false
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
    }

    private fun generateRandomNumber() {
        while(isBound) {
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