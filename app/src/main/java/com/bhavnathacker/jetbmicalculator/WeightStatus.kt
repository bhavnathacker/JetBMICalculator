package com.bhavnathacker.jetbmicalculator

import android.graphics.Color

enum class WeightStatus(val value: String, val color: Int) {
    UNDERWEIGHT("Underweight", Color.RED),
    HEALTHY_WEIGHT("Healthy Weight", Color.BLUE),
    OVERWEIGHT("Overweight", Color.RED),
    OBESITY("Obesity", Color.RED)
}
