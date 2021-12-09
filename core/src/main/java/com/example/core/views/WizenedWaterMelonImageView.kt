package com.example.core.views

import android.content.Context
import android.util.AttributeSet
import coil.load
import com.example.core.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext

@AndroidEntryPoint
class WizenedWaterMelonImageView @AssistedInject constructor(@ActivityContext context: Context, @Assisted attrs: AttributeSet?) : BaseWaterMelon(
    context,attrs){
    override val point = -50

    init {
        load(R.drawable.wizenedwatermelon)
    }

    @AssistedFactory
    interface Factory {
        fun create(attrs: AttributeSet? = null): WizenedWaterMelonImageView
    }
}