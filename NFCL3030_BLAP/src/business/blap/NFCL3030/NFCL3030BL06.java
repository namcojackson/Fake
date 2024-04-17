package business.blap.NFCL3030;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3030.common.NFCL3030CommonLogic;
import business.blap.NFCL3030.constant.NFCL3030Constant;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_RCPT_UN_APPLYTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC310001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC310001.NFZC310001;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_UN_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_UN_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 10/05/2015   Fujitsu         T.Tanaka        Update          crCardAppCd -> crCardApvlCd
 * 02/18/2016   Fujitsu         T.Tanaka        Update          Def#2631
 * 04/25/2016   Fujitsu         S.Fujita        Update          QC#5671
 * 07/25/2016   Hitachi         J.Kim           Update          QC#9476
 * 08/23/2016   Hitachi         T.Tsuchida      Update          QC#13570
 * 2016/11/29   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/06/13   Hitachi         E.Kameishi      Update          QC#18595
 * 2017/11/09   Fujitsu         M.Ohno          Update          QC#21403
 * 2018/01/10   Fujitsu         T.Murai         Update          QC#21400
 * 2018/01/19   Fujitsu         H.Ikeda         Update          QC#23136
 * 2018/01/30   Hitachi         E.Kameishi      Update          QC#23681
 * 2018/01/31   Fujitsu         T.Murai         Update          QC#21401
 * 2018/02/06   Fujitsu         T.Murai         Update          QC#21372
 * 2018/05/17   Hitachi         E.Kameishi      Update          QC#25458
 * 2018/06/07   Hitachi         E.Kameishi      Update          QC#25916
 * 2018/06/20   Hitachi         E.Kameishi      Update          QC#25916
 * 2018/06/21   Hitachi         E.Kameishi      Update          QC#26673
 * 2018/07/13   Fujitsu         Y.Matsui        Update          QC#26993
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2018/11/13   Fujitsu         S.Ohki          Update          QC#29149
 * 2019/01/18   Fujitsu         Y.Matsui        Update          QC#29926
 * 2019/07/09   Fujitsu         T.Murai         Update          QC#51282
 * 2019/08/20   Fujitsu         T.Murai         Update          QC#52780
 * 2019/09/03   Fujitsu         Y.Matsui        Update          QC#53152
 * 2020/01/31   Fujitsu         Y.Matsui        Update          QC#54826
 * 2020/06/05   CITS            R.Kurahashi     Update          QC#56012
 * 2020/06/22   CITS            R.Kurahashi     Update          QC#56012-1
 * 2020/07/10   CITS            R.Kurahashi     Update          QC#56012-2
 * 2020/09/04   CITS            R.Kurahashi     Update          QC#56012-3
 * 2021/10/20   CITS            G.Delgado       Update          QC#59241
 * 2022/01/17   CITS            G.Delgado       Update          QC#58844
 * 2022/08/04   CITS            D.Mamaril       Update          QC#60376
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3030BL06 extends S21BusinessHandler implements NFCL3030Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3030Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFCL3030Scrn00_Click_Reverse".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_Click_Reverse(cMsg, sMsg);
            } else if ("NFCL3030Scrn00_Click_CreditCardRefund".equals(screenAplID)) {
                doProcess_NFCL3030Scrn00_Click_CreditCardRefund(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Submit================================START", this);
        // START 2020/06/05 R.Kurahashi [QC#56012 ,MOD]
        // updateReceipt(cMsg, sMsg);

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(bizMsg.arRcptStsCd_H.getValue()) && bizMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.UNAPPLIED)) {
            // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
            //EZDCMsg beforeCMsg = (EZDCMsg) cMsg.clone();
            //NFCL3030CMsg beforeBizMsg = (NFCL3030CMsg) beforeCMsg;
            // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
            AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
            AR_RCPTTMsg rcptUpdMsg;
            String beforePayerCustCd = null;
            // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
            //String beforeRcptNum = null;
            // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
            String arRcptRefTxt = null;
            // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
            //String beforeArRcptRefTxt = null;
            //String rcptNum = null;
            // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, bizMsg.rcptNum_H);
            rcptUpdMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inMsg);
            // START 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
            // START 2020/07/10 R.Kurahashi [QC#56012-2,DEL]
            //String locNum = null;
            //boolean errFlg = false;
            // END 2020/07/10 R.Kurahashi [QC#56012-2,DEL]
            // START 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
            if (rcptUpdMsg != null) {
                // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                //beforeRcptNum = rcptUpdMsg.rcptNum.getValue();
                // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                beforePayerCustCd = rcptUpdMsg.payerCustCd.getValue();
            }

            if (rcptUpdMsg == null) {
                bizMsg.setMessageInfo("NFCM0032E");
            } else if (beforePayerCustCd.equals(bizMsg.payerCustCd_H.getValue())) {
                updateReceipt(cMsg, sMsg);
                bizMsg.setMessageInfo("NZZM0002I");
            } else {
                // START 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
                // START 2020/07/10 R.Kurahashi [QC#56012-2,DEL]
                //locNum = bizMsg.locNum_H.getValue();
                //bizMsg.locNum_H.clear();
                //if (!ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H.getValue()) && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H.getValue())) {
                //    bizMsg.payerCustCd_H.setErrorInfo(1, NFCM0883E);
                //    bizMsg.dsAcctNm_H.setErrorInfo(1, NFCM0883E);
                //    bizMsg.locNum_H.setErrorInfo(1, NFCM0883E);
                //    ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H, locNum);
                //    errFlg = true;
                //} else if (NFCL3030CommonLogic.getCustomer(getGlobalCompanyCode(), bizMsg)) {
                //    ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H, locNum);
                //    if (!NFCL3030CommonLogic.getCustomer(getGlobalCompanyCode(), bizMsg)) {
                //        bizMsg.clearErrorInfo();
                //        bizMsg.locNum_H.clear();
                //    }
                //} else {
                //    ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H, locNum);
                //    errFlg = true;
                //}
                // END 2020/07/10 R.Kurahashi [QC#56012-2,DEL]
                // START 2020/07/10 R.Kurahashi [QC#56012-2,MOD]
                //if (!errFlg) {
                if (NFCL3030CommonLogic.getCustomer(getGlobalCompanyCode(), bizMsg)) {
                // END 2020/07/10 R.Kurahashi [QC#56012-2,MOD]
                // END 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
                    // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                    //List<String> rsnCdList = new ArrayList<String>();
                    //String voidSsnCd = null;
                    //String rvrsRsnCd = null;
                    //String rsnCds = ZYPCodeDataUtil.getVarCharConstValue("NFCL3030_CHANGE_CUST_RVRS", getGlobalCompanyCode());
                    //if (ZYPCommonFunc.hasValue(rsnCds)) {
                    //    AR_ACCT_DTTMsg inAcctDtMsg = new AR_ACCT_DTTMsg();
                    //    ZYPEZDItemValueSetter.setValue(inAcctDtMsg.glblCmpyCd, getGlobalCompanyCode());
                    //    ZYPEZDItemValueSetter.setValue(inAcctDtMsg.onlBatTpCd, "1");
                    //    ZYPEZDItemValueSetter.setValue(inAcctDtMsg.subSysCd, SUB_SYS.NFC);
                    //    AR_ACCT_DTTMsg acctDtMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inAcctDtMsg);
                    //
                    //        rsnCdList = Arrays.asList(rsnCds.split(","));
                    //        voidSsnCd = rsnCdList.get(0);
                    //        ZYPEZDItemValueSetter.setValue(beforeBizMsg.arRcptVoidRsnCd_H, voidSsnCd);
                    //        if (rsnCdList.size() > 1) {
                    //            rvrsRsnCd = rsnCdList.get(1);
                    //        }
                    //        ZYPEZDItemValueSetter.setValue(beforeBizMsg.arRcptRvrsRsnCd_H, rvrsRsnCd);
                    //        reverse(beforeBizMsg, sMsg);
                    //
                    //        bizMsg.rcptNum_H.clear();
                    //        ZYPEZDItemValueSetter.setValue(bizMsg.ajeCratCpltFlg_H, ZYPConstant.FLG_OFF_N);
                    //        ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H, acctDtMsg.acctDt.getValue());
                    // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                            arRcptRefTxt = bizMsg.arRcptRefTxt_H.getValue();
                            // START 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
                            //if (ZYPCommonFunc.hasValue(arRcptRefTxt)) {
                            //    arRcptRefTxt = arRcptRefTxt + " " + beforeRcptNum;
                            //    if (arRcptRefTxt.length() > 50) {
                            //        arRcptRefTxt = arRcptRefTxt.substring(arRcptRefTxt.length() - 50);
                            //    }
                            //} else {
                            //    arRcptRefTxt = beforeRcptNum;
                            //}
                            arRcptRefTxt = NFCL3030CommonLogic.getArRcptRefTxt(beforePayerCustCd, getContextUserInfo().getUserId(), arRcptRefTxt);
                            // END 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
                            ZYPEZDItemValueSetter.setValue(bizMsg.arRcptRefTxt_H, arRcptRefTxt);
                            updateReceipt(bizMsg, sMsg);
                            // START 2021/10/20 G.Delgado [QC#59241,DEL]
                            // NFCL3030CommonLogic.deleteArCashApp(getGlobalCompanyCode(), bizMsg);
                            // END 2021/10/20 G.Delgado [QC#59241,DEL]
                            // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                            //rcptNum = bizMsg.rcptNum_H.getValue();
                            //inMsg = new AR_RCPTTMsg();
                            //ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                            //ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, beforeRcptNum);
                            //rcptUpdMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                            //beforeArRcptRefTxt = rcptUpdMsg.arRcptRefTxt.getValue();
                            //if (ZYPCommonFunc.hasValue(beforeArRcptRefTxt)) {
                            //    beforeArRcptRefTxt = beforeArRcptRefTxt + " " + rcptNum;
                            //    if (beforeArRcptRefTxt.length() > 50) {
                            //        beforeArRcptRefTxt = beforeArRcptRefTxt.substring(beforeArRcptRefTxt.length() - 50);
                            //    }
                            //} else {
                            //    beforeArRcptRefTxt = rcptNum;
                            //}
                            //ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRefTxt, beforeArRcptRefTxt);
                            //EZDTBLAccessor.update(rcptUpdMsg);
                            // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                            // START 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
                            //bizMsg.setMessageInfo("NFCM0915I", new String[] {bizMsg.rcptNum_H.getValue() });
                            bizMsg.setMessageInfo("NZZM0002I");
                            // END 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
                    // START 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                    //    }
                    //} else {
                    //    bizMsg.setMessageInfo("NFCM0856E", new String[] {"NFCL3030_CHANGE_CUST_RVRS" });
                    //}
                    // END 2020/09/04 R.Kurahashi [QC#56012-3,DEL]
                // START 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
                }
                // END 2020/06/22 R.Kurahashi [QC#56012-1,ADD]
            }
        } else {
            updateReceipt(cMsg, sMsg);
            bizMsg.setMessageInfo("NZZM0002I");
        }
        // END 2020/06/05 R.Kurahashi [QC#56012 ,MOD]
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Submit================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_Click_Reverse(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Submit================================START", this);
        reverse(cMsg, sMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_CMN_Submit================================END", this);
    }

    // START 2016/07/25 J.Kim [QC#9476,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3030Scrn00_Click_CreditCardRefund(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        NFCL3030SMsg glblMsg = (NFCL3030SMsg) sMsg;

        if (!NFCL3030CommonLogic.checkRefundData(bizMsg, glblMsg)) {
            return;
        }

        if (!NFCL3030CommonLogic.getRefundData(bizMsg, glblMsg)) {
            return;
        }
    }
    // END 2016/07/25 J.Kim [QC#9476,ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void updateReceipt(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        String arRcptTocCd;
        String arRcptProdCd;
        String rcptNum = null;
        boolean errFlg = false;

        String salesDt = ZYPDateUtil.getSalesDate();
//Def#3291
//        if (bizMsg.rcptDt_H.getValue().compareTo(salesDt) > 0) {
//            bizMsg.rcptDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
//            errFlg = true;
//        }

//        if (bizMsg.arRcptRemDt_H.getValue().compareTo(salesDt) > 0) {
//            bizMsg.arRcptRemDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
//            bizMsg.xxDplyTab.setValue(TAB_AddInfo);
//            errFlg = true;
//        }
        if (!NFCL3030CommonLogic.checkReceiptDate(bizMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.arRcptRemDt_H.getValue())) {
            if (!NFCL3030CommonLogic.checkRemittanceDate(bizMsg)) {
                bizMsg.xxDplyTab.setValue(TAB_AddInfo);
                return;
            }
        }


        if (bizMsg.glDt_H.getValue().compareTo(salesDt) > 0) {
            bizMsg.glDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
            errFlg = true;
        }

//Def#3291
//        if (ZYPCommonFunc.hasValue(bizMsg.arRcptRemDt_H) && bizMsg.rcptDt_H.getValue().compareTo(bizMsg.arRcptRemDt_H.getValue()) > 0) {
////            bizMsg.rcptDt_H.setErrorInfo(1, "NFCM0776E", new String[] {"Remitance Date", "Receipt Date"});
//            bizMsg.arRcptRemDt_H.setErrorInfo(1, "NFCM0776E", new String[] {"Remitance Date", "Receipt Date"});
//            bizMsg.xxDplyTab.setValue(TAB_AddInfo);
//            errFlg = true;
//        }

        // START 2018/11/13 [QC#29149,MOD]
//        if (!NFCL3030CommonLogic.checkGlDate(getGlobalCompanyCode(), bizMsg.glDt_H.getValue())) {
//            bizMsg.glDt_H.setErrorInfo(1, "NFCM0045E");
//            errFlg = true;
//        }

        if(!bizMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.PARTIAL) 
                && !bizMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.APPLIED)
                && !bizMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.VOID)
                && !bizMsg.ajeCratCpltFlg_H.getValue().equals(ZYPConstant.FLG_ON_Y)) {
        	if (!NFCL3030CommonLogic.checkGlDate(getGlobalCompanyCode(), bizMsg.glDt_H.getValue())) {
                bizMsg.glDt_H.setErrorInfo(1, "NFCM0045E");
                errFlg = true;
            }
        }
        // END 2018/11/13 [QC#29149,MOD]

        // START 2018/02/05 [QC#21372,ADD]
        // START 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
        //if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H) && !ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)) {
        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)) {
        // END 2020/09/04 R.Kurahashi [QC#56012-3,MOD]
            if (!NFCL3030CommonLogic.getDsAcctNm_Like(getGlobalCompanyCode(), bizMsg)) {
                errFlg = true;
                return;
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)) {
            if (!NFCL3030CommonLogic.getCustomer(getGlobalCompanyCode(), bizMsg)) {
                errFlg = true;
                return;
            }
        }
        // END 2018/02/05 [QC#21372,ADD] 

        if (ZYPCommonFunc.hasValue(bizMsg.rcptNum_H)) {
            NFCL3030CommonLogic.setDsAcctNm(getGlobalCompanyCode(), bizMsg);
        }

        if (!NFCL3030CommonLogic.checkPayerCustCdLock(getGlobalCompanyCode(), bizMsg, getContextUserInfo())) {
            errFlg = true;
        }

        // START 2016/04/25 S.Fujita [QC#5671,MOD]
        if (!NFCL3030CommonLogic.isReceiptDate(getGlobalCompanyCode(), bizMsg)) {
            errFlg = true;
        }
        // END 2016/04/25 S.Fujita [QC#5671,MOD]

        // START 2016/08/23 T.Tsuchida [QC#13570,ADD]
        if (!NFCL3030CommonLogic.existDsCustBankAcctReln(getGlobalCompanyCode(), bizMsg)) {
            // START 2017/06/09 E.Kameishi[QC#18595,MOD]
            if (!NFCL3030CommonLogic.insertDsCustBankAcctReln(getGlobalCompanyCode(), bizMsg, salesDt)) {
                errFlg = true;
            }
            // END 2017/06/09 E.Kameishi[QC#18595,MOD]
        }
        // END 2016/08/23 T.Tsuchida [QC#13570,ADD]

        if (errFlg) {
            return;
        }

        AR_RCPTTMsg rcptUpdMsg;
        String beforeArRcptStsCd = null;
        String beforeArCashApplyStsCd = null;
        ZYPEZDItemValueSetter.setValue(bizMsg.stdCcyCd_H, CCY_USD);

        boolean isFirst = false;

        if (ZYPCommonFunc.hasValue(bizMsg.rcptNum_H)) {
            AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, bizMsg.rcptNum_H);
            rcptUpdMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            if (rcptUpdMsg == null) {
                bizMsg.setMessageInfo("ZZZM9004E");
            } else {
                rcptNum = rcptUpdMsg.rcptNum.getValue();
                beforeArRcptStsCd = rcptUpdMsg.arRcptStsCd.getValue();
                beforeArCashApplyStsCd = rcptUpdMsg.arCashApplyStsCd.getValue();
            }
            if (!bizMsg.ezUpTime_H.getValue().equals(rcptUpdMsg.ezUpTime.getValue()) || !bizMsg.ezUpTimeZone_H.getValue().equals(rcptUpdMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            }
        } else {
            rcptUpdMsg = new AR_RCPTTMsg();
            rcptNum = ZYPNumbering.getUniqueID(getGlobalCompanyCode(), AR_RCPT_AUTO_SEQ_KEY);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptNum, rcptNum);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);

        }
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptChkNum, bizMsg.rcptChkNum_H.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H) && !ZYPCommonFunc.hasValue(rcptUpdMsg.payerCustCd)) {
            isFirst = true;
        }
        if (!ZYPCommonFunc.hasValue(beforeArRcptStsCd) || beforeArRcptStsCd.equals(AR_RCPT_STS.NEW)) {
            arRcptTocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY, getGlobalCompanyCode());
            arRcptProdCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY, getGlobalCompanyCode());

            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatNum, RCPT_BAT_NUM);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatSqNum, RCPT_BAT_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTrxTpCd, bizMsg.arRcptTrxTpCd_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTpCd, AR_RCPT_TP_DUMMY);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealCcyCd, bizMsg.stdCcyCd_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRfAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealVoidAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exchRate, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcCcyCd, bizMsg.stdCcyCd_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRfAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcVoidAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRvalVarAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptDt, bizMsg.rcptDt_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glDt, bizMsg.glDt_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.tocCd, arRcptTocCd);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.coaProdCd, arRcptProdCd);
            if (ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)) {
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
            } else {
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNIDENTIFIED);
            }
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.voidFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rfExchRate, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptInProcSqPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cratMethTpCd, CREATE_METH_TP_CD_MANUAL);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, bizMsg.xxTotAmt_H1);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.lastUpdUserId, getContextUserInfo().getUserId());
//            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptSrcCd, bizMsg.arRcptSrcCd_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRvrsRsnCd, bizMsg.arRcptRvrsRsnCd_H);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRvrsCmntTxt, bizMsg.arRcptRvrsCmntTxt_H);

            BigDecimal funcAmt = getFuncAmt(rcptUpdMsg.glblCmpyCd.getValue(), rcptUpdMsg.dealCcyCd.getValue(), bizMsg.xxTotAmt_H1.getValue(), rcptUpdMsg.glDt.getValue());
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, funcAmt);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cashAppDt, bizMsg.glDt_H);

            if (!ZYPCommonFunc.hasValue(bizMsg.arRcptStsCd_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.NEW);
            }

        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        } else if (!ZYPCommonFunc.hasValue(beforeArRcptStsCd) || beforeArRcptStsCd.equals(AR_RCPT_STS.OPEN)) {
        } else if (!ZYPCommonFunc.hasValue(beforeArRcptStsCd) || beforeArRcptStsCd.equals(AR_RCPT_STS.UNAPPLIED)) {
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
            // update Receipt Amount
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, bizMsg.xxTotAmt_H1);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, bizMsg.xxTotAmt_H1);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, bizMsg.xxTotAmt_H1);

            BigDecimal funcAmt = getFuncAmt(rcptUpdMsg.glblCmpyCd.getValue(), rcptUpdMsg.dealCcyCd.getValue(), bizMsg.xxTotAmt_H1.getValue(), rcptUpdMsg.glDt.getValue());
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, funcAmt);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, funcAmt);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, funcAmt);
        } else if (ZYPCommonFunc.hasValue(beforeArCashApplyStsCd) && beforeArCashApplyStsCd.equals(AR_CASH_APPLY_STS.UNIDENTIFIED) && ZYPCommonFunc.hasValue(bizMsg.payerCustCd_H)) {
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);

            // update Receipt Amount
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, bizMsg.xxTotAmt_H1);
            BigDecimal funcAmt = getFuncAmt(rcptUpdMsg.glblCmpyCd.getValue(), rcptUpdMsg.dealCcyCd.getValue(), bizMsg.xxTotAmt_H1.getValue(), rcptUpdMsg.glDt.getValue());
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, funcAmt);
        }

        if (isFirst) {
            // update Receipt Status
            // START 2018/09/20 T.Ogura [QC#28097,MOD]
//            rcptUpdMsg.arRcptStsCd.setValue(AR_RCPT_STS.OPEN);
            rcptUpdMsg.arRcptStsCd.setValue(AR_RCPT_STS.UNAPPLIED);
            // END   2018/09/20 T.Ogura [QC#28097,MOD]
        }

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.payerCustCd, bizMsg.payerCustCd_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.remDsBankAcctPk, bizMsg.remDsBankAcctPk_H1);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.custDsBankAcctPk, bizMsg.custDsBankAcctPk_H2);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptNoteTxt, bizMsg.arRcptNoteTxt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRefTxt, bizMsg.arRcptRefTxt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptCmntTxt, bizMsg.arRcptCmntTxt_H);
        // START 2020/01/31 [QC#54826, ADD]
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptMiscCmntTxt, bizMsg.arRcptMiscCmntTxt_H);
        // END 2020/01/31 [QC#54826, ADD]
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRemDt, bizMsg.arRcptRemDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.crCardApvlCd, bizMsg.crCardApvlCd_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.pmtSvcOrdNum, bizMsg.pmtSvcOrdNum_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.locNum, bizMsg.locNum_H);

        if (ZYPCommonFunc.hasValue(bizMsg.rcptNum_H)) {
            EZDTBLAccessor.update(rcptUpdMsg);
        } else {
            EZDTBLAccessor.create(rcptUpdMsg);
        }

        if (!RTNCD_NORMAL.equals(rcptUpdMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return;
        }

        if (!ZYPCommonFunc.hasValue(beforeArRcptStsCd) || beforeArRcptStsCd.equals(AR_RCPT_STS.NEW)) {
            if (!createArRcptDtl(bizMsg, rcptUpdMsg)) {
                return;
            }
        }

        //---------------------------------------------------------
        // Cash Application API
        //---------------------------------------------------------
        // START 2024/02/23 S.Ikariya [QC#63452, MOD]
        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        if (isFirst) {
//            AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        // END 2024/02/23 S.Ikariya [QC#63452, MOD]
            if (!createArTrxBal(bizMsg, arTrxBalTMsg, rcptUpdMsg)) {
                return;
            }
            if (!createArApplyIntfcWrk(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
                return;
            }
            // START 2019/01/18 Y.Matsui [QC#29926,ADD]
            if (bizMsg.ajeCratCpltFlg_H.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                if (!updateArRcptUnApply(bizMsg, rcptUpdMsg)) {
                    return;
                }
            }
        // END   2019/01/18 Y.Matsui [QC#29926,ADD]
        // START 2022/01/17 G.Delgado [QC#58844, ADD]
        } else {
            // Set AR_TRX_BAL customer
            if (!updateArTrxBalRcptCust(bizMsg)) {
                return;
            }
            // END 2022/01/17 G.Delgado [QC#58844, ADD]
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            AR_TRX_BALTMsg rcptInMsg = new AR_TRX_BALTMsg();
            rcptInMsg.setSQLID("001");
            rcptInMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            rcptInMsg.setConditionValue("arTrxNum01", bizMsg.rcptNum_H.getValue());
            AR_TRX_BALTMsgArray rcptOutMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(rcptInMsg);

            if (rcptOutMsg != null && rcptOutMsg.getValidCount() > 0) {
                arTrxBalTMsg = rcptOutMsg.no(0);
            } else {
                bizMsg.setMessageInfo("NFCM0864E", new String[] {"AR_TRX_BAL", bizMsg.rcptNum_H.getValue()});
                return;
            }
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
        }
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        if(ZYPCommonFunc.hasValue(bizMsg.invNum_H)
                && (bizMsg.xxTotAmt_H1.getValue().compareTo(BigDecimal.ZERO) > 0)) {

            AR_TRX_BALTMsg invData = new AR_TRX_BALTMsg();

            AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            inMsg.setConditionValue("arTrxNum01", bizMsg.invNum_H.getValue());
            AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if(outMsg != null && outMsg.getValidCount() > 0){
                invData = outMsg.no(0);
            } else {
                bizMsg.setMessageInfo("NFCM0864E", new String[] {"AR_TRX_BAL", bizMsg.invNum_H.getValue()});
                return;
            } 

            if(ZYPCommonFunc.hasValue(invData.arCashApplyStsCd.getValue())
                    && (AR_CASH_APPLY_STS.UNAPPLIED.equals(invData.arCashApplyStsCd.getValue())
                            || AR_CASH_APPLY_STS.PARTIAL.equals(invData.arCashApplyStsCd.getValue()))
                    && invData.dealRmngBalGrsAmt.getValue().compareTo(BigDecimal.ZERO) > 0) {
                if (!createArApplyIntfcWrkForCashApp(bizMsg, rcptUpdMsg, arTrxBalTMsg, invData)) {
                    return;
                }
            }
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]

        //---------------------------------------------------------
        // Update AR_TRX_BAL, AR_RCPT_UN_APPLY
        //---------------------------------------------------------
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        if (bizMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.OPEN)) {
        if (bizMsg.arRcptStsCd_H.getValue().equals(AR_RCPT_STS.UNAPPLIED)) {
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
            if (bizMsg.xxTotAmt_BK.getValue().compareTo(bizMsg.xxTotAmt_H1.getValue()) != 0) {
                if (!NFCCmnMethod.updateArTrxBalRcptUnApply(bizMsg.glblCmpyCd.getValue(), rcptUpdMsg)) {
                    bizMsg.setMessageInfo("NFCM0032E");
                    return;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.rcptNum_H, rcptNum);

        //---------------------------------------------------------
        // Update AR_BAT_RCPT AR_BAT_RCPT_STS
        //---------------------------------------------------------
        updateArBatRcptStatus(cMsg);

        // START 2018/01/30 E.Kameishi [QC#23681,ADD]
        //---------------------------------------------------------
        // Update Credit Profile
        //---------------------------------------------------------
        if (!NFCL3030CommonLogic.callCrPrflUpdtAPI(bizMsg, rcptUpdMsg)){
            return;
        }
        // END 2018/01/30 E.Kameishi [QC#23681,ADD]
    }

    /**
     * Create AR Receipt Detail
     * @param bizMsg NFCL3030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean createArRcptDtl(NFCL3030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {
        AR_RCPT_DTLTMsg inMsg = new AR_RCPT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, RCPT_DTL_NUM);
        AR_RCPT_DTLTMsg outMsg = (AR_RCPT_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg != null) {
//            bizMsg.setMessageInfo("ZZZM9004E");
//            return false;
            ZYPEZDItemValueSetter.setValue(outMsg.dealRcptDtlAmt, arRcptTMsg.dealNetRcptAmt);
            ZYPEZDItemValueSetter.setValue(outMsg.funcRcptDtlAmt, arRcptTMsg.funcNetRcptAmt);

            EZDTBLAccessor.update(outMsg);
            if (!RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
            return true;
        }
//        ZYPEZDItemValueSetter.setValue(inMsg.dealRcptDtlAmt, arRcptTMsg.dealRcptAmt);
//        ZYPEZDItemValueSetter.setValue(inMsg.funcRcptDtlAmt, arRcptTMsg.funcRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.dealRcptDtlAmt, arRcptTMsg.dealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRcptDtlAmt, arRcptTMsg.funcNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(inMsg);

        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }


    /**
     * Create AR Transaction Balance
     * @param bizMsg NFCL3030CMsg
     * @param inMsg AR_TRX_BALTMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean createArTrxBal(NFCL3030CMsg bizMsg, AR_TRX_BALTMsg inMsg, AR_RCPTTMsg arRcptTMsg) {
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, bizMsg.rcptNum_H);

        BigDecimal arTrxBalPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_TRX_BAL_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, bizMsg.stdCcyCd_H);

//        ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, arRcptTMsg.dealRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

//        ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, arRcptTMsg.dealRcptRmngBalAmt);
//        ZYPEZDItemValueSetter.setValue(inMsg.dealNetSlsAmt, arRcptTMsg.dealRcptRmngBalAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealNetSlsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.dealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.funcCcyCd, bizMsg.stdCcyCd_H);

//        ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, arRcptTMsg.funcRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRvalVarAmt, BigDecimal.ZERO);

//        ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, arRcptTMsg.funcRcptRmngBalAmt);
//        ZYPEZDItemValueSetter.setValue(inMsg.funcNetSlsAmt, arRcptTMsg.funcRcptRmngBalAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcNetSlsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.funcFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxDt, arRcptTMsg.rcptDt);
        ZYPEZDItemValueSetter.setValue(inMsg.glDt, bizMsg.glDt_H);
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, bizMsg.payerCustCd_H);
        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, arRcptTMsg.tocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, arRcptTMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(inMsg.exptFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(inMsg.billToCustAcctCd, bizMsg.payerCustCd_H);

        ZYPEZDItemValueSetter.setValue(inMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, arRcptTMsg.rcptChkNum);

        EZDTBLAccessor.create(inMsg);
        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }

    // START 2022/01/17 G.Delgado [QC#58844, ADD]
    /**
     * Update AR_TRX_BAL customer
     * @param bizMsg NFCL3030CMsg
     * @return boolean
     */
    public boolean updateArTrxBalRcptCust(NFCL3030CMsg bizMsg) {

        // Retrieve AR_TRX_BAL
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", bizMsg.rcptNum_H.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
        if (1 > outMsg.length()) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        AR_TRX_BALTMsg updMsg = outMsg.no(0);

        String payerCustCd = bizMsg.payerCustCd_H.getValue();
        String billToCustCd = (String) NFCL3030Query.getInstance().getBillToCustCd(bizMsg).getResultObject();

        // Update customer
        ZYPEZDItemValueSetter.setValue(updMsg.payerCustCd, payerCustCd);
        ZYPEZDItemValueSetter.setValue(updMsg.billToCustAcctCd, payerCustCd);
        ZYPEZDItemValueSetter.setValue(updMsg.billToCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(updMsg.sellToCustCd, payerCustCd);

        EZDTBLAccessor.update(updMsg);
        if (!RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }
    // END 2022/01/17 G.Delgado [QC#58844, ADD]

    /**
     * 
     * @param bizMsg NFCL3030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean updateArTrxBalRcptUnApply(NFCL3030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {

        if (arRcptTMsg.arCashApplyStsCd.getValue().equals(AR_CASH_APPLY_STS.UNAPPLIED)) {
            //-----------------------------------------
            // Update AR_TRX_BAL
            //-----------------------------------------
            AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
            inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("arTrxNum01", bizMsg.rcptNum_H.getValue());
            inMsg.setMaxCount(1);
            inMsg.setSQLID("001");
            AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (1 > outMsg.length()) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
            BigDecimal arTrxBalPk = outMsg.no(0).arTrxBalPk.getValue();

            inMsg.clear();
            inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inMsg.arTrxBalPk.setValue(arTrxBalPk);
            AR_TRX_BALTMsg updMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (updMsg == null) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updMsg.dealOrigGrsAmt, arRcptTMsg.dealNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.dealRmngBalGrsAmt, arRcptTMsg.dealNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.funcOrigGrsAmt, arRcptTMsg.funcNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.funcRmngBalGrsAmt, arRcptTMsg.funcNetRcptAmt.getValue());

            EZDTBLAccessor.update(updMsg);
            if (!RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }

            //-----------------------------------------
            // Update AR_RCPT_UN_APPLY
            //-----------------------------------------
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("arRcptNum", bizMsg.rcptNum_H.getValue());
            S21SsmEZDResult arRcptUnApply = NFCL3030Query.getInstance().getArRcptUnApply(bizMsg, ssmParam);
            if (!arRcptUnApply.isCodeNormal()) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
            List<Map> arRcptUnApplyList = new ArrayList<Map>();
            arRcptUnApplyList = (List<Map>) arRcptUnApply.getResultObject();
            Map arRcptUnApplyData = arRcptUnApplyList.get(0);
            BigDecimal arCashAppPk = new BigDecimal(arRcptUnApplyData.get("AR_CASH_APP_PK").toString());
            String arCashAppSqNum = (String) arRcptUnApplyData.get("AR_CASH_APP_SQ_NUM");

            AR_RCPT_UN_APPLYTMsg inMsg2 = new AR_RCPT_UN_APPLYTMsg();
            inMsg2.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inMsg2.arCashAppPk.setValue(arCashAppPk);
            inMsg2.arCashAppSqNum.setValue(arCashAppSqNum);
            AR_RCPT_UN_APPLYTMsg updMsg2 = (AR_RCPT_UN_APPLYTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg2);
            if (updMsg2 == null) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updMsg2.dealUnApplyAmt, arRcptTMsg.dealNetRcptAmt.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg2.funcUnApplyAmt, arRcptTMsg.funcNetRcptAmt.getValue());

            EZDTBLAccessor.update(updMsg2);
            if (!RTNCD_NORMAL.equals(updMsg2.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
        }

        //-----------------------------------------
        // Update AR_RCPT_DTL
        //-----------------------------------------
        AR_RCPT_DTLTMsg inMsg3 = new AR_RCPT_DTLTMsg();
        inMsg3.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg3.rcptNum.setValue(arRcptTMsg.rcptNum.getValue());
        inMsg3.rcptDtlNum.setValue(RCPT_DTL_NUM);
        AR_RCPT_DTLTMsg updMsg3 = (AR_RCPT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg3);
        if (updMsg3 == null) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        ZYPEZDItemValueSetter.setValue(updMsg3.dealRcptDtlAmt, arRcptTMsg.dealNetRcptAmt.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg3.funcRcptDtlAmt, arRcptTMsg.funcNetRcptAmt.getValue());
        EZDTBLAccessor.update(updMsg3);
        if (!RTNCD_NORMAL.equals(updMsg3.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }

 // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Create AR Apply Interface Work
     * @param bizMsg NFCL3030CMsg
     * @param rcptUpdMsg AR_RCPTTMsg
     * @param arTrxBalTMsg AR_TRX_BALTMsg
     * @param globalMsg NFCL3030_BSMsg
     * @param invData AR_TRX_BALTMsg
     * @return boolean
     */
    private boolean createArApplyIntfcWrkForCashApp(NFCL3030CMsg bizMsg, AR_RCPTTMsg rcptUpdMsg, AR_TRX_BALTMsg arTrxBalTMsg, AR_TRX_BALTMsg invData) {

        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        String userId = getContextUserInfo().getUserId();
        if (!ZYPCommonFunc.hasValue(currentSystemTime)) {
            bizMsg.setMessageInfo("NFCM0041E");
        }
        String applyGrpKey = userId.concat(currentSystemTime);
        BigDecimal applyGrpSubPk = BigDecimal.ONE;

        BigDecimal dealApplyAmt = BigDecimal.ZERO;
        if(bizMsg.xxTotAmt_H1.getValue().compareTo(invData.dealRmngBalGrsAmt.getValue()) >= 0) {
            dealApplyAmt = invData.dealRmngBalGrsAmt.getValue();
        } else {
            dealApplyAmt = bizMsg.xxTotAmt_H1.getValue();
        }

        AR_APPLY_INTFC_WRKTMsg inMsg = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpKey, userId.concat(currentSystemTime));
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpSubPk, applyGrpSubPk);
        ZYPEZDItemValueSetter.setValue(inMsg.bizAppId, "NFCL3030");
        ZYPEZDItemValueSetter.setValue(inMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.procTpCd, NFCConst.CST_PROC_TP_CD_NEW);
        ZYPEZDItemValueSetter.setValue(inMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(inMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(inMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(inMsg.usrId, userId);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, rcptUpdMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, RCPT_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptGlDt, rcptUpdMsg.glDt);
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, rcptUpdMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalPk, arTrxBalTMsg.arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrTz, rcptUpdMsg.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, rcptUpdMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, rcptUpdMsg.glDt);
        ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.invNum, invData.arTrxNum);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, invData.arTrxTpCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, invData.arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalLastUpdTs, invData.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalTz, invData.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, dealApplyAmt);

        EZDTBLAccessor.create(inMsg);

        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }

        applyGrpSubPk = applyGrpSubPk.add(BigDecimal.ONE);

        EZDConnectionMgr.getInstance().commit();
        String batProcDt = ZYPDateUtil.getBatProcDate(getGlobalCompanyCode());
        NFZC301001PMsg pMsg = new NFZC301001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.applyGrpKey, applyGrpKey);
        ZYPEZDItemValueSetter.setValue(pMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.batDt, batProcDt);

        NFZC301001 api = new NFZC301001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        String result = pMsg.getReturnCode();

        if (!APPLY_RTNCD_NORMAL.equals(result)) {

            if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Unprocessing" });
            } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Cash Application Error" });
            } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Exclusion Error" });
            } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
            } else {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
            }
        }
        return true;
    }

    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Create AR Apply Interface Work
     * @param bizMsg NFCL3030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @param arTrxBalTMsg AR_TRX_BALTMsg
     * @return boolean
     */
    public boolean createArApplyIntfcWrk(NFCL3030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg, AR_TRX_BALTMsg arTrxBalTMsg) {
        AR_APPLY_INTFC_WRKTMsg inMsg = new AR_APPLY_INTFC_WRKTMsg();
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        String userId = getContextUserInfo().getUserId();
        if (!ZYPCommonFunc.hasValue(currentSystemTime)) {
            bizMsg.setMessageInfo("NFCM0041E");
        }

        String applyGrpKey = userId.concat(currentSystemTime);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpKey, applyGrpKey);
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.bizAppId, "NFCL0210");
        ZYPEZDItemValueSetter.setValue(inMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.procTpCd, NFCConst.CST_PROC_TP_CD_NEW);
        ZYPEZDItemValueSetter.setValue(inMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(inMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(inMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(inMsg.usrId, userId);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, RCPT_DTL_NUM);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptGlDt, arRcptTMsg.glDt);
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, arRcptTMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalPk, arTrxBalTMsg.arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrTz, arRcptTMsg.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, bizMsg.stdCcyCd_H);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, arRcptTMsg.dealNetRcptAmt.getValue());

        EZDTBLAccessor.create(inMsg);

        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }

        String batProcDt = ZYPDateUtil.getBatProcDate(getGlobalCompanyCode());
        NFZC301001PMsg pMsg = new NFZC301001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.applyGrpKey, applyGrpKey);
        ZYPEZDItemValueSetter.setValue(pMsg.dealSqNum, DEAL_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(pMsg.batDt, batProcDt);

        NFZC301001 api = new NFZC301001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        String result = pMsg.getReturnCode();

        if (!APPLY_RTNCD_NORMAL.equals(result)) {
            if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Unprocessing" });
            } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Cash Application Error" });
            } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Exclusion Error" });
            } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
            } else {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
            }
        }
        return true;
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void reverse(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        String batProcDt = ZYPDateUtil.getBatProcDate(getGlobalCompanyCode());
        boolean errFlg = false;

        if (!NFCL3030CommonLogic.checkPayerCustCdLock(getGlobalCompanyCode(), bizMsg, getContextUserInfo())) {
            errFlg = true;
        }

        if (bizMsg.voidGlDt_H.getValue().compareTo(bizMsg.glDt_H.getValue()) < 0) {
            bizMsg.voidGlDt_H.setErrorInfo(1, "NFCM0776E", new String[] {"Reversal GL Date", "Accounting Date"});
            errFlg = true;
        } else if (!NFCL3030CommonLogic.checkGlDate(getGlobalCompanyCode(), bizMsg.voidGlDt_H.getValue())) {
            bizMsg.voidGlDt_H.setErrorInfo(1, "NFCM0045E");
            errFlg = true;
        }

        String salesDt = ZYPDateUtil.getSalesDate();
        if (bizMsg.voidGlDt_H.getValue().compareTo(salesDt) > 0) {
            bizMsg.voidGlDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
            errFlg = true;
        }

        if (bizMsg.voidDt_H.getValue().compareTo(salesDt) > 0) {
            bizMsg.voidDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
            errFlg = true;
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.voidDt_H) || bizMsg.rcptDt_H.getValue().compareTo(bizMsg.voidDt_H.getValue()) > 0) {
            bizMsg.voidDt_H.setErrorInfo(1, "NFCM0776E", new String[] {"Reversal Date", "Receipt Date"});
            errFlg = true;
        }

        // 2018/01/19 QC#23136 add start
        if (ZYPCommonFunc.hasValue(bizMsg.rcptNum_H)) {
            if (NFCL3030CommonLogic.checkInvoiceApply(getGlobalCompanyCode(), bizMsg)) {
                errFlg = true;
            }

            // START 2022/08/04 D.Mamaril [QC#60376,ADD]
            if (NFCL3030CommonLogic.checkOnAccountRefund(getGlobalCompanyCode(), bizMsg)) {
                bizMsg.setMessageInfo(NFCM0920E);
                errFlg = true;
            }
            // END 2022/08/04 D.Mamaril [QC#60376,ADD]
        }
        // 2018/01/19 QC#23136 add end

        // 2019/07/09 QC#51282 Add Start
        AR_RCPTTMsg checkMsg = new AR_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(checkMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(checkMsg.rcptNum, bizMsg.rcptNum_H);
        checkMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(checkMsg); 

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H.getValue(), bizMsg.ezUpTimeZone_H.getValue(), checkMsg.ezUpTime.getValue(), checkMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo("ZZZM9004E");
            errFlg = true;
        }
        // 2019/07/09 QC#51282 Add End

        if (errFlg) {
            return;
        }

        if (bizMsg.arCashApplyStsCd_H.getValue().equals(AR_CASH_APPLY_STS.UNIDENTIFIED)) {
            AR_RCPTTMsg arRcptInMsg = new AR_RCPTTMsg();
            ZYPEZDItemValueSetter.setValue(arRcptInMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(arRcptInMsg.rcptNum, bizMsg.rcptNum_H);
            AR_RCPTTMsg arRcptUpdMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(arRcptInMsg);

            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.dealApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.dealRfAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.dealVoidAmt, arRcptUpdMsg.dealRcptAmt);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.funcApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.funcApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.funcRfAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.funcVoidAmt, arRcptUpdMsg.funcRcptAmt);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.VOID);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.voidFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptVoidRsnCd, bizMsg.arRcptVoidRsnCd_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.voidDt, bizMsg.voidDt_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.voidGlDt, bizMsg.voidGlDt_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.cashAppDt, batProcDt);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.rcptNum, bizMsg.rcptNum_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptRvrsRsnCd, bizMsg.arRcptRvrsRsnCd_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptRvrsCmntTxt, bizMsg.arRcptRvrsCmntTxt_H);
            // START 2018/06/20 E.Kameishi [QC#25916,MOD]
            // Mod Start 2018/01/26 S21_NA#21401
            // ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
            // ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptStsCd, AR_RCPT_STS.REVERSED_MANUAL);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptStsCd, AR_RCPT_STS.REVERSED);
            // Mod End 2018/01/26 S21_NA#21401
            // END 2018/06/20 E.Kameishi [QC#25916,MOD]

            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.dealVoidAmt, arRcptUpdMsg.dealNetRcptAmt);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.funcVoidAmt, arRcptUpdMsg.funcNetRcptAmt);

            EZDTBLAccessor.update(arRcptUpdMsg);

            if (!RTNCD_NORMAL.equals(arRcptUpdMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }

            AR_RCPT_UN_APPLYTMsg unApplyUpdTMsg = new AR_RCPT_UN_APPLYTMsg();
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.rcptNum, arRcptUpdMsg.rcptNum);

            BigDecimal arCashAppPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_CASH_APP_SQ);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.arCashAppPk, arCashAppPk);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.arCashAppSqNum, AR_CASH_APP_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.rcptNum, arRcptUpdMsg.rcptNum);

            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.dealUnApplyAmt, arRcptUpdMsg.dealRcptRmngBalAmt);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.funcUnApplyAmt, arRcptUpdMsg.funcRcptRmngBalAmt);

            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.arUnApplyTpCd, AR_UN_APPLY_TP.ON_ACCOUNT);
            // START 2019/01/18 Y.Matsui [QC#29926,MOD]
            // ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.glDt, bizMsg.glDt_H);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.glDt, bizMsg.voidGlDt_H);
            // END   2019/01/18 Y.Matsui [QC#29926,MOD]
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.cashAppDt, bizMsg.glDt_H);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.arUnApplyStsCd, AR_UN_APPLY_STS.VOID);
            ZYPEZDItemValueSetter.setValue(unApplyUpdTMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.create(unApplyUpdTMsg);
            if (!RTNCD_NORMAL.equals(unApplyUpdTMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }

        } else {
            Map<String, Object> ssmMap = new HashMap<String, Object>();
            ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
            ssmMap.put("cMsg", bizMsg);
            ssmMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
            ssmMap.put("arAdjTrxTp_Adjustment", AR_ADJ_TRX_TP.ADJUSTMENT);
            ssmMap.put("arAdjTrxTp_ArCashRefund", AR_ADJ_TP.A_OR_R_CASH_REFUND);
            ssmMap.put("arTrxTp_OnAccount", AR_TRX_TP.ON_ACCOUNT);

            S21SsmEZDResult ssmResult = NFCL3030Query.getInstance().getRefundRcptCount(ssmMap);
            if (ssmResult.isCodeNormal()) {
                BigDecimal count = (BigDecimal) ssmResult.getResultObject();
                if (count.compareTo(BigDecimal.ZERO) > 0) {
                    bizMsg.setMessageInfo("NFCM0036E");
                    return;
                }
            }
            String rcptNum = null;
            String rcptHdrLastUpdTs = null;
            String rcptHdrTz = null;
            String rcptTrxBalLastUpdTs = null;
            String rcptTrxBalTz = null;
            BigDecimal rcptTrxBalPk = null;
            String payerCustCd = null;
            BigDecimal rcptDealApplyAmt = null;
            BigDecimal rcptDealRcptAmt = null;
            String rcptDt = null;
            // 2018/01/18 QC#23136 add start
            BigDecimal rcptDealApplyAdjAmt = null;
            // 2018/01/18 QC#23136 add end

            ssmResult = NFCL3030Query.getInstance().getReversedRcpt(ssmMap);

            if (ssmResult.isCodeNormal()) {
                Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
                rcptNum = (String) map.get("RCPT_NUM");
                rcptHdrLastUpdTs = (String) map.get("RCPT_HDR_LAST_UPD_TS");
                rcptHdrTz = (String) map.get("RCPT_HDR_TZ");
                rcptTrxBalLastUpdTs = (String) map.get("RCPT_TRX_BAL_LAST_UPD_TS");
                rcptTrxBalTz = (String) map.get("RCPT_TRX_BAL_TZ");
                rcptTrxBalPk = (BigDecimal) map.get("RCPT_TRX_BAL_PK");
                payerCustCd = (String) map.get("PAYER_CUST_CD");
                rcptDealApplyAmt = (BigDecimal) map.get("RCPT_DEAL_APPLY_AMT");
                rcptDealRcptAmt = (BigDecimal) map.get("RCPT_DEAL_RCPT_AMT");
                rcptDt = (String) map.get("RCPT_DT");
                // 2018/01/18 QC#23136 add start
                rcptDealApplyAdjAmt = ((BigDecimal) map.get("RCPT_DEAL_APPLY_ADJ_AMT")).multiply(new BigDecimal(-1));
                // 2018/01/18 QC#23136 add end

            }

            ssmResult = NFCL3030Query.getInstance().getReversedCashApplicationInfo(ssmMap);

            String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
            String userId = getContextUserInfo().getUserId();
            String applyGrpKey = userId.concat(currentSystemTime);

            // START 2019/09/03 Y.Matsui [QC#53152,ADD]
            Set<BigDecimal> autoPurgeOfsCrmPkSet = new HashSet<BigDecimal>();
            // END   2019/09/03 Y.Matsui [QC#53152,ADD]

            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> cashAppInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
                BigDecimal applyGrpSubPk = BigDecimal.ZERO;
                for (Map<String, Object> map : cashAppInfoList) {

                    String arApplyTpCd = (String) map.get("AR_APPLY_TP_CD");
                    BigDecimal appDealApplyAmt = (BigDecimal) map.get("DEAL_APPLY_AMT");

                    BigDecimal invArTrxBalPk = (BigDecimal) map.get("INV_AR_TRX_BAL_PK");
                    String invTrxBalLastUpdTs = (String) map.get("INV_TRX_BAL_LAST_UPD_TS");
                    String invTrxBalTz = (String) map.get("INV_TRX_BAL_TZ");
                    String invArTrxNum = (String) map.get("INV_AR_TRX_NUM");
                    String invArTrxTpCd = (String) map.get("INV_AR_TRX_TP_CD");
                    BigDecimal invDealApplyAdjAmt = (BigDecimal) map.get("INV_DEAL_APPLY_ADJ_AMT");
                    String invArCustRefNum = (String) map.get("INV_AR_CUST_REF_NUM");

                    BigDecimal accArTrxBalPk = (BigDecimal) map.get("ACC_AR_TRX_BAL_PK");
                    String accTrxBalLastUpdTs = (String) map.get("ACC_TRX_BAL_LAST_UPD_TS");
                    String accTrxBalTz = (String) map.get("ACC_TRX_BAL_TZ");
                    String accArTrxNum = (String) map.get("ACC_AR_TRX_NUM");
                    String accArTrxTpCd = (String) map.get("ACC_AR_TRX_TP_CD");
                    String accArCustRefNum = (String) map.get("ACC_AR_CUST_REF_NUM");

                    String arAdjTrxTpCd = (String) map.get("AR_ADJ_TRX_TP_CD");
                    String arAdjNum = (String) map.get("AR_ADJ_NUM");
                    String arAdjTpCd = (String) map.get("AR_ADJ_TP_CD");
                    String tocCd = (String) map.get("TOC_CD");
                    String coaProdCd = (String) map.get("COA_PROD_CD");
                    String adjCmntTxt = (String) map.get("ADJ_CMNT_TXT");

                    // START 2018/05/17 E.Kameishi [QC#25458, MOD]
                    String arWrtOffNoteCd = (String) map.get("AR_WRT_OFF_NOTE_CD");
                    String origArAdjTpCd = (String) map.get("ORIG_AR_ADJ_TP_CD");
                    // END 2018/05/17 E.Kameishi [QC#25458, MOD]

                    // START 2019/09/03 Y.Matsui [QC#53152,ADD]
                    if (ZYPCommonFunc.hasValue(invArTrxTpCd) && invArTrxTpCd.equals(AR_TRX_TP.CREDIT_MEMO)) {
                        if (ZYPConstant.FLG_ON_Y.equals(map.get("AR_AUTO_PURGE_OFS_FLG"))) {
                            autoPurgeOfsCrmPkSet.add(invArTrxBalPk);
                        }
                    }
                    // END   2019/09/03 Y.Matsui [QC#53152,ADD]

                    AR_APPLY_INTFC_WRKTMsg inMsg = new AR_APPLY_INTFC_WRKTMsg();

                    if (!ZYPCommonFunc.hasValue(currentSystemTime)) {
                        bizMsg.setMessageInfo("NFCM0041E");
                    }
                    applyGrpSubPk = applyGrpSubPk.add(BigDecimal.ONE);

                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inMsg.applyGrpKey, applyGrpKey);
                    ZYPEZDItemValueSetter.setValue(inMsg.applyGrpSubPk, applyGrpSubPk);
                    ZYPEZDItemValueSetter.setValue(inMsg.upldCsvRqstPk, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(inMsg.procTpCd, NFCConst.CST_PROC_TP_CD_CANC);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealSqNum, DEAL_SQ_NUM);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
                    ZYPEZDItemValueSetter.setValue(inMsg.procStsCd, PROC_STS.IN_COMPLETED);
                    ZYPEZDItemValueSetter.setValue(inMsg.usrId, userId);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, rcptNum);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, RCPT_DTL_NUM);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptInProcSqPk, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, payerCustCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalPk, rcptTrxBalPk);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrLastUpdTs, rcptHdrLastUpdTs);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrTz, rcptHdrTz);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalLastUpdTs, rcptTrxBalLastUpdTs);
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalTz, rcptTrxBalTz);
                    ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(inMsg.crTrxBalPk, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, bizMsg.stdCcyCd_H);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, appDealApplyAmt.negate());
                    ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);

                    String arAcctDt = NFCL3030CommonLogic.getArAcctDt(getGlobalCompanyCode());
                    String arAcctYrMth = NFCL3030CommonLogic.getArAcctYrMth(getGlobalCompanyCode());
                    String batProcThisYrMth = batProcDt.substring(0, 6);
                    if (arAcctYrMth.equals(batProcThisYrMth)) {
                        if (rcptDt.equals(batProcThisYrMth)) {
                            ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, rcptDt);
                        } else {
                            ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, arAcctDt);
                        }
                    } else {
                        Calendar calendar = Calendar.getInstance();
                        int batProcYear = Integer.parseInt(batProcDt.substring(0, 4));
                        int batProcMonth = Integer.parseInt(batProcDt.substring(4, 6));
                        calendar.set(batProcYear, batProcMonth, 1);
                        calendar.add(Calendar.MONTH, -2);
                        String batProcLastYrMth = new SimpleDateFormat("yyyyMM").format(calendar.getTime());

                        if (rcptDt.equals(batProcThisYrMth)) {
                            ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, arAcctDt);
                        } else if (rcptDt.equals(batProcLastYrMth)) {
                            ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, arAcctDt);
                        } else {
                            ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, rcptDt);
                        }
                    }
                    // START 2018/06/21 E.Kameishi [QC#26673,MOD]
                    // 2017/11/09 QC#21403 mod start
//                    if (ZYPCommonFunc.hasValue(invArTrxTpCd) && (invArTrxTpCd.equals(AR_TRX_TP.INVOICE) || invArTrxTpCd.equals(AR_TRX_TP.CREDIT_MEMO)) && invDealApplyAdjAmt.compareTo(BigDecimal.ZERO) == 0) {
                    // MOD START 2019/08/20 QC#52780
                    if (ZYPCommonFunc.hasValue(invArTrxTpCd) && (invArTrxTpCd.equals(AR_TRX_TP.INVOICE) || invArTrxTpCd.equals(AR_TRX_TP.CREDIT_MEMO) || invArTrxTpCd.equals(AR_TRX_TP.DEDUCTION) || invArTrxTpCd.equals(AR_TRX_TP.ON_ACCOUNT))) {
//                  if (ZYPCommonFunc.hasValue(invArTrxTpCd) && (invArTrxTpCd.equals(AR_TRX_TP.INVOICE) || invArTrxTpCd.equals(AR_TRX_TP.CREDIT_MEMO) || invArTrxTpCd.equals(AR_TRX_TP.DEDUCTION) || invArTrxTpCd.equals(AR_TRX_TP.ON_ACCOUNT)) && invDealApplyAdjAmt.compareTo(BigDecimal.ZERO) == 0) {
                    // MOD END 2019/08/20 QC#52780
                    // 2017/11/09 QC#21403 mod end
                    // END 2018/06/21 E.Kameishi [QC#26673,MOD]
                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, invArTrxBalPk);
                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalLastUpdTs, invTrxBalLastUpdTs);
                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalTz, invTrxBalTz);

                        ZYPEZDItemValueSetter.setValue(inMsg.invNum, invArTrxNum);
                        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, invArTrxTpCd);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, invArCustRefNum);
                    // START 2018/06/21 E.Kameishi [QC#26673,MOD]
                // DEL START 2019/08/20 QC#52780
                    // 2017/11/09 QC#21403 mod start
                    //} else if (ZYPCommonFunc.hasValue(invArTrxTpCd) && (invArTrxTpCd.equals(AR_TRX_TP.INVOICE) || invArTrxTpCd.equals(AR_TRX_TP.CREDIT_MEMO)) && invDealApplyAdjAmt.compareTo(BigDecimal.ZERO) > 0) {
//                    } else if (ZYPCommonFunc.hasValue(invArTrxTpCd) && (invArTrxTpCd.equals(AR_TRX_TP.INVOICE) || invArTrxTpCd.equals(AR_TRX_TP.CREDIT_MEMO) || invArTrxTpCd.equals(AR_TRX_TP.DEDUCTION) || invArTrxTpCd.equals(AR_TRX_TP.ON_ACCOUNT)) && invDealApplyAdjAmt.compareTo(BigDecimal.ZERO) > 0) {
//                    // 2017/11/09 QC#21403 mod end
//                    // END 2018/06/21 E.Kameishi [QC#26673,MOD]
//                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, invArTrxBalPk);
//                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalLastUpdTs, invTrxBalLastUpdTs);
//                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalTz, invTrxBalTz);
//
//                        ZYPEZDItemValueSetter.setValue(inMsg.invNum, invArTrxNum);
//                        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, invArTrxTpCd);
//                        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjNum, arAdjNum);
//                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ADJUSTMENT);
//                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTpCd, arAdjTpCd);
//                        // START 2018/05/17 E.Kameishi [QC#25458, MOD]
//                        if (ZYPCommonFunc.hasValue(arWrtOffNoteCd)) {
//                            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, invDealApplyAdjAmt.negate());
//                            ZYPEZDItemValueSetter.setValue(inMsg.arAdjTpCd, origArAdjTpCd);
//                        } else {
//                            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, invDealApplyAdjAmt);
//                        }
//                        // END 2018/05/17 E.Kameishi [QC#25458, MOD]
//                        ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, invArCustRefNum);
                // DEL START 2019/08/20 QC#52780
                    } else if (arApplyTpCd.equals(AR_APPLY_TP.ADJUSTMENT) && arAdjTrxTpCd.equals(AR_ADJ_TRX_TP.ADJUSTMENT)) {
                        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjNum, arAdjNum);
                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ADJUSTMENT);
                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTpCd, arAdjTpCd);
                        // Start 2018/01/18 H.Ikeda [QC#23136,MOD]
                        //ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, rcptDealApplyAmt);
                        //ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, rcptDealApplyAdjAmt);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, appDealApplyAmt.negate());
                        // End   2018/01/18 H.Ikeda [QC#23136,MOD]
                    } else if (arApplyTpCd.equals(AR_APPLY_TP.ADJUSTMENT) && arAdjTrxTpCd.equals(AR_ADJ_TRX_TP.ON_ACCOUNT)) {
                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, accArTrxBalPk);
                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalLastUpdTs, accTrxBalLastUpdTs);
                        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalTz, accTrxBalTz);
                        ZYPEZDItemValueSetter.setValue(inMsg.invNum, accArTrxNum);
                        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, accArTrxTpCd);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, appDealApplyAmt);
                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjNum, arAdjNum);
                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ON_ACCOUNT);
                        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTpCd, arAdjTpCd);
                        // Start 2018/01/18 H.Ikeda [QC#23136,MOD]
                        //ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, rcptDealApplyAmt);
                        //ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, rcptDealApplyAdjAmt);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, appDealApplyAmt.negate());
                        // End   2018/01/18 H.Ikeda [QC#23136,MOD]
                        ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, accArCustRefNum);
                    }
                    ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(inMsg.adjCmntTxt, adjCmntTxt);
                    ZYPEZDItemValueSetter.setValue(inMsg.tocCd, tocCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, coaProdCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, CCY_USD);

                    EZDTBLAccessor.create(inMsg);

                    if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM9003E");
                        return;
                    }
                }

                NFZC301001PMsg pMsg = new NFZC301001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(pMsg.applyGrpKey, applyGrpKey);
                ZYPEZDItemValueSetter.setValue(pMsg.dealSqNum, DEAL_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(pMsg.batDt, batProcDt);

                NFZC301001 api = new NFZC301001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                String result = pMsg.getReturnCode();
                // result == "1"
                if (!APPLY_RTNCD_NORMAL.equals(result)) {
                    if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                        bizMsg.setMessageInfo("NFCM0002E", new String[] {"Unprocessing" });
                    } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                        bizMsg.setMessageInfo("NFCM0002E", new String[] {"Cash Application Error" });
                    } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                        bizMsg.setMessageInfo("NFCM0002E", new String[] {"Exclusion Error" });
                    } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                        bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
                    } else {
                        bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
                    }
                }

                // START 2019/09/03 Y.Matsui [QC#53152,ADD]
                for (BigDecimal arTrxBalPk : autoPurgeOfsCrmPkSet) {
                    updateArAutoPurgeOfsFlg(bizMsg, arTrxBalPk);
                }
                // END   2019/09/03 Y.Matsui [QC#53152,ADD]
            }

            ssmResult = NFCL3030Query.getInstance().getReversedArCashAppPk(ssmMap);
            if (ssmResult.isCodeNormal()) {
                List<BigDecimal> updPkList = (List<BigDecimal>) ssmResult.getResultObject();
                for (BigDecimal updPk : updPkList) {
                    AR_CASH_APPTMsg updTMsg = new AR_CASH_APPTMsg();
                    ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(updTMsg.arCashAppPk, updPk);
                    ZYPEZDItemValueSetter.setValue(updTMsg.arScrCancFlg, ZYPConstant.FLG_ON_Y);
                    EZDTBLAccessor.updateByPartialKey(updTMsg, new String[] {"arScrCancFlg" });
                    if (!RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM0032E");
                        return;
                    }
                }
            }

            AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, bizMsg.rcptNum_H);
            AR_RCPTTMsg arRcptUpdMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inMsg);

            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.VOID);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.voidFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptVoidRsnCd, bizMsg.arRcptVoidRsnCd_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.voidDt, bizMsg.voidDt_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.voidGlDt, bizMsg.voidGlDt_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.cashAppDt, batProcDt);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.rcptNum, bizMsg.rcptNum_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptRvrsRsnCd, bizMsg.arRcptRvrsRsnCd_H);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptRvrsCmntTxt, bizMsg.arRcptRvrsCmntTxt_H);
            // START 2018/06/07 E.Kameishi [QC#25916,MOD]
            // Mod Start 2018/01/26 S21_NA#21401
            //ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
            // ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptStsCd, AR_RCPT_STS.REVERSED_MANUAL);
            ZYPEZDItemValueSetter.setValue(arRcptUpdMsg.arRcptStsCd, AR_RCPT_STS.REVERSED);
            // Mod Start 2018/01/26 S21_NA#21401
            // END 2018/06/07 E.Kameishi [QC#25916,MOD]

            EZDTBLAccessor.update(arRcptUpdMsg);

            if (!RTNCD_NORMAL.equals(arRcptUpdMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }

            AR_TRX_BALTMsg arTrxBalInMsg = new AR_TRX_BALTMsg();
            ZYPEZDItemValueSetter.setValue(arTrxBalInMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(arTrxBalInMsg.arTrxBalPk, rcptTrxBalPk);
            AR_TRX_BALTMsg arTrxBalUpdMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(arTrxBalInMsg);

            ZYPEZDItemValueSetter.setValue(arTrxBalUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.VOID);
            ZYPEZDItemValueSetter.setValue(arTrxBalUpdMsg.cashAppDt, batProcDt);
            EZDTBLAccessor.update(arTrxBalUpdMsg);

            if (!RTNCD_NORMAL.equals(arTrxBalUpdMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }

            AR_APPLY_INTFC_WRKTMsg applyIfWrkUpdTMsg = new AR_APPLY_INTFC_WRKTMsg();
            currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

            if (!ZYPCommonFunc.hasValue(currentSystemTime)) {
                bizMsg.setMessageInfo("NFCM0041E");
            }
            applyGrpKey = userId.concat(currentSystemTime);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.applyGrpKey, applyGrpKey);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.applyGrpSubPk, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.bizAppId, "NFCL0210");
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.upldCsvRqstPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.procTpCd, NFCConst.CST_PROC_TP_CD_CANC);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealSqNum, DEAL_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealSqDtlNum, DEAL_SQ_DTL_NUM);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.usrId, userId);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptNum, rcptNum);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptDtlNum, RCPT_DTL_NUM);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptInProcSqPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptTrxBalPk, arTrxBalUpdMsg.arTrxBalPk);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptHdrLastUpdTs, arRcptUpdMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptHdrTz, arRcptUpdMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptTrxBalLastUpdTs, arTrxBalUpdMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.rcptTrxBalTz, arTrxBalUpdMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.invTrxBalPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.crTrxBalPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.cashAppGlDt, bizMsg.voidGlDt_H);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.cashDiscPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealCashDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealOnAcctCashAmt, rcptDealRcptAmt);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(applyIfWrkUpdTMsg.dealCcyCd, CCY_USD);
            EZDTBLAccessor.create(applyIfWrkUpdTMsg);

            if (!RTNCD_NORMAL.equals(applyIfWrkUpdTMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }

            NFZC301001PMsg pMsg = new NFZC301001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(pMsg.applyGrpKey, applyGrpKey);
            ZYPEZDItemValueSetter.setValue(pMsg.dealSqNum, DEAL_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(pMsg.batDt, batProcDt);

            NFZC301001 api = new NFZC301001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            String result = pMsg.getReturnCode();

            if (!APPLY_RTNCD_NORMAL.equals(result)) {
                if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                    bizMsg.setMessageInfo("NFCM0002E", new String[] {"Unprocessing" });
                } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                    bizMsg.setMessageInfo("NFCM0002E", new String[] {"Cash Application Error" });
                } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                    bizMsg.setMessageInfo("NFCM0002E", new String[] {"Exclusion Error" });
                } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                    bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
                } else {
                    bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
                }
            }
        }

        // START 2018/07/13 Y.Matsui [QC#26993, ADD]
        updateArBatRcptStatus(cMsg);
        // END 2018/07/13 Y.Matsui [QC#26993, ADD]
    }

    /**
     * 
     * @param glblCmpyCd String
     * @param ccyCd String
     * @param dealAmt BigDecimal
     * @param paramDate String
     * @return BigDecimal
     */
    public static BigDecimal getFuncAmt(String glblCmpyCd, String ccyCd, BigDecimal dealAmt, String paramDate) {

        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        NFXC3070Bean currencyData = afxc307001.convertCurrency(glblCmpyCd, ccyCd, dealAmt, paramDate, null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {
            return dealAmt;
        } else {
            return currencyData.getFuncAmt();
        }
    }

    /**
     * 
     * @param cMsg EZDCMsg
     * @return boolean
     */
    public static boolean updateArBatRcptStatus(EZDCMsg cMsg) {
        // START 2018/07/13 Y.Matsui [QC#26993, MOD]
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;
        if (!ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H.getValue())) {
            return true;
        }

        NFZC310001PMsg pMsg = new NFZC310001PMsg();
        pMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        pMsg.arBatRcptNm.setValue(bizMsg.arBatRcptNm_H.getValue());
        new NFZC310001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        List<S21ApiMessage> xxMsgList = S21ApiUtil.getXxMsgList(pMsg);
        if (!xxMsgList.isEmpty()) {
            for (S21ApiMessage xxMsg : xxMsgList) {
                bizMsg.setMessageInfo(xxMsg.getXxMsgid(), xxMsg.getXxMsgPrmArray());
                return false;
            }
        }
        return true;
        // END 2018/07/13 Y.Matsui [QC#26993, MOD]
    }


    // START 2019/01/18 Y.Matsui [QC#29926,ADD]
    private boolean updateArRcptUnApply(NFCL3030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {

        if (!arRcptTMsg.arCashApplyStsCd.getValue().equals(AR_CASH_APPLY_STS.UNAPPLIED)) {
            return true;
        }

        if (arRcptTMsg.dealNetRcptAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }

        // -----------------------------------------
        // Update AR_RCPT_UN_APPLY
        // -----------------------------------------
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("arRcptNum", bizMsg.rcptNum_H.getValue());
        S21SsmEZDResult arRcptUnApply = NFCL3030Query.getInstance().getArRcptUnApply(bizMsg, ssmParam);
        if (!arRcptUnApply.isCodeNormal()) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        List<Map> arRcptUnApplyList = new ArrayList<Map>();
        arRcptUnApplyList = (List<Map>) arRcptUnApply.getResultObject();
        Map arRcptUnApplyData = arRcptUnApplyList.get(0);
        BigDecimal arCashAppPk = new BigDecimal(arRcptUnApplyData.get("AR_CASH_APP_PK").toString());
        String arCashAppSqNum = (String) arRcptUnApplyData.get("AR_CASH_APP_SQ_NUM");

        AR_RCPT_UN_APPLYTMsg inMsg = new AR_RCPT_UN_APPLYTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.arCashAppPk.setValue(arCashAppPk);
        inMsg.arCashAppSqNum.setValue(arCashAppSqNum);
        AR_RCPT_UN_APPLYTMsg updMsg = (AR_RCPT_UN_APPLYTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (updMsg == null) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        ZYPEZDItemValueSetter.setValue(updMsg.glDt, ZYPDateUtil.getSalesDate());

        EZDTBLAccessor.update(updMsg);
        if (!RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }
    // END   2019/01/18 Y.Matsui [QC#29926,ADD]

    // START 2019/09/03 Y.Matsui [QC#53152,ADD]
    private boolean updateArAutoPurgeOfsFlg(NFCL3030CMsg bizMsg, BigDecimal arTrxBalPk) {
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg updMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        if (updMsg == null) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(updMsg.arAutoPurgeOfsFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(updMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(updMsg);
            if (!RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
        }
        return true;
    }
    // END   2019/09/03 Y.Matsui [QC#53152,ADD]
}
