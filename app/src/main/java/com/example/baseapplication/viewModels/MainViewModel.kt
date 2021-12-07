package com.example.baseapplication.viewModels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.example.baseapplication.models.quotes.GetRandomQuotes
import com.example.baseapplication.network.LocalService
import com.example.baseapplication.utils.APP_TAG
import com.example.baseapplication.utils.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localService: LocalService, application: Application
): BaseViewModel(application) {

    fun getQuotes() {
        sendRequest()
        logD(APP_TAG, "Quotes method Called")
        localService.getQuotes("1").enqueue(object : Callback<ArrayList<GetRandomQuotes>> {
            override fun onFailure(call: Call<ArrayList<GetRandomQuotes>>, t: Throwable) {
                setError("There is no or poor internet connection. Please connect to stable internet connection and try again.")
            }
            override fun onResponse(
                call: Call<ArrayList<GetRandomQuotes>>,
                response: Response<ArrayList<GetRandomQuotes>>
            ) {
                if (response.code() == 200) {
                    setSuccess(response.body())
                    logD("**response",response.body().toString())
                }
                else {
                    setError(response, "")
                }
            }

        })
    }



}