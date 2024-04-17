/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0010.NSBL0010BMsg;
import business.servlet.NSBL0010.NSBL0010_ABMsg;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2014/04/07   Fujitsu         Y.Kamide        Update          Def#42.
 * 2015/11/18   Hitachi         T.Harada        Update          [CSA,DELETE]
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 * 2018/01/24   Hitachi         T.Tomita        Update          QC#23655
 *</pre>
 */
public class NSBL0010CommonLogic implements NSBL0010Constant {

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param funcList List<String>
     */
    public static final void initCommonButton(EZDCommonHandler handler, List<String> funcList) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        handler.setButtonEnabled(BTN_CMN_SAVE[0], false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        handler.setButtonEnabled(BTN_CMN_APPLY[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK4[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK5[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK6[0], true);
        handler.setButtonEnabled(BTN_CMN_BLANK7[0], false);
        handler.setButtonEnabled(BTN_CMN_CLEAR[0], true);
        handler.setButtonEnabled(BTN_CMN_RESET[0], false);
        handler.setButtonEnabled(BTN_CMN_RETURN[0], true);

        handler.setButtonEnabled(SEARCH_BTN, true);
        handler.setButtonEnabled(SEARCH_SHIPTO_BTN, true);

        handler.setButtonEnabled(SCHEDULE_BTN, false);
        handler.setButtonEnabled(DISPATCH_BTN, false);
        handler.setButtonEnabled(UN_SCHD_DISPT_BTN, false);
        // Add Start 2018/01/24 QC#23655
        handler.setButtonEnabled(ACCEPT_BTN, false);
        // Add End 2018/01/24 QC#23655
        handler.setButtonEnabled(HISTORY_BTN, false);
        // Add Start 2018/01/24 QC#23655
        handler.setButtonEnabled(UPDATE_ETA_BTN, false);
        // Add End 2018/01/24 QC#23655
    }

    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0010BMsg
     * @param userId String
     */
    public static final void setControlScreen(EZDCommonHandler handler, NSBL0010BMsg scrnMsg, String userId) {

        setTblBackColor(scrnMsg);
        controlScreenHeaderFields(handler, scrnMsg, userId);
        controlScreenDetailFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, scrnMsg, userId);
        setTabIndex(scrnMsg);

        // convert date time and timezone
        setConvertTimeZone(scrnMsg);
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0010BMsg
     * @param userId String
     */
    public static final void controlScreenHeaderFields(EZDCommonHandler handler, NSBL0010BMsg scrnMsg, String userId) {

// START 2016/10/19 N.Arai [QC#13901, MOD]
//        scrnMsg.orgNm.setInputProtected(true);
        scrnMsg.svcContrBrDescTxt.setInputProtected(true);
// END 2016/10/19 N.Arai [QC#13901, MOD]
        scrnMsg.locNm.setInputProtected(true);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0010BMsg
     * @param userId String
     */
    public static final void controlScreenDetailFields(EZDCommonHandler handler, NSBL0010BMsg scrnMsg, String userId) {
        NSBL0010_ABMsg abMsg = null;
        String svcTaskStsCd = null;
        int size = scrnMsg.A.getValidCount();

        for (int i = 0; i < size; i++) {
            abMsg = scrnMsg.A.no(i);
            abMsg.svcContrBrDescTxt_A.setInputProtected(true);
            abMsg.svcByLineBizTpCd_A.setInputProtected(true);
            abMsg.mdlNm_A.setInputProtected(true);
            abMsg.svcPblmSympTpCd_A.setInputProtected(true);
            abMsg.svcPblmSympTpNm_A.setInputProtected(true);
            abMsg.serNum_A.setInputProtected(true);
            abMsg.svcRspTmNum_A.setInputProtected(true);
            abMsg.svcTaskStsNm_A.setInputProtected(true);
            abMsg.shipToCustCd_A.setInputProtected(true);
            abMsg.locNm_A.setInputProtected(true);
            abMsg.techSchdTz_A.setInputProtected(true);
            abMsg.techSchdFromDt_AL.setInputProtected(true);
            abMsg.techSchdToDt_AL.setInputProtected(true);
            abMsg.techSchdTz_AL.setInputProtected(true);
            abMsg.xxDtTm_L1.setInputProtected(true);
            abMsg.xxDtTm_L2.setInputProtected(true);
            abMsg.xxFsrVisitEtaTz_A.setInputProtected(true);
            abMsg.fsrVisitEtaDt_AL.setInputProtected(true);
            abMsg.xxDtTm_L5.setInputProtected(true);
            abMsg.xxFsrVisitEtaTz_AL.setInputProtected(true);

            svcTaskStsCd = abMsg.svcTaskStsCd_A.getValue();

            // -- C/O Link
            // START 2015/11/18 T.Harada [CSA,DELETE]
            //if (!ZYPCommonFunc.hasValue(abMsg.xxBtnFlg_AC)) {
            //    abMsg.xxBtnFlg_AL.setInputProtected(true);
            //    setProtectedColor(scrnMsg, C_O_FLG_ID + i);
            //} else {
            //    abMsg.xxBtnFlg_AL.setInputProtected(false);
            //    scrnMsg.clearGUIAttribute(SCREEN_ID, C_O_FLG_ID + i);
            //}
            // END 2015/11/18 T.Harada [CSA,DELETE]

            // -- Hold Link
            if (ZYPConstant.FLG_ON_Y.equals(abMsg.svcCrHldFlg_A.getValue())) {
                abMsg.svcCrHldFlg_AL.setInputProtected(false);
                scrnMsg.clearGUIAttribute(SCREEN_ID, HOLD_FLG_ID + i);
            } else {
                abMsg.svcCrHldFlg_AL.setInputProtected(true);
                setProtectedColor(scrnMsg, HOLD_FLG_ID + i);
            }

            // START 2015/11/18 T.Harada [CSA,CHANGE]
            //if (SVC_TASK_STS.CONTINUOUS_OPEN.equals(svcTaskStsCd)) {
            //    commonDetailProtect(handler, abMsg, i, false);
            //    etaDateProtect(handler, abMsg, i, false);
            //} else if (SVC_TASK_STS.APPROVED.equals(svcTaskStsCd)) {
            //    commonDetailProtect(handler, abMsg, i, false);
            //    etaDateProtect(handler, abMsg, i, false);
            //} else if (SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
            //    commonDetailProtect(handler, abMsg, i, false);
            //    // Start Add 2014/04/07 Def#42.
            //    if (ZYPConstant.FLG_ON_Y.equals(abMsg.techAcptFlg_A.getValue())) {
            //        abMsg.techCd_A.setInputProtected(true);
            //        handler.setButtonEnabled(TECH_CD_BTN, i, false);
            //    }
            //    // End   Add 2014/04/07 Def#42.
            //    etaDateProtect(handler, abMsg, i, false);
            //} else if (SVC_TASK_STS.DISPATCHED.equals(svcTaskStsCd)) {
            //    commonDetailProtect(handler, abMsg, i, true);
            //    abMsg.xxChkBox_A.setInputProtected(false);
            //    etaDateProtect(handler, abMsg, i, false);
            //}
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
//            if (SVC_TASK_STS.TBO.equals(svcTaskStsCd) || SVC_TASK_STS.OPEN.equals(svcTaskStsCd)) {
            if (SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd) || SVC_TASK_STS.TBO.equals(svcTaskStsCd) || SVC_TASK_STS.OPEN.equals(svcTaskStsCd)) {
                commonDetailProtect(handler, abMsg, i, false);
                etaDateProtect(handler, abMsg, i, false);
            } else if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd) || SVC_TASK_STS.IN_ROUTE.equals(svcTaskStsCd)) {
                commonDetailProtect(handler, abMsg, i, true);
                abMsg.xxChkBox_A.setInputProtected(false);
                etaDateProtect(handler, abMsg, i, false);
            }
            // END 2015/11/18 T.Harada [CSA,CHANGE]
            else {
                commonDetailProtect(handler, abMsg, i, true);
                etaDateProtect(handler, abMsg, i, true);
            }
            abMsg.xxChkBox_A.setInputProtected(false);
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
        }

        scrnMsg.xxChkBox_AL.clear();
    }

    private static final void commonDetailProtect(EZDCommonHandler handler, NSBL0010_ABMsg abMsg, int idx, boolean flg) {
        abMsg.xxChkBox_A.setInputProtected(flg);
        abMsg.techCd_A.setInputProtected(flg);
        abMsg.techSchdFromDt_A.setInputProtected(flg);
        abMsg.techSchdToDt_A.setInputProtected(flg);
//        abMsg.techSchdTz_A.setInputProtected(flg);
        abMsg.xxDtTm_A1.setInputProtected(flg);
        abMsg.xxDtTm_A2.setInputProtected(flg);
        abMsg.svcCallPrtyCd_A.setInputProtected(flg);
        abMsg.schdDisptEmlFlg_A.setInputProtected(flg);

        handler.setButtonEnabled(TECH_CD_BTN, idx, !flg);
    }

    private static final void etaDateProtect(EZDCommonHandler handler, NSBL0010_ABMsg abMsg, int idx, boolean flg) {
        abMsg.fsrVisitEtaDt_A.setInputProtected(flg);
        abMsg.xxDtTm_A5.setInputProtected(flg);
    }

    private static void setProtectedColor(NSBL0010BMsg scrnMsg, String id) {

        EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, id);
        guiAttr.setStyleAttribute("color", "black");
        guiAttr.setStyleAttribute("text-decoration", "none");
        guiAttr.setStyleAttribute("cursor", "default");
        scrnMsg.addGUIAttribute(guiAttr);
    }

    /**
     * Set table's back color
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void setTblBackColor(NSBL0010BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        if (scrnMsg.A.getValidCount() > 0) {
            S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

            scrnMsg.clearAllGUIAttribute(SCREEN_ID);
            int row = ROW_NUM2;
            if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_L1)) {
                row = ROW_NUM3;
            }
            tblColor.setAlternateRowsBG(TBL_ID_LEFT, scrnMsg.A, row);
            tblColor.setAlternateRowsBG(TBL_ID_RIGHT, scrnMsg.A, row);

        }
    }

    private static void setTabIndex(NSBL0010BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, TBL_ID);
        scrnMsg.addGUIAttribute(tblFocusRule);

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(SCHD_SORT_ID);
        fRule.setNextId(PRTY_SORT_ID);
        tblFocusRule.addRule(fRule);

        fRule = new ZYPGUIFocusRule(PRTY_SORT_ID);
        fRule.setPrevId(SCHD_SORT_ID);
        tblFocusRule.addRule(fRule);

        fRule = new ZYPGUIFocusRule(EML_SORT_ID);
        fRule.setNextId(CHK_FLG_ID + 0);
        tblFocusRule.addRule(fRule);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (i == 0) {
                fRule = new ZYPGUIFocusRule(CHK_FLG_ID + 0);
                fRule.setPrevId(EML_SORT_ID);
                tblFocusRule.addRule(fRule);
            }
            if (i > 0) {
                fRule = new ZYPGUIFocusRule(CHK_FLG_ID + i);
                fRule.setPrevId(EML_FLG_ID + (i - 1));
                tblFocusRule.addRule(fRule);
            }

            fRule = new ZYPGUIFocusRule(SCHD_TZ_ID + i);
            fRule.setNextId(PRTY_CD_ID + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(PRTY_CD_ID + i);
            fRule.setPrevId(SCHD_TZ_ID + i);
            tblFocusRule.addRule(fRule);

            if ((i + 1) != scrnMsg.A.length()) {
                fRule = new ZYPGUIFocusRule(EML_FLG_ID + i);
                fRule.setNextId(CHK_FLG_ID + (i + 1));
                tblFocusRule.addRule(fRule);
            }
        }
    }

    /**
     * Buttons's activity set by user authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0010BMsg
     * @param userId String
     */
    public static final void controlButtonByAuthority(EZDCommonHandler handler, NSBL0010BMsg scrnMsg, String userId) {

        handler.setButtonEnabled(SCHEDULE_BTN, false);
        handler.setButtonEnabled(DISPATCH_BTN, false);
        handler.setButtonEnabled(UN_SCHD_DISPT_BTN, false);
        // Mod Start 2018/01/24 QC#23655
        handler.setButtonEnabled(ACCEPT_BTN, false);
        handler.setButtonEnabled(HISTORY_BTN, true);
        handler.setButtonEnabled(UPDATE_ETA_BTN, false);

        if (hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(SCHEDULE_BTN, true);
            handler.setButtonEnabled(DISPATCH_BTN, true);
            handler.setButtonEnabled(UN_SCHD_DISPT_BTN, true);
            handler.setButtonEnabled(ACCEPT_BTN, true);
//            handler.setButtonEnabled(HISTORY_BTN, true);
            handler.setButtonEnabled(UPDATE_ETA_BTN, true);
        }
        // Mod End 2018/01/24 QC#23655
    }

    /**
     * Return true if userId have update authority.
     * @param userId String
     * @return boolean
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE })) {
            return true;
        }
        return false;
    }

    /**
     * inputCheck
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void inputCheck(NSBL0010BMsg scrnMsg) {
        NSBL0010_ABMsg dtlMsg = null;
        String fromTm = null;
        int size = scrnMsg.A.getValidCount();

        for (int i = 0; i < size; i++) {
            dtlMsg = scrnMsg.A.no(i);

            // Time convert
            fromTm = dtlMsg.xxDtTm_A1.getValue();
            convertTime(fromTm, dtlMsg.xxDtTm_A1, dtlMsg.techSchdFromTm_A);

            fromTm = dtlMsg.xxDtTm_A2.getValue();
            convertTime(fromTm, dtlMsg.xxDtTm_A2, dtlMsg.techSchdToTm_A);

            if (dtlMsg.xxDtTm_A1.getErrorCode() == 0 && dtlMsg.xxDtTm_A2.getErrorCode() == 0) {
                if (ZYPCommonFunc.hasValue(dtlMsg.techSchdToDt_A) && ZYPCommonFunc.hasValue(dtlMsg.techSchdToTm_A)) {
                    String fromDtTm = dtlMsg.techSchdFromDt_A.getValue() + dtlMsg.techSchdFromTm_A.getValue();
                    String toDtTm = dtlMsg.techSchdToDt_A.getValue() + dtlMsg.techSchdToTm_A.getValue();
                    if (toDtTm.compareTo(fromDtTm) < 0) {
                        dtlMsg.techSchdToDt_A.setErrorInfo(1, NSBM0014E);
                        dtlMsg.xxDtTm_A2.setErrorInfo(1, NSBM0014E);
                    }
                }
            }
        }

        addCheckItems(scrnMsg);
    }

    /**
     * inputCheckForUpdateETA
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void inputCheckForUpdateETA(NSBL0010BMsg scrnMsg) {
        NSBL0010_ABMsg dtlMsg = null;
        String fromTm = null;
        int size = scrnMsg.A.getValidCount();

        for (int i = 0; i < size; i++) {
            dtlMsg = scrnMsg.A.no(i);
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
            if (!ZYPConstant.CHKBOX_ON_Y.equals(dtlMsg.xxChkBox_A.getValue())) {
                continue;
            }
// END 2017/01/05 N.Arai [QC#13901-2, MOD]

            // Time convert
            fromTm = dtlMsg.xxDtTm_A5.getValue();
            convertTime(fromTm, dtlMsg.xxDtTm_A5, dtlMsg.fsrVisitEtaTm_A);

            if (!ZYPCommonFunc.hasValue(dtlMsg.fsrVisitEtaDt_A)) {
                dtlMsg.fsrVisitEtaDt_A.setErrorInfo(1, ZZM9000E, new String[] {"ETA Date" });
            }

            if (!ZYPCommonFunc.hasValue(dtlMsg.xxDtTm_A5)) {
                dtlMsg.xxDtTm_A5.setErrorInfo(1, ZZM9000E, new String[] {"ETA Time" });
            }
        }

        addCheckItemsForUpdateETA(scrnMsg);
    }

    /**
     * addCheckItems
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void addCheckItems(NSBL0010BMsg scrnMsg) {
        int size = scrnMsg.A.getValidCount();
        for (int i = 0; i < size; i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).techCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).techSchdFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).techSchdToDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).techSchdTz_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDtTm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDtTm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcCallPrtyCd_A);
        }
    }

    /**
     * addCheckItemsForUpdateETA
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void addCheckItemsForUpdateETA(NSBL0010BMsg scrnMsg) {
        int size = scrnMsg.A.getValidCount();
        for (int i = 0; i < size; i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fsrVisitEtaDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDtTm_A5);
        }
    }

    private static final void convertTime(String fromTm, EZDBStringItem xxDtTm, EZDBStringItem techSchdTm) {
        if (ZYPCommonFunc.hasValue(fromTm)) {
            if (fromTm.matches(FORMAT_TM_FROM)) {
                ZYPEZDItemValueSetter.setValue(xxDtTm, fromTm.replaceAll(FORMAT_TM_FROM, FORMAT_TM_TO_SCR));
                ZYPEZDItemValueSetter.setValue(techSchdTm, fromTm.replaceAll(FORMAT_TM_FROM, FORMAT_TM_TO_DB));
            } else if (fromTm.matches(FORMAT_TM_FROM2)) {
                ZYPEZDItemValueSetter.setValue(xxDtTm, fromTm.replaceAll(FORMAT_TM_FROM2, FORMAT_TM_TO_SCR2));
                ZYPEZDItemValueSetter.setValue(techSchdTm, fromTm.replaceAll(FORMAT_TM_FROM2, FORMAT_TM_TO_DB2));
            } else if (fromTm.matches(FORMAT_TM_FROM3)) {
                ZYPEZDItemValueSetter.setValue(xxDtTm, String.format(FORMAT_TM_TO_SCR3, Integer.parseInt(fromTm)));
                ZYPEZDItemValueSetter.setValue(techSchdTm, String.format(FORMAT_TM_TO_DB3, Integer.parseInt(fromTm)));
            } else {
                xxDtTm.setErrorInfo(1, NSBM0004E, new String[] {"hh:mm, hhmm, hh" });
            }
        } else {
            techSchdTm.clear();
        }
    }

    /**
     * inputCheckForSearch
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void inputCheckForSearch(NSBL0010BMsg scrnMsg) {
// START 2016/10/19 N.Arai [QC#13901, MOD]
//        scrnMsg.addCheckItem(scrnMsg.orgLayerNum);
//        scrnMsg.addCheckItem(scrnMsg.orgCd);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd);
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
//        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcByLineBizTpCd_H);
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
// END 2016/10/19 N.Arai [QC#13901, MOD]
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum);
        scrnMsg.addCheckItem(scrnMsg.fsrNum);
        scrnMsg.addCheckItem(scrnMsg.techCd);
        scrnMsg.addCheckItem(scrnMsg.svcTaskStsCd_H3);
        scrnMsg.addCheckItem(scrnMsg.fsrVisitNum);
        scrnMsg.addCheckItem(scrnMsg.svcTaskRcvDt_H1);
        scrnMsg.addCheckItem(scrnMsg.svcTaskRcvDt_H2);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.addCheckItem(scrnMsg.techSchdFromDt);
        scrnMsg.addCheckItem(scrnMsg.dsSvcCallTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcBillTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_HO);
// START 2016/10/19 N.Arai [QC#13901, MOD]
//        scrnMsg.addCheckItem(scrnMsg.xxChkBox_EX);
// END 2016/10/19 N.Arai [QC#13901, MOD]
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_DW);
    }

    /**
     * inputCheckMondatory
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void inputCheckMondatory(NSBL0010BMsg scrnMsg) {
        boolean isError = true;
// START 2016/10/19 N.Arai [QC#13901, MOD]
//        if (ZYPCommonFunc.hasValue(scrnMsg.orgLayerNum)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.svcContrBrCd)) {
            isError = false;
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.orgCd)) {
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd_H3)) {
        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcByLineBizTpCd_H)) {
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
// END 2016/10/19 N.Arai [QC#13901, MOD]
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcTaskNum)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.fsrNum)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.techCd)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcTaskStsCd_H3)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.fsrVisitNum)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcTaskRcvDt_H1)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcTaskRcvDt_H2)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdlNm)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.techSchdFromDt)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsSvcCallTpCd_H3)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcBillTpCd_H3)) {
            isError = false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_HO)) {
            isError = false;
// START 2016/10/19 N.Arai [QC#13901, MOD]
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_EX)) {
//            isError = false;
// END 2016/10/19 N.Arai [QC#13901, MOD]
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_DW)) {
            isError = false;
        }

        if (isError) {
// START 2016/10/19 N.Arai [QC#13901, MOD]
//            scrnMsg.orgLayerNum.setErrorInfo(1, NSBM0001E);
//            scrnMsg.orgCd.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcContrBrCd.setErrorInfo(1, NSBM0001E);
// START 2017/01/05 N.Arai [QC#13901-2, MOD]
//            scrnMsg.lineBizTpCd_H3.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcByLineBizTpCd_H.setErrorInfo(1, NSBM0001E);
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
// END 2016/10/19 N.Arai [QC#13901, MOD]
            scrnMsg.svcTaskNum.setErrorInfo(1, NSBM0001E);
            scrnMsg.fsrNum.setErrorInfo(1, NSBM0001E);
            scrnMsg.techCd.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcTaskStsCd_H3.setErrorInfo(1, NSBM0001E);
            scrnMsg.fsrVisitNum.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcTaskRcvDt_H1.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcTaskRcvDt_H2.setErrorInfo(1, NSBM0001E);
            scrnMsg.shipToCustCd.setErrorInfo(1, NSBM0001E);
            scrnMsg.mdlNm.setErrorInfo(1, NSBM0001E);
            scrnMsg.techSchdFromDt.setErrorInfo(1, NSBM0001E);
            scrnMsg.dsSvcCallTpCd_H3.setErrorInfo(1, NSBM0001E);
            scrnMsg.svcBillTpCd_H3.setErrorInfo(1, NSBM0001E);
            scrnMsg.xxChkBox_HO.setErrorInfo(1, NSBM0001E);
// START 2016/10/19 N.Arai [QC#13901, MOD]
//            scrnMsg.xxChkBox_EX.setErrorInfo(1, NSBM0001E);
// END 2016/10/19 N.Arai [QC#13901, MOD]
            scrnMsg.xxChkBox_DW.setErrorInfo(1, NSBM0001E);

            inputCheckForSearch(scrnMsg);
        }
    }

    /**
     * checkUniqSelect
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void checkUniqSelect(NSBL0010BMsg scrnMsg) {
        int checkCnt = 0;
        int size = scrnMsg.A.getValidCount();

        for (int i = 0; i < size; i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                checkCnt++;
            }
        }

        if (checkCnt == 0) {
            scrnMsg.setMessageInfo(NSBM0007E);

        } else if (checkCnt > 1) {
            for (int i = 0; i < size; i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSBM0008E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                }
            }
        }
    }

    /**
     * setConvertTimeZone
     * @param scrnMsg NSBL0010BMsg
     */
    public static final void setConvertTimeZone(NSBL0010BMsg scrnMsg) {
        int size = scrnMsg.A.getValidCount();
        NSBL0010_ABMsg abMsg = null;
        String ctryCd = null;
        String postCd = null;

        for (int i = 0; i < size; i++) {
            abMsg = scrnMsg.A.no(i);
            ctryCd = abMsg.ctryCd_A.getValue();
            postCd = abMsg.postCd_A.getValue();

            abMsg.techSchdTz_A.clear();
            if (ZYPCommonFunc.hasValue(abMsg.techSchdFromDt_A)) {
                ZYPEZDItemValueSetter.setValue(abMsg.techSchdTz_A, NSXC001001SvcTimeZone.getSysTimeZone(abMsg.techSchdFromDt_A, abMsg.techSchdFromTm_A));
            } else if (ZYPCommonFunc.hasValue(abMsg.techSchdToDt_A)) {
                ZYPEZDItemValueSetter.setValue(abMsg.techSchdTz_A, NSXC001001SvcTimeZone.getSysTimeZone(abMsg.techSchdToDt_A, abMsg.techSchdToTm_A));
            } else {
                abMsg.techSchdTz_A.clear();
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_L1)) {
                continue;
            }

            if (!ZYPCommonFunc.hasValue(ctryCd) || !ZYPCommonFunc.hasValue(postCd)) {
                abMsg.techSchdFromDt_AL.clear();
                abMsg.techSchdFromTm_AL.clear();
                abMsg.techSchdToDt_AL.clear();
                abMsg.techSchdToTm_AL.clear();
                abMsg.techSchdTz_AL.clear();
                abMsg.svcTaskRcvDt_AL.clear();
                abMsg.svcTaskRcvTm_AL.clear();
                abMsg.svcTaskRcvTz_AL.clear();
                abMsg.xxDtTm_L1.clear();
                abMsg.xxDtTm_L2.clear();
                abMsg.xxDtTm_L3.clear();
                abMsg.fsrVisitEtaDt_AL.clear();
                abMsg.fsrVisitEtaTm_AL.clear();
                abMsg.xxFsrVisitEtaTz_AL.clear();
                abMsg.xxDtTm_L5.clear();
                continue;
            }

            ZYPEZDItemValueSetter.setValue(abMsg.techSchdFromDt_AL, abMsg.techSchdFromDt_A);
            ZYPEZDItemValueSetter.setValue(abMsg.techSchdFromTm_AL, abMsg.techSchdFromTm_A);
            ZYPEZDItemValueSetter.setValue(abMsg.techSchdToDt_AL, abMsg.techSchdToDt_A);
            ZYPEZDItemValueSetter.setValue(abMsg.techSchdToTm_AL, abMsg.techSchdToTm_A);

            ZYPEZDItemValueSetter.setValue(abMsg.svcTaskRcvDt_AL, abMsg.svcTaskRcvDt_A);
            ZYPEZDItemValueSetter.setValue(abMsg.svcTaskRcvTm_AL, abMsg.svcTaskRcvTm_A);

            ZYPEZDItemValueSetter.setValue(abMsg.fsrVisitEtaDt_AL, abMsg.fsrVisitEtaDt_A);
            ZYPEZDItemValueSetter.setValue(abMsg.fsrVisitEtaTm_AL, abMsg.fsrVisitEtaTm_A);

            if (ZYPCommonFunc.hasValue(abMsg.fsrVisitEtaDt_A)) {
                ZYPEZDItemValueSetter.setValue(abMsg.xxFsrVisitEtaTz_A, NSXC001001SvcTimeZone.getSysTimeZone(abMsg.fsrVisitEtaDt_A, abMsg.fsrVisitEtaTm_A));
            }
            if (ZYPCommonFunc.hasValue(abMsg.techAcptDt_A)) {
                ZYPEZDItemValueSetter.setValue(abMsg.xxTechAcptTz_A, NSXC001001SvcTimeZone.getSysTimeZone(abMsg.techAcptDt_A, abMsg.techAcptTm_A));
            }

            ZYPEZDItemValueSetter.setValue(abMsg.svcTaskRcvTz_A, NSXC001001SvcTimeZone.getSysTimeZone(abMsg.svcTaskRcvDt_A, abMsg.svcTaskRcvTm_A));
            ZYPEZDItemValueSetter.setValue(abMsg.tmZoneCd_A, abMsg.svcTaskRcvTz_A.getValue());

            if (ZYPCommonFunc.hasValue(abMsg.techSchdToDt_AL)) {
                NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, abMsg.techSchdToDt_AL, abMsg.techSchdToTm_AL, abMsg.techSchdTz_AL, ctryCd, postCd);
                ZYPEZDItemValueSetter.setValue(abMsg.xxDtTm_L2, replaceSchdTm(abMsg.techSchdToTm_AL.getValue()));
            } else {
                abMsg.xxDtTm_L2.clear();
                abMsg.techSchdToTm_AL.clear();
                abMsg.techSchdTz_AL.clear();
            }
            if (ZYPCommonFunc.hasValue(abMsg.techSchdFromDt_AL)) {
                NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, abMsg.techSchdFromDt_AL, abMsg.techSchdFromTm_AL, abMsg.techSchdTz_AL, ctryCd, postCd);
                ZYPEZDItemValueSetter.setValue(abMsg.xxDtTm_L1, replaceSchdTm(abMsg.techSchdFromTm_AL.getValue()));
            } else {
                abMsg.xxDtTm_L1.clear();
                abMsg.techSchdFromTm_AL.clear();
                abMsg.techSchdTz_AL.clear();
            }
            if (ZYPCommonFunc.hasValue(abMsg.svcTaskRcvDt_AL)) {
                NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, abMsg.svcTaskRcvDt_AL, abMsg.svcTaskRcvTm_AL, abMsg.svcTaskRcvTz_AL, ctryCd, postCd);
                ZYPEZDItemValueSetter.setValue(abMsg.xxDtTm_L3, replaceRcvTm(abMsg.svcTaskRcvTm_AL.getValue()));
            } else {
                abMsg.xxDtTm_L3.clear();
                abMsg.svcTaskRcvTm_AL.clear();
                abMsg.svcTaskRcvTz_AL.clear();
            }
            if (ZYPCommonFunc.hasValue(abMsg.fsrVisitEtaDt_AL)) {
                NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, abMsg.fsrVisitEtaDt_AL, abMsg.fsrVisitEtaTm_AL, abMsg.xxFsrVisitEtaTz_AL, ctryCd, postCd);
                ZYPEZDItemValueSetter.setValue(abMsg.xxDtTm_L5, replaceRcvTm(abMsg.fsrVisitEtaTm_AL.getValue()));
            } else {
                abMsg.xxDtTm_L5.clear();
                abMsg.fsrVisitEtaTm_AL.clear();
                abMsg.xxFsrVisitEtaTz_AL.clear();
            }
        }
    }

    /**
     * replace Schedule Time format
     * @param srcTm String
     * @return String
     */
    public static String replaceSchdTm(String srcTm) {
        if (srcTm != null) {
            return srcTm.replaceAll(FORMAT_SCHD_TM_FROM, FORMAT_SCHD_TM_TO);
        } else {
            return "";
        }
    }

    /**
     * replace Received Time format
     * @param srcTm String
     * @return String
     */
    public static String replaceRcvTm(String srcTm) {
        if (srcTm != null) {
            return srcTm.replaceAll(FORMAT_RCV_TM_FROM, FORMAT_RCV_TM_TO);
        } else {
            return "";
        }
    }

 // START 2016/10/19 N.Arai [QC#13901, MOD]
    /**
     * set Parameter for Service Branch(NMAL6050)
     * @param scrnMsgNSBL0010BMsg
     */
    public static void setParamBranch(NSBL0010BMsg scrnMsg) {

        setValue(scrnMsg.xxTblNm, "SVC_CONTR_BR");
        setValue(scrnMsg.xxTblCdColNm, "SVC_CONTR_BR_CD");
        setValue(scrnMsg.xxTblNmColNm, "SVC_CONTR_BR_DESC_TXT");
        setValue(scrnMsg.xxTblSortColNm, "SVC_CONTR_BR_CD");
        setValue(scrnMsg.xxScrNm, "Service Branch Popup");
        setValue(scrnMsg.xxHdrCdLbNm, "Branch Code");
        setValue(scrnMsg.xxHdrNmLbNm, "Branch Name");
        setValue(scrnMsg.xxDtlCdLbNm, "Branch Code");
        setValue(scrnMsg.xxDtlNmLbNm, "Branch Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.svcContrBrCd)) {
            setValue(scrnMsg.xxCondCd, scrnMsg.svcContrBrCd);
        } else {
            scrnMsg.xxCondCd.clear();
        }
        scrnMsg.xxCondNm.clear();
    }

    /**
     * get Parameter for NMAL6050
     * @param scrnMsgNSBL0010BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamNMAL6050(NSBL0010BMsg scrnMsg) {
        Object[] params = new Object[NSBL0010Constant.PRMS_11];
        params[NSBL0010Constant.PRMS_00] = scrnMsg.xxTblNm;
        params[NSBL0010Constant.PRMS_01] = scrnMsg.xxTblCdColNm;
        params[NSBL0010Constant.PRMS_02] = scrnMsg.xxTblNmColNm;
        params[NSBL0010Constant.PRMS_03] = scrnMsg.xxTblSortColNm;
        params[NSBL0010Constant.PRMS_04] = scrnMsg.xxScrNm;
        params[NSBL0010Constant.PRMS_05] = scrnMsg.xxHdrCdLbNm;
        params[NSBL0010Constant.PRMS_06] = scrnMsg.xxHdrNmLbNm;
        params[NSBL0010Constant.PRMS_07] = scrnMsg.xxDtlCdLbNm;
        params[NSBL0010Constant.PRMS_08] = scrnMsg.xxDtlNmLbNm;
        params[NSBL0010Constant.PRMS_09] = scrnMsg.xxCondCd;
        params[NSBL0010Constant.PRMS_10] = scrnMsg.xxCondNm;
        return params;
    }
// END 2016/10/19 N.Arai [QC#13901, MOD]

    /**
     * clear Popup Parameter
     * @param scrnMsg NSBL0010BMsg
     */
    public static void clearPopupParameter(NSBL0010BMsg scrnMsg) {
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
        scrnMsg.xxPopPrm_PQ.clear();
        scrnMsg.xxPopPrm_PR.clear();
        scrnMsg.xxPopPrm_PS.clear();
        scrnMsg.xxPopPrm_PT.clear();
        scrnMsg.xxPopPrm_PU.clear();
        scrnMsg.xxPopPrm_PV.clear();
        scrnMsg.xxPopPrm_PW.clear();
        scrnMsg.xxPopPrm_PX.clear();
     // START 2017/01/05 N.Arai [QC#13901-2, MOD]
        scrnMsg.xxTblNm.clear();
        scrnMsg.xxTblCdColNm.clear();
        scrnMsg.xxTblNmColNm.clear();
        scrnMsg.xxTblSortColNm.clear();
        scrnMsg.xxScrNm.clear();
        scrnMsg.xxHdrCdLbNm.clear();
        scrnMsg.xxHdrNmLbNm.clear();
        scrnMsg.xxDtlCdLbNm.clear();
        scrnMsg.xxDtlNmLbNm.clear();
        scrnMsg.xxCondCd.clear();
        scrnMsg.xxCondNm.clear();
        ZYPTableUtil.clear(scrnMsg.Z);
     // END 2017/01/05 N.Arai [QC#13901-2, MOD]
    }
}
