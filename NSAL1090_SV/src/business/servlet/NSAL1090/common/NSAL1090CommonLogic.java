/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090.common;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1090.NSAL1090BMsg;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2017/11/07   Hitachi         K.Kojima        Update          QC#22325
 *</pre>
 */
public class NSAL1090CommonLogic {

    /**
     * The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1090BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NSAL1090BMsg scrnMsg) {

        initControlCommonButton(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * init Control Common Button
     * @param handler EZDCommonHandler
     */
    private static void initControlCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1090BMsg
     */
    private static void controlScreenFields(EZDCommonHandler handler, NSAL1090BMsg scrnMsg) {

        if (hasValue(scrnMsg.custIncdtId_H)) {
            if (SVC_CR_REBIL_STS.ENTERED.equals(scrnMsg.svcCrRebilStsCd_H.getValue())) {
                handler.setButtonEnabled(BTN_SUBMIT_FOR_APPROVAL, true);
                handler.setButtonEnabled(BTN_VIEW_APPROVERS, false);
            } else {
                handler.setButtonEnabled(BTN_SUBMIT_FOR_APPROVAL, false);
                handler.setButtonEnabled(BTN_VIEW_APPROVERS, true);
            }
            handler.setButtonEnabled(BTN_RESEARCH, true);
            if (SVC_CR_REBIL_STS.CANCELLED.equals(scrnMsg.svcCrRebilStsCd_H.getValue()) || SVC_CR_REBIL_STS.APPROVED.equals(scrnMsg.svcCrRebilStsCd_H.getValue()) || SVC_CR_REBIL_STS.PROCESSED.equals(scrnMsg.svcCrRebilStsCd_H.getValue())) {
                handler.setButtonEnabled(BTN_CANCEL_CI, false);
            } else {
                handler.setButtonEnabled(BTN_CANCEL_CI, true);
            }
        } else {
            handler.setButtonEnabled(BTN_SUBMIT_FOR_APPROVAL, false);
            handler.setButtonEnabled(BTN_VIEW_APPROVERS, false);
            handler.setButtonEnabled(BTN_RESEARCH, false);
            handler.setButtonEnabled(BTN_CANCEL_CI, false);
        }

        scrnMsg.custIncdtId_H.setInputProtected(true);
        scrnMsg.svcCrRebilStsDescTxt_H.setInputProtected(true);
        scrnMsg.svcCrRebilDescTxt_H.setInputProtected(true);

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.xxChkBox_AL.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).origSvcInvNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).conslBillPk_A.setInputProtected(true);
            scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInvAmt_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxInvAmt_A3.setInputProtected(true);
            scrnMsg.A.no(i).crSvcInvNum_A.setInputProtected(true);
            scrnMsg.A.no(i).rebilSvcInvNum_A.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxExstFlg_A.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            }
        }

        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg);
        }

        // START 2017/11/07 K.Kojima [QC#22325,ADD]
        scrnMsg.xxInvAmt_S1.setInputProtected(true);
        scrnMsg.xxInvAmt_S2.setInputProtected(true);
        scrnMsg.xxInvAmt_S3.setInputProtected(true);
        // END 2017/11/07 K.Kojima [QC#22325,ADD]
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL1090BMsg
     */
    private static void setRowColors(NSAL1090BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
