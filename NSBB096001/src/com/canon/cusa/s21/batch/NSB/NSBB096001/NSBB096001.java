/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB096001;

import static com.canon.cusa.s21.batch.NSB.NSBB096001.constant.NSBB096001Constant.*;
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

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_TASKTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Send notification to Duty Manager
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/20   CITS            T.Wada          Create          N/A
 * 2018/07/31   CITS            T.Wada          Update          QC#26035
 * 2018/08/08   CITS            T.Wada          Update          QC#27592
 * 2023/05/11   Hitachi         T.Usui          Update          QC#61069
 * </pre>
 */

public class NSBB096001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Time stamp */
    private String systemTimeStamp = null;

    /** interval minutes to notice */
    private BigDecimal intvlToNtc = null;

    /** From Address **/
    private S21MailAddress from = null;

    /** Mail Date Format */
    private String mailDateTimeFmt = null;

    // QC#27592 Add Start
    /** Sales Date */
    private String slsDt;
    // QC#27592 Add End

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get System Timestamp
        this.systemTimeStamp = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);

        // Get interval to notice
        this.intvlToNtc = ZYPCodeDataUtil.getNumConstValue(CONST_INTERVAL_NTFY_DUTY_MGR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.intvlToNtc)) {
            this.intvlToNtc = BigDecimal.valueOf(DEF_INTERVAL_MINUTES);
        }
        // Get Mail Date Format
        this.mailDateTimeFmt = ZYPCodeDataUtil.getVarCharConstValue(CONST_MAIL_DATE_TIME_FORMAT, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.mailDateTimeFmt)) {
            this.mailDateTimeFmt = NOTICE_MAIL_DATE_TIME_FORMAT;
        }

        // QC#27592 Add Start
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        // QC#27592 Add End

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // main process
        sendNotification();

        if (this.errorCount > 0) {
            termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB096001().executeBatch(NSBB096001.class.getSimpleName());
    }

    /**
     * sendNotification
     */
    private void sendNotification() {

        initMailSettings();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getTargetTask();
            rs = stmt.executeQuery();

            while (rs.next()) {
                List<Map<String, String>> dtyMgrList = getDtyMgr(rs);

                if (dtyMgrList == null || dtyMgrList.isEmpty()) {
                    continue;
                }

                // send mail
                if (!sendMail(rs, dtyMgrList)) {
                    rollback();
                    this.errorCount++;
                    continue;
                }

                // increment notification count
                if (updSvcTask(rs)) {
                    commit();
                    this.normalCount++;
                } else {
                    rollback();
                    this.errorCount++;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getTargetTask
     * @return
     * @throws SQLException
     */
    private PreparedStatement getTargetTask() throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("sysTmStamp", this.systemTimeStamp);
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("svcDisptEventCd", SVC_DISPT_EVENT.TBO);
        paramMap.put("intvlToNtc", this.intvlToNtc);

        paramMap.put("svcMemoTpCd", SVC_MEMO_TP.PROBLEM);

        return this.ssmLLClient.createPreparedStatement("getTargetTask", paramMap, getExecPrm());
    }

    /**
     * getExecPrm
     * @return
     */
    private S21SsmExecutionParameter getExecPrm() {

        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(MAX_FETCH_SIZE);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * getDtyMgr
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Map<String, String>> getDtyMgr(ResultSet rs) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);

        // QC#27592 Del
        //paramMap.put("svcContrBrCd", (String) rs.getString("FLD_SVC_BR_CD"));

        paramMap.put("sysTmStamp", this.systemTimeStamp);
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);

        // QC#27592 Add Start
        paramMap.put("service", BIZ_AREA_ORG.SERVICE);
        paramMap.put("salesDate", slsDt);
        paramMap.put("fldSvcBrCd", (String) rs.getString("FLD_SVC_BR_CD"));
        paramMap.put("svcByLineBizTpCd", (String) rs.getString("SVC_BY_LINE_BIZ_TP_CD"));
        // QC#61069 2023/05/11 Add Start
        paramMap.put("svcByLineBizTpCdPps", SVC_BY_LINE_BIZ_TP_CD_PPS);
        paramMap.put("svcByLineBizTpCdLfs", SVC_BY_LINE_BIZ_TP_CD_LFS);
        // QC#61069 2023/05/11 Add End
        // QC#27592 Add End

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getDtyMgr", paramMap);
    }

    /**
     * initMailSettings
     */
    private void initMailSettings() {
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();

        if (!fromAddrList.isEmpty()) {
            this.from = fromAddrList.get(0);
        }
    }

    /**
     * sendMail
     * @param rs
     * @param dtyMgrList
     * @return
     * @throws SQLException
     */
    private boolean sendMail(ResultSet rs, List<Map<String, String>> dtyMgrList) throws SQLException {

        // Set To Address
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        for (Map<String, String> dtyMgr :  dtyMgrList) {
            String emlAddr = dtyMgr.get("EML_ADDR");
            if (hasValue(emlAddr)) {
                addrToList.add(new S21MailAddress(this.glblCmpyCd, emlAddr));
            }
        }

        if (addrToList == null || addrToList.isEmpty()) {
            return true;
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (template == null || !hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0135E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Mail Body Edit Start
        template.setTemplateParameter("svcTaskNum", rs.getString("SVC_TASK_NUM"));
        template.setTemplateParameter("xtrnlCallTpRefTxt", rs.getString("XTRNL_CALL_TP_REF_TXT"));
        template.setTemplateParameter("locNm", rs.getString("LOC_NM"));
        template.setTemplateParameter("shipToAddress", rs.getString("SHIP_TO_ADDR"));
        template.setTemplateParameter("svcPblmTpC", rs.getString("SVC_PBLM_TP_CD"));
        template.setTemplateParameter("svcPblmDescTxt", rs.getString("SVC_PBLM_TP_DESC_TXT"));
        template.setTemplateParameter("note", rs.getString("SVC_CMNT_TXT"));
        template.setTemplateParameter("svcContrBrDescTxt", rs.getString("SVC_CONTR_BR_DESC_TXT"));

        // Creation Date (formatted as "Mon DD YYYY HH:Mi AM" and set by converting to local time)
        // Convert
        String svcTaskRcvTs = rs.getString("SVC_TASK_RCV_DT") + rs.getString("SVC_TASK_RCV_TM");
        SvcTimeZoneInfo tmInfoForSvcTaskRcv = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, svcTaskRcvTs, rs.getString("CTRY_CD"), rs.getString("POST_CD"));
        String strDt = tmInfoForSvcTaskRcv.getDateTime().substring(0, DT_LEN);
        String strTm = tmInfoForSvcTaskRcv.getDateTime().substring(DT_LEN, TM_LEN);
        // change Format
        String svcTaskRcvTsOt = ZYPDateUtil.DateFormatter(strDt + strTm, MAIL_DATE_TIME_FORMAT_IN, this.mailDateTimeFmt);
        template.setTemplateParameter("svcTaskRcvTs", svcTaskRcvTsOt);

        // TBO Status (formatted as "Mon DD YYYY HH:Mi AM" and set by converting to local time)
        // Convert
        SvcTimeZoneInfo tmInfoForFsrEvEx = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, rs.getString("FSR_EVENT_EXE_TS"), rs.getString("CTRY_CD"), rs.getString("POST_CD"));
        String fsrDt = tmInfoForFsrEvEx.getDateTime().substring(0, DT_LEN);
        String fsrTm = tmInfoForFsrEvEx.getDateTime().substring(DT_LEN, TM_LEN);
        // change Format
        String fsrEventExeTsOt = ZYPDateUtil.DateFormatter(fsrDt + fsrTm, MAIL_DATE_TIME_FORMAT_IN, this.mailDateTimeFmt);
        template.setTemplateParameter("fsrEventExeTs", fsrEventExeTsOt);
        // Mail Body Edit End

        // Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(this.from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        return true;
    }

    /**
     * updSvcTask
     * @param rs
     * @return
     * @throws SQLException
     */
    private boolean updSvcTask(ResultSet rs) throws SQLException {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcTaskNum, rs.getString("SVC_TASK_NUM"));
        inMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

        // QC#26035 Mod Start
        BigDecimal ntfyCnt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(inMsg.svcTaskNtfyCnt)) {
            ntfyCnt = inMsg.svcTaskNtfyCnt.getValue();
        }
        if (hasValue(ntfyCnt) && ntfyCnt.intValue() > 0) {
            if (ntfyCnt.intValue() < MAX_NTFY_CNT) {
                ntfyCnt = ntfyCnt.add(BigDecimal.ONE);
            }
        } else {
            ntfyCnt = BigDecimal.ONE;
        }
        // QC#26035 Mod End
        setValue(inMsg.svcTaskNtfyCnt, ntfyCnt);

        EZDTBLAccessor.update(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            String errMsg = S21StringUtil.concatStrings(inMsg.getReturnCode(), "] [DB_NAME:", inMsg.getTableName(), ", COLUMN:", "SVC_TASK_NUM", "=", rs.getString("SVC_TASK_NUM"));
            S21InfoLogOutput.println(ZZZM9013E, new String[] {errMsg });
            return false;
        }
        return true;
    }
}
