/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB086001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.TOC_DLY_SNAP_SHOTTMsg;
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
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch copy the data from S21_ORG to TOC_DLY_SNAP_SHOT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/11/2009   CSA             N.Sasaki        Create          N/A
 * </pre>
 */
public class NFAB086001 extends S21BatchMain implements NFAB086001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
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

        new NFAB086001().executeBatch(NFAB086001.class.getSimpleName());

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

        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("rgtnStsCdReadyForOrderTaking", RGTN_STS_CD_READY_FOR_ORDER_TAKING);
        queryParam.put("rgtnStsCdTerminated", RGTN_STS_CD_TERMINATED);

        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getS21Org", queryParam, new DBProc());

        if (result) {
            commit();
        } else {
            S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            rollback();
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /** DB Process */
    private class DBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                // Clear table
                TOC_DLY_SNAP_SHOTTMsg tMsgDelete = new TOC_DLY_SNAP_SHOTTMsg();
                common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd" });

                while (rs.next()) {

                    final String tocCd = common.checkNull(rs.getString(TOC_CD));
                    final String frthOrgCd = common.checkNull(rs.getString(FRTH_ORG_CD));
                    final String coaBrCd = common.checkNull(rs.getString(COA_BR_CD));
                    final String coaCcCd = common.checkNull(rs.getString(COA_CC_CD));
                    final String coaProdCd = common.checkNull(rs.getString(COA_PROD_CD));
                    final String coaChCd = common.checkNull(rs.getString(COA_CH_CD));
                    final String coaExtnCd = common.checkNull(rs.getString("COA_EXTN_CD"));

                    TOC_DLY_SNAP_SHOTTMsg tMsg = new TOC_DLY_SNAP_SHOTTMsg();

                    common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                    common.setFieldValue(tMsg, "cratDt", procDt);
                    common.setFieldValue(tMsg, "tocCd", tocCd);
                    common.setFieldValue(tMsg, "frthOrgCd", frthOrgCd);
                    common.setFieldValue(tMsg, "coaBrCd", coaBrCd);
                    common.setFieldValue(tMsg, "coaCcCd", coaCcCd);
                    common.setFieldValue(tMsg, "coaProdCd", coaProdCd);
                    common.setFieldValue(tMsg, "coaChCd", coaChCd);
                    common.setFieldValue(tMsg, "coaExtnCd", coaExtnCd);

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
