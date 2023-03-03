<img src="https://raw.githubusercontent.com/moazelsawaf/dash_bubble/main/doc/assets/package_banner.png" width="100%" alt="Dash Flags Banner" />
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

*The plugin currently supports **Android** only.*

<br>
<p align="center">
<img src="https://raw.githubusercontent.com/moazelsawaf/dash_bubble/main/doc/assets/animated_example.webp" width="50%" alt="Animated Example" />
<br>
This GIF is taken from the <a href="https://github.com/moazelsawaf/dash_bubble/tree/main/example">Example Project</a>
</p>

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

The plugin uses these two permissions but you don't need to add them to your `AndroidManifest.xml` file because the plugin will add them automatically:

```xml
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
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

**Note**: All the methods are asynchronous and has a return type of `Future<bool>`.

| Method | Description | Parameters | Behavior | Notes |
| --- | --- | --- | --- | --- |
| `requestPermission()` | Requests the permission to draw over other apps | - | Returns `true` if the permission is granted, `false` otherwise | If the permission is already granted, this method will return `true` without asking the user<br><br>In Android versions prior to `Android 6.0 (M)`, this method will return `true` without asking the user |
| `hasPermission()` | Checks if the permission to draw over other apps is granted | - | Returns `true` if the permission is granted, `false` otherwise | - |
| `isRunning()` | Checks if the bubble is currently running | - | Returns `true` if the bubble is running, `false` otherwise | The bubble is considered running if it is visible on the screen |
| `startBubble()` | Starts the bubble | BubbleOptions? options<br><br>Function()? onBubbleTap | Returns `true` if the bubble started successfully, `false` otherwise | If the bubble is already running or the permission is not granted, this method will return `false` |
| `stopBubble()` | Stops the bubble | - | Returns `true` if the bubble stopped successfully, `false` otherwise | If the bubble is not running, this method will return `false` |

## 📝 Bubble Customization Options

**Note**: All the options are optional and you can pass only the options you want to customize.

| Option | Description | Default |
| --- | --- | --- |
| `notificationTitle` | The title of the service notification | `null` |
| `notificationText` | The text of the service notification | `null` |
| `notificationIcon` | The icon of the service notification | `null` |
| `bubbleIcon` | The icon of the bubble | `null` |
| `closeIcon` | The icon of the close button | `null` |
| `startLocationX` | The initial starting position of the bubble on the x axis | `0` |
| `startLocationY` | The initial starting position of the bubble on the y axis | `200` |
| `bubbleSize` | The size of the bubble | `60` |
| `enableClose` | Whether to show the close button or not | `true` |
| `enableAnimateToEdge` | Whether to animate the bubble to the edge of the screen when it is dragged to the edge of the screen or not | `true` |
| `enableBottomShadow` | Whether to show the bottom shadow behind the close button of the bubble or not | `true` |
| `keepAliveWhenAppExit` | Whether to keep the bubble alive when the app is closed or not | `false` |

## ✅ Roadmap

- [ ] Add Tests 🧪
- [ ] Implement a ready-to-use `AppBubble` which starts automatically when the app is on the background and stops when the app is on the foreground and has the ability to bring the app to the foreground when the bubble is tapped 📱
- [ ] Ability to pass a `Widget` as the `bubbleIcon` and `closeIcon` 💪🏻
- [ ] Implement the `ExpandableView` feature in the original library ⚡

## 💪🏻 Contribution Guide

I would be happy to have your contributions 💙

If you find a bug or want a feature, but don't know how to fix/implement it, please fill an [Issue](https://github.com/moazelsawaf/dash_bubble/issues).  
If you fixed a bug or implemented a feature, please send a [Pull Request](https://github.com/moazelsawaf/dash_bubble/pulls).

<a href="https://github.com/moazelsawaf/dash_bubble/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=moazelsawaf/dash_bubble" />
</a>

Made with [contrib.rocks](https://contrib.rocks).