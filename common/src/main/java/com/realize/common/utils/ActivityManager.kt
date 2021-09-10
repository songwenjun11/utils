package com.realize.common.utils

import android.app.Activity
import java.util.*

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...this is Activity Manager tools.
 */
object ActivityManager {
    //activity列表
    private val activityStack = Stack<Activity>()

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    /**
     * 根据activity的名字判断是否存在
     */
    fun isExistName(activityName: String) =
        getActivityName(activityName) != null

    fun isExist(activity: Activity) =
        activityStack.find { it == activity } != null

    /**
     * 根据名称获取实例
     */
    fun getActivityName(activityName: String): Activity? =
        activityStack.find { activity -> activity.localClassName == activityName }

    /**
     * 获取当前Activity
     */
    fun getPresentActivity(): Activity? = activityStack[0]
}