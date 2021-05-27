package com.jovvi.mobile.feature_category.ui.delegate

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jovvi.mobile.business_category.model.CategoryModel
import com.jovvi.mobile.common_ui.view_holder.InflateViewHolder
import com.jovvi.mobile.common_ui.widget.CategoryView
import com.jovvi.mobile.feature_category.R

internal class CategoryDelegate(
    private val onItemClicked: (CategoryModel) -> Unit
) : AdapterDelegate<List<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent, onItemClicked)
    }

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is CategoryModel
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as ViewHolder
        val category = items[position] as CategoryModel

        vh.bind(category)
    }

    private class ViewHolder(
        parent: ViewGroup,
        private val onItemClicked: (CategoryModel) -> Unit
    ) : InflateViewHolder(parent, R.layout.item_category) {

        private val categoryView: CategoryView = itemView.findViewById(R.id.view_category)
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)

        private lateinit var category: CategoryModel

        init {
            categoryView.setOnClickListener { onItemClicked(category) }
        }

        fun bind(category: CategoryModel) {
            this.category = category

            categoryView.applyAppearance(category.colorStart.argb, category.colorEnd.argb, 0)
            titleTextView.text = category.title
            descriptionTextView.text = category.description
        }
    }
}
