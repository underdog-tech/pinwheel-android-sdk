# PinwheelSDK

Our complete documentation can be found at: [https://docs.getpinwheel.com/](https://docs.getpinwheel.com/).

The Pinwheel SDK is used to open up a modal in your application. Your end-users interact with the modal to submit their login credentials to authenticate with their payroll provider over a secure and encrypted connection.

## Usage

The Pinwheel Android SDK's main interface is a `Fragment` that you can integrate into your app as you would any `Fragment`. Additionally, you can implement the `PinwheelEventListener` interface to receive events throughout `PinwheelFragment`'s lifecycle.

### Installation

The Pinwheel Android SDK is available via the [Maven Central Repository](https://search.maven.org/artifact/com.getpinwheel/pinwheel-android).

1. Add `mavenCentral` to your app's `build.gradle` repositories block
```gradle
repositories {
    mavenCentral()
}
```

2. Add the package to your dependencies:
```gradle
dependencies {
    implementation 'com.getpinwheel:pinwheel-android:3.1.5'
}
```

3. Sync your Android gradle project and the library should be ready to use.

### Configuration
Some platform integrations may require camera access for verification purposes. Additionally, storage access is needed to ensure direct deposit form downloads function correctly on API 28 (Android 9) and below.

Ensure the following permissions are included in your `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.CAMERA" />

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="28" />
```

### PinwheelFragment

To initialize the `PinwheelFragment`, a short-lived Link token will need to be generated first. Your mobile app should fetch the Link token from your server. DO NOT ever send this request from the client side and publicly expose your `api_secret`. 

The link token returned is valid for 15 minutes, after which it expires and can no longer be used to initialize the `PinwheelFragment`. The expiration time is returned as a unix timestamp. You can read more about Link tokens on our [documentation site](https://docs.getpinwheel.com/docs/api/reference/pinwheel-api.v1.json/paths/~1link_tokens/post).

```kotlin
import com.underdog_tech.pinwheel_android.PinwheelFragment

// fetch link token from server
val pinwheelFragment = PinwheelFragment.newInstance(token)
// show fragment
```

## PinwheelEventListener

The `PinwheelEventListener` interface is set up such that every event goes through the required `onEvent(eventName:, payload:)` handler, and optional convenience methods are provided for `onLogin`, `onSuccess`, `onError`, and  `onExit`. Note that the `onEvent(eventName:, payload:)` handler will still be called alongside the convenience methods.   

### `onEvent(eventName: PinwheelEventType, payload: PinwheelEventPayload?)`

Callback whenever a user interacts with the modal (e.g. logs in, or initiates a switch). See the [events section](https://docs.getpinwheel.com/docs/api/docs/introduction/Link.md#link-events) of the Link documentation.

### `onExit(error: PinwheelError?)`

Optional callback whenever a user exits the modal either explicitly or if an error occurred that crashed the modal. Error codes can be seen [here](https://docs.getpinwheel.com/docs/api/docs/introduction/Errors.md).

### `onSuccess(result: PinwheelResult)`

Optional callback whenever a user completes a Link flow successfully. Note: This is simply a front end callback only. If a user begins a job, closes the app, and the job completes successfully this callback will not be called.

### `onLogin(result: PinwheelLoginPayload)`

Optional callback for when a user logs in successfully.

### `onError(error: PinwheelError)`

Optional callback for when an error occurs.

## Example

An example project is provided in the app directory. Add `include ':app'` to `settings.gradle`. Add your API secret as a global gradle property, `PINWHEEL_API_SECRET` in `$USER_HOME/.gradle/gradle.properties`. This is done purely for ease of testing the sample app. In your production app, you should fetch the Link token from your server, and you should never include your API secret in your app.

## Author

[Pinwheel](https://getpinwheel.com)

## License

PinwheelSDK is available under the MIT license. See the LICENSE file for more info.
