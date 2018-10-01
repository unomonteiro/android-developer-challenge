package io.monteirodev.marvel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import io.monteirodev.marvel.features.ComicsManager
import io.monteirodev.marvel.models.Comic
import io.monteirodev.marvel.models.Image
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    protected var subscriptions = CompositeDisposable()

    private val COMIC_LIST_KEY = "comic_list_key"
    private val comicsManager by lazy { ComicsManager() }
    private val comicAdapter by lazy { ComicAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        if (savedInstanceState != null && savedInstanceState.containsKey(COMIC_LIST_KEY)) {
            comicAdapter.addComics(savedInstanceState.getParcelableArrayList(COMIC_LIST_KEY))
        } else {
//            comicAdapter.addComics(getMockData())
            requestComics()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(COMIC_LIST_KEY, comicAdapter.getComics())
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        subscriptions.clear()
    }

    private fun initAdapter() {
        comic_list.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = comicAdapter
        }
    }

    private fun getMockData(): ArrayList<Comic> {
        val comics = arrayListOf<Comic>()
        for (i in 1..20) {
            comics.add(Comic(
                    "title $i",
                    "description $i",
                    if (i==3) Image("","")
                    else Image("https://picsum.photos/200/300?image=$i", "")
            ))
        }
        return comics
    }

    private fun requestComics() {
        val subscription = comicsManager.getComics(comicAdapter.itemCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedComics ->
                            comicAdapter.addComics(retrievedComics)
                        },
                        { e ->
                            Snackbar.make(comic_list, e.message?: "Something went boom!",
                                    Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }
}
