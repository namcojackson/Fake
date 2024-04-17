/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2640.common;

import static business.servlet.NFCL2640.constant.NFCL2640Constant.BIZ_ID;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_ADD;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_APL;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_APR;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_CLR;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_DEL;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_DWL;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_RJT;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_RST;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_RTN;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_SAV;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_CMN_SUB;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.BTN_SEARCH;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.FUNC_ID_UPDATE;
import static business.servlet.NFCL2640.constant.NFCL2640Constant.ZZSM4300E;

import java.util.Calendar;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL2640.NFCL2640BMsg;
import business.servlet.NFCL2640.NFCL2640_ABMsg;
import business.servlet.NFCL2640.constant.NFCL2640Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 * 2019/02/26   Fujitsu         H.Ikeda         Update          QC#30302
 *</pre>
 */
public class NFCL2640CommonLogic {
    /**
     * initControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2640BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NFCL2640BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(NFCL2640Constant.SCRN_ID_00);
        initCommonButton(handler);
        initButton(handler);
        scrnMsg.setFocusItem(scrnMsg.arStmtIssCycleCd);
    }

    /**
     * searchControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2640BMsg
     * @param glblCmpyCd String
     */
    public static void searchControlScreen(EZDCommonHandler handler, NFCL2640BMsg scrnMsg, String glblCmpyCd) {
        // Start 2016/12/22 H.Ikeda [QC#12865,MOD]
        activeSubmitButton(handler, scrnMsg);
        // Emd   2016/12/22 H.Ikeda [QC#12865,MOD]
        searchScreenField(handler, scrnMsg, glblCmpyCd);

    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * initButton
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(BTN_ADD[0], BTN_ADD[1], BTN_ADD[2], 1, null);
    }

    /**
     * searchButton
     * @param handler EZDCommonHandler
     */
    public static void activeSubmitButton(EZDCommonHandler handler, NFCL2640BMsg scrnMsg) {
        // Start 2016/12/22 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            handler.setButtonEnabled(BTN_CMN_SUB[0], true);
        }
        // End   2016/12/22 H.Ikeda [QC#12865,MOD]
    }

    /**
     * searchScreenField
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2640BMsg
     * @param glblCmpyCd String
     */
    public static void searchScreenField(EZDCommonHandler handler, NFCL2640BMsg scrnMsg, String glblCmpyCd) {
        scrnMsg.arStmtIssCycleCd.setInputProtected(true);
        scrnMsg.arStmtDt_FR.setInputProtected(true);
        scrnMsg.arStmtDt_TO.setInputProtected(true);
        scrnMsg.arStmtStsCd.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFCL2640_ABMsg scrnMsgA = scrnMsg.A.no(i);
            scrnMsgA.arStmtDt_A1.setInputProtected(true);

            if (AR_STMT_STS.PENDING.equals(scrnMsgA.arStmtStsCd_A1.getValue())) {
                scrnMsgA.arStmtStsCd_A1.setInputProtected(false);
                scrnMsgA.lateFeeCalcFlg_A1.setInputProtected(false);

            } else if (AR_STMT_STS.SKIP.equals(scrnMsgA.arStmtStsCd_A1.getValue()) //
                    && scrnMsg.arStmtDt_NW.getValue().compareTo(scrnMsgA.arStmtDt_A1.getValue()) <= 0) {
                scrnMsgA.arStmtStsCd_A1.setInputProtected(false);
                scrnMsgA.lateFeeCalcFlg_A1.setInputProtected(true);

            } else {
                scrnMsgA.arStmtStsCd_A1.setInputProtected(true);
                scrnMsgA.lateFeeCalcFlg_A1.setInputProtected(true);
            }

            scrnMsgA.arStmtDataCratDt_A1.setInputProtected(true);
            scrnMsgA.arStmtPrintDt_A1.setInputProtected(true);
        }
    }

    /**
     * addScreenField
     * @param scrnMsg NFCL2640BMsg
     */
    public static void addScreenField(NFCL2640BMsg scrnMsg) {
        scrnMsg.arStmtIssCycleCd.setInputProtected(true);
        scrnMsg.arStmtDt_FR.setInputProtected(true);
        scrnMsg.arStmtDt_TO.setInputProtected(true);
        scrnMsg.arStmtStsCd.setInputProtected(true);

        for (int i = scrnMsg.B.getValidCount(); i < scrnMsg.A.getValidCount(); i++) {
            NFCL2640_ABMsg scrnMsgA = scrnMsg.A.no(i);
            scrnMsgA.arStmtDt_A1.setInputProtected(true);
            scrnMsgA.arStmtStsCd_A1.setInputProtected(true);
            scrnMsgA.arStmtDataCratDt_A1.setInputProtected(true);
            scrnMsgA.arStmtPrintDt_A1.setInputProtected(true);
            scrnMsgA.lateFeeCalcFlg_A1.setInputProtected(false);
        }
    }

    /**
     * clearField
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2640BMsg
     */
    public static void clearField(EZDCommonHandler handler, NFCL2640BMsg scrnMsg) {
        scrnMsg.arStmtIssCycleCd.setInputProtected(false);
        scrnMsg.arStmtDt_FR.setInputProtected(false);
        scrnMsg.arStmtDt_TO.setInputProtected(false);
        scrnMsg.arStmtStsCd.setInputProtected(false);
    }

    // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL2640BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/22 H.Ikeda [QC#12865,ADD]

    // Start 2019/2/26 H.Ikeda [QC#30302,ADD]
    /**
     * getArStmtIssCycleCd
     * 
     * @param arStmtIssCycleCd String
     * @param arStmtDay        String
     * @return String
     */
    public static String getArStmtIssCycleCd(String arStmtIssCycleCd, String arStmtDay) { 

        if (AR_STMT_ISS_CYCLE.MONTHLY_LAST.equals(arStmtIssCycleCd)) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(arStmtDay.substring(0, 4)));
            calendar.set(Calendar.MONTH, Integer.parseInt(arStmtDay.substring(4, 6)) - 1);

            return String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        } else {
            return arStmtIssCycleCd;
        }
    }
    // End  2019/2/26 H.Ikeda [QC#30302,ADD]
}
