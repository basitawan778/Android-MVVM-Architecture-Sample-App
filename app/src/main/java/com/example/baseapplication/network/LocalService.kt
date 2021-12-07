package com.example.baseapplication.network

import androidx.hilt.lifecycle.ViewModelInject
import com.example.baseapplication.models.quotes.GetRandomQuotes
import retrofit2.Call
import javax.inject.Inject


class LocalService
     @Inject constructor(
     private val localApi: LocalApi
    ){
    // Get Quotes
   /* fun getQuotes(): Call<GetRandomQuotes> {
        return localApi.getRandomQuotes()
    }*/
    fun getQuotes(pageNo: String): Call<ArrayList<GetRandomQuotes>> {
        return localApi.getRandomQuotes(pageNo)
    }
}