@echo OFF

echo Compiling the java files

:Bus_Java
if exist Bus.class (
    GOTO :Bus_Has_Class
) else (
    GOTO :Compile_Bus
)

:Bus_Has_Class
FOR /F %%i IN ('DIR /B /O:D Bus.java Bus.class') DO SET NEWEST=%%i
if "%NEWEST%"=="Bus.java" (
    GOTO :Compile_Bus
) else (
    echo Bus.java already Compiled
    GOTO :Rider_Java
)

:Compile_Bus
echo Compiling Bus.java...
javac Bus.java
echo Compiling Bus.java Completed

:Rider_Java
if exist Rider.class (
    GOTO :Rider_Has_Class
) else (
    GOTO :Compile_Rider
)

:Rider_Has_Class
FOR /F %%i IN ('DIR /B /O:D Rider.java Rider.class') DO SET NEWEST=%%i
if "%NEWEST%"=="Rider.java" (
    GOTO :Compile_Rider
) else (
    echo Rider.java already Compiled
    GOTO :BusGenarator_Java
)

:Compile_Rider
echo Compiling Rider.java...
javac Rider.java
echo Compiling Rider.java Completed

:BusGenarator_Java
if exist BusGenerator.class (
    GOTO :BusGenerator_Has_Class
) else (
    GOTO :Compile_BusGenerator
)

:BusGenerator_Has_Class
FOR /F %%i IN ('DIR /B /O:D BusGenerator.java BusGenerator.class') DO SET NEWEST=%%i
if "%NEWEST%"=="BusGenerator.java" (
    GOTO :Compile_BusGenerator
) else (
    echo BusGenerator.java already Compiled
    GOTO :RiderGenerator_Java
)

:Compile_BusGenerator
echo Compiling BusGenerator.java...
javac BusGenerator.java
echo Compiling BusGenerator.java Completed

:RiderGenerator_Java
if exist RiderGenerator.class (
    GOTO :RiderGenerator_Has_Class
) else (
    GOTO :Compile_RiderGenerator
)

:RiderGenerator_Has_Class
FOR /F %%i IN ('DIR /B /O:D RiderGenerator.java RiderGenerator.class') DO SET NEWEST=%%i
if "%NEWEST%"=="RiderGenerator.java" (
    GOTO :Compile_RiderGenerator
) else (
    echo RiderGenerator.java already Compiled
    GOTO :BusStop_Java
)

:Compile_RiderGenerator
echo Compiling RiderGenerator.java...
javac RiderGenerator.java
echo Compiling RiderGenerator.java Completed

:BusStop_Java
if exist BusStop.class (
    GOTO :BusStop_Has_Class
) else (
    GOTO :Compile_BusStop
)

:BusStop_Has_Class
FOR /F %%i IN ('DIR /B /O:D BusStop.java BusStop.class') DO SET NEWEST=%%i
if "%NEWEST%"=="BusStop.java" (
    GOTO :Compile_BusStop
) else (
    echo BusStop.java already Compiled
    GOTO :Demo_Java
)

:Compile_BusStop
echo Compiling BusStop.java...
javac BusStop.java
echo Compiling BusStop.java Completed

:Demo_Java
if exist Demo.class (
    GOTO :Demo_Has_Class
) else (
    GOTO :Compile_Demo
)

:Demo_Has_Class
FOR /F %%i IN ('DIR /B /O:D Demo.java Demo.class') DO SET NEWEST=%%i
if "%NEWEST%"=="Demo.java" (
    GOTO :Compile_Demo
) else (
    echo Demo.java already Compiled
    GOTO :Finish_Compiling
)

:Compile_Demo
echo Compiling Demo.java...
javac Demo.java
echo Compiling Demo.java Completed

:Finish_Compiling
echo Compiling Successfull
pause