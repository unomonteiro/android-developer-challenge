package io.monteirodev.marvel.commons

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.monteirodev.marvel.R
import io.monteirodev.marvel.models.Image
import java.math.BigInteger
import java.security.MessageDigest

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(image: Image) {
    if (TextUtils.isEmpty(image.path) || image.path.contains("image_not_available")) {
        Glide.with(context).load(R.drawable.image_not_found).into(this)
    } else if (image.path.contains("picsum")) {
        Glide.with(context).load(image.path).into(this)
    } else {
        Glide.with(context)
                .load(image.path+"/portrait_incredible."+image.extension).into(this)
    }
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}