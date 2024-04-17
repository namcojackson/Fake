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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0440_SV\gen\jsp\NSAL0440Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0440_SV\gen\jsp\NSAL0440Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="svcTermCondCatgDescTxt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermAttrbDescTxt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermAttrbMapValCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermCondDataValCd_PS".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="condValNum_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxTrxDt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxBtnFlg_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="serNum_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermAttrbMapValCd_M".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermCondDataValCd_MS".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="condValNum_M".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxTrxDt_M".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="Clear_ContrCondVal".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="OpenWin_ContrCondVal".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="Clear_MachCondVal".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="OpenWin_MachCondVal".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file
