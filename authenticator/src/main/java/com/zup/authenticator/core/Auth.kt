package com.zup.authenticator.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.zup.authenticator.AuthApplication
import com.zup.authenticator.AuthApplicationCallback
import com.zup.authenticator.AuthDeclaration
import com.zup.authenticator.R

internal class Auth(
    private val activity: Activity?,
    private val callbackError: AuthApplicationCallback?,
    private val callbackSuccess: AuthApplicationCallback?,
) {
    init {
        if (activity == null) {
            throw AuthException.AuthInvalidException("Sem activity")
        }

        this.initCallAuth()
    }

    private fun initCallAuth() {
        // TODO: inicia a tela de validação do codigo
        val authFragment = ValidFragment.newInstance(callbackSuccess, callbackError)
        val manager = (activity as AppCompatActivity).supportFragmentManager
        authFragment.setStyle(STYLE_NORMAL, R.style.Theme_AppCompat_Light_NoActionBar)
        authFragment.show(manager, "telaDeAutenticacao")
    }
}

internal fun startAuthApplication(appDeclaration: AuthDeclaration? = null): AuthApplication {
    val authApplication = AuthApplication()
    appDeclaration?.invoke(authApplication)
    Auth(
        activity = authApplication.getActivity(),
        callbackError = authApplication.getCallbackError(),
        callbackSuccess = authApplication.getCallbackSuccess(),
    )
    return authApplication
}
