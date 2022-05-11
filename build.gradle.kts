buildscript {
    extra.apply {
        set("compose_version", "1.1.1")
    }

    dependencies {
        classpath(Build.hiltAndroidGradlePlugin)
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.2.0" apply false
    id ("com.android.library") version "7.2.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
}



tasks.register("clear", Delete::class) {
    delete(rootProject.buildDir)
}