package com.example.umbrella.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val ZIP_CODE = "ZIP_CODE"
const val TEMPERATURE = "TEMPERATURE_MODE"

enum class TEMPERATURE_TYPE {
    Fahrenheit, Celeuis, Kelvin
}

class WeatherViewModel : ViewModel() {

    private val _configs = MutableLiveData<MutableMap<String, String>>()
    val configs get() = _configs

    private val _zip = MutableLiveData<String>()
    val zip : LiveData<String> get() = _zip

    private val _temperature = MutableLiveData<String>()
    val temperature : LiveData<String> get() = _temperature

    init {
        _configs.value?.put(ZIP_CODE, "")
        _configs.value?.put(TEMPERATURE, TEMPERATURE_TYPE.Fahrenheit.toString())
    }

    fun getZipCodeCountryCode() : String {
        TODO()
    }

}