/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2070.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NFBL2070.NFBL2070CMsg;
import business.blap.NFBL2070.NFBL2070SMsg;
import business.blap.NFBL2070.constant.NFBL2070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 * <pre>
 * Compensation Credit I/F Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   CSAI            Miyauchi        Create          N/A
 * 2016/11/02   Fujitsu         S.Fujita        Update          QC#15734
 * 2016/11/22   Hitachi         T.Tsuchida      Update          QC#16041
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * </pre>
 */
public class NFBL2070CommonLogic implements NFBL2070Constant {

    /**
     * set vendor invoice data list
     * @param cMsg
     * @param vndInvList
     */
    public static void setRossErrorData(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg, List<Map<String, Object>> rossList) {
        int iCnt = 0;
        for (Map<String, Object> map : rossList) {
             //Set Grid Data for Display
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).xxChkBox_A1, (String) map.get(DTL_COL_LIST.XX_CHECK_BOX.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvPk_A1, (BigDecimal) map.get(DTL_COL_LIST.RTL_INV_PK.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvLinePk_A1,(BigDecimal) map.get(DTL_COL_LIST.RTL_INV_LINE_PK.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvStsCd_A1, (String) map.get(DTL_COL_LIST.RTL_INV_STS_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).billToCustCd_A1, (String) map.get(DTL_COL_LIST.BILL_TO_CUST_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).sellToCustCd_A1, (String) map.get(DTL_COL_LIST.SELL_TO_CUST_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).serNum_A1, (String) map.get(DTL_COL_LIST.SER_NUM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).mdlNm_A1, (String) map.get(DTL_COL_LIST.MDL_NM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvApvlDt_A1, (String) map.get(DTL_COL_LIST.RTL_INV_APVL_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).bllgPerFromDt_A1, (String) map.get(DTL_COL_LIST.BLLG_PER_FROM_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).bllgPerThruDt_A1, (String) map.get(DTL_COL_LIST.BLLG_PER_THRU_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvLineNum_A1, (String) map.get(DTL_COL_LIST.RTL_INV_LINE_NUM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvChrgTpDescTxt_A1, (String) map.get(DTL_COL_LIST.RTL_INV_CHRG_TP_DESC_TXT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).shipQty_A1, (BigDecimal) map.get(DTL_COL_LIST.SHIP_QTY.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).dealGrsUnitPrcAmt_A1, (BigDecimal) map.get(DTL_COL_LIST.DEAL_GRS_UNIT_PRC_AMT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).slsTaxRate_A1, (BigDecimal) map.get(DTL_COL_LIST.SLS_TAX_RATE.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).bllgBizTpCd_A1, (String) map.get(DTL_COL_LIST.BLLG_BIZ_TP_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlDivCd_A1, (String) map.get(DTL_COL_LIST.RTL_DIV_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlInvNum_A1, (String) map.get(DTL_COL_LIST.RTL_INV_NUM.name()));
             // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
             //ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).rtlSmryInvNum_A1, (String) map.get(DTL_COL_LIST.RTL_SMRY_INV_NUM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).itrlRtlSmryInvNum_A1, (String) map.get(DTL_COL_LIST.ITRL_RTL_SMRY_INV_NUM.name()));
             // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).mdseCd_A1, (String) map.get(DTL_COL_LIST.MDSE_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).svcDlrCd_A1, (String) map.get(DTL_COL_LIST.SVC_DLR_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).instlPostCd_A1, (String) map.get(DTL_COL_LIST.INSTL_POST_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).instlCd_A1, (String) map.get(DTL_COL_LIST.INSTL_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).istlSubLocCd_A1, (String) map.get(DTL_COL_LIST.ISTL_SUB_LOC_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).invLineCratDt_A1, (String) map.get(DTL_COL_LIST.INV_LINE_CRAT_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).invLineModDt_A1, (String) map.get(DTL_COL_LIST.INV_LINE_MOD_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.A.no(iCnt).apInvRossStsCd_A1, (String) map.get(DTL_COL_LIST.AP_INV_ROSS_STS_CD.name()));

             //Set Grid Data for BackUp
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).xxChkBox_B1, (String) map.get(DTL_COL_LIST.XX_CHECK_BOX.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvPk_B1, (BigDecimal) map.get(DTL_COL_LIST.RTL_INV_PK.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvLinePk_B1,(BigDecimal) map.get(DTL_COL_LIST. RTL_INV_LINE_PK.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvStsCd_B1, (String) map.get(DTL_COL_LIST.RTL_INV_STS_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).billToCustCd_B1, (String) map.get(DTL_COL_LIST.BILL_TO_CUST_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).sellToCustCd_B1, (String) map.get(DTL_COL_LIST.SELL_TO_CUST_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).serNum_B1, (String) map.get(DTL_COL_LIST.SER_NUM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).mdlNm_B1, (String) map.get(DTL_COL_LIST.MDL_NM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvApvlDt_B1, (String) map.get(DTL_COL_LIST.RTL_INV_APVL_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).bllgPerFromDt_B1, (String) map.get(DTL_COL_LIST.BLLG_PER_FROM_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).bllgPerThruDt_B1, (String) map.get(DTL_COL_LIST.BLLG_PER_THRU_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvLineNum_B1, (String) map.get(DTL_COL_LIST.RTL_INV_LINE_NUM.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvChrgTpDescTxt_B1, (String) map.get(DTL_COL_LIST.RTL_INV_CHRG_TP_DESC_TXT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).shipQty_B1, (BigDecimal) map.get(DTL_COL_LIST.SHIP_QTY.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).dealGrsUnitPrcAmt_B1, (BigDecimal) map.get(DTL_COL_LIST.DEAL_GRS_UNIT_PRC_AMT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).slsTaxRate_B1, (BigDecimal) map.get(DTL_COL_LIST.SLS_TAX_RATE.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).bllgBizTpCd_B1, (String) map.get(DTL_COL_LIST.BLLG_BIZ_TP_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlDivCd_B1, (String) map.get(DTL_COL_LIST.RTL_DIV_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).rtlInvNum_B1, (String) map.get(DTL_COL_LIST.RTL_INV_NUM.name()));
             // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).itrlRtlSmryInvNum_B1, (String) map.get(DTL_COL_LIST.ITRL_RTL_SMRY_INV_NUM.name()));
             // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).mdseCd_B1, (String) map.get(DTL_COL_LIST.MDSE_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).svcDlrCd_B1, (String) map.get(DTL_COL_LIST.SVC_DLR_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).instlPostCd_B1, (String) map.get(DTL_COL_LIST.INSTL_POST_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).instlCd_B1, (String) map.get(DTL_COL_LIST.INSTL_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).istlSubLocCd_B1, (String) map.get(DTL_COL_LIST.ISTL_SUB_LOC_CD.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).invLineCratDt_B1, (String) map.get(DTL_COL_LIST.INV_LINE_CRAT_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).invLineModDt_B1, (String) map.get(DTL_COL_LIST.INV_LINE_MOD_DT.name()));
             ZYPEZDItemValueSetter.setValue(sMsg.B.no(iCnt).apInvRossStsCd_B1, (String) map.get(DTL_COL_LIST.AP_INV_ROSS_STS_CD.name()));

             iCnt ++;
             // START 2016/11/02 S.Fujita [QC#15734,MOD]
//             if (iCnt > MAX_ROW_CNT) {
             if (iCnt >= sMsg.A.length()) { // MOD 2016/11/28 [QC#16158] MAX_ROW_CNT -> sMsg.A.length()
             // END   2016/11/02 S.Fujita [QC#15734,MOD]
                  return;
             }
        }
    }

    /**
     * Copy to CMsg from SMsg
     * @param cMsg
     * @param sMsg
     */
    public static void copyCMsgFromSMsg(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_P1.getValueInt();
        for (int iCnt=0; iCnt<cMsg.A.getValidCount(); iCnt++){
            EZDMsg.copy(sMsg.A.no((pagenationFrom - 1) + iCnt), null, cMsg.A.no(iCnt), null);
        }

        // set value to pagenation items
        //pagenationFrom = pagenationFrom + 1;
        //cMsg.xxPageShowFromNum_P1.setValue( pagenationFrom );
        //cMsg.xxPageShowToNum_P1.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );
    }

    /**
     * Copy to SMsg from CMsg
     * @param cMsg
     * @param sMsg
     */
    public static void copySMsgFromCMsg(NFBL2070CMsg cMsg, NFBL2070SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_P1.getValueInt();
        for (int iCnt=0; iCnt<cMsg.A.getValidCount(); iCnt++){
            EZDMsg.copy(cMsg.A.no(iCnt), null, sMsg.A.no(pagenationFrom - 1 + iCnt), null);
        }

        // set value to pagenation items
        //pagenationFrom = pagenationFrom + 1;
        //cMsg.xxPageShowFromNum_P1.setValue( pagenationFrom );
        //cMsg.xxPageShowToNum_P1.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );
    }
}
