package io.monteirodev.marvel.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class ComicDataWrapper(var code:Int,
                       val data: ComicDataContainer)

class ComicDataContainer(val results:List<Comic> )

@Parcelize
data class Comic(var title: String,
                 var description :String,
                 var thumbnail: Image) : Parcelable

@Parcelize
data class Image(var path:String,
            var extension:String) : Parcelable