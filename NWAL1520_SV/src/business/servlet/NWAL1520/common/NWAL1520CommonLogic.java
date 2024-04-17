/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520.common;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.SCRN_ID_00;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.util.Arrays.asList;
import business.servlet.NWAL1520.NWAL1520BMsg;
import business.servlet.NWAL1520.NWAL1520Bean;
import business.servlet.NWAL1520.NWAL1520_BBMsgArray;
import business.servlet.NWAL1520.NWAL1520_LBMsg;
import business.servlet.NWAL1520.NWAL1520_LBMsgArray;
import business.servlet.NWAL1520.NWAL1520_VBMsg;
import business.servlet.NWAL1520.NWAL1520_VBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1520CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Fujitsu         A.Suda          Create          N/A
 * 2016/01/08   Fujitsu         T.Ishii         Update          S21_NA#2444
 * 2016/05/27   Fujitsu         T.Murai         Update          S21_NA#7509
 * 2017/02/08   Fujitsu         T.Aoi           Update          S21_NA#17287
 * 2017/07/14   Fujitsu         S.Takami        Update          S21_NA#19916
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#19916-2
 * 2017/08/21   Fujitsu         H.Sugawara      Update          QC#19952
 * 2018/07/05   Fujitsu         S.Yamamoto      Update          QC#26478
 * 2022/08/03   Hitachi	        H.Watanabe      Update          QC#60257
 * 2023/10/10   CITS            K.Ikeda         Update          QC#61940
 *</pre>
 */
public class NWAL1520CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * initScrnValues
     * @param scrnMsg NWAL1520BMsg
     */
    public static void initScrnValues(NWAL1520BMsg scrnMsg) {

        scrnMsg.L.clear();
        scrnMsg.L.setValidCount(0);
        scrnMsg.V.clear();
        scrnMsg.V.setValidCount(0);
        scrnMsg.hldRsnDescTxt_V.clear();
        scrnMsg.hldApplyRsnDescTxt_V.clear();
        scrnMsg.hldApplyRsnCd_V.clear();
        scrnMsg.hldUntilDt_V.clear();

        // S21_NA#2444 scrnMsg.condSqlTxt_A.clear();
        scrnMsg.hldRsnDescTxt_A.clear();
        scrnMsg.hldApplyRsnDescTxt_A.clear();
        scrnMsg.hldApplyRsnCd_A.clear();
        scrnMsg.hldUntilDt_A.clear();

        scrnMsg.hldRelRsnDescTxt_RH.clear();
        scrnMsg.hldRelRsnCd_RH.clear();
        scrnMsg.relMemoTxt_RH.clear();

    }

    /**
     * set InputProtectedforResultFields
     * @param scrnMsg NWAL1520BMsg
     * @param handler Event Handler
     */
    public static void setInputProtectedforCheckBox(EZDCommonHandler handler, NWAL1520BMsg scrnMsg) {

	        NWAL1520_LBMsgArray lineMsgArray = scrnMsg.L;
	        if (lineMsgArray.getValidCount() > 0) {
	
	            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
	                NWAL1520_LBMsg lineMsg = lineMsgArray.no(i);
	                EZDGUIAttribute checkBox = new EZDGUIAttribute(SCRN_ID_00, NWAL1520Bean.xxChkBox + i);
	                if (FLG_ON_Y.equals(lineMsg.relFlg.getValue()) || !isReleaseFunc(scrnMsg.B, lineMsg.relFuncTpCd.getValue())) {
	                    checkBox.setVisibility(false);
	                } else {
	                    checkBox.setVisibility(true);
	                }
	                scrnMsg.addGUIAttribute(checkBox);
	            }
	        }
    }

    /**
     * set InputProtected
     * @param scrnMsg NWAL1520BMsg
     * @param handler Event Handler
     */
    public static void setInputProtected(EZDCommonHandler handler, NWAL1520BMsg scrnMsg) {

        // Add Start 2017/08/21 QC#19952
        scrnMsg.hldRelRsnDescTxt_RH.setInputProtected(true);
        // Add End 2017/08/21 QC#19952

        if (FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            scrnMsg.cpoOrdNum_V.setInputProtected(true);
            scrnMsg.cpoOrdNum_A.setInputProtected(true);
            scrnMsg.condSqlTxt_V.setInputProtected(true);
            scrnMsg.condSqlTxt_A.setInputProtected(true);
            // START 08/03/2022 [QC#60257, Mod]
//            scrnMsg.configCatgCd_V.setInputProtected(true);
            if (!hasValue(scrnMsg.condSqlTxt_V)) {
                scrnMsg.configCatgCd_V.setInputProtected(false);
            } else {
                scrnMsg.configCatgCd_V.setInputProtected(true);
            }
            // END 08/03/2022 [QC#60257, Mod]
            scrnMsg.configCatgCd_A.setInputProtected(true);
            if (!hasValue(scrnMsg.cpoOrdNum_V)) {
                handler.setButtonEnabled(BTN_SEARCH_HOLD, false);
            }
        }

        if (!hasUpdateFuncId(scrnMsg.B)) {

            scrnMsg.condSqlTxt_A.setInputProtected(true);
            scrnMsg.cpoOrdNum_A.setInputProtected(true);
            scrnMsg.configCatgCd_A.setInputProtected(true);
            scrnMsg.hldDtlTxt_A.setInputProtected(true);
            scrnMsg.hldRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldApplyRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldUntilDt_A.setInputProtected(true);
            handler.setButtonEnabled(BTN_APPLY_HOLD, false);

            handler.setButtonEnabled(BTN_SELECT_ALL, false);
            handler.setButtonEnabled(BTN_UN_SELECT_ALL, false);

            scrnMsg.hldRelRsnDescTxt_RH.setInputProtected(true);
            scrnMsg.relMemoTxt_RH.setInputProtected(true);
            handler.setButtonEnabled(BTN_RELEASE_HOLD, false);

        }

        if (!hasApplyHldFuncId(scrnMsg.B)) {

            scrnMsg.condSqlTxt_A.setInputProtected(true);
            scrnMsg.cpoOrdNum_A.setInputProtected(true);
            scrnMsg.configCatgCd_A.setInputProtected(true);
            scrnMsg.hldDtlTxt_A.setInputProtected(true);
            scrnMsg.hldRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldApplyRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldUntilDt_A.setInputProtected(true);
            handler.setButtonEnabled(BTN_APPLY_HOLD, false);
        }

        NWAL1520_VBMsgArray lineMsgVArray = scrnMsg.V;
        if (lineMsgVArray.getValidCount() > 0) {

            for (int i = 0; i < lineMsgVArray.getValidCount(); i++) {
                NWAL1520_VBMsg lineMsg = lineMsgVArray.no(i);
                lineMsg.hldRsnDescTxt_VH.setInputProtected(true);
            }
        }

        NWAL1520_LBMsgArray lineMsgLArray = scrnMsg.L;
        if (lineMsgLArray.getValidCount() > 0) {

            for (int i = 0; i < lineMsgLArray.getValidCount(); i++) {
                NWAL1520_LBMsg lineMsg = lineMsgLArray.no(i);
                lineMsg.hldRsnDescTxt.setInputProtected(true);
                lineMsg.xxPsnNm_AP.setInputProtected(true);
                lineMsg.hldDtlTxt_AP.setInputProtected(true);
                lineMsg.xxPsnNm_RE.setInputProtected(true);
                lineMsg.relMemoTxt_RE.setInputProtected(true);
            }
        }

    }

    /**
     * set screenItemInit
     * @param scrnMsg NWAL1520BMsg
     * @param handler Event Handler
     */
    public static void setScreenItemInit(EZDCommonHandler handler, NWAL1520BMsg scrnMsg) {

        // radio Button initialize .
        scrnMsg.xxRadioBtn_V.setValue(BigDecimal.ONE);

        setInputProtected(handler, scrnMsg);

        if (!FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg.getValue()) && !NWAL1520CommonLogic.hasUpperTabFuncId(scrnMsg.B)) {
            handler.setButtonEnabled(BTN_SEARCH_HOLD, false);
            scrnMsg.condSqlTxt_V.setInputProtected(true);
            scrnMsg.cpoOrdNum_V.setInputProtected(true);
            scrnMsg.configCatgCd_V.setInputProtected(true);
            scrnMsg.hldRsnDescTxt_V.setInputProtected(true);
            scrnMsg.hldApplyRsnDescTxt_V.setInputProtected(true);
            scrnMsg.hldUntilDt_V.setInputProtected(true);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        }
    }

    /**
     * set screenItemInit
     * @param scrnMsg NWAL1520BMsg
     * @param handler Event Handler
     */
    public static void setScreenItemClear(EZDCommonHandler handler, NWAL1520BMsg scrnMsg) {

        // radio Button initialize .
        scrnMsg.xxRadioBtn_V.setValue(BigDecimal.ONE);

        scrnMsg.cpoOrdNum_V.setInputProtected(false);
        scrnMsg.cpoOrdNum_A.setInputProtected(false);
        scrnMsg.condSqlTxt_V.setInputProtected(false);
        scrnMsg.condSqlTxt_A.setInputProtected(false);
        scrnMsg.configCatgCd_V.setInputProtected(false);
        scrnMsg.configCatgCd_A.setInputProtected(false);

        if (!hasUpdateFuncId(scrnMsg.B)) {

            scrnMsg.condSqlTxt_A.setInputProtected(true);
            scrnMsg.cpoOrdNum_A.setInputProtected(true);
            scrnMsg.configCatgCd_A.setInputProtected(true);
            scrnMsg.hldDtlTxt_A.setInputProtected(true);
            scrnMsg.hldRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldApplyRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldUntilDt_A.setInputProtected(true);
            handler.setButtonEnabled(BTN_APPLY_HOLD, false);

            handler.setButtonEnabled(BTN_SELECT_ALL, false);
            handler.setButtonEnabled(BTN_UN_SELECT_ALL, false);

            scrnMsg.hldRelRsnDescTxt_RH.setInputProtected(true);
            scrnMsg.relMemoTxt_RH.setInputProtected(true);
            handler.setButtonEnabled(BTN_RELEASE_HOLD, false);

        }

        if (!hasApplyHldFuncId(scrnMsg.B)) {

            scrnMsg.condSqlTxt_A.setInputProtected(true);
            scrnMsg.cpoOrdNum_A.setInputProtected(true);
            scrnMsg.configCatgCd_A.setInputProtected(true);
            scrnMsg.hldDtlTxt_A.setInputProtected(true);
            scrnMsg.hldRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldApplyRsnDescTxt_A.setInputProtected(true);
            scrnMsg.hldUntilDt_A.setInputProtected(true);
            handler.setButtonEnabled(BTN_APPLY_HOLD, false);
        }

        if (!FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg.getValue()) && !NWAL1520CommonLogic.hasUpperTabFuncId(scrnMsg.B)) {
            handler.setButtonEnabled(BTN_SEARCH_HOLD, false);
            scrnMsg.condSqlTxt_V.setInputProtected(true);
            scrnMsg.cpoOrdNum_V.setInputProtected(true);
            scrnMsg.configCatgCd_V.setInputProtected(true);
            scrnMsg.hldRsnDescTxt_V.setInputProtected(true);
            scrnMsg.hldApplyRsnDescTxt_V.setInputProtected(true);
            scrnMsg.hldUntilDt_V.setInputProtected(true);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        }
    }
    /**
     * check LineNum
     * @param condSqlTxt String
     * @return errFlg boolean
     */
    public static boolean checkLineNum(String condSqlTxt) {

        boolean errFlg = false;
        String[] lineNumsList = condSqlTxt.split(",", 0);

        for (String lineNums : lineNumsList) {

            if (hasValue(lineNums)) {
                int periodCnt = lineNums.length() - lineNums.replaceAll("\\.", "").length();

                if (periodCnt != 1) {
                    errFlg = true;
                    break;

                } else {
                    String[] lineNumList = lineNums.split("\\.");

                    for (String lineNum : lineNumList) {
                        try {
                            Integer.parseInt(lineNum);

                        } catch (NumberFormatException e) {
                            errFlg = true;
                            break;
                        }
                    }
                }
            }
        }
        return errFlg;
    }

    /**
     * isReleaseFunc
     * @param functionIds NWAL1520_BBMsgArray
     * @param relFuncTpCd String
     * @return hasFunctionId boolean
     */
    private static boolean isReleaseFunc(NWAL1520_BBMsgArray functionIds, String relFuncTpCd) {

        if (HLD_TP.SALES_HOLD.equals(relFuncTpCd)) {
            return hasFunctionId(FUNC_SALES_HLD_REL, functionIds);

        } else if (HLD_TP.CREDIT_HOLD.equals(relFuncTpCd)) {
            return hasFunctionId(FUNC_CREDIT_HLD_REL, functionIds);

        } else if (HLD_TP.BILLING_HOLD.equals(relFuncTpCd)) {
            return hasFunctionId(FUNC_BILLING_HLD_REL, functionIds);

        } else if (HLD_TP.DELIVERY_HOLD.equals(relFuncTpCd)) {
            return hasFunctionId(FUNC_DELIVERY_HLD_REL, functionIds);

        } else if (HLD_TP.WORKFLOW_HOLD.equals(relFuncTpCd)) {
            return hasFunctionId(FUNC_WORKFLOW_HLD_REL, functionIds);

            // Add Start 2018/07/05 QC#26478
        } else if (HLD_TP.CUSTOMS_AND_REGULATORY_HOLD.equals(relFuncTpCd)) {
            return hasFunctionId(FUNC_CUST_REGU_HLD_REL, functionIds);
            // Add End 2018/07/05 QC#26478
        }

        return false;
    }

    // START 10/10/2023 [QC#61940, Add]
    /**
     * isPermission
     * @param functionIds NWAL1520_BBMsgArray
     * @param relFuncTpCd String
     * @return hasFunctionId boolean
     */
    public static boolean isPermission(NWAL1520_BBMsgArray functionIds, String relFuncTpCd) {
    	return isReleaseFunc(functionIds,relFuncTpCd);
    }
    // END 10/10/2023 [QC#61940, Add]

    /**
     * hasFunctionId
     * @param functionIds NWAL1520_BBMsgArray
     * @param functionId String
     * @return has FunctionId true/has NOT FunctionId false
     */
    public static boolean hasFunctionId(String functionId, NWAL1520_BBMsgArray functionIds) {
        for (int i = 0; i < functionIds.getValidCount(); i++) {
            if (functionId.equals(functionIds.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * hasUpdateFuncId
     * @param functionIds NWAL1520_BBMsgArray
     * @return has FunctionId for UPDATE true/has NOT FunctionId for UPDATE false
     */
    public static boolean hasUpdateFuncId(NWAL1520_BBMsgArray functionIds) {
        for (int i = 0; i < functionIds.getValidCount(); i++) {
            if (asList(FUNC_SALES_HLD_REL, FUNC_CREDIT_HLD_REL, FUNC_BILLING_HLD_REL, FUNC_DELIVERY_HLD_REL, FUNC_CUST_REGU_HLD_REL, FUNC_WORKFLOW_HLD_REL).contains(functionIds.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * hasUpperTabFuncId
     * @param functionIds NWAL1520_BBMsgArray
     * @return boolean
     */
    public static boolean hasUpperTabFuncId(NWAL1520_BBMsgArray functionIds) {
        for (int i = 0; i < functionIds.getValidCount(); i++) {
            if (asList(FUNC_UPPER_TUB_VIEW).contains(functionIds.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }
    /**
     * hasApplyHldFuncId
     * @param functionIds NWAL1520_BBMsgArray
     * @return has FunctionId for APPLY HOLD true/has NOT FunctionId for APPLY HOLD false
     */
    public static boolean hasApplyHldFuncId(NWAL1520_BBMsgArray functionIds) {
        for (int i = 0; i < functionIds.getValidCount(); i++) {
            if (asList(FUNC_BILLING_HLD_REL, FUNC_DELIVERY_HLD_REL).contains(functionIds.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1520BMsg scrnMsg) {

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
    }

    /**
     * getApplyReasonPopUpParams
     * @param scrnMsg NWAL1520BMsg
     * @return params for ApplyReasonPopUp
     */
    public static Object[] getApplyReasonPopUpParams(NWAL1520BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        scrnMsg.xxPopPrm_P1.setValue("HLD_APPLY_RSN");
        scrnMsg.xxPopPrm_P2.setValue("HLD_APPLY_RSN_CD");
        scrnMsg.xxPopPrm_P3.setValue("HLD_APPLY_RSN_DESC_TXT");
        scrnMsg.xxPopPrm_P4.setValue("HLD_APPLY_RSN_SORT_NUM");
        // S21_NA#17287 T.Aoi Mod Start
        //scrnMsg.xxPopPrm_P5.setValue("Hold Apply Reason Search");
        //scrnMsg.xxPopPrm_P6.setValue("Hold Apply Reason Code");
        //scrnMsg.xxPopPrm_P7.setValue("Hold Apply Reason Name");
        //scrnMsg.xxPopPrm_P8.setValue("Hold Apply Reason Code");
        //scrnMsg.xxPopPrm_P9.setValue("Hold Apply Reason Name");
        scrnMsg.xxPopPrm_P5.setValue("Hold Reason Search");
        scrnMsg.xxPopPrm_P6.setValue("Hold Reason Code");
        scrnMsg.xxPopPrm_P7.setValue("Hold Reason Name");
        scrnMsg.xxPopPrm_P8.setValue("Hold Reason Code");
        scrnMsg.xxPopPrm_P9.setValue("Hold Reason Name");
        // S21_NA#17287 T.Aoi Mod End

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_P1;
        params[1] = scrnMsg.xxPopPrm_P2;
        params[2] = scrnMsg.xxPopPrm_P3;
        params[3] = scrnMsg.xxPopPrm_P4;
        params[4] = scrnMsg.xxPopPrm_P5;
        params[5] = scrnMsg.xxPopPrm_P6;
        params[6] = scrnMsg.xxPopPrm_P7;
        params[7] = scrnMsg.xxPopPrm_P8;
        params[8] = scrnMsg.xxPopPrm_P9;
        params[9] = scrnMsg.xxPopPrm_PA;

        if (LINK_NM_HOLD_REASON_APPLY_HOLD.equals(scrnMsg.xxScrEventNm.getValue())) {
            params[10] = scrnMsg.hldApplyRsnDescTxt_A;
        } else {
            params[10] = scrnMsg.hldApplyRsnDescTxt_V;
        }
        return params;
    }

    /**
     * getViewHoldPopUpParams
     * @param scrnMsg NWAL1520BMsg
     * @param glblCmpyCd String
     * @return params for ViewHoldPopUp
     */
    public static Object[] getViewHoldPopUpParams(NWAL1520BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.I.clear();
        clearPopUpParam(scrnMsg);

        Object[] params = new Object[7];
        params[0] = "";
        // S21_NA#17287 T.Aoi Mod Start
        //params[1] = "Hold Reason Search";
        params[1] = "Hold Search";
        // S21_NA#17287 T.Aoi Mod End

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("HR.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append(",HR.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append(",HR.HLD_RSN_CD AS HLD_RSN_CD ");
        sb.append(",HR.HLD_RSN_DESC_TXT AS HLD_RSN_DESC_TXT ");
        sb.append(",HR.HLD_RSN_SORT_NUM AS HLD_RSN_SORT_NUM ");
        sb.append("FROM ");
        sb.append("HLD_RSN HR ");
        sb.append("WHERE ");
        sb.append("HR.EZCANCELFLAG  = '0' ");
        sb.append("AND HR.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        // 2017/07/27 S21_NA#19916-2 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.condSqlTxt_A)) {
            sb.append("AND HR.HLD_LVL_CD = '").append(HLD_LVL.CPO_DETAIL_LEVEL).append("' ");
        } else {
            sb.append("AND HR.HLD_LVL_CD = '").append(HLD_LVL.CPO_LEVEL).append("' ");
        }
        // 2017/07/27 S21_NA#19916-2 Add Start
        sb.append("AND HR.HLD_REL_AVAL_FLG  = 'Y' ");

        // S21_NA#7509 Add Start 
        boolean hasFuncTp = false;
        if (NWAL1520CommonLogic.hasFunctionId(FUNC_BILLING_HLD_REL, scrnMsg.B)) {
            sb.append("AND HR.REL_FUNC_TP_CD IN ('").append(HLD_TP.BILLING_HOLD).append("' ");
            hasFuncTp = true;
        }
        if (NWAL1520CommonLogic.hasFunctionId(FUNC_DELIVERY_HLD_REL, scrnMsg.B)) {
            if (hasFuncTp) {
                sb.append(",'").append(HLD_TP.DELIVERY_HOLD).append("' ");
            } else {
                sb.append("AND HR.REL_FUNC_TP_CD IN ('").append(HLD_TP.DELIVERY_HOLD).append("' ");
                hasFuncTp = true;
            }
        }
        if (hasFuncTp) {
            sb.append(") ");
        }
        // S21_NA#7509 Add End 

        sb.append("AND HR.HLD_APPLY_TP_CD = '").append(HLD_APPLY_TP_MANUAL).append("' "); // S21_NA#7509 Add
        sb.append("AND ( ( ");
        sb.append(" HR.HLD_EFF_FROM_DT <=  '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("' ");
        sb.append(" AND NVL(HR.HLD_EFF_TO_DT, '99991231') >=  '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("' "); // S21_NA#1080
        sb.append(" )  OR ( ");
        sb.append("EXISTS ( ");
        sb.append("SELECT 'X' ");
        sb.append("FROM ");
        sb.append("HLD H ");
        sb.append("WHERE ");
        sb.append("H.EZCANCELFLAG  = '0' ");
        sb.append("AND H.GLBL_CMPY_CD  = HR.GLBL_CMPY_CD ");
        sb.append("AND H.HLD_RSN_CD   = HR.HLD_RSN_CD ");
        sb.append("AND H.REL_FLG      = 'N' ");
        sb.append(" ) ) )");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        // S21_NA#17287 T.Aoi Mod Start
        //whereArray0[0] = "Hold Reason Code";
        whereArray0[0] = "Hold Code";
        // S21_NA#17287 T.Aoi Mod End
        whereArray0[1] = "HLD_RSN_CD";
        whereArray0[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        // S21_NA#17287 T.Aoi Mod Start
        //whereArray1[0] = "Hold Reason Name";
        whereArray1[0] = "Hold Name";
        // S21_NA#17287 T.Aoi Mod End
        whereArray1[1] = "HLD_RSN_DESC_TXT";
        // 2017/07/27 S21_NA#19916-2 Mod Start
//        whereArray1[2] = scrnMsg.hldRsnDescTxt_V.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.hldRsnDescTxt_V)) {
            whereArray1[2] = scrnMsg.hldRsnDescTxt_V.getValue();
        } else {
            whereArray1[2] = "%";
        }
        // 2017/07/27 S21_NA#19916-2 Mod Start
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        // S21_NA#17287 T.Aoi Mod Start
        //columnArray0[0] = "Hold Reason Code";
        columnArray0[0] = "Hold Code";
        // S21_NA#17287 T.Aoi Mod End
        columnArray0[1] = "HLD_RSN_CD";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        // S21_NA#17287 T.Aoi Mod Start
        //columnArray1[0] = "Hold Reason Name";
        columnArray1[0] = "Hold Name";
        // S21_NA#17287 T.Aoi Mod End
        columnArray1[1] = "HLD_RSN_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "HLD_RSN_SORT_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        params[6] = scrnMsg.I;

        return params;
    }


    /**
     * getApplyHoldPopUpParams
     * @param scrnMsg NWAL1520BMsg
     * @param glblCmpyCd String
     * @return params for ApplyHoldPopUp
     */
    public static Object[] getApplyHoldPopUpParams(NWAL1520BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.I.clear();
        clearPopUpParam(scrnMsg);

        Object[] params = new Object[7];
        params[0] = "";
        // S21_NA#17287 T.Aoi Mod Start
        //params[1] = "Hold Reason Search";
        params[1] = "Hold Search";
        // S21_NA#17287 T.Aoi Mod End

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("HR.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append(",HR.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append(",HR.HLD_RSN_CD AS HLD_RSN_CD ");
        sb.append(",HR.HLD_RSN_DESC_TXT AS HLD_RSN_DESC_TXT ");
        sb.append(",HR.HLD_RSN_SORT_NUM AS HLD_RSN_SORT_NUM ");
        sb.append("FROM ");
        sb.append("HLD_RSN HR ");
        sb.append("WHERE ");
        sb.append("HR.EZCANCELFLAG  = '0' ");
        sb.append("AND HR.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        // 2017/07/14 S21_NA#19916 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.condSqlTxt_A)) {
            sb.append("AND HR.HLD_LVL_CD = '").append(HLD_LVL.CPO_DETAIL_LEVEL).append("' ");
        } else {
            sb.append("AND HR.HLD_LVL_CD = '").append(HLD_LVL.CPO_LEVEL).append("' ");
        }
        // 2017/07/14 S21_NA#19916 Add End
        boolean hasFuncTp = false; // S21_NA#1080
        if (NWAL1520CommonLogic.hasFunctionId(FUNC_BILLING_HLD_REL, scrnMsg.B)) {
            sb.append("AND HR.REL_FUNC_TP_CD IN ('").append(HLD_TP.BILLING_HOLD).append("' ");
            hasFuncTp = true;
        }
        if (NWAL1520CommonLogic.hasFunctionId(FUNC_DELIVERY_HLD_REL, scrnMsg.B)) {
            if (hasFuncTp) {
                sb.append(",'").append(HLD_TP.DELIVERY_HOLD).append("' ");
            } else {
                sb.append("AND HR.REL_FUNC_TP_CD IN ('").append(HLD_TP.DELIVERY_HOLD).append("' ");
                hasFuncTp = true;
            }
        }
        if (hasFuncTp) {
            sb.append(") ");
        }
        sb.append("AND HR.HLD_EFF_FROM_DT  <=  '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("' ");
        sb.append("AND NVL(HR.HLD_EFF_TO_DT, '99991231') >=  '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("' "); // S21_NA#
        sb.append("AND HR.HLD_APPLY_TP_CD = '").append(HLD_APPLY_TP_MANUAL).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        // S21_NA#17287 T.Aoi Mod Start
        //whereArray0[0] = "Hold Reason Code";
        whereArray0[0] = "Hold Code";
        // S21_NA#17287 T.Aoi Mod End
        whereArray0[1] = "HLD_RSN_CD";
        whereArray0[2] = scrnMsg.xxPopPrm_P1.getValue();
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        // S21_NA#17287 T.Aoi Mod Start
        //whereArray1[0] = "Hold Reason Name";
        whereArray1[0] = "Hold Name";
        // S21_NA#17287 T.Aoi Mod End
        whereArray1[1] = "HLD_RSN_DESC_TXT";
        // 2017/07/14 S21_NA#19916 Mod Start
//        whereArray1[2] = scrnMsg.hldRsnDescTxt_A.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.hldRsnDescTxt_A.getValue())) {
            whereArray1[2] = scrnMsg.hldRsnDescTxt_A.getValue();
        } else {
            whereArray1[2] = "%";
        }
        // 2017/07/14 S21_NA#19916 Mod End
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        // S21_NA#17287 T.Aoi Mod Start
        //columnArray0[0] = "Hold Reason Code";
        columnArray0[0] = "Hold Code";
        // S21_NA#17287 T.Aoi Mod End
        columnArray0[1] = "HLD_RSN_CD";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        // S21_NA#17287 T.Aoi Mod Start
        //columnArray1[0] = "Hold Reason Name";
        columnArray1[0] = "Hold Name";
        // S21_NA#17287 T.Aoi Mod End
        columnArray1[1] = "HLD_RSN_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "HLD_RSN_SORT_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        params[6] = scrnMsg.I;

        return params;
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1520BMsg
     * @param scrnAMsgAry NWAL1520_LBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1520BMsg scrnMsg, NWAL1520_LBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1520BMsg
     * @param scrnAMsgAry NWAL1520_LBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1520BMsg scrnMsg, NWAL1520_LBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1520BMsg
     * @param scrnAMsgAry NWAL1520_LBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1520BMsg scrnMsg, NWAL1520_LBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }
    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1520BMsg
     * @param scrnAMsgAry NWAL1520_LBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1520BMsg scrnMsg, NWAL1520_VBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1520BMsg
     * @param scrnAMsgAry NWAL1520_LBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1520BMsg scrnMsg, NWAL1520_VBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1520BMsg
     * @param scrnAMsgAry NWAL1520_LBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1520BMsg scrnMsg, NWAL1520_VBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

}
