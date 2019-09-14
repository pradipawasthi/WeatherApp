package com.pradip.weatherapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doubtnutapp.domain.similarVideo.interactor.GetWeatherForecastUseCase
import com.pradip.domain.entities.WeatherForecastEntity
import com.pradip.weatherapp.dataModel.WeatherForecastDataModel
import com.pradip.weatherapp.mapper.WeatherForecastDataMapper
import com.pradip.weatherapp.plus
import com.pradip.weatherapp.utils.applyIoToMainSchedulerOnSingle
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

class MainViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val weatherForecastDataMapper: WeatherForecastDataMapper,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    companion object {
        const val PAGE = 4
    }


    private val _getWeatherForecast = MutableLiveData<WeatherForecastDataModel>()
    val getWeatherForecast: LiveData<WeatherForecastDataModel>
        get() = _getWeatherForecast

    fun getWeatherforeCast(city: String, latitude: Double, longitude: Double) {

        compositeDisposable + getWeatherForecastUseCase
            .execute(GetWeatherForecastUseCase.Param(city, latitude, longitude, PAGE))
            .applyIoToMainSchedulerOnSingle()
            .subscribe(this::onSuccess, this::onError)


    }

    private fun onSuccess(weatherForecastEntity: WeatherForecastEntity) {

        val weatherForecastData = weatherForecastEntity.run {
            weatherForecastDataMapper.map(weatherForecastEntity)
        }
        Log.d("errorRequest", "success.toString()")

    }


    private fun onError(error: Throwable) {
        Log.d("errorRequest", error.toString())

    }

}