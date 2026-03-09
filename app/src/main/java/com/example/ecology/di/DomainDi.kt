package com.example.ecology.di

import com.example.ecology.domain.LoginUserUseCase
import com.example.ecology.domain.SaveReportUseCase
import com.example.ecology.domain.SaveUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        SaveReportUseCase(get())
    }
    factory {
        SaveUserUseCase(get())
    }
    factory {
        LoginUserUseCase(get())
    }
}