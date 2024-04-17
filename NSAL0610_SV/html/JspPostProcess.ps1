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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0610_SV\gen\jsp\NSAL0610Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0610_SV\gen\jsp\NSAL0610Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="serNum_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxBtnFlg_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="mtrLbDescTxt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxChkBox_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="serNum_N".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx2}"' `
        -replace '(name="xxBtnFlg_N".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx2}"' `
        -replace '(name="mtrLbDescTxt_N".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx2}"' `
        -replace '(name="xxChkBox_N".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx2}"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file
