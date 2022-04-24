package com.example.umbrella.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umbrella.network.WeatherApi
import com.example.umbrella.network.WeatherDaily

import kotlinx.coroutines.launch

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val ZIP_CODE = "ZIP_CODE"
const val TEMPERATURE = "TEMPERATURE_MODE"

enum class Temperature_Unit(val unit: String) {
    Fahrenheit("imperial"),
    Celeuis("metric"),
    Kelvin("Standard")

}

private const val TAG = "WeatherViewModel"
class WeatherViewModel: ViewModel() {

    private val _zip = MutableLiveData<String>()
    val zip : LiveData<String> get() = _zip

    private val _unit = MutableLiveData<Temperature_Unit>()
    val unit get() = _unit

    private val _temperature = MutableLiveData<Float>()
    val temperature : LiveData<Float> get() = _temperature

    private val _description = MutableLiveData<String>()
    val description : LiveData<String> get() = _description

    private val _weatherResponse = MutableLiveData<List<WeatherDaily>>()
    val weatherResponse: LiveData<List<WeatherDaily>> get() = _weatherResponse

    private val _city = MutableLiveData<String>()
    val city : LiveData<String> get() = _city


    init {

        _weatherResponse.value = emptyList()
    }

    fun setZip(zipCode: String){
        _zip.value = zipCode
        getWeather()
    }

    fun setCity(cityName: String){
        _city.value = cityName
    }

    fun setUnit(string: String){
        when(string){
            Temperature_Unit.Fahrenheit.toString() -> _unit.value = Temperature_Unit.Fahrenheit
            Temperature_Unit.Celeuis.toString() -> _unit.value = Temperature_Unit.Celeuis
            Temperature_Unit.Kelvin.toString() -> _unit.value = Temperature_Unit.Kelvin
            else -> _unit.value = Temperature_Unit.Fahrenheit
        }
        getWeather()
    }

    private fun formatZip() = _zip.value + ",us"

    @SuppressLint("NewApi")
    fun getWeather() {
        Log.d(TAG, "getWeather: ")
        viewModelScope.launch {

            try {
                val weatherR = WeatherApi.retrofitService.getWeathers(
                    formatZip(),
                    units = unit.value?.unit.toString()
                )
                _city.value = weatherR.city.name
                _temperature.value = weatherR.list[0].main.temp
                _description.value = weatherR.list[0].weather[0].main

                val input_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val output_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")
                val mList = mutableListOf<WeatherDaily>()

                weatherR.list.forEach { weatherItem ->

                    val dateTime = LocalDateTime.parse(weatherItem.dt_txt, input_formatter)
                    val output_date = dateTime.format(output_formatter).toString()

                    val (date_s, time_s) = output_date.split("\\s+".toRegex(), 2)
                    val imageUrl =
                        "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}@2x.png"
                    val newItem = WeatherDaily(date_s, time_s, weatherItem.main.temp, imageUrl)

                    mList.add(newItem)
                }

                val maps = mList.groupBy { it.date }.filterValues {it.isNotEmpty()}
                for (i in maps.keys){
                    val coldest = maps[i]?.map { it.temperature }?.minOrNull()
                    val hottest = maps[i]?.map { it.temperature }?.maxOrNull()

                    for (j in maps[i]!!){
                        if (j.temperature == coldest){
                            j.isColdest = true
                        }
                        if (j.temperature == hottest){
                            j.isWarmest = true
                        }
                    }
                }

                _weatherResponse.value = mList

            }
            catch (exception: Exception){
                _description.value = "Error, please check your settings"
                exception.printStackTrace()
            }
        }
    }

}