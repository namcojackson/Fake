/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720.common;

import static business.servlet.NSAL0720.constant.NSAL0720Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0720.NSAL0720BMsg;

/**
 *<pre>
 * Update Bill To
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1611
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 * 2017/01/30   Hitachi         T.Mizuki        Update          QC#17310
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0720CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0720BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0720BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0720BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0720BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
     // START 2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
     // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0720BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0720BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0720BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0720BMsg scrnMsg) {
        scrnMsg.billToCustCd_H1.setInputProtected(false);
        scrnMsg.locNm_H1.setInputProtected(true);
        scrnMsg.billToCustLocAddr_H1.setInputProtected(true);
        // mod start 2016/07/27 CSA Defect#12001
        scrnMsg.xxChkBox_LS.setInputProtected(true);
        // mod end 2016/07/27 CSA Defect#12001
        scrnMsg.xxChkBox_BS.setInputProtected(false);
        scrnMsg.xxChkBox_OR.setInputProtected(false);
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0720BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0720BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            handler.setButtonEnabled(BTN_OPENWIN_BILLTO, i, true);
            if ((hasValue(scrnMsg.A.no(i).dsContrCtrlStsCd_AH)
                    && protectContrLine(scrnMsg.A.no(i).dsContrCtrlStsCd_AH.getValue(), scrnMsg.A.no(i).dsContrCatgCd_A1.getValue(), scrnMsg.A.no(i).dsContrDtlTpCd_A1.getValue()))
                    || (hasValue(scrnMsg.A.no(i).dsContrCtrlStsCd_AD)
                            && protectContrLine(scrnMsg.A.no(i).dsContrCtrlStsCd_AD.getValue(), scrnMsg.A.no(i).dsContrCatgCd_A1.getValue(), scrnMsg.A.no(i).dsContrDtlTpCd_A1.getValue()))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_BILLTO, i, false);
            }
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(false);
            if (protectBllgMtr(scrnMsg, i)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
                scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_BILLTO, i, false);
            }
            // mod start 2016/02/29 CSA Defect#2684
            scrnMsg.A.no(i).mtrLbDescTxt_A1.setInputProtected(true);
            // mod end 2016/02/29 CSA Defect#2684
            scrnMsg.A.no(i).billToCustLocAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).billToCustLocAddr_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0720BMsg
     */
    private static void setRowColors(NSAL0720BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param scrnMsg
     * @param i
     * @return
     */
    private static boolean protectBllgMtr(NSAL0720BMsg scrnMsg, int i) {
        if (hasValue(scrnMsg.A.no(i).dsContrCtrlStsCd_AH)
                && protectContrLine(scrnMsg.A.no(i).dsContrCtrlStsCd_AH.getValue(), scrnMsg.A.no(i).dsContrCatgCd_A1.getValue(), scrnMsg.A.no(i).dsContrDtlTpCd_A1.getValue())) {
            return true;
        } else if (hasValue(scrnMsg.A.no(i).dsContrCtrlStsCd_AD)
                && protectContrLine(scrnMsg.A.no(i).dsContrCtrlStsCd_AD.getValue(), scrnMsg.A.no(i).dsContrCatgCd_A1.getValue(), scrnMsg.A.no(i).dsContrDtlTpCd_A1.getValue())) {
            return true;
        } else if (hasValue(scrnMsg.A.no(i).dsContrCtrlStsCd_AB)
                && protectContrLine(scrnMsg.A.no(i).dsContrCtrlStsCd_AB.getValue(), scrnMsg.A.no(i).dsContrCatgCd_A1.getValue(), scrnMsg.A.no(i).dsContrDtlTpCd_A1.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * @param contrCtrlStsCd String
     */
    private static boolean protectContrLine(String contrCtrlStsCd, String dsContrCatgCd, String dsContrDtlTpCd) {
        if (DS_CONTR_CTRL_STS.EXPIRED.equals(contrCtrlStsCd)
                || DS_CONTR_CTRL_STS.CANCELLED.equals(contrCtrlStsCd)
                || DS_CONTR_CTRL_STS.TERMINATED.equals(contrCtrlStsCd)
                || DS_CONTR_CTRL_STS.TERMINATED_HOLD.equals(contrCtrlStsCd)
                || DS_CONTR_CTRL_STS.EXPIRED_HOLD.equals(contrCtrlStsCd)) {
            return true;
        }
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)
                && hasValue(dsContrDtlTpCd)
                && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return true;
        }
        return false;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    private static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL0720BMsg
     */
    public static void addCheckItemForApply(NSAL0720BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H1);
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL0720BMsg
     */
    public static void addCheckItemForUpdate(NSAL0720BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustLocAddr_A2);
        }
    }

    /**
     * @param scrnMsg NSAL0720BMsg
     */
    public static void clearPopupParam(NSAL0720BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
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
        // START 2016/01/05 T.Tomita [QC#1029, ADD]
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        // END 2016/01/05 T.Tomita [QC#1029, ADD]
        // mod start 2017/01/30 CSA QC#17310
        scrnMsg.xxPopPrm_24.clear();
        scrnMsg.xxPopPrm_25.clear();
        // mod end 2017/01/30 CSA QC#17310
    }
}
