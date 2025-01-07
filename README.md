# Android Project README

## Project Overview
This Android project is designed to efficiently handle REST API communication, data conversion, image loading, and dynamic font usage. The following dependencies are integrated to ensure seamless development and high performance.

## Dependencies

### 1. REST API Usage
**Library:** Retrofit  
**Dependency:**
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.11.0'
```
**Purpose:** Retrofit is a type-safe HTTP client for Android and Java that simplifies the process of consuming RESTful APIs.

### 2. Data Conversion (JSON)
**Library:** Gson Converter for Retrofit  
**Dependency:**
```gradle
implementation 'com.squareup.retrofit2:converter-gson:2.11.0'
```
**Purpose:** Converts JSON responses from REST APIs into Java/Kotlin objects.

### 3. Downloadable Icons
**Library:** Coil (Coil 3)  
**Dependency:**
```gradle
implementation 'io.coil-kt.coil3:coil-compose:3.0.4'
```
**Purpose:** Coil is an image loading library for Android that supports Compose. It provides efficient image downloading and caching.

### 4. Downloadable Fonts
**Library:** Jetpack Compose Google Fonts  
**Dependency:**
```gradle
implementation 'androidx.compose.ui:ui-text-google-fonts:1.7.6'
```
**Purpose:** Allows for the dynamic use of Google Fonts in Jetpack Compose applications.

## Installation
1. Clone the project repository.
2. Open the project in Android Studio.
3. Ensure that you have the latest version of Android Studio.
4. In the `build.gradle` (Module) file, add the dependencies listed above.
5. Sync the project with Gradle files.

## How to Use
- **Retrofit:** Use to make network calls and interact with REST APIs.
- **Gson Converter:** Parse API responses into data classes.
- **Coil:** Load images from URLs directly into Compose components.
- **Google Fonts:** Easily apply custom fonts by specifying font families directly in the UI.

## License
This project is licensed under the [MIT License](LICENSE).

## Contribution
Contributions are welcome! Feel free to submit a pull request or report issues.

