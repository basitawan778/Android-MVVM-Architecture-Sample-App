package com.example.baseapplication.modules

import android.content.Context
import com.example.baseapplication.network.LocalApi
import com.example.baseapplication.network.LocalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext application: Context): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(logger)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                        .header("x-rapidapi-host", "bodybuilding-quotes1.p.rapidapi.com")
                        .header("x-rapidapi-key","e4eaf60fefmsheadb224388efb58p184559jsn83e332fc7733")
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkApi(client: OkHttpClient): LocalApi {
        return Retrofit.Builder()
            .baseUrl("https://bodybuilding-quotes1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LocalApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRegistrationService(localApi: LocalApi): LocalService = LocalService(localApi)
}
