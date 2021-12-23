package com.example.servicesimplementation

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService

class MyService() : JobIntentService() {

    private val TAG = "ServiceDemo"

    private var isRandomNumberGeneratorOn = false
    private var generatedRandomNumber = 0

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MyService::class.java, 101, intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.i(TAG, "onStartCommand")
//        isRandomNumberGeneratorOn = true
//        Thread(Runnable {
//            generateRandomNumber()
//        }).start()
//        return super.onStartCommand(intent, flags, startId)
//    }

//    override fun onHandleIntent(intent: Intent?) {
//        Log.i(TAG, "OnHandleIntent")
//        isRandomNumberGeneratorOn = true
//        generateRandomNumber()
//    }

    //Works same as onHandleIntent
    override fun onHandleWork(intent: Intent) {
        Log.i(TAG, "OnHandleWork")
        Log.i(TAG, "Thread running on " + Thread.currentThread().name)
        isRandomNumberGeneratorOn = true
        generateRandomNumber()
        //stopSelf()
    }

    override fun onStopCurrentWork(): Boolean {
        return true
    }

//    override fun onBind(intent: Intent): IBinder {
//        Log.i(TAG, "onBind")
//        return MyBinder()
//    }
//
//    override fun onUnbind(intent: Intent?): Boolean {
//        Log.i(TAG, "onUnbind")
//        return super.onUnbind(intent)
//    }
//
//    override fun onDestroy() {
//        isRandomNumberGeneratorOn = false
//        Log.i(TAG, "onDestroy")
//    }

    private fun generateRandomNumber() {
        while (isRandomNumberGeneratorOn) {
            Thread.sleep(1000)
            generatedRandomNumber = (0..100).random()
            Log.i(TAG, generatedRandomNumber.toString())
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