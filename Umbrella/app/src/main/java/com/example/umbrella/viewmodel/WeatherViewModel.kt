package com.example.umbrella.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umbrella.R
import com.example.umbrella.network.WeatherApi
import com.example.umbrella.network.WeatherDaily
import com.example.umbrella.network.WeatherResponse
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val ZIP_CODE = "ZIP_CODE"
const val TEMPERATURE = "TEMPERATURE_MODE"

enum class Temperature_Unit(val unit: String) {
    Fahrenheit("imperial"),
    Celeuis("metric"),
    Kelvin("Standard")

}

class WeatherViewModel : ViewModel() {


    private val _zip = MutableLiveData<String>("94040,us")
    val zip : LiveData<String> get() = _zip

    private val _unit = MutableLiveData<Temperature_Unit>(Temperature_Unit.Fahrenheit)
    val unit get() = _unit

    private val _temperature = MutableLiveData<Float>()
    val temperature : LiveData<Float> get() = _temperature

    private val _description = MutableLiveData<String>()
    val description : LiveData<String> get() = _description

    private val _weatherResponse = MutableLiveData<List<WeatherDaily>>()
    val weatherResponse: LiveData<List<WeatherDaily>> get() = _weatherResponse


    init {
        _weatherResponse.value = emptyList()
    }


    fun getWeather() {
        viewModelScope.launch {

            val weatherR = WeatherApi.retrofitService.getWeathers(zip.value!!, units = Temperature_Unit.Fahrenheit.unit)

            _temperature.value = weatherR.list[0].main.temp
            _description.value = weatherR.list[0].weather[0].main

            val mList = mutableListOf<WeatherDaily>()
            weatherR.list.forEach{ weatherItem ->
                val (date_s, time_s) = weatherItem.dt_txt.split(" ")

                val newItem = WeatherDaily(date_s, time_s, weatherItem.main.temp)

                mList.add(newItem)
            }
            _weatherResponse.value = mList
        }
    }

}