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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyTheme {

                ProvideWindowInsets {
                    MainScreen(viewModel.dailyForecasts)
                }
            }
        }
    }
}

@Composable
fun MainScreen(forecasts: List<DailyForecast>) {
    // We use surface for setting up the contentColor
    Surface(contentColor = MaterialTheme.colors.onPrimary) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primaryVariant
                        )
                    )
                )
                .statusBarsPadding()
                .verticalScroll(rememberScrollState()) // useful on landscape orientation
        ) {

            TextOnlyToolbar(text = "Jakarta, Indonesia")

            BasicWeatherInfo(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            HumidityAndWindSimple(
                Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            )

            SunriseAndSunset(
                modifier = Modifier
                    .fillMaxWidth()
            )

            DailyForecast(modifier = Modifier.padding(16.dp), list = forecasts)
        }
    }
}
