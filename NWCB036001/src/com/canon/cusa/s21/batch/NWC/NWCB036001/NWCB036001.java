/** <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.batch.NWC.NWCB036001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.FM_INV_IMPT_DTLTMsg;
import business.db.FM_INV_IMPT_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21OracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Invoice Interface from EMSD Tool (Copy from IF to Biz Table)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/29   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWCB036001 extends S21BatchMain {

    /** NWCM0112E : It failed to get [@].(@) */
    private static final String NWCM0112E = "NWCM0112E";

    /** NWCM0132E : Failed to insert the @. */
    private static final String NWCM0132E = "NWCM0132E";

    /** line separator */
    private static final String NEW_LINE = String.format("%n");

    /** DEF_ERROR_MESSAGE_LEN */
    private static final int DEF_ERROR_MESSAGE_LEN = 140;

    /** mail group id(from) : FROM0005 */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id(to) for Error : NWCB0360 */
    private static final String MAIL_GROUP_ID_TO = "NWCB0360";

    /** mail template id : NWCB0360M001 */
    private static final String MAIL_TEMPLATE_ID_FOR_BIZ_ERROR = "NWCB0360M001";

    /** <pre>batchNm : NWCB0360 Invoice Interface from EMSD Tool (Copy from IF to Biz Table)</pre> */
    private static final String BATCH_NM = "NWCB0360 Invoice Interface from EMSD Tool (Copy from IF to Biz Table)";

    /** mail template key(batchNm) : batchNm */
    private static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** mail template key(batchProcLogId) : batchProcLogId */
    private static final String MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID = "batchProcLogId";

    /** MAIL_TEMPLATE_KEY_MSG_INFO */
    private static final String MAIL_TEMPLATE_KEY_MSG_INFO = "ErrorInfo";

    //    private static final int DEFAULT_FETCH_SIZE = 1000;

    /** read count */
    private int readCount;

    /** error count */
    private int errorCount;

    /** regist count */
    private int registCount;

    /** term code */
    private TERM_CD termCd;

    /** global company code */
    private String glblCmpyCd;

    /** interface id */
    private String interfaceId;

    /** transaction id array */
    private BigDecimal[] trxIds = null;

    /** s21 transaction accessor */
    S21TransactionTableAccessor s21TrxAsr = new S21TransactionTableAccessor();

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** errInfoList */
    private List<String> errInfoList = new ArrayList<String>();

    /** errorMessage */
    private StringBuilder errorMessage = new StringBuilder();

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.readCount = 0;
        this.registCount = 0;
        this.errorCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NWCM0112E, new String[] {"Global Company Code", "getGlobalCompanyCode()" });
        }
        this.interfaceId = getInterfaceID();
        if (this.interfaceId == null || "".equals(this.interfaceId)) {
            throw new S21AbendException(NWCM0112E, new String[] {"Interface ID", "getInterfaceID()" });
        }

        this.trxIds = this.s21TrxAsr.getIntegrationRecordDesc(this.interfaceId);

    }

    @Override
    protected void mainRoutine() {
        for (BigDecimal trxId : this.trxIds) {
            if (copyIFtoBizTbl(trxId)) {
                this.s21TrxAsr.endIntegrationProcess(this.interfaceId, trxId);
                commit();
            } else {
                rollback();
            }
        }

    }

    /**
     * copyIFtoBizTbl
     * @param trxId transaction id
     * @return if error then return false.
     */
    private boolean copyIFtoBizTbl(BigDecimal trxId) {
        Map<BigDecimal, BigDecimal> hdrPkMap = new HashMap<BigDecimal, BigDecimal>();
        int wkRgstCnt = this.registCount;
        if (!copyIFtoBizHeaderTbl(trxId, hdrPkMap)) {
            return false;
        }
        wkRgstCnt -= this.registCount;

        if (!copyIFtoBizDetailTbl(trxId, hdrPkMap)) {
            this.registCount += wkRgstCnt;
            return false;
        }
        return true;
    }

    /**
     * copyIFtoBizDetailTbl
     * @param trxId transaction id
     * @param hdrPkMap header pk map
     * @return if error then return false.
     */
    private boolean copyIFtoBizDetailTbl(BigDecimal trxId, Map<BigDecimal, BigDecimal> hdrPkMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("interfaceId", this.interfaceId);
        param.put("transactionId", trxId);

        List<Map<String, Object>> rsltList //
        = commonCast(this.ssmBatchClient.queryObjectList("getInterfaceDetailData", param));
        this.readCount += rsltList.size();
        for (Map<String, Object> rslt : rsltList) {
            FM_INV_IMPT_DTLTMsg tMsg = new FM_INV_IMPT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fmInvImptDtlPk, S21OracleSeqAccessor.getSeqNumber(ZYPOracleSeqConstant.FM_INV_IMPT_DTL_SQ));

            ZYPEZDItemValueSetter.setValue(tMsg.fmBatId, (BigDecimal) rslt.get("FM_BAT_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.arIntfcFlg, (String) rslt.get("AR_INTFC_FLG"));
            if (!ZYPCommonFunc.hasValue(tMsg.arIntfcFlg)) {
                tMsg.arIntfcFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invImptProcFlg, (String) rslt.get("INV_IMPT_PROC_FLG"));
            if (!ZYPCommonFunc.hasValue(tMsg.invImptProcFlg)) {
                tMsg.invImptProcFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.fmCratTs, (String) rslt.get("FM_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmCratUsrId, (BigDecimal) rslt.get("FM_CRAT_USR_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmUpdTs, (String) rslt.get("FM_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmUpdUsrId, (BigDecimal) rslt.get("FM_UPD_USR_ID"));

            ZYPEZDItemValueSetter.setValue(tMsg.fmAcctNum, (String) rslt.get("FM_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmBidTxt, (String) rslt.get("FM_BID_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgPerFromDt, (String) rslt.get("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgPerThruDt, (String) rslt.get("BLLG_PER_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, (String) rslt.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, (String) rslt.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmMdseCd, (String) rslt.get("FM_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmDescTxt, (String) rslt.get("FM_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmLineAmt, (BigDecimal) rslt.get("FM_LINE_AMT"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmLineTaxAmt, (BigDecimal) rslt.get("FM_LINE_TAX_AMT"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmArIntfcId, (BigDecimal) rslt.get("FM_AR_INTFC_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmItemId, (BigDecimal) rslt.get("FM_ITEM_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmMdseDescTxt, (String) rslt.get("FM_MDSE_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, (String) rslt.get("BILL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNum, (String) rslt.get("SHIP_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, (String) rslt.get("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, (String) rslt.get("SHIP_TO_CUST_ACCT_CD"));

            ZYPEZDItemValueSetter.setValue(tMsg.slsRepCrPct, (BigDecimal) rslt.get("SLS_REP_CR_PCT"));
            ZYPEZDItemValueSetter.setValue(tMsg.slsRepTocCd, (String) rslt.get("SLS_REP_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_01, (String) rslt.get("INTFC_LINE_ATTRB_TXT_01"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_02, (String) rslt.get("INTFC_LINE_ATTRB_TXT_02"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_03, (String) rslt.get("INTFC_LINE_ATTRB_TXT_03"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_04, (String) rslt.get("INTFC_LINE_ATTRB_TXT_04"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_05, (String) rslt.get("INTFC_LINE_ATTRB_TXT_05"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_06, (String) rslt.get("INTFC_LINE_ATTRB_TXT_06"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_07, (String) rslt.get("INTFC_LINE_ATTRB_TXT_07"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_08, (String) rslt.get("INTFC_LINE_ATTRB_TXT_08"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_09, (String) rslt.get("INTFC_LINE_ATTRB_TXT_09"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_10, (String) rslt.get("INTFC_LINE_ATTRB_TXT_10"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_11, (String) rslt.get("INTFC_LINE_ATTRB_TXT_11"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_12, (String) rslt.get("INTFC_LINE_ATTRB_TXT_12"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_13, (String) rslt.get("INTFC_LINE_ATTRB_TXT_13"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_14, (String) rslt.get("INTFC_LINE_ATTRB_TXT_14"));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcLineAttrbTxt_15, (String) rslt.get("INTFC_LINE_ATTRB_TXT_15"));
            ZYPEZDItemValueSetter.setValue(tMsg.dfrdAcctgRuleCd, (String) rslt.get("DFRD_ACCTG_RULE_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.shipQty, (BigDecimal) rslt.get("SHIP_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.invTpCd, (String) rslt.get("INV_TP_CD"));

            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, (String) rslt.get("PMT_TERM_CASH_DISC_CD"));

            if (hdrPkMap.containsKey(tMsg.fmBatId.getValue())) {
                BigDecimal hdrPk = hdrPkMap.get(tMsg.fmBatId.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.fmInvImptHdrPk, hdrPk);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.procErrFlg, ZYPConstant.FLG_OFF_N);

            S21FastTBLAccessor.insert(tMsg);
            if (!tMsg.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NORMAL)) {
                this.errInfoList.add(S21MessageFunc.clspGetMessage(NWCM0132E, editDtlErrMsg(tMsg)));
                this.errorCount++;
                return false;
            }
        }
        this.registCount += rsltList.size();
        return true;
    }

    private String[] editDtlErrMsg(FM_INV_IMPT_DTLTMsg tMsg) {
        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append("FM_INV_IMPT_DTL").append(" ");
        sb.append("key:FM_INV_IMPT_DTL_PK(").append(tMsg.fmInvImptDtlPk.getValue().toPlainString()).append(")");
        sb.append(",FM_INV_IMPT_HDR_PK(").append(tMsg.fmInvImptHdrPk.getValue().toPlainString()).append(")");
        sb.append(",FM_BAT_ID(").append(tMsg.fmBatId.getValue().toPlainString()).append(")");
        sb.append(",FM_UPD_TS(").append(tMsg.fmUpdTs.getValue()).append(")");
        sb.append(",FM_UPD_USR_ID(").append(tMsg.fmUpdUsrId.getValue().toPlainString()).append(")");

        return new String[] {sb.toString() };
    }

    /**
     * copyIFtoBizHeaderTbl
     * @param trxId transaction id
     * @param hdrPkMap header pk map
     * @return if error then return false.
     */
    private boolean copyIFtoBizHeaderTbl(BigDecimal trxId, Map<BigDecimal, BigDecimal> hdrPkMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("interfaceId", this.interfaceId);
        param.put("transactionId", trxId);

        List<Map<String, Object>> rsltList //
        = commonCast(this.ssmBatchClient.queryObjectList("getInterfaceHeaderData", param));
        this.readCount += rsltList.size();
        for (Map<String, Object> rslt : rsltList) {
            FM_INV_IMPT_HDRTMsg tMsg = new FM_INV_IMPT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fmInvImptHdrPk, S21OracleSeqAccessor.getSeqNumber(ZYPOracleSeqConstant.FM_INV_IMPT_HDR_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.fmBatId, (BigDecimal) rslt.get("FM_BAT_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.arIntfcFlg, (String) rslt.get("AR_INTFC_FLG"));
            if (!ZYPCommonFunc.hasValue(tMsg.arIntfcFlg)) {
                tMsg.arIntfcFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invImptProcFlg, (String) rslt.get("INV_IMPT_PROC_FLG"));
            if (!ZYPCommonFunc.hasValue(tMsg.invImptProcFlg)) {
                tMsg.invImptProcFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.fmCratTs, (String) rslt.get("FM_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmCratUsrId, (BigDecimal) rslt.get("FM_CRAT_USR_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmUpdTs, (String) rslt.get("FM_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(tMsg.fmUpdUsrId, (BigDecimal) rslt.get("FM_UPD_USR_ID"));
            ZYPEZDItemValueSetter.setValue(tMsg.procErrFlg, ZYPConstant.FLG_OFF_N);

            hdrPkMap.put(tMsg.fmBatId.getValue(), tMsg.fmInvImptHdrPk.getValue());
            S21FastTBLAccessor.insert(tMsg);
            if (!tMsg.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NORMAL)) {
                this.errInfoList.add(S21MessageFunc.clspGetMessage(NWCM0132E, editHdrErrMsg(tMsg)));
                this.errorCount++;
                return false;
            }
        }
        this.registCount += rsltList.size();
        return true;
    }

    private String[] editHdrErrMsg(FM_INV_IMPT_HDRTMsg tMsg) {
        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append("FM_INV_IMPT_DTL").append(" ");
        sb.append(",FM_INV_IMPT_HDR_PK(").append(tMsg.fmInvImptHdrPk.getValue().toPlainString()).append(")");
        sb.append(",FM_BAT_ID(").append(tMsg.fmBatId.getValue().toPlainString()).append(")");
        sb.append(",FM_UPD_TS(").append(tMsg.fmUpdTs.getValue()).append(")");
        sb.append(",FM_UPD_USR_ID(").append(tMsg.fmUpdUsrId.getValue().toPlainString()).append(")");

        return new String[] {sb.toString() };
    }

    @Override
    protected void termRoutine() {
        sendErrorMail();
        setTermState(this.termCd, this.registCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // initialize S21BatchMain
        new NWCB036001().executeBatch(NWCB036001.class.getSimpleName());
    }

    private void sendErrorMail() {
        if (errInfoList.size() == 0) {
            return;
        }
        for (String errInfo : errInfoList) {
            errorMessage.append(NEW_LINE).append("          ");
            errorMessage.append(errInfo);
        }
        postMail();
        commit();
    }

    private void postMail() {
        S21MailGroup mailGroupFrom = null;
        S21MailGroup mailGroupTo = null;
        S21MailAddress fromAddress = null;
        List<S21MailAddress> toAddressList = null;
        S21MailTemplate mailTemplate = null;
        S21Mail mail = null;

        mailGroupFrom = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        mailGroupTo = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_TO);
        toAddressList = mailGroupTo.getMailAddress();

        mailTemplate = new S21MailTemplate(getGlobalCompanyCode(), MAIL_TEMPLATE_ID_FOR_BIZ_ERROR);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_NM, BATCH_NM);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID, getBatchProcessLogID());
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_MSG_INFO, errorMessage.toString());

        mail = new S21Mail(getGlobalCompanyCode());
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();
    }

    private static <T> T commonCast(Object fromObj) {
        T toObj = (T) fromObj;
        return toObj;
    }
}
