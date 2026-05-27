@echo off
setlocal
set MAVEN_PROJECTBASEDIR=%~dp0
if defined MAVEN_HOME (
  "%MAVEN_HOME%\bin\mvn" %*
) else (
  java -jar "%~dp0.mvn\wrapper\maven-wrapper.jar" %*
)
endlocal
