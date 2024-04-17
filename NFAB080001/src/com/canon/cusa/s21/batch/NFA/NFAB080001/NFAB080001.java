/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB080001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.MDSE_DLY_SNAP_SHOTTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 * This batch copy the data from MDSE to MDSE_DLY_SNAP_SHOT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/11/2009   CSA             N.Sasaki        Create          N/A
 * 02/17/2016   CSAI            K.Uramori       Update          CSA Update
 * 2016/08/22   Hitachi         J.Kim           Update          QC#13524
 * </pre>
 */
public class NFAB080001 extends S21BatchMain implements ZYPConstant, NFAB080001Constant, NFACommonJrnlEntryConstant {

    // START 2016/08/22 J.Kim [QC#13524,DEL]
    // /** SSM Batch Clinent */
    // private S21SsmBatchClient ssmBatchClient;
    // END 2016/08/22 J.Kim [QC#13524,DEL]

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

    // START 2016/08/22 J.Kim [QC#13524,ADD]
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // END 2016/08/22 J.Kim [QC#13524,ADD]

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        // Get parameter (PROC_DT)
        procDt = S21BatchUtil.getInputParam1();

        new NFAB080001().executeBatch(NFAB080001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        // START 2016/08/22 J.Kim [QC#13524,DEL]
        // this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END 2016/08/22 J.Kim [QC#13524,DEL]
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        if (procDt == null || procDt.equals(BLANK)) {
            procDt = ZYPDateUtil.getBatProcDate();
        }

        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        //--- start add 2016/02/17
        if (!removeOldData()) {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }
        
        if (!updateLtstFlg()) {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }
        //---- end 2016/02/17
        
        S21InfoLogOutput.println("mainRoutine Method Start");

        // START 2016/08/22 J.Kim [QC#13524,MOD]
        // Map<String, String> queryParam = new HashMap<String, String>();
        // queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // queryParam.put("rgtnStsCdReadyForOrderTaking", RGTN_STS_CD_READY_FOR_ORDER_TAKING);
        // queryParam.put("rgtnStsCdTerminated", RGTN_STS_CD_TERMINATED);
        // queryParam.put("rgtnStsCdReadyForReceiving", RGTN_STS_CD_READY_FOR_RECEIVING);
        // queryParam.put("rgtnStsCdPendingCompletion", RGTN_STS_CD_PENDING_COMPLETION);
        ///Boolean result = (Boolean) this.ssmBatchClient.queryObject("getMdse", queryParam, new DBProc());
        Boolean result = doProcessQueryResult();
        // END 2016/08/22 J.Kim [QC#13524,MOD]

        if (result) {
            commit();
        } else {
            //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    private boolean updateLtstFlg() {

        // START 2016/08/22 J.Kim [QC#13524,MOD]
        // update Latest Flag to "N" for existing data
        // MDSE_DLY_SNAP_SHOTTMsg tmsg = new MDSE_DLY_SNAP_SHOTTMsg();
        // MDSE_DLY_SNAP_SHOTTMsg updTmsg = new MDSE_DLY_SNAP_SHOTTMsg();
        //
        // tmsg.glblCmpyCd.setValue(this.glblCmpyCd);
        // tmsg.ltstRecFlg.setValue(FLG_ON_Y);
        //
        // updTmsg.ltstRecFlg.setValue(FLG_OFF_N);
        //
        // int rtn = S21FastTBLAccessor.updateByPartialValue(tmsg, new String[] {"glblCmpyCd", "ltstRecFlg"}, updTmsg, new String[] {"ltstRecFlg"});
        //
        // if (rtn < 0) {
        //    return false;
        // }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<MDSE_DLY_SNAP_SHOTTMsg> mdssTmsgList = new ArrayList<MDSE_DLY_SNAP_SHOTTMsg>();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("ltstRecFlg", FLG_ON_Y);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMdseDlySnapShotData", queryParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                MDSE_DLY_SNAP_SHOTTMsg inMdssTmsg = new MDSE_DLY_SNAP_SHOTTMsg();
                ZYPEZDItemValueSetter.setValue(inMdssTmsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(inMdssTmsg.cratDt, rs.getString("CRAT_DT"));
                ZYPEZDItemValueSetter.setValue(inMdssTmsg.mdseCd, rs.getString("MDSE_CD"));
                MDSE_DLY_SNAP_SHOTTMsg outMdssTmsg = (MDSE_DLY_SNAP_SHOTTMsg) S21FastTBLAccessor.findByKey(inMdssTmsg);
                if (outMdssTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(outMdssTmsg.ltstRecFlg, FLG_OFF_N);
                    mdssTmsgList.add(inMdssTmsg);
                }

                if (mdssTmsgList.size() == MAX_NUMBER) {
                    int rtn = S21FastTBLAccessor.update(mdssTmsgList.toArray(new MDSE_DLY_SNAP_SHOTTMsg[mdssTmsgList.size()]));
                    if (rtn != MAX_NUMBER) {
                        return false;
                    }
                    mdssTmsgList.clear();
                }
            }

            if (mdssTmsgList.size() > 0) {
                int rtn = S21FastTBLAccessor.update(mdssTmsgList.toArray(new MDSE_DLY_SNAP_SHOTTMsg[mdssTmsgList.size()]));
                if (rtn != mdssTmsgList.size()) {
                    return false;
                }
                mdssTmsgList.clear();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2016/08/22 J.Kim [QC#13524,MOD]

        return true;
    }

    private boolean removeOldData() {

        // START 2016/08/22 J.Kim [QC#13524,MOD]
        // Map<String, String> queryParam = new HashMap<String, String>();
        // queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // queryParam.put("batProcDt", this.procDt);
        // queryParam.put("retenDt", ZYPCodeDataUtil.getNumConstValue(RETEN_DT, glblCmpyCd).toString());
        //
        // List<MDSE_DLY_SNAP_SHOTTMsg> list = (List<MDSE_DLY_SNAP_SHOTTMsg>) this.ssmBatchClient.queryObjectList("getOldData", queryParam);
        //
        // if (list.size() == 0) {
        //    return true;
        // }
        //
        // int rtn = S21FastTBLAccessor.removePhysical(list.toArray(new MDSE_DLY_SNAP_SHOTTMsg[list.size()]));
        //
        // if (rtn != list.size()) {
        //    return false;
        // }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<MDSE_DLY_SNAP_SHOTTMsg> tMsgList = new ArrayList<MDSE_DLY_SNAP_SHOTTMsg>();
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("batProcDt", procDt);
        queryParam.put("retenDt", ZYPCodeDataUtil.getNumConstValue(RETEN_DT, glblCmpyCd).toString());

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getOldData", queryParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                MDSE_DLY_SNAP_SHOTTMsg mdssTMsg = new MDSE_DLY_SNAP_SHOTTMsg();
                ZYPEZDItemValueSetter.setValue(mdssTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
                ZYPEZDItemValueSetter.setValue(mdssTMsg.cratDt, rs.getString("CRAT_DT"));
                ZYPEZDItemValueSetter.setValue(mdssTMsg.mdseCd, rs.getString("MDSE_CD"));
                tMsgList.add(mdssTMsg);

                if (tMsgList.size() == MAX_NUMBER) {
                    int rtn = S21FastTBLAccessor.removePhysical(tMsgList.toArray(new MDSE_DLY_SNAP_SHOTTMsg[tMsgList.size()]));
                    if (rtn != MAX_NUMBER) {
                        return false;
                    }
                    tMsgList.clear();
                }
            }

            if (tMsgList.size() > 0) {
                int rtn = S21FastTBLAccessor.removePhysical(tMsgList.toArray(new MDSE_DLY_SNAP_SHOTTMsg[tMsgList.size()]));
                if (rtn != tMsgList.size()) {
                    return false;
                }
                tMsgList.clear();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        // END 2016/08/22 J.Kim [QC#13524,MOD]

        return true;
    }

    /** DB Process */
    private Boolean doProcessQueryResult() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getMdse", queryParam, execParam);
            rs = stmt.executeQuery();

            //---- start mod 2016/02/17 keep data for retention period
            // Clear table
            //MDSE_DLY_SNAP_SHOTTMsg tMsgDelete = new MDSE_DLY_SNAP_SHOTTMsg();
            //common.setFieldValue(tMsgDelete, "glblCmpyCd", glblCmpyCd);
            //S21FastTBLAccessor.removeByPartialValue(tMsgDelete, new String[] {"glblCmpyCd" });
            //---- end 2016/02/17

            while (rs.next()) {

                final String mdseCd = common.checkNull(rs.getString(MDSE_CD));
                final String mdseNm = common.checkNull(rs.getString(MDSE_NM));
                final String firstProdCtrlCd = common.checkNull(rs.getString(FIRST_PROD_CTRL_CD));
                final String coaProdCd = common.checkNull(rs.getString(COA_PROD_CD));
                final String wrtDownFlg = common.checkNull(rs.getString(WRT_DOWN_FLG));
                final BigDecimal thisMthFobAmt = rs.getBigDecimal(THIS_MTH_FOB_AMT);
                final BigDecimal thisMthInlndFrtAmt = rs.getBigDecimal(THIS_MTH_INLND_FRT_AMT);
                final BigDecimal thisMthIntlFrtAmt = rs.getBigDecimal(THIS_MTH_INTL_FRT_AMT);
                final BigDecimal thisMthImptDtyAmt = rs.getBigDecimal(THIS_MTH_IMPT_DTY_AMT);
                final BigDecimal thisMthInsAmt = rs.getBigDecimal(THIS_MTH_INS_AMT);
                final BigDecimal thisMthTotStdCostAmt = rs.getBigDecimal(THIS_MTH_TOT_STD_COST_AMT);
                final BigDecimal lastMthTotStdCostAmt = rs.getBigDecimal(LAST_MTH_TOT_STD_COST_AMT);
                final BigDecimal nextMthTotStdCostAmt = rs.getBigDecimal(NEXT_MTH_TOT_STD_COST_AMT);
                final String vndCd = common.checkNull(rs.getString(VND_CD));
                final String costCcyCd = common.checkNull(rs.getString(COST_CCY_CD));
                final String rgtnStsCd = common.checkNull(rs.getString(RGTN_STS_CD));
                final String invtyCtrlFlg = common.checkNull(rs.getString(INVTY_CTRL_FLG));
                final String invtyValFlg = common.checkNull(rs.getString(INVTY_VAL_FLG));
                final String coaAcctCd = common.checkNull(rs.getString(COA_ACCT_CD));
                final String mdseCatgCd = common.checkNull(rs.getString(MDSE_CATG_CD));
                final String fobReCalcExclFlg = common.checkNull(rs.getString(FOB_RE_CALC_EXCL_FLG));
                final String mdseTpCd = common.checkNull(rs.getString(MDSE_TP_CD));
                
                MDSE_DLY_SNAP_SHOTTMsg tMsg = new MDSE_DLY_SNAP_SHOTTMsg();

                common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);
                common.setFieldValue(tMsg, "cratDt", procDt);
                common.setFieldValue(tMsg, "mdseCd", mdseCd);
                common.setFieldValue(tMsg, "mdseNm", mdseNm);
                common.setFieldValue(tMsg, "firstProdCtrlCd", firstProdCtrlCd);
                common.setFieldValue(tMsg, "coaProdCd", coaProdCd);
                common.setFieldValue(tMsg, "wrtDownFlg", wrtDownFlg);
                common.setFieldValue(tMsg, "thisMthFobAmt", thisMthFobAmt);
                common.setFieldValue(tMsg, "thisMthInlndFrtAmt", thisMthInlndFrtAmt);
                common.setFieldValue(tMsg, "thisMthIntlFrtAmt", thisMthIntlFrtAmt);
                common.setFieldValue(tMsg, "thisMthImptDtyAmt", thisMthImptDtyAmt);
                common.setFieldValue(tMsg, "thisMthInsAmt", thisMthInsAmt);
                common.setFieldValue(tMsg, "thisMthTotStdCostAmt", thisMthTotStdCostAmt);
                common.setFieldValue(tMsg, "lastMthTotStdCostAmt", lastMthTotStdCostAmt);
                common.setFieldValue(tMsg, "nextMthTotStdCostAmt", nextMthTotStdCostAmt);
                common.setFieldValue(tMsg, "vndCd", vndCd);
                common.setFieldValue(tMsg, "costCcyCd", costCcyCd);
                common.setFieldValue(tMsg, "rgtnStsCd", rgtnStsCd);
                common.setFieldValue(tMsg, "invtyCtrlFlg", invtyCtrlFlg);
                common.setFieldValue(tMsg, "invtyValFlg", invtyValFlg);
                common.setFieldValue(tMsg, "coaAcctCd", coaAcctCd);
                common.setFieldValue(tMsg, "mdseCatgCd", mdseCatgCd);
                common.setFieldValue(tMsg, "fobReCalcExclFlg", fobReCalcExclFlg);
                common.setFieldValue(tMsg, "mdseTpCd", mdseTpCd);
                
                //---- start add 2016/02/17 new fields for CSA
                setValue(tMsg.mdseCstUpdEffFromDt, rs.getString("MDSE_CST_UPD_EFF_FROM_DT"));
                setValue(tMsg.assetRecovCostAmt, rs.getBigDecimal("ASSET_RECOV_COST_AMT"));
                setValue(tMsg.prevAssetRecovCostAmt, rs.getBigDecimal("PREV_ASSET_RECOV_COST_AMT"));
                setValue(tMsg.nextAssetRecovCostAmt, rs.getBigDecimal("NEXT_ASSET_RECOV_COST_AMT"));
                setValue(tMsg.assetRecovCstEffFromDt, rs.getString("ASSET_RECOV_CST_EFF_FROM_DT"));
                setValue(tMsg.revCoaAcctCd, rs.getString("REV_COA_ACCT_CD"));
                setValue(tMsg.cogsCoaAcctCd, rs.getString("COGS_COA_ACCT_CD"));
                setValue(tMsg.expCoaAcctCd, rs.getString("EXP_COA_ACCT_CD"));
                setValue(tMsg.coaMdseTpCd, rs.getString("COA_MDSE_TP_CD"));
                setValue(tMsg.coaCcCd, rs.getString("COA_CC_CD"));
                setValue(tMsg.lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
                setValue(tMsg.ltstRecFlg, FLG_ON_Y);
                //---- end 2016/02/17
                
                setValue(tMsg.coaMdseTpCd, rs.getString("COA_MDSE_TP_CD"));
                setValue(tMsg.ltstRecFlg, "Y");
                
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
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
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

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
