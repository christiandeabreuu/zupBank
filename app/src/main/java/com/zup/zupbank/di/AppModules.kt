package com.zup.zupbank.di

import com.zup.zupbank.data.DatabaseHelper
import com.zup.zupbank.data.UserRepositoryImpl
import com.zup.zupbank.domain.repository.UserRepository
import com.zup.zupbank.domain.useCase.login.AuthUseCase
import com.zup.zupbank.domain.useCase.register.RegisterUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateEmailUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateNameUseCase
import com.zup.zupbank.domain.useCase.validate.ValidatePasswordUseCase
import com.zup.zupbank.domain.useCase.validate.ValidateRepeatedPasswordUseCase
import com.zup.zupbank.ui.preLogin.login.LoginViewModel
import com.zup.zupbank.ui.preLogin.register.RegisterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    single { DatabaseHelper.getInstance(androidApplication())?.userDao() }
}

val repositoryModule = module {

    factory<UserRepository> {
        UserRepositoryImpl(get())
    }
}

val useCaseModule = module {
    factory { ValidateEmailUseCase() }
    factory { ValidatePasswordUseCase() }
    factory { ValidateNameUseCase() }
    factory { ValidateRepeatedPasswordUseCase() }

    factory { AuthUseCase(get(), get(), get()) }
    factory { RegisterUseCase(get(), get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get(), get(), get()) }
}
