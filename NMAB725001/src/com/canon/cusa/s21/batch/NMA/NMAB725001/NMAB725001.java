/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB725001;

import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.AUTO_SEQ_KEY;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.BATCH_PROGRAM_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.BATCH_PROGRAM_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CRLF;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CR_LIST_GNRN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CR_LIST_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CSMP_CR_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CSMP_CUSA_MDSE_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CSMP_ERR_MSG_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CSMP_RTL_DLR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CSMP_TRX_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CUSA_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CUSA_MDSE_CD_MAX_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.CUSA_MDSE_CD_SUBSTR_FROM_POS;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.INTFC_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.LAST_UPD_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_FIELD_BATCH_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_FIELD_BATCH_PROC_LOG_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_FIELD_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_TYPE_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.NMAM8028E;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.NMAM8031E;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.NMAM8504E;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.NMAM8512E;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.SUBSTR_FROM_POS;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.TRX_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.TRX_ID_LENGTH_FOR_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB725001.constant.NMAB725001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CSMP_PRC_INTFCTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 * I95 CSMP Inbound Credit Price List IF (WMB)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         W.Honda         Create          N/A
 * 2016/05/26   Fujitsu         W.Honda         Create          QC#8357,#8446
 *</pre>
 */

public class NMAB725001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** interface Id */
    private String interfaceId = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    // QC#8357,#8446 2016/05/26 Mod start
    /** error message */
//    StringBuilder errMsg = new StringBuilder();
    private StringBuilder errMsg = null;
    // QC#8357,#8446 2016/05/26 Mod end

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // QC#8357,#8446 2016/05/26 Mod start
    /** S21TransactionTableAccessor */
//    private S21TransactionTableAccessor tblAccessor;
    private S21TransactionTableAccessor tblAccessor = null;
    // QC#8357,#8446 2016/05/26 Mod end

    // QC#8357,#8446 2016/05/26 Del start
//    /** S21MailAddress(from) */
//    private S21MailAddress mailAddrFrom = null;
//
//    /** S21MailAddress(To) */
//    private List<S21MailAddress> mailAddrToList = null;
//
//    /** S21MailTemplate(To) */
//    private S21MailTemplate maiTemplate = null;
    // QC#8357,#8446 2016/05/26 Del end

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB725001().executeBatch(NMAB725001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD});
        }

        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(NMAM8504E, new String[] {INTFC_ID});
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {GLBL_CMPY_CD});
        }

        tblAccessor = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // QC#8357,#8446 2016/05/26 Del start
//        // Get From Mail Address.
//        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
//        groupFrom.setMailKey1(MAIL_KEY_FROM);
//        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
//        if (addrFromList == null || addrFromList.isEmpty()) {
//            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_FROM, MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
//        }
//        this.mailAddrFrom = addrFromList.get(0);
//
//        // Get To Mail Address.
//        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
//        this.mailAddrToList = groupTo.getMailAddress();
//        if (this.mailAddrToList == null || this.mailAddrToList.isEmpty()) {
//            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, HYPHEN });
//        }
//
//        // Get Template
//        this.maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
//        if (!ZYPCommonFunc.hasValue(this.maiTemplate.getBody())) {
//            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ID});
//        }
        // QC#8357,#8446 2016/05/26 Del end

    }

    @Override
    protected void mainRoutine() {
        BigDecimal[] transactionIdList = tblAccessor.getIntegrationRecord(this.interfaceId);

        if (transactionIdList.length == 0) {
            this.termCd = TERM_CD.NORMAL_END;
            return;
        }

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            for (BigDecimal intfcTrxId : transactionIdList) {
                int workTotalCount = 0;
                boolean insertErr = false;

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

                execParam.setFetchSize(DEFAULT_FETCH_SIZE);
                execParam.setMaxRows(0);
                execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put("interfaceId", this.interfaceId);
                ssmParam.put("transactionId", intfcTrxId);
                stmt = this.ssmLLClient.createPreparedStatement("getReceiveCSMPPriceList", ssmParam, execParam);
                rsSet = stmt.executeQuery();

                while (rsSet.next()) {
                    workTotalCount++;

                    CSMP_PRC_INTFCTMsg workTMsg = new CSMP_PRC_INTFCTMsg();

                    BigDecimal csmpPrcIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_PRC_INTFC_SQ);

                    ZYPEZDItemValueSetter.setValue(workTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpPrcIntfcPk, csmpPrcIntfcPk);

                    ZYPEZDItemValueSetter.setValue(workTMsg.rtlDlrCd, rsSet.getString(CSMP_RTL_DLR_CD));
                    ZYPEZDItemValueSetter.setValue(workTMsg.crListGnrnNum, rsSet.getString(CR_LIST_GNRN_NUM));
                    ZYPEZDItemValueSetter.setValue(workTMsg.crListTxt, rsSet.getString(CR_LIST_TXT));

                    String csmpCusaMdseTxt = rsSet.getString(CSMP_CUSA_MDSE_TXT);
                    boolean overflowFlg = checkOverflow(csmpCusaMdseTxt);
                    if (overflowFlg) {
                        ZYPEZDItemValueSetter.setValue(workTMsg.cusaMdseCd, csmpCusaMdseTxt.substring(CUSA_MDSE_CD_SUBSTR_FROM_POS, CUSA_MDSE_CD_MAX_LENGTH));
                    } else {
                        ZYPEZDItemValueSetter.setValue(workTMsg.cusaMdseCd, csmpCusaMdseTxt);
                    }

                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpCrAmt, rsSet.getBigDecimal(CSMP_CR_AMT));
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpTrxStsCd, rsSet.getString(CSMP_TRX_TP_CD));
                    ZYPEZDItemValueSetter.setValue(workTMsg.lastUpdDt, rsSet.getString(LAST_UPD_DT));

                    if (overflowFlg) {
                        ZYPEZDItemValueSetter.setValue(workTMsg.csmpProcStsCd, CSMP_PROC_STS.ERROR);
                        String errMsgTxt = S21MessageFunc.clspGetMessage(NMAM8512E, new String[] {CUSA_MDSE_CD});
                        if (workTMsg.getAttr(CSMP_ERR_MSG_TXT).getDigit() < errMsgTxt.length()) {
                            ZYPEZDItemValueSetter.setValue(workTMsg.csmpErrMsgTxt, errMsgTxt.substring(SUBSTR_FROM_POS, workTMsg.getAttr(CSMP_ERR_MSG_TXT).getDigit()));
                        } else {
                            ZYPEZDItemValueSetter.setValue(workTMsg.csmpErrMsgTxt, errMsgTxt);
                        }
                        ZYPEZDItemValueSetter.setValue(workTMsg.csmpErrMsgTxt, S21MessageFunc.clspGetMessage(NMAM8512E, new String[] {CUSA_MDSE_CD}));
                        // QC#8357,#8446 2016/05/26 Mod start
                        addErrorMessage(rsSet.getString(TRANSACTION_ID), NMAM8512E, new String[] {CUSA_MDSE_CD});
                        // QC#8357,#8446 2016/05/26 Mod end
                    } else {
                        ZYPEZDItemValueSetter.setValue(workTMsg.csmpProcStsCd, CSMP_PROC_STS.NEW);
                    }
                    String csmpPrcCd = ZYPNumbering.getUniqueID(this.glblCmpyCd, AUTO_SEQ_KEY);
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpPrcListId, csmpPrcCd);

                    EZDTBLAccessor.insert(workTMsg);
                    // QC#8357,#8446 2016/05/26 Mod start
//                    if (!workTMsg.getReturnCode().equals(EZDTBLAccessor.RTNCD_NORMAL)) {
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(workTMsg.getReturnCode())) {
                    // QC#8357,#8446 2016/05/26 Mod end
                        addErrorMessage(rsSet.getString(TRANSACTION_ID), NMAM0176E, new String[] {workTMsg.getTableName()});
                        S21InfoLogOutput.println(NMAM0176E, new String[] {(String) rsSet.getString(TRANSACTION_ID)});
                        insertErr = true;
                        break;
                    }
                }

                S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);

                if (insertErr) {
                    rollback();
                    this.errorCount = this.errorCount + 1;
                    workTotalCount = 1;
                } else {
                    tblAccessor.endIntegrationProcess(this.interfaceId, intfcTrxId);
                    commit();
                }
                this.totalCount = this.totalCount + workTotalCount;
            }


        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            this.successCount = this.totalCount - this.errorCount;
        }

        // QC#8357,#8446 2016/05/26 Mod start
//        if (ZYPCommonFunc.hasValue(this.errMsg.toString())) {
        if (this.errMsg != null) {
            sendErrorMail();
            commit();
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
//        } else {
//            this.termCd = TERM_CD.NORMAL_END;
//        }
        // QC#8357,#8446 2016/05/26 Mod end
    }

    private boolean checkOverflow(String csmpCusaMdseTxt) {
        // Overflow Check
        if (ZYPCommonFunc.hasValue(csmpCusaMdseTxt)
                && CUSA_MDSE_CD_MAX_LENGTH < csmpCusaMdseTxt.length()) {
            return true;
        }

        return false;
    }

    private void addErrorMessage(String trxId, String msgID, String[] param) {
        if (trxId == null) {
            trxId = "";
        }

        // QC#8357,#8446 2016/05/26 Mod start
//        if (this.errMsg.length() == 0) {
        if (this.errMsg == null) {
            this.errMsg = new StringBuilder();
        // QC#8357,#8446 2016/05/26 Mod end
            this.errMsg.append(TRX_ID);
            this.errMsg.append(" ");
            this.errMsg.append(ERR_MSG);
            this.errMsg.append(CRLF);
        }

        this.errMsg.append("    ");
        this.errMsg.append(trxId);
        int trxIdInterval = 1;
        if (TRX_ID_LENGTH_FOR_ERR > trxId.length()) {
            trxIdInterval = TRX_ID_LENGTH_FOR_ERR - trxId.length();
        }
        for (int i = 0; i < trxIdInterval; i++) {
            this.errMsg.append(" ");
        }
        this.errMsg.append(S21MessageFunc.clspGetMessage(msgID, param));
        this.errMsg.append(CRLF);
    }

    // QC#8357,#8446 2016/05/26 Mod start
    private void sendErrorMail() {
        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_FROM, MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
        }
        S21MailAddress mailAddrFrom = addrFromList.get(0);

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, HYPHEN });
        }

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ID});
        }

//        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(this.mailAddrToList);
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        // Set Message
//        this.maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_ID, BATCH_PROGRAM_ID);
//        this.maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_NAME, BATCH_PROGRAM_NAME);
//        this.maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_PROC_LOG_ID, super.getBatchProcessLogID());
//        this.maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, this.errMsg.toString());
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_ID, BATCH_PROGRAM_ID);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_NAME, BATCH_PROGRAM_NAME);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_PROC_LOG_ID, super.getBatchProcessLogID());
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, this.errMsg.toString());

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
//        mail.setFromAddress(this.mailAddrFrom);
//        mail.setToAddressList(addrToList);
//        mail.setMailTemplate(this.maiTemplate);
//        mail.setSubject(this.maiTemplate.getSubject(), MAIL_CHARSET);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }
    // QC#8357,#8446 2016/05/26 Mod end

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);

    }

}
