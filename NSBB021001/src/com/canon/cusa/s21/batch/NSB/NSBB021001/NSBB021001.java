/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB021001;

import static com.canon.cusa.s21.batch.NSB.NSBB021001.constant.NSBB021001Constant.*;

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

import parts.common.EZDItemAttribute;
import parts.common.EZDValidatorException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.FSR_EVENTTMsg;
import business.db.S21_PSNTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
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
 * <pre>
 * Alert Cross Service Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/21   Hitachi         O.Okuma         Create          N/A
 * 2016/07/26   Hitachi         O.Okuma         Update          QC#12439
 * 2016/08/05   Hitachi         T.Tomita        Update          QC#12436
 * 2018/10/17   Hitachi         K.Fujimoto      Update          QC#28835
 * 2019/07/11   Hitachi         T.Kanasaka      Update          QC#51040
 * </pre>
 */

public class NSBB021001 extends S21BatchMain {

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

    /** Commit Number */
    private int commitNumber = 0;

    /** Process Time Stamp */
    private String procTs = null;

    /** Process Time Stamp(yyyyMMddHHmmss) */
    private String procTsUntilSec = null;

    /** Sales Date */
    private String salesDate = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** Initial Interval Minute */
    private BigDecimal initIntvlMin = null;

    /** Retry Flag */
    private String retryFlg = null;

    /** Retry Interval Minute */
    private BigDecimal retryIntvlMin = null;

    /** Send To List */
    private List<List<String>> sendToList = new ArrayList<List<String>>();

    /** CRS_SVC_PSN_TP */
    private String crsSvcPsnTp = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0392E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!hasValue(this.salesDate)) {
            // mod start 2016/08/05 CSA Defect#12436
            throw new S21AbendException(NSZM0392E, new String[] {"Sales Date" });
            // mod end 2016/08/05 CSA Defect#12436
        }

        // Get DS_COND_CONST
        this.initIntvlMin = getInitIntvlMin();
        if (!ZYPCommonFunc.hasValue(this.initIntvlMin)) {
            throw new S21AbendException(NSBM0135E, new String[] {TBL_NM_DS_COND_CONST, DS_COND_CONST_CD_INIT_INTVL_MIN });
        }

        this.retryFlg = getRentyFlg();
        if (!ZYPCommonFunc.hasValue(this.retryFlg)) {
            throw new S21AbendException(NSBM0135E, new String[] {TBL_NM_DS_COND_CONST, DS_COND_CONST_CD_RE_NTFY_FLG });
        }

        this.retryIntvlMin = getRentryIntvlMin();
        if (!ZYPCommonFunc.hasValue(this.retryIntvlMin)) {
            throw new S21AbendException(NSBM0135E, new String[] {TBL_NM_DS_COND_CONST, DS_COND_CONST_CD_RE_NTFY_INTVL_MIN });
        }

        setSendToList();
        if (this.sendToList == null || this.sendToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {TBL_NM_DS_COND_CONST, DS_COND_CONST_GRP_ID_SEND_TO });
        }

        // Get CRS_SVC_PSN_TP
        this.crsSvcPsnTp = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CRS_SVC_PSN_TP, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(crsSvcPsnTp)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CRS_SVC_PSN_TP });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_FETCH_SIZE) {
            this.commitNumber = MAX_FETCH_SIZE;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        this.procTsUntilSec = this.procTs.substring(0, SUB_STR_POS_14);
    }

    @Override
    protected void mainRoutine() {

        doProcess();

        if (!errMsgList.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            postMail(getAddressList(MAIL_GROUP_ID_TO), setMailTemplateParam(MAIL_TEMPLATE_ID_1, null));
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
        new NSBB021001().executeBatch(NSBB021001.class.getSimpleName());
    }

    private DS_COND_CONSTTMsgArray getDsCondConst(String dsCondConstGrpId, String dsCondConstCd) {
        DS_COND_CONSTTMsg param = new DS_COND_CONSTTMsg();

        if (dsCondConstCd == null) {
            param.setSQLID("003");
        } else {
            param.setSQLID("001");
            param.setConditionValue("dsCondConstCd01", dsCondConstCd);
        }
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsCondConstGrpId01", dsCondConstGrpId);

        return (DS_COND_CONSTTMsgArray) EZDTBLAccessor.findByCondition(param);
    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            ps = this.ssmLLClient.createPreparedStatement("getSvcTask", getSvcTaskParam(), execParam);
            rs = ps.executeQuery();

            while (rs.next()) {

                AlertCrossServiceRequestBean bean = setAlertCrossServiceRequestBean(rs);

                if (!insertFsrEvnt(bean)) {
                    this.errorCount += 1;
                    rollback();
                    continue;
                } else {
                    this.normalCount += 1;
                }

                for (List<String> sendList : this.sendToList) {
                   boolean sendFlg = false;
                    for (String sendTo : sendList) {
                        List<S21MailAddress> addrToList = getAddressList(getEmlAddr(bean, sendTo));
                        if (addrToList != null && !addrToList.isEmpty()) {
                            postMail(addrToList, setMailTemplateParam(MAIL_TEMPLATE_ID_2, bean));
                            sendFlg = true;
                        }
                    }
                    if (sendFlg) {
                        break;
                    }
                }
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private Map<String, Object> getSvcTaskParam() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("initIntvlMin", this.initIntvlMin);
        params.put("rentryIntvlMin", this.retryIntvlMin);
        params.put("svcDisptEventCd_ACS", SVC_DISPT_EVENT.ALTER_CROSS_SERVICE);
        params.put("procTs", this.procTsUntilSec);
        params.put("slsDate", this.salesDate);
        params.put("dsContrCtrlStsCd_canc", DS_CONTR_CTRL_STS.CANCELLED);
        params.put("rentryFlg", this.retryFlg);
        // START QC#28835 2018/10/17 K.fujimoto[ADD]
        List<String> svcTaskStsCdList = new ArrayList<String>();
        svcTaskStsCdList.add(SVC_TASK_STS.OPEN);
        svcTaskStsCdList.add(SVC_TASK_STS.IN_PROCESS);
        params.put("svcTaskStsCdList", svcTaskStsCdList);
        // START QC#28835 2018/10/17 K.fujimoto[END]
        // START 2019/07/11 T.Kanasaka [QC#51040, ADD]
        params.put("lineBizTpCd_ESS", LINE_BIZ_TP.ESS);
        // END 2019/07/11 T.Kanasaka [QC#51040, ADD]

        String[] crsSvcPsnTpList = this.crsSvcPsnTp.split(STR_CNM);
        params.put("crsSvcPsnTpList", crsSvcPsnTpList);

        return params;
    }

    private BigDecimal getInitIntvlMin() {
        DS_COND_CONSTTMsgArray list = getDsCondConst(DS_COND_CONST_GRP_ID_ML_CTRL, DS_COND_CONST_CD_INIT_INTVL_MIN);

        if (list.getValidCount() > 0) {
            return new BigDecimal(list.no(0).dsCondConstValTxt_01.getValue());
        }
        return null;
    }

    private String getRentyFlg() {
        DS_COND_CONSTTMsgArray list = getDsCondConst(DS_COND_CONST_GRP_ID_ML_CTRL, DS_COND_CONST_CD_RE_NTFY_FLG);

        if (list.getValidCount() > 0) {
            return list.no(0).dsCondConstValTxt_01.getValue();
        }
        return null;
    }

    private BigDecimal getRentryIntvlMin() {
        DS_COND_CONSTTMsgArray list = getDsCondConst(DS_COND_CONST_GRP_ID_ML_CTRL, DS_COND_CONST_CD_RE_NTFY_INTVL_MIN);

        if (list.getValidCount() > 0) {
            return new BigDecimal(list.no(0).dsCondConstValTxt_01.getValue());
        }
        return null;
    }

    private AlertCrossServiceRequestBean setAlertCrossServiceRequestBean(ResultSet rs) throws SQLException {

        AlertCrossServiceRequestBean bean = new AlertCrossServiceRequestBean();

        bean.setSvcTaskNum(rs.getString("SVC_TASK_NUM"));
        bean.setFsrNum(rs.getString("FSR_NUM"));
        bean.setSerNum(rs.getString("SER_NUM"));
        bean.setEzInTime(rs.getString("EZINTIME"));
        bean.setEzInUserID(rs.getString("EZINUSERID"));
        bean.setFsrEventExeTs(rs.getString("FSR_EVENT_EXE_TS"));
        bean.setDsAcctNm(rs.getString("DS_ACCT_NM"));
        bean.setMdlNm(rs.getString("T_MDL_NM"));
        bean.setNoProcTm(rs.getBigDecimal("NO_PROC_TM"));

        return bean;
    }

    private boolean insertFsrEvnt(AlertCrossServiceRequestBean bean) {
        FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();

        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_EVENT_SQ));
        setValue(tMsg.svcDisptEventCd, SVC_DISPT_EVENT.ALTER_CROSS_SERVICE);
        setValue(tMsg.svcTaskNum, bean.getSvcTaskNum());
        setValue(tMsg.fsrNum, bean.getFsrNum());
        setValue(tMsg.fsrEventExeUsrId, this.getClass().getSimpleName());
        setValue(tMsg.fsrEventExeTs, this.procTs);
        setValue(tMsg.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
        setValue(tMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(tMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            // mod start 2016/08/05 CSA Defect#12436
            StringBuffer sb = new StringBuffer();
            sb.append(KEY_FSR_NUM);
            sb.append(STR_COLON);
            sb.append(tMsg.fsrNum.getValue());
            sb.append(STR_CNM);
            sb.append(KEY_SVC_TASK_NUM);
            sb.append(STR_COLON);
            sb.append(tMsg.svcTaskNum.getValue());
            // mod end 2016/08/05 CSA Defect#12436
            String errMsg = S21MessageFunc.clspGetMessage(NSBM0121E, new String[] {TBL_NM_FSR_EVENT, sb.toString() });

            errMsgList.add(errMsg);
            return false;
        }
        return true;
    }

    private void setSendToList() {
        DS_COND_CONSTTMsgArray list = getDsCondConst(DS_COND_CONST_GRP_ID_SEND_TO, null);

        if (list == null) {
            return;
        }


        for (int i = 0; i < list.getValidCount(); i++) {
            ArrayList<String> addList = new ArrayList<String>();
            addSendToList(list.no(i).dsCondConstValTxt_01.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_02.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_03.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_04.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_05.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_06.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_07.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_08.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_09.getValue(), addList);
            addSendToList(list.no(i).dsCondConstValTxt_10.getValue(), addList);

            if (!addList.isEmpty()) {
                this.sendToList.add(addList);
            }
        }
        ArrayList<String> addList = new ArrayList<String>();
        addList.add(MAIL_GROUP_ID_TO);
        this.sendToList.add(addList);
    }

    private void addSendToList(String dsCondConstValTxt, ArrayList<String> list) {
        if (hasValue(dsCondConstValTxt)) {
            list.add(dsCondConstValTxt);
        }
    }

    private String getEmlAddr(AlertCrossServiceRequestBean bean, String dsCondConstValTxt) {

        if (MAIL_GROUP_ID_CREATOR.equals(dsCondConstValTxt)) {
            S21_PSNTMsg inMsg = new S21_PSNTMsg();
            EZDItemAttribute attr = inMsg.getAttr("psnCd");

            try {
                if (!ZYPCommonFunc.isCheckDigits(bean.getEzInUserID(), attr.getDigit(), attr.getFracDigit(), CHECK_DIGITS_TP_NON_NUMERIC)) {
                    return null;
                }
            } catch (EZDValidatorException e) {
                return null;
            }

            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.psnCd, bean.getEzInUserID());

            S21_PSNTMsg outMsg = (S21_PSNTMsg) EZDTBLAccessor.findByKey(inMsg);

            if (outMsg == null) {
                return null;
            }
            return outMsg.emlAddr.getValue();
        }
        return dsCondConstValTxt;
    }

    private void postMail(List<S21MailAddress> addrToList, S21MailTemplate template) {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        if (template == null) {
            return;
        }

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

    private List<S21MailAddress> getAddressList(String mailGrpIdTo) {

        List<S21MailAddress> addrToList = null;

        if (!hasValue(mailGrpIdTo)) {
            return null;
        }

        if (MAIL_GROUP_ID_GRP1.equals(mailGrpIdTo) || MAIL_GROUP_ID_GRP2.equals(mailGrpIdTo)) {
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, mailGrpIdTo);
            addrToList = groupTo.getMailAddress();
        } else {
            S21MailAddress addr = new S21MailAddress(this.glblCmpyCd, mailGrpIdTo);
            addrToList = new ArrayList<S21MailAddress>();
            addrToList.add(addr);
        }

        return  addrToList;
    }

    private S21MailTemplate setMailTemplateParam(String mailTemplateId, AlertCrossServiceRequestBean bean) {

        S21MailTemplate param = new S21MailTemplate(glblCmpyCd, mailTemplateId);

        if (MAIL_TEMPLATE_ID_1.equals(mailTemplateId)) {
            param.setTemplateParameter(MAIL_PARAM_BATCH_ID, this.getClass().getSimpleName());
            param.setTemplateParameter(MAIL_PARAM_ERR_DATE, this.procTsUntilSec);

            String newLine = System.getProperty("line.separator");
            StringBuilder msgBuf = new StringBuilder();
            for (String errMsg : this.errMsgList) {
                msgBuf.append(errMsg);
                msgBuf.append(newLine);
            }
            String errorMessage = msgBuf.toString();

            param.setTemplateParameter(MAIL_PARAM_MESSAGE, errorMessage);

        } else if (MAIL_TEMPLATE_ID_2.equals(mailTemplateId)) {
            param.setTemplateParameter(MAIL_PARAM_DATE, ZYPDateUtil.DateFormatter(this.procTsUntilSec, TIME_STAMP_FORMAT_14, MAIL_DATE_TIME_FORMAT));
            param.setTemplateParameter(MAIL_PARAM_FSR_NUM, bean.getFsrNum());
            param.setTemplateParameter(MAIL_PARAM_SVC_TASK_NUM, bean.getSvcTaskNum());
            param.setTemplateParameter(MAIL_PARAM_SER_NUM, bean.getSerNum());
            param.setTemplateParameter(MAIL_PARAM_MDL_NM, bean.getMdlNm());
            param.setTemplateParameter(MAIL_PARAM_DS_ACCT_NM, bean.getDsAcctNm());
            param.setTemplateParameter(MAIL_PARAM_SVC_TASK_CRAT_DATE, bean.getEzInTime());
            param.setTemplateParameter(MAIL_PARAM_NO_PROC_TIME, bean.getNoProcTm());
        }

        return param;
    }
}
