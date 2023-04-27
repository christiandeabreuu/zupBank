package com.zup.authenticator.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.zup.authenticator.AuthApplicationCallback
import com.zup.authenticator.R
import com.zup.authenticator.core.Constants.CHANNEL_ID
import com.zup.authenticator.core.extension.toast
import com.zup.authenticator.databinding.FragmentValidationBinding

class ValidFragment : DialogFragment(R.layout.fragment_validation) {

    private lateinit var binding: FragmentValidationBinding
    private lateinit var requestLauncher: ActivityResultLauncher<String>

    private lateinit var notificationManager: NotificationManager

    private val viewModel: ValidViewModel by lazy {
        ViewModelProvider(this)[ValidViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()

        requestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                showNotification()
            } else {
                toast("Permissão de notificaçao negada")
            }
        }
        askForNotificationPermisssion()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermisssion() {
        requestLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun showNotification() {
        notificationManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setContentTitle("Validação de Senha")
            .setContentText("Seu codigo é 1536")
            .setSmallIcon(R.drawable.ic_notifications)
            .build()
        notificationManager.notify(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {

        var callbackSuccess: AuthApplicationCallback? = null
        var callbackError: AuthApplicationCallback? = null

        fun newInstance(
            callbackSuccess: AuthApplicationCallback?,
            callbackError: AuthApplicationCallback?,
        ): ValidFragment {
            this.callbackSuccess = callbackSuccess
            this.callbackError = callbackError
            return ValidFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        callbackSuccess?.let { it() }
//        callbackError?.invoke()
        validationButtom()
        closeButtom()
    }

    private fun closeButtom() {
        binding.ibCloseButtom.setOnClickListener {
            callbackError?.invoke()
            dismiss()
        }
    }

    private fun validationButtom() {
        binding.btnValidation.setOnClickListener {
//            toast("chegouuu")
            callbackSuccess?.invoke()
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentValidationBinding.inflate(layoutInflater)
        return binding.root
    }
}
