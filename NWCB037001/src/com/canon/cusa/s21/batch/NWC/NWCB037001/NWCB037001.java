/** <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.batch.NWC.NWCB037001;

import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.BATCH_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.DEF_ERROR_MESSAGE_LEN;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.LEN_INVOICE_KEY;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MAIL_TEMPLATE_ID_FOR_BIZ_ERROR;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MAIL_TEMPLATE_KEY_BATCH_NM;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MAIL_TEMPLATE_KEY_MSG_INFO;
import static com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.NEW_LINE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.leftPad;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DFRD_ACCTG_RULETMsg;
import business.db.DS_INV_TPTMsg;
import business.db.DS_TAX_GRP_EXEMTMsg;
import business.db.FM_INV_IMPT_DTLTMsg;
import business.db.FM_INV_IMPT_HDRTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SVC_ALLOC_TPTMsg;
import business.db.VAR_CHAR_CONSTTMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateInputLinePMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC040007PMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NWZ.NWZC036101.NWZC036101;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant;
import com.canon.cusa.s21.batch.NWC.NWCB037001.constant.NWCB037001Constant.MSG_ID;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Invoice Interface from EMSD Tool (Create Invoice)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         M.Yamada        Create          N/A
 * 2017/05/18   Fujitsu         M.Yamada        Update          QC#18610
 * 2017/05/31   Fujitsu         M.Yamada        Update          QC#18663
 * 2018/01/11   Fujitsu         T.Murai         Update          QC#22609,22637
 * 2018/01/23   Hitachi         T.Tsuchida      Update          QC#23668
 * 2018/01/25   Fujitsu         M.Ohno          Update          QC#23413
 *</pre>
 */
public class NWCB037001 extends S21BatchMain {

    /** error count */
    private int errorCount;

    /** regist count */
    private int registCount;

    /** term code */
    private TERM_CD termCd;

    /** global company code */
    private String glblCmpyCd;

    /** sales date */
    private String slsDt;

    /** s21 transaction accessor */
    S21TransactionTableAccessor s21TrxAsr = new S21TransactionTableAccessor();

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** errInfoList */
    private List<String> errInfoList = new ArrayList<String>();

    /** errorMessage */
    private StringBuilder errorMessage = new StringBuilder();

    /** positive amount DS_INV_TP_CD */
    private String pstAmtDsInvTpCd;

    /** negative amount DS_INV_TP_CD */
    private String negAmtDsInvTpCd;

    /** emsd billing DS_ORD_TP_CD */
    private String emsdBllgDsOrdTpCd;

    /** default SVC_ALLOC_TRX_TP_NM */
    private String defaultSvcAllocTrxTpNm;

    // 2018/01/25 QC#23413 add start
    /** hdr error msg txt */
    private String hdrErrMsgTxt = null;
    // 2018/01/25 QC#23413 add end

    @Override
    protected void initRoutine() {
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        //        this.readCount = 0;
        this.registCount = 0;
        this.errorCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.NWCM0112E.toString(), new String[] {"Global Company Code", "getGlobalCompanyCode()" });
        }
        this.slsDt = ZYPDateUtil.getSalesDate();
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(MSG_ID.NWCM0112E.toString(), new String[] {"Sales Date", "ZYPDateUtil.getSalesDate()" });
        }

        this.pstAmtDsInvTpCd = ZYPCodeDataUtil.getVarCharConstValue(NWCB037001Constant.VAR_CHAR_CONST_KEY_PST_AMT_DS_INV_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.pstAmtDsInvTpCd)) {
            throw new S21AbendException(MSG_ID.NWCM0112E.toString(), new String[] {NWCB037001Constant.VAR_CHAR_CONST_KEY_PST_AMT_DS_INV_TP, new VAR_CHAR_CONSTTMsg().getTableName() });
        }

        this.negAmtDsInvTpCd = ZYPCodeDataUtil.getVarCharConstValue(NWCB037001Constant.VAR_CHAR_CONST_KEY_NEG_AMT_DS_INV_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.negAmtDsInvTpCd)) {
            throw new S21AbendException(MSG_ID.NWCM0112E.toString(), new String[] {NWCB037001Constant.VAR_CHAR_CONST_KEY_NEG_AMT_DS_INV_TP, new VAR_CHAR_CONSTTMsg().getTableName() });
        }

        this.emsdBllgDsOrdTpCd = ZYPCodeDataUtil.getVarCharConstValue(NWCB037001Constant.VAR_CHAR_CONST_KEY_EMSD_BILLING_DS_ORD_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.emsdBllgDsOrdTpCd)) {
            throw new S21AbendException(MSG_ID.NWCM0112E.toString(), new String[] {NWCB037001Constant.VAR_CHAR_CONST_KEY_EMSD_BILLING_DS_ORD_TP, new VAR_CHAR_CONSTTMsg().getTableName() });
        }

        this.defaultSvcAllocTrxTpNm = ZYPCodeDataUtil.getVarCharConstValue(NWCB037001Constant.VAR_CHAR_CONST_KEY_DEFAULT_TAX_TRX_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defaultSvcAllocTrxTpNm)) {
            throw new S21AbendException(MSG_ID.NWCM0112E.toString(), new String[] {NWCB037001Constant.VAR_CHAR_CONST_KEY_DEFAULT_TAX_TRX_TP, new VAR_CHAR_CONSTTMsg().getTableName() });
        }

    }

    @Override
    protected void mainRoutine() {
        if (resetStatus()) {
            commit();
        } else {
            rollback();
        }

        if (!checkData()) {
            commit(); // if error then updated the status.
        }

        createInvoice();
    }

    private void createInvoice() {
        List<Map<String, Object>> rsltList = getInvoiceTarget();

        String prevInvKey = "";
        BigDecimal hdrPk = null;
        if (!rsltList.isEmpty()) {
            prevInvKey = editInvoiceKey(rsltList.get(0));
            // 2018/01/25 QC#23413 add start
            hdrPk = (BigDecimal) rsltList.get(0).get("FM_INV_IMPT_HDR_PK");
            // 2018/01/25 QC#23413 add end
        }
        List<FM_INV_IMPT_DTLTMsg> dtlTMsgList = new ArrayList<FM_INV_IMPT_DTLTMsg>();
        for (Map<String, Object> rslt : rsltList) {
            String currInvKey = editInvoiceKey(rslt);
            if (!currInvKey.equals(prevInvKey)) {
                callCreateInvoiceApi(dtlTMsgList);
                dtlTMsgList.clear();
                prevInvKey = currInvKey;
            }
            // 2018/01/25 QC#23413 add start
            if (hdrPk.compareTo((BigDecimal) rslt.get("FM_INV_IMPT_HDR_PK")) != 0) {
                updateHdr(hdrPk);
                hdrPk = (BigDecimal) rslt.get("FM_INV_IMPT_HDR_PK");
            }
            // 2018/01/25 QC#23413 add end
            dtlTMsgList.add(editDtlTMsgList(rslt));
        }
        if (!dtlTMsgList.isEmpty()) {
            callCreateInvoiceApi(dtlTMsgList);
            // 2018/01/25 QC#23413 add start
            updateHdr(hdrPk);
            // 2018/01/25 QC#23413 add end
        }
    }

    private void addToErrInfoList(List<String> errMsgTxtList) {
        for (String errMsgTxt : errMsgTxtList) {
            this.errInfoList.add(errMsgTxt);
            //            this.errorCount++;
        }
    }

    private FM_INV_IMPT_DTLTMsg editDtlTMsgList(Map<String, Object> rslt) {
        FM_INV_IMPT_DTLTMsg tMsg = new FM_INV_IMPT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) rslt.get("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.fmInvImptDtlPk, (BigDecimal) rslt.get("FM_INV_IMPT_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.fmInvImptHdrPk, (BigDecimal) rslt.get("FM_INV_IMPT_HDR_PK"));

        ZYPEZDItemValueSetter.setValue(tMsg.fmBatId, (BigDecimal) rslt.get("FM_BAT_ID"));
        ZYPEZDItemValueSetter.setValue(tMsg.arIntfcFlg, (String) rslt.get("AR_INTFC_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.invImptProcFlg, (String) rslt.get("INV_IMPT_PROC_FLG"));
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

        ZYPEZDItemValueSetter.setValue(tMsg.procErrFlg, (String) rslt.get("PROC_ERR_FLG"));
        ZYPEZDItemValueSetter.setValue(tMsg.procErrMsgTxt, (String) rslt.get("PROC_ERR_MSG_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, (String) rslt.get("PMT_TERM_CASH_DISC_CD"));

        return tMsg;
    }

    private void callCreateInvoiceApi(List<FM_INV_IMPT_DTLTMsg> dtlTMsgList) {
        List<String> errMsgTxtList = createInvoiceMain(dtlTMsgList);
        if (errMsgTxtList != null && errMsgTxtList.size() > 0) {
            addToErrInfoList(errMsgTxtList);
        }
        return;
    }

    private String editInvoiceKey(Map<String, Object> rslt) {
        StringBuilder sb = new StringBuilder(LEN_INVOICE_KEY);
        sb.append(((BigDecimal) rslt.get("FM_BAT_ID")).toPlainString()).append(",") // 29
                .append((String) rslt.get("BILL_TO_CUST_CD")).append(",") // 21
                .append((String) rslt.get("PMT_TERM_CASH_DISC_CD")).append(",") // 4
                .append((String) rslt.get("SHIP_TO_CUST_CD")).append(",") // 21
                .append((String) rslt.get("FM_LINE_AMT_SIGN")); // 1

        return sb.toString();
    }

    /**
     * get invoice target data.
     * @param rsltList result list
     */
    private List<Map<String, Object>> getInvoiceTarget() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        List<Map<String, Object>> rsltList //
        = commonCast(this.ssmBatchClient.queryObjectList("getInvoiceTarget", param));
        if (rsltList == null) {
            return new ArrayList<Map<String, Object>>();
        }

        return rsltList;
    }

    /**
     * check data integrity.
     * @return if error then update status to error and return false.
     */
    private boolean checkData() {
        boolean isNormal = true;
        if (!checkHeaderExistsDetail()) {
            isNormal = false;
        }
        if (!checkDetailExistsHeader()) {
            isNormal = false;
        }
        if (!checkBatIdDuplicated()) {
            isNormal = false;
        }
        return isNormal;
    }

    /**
     * checkBatIdDuplicated.
     * @return if error then update status to error and return false.
     */
    private boolean checkBatIdDuplicated() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        List<BigDecimal> hdrPkList //
        = commonCast(this.ssmBatchClient.queryObjectList("checkBatIdDuplicated", param));
        this.errorCount += hdrPkList.size();
        for (BigDecimal hdrPk : hdrPkList) {
            FM_INV_IMPT_HDRTMsg keyMsg = new FM_INV_IMPT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(keyMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(keyMsg.fmInvImptHdrPk, hdrPk);

            FM_INV_IMPT_HDRTMsg tMsg = (FM_INV_IMPT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyMsg);
            if (tMsg == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invImptProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(//
                    tMsg.procErrMsgTxt //
                    , S21MessageFunc.clspGetMessage(MSG_ID.NWCM0140E.toString(), new String[] {"Batch Id(FM_INV_IMPT_HDR_PK:" + hdrPk.toPlainString() + ")" }));
            S21FastTBLAccessor.update(tMsg);

            this.errInfoList.add(tMsg.procErrMsgTxt.getValue());
        }
        return hdrPkList.isEmpty();
    }

    /**
     * checkDetailExistsHeader.
     * @return if error then update status to error and return false.
     */
    private boolean checkDetailExistsHeader() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        List<BigDecimal> dtlPkList //
        = commonCast(this.ssmBatchClient.queryObjectList("checkDetailExistsHeader", param));
        this.errorCount += dtlPkList.size();
        for (BigDecimal dtlPk : dtlPkList) {
            FM_INV_IMPT_DTLTMsg keyMsg = new FM_INV_IMPT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(keyMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(keyMsg.fmInvImptDtlPk, dtlPk);

            FM_INV_IMPT_DTLTMsg tMsg = (FM_INV_IMPT_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyMsg);
            if (tMsg == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invImptProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(//
                    tMsg.procErrMsgTxt //
                    , S21MessageFunc.clspGetMessage(MSG_ID.NWCM0142E.toString(), new String[] {"FM_INV_IMPT_HDR", "FM_INV_IMPT_DTL.FM_INV_IMPT_DTL_PK:" + dtlPk.toPlainString() + ")" }));
            S21FastTBLAccessor.update(tMsg);

            this.errInfoList.add(tMsg.procErrMsgTxt.getValue());
        }
        return dtlPkList.isEmpty();
    }

    /**
     * checkHeaderExistsDetail.
     * @return if error then update status to error and return false.
     */
    private boolean checkHeaderExistsDetail() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        List<BigDecimal> hdrPkList //
        = commonCast(this.ssmBatchClient.queryObjectList("checkHeaderExistsDetail", param));
        this.errorCount += hdrPkList.size();
        for (BigDecimal hdrPk : hdrPkList) {
            FM_INV_IMPT_HDRTMsg keyMsg = new FM_INV_IMPT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(keyMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(keyMsg.fmInvImptHdrPk, hdrPk);

            FM_INV_IMPT_HDRTMsg tMsg = (FM_INV_IMPT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyMsg);
            if (tMsg == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invImptProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(//
                    tMsg.procErrMsgTxt //
                    , S21MessageFunc.clspGetMessage(MSG_ID.NWCM0142E.toString(), new String[] {"FM_INV_IMPT_DTL", "FM_INV_IMPT_HDR.FM_INV_IMPT_HDR_PK:" + hdrPk.toPlainString() + ")" }));
            S21FastTBLAccessor.update(tMsg);

            this.errInfoList.add(tMsg.procErrMsgTxt.getValue());
        }
        return hdrPkList.isEmpty();
    }

    /**
     * <pre>
     * update error records for re-process.
     * INV_IMPT_PROC_FLG : N, PROC_ERR_FLG : N, PROC_ERR_MSG_TXT : null
     * </pre>
     * @return if error then return false.
     */
    private boolean resetStatus() {
        // reset error header records.
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);

        List<Map<String, Object>> rsltList //
        = commonCast(this.ssmBatchClient.queryObjectList("getErrorHeader", param));
        for (Map<String, Object> rslt : rsltList) {
            FM_INV_IMPT_HDRTMsg keyMsg = new FM_INV_IMPT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(keyMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(keyMsg.fmInvImptHdrPk, (BigDecimal) rslt.get("FM_INV_IMPT_HDR_PK"));

            FM_INV_IMPT_HDRTMsg tMsg = (FM_INV_IMPT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyMsg);
            if (!tMsg.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NORMAL)) {
                this.errInfoList.add(S21MessageFunc.clspGetMessage(MSG_ID.NWCM0133E.toString(), editHdrErrMsg(tMsg, "findByKeyForUpdate")));
                this.errorCount++;
                return false;
            }
            tMsg.invImptProcFlg.setValue(ZYPConstant.FLG_OFF_N);
            tMsg.procErrFlg.setValue(ZYPConstant.FLG_OFF_N);
            tMsg.procErrMsgTxt.clear();
            S21FastTBLAccessor.update(tMsg);
            if (!tMsg.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NORMAL)) {
                this.errInfoList.add(S21MessageFunc.clspGetMessage(MSG_ID.NWCM0133E.toString(), editHdrErrMsg(tMsg, "update")));
                this.errorCount++;
                return false;
            }
        }

        // reset error detail records.
        rsltList //
        = commonCast(this.ssmBatchClient.queryObjectList("getErrorDetail", param));
        for (Map<String, Object> rslt : rsltList) {
            FM_INV_IMPT_DTLTMsg keyMsg = new FM_INV_IMPT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(keyMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(keyMsg.fmInvImptDtlPk, (BigDecimal) rslt.get("FM_INV_IMPT_DTL_PK"));

            FM_INV_IMPT_DTLTMsg tMsg = (FM_INV_IMPT_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyMsg);
            if (!tMsg.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NORMAL)) {
                this.errInfoList.add(S21MessageFunc.clspGetMessage(MSG_ID.NWCM0133E.toString(), editDtlErrMsg(tMsg, "findByKeyForUpdate")));
                this.errorCount++;
                return false;
            }
            tMsg.invImptProcFlg.setValue(ZYPConstant.FLG_OFF_N);
            tMsg.procErrFlg.setValue(ZYPConstant.FLG_OFF_N);
            tMsg.procErrMsgTxt.clear();
            S21FastTBLAccessor.update(tMsg);
            if (!tMsg.getReturnCode().equals(S21FastTBLAccessor.RTNCD_NORMAL)) {
                this.errInfoList.add(S21MessageFunc.clspGetMessage(MSG_ID.NWCM0133E.toString(), editDtlErrMsg(tMsg, "update")));
                this.errorCount++;
                return false;
            }
        }
        return true;
    }

    private String[] editDtlErrMsg(FM_INV_IMPT_DTLTMsg tMsg, String cmd) {
        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append("FM_INV_IMPT_DTL").append("(").append(cmd).append(") ");
        sb.append("key:FM_INV_IMPT_DTL_PK(").append(tMsg.fmInvImptDtlPk.getValue().toPlainString()).append(")");
        sb.append(",FM_INV_IMPT_HDR_PK(").append(tMsg.fmInvImptHdrPk.getValue().toPlainString()).append(")");
        sb.append(",FM_BAT_ID(").append(tMsg.fmBatId.getValue().toPlainString()).append(")");
        sb.append(",FM_UPD_TS(").append(tMsg.fmUpdTs.getValue()).append(")");
        sb.append(",FM_UPD_USR_ID(").append(tMsg.fmUpdUsrId.getValue().toPlainString()).append(")");

        return new String[] {sb.toString() };
    }

    private String[] editHdrErrMsg(FM_INV_IMPT_HDRTMsg tMsg, String cmd) {
        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN);
        sb.append("FM_INV_IMPT_DTL").append("(").append(cmd).append(") ");
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
        new NWCB037001().executeBatch(NWCB037001.class.getSimpleName());
    }

    private void sendErrorMail() {
        if (this.errInfoList.size() == 0) {
            return;
        }
        //        StringBuilder sb = new StringBuilder(DEF_ERROR_MESSAGE_LEN * this.errInfoList.size());
        for (String errInfo : this.errInfoList) {
            this.errorMessage.append(NEW_LINE).append("          ");
            this.errorMessage.append(errInfo);
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

        mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        toAddressList = mailGroupTo.getMailAddress();

        mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_FOR_BIZ_ERROR);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_NM, BATCH_NM);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID, getBatchProcessLogID());
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_MSG_INFO, this.errorMessage.toString());

        mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();
    }

    private static <T> T commonCast(Object fromObj) {
        T toObj = (T) fromObj;
        return toObj;
    }

    /**
     * createInvoiceMain
     * @param fmInvImptDtlTMsgList
     * @return List<String>
     */
    private List<String> createInvoiceMain(List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList) {

        List<String> errMsgTxtList = new ArrayList<String>();

        if (!mandatoryCheck(fmInvImptDtlTMsgList, errMsgTxtList)) {
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.MND_CHK_ERR_MSG_TXT);
            this.errorCount += fmInvImptDtlTMsgList.size();
            return errMsgTxtList;
        }

        callInvoiceImportApi(fmInvImptDtlTMsgList, errMsgTxtList);

        return errMsgTxtList;
    }

    /**
     * mandatoryCheck
     * @param fmInvImptDtlTMsgList
     * @param errMsgTxtList
     * @return boolean
     */
    private boolean mandatoryCheck(List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<String> errMsgTxtList) {
        boolean isSuccess = true;
        String addMsgTxt;
        for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {

            //FM_AR_INTFC_ID
            if (!hasValue(dtlTMsg.fmArIntfcId)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"FM_AR_INTFC_ID" }, dtlTMsg.fmInvImptDtlPk.getValue().toString(), errMsgTxtList);
            }

            addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());

            //FM_ACCT_NUM
            if (!hasValue(dtlTMsg.fmAcctNum)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"FM_ACCT_NUM" }, addMsgTxt, errMsgTxtList);
            }

            //BLLG_PER_FROM_DT
            if (!hasValue(dtlTMsg.bllgPerFromDt)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"BLLG_PER_FROM_DT" }, addMsgTxt, errMsgTxtList);
            }

            //BLLG_PER_THRU_DT
            if (!hasValue(dtlTMsg.bllgPerThruDt)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"BLLG_PER_THRU_DT" }, addMsgTxt, errMsgTxtList);
            }

            //SHIP_TO_CUST_CD
            if (!hasValue(dtlTMsg.shipToCustCd)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"SHIP_TO_CUST_CD" }, addMsgTxt, errMsgTxtList);
            }

            //BILL_TO_CUST_CD
            if (!hasValue(dtlTMsg.billToCustCd)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"BILL_TO_CUST_CD" }, addMsgTxt, errMsgTxtList);
            }

            //FM_MDSE_CD
            if (!hasValue(dtlTMsg.fmMdseCd)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"FM_MDSE_CD" }, addMsgTxt, errMsgTxtList);
            }

            //FM_LINE_AMT
            if (!hasValue(dtlTMsg.fmLineAmt)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"FM_LINE_AMT" }, addMsgTxt, errMsgTxtList);
            }

            //BILL_TO_CUST_ACCT_CD
            if (!hasValue(dtlTMsg.billToCustAcctCd)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"BILL_TO_CUST_ACCT_CD" }, addMsgTxt, errMsgTxtList);
            }

            //SHIP_TO_CUST_ACCT_CD
            if (!hasValue(dtlTMsg.shipToCustAcctCd)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"SHIP_TO_CUST_ACCT_CD" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_01(Account Number)
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_01)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_01" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_02(Sales Rep Number)
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_02)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_02" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_03(E473 AR Interface ID)
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_03)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_03" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_04(Billing Period From)
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_04)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_04" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_05(Billing Period To)
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_05)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_05" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_06(Location Number)
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_06)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_06" }, addMsgTxt, errMsgTxtList);
            }

            //INTFC_LINE_ATTRB_TXT_15
            if (!hasValue(dtlTMsg.intfcLineAttrbTxt_15)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"INTFC_LINE_ATTRB_TXT_15" }, addMsgTxt, errMsgTxtList);
            }

            //PMT_TERM_CASH_DISC_CD
            if (!hasValue(dtlTMsg.pmtTermCashDiscCd)) {
                isSuccess = false;
                setErrMsg(MSG_ID.NWCM0155E.toString(), new String[] {"PMT_TERM_CASH_DISC_CD" }, addMsgTxt, errMsgTxtList);
            }

        }

        return isSuccess;
    }

    /**
     * setErrMsg
     * @param msgId
     * @param val
     * @param addMsgTxt
     * @param errMsgTxtList
     */
    private void setErrMsg(String msgId, String[] val, String addMsgTxt, List<String> errMsgTxtList) {

        StringBuilder sb = new StringBuilder(S21MessageFunc.clspGetMessage(msgId, val));
        if (hasValue(addMsgTxt)) {
            sb.append("(").append(addMsgTxt).append(")");
        }
        errMsgTxtList.add(sb.toString());
        S21InfoLogOutput.println(msgId, val);
    }

    /**
     * updateResult
     * @param fmInvImptDtlTMsgList
     * @param invLinePMsgList
     * @param errMsgTxt
     */
    private void updateResult(List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<NWZC040003PMsg> invLinePMsgList, String errMsgTxt) {

        boolean isNormalEnd = !hasValue(errMsgTxt);
        String[] condColName;
        String[] updColName;

        if (!isNormalEnd) {
            rollback();
        }

        // update detail
        for (int i = 0; i < fmInvImptDtlTMsgList.size(); ++i) {
            FM_INV_IMPT_DTLTMsg tmsg = fmInvImptDtlTMsgList.get(i);

            FM_INV_IMPT_DTLTMsg dtlCondTMsg = new FM_INV_IMPT_DTLTMsg();
            setValue(dtlCondTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(dtlCondTMsg.fmInvImptDtlPk, tmsg.fmInvImptDtlPk);
            condColName = new String[] {"glblCmpyCd", "fmInvImptDtlPk" };

            FM_INV_IMPT_DTLTMsg dtlUpdTMsg = (FM_INV_IMPT_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dtlCondTMsg);

            if (dtlUpdTMsg == null) {
                String tableName = new FM_INV_IMPT_DTLTMsg().getTableName();
                String pk = String.valueOf(tmsg.fmInvImptDtlPk.getValue());
                throw new S21AbendException(MSG_ID.NWCM0142E.toString(), new String[] {tableName, pk });
            }

            if (isNormalEnd) {
                BigDecimal taxAmt = null;
                if (invLinePMsgList != null && !invLinePMsgList.isEmpty()) {
                    taxAmt = invLinePMsgList.get(i).invLineDealTaxAmt.getValue();
                    if (hasValue(taxAmt)) {
                        if (BigDecimal.ZERO.compareTo(dtlUpdTMsg.fmLineAmt.getValue()) > 0) {
                            // set negative tax amount(invoice tax amount is positive)
                            taxAmt = taxAmt.negate();
                        }
                    }
                }
                setValue(dtlUpdTMsg.fmLineTaxAmt, taxAmt);
                setValue(dtlUpdTMsg.invImptProcFlg, ZYPConstant.FLG_ON_Y);
                setValue(dtlUpdTMsg.procErrFlg, ZYPConstant.FLG_OFF_N);
                updColName = new String[] {"fmLineTaxAmt", "invImptProcFlg", "procErrFlg" };
            } else {
                setValue(dtlUpdTMsg.invImptProcFlg, ZYPConstant.FLG_OFF_N);
                setValue(dtlUpdTMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
                setValue(dtlUpdTMsg.procErrMsgTxt, errMsgTxt);
                updColName = new String[] {"invImptProcFlg", "procErrFlg", "procErrMsgTxt" };
            }

            int result = S21FastTBLAccessor.updateByPartialValue(dtlCondTMsg, condColName, dtlUpdTMsg, updColName);

            if (result == 0) {
                String tableName = dtlCondTMsg.getTableName();
                String pk = String.valueOf(dtlCondTMsg.fmInvImptDtlPk.getValue());
                throw new S21AbendException(MSG_ID.NWCM0110E.toString(), new String[] {tableName, pk });
            }
        }

          // 2018/01/25 QC#23413 del start
//        // update header
//        FM_INV_IMPT_HDRTMsg hdrCondTMsg = new FM_INV_IMPT_HDRTMsg();
//        setValue(hdrCondTMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(hdrCondTMsg.fmInvImptHdrPk, fmInvImptDtlTMsgList.get(0).fmInvImptHdrPk.getValue());
//        condColName = new String[] {"glblCmpyCd", "fmInvImptHdrPk" };
//        FM_INV_IMPT_HDRTMsg hdrUpdTMsg = (FM_INV_IMPT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(hdrCondTMsg);
//
//        if (ZYPConstant.FLG_OFF_N.equals(hdrUpdTMsg.procErrFlg.getValue())) {
//            //update flg N,N/Y,N  to N,Y/Y,N
//            if (isNormalEnd) {
//                setValue(hdrUpdTMsg.invImptProcFlg, ZYPConstant.FLG_ON_Y);
//                setValue(hdrUpdTMsg.procErrFlg, ZYPConstant.FLG_OFF_N);
//                updColName = new String[] {"invImptProcFlg", "procErrFlg" };
//            } else {
//                setValue(hdrUpdTMsg.invImptProcFlg, ZYPConstant.FLG_OFF_N);
//                setValue(hdrUpdTMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
//                setValue(hdrUpdTMsg.procErrMsgTxt, errMsgTxt);
//                updColName = new String[] {"invImptProcFlg", "procErrFlg", "procErrMsgTxt" };
//            }
//
//            int result = S21FastTBLAccessor.updateByPartialValue(hdrCondTMsg, condColName, hdrUpdTMsg, updColName);
//
//            if (result == 0) {
//                String tableName = hdrUpdTMsg.getTableName();
//                String pk = String.valueOf(hdrCondTMsg.fmInvImptHdrPk.getValue());
//                throw new S21AbendException(MSG_ID.NWCM0110E.toString(), new String[] {tableName, pk });
//            }
//        }
        // 2018/01/25 QC#23413 del end
        // 2018/01/25 QC#23413 add start
        if (!hasValue(hdrErrMsgTxt)) {
            this.hdrErrMsgTxt = errMsgTxt;
        }
        // 2018/01/25 QC#23413 add end

        commit();
    }

    /**
     * Call Invoice Import API
     * @param fmInvImptDtlList
     * @param fmInvImptDtlTMsgList
     */
    private void callInvoiceImportApi(List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<String> errMsgTxtList) {

        List<NWZC040001PMsg> invHeaderPMsgList = new ArrayList<NWZC040001PMsg>();
        List<NWZC040002PMsg> invShipmentPMsgList = new ArrayList<NWZC040002PMsg>();
        List<NWZC040003PMsg> invLinePMsgList = new ArrayList<NWZC040003PMsg>();
        List<NWZC040005PMsg> invLineTaxDtlPMsgList = new ArrayList<NWZC040005PMsg>();
        List<NWZC040006PMsg> invLineSerNumPMsgList = new ArrayList<NWZC040006PMsg>();
        List<NWZC040007PMsg> invSlsCrPMsgList = new ArrayList<NWZC040007PMsg>();

        int dtlRecCnt = fmInvImptDtlTMsgList.size();
        boolean isSuccess = true;

        //set Header Parameter
        isSuccess = setInvHeaderParam(invHeaderPMsgList, fmInvImptDtlTMsgList, errMsgTxtList);
        if (!isSuccess) {
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.INV_IMPT_ERR_MSG_TXT);
            this.errorCount += dtlRecCnt;
            return;
        }

        // Add Start 2018/01/11 S21NA#22609
        // get Ship To Info
        String shipToCustCd = fmInvImptDtlTMsgList.get(0).shipToCustCd.getValue();
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("shipToCustCd", shipToCustCd);
        mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        Map<String, Object> shipInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", mapParam);
        if (shipInfoMap == null) {
            // error
            isSuccess = false;
            String msgId = MSG_ID.NWCM0115E.toString();
            String[] val = new String[] {"ShipToInfo:" + shipToCustCd };
            for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                String addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
            }
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.INV_IMPT_ERR_MSG_TXT);
            this.errorCount += dtlRecCnt;
            return;
        }
        // Add End 2018/01/11 S21NA#22609

        // get tax
        NWZC036101PMsg taxcalcPMsg = new NWZC036101PMsg();
        isSuccess = setTaxCalcApiParam(taxcalcPMsg, fmInvImptDtlTMsgList, invHeaderPMsgList, errMsgTxtList, shipInfoMap); // Add 2018/01/11 QC#22609 shipInfoMap
        if (!isSuccess) {
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.TAX_CALC_ERR_MSG_TXT);
            this.errorCount += dtlRecCnt;
            return;
        }

        new NWZC036101().execute(taxcalcPMsg, ONBATCH_TYPE.BATCH);

        isSuccess = getTaxCalculationAPIResult(taxcalcPMsg, fmInvImptDtlTMsgList, errMsgTxtList);

        if (!isSuccess) {
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.TAX_CALC_ERR_MSG_TXT);
            this.errorCount += dtlRecCnt;
            return;
        }

        //set Shipment Parameter
        setInvShipmentParam(invShipmentPMsgList, fmInvImptDtlTMsgList, shipInfoMap); // Add 2018/01/11 QC#22609 shipInfoMap

        //set Line Parameter
        setInvLineParam(invLinePMsgList, invHeaderPMsgList, invShipmentPMsgList, fmInvImptDtlTMsgList, taxcalcPMsg);

        //set Line Tax Detail Parameter
        setInvLineTaxDtlParam(invLineTaxDtlPMsgList, invLinePMsgList, taxcalcPMsg);

        //set Line Serial Number Parameter
        setInvLineSerNumParams(invLineSerNumPMsgList, invLinePMsgList, fmInvImptDtlTMsgList);

        //set Sales Credit Parameter
        isSuccess = setInvSalesCreditParam(invSlsCrPMsgList, invLinePMsgList, fmInvImptDtlTMsgList, errMsgTxtList);
        if (!isSuccess) {
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.INV_IMPT_ERR_MSG_TXT);
            this.errorCount += dtlRecCnt;
            return;
        }

        //call Invoice Import API
        new NWZC040001().execute(invHeaderPMsgList //
                , invShipmentPMsgList //
                , invLinePMsgList //
                , null, invLineTaxDtlPMsgList //
                , invLineSerNumPMsgList //
                , invSlsCrPMsgList //
                , ONBATCH_TYPE.BATCH);

        isSuccess = getInvoiceImportApiResult(fmInvImptDtlTMsgList, invHeaderPMsgList, invShipmentPMsgList, invLinePMsgList, invLineTaxDtlPMsgList, invLineSerNumPMsgList, invSlsCrPMsgList, errMsgTxtList);

        if (isSuccess) {
            updateResult(fmInvImptDtlTMsgList, invLinePMsgList, null);
            this.registCount += dtlRecCnt;
        } else {
            updateResult(fmInvImptDtlTMsgList, null, NWCB037001Constant.INV_IMPT_ERR_MSG_TXT);
            this.errorCount += dtlRecCnt;
        }

        return;
    }

    /**
     * getTaxCalculationAPIResult
     * @param taxCalcPMsg
     * @param fmInvImptDtlTMsgList
     * @param errMsgTxtList
     * @return boolean
     */
    private boolean getTaxCalculationAPIResult(NWZC036101PMsg taxCalcPMsg, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<String> errMsgTxtList) {

        boolean isSuccess = true;
        String msgId;
        String addMsgTxt;

        if (taxCalcPMsg.xxMsgIdList.getValidCount() > 0) {
            isSuccess = false;
            for (int i = 0; i < taxCalcPMsg.xxMsgIdList.getValidCount(); i++) {
                msgId = taxCalcPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                    addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                    setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                }
            }
        }
        return isSuccess;
    }

    /**
     * getInvoiceImportApiResult
     * @param fmInvImptDtlTMsgList
     * @param invHeaderPMsgList
     * @param invShipmentPMsgList
     * @param invLinePMsgList
     * @param invLineTaxDtlPMsgList
     * @param invLineSerNumPMsgList
     * @param invSlsCrPMsgList
     * @param errMsgTxtList
     * @return boolean
     */
    private boolean getInvoiceImportApiResult(//
            List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList //
            , List<NWZC040001PMsg> invHeaderPMsgList //
            , List<NWZC040002PMsg> invShipmentPMsgList //
            , List<NWZC040003PMsg> invLinePMsgList //
            , List<NWZC040005PMsg> invLineTaxDtlPMsgList //
            , List<NWZC040006PMsg> invLineSerNumPMsgList //
            , List<NWZC040007PMsg> invSlsCrPMsgList //
            , List<String> errMsgTxtList) {

        boolean isSuccess = true;

        String msgId;
        String addMsgTxt;
        for (NWZC040001PMsg headerParam : invHeaderPMsgList) {
            if (headerParam.xxMsgIdList.getValidCount() > 0) {
                isSuccess = false;
                for (int n = 0; n < headerParam.xxMsgIdList.getValidCount(); n++) {
                    msgId = headerParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                        addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                        setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                    }
                }
            }
        }

        for (NWZC040002PMsg shipmentParam : invShipmentPMsgList) {
            if (shipmentParam.xxMsgIdList.getValidCount() > 0) {
                isSuccess = false;
                for (int n = 0; n < shipmentParam.xxMsgIdList.getValidCount(); n++) {
                    msgId = shipmentParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                        addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                        setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                    }
                }
            }
        }

        for (NWZC040003PMsg lineParam : invLinePMsgList) {
            if (lineParam.xxMsgIdList.getValidCount() > 0) {
                isSuccess = false;
                for (int n = 0; n < lineParam.xxMsgIdList.getValidCount(); n++) {
                    msgId = lineParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    int idx = invLinePMsgList.indexOf(lineParam);
                    FM_INV_IMPT_DTLTMsg dtlTMsg = fmInvImptDtlTMsgList.get(idx);
                    addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                    setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                }
            }
        }

        for (NWZC040005PMsg taxDtlParam : invLineTaxDtlPMsgList) {
            if (taxDtlParam.xxMsgIdList.getValidCount() > 0) {
                isSuccess = false;
                for (int n = 0; n < taxDtlParam.xxMsgIdList.getValidCount(); n++) {
                    msgId = taxDtlParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    String lineNum = taxDtlParam.invLineNum.getValue();
                    int idx = Integer.parseInt(lineNum) - 1;
                    FM_INV_IMPT_DTLTMsg dtlTMsg = fmInvImptDtlTMsgList.get(idx);
                    addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                    setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                }
            }
        }

        for (NWZC040006PMsg serNumParam : invLineSerNumPMsgList) {
            if (serNumParam.xxMsgIdList.getValidCount() > 0) {
                isSuccess = false;
                for (int n = 0; n < serNumParam.xxMsgIdList.getValidCount(); n++) {
                    msgId = serNumParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    int idx = invLineSerNumPMsgList.indexOf(serNumParam);
                    FM_INV_IMPT_DTLTMsg dtlTMsg = fmInvImptDtlTMsgList.get(idx);
                    addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                    setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                }
            }
        }

        for (NWZC040007PMsg invSlsCrParam : invSlsCrPMsgList) {
            if (invSlsCrParam.xxMsgIdList.getValidCount() > 0) {
                isSuccess = false;
                for (int n = 0; n < invSlsCrParam.xxMsgIdList.getValidCount(); n++) {
                    msgId = invSlsCrParam.xxMsgIdList.no(n).xxMsgId.getValue();
                    int idx = invSlsCrPMsgList.indexOf(invSlsCrParam);
                    FM_INV_IMPT_DTLTMsg dtlTMsg = fmInvImptDtlTMsgList.get(idx);
                    addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                    setErrMsg(msgId, null, addMsgTxt, errMsgTxtList);
                }
            }
        }

        return isSuccess;
    }

    /**
     * setInvHeaderParam
     * @param invHeaderPmsgList
     * @param fmInvImptDtlTMsgList
     */
    private boolean setInvHeaderParam(//
            List<NWZC040001PMsg> invHeaderPmsgList, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<String> errMsgTxtList) {

        FM_INV_IMPT_DTLTMsg dtlTMsg = fmInvImptDtlTMsgList.get(0);
        String invTpCd;
        String dsInvTpCd;
        BigDecimal fmLineAmt = dtlTMsg.fmLineAmt.getValue();
        if (BigDecimal.ZERO.compareTo(fmLineAmt) > 0) {
            invTpCd = INV_TP.CREDIT_MEMO;
            dsInvTpCd = this.negAmtDsInvTpCd;
        } else {
            invTpCd = INV_TP.INVOICE;
            dsInvTpCd = this.pstAmtDsInvTpCd;
        }

        //check PMT_TERM_CASH_DISC
        PMT_TERM_CASH_DISCTMsg condTMsg = new PMT_TERM_CASH_DISCTMsg();
        setValue(condTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(condTMsg.pmtTermCashDiscCd, dtlTMsg.pmtTermCashDiscCd);

        PMT_TERM_CASH_DISCTMsg resTMmsg = (PMT_TERM_CASH_DISCTMsg) S21FastTBLAccessor.findByKey(condTMsg);
        if (resTMmsg == null) {
            String msgId = MSG_ID.NWCM0142E.toString();
            String[] val = new String[] {condTMsg.getTableName(), condTMsg.pmtTermCashDiscCd.getValue() };
            for (FM_INV_IMPT_DTLTMsg imptDtlTMsg : fmInvImptDtlTMsgList) {
                String addMsgTxt = String.valueOf(imptDtlTMsg.fmArIntfcId.getValue());
                setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
            }
            return false;
        }

        //set param
        NWZC040001PMsg param = new NWZC040001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        // START 2018/01/23 T.Tsuchida [QC#23668,DEL]
        //ZYPEZDItemValueSetter.setValue(param.invNum, dtlTMsg.intfcLineAttrbTxt_15); // Add 2018/01/11 QC#22637
        // END 2018/01/23 T.Tsuchida [QC#23668,DEL]
        ZYPEZDItemValueSetter.setValue(param.invDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(param.acctDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(param.invTpCd, invTpCd);
        ZYPEZDItemValueSetter.setValue(param.custIssPoNum, dtlTMsg.intfcLineAttrbTxt_15);
        ZYPEZDItemValueSetter.setValue(param.billToCustCd, dtlTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(param.sellToCustCd, dtlTMsg.fmAcctNum);
        ZYPEZDItemValueSetter.setValue(param.payerCustCd, dtlTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(param.pmtTermCd, resTMmsg.pmtTermCd);
        ZYPEZDItemValueSetter.setValue(param.pmtTermCashDiscCd, dtlTMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(param.invPrintStsCd, NWCB037001Constant.STS_CD_0);
        ZYPEZDItemValueSetter.setValue(param.invMlSendStsCd, NWCB037001Constant.STS_CD_0);
        ZYPEZDItemValueSetter.setValue(param.invEdiSendStsCd, NWCB037001Constant.STS_CD_0);
        ZYPEZDItemValueSetter.setValue(param.invFaxSendStsCd, NWCB037001Constant.STS_CD_0);
        ZYPEZDItemValueSetter.setValue(param.invEmlSendStsCd, NWCB037001Constant.STS_CD_0);
        ZYPEZDItemValueSetter.setValue(param.trxSrcTpCd, TRX_SRC_TP.WHOLE_SALES);
        ZYPEZDItemValueSetter.setValue(param.sysSrcCd, SYS_SRC.EMSD);
        ZYPEZDItemValueSetter.setValue(param.dsOrdTpCd, this.emsdBllgDsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(param.crCardChrgCpltCd, CR_CARD_CHRG_CPLT.NO_NEED_CREDIT_CARD_CHARGE);
        ZYPEZDItemValueSetter.setValue(param.dsInvTpCd, dsInvTpCd);
        ZYPEZDItemValueSetter.setValue(param.srcSysDocNum, dtlTMsg.intfcLineAttrbTxt_15);
        ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, dtlTMsg.intfcLineAttrbTxt_02);
        ZYPEZDItemValueSetter.setValue(param.itrlInvErrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(param.billToCustAcctCd, dtlTMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(param.soldToCustLocCd, dtlTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(param.lineBizTpCd, LINE_BIZ_TP.EMSD);
        ZYPEZDItemValueSetter.setValue(param.dsBizAreaCd, DS_BIZ_AREA.CONTRACTS);
        ZYPEZDItemValueSetter.setValue(param.actlApplyExchRate, BigDecimal.ONE);

        // QC#18610
        if (!setNetDueDate(param, fmInvImptDtlTMsgList, errMsgTxtList)) {
            return false;
        }

        invHeaderPmsgList.add(param);

        return true;
    }

    /**
     * QC#18610 set net due date from due date calculation api.
     * @param param
     * @param fmInvImptDtlTMsgList 
     * @param errMsgTxtList 
     * @return if error then return false.
     */
    private boolean setNetDueDate(//
            NWZC040001PMsg param, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<String> errMsgTxtList) {

        // due date calculation api
        NFZC309001PMsg dueDtCalcApiPMsg = new NFZC309001PMsg();
        setValue(dueDtCalcApiPMsg.glblCmpyCd, glblCmpyCd);
        setValue(dueDtCalcApiPMsg.pmtTermCashDiscCd, param.pmtTermCashDiscCd);
        setValue(dueDtCalcApiPMsg.trxDt, param.invDt);
        NFZC309001 dueDtCalcApi = new NFZC309001();
        dueDtCalcApi.execute(dueDtCalcApiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(dueDtCalcApiPMsg)) {
            for (int i = 0; i < dueDtCalcApiPMsg.xxMsgIdList.getValidCount(); i++) {
                String msgId = dueDtCalcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                    setErrMsg(msgId, null, String.valueOf(dtlTMsg.fmArIntfcId.getValue()), errMsgTxtList);
                }
            }
            return false;
        }
        setValue(param.netDueDt, dueDtCalcApiPMsg.dueDt);
        return true;
    }

    /**
     * setInvShipmentParam
     * @param invShipmentPmsgList
     * @param fmInvImptDtlTMsgList
	 * @param shipInfoMap // Add 2018/01/11 QC#22609 shipInfoMap
     */
    private void setInvShipmentParam(List<NWZC040002PMsg> invShipmentPmsgList, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, Map<String, Object> shipInfoMap) { // Add 2018/01/11 QC#22609 shipInfoMap

        FM_INV_IMPT_DTLTMsg dtlTMsg = fmInvImptDtlTMsgList.get(0);

        NWZC040002PMsg param = new NWZC040002PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        // START 2018/01/23 T.Tsuchida [QC#23668,DEL]
        //ZYPEZDItemValueSetter.setValue(param.invNum, dtlTMsg.intfcLineAttrbTxt_15); // Add 2018/01/11 QC#22637
        // END 2018/01/23 T.Tsuchida [QC#23668,DEL]
        ZYPEZDItemValueSetter.setValue(param.invBolLineNum, NWCB037001Constant.FIXED_INV_BOL_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(param.shipToCustCd, dtlTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(param.shipToCustAcctCd, dtlTMsg.shipToCustAcctCd);

        // Add Start 2018/01/11 S21_NA#22609
        ZYPEZDItemValueSetter.setValue(param.shipToLocNm, (String) shipInfoMap.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(param.shipToAddlLocNm, (String) shipInfoMap.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, (String) shipInfoMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, (String) shipInfoMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, (String) shipInfoMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, (String) shipInfoMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(param.shipToStCd, (String) shipInfoMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(param.shipToProvNm, (String) shipInfoMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(param.shipToCntyNm, (String) shipInfoMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(param.shipToPostCd, (String) shipInfoMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, (String) shipInfoMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, (String) shipInfoMap.get("CTRY_CD"));
        // Add End 2018/01/11 S21_NA#22609

        invShipmentPmsgList.add(param);
    }

    /**
     * setInvLineParam
     * @param invLinePMsgList
     * @param invShipmentPMsgList
     * @param fmInvImptDtlTMsgList
     * @param taxcalcPMsg
     */
    private void setInvLineParam(List<NWZC040003PMsg> invLinePMsgList, List<NWZC040001PMsg> invHeaderPMsgList, List<NWZC040002PMsg> invShipmentPMsgList, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, NWZC036101PMsg taxcalcPMsg) {

        NWZC040001PMsg invHeaderPMsg = invHeaderPMsgList.get(0);
        NWZC040002PMsg invShipmentPMsg = invShipmentPMsgList.get(0);
        FM_INV_IMPT_DTLTMsg dtlTMsg;
        NWZC040003PMsg param;
        NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg;
        NWZC036101_taxCalculateOutputLinePMsg taxCalcOutputLinePMsg;

        for (int i = 0; i < fmInvImptDtlTMsgList.size(); ++i) {
            dtlTMsg = fmInvImptDtlTMsgList.get(i);
            taxCalcOutputLinePMsg = taxcalcPMsg.taxCalculateOutputLine.no(i);
            taxCalcInputLinePMsg = taxcalcPMsg.taxCalculateInputLine.no(i);

            BigDecimal taxAmt = taxCalcOutputLinePMsg.invLineFuncTaxAmt.getValue();
            if (taxAmt == null) {
                taxAmt = BigDecimal.ZERO;
            }

            BigDecimal taxPct = taxCalcOutputLinePMsg.xxTaxCalcLineTaxPct.getValue();
            if (taxPct == null) {
                taxPct = BigDecimal.ZERO;
            }

            BigDecimal lineAmt = dtlTMsg.fmLineAmt.getValue();
            if (INV_TP.CREDIT_MEMO.equals(invHeaderPMsg.invTpCd.getValue())) {
                lineAmt = lineAmt.negate();
                taxAmt = taxAmt.negate();
            }

            BigDecimal qty = dtlTMsg.shipQty.getValue();
            if (!hasValue(qty)) {
                qty = BigDecimal.ONE;
            }

            param = new NWZC040003PMsg();
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
            // START 2018/01/23 T.Tsuchida [QC#23668,DEL]
            //ZYPEZDItemValueSetter.setValue(param.invNum, dtlTMsg.intfcLineAttrbTxt_15); // Add 2018/01/11 QC#22637
            // END 2018/01/23 T.Tsuchida [QC#23668,DEL]
            ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invShipmentPMsg.invBolLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(param.invLineNum, leftPad(Integer.toString(i + 1), NWCB037001Constant.DIGIT_INV_LINE_NUM, "0"));
            ZYPEZDItemValueSetter.setValue(param.invLineSubNum, NWCB037001Constant.FIXED_INV_LINE_SUB_NUM);
            ZYPEZDItemValueSetter.setValue(param.invLineSubTrxNum, NWCB037001Constant.FIXED_INV_LINE_SUB_TRX_NUM);
            ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, dtlTMsg.intfcLineAttrbTxt_02);
            ZYPEZDItemValueSetter.setValue(param.mdseCd, taxCalcInputLinePMsg.mdseCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(param.ordQty, qty);
            ZYPEZDItemValueSetter.setValue(param.shipQty, qty);
            ZYPEZDItemValueSetter.setValue(param.boQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.dealNetUnitPrcAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.invLineDealTaxAmt, taxAmt);
            ZYPEZDItemValueSetter.setValue(param.invLineDealNetAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.funcNetUnitPrcAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.invLineFuncTaxAmt, taxAmt);
            ZYPEZDItemValueSetter.setValue(param.invLineFuncNetAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.funcDiscUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.taxPct, taxPct);
            ZYPEZDItemValueSetter.setValue(param.firstBllgAttrbValTxt, dtlTMsg.intfcLineAttrbTxt_09);
            ZYPEZDItemValueSetter.setValue(param.scdBllgAttrbValTxt, dtlTMsg.intfcLineAttrbTxt_11);
            ZYPEZDItemValueSetter.setValue(param.thirdBllgAttrbValTxt, dtlTMsg.intfcLineAttrbTxt_12);
            ZYPEZDItemValueSetter.setValue(param.frthBllgAttrbValTxt, dtlTMsg.intfcLineAttrbTxt_13);
            ZYPEZDItemValueSetter.setValue(param.fifthBllgAttrbValTxt, dtlTMsg.intfcLineAttrbTxt_14);
            ZYPEZDItemValueSetter.setValue(param.sixthBllgAttrbValTxt, String.valueOf(dtlTMsg.fmArIntfcId.getValue()));
            ZYPEZDItemValueSetter.setValue(param.uomCd, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(param.manInvCratCmntTxt, dtlTMsg.fmDescTxt);
            // Add Start 2018/01/11 S21_NA#22609
            ZYPEZDItemValueSetter.setValue(param.dealGrsUnitPrcAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.dealGrsTotPrcAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.funcGrsUnitPrcAmt, lineAmt);
            ZYPEZDItemValueSetter.setValue(param.funcGrsTotPrcAmt, lineAmt);
            // Add End 2018/01/11 S21_NA#22609

            invLinePMsgList.add(param);
        }
    }

    /**
     * <pre>
     * Set Invoice Line Tax Detail Parameter
     * </pre>
     * @param invlineTaxPmsgList
     * @param invLinePMsgList
     * @param taxcalcPMsg
     */
    private void setInvLineTaxDtlParam(List<NWZC040005PMsg> invlineTaxPmsgList, List<NWZC040003PMsg> invLinePMsgList, NWZC036101PMsg taxcalcPMsg) {

        NWZC036101_taxCalculateOutputLinePMsg taxCalcOutputLinePMsg;
        NWZC040003PMsg invLinePMsg;
        NWZC040005PMsg param;
        List<BigDecimal> taxAmtList;
        List<BigDecimal> taxPctList;
        List<String> taxResultList;
        List<String> slsTaxTpList = new ArrayList<String>();
        slsTaxTpList.add(DS_SLS_TAX_TP.STATE_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.COUNTY_TAX);
        slsTaxTpList.add(DS_SLS_TAX_TP.CITY_TAX);

        for (int i = 0; i < invLinePMsgList.size(); ++i) {
            invLinePMsg = invLinePMsgList.get(i);
            taxCalcOutputLinePMsg = taxcalcPMsg.taxCalculateOutputLine.no(i);

            taxAmtList = new ArrayList<BigDecimal>();
            taxAmtList.add(taxCalcOutputLinePMsg.taxAmt_01.getValue());
            taxAmtList.add(taxCalcOutputLinePMsg.taxAmt_02.getValue());
            taxAmtList.add(taxCalcOutputLinePMsg.taxAmt_03.getValue());
            taxPctList = new ArrayList<BigDecimal>();
            taxPctList.add(taxCalcOutputLinePMsg.taxPct_01.getValue());
            taxPctList.add(taxCalcOutputLinePMsg.taxPct_02.getValue());
            taxPctList.add(taxCalcOutputLinePMsg.taxPct_03.getValue());
            taxResultList = new ArrayList<String>();
            taxResultList.add(taxCalcOutputLinePMsg.xxVtxRsltCd_01.getValue());
            taxResultList.add(taxCalcOutputLinePMsg.xxVtxRsltCd_02.getValue());
            taxResultList.add(taxCalcOutputLinePMsg.xxVtxRsltCd_03.getValue());

            for (int j = 0; j < taxResultList.size(); j++) {
                param = new NWZC040005PMsg();
                setValue(param.glblCmpyCd, this.glblCmpyCd);
                // START 2018/01/23 T.Tsuchida [QC#23668,DEL]
                //ZYPEZDItemValueSetter.setValue(param.invNum, invLinePMsg.invNum.getValue()); // Add 2018/01/11 QC#22637
                // END 2018/01/23 T.Tsuchida [QC#23668,DEL]
                setValue(param.invBolLineNum, invLinePMsg.invBolLineNum.getValue());
                setValue(param.invLineNum, invLinePMsg.invLineNum.getValue());
                setValue(param.invLineSubNum, invLinePMsg.invLineSubNum.getValue());
                setValue(param.invTrxLineSubNum, invLinePMsg.invLineSubTrxNum.getValue());

                BigDecimal taxAmt = taxAmtList.get(j);
                BigDecimal taxPct = taxPctList.get(j);
                if (taxAmt != null) {
                    setValue(param.dsSlsTaxTpCd, slsTaxTpList.get(j));
                    setValue(param.dealSlsTaxAmt, taxAmt);
                    setValue(param.funcSlsTaxAmt, taxAmt);
                    setValue(param.slsTaxPct, taxPct);
                    setValue(param.taxAreaId, taxCalcOutputLinePMsg.taxAreaId.getValue());
                    setValue(param.taxRsltDescTxt, taxResultList.get(j));
                    invlineTaxPmsgList.add(param);
                }
            }
        }
    }

    /**
     * <pre>
     * Set Invoice Line Serial Number Parameter
     * </pre>
     * @param invLineSerNumPMsgList
     * @param invLinePMsgList
     * @param fmInvImptDtlTMsgList
     */
    private void setInvLineSerNumParams(List<NWZC040006PMsg> invLineSerNumPMsgList, List<NWZC040003PMsg> invLinePMsgList, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList) {

        FM_INV_IMPT_DTLTMsg fmInvImptDtlTMsg;
        NWZC040003PMsg invLinePMsg;
        NWZC040006PMsg param;
        for (int i = 0; i < invLinePMsgList.size(); ++i) {
            fmInvImptDtlTMsg = fmInvImptDtlTMsgList.get(i);
            String serNum = fmInvImptDtlTMsg.intfcLineAttrbTxt_08.getValue();
            if (hasValue(serNum)) {
                invLinePMsg = invLinePMsgList.get(i);
                param = new NWZC040006PMsg();
                ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
                // START 2018/01/23 T.Tsuchida [QC#23668,DEL]
                //ZYPEZDItemValueSetter.setValue(param.invNum, invLinePMsg.invNum.getValue()); // Add 2018/01/11 QC#22637
                // END 2018/01/23 T.Tsuchida [QC#23668,DEL]
                ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invLinePMsg.invBolLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(param.invLineNum, invLinePMsg.invLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(param.invLineSubNum, invLinePMsg.invLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(param.invTrxLineSubNum, invLinePMsg.invLineSubTrxNum.getValue());
                ZYPEZDItemValueSetter.setValue(param.serNum, serNum);
                invLineSerNumPMsgList.add(param);
            }
        }
    }

    /**
     * <pre>
     * Set Invoice Sales Credit Parameter
     * </pre>
     * @param invlineTaxPmsgList List<NWZC040005PMsg>
     * @param invoiceBean InvoiceDataBean
     * @param dtlBean InvoiceLineDtlBean
     */
    private boolean setInvSalesCreditParam(List<NWZC040007PMsg> invSlsCrPMsgList, List<NWZC040003PMsg> invLinePMsgList, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<String> errMsgTxtList) {

        boolean isSuccess = true;
        NWZC040007PMsg param;

        for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
            int idx = fmInvImptDtlTMsgList.indexOf(dtlTMsg);
            NWZC040003PMsg invLinePMsg = invLinePMsgList.get(idx);
            param = new NWZC040007PMsg();
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
            // START 2018/01/23 T.Tsuchida [QC#23668,DEL]
            //ZYPEZDItemValueSetter.setValue(param.invNum, invLinePMsg.invNum.getValue()); // Add 2018/01/11 QC#22637
            // END 2018/01/23 T.Tsuchida [QC#23668,DEL]

            ZYPEZDItemValueSetter.setValue(param.invBolLineNum, invLinePMsg.invBolLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(param.invLineNum, invLinePMsg.invLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(param.invLineSubNum, invLinePMsg.invLineSubNum.getValue());
            ZYPEZDItemValueSetter.setValue(param.invLineSubTrxNum, invLinePMsg.invLineSubTrxNum.getValue());
            ZYPEZDItemValueSetter.setValue(param.mdseCd, invLinePMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.invLineSplPct, NWCB037001Constant.PCT_100);
            ZYPEZDItemValueSetter.setValue(param.slsRepTocCd, dtlTMsg.intfcLineAttrbTxt_02);
            BigDecimal slsRepCrPct = dtlTMsg.slsRepCrPct.getValue();
            if (!hasValue(slsRepCrPct)) {
                slsRepCrPct = NWCB037001Constant.PCT_100;
            }
            ZYPEZDItemValueSetter.setValue(param.slsRepCrPct, slsRepCrPct);
            ZYPEZDItemValueSetter.setValue(param.dealSlsCrAmt, invLinePMsg.invLineDealNetAmt.getValue());
            ZYPEZDItemValueSetter.setValue(param.funcSlsCrAmt, invLinePMsg.invLineFuncNetAmt.getValue());
            ZYPEZDItemValueSetter.setValue(param.dealCcyCd, CCY.US_DOLLAR);
            ZYPEZDItemValueSetter.setValue(param.dfrdAcctgRuleCd, dtlTMsg.dfrdAcctgRuleCd);
            if (hasValue(dtlTMsg.dfrdAcctgRuleCd)) {
                DFRD_ACCTG_RULETMsg condTMsg = new DFRD_ACCTG_RULETMsg();
                ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(condTMsg.dfrdAcctgRuleCd, dtlTMsg.dfrdAcctgRuleCd);

                DFRD_ACCTG_RULETMsg resTMsg = (DFRD_ACCTG_RULETMsg) S21FastTBLAccessor.findByKey(condTMsg);
                if (resTMsg != null) {
                    if (ZYPConstant.FLG_ON_Y.equals(resTMsg.dfrdRevFlg.getValue())) {
                        int durnAot = getDurnAot(dtlTMsg.bllgPerFromDt.getValue(), dtlTMsg.bllgPerThruDt.getValue());
                        ZYPEZDItemValueSetter.setValue(param.dfrdAcctgRuleDurnAot, BigDecimal.valueOf(durnAot));
                    }
                } else {
                    isSuccess = false;
                    String msgId = MSG_ID.NWCM0142E.toString();
                    String[] val = new String[] {new DFRD_ACCTG_RULETMsg().getTableName(), dtlTMsg.dfrdAcctgRuleCd.getValue() };
                    String addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                    setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
                }
            }
            ZYPEZDItemValueSetter.setValue(param.trxCd, TRX.SALES);
            ZYPEZDItemValueSetter.setValue(param.trxRsnCd, TRX_RSN.EMSD_REVENUE);

            invSlsCrPMsgList.add(param);
        }

        return isSuccess;
    }

    /**
     * getDurnAot
     * @param fromDt
     * @param thruDt
     * @return
     */
    private int getDurnAot(String fromDt, String thruDt) {
        int durnDt = 0;
        String dt = new String(fromDt);

        while (ZYPDateUtil.compare(dt, thruDt) < 0) {
            dt = addMonth(fromDt, ++durnDt);
        }

        return durnDt;
    }

    /**
     * addMonth
     * @param date
     * @param m
     * @return
     */
    private String addMonth(String date, int m) {

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(date, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYYY)) //
                , Integer.valueOf(ZYPDateUtil.DateFormatter(date, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MM)) - 1 //
                , Integer.valueOf(ZYPDateUtil.DateFormatter(date, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_DD)));

        cal.add(Calendar.MONTH, m);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(cal.getTime());

        return ymd;
    }

    /**
     * Set Tax Calculation API Parameter
     * @param taxcalcPMsg
     * @param fmInvImptDtlTMsgList
     * @param invHeaderPmsgList
     * @param errMsgTxtList
	 * @param shipInfoMap // Add 2018/01/11 QC#22609 shipInfoMap
     * @return
     */
    private boolean setTaxCalcApiParam(NWZC036101PMsg taxcalcPMsg, List<FM_INV_IMPT_DTLTMsg> fmInvImptDtlTMsgList, List<NWZC040001PMsg> invHeaderPmsgList, List<String> errMsgTxtList, Map<String, Object> shipInfoMap) {  // Add 2018/01/11 QC#22609 shipInfoMap

        boolean isSuccess = true;
        String msgId;
        String[] val;
        String addMsgTxt;
        NWZC040001PMsg invHeadePMsg = invHeaderPmsgList.get(0);
        FM_INV_IMPT_DTLTMsg dtlFirstTMsg = fmInvImptDtlTMsgList.get(0);

        setValue(taxcalcPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(taxcalcPMsg.slsDt, this.slsDt);
        setValue(taxcalcPMsg.xxModeCd, NWZC036101Constant.PROC_MODE_INVOICE);
        //Sell To Account Number
        setValue(taxcalcPMsg.dsAcctNum_SE, dtlFirstTMsg.fmAcctNum);
        //Bill To Account Number
        setValue(taxcalcPMsg.billToAcctNum, dtlFirstTMsg.billToCustAcctCd);
        //Bill To Location Number
        setValue(taxcalcPMsg.billToLocNum, dtlFirstTMsg.billToCustCd);
        //Bill to  Vertex Group Exemption
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("billToCustCd", taxcalcPMsg.billToLocNum.getValue());
        mapParam.put("billToAcctCd", taxcalcPMsg.billToAcctNum.getValue());
        String billToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getBillToTaxGrpExemCd", mapParam);
        setValue(taxcalcPMsg.dsTaxGrpExemCd_B, billToTaxExemGrpCd);

        //Bill to  Vertex Group Exemption Resale Flg
        String billToTaxGrpExemReslFlg = null;
        if (ZYPCommonFunc.hasValue(billToTaxExemGrpCd)) {
            DS_TAX_GRP_EXEMTMsg dsTaxGrpExemTmsg = new DS_TAX_GRP_EXEMTMsg();
            setValue(dsTaxGrpExemTmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(dsTaxGrpExemTmsg.dsTaxGrpExemCd, billToTaxExemGrpCd);
            dsTaxGrpExemTmsg = (DS_TAX_GRP_EXEMTMsg) S21CacheTBLAccessor.findByKey(dsTaxGrpExemTmsg);
            if (dsTaxGrpExemTmsg != null) {
                billToTaxGrpExemReslFlg = dsTaxGrpExemTmsg.dsTaxGrpExemReslFlg.getValue();
            }
        }
        setValue(taxcalcPMsg.dsTaxGrpExemReslFlg_B, billToTaxGrpExemReslFlg);
        //Ship To Account Number
        setValue(taxcalcPMsg.dsAcctNum_ST, dtlFirstTMsg.shipToCustAcctCd);
        //Ship To Location Number
        setValue(taxcalcPMsg.shipToLocNum, dtlFirstTMsg.shipToCustCd); // QC#18663

        //Ship to Vertex Group Exemption
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("shipToCustCd", taxcalcPMsg.shipToLocNum.getValue());
        mapParam.put("shipToAcctCd", taxcalcPMsg.dsAcctNum_ST.getValue());
        String shipToTaxExemGrpCd = (String) ssmBatchClient.queryObject("getShipToTaxGrpExemCd", mapParam);
        setValue(taxcalcPMsg.dsTaxGrpExemCd_ST, shipToTaxExemGrpCd);

        DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
        setValue(dsInvTpTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsInvTpTMsg.dsInvTpCd, invHeadePMsg.dsInvTpCd.getValue());
        dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
        if (dsInvTpTMsg == null) {
            // error
            isSuccess = false;
            msgId = MSG_ID.NWCM0142E.toString();
            val = new String[] {new DS_INV_TPTMsg().getTableName(), invHeadePMsg.dsInvTpCd.getValue() };
            for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
            }
        } else {
            //Tax Calculation Flag
            setValue(taxcalcPMsg.taxCalcFlg, dsInvTpTMsg.taxCalcFlg.getValue());
            //Tax Exemption
            setValue(taxcalcPMsg.taxExemFlg, dsInvTpTMsg.taxExemFlg.getValue());
            //Tax Exemption Reason Code
            setValue(taxcalcPMsg.taxExemRsnCd, dsInvTpTMsg.taxExemRsnCd.getValue());
        }
        //Transaction Date
        setValue(taxcalcPMsg.trxDt, this.slsDt);
        //Tax Calculate Header Num
        setValue(taxcalcPMsg.xxTaxCalcHdrNum, dtlFirstTMsg.intfcLineAttrbTxt_15);

        //get SHIP_TO_CUST & DS_SHIP_TO_CUST

        // Mod Start 201/01/11 S21_NA#22609
        String ctyAddr = (String) shipInfoMap.get("CTY_ADDR");
        BigDecimal cntyPk = (BigDecimal) shipInfoMap.get("CNTY_PK");
        String stCd = (String) shipInfoMap.get("ST_CD");
        // Ship To Tax Area ID
        setValue(taxcalcPMsg.taxAreaId_ST, getTaxAreaId(ctyAddr, cntyPk, stCd));
        setValue(taxcalcPMsg.geoCd_ST, (String) shipInfoMap.get("GEO_CD"));
        setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, (String) shipInfoMap.get("DS_INSD_CTY_LIMIT_FLG"));
        setValue(taxcalcPMsg.firstLineAddr_ST, (String) shipInfoMap.get("FIRST_LINE_ADDR"));
        setValue(taxcalcPMsg.scdLineAddr_ST, (String) shipInfoMap.get("SCD_LINE_ADDR"));
        setValue(taxcalcPMsg.ctyAddr_ST, ctyAddr);
        setValue(taxcalcPMsg.stCd_ST, stCd);
        setValue(taxcalcPMsg.cntyNm_ST, (String) shipInfoMap.get("CNTY_NM"));
        setValue(taxcalcPMsg.postCd_ST, (String) shipInfoMap.get("POST_CD"));
        setValue(taxcalcPMsg.ctryCd_ST, (String) shipInfoMap.get("CTRY_CD"));
//        mapParam = new HashMap<String, String>();
//        mapParam.put("glblCmpyCd", this.glblCmpyCd);
//        mapParam.put("shipToCustCd", dtlFirstTMsg.shipToCustCd.getValue());
//        mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject("getShipToInfo", mapParam);
//        if (mapRes == null) {
//            //error
//            isSuccess = false;
//            msgId = MSG_ID.NWCM0115E.toString();
//            val = new String[] {"ShipToInfo:" + dtlFirstTMsg.shipToCustCd.getValue() };
//            for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
//                addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
//                setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
//            }
//
//        } else {
//            String ctyAddr = (String) mapRes.get("CTY_ADDR");
//            BigDecimal cntyPk = (BigDecimal) mapRes.get("CNTY_PK");
//            String stCd = (String) mapRes.get("ST_CD");
//            setValue(taxcalcPMsg.taxAreaId_ST, getTaxAreaId(ctyAddr, cntyPk, stCd));
//            //Ship To Tax Area ID
//            setValue(taxcalcPMsg.geoCd_ST, (String) mapRes.get("GEO_CD"));
//            //Ship To Inside City Limit Flag
//            setValue(taxcalcPMsg.dsInsdCtyLimitFlg_ST, (String) mapRes.get("DS_INSD_CTY_LIMIT_FLG"));
//            //Ship to First Line Address
//            setValue(taxcalcPMsg.firstLineAddr_ST, (String) mapRes.get("FIRST_LINE_ADDR"));
//            //Ship to Second Line Address
//            setValue(taxcalcPMsg.scdLineAddr_ST, (String) mapRes.get("SCD_LINE_ADDR"));
//            //Ship to City Address
//            setValue(taxcalcPMsg.ctyAddr_ST, ctyAddr);
//            //Ship to State Code
//            setValue(taxcalcPMsg.stCd_ST, stCd);
//            //Ship To County Name
//            setValue(taxcalcPMsg.cntyNm_ST, (String) mapRes.get("CNTY_NM"));
//            //Ship To Post Code
//            setValue(taxcalcPMsg.postCd_ST, (String) mapRes.get("POST_CD"));
//            //Ship To Country Code
//            setValue(taxcalcPMsg.ctryCd_ST, (String) mapRes.get("CTRY_CD"));
//        }
        // Mod End 201/01/11 S21_NA#22609

        // get salesrep address from S21_PSN
        String tocCd = dtlFirstTMsg.intfcLineAttrbTxt_02.getValue();
        mapParam = new HashMap<String, String>();
        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("tocCd", tocCd);
        mapParam.put("invDt", this.slsDt);
        Map<String, Object> mapRes = (Map<String, Object>) ssmBatchClient.queryObject("getSalesRepAdress", mapParam);
        if (mapRes == null) {
            //error
            isSuccess = false;
            msgId = MSG_ID.NWCM0115E.toString();
            val = new String[] {"SalesRepAddress:tocCd=" + tocCd };
            for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {
                addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
            }

        } else {
            //Sales Rep Tax Area ID
            setValue(taxcalcPMsg.geoCd_SR, (String) mapRes.get("GEO_CD"));
            //Sales Rep Inside City Limit Flag
            setValue(taxcalcPMsg.dsInsdCtyLimitFlg_SR, (String) mapRes.get("DS_INSD_CTY_LIMIT_FLG"));
            //Sales Rep First Line Address
            setValue(taxcalcPMsg.firstLineAddr_SR, (String) mapRes.get("FIRST_LINE_ADDR"));
            //Sales Rep Second Line Address
            setValue(taxcalcPMsg.scdLineAddr_SR, (String) mapRes.get("SCD_LINE_ADDR"));
            //Sales Rep City Address
            setValue(taxcalcPMsg.ctyAddr_SR, (String) mapRes.get("CTY_ADDR"));
            //Sales Rep County Name
            setValue(taxcalcPMsg.cntyNm_SR, (String) mapRes.get("CNTY_NM"));
            //Sales Rep State Code
            setValue(taxcalcPMsg.stCd_SR, (String) mapRes.get("ST_CD"));
            //Sales Rep Post Code
            setValue(taxcalcPMsg.postCd_SR, (String) mapRes.get("POST_CD"));
            //Sales Rep Country Code
            setValue(taxcalcPMsg.ctryCd_SR, (String) mapRes.get("CTRY_CD"));

        }

        NWZC036101_taxCalculateInputLinePMsg taxCalcInputLinePMsg;
        int idx = 0;
        for (FM_INV_IMPT_DTLTMsg dtlTMsg : fmInvImptDtlTMsgList) {

            taxCalcInputLinePMsg = (NWZC036101_taxCalculateInputLinePMsg) taxcalcPMsg.taxCalculateInputLine.no(idx);
            //Tax Calculate Line Number
            setValue(taxCalcInputLinePMsg.xxTaxCalcLineNum_A, Integer.toString(idx));
            //Merchandise Code
            MDSETMsg mdseTMsg = getMdse(dtlTMsg.fmMdseCd.getValue());
            if (mdseTMsg == null) {
                //error
                isSuccess = false;
                msgId = MSG_ID.NWCM0142E.toString();
                val = new String[] {new MDSETMsg().getTableName(), dtlTMsg.fmMdseCd.getValue() };
                addMsgTxt = String.valueOf(dtlTMsg.fmArIntfcId.getValue());
                setErrMsg(msgId, val, addMsgTxt, errMsgTxtList);
            } else {
                setValue(taxCalcInputLinePMsg.mdseCd_A, mdseTMsg.mdseCd);

                String svcAllocTpCd = mdseTMsg.svcAllocTpCd.getValue();
                String taxExemTpCd = mdseTMsg.taxExemTpCd.getValue();
                String svcAllocTrxTpNm = null;
                if (svcAllocTpCd != null) {
                    SVC_ALLOC_TPTMsg svcAllocTpTMsg = new SVC_ALLOC_TPTMsg();
                    setValue(svcAllocTpTMsg.glblCmpyCd, this.glblCmpyCd);
                    setValue(svcAllocTpTMsg.svcAllocTpCd, svcAllocTpCd);
                    svcAllocTpTMsg = (SVC_ALLOC_TPTMsg) S21CacheTBLAccessor.findByKey(svcAllocTpTMsg);
                    if (svcAllocTpTMsg != null) {
                        svcAllocTrxTpNm = svcAllocTpTMsg.svcAllocTrxTpNm.getValue();
                    }
                }
                if (!hasValue(svcAllocTrxTpNm)) {
                    // default set if value is null
                    svcAllocTrxTpNm = this.defaultSvcAllocTrxTpNm;
                }

                //Service Allocation Type
                setValue(taxCalcInputLinePMsg.svcAllocTpCd_A, svcAllocTpCd);
                //Trx Type
                setValue(taxCalcInputLinePMsg.svcAllocTrxTpNm_A, svcAllocTrxTpNm);
                //Product Tax Code
                setValue(taxCalcInputLinePMsg.taxExemTpCd_A, taxExemTpCd);
            }

            //Shipped Quantity
            BigDecimal qty = dtlTMsg.shipQty.getValue();
            if (!hasValue(qty)) {
                qty = BigDecimal.ONE;
            }
            setValue(taxCalcInputLinePMsg.shipQty_A, qty);
            //Function Net Unit Price Amount
            setValue(taxCalcInputLinePMsg.funcNetUnitPrcAmt_A, dtlTMsg.fmLineAmt);
            //Sales Amount
            setValue(taxCalcInputLinePMsg.slsAmt_A, dtlTMsg.fmLineAmt);
            ++idx;
        }
        taxcalcPMsg.taxCalculateInputLine.setValidCount(idx);

        return isSuccess;
    }

    /**
     * getTaxAreaId
     * @param glblCmpyCd
     * @param shipToCtyAddr
     * @param shipToCntyPk
     * @param shipToStCd
     * @return
     */
    private String getTaxAreaId(String shipToCtyAddr, BigDecimal shipToCntyPk, String shipToStCd) {
        String taxAreaId = null;

        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("ctyAddr", shipToCtyAddr);
        inParam.put("cntyPk", shipToCntyPk);
        inParam.put("stCd", shipToStCd);

        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getTaxAreaId", inParam);
        if (resultMap == null) {
            inParam.put("ctyAddr", null);
            resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getTaxAreaId", inParam);
        }
        if (resultMap == null) {
            inParam.put("cntyPk", null);
            resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getTaxAreaId", inParam);
        }
        if (resultMap != null) {
            taxAreaId = (String) resultMap.get("TAX_AREA_ID");
        }

        return taxAreaId;
    }

    /**
     * getMdse
     * @param mdseCd
     * @return
     */
    private MDSETMsg getMdse(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        setValue(mdseTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            // get mdsecd
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            setValue(ordTakeMdseTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);

            if (ordTakeMdseTMsg != null) {
                mdseTMsg = new MDSETMsg();
                setValue(mdseTMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(mdseTMsg.mdseCd, ordTakeMdseTMsg.mdseCd.getValue());
                mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
            }

        }

        return mdseTMsg;
    }
 
    // 2018/01/25 QC#23413 add start
    private void updateHdr(BigDecimal hdrPk) {

        boolean isNormalEnd = !hasValue(this.hdrErrMsgTxt);
        String[] condColName;
        String[] updColName;


        // update header
        FM_INV_IMPT_HDRTMsg hdrCondTMsg = new FM_INV_IMPT_HDRTMsg();
        setValue(hdrCondTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(hdrCondTMsg.fmInvImptHdrPk, hdrPk);
        condColName = new String[] {"glblCmpyCd", "fmInvImptHdrPk" };
        FM_INV_IMPT_HDRTMsg hdrUpdTMsg = (FM_INV_IMPT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdate(hdrCondTMsg);

        if (ZYPConstant.FLG_OFF_N.equals(hdrUpdTMsg.procErrFlg.getValue())) {
            //update flg N,N/Y,N  to N,Y/Y,N
            if (isNormalEnd) {
                setValue(hdrUpdTMsg.invImptProcFlg, ZYPConstant.FLG_ON_Y);
                setValue(hdrUpdTMsg.procErrFlg, ZYPConstant.FLG_OFF_N);
                updColName = new String[] {"invImptProcFlg", "procErrFlg" };
            } else {
                setValue(hdrUpdTMsg.invImptProcFlg, ZYPConstant.FLG_OFF_N);
                setValue(hdrUpdTMsg.procErrFlg, ZYPConstant.FLG_ON_Y);
                setValue(hdrUpdTMsg.procErrMsgTxt, this.hdrErrMsgTxt);
                updColName = new String[] {"invImptProcFlg", "procErrFlg", "procErrMsgTxt" };
            }

            int result = S21FastTBLAccessor.updateByPartialValue(hdrCondTMsg, condColName, hdrUpdTMsg, updColName);

            if (result == 0) {
                String tableName = hdrUpdTMsg.getTableName();
                String pk = String.valueOf(hdrCondTMsg.fmInvImptHdrPk.getValue());
                throw new S21AbendException(MSG_ID.NWCM0110E.toString(), new String[] {tableName, pk });
            }

            this.hdrErrMsgTxt = null;
        }
        commit();
    }
    // 2018/01/25 QC#23413 add end

}
