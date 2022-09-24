package mz.co.bilheteira.callmonitor

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mz.co.bilheteira.callmonitor.data.Log
import mz.co.bilheteira.callmonitor.data.Root
import mz.co.bilheteira.callmonitor.data.Status
import mz.co.bilheteira.callmonitor.databinding.ActivityMainBinding
import mz.co.bilheteira.callmonitor.ui.CallMonitorViewModel
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var root: Root
    private val log = mutableListOf<Log>()
    private val status = mutableListOf<Status>()

    private val viewModel: CallMonitorViewModel by viewModels()

    private val server by lazy {
        embeddedServer(Netty, port = 12435, configure = {
            connectionGroupSize = 3
            workerGroupSize = 3
            callGroupSize = 5
        }) {
            install(CallLogging)

            routing {
                route("/") {
                    get {
                        call.respondText(
                            Gson().toJson(root).toString(),
                            ContentType.Application.Json
                        )
                    }
                }

                route("/status") {
                    get {
                        call.respondText(
                            Gson().toJson(status).toString(),
                            ContentType.Application.Json
                        )
                    }
                }

                route("/log") {
                    get {
                        call.respondText(
                            Gson().toJson(log).toString(),
                            ContentType.Application.Json
                        )
                    }
                }
            }
        }
    }

    private var isServerRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupClickListener()
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this) {
            when (it) {
                is CallMonitorViewModel.CallMonitorUIState.LogContent -> exposeLog(it.log)
                is CallMonitorViewModel.CallMonitorUIState.RootContent -> exposeRoot(it.root)
                is CallMonitorViewModel.CallMonitorUIState.StatusContent -> exposeStatus(it.status)
                else -> {}
            }
        }
    }

    private fun setupClickListener() = binding.apply {
        fab.setOnClickListener {
            if (!isServerRunning) {
                startServer()
            } else stopServer()
        }
    }

    private fun exposeLog(cachedLog: List<Log>) {
        log.clear()
        log.addAll(cachedLog)
    }

    private fun exposeRoot(cachedRoot: Root) {
        root = cachedRoot
    }

    private fun exposeStatus(cachedStatus: List<Status>) {
        status.clear()
        status.addAll(cachedStatus)
    }

    private fun startServer() = CoroutineScope(Dispatchers.IO).launch {
        viewModel.fetchRoot()
        viewModel.fetchLog()
        viewModel.fetchStatus()
        server.start(wait = true)
    }

    private fun stopServer() = CoroutineScope(Dispatchers.IO).launch {
        server.stop(gracePeriod = 100L, timeout = 2000L, TimeUnit.MILLISECONDS)
    }

    override fun onDestroy() {
        stopServer()
        super.onDestroy()
    }
}