/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.db.TRTY_RULE_TPTMsg;
import business.servlet.NMAL2610.NMAL2610BMsg;
import business.servlet.NMAL2610.NMAL2610_ABMsg;
import business.servlet.NMAL2610.NMAL2610_BBMsg;
import business.servlet.NMAL2610.NMAL2610_CBMsg;
import business.servlet.NMAL2610.NMAL2610_DBMsg;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Resource Search NMAL24000CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/10   Fujitsu         J.Sakamoto      Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/02   Fujitsu         M.suzuki        Update          CSA-QC#4553
 * 2016/03/03   Fujitsu         C.Tanaka        Create          QC#4551
 * 2016/06/24   Hitachi         A.Kohinata      Update          QC#10276
 * 2016/08/29   SRAA            Y.Chen          Update          QC#7966
 * 2016/10/06   Hitachi         Y.Takeno        Update          QC#13431
 * 2017/03/02   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#53019
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#54222
 *</pre>
 */

public class NMAL2610CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2610BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(NMAL2610Constant.SCREEN_ID);
        initCommonButton(handler);
        initButton(handler);
        controlScreenFields(handler, scrnMsg);
        // 2016/06/24 QC#10276 Add Start
        controlOrgLink(scrnMsg);
        // 2016/06/24 QC#10276 Add End
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonEnabled(NMAL2610Constant.BTN_SEARCH[0], true);
        handler.setButtonEnabled(NMAL2610Constant.BTN_COPY_TERRITORY[0], true);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2610BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        controlScreenDetailFields(handler, scrnMsg);
        controlScreenChildFields(handler, scrnMsg);
        // Add Start 2017/03/08 QC#15878
        if (scrnMsg.xxDplyTab.getValue().equals(NMAL2610Constant.TAB_TERRITORY_RULES)) {
            controlScreenTrtyRuleFields(handler, scrnMsg);
        }
        // Add End 2017/03/08 QC#15878
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2610BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        // QC#13431
        controlAttachmentButton(handler, scrnMsg);

        setIniOrgStatDate(scrnMsg);
        // QC#7966
        // scrnMsg.xxChkBox_H1.clear();
        scrnMsg.xxChkBox_F1.clear();
        scrnMsg.xxChkBox_F2.clear();
        scrnMsg.xxChkBox_F3.clear();
        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_P1);

    }

    // QC#13431
    /**
     * <pre>
     * controlAttachmentButton
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void controlAttachmentButton(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        // QC#13431
        if (ZYPCommonFunc.hasValue(scrnMsg.orgCd_H1)) {
            handler.setButtonEnabled(NMAL2610Constant.BTN_ATTACHMENTS, true);
        } else {
            handler.setButtonEnabled(NMAL2610Constant.BTN_ATTACHMENTS, false);
        }
    }

    
    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NMAL2610BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(NMAL2610Constant.SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(NMAL2610Constant.SCREEN_ID);
    }

    /**
     * <pre>
     * Common protect control
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NPAL1030BMsg
     */
    public static void commonControl(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        initCommonButton(handler);
        // checkAuth(handler, scrnMsg);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_SAVE[0], NMAL2610Constant.BTN_CMN_BTN_SAVE[1], NMAL2610Constant.BTN_CMN_BTN_SAVE[2], 0, null);
        //handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2610Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2610Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_APPLY[0], NMAL2610Constant.BTN_CMN_BTN_APPLY[1], NMAL2610Constant.BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_APPROVE[0], NMAL2610Constant.BTN_CMN_BTN_APPROVE[1], NMAL2610Constant.BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_REJECT[0], NMAL2610Constant.BTN_CMN_BTN_REJECT[1], NMAL2610Constant.BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_DOWNLOAD[0], NMAL2610Constant.BTN_CMN_BTN_DOWNLOAD[1], NMAL2610Constant.BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_DELETE[0], NMAL2610Constant.BTN_CMN_BTN_DELETE[1], NMAL2610Constant.BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_CLEAR[0], NMAL2610Constant.BTN_CMN_BTN_CLEAR[1], NMAL2610Constant.BTN_CMN_BTN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_RESET[0], NMAL2610Constant.BTN_CMN_BTN_RESET[1], NMAL2610Constant.BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_RETURN[0], NMAL2610Constant.BTN_CMN_BTN_RETURN[1], NMAL2610Constant.BTN_CMN_BTN_RETURN[2], 1, null);
    }

// QC#7966
//    /**
//     * <pre>
//     * The state of the screen item is set.
//     * </pre>
//     * @param handler EZDCommonHandler
//     */
//    public static void enableLinkButton(EZDCommonHandler handler) {
//        handler.setButtonEnabled(NMAL2610Constant.BTN_FILTER[0], true);
//    }
//
//    /**
//     * <pre>
//     * The state of the screen item is set.
//     * </pre>
//     * @param handler EZDCommonHandler
//     */
//    public static void disableLinkButton(EZDCommonHandler handler) {
//        handler.setButtonEnabled(NMAL2610Constant.BTN_FILTER[0], false);
//    }

    /**
     * The method explanation: control organization link enable or
     * disable
     * @param scrnMsg NMAL2610BMsg
     */
    public static void controlOrgLink(NMAL2610BMsg scrnMsg) {

        // 2016/06/24 QC#10276 Mod Start
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgCd_A1)) {
                scrnMsg.A.no(i).orgCd_L1.setInputProtected(false);
                scrnMsg.A.no(i).orgCd_L1.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).orgCd_L1.clear();
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).orgCd_B1)) {
                scrnMsg.B.no(i).orgCd_L2.setInputProtected(false);
                scrnMsg.B.no(i).orgCd_L2.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.B.no(i).orgCd_L2.clear();
            }
        }
        // 2016/06/24 QC#10276 Mod End
    }

    /**
     * The method explanation: control Role pulldown enable or disable
     * @param scrnMsg NMAL2610BMsg
     */
    public static void controlRolePullDown(NMAL2610BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.otherResrcTrtyFlg_H1.getValue())) {
                scrnMsg.D.no(i).acctTeamRoleTpCd_P1.setInputProtected(false);
            } else {
                scrnMsg.D.no(i).acctTeamRoleTpCd_P1.clear();
                scrnMsg.D.no(i).acctTeamRoleTpCd_P1.setInputProtected(true);
            }
        }
    }

    // Add Start 2017/03/02 QC#15878
    /**
     * trtyRuleValCtrl.
     * @param handler EZDCommonHandler
     * @param idx int
     * @param scrnMsg NMAL2610BMsg
     */
    public static void trtyRuleValCtrl(EZDCommonHandler handler, int idx, NMAL2610BMsg scrnMsg) {

        // Input Field Change
        String popupType = getPopupType(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue());

        if (NMAL2610Constant.POPUP_TYPE_NONE.equals(popupType)) {
            handler.setButtonEnabled(NMAL2610Constant.BTN_OPENWIN_TARGET_FROM, idx, false);
            handler.setButtonEnabled(NMAL2610Constant.BTN_OPENWIN_TARGET_TO, idx, false);
        } else {
            handler.setButtonEnabled(NMAL2610Constant.BTN_OPENWIN_TARGET_FROM, idx, true);
            handler.setButtonEnabled(NMAL2610Constant.BTN_OPENWIN_TARGET_TO, idx, true);
        }
    }

    private static String getPopupType(String attrbCd) {
        String rtnType = "";
        if (TRTY_RULE_TP.STATE.equals(attrbCd)) {
            rtnType = NMAL2610Constant.POPUP_TYPE_GEN;
        } else if (TRTY_RULE_TP.POSTAL_CODE.equals(attrbCd)) {
            rtnType = NMAL2610Constant.POPUP_TYPE_GEN;
            // Del Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_TP.COUNTY.equals(attrbCd)) {
        //    rtnType = NMAL2610Constant.POPUP_TYPE_GEN;
        //} else if (TRTY_RULE_TP.CITY.equals(attrbCd)) {
        //    rtnType = NMAL2610Constant.POPUP_TYPE_GEN;
            // Del End 2018/06/01 QC#24293
            // Mod Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_TP.ACCOUNT_NAME.equals(attrbCd)) {
        } else if (TRTY_RULE_TP.ACCOUNT_NUMBER.equals(attrbCd)) {
            // Mod End 2018/06/01 QC#24293
            rtnType = NMAL2610Constant.POPUP_TYPE_ACCT;
        } else if (TRTY_RULE_TP.LOCATION_NUMBER.equals(attrbCd)) {
            rtnType = NMAL2610Constant.POPUP_TYPE_ACCT;
        } else if (TRTY_RULE_TP.ACCOUNT_CLASSIFICATION.equals(attrbCd)) {
            rtnType = NMAL2610Constant.POPUP_TYPE_CMN;
        } else if (TRTY_RULE_TP.CUSTOMERGROUP.equals(attrbCd)) {
            rtnType = NMAL2610Constant.POPUP_TYPE_CMN;
        } else if (TRTY_RULE_TP.SIC_CODE.equals(attrbCd)) {
            rtnType = NMAL2610Constant.POPUP_TYPE_NONE;
        }

        return rtnType;
    }
    // Add End 2017/03/02 QC#15878

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2610BMsg
     * @return Object[]
     */
    public static Object[] setParamForHeaderTerritorySearchPopup(NMAL2610BMsg scrnMsg) {

        Object[] params = new Object[NMAL2610Constant.POP_PAR_8];

        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2610Constant.OPEN_WIN_TERRITORY_LOOKUP);

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgNm_H1);
        }

        // Business Area
        params[NMAL2610Constant.POP_PAR_0] = scrnMsg.xxPopPrm_0;
        // Territory Name
        params[NMAL2610Constant.POP_PAR_1] = scrnMsg.xxPopPrm_1;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2610BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForDetailTerritorySearchPopup(NMAL2610BMsg scrnMsg, int i) {

        Object[] params = new Object[NMAL2610Constant.POP_PAR_8];

        initParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2610Constant.OPEN_WIN_TERRITORY_LOOKUP_DETAIL);

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_P1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgNm_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.A.no(i).orgNm_A1);
        }

        // Business Area
        params[NMAL2610Constant.POP_PAR_0] = scrnMsg.xxPopPrm_0;
        // Territory Name
        params[NMAL2610Constant.POP_PAR_1] = scrnMsg.xxPopPrm_1;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2610BMsg
     * @param i int
     * @return Object[]
     */
    public static Object[] setParamForResourceSearchPopup(NMAL2610BMsg scrnMsg, int i) {

        Object[] params = new Object[NMAL2610Constant.POP_PAR_3];
        initParam(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).psnNum_D1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.D.no(i).psnNum_D1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).psnCd_D1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.D.no(i).psnCd_D1);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_2, ZYPConstant.FLG_ON_Y);

        // Resource#
        params[NMAL2610Constant.POP_PAR_0] = scrnMsg.xxPopPrm_0;
        // Employee ID
        params[NMAL2610Constant.POP_PAR_1] = scrnMsg.xxPopPrm_1;

        params[NMAL2610Constant.POP_PAR_2] = scrnMsg.xxPopPrm_2;

        return params;

    }

    // QC#13431
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2610BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setParamForAttachmentPopup(NMAL2610BMsg scrnMsg, String glblCmpyCd) {
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
     * @param scrnMsg NMAL2610BMsg
     */
    public static void initParam(NMAL2610BMsg scrnMsg) {

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

    }

    /**
     * addTabTerritoryCheckItem
     * @param scrnMsg NMAL2610BMsg
     */
    public static void addTabTerritoryCheckItem(NMAL2610BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
    }

    /**
     * addTabRulesCheckItem
     * @param scrnMsg NMAL2610BMsg
     */
    public static void addTabRulesCheckItem(NMAL2610BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C1);
        }
    }

    /**
     * addTabResourceCheckItem
     * @param scrnMsg NMAL2610BMsg
     */
    public static void addTabResourceCheckItem(NMAL2610BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_D1);
        }
    }

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
     * <pre>
     * clear mandantory check for Header
     * </pre>
     * @param scrnMsg NMAL2610BMsg
     */
    public static void clearMandantoryForHeader(NMAL2610BMsg scrnMsg) {
        // ## Header ##
        // Business Area
        if (scrnMsg.bizAreaOrgCd_P1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("bizAreaOrgCd_P1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.bizAreaOrgCd_P1.clearErrorInfo();
            }
        }

        // Territory Name
        if (scrnMsg.orgNm_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("orgNm_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.orgNm_H1.clearErrorInfo();
            }
        }

        // Start Date
        if (scrnMsg.effFromDt_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt_H1");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.effFromDt_H1.clearErrorInfo();
            }
        }

        // ## Territory ##
        // Start Date
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).orgNm_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("orgNm_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).orgNm_A1.clearErrorInfo();
                }
            }
            if (scrnMsg.A.no(i).effFromDt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_A1.clearErrorInfo();
                }
            }
        }

        // ## Territory Rule ##
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // Rule Type
            if (scrnMsg.C.no(i).trtyRuleTpCd_P1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("trtyRuleTpCd_P1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).trtyRuleTpCd_P1.clearErrorInfo();
                }
            }

            // Operator
            if (scrnMsg.C.no(i).trtyRuleOprdTpCd_P1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("trtyRuleOprdTpCd_P1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).trtyRuleOprdTpCd_P1.clearErrorInfo();
                }
            }

            // Value From
            if (scrnMsg.C.no(i).trtyRuleFromValTxt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("trtyRuleFromValTxt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).trtyRuleFromValTxt_C1.clearErrorInfo();
                }
            }

            // Start Date
            if (scrnMsg.C.no(i).effFromDt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("effFromDt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).effFromDt_C1.clearErrorInfo();
                }
            }
        }

        // ## Territory Rule ##
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            // Start Date
            if (scrnMsg.D.no(i).effFromDt_D1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.D.no(i).getErrorInfo("effFromDt_D1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.D.no(i).effFromDt_D1.clearErrorInfo();
                }
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NMAL2610BMsg
     * @param isCheckAll boolean
     */
    public static void addCheckItem(NMAL2610BMsg scrnMsg, boolean isCheckAll) {
        // 2016/02/12 CSA-QC#2869 Add Start
        scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_P1);
        scrnMsg.addCheckItem(scrnMsg.trtyTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);

        scrnMsg.addCheckItem(scrnMsg.orgTierCd_P1);
        scrnMsg.addCheckItem(scrnMsg.orgDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.trtyGrpTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        // QC#7966
        // scrnMsg.addCheckItem(scrnMsg.orgNm_F1);
        // 2016/02/12 CSA-QC#2869 Add End

        if (isCheckAll || NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NMAL2610_ABMsg acMsg = scrnMsg.A.no(i);
                scrnMsg.addCheckItem(acMsg.xxChkBox_A1);
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(acMsg.orgNm_A1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(acMsg.effFromDt_A1);
                scrnMsg.addCheckItem(acMsg.effThruDt_A1);
            }
            // 2016/03/02 S21_NA#4553 Add Start ------------
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NMAL2610_BBMsg acMsg = scrnMsg.B.no(i);
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(acMsg.orgNm_B1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(acMsg.effFromDt_B1);
                scrnMsg.addCheckItem(acMsg.effThruDt_B1);
            }
            // 2016/03/02 S21_NA#4553 Add End --------------
            scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY);
            scrnMsg.putErrorScreen();
        }

        if (isCheckAll || NMAL2610Constant.TAB_TERRITORY_RULES.equals(scrnMsg.xxDplyTab.getValue())) {
            // QC#7966
            scrnMsg.addCheckItem(scrnMsg.trtyRuleFromValTxt_F1);
            scrnMsg.addCheckItem(scrnMsg.trtyRuleThruValTxt_F1);

            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NMAL2610_CBMsg acMsg = scrnMsg.C.no(i);
                scrnMsg.addCheckItem(acMsg.trtyRuleTpCd_P1);
                scrnMsg.addCheckItem(acMsg.trtyRuleLogicTpCd_P1);
                scrnMsg.addCheckItem(acMsg.trtyRuleFromValTxt_C1);
                scrnMsg.addCheckItem(acMsg.trtyRuleThruValTxt_C1);
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(acMsg.trtyRuleOprdTpCd_P1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(acMsg.effFromDt_C1);
                scrnMsg.addCheckItem(acMsg.effThruDt_C1);
                // 2017/11/16 CSA-QC#20597 Add Start
                // Warning
                // Del Start 2019/12/06 QC#54222
//                if (acMsg.trtyRuleFromValTxt_C1.getErrorCode() == 2) {
                // Del End   2019/12/06 QC#54222
                // Add Start 2019/12/06 QC#54222
                if (acMsg.trtyRuleFromValTxt_C1.getErrorCode() == 1 || acMsg.trtyRuleFromValTxt_C1.getErrorCode() == 2) {
                // Add End   2019/12/06 QC#54222
                    scrnMsg.xxDplyTab_BK.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                }
                // 2017/11/16 CSA-QC#20597 Add End
            }
            scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
            scrnMsg.putErrorScreen();
        }

        if (isCheckAll || NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NMAL2610_DBMsg acMsg = scrnMsg.D.no(i);
                // 2016/03/03 CSA-QC#4545 Add Start
                scrnMsg.addCheckItem(acMsg.xxChkBox_D1);
                scrnMsg.addCheckItem(acMsg.xxPsnNm_D1);
                scrnMsg.addCheckItem(acMsg.psnCd_D1);
                scrnMsg.addCheckItem(acMsg.acctTeamRoleTpCd_P1);
                // 2016/03/03 CSA-QC#4545 Add End
                scrnMsg.addCheckItem(acMsg.effFromDt_D1);
                scrnMsg.addCheckItem(acMsg.effThruDt_D1);
            }
            scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_RESOURCE_ASSIGNE);
            scrnMsg.putErrorScreen();
        }
    }

    /**
     * initialCopyTerritory
     * @param scrnMsg NMAL2610BMsg
     */
    public static void initialCopyTerritory(NMAL2610BMsg scrnMsg) {

        scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);

        scrnMsg.orgDescTxt_H1.setValue(NMAL2610Constant.MESSAGE_KIND_COPY + scrnMsg.orgNm_H1.getValue());
        scrnMsg.orgNm_H1.clear();
        scrnMsg.orgCd_H1.clear();

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount(0);

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).trtyRulePk_X3.clear();
        }

    }

    /**
     * controlScreenContactPointFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2610BMsg
     */
    private static final void controlScreenChildFields(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).effFromDt_B1.setInputProtected(false);
            scrnMsg.B.no(i).effThruDt_B1.setInputProtected(false);
        }
    }

    // Add Start 2017/03/02 QC#15878
    /**
     * controlScreenTrtyRuleFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2610BMsg
     */
    public static final void controlScreenTrtyRuleFields(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            trtyRuleValCtrl(handler, i, scrnMsg);
        }
    }
    // Add End 2017/03/02 QC#15878

    /**
     * setIniOrgStatDate
     * @param scrnMsg NMAL2610BMsg
     */
    public static void setIniOrgStatDate(NMAL2610BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
            // Get Sales Date
            final String slsDt = ZYPDateUtil.getSalesDate();
            // Set Start Date
            scrnMsg.effFromDt_H1.setValue(slsDt);
        }
    }

    /**
     * The method explanation: set default from date
     * @param scrnMsg NMAL2610BMsg
     */
    public static void setTerritoryStartDate(NMAL2610BMsg scrnMsg) {

        // Get Sales Date
        final String slsDt = ZYPDateUtil.getSalesDate();

        // Set Start Date
        scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).effFromDt_A1.setValue(slsDt);
    }

    /**
     * The method explanation: set default from date
     * @param scrnMsg NMAL2610BMsg
     */
    public static void setRulesStartDate(NMAL2610BMsg scrnMsg) {

        // Get Sales Date
        final String slsDt = ZYPDateUtil.getSalesDate();

        // Set Start Date
        scrnMsg.C.no(scrnMsg.C.getValidCount() - 1).effFromDt_C1.setValue(slsDt);
    }

    /**
     * The method explanation: set default from date
     * @param scrnMsg NMAL2610BMsg
     */
    public static void setResourceStartDate(NMAL2610BMsg scrnMsg) {

        // Get Sales Date
        final String slsDt = ZYPDateUtil.getSalesDate();

        // Set Start Date
        scrnMsg.D.no(scrnMsg.D.getValidCount() - 1).effFromDt_D1.setValue(slsDt);
    }

    // Add Start 2017/03/02 QC#15878
    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL2610BMsg
     * @param idx          index of scrnMsg.A
     * @param glblCmpyCd     GlobalCompanyCode
     * @return Popup Parameter
     */
    public static final Object[] createPopPrm(NMAL2610BMsg scrnMsg, int idx, String glblCmpyCd) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (TRTY_RULE_TP.STATE.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())
                || TRTY_RULE_TP.POSTAL_CODE.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())
                // Mod Start 2018/06/01 QC#24293
                //|| TRTY_RULE_TP.COUNTY.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())
                //|| TRTY_RULE_TP.CITY.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
                ) {
            // Mod End 2018/06/01 QC#24293
            scrnMsg.xxScrEventNm.setValue("NWAL1130");
            return createPopPrmForNWAL1130(scrnMsg, glblCmpyCd, idx);
            // Mod Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_TP.ACCOUNT_NAME.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())
        } else if (TRTY_RULE_TP.ACCOUNT_NUMBER.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())
                // Mod End 2018/06/01 QC#24293
                || TRTY_RULE_TP.LOCATION_NUMBER.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            scrnMsg.xxScrEventNm.setValue("NMAL6760");
            return createPopPrmForNMAL6760(scrnMsg, glblCmpyCd, idx);
        } else if (TRTY_RULE_TP.ACCOUNT_CLASSIFICATION.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())
                || TRTY_RULE_TP.CUSTOMERGROUP.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            scrnMsg.xxScrEventNm.setValue("NMAL6050");
            return createPopPrmForNMAL6050(scrnMsg, glblCmpyCd, idx);
        }

        return null;
    }
    public static Object[] createPopPrmForNWAL1130(NMAL2610BMsg scrnMsg, String glblCmpyCd, int idx) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] params = new Object[7];
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG = '0' ");
        baseSql.append("AND C.CNTY_PK = R.CNTY_PK ");

        List<Object[]> whereList = new ArrayList<Object[]>();
        TRTY_RULE_TPTMsg tMsg = srchTrtyRuleTp(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue(), glblCmpyCd);
        String labelName = "";
        String dbColumnName = "";
        String paramValue = setParamValue(//
                scrnMsg.C.no(idx).trtyRuleFromValTxt_C1.getValue(), //
                scrnMsg.C.no(idx).trtyRuleThruValTxt_C1.getValue(), //
                scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue(), //
                scrnMsg.xxBtnFlg.getValue());
        if (null != tMsg) {
            labelName = tMsg.trtyRuleTpDescTxt.getValue();
            dbColumnName = tMsg.srchColNm.getValue();
        }
        addWhereCondition(whereList, labelName, dbColumnName, paramValue, ZYPConstant.FLG_ON_Y);

        List<Object[]> columnList = new ArrayList<Object[]>();
        addDisplayColumn(columnList, "City", "CTY_ADDR", BigDecimal.valueOf(25), "Y");
        addDisplayColumn(columnList, "State", "ST_CD", BigDecimal.valueOf(5), "Y");
        addDisplayColumn(columnList, "Postal Code", "POST_CD", BigDecimal.valueOf(10), "Y");
        addDisplayColumn(columnList, "County", "CNTY_NM", BigDecimal.valueOf(30), "Y");
        
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        addSortCondition(sortConditionList, "CTY_ADDR", "ASC");
        addSortCondition(sortConditionList, "ST_CD", "ASC");
        addSortCondition(sortConditionList, "POST_CD", "ASC");
        addSortCondition(sortConditionList, "CNTY_NM", "ASC");

        params[0] = "";
        params[1] = "Address Lookup Popup";
        params[2] = baseSql.toString();
        params[3] = whereList;
        params[4] = columnList;
        params[5] = sortConditionList;
        params[6] = scrnMsg.R;
        
        return params;
    }

    public static TRTY_RULE_TPTMsg srchTrtyRuleTp(String trtyRuleTpCd, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(trtyRuleTpCd) || !ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        }

        TRTY_RULE_TPTMsg tMsg = new TRTY_RULE_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.trtyRuleTpCd.setValue(trtyRuleTpCd);
        return (TRTY_RULE_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
    }

    public static String setParamValue(String fromRuleValue, String thruRuleValue, String trtyRuleTpCd, String btnFlg) {

        String setValue = null;

        if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(btnFlg)) {
            setValue = fromRuleValue;
        } else {
            setValue = thruRuleValue;
        }

        if (!ZYPCommonFunc.hasValue(setValue)) {
            return "";
        }

        if (TRTY_RULE_TP.POSTAL_CODE.equals(trtyRuleTpCd) && setValue.length() >= 6) {
            setValue = setValue.substring(0, 5);
            setValue += "%";
        }

        return setValue;
    }

    private static void addWhereCondition(List<Object[]> whereList, String labelName, String dbColumnName, String initValue, String likeConditionFlag){
        Object[] whereArray= new Object[4];
        whereArray[0] = labelName;
        whereArray[1] = dbColumnName;
        whereArray[2] = initValue;
        whereArray[3] = likeConditionFlag;
        whereList.add(whereArray);
    }
    
    private static void addDisplayColumn(List<Object[]> columnList, String labelName, String dbColumnName, BigDecimal displaySize, String linkFlag){
        Object[] columnArray = new Object[4];
        columnArray[0] = labelName;
        columnArray[1] = dbColumnName;
        columnArray[2] = displaySize;
        columnArray[3] = linkFlag;
        columnList.add(columnArray);
    }
    
    private static void addSortCondition(List<Object[]> sortConditionList, String dbColumnName, String orderBy){
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = dbColumnName;
        sortConditionArray[1] = orderBy;
        sortConditionList.add(sortConditionArray);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2610BMsg
     * @param glblCmpyCd String
     * @param idx int
     * @return Object[]
     */
    public static Object[] createPopPrmForNMAL6050(NMAL2610BMsg scrnMsg, String glblCmpyCd, int idx) {

        TRTY_RULE_TPTMsg tMsg = srchTrtyRuleTp(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue(), glblCmpyCd);
        Object[] params = new Object[11];
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, tMsg.srchTblNm.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, tMsg.srchColNm.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_2, tMsg.dplyColNm.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_3, tMsg.srchColNm.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_4, conectStrValue(tMsg.trtyRuleTpDescTxt.getValue(), NMAL2610Constant.CON_VAL_SEARCH));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_5, conectStrValue(tMsg.trtyRuleTpDescTxt.getValue(), NMAL2610Constant.CON_VAL_CODE));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_6, conectStrValue(tMsg.trtyRuleTpDescTxt.getValue(), NMAL2610Constant.CON_VAL_NAME));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_7, conectStrValue(tMsg.trtyRuleTpDescTxt.getValue(), NMAL2610Constant.CON_VAL_CODE));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_8, conectStrValue(tMsg.trtyRuleTpDescTxt.getValue(), NMAL2610Constant.CON_VAL_NAME));

        params[0] = scrnMsg.xxPopPrm_0;
        params[1] = scrnMsg.xxPopPrm_1;
        params[2] = scrnMsg.xxPopPrm_2;
        params[3] = scrnMsg.xxPopPrm_3;
        params[4] = scrnMsg.xxPopPrm_4;
        params[5] = scrnMsg.xxPopPrm_5;
        params[6] = scrnMsg.xxPopPrm_6;
        params[7] = scrnMsg.xxPopPrm_7;
        params[8] = scrnMsg.xxPopPrm_8;

        if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            params[9] = scrnMsg.C.no(idx).trtyRuleFromValTxt_C1;
        } else {
            params[9] = scrnMsg.C.no(idx).trtyRuleThruValTxt_C1;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_10, "");
        params[10] = scrnMsg.xxPopPrm_10;

        return params;
    }

    private static Object[] createPopPrmForNMAL6760(NMAL2610BMsg scrnMsg, String glblCmpyCd, int idx) {
        scrnMsg.P.clear();

        // Mod Start 2018/06/01 QC#24293
        //if (TRTY_RULE_TP.ACCOUNT_NAME.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
        if (TRTY_RULE_TP.ACCOUNT_NUMBER.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            // Mod End 2018/06/01 QC#24293
            if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                // Mod Start 2018/06/01 QC#24293
                //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.C.no(idx).trtyRuleFromValTxt_C1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.C.no(idx).trtyRuleFromValTxt_C1);
                // Mod End 2018/06/01 QC#24293
            } else {
                // Mod Start 2018/06/01 QC#24293
                //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.C.no(idx).trtyRuleThruValTxt_C1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.C.no(idx).trtyRuleThruValTxt_C1);
                // Mod End 2018/06/01 QC#24293
            }
        } else if (TRTY_RULE_TP.LOCATION_NUMBER.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm_P, scrnMsg.C.no(idx).trtyRuleFromValTxt_C1);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm_P, scrnMsg.C.no(idx).trtyRuleThruValTxt_C1);
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(29).xxPopPrm_P, ZYPConstant.FLG_ON_Y); //Category: Location Status
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(30).xxPopPrm_P, ZYPConstant.FLG_ON_Y); //Category: Bill To/Ship To Status
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(33).xxPopPrm_P, ZYPConstant.FLG_ON_Y); // Category: Active

        Object[] params = new Object[NMAL2610Constant.NAML6760_PRM_NUM];
        for (int j = 0; j < NMAL2610Constant.NAML6760_PRM_NUM; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm_P;
        }

        return params;
    }

    private static String conectStrValue(String baseValue, String conValue) {

        StringBuilder setValue = new StringBuilder();
        setValue.append(baseValue);
        setValue.append(conValue);

        return setValue.toString();
    }
    // Add End 2017/03/02 QC#15878

    // 2018/09/21 QC#27726,ADD Add Start
    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2510BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2610BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2610BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2610BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId, int grpRows) {
        S21TableColorController tblColor = new S21TableColorController(NMAL2610Constant.SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.(All Table)
     * 
     * @param scrnMsg     NMAL2610BMsg
     */
    public static void setAllBGWithClear(NMAL2610BMsg scrnMsg) {
        if (NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.A, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UP);
            setRowsBGWithClear(scrnMsg, scrnMsg.B, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UN);
        } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_RULE);
        } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(scrnMsg.xxDplyTab.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.D, NMAL2610Constant.SCREEN_TABLE_NAME_RESRC);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.(All Table and view first tab)
     * 
     * @param scrnMsg     NMAL2610BMsg
     * @param clearTab    String
     */
    public static void setAllBGWithReset(NMAL2610BMsg scrnMsg, String clearTab) {
        if (NMAL2610Constant.TAB_TERRITORY.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.A, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UP);
            clearRowsBG(scrnMsg, scrnMsg.B, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UN);
        } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.C, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_RULE);
        } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(clearTab)) {
            clearRowsBG(scrnMsg, scrnMsg.D, NMAL2610Constant.SCREEN_TABLE_NAME_RESRC);
        }
        // Color on
        S21TableColorController tblColor = new S21TableColorController(NMAL2610Constant.SCREEN_ID, scrnMsg);
        if (NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UP, scrnMsg.A, 1);
            tblColor.setAlternateRowsBG(NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UN, scrnMsg.B, 1);
        } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_RULE, scrnMsg.C, 1);
        } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(scrnMsg.xxDplyTab.getValue())) {
            tblColor.setAlternateRowsBG(NMAL2610Constant.SCREEN_TABLE_NAME_RESRC, scrnMsg.D, 1);
        }
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. (All Table)
     * 
     * @param scrnMsg     NMAL2610BMsg
     */
    public static void clearAllRowsBG(NMAL2610BMsg scrnMsg) {
        if (NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UP);
            clearRowsBG(scrnMsg, scrnMsg.B, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_UN);
        } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.C, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_RULE);
        } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(scrnMsg.xxDplyTab.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.D, NMAL2610Constant.SCREEN_TABLE_NAME_RESRC);
        }
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL2610BMsg
     * @param scrnMsgAry  EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    private static void clearRowsBG(NMAL2610BMsg scrnMsg, EZDBMsgArray scrnMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(NMAL2610Constant.SCREEN_ID, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnMsgAry);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. (All Table)
     * 
     * @param handler     EZDCommonHandler
     * @param scrnMsg     NMAL2610BMsg
     */
    public static void setAddDelButton(EZDCommonHandler handler, NMAL2610BMsg scrnMsg) {
        if (NMAL2610Constant.TAB_TERRITORY.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.A.getValidCount() + 1 <= scrnMsg.A.length()) {
                handler.setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_TERRITORY, true);
            } else {
                handler.setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_TERRITORY, false);
            }
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_TERRITORY, true);
            } else {
                handler.setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_TERRITORY, false);
            }
        } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(scrnMsg.xxDplyTab.getValue())) {
            // Del Start 2019/12/06 QC#53019
//          if (scrnMsg.C.getValidCount() + 1 <= scrnMsg.C.length()) {
//              handler.setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RULES, true);
//          } else {
//              handler.setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RULES, false);
//          }
          // Del End   2019/12/06 QC#53019
            if (scrnMsg.C.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RULES, true);
            } else {
                handler.setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RULES, false);
            }
        } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.D.getValidCount() + 1 <= scrnMsg.D.length()) {
                handler.setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RESOURCE, true);
            } else {
                handler.setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RESOURCE, false);
            }
            if (scrnMsg.D.getValidCount() > 0) {
                handler.setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RESOURCE, true);
            } else {
                handler.setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RESOURCE, false);
            }
        }
    }
    // 2018/09/21 QC#27726,ADD Add End
}
