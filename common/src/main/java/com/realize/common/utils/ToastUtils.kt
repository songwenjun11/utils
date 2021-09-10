package com.realize.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
@SuppressLint("StaticFieldLeak")
object ToastUtils {
    private var context: Context? = null
    private var toast: Toast? = null

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    fun showShort(msg: String) {
        cancal()
        Log.v("toast.short", msg)
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    fun showLong(msg: String) {
        cancal()
        Log.v("toast.long", msg)
        toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        toast!!.show()
    }

    fun cancal() {
        toast?.cancel()
        toast = null
    }
}