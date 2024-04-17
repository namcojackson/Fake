/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB008002;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.NFBI0140_01TMsg;
import business.db.CM_IF_OPEN_AP_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Accounts Payable Daily Header Interface
 * 
 * Insert records from CM_IF_OPEN_AP_HDR table into interface table(NFBI0140_01).
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/09/2009   CUSA            Y.Aikawa        Create          N/A
 * 12/09/2009   CUSA            Y.Aikawa        Create          N/A
 * 07/29/2013   Fujitsu         T.Tanaka        Update          Def#1454 ARCS
 * 2016/09/16   Hitachi         K.Kojima        Update          QC#14502
 * 2016/09/23   Hitachi         K.Kasai         Update          QC#14354
 * 2017/12/12   Hitachi         E.Kameishi      Update          QC#23052
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#24079
 * 2018/05/31   Hitachi         Y.Takeno        Update          QC#26158
 * </pre>
 */
public class NFBB008002 extends S21BatchMain implements NFBB008002Constant {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String globalCompanyCode;

    /** transaction ID */
    private BigDecimal transactionId0140 = null;

    /** 終了コード */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** SQLアクセス部品 */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Commit Count (NFBI0140_01) */
    private int commitCount0140;

    /** Total Commit Count */
    private int totalCommitCount;

    /** CM_PROC_DT */
    private String cmProcDt;

    /** NFBI0140_01 Handling Bulk TBL Accessor */
    private NFBI0140_01TMsg[] afbi014001Tmsgs = new NFBI0140_01TMsg[INT_COM_LIM];

    /** CM_IF_OPEN_AP_HDR Handling Bulk TBL Accessor */
    private CM_IF_OPEN_AP_HDRTMsg[] cmIfOpenApHdrTmsgs = new CM_IF_OPEN_AP_HDRTMsg[INT_COM_LIM];

    /** NFBI0140_01, CM_IF_OPEN_AP_HDR Bulk Insert/Update Count */
    private int iCnt;

    /** NFBI0140_01 Unit Id */
    private int unitId;

    //START 2016/09/23 K.Kasai [QC#14354,ADD]
    /** VAR_CHAR_CONST: AP_INV_SRC_NM */
    private String apInvSrcNm;
    //END 2016/09/23 K.Kasai [QC#14354,ADD]

    /** S21TransactionTableAccessor */
    S21TransactionTableAccessor intCmLib;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        new NFBB008002().executeBatch(NFBB008002.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {

        // Get User Profile Service Instance
        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        // Get Global Company Code
        this.globalCompanyCode = this.profile.getGlobalCompanyCode();

        /* Initialize S21BatchMain */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Initialize Commit Count
        this.commitCount0140 = 0;
        this.totalCommitCount = 0;

        // Initialize NFBI0140_01 and CM_IF_OPEN_AP_HDR Bulk
        // Insert/Update Count
        iCnt = 0;
        //START 2016/09/23 K.Kasai [QC#14354,ADD]
        this.apInvSrcNm = ZYPCodeDataUtil.getVarCharConstValue(CONST_NFBB008002_AP_INV_SRC_NM, this.globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(this.apInvSrcNm)) {
            this.apInvSrcNm = DFT_AP_INV_SRC_NM;
        }
        //END 2016/09/23 K.Kasai [QC#14354,ADD]

    }

    @Override
    protected void mainRoutine() {

        // START 2017/12/12 E.Kameishi [QC#23052, MOD]
        // Get CM_PROC_DT
        //getCmProcDt();
        cmProcDt = ZYPDateUtil.getBatProcDate(this.globalCompanyCode);
        // END 2017/12/12 E.Kameishi [QC#23052, MOD]

        // Insert records from CM_IF_OPEN_AP_HDR table into interface
        // table(NFBI0140_01).
        if (insertNFBI014001()) {

            if (iCnt > 0) {
                // Change Arrary size for last records
                afbi014001Tmsgs = changeArraySize(afbi014001Tmsgs, iCnt);
                cmIfOpenApHdrTmsgs = changeArraySize(cmIfOpenApHdrTmsgs, iCnt);
                // Bulk Insert after reading data.
                int iRet = S21FastTBLAccessor.insert(afbi014001Tmsgs);
                if (iRet > 0) {
                    // Success, none handling
                } else {
                    rollback();
                    commitCount0140 = 0;
                    throw new S21AbendException(ZZBM0074E);
                }

                // Update
                int iRetUp = S21FastTBLAccessor.update(cmIfOpenApHdrTmsgs);
                if (iRetUp > 0) {
                    // Success, none handling
                } else {
                    rollback();
                    commitCount0140 = 0;
                    throw new S21AbendException(NFBM0028E);
                }

                commitCount0140 = commitCount0140 + iRet;
                iCnt = 0;
                // Init Arrary
                initTMsgArray();

            }

            // Create interface transaction record.
            intCmLib.createIntegrationRecordForBatch(INTERFACE_ID_NFBI0140, transactionId0140);

            commit();

        } else {
            rollback();
        }

    }

    @Override
    protected void termRoutine() {

        // Display INTERFACE_ID, TRANSACTION_ID
        S21InfoLogOutput.printlnv(ZZIM0009I, INTERFACE_ID_NFBI0140, this.transactionId0140);

        // Display Output Count
        S21InfoLogOutput.printlnv(ZZBM0009I, NFBI0140_01, "INSERT", this.commitCount0140);

        // Total Commit Count
        this.totalCommitCount = commitCount0140;

        // Set termination code and total commit count.
        setTermState(this.termCd, totalCommitCount, 0, 0);

    }

    // START 2017/12/12 E.Kameishi [QC#23052, DEL]
//    /**
//     * Get CM_PROC_DT from CM_PROC_DT table.
//     */
//    private boolean getCmProcDt() {
//        EZDDebugOutput.println(1, "[[START]]", this);
//
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", globalCompanyCode);
//
//        // Select records from CM_INV_MDSE_PSET_INV_HDR table.
//        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_CM_PROC_DT, queryParam, new SelectCmProcDtRsHandler());
//
//        EZDDebugOutput.println(1, "[[E N D]]", this);
//
//        return bRet;
//
//    }
//
//    /**
//     * <pre>
//     *  Get CM_PROC_DT from CM_PROC_DT table.
//     * </pre>
//     */
//    private class SelectCmProcDtRsHandler extends S21SsmBooleanResultSetHandlerSupport {
//        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
//
//            if (rs.next()) {
//                cmProcDt = rs.getString(CM_PROC_DT);
//            } else {
//                throw new S21AbendException(NFBM0028E);
//            }
//
//            return true;
//
//        }
//    }
    // END 2017/12/12 E.Kameishi [QC#23052, DEL]

    /**
     * Insert records into NFBI0140_01 table.
     */
    private boolean insertNFBI014001() {
        EZDDebugOutput.println(1, "[[START]]", this);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", globalCompanyCode);

        // Select records from CM_IF_OPEN_AP_HDR table.
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD_FROM_CM_IF_OPEN_AP_HDR, queryParam, new SelectRecordFromCmIfOpenApHdrRsHandler());

        EZDDebugOutput.println(1, "[[E N D]]", this);

        return bRet;

    }

    /**
     * <pre>
     *  Insert records into NFBI0140_01 table.
     * </pre>
     */
    private class SelectRecordFromCmIfOpenApHdrRsHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            int iRet = 0;
            int iRetUp = 0;

            // Count up Interface table Unit Id.
            unitId = 1;

            // Create transaction ID for NFBI0140.
            intCmLib = new S21TransactionTableAccessor();
            transactionId0140 = intCmLib.getNextTransactionId();

            while (rs.next()) {

                NFBI0140_01TMsg NFBI014001 = new NFBI0140_01TMsg();

                NFBI014001.interfaceId.setValue(INTERFACE_ID_NFBI0140);
                NFBI014001.transactionId.setValue(transactionId0140);
                NFBI014001.segmentId.setValue(SEGMENT_ID);
                NFBI014001.unitId.setValue(unitId);
                NFBI014001.seqNumber.setValue(SEQ_NUMBER);

                NFBI014001.invoiceIdL15If.setValue(rs.getString(AP_INV_ID));

                if (ZYPCommonFunc.hasValue(rs.getString(INVOICE_NUM_L50_IF))) {
                    NFBI014001.invoiceNumL50If.setValue(rs.getString(INVOICE_NUM_L50_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(INVOICE_DATE_L8_IF))) {
                    NFBI014001.invoiceDateL8If.setValue(rs.getString(INVOICE_DATE_L8_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getBigDecimal(INVOICE_AMOUNT_N15_IF))) {
                    NFBI014001.invoiceAmountN15If.setValue(rs.getBigDecimal(INVOICE_AMOUNT_N15_IF));
                }

                //START 2016/09/23 K.Kasai [QC#14354,MOD]
                NFBI014001.sourceL20If.setValue(apInvSrcNm);
                //END 2016/09/23 K.Kasai [QC#14354,MOD]

                if (ZYPCommonFunc.hasValue(rs.getString(DESCRIPTION_L240_IF))) {
                    NFBI014001.descriptionL240If.setValue(rs.getString(DESCRIPTION_L240_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_1_L3_IF))) {
                    NFBI014001.coa1L3If.setValue(rs.getString(COA_1_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_2_L3_IF))) {
                    NFBI014001.coa2L3If.setValue(rs.getString(COA_2_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_3_L6_IF))) {
                    NFBI014001.coa3L6If.setValue(rs.getString(COA_3_L6_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_4_L8_IF))) {
                    NFBI014001.coa4L8If.setValue(rs.getString(COA_4_L8_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_5_L2_IF))) {
                    NFBI014001.coa5L2If.setValue(rs.getString(COA_5_L2_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_6_L3_IF))) {
                    NFBI014001.coa6L3If.setValue(rs.getString(COA_6_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_7_L3_IF))) {
                    NFBI014001.coa7L3If.setValue(rs.getString(COA_7_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_8_L4_IF))) {
                    // START 2016/09/16 K.Kojima [QC#14502,MOD]
                    // NFBI014001.coa8L4If.setValue(rs.getString(COA_8_L4_IF));
                    NFBI014001.coa8L4If.setValue(createCoa8L4If(rs.getString(COA_8_L4_IF)));
                    // END 2016/09/16 K.Kojima [QC#14502,MOD]
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_9_L3_IF))) {
                    NFBI014001.coa9L3If.setValue(rs.getString(COA_9_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(VENDOR_SITE_ID_L15_IF))) {
                    NFBI014001.vendorSiteIdL15If.setValue(rs.getString(VENDOR_SITE_ID_L15_IF));
                }

                // START 2018/03/06 [QC#24079, ADD]
                if (ZYPCommonFunc.hasValue(rs.getString(TERMS_NAME_L50_IF))) {
                    NFBI014001.termsNameL50If.setValue(rs.getString(TERMS_NAME_L50_IF));
                }
                // END   2018/03/06 [QC#24079, ADD]

                if (ZYPCommonFunc.hasValue(rs.getString(CODE_CURRENCY_L3_IF))) {
                    NFBI014001.codeCurrencyL3If.setValue(rs.getString(CODE_CURRENCY_L3_IF));
                }


                if (ZYPCommonFunc.hasValue(rs.getBigDecimal(EXCHANGE_RATE_N15_IF))) {
                    NFBI014001.exchangeRateN15If.setValue(rs.getBigDecimal(EXCHANGE_RATE_N15_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(EXCHANGE_DATE_L8_IF))) {
                    NFBI014001.exchangeDateL8If.setValue(rs.getString(EXCHANGE_DATE_L8_IF));
                }

                // START 2018/05/31 [QC#26158, ADD]
                if (ZYPCommonFunc.hasValue(rs.getString(EXCL_PAY_FLAG_L1_IF))) {
                    NFBI014001.exclPayFlagL1If.setValue(rs.getString(EXCL_PAY_FLAG_L1_IF));
                }
                // END   2018/05/31 [QC#26158, ADD]

                NFBI014001.creationDateL8If.setValue(cmProcDt);
                NFBI014001.updateDateL8If.setValue(cmProcDt);
                
                // DOC_ATT_L240_IF set null
                ZYPEZDItemValueSetter.setValue(NFBI014001.accountingDateL8If, cmProcDt);

                // Insert into NFBI0140_001;
                afbi014001Tmsgs[iCnt] = NFBI014001;

                // Update IF_PROC_STS_CD on CM_IF_OPEN_AP_HDR table.
                CM_IF_OPEN_AP_HDRTMsg cmIfOpenApHdr = new CM_IF_OPEN_AP_HDRTMsg();
                cmIfOpenApHdr.glblCmpyCd.setValue(globalCompanyCode);
                cmIfOpenApHdr.apInvId.setValue(rs.getString(AP_INV_ID));
                cmIfOpenApHdr.apVndCd.setValue(rs.getString(AP_VND_CD));
                cmIfOpenApHdr.apVndInvNum.setValue(rs.getString(AP_VND_INV_NUM));
                cmIfOpenApHdr.apVndInvSqNum.setValue(rs.getString(AP_VND_INV_SQ_NUM));
                cmIfOpenApHdr.remSqNum.setValue(rs.getString(REM_SQ_NUM));
                S21FastTBLAccessor.findByKeyForUpdate(cmIfOpenApHdr);
                cmIfOpenApHdr.ifProcStsCd.setValue(IF_PROC_STS_CD_C);
                cmIfOpenApHdrTmsgs[iCnt] = cmIfOpenApHdr;

                // Count up by a record.
                iCnt++;

                // Count up Interface table Unit Id.
                unitId++;

                // Bulk Insert for being up performance.
                iRet = 0;
                iRetUp = 0;

                if (iCnt == INT_COM_LIM) {
                    // Insert
                    iRet = S21FastTBLAccessor.insert(afbi014001Tmsgs);
                    if (iRet > 0) {
                        // Success, none handling
                    } else {
                        rollback();
                        commitCount0140 = 0;
                        throw new S21AbendException(ZZBM0074E);
                    }

                    // Update
                    iRetUp = S21FastTBLAccessor.update(cmIfOpenApHdrTmsgs);
                    if (iRetUp > 0) {
                        // Success, none handling
                    } else {
                        rollback();
                        commitCount0140 = 0;
                        throw new S21AbendException(NFBM0028E);
                    }

                    commitCount0140 = commitCount0140 + iRet;
                    // Init Arrary
                    initTMsgArray();
                    iCnt = 0;

                }

            }

            return Boolean.TRUE;
        }
    }

    /**
     * Change Arrary size of NFBI0140_01TMsg Array for last records
     */
    public NFBI0140_01TMsg[] changeArraySize(NFBI0140_01TMsg[] arrayRec, int newsize) {

        NFBI0140_01TMsg copyMsgs[] = arrayRec.clone();
        arrayRec = new NFBI0140_01TMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }
        return arrayRec;
    }

    /**
     * Change Arrary size of CM_IF_OPEN_AP_HDRTMsg Array for last
     * records
     */
    public CM_IF_OPEN_AP_HDRTMsg[] changeArraySize(CM_IF_OPEN_AP_HDRTMsg[] arrayRec, int newsize) {

        CM_IF_OPEN_AP_HDRTMsg copyMsgs[] = arrayRec.clone();
        arrayRec = new CM_IF_OPEN_AP_HDRTMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }
        return arrayRec;
    }

    /**
     * Init NFBI0140_01TMsg and CM_IF_OPEN_AP_HDRTMsg Array
     */
    public void initTMsgArray() {

        this.afbi014001Tmsgs = new NFBI0140_01TMsg[INT_COM_LIM];
        this.cmIfOpenApHdrTmsgs = new CM_IF_OPEN_AP_HDRTMsg[INT_COM_LIM];

    }

    // START 2016/09/16 K.Kojima [QC#14502,ADD]
    /**
     * createCoa8L4If
     * @param str
     * @return String
     */
    private String createCoa8L4If(String str) {
        return ZYPCommonFunc.leftPad(str, 4, "0");
    }
    // END 2016/09/16 K.Kojima [QC#14502,ADD]

}
