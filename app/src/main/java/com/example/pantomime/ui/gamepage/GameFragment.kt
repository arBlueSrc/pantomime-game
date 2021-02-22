package com.example.pantomime.ui.gamepage

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pantomime.databinding.FragmentGameBinding
import com.example.pantomime.ui.MainViewModel
import com.example.pantomime.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : BaseFragment() {

    lateinit var binding: FragmentGameBinding

//    private val viewModel by viewModels<GameViewModel>()
    private val viewModel :GameViewModel by viewModel()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val TAG = "GameFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)

//        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.eventGameFinish.observe(viewLifecycleOwner,{
            if(it){
                sharedViewModel.lastScore.value = viewModel.score.value
                openNextFragment()
                viewModel.onGameFinishComplete()
            }
        })

        viewModel.eventBuzz.observe(viewLifecycleOwner,{
            if(it != BuzzType.NO_BUZZ){
                buzz(it.pattern)
            }
        })

        viewModel.message.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

    }


    private fun openNextFragment() {
        viewModel.score.value?.let {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(it))
        }
    }

    @Suppress("DEPRECATION")
    private fun buzz(pattern:LongArray){
        val buzzer : Vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            buzzer.vibrate(VibrationEffect.createWaveform(pattern,-1))
        }else{
            buzzer.vibrate(pattern,-1)
        }
    }
}