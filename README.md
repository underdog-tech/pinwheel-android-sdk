# Pinwheel: Android SDK

Pinwheel-android will help you easly integrate Link into your android application.

## Setup

In `gradle.properties` file, provide a `CDN_URL`

## minSdkVersion

```minSdkVersion 22```

## Integration

### PinwheelFragment
Sdk provides the `PinwheelFragment` that takes `linkToken: String` as an argument. 
([Learn how to get the linkToken.](https://docs.getpinwheel.com/api-reference/index.html#create-link-token))<br/>
`PinwheelFragment` has `newInstance(linkToken: String)` method but it is ok to use different way to pass the argument.
If `linkToken` is not present, fragment will throw `IllegalStateExcpetion`.

### PinwheelEventListener
To listen for the events coming from the Link, implement `PinwheelEventListener`. <br/>
Then simply provide the instance of listener to `PinwheelFragment` by setting `pinwheelEventListener` field.

```kotlin
interface PinwheelEventListener {

    fun onSuccess(successEvent: PinwheelSuccessEvent)

    fun onExit(exitEvent: PinwheelExitEvent)

    fun onEvent(actionEvent: PinwheelActionEvent)
}
```
