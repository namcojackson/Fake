/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB013001;

import static com.canon.cusa.s21.batch.NSA.NSAB013001.constant.NSAB013001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.NSAI0010_01TMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
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
 * Batch Fax
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/07/2015   Hitachi         T.Tomita        Create          N/A
 * </pre>
 */
public class NSAB013001 extends S21BatchMain {

    /** error Message */
    private String errMsg = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Process Time Stamp */
    private String procTs;

    /** Sales Date */
    private String salesDate;

    /** Batch Fax Days */
    private int batFaxDays;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** Error Key Map */
    private Map<String, String> errKeyMap;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get Batch Fax Days
        BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue(KEY_NSAB0130_BAT_FAX_DAYS, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(numConstVal)) {
            this.batFaxDays = (int) Math.ceil(numConstVal.doubleValue());
        } else {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BAT_FAX_DAYS });
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = trxAccess.getNextTransactionId();
        this.unitId = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount   = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        this.errKeyMap = new HashMap<String, String>();
    }

    @Override
    protected void mainRoutine() {
        insertInterfaceData("getInputData", setSearchCondition());

        if (this.normalCount > 0) {
            // Insert Transaction data
            trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
        }

        if (this.errorCount > 0) {
            insertErrorData();
            sendMail();
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB013001().executeBatch(NSAB013001.class.getSimpleName());
    }

    private void insertInterfaceData(String sqlId, Map<String, Object> paramMap) {
        List<NSAI0010_01TMsg> inTMsgList = new ArrayList<NSAI0010_01TMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;

            // Insert Interface Data
            while (rs.next()) {
                if (this.unitId == 0) {
                    setErrKeyMap(rs);
                }

                this.unitId++;
                inTMsgList.add(setCreateValue(rs, this.unitId));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList, rs);
                    inTMsgList = new ArrayList<NSAI0010_01TMsg>();
                    this.normalCount += commitCount;
                    commitCount = 0;
                }
            }

            if (this.unitId != this.normalCount + this.errorCount) {
                commitCount = insertInterfaceData(inTMsgList, rs);
                this.normalCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertInterfaceData(List<NSAI0010_01TMsg> inMsgLst, ResultSet rs) {
        NSAI0010_01TMsg[] inMsgArray;
        inMsgArray = new NSAI0010_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.errorCount += inMsgArray.length - insertCount;
            this.errMsg = S21MessageFunc.clspGetMessage(INS_ERR_MSD_ID, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    private Map<String, Object> setSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("salesDate", this.salesDate);
        inParam.put("batFaxDays", this.batFaxDays);
        inParam.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        inParam.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        inParam.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        inParam.put("dsMtrReadTpInit", DS_MTR_READ_TP.INITIAL_METER_READING);
        return inParam;
    }

    private void insertErrorData() {
        List<SVC_BAT_ERR_LOGTMsg> inTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();

        inTMsgList.add(setCreateErrorValue());

        SVC_BAT_ERR_LOGTMsg[] inMsgArray;
        inMsgArray = new SVC_BAT_ERR_LOGTMsg[inTMsgList.size()];
        S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
    }

    private NSAI0010_01TMsg setCreateValue(ResultSet rs, int count) {
        NSAI0010_01TMsg inParam = new NSAI0010_01TMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.interfaceId, this.intfcId);
            ZYPEZDItemValueSetter.setValue(inParam.transactionId, this.trxId);
            ZYPEZDItemValueSetter.setValue(inParam.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(inParam.unitId, new BigDecimal(count));
            ZYPEZDItemValueSetter.setValue(inParam.seqNumber, BigDecimal.ONE);
            String svcContrBrCd = rs.getString("SVC_CONTR_BR_CD");
            ZYPEZDItemValueSetter.setValue(inParam.svcContrBrCd, svcContrBrCd);
            ZYPEZDItemValueSetter.setValue(inParam.serNum, trimCut(rs.getString("SER_NUM"), SUB_STR_POS_20));
            ZYPEZDItemValueSetter.setValue(inParam.dsContrNum, trimCut(rs.getString("DS_CONTR_NUM"), SUB_STR_POS_10));
            ZYPEZDItemValueSetter.setValue(inParam.batFaxDsContrSqNum, rs.getString("BAT_FAX_DS_CONTR_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.batFaxRecSqNum, rs.getString("BAT_FAX_REC_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.nextBllgDt, rs.getString("NEXT_BLLG_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.shipToCustCd, trimCut(rs.getString("SHIP_TO_CUST_CD"), SUB_STR_POS_8));
            ZYPEZDItemValueSetter.setValue(inParam.bllgMtrBillToCustCd, trimCut(rs.getString("BLLG_MTR_BILL_TO_CUST_CD"), SUB_STR_POS_8));
            ZYPEZDItemValueSetter.setValue(inParam.contrEffFromDt, rs.getString("CONTR_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.contrEffThruDt, rs.getString("CONTR_EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.batFaxBllgCycleCd, rs.getString("BAT_FAX_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.svcContrBrDescTxt, trimCut(trimConcat(svcContrBrCd, STR_HYPHEN, rs.getString("SVC_CONTR_BR_DESC_TXT")), SUB_STR_POS_30));
            ZYPEZDItemValueSetter.setValue(inParam.baseBllgNextBllgDt, rs.getString("BASE_BLLG_NEXT_BLLG_DT"));
            // mod start 2016/02/29 CSA Defect#2684
            ZYPEZDItemValueSetter.setValue(inParam.mtrLbNm, trimCut(rs.getString("MTR_LB_DESC_TXT"), SUB_STR_POS_30));
            // mod end 2016/02/29 CSA Defect#2684
            BigDecimal initReadMtrCnt = rs.getBigDecimal("INIT_READ_MTR_CNT");
            if (ZYPCommonFunc.hasValue(initReadMtrCnt) && initReadMtrCnt.compareTo(MAX_COUNT) > 0) {
                ZYPEZDItemValueSetter.setValue(inParam.initReadMtrCnt, MAX_COUNT);
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.initReadMtrCnt, initReadMtrCnt);
            }
            BigDecimal lastReadMtrCnt = rs.getBigDecimal("LAST_READ_MTR_CNT");
            if (ZYPCommonFunc.hasValue(lastReadMtrCnt) && lastReadMtrCnt.compareTo(MAX_COUNT) > 0) {
                ZYPEZDItemValueSetter.setValue(inParam.lastReadMtrCnt, MAX_COUNT);
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.lastReadMtrCnt, lastReadMtrCnt);
            }
            ZYPEZDItemValueSetter.setValue(inParam.lastMtrReadDt, rs.getString("LAST_MTR_READ_DT"));
            ZYPEZDItemValueSetter.setValue(inParam.custMachCtrlNum, trimCut(rs.getString("CUST_MACH_CTRL_NUM"), SUB_STR_POS_12));
            ZYPEZDItemValueSetter.setValue(inParam.firstProdCtrlCd, rs.getString("FIRST_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.mdlNm, trimCut(rs.getString("MDL_NM"), SUB_STR_POS_15));
            ZYPEZDItemValueSetter.setValue(inParam.locCtacPsnNm, trimCut(trimConcat(rs.getString("LOC_CTAC_FIRST_NM"), STR_SPACE, rs.getString("LOC_CTAC_LAST_NM")), SUB_STR_POS_30));
            ZYPEZDItemValueSetter.setValue(inParam.ctacPsnTelNum, trimCut(rs.getString("CTAC_PSN_TEL_NUM"), SUB_STR_POS_15));
            ZYPEZDItemValueSetter.setValue(inParam.ctacPsnFaxNum, trimCut(rs.getString("CTAC_PSN_FAX_NUM"), SUB_STR_POS_15));
            ZYPEZDItemValueSetter.setValue(inParam.ctacPsnAvalHourMn, rs.getString("CTAC_PSN_AVAL_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(inParam.shipToCustNm, trimCut(rs.getString("SHIP_TO_CUST_NM"), SUB_STR_POS_30));
            String dsContrCatgCd = rs.getString("DS_CONTR_CATG_CD");
            if (ZYPCommonFunc.hasValue(dsContrCatgCd) && dsContrCatgCd.equals(DS_CONTR_CATG.FLEET)) {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrCatgCd, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(inParam.dsContrCatgCd, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(inParam.ediAttnCd, rs.getString("EDI_ATTN_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.faxCtacPsnNm, trimCut(trimConcat(rs.getString("FAX_CTAC_PSN_FIRST_NM"), STR_SPACE, rs.getString("FAX_CTAC_PSN_LAST_NM")), SUB_STR_POS_20));
            ZYPEZDItemValueSetter.setValue(inParam.firstLineAddr, trimCut(rs.getString("FIRST_LINE_ADDR"), SUB_STR_POS_30));
            ZYPEZDItemValueSetter.setValue(inParam.scdLineAddr, trimCut(rs.getString("SCD_LINE_ADDR"), SUB_STR_POS_30));
            ZYPEZDItemValueSetter.setValue(inParam.ctyAddr, trimCut(rs.getString("CTY_ADDR"), SUB_STR_POS_15));
            ZYPEZDItemValueSetter.setValue(inParam.stCd, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.postCd, trimCut(rs.getString("POST_CD"), SUB_STR_POS_12));
            ZYPEZDItemValueSetter.setValue(inParam.mtrReadMethCd, rs.getString("MTR_READ_METH_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.ctacPsnEmlAddr, trimCut(rs.getString("CTAC_PSN_EML_ADDR"), SUB_STR_POS_150));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return inParam;
    }

    private SVC_BAT_ERR_LOGTMsg setCreateErrorValue() {
        SVC_BAT_ERR_LOGTMsg inParam = new SVC_BAT_ERR_LOGTMsg();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogPk, getSvcBatErrLogPk());
        ZYPEZDItemValueSetter.setValue(inParam.bizAppId, STR_BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogTs, this.procTs);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_01, this.errKeyMap.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_02, this.errKeyMap.get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_03, this.errKeyMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_04, this.errKeyMap.get("DS_CONTR_BLLG_MTR_PK"));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_05, this.errKeyMap.get("SVC_PHYS_MTR_PK"));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_06, String.valueOf(this.normalCount + this.errorCount));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_07, String.valueOf(this.errorCount));
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_01, KEY_DS_CONTR_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_02, KEY_DS_CONTR_DTL_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_03, KEY_SVC_MACH_MSTR_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_04, KEY_DS_CONTR_BLLG_MTR_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_05, KEY_SVC_PHYS_MTR_PK);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_06, KEY_TOTAL_COUNT);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_07, KEY_ERROR_COUNT);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrMsgTxt, this.errMsg);

        return inParam;
    }

    private void setErrKeyMap(ResultSet rs) {
        try {
            this.errKeyMap.put("DS_CONTR_PK", rs.getString("DS_CONTR_PK"));
            this.errKeyMap.put("DS_CONTR_DTL_PK", rs.getString("DS_CONTR_DTL_PK"));
            this.errKeyMap.put("SVC_MACH_MSTR_PK", rs.getString("SVC_MACH_MSTR_PK"));
            this.errKeyMap.put("DS_CONTR_BLLG_MTR_PK", rs.getString("DS_CONTR_BLLG_MTR_PK"));
            this.errKeyMap.put("SVC_PHYS_MTR_PK", rs.getString("SVC_PHYS_MTR_PK"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private String trimCut(String val, int len) {
        if (!ZYPCommonFunc.hasValue(val) || val.length() < len) {
            return val;
        }
        return val.trim().substring(0, len);
    }

    private String trimConcat(String val1, String con, String val2) {
        String strVal1 = val1;
        String strVal2 = val2;

        if (ZYPCommonFunc.hasValue(strVal1)) {
            strVal1 = strVal1.trim();
        }

        if (ZYPCommonFunc.hasValue(strVal2)) {
            strVal2 = strVal2.trim();
        }

        return ZYPCommonFunc.concatString(strVal1, con, strVal2);
    }

    /**
     * This method will return SVC_BAT_ERR_LOG_SQ for
     * SVC_BAT_ERR_LOG_PK.
     * @return BigDecimal
     */
    private BigDecimal getSvcBatErrLogPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ");
    }

    /**
     *<pre>
     * Send Mail
     *</pre>
     */
    private void sendMail() {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!fromAddrList.isEmpty()) {
            from = fromAddrList.get(0);
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        groupTo.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_GROUP_ID_TO });
        }

        // 3. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0045E, new String[] {MAIL_TEMPLATE_ID });
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, STR_BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, this.errMsg);

        // 4. Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
}
