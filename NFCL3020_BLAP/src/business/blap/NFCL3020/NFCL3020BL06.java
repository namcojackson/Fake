package business.blap.NFCL3020;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3020.common.NFCL3020CommonLogic;
import business.blap.NFCL3020.constant.NFCL3020Constant;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_BAT_RCPTTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC310001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC310001.NFZC310001;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 09/05/2016   Fujitsu         C.Tanaka        Update          QC#5521
 * 10/12/2016   Hitachi         E.Kameishi      Update          QC#14273
 * 01/10/2018   Fujitsu         T.Murai         Update          QC#21400
 * 2018/01/18   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/03/14   Fujitsu         H.Ikeda         Update          QC#24800
 * 2018/04/03   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 2018/04/06   Fujitsu         H.Ikeda         Update          QC#25338
 * 2018/06/04   Fujitsu         Y.Matsui        Update          QC#25368
 * 2018/07/13   Fujitsu         Y.Matsui        Update          QC#26993
 * 2018/07/23   Fujitsu         S.Ohki          Update          QC#25928
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3020BL06 extends S21BusinessHandler implements NFCL3020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3020Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Save(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_Click_Del".equals(screenAplID)) {
                doProcess_NFCL3020Scrn00_Click_Del((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);

            // QC#5521 ADD Start
            } else if ("NFCL3020Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);

            } else if ("NFCL3020Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);

            // START 2018/07/23 S.Ohki [QC#25928, DEL]
//            } else if ("NFCL3020Scrn00_DeleteSearch".equals(screenAplID)) {
//                doProcess_NFCL3020Scrn00_DeleteSearch((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);
//
//            } else if ("NFCL3020Scrn00_SaveSearch".equals(screenAplID)) {
//                doProcess_NFCL3020Scrn00_SaveSearch((NFCL3020CMsg) cMsg, (NFCL3020SMsg) sMsg);
            // END   2018/07/23 S.Ohki [QC#25928, DEL]
            // QC#5521 ADD End
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
    private void doProcess_NFCL3020Scrn00_CMN_Save(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Save================================START", this);
        updateBatReceipt(cMsg, sMsg, false);
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Save================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Submit================================START", this);
        updateBatReceipt(cMsg, sMsg, true);
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_CMN_Submit================================END", this);
    }

    private void updateBatReceiptByStatus(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        NFCL3020CommonLogic.copyPage(bizMsg, bizMsg.B, globalMsg.B);
        
        if(ZYPCommonFunc.hasValue(bizMsg.arBatRcptStsCd_H.getValue())) { 
            
        }
    }

    private void updateBatReceiptByStatusNew(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;

        AR_BAT_RCPTTMsg batUpdMsg = new AR_BAT_RCPTTMsg();

        batUpdMsg = new AR_BAT_RCPTTMsg();
        ZYPEZDItemValueSetter.setValue(batUpdMsg.glblCmpyCd, getGlobalCompanyCode());
        String arBatRcptNm = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), "AR_BAT_RCPT_NM");
        ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptNm, arBatRcptNm);

    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void updateBatReceipt(EZDCMsg cMsg, EZDSMsg sMsg, boolean isSubmit) {
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        NFCL3020CommonLogic.copyPage(bizMsg, bizMsg.B, globalMsg.B);

        boolean errFlg = false;

        String salesDt = ZYPDateUtil.getSalesDate();
        if (bizMsg.arBatRcptDt_H.getValue().compareTo(salesDt) > 0) {
            bizMsg.arBatRcptDt_H.setErrorInfo(1, "NFCM0040E", new String[] {});
            errFlg = true;
        }
// Def#3294
//        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
//            if (!ZYPCommonFunc.hasValue(bizMsg.arBatRcptDt_H) || bizMsg.B.no(i).rcptDt_B.getValue().compareTo(bizMsg.arBatRcptDt_H.getValue()) > 0) {
//                bizMsg.arBatRcptDt_H.setErrorInfo(1, "NFCM0776E", new String[] {"Batch Date", "Receipt Date"});
//                errFlg = true;
//            }
//        }

//        if (isSubmit) {
//            if (globalMsg.B.getValidCount() == 0) {
//                bizMsg.setMessageInfo("NFCM0761E");
//            }
//
//            BigDecimal totCnt = new BigDecimal(globalMsg.B.getValidCount());
//            BigDecimal totAmt = BigDecimal.ZERO;
//
//            for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
//                totAmt = totAmt.add(globalMsg.B.no(i).funcRcptAmt_B.getValue());
//            }
//
//            if (totCnt.compareTo(bizMsg.arBatRcptCnt_H.getValue()) != 0) {
//                bizMsg.arBatRcptCnt_H.setErrorInfo(1, "NFCM0759E", new String[] {totCnt.toPlainString() });
//                errFlg = true;
//            }
//
//            if (totAmt.compareTo(bizMsg.arBatRcptAmt_H.getValue()) != 0) {
//                bizMsg.arBatRcptAmt_H.setErrorInfo(1, "NFCM0760E", new String[] {totAmt.toPlainString() });
//                errFlg = true;
//            }
//        }
        BigDecimal totAmt = BigDecimal.ZERO;
        BigDecimal totCnt = new BigDecimal(globalMsg.B.getValidCount());
        if (globalMsg.B.getValidCount() != 0) {

            for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
                totAmt = totAmt.add(globalMsg.B.no(i).funcRcptAmt_B.getValue());
            }
        }

        AR_BAT_RCPTTMsg batUpdMsg;
        String arBatRcptNm;

        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)) {
            arBatRcptNm = bizMsg.arBatRcptNm_H.getValue();
            AR_BAT_RCPTTMsg inMsg = new AR_BAT_RCPTTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.arBatRcptNm, arBatRcptNm);
            batUpdMsg = (AR_BAT_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            if (!bizMsg.ezUpTime_H.getValue().equals(batUpdMsg.ezUpTime.getValue()) || !bizMsg.ezUpTimeZone_H.getValue().equals(batUpdMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            }
        } else {
            batUpdMsg = new AR_BAT_RCPTTMsg();
            ZYPEZDItemValueSetter.setValue(batUpdMsg.glblCmpyCd, getGlobalCompanyCode());
            arBatRcptNm = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), "AR_BAT_RCPT_NM");
            ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptNm, arBatRcptNm);
        }
        ZYPEZDItemValueSetter.setValue(batUpdMsg.arRcptSrcCd, bizMsg.arRcptSrcCd_H);

        // START 2018/07/13 Y.Matsui [QC#26993, MOD]
        NFCL3020CommonLogic.reCalcArBatRcptStatus(bizMsg, globalMsg);
        ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptStsCd, bizMsg.arBatRcptStsCd_H);
        // END   2018/07/13 Y.Matsui [QC#26993, MOD]

        ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptCmntTxt, bizMsg.arBatRcptCmntTxt_H);

        ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptCnt, bizMsg.arBatRcptCnt_H);
        ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptAmt, bizMsg.arBatRcptAmt_H);
        ZYPEZDItemValueSetter.setValue(batUpdMsg.arBatRcptDt, bizMsg.arBatRcptDt_H);
        ZYPEZDItemValueSetter.setValue(batUpdMsg.remDsBankAcctPk, bizMsg.remDsBankAcctPk_H);

        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)) {
            EZDTBLAccessor.update(batUpdMsg);
        } else {
            EZDTBLAccessor.create(batUpdMsg);
        }
        if (!RTNCD_NORMAL.equals(batUpdMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return;
        }
        // START 2016/10/12 E.Kameishi [QC#14273,ADD]
        GLBL_CMPYTMsg inGlblCmpyMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(inGlblCmpyMsg.glblCmpyCd, getGlobalCompanyCode());
        GLBL_CMPYTMsg outGloblCmpyMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(inGlblCmpyMsg);
        // END 2016/10/12 E.Kameishi [QC#14273,ADD]
        // START   2018/04/06 H.Ikeda [QC#25338, ADD]
        String arRcptTocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY, getGlobalCompanyCode());
        String arRcptProdCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY, getGlobalCompanyCode());
        // END     2018/04/06 H.Ikeda [QC#25338, ADD]
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            // START   2018/04/06 H.Ikeda [QC#25338, ADD]
            boolean isFirst = false;
            String beforeArRcptStsCd = null;
            // END     2018/04/06 H.Ikeda [QC#25338, ADD]
            AR_RCPTTMsg rcptUpdMsg;
            // START 2024/02/23 S.Ikariya [QC#63452, ADD]
            AR_TRX_BALTMsg invData = new AR_TRX_BALTMsg();
            // END 2024/02/23 S.Ikariya [QC#63452, ADD]
            if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).rcptNum_B)) {
                AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, globalMsg.B.no(i).rcptNum_B);
                rcptUpdMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                if (!globalMsg.B.no(i).ezUpTime_B.getValue().equals(rcptUpdMsg.ezUpTime.getValue()) || !globalMsg.B.no(i).ezUpTimeZone_B.getValue().equals(rcptUpdMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
            } else {
                // START 2024/02/23 S.Ikariya [QC#63452, ADD]
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).arTrxNum_B)) {
                    AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
                    inMsg.setSQLID("001");
                    inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                    inMsg.setConditionValue("arTrxNum01", globalMsg.B.no(i).arTrxNum_B.getValue());
                    AR_TRX_BALTMsgArray outMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

                    if (outMsg != null && outMsg.getValidCount() != 0) {
                        invData = outMsg.no(0);
                    } else {
                        bizMsg.setMessageInfo("NFCM0864E", new String[] {"AR_TRX_BAL", globalMsg.B.no(i).arTrxNum_B.getValue() });
                        return;
                    }
                    if (!checkInvBalForCashApp(bizMsg, globalMsg.B.no(i), invData)) {
                        continue;
                    }
                }
                // END 2024/02/23 S.Ikariya [QC#63452, ADD]
                String rcptNm = ZYPNumbering.getUniqueID(getGlobalCompanyCode(), "RC#");
                rcptUpdMsg = new AR_RCPTTMsg();
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptNum, rcptNm);
            }
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptChkNum, globalMsg.B.no(i).rcptChkNum_B);
            
            beforeArRcptStsCd = rcptUpdMsg.arRcptStsCd.getValue();

            if (!ZYPCommonFunc.hasValue(globalMsg.B.no(i).rcptNum_B)) {
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arBatRcptNm, arBatRcptNm);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatNum, "999");
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptBatSqNum, "001");

                // START 2016/10/12 E.Kameishi [QC#14273,ADD]
                if (outGloblCmpyMsg != null) {
                    if (ZYPCommonFunc.hasValue(outGloblCmpyMsg.stdCcyCd)) {
                        ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealCcyCd, outGloblCmpyMsg.stdCcyCd.getValue());
                    }
                }
                // END 2016/10/12 E.Kameishi [QC#14273,ADD]
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealApplyAdjAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRfAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealVoidAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exchRate, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcCcyCd, "USD");

                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcApplyAdjAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRfAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcVoidAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRvalVarAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, BigDecimal.ZERO);

                // START 2018/04/04 H.Ikeda [QC#21737-1, MOD]
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).payerCustCd_B) && !ZYPCommonFunc.hasValue(rcptUpdMsg.payerCustCd)) {
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNAPPLIED);
                    // START 2018/09/20 T.Ogura [QC#28097,MOD]
//                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.OPEN);
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.UNAPPLIED);
                    // END   2018/09/20 T.Ogura [QC#28097,MOD]
                    isFirst = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arCashApplyStsCd, AR_CASH_APPLY_STS.UNIDENTIFIED);
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.NEW);
                }
                // END   2018/04/04 H.Ikeda [QC#21737-1, MOD]
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, globalMsg.B.no(i).funcRcptAmt_B);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, globalMsg.B.no(i).funcRcptAmt_B);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptSrcCd, bizMsg.arRcptSrcCd_H);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTrxTpCd, globalMsg.B.no(i).arRcptTrxTpCd_B);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rcptDt, globalMsg.B.no(i).rcptDt_B);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.voidFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.exptFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptStsCd, AR_RCPT_STS.NEW);
//                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.remDsBankAcctPk, bizMsg.remDsBankAcctPk_H);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.remDsBankAcctPk, globalMsg.B.no(i).remDsBankAcctPk_B);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.ajeCratCpltFlg, ZYPConstant.FLG_OFF_N);
                // START 2018/03/14 H.Ikeda [QC#24800, ADD]
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.payerCustCd, globalMsg.B.no(i).payerCustCd_B);
                // End   2018/03/14 H.Ikeda [QC#24800, ADD]
                // START 2018/04/06 H.Ikeda [QC#25338, ADD]
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.arRcptTpCd, AR_RCPT_TP_DUMMY);
                // START 2018/06/04 Y.Matsui [QC#25368,MOD]
//                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glDt, bizMsg.arBatRcptDt_H);
                String arAcctDt = NFCL3020CommonLogic.getArAcctDt(bizMsg);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.glDt, arAcctDt);
                // END   2018/06/04 Y.Matsui [QC#25368,MOD]
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.tocCd, arRcptTocCd);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.coaProdCd, arRcptProdCd);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.rfExchRate, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.cashAppDt, bizMsg.arBatRcptDt_H);
                // END   2018/04/06 H.Ikeda [QC#25338, ADD]
            } else {
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealNetRcptAmt, globalMsg.B.no(i).funcRcptAmt_B);
                ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcNetRcptAmt, globalMsg.B.no(i).funcRcptAmt_B);
                if(globalMsg.B.no(i).arCashApplyStsCd_B.getValue().equals(AR_CASH_APPLY_STS.UNAPPLIED)) {
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptAmt, globalMsg.B.no(i).funcRcptAmt_B);
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.dealRcptRmngBalAmt, globalMsg.B.no(i).funcRcptAmt_B);
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptAmt, globalMsg.B.no(i).funcRcptAmt_B);
                    ZYPEZDItemValueSetter.setValue(rcptUpdMsg.funcRcptRmngBalAmt, globalMsg.B.no(i).funcRcptAmt_B);
                }
            }

            // START 2018/03/14 H.Ikeda [QC#24800, ADD]
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.payerCustCd, globalMsg.B.no(i).payerCustCd_B);
            // End   2018/03/14 H.Ikeda [QC#24800, ADD]

            // START 2018/04/06 H.Ikeda [QC#25338, ADD]
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.locNum, globalMsg.B.no(i).locNum_B);
            ZYPEZDItemValueSetter.setValue(rcptUpdMsg.lastUpdUserId, getContextUserInfo().getUserId());
            // END   2018/04/06 H.Ikeda [QC#25338, ADD]

            if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).rcptNum_B)) {
                EZDTBLAccessor.update(rcptUpdMsg);
            } else {
                EZDTBLAccessor.create(rcptUpdMsg);
            }
            if (!RTNCD_NORMAL.equals(rcptUpdMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return;
            }

            // START 2018/04/06 H.Ikeda [QC#25338, ADD]
            if (!ZYPCommonFunc.hasValue(beforeArRcptStsCd) || beforeArRcptStsCd.equals(AR_RCPT_STS.NEW)) {
                if (!createArRcptDtl(bizMsg, rcptUpdMsg)) {
                    return;
                }
            }

            //---------------------------------------------------------
            // Cash Application API
            //---------------------------------------------------------
            if (isFirst) {
                AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
                if (!createArTrxBal(bizMsg, arTrxBalTMsg, rcptUpdMsg)) {
                    return;
                }
                if (!createArApplyIntfcWrk(bizMsg, rcptUpdMsg, arTrxBalTMsg)) {
                    return;
                }
                // START 2024/02/23 S.Ikariya [QC#63452, ADD]
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).arTrxNum_B)) {
                    if (!createArApplyIntfcWrkForCashApp(bizMsg, rcptUpdMsg, arTrxBalTMsg, globalMsg.B.no(i), invData)) {
                        return;
                    }
                }
                // END 2024/02/23 S.Ikariya [QC#63452, ADD]
            }

            //----------------------------------------------------------
            // Update AR_RCPT_DTL,AR_TRX_BAL,AR_RCPT_UN_APPLY
            //----------------------------------------------------------
            // START 2018/09/20 T.Ogura [QC#28097,ADD]
//            if (globalMsg.B.no(i).arRcptStsCd_B.getValue().equals(AR_RCPT_STS.OPEN)) {
            if (globalMsg.B.no(i).arRcptStsCd_B.getValue().equals(AR_RCPT_STS.UNAPPLIED)) {
            // END   2018/09/20 T.Ogura [QC#28097,ADD]
               // NFCCmnMethod.updateArTrxBalRcptUnApply(bizMsg.glblCmpyCd.getValue(), rcptUpdMsg);
                if (!NFCCmnMethod.updateArTrxBalRcptUnApply(bizMsg.glblCmpyCd.getValue(), rcptUpdMsg)) {
                    bizMsg.setMessageInfo("NFCM0032E");
                    return;
                }
            }

            // START 2018/04/06 H.Ikeda [QC#25338, ADD]
            //---------------------------------------------------------
            // Update AR_BAT_RCPT AR_BAT_RCPT_STS
            //---------------------------------------------------------
            updateArBatRcptStatus(cMsg);

            //---------------------------------------------------------
            // Update Credit Profile
            //---------------------------------------------------------
            if (!NFCL3020CommonLogic.callCrPrflUpdtAPI(bizMsg, rcptUpdMsg)){
                return;
            }
            // END   2018/04/06 H.Ikeda [QC#25338, ADD]
        }
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        for (int j = 0; j < globalMsg.B.getValidCount(); j++) {
            if (globalMsg.B.no(j).rcptChkNum_B.isError()) {
                NFCL3020CommonLogic.setPageData(globalMsg, bizMsg, j + 1);
                break;
            }
        }
        if (!MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.arBatRcptNm_H, arBatRcptNm);
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    }

    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Check InvoiceBalance For CashApply 
     * @param bizMsg NFCL3020CMsg
     * @param globalMsg NFCL3020BSMsg
     * @param invData AR_TRX_BALTMsg
     * @return boolean
     */
    private boolean checkInvBalForCashApp(NFCL3020CMsg bizMsg, NFCL3020_BSMsg globalMsg, AR_TRX_BALTMsg invData) {
        if (globalMsg.funcRcptAmt_B.getValueInt() <= 0) {
            globalMsg.rcptChkNum_B.setErrorInfo(1, "NFCM0066E");
            bizMsg.setMessageInfo("ZZM9037E", null);
            return false;
        }
        if (invData.arCashApplyStsCd.getValue().equals(AR_CASH_APPLY_STS.APPLIED) && invData.dealRmngBalGrsAmt.getValueInt() == 0) {
            globalMsg.rcptChkNum_B.setErrorInfo(1, "NFCM0066E");
            bizMsg.setMessageInfo("ZZM9037E", null);
            return false;
        }
        return true;
    }

    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    // START 2018/04/06 H.Ikeda [QC#25338, ADD]
    /**
     * Create AR Receipt Detail
     * @param bizMsg NFCL3020CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean createArRcptDtl(NFCL3020CMsg bizMsg, AR_RCPTTMsg arRcptTMsg) {
        AR_RCPT_DTLTMsg inMsg = new AR_RCPT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptDtlNum, RCPT_DTL_NUM);
        AR_RCPT_DTLTMsg outMsg = (AR_RCPT_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg != null) {
            ZYPEZDItemValueSetter.setValue(outMsg.dealRcptDtlAmt, arRcptTMsg.dealNetRcptAmt);
            ZYPEZDItemValueSetter.setValue(outMsg.funcRcptDtlAmt, arRcptTMsg.funcNetRcptAmt);

            EZDTBLAccessor.update(outMsg);
            if (!RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                return false;
            }
            return true;
        }
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
     * 
     * @param cMsg EZDCMsg
     * @return boolean
     */
    public static boolean updateArBatRcptStatus(EZDCMsg cMsg) {
        // START 2018/07/13 Y.Matsui [QC#26993, MOD]
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
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

    /**
     * Create AR Apply Interface Work
     * @param bizMsg NFCL3030CMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @param arTrxBalTMsg AR_TRX_BALTMsg
     * @return boolean
     */
    public boolean createArApplyIntfcWrk(NFCL3020CMsg bizMsg, AR_RCPTTMsg arRcptTMsg, AR_TRX_BALTMsg arTrxBalTMsg) {
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
        // START 2018/06/04 Y.Matsui [QC#25368,MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.rcptGlDt, bizMsg.arBatRcptDt_H);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptGlDt, arRcptTMsg.glDt);
        // END   2018/06/04 Y.Matsui [QC#25368,MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, arRcptTMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalPk, arTrxBalTMsg.arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptHdrTz, arRcptTMsg.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inMsg.rcptTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
        ZYPEZDItemValueSetter.setValue(inMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.invTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, arTrxBalTMsg.dealCcyCd);
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
     * Create AR Transaction Balance
     * @param bizMsg NFCL3020CMsg
     * @param inMsg AR_TRX_BALTMsg
     * @param arRcptTMsg AR_RCPTTMsg
     * @return boolean
     */
    public boolean createArTrxBal(NFCL3020CMsg bizMsg, AR_TRX_BALTMsg inMsg, AR_RCPTTMsg arRcptTMsg) {
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());

        BigDecimal arTrxBalPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_TRX_BAL_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, arRcptTMsg.rcptNum);
        ZYPEZDItemValueSetter.setValue(inMsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
        ZYPEZDItemValueSetter.setValue(inMsg.dealCcyCd, arRcptTMsg.dealCcyCd);

        ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealNetSlsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.dealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealInvDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealPrmoDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.dealRcptVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.funcCcyCd, arRcptTMsg.funcCcyCd);

        ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyGrsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyCrAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inMsg.funcRvalVarAmt, BigDecimal.ZERO);

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
        // START 2018/06/04 Y.Matsui [QC#25368,MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.glDt, bizMsg.arBatRcptDt_H);
        ZYPEZDItemValueSetter.setValue(inMsg.glDt, arRcptTMsg.glDt);
        // END   2018/06/04 Y.Matsui [QC#25368,MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.payerCustCd, arRcptTMsg.payerCustCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, arRcptTMsg.tocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.coaProdCd, arRcptTMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(inMsg.exptFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(inMsg.billToCustAcctCd, arRcptTMsg.payerCustCd);

        ZYPEZDItemValueSetter.setValue(inMsg.arAutoPurgeOfsFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(inMsg.arCustRefNum, arRcptTMsg.rcptChkNum);

        EZDTBLAccessor.create(inMsg);
        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E");
            return false;
        }
        return true;
    }

    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Create AR Apply Interface Work
     * @param bizMsg NFCL3020CMsg
     * @param rcptUpdMsg AR_RCPTTMsg
     * @param arTrxBalTMsg AR_TRX_BALTMsg
     * @param globalMsg NFCL3020_BSMsg
     * @param invData AR_TRX_BALTMsg
     * @return boolean
     */
    private boolean createArApplyIntfcWrkForCashApp(NFCL3020CMsg bizMsg, AR_RCPTTMsg rcptUpdMsg, AR_TRX_BALTMsg arTrxBalTMsg, NFCL3020_BSMsg globalMsg, AR_TRX_BALTMsg invData) {

        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        String userId = getContextUserInfo().getUserId();
        if (!ZYPCommonFunc.hasValue(currentSystemTime)) {
            bizMsg.setMessageInfo("NFCM0041E");
        }
        String applyGrpKey = userId.concat(currentSystemTime);
        BigDecimal applyGrpSubPk = BigDecimal.ONE;

        BigDecimal dealApplyAmt = BigDecimal.ZERO;
        if (globalMsg.funcRcptAmt_B.getValue().compareTo(invData.dealRmngBalGrsAmt.getValue()) >= 0) {
            dealApplyAmt = invData.dealRmngBalGrsAmt.getValue();
        } else {
            dealApplyAmt = globalMsg.funcRcptAmt_B.getValue();
        }

        AR_APPLY_INTFC_WRKTMsg inMsg = new AR_APPLY_INTFC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpKey, userId.concat(currentSystemTime));
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpSubPk, applyGrpSubPk);
        ZYPEZDItemValueSetter.setValue(inMsg.bizAppId, "NFCL3020");
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
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3020Scrn00_Click_Del(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Click_Del================================START", this);
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;
        NFCL3020SMsg globalMsg = (NFCL3020SMsg) sMsg;
        NFCL3020CommonLogic.copyPage(bizMsg, bizMsg.B, globalMsg.B);

        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.B.no(i).xxChkBox_B.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).rcptNum_B)) {
                    AR_RCPTTMsg inMsg = new AR_RCPTTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inMsg.rcptNum, globalMsg.B.no(i).rcptNum_B);
                    AR_RCPTTMsg delMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
                    if (!globalMsg.B.no(i).ezUpTime_B.getValue().equals(delMsg.ezUpTime.getValue()) || !globalMsg.B.no(i).ezUpTimeZone_B.getValue().equals(delMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo("ZZZM9004E");
                        return;
                    }
                    // Mod Start 2018/04/03 QC#21737-1
                    EZDTBLAccessor.logicalRemove(delMsg);
                    //EZDTBLAccessor.remove(delMsg);
                    // Mod End   2018/04/03 QC#21737-1
                    if (!RTNCD_NORMAL.equals(delMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM0032E");
                        return;
                    }
                }
            }
        }

        EZDDebugOutput.println(1, "doProcess_NFCL3020Scrn00_Click_Del================================END", this);
    }

    // START 2018/07/23 S.Ohki [QC#25928, DEL]
//    // QC#5521 ADD Start
//    /**
//     * Delete_Search Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NFCL3020Scrn00_DeleteSearch(NFCL3020CMsg bizMsg, NFCL3020SMsg glblMsg) {
//
//        String glblCmpyCd = getGlobalCompanyCode();
//
//        // set API parameter
//        NSZC033001PMsg pMsg = new NSZC033001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
//
//        // call Search Option API
//        if (NFCL3020CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
//            NFCL3020CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
//            bizMsg.srchOptPk_H.clear();
//        }
//    }
//
//    /**
//     * Save_Search Event
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     */
//    private void doProcess_NFCL3020Scrn00_SaveSearch(NFCL3020CMsg bizMsg, NFCL3020SMsg glblMsg) {
//
//        if (NFCL3020CommonLogic.isExistSaveSearchName(bizMsg)) {
//            bizMsg.srchOptNm_H.setErrorInfo(1, NFCM0865E, new String[] {"Save", "Search Option Name" });
//            return;
//        }
//
//        String glblCmpyCd = getGlobalCompanyCode();
//        String userId = getContextUserInfo().getUserId();
//
//        // set search option value to API parameter
//        NSZC033001PMsg pMsg = new NSZC033001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
//
//        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H) && NFCL3020CommonLogic.isSameSaveSearchName(bizMsg)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H);
//        }
//
//        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H);
//        } else {
//            NFCL3020CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
//        }
//
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);
//
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.arRcptSrcCd_H);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.arBatRcptNm_H);
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.arBatRcptStsCd_H);
//
//        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptCnt_H)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.arBatRcptCnt_H.getValue().toString());
//        }
//        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptAmt_H)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.arBatRcptAmt_H.getValue().toString());
//        }
//        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptDt_H)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.arBatRcptDt_H.getValue());
//        }
//
//        // call Search Option API
//        if (NFCL3020CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
//            NFCL3020CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H, pMsg.srchOptPk);
//            bizMsg.srchOptNm_H.clear();
//        }
//    }
//    // QC#5521 ADD End
    // END   2018/07/23 S.Ohki [QC#25928, DEL]
}
