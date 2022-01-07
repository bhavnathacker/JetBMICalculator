package com.bhavnathacker.jetbmicalculator

fun getWeightStatus(bmiValue: Double): WeightStatus {
    return when (bmiValue) {
        in 0.0..18.4 -> WeightStatus.UNDERWEIGHT
        in 18.5..24.9 -> WeightStatus.HEALTHY_WEIGHT
        in 25.0..29.9 -> WeightStatus.OVERWEIGHT
        else -> WeightStatus.OBESITY
    }
}

fun getFormattedBMI(bmiValue: Double): String {
    return "%.1f".format(bmiValue)
}


fun calculateBMI(weight: Double, height: Double): Double {
    if (weight.equals(0.0) || height.equals(0.0)) return 0.0
    return weight/(height/100 * height/100)
}