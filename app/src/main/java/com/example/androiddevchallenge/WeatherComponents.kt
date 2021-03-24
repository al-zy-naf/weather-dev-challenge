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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextOnlyToolbar(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .semantics {
                contentDescription = "You are viewing the $text weather report"
            }
    )
}

@Composable
fun BasicWeatherInfo(modifier: Modifier, weatherData: WeatherData = WeatherData.random()) {

    Row(
        modifier = modifier.semantics(mergeDescendants = true) {
            contentDescription =
                "The weather in Jakarta, Indonesia is currently ${weatherData.weatherSemanticsName} with a temperature of 32 degrees Celsius"
        },
        verticalAlignment = Alignment.CenterVertically
    ) {

        // TODO: Set the content description
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)

        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = "32° C",
                style = MaterialTheme.typography.h2
            )

            Text(
                text = "Sunny",
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Composable
fun HumidityAndWindSimple(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.semantics(mergeDescendants = true) {
            contentDescription =
                "Currently, the air humidity in Jakarta, Indonesia is 85% while the wind speed is 6 km/h"
        }
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.humidity_test),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight()
                )
                Text(
                    text = "85%",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.wind_test),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight()
                )
                Text(
                    text = "6 km/h",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DailyForecast(modifier: Modifier, list: List<DailyForecast>) {

    Text(
        text = "Forecast",
        style = MaterialTheme.typography.h4,
        modifier = modifier
    )

    // Initially, I want to use "horizontal" parameter on PaddingValues but the compiler keep saying "Cannot find a parameter with this name: horizontal"
    LazyRow(
        modifier = Modifier.padding(bottom = 16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(list) { dailyForecast ->
            val date = dailyForecast.date
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.semantics(mergeDescendants = true) {
                    contentDescription =
                        "The weather in Jakarta on $date is expected to be ${dailyForecast.weatherData.weatherSemanticsName} with a temperature of ${dailyForecast.temperature} degrees Celsius"
                }
            ) {
                Text(
                    text = "${date.substring(0, 3)} ${date.substring(date.indexOf(" ") + 1,date.length)}",
                    style = MaterialTheme.typography.h5
                )
                Image(
                    painter = painterResource(id = dailyForecast.weatherData.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .size(64.dp)
                )
                Text(
                    text = "${dailyForecast.temperature}° C",
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}

@Composable
fun SunriseAndSunset(modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.semantics(true) {
                contentDescription = "The next sunrise in Jakarta, Indonesia will occur at 5:56"
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
            )

            Text(
                text = "Sunrise",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium
            )
            Text(
                "05:56",
                style = MaterialTheme.typography.h6
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.semantics(true) {
                contentDescription = "The next sunset in Jakarta, Indonesia will occur at 18:01"
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
            )

            Text(
                text = "Sunset",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "18:01",
                style = MaterialTheme.typography.h6
            )
        }
    }
}
