package com.example.pantomime.ui.scorepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pantomime.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    lateinit var binding : FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = ScoreFragmentArgs.fromBundle(requireArguments()).score
        binding.txtFinalScore.text = score.toString()

        binding.button.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}