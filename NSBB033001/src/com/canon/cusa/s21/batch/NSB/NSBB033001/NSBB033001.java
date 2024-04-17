/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB033001;

import static com.canon.cusa.s21.batch.NSB.NSBB033001.constant.NSBB033001Constant.*;
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

import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_MOD_SER_RNGTMsg;
import business.db.SVC_MOD_STSTMsg;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SerialRangeCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
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
 * Create Modification Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/10   Hitachi         K.Yamada        Update          CSA QC#5225
 * 2016/07/06   Hitachi         A.Kohinata      Update          CSA QC#11352
 * 2016/07/19   Hitachi         O.Okuma         Update          CSA QC#11734
 * 2017/01/18   Hitachi         K.Kitachi       Update          CSA QC#15818
 * 2018/08/08   Hitachi         K.Kitachi       Update          CSA QC#27460
 * 2018/09/13   Hitachi         K.Fujimoto      Update          CSA QC#28063
 * 2018/10/03   Hitachi         T.Tomita        Update          CSA QC#28582
 * </pre>
 */

public class NSBB033001 extends S21BatchMain {

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

    /** Service Modification Call Type */
    private String svcModCallTp = null;

    /** Service Modification Problem Type */
    private String svcModPblmTp = null;

    // 2016/07/06 QC#11352 Add Start
    private String svcModNotExistEmlAddr = null;
    // 2016/07/06 QC#11352 Add End

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

        // Get Service Modification Call Type
        this.svcModCallTp = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SVC_MOD_CALL_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcModCallTp)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_SVC_MOD_CALL_TP });
        }

        // Get Service Modification Problem Type
        this.svcModPblmTp = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SVC_MOD_PBLM_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcModPblmTp)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_SVC_MOD_PBLM_TP });
        }

        // 2016/07/06 QC#11352 Add Start
        this.svcModNotExistEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SVC_MOD_NOT_EXIST_EML_ADDR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcModNotExistEmlAddr)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_SVC_MOD_NOT_EXIST_EML_ADDR });
        }
        // 2016/07/06 QC#11352 Add End

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {

        createModCall();

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
        new NSBB033001().executeBatch(NSBB033001.class.getSimpleName());
    }

    private void createModCall() {

        // get Target Service Modefication Info
        List<CreateModificationCallBean> svcModInfoBeanList = getTargetSvcModInfo();

        for (CreateModificationCallBean svcModInfoBean : svcModInfoBeanList) {

            // get Target Service Machine Master Info
            List<CreateModificationCallBean> svcMachMstrInfoBeanList = getTargetSvcMachMstrInfo(svcModInfoBean);

            NSZC043001PMsg apiPMsg;
            String svcTaskNum = null;
            boolean errFlg = false;

            for (CreateModificationCallBean svcMachMstrInfoBean : svcMachMstrInfoBeanList) {

                apiPMsg = new NSZC043001PMsg();
                svcTaskNum = null;
                errFlg = false;

                // create Service Task Info
                if (callFsrUpdateApi(svcModInfoBean, svcMachMstrInfoBean, apiPMsg)) {
                    if (apiPMsg.taskList.getValidCount() > 0) {
                        svcTaskNum = apiPMsg.taskList.no(0).svcTaskNum.getValue();
                    }
                } else {
                    rollback();
                    errFlg = true;
                }

                // insert SVC_MOD_STS
                if (!errFlg) {
                    if (!insertSvcModSts(svcModInfoBean, svcMachMstrInfoBean, svcTaskNum)) {
                        errFlg = true;
                    }
                }

                if (errFlg) {
                    this.errorCount++;
                } else {
                    this.normalCount++;
                }
                commit();
            }
            // update SVC_MOD_SER_RNG
            // Mod Start 2018/10/03 QC#28582
//            updateSvcModSerRng(svcModInfoBean);
            if (updateSvcModSerRng(svcModInfoBean)) {
                commit();
            } else {
                rollback();
            }
            // Mod End 2018/10/03 QC#28582
        }
    }

    private List<CreateModificationCallBean> getTargetSvcModInfo() {

        List<CreateModificationCallBean> beanList = new ArrayList<CreateModificationCallBean>();
        List<Map<String, Object>> svcModInfoList;

        svcModInfoList = getSvcMod();
        for (int i = 0; i < svcModInfoList.size(); i++) {
            Map<String, Object> svcModInfo = svcModInfoList.get(i);
            CreateModificationCallBean bean = setSvcModInfoBean(svcModInfo);

            // Check LastBatExecTs
            String lastBatExecTs = bean.getLastBatExecTs();
            // Del Start 2018/10/03 QC#28582
//            if (!hasValue(lastBatExecTs)) {
//                beanList.add(bean);
//                continue;
//            }
            // Del End 2018/10/03 QC#28582
            if (ZYPConstant.FLG_ON_Y.equals(bean.getOneTmOnlyFlg()) && hasValue(lastBatExecTs)) {
                continue;
            }
            // Mod Start 2018/10/03 QC#28582
//            String targetTs = this.salesDate + MAX_TM;
//            if (ZYPConstant.FLG_ON_Y.equals(bean.getCratModMlyFlg())) {
//                Date date = toDate(this.salesDate, DATE_FORMAT);
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date);
//                cal.add(Calendar.MONTH, -1);
//                targetTs = toStringDate(cal.getTime(), DATE_FORMAT) + MAX_TM;
//            } else {
//                if (hasValue(bean.getCratModTermAot())) {
//                    targetTs = ZYPDateUtil.addDays(this.salesDate, bean.getCratModTermAot().intValue() * -1) + MAX_TM;
//                }
//            }
//            if (lastBatExecTs.compareTo(targetTs) <= 0) {
//                beanList.add(bean);
//            }
            beanList.add(bean);
            // Mod End 2018/10/03 QC#28582
        }
        return beanList;
    }

    private List<CreateModificationCallBean> getTargetSvcMachMstrInfo(CreateModificationCallBean svcModInfoBean) {

        List<CreateModificationCallBean> beanList = new ArrayList<CreateModificationCallBean>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fromSerNum = svcModInfoBean.getSvcModFromSerNum();
        String toSerNum = svcModInfoBean.getSvcModToSerNum();

        try {
            stmt = getSvcMachMstr(svcModInfoBean);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CreateModificationCallBean bean = setSvcMachMstrInfoBean(rs);

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

    private boolean callFsrUpdateApi(CreateModificationCallBean svcModInfoBean, CreateModificationCallBean svcMachMstrInfoBean, NSZC043001PMsg apiPMsg) {

        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        setValue(apiPMsg.userId, BATCH_ID);
        // add start 2016/03/10 CSA Defect#5225
        setValue(apiPMsg.slsDt, this.salesDate);
        // add end 2016/03/10 CSA Defect#5225
        setValue(apiPMsg.firstProdCtrlCd, svcMachMstrInfoBean.getFirstProdCtrlCd());
        setValue(apiPMsg.svcMachMstrPk, svcMachMstrInfoBean.getSvcMachMstrPk());
        setValue(apiPMsg.custMachCtrlNum, svcMachMstrInfoBean.getCustMachCtrlNum());
        setValue(apiPMsg.serNum, svcMachMstrInfoBean.getSerNum());
        setValue(apiPMsg.custPoNum, svcMachMstrInfoBean.getCustIssPoNum());
        setValue(apiPMsg.custPoDt, svcMachMstrInfoBean.getDsPoExprDt());
        setValue(apiPMsg.dsSvcCallTpCd, this.svcModCallTp);
        setValue(apiPMsg.svcTaskRcvDt, this.procTs.substring(0, SUB_STR_POS_8));
        setValue(apiPMsg.svcTaskRcvTm, this.procTs.substring(SUB_STR_POS_8, SUB_STR_POS_14));
        setValue(apiPMsg.svcModNum, svcModInfoBean.getSvcModPlnId());
        setValue(apiPMsg.svcPblmTpCd, this.svcModPblmTp);
        setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.S21_SYSTEM);
        // 2018/09/13 QC#11734 Add Start
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // 2018/09/13 QC#11734 Add End

        // 2016/07/06 QC#11352 Add Start
        Map<String, Object> emlAddrInfo = getEmlAddr(svcMachMstrInfoBean.getSvcMachMstrPk());
        if (emlAddrInfo != null) {
            setValue(apiPMsg.custTelNum, (String) emlAddrInfo.get("CUST_TEL_NUM"));
            setValue(apiPMsg.custTelExtnNum, (String) emlAddrInfo.get("CUST_TEL_EXTN_NUM"));
            setValue(apiPMsg.svcCustAttnTxt, (String) emlAddrInfo.get("SVC_CUST_ATTN_TXT"));
            setValue(apiPMsg.custEmlAddr, (String) emlAddrInfo.get("CUST_EML_ADDR"));
            setValue(apiPMsg.svcCustCllrTxt, (String) emlAddrInfo.get("SVC_CUST_CLLR_TXT"));
            setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
            setValue(apiPMsg.taskList.no(0).svcCustCllrTelNum, (String) emlAddrInfo.get("SVC_CUST_CLLR_TEL_NUM"));
            setValue(apiPMsg.taskList.no(0).svcCustCllrTelExtnNum, (String) emlAddrInfo.get("SVC_CUST_CLLR_TEL_EXTN_NUM"));
            apiPMsg.taskList.setValidCount(1);
        } else {
            setValue(apiPMsg.custEmlAddr, this.svcModNotExistEmlAddr);
        }
        // 2016/07/06 QC#11352 Add End

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(svcModInfoBean, svcMachMstrInfoBean, xxMsgId);
            }
            return false;
        }
        return true;
    }

    private boolean insertSvcModSts(CreateModificationCallBean svcModInfoBean, CreateModificationCallBean svcMachMstrInfoBean, String svcTaskNum) {

        // 2016/07/19 QC#11734 Mod Start
        BigDecimal svcModStsPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_STS_SQ);
        // 2016/07/19 QC#11734 Mod End
        
        SVC_MOD_STSTMsg svcModStsTMsg = new SVC_MOD_STSTMsg();
        setValue(svcModStsTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcModStsTMsg.svcModStsPk, svcModStsPk);
        setValue(svcModStsTMsg.svcModPk, svcModInfoBean.getSvcModPk());
        setValue(svcModStsTMsg.svcModDtlPk, svcModInfoBean.getSvcModDtlPk());
        setValue(svcModStsTMsg.svcMachMstrPk, svcMachMstrInfoBean.getSvcMachMstrPk());
        setValue(svcModStsTMsg.svcTaskNum, svcTaskNum);
        setValue(svcModStsTMsg.svcModProcStsCd, SVC_MOD_PROC_STS.OPEN);
        S21FastTBLAccessor.insert(svcModStsTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcModStsTMsg.getReturnCode())) {
            addErrMsg(svcModInfoBean, svcMachMstrInfoBean, NSBM0121E, new String[] {"SVC_MOD_STS", createFaileMsg("SVC_MOD_STS_PK", svcModStsPk) });
            return false;
        }
        return true;
    }

    private boolean updateSvcModSerRng(CreateModificationCallBean svcModInfoBean) {

        BigDecimal svcModSerRngPk = svcModInfoBean.getSvcModSerRngPk();

        SVC_MOD_SER_RNGTMsg svcModSerRngTMsg = new SVC_MOD_SER_RNGTMsg();
        setValue(svcModSerRngTMsg.glblCmpyCd, glblCmpyCd);
        setValue(svcModSerRngTMsg.svcModSerRngPk, svcModSerRngPk);
        svcModSerRngTMsg = (SVC_MOD_SER_RNGTMsg) EZDTBLAccessor.findByKeyForUpdate(svcModSerRngTMsg);

        if (svcModSerRngTMsg == null) {
            addErrMsg(svcModInfoBean, null, NSBM0120E, new String[] {"SVC_MOD_SER_RNG", createFaileMsg("SVC_MOD_SER_RNG_PK", svcModSerRngPk) });
            return false;
        }

        setValue(svcModSerRngTMsg.lastBatExecTs, this.salesDate + this.procTs.substring(SUB_STR_POS_8, SUB_STR_POS_14));
        EZDTBLAccessor.update(svcModSerRngTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcModSerRngTMsg.getReturnCode())) {
            addErrMsg(svcModInfoBean, null, NSBM0120E, new String[] {"SVC_MOD_SER_RNG", createFaileMsg("SVC_MOD_SER_RNG_PK", svcModSerRngPk) });
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

    private void addErrMsg(CreateModificationCallBean svcModInfoBean, CreateModificationCallBean svcMachMstrInfoBean, String msgId, String... param) {

        String errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        // 2016/07/19 QC#11734 Add Start
        StringBuilder errMsgSb = new StringBuilder(errMsg);
        errMsgSb.append(" [Mod Plan ID:");
        errMsgSb.append(svcModInfoBean.getSvcModPlnId());
        errMsgSb.append(" Serial #:");
        errMsgSb.append(svcMachMstrInfoBean.getSerNum());
        errMsgSb.append("]");
        // 2016/07/19 QC#11734 Add End

        // 2016/07/19 QC#11734 Mod Start
        this.errMsgList.add(errMsgSb.toString());
        // 2016/07/19 QC#11734 Mod End

        SVC_BAT_ERR_LOGTMsg svcBatErrLogTMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(svcBatErrLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcBatErrLogTMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        setValue(svcBatErrLogTMsg.bizAppId, BIZ_APP_ID);
        setValue(svcBatErrLogTMsg.svcBatErrLogTs, this.procTs);
        setValue(svcBatErrLogTMsg.svcBatErrKeyNum_01, svcModInfoBean.getSvcModSerRngPk().toString());
        setValue(svcBatErrLogTMsg.svcBatErrKeyNm_01, KEY_SVC_MOD_SER_RNG_PK);
        if (svcMachMstrInfoBean != null) {
            setValue(svcBatErrLogTMsg.svcBatErrKeyNum_02, svcMachMstrInfoBean.getSvcMachMstrPk().toString());
            setValue(svcBatErrLogTMsg.svcBatErrKeyNm_02, KEY_SVC_MACH_MSTR_PK);
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

    private CreateModificationCallBean setSvcModInfoBean(Map<String, Object> svcModInfo) {

        CreateModificationCallBean bean = new CreateModificationCallBean();
        bean.setSvcModPlnId((String) svcModInfo.get("SVC_MOD_PLN_ID"));
        bean.setSvcModPk((BigDecimal) svcModInfo.get("SVC_MOD_PK"));
        bean.setSvcModDtlPk((BigDecimal) svcModInfo.get("SVC_MOD_DTL_PK"));
        bean.setSvcModSerRngPk((BigDecimal) svcModInfo.get("SVC_MOD_SER_RNG_PK"));
        bean.setSvcModFromSerNum((String) svcModInfo.get("SVC_MOD_FROM_SER_NUM"));
        bean.setSvcModToSerNum((String) svcModInfo.get("SVC_MOD_TO_SER_NUM"));
        bean.setMdseCd((String) svcModInfo.get("MDSE_CD"));
        bean.setLgSerNum((BigDecimal) svcModInfo.get("LG_SER_NUM"));
        bean.setLastBatExecTs((String) svcModInfo.get("LAST_BAT_EXEC_TS"));
        bean.setOneTmOnlyFlg((String) svcModInfo.get("ONE_TM_ONLY_FLG"));
        bean.setCratModMlyFlg((String) svcModInfo.get("CRAT_MOD_MLY_FLG"));
        bean.setCratModTermAot((BigDecimal) svcModInfo.get("CRAT_MOD_TERM_AOT"));
        bean.setOrdTakeMdseCd((String) svcModInfo.get("ORD_TAKE_MDSE_CD"));
        return bean;
    }

    private CreateModificationCallBean setSvcMachMstrInfoBean(ResultSet rs) throws SQLException {

        CreateModificationCallBean bean = new CreateModificationCallBean();
        bean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        bean.setCustMachCtrlNum(rs.getString("CUST_MACH_CTRL_NUM"));
        bean.setSerNum(rs.getString("SER_NUM"));
        bean.setCustIssPoNum(rs.getString("CUST_ISS_PO_NUM"));
        bean.setDsPoExprDt(rs.getString("DS_PO_EXPR_DT"));
        bean.setFirstProdCtrlCd(rs.getString("FIRST_PROD_CTRL_CD"));
        return bean;
    }

    private List<Map<String, Object>> getSvcMod() {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("salesDate", this.salesDate);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcMod", paramMap);
    }

    // 2016/07/06 QC#11352 Add Start
    private Map<String, Object> getEmlAddr(BigDecimal svcMachMstrPk) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("svcTaskStsCancel", SVC_TASK_STS.CANCELLED);

        return (Map<String, Object>) ssmBatchClient.queryObject("getEmlAddr", paramMap);
    }
    // 2016/07/06 QC#11352 Add End

    private PreparedStatement getSvcMachMstr(CreateModificationCallBean svcModInfoBean) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        if (hasValue(svcModInfoBean.getOrdTakeMdseCd())) {
            paramMap.put("mdseCd01", svcModInfoBean.getMdseCd() + STR_PERCENT);
        } else {
            paramMap.put("mdseCd02", svcModInfoBean.getMdseCd());
        }
        paramMap.put("lgSerNum", svcModInfoBean.getLgSerNum());
        paramMap.put("svcModDtlPk", svcModInfoBean.getSvcModDtlPk());
        paramMap.put("svcModNum", svcModInfoBean.getSvcModPlnId());
        paramMap.put("svcMachMstrStsW4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        paramMap.put("svcMachMstrStsIstl", SVC_MACH_MSTR_STS.INSTALLED);
        // START 2017/01/18 K.Kitachi [QC#15818, ADD]
        paramMap.put("svcMachTpIsAcc", SVC_MACH_TP.ACCESSORY);
        // END 2017/01/18 K.Kitachi [QC#15818, ADD]
        // START 2018/08/08 K.Kitachi [QC#27460, ADD]
        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
        paramMap.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
        // END 2018/08/08 K.Kitachi [QC#27460, ADD]

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

    // Del Start 2018/10/03 QC#28582
//    private static Date toDate(String inDtTm, String inFormat) {
//        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
//        try {
//            return parser.parse(inDtTm);
//        } catch (ParseException e) {
//            return null;
//        }
//    }
//
//    private static String toStringDate(Date inDtTm, String inFormat) {
//        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
//        return parser.format(inDtTm);
//    }
    // Del End 2018/10/03 QC#28582
}
