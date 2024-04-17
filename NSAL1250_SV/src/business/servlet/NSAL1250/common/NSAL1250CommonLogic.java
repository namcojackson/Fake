/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1250.common;

import static business.servlet.NSAL1250.constant.NSAL1250Constant.APPROVE;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.AUTH_UPDATE;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_DELETE;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_RESET;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.BUSINESS_ID;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.CHANGE_READ;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.REASSIGN;
import static business.servlet.NSAL1250.constant.NSAL1250Constant.SCREEN_ID;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1250.NSAL1250BMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/09/14   Hitachi         N.Arai          Update          QC#10729
 *</pre>
 */
public class NSAL1250CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1250BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1250BMsg scrnMsg) {
        controlScreenButton(handler, scrnMsg);
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL1250BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL1250BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // START 09/14/2016 N.Arai [QC#10729, MOD]
        if (!S21NwfWorkItem.STATUS.RUN.getCode().equals(scrnMsg.wfProcStsCd.getValue())) {
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        }
        // END 09/14/2016 N.Arai [QC#10729, MOD]
        if (!hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonEnabled("Approve", false);
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Interface Maintenance (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1250BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL1250BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenTableFields(handler, scrnMsg);
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1250BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL1250BMsg scrnMsg) {
        scrnMsg.xxWfActOpNm.setInputProtected(true);
        scrnMsg.xxCtacPsnNmTxt.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt.setInputProtected(true);
        scrnMsg.xxWfCmntTxt_2.setInputProtected(true);
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL1250BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL1250BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcContrBllgFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcContrBllgThruDt_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrChrgDealAmt_AV.setInputProtected(true);
            scrnMsg.A.no(i).mtrChrgDealAmt_AA.setInputProtected(true);
            scrnMsg.A.no(i).mtrChrgDealAmt_AP.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).xxTsDsp19Txt_B.setInputProtected(true);
            scrnMsg.B.no(i).xxWfActOpNm_B.setInputProtected(true);
            scrnMsg.B.no(i).xxCtacPsnNmTxt_BF.setInputProtected(true);
            scrnMsg.B.no(i).xxCtacPsnNmTxt_BT.setInputProtected(true);
            scrnMsg.B.no(i).xxWfCmntTxt_B.setInputProtected(true);
        }
    }

    /**
     * control screen button
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1250BMsg
     */
    // 09/14/2016 N.Arai [QC#10729, ADD]
    private static void controlScreenButton(EZDCommonHandler handler, NSAL1250BMsg scrnMsg) {

        if (S21NwfWorkItem.STATUS.RUN.getCode().equals(scrnMsg.wfProcStsCd.getValue())) {
            handler.setButtonEnabled(APPROVE, true);
            handler.setButtonEnabled(REASSIGN, true);
            handler.setButtonEnabled(CHANGE_READ, true);
        } else {
            handler.setButtonEnabled(APPROVE, false);
            handler.setButtonEnabled(REASSIGN, false);
            handler.setButtonEnabled(CHANGE_READ, false);
        }

    }

}
