package com.example.baseapplication.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.baseapplication.network.LocalService
import com.example.baseapplication.utils.APP_TAG
import com.example.baseapplication.utils.RequestHandler
import com.example.baseapplication.utils.logD
import com.example.baseapplication.utils.showShort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()
    val requestHandlerMLD = MutableLiveData<RequestHandler>()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    open fun sendRequest() {
        logD(APP_TAG, "method sendRequest")
        requestHandlerMLD.value = RequestHandler(
            loading = true,
            isSuccess = false,
            any = null
        )
    }

    open fun setError(
        obj: Any?,
        msg: String? = null,
        showAlert: Boolean = false,
        t: Throwable? = null
    ) {
        if (!showAlert) {
            if (msg != null) {
                showShort(getApplication(), msg)

            } else if (t != null) {
                showShort(getApplication(), t.localizedMessage)
            } else {
                if (obj is Response<*>) {
                    if (obj.code() == 401) {
                    }
                    if (obj.errorBody() != null) {
                        val msg2 = obj.errorBody()!!.string()
                        if (msg2.isNullOrEmpty()) {
                            showShort(getApplication(), obj.message()!!)
                        } else {
                            try {
                                val trimMsg = msg2.replace("\"", "")
                                showShort(getApplication(), trimMsg.trim())

                            } catch (e: Exception) {
                                showShort(getApplication(), obj.message()!!)
                            }
                        }
                    } else {
                        showShort(getApplication(), obj.message()!!)

                    }

                } else if (obj is Call<*>) {
                    /* val call = obj as Call<*>
                     showShort(getApplication(),call.request )*/
//                    showShort(getApplication(), "Request Timeout")
                    showShort(getApplication(), ""+msg)

                } else {
                    showShort(getApplication(), "No Internet Connection")
                }
            }

//            logD("**parsingIssue", "1 : method error")
            requestHandlerMLD.value = RequestHandler(
                loading = false,
                isSuccess = false,
                any = obj
            )
        }
        else {
            logD("**parsingIssue", "2 : method error")
            if (msg != null) {
                requestHandlerMLD.value = RequestHandler(
                    loading = false,
                    isSuccess = false,
                    showAlert = true,
                    any = msg
                )

            } else {
                requestHandlerMLD.value = RequestHandler(
                    loading = false,
                    isSuccess = false,
                    showAlert = true,
                    any = obj
                )
            }
        }

    }


    open fun setSuccess(obj: Any?) {
        logD(APP_TAG, "method setSuccess")
        requestHandlerMLD.value = RequestHandler(
            loading = false,
            isSuccess = true,
            any = obj
        )
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}