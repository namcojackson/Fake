/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1030.common;

import static business.servlet.NSAL1030.constant.NSAL1030Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1030.NSAL1030BMsg;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1030CommonLogic {

    /**
     * set input parameter
     * @param scrnMsg NSAL1030BMsg
     * @param arg Serializable
     */
    public static void setInputParam(NSAL1030BMsg scrnMsg, Serializable arg) {

        scrnMsg.svcInvNum_P.clear();
        scrnMsg.dsContrNum_P.clear();
        scrnMsg.conslBillPk_P.clear();
        scrnMsg.serNum_P.clear();
        scrnMsg.bllgPerFromDt_P.clear();
        scrnMsg.bllgPerToDt_P.clear();
        scrnMsg.svcCrRebilPk_P.clear();

        if (!(arg instanceof Object[])) {
            return;
        }

        Object[] params = (Object[]) arg;
        if (params.length < NSAL1030_PRM_LENGTH) {
            return;
        }

        if (params[0] instanceof EZDBStringItem) {
            setValue(scrnMsg.svcInvNum_P, ((EZDBStringItem) params[0]));
        }
        if (params[1] instanceof EZDBStringItem) {
            setValue(scrnMsg.dsContrNum_P, ((EZDBStringItem) params[1]));
        }
        if (params[2] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.conslBillPk_P, ((EZDBBigDecimalItem) params[2]));
        }
        if (params[3] instanceof EZDBStringItem) {
            setValue(scrnMsg.serNum_P, ((EZDBStringItem) params[3]));
        }
        if (params[4] instanceof EZDBDateItem) {
            setValue(scrnMsg.bllgPerFromDt_P, ((EZDBDateItem) params[4]));
        }
        if (params[5] instanceof EZDBDateItem) {
            setValue(scrnMsg.bllgPerToDt_P, ((EZDBDateItem) params[5]));
        }
        if (params[6] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.svcCrRebilPk_P, ((EZDBBigDecimalItem) params[6]));
        }
    }

    /**
     * The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1030BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NSAL1030BMsg scrnMsg) {

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
     * @param scrnMsg NSAL1030BMsg
     */
    private static void controlScreenFields(EZDCommonHandler handler, NSAL1030BMsg scrnMsg) {

        scrnMsg.custIncdtId_H.setInputProtected(true);
        scrnMsg.svcCrRebilStsDescTxt_H.setInputProtected(true);
        scrnMsg.svcCrRebilDescTxt_H.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcInvNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).conslBillPk_A.setInputProtected(true);
            scrnMsg.A.no(i).svcInvChrgTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).invLineDealSlsAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).invLineDealTaxAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).invLineDealNetAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
            scrnMsg.A.no(i).svcCrRebilStsDescTxt_A.setInputProtected(true);
        }

        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg);
        }
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL1030BMsg
     */
    private static void setRowColors(NSAL1030BMsg scrnMsg) {

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
