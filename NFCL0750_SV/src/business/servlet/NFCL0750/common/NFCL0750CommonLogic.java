/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0750.common;

import static business.servlet.NFCL0750.constant.NFCL0750Constant.SCREEN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL0750.NFCL0750BMsg;
import business.servlet.NFCL0750.NFCL0750_ABMsg;
import business.servlet.NFCL0750.constant.NFCL0750Constant.BTN_LBL;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/10   Hitachi         T.Tsuchida      Update          QC#5025
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 *</pre>
 */
public class NFCL0750CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NFCL0750BMsg
     */
    public static void addCheckItem(NFCL0750BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.wrtOffRqstUsrId_H);
        scrnMsg.addCheckItem(scrnMsg.wrtOffRqstGrpCd_H);
        scrnMsg.addCheckItem(scrnMsg.arAdjRsnDescTxt_SV);
        scrnMsg.addCheckItem(scrnMsg.arAdjTpDescTxt_SV);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.arWrtOffRqstTpDescTxt_SV);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_R);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_A);
        // START 2018/02/28 J.Kim [QC#21141,MOD]
        // scrnMsg.addCheckItem(scrnMsg.procStsDescTxt_SV);
        scrnMsg.addCheckItem(scrnMsg.arDsWfStsDescTxt_SV);
        // END 2018/02/28 J.Kim [QC#21141,MOD]
        // START 2018/04/20 J.Kim [QC#24885,ADD]
        scrnMsg.addCheckItem(scrnMsg.cltDispTpCd_H);
        // END 2018/04/20 J.Kim [QC#24885,ADD]
    }

    /**
     * Clear Data
     * @param scrnMsg NFCL0750BMsg
     */
    public static void clearData(NFCL0750BMsg scrnMsg) {
        setValue(scrnMsg.xxChkBox_R, ZYPConstant.CHKBOX_ON_Y);
        setValue(scrnMsg.xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
    }
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
     * @param scrnMsg NFCL0750BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NFCL0750BMsg scrnMsg) {
        initCommonButton(handler);
        controlScreenHeader(handler, scrnMsg);
        controlScreenDetail(handler, scrnMsg);
        controlScreenBottom(handler, scrnMsg, false);
        clearData(scrnMsg);
    }

    /**
     * <pre>
     * The Search Event of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0750BMsg
     */
    public static final void srchControlScreen(EZDCommonHandler handler, NFCL0750BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetail(handler, scrnMsg);
            controlScreenBottom(handler, scrnMsg, true);
        }
    }

    /**
     * Control screen header
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0750BMsg
     */
    private static final void controlScreenHeader(EZDCommonHandler handler, NFCL0750BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0750BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NFCL0750BMsg scrnMsg) {
        scrnMsg.wrtOffRqstUsrId_H.setInputProtected(false);
        scrnMsg.wrtOffRqstGrpCd_H.setInputProtected(false);
        scrnMsg.arAdjRsnDescTxt_SV.setInputProtected(false);
        scrnMsg.arAdjTpDescTxt_SV.setInputProtected(false);
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        scrnMsg.dsAcctNum_H.setInputProtected(false);
        scrnMsg.arWrtOffRqstTpDescTxt_SV.setInputProtected(false);
        scrnMsg.dsAcctNum_H.setInputProtected(false);
        scrnMsg.xxFromDt_H.setInputProtected(false);
        scrnMsg.xxThruDt_H.setInputProtected(false);
        scrnMsg.xxChkBox_R.setInputProtected(false);
        scrnMsg.xxChkBox_A.setInputProtected(false);
        // START 2018/02/28 J.Kim [QC#21143,MOD]
        // scrnMsg.procStsDescTxt_SV.setInputProtected(false);
        scrnMsg.arDsWfStsDescTxt_SV.setInputProtected(false);
        // END 2018/02/28 J.Kim [QC#21143,MOD]
        // START 2018/04/20 J.Kim [QC#24885,ADD]
        scrnMsg.cltDispTpCd_H.setInputProtected(false);
        // END 2018/04/20 J.Kim [QC#24885,ADD]
    }

    /**
     * controlScreenDetail
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0750BMsg
     */
    private static void controlScreenDetail(EZDCommonHandler handler, NFCL0750BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg, "A");
        }
    }

    /**
     * controlScreenBottom
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0750BMsg
     * @param isEnabled boolean
     */
    private static void controlScreenBottom(EZDCommonHandler handler, NFCL0750BMsg scrnMsg, boolean isEnabled) {
        handler.setButtonEnabled(BTN_LBL.DETAIL.getOrgNm(), isEnabled);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL0750BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NFCL0750BMsg scrnMsg) {

        scrnMsg.xxRadioBtn_A.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL0750_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxRowNum_A.setInputProtected(false);
            abMsg.arWrtOffRqstPk_A.setInputProtected(true);
            abMsg.wrtOffRqstUsrId_A.setInputProtected(true);
            abMsg.wrtOffRqstGrpCd_A.setInputProtected(true);
            abMsg.arWrtOffRqstTpCd_A.setInputProtected(true);
            abMsg.arWrtOffRqstTpDescTxt_A.setInputProtected(true);
            abMsg.arAdjRsnDescTxt_A.setInputProtected(true);
            abMsg.arAdjTpDescTxt_A.setInputProtected(true);
            // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
            abMsg.arWrtOffNoteTxt_A.setInputProtected(true);
            // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
            abMsg.xxGenlFldAreaTxt_FB.setInputProtected(true);
            abMsg.xxGenlFldAreaTxt_IN.setInputProtected(true);
            abMsg.xxGenlFldAreaTxt_DT.setInputProtected(true);
            abMsg.xxGenlFldAreaTxt_CN.setInputProtected(true);
            abMsg.inclConslInvFlg_A.setInputProtected(true);
            // START 2018/02/28 J.Kim [QC#21143,MOD]
            // abMsg.procStsDescTxt_A.setInputProtected(true);
            abMsg.arDsWfStsDescTxt_A.setInputProtected(true);
            // END 2018/02/28 J.Kim [QC#21143,MOD]
            // START 2018/03/30 J.Kim [QC#21721,MOD]
            abMsg.wrtOffApvlUsrNm_A.setInputProtected(true);
            // END 2018/03/30 J.Kim [QC#21721,MOD]
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
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);

    }

    /**
     * setRowColors
     * @param scrnMsg NFCL0750BMsg
     * @param tblId String
     */
    private static void setRowColors(NFCL0750BMsg scrnMsg, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        // START 2016/03/10 T.Tsuchida [QC#5025,MOD]
//        try {
//            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField(tblId).get(scrnMsg);
//            tblColor.setAlternateRowsBG(tblId, table);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        // END 2016/03/10 T.Tsuchida [QC#5025,MOD]
        tblColor.setAlternateRowsBG(tblId, scrnMsg.A);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NFCL0750BMsg
     */
    public static void initParam(NFCL0750BMsg scrnMsg) {
        scrnMsg.arWrtOffRqstPk_P.clear();
        scrnMsg.wrtOffRqstGrpCd_P.clear();
        scrnMsg.wrtOffRqstUsrId_P.clear();
        scrnMsg.arWrtOffRqstTpCd_P.clear();
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
    }

}
