package com.jubee.bookstore.app

import android.app.Application
import com.jubee.bookstore.di.ApplicationComponent
import com.jubee.bookstore.di.DaggerApplicationComponent

class BookstoreApplication : Application() {
    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
        instance = this
    }

    companion object {
        lateinit var instance: BookstoreApplication
    }
}