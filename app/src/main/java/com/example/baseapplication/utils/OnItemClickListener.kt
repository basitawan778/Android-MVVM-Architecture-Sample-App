package com.example.baseapplication.utils

import android.view.View


interface OnItemClickListener {
    fun onItemClick(view: View, pos: Int, obj: Any)
}