# Blackbox: Singularity

Game currently WIP

### Setup
1. Download android studio preview here (https://developer.android.com/studio/preview/), extract somewhere you want to install the app. Then run the program `studio.exe` or `studio64.exe` (Depending on your JDK, you may need to install a java JDK). 
2. File => Open => blackbox-singularity/build.gradle
3. Select OK on the 'Import Project from Gradle dialog' and import all modules 
4. Make sure that configure on demand is disabled in your IDEA/AS (File => Settings => Build, Execution, Deployment => Gradle-Android Compiler => Configure on demand)
5. Open View => Tool Windows => Gradle and click sync
6. Right click `desktop/java/blackbox.game/BlackboxGame` and click "Run". It will throw an error.
7. Go to Run => Run Configurations.. => choose DesktopLauncher, Arguments Tab => Working Directory => Others then browse to `blackbox/android/assets/` and click Apply => Run
8. Now run BlackboxGame again. It should work.

For more help see https://libgdx.badlogicgames.com/documentation/gettingstarted/Importing%20into%20IDE.html

### License
See LICENSE