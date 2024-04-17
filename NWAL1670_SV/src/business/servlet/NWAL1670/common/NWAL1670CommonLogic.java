/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1670.common;

import static business.servlet.NWAL1670.constant.NWAL1670Constant.BIZ_ID;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_ADD_ATTR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_ADD_MMBR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_ADD_TEAM;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_APL;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_APR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_RST;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_CPY_TEAM;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_DEL_ATTR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_DEL_MMBR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_DEL_TEAM;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_OPEN_WIN_ATTR;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_OPEN_WIN_NMAL2570;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.BTN_SEARCH;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.FUNC_ID_EDT;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.FUNC_ID_INQ;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.PRM_DISP_HIRARCHEY_ACCT;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.PRM_LENGTH_NMAL2570;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.PRM_LENGTH_NMAL6050;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.PRM_LENGTH_NMAL6760;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.SCREEN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1670.NWAL1670BMsg;
import business.servlet.NWAL1670.NWAL1670_BBMsg;
import business.servlet.NWAL1670.NWAL1670_CBMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma          Create          N/A
 *</pre>
 */
public class NWAL1670CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        activateButtons(handler, scrnMsg, functionList);
        setRowColors(scrnMsg);
        controlScreenFieldsForInit(scrnMsg);
    }

    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     * @param functionList List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, NWAL1670BMsg scrnMsg, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 0, null);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
            setButtonEnabledForInit(handler, scrnMsg, false);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
            setButtonEnabledForInit(handler, scrnMsg, true);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
            setButtonEnabledForInit(handler, scrnMsg, true);
        }
    }

    /**
     * setButtonEnabledForInit
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     * @param isEnabled boolean
     */
    private static void setButtonEnabledForInit(EZDCommonHandler handler, NWAL1670BMsg scrnMsg, boolean isEnabled) {

        handler.setButtonEnabled(BTN_SEARCH, isEnabled);
        handler.setButtonEnabled(BTN_ADD_TEAM, isEnabled);
        handler.setButtonEnabled(BTN_DEL_TEAM, false);
        handler.setButtonEnabled(BTN_CPY_TEAM, false);
        handler.setButtonEnabled(BTN_ADD_MMBR, false);
        handler.setButtonEnabled(BTN_DEL_MMBR, false);
        handler.setButtonEnabled(BTN_ADD_ATTR, false);
        handler.setButtonEnabled(BTN_DEL_ATTR, false);
    }

    /**
     * setButtonEnabledCngTeam
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     * @param isEnabled boolean
     */
    private static void setButtonEnabledCngTeam(EZDCommonHandler handler, NWAL1670BMsg scrnMsg, boolean isEnabled) {

        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_ADD_TEAM, true);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_DEL_TEAM, true);
            handler.setButtonEnabled(BTN_CPY_TEAM, true);
            handler.setButtonEnabled(BTN_ADD_MMBR, isEnabled);
            handler.setButtonEnabled(BTN_ADD_ATTR, isEnabled);
        } else {
            handler.setButtonEnabled(BTN_DEL_TEAM, false);
            handler.setButtonEnabled(BTN_CPY_TEAM, false);
            handler.setButtonEnabled(BTN_ADD_MMBR, false);
            handler.setButtonEnabled(BTN_ADD_ATTR, false);
        }
        handler.setButtonEnabled(BTN_DEL_MMBR, false);
        handler.setButtonEnabled(BTN_DEL_ATTR, false);
    }

    /**
     * setButtonEnabledCngDtl
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    private static void setButtonEnabledCngDtl(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_ADD_TEAM, true);
        handler.setButtonEnabled(BTN_DEL_TEAM, true);
        handler.setButtonEnabled(BTN_CPY_TEAM, true);
        handler.setButtonEnabled(BTN_ADD_MMBR, true);
        handler.setButtonEnabled(BTN_ADD_ATTR, true);
        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_DEL_MMBR, true);
        } else {
            handler.setButtonEnabled(BTN_DEL_MMBR, false);
        }
        if (scrnMsg.C.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_DEL_ATTR, true);
        } else {
            handler.setButtonEnabled(BTN_DEL_ATTR, false);
        }
    }

    /**
     * controlScreenFieldsForInit
     * @param scrnMsg NWAL1670BMsg
     */
    private static void controlScreenFieldsForInit(NWAL1670BMsg scrnMsg) {
        scrnMsg.ordTeamAttrbTpCd.setInputProtected(true);
    }

    /**
     * controlScreenFieldsMemberList
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    private static void controlScreenFieldsForMemberList(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        if (scrnMsg.B.getValidCount() <= 0 || !hasValue(scrnMsg.xxRadioBtn)) {
            return;
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1670_BBMsg bbMsg = scrnMsg.B.no(i);
            bbMsg.fullPsnNm_B.setInputProtected(true);

            if (hasValue(bbMsg.ordTeamMstrDtlPk_B)) {
                bbMsg.ordTeamAttrbValTxt_B.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPEN_WIN_NMAL2570, i, false);
            } else {
                bbMsg.ordTeamAttrbValTxt_B.setInputProtected(false);
                handler.setButtonEnabled(BTN_OPEN_WIN_NMAL2570, i, true);
            }
        }
    }

    /**
     * controlScreenFieldsForAttributeList
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    private static void controlScreenFieldsForAttributeList(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        scrnMsg.ordTeamAttrbTpCd.setInputProtected(false);

        if (scrnMsg.C.getValidCount() <= 0 || !hasValue(scrnMsg.xxRadioBtn)) {
            return;
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL1670_CBMsg cbMsg = scrnMsg.C.no(i);
            cbMsg.dsAcctNm_C.setInputProtected(true);

            if (hasValue(cbMsg.ordTeamMstrDtlPk_C)) {
                cbMsg.ordTeamAttrbTpCd_C.setInputProtected(true);
                cbMsg.ordTeamAttrbValTxt_C.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPEN_WIN_ATTR, i, false);
            } else {
                cbMsg.ordTeamAttrbTpCd_C.setInputProtected(false);
                cbMsg.ordTeamAttrbValTxt_C.setInputProtected(false);
                handler.setButtonEnabled(BTN_OPEN_WIN_ATTR, i, true);
            }
        }
    }

    /**
     * controlScreenFieldsForSearch
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static void controlScreenFieldsForSearch(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        setButtonEnabledCngTeam(handler, scrnMsg, false);
        controlScreenFieldsForInit(scrnMsg);

    }

    /**
     * controlScreenFieldsForAddTeam
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static void controlScreenFieldsForAddTeam(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        setButtonEnabledCngTeam(handler, scrnMsg, true);
        controlScreenFieldsForMemberList(handler, scrnMsg);
        controlScreenFieldsForAttributeList(handler, scrnMsg);
    }

    /**
     * controlScreenFieldsForAddMember
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static void controlScreenFieldsChgMember(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        setButtonEnabledCngDtl(handler, scrnMsg);
        controlScreenFieldsForMemberList(handler, scrnMsg);
    }

    /**
     * controlScreenFieldsForAddAttr
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static void controlScreenFieldsChgAttrb(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        setButtonEnabledCngDtl(handler, scrnMsg);
        controlScreenFieldsForAttributeList(handler, scrnMsg);
    }

    /**
     * controlScreenFieldsForDelTeam
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static void controlScreenFieldsForDelTeam(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        setButtonEnabledCngTeam(handler, scrnMsg, false);
        controlScreenFieldsForInit(scrnMsg);
    }

    /**
     * controlScreenFieldsForOnClickTeam
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1670BMsg
     */
    public static void controlScreenFieldsForOnClickTeam(EZDCommonHandler handler, NWAL1670BMsg scrnMsg) {

        setButtonEnabledCngDtl(handler, scrnMsg);
        controlScreenFieldsForMemberList(handler, scrnMsg);
        controlScreenFieldsForAttributeList(handler, scrnMsg);
    }

    /**
     * setRowColors
     * @param scrnMsg NWAL1670BMsg
     */
    private static void setRowColors(NWAL1670BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);
        tblColor.setAlternateRowsBG("C", scrnMsg.C);
    }

    /**
     * addCheckItem
     * @param scrnMsg NWAL1670BMsg
     * @param isSubmit boolean
     */
    public static void addCheckItem(NWAL1670BMsg scrnMsg, boolean isSubmit) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ordTeamMstrNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ordZnCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
        }

        if (isSubmit) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).ordTeamAttrbValTxt_B);
            }
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.C.no(i).ordTeamAttrbTpCd_C);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).ordTeamAttrbValTxt_C);
            }
        }
    }

    /**
     * clearParam
     * @param scrnMsg NWAL1670BMsg
     */
    public static void clearParam(NWAL1670BMsg scrnMsg) {

        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
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
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        scrnMsg.xxPopPrm_24.clear();
    }

    /**
     * setParamForResourceLookup
     * @param scrnMsg NWAL1670BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceLookup(NWAL1670BMsg scrnMsg) {

        int index = scrnMsg.xxNum_PO.getValueInt();

        setValue(scrnMsg.xxPopPrm_02, scrnMsg.B.no(index).ordTeamAttrbValTxt_B);

        Object[] params = new Object[PRM_LENGTH_NMAL2570];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;

        return params;
    }

    /**
     * setParamForAccountPopup
     * @param scrnMsg NWAL1670BMsg
     * @return Object[]
     */
    public static Object[] setParamForAccountPopup(NWAL1670BMsg scrnMsg) {

        int index = scrnMsg.xxNum_PO.getValueInt();

        setValue(scrnMsg.xxPopPrm_01, scrnMsg.C.no(index).ordTeamAttrbValTxt_C);
        setValue(scrnMsg.xxPopPrm_13, PRM_DISP_HIRARCHEY_ACCT);

        Object[] params = new Object[PRM_LENGTH_NMAL6760];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        params[i++] = scrnMsg.xxPopPrm_15;
        params[i++] = scrnMsg.xxPopPrm_16;
        params[i++] = scrnMsg.xxPopPrm_17;
        params[i++] = scrnMsg.xxPopPrm_18;
        params[i++] = scrnMsg.xxPopPrm_19;
        params[i++] = scrnMsg.xxPopPrm_20;
        params[i++] = scrnMsg.xxPopPrm_21;
        params[i++] = scrnMsg.xxPopPrm_22;
        params[i++] = scrnMsg.xxPopPrm_23;
        params[i++] = scrnMsg.xxPopPrm_24;

        return params;
    }

    /**
     * setParamForCommonCodePopup
     * @param scrnMsg NWAL1670BMsg
     * @param isLineBizTp boolean
     * @return Object[]
     */
    public static Object[] setParamForCommonCodePopup(NWAL1670BMsg scrnMsg, boolean isLineBizTp) {

        int index = scrnMsg.xxNum_PO.getValueInt();

        if (isLineBizTp) {
            setValue(scrnMsg.xxPopPrm_01, "LINE_BIZ_TP");
            setValue(scrnMsg.xxPopPrm_02, "LINE_BIZ_TP_CD");
            setValue(scrnMsg.xxPopPrm_03, "LINE_BIZ_TP_DESC_TXT");
            setValue(scrnMsg.xxPopPrm_04, "LINE_BIZ_TP_SORT_NUM");
            setValue(scrnMsg.xxPopPrm_05, "Line of Business Search");
            setValue(scrnMsg.xxPopPrm_06, "Line of Business Code");
            setValue(scrnMsg.xxPopPrm_07, "Line of Business Name");
            setValue(scrnMsg.xxPopPrm_08, "Line of Business Code");
            setValue(scrnMsg.xxPopPrm_09, "Line of Business Name");
        } else {
            setValue(scrnMsg.xxPopPrm_01, "COA_BR");
            setValue(scrnMsg.xxPopPrm_02, "COA_BR_CD");
            setValue(scrnMsg.xxPopPrm_03, "COA_BR_DESC_TXT");
            setValue(scrnMsg.xxPopPrm_04, "COA_BR_SORT_NUM");
            setValue(scrnMsg.xxPopPrm_05, "COA Branch Search");
            setValue(scrnMsg.xxPopPrm_06, "COA Branch Code");
            setValue(scrnMsg.xxPopPrm_07, "COA Branch Name");
            setValue(scrnMsg.xxPopPrm_08, "COA Branch Code");
            setValue(scrnMsg.xxPopPrm_09, "COA Branch Name");
        }
        setValue(scrnMsg.xxPopPrm_10, scrnMsg.C.no(index).ordTeamAttrbValTxt_C);

        Object[] params = new Object[PRM_LENGTH_NMAL6050];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;

        return params;
    }
}
