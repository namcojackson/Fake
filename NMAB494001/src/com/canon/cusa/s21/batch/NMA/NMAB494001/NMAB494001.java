/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB494001;

import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.BATCH_PROGRAM_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.BATCH_PROGRAM_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.COMMA;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.CRLF;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DEFAULT_COMMIT_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DUNS_CRIT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DUNS_CRIT_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DUNS_TRX_HDR;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.DUNS_TRX_XTRCT;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.END_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.EQUAL;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.GROUP_ID_ONE;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.GROUP_ID_ZERO;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.INTFC_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.KEY_ACCT_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_CHARSET;
//import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_DUNS_TRX_HDR;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_FIELD_BATCH_NAME;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_FIELD_BATCH_PROC_LOG_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_FIELD_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_FIELD_SUC_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_KEY_TO_ERROR;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_KEY_TO_NORMAL;
//import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_TEMPLATE_ERROR;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_TEMPLATE_NORMAL;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_TYPE_FROM;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.MAX_FILE_NAME_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAI4900_01;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAI4900_02;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAI4900_03;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAI4900_04;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.PTY_LOC_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.PROS_PTY_LOC_WRK;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8028E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8031E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8186E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8459E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8504E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8596E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8678E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.NMAM8679E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.SPACE;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB494001.constant.NMAB494001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DUNS_FILE_TPTMsg;
import business.db.DUNS_TRX_FILETMsg;
import business.db.DUNS_TRX_HDRTMsg;
import business.db.DUNS_TRX_XTRCTTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.NMAI4900_01TMsg;
import business.db.NMAI4900_02TMsg;
import business.db.NMAI4900_03TMsg;
import business.db.NMAI4900_04TMsg;
//import business.db.PROS_PTY_LOC_WRKTMsg;
//import business.db.PTY_LOC_WRKTMsg;
//import business.parts.NMZC001001PMsg;
//import business.parts.NMZC001002PMsg;

//import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
//import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.batch.NMA.NMAB494001.S21SsmBatchClientCustom;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_DLR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CHNG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CRIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_FILE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
//import com.canon.cusa.s21.framework.api.S21ApiMessage;
//import com.canon.cusa.s21.framework.api.S21ApiUtil;
//import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
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
 * Load DUNS by batch (Outbound)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Murai         Create          N/A
 * 2016/06/20   Fujitsu         H.Ikeda         Update          QC#10255
 * 2016/06/20   Fujitsu         H.Ikeda         Update          QC#10493
 * 2016/06/27   Fujitsu         H.Ikeda         Update          QC#10816
 * 2016/06/27   Fujitsu         H.Ikeda         Update          QC#10892
 * 2016/06/29   Fujitsu         H.Ikeda         Update          QC#11065
 * 2016/07/01   Fujitsu         M.Ohno          Update          QC#11147
 * 2016/07/12   Fujitsu         T.Murai         Update          QC#10104
 * 2016/07/20   Fujitsu         R.Nakamura      Update          QC#12021
 * 2016/08/31   Fujitsu         N.Sugiura       Update          QC#13861
 * 2016/10/17   Hitachi         T.Mizuki        Update          QC#15017
 * 2017/11/07   Fujitsu         M.Ohno          Update          QC#22034
 * 2018/03/30   Fujitsu         Hd.Sugawara     Update          QC#24462
 * 2018/06/28   Fujitsu         Mz.Takahashi    Update          QC#25339
 *</pre>
 */

public class NMAB494001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    // Mod Start 2016/08/31 QC#13861
    /** interface Id(NMAI4900_01) : DNB_CLEANSED_NEW_PROSPECTS.txt */
    private String nmai490001interfaceId = null;

    /** interface Id(NMAI4900_02) : DNB_NEW_CUSTOMERS_AND_PROSPECTS.txt */
    private String nmai490002interfaceId = null;

    /** interface Id(NMAI4900_03) : DNB_NAME_ADDRESS_PHONE_CHANGE.txt */
    private String nmai490003interfaceId = null;

    /** interface Id(NMAI4900_04) : DNB_CLEANSED_ALL_YEARLY.txt */
    private String nmai490004interfaceId = null;
    // Mod End 2016/08/31 QC#13861

    /** Sales Date */
    private String salesDate = null;

    /** classificationCd */
    private String classificationCd = null;

    // Del Start 2016/07/12 QC#10104
    // /** glClassificationCd */
    // private String glClassificationCd = null;
    //
    // /** glIntercompanyCd */
    // private String glIntercompanyCd = null;
    // Del End 2016/07/12 QC#10104
    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** total count */
    private int notCommitCount = 0;

    /** error message */
    private StringBuilder errMsg = null;

    /** Success message */
    private StringBuilder sucMsg = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    // Add Start 2018/07/05 QC#25339
    /** SQL access parts */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;
    // Add End 2018/07/05 QC#25339

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor tblAccessor = null;

    // Mod Start 2016/08/31 QC#13861
    /** Transaction ID(NMAI4900_01) : DNB_CLEANSED_NEW_PROSPECTS.txt */
    private BigDecimal nmai490001transactionId;

    /** Transaction ID(NMAI4900_02) : DNB_NEW_CUSTOMERS_AND_PROSPECTS.txt */
    private BigDecimal nmai490002transactionId;

    /** Transaction ID(NMAI4900_03) : DNB_NAME_ADDRESS_PHONE_CHANGE.txt */
    private BigDecimal nmai490003transactionId;

    /** Transaction ID(NMAI4900_04) : DNB_CLEANSED_ALL_YEARLY.txt */
    private BigDecimal nmai490004transactionId;
    // Mod End 2016/08/31 QC#13861

    /** Most recent transmission date */
    private String recentDate = null;

    /** NMAI4900 value (UNIT_ID) */
    private final BigDecimal nmai4900UnitId = new BigDecimal(1);

    /** NMAI4900_01 (SEGMENT_ID) */
    private int nmai490001segmentId = 1;

    /** NMAI4900_02 (SEGMENT_ID) */
    private int nmai490002segmentId = 1;

    /** NMAI4900_03 (SEGMENT_ID) */
    private int nmai490003segmentId = 1;

    /** NMAI4900_04 (SEGMENT_ID) */
    private int nmai490004segmentId = 1;

    /** NMAI4900_01 (SEQ_NUMBER) */
    private BigDecimal nmai490001seqNumberId = BigDecimal.ONE;

    /** NMAI4900_02 (SEQ_NUMBER) */
    private BigDecimal nmai490002seqNumberId = BigDecimal.ONE;

    /** NMAI4900_03 (SEQ_NUMBER) */
    private BigDecimal nmai490003seqNumberId = BigDecimal.ONE;

    /** NMAI4900_04 (SEQ_NUMBER) */
    private BigDecimal nmai490004seqNumberId = BigDecimal.ONE;

    // Add Start 2018/07/02 QC#25339
    private int commitCount = DEFAULT_COMMIT_SIZE;
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    private class DunsDataBucket{
        DUNS_TRX_XTRCTTMsg dtx = null;
        NMAI4900_01TMsg nMai4900_01 = null;
        NMAI4900_02TMsg nMai4900_02 = null;
        NMAI4900_03TMsg nMai4900_03 = null;
        NMAI4900_04TMsg nMai4900_04 = null;
        BigDecimal ptyLocPk = null;
        BigDecimal prosPtyLocPk = null;

        public DUNS_TRX_XTRCTTMsg getDtx() {
            return dtx;
        }
        public void setDtx(DUNS_TRX_XTRCTTMsg dtx) {
            this.dtx = dtx;
        }
        public NMAI4900_01TMsg getNMai4900_01() {
            return nMai4900_01;
        }
        public void setNMai4900_01(NMAI4900_01TMsg mai4900_01) {
            nMai4900_01 = mai4900_01;
        }
        public NMAI4900_02TMsg getNMai4900_02() {
            return nMai4900_02;
        }
        public void setNMai4900_02(NMAI4900_02TMsg mai4900_02) {
            nMai4900_02 = mai4900_02;
        }
        public NMAI4900_03TMsg getNMai4900_03() {
            return nMai4900_03;
        }
        public void setNMai4900_03(NMAI4900_03TMsg mai4900_03) {
            nMai4900_03 = mai4900_03;
        }
        public NMAI4900_04TMsg getNMai4900_04() {
            return nMai4900_04;
        }
        public void setNMai4900_04(NMAI4900_04TMsg mai4900_04) {
            nMai4900_04 = mai4900_04;
        }
        public BigDecimal getPtyLocPk() {
            return ptyLocPk;
        }
        public void setPtyLocPk(BigDecimal ptyLocPk) {
            this.ptyLocPk = ptyLocPk;
        }
        public BigDecimal getProsPtyLocPk() {
            return prosPtyLocPk;
        }
        public void setProsPtyLocPk(BigDecimal prosPtyLocPk) {
            this.prosPtyLocPk = prosPtyLocPk;
        }
    }
    // Add End 2018/07/02 QC#25339

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB494001().executeBatch(NMAB494001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD });
        }

        // value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {GLBL_CMPY_CD });
        }

        // Mod Start 2016/08/31 QC#13861
        this.nmai490001interfaceId = getInterfaceIdForDunsFileType(DUNS_FILE_TP.DNB_CLEANSED_NEW_PROSPECTS_TXT);
        if (!ZYPCommonFunc.hasValue(this.nmai490001interfaceId)) {
            throw new S21AbendException(NMAM8504E, new String[] {INTFC_ID });
        }
        this.nmai490002interfaceId = getInterfaceIdForDunsFileType(DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT);
        if (!ZYPCommonFunc.hasValue(this.nmai490002interfaceId)) {
            throw new S21AbendException(NMAM8504E, new String[] {INTFC_ID });
        }
        this.nmai490003interfaceId = getInterfaceIdForDunsFileType(DUNS_FILE_TP.DNB_NAME_ADDRESS_PHONE_CHANGE_TXT);
        if (!ZYPCommonFunc.hasValue(this.nmai490003interfaceId)) {
            throw new S21AbendException(NMAM8504E, new String[] {INTFC_ID });
        }
        this.nmai490004interfaceId = getInterfaceIdForDunsFileType(DUNS_FILE_TP.DNB_CLEANSED_ALL_YEARLY_TXT);
        if (!ZYPCommonFunc.hasValue(this.nmai490004interfaceId)) {
            throw new S21AbendException(NMAM8504E, new String[] {INTFC_ID });
        }
        // Mod End 2016/08/31 QC#13861

        this.salesDate = ZYPDateUtil.getSalesDate();

        // Add Start 2018/07/03 QC#25339
        int count =  this.getCommitCount();

        if (count > this.commitCount) {
            this.commitCount = count; 
        }
        // Add End 2018/07/03 QC#25339

        // Get Classification Code
        this.classificationCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_ACCT_CLS_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(classificationCd)) {
            throw new S21AbendException(NMAM8186E, new String[] {CLS_CD });
        }
        // Del Start 2016/07/12 QC#10104
        // Get GL Classification code, GL Intercompany code
        // DEF_DPLY_COA_INFOTMsg coaInfoTmsg = new DEF_DPLY_COA_INFOTMsg();
        // ZYPEZDItemValueSetter.setValue(coaInfoTmsg.glblCmpyCd, this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(coaInfoTmsg.appFuncId, KEY_COA_INFO);
        // coaInfoTmsg = (DEF_DPLY_COA_INFOTMsg) S21CacheTBLAccessor.findByKey(coaInfoTmsg);
        //
        // if (coaInfoTmsg == null) {
        // throw new S21AbendException(NMAM8186E, new String[] {GL_CLS_CD });
        // }
        // this.glClassificationCd = coaInfoTmsg.coaChCd.getValue();
        // this.glIntercompanyCd = coaInfoTmsg.coaAfflCd.getValue();
        // Del End 2016/07/12 QC#10104

        tblAccessor = new S21TransactionTableAccessor();

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        
        // Add Start 2018/07/05 QC#25339
        this.ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
        // Add End 2018/07/05 QC#25339
    }

    @Override
    protected void mainRoutine() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Search Target Data - Header
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("dunsProcTpExtraDNB", DUNS_PROC_TP.EXTRACT_FOR_DNB);
            ssmParam.put("dunsProcStsRegist", DUNS_PROC_STS.REGISTERED);

            stmt = this.ssmLLClient.createPreparedStatement("getDunsTrxHeaderPk", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            while (rsSet.next()) {

                String hdrPk = rsSet.getString("DUNS_TRX_HDR_PK");

                // Del Start 2016/08/31 QC#13861
                // create Integration Record
                // this.transactionId = tblAccessor.createIntegrationRecord(this.interfaceId);
                // Del End 2016/08/31 QC#13861

                // Crear transactionId
                nmai490001transactionId = null;
                nmai490002transactionId = null;
                nmai490003transactionId = null;
                nmai490004transactionId = null;

                boolean successFlg = createSendDate(hdrPk);

                // Register the relevant data of the DUNS
                if (successFlg) {
                    registDunsHdr(hdrPk);
                }
            }
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            this.totalCount = this.successCount + this.errorCount;
        }

        if (this.errMsg != null) {
            // send Error Mail
            sendErrorMail();
            this.termCd = TERM_CD.WARNING_END;
        } else {
            if (this.sucMsg != null) {
                // Send Notification Mail
                sendMail();
            }
        }
    }

    /**
     * Create Send Date for DUNS_TRX_HDR_PK
     * @param dunsHdrPk
     * @return boolean False-Error
     */
    private boolean createSendDate(String dunsHdrPk) {

        // Search Target Data Criteria
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dunsHdrPk", dunsHdrPk);

        // Mod 2016/07/12 QC#10104
        // List<Map<String, Object>> dunsCritList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDunsCriteria", ssmParam);
        List<Map<String, String>> dunsCritList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDunsCriteria", ssmParam);

        if (dunsCritList != null && 0 < dunsCritList.size()) {
            boolean isError = false;

            List<String> xtractGrpIdList = new ArrayList<String>();
            List<String> dunsCritCdList = new ArrayList<String>();

            String critCd34ValTxt = null;
            for (Map<String, String> dunsCritMap : dunsCritList) {

                xtractGrpIdList.add(dunsCritMap.get("DUNS_CRIT_XTRCT_GRP_ID"));
                String critCd = dunsCritMap.get("DUNS_CRIT_CD");
                dunsCritCdList.add(critCd);
                if (DUNS_CRIT.INCLUDE_RECORDS_CLEANSED_BEFORE.equals(critCd)) {
                    critCd34ValTxt = dunsCritMap.get("DUNS_CRIT_VAL_TXT");
                }

                isError = validCheck(dunsHdrPk, dunsCritMap, isError);
            }
            // ListCheck - DUNS_CRIT_XTRCT_GRP_ID
            isError = listValidCheck(dunsHdrPk, xtractGrpIdList, isError);

            if (isError) {
                return false;
            }

            // Insert Duns Transaction
            // Mod Start 2018/03/30 QC#24462
            //if (!insertDunsTrx(dunsHdrPk, dunsCritCdList, critCd34ValTxt)) {
            //    return false;
            //}
            insertDunsTrx(dunsHdrPk, dunsCritCdList, critCd34ValTxt);
            // Mod End 2018/03/30 QC#24462

        }
        return true;
    }

    // Add Start 2018/07/02 QC#25339
    private boolean insertDunsTrx(String dunsTrxHdrPk, List<String> dunsCritCdList, String critCd34ValTxt) {

        boolean result = true;

        String dbIndentifier = getDBIdentifier();
        List<DUNS_TRX_XTRCTTMsg> dtxList = new ArrayList<DUNS_TRX_XTRCTTMsg>();
        List<NMAI4900_01TMsg> nMai4900_01List = new ArrayList<NMAI4900_01TMsg>();
        List<NMAI4900_02TMsg> nMai4900_02List = new ArrayList<NMAI4900_02TMsg>();
        List<NMAI4900_03TMsg> nMai4900_03List = new ArrayList<NMAI4900_03TMsg>();
        List<NMAI4900_04TMsg> nMai4900_04List = new ArrayList<NMAI4900_04TMsg>();
        List<DunsDataBucket> bucketList = new ArrayList<DunsDataBucket>();

        List<String> dunsFileTpSet = getFileTp(dunsCritCdList);

        int dunsFileCnt = dunsFileTpSet.size();
        int fileCnt = 0;

        for (String fileTpCd : dunsFileTpSet) {
            fileCnt = fileCnt + 1;

            ResultSet rs = null;
            PreparedStatement stmtSelect = null;
            Map<String, Object> params = getExtractSendDataParams(dunsTrxHdrPk, fileTpCd, dunsCritCdList, critCd34ValTxt);

            try {
                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(DEFAULT_FETCH_SIZE);
                execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

                stmtSelect = this.ssmLLClient.createPreparedStatement("getExtractSendData", params, execParam);
                rs = stmtSelect.executeQuery();

                int dataCnt = 0;
                List<NMAI4900_01TMsg> nMai4900_01ListWrk = new ArrayList<NMAI4900_01TMsg>();
                List<NMAI4900_02TMsg> nMai4900_02ListWrk = new ArrayList<NMAI4900_02TMsg>();
                List<NMAI4900_03TMsg> nMai4900_03ListWrk = new ArrayList<NMAI4900_03TMsg>();
                List<NMAI4900_04TMsg> nMai4900_04ListWrk = new ArrayList<NMAI4900_04TMsg>();

                while (rs.next()) {
                    DunsDataBucket bucket = new DunsDataBucket();
                    nMai4900_01ListWrk.clear();
                    nMai4900_02ListWrk.clear();
                    nMai4900_03ListWrk.clear();
                    nMai4900_04ListWrk.clear();

                    //
                    // DUNS_TRX_XTRCT
                    //
                    DUNS_TRX_XTRCTTMsg dtxTMsg = toDunsTrxXtractTMsg(dunsTrxHdrPk, fileTpCd, rs);
                    bucket.setDtx(dtxTMsg);

                    //
                    // PTY_LOC_WRK or PROS_PTY_LOC_WRK
                    //
                    if (fileCnt == dunsFileCnt) {
                        setPtyLocWrkPk(rs, bucket);
                    }

                    //
                    // I/F Insert
                    //
                    if (!toInterfaceTmsg(
                            dunsTrxHdrPk, fileTpCd, rs,
                            nMai4900_01ListWrk,
                            nMai4900_02ListWrk,
                            nMai4900_03ListWrk,
                            nMai4900_04ListWrk,
                            bucket)) {
                        continue;
                    }

                    this.notCommitCount++;
                    dataCnt++;

                    dtxList.add(dtxTMsg);
                    nMai4900_01List.addAll(nMai4900_01ListWrk);
                    nMai4900_02List.addAll(nMai4900_02ListWrk);
                    nMai4900_03List.addAll(nMai4900_03ListWrk);
                    nMai4900_04List.addAll(nMai4900_04ListWrk);
                    bucketList.add(bucket);

                    if (this.notCommitCount >= this.commitCount) {
                        bulkSync(dunsTrxHdrPk,
                                dtxList, 
                                nMai4900_01List, 
                                nMai4900_02List, 
                                nMai4900_03List, 
                                nMai4900_04List, 
                                bucketList,
                                dbIndentifier);
                        this.notCommitCount = 0;
                    }
                }

                if (this.notCommitCount > 0) {
                    bulkSync(dunsTrxHdrPk,
                            dtxList, 
                            nMai4900_01List, 
                            nMai4900_02List, 
                            nMai4900_03List, 
                            nMai4900_04List, 
                            bucketList,
                            dbIndentifier);
                    this.notCommitCount = 0;
                }

                //
                // INSERT DUNS_TRX_FILE
                //
                if (!insertDunsTrxFile(dunsTrxHdrPk, fileTpCd, BigDecimal.valueOf(dataCnt))) {
                    continue;
                }
                commit();

                if (dataCnt == 0) {
                    continue;
                }

                addMessageParam(dunsTrxHdrPk, getFileTpTxt(fileTpCd), dataCnt);
                commit();

            } catch (SQLException e) {
                throw new S21AbendException(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            }
        }

        return result;
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    private void setPtyLocWrkPk(ResultSet rs, DunsDataBucket bucket){
        try {
            if (DS_ACCT_TP.PROSPECT.equals(rs.getString("DS_ACCT_TP_CD"))) {
                bucket.setProsPtyLocPk(rs.getBigDecimal("PTY_LOC_PK"));
            } else {
                bucket.setPtyLocPk(rs.getBigDecimal("PTY_LOC_PK"));
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    private void bulkSync(
            String dunsTrxHdrPk,
            List<DUNS_TRX_XTRCTTMsg> dtxList,
            List<NMAI4900_01TMsg> nMai4900_01List,
            List<NMAI4900_02TMsg> nMai4900_02List,
            List<NMAI4900_03TMsg> nMai4900_03List,
            List<NMAI4900_04TMsg> nMai4900_04List,
            List<DunsDataBucket> bucketList,
            String dbIndentifier
            ){
        Boolean recoverFlg = true;

        if (bulkInsert(dtxList, nMai4900_01List, nMai4900_02List, nMai4900_03List, nMai4900_04List)){
            if (bulkUpdateByDunsDataBucket(bucketList, dbIndentifier)){
            recoverFlg = false;
        }
        }

        if (recoverFlg){
            rollback();
            recoverRegist(dunsTrxHdrPk, bucketList, dbIndentifier);
        } else {
            this.successCount += this.notCommitCount;
        }

        commit();

        dtxList.clear();
        nMai4900_01List.clear();
        nMai4900_02List.clear();
        nMai4900_03List.clear();
        nMai4900_04List.clear();
        bucketList.clear();
        
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    private void recoverRegist(String dunsTrxHdrPk, List<DunsDataBucket> recoverList, String dbIndentifier){
        Boolean isCommit = false;

        if (recoverList.isEmpty()){
            return;
        }

        BigDecimal nmai490001seqNumberIdWrk = getMinSeqNumber(recoverList, 1);
        BigDecimal nmai490002seqNumberIdWrk = getMinSeqNumber(recoverList, 2);
        BigDecimal nmai490003seqNumberIdWrk = getMinSeqNumber(recoverList, 3);
        BigDecimal nmai490004seqNumberIdWrk = getMinSeqNumber(recoverList, 4);

        BigDecimal nmai490001seqNumberIdBk;
        BigDecimal nmai490002seqNumberIdBk;
        BigDecimal nmai490003seqNumberIdBk;
        BigDecimal nmai490004seqNumberIdBk;
        
        for(DunsDataBucket dat : recoverList){
            isCommit = true;

            nmai490001seqNumberIdBk = nmai490001seqNumberIdWrk;
            nmai490002seqNumberIdBk = nmai490002seqNumberIdWrk;
            nmai490003seqNumberIdBk = nmai490003seqNumberIdWrk;
            nmai490004seqNumberIdBk = nmai490004seqNumberIdWrk;

            //
            // DUNS_TRX_XTRCT
            //
            EZDTMsg tmsg = dat.getDtx();
            String dsAcctNum = dat.getDtx().dsAcctNum.getValue();
            String locNum = dat.getDtx().locNum.getValue();

            S21FastTBLAccessor.insert(tmsg);

            if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                // Nothing
            } else {
                isCommit = false;
                addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, 
                        new String[] {DUNS_TRX_XTRCT, dsAcctNum, locNum });
                S21InfoLogOutput.println(NMAM8678E, new String[] {DUNS_TRX_XTRCT, dsAcctNum, locNum });
            }

            //
            // NMAI4900_01
            //
            tmsg = dat.getNMai4900_01(); 
            if ((tmsg != null) && (isCommit)) {
                dat.getNMai4900_01().seqNumber.setValue(nmai490001seqNumberIdWrk);
                S21FastTBLAccessor.insert(tmsg);

                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    nmai490001seqNumberIdWrk = nmai490001seqNumberIdWrk.add(BigDecimal.ONE);
                } else {
                    isCommit = false;
                    addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_01, dsAcctNum, locNum });
                    S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_01, dsAcctNum, locNum });
                }
            }

            //
            // NMAI4900_02
            //
            tmsg = dat.getNMai4900_02(); 
            if ((tmsg != null) && (isCommit)) {
                dat.getNMai4900_02().seqNumber.setValue(nmai490002seqNumberIdWrk);
                S21FastTBLAccessor.insert(tmsg);

                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    nmai490002seqNumberIdWrk = nmai490002seqNumberIdWrk.add(BigDecimal.ONE);
                } else {
                    isCommit = false;
                    addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_02, dsAcctNum, locNum });
                    S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_02, dsAcctNum, locNum });
                }
            }

            //
            // NMAI4900_03
            //
            tmsg = dat.getNMai4900_03(); 
            if ((tmsg != null) && (isCommit)) {
                dat.getNMai4900_03().seqNumber.setValue(nmai490003seqNumberIdWrk);
                S21FastTBLAccessor.insert(tmsg);

                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    nmai490003seqNumberIdWrk = nmai490003seqNumberIdWrk.add(BigDecimal.ONE);
                } else {
                    isCommit = false;
                    addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_03, dsAcctNum, locNum });
                    S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_03, dsAcctNum, locNum });
                }
            }

            //
            // NMAI4900_04
            //
            tmsg = dat.getNMai4900_04(); 
            if ((tmsg != null) && (isCommit)) {
                dat.getNMai4900_04().seqNumber.setValue(nmai490004seqNumberIdWrk);
                S21FastTBLAccessor.insert(tmsg);

                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    nmai490004seqNumberIdWrk = nmai490004seqNumberIdWrk.add(BigDecimal.ONE);
                } else {
                    isCommit = false;
                    addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_04, dsAcctNum, locNum });
                    S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_04, dsAcctNum, locNum });
                }
            }

            //
            // PTY_LOC_WRK
            //
            if (ZYPCommonFunc.hasValue(dat.getPtyLocPk())){
                int ret = bulkUpdate(Arrays.asList(dat.getPtyLocPk()), dbIndentifier, DS_ACCT_TP.CUSTOMER);

                if (ret <= 0){
                    isCommit = false;
                    addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {PTY_LOC_WRK, dsAcctNum, locNum});
                    S21InfoLogOutput.println(NMAM8678E, new String[] {PTY_LOC_WRK, dsAcctNum, locNum });
                }
            }

            //
            // PROS_PTY_LOC_WRK
            //
            if (ZYPCommonFunc.hasValue(dat.getProsPtyLocPk())){
                int ret = bulkUpdate(Arrays.asList(dat.getProsPtyLocPk()), dbIndentifier, DS_ACCT_TP.PROSPECT);

                if (ret <= 0){
                    isCommit = false;
                    addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {PROS_PTY_LOC_WRK, dsAcctNum, locNum});
                    S21InfoLogOutput.println(NMAM8678E, new String[] {PROS_PTY_LOC_WRK, dsAcctNum, locNum });
                }
            }

            if (isCommit){
                this.successCount++;
                commit();
            } else {
                this.errorCount++;
                rollback();
                nmai490001seqNumberIdWrk = nmai490001seqNumberIdBk;
                nmai490002seqNumberIdWrk = nmai490002seqNumberIdBk;
                nmai490003seqNumberIdWrk = nmai490003seqNumberIdBk;
                nmai490004seqNumberIdWrk = nmai490004seqNumberIdBk;
            }
        }

        this.nmai490001seqNumberId = nmai490001seqNumberIdWrk;
        this.nmai490002seqNumberId = nmai490002seqNumberIdWrk;
        this.nmai490003seqNumberId = nmai490003seqNumberIdWrk;
        this.nmai490004seqNumberId = nmai490004seqNumberIdWrk;

    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    private BigDecimal getMinSeqNumber(List<DunsDataBucket> recoverList, int type){
        BigDecimal nmai490001seqNumberIdMin = BigDecimal.ONE;
        EZDTBigDecimalItem tmp = null;

        for(DunsDataBucket dat : recoverList){
            tmp = null;

            switch(type){
                case 1:
                    if (dat.getNMai4900_01() != null){
                        tmp = dat.getNMai4900_01().seqNumber;
                    }
                    break;
                case 2:
                    if (dat.getNMai4900_02() != null){
                        tmp = dat.getNMai4900_02().seqNumber;
                    }
                    break;
                case 3:
                    if (dat.getNMai4900_03() != null){
                        tmp = dat.getNMai4900_03().seqNumber;
                    }
                    break;
                case 4:
                    if (dat.getNMai4900_04() != null){
                        tmp = dat.getNMai4900_04().seqNumber;
                    }                    
                    break;
                    
            }

            if (ZYPCommonFunc.hasValue(tmp)){
                nmai490001seqNumberIdMin = tmp.getValue();
                break;
            }
        }
        
        return nmai490001seqNumberIdMin;
        
    }
    // Add End 2018/07/02 QC#25339

    
    // Add Start 2018/07/02 QC#25339
    private Boolean bulkInsert(
            List<DUNS_TRX_XTRCTTMsg> dtxList,
            List<NMAI4900_01TMsg> nMai4900_01List,
            List<NMAI4900_02TMsg> nMai4900_02List,
            List<NMAI4900_03TMsg> nMai4900_03List,
            List<NMAI4900_04TMsg> nMai4900_04List
            ){

        List<Object[]> tmsgList = 
            Arrays.asList(
                    dtxList.toArray(), 
                    nMai4900_01List.toArray(), 
                    nMai4900_02List.toArray(), 
                    nMai4900_03List.toArray(), 
                    nMai4900_04List.toArray());

        List<String> tblName = Arrays.asList(
                DUNS_TRX_XTRCT, NMAI4900_01,NMAI4900_02, NMAI4900_03, NMAI4900_04);
        
        int idx = 0;
        for (Object[] tmsgArray : tmsgList){
            int max = tmsgArray.length;

            if (max <= 0){
                continue;
            }

            EZDTMsg[] list = new EZDTMsg[max];

            for (int cnt = 0; cnt < max; cnt++){
                list[cnt] = (EZDTMsg) tmsgArray[cnt];
            }

            int cnt = S21FastTBLAccessor.insert(list);

            if (cnt != max){
                S21InfoLogOutput.println(
                        String.format(
                                "Bulk insert error. Switch to single registration mode. Table[%s] Count[%d]", 
                                tblName.get(idx), dtxList.size()));
                return false;
            }
            idx++;
        }

        return true;
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/09 QC#25339
    private Boolean bulkUpdateByDunsDataBucket(List<DunsDataBucket> keys, String dbIndentifier){
        List<BigDecimal> custKeys = new ArrayList<BigDecimal>();
        List<BigDecimal> prosKeys = new ArrayList<BigDecimal>();

        for (DunsDataBucket key : keys){
            if (ZYPCommonFunc.hasValue(key.ptyLocPk)){
                custKeys.add(key.ptyLocPk);
            }

            if (ZYPCommonFunc.hasValue(key.getProsPtyLocPk())){
                prosKeys.add(key.prosPtyLocPk);
            }
        }

        if (!custKeys.isEmpty()){
            int ret = bulkUpdate(custKeys, dbIndentifier, DS_ACCT_TP.CUSTOMER);

            if (ret != custKeys.size()){
                S21InfoLogOutput.println(
                        String.format(
                                "Bulk update error. Switch to single update mode. Table[%s] Count[%d]",
                                PTY_LOC_WRK,
                                custKeys.size()
                                ));
                return false;
            }
        }

        if (!prosKeys.isEmpty()){
            int ret = bulkUpdate(prosKeys, dbIndentifier, DS_ACCT_TP.PROSPECT);

            if (ret != prosKeys.size()){
                S21InfoLogOutput.println(
                        String.format(
                                "Bulk update error. Switch to single update mode. Table[%s] Count[%d]",
                                PROS_PTY_LOC_WRK,
                                prosKeys.size()
                                ));
                return false;
            }
        } 

        return true;
    }
    // Add End 2018/07/09 QC#25339

    // Add Start 2018/07/05 QC#25339
    private int bulkUpdate(List<BigDecimal> keys, String dbIndentifier, String tp){

        BigDecimal[] keyArray = new BigDecimal[keys.size()];
        String statementId = "";
        Map<String, Object> updateParam = new HashMap<String, Object>();
        updateParam.put("glblCmpyCd", glblCmpyCd);
        updateParam.put("nowTime", EZDDBCICarrier.getStartDateTime());
        updateParam.put("timeZone", EZDDBCICarrier.getUpTimeZone());
        updateParam.put("upCmpyCd", EZDDBCICarrier.getUpCmpyCd());
        updateParam.put("userId", EZDDBCICarrier.getUserID());
        updateParam.put("aplId", EZDDBCICarrier.getUppgID());
        updateParam.put("dsLastRcvDunsTxt", dbIndentifier);
        updateParam.put("dsLastRcvDunsDt", this.salesDate);
        updateParam.put("ptyLocPks", keys.toArray(keyArray));

        if (DS_ACCT_TP.PROSPECT.equals(tp)){
            statementId = "updateProsPtyLocWrk";
        } else {
            statementId = "updatePtyLocWrk";
        }

        return  this.ssmBatchClientCustom.update(statementId, updateParam);

    }
    // Add End 2018/07/05 QC#25339

    // Del Start 2018/07/02 QC#25339
//  private boolean insertDunsTrx(String dunsTrxHdrPk, List<String> dunsCritCdList, String critCd34ValTxt) {
//
//            boolean result = true;
//
//            // Mod Start 2016/06/27 QC#10892
//            List<String> dunsFileTpSet = getFileTp(dunsCritCdList);
//            // Mod End 2016/06/27 QC#10892
//
////            List<Map<String, Object>> extractSendDataList;
//            // Add Start 2016/06/27 QC#10892
//            int dunsFileCnt = dunsFileTpSet.size();
//            int fileCnt = 0;
//            // Add End 2016/06/27 QC#10892
//            for (String fileTpCd : dunsFileTpSet) {
//                // Add Start 2016/06/27 QC#11065
////                boolean fileTpFlg = false;
//                // Add Start 2016/06/27 QC#11065
//                fileCnt = fileCnt + 1;
//
//                // Mod Start 2016/08/31 QC#13861
//                // Reset Interface SegmentNum for each DUNS_FILE_TP_CD
//                // resetSegmentNum();
//                // Mod End 2016/08/31 QC#13861
//
//                // Get Extract Send Data // Mod 2016/07/12 QC#10104
//                // extractSendDataList = getExtractSendData(dunsTrxHdrPk, fileTpCd, dunsCritCdList, critCd34ValTxt, updateFlg);
//                // Mod Start 2016/07/20 QC#12021
////                extractSendDataList = getExtractSendData(dunsTrxHdrPk, fileTpCd, dunsCritCdList, critCd34ValTxt);
//                ResultSet rs = null;
//                PreparedStatement stmtSelect = null;
//                Map<String, Object> params = getExtractSendDataParams(dunsTrxHdrPk, fileTpCd, dunsCritCdList, critCd34ValTxt);
//
//                try {
//                    S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//                    execParam.setFetchSize(DEFAULT_FETCH_SIZE);
//                    execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//                    execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//                    execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//
//                    stmtSelect = this.ssmLLClient.createPreparedStatement("getExtractSendData", params, execParam);
//                    rs = stmtSelect.executeQuery();
//
////                    // result null or 0
////                    if (extractSendDataList == null) {
////                        continue;
////                    }
////                    if (extractSendDataList.size() == 0) {
////                        // INSERT DUNS_TRX_FILE
////                        if (!insertDunsTrxFile(dunsTrxHdrPk, fileTpCd, BigDecimal.valueOf(extractSendDataList.size()))) {
////                            return false;
////                        }
////                        this.totalCount++;
////                        this.notCommitCount++;
////                        if (this.notCommitCount > DEFAULT_COMMIT_SIZE) {
////                            commit();
////                            this.successCount += this.notCommitCount;
////                            this.notCommitCount = 0;
////                        }
////                        continue;
////                    }
////
////                    for (Map<String, Object> extractSendDataMap : extractSendDataList) {
////
////                        // INSERT DUNS_TRX_XTRCT
////                        if (!insertDunsTrxXtract(dunsTrxHdrPk, fileTpCd, extractSendDataMap)) {
////                            return false;
////                        }
////
////                        // Mod Start 2016/06/27 QC#11065
////                        // INSERT DUNS_TRX_FILE
////                        if (!fileTpFlg) {
////                            fileTpFlg = true;
////                            if (!insertDunsTrxFile(dunsTrxHdrPk, fileTpCd, BigDecimal.valueOf(extractSendDataList.size()))) {
////                                return false;
////                            }
////                        }
////                        // Mod End 2016/06/27 QC#11065
////                        // Mod Start 2016/06/27 QC#10892
////                        if (fileCnt == dunsFileCnt) {
////                            // Call Customer Update API
////                            if (!callCustomerUpdateApi(dunsTrxHdrPk, extractSendDataMap)) {
////                                return false;
////                            }
////                        }
////                        // Mod Start 2016/06/27 QC#10892
////                        // I/F Insert
////                        if (!insertInterface(dunsTrxHdrPk, fileTpCd, extractSendDataMap)) {
////                            return false;
////                        }
////
////                        // Add Start 2016/06/21 QC#10493
////                        if (updateFlg) {
////                            updateDsPtyLocWrk((BigDecimal) extractSendDataMap.get("PTY_LOC_PK"));
////                        }
////                        // Add Start 2016/06/21 QC#10493
////                    }
////
////                    this.notCommitCount++;
////                    if (this.notCommitCount > DEFAULT_COMMIT_SIZE) {
////                        commit();
////                        this.successCount += this.notCommitCount;
////                        this.notCommitCount = 0;
////                    }
////                    addMessageParam(dunsTrxHdrPk, getFileTpTxt(fileTpCd), extractSendDataList.size());
////
////                    commit();
////                    this.successCount += this.notCommitCount;
////                    this.notCommitCount = 0;
//
//                    int dataCnt = 0;
//                    while (rs.next()) {
//
//                        // INSERT DUNS_TRX_XTRCT
//                        if (!insertDunsTrxXtract(dunsTrxHdrPk, fileTpCd, rs)) {
//                            // Mod Start 2018/03/30 QC#24462
//                            //return false;
//                            continue;
//                            // Mod End 2018/03/30 QC#24462
//                        }
//
//                        if (fileCnt == dunsFileCnt) {
//                            // Call Customer Update API
//                            if (!callCustomerUpdateApi(dunsTrxHdrPk, rs)) {
//                                // Mod Start 2018/03/30 QC#24462
//                                //return false;
//                                continue;
//                                // Mod End 2018/03/30 QC#24462
//                            }
//                        }
//
//                        // I/F Insert
//                        if (!insertInterface(dunsTrxHdrPk, fileTpCd, rs)) {
//                            // Mod Start 2018/03/30 QC#24462
//                            //return false;
//                            continue;
//                            // Mod End 2018/03/30 QC#24462
//                        }
//
//                        this.notCommitCount++;
//                        dataCnt++;
//                        if (this.notCommitCount >= DEFAULT_COMMIT_SIZE) {
//                            commit();
//                            this.successCount += this.notCommitCount;
//                            this.notCommitCount = 0;
//                        }
//                    }
//
//                    if (this.notCommitCount > 0) {
//                        commit();
//                        this.successCount += this.notCommitCount;
//                        this.notCommitCount = 0;
//                    }
//
//                    // INSERT DUNS_TRX_FILE
//                    if (!insertDunsTrxFile(dunsTrxHdrPk, fileTpCd, BigDecimal.valueOf(dataCnt))) {
//                        // Mod Start 2018/03/30 QC#24462
//                        //return false;
//                        continue;
//                        // Mod End 2018/03/30 QC#24462
//                    }
//                    commit();
//                    this.successCount += 1;
//
//                    if (dataCnt == 0) {
//                        this.totalCount++;
//                        continue;
//                    }
//
//                    addMessageParam(dunsTrxHdrPk, getFileTpTxt(fileTpCd), dataCnt);
//                    commit();
//
//                } catch (SQLException e) {
//                    throw new S21AbendException(e);
//                } finally {
//                    S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
//                }
//            }
//
//            return result;
//        }
    // Del End 2018/07/02 QC#25339

    // Del Start 2016/07/20 QC#12021
    // Mod 2016/07/12 QC#10104
    // private List<Map<String, Object>> getExtractSendData(String dunsTrxHdrPk, String fileTpCd, List<String> dunsCritCdList, String critCd34ValTxt, boolean updateFlg) {
//    private List<Map<String, Object>> getExtractSendData(String dunsTrxHdrPk, String fileTpCd, List<String> dunsCritCdList, String critCd34ValTxt) {
//
//        boolean customerFlg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_CUSTOMERS);
//        boolean prospectFlg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_PROSPECTS);
//        // Add Start 2016/06/20 QC#10255
//        boolean crit31Flg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_RECORDS_WITH_NAME_CHANGE_SINCE_LAST_EXTRACT);
//        boolean crit32Flg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_RECORDS_WITH_ADDRESS_CHANGE_SINCE_LAST_EXTRACT);
//        boolean crit33Flg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_RECORDS_WITH_PHONE_NUM_CHANGE_SINCE_LAST_EXTRACT);
//        // Add End 2016/06/21 QC#10255
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("slsDt", this.salesDate);
//        ssmParam.put("endDt", END_DATE);
//        ssmParam.put("rgtnStsP20", RGTN_STS.READY_FOR_ORDER_TAKING);
//        ssmParam.put("filterLineAddr", "158 GAITH%");
//        ssmParam.put("filterForAcctNm", getParamFilterAcctNm());
//        ssmParam.put("filterForAcctDlrCd", getParamFilterAcctDlrCd());
//        ssmParam.put("ctryCdUS", CTRY.UNITED_STATES_OF_AMERICA);
//        ssmParam.put("filterforAddr", getParamFilterAddr());
//
//        if (DUNS_FILE_TP.DNB_CLEANSED_ALL_YEARLY_TXT.equals(fileTpCd)) {
//
//            // for DUNS_FILE_TP : 04
//            ssmParam.put("custFlg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("crit12Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit21Flg", ZYPConstant.FLG_OFF_N);
//            // Add Start 2016/06/21 QC#10493
//            ssmParam.put("crit31Flg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("crit31ValTxt", DUNS_CHNG_TP.UPDATED);
//            ssmParam.put("crit32Flg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("crit32ValTxt", DUNS_CHNG_TP.UPDATED);
//            ssmParam.put("crit33Flg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("crit33ValTxt", DUNS_CHNG_TP.UPDATED);
//            // Add End 2016/06/21 QC#10493
//            ssmParam.put("crit34Flg", ZYPConstant.FLG_OFF_N);
//        } else if (DUNS_FILE_TP.DNB_CLEANSED_NEW_PROSPECTS_TXT.equals(fileTpCd)) {
//
//            // for DUNS_FILE_TP : 01
//            if (!ZYPCommonFunc.hasValue(this.recentDate)) {
//                getRecentDate();
//            }
//
//            ssmParam.put("custFlg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("crit12Flg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("recentDt", this.recentDate);
//            ssmParam.put("crit21Flg", ZYPConstant.FLG_OFF_N);
//            // Add Start 2016/06/21 QC#10493
//            ssmParam.put("crit31Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit32Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit33Flg", ZYPConstant.FLG_OFF_N);
//            // Add End 2016/06/21 QC#10493
//            ssmParam.put("crit34Flg", ZYPConstant.FLG_OFF_N);
//        } else if (DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT.equals(fileTpCd)) {
//
//            // for DUNS_FILE_TP : 02
//            if (customerFlg) {
//                ssmParam.put("custFlg", ZYPConstant.FLG_ON_Y);
//            } else {
//                ssmParam.put("custFlg", ZYPConstant.FLG_OFF_N);
//            }
//            if (prospectFlg) {
//                ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
//            } else {
//                ssmParam.put("prosFlg", ZYPConstant.FLG_OFF_N);
//            }
//            ssmParam.put("crit12Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit21Flg", ZYPConstant.FLG_ON_Y);
//            // Add Start 2016/06/21 QC#10493
//            ssmParam.put("crit31Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit32Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit33Flg", ZYPConstant.FLG_OFF_N);
//            // Add End 2016/06/21 QC#10493
//            ssmParam.put("crit34Flg", ZYPConstant.FLG_OFF_N);
//        } else if (DUNS_FILE_TP.DNB_NAME_ADDRESS_PHONE_CHANGE_TXT.equals(fileTpCd)) {
//
//            // for DUNS_FILE_TP : 03 // TBD: DUNS_CRIT_CD(31-33)
//            if (customerFlg) {
//                ssmParam.put("custFlg", ZYPConstant.FLG_ON_Y);
//            } else {
//                ssmParam.put("custFlg", ZYPConstant.FLG_OFF_N);
//            }
//            if (prospectFlg) {
//                ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
//            } else {
//                ssmParam.put("prosFlg", ZYPConstant.FLG_OFF_N);
//            }
//            ssmParam.put("crit1232Flg", ZYPConstant.FLG_OFF_N);
//            ssmParam.put("crit21Flg", ZYPConstant.FLG_OFF_N);
//            // Add Start 2016/06/21 QC#10493
//            if (crit31Flg) {
//                ssmParam.put("crit31Flg", ZYPConstant.FLG_ON_Y);
//                ssmParam.put("crit31ValTxt", DUNS_CHNG_TP.UPDATED);
//                // updateFlg = true; // Del 2016/07/12 QC#10104
//            } else {
//                ssmParam.put("crit31Flg", ZYPConstant.FLG_OFF_N);
//            }
//            if (crit32Flg) {
//                ssmParam.put("crit32Flg", ZYPConstant.FLG_ON_Y);
//                ssmParam.put("crit32ValTxt", DUNS_CHNG_TP.UPDATED);
//                // updateFlg = true; // Del 2016/07/12 QC#10104
//            } else {
//                ssmParam.put("crit32Flg", ZYPConstant.FLG_OFF_N);
//            }
//            if (crit33Flg) {
//                ssmParam.put("crit33Flg", ZYPConstant.FLG_ON_Y);
//                ssmParam.put("crit33ValTxt", DUNS_CHNG_TP.UPDATED);
//                // updateFlg = true; // Del 2016/07/12 QC#10104
//            } else {
//                ssmParam.put("crit33Flg", ZYPConstant.FLG_OFF_N);
//            }
//            // Add End 2016/06/21 QC#10493
//            ssmParam.put("crit34Flg", ZYPConstant.FLG_ON_Y);
//            ssmParam.put("crit34ValTxt", critCd34ValTxt);
//        }
//
//        List<Map<String, Object>> extractSendDataList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getExtractSendData", ssmParam);
//
//        return extractSendDataList;
//    }
    // Del End 2016/07/20 QC#12021

    // Add Start 2016/07/20 QC#12021
    private Map<String, Object> getExtractSendDataParams(String dunsTrxHdrPk, String fileTpCd, List<String> dunsCritCdList, String critCd34ValTxt) {

        boolean customerFlg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_CUSTOMERS);
        boolean prospectFlg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_PROSPECTS);
        boolean crit31Flg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_RECORDS_WITH_NAME_CHANGE_SINCE_LAST_EXTRACT);
        boolean crit32Flg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_RECORDS_WITH_ADDRESS_CHANGE_SINCE_LAST_EXTRACT);
        boolean crit33Flg = dunsCritCdList.contains(DUNS_CRIT.INCLUDE_RECORDS_WITH_PHONE_NUM_CHANGE_SINCE_LAST_EXTRACT);

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("slsDt", this.salesDate);
        ssmParam.put("endDt", END_DATE);
        ssmParam.put("rgtnStsP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("filterLineAddr", "158 GAITH%");
        ssmParam.put("filterForAcctNm", getParamFilterAcctNm());
        ssmParam.put("filterForAcctDlrCd", getParamFilterAcctDlrCd());
        ssmParam.put("ctryCdUS", CTRY.UNITED_STATES_OF_AMERICA);
        ssmParam.put("filterforAddr", getParamFilterAddr());

        if (DUNS_FILE_TP.DNB_CLEANSED_ALL_YEARLY_TXT.equals(fileTpCd)) {

            // for DUNS_FILE_TP : 04
            ssmParam.put("custFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("crit12Flg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("crit21Flg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("updNameAddTelFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("crit34Flg", ZYPConstant.FLG_OFF_N);
        } else if (DUNS_FILE_TP.DNB_CLEANSED_NEW_PROSPECTS_TXT.equals(fileTpCd)) {

            // for DUNS_FILE_TP : 01
            if (!ZYPCommonFunc.hasValue(this.recentDate)) {
                getRecentDate();
            }

            ssmParam.put("custFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("crit12Flg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("recentDt", this.recentDate);
            ssmParam.put("crit21Flg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("updNameAddTelFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("crit34Flg", ZYPConstant.FLG_OFF_N);
        } else if (DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT.equals(fileTpCd)) {

            // for DUNS_FILE_TP : 02
            if (customerFlg) {
                ssmParam.put("custFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("custFlg", ZYPConstant.FLG_OFF_N);
            }
            if (prospectFlg) {
                ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("prosFlg", ZYPConstant.FLG_OFF_N);
            }
            ssmParam.put("crit12Flg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("crit21Flg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("updNameAddTelFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("crit34Flg", ZYPConstant.FLG_OFF_N);
        } else if (DUNS_FILE_TP.DNB_NAME_ADDRESS_PHONE_CHANGE_TXT.equals(fileTpCd)) {

            // for DUNS_FILE_TP : 03 // TBD: DUNS_CRIT_CD(31-33)
            if (customerFlg) {
                ssmParam.put("custFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("custFlg", ZYPConstant.FLG_OFF_N);
            }
            if (prospectFlg) {
                ssmParam.put("prosFlg", ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put("prosFlg", ZYPConstant.FLG_OFF_N);
            }
            ssmParam.put("crit1232Flg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("crit21Flg", ZYPConstant.FLG_OFF_N);
            if (crit31Flg || crit32Flg || crit33Flg) {
                ssmParam.put("updNameAddTelFlg", ZYPConstant.FLG_ON_Y);
            }
            if (crit31Flg && !crit32Flg && !crit33Flg) {
                ssmParam.put("updNameOnly", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit31ValTxt", DUNS_CHNG_TP.UPDATED);
            }
            if (!crit31Flg && crit32Flg && !crit33Flg) {
                ssmParam.put("updAddrOnly", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit32ValTxt", DUNS_CHNG_TP.UPDATED);
            }
            if (!crit31Flg && !crit32Flg && crit33Flg) {
                ssmParam.put("updTelOnly", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit33ValTxt", DUNS_CHNG_TP.UPDATED);
            }
            if (crit31Flg && crit32Flg && !crit33Flg) {
                ssmParam.put("updNameAddr", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit31ValTxt", DUNS_CHNG_TP.UPDATED);
                ssmParam.put("crit32ValTxt", DUNS_CHNG_TP.UPDATED);
            }
            if (crit31Flg && !crit32Flg && crit33Flg) {
                ssmParam.put("updNameTel", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit31ValTxt", DUNS_CHNG_TP.UPDATED);
                ssmParam.put("crit33ValTxt", DUNS_CHNG_TP.UPDATED);
            }
            if (!crit31Flg && crit32Flg && crit33Flg) {
                ssmParam.put("updAddrTel", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit32ValTxt", DUNS_CHNG_TP.UPDATED);
                ssmParam.put("crit33ValTxt", DUNS_CHNG_TP.UPDATED);
            }
            if (crit31Flg && crit32Flg && crit33Flg) {
                ssmParam.put("updNameAddrTel", ZYPConstant.FLG_ON_Y);
                ssmParam.put("crit31ValTxt", DUNS_CHNG_TP.UPDATED);
                ssmParam.put("crit32ValTxt", DUNS_CHNG_TP.UPDATED);
                ssmParam.put("crit33ValTxt", DUNS_CHNG_TP.UPDATED);
            }

            ssmParam.put("crit34Flg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("crit34ValTxt", critCd34ValTxt);
        }

        return ssmParam;
    }
    // Add End 2016/07/20 QC#12021

    private List<String> getFileTp(List<String> dunsCritCdList) {

        Set<String> dunsFileTpSet = new HashSet<String>();

        // dunsFileTpList has DUNS_CRIT_TP.CUSTMER
        boolean custmerFlg = false;

        // dunsFileTpList has DUNS_CRIT_TP.PROSPECT
        boolean prospectFlg = false;

        for (String dunsCritCd : dunsCritCdList) {
            // Mod Start 2016/06/20 QC#10255
            if (DUNS_CRIT.YEARLY.equals(dunsCritCd)) {
                dunsFileTpSet.add(DUNS_FILE_TP.DNB_CLEANSED_ALL_YEARLY_TXT);
                break;

            } else if (DUNS_CRIT.MONTHLY.equals(dunsCritCd)) {
                // do nothing
                continue;
                // Mod End 2016/06/20 QC#10255
            } else if (DUNS_CRIT.INCLUDE_CUSTOMERS.equals(dunsCritCd)) {
                custmerFlg = true;

            } else if (DUNS_CRIT.INCLUDE_PROSPECTS.equals(dunsCritCd)) {
                dunsFileTpSet.add(DUNS_FILE_TP.DNB_CLEANSED_NEW_PROSPECTS_TXT);
                prospectFlg = true;

            } else if (DUNS_CRIT.INCLUDE_RECORDS_NEVER_CLEANSED_BEFORE.equals(dunsCritCd)) {
                if (custmerFlg) {
                    dunsFileTpSet.add(DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT);
                }
                if (prospectFlg) {
                    dunsFileTpSet.add(DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT);
                }

            } else if (DUNS_CRIT.INCLUDE_RECORDS_WITH_NAME_CHANGE_SINCE_LAST_EXTRACT.equals(dunsCritCd) //
                    || DUNS_CRIT.INCLUDE_RECORDS_WITH_ADDRESS_CHANGE_SINCE_LAST_EXTRACT.equals(dunsCritCd)//
                    || DUNS_CRIT.INCLUDE_RECORDS_WITH_PHONE_NUM_CHANGE_SINCE_LAST_EXTRACT.equals(dunsCritCd)//
                    || DUNS_CRIT.INCLUDE_RECORDS_CLEANSED_BEFORE.equals(dunsCritCd)) {
                dunsFileTpSet.add(DUNS_FILE_TP.DNB_NAME_ADDRESS_PHONE_CHANGE_TXT);
            }
        }

        List<String> dataSet = new ArrayList<String>();
        for (String tmp : dunsFileTpSet) {
            dataSet.add(tmp);
        }
        Collections.reverse(dataSet);
        return dataSet;
    }

    /**
     * validation Check
     * @param dunsTrxHdrPk
     * @param dunsCritMap
     * @return boolean : true-Error
     */
    private boolean validCheck(String dunsTrxHdrPk, Map<String, String> dunsCritMap, boolean errorFlg) {

        String xtarctFlg = dunsCritMap.get("DUNS_CRIT_XTRCT_FLG");
        String critCd = dunsCritMap.get("DUNS_CRIT_CD");
        String critValTxt = dunsCritMap.get("DUNS_CRIT_VAL_TXT");

        if (!ZYPConstant.FLG_ON_Y.equals(xtarctFlg)) {
            addErrorMessageParam(dunsTrxHdrPk, ZZZM9026E, new String[] {DUNS_CRIT_CD });
            S21InfoLogOutput.println(ZZZM9026E, new String[] {DUNS_CRIT_CD });
            errorFlg = true;
        }

        if (DUNS_CRIT.INCLUDE_RECORDS_CLEANSED_BEFORE.equals(critCd) && ZYPCommonFunc.hasValue(critValTxt)) {
            if (isErrorDateFormat(critValTxt)) {
                addErrorMessageParam(dunsTrxHdrPk, NMAM8459E, new String[] {DUNS_CRIT_VAL_TXT });
                S21InfoLogOutput.println(NMAM8459E, new String[] {DUNS_CRIT_VAL_TXT });
                errorFlg = true;
            }
        }
        return errorFlg;
    }

    /**
     * Validation Check for XtractGrpID List
     * @param dunsTrxHdrPk
     * @param xtractGrpIdList
     * @param errorFlg
     * @return
     */
    private boolean listValidCheck(String dunsTrxHdrPk, List<String> xtractGrpIdList, boolean errorFlg) {

        int id0Count = 0;
        int id1Count = 0;

        for (String grpId : xtractGrpIdList) {
            if (GROUP_ID_ZERO.equals(grpId)) {
                id0Count++;
            } else if (GROUP_ID_ONE.equals(grpId)) {
                id1Count++;
            }
        }

        if (id0Count != 1) {
            addErrorMessageParam(dunsTrxHdrPk, NMAM8596E, new String[] {GROUP_ID_ZERO, String.valueOf(id0Count) });
            S21InfoLogOutput.println(NMAM8596E, new String[] {GROUP_ID_ZERO, String.valueOf(id0Count) });
            errorFlg = true;
        }
        if (id1Count < 1) {
            addErrorMessageParam(dunsTrxHdrPk, NMAM8596E, new String[] {GROUP_ID_ONE, String.valueOf(id1Count) });
            S21InfoLogOutput.println(NMAM8596E, new String[] {GROUP_ID_ONE, String.valueOf(id1Count) });
            errorFlg = true;
        }

        return errorFlg;
    }

    // Add Start 2018/07/02 QC#25339
    private DUNS_TRX_XTRCTTMsg toDunsTrxXtractTMsg(String dunsTrxHdrPk, String fileTpCd, ResultSet rs) {

        BigDecimal dunsTrxExtrctPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUNS_TRX_XTRCT_SQ);
        DUNS_TRX_XTRCTTMsg dunsTrxXtrctTmsg = new DUNS_TRX_XTRCTTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsTrxXtrctPk, dunsTrxExtrctPk);
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsTrxHdrPk, new BigDecimal(dunsTrxHdrPk));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsFileTpCd, fileTpCd);
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.cnctDunsAddr, getCnctAddr(rs));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.cntyNm, rs.getString("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.telNum, rs.getString("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.locNum, rs.getString("LOC_NUM"));

            BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
            if (ZYPCommonFunc.hasValue(dunsCustId)) {
                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsCustId, dunsCustId.toString());
            }
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsNum, rs.getString("DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));

            String sendCntStr = rs.getString("DUNS_SEND_CNT");

            if (ZYPCommonFunc.hasValue(sendCntStr)) {
                BigDecimal sendCnt = new BigDecimal(sendCntStr);
                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsSendCnt, BigDecimal.ONE.add(sendCnt));
            } else {
                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsSendCnt, BigDecimal.ONE);
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }
        
        return dunsTrxXtrctTmsg;
    }
    // Add End 2018/07/02 QC#25339

    // Del Start 2018/07/02 QC#25339
    // Mod Start 2016/07/20 QC#12021
//  private boolean insertDunsTrxXtract(String dunsTrxHdrPk, String fileTpCd, ResultSet rs) {
//
//            BigDecimal dunsTrxExtrctPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUNS_TRX_XTRCT_SQ);
//            DUNS_TRX_XTRCTTMsg dunsTrxXtrctTmsg = new DUNS_TRX_XTRCTTMsg();
//
//            try {
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsTrxXtrctPk, dunsTrxExtrctPk);
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsTrxHdrPk, new BigDecimal(dunsTrxHdrPk));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsFileTpCd, fileTpCd);
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.cnctDunsAddr, getCnctAddr(rs));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.ctyAddr, rs.getString("CTY_ADDR"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.cntyNm, rs.getString("CNTY_NM"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.stCd, rs.getString("ST_CD"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.postCd, rs.getString("POST_CD"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.telNum, rs.getString("TEL_NUM"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.locNum, rs.getString("LOC_NUM"));
//
//                BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
//                if (ZYPCommonFunc.hasValue(dunsCustId)) {
//                    ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsCustId, dunsCustId.toString());
//                }
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsNum, rs.getString("DUNS_NUM"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//                ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
//
//                // mod start 2016/10/17 CSA QC#15017
//                String sendCntStr = rs.getString("DUNS_SEND_CNT");
//                // mod end 2016/10/17 CSA QC#15017
//                if (ZYPCommonFunc.hasValue(sendCntStr)) {
//                    BigDecimal sendCnt = new BigDecimal(sendCntStr);
//                    ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsSendCnt, BigDecimal.ONE.add(sendCnt));
//                } else {
//                    ZYPEZDItemValueSetter.setValue(dunsTrxXtrctTmsg.dunsSendCnt, BigDecimal.ONE);
//                }
//            } catch (SQLException e) {
//                throw new S21AbendException(e);
//            }
//            S21FastTBLAccessor.insert(dunsTrxXtrctTmsg);
//
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dunsTrxXtrctTmsg.getReturnCode())) {
//                this.errorCount++;
//                // Del Start 2018/03/30 QC#24462
//                //super.rollback();
//                // Del End 2018/03/30 QC#24462
//                // Mod Start 2018/03/30 QC#24462
//                //addErrorMessageParam(dunsTrxHdrPk, NMAM0176E, new String[] {DUNS_TRX_XTRCT });
//                //S21InfoLogOutput.println(NMAM0176E, new String[] {DUNS_TRX_XTRCT });
//                addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {DUNS_TRX_XTRCT, //
//                        dunsTrxXtrctTmsg.dsAcctNum.getValue(), dunsTrxXtrctTmsg.locNum.getValue() });
//                S21InfoLogOutput.println(NMAM8678E, new String[] {DUNS_TRX_XTRCT });
//                // Mod End 2018/03/30 QC#24462
//                return false;
//            }
//
//            return true;
//        }
    // Mod End 2016/07/20 QC#12021
    // Del End 2018/07/02 QC#25339

    private boolean insertDunsTrxFile(String dunsTrxHdrPk, String fileTpCd, BigDecimal extractListSize) {

        BigDecimal dunsTrxFilePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DUNS_TRX_FILE_SQ);

        DUNS_TRX_FILETMsg dunsTrxFileTmsg = new DUNS_TRX_FILETMsg();
        ZYPEZDItemValueSetter.setValue(dunsTrxFileTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dunsTrxFileTmsg.dunsTrxFilePk, dunsTrxFilePk);
        ZYPEZDItemValueSetter.setValue(dunsTrxFileTmsg.dunsTrxHdrPk, new BigDecimal(dunsTrxHdrPk));
        ZYPEZDItemValueSetter.setValue(dunsTrxFileTmsg.dunsFileTpCd, fileTpCd);
        ZYPEZDItemValueSetter.setValue(dunsTrxFileTmsg.dunsFileNm, getFileTpTxt(fileTpCd));
        ZYPEZDItemValueSetter.setValue(dunsTrxFileTmsg.dunsFileLineNum, extractListSize);

        S21FastTBLAccessor.insert(dunsTrxFileTmsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dunsTrxFileTmsg.getReturnCode())) {
            this.errorCount++;
            // Del Start 2018/03/30 QC#24462
            //super.rollback();
            // Del End 2018/03/30 QC#24462
            // Mod Start 2018/03/30 QC#24462
            //addErrorMessageParam(dunsTrxHdrPk, NMAM0176E, new String[] {DUNS_TRX_FILE });
            //S21InfoLogOutput.println(NMAM0176E, new String[] {DUNS_TRX_FILE });
            addErrorMessageParam(dunsTrxHdrPk, NMAM8679E, new String[] {dunsTrxFileTmsg.dunsFileTpCd.getValue() });
            S21InfoLogOutput.println(NMAM8679E, new String[] {dunsTrxFileTmsg.dunsFileTpCd.getValue() });
            // Mod End 2018/03/30 QC#24462
            return false;
        }

        return true;
    }

   // Del Start 2018/07/02 QC#25339
//  // Mod Start 2016/07/20 QC#12021
////  private boolean callCustomerUpdateApi(String dunsTrxHdrPk, Map<String, Object> extractSendDataMap) {
//  private boolean callCustomerUpdateApi(String dunsTrxHdrPk, ResultSet rs) {
//
////      NMZC001001PMsg nmzc001001Pmsg = setupCustUpdateApiMsg(extractSendDataMap);
//      NMZC001001PMsg nmzc001001Pmsg = setupCustUpdateApiMsg(rs);
//
//      if (!callApiNMZC001001(nmzc001001Pmsg, dunsTrxHdrPk)) {
//          // Del Start 2018/03/30 QC#24462
//          //super.rollback();
//          // Del End 2018/03/30 QC#24462
//          return false;
//      }
//
//      return true;
//  }
//  // Mod End 2016/07/20 QC#12021
//
//  // Mod Start 2016/07/20 QC#12021
//  private NMZC001001PMsg setupCustUpdateApiMsg(ResultSet rs) {
//
//      NMZC001001PMsg nmzc001001Pmsg = new NMZC001001PMsg();
//
//      try {
//          if (DS_ACCT_TP.PROSPECT.equals(rs.getString("DS_ACCT_TP_CD"))) {
//              ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_PROS_UPD);
//          } else {
//              ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_CUST_UPD);
//          }
//
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.glblCmpyCd, this.glblCmpyCd);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.slsDt, this.salesDate);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//          // 2017/11/07 QC#22034 mod start
////          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctItrlFlg, ZYPConstant.FLG_ON_Y);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctItrlFlg, rs.getString("DS_ACCT_ITRL_FLG"));
//          // 2017/11/07 QC#22034 mod end
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctClsCd, rs.getString("DS_ACCT_CLS_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.coaChCd, rs.getString("COA_CH_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.coaAfflCd, rs.getString("COA_AFFL_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctLegalNm, rs.getString("DS_ACCT_LEGAL_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dbaNm, rs.getString("DBA_NM"));
//
//          if (DS_ACCT_TP.CUSTOMER.equals(rs.getString("DS_ACCT_TP_CD"))) {
//              ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.rgtnStsCd, rs.getString("RGTN_STS_CD"));
//          }
//
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.dsAcctUrl, rs.getString("DS_ACCT_URL"));
//
//          nmzc001001Pmsg.NMZC001002PMsg.setValidCount(1);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).xxPrinFlg, ZYPConstant.FLG_OFF_N);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).locNum, rs.getString("LOC_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).effFromDt, rs.getString("EFF_FROM_DT"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).effThruDt, rs.getString("EFF_THRU_DT"));
////           ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).actvFlg, ZYPConstant.FLG_OFF_N);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).actvFlg, ZYPConstant.FLG_ON_Y);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).ctyAddr, rs.getString("CTY_ADDR"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).cntyNm, rs.getString("CNTY_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).stCd, rs.getString("ST_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).postCd, rs.getString("POST_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).ctryCd, rs.getString("CTRY_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).locNm, rs.getString("DS_LOC_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).addlLocNm, rs.getString("ADDL_LOC_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).glnNum, rs.getBigDecimal("GLN_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).telNum, rs.getString("TEL_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).faxNum, rs.getString("FAX_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).provNm, rs.getString("PROV_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).geoCd, rs.getString("GEO_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsInsdCtyLimitFlg, rs.getString("DS_INSD_CTY_LIMIT_FLG"));
//
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsNum, rs.getString("DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).hqDunsNum, rs.getString("HQ_DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsPrntDunsNum, rs.getString("DS_PRNT_DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsLocEmpNum, rs.getBigDecimal("DS_LOC_EMP_NUM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsLocRevAmt, rs.getBigDecimal("DS_LOC_REV_AMT"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsLastUpdDunsDt, rs.getString("DS_LAST_UPD_DUNS_DT"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsCustSicDescTxt, rs.getString("DS_CUST_SIC_DESC_TXT"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsTradeStyleNm, rs.getString("DUNS_TRADE_STYLE_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsActvCd, rs.getString("DUNS_ACTV_CD"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsLineAddr, rs.getString("DUNS_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsBizNm, rs.getString("DUNS_BIZ_NM"));
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsLastRcvDunsTxt, getDBIdentifier());
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dsLastRcvDunsDt, this.salesDate);
//
//          BigDecimal count = rs.getBigDecimal("DUNS_SEND_CNT");
//          if (ZYPCommonFunc.hasValue(count)) {
//              ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsSendCnt, BigDecimal.ONE.add(count));
//          } else {
//              ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).dunsSendCnt, BigDecimal.ONE);
//          }
//
//          ZYPEZDItemValueSetter.setValue(nmzc001001Pmsg.NMZC001002PMsg.no(0).xxLocXrefInfoFlg, ZYPConstant.FLG_OFF_N);
//      } catch (SQLException e) {
//          throw new S21AbendException(e);
//      }
//
//      return nmzc001001Pmsg;
//  }
//  // Mod End 2016/07/20 QC#12021
//
//  private boolean callApiNMZC001001(NMZC001001PMsg pMsg, String dunsTrxHdrPk) {
//      NMZC001001 api = new NMZC001001();
//      api.execute(pMsg, ONBATCH_TYPE.BATCH);
//
//      List<S21ApiMessage> errList = S21ApiUtil.getXxMsgList(pMsg);
//
//      boolean errExist = false;
//      if (!errList.isEmpty()) {
//          for (S21ApiMessage msg : errList) {
//              String msgId = msg.getXxMsgid();
//              String[] msgPrm = msg.getXxMsgPrmArray();
//
//              if (msgId.endsWith("E")) {
//                  errExist = true;
//                  // Mod Start 2018/03/30 QC#24462
//                  //addErrorMessageParam(dunsTrxHdrPk, msgId, msgPrm);
//                  addErrorMessageParamWithAccountInfo(dunsTrxHdrPk, msgId, msgPrm, //
//                          pMsg.dsAcctNum.getValue(), pMsg.NMZC001002PMsg.no(0).locNum.getValue());
//                  // Mod End 2018/03/30 QC#24462
//                  S21InfoLogOutput.println(msgId, msgPrm);
//              }
//          }
//      } else {
//          for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
//              NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
//              errList = S21ApiUtil.getXxMsgList(linePrm);
//              for (S21ApiMessage msg : errList) {
//                  String msgId = msg.getXxMsgid();
//                  String[] msgPrm = msg.getXxMsgPrmArray();
//
//                  if (msgId.endsWith("E")) {
//                      errExist = true;
//                      // Mod Start 2018/03/30 QC#24462
//                      //addErrorMessageParam(dunsTrxHdrPk, msgId, msgPrm);
//                      addErrorMessageParamWithAccountInfo(dunsTrxHdrPk, msgId, msgPrm, //
//                              pMsg.dsAcctNum.getValue(), pMsg.NMZC001002PMsg.no(0).locNum.getValue());
//                      // Mod End 2018/03/30 QC#24462
//                      S21InfoLogOutput.println(msgId, msgPrm);
//                  }
//              }
//          }
//      }
//      if (errExist) {
//          this.errorCount++;
//          return false;
//      } else {
//          return true;
//      }
//  }
    // Del End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    /**
     * Create Interface TMsg
     * @param dunsTrxHdrPk
     * @param fileTpCd
     * @param extractSendDataMap
     * @return boolean Error- False
     */
    private boolean toInterfaceTmsg(
            String dunsTrxHdrPk, String fileTpCd, ResultSet rs,
            List<NMAI4900_01TMsg> nMai4900_01List,
            List<NMAI4900_02TMsg> nMai4900_02List,
            List<NMAI4900_03TMsg> nMai4900_03List,
            List<NMAI4900_04TMsg> nMai4900_04List,
            DunsDataBucket bucket) {

        if (DUNS_FILE_TP.DNB_CLEANSED_NEW_PROSPECTS_TXT.equals(fileTpCd)) {
            NMAI4900_01TMsg tmsg = to01TMsg(rs);
            nMai4900_01List.add(tmsg);
            bucket.setNMai4900_01(tmsg);
        } else if (DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT.equals(fileTpCd)) {
            NMAI4900_02TMsg tmsg = to02TMsg(rs);
            nMai4900_02List.add(tmsg);
            bucket.setNMai4900_02(tmsg);
        } else if (DUNS_FILE_TP.DNB_NAME_ADDRESS_PHONE_CHANGE_TXT.equals(fileTpCd)) {
            NMAI4900_03TMsg tmsg = to03TMsg(rs);
            nMai4900_03List.add(tmsg);
            bucket.setNMai4900_03(tmsg);
        } else if (DUNS_FILE_TP.DNB_CLEANSED_ALL_YEARLY_TXT.equals(fileTpCd)) {
            NMAI4900_04TMsg tmsg = to04TMsg(rs);
            nMai4900_04List.add(tmsg);
            bucket.setNMai4900_04(tmsg);
        } else {
            S21InfoLogOutput.println(String.format("Illegal duns file tp[%s].", fileTpCd));
            return false;
        }

        return true;
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    /**
     * Create Interface NMAI4900_01 TMsg 
     * @param extractSendDataMap
     * @return TMsg
     */
    private NMAI4900_01TMsg to01TMsg(ResultSet rs) {

        NMAI4900_01TMsg tMsg = new NMAI4900_01TMsg();

        try {
            this.nmai490001transactionId = getTransactionId(this.nmai490001transactionId, this.nmai490001interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490001interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490001transactionId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490001segmentId));
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, this.nmai490001seqNumberId);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
            ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));

            BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
            if (ZYPCommonFunc.hasValue(dunsCustId)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
            }
            ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }
        this.nmai490001seqNumberId = this.nmai490001seqNumberId.add(BigDecimal.ONE);

        return tMsg;

    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    /**
     * Create Interface NMAI4900_02 TMsg 
     * @param extractSendDataMap
     * @return TMsg
     */
    private NMAI4900_02TMsg to02TMsg(ResultSet rs) {

        NMAI4900_02TMsg tMsg = new NMAI4900_02TMsg();

        try {
            this.nmai490002transactionId = getTransactionId(this.nmai490002transactionId, this.nmai490002interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490002interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490002transactionId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490002segmentId));
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, this.nmai490002seqNumberId);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
            ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));

            BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
            if (ZYPCommonFunc.hasValue(dunsCustId)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
            }
            ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }

        this.nmai490002seqNumberId = this.nmai490002seqNumberId.add(BigDecimal.ONE);
        return tMsg;
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    /**
     * Create Interface NMAI4900_03 TMsg 
     * @param extractSendDataMap
     * @return TMsg
     */
    private NMAI4900_03TMsg to03TMsg(ResultSet rs) {

        NMAI4900_03TMsg tMsg = new NMAI4900_03TMsg();

        try {
            this.nmai490003transactionId = getTransactionId(this.nmai490003transactionId, this.nmai490003interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490003interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490003transactionId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490003segmentId));
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, this.nmai490003seqNumberId);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
            ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));

            BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
            if (ZYPCommonFunc.hasValue(dunsCustId)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
            }
            ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }

        this.nmai490003seqNumberId = this.nmai490003seqNumberId.add(BigDecimal.ONE);
        return tMsg;
    }
    // Add End 2018/07/02 QC#25339

    // Add Start 2018/07/02 QC#25339
    /**
     * Create Interface NMAI4900_04 TMsg 
     * @param extractSendDataMap
     * @return TMsg
     */
    private NMAI4900_04TMsg to04TMsg(ResultSet rs) {

        NMAI4900_04TMsg tMsg = new NMAI4900_04TMsg();

        try {
            this.nmai490004transactionId = getTransactionId(this.nmai490004transactionId, this.nmai490004interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490004interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490004transactionId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490004segmentId));
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, this.nmai490004seqNumberId);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
            ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));

            BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
            if (ZYPCommonFunc.hasValue(dunsCustId)) {
                ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
            }
            ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }

        this.nmai490004seqNumberId = this.nmai490004seqNumberId.add(BigDecimal.ONE);
        return tMsg;

    }
    // Add End 2018/07/02 QC#25339

    // Del Start 2018/07/02 QC#25339
//  // Mod Start 2016/07/20 QC#12021
//  /**
//   * Insert Interface
//   * @param dunsTrxHdrPk
//   * @param fileTpCd
//   * @param extractSendDataMap
//   * @return boolean Error- False
//   */
////  private boolean insertInterface(String dunsTrxHdrPk, String fileTpCd, Map<String, Object> extractSendDataMap) {
//  private boolean insertInterface(String dunsTrxHdrPk, String fileTpCd, ResultSet rs) {
//
//      // Add Start 2018/03/30 QC#24462
//      String dsAcctNum = null;
//      String locNum = null;
//
//      try {
//          dsAcctNum = rs.getString("DS_ACCT_NUM");
//          locNum = rs.getString("LOC_NUM");
//      } catch (SQLException e) {
//          throw new S21AbendException(e);
//      }
//      // Add End 2018/03/30 QC#24462
//
//      if (DUNS_FILE_TP.DNB_CLEANSED_NEW_PROSPECTS_TXT.equals(fileTpCd)) {
////          if (!insert01(extractSendDataMap)) {
//          if (!insert01(rs)) {
//              // Mod Start 2018/03/30 QC#24462
//              //addErrorMessageParam(dunsTrxHdrPk, NMAM0176E, new String[] {NMAI4900_01 });
//              //S21InfoLogOutput.println(NMAM0176E, new String[] {NMAI4900_01 });
//              addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_01, dsAcctNum, locNum });
//              S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_01, dsAcctNum, locNum });
//              // Mod End 2018/03/30 QC#24462
//              return false;
//          }
//
//      } else if (DUNS_FILE_TP.DNB_NEW_CUSTOMERS_AND_PROSPECTS_TXT.equals(fileTpCd)) {
////          if (!insert02(extractSendDataMap)) {
//          if (!insert02(rs)) {
//              // Mod Start 2018/03/30 QC#24462
//              //addErrorMessageParam(dunsTrxHdrPk, NMAM0176E, new String[] {NMAI4900_02 });
//              //S21InfoLogOutput.println(NMAM0176E, new String[] {NMAI4900_02 });
//              addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_02, dsAcctNum, locNum });
//              S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_02, dsAcctNum, locNum });
//              // Mod End 2018/03/30 QC#24462
//              return false;
//          }
//
//      } else if (DUNS_FILE_TP.DNB_NAME_ADDRESS_PHONE_CHANGE_TXT.equals(fileTpCd)) {
////          if (!insert03(extractSendDataMap)) {
//          if (!insert03(rs)) {
//              // Mod Start 2018/03/30 QC#24462
//              //addErrorMessageParam(dunsTrxHdrPk, NMAM0176E, new String[] {NMAI4900_03 });
//              //S21InfoLogOutput.println(NMAM0176E, new String[] {NMAI4900_03 });
//              addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_03, dsAcctNum, locNum });
//              S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_03, dsAcctNum, locNum });
//              // Mod End 2018/03/30 QC#24462
//              return false;
//
//          }
//
//      } else if (DUNS_FILE_TP.DNB_CLEANSED_ALL_YEARLY_TXT.equals(fileTpCd)) {
////          if (!insert04(extractSendDataMap)) {
//          if (!insert04(rs)) {
//              // Mod Start 2018/03/30 QC#24462
//              //addErrorMessageParam(dunsTrxHdrPk, NMAM0176E, new String[] {NMAI4900_04 });
//              //S21InfoLogOutput.println(NMAM0176E, new String[] {NMAI4900_04 });
//              addErrorMessageParam(dunsTrxHdrPk, NMAM8678E, new String[] {NMAI4900_04, dsAcctNum, locNum });
//              S21InfoLogOutput.println(NMAM8678E, new String[] {NMAI4900_04, dsAcctNum, locNum });
//              // Mod End 2018/03/30 QC#24462
//              return false;
//
//          }
//      } else {
//          return false;
//      }
//      return true;
//  }
//  // Mod End 2016/07/20 QC#12021
//
//  // Mod Start 2016/07/20 QC#12021
//  /**
//   * Insert Interface NMAI4900_01
//   * @param extractSendDataMap
//   * @return boolean False:error
//   */
//  private boolean insert01(ResultSet rs) {
//
//      NMAI4900_01TMsg tMsg = new NMAI4900_01TMsg();
//
//      try {
//          // Mod Start 2016/08/31 QC#13861
//          this.nmai490001transactionId = getTransactionId(this.nmai490001transactionId, this.nmai490001interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490001interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490001transactionId);
//          // Mod End 2016/08/31 QC#13861
//          ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490001segmentId));
//          ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
//          ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.valueOf(this.nmai490001seqNumberId));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
//          ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));
//
//          BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
//          if (ZYPCommonFunc.hasValue(dunsCustId)) {
//              ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
//          }
//          ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
//      } catch (SQLException e) {
//          throw new S21AbendException(e);
//      }
//
//      S21FastTBLAccessor.insert(tMsg);
//
//      if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//          this.errorCount++;
//          // Del Start 2018/03/30 QC#24462
//          //super.rollback();
//          // Del End 2018/03/30 QC#24462
//          return false;
//      }
//
//      // this.nmai490001segmentId++; // Del 2016/08/31 QC#13861
//      this.nmai490001seqNumberId++;
//      return true;
//  }
//  // Mod End 2016/07/20 QC#12021
//
//  // Mod Start 2016/07/20 QC#12021
//  /**
//   * Insert Interface NMAI4900_02
//   * @param extractSendDataMap
//   * @return boolean False:error
//   */
//  private boolean insert02(ResultSet rs) {
//
//      NMAI4900_02TMsg tMsg = new NMAI4900_02TMsg();
//
//      try {
//          // Mod Start 2016/08/31 QC#13861
//          this.nmai490002transactionId = getTransactionId(this.nmai490002transactionId, this.nmai490002interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490002interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490002transactionId);
//          // Mod End 2016/08/31 QC#13861
//          ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490002segmentId));
//          ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
//          ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.valueOf(this.nmai490002seqNumberId));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
//          ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));
//
//          BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
//          if (ZYPCommonFunc.hasValue(dunsCustId)) {
//              ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
//          }
//          ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
//      } catch (SQLException e) {
//          throw new S21AbendException(e);
//      }
//
//      S21FastTBLAccessor.insert(tMsg);
//
//      if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//          this.errorCount++;
//          // Del Start 2018/03/30 QC#24462
//          //super.rollback();
//          // Del End 2018/03/30 QC#24462
//          return false;
//      }
//
//      // this.nmai490002segmentId++; // Del 2016/08/31 QC#13861
//      this.nmai490002seqNumberId++;
//      return true;
//  }
//  // Mod Start 2016/07/20 QC#12021
//
//  // Mod Start 2016/07/20 QC#12021
//  /**
//   * Insert Interface NMAI4900_03
//   * @param extractSendDataMap
//   * @return boolean False:error
//   */
//  private boolean insert03(ResultSet rs) {
//
//      NMAI4900_03TMsg tMsg = new NMAI4900_03TMsg();
//
//      try {
//          // Mod Start 2016/08/31 QC#13861
//          this.nmai490003transactionId = getTransactionId(this.nmai490003transactionId, this.nmai490003interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490003interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490003transactionId);
//          // Mod End 2016/08/31 QC#13861
//          ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490003segmentId));
//          ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
//          ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.valueOf(this.nmai490003seqNumberId));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
//          ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));
//
//          BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
//          if (ZYPCommonFunc.hasValue(dunsCustId)) {
//              ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
//          }
//          ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
//      } catch (SQLException e) {
//          throw new S21AbendException(e);
//      }
//
//      S21FastTBLAccessor.insert(tMsg);
//
//      if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//          this.errorCount++;
//          // Del Start 2018/03/30 QC#24462
//          //super.rollback();
//          // Del End 2018/03/30 QC#24462
//          return false;
//      }
//
//      // this.nmai490003segmentId++; // Del 2016/08/31 QC#13861
//      this.nmai490003seqNumberId++;
//      return true;
//  }
//  // Mod End 2016/07/20 QC#12021
//
//  // Mod Start 2016/07/20 QC#12021
//  /**
//   * Insert Interface NMAI4900_04
//   * @param extractSendDataMap
//   * @return boolean False:error
//   */
//  private boolean insert04(ResultSet rs) {
//
//      NMAI4900_04TMsg tMsg = new NMAI4900_04TMsg();
//
//      try {
//          // Mod Start 2016/08/31 QC#13861
//          this.nmai490004transactionId = getTransactionId(this.nmai490004transactionId, this.nmai490004interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.nmai490004interfaceId);
//          ZYPEZDItemValueSetter.setValue(tMsg.transactionId, this.nmai490004transactionId);
//          // Mod End 2016/08/31 QC#13861
//          ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.valueOf(this.nmai490004segmentId));
//          ZYPEZDItemValueSetter.setValue(tMsg.unitId, this.nmai4900UnitId);
//          ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.valueOf(this.nmai490004seqNumberId));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cnctDunsAddr, getCnctAddr(rs));
//          ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
//          ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString("LOC_NUM"));
//
//          BigDecimal dunsCustId = rs.getBigDecimal("DS_ACCT_PK");
//          if (ZYPCommonFunc.hasValue(dunsCustId)) {
//              ZYPEZDItemValueSetter.setValue(tMsg.dunsCustId, dunsCustId.toString());
//          }
//          ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
//          ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
//      } catch (SQLException e) {
//          throw new S21AbendException(e);
//      }
//
//      S21FastTBLAccessor.insert(tMsg);
//
//      if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//          this.errorCount++;
//          // Del Start 2018/03/30 QC#24462
//          //super.rollback();
//          // Del End 2018/03/30 QC#24462
//          return false;
//      }
//
//      // this.nmai490004segmentId++; // Del 2016/08/31 QC#13861
//      this.nmai490004seqNumberId++;
//      return true;
//  }
//  // Mod End 2016/07/20 QC#12021
    // Del End 2018/07/02 QC#25339

    private void registDunsHdr(String dunsTrxHdrPk) {

        DUNS_TRX_HDRTMsg hdrTmsg = new DUNS_TRX_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(hdrTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hdrTmsg.dunsTrxHdrPk, new BigDecimal(dunsTrxHdrPk));
        hdrTmsg = (DUNS_TRX_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(hdrTmsg);

        ZYPEZDItemValueSetter.setValue(hdrTmsg.dunsProcStsCd, DUNS_PROC_STS.DONE);
        ZYPEZDItemValueSetter.setValue(hdrTmsg.dunsProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));

        EZDTBLAccessor.update(hdrTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrTmsg.getReturnCode())) {
            throw new S21AbendException(NMAM0177E, new String[] {DUNS_TRX_HDR });
        }

        commit();
        this.successCount += this.notCommitCount;
        this.notCommitCount = 0;

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
        groupTo.setMailKey1(MAIL_KEY_TO_ERROR);
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, MAIL_KEY_TO_ERROR });
        }

        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ERROR);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_ERROR });
        }

        // Set Message
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_ID, BATCH_PROGRAM_ID);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_NAME, BATCH_PROGRAM_NAME);
        maiTemplate.setTemplateParameter(MAIL_FIELD_BATCH_PROC_LOG_ID, super.getBatchProcessLogID());
        maiTemplate.setTemplateParameter(MAIL_FIELD_ERR_MSG, this.errMsg.toString());

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    private void sendMail() {
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
            throw new S21AbendException(NMAM8028E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, MAIL_KEY_TO_NORMAL });
        }

        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_NORMAL);
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(NMAM8031E, new String[] {MAIL_TEMPLATE_NORMAL });
        }

        // Set Message
        maiTemplate.setTemplateParameter(MAIL_FIELD_SUC_MSG, this.sucMsg.toString());

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    // Dell Start 2016/07/20 QC#12021
//    private String getCnctAddr(Map<String, Object> extractSendDataMap) {
//        StringBuilder sb = new StringBuilder();
//
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("FIRST_LINE_ADDR"))) {
//            sb.append((String) extractSendDataMap.get("FIRST_LINE_ADDR"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("SCD_LINE_ADDR"))) {
//            sb.append((String) extractSendDataMap.get("SCD_LINE_ADDR"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("THIRD_LINE_ADDR"))) {
//            sb.append((String) extractSendDataMap.get("THIRD_LINE_ADDR"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("FRTH_LINE_ADDR"))) {
//            sb.append((String) extractSendDataMap.get("FRTH_LINE_ADDR"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("CTY_ADDR"))) {
//            sb.append((String) extractSendDataMap.get("CTY_ADDR"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("CNTY_NM"))) {
//            sb.append((String) extractSendDataMap.get("CNTY_NM"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("ST_CD"))) {
//            sb.append((String) extractSendDataMap.get("ST_CD"));
//            sb.append(COMMA).append(SPACE);
//        }
//        if (ZYPCommonFunc.hasValue((String) extractSendDataMap.get("POST_CD"))) {
//            sb.append((String) extractSendDataMap.get("POST_CD"));
//            sb.append(COMMA).append(SPACE);
//        }
//        sb.append("United States");
//
//        return sb.toString();
//    }
    // Dell End 2016/07/20 QC#12021

    // Add Start 2016/07/20 QC#12021
    private String getCnctAddr(ResultSet rs) {
        StringBuilder sb = new StringBuilder();

        try {
            if (ZYPCommonFunc.hasValue(rs.getString("FIRST_LINE_ADDR"))) {
                sb.append(rs.getString("FIRST_LINE_ADDR"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("SCD_LINE_ADDR"))) {
                sb.append(rs.getString("SCD_LINE_ADDR"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("THIRD_LINE_ADDR"))) {
                sb.append(rs.getString("THIRD_LINE_ADDR"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("FRTH_LINE_ADDR"))) {
                sb.append(rs.getString("FRTH_LINE_ADDR"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("CTY_ADDR"))) {
                sb.append(rs.getString("CTY_ADDR"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("CNTY_NM"))) {
                sb.append(rs.getString("CNTY_NM"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("ST_CD"))) {
                sb.append(rs.getString("ST_CD"));
                sb.append(COMMA).append(SPACE);
            }
            if (ZYPCommonFunc.hasValue(rs.getString("POST_CD"))) {
                sb.append(rs.getString("POST_CD"));
                sb.append(COMMA).append(SPACE);
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }

        sb.append("United States");

        return sb.toString();
    }
    // Add End 2016/07/20 QC#12021

// Del Start 2016/08/31 QC#13861
//   /**
//    * Reset Interface SegmentNumber.
//    */
//    private void resetSegmentNum() {
//        this.nmai490001segmentId = 1;
//        this.nmai490002segmentId = 1;
//        this.nmai490003segmentId = 1;
//        this.nmai490004segmentId = 1;
//    }
// Del End 2016/08/31 QC#13861

    /**
     * Get Duns File Type Text
     * @param fileTpCd
     * @return String dunsFileTpDescTxt
     */
    private String getFileTpTxt(String fileTpCd) {
        return ZYPCodeDataUtil.getName("DUNS_FILE_TP", this.glblCmpyCd, fileTpCd);
    }

    private List<String> getParamFilterAcctNm() {

        List<String> filterList = new ArrayList<String>();
        filterList.add("%CPC");
        filterList.add("CANON%");
        filterList.add("% FM");
        filterList.add("%DUPLICATE%");
        filterList.add("% OM ONLY");
        filterList.add("% SERVICE ONLY");
        filterList.add("CFS %");

        return filterList;
    }

    private List<String> getParamFilterAcctDlrCd() {

        List<String> filterList = new ArrayList<String>();
        filterList.add(DS_ACCT_DLR._1000);
        filterList.add(DS_ACCT_DLR._1010);
        filterList.add(DS_ACCT_DLR._1020);
        filterList.add(DS_ACCT_DLR._2000);
        filterList.add(DS_ACCT_DLR._3000);
        return filterList;
    }

    private List<String> getParamFilterAddr() {

        List<String> filterList = new ArrayList<String>();
        filterList.add("%DUPLICATE%");
        filterList.add("%DzUPLICATE%");

        return filterList;
    }

    private void getRecentDate() {

        // Search Target Data Criteria
        Map<String, Object> ssmParamForCrit12 = new HashMap<String, Object>();
        ssmParamForCrit12.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamForCrit12.put("dunsProcTpExtraDNB", DUNS_PROC_TP.EXTRACT_FOR_DNB);
        ssmParamForCrit12.put("dunsProcStsRegist", DUNS_PROC_STS.DONE);
        this.recentDate = (String) ssmBatchClient.queryObject("getRecentDate", ssmParamForCrit12);

        if (!ZYPCommonFunc.hasValue(this.recentDate)) {
            this.recentDate = getRecentDateBySlsDt();
        }

    }

    private String getRecentDateBySlsDt() {
        String recentDtStr = this.salesDate;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            java.util.Date recentDt = sdf.parse(recentDtStr);

            Calendar cal = Calendar.getInstance();
            cal.setTime(recentDt);
            cal.add(Calendar.DATE, -30);

            recentDtStr = sdf.format(cal.getTime());
        } catch (ParseException e) {
            return null;
        }

        return recentDtStr;
    }

    private String getDBIdentifier() {
        String identifier = this.salesDate;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("'DNB'-dd-MMM-yyyy");

            java.util.Date identifierDt = sdf.parse(identifier);
            identifier = sdf1.format(identifierDt);
        } catch (ParseException e) {
            return null;
        }

        return identifier;
    }

    private boolean isErrorDateFormat(String critValTxt) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            format.setLenient(false);
            format.parse(critValTxt);
        } catch (java.text.ParseException e) {
            return true;
        }
        return false;
    }

    /**
     * add ErrorMessage for Error Information
     * @param dunsTrxHdrPk
     * @param msgID
     * @param param
     */
    private void addErrorMessageParam(String dunsTrxHdrPk, String msgID, String[] param) {

        if (this.errMsg == null) {
            this.errMsg = new StringBuilder();
        } else {
            this.errMsg.append(SPACE).append(SPACE).append(SPACE).append(SPACE);
        }

        this.errMsg.append(MAIL_DUNS_TRX_HDR);
        this.errMsg.append(SPACE).append(EQUAL).append(SPACE);
        this.errMsg.append(dunsTrxHdrPk);
        this.errMsg.append(SPACE).append(COMMA).append(SPACE);
        this.errMsg.append(msgID);
        this.errMsg.append(SPACE).append(S21MessageFunc.clspGetMessage(msgID, param));
        this.errMsg.append(CRLF);
    }

    // Del Start 2018/07/03 QC#25339
    // Add Start 2018/03/30 QC#24462
    /**
     * add ErrorMessage for Error Information with account information
     * @param dunsTrxHdrPk
     * @param msgID
     * @param param
     */
//    private void addErrorMessageParamWithAccountInfo(String dunsTrxHdrPk, String msgID, String[] param, //
//            String acctNum, String locNum) {

//        if (this.errMsg == null) {
//            this.errMsg = new StringBuilder();
//        } else {
//            this.errMsg.append(SPACE).append(SPACE).append(SPACE).append(SPACE);
//        }

//        this.errMsg.append(MAIL_DUNS_TRX_HDR);
//        this.errMsg.append(SPACE).append(EQUAL).append(SPACE);
//        this.errMsg.append(dunsTrxHdrPk);
//        this.errMsg.append(SPACE).append(COMMA).append(SPACE);

//        this.errMsg.append(MAIL_DS_ACCT_NUM);
//        this.errMsg.append(SPACE).append(EQUAL).append(SPACE);
//        this.errMsg.append(changeString(acctNum));
//        this.errMsg.append(SPACE).append(COMMA).append(SPACE);

//        this.errMsg.append(MAIL_LOC_NUM);
//        this.errMsg.append(SPACE).append(EQUAL).append(SPACE);
//        this.errMsg.append(changeString(locNum));
//        this.errMsg.append(SPACE).append(COMMA).append(SPACE);

//        this.errMsg.append(msgID);
//        this.errMsg.append(SPACE).append(S21MessageFunc.clspGetMessage(msgID, param));
//        this.errMsg.append(CRLF);
//    }

    /**
     * Change String
     * @param strArg String
     * @return String
     */
//    private String changeString(String strArg) {
//        if (strArg == null) {
//            return "";
//        } else {
//            return strArg;
//        }
//    }
    // Add End 2018/03/30 QC#24462
    // Del End 2018/07/03 QC#25339

    /**
     * add ErrorMessage for Error Information
     * @param dunsTrxHdrPk
     * @param msgID
     * @param param
     */
    private void addMessageParam(String dunsTrxHdrPk, String fileName, int numOfCount) {

        if (this.sucMsg == null) {
            this.sucMsg = new StringBuilder();
        }

        this.sucMsg.append(fileName);

        int fileNmInterval = 1;
        if (MAX_FILE_NAME_LENGTH > fileName.length()) {
            fileNmInterval = MAX_FILE_NAME_LENGTH - fileName.length();
        }
        for (int i = 0; i < fileNmInterval; i++) {
            this.sucMsg.append(SPACE);
        }
        this.sucMsg.append(numOfCount);
        this.sucMsg.append(SPACE).append("records");
        this.sucMsg.append(CRLF);
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);

    }

    // Add Start 2016/08/31 QC#13861
    private String getInterfaceIdForDunsFileType(String dunsFileTpCd) {
        DUNS_FILE_TPTMsg dunsFileTpTMsg = new DUNS_FILE_TPTMsg();
        dunsFileTpTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        dunsFileTpTMsg.dunsFileTpCd.setValue(dunsFileTpCd);

        dunsFileTpTMsg = (DUNS_FILE_TPTMsg) S21CacheTBLAccessor.findByKey(dunsFileTpTMsg);

        if (dunsFileTpTMsg == null || !ZYPCommonFunc.hasValue(dunsFileTpTMsg.intfcId)) {
            return null;
        } else {
            return dunsFileTpTMsg.intfcId.getValue();
        }
    }
    // Add End 2016/08/31 QC#13861
    private BigDecimal getTransactionId(BigDecimal currentTransactionId, String interfaceId) {

        if (ZYPCommonFunc.hasValue(currentTransactionId)) {
            return currentTransactionId;
        } else {
            return tblAccessor.createIntegrationRecord(interfaceId);
        }

    }

}
