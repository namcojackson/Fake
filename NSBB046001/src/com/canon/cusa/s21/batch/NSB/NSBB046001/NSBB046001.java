/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB046001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_TASK_SEND_RSLTTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *  Send Task Info to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/06   Fujitsu         N.Sugiura       Create          N/A
 * 09/01/2015   Fujitsu         T.Nakamura      Update          BugFix
 * 10/16/2015   Fujitsu         T.Nakamura      Update          BugFix
 * 10/28/2015   Fujitsu         T.Nakamura      Update          BugFix, Vikas & Sigi's request
 * 11/16/2015   Fujitsu         T.Nakamura      Update          BugFix
 * 02/08/2016   Hitachi         Y.Takeno        Update          QC#3727
 * 02/23/2016   Hitachi         Y.Takeno        Update          QC#3727
 * 02/29/2016   Hitachi         T.Iwamoto       Update          QC#4101,4102
 * 03/25/2016   Hitachi         T.Iwamoto       Update          QC#6020,6031
 * 2016/03/31   Hitachi         K.Yamada        Update          CSA QC#5737
 * 2016/04/08   Hitachi         T.Iwamoto       Update          QC#6042,4101,4467
 * 2016/04/13   Hitachi         T.Iwamoto       Update          CSA QC#2567,6718,6888
 * 2016/04/27   Hitachi         T.Iwamoto       Update          CSA QC#7198,7295
 * 05/05/2016   Fujitsu         S.Nakai         Update          CSA QC#7949
 * 05/05/2016   Fujitsu         S.Nakai         Update          CSA QC#7198
 * 05/09/2016   Hitachi         T.Iwamoto       Update          CSA QC#4101,4467,5528,6042
 * 05/11/2016   Hitachi         T.Iwamoto       Update          CSA QC#6042
 * 05/17/2016   Hitachi         T.Iwamoto       Update          CSA QC#6042
 * 06/17/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 10/22/2018   Hitachi         T.Tomita        Update          CSA QC#28565
 * 2019/03/01   Hitachi         K.Fujimoto      Update          CSA QC#29742
 * 2019/08/22   Hitachi         K.Fujimoto      Update          QC#51206
 * 2022/07/25   Hitachi         K.Kim           Update          QC#60282
 *</pre>
 */
public class NSBB046001 extends S21BatchMain implements NSBB046001Constant {

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** global company code */
    private String glblCmpyCd = "";

    /** Sales Date */
    private String slsDt = "";

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Error Messages */
    private Map<String, EZDMessageInfo> errorMessages = new LinkedHashMap<String, EZDMessageInfo>();

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Error Key Map */
    List<Map<String, Object>> errKeyMap = null;

    // Add Start 2018/10/22 QC#28565
    /** Retry Send Process Status */
    String[] rtrySendProcStsArray = null;
    // Add End 2018/10/22 QC#28565

    // Add Start 2019/03/01 K.Fujimoto QC#29742
    /** User Variable1 */
    private String usrVar1;

    /** Multi Count */
    private BigDecimal multiCnt;
    // Add End   2019/03/01 K.Fujimoto QC#29742

    /**
     * @param args parameter
     */
    public static void main(String[] args) {

        new NSBB046001().executeBatch(NSBB046001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }
        // blank check(Sales Date)
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }
        // Add Start 2018/10/22 QC#28565
        String rtrySendProcStsTxt = ZYPCodeDataUtil.getVarCharConstValue(AUTO_RTRY_SEND_PROC_STS, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(rtrySendProcStsTxt)) {
            this.rtrySendProcStsArray = rtrySendProcStsTxt.split(",");
        }
        // Add End 2018/10/22 QC#28565

        // Add Start 2019/03/01 K.Fujimoto QC#29742
        this.multiCnt = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_MULTI_SEND_TASK_TO_CLICK_CNT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.multiCnt)) {
            this.multiCnt = BigDecimal.ONE;
        }
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(NSZM0392E, new String[] {"User Variable1"});
        }
        // Add End   2019/03/01 K.Fujimoto QC#29742

        // Initialize
        this.errKeyMap = new ArrayList<Map<String, Object>>();
    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Send Task Information.
        sendtaskInfo();

        // Send Mail if error or warning occurred.
        if (!errorMessages.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            insertErrorData();
            sendErrorMail();
        }
    }


    @Override
    protected void termRoutine() {

        setRecordCount(totalNmlCount, totalErrCount, (totalNmlCount + totalErrCount));
        setTermState(termCd);
    }

    /**
     * Send Task Information.
     */
    private void sendtaskInfo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // get Task Information.
            stmt = ssmLLClient.createPreparedStatement("getTaskInfo", getTaskInfoParam());
            rs = stmt.executeQuery();

            // Add Start 2018/10/23 QC#28565
            String fsrNum;
            String svcTaskNum;
            String appMsgId;
            // Add End 2018/10/23 QC#28565
            while (rs.next()) {

                // Call Send Task Info API
                NSZC107001PMsg inPMsg = new NSZC107001PMsg();
                ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, this.slsDt);
                ZYPEZDItemValueSetter.setValue(inPMsg.svcTaskNum, rs.getString(SVC_TASK_NUM));
                // START 2022/07/25 [QC#60282, ADD]
                ZYPEZDItemValueSetter.setValue(inPMsg.sendTaskBatchFlg, ZYPConstant.FLG_ON_Y);
                // END 2022/07/25 [QC#60282, ADD]

                NSZC107001 nszc107001API = new NSZC107001();
                nszc107001API.execute(inPMsg, ONBATCH_TYPE.BATCH);
                // Add Start 2018/10/23 QC#28565
                fsrNum = rs.getString(FSR_NUM);
                svcTaskNum = rs.getString(SVC_TASK_NUM);
                appMsgId = getClickErrMsgId(fsrNum, svcTaskNum);
                // Add End 2018/10/23 QC#28565

                if (S21ApiUtil.isXxMsgId(inPMsg)) {
                    // API Result Error/Waring
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(inPMsg);
                    for (S21ApiMessage msg : msgList) {
                        addMessage(rs.getString(SVC_TASK_NUM), rs.getString(FSR_NUM), msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                    totalErrCount++;
                // Add Start 2018/10/23 QC#28565
                } else if (ZYPCommonFunc.hasValue(appMsgId)) {
                    // Click Send Error
                    addMessage(svcTaskNum, fsrNum, appMsgId);
                    totalErrCount++;
                // Add End 2018/10/23 QC#28565
                } else {
                    totalNmlCount++;
                }
                commit();
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Add Error Message.
     * @param svcTaskNum svcTaskNum
     * @param fsrNum fsrNum
     * @param code Message Code
     * @param param Message Parameter
     */
    private void addMessage(String svcTaskNum, String fsrNum, String code, String... param) {
        termCd = TERM_CD.WARNING_END;

        // Add Start 2019/08/22 K.Fujimoto QC#51206
        if (!code.equals(NSZM1362E) && !code.equals(NSZM1363E) && !code.equals(NSZM1364E) && !code.equals(NSZM1365E)) {
            errorMessages.put(svcTaskNum, new EZDMessageInfo(code, param));
            setErrKeyMap(svcTaskNum, fsrNum);
        }
        // Add End   2019/08/22 K.Fujimoto QC#51206
        S21InfoLogOutput.println(code, param);
    }
    /**
     * Send Error Mail.
     */
    private void sendErrorMail() {
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        // Set Parameter
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, errDate);
        // Add Start 2018/10/22 QC#28565
        StringBuilder errMsg = new StringBuilder();
        for (Map<String, Object> entry : errKeyMap) {
            if (errMsg.length() > 0) {
                errMsg.append("\r\n  ");
            }
            errMsg.append("FSR#");
            errMsg.append(entry.get(FSR_NUM));
            errMsg.append(", Task#");
            errMsg.append(entry.get(SVC_TASK_NUM));
            errMsg.append(", Message : ");
            errMsg.append(entry.get(ERROR_MESSAGE));
        }
        template.setTemplateParameter(MAIL_ITEM_MSG, errMsg.toString());
        // Add End 2018/10/22 QC#28565

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }
    private void setErrKeyMap(String svcTaskNum, String fsrNum) {

        Map<String, Object> errKey = new HashMap<String, Object>();
        errKey.put(SVC_TASK_NUM, svcTaskNum);
        errKey.put(FSR_NUM, fsrNum);
        errKey.put(ERROR_MESSAGE, errorMessages.get(svcTaskNum).getMessage());
        this.errKeyMap.add(errKey);
    }
    private void insertErrorData() {
        List<SVC_BAT_ERR_LOGTMsg> inTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();

        for (Map<String, Object> entry : errKeyMap) {
            inTMsgList.add(setCreateErrorValue(entry));
        }

        SVC_BAT_ERR_LOGTMsg[] inMsgArray;
        inMsgArray = new SVC_BAT_ERR_LOGTMsg[inTMsgList.size()];
        S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
    }
    private SVC_BAT_ERR_LOGTMsg setCreateErrorValue(Map<String, Object> entry) {
        SVC_BAT_ERR_LOGTMsg inParam = new SVC_BAT_ERR_LOGTMsg();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogPk, getSvcBatErrLogPk());
        ZYPEZDItemValueSetter.setValue(inParam.bizAppId, BATCH_PROGRAM_ID);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrLogTs, this.currentSystemTs);
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_01, entry.get(FSR_NUM).toString());
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNum_02, entry.get(SVC_TASK_NUM).toString());
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_01, "FSR_NUM");
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrKeyNm_02, "SVC_TASK_NUM");
        ZYPEZDItemValueSetter.setValue(inParam.svcBatErrMsgTxt, entry.get(ERROR_MESSAGE).toString());

        return inParam;
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
     * Get Parameter to Query Unsent Document.
     * @return Query Parameter
     */
    private Map<String, Object> getTaskInfoParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcDisptEvent", SVC_DISPT_EVENT.CANCEL);
        params.put("procStsProcessed", MBL_INTFC_PROC.PROCESSED);
        params.put("procStsError", MBL_INTFC_PROC.ERROR);
        // Add Start 2018/10/22 QC#28565
        params.put("rtrySendProcStsArray", this.rtrySendProcStsArray);
        // Add End 2018/10/22 QC#28565
        // Add Start 2019/03/01 K.Fujimoto QC#29742
        params.put("multiCnt", multiCnt);
        params.put("usrVar1", this.usrVar1);
        // Add End   2019/03/01 K.Fujimoto QC#29742
        return params;
    }
    // Add Start 2018/10/22 QC#28565
    private String getClickErrMsgId(String fsrNum, String svcTaskNum) {
        CLICK_TASK_SEND_RSLTTMsg inMsg = new CLICK_TASK_SEND_RSLTTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.fsrNum, fsrNum);
        setValue(inMsg.svcTaskNum, svcTaskNum);
        inMsg = (CLICK_TASK_SEND_RSLTTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null || PROC_STS.COMPLEATED.equals(inMsg.rtrySendProcStsCd.getValue())) {
            return null;
        }
        return inMsg.appMsgId.getValue();
    }
    // Add End 2018/10/22 QC#28565
}
