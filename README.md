# Blackbox: Singularity

Game currently WIP

### Setup
1. Download android studio preview here (https://developer.android.com/studio/preview/), extract somewhere you want to install the app. Then run the program `studio.exe` or `studio64.exe` (Depending on your JDK, you may need to install a java JDK). 
2. Open `blackbox.ipr` with Android Studio
3. Open View => Tool Windows => Gradle and click sync
3. Right click `desktop/java/blackbox.game/BlackboxGame` and click "Run". It will throw an error.
4. Go to Run => Run Configurations.. => choose DesktopLauncher, Arguments Tab => Working Directory => Others then browse to `blackbox/android/assets/` and click Apply => Run
5. Now run BlackboxGame again. It should work.

### License
See LICENSE