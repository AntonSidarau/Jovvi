package com.jovvi.mobile.feature_topics.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jovvi.mobile.business_category.model.Category
import com.jovvi.mobile.business_topics.model.Topic
import com.jovvi.mobile.common_navigation.exit
import com.jovvi.mobile.common_ui.adapter.DefaultDelegatedAdapter
import com.jovvi.mobile.common_ui.ext.addSystemBottomPadding
import com.jovvi.mobile.common_ui.ext.getSerializableAs
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.common_ui.fragment.Injector
import com.jovvi.mobile.feature_topics.R
import com.jovvi.mobile.feature_topics.ui.delegate.TopicsDelegate

class TopicsFragment : BaseFragment(R.layout.fragment_topics) {

    companion object {

        private const val ARG_CATEGORY = "arg_category"

        fun newInstance(category: Category): TopicsFragment {
            return TopicsFragment().apply {
                arguments = bundleOf(ARG_CATEGORY to category)
            }
        }
    }

    private val adapter = DefaultDelegatedAdapter(TopicsDelegate())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi(view)
    }

    override fun onBackPressed() {
        Injector.navigator.exit()
    }

    private fun initUi(view: View) {
        val recyclerTopics: RecyclerView = view.findViewById(R.id.rv_topics)
        recyclerTopics.addSystemBottomPadding()

        recyclerTopics.adapter = adapter
        recyclerTopics.layoutManager = GridLayoutManager(requireContext(), 2)

        val category: Category = requireArguments().getSerializableAs(ARG_CATEGORY)

        adapter.setItems(
            listOf(
                Topic("Animals", category.colorStart, category.colorEnd),
                Topic("Food", category.colorStart, category.colorEnd),
                Topic("Work", category.colorStart, category.colorEnd),
                Topic("Magazines", category.colorStart, category.colorEnd),
                Topic("Games", category.colorStart, category.colorEnd),
                Topic("Videos", category.colorStart, category.colorEnd),
                Topic("Plants", category.colorStart, category.colorEnd),
                Topic("Friends", category.colorStart, category.colorEnd),
                Topic("Technology", category.colorStart, category.colorEnd),
                Topic("250 questions", category.colorStart, category.colorEnd),
                Topic("Cars", category.colorStart, category.colorEnd),
                Topic("Politics", category.colorStart, category.colorEnd),
                Topic("Music", category.colorStart, category.colorEnd),
                Topic("Art", category.colorStart, category.colorEnd),
                Topic("Sculpture", category.colorStart, category.colorEnd)
            )
        )
    }
}
