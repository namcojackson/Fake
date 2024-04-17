/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB073001;

import static com.canon.cusa.s21.batch.NSA.NSAB073001.constant.NSAB073001Constant.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;

import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
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
 * <pre>
 * NSAB073001
 * Void Credit Card for Service Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2016   Hitachi         N.Arai          Create          N/A
 * </pre>
 */
public class NSAB073001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** total Count */
    private int totalCount = 0;

    /** Error Count */
    private int errorCont = 0;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /** API Error message list */
    private List<String> apiErrMsgList = new ArrayList<String>();

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NASM0010E);
        }

        // "Sales Date"
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E);
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {

        voidContractCreditCard();
        voidFsrCreditCard();

    }

    @Override
    protected void termRoutine() {

        sendEmail();
        if (this.errorCont > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCont, this.errorCont, this.totalCount);

    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB073001().executeBatch(NSAB073001.class.getSimpleName());
    }

    private void voidContractCreditCard() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = getContractCreditCardVoid();
            rs = ps.executeQuery();

            while (rs.next()) {

                this.totalCount++;
                callCrCardTrxUpdateApi(rs);

            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void voidFsrCreditCard() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = getFsrCreditCardVoid();
            rs = ps.executeQuery();

            while (rs.next()) {

                this.totalCount++;
                callCrCardTrxUpdateApi(rs);

            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void callCrCardTrxUpdateApi(ResultSet rs)  throws SQLException {

        NWZC203001PMsg pMsg = new NWZC203001PMsg();
        // NWZC2030 Credit Card Validation API
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, WRITE_CC_TRANSACTION_02);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.crCardCustRefNum, rs.getString("CR_CARD_CUST_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthRefNum, rs.getString("CR_CARD_AUTH_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthDt, rs.getString("CR_CARD_AUTH_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardSetlDt, rs.getString("CR_CARD_SETL_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthAmt, rs.getBigDecimal("CR_CARD_AUTH_AMT"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthTaxAmt, rs.getBigDecimal("CR_CARD_AUTH_TAX_AMT"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxTpCd, rs.getString("CR_CARD_TRX_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoTxt, rs.getString("FIRST_TRX_INFO_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.scdTrxInfoTxt, rs.getString("SCD_TRX_INFO_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.thirdTrxInfoTxt, rs.getString("THIRD_TRX_INFO_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.frthTrxInfoTxt, rs.getString("FRTH_TRX_INFO_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.fifthTrxInfoTxt, rs.getString("FIFTH_TRX_INFO_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.firstTrxInfoNum, rs.getBigDecimal("FIRST_TRX_INFO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.scdTrxInfoNum, rs.getBigDecimal("SCD_TRX_INFO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.thirdTrxInfoNum, rs.getBigDecimal("THIRD_TRX_INFO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.frthTrxInfoNum, rs.getBigDecimal("FRTH_TRX_INFO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.fifthTrxInfoNum, rs.getBigDecimal("FIFTH_TRX_INFO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.origCrCardTrxPk, rs.getBigDecimal("ORIG_CR_CARD_TRX_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardTrxPk, rs.getBigDecimal("CR_CARD_TRX_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardCancDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcTrxRefIdxCd, rs.getString("CR_CARD_REF_IDX_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.VOID_COMPLETED);
        ZYPEZDItemValueSetter.setValue(pMsg.authStsMsgCmntTxt, rs.getString("AUTH_STS_MSG_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcProcStsCd, rs.getString("CR_CARD_TRX_PROC_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxPmtcApvlStsNum, rs.getString("CR_CARD_TRX_APVL_STS_CD"));

        new NWZC203001().execute(pMsg, ONBATCH_TYPE.BATCH);
        setApiErrMsgList(pMsg);

        if (this.apiErrMsgList.size() > 0) {
            addMsgList(pMsg);
            this.apiErrMsgList.clear();
            this.errorCont++;
            rollback();
        } else {
            commit();
        }

    }

    private PreparedStatement getContractCreditCardVoid() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cardAuthSaved", CR_CARD_AUTH_STS.SAVED);
        queryParam.put("cardTrxTpCntrH", CR_CARD_TRX_TP.CONTRACT_HEADER);
        queryParam.put("pmtTermCashDiscCc", PMT_TERM_CASH_DISC.CREDIT_CARD);
        queryParam.put("dsContrCtrlStsCanc", DS_CONTR_CTRL_STS.CANCELLED);
        queryParam.put("cardTrxTpCntrD", CR_CARD_TRX_TP.CONTRACT_DETAIL);
        queryParam.put("cardTrxTpCntrM", CR_CARD_TRX_TP.CPONTRACT_METER);
        return this.ssmLLClient.createPreparedStatement("getContractCreditCardVoid", queryParam, getExecPrm());
    }

    private PreparedStatement getFsrCreditCardVoid() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cardTrxTpSvcReq", CR_CARD_TRX_TP.SERVICE_REQUEST);
        queryParam.put("cardAuthSaved", CR_CARD_AUTH_STS.SAVED);
        queryParam.put("pmtTermCashDiscCc", PMT_TERM_CASH_DISC.CREDIT_CARD);
        queryParam.put("svcTaskStsCcl", SVC_TASK_STS.CANCELLED);
        return this.ssmLLClient.createPreparedStatement("getFsrCreditCardVoid", queryParam, getExecPrm());
    }

    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private boolean setApiErrMsgList(EZDPMsg apiPMsg) {

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                this.apiErrMsgList.add(xxMsgId);
            }
            return false;
        }
        return true;
    }

    private void addMsgList(NWZC203001PMsg apiPMsg) {
        for (String apiMsgId : this.apiErrMsgList) {
            String errMsg = S21MessageFunc.clspGetMessage(apiMsgId);
            StringBuilder sb = new StringBuilder();
            sb.append(errMsg);
            sb.append("   ");
            sb.append("CR_CARD_TRX_PK=");
            sb.append(apiPMsg.crCardTrxPk.getValue());
            this.msgList.add(sb.toString());
        }
    }

    private void sendEmail() {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    tmpl.setTemplateParameter(MAIL_ITEM_BATCH_ID, BUSINESS_ID);
                    tmpl.setTemplateParameter(MAIL_ITEM_ERR_DATE, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL));
                    tmpl.setTemplateParameter(MAIL_ITEM_ERR_MSG, msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();
                }
            }
        }
    }

}
