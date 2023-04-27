package com.zup.zupbank.ui.preLogin.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zup.authenticator.startCallAuth
import com.zup.zupbank.common.Resource
import com.zup.zupbank.domain.model.User
import com.zup.zupbank.domain.useCase.login.AuthUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateEmailUseCase
import com.zup.zupbank.domain.useCase.validate.ValidatePasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: AuthUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
) : ViewModel() {

    private val _loginForm = MutableStateFlow(LoginFormStates())
    val loginFormState: StateFlow<LoginFormStates> = _loginForm

    private val _loginUiState = MutableStateFlow<Resource<User>>(Resource.Initialize())
    val loginUiState: StateFlow<Resource<User>> = _loginUiState

    fun login(activity: Activity, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            // Ao fazer login já chama o loading
            _loginUiState.value = Resource.Loading()
            // Para simular o carregamento da internet
            delay(2000L)
            // Monta os parâmetros necessários para aplicar a regra de negócio
            val user = AuthUseCase.Params(email = email, password = password)

            loginUseCase.execute(user).catch { exception ->
                _loginUiState.value = Resource.Error(exception)
            }.collect {
                handleUser(activity = activity, user = it)
            }
        }

    private fun handleUser(activity: Activity, user: User) {
//        _loginUiState.value = Resource.Success(user)
        startCallAuth {
            setActivity(activity)
            onSuccess { _loginUiState.value = Resource.Success(user) }
            onError { _loginUiState.value = Resource.Error(null) }
        }
    }

    fun loginDataChanged(userEmail: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _loginForm.update { it.copy(isDataValid = false) }
            val validateEmail = validateEmailUseCase.execute(userEmail)

            if (!validateEmail.successful) {
                _loginForm.update { it.copy(usernameError = validateEmail.exception?.message) }
                return@launch
            } else {
                _loginForm.update { it.copy(usernameError = null) }
            }

            val validatePassword = validatePasswordUseCase.execute(password)
            if (!validatePassword.successful) {
                _loginForm.update { it.copy(passwordError = validatePassword.exception?.message) }
                return@launch
            } else {
                _loginForm.update { it.copy(passwordError = null) }
            }

            _loginForm.update { it.copy(isDataValid = true) }
        }
}
