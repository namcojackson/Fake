package business.servlet.NFCL0510.common;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL0510.NFCL0510BMsg;
import business.servlet.NFCL0510.constant.NFCL0510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 11/30/2016   Fujitsu         S.Fujita        Update          QC#16255
 * 2018/06/11   Hitachi         Y.Takeno        Create          QC#19723
 * 2023/07/03   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFCL0510CommonLogic implements NFCL0510Constant {

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0510BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL0510BMsg scrnMsg, S21UserProfileService userProfileService) {
        scrnAppli.setButtonProperties("Click_Search", "Click_Search", "Search", 1, null);

        scrnAppli.setButtonProperties("btn1", "CMN_Save", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2018/06/11 [QC#19723, MOD]
        // scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        // END   2018/06/11 [QC#19723, MOD]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnMsg.setInputProtected(false);
        scrnMsg.xxTabProt_01.setInputProtected(true);
        scrnMsg.arLockBoxFileNm_BH.setInputProtected(true);
        scrnMsg.arLockBoxBatNum_BH.setInputProtected(true);

        // START 2018/06/11 [QC#19723, MOD]
        if (scrnMsg.B.getValidCount() > 0) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        }
        // END   2018/06/11 [QC#19723, MOD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).setInputProtected(true);
        }

        if (!hasFunctionUpdate(userProfileService)) {
            scrnMsg.setInputProtected(true);
            scrnMsg.A.setInputProtected(true);
            scrnMsg.B.setInputProtected(true);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0510BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setProtected(EZDCommonHandler scrnAppli, NFCL0510BMsg scrnMsg, S21UserProfileService userProfileService) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "arLockBoxFileNm_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "arLockBoxNm_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "rcvDt_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "arLockBoxStsCd_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "remBankRteNum_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "remDsBankAcctNum_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "lockBoxRecCnt_A#" + i);
            scrnMsg.clearGUIAttribute("NFCL0510Scrn00", "lockBoxTotAmt_A#" + i);
        }
        scrnMsg.clearAllGUIAttribute("NFCL0510Scrn00");

        // START 2018/06/11 [QC#19723, MOD]
        if (scrnMsg.B.getValidCount() > 0) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        // END   2018/06/11 [QC#19723, MOD]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (i > 0) {
                if (scrnMsg.A.no(i).arLockBoxFileNm_A.getValue().equals(scrnMsg.A.no(i - 1).arLockBoxFileNm_A.getValue())) {
                    setItemVisibility(scrnMsg, "arLockBoxFileNm_A#" + i, false);
                    setItemVisibility(scrnMsg, "arLockBoxNm_A#" + i, false);
                    setItemVisibility(scrnMsg, "rcvDt_A#" + i, false);
                    // START 2018/06/11 [QC#19723, MOD]
                    // setItemVisibility(scrnMsg, "arLockBoxStsCd_A#" + i, false);
                    setItemVisibility(scrnMsg, "arLockBoxStsCd_A#" + i, true);
                    // END   2018/06/11 [QC#19723, MOD]
                    setItemVisibility(scrnMsg, "remBankRteNum_A#" + i, false);
                    setItemVisibility(scrnMsg, "remDsBankAcctNum_A#" + i, false);
                    setItemVisibility(scrnMsg, "lockBoxRecCnt_A#" + i, false);
                    setItemVisibility(scrnMsg, "lockBoxTotAmt_A#" + i, false);
                } else {
                    setItemVisibility(scrnMsg, "arLockBoxFileNm_A#" + i, true);
                    setItemVisibility(scrnMsg, "arLockBoxNm_A#" + i, true);
                    setItemVisibility(scrnMsg, "rcvDt_A#" + i, true);
                    setItemVisibility(scrnMsg, "arLockBoxStsCd_A#" + i, true);
                    setItemVisibility(scrnMsg, "remBankRteNum_A#" + i, true);
                    setItemVisibility(scrnMsg, "remDsBankAcctNum_A#" + i, true);
                    setItemVisibility(scrnMsg, "lockBoxRecCnt_A#" + i, true);
                    setItemVisibility(scrnMsg, "lockBoxTotAmt_A#" + i, true);
                }
            }
            scrnMsg.A.no(i).setInputProtected(true);
            if (scrnMsg.A.no(i).manUsbleLockBoxStsFlg_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                if (hasFunctionUpdate(userProfileService)) {
                    scrnMsg.A.no(i).arLockBoxStsCd_A.setInputProtected(false);
                }
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (i > 0) {
                if (scrnMsg.B.no(i).arLockBoxBatNum_B.getValue().equals(scrnMsg.B.no(i - 1).arLockBoxBatNum_B.getValue()) && (scrnMsg.B.no(i).arLockBoxBatLineNum_B.getValue().equals(scrnMsg.B.no(i - 1).arLockBoxBatLineNum_B.getValue()))) {
                    setItemVisibility(scrnMsg, "arLockBoxBatNum_B#" + i, false);
                    setItemVisibility(scrnMsg, "arLockBoxBatLineNum_B#" + i, false);
                    setItemVisibility(scrnMsg, "custRcptNum_B#" + i, false);
                    setItemVisibility(scrnMsg, "custRcptAmt_B#" + i, false);
                    setItemVisibility(scrnMsg, "custBankRteNum_B#" + i, false);
                    // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//                    setItemVisibility(scrnMsg, "custDsBankAcctNum_B#" + i, false);
                    setItemVisibility(scrnMsg, "custDsBankAcctNum_BM#" + i, false);
                    // END 2023/07/03 S.Nakatani [QC#55645, MOD]
                } else {
                    setItemVisibility(scrnMsg, "arLockBoxBatNum_B#" + i, true);
                    setItemVisibility(scrnMsg, "arLockBoxBatLineNum_B#" + i, true);
                    setItemVisibility(scrnMsg, "custRcptNum_B#" + i, true);
                    setItemVisibility(scrnMsg, "custRcptAmt_B#" + i, true);
                    setItemVisibility(scrnMsg, "custBankRteNum_B#" + i, true);
                    // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//                    setItemVisibility(scrnMsg, "custDsBankAcctNum_B#" + i, true);
                    setItemVisibility(scrnMsg, "custDsBankAcctNum_BM#" + i, true);
                    // END 2023/07/03 S.Nakatani [QC#55645, MOD]
                }
            }
            scrnMsg.B.no(i).setInputProtected(true);
            if ((scrnMsg.B.no(i).arLockBoxStsCd_B.getValue().equals(AR_LOCK_BOX_STS.ERROR) || scrnMsg.B.no(i).arLockBoxStsCd_B.getValue().equals(AR_LOCK_BOX_STS.REPROCESS) || scrnMsg.B.no(i).arLockBoxStsCd_B.getValue().equals(AR_LOCK_BOX_STS.CANCELLED))
                     // START 2016/11/30 S.Fujita [QC#16255,MOD]
                    && (scrnMsg.B.no(i).arRcptRcvErrFlg_B.getValue().equals(ZYPConstant.FLG_ON_Y))) {
//                    && (scrnMsg.B.no(i).arRcptRcvErrFlg_B.getValue().equals(ZYPConstant.FLG_ON_Y)) && (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).vldCustRcptNum_B))) {
                     // END   2016/11/30 S.Fujita [QC#16255,MOD]
                if (hasFunctionUpdate(userProfileService)) {
                    scrnMsg.B.no(i).custRcptNum_B.setInputProtected(false);
                    scrnMsg.B.no(i).custRcptAmt_B.setInputProtected(false);
                    scrnMsg.B.no(i).custBankRteNum_B.setInputProtected(false);
                    // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//                    scrnMsg.B.no(i).custDsBankAcctNum_B.setInputProtected(false);
                    scrnMsg.B.no(i).custDsBankAcctNum_BM.setInputProtected(false);
                    // END 2023/07/03 S.Nakatani [QC#55645, MOD]
                    scrnMsg.B.no(i).payerCustCd_B.setInputProtected(false);
                    scrnMsg.B.no(i).custInvNum_B.setInputProtected(false);
                    scrnMsg.B.no(i).xxScrItem30Txt_B.setInputProtected(false);
                    scrnMsg.B.no(i).custInvAmt_B.setInputProtected(false);
                    
                }
            }
        }
    }

    /**
     * 
     * @param scrnMsg NFCL0510BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    public static void setItemVisibility(NFCL0510BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute("NFCL0510Scrn00", itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }

    /**
     * @param scrnMsg NFCL3020BMsg
     */
    public static void clearScrnBackgroundColor(NFCL0510BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFCL0510Scrn00");
    }

    /**
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean hasFunctionUpdate(S21UserProfileService userProfileService) {

        List<String> list = userProfileService.getFunctionCodeListForBizAppId(FUNC_ID);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(FUNCTION_UPDATE)) {
                return true;
            }
        }
        return false;
    }
}
