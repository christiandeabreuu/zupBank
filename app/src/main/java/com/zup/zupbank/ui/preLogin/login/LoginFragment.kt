package com.zup.zupbank.ui.preLogin.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zup.zupbank.R
import com.zup.zupbank.common.Resource
import com.zup.zupbank.common.extension.*
import com.zup.zupbank.databinding.FragmentLoginBinding
import com.zup.zupbank.domain.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.loginFormState.launchAndCollectIn(viewLifecycleOwner) { loginFormState ->
            binding.btnLogin.isEnabled = loginFormState.isDataValid
            loginFormState.usernameError?.let {
                binding.etUserEmail.error = it
            }
            loginFormState.passwordError?.let {
                binding.etPassword.error = it
            }
        }

        loginViewModel.loginUiState.launchAndCollectIn(this) { uiState ->
            when (uiState) {
                is Resource.Initialize -> binding.pbLoading.gone()
                is Resource.Loading -> binding.pbLoading.visible()
                is Resource.Success -> successLogin(uiState.data)
                is Resource.Error -> showLoginFailed(uiState.error)
            }
        }

        this.events()
        this.goToRegister()
    }

    private fun goToRegister() {
        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun events() {
        binding.etUserEmail.doAfterTextChanged { handleDataChanged() }

        binding.etPassword.doAfterTextChanged { handleDataChanged() }

        binding.etPassword.actionDone { this.callLogin() }

        binding.btnLogin.setOnClickListener { this.callLogin() }
    }

    private fun callLogin() {
        loginViewModel.login(
            requireActivity(),
            binding.etUserEmail.text.toString(),
            binding.etPassword.text.toString(),
        )
    }

    private fun handleDataChanged() {
        loginViewModel.loginDataChanged(
            binding.etUserEmail.text.toString(),
            binding.etPassword.text.toString(),
        )
    }

    private fun successLogin(user: User?) {
        if (context == null || activity == null || user == null) {
            return
        }
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun showLoginFailed(error: Throwable?) {
        val errorMsg = error?.message ?: "Erro nao identificado"
        context ?: return
        binding.pbLoading.gone()
        toast(errorMsg) //error?.message
    }
}
