package com.example.umbrella.viewmodel

fun isWarm(degree: Float, unit: Temperature_Unit): Boolean {

    return degree >= when(unit){
        Temperature_Unit.Celeuis -> 15.55f
        Temperature_Unit.Kelvin -> 288.705556f
        else -> 60.00f
    }
}