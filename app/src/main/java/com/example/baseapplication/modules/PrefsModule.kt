package com.example.baseapplication.modules

import android.app.Application
import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import com.example.baseapplication.utils.SharePreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton



@Module
class PrefsModule {

    @Provides
    @Singleton
    @typeOfContext(CONTEXT_APP)
    fun provideSharedPrefs(app: Application): SharePreferencesHelper {
        return SharePreferencesHelper(app)
    }

    @Provides
    @Singleton
    @typeOfContext(CONTEXT_ACTIVITY)
    fun provideSharedPrefsAct(activity: AppCompatActivity): SharePreferencesHelper {
        return SharePreferencesHelper(activity)
    }

    @Provides
    @Singleton
    @typeOfContext(CONTEXT_SERVICE)
    fun provideSharedPrefsService(service: Service): SharePreferencesHelper {
        return SharePreferencesHelper(service)
    }
}

const val CONTEXT_APP = "app_ctx"
const val CONTEXT_ACTIVITY = "activity_ctx"
const val CONTEXT_SERVICE = "service_ctx"


@Qualifier
annotation class typeOfContext(val type: String)