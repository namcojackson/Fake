/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0670.common;

import static business.servlet.NSAL0670.constant.NSAL0670Constant.SCREEN_ID;
import static business.servlet.NSAL0670.constant.NSAL0670Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0670.NSAL0670BMsg;
import business.servlet.NSAL0670.NSAL0670_ABMsg;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1611
 * 2015/12/15   Hitachi         K.Kasai         Update          QC#1577
 * 2017/01/13   Hitachi         K.Ochiai        Update          QC#16331
 * 
 *</pre>
 */
public class NSAL0670CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0670BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0670BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0670BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0670BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        // START 2017/01/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2017/01/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0670BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0670BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0670BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0670BMsg scrnMsg) {
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
        scrnMsg.xxChkBox_AL.setInputProtected(false);
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0670BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0670BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            if (notProtectContrLine(scrnMsg.A.no(i))) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            }
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCtrlStsNm_AD.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0670BMsg
     */
    private static void setRowColors(NSAL0670BMsg scrnMsg) {
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
     */
    private static boolean notProtectContrLine(NSAL0670_ABMsg msg) {
        if (!DS_CONTR_CTRL_STS.DRAFT.equals(msg.dsContrCtrlStsCd_AH.getValue())
                && !DS_CONTR_CTRL_STS.ENTERED.equals(msg.dsContrCtrlStsCd_AH.getValue())
                && !DS_CONTR_CTRL_STS.DRAFT.equals(msg.dsContrCtrlStsCd_AD.getValue())
                && !DS_CONTR_CTRL_STS.ENTERED.equals(msg.dsContrCtrlStsCd_AD.getValue())) {
            ZYPEZDItemValueSetter.setValue(msg.xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
            return false;
        }
        return true;
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
     * @param scrnMsg NSAL0670BMsg
     */
    public static void addCheckItem(NSAL0670BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
    }
}
