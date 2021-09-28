package com.realize.route_lib

import android.app.Application
import android.content.Context
import java.io.File

/**
 * Created by SongWenjun
 * 2021/9/16
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
object RouteManager {
    private val map = mutableMapOf<String, Class<Any>>()

    @JvmStatic
    fun putAction(key: String, clazz: Class<Any>) {
        map[key] = clazz
    }

    fun init(context: Context){

    }

    fun navigation() {

    }
}