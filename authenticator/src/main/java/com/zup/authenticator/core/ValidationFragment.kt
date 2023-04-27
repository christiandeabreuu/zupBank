//package com.zup.authenticator.core
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.annotation.RequiresApi
//import androidx.core.app.NotificationCompat
//import androidx.fragment.app.Fragment
//import com.zup.authenticator.R
//import com.zup.authenticator.core.Constants.CHANNEL_ID
//import com.zup.authenticator.core.extension.toast
//import com.zup.authenticator.core.extension.viewBinding
//import com.zup.authenticator.databinding.FragmentValidationBinding
//
//class ValidationFragment : Fragment(R.layout.fragment_validation) {
//    private val binding by viewBinding(FragmentValidationBinding::bind)
//
//    private lateinit var requestLauncher: ActivityResultLauncher<String>
//
//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
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
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        goToHome()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//    private fun askForNotificationPermisssion() {
//        requestLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
//    }
//
//    private fun showNotification() {
//        val notificationManager =
//            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
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
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun goToHome() {
//        binding.btnValidation.setOnClickListener {
////            findNavController().navigate(R.id.action)
//        }
//    }
//}
//
//        binding.etAuth1.addTextChangedListener(
//            object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int,
//                ) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun afterTextChanged(s: Editable?) {
//                    binding.etAuth2.requestFocus()
//                }
//            }
//        )
