package com.jovvi.mobile.feature_category.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovvi.mobile.business_category.model.Category
import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.common_navigation.exit
import com.jovvi.mobile.common_navigation.forwardTo
import com.jovvi.mobile.common_ui.adapter.DefaultDelegatedAdapter
import com.jovvi.mobile.common_ui.ext.addSystemBottomPadding
import com.jovvi.mobile.common_ui.ext.addSystemTopMargins
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.common_ui.fragment.Injector
import com.jovvi.mobile.feature_category.R
import com.jovvi.mobile.feature_category.navigation.CategoryNavigationProvider
import com.jovvi.mobile.feature_category.ui.delegate.CategoryDelegate

class CategoryFragment : BaseFragment(R.layout.fragment_category) {

    private val adapter = DefaultDelegatedAdapter(CategoryDelegate(::onCategoryClicked))

    private lateinit var tvTitle: TextView
    private lateinit var recyclerCategory: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi(view)
        bindViewActions(view)
    }

    override fun onBackPressed() {
        Injector.navigator.exit()
    }

    private fun initUi(view: View) {
        tvTitle = view.findViewById(R.id.tv_title)
        recyclerCategory = view.findViewById(R.id.rv_category)

        tvTitle.addSystemTopMargins()
        recyclerCategory.addSystemBottomPadding()
        recyclerCategory.adapter = adapter

        adapter.setItems(
            listOf(
                Category(
                    Color(red = 0x4a, green = 0xda, blue = 0xfe),
                    Color(red = 0x7a, green = 0xe6, blue = 0xfe),
                    title = "Conversation starters",
                    description = "Kick off your conversation with these amazing questions. Don't be shy"
                ),
                Category(
                    Color(red = 0x66, green = 0xb8, blue = 0xff),
                    Color(red = 0x91, green = 0xcd, blue = 0xff),
                    title = "Dating",
                    description = "Ask something your hot date or you life partner. Flirt and make stronger connections!"
                ),
                Category(
                    Color(red = 0xbb, green = 0xa1, blue = 0xff),
                    Color(red = 0xcf, green = 0xbd, blue = 0xff),
                    title = "Conversation games",
                    description = "Play with friends and family at a party or a road trip!"
                ),
                Category(
                    Color(red = 0xfd, green = 0x7b, blue = 0x9c),
                    Color(red = 0xfd, green = 0xa1, blue = 0xba),
                    title = "Thought-ptovoking",
                    description = "Ask something your hot date or you life partner. Flirt and make stronger connections!"
                ),
                Category(
                    Color(red = 0xff, green = 0xd6, blue = 0x00),
                    Color(red = 0xff, green = 0xea, blue = 0x79),
                    title = "Thought-ptovoking",
                    description = "Ask something your hot date or you life partner. Flirt and make stronger connections!"
                ),
                Category(
                    Color(red = 0x4a, green = 0xda, blue = 0xfe),
                    Color(red = 0x7a, green = 0xe6, blue = 0xfe),
                    title = "Conversation starters",
                    description = "Kick off your conversation with these amazing questions. Don't be shy"
                ),
                Category(
                    Color(red = 0x66, green = 0xb8, blue = 0xff),
                    Color(red = 0x91, green = 0xcd, blue = 0xff),
                    title = "Dating",
                    description = "Ask something your hot date or you life partner. Flirt and make stronger connections!"
                ),
                Category(
                    Color(red = 0xbb, green = 0xa1, blue = 0xff),
                    Color(red = 0xcf, green = 0xbd, blue = 0xff),
                    title = "Conversation games",
                    description = "Play with friends and family at a party or a road trip!"
                ),
                Category(
                    Color(red = 0xfd, green = 0x7b, blue = 0x9c),
                    Color(red = 0xfd, green = 0xa1, blue = 0xba),
                    title = "Thought-ptovoking",
                    description = "Ask something your hot date or you life partner. Flirt and make stronger connections!"
                ),
                Category(
                    Color(red = 0xff, green = 0xd6, blue = 0x00),
                    Color(red = 0xff, green = 0xea, blue = 0x79),
                    title = "Thought-ptovoking",
                    description = "Ask something your hot date or you life partner. Flirt and make stronger connections!"
                )
            )
        )
    }

    private fun bindViewActions(view: View) {
        val btnHotQuestion: Button = view.findViewById(R.id.btn_hot)
        val btnFavoriteQuestion: Button = view.findViewById(R.id.btn_favorite)
        val navProvider = Injector.categoryNavigationProvider as CategoryNavigationProvider
        val ivMore: ImageView = view.findViewById(R.id.iv_more)

        btnHotQuestion.setOnClickListener {
            Injector.navigator.forwardTo(navProvider.hotQuestionScreen())
        }
        btnFavoriteQuestion.setOnClickListener {
            Injector.navigator.forwardTo(navProvider.favoriteQuestionsScreen())
        }
        ivMore.setOnClickListener {
            Injector.navigator.forwardTo(navProvider.aboutUsScreen())
        }
    }

    private fun onCategoryClicked(category: Category) {
        val navProvider = Injector.categoryNavigationProvider as CategoryNavigationProvider
        Injector.navigator.forwardTo(navProvider.topicsScreen(category))
    }
}
