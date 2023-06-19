#!/bin/bash

HOME=$1

JAR_NAME=$(ls $HOME/build/libs/*.jar | grep -v plain)
VERSION=$(echo $JAR_NAME | grep -o -E "[0-9]+\.[0-9]+\.[0-9]+")
echo $VERSION
