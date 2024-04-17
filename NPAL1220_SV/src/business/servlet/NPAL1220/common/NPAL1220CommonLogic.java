/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220.common;

import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_ADD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_DELETE_SEARCH;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_SAVE_SEARCH;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_SEARCH;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_SET_ITEM_DESCRIPTION;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_SET_SUPPLIER_NAME;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.BTN_SET_SUPPLIER_SITE_NAME;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DECIMAL_POINT_USD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_ASL_NM;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_ASL_QLFY_REF_CD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_MDSE_NM;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_PRNT_VND_CD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_PRNT_VND_NM;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_SPLY_ITEM_NUM;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_VND_CD;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.DISPLAY_NM_LOC_NM;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.FUNC_EDIT;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.SCRN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1220.NPAL1220BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1220 ASL SearchS
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS       Takeshi Tokutomi     Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1220CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1220BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1220BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);

        // Search Condition
        scrnMsg.aslNm.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        scrnMsg.prntVndNm.setInputProtected(true);
        scrnMsg.splyItemNum.setInputProtected(false);
        scrnMsg.actvFlg.setInputProtected(false);
        scrnMsg.mdseCd.setInputProtected(false);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.aslQlfyTpCd_PD.setInputProtected(false);
        scrnMsg.aslQlfyTpDescTxt_PD.setInputProtected(false);
        scrnMsg.aslQlfyRefCd.setInputProtected(false);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        scrnMsg.vndCd.setInputProtected(false);
        scrnMsg.locNm.setInputProtected(true);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

        // Default value
        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg, ZYPConstant.FLG_ON_Y);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SET_SUPPLIER_NAME, true);
        handler.setButtonEnabled(BTN_SET_ITEM_DESCRIPTION, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_ADD, false);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        handler.setButtonEnabled(BTN_SET_SUPPLIER_SITE_NAME, true);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1220BMsg
     */
    public static void setTableScreen(NPAL1220BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prntVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).splyItemNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).unitPrcAmt_A1.setAppFracDigit(DECIMAL_POINT_USD);
            // START 2018/01/11 S.Katsuma [QC#12226,ADD]
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            // END 2018/01/11 S.Katsuma [QC#12226,ADD]
        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1220BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1220BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_ADD, true);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NPAL1220BMsg scrnMsg) {
        // Search Options
        scrnMsg.srchOptNm_TX.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);

        // Search Condition
        scrnMsg.aslNm.setNameForMessage(DISPLAY_NM_ASL_NM);
        scrnMsg.prntVndCd.setNameForMessage(DISPLAY_NM_PRNT_VND_CD);
        scrnMsg.prntVndNm.setNameForMessage(DISPLAY_NM_PRNT_VND_NM);
        scrnMsg.splyItemNum.setNameForMessage(DISPLAY_NM_SPLY_ITEM_NUM);
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_NM_MDSE_CD);
        scrnMsg.mdseDescShortTxt.setNameForMessage(DISPLAY_NM_MDSE_NM);
        scrnMsg.aslQlfyRefCd.setNameForMessage(DISPLAY_NM_ASL_QLFY_REF_CD);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        scrnMsg.vndCd.setNameForMessage(DISPLAY_NM_VND_CD);
        scrnMsg.locNm.setNameForMessage(DISPLAY_NM_LOC_NM);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

    }

    /**
     * Get Param NWAL1130 For Supplier Information
     * @param scrnMsg NPAL1220BMsg
     * @return Param For Warehouse Information
     */
    public static Object[] getParamNWAL1130ForSupplierInformation(NPAL1220BMsg scrnMsg) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supplier Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT ");
        sb.append("    PV.EZCANCELFLAG ");
        sb.append("   ,PV.GLBL_CMPY_CD ");
        sb.append("   ,PV.PRNT_VND_CD ");
        sb.append("   ,PV.PRNT_VND_NM ");
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        sb.append("   ,PV.VND_CD ");
        sb.append("   ,PV.VND_NM ");
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]
        sb.append("FROM ");
        sb.append("    PO_VND_V PV ");
        sb.append("WHERE ");
        sb.append("        PV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("    AND PV.EZCANCELFLAG = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Supplier Code";
        whereArray0[1] = "PRNT_VND_CD";
        whereArray0[2] = scrnMsg.prntVndCd.getValue();
        whereArray0[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray0);
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Name";
        whereArray1[1] = "PRNT_VND_NM";
        whereArray1[2] = scrnMsg.prntVndNm.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Supplier Site Code";
        whereArray2[1] = "VND_CD";
        whereArray2[2] = scrnMsg.vndCd.getValue();
        whereArray2[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray2);
        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Supplier Site Name";
        whereArray3[1] = "VND_NM";
        whereArray3[2] = scrnMsg.locNm.getValue();
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Supplier Code";
        columnArray0[1] = "PRNT_VND_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Name";
        columnArray1[1] = "PRNT_VND_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Supplier Site Code";
        columnArray2[1] = "VND_CD";
        columnArray2[2] = BigDecimal.valueOf(20);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Supplier Site Name";
        columnArray3[1] = "VND_NM";
        columnArray3[2] = BigDecimal.valueOf(30);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * check Item
     * @param scrnMsg NPAL1220BMsg
     */
    public static void commonAddCheckItem(NPAL1220BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);

        scrnMsg.addCheckItem(scrnMsg.aslNm);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.splyItemNum);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.aslQlfyTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.aslQlfyRefCd);
        // START 2018/01/11 S.Katsuma [QC#12226,ADD]
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        // END 2018/01/11 S.Katsuma [QC#12226,ADD]
    }

    /**
     * clear table
     * @param scrnMsg
     */
    public static void clearTable(NPAL1220BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }
}
