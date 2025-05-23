package com.olimhousestudio.qooraan.data.datasource.remote

import android.location.Location

sealed class LocationServiceCondition<T> {

    class NoGps<Nothing> : LocationServiceCondition<Nothing>()

    class MissingPermission<Nothing> : LocationServiceCondition<Nothing>()

    data class Success<T>(val location: Location?) : LocationServiceCondition<T>()

    class Error<T> : LocationServiceCondition<T>()
}