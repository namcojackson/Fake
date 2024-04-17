/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB044001;

import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.CONST_NM_IWR_CONN_ERR_DAYS;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.DATE_FORMAT_FULL;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.IWR_COND_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_TEMPLATE_KEY_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_TEMPLATE_KEY_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAIL_TEMPLATE_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.NSAM0031E;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.NSAM0069E;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.NSAM0177E;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.NSAM0178E;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.SQLID_GET_SVC_MACH_MSTR;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.TBL_NM_SVC_MACH_MSTR;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.TBL_NM_SVC_MACH_MSTR_TRK;
import static com.canon.cusa.s21.batch.NSA.NSAB044001.constant.NSAB044001Constant.ZZZM9006E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * Update Not Communication Status
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Hitachi         Y.Takeno        Create          N/A
 * 2016/07/08   Hitachi         Y.Takeno        Create          QC#9425
 * 2016/11/09   Hitachi         N.Arai          Update          QC#15829
 *</pre>
 */
public class NSAB044001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

// START 2016/11/09 N.Arai [QC#15829, MOD]
    /** Sales Date */
    private String slsDt = null;

    /** Error trk Count */
    private int errorTrkCount;
// END 2016/11/09 N.Arai [QC#15829, MOD]

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

// START 2016/11/09 N.Arai [QC#15829, MOD]
        // "Sales Date"
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E);
        }
// END 2016/11/09 N.Arai [QC#15829, MOD]

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main method.
     * 
     * @param args args
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NSAB044001().executeBatch(NSAB044001.class.getSimpleName());
    }

    private void doProcess() {
        List<SVC_MACH_MSTRTMsg> inTMsgList = new ArrayList<SVC_MACH_MSTRTMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // START 07/08/2016 [QC#9425, MOD]
        BigDecimal bdIwrConnErrDays = ZYPCodeDataUtil.getNumConstValue(CONST_NM_IWR_CONN_ERR_DAYS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(bdIwrConnErrDays)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"NUM_CONST:IWR_CONN_ERR_DAYS"});
        }
        int iwrConnErrDays = bdIwrConnErrDays.intValue();
        // END 07/08/2016 [QC#9425, MOD]
        String systemTime = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_FULL);
        String iwrLastCommTs = ZYPDateUtil.addDays(systemTime, iwrConnErrDays * -1);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("iwrLastCommTs", iwrLastCommTs);
        paramMap.put("shrDlrFlg", ZYPConstant.FLG_OFF_N);
        paramMap.put("iwrCondCd", IWR_COND.ACTIVE);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(SQLID_GET_SVC_MACH_MSTR, paramMap, execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;
            int commitTrkCount = 0;
            // update SVC_MACH_MSTR
            while (rs.next()) {
                inTMsgList.add(createTMsg(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = updateSvcMachMstr(inTMsgList);
                    if (commitCount == inTMsgList.size()) {
// START 2016/11/09 N.Arai [QC#15829, MOD]
                        commitTrkCount = createSvcMachMstrTrk(inTMsgList, IWR_COND.ACTIVE);
                        if (commitTrkCount != inTMsgList.size()) {
                            this.errorTrkCount += commitCount;
                            rollback();
                            break;
                        }
// END 2016/11/09 N.Arai [QC#15829, MOD]
                        commit();
                        this.normalCount += commitCount;
                    } else {
                        this.errorCount += commitCount;
                        rollback();
                        break;
                    }
                    inTMsgList = new ArrayList<SVC_MACH_MSTRTMsg>();
                }
            }
            if (inTMsgList.size() != 0) {
                commitCount = updateSvcMachMstr(inTMsgList);
                if (commitCount == inTMsgList.size()) {
// START 2016/11/09 N.Arai [QC#15829, MOD]
                    commitTrkCount = createSvcMachMstrTrk(inTMsgList, IWR_COND.ACTIVE);
                    if (commitTrkCount != inTMsgList.size()) {
                        this.errorTrkCount += commitCount;
                        rollback();
                    }
//END 2016/11/09 N.Arai [QC#15829, MOD]
                    commit();
                    this.normalCount += commitCount;
                } else {
                    this.errorCount += commitCount;
                    rollback();
                }
            }

            if (this.errorCount > 0) {
                sendMail(S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {TBL_NM_SVC_MACH_MSTR, "" }));
// START 2016/11/09 N.Arai [QC#15829, MOD]
            } else if (this.errorTrkCount > 0) {
                sendMail(S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {TBL_NM_SVC_MACH_MSTR_TRK, "" }));
//END 2016/11/09 N.Arai [QC#15829, MOD]
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

// START 2016/11/09 N.Arai [QC#15829, MOD]
    private int createSvcMachMstrTrk(List<SVC_MACH_MSTRTMsg> inTMsgList, String oldIwrCondCd) {

        int insCnt = 0;
        for (SVC_MACH_MSTRTMsg tMsg : inTMsgList) {

            SVC_MACH_MSTR_TRKTMsg trkTMsg = new SVC_MACH_MSTR_TRKTMsg();
            setValue(trkTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(trkTMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
            setValue(trkTMsg.svcMachMstrPk, tMsg.svcMachMstrPk);
            setValue(trkTMsg.trxRgtnDt, this.slsDt);
            setValue(trkTMsg.updFldTxt, IWR_COND_CD);
            setValue(trkTMsg.oldValTxt, oldIwrCondCd);
            setValue(trkTMsg.newValTxt, tMsg.iwrCondCd);
            setValue(trkTMsg.updUsrId, BATCH_ID);
            setValue(trkTMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.create(trkTMsg);
            
            if (EZDTBLAccessor.RTNCD_NORMAL.equals(trkTMsg.getReturnCode())) {
                insCnt++;
            }
        }
        return insCnt;
    }
// END 2016/11/09 N.Arai [QC#15829, MOD]

    private SVC_MACH_MSTRTMsg createTMsg(ResultSet rs) throws SQLException {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        return tMsg;
    }

    private int updateSvcMachMstr(List<SVC_MACH_MSTRTMsg> tMsgList) {
        List<SVC_MACH_MSTRTMsg> updTMsgList = new ArrayList<SVC_MACH_MSTRTMsg>(tMsgList.size());

        for (SVC_MACH_MSTRTMsg tMsg : tMsgList) {
            tMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.iwrCondCd, IWR_COND.NOT_COMMUNICATING);
            updTMsgList.add(tMsg);
        }

        return S21FastTBLAccessor.update(updTMsgList.toArray(new SVC_MACH_MSTRTMsg[updTMsgList.size()]));
    }

    private void sendMail(String message) {

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!fromAddrList.isEmpty()) {
            from = fromAddrList.get(0);
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        // Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
}
