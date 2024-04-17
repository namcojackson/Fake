/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510.common;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_10_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_10_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_10_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_1_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_1_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_1_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_2_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_2_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_2_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_3_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_3_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_3_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_4_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_4_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_4_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_5_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_5_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_5_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_6_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_6_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_6_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_7_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_7_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_7_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_8_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_8_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_8_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_9_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_9_NAME;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BTN_CMN_BTN_9_VAL;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_SCREEN_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.EVENT_OPEN_WIN_ITEM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.EVENT_OPEN_WIN_SUBITEM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.FUNCTION_UPDATE;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_0;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_1;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_11;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_2;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_20;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_3;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_4;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_5;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_6;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_62;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.IDX_7;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.LOC_ROLE_TP_CDLIST;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1510.NPAL1510BMsg;
import business.servlet.NPAL1510.NPAL1510_QBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2016/02/22   CUSA            K.Ogino         Update          QC#4407
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 * 2018/01/30   CITS            K.Mishiro       Update          QC#22521
 * 2018/03/09   CITS            M.Naito         Update          QC#22590
 * 2019/01/31   CITS            K.Ogino         Update          QC#29963
 * 2019/09/20   CITS            R.Shimamoto     Update          QC#52362
 * 2022/05/19   Hitachi         A.Kohinata      Update          QC#57934
 *</pre>
 */
public class NPAL1510CommonLogic {

    /**
     * @param scrnMsg NPAL1510BMsg
     * @param displayLevel String
     */
    public static void setTableSettings(NPAL1510BMsg scrnMsg, String displayLevel) {
        // Table Attribute clear
        scrnMsg.clearAllGUIAttribute(BUSINESS_SCREEN_ID);

        // Line Color
        S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustLocNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).poLineStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndPoAckLineStsTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndPoAckStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndOtbdCarrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndInvtyLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndShipToCustLocNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndSellToCustLocNm_A1.setInputProtected(true);
            if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).proNum_A1)){
                EZDGUIAttribute trkNumAnchor = new EZDGUIAttribute(BUSINESS_SCREEN_ID,"carrTrk"+i);
                trkNumAnchor.setVisibility(false);
                scrnMsg.addGUIAttribute(trkNumAnchor);
            }

            // set Fraction Digit
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poDispQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poRcvQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).poInvQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).poCancQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).ordQty_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).thisMthFobCostAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).vndChildBomPrcAmt_A1.setAppFracDigit(2);
            // add start 2022/05/19 QC#57934
            scrnMsg.A.no(i).poRcvQty_WO.setAppFracDigit(0);
            scrnMsg.A.no(i).poInvQty_WO.setAppFracDigit(0);
            // add end 2022/05/19 QC#57934
            // START 2018/01/31 K.Mishiro [QC#22521,ADD]
            scrnMsg.A.no(i).prntVndCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).prntVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndNm_A1.setInputProtected(true);
            // END 2018/01/31 K.Mishiro [QC#22521,ADD]

        }
    }

    /**
     * @param scrnMsg NPAL1510BMsg
     */
    public static void setPoDetailFocus(NPAL1510BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.setFocusItem(scrnMsg.A.no(i).poOrdNum_A1);
            break;
        }
    }

    /**
     * clear table
     * @param scrnMsg NPAL1090BMsg
     */
    public static void clearTable(NPAL1510BMsg scrnMsg) {
        // Table Attribute clear
        scrnMsg.clearAllGUIAttribute(BUSINESS_SCREEN_ID);

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * <pre>
     * Initial Common Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_1_ID, BTN_CMN_BTN_1_NAME, BTN_CMN_BTN_1_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3_ID, BTN_CMN_BTN_3_NAME, BTN_CMN_BTN_3_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4_ID, BTN_CMN_BTN_4_NAME, BTN_CMN_BTN_4_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5_ID, BTN_CMN_BTN_5_NAME, BTN_CMN_BTN_5_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6_ID, BTN_CMN_BTN_6_NAME, BTN_CMN_BTN_6_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7_ID, BTN_CMN_BTN_7_NAME, BTN_CMN_BTN_7_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9_ID, BTN_CMN_BTN_9_NAME, BTN_CMN_BTN_9_VAL, 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10_ID, BTN_CMN_BTN_10_NAME, BTN_CMN_BTN_10_VAL, 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_1_ID, BTN_CMN_BTN_1_NAME, BTN_CMN_BTN_1_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3_ID, BTN_CMN_BTN_3_NAME, BTN_CMN_BTN_3_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4_ID, BTN_CMN_BTN_4_NAME, BTN_CMN_BTN_4_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5_ID, BTN_CMN_BTN_5_NAME, BTN_CMN_BTN_5_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6_ID, BTN_CMN_BTN_6_NAME, BTN_CMN_BTN_6_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7_ID, BTN_CMN_BTN_7_NAME, BTN_CMN_BTN_7_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_9_ID, BTN_CMN_BTN_9_NAME, BTN_CMN_BTN_9_VAL, 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10_ID, BTN_CMN_BTN_10_NAME, BTN_CMN_BTN_10_VAL, 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Get Param NWAL1130 For Supplier
     * @param scrnMsg NPAL1510BMsg
     * @return Param NWAL1130 For Supplier
     */
    public static Object[] getParamNWAL1130ForSupplier(NPAL1510BMsg scrnMsg) {

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
        sb.append("    VND V,");
        sb.append("    VND_TP_RELN VTR");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        sb.append("  AND V.RGTN_STS_CD = '");
        sb.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sb.append("'");
        sb.append("  AND V.EFF_THRU_DT >= TO_CHAR (SYSDATE, 'YYYYMMDD')");
        sb.append("  AND V.VND_CD = VTR.VND_CD");
        sb.append("  AND V.GLBL_CMPY_CD = VTR.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = VTR.EZCANCELFLAG");
        sb.append("  AND VTR.VND_TP_CD = '");
        sb.append(VND_TP.SUPPLIER);
        sb.append("'");
        sb.append("  AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append("  AND V.PRNT_VND_PK = PV.PRNT_VND_PK");
        sb.append("  AND (PV.INAC_DT IS NULL OR PV.INAC_DT > TO_CHAR (SYSDATE, 'YYYYMMDD'))");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Supplier Code";
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
        whereArray3[IDX_2] = scrnMsg.vndCd.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "Supplier Site Name";
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = scrnMsg.vndNm.getValue();
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Supplier Code";
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

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * Get Param NWAL1130 For Buyer
     * @param scrnMsg NPAL1510BMsg
     * @return Param NMAL6050 For Buyer
     */
    public static Object[] getParamNWAL1130ForBuyer(NPAL1510BMsg scrnMsg, String glblCmpyCd) {

        // QC#13136 MOD START
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Buyer Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("   AP.USR_NM ");
        sb.append("  ,AP.FIRST_NM || (NVL2(AP.MID_NM, ' '||AP.MID_NM||' ', ' ')) || AP.LAST_NM FULL_PSN_NM ");
        sb.append("  ,AP.EZCANCELFLAG ");
        sb.append("  ,AP.GLBL_CMPY_CD ");
        sb.append("FROM ");
        sb.append("   AUTH_PSN AP ");
        sb.append("WHERE");
        sb.append("    AP.GLBL_CMPY_CD   = " + "'" + glblCmpyCd + "'");
        sb.append(" AND AP.EZCANCELFLAG  = '0'");
        //QC:11661
        sb.append("  UNION");
        sb.append(" SELECT");
        sb.append("   S.PSN_CD AS USR_NM ");
        sb.append("  ,S.FULL_PSN_NM ");
        sb.append("  ,S.EZCANCELFLAG ");
        sb.append("  ,S.GLBL_CMPY_CD ");
        sb.append("FROM ");
        sb.append("   SCE_SYS_USR_LIST S ");
        sb.append("WHERE");
        sb.append("    S.GLBL_CMPY_CD   = " + "'" + glblCmpyCd + "'");
        sb.append(" AND S.EZCANCELFLAG  = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Person Code";
        whereArray1[IDX_1] = "USR_NM";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Person Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm)) {
            whereArray2[IDX_2] = "%" + scrnMsg.fullPsnNm.getValue() + "%";
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Person Code";
        columnArray1[IDX_1] = "USR_NM";
        columnArray1[IDX_2] = new BigDecimal(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Person Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = new BigDecimal(IDX_62);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "USR_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
        // QC#13136 MOD END
    }

        /**
         * Set Param NMAL6800 For Item#
         * @param scrnMsg NPAL1510BMsg
         * @return Param NMAL6800 For Item#
         */
        public static Object[] getParamNMAL6800ForItem(NPAL1510BMsg scrnMsg, String eventName) {

            scrnMsg.xxPopPrm_P0.clear();
            scrnMsg.xxPopPrm_P1.clear();
            scrnMsg.xxPopPrm_P2.clear();
            scrnMsg.xxPopPrm_P3.clear();
            scrnMsg.xxPopPrm_P4.clear();
            scrnMsg.xxPopPrm_P5.clear();
            scrnMsg.xxPopPrm_P6.clear();
            scrnMsg.xxPopPrm_P7.clear();
            scrnMsg.xxPopPrm_P8.clear();
            scrnMsg.xxPopPrm_P9.clear();
            scrnMsg.xxPopPrm_PA.clear();

            // Manager Code is set to subscreen delivery information.
            if(EVENT_OPEN_WIN_ITEM.equals(eventName)){
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, "10");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, eventName);
            } else if(EVENT_OPEN_WIN_SUBITEM.equals(eventName)){
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.mdseCd_SB);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, "10");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, eventName);
            }

            Object[] params = new Object[10];
            params[0] = scrnMsg.xxPopPrm_P0;
            params[1] = scrnMsg.xxPopPrm_P1;
            params[2] = scrnMsg.xxPopPrm_P2;
            params[3] = scrnMsg.xxPopPrm_P3;
            params[4] = scrnMsg.xxPopPrm_P4;
            params[5] = scrnMsg.xxPopPrm_P5;
            params[6] = scrnMsg.xxPopPrm_P6;
            params[7] = scrnMsg.xxPopPrm_P7;
            params[8] = scrnMsg.xxPopPrm_P8;
            params[9] = scrnMsg.xxPopPrm_P9;

            return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1090BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NPAL1510BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();

        // QC#29963 
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, LOC_ROLE_TP_CDLIST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.rtlSwhNm);

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * Get Param NWAL1130 For CUSAEventHist
     * @param scrnMsg NPAL1510BMsg
     * @param rowNum int
     * @param glblCmpyCd String
     * @return Param NWAL1130 For CUSAEventHist
     */
    public static Object[] getParamNWAL1130ForCUSAEventHist(NPAL1510BMsg scrnMsg, int rowNum, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "CUSA Order Event History";
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    B.EZCANCELFLAG");
        sb.append("    , '" + glblCmpyCd + "' AS GLBL_CMPY_CD");
        sb.append("    , B.BIZ_PROC_LOG_PK");
        sb.append("    , B.PRNT_DOC_ID");
        sb.append("    , B.EVENT_ID");
        sb.append("    , TO_CHAR(TO_DATE(SUBSTRB(B.EZINTIME, 1, 12), 'YYYYMMDDHH24MI'), 'MM/DD/YYYY HH24:MI' ) AS EZINTIME");
        sb.append("    , B.DOC_ID");
        sb.append("    , B.EZINUSERID");
        sb.append("    , B.BIZ_PROC_CMNT_TXT_01");
        sb.append(" FROM");
        sb.append("    CSA_BIZ_PROC_LOG_V B");
        sb.append(" WHERE B.EZCANCELFLAG = '0'");
        sb.append("   AND B.PROC_ID = 'OM'");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "CUSA CPO#";
        whereArray1[IDX_1] = "PRNT_DOC_ID";
        whereArray1[IDX_2] = scrnMsg.A.no(rowNum).vndCpoOrdNum_A1.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "CUSA CPO Line#";
        whereArray2[IDX_1] = "DOC_ID";
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(rowNum).vndCpoDtlLineNum_A1) //
                && ZYPCommonFunc.hasValue(scrnMsg.A.no(rowNum).vndCpoDtlLineSubNum_A1)) {
            whereArray2[IDX_2] = //
            scrnMsg.A.no(rowNum).vndCpoDtlLineNum_A1.getValue() + "." //
                    + scrnMsg.A.no(rowNum).vndCpoDtlLineSubNum_A1.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(rowNum).vndCpoDtlLineNum_A1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.A.no(rowNum).vndCpoDtlLineSubNum_A1)) {
            whereArray2[IDX_2] = scrnMsg.A.no(rowNum).vndCpoDtlLineNum_A1.getValue();
        } else {
            whereArray2[IDX_2] = scrnMsg.A.no(rowNum).vndCpoDtlLineSubNum_A1.getValue();
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Event";
        columnArray1[IDX_1] = "EVENT_ID";
        columnArray1[IDX_2] = BigDecimal.valueOf(32);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Date";
        columnArray2[IDX_1] = "EZINTIME";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Line";
        columnArray3[IDX_1] = "DOC_ID";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_7);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "User ID";
        columnArray4[IDX_1] = "EZINUSERID";
        columnArray4[IDX_2] = BigDecimal.valueOf(16);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Memo";
        columnArray5[IDX_1] = "BIZ_PROC_CMNT_TXT_01";
        columnArray5[IDX_2] = BigDecimal.valueOf(100);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        columnList.add(columnArray5);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "EZINTIME";
        sortConditionArray1[IDX_1] = "ASC";
        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "BIZ_PROC_LOG_PK";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);
        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1510BMsg
     * @return ShipToCustPopup Param (NMAL6760) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NPAL1510BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();
        scrnMsg.xxPopPrm_PO.clear();
        scrnMsg.xxPopPrm_PP.clear();

        // QC#22590 mod start
        // QC#4407 Bug Fix Start
        // 2019/09/20 QC#52362 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.shipToCustLocNm);
        String shipToCustLocNm = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustLocNm)) {
        	shipToCustLocNm = scrnMsg.shipToCustLocNm.getValue();
        	if (shipToCustLocNm.length() == 60) {
        		shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
        	}
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, shipToCustLocNm);
        // 2019/09/20 QC#52362 Mod End
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PG, scrnMsg.shipToCustCd);
        // QC#4407 Bug Fix End
        // QC#22590 mod end
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, "03");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PK, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PL, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PM, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PN, "-");

        int paramCount = 0;
        Object[] params = new Object[26];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;
        params[paramCount++] = scrnMsg.xxPopPrm_PB;
        params[paramCount++] = scrnMsg.xxPopPrm_PC;
        params[paramCount++] = scrnMsg.xxPopPrm_PD;
        params[paramCount++] = scrnMsg.xxPopPrm_PE;
        params[paramCount++] = scrnMsg.xxPopPrm_PF;
        params[paramCount++] = scrnMsg.xxPopPrm_PG;
        params[paramCount++] = scrnMsg.xxPopPrm_PH;
        params[paramCount++] = scrnMsg.xxPopPrm_PI;
        params[paramCount++] = scrnMsg.xxPopPrm_PJ;
        params[paramCount++] = scrnMsg.xxPopPrm_PK;
        params[paramCount++] = scrnMsg.xxPopPrm_PL;
        params[paramCount++] = scrnMsg.xxPopPrm_PM;
        params[paramCount++] = scrnMsg.xxPopPrm_PN;
        params[paramCount++] = scrnMsg.xxPopPrm_PO;
        params[paramCount++] = scrnMsg.xxPopPrm_PP;

        return params;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

    // QC#122521 add start
    public static Object[] toArray_popup(NPAL1510_QBMsgArray q, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = q.no(i).xxPopPrm;
        }
        return param;
    }
    // QC#122521 add end

}
