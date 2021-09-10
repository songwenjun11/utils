package com.realize.common.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
fun Any.toJson(gson: Gson): String {
    return gson.toJson(this)
}

fun Any.toJson(): String {
    return toJson(Gson())
}

fun <B> String.fromJson(tClass: Class<B>, gson: Gson = Gson()): B {
    return gson.fromJson(this, tClass)
}

fun <B> String.fromJson(gson: Gson, getType: () -> TypeToken<B>): B {
    return fromJson(getType)
}

fun <B> String.fromJson(getType: () -> TypeToken<B>): B {
    return Gson().fromJson(this, getType().type)
}