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
$org_file = 'C:\S21_Git\CSA\S21_CSA_APP\NSAL0300_SV\gen\jsp\NSAL0300Scrn00.jsp'
$dst_file = 'C:\S21_Git\CSA\S21_CSA_APP\NSAL0300_SV\gen\jsp\NSAL0300Scrn00.jsp.tmp'
(Get-Content -Path $org_file) | ForEach-Object {
	$_ -replace '(name="xxChkBox_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="sqId_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="xxLinkProt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="dsContrDtlPk_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="svcMachMstrPk_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="svcMachMstrPk_AL".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="serNum_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="mdseCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="mdlNm_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="mdlNm_AL".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="dsContrDtlStsDescTxt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="mdseDescShortTxt_AS".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="svcPgmMdseCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="billToCustLocAddr_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="contrEffFromDt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="contrEffThruDt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="baseBllgCycleCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="basePrcDealAmt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="mtrReadMethCd_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="rnwEffFromDt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="contrRnwTotCnt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="contrCloDt_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="prcMtrPkgPk_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="prcMtrPkgNm_A".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="<%=a%>"' `
		-replace '(name="dsContrBllgMtrPk_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="dsContrDtlPk_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="svcMachMstrPk_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="serNum_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mdseCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mdseDescShortTxt_BI".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mdlNm_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="contrEffFromDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="contrEffThruDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="nextBllgDt_BB".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="nextBllgDt_BU".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="shipToCustCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="shipToLocNm_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxListNum_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="baseBillToCustCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="billToLocNm_BB".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="ctacPsnFirstNm_BL".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="ctacPsnFirstNm_BB".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="ctacPsnLastNm_BB".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="baseBllgTmgCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
        -replace '(name="xxTpCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="baseDplyPerEndDay_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="contrBllgDay_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="svcPgmMdseCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mdseDescShortTxt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="baseBllgCycleCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="basePrcDealAmt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxListNum_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="basePrcTermDealAmtRate_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="usgBillToCustCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="billToLocNm_BU".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxPsnNm_BU".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mtrDplyPerEndDay_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mtrBllgDay_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="bllgMtrBillToCustCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="billToLocNm_BM".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="ctacPsnFirstNm_ML".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="ctacPsnFirstNm_BM".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="ctacPsnLastNm_BM".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mtrLbDescTxt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="bllgMtrBllgCycleCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xsChrgTpCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xsMtrCopyQty_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xsMtrAmtRate_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="rnwEffFromDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="contrRnwTotCnt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="contrCloDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="billToCustLocAddr_BB".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="shipToCustLocAddr_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="svcBrCd_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="svcBrCdDescTxt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="mtrLbDescTxt_BX".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="contrMtrMultRate_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="billToCustLocAddr_BM".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxComnScrColValTxt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="bllgFreeCopyCnt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="bllgMinCnt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="bllgMinAmtRate_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="bllgRollOverRatio_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="cumCopyCnt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="cumCopyFreqMthAot_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="cumCopyStartDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="cumCopyEndDt_B".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxLinkProt_BB".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxLinkProt_BM".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxLinkProt_BC".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxLinkProt_BD".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="xxLinkProt_BA".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_AdditionalCharge".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_PricingEffectivity_Base".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_Schedule_Base".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_ShipTo".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_BillTo_Base".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_ServiceProgram".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_Contact_Base".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_BillingCounters".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_PricingEffectivity_Meter".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_Schedule_Usage".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_BillTo_Meter".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_Contact_Meter".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_FreeCopyRollOverHistory".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_SpecialInstruction_Base".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '(name="OpenWin_SpecialInstruction_Meter".*)ezfArrayNo="[0-9]+"', '$1ezfArrayNo="${b}"' `
		-replace '<ezf:inputHidden name="xxScrItem500Txt" ezfName="" />', '<input type="hidden" name="xxScrItem500Txt" value="">' `
		-replace '<ezf:inputHidden name="xxListNum_LY" ezfName="" />', '<input type="hidden" name="xxListNum_LY" value="">'
} | Set-Content -Encoding UTF8 $dst_file
Move-Item -Force $dst_file $org_file