package com.realize.common.ext

import android.util.Log
import com.realize.common.utils.ToastUtils

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
fun String.showToast(isLong: Boolean = false) {
    if (isLong)
        ToastUtils.showShort(this)
    else
        ToastUtils.showLong(this)
    logv("toast")
}

fun String.loge(tag: String) {
    Log.e(tag, this)
}

fun String.logv(tag: String) {
    Log.v(tag, this)
}

fun String.logi(tag: String) {
    Log.i(tag, this)
}

fun String.logd(tag: String) {
    Log.d(tag, this)
}