/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB724001;

import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.BATCH_PROGRAM_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.BATCH_PROGRAM_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.BLANK;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CRLF;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CATG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CONTR_STS_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CR_LIST_GEN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CR_LIST_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CSA_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CSA_USR_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_CUSA_USR_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_DLR_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_ERR_MSG_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_PREV_CUSA_USR_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_PREV_RCV_CONTR_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_RCV_CONTR_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_RG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_TRX_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.CSMP_VLD_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.DLR_REF_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.DLR_REF_NUM_MAX_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.INTFC_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_FIELD_BATCH_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_FIELD_BATCH_PROC_LOG_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_FIELD_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_TYPE_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.NMAM8028E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.NMAM8031E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.NMAM8504E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.NMAM8505E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.NMAM8547E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.SUBSTR_FROM_POS;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.TRX_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.TRX_ID_LENGTH_FOR_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB724001.constant.NMAB724001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CSMP_CONTR_INTFCTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
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
 * I105 CSMP Inbound Contract I/F (WMB)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Fujitsu         W.Honda         Create          N/A
 * 2016/05/26   Fujitsu         W.Honda         Update          QC#8357,#8446
 * 2016/05/27   Fujitsu         W.Honda         Update          QC#8582
 * 2016/06/01   Fujitsu         W.Honda         Update          QC#9314
 *</pre>
 */

public class NMAB724001 extends S21BatchMain {

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
    // QC#8357,#8446 2016/05/26 Mod start

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
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB724001().executeBatch(NMAB724001.class.getSimpleName());

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
                stmt = this.ssmLLClient.createPreparedStatement("getReceiveCSMPContract", ssmParam, execParam);
                rsSet = stmt.executeQuery();

                while (rsSet.next()) {
                    workTotalCount++;

                    CSMP_CONTR_INTFCTMsg workTMsg = new CSMP_CONTR_INTFCTMsg();

                    BigDecimal csmpContrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_CONTR_INTFC_SQ);

                    ZYPEZDItemValueSetter.setValue(workTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpContrIntfcPk, csmpContrIntfcPk);
                    String csmpCsaNum = rsSet.getString(CSMP_CSA_NUM);
                    boolean overflowFlg = checkOverflow(csmpCsaNum);
                    if (overflowFlg) {
                        ZYPEZDItemValueSetter.setValue(workTMsg.dlrRefNum, csmpCsaNum.substring(SUBSTR_FROM_POS, workTMsg.getAttr(DLR_REF_NUM).getDigit()));
                    } else {
                        ZYPEZDItemValueSetter.setValue(workTMsg.dlrRefNum, csmpCsaNum);
                    }
                    ZYPEZDItemValueSetter.setValue(workTMsg.dsAcctNm, rsSet.getString(CSMP_CSA_USR_NM));
                    ZYPEZDItemValueSetter.setValue(workTMsg.cusaEndUsrNum, rsSet.getString(CSMP_CUSA_USR_NUM));
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpNum, rsSet.getString(CSMP_RCV_CONTR_NUM));
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpContrStsCd, rsSet.getString(CSMP_CONTR_STS_TXT));
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpRgCd, rsSet.getString(CSMP_RG_CD));
                    ZYPEZDItemValueSetter.setValue(workTMsg.rtlDlrCd, rsSet.getString(CSMP_DLR_TXT));
                    // QC#8582 2016/05/26 Mod start
//                    ZYPEZDItemValueSetter.setValue(workTMsg.effFromDt, rsSet.getString(CSMP_EFF_FROM_DT));
//                    ZYPEZDItemValueSetter.setValue(workTMsg.effThruDt, rsSet.getString(CSMP_EFF_THRU_DT));
                    String formatedDate = dateFormat(rsSet.getString(CSMP_EFF_FROM_DT), rsSet.getString(TRANSACTION_ID), CSMP_EFF_FROM_DT, csmpCsaNum, ZYPDateUtil.TYPE_YYYYMMDD);
                    ZYPEZDItemValueSetter.setValue(workTMsg.effFromDt, formatedDate);
                    formatedDate = dateFormat(rsSet.getString(CSMP_EFF_THRU_DT), rsSet.getString(TRANSACTION_ID), CSMP_EFF_THRU_DT, csmpCsaNum, ZYPDateUtil.TYPE_YYYYMMDD);
                    ZYPEZDItemValueSetter.setValue(workTMsg.effThruDt, formatedDate);
                    // QC#8582 2016/05/26 Mod start
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpCatgCd, rsSet.getString(CSMP_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(workTMsg.crListTxt, rsSet.getString(CSMP_CR_LIST_TXT));
                    ZYPEZDItemValueSetter.setValue(workTMsg.crListGnrnNum, rsSet.getString(CSMP_CR_LIST_GEN_NUM));
                    // QC#8582 2016/05/26 Mod start
//                    ZYPEZDItemValueSetter.setValue(workTMsg.vldFromDt, rsSet.getString(CSMP_VLD_FROM_DT));
                    formatedDate = dateFormat(rsSet.getString(CSMP_VLD_FROM_DT), rsSet.getString(TRANSACTION_ID), CSMP_VLD_FROM_DT, csmpCsaNum, ZYPDateUtil.TYPE_YYYYMMDD);
                    ZYPEZDItemValueSetter.setValue(workTMsg.vldFromDt, formatedDate);
                    // QC#8582 2016/05/26 Mod end
                    ZYPEZDItemValueSetter.setValue(workTMsg.prevCsmpNum, rsSet.getString(CSMP_PREV_RCV_CONTR_NUM));
                    ZYPEZDItemValueSetter.setValue(workTMsg.prevUsrTxt, rsSet.getString(CSMP_PREV_CUSA_USR_NUM));
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpTrxStsCd, rsSet.getString(CSMP_TRX_TXT));
                    // QC#8582 2016/05/26 Mod start
//                    ZYPEZDItemValueSetter.setValue(workTMsg.lastUpdTs, rsSet.getString(CSMP_LAST_UPD_TS));
                    formatedDate = timestampFormat(rsSet.getString(CSMP_LAST_UPD_TS), rsSet.getString(TRANSACTION_ID), CSMP_LAST_UPD_TS, csmpCsaNum);
                    ZYPEZDItemValueSetter.setValue(workTMsg.lastUpdTs, formatedDate);
                    // QC#8582 2016/05/26 Mod end
                    ZYPEZDItemValueSetter.setValue(workTMsg.csmpProcStsCd, CSMP_PROC_STS.NEW);
                    if (overflowFlg) {
                        String errMsgTxt = S21MessageFunc.clspGetMessage(NMAM8505E, new String[] {rsSet.getString("CSMP_CSA_NUM")});
                        if (workTMsg.getAttr(CSMP_ERR_MSG_TXT).getDigit() < errMsgTxt.length()) {
                            ZYPEZDItemValueSetter.setValue(workTMsg.csmpErrMsgTxt, errMsgTxt.substring(SUBSTR_FROM_POS, workTMsg.getAttr(CSMP_ERR_MSG_TXT).getDigit()));
                        } else {
                            ZYPEZDItemValueSetter.setValue(workTMsg.csmpErrMsgTxt, errMsgTxt);
                        }
                        // QC#9314 2016/06/01 Mod start
                        addErrorMessage(rsSet.getString(TRANSACTION_ID), NMAM8505E, new String[] {rsSet.getString("CSMP_CSA_NUM")});
                        // QC#9314 2016/06/01 Mod end
                    }

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

                    // QC#8357,#8446 2016/05/26 Del start
//                    workTMsg.clear();
                    // QC#8357,#8446 2016/05/26 Del end
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

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);
    }

    private boolean checkOverflow(String csmpCsaNum) {
        // Overflow Check
        if (ZYPCommonFunc.hasValue(csmpCsaNum)
                && DLR_REF_NUM_MAX_LENGTH < csmpCsaNum.length()) {
            return true;
        }

        return false;
    }

    // QC#8582 2016/05/27 Add start
    private String dateFormat(String date, String trxId, String columnName, String csmpNum, String format) {
        if (!ZYPCommonFunc.hasValue(date)) {
            addErrorMessage(trxId, NMAM8547E, new String[] {columnName, csmpNum, date});
            return BLANK;
        }

        if (!ZYPDateUtil.isValidDate(date, format)) {
            addErrorMessage(trxId, NMAM8547E, new String[] {columnName, csmpNum, date});
            return BLANK;
        }

        return date;
    }

    private String timestampFormat(String date, String trxId, String columnName, String csmpNum) {
        if (!ZYPCommonFunc.hasValue(date)) {
            addErrorMessage(trxId, NMAM8547E, new String[] {columnName, csmpNum, date});
            return BLANK;
        }

        if (!checkTime(date)) {
            addErrorMessage(trxId, NMAM8547E, new String[] {columnName, csmpNum, date});
            return BLANK;
        }

        return date;
    }

    /**
     * Check if correct time or not(yyyyMMddhhmmss)
     * @param str String
     * @return boolean
     */
    private boolean checkTime(String str) {

        if (str.length() != 14) {
            return false;
        }
        if (!ZYPDateUtil.checkDate(str.substring(0, 8))) {
            return false;
        }

        if (str.length() == 14) {
            try {
                int hh = Integer.parseInt(str.substring(8, 10));
                int mm = Integer.parseInt(str.substring(10, 12));
                int ss = Integer.parseInt(str.substring(12, 14));

                if (hh < 0 || hh > 23) {
                    return false;
                }
                if (mm < 0 || mm > 59) {
                    return false;
                }
                if (ss < 0 || ss > 59) {
                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    // QC#8582 2016/05/27 Add end

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
        // QC#8357,#8446 2016/05/26 Add end

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
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, errMsg.toString());

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

}
