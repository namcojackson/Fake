/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0760.common;

import static business.servlet.NFCL0760.constant.NFCL0760Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL0760.NFCL0760BMsg;
import business.servlet.NFCL0760.NFCL0760_ABMsg;
import business.servlet.NFCL0760.constant.NFCL0760Constant.BTN_LBL;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/10   Hitachi         T.Tsuchida      Update          QC#5026
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 *</pre>
 */
public class NFCL0760CommonLogic {

    // ************* For Popup *************
    /**
     * Check lastGuard is 'CLOSE' event.
     * @param lastGuard String
     * @return If lastGuard is 'CLOSE' event.
     */
    public static boolean isClosedEvent(String lastGuard) {
        return BTN_LBL.CLOSE.getGuardNm().toLowerCase().equals(lastGuard.toLowerCase());
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NFCL0760BMsg scrnMsg) {
        initCommonButton(handler);
        controlScreenHeader(handler, scrnMsg);
        controlScreenDetail(handler, scrnMsg);
        controlScreenBottom(handler, scrnMsg, false);
        setDataFormat(scrnMsg);
    }

    /**
     * <pre>
     * set Data Format.
     * </pre>
     * @param scrnMsg NFCL0760BMsg
     */
    public static final void setDataFormat(NFCL0760BMsg scrnMsg) {
        scrnMsg.xxTotAmt_H.setAppFracDigit(2);
        scrnMsg.highRmngBalAmt_H.setAppFracDigit(2);
        scrnMsg.lowRmngBalAmt_H.setAppFracDigit(2);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL0760_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.dealOrigGrsAmt_A.setAppFracDigit(2);
            abMsg.dealApplyAdjRsvdAmt_A.setAppFracDigit(2);
            abMsg.dealRmngBalGrsAmt_A.setAppFracDigit(2);
            abMsg.dealArAdjAmt_A.setAppFracDigit(2);
        }
    }

    /**
     * <pre>
     * The Search Event of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     */
    public static final void srchControlScreen(EZDCommonHandler handler, NFCL0760BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetail(handler, scrnMsg);
            controlScreenBottom(handler, scrnMsg, true);
        }
    }

    /**
     * Control screen header
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     */
    private static final void controlScreenHeader(EZDCommonHandler handler, NFCL0760BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NFCL0760BMsg scrnMsg) {
        scrnMsg.wrtOffRqstUsrId_H.setInputProtected(true);
        scrnMsg.wrtOffRqstGrpCd_H.setInputProtected(true);
        scrnMsg.xxTotAmt_H.setInputProtected(true);
        scrnMsg.arAdjRsnDescTxt_H.setInputProtected(true);
        scrnMsg.arAdjTpDescTxt_H.setInputProtected(true);
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        scrnMsg.arWrtOffNoteTxt_H.setInputProtected(true);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        scrnMsg.lowRmngBalAmt_H.setInputProtected(true);
        scrnMsg.highRmngBalAmt_H.setInputProtected(true);
        scrnMsg.lowInvNum_H.setInputProtected(true);
        scrnMsg.highInvNum_H.setInputProtected(true);
        scrnMsg.lowInvDueDt_H.setInputProtected(true);
        scrnMsg.highInvDueDt_H.setInputProtected(true);
        scrnMsg.lowDsAcctNum_H.setInputProtected(true);
        scrnMsg.highDsAcctNum_H.setInputProtected(true);
        scrnMsg.invTpDescTxt_H.setInputProtected(true);
        scrnMsg.inclConslInvFlg_H.setInputProtected(true);
        // START 2018/09/11 [QC#24884, ADD]
        handler.setButtonEnabled(BTN_LBL.ATTACHMENT.getOrgNm(), false);
        if (AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN.equals(scrnMsg.arWrtOffRqstTpCd_H.getValue())) {
            handler.setButtonEnabled(BTN_LBL.ATTACHMENT.getOrgNm(), true);
        }
        // END   2018/09/11 [QC#24884, ADD]
    }

    /**
     * controlScreenDetail
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     */
    private static void controlScreenDetail(EZDCommonHandler handler, NFCL0760BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenBottom
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     * @param isEnabled boolean
     */
    private static void controlScreenBottom(EZDCommonHandler handler, NFCL0760BMsg scrnMsg, boolean isEnabled) {
        handler.setButtonEnabled(BTN_LBL.DETAIL.getOrgNm(), isEnabled);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0760BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NFCL0760BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL0760_ABMsg abMsg = scrnMsg.A.no(i);
            // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
            //abMsg.arAdjRsnDescTxt_A.setInputProtected(true);
            //abMsg.arAdjTpDescTxt_A.setInputProtected(true);
            abMsg.adjDt_A.setInputProtected(true);
            // END 2017/08/21 T.Tsuchida [QC#19573,MOD]
            abMsg.billToCustAcctCd_A.setInputProtected(true);
            abMsg.dsAcctNm_A.setInputProtected(true);
            abMsg.arTrxNum_A.setInputProtected(true);
            abMsg.invTpDescTxt_A.setInputProtected(true);
            abMsg.dsInvTpDescTxt_A.setInputProtected(true);
            abMsg.dealOrigGrsAmt_A.setInputProtected(true);
            abMsg.invDueDt_A.setInputProtected(true);
            abMsg.dealApplyAdjRsvdAmt_A.setInputProtected(true);
            abMsg.dealRmngBalGrsAmt_A.setInputProtected(true);
            abMsg.arAdjNum_A.setInputProtected(true);
            abMsg.dealArAdjAmt_A.setInputProtected(true);
            // START 2018/02/28 J.Kim [QC#21143,MOD]
            // abMsg.procStsDescTxt_A.setInputProtected(true);
            abMsg.arDsWfStsDescTxt_A.setInputProtected(true);
            // END 2018/02/28 J.Kim [QC#21143,MOD]
            abMsg.wrtOffErrMsgTxt_A.setInputProtected(true);
        }
    }

    /**
     * initCommonButton
     * @param handler EZDCommonHandler
     */
    private static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

    }

    /**
     * setRowColors
     * @param scrnMsg NFCL0760BMsg
     * @param tblId String
     */
    private static void setRowColors(NFCL0760BMsg scrnMsg, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        // START 2016/03/10 T.Tsuchida [QC#5026,MOD]
//      try {
//          EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField(tblId).get(scrnMsg);
//          tblColor.setAlternateRowsBG(tblId, table);
//      } catch (Throwable e) {
//          e.printStackTrace();
//          throw new RuntimeException(e);
//      }
      // END 2016/03/10 T.Tsuchida [QC#5026,MOD]
      tblColor.setAlternateRowsBG(tblId, scrnMsg.A);
    }

}
