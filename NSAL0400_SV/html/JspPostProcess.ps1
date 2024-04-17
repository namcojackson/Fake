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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0400_SV\gen\jsp\NSAL0400Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0400_SV\gen\jsp\NSAL0400Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
	$_ -replace '(name="xxScrItem40Txt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="contrVrsnEffFromDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="dsContrCtrlStsNm_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="dsContrCtrlStsNm_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="contrVrsnEffThruDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="dsAcctNm_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="contrCloDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="trmnTotAmt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="trmnOvrdTotAmt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="supprCrFlg_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="contrTrmnFlg_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
	        -replace '(name="".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' `
		-replace '(name="xxChkBox_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=hdrIdx %>"' 
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file