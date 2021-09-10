package com.realize.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by SongWenjun
 * 2021/5/13
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...公共的RecyclerView适配器
 */
abstract class BaseRecyclerViewAdapter<D, V : ViewBinding>(
    val context: Context,
    val dataList: MutableList<D>
) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<V>>() {
    lateinit var binding: V
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    class BaseViewHolder<V : ViewBinding>(itemView: View, var binding: V) :
        RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        binding = createLayout(viewType, parent)

        return BaseViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bindData2View(holder.binding, position)

        if (::onClickListener.isInitialized)
            holder.itemView.setOnClickListener {
                if (!onItemClick(holder, position)) {
                    onClickListener.invoke(position)
                }
            }
    }

    /**
     * 在item点击之前执行，返回true代表拦截 用于重写扩展功能
     */
    open fun onItemClick(holder: BaseViewHolder<V>, position: Int) = false

    abstract fun createLayout(viewType: Int, parent: ViewGroup): V

    abstract fun bindData2View(binding: V, position: Int)

    lateinit var onClickListener: ((Int) -> Unit?)

    fun setOnItemClickListener(listener: (Int) -> Unit?) {
        onClickListener = listener
    }
}