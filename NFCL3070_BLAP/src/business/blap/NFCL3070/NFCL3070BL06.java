/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3070;

import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0786E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0787W;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0792E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0793W;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0846E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0870E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;
import static business.blap.NFCL3070.constant.NFCL3070Constant.RQST_TP_REBILL_ONLY;
import static business.blap.NFCL3070.constant.NFCL3070Constant.ZZM9037E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.ZZZM9006E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0851E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0818E;
import static business.blap.NFCL3070.constant.NFCL3070Constant.NFCM0902E;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDSMsg;
import business.blap.NFCL3070.common.NFCL3070CommonLogic;
import business.db.INVTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_REBIL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL3070BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/06/28   Fujitsu         S.Fujita        Update          QC#10942
 * 2016/07/13   Fujitsu         S.Fujita        Update          QC#11445
 * 2016/08/30   Fujitsu         S.Fujita        Update          QC#13990
 * 2016/10/24   Fujitsu         T.Murai         Update          QC#14760
 * 2017/06/23   Hitachi         E.Kameishi      Update          QC#19510
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/06/27   Hitachi         E.Kameishi      Update          QC#26906
 * 2018/08/27   Fujitsu         T.Murai         Update          QC#52944
 * 2019/09/03   Fujitsu         T.Murai         Update          QC#52945
 * 2019/09/17   Fujitsu         S.Takami        Update          QC#53456
 * 2020/02/29   Fujitsu         Y.Matsui        Update          QC#55872
 *</pre>
 */
public class NFCL3070BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3070CMsg bizMsg = (NFCL3070CMsg) cMsg;
            NFCL3070SMsg glblMsg = (NFCL3070SMsg) sMsg;

            if ("NFCL3070Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3070Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NFCL3070Scrn00_CMN_Yes".equals(screenAplID)) {
                doProcess_NFCL3070Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3070Scrn00_CMN_Submit(NFCL3070CMsg bizMsg, NFCL3070SMsg glblMsg) {
        boolean isError = false;
        boolean isWarning = false;
        // isCreditOnly
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_C, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_C, ZYPConstant.FLG_OFF_N);
        }
        // isTargetLineLevel
        if (ZYPCommonFunc.hasValue(bizMsg.invLineNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_T, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_T, ZYPConstant.FLG_OFF_N);
        }
        // isPartialAmount
        if (ZYPCommonFunc.hasValue(bizMsg.arCrPct) || ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_P, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_P, ZYPConstant.FLG_OFF_N);
        }

        // START 2018/05/30 E.Kameishi [QC#26229,MOD]
         //Check exists CI Number
         if (!NFCL3070CommonLogic.checkCITicket(bizMsg)) {
             bizMsg.custIncdtId.setErrorInfo(1, NFCM0818E);
             isError = true;
         }
         // END 2018/05/30 E.Kameishi [QC#26229,MOD]

        // Check equal CI Number
        // if (!NFCL3070CommonLogic.equalCITicket(bizMsg)) {
        // bizMsg.custIncdtId.setErrorInfo(1, NFCM0819E);
        // isError = true;
        // }

        //START 2018/05/30 E.Kameishi [QC#26229,MOD]
        // Check exists Credit Rebill Reason
        if (!NFCL3070CommonLogic.isArCrRebilRsnCd(bizMsg)) {
            bizMsg.arCrRebilRsnNm.setErrorInfo(1, ZZZM9006E, new String[] {"Reason" });
            isError = true;
        }
        //END 2018/05/30 E.Kameishi [QC#26229,MOD]

        // Get Original Invoice
        INVTMsg origInvTMsg = NFCL3070CommonLogic.getInvTMsg(bizMsg);
        if (origInvTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(origInvTMsg.getReturnCode())) {
            bizMsg.origInvNum.setErrorInfo(1, NFCM0786E);
            isError = true;
        }

        // Check exists Invoice Line
        if (bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            if (!NFCL3070CommonLogic.isInvoiceLineNum(bizMsg)) {
                if (bizMsg.invLineNum.getErrorCode() == EZDMessageInfo.MSGTYPE_WARNING) {
                    isError = true;
                } else {
                    isWarning = true;
                    // START 2019/09/04 [QC#52945 ADD]
                    if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CO, bizMsg.crRebilAmt_CO.getValue().negate());
                    }
                    // END 2019/09/04 [QC#52945 ADD]
                }
            } else { // START 2019/09/17 S.Takami [QC#53456,ADD]
                if (BigDecimal.ZERO.compareTo(NFCL3070Query.getInstance().hasSetParentLine(bizMsg)) != 0) {
                    bizMsg.invLineNum.setErrorInfo(1, NFCM0902E, new String[]{"another Line Number", "Set Item Line Number", "Line Number"});
                    isError = true;
                }
                // END 2019/09/17 S.Takami [QC#53456,ADD]
            }
        }

        // START 2017/06/23 E.Kameishi [QC#19510, MOD]
        // START 2016/10/24 T.Murai [QC#14760, ADD]
        if (origInvTMsg != null && INV_TP.CREDIT_MEMO.equals(origInvTMsg.invTpCd.getValue())) {
            bizMsg.origInvNum.setErrorInfo(1, NFCM0870E);
            isError = true;
        }
        // END   2016/10/24 T.Murai [QC#14760, ADD]

        // START 2016/07/13 S.Fujita [QC#11445,ADD]
        // Check Finalize Invoice Flag
        if (!isError) {
            if (origInvTMsg != null && ZYPConstant.FLG_OFF_N.equals(origInvTMsg.fnlzInvFlg.getValue())) {
                bizMsg.origInvNum.setErrorInfo(1, NFCM0851E);
                isError = true;
            }
        }
        // END   2016/07/13 S.Fujita [QC#11445,ADD]
        // END 2017/06/23 E.Kameishi [QC#19510, MOD]

        // return if has error or warning
        if (isError) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }
        if (isWarning) {
            bizMsg.setMessageInfo(NFCM0793W);
            return;
        }
        // set original invoice value to bizMsg
        if (!NFCL3070CommonLogic.setOriginalInvoiceValue(bizMsg, origInvTMsg)) {
            bizMsg.origInvNum.setErrorInfo(1, NFCM0786E);
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        // START 2016/06/28 S.Fujita [QC#10942,ADD]
        // return if Credit Amount equals zero or positive number
        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
            if (bizMsg.crRebilAmt_CO.getValue().compareTo(BigDecimal.ZERO) == 0 || bizMsg.crRebilAmt_CO.getValue().signum() > 0) {
                bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0846E, new String[] {"Credit Amount" });
                bizMsg.setMessageInfo(ZZM9037E);
                return;
            }
        // if mode is the CreditOnly, crCredit Amount multiply -1.
        ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CO, bizMsg.crRebilAmt_CO.getValue().negate());
        }
        // END   2016/06/28 S.Fujita [QC#10942,ADD]

        // Request Type : CREDIT ONLY MODE
        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
            // DEL START 2019/08/27 QC#52944
            // check Credit amounts over the Invoice balance
//            if (!NFCL3070CommonLogic.isCreditAmountOverInvBalance(bizMsg)) {
//                bizMsg.setMessageInfo(ZZM9037E);
//                return;
//            }
            // DEL END 2019/08/27 QC#52944
            if (!bizMsg.xxPgFlg_T.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                // Tax
                if (AR_CR_TP.TAX.equals(bizMsg.arCrTpCd.getValue())) {
                    if (!NFCL3070CommonLogic.isCreditAmountLessThanOrigInv(bizMsg.crRebilAmt_CO.getValue(), origInvTMsg.invTotDealTaxAmt.getValue())) {
                        // START 2016/06/28 S.Fujita [QC#10942,ADD]
                        // if mode is the CreditOnly, crCredit Amount restore negative number
                        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CO, bizMsg.crRebilAmt_CO.getValue().negate());
                        }
                        // END   2016/06/28 S.Fujita [QC#10942,ADD]
                        bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice Tax" });
                        bizMsg.setMessageInfo(ZZM9037E);
                        return;
                    }
                // Freight
                } else if (AR_CR_TP.FREIGHT.equals(bizMsg.arCrTpCd.getValue())) {
                    if (!NFCL3070CommonLogic.isCreditAmountLessThanOrigInv(bizMsg.crRebilAmt_CO.getValue(), origInvTMsg.invTotDealFrtAmt.getValue())) {
                        // START 2016/06/28 S.Fujita [QC#10942,ADD]
                        // if mode is the CreditOnly, crCredit Amount restore negative number
                        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CO, bizMsg.crRebilAmt_CO.getValue().negate());
                        }
                        // END   2016/06/28 S.Fujita [QC#10942,ADD]
                        bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice Freight" });
                        bizMsg.setMessageInfo(ZZM9037E);
                        return;
                    }
                // Receivables
                } else if (AR_CR_TP.RECEIVABLES.equals(bizMsg.arCrTpCd.getValue())) {
                    if (!NFCL3070CommonLogic.isCreditAmountLessThanOrigInv(bizMsg.crRebilAmt_CO.getValue(), origInvTMsg.invTotDealSlsAmt.getValue())) {
                        // START 2016/06/28 S.Fujita [QC#10942,ADD]
                        // if mode is the CreditOnly, crCredit Amount restore negative number
                        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.crRebilAmt_CO, bizMsg.crRebilAmt_CO.getValue().negate());
                        }
                        // END   2016/06/28 S.Fujita [QC#10942,ADD]
                        bizMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0792E, new String[] {"Invoice Sales" });
                        bizMsg.setMessageInfo(ZZM9037E);
                        return;
                    }
                }
            }
        }
        // Request Type : BILL ONLY or BOTH MODE
        //if (!isCreditOnly) {
            // check rebill invoice amount equals to CI Amount
            // if
            // (!NFCL3070CommonLogic.isEqual(origInvTMsg.invTotDealNetAmt.getValue(),
            // bizMsg.xxInvTotAmt_CI.getValue())) {
            // bizMsg.origInvNum.setErrorInfo(1, NFCM0804E);
            // bizMsg.setMessageInfo(ZZM9037E);
            // return;
            // }
        //}

        // get scale
        if (!NFCL3070CommonLogic.getFuncCcyScale(bizMsg)) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }
        // Check calculated amount under zero
        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.arCrPct)) {
            // zero check of calculated amount
            if (!NFCL3070CommonLogic.isCalAmountUnderZero(bizMsg, origInvTMsg)) {
                bizMsg.setMessageInfo(ZZM9037E);
                return;
            }
        }
        // Check exists applied credit
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.allwFlg.getValue())) {
            NFCL3070CommonLogic.checkApplyOfCreditMemo(bizMsg);
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.arCashApplyEligFlg.getValue())) {
                bizMsg.arCrRebilAddlCmntTxt.setErrorInfo(2, NFCM0787W);
                bizMsg.setMessageInfo(NFCM0793W);
                return;
            }
        }
        // Register Invoice Table
        // CREDIT ONLY
        if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            bizMsg.arCrRebilTpCd.setValue(AR_CR_REBIL_TP.CREDIT_ONLY);
        	// START 2018/06/27 E.Kameishi [QC#26906,ADD]
            if (!NFCL3070CommonLogic.registInvoiceTables(bizMsg, origInvTMsg)) {
                bizMsg.invNum_C.clear();
                return;
            }
        	// END 2018/06/27 E.Kameishi [QC#26906,ADD]
        // REBILL ONLY
        } else if (RQST_TP_REBILL_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            bizMsg.arCrRebilTpCd.setValue(AR_CR_REBIL_TP.REBILL_ONLY);
            NFCL3070CommonLogic.registInvoiceTables(bizMsg, origInvTMsg);

        // BOTH
        } else {
            bizMsg.arCrRebilTpCd.setValue(AR_CR_REBIL_TP.CREDIT_AND_REBILL);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_C, ZYPConstant.FLG_ON_Y);
            if (!NFCL3070CommonLogic.registInvoiceTables(bizMsg, origInvTMsg)) {
                return;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPgFlg_C, ZYPConstant.FLG_OFF_N);
            NFCL3070CommonLogic.registInvoiceTables(bizMsg, origInvTMsg);
        }

        // START 2020/02/29 [QC#55872,DEL]
        // START 2018/05/30 E.Kameishi [QC#26229,ADD]
        // if (RQST_TP_CREDIT_ONLY.compareTo(bizMsg.xxRadioBtn.getValue()) == 0 || RQST_TP_BOTH.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
        //     if (!NFCL3070CommonLogic.checkArCrRebilValue(bizMsg, origInvTMsg)) {
        //         bizMsg.invNum_C.clear();
        //         bizMsg.invNum_R.clear();
        //         bizMsg.setMessageInfo(NFCM0886E);
        //         return;
        //     }
        // }
        // END 2018/05/30 E.Kameishi [QC#26229,ADD]
        // END 2020/02/29 [QC#55872,DEL]
    }
}
