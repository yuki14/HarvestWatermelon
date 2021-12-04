package com.example.game.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.game.R
import com.example.game.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val scoreViewModel: ScoreViewModel by navGraphViewModels(R.id.game_nav_graph) { defaultViewModelProviderFactory }
    private lateinit var rankingAdapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false).also {
            it.scoreViewModel = scoreViewModel
            it.lifecycleOwner = viewLifecycleOwner

            it.rankingRecyclerView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = RankingAdapter(viewLifecycleOwner).also { itAdapter ->
                    rankingAdapter = itAdapter
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scoreViewModel.loadScoreList()

        binding.backToTitleButton.setOnClickListener {
            findNavController().navigate(R.id.action_ResultFragment_to_TopFragment)
        }

        binding.retryButton.setOnClickListener {
            findNavController().navigate(R.id.action_ResultFragment_to_GameFragment)
        }

        scoreViewModel.scoreList.observe(viewLifecycleOwner, {
            it?.let {
                rankingAdapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}