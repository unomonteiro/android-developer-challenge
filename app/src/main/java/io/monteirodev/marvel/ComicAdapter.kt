package io.monteirodev.marvel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.monteirodev.marvel.commons.inflate
import io.monteirodev.marvel.models.Comic
import kotlinx.android.synthetic.main.comic_item.view.*

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>(){

    private var comicList = ArrayList<Comic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(parent.inflate(R.layout.comic_item))
    }

    override fun getItemCount(): Int = comicList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        return holder.bind(comicList[position])
    }

    fun addComics(newComics: ArrayList<Comic>) {
        comicList.addAll(newComics)
        notifyDataSetChanged()
    }

    fun getComics() = comicList

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: Comic) = with(itemView) {
            comic_image_view.setImageResource(R.mipmap.ic_launcher)
            comic_title_text_view.text = comic.title
        }
    }
}