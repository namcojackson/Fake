package business.blap.NFDL0040;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0040.common.NFDL0040CommonLogic;
import business.blap.NFDL0040.constant.NFDL0040Constant;
import business.db.CLT_DSPT_TRXTMsg;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;
import business.db.CLT_PRMS_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRMS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/04/21   Fujitsu         C.Naito         Update          QC#6269-3
 * 2016/04/21   Fujitsu         C.Naito         Update          QC#6269-2
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10786
 * 2016/08/26   Fujitsu         H.Ikeda         Update          QC#10786
 * 2018/04/18   Hitachi         Y.Takeno        Update          QC#20940
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0040BL06 extends S21BusinessHandler implements NFDL0040Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0040Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0040Scrn00_CMN_Submit(cMsg, sMsg);
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
    private void doProcess_NFDL0040Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_CMN_Submit================================START", this);
        updatePromise(cMsg, sMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0040Scrn00_CMN_Submit================================END", this);
    }

    private void updatePromise(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0040CMsg bizMsg = (NFDL0040CMsg) cMsg;
        NFDL0040SMsg globalMsg = (NFDL0040SMsg) sMsg;

        NFDL0040CommonLogic.copyPage(bizMsg, globalMsg);
        globalMsg.B.clearErrorInfo();

        int newCount = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).cltPrmsDtlPk_A)) {
                newCount++;
                if (newCount > 1) {
                    bizMsg.setMessageInfo("NFCM0765E");
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_PROMISE);
                    return;
                }
            }
        }

        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        boolean isMultiInv = bizMsg.xxTrxNumSrchTxt.getValue().contains(",");
        if (isMultiInv) {
            int newDisputeCount = 0;
            for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(globalMsg.B.no(i).cltDsptTrxPk_B)) {
                    newDisputeCount++;
                    if (newDisputeCount > 1) {
                        bizMsg.setMessageInfo("NFCM0765E");
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DISPUTE);
                        return;
                    }
                }
            }

            int openDisputeCount = NFDL0040CommonLogic.getOpenDisputeCount(getGlobalCompanyCode(), bizMsg);
            if (!ZYPCommonFunc.hasValue(globalMsg.B.no(0).cltDsptTrxPk_B) && openDisputeCount > 0) {
                bizMsg.setMessageInfo("ZZZM9015E");
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DISPUTE);
                return;
            }
        }
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]

        // [QC#6269-3] UPDATE start
        //has HeaderInvoice# ->Check,  not has HeaderInvoice# ->No Check
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.arTrxNum_H)) {
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
            if (NFDL0040CommonLogic.isInvalidDisputeAmt(getGlobalCompanyCode(), bizMsg, globalMsg)) {
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    bizMsg.B.no(i).dealCltDsptAmt_B.setErrorInfo(1, "NFCM0766E");
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, TAB_DISPUTE);
                return;
            }
        }
        // [QC#6269-3] UPDATE end

        // START 2023/05/26 S.Nakatani [QC#61271,ADD]
        boolean isError = false;
        // END 2023/05/26 S.Nakatani [QC#61271,ADD]
        /*************
         * Promise
         *************/
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!globalMsg.A.no(i).dealCltPrmsAmt_A.getValue().equals(globalMsg.A.no(i).dealCltPrmsAmt_AB.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).cltPrmsDtlPk_A)) {
                    CLT_PRMS_DTLTMsg inMsg = new CLT_PRMS_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsDtlPk, globalMsg.A.no(i).cltPrmsDtlPk_A);
                    CLT_PRMS_DTLTMsg updMsg = (CLT_PRMS_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
                    ZYPEZDItemValueSetter.setValue(updMsg.dealCltPrmsAmt, globalMsg.A.no(i).dealCltPrmsAmt_A);
                    if (updMsg.dealCltPrmsAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        ZYPEZDItemValueSetter.setValue(updMsg.cltPrmsStsCd, CLT_PRMS_STS.CANCELLED);
                    }
                    ZYPEZDItemValueSetter.setValue(updMsg.funcCltPrmsAmt, globalMsg.A.no(i).dealCltPrmsAmt_A);
                    // START 2017/01/16 H.Ikeda [QC#10786,ADD]
                    ZYPEZDItemValueSetter.setValue(updMsg.cltPrmsNoteTxt, globalMsg.A.no(i).cltPrmsNoteTxt_A);
                    // END   2017/01/16 H.Ikeda [QC#10786,ADD]

                    EZDTBLAccessor.update(updMsg);
                    if (!RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM0032E");
                        return;
                    }
                } else {
                    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
                    if (isMultiInv) {
                        isError = promiseMultiInvCreateInMsg(bizMsg, globalMsg, i);
                        if (isError) {
                            return;
                        }
                    } else {
                    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
                        String salesDate = ZYPDateUtil.getSalesDate();
                        CLT_PRMS_DTLTMsg inMsg = new CLT_PRMS_DTLTMsg();
                        BigDecimal cltPrmsDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ORACLE_SEQ_CLT_PRMS_DTL_SQ);
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsDtlPk, cltPrmsDtlPk);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltAcctCd, bizMsg.dsAcctNum_H);
                        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, bizMsg.arTrxNum_H);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsStsCd, CLT_PRMS_STS.COLLECTIBLE);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsDt, globalMsg.A.no(i).cltPrmsDt_A);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealCltPrmsAmt, globalMsg.A.no(i).dealCltPrmsAmt_A);
                        ZYPEZDItemValueSetter.setValue(inMsg.funcCltPrmsAmt, globalMsg.A.no(i).dealCltPrmsAmt_A);
                        // [QC#6269-2] UPDATE start
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).dealRmngBalGrsAmt_A2.getValue())) {
                            ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, globalMsg.A.no(i).dealRmngBalGrsAmt_A2);
                        } else {
                            ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
                        }
                        if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).funcRmngBalGrsAmt_A2.getValue())) {
                            ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, globalMsg.A.no(i).funcRmngBalGrsAmt_A2);
                        } else {
                            ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, BigDecimal.ZERO);
                        }
                        // [QC#6269-2] UPDATE end
                        ZYPEZDItemValueSetter.setValue(inMsg.cratUsrId, getContextUserInfo().getUserId());
                        ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsCratDt, salesDate);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsBrknFlg, ZYPConstant.FLG_OFF_N);
                        // START 2017/01/16 H.Ikeda [QC#10786,ADD]
                        ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsNoteTxt, globalMsg.A.no(i).cltPrmsNoteTxt_A);
                        // END 2017/01/16 H.Ikeda [QC#10786,ADD]

                        EZDTBLAccessor.create(inMsg);
                        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                            bizMsg.setMessageInfo("NFCM0032E");
                            return;
                        }

                    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
                    }
                    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
                }
            }
        }

        /*************
         * Dispute
         *************/
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            if (!globalMsg.B.no(i).dealCltDsptAmt_B.getValue().equals(globalMsg.B.no(i).dealCltDsptAmt_BB.getValue())) {
                if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).cltDsptTrxPk_B)) {
                    CLT_DSPT_TRXTMsg inMsg = new CLT_DSPT_TRXTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inMsg.cltDsptTrxPk, globalMsg.B.no(i).cltDsptTrxPk_B);
                    CLT_DSPT_TRXTMsg updMsg = (CLT_DSPT_TRXTMsg) EZDTBLAccessor.findByKey(inMsg);
                    ZYPEZDItemValueSetter.setValue(updMsg.dealCltDsptAmt, globalMsg.B.no(i).dealCltDsptAmt_B);
                    ZYPEZDItemValueSetter.setValue(updMsg.funcCltDsptAmt, globalMsg.B.no(i).dealCltDsptAmt_B);
                    // START 2016/08/26 K.Kojima [QC#10786,ADD]
                    ZYPEZDItemValueSetter.setValue(updMsg.cltDsptStsCd, globalMsg.B.no(i).cltDsptStsCd_B);
                    ZYPEZDItemValueSetter.setValue(updMsg.cltDsptNoteTxt, globalMsg.B.no(i).cltDsptNoteTxt_B);
                    // END 2016/08/26 K.Kojima [QC#10786,ADD]
                    EZDTBLAccessor.update(updMsg);
                    if (!RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                        bizMsg.setMessageInfo("NFCM0032E");
                        return;
                    }
                } else {
                    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
                    if (isMultiInv) {
                        isError = disputeMultiInvCreateInMsg(bizMsg, globalMsg, i);
                        if (isError) {
                            return;
                        }
                    } else {
                    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
                        String salesDate = ZYPDateUtil.getSalesDate();
                        CLT_DSPT_TRXTMsg inMsg = new CLT_DSPT_TRXTMsg();
                        BigDecimal cltDsptTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ORACLE_SEQ_CLT_DSPT_TRX_SQ);
                        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(inMsg.cltDsptTrxPk, cltDsptTrxPk);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltAcctCd, bizMsg.dsAcctNum_H);
                        ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, bizMsg.arTrxNum_H);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltDsptRsnCd, globalMsg.B.no(i).cltDsptRsnCd_B);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltDsptDt, salesDate);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealCltDsptAmt, globalMsg.B.no(i).dealCltDsptAmt_B);
                        ZYPEZDItemValueSetter.setValue(inMsg.funcCltDsptAmt, globalMsg.B.no(i).dealCltDsptAmt_B);
                        ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, globalMsg.B.no(i).dealRmngBalGrsAmt_B);
                        // [QC#6269-2] INSERT start
                        ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, globalMsg.B.no(i).funcRmngBalGrsAmt_B);
                        // [QC#6269-2] INSERT end
                        ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, globalMsg.B.no(i).dealRmngBalGrsAmt_B);
                        // [QC#6269-2] INSERT start
                        ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, globalMsg.B.no(i).funcRmngBalGrsAmt_B);
                        // [QC#6269-2] INSERT end
                        ZYPEZDItemValueSetter.setValue(inMsg.cltDsptCratUsrId, getContextUserInfo().getUserId());
                        // START 2016/08/26 K.Kojima [QC#10786,ADD]
                        ZYPEZDItemValueSetter.setValue(inMsg.cltDsptStsCd, globalMsg.B.no(i).cltDsptStsCd_B);
                        ZYPEZDItemValueSetter.setValue(inMsg.cltDsptNoteTxt, globalMsg.B.no(i).cltDsptNoteTxt_B);
                        // END 2016/08/26 K.Kojima [QC#10786,ADD]

                        EZDTBLAccessor.create(inMsg);
                        if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                            bizMsg.setMessageInfo("NFCM0032E");
                            return;
                        }
                    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
                    }
                    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
                }

                // START 2018/04/18 [QC#20940, ADD]
                if (!globalMsg.B.no(i).cltDsptStsCd_B.getValue().equals(globalMsg.B.no(i).cltDsptStsCd_BB.getValue())) {
                    boolean isOpen = false;
                    if (CLT_DSPT_STS.APPROVED.equals(globalMsg.B.no(i).cltDsptStsCd_B.getValue())) {
                        isOpen = true;
                    }
                    createCltNoteDtl(bizMsg, globalMsg.B.no(i), isOpen);
                }
                
                // END   2018/04/18 [QC#20940, ADD]
            }
        }
    }

    // START 2018/04/18 [QC#20940, ADD]
    private void createCltNoteDtl(NFDL0040CMsg bizMsg, NFDL0040_BSMsg bSMsg, boolean isOpenDispute) {
        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, getGlobalCompanyCode());
        if (isOpenDispute) {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.DISPUTE_OPENED);
        } else {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.DISPUTE_CLOSED);
        }
        cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);

        String createdBy = getContextUserInfo().getFirstName() + " " + getContextUserInfo().getLastName();

        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NFDL0040_CLT_DTL_NOTE_TXT, getGlobalCompanyCode());
        // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//        String dtlNoteTxt = String.format(dtlNoteTxtTmpl, 
//                bSMsg.cltDsptNoteTxt_B.getValue(), bizMsg.arTrxNum_H.getValue(), bSMsg.dealCltDsptAmt_B.getValue(), createdBy);
        String dtlNoteTxt = String.format(dtlNoteTxtTmpl, 
                bSMsg.cltDsptNoteTxt_B.getValue(), bizMsg.xxTrxNumSrchTxt.getValue(), bSMsg.dealCltDsptAmt_B.getValue(), createdBy);
        // END 2023/05/26 S.Nakatani [QC#61271,MOD]
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

    // START 2023/05/26 S.Nakatani [QC#61271,ADD]
    private boolean promiseMultiInvCreateInMsg(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg, int i) {
        boolean isError = false;
        CLT_PRMS_DTLTMsg inMsg = new CLT_PRMS_DTLTMsg();
        List<String> arTrxNumList = NFDL0040CommonLogic.splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        for (String arTrxNum : arTrxNumList) {
            String salesDate = ZYPDateUtil.getSalesDate();
            BigDecimal cltPrmsDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ORACLE_SEQ_CLT_PRMS_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsDtlPk, cltPrmsDtlPk);
            ZYPEZDItemValueSetter.setValue(inMsg.cltAcctCd, bizMsg.dsAcctNum_H);
            ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, arTrxNum);
            ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsStsCd, CLT_PRMS_STS.COLLECTIBLE);
            ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsDt, globalMsg.A.no(i).cltPrmsDt_A);
            BigDecimal dealRmngBalGrsAmt = (BigDecimal) NFDL0040CommonLogic.getArTrxBalData(getGlobalCompanyCode(), bizMsg, arTrxNum).get("DEAL_RMNG_BAL_GRS_AMT");
            ZYPEZDItemValueSetter.setValue(inMsg.dealCltPrmsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.funcCltPrmsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.cratUsrId, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsCratDt, salesDate);
            ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsBrknFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.cltPrmsNoteTxt, globalMsg.A.no(i).cltPrmsNoteTxt_A);

            EZDTBLAccessor.create(inMsg);
            if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                isError = true;
                return isError;
            }
        }
        return isError;
    }

    private boolean disputeMultiInvCreateInMsg(NFDL0040CMsg bizMsg, NFDL0040SMsg globalMsg, int i) {
        boolean isError = false;
        String salesDate = ZYPDateUtil.getSalesDate();
        CLT_DSPT_TRXTMsg inMsg = new CLT_DSPT_TRXTMsg();
        List<String> arTrxNumList = NFDL0040CommonLogic.splitAndToInvList(bizMsg.xxTrxNumSrchTxt.getValue());
        for (String arTrxNum : arTrxNumList) {
            BigDecimal cltDsptTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ORACLE_SEQ_CLT_DSPT_TRX_SQ);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inMsg.cltDsptTrxPk, cltDsptTrxPk);
            ZYPEZDItemValueSetter.setValue(inMsg.cltAcctCd, bizMsg.dsAcctNum_H);
            ZYPEZDItemValueSetter.setValue(inMsg.arTrxNum, arTrxNum);
            ZYPEZDItemValueSetter.setValue(inMsg.cltDsptRsnCd, globalMsg.B.no(i).cltDsptRsnCd_B);
            ZYPEZDItemValueSetter.setValue(inMsg.cltDsptDt, salesDate);
            BigDecimal dealRmngBalGrsAmt = (BigDecimal) NFDL0040CommonLogic.getArTrxBalData(getGlobalCompanyCode(), bizMsg, arTrxNum).get("DEAL_RMNG_BAL_GRS_AMT");
            ZYPEZDItemValueSetter.setValue(inMsg.dealCltDsptAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.funcCltDsptAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.dealRmngBalGrsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.funcRmngBalGrsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.dealOrigGrsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.funcOrigGrsAmt, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(inMsg.cltDsptCratUsrId, getContextUserInfo().getUserId());
            ZYPEZDItemValueSetter.setValue(inMsg.cltDsptStsCd, globalMsg.B.no(i).cltDsptStsCd_B);
            ZYPEZDItemValueSetter.setValue(inMsg.cltDsptNoteTxt, globalMsg.B.no(i).cltDsptNoteTxt_B);

            EZDTBLAccessor.create(inMsg);
            if (!RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E");
                isError = true;
                return isError;
            }
        }
        return isError;
    }
    // END 2023/05/26 S.Nakatani [QC#61271,ADD]
}
