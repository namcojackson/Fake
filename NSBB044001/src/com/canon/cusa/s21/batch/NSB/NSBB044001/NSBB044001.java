/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB044001;

import static com.canon.cusa.s21.batch.NSB.NSBB044001.constant.NSBB044001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.SVC_BAT_ERR_LOGTMsg;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
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
 * Create Follow Up Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         A.Kohinata      Create          N/A
 * 2017/01/18   Hitachi         K.Kitachi       Update          CSA QC#15818
 * 2018/06/05   Fujitsu         T.Murai         Update          QC#25675
 * </pre>
 */

public class NSBB044001 extends S21BatchMain {

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

    /** Process Time Stamp */
    private String procTs = null;

    /** Sales Date */
    private String salesDate = null;

    /** Service Problem Type */
    private String svcPblmTp = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** SVC_BAT_ERR_LOGTMsg list */
    private List<SVC_BAT_ERR_LOGTMsg> svcBatErrLogTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);

        // Get IWR Service Problem Type
        this.svcPblmTp = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_IWR_SVC_PBLM_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcPblmTp)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_IWR_SVC_PBLM_TP });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {

        // TODO
        // Add Start 2018/06/05 QC#25675 performance Check
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        // Add End 2018/06/05 QC#25675 performance Check
        createFollowUpCall();

        // TODO
        // Add Start 2018/06/05 QC#25675 performance Check
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:createFollowUpCall]End [%s]", sw.getElapsedMilliSec()));
        sw = new S21StopWatch();
        sw.start();
        // Add End 2018/06/05 QC#25675 performance Check
        if (this.errorCount > 0) {
            termCd = TERM_CD.WARNING_END;
            insertSvcBatErrLog();
            sendMail();
        }
        // TODO
        // Add Start 2018/06/05 QC#25675 performance Check
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:hasError]End [%s]", sw.getElapsedMilliSec()));
        // Add End 2018/06/05 QC#25675 performance Check
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB044001().executeBatch(NSBB044001.class.getSimpleName());
    }

    private void createFollowUpCall() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // TODO
            // Add Start 2018/06/05 QC#25675 performance Check
            S21StopWatch sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/05 QC#25675 performance Check
            stmt = getSvcMachMstr();
            rs = stmt.executeQuery();
            // TODO
            // Add Start 2018/06/05 QC#25675 performance Check
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getSvcMachMstr]End [%s]", sw.getElapsedMilliSec()));
            sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/05 QC#25675 performance Check
            Map<String, Object> ctacPsnMap;

            while (rs.next()) {
                ctacPsnMap = getCtacPsn(rs);

                // call Fsr Update API
                if (callFsrUpdateApi(rs, ctacPsnMap)) {
                    commit();
                    this.normalCount++;
                } else {
                    rollback();
                    this.errorCount++;
                }
            }
            // TODO
            // Add Start 2018/06/05 QC#25675 performance Check
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:callFsrUpdateApi]End [%s]", sw.getElapsedMilliSec()));
            // Add End 2018/06/05 QC#25675 performance Check
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private boolean callFsrUpdateApi(ResultSet rs, Map<String, Object> ctacPsnMap) throws SQLException {

        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        setValue(apiPMsg.userId, BATCH_ID);
        setValue(apiPMsg.slsDt, this.salesDate);
        setValue(apiPMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.firstProdCtrlCd, (String) ctacPsnMap.get("FIRST_PROD_CTRL_CD"));
        setValue(apiPMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        setValue(apiPMsg.custTelNum, (String) ctacPsnMap.get("TEL_NUM"));
        setValue(apiPMsg.svcCustAttnTxt, (String) ctacPsnMap.get("CTAC_PSN_NM"));
        setValue(apiPMsg.custEmlAddr, (String) ctacPsnMap.get("EML_ADDR"));
        setValue(apiPMsg.dsSvcCallTpCd, DS_SVC_CALL_TP.SYSTEMS);
        setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP.NO_CHRGE);
        setValue(apiPMsg.svcTaskRcvDt, this.procTs.substring(0, SUB_STR_POS_8));
        setValue(apiPMsg.svcTaskRcvTm, this.procTs.substring(SUB_STR_POS_8, SUB_STR_POS_14));
        setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.svcPblmTpCd, this.svcPblmTp);
        setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.IWR);
        setValue(apiPMsg.svcCustCllrTxt, API_SVC_CUST_CLLR_TXT);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(rs, xxMsgId);
            }
            return false;
        }
        return true;
    }

    private PreparedStatement getSvcMachMstr() throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("iwrCondNotCommu", IWR_COND.NOT_COMMUNICATING);
        paramMap.put("dsSvcCallTpSystems", DS_SVC_CALL_TP.SYSTEMS);
        paramMap.put("svcTaskStsOpen", SVC_TASK_STS.OPEN);
        paramMap.put("svcTaskStsInProcess", SVC_TASK_STS.IN_PROCESS);
        // START 2017/01/18 K.Kitachi [QC#15818, ADD]
        paramMap.put("svcMachTpIsAcc", SVC_MACH_TP.ACCESSORY);
        // END 2017/01/18 K.Kitachi [QC#15818, ADD]

        return this.ssmLLClient.createPreparedStatement("getSvcMachMstr", paramMap, getExecPrm());
    }

    private S21SsmExecutionParameter getExecPrm() {

        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(MAX_FETCH_SIZE);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private Map<String, Object> getCtacPsn(ResultSet rs) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        paramMap.put("svcCtacTpDelivery", SVC_CTAC_TP.DELIVERY_CONTACT);
        paramMap.put("dsCtacPntTpMobile", DS_CTAC_PNT_TP.PHONE_MOBILE);
        paramMap.put("dsCtacPntTpWork", DS_CTAC_PNT_TP.PHONE_WORK);
        paramMap.put("dsCtacPntTpEMail", DS_CTAC_PNT_TP.EMAIL_WORK);
        paramMap.put("salesDate", this.salesDate);
        paramMap.put("maxEffThruDt", MAX_EFF_THRU_DT);
        paramMap.put("defaultTelNum", DEF_TEL_NUM);
        paramMap.put("defaultEmlAddr", DEF_EML_ADDR);
        paramMap.put("length60", LENGTH_60);

        return (Map<String, Object>) ssmBatchClient.queryObject("getCtacPsn", paramMap);
    }

    private void insertSvcBatErrLog() {

        SVC_BAT_ERR_LOGTMsg[] inMsgArray;
        inMsgArray = new SVC_BAT_ERR_LOGTMsg[this.svcBatErrLogTMsgList.size()];
        S21FastTBLAccessor.insert(this.svcBatErrLogTMsgList.toArray(inMsgArray));
    }

    private void sendMail() {

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
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0135E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String msg : this.errMsgList) {
            msgBuf.append(msg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        // Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    private void addErrMsg(ResultSet rs, String msgId, String... param) throws SQLException {

        String errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        this.errMsgList.add(errMsg);

        SVC_BAT_ERR_LOGTMsg svcBatErrLogTMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(svcBatErrLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcBatErrLogTMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        setValue(svcBatErrLogTMsg.bizAppId, BIZ_APP_ID);
        setValue(svcBatErrLogTMsg.svcBatErrLogTs, this.procTs);
        setValue(svcBatErrLogTMsg.svcBatErrKeyNum_01, rs.getBigDecimal("SVC_MACH_MSTR_PK").toString());
        setValue(svcBatErrLogTMsg.svcBatErrKeyNm_01, KEY_SVC_MACH_MSTR_PK);
        setValue(svcBatErrLogTMsg.svcBatErrMsgTxt, errMsg);
        this.svcBatErrLogTMsgList.add(svcBatErrLogTMsg);
    }
}
