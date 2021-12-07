package com.example.baseapplication.utils


data class RequestHandler(
    var loading: Boolean,
    var isSuccess: Boolean,
    var showAlert: Boolean = false,
    var any: Any?
)