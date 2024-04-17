/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230.common;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CHECK_ALL;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_DELETE_ROW;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_INSERT_ROW;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_ITEM;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_SEARCH;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_SET_ITEM_DESCRIPTION;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_SET_SUPPLIER_NAME;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_SITE;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_UNCHECK;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.BTN_UPLOAD;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DECIMAL_POINT_USD;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_ASL_DESC_TXT;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_ASL_END_DT;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_ASL_NM;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_ASL_QLFY_REF_CD;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_ASL_START_DT;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_MDSE_NM;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_PRNT_VND_CD;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_PRNT_VND_NM;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_SPLY_ITEM_NUM;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_XX_YES_NO_CD;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.FUNC_EDIT;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.MODE_INQUIRY;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.MODE_UPDATE;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.SCRN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1230.NPAL1230BMsg;
import business.servlet.NPAL1230.NPAL1230Bean;
import business.servlet.NPAL1230.NPAL1230_ABMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 * 2022/12/28   Hitachi         T.Kuroda        Update          QC#60947
 * 2023/01/25   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1230CommonLogic {

    /**
     * The method explanation: The display control of the screen item.
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1230BMsg
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1230BMsg scrnMsg) {

        // Header
        if (!isEntryGranted()) {
            scrnMsg.aslNm.setInputProtected(true);
            scrnMsg.aslStartDt.setInputProtected(true);
            scrnMsg.aslEndDt.setInputProtected(true);
            scrnMsg.xxLinkAncr.setInputProtected(true);
            scrnMsg.prntVndCd.setInputProtected(true);
            scrnMsg.aslDescTxt.setInputProtected(true);
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
            scrnMsg.xxLinkAncr_AN.setInputProtected(true);
            // END 2018/01/12 S.Katsuma [QC#12226,ADD]

        }

        scrnMsg.prntVndNm.setInputProtected(true);

        // Header Search
        scrnMsg.mdseDescShortTxt.setInputProtected(true);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        if (!isEntryGranted()) {
            handler.setButtonEnabled(BTN_SET_SUPPLIER_NAME, false);
        } else {
            handler.setButtonEnabled(BTN_SET_SUPPLIER_NAME, true);
        }
        handler.setButtonEnabled(BTN_SET_ITEM_DESCRIPTION, true);

        if (ZYPCommonFunc.hasValue(scrnMsg.aslHdrPk)) {
            handler.setButtonEnabled(BTN_SEARCH, true);
        } else {
            handler.setButtonEnabled(BTN_SEARCH, false);
        }

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        if (!isEntryGranted()) {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.aslHdrPk)) {
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        }

        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Control Detail Button
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1230BMsg
     */
    public static void ctrlDetailButton(EZDCommonHandler handler, NPAL1230BMsg scrnMsg) {

        if (!isEntryGranted()) {
            handler.setButtonEnabled(BTN_CHECK_ALL, false);
            handler.setButtonEnabled(BTN_UNCHECK, false);
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_ITEM, false);
            handler.setButtonEnabled(BTN_SITE, false);
            scrnMsg.xxFileData_UP.setInputProtected(true);
            handler.setButtonEnabled(BTN_UPLOAD, false);
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_CHECK_ALL, true);
                handler.setButtonEnabled(BTN_UNCHECK, true);
                handler.setButtonEnabled(BTN_INSERT_ROW, true);
                handler.setButtonEnabled(BTN_DELETE_ROW, true);
            } else {
                handler.setButtonEnabled(BTN_CHECK_ALL, false);
                handler.setButtonEnabled(BTN_UNCHECK, false);
                handler.setButtonEnabled(BTN_INSERT_ROW, true);
                handler.setButtonEnabled(BTN_DELETE_ROW, false);
            }
        }
    }

    /**
     * Table Column Protect.
     * @param scrnMsg NPAL1230BMsg
     */
    public static void setTableScreen(NPAL1230BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A_Left", scrnMsg.A);
        tblColor.setAlternateRowsBG("A_Right", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2022/12/28 T.Kuroda [QC#60947,MOD]
//            if (!isEntryGranted()) {
//                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
//                scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
//                scrnMsg.A.no(i).primSplyFlg_A.setInputProtected(true);
//                scrnMsg.A.no(i).vndUomCd_A.setInputProtected(true);
//                scrnMsg.A.no(i).splyItemNum_A.setInputProtected(true);
//                scrnMsg.A.no(i).primSplyFlg_A.setInputProtected(true);
//                scrnMsg.A.no(i).vndCd_A.setInputProtected(true);
//                scrnMsg.A.no(i).unitPrcAmt_A.setInputProtected(true);
//                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
//                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
//                scrnMsg.A.no(i).aslItemCmntTxt_A.setInputProtected(true);
//                scrnMsg.A.no(i).vndUomQty_A.setInputProtected(true);
//                scrnMsg.A.no(i).baseOrdQty_A.setInputProtected(true);
//                scrnMsg.A.no(i).vndIncrOrdQty_A.setInputProtected(true);
//                scrnMsg.A.no(i).vndMinOrdQty_A.setInputProtected(true);
//                // QC#21170 2018/04/11 Star
//                scrnMsg.A.no(i).vndLtDaysNum_A.setInputProtected(true);
//                // QC#21170 2018/04/11 Star
//            }

            boolean protectedFlag = (!isEntryGranted()
                || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A) && ZYPDateUtil.isPastDate(scrnMsg.A.no(i).effThruDt_A.getValue())));
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).mdseCd_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).primSplyFlg_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).vndUomCd_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).splyItemNum_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).primSplyFlg_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).vndCd_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).unitPrcAmt_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).aslItemCmntTxt_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).vndUomQty_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).baseOrdQty_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).vndIncrOrdQty_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).vndMinOrdQty_A.setInputProtected(protectedFlag);
            scrnMsg.A.no(i).vndLtDaysNum_A.setInputProtected(protectedFlag);
            // END 2022/12/28 T.Kuroda [QC#60947,MOD]
            // START 2023/01/25 S.Dong [QC#60966, ADD]
            scrnMsg.A.no(i).vndShipLtDaysNum_A.setInputProtected(protectedFlag);
            // END 2023/01/25 S.Dong [QC#60966, ADD]
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxComnScrFirstValTxt_A.setInputProtected(true);
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
            scrnMsg.A.no(i).locNm_A.setInputProtected(true);
            // END 2018/01/12 S.Katsuma [QC#12226,ADD]

            // Set Application Fraction Digit
            scrnMsg.A.no(i).unitPrcAmt_A.setAppFracDigit(DECIMAL_POINT_USD);
        }

        setCursorRuleForSwhDetail(scrnMsg);
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NPAL1230BMsg scrnMsg) {

        // Search Condition
        scrnMsg.aslNm.setNameForMessage(DISPLAY_NM_ASL_NM);
        scrnMsg.aslStartDt.setNameForMessage(DISPLAY_NM_ASL_START_DT);
        scrnMsg.aslEndDt.setNameForMessage(DISPLAY_NM_ASL_END_DT);
        scrnMsg.prntVndCd.setNameForMessage(DISPLAY_NM_PRNT_VND_CD);
        scrnMsg.prntVndNm.setNameForMessage(DISPLAY_NM_PRNT_VND_NM);
        scrnMsg.aslDescTxt.setNameForMessage(DISPLAY_NM_ASL_DESC_TXT);
        scrnMsg.splyItemNum.setNameForMessage(DISPLAY_NM_SPLY_ITEM_NUM);
        scrnMsg.mdseCd.setNameForMessage(DISPLAY_NM_MDSE_CD);
        scrnMsg.mdseDescShortTxt.setNameForMessage(DISPLAY_NM_MDSE_NM);
        scrnMsg.aslQlfyRefCd.setNameForMessage(DISPLAY_NM_ASL_QLFY_REF_CD);
        scrnMsg.xxYesNoCd.setNameForMessage(DISPLAY_NM_XX_YES_NO_CD);

        // Set Detail.
        for (int index = 0; index < scrnMsg.A.length(); index++) {
            setDisplayNameForDetailMessage(scrnMsg.A.no(index));
        }
    }

    /**
     * <p>
     * Sets the name for the error message (Detail Lines).
     * </p>
     * @param scrnRow NPAL1230_ABMsg
     */
    public static void setDisplayNameForDetailMessage(NPAL1230_ABMsg scrnRow) {

        scrnRow.mdseCd_A.setNameForMessage("Item Number");
        scrnRow.primSplyFlg_A.setNameForMessage("Primary Supplier Flag");
        scrnRow.vndUomCd_A.setNameForMessage("UOM");
        scrnRow.vndCd_A.setNameForMessage("Primary Supplier Site");
        scrnRow.unitPrcAmt_A.setNameForMessage("PO Price");
        scrnRow.effFromDt_A.setNameForMessage("Effective Date From");
        scrnRow.effThruDt_A.setNameForMessage("Effective Date To");
        scrnRow.vndUomQty_A.setNameForMessage("Vnd UOM Qty");
        scrnRow.baseOrdQty_A.setNameForMessage("Base Qty");
        scrnRow.vndIncrOrdQty_A.setNameForMessage("Incr Ord Qty");
        scrnRow.vndMinOrdQty_A.setNameForMessage("Min Ord Qty");
        // QC#21170 2018/04/11 Star
        scrnRow.vndLtDaysNum_A.setNameForMessage("Lead Time");
        // QC#21170 2018/04/11 Star
        // START 2023/01/25 S.Dong [QC#60966, ADD]
        scrnRow.vndLtDaysNum_A.setNameForMessage("Vendor Ship Lead Time");
        // END 2023/01/25 S.Dong [QC#60966, ADD]
    }

    /***
     * <p>
     * Checks if entry is granted.
     * </p>
     * @return If entry is granted, return true.
     */
    public static final boolean isEntryGranted() {
        S21UserProfileService service = S21UserProfileServiceFactory.getInstance().getService();
        return service.isFunctionGranted(service.getContextUserInfo().getUserId(), FUNC_EDIT);
    }

    /**
     * Get Param NWAL1130 For Supplier Information
     * @param scrnMsg NPAL1230BMsg
     * @param selectNum selected line number
     * @return Param For Warehouse Information
     */
    public static Object[] getParamNWAL1130ForSupplierInformation(NPAL1230BMsg scrnMsg, int selectNum) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supplier Search";
        params[2] = buildSQLForSupplierInformation(glblCmpyCd);
        List<Object[]> whereList = new ArrayList<Object[]>();
        params[3] = setWhereList(scrnMsg, selectNum, whereList);
        List<Object[]> columnList = new ArrayList<Object[]>();
        params[4] = setColumnList(selectNum, columnList);
        List<Object[]> sortCondList = new ArrayList<Object[]>();
        params[5] = setSortCondList(sortCondList);

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * Get Param NPAL1240 For Qualifier Information.
     * @param scrnMsg NPAL1230BMsg
     * @return Param For Warehouse Information
     */
    public static Object[] getParamNPAL1240(NPAL1230BMsg scrnMsg) {

        Object[] params = new Object[3];

        if (!isEntryGranted()) {
            params[0] = MODE_INQUIRY;
        } else {
            params[0] = MODE_UPDATE;
        }
        params[1] = scrnMsg.glblCmpyCd.getValue();
        params[2] = scrnMsg.Q;

        return params;
    }

    /**
     * setCursorRuleForSwhDetail
     * @param scrnMsg NPAL1230BMsg
     */
    public static void setCursorRuleForSwhDetail(NPAL1230BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule("NPAL1230Scrn00", NPAL1230Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        ZYPGUIFocusRule fRule;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            fRule = new ZYPGUIFocusRule("splyItemNum_A" + "#" + i);
            fRule.setNextId(NPAL1230Bean.vndCd_A + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule("vndCd_A" + "#" + i);
            fRule.setPrevId(NPAL1230Bean.splyItemNum_A + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule("xxChkBox_A" + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NPAL1230Bean.vndMinOrdQty_A + "#" + (i - 1));
            }
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule("vndMinOrdQty_A" + "#" + i);
            if ((i + 1) != scrnMsg.A.length()) {
                fRule.setNextId(NPAL1230Bean.xxChkBox_A + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);

        }
    }

    // START 2018/01/12 S.Katsuma [QC#12226,ADD]
    /**
     * Get Param NWAL1130 For Supplier Site
     * @param scrnMsg NPAL1230BMsg
     * @param selectNum selected line number
     * @return Param For Warehouse Information
     */
    public static Object[] getParamNWAL1130ForASLName(NPAL1230BMsg scrnMsg, int selectNum) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "ASL Name Search";
        params[2] = buildSQLForASLName(glblCmpyCd);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "ASL Name";
        whereArray0[1] = "ASL_NM";
        whereArray0[2] = scrnMsg.aslNm.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        params[3] = setWhereList(scrnMsg, selectNum, whereList);

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "ASL Name";
        columnArray0[1] = "ASL_NM";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);
        params[4] = setColumnList(selectNum, columnList);

        List<Object[]> sortCondList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "ASL_NM";
        sortConditionArray0[1] = "ASC";
        sortCondList.add(sortConditionArray0);
        params[5] = setSortCondList(sortCondList);

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * buildSQLForSupplierInformation
     * @param glblCmpyCd
     */
    public static String buildSQLForSupplierInformation(String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    PV.EZCANCELFLAG ");
        sb.append("   ,PV.GLBL_CMPY_CD ");
        sb.append("   ,PV.PRNT_VND_CD ");
        sb.append("   ,PV.PRNT_VND_NM ");
        sb.append("   ,PV.VND_CD ");
        sb.append("   ,PV.VND_NM ");
        sb.append("FROM ");
        sb.append("    PO_VND_V PV ");
        sb.append("WHERE ");
        sb.append("        PV.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");
        sb.append("    AND PV.EZCANCELFLAG  = '0' ");

        return sb.toString();
    }

    /**
     * buildSQLForASLName
     * @param glblCmpyCd
     */
    public static String buildSQLForASLName(String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    DISTINCT AH.ASL_NM ");
        sb.append("   ,PV.EZCANCELFLAG ");
        sb.append("   ,PV.GLBL_CMPY_CD ");
        sb.append("   ,PV.PRNT_VND_CD ");
        sb.append("   ,PV.PRNT_VND_NM ");
        sb.append("   ,PV.VND_CD ");
        sb.append("   ,PV.VND_NM ");
        sb.append("FROM ");
        sb.append("    PO_VND_V PV ");
        sb.append("   ,ASL_HDR  AH ");
        sb.append("   ,ASL_DTL  AD ");
        sb.append("WHERE ");
        sb.append("        AH.GLBL_CMPY_CD  = '").append(glblCmpyCd).append("' ");
        sb.append("    AND AH.EZCANCELFLAG  = '0' ");
        sb.append("    AND AH.GLBL_CMPY_CD  = AD.GLBL_CMPY_CD ");
        sb.append("    AND AH.ASL_HDR_PK    = AD.ASL_HDR_PK ");
        sb.append("    AND AD.EZCANCELFLAG  = '0' ");
        sb.append("    AND AD.GLBL_CMPY_CD  = PV.GLBL_CMPY_CD ");
        sb.append("    AND AH.PRNT_VND_CD   = PV.PRNT_VND_CD ");
        sb.append("    AND AD.VND_CD        = PV.VND_CD ");
        sb.append("    AND PV.EZCANCELFLAG  = '0' ");

        return sb.toString();
    }

    /**
     * setWhereList
     * @param NPAL1230BMsg scrnMsg
     * @param selectNum
     */
    public static List<Object[]> setWhereList(NPAL1230BMsg scrnMsg, int selectNum, List<Object[]> whereList) {
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

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Supplier Site Code";
        if (selectNum >= 0) {
            whereArray2[1] = "VND_CD";
            whereArray2[2] = scrnMsg.A.no(selectNum).vndCd_A.getValue();
        } else {
            whereArray2[1] = null;
            whereArray2[2] = null;
        }
        whereArray2[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Supplier Site Name";
        if (selectNum >= 0) {
            whereArray3[1] = "VND_NM";
            whereArray3[2] = null;
        } else {
            whereArray3[1] = null;
            whereArray3[2] = null;
        }
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        return whereList;
    }

    /**
     * setColumnList
     * @param NPAL1230BMsg scrnMsg
     * @param selectNum
     */
    public static List<Object[]> setColumnList(int selectNum, List<Object[]> columnList) {
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Supplier Code";
        columnArray0[1] = "PRNT_VND_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        if (columnList.size() > 0) {
            columnArray0[3] = ZYPConstant.FLG_OFF_N;
        } else {
            columnArray0[3] = ZYPConstant.FLG_ON_Y;
        }

        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Name";
        columnArray1[1] = "PRNT_VND_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Supplier Site Code";
        columnArray2[1] = "VND_CD";
        columnArray2[2] = BigDecimal.valueOf(20);

        if (selectNum >= 0) {
            columnArray2[3] = ZYPConstant.FLG_ON_Y;
        } else {
            columnArray2[3] = ZYPConstant.FLG_OFF_N;
        }

        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Supplier Site Name";
        columnArray3[1] = "VND_NM";
        columnArray3[2] = BigDecimal.valueOf(30);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        return columnList;
    }

    /**
     * setSortCondList
     */
    public static List<Object[]> setSortCondList(List<Object[]> sortConditionList) {
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        return sortConditionList;
    }
    // END 2018/01/12 S.Katsuma [QC#12226,ADD]
}
