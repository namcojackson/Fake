/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270.common;


import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL0270.NFAL0270BMsg;
import business.servlet.NFAL0270.NFAL0270Bean;
import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * 
     * @param handler EZDCommonHandler
     * @param scrnMsg NFAL0270BMsg
     * @param userId userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NFAL0270BMsg scrnMsg, String userId) {

        initCommonButton(handler, userId);
        setTblBackColor(scrnMsg);
        controlScreenDetailFields(handler, scrnMsg, userId);
    }

    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * 
     * @param handler EZDCommonHandler
     * @param scrnMsg NFAL0270BMsg
     * @param userId userId
     */
    public static final void setControlScreen(EZDCommonHandler handler, NFAL0270BMsg scrnMsg, String userId) {

        setTblBackColor(scrnMsg);
        controlScreenFields(handler, scrnMsg, userId);
    }

    /**
     * Method name: initCommonButton
     * <dd>The method explanation: Initialize Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     * @param userId Login authority
     */
    public static final void initCommonButton(EZDCommonHandler handler, String userId) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        if (!hasUpdateAuthority(userId)) {
            // For Referencer
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        } else {
            // For Updated by
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        }
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NFAL0270BMsg
     * @param userId userId
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NFAL0270BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }

    /**
     * Set table's back color
     * @param scrnMsg NFAL0270BMsg
     */
    public static final void setTblBackColor(NFAL0270BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG(TABLE_ID_A_RIGHT, scrnMsg.A);
    }

    /**
     * Return true if userId have update authority.
     * @param userId userId
     * @return update authority : true
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE})) {
            return true;
        }
        return false;
    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NFAL0270BMsg scrnMsg, String userId) {

        handler.setButtonEnabled(BTN_SEARCH[0], true);
        handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
        handler.setButtonEnabled(BTN_DELETE_ROW[0], false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!hasUpdateAuthority(userId)) {
                scrnMsg.A.no(i).mdlGrpId_A.setInputProtected(true);
                scrnMsg.A.no(i).mdlGrpNm_A.setInputProtected(true);
                scrnMsg.A.no(i).svcInvChrgTpCd_A.setInputProtected(true);
                scrnMsg.A.no(i).svcAllocTpCd_A.setInputProtected(true);
                scrnMsg.A.no(i).equipAllocPct_A.setInputProtected(true);
                scrnMsg.A.no(i).svcAllocPct_A.setInputProtected(true);
                scrnMsg.A.no(i).splyAllocPct_A.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPEN_WIN_MDL_GRP_ID_A, i, false);
            } else {
                scrnMsg.A.no(i).mdlGrpId_A.setInputProtected(false);
                scrnMsg.A.no(i).mdlGrpNm_A.setInputProtected(true);
                scrnMsg.A.no(i).svcInvChrgTpCd_A.setInputProtected(false);
                scrnMsg.A.no(i).svcAllocTpCd_A.setInputProtected(false);
                scrnMsg.A.no(i).equipAllocPct_A.setInputProtected(false);
                scrnMsg.A.no(i).svcAllocPct_A.setInputProtected(false);
                scrnMsg.A.no(i).splyAllocPct_A.setInputProtected(false);
                handler.setButtonEnabled(BTN_OPEN_WIN_MDL_GRP_ID_A, i, true);
                handler.setButtonEnabled(BTN_DELETE_ROW[0], true);
            }
        }

        if (hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
        }
    }

    /**
     * set Parameter for Model Group(NMAL6050)
     * @param scrnMsg NFAL0270BMsg
     */
    public static void setParamMdlGrp(NFAL0270BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.setValue("DS_MDL_GRP");
        scrnMsg.xxTblCdColNm_P1.setValue("MDL_GRP_ID");
        scrnMsg.xxTblNmColNm_P1.setValue("MDL_GRP_NM");
        scrnMsg.xxTblSortColNm_P1.setValue("MDL_GRP_ID");
        scrnMsg.xxScrNm_P1.setValue("Model Group Popup");
        scrnMsg.xxHdrCdLbNm_P1.setValue("Model Group ID");
        scrnMsg.xxHdrNmLbNm_P1.setValue("Model Group Name");
        scrnMsg.xxDtlCdLbNm_P1.setValue("Model Group ID");
        scrnMsg.xxDtlNmLbNm_P1.setValue("Model Group Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.mdlGrpId_H)) {
            scrnMsg.xxCondCd_P1.setValue(scrnMsg.mdlGrpId_H.getValue().toString());
        } else {
            scrnMsg.xxCondCd_P1.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.mdlGrpNm_H)) {
            scrnMsg.xxCondNm_P1.setValue(scrnMsg.mdlGrpNm_H.getValue());
        } else {
            scrnMsg.xxCondNm_P1.clear();
        }
    }

    /**
     * set Parameter for Model Group(NMAL6050)
     * @param scrnMsg NFAL0270BMsg
     */
    public static void setParamMdlGrpA(NFAL0270BMsg scrnMsg, int eventRow) {

        scrnMsg.xxTblNm_P1.setValue("DS_MDL_GRP");
        scrnMsg.xxTblCdColNm_P1.setValue("MDL_GRP_ID");
        scrnMsg.xxTblNmColNm_P1.setValue("MDL_GRP_NM");
        scrnMsg.xxTblSortColNm_P1.setValue("MDL_GRP_ID");
        scrnMsg.xxScrNm_P1.setValue("Model Group Popup");
        scrnMsg.xxHdrCdLbNm_P1.setValue("Model Group ID");
        scrnMsg.xxHdrNmLbNm_P1.setValue("Model Group Name");
        scrnMsg.xxDtlCdLbNm_P1.setValue("Model Group ID");
        scrnMsg.xxDtlNmLbNm_P1.setValue("Model Group Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRow).mdlGrpId_A)) {
            scrnMsg.xxCondCd_P1.setValue(scrnMsg.A.no(eventRow).mdlGrpId_A.getValue().toString());
        } else {
            scrnMsg.xxCondCd_P1.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRow).mdlGrpNm_A)) {
            scrnMsg.xxCondNm_P1.setValue(scrnMsg.A.no(eventRow).mdlGrpNm_A.getValue());
        } else {
            scrnMsg.xxCondNm_P1.clear();
        }
    }

    /**
     * get Parameter for NMAL6050
     * @param scrnMsg NFAL0270BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamNMAL6050(NFAL0270BMsg scrnMsg) {
        Object[] params = new Object[PRMS_11];
        params[PRMS_00] = scrnMsg.xxTblNm_P1;
        params[PRMS_01] = scrnMsg.xxTblCdColNm_P1;
        params[PRMS_02] = scrnMsg.xxTblNmColNm_P1;
        params[PRMS_03] = scrnMsg.xxTblSortColNm_P1;
        params[PRMS_04] = scrnMsg.xxScrNm_P1;
        params[PRMS_05] = scrnMsg.xxHdrCdLbNm_P1;
        params[PRMS_06] = scrnMsg.xxHdrNmLbNm_P1;
        params[PRMS_07] = scrnMsg.xxDtlCdLbNm_P1;
        params[PRMS_08] = scrnMsg.xxDtlNmLbNm_P1;
        params[PRMS_09] = scrnMsg.xxCondCd_P1;
        params[PRMS_10] = scrnMsg.xxCondNm_P1;
        return params;
    }

    /**
     * addCheckItem
     * @param scrnMsg NFAL0270BMsg
     */
    public static void addCheckItem(NFAL0270BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mdlGrpId_H);
        scrnMsg.addCheckItem(scrnMsg.mdlGrpNm_H);
        scrnMsg.addCheckItem(scrnMsg.svcInvChrgTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.setCheckParam(new String[] {NFAL0270Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlGrpId_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlGrpNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcInvChrgTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAllocTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).equipAllocPct_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAllocPct_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).splyAllocPct_A);
            scrnMsg.addCheckItem(scrnMsg.A);
        }
    }

}
