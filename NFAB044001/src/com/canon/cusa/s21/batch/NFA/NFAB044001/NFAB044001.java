/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB044001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.ACCT_EXCH_RATESTMsg;
import business.db.NFAI0050_01TMsg;
import business.parts.NFACommonJrnlEntryConstant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import parts.dbcommon.EZDTBLAccessor;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/12/2013   CSAI            K.Uramori       Create          N/A
 * </pre>
 */
public class NFAB044001 extends S21BatchMain implements NFAB044001Constant, NFACommonJrnlEntryConstant {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCnt = 0;

    /** Process Date */
    private static String procDt = "";

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NFAB044001().executeBatch(NFAB044001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        procDt = ZYPDateUtil.getBatProcDate();

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        S21TransactionTableAccessor tranAccessor = new S21TransactionTableAccessor();

        // Get the Oldest transaction ID.
        BigDecimal oldestTrx = tranAccessor.searchIntegrationRecordOldest(INTFC_ID);
        // Get the Latest transaction ID.
        BigDecimal latestTrx = tranAccessor.searchIntegrationRecordLatest(INTFC_ID);

        // Normal End if no integration data is found.
        if (oldestTrx == null || latestTrx == null) {
            EZDDebugOutput.println(1, "Integration data not found.", this);
            return;
        }

        // process only latest transaction
        while (oldestTrx != null && !oldestTrx.equals(latestTrx)) {

            S21InfoLogOutput.printlnv(ZZIM0009I, INTFC_ID, oldestTrx);
            // Update the transaction ID to PROCESSED_FLAG = '1'
            tranAccessor.endIntegrationProcess(INTFC_ID, oldestTrx);

            // Get the Oldest transaction ID to PROCESSED_FLAG = '0'
            oldestTrx = tranAccessor.searchIntegrationRecordOldest(INTFC_ID);
        }

        if (!doProcessDailyRate(latestTrx)) {
            rollback();
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        } else {
            if (!doProcessMonthlyRate(latestTrx)) {
                rollback();
                // abend
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            }
        }

        // clear IF table
        // 2013/10/10 Keep IF
        //if (!clearIFtable()) {
        //    rollback();
            // abend
        //    throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        //}

       // Update the transaction ID to PROCESSED_FLAG = '1'
        tranAccessor.endIntegrationProcess(INTFC_ID, latestTrx);
        
        commit();

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    private boolean doProcessDailyRate(BigDecimal transactionId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("procDate", procDt);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);

        return (Boolean) this.ssmBatchClient.queryObject("getRecordForDailyExchRates", queryParam, new DailyRateProcHandler());
    }

    private boolean doProcessMonthlyRate(BigDecimal transactionId) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("procDate", procDt);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);

        return (Boolean) this.ssmBatchClient.queryObject("getRecordForMonthlyExchRates", queryParam, new MonthlyRateProcHandler());
    }


    /**
     * DailyRateProcHandler class
     * @author H00180
     *
     */
    private final class DailyRateProcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

                while (rs.next()) {

                    // check if record already exists
                    if (!NONE.equals(rs.getString(IS_EXISTS))) {
                        // delete first
                        // insert new
                        ACCT_DLY_ACTL_EXCH_RATESTMsg tmsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();

                        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(tmsg.ccyCd, rs.getString(TO_CCY_CD));
                        ZYPEZDItemValueSetter.setValue(tmsg.actlExchRateEntDt, rs.getString(ACTL_EXCH_RATE_ENT_DT));

                        EZDTBLAccessor.remove(tmsg);
                    }

                    // insert new
                    ACCT_DLY_ACTL_EXCH_RATESTMsg tmsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();

                    ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tmsg.ccyCd, rs.getString(TO_CCY_CD));
                    ZYPEZDItemValueSetter.setValue(tmsg.actlExchRateEntDt, rs.getString(ACTL_EXCH_RATE_ENT_DT));
                    ZYPEZDItemValueSetter.setValue(tmsg.actlExchRate, rs.getBigDecimal(ACTL_EXCH_RATE));

                    createRecord(tmsg);
                }

                if (tMsgCnt != 0) {
                    createRecord(null);
                }

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        }
    }

    /**
     * MonthlyRate ProcHandler Class
     * @author H00180
     *
     */
    private final class MonthlyRateProcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

                while (rs.next()) {

                    // check if record already exists
                    if (!NONE.equals(rs.getString(IS_EXISTS))) {
                        // update ACG_EXCH_RATE
                        ACCT_EXCH_RATESTMsg tmsg = new ACCT_EXCH_RATESTMsg();

                        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(tmsg.ccyCd, rs.getString(TO_CCY_CD));
                        ZYPEZDItemValueSetter.setValue(tmsg.applYrMth, rs.getString(APPL_YR_MTH));

                        tmsg = (ACCT_EXCH_RATESTMsg) EZDTBLAccessor.findByKey(tmsg);

                        ZYPEZDItemValueSetter.setValue(tmsg.avgExchRate, rs.getBigDecimal(AVG_EXCH_RATE));

                        EZDTBLAccessor.update(tmsg);

                        commitCount += 1;
                    } else {

                        // insert new
                        ACCT_EXCH_RATESTMsg tmsg = new ACCT_EXCH_RATESTMsg();

                        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(tmsg.ccyCd, rs.getString(TO_CCY_CD));
                        ZYPEZDItemValueSetter.setValue(tmsg.applYrMth, rs.getString(APPL_YR_MTH));
                        ZYPEZDItemValueSetter.setValue(tmsg.stdExchRate, rs.getBigDecimal(STD_EXCH_RATE));
                        ZYPEZDItemValueSetter.setValue(tmsg.avgExchRate, rs.getBigDecimal(AVG_EXCH_RATE));

                        createRecord(tmsg);
                    }
                }

                if (tMsgCnt != 0) {
                    createRecord(null);
                }

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        }
    }

    private void createRecord(EZDTMsg tMsg) {

        if (tMsg != null) {
            tMsgCreate[tMsgCnt] = tMsg;
            tMsgCnt += 1;

        } else { // array may be not full
            tMsgCreate = changeArraySize(tMsgCreate, tMsgCnt);
        }

        // per 10000 lines
        if (tMsgCnt >= BULK_INSERT_COUNT || tMsg == null) {

            int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

            // if passed records' count and return count don't
            // match, error
            if (retCnt != tMsgCnt) {
                throw new S21AbendException(ZZBM0074E);
            }
            commitCount += tMsgCnt;
            // initialize
            tMsgCnt = 0;
            tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    private EZDTMsg[] changeArraySize(EZDTMsg[] arrayRec, int newsize) {

        EZDTMsg[] copyMsgs = arrayRec.clone();
        arrayRec = new EZDTMsg[newsize];
        // copy data
        for (int i = 0; i < newsize; i++) {
            arrayRec[i] = copyMsgs[i];
        }
        return arrayRec;
    }

    private boolean clearIFtable() {

        NFAI0050_01TMsg tmsg = new NFAI0050_01TMsg();

        ZYPEZDItemValueSetter.setValue(tmsg.interfaceId, INTFC_ID);

        try {
            S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {INTERFACE_ID });

        } catch (EZDAbendException ex) {
            errMsg = ex.getMessage();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, 0);
        System.out.println("=========errMsg : " + errMsg);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
