package mz.co.bilheteira.callmonitor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mz.co.bilheteira.callmonitor.data.Log
import mz.co.bilheteira.callmonitor.databinding.FragmentCallLogBinding

@AndroidEntryPoint
class CallLogFragment : Fragment() {
    private var _binding: FragmentCallLogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CallMonitorViewModel by viewModels()

    private val adp: LogAdapter by lazy {
        LogAdapter(
            onLogCLick = {
                Toast.makeText(
                    requireContext(),
                    "Making call to ${it.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

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
        setupAdapter()
        setupObservers()
        setupClickListeners()

        binding.apply {
//            buttonFirst.setOnClickListener {
//                findNavController().navigate(R.id.toSecondFragment)
//            }
        }
    }

    private fun setupData() = viewModel.setupData()

    private fun setupAdapter() {
        binding.recycler.adapter = adp
    }

    private fun setupClickListeners() = binding.apply {
//        buttonFirst.setOnClickListener {
//
//        }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                CallMonitorViewModel.CallMonitorUIState.Loading -> renderLoading()
                CallMonitorViewModel.CallMonitorUIState.Success -> renderSuccess()
                is CallMonitorViewModel.CallMonitorUIState.Content -> renderLogs(it.logs)
                is CallMonitorViewModel.CallMonitorUIState.Error -> renderError(it.message)
                else -> {}
            }
        }
    }

    private fun renderLoading() = Toast.makeText(
        requireContext(),
        "Loading, please wait...",
        Toast.LENGTH_SHORT
    ).show()

    private fun renderSuccess() {
        Toast.makeText(
            requireContext(),
            "Data successfully loaded...",
            Toast.LENGTH_SHORT
        ).show()

        fetchCachedLogs()
    }

    private fun fetchCachedLogs() = viewModel.fetchCachedLog()

    private fun renderError(message: String) = Toast.makeText(
        requireContext(),
        message,
        Toast.LENGTH_SHORT
    ).show()

    private fun renderLogs(cachedLogs: List<Log>) = binding.apply{
        recycler.isVisible = true
        adp.submitItems(cachedLogs)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}