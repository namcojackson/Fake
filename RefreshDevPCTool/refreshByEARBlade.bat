@echo off

Rem isstop -c

call "C:\S21_EZDeveloper\workspace\RefreshDevPCTool\bat\setAntBlade.bat"

echo ***** 1) refresh development PC.
	call ant -file "C:\S21_EZDeveloper\workspace\RefreshDevPCTool\ant\refreshDevPCBlade.xml" refresh.byEAR

Rem isstart
	
pause
