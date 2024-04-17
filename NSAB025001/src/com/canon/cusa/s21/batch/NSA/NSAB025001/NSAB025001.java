/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB025001;

import static com.canon.cusa.s21.batch.NSA.NSAB025001.constant.NSAB025001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NSZC049001PMsg;
import business.parts.NSZC050001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC049001.NSZC049001;
import com.canon.cusa.s21.api.NSZ.NSZC050001.NSZC050001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 * Auto Registers & Deregister unit to UGW
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/09/01   Hitachi         T.Tomita        Update          QC#14071
 *</pre>
 */
public class NSAB025001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String salesDate;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    // START 2016/09/01 T.Tomita [QC#14071, ADD]
    /** Warning Send Flag */
    private String warnSendFlg;
    // END 2016/09/01 T.Tomita [QC#14071, ADD]

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSAM0178E);
        }

        // START 2016/09/01 T.Tomita [QC#14071, ADD]
        this.warnSendFlg = ZYPConstant.FLG_ON_Y;
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSAB0250_WARN_MAIL_SEND_FLG, this.glblCmpyCd);
        if (hasValue(constVal)) {
            this.warnSendFlg = constVal;
        }
        // END 2016/09/01 T.Tomita [QC#14071, ADD]

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
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB025001().executeBatch(NSAB025001.class.getSimpleName());
    }

    /**
     * doProcess
     */
    private void doProcess() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // get request data
            stmt = ssmLLClient.createPreparedStatement("getTargetDataList", getTargetDataListParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (processRequestData(rs)) {
                    this.normalCount++;
                    commit();

                } else {
                    this.errorCount++;
                    rollback();
                }

            }

            if (!errMsgList.isEmpty()) {
                sendMail();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    private Map<String, Object> getTargetDataListParams() {
        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);

        return prms;
    }

    private boolean processRequestData(ResultSet rs) throws SQLException {
        if (isRegister(rs)) {
            // Call NSZC0490
            return callRegisterToUGW(rs);
        } else if (isDeRegister(rs)) {
            // Call NSZC0500
            return callDeRegisterToUGW(rs);
        }
        return false;
    }

    private boolean callRegisterToUGW(ResultSet rs) throws SQLException {
        NSZC049001PMsg nszc049001PMsg = new NSZC049001PMsg();
        setParamForNSZC049001(nszc049001PMsg, rs);

        NSZC049001 apiNSZC049001 = new NSZC049001();
        apiNSZC049001.execute(nszc049001PMsg, ONBATCH_TYPE.BATCH);
        boolean normalFlag = true;
        if (S21ApiUtil.isXxMsgId(nszc049001PMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(nszc049001PMsg);
            // START 2016/09/01 T.Tomita [QC#14071, MOD]
//            for (String xxMsgId : xxMsgIdList) {
//                if (xxMsgId.endsWith("E")) {
//                    normalFlag = false;
//                    break;
//                }
//            }
            normalFlag = isNormalMsg(xxMsgIdList);
            for (String xxMsgId : xxMsgIdList) {
                if (xxMsgId.endsWith("W") && this.warnSendFlg.equals(ZYPConstant.FLG_OFF_N)) {
                    continue;
                }
                this.errMsgList.add("Ser#["+ rs.getString("SER_NUM") + "]:" + S21MessageFunc.clspGetMessage(xxMsgId));
            }
            // END 2016/09/01 T.Tomita [QC#14071, MOD]
            return normalFlag;
        }
        return true;
    }

    private boolean callDeRegisterToUGW(ResultSet rs) throws SQLException {
        NSZC050001PMsg nszc050001PMsg = new NSZC050001PMsg();
        setParamForNSZC050001(nszc050001PMsg, rs);

        NSZC050001 apiNSZC050001 = new NSZC050001();
        apiNSZC050001.execute(nszc050001PMsg, ONBATCH_TYPE.BATCH);
        boolean normalFlag = true;
        if (S21ApiUtil.isXxMsgId(nszc050001PMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(nszc050001PMsg);
            // START 2016/09/01 T.Tomita [QC#14071, MOD]
//            for (String xxMsgId : xxMsgIdList) {
//                if (xxMsgId.endsWith("E")) {
//                    normalFlag = false;
//                    break;
//                }
//            }
            normalFlag = isNormalMsg(xxMsgIdList);
            for (String xxMsgId : xxMsgIdList) {
                if (xxMsgId.endsWith("W") && this.warnSendFlg.equals(ZYPConstant.FLG_OFF_N)) {
                    continue;
                }
                this.errMsgList.add("Ser#["+ rs.getString("SER_NUM") + "]:" + S21MessageFunc.clspGetMessage(xxMsgId));
            }
            // END 2016/09/01 T.Tomita [QC#14071, MOD]
            return normalFlag;
        }
        return true;
    }

    private void setParamForNSZC049001(NSZC049001PMsg pMsg, ResultSet rs) throws SQLException {
        if (pMsg == null || rs == null) {
            return;
        }
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.usrId, BATCH_ID);
        setValue(pMsg.svcCallFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.svcMachMstrPk, rs.getBigDecimal(COL_SVC_MACH_MSTR_PK));
    }

    private void setParamForNSZC050001(NSZC050001PMsg pMsg, ResultSet rs) throws SQLException {
        if (pMsg == null || rs == null) {
            return;
        }
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.svcMachMstrPk, rs.getBigDecimal(COL_SVC_MACH_MSTR_PK));
    }

    private boolean isRegister(ResultSet rs) throws SQLException {
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_IWR_RGTN_RQST_FLG)) && ZYPConstant.FLG_OFF_N.equals(rs.getString(COL_IWR_DE_RGTN_RQST_FLG))) {
            return true;
        }

        return false;
    }

    private boolean isDeRegister(ResultSet rs) throws SQLException {
        if (ZYPConstant.FLG_OFF_N.equals(rs.getString(COL_IWR_RGTN_RQST_FLG)) && ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_IWR_DE_RGTN_RQST_FLG))) {
            return true;
        }

        return false;
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
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
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

    // START 2016/09/01 T.Tomita [QC#14071, ADD]
    private boolean isNormalMsg(List<String> xxMsgIdList) {
        for (String xxMsgId : xxMsgIdList) {
            if (xxMsgId.endsWith("E")) {
                return false;
            }
        }
        return true;
    }
    // END 2016/09/01 T.Tomita [QC#14071, ADD]
}
