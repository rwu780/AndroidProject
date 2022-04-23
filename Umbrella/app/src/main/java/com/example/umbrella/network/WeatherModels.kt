package com.example.umbrella.network

import java.math.BigInteger
import java.util.*

data class WeatherResponse(
    val list: List<WeatherItem>
)

data class WeatherItem(
    val dt_txt: String,
    val main: WeatherMain,
    val weather: List<WeatherDescription>
)

class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String
)

class WeatherMain(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float
)

data class WeatherDaily(
    val date: String,
    val time: String,
    val temperature: Float,
)


