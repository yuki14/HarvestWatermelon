package com.example.game.fragments

import android.graphics.Rect
import android.graphics.Rect.intersects
import android.widget.ImageView
import androidx.lifecycle.*
import com.example.core.views.BaseWaterMelon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val mCountDownTimer = MutableLiveData<Long>().also { it.value = 60 }
    val countDownTimer: LiveData<Long>
        get() = mCountDownTimer

    private val mTargetImageView = MutableLiveData<ImageView>()
    val targetImageView: LiveData<ImageView>
        get() = mTargetImageView

    private val mRandomValueForWaterMelonView = MutableLiveData<Int>()
    val randomValueForWaterMelonView: LiveData<Int>
        get() = mRandomValueForWaterMelonView

    private val mRandomValueForWaterMelonType = MutableLiveData<Int>()
    val randomValueForWaterMelonType: LiveData<Int>
        get() = mRandomValueForWaterMelonType

    private val mScore = MutableLiveData<Int>().also { it.value = 0 }
    val score: LiveData<Int>
        get() = mScore

    fun countDownTime() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            mCountDownTimer.let {
                it.postValue(it.value?.minus(1))
            }
        }
    }

    fun viewsIntersect(view1: ImageView, view2: BaseWaterMelon) {
        viewModelScope.launch(Dispatchers.IO) {
            repeat(20) {
                val view1Rect = Rect()
                view1.getHitRect(view1Rect)
                val view2Rect = Rect()
                view2.getHitRect(view2Rect)
                if (intersects(view1Rect, view2Rect)) {
                    setPoint(view2)
                    mTargetImageView.postValue(view2)
                    return@launch
                }
                delay(100)
            }
        }
    }

    private fun setPoint(view2: BaseWaterMelon) {
        mScore.also {
            it.postValue(it.value?.plus(view2.point))
        }
    }

    fun createRandomValueForWaterMelonImage() {
        viewModelScope.launch {
            while (countDownTimer.value != 0L) {
                delay(2000)
                val secureRandom = SecureRandom()
                mRandomValueForWaterMelonType.postValue(secureRandom.nextInt(8))
                mRandomValueForWaterMelonView.postValue(secureRandom.nextInt(8))
            }
        }
    }
}
