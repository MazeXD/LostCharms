#!/usr/bin/env bash
rm -f -r "build/classes"
rm -f -r "build/libs"
rm -f -r "build/resources"
exec ./gradlew build
