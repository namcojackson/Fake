/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB087001;

import static com.canon.cusa.s21.batch.NSB.NSBB087001.constant.NSBB087001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.FSRTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC005001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.message.ZYPEZDMessageInfoUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * Auto Call Close Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   Hitachi         Y.Takeno        Create          N/A
 * 07/08/2016   Hitachi         Y.Takeno        Update          QC#9933
 *</pre>
 */
public class NSBB087001 extends S21BatchMain {

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global company code */
    private String glblCmpyCd = "";

    /** Sales Date */
    private String slsDt;

    /** System Time */
    private String systemTime = "";

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Auto Close Minutes */
    private int autoCloseMinutes = 0;

    /** Error Mail List */
    private List<Map<String, Object>> errorMailParamMapList = new ArrayList<Map<String, Object>>();

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args arguments
     */
    public static void main(String[] args) {
        new NSBB087001().executeBatch(NSBB087001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }

        // blank check(Sales Date)
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // VAR_CHAR_CONST : NSBB0870_AUTO_CLOSE_MINUTES
        String strAutoCloseMinutes = ZYPCodeDataUtil.getVarCharConstValue(NSBB0870_AUTO_CLOSE_MINUTES, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(strAutoCloseMinutes)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0870_AUTO_CLOSE_MINUTES });
        }
        this.autoCloseMinutes = Integer.valueOf(strAutoCloseMinutes);

        this.systemTime = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_TS);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            stmt = ssmLLClient.createPreparedStatement("getCloseFsr", getCloseFsrParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (processData(rs)) {
                    this.totalNmlCount++;

                } else {
                    this.totalErrCount++;
                }
            }

            // START 2016/07/08 [MOD, QC#9933]
            if (this.totalErrCount > 0) {
                this.termCd = TERM_CD.WARNING_END;
                insertSvcBatErrLog();
                commit();
                // send error report mail
                sendRequestDataErrorMail();
            }
            // END 2016/07/08 [MOD, QC#9933]

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);

        }
    }

    @Override
    protected void termRoutine() {
        setRecordCount(this.totalNmlCount, this.totalErrCount, (this.totalNmlCount + this.totalErrCount));
        setTermState(this.termCd);
    }

    private boolean processData(ResultSet rs) throws SQLException {

        String fsrNum = (String) rs.getString("FSR_NUM");
        String svcTaskNum = (String) rs.getString("SVC_TASK_NUM");
        if (!checkSvcTask(fsrNum)) {
            // send error report mail
            this.errorMailParamMapList.add(createErrorInfoMap(fsrNum, svcTaskNum, NSBM0167E, new String[] {fsrNum }));
            return false;
        }

        // call NSZC005001 (Debrief mode)
        NSZC005001PMsg pMsg = createNSZC005001PMsg(rs, NSZC005001.MODE_DEBRIEF);
        if (!callApiNSZC005001(pMsg)) {
            // roll-back transaction
            rollback();

            // send error report mail
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            this.errorMailParamMapList.add(createErrorInfoMap(fsrNum, svcTaskNum, msg.getXxMsgid(), msg.getXxMsgPrmArray()));

            return false;
        }

        if (checkFsr(fsrNum)) {
            // call NSZC005001 (Chargable mode)
            pMsg = createNSZC005001PMsg(rs, NSZC005001.MODE_CHARGABLE);
            if (!callApiNSZC005001(pMsg)) {
                // roll-back transaction
                rollback();

                // send error report mail
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                this.errorMailParamMapList.add(createErrorInfoMap(fsrNum, svcTaskNum, msg.getXxMsgid(), msg.getXxMsgPrmArray()));

                return false;
            }
        }

        // commit transaction
        commit();

        return true;
    }

    private Map<String, Object> getCloseFsrParams() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_TS);
        try {
            calendar.setTime(format.parse(this.systemTime));
        } catch (ParseException e) {
            throw new S21AbendException(e);
        }
        calendar.add(Calendar.MINUTE, this.autoCloseMinutes * -1);
        String ezUptime = format.format(calendar.getTime());

        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("fsrVisitStsCd", SVC_TASK_STS.WAITING_FOR_CUSTOMER_ACTION);
        prms.put("ezUptime", ezUptime);
        return prms;
    }

    private boolean checkSvcTask(String fsrNum) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("012");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrNum01", fsrNum);
        inMsg.setConditionValue("svcTaskStsCd01A", SVC_TASK_STS.CANCELLED);
        inMsg.setConditionValue("svcTaskStsCd01B", SVC_TASK_STS.CLOSED);

        int taskRecordCount = EZDTBLAccessor.count(inMsg);
        if (taskRecordCount > 1) {
            return false;
        }
        return true;
    }

    private boolean checkFsr(String fsrNum) {
        FSRTMsg inMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.fsrNum, fsrNum);

        inMsg = (FSRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode()) && SVC_TASK_STS.PENDING_CHARGE.equals(inMsg.fsrStsCd.getValue())) {
            return true;
        }
        return false;
    }

    private NSZC005001PMsg createNSZC005001PMsg(ResultSet rs, String mode) throws SQLException {
        String fsrNum = (String) rs.getString("FSR_NUM");
        String svcTaskNum = (String) rs.getString("SVC_TASK_NUM");
        BigDecimal svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
        String sysDt = S21StringUtil.subStringByLength(this.systemTime, DATE_START_POS, DATE_END_POS);
        String sysTm = S21StringUtil.subStringByLength(this.systemTime, TIME_START_POS, TIME_END_POS);

        NSZC005001PMsg pMsg = new NSZC005001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.userId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.frceUpdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, fsrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitCpltDt, sysDt);
        ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitCpltTm, sysTm);
        ZYPEZDItemValueSetter.setValue(pMsg.svcTrvlTmNum, BigDecimal.ZERO);

        if (NSZC005001.MODE_DEBRIEF.equals(mode)) {
            pMsg.xxVisitTaskList.setValidCount(1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxVisitTaskList.no(0).svcTaskNum, svcTaskNum);

        } else if (NSZC005001.MODE_CHARGABLE.equals(mode)) {
            pMsg.xxChargesList.setValidCount(1);
            ZYPEZDItemValueSetter.setValue(pMsg.xxChargesList.no(0).fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(pMsg.xxChargesList.no(0).svcTaskNum, svcTaskNum);
        }

        return pMsg;
    }

    private boolean callApiNSZC005001(NSZC005001PMsg pMsg) {
        NSZC005001 api = new NSZC005001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private void sendRequestDataErrorMail() {
        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0077E, new String[] {MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        // Create Mail Template Param Map
        Map<String, String> paramMap = createMailTemplateParamMap(this.errorMailParamMapList);

        // Set Parameter
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            template.setTemplateParameter(entry.getKey(), entry.getValue());
        }

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    private Map<String, String> createMailTemplateParamMap(List<Map<String, Object>> errorInfoList) {
        Map<String, String> prmMap = new HashMap<String, String>();
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // ${batchId}
        prmMap.put(MAIL_ITEM_BATCH_ID, this.getClass().getSimpleName());

        // ${errDate}
        prmMap.put(MAIL_ITEM_ERR_DATE, errDate);

        // ${message}
        ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        for (Map<String, Object> errorInfo : errorInfoList) {
            String fsrNum = (String) errorInfo.get(ERROR_ITEM_FSR_NUM);
            String msgId = (String) errorInfo.get(ERROR_ITEM_MSG_ID);
            String[] msgPrms = (String[]) errorInfo.get(ERROR_ITEM_MSG_PRMS);

            sb.append(msgId);
            sb.append(' ');
            sb.append(util.getI18nMessage(null, msgId, msgPrms));
            sb.append(" [FSR#:");
            sb.append(fsrNum);
            sb.append("] ");
            sb.append(newLine);
        }

        prmMap.put(MAIL_ITEM_MESSAGE, sb.toString());

        return prmMap;
    }

    private Map<String, Object> createErrorInfoMap(String fsrNum, String svcTaskNum, String msgId, String[] msgPrms) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put(ERROR_ITEM_FSR_NUM, fsrNum);
        prmMap.put(ERROR_ITEM_SVC_TASK_NUM, svcTaskNum);
        prmMap.put(ERROR_ITEM_TS, this.systemTime);
        prmMap.put(ERROR_ITEM_MSG_ID, msgId);
        prmMap.put(ERROR_ITEM_MSG_PRMS, msgPrms);

        return prmMap;
    }

    private void insertSvcBatErrLog() {
        List<SVC_BAT_ERR_LOGTMsg> tMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>(this.errorMailParamMapList.size());

        for (Map<String, Object> errorMap : this.errorMailParamMapList) {
            SVC_BAT_ERR_LOGTMsg tMsg = createSvcBatErrLogTMsg(errorMap);
            tMsgList.add(tMsg);
        }
        S21FastTBLAccessor.insert(tMsgList.toArray(new SVC_BAT_ERR_LOGTMsg[tMsgList.size()]));
    }

    private SVC_BAT_ERR_LOGTMsg createSvcBatErrLogTMsg(Map<String, Object> paramMap) {
        SVC_BAT_ERR_LOGTMsg tMsg = new SVC_BAT_ERR_LOGTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BAT_ERR_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.bizAppId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrLogTs, this.systemTime);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, (String) paramMap.get(ERROR_ITEM_FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "FSR_NUM");
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNum_01, (String) paramMap.get(ERROR_ITEM_SVC_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrKeyNm_01, "SVC_TASK_NUM");

        ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
        String msgId = (String) paramMap.get(ERROR_ITEM_MSG_ID);
        String[] msgPrms = (String[]) paramMap.get(ERROR_ITEM_MSG_PRMS);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBatErrMsgTxt, util.getI18nMessage(null, msgId, msgPrms));

        return tMsg;
    }
}
