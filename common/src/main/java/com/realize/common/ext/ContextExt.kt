package com.realize.common.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */

inline fun <reified A : Activity> Context.openActivity() {
    val intent = Intent(this, A::class.java)
    if (!(this is Activity))
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    openActivity { intent }
}

inline fun Context.openActivity(intent: () -> Intent) {
    startActivity(intent.invoke())
}

inline fun <reified A : Activity> Context.openActivity(bunild: (Intent) -> Unit) {
    val intent = Intent(this, A::class.java)
    if (!(this is Activity))
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    bunild.invoke(intent)
    openActivity { intent }
}

/**
 * 申请权限扩展
 */
@SuppressLint("CheckResult")
fun Context.requestPermissions(vararg permissions: String, callback: (granted: Boolean) -> Unit) {
    if (this is FragmentActivity && permissions.isNotEmpty()) {
        RxPermissions(this).request(*permissions)
            .subscribe { granted ->
                callback.invoke(granted)
            }
    }
}
