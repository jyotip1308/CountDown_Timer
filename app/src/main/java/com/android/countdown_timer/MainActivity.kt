package com.android.countdown_timer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.countdown_timer.ui.theme.Countdown_TimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Countdown_TimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CountdownTimer()
                }
            }
        }
    }
}

@Composable
fun CountdownTimer() {
    var timerRunning by remember { mutableStateOf(false) }
    var currentTime by remember { mutableStateOf(10L) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Countdown: $currentTime seconds", style = TextStyle(fontSize = 30.sp))

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (!timerRunning) {
                        // Start the countdown timer
                        object : CountDownTimer(currentTime * 1000, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                currentTime = millisUntilFinished / 1000
                            }

                            override fun onFinish() {
                                timerRunning = false
                            }
                        }.start()

                        timerRunning = true
                    }
                },
                enabled = !timerRunning
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                Text(text = "Start")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (timerRunning) {
                        // Stop the countdown timer
                        timerRunning = false
                    }
                },
                enabled = timerRunning
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                Text(text = "Stop")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CountdownTimerPreview() {
//    ServiceTheme {
//        CountdownTimer()
//    }
//}