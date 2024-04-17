/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7410.common;

import static business.servlet.NMAL7410.constant.NMAL7410Constant.AUTH_EDIT;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_ADD;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_DELETE;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.BTN_SEARCH;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_0;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_1;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_13;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_2;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_20;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_3;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_4;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_40;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_5;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_6;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.IDX_7;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.SCREEN_ID;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.ZZM9000E;
import static business.servlet.NMAL7410.constant.NMAL7410Constant.ZZZM9010E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7410.NMAL7410BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */

public class NMAL7410CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param profile S21UserProfileService
     * @param scrnMsg NMAL7410BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, S21UserProfileService profile, NMAL7410BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        changeActivation(handler, profile, scrnMsg);
        initProtect(scrnMsg);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);

    }

    /**
     * <pre>
     * protect common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void protectCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_ADD, true);
        handler.setButtonEnabled(BTN_DELETE, false);
    }

    /**
     * The initial protect
     * @param scrnMsg NMAL7410BMsg
     */
    public static void initProtect(NMAL7410BMsg scrnMsg) {
        scrnMsg.prcCatgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).csaPrcCatgCd_A.setInputProtected(true);
            scrnMsg.A.no(i).prcCatgNm_A.setInputProtected(true);
        }
    }

    /**
     * Set protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7410BMsg
     */
    public static void setProtect(EZDCommonHandler handler, NMAL7410BMsg scrnMsg) {

        // Delete Button
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_DELETE, false);
        } else {
            handler.setButtonEnabled(BTN_DELETE, true);
        }
        // Add Button
        if (scrnMsg.xxRowNum_A.getValueInt() == scrnMsg.xxPageShowOfNum.getValueInt()) {
            handler.setButtonEnabled(BTN_ADD, false);
        } else {
            handler.setButtonEnabled(BTN_ADD, true);
        }
    }

    /**
     * protect by Auth
     * @param handler EZDCommonHandler
     * @param profile S21UserProfileService
     * @param scrnMsg NMAL7410BMsg
     */
    public static void changeActivation(EZDCommonHandler handler, S21UserProfileService profile, NMAL7410BMsg scrnMsg) {

        String user = profile.getContextUserInfo().getUserId();
        boolean authEdit = profile.isFunctionGranted(user, AUTH_EDIT);
        if (authEdit) {
            initCommonButton(handler);
        } else {
            protectCommonButton(handler);
        }
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NMAL7410BMsg
     */
    public static final void setTblBackColor(NMAL7410BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * date Relation Check
     * @param scrnMsg NMAL7410BMsg
     */
    public static void dateRelateCheck(NMAL7410BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.effFromDt_TO)) {
            if (scrnMsg.effFromDt_FR.getValue().compareTo(scrnMsg.effFromDt_TO.getValue()) > 0) {
                scrnMsg.effFromDt_FR.setErrorInfo(1, ZZZM9010E, //
                        new String[] {scrnMsg.effThruDt_FR.getNameForMessage(), scrnMsg.effThruDt_TO.getNameForMessage() });
                scrnMsg.effFromDt_TO.setErrorInfo(1, ZZZM9010E, //
                        new String[] {scrnMsg.effThruDt_FR.getNameForMessage(), scrnMsg.effThruDt_TO.getNameForMessage() });
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_TO)) {
            if (scrnMsg.effThruDt_FR.getValue().compareTo(scrnMsg.effThruDt_TO.getValue()) > 0) {
                scrnMsg.effThruDt_FR.setErrorInfo(1, ZZZM9010E, //
                        new String[] {scrnMsg.effThruDt_FR.getNameForMessage(), scrnMsg.effThruDt_TO.getNameForMessage() });
                scrnMsg.effThruDt_TO.setErrorInfo(1, ZZZM9010E, //
                        new String[] {scrnMsg.effThruDt_FR.getNameForMessage(), scrnMsg.effThruDt_TO.getNameForMessage() });
            }
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * Input Check
     * @param scrnMsg NMAL7410BMsg
     */
    public static void allAddCheck(NMAL7410BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.crListTxt);
        scrnMsg.addCheckItem(scrnMsg.crListGnrnNum);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_FR);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_TO);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_FR);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_TO);
        scrnMsg.putErrorScreen();
    }

    /**
     * paging Check
     * @param scrnMsg NMAL7410BMsg
     */
    public static void pagingAddCheck(NMAL7410BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).crListTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).crListGnrnNum_A);
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).csaPrcCatgCd_A)) {
                scrnMsg.A.no(i).csaPrcCatgCd_A.setErrorInfo(9, ZZM9000E, new String[] {"CSA Price List" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).csaPrcCatgCd_A);
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A)) {
                if (scrnMsg.A.no(i).effFromDt_A.getValue().compareTo(scrnMsg.A.no(i).effThruDt_A.getValue()) > 0) {
                    scrnMsg.A.no(i).effFromDt_A.setErrorInfo(9, ZZZM9010E, new String[] {"Effective From", "Effective Thru" });
                    scrnMsg.A.no(i).effThruDt_A.setErrorInfo(9, ZZZM9010E, new String[] {"Effective From", "Effective Thru" });
                }
            }
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * Get Param NWAL1130 For CSA Price List
     * @param scrnMsg NMAL7410BMsg
     * @return Param NWAL1130 CSA Price List
     */
    public static Object[] getParamNWAL1130PrcCatg(NMAL7410BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "CSA Price List Search";

        params[IDX_2] = getPrcCatgSql(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_2);
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "CSA Price List Cd";
        whereArray[IDX_1] = "PRC_CATG_CD";
        whereArray[IDX_2] = "";
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "CSA Price List Nm";
        whereArray2[IDX_1] = "PRC_CATG_NM";
        whereArray2[IDX_2] = scrnMsg.prcCatgNm.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_2);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "CSA Price List Cd";
        columnArray0[IDX_1] = "PRC_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "CSA Price List Nm";
        columnArray1[IDX_1] = "PRC_CATG_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_40);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Price List Type";
        columnArray2[IDX_1] = "PRC_LIST_TP_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Price List Structure Type";
        columnArray3[IDX_1] = "PRC_LIST_STRU_TP_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_2);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PRC_CATG_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRC_CATG_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For CSA Price List
     * @param scrnMsg NMAL7410BMsg
     * @return Param NWAL1130 CSA Price List
     */
    public static Object[] getParamNWAL1130ListPrcCatg(NMAL7410BMsg scrnMsg) {

        int cellIdx = scrnMsg.xxCellIdx.getValueInt();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "CSA Price List Search";

        params[IDX_2] = getPrcCatgSql(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_2);
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "CSA Price List Cd";
        whereArray[IDX_1] = "PRC_CATG_CD";
        whereArray[IDX_2] = scrnMsg.A.no(cellIdx).csaPrcCatgCd_A.getValue();
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "CSA Price List Nm";
        whereArray2[IDX_1] = "PRC_CATG_NM";
        whereArray2[IDX_2] = scrnMsg.A.no(cellIdx).prcCatgNm_A.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_2);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "CSA Price List Cd";
        columnArray0[IDX_1] = "PRC_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "CSA Price List Nm";
        columnArray1[IDX_1] = "PRC_CATG_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_40);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Price List Type";
        columnArray2[IDX_1] = "PRC_LIST_TP_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Price List Structure Type";
        columnArray3[IDX_1] = "PRC_LIST_STRU_TP_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_2);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PRC_CATG_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRC_CATG_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Create SQL for NWAL1130 - CSA Price List
     * @param scrnMsg NMAL7410BMsg
     * @return String SQL
     */
    public static String getPrcCatgSql(NMAL7410BMsg scrnMsg) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     PC.GLBL_CMPY_CD        GLBL_CMPY_CD ");
        sb.append("    ,PC.EZCANCELFLAG        EZCANCELFLAG ");
        sb.append("    ,PC.PRC_CATG_CD         PRC_CATG_CD ");
        sb.append("    ,PC.PRC_CATG_NM         PRC_CATG_NM ");
        sb.append("    ,PLT.PRC_LIST_TP_NM     PRC_LIST_TP_NM ");
        sb.append("    ,PLST.PRC_LIST_STRU_TP_NM   PRC_LIST_STRU_TP_NM ");
        sb.append("FROM  ");
        sb.append("     PRC_CATG    PC ");
        sb.append("    ,PRC_CTX_RELN   PCR ");
        sb.append("    ,PRC_LIST_TP    PLT ");
        sb.append("    ,PRC_LIST_STRU_TP   PLST ");
        sb.append("WHERE ");
        sb.append("    PC.GLBL_CMPY_CD     = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND PC.EZCANCELFLAG     = '0' ");
        sb.append("AND PC.GLBL_CMPY_CD     = PCR.GLBL_CMPY_CD ");
        sb.append("AND PC.PRC_LIST_TP_CD   = PCR.PRC_LIST_TP_CD ");
        sb.append("AND PCR.PRC_CTX_TP_CD   = '").append(PRC_CTX_TP.CSMP_CREDIT).append("' ");
        sb.append("AND PCR.EZCANCELFLAG    = '0' ");
        sb.append("AND PC.GLBL_CMPY_CD     = PLT.GLBL_CMPY_CD ");
        sb.append("AND PC.PRC_LIST_TP_CD   = PLT.PRC_LIST_TP_CD ");
        sb.append("AND PLT.EZCANCELFLAG    = '0' ");
        sb.append("AND PLT.GLBL_CMPY_CD    = PLST.GLBL_CMPY_CD ");
        sb.append("AND PLT.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD ");
        sb.append("AND PLST.EZCANCELFLAG   = '0' ");

        return sb.toString();
    }
}
