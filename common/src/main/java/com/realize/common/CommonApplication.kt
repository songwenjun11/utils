package com.realize.common

import android.app.Application
import android.content.Context
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
open class CommonApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        ToastUtils.init(context)
    }
}