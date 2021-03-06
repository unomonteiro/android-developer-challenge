package io.monteirodev.marvel.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.monteirodev.marvel.R
import io.monteirodev.marvel.commons.inflate
import io.monteirodev.marvel.commons.loadImg
import io.monteirodev.marvel.models.Comic
import kotlinx.android.synthetic.main.comic_item.view.*

class ComicAdapter(val clickListener: (Comic) -> Unit) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>(){

    private var comicList = ArrayList<Comic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(parent.inflate(R.layout.comic_item))
    }

    override fun getItemCount(): Int = comicList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        return holder.bind(comicList[position], clickListener)
    }

    fun addComics(newComics: ArrayList<Comic>) {
        comicList.addAll(newComics)
        notifyDataSetChanged()
    }

    fun getComics() = comicList

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: Comic, clickListener: (Comic) -> Unit) = with(itemView) {
            comic_image_view.loadImg(comic.thumbnail)
            comic_title_text_view.text = comic.title
            setOnClickListener { clickListener(comic) }
        }
    }
}