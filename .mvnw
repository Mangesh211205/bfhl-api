#!/usr/bin/env sh
# ----------------------------------------------------------------------------
# Maven Wrapper
# ----------------------------------------------------------------------------
basedir=$(dirname "$0")

if [ -z "$MAVEN_PROJECTBASEDIR" ]; then
  MAVEN_PROJECTBASEDIR="$basedir"
fi

if [ -n "$MAVEN_HOME" ] ; then
  exec "$MAVEN_HOME/bin/mvn" "$@"
else
  exec java -jar "$basedir/.mvn/wrapper/maven-wrapper.jar" "$@"
fi
