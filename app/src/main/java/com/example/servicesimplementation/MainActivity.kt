package com.example.servicesimplementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.servicesimplementation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.i(TAG, "Thread running on " + Thread.currentThread().name)

        val serviceIntent = Intent(this, MyService::class.java)
        binding.btnStartService.setOnClickListener {
            startMyService(serviceIntent)
        }

        binding.btnStopService.setOnClickListener {
            stopMyService(serviceIntent)
        }
    }

    private fun startMyService(intent: Intent) {
        startService(intent)
    }

    private fun stopMyService(intent: Intent) {
        stopService(intent)
    }
}