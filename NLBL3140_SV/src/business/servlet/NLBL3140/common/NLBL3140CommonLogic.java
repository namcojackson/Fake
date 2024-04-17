/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3140.common;

import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_ADD_NEW_ROW;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_LINE_CANCEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_SELECT_ALL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_UN_SELECT_ALL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.EDT_AUTH;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_0;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_1;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_10;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_11;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_13;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_2;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_3;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_4;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_5;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_50;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_6;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_7;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_8;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.IDX_9;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.LBL_ALLOCATION_TYPE;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.LBL_BU;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.LBL_ORDER_CATGORY;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.LBL_SWH;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.LBL_TIME_FENCE;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.LBL_WH;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.NLAM1286E;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_ORD_CATG_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.OPEN_WIN_SEARCH_ORD_CATG_LINK_EV;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.PERCENT;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.REF_AUTH;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.SCREEN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3140.NLBL3140BMsg;
import business.servlet.NLBL3140.NLBL3140Bean;
import business.servlet.NLBL3140.NLBL3140_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2023/07/10   Hitachi         G.Quan          Update          QC#61543
 * </pre>
 */
public class NLBL3140CommonLogic {

    /**
     * Set Name for Message.
     * @param scrnMsg NLBL3140BMsg
     */
    public static void setNameForMessage(NLBL3140BMsg scrnMsg) {

        scrnMsg.lineBizTpCd.setNameForMessage(LBL_BU);
        scrnMsg.dsOrdCatgDescTxt.setNameForMessage(LBL_ORDER_CATGORY);
        scrnMsg.rtlWhCd.setNameForMessage(LBL_WH);
        scrnMsg.rtlSwhCd.setNameForMessage(LBL_SWH);
        scrnMsg.hardAllocTpCd.setNameForMessage(LBL_ALLOCATION_TYPE);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NLBL3140_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.lineBizTpCd_A.setNameForMessage(LBL_BU);
            // 2017/08/15 QC#20555 MOD BEGIN
//            lineMsg.rtlWhNm_A.setNameForMessage(LBL_WH);
//            lineMsg.rtlSwhNm_A.setNameForMessage(LBL_SWH);
            lineMsg.rtlWhCd_A.setNameForMessage(LBL_WH);
            lineMsg.rtlSwhCd_A.setNameForMessage(LBL_SWH);
            // 2017/08/15 QC#20555 MOD END
            lineMsg.dsOrdCatgDescTxt_A.setNameForMessage(LBL_ORDER_CATGORY);
            lineMsg.hardAllocTpCd_A.setNameForMessage(LBL_ALLOCATION_TYPE);
            lineMsg.tmFenceDaysAot_A.setNameForMessage(LBL_TIME_FENCE);
        }
    }

    /**
     * Initial Common Button
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * Set Control button by Function ID
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3140BMsg
     */
    public static void setAuthority(EZDCommonHandler handler, NLBL3140BMsg scrnMsg) {
        handler.setButtonEnabled(BTN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_LINE_CANCEL, false);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            String funcId = scrnMsg.Z.no(i).xxFuncId_Z.getValue();

            if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(funcId)) {
                handler.setButtonEnabled(BTN_ADD_NEW_ROW, false);
                handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
            } else if (EDT_AUTH.equals(funcId)) {

                break;
            }
        }
    }

    /**
     * Change Table Back Ground Color
     * @param scrnMsg
     */
    public static void changeTableBackGroundColor(NLBL3140BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLBL3140Bean.A, scrnMsg.A);
    }

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NLBL3140BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NLBL3140BMsg scrnMsg, String glbCmpyCd) {

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
        // 2017/08/14 QC#20555 DEL BEGIN
//        sb.append("    DOC.DS_ORD_CATG_CD IN (");
//        sb.append("        SELECT");
//        sb.append("            DOT.DS_ORD_CATG_CD AS DS_ORD_CATG_CD");
//        sb.append("        FROM");
//        sb.append("            AVAL_DS_ORD_TP_APP_ID AD");
//        sb.append("           ,DS_ORD_TP             DOT");
//        sb.append("        WHERE");
//        sb.append("            AD.GLBL_CMPY_CD      = '").append(glbCmpyCd).append("'");
//        sb.append("            AND AD.BIZ_APP_ID    = '").append(BIZ_ID).append("' ");
//        sb.append("            AND AD.EZCANCELFLAG  = '0'");
//        sb.append("            AND AD.GLBL_CMPY_CD  = DOT.GLBL_CMPY_CD");
//        sb.append("            AND AD.DS_ORD_TP_CD  = DOT.DS_ORD_TP_CD");
//        sb.append("            AND DOT.EZCANCELFLAG = '0'");
//        sb.append("        GROUP BY");
//        sb.append("            DOT.DS_ORD_CATG_CD )");
//        sb.append("    AND DOC.GLBL_CMPY_CD = '").append(glbCmpyCd).append("'");
        sb.append("    DOC.GLBL_CMPY_CD = '").append(glbCmpyCd).append("'");
        // 2017/08/14 QC#20555 DEL END
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Category Name";
        whereArray[IDX_1] = "DS_ORD_CATG_DESC_TXT";

        if (OPEN_WIN_SEARCH_ORD_CATG_LINK_EV.equals(scrnMsg.xxScrEventNm.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        } else if (OPEN_WIN_SEARCH_ORD_CATG_EV.equals(scrnMsg.xxScrEventNm.getValue()) &&ZYPCommonFunc.hasValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).dsOrdCatgDescTxt_A)) {
            whereArray[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).dsOrdCatgDescTxt_A.getValue();
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

        ZYPTableUtil.clear(scrnMsg.X);
        params[IDX_6] = scrnMsg.X;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NLBL3140BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NLBL3140BMsg scrnMsg) {

        scrnMsg.Y.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);

        // 2017/08/14 QC#20555 MOD BEGIN
//        if ("OpenWin_SearcWh_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
        if ("OpenWin_SearchWh_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_6).xxPopPrm, scrnMsg.rtlWhCd);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, scrnMsg.rtlWhNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_8).xxPopPrm, scrnMsg.rtlSwhCd);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, scrnMsg.rtlSwhNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, "");
                  } else if ("OpenWin_SearchWh".equals(scrnMsg.xxScrEventNm.getValue()) || "OpenWin_SearchSwh".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_6).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhCd_A);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhNm_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_8).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhCd_A);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhNm_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, "");
        }
        // 2017/08/14 QC#20555 MOD END

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.Y.no(IDX_0).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_1).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_2).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_3).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_4).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_5).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_6).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_7).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_8).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_9).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_10).xxPopPrm;

        return params;
    }

    /**
     * Check Mandatory Item
     * @param scrnMsg NLBL3140BMsg
     */
    public static void checkItem(NLBL3140BMsg scrnMsg) {
        if (!(ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd) || ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt) || ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd) || ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd) || ZYPCommonFunc
                .hasValue(scrnMsg.hardAllocTpCd))) {
            scrnMsg.lineBizTpCd.setErrorInfo(1, NLAM1286E);
            scrnMsg.dsOrdCatgDescTxt.setErrorInfo(1, NLAM1286E);
            scrnMsg.rtlSwhCd.setErrorInfo(1, NLAM1286E);
            scrnMsg.rtlWhCd.setErrorInfo(1, NLAM1286E);
            scrnMsg.hardAllocTpCd.setErrorInfo(1, NLAM1286E);
        }
    }

    /**
     * Set Control Protected Input Form
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3140BMsg
     */
    public static void setProtected(EZDCommonHandler handler, NLBL3140BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).rtlWhNm_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A.setInputProtected(true);
            // START 2023/07/06 G.Quan [QC#61543, ADD]
            scrnMsg.A.no(i).coaProdNm_A.setInputProtected(true);
            // End 2023/07/06 G.Quan [QC#61543, ADD]
        }
    }

    // START 2023/07/10 G.Quan [QC#61543, ADD]
    /**
     * Set Location Popup param
     * @param scrnMsg NLBL3140BMsg
     * @return LocationPopup Param (MAL6050) Object[]
     */
    public static Object[] setNMAL6050(NLBL3140BMsg scrnMsg) {

        scrnMsg.Y.no(0).xxPopPrm.setValue("COA_PROD");
        scrnMsg.Y.no(1).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.Y.no(2).xxPopPrm.setValue("COA_PROD_NM");
        scrnMsg.Y.no(3).xxPopPrm.setValue("COA_PROD_CD");
        scrnMsg.Y.no(4).xxPopPrm.setValue("Product Code Popup");
        scrnMsg.Y.no(5).xxPopPrm.setValue("Product Code");
        scrnMsg.Y.no(6).xxPopPrm.setValue("Product Name");
        scrnMsg.Y.no(7).xxPopPrm.setValue("Product Code");
        scrnMsg.Y.no(8).xxPopPrm.setValue("Product Name");
        if ("OpenWin_COA_Product_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.coaProdCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(9).xxPopPrm, scrnMsg.coaProdCd);
            } else {
                scrnMsg.Y.no(9).xxPopPrm.clear();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.coaProdNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(10).xxPopPrm, scrnMsg.coaProdNm);
            } else {
                scrnMsg.Y.no(10).xxPopPrm.clear();
            }
        } else if ("OpenWin_COA_Product".equals(scrnMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).coaProdCd_A)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(9).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).coaProdCd_A);
            } else {
                scrnMsg.Y.no(9).xxPopPrm.clear();
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).coaProdNm_A)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(10).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).coaProdNm_A);
            } else {
                scrnMsg.Y.no(10).xxPopPrm.clear();
            }
        }
        

        int paramCount = 0;
        Object[] params = new Object[11];
        params[paramCount++] = scrnMsg.Y.no(0).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(1).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(2).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(3).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(4).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(5).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(6).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(7).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(8).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(9).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(10).xxPopPrm;

        return params;
    }
    // End 2023/07/10 G.Quan [QC#61543, ADD]
}
