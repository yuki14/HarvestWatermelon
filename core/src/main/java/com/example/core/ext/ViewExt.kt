package com.example.core

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleCoroutineScope
import coil.clear
import com.example.core.views.BaseWaterMelon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

fun BaseWaterMelon.animateTranslationAboveWithFadeOut(
    rootView: ConstraintLayout,
    scope: LifecycleCoroutineScope,
    collisionAction: (() -> Unit?)? = null
) {
    val translationY = ObjectAnimator.ofFloat(this, "translationY", -100f)
    val alpha = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)

    val animatorSet = AnimatorSet()
    animatorSet.interpolator = LinearInterpolator()
    animatorSet.playTogether(translationY, alpha)
    animatorSet.duration = 2000
    animatorSet.addListener(object : Animator.AnimatorListener {
        lateinit var animator: Animator
        override fun onAnimationEnd(p0: Animator?) {
            p0?.let { animator = it }
            this@animateTranslationAboveWithFadeOut.visibility = View.GONE
            this@animateTranslationAboveWithFadeOut.clear()
            this@animateTranslationAboveWithFadeOut.clearAnimation()
            animatorSet.removeAllListeners()
            rootView.removeView(this@animateTranslationAboveWithFadeOut)
        }

        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {
            p0?.let { animator = it }
            scope.launch(Dispatchers.IO) {
                // 当たり判定
                collisionAction?.invoke()
            }
        }
    })
    animatorSet.start()
}

@BindingAdapter(
    "android:visibilityAfterSetCollectionData",
    "android:emptyVisibility",
    "android:notEmptyVisibility"
)
fun View.visibilityAfterSetCollectionData(
    targetData: Collection<*>?,
    emptyVisibility: Int,
    notEmptyVisibility: Int
) {
    if (targetData != null) {
        visibility = if (targetData.isNotEmpty()) {
            notEmptyVisibility
        } else {
            emptyVisibility
        }
    }
}