/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

enum class WeatherData(@DrawableRes val icon: Int, val weatherSemanticsName: String) {
    SUNNY(R.drawable.sun, "sunny"),
    PARTLY_CLOUDY(R.drawable.partly_cloudy, "partly cloudy"),
    CLOUDY(R.drawable.partly_cloudy, "cloudy"),
    DRIZZLE(R.drawable.drizzle, "drizzling"),
    HEAVY_RAIN(R.drawable.heavy_rain, "heavy raining");

    companion object {
        fun random() = when ((0..4).random()) {
            0 -> SUNNY
            1 -> PARTLY_CLOUDY
            2 -> CLOUDY
            3 -> DRIZZLE
            else -> HEAVY_RAIN
        }
    }
}

data class DailyForecast(
    val weatherData: WeatherData,
    val date: String,
    val temperature: Int
)
