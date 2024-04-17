/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0460.common;

import static business.servlet.NSAL0460.constant.NSAL0460Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0460.NSAL0460BMsg;
import business.servlet.NSAL0460.NSAL0460_ABMsg;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/11/27   Hitachi         T.Iwamoto       Update          QC#1235
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1219
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1611
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 * 2016/02/18   Hitachi         A.Kohinata      Update          QC#2882
 * 2016/02/26   Hitachi         K.Kasai         Update          QC#2684
 * 2016/03/31   Hitachi         T.Tomita        Update          QC#4587
 * 2017/01/17   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0460CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0460BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0460BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0460BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0460BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        // mod start 2017/01/17 CSA Defect#16331
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // [QC#2882,MOD]START
        //if (hasUpdateFuncId() && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxHldFlg.getValue())) {
        if (notProtectContr(scrnMsg)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        }
        // [QC#2882,MOD]END
        // mod end 2017/01/17 CSA Defect#16331
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0460BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0460BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Start Read Capture#(" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0460BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0460BMsg scrnMsg) {
        // [QC#2882,MOD]START
        //if (hasUpdateFuncId()) {
        if (notProtectContr(scrnMsg)) {
        // [QC#2882,MOD]END
            scrnMsg.svcMemoRsnCd_PS.setInputProtected(false);
            scrnMsg.svcCmntTxt.setInputProtected(false);
        } else {
            scrnMsg.svcMemoRsnCd_PS.setInputProtected(true);
            scrnMsg.svcCmntTxt.setInputProtected(true);
        }

    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0460BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0460BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrNum.setInputProtected(true);
            if (i != 0 && scrnMsg.A.no(i).xxRowNum_C.getValueInt() != 1) {
                scrnMsg.A.no(i).dsContrNum.clear();
            }
            scrnMsg.A.no(i).serNum.setInputProtected(true);
            scrnMsg.A.no(i).contrEffFromDt.setInputProtected(true);
            if (i != 0 && scrnMsg.A.no(i).xxRowNum_D.getValueInt() != 1) {
                scrnMsg.A.no(i).serNum.clear();
                scrnMsg.A.no(i).contrEffFromDt.clear();
            }
            // mod start 2016/02/25 CSA Defect#2684
            scrnMsg.A.no(i).mtrLbDescTxt.setInputProtected(true);
            // mod end 2016/02/25 CSA Defect#2684
            // [QC#2886,DEL]START
            //scrnMsg.A.no(i).dsMsgTxt.setInputProtected(true);
            // [QC#2886,DEL]END

            if (notProtectContrLine(scrnMsg.A.no(i))) {
                scrnMsg.A.no(i).xxChkBox.setInputProtected(false);
                scrnMsg.A.no(i).mtrReadDt.setInputProtected(false);
                scrnMsg.A.no(i).readMtrCnt.setInputProtected(false);
                // [QC#1235,MOD]START
                handler.setButtonEnabled("OpenMeterRead", i, true);
                // [QC#1235,MOD]END
                // [QC#2886,ADD]START
                scrnMsg.A.no(i).svcCmntTxt_A.setInputProtected(false);
                // [QC#2886,ADD]END
            } else {
                // [QC#2886,MOD]START
                //ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dsMsgTxt, getRtnMsg(NSAM0065E));
                scrnMsg.A.no(i).svcCmntTxt_A.setInputProtected(true);
                // [QC#2886,MOD]START
                scrnMsg.A.no(i).xxChkBox.setInputProtected(true);
                scrnMsg.A.no(i).mtrReadDt.setInputProtected(true);
                scrnMsg.A.no(i).readMtrCnt.setInputProtected(true);
                // [QC#1235,MOD]START
                handler.setButtonEnabled("OpenMeterRead", i, false);
                // [QC#1235,MOD]END
            }
            // START 2016/03/31 T.Tomita [QC#4587, ADD]
            if (scrnMsg.A.no(i).xxReadOnlyFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                scrnMsg.A.no(i).mtrReadDt.setInputProtected(true);
                scrnMsg.A.no(i).readMtrCnt.setInputProtected(true);
            }
            // END 2016/03/31 T.Tomita [QC#4587, ADD]
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0460BMsg
     */
    private static void setRowColors(NSAL0460BMsg scrnMsg) {

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

    /**
     * @param msg NSAL0460_ABMsg
     * @return boolean
     */
    private static boolean notProtectContrLine(NSAL0460_ABMsg msg) {
        if (!hasUpdateFuncId()) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(msg.actvFlg.getValue())) {
            return true;
        }
        return false;
    }

    // [QC#2882,ADD]START
    private static boolean notProtectContr(NSAL0460BMsg scrnMsg) {
        if (!hasUpdateFuncId()) {
            return false;
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).actvFlg.getValue())) {
                return true;
            }
        }
        return false;
    }
    // [QC#2882,ADD]START
}
