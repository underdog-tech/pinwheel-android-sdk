# Change Log

All notable changes to this project will be documented in this file.

## 3.0.x Releases

- `3.0.x` Releases = [3.0.0](#300) | [3.0.1](#301) | [3.0.2](#302) | [3.0.3](#303) | [3.0.4](#304) | [3.0.5](#305) | [3.0.6](#306) | [3.1.0](#310) | [3.1.1](#311)

### [3.1.1](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.1.1)

#### Notes

- Internal support changes to improve conversion rates.

### [3.1.0](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.1.0)

#### Notes

This release introduces support for additional platforms and integrations, improving conversion rates and overall functionality.

**Important Note:**
Some integrations may now require camera access for verification purposes. Ensure the following permission is included in your `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.CAMERA" />
```

### [3.0.6](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.6)

#### Notes

- Internal bugfixes and support changes.

### [3.0.5](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.5)

#### Notes

- Internal bugfixes.

### [3.0.4](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.4)

#### Notes

- Internal bugfixes.

### [3.0.3](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.3)

#### Notes

- Add support for the `OTHER_EVENT` event type.

### [3.0.2](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.2)

#### Notes

- Internal bugfixes.

### [3.0.1](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.1)

#### Notes

- Expand internal functionality to support a broader range of platforms and increase conversion rate.

### [3.0.0](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/3.0.0)

#### Notes

This new major version bump introduces an updated API to support partner-based switches.

*Deprecated events have been removed and we have updated the success event to match the current standard switch schema (used in the `direct_deposit_switch.added` webhook and the `input_allocation` event).*

#### Changed
- The `action` field in `input_allocation` event is now optional.
- The `params` field in the `success` event uses the `input_allocation` schema with fields `action` and `allocation`.

#### Removed
- Removed `input_amount` event.

## 2.4.x Releases

- `2.4.x` Releases - [2.4.0](#240) | [2.4.1](#241) | [2.4.2](#242) | [2.4.3](#243) | [2.4.4](#244) | [2.4.5](#245) | [2.4.6](#246) | [2.4.7](#247)

---
### [2.4.7](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.7)

#### Notes

- Expand internal functionality to support a broader range of platforms and increase conversion rate.

### [2.4.6](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.6)

#### Notes

- Fix serialization issue caused by consumer Proguard rules. (`success` and `input_allocation` events payloads not serializing correctly)

### [2.4.5](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.5)

#### Notes

- Internal support changes for more powerful UI/UX expressivity.

### [2.4.4](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.4)

#### Notes

- Internal support changes.

### [2.4.3](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.3)

#### Notes

- Update Proguard consumer rules.

### [2.4.2](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.2)

#### Notes

- Adding more support for consumption by Pinwheel's React Native SDK.
- Proguard updates for support.

### [2.4.1](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.1)

#### Notes

- Adding support for consumption by Pinwheel's React Native SDK.

### [2.4.0](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.4.0)

#### Notes

We're thrilled to announce the latest version of our SDK! While you'll find that our familiar API contract remains unchanged, there's a host of improvements that make this upgrade indispensable:

- **Enhanced Redundancy**: 🛡️ We've fortified our systems, ensuring smoother recovery from integration failures for a significant percentage of our traffic.
- **Superior Uptime**: 🦾 Reliability is a top priority. This upgrade brings even more robust uptime for DDS integrations.
- **Increased Conversion**: ↗️ We are leveraging system level features to increase conversion.
- **Easy Upgrade**: 🥧 No changes were made to the documented API contract. Easy as pie.

## 2.3.x Releases

- `2.3.x` Releases - [2.3.14](#2314) | [2.3.15](#2315) | [2.3.16](#2316) | [2.3.17](#2317) | [2.3.18](#2318) | [2.3.19](#2319)

---

### [2.3.19](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.3.19)

- Add support for the following event types: `CARD_SWITCH_BEGIN`, `DD_FORM_BEGIN`, `DD_FORM_CREATE`, `DD_FORM_DOWNLOAD`, `SCREEN_TRANSITION`.

### [2.3.18](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.3.18)

- Enables the DOM storage setting as Link leverages localStorage.

### [2.3.17](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.3.17)

- Adding new payload for the PinwheelEventType.INPUT_ALLOCATION event.


### [2.3.16](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.3.16)

- Update version for test deploy


### [2.3.15](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.3.14)

#### Added

- PinwheelEventType.INPUT_ALLOCATION
  - Added by [Robby Abaya](https://github.com/rawbee) in Pull Request [#75](https://github.com/underdog-tech/pinwheel-android-sdk/pull/75).

### [2.3.14](https://repo1.maven.org/maven2/com/getpinwheel/pinwheel-android/2.3.14)

#### Added

- PinwheelEventType.INPUT_REQUIRED
  - Added by [Robby Abaya](https://github.com/rawbee) in Pull Request [#67](https://github.com/underdog-tech/pinwheel-android-sdk/pull/67).
