/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB034001;

import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.KEY_SVC_MOD_STS_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.KEY_SVC_TASK_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_TEMPLATE_KEY_DATE;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_TEMPLATE_KEY_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAIL_TEMPLATE_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.NSBM0069E;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.NSBM0120E;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.NSBM0135E;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.STR_COLON;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.SUB_STR_POS_14;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.SUB_STR_POS_8;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.VAR_CHAR_CONST_KEY_SVC_MOD_CANC_TERM_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB034001.constant.NSBB034001Constant.ZZM9000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_MOD_STSTMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SerialRangeCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_OPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Cancel Modification Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         M.Gotou         Create          N/A
 * 2016/05/24   Hitachi         T.Iwamoto       Update          QC#8809
 * 2016/07/19   Hitachi         O.Okuma         Update          QC#11734
 * 2018/01/18   Hitachi         K.Kojima        Update          QC#22394
 * 2018/08/08   Hitachi         K.Kitachi       Update          QC#27460
 * 2019/01/24   Hitachi         K.Kitachi       Update          QC#30041
 * 2023/01/16   Hitachi         S.Dong          Update          QC#60922
 * </pre>
 */

public class NSBB034001 extends S21BatchMain {

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

    /** Service Modification Cancel Term AOT */
    private String svcModCancelTermAot = null;

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
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get Service Modification Cancel Term AOT
        String termAot = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SVC_MOD_CANC_TERM_AOT, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(termAot)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_SVC_MOD_CANC_TERM_AOT });
        }
        Date date = toDate(this.salesDate, DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, Integer.parseInt(termAot));
        this.svcModCancelTermAot = toStringDate(cal.getTime(), DATE_FORMAT);

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {

        cancelModCall();

        if (!errMsgList.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            insertSvcBatErrLog();
            sendMail();
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
        new NSBB034001().executeBatch(NSBB034001.class.getSimpleName());
    }

    private void cancelModCall() {

        // 2.(1).1) get Target Service Modefication Info
        List<CancelModificationCallBean> svcModInfoBeanList = getTargetSvcModInfo();

        for (CancelModificationCallBean svcModInfoBean : svcModInfoBeanList) {

            // 2.(1).2) get Target Service Machine Master Info
            List<CancelModificationCallBean> svcMachMstrInfoBeanList = getTargetSvcMachMstrInfo(svcModInfoBean);

            execCancel(svcMachMstrInfoBeanList);
        }

        // 2.(6) get Target Service Modefication Status Info
        List<CancelModificationCallBean> svcModStsBeanList = getTargetSvcModStsInfo();
        execCancel(svcModStsBeanList);

        // START 2018/01/18 K.Kojima [QC#22394,ADD]
        updateSvcModStsForCancel();
        updateSvcModStsForClose();
        // END 2018/01/18 K.Kojima [QC#22394,ADD]
    }

    private void execCancel(List<CancelModificationCallBean> svcMachMstrInfoBeanList) {

        NSZC043001PMsg apiPMsg;
        NSZC045001PMsg addTaskApiPMsg;
        boolean errFlg = false;
        boolean execFlg = false;

        for (CancelModificationCallBean svcMachMstrInfoBean : svcMachMstrInfoBeanList) {

            // 2.(2) select cancel API
            List<CancelModificationCallBean> svcTaskBeanList = getTargetSvcTaskInfo(svcMachMstrInfoBean);

            for (CancelModificationCallBean svcTaskBean : svcTaskBeanList) {
                if (svcTaskBean.getSvcTaskStsCd().equals(SVC_TASK_STS.CANCELLED)) {
                    // 2.(5) update SVC_MOD_STS
                    if (!updateSvcModSts(svcMachMstrInfoBean)) {
                        this.errorCount++;
                        rollback();
                    } else {
                        this.normalCount++;
                        commit();
                    }
                    continue;
                } else if (svcTaskBean.getSvcTaskStsCd().equals(SVC_TASK_STS.DEBRIEF_ERROR)
                        || svcTaskBean.getSvcTaskStsCd().equals(SVC_TASK_STS.COMPLETED)
                        || svcTaskBean.getSvcTaskStsCd().equals(SVC_TASK_STS.CLOSED)) {
                    continue;
                } else if (svcTaskBean.getFsrVisitStsCd().equals(SVC_TASK_STS.ARRIVED)) {
                    break;
                }
                if (svcTaskBeanList.size() == 1) {
                    // 2.(3) Call FSR Update API
                    apiPMsg = new NSZC043001PMsg();
                    errFlg = false;
                    // cancel Service Task Info
                    if (!callFsrUpdateApi(svcMachMstrInfoBean, apiPMsg)) {
                        errFlg = true;
                    } else {
                        execFlg = true;
                    }
                } else if (svcTaskBean.getSvcTaskNum().equals(svcMachMstrInfoBean.getSvcTaskNum())) {
                    // 2.(4) Call Add Task API
                    addTaskApiPMsg = new NSZC045001PMsg();
                    errFlg = false;
                    // cancel Service Task Info
                    if (!callAddTaskApi(svcMachMstrInfoBean, addTaskApiPMsg)) {
                        errFlg = true;
                    } else {
                        execFlg = true;
                    }
                } else {
                    continue;
                }
                if (execFlg) {
                    // 2.(5) update SVC_MOD_STS
                    if (!updateSvcModSts(svcMachMstrInfoBean)) {
                        errFlg = true;
                    }
                    execFlg = false;
                }
                if (errFlg) {
                    this.errorCount++;
                    rollback();
                } else {
                    this.normalCount++;
                    commit();
                }
            }
        }
    }

    private List<CancelModificationCallBean> getTargetSvcModInfo() {

        List<CancelModificationCallBean> beanList = new ArrayList<CancelModificationCallBean>();
        List<Map<String, Object>> svcModInfoList;

        try {
            svcModInfoList = getSvcMod();
            for (int i = 0; i < svcModInfoList.size(); i++) {
                Map<String, Object> svcModInfo = svcModInfoList.get(i);
                CancelModificationCallBean bean = setSvcModInfoBean(svcModInfo);
                beanList.add(bean);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return beanList;
    }

    private List<CancelModificationCallBean> getTargetSvcMachMstrInfo(CancelModificationCallBean svcModInfoBean) {

        List<CancelModificationCallBean> beanList = new ArrayList<CancelModificationCallBean>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fromSerNum = svcModInfoBean.getSvcModFromSerNum();
        String toSerNum = svcModInfoBean.getSvcModToSerNum();

        try {
            stmt = getSvcMachMstr(svcModInfoBean);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CancelModificationCallBean bean = setSvcMachMstrInfoBean(rs);

                // Check SerNum Range
                if (NSXC002001SerialRangeCheck.isCheckSerialNum(fromSerNum, toSerNum, bean.getSerNum())) {
                    beanList.add(bean);
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return beanList;
    }

    private List<CancelModificationCallBean> getTargetSvcModStsInfo() {

        List<CancelModificationCallBean> beanList = new ArrayList<CancelModificationCallBean>();
        List<Map<String, Object>> svcModStsInfoList;

        try {
            svcModStsInfoList = getSvcModSts();
            for (int i = 0; i < svcModStsInfoList.size(); i++) {
                Map<String, Object> svcModStsInfo = svcModStsInfoList.get(i);
                CancelModificationCallBean bean = setSvcModStsInfoBean(svcModStsInfo);
                beanList.add(bean);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return beanList;
    }

    private List<CancelModificationCallBean> getTargetSvcTaskInfo(CancelModificationCallBean svcMachMstrInfoBean) {

        List<CancelModificationCallBean> beanList = new ArrayList<CancelModificationCallBean>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getSvcTask(svcMachMstrInfoBean);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CancelModificationCallBean bean = setSvcTaskInfoBean(rs);
                beanList.add(bean);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return beanList;
    }

    private boolean callFsrUpdateApi(CancelModificationCallBean svcMachMstrInfoBean, NSZC043001PMsg apiPMsg) {

        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_CANCEL_FSR);
        // mod start 2016/05/24 CSA Defect#8809
        setValue(apiPMsg.slsDt, this.salesDate);
        // mod end 2016/05/24 CSA Defect#8809
        setValue(apiPMsg.userId, BATCH_ID);
        setValue(apiPMsg.fsrNum, svcMachMstrInfoBean.getFsrNum());
        setValue(apiPMsg.taskList.no(0).svcTaskNum, svcMachMstrInfoBean.getSvcTaskNum());
        setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(svcMachMstrInfoBean, xxMsgId);
            }
            return false;
        }
        return true;
    }

    private boolean callAddTaskApi(CancelModificationCallBean svcMachMstrInfoBean, NSZC045001PMsg addTaskApiPMsg) {

        setValue(addTaskApiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(addTaskApiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(addTaskApiPMsg.slsDt, this.salesDate);
        setValue(addTaskApiPMsg.fsrNum, svcMachMstrInfoBean.getFsrNum());
        setValue(addTaskApiPMsg.userId, BATCH_ID);
        setValue(addTaskApiPMsg.bypsPrfTechFlg, ZYPConstant.FLG_ON_Y);
        setValue(addTaskApiPMsg.dsSvcCallTpCd, svcMachMstrInfoBean.getDsSvcCallTpCd());
        setValue(addTaskApiPMsg.svcTaskRcvDt, this.procTs.substring(0, SUB_STR_POS_8));
        setValue(addTaskApiPMsg.svcTaskRcvTm, this.procTs.substring(SUB_STR_POS_8, SUB_STR_POS_14));
        setValue(addTaskApiPMsg.xxSvcTaskList.no(0).svcTaskNum, svcMachMstrInfoBean.getSvcTaskNum());
        setValue(addTaskApiPMsg.xxSvcTaskList.no(0).svcCallPrtyCd, svcMachMstrInfoBean.getSvcCallPrtyCd());
        addTaskApiPMsg.xxSvcTaskList.setValidCount(1);

        new NSZC045001().execute(addTaskApiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(addTaskApiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(addTaskApiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(svcMachMstrInfoBean, xxMsgId);
            }
            return false;
        }
        return true;
    }

    private boolean updateSvcModSts(CancelModificationCallBean getTargetSvcTaskInfo) {

        BigDecimal svcModStsPk = getTargetSvcTaskInfo.getSvcModStsPk();

        SVC_MOD_STSTMsg svcModStsTMsg = new SVC_MOD_STSTMsg();
        setValue(svcModStsTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcModStsTMsg.svcModStsPk, svcModStsPk);
        svcModStsTMsg = (SVC_MOD_STSTMsg) EZDTBLAccessor.findByKeyForUpdate(svcModStsTMsg);

        if (svcModStsTMsg == null) {
            addErrMsg(getTargetSvcTaskInfo, NSBM0120E, new String[] {"SVC_MOD_STS", createFaileMsg("SVC_MOD_STS_PK", svcModStsPk) });
            return false;
        }

        // START 2018/01/18 K.Kojima [QC#22394,ADD]
        // START 2019/01/24 K.Kitachi [QC#30041, MOD]
//        if (!SVC_MOD_OPT.CANCEL.equals(svcModStsTMsg.svcModOptCd.getValue())) {
        if (hasValue(svcModStsTMsg.svcModOptCd) && !SVC_MOD_OPT.CANCEL.equals(svcModStsTMsg.svcModOptCd.getValue())) {
        // END 2019/01/24 K.Kitachi [QC#30041, MOD]
            return true;
        }
        // END 2018/01/18 K.Kojima [QC#22394,ADD]

        setValue(svcModStsTMsg.svcModProcStsCd, SVC_MOD_PROC_STS.CANCELLED);
        setValue(svcModStsTMsg.svcModOptCd, SVC_MOD_OPT.CANCEL);
        setValue(svcModStsTMsg.svcModOptDt, this.salesDate);
        EZDTBLAccessor.update(svcModStsTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcModStsTMsg.getReturnCode())) {
            addErrMsg(getTargetSvcTaskInfo, NSBM0120E, new String[] {"SVC_MOD_STS", createFaileMsg("SVC_MOD_STS_PK", svcModStsPk) });
            return false;
        }
        return true;
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

    private void addErrMsg(CancelModificationCallBean svcModStsInfoBean, String msgId, String... param) {

        String errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        // add start 2016/07/19 CSA Defect#11734
        StringBuilder errMsgSb = new StringBuilder(errMsg);
        errMsgSb.append(" [Task #:");
        errMsgSb.append(svcModStsInfoBean.getSvcTaskNum());
        errMsgSb.append("]");
        // add end 2016/07/19 CSA Defect#11734

        // mod start 2016/07/19 CSA Defect#11734
        this.errMsgList.add(errMsgSb.toString());
        // mod end 2016/07/19 CSA Defect#11734

        SVC_BAT_ERR_LOGTMsg svcBatErrLogTMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(svcBatErrLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcBatErrLogTMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        setValue(svcBatErrLogTMsg.bizAppId, BIZ_APP_ID);
        setValue(svcBatErrLogTMsg.svcBatErrLogTs, this.procTs);
        if (svcModStsInfoBean != null) {
            setValue(svcBatErrLogTMsg.svcBatErrKeyNum_01, svcModStsInfoBean.getSvcModStsPk().toString());
            setValue(svcBatErrLogTMsg.svcBatErrKeyNm_01, KEY_SVC_MOD_STS_PK);
            setValue(svcBatErrLogTMsg.svcBatErrKeyNum_02, svcModStsInfoBean.getSvcTaskNum().toString());
            setValue(svcBatErrLogTMsg.svcBatErrKeyNm_02, KEY_SVC_TASK_NUM);
        }
        setValue(svcBatErrLogTMsg.svcBatErrMsgTxt, errMsg);
        this.svcBatErrLogTMsgList.add(svcBatErrLogTMsg);
    }

    private String createFaileMsg(String keyName, BigDecimal keyValue) {

        StringBuffer sb = new StringBuffer(keyName);
        sb.append(STR_COLON);
        sb.append(keyValue.toString());
        return sb.toString();
    }

    private CancelModificationCallBean setSvcModInfoBean(Map<String, Object> svcModInfo) {

        CancelModificationCallBean bean = new CancelModificationCallBean();
        bean.setSvcModPlnId((String) svcModInfo.get("SVC_MOD_PLN_ID"));
        bean.setSvcModPk((BigDecimal) svcModInfo.get("SVC_MOD_PK"));
        bean.setSvcModDtlPk((BigDecimal) svcModInfo.get("SVC_MOD_DTL_PK"));
        bean.setSvcModSerRngPk((BigDecimal) svcModInfo.get("SVC_MOD_SER_RNG_PK"));
        bean.setSvcModFromSerNum((String) svcModInfo.get("SVC_MOD_FROM_SER_NUM"));
        bean.setSvcModToSerNum((String) svcModInfo.get("SVC_MOD_TO_SER_NUM"));
        bean.setMdseCd((String) svcModInfo.get("MDSE_CD"));
        bean.setLgSerNum((BigDecimal) svcModInfo.get("LG_SER_NUM"));
        bean.setOrdTakeMdseCd((String) svcModInfo.get("ORD_TAKE_MDSE_CD"));
        return bean;
    }

    private CancelModificationCallBean setSvcMachMstrInfoBean(ResultSet rs) throws SQLException {

        CancelModificationCallBean bean = new CancelModificationCallBean();
        bean.setSerNum(rs.getString("SER_NUM"));
        bean.setSvcModStsPk(rs.getBigDecimal("SVC_MOD_STS_PK"));
        bean.setSvcTaskNum(rs.getString("SVC_TASK_NUM"));
        bean.setFsrNum(rs.getString("FSR_NUM"));
        bean.setDsSvcCallTpCd(rs.getString("DS_SVC_CALL_TP_CD"));
        bean.setSvcCallPrtyCd(rs.getString("SVC_CALL_PRTY_CD"));
        return bean;
    }

    private CancelModificationCallBean setSvcModStsInfoBean(Map<String, Object> svcModStsInfo) {

        CancelModificationCallBean bean = new CancelModificationCallBean();
        bean.setSvcModStsPk((BigDecimal) svcModStsInfo.get("SVC_MOD_STS_PK"));
        bean.setSvcTaskNum((String) svcModStsInfo.get("SVC_TASK_NUM"));
        bean.setFsrNum((String) svcModStsInfo.get("FSR_NUM"));
        bean.setDsSvcCallTpCd((String) svcModStsInfo.get("DS_SVC_CALL_TP_CD"));
        bean.setSvcCallPrtyCd((String) svcModStsInfo.get("SVC_CALL_PRTY_CD"));
        return bean;
    }

    private CancelModificationCallBean setSvcTaskInfoBean(ResultSet rs) throws SQLException {

        CancelModificationCallBean bean = new CancelModificationCallBean();
        bean.setSvcTaskNum(rs.getString("SVC_TASK_NUM"));
        bean.setSvcTaskStsCd(rs.getString("SVC_TASK_STS_CD"));
        bean.setFsrVisitStsCd(rs.getString("FSR_VISIT_STS_CD"));
        return bean;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcMod() throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("fromDt", this.svcModCancelTermAot);
        paramMap.put("toDt", this.salesDate);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcMod", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcModSts() throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("cancelCd", SVC_MOD_OPT.CANCEL);
        paramMap.put("fromDt", this.svcModCancelTermAot);
        paramMap.put("toDt", this.salesDate);
        paramMap.put("Complete", SVC_TASK_STS.COMPLETED);
        paramMap.put("Close", SVC_TASK_STS.CLOSED);
        paramMap.put("Cancel", SVC_TASK_STS.CANCELLED);
        paramMap.put("Arrived", SVC_TASK_STS.ARRIVED);
        // START 2018/01/18 K.Kojima [QC#22394,ADD]
        paramMap.put("svcModProcStsOpen", SVC_MOD_PROC_STS.OPEN);
        paramMap.put("adminCd", SVC_MOD_OPT.ADMIN);
        paramMap.put("remanCd", SVC_MOD_OPT.REMAN);
        paramMap.put("salesDate", this.salesDate);
        // END 2018/01/18 K.Kojima [QC#22394,ADD]

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcModSts", paramMap);
    }

    private PreparedStatement getSvcMachMstr(CancelModificationCallBean svcModInfoBean) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        // START 2018/08/08 K.Kitachi [QC#27460, DEL]
//        if (hasValue(svcModInfoBean.getOrdTakeMdseCd())) {
//            paramMap.put("mdseCd01", svcModInfoBean.getMdseCd() + STR_PERCENT);
//        } else {
//            paramMap.put("mdseCd02", svcModInfoBean.getMdseCd());
//        }
        // END 2018/08/08 K.Kitachi [QC#27460, DEL]
        paramMap.put("lgSerNum", svcModInfoBean.getLgSerNum());
        paramMap.put("svcModDtlPk", svcModInfoBean.getSvcModDtlPk());

        return this.ssmLLClient.createPreparedStatement("getSvcMachMstr", paramMap, getExecPrm());
    }

    private PreparedStatement getSvcTask(CancelModificationCallBean svcModInfoBean) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("fsrNum", svcModInfoBean.getFsrNum());

        return this.ssmLLClient.createPreparedStatement("getSvcTask", paramMap, getExecPrm());
    }

    private S21SsmExecutionParameter getExecPrm() {

        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(MAX_FETCH_SIZE);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }
    private static Date toDate(String inDtTm, String inFormat) {
        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
        try {
            return parser.parse(inDtTm);
        } catch (ParseException e) {
            return null;
        }
    }

    private static String toStringDate(Date inDtTm, String inFormat) {
        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
        return parser.format(inDtTm);
    }

    // START 2018/01/18 K.Kojima [QC#22394,ADD]
    private void updateSvcModStsForCancel() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcModProcStsOpen", SVC_MOD_PROC_STS.OPEN);
        // START 2023/01/16 S.Dong [QC#60922, ADD]
        paramMap.put("svcModProcStsClosed", SVC_MOD_PROC_STS.CLOSED);
        // END 2023/01/16 S.Dong [QC#60922, ADD]
        paramMap.put("cancelCd", SVC_MOD_OPT.CANCEL);
        paramMap.put("salesDate", this.salesDate);
        List<BigDecimal> svcModStsPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcModStsForCancel", paramMap);
        if (svcModStsPkList == null || svcModStsPkList.size() == 0) {
            return;
        }
        List<SVC_MOD_STSTMsg> updateTMsgList = new ArrayList<SVC_MOD_STSTMsg>();
        for (BigDecimal svcModStsPk : svcModStsPkList) {
            SVC_MOD_STSTMsg tMsg = new SVC_MOD_STSTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.svcModStsPk, svcModStsPk);
            tMsg = (SVC_MOD_STSTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.svcModProcStsCd, SVC_MOD_PROC_STS.CANCELLED);
            updateTMsgList.add(tMsg);
        }
        if (updateTMsgList.size() == 0) {
            return;
        }
        S21FastTBLAccessor.update(updateTMsgList.toArray(new SVC_MOD_STSTMsg[updateTMsgList.size()]));
    }

    private void updateSvcModStsForClose() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcModProcStsOpen", SVC_MOD_PROC_STS.OPEN);
        // START 2023/01/16 S.Dong [QC#60922, ADD]
        paramMap.put("svcModProcStsCancelled", SVC_MOD_PROC_STS.CANCELLED);
        // END 2023/01/16 S.Dong [QC#60922, ADD]
        paramMap.put("adminCd", SVC_MOD_OPT.ADMIN);
        paramMap.put("remanCd", SVC_MOD_OPT.REMAN);
        paramMap.put("salesDate", this.salesDate);
        List<BigDecimal> svcModStsPkList = (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcModStsForClose", paramMap);
        if (svcModStsPkList == null || svcModStsPkList.size() == 0) {
            return;
        }
        List<SVC_MOD_STSTMsg> updateTMsgList = new ArrayList<SVC_MOD_STSTMsg>();
        for (BigDecimal svcModStsPk : svcModStsPkList) {
            SVC_MOD_STSTMsg tMsg = new SVC_MOD_STSTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.svcModStsPk, svcModStsPk);
            tMsg = (SVC_MOD_STSTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.svcModProcStsCd, SVC_MOD_PROC_STS.CLOSED);
            updateTMsgList.add(tMsg);
        }
        if (updateTMsgList.size() == 0) {
            return;
        }
        S21FastTBLAccessor.update(updateTMsgList.toArray(new SVC_MOD_STSTMsg[updateTMsgList.size()]));
    }
    // END 2018/01/18 K.Kojima [QC#22394,ADD]
}
