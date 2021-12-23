package com.example.servicesimplementation

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.servicesimplementation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "ServiceDemo"

    private lateinit var binding: ActivityMainBinding

    private var isBound = false
    private lateinit var serviceConnection: ServiceConnection
    private lateinit var myService: MyService

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

        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                isBound = true
                val binder = service as MyService.MyBinder
                myService = binder.getMyService()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }

        }

        binding.btnBindService.setOnClickListener {
            bindToMyService(serviceIntent)
        }

        binding.btnUnbindService.setOnClickListener {
            unbindFromMyService(serviceIntent)
        }

        binding.btnGetData.setOnClickListener {
            updateUI()
        }
    }

    private fun startMyService(intent: Intent) {
        startService(intent)
        Toast.makeText(this, "Service started succesfully!", Toast.LENGTH_SHORT).show()
    }

    private fun stopMyService(intent: Intent) {
        stopService(intent)
        Toast.makeText(this, "Service stopped succesfully!", Toast.LENGTH_SHORT).show()
    }

    private fun bindToMyService(intent: Intent) {
        if(!isBound) {
            bindService(intent, serviceConnection, BIND_AUTO_CREATE)
            Toast.makeText(this, "Service bound succesfully!", Toast.LENGTH_SHORT).show()
            isBound = true
            return
        }

        Toast.makeText(this, "Already bound to the service!", Toast.LENGTH_SHORT).show()
    }

    private fun unbindFromMyService(intent: Intent) {
        if(isBound) {
            unbindService(serviceConnection)
            Toast.makeText(this, "Service unbound succesfully!", Toast.LENGTH_SHORT).show()
            isBound = false
            return
        }

        Toast.makeText(this, "Service already unbounded!", Toast.LENGTH_SHORT).show()
    }

    private fun updateUI() {
        if(isBound) {
            binding.tvRandomNumber.text = myService.getRandomNumber().toString()
        }
    }
}