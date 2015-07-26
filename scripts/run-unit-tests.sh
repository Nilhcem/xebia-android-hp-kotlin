#!/bin/bash

# avoid virtual desktop jumps at mac terminal on test run
export JAVA_TOOL_OPTIONS='-Djava.awt.headless=true'

./gradlew :app:testMockDebug

echo "test reports: $(pwd)/app/build/reports/tests/mockDebug/index.html"
