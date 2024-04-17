/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB110001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.DS_ACCT_CUST_MTH_SNAPTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
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
 * This batch copy the data from BILL_TO_CUST to CUST_MTH_SNAP_SHOT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/19/2016   CSAI            K.Uramori       Create          Copy of NFAB083001
 * </pre>
 */
public class NFAB110001 extends S21BatchMain implements NFAB110001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
    /** SSM Batch Clinent */
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

    /** Process Date */
    private static String procDt = "";

    /** Process Date */
    private static String procYrMth = "";

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCnt = 0;

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry common = new NFACommonJrnlEntry();

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        // Get parameter (PROC_DT)
        procDt = S21BatchUtil.getInputParam1();

        new NFAB110001().executeBatch(NFAB110001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        if (procDt == null || procDt.equals(BLANK)) {
            procDt = ZYPDateUtil.getBatProcDate();
        }
        procYrMth = ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyyMM");

        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        
        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getDsAcctCust", queryParam, new DBProc());

        if (result) {
            commit();
        } else {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /** DB Process */
    private class DBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                // Clear table
                DS_ACCT_CUST_MTH_SNAPTMsg tMsgDelete = new DS_ACCT_CUST_MTH_SNAPTMsg();
                common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd" });

                while (rs.next()) {

                    DS_ACCT_CUST_MTH_SNAPTMsg tMsg = new DS_ACCT_CUST_MTH_SNAPTMsg();

                    setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    setValue(tMsg.cratYrMth, procYrMth);
                    setValue(tMsg.dsAcctCustPk, rs.getBigDecimal(SELL_TO_CUST_PK));
                    setValue(tMsg.dsAcctNum, rs.getString(SELL_TO_CUST_CD));
                    setValue(tMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
                    setValue(tMsg.coaCmpyCd, rs.getString(COA_CMPY_CD));
                    setValue(tMsg.coaBrCd, rs.getString(COA_BR_CD));
                    setValue(tMsg.coaCcCd, rs.getString(COA_CC_CD));
                    setValue(tMsg.coaAcctCd, rs.getString(COA_ACCT_CD));
                    setValue(tMsg.coaProdCd, rs.getString(COA_PROD_CD));
                    setValue(tMsg.coaChCd, rs.getString(COA_CH_CD));
                    setValue(tMsg.coaAfflCd, rs.getString(COA_AFFL_CD));
                    setValue(tMsg.coaProjCd, rs.getString(COA_PROJ_CD));
                    setValue(tMsg.coaExtnCd, rs.getString(COA_EXTN_CD));
                        
                    createRecord(tMsg);
                } // while

                if (tMsgCnt != 0) {
                    createRecord(null);
                }

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        }

        /**
         * <pre>
         *  Create New Record
         * </pre>
         * 
         * @param EZDTMsg tMsg
         * @throws JrnlCommonException JrnlCommonException
         */
        private void createRecord(EZDTMsg tMsg) throws NFACommonJrnlEntry.JrnlCommonException {

            if (tMsg != null) {
                tMsgCreate[tMsgCnt] = tMsg;
                tMsgCnt += 1;

            } else { // array may be not full
                tMsgCreate = common.changeArraySize(tMsgCreate, tMsgCnt);
            }

            // per 10000 lines
            if (tMsgCnt >= BULK_INSERT_COUNT || tMsg == null) {

                int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

                // if passed records' count and return count don't
                // match, error
                if (retCnt != tMsgCnt) {
                    throw common.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += tMsgCnt;
                // initialize
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
