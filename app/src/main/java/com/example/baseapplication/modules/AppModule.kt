package com.example.baseapplication.modules

import android.app.Application
import dagger.Module
import dagger.Provides


@Module
class AppModule(val app: Application) {
    @Provides
    fun provideApp(): Application = app
}