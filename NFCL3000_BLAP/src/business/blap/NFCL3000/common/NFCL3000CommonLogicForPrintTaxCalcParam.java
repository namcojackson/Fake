/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3000.common;

import java.util.List;

import business.blap.NFCL3000.constant.NFCL3000Constant;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/10/02   Fujitsu         S.Takami        Create          QC#53633-2
 * 2020/08/19   CITS            R.Kurahashi     Update          QC#57533
 *</pre>
 */
public class NFCL3000CommonLogicForPrintTaxCalcParam implements NFCL3000Constant {

    /** Print Tax Calculation API parameters if this value is true. */
    // START 2020/08/19 R.Kurahashi [QC#57533,MOD]
    //private static final boolean TAX_CALC_PRM_PRINT = false;
    private static final boolean TAX_CALC_PRM_PRINT = true;
    // END 2020/08/19 R.Kurahashi [QC#57533,MOD]

    /**
     * <pre>
     * Out put NWZC036101PMsg data to standard output.
     * @param taxcalcPMsgList List of NWZC036101PMsg Tax Calculation API Parameters
     * </pre>
     */
    public static void printTaxCalcApiPMsg(List<NWZC036101PMsg> taxcalcPMsgList) {

        if (!TAX_CALC_PRM_PRINT) {
            return;
        }
        for (NWZC036101PMsg taxCalcPMsg : taxcalcPMsgList) {
            System.out.println("++++ NFCL3000CommonLogic Tax Calc Header Start ++++");
            System.out.println("GLBL_CMPY_CD:" + String.valueOf(taxCalcPMsg.glblCmpyCd.getValue()));
            System.out.println("SLS_DT:" + String.valueOf(taxCalcPMsg.slsDt.getValue()));
            System.out.println("XX_MODE_CD:" + String.valueOf(taxCalcPMsg.xxModeCd.getValue()));
            System.out.println("DS_ACCT_NUM_SE:" + String.valueOf(taxCalcPMsg.dsAcctNum_SE.getValue()));
            System.out.println("BILL_TO_ACCT_NUM:" + String.valueOf(taxCalcPMsg.billToAcctNum.getValue()));
            System.out.println("BILL_TO_LOC_NUM:" + String.valueOf(taxCalcPMsg.billToLocNum.getValue()));
            System.out.println("DS_TAX_GRP_EXEM_CD_B:" + String.valueOf(taxCalcPMsg.dsTaxGrpExemCd_B.getValue()));
            System.out.println("DS_TAX_GRP_EXEM_RESL_FLG_B:" + String.valueOf(taxCalcPMsg.dsTaxGrpExemReslFlg_B.getValue()));
            System.out.println("DS_ACCT_NUM_ST:" + String.valueOf(taxCalcPMsg.dsAcctNum_ST.getValue()));
            System.out.println("SHIP_TO_LOC_NUM:" + String.valueOf(taxCalcPMsg.shipToLocNum.getValue()));
            System.out.println("DS_TAX_GRP_EXEM_CD_ST:" + String.valueOf(taxCalcPMsg.dsTaxGrpExemCd_ST.getValue()));
            System.out.println("TAX_CALC_FLG:" + String.valueOf(taxCalcPMsg.taxCalcFlg.getValue()));
            System.out.println("TAX_EXEM_FLG:" + String.valueOf(taxCalcPMsg.taxExemFlg.getValue()));
            System.out.println("TAX_EXEM_RSN_CD:" + String.valueOf(taxCalcPMsg.taxExemRsnCd.getValue()));
            System.out.println("LEASE_PRCH_OPT_CD:" + String.valueOf(taxCalcPMsg.leasePrchOptCd.getValue()));
            System.out.println("TRX_DT:" + String.valueOf(taxCalcPMsg.trxDt.getValue()));
            System.out.println("XX_TAX_CALC_HDR_NUM:" + String.valueOf(taxCalcPMsg.xxTaxCalcHdrNum.getValue()));
            System.out.println("GEO_CD_ST:" + String.valueOf(taxCalcPMsg.geoCd_ST.getValue()));
            System.out.println("DS_INSD_CTY_LIMIT_FLG_ST:" + String.valueOf(taxCalcPMsg.dsInsdCtyLimitFlg_ST.getValue()));
            System.out.println("TAX_AREA_ID_ST:" + String.valueOf(taxCalcPMsg.taxAreaId_ST.getValue()));
            System.out.println("FIRST_LINE_ADDR_ST:" + String.valueOf(taxCalcPMsg.firstLineAddr_ST.getValue()));
            System.out.println("SCD_LINE_ADDR_ST:" + String.valueOf(taxCalcPMsg.scdLineAddr_ST.getValue()));
            System.out.println("CTY_ADDR_ST:" + String.valueOf(taxCalcPMsg.ctyAddr_ST.getValue()));
            System.out.println("ST_CD_ST:" + String.valueOf(taxCalcPMsg.stCd_ST.getValue()));
            System.out.println("CNTY_NM_ST:" + String.valueOf(taxCalcPMsg.cntyNm_ST.getValue()));
            System.out.println("POST_CD_ST:" + String.valueOf(taxCalcPMsg.postCd_ST.getValue()));
            System.out.println("CTRY_CD_ST:" + String.valueOf(taxCalcPMsg.ctryCd_ST.getValue()));
            System.out.println("GEO_CD_SR:" + String.valueOf(taxCalcPMsg.geoCd_SR.getValue()));
            System.out.println("DS_INSD_CTY_LIMIT_FLG_SR:" + String.valueOf(taxCalcPMsg.dsInsdCtyLimitFlg_SR.getValue()));
            System.out.println("FIRST_LINE_ADDR_SR:" + String.valueOf(taxCalcPMsg.firstLineAddr_SR.getValue()));
            System.out.println("SCD_LINE_ADDR_SR:" + String.valueOf(taxCalcPMsg.scdLineAddr_SR.getValue()));
            System.out.println("CTY_ADDR_SR:" + String.valueOf(taxCalcPMsg.ctyAddr_SR.getValue()));
            System.out.println("ST_CD_SR:" + String.valueOf(taxCalcPMsg.stCd_SR.getValue()));
            System.out.println("CNTY_NM_SR:" + String.valueOf(taxCalcPMsg.cntyNm_SR.getValue()));
            System.out.println("POST_CD_SR:" + String.valueOf(taxCalcPMsg.postCd_SR.getValue()));
            System.out.println("CTRY_CD_SR:" + String.valueOf(taxCalcPMsg.ctryCd_SR.getValue()));
            System.out.println("GEO_CD_SF:" + String.valueOf(taxCalcPMsg.geoCd_SF.getValue()));
            System.out.println("WH_CD_SF:" + String.valueOf(taxCalcPMsg.whCd_SF.getValue()));
            System.out.println("FIRST_LINE_ADDR_SF:" + String.valueOf(taxCalcPMsg.firstLineAddr_SF.getValue()));
            System.out.println("SCD_LINE_ADDR_SF:" + String.valueOf(taxCalcPMsg.scdLineAddr_SF.getValue()));
            System.out.println("CTY_ADDR_SF:" + String.valueOf(taxCalcPMsg.ctyAddr_SF.getValue()));
            System.out.println("ST_CD_SF:" + String.valueOf(taxCalcPMsg.stCd_SF.getValue()));
            System.out.println("CNTY_NM_SF:" + String.valueOf(taxCalcPMsg.cntyNm_SF.getValue()));
            System.out.println("POST_CD_SF:" + String.valueOf(taxCalcPMsg.postCd_SF.getValue()));
            System.out.println("CTRY_CD_SF:" + String.valueOf(taxCalcPMsg.ctryCd_SF.getValue()));
            System.out.println("INV_TP_CD:" + String.valueOf(taxCalcPMsg.invTpCd.getValue()));
            System.out.println("++++ NFCL3000CommonLogic Tax Calc Header E n d ++++");
            for (int i = 0; i < taxCalcPMsg.taxCalculateInputLine.getValidCount(); i++) {
                System.out.println("++++ NFCL3000CommonLogic Tax Calc Input Line[" + String.valueOf(i) +  "] Start ++++");
                NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg = taxCalcPMsg.taxCalculateInputLine.no(i);
                System.out.println("XX_TAX_CALC_LINE_NUM_A" + String.valueOf(taxCalcInputLinePMsg.xxTaxCalcLineNum_A.getValue()));
                System.out.println("TRX_DT_A" + String.valueOf(taxCalcInputLinePMsg.trxDt_A.getValue()));
                System.out.println("MDSE_CD_A" + String.valueOf(taxCalcInputLinePMsg.mdseCd_A.getValue()));
                System.out.println("SVC_ALLOC_TP_CD_A" + String.valueOf(taxCalcInputLinePMsg.svcAllocTpCd_A.getValue()));
                System.out.println("SVC_ALLOC_TRX_TP_NM_A" + String.valueOf(taxCalcInputLinePMsg.svcAllocTrxTpNm_A.getValue()));
                System.out.println("TAX_EXEM_TP_CD_A" + String.valueOf(taxCalcInputLinePMsg.taxExemTpCd_A.getValue()));
                System.out.println("SHIP_QTY_A" + String.valueOf(taxCalcInputLinePMsg.shipQty_A.getValue()));
                System.out.println("FUNC_NET_UNIT_PRC_AMT_A" + String.valueOf(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A.getValue()));
                System.out.println("SLS_AMT_A" + String.valueOf(taxCalcInputLinePMsg.slsAmt_A.getValue()));
                System.out.println("TAX_AMT_A" + String.valueOf(taxCalcInputLinePMsg.taxAmt_A.getValue()));
                System.out.println("BILL_TO_ACCT_NUM_A" + String.valueOf(taxCalcInputLinePMsg.billToAcctNum_A.getValue()));
                System.out.println("BILL_TO_LOC_NUM_A" + String.valueOf(taxCalcInputLinePMsg.billToLocNum_A.getValue()));
                System.out.println("DS_TAX_GRP_EXEM_CD_AB" + String.valueOf(taxCalcInputLinePMsg.dsTaxGrpExemCd_AB.getValue()));
                System.out.println("DS_TAX_GRP_EXEM_RESL_FLG_AB" + String.valueOf(taxCalcInputLinePMsg.dsTaxGrpExemReslFlg_AB.getValue()));
                System.out.println("DS_ACCT_NUM_AT" + String.valueOf(taxCalcInputLinePMsg.dsAcctNum_AT.getValue()));
                System.out.println("SHIP_TO_LOC_NUM_A" + String.valueOf(taxCalcInputLinePMsg.shipToLocNum_A.getValue()));
                System.out.println("DS_TAX_GRP_EXEM_CD_AT" + String.valueOf(taxCalcInputLinePMsg.dsTaxGrpExemCd_AT.getValue()));
                System.out.println("GEO_CD_AT" + String.valueOf(taxCalcInputLinePMsg.geoCd_AT.getValue()));
                System.out.println("DS_INSD_CTY_LIMIT_FLG_AT" + String.valueOf(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AT.getValue()));
                System.out.println("TAX_AREA_ID_AT" + String.valueOf(taxCalcInputLinePMsg.taxAreaId_AT.getValue()));
                System.out.println("FIRST_LINE_ADDR_AT" + String.valueOf(taxCalcInputLinePMsg.firstLineAddr_AT.getValue()));
                System.out.println("SCD_LINE_ADDR_AT" + String.valueOf(taxCalcInputLinePMsg.scdLineAddr_AT.getValue()));
                System.out.println("CTY_ADDR_AT" + String.valueOf(taxCalcInputLinePMsg.ctyAddr_AT.getValue()));
                System.out.println("ST_CD_AT" + String.valueOf(taxCalcInputLinePMsg.stCd_AT.getValue()));
                System.out.println("CNTY_NM_AT" + String.valueOf(taxCalcInputLinePMsg.cntyNm_AT.getValue()));
                System.out.println("POST_CD_AT" + String.valueOf(taxCalcInputLinePMsg.postCd_AT.getValue()));
                System.out.println("CTRY_CD_AT" + String.valueOf(taxCalcInputLinePMsg.ctryCd_AT.getValue()));
                System.out.println("GEO_CD_AR" + String.valueOf(taxCalcInputLinePMsg.geoCd_AR.getValue()));
                System.out.println("DS_INSD_CTY_LIMIT_FLG_AR" + String.valueOf(taxCalcInputLinePMsg.dsInsdCtyLimitFlg_AR.getValue()));
                System.out.println("FIRST_LINE_ADDR_AR" + String.valueOf(taxCalcInputLinePMsg.firstLineAddr_AR.getValue()));
                System.out.println("SCD_LINE_ADDR_AR" + String.valueOf(taxCalcInputLinePMsg.scdLineAddr_AR.getValue()));
                System.out.println("CTY_ADDR_AR" + String.valueOf(taxCalcInputLinePMsg.ctyAddr_AR.getValue()));
                System.out.println("ST_CD_AR" + String.valueOf(taxCalcInputLinePMsg.stCd_AR.getValue()));
                System.out.println("CNTY_NM_AR" + String.valueOf(taxCalcInputLinePMsg.cntyNm_AR.getValue()));
                System.out.println("POST_CD_AR" + String.valueOf(taxCalcInputLinePMsg.postCd_AR.getValue()));
                System.out.println("CTRY_CD_AR" + String.valueOf(taxCalcInputLinePMsg.ctryCd_AR.getValue()));
                System.out.println("GEO_CD_AF" + String.valueOf(taxCalcInputLinePMsg.geoCd_AF.getValue()));
                System.out.println("WH_CD_AF" + String.valueOf(taxCalcInputLinePMsg.whCd_AF.getValue()));
                System.out.println("FIRST_LINE_ADDR_AF" + String.valueOf(taxCalcInputLinePMsg.firstLineAddr_AF.getValue()));
                System.out.println("SCD_LINE_ADDR_AF" + String.valueOf(taxCalcInputLinePMsg.scdLineAddr_AF.getValue()));
                System.out.println("CTY_ADDR_AF" + String.valueOf(taxCalcInputLinePMsg.ctyAddr_AF.getValue()));
                System.out.println("ST_CD_AF" + String.valueOf(taxCalcInputLinePMsg.stCd_AF.getValue()));
                System.out.println("CNTY_NM_AF" + String.valueOf(taxCalcInputLinePMsg.cntyNm_AF.getValue()));
                System.out.println("POST_CD_AF" + String.valueOf(taxCalcInputLinePMsg.postCd_AF.getValue()));
                System.out.println("CTRY_CD_AF" + String.valueOf(taxCalcInputLinePMsg.ctryCd_AF.getValue()));
                System.out.println("TRX_LINE_NUM" + String.valueOf(taxCalcInputLinePMsg.trxLineNum.getValue()));
                System.out.println("PRC_COND_TP_CD" + String.valueOf(taxCalcInputLinePMsg.prcCondTpCd.getValue()));
                System.out.println("++++ NFCL3000CommonLogic Tax Calc Input Line[" + String.valueOf(i) +  "] E n d ++++");
            }
        }
    }
}
