@echo off
setlocal
REM path=d:\
REM echo local variable path'value:
REM set path
REM endlocalendlocal
REM echo system variable path'value:
REM set path

REM set up DCS_HOME
set DCS_HOME=..
REM set up the config dir
if "a%1"=="a" (
	set CONFIG_DIR=%DCS_HOME%\config
) else (
	set CONFIG_DIR=%1
)

REM add some configuration files, for example log properties etc...
set CLASSPATH=%CONFIG_DIR%;

REM set up jvm arguments
set JVM_ARGS=-XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -Xms512M -Xmx1024M -Ddcs.config.dir=%CONFIG_DIR% -Djava.util.logging.config.file=%CONFIG_DIR%\logging.properties


REM add all jar files in the "%DCS_HOME%\lib" into the CLASSPATH
for /R ..\lib %%A in (*.jar) do (
	set CLASSPATH=!CLASSPATH!;%%A
)

:: execute the application
echo *******************************************************************************************************************************
echo "java %JVM_ARGS% -classpath %CLASSPATH%" com.debuglife.codelabs.appmanage.TestStartUpWithBatCommond sites-configuration.xml
echo *******************************************************************************************************************************
java %JVM_ARGS% -classpath %CLASSPATH% com.debuglife.codelabs.appmanage.TestStartUpWithBatCommond 