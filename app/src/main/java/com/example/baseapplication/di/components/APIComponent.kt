package com.example.baseapplication.di.components

import com.example.baseapplication.modules.APIModule
import com.example.baseapplication.modules.AppModule
import com.example.baseapplication.network.LocalService
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(APIModule::class, AppModule::class))
interface APIComponent {
    fun injectAPI(service: LocalService)

}