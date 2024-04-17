#
# 1. "Generate JSP File"
# 2. Delete gen/prop/*.properties
# 3. "Execute Internationalization Tool"
# 4. Open this file in "Windows PowerShell ISE"
# 5. Go to Command Pane (Ctrl + d) and enter the following command
#    Set-ExecutionPolicy -Scope "Process" -ExecutionPolicy "RemoteSigned"
# 6. Press "Yes" button
# 7. Run script (F5)
#
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0570_SV\gen\jsp\NSAL0570Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0570_SV\gen\jsp\NSAL0570Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
	$_ -replace '(name="dsContrPrcEffSqNum_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="contrPrcEffFromDt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="contrPrcEffThruDt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="bllgCycleCd_A3".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="bllgMinCnt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="bllgMinAmtRate_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="bllgRollOverRatio_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="bllgFreeCopyCnt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="xsMtrCopyQty_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="xsMtrAmtRate_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="InsertPrcAllowRow".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="DeletePrcAllowRow".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="dsContrDtlStsNm_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="cratDt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' `
		-replace '(name="svcMemoRsnNm_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${i}"' 
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file