<img src="https://raw.githubusercontent.com/moazelsawaf/dash_bubble/main/doc/assets/package_banner.png" width="100%" alt="Dash Bubble Banner" />
<h2 align="center">
  Dash Bubble
</h2>

<p align="center">
  <a href="https://flutter.dev">
    <img src="https://img.shields.io/badge/Platform-Flutter-02569B?logo=flutter" alt="Platform">
  </a>
  <a href="https://pub.dartlang.org/packages/dash_bubble">
    <img alt="Pub Package" src="https://img.shields.io/pub/v/dash_bubble.svg?color=blue">
  </a>
  <a href="https://opensource.org/licenses/BSD-3-Clause"
  rel="ugc"><img src="https://img.shields.io/badge/License-BSD_3--Clause-red.svg" alt="License: BSD-3-Clause"></a>
  <br>
  <a href="https://github.com/moazelsawaf/dash_bubble" rel="ugc"><img   src="https://img.shields.io/github/languages/code-size/moazelsawaf/dash_bubble.svg" alt="GitHub code size in bytes"></a>
  <a href="https://github.com/moazelsawaf/dash_bubble">
    <img src="https://img.shields.io/github/stars/moazelsawaf/dash_bubble.svg?style=flat&logo=github&colorB=ffcc00&label=stars" alt="Star on GitHub">
  </a>
  <a href="https://github.com/moazelsawaf/dash_bubble/issues" rel="ugc"><img   src="https://img.shields.io/github/issues/moazelsawaf/dash_bubble.svg?color=DF1304" alt="GitHub Open Issues"></a>
  <a href="https://github.com/moazelsawaf/dash_bubble/commits/main" rel="ugc"><img   src="https://img.shields.io/github/last-commit/moazelsawaf/dash_bubble.svg" alt="GitHub Last Commit Date"></a>
</p>

---

## 💡 Overview

A Flutter plugin that allows you to create a floating bubble on the screen built on top of [Floating-Bubble-View](https://github.com/TorryDo/Floating-Bubble-View) library by [TorryDo](https://github.com/TorryDo) 💙

*The plugin currently supports **Android** only and **doesn't support IOS because** the feature of drawing over other apps is not available there*

<br>
<p align="center">
<img src="https://raw.githubusercontent.com/moazelsawaf/dash_bubble/main/doc/assets/animated_example.webp" width="25%" alt="Animated Example" />
<br>
This GIF is taken from the <a href="https://github.com/moazelsawaf/dash_bubble/tree/main/example">Example Project</a>
</p>

## 🚧 Breaking Changes

### v1.0.0

* 🚚 rename `onBubbleTap()` callback to `onTap()` in `startBubble()` method.
* ✨ convert the values of `startLocationX` and `startLocationY` to be in density-independent pixels (dp) instead of pixels (px) as per Flutter convention.

## 🔧 Setup

Set the minimum SDK version to `21` or higher in your `android/app/build.gradle` file:

```gradle
android {
    defaultConfig {
        ...
        minSdkVersion 21 // Set this to 21 or higher
        ...
    }
}
```

The plugin uses these two permissions but **you don't need to add them** to your `AndroidManifest.xml` file because the plugin will add them automatically:

```xml
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## 💻 Usage

Import the package:

```dart
import 'package:dash_bubble/dash_bubble.dart';
```

Use the singleton instance of `DashBubble` to access all the available methods, for example:

```dart
DashBubble.instance.requestPermission()
DashBubble.instance.startBubble()
```

📘 You can checkout the complete API Reference [here](https://pub.dev/documentation/dash_bubble/latest/)

## 🧰 Available Methods

### Notes

* All the methods are asynchronous and has a return type of `Future<bool>`.
* All the methods returns `false` if the platform is not **Android**.

| Method | Description | Parameters | Behavior | Notes |
| --- | --- | --- | --- | --- |
| `requestPermission()` | Requests the permission to draw over other apps | - | Returns `true` if the permission is granted, `false` otherwise | If the permission is already granted, this method will return `true` without asking the user<br><br>In Android versions prior to `Android 6.0 (M)`, this method will return `true` without asking the user |
| `hasPermission()` | Checks if the permission to draw over other apps is granted | - | Returns `true` if the permission is granted, `false` otherwise | In Android versions prior to `Android 6.0 (M)`, this method will always return `true` |
| `isRunning()` | Checks if the bubble is currently running | - | Returns `true` if the bubble is running, `false` otherwise | The bubble is considered running if it is visible on the screen |
| `startBubble()` | Starts the bubble | BubbleOptions? options<br><br>[Available Callbacks](#-available-callbacks) | Returns `true` if the bubble started successfully, `false` otherwise | If the bubble is already running or the permission is not granted, this method will return `false` |
| `stopBubble()` | Stops the bubble | - | Returns `true` if the bubble stopped successfully, `false` otherwise | If the bubble is not running, this method will return `false` |

## 📝 Bubble Customization Options

**Note**: All the options are optional and you can pass only the options you want to customize.

| Option | Description | Default | Notes |
| --- | --- | --- | --- |
| `bubbleIcon` | The icon of the bubble | `null` | The icon is set as an image file placed inside `android\app\src\main\res\drawable\` and the value of the parameter should be the the file name **without the extension**<br><br>If not provided, the icon will be the plugin icon |
| `closeIcon` | The icon of the close button | `null` | The icon is set as an image file placed inside `android\app\src\main\res\drawable\` and the value of the parameter should be the the file name **without the extension**<br><br>If not provided, there will be a default close icon |
| `startLocationX` | The initial starting position of the bubble on the x axis | `0` | - |
| `startLocationY` | The initial starting position of the bubble on the y axis | `200` | - |
| `bubbleSize` | The size of the bubble | `60` | - |
| `opacity` | The opacity of the bubble | `1.0` | The value must be between `0.0` and `1.0` |
| `enableClose` | Whether to show the close button or not | `true` | - |
| `closeBehavior` | The behavior of the close button | `CloseBehavior.following` | The value must be a member of `CloseBehavior` enum<br><br>Available values:<br>`CloseBehavior.following`<br>`CloseBehavior.fixed` |
| `distanceToClose` |The distance between the bubble and the bottom edge of the screen to show the close button | `100` | - |
| `enableAnimateToEdge` | Whether to animate the bubble to the edge of the screen when it is dragged to the edge of the screen or not | `true` | - |
| `enableBottomShadow` | Whether to show the bottom shadow behind the close button of the bubble or not | `true` | - |
| `keepAliveWhenAppExit` | Whether to keep the bubble alive when the app is closed or not | `false` | - |

## 📞 Available Callbacks

**Note**: All the callbacks are optional and they all can be passed as parameters to the `startBubble()` method.

| Callback | Description | Parameters | Notes |
| --- | --- | --- | --- |
| `onTap` | Called when the bubble is tapped | - | - |
| `onTapDown` | Called when the bubble is tapped down (pressed) | `double x`<br>`double y` | The parameters `x` and `y` are the coordinates of the bubble when it is tapped down |
| `onTapUp` | Called when the bubble is tapped up (released) | `double x`<br>`double y` | The parameters `x` and `y` are the new coordinates of the bubble after it is tapped up |
| `onMove` | Called when the bubble is moved | `double x`<br>`double y` | The parameters `x` and `y` are the new coordinates of the bubble after it is moved |

## 🔔 Service Notification

The service notification is a non-dismissible notification that is shown when the bubble is running to keep the service alive.

The notification is shown automatically when the bubble is started and hidden automatically when the bubble is stopped.

*Currently, their is no way to disable the notification.*

**Note**: Starting from `Android 13 (Tiramisu)`, the **POST_NOTIFICATIONS** permission must be granted to show the notification, however, you don't need to add the permission to the `AndroidManifest.xml` file because it is already added by the plugin, but you need to request it at the runtime, otherwise the notification will not be shown.

This permission can be requested by calling `requestPostNotificationsPermission()` method.

### Customization Options

The service notification can be optionally customized by passing a `notificationOptions` parameter to the `startBubble()` method.

**Note**: All the options are optional and you can pass only the options you want to customize. Here is a list of the available options in the `notificationOptions` parameter:

| Option | Description | Default | Notes |
| --- | --- | --- | --- |
| `id` | The id of the service notification | `101` | - |
| `title` | The title of the service notification | `null` | If not provided, the title will be the app name |
| `body` | The body of the service notification | `null` | If not provided, there will be no notification body |
| `icon` | The icon of the service notification | `null` | The icon is set as an image file placed inside `android\app\src\main\res\drawable\` and the value of the parameter should be the the file name **without the extension**<br><br>If not provided, the icon will be the plugin icon |
| `channelId` | The channel id of the service notification | `bubble_notification` | - |
| `channelName` | The channel name of the service notification | `Bubble Notification` | - |
  
## ✅ Roadmap

* [ ] Add Tests 🧪
* [ ] Implement a ready-to-use `AppBubble` which starts automatically when the app is on the background and stops when the app is on the foreground and has the ability to bring the app to the foreground when the bubble is tapped 📱
* [ ] Ability to pass a `Widget` as the `bubbleIcon` and `closeIcon` 💪🏻
* [ ] Implement the `ExpandableView` feature in the original library ⚡
* [x] Implement the the rest of the available callbacks in the original library `onMove(x,y)`, `onUp(x,y)`, and `onDown(x,y)` 🔗

## 💪🏻 Contribution Guide

I would be happy to have your contributions 💙

If you find a bug or want a feature, but don't know how to fix/implement it, please fill an [Issue](https://github.com/moazelsawaf/dash_bubble/issues).  
If you fixed a bug or implemented a feature, please send a [Pull Request](https://github.com/moazelsawaf/dash_bubble/pulls).

<a href="https://github.com/moazelsawaf/dash_bubble/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=moazelsawaf/dash_bubble" />
</a>

Made with [contrib.rocks](https://contrib.rocks).
