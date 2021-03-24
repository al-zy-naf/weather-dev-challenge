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

import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Locale

class MainViewModel : ViewModel() {
    private val calendar: Calendar = Calendar.getInstance()

    // We don't need to collect this as state because it never change
    val dailyForecasts = List(7) {
        val day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
        val date = calendar.get(Calendar.DATE)

        // Random Temperature
        val temperature = (30..36).random()
        val forecast = DailyForecast(
            WeatherData.random(),
            "$day $date",
            temperature
        )
        calendar.add(Calendar.DATE, 1)
        forecast
    }
}
