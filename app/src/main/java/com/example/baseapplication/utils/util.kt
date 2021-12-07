package com.example.baseapplication.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baseapplication.BuildConfig
import com.example.baseapplication.R

val Any.APP_TAG: String
    get() = "**logs::" + this::class.simpleName

fun logV(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.v(tag, msg)
}

// do something for a debug build
fun logD(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

fun logE(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}


/**
 * extension methods for Toasts.
 */
fun showShort(context: Context, msg: String) {
    if (BuildConfig.DEBUG) Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun showReleaseShort(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

// do something for a debug build
fun showLong(context: Context, msg: String) {
    if (BuildConfig.DEBUG) Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}