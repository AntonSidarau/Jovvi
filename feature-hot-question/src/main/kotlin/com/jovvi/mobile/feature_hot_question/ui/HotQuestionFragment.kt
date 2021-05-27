package com.jovvi.mobile.feature_hot_question.ui

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.Orientation
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.jovvi.mobile.common_di.Scopes
import com.jovvi.mobile.common_mpp.Color
import com.jovvi.mobile.common_navigation.Router
import com.jovvi.mobile.common_ui.ext.*
import com.jovvi.mobile.common_ui.fragment.BaseFragment
import com.jovvi.mobile.feature_hot_question.R
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

class HotQuestionFragment : BaseFragment(R.layout.fragment_host_question), AndroidScopeComponent {

    companion object {

        fun newInstance(): Fragment {
            return HotQuestionFragment()
        }
    }

    private val router: Router by inject()

    private lateinit var ivCopy: ImageView

    override val scope: Scope by lazyMainThread {
        getKoin().getScope(Scopes.ACTIVITY)
        /*createCustomScope(HotQuestionScopes.FEATURE).also {
            it.linkTo(it.getScope(Scopes.ACTIVITY))
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi(view)
        bindViewActions(view)
    }

    override fun onBackPressed() {
        router.exit()
    }

    private fun initUi(view: View) {
        val containerQuestion: ViewGroup = view.findViewById(R.id.container_question)
        val tvQuestion: TextView = view.findViewById(R.id.tv_question)
        ivCopy = view.findViewById(R.id.iv_copy)

        val colorStart = Color(red = 0x4a, green = 0xda, blue = 0xfe)
        val colorEnd = Color(red = 0x7a, green = 0xe6, blue = 0xfe)
        val outerCornerRadius = view.context.resources.getDimension(R.dimen.corner_large_size)
        val innerCornerRadius = outerCornerRadius - view.dpToPx(3F)
        val padding = view.dpToPx(4F).toInt()

        val strokeDrawable = GradientDrawable(Orientation.BL_TR, intArrayOf()).apply {
            cornerRadius = outerCornerRadius
            shape = GradientDrawable.RECTANGLE
            colors = intArrayOf(colorStart.argb, colorEnd.argb)
        }

        val fillDrawable = GradientDrawable().apply {
            cornerRadius = innerCornerRadius
            shape = GradientDrawable.RECTANGLE
            color = ColorStateList.valueOf(view.context.getColorInt(R.color.white))
        }

        containerQuestion.background = LayerDrawable(
            arrayOf(strokeDrawable, InsetDrawable(fillDrawable, padding))
        )
        tvQuestion.text = "Somebody has told me that world is gonna roll me"
        ivCopy.imageTintList = ColorStateList.valueOf(colorEnd.argb)
    }

    private fun bindViewActions(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val btnShareTwitter: Button = view.findViewById(R.id.btn_share_twitter)
        val btnShareInstagram: Button = view.findViewById(R.id.btn_share_instagram)

        toolbar.addSystemTopMargins()
        btnShareTwitter.addSystemBottomMargins()

        ivCopy.setOnClickListener {
            Toast.makeText(requireContext(), "Copied(no)", Toast.LENGTH_SHORT).show()
        }
        toolbar.setNavigationOnClickListener { router.exit() }
        btnShareTwitter.setOnClickListener {
            Toast.makeText(requireContext(), "Share to twitter", Toast.LENGTH_SHORT).show()
        }
        btnShareInstagram.setOnClickListener {
            Toast.makeText(requireContext(), "Share to instagram", Toast.LENGTH_SHORT).show()
        }
    }
}

