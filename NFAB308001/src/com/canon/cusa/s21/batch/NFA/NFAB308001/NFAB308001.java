package com.canon.cusa.s21.batch.NFA.NFAB308001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.BDG_VRSNTMsg;
import business.db.NFAI3080_01TMsg;

import com.canon.cusa.s21.batch.NFA.NFAB308001.constant.NFAB308001Constant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * NFAB308001
 * Budget IF Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2016   Hitachi         N.Arai          Create          N/A
 * </pre>
 */
public class NFAB308001 extends S21BatchMain {
    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Low Level Client */
    private S21SsmExecutionParameter execParam = null;

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.execParam = getExecParam();
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NFAB308001Constant.ZZZM9025E, new String[] {"Global Company Code" });
        }
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter param = new S21SsmExecutionParameter();
        param.setFetchSize(NFAB308001Constant.BULK_INSERT_CNT);
        param.setMaxRows(0);
        param.setFetchDirection(ResultSet.FETCH_FORWARD);
        param.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        param.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return param;
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        S21TransactionTableAccessor tranAccessor = new S21TransactionTableAccessor();

        // Get the Oldest transaction ID.
        BigDecimal oldestTrx = tranAccessor.searchIntegrationRecordOldest(NFAB308001Constant.INTFC_ID);

        // Get the Latest transaction ID.
        BigDecimal latestTrx = tranAccessor.searchIntegrationRecordLatest(NFAB308001Constant.INTFC_ID);

        // Normal End if no integration data is found.
        if (oldestTrx == null || latestTrx == null) {
            EZDDebugOutput.println(1, "Not found integration data.", this);
            return;
        }

        while (oldestTrx != null) {
        // Get the record from latest transaction
            if (oldestTrx.equals(latestTrx)) {

                // set new data
                setNewBdgVrsn(latestTrx);

                // set new data for reactivation (ezcancelflag update)
                setReactivateBdgVrsn(latestTrx);

                // set data to be updated
                setUpdBdgVrsn(latestTrx);

                // set data to be deleted
                setDelBdgVrsn(latestTrx);

            } else {
                // Delete IF data from NFAI3080_01
                delIFData(oldestTrx);
            }

            // Update the transaction ID to PROCESSED_FLAG = '1'
            tranAccessor.endIntegrationProcess(NFAB308001Constant.INTFC_ID, oldestTrx);

            // Get the Oldest transaction ID to PROCESSED_FLAG = '0'
            oldestTrx = tranAccessor.searchIntegrationRecordOldest(NFAB308001Constant.INTFC_ID);

        }

        commit();

    }

    private void delIFData(BigDecimal transactionId) {

        NFAI3080_01TMsg tmsg = new NFAI3080_01TMsg();

        tmsg.transactionId.setValue(transactionId);
        int rtn = S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"transactionId" });

        if (rtn < 0) {
         // error
            rollback();
            throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to delete IF."});
        }
    }

    private void insert(ResultSet rs) {
        List<BDG_VRSNTMsg> listTmsg = new ArrayList<BDG_VRSNTMsg>();

        try {
            while (rs.next()) {
                listTmsg.add(setCreateValue(rs));
                if (NFAB308001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    BDG_VRSNTMsg[] arryTMsg = listTmsg.subList(0, NFAB308001Constant.BULK_INSERT_CNT).toArray(new BDG_VRSNTMsg[NFAB308001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.insert(arryTMsg);
                    if (rtn != NFAB308001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to insert."});
                    }
                    // reset list
                    this.normCnt += listTmsg.size();
                    listTmsg.subList(0, NFAB308001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (listTmsg.size() != 0) {
                // process rest of list
                BDG_VRSNTMsg[] arryTMsg = listTmsg.toArray(new BDG_VRSNTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.insert(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to insert."});
                }
                this.normCnt += listTmsg.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void reactivate(ResultSet rs) {
        List<BDG_VRSNTMsg> listTmsg = new ArrayList<BDG_VRSNTMsg>();

        try {
            while (rs.next()) {
                listTmsg.add(setCreateValue(rs));
                if (NFAB308001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    BDG_VRSNTMsg[] arryTMsg = listTmsg.subList(0, NFAB308001Constant.BULK_INSERT_CNT).toArray(new BDG_VRSNTMsg[NFAB308001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.removePhysical(arryTMsg);
                    if (rtn != NFAB308001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to reactivate - delete."});
                    }
                    rtn = S21FastTBLAccessor.insert(arryTMsg);
                    if (rtn != NFAB308001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to reactivate - insert."});
                    }
                    // reset list
                    this.normCnt += listTmsg.size();
                    listTmsg.subList(0, NFAB308001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (listTmsg.size() != 0) {
                BDG_VRSNTMsg[] arryTMsg = listTmsg.toArray(new BDG_VRSNTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.removePhysical(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to reactivate - delete."});
                }
                rtn = S21FastTBLAccessor.insert(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to reactivate - insert."});
                }
                // reset list
                this.normCnt += listTmsg.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void update(ResultSet rs) {
        List<BDG_VRSNTMsg> tmpList = new ArrayList<BDG_VRSNTMsg>();

        try {
            while (rs.next()) {
                tmpList.add(setUpdateValue(rs));
                if (NFAB308001Constant.BULK_INSERT_CNT == tmpList.size()) {
                    BDG_VRSNTMsg[] arryTMsg = tmpList.subList(0, NFAB308001Constant.BULK_INSERT_CNT).toArray(new BDG_VRSNTMsg[NFAB308001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.update(arryTMsg);
                    if (rtn != NFAB308001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to update."});
                    }
                    // reset list
                    this.normCnt += tmpList.size();
                    tmpList.subList(0, NFAB308001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (tmpList.size() != 0) {
                // process rest of list
                BDG_VRSNTMsg[] arryTMsg = tmpList.toArray(new BDG_VRSNTMsg[tmpList.size()]);
                int rtn = S21FastTBLAccessor.update(arryTMsg);
                if (rtn != tmpList.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to update."});
                }
                this.normCnt += tmpList.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void delete(ResultSet rs) {
        List<BDG_VRSNTMsg> listTmsg = new ArrayList<BDG_VRSNTMsg>();

        try {
            while (rs.next()) {
                listTmsg.add(setCreateValue(rs));
                if (NFAB308001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    BDG_VRSNTMsg[] arryTMsg = listTmsg.subList(0, NFAB308001Constant.BULK_INSERT_CNT).toArray(new BDG_VRSNTMsg[NFAB308001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.removeLogical(arryTMsg);
                    if (rtn != NFAB308001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to delete."});
                    }
                    // reset list
                    this.normCnt += listTmsg.size();
                    listTmsg.subList(0, NFAB308001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (listTmsg.size() != 0) {
                // process rest of list
                BDG_VRSNTMsg[] arryTMsg = listTmsg.toArray(new BDG_VRSNTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.removeLogical(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB308001Constant.NFAM0035E, new String[] {NFAB308001Constant.MSG_PARAM, "Unable to delete."});
                }
                this.normCnt += listTmsg.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void setNewBdgVrsn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId", NFAB308001Constant.INTFC_ID);
        queryParam.put("transactionId", transactionId);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("insert", NFAB308001Constant.INSERT);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getNewBdgVrsn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            insert(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private BDG_VRSNTMsg setUpdateValue(ResultSet rs) {
        BDG_VRSNTMsg inParam = setCreateValue(rs);
        BigDecimal bdgVrsnAmt = inParam.bdgVrsnAmt.getValue();

        inParam = (BDG_VRSNTMsg) S21FastTBLAccessor.findByKeyForUpdate(inParam);

        // values to be updated
        setValue(inParam.bdgVrsnAmt, bdgVrsnAmt);
        return inParam;
    }

    private BDG_VRSNTMsg setCreateValue(ResultSet rs) {
        BDG_VRSNTMsg inParam = new BDG_VRSNTMsg();
        try {
            setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            setValue(inParam.bdgYrMthPerTxt, rs.getString("BDG_YR_MTH_PER_TXT"));
            setValue(inParam.bdgScnNm, rs.getString("BDG_SCN_NM"));
            setValue(inParam.coaId, rs.getBigDecimal("COA_ID"));
            setValue(inParam.bdgScnOpenDt, rs.getString("BDG_SCN_OPEN_DT"));
            setValue(inParam.coaCmpyCd, rs.getString("COA_CMPY_CD"));
            setValue(inParam.coaBrCd, rs.getString("COA_BR_CD"));
            setValue(inParam.coaCcCd, rs.getString("COA_CC_CD"));
            setValue(inParam.coaAcctCd, rs.getString("COA_ACCT_CD"));
            setValue(inParam.coaProdCd, rs.getString("COA_PROD_CD"));
            setValue(inParam.coaChCd, rs.getString("COA_CH_CD"));
            setValue(inParam.coaAfflCd, rs.getString("COA_AFFL_CD"));
            setValue(inParam.coaProjCd, rs.getString("COA_PROJ_CD"));
            setValue(inParam.coaExtnCd, rs.getString("COA_EXTN_CD"));
            setValue(inParam.bdgVrsnAmt, rs.getBigDecimal("BDG_VRSN_AMT"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private void setReactivateBdgVrsn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId", NFAB308001Constant.INTFC_ID);
        queryParam.put("transactionId", transactionId);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("insert", NFAB308001Constant.INSERT);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getReactivateBdgVrsn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            reactivate(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void setUpdBdgVrsn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId", NFAB308001Constant.INTFC_ID);
        queryParam.put("transactionId", transactionId);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("upd", NFAB308001Constant.UPDATE);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getUpdatedBdgVrsn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            update(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void setDelBdgVrsn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId", NFAB308001Constant.INTFC_ID);
        queryParam.put("transactionId", transactionId);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("del", NFAB308001Constant.DELETE);

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getDelBdgVrsn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            delete(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFAB308001().executeBatch(NFAB308001.class.getSimpleName());
    }

}
