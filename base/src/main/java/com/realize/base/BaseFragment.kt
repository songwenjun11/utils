package com.realize.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by SongWenjun
 * 2021/9/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
abstract class BaseFragment<V : ViewBinding> : Fragment() {
    lateinit var binding: V
    lateinit var fragmentName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createView(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        fragmentName = javaClass.simpleName

        val method = try {
            binding.javaClass.getMethod(
                "setFragment",
                javaClass.classLoader.loadClass(javaClass.canonicalName)
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

        binding.root.post {
            initData()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden && isLazyLoad()) {
            loadData(hidden)
        }
    }

    abstract fun createView(inflater: LayoutInflater): V

    abstract fun initView()

    open fun initData() {}

    open fun loadData(isVisibility: Boolean) {}

    open fun isLazyLoad(): Boolean {
        return false
    }
}