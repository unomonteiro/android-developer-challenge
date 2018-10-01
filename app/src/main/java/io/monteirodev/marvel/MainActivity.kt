package io.monteirodev.marvel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import io.monteirodev.marvel.models.Comic
import io.monteirodev.marvel.models.Image
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val COMIC_LIST_KEY = "comic_list_key"
    private val comicAdapter by lazy { ComicAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        if (savedInstanceState != null && savedInstanceState.containsKey(COMIC_LIST_KEY)) {
            comicAdapter.addComics(savedInstanceState.getParcelableArrayList(COMIC_LIST_KEY))
        } else {
            addMockData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(COMIC_LIST_KEY, comicAdapter.getComics())
    }

    private fun initAdapter() {
        comic_list.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = comicAdapter
        }
    }

    private fun addMockData() {
        val comics = arrayListOf<Comic>()
        for (i in 1..20) {
            comics.add(Comic(
                    "title $i",
                    "description $i",
                    Image("", "")
            ))
        }
        comicAdapter.addComics(comics)
    }
}
