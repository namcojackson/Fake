/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB263001;

import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_FIELD_ERR_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_FIELD_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_GROUP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MAIL_TEMPLATE_NPAB2630M001;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MSG_TXT_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.MSG_TXT_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.RCV_ASN_VND_RCV_ASN_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.PO_VND_V_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.PO_VND_V_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_INTERFACE_ID_HYTEC;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_INTERFACE_ID_ORACLE;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_INVTY_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_STK_STS_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_STK_STS_TXT_GOOD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_STK_STS_TXT_TBR;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAI043001_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAM1265E;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAM1266E;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAM1298E;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.NPAM1300E;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.VAR_CHAR_CONST_VAR_CHAR_CONST_NM_HYTEC;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.VAR_CHAR_CONST_VAR_CHAR_CONST_VAL_HYTEC;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.VAR_CHAR_CONST_VAR_CHAR_CONST_NM_ORACLE;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.VAR_CHAR_CONST_VAR_CHAR_CONST_VAL_ORACLE;
import static com.canon.cusa.s21.batch.NPA.NPAB263001.constant.NPAB263001Constant.ZZXM0020E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.THIRD_PTY_INVTYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * NPAB263001 : Receiving Inventory from EDI Vendor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/22/2023   Hitachi         S.Dong          Create          #QC61128
 *</pre>
 */
public class NPAB263001 extends S21BatchMain {

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

    /** Create Warehouse Code For Hytec */
    private String varCharConstValHytec = null;

    /** Create Vendor Code for Oracle */
    private String varCharConstValOracle = null;

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

        // get Hytec Warehouse Code from VAR_CHAR_CONST
        String constValueHytec = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_VAR_CHAR_CONST_NM_HYTEC, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constValueHytec)) {
            varCharConstValHytec = constValueHytec;
        } else {
            varCharConstValHytec = VAR_CHAR_CONST_VAR_CHAR_CONST_VAL_HYTEC;
        }
        
        // get Oracle Vendor Code from VAR_CHAR_CONST
        String constValueOracle = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_VAR_CHAR_CONST_NM_ORACLE, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constValueOracle)) {
            varCharConstValOracle = constValueOracle;
        } else {
            varCharConstValOracle = VAR_CHAR_CONST_VAR_CHAR_CONST_VAL_ORACLE;
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
        new NPAB263001().executeBatch(NPAB263001.class.getSimpleName());
    }

    /**
     * importInventoryData : main logic
     * @param transactionIdList
     */
    private void importInventoryData(BigDecimal[] transactionIdList) {

        for (BigDecimal tranId : transactionIdList) {

            successCount = 0;
            
            // Select
            List<Map<String, Object>> receiveInventoryList = selectReceivingInventory(tranId);

            if (!receiveInventoryList.isEmpty()) {
                // Delete
                if (!deleteInventoryData(receiveInventoryList)) {
                    rollback();
                    this.mailMessage = S21MessageFunc.clspGetMessage(NPAM1298E);
                    termCd = TERM_CD.WARNING_END;
                    return;
                }
            }

            // Insert
            if (!importInventoryData(receiveInventoryList)) {

                rollback();
                errorCount = this.errorCount + receiveInventoryList.size();
                this.mailMessage = S21MessageFunc.clspGetMessage(NPAM1300E);
                termCd = TERM_CD.WARNING_END;
                return;
            }

            // Flg Update
            accessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();
        }
    }

    /**
     * deleteInventoryData : sub program
     * @param receiveInventoryList
     * @return
     */
    private boolean deleteInventoryData(List<Map<String, Object>> receiveInventoryList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        THIRD_PTY_INVTYTMsg deleteData = new THIRD_PTY_INVTYTMsg();
        
        for (Map<String, Object> receiveInventory : receiveInventoryList) {
            ZYPEZDItemValueSetter.setValue(deleteData.glblCmpyCd, this.glblCmpyCd);
            if (NPAI043001_INTERFACE_ID_HYTEC.equals(this.interfaceId)) {
                ZYPEZDItemValueSetter.setValue(deleteData.vndCd, (String) receiveInventory.get(RCV_ASN_VND_RCV_ASN_VND_CD));
            } else if (NPAI043001_INTERFACE_ID_ORACLE.equals(this.interfaceId)) {
                ZYPEZDItemValueSetter.setValue(deleteData.vndCd, (String) receiveInventory.get(PO_VND_V_VND_CD));
            }
            
            EZDTBLAccessor.removeByPartialKey(deleteData);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(deleteData.getReturnCode()) && !EZDTBLAccessor.RTNCD_NOT_FOUND.equals(deleteData.getReturnCode())) {
                return false;
            }
        }

        return true;
    }

    /**
     * selectReceivingInventory : sub program
     * @param tranId
     * @return
     */
    private List<Map<String, Object>> selectReceivingInventory(BigDecimal tranId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        List<Map<String, Object>> inventoryListForHytec = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> inventoryListForOracle = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> inventoryList = new ArrayList<Map<String, Object>>();
        
        ssmParam.put("interfaceId", this.interfaceId);
        ssmParam.put("transactionId", tranId);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        if (NPAI043001_INTERFACE_ID_HYTEC.equals(this.interfaceId)) {
            ssmParam.put("vndSysTpCd", VND_SYS_TP.HYTEC);
            inventoryListForHytec = this.ssmBatchClient.queryObjectList("getReceivingInventories", ssmParam, ssmEexcParam);
        } else if (NPAI043001_INTERFACE_ID_ORACLE.equals(this.interfaceId)) {
            ssmParam.put("vndCd", this.varCharConstValOracle);
            inventoryListForOracle = this.ssmBatchClient.queryObjectList("getReceivingInventoriesForOracle", ssmParam, ssmEexcParam);
        }

        
        inventoryList.addAll(inventoryListForHytec);
        inventoryList.addAll(inventoryListForOracle);
        
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

            if (!createReceivingInventory(invtyList)) {
                this.errorCount = this.errorCount + receiveInventoryList.size();
                return false;
            }
        }

        this.successCount = this.successCount + receiveInventoryList.size();
        return true;
    }

    /**
     * createReceivingInventory : sub program
     * @param invList
     * @return
     */
    private boolean createReceivingInventory(Map<String, Object> invList) {

        // Insert.
        THIRD_PTY_INVTYTMsg thirdPtyInvtyData = new THIRD_PTY_INVTYTMsg();
            
        ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.prntVndCd, (String) invList.get(PO_VND_V_PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.mdseCd, (String) invList.get(NPAI043001_MDSE_CD));

        // interface id equals Hytec
        if (NPAI043001_INTERFACE_ID_HYTEC.equals(this.interfaceId)){
            ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.vndCd, (String) invList.get(RCV_ASN_VND_RCV_ASN_VND_CD));
            ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.invtyLocCd, this.varCharConstValHytec);
            
            if (NPAI043001_STK_STS_TXT_GOOD.equals(invList.get(NPAI043001_STK_STS_TXT))) {
                ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.stkStsCd, STK_STS.GOOD);
            } else if (NPAI043001_STK_STS_TXT_TBR.equals(invList.get(NPAI043001_STK_STS_TXT))) {
                ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.stkStsCd, STK_STS.TO_BE_REMAN);
            } else {
                ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.stkStsCd, "0");
            }
        }
        
        // interface id equals Oracle
        if (NPAI043001_INTERFACE_ID_ORACLE.equals(this.interfaceId) ) {
            if (invList.get(NPAI043001_STK_STS_TXT) != null) {
                ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.stkStsCd, (String)invList.get(NPAI043001_STK_STS_TXT));
            } else {
                ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.stkStsCd, "0");
            }
            ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.vndCd, (String) invList.get(PO_VND_V_VND_CD));
            ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.invtyLocCd, (String) invList.get(NPAI043001_WH_CD));
        }
        ZYPEZDItemValueSetter.setValue(thirdPtyInvtyData.invtyQty, (BigDecimal) invList.get(NPAI043001_INVTY_QTY));

        EZDTBLAccessor.create(thirdPtyInvtyData);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(thirdPtyInvtyData.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * sendErrorMail
     */
    private void sendErrorMail() {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID);

        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {
            throw new S21AbendException(NPAM1265E);
        }

        // Get Template.
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_NPAB2630M001);

        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAM1266E, new String[] {MAIL_TEMPLATE_NPAB2630M001 });
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
