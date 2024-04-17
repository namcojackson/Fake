/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB032001;

import static com.canon.cusa.s21.batch.NSA.NSAB032001.constant.NSAB032001Constant.*;
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

import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC054001PMsg;
import business.parts.NSZC056001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC054001.NSZC054001;
import com.canon.cusa.s21.api.NSZ.NSZC056001.NSZC056001;
import com.canon.cusa.s21.api.NSZ.NSZC056001.constant.NSZC056001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 *<pre>
 * Billing Calculation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         K.Kishimoto     Create          N/A
 * 2016/06/08   Hitachi         T.Aoyagi        Update          QC9636
 * 2016/08/22   Hitachi         K.Kishimoto     Update          QC13150
 * 2017/01/30   Hitachi         K.Kojima        Update          QC#15640
 * 01/24/2018   Hitachi         T.Tomita        Update          QC#23684
 * 2018/09/07   Hitachi         K.Kojima        Update          QC#28107
 * 2019/11/14   Hitachi         A.Kohinata      Update          QC#54401
 * 2022/09/20   Hitachi         D.Yoshida       Update          QC#60070
 *</pre>
 */
public class NSAB032001 extends S21BatchMain implements ZYPConstant {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** User Variable1 */
    private String usrVar1;

    /** Sales Date */
    private String salesDate;

    /** Multi Billing Calculation */
    private BigDecimal multiCnt;

    /** Service Billing Calculation Window Days */
    // START 2022/09/20 [QC#60070,DEL]
//    private BigDecimal windowDays;
    // END   2022/09/20 [QC#60070,DEL]

    /** Commit Number */
    private int commitNumber;

    /** Total Count */
    private int totalCount;

    // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
    /** Error Count */
    private int errorCount;
    // END 2016/06/08 T.Aoyagi [QC#9636, ADD]

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchlient = null;

    // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
    /** [@] is not registered.(@) */
    private static final String NSAM0069E = "NSAM0069E";

    /** Business Application ID */
    private static final String BIZ_APP_ID = "NSAB0320";

    /** mail group id for From */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    private static final String MAIL_GROUP_ID_TO = "NSAB0320";

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

    /** From Address List */
    List<S21MailAddress> fromAddrList = null;

    /** To Address List */
    List<S21MailAddress> addrToList = null;

    /** Mail Template */
    S21MailTemplate template = null;

    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();
    // END 2016/06/08 T.Aoyagi [QC#9636, ADD]

    // START 2018/09/07 K.Kojima [QC#28107,ADD]
    /** Log Output Flag */
    private String logOutputFlg = null;
    // END 2018/09/07 K.Kojima [QC#28107,ADD]

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get User Variable1
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(ZZM9000E, new String[] {"User Variable1" });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get Number Constant
        // Mod Start 2018/01/24 QC#23684
        this.multiCnt = ZYPCodeDataUtil.getNumConstValue("NSAB0320MULTI_BLLG_CALC_CNT", this.glblCmpyCd);
        // Mod End 2018/01/24 QC#23684
        if (!hasValue(this.multiCnt)) {
            throw new S21AbendException(ZZZM9006E, new String[] {"VAR_CHAR_CONST" });
        }

        // START 2022/09/20 [QC#60070,DEL]
//        this.windowDays = ZYPCodeDataUtil.getNumConstValue("SVC_BLLG_CALC_WINDOW_DAYS", this.glblCmpyCd);
//        if (!hasValue(this.windowDays)) {
//            throw new S21AbendException(ZZZM9006E, new String[] {"VAR_CHAR_CONST" });
//        }
        // END   2022/09/20 [QC#60070,DEL]

        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        this.logOutputFlg = ZYPCodeDataUtil.getVarCharConstValue("BLLG_CALC_LOG_OUTPUT_FLG", this.glblCmpyCd);
        // END 2018/09/07 K.Kojima [QC#28107,ADD]

        // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
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
        // END 2016/06/08 T.Aoyagi [QC#9636, ADD]

        // Initialize
        this.totalCount = 0;
        // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
        this.errorCount = 0;
        // END 2016/06/08 T.Aoyagi [QC#9636, ADD]
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchlient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        bllgCalc();
    }

    @Override
    protected void termRoutine() {
        // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
        // END 2016/06/08 T.Aoyagi [QC#9636, MOD]
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB032001().executeBatch(NSAB032001.class.getSimpleName());
    }

    private void bllgCalc() {

        // Base Charge
        baseBllgCalc();
        // Usage Charge
        usageBllgCalc();
        // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
        sendMail();
        // END 2016/06/08 T.Aoyagi [QC#9636, ADD]
    }

    private void baseBllgCalc() {
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-baseBllgCalc-getBaseChrgRegFlt start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        Map<String, Object> ssmParamReg = getSsmParamBaseReg();
        baseBllgCalcCommon("getBaseChrgRegFlt", ssmParamReg);
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-baseBllgCalc-getBaseChrgRegFlt end");
        s21InfoLogOutputPrintln("NSAB0320-baseBllgCalc-getBaseChrgAgg start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        Map<String, Object> ssmParamAgg = getSsmParamBaseAgg();
        baseBllgCalcCommon("getBaseChrgAgg", ssmParamAgg);
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-baseBllgCalc-getBaseChrgAgg end");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
    }

    private void usageBllgCalc() {
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-usageBllgCalc-getUsageChrgReg start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        Map<String, Object> ssmParamReg = getSsmParamUsageReg();
        usageBllgCalcReg("getUsageChrgReg", ssmParamReg);
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-usageBllgCalc-getUsageChrgReg end");
        s21InfoLogOutputPrintln("NSAB0320-usageBllgCalc-getUsageChrgFltAgg(Aggregate) start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        Map<String, Object> ssmParamAgg = getSsmParamUsageAgg();
        usageBllgCalcFltAgg("getUsageChrgFltAgg", ssmParamAgg);
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-usageBllgCalc-getUsageChrgFltAgg(Aggregate) end");
        s21InfoLogOutputPrintln("NSAB0320-usageBllgCalc-getUsageChrgFltAgg(Fleet) start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        Map<String, Object> ssmParamFlt = getSsmParamUsageFlt();
        usageBllgCalcFltAgg("getUsageChrgFltAgg", ssmParamFlt);
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSAB0320-usageBllgCalc-getUsageChrgFltAgg(Fleet) end");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
    }
    // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
    private void baseBllgCalcCommon(String sqlId, Map<String, Object> ssmParam) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, ssmParam, execParam);
            rs = stmt.executeQuery();

            int contrCnt = 0;
            String preDsContrNum = null;
            boolean hasErr = false;
            while (rs.next()) {
                // START 2017/01/30 K.Kojima [QC#15640,DEL]
                // contrCnt++;
                // END 2017/01/30 K.Kojima [QC#15640,DEL]
                String curDsContrNum = rs.getString("DS_CONTR_NUM");
                if (preDsContrNum != null && !preDsContrNum.equals(curDsContrNum)) {
                    this.totalCount++;
                    if (hasErr) {
                        this.errorCount++;
                        rollback();
                    } else {
                        commit();
                    }
                    contrCnt = 0;
                    hasErr = false;
                }
                if (!callBllgCalcAPI(rs)) {
                    hasErr = true;
                }
                preDsContrNum = curDsContrNum;
                // START 2017/01/30 K.Kojima [QC#15640,ADD]
                contrCnt++;
                // END 2017/01/30 K.Kojima [QC#15640,ADD]
            }
            if (contrCnt != 0) {
                this.totalCount++;
                if (hasErr) {
                    this.errorCount++;
                    rollback();
                } else {
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void usageBllgCalcReg(String sqlId, Map<String, Object> ssmParam) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, ssmParam, execParam);
            rs = stmt.executeQuery();

            int contrCnt = 0;
            String preDsContrNum = null;
            boolean hasErr = false;
            while (rs.next()) {
                // START 2017/01/30 K.Kojima [QC#15640,DEL]
                // contrCnt++;
                // END 2017/01/30 K.Kojima [QC#15640,DEL]
                String curDsContrNum = rs.getString("DS_CONTR_NUM");
                if (preDsContrNum != null && !preDsContrNum.equals(curDsContrNum)) {
                    this.totalCount++;
                    if (hasErr) {
                        this.errorCount++;
                        rollback();
                    } else {
                        commit();
                    }
                    contrCnt = 0;
                    hasErr = false;
                }
                preDsContrNum = curDsContrNum;
                if (getNotEntryCntReg(rs.getBigDecimal("DS_CONTR_DTL_PK"), rs.getString("NEXT_BLLG_DT")) == 0) {
                    if (!callPreviewBillingAPI(rs)) {
                        hasErr = true;
                    }
                }
                // START 2017/01/30 K.Kojima [QC#15640,ADD]
                contrCnt++;
                // END 2017/01/30 K.Kojima [QC#15640,ADD]
            }
            if (contrCnt != 0) {
                this.totalCount++;
                if (hasErr) {
                    this.errorCount++;
                    rollback();
                } else {
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void usageBllgCalcFltAgg(String sqlId, Map<String, Object> ssmParam) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, ssmParam, execParam);
            rs = stmt.executeQuery();

            int contrCnt = 0;
            String preDsContrNum = null;
            // add start 2019/11/14 QC#54401
            String preNextBillDt = null;
            String preBllgSchdThruDt = null;
            // add end 2019/11/14 QC#54401
            boolean hasErr = false;
            while (rs.next()) {
                // START 2017/01/30 K.Kojima [QC#15640,DEL]
                // contrCnt++;
                // END 2017/01/30 K.Kojima [QC#15640,DEL]
                String curDsContrNum = rs.getString("DS_CONTR_NUM");
                if (preDsContrNum != null && !preDsContrNum.equals(curDsContrNum)) {
                    this.totalCount++;
                    if (hasErr) {
                        this.errorCount++;
                        rollback();
                    } else {
                        commit();
                    }
                    contrCnt = 0;
                    hasErr = false;
                }
                // Del Start 08/22/2016 <QC#13150>
//                if (preDsContrNum != null && preDsContrNum.equals(curDsContrNum)) {
//                    continue;
//                }
                // Del End   08/22/2016 <QC#13150>
                // mod start 2019/11/14 QC#54401
                String curNextBillDt = rs.getString("NEXT_BLLG_DT");
                String curBllgSchdThruDt = rs.getString("BLLG_SCHD_THRU_DT");
                if (getNotEntryCntFltAgg(rs.getBigDecimal("DS_CONTR_PK"), curNextBillDt) == 0) {
                    if (!isSamePeriod(preDsContrNum, preNextBillDt, preBllgSchdThruDt, curDsContrNum, curNextBillDt, curBllgSchdThruDt)) {
                        if (!callPreviewBillingAPIFltAgg(rs)) {
                            hasErr = true;
                        }
                    }
                }
                preDsContrNum = curDsContrNum;
                preNextBillDt = curNextBillDt;
                preBllgSchdThruDt = curBllgSchdThruDt;
                // mod end 2019/11/14 QC#54401
                // START 2017/01/30 K.Kojima [QC#15640,DEL]
                contrCnt++;
                // END 2017/01/30 K.Kojima [QC#15640,DEL]
            }
            if (contrCnt != 0) {
                this.totalCount++;
                if (hasErr) {
                    this.errorCount++;
                    rollback();
                } else {
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    // END 2016/06/08 T.Aoyagi [QC#9636, MOD]
    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> getSsmParamBaseReg() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        List<String> dsContrCatgCdList = new ArrayList<String>();
        dsContrCatgCdList.add(DS_CONTR_CATG.REGULAR);
        dsContrCatgCdList.add(DS_CONTR_CATG.FLEET);
        ssmParam.put("dsContrCatgCd", dsContrCatgCdList);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        return ssmParam;
    }

    private Map<String, Object> getSsmParamBaseAgg() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        return ssmParam;
    }

    private Map<String, Object> getSsmParamUsageReg() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.REGULAR);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        return ssmParam;
    }

    private Map<String, Object> getSsmParamUsageAgg() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        return ssmParam;
    }

    private Map<String, Object> getSsmParamUsageFlt() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        return ssmParam;
    }
    // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
    private boolean callBllgCalcAPI(ResultSet rs) throws SQLException {

        NSZC056001 api = new NSZC056001();
        NSZC056001PMsg pMsg = new NSZC056001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC056001Constant.MODE_BILLING_STAGE);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        setValue(pMsg.baseChrgFlg, FLG_ON_Y);
        setValue(pMsg.usgChrgFlg, FLG_OFF_N);
        setValue(pMsg.baseChrgFlg, FLG_ON_Y);
        setValue(pMsg.usgChrgFlg, FLG_OFF_N);
        setValue(pMsg.estFlg, rs.getString("DS_CONTR_SCHD_EST_FLG"));
        if (hasValue(rs.getString("RVS_SCHD_DT"))) {
            setValue(pMsg.nextBllgDt, rs.getString("RVS_SCHD_DT"));
        } else {
            setValue(pMsg.nextBllgDt, rs.getString("NEXT_BLLG_DT"));
        }
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        setValue(pMsg.xxSetFlg_LG, this.logOutputFlg);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();

            addErrMsg(msgId, msgPrms, rs.getString("DS_CONTR_NUM"), rs.getBigDecimal("DS_CONTR_DTL_PK"));
            return false;
        }
        return true;
    }

    private boolean callPreviewBillingAPI(ResultSet rs) throws SQLException {

        NSZC054001 api = new NSZC054001();
        NSZC054001PMsg pMsg = new NSZC054001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.mtrReadDt, getMtrReadDtByGrpSq(rs.getBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ")));
        setValue(pMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(pMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        setValue(pMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        setValue(pMsg.xxSetFlg_LG, this.logOutputFlg);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();

            addErrMsg(msgId, msgPrms, rs.getString("DS_CONTR_NUM"), rs.getBigDecimal("DS_CONTR_DTL_PK"));
            return false;
        }
        return true;
    }

    private boolean callPreviewBillingAPIFltAgg(ResultSet rs) throws SQLException {

        NSZC054001 api = new NSZC054001();
        NSZC054001PMsg pMsg = new NSZC054001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.mtrReadDt, rs.getString("BLLG_SCHD_THRU_DT"));
        setValue(pMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(pMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        setValue(pMsg.xxSetFlg_LG, this.logOutputFlg);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();

            addErrMsg(msgId, msgPrms, rs.getString("DS_CONTR_NUM"), rs.getBigDecimal("DS_CONTR_DTL_PK"));
            return false;
        }
        return true;
    }
    // END 2016/06/08 T.Aoyagi [QC#9636, MOD]
    private String getMtrReadDtByGrpSq(BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).mtrReadDt.getValue();
    }

    private int getNotEntryCntReg(BigDecimal dsContrDtlPk, String nextBllgDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("nextBllgDt", nextBllgDt);
        BigDecimal notEntryCnt = (BigDecimal) this.ssmBatchlient.queryObject("getNotEntryCntReg", ssmParam);
        return notEntryCnt.intValue();
    }

    // mod start 2019/11/14 QC#54401
    private int getNotEntryCntFltAgg(BigDecimal dsContrPk, String nextBllgDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("agg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("fleet", DS_CONTR_DTL_TP.FLEET);
        ssmParam.put("nextBllgDt", nextBllgDt);
        BigDecimal notEntryCnt = (BigDecimal) this.ssmBatchlient.queryObject("getNotEntryCntFltAgg", ssmParam);
        return notEntryCnt.intValue();
    }
    // mod end 2019/11/14 QC#54401

    // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
    private void addErrMsg(String msgId, String[] msgPrms, String dsContrNum, BigDecimal dsContrDtlPk) {

        String errMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
        StringBuilder sb = new StringBuilder();
        if (hasValue(errMsg)) {
            sb.append(errMsg);
            sb.append("[DS_CONTR_NUM=");
            sb.append(dsContrNum);
            sb.append("]");
            sb.append("[DS_CONTR_DTL_PK=");
            sb.append(dsContrDtlPk);
            sb.append("]");
            this.errMsgList.add(sb.toString());
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
    // END 2016/06/08 T.Aoyagi [QC#9636, ADD]

    // START 2018/09/07 K.Kojima [QC#28107,ADD]
    private void s21InfoLogOutputPrintln(String message) {
        if (this.logOutputFlg != null && this.logOutputFlg.equals(ZYPConstant.FLG_ON_Y)) {
            S21InfoLogOutput.println("[Billing Calculation Log]" + message);
        }
    }
    // END 2018/09/07 K.Kojima [QC#28107,ADD]

    // mod start 2019/11/14 QC#54401
    private boolean isSamePeriod(String preDsContrNum, String preNextBillDt, String preBllgSchdThruDt, String curDsContrNum, String curNextBillDt, String curBllgSchdThruDt) {
        if (!hasValue(preDsContrNum) || !hasValue(preNextBillDt) || !hasValue(preBllgSchdThruDt)) {
            return false;
        }
        if (!preDsContrNum.equals(curDsContrNum)) {
            return false;
        }
        if (!preNextBillDt.equals(curNextBillDt)) {
            return false;
        }
        if (!preBllgSchdThruDt.equals(curBllgSchdThruDt)) {
            return false;
        }
        return true;
    }
    // mod end 2019/11/14 QC#54401

}
