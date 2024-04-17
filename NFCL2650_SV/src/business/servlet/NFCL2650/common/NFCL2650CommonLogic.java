/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650.common;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_APL;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_APR;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_CLR;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_DEL;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_DWL;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_RJT;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_RST;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_RTN;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_SAV;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_CMN_SUB;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_GET_BILL_TO_CUST;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_GET_CUST_NM_FROM;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BTN_GET_CUST_NM_TO;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.NFCM0023E;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.NFCM0808E;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.SCRN_ID_00;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.BIZ_ID;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.FUNC_ID_UPDATE;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.ZZSM4300E;


import java.util.List;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL2650.NFCL2650BMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL2650CommonLogic {

    /**
     * initControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2650BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NFCL2650BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        // Start 2016/12/22 H.Ikeda [QC#12865,MOD]
        initCommonButton(handler, scrnMsg);
        // End   2016/12/22 H.Ikeda [QC#12865,MOD]
        initButton(handler);
        initScreenField(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_FR);
    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler, NFCL2650BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        // Start 2016/12/22 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        // END   2016/12/22 H.Ikeda [QC#12865,MOD]
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
        handler.setButtonProperties(BTN_GET_CUST_NM_FROM[0], BTN_GET_CUST_NM_FROM[1], BTN_GET_CUST_NM_FROM[2], 1, null);
        handler.setButtonProperties(BTN_GET_CUST_NM_TO[0], BTN_GET_CUST_NM_TO[1], BTN_GET_CUST_NM_TO[2], 1, null);
        handler.setButtonProperties(BTN_GET_BILL_TO_CUST[0], BTN_GET_BILL_TO_CUST[1], BTN_GET_BILL_TO_CUST[2], 1, null);
    }

    /**
     * initScreenField
     * @param handler EZDCommonHandler
     * @param scrnMsg NFCL2650BMsg
     */
    public static void initScreenField(EZDCommonHandler handler, NFCL2650BMsg scrnMsg) {
        scrnMsg.acctDt.setInputProtected(true);
        scrnMsg.dsAcctNum_FR.setInputProtected(false);
        scrnMsg.dsAcctNum_TO.setInputProtected(false);
        scrnMsg.dsAcctNm_F1.setInputProtected(true);
        scrnMsg.dsAcctNm_T1.setInputProtected(true);
        scrnMsg.dsAcctNm_F2.setInputProtected(false);
        scrnMsg.dsAcctNm_T2.setInputProtected(false);
        // START 2016/11/25 T.Murai [QC#13259, MOD]
        // scrnMsg.billToCustCd.setInputProtected(false);
        // scrnMsg.dsAcctNm_BL.setInputProtected(true);
        scrnMsg.locNum.setInputProtected(false);
        scrnMsg.locNm.setInputProtected(true);
        // END 2016/11/25 T.Murai [QC#13259, MOD]
    }

    /**
     * submitCheck
     * @param scrnMsg NFCL2650BMsg
     */
    public static void submitCheck(NFCL2650BMsg scrnMsg) {
        if (!scrnMsg.dsAcctNum_FR.isClear() && !scrnMsg.dsAcctNum_TO.isClear() //
                && scrnMsg.dsAcctNm_F2.isClear() && scrnMsg.dsAcctNm_T2.isClear() && scrnMsg.locNum.isClear()) { // MOD 2016/11/25 [QC#13259] billToCustCd -> locNum
            if (scrnMsg.dsAcctNum_FR.getValue().compareTo(scrnMsg.dsAcctNum_TO.getValue()) > 0) {
                scrnMsg.dsAcctNum_FR.setErrorInfo(1, NFCM0023E);
                scrnMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0023E);
            }
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_FR);
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);

        } else if (scrnMsg.dsAcctNum_FR.isClear() && scrnMsg.dsAcctNum_TO.isClear() //
                && !scrnMsg.dsAcctNm_F2.isClear() && !scrnMsg.dsAcctNm_T2.isClear() && scrnMsg.locNum.isClear()) { // MOD 2016/11/25 [QC#13259] billToCustCd -> locNum
            if (scrnMsg.dsAcctNm_F2.getValue().compareTo(scrnMsg.dsAcctNm_T2.getValue()) > 0) {
                scrnMsg.dsAcctNm_F2.setErrorInfo(1, NFCM0023E);
                scrnMsg.dsAcctNm_T2.setErrorInfo(1, NFCM0023E);
            }
            scrnMsg.addCheckItem(scrnMsg.dsAcctNm_F2);
            scrnMsg.addCheckItem(scrnMsg.dsAcctNm_T2);
        } else if (scrnMsg.dsAcctNum_FR.isClear() && scrnMsg.dsAcctNum_TO.isClear() //
                && scrnMsg.dsAcctNm_F2.isClear() && scrnMsg.dsAcctNm_T2.isClear() && !scrnMsg.locNum.isClear()) { // MOD 2016/11/25 [QC#13259] billToCustCd -> locNum
            scrnMsg.addCheckItem(scrnMsg.locNum); // MOD 2016/11/25 [QC#13259] billToCustCd -> locNum
        } else {
            boolean errFlag = false;
            if (!scrnMsg.dsAcctNum_FR.isClear()) {
                scrnMsg.dsAcctNum_FR.setErrorInfo(1, NFCM0808E);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_FR);
                errFlag = true;
            }
            if (!scrnMsg.dsAcctNum_TO.isClear()) {
                scrnMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0808E);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);
                errFlag = true;
            }

            if (!scrnMsg.dsAcctNm_F2.isClear()) {
                scrnMsg.dsAcctNm_F2.setErrorInfo(1, NFCM0808E);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNm_F2);
                errFlag = true;
            }

            if (!scrnMsg.dsAcctNm_T2.isClear()) {
                scrnMsg.dsAcctNm_T2.setErrorInfo(1, NFCM0808E);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNm_T2);
                errFlag = true;
            }

            // START 2016/11/25 [QC#13259, MOD]
            // if (!scrnMsg.billToCustCd.isClear()) {
            // scrnMsg.billToCustCd.setErrorInfo(1, NFCM0808E);
            // scrnMsg.addCheckItem(scrnMsg.billToCustCd);
            // errFlag = true;
            // }
            if (!scrnMsg.locNum.isClear()) {
                scrnMsg.locNum.setErrorInfo(1, NFCM0808E);
                scrnMsg.addCheckItem(scrnMsg.locNum);
                errFlag = true;
            }
            // END   2016/11/25 [QC#13259, MOD]
            if (!errFlag) {
                scrnMsg.dsAcctNum_FR.setErrorInfo(1, NFCM0808E);
                scrnMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0808E);
                scrnMsg.dsAcctNm_F2.setErrorInfo(1, NFCM0808E);
                scrnMsg.dsAcctNm_T2.setErrorInfo(1, NFCM0808E);
                scrnMsg.locNum.setErrorInfo(1, NFCM0808E); // MOD 2016/11/25 [QC#13259] billToCustCd -> locNum
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_FR);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNm_F2);
                scrnMsg.addCheckItem(scrnMsg.dsAcctNm_T2);
                scrnMsg.addCheckItem(scrnMsg.locNum); // MOD 2016/11/25 [QC#13259] billToCustCd -> locNum
            }
        }
    }

    // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL2650BMsg scrnMsg) {
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
}
