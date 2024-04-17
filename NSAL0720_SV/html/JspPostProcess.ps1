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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0720_SV\gen\jsp\NSAL0720Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0720_SV\gen\jsp\NSAL0720Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="xxBtnFlg".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxScrItem34Txt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxChkBox_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="serNum_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxChkBox_A2".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="mtrLbDescTxt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="billToCustLocAddr_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="billToCustLocAddr_A2".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="OpenWin_BillTo".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxGenlFldAreaTxt_A1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file