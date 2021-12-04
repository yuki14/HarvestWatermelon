package com.example.game.fragments

import android.content.Context
import androidx.lifecycle.*
import com.example.core.db.Score
import com.example.core.repository.ScoreRepository
import com.example.game.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository,
    @ApplicationContext private val context: Context
) :
    ViewModel() {
    private val mScore = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = mScore

    private val mScoreList = MutableLiveData<List<Score>>()
    val scoreList: LiveData<List<Score>>
        get() = mScoreList

    fun loadScoreList() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentScoreList = scoreRepository.getAllScores()
            var resultScoreList: List<Score>? = null

            val targetScore = score.value?.let { Score(score = it.toString()) } ?: run {
                mScoreList.postValue(currentScoreList.mapIndexed { index, score ->
                    Score(
                        index,
                        "${context.resources.getStringArray(R.array.ranking_label)[index]}：${score.score}"
                    )
                })
                return@launch
            }
            if (currentScoreList.size > 2) {
                scoreRepository.deleteScores()
                targetScore.also {
                    currentScoreList.toMutableList().apply {
                        add(it)
                        sortWith(
                            compareByDescending { it.score }
                        )
                        removeLast()
                        resultScoreList = mapIndexed { index, score ->
                            Score(
                                index,
                                "${context.resources.getStringArray(R.array.ranking_label)[index]}：${score.score}"
                            )
                        }
                        forEach { scoreRepository.saveScore(it) }
                    }
                }
            } else {
                scoreRepository.saveScore(targetScore)

                resultScoreList =
                    scoreRepository.getAllScores().mapIndexed { index, score ->
                        Score(
                            index,
                            "${context.resources.getStringArray(R.array.ranking_label)[index]}：${score.score}"
                        )
                    }
            }
            mScoreList.postValue(resultScoreList)
        }
    }

    fun setScore(score: Int) {
        mScore.postValue(score)
    }
}
