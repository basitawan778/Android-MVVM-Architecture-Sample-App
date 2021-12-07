package com.example.baseapplication.views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.example.baseapplication.utils.RequestHandler
import com.example.baseapplication.utils.NetworkListener
import com.example.baseapplication.utils.OnViewClickListener
import com.example.baseapplication.viewModels.BaseViewModel
import com.jsbl.genix.views.dialogs.ProgressDialog


abstract class BaseActivity<VM : BaseViewModel/*, DB : ViewDataBinding*/>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity(), OnViewClickListener, NetworkListener {

    lateinit var dialogP: ProgressDialog
    lateinit var requestObserver: Observer<RequestHandler>
    var isConnected = false
/*
    @LayoutRes
    abstract fun getLayoutRes(): Int*/

 /*   val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }
*/
    val viewModel by lazy {
        ViewModelProvider(this).get(mViewModelClass)
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
//
//        setContentView(getLayoutRes())
        super.onCreate(savedInstanceState)

        onInject()
//        if (isNetworkAvailablee(this)) {
        setRequestHandler()
        /*   } else {
               dismissDialog()
               showShort(this, "" + resources.getString(R.string.internt_problem))
           }*/
        initViewModel(viewModel)

    }


    fun setRequestHandler() {
        requestObserver = Observer<RequestHandler> { t ->
            if (t != null) {
                if (t.loading && !t.isSuccess) {
                    onLoading(t)
                } else if (!t.loading && !t.isSuccess) {
                    dismissDialog()
                    onError(t)
                } else if (!t.loading && t.isSuccess) {
                    dismissDialog()
                    onSuccess(t)
                }
            } else {
            }
        }
        viewModel.requestHandlerMLD.observe(this, requestObserver)
    }


    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */


    abstract fun initViewModel(viewModel: VM)

    protected fun showPDialog() {
        dialogP = ProgressDialog.newInstance()
        dialogP.showAllowingStateLoss(supportFragmentManager, "progress")
        dialogP.isCancelable = false
    }

    protected fun dismissDialog() {
        if (this::dialogP.isInitialized)
            if (dialogP.isAdded)
                dialogP.dismiss()
    }

    open fun isNetworkAvailablee(context: Context): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isConnected = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                activeNetworkInfo?.run {
                    this@BaseActivity.isConnected = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return false
    }
}
