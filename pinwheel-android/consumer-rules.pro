-keepclassmembers class ** {
  @android.webkit.JavascriptInterface public *;
}

-keepclassmembers class ** implements com.underdog_tech.pinwheel_android.model.PinwheelEventPayload {
  *;
}