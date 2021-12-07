package com.example.baseapplication.utils

import com.example.baseapplication.utils.RequestHandler


interface NetworkListener {
    fun onLoading(obj: RequestHandler)
    fun onSuccess(obj: RequestHandler)
    fun onError(obj: RequestHandler)
}