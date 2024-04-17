@echo off

REM isstop -c

call "C:\S21_EZDeveloper\workspace\RefreshDevPCTool\bat\setAnt.bat"

echo ***** 1) refresh development PC.
	call ant -file "C:\S21_EZDeveloper\workspace\RefreshDevPCTool\ant\refreshDevPC.xml" refresh.byEAR

REM isstart
	
pause
