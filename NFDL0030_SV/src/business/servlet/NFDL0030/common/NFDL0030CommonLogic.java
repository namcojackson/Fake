package business.servlet.NFDL0030.common;

import java.math.BigDecimal;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFDL0030.NFDL0030BMsg;
import business.servlet.NFDL0030.NFDL0030_ABMsgArray;
import business.servlet.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 2016/05/09   Fujitsu         S.Fujita        Update          QC#7021
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#9829
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10203
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/02/22   Fujitsu         T.Murai         Update          QC#16811
 * 2018/04/24   Hitachi         Y.Takeno        Update          QC#20940
 * 2019/02/07   Fujitsu         S.Ohki          Update          QC#30023
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030CommonLogic implements NFDL0030Constant {

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL3010BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0030BMsg scrnMsg, S21UserProfileService userProfileService) {

        scrnMsg.setInputProtected(false);
        scrnAppli.setButtonProperties("AddLine", "AddLine", "Add", 1, null);
        scrnAppli.setButtonProperties("DeleteLine", "DeleteLine", "Del", 1, null);
        scrnAppli.setButtonProperties("OpenWin_CreditCardEntry", "OpenWin_CreditCardEntry", "Credit Card Entry", 1, null);
        scrnAppli.setButtonProperties("SearchInvoice", "SearchInvoice", ">>", 1, null);
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        scrnAppli.setButtonProperties("AddOnAcctLine", "AddOnAcctLine", "On Account", 1, null);
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        scrnAppli.setButtonProperties("btn1", "CMN_Save", "Save", 0, null);
        // Start 2016/12/27 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // End    2016/12/27 H.Ikeda [QC#12865,MOD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        scrnMsg.crCardTpNm_H.setInputProtected(true);
        scrnMsg.crCardLastDigitNum_H.setInputProtected(true);
        scrnMsg.crCardExprYrMth_H.setInputProtected(true);
        scrnMsg.crCardHldNm_H.setInputProtected(true);
        scrnMsg.xxAllLineAddr_H.setInputProtected(true);
        // START 2016/08/01 T.Tsuchida [QC#9829,ADD]
        scrnMsg.crCardCustRefNum_H.setInputProtected(true);
        // END 2016/08/01 T.Tsuchida [QC#9829,ADD]
        // START 2018/05/15 E.Kameishi [QC#24833,ADD]
        scrnMsg.crCardCustRefNum_H2.setInputProtected(true);
        // END 2018/05/15 E.Kameishi [QC#24833,ADD]
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        scrnMsg.bankRteNum_H.setInputProtected(true);
        scrnMsg.dsBankAcctNum_H.setInputProtected(true);
        scrnMsg.sellToCustLocNm_H.setInputProtected(true);
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(true);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dealNetRcptAmt_A1, scrnMsg.A.no(i).dealRmngBalGrsAmt_A1);
            } else {
                scrnMsg.A.no(i).dealNetRcptAmt_A1.setInputProtected(false);
            }
        }

        if (!hasFunctionUpdate(userProfileService)) {
            scrnMsg.setInputProtected(true);
            scrnMsg.A.setInputProtected(true);
            scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        }

        // START 2016/08/26 K.Kojima [QC#13204,ADD]
        if (scrnMsg.xxModeCd.getValue().equals(MODE_INVOICE)) {
            scrnMsg.dealNetRcptAmt_H.setInputProtected(true);
        }
        // END 2016/08/26 K.Kojima [QC#13204,ADD]
    }

//    /**
//     * @param userProfileService S21UserProfileService
//     * @return boolean
//     */
//    public static boolean hasFunctionUpdate(S21UserProfileService userProfileService) {
//
//        List<String> list = userProfileService.getFunctionCodeListForBizAppId(FUNC_ID);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).equals(FUNCTION_UPDATE)) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean hasFunctionUpdate(S21UserProfileService userProfileService) {
        return true;
    }

    // START 2016/05/09 S.Fujita [QC#7021,MOD]
    /**
     * inputCheckHeader
     * @param scrnMsg NFDL0030BMsg
     */
    public static void clearScreen(NFDL0030BMsg scrnMsg) {

        scrnMsg.dealNetRcptAmt_H.clear();
        scrnMsg.crCardTpNm_H.clear();
        scrnMsg.crCardLastDigitNum_H.clear();
        scrnMsg.crCardExprYrMth_H.clear();
        scrnMsg.crCardHldNm_H.clear();
        // START 2016/08/01 T.Tsuchida [QC#9829,ADD]
        scrnMsg.crCardCustRefNum_H.clear();
        // END 2016/08/01 T.Tsuchida [QC#9829,ADD]
        scrnMsg.xxAllLineAddr_H.clear();
        // START 2018/04/24 [QC#20940, ADD]
        scrnMsg.dtlNoteTxt_H.clear();
        // END   2018/04/24 [QC#20940, ADD]
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        scrnMsg.dsPmtMethCd_H.clear();
        scrnMsg.bankRteNum_H.clear();
        scrnMsg.dsBankAcctNum_H.clear();
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        NFDL0030_ABMsgArray lineMsgArray = scrnMsg.A;
        if (lineMsgArray.getValidCount() > 0) {
            for (int i = 0; i < lineMsgArray.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.clear();
                scrnMsg.A.no(i).xxChkBox_A2.clear();
                scrnMsg.A.no(i).dealNetRcptAmt_A1.clear();
            }
        }
    }
    // END 2016/05/09 S.Fujita [QC#7021,MOD]

    // START 2016/08/26 K.Kojima [QC#10203,ADD]
    /**
     * calculationTotalAmountForModeInvoice
     * @param scrnMsg NFDL0030BMsg
     */
    public static void calculationTotalAmountForModeInvoice(NFDL0030BMsg scrnMsg) {
        if (scrnMsg == null || !ZYPCommonFunc.hasValue(scrnMsg.xxModeCd) || !scrnMsg.xxModeCd.getValue().equals(MODE_INVOICE)) {
            return;
        }
        BigDecimal dealNetRcptAmt_H = BigDecimal.ZERO;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dealNetRcptAmt_A1)) {
                dealNetRcptAmt_H = dealNetRcptAmt_H.add(scrnMsg.A.no(i).dealNetRcptAmt_A1.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dealNetRcptAmt_H, dealNetRcptAmt_H);
    }
    // END 2016/08/26 K.Kojima [QC#10203,ADD]

    // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFDL0030BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(FUNC_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNCTION_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/27 H.Ikeda [QC#12865,ADD]

    // START 2017/02/22 [QC#16811, ADD]
    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFCL3010BMsg
     */
    public static void submitSuccessScreen(EZDCommonHandler scrnAppli, NFDL0030BMsg scrnMsg) {

        scrnMsg.setInputProtected(true);
        scrnAppli.setButtonProperties("AddLine", "AddLine", "Add", 0, null);
        scrnAppli.setButtonProperties("DeleteLine", "DeleteLine", "Del", 0, null);
        scrnAppli.setButtonProperties("OpenWin_CreditCardEntry", "OpenWin_CreditCardEntry", "Credit Card Entry", 0, null);
        scrnAppli.setButtonProperties("SearchInvoice", "SearchInvoice", ">>", 0, null);
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        scrnAppli.setButtonProperties("AddOnAcctLine", "AddOnAcctLine", "On Account", 0, null);
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        scrnAppli.setButtonProperties("btn1", "CMN_Save", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);
        scrnAppli.setButtonProperties("btn9", "", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).setInputProtected(true);
        }
    }
    // END   2017/02/22 [QC#16811, ADD]

    // START 2019/02/07 S.Ohki [QC#30023,ADD]
    /**
     * payFullAllCheckOn
     * @param scrnMsg NFDL0030BMsg
     */
    public static void payFullAllCheckOn(NFDL0030BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
        }
    }
    // END   2019/02/07 S.Ohki [QC#30023,ADD]

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /**
     * clearPopupParam
     * @param scrnMsg NFDL0030BMsg
     */
    public static void clearPopupParam(NFDL0030BMsg scrnMsg) {
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
        scrnMsg.xxScrEventNm.clear();
    }
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]
}
