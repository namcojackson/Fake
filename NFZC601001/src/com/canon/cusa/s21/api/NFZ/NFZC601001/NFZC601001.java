/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC601001;

import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.ACCT_MA_CD_EMANAGE;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.APPLY_GRP_KEY_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AR_BAT_RCPT_NM_EMNG;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AR_BAT_USR_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AR_RCPT_PROD_CD;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AR_RCPT_SRC_CD_DUMMY;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AR_RCPT_TOC_CD;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.BAT_DT_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.BILL_TO_CUST_CD_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.COLON;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.CR_CARD_AUTH_AMT_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.CR_CARD_CUST_REF_NUM;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.CR_CARD_TRX_TP_CD_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.CST_BIZ_APP_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.DEAL_SQ_NUM_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.DELIMITER;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.EIGTH_FIGURE;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.FUNC_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.GLBL_CMPY_CD_ADB;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.GLBL_CMPY_CD_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.HHMMSS;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.NFZC2020;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.NFZC3010;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.NWZC2030;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.PMTC_PRFL_ORD_OVRD_CD_NO;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.PMTC_TAX_INDEX_NUM_OFF;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.PROC_DT_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.SELL_TO_CUST_CD_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.SLS_DT_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.STR_UNDERSCORE;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.THREE_FIGURE;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.XX_INTFC_RTRN_STS_CD_ERR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.XX_INTFC_RTRN_STS_CD_NORMAL;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.XX_MODE_CD_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.XX_PMTC_ORD_ID_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.XX_PMTC_PRFL_ORD_OVRD_CD_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.XX_PMTC_TAX_IND_NUM_STR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AR_RCPT_CHK_NUM_HDR;
import static com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_BAT_RCPTTMsg;
import business.db.AR_RCPT_IN_PROC_WRKTMsg;
import business.db.AR_RCPT_RCV_WRKTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;
import business.parts.NFZC601001PMsg;
import business.parts.NFZC601001_InvoiceInfoListPMsg;
import business.parts.NFZC601001_xxMsgIdListPMsgArray;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.api.NFZ.NFZC601001.constant.NFZC601001Constant.MSG_ERROR_CD;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NFX.NFXC302001.NFCReceiptCreation;
import com.canon.cusa.s21.common.NFX.NFXC302001.NFXC3020Bean;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
/**
 * <pre>
 * Create Credit Card Receipt Base Data
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2016   Canon           Y.Miyauchi      Create          N/A
 * 2016/12/19   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/02/20   Hitachi         E.Kameishi      Update          QC#16802
 * 2017/03/10   Hitachi         E.Kameishi      Update          QC#18018
 * 2017/03/14   Hitachi         E.Kameishi      Update          QC#18018
 * 2017/04/19   Fujitsu         M.Yamada        Update          QC#18336
 * 2020/07/30   CITS            R.Kurahashi     Update          QC#57436
 *</pre>
 */
public class NFZC601001 extends S21ApiCommonBase {
    
    /** Global Company Code */
    private String glblCmpyCd = "";

    /** ssm Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Sales Date */
    private String slsDt = null;

    /** Receipt Toc Code */
    private String arRcptTocCd = null;

    /** Receipt Product Code */
    private String arRcptProdCd = null;

    /** User ID For Batch Process Of AR System */
    private String arBatUsrId = null;

    /** System Time */
    private String sysTime = null;

   /**
    * Init
    */
    public NFZC601001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        sysTime = ZYPDateUtil.getCurrentSystemTime(HHMMSS);
    }

    /**
     * @param param : NFZC061001PMsg
     * @param onBatchType : On Batch Type
     */
    public void execute(NFZC601001PMsg param, final ONBATCH_TYPE onBatchType) {
        // START 2017/03/10 E.Kameishi [QC#18018,MOD]

        if (doProcess(param, onBatchType)) {
            EZDConnectionMgr.getInstance().commit();
            setValue(param.xxIntfcRtrnStsCd, XX_INTFC_RTRN_STS_CD_NORMAL);
        } else {
            EZDConnectionMgr.getInstance().rollback();
            param.xxIntfcRtrnStsCd.setValue(XX_INTFC_RTRN_STS_CD_ERR);
        }
        // END 2017/03/10 E.Kameishi [QC#18018,MOD]
    }

    private boolean doProcess(NFZC601001PMsg param, final ONBATCH_TYPE onBatchType) {
        // Get Common Parameter
        // START 2017/03/10 E.Kameishi [QC#18018,MOD]
        if (getCommonParam(param)) {
            return false;
        }
        // END 2017/03/10 E.Kameishi [QC#18018,MOD]

        // Set CR_CARD_CHRG_FLG
        if (!ZYPCommonFunc.hasValue(param.xxCrCardChrgFlg)) {
            param.xxCrCardChrgFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Check Input Parameter
        if (checkParam(param)) {
            return false;
        }

        // Set AR_BAT_RCPT_AMT and BAT_RCPT_REC_CNT
        BigDecimal batRcptRecCnt = BigDecimal.valueOf(param.InvoiceInfoList.getValidCount());
        BigDecimal arBatRcptAmt = BigDecimal.ZERO;
        for (int i = 0; i < param.InvoiceInfoList.getValidCount(); i++) {
            arBatRcptAmt = arBatRcptAmt.add(param.InvoiceInfoList.no(i).dealApplyAmt.getValue());
        }

        // List For Updating Credit Profile
        List<String> billToCustCdList = new ArrayList<String>();

        // Get Sequence Number
        BigDecimal rcvSqPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_RCV_SQ);
        int rcptHdrNumSeq = 1;
        AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg = null;
        for (int i = 0; i < param.InvoiceInfoList.getValidCount(); i++) {
            // Get AR_TRX_BAL
            AR_TRX_BALTMsg arTRxBalTMsg = getArTrxBal(param.InvoiceInfoList.no(i).invNum.getValue());

            // Existence Check
            if (arTRxBalTMsg == null) {
                // START 2017/03/10 E.Kameishi [QC#18018,MOD]
                setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0659E.name()); // QC#18336
                return false;
                // END 2017/03/10 E.Kameishi [QC#18018,MOD]
            }

            // Check AR Cash Apply Status
            String arCashApplyStatus = arTRxBalTMsg.arCashApplyStsCd.getValue();
            if (!AR_CASH_APPLY_STS.PARTIAL.equals(arCashApplyStatus) && !AR_CASH_APPLY_STS.UNAPPLIED.equals(arCashApplyStatus)) {
                // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//                setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0039E.name());
//                String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0039E.name(), null);
//                setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
                setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0039E.name()); // QC#18336
                return false;
                // END 2017/03/10 E.Kameishi [QC#18018,MOD]
            }

            // Check Deal Remaining Balance Gross Amount
            BigDecimal dealRmngBalGrsAmt = arTRxBalTMsg.dealRmngBalGrsAmt.getValue();
            if (param.InvoiceInfoList.no(i).dealApplyAmt.getValue().compareTo(dealRmngBalGrsAmt) > 0) {
                // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//                setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0578E.name());
//                String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0578E.name(), null);
//                setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
                setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0578E.name()); // QC#18336
                return false;
                // END 2017/03/10 E.Kameishi [QC#18018,MOD]
            }

            // Create AR_RCPT_RCV_WRK
            String billToCustAcctCd = arTRxBalTMsg.billToCustAcctCd.getValue();
            arRcptRcvWrkTMsg = insArRcptRcv(param.InvoiceInfoList.no(i), rcptHdrNumSeq, billToCustAcctCd, rcvSqPk, batRcptRecCnt, arBatRcptAmt, param);
            // START 2017/03/10 E.Kameishi [QC#18018,ADD]
            if (arRcptRcvWrkTMsg == null) {
                return false;
            }
            // END 2017/03/10 E.Kameishi [QC#18018,ADD]
            // Set BILL_TO_CUST_CD List For Updating Credit Profile
            billToCustCdList.add(arTRxBalTMsg.billToCustCd.getValue());

            // Create AR_RCPT_IN_PROC_WRK
            if (insArRcptInProcWrk(arRcptRcvWrkTMsg, param)) {
                return false;
            }

            // Create AR_APPLY_INTFC_WRK
            if (insArApplyIntfcWrk(arRcptRcvWrkTMsg, rcptHdrNumSeq, arTRxBalTMsg, param)) {
                return false;
            }

            // Update AR_RCPT_RCV_WRK
            if (updateArRcptRcvWrk(arRcptRcvWrkTMsg, param)) {
                return false;
            }
            rcptHdrNumSeq++;
        }

        // Create Batch Receipt
        if (arRcptRcvWrkTMsg != null) {
            if (insArBatRcpt(arRcptRcvWrkTMsg, param)) {
                return false;
            }
        } else {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0040E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0040E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0040E.name()); // QC#18336
            return false;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Get Parameter For API
        List<Map<String, Object>> applyGrpKeyList = selectApplyGrpKeyByIntfcId();
        if (applyGrpKeyList.size() <= 0) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0570E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0570E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0570E.name()); // QC#18336
            return false;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Create AR_RCPT, AR_CASH_APP
        for (Map<String, Object> applyGrpKeyMap : applyGrpKeyList) {
            String applyGrpKey = (String) applyGrpKeyMap.get(NFCDbConst.APPLY_GRP_KEY);
            String rcvHdrNum = (String) applyGrpKeyMap.get(NFCDbConst.RCV_HDR_NUM);
            // Call NFXC3020 for creating AR_RCPT
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
            if (callNFXC3020(applyGrpKey, rcvHdrNum, param)) {
                return false;
            }
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
            // Call NFZC3010 for creating AR_CASH_APP
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
            if (callNFZC3010(param, applyGrpKey, rcvHdrNum)) {
                return false;
            }
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Update Credit Profile
        for (int i = 0; i < billToCustCdList.size(); i++) {
            String  billToCustCd = billToCustCdList.get(i);
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
            if (callNFZC2020(param, billToCustCd)) {
                return false;
            }
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Call NWZC2030
        if (ZYPConstant.FLG_ON_Y.equals(param.xxCrCardChrgFlg.getValue())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
            if (callNWZC2030(param, arRcptRcvWrkTMsg, arBatRcptAmt)) {
                return false;
            }
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return true;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    /**
     * @param msgIdListPMsgArray NFZC601001PMsg
     * @param msgId message id for return value.
     */
    private void setMsgIdList(NFZC601001_xxMsgIdListPMsgArray msgIdListPMsgArray, String msgId) {
        int cnt = msgIdListPMsgArray.getValidCount();
        setValue(msgIdListPMsgArray.no(cnt).xxMsgId, msgId);
        String msgTxt = getRtnMsg(msgId, null);
        setValue(msgIdListPMsgArray.no(cnt).xxMsgTxt, msgTxt);
        msgIdListPMsgArray.setValidCount(++cnt);
    }

    private boolean getCommonParam(NFZC601001PMsg param) {
        // Get Global Company Code
        // START 2017/03/10 E.Kameishi [QC#18018,MOD]
        this.glblCmpyCd = param.glblCmpyCd.getValue();
        // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            this.glblCmpyCd = GLBL_CMPY_CD_ADB;
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.slsDt)) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0031E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0031E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0031E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Get VAR_CHAR_CONST : Receipt TOC Code
        this.arRcptTocCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_TOC_CD, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.arRcptTocCd)) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0032E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0032E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0032E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Get VAR_CHAR_CONST : Receipt Product Code
        this.arRcptProdCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_PROD_CD, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.arRcptProdCd)) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0033E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0033E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0033E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        // Get VAR_CHAR_CONST : User ID for Batch Process of AR System
        this.arBatUsrId = ZYPCodeDataUtil.getVarCharConstValue(AR_BAT_USR_ID, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.arBatUsrId)) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0607E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0607E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0607E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean checkParam(NFZC601001PMsg param) {
        // Check CR_CARD_CUST_REF_NUM
        if (!ZYPCommonFunc.hasValue(param.crCardCustRefNum)) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0034E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0034E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0034E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }

        //  Check XX_CR_CARD_CHRG_FLG
        if (!ZYPConstant.FLG_ON_Y.equals(param.xxCrCardChrgFlg.getValue()) && !ZYPConstant.FLG_OFF_N.equals(param.xxCrCardChrgFlg.getValue())) {
            // START 2017/03/10 E.Kameishi [QC#18018,ADD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0035E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0035E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0035E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,ADD]
        }

        // Check InvoiceList
        if (param.InvoiceInfoList == null) {
            // START 2017/03/10 E.Kameishi [QC#18018,ADD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0036E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0036E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0036E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,ADD]
        } else {
            for (int i = 0; i < param.InvoiceInfoList.getValidCount(); i++) {
                // Check INV_NUM
                if (!ZYPCommonFunc.hasValue(param.InvoiceInfoList.no(i).invNum)) {
                    // START 2017/03/10 E.Kameishi [QC#18018,ADD]
//                    setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0037E.name());
//                    String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0037E.name(), null);
//                    setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
                    setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0037E.name()); // QC#18336
                    return true;
                    // END 2017/03/10 E.Kameishi [QC#18018,ADD]
                }
                // Check DEAL_APPLY_AMT existence
                if (!ZYPCommonFunc.hasValue(param.InvoiceInfoList.no(i).dealApplyAmt)) {
                    // START 2017/03/10 E.Kameishi [QC#18018,ADD]
//                    setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0038E.name());
//                    String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0038E.name(), null);
//                    setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
                    setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0038E.name()); // QC#18336
                    return true;
                    // END 2017/03/10 E.Kameishi [QC#18018,ADD]
                }
            }
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private AR_TRX_BALTMsg getArTrxBal(String invNum) {
        AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
        arTrxBalTMsg.setSQLID("014");
        arTrxBalTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        arTrxBalTMsg.setConditionValue("arTrxNum01", invNum);
        arTrxBalTMsg.setConditionValue("arTrxTpCd01", AR_TRX_TP.INVOICE);

        AR_TRX_BALTMsgArray arTrxBalTMsgArray = (AR_TRX_BALTMsgArray) S21ApiTBLAccessor.findByCondition(arTrxBalTMsg);

        if (arTrxBalTMsgArray.getValidCount() <= 0) {
            return null;
        }
        return arTrxBalTMsgArray.no(0);
    }

    private AR_RCPT_RCV_WRKTMsg insArRcptRcv(NFZC601001_InvoiceInfoListPMsg param, int rcptHdrNumSeq, String billToCustCd, BigDecimal rcvSqPk, BigDecimal batRcptRecCnt, BigDecimal arBatRcptAmt, NFZC601001PMsg pMsg) {
        int rcptDtlNumSeq = 1;

        AR_RCPT_RCV_WRKTMsg arRcptRcvWrkT = new AR_RCPT_RCV_WRKTMsg();
        setValue(arRcptRcvWrkT.glblCmpyCd, glblCmpyCd);
        setValue(arRcptRcvWrkT.rcvSqPk, rcvSqPk);
        setValue(arRcptRcvWrkT.rcvHdrNum, ZYPCommonFunc.leftPad(String.valueOf(rcptHdrNumSeq), EIGTH_FIGURE, BigDecimal.ZERO.toString()));
        setValue(arRcptRcvWrkT.rcvDtlNum, ZYPCommonFunc.leftPad(String.valueOf(rcptDtlNumSeq), THREE_FIGURE, BigDecimal.ZERO.toString()));
        setValue(arRcptRcvWrkT.rcvDt, this.slsDt);
        setValue(arRcptRcvWrkT.rcvFuncId, CST_BIZ_APP_ID);
        setValue(arRcptRcvWrkT.entryDt_01, this.slsDt);
        setValue(arRcptRcvWrkT.entryTm_01, sysTime);
        setValue(arRcptRcvWrkT.firstBillToCustCd, billToCustCd);
        setValue(arRcptRcvWrkT.depDt_01, this.slsDt);
        setValue(arRcptRcvWrkT.chkAmt, param.dealApplyAmt.getValue());
        setValue(arRcptRcvWrkT.ansiCrDrFlg, NFCConst.CST_FLAG_OFF);
        setValue(arRcptRcvWrkT.ansiPmtMethCd, NFCConst.CST_ANSI_PMT_METH_TP_CD_CR);
        setValue(arRcptRcvWrkT.apvlFlg_01, NFCConst.CST_FLAG_OFF);
        setValue(arRcptRcvWrkT.acctMaCd, ACCT_MA_CD_EMANAGE);
        setValue(arRcptRcvWrkT.entryTm_02, BigDecimal.ZERO.toString());
        setValue(arRcptRcvWrkT.updTm_01, BigDecimal.ZERO.toString());
        setValue(arRcptRcvWrkT.apvlFlg_02, ZYPConstant.FLG_OFF_N);
        setValue(arRcptRcvWrkT.inv30OrigNum, NFCCmnMethod.convertDbString(param.invNum.getValue()));
        setValue(arRcptRcvWrkT.chkRcptTotAmt, param.dealApplyAmt.getValue());
        setValue(arRcptRcvWrkT.invOrigTotAmt, BigDecimal.ZERO);
        setValue(arRcptRcvWrkT.invDiscTotAmt, BigDecimal.ZERO);
        setValue(arRcptRcvWrkT.entryTm_03, BigDecimal.ZERO.toString());
        setValue(arRcptRcvWrkT.updTm_02, BigDecimal.ZERO.toString());
        setValue(arRcptRcvWrkT.cashApplyAmt, BigDecimal.ZERO);
        setValue(arRcptRcvWrkT.rcvStsCd, NFCConst.CST_RCV_STS_CD_UNPROC);
        setValue(arRcptRcvWrkT.upldCratMethTpCd, NFCConst.CST_CREATE_METH_TP_CD_AUTO);
        setValue(arRcptRcvWrkT.exptFlg, NFCConst.CST_FLAG_OFF);
        setValue(arRcptRcvWrkT.arBatRcptNm, AR_BAT_RCPT_NM_EMNG + STR_UNDERSCORE + this.slsDt + STR_UNDERSCORE + rcvSqPk);
        setValue(arRcptRcvWrkT.payerCustCd, billToCustCd);
        setValue(arRcptRcvWrkT.custInvNum, NFCCmnMethod.convertDbString(param.invNum.getValue()));
        setValue(arRcptRcvWrkT.arRcptSrcCd, AR_RCPT_SRC_CD_DUMMY);
        setValue(arRcptRcvWrkT.arLockBoxFileNm, AR_BAT_RCPT_NM_EMNG + STR_UNDERSCORE + this.slsDt + STR_UNDERSCORE + rcvSqPk);
        setValue(arRcptRcvWrkT.batRcptRecCnt, batRcptRecCnt);
        setValue(arRcptRcvWrkT.batRcptTotAmt, arBatRcptAmt);
        
        // START 2020/07/30 R.Kurahashi [QC#57436,ADD]
        setValue(arRcptRcvWrkT.chk30Num, S21StringUtil.concatStrings(AR_RCPT_CHK_NUM_HDR, ZYPExtnNumbering.getUniqueID(this.glblCmpyCd, AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM)));
        // END 2020/07/30 R.Kurahashi [QC#57436,ADD]

        // Insert Start
        S21ApiTBLAccessor.insert(arRcptRcvWrkT);

        // Insert Result Check
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arRcptRcvWrkT.getReturnCode())) {
            // START 2017/03/10 E.Kameishi [QC#18018,ADD]
//            setValue(pMsg.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0040E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0040E.name(), null);
//            setValue(pMsg.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(pMsg.xxMsgIdList, MSG_ERROR_CD.NFZM0040E.name()); // QC#18336
            return null;
            // END 2017/03/10 E.Kameishi [QC#18018,ADD]
        }

        return arRcptRcvWrkT;
    }

    private boolean insArRcptInProcWrk(AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg, NFZC601001PMsg param) {
        String glblCmpyCdWrk = arRcptRcvWrkTMsg.glblCmpyCd.getValue();
        BigDecimal dealRcptAmtWrk = arRcptRcvWrkTMsg.chkAmt.getValue();
        String glDtWrk = arRcptRcvWrkTMsg.depDt_01.getValue();
        // Get GLBL_CMPYTMsg for CCY Code
        GLBL_CMPYTMsg inGlblCmpyTMsg = new GLBL_CMPYTMsg();
        setValue(inGlblCmpyTMsg.glblCmpyCd, glblCmpyCdWrk);
        GLBL_CMPYTMsg outGlblCmpyTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByKey(inGlblCmpyTMsg);
        String ccyCd = outGlblCmpyTMsg.stdCcyCd.getValue();

        // Acquisition of Afxc3070BeanglblCmpyCdWrk
        NFXC3070Bean rtnBean = getAfxc3070Bean(glblCmpyCdWrk, ccyCd, dealRcptAmtWrk, glDtWrk, param);
        if (rtnBean == null) {
            return true;
        }
        BigDecimal exchRate = rtnBean.getExchRate();
        String funcCcyCd = rtnBean.getFuncCcyCd();
        BigDecimal funcRcptAmt = rtnBean.getFuncAmt();
        rtnBean = getAfxc3070Bean(glblCmpyCdWrk, ccyCd, (BigDecimal) arRcptRcvWrkTMsg.chkRcptTotAmt.getValue(), glDtWrk, param);
        if (rtnBean == null) {
            return true;
        }
        BigDecimal funcRcptDtlAmt = rtnBean.getFuncAmt();

        AR_RCPT_IN_PROC_WRKTMsg arRcptInProcWrkT = new AR_RCPT_IN_PROC_WRKTMsg();
        setValue(arRcptInProcWrkT.glblCmpyCd, glblCmpyCdWrk);
        setValue(arRcptInProcWrkT.rcptInProcSqPk, arRcptRcvWrkTMsg.rcvSqPk);
        setValue(arRcptInProcWrkT.rcvHdrNum, arRcptRcvWrkTMsg.rcvHdrNum);
        setValue(arRcptInProcWrkT.rcvDtlNum, arRcptRcvWrkTMsg.rcvDtlNum);
        setValue(arRcptInProcWrkT.rcvDt, arRcptRcvWrkTMsg.rcvDt);
        setValue(arRcptInProcWrkT.rcvTm, arRcptRcvWrkTMsg.entryTm_01);
        setValue(arRcptInProcWrkT.rcvFuncId, arRcptRcvWrkTMsg.rcvFuncId);
        setValue(arRcptInProcWrkT.rcptStsCd, NFCConst.CST_RCPT_STS_CD_NORM);
        setValue(arRcptInProcWrkT.rcptBatNum, arRcptRcvWrkTMsg.batNum);
        setValue(arRcptInProcWrkT.rcptBatSqNum, arRcptRcvWrkTMsg.rcptBatSqNum);
        setValue(arRcptInProcWrkT.arRcptTrxTpCd, NFCConst.CST_AR_RCPT_TRX_TP_CD_00);
        setValue(arRcptInProcWrkT.arRcptTpCd, NFCConst.CST_AR_RCPT_TP_CD_04);
        setValue(arRcptInProcWrkT.dealCcyCd, ccyCd);
        setValue(arRcptInProcWrkT.dealRcptAmt, arRcptRcvWrkTMsg.chkAmt);
        setValue(arRcptInProcWrkT.dealApplyAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.dealRfAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.dealVoidAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.dealRcptRmngBalAmt, arRcptRcvWrkTMsg.chkAmt);
        setValue(arRcptInProcWrkT.exchRate, exchRate);
        setValue(arRcptInProcWrkT.funcCcyCd, funcCcyCd);
        setValue(arRcptInProcWrkT.funcRcptAmt, funcRcptAmt);
        setValue(arRcptInProcWrkT.funcApplyAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.funcRfAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.funcVoidAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.funcRcptRmngBalAmt, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.rcptDt, arRcptRcvWrkTMsg.depDt_01);
        setValue(arRcptInProcWrkT.glDt, arRcptRcvWrkTMsg.depDt_01);
        setValue(arRcptInProcWrkT.rcptChkNum, arRcptRcvWrkTMsg.chk30Num);
        setValue(arRcptInProcWrkT.rcptChkDt, arRcptRcvWrkTMsg.depDt_01);
        setValue(arRcptInProcWrkT.payerCustCd, arRcptRcvWrkTMsg.firstBillToCustCd);
        setValue(arRcptInProcWrkT.tocCd, this.arRcptTocCd);
        setValue(arRcptInProcWrkT.coaProdCd, this.arRcptProdCd);
        setValue(arRcptInProcWrkT.rcptFirstCmntTxt, arRcptRcvWrkTMsg.upldRcptFirstCmntTxt);
        setValue(arRcptInProcWrkT.rcptScdCmntTxt, arRcptRcvWrkTMsg.upldRcptScdCmntTxt);
        setValue(arRcptInProcWrkT.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
        setValue(arRcptInProcWrkT.voidFlg, ZYPConstant.FLG_OFF_N);
        setValue(arRcptInProcWrkT.rfExchRate, BigDecimal.ZERO);
        setValue(arRcptInProcWrkT.rcvTs, arRcptRcvWrkTMsg.entryDt_01.getValue() + arRcptRcvWrkTMsg.entryTm_01.getValue());
        setValue(arRcptInProcWrkT.rcvSqPk, arRcptRcvWrkTMsg.rcvSqPk);
        setValue(arRcptInProcWrkT.rcvTrxTpCd, NFCConst.CST_AR_TRX_TP_CD_INVOICE);
        setValue(arRcptInProcWrkT.rcvTrxNum, arRcptRcvWrkTMsg.inv30OrigNum);
        setValue(arRcptInProcWrkT.arEdiSendBankCd, arRcptRcvWrkTMsg.acctMaCd);
        setValue(arRcptInProcWrkT.ediRcvCustNm, arRcptRcvWrkTMsg.ansiNm);
        setValue(arRcptInProcWrkT.cratMethTpCd, arRcptRcvWrkTMsg.upldCratMethTpCd);
        setValue(arRcptInProcWrkT.invNum, arRcptRcvWrkTMsg.inv30OrigNum);
        setValue(arRcptInProcWrkT.arCustRefNum, arRcptRcvWrkTMsg.inv30OrigNum);
        setValue(arRcptInProcWrkT.arCustRefTpCd, NFCConst.CST_AR_TRX_TP_CD_INVOICE);
        setValue(arRcptInProcWrkT.dealRcptDtlAmt, arRcptRcvWrkTMsg.chkRcptTotAmt);
        setValue(arRcptInProcWrkT.funcRcptDtlAmt, funcRcptDtlAmt);
        setValue(arRcptInProcWrkT.autoCratFlg, ZYPConstant.FLG_OFF_N);
        setValue(arRcptInProcWrkT.dupErrCd, NFCConst.DUP_ERR_CD_NOMAL);
        setValue(arRcptInProcWrkT.grpInvFlg, ZYPConstant.FLG_OFF_N);
        setValue(arRcptInProcWrkT.exptFlg, arRcptRcvWrkTMsg.exptFlg);
        setValue(arRcptInProcWrkT.arBatRcptNm, arRcptRcvWrkTMsg.arBatRcptNm);
        setValue(arRcptInProcWrkT.remDsBankAcctPk, arRcptRcvWrkTMsg.remDsBankAcctPk);
        setValue(arRcptInProcWrkT.custDsBankAcctPk, arRcptRcvWrkTMsg.custDsBankAcctPk);
        setValue(arRcptInProcWrkT.custAcctRefNum, arRcptRcvWrkTMsg.custAcctRefNum);
        setValue(arRcptInProcWrkT.custRcptNum, arRcptRcvWrkTMsg.custRcptNum);
        setValue(arRcptInProcWrkT.custInvNum, arRcptRcvWrkTMsg.custInvNum);
        setValue(arRcptInProcWrkT.arRcptSrcCd, arRcptRcvWrkTMsg.arRcptSrcCd);
        setValue(arRcptInProcWrkT.arCustIdStsCd, arRcptRcvWrkTMsg.arCustIdStsCd);
        setValue(arRcptInProcWrkT.arLockBoxFileNm, arRcptRcvWrkTMsg.arLockBoxFileNm);
        setValue(arRcptInProcWrkT.arLockBoxCd, arRcptRcvWrkTMsg.arLockBoxCd);
        setValue(arRcptInProcWrkT.batRcptRecCnt, arRcptRcvWrkTMsg.batRcptRecCnt);
        setValue(arRcptInProcWrkT.batRcptTotAmt, arRcptRcvWrkTMsg.batRcptTotAmt);

        // Insert Start
        S21ApiTBLAccessor.insert(arRcptInProcWrkT);

        // Insert Result Check
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arRcptInProcWrkT.getReturnCode())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0041E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0041E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0041E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean insArApplyIntfcWrk(AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg, int applyGrpSubKey, AR_TRX_BALTMsg arTRxBalTMsg, NFZC601001PMsg param) {
        String glblCmpyCdWrk = arRcptRcvWrkTMsg.glblCmpyCd.getValue();
        // Get GLBL_CMPYTMsg for CCY Code
        GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, glblCmpyCdWrk, glblCmpyCdWrk);

        AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkT = new AR_APPLY_INTFC_WRKTMsg();
        setValue(arApplyIntfcWrkT.glblCmpyCd, glblCmpyCdWrk);
        setValue(arApplyIntfcWrkT.applyGrpKey, CST_BIZ_APP_ID + arRcptRcvWrkTMsg.rcvSqPk.getValue());
        setValue(arApplyIntfcWrkT.applyGrpSubPk, BigDecimal.valueOf(applyGrpSubKey));
        setValue(arApplyIntfcWrkT.bizAppId, CST_BIZ_APP_ID);
        setValue(arApplyIntfcWrkT.intfcId, CST_BIZ_APP_ID);
        setValue(arApplyIntfcWrkT.upldCsvRqstPk, arRcptRcvWrkTMsg.rcvSqPk);
        setValue(arApplyIntfcWrkT.procTpCd, NFCConst.CST_PROC_TP_CD_NEW);
        setValue(arApplyIntfcWrkT.dealSqNum, arRcptRcvWrkTMsg.rcvHdrNum);
        setValue(arApplyIntfcWrkT.dealSqDtlNum, arRcptRcvWrkTMsg.rcvDtlNum);
        setValue(arApplyIntfcWrkT.procStsCd, NFCConst.CST_PROC_STS_CD_UNPROC);
        setValue(arApplyIntfcWrkT.usrId, this.arBatUsrId);
        setValue(arApplyIntfcWrkT.rcvFuncId, arRcptRcvWrkTMsg.rcvFuncId);
        setValue(arApplyIntfcWrkT.rcptInProcSqPk, arRcptRcvWrkTMsg.rcvSqPk);
        setValue(arApplyIntfcWrkT.rcvHdrNum, arRcptRcvWrkTMsg.rcvHdrNum);
        setValue(arApplyIntfcWrkT.rcvDtlNum, arRcptRcvWrkTMsg.rcvDtlNum);
        setValue(arApplyIntfcWrkT.rcptGlDt, arRcptRcvWrkTMsg.depDt_01);
        setValue(arApplyIntfcWrkT.payerCustCd, arRcptRcvWrkTMsg.payerCustCd);
        setValue(arApplyIntfcWrkT.rcptTrxBalPk, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.grpInvFlg, ZYPConstant.FLG_OFF_N);
        setValue(arApplyIntfcWrkT.invNum, arRcptRcvWrkTMsg.inv30OrigNum);
        setValue(arApplyIntfcWrkT.invTrxBalPk, arTRxBalTMsg.arTrxBalPk);
        setValue(arApplyIntfcWrkT.invTrxBalLastUpdTs, arTRxBalTMsg.ezUpTime);
        setValue(arApplyIntfcWrkT.invTrxBalTz, arTRxBalTMsg.ezUpTimeZone);
        setValue(arApplyIntfcWrkT.crTrxBalPk, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.dealCcyCd, glblCmpyT.stdCcyCd.getValue());
        setValue(arApplyIntfcWrkT.dealApplyAmt, arRcptRcvWrkTMsg.chkRcptTotAmt);
        setValue(arApplyIntfcWrkT.cashAppGlDt, arRcptRcvWrkTMsg.depDt_01);
        setValue(arApplyIntfcWrkT.cashDiscPct, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.dealCashDiscAmt, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.dealOnAcctCashAmt, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.dealApplyAdjAmt, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
        setValue(arApplyIntfcWrkT.tocCd, this.arRcptTocCd);
        setValue(arApplyIntfcWrkT.coaProdCd, this.arRcptProdCd);

        // Insert Start
        S21ApiTBLAccessor.insert(arApplyIntfcWrkT);

        // Insert Result Check
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arApplyIntfcWrkT.getReturnCode())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0565E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0565E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0565E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean insArBatRcpt(AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg, NFZC601001PMsg param) {
        AR_BAT_RCPTTMsg arBatRcptT = new AR_BAT_RCPTTMsg();

        setValue(arBatRcptT.glblCmpyCd, arRcptRcvWrkTMsg.glblCmpyCd);
        setValue(arBatRcptT.arBatRcptNm, arRcptRcvWrkTMsg.arBatRcptNm);
        setValue(arBatRcptT.arRcptSrcCd, arRcptRcvWrkTMsg.arRcptSrcCd);
        setValue(arBatRcptT.arBatRcptDt, arRcptRcvWrkTMsg.rcvDt);
        setValue(arBatRcptT.arBatRcptCnt, arRcptRcvWrkTMsg.batRcptRecCnt);
        setValue(arBatRcptT.arBatRcptAmt, arRcptRcvWrkTMsg.batRcptTotAmt);
        setValue(arBatRcptT.arBatRcptStsCd, AR_BAT_RCPT_STS.CLOSED);
        setValue(arBatRcptT.arLockBoxFileNm, arRcptRcvWrkTMsg.arLockBoxFileNm);
        setValue(arBatRcptT.arLockBoxCd, arRcptRcvWrkTMsg.arLockBoxCd);
        setValue(arBatRcptT.arLockBoxBatNum, arRcptRcvWrkTMsg.batNum);
        setValue(arBatRcptT.remDsBankAcctPk, arRcptRcvWrkTMsg.remDsBankAcctPk);
        setValue(arBatRcptT.custDsBankAcctPk, arRcptRcvWrkTMsg.custDsBankAcctPk);

        // Insert Start
        S21ApiTBLAccessor.insert(arBatRcptT);

        // Insert Result Check
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arBatRcptT.getReturnCode())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFZM0042E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFZM0042E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFZM0042E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private NFXC3070Bean getAfxc3070Bean(String mapGblCmpyCd, String dealCcyCd, BigDecimal dealRcptAmt, String glDt, NFZC601001PMsg param) {

        NFCCurrencyConversion afcCcyCnv = new NFCCurrencyConversion();

        NFXC3070Bean rtnBean = afcCcyCnv.convertCurrency(mapGblCmpyCd, dealCcyCd, dealRcptAmt, glDt, null);

        if (NFCConst.CST_RTN_CD_ERR.equals(rtnBean.getRtrnCd())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0550E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0550E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0550E.name()); // QC#18336
            return null;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
            }
        return rtnBean;
    }

    private List<Map<String, Object>> selectApplyGrpKeyByIntfcId() {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("intfcId", CST_BIZ_APP_ID);
        ssmParam.put("procStsCd", NFCConst.CST_PROC_STS_CD_UNPROC);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getApplyGrpKeyByIntfcId", ssmParam);
    }

    private static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    private boolean updateArRcptRcvWrk(AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg, NFZC601001PMsg param) {
        AR_RCPT_RCV_WRKTMsg arRcptRcvWrkT = new AR_RCPT_RCV_WRKTMsg();
        setValue(arRcptRcvWrkT.glblCmpyCd, arRcptRcvWrkTMsg.glblCmpyCd);
        setValue(arRcptRcvWrkT.rcvSqPk, arRcptRcvWrkTMsg.rcvSqPk);
        setValue(arRcptRcvWrkT.rcvHdrNum, arRcptRcvWrkTMsg.rcvHdrNum);
        setValue(arRcptRcvWrkT.rcvDtlNum, arRcptRcvWrkTMsg.rcvDtlNum);

        arRcptRcvWrkT = (AR_RCPT_RCV_WRKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(arRcptRcvWrkT);
        setValue(arRcptRcvWrkT.rcvStsCd, NFCConst.CST_RCV_STS_CD_PROC);
        // Update Start
        S21ApiTBLAccessor.update(arRcptRcvWrkT);

        // Update Result Check
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(arRcptRcvWrkT.getReturnCode())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0608E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0608E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0608E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean callNFXC3020(String applyGrpKey, String rcvHdrNum, NFZC601001PMsg param) {
        NFCReceiptCreation afcReceiptCreation = new NFCReceiptCreation();
        // START 2020/07/30 R.Kurahashi [QC#57436,MOD]
        //NFXC3020Bean nfxc3020bean = afcReceiptCreation.execute(this.glblCmpyCd, applyGrpKey, this.slsDt, rcvHdrNum);
        String crCardLastDigitNum = param.crCardLastDigitNum.getValue();
        NFXC3020Bean nfxc3020bean;
        if (ZYPCommonFunc.hasValue(crCardLastDigitNum)) {
            nfxc3020bean = afcReceiptCreation.execute(this.glblCmpyCd, applyGrpKey, this.slsDt, rcvHdrNum, null, crCardLastDigitNum);
        } else {
            nfxc3020bean = afcReceiptCreation.execute(this.glblCmpyCd, applyGrpKey, this.slsDt, rcvHdrNum);
        }
        // END 2020/07/30 R.Kurahashi [QC#57436,MOD]

        if (nfxc3020bean == null || NFCConst.CST_RTN_CD_ERR.equals(nfxc3020bean.getRtrnNo())) {
            // START 2017/03/10 E.Kameishi [QC#18018,MOD]
//            setValue(param.xxMsgIdList.no(0).xxMsgId, MSG_ERROR_CD.NFCM0561E.name());
//            String msgTxt = getRtnMsg(MSG_ERROR_CD.NFCM0561E.name(), null);
//            setValue(param.xxMsgIdList.no(0).xxMsgTxt, msgTxt);
            setMsgIdList(param.xxMsgIdList, MSG_ERROR_CD.NFCM0561E.name()); // QC#18336
            return true;
            // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        }
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean callNFZC3010(NFZC601001PMsg param, String applyGrpKey, String rcvHdrNum) {
        NFZC301001PMsg pMsg = new NFZC301001PMsg();
        NFZC301001 nfzc301001 = new NFZC301001();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.applyGrpKey, applyGrpKey);
        setValue(pMsg.dealSqNum, rcvHdrNum);
        setValue(pMsg.batDt, this.slsDt);

        nfzc301001.execute(pMsg, ONBATCH_TYPE.BATCH);
        // START 2017/03/14 E.Kameishi [QC#18018,ADD]
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//                setValue(param.xxMsgIdList.no(i).xxMsgId, pMsg.xxMsgIdList.no(i).xxMsgId);
//                String msgTxt = getRtnMsg(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), S21ApiUtil.getXxMsgList(pMsg).get(i).getXxMsgPrmArray());
//                setValue(param.xxMsgIdList.no(i).xxMsgTxt, msgTxt);
                setMsgIdList(param.xxMsgIdList, pMsg.xxMsgIdList.no(i).xxMsgId.getValue()); // QC#18336
            }

            StringBuilder key = new StringBuilder();
            key.append(GLBL_CMPY_CD_STR + COLON + this.glblCmpyCd);
            key.append(DELIMITER + APPLY_GRP_KEY_STR + COLON + applyGrpKey);
            key.append(DELIMITER + DEAL_SQ_NUM_STR + COLON +  rcvHdrNum);
            key.append(DELIMITER + BAT_DT_STR + COLON +  this.slsDt);
            S21InfoLogOutput.println(FUNC_ID + COLON + NFZC3010 + DELIMITER + key.toString());

            return true;
        }
        // END 2017/03/14 E.Kameishi [QC#18018,ADD]
        // START 2017/03/14 E.Kameishi [QC#18018,DEL]
        //if (!NFCConst.CST_NFZC301001_RTN_CD_CPLT.equals(pMsg.getReturnCode())) {
        //    // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        //    msgMap.addXxMsgId(MSG_ERROR_CD.NFCM0560E.name());
        //    return true;
        //    // END 2017/03/10 E.Kameishi [QC#18018,ADD]
        //}
        // END 2017/03/14 E.Kameishi [QC#18018,DEL]
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean callNFZC2020(NFZC601001PMsg param, String billToCustCd) {
        NFZC202001PMsg pMsg = new NFZC202001PMsg();
        NFZC202001 nfzc202001 = new NFZC202001();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
        setValue(pMsg.billToCustCd, billToCustCd);
        setValue(pMsg.procDt, this.slsDt);

        nfzc202001.execute(pMsg, ONBATCH_TYPE.BATCH);
        // START 2017/03/14 E.Kameishi [QC#18018,ADD]
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//                setValue(param.xxMsgIdList.no(i).xxMsgId, pMsg.xxMsgIdList.no(i).xxMsgId);
//                String msgTxt = getRtnMsg(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), S21ApiUtil.getXxMsgList(pMsg).get(i).getXxMsgPrmArray());
//                setValue(param.xxMsgIdList.no(i).xxMsgTxt, msgTxt);
                setMsgIdList(param.xxMsgIdList, pMsg.xxMsgIdList.no(i).xxMsgId.getValue()); // QC#18336
            }

            StringBuilder key = new StringBuilder();
            key.append(GLBL_CMPY_CD_STR + COLON + this.glblCmpyCd);
            key.append(DELIMITER + XX_MODE_CD_STR + COLON + NFZC202001.MODE_BILL_TO_CUST);
            key.append(DELIMITER + BILL_TO_CUST_CD_STR + COLON +  billToCustCd);
            key.append(DELIMITER + PROC_DT_STR + COLON +  this.slsDt);
            S21InfoLogOutput.println(FUNC_ID + COLON + NFZC2020 + DELIMITER + key.toString());

             return true;
         }
         // END 2017/03/14 E.Kameishi [QC#18018,ADD]

         // START 2017/03/14 E.Kameishi [QC#18018,DEL]
         //if (S21ApiUtil.isXxMsgId(pMsg)) {
         //    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
         //    S21ApiMessage msg = msgList.get(0);
         //    String msgId = msg.getXxMsgid();
         //    String[] msgPrms = msg.getXxMsgPrmArray();
         //    String rtnMsg = getRtnMsg(msgId, msgPrms);
         //    String[] str = {msgId, rtnMsg, billToCustCd};
         //    // START 2017/03/10 E.Kameishi [QC#18018,MOD]
         //    msgMap.addXxMsgIdWithPrm(MSG_ERROR_CD.NFCM0813E.name(), str);
         //    return true;
         //    // END 2017/03/10 E.Kameishi [QC#18018,MOD]
         //}
         // END 2017/03/14 E.Kameishi [QC#18018,DEL]

         // START 2017/03/10 E.Kameishi [QC#18018,ADD]
         return false;
         // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }

    private boolean callNWZC2030(NFZC601001PMsg param, AR_RCPT_RCV_WRKTMsg arRcptRcvWrkTMsg, BigDecimal arBatRcptAmt) {
        NWZC203001PMsg pMsg = new NWZC203001PMsg();
        NWZC203001 nwzc203001 = new NWZC203001();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NWZC203001Constant.PROC_MODE_SETTLE);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.crCardCustRefNum, param.crCardCustRefNum);
        setValue(pMsg.sellToCustCd, arRcptRcvWrkTMsg.firstBillToCustCd);
        setValue(pMsg.crCardAuthAmt, arBatRcptAmt);
        setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.AR);
        setValue(pMsg.xxPmtcTaxIndNum, PMTC_TAX_INDEX_NUM_OFF);
        setValue(pMsg.xxPmtcPrflOrdOvrdCd, PMTC_PRFL_ORD_OVRD_CD_NO);
        setValue(pMsg.xxPmtcOrdId, arRcptRcvWrkTMsg.arBatRcptNm);

        nwzc203001.execute(pMsg, ONBATCH_TYPE.BATCH);
        // START 2017/03/14 E.Kameishi [QC#18018,ADD]
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
//                setValue(param.xxMsgIdList.no(i).xxMsgId, pMsg.xxMsgIdList.no(i).xxMsgId);
//                String msgTxt = getRtnMsg(pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), S21ApiUtil.getXxMsgList(pMsg).get(i).getXxMsgPrmArray());
//                setValue(param.xxMsgIdList.no(i).xxMsgTxt, msgTxt);
                setMsgIdList(param.xxMsgIdList, pMsg.xxMsgIdList.no(i).xxMsgId.getValue()); // QC#18336
            }

            StringBuilder key = new StringBuilder();
            key.append(GLBL_CMPY_CD_STR + COLON + this.glblCmpyCd);
            key.append(DELIMITER + XX_MODE_CD_STR + COLON + NWZC203001Constant.PROC_MODE_SETTLE);
            key.append(DELIMITER + SLS_DT_STR + COLON + this.slsDt);
            key.append(DELIMITER + CR_CARD_CUST_REF_NUM + COLON + param.crCardCustRefNum.getValue());
            key.append(DELIMITER + SELL_TO_CUST_CD_STR + COLON + arRcptRcvWrkTMsg.firstBillToCustCd.getValue());
            key.append(DELIMITER + CR_CARD_AUTH_AMT_STR + COLON +  arBatRcptAmt);
            key.append(DELIMITER + CR_CARD_TRX_TP_CD_STR + COLON +  CR_CARD_TRX_TP.AR);
            key.append(DELIMITER + XX_PMTC_TAX_IND_NUM_STR + COLON +  PMTC_TAX_INDEX_NUM_OFF);
            key.append(DELIMITER + XX_PMTC_PRFL_ORD_OVRD_CD_STR + COLON +  PMTC_PRFL_ORD_OVRD_CD_NO);
            key.append(DELIMITER + XX_PMTC_ORD_ID_STR + COLON +  arRcptRcvWrkTMsg.arBatRcptNm.getValue());
            S21InfoLogOutput.println(FUNC_ID + COLON + NWZC2030 + DELIMITER + key.toString());

            return true;
        }
        // END 2017/03/14 E.Kameishi [QC#18018,ADD]

        // START 2017/03/14 E.Kameishi [QC#18018,DEL]
        //if (S21ApiUtil.isXxMsgId(pMsg)) {
        //    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
        //    S21ApiMessage msg = msgList.get(0);
        //    String msgId = msg.getXxMsgid();
        //    String[] str = {NWZC2030, msgId, "crCardCustRefNum=" + param.crCardCustRefNum};
        //    // START 2017/03/10 E.Kameishi [QC#18018,MOD]
        //    msgMap.addXxMsgIdWithPrm(MSG_ERROR_CD.NFBM0208E.name(), str);
        //    return true;
        //    // END 2017/03/10 E.Kameishi [QC#18018,MOD]
        //}
        // END 2017/03/14 E.Kameishi [QC#18018,DEL]
        // START 2017/03/10 E.Kameishi [QC#18018,ADD]
        return false;
        // END 2017/03/10 E.Kameishi [QC#18018,ADD]
    }
}
