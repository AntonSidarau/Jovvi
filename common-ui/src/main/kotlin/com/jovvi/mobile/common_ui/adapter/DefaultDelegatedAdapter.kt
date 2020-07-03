package com.jovvi.mobile.common_ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

open class DefaultDelegatedAdapter(
    vararg delegates: AdapterDelegate<List<Any>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Any>()
    private val delegatesManager = AdapterDelegatesManager<List<Any>>()

    init {
        addDelegates(*delegates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder, null)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        delegatesManager.onBindViewHolder(items, position, holder, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return delegatesManager.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount() = items.size

    fun addDelegate(delegate: AdapterDelegate<List<Any>>) {
        delegatesManager.addDelegate(delegate)
    }

    fun addDelegates(vararg delegates: AdapterDelegate<List<Any>>) {
        delegates.forEach { delegatesManager.addDelegate(it) }
    }

    fun setItems(newItems: List<Any>) {
        if (items == newItems) {
            return
        }

        items.clear()
        items.addAll(newItems)

        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Any>) {
        items.addAll(newItems)

        notifyItemRangeChanged(items.size, newItems.size)
    }
}
