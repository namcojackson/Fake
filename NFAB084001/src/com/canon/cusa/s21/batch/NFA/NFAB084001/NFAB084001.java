/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB084001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.VND_DLY_SNAP_SHOTTMsg;
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
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch copy the data from VND to VND_DLY_SNAP_SHOT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/11/2009   CSA             N.Sasaki        Create          N/A
 * 02/17/2016   CSAI            K.Uramori       Update          Update for CSA
 * </pre>
 */
public class NFAB084001 extends S21BatchMain implements NFAB084001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
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

        new NFAB084001().executeBatch(NFAB084001.class.getSimpleName());

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

        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getVnd", queryParam, new DBProc());

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
                VND_DLY_SNAP_SHOTTMsg tMsgDelete = new VND_DLY_SNAP_SHOTTMsg();
                common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd" });

                while (rs.next()) {

                    final String vndCd = common.checkNull(rs.getString(VND_CD));
                    final String coaAfflCd = common.checkNull(rs.getString(COA_AFFL_CD));

                    VND_DLY_SNAP_SHOTTMsg tMsg = new VND_DLY_SNAP_SHOTTMsg();

                    common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                    common.setFieldValue(tMsg, "cratDt", procDt);
                    common.setFieldValue(tMsg, "vndCd", vndCd);
                    common.setFieldValue(tMsg, "coaAfflCd", coaAfflCd);

                    //---- start add 2016/02/17 new fields for CSA
                    setValue(tMsg.splyCoaCmpyCd, rs.getString("SPLY_COA_CMPY_CD"));
                    setValue(tMsg.splyCoaBrCd, rs.getString("SPLY_COA_BR_CD"));
                    setValue(tMsg.splyCoaCcCd, rs.getString("SPLY_COA_CC_CD"));
                    setValue(tMsg.splyCoaAcctCd, rs.getString("SPLY_COA_ACCT_CD"));
                    setValue(tMsg.splyCoaProdCd, rs.getString("SPLY_COA_PROD_CD"));
                    setValue(tMsg.splyCoaChCd, rs.getString("SPLY_COA_CH_CD"));
                    setValue(tMsg.splyCoaAfflCd, rs.getString("SPLY_COA_AFFL_CD"));
                    setValue(tMsg.splyCoaProjCd, rs.getString("SPLY_COA_PROJ_CD"));
                    setValue(tMsg.splyCoaExtnCd, rs.getString("SPLY_COA_EXTN_CD"));
                    
                    setValue(tMsg.prePmtCoaCmpyCd, rs.getString("PRE_PMT_COA_CMPY_CD"));
                    setValue(tMsg.prePmtCoaBrCd, rs.getString("PRE_PMT_COA_BR_CD"));
                    setValue(tMsg.prePmtCoaCcCd, rs.getString("PRE_PMT_COA_CC_CD"));
                    setValue(tMsg.prePmtCoaAcctCd, rs.getString("PRE_PMT_COA_ACCT_CD"));
                    setValue(tMsg.prePmtCoaProdCd, rs.getString("PRE_PMT_COA_PROD_CD"));
                    setValue(tMsg.prePmtCoaChCd, rs.getString("PRE_PMT_COA_CH_CD"));
                    setValue(tMsg.prePmtCoaAfflCd, rs.getString("PRE_PMT_COA_AFFL_CD"));
                    setValue(tMsg.prePmtCoaProjCd, rs.getString("PRE_PMT_COA_PROJ_CD"));
                    setValue(tMsg.prePmtCoaExtnCd, rs.getString("PRE_PMT_COA_EXTN_CD"));
                    
                    setValue(tMsg.vndRtrnCoaCmpyCd, rs.getString("VND_RTRN_COA_CMPY_CD"));
                    setValue(tMsg.vndRtrnCoaBrCd, rs.getString("VND_RTRN_COA_BR_CD"));
                    setValue(tMsg.vndRtrnCoaCcCd, rs.getString("VND_RTRN_COA_CC_CD"));
                    setValue(tMsg.vndRtrnCoaAcctCd, rs.getString("VND_RTRN_COA_ACCT_CD"));
                    setValue(tMsg.vndRtrnCoaProdCd, rs.getString("VND_RTRN_COA_PROD_CD"));
                    setValue(tMsg.vndRtrnCoaChCd, rs.getString("VND_RTRN_COA_CH_CD"));
                    setValue(tMsg.vndRtrnCoaAfflCd, rs.getString("VND_RTRN_COA_AFFL_CD"));
                    setValue(tMsg.vndRtrnCoaProjCd, rs.getString("VND_RTRN_COA_PROJ_CD"));
                    setValue(tMsg.vndRtrnCoaExtnCd, rs.getString("VND_RTRN_COA_EXTN_CD"));
                    
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
