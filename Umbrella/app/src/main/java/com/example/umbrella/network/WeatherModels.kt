package com.example.umbrella.network

import java.math.BigInteger
import java.util.*

data class WeatherResponse(
    val list: List<WeatherItem>,
    val city: City
)

data class City (
    val name: String,
    val country: String
)

data class WeatherItem(
    val dt_txt: String,
    val main: WeatherMain,
    val weather: List<WeatherDescription>
)

class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

class WeatherMain(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float
)

//data class WeatherDaily123(
//    val date: String,
//    val hourly: List<WeatherHourly>
//) {
//    fun findHottestAndColdest() {
//        val coldest = hourly.map {
//            it.temperature
//        }.maxOrNull()
//
//        val hottest = hourly.map { it.temperature }.maxOrNull()
//
//        for (i in hourly){
//            if (i.temperature == coldest){
//                i.isColdest = true
//            }
//            if (i.temperature == hottest){
//                i.isWarmest = true
//            }
//        }
//
//    }
//
//}

data class WeatherDaily(
    val date: String,
    val time: String,
    val temperature: Float,
    val iconUrl: String,
    var isColdest: Boolean = false,
    var isWarmest: Boolean = false
)


