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
import java.net.NetworkInterface

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

        val localIpAddress = getInternetProtocol()
        localIpAddress?.let { ip ->
            "IP Address: $ip".also {
                binding.apply {
                    ipAddress.text = it
                    ipAddress.isVisible = true
                }
            }
            "Port Number: 8080".also {
                binding.apply {
                    portNumber.text = it
                    portNumber.isVisible = true
                }
            }
        } ?: Toast.makeText(requireContext(), "IP not available", Toast.LENGTH_SHORT).show()
    }

    private fun setupData() = viewModel.setupData()

    private fun setupAdapter() {
        binding.recycler.adapter = adp
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

    private fun renderLogs(cachedLogs: List<Log>) = binding.apply {
        recycler.isVisible = true
        adp.submitItems(cachedLogs)
    }

    private fun getInternetProtocol(): String? {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces().iterator().asSequence()
        val localAddress = networkInterfaces.flatMap {
            it.inetAddresses.asSequence()
                .filter { inetAddress ->
                    inetAddress.isSiteLocalAddress && inetAddress.hostAddress?.let { hostAddress ->
                        !hostAddress.contains(":")
                    } ?: false && inetAddress.hostAddress != "127.0.0.1"
                }
                .map { inetAddress ->
                    inetAddress.hostAddress
                }
        }
        return localAddress.firstOrNull()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}