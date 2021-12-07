package com.example.baseapplication.network

import com.example.baseapplication.models.quotes.GetRandomQuotes
import retrofit2.Call
import retrofit2.http.*


interface LocalApi {

  /*  @POST("movie/upcoming")
    fun upcomingMovies(@Body moviesRequestModel: MoviesRequestModel): Call<MoviesResponseModel>
*/

    @GET("quotes")
//    fun getRandomQuotes(): Call<GetRandomQuotes>
//    void getMyThing(@QueryMap Map<String, String> params, new Callback<String> callback);

    fun getRandomQuotes(
        @Query("page") pageNumber: String
    ): Call<ArrayList<GetRandomQuotes>>



}