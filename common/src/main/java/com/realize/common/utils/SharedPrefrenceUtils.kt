package com.realize.ktutils.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2016/6/13.
 */
object SharedPrefrenceUtils {
    private var sp: SharedPreferences? = null
    fun saveBoolean(context: Context?, key: String?, value: Boolean) {
        if (context == null) return
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putBoolean(key, value).commit()
    }

    fun getBoolean(context: Context?, key: String?, defValue: Boolean): Boolean {
        if (context == null) return false
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, defValue)
    }

    fun getBoolean(context: Context?, key: String?): Boolean {
        if (context == null) return false
        if (sp == null && context != null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, false)
    }

    fun saveString(context: Context?, key: String?, value: String?): Boolean {
        if (context == null) return false
        if (sp == null && context != null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        val `is` = sp!!.edit().putString(key, value).commit()
        Log.e("是否存储成功", "：$`is`")
        return `is`
    }

    fun getString(context: Context?, key: String?, defValue: String?): String? {
        if (context == null) return ""
        if (sp == null && context != null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, defValue)
    }

    fun getString(context: Context?, key: String?): String? {
        if (context == null) return ""
        if (sp == null && context != null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, "")
    }

    /**
     * 保存对象
     *
     * @param context 上下文
     * @param key     键
     * @param obj     要保存的对象（Serializable的子类）
     * @param <T>     泛型定义
    </T> */
    fun <T : Serializable?> putObject(context: Context, key: String, obj: T) =
        try {
            put(context, key, obj)
        } catch (e: Exception) {
            e.printStackTrace()
            true
        }

    /**
     * 获取对象
     *
     * @param context 上下文
     * @param key     键
     * @param <T>     指定泛型
     * @return 泛型对象
    </T> */
    fun <T : Serializable?> getObject(context: Context, key: String): T? = try {
        SharedPrefrenceUtils[context, key] as T?
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    fun setFloat(
        context: Context?, key: String?,
        value: Float
    ) {
        val settings = PreferenceManager
            .getDefaultSharedPreferences(context)
        settings.edit().putFloat(key, value).apply()
    }

    fun getFloat(
        context: Context?, key: String?,
        defaultValue: Float
    ): Float {
        val settings = PreferenceManager
            .getDefaultSharedPreferences(context)
        return settings.getFloat(key, defaultValue)
    }

    fun setLong(
        context: Context?, key: String?,
        value: Long
    ) {
        val settings = PreferenceManager
            .getDefaultSharedPreferences(context)
        settings.edit().putLong(key, value).apply()
    }

    fun getLong(
        context: Context?, key: String?,
        defaultValue: Long
    ): Long {
        val settings = PreferenceManager
            .getDefaultSharedPreferences(context)
        return settings.getLong(key, defaultValue)
    }

    fun putStringList(context: Context, key: String, list: List<String?>?) {
        try {
            put(context, key, list)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getStringList(context: Context, key: String): List<String>? {
        try {
            return SharedPrefrenceUtils[context, key] as List<String>?
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList()
    }

    /**
     * 存储List集合
     *
     * @param context 上下文
     * @param key     存储的键
     * @param list    存储的集合
     */
    fun putSerializableList(context: Context, key: String, list: List<Serializable?>?): Boolean {
        try {
            return put(context, key, list)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    /**
     * 获取List集合
     *
     * @param context 上下文
     * @param key     键
     * @param <E>     指定泛型
     * @return List集合
    </E> */
    fun <E : Serializable?> getSerializableList(context: Context, key: String): List<E>? {
        try {
            return SharedPrefrenceUtils[context, key] as List<E>?
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList()
    }

    /**
     * 存储Map集合
     *
     * @param context 上下文
     * @param key     键
     * @param map     存储的集合
     * @param <K>     指定Map的键
     * @param <V>     指定Map的值
    </V></K> */
    fun <K : Serializable?, V : Serializable?> putMap(
        context: Context,
        key: String, map: Map<K, V>?
    ) {
        try {
            put(context, key, map)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun <K : Serializable?, V : Serializable?> getMap(context: Context, key: String): Map<K, V>? {
        try {
            return SharedPrefrenceUtils[context, key] as Map<K, V>?
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 存储对象
     */
    @Throws(IOException::class)
    private fun put(context: Context, key: String, obj: Any?): Boolean {
        if (obj == null) { //判断对象是否为空
            return false
        }
        val baos = ByteArrayOutputStream()
        var oos: ObjectOutputStream? = null
        oos = ObjectOutputStream(baos)
        oos.writeObject(obj)
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        val objectStr = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
        baos.close()
        oos.close()
        return saveString(context, key, objectStr)
    }

    /**
     * 获取对象
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    private operator fun get(context: Context, key: String): Any? {
        val wordBase64 = getString(context, key)
        // 将base64格式字符串还原成byte数组
        if (TextUtils.isEmpty(wordBase64)) { //不可少，否则在下面会报java.io.StreamCorruptedException
            return null
        }
        val objBytes = Base64.decode(wordBase64!!.toByteArray(), Base64.DEFAULT)
        val bais = ByteArrayInputStream(objBytes)
        val ois = ObjectInputStream(bais)
        // 将byte数组转换成product对象
        val obj = ois.readObject()
        bais.close()
        ois.close()
        return obj
    }

    fun clear() {
        val editor = sp!!.edit()
        editor.clear()
        editor.commit()
    }
}