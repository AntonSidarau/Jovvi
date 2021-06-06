package com.jovvi.mobile.common_ui.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DiffDelegatedAdapter(
    callback: DiffUtil.ItemCallback<Any>
) : AsyncListDifferDelegationAdapter<Any>(callback) {

    constructor(
        callback: DiffUtil.ItemCallback<Any>,
        vararg delegate: AdapterDelegate<List<Any>>
    ) : this(callback) {
        addDelegates(*delegate)
    }

    fun addListListener(listener: AsyncListDiffer.ListListener<Any>) {
        differ.addListListener(listener)
    }

    fun removeListListener(listener: AsyncListDiffer.ListListener<Any>) {
        differ.removeListListener(listener)
    }

    fun addDelegate(delegate: AdapterDelegate<List<Any>>) {
        delegatesManager.addDelegate(delegate)
    }

    fun addDelegates(vararg delegates: AdapterDelegate<List<Any>>) {
        delegates.forEach { delegatesManager.addDelegate(it) }
    }
}
