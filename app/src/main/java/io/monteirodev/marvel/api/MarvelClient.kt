package io.monteirodev.marvel.api

import io.monteirodev.marvel.models.ComicDataWrapper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarvelClient {

    private val marvelApi: MarvelApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        marvelApi = retrofit.create(MarvelApi::class.java)
    }

    fun getComics(ts: String, apikey: String, hash: String, offset: Int): Call<ComicDataWrapper> {
        return marvelApi.getComicData(ts, apikey, hash, offset)
    }
}