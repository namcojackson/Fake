package com.canon.cusa.s21.batch.NFC.NFCB125001;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
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
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFCB12501 Credit Balance Update (Balance) Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ------------------------------------------------------------------------
 * 11/12/2015   Fujitsu         T.Tanaka        Create          N/A
 * 2016/09/01   Hitachi         K.Kojima        Update          QC#10786
 * 2019/02/28   Fujitsu         S.Ohki          Update          QC#30584
 * 2022/03/14   CITS            K.Suzuki        Update          QC#59706
 *</pre>
 */

public class NFCB125001 extends S21BatchMain {

    private S21SsmBatchClient ssmBatchClient;

    private String globalCompanyCode;

    private String batProcDate;

    private String arRgtnStsCd;

    private int procCount = 0;

    private int normalCnt = 0;

    private int errorCnt = 0;

    private int apiErrorCnt = 0;

    private static final String NFCM0503E = "NFCM0503E";

    public static final String VARCHAR_AR_RGTN_STS_CD = "AR_RGTN_STS_CD";

    private static final int FETCH_SIZE = 100;

    static final String SELECT_RECORDS = "SELECT_RECORDS";

    static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    static final String MAIL_GRP_FROM_ID = "FROM0003";  // NFC
    static final String MAIL_KEY_1_FROM = "NFC";
    static final String MAIL_GRP_TO_ID = "NFCB1250";
    static final String MAIL_KEY_1_TO = "TO";
    static final String MAIL_TMPLT_ID = "NFCB1250M001";

    static final String MSG_CUSTOMER = "Customer Acct# : ";
    static final String MSG_ERROR =    "Message        : ";
//    Set<String> mailMsg = new HashSet<String>();
    Set<String> mailMsg = null;
    /**
     * @param args
     */
    public static void main(String[] args) {

        new NFCB125001().executeBatch(NFCB125001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        if (this.ssmBatchClient == null) {
            setTermState(TERM_CD.ABNORMAL_END, normalCnt, errorCnt, procCount);
            throw new S21AbendException(NFCM0503E);
        }

        this.globalCompanyCode = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.globalCompanyCode)) {
            setTermState(TERM_CD.ABNORMAL_END, normalCnt, errorCnt, procCount);
            throw new S21AbendException(NFCM0503E);
        }

        this.batProcDate = ZYPDateUtil.getBatProcDate(this.globalCompanyCode);
        if (S21StringUtil.isEmpty(this.batProcDate)) {
            setTermState(TERM_CD.ABNORMAL_END, normalCnt, errorCnt, procCount);
            throw new S21AbendException(NFCM0503E);
        }

        // AR Registration Code
        this.arRgtnStsCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_AR_RGTN_STS_CD, this.globalCompanyCode);
        if(S21StringUtil.isEmpty(this.arRgtnStsCd)) {
            setTermState(TERM_CD.ABNORMAL_END, normalCnt, errorCnt, procCount);
            throw new S21AbendException(NFCM0503E);
        }

    }

    /**
     *
     */
    @Override
    protected void mainRoutine() {

        if (selectRecords()) {
            commit();
        } else {
            rollback();
        }
    }

    /**
     * termRoutine
     */
    @Override
    protected void termRoutine() {
        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errorCnt, this.procCount);
    }

    /**
     *
     * @return
     */
    private boolean selectRecords() {

        this.mailMsg = new HashSet<String>();

        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        Map<String, Serializable> queryParam = new HashMap<String, Serializable>();
        queryParam.put("glblCmpyCd", this.globalCompanyCode);
        queryParam.put("rgtnStsCd", this.arRgtnStsCd);
        queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTp_Rcpt", AR_TRX_TP.RECEIPT);
        queryParam.put("arTrxTp_Inv", AR_TRX_TP.INVOICE);
        queryParam.put("procDt", this.batProcDate);
        // START 2016/09/01 K.Kojima [QC#10786,ADD]
        queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/09/01 K.Kojima [QC#10786,ADD]

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchSize(FETCH_SIZE);
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // START 2022/03/14 K.Suzuki [QC#59706,ADD]
            S21InfoLogOutput.println("SELECT_RECORDS Start");
            // END   2022/03/14 K.Suzuki [QC#59706,ADD]
            stmt = ssmLLClient.createPreparedStatement(SELECT_RECORDS, queryParam, ssmParam);
            rs = stmt.executeQuery();
            // START 2022/03/14 K.Suzuki [QC#59706,ADD]
            S21InfoLogOutput.println("SELECT_RECORDS End");
            // END   2022/03/14 K.Suzuki [QC#59706,ADD]

            while (rs.next()) {
                if(callCreditUpdateApi(rs.getString(DS_ACCT_NUM))){
                    commit();
                    this.normalCnt ++;
                } else {
                    this.errorCnt ++;
                }
                this.procCount++;
            }

        } catch (SQLException e) {
            setTermState(TERM_CD.ABNORMAL_END, normalCnt, errorCnt, procCount);
            throw new S21AbendException(NFCM0503E);
        }
        if(this.errorCnt > 0) {
            sendMail();
        }

        return true;
    }

    /**
     *
     * @param acctNum
     * @return
     */
    private boolean callCreditUpdateApi( String acctNum) {

        NFZC202001 api = new NFZC202001();
        NFZC202001PMsg apiMsg = new NFZC202001PMsg();

        apiMsg.xxModeCd.setValue("03");
        apiMsg.glblCmpyCd.setValue(this.globalCompanyCode);
        apiMsg.sellToCustCd.setValue(acctNum);
        apiMsg.procDt.setValue(this.batProcDate);

        // START 2022/03/14 K.Suzuki [QC#59706,ADD]
        S21InfoLogOutput.println("NFZC202001 Start");
        // END   2022/03/14 K.Suzuki [QC#59706,ADD]
        api.execute(apiMsg, ONBATCH_TYPE.BATCH);
        // START 2022/03/14 K.Suzuki [QC#59706,ADD]
        S21InfoLogOutput.println("NFZC202001 End");
        // END   2022/03/14 K.Suzuki [QC#59706,ADD]
        if(apiMsg.xxMsgIdList.getValidCount()>0) {
            for(int i=0; i<apiMsg.xxMsgIdList.getValidCount(); i++) {
                setErorMesg(acctNum, apiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                this.apiErrorCnt++;
            }
            return false;
        }
        return true;
    }

    /**
     *
     * @param acctNum
     * @param msgId
     */
    private void setErorMesg(String acctNum, String msgId) {
        StringBuilder msg = new StringBuilder();

        String crlf = System.getProperty("line.separator");

        msg.append(MSG_CUSTOMER);
        msg.append(acctNum);
        msg.append(crlf);

        msg.append(MSG_ERROR);
        String message = S21MessageFunc.clspGetMessage(msgId);
        msg.append(message);
        msg.append(crlf);
        msg.append(crlf);

        this.mailMsg.add(msg.toString());
    }

    /*
     *
     */
    private boolean sendMail() {
        S21Mail mail = new S21Mail(this.globalCompanyCode);

        // From
        S21MailGroup grpFrom = new S21MailGroup(this.globalCompanyCode, MAIL_GRP_FROM_ID);
        grpFrom.setMailKey1(MAIL_KEY_1_FROM);
        List<S21MailAddress> addrFromList = grpFrom.getMailAddress();
        S21MailAddress from = null;
        if (addrFromList.size() == 1) {
            from = addrFromList.get(0);
        } else {
            throw new S21AbendException("ZZMM0007E", new String[]{"From Address"});
        }
        mail.setFromAddress(from);

        // To
        S21MailGroup grpTo = new S21MailGroup(this.globalCompanyCode, MAIL_GRP_TO_ID);
        grpTo.setMailKey1(MAIL_KEY_1_TO);
        List<S21MailAddress> addrToList = grpTo.getMailAddress();

        mail.setToAddressList(addrToList);

        // Template
        S21MailTemplate tmplt = new S21MailTemplate(this.globalCompanyCode, MAIL_TMPLT_ID);
        tmplt.setTemplateParameter("TempBody", getMessage());
        mail.setSubject(tmplt.getSubject("en"), "ISO-8859-1");
        mail.setMailTemplate(tmplt);

        // Send mail
        // START 2019/02/28 S.Ohki [QC#30584, MOD]
//        String mailEventID = mail.sendMail();
        String mailEventID = mail.postMail();
        // END 2019/02/28 S.Ohki [QC#30584, MOD]

        return true;
    }

    /**
     *
     * @return
     */
    private String getMessage() {
        String[] strArray = this.mailMsg.toArray(new String[0]);
        StringBuilder msg = new StringBuilder();
        for(int i = 0; i <strArray.length; i++) {
            msg.append(strArray[i]);
        }
        return msg.toString();
    }

}
