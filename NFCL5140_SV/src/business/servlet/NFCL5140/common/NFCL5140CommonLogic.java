/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 09/29/2010   Fujitsu         I.Kondo         Update          DefID:8207 No.410
 * 11/04/2010   Fujitsu         I.Kondo         Update          DefID:M15
 * 2018/03/28   Fujitsu         H.Ikeda         Update          QC#21738
 * 2018/11/01   Fujitsu         S.Takami        Update          QC#28289
 * 2022/11/14	Hitachi			R.Takau			Update			QC#57252				 
 * </pre>
 */
package business.servlet.NFCL5140.common;

import parts.common.EZDDebugOutput;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL5140.NFCL5140BMsg;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * NFCL5140CommonLogic class.
 */
public class NFCL5140CommonLogic implements NFCL5140Constant {
    /**
     * @param scrnMsg NFCL0650BMsg
     * @return Object[]
     */
    public static Object[] getParams_for_NMAL6050(NFCL5140BMsg scrnMsg) {

        Object[] params = new Object[NMAL6050_PARAM_LENGTH];

        params[NMAL6050_PARAM_TBL_NM] = scrnMsg.xxTblNm;
        params[NMAL6050_PARAM_TBL_CD_COLUMN_CD] = scrnMsg.xxTblCdColNm;
        params[NMAL6050_PARAM_TBL_CD_COLUMN_NM] = scrnMsg.xxTblNmColNm;
        params[NMAL6050_PARAM_TBL_SORT_NUM_COLUMN_NM] = scrnMsg.xxTblSortColNm;
        params[NMAL6050_PARAM_SCR_NM] = scrnMsg.xxScrNm;
        params[NMAL6050_PARAM_HDR_CD_LABEL] = scrnMsg.xxHdrCdLbNm;
        params[NMAL6050_PARAM_HDR_NM_LABEL] = scrnMsg.xxHdrNmLbNm;
        params[NMAL6050_PARAM_DTL_CD_LABEL] = scrnMsg.xxDtlCdLbNm;
        params[NMAL6050_PARAM_DTL_NM_LABEL] = scrnMsg.xxDtlNmLbNm;
        params[NMAL6050_PARAM_CONDITION_CD] = scrnMsg.xxCondCd;
        params[NMAL6050_PARAM_CONDITION_NM] = scrnMsg.xxCondNm;

        EZDDebugOutput.println(DEBUG_MSG_LVL, "*** NFCL5140 is Set Parameter. ***", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[0]  TBL_NM ===================== [" + scrnMsg.xxTblNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[1]  TBL_CD_COLUMN_CD =========== [" + scrnMsg.xxTblCdColNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[2]  TBL_CD_COLUMN_NM =========== [" + scrnMsg.xxTblNmColNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[3]  TBL_SORT_NUM_COLUMN_NM ===== [" + scrnMsg.xxTblSortColNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[4]  SCR_NM ===================== [" + scrnMsg.xxScrNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[5]  HDR_CD_LABEL =============== [" + scrnMsg.xxHdrCdLbNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[6]  HDR_NM_LABEL =============== [" + scrnMsg.xxHdrNmLbNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[7]  DTL_CD_LABEL =============== [" + scrnMsg.xxDtlCdLbNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[8]  DTL_NM_LABEL =============== [" + scrnMsg.xxDtlNmLbNm.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[9]  CONDITION_CD =============== [" + scrnMsg.xxCondCd.getValue() + "]", null);
        EZDDebugOutput.println(DEBUG_MSG_LVL, "[10] CONDITION_NM =============== [" + scrnMsg.xxCondNm.getValue() + "]", null);

        return params;

    }

    /**
     * @param scrnMsg NFCL5140BMsg
     */
    public static void clearHeaderInfo(NFCL5140BMsg scrnMsg) {
        scrnMsg.xxInpAmtNum.clear();
        scrnMsg.arCustRefNum.clear();
        //scrnMsg.tocCd.clear();
        //scrnMsg.coaProdCd.clear();
        scrnMsg.arAdjTpCd_P1.clear();
        scrnMsg.xxInvCmntTxt.clear();
    }

    /**
     * @param scrnMsg NFCL5140BMsg
     */
    public static void clearHeaderInfoAfterInsert(NFCL5140BMsg scrnMsg) {
        scrnMsg.xxInpAmtNum.clear();
        scrnMsg.arCustRefNum.clear();
        scrnMsg.arAdjTpCd_P1.clear();
        scrnMsg.xxInvCmntTxt.clear();

        if (AR_TRX_TP_CD.ADJ.getValue().equals(scrnMsg.xxModeInd.getValue())) {
            //scrnMsg.tocCd.clear();
            //scrnMsg.coaProdCd.clear();
            //scrnMsg.tocCd.setInputProtected(false);
            //scrnMsg.coaProdCd.setInputProtected(false);
        }
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5140BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL5140BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        // START 2018/03/28 H.Ikeda [QC#21738,MOD]
        //scrnMsg.xxInpAmtNum.setInputProtected(true);
        //scrnMsg.arCustRefNum.setInputProtected(true);
        scrnMsg.xxInpAmtNum.setInputProtected(false);
        scrnMsg.arCustRefNum.setInputProtected(false);
        
        //scrnMsg.tocCd.setInputProtected(true);
        //scrnMsg.tocCd_LN.setInputProtected(true);
        //scrnMsg.coaProdCd.setInputProtected(true);
        //scrnMsg.coaProdCd_LN.setInputProtected(true);
        scrnMsg.arAdjTpCd_P1.setInputProtected(true);
        //scrnMsg.xxInvCmntTxt.setInputProtected(true);
        
        // START 2022/11/14 R.Takau [QC#57252,ADD]
        if (!SLC_OTHER.equals(scrnMsg.arAdjTpCd_P1.getValue())) {
        	scrnMsg.xxCmntTxt.setInputProtected(true);
        	scrnAppli.setButtonEnabled(BTN_A, false);
        }
        //END 2022/11/14 R.Takau [QC#57252,ADD]

        scrnAppli.setButtonProperties("Delete_Record", "Delete_Record", "Delete", 0, null);
        //scrnAppli.setButtonProperties("Insert_Record", "Insert_Record", "Insert", 0, null);
        scrnAppli.setButtonProperties("Insert_Record", "Insert_Record", "Insert", 1, null);
        scrnAppli.setButtonProperties("Add_Record", "Add_Record", "Add", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Close", "Close", 1, null);

        scrnMsg.xxModeInd.setValue(AR_TRX_TP_CD.ACC.getValue());
        // END   2018/03/28 H.Ikeda [QC#21738,MOD]
        // START 2018/11/01 S.Takami [QC#28289, Add]
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd_OF.getValue())) {
            scrnMsg.xxModeInd.setValue("3");
            scrnMsg.xxModeInd.setInputProtected(true);
            scrnMsg.arAdjTpCd_P1.setInputProtected(false);
        }
        // END   2018/11/01 S.Takami [QC#28289, Add]
    }

    /**
     * @param scrnMsg NFCL5140BMsg
     */
    public static void protectDetailCmnt(NFCL5140BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).arCustRefNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInvCmntTxt_A1.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg NFCL5140BMsg
     */
    public static void setRowBg(NFCL5140BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController("NFCL5140Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }

    /**
     * @param scrnMsg NFCL5140BMsg
     */
    public static void setAppFracDigit(NFCL5140BMsg scrnMsg) {

        scrnMsg.xxInpAmtNum.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxInpAmtNum_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum_H1.getValueInt());
        }
    }
    
    // START 2022/11/09 R.Takau [QC#57252,ADD]
    /**
     * Get Param NMAL2550 For Charge Account
     * @param scrnMsg NFCL5140BMsg
     * @return Param NFCL5140 For Payment Term
     */
    public static Object[] getParamForChargeAccount(NFCL5140BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        if (!splitCOA9Seg(scrnMsg)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, "NFCL5140");

        Object[] params = new Object[PARAM_INDEX_10];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_I0;
        params[index++] = scrnMsg.xxPopPrm_I1;
        params[index++] = scrnMsg.xxPopPrm_I7;
        params[index++] = scrnMsg.xxPopPrm_I2;
        params[index++] = scrnMsg.xxPopPrm_I3;
        params[index++] = scrnMsg.xxPopPrm_I4;
        params[index++] = scrnMsg.xxPopPrm_I5;
        params[index++] = scrnMsg.xxPopPrm_I6;
        params[index++] = scrnMsg.xxPopPrm_I8;
        params[index++] = scrnMsg.xxPopPrm_I9;
        return params;
    }
    
    /**
     * Clear PopUpParam
     * @param scrnMsg NFCL5140BMsg
     */
    private static void clearPopUpParam(NFCL5140BMsg scrnMsg) {
        scrnMsg.xxPopPrm_I0.clear();
        scrnMsg.xxPopPrm_I1.clear();
        scrnMsg.xxPopPrm_I2.clear();
        scrnMsg.xxPopPrm_I3.clear();
        scrnMsg.xxPopPrm_I4.clear();
        scrnMsg.xxPopPrm_I5.clear();
        scrnMsg.xxPopPrm_I6.clear();
        scrnMsg.xxPopPrm_I7.clear();
        scrnMsg.xxPopPrm_I8.clear();
        scrnMsg.xxPopPrm_I9.clear();
    }
    
    /**
     * splitCOA9Seg
     * @param scrnMsg NFCL5140BMsg
     * @return boolean
     */
    private static boolean splitCOA9Seg(NFCL5140BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt)) {
            // Set Default Value
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, scrnMsg.coaCmpyCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, scrnMsg.coaBrCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, scrnMsg.coaCcCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, scrnMsg.coaAcctCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, scrnMsg.coaProdCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, scrnMsg.coaChCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, scrnMsg.coaAfflCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, scrnMsg.coaProjCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, scrnMsg.coaExtnCd_DF.getValue());
            
            return true;
        }
        String coa[] = scrnMsg.xxCmntTxt.getValue().split("\\.", PARAM_INDEX_9);
        if(coa.length != PARAM_INDEX_9) {
            String errMsg = "9 segments format";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            scrnMsg.xxCmntTxt.setErrorInfo(1, NFBM0041E, new String[]{errMsg});
            return false;
        }

        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, coa[index++]);

        return true;
    }
    //END 2022/11/10 R.Takau [QC#57252,ADD]
}

