package com.example.game.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import coil.clear
import com.example.core.animateTranslationAboveWithFadeOut
import com.example.core.views.OtsukisamaImageView
import com.example.core.views.WaterMelonImageView
import com.example.core.views.WizenedWaterMelonImageView
import com.example.game.R
import com.example.game.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var soilList: ArrayList<ImageView>
    private val gameViewModel: GameViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by navGraphViewModels(R.id.game_nav_graph) { defaultViewModelProviderFactory }
    @Inject
    lateinit var waterMelonFactory: WaterMelonImageView.Factory
    @Inject
    lateinit var otsukisamaFactory: OtsukisamaImageView.Factory
    @Inject
    lateinit var wizenedWaterMelonFactory: WizenedWaterMelonImageView.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false).also {
            it.gameViewModel = gameViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            soilList = arrayListOf(
                soilImageView1,
                soilImageView2,
                soilImageView3,
                soilImageView4,
                soilImageView5,
                soilImageView6,
                soilImageView7,
                soilImageView8,
                soilImageView9
            )
        }

        var leftSideXCoOrdinate = 0f
        var topSideYCoOrdinate = 0f

        binding.playerImageView.setOnTouchListener { playerImageView, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    leftSideXCoOrdinate = playerImageView.x - event.rawX
                    topSideYCoOrdinate = playerImageView.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    playerImageView.animate().apply {
                        x(event.rawX.toInt() + leftSideXCoOrdinate)
                        y(event.rawY.toInt() + topSideYCoOrdinate)
                        duration = 0
                        start()
                    }
                }
                MotionEvent.ACTION_UP -> {
                }
                else -> {
                }
            }
            true
        }

        gameViewModel.createRandomValueForWaterMelonImage()

        gameViewModel.countDownTimer.observe(viewLifecycleOwner, {
            it?.let {
                gameViewModel.countDownTime()
            }
        })

        gameViewModel.randomValueForWaterMelonView.observe(viewLifecycleOwner, {
            it?.let {
                binding.apply {
                    ConstraintSet().apply {
                        val waterMelonImageView =
                            when (gameViewModel?.randomValueForWaterMelonType?.value) {
                                in 0..4 -> waterMelonFactory.create()
                                in 5..6 -> otsukisamaFactory.create()
                                else -> wizenedWaterMelonFactory.create()
                            }
                        waterMelonImageView.id = generateViewId()

                        rootView.addView(waterMelonImageView)
                        runCatching { clone(rootView) }
                        //left to left of
                        connect(
                            waterMelonImageView.id,
                            ConstraintSet.LEFT,
                            soilList[it].id,
                            ConstraintSet.LEFT

                        )
                        //right to right of
                        connect(
                            waterMelonImageView.id,
                            ConstraintSet.RIGHT,
                            soilList[it].id,
                            ConstraintSet.RIGHT
                        )
                        //bottom
                        connect(
                            waterMelonImageView.id,
                            ConstraintSet.BOTTOM,
                            soilList[it].id,
                            ConstraintSet.BOTTOM
                        )
                        applyTo(rootView)
                        waterMelonImageView.updateLayoutParams {
                            width = 200
                            height = 200
                        }
                        waterMelonImageView.animateTranslationAboveWithFadeOut(
                            rootView,
                            lifecycleScope
                        ) {
                            gameViewModel?.viewsIntersect(
                                playerImageView,
                                waterMelonImageView
                            )
                        }
                    }
                }
            }
        })

        gameViewModel.targetImageView.observe(viewLifecycleOwner, {
            it?.let {
                binding.apply {
                    it.clear()
                    it.clearAnimation()
                    it.visibility = View.GONE
                    rootView.removeView(it)
                }
            }
        })

        gameViewModel.countDownTimer.observe(viewLifecycleOwner, {
            it?.let {
                if (it == 0L) {
                    scoreViewModel.setScore(gameViewModel.score.value ?: 0)
                    findNavController().navigate(R.id.action_GameFragment_to_ResultFragment)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}