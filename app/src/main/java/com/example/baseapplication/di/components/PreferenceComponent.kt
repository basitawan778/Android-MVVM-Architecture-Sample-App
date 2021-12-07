package com.example.baseapplication.di.components

import android.app.Activity
import android.app.Service
import com.example.baseapplication.modules.PrefsModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(PrefsModule::class))
interface PreferenceComponent {
    fun getPrefs(activity: Activity)
    fun getPrefsForService(service: Service)
}