/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB023001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_DTLTMsg;
import business.db.IWR_MTR_READ_DTL_WRKTMsg;
import business.db.IWR_MTR_READ_HDR_WRKTMsg;
import business.db.IWR_RGTN_CONDTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 * Create IWR Meter Reading Work
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/24   Hitachi         T.Aoyagi        Create          N/A
 * 2016/11/09   Hitachi         N.Arai          Update          QC#15829
 * 2018/03/02   Hitachi         U.Kim           Update          QC#23112
 * 2018/03/07   Hitachi         K.Kojima        Update          QC#23280
 * 2018/05/08   CITS            T.Wada          Update          QC#15410
 * </pre>
 */
public class NSAB023001 extends S21BatchMain {

    /** [@] is not registered.(@) */
    private static final String NSAM0069E = "NSAM0069E";

    /** Serial # cannot be uniquely specified. */
    private static final String NSAM0128E = "NSAM0128E";

    /** [@] cannot be obtained. */
    private static final String NSAM0179E = "NSAM0179E";

    /** Failed to update "@". */
    private static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    private static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    private static final String NSAM0033E = "NSAM0033E";

    /** [@] field is mandatory. */
    private static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    private static final String ZZZM9006E = "ZZZM9006E";

    /** message Item: GlobalCompanyCode */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceId */
    private static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    /** MAX_COMMIT_NUMBER:1000 */
    private static final int MAX_COMMIT_NUMBER = 1000;

    /** Length : Date Time */
    private static final int LEN_DT_TM = 14;

    /** Business Application ID */
    private static final String BIZ_APP_ID = "NSAB0230";

    /** mail group id for From */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    private static final String MAIL_GROUP_ID_TO = "NSAB0230";

    /** template ID */
    private static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    private static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    private static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    private static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** Sales Date */
    private String salesDate;

    /** Meter History Count */
    private BigDecimal mtrHistCnt;

    /** Commit Number */
    private int commitNumber;

    /** Total Count */
    private int totalCount;

    /** error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** From Address List */
    private List<S21MailAddress> fromAddrList = null;

    /** To Address List */
    private List<S21MailAddress> addrToList = null;

    /** Mail Template */
    private S21MailTemplate template = null;

    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();

// START 2016/11/09 N.Arai [QC#15829, MOD]
    /** Batch Id **/
    private static final String BATCH_ID = BIZ_APP_ID + "01";

    /** SVC_MACH_MSTR Column : IWR_COND_CD **/
    private static final String IWR_COND_CD = "IWR_COND_CD";
// END 2016/11/09 N.Arai [QC#15829, MOD]
    
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

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, NSAB023001.class.getSimpleName());

        // Get Meter History Count
        this.mtrHistCnt = ZYPCodeDataUtil.getNumConstValue("UGW_MTR_HIST_CNT", this.glblCmpyCd);
        if (!hasValue(this.mtrHistCnt)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"NUM_CONST"});
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        this.fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        this.addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        this.template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = trxAccess.getIntegrationRecord(this.intfcId);
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatClient = S21SsmBatchClient.getClient(getClass());
    }

    @Override
    protected void mainRoutine() {

        for (BigDecimal trxId : this.trxIdList) {
            insertIwrMtrReadWrk(trxId);

            // Insert Transaction data
            trxAccess.endIntegrationProcess(this.intfcId, trxId);

            commit();
        }
        deleteIwrMtrReadWrk();
        sendMail();
    }

    @Override
    protected void termRoutine() {
        this.errorCount = this.errMsgList.size();
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB023001().executeBatch(NSAB023001.class.getSimpleName());
    }

    private void insertIwrMtrReadWrk(BigDecimal trxId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = getSsmParam(trxId);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getIFData", ssmParam, execParam);
            rs = stmt.executeQuery();

            Map<String, BigDecimal> prevKey = new HashMap<String, BigDecimal>();
            List<BigDecimal> machMstrPkList = new ArrayList<BigDecimal>();
            String prevSerNum = null;
            BigDecimal hdrWrkPk = null;
            Map<String, Object> machInfo = null;

            while (rs.next()) {

                String errMsg = null;
                totalCount++;

                // ------------------------------
                // Insert IWR_MTR_READ_HDR_WRK
                // ------------------------------
                if (prevKey.isEmpty() || !isSameKey(prevKey, rs)) {
                    hdrWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.IWR_MTR_READ_HDR_WRK_SQ);
                    insertIwrMtrReadHdrWrk(rs, hdrWrkPk);
                    setKey(prevKey, rs);
                }

                // ------------------------------
                // Get Machine Info
                // ------------------------------
                String serNum = rs.getString("SER_NUM");
                List<Map<String, Object>> machInfoList = getMachInfo(serNum);

                if (machInfoList.size() == 1) {
                    machInfo = machInfoList.get(0);
                } else if (machInfoList.size() > 1) {
                    machInfo = null;
                    errMsg = S21MessageFunc.clspGetMessage(NSAM0128E);
                } else {
                    machInfo = null;
                    errMsg = S21MessageFunc.clspGetMessage(ZZZM9006E, new String[]{"SVC_MACH_MSTR"});
                }

                // ------------------------------
                // Insert IWR_MTR_READ_DTL_WRK
                // ------------------------------
                IWR_MTR_READ_DTL_WRKTMsg dtlWrkTMsg = insertIwrMtrReadDtlWrk(rs, hdrWrkPk, machInfo, errMsg);

                if (hasValue(dtlWrkTMsg.iwrMsgTxt)) {
                    // Error
                    addErrMsg(dtlWrkTMsg);
                    continue;
                }

                // ------------------------------
                // Update Machine Info
                // ------------------------------
                if (prevSerNum == null || !prevSerNum.equals(serNum)) {
                    BigDecimal svcMachMstrPk = dtlWrkTMsg.svcMachMstrPk.getValue();
                    String iwrCondCd = (String) machInfo.get("IWR_COND_CD");

                    updateDsContrDtl(svcMachMstrPk);
                    updateSvcMachMstr(svcMachMstrPk, iwrCondCd);

                    if (!machMstrPkList.contains(svcMachMstrPk)) {
                        machMstrPkList.add(svcMachMstrPk);
                    }

                    prevSerNum = serNum;
                }
            }

            for (BigDecimal svcMachMstrPk : machMstrPkList) {
                updateIwrRgtnCond(svcMachMstrPk);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> getSsmParam(BigDecimal trxId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.intfcId);
        ssmParam.put("transactionId", trxId);
        // START 2018/03/02 U.Kim [QC#23112, ADD]
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        // END 2018/03/02 U.Kim [QC#23112, ADD]
        return ssmParam;
    }

    private boolean isSameKey(Map<String, BigDecimal> prevKey, ResultSet rs) throws SQLException {

        if (prevKey.get("TRANSACTION_ID").compareTo(rs.getBigDecimal("TRANSACTION_ID")) != 0) {
            return false;
        }
        if (prevKey.get("SEGMENT_ID").compareTo(rs.getBigDecimal("SEGMENT_ID")) != 0) {
            return false;
        }
        if (prevKey.get("UNIT_ID").compareTo(rs.getBigDecimal("UNIT_ID")) != 0) {
            return false;
        }
        return true;
    }

    private void setKey(Map<String, BigDecimal> prevKey, ResultSet rs) throws SQLException {

        prevKey.put("TRANSACTION_ID", rs.getBigDecimal("TRANSACTION_ID"));
        prevKey.put("SEGMENT_ID", rs.getBigDecimal("SEGMENT_ID"));
        prevKey.put("UNIT_ID", rs.getBigDecimal("UNIT_ID"));
    }

    private IWR_MTR_READ_HDR_WRKTMsg insertIwrMtrReadHdrWrk(ResultSet rs, BigDecimal iwrMtrReadHdrWrkPk) throws SQLException {
        IWR_MTR_READ_HDR_WRKTMsg inMsg = new IWR_MTR_READ_HDR_WRKTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.iwrMtrReadHdrWrkPk, iwrMtrReadHdrWrkPk);
        setValue(inMsg.ugwCtryCd, rs.getString("UGW_CTRY_CD"));
        setValue(inMsg.ugwCmpyCd, rs.getString("UGW_CMPY_CD"));
        setValue(inMsg.cratDtTmTs, rs.getString("CRAT_DT_TM_TS"));

        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0032E, new String[] {"IWR_MTR_READ_HDR_WRK"});
        }
        return inMsg;
    }

    private IWR_MTR_READ_DTL_WRKTMsg insertIwrMtrReadDtlWrk(ResultSet rs, BigDecimal iwrMtrReadHdrWrkPk, Map<String, Object> machInfo, String errMsg) throws SQLException {

        IWR_MTR_READ_DTL_WRKTMsg inMsg = new  IWR_MTR_READ_DTL_WRKTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal iwrMtrReadDtlWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.IWR_MTR_READ_DTL_WRK_SQ);
        setValue(inMsg.iwrMtrReadDtlWrkPk, iwrMtrReadDtlWrkPk);
        setValue(inMsg.localMtrRcvDtTmTs, rs.getString("LOCAL_MTR_RCV_DT_TM_TS"));
        setValue(inMsg.gmtMtrRcvDtTmTs, rs.getString("GMT_MTR_RCV_DT_TM_TS"));
        setValue(inMsg.iwrMdlNm, rs.getString("IWR_MDL_NM"));
        setValue(inMsg.mtrCntrId, rs.getString("MTR_CNTR_ID"));
        setValue(inMsg.mtrReadDt, convertDateFormat(rs.getString("LOCAL_MTR_RCV_DT_TM_TS")));
        setValue(inMsg.readMtrCnt, rs.getBigDecimal("READ_MTR_CNT"));
        setValue(inMsg.iwrMtrReadHdrWrkPk, iwrMtrReadHdrWrkPk);
        setValue(inMsg.childSerNum, rs.getString("SER_NUM"));

        if (machInfo != null) {
            // ------------------------------
            // Set Machine Info
            // ------------------------------
            BigDecimal svcMachMstrPk = (BigDecimal) machInfo.get("SVC_MACH_MSTR_PK");
            setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
            setValue(inMsg.serNum, (String) machInfo.get("SER_NUM"));
            setValue(inMsg.childSvcMachMstrPk, (BigDecimal) machInfo.get("CHILD_SVC_MACH_MSTR_PK"));
            setValue(inMsg.childSerNum, (String) machInfo.get("CHILD_SER_NUM"));

            // ------------------------------
            // Set Physical Meter PK
            // ------------------------------
            BigDecimal svcPhysMtrPk = getMtrInfo(svcMachMstrPk, rs.getString("MTR_CNTR_ID"));
            if (hasValue(svcPhysMtrPk)) {
                setValue(inMsg.svcPhysMtrPk, svcPhysMtrPk);
            } else {
                errMsg = S21MessageFunc.clspGetMessage(ZZZM9006E, new String[]{"SVC_PHYS_MTR"});
            }

            // ------------------------------
            // Convert local time to system time
            // ------------------------------
            String mtrRcvDtTmTs = convertLocToSys(rs.getString("LOCAL_MTR_RCV_DT_TM_TS"), machInfo);
            if (hasValue(mtrRcvDtTmTs)) {
                setValue(inMsg.mtrRcvDtTmTs, mtrRcvDtTmTs);
            } else {
                errMsg = S21MessageFunc.clspGetMessage(NSAM0179E, new String[]{"Converted system time"});
            }
        }

        if (hasValue(errMsg)) {
            setValue(inMsg.iwrProcStsCd, IWR_PROC_STS.SKIP_RECORD);
        } else {
            setValue(inMsg.iwrProcStsCd, IWR_PROC_STS.NEW_RECORD);
        }
        setValue(inMsg.iwrMsgTxt, errMsg);

        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0032E, new String[] {"IWR_MTR_READ_DTL_WRK"});
        }
        return inMsg;
    }

    private String convertDateFormat(String dtTm) {

        if (!hasValue(dtTm)) {
            return dtTm;
        }
        return dtTm.substring(0, ZYPDateUtil.TYPE_YYYYMMDD.length());
    }

    private String convertLocToSys(String locDtTm, Map<String, Object> machInfo) {

        if (hasValue(locDtTm)) {
            String ctryCd = (String) machInfo.get("CTRY_CD");
            String postCd = (String) machInfo.get("POST_CD");
            SvcTimeZoneInfo svcTimeZoneInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, locDtTm, ctryCd, postCd);
            if (svcTimeZoneInfo != null) {
                String sysDtTm = svcTimeZoneInfo.getDateTime();
                if (hasValue(sysDtTm)) {
                    return sysDtTm.substring(0, LEN_DT_TM);
                }
            }
        }
        return null;
    }

    private void addErrMsg(IWR_MTR_READ_DTL_WRKTMsg dtlWrkTMsg) {

        StringBuilder sb = new StringBuilder();
        String errMsg = dtlWrkTMsg.iwrMsgTxt.getValue();
        if (hasValue(errMsg)) {
            sb.append(errMsg);
            sb.append("[IWR_MTR_READ_DTL_WRK_PK=");
            sb.append(dtlWrkTMsg.iwrMtrReadDtlWrkPk.getValue());
            sb.append("]");
            this.errMsgList.add(sb.toString());
        }
    }

    private void updateIwrRgtnCond(BigDecimal svcMachMstrPk) {

        if (!hasValue(svcMachMstrPk)) {
            return;
        }

        Map<String, Object> iwrRgtnCond = getMaxMtrRcvDt(svcMachMstrPk);
        if (iwrRgtnCond != null) {
            BigDecimal iwrRgtnCondPk = (BigDecimal) iwrRgtnCond.get("IWR_RGTN_COND_PK");
            String mtrRcvDtTmTs = (String) iwrRgtnCond.get("MTR_RCV_DT_TM_TS");

            IWR_RGTN_CONDTMsg inMsg = new IWR_RGTN_CONDTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.iwrRgtnCondPk, iwrRgtnCondPk);
            inMsg = (IWR_RGTN_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg == null) {
                return;
            }
            setValue(inMsg.iwrLastCommTs, mtrRcvDtTmTs);
            EZDTBLAccessor.update(inMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0031E, new String[] {"IWR_RGTN_COND"});
            }
        }
    }

    private void updateDsContrDtl(BigDecimal svcMachMstrPk) {

        if (!hasValue(svcMachMstrPk)) {
            return;
        }

        // QC#15410 Mod Start
        List<BigDecimal> dsContrDtlPkList = getDsContrDtlPk(svcMachMstrPk);
        for (int i=0; i<dsContrDtlPkList.size(); i++) {
            BigDecimal dsContrDtlPk = dsContrDtlPkList.get(i);
        if (hasValue(dsContrDtlPk)) {
            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
            inMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg == null) {
                    continue;
            }
            setValue(inMsg.prevMtrReadMethCd, inMsg.mtrReadMethCd);
            setValue(inMsg.mtrReadMethCd, MTR_READ_METH.IMAGEWARE);
            EZDTBLAccessor.update(inMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0031E, new String[] {"DS_CONTR_DTL"});
            }
        }
            
        }
//        BigDecimal dsContrDtlPk = getDsContrDtlPk(svcMachMstrPk);
//        if (hasValue(dsContrDtlPk)) {
//            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
//            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
//            inMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
//            if (inMsg == null) {
//                return;
//            }
//            setValue(inMsg.prevMtrReadMethCd, inMsg.mtrReadMethCd);
//            setValue(inMsg.mtrReadMethCd, MTR_READ_METH.IMAGEWARE);
//            EZDTBLAccessor.update(inMsg);
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
//                throw new S21AbendException(NSAM0031E, new String[] {"DS_CONTR_DTL"});
//            }
//        }
        // QC#15410 Mod End
    }

    private void updateSvcMachMstr(BigDecimal svcMachMstrPk, String iwrCondCd) {

        if (!hasValue(svcMachMstrPk)) {
            return;
        }
        if (!IWR_COND.NOT_COMMUNICATING.equals(iwrCondCd)) {
            return;
        }
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
        inMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
        if (inMsg == null) {
            return;
        }
        setValue(inMsg.iwrCondCd, IWR_COND.ACTIVE);
        EZDTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0031E, new String[] {"SVC_MACH_MSTR"});
        }

// START 2016/11/09 N.Arai [QC#15829, MOD]
        createSvcMachMstrTrk(svcMachMstrPk, iwrCondCd, inMsg.iwrCondCd.getValue());
// END 2016/11/09 N.Arai [QC#15829, MOD]
    }

// START 2016/11/09 N.Arai [QC#15829, MOD]
    private void createSvcMachMstrTrk(BigDecimal svcMachMstrPk, String oldIwrCondCd, String newIwrCondCd) {
        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(tMsg.trxRgtnDt, this.salesDate);
        setValue(tMsg.updFldTxt, IWR_COND_CD);
        setValue(tMsg.oldValTxt, oldIwrCondCd);
        setValue(tMsg.newValTxt, newIwrCondCd);
        setValue(tMsg.updUsrId, BATCH_ID);
        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            throw new S21AbendException(NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
        }
    }
// END 2016/11/09 N.Arai [QC#15829, MOD]

    private void deleteIwrMtrReadWrk() {

        // START 2018/03/07 K.Kojima [QC#23280,ADD]
        List<IWR_MTR_READ_DTL_WRKTMsg> dtlDeleteList = new ArrayList<IWR_MTR_READ_DTL_WRKTMsg>();
        // END 2018/03/07 K.Kojima [QC#23280,ADD]

        List<BigDecimal> dtlPkList = getIwrMtrReadDtlWrkPkForDel();
        for (BigDecimal dtlPk : dtlPkList) {
            IWR_MTR_READ_DTL_WRKTMsg inMsg = new IWR_MTR_READ_DTL_WRKTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.iwrMtrReadDtlWrkPk, dtlPk);
            inMsg = (IWR_MTR_READ_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg == null) {
                continue;
            }
            // START 2018/03/07 K.Kojima [QC#23280,DEL]
            // EZDTBLAccessor.logicalRemove(inMsg);
            // 
            // if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            //     throw new S21AbendException(NSAM0033E, new String[] {"IWR_MTR_READ_DTL_WRK"});
            // }
            // END 2018/03/07 K.Kojima [QC#23280,DEL]
            // START 2018/03/07 K.Kojima [QC#23280,ADD]
            dtlDeleteList.add(inMsg);
            if (dtlDeleteList.size() >= this.commitNumber) {
                IWR_MTR_READ_DTL_WRKTMsg[] inMsgArray = new IWR_MTR_READ_DTL_WRKTMsg[dtlDeleteList.size()];
                S21FastTBLAccessor.removePhysical(dtlDeleteList.toArray(inMsgArray));
                dtlDeleteList = new ArrayList<IWR_MTR_READ_DTL_WRKTMsg>();
            }
            // END 2018/03/07 K.Kojima [QC#23280,ADD]
        }
        // START 2018/03/07 K.Kojima [QC#23280,ADD]
        if (dtlDeleteList.size() > 0) {
            IWR_MTR_READ_DTL_WRKTMsg[] inMsgArray = new IWR_MTR_READ_DTL_WRKTMsg[dtlDeleteList.size()];
            S21FastTBLAccessor.removePhysical(dtlDeleteList.toArray(inMsgArray));
        }
        // END 2018/03/07 K.Kojima [QC#23280,ADD]

        List<BigDecimal> hdrPkList = getIwrMtrReadHdrWrkPkForDel();
        for (BigDecimal hdrPk : hdrPkList) {
            IWR_MTR_READ_HDR_WRKTMsg inMsg = new IWR_MTR_READ_HDR_WRKTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.iwrMtrReadHdrWrkPk, hdrPk);
            inMsg = (IWR_MTR_READ_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg == null) {
                continue;
            }
            // START 2018/03/07 K.Kojima [QC#23280,MOD]
            // EZDTBLAccessor.logicalRemove(inMsg);
            EZDTBLAccessor.remove(inMsg);
            // END 2018/03/07 K.Kojima [QC#23280,MOD]

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {"IWR_MTR_READ_HDR_WRK"});
            }
        }
    }

    private void sendMail() {

        if (this.errMsgList.isEmpty()) {
            return;
        }

        S21MailAddress from = null;
        if (!this.fromAddrList.isEmpty()) {
            from = this.fromAddrList.get(0);
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String errMsg : this.errMsgList) {
            msgBuf.append(errMsg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        logBuf.append(msgBuf);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }

    private List<Map<String, Object>> getMachInfo(String serNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("serNum", serNum);
        return (List<Map<String, Object>>) ssmBatClient.queryObjectList("getMachInfo", ssmParam);
    }

    private BigDecimal getMtrInfo(BigDecimal svcMachMstrPk, String mtrCntrId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("mtrCntrId", mtrCntrId);
        return (BigDecimal) ssmBatClient.queryObject("getMtrInfo", ssmParam);
    }

    // QC#15410 Mod
//    private BigDecimal getDsContrDtlPk(BigDecimal svcMachMstrPk) {
    private List<BigDecimal> getDsContrDtlPk(BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrDtlStsCdCanc", DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("slsDt", this.salesDate);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("mtrReadMethCd", MTR_READ_METH.IMAGEWARE);
        return (List<BigDecimal>) ssmBatClient.queryObjectList("getDsContrDtlPk", ssmParam);
    }

    private Map<String, Object> getMaxMtrRcvDt(BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("iwrProcStsCdNewRecord", IWR_PROC_STS.NEW_RECORD);
        return (Map<String, Object>) ssmBatClient.queryObject("getMaxMtrRcvDt", ssmParam);
    }

    private List<BigDecimal> getIwrMtrReadDtlWrkPkForDel() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("mtrHistCnt", this.mtrHistCnt);
        return (List<BigDecimal>) ssmBatClient.queryObjectList("getIwrMtrReadDtlWrkPkForDel", ssmParam);
    }

    private List<BigDecimal> getIwrMtrReadHdrWrkPkForDel() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        return (List<BigDecimal>) ssmBatClient.queryObjectList("getIwrMtrReadHdrWrkPkForDel", ssmParam);
    }
}
