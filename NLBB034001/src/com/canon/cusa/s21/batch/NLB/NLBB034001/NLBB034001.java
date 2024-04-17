/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB034001;

import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_FRT_CHRG_BU_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_FRT_INFO_INTFC_PROC_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_FRT_INFO_INTFC_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_FRT_INFO_INTFC_WRK_PK;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_FRT_PRFL_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.CARR_FRT_SRC_TP_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_BR_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_CC_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_CH_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_GL_SEG_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_PROJ_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.COA_PROD_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.FROM_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.INVTY_TRX_DT;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAIL_FIELD_MESSAGE;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAIL_FIELD_TIMESTAMP;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAIL_GROUP_ID_TO_SYSTEM;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAIL_TEMPLATE_ID_M001;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAIL_TS_FMT;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAP_KEY_CARR_FRT_INFO_INTFC_PROC_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MAP_KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_ID_NLAM1272E;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_ID_NLAM1273E;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_ID_NLGM0044E;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_ID_NLGM0045E;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_ID_NLGM0046E;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_ID_ZZZM9025E;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_TXT_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.MSG_TXT_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.NLBI1330_01;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.TO_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.TRX_REF_NUM_01;
import static com.canon.cusa.s21.batch.NLB.NLBB034001.constant.NLBB034001Constant.TRX_REF_NUM_02;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CARR_FRT_INFO_INTFC_WRKTMsg;
import business.db.NLBI1330_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLBB0340 Send Freight Info to CTSI (IF Data Creation) Batch
 * Function Name : NLBB034001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/06/2016   CITS            R.Shimamoto     Create          N/A
 * 11/29/2016   CITS            Y.IWASAKI       Update          QC#16246
 * 01/11/2017   CITS            R.Shimamoto     Update          QC#16278
 * 04/23/2018   CITS            K.Ogino         Update          QC#25669
 * </pre>
 */
public class NLBB034001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** Interface Id */
    private String interfaceId = null;

    /** Mail System Skipped Message */
    private StringBuilder mailSystemSkippedMessage;

    @Override
    protected void initRoutine() {

        // Get Global Company Code
        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        // get InterfaceID
        this.interfaceId = getInterfaceID();

        // getClient
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Set termCd
        termCd = TERM_CD.NORMAL_END;

        mailSystemSkippedMessage = new StringBuilder();

    }

    @Override
    protected void mainRoutine() {

        // Check GlobalCompanyCode
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            // GlobalCompanyCode is no data
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        // Check Interface Id
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            // Interface Id is no data
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {MSG_TXT_INTERFACE_ID });
        }

        // Create Transaction Id.
        S21TransactionTableAccessor tranAcc = new S21TransactionTableAccessor();

        // new record create(INTERFACE_TRANSACTION table)
        BigDecimal newTranID = tranAcc.getNextTransactionId();

        // Select target data.
        getCarrFrtInfoIntfcWrk(newTranID);

        // QC#16246
        // Skip when no record.
        if (this.successCount > 0) {
            tranAcc.createIntegrationRecordForBatch(this.interfaceId, newTranID);
        } else {
            // QC#16278 Add.
            sendSkippedMail();
        }

        commit();

    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);

    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NLBB034001().executeBatch(NLBB034001.class.getSimpleName());
    }

    /**
     * getCarrFrtInfoIntfcWrk
     */
    private void getCarrFrtInfoIntfcWrk(BigDecimal newTranID) {
        // List<Map<String, Object>> carrFrtInfoIntfcWrkList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(MAP_KEY_GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(MAP_KEY_CARR_FRT_INFO_INTFC_PROC_CD, ZYPConstant.FLG_OFF_0);

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getCarrFrtInfoIntfcWrk", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            int seqNum = 1;
            while (rs.next()) {
                totalCount++;
                Map<String, Object> carrFrtInfoIntfcWrk = new HashMap<String, Object>();

                carrFrtInfoIntfcWrk.put(CARR_FRT_INFO_INTFC_WRK_PK, rs.getBigDecimal(CARR_FRT_INFO_INTFC_WRK_PK));
                carrFrtInfoIntfcWrk.put(CARR_FRT_SRC_TP_NM, rs.getString(CARR_FRT_SRC_TP_NM));
                carrFrtInfoIntfcWrk.put(CARR_FRT_PRFL_NUM, rs.getString(CARR_FRT_PRFL_NUM));
                carrFrtInfoIntfcWrk.put(TRX_HDR_NUM, rs.getString(TRX_HDR_NUM));
                carrFrtInfoIntfcWrk.put(TRX_REF_NUM_01, rs.getString(TRX_REF_NUM_01));
                carrFrtInfoIntfcWrk.put(TRX_REF_NUM_02, rs.getString(TRX_REF_NUM_02));
                carrFrtInfoIntfcWrk.put(COA_CMPY_CD, rs.getString(COA_CMPY_CD));
                carrFrtInfoIntfcWrk.put(COA_BR_CD, rs.getString(COA_BR_CD));
                carrFrtInfoIntfcWrk.put(COA_CC_CD, rs.getString(COA_CC_CD));
                carrFrtInfoIntfcWrk.put(COA_ACCT_CD, rs.getString(COA_ACCT_CD));
                carrFrtInfoIntfcWrk.put(COA_PROD_CD, rs.getString(COA_PROD_CD));
                carrFrtInfoIntfcWrk.put(COA_CH_CD, rs.getString(COA_CH_CD));
                carrFrtInfoIntfcWrk.put(COA_AFFL_CD, rs.getString(COA_AFFL_CD));
                carrFrtInfoIntfcWrk.put(COA_PROJ_CD, rs.getString(COA_PROJ_CD));
                carrFrtInfoIntfcWrk.put(CARR_FRT_CHRG_BU_CD, rs.getString(CARR_FRT_CHRG_BU_CD));
                carrFrtInfoIntfcWrk.put(CARR_CD, rs.getString(CARR_CD));
                carrFrtInfoIntfcWrk.put(INVTY_TRX_DT, rs.getString(INVTY_TRX_DT));
                carrFrtInfoIntfcWrk.put(FROM_POST_CD, rs.getString(FROM_POST_CD));
                carrFrtInfoIntfcWrk.put(TO_POST_CD, rs.getString(TO_POST_CD));
                carrFrtInfoIntfcWrk.put(CARR_FRT_INFO_INTFC_PROC_CD, rs.getString(CARR_FRT_INFO_INTFC_PROC_CD));
                // QC#25669
                carrFrtInfoIntfcWrk.put(COA_GL_SEG_TXT, rs.getString(COA_GL_SEG_TXT));

                // Create for IFTable(NLBI1330_01)
                updateInterfaceTable(newTranID, carrFrtInfoIntfcWrk, seqNum);

                // Update For CARR_FRT_INFO_INTFC_WRK
                updateCarrFrtInfoIntfcWrk((BigDecimal) carrFrtInfoIntfcWrk.get(CARR_FRT_INFO_INTFC_WRK_PK));

                successCount++;
                seqNum++;

            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    /**
     * updateInterfaceTable
     * @param tranID
     * @param carrFrtInfoIntfcWrk
     * @param seqNum
     */
    private void updateInterfaceTable(BigDecimal tranID, Map<String, Object> carrFrtInfoIntfcWrk, int seqNum) {

        NLBI1330_01TMsg ifTMsg = new NLBI1330_01TMsg();

        ifTMsg.interfaceId.setValue(this.interfaceId);
        ifTMsg.transactionId.setValue(tranID);
        ifTMsg.segmentId.setValue(BigDecimal.ONE);
        ifTMsg.unitId.setValue(BigDecimal.ONE);
        ifTMsg.seqNumber.setValue(seqNum);
        if (carrFrtInfoIntfcWrk.get(CARR_FRT_SRC_TP_NM) != null) {
            ifTMsg.carrFrtSrcTpNm.setValue((String) carrFrtInfoIntfcWrk.get(CARR_FRT_SRC_TP_NM));
        } else {
            ifTMsg.carrFrtSrcTpNm.clear();
        }
        if (carrFrtInfoIntfcWrk.get(CARR_FRT_PRFL_NUM) != null) {
            ifTMsg.carrFrtPrflNum.setValue((String) carrFrtInfoIntfcWrk.get(CARR_FRT_PRFL_NUM));
        } else {
            ifTMsg.carrFrtPrflNum.clear();
        }
        if (carrFrtInfoIntfcWrk.get(TRX_HDR_NUM) != null) {
            ifTMsg.trxHdrNum.setValue((String) carrFrtInfoIntfcWrk.get(TRX_HDR_NUM));
        } else {
            ifTMsg.trxHdrNum.clear();
        }
        if (carrFrtInfoIntfcWrk.get(TRX_REF_NUM_01) != null) {
            ifTMsg.trxRefNum_01.setValue((String) carrFrtInfoIntfcWrk.get(TRX_REF_NUM_01));
        } else {
            ifTMsg.trxRefNum_01.clear();
        }
        if (carrFrtInfoIntfcWrk.get(TRX_REF_NUM_02) != null) {
            ifTMsg.trxRefNum_02.setValue((String) carrFrtInfoIntfcWrk.get(TRX_REF_NUM_02));
        } else {
            ifTMsg.trxRefNum_02.clear();
        }
        if (carrFrtInfoIntfcWrk.get(CARR_CD) != null) {
            ifTMsg.carrCd.setValue((String) carrFrtInfoIntfcWrk.get(CARR_CD));
        } else {
            ifTMsg.carrCd.clear();
        }
        if (carrFrtInfoIntfcWrk.get(INVTY_TRX_DT) != null) {
            ifTMsg.invtyTrxDt.setValue((String) carrFrtInfoIntfcWrk.get(INVTY_TRX_DT));
        } else {
            ifTMsg.invtyTrxDt.clear();
        }
        if (carrFrtInfoIntfcWrk.get(FROM_POST_CD) != null) {
            ifTMsg.fromPostCd.setValue((String) carrFrtInfoIntfcWrk.get(FROM_POST_CD));
        } else {
            ifTMsg.fromPostCd.clear();
        }
        if (carrFrtInfoIntfcWrk.get(TO_POST_CD) != null) {
            ifTMsg.toPostCd.setValue((String) carrFrtInfoIntfcWrk.get(TO_POST_CD));
        } else {
            ifTMsg.toPostCd.clear();
        }
        // QC#25669
        if (carrFrtInfoIntfcWrk.get(COA_GL_SEG_TXT) != null) {
            ifTMsg.coaGlSegTxt.setValue((String) carrFrtInfoIntfcWrk.get(COA_GL_SEG_TXT));
        } else {
            ifTMsg.coaGlSegTxt.clear();
        }
        EZDTBLAccessor.insert((NLBI1330_01TMsg) ifTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ifTMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NLGM0045E, new String[] {NLBI1330_01, CARR_FRT_INFO_INTFC_WRK, TRX_HDR_NUM, (String) carrFrtInfoIntfcWrk.get(TRX_HDR_NUM) });
        }
    }

    /**
     * updateCarrFrtInfoIntfcWrk
     * @param carrFrtInfoIntfcWrkPk
     */
    private void updateCarrFrtInfoIntfcWrk(BigDecimal carrFrtInfoIntfcWrkPk) {

        CARR_FRT_INFO_INTFC_WRKTMsg updateTMsg = new CARR_FRT_INFO_INTFC_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updateTMsg.carrFrtInfoIntfcWrkPk, carrFrtInfoIntfcWrkPk);
        updateTMsg = (CARR_FRT_INFO_INTFC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updateTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NLGM0044E, new String[] {CARR_FRT_INFO_INTFC_WRK, CARR_FRT_INFO_INTFC_WRK_PK, carrFrtInfoIntfcWrkPk.toString() });
        }
        ZYPEZDItemValueSetter.setValue(updateTMsg.carrFrtInfoIntfcProcCd, ZYPConstant.FLG_ON_1);
        EZDTBLAccessor.update((CARR_FRT_INFO_INTFC_WRKTMsg) updateTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NLGM0046E, new String[] {CARR_FRT_INFO_INTFC_WRK, CARR_FRT_INFO_INTFC_WRK_PK, carrFrtInfoIntfcWrkPk.toString() });
        }

    }

    /**
     * sendSkippedMail
     */
    private void sendSkippedMail() {

        // Get To Mail Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO_SYSTEM);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(MSG_ID_NLAM1273E, new String[] {MAIL_GROUP_ID_TO_SYSTEM });
        }
        sendMail(addrToList, this.mailSystemSkippedMessage.toString());
    }

    /**
     * Send Mail
     */
    private void sendMail(List<S21MailAddress> addrToList, String mailBody) {

        String currentTs = ZYPDateUtil.getCurrentSystemTime(MAIL_TS_FMT);
        // Get From Mail Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }
        // Get Template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_M001);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(MSG_ID_NLAM1272E, new String[] {MAIL_TEMPLATE_ID_M001 });
        }

        template.setTemplateParameter(MAIL_FIELD_TIMESTAMP, currentTs);
        template.setTemplateParameter(MAIL_FIELD_MESSAGE, mailBody);

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

}
