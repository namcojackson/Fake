package business.servlet.NFCL3030.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL3030.NFCL3030BMsg;
import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 05/16/2016   Fujitsu         S.Fujita        Update          QC#6457
 * 07/25/2016   Hitachi         J.Kim           Update          QC#9476
 * 08/09/2016   Hitachi         T.Tsuchida      Update          QC#9476
 * 08/10/2016   Hitachi         J.Kim           Update          QC#9476
 * 08/23/2016   Hitachi         T.Tsuchida      Update          QC#13570
 * 2016/08/31   Hitachi         T.Tsuchida      Update          QC#13565
 * 2016/11/02   Fujitsu         R.Nakamura      Update          QC#22194
 * 2018/01/31   Fujitsu         T.Murai         Update          QC#21401
 * 2018/02/02   Fujitsu         T.Murai         Update          QC#21372
 * 2018/02/06   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/04/04   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 2018/06/06   Hitachi         E.Kameishi      Update          QC#22758
 * 2018/08/22   CITS            K.Kameoka       Update          QC#24746
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2018/10/09   Fujitsu         T.Ogura         Update          QC#28166
 * 2018/11/13   Fujitsu         S.Ohki          Update          QC#29149
 * 2019/03/25   Fujitsu         S.Takami        Update          QC#30902
 * 2019/08/13   Fujitsu         H.Ikeda         Update          QC#52593
 * 2019/09/11   Fujitsu         H.Ikeda         Update          QC#53382
 * 2020/01/01   Fujitsu         Y.Matsui        Update          QC#55260,QC#55263
 * 2020/06/05   CITS            R.Kurahashi     Update          QC#56012
 * 2022/08/31   CITS            K.Suzuki        Update          QC#60510
 * 2023/07/03   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFCL3030CommonLogic implements NFCL3030Constant {

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL3010BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL3030BMsg scrnMsg, S21UserProfileService userProfileService) {
        scrnMsg.setInputProtected(false);
        scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 0, null);
        // START 2016/07/25 J.Kim [QC#9476,ADD]
        scrnAppli.setButtonProperties("Click_CreditCardRefund", "Click_CreditCardRefund", "Credit Card Refund", 0, null);
        // END 2016/07/25 J.Kim [QC#9476,ADD]
        scrnAppli.setButtonProperties("Click_CashApplication", "Click_CashApplication", "Application", 0, null);

        scrnAppli.setButtonProperties("btn1", "CMN_Save", "Save", 0, null);
        // START 2022/08/31 [QC#60510,ADD]
        if (!ZYPConstant.FLG_ON_1.equals(scrnMsg.xxBtnFlg_SU.getValue())) {
            // END 2022/08/31 [QC#60510,ADD]
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
            // START 2022/08/31 [QC#60510,ADD]
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // END 2022/08/31 [QC#60510,ADD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        // START 2018/04/05 H.Ikeda [QC#21737-1,MOD]
        //scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);
        // END   2018/04/05 H.Ikeda [QC#21737-1,MOD]
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        if(scrnMsg.appFuncId.getValue().equals(FUNC_ID)) {
            // START 2018/04/05 H.Ikeda [QC#21737-1,DEL]
            //scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
            // END   2018/04/05 H.Ikeda [QC#21737-1,DEL]
            scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 0, null);
        } else {
            // START 2018/04/05 H.Ikeda [QC#21737-1,DEL]
            //scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
            // END   2018/04/05 H.Ikeda [QC#21737-1,DEL]
            scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
        }
        // START 2016/05/12 K.Kojima [QC#4492,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H)) {
            scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 1, null);
            // START 2016/05/16 S.Fujita [QC#6457,ADD]
            scrnAppli.setButtonProperties("Click_JournalInquiry", "Click_JournalInquiry", "Journal Inquiry", 1, null);
            // END 2016/05/16 S.Fujita [QC#6457,ADD]
        } else {
            scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 0, null);
            // START 2016/05/16 S.Fujita [QC#6457,ADD]
            scrnAppli.setButtonProperties("Click_JournalInquiry", "Click_JournalInquiry", "Journal Inquiry", 0, null);
            // END 2016/05/16 S.Fujita [QC#6457,ADD]
        }
        // END 2016/05/12 K.Kojima [QC#4492,ADD]

        scrnMsg.setInputProtected(false);

        scrnMsg.rcptNum_H.setInputProtected(true);
        scrnMsg.arBatRcptNm_H.setInputProtected(true);
        scrnMsg.arRcptStsCd_H.setInputProtected(true);
        scrnMsg.xxTotAmt_H2.setInputProtected(true);
        scrnMsg.xxTotAmt_H3.setInputProtected(true);
        scrnMsg.xxTotAmt_H4.setInputProtected(true);
        scrnMsg.xxTotAmt_H5.setInputProtected(true);

        // START 2018/02/02 [QC#21372 MOD]
      scrnMsg.payerCustCd_H.setInputProtected(false);
      scrnMsg.payerCustCd_L.setInputProtected(false);
      scrnMsg.dsAcctNm_H.setInputProtected(false);
      scrnAppli.setButtonProperties("CustomerName", "CustomerName", ">>", 1, null);
//        scrnMsg.payerCustCd_H.setInputProtected(true);
//        scrnMsg.payerCustCd_L.setInputProtected(false);
//        scrnMsg.dsAcctNm_H.setInputProtected(true);
      // END 2018/02/02 [QC#21372 MOD]
        scrnMsg.locNm_H.setInputProtected(true);

        scrnMsg.locNum_H.setInputProtected(true);
        // START 2018/02/06 [QC#22759 ADD]
        scrnAppli.setButtonProperties("OpenWin_SearchAccountByTrxPopup", "OpenWin_SearchAccountByTrxPopup", "Search By Trx", 1, null);
        // END   2018/02/06 [QC#22759 ADD]
        scrnMsg.dsBankAcctNm_H1.setInputProtected(true);
        scrnMsg.dsBankBrNm_H1.setInputProtected(true);
        scrnMsg.dsBankAcctNum_H1.setInputProtected(true);

        scrnMsg.dsBankAcctNm_H2.setInputProtected(true);
        scrnMsg.dsBankBrNm_H2.setInputProtected(true);
        scrnMsg.bankRteNum_H2.setInputProtected(true); // ADD 2018/02/02 QC#21372
        // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//        scrnMsg.dsBankAcctNum_H2.setInputProtected(true);
        scrnMsg.dsBankAcctNum_M1.setInputProtected(true);
      // START 2023/07/03 S.Nakatani [QC#55645, MOD]

        scrnMsg.voidDt_H.setInputProtected(true);
        scrnMsg.arRcptVoidRsnCd_H.setInputProtected(true);
        scrnMsg.arRcptRvrsRsnCd_H.setInputProtected(true);
        scrnMsg.voidGlDt_H.setInputProtected(true);
        scrnMsg.arRcptRvrsCmntTxt_H.setInputProtected(true);

        scrnMsg.arRcptRemDt_H.setIndispensable(false);

        
        if (scrnMsg.xxEdtModeFlg_H.getValue().equals(ZYPConstant.FLG_OFF_N)) {
            scrnMsg.dsBankCd_H1.setInputProtected(true);
            scrnMsg.bankRteNum_H1.setInputProtected(true);
            scrnMsg.remDsBankAcctPk_H1.setInputProtected(true);
        } else {
            scrnMsg.dsBankCd_H1.setInputProtected(false);
            scrnMsg.bankRteNum_H1.setInputProtected(false);
            scrnMsg.remDsBankAcctPk_H1.setInputProtected(false);
        }
        // START 2019/08/13 H.Ikeda [QC#52593, MOD]
//        if (ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H) && !scrnMsg.arRcptStsCd_H.equals(AR_RCPT_STS.NEW)) {
//            scrnMsg.rcptDt_H.setInputProtected(true);
//            scrnMsg.arRcptTrxTpCd_H.setInputProtected(true);
//            scrnMsg.arRcptSrcCd_H.setInputProtected(true);
////            scrnMsg.xxTotAmt_H1.setInputProtected(true);
//            scrnMsg.voidDt_H.setInputProtected(false);
//            scrnMsg.arRcptVoidRsnCd_H.setInputProtected(false);
//            scrnMsg.arRcptRvrsRsnCd_H.setInputProtected(false);
//            scrnMsg.voidGlDt_H.setInputProtected(false);
//            scrnMsg.arRcptRvrsCmntTxt_H.setInputProtected(false);
//            scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 1, null);
//        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H)) {
            scrnMsg.voidDt_H.setInputProtected(false);
            scrnMsg.arRcptVoidRsnCd_H.setInputProtected(false);
            scrnMsg.arRcptRvrsRsnCd_H.setInputProtected(false);
            scrnMsg.voidGlDt_H.setInputProtected(false);
            scrnMsg.arRcptRvrsCmntTxt_H.setInputProtected(false);
            scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 1, null);
            if (!scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.NEW)) {
                scrnMsg.rcptDt_H.setInputProtected(true);
                scrnMsg.arRcptTrxTpCd_H.setInputProtected(true);
                scrnMsg.arRcptSrcCd_H.setInputProtected(true);
            }
        }
        // END   2019/08/13 H.Ikeda [QC#52593, MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_H) && scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.VOID)) {
            scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 0, null);
            scrnAppli.setButtonProperties("Click_CashApplication", "Click_CashApplication", "Application", 0, null);
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
            // START 2016/05/12 K.Kojima [QC#4492,ADD]
            scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 0, null);
            // END 2016/05/12 K.Kojima [QC#4492,ADD]
            scrnMsg.setInputProtected(true);
        // START 2020/06/05 R.Kurahashi [QC#56012 ,MOD]
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_H) && !scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.UNIDENTIFIED)) {
        } else if (ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_H) && !scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.UNIDENTIFIED) && !scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.UNAPPLIED)) {
        // END 2020/06/05 R.Kurahashi [QC#56012 ,MOD]
            scrnMsg.payerCustCd_H.setInputProtected(true);
            // START 2018/02/02 [QC#21372,ADD]
            scrnMsg.dsAcctNm_H.setInputProtected(true);
            scrnAppli.setButtonProperties("CustomerName", "CustomerName", ">>", 0, null);
            // END   2018/02/02 [QC#21372,ADD]
            scrnMsg.payerCustCd_L.setInputProtected(true);
            // START 2018/11/13 [QC#29149,DEL]
//            scrnMsg.glDt_H.setInputProtected(true);
            // END 2018/11/13 [QC#29149,DEL]
            // START 2016/08/10 J.Kim [QC#9476,DEL]
            //// START 2016/08/09 T.Tsuchida [QC#9476,MOD]
            ////// START 2016/07/25 J.Kim [QC#9476,ADD]
            /////if (!ZYPConstant.FLG_ON_1.equals(scrnMsg.xxBtnFlg.getValue())) {
            ///    scrnAppli.setButtonProperties("Click_CreditCardRefund", "Click_CreditCardRefund", "Credit Card Refund", 1, null);
            ////}
            ////// END 2016/07/25 J.Kim [QC#9476,ADD]
            // scrnAppli.setButtonProperties("Click_CreditCardRefund", "Click_CreditCardRefund", "Credit Card Refund", 0, null);
            //// END 2016/08/09 T.Tsuchida [QC#9476,MOD]
            // END 2016/08/09 J.Kim [QC#9476,DEL]
            scrnAppli.setButtonProperties("Click_CashApplication", "Click_CashApplication", "Application", 1, null);
            // START 2018/02/06 [QC#22759 ADD]
            scrnAppli.setButtonProperties("OpenWin_SearchAccountByTrxPopup", "OpenWin_SearchAccountByTrxPopup", "Search By Trx", 0, null);
            // END   2018/02/06 [QC#22759 ADD]
        }
        // START 2020/06/05 R.Kurahashi [QC#56012 ,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.arCashApplyStsCd_H) && scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.UNAPPLIED)) {
            scrnAppli.setButtonProperties("Click_CashApplication", "Click_CashApplication", "Application", 1, null);
            scrnAppli.setButtonProperties("OpenWin_SearchAccountByTrxPopup", "OpenWin_SearchAccountByTrxPopup", "Search By Trx", 1, null);
        }
        // END 2020/06/05 R.Kurahashi [QC#56012 ,ADD]
        // START 2018/10/09 T.Ogura [QC#28166,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.xxBtnFlg_AR) && ZYPConstant.FLG_OFF_0.equals(scrnMsg.xxBtnFlg_AR.getValue())) {
            scrnAppli.setButtonProperties("Click_CashApplication", "Click_CashApplication", "Application", 0, null);
        }
        // END   2018/10/09 T.Ogura [QC#28166,ADD]

        // START 2016/08/09 J.Kim [QC#9476,MOD]
        // // START 2016/08/09 T.Tsuchida [QC#9476,ADD]
        // } else {
        //    if (!ZYPConstant.FLG_ON_1.equals(scrnMsg.xxBtnFlg.getValue())) {
        //        scrnAppli.setButtonProperties("Click_CreditCardRefund", "Click_CreditCardRefund", "Credit Card Refund", 1, null);
        //    }
        //    // END 2016/08/09 T.Tsuchida [QC#9476,ADD]
        //}
        // START 2019/09/11 H.Ikeda [QC#53382, MOD]
        //if (AR_CASH_APPLY_STS.UNAPPLIED.equals(scrnMsg.arCashApplyStsCd_H.getValue()) && !ZYPConstant.FLG_ON_1.equals(scrnMsg.xxBtnFlg.getValue())) {
        if ((AR_CASH_APPLY_STS.UNAPPLIED.equals(scrnMsg.arCashApplyStsCd_H.getValue()) || AR_CASH_APPLY_STS.PARTIAL.equals(scrnMsg.arCashApplyStsCd_H.getValue())) && !ZYPConstant.FLG_ON_1.equals(scrnMsg.xxBtnFlg.getValue())) {
        // END    2019/09/11 H.Ikeda [QC#53382, MOD]

            // START 2020/01/01 Y.Matsui [QC#55263,MOD]
            if (S21StringUtil.isEquals(AR_RCPT_SRC.CREDIT_CARD_PAYMENT, scrnMsg.arRcptSrcCd_H.getValue())) {
                if (hasFunctioReverse(S21UserProfileServiceFactory.getInstance().getService())) {
                    scrnAppli.setButtonProperties("Click_CreditCardRefund", "Click_CreditCardRefund", "Credit Card Refund", 1, null);
                    scrnAppli.setButtonConfirmMsgEx("Click_CreditCardRefund", "", null, 1);
                }
            }
            // END   2020/01/01 Y.Matsui [QC#55263,MOD]
        }
        // END 2016/08/09 J.Kim [QC#9476,MOD]

        // START 2016/08/31 T.Tsuchida [QC#13565,MOD]
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        if(scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.APPLIED_PARTIAL_OR_APPLIED)
//                || scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.OPEN)
        if(scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.PARTIAL_APPLIED)
                || scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.APPLIED)
                || scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.UNAPPLIED)
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
                // START 2018/01/31 T.Murai [QC#21401,DEL]
                // || scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.REVERSED_MANUAL)
                // || scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.REVERSED_AUTOMATIC))
                // END 2018/01/31 T.Murai [QC#21401,DEL]
            ) {

            scrnMsg.xxTotAmt_H1.setInputProtected(true);
            scrnMsg.rcptChkNum_H.setInputProtected(true);
        }
        // END 2016/08/31 T.Tsuchida [QC#13565,MOD]

        // Add Start 2017/11/02 QC#22194
        if(scrnMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.REFUND)) {
            scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 0, null);
        }
        // Add End 2017/11/02 QC#22194

        // TAB Protect
        scrnMsg.xxTabProt_A.setInputProtected(false);
        scrnMsg.xxTabProt_B.setInputProtected(false);
        scrnMsg.xxTabProt_C.setInputProtected(false);
        
        NFCL3030CommonLogic.setProtect(scrnMsg);
        
        if (!hasFunctionUpdate(userProfileService)) {
            scrnMsg.setInputProtected(true);
            scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
            // START 2016/05/12 K.Kojima [QC#4492,ADD]
            scrnAppli.setButtonProperties("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment", 0, null);
            // END 2016/05/12 K.Kojima [QC#4492,ADD]
            
            //QC#24746 Add Start
            if (hasFunctionReference(userProfileService)) {
                // TAB Protect
                scrnMsg.xxTabProt_A.setInputProtected(false);
                scrnMsg.xxTabProt_B.setInputProtected(false);
                scrnMsg.xxTabProt_C.setInputProtected(false);
                scrnAppli.setButtonProperties("OpenWin_SearchAccountByTrxPopup", "OpenWin_SearchAccountByTrxPopup", "Search By Trx", 0, null);
            }
            //QC#24746 Add End

        }
        // START 2018/06/06 E.Kameishi [QC#22758,ADD]
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(FUNC_ID);
        if (!funcList.contains(FUNCTION_REVERSE)) {
            scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 0, null);
        }
        // END 2018/06/06 E.Kameishi [QC#22758,ADD]

        // START 2020/01/07 Y.Matsui [QC#55260,DEL]
//        // START 2019/03/25 S.Takami [QC#30902,ADD]
//        if (S21StringUtil.isEquals(AR_RCPT_SRC.CREDIT_CARD_PAYMENT, scrnMsg.arRcptSrcCd_H.getValue())) {
//            scrnAppli.setButtonProperties("Click_Reverse", "Click_Reverse", "Reverse", 0, null);
//        }
//        // END 2019/03/25 S.Takami [QC#30902,ADD]
        // END 2020/01/07 Y.Matsui [QC#55260,DEL]
    }

    /**
     * @param scrnMsg  NFCL3020BMsg
     */
    public static void clearScrnBackgroundColor(NFCL3030BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFCL3030Scrn00");
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
    
    /**
     * 
     * @param scrnMsg
     */
    public static void setProtect(NFCL3030BMsg scrnMsg) {

        boolean isProtect = false;

        if(scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.PARTIAL) 
                || scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.APPLIED)
                || scrnMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.VOID)) {
            isProtect = true;
        }
        if(scrnMsg.ajeCratCpltFlg_H.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            isProtect = true;
        }
        
        if(isProtect) {
            scrnMsg.xxTotAmt_H1.setInputProtected(true);
            scrnMsg.xxTotAmt_H1.setIndispensable(false);
            // START 2018/11/13 [QC#29149,ADD]
            scrnMsg.glDt_H.setInputProtected(true);
            scrnMsg.glDt_H.setIndispensable(false);
            // END 2018/11/13 [QC#29149,ADD]
        } else {
            scrnMsg.xxTotAmt_H1.setInputProtected(false);
            scrnMsg.xxTotAmt_H1.setIndispensable(true);
            // START 2018/11/13 [QC#29149,ADD]
            scrnMsg.glDt_H.setInputProtected(false);
            scrnMsg.glDt_H.setIndispensable(true);
            // END 2018/11/13 [QC#29149,ADD]
        }
    }

    // START 2016/08/23 T.Tsuchida [QC#13570,ADD]
    /**
     * Get Parameter NWAL1130 For Bank Account
     * @param scrnMsg NFCL3030BMsg
     * @return Parameter NWAL1130 For Bank Account
     */
    public static Object[] getParamNWAL1130ForBankAcct(NFCL3030BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Bank Account Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DISTINCT");
        sb.append("    A.GLBL_CMPY_CD");
        sb.append("   ,A.EZCANCELFLAG");
        sb.append("   ,A.DS_BANK_ACCT_NM ");
        sb.append("   ,B.DS_BANK_BR_NM ");
        sb.append("   ,B.BANK_RTE_NUM ");
        sb.append("   ,A.DS_BANK_ACCT_NUM ");
        sb.append("   ,A.DS_BANK_ACCT_PK ");
        // START 2023/07/03 S.Nakatani [QC#55645, ADD]
        sb.append("   ,CASE WHEN LENGTH(A.DS_BANK_ACCT_NUM) <= 4 ");
        sb.append("      THEN REGEXP_REPLACE(A.DS_BANK_ACCT_NUM,'.','*') ");
        sb.append("      ELSE REGEXP_REPLACE(SUBSTR(A.DS_BANK_ACCT_NUM, 1, LENGTH(A.DS_BANK_ACCT_NUM) - 4),'.','*') ");
        sb.append("             || SUBSTR(A.DS_BANK_ACCT_NUM, LENGTH(A.DS_BANK_ACCT_NUM) - 4 + 1) ");
        sb.append("    END AS MASK_BANK_ACCT_NUM ");
        // END 2023/07/03 S.Nakatani [QC#55645, ADD]
        sb.append("FROM");
        sb.append("    DS_BANK_ACCT A ");
        sb.append("   ,DS_BANK_BR   B ");
        sb.append("   ,SELL_TO_CUST C ");
        sb.append("   ,DS_CUST_BANK_ACCT_RELN D ");
        sb.append("WHERE");
        sb.append("        A.GLBL_CMPY_CD                  = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND A.EFF_FROM_DT                  <= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND NVL(A.EFF_THRU_DT, '99991231') >= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND A.DS_BANK_ACCT_TP_CD            = '").append(scrnMsg.dsBankAcctTpCd.getValue()).append("'");
        sb.append("    AND A.GLBL_CMPY_CD                  = B.GLBL_CMPY_CD");
        sb.append("    AND A.DS_BANK_BR_PK                 = B.DS_BANK_BR_PK");
        sb.append("    AND A.GLBL_CMPY_CD                  = C.GLBL_CMPY_CD");
        if (ZYPCommonFunc.hasValue(scrnMsg.payerCustCd_H)) {
            sb.append("    AND C.SELL_TO_CUST_CD                   = '").append(scrnMsg.payerCustCd_H.getValue()).append("'");
        }
        sb.append("    AND A.GLBL_CMPY_CD                  = D.GLBL_CMPY_CD");
        sb.append("    AND A.DS_BANK_ACCT_PK               = D.DS_BANK_ACCT_PK");
        sb.append("    AND C.SELL_TO_CUST_PK               = D.DS_ACCT_CUST_PK");
        sb.append("    AND A.EZCANCELFLAG                  = '0'");
        sb.append("    AND B.EZCANCELFLAG                  = '0'");
        sb.append("    AND C.EZCANCELFLAG                  = '0'");
        sb.append("    AND D.EZCANCELFLAG                  = '0'");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Bank Name";
        whereArray0[1] = "DS_BANK_ACCT_NM";
        whereArray0[2] = scrnMsg.dsBankAcctNm_H2.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Branch Name";
        whereArray1[1] = "DS_BANK_BR_NM";
        whereArray1[2] = scrnMsg.dsBankBrNm_H2.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Routing Number";
        whereArray2[1] = "BANK_RTE_NUM";
        whereArray2[2] = scrnMsg.bankRteNum_H2.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Account Number";
        whereArray3[1] = "DS_BANK_ACCT_NUM";
        // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//        whereArray3[2] = scrnMsg.dsBankAcctNum_H2.getValue();
        whereArray3[2] = null;
        // END 2023/07/03 S.Nakatani [QC#55645, MOD]
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Bank Name";
        columnArray0[1] = "DS_BANK_ACCT_NM";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Branch Name";
        columnArray1[1] = "DS_BANK_BR_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Routing Number";
        columnArray2[1] = "BANK_RTE_NUM";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Account Number";
        // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//        columnArray3[1] = "DS_BANK_ACCT_NUM";
        columnArray3[1] = "MASK_BANK_ACCT_NUM";
        // END 2023/07/03 S.Nakatani [QC#55645, MOD]
        columnArray3[2] = BigDecimal.valueOf(14);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "PK";
        columnArray4[1] = "DS_BANK_ACCT_PK";
        columnArray4[2] = BigDecimal.valueOf(28);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        // START 2023/07/03 S.Nakatani [QC#55645, ADD]
        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Un Musked Account Number";
        columnArray5[1] = "DS_BANK_ACCT_NUM";
        columnArray5[2] = BigDecimal.valueOf(0);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);
        // END 2023/07/03 S.Nakatani [QC#55645, ADD]
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_BANK_ACCT_NM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[6] = scrnMsg.P;

        return params;
    }
    // END 2016/08/23 T.Tsuchida [QC#13570,ADD]
    
    //QC#24746 Add Start
    /**
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean hasFunctionReference(S21UserProfileService userProfileService) {

        List<String> list = userProfileService.getFunctionCodeListForBizAppId(FUNC_ID);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(FUNCTION_REFERENCE)) {
                return true;
            }
        }
        return false;
    }
    //QC#24746 Add End

    // START 2020/01/01 Y.Matsui [QC#55263,ADD]
    /**
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean hasFunctioReverse(S21UserProfileService userProfileService) {

        List<String> list = userProfileService.getFunctionCodeListForBizAppId(FUNC_ID);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(FUNCTION_REVERSE)) {
                return true;
            }
        }
        return false;
    }
    // END   2020/01/01 Y.Matsui [QC#55263,MOD]
}
