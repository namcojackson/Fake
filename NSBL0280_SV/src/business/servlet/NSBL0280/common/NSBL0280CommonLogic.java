/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0280.common;

import static business.servlet.NSBL0280.constant.NSBL0280Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSBL0280.NSBL0280CMsg;
import business.servlet.NSBL0280.NSBL0280BMsg;

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
 * 2016/12/15   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 *</pre>
 */
public class NSBL0280CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSBL0280CMsg
     */
    public static NSBL0280CMsg setRequestData_UpdateCommon() {

        NSBL0280CMsg bizMsg = new NSBL0280CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSBL0280CMsg
     */
    public static NSBL0280CMsg setRequestData_SearchCommon() {

        NSBL0280CMsg bizMsg = new NSBL0280CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        screenControlProcess(handler, scrnMsg);
        scrnMsg.fullPsnNm.setInputProtected(true);
        handler.setButtonEnabled(INSERT_ROW[0], false);
        handler.setButtonEnabled(DELETE_ROW[0], false);
        handler.setButtonEnabled(SWITCH_VIEW[0], false);
        scrnMsg.setFocusItem(scrnMsg.svcSkillResrcTpCd_S);
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void screenControlClear(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        initialize(handler, scrnMsg);
    }

    /**
     * screenControlAdd
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void screenControlInsert(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxErrNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], false);
        }

        enableButtons(handler, DELETE_ROW[0]);
        enableButtons(handler, SWITCH_VIEW[0]);
        enableButtons(handler, SUBMIT[0]);
        setRowColors(scrnMsg);
        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.xxRadioBtn_A.setValue(scrnMsg.A.getValidCount() - 1);
            scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A);
        } else {
            scrnMsg.xxRadioBtn_B.setValue(scrnMsg.B.getValidCount() - 1);
            scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_B);
        }
    }

    /**
     * processDelete
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void screenControlDelete(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxErrNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        } else {
            handler.setButtonEnabled(INSERT_ROW[0], false);
        }

        deleteButtonControl(handler, scrnMsg);
        setSelectedIndex(scrnMsg);
    }

    private static void deleteButtonControl(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        int deleteCnt = 0;
        int switchView = 0;
        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).techTngHistPk_A)) {
                    deleteCnt++;
                }
            }
            switchView = scrnMsg.A.getValidCount();
        } else {
            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(j).techTngHistPk_B)) {
                    deleteCnt++;
                }
            }
            switchView = scrnMsg.B.getValidCount();
        }

        if (deleteCnt == 0) {
            handler.setButtonEnabled(DELETE_ROW[0], false);
        } else {
            handler.setButtonEnabled(DELETE_ROW[0], true);
        }

        if (switchView == 0) {
            handler.setButtonEnabled(SWITCH_VIEW[0], false);
        } else {
            handler.setButtonEnabled(SWITCH_VIEW[0], true);
        }
    }

    /**
     * searchScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void searchScreenControl(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            searchResourceScreenControl(handler, scrnMsg);
        } else {
            searchSkillScreenControl(handler, scrnMsg);
        }
    }

    /**
     * searchScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void searchResourceScreenControl(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        int validCount = scrnMsg.A.getValidCount();
        if (validCount == 0) {
            handler.setButtonEnabled(SWITCH_VIEW[0], false);
            handler.setButtonEnabled(SUBMIT[0], false);
        } else {
            handler.setButtonEnabled(SWITCH_VIEW[0], true);
            handler.setButtonEnabled(SUBMIT[0], true);
        }

        for (int index = 0; index < validCount; index++) {
            scrnMsg.A.no(index).svcSkillTpDescTxt_A.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxErrNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        }
        deleteButtonControl(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);

        setRowColors(scrnMsg);
    }

    /**
     * searchSkillScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void searchSkillScreenControl(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        int validCount = scrnMsg.B.getValidCount();
        if (validCount == 0) {
            handler.setButtonEnabled(SWITCH_VIEW[0], false);
            handler.setButtonEnabled(SUBMIT[0], false);
        } else {
            handler.setButtonEnabled(SWITCH_VIEW[0], true);
            handler.setButtonEnabled(SUBMIT[0], true);
        }

        for (int index = 0; index < validCount; index++) {
            scrnMsg.B.no(index).fullPsnNm_B.setInputProtected(true);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxErrNum)) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        }
        deleteButtonControl(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);

        setRowColors(scrnMsg);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void submitScreenControl(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.psnCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.svcSkillDescTxt);
        }

        deleteButtonControl(handler, scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * switchViewScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void switchViewScreenControl(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            screenControlProcess(handler, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.psnCd);
            handler.setButtonEnabled(SEARCH_RESOURCE[0], true);
            searchResourceScreenControl(handler, scrnMsg);
        } else {
            screenControlProcessSkill(handler, scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.svcSkillDescTxt);
            handler.setButtonEnabled(SEARCH_SKILL[0], true);
            searchSkillScreenControl(handler, scrnMsg);
        }
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.fullPsnNm.setInputProtected(true);

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
     * screenControlProcessSkill
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void screenControlProcessSkill(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH_SKILL, INSERT_ROW, DELETE_ROW, SWITCH_VIEW);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        enableButtons(handler, CLEAR[0], RETURN[0], SEARCH_SKILL[0]);
    }

    /**
     * addCheckItemRadioBtn
     * @param scrnMsg NSBL0280BMsg
     */
    public static void addCheckItemRadioBtn(NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A);
        } else {
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_B);
        }
    }

    /**
     * checkInputPageCheckItem
     * @param scrnMsg NSBL0280BMsg
     */
    public static void checkInputPageCheckItem(NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillDescTxt_A);
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
     * @param scrnMsg NSBL0280BMsg
     */
    public static void checkInputAddCheckItem(NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.psnCd);
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillTpDescTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillDescTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlPk_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            }
        } else {
            scrnMsg.addCheckItem(scrnMsg.svcSkillDescTxt);
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).techCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).fullPsnNm_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).svcSkillResrcTpCd_BS);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).svcSkillLvlPk_BS);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSBL0280BMsg
     */
    public static void addCheckItem(NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.psnCd);
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_A);
            for (int i = 0; i < scrnMsg.A.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillTpDescTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillDescTxt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlPk_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            }
        } else {
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_B);
            scrnMsg.addCheckItem(scrnMsg.svcSkillDescTxt);
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).techCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).fullPsnNm_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).svcSkillResrcTpCd_BS);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).svcSkillLvlPk_BS);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B);
            }
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSBL0280). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
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
     * @param scrnMsg NSBL0280BMsg
     */
    public static void enableButtonsByFuncId(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {
        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH_RESOURCE[0]);
        } else {
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH_SKILL[0]);
        }
    }

    /**
     * isInactiveList
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0280BMsg
     */
    public static void isInactiveList(EZDCommonHandler handler, NSBL0280BMsg scrnMsg) {

        handler.setButtonEnabled(SUBMIT[0], false);
        handler.setButtonEnabled(INSERT_ROW[0], false);
        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                handler.setButtonEnabled("OpenWin_S", i, false);
                scrnMsg.A.no(i).svcSkillTpDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).svcSkillDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).svcSkillLvlPk_AS.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            }
        } else {
            for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                handler.setButtonEnabled("OpenWin_R", j, false);
                scrnMsg.B.no(j).fullPsnNm_B.setInputProtected(true);
                scrnMsg.B.no(j).svcSkillResrcTpCd_BS.setInputProtected(true);
                scrnMsg.B.no(j).svcSkillLvlPk_BS.setInputProtected(true);
                scrnMsg.B.no(j).effFromDt_B.setInputProtected(true);
                scrnMsg.B.no(j).effThruDt_B.setInputProtected(true);
            }
        }
    }

    private static void setSelectedIndex(NSBL0280BMsg scrnMsg) {

        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
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

    private static void setRowColors(NSBL0280BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (RESOURCE_TYPE.equals(scrnMsg.xxScrDply.getValue())) {
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
     * @param btnHtmlNm String
     */
    public static void enableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, true);
        }
    }

    /**
     * getResourceTypeSql
     * @param scrnMsg NSBL0280BMsg
     * @return String
     */
    public static String getResourceTypeSql(NSBL0280BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("    SP.GLBL_CMPY_CD");
        sql.append("   ,SP.EZCANCELFLAG");
        sql.append("   ,SP.PSN_CD");
        sql.append("   ,SP.PSN_LAST_NM||','||PSN_FIRST_NM AS PSN_NM ");
        sql.append(" FROM ");
        sql.append("    S21_PSN   SP ");
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
        sql.append("'    AND DEL_FLG ='");
        sql.append(ZYPConstant.FLG_OFF_N);
        sql.append("'    AND SP.EZCANCELFLAG = '0'");
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
        sql.append("            AND OFS.GLBL_CMPY_CD       = T.GLBL_CMPY_CD");
        sql.append("            AND OFS.TOC_CD             = T.TOC_CD");
        sql.append("            AND T.GLBL_CMPY_CD         = OFRT.GLBL_CMPY_CD");
        sql.append("            AND T.ORG_FUNC_ROLE_TP_CD  = OFRT.ORG_FUNC_ROLE_TP_CD");
        sql.append("            AND OFRT.TECH_MSTR_REQ_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sql.append("            AND OFRT.ACTV_FLG          = '").append(ZYPConstant.FLG_ON_Y).append("'");
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
     * @param scrnMsg NSBL0280BMsg
     * @param index int
     * @return List<Object[]>
     */
    public static List<Object[]> getResourceTypeWhereList(NSBL0280BMsg scrnMsg, int index) {

        String psnCd = null;
        String fullPsnNm = null;
        if (index >= 0) {
            psnCd = scrnMsg.B.no(index).techCd_B.getValue();
            fullPsnNm = scrnMsg.B.no(index).fullPsnNm_B.getValue();
        } else {
            psnCd = scrnMsg.psnCd.getValue();
            fullPsnNm = scrnMsg.fullPsnNm.getValue();
        }

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
     * getSkillTypeSql
     * @param scrnMsg NSBL0280BMsg
     * @return String
     */
    public static String getSkillTypeSql(NSBL0280BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("    SS.GLBL_CMPY_CD");
        sql.append("   ,SS.EZCANCELFLAG");
        sql.append("   ,SS.SVC_SKILL_NUM");
        sql.append("   ,SS.SVC_SKILL_DESC_TXT");
        sql.append("   ,SST.SVC_SKILL_TP_DESC_TXT");
        sql.append("   ,SST.SVC_SKILL_TP_CD");
        sql.append(" FROM ");
        sql.append("    SVC_SKILL    SS ");
        sql.append("  , SVC_SKILL_TP SST ");
        sql.append(" WHERE");
        sql.append("    SS.GLBL_CMPY_CD = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("' ");
        sql.append("     AND SS.EFF_FROM_DT                 <= '");
        sql.append(scrnMsg.slsDt.getValue());
        sql.append("'    AND NVL(SS.EFF_THRU_DT, '99991231') >= '");
        sql.append(scrnMsg.slsDt.getValue());
        sql.append("'    AND SS.EZCANCELFLAG                 = '0'");
        sql.append("     AND SS.GLBL_CMPY_CD                 = SST.GLBL_CMPY_CD");
        sql.append("     AND SS.SVC_SKILL_TP_CD              = SST.SVC_SKILL_TP_CD");
        sql.append("     AND SST.EZCANCELFLAG                = '0' ");
        return sql.toString();
    }

    /**
     * getSkillTypeWhereList
     * @param scrnMsg NSBL0280BMsg
     * @param index int
     * @return List<Object[]>
     */
    public static List<Object[]> getSkillTypeWhereList(NSBL0280BMsg scrnMsg, int index) {

        String svcSkillNum = null;
        String svcSkillDescTxt = null;
        if (index >= 0) {
            svcSkillNum = scrnMsg.A.no(index).svcSkillNum_A.getValue();
            svcSkillDescTxt = scrnMsg.A.no(index).svcSkillDescTxt_A.getValue();
        } else {
            svcSkillNum = scrnMsg.svcSkillNum.getValue();
            svcSkillDescTxt = scrnMsg.svcSkillDescTxt.getValue();
        }

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Skill Number";
        h0[WLIST_OBJ_ID] = "SVC_SKILL_NUM";
        if (svcSkillNum != null) {
            h0[WLIST_OBJ_VALUE] = svcSkillNum;
        } else {
            h0[WLIST_OBJ_VALUE] = "";
        }
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Skill Description";
        h1[WLIST_OBJ_ID] = "SVC_SKILL_DESC_TXT";
        if (svcSkillDescTxt != null) {
            h1[WLIST_OBJ_VALUE] = svcSkillDescTxt;
        } else {
            h1[WLIST_OBJ_VALUE] = "";
        }
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    /**
     * getSkillTypeColumnList
     * @return List<Object[]>
     */
    public static List<Object[]> getSkillTypeColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Skill Number";
        c0[CLIST_OBJ_ID] = "SVC_SKILL_NUM";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_SKILL_NUM);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Skill Description";
        c1[CLIST_OBJ_ID] = "SVC_SKILL_DESC_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_SKILL_DESC_TXT);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Skill Type Description";
        c2[CLIST_OBJ_ID] = "SVC_SKILL_TP_DESC_TXT";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_SKILL_DESC_TXT);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c2);

        return columnList;
    }

    /**
     * getSkillTypeSortConditionList
     * @return List<Object[]>
     */
    public static List<Object[]> getSkillTypeSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "SVC_SKILL_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        return sortConditionList;
    }

}
