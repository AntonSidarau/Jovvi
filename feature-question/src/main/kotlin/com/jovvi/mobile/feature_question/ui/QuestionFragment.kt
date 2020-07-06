package com.jovvi.mobile.feature_question.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.business_topics.model.Topic
import com.jovvi.mobile.common_navigation.exit
import com.jovvi.mobile.common_ui.ext.addSystemTopMargins
import com.jovvi.mobile.common_ui.ext.getSerializableAs
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.common_ui.fragment.Injector
import com.jovvi.mobile.feature_question.R

class QuestionFragment : BaseFragment(R.layout.fragment_question) {

    companion object {

        private const val ARG_TOPIC = "arg_topic"

        fun newInstance(topic: Topic): Fragment {
            return QuestionFragment().apply {
                arguments = bundleOf(ARG_TOPIC to topic)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi(view)
    }

    override fun onBackPressed() {
        Injector.navigator.exit()
    }

    private fun initUi(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)

        val topic: Topic = requireArguments().getSerializableAs(ARG_TOPIC)

        toolbar.addSystemTopMargins()
        toolbar.setNavigationOnClickListener { Injector.navigator.exit() }
        toolbar.title = topic.name
    }
}
