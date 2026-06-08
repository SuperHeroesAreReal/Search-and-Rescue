@echo off
setlocal
set "PROMPT=%*"
if not defined PROMPT set "PROMPT=Check current IntelliJ task orientation"

echo ==============================
echo S+R FULL ORIENTATION
echo ==============================
echo Project: %CD%
echo.

echo [1/3] OhBuoy
call "%~dp0sr-ohbuoy.bat" startup

echo.
echo [2/3] AIluminode
call "%~dp0sr-ailuminode.bat" "%PROMPT%"

echo.
echo [3/3] RECCE
call "C:\digitalJungleTours\coding\NodAIity\Search-and-Rescue\recce.bat"

echo.
echo ==============================
echo S+R COMPLETE
echo ==============================
endlocal
