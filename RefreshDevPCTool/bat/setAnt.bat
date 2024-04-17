@echo off

set ANT_INSTALL_DIR=C:\Temp\_apache-ant-1.7.1
set ANT_INSTALL_FILE=_apache-ant-1.7.1.zip

if not exist "%ANT_INSTALL_DIR%" goto INSTALL
goto END

:INSTALL
	mkdir C:\Temp 2> nul
	copy "C:\S21_EZDeveloper\workspace\RefreshDevPCTool\ant\%ANT_INSTALL_FILE%" C:\Temp /Y

	pushd C:\Temp\
		jar xvf "%ANT_INSTALL_FILE%"
		del "%ANT_INSTALL_FILE%"
	popd

:END
	set ANT_HOME=%ANT_INSTALL_DIR%
	set OLD_PATH=%PATH%
	set PATH=%ANT_HOME%/bin;%OLD_PATH%
	set ANT_OPTS=-Xmx768m
