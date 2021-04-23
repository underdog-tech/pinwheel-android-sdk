# PinwheelSDK

Our complete documentation can be found at: [https://docs.getpinwheel.com/](https://docs.getpinwheel.com/).

The Pinwheel SDK is used to open up a modal in your application. Your end-users interact with the modal to submit their login credentials to authenticate with their payroll provider over a secure and encrypted connection.

## Usage

The Pinwheel Android SDK's main interface is a `Fragment` that you can integrate into your app as you would any `Fragment`. Additionally, you can implement the `PinwheelEventListener` interface to receive events throughout `PinwheelFragment`'s lifecycle.

### Installation

The Pinwheel Android SDK is available via [JitPack](https://jitpack.io/#underdog-tech/pinwheel-android-sdk), or alternatively via [GitHub Packages](https://github.com/underdog-tech/pinwheel-android-sdk/packages/719840).

#### JitPack
To install the SDK using JitPack:
1. Add  the JitPack url to your app's `build.gradle` file:
```gradle
repositories {
    maven {
        url 'https://jitpack.io'
    }
}
```

2. Add the package to your dependencies:
```gradle
dependencies {
    implementation 'com.github.underdog-tech:pinwheel-android-sdk:main-SNAPSHOT'
}
```

3. Sync your Android gradle project and the library should be ready to use.


#### GitHub Package
To install the SDK using GitHub packages:
1. Generage a personal access token [on GitHub](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token) with the `read:packages` scope. 

2. Store your credentials in a file named `github.properties`:
```txt
gpr.usr=<username or team>
gpr.key=ghp_xKEY
```

> Note: there are other ways to do this, e.g. environment vars, etc..

3. Add the GitHub repository to your app's `build.gradle` file:
```gradle
def githubProperties = new Properties()
githubProperties.load(new FileInputStream(rootProject.file("github.properties")))
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/underdog-tech/pinwheel-android-sdk")
        credentials {
            username = githubProperties['gpr.usr']
            password = githubProperties['gpr.key']
        }
    }
}
```

4. Add the package to your dependencies:
```gradle
dependencies {
    implementation 'com.underdog_tech.pinwheel:pinwheel-android:2.3.0'
}
```

5. Sync your Android gradle project and the library should be ready to use.

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

An example project is provided in the app directory. Add `include ':app'` to `settings.gradle`. Also add your API secret to `gradle.properties`. This is done purely for ease of testing the sample app. In your production app, you should fetch the Link token from your server, and you should never include your API secret in your app.

## Author

[Pinwheel](https://getpinwheel.com)

## License

PinwheelSDK is available under the MIT license. See the LICENSE file for more info.
