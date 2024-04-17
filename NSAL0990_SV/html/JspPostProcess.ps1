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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0990_SV\gen\jsp\NSAL0990Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0990_SV\gen\jsp\NSAL0990Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="xxChkBox_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="serNum_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="mdseCd_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="mdseDescShortTxt_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="cpoMinOrdQty_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="entCpoDtlDealSlsAmt_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="ordCustUomQty_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="entDealNetUnitPrcAmt_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="imgSplyColorTpNm_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="imgSplyOemCd_C".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="xxChkBox_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="serNum_LK".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="serNum_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="t_MdlNm_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="mdseCd_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="mdseDescShortTxt_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="cpoMinOrdQty_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="entCpoDtlDealSlsAmt_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="ordCustUomQty_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="entDealNetUnitPrcAmt_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="imgSplyColorTpNm_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
        -replace '(name="imgSplyOemCd_E".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${idx}"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file
