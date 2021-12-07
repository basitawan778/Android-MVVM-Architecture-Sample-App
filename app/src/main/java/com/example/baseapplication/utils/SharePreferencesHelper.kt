package com.example.baseapplication.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class SharePreferencesHelper {

    // Test
    companion object {

        var prefs: SharedPreferences?= null
        var sharedEditor: SharedPreferences.Editor? = null

        @Volatile
        private var instance: SharePreferencesHelper? = null
        private var lock = Any()


        operator fun invoke(context: Context): SharePreferencesHelper =
            instance ?: kotlin.synchronized(lock) {
                instance ?: buildHelper(context).also {
                    instance = it
                }
            }

        private fun buildHelper(context: Context): SharePreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharePreferencesHelper()
        }
    }



    /*fun setRegPassword(password: String) {
        prefs?.edit(commit = true) {
            putString(SCOPE_PASS, password)
        }
    }
    fun getRegPassword(): String = if (prefs != null) {
        prefs?.getString(SCOPE_PASS, "")!!
    } else {
        ""
    }*/



}