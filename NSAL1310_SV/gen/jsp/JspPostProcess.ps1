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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL1310_SV\gen\jsp\NSAL1310Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL1310_SV\gen\jsp\NSAL1310Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="svcTermAttrbDefValTxt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="condValNum_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxTrxDt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermCondDataValCd_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermCondDataValCd_L1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="svcTermCondDataValCd_L1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="OpenWin_DefValue".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file
