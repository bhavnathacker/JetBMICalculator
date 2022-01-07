package com.bhavnathacker.jetbmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhavnathacker.jetbmicalculator.ui.theme.JetBMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBMICalculatorTheme {
                MainContent()
            }
        }
    }
}

@Preview
@Composable
fun MainContent() {
    var showBmiResult by remember {
        mutableStateOf(false)
    }

    var bmiResult by remember {
        mutableStateOf(0.0)
    }

    Surface(modifier = Modifier.padding(12.dp)) {
        Column {
            BMICalculatorForm(onCalculateClicked = { weight, height ->
                bmiResult = calculateBMI(weight, height)
                showBmiResult = true
            }, onResetClicked = {
                bmiResult = 0.0
                showBmiResult = false
            })

            Spacer(modifier = Modifier.height(20.dp))

            BMIResultForm(showBmiResult, bmiResult)
        }
    }

}

@Composable
fun BMICalculatorForm(onCalculateClicked: (Double, Double) -> Unit, onResetClicked: () -> Unit) {
    var height by remember {
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }


    val validHeight = remember(height) {
        height.isNotEmpty()
    }

    val validWeight = remember(weight) {
        weight.isNotEmpty()
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(8.dp)),
        border = BorderStroke(1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Adult BMI Calculator",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = {
                    Text(
                        text = "Height in cms"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = {
                    Text(
                        text = "Weight in kgs"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    if (!validHeight || !validWeight) return@Button
                    onCalculateClicked(weight.toDouble(), height.toDouble())
                }) {
                    Text(text = "Calculate")
                }

                Button(onClick = {
                    weight = ""
                    height = ""
                    onResetClicked()
                }) {
                    Text(text = "Reset")
                }

            }

        }
    }

}


@Composable
fun BMIResultForm(showBmi: Boolean = true, bmiValue: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        shape = RoundedCornerShape(CornerSize(8.dp)),
        border = BorderStroke(1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showBmi) {
                val weightStatus = getWeightStatus(bmiValue)
                val bmiToDisplay = getFormattedBMI(bmiValue)
                Text(
                    text = "BMI: $bmiToDisplay",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(weightStatus.color),
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Weight Status: ${weightStatus.value}",
                    fontWeight = FontWeight.Bold,
                    color = Color(weightStatus.color),
                    style = MaterialTheme.typography.h5
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "BMI image",
                    modifier = Modifier.size(240.dp)
                )
            }

        }
    }
}
