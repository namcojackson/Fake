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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0690_SV\gen\jsp\NSAL0690Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0690_SV\gen\jsp\NSAL0690Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace 'ezfHyo="A" ezfArrayNo="0"', 'ezfHyo="A" ezfArrayNo="<%=indexA%>"' `
        -replace '(name="xxChkBox_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="mdseCd_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="serNum_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="t_MdlNm_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="xxScrItem8Txt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="dsContrCtrlStsNm_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="contrEffFromDt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="xxDiscRatio_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="contrEffThruDt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="xxThruDt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="basePrcDealAmt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="newBaseDealAmt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="xxGenlFldAreaTxt_B1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
        -replace '(name="OpenWin_Pricing".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${indexB}"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file