package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val probabilityRepository = ProbabilityRepository()

        setContent {
            DecisionMakingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DecisionMakingScreen(
                        probabilityRepository.probabilities,
                        probabilityRepository::runProbability,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun DecisionMakingTheme(content: @Composable () -> Unit) {
        content()
    }

    @Composable
    fun DecisionMakingScreen(
        probabilities: List<Double>,
        runProbabilities: (Double) -> Boolean,
        modifier: Modifier = Modifier
    ) {
        var clickCount by remember { mutableIntStateOf(0) }

        var clickMessage by remember { mutableStateOf("Click Count: $clickCount") }

        var displayMessage by remember { mutableStateOf("Should we go?") }

        Row(modifier = modifier.fillMaxWidth()){
            Box{
                Text("CCID: mlander")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box{
                Text("Student ID: xxxxxxxx")
            }
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box {
                    Text(
                        displayMessage,
                        fontSize = 24.sp
                    )
                }
            }
            Row(
                modifier = modifier.fillMaxWidth().padding(all = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = {
                        val runResult = runProbabilities(probabilities[0])

                        displayMessage = if (runResult) "Yes!" else "No"

                        clickCount += 1
                        clickMessage = "Click Count: $clickCount"
                    }

                ) {
                    Text("Yes!")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        val runResult = runProbabilities(probabilities[1])

                        displayMessage = if(runResult) "Yes!" else "No"

                        clickCount += 1
                        clickMessage = "Click Count: $clickCount"
                    }

                ) {
                    Text("Maybe")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        val runResult = runProbabilities(probabilities[2])

                        displayMessage = if(runResult) "Yes!" else "No"

                        clickCount += 1
                        clickMessage = "Click Count: $clickCount"
                    }

                ) {
                    Text("Not Sure")
                }
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box{
                    Text(
                        clickMessage,
                        fontSize = 18.sp
                    )
                }
                }
            }
        }

    class ProbabilityRepository {
        private val _probabilities = mutableStateListOf(0.5, 0.25, 0.1)

        val probabilities: List<Double>
            get() = _probabilities

        fun runProbability(buttonProbability: Double): Boolean {
            val randDouble = Random.nextDouble()

            return if (randDouble <= buttonProbability) true else false
        }
    }
}