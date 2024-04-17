/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140.common;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.ADD_LINE_ELIG_ACCT;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.ADD_LINE_ELIG_ORD_CATG;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BIZ_APP_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_1;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_10;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_2;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_3;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_4;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_5;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_6;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_7;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_8;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BTN_CMN_BTN_9;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.DELETE_LINE_ELIG_ACCT;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.DELETE_LINE_ELIG_ORD_CATG;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.FUNCTION_UPDATE;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_0;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_1;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_13;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_15;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_2;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_3;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_4;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_5;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_50;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_6;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_7;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.PERCENT;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.SCRN_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.TABLE_A;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.TABLE_B;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWCL0140.NWCL0140BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NWCL0140CommonLogic {

    /**
     * Set Common Button for Initialized screen
     * @param handler EZDCommonHandler
     */
    public static void setCommonButtonInit(EZDCommonHandler handler) {
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonEnabled(ADD_LINE_ELIG_ACCT, true);
            handler.setButtonEnabled(ADD_LINE_ELIG_ORD_CATG, true);
            handler.setButtonEnabled(DELETE_LINE_ELIG_ACCT, true);
            handler.setButtonEnabled(DELETE_LINE_ELIG_ORD_CATG, true);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonEnabled(ADD_LINE_ELIG_ACCT, false);
            handler.setButtonEnabled(ADD_LINE_ELIG_ORD_CATG, false);
            handler.setButtonEnabled(DELETE_LINE_ELIG_ACCT, false);
            handler.setButtonEnabled(DELETE_LINE_ELIG_ORD_CATG, false);
        }
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

    }

    /**
     * set table attribute
     * @param scrnMsg NWCL0140BMsg
     */
    public static void setTableColumnAttr(NWCL0140BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        tblColor.setAlternateRowsBG(TABLE_B, scrnMsg.B);
        // Invoiced Threshold(%) Digit
        scrnMsg.attrbValNum.setAppFracDigit(2);

        if (isUpdatable()) {
            scrnMsg.attrbValNum.setInputProtected(false);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxLinkAncr_AD.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkAncr_LD.setInputProtected(false);
                scrnMsg.A.no(i).xxChkBox_EL.setInputProtected(false);
                scrnMsg.A.no(i).billToCustAcctCd_EL.setInputProtected(false);
                scrnMsg.A.no(i).billToCustCd_EL.setInputProtected(false);
                scrnMsg.A.no(i).dsAcctNm_EL.setInputProtected(true);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxLinkAncr_OD.setInputProtected(false);
                scrnMsg.B.no(i).xxLinkAncr_RD.setInputProtected(false);
                scrnMsg.B.no(i).xxChkBox_EX.setInputProtected(false);
                scrnMsg.B.no(i).dsOrdCatgDescTxt_EX.setInputProtected(false);
                scrnMsg.B.no(i).dsOrdTpDescTxt_EX.setInputProtected(false);
                scrnMsg.B.no(i).ordCatgInclFlg_EX.setInputProtected(false);
            }
        } else {
            scrnMsg.attrbValNum.setInputProtected(true);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxLinkAncr_AD.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_LD.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_EL.setInputProtected(true);
                scrnMsg.A.no(i).billToCustAcctCd_EL.setInputProtected(true);
                scrnMsg.A.no(i).billToCustCd_EL.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_EL.setInputProtected(true);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).xxLinkAncr_OD.setInputProtected(true);
                scrnMsg.B.no(i).xxLinkAncr_RD.setInputProtected(true);
                scrnMsg.B.no(i).xxChkBox_EX.setInputProtected(true);
                scrnMsg.B.no(i).dsOrdCatgDescTxt_EX.setInputProtected(true);
                scrnMsg.B.no(i).dsOrdTpDescTxt_EX.setInputProtected(true);
                scrnMsg.B.no(i).ordCatgInclFlg_EX.setInputProtected(true);
            }
        }
    }

    /**
     * Check error
     * @param scrnMsg NWCL0140BMsg
     */
    public static void chkErrorInfo(NWCL0140BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.attrbValNum);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxLinkAncr_AD);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxLinkAncr_LD);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_EL);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustAcctCd_EL);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustCd_EL);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNm_EL);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxLinkAncr_OD);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxLinkAncr_RD);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_EX);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOrdCatgDescTxt_EX);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOrdTpDescTxt_EX);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).ordCatgInclFlg_EX);
        }
    }

    /**
     * setParamForNMAL6760PopupH
     * @param scrnMsg NMAL6880BMsg
     * @param billToCustAcctCd EZDBStringItem
     * @param billToCustCd EZDBStringItem
     * @return Object[]
     */
    public static Object[] getParamForNMAL6760Popup(NWCL0140BMsg scrnMsg, EZDBStringItem billToCustAcctCd, EZDBStringItem billToCustCd) {

        Object[] params = new Object[scrnMsg.P.length()];

        scrnMsg.P.clear();

        // Account Number
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_0).xxPopPrm_P, billToCustAcctCd);
        // Location Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_15).xxPopPrm_P, billToCustCd);

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        }

        return params;
    }

    /**
     * getParamNWAL1130ForOrdCatg
     * @param glblCmpyCd String
     * @param scrnMsg NWCL0140BMsg
     * @param dsOrdCatgDescTxt EZDBStringItem
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForOrdCatg(String glblCmpyCd, NWCL0140BMsg scrnMsg, EZDBStringItem dsOrdCatgDescTxt) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Order Category Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOC.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,DOC.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,DOC.DS_ORD_CATG_CD       AS DS_ORD_CATG_CD");
        sb.append("   ,DOC.DS_ORD_CATG_DESC_TXT AS DS_ORD_CATG_DESC_TXT");
        sb.append("   ,DOC.DS_ORD_CATG_SORT_NUM AS DS_ORD_CATG_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_CATG DOC ");
        sb.append("WHERE");
        sb.append("    DOC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Category Name";
        whereArray[IDX_1] = "DS_ORD_CATG_DESC_TXT";

        if (ZYPCommonFunc.hasValue(dsOrdCatgDescTxt)) {
            whereArray[IDX_2] = dsOrdCatgDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }

        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Category Code";
        columnArray0[IDX_1] = "DS_ORD_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Category Name";
        columnArray1[IDX_1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Order Reason
     * @param glblCmpyCd String
     * @param scrnMsg NWCL0140BMsg
     * @param dsOrdTpDescTxt EZDBStringItem
     * @param dsOrdCatgCd EZDBStringItem
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForOrdRsn(String glblCmpyCd, NWCL0140BMsg scrnMsg, EZDBStringItem dsOrdTpDescTxt, EZDBStringItem dsOrdCatgCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Order Reason Search";

        // S21_NA#8544
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOT.GLBL_CMPY_CD       AS GLBL_CMPY_CD");
        sb.append("   ,DOT.EZCANCELFLAG       AS EZCANCELFLAG");
        sb.append("   ,DOT.DS_ORD_TP_CD       AS DS_ORD_TP_CD");
        sb.append("   ,DOT.DS_ORD_TP_DESC_TXT AS DS_ORD_TP_DESC_TXT");
        sb.append("   ,DOT.DS_ORD_TP_SORT_NUM AS DS_ORD_TP_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_TP DOT ");
        sb.append("    ,DS_ORD_TP_PROC_DFN  DOTPD ");
        sb.append("WHERE");
        sb.append("    DOT.GLBL_CMPY_CD       = '").append(glblCmpyCd).append("'");
        sb.append("    AND DOT.DS_ORD_CATG_CD = '").append(dsOrdCatgCd.getValue()).append("'");
        sb.append("    AND DOT.EZCANCELFLAG   = '0'");
        sb.append("    AND DOT.EZCANCELFLAG   = DOTPD.EZCANCELFLAG");
        sb.append("    AND DOT.GLBL_CMPY_CD   = DOTPD.GLBL_CMPY_CD");
        sb.append("    AND DOT.DS_ORD_TP_CD   = DOTPD.DS_ORD_TP_CD");
        sb.append("    AND DOTPD.ACTV_FLG     = 'Y'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Reason Name";
        whereArray[IDX_1] = "DS_ORD_TP_DESC_TXT";
        if (ZYPCommonFunc.hasValue(dsOrdTpDescTxt)) {
            whereArray[IDX_2] = dsOrdTpDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }

        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Reason Code";
        columnArray0[IDX_1] = "DS_ORD_TP_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Reason Name";
        columnArray1[IDX_1] = "DS_ORD_TP_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_ORD_TP_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_UPDATE);
    }

    /**
     * Function List
     * @return List<String> Function List
     */
    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList;
    }
}
