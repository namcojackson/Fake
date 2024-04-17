package business.blap.NFCL0730.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;

import business.blap.NFCL0730.NFCL0730CMsg;
import business.blap.NFCL0730.NFCL0730Query;
import business.blap.NFCL0730.NFCL0730SMsg;
import business.blap.NFCL0730.constant.NFCL0730Constant;
import business.db.AR_ADJ_COA_INFOTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.HR_TTL_APVL_LIMITTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka        Create          Initial
 * 04/13/2016   CSAI            K.Uramori       Update          QC#7012
 * 2016/07/01   Hitachi         K.Kojima        Update          QC#11165
 * 2016/09/07   Hitachi         K.Kojima        Update          QC#13509
 * 2018/01/17   Fujitsu         T.Murai         Update          QC#20563
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2018/10/19   Fujitsu         T.Noguchi       Update          QC#28823
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 * 2023/01/27   Hitachi         T.Kuroda        Update          QC#61089
 * 2023/06/30   Hitachi         S.Fujita        Update          QC#60397
 * 2023/08/02   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */

public class NFCL0730CommonLogic implements NFCL0730Constant {
    
    /**
     * 
     * @param bizMsg
     * @param rcptNum
     * @return
     */
    public static boolean createArRcptForNewReceipt(NFCL0730CMsg bizMsg, String rcptNum, String arRcptBatNum, String arRcptBatSqNum, String tocCd, 
            String coaProdCd, String crMgrPsnCd, int index) {

        //---- start 2016/03/09 move to caller so that it won't be called multiple times.
        /*String arRcptBatNum = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_RCPT_BAT_NUM, bizMsg.glblCmpyCd.getValue());
        String arRcptBatSqNum = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM, bizMsg.glblCmpyCd.getValue());
        String tocCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD, bizMsg.glblCmpyCd.getValue());
        String coaProdCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD, bizMsg.glblCmpyCd.getValue());
        */
        //---- end 2016/03/09

        
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArRcptMsg.rcptNum.setValue(rcptNum);
        inArRcptMsg.rcptBatNum.setValue(arRcptBatNum);
        inArRcptMsg.rcptBatSqNum.setValue(arRcptBatSqNum);
        inArRcptMsg.arRcptTrxTpCd.setValue(AR_RCPT_TRX_TP.OFFSET);
        inArRcptMsg.arRcptTpCd.setValue(AR_RCPT_TP.OFFSET);
        inArRcptMsg.dealCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArRcptMsg.dealRcptAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealRfAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealVoidAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealRcptRmngBalAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.exchRate.setValue(BigDecimal.ONE);
        inArRcptMsg.funcCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArRcptMsg.funcRcptAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcApplyAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcRfAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcVoidAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcRvalVarAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcRcptRmngBalAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.rcptDt.setValue(bizMsg.A.no(index).glDt_A1.getValue());
        inArRcptMsg.glDt.setValue(bizMsg.A.no(index).glDt_A1.getValue());
        inArRcptMsg.payerCustCd.setValue(bizMsg.billToCustAcctCd_H2.getValue());
        inArRcptMsg.tocCd.setValue(tocCd);
        inArRcptMsg.coaProdCd.setValue(coaProdCd);
        inArRcptMsg.crAnlstPsnCd.setValue(crMgrPsnCd);
        inArRcptMsg.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS.APPLIED);
        inArRcptMsg.voidFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArRcptMsg.cratMethTpCd.setValue("M");
        inArRcptMsg.exptFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArRcptMsg.exptFirstBankChrgCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArRcptMsg.dealFirstExptChrgAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcFirstExptChrgAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.exptScdBankChrgCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArRcptMsg.dealScdExptChrgAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcScdExptChrgAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.dealNetRcptAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.funcNetRcptAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.fgnExchLossGainAmt.setValue(BigDecimal.ZERO);
        inArRcptMsg.ajeCratCpltFlg.setValue(ZYPConstant.FLG_OFF_N);
        // START 2018/09/20 T.Ogura [QC#28097,ADD]
        inArRcptMsg.arRcptSrcCd.setValue(AR_RCPT_SRC.SYSTEM_CREATED);
        // END   2018/09/20 T.Ogura [QC#28097,ADD]

        EZDTBLAccessor.create(inArRcptMsg);
        
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArRcptMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param rcptNum
     * @param glblCmpyCd
     * @return
     */
    public static boolean createArRcptDtlForNewReceipt(NFCL0730CMsg bizMsg, String rcptNum) {

        AR_RCPT_DTLTMsg inArRcptDtlMsg = new AR_RCPT_DTLTMsg();
        inArRcptDtlMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArRcptDtlMsg.rcptNum.setValue(rcptNum);
        inArRcptDtlMsg.rcptDtlNum.setValue(ZYPCommonFunc.leftPad(STR_1, MAX_LENGTH_4, PAD_STR_0));
        inArRcptDtlMsg.dealRcptDtlAmt.setValue(BigDecimal.ZERO);
        inArRcptDtlMsg.funcRcptDtlAmt.setValue(BigDecimal.ZERO);
        inArRcptDtlMsg.autoCratFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArRcptDtlMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(inArRcptDtlMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArRcptDtlMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param rcptNum
     * @param arTrxBalPk
     * @param actlExchRate
     * @return
     */
    public static boolean createArTrxBalNewReceipt(NFCL0730CMsg bizMsg, String rcptNum, BigDecimal arTrxBalPk, String tocCd, String coaProdCd, int index) {

        //---- start 2016/03/09 move to caller so that it won't be called multiple times.
        //String tocCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD, bizMsg.glblCmpyCd.getValue());
        //String coaProdCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD, bizMsg.glblCmpyCd.getValue());
        //---- end 2016/03/09

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);
        inArTrxBalMsg.arTrxNum.setValue(rcptNum);
        inArTrxBalMsg.arTrxTpCd.setValue(AR_TRX_TP.RECEIPT);
        inArTrxBalMsg.dealCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArTrxBalMsg.dealOrigGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyCashDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyCrAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealNetSlsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealFrtAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealTaxAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealInvDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealPrmoDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.dealRcptVoidAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.exchRate.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArTrxBalMsg.funcOrigGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyCashDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyCrAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcRvalVarAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcRmngBalGrsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcNetSlsAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcFrtAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcTaxAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcInvDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcPrmoDiscAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.funcRcptVoidAmt.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        inArTrxBalMsg.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS.APPLIED);
        inArTrxBalMsg.arTrxDt.setValue(bizMsg.A.no(index).glDt_A1.getValue());
        inArTrxBalMsg.glDt.setValue(bizMsg.A.no(index).glDt_A1.getValue());
        inArTrxBalMsg.billToCustCd.setValue(bizMsg.billToCustAcctCd_H2.getValue());
        inArTrxBalMsg.sellToCustCd.setValue(bizMsg.billToCustAcctCd_H2.getValue());
        inArTrxBalMsg.payerCustCd.setValue(bizMsg.billToCustAcctCd_H2.getValue());
        inArTrxBalMsg.tocCd.setValue(tocCd);
        inArTrxBalMsg.coaProdCd.setValue(coaProdCd);
        inArTrxBalMsg.exptFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArTrxBalMsg.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(inArTrxBalMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArTrxBalMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param rcptNum
     * @param arTrxBalPk
     * @param usrId
     * @param aGrKey
     * @param glblMsgCnt
     */
    public static boolean createArApplyIntfcWrkForNewlyHeader(NFCL0730CMsg bizMsg, String rcptNum, BigDecimal arTrxBalPk, String usrId, String aGrKey, int index) {

        String ezUpTimeR = null;
        String ezUpdTimeZoneR = null;
        String ezUpTimeT = null;
        String ezUpdTimeZoneT = null;

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArRcptMsg.rcptNum.setValue(rcptNum);
        AR_RCPTTMsg outArRcptMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inArRcptMsg);
        if (outArRcptMsg == null) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return false;
        } else {
            ezUpTimeR = outArRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneR = outArRcptMsg.ezUpTimeZone.getValue();
        }

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg outArTrxBalMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);
        if (outArTrxBalMsg == null) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return false;
        } else {
            ezUpTimeT = outArTrxBalMsg.ezUpTime.getValue();
            ezUpdTimeZoneT = outArTrxBalMsg.ezUpTimeZone.getValue();
        }

        // AR_APPLY_INTFC_WRK -Create
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(1);
        inArApplyIntfcWrkMsg.bizAppId.setValue("NFCL0660");
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("1");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(STR_1, MAX_LENGTH_4, PAD_STR_0));
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(rcptNum);
        inArApplyIntfcWrkMsg.rcptDtlNum.setValue("0");
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.A.no(index).glDt_A1.getValue());
        inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.billToCustAcctCd_H2.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(arTrxBalPk);
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeT);
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneT);
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.stdCcyCd.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        EZDTBLAccessor.create(inArApplyIntfcWrkMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArApplyIntfcWrkMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @param rcptNum
     * @param arTrxBalPk
     * @param usrId
     * @param aGrKey
     * @param glblMsgCnt
     * @return
     */
    public static boolean createArApplyIntfcWrkForNewlyDetail(NFCL0730CMsg bizMsg, NFCL0730SMsg globalMsg, String rcptNum, BigDecimal arTrxBalPk, 
            String usrId, String aGrKey, int index) {

        String ezUpTimeR = null;
        String ezUpdTimeZoneR = null;
        String ezUpTimeT = null;
        String ezUpdTimeZoneT = null;
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = null;

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        inArRcptMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArRcptMsg.rcptNum.setValue(rcptNum);
        AR_RCPTTMsg outArRcptMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inArRcptMsg);
        if (outArRcptMsg == null) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return false;
        } else {
            ezUpTimeR = outArRcptMsg.ezUpTime.getValue();
            ezUpdTimeZoneR = outArRcptMsg.ezUpTimeZone.getValue();
        }

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);
        AR_TRX_BALTMsg outArTrxBalMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);
        if (outArTrxBalMsg == null) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return false;
        } else {
            ezUpTimeT = outArTrxBalMsg.ezUpTime.getValue();
            ezUpdTimeZoneT = outArTrxBalMsg.ezUpTimeZone.getValue();
        }

        // AR_APPLY_INTFC_WRK -Create

        // Invoice
        inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
        
        String dealSqDtlNum = String.valueOf(1);
        // Common
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.bizAppId, "NFCL0660");
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procTpCd, "1");
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealSqNum, "00000001");
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealSqDtlNum, ZYPCommonFunc.leftPad(dealSqDtlNum, MAX_LENGTH_4, PAD_STR_0));
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.usrId, usrId);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptGlDt, bizMsg.A.no(index).glDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.payerCustCd, bizMsg.billToCustAcctCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptHdrLastUpdTs, ezUpTimeR);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptHdrTz, ezUpdTimeZoneR);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs, ezUpTimeT);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.rcptTrxBalTz, ezUpdTimeZoneT);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.grpInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invNum, bizMsg.arTrxNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arTrxTpCd, bizMsg.arTrxTpCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalPk, bizMsg.arTrxBalPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalLastUpdTs, bizMsg.ezUpTime_H2.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.invTrxBalTz, bizMsg.ezUpTimeZone_H2.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealCcyCd, bizMsg.stdCcyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.cashAppGlDt, bizMsg.A.no(index).glDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.arAdjTpCd, bizMsg.A.no(index).arAdjTpCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjAmt, bizMsg.A.no(index).dealApplyAmt_A1.getValue().negate());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.adjCmntTxt, bizMsg.A.no(index).adjCmntTxt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.tocCd, bizMsg.tocCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.coaProdCd, bizMsg.coaProdCd_H2.getValue());

        EZDTBLAccessor.create(inArApplyIntfcWrkMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArApplyIntfcWrkMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
            return false;
        }

        // 2016/03/09 This function to be called per line
        /*
        // Adjustment
        for (int i = 0; i < bizMsg.A.getValidCount() ; i++) {
            inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
            aGrSubKey = i + 2;
            dealSqDtlNum = String.valueOf(aGrSubKey);

            // Common
            inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
            inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(aGrSubKey);
            inArApplyIntfcWrkMsg.bizAppId.setValue("NFCL0730");
            inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.procTpCd.setValue("1");
            inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
            inArApplyIntfcWrkMsg.dealSqDtlNum.setValue(ZYPCommonFunc.leftPad(dealSqDtlNum, MAX_LENGTH_4, PAD_STR_0));
            inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
            inArApplyIntfcWrkMsg.usrId.setValue(usrId);
            inArApplyIntfcWrkMsg.rcptNum.setValue(rcptNum);
            inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(arTrxBalPk);
            inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(ezUpTimeR);
            inArApplyIntfcWrkMsg.rcptHdrTz.setValue(ezUpdTimeZoneR);
            inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(ezUpTimeT);
            inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(ezUpdTimeZoneT);
            inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
            inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.stdCcyCd.getValue());
            inArApplyIntfcWrkMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsg.acctDt_H2.getValue());
            inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

            inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.billToCustAcctCd_H2.getValue());
            inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsg.acctDt_H2.getValue());

            inArApplyIntfcWrkMsg.arTrxTpCd.setValue(bizMsg.arTrxTpCd_H2.getValue());
            inArApplyIntfcWrkMsg.invTrxBalPk.setValue(bizMsg.arTrxBalPk_H1.getValue());
            inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsg.ezUpTime_H2.getValue());
            inArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsg.ezUpTimeZone_H2.getValue());

            inArApplyIntfcWrkMsg.arAdjTrxTpCd.setValue(AR_ADJ_TRX_TP.ADJUSTMENT);
            inArApplyIntfcWrkMsg.arAdjTpCd.setValue(bizMsg.A.no(i).arAdjTpCd_A1.getValue());
            inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(bizMsg.A.no(i).dealApplyAmt_A1.getValue());
            inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);
            inArApplyIntfcWrkMsg.tocCd.setValue(bizMsg.tocCd_H2.getValue());
            inArApplyIntfcWrkMsg.coaProdCd.setValue(bizMsg.coaProdCd_H2.getValue());

            ZYPEZDItemValueSetter.setValue(inArApplyIntfcWrkMsg.adjCmntTxt, bizMsg.A.no(i).adjCmntTxt_A1.getValue());
            
            EZDTBLAccessor.create(inArApplyIntfcWrkMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArApplyIntfcWrkMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0032E", null);
                return false;
            }
        }
        */
        return true;
    }

    public static boolean doApplyBatchAPI(NFCL0730CMsg bizMsg, String aGrKey) {

        boolean retVal = true;

        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        apiMsg.applyGrpKey.setValue(aGrKey);
        apiMsg.dealSqNum.setValue("00000001");
        apiMsg.batDt.setValue(bizMsg.slsDt.getValue());
        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        String result = apiMsg.getReturnCode();

        // result == "1"
        if (APPLY_RTNCD_NORMAL.equals(result)) {
            bizMsg.setMessageInfo("NZZM0002I", new String[] {"Cash Application" });
            return true;

        } else {

            // result == "0"
            if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Unprocessing" });

                // result == "2"
            } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Cash Application Error" });

                // result == "3"
            } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Exclusion Error" });

                // result == "4"
            } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });

            } else {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Others Error" });
            }

            retVal = false;
        }
        return retVal;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean doCreditProfileUpdateAPI(NFCL0730CMsg bizMsg) {

        NFZC202001 api = new NFZC202001();
        NFZC202001PMsg apiMsg = new NFZC202001PMsg();
        apiMsg.xxModeCd.setValue("02");
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        apiMsg.billToCustCd.setValue(bizMsg.billToCustCd_H2.getValue());
        apiMsg.procDt.setValue(bizMsg.slsDt.getValue());

        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        if(apiMsg.xxMsgIdList.getValidCount()>0) {
            for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
                bizMsg.setMessageInfo(apiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                break;
            }
            return false;
        }
        return true;
    }

  //---- start add 2016/03/09
    public static String getCrMsgPsnCd(NFCL0730CMsg bizMsg) {
        
        DS_ACCT_CR_PRFLTMsg tmsg = new DS_ACCT_CR_PRFLTMsg();
        
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNum, bizMsg.billToCustAcctCd_H2.getValue());
        
        tmsg = (DS_ACCT_CR_PRFLTMsg) EZDTBLAccessor.findByKey(tmsg);
        
        if (tmsg == null) {
            return null;
        } else {
            return tmsg.crMgrPsnCd.getValue();
        }
    }
    //---- end 2016/03/09
    
    //---- start add 2016/03/10
    public static boolean searchHeader(NFCL0730CMsg bizMsg) {
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("arTrxNum01", bizMsg.arTrxNum_H1.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        
        AR_TRX_BALTMsgArray arTrxBalTMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(arTrxBalTMsg == null) {
            // START 2016/07/01 K.Kojima [QC#11165,MOD]
            // bizMsg.setMessageInfo("NFCM0041E");
            bizMsg.setMessageInfo("NFCM0150E");
            // END 2016/07/01 K.Kojima [QC#11165,MOD]
            return false;
        }
        if(arTrxBalTMsg.getValidCount() < 1) {
            // START 2016/07/01 K.Kojima [QC#11165,MOD]
            // bizMsg.setMessageInfo("NFCM0041E");
            bizMsg.setMessageInfo("NFCM0150E");
            // END 2016/07/01 K.Kojima [QC#11165,MOD]
            return false;
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H1, arTrxBalTMsg.no(0).dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H2, arTrxBalTMsg.no(0).dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.arCashApplyStsCd_H2, arTrxBalTMsg.no(0).arCashApplyStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H2, arTrxBalTMsg.no(0).ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H2, arTrxBalTMsg.no(0).ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_H2, arTrxBalTMsg.no(0).billToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_H2, arTrxBalTMsg.no(0).billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxTpCd_H2, arTrxBalTMsg.no(0).arTrxTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.tocCd_H2, arTrxBalTMsg.no(0).tocCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H2, arTrxBalTMsg.no(0).coaProdCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxBalPk_H1, arTrxBalTMsg.no(0).arTrxBalPk.getValue());
        // START 2016/09/07 K.Kojima [QC#13509,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dealApplyAdjAmt_H2, arTrxBalTMsg.no(0).dealApplyAdjAmt.getValue());
        // END 2016/09/07 K.Kojima [QC#13509,ADD]
        // START 2018/01/17 T.Murai [QC#20563,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.dealApplyAdjRsvdAmt_H2, arTrxBalTMsg.no(0).dealApplyAdjRsvdAmt.getValue());
        // END 2018/01/17 T.Murai [QC#20563,ADD]
        // 2018/10/19 QC#28823 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.adjDt_H1, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(bizMsg.dealArAdjAmt_H1, arTrxBalTMsg.no(0).dealRmngBalGrsAmt.getValue().negate());
        // 2018/10/19 QC#28823 ADd End

        return true;
    }
    
    public static void searchDetail(NFCL0730CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("arTrxNum", bizMsg.arTrxNum_H1.getValue());
        ssmParam.put("arApplytpCd", AR_APPLY_TP.ADJUSTMENT);
        
        S21SsmEZDResult ssmResult = NFCL0730Query.getInstance().getDetails(bizMsg, ssmParam);
        
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > bizMsg.B.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = bizMsg.B.length();
            }
            int i;
            for (i = 0; i < bizMsg.B.getValidCount(); i++) {
                if(i < bizMsg.A.length()) {
                    EZDMsg.copy(bizMsg.B.no(i), "B1", bizMsg.A.no(i), "A1");
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i);
        } else {
            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.A.setValidCount(0);
        }
    }
    
    //---- end 2016/03/10
    
    /**
     * 
     * @return
     */
    public static String getRcptNum() {
        String rcptNum = null;

        try {
            rcptNum = ZYPNumbering.getUniqueID(BIZAPL_RCPTNUMKEY);
        } catch (IllegalArgumentException iae) {
        }
        return rcptNum;
    }

    /**
     * 
     * @return
     */
    public static BigDecimal getArTrxBalPk() {
        BigDecimal arTrxBalPk = null;

        NFCNumbering afcNumbering = new NFCNumbering();
        NFXC3060Bean outGetNumber = afcNumbering.getNumber(ZYPOracleSeqAccessor.AR_TRX_BAL_SQ, "", AR_TRX_BAL_SQ_START_NUM);
        if (AR_TRX_BAL_SQ_RTNCD_NORMAL.equals(outGetNumber.getRtrnNo())) {
            arTrxBalPk = outGetNumber.getOraSqNo();
        }
        return arTrxBalPk;
    }
    
    
    //---- start 2016/04/13 QC#7012
    /**
     * @param glblCmpyCd String
     * @param funcList List<String>
     * @return AR_ADJ_TPTMsgArray
     */
    // START 2023/01/27 T.Kuroda [QC#61089, MOD]
    // public static S21SsmEZDResult findArAdjTpList(String glblCmpyCd) {
    public static S21SsmEZDResult findArAdjTpList(String glblCmpyCd, List<String> funcList) {
    // END   2023/01/27 T.Kuroda [QC#61089, MOD]

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("arAdjCatgCd", AR_ADJ_CATG.ADJUSTMENT);
        // START 2023/01/27 T.Kuroda [QC#61089, ADD]
        queryParam.put("funcList", funcList);
        // END   2023/01/27 T.Kuroda [QC#61089, ADD]

        S21SsmEZDResult dsResult = NFCL0730Query.getInstance().findArAdjTpForAdj(queryParam);

        if (dsResult.isCodeNormal()) {
            return dsResult;
        }
        
        return null;
    }
    //---- end 2016/04/13
    
    // START 2022/11/10 S.Naya [QC#57252,ADD]
    /**
     * glCodeCombinationCheck
     * @param bizMsg NFCL0730CMsg
     * @param coa String[]
     * @return boolean
     */
    public static boolean checkGlCodeCombination(NFCL0730CMsg bizMsg, String[] coa) {
        if (!check9Seg(bizMsg, coa)) {
            return false;
        }

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);

        int coaIdx = 0;
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, "NFCL0730");

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            String msgId;
            String msgTxt;
            msgId = apiMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue();
            bizMsg.xxCmntTxt_H1.clearErrorInfo();
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, msgId, new String[] {msgTxt });
            return false;
        } else {
            bizMsg.xxCmntTxt_H1.clearErrorInfo();
        }
        return true;
    }

    /**
     * Check 9 Segment
     * @param bizMsg NFCL0730CMsg
     * @param coa String[]
     * @return boolean
     */
    public static boolean check9Seg(NFCL0730CMsg bizMsg, String[] coa) {

        AR_ADJ_COA_INFOTMsg arAdjCoaInfoTMsg = new AR_ADJ_COA_INFOTMsg();
        int coaCmpyCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaCmpyCd").getDigit();
        int coaBrCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaBrCd").getDigit();
        int coaCcCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaCcCd").getDigit();
        int coaAcctCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaAcctCd").getDigit();
        int coaProdCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaProdCd").getDigit();
        int coaChCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaChCd").getDigit();
        int coaAfflCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaAfflCd").getDigit();
        int coaProjCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaProjCd").getDigit();
        int coaExtnCdDigit = arAdjCoaInfoTMsg.getAttr("adjCoaExtnCd").getDigit();
        int coaIdx = 0;

        if (coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {errMsg });
            return false;
        }
        if (coa[coaIdx++].length() > coaCmpyCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Company" });
            return false;
        }
        if (coa[coaIdx++].length() > coaBrCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Branch" });
            return false;
        }
        if (coa[coaIdx++].length() > coaCcCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Cost Center" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAcctCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Account" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProdCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Product" });
            return false;
        }
        if (coa[coaIdx++].length() > coaChCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Channel" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAfflCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Intercompany" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProjCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Merchandise" });
            return false;
        }
        if (coa[coaIdx++].length() > coaExtnCdDigit) {
            bizMsg.xxCmntTxt_H1.setErrorInfo(1, NFCM0833E, new String[] {"Business Unit" });
            return false;
        }
        return true;
    }

    /**
     * createArAdjCoaInfo
     * @param bizMsg
     * @param aGrKey
     * @param index
     */
    public static boolean createArAdjCoaInfo(NFCL0730CMsg bizMsg, String aGrKey, int index) {

        // AR_RCPT TimeStamp -Get
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkTMsg = new AR_APPLY_INTFC_WRKTMsg();
        inArApplyIntfcWrkTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArApplyIntfcWrkTMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkTMsg.applyGrpSubPk.setValue(BigDecimal.ONE);
        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkTMsg = (AR_APPLY_INTFC_WRKTMsg) EZDTBLAccessor.findByKey(inArApplyIntfcWrkTMsg);
        if (outArApplyIntfcWrkTMsg == null) {
            bizMsg.setMessageInfo("NFCM0079E", null);
            return false;
        }

        // Separate 9 segment
        int coaIdx = 0;
        String[] coa = bizMsg.A.no(index).xxCmntTxt_A1.getValue().split("\\.");

        // AR_ADJ_COA_INFOTMsg -Create
        AR_ADJ_COA_INFOTMsg inArAdjCoaInfoTMsg = new AR_ADJ_COA_INFOTMsg();
        inArAdjCoaInfoTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArAdjCoaInfoTMsg.arAdjNum.setValue(outArApplyIntfcWrkTMsg.arAdjNum.getValue());
        inArAdjCoaInfoTMsg.adjCoaCmpyCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaBrCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaCcCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaAcctCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaProdCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaChCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaAfflCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaProjCd.setValue(coa[coaIdx++]);
        inArAdjCoaInfoTMsg.adjCoaExtnCd.setValue(coa[coaIdx++]);

        EZDTBLAccessor.create(inArAdjCoaInfoTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inArAdjCoaInfoTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
            return false;
        }
        return true;
    }

    /**
     * get9SegDefault
     * @param bizMsg
     */
    public static void get9SegDefault(NFCL0730CMsg bizMsg) {
        
        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(bizMsg);

        if (tMsg == null) {
            bizMsg.coaCmpyCd_DF.clear();
            bizMsg.coaAfflCd_DF.clear();
            bizMsg.coaBrCd_DF.clear();
            bizMsg.coaCcCd_DF.clear();
            bizMsg.coaAcctCd_DF.clear();
            bizMsg.coaProdCd_DF.clear();
            bizMsg.coaChCd_DF.clear();
            bizMsg.coaProjCd_DF.clear();
            bizMsg.coaExtnCd_DF.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_DF, tMsg.coaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_DF, tMsg.coaAfflCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_DF, tMsg.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_DF, tMsg.coaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_DF, tMsg.coaAcctCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_DF, tMsg.coaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_DF, tMsg.coaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_DF, tMsg.coaProjCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_DF, tMsg.coaExtnCd.getValue());
        }
        
        return;
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    public static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(NFCL0730CMsg bizMsg) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsg.appFuncId.setValue(bizMsg.getBusinessID());

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // END   2022/11/10 S.Naya [QC#57252,ADD]

    // START 2023/06/30 S.Fujita [QC#60397,ADD]
    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param psnCd PSN Code
     * @return HR Title Name
     */
    public static String getHrTtlNm(String glblCmpyCd, String psnCd) {

        if (ZYPCommonFunc.hasValue(psnCd)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("psnCd", psnCd);
            // START 2023/08/02 S.Fujita [QC#60397,ADD]
            ssmParam.put("catgCd", AR_ADJ_CATG.ADJUSTMENT);
            // END 2023/08/02 S.Fujita [QC#60397,ADD]
            String hrTtlNm = NFCL0730Query.getInstance().getHrTtlNm(null, ssmParam);
            if (ZYPCommonFunc.hasValue(hrTtlNm)); {
                return  hrTtlNm;
            }
        }
        return null;
    }

    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param hrTtlNm HR Title Name
     * @return HR Title Approval Limit PK
     */
    public static BigDecimal getHrTtlApvlLimitPk(String glblCmpyCd, String hrTtlNm) {

        if (ZYPCommonFunc.hasValue(hrTtlNm)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("hrTtlNm", hrTtlNm);
            // START 2023/08/02 S.Fujita [QC#60397,ADD]
            ssmParam.put("catgCd", AR_ADJ_CATG.ADJUSTMENT);
            // END 2023/08/02 S.Fujita [QC#60397,ADD]
            BigDecimal hrTtlApvlLimitPk = NFCL0730Query.getInstance().getHrTtlApvlLimitPk(null, ssmParam);
            if (ZYPCommonFunc.hasValue(hrTtlApvlLimitPk)); {
                return  hrTtlApvlLimitPk;
            }
        }
        return null;
    }

    // END 2023/06/30 S.Fujita [QC#60397,ADD]
}
