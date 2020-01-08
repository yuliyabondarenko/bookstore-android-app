package com.jubee.bookstore.app

import android.app.Application
import com.jubee.bookstore.di.ApplicationComponent
import com.jubee.bookstore.di.DaggerApplicationComponent
import com.jubee.bookstore.di.module.NetworkModule
import com.jubee.bookstore.di.module.PersistenceModule
import com.jubee.bookstore.di.module.ViewModelModule

class BookstoreApplication : Application() {
    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .persistenceModule(PersistenceModule(this))
            .viewModelModule(ViewModelModule())
            .build()
        instance = this
    }

    companion object {
        lateinit var instance: BookstoreApplication
    }
}