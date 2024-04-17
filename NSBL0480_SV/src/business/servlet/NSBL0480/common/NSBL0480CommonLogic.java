/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0480.common;

import static business.servlet.NSBL0480.constant.NSBL0480Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSBL0480.NSBL0480CMsg;
import business.servlet.NSBL0480.NSBL0480BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 * 2016/06/08   Hitachi         J.Kim           Update          QC#6952 
 * 2016/12/15   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public class NSBL0480CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * setRequestData_UpdateCommon
     * @return NSBL0480CMsg
     */
    public static NSBL0480CMsg setRequestData_UpdateCommon() {

        NSBL0480CMsg bizMsg = new NSBL0480CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * setRequestData_SearchCommon
     * @return NSBL0480CMsg
     */
    public static NSBL0480CMsg setRequestData_SearchCommon() {

        NSBL0480CMsg bizMsg = new NSBL0480CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void initialize(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        screenControlProcess(handler, scrnMsg);
        scrnMsg.fullPsnNm_A3.setInputProtected(true);
        handler.setButtonEnabled(SEARCH_RESOURCE[0], true);
        handler.setButtonEnabled(INSERT_ROW[0], false);
        handler.setButtonEnabled(DELETE_ROW[0], false);
        handler.setButtonEnabled(SWITCH_VIEW[0], false);
        scrnMsg.setFocusItem(scrnMsg.techCd_A1);
    }

    /**
     * screenControlClear
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void screenControlClear(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        initialize(handler, scrnMsg);
    }

    /**
     * screenControlInsert
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void screenControlInsert(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], false);
        }

        enableButtons(handler, SUBMIT[0]);
        enableButtons(handler, DELETE_ROW[0]);
        enableButtons(handler, SWITCH_VIEW[0]);
        setRowColors(scrnMsg);
        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.xxRadioBtn_A.setValue(scrnMsg.A.getValidCount() - 1);
            scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A);
        } else {
            scrnMsg.xxRadioBtn_B.setValue(scrnMsg.B.getValidCount() - 1);
            scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_B);
        }
    }

    /**
     * screenControlDelete
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void screenControlDelete(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        } else {
            handler.setButtonEnabled(INSERT_ROW[0], false);
        }

        deleteButtonControl(handler, scrnMsg);
        setSelectedIndex(scrnMsg);
    }

    private static void deleteButtonControl(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        int deleteCnt = 0;
        int validCount = 0;
        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcAccsPmitTechRelnPk_A)) {
                    deleteCnt++;
                }
            }
            validCount = scrnMsg.A.getValidCount();
        } else {
            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(j).svcAccsPmitTechRelnPk_B)) {
                    deleteCnt++;
                }
            }
            validCount = scrnMsg.B.getValidCount();
        }

        if (deleteCnt == 0) {
            handler.setButtonEnabled(DELETE_ROW[0], false);
        } else {
            handler.setButtonEnabled(DELETE_ROW[0], true);
        }

        if (validCount == 0) {
            handler.setButtonEnabled(SWITCH_VIEW[0], false);
        } else {
            handler.setButtonEnabled(SWITCH_VIEW[0], true);
        }
    }

    /**
     * searchScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void searchScreenControl(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            searchResourceScreenControl(handler, scrnMsg);
        } else {
            searchAccessScreenControl(handler, scrnMsg);
        }
    }

    /**
     * searchResourceScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void searchResourceScreenControl(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        // START 2017/02/01 K.Kitachi [QC#16629, MOD]
        int length = scrnMsg.A.length();
        if (length == 0) {
            handler.setButtonEnabled(SWITCH_VIEW[0], false);
            handler.setButtonEnabled(SUBMIT[0], false);
        } else {
            handler.setButtonEnabled(SWITCH_VIEW[0], true);
            handler.setButtonEnabled(SUBMIT[0], true);
        }

        for (int index = 0; index < length; index++) {
            scrnMsg.A.no(index).svcAccsPmitDescTxt_A.setInputProtected(true);
        }
        // END 2017/02/01 K.Kitachi [QC#16629, MOD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        }
        deleteButtonControl(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);

        setRowColors(scrnMsg);
    }

    /**
     * searchAccessScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void searchAccessScreenControl(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        // START 2017/02/01 K.Kitachi [QC#16629, MOD]
        int length = scrnMsg.B.length();
        if (length == 0) {
            handler.setButtonEnabled(SWITCH_VIEW[0], false);
            handler.setButtonEnabled(SUBMIT[0], false);
        } else {
            handler.setButtonEnabled(SWITCH_VIEW[0], true);
            handler.setButtonEnabled(SUBMIT[0], true);
        }

        for (int index = 0; index < length; index++) {
            scrnMsg.B.no(index).fullPsnNm_B.setInputProtected(true);
        }
        // END 2017/02/01 K.Kitachi [QC#16629, MOD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        }
        deleteButtonControl(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);

        setRowColors(scrnMsg);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void submitScreenControl(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.techCd_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.techCd_B1);
        }

        deleteButtonControl(handler, scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * switchViewScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void switchViewScreenControl(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            screenControlProcess(handler, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.techCd_A1);
            handler.setButtonEnabled(SEARCH_RESOURCE[0], true);
            searchResourceScreenControl(handler, scrnMsg);
        } else {
            screenControlProcessAccessPermits(handler, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.svcAccsPmitNum);
            handler.setButtonEnabled(SEARCH_ACCESS[0], true);
            searchAccessScreenControl(handler, scrnMsg);
        }
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.psnTpDescTxt.setInputProtected(true);
        scrnMsg.fullPsnNm_A3.setInputProtected(true);

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH_RESOURCE, INSERT_ROW, DELETE_ROW, SWITCH_VIEW);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        screenControlByFuncId(handler, scrnMsg);
    }

    /**
     * screenControlProcessAccessPermits
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void screenControlProcessAccessPermits(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.svcAccsPmitDescTxt.setInputProtected(true);

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH_ACCESS, INSERT_ROW, DELETE_ROW, SWITCH_VIEW);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        enableButtons(handler, CLEAR[0], RETURN[0], SEARCH_ACCESS[0]);
    }

    /**
     * addCheckItemRadioBtn
     * @param scrnMsg NSBL0480BMsg
     */
    public static void addCheckItemRadioBtn(NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A);
        } else {
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_B);
        }
    }

    /**
     * addCheckItemSwitchView
     * @param scrnMsg NSBL0480BMsg
     */
    public static void addCheckItemSwitchView(NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAccsPmitNum_A);
            }
        } else {
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).techCd_B);
            }
        }
    }

    /**
     * checkInputPageCheckItem
     * @param scrnMsg NSBL0480BMsg
     */
    public static void checkInputPageCheckItem(NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAccsPmitNum_A);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPmitLvlTpCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem30Txt_A);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            }
        } else {
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).techCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
            }
        }
    }

    /**
     * checkInputAddCheckItem
     * @param scrnMsg NSBL0480BMsg
     */
    public static void checkInputAddCheckItem(NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.techCd_A1);
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAccsPmitNum_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAccsPmitDescTxt_A);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPmitLvlTpCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem30Txt_A);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            }
        } else {
            scrnMsg.addCheckItem(scrnMsg.techCd_B1);
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).techCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).fullPsnNm_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSBL0480BMsg
     */
    public static void addCheckItem(NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.techCd_A1);
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A);
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAccsPmitNum_A);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPmitLvlTpCd_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem30Txt_A);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            }
        } else {
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_B);
            scrnMsg.addCheckItem(scrnMsg.svcAccsPmitNum);
            // START 2017/02/01 K.Kitachi [QC#16629, ADD]
            scrnMsg.addCheckItem(scrnMsg.svcPmitLvlTpCd);
            scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt);
            // END 2017/02/01 K.Kitachi [QC#16629, ADD]
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).techCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
            }
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSBL0480). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNC_ID_T020) || funcIdList.contains(FUNC_ID_T030) || funcIdList.contains(FUNC_ID_T040)) {
            enableButtonsByFuncId(handler, scrnMsg);
        } else if (funcIdList.contains(FUNC_ID_T010)) {
            enableButtonsByFuncId(handler, scrnMsg);
            isInactiveList(handler, scrnMsg);
        } else {
            enableButtons(handler, RETURN[0]);
        }
    }

    /**
     * enableButtonsByFuncId
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void enableButtonsByFuncId(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {
        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH_RESOURCE[0]);
        } else {
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH_ACCESS[0]);
        }
    }

    /**
     * isInactiveList
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0480BMsg
     */
    public static void isInactiveList(EZDCommonHandler handler, NSBL0480BMsg scrnMsg) {

        handler.setButtonEnabled(SUBMIT[0], false);
        handler.setButtonEnabled(INSERT_ROW[0], false);
        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                handler.setButtonEnabled("OpenWin_R_Access", i, false);
                scrnMsg.A.no(i).svcAccsPmitNum_A.setInputProtected(true);
                scrnMsg.A.no(i).svcAccsPmitDescTxt_A.setInputProtected(true);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.A.no(i).svcPmitLvlTpCd_A.setInputProtected(true);
                scrnMsg.A.no(i).xxScrItem30Txt_A.setInputProtected(true);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            }
        } else {
            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                handler.setButtonEnabled("OpenWin_R_Resource", j, false);
                scrnMsg.B.no(j).fullPsnNm_B.setInputProtected(true);
                scrnMsg.B.no(j).techCd_B.setInputProtected(true);
                scrnMsg.B.no(j).effFromDt_B.setInputProtected(true);
                scrnMsg.B.no(j).effThruDt_B.setInputProtected(true);
            }
        }
    }

    private static void setSelectedIndex(NSBL0480BMsg scrnMsg) {

        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.xxRadioBtn_A.setValue(BigDecimal.ZERO);
                scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A);
            }
        } else {
            if (scrnMsg.B.getValidCount() > 0) {
                scrnMsg.xxRadioBtn_B.setValue(BigDecimal.ZERO);
                scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_B);
            }
        }
    }

    private static void setRowColors(NSBL0480BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (RESOURCE_MODE.equals(scrnMsg.xxScrDply.getValue())) {
            if (scrnMsg.A.length() > 0) {
                tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
                tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
            }
        } else {
            if (scrnMsg.B.length() > 0) {
                tblColor.clearRowsBG(TABLE_B, scrnMsg.B);
                tblColor.setAlternateRowsBG(TABLE_B, scrnMsg.B);
            }
        }
    }

    /**
     * Activate buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String...
     */
    public static void enableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, true);
        }
    }

    /**
     * getResourceTypeSql
     * @param scrnMsg NSBL0480BMsg
     * @return String
     */
    public static String getResourceTypeSql(NSBL0480BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("    SP.GLBL_CMPY_CD");
        sql.append("   ,SP.EZCANCELFLAG");
        sql.append("   ,SP.PSN_CD");
        sql.append("   ,SP.PSN_LAST_NM||','||PSN_FIRST_NM AS PSN_NM ");
        sql.append("   ,PT.PSN_TP_DESC_TXT ");
        sql.append(" FROM ");
        sql.append("    S21_PSN   SP ");
        sql.append("   ,PSN_TP    PT ");
        sql.append("   ,TECH_MSTR TM ");
        sql.append(" WHERE");
        sql.append("     SP.GLBL_CMPY_CD = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("' ");
        sql.append("     AND SP.EFF_FROM_DT   <= '");
        sql.append(scrnMsg.slsDt.getValue());
        sql.append("' ");
        sql.append("     AND NVL(SP.EFF_THRU_DT, '99991231') >= '");
        sql.append(scrnMsg.slsDt.getValue());
        sql.append("'    AND SP.DEL_FLG ='");
        sql.append(ZYPConstant.FLG_OFF_N);
        sql.append("'    AND SP.EZCANCELFLAG = '0'");
        sql.append("     AND SP.GLBL_CMPY_CD = PT.GLBL_CMPY_CD");
        sql.append("     AND SP.PSN_TP_CD    = PT.PSN_TP_CD");
        sql.append("     AND PT.EZCANCELFLAG = '0'");
        sql.append("     AND SP.GLBL_CMPY_CD = TM.GLBL_CMPY_CD");
        sql.append("     AND SP.PSN_CD       = TM.TECH_TOC_CD");
        sql.append("     AND TM.EZCANCELFLAG = '0' ");
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        sql.append("    AND EXISTS (");
        sql.append("        SELECT");
        sql.append("            1");
        sql.append("        FROM");
        sql.append("            ORG_FUNC_ASG OFS");
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            ,TOC              T");
        sql.append("            ,ORG_FUNC_ROLE_TP OFRT");
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("        WHERE");
        sql.append("                OFS.GLBL_CMPY_CD = TM.GLBL_CMPY_CD");
        sql.append("            AND OFS.PSN_CD       = TM.TECH_TOC_CD");
        sql.append("            AND OFS.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sql.append("            AND NVL(OFS.EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            AND OFS.GLBL_CMPY_CD                  = T.GLBL_CMPY_CD");
        sql.append("            AND OFS.TOC_CD                        = T.TOC_CD");
        sql.append("            AND T.GLBL_CMPY_CD                    = OFRT.GLBL_CMPY_CD");
        sql.append("            AND T.ORG_FUNC_ROLE_TP_CD             = OFRT.ORG_FUNC_ROLE_TP_CD");
        sql.append("            AND OFRT.TECH_MSTR_REQ_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sql.append("            AND OFRT.ACTV_FLG                     = '").append(ZYPConstant.FLG_ON_Y).append("'");
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            AND OFS.EZCANCELFLAG = '0'");
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            AND T.EZCANCELFLAG    = '0'");
        sql.append("            AND OFRT.EZCANCELFLAG = '0'");
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("    )");
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
        return sql.toString();
    }

    /**
     * getResourceTypeWhereList
     * @param scrnMsg NSBL0480BMsg
     * @param index int
     * @return List<Object[]>
     */
    public static List<Object[]> getResourceTypeWhereList(NSBL0480BMsg scrnMsg, int index) {

        String psnCd = null;
        String fullPsnNm = null;
        String psnTpDescTxt = null;
        // mod start 2016/06/08 CSA Defect#9652
        if (index < 0) {
            psnCd = scrnMsg.techCd_A1.getValue();
            fullPsnNm = scrnMsg.fullPsnNm_A3.getValue();
            psnTpDescTxt = scrnMsg.psnTpDescTxt.getValue();
        } else {
            psnCd = scrnMsg.B.no(index).techCd_B.getValue();
            fullPsnNm = scrnMsg.B.no(index).fullPsnNm_B.getValue();
        }
        // mod start 2016/06/08 CSA Defect#9652

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Person Code";
        h0[WLIST_OBJ_ID] = "PSN_CD";
        if (psnCd != null) {
            h0[WLIST_OBJ_VALUE] = psnCd;
        } else {
            h0[WLIST_OBJ_VALUE] = "";
        }
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Person Name";
        h1[WLIST_OBJ_ID] = "PSN_NM";
        if (fullPsnNm != null) {
            h1[WLIST_OBJ_VALUE] = fullPsnNm;
        } else {
            h1[WLIST_OBJ_VALUE] = "";
        }
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
        h2[WLIST_DSP_OBJ_NM] = "Person Type";
        h2[WLIST_OBJ_ID] = "PSN_TP_DESC_TXT";
        if (psnTpDescTxt != null) {
            h2[WLIST_OBJ_VALUE] = psnTpDescTxt;
        } else {
            h2[WLIST_OBJ_VALUE] = "";
        }
        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h2);

        return whereList;
    }

    /**
     * getResourceTypeColumnList
     * @return List<Object[]>
     */
    public static List<Object[]> getResourceTypeColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Person Code";
        c0[CLIST_OBJ_ID] = "PSN_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(PSN_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Person Name";
        c1[CLIST_OBJ_ID] = "PSN_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(FULL_NAME_TXT);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Person Type";
        c2[CLIST_OBJ_ID] = "PSN_TP_DESC_TXT";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(PSN_TP_DESC_TXT);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c2);

        return columnList;
    }

    /**
     * getResourceTypeSortConditionList
     * @return List<Object[]>
     */
    public static List<Object[]> getResourceTypeSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "PSN_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);
        return sortConditionList;
    }

    /**
     * getRSql
     * @param scrnMsg NSBL0480BMsg
     * @return String
     */
    public static String getRSql(NSBL0480BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();
        // START 2017/02/01 K.Kitachi [QC#16629, MOD]
        sql.append(" SELECT");
        sql.append("    SAP.GLBL_CMPY_CD");
        sql.append("   ,SAP.EZCANCELFLAG");
        sql.append("   ,SAP.SVC_ACCS_PMIT_NUM");
        sql.append("   ,PVAL.SVC_ACCS_PMIT_DESC_TXT");
        sql.append("   ,SAP.SVC_ACCS_PMIT_PK");
        sql.append("   ,SAP.SVC_PMIT_LVL_TP_CD");
        sql.append("   ,SAP.SVC_PMIT_LVL_VAL_TXT");
        sql.append("   ,SPLT.SVC_PMIT_LVL_TP_DESC_TXT");
        sql.append(" FROM ");
        sql.append("    SVC_ACCS_PMIT      SAP ");
        sql.append("   ,SVC_PMIT_LVL_TP    SPLT ");
        sql.append("   ,SVC_ACCS_PMIT_VAL  PVAL ");
        sql.append(" WHERE");
        sql.append("     SAP.GLBL_CMPY_CD           = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("' ");
        sql.append("     AND SAP.EFF_FROM_DT       <= '");
        sql.append(scrnMsg.slsDt.getValue());
        sql.append("' ");
        sql.append("     AND NVL(SAP.EFF_TO_DT, '99991231') >= '");
        sql.append(scrnMsg.slsDt.getValue());
        sql.append("'    AND SAP.EZCANCELFLAG        = '0'");
        sql.append("     AND SAP.GLBL_CMPY_CD        = SPLT.GLBL_CMPY_CD");
        sql.append("     AND SAP.SVC_PMIT_LVL_TP_CD  = SPLT.SVC_PMIT_LVL_TP_CD");
        sql.append("     AND SPLT.EZCANCELFLAG       = '0'");
        sql.append("     AND SAP.GLBL_CMPY_CD        = PVAL.GLBL_CMPY_CD (+)");
        sql.append("     AND SAP.SVC_ACCS_PMIT_NUM   = PVAL.SVC_ACCS_PMIT_NUM (+)");
        sql.append("     AND PVAL.EZCANCELFLAG (+)   = '0'");
        // END 2017/02/01 K.Kitachi [QC#16629, MOD]

        return sql.toString();
    }

    /**
     * getRWhereList
     * @param scrnMsg NSBL0480BMsg
     * @param index int
     * @return List<Object[]>
     */
    public static List<Object[]> getRWhereList(NSBL0480BMsg scrnMsg, int index) {

        String psnCd = null;
        String fullPsnNm = null;
        if (index < 0) {
            psnCd = scrnMsg.techCd_B1.getValue();
            fullPsnNm = scrnMsg.svcAccsPmitDescTxt.getValue();
        } else {
            psnCd = scrnMsg.A.no(index).svcAccsPmitNum_A.getValue();
            fullPsnNm = scrnMsg.A.no(index).svcAccsPmitDescTxt_A.getValue();
        }

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Number";
        h0[WLIST_OBJ_ID] = "SVC_ACCS_PMIT_NUM";
        if (psnCd != null) {
            h0[WLIST_OBJ_VALUE] = psnCd;
        } else {
            h0[WLIST_OBJ_VALUE] = "";
        }
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Name";
        h1[WLIST_OBJ_ID] = "SVC_ACCS_PMIT_DESC_TXT";
        if (fullPsnNm != null) {
            h1[WLIST_OBJ_VALUE] = fullPsnNm;
        } else {
            h1[WLIST_OBJ_VALUE] = "";
        }
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
        h2[WLIST_DSP_OBJ_NM] = "Level Type";
        h2[WLIST_OBJ_ID] = "SVC_PMIT_LVL_TP_DESC_TXT";
        h2[WLIST_OBJ_VALUE] = "";
        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h2);

        return whereList;
    }

    /**
     * getRColumnList
     * @return List<Object[]>
     */
    public static List<Object[]> getRColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Access Permits Number";
        c0[CLIST_OBJ_ID] = "SVC_ACCS_PMIT_NUM";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_ACCS_PMIT_NUM_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Access Permits Name";
        c1[CLIST_OBJ_ID] = "SVC_ACCS_PMIT_DESC_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_PMIT_DESC_TXT);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Access Permits Level Type";
        c2[CLIST_OBJ_ID] = "SVC_PMIT_LVL_TP_DESC_TXT";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_PMIT_DESC_TXT);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c2);

        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
        c3[CLIST_DSP_OBJ_NM] = "Access Permits Level Value";
        c3[CLIST_OBJ_ID] = "SVC_PMIT_LVL_VAL_TXT";
        c3[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_PMIT_LVL_VAL_TXT);
        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c3);

        // START 2017/02/01 K.Kitachi [QC#16629, ADD]
        Object[] c4 = new Object[ATTR_NUM_CLMN_LIST];
        c4[CLIST_DSP_OBJ_NM] = "";
        c4[CLIST_OBJ_ID] = "SVC_PMIT_LVL_TP_CD";
        c4[CLIST_OBJ_LENGTH] = BigDecimal.ZERO;
        c4[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c4);
        // END 2017/02/01 K.Kitachi [QC#16629, ADD]

        return columnList;
    }

    /**
     * getRSortConditionList
     * @return List<Object[]>
     */
    public static List<Object[]> getRSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "SVC_ACCS_PMIT_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "SVC_ACCS_PMIT_DESC_TXT";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[2];
        sortConditionArray3[0] = "SVC_PMIT_LVL_TP_DESC_TXT";
        sortConditionArray3[1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        return sortConditionList;
    }
}
