@echo off
setlocal
set "ROOT=%~dp0"
set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
if not exist "%JAVA_EXE%" set "JAVA_EXE=C:\Program Files\Android\Android Studio\jbr\bin\java.exe"

if not exist "%JAVA_EXE%" (
    echo ERROR: Java not found. Set JAVA_HOME or install Android Studio JBR.
    exit /b 1
)

"%JAVA_EXE%" "-Dfile.encoding=UTF-8" -classpath "%ROOT%cli\build\install\ohbuoy\lib\cli.jar;%ROOT%cli\build\install\ohbuoy\lib\core.jar;%ROOT%cli\build\install\ohbuoy\lib\kotlin-stdlib-2.2.10.jar;%ROOT%cli\build\install\ohbuoy\lib\annotations-13.0.jar" io.nodaiity.ohbuoy.cli.OhBuoyCliKt %*
endlocal
