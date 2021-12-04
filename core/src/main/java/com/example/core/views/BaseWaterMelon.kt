package com.example.core.views

import android.content.Context
import android.util.AttributeSet

abstract class BaseWaterMelon constructor(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(
    context,attrs){
    open val point: Int = 0
}