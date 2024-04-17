@echo off
for %%i in (../SharedResources/31dbjava/oracle/gen/src/business/db/*TMsg.java) do @call :proc %%i
exit /b

:proc
@set V=%1
@set XML_FILE_NAME=%V:~0,-9%.xml
@set source=../SharedResources/31dbjava/oracle/gen/xml/%XML_FILE_NAME%
if NOT EXIST %source% (
  @rem echo %XML_FILE_NAME%
  copy .\condition.xml .\temp\%XML_FILE_NAME% 1>NUL
)
