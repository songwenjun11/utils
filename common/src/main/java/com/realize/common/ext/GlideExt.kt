package com.realize.common.ext

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.realize.common.CommonApplication

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
fun ImageView.loadRes(resId: Int) {
    Glide.with(context).load(resId).into(this)
}

fun ImageView.loadUrl(path: String) {
    Glide.with(context).load(path).into(this)
}

fun ImageView.loadUri(uri: Uri) {
    Glide.with(context).load(uri).into(this)
}