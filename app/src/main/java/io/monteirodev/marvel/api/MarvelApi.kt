package io.monteirodev.marvel.api

import io.monteirodev.marvel.models.ComicDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://developer.marvel.com/documentation/authorization
 * md5(ts+privateKey+publicKey)
 * */
interface MarvelApi {
    @GET("v1/public/comics")
    fun getComicData(
            @Query("ts") timestamp: String,
            @Query("apikey") apikey: String,
            @Query("hash") hash: String,
            @Query("offset") offset: Int
    ): Call<ComicDataWrapper>

}