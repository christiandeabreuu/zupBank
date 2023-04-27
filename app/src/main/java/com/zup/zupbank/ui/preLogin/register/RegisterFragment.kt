package com.zup.zupbank.ui.preLogin.register

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zup.zupbank.R
import com.zup.zupbank.common.Resource
import com.zup.zupbank.common.extension.*
import com.zup.zupbank.databinding.FragmentRegisterBinding
import com.zup.zupbank.databinding.ItemBottomSheetBinding
import com.zup.zupbank.domain.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding by viewBinding(FragmentRegisterBinding::bind)
    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel.registerFormState.launchAndCollectIn(viewLifecycleOwner) { registerFormState ->
            binding.btnRegister.isEnabled = registerFormState.isDataValid
            registerFormState.userNameError?.let {
                binding.etUserName.error = it
            }
            registerFormState.userEmailError?.let {
                binding.etUserEmail.error = it
            }
            registerFormState.passwordError?.let {
                binding.etPassword.error = it
            }
            registerFormState.confirmPasswordError?.let {
                binding.etConfirmPassword.error = it
            }
        }

        registerViewModel.registerUiState.launchAndCollectIn(this) { uiState ->
            when (uiState) {
                is Resource.Initialize -> binding.loading.gone()
                is Resource.Loading -> binding.loading.visible()
                is Resource.Success -> successRegister(uiState.data)
                is Resource.Error -> showRegisterFailed(uiState.error)
            }
        }

//        this.showBottomSheet()
        this.events()
    }

    private fun showBottomSheet() {
        binding.btnRegister.setOnClickListener { bottomSheetDialog() }
    }

    private fun bottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val sheetBinding: ItemBottomSheetBinding =
            ItemBottomSheetBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(sheetBinding.root)
        dialog.show()
        dialog.isShowing.and(navBack())
    }

    private fun events() {
        binding.etUserName.doAfterTextChanged { handleDataChanged() }

        binding.etUserEmail.doAfterTextChanged { handleDataChanged() }

        binding.etPassword.doAfterTextChanged { handleDataChanged() }

        binding.etConfirmPassword.doAfterTextChanged { handleDataChanged() }

        binding.etPassword.actionDone { this.callRegister() }

        binding.btnRegister.setOnClickListener { this.callRegister() }
    }

    private fun callRegister() {
//        showBottomSheet()
        registerViewModel.register(
            requireActivity(),
            binding.etUserName.text.toString(),
            binding.etUserEmail.text.toString(),
            binding.etPassword.text.toString(),
        )
        clearFields()
        bottomSheetDialog()
    }

    private fun handleDataChanged() {
        registerViewModel.registerDataChanged(
            binding.etUserName.text.toString(),
            binding.etUserEmail.text.toString(),
            binding.etPassword.text.toString(),
            binding.etConfirmPassword.text.toString(),
        )
    }

    private fun successRegister(user: User?) {
        if (context == null || activity == null || user == null) {
            return
        }
    }

    private fun showRegisterFailed(error: Throwable?) {
        context ?: return
        binding.loading.gone()
        toast(error?.message)
    }

    private fun clearFields() {
        binding.etUserName.text?.clear()
        binding.etUserEmail.text?.clear()
        binding.etPassword.text?.clear()
        binding.etConfirmPassword.text?.clear()
    }
}
