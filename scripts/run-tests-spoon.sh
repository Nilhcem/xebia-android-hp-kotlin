#!/bin/bash

# avoid virtual desktop jumps at mac terminal on test run
export JAVA_TOOL_OPTIONS='-Djava.awt.headless=true'

./gradlew clean spoonMockDebugAndroidTest

echo "test reports: $(pwd)/app/build/spoon/mock/debug/index.html"
