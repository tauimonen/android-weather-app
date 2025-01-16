# Android Project README

## Project Overview
This Android project streamlines REST API communication, data handling, image loading, and dynamic font integration. Key dependencies ensure smooth development, maintainability, and optimal performance.

## Dependencies

### 1. REST API Integration
**Library:** Retrofit  
**Dependency:**
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.11.0'
```
**Purpose:** Retrofit is a type-safe HTTP client that simplifies RESTful API consumption, enabling efficient network operations.

### 2. JSON Data Conversion
**Library:** Gson Converter for Retrofit  
**Dependency:**
```gradle
implementation 'com.squareup.retrofit2:converter-gson:2.11.0'
```
**Purpose:** Automates the conversion of JSON API responses into Java/Kotlin objects, streamlining data manipulation.

### 3. Image Loading and Caching
**Library:** Coil (Coil 3)  
**Dependency:**
```gradle
implementation 'io.coil-kt.coil3:coil-compose:3.0.4'
```
**Purpose:** Coil is an efficient image loading library for Android Compose, providing seamless image downloads and caching.

### 4. Dynamic Font Integration
**Library:** Jetpack Compose Google Fonts  
**Dependency:**
```gradle
implementation 'androidx.compose.ui:ui-text-google-fonts:1.7.6'
```
**Purpose:** Enables the use of Google Fonts in Jetpack Compose, allowing dynamic font customization directly within the UI.

### 5. ViewModel for Compose
**Library:** AndroidX Lifecycle ViewModel for Compose  
**Dependency:**
```gradle
implementation 'androidx.lifecycle:lifecycle-viewmodel-compose'
```
**Purpose:** Integrates ViewModel support with Jetpack Compose, facilitating state management and lifecycle awareness in Compose applications.

### 6. Location Services for Android  
Library: Google Play Services Location  
Dependency:  

```kotlin
implementation 'com.google.android.gms:play-services-location:21.3.0'
```

**Purpose:** Provides APIs for accessing location-based services on Android, including geofencing, activity recognition, and location updates, to build location-aware applications effectively.

### 7. Hilt Android Gradle Plugin
Library: Hilt for Dependency Injection
Dependency:

```kotlin
implementation "com.google.dagger:hilt-android:2.55"
kapt "com.google.dagger:hilt-compiler:2.55"
```

**Purpose:** Facilitates dependency injection in Android applications, simplifying the process of managing dependencies, promoting modularity, and improving testability. It integrates seamlessly with Jetpack components and Android's lifecycle.

## OpenWeatherMap API calls:

### Current weather data:
https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

### Forecast for 5 days with data every 3 hours by geographic coordinates. All weather data can be obtained in JSON and XML formats.
api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}

## Installation
1. Clone the project repository:
   ```bash
   git clone <repository-url>
   ```
2. Open the project in Android Studio.
3. Verify that you are using the latest version of Android Studio.
4. Add the dependencies to your `build.gradle` (Module) file.
5. Sync the project with Gradle files to ensure proper configuration.

## How to Use
- **Retrofit:** Simplifies network calls and API interactions.
- **Gson Converter:** Transforms API responses into structured data classes.
- **Coil:** Efficiently loads and caches images directly into Compose components.
- **Google Fonts:** Dynamically apply custom fonts by specifying font families within the Compose UI.
- **ViewModel Compose:** Manage state and lifecycle events seamlessly in Jetpack Compose.

## License
This project is distributed under the [MIT License](LICENSE).

## Contribution
Contributions are encouraged and appreciated! Feel free to submit pull requests or report any issues.

