package com.canon.cusa.s21.batch.NFC.NFCB305001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.batch.NFC.NFCB305001.NFCB305001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.AR_RF_TRXTMsg;
import business.db.NFCI1130_01TMsg;
import business.parts.NFBCommonBusiness;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 *
 * <pre>
 * Send AR Refund Info to CFS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/28/2016   CUSA            Y.Aikawa        Create          N/A
 * 2016/04/26   Hitachi         T.Tsuchida      Update          QC#7484
 * 2016/04/26   Hitachi         T.Tsuchida      Update          QC#7485
 * 2018/02/08   Hitachi         T.Tsuchida      Update          QC#24043
 * 2019/02/28   Fujitsu         S.Ohki          Update          QC#30584
 * </pre>
 */
public class NFCB305001 extends S21BatchMain {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Batch Process Date */
    private String batProcDt;
    /** Batch Process Date */
    private String batProcDtYYMMDD;
    /** Interface Transaction Table Accessor */
    private S21TransactionTableAccessor intCmLib;
    /** TERM_CD */
    private TERM_CD termCd;
    /** User Parameter 1 (VAR_USER1): CFS_INV_NUM_SIZE */
    private int intCfsInvNumSize;
    /** User Parameter 2 (VAR_USER2): IntfcFileNm */
    private String intfcFileNm;

    /** Commit Count (NFCI1130_01) */
    private int nfci113001CommitCount;
    /** Commit Count (AR_RF_TRX) */
    private int arRfTrxCommitCount;
    /** Total Commit Count */
    private int totalCommitCount;

    /** NFCI1130_01 Bulk Insert Count */
    private int iCntNfci113001;
    /** AR_RF_TRX Bulk Insert Count */
    private int iCntArRfTrx;

    /** NFCI1130_01TMsg List for bulk insert process */
    private List<NFCI1130_01TMsg> listNfci113001TMsg;
    /** AR_RF_TRXTMsg List for bulk update process */
    private List<AR_RF_TRXTMsg> listArRfTrxTMsg;

    /** String for email attachment */
    private StringBuilder sbEmailAttachment;

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFCB305001().executeBatch(NFCB305001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        termCd = TERM_CD.NORMAL_END;
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        /** Initialize NFCI1130_01TMsg list for bulk update process */
        initListForBulkUpdate();
        /** Initialize Bulk Update Count */
        initBulkCount();
        batProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(batProcDt)) {
            throw new S21AbendException(NFCM0624E);
        }
        batProcDtYYMMDD = batProcDt.substring(2, 4);
        batProcDtYYMMDD = batProcDtYYMMDD + batProcDt.substring(4, 6);
        batProcDtYYMMDD = batProcDtYYMMDD + batProcDt.substring(6, 8);
        /** Initialize Interface Transaction Table Accessor */
        intCmLib = new S21TransactionTableAccessor();
        /** get user parameter */
        String strCfsInvNumSize  = S21BatchUtil.getUserVariable1();
        if (strCfsInvNumSize == null || strCfsInvNumSize.length() == 0) {
            intCfsInvNumSize = INT_8;
        } else {
            intCfsInvNumSize = Integer.valueOf(strCfsInvNumSize);
        }
        intfcFileNm = S21BatchUtil.getUserVariable2();
        if (!ZYPCommonFunc.hasValue(intfcFileNm)) {
            intfcFileNm = DEF_INTFC_FILE_NM;
        }
        /** Initialize string for email attachment */
        sbEmailAttachment =  new StringBuilder();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {

        BigDecimal transactionId = BigDecimal.ZERO;
        BigDecimal totDealRfAmt = BigDecimal.ZERO;
        int seqNum = 1;

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("arRfStsCd", AR_RF_STS.CLOSED);
        ssmParam.put("flg_N", ZYPConstant.FLG_OFF_N);
        ssmParam.put("arRfTpCd", AR_RF_TP.CFS);
        List<Map<String, Object>> listRefundRecord = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_REFUND_RECORD, ssmParam);
        if (listRefundRecord.size() > 0)  {
            for (int i = 0; i < listRefundRecord.size(); i++) {
                Map<String, Object> mapRefundRecord = (Map<String, Object>) listRefundRecord.get(i);
                BigDecimal arRfTrxPk = (BigDecimal) mapRefundRecord.get(AR_RF_TRX_PK);
                String arOrgRcptNum = (String) mapRefundRecord.get(AR_ORG_RCPT_NUM);
                String apVndInvNum = (String) mapRefundRecord.get(AP_VND_INV_NUM);
                BigDecimal dealRfAmt = (BigDecimal) mapRefundRecord.get(DEAL_RF_AMT);
                totDealRfAmt = totDealRfAmt.add(dealRfAmt);
                if (i == 0) {
                    // Create transaction ID
                    transactionId = intCmLib.getNextTransactionId();
                }
                ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("flg_N", ZYPConstant.FLG_OFF_N);
                ssmParam.put("arLockBoxStsCd", AR_LOCK_BOX_STS.RECEIPT_WORK_GENERATED);
                ssmParam.put("vldCustRcptNum", arOrgRcptNum);
                List<Map<String, Object>> listLockboxRecord = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_LOCKBOX_RECORD, ssmParam);
                if (listLockboxRecord == null || listLockboxRecord.size() == 0) {
                    // create one interface table record from AR_RF_TRX table information.
                    NFCI1130_01TMsg nfci113001 = new NFCI1130_01TMsg();
                    ZYPEZDItemValueSetter.setValue(nfci113001.interfaceId, NFCI1130);
                    ZYPEZDItemValueSetter.setValue(nfci113001.transactionId, transactionId);
                    ZYPEZDItemValueSetter.setValue(nfci113001.segmentId, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(nfci113001.unitId, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(nfci113001.seqNumber, BigDecimal.valueOf(seqNum++));
                    String intfcCfsInvNumTxt = formatIntfcCfsInvNumTxt(apVndInvNum);
                    ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsInvNumTxt, intfcCfsInvNumTxt);
                    ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsInvAmtNum, dealRfAmt);
                    String intfcCfsRcptNumTxt = formatIntfcCfsRcptNumTxt(arOrgRcptNum);
                    ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsRcptNumTxt, intfcCfsRcptNumTxt);
                    ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsRcptAmtNum, dealRfAmt);
                    ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsDlrTxt, CFS);
                    ZYPEZDItemValueSetter.setValue(nfci113001.batProcDt, batProcDt);
                    listNfci113001TMsg.add(nfci113001);
                    iCntNfci113001++;
                    // Create string for email attachment
                    appendLineForEmailAttachment(nfci113001);
                } else {
                    BigDecimal remainingRefundAmt = dealRfAmt;
                    for (int j = 0; j < listLockboxRecord.size(); j++) {
                        Map<String, Object> mapLockboxRecord = (Map<String, Object>) listLockboxRecord.get(j);
                        String existsArTrxBal = (String) mapLockboxRecord.get(EXISTS_AR_TRX_BAL);
                        String custInvNum = (String) mapLockboxRecord.get(CUST_INV_NUM);
                        BigDecimal custInvAmt = (BigDecimal) mapLockboxRecord.get(CUST_INV_AMT);
                        if (ZYPConstant.FLG_ON_Y.equals(existsArTrxBal)) {
                            // Skip invoice if exists on CSA.
                            continue;
                        }
                        NFCI1130_01TMsg nfci113001 = new NFCI1130_01TMsg();
                        ZYPEZDItemValueSetter.setValue(nfci113001.interfaceId, NFCI1130);
                        ZYPEZDItemValueSetter.setValue(nfci113001.transactionId, transactionId);
                        ZYPEZDItemValueSetter.setValue(nfci113001.segmentId, BigDecimal.ONE);
                        ZYPEZDItemValueSetter.setValue(nfci113001.unitId, BigDecimal.ONE);
                        ZYPEZDItemValueSetter.setValue(nfci113001.seqNumber, BigDecimal.valueOf(seqNum++));
                        String intfcCfsInvNumTxt = formatIntfcCfsInvNumTxt(custInvNum);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsInvNumTxt, intfcCfsInvNumTxt);
                        BigDecimal intfcCfsInvAmtNum = BigDecimal.ZERO;
                        if (remainingRefundAmt.compareTo(custInvAmt) >= 0) {
                            intfcCfsInvAmtNum = custInvAmt;
                            remainingRefundAmt = remainingRefundAmt.subtract(custInvAmt);
                        } else {
                            intfcCfsInvAmtNum = remainingRefundAmt;
                            remainingRefundAmt = BigDecimal.ZERO;
                        }
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsInvAmtNum, intfcCfsInvAmtNum);
                        String intfcCfsRcptNumTxt = formatIntfcCfsRcptNumTxt(arOrgRcptNum);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsRcptNumTxt, intfcCfsRcptNumTxt);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsRcptAmtNum, dealRfAmt);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsDlrTxt, CFS);
                        ZYPEZDItemValueSetter.setValue(nfci113001.batProcDt, batProcDt);
                        listNfci113001TMsg.add(nfci113001);
                        iCntNfci113001++;
                        // Create string for email attachment
                        appendLineForEmailAttachment(nfci113001);
                    }
                    if (remainingRefundAmt.compareTo(BigDecimal.ZERO) > 0) {
                        NFCI1130_01TMsg nfci113001 = new NFCI1130_01TMsg();
                        ZYPEZDItemValueSetter.setValue(nfci113001.interfaceId, NFCI1130);
                        ZYPEZDItemValueSetter.setValue(nfci113001.transactionId, transactionId);
                        ZYPEZDItemValueSetter.setValue(nfci113001.segmentId, BigDecimal.ONE);
                        ZYPEZDItemValueSetter.setValue(nfci113001.unitId, BigDecimal.ONE);
                        ZYPEZDItemValueSetter.setValue(nfci113001.seqNumber, BigDecimal.valueOf(seqNum++));
                        String intfcCfsInvNumTxt = formatIntfcCfsInvNumTxt(apVndInvNum);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsInvNumTxt, intfcCfsInvNumTxt);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsInvAmtNum, remainingRefundAmt); // Set remaining refund amount.
                        String intfcCfsRcptNumTxt = formatIntfcCfsRcptNumTxt(arOrgRcptNum);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsRcptNumTxt, intfcCfsRcptNumTxt);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsRcptAmtNum, dealRfAmt);
                        ZYPEZDItemValueSetter.setValue(nfci113001.intfcCfsDlrTxt, CFS);
                        ZYPEZDItemValueSetter.setValue(nfci113001.batProcDt, batProcDt);
                        listNfci113001TMsg.add(nfci113001);
                        iCntNfci113001++;
                        // Create string for email attachment
                        appendLineForEmailAttachment(nfci113001);
                    }
                }
                if (iCntNfci113001 >= INT_BULK_COM_LIM) {
                    bulkInsertNfci113001();
                }
                AR_RF_TRXTMsg arRfTrx = new AR_RF_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(arRfTrx.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(arRfTrx.arRfTrxPk, arRfTrxPk);
                arRfTrx = (AR_RF_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(arRfTrx);
                if (arRfTrx != null) {
                    ZYPEZDItemValueSetter.setValue(arRfTrx.cfsIntfcCpltFlg, ZYPConstant.FLG_ON_Y);
                    listArRfTrxTMsg.add(arRfTrx);
                    iCntArRfTrx++;
                    if (iCntArRfTrx >= INT_BULK_COM_LIM) {
                        bulkUpdateArRfTrx();
                    }
                }
            }
            if (iCntNfci113001 > 0) {
                bulkInsertNfci113001();
            }
            if (iCntArRfTrx > 0) {
                bulkUpdateArRfTrx();
            }
            // Create interface transaction record.
            intCmLib.createIntegrationRecordForBatch(NFCI1130, transactionId);
            commit();

            StringBuilder sbMessage = new StringBuilder();
            sbMessage.append(intfcFileNm);
            sbMessage.append(" file is transferred to CFS with ");
            sbMessage.append(Integer.toString(seqNum - 1));
            sbMessage.append(" invoices ");
            sbMessage.append(LINE_FEED_CODE);
            sbMessage.append("Check Date : ");
            sbMessage.append(getMMDDYYWithSlash(batProcDt));
            sbMessage.append(LINE_FEED_CODE);
            sbMessage.append("Total paid amount : ");
            sbMessage.append(NFBCommonBusiness.formatEmailAmountString(totDealRfAmt));
            sendEmail(MAIL_SUB_NM, sbMessage.toString());
        }
    }
    @Override
    protected void termRoutine() {
        this.totalCommitCount = nfci113001CommitCount + arRfTrxCommitCount;
        /** Normal End this process */
        setTermState(termCd, totalCommitCount, 0, 0);
    }
    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.nfci113001CommitCount = 0;
        this.arRfTrxCommitCount = 0;
        this.totalCommitCount = 0;
    }
    /**
     * Initialize Bulk TBL Accessor.
     */
    private void initListForBulkUpdate() {
        this.listNfci113001TMsg = new ArrayList<NFCI1130_01TMsg>();
        this.listArRfTrxTMsg = new ArrayList<AR_RF_TRXTMsg>();
    }
    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntNfci113001 = 0;
        this.iCntArRfTrx = 0;
    }

    /**
     * Bulk update AR_RF_TRX table.
     */
    private void bulkInsertNfci113001() {
        NFCI1130_01TMsg[] nfci113001TMsgs = listNfci113001TMsg.toArray(new NFCI1130_01TMsg[listNfci113001TMsg.size()]);
        int iRet = S21FastTBLAccessor.insert(nfci113001TMsgs);
        if (iRet > 0) {
            nfci113001CommitCount = nfci113001CommitCount + iRet;
            listNfci113001TMsg = new ArrayList<NFCI1130_01TMsg>();
            iCntNfci113001 = 0;
        } else {
            nfci113001CommitCount = 0;
            throw new S21AbendException(ZZBM0074E);
        }
    }

    /**
     * Bulk update AR_RF_TRX table.
     */
    private void bulkUpdateArRfTrx() {
        AR_RF_TRXTMsg[] arRfTrxTMsgs = listArRfTrxTMsg.toArray(new AR_RF_TRXTMsg[listArRfTrxTMsg.size()]);
        int iRet = S21FastTBLAccessor.update(arRfTrxTMsgs);
        if (iRet > 0) {
            arRfTrxCommitCount = arRfTrxCommitCount + iRet;
            listArRfTrxTMsg = new ArrayList<AR_RF_TRXTMsg>();
            iCntArRfTrx = 0;
        } else {
            arRfTrxCommitCount = 0;
            throw new S21AbendException(NFCM0624E);
        }
    }

    /**
     * Format INTFC_CFS_INV_NUM_TXT
     * 
     * @param intfcCfsInvNumTxt String
     * @return String
     */
    private String formatIntfcCfsInvNumTxt(String intfcCfsInvNumTxt) {
        if (ZYPCommonFunc.hasValue(intfcCfsInvNumTxt)) {
            // START 2018/02/08 T.Tsuchida [QC#24043,MOD]
            // Zero padding
//            int paddingDigits = 0;
//            if (intfcCfsInvNumTxt.length() > intCfsInvNumSize) {
//                intfcCfsInvNumTxt = intfcCfsInvNumTxt.substring(0, intCfsInvNumSize);
//            } else {
//                paddingDigits = intCfsInvNumSize - intfcCfsInvNumTxt.length();
//                for (int i = 0; i < paddingDigits; i++) {
//                    intfcCfsInvNumTxt = STR_0 + intfcCfsInvNumTxt;
//                }
//            }
            if (intfcCfsInvNumTxt.length() > intCfsInvNumSize) {
                intfcCfsInvNumTxt = intfcCfsInvNumTxt.substring(0, intCfsInvNumSize);
            }
            // END 2018/02/08 T.Tsuchida [QC#24043,MOD]
            return intfcCfsInvNumTxt;
        } else {
            return null;
        }
    }

    /**
     * Format INTFC_CFS_RCPT_NUM_TXT
     * 
     * @param intfcCfsInvNumTxt String
     * @return String
     */
    private String formatIntfcCfsRcptNumTxt(String intfcCfsRcptNumTxt) {
        if (ZYPCommonFunc.hasValue(intfcCfsRcptNumTxt)) {
            if (intfcCfsRcptNumTxt.length() > INT_10) {
                intfcCfsRcptNumTxt = intfcCfsRcptNumTxt.substring(0, INT_10);
            }
            return intfcCfsRcptNumTxt;
        } else {
            return null;
        }
    }

    /****************************
     * Send Error email.
     * @param subject String
     * @param message String
     ****************************/
    private void sendEmail(String subject, String message) {
        /*******************************
         * Create attachment file 
         *******************************/
        S21MailAttachment attachment = new S21MailAttachment(glblCmpyCd);
        String strAttachment = sbEmailAttachment.toString();
        byte[] attachData = strAttachment.getBytes();
        int attachId = attachment.setAttachment(attachData);
        attachment.setFileName(intfcFileNm);
        /*******************************
         * Set mail parameter
         *******************************/
        Map<String, String> mailParamMap = new HashMap<String, String>();
        mailParamMap.put("glblCmpyCd", glblCmpyCd);
        mailParamMap.put("groupIdTo", CST_MAIL_GRP_ID_TO);
        mailParamMap.put("groupToMailKey1", CST_MAIL_KEY_TO_1);
        mailParamMap.put("groupToMailKey2", CST_MAIL_KEY_TO_2);
        mailParamMap.put("groupIdCc", CST_MAIL_GRP_ID_CC);
        mailParamMap.put("groupCcMailKey1", CST_MAIL_KEY_CC_1);
        mailParamMap.put("groupCcMailKey2", CST_MAIL_KEY_CC_2);
        mailParamMap.put("groupIdBcc", CST_MAIL_GRP_ID_BCC);
        mailParamMap.put("groupBccMailKey1", CST_MAIL_KEY_BCC_1);
        mailParamMap.put("groupBccMailKey2", CST_MAIL_KEY_BCC_2);
        mailParamMap.put("attachmentId", Integer.toString(attachId));
        /*******************************
         * Create mail template
         *******************************/
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID);
        if (template == null) {
            S21InfoLogOutput.println(NFBM0184E, new String[] {ML_TMPL_ID});
            termCd = TERM_CD.WARNING_END;
            return;
        }
        /*******************************
         * Set mail template parameter
         *******************************/
        /** subject */
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_SUBJECT, subject);
        /** message */
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);
        /*******************************
         * Send Mail
         *******************************/
        sendMail(mailParamMap, template);
    }

    /**
     * addLineForEmailAttachment
     * @param nfci113001 NFCI1130_01TMsg
     */
    public void appendLineForEmailAttachment(NFCI1130_01TMsg nfci113001) {
        String dtlStr1 = nfci113001.intfcCfsInvNumTxt.getValue();     // INTFC_CFS_INV_NUM_TXT
        BigDecimal dtlBd1 = nfci113001.intfcCfsInvAmtNum.getValue();  // INTFC_CFS_INV_AMT_NUM
        String dtlStr2 = nfci113001.intfcCfsRcptNumTxt.getValue();    // INTFC_CFS_RCPT_NUM_TXT
        BigDecimal dtlBd2 = nfci113001.intfcCfsRcptAmtNum.getValue(); // INTFC_CFS_RCPT_AMT_NUM
        String dtlStr3 = nfci113001.intfcCfsDlrTxt.getValue();        // INTFC_CFS_DLR_TXT
        String dtlStr4 = batProcDtYYMMDD;                             // BAT_PROC_DT(YYMMDD)
        // Create line for attachment.
        String strLine = formatLineForEmailAttachment(dtlStr1, dtlBd1, dtlStr2, dtlBd2, dtlStr3, dtlStr4);
        sbEmailAttachment.append(strLine);
        sbEmailAttachment.append(LINE_FEED_CODE);
    }

    /**
     * formatLineForEmailAttachment
     * @param dtlStr1 String 
     * @param dtlBd1 BigDecimal
     * @param dtlStr2 String 
     * @param dtlBd2 BigDecimal
     * @param dtlStr3 String 
     * @param dtlStr4 String 
     * @return String
     */
    private String formatLineForEmailAttachment(String dtlStr1, BigDecimal dtlBd1, String dtlStr2, BigDecimal dtlBd2, String dtlStr3, String dtlStr4) {
        return String.format(FORMAT_EMAIL_LINE,
                              chkNull(dtlStr1),
                              chkNull(dtlBd1.toString()),
                              chkNull(dtlStr2),
                              chkNull(dtlBd2.toString()),
                              chkNull(dtlStr3),
                              chkNull(dtlStr4)
                             );
    }

    /**
     * 
     * sendMail
     * 
     * @param mailParamMap Map<String, String>
     * @param template S21MailTemplate
     * @author Y.Aikawa
     */
    public static void sendMail(Map<String, String> mailParamMap, S21MailTemplate template) {

        /****************************
         * Get Parameter
         ****************************/
        String glblCmpyCd = (String) mailParamMap.get("glblCmpyCd");
        String groupIdTo = (String) mailParamMap.get("groupIdTo");
        String groupToMailKey1 = (String) mailParamMap.get("groupToMailKey1");
        String groupToMailKey2 = (String) mailParamMap.get("groupToMailKey2");
        String groupToMailKey3 = (String) mailParamMap.get("groupToMailKey3");
        String groupIdCc = (String) mailParamMap.get("groupIdCc");
        String groupCcMailKey1 = (String) mailParamMap.get("groupCcMailKey1");
        String groupCcMailKey2 = (String) mailParamMap.get("groupCcMailKey2");
        String groupCcMailKey3 = (String) mailParamMap.get("groupCcMailKey3");
        String groupIdBcc = (String) mailParamMap.get("groupIdBcc");
        String groupBccMailKey1 = (String) mailParamMap.get("groupBccMailKey1");
        String groupBccMailKey2 = (String) mailParamMap.get("groupBccMailKey2");
        String groupBccMailKey3 = (String) mailParamMap.get("groupBccMailKey3");

        /****************************
         * Mandatory Parameter Check
         ****************************/
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Global Company Code" });
        }
        if (!ZYPCommonFunc.hasValue(groupIdTo)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"To Group ID" });
        }
        if (!ZYPCommonFunc.hasValue(groupToMailKey1)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"To Mail key 1" });
        }
        if (template == null) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Mail Template" });
        }
        /*******************************
         * Set "From" email address to
         * "S21_NFC@cusa.canon.com"
         *******************************/
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GRP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_1_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress from = null;
        if (addrFromList.size() <= 0) {
            throw new S21AbendException(ZZMM0007E, new String[] {"From Address" });
        } else {
            from = addrFromList.get(0);
        }
        /*******************************
         * Get "To" email address
         *******************************/
        S21MailGroup groupTo;
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(groupIdTo)) {
            groupTo = new S21MailGroup(glblCmpyCd, groupIdTo);
            groupTo.setMailKey1(groupToMailKey1);
            if (ZYPCommonFunc.hasValue(groupToMailKey2)) {
                groupTo.setMailKey2(groupToMailKey2);
            }
            if (ZYPCommonFunc.hasValue(groupToMailKey3)) {
                groupTo.setMailKey3(groupToMailKey3);
            }
            addrToList = groupTo.getMailAddress();
        }
        /*******************************
         * Get "CC" email address
         *******************************/
        S21MailGroup groupCc;
        List<S21MailAddress> addrCcList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(groupIdCc)) {
            groupCc = new S21MailGroup(glblCmpyCd, groupIdCc);
            groupCc.setMailKey1(groupCcMailKey1);
            if (ZYPCommonFunc.hasValue(groupCcMailKey2)) {
                groupCc.setMailKey2(groupCcMailKey2);
            }
            if (ZYPCommonFunc.hasValue(groupCcMailKey3)) {
                groupCc.setMailKey3(groupCcMailKey3);
            }
            addrCcList = groupCc.getMailAddress();
        }
        /*******************************
         * Get "BCC" email address
         *******************************/
        S21MailGroup groupBcc;
        List<S21MailAddress> addrBccList = new ArrayList<S21MailAddress>();
        if (ZYPCommonFunc.hasValue(groupIdBcc)) {
            groupBcc = new S21MailGroup(glblCmpyCd, groupIdBcc);
            groupBcc.setMailKey1(groupBccMailKey1);
            if (ZYPCommonFunc.hasValue(groupBccMailKey2)) {
                groupBcc.setMailKey2(groupBccMailKey2);
            }
            if (ZYPCommonFunc.hasValue(groupBccMailKey3)) {
                groupBcc.setMailKey3(groupBccMailKey3);
            }
            addrBccList = groupBcc.getMailAddress();
        }
        /*******************************
         * Send mail.
         *******************************/
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setMailTemplate(template);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setCcAddressList(addrCcList);
        mail.setBccAddressList(addrBccList);
        mail.setSubject(template.getSubject("en"), "ISO-8859-1");
        if (mailParamMap.containsKey("attachmentId")) {
            mail.setAttachmentId(Integer.parseInt((String) mailParamMap.get("attachmentId")));
        }
        // START 2019/02/28 S.Ohki [QC#30584, MOD]
//        String mailEvent = mail.sendMail();
        String mailEvent = mail.postMail();
        // END 2019/02/28 S.Ohki [QC#30584, MOD]
        if (!hasValue(mailEvent)) {
            return;
        }
    }

    /**
     * chkNull
     * If the passed value is null then return BigDecimal.ZERO.
     * @param val BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal chkNull(BigDecimal val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * chkNull
     * If the passed value is null then return "".
     * @param val String
     * @return String
     */
    public static String chkNull(String val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        } else {
            return EMPTY_STRING;
        }
    }

    /**
     * Method name: getMMDDYYWithSlash
     * <dd>The method explanation: Convert YYMMDD to MM/DD/YY.
     * <dd>Remarks:
     * @param strDt String
     * @return String
     */
    public static String getMMDDYYWithSlash(String strDt) {
        if (ZYPCommonFunc.hasValue(strDt)
        &&  strDt.length() == 8) {
            return strDt.substring(4, 6) + "/" + strDt.substring(6, 8) + "/" + strDt.substring(2, 4);
        } else {
            return EMPTY_STRING;
        }
    }
}
