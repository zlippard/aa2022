package com.zaclippard.androidaccelerator2022.singleactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zaclippard.androidaccelerator2022.databinding.FragmentFlowExampleBinding
import com.zaclippard.androidaccelerator2022.viewmodels.FlowExampleViewModel
import kotlinx.coroutines.launch

class FlowExampleFragment : Fragment() {

    private lateinit var binding: FragmentFlowExampleBinding
    private val viewModel: FlowExampleViewModel by viewModels {
        FlowExampleViewModel.Factory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlowExampleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startStateFlow()

        binding.startFlowButton.setOnClickListener {
            // LiveData Approach
            viewModel.numbersLiveData.observe(viewLifecycleOwner) { newNum ->
                binding.flowText.text = newNum.toString()
            }

            // Flow Approach - Note we need repeatOnLifecycle()
            // because we want to attach on STARTED and detach on STOPPED
            // otherwise the coroutine could still run beyond the scope
            // of when we want to attach data to the view. It's typically
            // bad practice to manipulate views in the STOPPED state as the
            // Activity/Fragment is in the background at this point.
//            viewLifecycleOwner.lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.numbersFlow.collect { newNum ->
//                        binding.flowText.text = newNum.toString()
//                    }
//                }
//            }
        }

        binding.startStateFlowButton.setOnClickListener {
            // LiveData Approach
            viewModel.numbersStateLiveData.observe(viewLifecycleOwner) { newStateNum ->
                binding.stateFlowText.text = newStateNum.toString()
            }

            // Flow Approach
//            viewLifecycleOwner.lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.numbersStateFlow.collect { newStateNum ->
//                        binding.stateFlowText.text = newStateNum.toString()
//                    }
//                }
//            }
        }
    }
}
