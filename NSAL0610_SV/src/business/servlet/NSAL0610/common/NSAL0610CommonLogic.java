/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0610.common;

import static business.servlet.NSAL0610.constant.NSAL0610Constant.*;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0610.NSAL0610BMsg;
import business.servlet.NSAL0610.NSAL0610_ABMsg;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto         Create          N/A
 * 2016/04/21   Hitachi         T.Iwamoto         Update          QC#4424
 *</pre>
 */
public class NSAL0610CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0610BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0610BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0610BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0610BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId() && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxHldFlg.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0610BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0610BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
        setRowColors(scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Copy Contract (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
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
     * @param scrnMsg NSAL0610BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0610BMsg scrnMsg) {

        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.dsContrNum_H2.setInputProtected(true);

        // mod start 2016/04/21 CSA Defect#4424
        if (hasUpdateFuncId() && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxHldFlg.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSelFlg_HA.getValue())) {
                scrnMsg.xxChkBox_H1.setInputProtected(true);
                handler.setButtonEnabled("RightArrow", false);
                handler.setButtonEnabled("OrigSelectAll", false);
                handler.setButtonEnabled("OrigUnSelectAll", false);
            } else {
                scrnMsg.xxChkBox_H1.setInputProtected(false);
                handler.setButtonEnabled("RightArrow", true);
                handler.setButtonEnabled("OrigSelectAll", true);
                handler.setButtonEnabled("OrigUnSelectAll", true);
            }
            if (isNoModifiableData(scrnMsg)) {
                scrnMsg.xxChkBox_H2.setInputProtected(true);
                handler.setButtonEnabled("LeftArrow", false);
                handler.setButtonEnabled("NewSelectAll", false);
                handler.setButtonEnabled("NewUnSelectAll", false);
            } else {
                scrnMsg.xxChkBox_H2.setInputProtected(false);
                handler.setButtonEnabled("LeftArrow", true);
                handler.setButtonEnabled("NewSelectAll", true);
                handler.setButtonEnabled("NewUnSelectAll", true);
            }
            // mod start 2016/04/21 CSA Defect#4424
        } else {
            scrnMsg.xxChkBox_H1.setInputProtected(true);
            scrnMsg.xxChkBox_H2.setInputProtected(true);
            handler.setButtonEnabled("RightArrow", false);
            handler.setButtonEnabled("LeftArrow", false);
            handler.setButtonEnabled("OrigSelectAll", false);
            handler.setButtonEnabled("OrigUnSelectAll", false);
            handler.setButtonEnabled("NewSelectAll", false);
            handler.setButtonEnabled("NewUnSelectAll", false);
        }
    }

    // add 2016/04/21 CSA Defect#4424
    private static boolean isNoModifiableData(NSAL0610BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) && DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
            if (scrnMsg.N.getValidCount() == 0) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
            if (!DS_CONTR_DTL_TP.FLEET.equals(scrnMsg.N.no(i).dsContrDtlTpCd_N.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.N.no(i).dsContrDtlTpCd_N.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.N.no(i).xxDplyCtrlFlg_N.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0610BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0610BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
            if (notProtectContrLineA(scrnMsg.A.no(i))) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            }
            if (DS_CONTR_DTL_TP.FLEET.equals(scrnMsg.A.no(i).dsContrDtlTpCd_A.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.A.no(i).dsContrDtlTpCd_A.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                }
            }
        }

        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
            scrnMsg.N.no(i).serNum_N.setInputProtected(true);
            scrnMsg.N.no(i).mtrLbDescTxt_N.setInputProtected(true);
            if (DS_CONTR_DTL_TP.FLEET.equals(scrnMsg.N.no(i).dsContrDtlTpCd_N.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.N.no(i).dsContrDtlTpCd_N.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.N.no(i).xxDplyCtrlFlg_N.getValue())) {
                    scrnMsg.N.no(i).xxChkBox_N.setInputProtected(true);
                }
            }
        }
    }

    /**
     * @param scrnMsg
     * @param i
     */
    private static boolean notProtectContrLineA(NSAL0610_ABMsg msg) {
        if (!hasUpdateFuncId()) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(msg.xxSelFlg_A.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0760BMsg
     */
    public static void setRowColors(NSAL0610BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray tableA = (EZDBMsgArray) scrnMsg.getClass().getField(TABLE_A).get(scrnMsg);
            EZDBMsgArray tableN = (EZDBMsgArray) scrnMsg.getClass().getField(TABLE_N).get(scrnMsg);
            tblColor.clearRowsBG(TABLE_A, tableA);
            tblColor.clearRowsBG(TABLE_N, tableN);

            boolean targetFlg = false;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                    targetFlg = false;
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxSelFlg_A.getValue())) {
                        targetFlg = true;
                    }
                }
                if (targetFlg) {
                    tblColor.setRowStyle(TABLE_A, i, SELECT_COLOR);
                }

            }
            for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
                if (DS_CONTR_DTL_TP.FLEET.equals(scrnMsg.N.no(i).dsContrDtlTpCd_N.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.N.no(i).dsContrDtlTpCd_N.getValue())) {
                    tblColor.setRowStyle(TABLE_N, i, SELECT_COLOR);
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
