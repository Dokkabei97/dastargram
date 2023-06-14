#!/bin/bash

JAR_NAME=$(ls ../build/libs/*.jar | grep -v plain)
VERSION=$(echo $JAR_NAME | grep -o -E "[0-9]+\.[0-9]+\.[0-9]+")
echo $VERSION
