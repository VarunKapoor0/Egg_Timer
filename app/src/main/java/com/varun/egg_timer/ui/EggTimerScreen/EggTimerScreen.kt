package com.varun.egg_timer.ui.EggTimerScreen

import EggTypeCard
import android.media.RingtoneManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.snapping.SnapPosition.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Egg
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.varun.egg_timer.viewmodels.EggTimerViewmodel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember

@Composable
fun EggTimerScreen(viewModel: EggTimerViewmodel = viewModel()) {
    val context = LocalContext.current

    val eggTypes by viewModel.eggTypes.collectAsState()
    val timeRemaining by viewModel.timeRemaining.collectAsState()
    val isRunning by viewModel.isTimerRunning.collectAsState()
    val isAlarmRinging by viewModel.isAlarmRinging.collectAsState()

    //RingtoneManager for alarm sound
    val alarmTone = remember {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        RingtoneManager.getRingtone(context, uri).apply {
            streamType = android.media.AudioManager.STREAM_ALARM
        }
    }

    LaunchedEffect(Unit) {
        viewModel.playAlarm.collect {
            alarmTone?.play()
        }
    }

    Column(Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Icon(
            imageVector = Icons.Outlined.Egg,
            contentDescription = "Egg Icon",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(96.dp)
        )
    }


    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(64.dp))



        Spacer(Modifier.height(32.dp))

        if(isRunning){
            Text("Time Remaining: ${timeRemaining / 60}:${(timeRemaining % 60).toString().padStart(2, '0')}",
                style = MaterialTheme.typography.headlineMedium)
        } else{
            Text("Choose your egg", style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(Modifier.height(16.dp))
        eggTypes.forEach{
            EggTypeCard(it) {selectedEgg ->
                viewModel.startTimer(selectedEgg.minutes)
            }
        }

        if(isRunning){
            AnimatedVisibility(visible = isRunning) {
                Button(onClick = { viewModel.stopTimer() }) {
                    Text("Stop Timer")
                }
            }
            BoilingEggAnimation(
                modifier = Modifier.size(200.dp)
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if(isAlarmRinging){
            Button(
                onClick = {
                    alarmTone.stop()
                    viewModel.stopTimer()
                },
                modifier = Modifier.padding(top=16.dp)
            ){
                Text("Stop Alarm")
            }
        }
    }
}