package com.example.ecology.di

import com.example.ecology.ui.screen.allreport.AllReportViewModel
import com.example.ecology.ui.screen.login.LogInViewModel
import com.example.ecology.ui.screen.myreport.MyReportViewModel
import com.example.ecology.ui.screen.mediaaccess.MediaAccessViewModel
import com.example.ecology.ui.screen.newreport.NewReportViewModel
import com.example.ecology.ui.screen.signup.SignUpViewModel
import com.example.ecology.ui.screen.startactivity.StartActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<StartActivityViewModel>{
        StartActivityViewModel()
    }
    viewModel<NewReportViewModel> {
        NewReportViewModel(get(), get())
    }
    viewModel<MyReportViewModel> {
        MyReportViewModel(get(), get())
    }
    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }
    viewModel<LogInViewModel> {
        LogInViewModel(get())
    }
    viewModel<MediaAccessViewModel> {
        MediaAccessViewModel(get())
    }
    viewModel<AllReportViewModel> {
        AllReportViewModel(get(), get())
    }
}