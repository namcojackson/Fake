/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0290.common;

import static business.servlet.NSBL0290.constant.NSBL0290Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSBL0290.NSBL0290CMsg;
import business.servlet.NSBL0290.NSBL0290BMsg;

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
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 * 2016/10/21   Hitachi         K.Kojima        Update          QC#15137
 * 2017/01/25   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0290CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSBL0290CMsg
     */
    public static NSBL0290CMsg setRequestData_UpdateCommon() {

        NSBL0290CMsg bizMsg = new NSBL0290CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSBL0290CMsg
     */
    public static NSBL0290CMsg setRequestData_SearchCommon() {

        NSBL0290CMsg bizMsg = new NSBL0290CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        screenControlProcess(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcSkillTpCd_S);
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void clearInitialize(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        scrnMsg.svcSkillTpCd_S.setInputProtected(false);
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.svcSkillNm_H.setInputProtected(false);
        scrnMsg.svcSkillDescTxt_H.setInputProtected(false);
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        screenControlProcess(handler, scrnMsg);
    }

    /**
     * processAdd
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     */
    public static void processAdd(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxBtnFlg.getValue())) {
            handler.setButtonEnabled(INSERT_ROW[0], false);
        }

        enableButtons(handler, DELETE_ROW[0]);
        initAppFracDigit(scrnMsg);
        setRowColors(scrnMsg);
        checkBoxControl(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).xxChkBox);
    }

    public static void checkBoxControl(NSBL0290BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcSkillNum.getValue())) {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
            }
        }
    }

    /**
     * processDelete
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     */
    public static void processDelete(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxBtnFlg.getValue())) {
            handler.setButtonEnabled(INSERT_ROW[0], true);
        }

        deleteButtonControl(handler, scrnMsg);
    }

    private static void deleteButtonControl(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        int deleteCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcSkillNum)) {
                deleteCnt++;
            }
        }

        if (deleteCnt == 0) {
            handler.setButtonEnabled(DELETE_ROW[0], false);
        }
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.svcSkillTpDescTxt.setInputProtected(true);
        scrnMsg.svcSkillLvlGrpCd_S.setInputProtected(true);
        scrnMsg.effFromDt_H.setInputProtected(true);
        scrnMsg.effThruDt_H.setInputProtected(true);

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN, SEARCH, INSERT_ROW, DELETE_ROW);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        screenControlByFuncId(handler, scrnMsg);
    }

    /**
     * setListInactive
     * @param scrnMsg NSBL0290BMsg
     */
    public static void setListInactive(NSBL0290BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillNum.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).svcAliasRate.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt.setInputProtected(true);
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     */
    public static void submitScreenControl(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        scrnMsg.svcSkillTpCd_S.setInputProtected(true);
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.svcSkillNm_H.setInputProtected(true);
        scrnMsg.svcSkillDescTxt_H.setInputProtected(true);
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.svcSkillTpDescTxt.setInputProtected(false);
        scrnMsg.svcSkillLvlGrpCd_S.setInputProtected(false);
        scrnMsg.effFromDt_H.setInputProtected(false);
        scrnMsg.effThruDt_H.setInputProtected(false);

        checkBoxControl(scrnMsg);
        handler.setButtonProperties(SEARCH[0], SEARCH[1], SEARCH[2], BTN_INACTIVE, null);

        deleteButtonControl(handler, scrnMsg);
        initAppFracDigit(scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * searchScreenControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     */
    public static void searchScreenControl(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        scrnMsg.svcSkillTpCd_S.setInputProtected(true);
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.svcSkillNm_H.setInputProtected(true);
        scrnMsg.svcSkillDescTxt_H.setInputProtected(true);
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.svcSkillTpDescTxt.setInputProtected(false);
        scrnMsg.svcSkillLvlGrpCd_S.setInputProtected(false);
        scrnMsg.effFromDt_H.setInputProtected(false);
        scrnMsg.effThruDt_H.setInputProtected(false);

        checkBoxControl(scrnMsg);
        deleteButtonControl(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        handler.setButtonEnabled(SEARCH[0], false);
        // START 2017/01/25 K.Ochiai [QC#16331,ADD]
        handler.setButtonEnabled(RESET[0], true);
        // END 2017/01/25 K.Ochiai [QC#16331,ADD]
        handler.setButtonEnabled(SUBMIT[0], true);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxBtnFlg.getValue())) {
            handler.setButtonEnabled(INSERT_ROW[0], true);

        }
        initAppFracDigit(scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * addCheckItem
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     */
    public static void addCheckItem(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcSkillTpCd_S);
        // START 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.addCheckItem(scrnMsg.svcSkillNm_H);
        scrnMsg.addCheckItem(scrnMsg.svcSkillDescTxt_H);
        // END 2016/10/21 K.Kojima [QC#15137,ADD]
        scrnMsg.addCheckItem(scrnMsg.svcSkillTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.svcSkillLvlGrpCd_S);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillNm);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillDescTxt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAliasRate);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt);
        }
    }

    /**
     * initAppFracDigit
     * @param scrnMsg NSBL0290BMsg
     */
    public static void initAppFracDigit(NSBL0290BMsg scrnMsg) {

        for (int index = 0; index < scrnMsg.A.length(); index++) {
            scrnMsg.A.no(index).svcAliasRate.setAppFracDigit(2);
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0290BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSBL0290). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNC_ID_T020) || funcIdList.contains(FUNC_ID_T030) || funcIdList.contains(FUNC_ID_T040)) {
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH[0]);
        } else if (funcIdList.contains(FUNC_ID_T010)) {
            enableButtons(handler, CLEAR[0], RETURN[0], SEARCH[0]);
            setProtected(handler, scrnMsg);
        } else {
            enableButtons(handler, RETURN[0]);
        }
    }

    /**
     * setProtected
     * @param scrnMsg NSBL0290BMsg
     */
    public static void setProtected(EZDCommonHandler handler, NSBL0290BMsg scrnMsg) {

        handler.setButtonEnabled(SUBMIT[0], false);
        handler.setButtonEnabled(INSERT_ROW[0], false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillNm.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).svcAliasRate.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt.setInputProtected(true);
        }
    }

    private static void setRowColors(NSBL0290BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.length() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
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

}
