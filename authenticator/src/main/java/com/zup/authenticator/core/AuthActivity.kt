package com.zup.authenticator.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.zup.authenticator.R
import com.zup.authenticator.core.extension.toast
import com.zup.authenticator.databinding.ActivityAuthBinding

//class AuthActivity : AppCompatActivity(R.layout.activity_auth) {
//
//    private lateinit var binding: ActivityAuthBinding
//
//    private lateinit var requestLauncher: ActivityResultLauncher<String>
//
//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAuthBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        createNotificationChannel()
//
//        requestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//            if (it) {
//                showNotification()
//            } else {
//                toast("Permissão de notificaçao negada")
//            }
//        }
//        askForNotificationPermisssion()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//    private fun askForNotificationPermisssion() {
//        requestLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
//    }
//
//    private fun showNotification() {
//        val notificationManager =
//            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
//            .setContentTitle("Validação de Senha")
//            .setContentText("Seu codigo é 1536")
//            .setSmallIcon(R.drawable.ic_notifications)
//            .build()
//        notificationManager.notify(1, notification)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.channel_name)
//            val descriptionText = getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//
//}
