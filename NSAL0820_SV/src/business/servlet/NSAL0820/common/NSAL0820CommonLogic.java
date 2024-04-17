/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820.common;

import static business.servlet.NSAL0820.constant.NSAL0820Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0820.NSAL0820Bean;
import business.servlet.NSAL0820.NSAL0820BMsg;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/04/04   Hitachi         T.Iwamoto       Update          QC#5822
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/07/14   Hitachi         M.Gotou         Update          QC#11832
 * 2016/07/19   Hitachi         M.Gotou         Update          QC#11854
 * 2016/07/25   Hitachi         Y.Takeno        Update          QC#11826
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#12141
 * 2016/08/24   Hitachi         T.Mizuki        Update          QC#11836
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0820CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0820BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0820BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId()) {
            // START 2016/07/25 [QC#11826, MOD]
            // if
            // (MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue())) {
            if (MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue()) && scrnMsg.A.getValidCount() > 0) {
                // END 2016/07/25 [QC#11826, MOD]
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 1, null);
                handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
                handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            }
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0820BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenTableFields(handler, scrnMsg);
        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // focus set add
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.contrIntfcSrcTpCd_SS);
        }
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]

    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Actual Counters for Interface (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
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
     * @param scrnMsg NSAL0820BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {
        // START 2016/07/27 M.Gotou [QC#12141, ADD]
        handler.setButtonEnabled("AddRow", false);
        // END 2016/07/27 M.Gotou [QC#12141, ADD]
        if (hasUpdateFuncId()) {
            if (MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue())) {
                handler.setButtonEnabled("OpenForCorrection", false);
                // START 2016/07/25 [QC#11826, MOD]
                if (scrnMsg.A.getValidCount() > 0) {
                    handler.setButtonEnabled("SelectAll", true);
                    handler.setButtonEnabled("UnselectAll", true);
                    handler.setButtonEnabled("ValidateData", true);
                } else {
                    handler.setButtonEnabled("SelectAll", false);
                    handler.setButtonEnabled("UnselectAll", false);
                    handler.setButtonEnabled("ValidateData", false);
                }
                // END 2016/07/25 [QC#11826, MOD]
            } else {
                handler.setButtonEnabled("OpenForCorrection", true);
                handler.setButtonEnabled("SelectAll", false);
                handler.setButtonEnabled("UnselectAll", false);
                handler.setButtonEnabled("ValidateData", false);
            }
            handler.setButtonEnabled("Upload", true);
            // START 2016/07/27 M.Gotou [QC#12141, ADD]
            if (scrnMsg.xxExstFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                handler.setButtonEnabled("AddRow", true);
            }
            // END 2016/07/27 M.Gotou [QC#12141, ADD]
        } else {
            handler.setButtonEnabled("OpenForCorrection", false);
            handler.setButtonEnabled("Upload", false);
            handler.setButtonEnabled("SelectAll", false);
            handler.setButtonEnabled("UnselectAll", false);
            handler.setButtonEnabled("ValidateData", false);
        }

        scrnMsg.dsContrIntfcBatNum_S.setInputProtected(true);

    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL0820BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/05/25 T.Tomita [QC#8898, MOD]
            if (hasUpdateFuncId() && MODE_AFTER_OPEN.equals(scrnMsg.xxModeCd.getValue()) && !DS_CONTR_PROC_STS.COMPLEATED.equals(scrnMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).dsContrSrcRefNum_A.setInputProtected(false);
                scrnMsg.A.no(i).contrIntfcSrcTpCd_A.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A.setInputProtected(false);
                scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A.setInputProtected(false);
                // scrnMsg.A.no(i).physMtrLbCd_A.setInputProtected(false);
                scrnMsg.A.no(i).bllblFlg_A.setInputProtected(false);
                scrnMsg.A.no(i).contrMtrMultRate_A.setInputProtected(false);
                // scrnMsg.A.no(i).bllgMtrLbCd_A.setInputProtected(false);
                scrnMsg.A.no(i).intgMdseCd_A.setInputProtected(false);
                // mod start 2016/08/24 CSA QC#11836
                scrnMsg.A.no(i).mtrLbDescTxt_AP.setInputProtected(false);
                scrnMsg.A.no(i).mtrLbDescTxt_AB.setInputProtected(false);
                // mod end 2016/08/24 CSA QC#11836
                handler.setButtonEnabled("OpenWin_Machine", i, true);
                handler.setButtonEnabled("OpenWin_Merchandise", i, true);
                handler.setButtonEnabled("OpenWin_PhysMtr", i, true);
                handler.setButtonEnabled("OpenWin_BllgMtr", i, true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrSrcRefNum_A.setInputProtected(true);
                scrnMsg.A.no(i).contrIntfcSrcTpCd_A.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A.setInputProtected(true);
                scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
                // scrnMsg.A.no(i).physMtrLbCd_A.setInputProtected(true);
                scrnMsg.A.no(i).bllblFlg_A.setInputProtected(true);
                scrnMsg.A.no(i).contrMtrMultRate_A.setInputProtected(true);
                // scrnMsg.A.no(i).bllgMtrLbCd_A.setInputProtected(true);
                scrnMsg.A.no(i).intgMdseCd_A.setInputProtected(true);
                // mod start 2016/08/24 CSA QC#11836
                scrnMsg.A.no(i).mtrLbDescTxt_AP.setInputProtected(true);
                scrnMsg.A.no(i).mtrLbDescTxt_AB.setInputProtected(true);
                // mod end 2016/08/24 CSA QC#11836
                handler.setButtonEnabled("OpenWin_Machine", i, false);
                handler.setButtonEnabled("OpenWin_Merchandise", i, false);
                handler.setButtonEnabled("OpenWin_PhysMtr", i, false);
                handler.setButtonEnabled("OpenWin_BllgMtr", i, false);
            }
            scrnMsg.A.no(i).intfcErrMsgTxt_A.setInputProtected(true);
            // END 2016/05/25 T.Tomita [QC#8898, MOD]
            // START 2016/07/14 M.Gotou [QC#11832, ADD]
            scrnMsg.A.no(i).contrMtrMultRate_A.setAppFracDigit(2);
            // END 2016/07/14 M.Gotou [QC#11832, ADD]
        }

        S21TableColorController control = new S21TableColorController(SCRN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * <pre>
     * checkInput for table
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0820BMsg
     */
    public static final void checkInputForTable(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NSAL0820Bean.dsContrSrcRefNum_A, NSAL0820Bean.serNum_A, NSAL0820Bean.svcMachMstrPk_A, NSAL0820Bean.mdseCd_A, NSAL0820Bean.contrMtrMultRate_A, NSAL0820Bean.intgMdseCd_A,
                NSAL0820Bean.physMtrLbCd_A, NSAL0820Bean.bllgMtrLbCd_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
    }

    // ADD start 2016/04/22 CSA Defect#6691
    /**
     * set error info to "Proc Message" field.
     * @param scrnMsg NSAL0820BMsg
     */
    public static void setErrorInfoForTable(NSAL0820BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/07/19 M.Gotou [QC#11854, MOD]
            if (hasValue(scrnMsg.A.no(i).xxMsgId_A)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).intfcErrMsgTxt_A);
                scrnMsg.A.no(i).intfcErrMsgTxt_A.clearErrorInfo();
                scrnMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0541E);
            }
            // END 2016/07/19 M.Gotou [QC#11854, MOD]
        }
    }

    // ADD end 2016/04/22 CSA Defect#6691

    /**
     * set error info to "Proc Message" field.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0820BMsg
     */
    public static void setErrorInfo(EZDCommonHandler handler, NSAL0820BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // MOD start 2016/04/04 CSA Defect#5822
            // START 2016/07/19 M.Gotou [QC#11854, MOD]
            if (hasValue(scrnMsg.A.no(i).xxMsgId_A) && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).intfcErrMsgTxt_A);
                scrnMsg.A.no(i).intfcErrMsgTxt_A.clearErrorInfo();
                scrnMsg.A.no(i).intfcErrMsgTxt_A.setErrorInfo(1, NSAM0541E);
            }
            // END 2016/07/19 M.Gotou [QC#11854, MOD]
            // MOD end 2016/04/04 CSA Defect#5822
        }
    }
}
