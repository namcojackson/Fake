/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400.common;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_ADD_LINE;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_1;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_10;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_2;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_3;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_4;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_5;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_6;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_7;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_8;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_CMN_BTN_9;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_DELETE_LINE;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_SEARCH;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.BTN_UPLOAD;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.FUNC_EDIT;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.SCRN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2400.NWAL2400BMsg;
import business.servlet.NWAL2400.NWAL2400_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 * 2017/10/04   Fujitsu         H.Sugawara      Update          QC#19922
 *</pre>
 */
public class NWAL2400CommonLogic {

    /**
     * The method explanation: The display control of the screen item. for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1220BMsg
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NWAL2400BMsg scrnMsg) {

        // Upload
        if (!isEntryGranted()) {
            scrnMsg.xxSelRadioBtnObj.setInputProtected(true);
            scrnMsg.xxFileData_UP.setInputProtected(true);
            handler.setButtonEnabled(BTN_UPLOAD, false);
        }

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);

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
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
    }

    /**
     * Control Detail Button
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2400BMsg
     */
    public static void ctrlDetailButton(EZDCommonHandler handler, NWAL2400BMsg scrnMsg) {

        if (!isEntryGranted()) {
            handler.setButtonEnabled(BTN_ADD_LINE, false);
            handler.setButtonEnabled(BTN_DELETE_LINE, false);
            scrnMsg.xxFileData_UP.setInputProtected(true);
            handler.setButtonEnabled(BTN_UPLOAD, false);
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_ADD_LINE, true);
                handler.setButtonEnabled(BTN_DELETE_LINE, true);
            } else {
                handler.setButtonEnabled(BTN_ADD_LINE, true);
                handler.setButtonEnabled(BTN_DELETE_LINE, false);
            }
        }
    }

    /**
     * Table Column Protect.
     * @param scrnMsg NWAL2400BMsg
     */
    public static void setTableScreen(NWAL2400BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!isEntryGranted()) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).coaBrCd_A.setInputProtected(true);
                scrnMsg.A.no(i).coaBrDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).rtlDlrCd_A.setInputProtected(true);
                scrnMsg.A.no(i).rtlDivCd_A.setInputProtected(true);
                scrnMsg.A.no(i).dsOrdCatgDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_AC.setInputProtected(true);
                scrnMsg.A.no(i).dsOrdTpDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_AR.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_AW.setInputProtected(true);
                scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_AB.setInputProtected(true);
                scrnMsg.A.no(i).contrGrpCd_A.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            }
        }
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NWAL2400BMsg scrnMsg) {

        scrnMsg.rtlDlrCd.setNameForMessage("Dealer Code");

        // Set Detail.
        for (int index = 0; index < scrnMsg.A.length(); index++) {
            setDisplayNameForDetailMessage(scrnMsg.A.no(index));
        }
    }

    /**
     * <p>
     * Sets the name for the error message (Detail Lines).
     * </p>
     * @param scrnRow NWAL2400_ABMsg
     */
    public static void setDisplayNameForDetailMessage(NWAL2400_ABMsg scrnRow) {

        scrnRow.xxChkBox_A.setNameForMessage("Ckeck Box");
        scrnRow.coaBrCd_A.setNameForMessage("Branch Code");
        scrnRow.coaBrDescTxt_A.setNameForMessage("Branch Code");
        scrnRow.rtlDlrCd_A.setNameForMessage("Dealer Code");
        scrnRow.rtlDivCd_A.setNameForMessage("Division");
        scrnRow.dsOrdCatgDescTxt_A.setNameForMessage("Order Category");
        scrnRow.dsOrdTpDescTxt_A.setNameForMessage("Reason");
        scrnRow.rtlWhNm_A.setNameForMessage("Warehouse");
        // Mod Start 2017/10/04 QC#19922
        //scrnRow.billToCustCd_A.setNameForMessage("Bill To Location");
        scrnRow.billToCustCd_A.setNameForMessage("Bill To Code");
        // Mod End 2017/10/04 QC#19922
        scrnRow.contrGrpCd_A.setNameForMessage("Contact Group");
        scrnRow.effFromDt_A.setNameForMessage("Start Date");
        scrnRow.effThruDt_A.setNameForMessage("End Dates");
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
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL2400BMsg
     * @param selectNum int
     * @return Param For Order Category
     */
    public static Object[] getParamNWAL1130ForOrderCategory(NWAL2400BMsg scrnMsg, int selectNum) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Order Category Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    DOC.EZCANCELFLAG ");
        sb.append("   ,DOC.GLBL_CMPY_CD ");
        sb.append("   ,DOC.DS_ORD_CATG_CD ");
        sb.append("   ,DOC.DS_ORD_CATG_DESC_TXT ");
        sb.append("   ,DOC.DS_ORD_CATG_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    DS_ORD_CATG DOC ");
        sb.append("WHERE ");
        sb.append("    DOC.DS_ORD_CATG_CD IN ( ");
        sb.append("        SELECT ");
        sb.append("            DS_ORD_CATG_CD ");
        sb.append("        FROM ");
        sb.append("            AVAL_DS_ORD_TP_APP_ID AD ");
        sb.append("           ,DS_ORD_TP             DOT  ");
        sb.append("        WHERE ");
        sb.append("                AD.GLBL_CMPY_CD     = '").append(glblCmpyCd).append("' ");
        sb.append("            AND AD.BIZ_APP_ID       = '").append(BIZ_APP_ID).append("' ");
        sb.append("            AND AD.GLBL_CMPY_CD     = DOT.GLBL_CMPY_CD ");
        sb.append("            AND AD.DS_ORD_TP_CD     = DOT.DS_ORD_TP_CD ");
        sb.append("            AND AD.EZCANCELFLAG     = '0' ");
        sb.append("            AND DOT.EZCANCELFLAG    = '0' ");
        sb.append("        GROUP BY DOT.DS_ORD_CATG_CD ) ");
        sb.append("    AND DOC.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("    AND DOC.EZCANCELFLAG    = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Order Category Name";
        whereArray0[1] = "DS_ORD_CATG_DESC_TXT";
        whereArray0[2] = scrnMsg.A.no(selectNum).dsOrdCatgDescTxt_A.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Category Code";
        columnArray0[1] = "DS_ORD_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Category Name";
        columnArray1[1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * Get Param NWAL1130 For Order Reason
     * @param scrnMsg NWAL2400BMsg
     * @param selectNum int
     * @return Param For Order Category
     */
    public static Object[] getParamNWAL1130ForOrderReason(NWAL2400BMsg scrnMsg, int selectNum) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Order Reason Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    DOT.EZCANCELFLAG ");
        sb.append("   ,DOT.GLBL_CMPY_CD ");
        sb.append("   ,DOT.DS_ORD_TP_CD ");
        sb.append("   ,DOT.DS_ORD_TP_DESC_TXT ");
        sb.append("   ,DOT.DS_ORD_TP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    DS_ORD_TP DOT ");
        sb.append("WHERE ");
        sb.append("        DOT.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("    AND DOT.DS_ORD_CATG_CD  = '").append(scrnMsg.A.no(selectNum).dsOrdCatgCd_A.getValue()).append("' ");
        sb.append("    AND DOT.EZCANCELFLAG    = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Order Reason Name";
        whereArray0[1] = "DS_ORD_TP_DESC_TXT";
        whereArray0[2] = scrnMsg.A.no(selectNum).dsOrdTpDescTxt_A.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Reason Code";
        columnArray0[1] = "DS_ORD_TP_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Reason Name";
        columnArray1[1] = "DS_ORD_TP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_ORD_TP_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * Get Param NWAL1130 For Coa Br
     * @param scrnMsg NWAL2400BMsg
     * @param selectNum int
     * @return Param For Order Category
     */
    public static Object[] getParamNWAL1130ForCoaBr(NWAL2400BMsg scrnMsg, int selectNum) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Branch Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    CB.EZCANCELFLAG ");
        sb.append("   ,CB.GLBL_CMPY_CD ");
        sb.append("   ,CB.COA_BR_CD ");
        sb.append("   ,CB.COA_BR_DESC_TXT  ");
        sb.append("FROM COA_BR CB ");
        sb.append("WHERE CB.EZCANCELFLAG = '0' ");
        sb.append("  AND CB.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Branch Code";
        whereArray0[1] = "COA_BR_CD";
        whereArray0[2] = scrnMsg.A.no(selectNum).coaBrCd_A.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Branch Code";
        columnArray0[1] = "COA_BR_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Branch Name";
        columnArray1[1] = "COA_BR_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "COA_BR_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.R);
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * Get Param NMAL6760 For Bill To Popup
     * @param scrnMsg NWAL2400BMsg
     * @param selectNum int
     * @return Param For Order Category
     */
    public static Object[] getParamNMAL6760ForBillToPopup(NWAL2400BMsg scrnMsg, int selectNum) {

        Object[] params = new Object[scrnMsg.P.length()];

        scrnMsg.P.clear();

        scrnMsg.P.no(8).xxPopPrm_P.setValue("03");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(15).xxPopPrm_P, scrnMsg.A.no(selectNum).billToCustCd_A);

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        }

        return params;
    }

    /**
     * set parameter to call NPAL1010 popup.
     * @param scrnMsg NWAL2400BMsg
     * @param index int
     * @return Object[]
     */
    public static Object[] setParamForWarehousePopup(NWAL2400BMsg scrnMsg, int index) {

        Object[] params = new Object[scrnMsg.W.length()];

        scrnMsg.W.clear();

        // Set Params
        // Data Security Flag
        scrnMsg.W.no(3).xxPopPrm_W.setValue(ZYPConstant.FLG_OFF_N);
        // Virtual Warehouse Flag
        scrnMsg.W.no(4).xxPopPrm_W.setValue(ZYPConstant.FLG_OFF_N);
        // Retail WH Name
        ZYPEZDItemValueSetter.setValue(scrnMsg.W.no(7).xxPopPrm_W, scrnMsg.A.no(index).rtlWhNm_A);
        // Only RTL_WH Flag
        scrnMsg.W.no(10).xxPopPrm_W.setValue(ZYPConstant.FLG_ON_Y);
        // Inventory Owner
        scrnMsg.W.no(12).xxPopPrm_W.setValue("GMD");
        // Inventory Owner Lock Flag
        scrnMsg.W.no(17).xxPopPrm_W.setValue(ZYPConstant.FLG_ON_Y);

        for (int i = 0; i < scrnMsg.W.length(); i++) {
            params[i] = scrnMsg.W.no(i).xxPopPrm_W;
        }
        return params;
    }
}
