@echo off
setlocal
set "PROMPT=%*"
if not defined PROMPT set "PROMPT=Check current IntelliJ task orientation"

echo ==============================
echo S+R AIluminode
echo ==============================
echo Project: %CD%
echo.

call "C:\digitalJungleTours\coding\NodAIity\AIluminode\ailuminode.bat" scan "%PROMPT%"

endlocal
