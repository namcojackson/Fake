/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1140.common;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import static business.servlet.NSAL1140.constant.NSAL1140Constant.*;
import business.servlet.NSAL1140.NSAL1140BMsg;
import business.servlet.NSAL1140.NSAL1140_ABMsg;

/**
 *<pre>
 * Supply Watch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Hitachi         T.Nishimura     Create          N/A
 * 2016/03/25   Hitachi         A.Kohinata      Update          QC#6051
 * 2016/03/30   Hitachi         A.Kohinata      Update          QC#6066
 * 2016/10/14   Hitachi         T.Tomita        Update          QC#15196
 *</pre>
 */
public class NSAL1140CommonLogic {

    /**
     * initCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    private static void initCommonButtonEdit(EZDCommonHandler scrnAppli) {
        scrnAppli.setButtonProperties(BTN_CMN_SAVE_BTN_NM, BTN_CMN_SAVE_EVENT_NM, BTN_CMN_SAVE_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT_BTN_NM, BTN_CMN_SUBMIT_EVENT_NM, BTN_CMN_SUBMIT_LBL_NM, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPLY_BTN_NM, BTN_CMN_APPLY_EVENT_NM, BTN_CMN_APPLY_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPROVE_BTN_NM, BTN_CMN_APPROVE_EVENT_NM, BTN_CMN_APPROVE_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_REJECT_BTN_NM, BTN_CMN_REJECT_EVENT_NM, BTN_CMN_REJECT_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, BTN_CMN_DOWNLOAD_EVENT_NM, BTN_CMN_DOWNLOAD_LBL_NM, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_DELETE_BTN_NM, BTN_CMN_DELETE_EVENT_NM, BTN_CMN_DELETE_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, BTN_CMN_CLEAR_EVENT_NM, BTN_CMN_CLEAR_LBL_NM, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET_BTN_NM, BTN_CMN_RESET_EVENT_NM, BTN_CMN_RESET_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LBL_NM, 1, null);
        scrnAppli.setButtonEnabled(VIEW_ITEM_BTN_NM, true);
        // START 2016/10/14 T.Tomita [QC#15196, ADD]
        scrnAppli.setButtonEnabled(SELECT_ALL_BTN_NM, true);
        scrnAppli.setButtonEnabled(UNSELECT_ALL_BTN_NM, true);
        // END 2016/10/14 T.Tomita [QC#15196, ADD]
    }

    /**
     * initCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    private static void initCommonButtonSearch(EZDCommonHandler scrnAppli) {
        scrnAppli.setButtonProperties(BTN_CMN_SAVE_BTN_NM, BTN_CMN_SAVE_EVENT_NM, BTN_CMN_SAVE_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT_BTN_NM, BTN_CMN_SUBMIT_EVENT_NM, BTN_CMN_SUBMIT_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPLY_BTN_NM, BTN_CMN_APPLY_EVENT_NM, BTN_CMN_APPLY_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPROVE_BTN_NM, BTN_CMN_APPROVE_EVENT_NM, BTN_CMN_APPROVE_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_REJECT_BTN_NM, BTN_CMN_REJECT_EVENT_NM, BTN_CMN_REJECT_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, BTN_CMN_DOWNLOAD_EVENT_NM, BTN_CMN_DOWNLOAD_LBL_NM, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_DELETE_BTN_NM, BTN_CMN_DELETE_EVENT_NM, BTN_CMN_DELETE_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, BTN_CMN_CLEAR_EVENT_NM, BTN_CMN_CLEAR_LBL_NM, 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET_BTN_NM, BTN_CMN_RESET_EVENT_NM, BTN_CMN_RESET_LBL_NM, 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LBL_NM, 1, null);
        scrnAppli.setButtonEnabled(VIEW_ITEM_BTN_NM, false);
        // START 2016/10/14 T.Tomita [QC#15196, ADD]
        scrnAppli.setButtonEnabled(SELECT_ALL_BTN_NM, false);
        scrnAppli.setButtonEnabled(UNSELECT_ALL_BTN_NM, false);
        // END 2016/10/14 T.Tomita [QC#15196, ADD]
    }

    /**
     * initialControlScreen.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0620BMsg
     */
    public static void initialControlScreen(EZDCommonHandler scrnAppli, NSAL1140BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() == 0) {
            initCommonButtonSearch(scrnAppli);
        } else {
            initCommonButtonEdit(scrnAppli);
            controlScreenDetailFields(scrnMsg);
        }
        controlScreenFooterFields(scrnMsg);
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

    private static void controlScreenFooterFields(NSAL1140BMsg scrnMsg) {
        boolean readonlyFlg = !hasUpdateFuncId();
        scrnMsg.svcSplyContrStsCd_1V.setInputProtected(readonlyFlg);
        scrnMsg.xxChkBox_F.setInputProtected(readonlyFlg);
        scrnMsg.ovwrtAbuseFlg_2V.setInputProtected(readonlyFlg);
        scrnMsg.psnCd_1V.setInputProtected(readonlyFlg);
        scrnMsg.abuseOvwrtRsnCd_1V.setInputProtected(readonlyFlg);
        scrnMsg.svcSplyExprDt.setInputProtected(readonlyFlg);
        scrnMsg.abuseVarPct.setInputProtected(readonlyFlg);
        scrnMsg.abuseVarPct.setAppFracDigit(2);
        scrnMsg.abuseActCd_1V.setInputProtected(readonlyFlg);
        scrnMsg.abuseActCmntTxt.setInputProtected(readonlyFlg);
    }

    /**
     * controlScreenDetailFields.
     * @param scrnMsg NSAL0620BMsg
     */
    public static final void controlScreenDetailFields(NSAL1140BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1140_ABMsg abMsg = scrnMsg.A.no(i);
            if (hasUpdateFuncId()) {
                abMsg.xxChkBox_A.setInputProtected(ZYPConstant.FLG_ON_Y.equals(abMsg.frzFlg_A.getValue()));
            } else {
                abMsg.xxChkBox_A.setInputProtected(true);
            }
            abMsg.abuseVarPct_A.setInputProtected(true);
            abMsg.abuseVarPct_A.setAppFracDigit(2);
            abMsg.noMainUnitCnt_A.setInputProtected(true);
            abMsg.xxRowNum_A.setInputProtected(true);
            abMsg.xxTotBaseAmt_A.setInputProtected(true);
            abMsg.xxTotBaseAmt_A.setAppFracDigit(0);
            abMsg.xxUsedQty_A.setInputProtected(true);
            abMsg.contrVrsnEffFromDt_A.setInputProtected(true);
            abMsg.procDt_A.setInputProtected(true);
            abMsg.termCondChkDt_A.setInputProtected(true);
            abMsg.abuseBcktCd_A.setInputProtected(true);
            abMsg.abuseFlg_A.setInputProtected(true);
            abMsg.abuseOvwrtRsnDescTxt_A.setInputProtected(true);
            abMsg.dsAcctGrpDescTxt_A.setInputProtected(true);
            abMsg.dsAcctNm_A.setInputProtected(true);
            abMsg.dsContrEdiDescTxt_A.setInputProtected(true);
            abMsg.dsContrNum_A.setInputProtected(true);
            abMsg.frzFlg_A.setInputProtected(true);
            abMsg.bllgCycleDescTxt_A.setInputProtected(true);
            abMsg.othContrAbuseFlg_A.setInputProtected(true);
            abMsg.ovwrtAbuseFlg_A.setInputProtected(true);
            abMsg.shipToCustAcctCd_A.setInputProtected(true);
            abMsg.svcContrBrDescTxt_A.setInputProtected(true);
            abMsg.svcCovTmplTpDescTxt_A.setInputProtected(true);
            abMsg.svcRgDescTxt_A.setInputProtected(true);
            if (ZYPCommonFunc.hasValue(abMsg.shipToCustAcctCd_A)) {
                abMsg.shipToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
                abMsg.shipToCustAcctCd_LK.setInputProtected(false);
            } else {
                abMsg.shipToCustAcctCd_LK.clear();
            }
            if (ZYPCommonFunc.hasValue(abMsg.dsContrNum_A)) {
                abMsg.dsContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
                abMsg.dsContrNum_LK.setInputProtected(false);
            } else {
                abMsg.dsContrNum_LK.clear();
            }
        }
        setTableBGColor(scrnMsg);
    }

    private static void setTableBGColor(NSAL1140BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.clearRowsBG("B", scrnMsg.B);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1140BMsg
     */
    public static void commonAddCheckItem(NSAL1140BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.abuseBcktCd_1V);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_01);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_01);
        scrnMsg.addCheckItem(scrnMsg.abuseFlg_1V);
        scrnMsg.addCheckItem(scrnMsg.ovwrtAbuseFlg_1V);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctGrpNm);
        scrnMsg.addCheckItem(scrnMsg.procDt_01);
        scrnMsg.addCheckItem(scrnMsg.procDt_02);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_1V);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_02);
        scrnMsg.addCheckItem(scrnMsg.dsContrEdiCd_1V);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);

    }

    /**
     * @param scrnMsg NSAL1140BMsg
     */
    public static void clearPopupParameter(NSAL1140BMsg scrnMsg) {

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
        scrnMsg.dsContrPk_P.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        scrnMsg.xxScrEventNm.clear();
        ZYPTableUtil.clear(scrnMsg.X);
    }
}
