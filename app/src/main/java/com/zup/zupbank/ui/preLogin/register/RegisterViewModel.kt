package com.zup.zupbank.ui.preLogin.register

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zup.zupbank.common.Resource
import com.zup.zupbank.domain.model.User
import com.zup.zupbank.domain.useCase.register.RegisterUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateEmailUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateNameUseCase
import com.zup.zupbank.domain.useCase.validate.ValidatePasswordUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateRepeatedPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
) : ViewModel() {

    private val _registerForm = MutableStateFlow(RegisterFormStates())
    val registerFormState: StateFlow<RegisterFormStates> = _registerForm

    private val _registerUiState = MutableStateFlow<Resource<User>>(Resource.Initialize())
    val registerUiState: StateFlow<Resource<User>> = _registerUiState

    private val _stateUser = MutableStateFlow<Boolean>(false)
    val stateUser: StateFlow<Boolean> = _stateUser

    fun register(activity: Activity, name: String, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
//            // Ao fazer login já chama o loading
//            _registerUiState.value = Resource.Loading()
//            // Para simular o carregamento da internet
//            delay(2000L)
//            // Monta os parâmetros necessários para aplicar a regra de negócio
            val user = RegisterUseCase.Params(name = name, email = email, password = password)

            registerUseCase.execute(user).catch { exception ->
                _registerUiState.value = Resource.Error(exception)
            }.collect {
//                _registerUiState.value = Resource.Loading()
            }
        }

    fun registerDataChanged(
        name: String,
        email: String,
        password: String,
        repeatedPassword: String,
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            _registerForm.update { it.copy(isDataValid = false) }
            val validateName = validateNameUseCase.execute(name)
            if (!validateName.successful) {
                _registerForm.update { it.copy(userNameError = validateName.exception?.message) }
                return@launch
            } else {
                _registerForm.update { it.copy(userNameError = null) }
            }

            val validateEmail = validateEmailUseCase.execute(email)
            if (!validateEmail.successful) {
                _registerForm.update { it.copy(userEmailError = validateEmail.exception?.message) }
                return@launch
            } else {
                _registerForm.update { it.copy(userEmailError = null) }
            }

            val validatePassword = validatePasswordUseCase.execute(password)
            if (!validatePassword.successful) {
                _registerForm.update { it.copy(passwordError = validatePassword.exception?.message) }
                return@launch
            } else {
                _registerForm.update { it.copy(passwordError = null) }
            }

            val confirmValidatePassword =
                validateRepeatedPasswordUseCase.execute(password, repeatedPassword)
            if (!confirmValidatePassword.successful) {
                _registerForm.update { it.copy(confirmPasswordError = confirmValidatePassword.exception?.message) }
                return@launch
            } else {
                _registerForm.update { it.copy(confirmPasswordError = null) }
            }

            _registerForm.update { it.copy(isDataValid = true) }
        }
}
