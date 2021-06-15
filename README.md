# AboutQrJava
Create and Scan QR using zxing library in Java

# Using Zxing Library
[Zxing Library](https://github.com/journeyapps/zxing-android-embedded)

# I am using Sdk Under 24
[build.gradle]
```
implementation('com.journeyapps:zxing-android-embedded:4.2.0') { transitive = false }
implementation 'com.google.zxing:core:3.3.0'

android {
    buildToolsVersion '28.0.3' // Older versions may give compile errors
}
```
## If you are minSdk Version 24 or more
[build.gradle]
```
implementation 'com.journeyapps:zxing-android-embedded:4.2.0'
implementation 'androidx.appcompat:appcompat:1.0.2'

android {
    buildToolsVersion '28.0.3' // Older versions may give compile errors
}
```
# You Can Create QR
- If you want Create QR, Please note `generateQRCode(String contents)` in "MainActivity"

# You Can Scan QR
- If you want to Scan QR, Please note `ScanQrActivity`
- Don't forget to declare in `AndroidManifest.xml`
```
    <!-- if sdk is min 24 you must declare -->
    <uses-sdk tools:overrideLibrary="com.google.zxing.client.android" />

    <application
        android:hardwareAccelerated="true"
        ...
        />
```

