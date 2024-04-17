/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL1120.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFBL1120.NFBL1120BMsg;
import business.servlet.NFBL1120.constant.NFBL1120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * </pre>
 */
public class NFBL1120CommonLogic implements NFBL1120Constant {

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1120BMsg
     */
    public static void initControl(EZDCommonHandler handler, NFBL1120BMsg scrnMsg) {
        setInputProtected(scrnMsg);
        setButton(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
    }

    /**
     * @param scrnMsg NFBL1120BMsg
     */
    public static void setInputProtected(NFBL1120BMsg scrnMsg) {
        // Header
        scrnMsg.apBatNum.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        scrnMsg.prntVndNm.setInputProtected(true);
        scrnMsg.apBatDt.setInputProtected(false);
        scrnMsg.locNm.setInputProtected(false);
        scrnMsg.apMaintInvStsCd_S.setInputProtected(false);
        // START 2016/09/13 K.Kojima [QC#12725,MOD]
        // scrnMsg.apvrUsrId.setInputProtected(false);
        // scrnMsg.apvrUsrNm.setInputProtected(true);
        scrnMsg.varCharConstNm_S.setInputProtected(false);
        // END 2016/09/13 K.Kojima [QC#12725,MOD]
        scrnMsg.apInvNum.setInputProtected(false);
        scrnMsg.invDt.setInputProtected(false);
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).apBatNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).apBatDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).apVndCd_A1.setInputProtected(true);
            // START 2016/09/13 K.Kojima [QC#12725,ADD]
            scrnMsg.A.no(i).prntVndCd_A1.setInputProtected(true);
            // END 2016/09/13 K.Kojima [QC#12725,ADD]
            scrnMsg.A.no(i).prntVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndSiteNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).apMaintInvStsDescTxt_A1.setInputProtected(true);
            // START 2016/09/09 K.Kojima [QC#12725,MOD]
            // scrnMsg.A.no(i).apvrUsrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).varCharConstVal_A1.setInputProtected(true);
            // END 2016/09/09 K.Kojima [QC#12725,MOD]
            scrnMsg.A.no(i).apInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).invDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).apInvAmt_A1.setInputProtected(true);
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1120BMsg
     */
    public static void setButton(EZDCommonHandler handler, NFBL1120BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH_SUPPLIER_NAME, true); // Supplier Button  
        handler.setButtonEnabled(BTN_NORMAL_SEARCH_APPROVER_NAME, true); // Approver Button  
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, true); // Search Button
        handler.setButtonEnabled(BTN_NORMAL_CREATE_NEW_BATCH_BUTTON, true); // Create New Batch Button

        // Common Button
        // START 2016/09/13 K.Kojima [QC#12725,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK1[0],
        // BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK2[0],
        // BTN_CMN_BLANK2[1], BTN_CMN_BLANK2[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK3[0],
        // BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK4[0],
        // BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK5[0],
        // BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK6[0],
        // BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_BLANK7[0],
        // BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        // END 2016/09/13 K.Kojima [QC#12725,MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        // START 2016/09/13 K.Kojima [QC#12725,MOD]
        // handler.setButtonProperties(BTN_CMN_BLANK9[0],
        // BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        // END 2016/09/13 K.Kojima [QC#12725,MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Method name: clearRowsBG_A
     * <dd>The method explanation: Clear alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_A(EZDBMsg bMsg) {
        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
    }

    /**
     * Method name: setAlternateRowsBG_A
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBG_A(EZDBMsg bMsg) {
        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
    }

    /**
     * Method name: setAlternateRowsBGCommon
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBGCommon(EZDBMsg bMsg) {
        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        clearRowsBG_A(scrnMsg);
        setAlternateRowsBG_A(scrnMsg);
    }

    /**
     * Method name: setButtonConfirmMsg
     * <dd>The method explanation: Set Button Confirm Message.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static void setButtonConfirmMsg(EZDCommonHandler handler) {
        // Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], ZZM8101I, new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], ZZM8101I, new String[] {BTN_CMN_RETURN[2] }, 0);
    }

    /**
     * Get Param NWAL1130 For Vendor Location
     * @param scrnMsg NFBL1120BMsg
     * @return Param NWAL1130 For Warehouse
     */
//    public static Object[] getParamNWAL1130ForVndLocLink(NFBL1120BMsg scrnMsg, String glblCmpyCd) {
//
//        Object[] params = new Object[IDX_7];
//        params[IDX_0] = EMPTY_STRING;
//        params[IDX_1] = "Vendor Location Search";
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ");
//        sb.append("  V.EZCANCELFLAG        AS EZCANCELFLAG ");
//        sb.append(", V.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
//        sb.append(", V.LOC_NUM             AS LOC_NUM ");
//        sb.append(", V.VND_CD              AS VND_CD ");
//        sb.append(", V.LOC_NM              AS LOC_NM ");
//        sb.append("FROM ");
//        sb.append("  VND V ");
//        sb.append("WHERE ");
//        sb.append("    V.EZCANCELFLAG   = '0' ");
//        sb.append("AND V.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");
//
//        params[IDX_2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[IDX_4];
//        whereArray0[IDX_0] = "Location Number";
//        whereArray0[IDX_1] = "UPPER(LOC_NUM)";
//        whereArray0[IDX_2] = scrnMsg.locNum.getValue();
//        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Vendor Number";
//        whereArray1[IDX_1] = "UPPER(VND_CD)";
//        whereArray1[IDX_2] = scrnMsg.apVndCd.getValue();
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        Object[] whereArray2 = new Object[IDX_4];
//        whereArray2[IDX_0] = "Vendor Name";
//        whereArray2[IDX_1] = "UPPER(LOC_NM)";
//        whereArray2[IDX_2] = scrnMsg.locNm.getValue();
//        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray2);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[IDX_4];
//        columnArray0[IDX_0] = "Location Number";
//        columnArray0[IDX_1] = "LOC_NUM";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
//        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Vendor Number";
//        columnArray1[IDX_1] = "VND_CD";
//        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
//        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        Object[] columnArray2 = new Object[IDX_4];
//        columnArray2[IDX_0] = "Vendor Name";
//        columnArray2[IDX_1] = "LOC_NM";
//        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
//        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray2);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[IDX_2];
//        sortConditionArray0[IDX_0] = "LOC_NUM";
//        sortConditionArray0[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "VND_CD";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        Object[] sortConditionArray2 = new Object[IDX_2];
//        sortConditionArray2[IDX_0] = "LOC_NM";
//        sortConditionArray2[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray2);
//
//        params[IDX_5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[IDX_6] = scrnMsg.Z;
//
//        return params;
//    }
    
    /**
     * Get Param NWAL1130 For Merchandise
     * @param scrnMsg NFBL1120BMsg
     * @return Param NWAL1130 For Merchandise
     */
//    public static Object[] getParamNWAL1130ForMdseButton(NFBL1120BMsg scrnMsg, String glblCmpyCd) {
//
//        Object[] params = new Object[IDX_7];
//        params[IDX_0] = EMPTY_STRING;
//        params[IDX_1] = "Merchandise Search";
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ");
//        sb.append("  M.EZCANCELFLAG        AS EZCANCELFLAG ");
//        sb.append(", M.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
//        sb.append(", M.MDSE_CD             AS MDSE_CD ");
//        sb.append(", M.MDSE_NM             AS MDSE_NM ");
//        sb.append("FROM ");
//        sb.append("  MDSE M ");
//        sb.append("WHERE ");
//        sb.append("    M.EZCANCELFLAG   = '0' ");
//        sb.append("AND M.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");
//
//        params[IDX_2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[IDX_4];
//        whereArray0[IDX_0] = "Merchandise Code";
//        whereArray0[IDX_1] = "UPPER(MDSE_CD)";
//        whereArray0[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.getValue();
//        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Merchandise Name";
//        whereArray1[IDX_1] = "UPPER(MDSE_NM)";
//        whereArray1[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseNm_A1.getValue();
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[IDX_4];
//        columnArray0[IDX_0] = "Merchandise Code";
//        columnArray0[IDX_1] = "MDSE_CD";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_16);
//        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Merchandise Name";
//        columnArray1[IDX_1] = "MDSE_NM";
//        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
//        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[IDX_2];
//        sortConditionArray0[IDX_0] = "MDSE_CD";
//        sortConditionArray0[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "MDSE_NM";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        params[IDX_5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[IDX_6] = scrnMsg.Z;
//
//        return params;
//    }

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).apInvAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
        }

    }

    // START 2017/12/22 [QC#22831, ADD]
    /**
     * Get Param NWAL1130 For Supplier
     * @param scrnMsg NFBL1120BMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @return Param NWAL1130 For Supplier
     */
    public static Object[] getParamNWAL1130ForSupplier(NFBL1120BMsg scrnMsg, String glblCmpyCd, String salesDate) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Supplier/Supplier Site Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    V.EZCANCELFLAG,");
        sb.append("    V.GLBL_CMPY_CD,");
        sb.append("    V.VND_CD,");
        sb.append("    V.LOC_NM,");
        sb.append("    V.INAC_DT,");
        sb.append("    PV.PRNT_VND_CD,");
        sb.append("    PV.PRNT_VND_NM,");
        sb.append("    PV.PRNT_VND_TP_CD");
        sb.append(" FROM");
        sb.append("    PRNT_VND PV,");
        sb.append("    VND      V,");
        sb.append("    VND_TP_RELN VTR");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        sb.append("  AND V.RGTN_STS_CD  = '");
        sb.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sb.append("'");
        sb.append("  AND V.EFF_THRU_DT  >= '").append(salesDate).append("'");
        sb.append("  AND V.VND_CD        = VTR.VND_CD");
        sb.append("  AND V.GLBL_CMPY_CD  = VTR.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG  = VTR.EZCANCELFLAG");
        sb.append("  AND VTR.VND_TP_CD   = '");
        sb.append(VND_TP.SUPPLIER);
        sb.append("'");
        sb.append("  AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append("  AND V.PRNT_VND_PK  = PV.PRNT_VND_PK");
        sb.append("  AND (PV.INAC_DT IS NULL OR PV.INAC_DT > '").append(salesDate).append("')");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        // START 2017/12/25 [QC#22831, MOD]
        whereArray1[IDX_0] = "Supplier Number";
        // END   2017/12/25 [QC#22831, MOD]
        whereArray1[IDX_1] = "PRNT_VND_CD";
        whereArray1[IDX_2] = scrnMsg.prntVndCd.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Supplier Name";
        whereArray2[IDX_1] = "PRNT_VND_NM";
        whereArray2[IDX_2] = scrnMsg.prntVndNm.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Supplier Site Code";
        whereArray3[IDX_1] = "VND_CD";
        whereArray3[IDX_2] = null;
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "Supplier Site Name";
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = scrnMsg.locNm.getValue();
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        // START 2017/12/25 [QC#22831, MOD]
        columnArray1[IDX_0] = "Supplier Number";
        // END   2017/12/25 [QC#22831, MOD]
        columnArray1[IDX_1] = "PRNT_VND_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Supplier Name";
        columnArray2[IDX_1] = "PRNT_VND_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Supplier Site Code";
        columnArray3[IDX_1] = "VND_CD";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Supplier Site Name";
        columnArray4[IDX_1] = "LOC_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "VND_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);
        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END 2017/12/22 [QC#22831, ADD]
}
