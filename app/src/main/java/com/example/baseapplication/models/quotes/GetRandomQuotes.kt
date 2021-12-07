package com.example.baseapplication.models.quotes

import com.google.gson.annotations.SerializedName


data class GetRandomQuotes(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("quote")
    val quote: String?= null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("category")
    val category: String? = null

)