/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB721001;

import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.BATCH_PROC_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.BATCH_PROGRAM_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.BATCH_PROGRAM_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.BEFORE_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.COMMA;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CRLF;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CR_LIST_GNRN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CR_LIST_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSA_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSA_PRC_CATG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_CR_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_EML_SEND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_ERR_DTL_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_ERR_MSG_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_PRC_INTFC;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_PRC_INTFC_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_TRX_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CSMP_TRX_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.CUSA_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.DIGIT_FOR_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.D_CSMP_TRX_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_FOR_CREDIT_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_FOR_CSA_ITEM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_FOR_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_FOR_ITEM_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_FOR_IUD_FLAG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_FOR_UPD_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_INFO_HEADER_MARK;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_HEADER_RULE_FOR_CREDIT_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_HEADER_RULE_FOR_CSA_ITEM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_HEADER_RULE_FOR_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_HEADER_RULE_FOR_ITEM_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_HEADER_RULE_FOR_IUD_FLAG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_HEADER_RULE_FOR_UPD_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ERR_MSG_UPDATE_BLANK;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.I_AND_U;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.I_CSMP_TRX_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.KEY_CREATE_LIST_AND_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.KEY_CREATE_LIST_BY_STS;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.LAST_UPD_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_DIGIT_FOR_CREDIT_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_DIGIT_FOR_CSA_ITEM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_DIGIT_FOR_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_DIGIT_FOR_ITEM_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_DIGIT_FOR_IUD_FLAG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_DIGIT_FOR_UPD_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_BATCH_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_COMPLETED_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_ERROR_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_PROCESSED_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_TIMESTAMP;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_TOTAL_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_FIELD_WARNING_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_TYPE_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MIN_REC_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.MSG_SPACE;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8028E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8031E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8132E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8519E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8520E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8521E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8522E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8523E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8524E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8525E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8526E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8527E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8528E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8529E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8530E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8531E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NMAM8540E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.NUM_CONST;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.PRC_LIST_EQUIP;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.PRC_LIST_EQUIP_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.PRC_LIST_EQUIP_PRC_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.SET_TIMESTAMP_FORMAT;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.SUBSTR_FROM_POS;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.U_CSMP_TRX_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.YYYYMMDD_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB721001.constant.NMAB721001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CSMP_PRC_INTFCTMsg;
//import business.db.CSMP_PRC_XREFTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_LIST_EQUIPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * CSMP Price List sync process.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/18   Fujitsu         W.Honda         Create          N/A
 * 2016/06/09   Fujitsu         W.Honda         Update          QC#9711
 * 2016/06/14   Fujitsu         W.Honda         Update          QC#9945
 * 2016/06/20   SRAA            K.Aratani       Update          QC#10447
 *</pre>
 */

public class NMAB721001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** Batch Process Date */
    private String batProcDate = null;

    /** Batch Process Date */
    private String batProcType = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** processed count */
    private int processedCount = 0;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** SQL access parts */
    private boolean mailSendFlg = false;

    /** error message limit */
    private int errMsgLimit = 0;

    /** error record pK List */
    private List<BigDecimal> errRecPkList = new ArrayList<BigDecimal>();

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB721001().executeBatch(NMAB721001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD});
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {GLBL_CMPY_CD});
        }

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.batProcDate = ZYPDateUtil.getBatProcDate();
        if (!ZYPCommonFunc.hasValue(this.batProcDate)) {
            throw new S21AbendException(ZZZM9025E, new String[] {BATCH_PROC_DT});
        }

        this.batProcType = getUserVariable1();
        if (batProcType == null || "".equals(batProcType)) {
            throw new S21AbendException(ZZZM9026E, new String[] {});
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Mail Send Check.
        String csmpEmlSendCds = ZYPCodeDataUtil.getVarCharConstValue(CSMP_EML_SEND_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(csmpEmlSendCds)) {
            String[] csmpEmlSendCdList = csmpEmlSendCds.split(",", 0);
            for (String csmpEmlSendCd : csmpEmlSendCdList) {
                if (this.batProcType.equals(csmpEmlSendCd)) {
                    this.mailSendFlg = true;
                }
            }
        }

        // Error Message Limit.
        if (this.mailSendFlg) {
            BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue(CSMP_ERR_DTL_CNT, this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(numConstVal)) {
                this.errMsgLimit = numConstVal.intValue();
            } else {
                throw new S21AbendException(NMAM8132E, new String[] {CSMP_ERR_DTL_CNT, NUM_CONST });
            }
        }
    }

    @Override
    protected void mainRoutine() {
        // Get Process Target Record Count.
        String[] totalTargetStsCdList = new String[] {CSMP_PROC_STS.NEW, CSMP_PROC_STS.ERROR};
        this.totalCount = getProcessTargetRecordCount(totalTargetStsCdList);

        if (this.totalCount > 0) {
            checkValidationForNew();

            checkDuplication();

            checkValidation();

            createPricelist();
        }

        registerMail();
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);
    }

    private int getProcessTargetRecordCount(String[] csmpProcStsCdList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("csmpProcStsCdList", csmpProcStsCdList);

        Integer recCnt = (Integer) this.ssmBatchClient.queryObject("getRecordCountByStsCd", ssmParam);

        if (recCnt > 0) {
            return recCnt.intValue();
        }
        return 0;

    }

    private void checkValidationForNew() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        int recCnt = 0;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        String[] totalTargetStsCdList = new String[] {CSMP_PROC_STS.NEW};
        ssmParam.put("csmpProcStsCdList", totalTargetStsCdList);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getTargetRecordByStsCd", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                recCnt++;

                // CSMP_TRX_STS_CD
                if (checkCsmpTrxStsCd(rsSet)) {
                    continue;
                }

                // LAST_UPD_DT
                if (!ZYPCommonFunc.hasValue(rsSet.getString(LAST_UPD_DT))
                        || ZYPDateUtil.compare(rsSet.getString(LAST_UPD_DT), batProcDate) > 0) {
                    updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8520E, new String[] {});
                    continue;
                }

                // CUSA_MDSE_CD
                String workMdseCd = searchOrdTakeMdse(rsSet);

                if (checkMdseCdExist(rsSet, workMdseCd)) {
                    continue;
                }

                // CSMP Price List Cross Reference
                String csaPrcCatgCd = searchCsmpPrcXref(rsSet);
                if (!ZYPCommonFunc.hasValue(csaPrcCatgCd)) {
                    continue;
                }

                updateCsmpPrcIntfc(rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), workMdseCd, csaPrcCatgCd, "");

                if (recCnt >= this.commitUnit) {
                    commit();
                    recCnt = 0;
                }

            }

            if (recCnt > 0) {
                commit();
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void checkValidation() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        int recCnt = 0;
        int processedCnt = 0;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        String[] totalTargetStsCdList = new String[] {CSMP_PROC_STS.NEW, CSMP_PROC_STS.ERROR};
        ssmParam.put("csmpProcStsCdList", totalTargetStsCdList);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getTargetRecordByStsCd", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                recCnt++;
                String workMdseCd = null;
                if (ZYPCommonFunc.hasValue(rsSet.getString(CSA_MDSE_CD))) {
                    workMdseCd = rsSet.getString(CSA_MDSE_CD);
                } else {
                    workMdseCd = rsSet.getString(CUSA_MDSE_CD);
                }
                String csaPrcCatgCd = rsSet.getString(CSA_PRC_CATG_CD);

                if (CSMP_PROC_STS.ERROR.equals(rsSet.getString(CSMP_PROC_STS_CD))) {
                    // LAST_UPD_DT
                    if (!ZYPCommonFunc.hasValue(rsSet.getString(LAST_UPD_DT))
                            || ZYPDateUtil.compare(rsSet.getString(LAST_UPD_DT), batProcDate) > 0) {
                        updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8520E, new String[] {});
                        continue;
                    }

                    // CUSA_MDSE_CD
                    workMdseCd = searchOrdTakeMdse(rsSet);

                    if (checkMdseCdExist(rsSet, workMdseCd)) {
                        continue;
                    }

                    // CSMP Price List Cross Reference
                    csaPrcCatgCd = searchCsmpPrcXref(rsSet);
                    if (!ZYPCommonFunc.hasValue(csaPrcCatgCd)) {
                        continue;
                    }
                }

                // Price List
                if (searchPriceList(rsSet, csaPrcCatgCd)) {
                    continue;
                }

                if (searchPriceListDetail(rsSet, csaPrcCatgCd, workMdseCd)) {
                    continue;
                }

                updateCsmpPrcIntfc(rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), workMdseCd, csaPrcCatgCd, CSMP_PROC_STS.PROCESSED);
                processedCnt++;

                if (recCnt >= this.commitUnit) {
                    commit();
                    recCnt = 0;
                    this.processedCount = this.processedCount + processedCnt;
                    processedCnt = 0;
                }

            }

            if (recCnt > 0) {
                commit();
                this.processedCount = this.processedCount + processedCnt;
                processedCnt = 0;
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void updateCsmpPrcIntfcByErr(String csmpProcStsCd, BigDecimal csmpPrcIntfcPk, String errMsgCd, String[] msgParm) {
        CSMP_PRC_INTFCTMsg inTMsg = new CSMP_PRC_INTFCTMsg();
        CSMP_PRC_INTFCTMsg outTMsg = null;

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpPrcIntfcPk, csmpPrcIntfcPk);
        outTMsg = (CSMP_PRC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

        ZYPEZDItemValueSetter.setValue(outTMsg.csmpProcStsCd, csmpProcStsCd);
        ZYPEZDItemValueSetter.setValue(outTMsg.csmpErrMsgTxt, S21MessageFunc.clspGetMessage(errMsgCd, msgParm));

        EZDTBLAccessor.update(outTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NMAM0177E, new String[] {inTMsg.getTableName()});
        }

        if (this.errMsgLimit >= this.errRecPkList.size()
                && !this.errRecPkList.contains(csmpPrcIntfcPk)) {
            this.errRecPkList.add(csmpPrcIntfcPk);
        }

        return;
    }

    private void updateCsmpPrcIntfc(BigDecimal csmpPrcIntfcPk, String newMdseCd, String csaPrcCatgCd, String csmpProcStsCd) {
        CSMP_PRC_INTFCTMsg inTMsg = new CSMP_PRC_INTFCTMsg();

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.csmpPrcIntfcPk, csmpPrcIntfcPk);
        inTMsg = (CSMP_PRC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

        if (ZYPCommonFunc.hasValue(newMdseCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.csaMdseCd, newMdseCd);
        }
        if (ZYPCommonFunc.hasValue(csaPrcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.csaPrcCatgCd, csaPrcCatgCd);
        }
        if (ZYPCommonFunc.hasValue(csmpProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.csmpProcStsCd, csmpProcStsCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.csmpErrMsgTxt, ERR_MSG_UPDATE_BLANK);
        }

        EZDTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NMAM0177E, new String[] {CSMP_PRC_INTFC});
        }

        return;
    }

    private boolean checkCsmpTrxStsCd(ResultSet rsSet) throws SQLException {
        boolean checkResultForTrxSts = true;

        for (String csmpTrxStsCd : CSMP_TRX_STS_CD_LIST) {
            if (csmpTrxStsCd.equals(rsSet.getString(CSMP_TRX_STS_CD))) {
                checkResultForTrxSts = false;
                break;
            }
        }

        if (checkResultForTrxSts) {
            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.WARNING, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8519E, new String[] {rsSet.getString(CSMP_TRX_STS_CD)});
            return true;
        }

        return false;
    }

    private String searchOrdTakeMdse(ResultSet rsSet) throws SQLException {
        String workMdseCd = rsSet.getString(CUSA_MDSE_CD);
        if (ZYPCommonFunc.hasValue(workMdseCd)
                && workMdseCd.length() > DIGIT_FOR_MDSE_CD) {
            ORD_TAKE_MDSETMsg inOrdTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
            ORD_TAKE_MDSETMsg outOrdTakeMdseTMsg = null;

            ZYPEZDItemValueSetter.setValue(inOrdTakeMdseTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inOrdTakeMdseTMsg.ordTakeMdseCd, workMdseCd.substring(SUBSTR_FROM_POS, DIGIT_FOR_MDSE_CD));
            outOrdTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(inOrdTakeMdseTMsg);

            if (outOrdTakeMdseTMsg != null) {
                workMdseCd = outOrdTakeMdseTMsg.ordTakeMdseCd.getValue();
            }
        }

        return workMdseCd;
    }

    private boolean checkMdseCdExist(ResultSet rsSet, String workMdseCd) throws SQLException {
        ALL_MDSE_VTMsg inAllMdseVTMsg = new ALL_MDSE_VTMsg();
        ALL_MDSE_VTMsgArray outAllMdseVTMsg = null;

        ZYPEZDItemValueSetter.setValue(inAllMdseVTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inAllMdseVTMsg.mdseCd, workMdseCd);

        inAllMdseVTMsg.setSQLID("003");
        inAllMdseVTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inAllMdseVTMsg.setConditionValue("mdseCd01", workMdseCd);

        outAllMdseVTMsg = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(inAllMdseVTMsg);
        if (outAllMdseVTMsg == null || outAllMdseVTMsg.length() == 0) {
            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8521E, new String[] {workMdseCd});
            return true;
        }

        for (int i = 0; i < outAllMdseVTMsg.length(); i++) {
            ALL_MDSE_VTMsg allMdseVTMsg = outAllMdseVTMsg.no(i);
            if (MDSE_ITEM_STS.INACTIVE.equals(allMdseVTMsg.mdseItemStsCd.getValue())) {
                updateCsmpPrcIntfcByErr(CSMP_PROC_STS.WARNING, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8522E, new String[] {workMdseCd});
                return true;
            }
        }

        return false;
    }

    private String searchCsmpPrcXref(ResultSet rsSet) throws SQLException {
    	//QC#10447
//        CSMP_PRC_XREFTMsg inCsmpPrcXrefTMsg = new CSMP_PRC_XREFTMsg();
//        CSMP_PRC_XREFTMsg outCsmpPrcXrefTMsg = null;
        String  csaPrcCatgCd = null;

        
//        ZYPEZDItemValueSetter.setValue(inCsmpPrcXrefTMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(inCsmpPrcXrefTMsg.crListGnrnNum, rsSet.getString(CR_LIST_GNRN_NUM));
//        ZYPEZDItemValueSetter.setValue(inCsmpPrcXrefTMsg.crListTxt, rsSet.getString(CR_LIST_TXT));
//
//        outCsmpPrcXrefTMsg = (CSMP_PRC_XREFTMsg) S21CacheTBLAccessor.findByKey(inCsmpPrcXrefTMsg);
//        if (outCsmpPrcXrefTMsg == null) {
//            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8523E, new String[] {rsSet.getString(CR_LIST_TXT) + "." + rsSet.getString(CR_LIST_GNRN_NUM)});
//            return csaPrcCatgCd;
//        }
        String crListTxt = rsSet.getString(CR_LIST_TXT);
        String crListGnrnNum = rsSet.getString(CR_LIST_GNRN_NUM);
        if (ZYPCommonFunc.hasValue(crListTxt) && ZYPCommonFunc.hasValue(crListGnrnNum)) {
	        Map<String, Object> ssmParam2 = new HashMap<String, Object>();
	        ssmParam2.put("glblCmpyCd", this.glblCmpyCd);
	        ssmParam2.put("crListGnrnNum", crListGnrnNum);
	        ssmParam2.put("crListTxt", crListTxt);
	
	        csaPrcCatgCd = (String) this.ssmBatchClient.queryObject("getCsmpPrcXrefWithoutEffDt", ssmParam2);
	
	        if (!ZYPCommonFunc.hasValue(csaPrcCatgCd)) {
	            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8523E, new String[] {rsSet.getString(CR_LIST_TXT) + "." + rsSet.getString(CR_LIST_GNRN_NUM)});
	            return csaPrcCatgCd;
	        }
	
	        Map<String, Object> ssmParam = new HashMap<String, Object>();
	        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//	        ssmParam.put("crListGnrnNum", outCsmpPrcXrefTMsg.crListGnrnNum.getValue());
//          ssmParam.put("crListTxt", outCsmpPrcXrefTMsg.crListTxt.getValue());
	        ssmParam.put("crListGnrnNum", crListGnrnNum);
	        ssmParam.put("crListTxt", crListTxt);
	        ssmParam.put("batProcDate", this.batProcDate);
	
	        csaPrcCatgCd = (String) this.ssmBatchClient.queryObject("getCsmpPrcXref", ssmParam);
	
	        if (!ZYPCommonFunc.hasValue(csaPrcCatgCd)) {
	            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8524E, new String[] {rsSet.getString(CR_LIST_TXT) + "." + rsSet.getString(CR_LIST_GNRN_NUM)});
	        }
        } else {
            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8523E, new String[] {rsSet.getString(CR_LIST_TXT) + "." + rsSet.getString(CR_LIST_GNRN_NUM)});
            return null;
        }

        return csaPrcCatgCd;
    }

    private boolean searchPriceList(ResultSet rsSet, String csaPrcCatgCd) throws SQLException {
        Map<String, Object> ssmPrcCatgParam = new HashMap<String, Object>();
        ssmPrcCatgParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrcCatgParam.put("csaPrcCatgCd", csaPrcCatgCd);
        ssmPrcCatgParam.put("lastUpdDt", rsSet.getString(LAST_UPD_DT));
        ssmPrcCatgParam.put("delFlgN", ZYPConstant.FLG_OFF_N);
        ssmPrcCatgParam.put("prcListStruTpEquip", PRC_LIST_STRU_TP.EQUIPMENT);

        String prcCatgCd = (String) this.ssmBatchClient.queryObject("getPriceList", ssmPrcCatgParam);

        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8526E, new String[] {rsSet.getString(CR_LIST_TXT) + "." + rsSet.getString(CR_LIST_GNRN_NUM)});
            return true;
        }

        ssmPrcCatgParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);

        prcCatgCd = (String) this.ssmBatchClient.queryObject("getPriceList", ssmPrcCatgParam);

        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8527E, new String[] {rsSet.getString(CR_LIST_TXT) + "." + rsSet.getString(CR_LIST_GNRN_NUM)});
            return true;
        }

        return false;
    }

    private boolean searchPriceListDetail(ResultSet rsSet, String prcCatgCd, String workMdseCd) throws SQLException {
        // Price List Detail
        Map<String, Object> ssmPrcCatgDtlParam = new HashMap<String, Object>();
        ssmPrcCatgDtlParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrcCatgDtlParam.put("prcCatgCd", prcCatgCd);
        ssmPrcCatgDtlParam.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
        ssmPrcCatgDtlParam.put("workMdseCd", workMdseCd);
        ssmPrcCatgDtlParam.put("pkgUomEach", PKG_UOM.EACH);
        ssmPrcCatgDtlParam.put("lastUpdDt", rsSet.getString(LAST_UPD_DT));
        ssmPrcCatgDtlParam.put("delFlgN", ZYPConstant.FLG_OFF_N);

        List<Map< ? , ? >> prcCatgDtlList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getPriceListDtl", ssmPrcCatgDtlParam);

        if (prcCatgDtlList.isEmpty()) {
            ssmPrcCatgDtlParam.put("lastUpdDt", null);

            prcCatgDtlList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getPriceListDtl", ssmPrcCatgDtlParam);

            if (!prcCatgDtlList.isEmpty()) {
                for (Map< ? , ? > prcCatgDtl : prcCatgDtlList) {
                    if (ZYPDateUtil.compare(rsSet.getString(LAST_UPD_DT), (String) prcCatgDtl.get("EFF_FROM_DT")) <= 0) {
                        updateCsmpPrcIntfcByErr(CSMP_PROC_STS.ERROR, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8530E, new String[] {prcCatgCd, workMdseCd, rsSet.getString(CSMP_CR_AMT)});
                        return true;
                    }
                }
            }
        }

        for (Map< ? , ? > prcCatgDtl : prcCatgDtlList) {
            if (D_CSMP_TRX_STS_CD.equals(rsSet.getString(CSMP_TRX_STS_CD))) {
                if (ZYPCommonFunc.isNumberCheck(rsSet.getString(CSMP_CR_AMT))
                        && new BigDecimal(rsSet.getString(CSMP_CR_AMT)).compareTo((BigDecimal) prcCatgDtl.get(PRC_LIST_EQUIP_PRC_AMT)) != 0) {
                    updateCsmpPrcIntfcByErr(CSMP_PROC_STS.WARNING, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8529E, new String[] {prcCatgCd, workMdseCd, rsSet.getString(CSMP_CR_AMT)});
                    return true;
                }
            }

            if (!D_CSMP_TRX_STS_CD.equals(rsSet.getString(CSMP_TRX_STS_CD))) {
                if (ZYPCommonFunc.isNumberCheck(rsSet.getString(CSMP_CR_AMT))
                        && new BigDecimal(rsSet.getString(CSMP_CR_AMT)).compareTo((BigDecimal) prcCatgDtl.get(PRC_LIST_EQUIP_PRC_AMT)) == 0) {
                    updateCsmpPrcIntfcByErr(CSMP_PROC_STS.WARNING, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8528E, new String[] {prcCatgCd, workMdseCd, rsSet.getString(CSMP_CR_AMT)});
                    return true;
                }
            }
        }

        return false;
    }

    private void checkDuplication() {
        // Duplication check Transaction status = I, U.
        checkDuplicationByTransactionStatus();

        // Duplication check duplication key = CsaMdseCd,CsaCatgCd,CsmpCrAmt,CrListTxt
        // and Transaction Status = I.
        checkDuplicationByKeyAndTrxSts(I_CSMP_TRX_STS_CD);

        // Duplication check duplication key = CsaMdseCd,CsaCatgCd,CsmpCrAmt,CrListTxt
        // and Transaction Status = U.
        checkDuplicationByKeyAndTrxSts(U_CSMP_TRX_STS_CD);

        // Duplication check duplication key = CsaMdseCd,CsaPrcCatgCd,CsmpCrAmt
        checkDuplicationByKey();
    }

    private void createPricelist() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        int recCnt = 0;
        int complateCnt = 0;
        String checkDupRec = new String();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("csmpProcStsProcessed", CSMP_PROC_STS.PROCESSED);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getCheckClearRecord", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {
                recCnt++;

                // Duplication check
                if (ZYPCommonFunc.hasValue(checkDupRec)
                        && checkDupRec.equals(createKey(rsSet))) {
                    updateCsmpPrcIntfcByErr(CSMP_PROC_STS.WARNING, rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), NMAM8531E, new String[] {rsSet.getString(CSA_PRC_CATG_CD), rsSet.getString(CSA_MDSE_CD)});
                    continue;
                }
                checkDupRec = createKey(rsSet);

                //old record Inactive
                Map<String, Object> ssmDtlParam = new HashMap<String, Object>();
                ssmDtlParam.put("glblCmpyCd", this.glblCmpyCd);
                ssmDtlParam.put("prcCatgCd", rsSet.getString(CSA_PRC_CATG_CD));
                ssmDtlParam.put("prcQlfyTpItemCode", PRC_QLFY_TP.ITEM_CODE);
                ssmDtlParam.put("workMdseCd", rsSet.getString(CSA_MDSE_CD));
                ssmDtlParam.put("pkgUomEach", PKG_UOM.EACH);
                ssmDtlParam.put("lastUpdDt", rsSet.getString(LAST_UPD_DT));
                ssmDtlParam.put("delFlgN", ZYPConstant.FLG_OFF_N);

                List<Map< ? , ? >> oldPrcCatgDtlList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getUpdatePriceListDtl", ssmDtlParam);

                for (Map< ? , ? > prcCatgDtl : oldPrcCatgDtlList) {
                    PRC_LIST_EQUIPTMsg prcListEquip = new PRC_LIST_EQUIPTMsg();

                    ZYPEZDItemValueSetter.setValue(prcListEquip.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(prcListEquip.prcListEquipPk, (BigDecimal) prcCatgDtl.get(PRC_LIST_EQUIP_PK));

                    prcListEquip = (PRC_LIST_EQUIPTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prcListEquip);

                    if (ZYPDateUtil.compare(rsSet.getString(LAST_UPD_DT), (String) prcCatgDtl.get(EFF_FROM_DT)) == 0) {
                        // QC#9711 2016/06/09 Mod start
//                        ZYPEZDItemValueSetter.setValue(prcListEquip.effThruDt, rsSet.getString(LAST_UPD_DT));
                        ZYPEZDItemValueSetter.setValue(prcListEquip.delFlg, ZYPConstant.FLG_ON_Y);
                        // QC#9711 2016/06/09 Mod end
                    } else {
                        ZYPEZDItemValueSetter.setValue(prcListEquip.effThruDt, ZYPDateUtil.addDays(rsSet.getString(LAST_UPD_DT), BEFORE_COUNT));
                    }

                    EZDTBLAccessor.update(prcListEquip);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcListEquip.getReturnCode())) {
                        S21InfoLogOutput.println(NMAM0177E, new String[] {PRC_LIST_EQUIP});
                    }
                }

                // new record create
                if (I_CSMP_TRX_STS_CD.equals(rsSet.getString(CSMP_TRX_STS_CD))
                        || U_CSMP_TRX_STS_CD.equals(rsSet.getString(CSMP_TRX_STS_CD))) {
                    ssmDtlParam.put("rowNum", MIN_REC_CNT);

                    List<Map< ? , ? >> prcCatgDtlList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getFuturePriceListDtl", ssmDtlParam);

                    String effThruDt = "";

                    if (prcCatgDtlList != null && prcCatgDtlList.size() > 0) {
                        // get Minimum EFF_FROM_DT
                        Map< ? , ? > futurePrcCatgDtl = prcCatgDtlList.get(0);
                        effThruDt = ZYPDateUtil.addDays((String) futurePrcCatgDtl.get("EFF_FROM_DT"), BEFORE_COUNT);
                    }

                    PRC_LIST_EQUIPTMsg newPrcListEquip = new PRC_LIST_EQUIPTMsg();

                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.prcListEquipPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ));
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.prcCatgCd, rsSet.getString(CSA_PRC_CATG_CD));
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.prcQlfyTpCd, PRC_QLFY_TP.ITEM_CODE);
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.prcQlfyValTxt, rsSet.getString(CSA_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.pkgUomCd, PKG_UOM.EACH);
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.prcListEquipPrcAmt, rsSet.getBigDecimal(CSMP_CR_AMT));
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.openMktFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.effFromDt, rsSet.getString(LAST_UPD_DT));
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.effThruDt, effThruDt);
                    ZYPEZDItemValueSetter.setValue(newPrcListEquip.delFlg, ZYPConstant.FLG_OFF_N);

                    EZDTBLAccessor.insert(newPrcListEquip);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newPrcListEquip.getReturnCode())) {
                        S21InfoLogOutput.println(NMAM0176E, new String[] {PRC_LIST_EQUIP});
                    }
                }

                // update
                updateCsmpPrcIntfc(rsSet.getBigDecimal(CSMP_PRC_INTFC_PK), new String(), new String(), CSMP_PROC_STS.COMPLETED);
                complateCnt++;

                if (recCnt >= this.commitUnit) {
                    commit();
                    recCnt = 0;
                    this.successCount = this.successCount + complateCnt;
                    complateCnt = 0;
                }

            }

            if (recCnt > 0) {
                commit();
                this.successCount = this.successCount + complateCnt;
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private String createKey(ResultSet rsSet) throws SQLException {
        StringBuilder sb = new StringBuilder();

        sb.append(rsSet.getString(CSA_MDSE_CD)).append(",").append(rsSet.getString(CSA_PRC_CATG_CD));

        return sb.toString();
    }

    private String createKey(Map< ? , ? > rsMap, String[] createKeyList) {
        StringBuilder sb = new StringBuilder();

        for (String createKey : createKeyList) {
            if (sb.length() != 0) {
                sb.append(COMMA);
            }

            if (CSMP_CR_AMT.equals(createKey)) {
                BigDecimal csmpCrAmt = (BigDecimal) rsMap.get(CSMP_CR_AMT);
                sb.append(csmpCrAmt.toString());
            } else {
                sb.append((String) rsMap.get(createKey));
            }
        }

        return sb.toString();
    }

    private void checkDuplicationByTransactionStatus() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procStsNew", CSMP_PROC_STS.NEW);
        ssmParam.put("trxStsInsert", I_CSMP_TRX_STS_CD);
        ssmParam.put("trxStsUpdate", U_CSMP_TRX_STS_CD);
        ssmParam.put("substrFromPos", SUBSTR_FROM_POS);
        ssmParam.put("digitForMdseCd", DIGIT_FOR_MDSE_CD);

        List<Map< ? , ? >> dupRecList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getInsAndUpdDup", ssmParam);

        for (Map< ? ,  ? > dupRec : dupRecList) {
            CSMP_PRC_INTFCTMsg csmpPrcIntfc = new CSMP_PRC_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(csmpPrcIntfc.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(csmpPrcIntfc.csmpPrcIntfcPk, (BigDecimal) dupRec.get(CSMP_PRC_INTFC_PK));
            csmpPrcIntfc = (CSMP_PRC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(csmpPrcIntfc);

            ZYPEZDItemValueSetter.setValue(csmpPrcIntfc.csmpErrMsgTxt, S21MessageFunc.clspGetMessage(NMAM8525E, new String[] {I_AND_U}));

            EZDTBLAccessor.update(csmpPrcIntfc);
            EZDTBLAccessor.logicalRemove(csmpPrcIntfc);
        }

        super.commit();
    }

    private void checkDuplicationByKeyAndTrxSts(String csmpTrxStsCd) {
        String preRecKey = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procStsNew", CSMP_PROC_STS.NEW);
        ssmParam.put("trxStsInsert", csmpTrxStsCd);
        ssmParam.put("minRecCnt", MIN_REC_CNT);

        List<Map< ? , ? >> dupRecList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getRecordDuplicationByKeyAndTrxSts", ssmParam);

        for (Map< ? , ? > dupRec : dupRecList) {
            String recKey = createKey(dupRec, KEY_CREATE_LIST_BY_STS);

            if (!ZYPCommonFunc.hasValue(preRecKey)
                    || dupRec.equals(recKey)) {
                preRecKey = recKey;
                continue;
            }

            CSMP_PRC_INTFCTMsg csmpPrcIntfc = new CSMP_PRC_INTFCTMsg();

            ZYPEZDItemValueSetter.setValue(csmpPrcIntfc.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(csmpPrcIntfc.csmpPrcIntfcPk, (BigDecimal) dupRec.get(CSMP_PRC_INTFC_PK));
            csmpPrcIntfc = (CSMP_PRC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(csmpPrcIntfc);

            ZYPEZDItemValueSetter.setValue(csmpPrcIntfc.csmpErrMsgTxt, S21MessageFunc.clspGetMessage(NMAM8525E, new String[] {csmpTrxStsCd}));

            EZDTBLAccessor.update(csmpPrcIntfc);
            EZDTBLAccessor.logicalRemove(csmpPrcIntfc);
        }

        super.commit();
    }

    private void checkDuplicationByKey() {
        String preRecKey = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procStsNew", CSMP_PROC_STS.NEW);
        ssmParam.put("minRecCnt", MIN_REC_CNT);

        List<Map< ? , ? >> dupRecList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getRecordDuplicationByKey", ssmParam);

        for (Map< ? , ? > dupRec : dupRecList) {
            String recKey = createKey(dupRec, KEY_CREATE_LIST_AND_AMT);

            if (!ZYPCommonFunc.hasValue(preRecKey)) {
                preRecKey = recKey;
                continue;
            }

            updateCsmpPrcIntfcByErr(CSMP_PROC_STS.WARNING, (BigDecimal) dupRec.get(CSMP_PRC_INTFC_PK), NMAM8540E, new String[] {});
        }

        super.commit();
    }

    private void registerMail() {

        // get Error Record Count
        String[] totalTargetStsCdList = new String[] {CSMP_PROC_STS.ERROR};
        int errorRecord = getProcessTargetRecordCount(totalTargetStsCdList);

        int warningRecord = this.totalCount - this.successCount - errorRecord;

        this.errorCount = this.totalCount - this.successCount;

        if (!this.mailSendFlg) {
            return;
        }

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

        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        String errMsg = getErrMsg();

        // Set Message
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_ID, BATCH_PROGRAM_ID);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_NAME, BATCH_PROGRAM_NAME);
        // QC#9945 2016/06/14 Mod start
//        maiTemplate.setTemplateParameter(MAIL_FIELD_TIMESTAMP, ZYPDateUtil.getCurrentSystemTime(DATEFORMAT_YYYYMMDDHHMMSS));
        maiTemplate.setTemplateParameter(MAIL_FIELD_TIMESTAMP, ZYPDateUtil.getCurrentSystemTime(SET_TIMESTAMP_FORMAT));
        // QC#9945 2016/06/14 Mod end
        maiTemplate.setTemplateParameter(MAIL_FIELD_TOTAL_CNT, this.totalCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_PROCESSED_CNT, this.processedCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_COMPLETED_CNT, this.successCount);
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERROR_CNT, errorRecord);
        maiTemplate.setTemplateParameter(MAIL_FIELD_WARNING_CNT, warningRecord);
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, errMsg);

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    private String getErrMsg() {
        StringBuilder sb = new StringBuilder();

        sb.append(ERR_INFO_HEADER_MARK);
        sb.append(MSG_SPACE);
        sb.append(ERR_INFO_HEADER);
        sb.append(MSG_SPACE);
        sb.append(ERR_INFO_HEADER_MARK);
        sb.append(CRLF);
        sb.append(CRLF);

        sb.append(ZYPCommonFunc.rightPad(ERR_INFO_HEADER_FOR_CREDIT_LIST, MAIL_DIGIT_FOR_CREDIT_LIST, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_INFO_HEADER_FOR_ITEM_CODE, MAIL_DIGIT_FOR_ITEM_CODE, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_INFO_HEADER_FOR_CSA_ITEM, MAIL_DIGIT_FOR_CSA_ITEM, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_INFO_HEADER_FOR_IUD_FLAG, MAIL_DIGIT_FOR_IUD_FLAG, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_INFO_HEADER_FOR_UPD_DATE, MAIL_DIGIT_FOR_UPD_DATE, MSG_SPACE));
        sb.append(ZYPCommonFunc.leftPad(ERR_INFO_HEADER_FOR_ERR_MSG, MAIL_DIGIT_FOR_ERR_MSG, MSG_SPACE));
        sb.append(CRLF);

        sb.append(ZYPCommonFunc.rightPad(ERR_MSG_HEADER_RULE_FOR_CREDIT_LIST, MAIL_DIGIT_FOR_CREDIT_LIST, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_MSG_HEADER_RULE_FOR_ITEM_CODE, MAIL_DIGIT_FOR_ITEM_CODE, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_MSG_HEADER_RULE_FOR_CSA_ITEM, MAIL_DIGIT_FOR_CSA_ITEM, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_MSG_HEADER_RULE_FOR_IUD_FLAG, MAIL_DIGIT_FOR_IUD_FLAG, MSG_SPACE));
        sb.append(ZYPCommonFunc.rightPad(ERR_MSG_HEADER_RULE_FOR_UPD_DATE, MAIL_DIGIT_FOR_UPD_DATE, MSG_SPACE));
        sb.append(ZYPCommonFunc.leftPad(ERR_MSG_HEADER_RULE_FOR_ERR_MSG, MAIL_DIGIT_FOR_ERR_MSG, MSG_SPACE));
        sb.append(CRLF);
        sb.append(CRLF);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        String[] csmpProcStsCdList = new String[] {CSMP_PROC_STS.ERROR, CSMP_PROC_STS.WARNING};
        ssmParam.put("csmpProcStsCdList", csmpProcStsCdList);
        BigDecimal[] errRecPks = this.errRecPkList.toArray(new BigDecimal[this.errRecPkList.size()]);
        ssmParam.put("targetRecPkList", errRecPks);

        List<Map< ? , ? >> errMsgRecList = (List<Map< ? , ? >>) this.ssmBatchClient.queryObjectList("getErrorMessage", ssmParam);

        for (Map< ? , ? > errMsgRec : errMsgRecList) {
            // QC#9945 2016/06/14 Mod start
//            sb.append(ZYPCommonFunc.rightPad((String) errMsgRec.get(CREDIT_LIST), MAIL_DIGIT_FOR_CREDIT_LIST, MSG_SPACE));
            String creditList = new String();
            if (ZYPCommonFunc.hasValue((String) errMsgRec.get(CR_LIST_TXT))
                    && ZYPCommonFunc.hasValue((String) errMsgRec.get(CR_LIST_GNRN_NUM))) {
                creditList = (String) errMsgRec.get(CR_LIST_TXT) + "." + (String) errMsgRec.get(CR_LIST_GNRN_NUM);
            } else if (!ZYPCommonFunc.hasValue((String) errMsgRec.get(CR_LIST_TXT))) {
                creditList = (String) errMsgRec.get(CR_LIST_GNRN_NUM);
            } else if (!ZYPCommonFunc.hasValue((String) errMsgRec.get(CR_LIST_GNRN_NUM))) {
                creditList = (String) errMsgRec.get(CR_LIST_TXT);
            }
            sb.append(ZYPCommonFunc.rightPad(creditList, MAIL_DIGIT_FOR_CREDIT_LIST, MSG_SPACE));
            // QC#9945 2016/06/14 Mod end
            sb.append(ZYPCommonFunc.rightPad((String) errMsgRec.get(CUSA_MDSE_CD), MAIL_DIGIT_FOR_ITEM_CODE, MSG_SPACE));
            sb.append(ZYPCommonFunc.rightPad((String) errMsgRec.get(CSA_MDSE_CD), MAIL_DIGIT_FOR_CSA_ITEM, MSG_SPACE));
            sb.append(ZYPCommonFunc.rightPad((String) errMsgRec.get(CSMP_TRX_STS_CD), MAIL_DIGIT_FOR_IUD_FLAG, MSG_SPACE));
            // QC#9945 2016/06/14 Mod start
//            sb.append(ZYPCommonFunc.rightPad((String) errMsgRec.get(LAST_UPD_DT), MAIL_DIGIT_FOR_UPD_DATE, MSG_SPACE));
            sb.append(ZYPCommonFunc.rightPad(formatDt((String) errMsgRec.get(LAST_UPD_DT)), MAIL_DIGIT_FOR_UPD_DATE, MSG_SPACE));
            // QC#9945 2016/06/14 Mod end
            String errMsgTxt = (String) errMsgRec.get(CSMP_ERR_MSG_TXT);
            if (ZYPCommonFunc.hasValue(errMsgTxt)
                    && MAIL_DIGIT_FOR_ERR_MSG < errMsgTxt.length()) {
                // - 1 is a space.
                sb.append(ZYPCommonFunc.leftPad(errMsgTxt.substring(SUBSTR_FROM_POS, MAIL_DIGIT_FOR_ERR_MSG - 1), MAIL_DIGIT_FOR_ERR_MSG, MSG_SPACE));
            } else {
                sb.append(ZYPCommonFunc.leftPad(errMsgTxt, MAIL_DIGIT_FOR_ERR_MSG, MSG_SPACE));
            }
            sb.append(CRLF);
        }
        return sb.toString();
    }

    // QC#9945 2016/06/14 Add start
    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }
    // QC#9945 2016/06/14 Add end
}
