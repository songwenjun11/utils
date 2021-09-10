package com.realize.common.ext

import com.google.gson.Gson

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
/**
 * 简单的集合深拷贝
 */
fun <T> List<T>.copy(): List<T> {
    val list = mutableListOf<T>()
    val listJson = toJson()
    val listCopy = listJson.fromJson(list.javaClass)
    return listCopy
}