package mz.co.bilheteira.callmonitor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mz.co.bilheteira.callmonitor.data.Service as DomainService
import mz.co.bilheteira.callmonitor.data.Status as DomainStatus
import mz.co.bilheteira.callmonitor.db.entities.Log
import mz.co.bilheteira.callmonitor.db.entities.Service
import mz.co.bilheteira.callmonitor.db.entities.Status
import mz.co.bilheteira.callmonitor.data.Log as DomainLog
import mz.co.bilheteira.callmonitor.repository.CallMonitorRepository
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CallMonitorViewModel @Inject constructor(
    private val callMonitorRepository: CallMonitorRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<CallMonitorUIState>()
    val uiState: LiveData<CallMonitorUIState> = _uiState

    fun setupData() {
        val status = DomainStatus(
            id = 1,
            ongoing = false,
            number = "+120255550203",
            name = "Elon Musk"
        )

        val ongoingStatus = DomainStatus(
            id = 2,
            ongoing = true,
            number = "+120255550203",
            name = "Nord Security"
        )

        val firstLog = DomainLog(
            id = 1,
            beginning = Calendar.getInstance().time.toString(),
            duration = "4998",
            number = "+120255550203",
            name = "Elon Musk",
            timesQueried = 5
        )

        val secondLog = DomainLog(
            id = 2,
            beginning = Calendar.getInstance().time.toString(),
            duration = "338",
            number = "+120255550203",
            name = "Mark Zuckerberg",
            timesQueried = 1
        )

        val logService = DomainService(
            id = "1",
            name = "log",
            uri = "http://localhost:8080/log"
        )

        val statusService = DomainService(
            id = "2",
            name = "status",
            uri = "http://localhost:8080/status"
        )

        _uiState.value = CallMonitorUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            // Delaying a bit just for UI/UX perspective
            delay(500L)

            callMonitorRepository.insertStatus(status.toStatusEntity())
            callMonitorRepository.insertStatus(ongoingStatus.toStatusEntity())

            callMonitorRepository.insertLog(firstLog.toLogEntity())
            callMonitorRepository.insertLog(secondLog.toLogEntity())

            callMonitorRepository.insertService(logService.toServiceEntity())
            callMonitorRepository.insertService(statusService.toServiceEntity())

            _uiState.postValue(CallMonitorUIState.Success)
        }
    }

    fun fetchCachedLog() {
        _uiState.value = CallMonitorUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val cachedLog = callMonitorRepository.getLog()
            if (cachedLog.isNotEmpty()) {
                val domainLog = cachedLog.map {
                    DomainLog(
                        id = it.id,
                        beginning = it.beginning,
                        duration = it.duration,
                        number = it.number,
                        name = it.name,
                        timesQueried = it.timesQueried
                    )
                }

                _uiState.postValue(CallMonitorUIState.Content(logs = domainLog))
            } else _uiState.postValue(CallMonitorUIState.Error("No logs found"))
        }
    }

    private fun DomainLog.toLogEntity(): Log = Log(
        id = this.id,
        beginning = this.beginning,
        duration = this.duration,
        number = this.number,
        name = this.name,
        timesQueried = this.timesQueried
    )

    private fun DomainStatus.toStatusEntity(): Status = Status(
        id = this.id,
        ongoing = this.ongoing,
        number = this.number,
        name = this.name
    )

    private fun DomainService.toServiceEntity(): Service = Service(
        id = this.id,
        name = this.name,
        uri = this.uri
    )

    sealed class CallMonitorUIState {
        object Loading : CallMonitorUIState()
        object Success : CallMonitorUIState()
        data class Content(val logs: List<DomainLog>) : CallMonitorUIState()
        data class Error(val message: String) : CallMonitorUIState()
    }
}