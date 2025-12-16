@echo off
:: palisades.lakes (at) gmail (dot) com
:: 2025-12-16

:: TODO: probably not relevant anymore
::set THRUPUT=
set THRUPUT=-server -Xbatch -XX:+UseFMA

::set XMX=-Xms56g -Xmx56g 
::set XMX=-Xms31g -Xmx31g -Xmn12g 
set XMX=-Xms12g -Xmx12g -Xmn5g 

set OPENS=--add-opens java.base/java.lang=ALL-UNNAMED

set CP=-cp lib/*

set JAVA="%JAVA_HOME%\bin\java"

set CMD=%JAVA% %THRUPUT% -ea -dsa %XMX% %OPENS% %CP% %*
::echo %CMD%
%CMD%
