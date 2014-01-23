@echo off
rmdir /S /Q "build/classes"
rmdir /S /Q "build/libs"
rmdir /S /Q "build/resources"
gradlew build
