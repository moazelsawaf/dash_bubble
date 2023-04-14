## 1.0.0

* **Breaking:** chore: 🚚 rename `onBubbleTap()` callback to `onTap()` in `startBubble()` method
* **Breaking:** feat: ✨ convert the values of `startLocationX` and `startLocationY` to be in density-independent pixels (dp) instead of pixels (px) as per Flutter convention
* build: ⬆️ bump native dependency version to `v0.5.0`
* feat: ✨ add 3 new parameters to the bubble options (`opacity`, `closeBehavior`, and `distanceToClose`)
* feat: ✨ implement the rest of the available callbacks `onTapDown(x, y)`, `onTapUp(x, y)`, and `onMove(x ,y)` with refactor to the native code
* feat: ✨ Adopt pixel-independent values for all the measurements from now on as per Flutter convention
* chore: 🥅 add some assertions to `BubbleOptions` for more robustness
* style: ✏️ fix the spacing in the logged error messages
* chore: 🙈 remove some temporarily ignored files from .gitignore
* docs: 📝 update the documentation

## 0.0.2

* build: ⬆️ upgrade the version of the native dependency
* fix: 🐛 crash when start bubble immediately from background after exiting permission request
* feat: ✨ update the plugin behavior to return `false` in all the methods if the platform is not Android
* chore: 🏷️ change int parameters in `bubble_options` to be `double`
* refactor: 🔒 hide private libraries from the api
* refactor: 🚚 `ERROR_CODE` -> `ERROR_TAG` in constants at kotlin code
* docs: 💡 power up the example with custom bubble icon
* docs: 📝 improve the documentation

## 0.0.1

* Initial release 🎉
