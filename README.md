# AdDroid Adware
This app is an adware* made using Google's Admob SDK. It can display ads on boot, on unlocking the screen, on installing/removing an app, etc. **For educational purposes only**.
On Android 10+, you will need to enable "Display over other apps" permission for the app for the ads to be displayed, due to Android's background activity start restrictions.
## Features
1. Displays ads on receiving multiple intents: `RECEIVE_BOOT_COMPLETED`,`USER_PRESENT`, `PACKAGE_FULLY_REMOVED`, etc.
2. Undetectable in launcher and hard to find in apps list, as it has no name and logo
3. The app itself is open source (except for Google Admob SDK which is proprietary)

## Development status
Currently in early development stage. WIP.
