package com.canon.cusa.s21.batch.NMA.NMAB521001;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;
import business.db.DS_ACCT_RVW_PROSTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * UnHold to Territory Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/24   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NMAB521001 extends S21BatchMain {

    /** max date : 99991231 */
    private static final String MAX_DT = "99991231";

    /** An parameter "Interface ID" has not been set. */
    private static final String USEM0099E = "USEM0099E";

    /** It failed to register [@]. */
    private static final String NMAM0176E = "NMAM0176E";

    /** length of Create Date */
    private static final int LEN_CRAT_DT = "YYYYMMDD".length();

    /** length of person code */
    private static final int LEN_PSN_CD = 8;

    /** table id : TRTY_TRK_TRTY_LIST */
    private static final String TRTY_TRK_TRTY_LIST = "TRTY_TRK_TRTY_LIST";

    /** table id : DS_ACCT_RWV_PROS */
    private static final String DS_ACCT_RWV_PROS = "DS_ACCT_RWV_PROS";

    /** mail group id(from) : FROM0005 */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id(to) : NMAB5210 */
    private static final String MAIL_GROUP_ID_TO = "NMAB5210";

    /** mail template id : NMAB5210M001 */
    private static final String MAIL_TEMPLATE_ID = "NMAB5210M001";

    /** mail template key(batchId) : batchId */
    private static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** batch id : NMAB5210 */
    private static final String BATCH_ID = "NMAB5210";

    /** mail template key(batchNm) : batchNm */
    private static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** batch name : UnHold to territory update batch */
    private static final String BATCH_NM = "Unhold to territory update batch";

    /** mail template key(batchProcLogId) : batchProcLogId */
    private static final String MAIL_TEMPLATE_KEY_LOG_ID = "batchProcLogId";

    /** mail template key(ErrorInfo) : ErrorInfo */
    private static final String MAIL_TEMPLATE_KEY_ERR_INFO = "ErrorInfo";

    /** line separator */
    private static final String NEW_LINE = String.format("%n");

    private static final DecimalFormat DF_TRX_ID = new DecimalFormat("000000000000000000000000000000");

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /** SSM Client Custom */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /** commit count */
    private int commitCount;

    /** error count */
    private int errorCount;

    /** term code */
    private TERM_CD termCd;

    /** interface id */
    private String interfaceId;

    /** transaction id array */
    private BigDecimal[] trxIds = null;

    /** error PK list */
    private List<BigDecimal> errPkList = new ArrayList<BigDecimal>();

    /** s21 transaction accessor */
    S21TransactionTableAccessor s21TrxAsr = new S21TransactionTableAccessor();

    @Override
    protected void initRoutine() {
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
        // Initialization of SSM Custom
        ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());

        this.commitCount = 0;
        this.errorCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.interfaceId = getInterfaceID();
        if (this.interfaceId == null || "".equals(this.interfaceId)) {
            throw new S21AbendException(USEM0099E);
        }
        this.trxIds = this.s21TrxAsr.getIntegrationRecordDesc(this.interfaceId);

    }

    @Override
    protected void mainRoutine() {
        int i = 0;
        for (BigDecimal trxId : this.trxIds) {
            if (i == 0) {
                doProcess(trxId);
            }
            this.s21TrxAsr.endIntegrationProcess(this.interfaceId, trxId);
            i++;
        }
        if (this.errorCount == 0) {
            commit();
        } else {
            rollback();
            sendErrorMail();
            commit();
        }

    }

    private void sendErrorMail() {
        S21MailGroup mailGroupFrom = null;
        S21MailGroup mailGroupTo = null;
        S21MailAddress fromAddress = null;
        List<S21MailAddress> toAddressList = null;
        S21MailTemplate mailTemplate = null;
        S21Mail mail = null;
        //        String returnCode = null;

        mailGroupFrom = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        mailGroupTo = new S21MailGroup(getGlobalCompanyCode(), MAIL_GROUP_ID_TO);
        toAddressList = mailGroupTo.getMailAddress();

        mailTemplate = new S21MailTemplate(getGlobalCompanyCode(), MAIL_TEMPLATE_ID);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_ID, BATCH_ID);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_NM, BATCH_NM);
        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_LOG_ID, getBatchProcessLogID());

        mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_ERR_INFO, editErrInfo());

        mail = new S21Mail(getGlobalCompanyCode());
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        mail.postMail();
        //        returnCode = mail.postMail();
        //        if ("".equals(returnCode)) {
        //            //
        //        }
    }

    private String editErrInfo() {
        StringBuilder errInfo = new StringBuilder();
        errInfo.append("    Transaction ID                 Error Message");
        errInfo.append(NEW_LINE).append("    ").append(DF_TRX_ID.format(this.trxIds[0]));
        errInfo.append(" ").append(S21MessageFunc.clspGetMessage(NMAM0176E, new String[] {DS_ACCT_RWV_PROS }));
        errInfo.append(NEW_LINE).append("    ").append(" DS_ACCT_RVW_PROS_PK:");
        errInfo.append(S21StringUtil.toStringList(errPkList));
        return errInfo.toString();
    }

    private void doProcess(BigDecimal trxId) {
        truncateTrtyTrkTrtyList();
        insertTrtyTrkTrtyList(trxId);

        List<BigDecimal> rsltPkList = getUnholdTarget();
        if (rsltPkList == null || rsltPkList.size() == 0) {
            return;
        }
        for (BigDecimal rsltPk : rsltPkList) {
            execUnHold(rsltPk);
        }
    }

    private void execUnHold(BigDecimal rsltPk) {
        DS_ACCT_RVW_PROSTMsg tMsg = new DS_ACCT_RVW_PROSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctRvwProsPk, rsltPk);

        tMsg = (DS_ACCT_RVW_PROSTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
        if (tMsg == null) {
            this.errorCount++;
            this.errPkList.add(rsltPk);
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.trtyTrkHldFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            this.errorCount++;
            this.errPkList.add(tMsg.dsAcctRvwProsPk.getValue());
            return;
        }
        this.commitCount++;
    }

    /**
     * @return List<BigDecimal> UnHold target PK List
     */
    private List<BigDecimal> getUnholdTarget() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        List<BigDecimal> rsltList //
        = (List<BigDecimal>) this.ssmClient.queryObjectList("getUnholdTarget", queryParam);

        return rsltList;
    }

    private void insertTrtyTrkTrtyList(BigDecimal trxId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("tableId", TRTY_TRK_TRTY_LIST);
        queryParam.put("interfaceId", this.interfaceId);
        queryParam.put("transactionId", trxId);

        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        queryParam.put("ezintime", dateTime);
        queryParam.put("ezintimezone", upTimeZone);
        queryParam.put("ezincompanycode", upCmpyCd);
        queryParam.put("ezinuserid", userId);
        queryParam.put("ezinaplid", upPgId);

        queryParam.put("ezuptime", dateTime);
        queryParam.put("ezuptimezone", upTimeZone);
        queryParam.put("ezupcompanycode", upCmpyCd);
        queryParam.put("ezupuserid", userId);
        queryParam.put("ezupaplid", upPgId);

        queryParam.put("lenPsnCd", LEN_PSN_CD);
        queryParam.put("lenCratDt", LEN_CRAT_DT);

        queryParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
        queryParam.put("maxDt", MAX_DT);

        this.ssmBatchClientCustom.insert("insertTrtyTrkTrtyList", queryParam);

        commit();
    }

    private void truncateTrtyTrkTrtyList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("tableId", TRTY_TRK_TRTY_LIST);
        this.ssmBatchClientCustom.delete("truncateTable", queryParam);
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
            this.errorCount += this.commitCount;
            this.commitCount = 0;
        }
        setTermState(this.termCd, this.commitCount, this.errorCount, this.commitCount + this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // initialize S21BatchMain
        new NMAB521001().executeBatch(NMAB521001.class.getSimpleName());
    }

}
