package com.zup.authenticator

import android.app.Activity
import com.zup.authenticator.core.startAuthApplication

internal typealias AuthApplicationCallback = () -> Unit

typealias AuthDeclaration = AuthApplication.() -> Unit

open class AuthApplication {
    private var callbackSuccess: AuthApplicationCallback? = null
    private var callbackError: AuthApplicationCallback? = null
    private var activity: Activity? = null

    internal fun getActivity() = activity
    internal fun getCallbackError() = callbackError
    internal fun getCallbackSuccess() = callbackSuccess

    fun onSuccess(callbackSuccess: () -> Unit): AuthApplication {
        this.callbackSuccess = callbackSuccess
        return this
    }

    fun onError(callbackError: () -> Unit): AuthApplication {
        this.callbackError = callbackError
        return this
    }

    fun setActivity(activity: Activity) {
        this.activity = activity
    }
}

fun startCallAuth(appDeclaration: AuthDeclaration): AuthApplication =
    startAuthApplication(appDeclaration)
