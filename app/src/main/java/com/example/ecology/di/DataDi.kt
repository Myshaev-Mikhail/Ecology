package com.example.ecology.di

import androidx.room.Room
import com.example.ecology.data.ReportRepositoryImpl
import com.example.ecology.data.UserRepositoryImpl
import com.example.ecology.data.local.AppDatabase
import com.example.ecology.data.session.SessionManager
import com.example.ecology.domain.ReportRepository
import com.example.ecology.domain.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single { SessionManager() }
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "ecology_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().reportDao() }
    single { get<AppDatabase>().userDao() }

    single<ReportRepository> {
        ReportRepositoryImpl(get())
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}