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
$org_file = 'C:\S21_EZDeveloper\workspace\NPAL1080_SV\gen\jsp\NPAL1080Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NPAL1080_SV\gen\jsp\NPAL1080Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
    $_ -replace '(name="GetItemName".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="GetWhOrSupplierName".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="OpenWin_Item".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="OpenWin_Tracking".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="OpenWin_Wh_Supplier".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="dsSoLineStsDescTxt_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="insrcFlg_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="locNm_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="mdseCd_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="mdseDescShortTxt_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="ordQty_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="poCratFlg_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="poOrdNum_AC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqCancQty_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqFrzFlg_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqInsrcQty_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqLineCmntTxt_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqLineSubNum_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqLineNum_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqLineStsDescTxt_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqLineTpCd_SE".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqNum_AC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prchReqQty_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prntVndCd_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="prntVndNm_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="procrTpCd_SE".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="rwsNum_AC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="rwsPutAwayQty_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="rwsRefNum_AC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="rwsStsDescTxt_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="shipQty_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="shpgSvcLvlDescTxt_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="vndSoNum_AC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxChkBox_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxPopPrm_D1".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxRecHistCratByNm".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxRecHistCratTs".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxRecHistTblNm".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxRecHistUpdByNm".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxRecHistUpdTs".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
        -replace '(name="xxScrItem20Txt_AC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file
