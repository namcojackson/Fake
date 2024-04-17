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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0380_SV\gen\jsp\NSAL0380Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0380_SV\gen\jsp\NSAL0380Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="contrAutoRnwTpCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="0"' `
        -replace '(name="befEndRnwDaysAot_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="0"' `
        -replace '(name="maxContrRnwCnt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="0"' `
        -replace '(name="maxPrcUpRatio_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="0"' `
        -replace '(name="basePrcUpRatio_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="0"' `
        -replace '(name="mtrPrcUpRatio_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="0"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file
