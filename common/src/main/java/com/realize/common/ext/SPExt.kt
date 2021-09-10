package com.realize.common.ext

import android.content.Context
import com.realize.common.CommonApplication
import com.realize.ktutils.utils.SharedPrefrenceUtils
import java.io.Serializable

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
//---------------------------------Serializable操作------------------------------------------------------
fun Serializable.save(context: Context, key: String) =
    SharedPrefrenceUtils.putObject(context, key, this)

fun Serializable.save(key: String) =
    save(CommonApplication.context, key)

fun <T : Serializable> Any.getSerializable(context: Context, key: String) =
    SharedPrefrenceUtils.getObject<T>(context, key)

fun <T : Serializable> Any.getSerializable(key: String) =
    SharedPrefrenceUtils.getObject<T>(CommonApplication.context, key)

//---------------------------------String操作------------------------------------------------------
fun String.save(context: Context, key: String) = SharedPrefrenceUtils.saveString(context, key, this)

fun String.save(key: String) = save(CommonApplication.context, this)

fun Any.getString(context: Context, key: String) = SharedPrefrenceUtils.getString(context, key)

fun Any.getString(key: String) = getString(CommonApplication.context, key)

//---------------------------------Boolean操作------------------------------------------------------
fun Boolean.save(context: Context, key: String) =
    SharedPrefrenceUtils.saveBoolean(context, key, this)

fun Boolean.save(key: String) = save(CommonApplication.context, key)

fun Any.getBoolean(context: Context, key: String) = SharedPrefrenceUtils.getBoolean(context, key)

fun Any.getBoolean(key: String) = getBoolean(CommonApplication.context, key)

//---------------------------------Mutablelist操作------------------------------------------------------
fun <T : Serializable> MutableList<T>.save(context: Context, key: String) =
    SharedPrefrenceUtils.putSerializableList(context, key, this)

fun <T : Serializable> MutableList<T>.save(key: String) = save(CommonApplication.context, key)

fun <T : Serializable> MutableList<T>.getMutableList(context: Context, key: String) =
    SharedPrefrenceUtils.getSerializableList<T>(context, key)

fun <T : Serializable> MutableList<T>.getMutableList(key: String) =
    getMutableList(CommonApplication.context, key)