package com.varun.egg_timer.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varun.egg_timer.data.EggTypeRepository
import com.varun.egg_timer.models.EggType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EggTimerViewmodel: ViewModel() {

    private val repository = EggTypeRepository()

    private val _eggTypes = MutableStateFlow<List<EggType>>(emptyList())
    val eggTypes: StateFlow<List<EggType>> = _eggTypes

    private val _timeRemaining = MutableStateFlow(0);
    val timeRemaining: StateFlow<Int> = _timeRemaining

    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning

    //alarm
    private val _playAlarm = MutableSharedFlow<Unit>()
    val playAlarm = _playAlarm.asSharedFlow()

    private val _isAlarmRinging = MutableStateFlow(false)
    val isAlarmRinging: StateFlow<Boolean> = _isAlarmRinging

    private var timerJob: Job? = null

    //pick up all the data on egg types from the repository

    init {
        _eggTypes.value = repository.getEggTypes()
    }

    fun startTimer(minutes: Int){
        timerJob?.cancel()
        _isTimerRunning.value = true
        _isAlarmRinging.value = false
        _timeRemaining.value = minutes * 60

        timerJob = viewModelScope.launch {

            while(_timeRemaining.value > 0){
                delay(1000)
                _timeRemaining.value -= 1
            }
            _isTimerRunning.value = false
            _isAlarmRinging.value = true
            _playAlarm.emit(Unit)
        }
    }

    fun stopTimer(){
        _isTimerRunning.value = false
        _timeRemaining.value = 0
        _isAlarmRinging.value = false
    }
}