/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB495001;

import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.BATCH_PROGRAM_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.BATCH_PROGRAM_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.CRLF;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.DEFAULT_COMMIT_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.INTFC_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.JOB_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_BATCH_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_BATCH_PROC_LOG_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_BATCH_TBL_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_ERR_INFO;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_INFO;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_MSG_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_FIELD_MSG_TRX_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_TEMPLATE_ID_NORMAL;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_TEMPLATE_ID_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_TYPE_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.NMAM8028E;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.NMAM8031E;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.NMAM8504E;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.NMAM8571E;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.TRX_ID_LENGTH_FOR_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_KEY_TO_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB495001.constant.NMAB495001Constant.MAIL_KEY_TO_NORMAL;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DUNS_RCV_INFOTMsg;
import business.db.DUNS_TRX_FILETMsg;
import business.db.DUNS_TRX_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_FILE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Load DUNS by batch(Inbound)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         H.Ikeda         Create          N/A
 * 2016/06/29   Fujitsu         H.Ikeda         Update          QC#10162
 * 2016/09/28   Fujitsu         H.Ikeda         Update          QC#14852
 *
 *</pre>
 */

public class NMAB495001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** interface Id */
    private String interfaceId = null;

    /** commitCount */
    private int commitCnt = 0;

    /** batchProcessLog Id */
    private String batchProcessLogId = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** error message */
    private StringBuilder errMsg = null;

    /** SSM LowLevel Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor tblAccessor = null;

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB495001().executeBatch(NMAB495001.class.getSimpleName());

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

        this.commitCnt = getCommitCount();
        if (this.commitCnt <= 0 || DEFAULT_COMMIT_SIZE < this.commitCnt) {
            this.commitCnt = DEFAULT_COMMIT_SIZE;
        }

        tblAccessor = new S21TransactionTableAccessor();

        batchProcessLogId = super.getBatchProcessLogID();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // transactionID(List)
        BigDecimal[] transactionIdList = tblAccessor.getIntegrationRecord(this.interfaceId);
        if (transactionIdList.length == 0) {
            this.termCd = TERM_CD.NORMAL_END;
            return;
        }
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        String dataId = null;
        int workTotalCount = 0;
        String oldDataId = null;
        BigDecimal dunsTrxHdrSq = null;
        BigDecimal dunsTrxFileSq = null;
        BigDecimal dataCnt = null;
        DUNS_TRX_HDRTMsg workHeaderTMsg = new DUNS_TRX_HDRTMsg();
        DUNS_TRX_FILETMsg workFileTMsg = new DUNS_TRX_FILETMsg();
        List<BigDecimal> keyList = new ArrayList<BigDecimal>();
        List<String> fileNameList = null;
        List<String> sysDateList = null;
        List<BigDecimal> lineNumList = null;

        int rcvInfoCnt = 0;
        
        try {
            for (BigDecimal trxId : transactionIdList) {
                workTotalCount = 0;
                dataCnt = BigDecimal.ZERO;
                boolean insertErr = false;
                oldDataId = null;
                fileNameList = new ArrayList<String>();
                sysDateList = new ArrayList<String>();
                lineNumList = new ArrayList<BigDecimal>();

                // get IF
                rsSet = getInterFaceData(trxId, stmt);
                while (rsSet.next()) {
                    workTotalCount++;
                    // DataID
                    dataId = batchProcessLogId + rsSet.getString("TRANSACTION_ID") + rsSet.getString("SEGMENT_ID");
                    if (dataId.equals(oldDataId)) {
                        dataCnt = dataCnt.add(BigDecimal.ONE);
                    } else {
                        if (ZYPCommonFunc.hasValue(oldDataId)) {
                            if (insertHeader(workHeaderTMsg, rsSet.getString("TRANSACTION_ID"), DUNS_PROC_STS.DONE)) {
                                insertErr = true;
                                break;
                            }
                            // Update cnt
                            if (insertFile(workFileTMsg, String.valueOf(trxId), dataCnt)) {
                                insertErr = true;
                                break;
                            }
                            super.commit();
                            lineNumList.add(dataCnt);
                        }

                        oldDataId = dataId;
                        dataCnt = BigDecimal.ONE;
                        // headSQ
                        dunsTrxHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUNS_TRX_HDR_SQ);
                        keyList.add(dunsTrxHdrSq);
                        // Header Set
                        workHeaderTMsg = new DUNS_TRX_HDRTMsg();
                        String sysDate = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);
                        setDunsTrxHdrTMsg(workHeaderTMsg, dunsTrxHdrSq, sysDate);
                        sysDateList.add(sysDate);

                        // FileSQ
                        dunsTrxFileSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUNS_TRX_FILE_SQ);
                        // File Set
                        String fileName = getFileName(rsSet.getBigDecimal("TRANSACTION_ID"), rsSet.getBigDecimal("SEGMENT_ID"));
                        fileNameList.add(fileName);
                        workFileTMsg = new DUNS_TRX_FILETMsg();
                        setDunsTrxFileTMsg(workFileTMsg, dunsTrxHdrSq, fileName, dunsTrxFileSq);
                    }

                    // insert(Info)
                    DUNS_RCV_INFOTMsg workInfoTMsg = new DUNS_RCV_INFOTMsg();
                    setDunsInfoTblTMsg(workInfoTMsg, dunsTrxHdrSq, rsSet);
                    EZDTBLAccessor.insert(workInfoTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(workInfoTMsg.getReturnCode())) {
                        createErrorMessage(rsSet.getString("TRANSACTION_ID"), NMAM8571E, new String[] {MAIL_FIELD_BATCH_TBL_NAME, rsSet.getString("LOC_NUM")});
                        insertErr = true;
                        break;
                    } else {
                        rcvInfoCnt++;
                    }

                    if (insertErr) {
                        insertHeader(workHeaderTMsg, String.valueOf(trxId), DUNS_PROC_STS.ERROR);
                        sendErrorMail();
                        this.errorCount = 1;
                        super.commit();
                        break;
                    } else {
                        if (rcvInfoCnt == this.commitCnt) {
                            super.commit();
                            rcvInfoCnt = 0;
                        }
                    }
                }
                // Mod Start 2016/09/28 QC#14852
                if (!ZYPCommonFunc.hasValue(oldDataId)) {
                    tblAccessor.endIntegrationProcess(this.interfaceId, trxId);
                    continue;
                }
                // Mod End 2016/09/28 QC#14852
                // commit
                if (!insertErr) {
                    // Insert Header
                    insertHeader(workHeaderTMsg, String.valueOf(trxId), DUNS_PROC_STS.DONE);
                    // Insert File
                    insertFile(workFileTMsg, String.valueOf(trxId), dataCnt);
                    lineNumList.add(dataCnt);
                    if (insertErr) {
                        sendErrorMail();
                    } else {
                        sendMail(fileNameList, sysDateList, lineNumList);
                    }
                    super.commit();
                } else {
                    super.rollback();
                    insertHeader(workHeaderTMsg, String.valueOf(trxId), DUNS_PROC_STS.ERROR);
                    sendErrorMail();
                    this.errorCount = 1;
                    super.commit();
                }
                // close
                S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);

                tblAccessor.endIntegrationProcess(this.interfaceId, trxId);
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            this.totalCount = this.totalCount + workTotalCount;
            this.successCount = this.totalCount - this.errorCount;
        }

        if (this.errMsg != null) {
            this.termCd = TERM_CD.WARNING_END;
        } else {
            this.termCd = TERM_CD.NORMAL_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);
    }

    private boolean insertHeader(DUNS_TRX_HDRTMsg workHeaderTMsg, String trnId, String sts) {
        ZYPEZDItemValueSetter.setValue(workHeaderTMsg.dunsProcStsCd, sts);
        EZDTBLAccessor.insert(workHeaderTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(workHeaderTMsg.getReturnCode())) {
            createErrorMessage(trnId, NMAM8571E, new String[] {workHeaderTMsg.getTableName()});
            return true;
        }
        return false;
    }

    private boolean insertFile(DUNS_TRX_FILETMsg workFileTMsg, String trnId, BigDecimal cnt) {
        ZYPEZDItemValueSetter.setValue(workFileTMsg.dunsFileLineNum, cnt);
        EZDTBLAccessor.insert(workFileTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(workFileTMsg.getReturnCode())) {
            createErrorMessage(trnId, NMAM8571E, new String[] {workFileTMsg.getTableName()});
            return true;
        }
        return false;
    }

    private ResultSet getInterFaceData(BigDecimal trxId, PreparedStatement stmt) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.interfaceId);
        ssmParam.put("transactionId", trxId);

        stmt = this.ssmLLClient.createPreparedStatement("getInterFaceData", ssmParam, execParam);
        return stmt.executeQuery();
    }

    private void setDunsTrxHdrTMsg(DUNS_TRX_HDRTMsg workHeaderTMsg, BigDecimal dunsTrxHdrSq, String sysDate) {
        ZYPEZDItemValueSetter.setValue(workHeaderTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(workHeaderTMsg.dunsTrxHdrPk, dunsTrxHdrSq);
        ZYPEZDItemValueSetter.setValue(workHeaderTMsg.dunsProcTpCd, DUNS_PROC_TP.RECEIVE_AND_IMOORT_DNB_FILE);
        workHeaderTMsg.dunsProcStsCd.clear();
        ZYPEZDItemValueSetter.setValue(workHeaderTMsg.dunsProcTs, sysDate);
        ZYPEZDItemValueSetter.setValue(workHeaderTMsg.dunsProcById, JOB_ID);
        workHeaderTMsg.dunsProcCmntTxt.clear();
    }

    private String getFileName(BigDecimal trxId, BigDecimal segmentId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("transactionId", trxId);
        ssmParam.put("segmentId", segmentId);

        return (String) this.ssmBatchClient.queryObject("getRemoteFileName", ssmParam);
    }

    private void setDunsTrxFileTMsg(DUNS_TRX_FILETMsg workFileTMsg, BigDecimal dunsTrxHdrSq, String fileName, BigDecimal dunsTrxFileSq) {
        ZYPEZDItemValueSetter.setValue(workFileTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(workFileTMsg.dunsTrxFilePk, dunsTrxFileSq);
        ZYPEZDItemValueSetter.setValue(workFileTMsg.dunsTrxHdrPk, dunsTrxHdrSq);
        ZYPEZDItemValueSetter.setValue(workFileTMsg.dunsFileTpCd, DUNS_FILE_TP.IMPORT_DUNS_DATA_FROM_DNB);
        ZYPEZDItemValueSetter.setValue(workFileTMsg.dunsFileNm, fileName);
    }

    private void setDunsInfoTblTMsg(DUNS_RCV_INFOTMsg workInfoTMsg, BigDecimal dunsTrxHdrSq, ResultSet rsSet) throws SQLException {
        BigDecimal dunsTrxInfoSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUNS_RCV_INFO_SQ);

        ZYPEZDItemValueSetter.setValue(workInfoTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsRcvInfoPk, dunsTrxInfoSq);
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsTrxHdrPk, dunsTrxHdrSq);
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsAcctNum, rsSet.getString("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.cnctDunsAddr, rsSet.getString("CNCT_DUNS_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstLineAddr, rsSet.getString("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsScdLineAddr, rsSet.getString("DUNS_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdLineAddr, rsSet.getString("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.frthLineAddr, rsSet.getString("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCtyAddr, rsSet.getString("DUNS_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.cntyDescTxt, rsSet.getString("CNTY_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsStNm, rsSet.getString("DUNS_ST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsPostCd, rsSet.getString("DUNS_POST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsTelNum, rsSet.getString("DUNS_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.locNum, rsSet.getString("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCustId, rsSet.getString("DUNS_CUST_ID"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.orclDunsNum, rsSet.getString("ORCL_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsDsCustSicCd, rsSet.getString("DUNS_DS_CUST_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ultDunsNum, rsSet.getString("ULT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsOrgId, rsSet.getString("DUNS_ORG_ID"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsOrgSq, rsSet.getBigDecimal("DUNS_ORG_SQ"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ncoaMoveEffDt, rsSet.getString("NCOA_MOVE_EFF_DT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ncoaLacsConvDt, rsSet.getString("NCOA_LACS_CONV_DT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.nixieACd, rsSet.getString("NIXIE_A_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.nixieBCd, rsSet.getString("NIXIE_B_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.nixieCCd, rsSet.getString("NIXIE_C_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsPrimNum, rsSet.getString("DUNS_PRIM_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.preDrctnCd, rsSet.getString("PRE_DRCTN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bestStrNm, rsSet.getString("BEST_STR_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.strSfxCd, rsSet.getString("STR_SFX_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.postDrctnCd, rsSet.getString("POST_DRCTN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.unitDesgPsnCd, rsSet.getString("UNIT_DESG_PSN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsScdUsBasicNum, rsSet.getString("DUNS_SCD_US_BASIC_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bestCtyNm, rsSet.getString("BEST_CTY_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bestStNm, rsSet.getString("BEST_ST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bestZipCd, rsSet.getString("BEST_ZIP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.postLastFourCd, rsSet.getString("POST_LAST_FOUR_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.delyPntBrcdCd, rsSet.getString("DELY_PNT_BRCD_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.carrRteCd, rsSet.getString("CARR_RTE_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.lineTrvlCd, rsSet.getString("LINE_TRVL_CD"));
        // Mod Start 2016/06/29 QC#10162
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.aaMatchCd, rsSet.getString("AA_MATCH_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfMatchCd, rsSet.getString("DSF_MATCH_CD"));
        // Mod End   2016/06/29 QC#10162
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfSortInstnCd, rsSet.getString("DSF_SORT_INSTN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.delyAbtyInd, rsSet.getString("DELY_ABTY_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfDelyTpCd, rsSet.getString("DSF_DELY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfVacInd, rsSet.getString("DSF_VAC_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfSesnInd, rsSet.getString("DSF_SESN_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfDropInd, rsSet.getString("DSF_DROP_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsPrimNcoaAddr, rsSet.getString("DUNS_PRIM_NCOA_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsScdNcoaAddr, rsSet.getString("DUNS_SCD_NCOA_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.preNumChngInd, rsSet.getString("PRE_NUM_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.preDirChngInd, rsSet.getString("PRE_DIR_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.preNmChngInd, rsSet.getString("PRE_NM_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.sfxChngInd, rsSet.getString("SFX_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.postDirChngInd, rsSet.getString("POST_DIR_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdUnitDescChngInd, rsSet.getString("SCD_UNIT_DESC_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdNumChngInd, rsSet.getString("SCD_NUM_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ctyNmChngInd, rsSet.getString("CTY_NM_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.stAbbrChngInd, rsSet.getString("ST_ABBR_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.postCdChngInd, rsSet.getString("POST_CD_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.postLastFourChngInd, rsSet.getString("POST_LAST_FOUR_CHNG_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsMatchCd, rsSet.getString("DUNS_MATCH_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsActvCd, rsSet.getString("DUNS_ACTV_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsNum, rsSet.getString("DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.matchGrdCd, rsSet.getString("MATCH_GRD_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCnfdCd, rsSet.getString("DUNS_CNFD_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsAccyPct, rsSet.getBigDecimal("DUNS_ACCY_PCT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.nmPrflCd, rsSet.getString("NM_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.strNoPrflCd, rsSet.getString("STR_NO_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.strNmPrflCd, rsSet.getString("STR_NM_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ctyPrflCd, rsSet.getString("CTY_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.stProvPrflCd, rsSet.getString("ST_PROV_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.pobPrflCd, rsSet.getString("POB_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.phoPrflCd, rsSet.getString("PHO_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.zipPrflCd, rsSet.getString("ZIP_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.sicPrflCd, rsSet.getString("SIC_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dnsyPrflCd, rsSet.getString("DNSY_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.nonUniqPrflCd, rsSet.getString("NON_UNIQ_PRFL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.custAddrDsfCd, rsSet.getString("CUST_ADDR_DSF_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.physAddrTpCd, rsSet.getString("PHYS_ADDR_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.physAddrDsfCd, rsSet.getString("PHYS_ADDR_DSF_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsMlAddrTpCd, rsSet.getString("DUNS_ML_ADDR_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlAddrDsfCd, rsSet.getString("ML_ADDR_DSF_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.physAddrVacCd, rsSet.getString("PHYS_ADDR_VAC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlAddrVacCd, rsSet.getString("ML_ADDR_VAC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.physAddrDelyTpCd, rsSet.getString("PHYS_ADDR_DELY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlAddrDelyTpCd, rsSet.getString("ML_ADDR_DELY_TP_CD"));
        // Mod Start 2016/06/29 QC#10162
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfPhysAddrTpMatchCd, rsSet.getString("DSF_PHYS_ADDR_TP_MATCH_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dsfMlAddrTpMatchCd, rsSet.getString("DSF_ML_ADDR_TP_MATCH_CD"));
        // Mod End   2016/06/29 QC#10162
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.cmraCdPhysAddr, rsSet.getString("CMRA_CD_PHYS_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.cmraCdMlAddr, rsSet.getString("CMRA_CD_ML_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.crctOppInd, rsSet.getString("CRCT_OPP_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsNum_02, rsSet.getString("DUNS_NUM_02"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsBizNm, rsSet.getString("DUNS_BIZ_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsTradeStyleNm, rsSet.getString("DUNS_TRADE_STYLE_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsLineAddr, rsSet.getString("DUNS_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCtyNm, rsSet.getString("DUNS_CTY_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsStCd_01, rsSet.getString("DUNS_ST_CD_01"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.postCd, rsSet.getString("POST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.addlPostCd, rsSet.getString("ADDL_POST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlLineAddr, rsSet.getString("ML_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlCtyNm, rsSet.getString("ML_CTY_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlStCd, rsSet.getString("ML_ST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlPostCd, rsSet.getString("ML_POST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mlAddlPostCd, rsSet.getString("ML_ADDL_POST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.carrRteCd_01, rsSet.getString("CARR_RTE_CD_01"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.delyPntCd, rsSet.getString("DELY_PNT_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ctryCd, rsSet.getString("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mktgRecStCd, rsSet.getString("MKTG_REC_ST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCntyCd, rsSet.getString("DUNS_CNTY_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCtyCd, rsSet.getString("DUNS_CTY_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsSmsaCd, rsSet.getString("DUNS_SMSA_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dnbRefCmntTxt, rsSet.getString("DNB_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.fipsStCd, rsSet.getString("FIPS_ST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.fipsCntyCd, rsSet.getString("FIPS_CNTY_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.fipsMsaCd, rsSet.getString("FIPS_MSA_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsRefCmntTxt, rsSet.getString("DUNS_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mktgRecTelNum, rsSet.getString("MKTG_REC_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoFirstNm, rsSet.getString("CEO_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoMidNm, rsSet.getString("CEO_MID_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoLastNm, rsSet.getString("CEO_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoSfxNm, rsSet.getString("CEO_SFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoPfxNm, rsSet.getString("CEO_PFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoTtlNm, rsSet.getString("CEO_TTL_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoMrcdCd, rsSet.getString("CEO_MRCD_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.annSlsAmt, rsSet.getBigDecimal("ANN_SLS_AMT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.slsVolRelnCd, rsSet.getString("SLS_VOL_RELN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.empTotNum, rsSet.getBigDecimal("EMP_TOT_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.empTotRelnCd, rsSet.getString("EMP_TOT_RELN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.empHereNum, rsSet.getString("EMP_HERE_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.empHereRelnCd, rsSet.getString("EMP_HERE_RELN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.yrStartNum, rsSet.getString("YR_START_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsStsInd, rsSet.getString("DUNS_STS_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsSbsdyInd, rsSet.getString("DUNS_SBSDY_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsMnfInd, rsSet.getString("DUNS_MNF_IND"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.glblUltDunsNum, rsSet.getString("GLBL_ULT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.hqDunsNum, rsSet.getString("HQ_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.prntDunsNum, rsSet.getString("PRNT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.hqPrntCtyNm, rsSet.getString("HQ_PRNT_CTY_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.hqPrntStCd, rsSet.getString("HQ_PRNT_ST_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.fgnUltDunsNum, rsSet.getString("FGN_ULT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.fgnPrntDunsNum, rsSet.getString("FGN_PRNT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsHrchCd, rsSet.getString("DUNS_HRCH_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsDiasCd, rsSet.getString("DUNS_DIAS_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsPpltCd, rsSet.getString("DUNS_PPLT_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dnbFirstRefCmntTxt, rsSet.getString("DNB_FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dnbRptDt, rsSet.getString("DNB_RPT_DT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ceoGenCd, rsSet.getString("CEO_GEN_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.recClsTpCd, rsSet.getString("REC_CLS_TP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.lineBizNm, rsSet.getString("LINE_BIZ_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstSicCd, rsSet.getString("FIRST_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdSicCd, rsSet.getString("SCD_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdSicCd, rsSet.getString("THIRD_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstSicDescTxt, rsSet.getString("FIRST_SIC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdSicDescTxt, rsSet.getString("SCD_SIC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdSicDescTxt, rsSet.getString("THIRD_SIC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.stNm, rsSet.getString("ST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.mktgRecCntyNm, rsSet.getString("MKTG_REC_CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsSmsaNm, rsSet.getString("DUNS_SMSA_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.glblUltBizNm, rsSet.getString("GLBL_ULT_BIZ_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.hqDunsNm, rsSet.getString("HQ_DUNS_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdYrSlsGrthNum, rsSet.getString("THIRD_YR_SLS_GRTH_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdYrSlsPctGrthNum, rsSet.getString("THIRD_YR_SLS_PCT_GRTH_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdYrEmpGrthNum, rsSet.getString("THIRD_YR_EMP_GRTH_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdYrEmpPctGrthNum, rsSet.getString("THIRD_YR_EMP_PCT_GRTH_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.trendYrSlsNum, rsSet.getString("TREND_YR_SLS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.trendYrEmpNum, rsSet.getString("TREND_YR_EMP_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdYrSlsNum, rsSet.getString("THIRD_YR_SLS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdYrEmpNum, rsSet.getString("THIRD_YR_EMP_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bankDunsNum, rsSet.getString("BANK_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsBankNm, rsSet.getString("DUNS_BANK_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsBankAddr, rsSet.getString("DUNS_BANK_ADDR"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bankCtyNm, rsSet.getString("BANK_CTY_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bankStAbbrNm, rsSet.getString("BANK_ST_ABBR_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.bankZipCd, rsSet.getString("BANK_ZIP_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.acctFirmNm, rsSet.getString("ACCT_FIRM_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.legalStsCd, rsSet.getString("LEGAL_STS_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.imptExptCd, rsSet.getString("IMPT_EXPT_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.ownrRntlCd, rsSet.getString("OWNR_RNTL_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.trtyCoverCd, rsSet.getString("TRTY_COVER_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsAcctCnt, rsSet.getBigDecimal("DUNS_ACCT_CNT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsSqftTxt, rsSet.getString("DUNS_SQFT_TXT"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecFirstNm, rsSet.getString("FIRST_EXEC_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecMidNm, rsSet.getString("FIRST_EXEC_MID_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecLastNm, rsSet.getString("FIRST_EXEC_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecSfxNm, rsSet.getString("FIRST_EXEC_SFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecPfxNm, rsSet.getString("FIRST_EXEC_PFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecTtlNm, rsSet.getString("FIRST_EXEC_TTL_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.firstExecMrcCd, rsSet.getString("FIRST_EXEC_MRC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecFirstNm, rsSet.getString("SCD_EXEC_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecMidNm, rsSet.getString("SCD_EXEC_MID_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecLastNm, rsSet.getString("SCD_EXEC_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecSfxNm, rsSet.getString("SCD_EXEC_SFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecPfxNm, rsSet.getString("SCD_EXEC_PFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecTtlNm, rsSet.getString("SCD_EXEC_TTL_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.scdExecMrcCd, rsSet.getString("SCD_EXEC_MRC_CD"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecFirstNm, rsSet.getString("THIRD_EXEC_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecMidNm, rsSet.getString("THIRD_EXEC_MID_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecLastNm, rsSet.getString("THIRD_EXEC_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecSfxNm, rsSet.getString("THIRD_EXEC_SFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecPfxNm, rsSet.getString("THIRD_EXEC_PFX_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecTtlNm, rsSet.getString("THIRD_EXEC_TTL_NM"));
        ZYPEZDItemValueSetter.setValue(workInfoTMsg.thirdExecMrcCd, rsSet.getString("THIRD_EXEC_MRC_CD"));
        // Del Start 2016/06/29 QC#10162
        //ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCmpySicCd, rsSet.getString("DUNS_CMPY_SIC_CD"));
        //ZYPEZDItemValueSetter.setValue(workInfoTMsg.dunsCmpySicDescTxt, rsSet.getString("DUNS_CMPY_SIC_DESC_TXT"));
        // Del End   2016/06/29 QC#10162
    }

    private void createErrorMessage(String trxId, String msgID, String[] param) {
        if (trxId == null) {
            trxId = "";
        }

        if (this.errMsg == null) {
            this.errMsg = new StringBuilder();
            this.errMsg.append(MAIL_FIELD_MSG_TRX_ID);
            this.errMsg.append(" ");
            this.errMsg.append(MAIL_FIELD_MSG_ERR_MSG);
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
        groupTo.setMailKey1(MAIL_KEY_TO_ERR);
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, HYPHEN });
        }

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_ERR);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ID_ERR});
        }

        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        // Set Message
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_ID, BATCH_PROGRAM_ID);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_NAME, BATCH_PROGRAM_NAME);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_PROC_LOG_ID, batchProcessLogId);
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_INFO, errMsg.toString());

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    private void sendMail(List<String> fileNameList, List<String> sysDateList, List<BigDecimal> lineNumList) {
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
        groupTo.setMailKey1(MAIL_KEY_TO_NORMAL);
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, HYPHEN });
        }

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_NORMAL);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ID_NORMAL});
        }

        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        // Set Message
        maiTemplate.setTemplateParameter(MAIL_FIELD_INFO, createMessage(fileNameList, sysDateList, lineNumList));

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    private String createMessage(List<String> fileNameList, List<String> sysDateList, List<BigDecimal> lineNumList) {

        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < fileNameList.size(); i++) {
            msg.append(" Imported File:");
            msg.append("                      ");
            msg.append(fileNameList.get(i));
            msg.append(CRLF);
            msg.append(" Imported Date:");
            msg.append("                    ");
            msg.append(sysDateList.get(i));
            msg.append(CRLF);
            msg.append(" Imported Record Count:");
            msg.append("  ");
            msg.append(lineNumList.get(i));
            msg.append(CRLF);
        }

        return msg.toString();
    }

}
