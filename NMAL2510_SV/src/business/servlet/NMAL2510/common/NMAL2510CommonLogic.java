/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2510.NMAL2510BMsg;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Resource Maintenance NMAL2510CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/01   Fujitsu         M.suzuki        Update          CSA-QC#4653
 * 2016/08/26   SRAA            Y.Chen          Update          QC#10364
 * 2016/09/02   SRAA            Y.Chen          Update          QC#6402
 * 2016/09/19/  SRAA            Y.Chen          Update          QC#14595
 * 2016/10/05   Hitachi         Y.Takeno        Update          QC#13431
 * 2016/10/12   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 * 2016/10/12   Fujitsu         C.Yokoi         Update          CSA-QC#14219
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */

public class NMAL2510CommonLogic {

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NMAL2510BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NMAL2510Constant.SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NMAL2510Constant.SCREEN_ID);
    }

    /**
     * <pre>
     * Common protect control
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NMAL2510BMsg
     */
    public static void commonControl(EZDCommonHandler handler, NMAL2510BMsg scrnMsg) {
        initCommonButton(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2510BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2510BMsg scrnMsg) {

        // 2016/03/01 S21_NA#4653 Add Start --------------
        scrnMsg.xxPsnNm_D1.setInputProtected(true);
        // 2016/03/01 S21_NA#4653 Mod End   --------------
        controlScreenDetailFields(handler, scrnMsg);
        controlBusinessAreaFields(scrnMsg);
        // QC#13431
        controlAttachmentButton(handler, scrnMsg);

        // QC#5665
        for(int i=0; i<scrnMsg.T.length(); i++){
            scrnMsg.T.no(i).orgNm_1.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_2.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_3.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_4.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_5.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_6.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_7.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_8.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_9.setInputProtected(true);
            scrnMsg.T.no(i).orgNm_10.setInputProtected(true);
        }
        for(int i=0; i<scrnMsg.B.length(); i++){
            scrnMsg.B.no(i).orgDescTxt_B2.setInputProtected(true);
        }
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2510BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2510BMsg scrnMsg) {
        scrnMsg.xxAllLineAddr_H1.setInputProtected(true);
        // QC#10364
        scrnMsg.hrPsnIntfcLocCd_H1.setInputProtected(true);
    }

    /**
     * addTabTerritoryCheckItem
     * @param scrnMsg NMAL2510BMsg
     */
    public static void addTabTerritoryCheckItem(NMAL2510BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B2);
        }
    }

    /**
     * addTabRevenueCheckItem
     * @param scrnMsg NMAL2510BMsg
     */
    public static void addTabRevenueCheckItem(NMAL2510BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C2);
        }
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_SAVE[0], NMAL2510Constant.BTN_CMN_BTN_SAVE[1], NMAL2510Constant.BTN_CMN_BTN_SAVE[2], 0, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_APPLY[0], NMAL2510Constant.BTN_CMN_BTN_APPLY[1], NMAL2510Constant.BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_APPROVE[0], NMAL2510Constant.BTN_CMN_BTN_APPROVE[1], NMAL2510Constant.BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_REJECT[0], NMAL2510Constant.BTN_CMN_BTN_REJECT[1], NMAL2510Constant.BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_DOWNLOAD[0], NMAL2510Constant.BTN_CMN_BTN_DOWNLOAD[1], NMAL2510Constant.BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_DELETE[0], NMAL2510Constant.BTN_CMN_BTN_DELETE[1], NMAL2510Constant.BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_CLEAR[0], NMAL2510Constant.BTN_CMN_BTN_CLEAR[1], NMAL2510Constant.BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_RESET[0], NMAL2510Constant.BTN_CMN_BTN_RESET[1], NMAL2510Constant.BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_RETURN[0], NMAL2510Constant.BTN_CMN_BTN_RETURN[1], NMAL2510Constant.BTN_CMN_BTN_RETURN[2], 1, null);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceSearchPopup(NMAL2510BMsg scrnMsg) {

        Object[] params = new Object[2];
        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2510Constant.OPEN_WIN_RESOURCE_LOOKUP);

        // Resource#
        if (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.psnNum_H1);
        }

        // Employee ID
        if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.psnCd_H1);
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceSearchPopupBySupervisor(NMAL2510BMsg scrnMsg) {

        Object[] params = new Object[4];
        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2510Constant.OPEN_WIN_RESOURCE_LOOKUP_BY_SUPERVISOR);

        // Supervisor ID
        if (ZYPCommonFunc.hasValue(scrnMsg.hrSupvId_H1)) {
            // 2016/11/10 CSA-QC#14219 Mod Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.hrSupvId_H1);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.hrSupvId_H1);
            // 2016/11/10 CSA-QC#14219 Mod Start
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForOrganizationSearchPopup(NMAL2510BMsg scrnMsg, int i) {

        Object[] params = new Object[7];
        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).bizAreaOrgCd_P2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.A.no(i).bizAreaOrgCd_P2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgNm_A2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.A.no(i).orgNm_A2);
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;
        params[4] = scrnMsg.xxPopPrm_4;
        params[5] = scrnMsg.xxPopPrm_5;
        params[6] = scrnMsg.xxPopPrm_6;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @return Object[]
     */
    public static Object[] setParamForGeoCodeSearchPopup(NMAL2510BMsg scrnMsg) {
        // 2016/10/12 CSA-QC#4096 Add Start
        Object[] params = new Object[5];

        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.ctyAddr_H1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.stCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.stCd_H1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.postCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_2, scrnMsg.postCd_H1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, scrnMsg.cntyNm_H1);
        }

        // City Address
        params[0] = scrnMsg.xxPopPrm_0;
        // State
        params[1] = scrnMsg.xxPopPrm_1;
        // Post
        params[2] = scrnMsg.xxPopPrm_2;
        // County Name
        params[3] = scrnMsg.xxPopPrm_3;
        // Geo Code
        params[4] = scrnMsg.xxPopPrm_4;

        return params;
        // 2016/10/12 CSA-QC#4096 Add End
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForTerritorySearchPopup(NMAL2510BMsg scrnMsg, int i) {

        Object[] params = new Object[8];

        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).bizAreaOrgCd_P3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.B.no(i).bizAreaOrgCd_P3);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).orgNm_B2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.B.no(i).orgNm_B2);
        }

        // Business Area
        params[0] = scrnMsg.xxPopPrm_0;
        // Territory Name
        params[1] = scrnMsg.xxPopPrm_1;
        // Parent Territory Name
        params[2] = scrnMsg.xxPopPrm_2;
        // Resource Name
        params[3] = scrnMsg.xxPopPrm_3;
        // Employee ID
        params[4] = scrnMsg.xxPopPrm_4;
        // Rank
        params[5] = scrnMsg.xxPopPrm_5;
        // Line of Business
        params[6] = scrnMsg.xxPopPrm_6;
        // Organization Name
        params[7] = scrnMsg.xxPopPrm_7;

        return params;
    }

    /**
     * controlBusinessAreaFields
     * @param scrnMsg NMAL2510BMsg
     */
    public static void controlBusinessAreaFields(NMAL2510BMsg scrnMsg) {
        // 2016/03/04 CSA-QC#4654 Add Start
        for (int i = 0; i< scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).actvFlg_BA.getValue())) {
                scrnMsg.A.no(i).bizAreaOrgCd_P2.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).bizAreaOrgCd_P2.setInputProtected(false);
            }
        }
        // 2016/03/04 CSA-QC#4654 Add End
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForGLCommonSearchPopup(NMAL2510BMsg scrnMsg, int i) {

        Object[] params = new Object[10];

        initParam(scrnMsg);

        scrnMsg.xxPopPrm_0.setValue(NMAL2510Constant.BIZ_ID);

        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).coaCmpyCd_C2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.C.no(i).coaCmpyCd_C2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).coaBrCd_C2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, scrnMsg.C.no(i).coaBrCd_C2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).coaCcCd_C2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_4, scrnMsg.C.no(i).coaCcCd_C2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).coaExtnCd_C2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_9, scrnMsg.C.no(i).coaExtnCd_C2);
        }

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;
        params[4] = scrnMsg.xxPopPrm_4;
        params[5] = scrnMsg.xxPopPrm_5;
        params[6] = scrnMsg.xxPopPrm_6;
        params[7] = scrnMsg.xxPopPrm_7;
        params[8] = scrnMsg.xxPopPrm_8;
        params[9] = scrnMsg.xxPopPrm_9;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setParamForContractSearchPopup(NMAL2510BMsg scrnMsg, String glblCmpyCd) {
// QC#14595
//        Object[] params = new Object[20];
//
//        initParam(scrnMsg);
//
//        // Contact Type
//        if (ZYPCommonFunc.hasValue(scrnMsg.ctacTpCd_D1)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.ctacTpCd_D1);
//        }
//
//        params[0] = scrnMsg.xxPopPrm_0;
//        params[1] = scrnMsg.xxPopPrm_1;
//        params[2] = scrnMsg.xxPopPrm_2;
//        params[3] = scrnMsg.xxPopPrm_3;
//        params[4] = scrnMsg.xxPopPrm_4;
//        params[5] = scrnMsg.xxPopPrm_5;
//        params[6] = scrnMsg.xxPopPrm_6;
//        params[7] = scrnMsg.xxPopPrm_7;
//        params[8] = scrnMsg.xxPopPrm_8;
//        params[9] = scrnMsg.xxPopPrm_9;
//        params[10] = scrnMsg.xxPopPrm_10;
//        params[11] = scrnMsg.xxPopPrm_11;
//        params[12] = scrnMsg.xxPopPrm_12;
//        params[13] = scrnMsg.xxPopPrm_13;
//        params[14] = scrnMsg.xxPopPrm_14;
//        params[15] = scrnMsg.dsCtacPntPk_A1;
//        params[16] = scrnMsg.dsCtacPntPk_A2;
//        params[17] = scrnMsg.dsCtacPntPk_A3;
//        params[18] = scrnMsg.P;
//        params[19] = scrnMsg.ctacPsnPk_D1;
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supplier Contact Popup";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT  ");
        sb.append("   CP.GLBL_CMPY_CD ");
        sb.append(" , CP.EZCANCELFLAG ");
        sb.append(" , CP.CTAC_PSN_PK ");
        sb.append(" , PV.PRNT_VND_CD ");
        sb.append(" , PV.PRNT_VND_NM ");
        sb.append(" , VD.VND_CD ");
        sb.append(" , VD.LOC_NM ");
        sb.append(" , CP.CTAC_PSN_FIRST_NM ");
        sb.append(" , CP.CTAC_PSN_LAST_NM ");
        sb.append(" , DCPT.DS_CTAC_PSN_TTL_DESC_TXT ");
        sb.append(" , ( ");
        sb.append("    SELECT DS_CTAC_PNT_VAL_TXT FROM ( ");
        sb.append("      SELECT P.DS_CTAC_PNT_VAL_TXT ");
        sb.append("           , ROW_NUMBER() OVER (ORDER BY P.DS_CTAC_PNT_PK) SEQ ");
        sb.append("        FROM DS_CTAC_PNT P ");
        sb.append("       WHERE P.EZCANCELFLAG = '0' ");
        sb.append("         AND P.CTAC_PSN_PK = CP.CTAC_PSN_PK ");
        sb.append("         AND P.GLBL_CMPY_CD = CP.GLBL_CMPY_CD ");
        sb.append("         AND P.DS_CTAC_PNT_ACTV_FLG = 'Y'  ");
        sb.append("         AND P.DS_CTAC_PNT_TP_CD = '" + DS_CTAC_PNT_TP.PHONE_WORK + "' ");
        sb.append("     ) WHERE SEQ = 1 ");
        sb.append("   ) ");
        sb.append("   TEL_DS_CTAC_PNT_VAL_TXT ");
        sb.append(" , ( ");
        sb.append("    SELECT DS_CTAC_PNT_VAL_TXT FROM ( ");
        sb.append("      SELECT P.DS_CTAC_PNT_VAL_TXT ");
        sb.append("           , ROW_NUMBER() OVER (ORDER BY P.DS_CTAC_PNT_PK) SEQ ");
        sb.append("        FROM DS_CTAC_PNT P ");
        sb.append("       WHERE P.EZCANCELFLAG = '0' ");
        sb.append("         AND P.CTAC_PSN_PK = CP.CTAC_PSN_PK ");
        sb.append("         AND P.GLBL_CMPY_CD = CP.GLBL_CMPY_CD ");
        sb.append("         AND P.DS_CTAC_PNT_ACTV_FLG = 'Y'  ");
        sb.append("         AND P.DS_CTAC_PNT_TP_CD = '" + DS_CTAC_PNT_TP.FAX_WORK + "' ");
        sb.append("     ) WHERE SEQ = 1 ");
        sb.append("   ) ");
        sb.append("   FAX_DS_CTAC_PNT_VAL_TXT ");
        sb.append(" , DCPD.DS_CTAC_PSN_DEPT_DESC_TXT ");
        sb.append(" , DCR.CTAC_TP_CD ");
        // Mod Start 2017/12/06 QC#21897
        //sb.append(" , CT.CTAC_TP_NM ");
        sb.append(" , CT.CTAC_TP_DESC_TXT ");
        // Mod End 2017/12/06 QC#21897
        sb.append(" , ( ");
        sb.append("    SELECT DS_CTAC_PNT_VAL_TXT FROM ( ");
        sb.append("      SELECT P.DS_CTAC_PNT_VAL_TXT ");
        sb.append("           , ROW_NUMBER() OVER (ORDER BY P.DS_CTAC_PNT_PK) SEQ ");
        sb.append("        FROM DS_CTAC_PNT P ");
        sb.append("       WHERE P.EZCANCELFLAG = '0' ");
        sb.append("         AND P.CTAC_PSN_PK = CP.CTAC_PSN_PK ");
        sb.append("         AND P.GLBL_CMPY_CD = CP.GLBL_CMPY_CD ");
        sb.append("         AND P.DS_CTAC_PNT_ACTV_FLG = 'Y'  ");
        sb.append("         AND P.DS_CTAC_PNT_TP_CD = '" + DS_CTAC_PNT_TP.EMAIL_WORK + "' ");
        sb.append("     ) WHERE SEQ = 1 ");
        sb.append("   ) ");
        sb.append("   EML_DS_CTAC_PNT_VAL_TXT ");
        sb.append(" FROM ");
        sb.append("   VND               VD ");
        sb.append(" , PRNT_VND          PV ");
        sb.append(" , DS_CTAC_PSN_RELN  DCR ");
        sb.append(" , CTAC_PSN          CP ");
        sb.append(" , CTAC_TP           CT ");
        sb.append(" , DS_CTAC_PSN_DEPT  DCPD ");
        sb.append(" , DS_CTAC_PSN_TTL   DCPT ");
        sb.append(" WHERE  ");
        sb.append("     VD.EZCANCELFLAG = '0' ");
        sb.append(" AND VD.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("  ");
        sb.append(" AND VD.PRNT_VND_PK = PV.PRNT_VND_PK ");
        sb.append(" AND VD.GLBL_CMPY_CD = PV.GLBL_CMPY_CD ");
        sb.append(" AND PV.EZCANCELFLAG = '0' ");
        sb.append("  ");
        sb.append(" AND VD.LOC_NUM = DCR.LOC_NUM ");
        sb.append(" AND VD.GLBL_CMPY_CD = DCR.GLBL_CMPY_CD ");
        sb.append(" AND DCR.EZCANCELFLAG = '0' ");
        sb.append("  ");
        sb.append(" AND DCR.CTAC_PSN_PK = CP.CTAC_PSN_PK ");
        sb.append(" AND DCR.GLBL_CMPY_CD = CP.GLBL_CMPY_CD ");
        sb.append(" AND CP.EZCANCELFLAG = '0' ");
        sb.append(" AND CP.CTAC_PSN_ACTV_FLG = 'Y' ");
        sb.append("  ");
        sb.append(" AND DCR.CTAC_TP_CD = CT.CTAC_TP_CD ");
        sb.append(" AND DCR.GLBL_CMPY_CD = CT.GLBL_CMPY_CD ");
        sb.append(" AND CT.EZCANCELFLAG = '0' ");
        sb.append("  ");
        sb.append(" AND CP.DS_CTAC_PSN_DEPT_CD = DCPD.DS_CTAC_PSN_DEPT_CD(+) ");
        sb.append(" AND CP.GLBL_CMPY_CD = DCPD.GLBL_CMPY_CD(+) ");
        sb.append(" AND DCPD.EZCANCELFLAG(+) = '0' ");
        sb.append("  ");
        sb.append(" AND CP.DS_CTAC_PSN_TTL_CD = DCPT.DS_CTAC_PSN_TTL_CD(+) ");
        sb.append(" AND CP.GLBL_CMPY_CD = DCPT.GLBL_CMPY_CD(+) ");
        sb.append(" AND DCPT.EZCANCELFLAG(+) = '0' ");
        
        params[2] = sb.toString();

        String ctacPsnPk = "";
        if(ZYPCommonFunc.hasValue(scrnMsg.ctacPsnPk_D1.getValue())){
            ctacPsnPk = scrnMsg.ctacPsnPk_D1.getValue().toPlainString();
        }
        List<Object[]> whereList = new ArrayList<Object[]>();
        whereList.add(new Object[] {"Supplier Code", "PRNT_VND_CD", "", "Y" });
        whereList.add(new Object[] {"Supplier Name", "PRNT_VND_NM", "", "Y" });
        whereList.add(new Object[] {"First Name", "CTAC_PSN_FIRST_NM", "", "Y" });
        whereList.add(new Object[] {"Last Name", "CTAC_PSN_LAST_NM", "", "Y" });
        whereList.add(new Object[] {"Telephone#", "TEL_DS_CTAC_PNT_VAL_TXT", "", "Y" });
        whereList.add(new Object[] {"Email Address", "EML_DS_CTAC_PNT_VAL_TXT", "", "Y" });
        whereList.add(new Object[] {"Contact ID", "CTAC_PSN_PK", ctacPsnPk, "Y" });
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        columnList.add(new Object[] {"Contact ID", "CTAC_PSN_PK", BigDecimal.valueOf(7), "Y" });
        columnList.add(new Object[] {"Supplier Code", "PRNT_VND_CD", BigDecimal.valueOf(8), "N" });
        columnList.add(new Object[] {"Supplier Name", "PRNT_VND_NM", BigDecimal.valueOf(15), "N" });
        columnList.add(new Object[] {"First Name", "CTAC_PSN_FIRST_NM", BigDecimal.valueOf(13), "N" });
        columnList.add(new Object[] {"Last Name", "CTAC_PSN_LAST_NM", BigDecimal.valueOf(13), "N" });
        columnList.add(new Object[] {"Telephone#", "TEL_DS_CTAC_PNT_VAL_TXT", BigDecimal.valueOf(9), "N" });
        columnList.add(new Object[] {"Fax#", "FAX_DS_CTAC_PNT_VAL_TXT", BigDecimal.valueOf(9), "N" });
        columnList.add(new Object[] {"Email Address", "EML_DS_CTAC_PNT_VAL_TXT", BigDecimal.valueOf(25), "N" });
        // Mod Start 2017/12/06 QC#21897
        //columnList.add(new Object[] {"Contact Type", "CTAC_TP_NM", BigDecimal.valueOf(12), "N" });
        columnList.add(new Object[] {"Contact Type", "CTAC_TP_DESC_TXT", BigDecimal.valueOf(12), "N" });
        // Mod End 2017/12/06 QC#21897
        columnList.add(new Object[] {"Department", "DS_CTAC_PSN_DEPT_DESC_TXT", BigDecimal.valueOf(12), "N" });
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        sortConditionList.add(new Object[] {"PRNT_VND_NM", "ASC" });
        sortConditionList.add(new Object[] {"CTAC_PSN_FIRST_NM", "ASC" });
        sortConditionList.add(new Object[] {"CTAC_PSN_LAST_NM", "ASC" });
        params[5] = sortConditionList;

        scrnMsg.L.clear();
        params[6] = scrnMsg.L;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     * @return Object[]
     */
    public static Object[] setParamForLocationSearchPopup(NMAL2510BMsg scrnMsg) {

        initParam(scrnMsg);

        // Address 1
        if (ZYPCommonFunc.hasValue(scrnMsg.firstLineAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.firstLineAddr_H1);
        }

        // Address 2
        if (ZYPCommonFunc.hasValue(scrnMsg.scdLineAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.scdLineAddr_H1);
        }

        // Address 3
        if (ZYPCommonFunc.hasValue(scrnMsg.thirdLineAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_2, scrnMsg.thirdLineAddr_H1);
        }

        // Address 4
        if (ZYPCommonFunc.hasValue(scrnMsg.frthLineAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, scrnMsg.frthLineAddr_H1);
        }

        // City
        if (ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_4, scrnMsg.ctyAddr_H1);
        }

        // State
        if (ZYPCommonFunc.hasValue(scrnMsg.stCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_5, scrnMsg.stCd_H1);
        }

        // Province
        if (ZYPCommonFunc.hasValue(scrnMsg.provNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_6, scrnMsg.provNm_H1);
        }

        // County
        if (ZYPCommonFunc.hasValue(scrnMsg.cntyNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_7, scrnMsg.cntyNm_H1);
        }

        // Postal Code
        if (ZYPCommonFunc.hasValue(scrnMsg.postCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_8, scrnMsg.postCd_H1);
        }

        // Country
        if (ZYPCommonFunc.hasValue(scrnMsg.ctryCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_9, scrnMsg.ctryCd_H1);
        }

        Object[] params = new Object[10];

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;
        params[4] = scrnMsg.xxPopPrm_4;
        params[5] = scrnMsg.xxPopPrm_5;
        params[6] = scrnMsg.xxPopPrm_6;
        params[7] = scrnMsg.xxPopPrm_7;
        params[8] = scrnMsg.xxPopPrm_8;
        params[9] = scrnMsg.xxPopPrm_9;

        return params;
    }

// QC#13431
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2510BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setParamForAttachmentPopup(NMAL2510BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[9];

        params[0] = scrnMsg.xxPopPrm_0.getValue();
        params[1] = scrnMsg.xxPopPrm_1.getValue();
        params[2] = scrnMsg.xxPopPrm_2.getValue();
        params[3] = scrnMsg.xxPopPrm_3.getValue();
        params[4] = scrnMsg.xxPopPrm_4.getValue();
        params[5] = scrnMsg.xxPopPrm_5.getValue();
        params[6] = scrnMsg.xxPopPrm_6.getValue();
        params[7] = scrnMsg.xxPopPrm_7.getValue();
        params[8] = scrnMsg.xxPopPrm_8.getValue();

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2500BMsg
     */
    public static void initParam(NMAL2510BMsg scrnMsg) {

        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();
        scrnMsg.xxPopPrm_7.clear();
        scrnMsg.xxPopPrm_8.clear();
        scrnMsg.xxPopPrm_9.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();

        scrnMsg.P.clear();
    }
// QC#13431

    /**
     * checkActiveDate
     * @param effFromDt String
     * @param effThruDt String
     * @param currentDt String
     * @return boolean
     */
    public static boolean checkActiveDate(String effFromDt, String effThruDt, String currentDt) {

        if (effFromDt.compareTo(currentDt) <= 0) {
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                return true;
            } else if (effThruDt.compareTo(currentDt) >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * setProtectEffectiveFrom
     * @param scrnMsg NMAL2510BMsg
     */
    public static void setProtectEffectiveFrom(NMAL2510BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).ezUpTime_C3)) {
                if (scrnMsg.C.no(i).effFromDt_C2.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
                    scrnMsg.C.no(i).effFromDt_C2.setInputProtected(true);
                } else {
                    scrnMsg.C.no(i).effFromDt_C2.setInputProtected(false);
                }
            } else {
                scrnMsg.C.no(i).effFromDt_C2.setInputProtected(false);
            }
        }
    }

    /**
     * setProtectKeyValue
     * @param scrnMsg NMAL2510BMsg
     */
    public static void setProtectKeyValue(NMAL2510BMsg scrnMsg) {

        boolean doProtect = false;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRsltFlg_H1.getValue())) {
            scrnMsg.psnNum_L1.clear();
            doProtect = true;
        } else {
            scrnMsg.psnNum_L1.setValue(ZYPConstant.FLG_ON_Y);
        }
        // QC#6402
        // scrnMsg.psnCd_H1.setInputProtected(doProtect);
        scrnMsg.psnCd_H1.setInputProtected(true);
        scrnMsg.psnNum_H1.setInputProtected(doProtect);
        // scrnMsg.psnNum_L1.setInputProtected(doProtect);
        scrnMsg.geoCd_L1.setValue(ZYPConstant.FLG_ON_Y);
    }

    // QC#13431
    /**
     * controlAttachmentButton
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2510BMsg
     */
    public static final void controlAttachmentButton(EZDCommonHandler handler, NMAL2510BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1)) {
            handler.setButtonEnabled(NMAL2510Constant.BTN_ATTACHMENTS, true);
        } else {
            handler.setButtonEnabled(NMAL2510Constant.BTN_ATTACHMENTS, false);
        }
    }

    /**
     * setFocusForAPIError
     * @param scrnMsg NMAL2510BMsg
     */
    public static void setFocusForAPIError(NMAL2510BMsg scrnMsg) {

        if (NMAL2510Constant.MSG_COA_CMPY_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).coaCmpyCd_C2);
        }
        if (NMAL2510Constant.MSG_COA_EXTN_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).coaExtnCd_C2);
        }
        if (NMAL2510Constant.MSG_COA_BR_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).coaBrCd_C2);
        }
        if (NMAL2510Constant.MSG_COA_CC_CD.equals(scrnMsg.xxMsgPrmTxt.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).coaCcCd_C2);
        }
    }

    /**
     * <pre>
     * clear mandantory check
     * </pre>
     * @param scrnMsg NMAL2510BMsg
     */
    public static void clearMandantoryCheckForHeader(NMAL2510BMsg scrnMsg) {

        // ### Header ###
        // Resource #
        if (scrnMsg.psnNum_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnNum_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnNum_H1.clearErrorInfo();
            }
        }

        // First Name
        if (scrnMsg.psnFirstNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnFirstNm_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnFirstNm_H1.clearErrorInfo();
            }
        }

        // Last Name
        if (scrnMsg.psnLastNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnLastNm_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnLastNm_H1.clearErrorInfo();
            }
        }

        // Type
        if (scrnMsg.psnTpCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("psnTpCd_P1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.psnTpCd_P1.clearErrorInfo();
            }
        }

        // Employment Date From
        if (scrnMsg.effFromDt_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.effFromDt_H1.clearErrorInfo();
            }
        }
    }
    // 2016/03/04 S21_NA#4539 Mod Start --------------
    /**
     * <pre>
     * clear mandantory check
     * </pre>
     * @param scrnMsg NMAL2510BMsg
     */
    public static void clearMandantoryCheck(NMAL2510BMsg scrnMsg) {
        // ## Header ##
        clearMandantoryCheckForHeader(scrnMsg);

        // ### Hierarchy ###
        if (NMAL2510Constant.TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            clearMandantoryCheckForHierarchy(scrnMsg);
        }

        // ### Territory ###
        if (NMAL2510Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            clearMandantoryCheckForTerritory(scrnMsg);
        }

        // ### Revenue Data ###
        if (NMAL2510Constant.TAB_REVENUE.equals(scrnMsg.xxDplyTab.getValue())) {
            clearMandantoryCheckForRevenueData(scrnMsg);
        }
    }

    /**
     * clearMandantoryCheckForRevenueData
     * @param scrnMsg NMAL2510BMsg
     */
    public static void clearMandantoryCheckForRevenueData(NMAL2510BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // Revenue Account Type
            if (scrnMsg.C.no(i).revAcctTpCd_P1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("revAcctTpCd_P1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).revAcctTpCd_P1.clearErrorInfo();
                }
            }

            // COA Company
            if (scrnMsg.C.no(i).coaCmpyCd_C2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("coaCmpyCd_C2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).coaCmpyCd_C2.clearErrorInfo();
                }
            }

            // COA Extention
            if (scrnMsg.C.no(i).coaExtnCd_C2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("coaExtnCd_C2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).coaExtnCd_C2.clearErrorInfo();
                }
            }

            // COA Branch
            if (scrnMsg.C.no(i).coaBrCd_C2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("coaBrCd_C2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).coaBrCd_C2.clearErrorInfo();
                }
            }

            // COA CC
            if (scrnMsg.C.no(i).coaCcCd_C2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("coaCcCd_C2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).coaCcCd_C2.clearErrorInfo();
                }
            }

            // Start Date
            if (scrnMsg.C.no(i).effFromDt_C2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("effFromDt_C2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).effFromDt_C2.clearErrorInfo();
                }
            }
        }
    }

    /**
     * clearMandantoryCheckForTerritory
     * @param scrnMsg NMAL2510BMsg
     */
    public static void clearMandantoryCheckForTerritory(NMAL2510BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // Business Area
            if (scrnMsg.B.no(i).bizAreaOrgCd_P3.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.B.no(i).getErrorInfo("bizAreaOrgCd_P3");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.B.no(i).bizAreaOrgCd_P3.clearErrorInfo();
                }
            }

            // Territory Name
            if (scrnMsg.B.no(i).orgNm_B2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.B.no(i).getErrorInfo("orgNm_B2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.B.no(i).orgNm_B2.clearErrorInfo();
                }
            }

            // Start Date
            if (scrnMsg.B.no(i).effFromDt_B2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.B.no(i).getErrorInfo("effFromDt_B2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.B.no(i).effFromDt_B2.clearErrorInfo();
                }
            }

        }
    }

    /**
     * clearMandantoryCheckForHierarchy
     * @param scrnMsg NMAL2510BMsg
     */
    public static void clearMandantoryCheckForHierarchy(NMAL2510BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Business Area
            if (scrnMsg.A.no(i).bizAreaOrgCd_P2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("bizAreaOrgCd_P2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).bizAreaOrgCd_P2.clearErrorInfo();
                }
            }

            // Role
            if (scrnMsg.A.no(i).orgFuncRoleTpCd_P2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("orgFuncRoleTpCd_P2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).orgFuncRoleTpCd_P2.clearErrorInfo();
                }
            }

            // Organization Name
            if (scrnMsg.A.no(i).orgNm_A2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("orgNm_A2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).orgNm_A2.clearErrorInfo();
                }
            }

            // Start Date
            if (scrnMsg.A.no(i).effFromDt_A2.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A2");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_A2.clearErrorInfo();
                }
            }

        }
    }
    // 2016/03/04 S21_NA#4539 Mod End --------------
    /**
     * <pre>
     * addCheck input header items
     * </pre>
     * @param scrnMsg NMAL2510BMsg
     */
    public static void addCheckHeaderItems(NMAL2510BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        scrnMsg.addCheckItem(scrnMsg.psnFirstNm_H1);
        scrnMsg.addCheckItem(scrnMsg.psnTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.psnLastNm_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.hrPsnCmpyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.jobTtlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.hrTtlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.workTelNum_H1);
        scrnMsg.addCheckItem(scrnMsg.hrSupvId_H1);
        scrnMsg.addCheckItem(scrnMsg.cellTelNum_H1);
        scrnMsg.addCheckItem(scrnMsg.hrSupvNm_H1);
        scrnMsg.addCheckItem(scrnMsg.emlAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_H1);
    }

    /**
     * <pre>
     * addCheck input items
     * </pre>
     * @param scrnMsg NMAL2510BMsg
     */
    public static void addCheckItems(NMAL2510BMsg scrnMsg) {
        // ### Header ###
        addCheckHeaderItems(scrnMsg);

        // ### Hierarchy Tab ###
        if (NMAL2510Constant.TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).bizAreaOrgCd_P2);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).orgFuncRoleTpCd_P2);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).orgNm_A2);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A2);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A2);
            }

            // ### Territory Tab ###
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B2);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).bizAreaOrgCd_P3);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).orgNm_B2);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).acctTeamRoleTpCd_P3);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B2);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B2);
            }

            // ### Revenue Data ###
        } else if (NMAL2510Constant.TAB_REVENUE.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.geoCd_C1);

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C2);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).revAcctTpCd_P1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).coaCmpyCd_C2);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).coaExtnCd_C2);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).coaBrCd_C2);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).coaCcCd_C2);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C2);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C2);
            }

            // ### Miscellaneous Tab ###
        } else {
            scrnMsg.addCheckItem(scrnMsg.moveOrdLimitAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.tmZoneCd_P1);
            scrnMsg.addCheckItem(scrnMsg.costPerHourAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.ctacPsnPk_D1);
            scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D1);
        }
    }

    // START 2018/09/14 S.Kosaka [QC#27723,ADD]
    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2510BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2510BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2510BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2510BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId, int grpRows) {
        S21TableColorController tblColor = new S21TableColorController(NMAL2510Constant.SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.(All Table)
     * 
     * @param scrnMsg     NMAL2510BMsg
     */
    public static void setAllBGWithClear(NMAL2510BMsg scrnMsg) {
        if (NMAL2510Constant.TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
            setRowsBGWithClear(scrnMsg, scrnMsg.T, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UN);
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.B, NMAL2510Constant.SCREEN_TABLE_NAME_TERRITORY);
        } else if (NMAL2510Constant.TAB_REVENUE.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2510Constant.SCREEN_TABLE_NAME_REVENUE);
        }
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. (All Table)
     * 
     * @param scrnMsg     NMAL2510BMsg
     */
    public static void clearAllRowsBG(NMAL2510BMsg scrnMsg) {
        if (NMAL2510Constant.TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
            clearRowsBG(scrnMsg, scrnMsg.T, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UN);
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.B, NMAL2510Constant.SCREEN_TABLE_NAME_TERRITORY);
        } else if (NMAL2510Constant.TAB_REVENUE.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.C, NMAL2510Constant.SCREEN_TABLE_NAME_REVENUE);
        }
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL2510BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    private static void clearRowsBG(NMAL2510BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(NMAL2510Constant.SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnMsgAry);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. (All Table)
     * 
     * @param handler     EZDCommonHandler
     * @param scrnMsg     NMAL2510BMsg
     */
    public static void setAddDelButton(EZDCommonHandler handler, NMAL2510BMsg scrnMsg) {
        if (NMAL2510Constant.TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.A.getValidCount() + 1 <= scrnMsg.A.length()) {
                handler.setButtonEnabled(NMAL2510Constant.BTN_INSERT_HIERARCHY, true);
            } else {
                handler.setButtonEnabled(NMAL2510Constant.BTN_INSERT_HIERARCHY, false);
            }
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2510Constant.BTN_DELETE_HIERARCHY, true);
            } else {
                handler.setButtonEnabled(NMAL2510Constant.BTN_DELETE_HIERARCHY, false);
            }
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.B.getValidCount() + 1 <= scrnMsg.B.length()) {
                handler.setButtonEnabled(NMAL2510Constant.BTN_INSERT_TERRITORY, true);
            } else {
                handler.setButtonEnabled(NMAL2510Constant.BTN_INSERT_TERRITORY, false);
            }
            if (scrnMsg.B.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2510Constant.BTN_DELETE_TERRITORY, true);
            } else {
                handler.setButtonEnabled(NMAL2510Constant.BTN_DELETE_TERRITORY, false);
            }
        } else if (NMAL2510Constant.TAB_REVENUE.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.C.getValidCount() + 1 <= scrnMsg.C.length()) {
                handler.setButtonEnabled(NMAL2510Constant.BTN_INSERT_REVENUE, true);
            } else {
                handler.setButtonEnabled(NMAL2510Constant.BTN_INSERT_REVENUE, false);
            }
            if (scrnMsg.C.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2510Constant.BTN_DELETE_REVENUE, true);
            } else {
                handler.setButtonEnabled(NMAL2510Constant.BTN_DELETE_REVENUE, false);
            }
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.(All Table and view first tab)
     * 
     * @param scrnMsg     NMAL2510BMsg
     * @param clearTab    String
     */
    public static void setAllBGWithReset(NMAL2510BMsg scrnMsg, String clearTab) {
        if (NMAL2510Constant.TAB_HIERARCHY.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.A, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UP);
            clearRowsBG(scrnMsg, scrnMsg.T, NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UN);
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.B, NMAL2510Constant.SCREEN_TABLE_NAME_TERRITORY);
        } else if (NMAL2510Constant.TAB_REVENUE.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.C, NMAL2510Constant.SCREEN_TABLE_NAME_REVENUE);
        }
        // Color on
        S21TableColorController tblColor = new S21TableColorController(NMAL2510Constant.SCREEN_ID, scrnMsg);
        if (NMAL2510Constant.TAB_HIERARCHY.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UP, scrnMsg.A, 1);
            tblColor.setAlternateRowsBG(NMAL2510Constant.SCREEN_TABLE_NAME_HIERARCHY_UN, scrnMsg.T, 1);
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2510Constant.SCREEN_TABLE_NAME_TERRITORY, scrnMsg.B, 1);
        } else if (NMAL2510Constant.TAB_REVENUE.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2510Constant.SCREEN_TABLE_NAME_REVENUE, scrnMsg.C, 1);
        }
    }
    // END 2018/09/14 S.Kosaka [QC#27723,ADD]
}
