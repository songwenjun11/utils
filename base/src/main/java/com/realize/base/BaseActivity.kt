package com.realize.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.realize.common.ext.loge
import com.realize.common.utils.ActivityManager

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    lateinit var activityName: String
    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.addActivity(this)
        activityName = javaClass.simpleName
        javaClass.simpleName.loge("ActivityName")

        binding = createBinding()
        setContentView(binding.root)

        binding.root.post {
            initView()

            initData()
        }

        val method = try {
            binding.javaClass.getMethod(
                "setActivity",
                classLoader.loadClass(javaClass.canonicalName)
            )
        } catch (e: NoSuchMethodException) {
            null
        } catch (e: ClassNotFoundException) {
            null
        }
        if (method != null) {
            method.isAccessible = false
            method.invoke(binding, this)
        }
    }

    protected abstract fun createBinding(): V

    protected abstract fun initView()

    protected fun initData() {}

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
    }
}