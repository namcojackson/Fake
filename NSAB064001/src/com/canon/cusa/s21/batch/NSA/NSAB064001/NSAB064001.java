/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB064001;

import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ATTR_NM_IWR_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ATTR_NM_MTR_CNTR_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ATTR_NM_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ATTR_NM_SERNUM;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.CHECK_DIGITS_TP_NON_NUMERIC;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.CHECK_DIGITS_TP_REAL_NUMERIC;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_CRLF;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_DATE_1;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_DATE_2;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_DATE_FMT;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_METER_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_METER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_MODEL;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ERR_MSG_SERIAL;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.INS_ERR_MSD_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.INTERFACE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.IWR_BLLG_CNT_INFO_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MAIL_GROUP_KEY_FROM;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MAIL_GRP_ID_FROM;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MAIL_ITEM_BATCH_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MAIL_ITEM_ERR_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MAIL_ITEM_ERR_MSG;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.NUM_0;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.NUM_1;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.NUM_2;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.NUM_3;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.NUM_8;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.SET_MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.SPLIT_START_INDEX;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.STR_COLON;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.STR_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.STR_SLASH;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.STR_SPLIT_CHAR;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.TIME_STAMP_FORMAT_SLASH;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.UNIT_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ZZM9001E;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ZZM9004E;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ZZM9008E;
import static com.canon.cusa.s21.batch.NSA.NSAB064001.constant.NSAB064001Constant.ZZM9032E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.common.EZDValidatorException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NSAI0230_01TMsg;
import business.db.NSAI0230_02TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Change Format IWR Meter Reading
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/06/20   Hitachi         O.Okuma         Create          N/A
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#10707
 * </pre>
 */
public class NSAB064001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId;

    /** Commit Number */
    private int commitNumber;

    /** Error Count */
    private int errorCount;

    /** Total Error Count */
    private int totalErrorCount;

    /** Normal Count */
    private int normalCount;

    /** Total Normal Count */
    private int totalNormalCount;

    /** Process Time Stamp */
    private String procTs;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = trxAccess.getIntegrationRecord(this.intfcId);
        this.unitId = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.errorCount = 0;
        this.normalCount = 0;
        this.totalErrorCount = 0;
        this.totalNormalCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {

        for (BigDecimal trgtTrxId : this.trxIdList) {

            this.trxId = this.trxAccess.getNextTransactionId();
            insertInterfaceData("getInputData", setSearchCondition(trgtTrxId));

            this.trxAccess.endIntegrationProcess(this.intfcId, trgtTrxId);

            if (this.normalCount > 0) {
                insertInterfaceHeader();
                this.trxAccess.createIntegrationRecordForBatch(INTERFACE_ID, this.trxId);
            }

            this.totalNormalCount += this.normalCount;
            this.totalErrorCount += this.errorCount;
            this.normalCount = 0;
            this.errorCount = 0;
            this.unitId = 0;
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errMsgList.size() > 0) {
            this.termCd = TERM_CD.WARNING_END;
            postErrorMail();
        }
        setTermState(this.termCd, this.totalNormalCount, this.totalErrorCount, this.totalNormalCount + this.totalErrorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB064001().executeBatch(NSAB064001.class.getSimpleName());
    }

    private void insertInterfaceData(String sqlId, Map<String, Object> paramMap) {
        List<NSAI0230_02TMsg> inTMsgList = new ArrayList<NSAI0230_02TMsg>();

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

            int procCount = 0;
            int commitCount = 0;

            // Insert Interface Data
            while (rs.next()) {
                String[] splitData = rs.getString(IWR_BLLG_CNT_INFO_TXT).split(STR_SPLIT_CHAR);
                String[] dtlDataList = getInsertData(rs, splitData);

                int mtrCntrIdIndex = SPLIT_START_INDEX;

                if (dtlDataList == null) {

                    this.errorCount++;

                } else {

                    while (mtrCntrIdIndex < dtlDataList.length) {

                        this.unitId++;
                        inTMsgList.add(setCreateValueForDetal(dtlDataList, mtrCntrIdIndex));
                        mtrCntrIdIndex += 2;
                    }

                    if (this.commitNumber <= commitCount + inTMsgList.size()) {
                        commit();
                        this.normalCount += procCount;
                        procCount = 0;
                        commitCount = 0;
                    }

                    commitCount = insertInterfaceData(inTMsgList);

                    procCount++;
                    inTMsgList = new ArrayList<NSAI0230_02TMsg>();
                }
            }
            if (commitCount != 0) {
                commit();
                this.normalCount += procCount;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertInterfaceData(List<NSAI0230_02TMsg> inMsgLst) {
        NSAI0230_02TMsg[] inMsgArray;
        inMsgArray = new NSAI0230_02TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(INS_ERR_MSD_ID, new String[] {inMsgArray[0].getTableName() });
        }
        return insertCount;
    }

    private Map<String, Object> setSearchCondition(BigDecimal srchTrxId) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("intfcId", this.intfcId);
        inParam.put("transactionId", srchTrxId);
        return inParam;
    }

    private NSAI0230_02TMsg setCreateValueForDetal(String[] dataList, int index) {

        NSAI0230_02TMsg inParam = new NSAI0230_02TMsg();

        setValue(inParam.interfaceId, INTERFACE_ID);
        setValue(inParam.transactionId, this.trxId);
        setValue(inParam.segmentId, BigDecimal.ONE);
        setValue(inParam.unitId, BigDecimal.valueOf(this.unitId));
        setValue(inParam.seqNumber, BigDecimal.ONE);
        setValue(inParam.localMtrRcvDtTmTs, dataList[NUM_0]);
        setValue(inParam.gmtMtrRcvDtTmTs, dataList[NUM_1]);
        setValue(inParam.iwrMdlNm, dataList[NUM_2]);
        setValue(inParam.serNum, dataList[NUM_3]);
        setValue(inParam.mtrCntrId, dataList[index]);
        if (hasValue(dataList[index + 1])) {
            setValue(inParam.readMtrCnt, BigDecimal.valueOf(Integer.valueOf(dataList[index + 1])));
        }
        return inParam;
    }

    private void insertInterfaceHeader() {

        NSAI0230_01TMsg inMsg = new NSAI0230_01TMsg();

        setValue(inMsg.interfaceId, INTERFACE_ID);
        setValue(inMsg.transactionId, this.trxId);
        setValue(inMsg.segmentId, BigDecimal.ONE);
        setValue(inMsg.unitId, BigDecimal.ONE);
        setValue(inMsg.seqNumber, BigDecimal.ONE);
        setValue(inMsg.ugwCtryCd, ZYPCodeDataUtil.getVarCharConstValue("IWR_CTRY_CD", this.glblCmpyCd));
        setValue(inMsg.ugwCmpyCd, ZYPCodeDataUtil.getVarCharConstValue("IWR_CORP_CD", this.glblCmpyCd));
        setValue(inMsg.cratDtTmTs, this.procTs);

        EZDTBLAccessor.insert(inMsg);
        if (inMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            throw new S21AbendException(INS_ERR_MSD_ID, new String[] {inMsg.getTableName() });
        }
    }

    private String[] getInsertData(ResultSet rs, String[] dataList) throws SQLException {

        dataList[0] = dataList[0].substring(1);
        dataList[dataList.length - 1] = dataList[dataList.length - 1].substring(0, dataList[dataList.length - 1].length() - 1);

        String[] rtnDataList = new String[dataList.length];

        NSAI0230_02TMsg msg = new NSAI0230_02TMsg();

        StringBuilder sb = new StringBuilder();
        sb.append(STR_INTERFACE_ID);
        sb.append(STR_COLON);
        sb.append(this.intfcId);
        sb.append(" ");
        sb.append(TRANSACTION_ID);
        sb.append(STR_COLON);
        sb.append(rs.getString(TRANSACTION_ID));
        sb.append(" ");
        sb.append(UNIT_ID);
        sb.append(STR_COLON);
        sb.append(rs.getString(UNIT_ID));
        sb.append(" ");

        boolean isNormal = true;

        // LOCAL_MTR_RCV_DT_TM_TS
        if (!hasValue((dataList[NUM_0]))) {
            rtnDataList[NUM_0] = "";
        } else {
            rtnDataList[NUM_0] = checkDate(dataList[NUM_0], sb.toString(), ERR_MSG_DATE_1);

            if (rtnDataList[NUM_0] == null) {
                isNormal = false;
            }
        }

        // GMT_MTR_RCV_DT_TM_TS
        if (!hasValue((dataList[NUM_1]))) {
            rtnDataList[NUM_1] = "";
        } else {
            rtnDataList[NUM_1] = checkDate(dataList[NUM_1], sb.toString(), ERR_MSG_DATE_2);

            if (rtnDataList[NUM_1] == null) {
                isNormal = false;
            }
        }

        // IWR_MDL_NM
        if (!hasValue((dataList[NUM_2]))) {
            rtnDataList[NUM_2] = "";
        } else {
            EZDItemAttribute attr = msg.getAttr(ATTR_NM_IWR_MDL_NM);
            if (checkChar(dataList[NUM_2], sb.toString(), ERR_MSG_MODEL) || checkLen(attr, dataList[NUM_2], CHECK_DIGITS_TP_NON_NUMERIC, sb.toString(), ERR_MSG_MODEL)) {
                isNormal = false;
            }
            rtnDataList[NUM_2] = dataList[NUM_2];
        }

        // SER_NUM
        if (!hasValue((dataList[NUM_3]))) {
            rtnDataList[NUM_3] = "";
        } else {
            EZDItemAttribute attr = msg.getAttr(ATTR_NM_SERNUM);
            if (checkChar(dataList[NUM_3], sb.toString(), ERR_MSG_SERIAL) || checkLen(attr, dataList[NUM_3], CHECK_DIGITS_TP_NON_NUMERIC, sb.toString(), ERR_MSG_SERIAL)) {
                isNormal = false;
            }
            rtnDataList[NUM_3] = dataList[NUM_3];
        }

        for (int i = SPLIT_START_INDEX; i < dataList.length; i++) {
            if (!hasValue(dataList[i])) {
                rtnDataList[i] = "";
                continue;
            }
            if (i % 2 == 0) {
                // MTR_CNTR_ID
                EZDItemAttribute attr = msg.getAttr(ATTR_NM_MTR_CNTR_ID);
                if (checkChar(dataList[i], sb.toString(), ERR_MSG_METER_NUM) || checkLen(attr, dataList[i], CHECK_DIGITS_TP_NON_NUMERIC, sb.toString(), ERR_MSG_METER_NUM)) {
                    isNormal = false;
                }
            } else {
                // READ_MTR_CNT
                EZDItemAttribute attr = msg.getAttr(ATTR_NM_READ_MTR_CNT);
                if (checkNum(dataList[i], sb.toString(), ERR_MSG_METER_CNT) || checkLen(attr, dataList[i], CHECK_DIGITS_TP_REAL_NUMERIC, sb.toString(), ERR_MSG_METER_CNT)) {
                    isNormal = false;
                }
            }
            rtnDataList[i] = dataList[i];
        }

        if (!isNormal) {
            return null;
        }
        return rtnDataList;
    }

    private String checkDate(String strDate, String errStr, String errMsg) {

        if (strDate.length() != TIME_STAMP_FORMAT_SLASH.length()) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9032E, new String[] {errStr + errMsg + strDate + ERR_MSG_DATE_FMT }));
            return null;
        }

        if (!ZYPDateUtil.checkDate(strDate.replaceAll(STR_SLASH, "").substring(NUM_0, NUM_8))) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9032E, new String[] {errStr + errMsg + strDate + ERR_MSG_DATE_FMT }));
            return null;
        }
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(TIME_STAMP_FORMAT_SLASH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(TIME_STAMP_FORMAT);
        String rtnStr;

        try {
            rtnStr = outputDateFormat.format(inputDateFormat.parse(strDate));
        } catch (java.text.ParseException e) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9032E, new String[] {errStr + errMsg + strDate + ERR_MSG_DATE_FMT }));
            return null;
        }
        return rtnStr;
    }

    private boolean checkChar(String str, String errStr, String fieldNm) {

        boolean isErr = !ZYPCommonFunc.isNarrowChar(str);

        if (isErr) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9008E, new String[] {errStr + fieldNm + str }));
        }
        return isErr;
    }

    private boolean checkLen(EZDItemAttribute attr, String str, int lstatus, String errStr, String fieldNm) {

        boolean isErr = false;
        try {
            isErr = !ZYPCommonFunc.isCheckDigits(str, attr.getDigit(), attr.getFracDigit(), lstatus);

        } catch (EZDValidatorException e) {
            isErr = true;
        }

        if (isErr) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9001E, new String[] {errStr + fieldNm + str }));
        }
        return isErr;
    }

    private boolean checkNum(String strNum, String errStr, String fieldNm) {

        boolean isErr = !ZYPCommonFunc.isNumeric(strNum);

        if (isErr) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9004E, new String[] {errStr + fieldNm + strNum }));
        }
        return isErr;
    }

    private void postErrorMail() {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BUSINESS_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, SET_MAIL_TEMPLATE_ID);
        if (template == null) {
            return;
        }
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, this.getClass().getSimpleName());
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, this.procTs);

        StringBuilder msgBuf = new StringBuilder();
        for (String errMsg : this.errMsgList) {
            msgBuf.append(errMsg);
            msgBuf.append(ERR_MSG_CRLF);
        }
        String errorMessage = msgBuf.toString();

        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, errorMessage);

        // *****************************************************************
        // Post mail
        // *****************************************************************
        S21Mail mail;
        for (S21MailAddress addr : addrToList) {
            mail = new S21Mail(this.glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddress(addr);
            mail.setMailTemplate(template);
            mail.postMail();
        }
        return;
    }
}
