package mz.co.bilheteira.callmonitor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mz.co.bilheteira.callmonitor.databinding.FragmentCallLogBinding

@AndroidEntryPoint
class CallLogFragment : Fragment() {
    private var _binding: FragmentCallLogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CallMonitorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
        setupObservers()
        setupClickListeners()

        binding.apply {
//            buttonFirst.setOnClickListener {
//                findNavController().navigate(R.id.toSecondFragment)
//            }
        }
    }

    private fun setupData() = viewModel.setupData()

    private fun setupClickListeners() = binding.apply {
//        buttonFirst.setOnClickListener {
//
//        }
    }

    private fun setupObservers() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}