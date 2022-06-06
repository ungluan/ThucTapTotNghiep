/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidkotlinfinal.navigation

/**
 * Available screens.
 */
enum class Screens {
    HOME,
    USER_DETAIL
}

/**
 * Interfaces that defines an app navigator.
 */
interface AppNavigator {
    // Navigate to a given screen.
    fun navigateTo(screen: Screens)
}
