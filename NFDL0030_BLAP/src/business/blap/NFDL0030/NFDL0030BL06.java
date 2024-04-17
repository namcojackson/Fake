/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2010   Fujitsu         S.Tsuboi        Create          3280
 * 01/18/2010   Fujitsu         T.Murai         Update          S21_NA#20563
 * 04/18/2018   Hitachi         Y.Takeno        Update          QC#20940
 * 04/25/2018   Hitachi         Y.Takeno        Update          QC#20940
 * 05/18/2018   Hitachi         Y.Takeno        Update          QC#24882
 * 05/29/2018   Hitachi         Y.Takeno        Update          QC#24882
 * 06/04/2018   Hitachi         Y.Takeno        Update          QC#20940-1
 * 06/06/2018   Hitachi         Y.Takeno        Update          QC#24882-1
 * 06/15/2018   Hitachi         Y.Takeno        Update          QC#26239
 * 07/10/2018   Hitachi         Y.Takeno        Update          QC#24882-2
 * 2019/09/20   Hitachi         H.Umeda         Update          QC#53481
 * 2020/01/30   Fujitsu         H.Mizukami      Update          QC#55528
 * 2020/03/03   Fujitsu         H.Mizukami      Update          QC#55664
 * 2020/06/01   Fujitsu         H.Ikeda         Update          QC#56866
 * 2022/11/30   CITS            T.Masuyama      Update          QC#60877
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 * 2023/04/25   Hitachi         R.Takau         Update          QC#55998
 *</pre>
 */
package business.blap.NFDL0030;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0030.common.NFDL0030CommonLogic;
import business.blap.NFDL0030.constant.NFDL0030Constant;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_BAT_RCPTTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;
import business.db.CLT_PMT_CONF_LTR_WRKTMsg;
import business.db.CR_CARD_TRXTMsg;
import business.db.ECHK_RCPT_TRXTMsg;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NWZC203001PMsg;
import business.parts.NWZC203001_APMsg;
import business.parts.NWZC229001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC229001.NWZC229001;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/07/21   Hitachi         K.Kojima        Update          QC#9829
 * 2016/07/22   Hitachi         T.Tsuchida      Update          QC#9829
 * 2016/07/28   Hitachi         T.Tsuchida      Update          QC#12604
 * 2016/07/29   Hitachi         T.Tsuchida      Update          QC#12657
 * 2016/07/29   Hitachi         T.Tsuchida      Update          QC#12600
 * 2016/09/28   Hitachi         K.Kojima        Update          QC#11021
 * 2017/12/13   Hitachi         E.Kameishi      Update          QC#22096
 * 2018/04/18   Hitachi         Y.Takeno        Update          QC#20940
 * 2018/05/18   Hitachi         Y.Takeno        Update          QC#24882
 * 2018/06/15   Hitachi         Y.Takeno        Update          QC#26239
 * 2018/07/11   Fujitsu         S.Ohki          Update          QC#27002
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2019/01/07   Fujitsu         T.Ogura         Update          QC#28800
 * 2019/02/07   Fujitsu         S.Ohki          Update          QC#30023
 * 2019/07/31   Fujitsu         H.Ikeda         Update          QC#52112
 * 2020/01/30   Fujitsu         H.Mizukami      Update          QC#55528
 * 2020/05/19   Fujitsu         H.Ikeda         Update          QC#56555
 * 2020/06/01   Fujitsu         H.Ikeda         Update          QC#56866
 * 2020/09/28   CITS            K.Ogino         Update          QC#56866
 * 2020/10/06   CITS            K.Ogino         Update          QC#56866-1
 * 05/30/2022   CITS            K.Ogino         Update          QC#60127
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 * 2023/11/29   Hitachi         TZ.Win          Update          QC#62089
 * 2024/03/01   CITS            M.Okamura       Update          QC#63456
 *</pre>
 */
public class NFDL0030BL06 extends S21BusinessHandler implements NFDL0030Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0030Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NFDL0030CMsg) cMsg, (NFDL0030SMsg) sMsg);
            } else if ("NFDL0030Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NFDL0030CMsg) cMsg, (NFDL0030SMsg) sMsg);
            } else if ("NFDL0030Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_CMN_Submit((NFDL0030CMsg) cMsg, (NFDL0030SMsg) sMsg);
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
    private void doProcess_NFDL0030Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_CMN_Submit================================START", this);

        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;
        String slsDt = ZYPDateUtil.getSalesDate();
        boolean errFlg = false;

// START 2019/09/25 [QC#53481, ADD]
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.dsPmtMethCd_H)) {
            bizMsg.dsPmtMethCd_H.setErrorInfo(1, "NFDM0002E", new String[] {"Payment Type" });
            return;
        }
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (INV_NUM_ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxNum_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxTpCd_A1, AR_TRX_TP.ON_ACCOUNT);
                continue;
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealRmngBalGrsAmt_A1)) {
                Map<String, Object> prmMap = new HashMap<String, Object>();
                prmMap.put("glblCmpyCd", getGlobalCompanyCode());
                prmMap.put("invNumList", new String[]{bizMsg.A.no(i).arTrxNum_A1.getValue()});
                prmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
                prmMap.put("arCashApplyStsCdList", new String[]{AR_CASH_APPLY_STS.UNAPPLIED, AR_CASH_APPLY_STS.PARTIAL});
                prmMap.put("arTrxTpCdList", new String[]{ AR_TRX_TP.INVOICE, AR_TRX_TP.CREDIT_MEMO, AR_TRX_TP.DEDUCTION, AR_TRX_TP.ON_ACCOUNT });
                S21SsmEZDResult arTrxBal =  NFDL0030Query.getInstance().getArTrxBalList(prmMap, bizMsg);
    
                if (arTrxBal.isCodeNormal()) {
                    List<Map<String, Object>> arTrxBalList = (List<Map<String, Object>>) arTrxBal.getResultObject();
                    String arTrxNum = (String) arTrxBalList.get(0).get("AR_TRX_NUM");
                    BigDecimal dealRmngBalGrsAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_RMNG_BAL_GRS_AMT");
                    BigDecimal arTrxBalPk = (BigDecimal) arTrxBalList.get(0).get("AR_TRX_BAL_PK");
                    String ezUpTime = (String) arTrxBalList.get(0).get("EZUPTIME");
                    String ezUpTimeZone = (String) arTrxBalList.get(0).get("EZUPTIMEZONE");
    
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealRmngBalGrsAmt_A1, dealRmngBalGrsAmt);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxBalPk_A1, arTrxBalPk);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTime_A1, ezUpTime);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTimeZone_A1, ezUpTimeZone);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).acctDt_A1, bizMsg.acctDt_H);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxNum_BK, arTrxNum);
    
                    BigDecimal dealApplyAdjRsvdAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_APPLY_ADJ_RSVD_AMT");
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealApplyAdjRsvdAmt_A1, dealApplyAdjRsvdAmt);
    
                    String arTrxTpCd = (String) arTrxBalList.get(0).get("AR_TRX_TP_CD");
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxTpCd_A1, arTrxTpCd);
    
                    if (bizMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealNetRcptAmt_A1, dealRmngBalGrsAmt);
                    }
    
                } else {
                    bizMsg.A.no(i).dealRmngBalGrsAmt_A1.clear();
                    bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, "NFDM0015E");
                    return;
                }
            }
        }
// END 2019/09/25 [QC#53481, ADD]

        if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            if (bizMsg.acctDt_H.getValue().compareTo(slsDt) > 0) {
                bizMsg.acctDt_H.setErrorInfo(1, "NFCM0040E");
                errFlg = true;
            } else if (!NFDL0030CommonLogic.checkGlDate(getGlobalCompanyCode(), bizMsg.acctDt_H.getValue())) {
                bizMsg.acctDt_H.setErrorInfo(1, "NFCM0045E");
                errFlg = true;
            }
        }

        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        if (DS_PMT_METH.CREDIT_CARD.equals(bizMsg.dsPmtMethCd_H.getValue())) {
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.crCardLastDigitNum_H)) {
                bizMsg.crCardTpNm_H.setErrorInfo(1, "NWBM0163E");
                bizMsg.crCardLastDigitNum_H.setErrorInfo(1, "NWBM0163E");
                bizMsg.crCardExprYrMth_H.setErrorInfo(1, "NWBM0163E");
                bizMsg.crCardHldNm_H.setErrorInfo(1, "NWBM0163E");
                // START 2018/05/15 E.Kameishi [QC#24833,ADD]
                bizMsg.crCardCustRefNum_H2.setErrorInfo(1, "NWBM0163E");
                // END 2018/05/15 E.Kameishi [QC#24833,ADD]
                errFlg = true;
            }
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        }
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            for (int j = i + 1; j < bizMsg.A.getValidCount(); j++) {
                if (bizMsg.A.no(i).arTrxNum_A1.getValue().equals(bizMsg.A.no(j).arTrxNum_A1.getValue())) {
                    bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, "NMZM0144E", new String[]{"Invoice#"});
                    bizMsg.A.no(j).arTrxNum_A1.setErrorInfo(1, "NMZM0144E", new String[]{"Invoice#"});
                    errFlg = true;
                }
            }
        }

        // START 2018/05/18 [QC#24882, ADD]
        if (!NFDL0030CommonLogic.checkCollector(getGlobalCompanyCode(), getUserProfileService(), bizMsg)) {
            errFlg = true;
        }
        // START 2018/06/06 [QC#24882-1, DEL]
        // if (!NFDL0030CommonLogic.checkCatcPsn(getGlobalCompanyCode(), bizMsg)) {
        //     errFlg = true;
        //}
        // END   2018/06/06 [QC#24882-1, DEL]
        // END   2018/05/18 [QC#24882, ADD]

        if (errFlg) {
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2023/03/10 S.Nakatani [QC#55645,MOD]
            // START 2020/03/03 [QC#55664, ADD]
//            if (AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
//                bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, "NFDM0052E");
//                errFlg = true;
//            }
            // END   2020/03/03 [QC#55664, ADD]
            if (!AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
            // END 2023/03/10 S.Nakatani [QC#55645,MOD]
                if (bizMsg.A.no(i).arTrxNum_A1.getValue().equals(bizMsg.A.no(i).arTrxNum_BK.getValue())) {
                    AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inMsg.arTrxBalPk, bizMsg.A.no(i).arTrxBalPk_A1);
                    AR_TRX_BALTMsg outMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                    if (outMsg == null) {
                        bizMsg.setMessageInfo("NFAM0004E");
                        return;
                    } else {
                        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo("NFAM0004E");
                            return;
                        }

                        if (bizMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealNetRcptAmt_A1, outMsg.dealRmngBalGrsAmt);
                        }
                    }
                } else {
                    bizMsg.A.no(i).arTrxNum_BK.clear();
                    bizMsg.A.no(i).ezUpTime_A1.clear();
                    bizMsg.A.no(i).ezUpTimeZone_A1.clear();

                    Map<String, Object> prmMap = new HashMap<String, Object>();
                    prmMap.put("glblCmpyCd", getGlobalCompanyCode());
                    prmMap.put("invNumList", new String[] {bizMsg.A.no(i).arTrxNum_A1.getValue() });
                    prmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
                    prmMap.put("arCashApplyStsCdList", new String[] {AR_CASH_APPLY_STS.UNAPPLIED, AR_CASH_APPLY_STS.PARTIAL });
                    // START 2018/06/23 [QC#26239, ADD]
                    prmMap.put("arTrxTpCdList", new String[] {AR_TRX_TP.INVOICE, AR_TRX_TP.CREDIT_MEMO, AR_TRX_TP.DEDUCTION, AR_TRX_TP.ON_ACCOUNT });
                    // END 2018/06/23 [QC#26239, ADD]
                    S21SsmEZDResult arTrxBal = NFDL0030Query.getInstance().getArTrxBalList(prmMap, bizMsg);

                    if (arTrxBal.isCodeNormal()) {
                        List<Map<String, Object>> arTrxBalList = (List<Map<String, Object>>) arTrxBal.getResultObject();
                        BigDecimal dealRmngBalGrsAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_RMNG_BAL_GRS_AMT");
                        BigDecimal dealApplyAdjRsvdAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_APPLY_ADJ_RSVD_AMT"); // Add
                                                                                                                          // 2018/01/16
                                                                                                                          // S21_NA#20563
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealRmngBalGrsAmt_A1, dealRmngBalGrsAmt);
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealApplyAdjRsvdAmt_A1, dealApplyAdjRsvdAmt); // Add
                                                                                                                    // 2018/01/16
                                                                                                                    // S21_NA#20563

                        // START 2018/06/15 [QC#26239, ADD]
                        String arTrxTpCd = (String) arTrxBalList.get(0).get("AR_TRX_TP_CD");
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxTpCd_A1, arTrxTpCd);
                        // END 2018/06/15 [QC#26239, ADD]

                        if (bizMsg.A.no(i).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealNetRcptAmt_A1, dealRmngBalGrsAmt);
                        }
                    } else {
                        bizMsg.A.no(i).dealRmngBalGrsAmt_A1.clear();
                        bizMsg.A.no(i).arTrxNum_A1.setErrorInfo(1, "NFDM0015E");
                        errFlg = true;
                    }
                }
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealRmngBalGrsAmt_A1)) {
                // START 2018/06/15 [QC#26239, MOD]
                // if (AR_TRX_TP.INVOICE.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                if (AR_TRX_TP.INVOICE.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())
                        || AR_TRX_TP.DEDUCTION.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                    // Invoice, Deduction
                    if (bizMsg.A.no(i).dealNetRcptAmt_A1.getValue().compareTo(bizMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue()) > 0) {
                        bizMsg.A.no(i).dealNetRcptAmt_A1.setErrorInfo(1, "NLAM0007E", new String[]{"Payment Amount", "Balance Amount"});
                        errFlg = true;
                    } else if (bizMsg.A.no(i).dealNetRcptAmt_A1.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                        bizMsg.A.no(i).dealNetRcptAmt_A1.setErrorInfo(1, "NWAM0712E", new String[]{"Payment Amount", "Zero"});
                        errFlg = true;
                    // Add Start 2018/01/16 S21_NA#20563
                    } else if (bizMsg.A.no(i).dealNetRcptAmt_A1.getValue().compareTo( //
                            bizMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue().subtract(bizMsg.A.no(i).dealApplyAdjRsvdAmt_A1.getValue())) > 0) {
                        bizMsg.A.no(i).dealNetRcptAmt_A1.setErrorInfo(1, NFCM0880E);
                        errFlg = true;
                    }
                    // Add End 2018/01/16 S21_NA#20563
                // START 2023/03/10 S.Nakatani [QC#55645,ADD]
                } else if (AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                    if (bizMsg.A.no(i).dealNetRcptAmt_A1.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                        bizMsg.A.no(i).dealNetRcptAmt_A1.setErrorInfo(1, "NWAM0712E", new String[]{"Payment Amount", "Zero"});
                        errFlg = true;
                    }
                // END 2023/03/10 S.Nakatani [QC#55645,ADD]
                } else {
                    // Credit Memo, On Account
                    if (bizMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue().compareTo(bizMsg.A.no(i).dealNetRcptAmt_A1.getValue()) > 0) {
                        bizMsg.A.no(i).dealNetRcptAmt_A1.setErrorInfo(1, "NLAM0007E", new String[]{"Payment Amount", "Balance Amount"});
                        errFlg = true;
                    } else if (bizMsg.A.no(i).dealNetRcptAmt_A1.getValue().compareTo(BigDecimal.ZERO) >= 0) {
                        bizMsg.A.no(i).dealNetRcptAmt_A1.setErrorInfo(1, "NFDM0050E", new String[]{"Payment Amount", "Zero"});
                        errFlg = true;
                    }
                }
                // END   2018/06/15 [QC#26239, MOD]
            }

            if (bizMsg.A.no(i).acctDt_A1.getValue().compareTo(slsDt) > 0) {
                bizMsg.A.no(i).acctDt_A1.setErrorInfo(1, "NFCM0040E");
                errFlg = true;
            } else if (!NFDL0030CommonLogic.checkGlDate(getGlobalCompanyCode(), bizMsg.A.no(i).acctDt_A1.getValue())) {
                bizMsg.A.no(i).acctDt_A1.setErrorInfo(1, "NFCM0045E");
                errFlg = true;
            }
        // START 2018/06/15 [QC#26239, ADD]
        }
        // END   2018/06/15 [QC#26239, ADD]

        if (errFlg) {
            return;
        }

        // START 2018/06/15 [QC#26239, ADD]
        BigDecimal sumDealNetRcptAmt = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealNetRcptAmt_A1.getValue())) {
                sumDealNetRcptAmt = sumDealNetRcptAmt.add(bizMsg.A.no(i).dealNetRcptAmt_A1.getValue());
            }
        }
        // START 2018/07/10 [QC#24882-2, ADD]
        if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            sumDealNetRcptAmt = bizMsg.dealNetRcptAmt_H.getValue();
        }
        // END   2018/07/10 [QC#24882-2, ADD]
        if (sumDealNetRcptAmt.compareTo(BigDecimal.ZERO) <= 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dealNetRcptAmt_H, sumDealNetRcptAmt);
            bizMsg.dealNetRcptAmt_H.setErrorInfo(1, "NWAM0712E", new String[]{ "Payment Amount", "Zero" });
            return;
        }
        // END   2018/06/15 [QC#26239, ADD]

        // START 2018/06/15 [QC#26239, ADD]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        // END   2018/06/15 [QC#26239, ADD]
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                continue;
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]

            AR_TRX_BALTMsg arTrxBalInMsg = new AR_TRX_BALTMsg();
            ZYPEZDItemValueSetter.setValue(arTrxBalInMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(arTrxBalInMsg.arTrxBalPk, bizMsg.A.no(i).arTrxBalPk_A1);

            AR_TRX_BALTMsg arTrxBalOutMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(arTrxBalInMsg);

            if (arTrxBalOutMsg == null) {
                bizMsg.setMessageInfo("NFCM0079E");
                return;
            } else if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A1.getValue(), bizMsg.A.no(i).ezUpTimeZone_A1.getValue(), arTrxBalOutMsg.ezUpTime.getValue(), arTrxBalOutMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NFCM0079E");
                return;
            }

            // START 2016/07/29 T.Tsuchida [QC#12600,MOD]
//            // Call Credit Card Validation API
//            NWZC203001PMsg pMsg = new NWZC203001PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC203001.PROC_MODE_SETTLE);
//            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, bizMsg.crCardCustRefNum_H);
//            ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, arTrxBalOutMsg.sellToCustCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, bizMsg.A.no(i).dealNetRcptAmt_A1);
//            ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.AR);
//            ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, arTrxBalOutMsg.arTrxNum);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcPrflOrdOvrdCd, "NO");
//            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, "1");
//            ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, arTrxBalOutMsg.arTrxNum);
//
//            new NWZC203001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//            if (pMsg.xxMsgIdList.getValidCount() > 0) {
//                bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//                return;
//            }
//            if (!NWZC203001.PMTC_PROC_STS_CD_0.equals(pMsg.xxPmtcProcStsCd.getValue())
//                    && !NWZC203001.PMTC_APVL_STS_NUM_1.equals(pMsg.xxPmtcApvlStsNum.getValue())) {
//                bizMsg.setMessageInfo("NWAM0482E");
//                return;
//            }
//
//            // START 2016/07/28 T.Tsuchida [QC#12604,ADD]
//            CR_CARD_TRXTMsg crCardTrxTMsg = getCrCardTrx(pMsg.crCardTrxPk.getValue());
//            if (crCardTrxTMsg == null) {
//                bizMsg.setMessageInfo("NFDM0012E", new String[] {"CR_CARD_TRX"} );
//                return;
//            }
//            ZYPEZDItemValueSetter.setValue(bizMsg.crCardApvlCd_H, crCardTrxTMsg.crCardAuthRefNum);
//            // END 2016/07/28 T.Tsuchida [QC#12604,ADD]
//
//       }
//
//       if (!createArRcpt(bizMsg)) {
//           return;
//       }

        }

        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        if (DS_PMT_METH.CREDIT_CARD.equals(bizMsg.dsPmtMethCd_H.getValue())) {
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (!createArRcpt(bizMsg, slsDt)) {
                return;
        // START 2023/03/10 S.Nakatani [QC#55645,MOD]
            }
        } else if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
            if (!createArRcptForECheck(bizMsg, slsDt)){
                return;
            }
        }
        // END 2023/03/10 S.Nakatani [QC#55645,MOD]
        // END 2016/07/29 T.Tsuchida [QC#12600,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_CMN_Submit================================END", this);
    }

    // START 2016/07/28 T.Tsuchida [QC#12604,ADD]
    private CR_CARD_TRXTMsg getCrCardTrx(BigDecimal crCardTrxPk) {
        CR_CARD_TRXTMsg inMsg = new CR_CARD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.crCardTrxPk, crCardTrxPk);
        return (CR_CARD_TRXTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2016/07/28 T.Tsuchida [QC#12604,ADD]

    // START 2016/07/29 T.Tsuchida [QC#12600,MOD]
    //private boolean createArRcpt(NFDL0030CMsg bizMsg) {
    private boolean createArRcpt(NFDL0030CMsg bizMsg, String slsDt) {
    // END 2016/07/29 T.Tsuchida [QC#12600,MOD]
        GLBL_CMPYTMsg glblCmpyInMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyInMsg.glblCmpyCd, getGlobalCompanyCode());
        GLBL_CMPYTMsg glblCmpyOutMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyInMsg);

        if (!RTNCD_NORMAL.equals(glblCmpyOutMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NLZM0095E");
            return false;
        }

        BigDecimal totDealNetRcptAmt = BigDecimal.ZERO;
        BigDecimal totRmngBalGrsAmt = BigDecimal.ZERO;
        if (bizMsg.A.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                totDealNetRcptAmt = totDealNetRcptAmt.add(bizMsg.A.no(i).dealNetRcptAmt_A1.getValue());
                totRmngBalGrsAmt = totRmngBalGrsAmt.add(bizMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue());
            }
        } else {
            totDealNetRcptAmt = bizMsg.dealNetRcptAmt_H.getValue();
            totRmngBalGrsAmt = bizMsg.dealNetRcptAmt_H.getValue();
        }

        AR_RCPTTMsg rcptUpdMsg = new AR_RCPTTMsg();
        String rcptNum = ZYPNumbering.getUniqueID(getGlobalCompanyCode(), AR_RCPT_AUTO_SEQ_KEY);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);

        String arRcptTocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY, getGlobalCompanyCode());
        String arRcptProdCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatNum, RCPT_BAT_NUM);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatSqNum, RCPT_BAT_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTrxTpCd, AR_RCPT_TRX_TP.REGULAR_RECEIPT);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTpCd, AR_RCPT_TP.CREDIT_CARD);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealCcyCd, glblCmpyOutMsg.stdCcyCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, totRmngBalGrsAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcCcyCd, glblCmpyOutMsg.stdCcyCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, totRmngBalGrsAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.tocCd, arRcptTocCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.coaProdCd, arRcptProdCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.voidFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rfExchRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cratMethTpCd, CREATE_METH_TP_CD_MANUAL);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exptFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.lastUpdUserId, getContextUserInfo().getUserId());
        // START 2018/09/20 T.Ogura [QC#28097,MOD]
//        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.CLEARED);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.UNAPPLIED);
        // END   2018/09/20 T.Ogura [QC#28097,MOD]
        // START 2018/05/15 E.Kameishi [QC#24833,MOD]
        // START 2016/07/29 T.Tsuchida [QC#12657,MOD]
        //ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptSrcCd, AR_RCPT_SRC.CREDIT_CARD);
        //ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptSrcCd, AR_RCPT_SRC.BANK_OF_AMERICA_LOCKBOX);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptSrcCd, AR_RCPT_SRC.CREDIT_CARD_PAYMENT);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptChkNum, S21StringUtil.concatStrings(AR_RCPT_CHK_NUM_HDR, ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM)));
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRefTxt, bizMsg.crCardCustRefNum_H2);
        // START 2016/07/29 T.Tsuchida [QC#12657,MOD]
        // EMD 2018/05/15 E.Kameishi [QC#24833,MOD]
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRemDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cashAppDt, bizMsg.acctDt_H);
        // START 2024/03/01 [QC#63456, MOD]
        boolean invFlg = isExistInvoice(bizMsg);
        if ((bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT))
                || (bizMsg.A.getValidCount() > 0 && !invFlg)) {
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
        }
        // END 2024/03/01 [QC#63456, MOD]
        // START 2016/07/29 T.Tsuchida [QC#12600,MOD]
        //// START 2016/07/28 T.Tsuchida [QC#12604,MOD]
        ////ZYPEZDItemValueSetter.setValue(rcptUpdMsg.crCardApvlCd, bizMsg.crCardCustRefNum_H);
        //ZYPEZDItemValueSetter.setValue(rcptUpdMsg.crCardApvlCd, bizMsg.crCardApvlCd_H);
        //// END 2016/07/28 T.Tsuchida [QC#12604,MOD]

//        // START 2020/05/19 [QC#56555, MOD]
        // START 2020/05/22 [QC#56866, MOD]
        // Call Credit Card Validation API
        NWZC203001PMsg pMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC203001.PROC_MODE_SETTLE);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, bizMsg.crCardCustRefNum_H);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.AR);
        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, rcptNum);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcPrflOrdOvrdCd, "NO");
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, "1");
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, rcptNum);

        boolean workFlg = false;
        int totalCnt = 0;
        BigDecimal totCrCardAuthTaxAmt = BigDecimal.ZERO;
        BigDecimal totCrCardAuthFrtAmt = BigDecimal.ZERO;
        BigDecimal totCrCardAuthDiscAmt = BigDecimal.ZERO;
        BigDecimal totCrCardAuthLineItemCnt = BigDecimal.ZERO;
        // QC#56866 Mod Start
        BigDecimal constNum = ZYPCodeDataUtil.getNumConstValue("PAYEEZY_LINE_ITEM_LIMIT_NUM", getGlobalCompanyCode());
        List<String> arTrxNumList = new ArrayList<String>();
        List<String> invLst = new ArrayList<String>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            arTrxNumList.add(bizMsg.A.no(i).arTrxNum_A1.getValue());
        }

        // QC56866-1 Mod Start
        if (!arTrxNumList.isEmpty()) {

            S21SsmEZDResult invInfo =  NFDL0030Query.getInstance().getInvInfo(getGlobalCompanyCode(), arTrxNumList, constNum);
            List<Map<String, Object>> invInfoList = (List<Map<String, Object>>) invInfo.getResultObject();
            if (invInfo.isCodeNormal()) {
    //            if (i == 0) {
                workFlg = true;
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthPostCd, (String)invInfoList.get(0).get("SHIP_TO_POST_CD"));              // INV_BOL.SHIP_TO_POST_CD
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAddr, (String)invInfoList.get(0).get("ADDR"));                           // INV_BOL.SHIP_TO_FIRST_LINE_ADDR - NV_BOL.SHIP_TO_FRTH_LINE_ADDR
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthCtyAddr, (String)invInfoList.get(0).get("SHIP_TO_CTY_ADDR"));            // INV_BOL.SHIP_TO_CTY_ADDR
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthStCd, (String)invInfoList.get(0).get("SHIP_TO_ST_CD"));                  // INV_BOL.SHIP_TO_ST_CD
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthCtryCd, (String)invInfoList.get(0).get("SHIP_TO_CTRY_CD"));              // INV_BOL.SHIP_TO_CTRY_CD
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthFromZipCd, (String)invInfoList.get(0).get("POST_CD"));                   // INV_BOL.SHIP_FROM_INVTY_LOC_CD -> SHIP_TO_CUST.POST_CD
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTrxNum, rcptNum);
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthDtyAmt, DUTY_AMOUNT);
                //ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAltTaxId, null);                                                         // NULL
                ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAltTaxAmt, ALT_TAX_AMOUNT);

                Map<String, Object> dsCardInfo =  (Map<String, Object>) NFDL0030Query.getInstance().getDsCardInfo(getGlobalCompanyCode(), bizMsg.crCardCustRefNum_H.getValue()).getResultObject();
                // Credit Card Validation API Data Set
                if (dsCardInfo != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthHldNm, createName((String)dsCardInfo.get("CR_CARD_HLD_NM")));        // DS_CR_CARD.CR_CARD_HLD_NM
                }

                for (int j = 0; j < invInfoList.size(); j++) {

                    if (j == 0) {
                        totCrCardAuthLineItemCnt = totCrCardAuthLineItemCnt.add((BigDecimal)invInfoList.get(j).get("CNT"));                // INV_LINE Count
                    }

                    if (!invLst.contains((String)invInfoList.get(j).get("INV_NUM"))) {
                        totCrCardAuthFrtAmt = totCrCardAuthFrtAmt.add((BigDecimal)invInfoList.get(j).get("INV_TOT_DEAL_FRT_AMT"));         // INV.INV_TOT_DEAL_FRT_AMT
                        totCrCardAuthDiscAmt = totCrCardAuthDiscAmt.add((BigDecimal)invInfoList.get(j).get("INV_TOT_DEAL_DISC_AMT"));      // INV.INV_TOT_DEAL_DISC_AMT
    //                    totCrCardAuthLineItemCnt = totCrCardAuthLineItemCnt.add((BigDecimal)invInfoList.get(j).get("CNT"));                // INV_LINE Count
                        totCrCardAuthTaxAmt = totCrCardAuthTaxAmt.add((BigDecimal)invInfoList.get(j).get("INV_TOT_DEAL_TAX_AMT"));         // INV.INV_TOT_DEAL_TAX_AMT
                        invLst.add((String)invInfoList.get(j).get("INV_NUM"));
                    }

                    // QC#60127 Add
                    if (BigDecimal.ZERO.compareTo((BigDecimal) invInfoList.get(j).get("INV_LINE_DEAL_NET_AMT")) >= 0) {
                        continue;
                    }

                    NWZC203001_APMsg apMsg = pMsg.A.no(totalCnt);

                    ZYPEZDItemValueSetter.setValue(apMsg.crCardTrxDtlLineNum, String.valueOf(j));                                           // Serial No
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthMdseNm, (String)invInfoList.get(j).get("MDSE_NM"));                      // INV_LINE.MDSE_NM
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthProdCd, (String)invInfoList.get(j).get("UPC_CD"));                       // INV_LINE.MDSE_CD -> MDSE.UPC_CD
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthOrdQty, (BigDecimal)invInfoList.get(j).get("ORD_QTY"));                  // INV_LINE.ORD_QTY
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthUomCd, UNIT_OF_MEASURE);
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlTaxAmt, (BigDecimal)invInfoList.get(j).get("INV_LINE_DEAL_TAX_AMT")); // INV_LINE.INV_LINE_DEAL_TAX_AMT
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlTaxPct, (BigDecimal)invInfoList.get(j).get("TAX_PCT"));               // INV_LINE.TAX_PCT
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlAmt, (BigDecimal)invInfoList.get(j).get("INV_LINE_DEAL_NET_AMT"));    // INV_LINE.INV_LINE_DEAL_NET_AMT
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDtlDiscAmt, DISCOUNT_AMOUNT);
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthCmdtyCd, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_L_TICKET_TRN_COMMONDITY_CD, getGlobalCompanyCode()));  // L_TICKET_TRN_COMMONDITY_CD
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthUnitPrcAmt, (BigDecimal)invInfoList.get(j).get("DEAL_NET_UNIT_PRC_AMT")); // INV_LINE.DEAL_NET_UNIT_PRC_AMT
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthGrsNetInd, GROSS_NET_INDICATOR);
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthTaxTpCd, TAX_TYPE);
                    ZYPEZDItemValueSetter.setValue(apMsg.crCardAuthDiscInd, DISCOUNT_INDICATOR);

                    totalCnt = totalCnt + 1;
                    if (totalCnt > pMsg.A.length()) {
                        bizMsg.setMessageInfo("NFBM0105E", new String[]{"Target data", String.valueOf(pMsg.A.length())});
                        return false;
                    }
                    // QC#56866 Add
                    if (ZYPCommonFunc.hasValue(constNum) && totalCnt == constNum.intValue()) {
                        break;
                    }
                    // QC#56866 End
                }
            }
            // QC#56866 Mod End
        }
        // QC56866-1 Mod End

        if (workFlg) {
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, totCrCardAuthTaxAmt);           // INV.INV_TOT_DEAL_TAX_AMT
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthFrtAmt, totCrCardAuthFrtAmt);           // INV.INV_TOT_DEAL_FRT_AMT
            // QC#60127 Modify
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthDiscAmt, totCrCardAuthDiscAmt.abs());         // INV.INV_TOT_DEAL_DISC_AMT
            ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthLineItemCnt, totCrCardAuthLineItemCnt); // INV_LINE Count
            pMsg.A.setValidCount(totalCnt);
        }

        new NWZC203001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }
        if (!NWZC203001.PMTC_PROC_STS_CD_0.equals(pMsg.xxPmtcProcStsCd.getValue())
                && !NWZC203001.PMTC_APVL_STS_NUM_1.equals(pMsg.xxPmtcApvlStsNum.getValue())) {
            bizMsg.setMessageInfo("NWAM0482E");
            return false;
        }

        CR_CARD_TRXTMsg crCardTrxTMsg = getCrCardTrx(pMsg.crCardTrxPk.getValue());
        if (crCardTrxTMsg == null) {
            bizMsg.setMessageInfo("NFDM0012E", new String[] {"CR_CARD_TRX"} );
            return false;
        }
        // END   2020/05/22 [QC#56866, MOD]

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.crCardApvlCd, crCardTrxTMsg.crCardAuthRefNum);
        // END 2016/07/29 T.Tsuchida [QC#12600,MOD]

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.payerCustCd, bizMsg.dsAcctNum_H);

        EZDTBLAccessor.create(rcptUpdMsg);

        if (!RTNCD_NORMAL.equals(rcptUpdMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
//        // END 2020/05/19 [QC#56555, MOD]
        if (!createArRcptDtl(bizMsg, rcptUpdMsg)) {
            return false;
        }

        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        if (!createArTrxBal(bizMsg, arTrxBalTMsg, rcptUpdMsg)) { 
            return false;
        }

        // Start 2022/11/30 [QC#60877, ADD]
        EZDConnectionMgr.getInstance().commit();
        // END 2022/11/30 [QC#60877, ADD]
        // START 2024/03/01 [QC#63456, ADD]
        if (!createArApplyIntfcWrkForRcpt(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
            return false;
        }
        // END 2024/03/01 [QC#63456, ADD]
        if (bizMsg.A.getValidCount() > 0) {
            // START 2024/03/01 [QC#63456, MOD]
            if (invFlg) {
            //if (!createArApplyIntfcWrkForRcpt(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
            //    return false;
            //}
                if (!createArApplyIntfcWrkForCashApp(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
                    return false;
                }
            }
            // END 2024/03/01 [QC#63456, MOD]
        }

        // START 2018/05/18 [QC#24882, ADD]
        Map<String, Object>confLtrInfo = sendConfirmationLetter(bizMsg, rcptNum);
        // END   2018/05/18 [QC#24882, ADD]

        // START 2018/04/18 [QC#20940, ADD]
        // START 2018/05/18 [QC#24882, MOD]
        // createCltNoteDtl(bizMsg, rcptNum, totDealNetRcptAmt);
        // START 2018/06/04 [QC#20940-1, MOD]
        // createCltNoteDtl(bizMsg, rcptNum, totDealNetRcptAmt, confLtrInfo);
        String rcptChkNum = rcptUpdMsg.rcptChkNum.getValue();
        createCltNoteDtl(bizMsg, rcptChkNum, totDealNetRcptAmt, confLtrInfo);
        // END   2018/06/04 [QC#20940-1, MOD]
        // END   2018/05/18 [QC#24882, MOD]
        // END   2018/04/18 [QC#20940, ADD]

//        // START 2020/05/19 [QC#5655, MOD]
//        // Call Credit Card Validation API
//        NWZC203001PMsg pMsg = new NWZC203001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC203001.PROC_MODE_SETTLE);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, bizMsg.crCardCustRefNum_H);
//        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, bizMsg.dsAcctNum_H);
//        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, totDealNetRcptAmt);
//        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.AR);
//        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, rcptNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcPrflOrdOvrdCd, "NO");
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTaxIndNum, "1");
//        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcOrdId, rcptNum);
//
//        new NWZC203001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//        if (pMsg.xxMsgIdList.getValidCount() > 0) {
//            bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
//            return false;
//        }
//        if (!NWZC203001.PMTC_PROC_STS_CD_0.equals(pMsg.xxPmtcProcStsCd.getValue())
//                && !NWZC203001.PMTC_APVL_STS_NUM_1.equals(pMsg.xxPmtcApvlStsNum.getValue())) {
//            bizMsg.setMessageInfo("NWAM0482E");
//            return false;
//        }
//
//        CR_CARD_TRXTMsg crCardTrxTMsg = getCrCardTrx(pMsg.crCardTrxPk.getValue());
//        if (crCardTrxTMsg == null) {
//            bizMsg.setMessageInfo("NFDM0012E", new String[] {"CR_CARD_TRX"} );
//            return false;
//        }
//        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.crCardApvlCd, crCardTrxTMsg.crCardAuthRefNum);
//
//        EZDTBLAccessor.create(rcptUpdMsg);
//
//        if (!RTNCD_NORMAL.equals(rcptUpdMsg.getReturnCode())) {
//            bizMsg.setMessageInfo("NFCM0032E");
//            return false;
//        }
//        // END   2020/05/19 [QC#56555, MOD]

        return true;
    }

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    private boolean createArRcptForECheck(NFDL0030CMsg bizMsg, String slsDt) {
        GLBL_CMPYTMsg glblCmpyInMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyInMsg.glblCmpyCd, getGlobalCompanyCode());
        GLBL_CMPYTMsg glblCmpyOutMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyInMsg);

        if (!RTNCD_NORMAL.equals(glblCmpyOutMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NLZM0095E");
            return false;
        }

        BigDecimal bankAcctPk = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(bizMsg.dsCustBankAcctRelnPk_H)) {
            S21SsmEZDResult ssmResult = NFDL0030Query.getInstance().getBankAcctPk(getGlobalCompanyCode(), bizMsg.dsCustBankAcctRelnPk_H.getValue());
            if (!ssmResult.isCodeNormal()) {
                bizMsg.setMessageInfo("NFCM0041E");
                return false;
            }
            bankAcctPk = (BigDecimal) ssmResult.getResultObject();
        }

        BigDecimal totDealNetRcptAmt = BigDecimal.ZERO;
        BigDecimal totRmngBalGrsAmt = BigDecimal.ZERO;
        if (bizMsg.A.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                totDealNetRcptAmt = totDealNetRcptAmt.add(bizMsg.A.no(i).dealNetRcptAmt_A1.getValue());
                totRmngBalGrsAmt = totRmngBalGrsAmt.add(bizMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue());
            }
        } else {
            totDealNetRcptAmt = bizMsg.dealNetRcptAmt_H.getValue();
            totRmngBalGrsAmt = bizMsg.dealNetRcptAmt_H.getValue();
        }

        NWZC229001PMsg pMsg = new NWZC229001PMsg();
        // Call ECheck API
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.bankRteNum, bizMsg.bankRteNum_H);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBankAcctNum, bizMsg.dsBankAcctNum_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCustBankAcctRelnPk, bizMsg.dsCustBankAcctRelnPk_H);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(pMsg.echkPmtAmt, totDealNetRcptAmt);
        if (bizMsg.A.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(i).invNum, bizMsg.A.no(i).arTrxNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(i).invTotDealNetAmt, bizMsg.A.no(i).dealNetRcptAmt_A1);
            }
            pMsg.InvList.setValidCount(bizMsg.A.getValidCount());
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(0).invNum, INV_NUM_ON_ACCOUNT);
            ZYPEZDItemValueSetter.setValue(pMsg.InvList.no(0).invTotDealNetAmt, totDealNetRcptAmt);
            pMsg.InvList.setValidCount(1);
        }

        new NWZC229001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            if (!ZYPCodeDataUtil.getVarCharConstValue("ECHK_APVL_STS_APPROVED", pMsg.glblCmpyCd.getValue()).equals(pMsg.echkHostRspStsCd.getValue())) {
                bizMsg.setMessageInfo(NWZM2316E, new String[] {pMsg.xxMsgIdList.no(0).xxMsgTxt.getValue() });
            } else {
                bizMsg.setMessageInfo(NFDM0060E);
            }
            return false;
        }

        AR_RCPTTMsg rcptUpdMsg = new AR_RCPTTMsg();
        String rcptNum = ZYPNumbering.getUniqueID(getGlobalCompanyCode(), AR_RCPT_AUTO_SEQ_KEY);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);

        String arRcptTocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY, getGlobalCompanyCode());
        String arRcptProdCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatNum, RCPT_BAT_NUM);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatSqNum, RCPT_BAT_SQ_NUM);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTrxTpCd, AR_RCPT_TRX_TP.REGULAR_RECEIPT);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTpCd, AR_RCPT_TP.ACH);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealCcyCd, glblCmpyOutMsg.stdCcyCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, totRmngBalGrsAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcCcyCd, glblCmpyOutMsg.stdCcyCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, totRmngBalGrsAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.tocCd, arRcptTocCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.coaProdCd, arRcptProdCd);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.voidFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rfExchRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cratMethTpCd, CREATE_METH_TP_CD_MANUAL);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exptFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.lastUpdUserId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.UNAPPLIED);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptSrcCd, AR_RCPT_SRC.CHECK_BY_PHONE);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptChkNum, S21StringUtil.concatStrings(AR_RCPT_E_CHK_NUM_HDR, pMsg.echkPmtReqId.getValue()));
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arBatRcptNm, S21StringUtil.concatStrings(AR_BAT_RCPT_E_CHK_NM_HDR, bizMsg.dsAcctNum_H.getValue(), "_", ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss")));
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRefTxt, bizMsg.crCardCustRefNum_H2);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptRemDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cashAppDt, bizMsg.acctDt_H);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.custDsBankAcctPk, bankAcctPk);
        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptCmntTxt, S21StringUtil.concatStrings(AR_RCPT_E_CHK_CMNT_TXT_HDR, " ", ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy"), " ", getContextUserInfo().getUserId()));

        // START 2023/11/29 TZ.Win [QC#62089,ADD]
        boolean invFlg = isExistInvoice(bizMsg);
        if ((bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) || 
                (bizMsg.A.getValidCount() > 0 && !invFlg)) {
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
        }
        // END 2023/11/29 TZ.Win [QC#62089,ADD]

        AR_BAT_RCPTTMsg batRcptUpdMsg = new AR_BAT_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.arBatRcptNm, S21StringUtil.concatStrings(AR_BAT_RCPT_E_CHK_NM_HDR, bizMsg.dsAcctNum_H.getValue(), "_", ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss")));
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.arRcptSrcCd, AR_RCPT_SRC.CHECK_BY_PHONE);
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.arBatRcptDt, slsDt);
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.arBatRcptCnt, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.arBatRcptAmt, totDealNetRcptAmt);
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.arBatRcptStsCd, AR_BAT_RCPT_STS.CLOSED);
        ZYPEZDItemValueSetter.setValue(batRcptUpdMsg.custDsBankAcctPk, bankAcctPk);

        EZDConnectionMgr.getInstance().commit();

        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.payerCustCd, bizMsg.dsAcctNum_H);

        EZDTBLAccessor.create(rcptUpdMsg);
        EZDTBLAccessor.create(batRcptUpdMsg);

        if (!RTNCD_NORMAL.equals(rcptUpdMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }

        if (!createArRcptDtl(bizMsg, rcptUpdMsg)) {
            return false;
        }

        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        if (!createArTrxBal(bizMsg, arTrxBalTMsg, rcptUpdMsg)) {
            return false;
        }

        // Start 2022/11/30 [QC#60877, ADD]
        EZDConnectionMgr.getInstance().commit();
        // END 2022/11/30 [QC#60877, ADD]

        // START 2023/11/29 TZ.Win [QC#62089,ADD]
        if (!createArApplyIntfcWrkForRcpt(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
            return false;
        }
        // END 2023/11/29 TZ.Win [QC#62089,ADD]

        if (bizMsg.A.getValidCount() > 0) {
            // START 2023/11/29 TZ.Win [QC#62089,MOD]
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            //if (!INV_NUM_ON_ACCOUNT_ONLY.equals(bizMsg.A.getValidCount()) && !AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(0).arTrxTpCd_A1.getValue())) {
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            if(invFlg) {
                //if (!createArApplyIntfcWrkForRcpt(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
                //    return false;
                //}
            // END 2023/11/29 TZ.Win [QC#62089,MOD]
                if (!createArApplyIntfcWrkForCashApp(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
                    return false;
                }
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        }

        updateEchkRcptTrx(bizMsg, pMsg.echkPmtReqId.getValue());

        Map<String, Object> confLtrInfo = sendConfirmationLetter(bizMsg, rcptNum);

        String rcptChkNum = rcptUpdMsg.rcptChkNum.getValue();
        createCltNoteDtl(bizMsg, rcptChkNum, totDealNetRcptAmt, confLtrInfo);

        return true;
    }
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]

    /**
     * Create AR Receipt Detail
     * @param bizMsg NFDL0030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean createArRcptDtl(NFDL0030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {
        if (bizMsg.A.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                AR_RCPT_DTLTMsg inMsg = new AR_RCPT_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, arRcptTMsg.rcptNum);
                ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 4, "0"));
                AR_RCPT_DTLTMsg outMsg = (AR_RCPT_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

                if (outMsg != null) {
                    bizMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(inMsg.invNum, bizMsg.A.no(i).arTrxNum_A1);
                ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, bizMsg.A.no(i).arTrxNum_A1);
                ZYPEZDItemValueSetter.setValue(inMsg.arCustRefTpCd, AR_CUST_REF_TP.INVOICE_NUMBER);
                ZYPEZDItemValueSetter.setValue(inMsg.dealRcptDtlAmt, bizMsg.A.no(i).dealNetRcptAmt_A1);
                ZYPEZDItemValueSetter.setValue(inMsg.funcRcptDtlAmt, bizMsg.A.no(i).dealNetRcptAmt_A1);
                ZYPEZDItemValueSetter.setValue(inMsg.rcvTrxTpCd, AR_CUST_REF_TP.INVOICE_NUMBER);
                ZYPEZDItemValueSetter.setValue(inMsg.rcvTrxNum, bizMsg.A.no(i).arTrxNum_A1);
                ZYPEZDItemValueSetter.setValue(inMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
                EZDTBLAccessor.create(inMsg);
                if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0032E");
                    return false;
                }
            }
        } else {
            AR_RCPT_DTLTMsg inMsg = new AR_RCPT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, arRcptTMsg.rcptNum);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, RCPT_DTL_NUM);
            AR_RCPT_DTLTMsg outMsg = (AR_RCPT_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

            if (outMsg != null) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return false;
            }

            ZYPEZDItemValueSetter.setValue(inMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
            // START 2023/12/27 TZ.Win [QC#62089,MOD]
//            ZYPEZDItemValueSetter.setValue(inMsg.dealRcptDtlAmt, arRcptTMsg.dealRcptAmt);
//            ZYPEZDItemValueSetter.setValue(inMsg.funcRcptDtlAmt, arRcptTMsg.funcRcptAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.dealRcptDtlAmt, arRcptTMsg.dealNetRcptAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.funcRcptDtlAmt, arRcptTMsg.funcNetRcptAmt);
            // END 2023/12/27 TZ.Win [QC#62089,MOD]
            EZDTBLAccessor.create(inMsg);
            if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
        }
        return true;
    }

    /**
     * Create AR Transaction Balance
     * @param bizMsg NFDL0030CMsg
     * @param inMsg AR_TRX_BALTMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean createArTrxBal(NFDL0030CMsg bizMsg, AR_TRX_BALTMsg inMsg, AR_RCPTTMsg arRcptTMsg) {
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, arRcptTMsg.rcptNum);

        BigDecimal arTrxBalPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_TRX_BAL_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, arRcptTMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, arRcptTMsg.dealRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, arRcptTMsg.dealRcptRmngBalAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.dealNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.funcCcyCd, arRcptTMsg.funcCcyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, arRcptTMsg.funcRcptAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, arRcptTMsg.funcRcptRmngBalAmt);
        ZYPEZDItemValueSetter.setValue(inMsg.funcNetSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxDt, arRcptTMsg.rcptDt);
        ZYPEZDItemValueSetter.setValue(inMsg.glDt, arRcptTMsg.glDt);
        ZYPEZDItemValueSetter.setValue(inMsg.cashAppDt, arRcptTMsg.cashAppDt);
        // START 2016/09/28 K.Kojima [QC#11021,DEL]
        // ZYPEZDItemValueSetter.setValue(inMsg.billToCustCd,
        // arRcptTMsg.payerCustCd);
        // END 2016/09/28 K.Kojima [QC#11021,DEL]
        ZYPEZDItemValueSetter.setValue(inMsg.sellToCustCd, arRcptTMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, arRcptTMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, arRcptTMsg.tocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, arRcptTMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(inMsg.exptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.billToCustAcctCd, arRcptTMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);

        // START 2018/05/15 E.Kameishi [QC#24833,ADD]
        ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, arRcptTMsg.rcptChkNum);
        // END 2018/05/15 E.Kameishi [QC#24833,ADD]

        // START 2023/11/29 TZ.Win [QC#62089,ADD]
        // START 2024/03/01 [QC#63456, DEL]
        // if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
        // END 2024/03/01 [QC#63456, DEL]
        boolean invFlg = isExistInvoice(bizMsg);
        if ((bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) ||
                (bizMsg.A.getValidCount() > 0 && !invFlg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, BigDecimal.ZERO);
        }
        // START 2024/03/01 [QC#63456, DEL]
        // }
        // END 2024/03/01 [QC#63456, DEL]
        // END 2023/11/29 TZ.Win [QC#62089,ADD]

        EZDTBLAccessor.create(inMsg);
        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }

    /**
     * Create AR Apply Interface Work
     * @param bizMsg NFDL0030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @param arTrxBalTMsg AR_TRX_BALTMsg
     * @return boolean
     */
    public boolean createArApplyIntfcWrkForRcpt(NFDL0030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg, AR_TRX_BALTMsg arTrxBalTMsg) {
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
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, arRcptTMsg.dealCcyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        // START 2023/11/29 TZ.Win [QC#62089,ADD]
        // START 2024/03/01 [QC#63456, DEL]
        // if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
        // END 2024/03/01 [QC#63456, DEL]
        boolean invFlg = isExistInvoice(bizMsg);

        if ((bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) ||
                (bizMsg.A.getValidCount() > 0 && !invFlg)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, arRcptTMsg.dealNetRcptAmt.getValue());
        }
        // START 2024/03/01 [QC#63456, DEL]
        // }
        // END 2024/03/01 [QC#63456, DEL]
        // END 2023/11/29 TZ.Win [QC#62089,ADD]

        EZDTBLAccessor.create(inMsg);

        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }

        // Start 2022/11/30 [QC#60877, ADD]
        EZDConnectionMgr.getInstance().commit();
        // END 2022/11/30 [QC#60877, ADD]

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
     * Create AR Apply Interface Work
     * @param bizMsg NFDL0030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @param arTrxBalTMsg AR_TRX_BALTMsg
     * @return boolean
     */
    public boolean createArApplyIntfcWrkForCashApp(NFDL0030CMsg bizMsg, AR_RCPTTMsg arRcptTMsg, AR_TRX_BALTMsg arTrxBalTMsg) {

        int i = 0;
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        String userId = getContextUserInfo().getUserId();
        if (!ZYPCommonFunc.hasValue(currentSystemTime)) {
            bizMsg.setMessageInfo("NFCM0041E");
        }
        String applyGrpKey = userId.concat(currentSystemTime);
        BigDecimal applyGrpSubPk = BigDecimal.ONE;

        do {
            AR_APPLY_INTFC_WRKTMsg inMsg = new AR_APPLY_INTFC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.applyGrpKey, applyGrpKey);
            ZYPEZDItemValueSetter.setValue(inMsg.applyGrpSubPk, applyGrpSubPk);
            ZYPEZDItemValueSetter.setValue(inMsg.bizAppId, "NFCL0610");
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
            ZYPEZDItemValueSetter.setValue(inMsg.cashAppGlDt, arRcptTMsg.glDt);
            ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, arRcptTMsg.payerCustCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalPk, arTrxBalTMsg.arTrxBalPk);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrLastUpdTs, arTrxBalTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrTz, arRcptTMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.crTrxBalPk, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, arRcptTMsg.dealCcyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.cashDiscPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.dealCashDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())) {
                i++;
                continue;
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (bizMsg.xxModeCd.getValue().equals(MODE_INVOICE)) {
                // START 2016/07/21 K.Kojima [QC#9829,DEL]
                // ZYPEZDItemValueSetter.setValue(inMsg.invNum,
                // arTrxBalTMsg.arTrxNum);
                // ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd,
                // arTrxBalTMsg.arTrxTpCd);
                // ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk,
                // arTrxBalTMsg.arTrxBalPk);
                // ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalLastUpdTs,
                // arTrxBalTMsg.ezUpTime);
                // ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalTz,
                // arTrxBalTMsg.ezUpTimeZone);
                // END 2016/07/21 K.Kojima [QC#9829,DEL]

                // START 2016/07/21 K.Kojima [QC#9829,ADD]
                AR_TRX_BALTMsg arTrxBalInMsg = new AR_TRX_BALTMsg();
                ZYPEZDItemValueSetter.setValue(arTrxBalInMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(arTrxBalInMsg.arTrxBalPk, bizMsg.A.no(i).arTrxBalPk_A1);
                AR_TRX_BALTMsg arTrxBalOutMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(arTrxBalInMsg);

                ZYPEZDItemValueSetter.setValue(inMsg.invNum, arTrxBalOutMsg.arTrxNum);
                ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, arTrxBalOutMsg.arTrxTpCd);
                ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, arTrxBalOutMsg.arTrxBalPk);
                ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalLastUpdTs, arTrxBalOutMsg.ezUpTime);
                ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalTz, arTrxBalOutMsg.ezUpTimeZone);
                // END 2016/07/21 K.Kojima [QC#9829,ADD]

                ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, bizMsg.A.no(i).dealNetRcptAmt_A1);
                ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
                // START 2016/07/21 K.Kojima [QC#9829,DEL]
                // ZYPEZDItemValueSetter.setValue(inMsg.tocCd,
                // arTrxBalOutMsg.tocCd);
                // ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd,
                // arTrxBalOutMsg.coaProdCd);
                // END 2016/07/21 K.Kojima [QC#9829,DEL]
            } else {
                ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, arRcptTMsg.dealRcptAmt);
                ZYPEZDItemValueSetter.setValue(inMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ADJUSTMENT);
                ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, arRcptTMsg.dealRcptAmt);
            }

            EZDTBLAccessor.create(inMsg);

            if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }

            i++;
            applyGrpSubPk = applyGrpSubPk.add(BigDecimal.ONE);
        } while (bizMsg.xxModeCd.getValue().equals(MODE_INVOICE) && i < bizMsg.A.getValidCount());

        // Start 2022/11/30 [QC#60877, ADD]
        EZDConnectionMgr.getInstance().commit();
        // END 2022/11/30 [QC#60877, ADD]

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
        // START 2017/12/13 E.Kameishi [QC#22096, ADD]
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//            if (MODE_INVOICE.equals(bizMsg.xxModeCd.getValue())) {
            if (MODE_INVOICE.equals(bizMsg.xxModeCd.getValue()) && !AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(j).arTrxTpCd_A1.getValue())) {
            // END 2023/03/10 S.Nakatani [QC#55645,MOD]
                if (!callCrPrflUpdtAPI(bizMsg, bizMsg.A.no(j).arTrxBalPk_A1.getValue())){
                    return false;
                }
            }
        }
        // END 2017/12/13 E.Kameishi [QC#22096, ADD]
        return true;
    }
    // START 2017/12/13 E.Kameishi [QC#22096, ADD]
    public boolean callCrPrflUpdtAPI(NFDL0030CMsg bizMsg, BigDecimal arTrxBalPk) {
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();

        AR_TRX_BALTMsg arTrxBal = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(arTrxBal.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(arTrxBal.arTrxBalPk, arTrxBalPk);
        arTrxBal = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKeyForUpdate(arTrxBal);

        // START 2020/01/30 H.Mizukami [QC#55528, MOD]
        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(arTrxBal.billToCustCd)) {
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
            ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, arTrxBal.billToCustCd);
        } else {
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
            ZYPEZDItemValueSetter.setValue(paramMsg.sellToCustCd, arTrxBal.billToCustAcctCd);
        }
        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, ZYPDateUtil.getSalesDate());
        // END   2020/01/30 H.Mizukami [QC#55528, MOD]

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                bizMsg.setMessageInfo(msgId);
                return false;
            }
        }
        return true;
    }
    // END 2017/12/13 E.Kameishi [QC#22096, ADD]

    // START 2018/04/18 [QC#20940, ADD]
    private void createCltNoteDtl(NFDL0030CMsg bizMsg, String rcptChkNum, BigDecimal totDealNetRcptAmt, Map<String, Object>confLtrInfo) {

        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, getGlobalCompanyCode());
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        if (DS_PMT_METH.CREDIT_CARD.equals(bizMsg.dsPmtMethCd_H.getValue())) {
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.CREDIT_CARD_PAYMENT);
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        } else if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.CHECK_BY_PHONE_PAYMENT);
        }
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);

        String createdBy = getContextUserInfo().getFirstName() + " " + getContextUserInfo().getLastName();
        // START 2018/05/23 [QC#24882, ADD]
        String ctacToNm = (String) confLtrInfo.get(CTAC_TO_NM);
        String ctacToMlAddr = (String) confLtrInfo.get(CTAC_TO_ML_ADDR);
        // END   2018/05/23 [QC#24882, ADD]

        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NFDL0030_CLT_DTL_NOTE_TXT, getGlobalCompanyCode());
        // START 2018/05/23 [QC#24882, MOD]
        // String dtlNoteTxt = String.format(dtlNoteTxtTmpl, bizMsg.dtlNoteTxt_H.getValue(), rcptChkNum, totDealNetRcptAmt, createdBy);
        String dtlNoteTxt = String.format(dtlNoteTxtTmpl, bizMsg.dtlNoteTxt_H.getValue(), rcptChkNum, totDealNetRcptAmt, createdBy, ctacToNm, ctacToMlAddr);
        // END   2018/05/23 [QC#24882, MOD]
        if (dtlNoteTxt.length() > MAX_LENGTH_CLT_DTL_NOTE_TXT) {
            dtlNoteTxt = dtlNoteTxt.substring(0, MAX_LENGTH_CLT_DTL_NOTE_TXT);
        }

        CLT_NOTE_DTLTMsg cltNoteDtlMsg = new CLT_NOTE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctCd, bizMsg.dsAcctNum_H);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctTpCd, CLT_ACCT_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteSubjTxt, cltNoteTp.cltNoteTpDescTxt);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteTpCd, cltNoteTp.cltNoteTpCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.dtlNoteTxt, dtlNoteTxt);

        EZDTBLAccessor.create(cltNoteDtlMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cltNoteDtlMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFDM0013E", new String[] {"CLT_NOTE_DTL" });
            return;
        }
    }
    // END   2018/04/18 [QC#20940, ADD]

    // START 2018/05/23 [QC#24882, ADD]
    private Map<String, Object> sendConfirmationLetter(NFDL0030CMsg bizMsg, String rcptNum) {
        Map<String, Object> confLtrInfo = new HashMap<String, Object>();
        
        List<String> arTrxNumList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            arTrxNumList.add(bizMsg.A.no(i).arTrxNum_A1.getValue());
        }
        if (!bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT) && arTrxNumList.isEmpty()) {
            return confLtrInfo;
        }

        // insert CLT_PMT_CONF_LTR_WRK
        Map<String, Object> cltInfoMap = null;
        BigDecimal rqstNum = null;
        List<BigDecimal> rqstNumList = new ArrayList<BigDecimal>();
        S21SsmEZDResult ssmResult = null;
        if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            if (DS_PMT_METH.CREDIT_CARD.equals(bizMsg.dsPmtMethCd_H.getValue())) {
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
                ssmResult = NFDL0030Query.getInstance().getConfLtrDataOnAcct(getGlobalCompanyCode(), rcptNum, bizMsg.crCardCustRefNum_H.getValue());
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            } else if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
                ssmResult = NFDL0030Query.getInstance().getConfLtrDataOnAcctForECheck(getGlobalCompanyCode(), rcptNum);
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        } else {
            // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//            ssmResult = NFDL0030Query.getInstance().getConfLtrData(getGlobalCompanyCode(), arTrxNumList, rcptNum, bizMsg.crCardCustRefNum_H.getValue());
            if (DS_PMT_METH.CREDIT_CARD.equals(bizMsg.dsPmtMethCd_H.getValue())) {
                ssmResult = NFDL0030Query.getInstance().getConfLtrData(getGlobalCompanyCode(), rcptNum, bizMsg.crCardCustRefNum_H.getValue());
            } else if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
                ssmResult = NFDL0030Query.getInstance().getConfLtrDataForECheck(getGlobalCompanyCode(), rcptNum);
            }
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
        }
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            String curBilToCustCd = null;
            String prvBilToCustCd = null;
            // START 2019/07/31 [QC#52112, ADD]
            String curBilToCustAcctCd = null;
            String prvBilToCustAcctCd = null;
            // END   2019/07/31 [QC#52112, ADD]
            for (Map<String, Object> confLtrInfoMap : resultList) {
                curBilToCustCd = (String) confLtrInfoMap.get("BILL_TO_CUST_CD");
                // START 2019/07/31 [QC#52112, ADD]
                curBilToCustAcctCd = (String) confLtrInfoMap.get("BILL_TO_CUST_ACCT_CD");
                // END   2019/07/31 [QC#52112, ADD]

                // get CLT_PMT_CONF_LTR_WRK_PK
                BigDecimal confLtrWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLT_PMT_CONF_LTR_WRK_SQ);

                if (!ZYPCommonFunc.hasValue(curBilToCustCd) || !curBilToCustCd.equals(prvBilToCustCd)) {
                    // START 2019/07/31 [QC#52112, MOD]
                    if (!ZYPCommonFunc.hasValue(curBilToCustAcctCd) || !curBilToCustAcctCd.equals(prvBilToCustAcctCd)) {
                        // get request# (seq.)
                        rqstNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLT_PMT_CONF_LTR_RQST_NUM_SQ);
                        rqstNumList.add(rqstNum);
                    }
                    // END   2019/07/31 [QC#52112, MOD]

                    // get collector info
                    String arTrxNum = (String) confLtrInfoMap.get("AR_TRX_NUM");
                    // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//                    if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
                    if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT) || !ZYPCommonFunc.hasValue(arTrxNum)) {
                    // END 2023/03/10 S.Nakatani [QC#55645,MOD]
                        arTrxNum = (String) confLtrInfoMap.get("RCPT_NUM");
                    }
                    cltInfoMap = (Map<String, Object>) NFDL0030Query.getInstance().getCltInfoMap(getGlobalCompanyCode(), arTrxNum).getResultObject();
                }

                // insert CLT_PMT_CONF_LTR_WRK
                CLT_PMT_CONF_LTR_WRKTMsg ltrWrkTMsg = createLtrWrkTMsg(confLtrWrkPk, rqstNum, cltInfoMap, confLtrInfoMap);
                EZDTBLAccessor.insert(ltrWrkTMsg);
                if (!RTNCD_NORMAL.equals(ltrWrkTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0032E");
                    return confLtrInfo;
                }

                prvBilToCustCd = curBilToCustCd;
                // START 2019/07/31 [QC#52112, ADD]
                prvBilToCustAcctCd = curBilToCustAcctCd;
                // END   2019/07/31 [QC#52112, ADD]
            }
        }

        // START 2019/02/07 S.Ohki [QC#30023,DEL]
//        // create S21EIP service instance
//        S21EIPPrintingService service = new S21EIPPrintingService();
        // END   2019/02/07 S.Ohki [QC#30023,DEL]

        // START 2019/02/07 S.Ohki [QC#30023,ADD]
        for (int i = 0; i < rqstNumList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.L.no(i).cltPmtConfLtrRqstNum_L1, rqstNumList.get(i));
        }
        bizMsg.L.setValidCount(rqstNumList.size());
        // END   2019/02/07 S.Ohki [QC#30023,ADD]

        // create email report
        for (BigDecimal tgtRqstNum : rqstNumList) {
            Map<String, Object> confLtrWrkMap = (Map<String, Object>) NFDL0030Query.getInstance().getConfLtrWrk(getGlobalCompanyCode(), tgtRqstNum).getResultObject();
            if (confLtrWrkMap != null) {
                // START 2019/02/07 S.Ohki [QC#30023,DEL]
//                // load mail template
//                S21MailTemplate template = createMailTemplate(MAIL_TMPL_ID, confLtrWrkMap);
//                if (template == null) {
//                    bizMsg.setMessageInfo(NFBM0184E, new String[] {MAIL_TMPL_ID} );
//                }
//
//                // create report request bean
//                S21ReportRequestBean request = new S21ReportRequestBean(RPT_ID);
//
//                // create report parameter
//                S21InputParameter inputParam = createInputParameter(request, confLtrWrkMap);
//                request.setInputParamBean(inputParam);
//
//                // create email parameter
//                // START 2018/05/29 [QC#24882, MOD]
//                S21EmailOutputParameter emailOutParam = createEmailOutputParameter(request, template, confLtrWrkMap, bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT));
//                // END   2018/05/29 [QC#24882, MOD]
//                request.setEmailOutParamBean(emailOutParam);
//
//                // create request
//                service.createReportByAsync(request);
//                // START 2018/07/10 [QC#24882-2, DEL]
//                // service.activateAsyncReportJob();
//                // END   2018/07/10 [QC#24882-2, DEL]
                // END   2019/02/07 S.Ohki [QC#30023,DEL]

                updateConfLtrInfo(confLtrInfo, confLtrWrkMap);
            }
        }

        // START 2019/02/07 S.Ohki [QC#30023,DEL]
//        // START 2018/07/10 [QC#24882-2, ADD]
//        service.activateAsyncReportJob();
//        // END   2018/07/10 [QC#24882-2, ADD]
        // END   2019/02/07 S.Ohki [QC#30023,DEL]

        return confLtrInfo;
    }

    private void updateConfLtrInfo(Map<String, Object> confLtrInfo, Map<String, Object> confLtrWrkMap) {
        String ctacToNm = (String) confLtrInfo.get(CTAC_TO_NM);
        String ctacToMlAddr = (String) confLtrInfo.get(CTAC_TO_ML_ADDR);

        StringBuilder builder = new StringBuilder();
        if (ctacToNm != null && !ctacToNm.isEmpty()) {
            builder.append(ctacToNm);
            builder.append(COMMA);
            builder.append(SPACE);
        }
        // START 2018/06/06 [QC#24882-1, MOD]
        if (ZYPCommonFunc.hasValue((String) confLtrWrkMap.get("CTAC_PSN_FIRST_NM"))) {
            builder.append((String) confLtrWrkMap.get("CTAC_PSN_FIRST_NM"));
            builder.append(SPACE);
        }
        if (ZYPCommonFunc.hasValue((String) confLtrWrkMap.get("CTAC_PSN_LAST_NM"))) {
            builder.append((String) confLtrWrkMap.get("CTAC_PSN_LAST_NM"));
        }
        // END   2018/06/06 [QC#24882-1, MOD]
        confLtrInfo.put(CTAC_TO_NM, builder.toString());
        
        builder = new StringBuilder();
        if (ctacToMlAddr != null && !ctacToMlAddr.isEmpty()) {
            builder.append(ctacToMlAddr);
            builder.append(COMMA);
            builder.append(SPACE);
        }
        // START 2018/06/06 [QC#24882-1, MOD]
        if (ZYPCommonFunc.hasValue((String) confLtrWrkMap.get("CTAC_PSN_EML_ADDR"))) {
            builder.append((String) confLtrWrkMap.get("CTAC_PSN_EML_ADDR"));
        }
        // END   2018/06/06 [QC#24882-1, MOD]
        confLtrInfo.put(CTAC_TO_ML_ADDR, builder.toString());
    }

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    private void updateEchkRcptTrx(NFDL0030CMsg bizMsg, String echkPmtReqId) {
        ECHK_RCPT_TRXTMsg inMsg = new ECHK_RCPT_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.echkPmtReqId, echkPmtReqId);

        inMsg = (ECHK_RCPT_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
        if (inMsg == null) {
            throw new S21AbendException(NFCM0594E, new String[] {"ECHK_RCPT_TRX" });
        }

        ZYPEZDItemValueSetter.setValue(inMsg.arCashApplyFlg, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.update(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NFCM0533E, new String[] {"ECHK_RCPT_TRX" });
        }
    }
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]

    private CLT_PMT_CONF_LTR_WRKTMsg createLtrWrkTMsg(BigDecimal cltPmtConfLtrWrkPk, BigDecimal cltPmtConfLtrRqstNum, Map<String, Object> cltInfoMap, Map<String, Object> confLtrInfoMap) {
        String glblCmpyCd = getGlobalCompanyCode();

        CLT_PMT_CONF_LTR_WRKTMsg tMsg = new CLT_PMT_CONF_LTR_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPmtConfLtrWrkPk, cltPmtConfLtrWrkPk);
        ZYPEZDItemValueSetter.setValue(tMsg.cltPmtConfLtrRqstNum, cltPmtConfLtrRqstNum);

        ZYPEZDItemValueSetter.setValue(tMsg.hdrCmpyNm, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_HDR_PRINT_CMPY_NM, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcFirstLineAddr, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_FIRST_ADDR, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcScdLineAddr, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_SCD_ADDR, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcThirdLineAddr, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_THIRD_ADDR, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcFrthLineAddr, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_FRTH_ADDR, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcCtyAddr, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_CITY_ADDR, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcProvNm, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_PROV_ADDR, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcStCd, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_ST_CD, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcPostCd, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_POST_CD, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcTelNum, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_OFC_TEL_NUM, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.hdrCmpyUrl, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_CMPY_URL, glblCmpyCd));

        ZYPEZDItemValueSetter.setValue(tMsg.pmtConfDtFmtTxt, convertDtFormat(ZYPDateUtil.getSalesDate()));

        ZYPEZDItemValueSetter.setValue(tMsg.hdrCustNm, (String) confLtrInfoMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, (String) confLtrInfoMap.get("CTAC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnMidNm, (String) confLtrInfoMap.get("CTAC_PSN_MID_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, (String) confLtrInfoMap.get("CTAC_PSN_LAST_NM"));

        ZYPEZDItemValueSetter.setValue(tMsg.crCardTpDescTxt, (String) confLtrInfoMap.get("CR_CARD_TP_DESC_TXT"));

        ZYPEZDItemValueSetter.setValue(tMsg.dtlArTrxNum, (String) confLtrInfoMap.get("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dtlArCustRefNum , (String) confLtrInfoMap.get("AR_CUST_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dtlGrpInvNum, (String) confLtrInfoMap.get("GRP_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dtlDealNetRcptAmtTxt, convertAmtFormat((String) confLtrInfoMap.get("CCY_SGN_TXT"), (BigDecimal) confLtrInfoMap.get("DEAL_RCPT_DTL_AMT")));
        ZYPEZDItemValueSetter.setValue(tMsg.dtlCrCardCustRefNum, (String) confLtrInfoMap.get("RCPT_CHK_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dtlBillToCustCd, (String) confLtrInfoMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.dtlBillToCustAcctCd, (String) confLtrInfoMap.get("BILL_TO_CUST_ACCT_CD"));

        ZYPEZDItemValueSetter.setValue(tMsg.cltrCmpyNm, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_HDR_PRINT_CMPY_NM, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrPsnFirstNm, (String) cltInfoMap.get("PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrPsnMidNm, (String) cltInfoMap.get("PSN_MID_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrPsnLastNm, (String) cltInfoMap.get("PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrHrTtlNm, (String) cltInfoMap.get("HR_TTL_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrEmlAddr, (String) cltInfoMap.get("EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrFaxNum, (String) cltInfoMap.get("FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrTelNum, (String) cltInfoMap.get("WORK_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrFirstLineAddr, (String) cltInfoMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrScdLineAddr, (String) cltInfoMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrThirdLineAddr, (String) cltInfoMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrFrthLineAddr, (String) cltInfoMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrCtyAddr, (String) cltInfoMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrProvNm, (String) cltInfoMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrStCd, (String) cltInfoMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrPostCd, (String) cltInfoMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.cltrHdrCmpyUrl, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_CONF_LTR_CMPY_URL, glblCmpyCd));

        // START 2023/04/25 R.Takau [QC#55998,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.dealNetRcptAmtTotTxt, convertAmtFormat((String) confLtrInfoMap.get("CCY_SGN_TXT"), (BigDecimal) confLtrInfoMap.get("DEAL_RCPT_AMT")));
        // END 2023/04/25 R.Takau [QC#55998,ADD]
        return tMsg;
    }

    private static String convertDtFormat(String yyyyMMdd) {
        if (!ZYPCommonFunc.hasValue(yyyyMMdd) || !ZYPDateUtil.checkDate(yyyyMMdd)) {
            return "";
        }
        try {
            DateFormat inFormatter = new SimpleDateFormat(IN_DATE_FORMAT);
            DateFormat outFormatter = new SimpleDateFormat(OUT_DATE_FORMAT);
            Date dt = inFormatter.parse(yyyyMMdd);
            return outFormatter.format(dt);

        } catch (ParseException e) {
            throw new S21AbendException(e);
        }
    }

    private static String convertAmtFormat(String ccySgnTxt, BigDecimal data) {
        StringBuilder builder = new StringBuilder();
        if (ZYPCommonFunc.hasValue(ccySgnTxt)) {
            builder.append(ccySgnTxt);
        }
        builder.append(ZYPFormatUtil.formatNumToSysDisp(data.setScale(2)));
        return builder.toString();
    }

    // START 2019/02/07 S.Ohki [QC#30023,DEL]
//    private S21MailTemplate createMailTemplate(String mlTmplId, Map<String, Object> confLtrWrkMap) {
//        S21MailTemplate template = new S21MailTemplate(getGlobalCompanyCode(), mlTmplId);
//        template.setTemplateParameter(ML_TMPL_PRM_BILL_TO_CUST_CD, (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_CD"));
//        template.setTemplateParameter(ML_TMPL_PRM_CARD_TYPE_NAME, (String) confLtrWrkMap.get("CR_CARD_TP_DESC_TXT"));
//        template.setTemplateParameter(ML_TMPL_PRM_SIGNATURE, createSignature(confLtrWrkMap));
//
//        return template;
//    }
//
//    private String createSignature(Map<String, Object> confLtrWrkMap) {
//        StringBuilder signature = new StringBuilder();
//
//        signature.append(confLtrWrkMap.get("CLTR_PSN_FIRST_NM"));
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_PSN_MID_NM"))) {
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_PSN_MID_NM"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_PSN_LAST_NM"))) {
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_PSN_LAST_NM"));
//        }
//        signature.append(System.getProperty("line.separator"));
//
//        // START 2018/06/06 [QC#24882, MOD]
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_HR_TTL_NM"))) {
//            signature.append(confLtrWrkMap.get("CLTR_HR_TTL_NM"));
//            signature.append(System.getProperty("line.separator"));
//        }
//
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_CMPY_NM"))) {
//            signature.append(confLtrWrkMap.get("CLTR_CMPY_NM"));
//            signature.append(System.getProperty("line.separator"));
//        }
//        // END   2018/06/06 [QC#24882, MOD]
//
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_FIRST_LINE_ADDR"))) {
//            signature.append(confLtrWrkMap.get("CLTR_FIRST_LINE_ADDR"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_SCD_LINE_ADDR"))) {
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_SCD_LINE_ADDR"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_THIRD_LINE_ADDR"))) {
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_THIRD_LINE_ADDR"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_FRTH_LINE_ADDR"))) {
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_FRTH_LINE_ADDR"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_CTY_ADDR"))) {
//            signature.append(COMMA);
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_CTY_ADDR"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_PROV_NM"))) {
//            signature.append(COMMA);
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_PROV_NM"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_ST_CD"))) {
//            signature.append(COMMA);
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_ST_CD"));
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_POST_CD"))) {
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_POST_CD"));
//        }
//        signature.append(System.getProperty("line.separator"));
//
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_HDR_CMPY_URL"))) {
//            signature.append(confLtrWrkMap.get("CLTR_HDR_CMPY_URL"));
//        }
//        signature.append(System.getProperty("line.separator"));
//
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_TEL_NUM"))) {
//            signature.append(TELEPHONE);
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_TEL_NUM"));
//            signature.append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String)confLtrWrkMap.get("CLTR_FAX_NUM"))) {
//            signature.append(FAX);
//            signature.append(SPACE);
//            signature.append(confLtrWrkMap.get("CLTR_FAX_NUM"));
//        }
//
//        return signature.toString();
//    }
//
//    private S21InputParameter createInputParameter(S21ReportRequestBean request, Map<String, Object> confLtrWrkMap) {
//        S21InputParameter inputParam = request.getInputParamBeanInstance();
//
//        String sysTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
//        request.setRptTtlNm(getReportTitle(RPT_TTL_NM, (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_CD"), sysTimeStamp));
//        request.setRptArcFlg(true);
//        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
//
//        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
//        inputParam.addReportParameter("GLBL_CMPY_CD", getGlobalCompanyCode());
//        inputParam.addReportParameter("CLT_PMT_CONF_LTR_RQST_NUM", confLtrWrkMap.get("CLT_PMT_CONF_LTR_RQST_NUM"));
//        // START 2019/01/07 T.Ogura [QC#28800,ADD]
//        inputParam.addReportParameter("DTL_BILL_TO_CUST_ACCT_CD", confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD"));
//        // END   2019/01/07 T.Ogura [QC#28800,ADD]
//
//        return inputParam;
//    }
//
//    private String getReportTitle(String titleName, String billToCustCd, String timeStampStr) {
//        StringBuilder signature = new StringBuilder();
//        signature.append(titleName);
//        // START 2018/06/06 [QC#24882-1, MOD]
//        if (ZYPCommonFunc.hasValue(billToCustCd)) {
//            signature.append(SPACE);
//            signature.append("BillToCustomer");
//            signature.append(SPACE);
//            signature.append(billToCustCd);
//        }
//        // END   2018/06/06 [QC#24882-1, MOD]
//        signature.append(SPACE);
//        signature.append("Time");
//        signature.append(SPACE);
//        signature.append(timeStampStr);
//
//        return signature.toString();
//    }
//
//    private S21EmailOutputParameter createEmailOutputParameter(S21ReportRequestBean request, S21MailTemplate template, Map<String, Object> confLtrWrkMap, boolean onAccount) {
//        // START 2018/05/29 [QC#24882, MOD]
//        String cltPsnCd = null;
//        if (onAccount) {
//            cltPsnCd = (String) NFDL0030Query.getInstance().getCltPsnCdOnAcct(
//                    getGlobalCompanyCode(), (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD")).getResultObject();
//        } else {
//            cltPsnCd = (String) NFDL0030Query.getInstance().getCltPsnCd(
//                    getGlobalCompanyCode(), (String) confLtrWrkMap.get("DTL_AR_TRX_NUM")).getResultObject();
//        }
//        String fromAddr = NFDL0030CommonLogic.getCollectorMailAddress(getGlobalCompanyCode(), getUserProfileService(), cltPsnCd);
//        // END   2018/05/29 [QC#24882, MOD]
//        // START 2018/06/06 [QC#24882-1, MOD]
//        // String toAddr = (String) confLtrWrkMap.get("CTAC_PSN_EML_ADDR");
//        String toAddr = fromAddr;
//        // END   2018/06/06 [QC#24882-1, MOD]
//
//        S21EmailOutputParameter emailOutParam = request.getEmailOutParamInstance();
//        emailOutParam.setBranchNo(RPT_BR_NUM_EML);
//        emailOutParam.setSenderAddress(fromAddr);
//        emailOutParam.addToAddress(toAddr);
//        emailOutParam.setSubject(template.getSubject());
//        emailOutParam.setBodyText(template.getBody());
//        // START 2018/07/11 [QC#27002, MOD]
////        emailOutParam.setAttachFileName(ATTACH_FILE_NM);
//        emailOutParam.setAttachFileName(ATTACH_FILE_NM + EXTENSION_PDF);
//        // END   2018/07/11 [QC#27002, MOD]
//        emailOutParam.setDestinationID(RPT_DEST_ID);
//
//        return emailOutParam;
//    }
//    // END   2018/05/23 [QC#24882, ADD]
    // END   2019/02/07 S.Ohki [QC#30023,DEL]

    // START 2020/06/2 [QC#56866, ADD]
    /**
     * createName
     * 
     * @param name String
     * @return     String
     */
    private String createName (String name) {
        if (!name.contains(" ")) {
           name =  "*" + name;
        } else {
            int cnt = name.lastIndexOf(" ") + 1;
            StringBuilder sb = new StringBuilder(name);
            sb.insert(cnt, "*");
            return sb.toString();
        }
        return name;
    }
    // END   2020/06/2 [QC#56866, ADD]

    // START 2023/11/29 TZ.Win [QC#62089,ADD]
    /**
     * isExistInvoice
     * 
     * @param bizMsg NFDL0030CMsg
     * @return     boolean
     */
    private boolean isExistInvoice (NFDL0030CMsg bizMsg) {
        boolean invExistFlg = false;
        if (bizMsg.A.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if(!AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(i).arTrxTpCd_A1.getValue())){
                    invExistFlg = true;
                }
            }
        }
        return invExistFlg;
    }
    // END 2023/11/29 TZ.Win [QC#62089,ADD]
}
