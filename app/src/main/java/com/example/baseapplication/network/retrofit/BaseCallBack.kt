package com.example.baseapplication.network.retrofit

import com.example.baseapplication.model.ApiResponse


abstract class BaseCallBack<T> {
    abstract fun onLoading()
    abstract fun onSuccess(response: ApiResponse<T>)
    abstract fun onFailure(response: ApiResponse<T>)
    abstract fun onSessionExpire()
}