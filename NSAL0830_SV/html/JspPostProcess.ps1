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
$org_file = 'C:\S21_EZDeveloper\workspace\NSAL0830_SV\gen\jsp\NSAL0830Scrn00.jsp'
$dst_file = 'C:\S21_EZDeveloper\workspace\NSAL0830_SV\gen\jsp\NSAL0830Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
	$_ -replace '(name="xxChkBox_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="dsContrSrcRefNum_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="contrIntfcSrcTpCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="serNum_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="svcMachMstrPk_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="mdseCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="dsContrProcStsDescTxt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xsMtrCopyQty_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xsMtrAmtRate_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="AddPricingRow".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="DeletePricingRow".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xxRecHistCratTs_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xxRecHistCratByNm_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xxRecHistUpdTs_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xxRecHistUpdByNm_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xxRecHistTblNm_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%= i %>"' `
		-replace '(name="xxRadioBtn_H".*)value="{EZF_ROW_NUMBER}"', '$1value="<%= String.valueOf(i) %>"' `
} | Set-Content -Encoding String $dst_file
Move-Item -Force $dst_file $org_file