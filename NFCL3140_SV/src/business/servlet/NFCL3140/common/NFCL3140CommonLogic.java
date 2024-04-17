/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3140.common;

import static business.servlet.NFCL3140.constant.NFCL3140Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.servlet.NFCL3140.NFCL3140BMsg;
import business.servlet.NFCL3140.NFCL3140Bean;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#12142
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13200
 *</pre>
 */
public class NFCL3140CommonLogic {

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFCL3140BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NFCL3140BMsg scrnMsg) {
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        if (!funcIdList.contains(UPD_FUNC)) {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }

        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        scrnMsg.dsInvTpNm_AC.setInputProtected(true);
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        // START 2016/08/09 K.Kojima [QC#13200,ADD]
        scrnMsg.extCurSqNum.setInputProtected(true);
        // END 2016/08/09 K.Kojima [QC#13200,ADD]
    }

    /**
     * setCommonPopUpParamForInvoiceType
     * @param scrnMsg NFCL3140BMsg
     * @param eventName String
     * @return Object[]
     */
    public static Object[] setCommonPopUpParamForInvoiceType(NFCL3140BMsg scrnMsg, String eventName) {
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        return setCommonPopUpParam(scrnMsg, eventName, "Invoice Type Popup", false, scrnMsg.dsInvTpCd.getValue(), scrnMsg.dsInvTpNm.getValue());
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
    }

    /**
     * setCommonPopUpParamForAssociatedCreditType
     * @param scrnMsg NFCL3140BMsg
     * @param eventName String
     * @return Object[]
     */
    public static Object[] setCommonPopUpParamForAssociatedCreditType(NFCL3140BMsg scrnMsg, String eventName) {
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        return setCommonPopUpParam(scrnMsg, eventName, "Associated Credit Type Popup", true, scrnMsg.dsInvTpCd_AC.getValue(), scrnMsg.dsInvTpNm_AC.getValue());
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
    }

    /**
     * setCommonPopUpParam
     * @param scrnMsg NFCL3140BMsg
     * @param eventName String
     * @param windowTitime String
     * @param creditMemoFlag boolean
     * @param dsInvTpCd String
     * @param dsInvTpDescTxt String
     * @return Object[]
     */
    private static Object[] setCommonPopUpParam(NFCL3140BMsg scrnMsg, String eventName, String windowTitime, boolean creditMemoFlag, String dsInvTpCd, String dsInvTpDescTxt) {
        scrnMsg.xxScrEventNm.setValue(eventName);
        scrnMsg.P.clear();

        Object[] params = new Object[7];
        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = windowTitime;
        // Table SQL
        params[i++] = getSelectSQL(scrnMsg, creditMemoFlag);
        // Where List
        params[i++] = getWhereList(scrnMsg, dsInvTpCd, dsInvTpDescTxt);
        // Column List
        params[i++] = getColumnList();
        // Sort Condition List
        params[i++] = getSortConditionList();
        // outPut List
        params[i++] = scrnMsg.P;

        return params;
    }

    /**
     * getSelectSQL
     * @param scrnMsg NFCL3140BMsg
     * @param creditMemoFlag boolean
     * @return String
     */
    private static String getSelectSQL(NFCL3140BMsg scrnMsg, boolean creditMemoFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append("    SELECT");
        sb.append("         DIT.GLBL_CMPY_CD");
        sb.append("        ,'0' AS EZCANCELFLAG");
        sb.append("        ,DIT.DS_INV_TP_SORT_NUM");
        sb.append("        ,DIT.DS_INV_TP_CD");
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        sb.append("        ,DIT.DS_INV_TP_NM");
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        sb.append("        ,CASE WHEN DIT.EZCANCELFLAG = '0' THEN 'Y' ELSE 'N' END AS ACTIVE_FLAG");
        sb.append("    FROM");
        sb.append("        DS_INV_TP DIT");
        sb.append("    WHERE");
        sb.append("            DIT.GLBL_CMPY_CD = '#glblCmpyCd#'");
        if (creditMemoFlag == true) {
            sb.append("        AND DIT.INV_TP_CD = '#invTp#'");
        }
        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", scrnMsg.glblCmpyCd.getValue());
        if (creditMemoFlag == true) {
            sql = sql.replaceAll("#invTp#", INV_TP.CREDIT_MEMO);
        }
        return sql;
    }

    /**
     * getWhereList
     * @param scrnMsg NFCL3140BMsg
     * @param dsInvTpCd String
     * @param dsInvTpDescTxt String
     * @return List<Object[]>
     */
    private static List<Object[]> getWhereList(NFCL3140BMsg scrnMsg, String dsInvTpCd, String dsInvTpDescTxt) {
        List<Object[]> whereList = new ArrayList<Object[]>(2);

        Object[] h0 = new Object[4];
        h0[0] = "Invoice Type Code";
        h0[1] = "DS_INV_TP_CD";
        h0[2] = dsInvTpCd;
        h0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[4];
        h1[0] = "Invoice Type Name";
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        h1[1] = "DS_INV_TP_NM";
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        h1[2] = dsInvTpDescTxt;
        h1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    /**
     * getColumnList
     * @return List<Object[]>
     */
    private static List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>(3);

        Object[] c0 = new Object[4];
        c0[0] = "Invoice Type Code";
        c0[1] = "DS_INV_TP_CD";
        c0[2] = new BigDecimal(15);
        c0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[4];
        c1[0] = "Invoice Type Name";
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        c1[1] = "DS_INV_TP_NM";
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        c1[2] = new BigDecimal(50);
        c1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        Object[] c2 = new Object[4];
        c2[0] = "Active Flag";
        c2[1] = "ACTIVE_FLAG";
        c2[2] = new BigDecimal(15);
        c2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        return columnList;
    }

    /**
     * getSortConditionList
     * @return List<Object[]>
     */
    private static List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>(1);
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_INV_TP_SORT_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NFCL3140BMsg
     */
    public static void commonAddCheckItem(NFCL3140BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsInvTpNm);
        scrnMsg.addCheckItem(scrnMsg.dsInvTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.invTpCd_SV);
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        scrnMsg.addCheckItem(scrnMsg.dsInvTpNm_AC);
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        scrnMsg.addCheckItem(scrnMsg.invSrcTxt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
        scrnMsg.addCheckItem(scrnMsg.postToGlFlg);
        scrnMsg.addCheckItem(scrnMsg.openArFlg);
        scrnMsg.addCheckItem(scrnMsg.taxCalcFlg);
        scrnMsg.addCheckItem(scrnMsg.taxExemFlg);
        scrnMsg.addCheckItem(scrnMsg.invPrintFlg);
        scrnMsg.addCheckItem(scrnMsg.autoInvNumFlg);
        // START 2016/08/09 K.Kojima [QC#13200,ADD]
        scrnMsg.addCheckItem(scrnMsg.autoSeqCd_SV);
        // END 2016/08/09 K.Kojima [QC#13200,ADD]
        scrnMsg.addCheckItem(scrnMsg.arCoaCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaBrCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaCcCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaChCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaProjCd);
        scrnMsg.addCheckItem(scrnMsg.arCoaExtnCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaBrCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaCcCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaChCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaProjCd);
        scrnMsg.addCheckItem(scrnMsg.slsCoaExtnCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaBrCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaCcCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaChCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaProjCd);
        scrnMsg.addCheckItem(scrnMsg.taxCoaExtnCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaBrCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaCcCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaProdCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaChCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaProjCd);
        scrnMsg.addCheckItem(scrnMsg.unEarnCoaExtnCd);
        scrnMsg.A.setCheckParam(new String[] {NFCL3140Bean.invGrpAttrbTxt_SV }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * setupAnchor
     * @param scrnMsg NFCL3140BMsg
     */
    public static void setupAnchor(NFCL3140BMsg scrnMsg) {
        if (INV_TP.CREDIT_MEMO.equals(scrnMsg.invTpCd_SV.getValue())) {
            scrnMsg.xxLinkProt_AC.setInputProtected(true);
            scrnMsg.xxLinkProt_AC.clear();
            // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsInvTpNm_AC, (String) null);
            // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        } else {
            scrnMsg.xxLinkProt_AC.setInputProtected(false);
            scrnMsg.xxLinkProt_AC.setValue(ZYPConstant.FLG_ON_1);
        }
    }
}
