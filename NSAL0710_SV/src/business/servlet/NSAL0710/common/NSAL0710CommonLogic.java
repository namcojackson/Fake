/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0710.common;

import static business.servlet.NSAL0710.constant.NSAL0710Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0710.NSAL0710BMsg;
import business.servlet.NSAL0710.NSAL0710_ABMsg;

/**
 *<pre>
 * Update Read Methods
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/18   Hitachi         K.Kasai         Create          N/A
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1219
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1577
 * 2017/02/13   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0710CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0710BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0710BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0710BMsg
     */
    public static final void initCommonButton(EZDCommonHandler handler, NSAL0710BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
    // START 2017/02/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
    // END   2017/02/13 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0710BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0710BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0710BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0710BMsg scrnMsg) {
        scrnMsg.mtrReadMethCd_H3.setInputProtected(false);
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt_H1.setInputProtected(false);
        scrnMsg.xxChkBox_AL.setInputProtected(false);
        scrnMsg.xxChkBox_BL.setInputProtected(false);
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0710BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0710BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            if (protectContrLine(scrnMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            }
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCtrlStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(false);
            if (i > 0 && protectContrLine(scrnMsg.A.no(i))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxGenlFldAreaTxt_A1, getRtnMsg(NSAM0065E));
                scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
            }
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyCtrlFlg_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyCtrlFlg_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyCtrlFlg_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyCtrlFlg_A4.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyCtrlFlg_A5.setInputProtected(true);
            scrnMsg.A.no(i).mtrReadMethNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mtrReadMethNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg
     * @param i
     */
    private static boolean protectContrLine(NSAL0710_ABMsg msg) {
        if (DS_CONTR_CTRL_STS.EXPIRED.equals(msg.dsContrCtrlStsCd_AH.getValue())
                || DS_CONTR_CTRL_STS.CANCELLED.equals(msg.dsContrCtrlStsCd_AH.getValue())
                || DS_CONTR_CTRL_STS.TERMINATED.equals(msg.dsContrCtrlStsCd_AH.getValue())
                || DS_CONTR_CTRL_STS.TERMINATED_HOLD.equals(msg.dsContrCtrlStsCd_AH.getValue())
                || DS_CONTR_CTRL_STS.EXPIRED_HOLD.equals(msg.dsContrCtrlStsCd_AH.getValue())) {
            return true;
        } else if (DS_CONTR_CTRL_STS.EXPIRED.equals(msg.dsContrCtrlStsCd_AD.getValue())
                        || DS_CONTR_CTRL_STS.CANCELLED.equals(msg.dsContrCtrlStsCd_AD.getValue())
                        || DS_CONTR_CTRL_STS.TERMINATED.equals(msg.dsContrCtrlStsCd_AD.getValue())
                        || DS_CONTR_CTRL_STS.TERMINATED_HOLD.equals(msg.dsContrCtrlStsCd_AH.getValue())
                        || DS_CONTR_CTRL_STS.EXPIRED_HOLD.equals(msg.dsContrCtrlStsCd_AH.getValue())) {
                    return true;
                }
        return false;
    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL0710BMsg
     */
    public static void addCheckItem(NSAL0710BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.mtrReadMethCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
    }

    /**
     * Get Return Message
     * @param msgId String
     * @return String
     */
    public static String getRtnMsg(String msgId) {
        String rtnVal = "";
        if (hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }
}
