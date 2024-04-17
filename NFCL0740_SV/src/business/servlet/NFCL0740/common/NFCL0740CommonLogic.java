/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0740.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL0740.NFCL0740BMsg;
import business.servlet.NFCL0740.constant.NFCL0740Constant;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Write-Off Request Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#8784
 *</pre>
 */
public class NFCL0740CommonLogic implements NFCL0740Constant {

    /**
     * Initialize
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL0740BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL0740BMsg scrnMsg, S21UserProfileService userProfileService) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("Click_SetCustomerName1", "Click_SetCustomerName1", ">>", 1, null);
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // scrnAppli.setButtonProperties("Click_SetCustomerName2",
        // "Click_SetCustomerName2", ">>", 1, null);
        // END 2016/07/08 K.Kojima [QC#8784,DEL]

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnMsg.arAdjRsnCd_H1.setIndispensable(true);
        scrnMsg.arAdjTpCd_H1.setIndispensable(true);
        scrnMsg.dealRmngBalGrsAmt_H1.setIndispensable(true);
        scrnMsg.dealRmngBalGrsAmt_H2.setIndispensable(true);

        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // scrnMsg.dsAcctNm_H2.setInputProtected(true);
        // END 2016/07/08 K.Kojima [QC#8784,DEL]

        scrnMsg.xxRadioBtn.setValue(1); // Generate Report Only
    }

    /**
     * Clear Popup Parameters
     * @param scrnMsg NFCL0740BMsg
     */
    public static void clearPopPrm(NFCL0740BMsg scrnMsg) {
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
    }

}
