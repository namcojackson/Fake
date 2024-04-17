/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB143001;

import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.CVI_INVTY_TP_GOOD;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.DB_SEQ_CVI_INVTY_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_FIELD_ERR_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_FIELD_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_GROUP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MAIL_TEMPLATE_NPAB1430M001;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MSG_TXT_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.MSG_TXT_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAI221001_CVI_INVTY_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAI221001_CVI_INVTY_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAI221001_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAM1265E;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAM1266E;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAM1298E;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.NPAM1300E;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB143001.constant.NPAB143001Constant.ZZXM0020E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CVI_INVTYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB143001 : CVI On-Hand Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   CITS            Y.Kuroda        Create          N/A
 * 09/16/2016   CITS            K.Ogino         Update          QC#13735
 *</pre>
 */
public class NPAB143001 extends S21BatchMain {

    /** Global Company Code. */
    private String glblCmpyCd = null;

    /** User Profile Service. */
    private S21UserProfileService profileService = null;

    /** Interface ID. */
    private String interfaceId = null;

    /** TableAccessor. */
    private S21TransactionTableAccessor accessor = null;

    /** Termination code. */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total count. */
    private int totalCount = 0;

    /** Success count. */
    private int successCount = 0;

    /** Error count. */
    private int errorCount = 0;

    /** Mail Error Message. */
    private String mailMessage = null;

    /** SSM Batch Client. */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Execution Parameter. */
    private S21SsmExecutionParameter ssmEexcParam = null;

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        glblCmpyCd = profileService.getGlobalCompanyCode();

        /** Parameter Check : Global Company Code */
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZXM0020E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        /** Parameter Check : Interface ID */
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZXM0020E, new String[] {MSG_TXT_INTERFACE_ID });
        }

        // Initialization of SSM
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmEexcParam = new S21SsmExecutionParameter();
        ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmEexcParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

    }

    @Override
    protected void mainRoutine() {

        accessor = new S21TransactionTableAccessor();
        BigDecimal[] transactionIdList = accessor.getIntegrationRecord(this.interfaceId);

        // Import
        if (transactionIdList.length > 0) {
            importInventoryData(transactionIdList);
        }

        if (ZYPCommonFunc.hasValue(this.mailMessage)) {
            sendErrorMail();
            commit();
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {

        setTermState(termCd, successCount, errorCount);
    }

    /**
     * main : initialize
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NPAB143001().executeBatch(NPAB143001.class.getSimpleName());
    }

    /**
     * importInventoryData : main logic
     * @param transactionIdList
     */
    private void importInventoryData(BigDecimal[] transactionIdList) {

        for (BigDecimal tranId : transactionIdList) {

            successCount = 0;

            // Delete
            if (!deleteInventoryData()) {
                rollback();
                this.mailMessage = S21MessageFunc.clspGetMessage(NPAM1298E);
                termCd = TERM_CD.WARNING_END;
                return;
            }

            // Select
            List<Map<String, Object>> receiveInventoryList = selectCVIInventory(tranId);

            // Insert
            if (!importInventoryData(receiveInventoryList)) {

                rollback();
                errorCount = this.errorCount + receiveInventoryList.size();
                this.mailMessage = S21MessageFunc.clspGetMessage(NPAM1300E);
                termCd = TERM_CD.WARNING_END;
                return;
            }

            accessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();
        }
    }

    /**
     * deleteInventoryData : sub program
     * @return
     */
    private boolean deleteInventoryData() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);

        CVI_INVTYTMsg deleteData = new CVI_INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(deleteData.glblCmpyCd, this.glblCmpyCd);

        EZDTBLAccessor.removeByPartialKey(deleteData);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(deleteData.getReturnCode()) && !EZDTBLAccessor.RTNCD_NOT_FOUND.equals(deleteData.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * selectCVIInventory : sub program
     * @param tranId
     * @return
     */
    private List<Map<String, Object>> selectCVIInventory(BigDecimal tranId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.interfaceId);
        ssmParam.put("transactionId", tranId);
        ssmParam.put("invtyTpDescTxt", CVI_INVTY_TP_GOOD);

        List<Map<String, Object>> inventoryList = this.ssmBatchClient.queryObjectList("getReceivingInventories", ssmParam, ssmEexcParam);

        return inventoryList;
    }

    /**
     * importInventoryData : sub program
     * @param receiveInventoryList
     * @return
     */
    private boolean importInventoryData(List<Map<String, Object>> receiveInventoryList) {

        this.totalCount = this.totalCount + receiveInventoryList.size();

        // Insert.
        for (Map<String, Object> invtyList : receiveInventoryList) {

            if (!createCVIInventory(invtyList)) {
                this.errorCount = this.errorCount + receiveInventoryList.size();
                return false;
            }
        }

        this.successCount = this.successCount + receiveInventoryList.size();
        return true;
    }

    /**
     * createCVIInventory : sub program
     * @param invList
     * @return
     */
    private boolean createCVIInventory(Map<String, Object> invList) {

        // Insert.
        CVI_INVTYTMsg createData = new CVI_INVTYTMsg();

        ZYPEZDItemValueSetter.setValue(createData.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(createData.cviInvtyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(DB_SEQ_CVI_INVTY_SQ));
        ZYPEZDItemValueSetter.setValue(createData.mdseCd, (String) invList.get(NPAI221001_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(createData.cviInvtyTpDescTxt, (String) invList.get(NPAI221001_CVI_INVTY_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(createData.cviInvtyQty, (BigDecimal) invList.get(NPAI221001_CVI_INVTY_QTY));

        EZDTBLAccessor.create(createData);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createData.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * sendErrorMail
     */
    private void sendErrorMail() {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);

        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {
            throw new S21AbendException(NPAM1265E);
        }

        // Get Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_NPAB1430M001);

        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAM1266E, new String[] {MAIL_TEMPLATE_NPAB1430M001 });
        }

        template.setTemplateParameter(MAIL_FIELD_BATCH_ID, PROGRAM_ID);
        template.setTemplateParameter(MAIL_FIELD_ERR_DATE, ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT));
        template.setTemplateParameter(MAIL_FIELD_MESSAGE, this.mailMessage);

        // Send Mail.
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
}
