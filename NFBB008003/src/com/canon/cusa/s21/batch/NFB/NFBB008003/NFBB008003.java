/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB008003;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.NFBI0145_01TMsg;
import business.db.CM_IF_OPEN_AP_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
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
 * Accounts Payable Daily Detail Interface
 * 
 * Insert records from CM_IF_OPEN_AP_DTL table into interface table(NFBI0145_01).
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/09/2009   CUSA            Y.Aikawa        Create          N/A
 * 07/29/2013   Fujitsu         T.Tanaka        Update          Def#1454 ARCS
 * 08/24/2016   CSAI            K.Uramori       Update          QC#13733 CSA
 * 2016/09/16   Hitachi         K.Kojima        Update          QC#14502
 * 2017/12/12   Hitachi         E.Kameishi      Update          QC#23052
 * </pre>
 */
public class NFBB008003 extends S21BatchMain implements NFBB008003Constant {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String globalCompanyCode;

    /** transaction ID */
    private BigDecimal transactionId0145 = null;

    /** 終了コード */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** SQLアクセス部品 */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Commit Count (NFBI0145_01) */
    private int commitCount0145;

    /** Total Commit Count */
    private int totalCommitCount;

    /** CM_PROC_DT */
    private String cmProcDt;

    /** NFBI0145_01 Handling Bulk TBL Accessor */
    private NFBI0145_01TMsg[] afbi014501Tmsgs = new NFBI0145_01TMsg[INT_COM_LIM];

    /** CM_IF_OPEN_AP_DTL Handling Bulk TBL Accessor */
    private CM_IF_OPEN_AP_DTLTMsg[] cmIfOpenApDtlTmsgs = new CM_IF_OPEN_AP_DTLTMsg[INT_COM_LIM];

    /** NFBI0145_01, CM_IF_OPEN_AP_DTL Bulk Insert/Update Count */
    private int iCnt;

    /** NFBI0145_01 Unit Id */
    private int unitId;

    /** S21TransactionTableAccessor */
    S21TransactionTableAccessor intCmLib;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        new NFBB008003().executeBatch(NFBB008003.class.getSimpleName());

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
        this.commitCount0145 = 0;
        this.totalCommitCount = 0;

        // Initialize NFBI0145_01 and CM_IF_OPEN_AP_DTL Bulk
        // Insert/Update Count
        iCnt = 0;

    }

    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {

        // START 2017/12/12 E.Kameishi [QC#23052, MOD]
        // Get CM_PROC_DT
        //getCmProcDt();
        cmProcDt = ZYPDateUtil.getBatProcDate(this.globalCompanyCode);
        // END 2017/12/12 E.Kameishi [QC#23052, MOD]

        // Insert records from CM_IF_OPEN_AP_DTL table into interface
        // table(NFBI0145_01).
        if (insertNFBI014501()) {

            if (iCnt > 0) {
                // Change Arrary size for last records
                afbi014501Tmsgs = changeArraySize(afbi014501Tmsgs, iCnt);
                cmIfOpenApDtlTmsgs = changeArraySize(cmIfOpenApDtlTmsgs, iCnt);
                // Bulk Insert after reading data.
                int iRet = S21FastTBLAccessor.insert(afbi014501Tmsgs);
                if (iRet > 0) {
                    // Success, none handling
                } else {
                    rollback();
                    commitCount0145 = 0;
                    throw new S21AbendException(ZZBM0074E);
                }

                // Update
                int iRetUp = S21FastTBLAccessor.update(cmIfOpenApDtlTmsgs);
                if (iRetUp > 0) {
                    // Success, none handling
                } else {
                    rollback();
                    commitCount0145 = 0;
                    throw new S21AbendException(NFBM0028E);
                }

                commitCount0145 = commitCount0145 + iRet;
                iCnt = 0;
                // Init Arrary
                initTMsgArray();

            }

            // Create interface transaction record.
            intCmLib.createIntegrationRecordForBatch(INTERFACE_ID_NFBI0145, transactionId0145);

            commit();

        } else {
            rollback();
        }

    }

    @Override
    protected void termRoutine() {

        // Display INTERFACE_ID, TRANSACTION_ID
        S21InfoLogOutput.printlnv(ZZIM0009I, INTERFACE_ID_NFBI0145, this.transactionId0145);

        // Display Output Count
        S21InfoLogOutput.printlnv(ZZBM0009I, NFBI0145_01, "INSERT", this.commitCount0145);

        // Total Commit Count
        this.totalCommitCount = commitCount0145;

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
     * Insert records into NFBI0145_01 table.
     */
    private boolean insertNFBI014501() {
        EZDDebugOutput.println(1, "[[START]]", this);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", globalCompanyCode);

        // Select records from CM_IF_OPEN_AP_DTL table.
        Boolean bRet = (Boolean) ssmBatchClient.queryObject(SELECT_RECORD_FROM_CM_IF_OPEN_AP_DTL, queryParam, new SelectRecordFromCmIfOpenApHdrRsHandler());

        EZDDebugOutput.println(1, "[[E N D]]", this);

        return bRet;

    }

    /**
     * <pre>
     *  Insert records into NFBI0145_01 table.
     * </pre>
     */
    private class SelectRecordFromCmIfOpenApHdrRsHandler extends S21SsmBooleanResultSetHandlerSupport {
        public Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            int iRet = 0;
            int iRetUp = 0;

            // Count up Interface table Unit Id.
            unitId = 1;

            // Create transaction ID for NFBI0145.
            intCmLib = new S21TransactionTableAccessor();
            transactionId0145 = intCmLib.getNextTransactionId();

            while (rs.next()) {

                NFBI0145_01TMsg NFBI014501 = new NFBI0145_01TMsg();

                NFBI014501.interfaceId.setValue(INTERFACE_ID_NFBI0145);
                NFBI014501.transactionId.setValue(transactionId0145);
                NFBI014501.segmentId.setValue(SEGMENT_ID);
                NFBI014501.unitId.setValue(unitId);
                NFBI014501.seqNumber.setValue(SEQ_NUMBER);

                NFBI014501.invoiceIdL8If.setValue(rs.getString(AP_INV_ID));

                if (ZYPCommonFunc.hasValue(rs.getString(INVOICE_NUMBER_L15_IF))) {
                    NFBI014501.invoiceNumberL15If.setValue(rs.getString(INVOICE_NUMBER_L15_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(LINE_NUMBER_L5_IF))) {
                    NFBI014501.lineNumberL5If.setValue(rs.getString(LINE_NUMBER_L5_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(DESCRIPTION_L32_IF))) {
                    NFBI014501.descriptionL32If.setValue(rs.getString(DESCRIPTION_L32_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getBigDecimal(INVOICE_AMOUNT_N15_IF))) {
                    NFBI014501.invoiceAmountN15If.setValue(rs.getBigDecimal(INVOICE_AMOUNT_N15_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_1_L3_IF))) {
                    NFBI014501.coa1L3If.setValue(rs.getString(COA_1_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_2_L3_IF))) {
                    NFBI014501.coa2L3If.setValue(rs.getString(COA_2_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_3_L6_IF))) {
                    NFBI014501.coa3L6If.setValue(rs.getString(COA_3_L6_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_4_L8_IF))) {
                    NFBI014501.coa4L8If.setValue(rs.getString(COA_4_L8_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_5_L2_IF))) {
                    NFBI014501.coa5L2If.setValue(rs.getString(COA_5_L2_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_6_L3_IF))) {
                    NFBI014501.coa6L3If.setValue(rs.getString(COA_6_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_7_L3_IF))) {
                    NFBI014501.coa7L3If.setValue(rs.getString(COA_7_L3_IF));
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_8_L4_IF))) {
                    // START 2016/09/16 K.Kojima [QC#14502,MOD]
                    // NFBI014501.coa8L4If.setValue(rs.getString(COA_8_L4_IF));
                    NFBI014501.coa8L4If.setValue(createCoa8L4If(rs.getString(COA_8_L4_IF)));
                    // END 2016/09/16 K.Kojima [QC#14502,MOD]
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COA_9_L3_IF))) {
                    NFBI014501.coa9L3If.setValue(rs.getString(COA_9_L3_IF));
                }

                NFBI014501.creationDateL8If.setValue(cmProcDt);
                //---- start QC#13733 remove this field according to data object change.
                //NFBI014501.lastUpdateDateL8If.setValue(cmProcDt);
                //---- end QC#13733

                // Insert into NFBI0145_01;
                afbi014501Tmsgs[iCnt] = NFBI014501;

                // Update IF_PROC_STS_CD on CM_IF_OPEN_AP_DTL table.
                CM_IF_OPEN_AP_DTLTMsg cmIfOpenApDtl = new CM_IF_OPEN_AP_DTLTMsg();
                cmIfOpenApDtl.glblCmpyCd.setValue(globalCompanyCode);
                cmIfOpenApDtl.apInvId.setValue(rs.getString(AP_INV_ID)); 
                cmIfOpenApDtl.apVndCd.setValue(rs.getString(AP_VND_CD));
                cmIfOpenApDtl.apVndInvNum.setValue(rs.getString(AP_VND_INV_NUM));
                cmIfOpenApDtl.apVndInvSqNum.setValue(rs.getString(AP_VND_INV_SQ_NUM));
                cmIfOpenApDtl.remSqNum.setValue(rs.getString(REM_SQ_NUM));
                cmIfOpenApDtl.vndCd.setValue(rs.getString(VND_CD));
                cmIfOpenApDtl.vndInvNum.setValue(rs.getString(VND_INV_NUM));
                cmIfOpenApDtl.vndInvSqNum.setValue(rs.getString(VND_INV_SQ_NUM));
                cmIfOpenApDtl.apInvLineNum.setValue(rs.getString(AP_INV_LINE_NUM));
                S21FastTBLAccessor.findByKeyForUpdate(cmIfOpenApDtl);
                cmIfOpenApDtl.ifProcStsCd.setValue(IF_PROC_STS_CD_C);
                cmIfOpenApDtlTmsgs[iCnt] = cmIfOpenApDtl;

                // Count up by a record.
                iCnt++;

                // Count up Interface table Unit Id.
                unitId++;

                // Bulk Insert for being up performance.
                iRet = 0;
                iRetUp = 0;

                if (iCnt == INT_COM_LIM) {
                    // Insert
                    iRet = S21FastTBLAccessor.insert(afbi014501Tmsgs);
                    if (iRet > 0) {
                        // Success, none handling
                    } else {
                        rollback();
                        commitCount0145 = 0;
                        throw new S21AbendException(ZZBM0074E);
                    }

                    // Update
                    iRetUp = S21FastTBLAccessor.update(cmIfOpenApDtlTmsgs);
                    if (iRetUp > 0) {
                        // Success, none handling
                    } else {
                        rollback();
                        commitCount0145 = 0;
                        throw new S21AbendException(NFBM0028E);
                    }

                    commitCount0145 = commitCount0145 + iRet;
                    // Init Arrary
                    initTMsgArray();
                    iCnt = 0;

                }

            }

            return Boolean.TRUE;
        }
    }

    /**
     * Change Arrary size of NFBI0145_01TMsg Array for last records
     */
    public NFBI0145_01TMsg[] changeArraySize(NFBI0145_01TMsg[] arrayRec, int newsize) {

        NFBI0145_01TMsg copyMsgs[] = arrayRec.clone();
        arrayRec = new NFBI0145_01TMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }
        return arrayRec;
    }

    /**
     * Change Arrary size of CM_IF_OPEN_AP_DTLTMsg Array for last
     * records
     */
    public CM_IF_OPEN_AP_DTLTMsg[] changeArraySize(CM_IF_OPEN_AP_DTLTMsg[] arrayRec, int newsize) {

        CM_IF_OPEN_AP_DTLTMsg copyMsgs[] = arrayRec.clone();
        arrayRec = new CM_IF_OPEN_AP_DTLTMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }
        return arrayRec;
    }

    /**
     * Init NFBI0145_01TMsg and CM_IF_OPEN_AP_DTLTMsg Array
     */
    public void initTMsgArray() {

        this.afbi014501Tmsgs = new NFBI0145_01TMsg[INT_COM_LIM];
        this.cmIfOpenApDtlTmsgs = new CM_IF_OPEN_AP_DTLTMsg[INT_COM_LIM];

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
