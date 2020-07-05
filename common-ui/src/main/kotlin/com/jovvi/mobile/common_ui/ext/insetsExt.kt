package com.jovvi.mobile.common_ui.ext

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

fun View.updatePadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

fun View.addSystemVerticalPadding(
    targetView: View = this,
    isConsumed: Boolean = false,
    action: () -> Unit = { }
) {
    doOnApplyWindowInsets { _, insets, initialPadding, _ ->
        targetView.updatePadding(
            top = initialPadding.top + insets.systemWindowInsetTop,
            bottom = initialPadding.top + insets.systemWindowInsetBottom
        )

        action()

        if (isConsumed) {
            val newInsetsRect = Rect(
                insets.systemWindowInsetLeft, 0, insets.systemWindowInsetRight, 0
            )
            WindowInsetsCompat.Builder(insets)
                .setSystemWindowInsets(Insets.of(newInsetsRect))
                .build()
        } else {
            insets
        }
    }
}

fun View.addSystemTopPadding(
    targetView: View = this,
    isConsumed: Boolean = false
) {
    doOnApplyWindowInsets { _, insets, initialPadding, _ ->
        targetView.updatePadding(
            top = initialPadding.top + insets.systemWindowInsetTop
        )
        if (isConsumed) {
            val newInsetsRect = Rect(
                insets.systemWindowInsetLeft,
                0,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetBottom
            )
            WindowInsetsCompat.Builder(insets)
                .setSystemWindowInsets(Insets.of(newInsetsRect))
                .build()
        } else {
            insets
        }
    }
}

fun View.addSystemBottomPadding(
    targetView: View = this,
    isConsumed: Boolean = false
) {
    doOnApplyWindowInsets { _, insets, initialPadding, _ ->
        targetView.updatePadding(
            bottom = initialPadding.bottom + insets.systemWindowInsetBottom
        )
        if (isConsumed) {
            val newInsetsRect = Rect(
                insets.systemWindowInsetLeft,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                0
            )
            WindowInsetsCompat.Builder(insets)
                .setSystemWindowInsets(Insets.of(newInsetsRect))
                .build()
        } else {
            insets
        }
    }
}

fun View.addSystemBottomMargins(
    targetView: View = this,
    isConsumed: Boolean = false
) {
    doOnApplyWindowInsets { _, insets, _, initialMargin ->
        targetView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = initialMargin.bottom + insets.systemWindowInsetBottom
        }
        if (isConsumed) {
            val newInsetsRect = Rect(
                insets.systemWindowInsetLeft,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                0
            )
            WindowInsetsCompat.Builder(insets)
                .setSystemWindowInsets(Insets.of(newInsetsRect))
                .build()
        } else {
            insets
        }
    }
}

fun View.addSystemTopMargins(
    targetView: View = this,
    isConsumed: Boolean = false
) {
    doOnApplyWindowInsets { _, insets, _, initialMargin ->
        targetView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            topMargin = initialMargin.top + insets.systemWindowInsetTop
        }
        if (isConsumed) {
            val newInsetsRect = Rect(
                insets.systemWindowInsetLeft,
                0,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetBottom
            )
            WindowInsetsCompat.Builder(insets)
                .setSystemWindowInsets(Insets.of(newInsetsRect))
                .build()
        } else {
            insets
        }
    }
}

fun View.doOnApplyWindowInsets(
    block: (View, insets: WindowInsetsCompat, initialPadding: Rect, initialMargin: Rect) -> WindowInsetsCompat
) {
    val initialPadding = recordInitialPaddingForView(this)
    val initialMargins = recordInitialMarginForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding, initialMargins)
    }
    requestApplyInsetsWhenAttached()
}

private fun recordInitialPaddingForView(view: View): Rect {
    return Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)
}

private fun recordInitialMarginForView(view: View): Rect {
    val lp = view.layoutParams as? ViewGroup.MarginLayoutParams
    return Rect(
        lp?.leftMargin ?: 0,
        lp?.topMargin ?: 0,
        lp?.rightMargin ?: 0,
        lp?.bottomMargin ?: 0
    )
}

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}
