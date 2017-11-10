# Yuneec-RTSP-Player-Android
Android Library for displaying live video stream from camera. This library uses [IJKPlayer](https://github.com/Bilibili/ijkplayer), which is based on [FFmpeg](http://ffmpeg.org/). 

## Usage

### Pull in using maven

To use this library in your Android application, you can add the following to the root `build.grade`:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then, add the dependency to the app's `build.gradle`:

```
compile 'com.github.YUNEEC:Yuneec-RTSP-Player-Android:v0.1'
```
where X.Y.Z is the version to select.

The source code in this repository is published under the [BSD 3-Clause license](LICENSE).
