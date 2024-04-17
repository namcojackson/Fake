package com.canon.cusa.s21.batch.NFA.NFAB105001;

import static com.canon.cusa.s21.batch.NFA.NFAB105001.NFAB105001Constant.*;
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
import business.db.COA_GL_CMBNTMsg;
import business.db.COA_GL_CMBN_4_SEGTMsg;
import business.db.NFAI0100_01TMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Daily Exchange Rate Notification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/11/2016   CSAI            K.Uramori       Create          N/A
 * 09/08/2016   Hitachi         T.Tsuchida      Update          QC#14290
 * 2016/10/17   Hitachi         K.Kojima        Update          QC#14039
 * </pre>
 */
public class NFAB105001 extends S21BatchMain {
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
            throw new S21AbendException(NFAB105001Constant.ZZZM9025E, new String[] {"Global Company Code" });
        }
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter param = new S21SsmExecutionParameter();
        param.setFetchSize(NFAB105001Constant.BULK_INSERT_CNT);
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
        BigDecimal oldestTrx = tranAccessor.searchIntegrationRecordOldest(NFAB105001Constant.INTFC_ID);

        // Get the Latest transaction ID.
        BigDecimal latestTrx = tranAccessor.searchIntegrationRecordLatest(NFAB105001Constant.INTFC_ID);

        // Normal End if no integration data is found.
        if (oldestTrx == null || latestTrx == null) {
            EZDDebugOutput.println(1, "Not found integration data.", this);
            return;
        }

        while (oldestTrx != null) {
        // Get the record from latest transaction
            if (oldestTrx.equals(latestTrx)) {

                // set new data
                setNewCmbn(latestTrx);

                // set new data for reactivation (ezcancelflag update)
                setReactivateCmbn(latestTrx);

                // set data to be updated
                setUpdCmbn(latestTrx);

                // set data to be deleted
                setDelCmbn(latestTrx);

            }

            // Update the transaction ID to PROCESSED_FLAG = '1'
            tranAccessor.endIntegrationProcess(NFAB105001Constant.INTFC_ID, oldestTrx);

            delIFData(oldestTrx);

            // Get the Oldest transaction ID to PROCESSED_FLAG = '0'
            oldestTrx = tranAccessor.searchIntegrationRecordOldest(NFAB105001Constant.INTFC_ID);

        }

        // START 2016/10/17 K.Kojima [QC#14039,ADD]
        if (this.normCnt > 1) {
            // delete COA_GL_CMBN_4_SEG
            delCoaGlCmbn4Seg();

            // insert COA_GL_CMBN_4_SEG
            insCoaGlCmbn4Seg();
        }
        // END 2016/10/17 K.Kojima [QC#14039,ADD]

        commit();

    }

    private void delIFData(BigDecimal transactionId) {

        NFAI0100_01TMsg tmsg = new NFAI0100_01TMsg();

        tmsg.transactionId.setValue(transactionId);
        int rtn = S21FastTBLAccessor.removeByPartialValue(tmsg, new String[] {"transactionId" });

        if (rtn < 0) {
         // error
            rollback();
            throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to delete IF."});
        }
    }

    private void insert(ResultSet rs) {
        List<COA_GL_CMBNTMsg> listTmsg = new ArrayList<COA_GL_CMBNTMsg>();

        try {
            while (rs.next()) {
                listTmsg.add(setCreateValue(rs));
                if (NFAB105001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    COA_GL_CMBNTMsg[] arryTMsg = listTmsg.subList(0, NFAB105001Constant.BULK_INSERT_CNT).toArray(new COA_GL_CMBNTMsg[NFAB105001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.insert(arryTMsg);
                    if (rtn != NFAB105001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to insert."});
                    }
                    // reset list
                    this.normCnt += listTmsg.size();
                    listTmsg.subList(0, NFAB105001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (listTmsg.size() != 0) {
                // process rest of list
                COA_GL_CMBNTMsg[] arryTMsg = listTmsg.toArray(new COA_GL_CMBNTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.insert(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to insert."});
                }
                this.normCnt += listTmsg.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void reactivate(ResultSet rs) {
        List<COA_GL_CMBNTMsg> listTmsg = new ArrayList<COA_GL_CMBNTMsg>();

        try {
            while (rs.next()) {
//                EZDTBLAccessor.create(setCreateValue(rs));
//                this.normCnt += 1;
                listTmsg.add(setCreateValue(rs));
                if (NFAB105001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    COA_GL_CMBNTMsg[] arryTMsg = listTmsg.subList(0, NFAB105001Constant.BULK_INSERT_CNT).toArray(new COA_GL_CMBNTMsg[NFAB105001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.removePhysical(arryTMsg);
                    if (rtn != NFAB105001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to reactivate - delete."});
                    }
                    rtn = S21FastTBLAccessor.insert(arryTMsg);
                    if (rtn != NFAB105001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to reactivate - insert."});
                    }
                    // reset list
                    this.normCnt += listTmsg.size();
                    listTmsg.subList(0, NFAB105001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (listTmsg.size() != 0) {
                COA_GL_CMBNTMsg[] arryTMsg = listTmsg.toArray(new COA_GL_CMBNTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.removePhysical(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to reactivate - delete."});
                }
                rtn = S21FastTBLAccessor.insert(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to reactivate - insert."});
                }
                // reset list
                this.normCnt += listTmsg.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void update(ResultSet rs) {
        List<COA_GL_CMBNTMsg> tmpList = new ArrayList<COA_GL_CMBNTMsg>();

        try {
            while (rs.next()) {
                tmpList.add(setUpdateValue(rs));
                if (NFAB105001Constant.BULK_INSERT_CNT == tmpList.size()) {
                    COA_GL_CMBNTMsg[] arryTMsg = tmpList.subList(0, NFAB105001Constant.BULK_INSERT_CNT).toArray(new COA_GL_CMBNTMsg[NFAB105001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.update(arryTMsg);
                    if (rtn != NFAB105001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to update."});
                    }
                    // reset list
                    this.normCnt += tmpList.size();
                    tmpList.subList(0, NFAB105001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (tmpList.size() != 0) {
                // process rest of list
                COA_GL_CMBNTMsg[] arryTMsg = tmpList.toArray(new COA_GL_CMBNTMsg[tmpList.size()]);
                int rtn = S21FastTBLAccessor.update(arryTMsg);
                if (rtn != tmpList.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to update."});
                }
                this.normCnt += tmpList.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void delete(ResultSet rs) {
        List<COA_GL_CMBNTMsg> listTmsg = new ArrayList<COA_GL_CMBNTMsg>();

        try {
            while (rs.next()) {
                listTmsg.add(setCreateValue(rs));
                if (NFAB105001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    COA_GL_CMBNTMsg[] arryTMsg = listTmsg.subList(0, NFAB105001Constant.BULK_INSERT_CNT).toArray(new COA_GL_CMBNTMsg[NFAB105001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.removeLogical(arryTMsg);
                    if (rtn != NFAB105001Constant.BULK_INSERT_CNT) {
                        // error
                        rollback();
                        throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to delete."});
                    }
                    // reset list
                    this.normCnt += listTmsg.size();
                    listTmsg.subList(0, NFAB105001Constant.BULK_INSERT_CNT).clear();
                }
            }
            if (listTmsg.size() != 0) {
                // process rest of list
                COA_GL_CMBNTMsg[] arryTMsg = listTmsg.toArray(new COA_GL_CMBNTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.removeLogical(arryTMsg);
                if (rtn != listTmsg.size()) {
                    // error
                    rollback();
                    throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to delete."});
                }
                this.normCnt += listTmsg.size();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    private void setNewCmbn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("insert", "I");

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getNewCmbn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            insert(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private COA_GL_CMBNTMsg setUpdateValue(ResultSet rs) {
        COA_GL_CMBNTMsg inParam = setCreateValue(rs);
        String enblFlg = inParam.coaGlCmbnEnblFlg.getValue();
        String stsCd = inParam.coaGlCmbnStsCd.getValue();

        inParam = (COA_GL_CMBNTMsg) S21FastTBLAccessor.findByKeyForUpdate(inParam);

        // values to be updated
        setValue(inParam.coaGlCmbnEnblFlg, enblFlg);
        setValue(inParam.coaGlCmbnStsCd, stsCd);
        return inParam;
    }

    private COA_GL_CMBNTMsg setCreateValue(ResultSet rs) {
        COA_GL_CMBNTMsg inParam = new COA_GL_CMBNTMsg();
        try {
            setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            setValue(inParam.coaGlCmbnPk, rs.getBigDecimal("COA_GL_CMBN_PK"));
            setValue(inParam.coaCmpyCd, rs.getString("COA_CMPY_CD"));
            setValue(inParam.coaBrCd, rs.getString("COA_BR_CD"));
            setValue(inParam.coaCcCd, rs.getString("COA_CC_CD"));
            setValue(inParam.coaAcctCd, rs.getString("COA_ACCT_CD"));
            setValue(inParam.coaProdCd, rs.getString("COA_PROD_CD"));
            setValue(inParam.coaChCd, rs.getString("COA_CH_CD"));
            setValue(inParam.coaAfflCd, rs.getString("COA_AFFL_CD"));
            setValue(inParam.coaProjCd, rs.getString("COA_PROJ_CD"));
            setValue(inParam.coaExtnCd, rs.getString("COA_EXTN_CD"));
            setValue(inParam.coaGlCmbnStsCd, rs.getString("COA_GL_CMBN_STS_CD"));
            setValue(inParam.coaId, rs.getBigDecimal("COA_ID"));
            setValue(inParam.coaGlCmbnTpCd, rs.getString("COA_GL_CMBN_TP_CD"));
            setValue(inParam.coaGlCmbnEnblFlg, rs.getString("COA_GL_CMBN_ENBL_FLG"));
            setValue(inParam.coaGlCmbnPstgAllwFlg, rs.getString("COA_GL_CMBN_PSTG_ALLW_FLG"));
            setValue(inParam.coaGlCmbnBdgAllwFlg, rs.getString("COA_GL_CMBN_BDG_ALLW_FLG"));
            setValue(inParam.coaStartActvDt, rs.getString("COA_START_ACTV_DT"));
            setValue(inParam.coaEndActvDt, rs.getString("COA_END_ACTV_DT"));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private void setReactivateCmbn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("insert", "I");

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getReactivateCmbn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            reactivate(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void setUpdCmbn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("upd", "U");

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getUpdatedCmbn", queryParam, this.execParam);
            rs = stmt.executeQuery();
            update(rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void setDelCmbn(BigDecimal transactionId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("del", "D");

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getDelCmbn", queryParam, this.execParam);
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
        new NFAB105001().executeBatch(NFAB105001.class.getSimpleName());
    }

    // START 2016/10/17 K.Kojima [QC#14039,ADD]
    /**
     * delCoaGlCmbn4Seg
     */
    private void delCoaGlCmbn4Seg() {
        COA_GL_CMBN_4_SEGTMsg tMsg = new COA_GL_CMBN_4_SEGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        int rtn = S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
        if (rtn < 0) {
            rollback();
            throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to COA_GL_CMBN_4_SEG." });
        }
    }

    /**
     * insCoaGlCmbn4Seg
     */
    private void insCoaGlCmbn4Seg() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getCoaGlCmbnFor4Seg", queryParam, this.execParam);
            rs = stmt.executeQuery();
            List<COA_GL_CMBN_4_SEGTMsg> listTmsg = new ArrayList<COA_GL_CMBN_4_SEGTMsg>();
            while (rs.next()) {
                COA_GL_CMBN_4_SEGTMsg tMsg = new COA_GL_CMBN_4_SEGTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.coaGlCmbn4SegCd, rs.getString(COA_GL_CMBN_4_SEG_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, rs.getString(COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, rs.getString(COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, rs.getString(COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.coaChCd, rs.getString(COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.coaGlCmbnActvFlg, ZYPConstant.FLG_ON_Y);
                listTmsg.add(tMsg);
                if (NFAB105001Constant.BULK_INSERT_CNT == listTmsg.size()) {
                    COA_GL_CMBN_4_SEGTMsg[] arryTMsg = listTmsg.toArray(new COA_GL_CMBN_4_SEGTMsg[NFAB105001Constant.BULK_INSERT_CNT]);
                    int rtn = S21FastTBLAccessor.insert(arryTMsg);
                    if (rtn != NFAB105001Constant.BULK_INSERT_CNT) {
                        rollback();
                        throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to insert COA_GL_CMBN_4_SEG." });
                    }
                    listTmsg = new ArrayList<COA_GL_CMBN_4_SEGTMsg>();
                }
            }
            if (listTmsg.size() > 0) {
                COA_GL_CMBN_4_SEGTMsg[] arryTMsg = listTmsg.toArray(new COA_GL_CMBN_4_SEGTMsg[listTmsg.size()]);
                int rtn = S21FastTBLAccessor.insert(arryTMsg);
                if (rtn != listTmsg.size()) {
                    rollback();
                    throw new S21AbendException(NFAB105001Constant.NFAM0035E, new String[] {NFAB105001Constant.MSG_PARAM, "Unable to insert COA_GL_CMBN_4_SEG." });
                }
                listTmsg = new ArrayList<COA_GL_CMBN_4_SEGTMsg>();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    // END 2016/10/17 K.Kojima [QC#14039,ADD]
}
