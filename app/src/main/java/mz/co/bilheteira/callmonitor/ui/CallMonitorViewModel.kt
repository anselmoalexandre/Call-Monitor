package mz.co.bilheteira.callmonitor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mz.co.bilheteira.callmonitor.data.Root as DomainRoot
import mz.co.bilheteira.callmonitor.data.Service as DomainService
import mz.co.bilheteira.callmonitor.data.Status as DomainStatus
import mz.co.bilheteira.callmonitor.db.entities.Log
import mz.co.bilheteira.callmonitor.db.entities.Root
import mz.co.bilheteira.callmonitor.db.entities.Service
import mz.co.bilheteira.callmonitor.db.entities.Status
import mz.co.bilheteira.callmonitor.data.Log as DomainLog
import mz.co.bilheteira.callmonitor.repository.CallMonitorRepository
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
            number = "+258845479011",
            name = "Elon Musk"
        )

        val ongoingStatus = DomainStatus(
            id = 1,
            ongoing = true,
            number = "+258845479011",
            name = "Nord Security"
        )

        _uiState.value = CallMonitorUIState.Loading
        viewModelScope.launch(Dispatchers.IO){
            callMonitorRepository.insertStatus(status.toStatusEntity())
            callMonitorRepository.insertStatus(ongoingStatus.toStatusEntity())
        }


    }

    fun DomainLog.toLogEntity(): Log = Log(
        id = this.id,
        beginning = this.beginning,
        duration = this.duration,
        number = this.number,
        name = this.name,
        timesQueried = this.timesQueried
    )

    fun DomainStatus.toStatusEntity(): Status = Status(
        id = this.id,
        ongoing = this.ongoing,
        number = this.number,
        name = this.name
    )

    fun DomainService.toServiceEntity(): Service = Service(
        id = this.id,
        name = this.name,
        uri = this.uri
    )

    fun DomainRoot.toRootEntity(): Root = Root(
        start = this.start,
        id = this.services.first().id
    )

    sealed class CallMonitorUIState {
        object Loading : CallMonitorUIState()
        object Success : CallMonitorUIState()
        data class Error(val message: String) : CallMonitorUIState()
    }
}