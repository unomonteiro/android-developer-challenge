package io.monteirodev.marvel.features

import io.monteirodev.marvel.BuildConfig
import io.monteirodev.marvel.api.MarvelClient
import io.monteirodev.marvel.commons.md5
import io.monteirodev.marvel.models.Comic
import io.reactivex.Observable

class ComicsManager(private val api: MarvelClient = MarvelClient()) {
    fun getComics(after: Int): Observable<ArrayList<Comic>> {
        return Observable.create {
            subscriber ->

            val ts = System.currentTimeMillis().toString()
            val apikey = BuildConfig.marvel_public_key
            val hash = (ts + BuildConfig.marvel_private_key + apikey).md5()

            val callResponse = api.getComics(ts, apikey, hash, after)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val comicList = response.body()?.data?.results as ArrayList<Comic>
                subscriber.onNext(comicList)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }

    }
}