/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB110001;

import static com.canon.cusa.s21.batch.NLC.NLCB110001.constant.NLCB110001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ABC_ANLS_RQSTTMsg;
import business.db.ABC_ANLS_RSLTTMsg;
import business.db.ABC_ANLS_RSLT_DTLTMsg;
import business.db.ART10FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>    
 * Batch Program Class for ABC Analysis Result Import
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 07/05/2016   CITS            S.Endo      Create          
 * 
 *</pre>
 */
public class NLCB110001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    // -- Count of processing -------------------
    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    // -- Other Internal Variable ---------------
    /** S21RequestBatchHelper */
    private S21RequestBatchHelper reqbatch = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** Termination code */
    private TERM_CD termCd;

    /** Insert List ABC_ANLS_RSLT_DTLTMsg */
    private List<ABC_ANLS_RSLT_DTLTMsg> detailMsgList = null;

    /** Insert List ABC_ANLS_RQSTTMsg */
    private Map<BigDecimal, ABC_ANLS_RQSTTMsg> reqMsgMap = null;

    /** Insert List ABC_ANLS_RSLTTMsg */
    private List<ABC_ANLS_RSLTTMsg> rsltMsgList = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** ABC Assignment PK */
    private BigDecimal abcAssignmentPk;

    /** ABC Class Number */
    private String abcClassNum;

    /** Latest RequestPK */
    private BigDecimal latestReqPk;

    /**
     * @param args execution parameters
     */
    public static void main(String[] args) {
        new NLCB110001().executeBatch(NLCB110001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization of variable
        termCd = TERM_CD.NORMAL_END;
        // Initialization of S21UserProfileService
        reqbatch = new S21RequestBatchHelper();
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        if (!reqbatch.isRecord()) {
            termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg reqmsg : reqbatch.getRequestList()) {
            doProcess(reqmsg);
        }
    }

    @Override
    protected void termRoutine() {
        // Setting of termination code
        setTermState(termCd, normalCount, errorCount);
    }

    /**
     * do Check & Import Processes
     * @param reqmsg ART10FMgs
     */
    private void doProcess(ART10FMsg reqMsg) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            glblCmpyCd = reqMsg.EZInCompanyCode.getValue();
            String upldRqstPk = reqMsg.EZREQCondition.getValue();
            String upldCsvId = reqMsg.EZREQUserKey.getValue();

            messenger = new ZYPCSVUploadMessenger(upldCsvId, new BigDecimal(upldRqstPk));
            // check item count match requestPK condition
            BigDecimal wrkCount = getCountWrkData(upldRqstPk);
            if (wrkCount.intValue() == 0) {
                messenger.addMessageForFile(MSG_NLCM0192E, null);
                messenger.uploadMessage();
                reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                termCd = TERM_CD.WARNING_END;
                return;
            }

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(COL_UPLD_CSV_RQST_PK, upldRqstPk);
            ps = ssmLLClient.createPreparedStatement("getWrkData", queryParam, execParam());
            rs = ps.executeQuery();
            reqMsgMap = new HashMap<BigDecimal, ABC_ANLS_RQSTTMsg>();
            detailMsgList = new ArrayList<ABC_ANLS_RSLT_DTLTMsg>(wrkCount.intValue());
            rsltMsgList = new ArrayList<ABC_ANLS_RSLTTMsg>(wrkCount.intValue());

            int recordCount = 0;
            String prevItemNumber = "";
            String prevWHCode = "";
            String prevSWHCode = "";
            String prevStockStatusCode = "";
            String prevAbcName = "";
            String prevAbcClassName = "";
            String prevAbcClassTag = "";
            String prevMdseCd = "";
            Boolean firstLoop = true;
            while (rs.next()) {
                recordCount++;
                // 1-1-1).checkMandatory
                if (!checkMandatory(rs)) {
                    detailMsgList = null;
                    continue;
                }
                // 1-1-2).checkKeyItem
                if (!firstLoop) {
                    if (!checkKeyItem(rs, prevAbcName, prevAbcClassName)) {
                        prevAbcName = rs.getString(COL_ABC_ASG_NM);
                        prevAbcClassName = rs.getString(COL_ABC_ANLS_CLS_NM);
                        detailMsgList = null;
                        continue;
                    }
                }
                // 1-1-3).isExistInMaster
                if (!isExistsInMaster(rs)) {
                    detailMsgList = null;
                    continue;
                }
                // 1-1-4).checkDuplicate
                if (!firstLoop) {
                    if (checkDuplicate(rs, prevItemNumber, prevWHCode, prevSWHCode, prevStockStatusCode)) {
                        detailMsgList = null;
                    }
                }
                // 1-1-5).checkClassTag
                if (!firstLoop) {
                    if (!checkClassTag(rs, prevItemNumber, prevWHCode, prevAbcClassTag)) {
                        detailMsgList = null;
                    }
                }
                if (detailMsgList != null) {
                    // create RequestMsg
                    BigDecimal reqPk = addRequest(rs);
                    // create ResultDetailMsg
                    addResultDetail(rs, reqPk);
                    // create ResultMsg
                    addResult(rs, reqPk, prevWHCode, prevMdseCd);
                }
                prevAbcName = rs.getString(COL_ABC_ASG_NM);
                prevAbcClassName = rs.getString(COL_ABC_ANLS_CLS_NM);
                prevAbcClassTag = rs.getString(COL_ABC_ANLS_CLS_TAG_CD);
                prevItemNumber = rs.getString(COL_MDSE_CD);
                prevWHCode = rs.getString(COL_RTL_WH_CD);
                prevSWHCode = rs.getString(COL_RTL_SWH_CD);
                prevStockStatusCode = rs.getString(COL_STK_STS_CD);
                prevMdseCd = rs.getString(COL_MDSE_CD);
                firstLoop = false;
            }
            if (detailMsgList == null) {
                messenger.uploadMessage();
                errorCount += recordCount;
                reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                termCd = TERM_CD.WARNING_END;
            } else {
                updateRequest();
                insertResultDetail();
                updateResult();
                commit();
                messenger.uploadMessage();
                reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
                normalCount += recordCount;
            }
        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * getCountWrkData
     * @param glblCmpyCd String
     * @param upldRqstPk String
     * @return BigDecimal
     */
    private BigDecimal getCountWrkData(String upldRqstPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_UPLD_CSV_RQST_PK, upldRqstPk);
        return (BigDecimal) ssmBatchClient.queryObject("getWrkDataCount", queryParam);
    }

    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * checkMandatory
     * @param rs
     * @return if item value is not null or empty,then return true
     * ,else return false.
     * @throws SQLException
     */
    private boolean checkMandatory(ResultSet rs) throws SQLException {
        boolean checkResult = true;
        // ABC_ASG_NM
        if (!checkMandatoryColumn(rs, COL_ABC_ASG_NM, CSV_ABC_NAME)) {
            checkResult = false;
        }
        // MDSE_CD
        if (!checkMandatoryColumn(rs, COL_MDSE_CD, CSV_ITEM_NUMBER)) {
            checkResult = false;
        }
        // RTL_WH_CD
        if (!checkMandatoryColumn(rs, COL_RTL_WH_CD, CSV_WAREHOUSE_CODE)) {
            checkResult = false;
        }
        // ABC_ANLS_CLS_NM
        if (!checkMandatoryColumn(rs, COL_ABC_ANLS_CLS_NM, CSV_ABC_CLASS_NAME)) {
            checkResult = false;
        }
        // ABC_ANLS_CLS_TAG_CD
        if (!checkMandatoryColumn(rs, COL_ABC_ANLS_CLS_TAG_CD, CSV_ABC_CLASS_TAG)) {
            checkResult = false;
        }
        return checkResult;
    }

    /**
     * checkMandatoryColumn
     * @param rs ResultSet
     * @param targetColumnName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatoryColumn(ResultSet rs, String targetColumnName, String csvColumnName) throws SQLException {
        String targetColumn = rs.getString(targetColumnName);
        if (!ZYPCommonFunc.hasValue(targetColumn)) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, MSG_ZZM9000E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkKeyItem
     * @param rs
     * @param prevAbcName
     * @param prevAbcClassName
     * @return if item value and previous item value is same,then
     * return true ,else return false.
     * @throws SQLException
     */
    private boolean checkKeyItem(ResultSet rs, String prevAbcName, String prevAbcClassName) throws SQLException {
        boolean checkResult = true;
        if (prevAbcName != null) {
            if (!prevAbcName.equals(rs.getString(COL_ABC_ASG_NM))) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0188E, null);
                checkResult = false;
            }
        }
        if (prevAbcClassName != null) {
            if (!prevAbcClassName.equals(rs.getString(COL_ABC_ANLS_CLS_NM))) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0189E, null);
                checkResult = false;
            }
        }
        return checkResult;
    }

    /**
     * uploadMesageForRecord
     * @param upldCsvRqstRowNum BigDecimal
     * @param msgId String
     * @param msgStr String
     */
    private void uploadMesageForRecord(BigDecimal upldCsvRqstRowNum, String msgId, String msgStr) {
        messenger.addMessageForRecord(upldCsvRqstRowNum, msgId, msgStr);
    }

    /**
     * isExistsInMaster
     * @param rs
     * @return if item value is exists in Master,then return true
     * ,else return false.
     * @throws SQLException
     */
    private boolean isExistsInMaster(ResultSet rs) throws SQLException {
        boolean checkResult = true;
        // ABCName
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_ABC_ASG_NM, rs.getObject(COL_ABC_ASG_NM));
        abcAssignmentPk = (BigDecimal) ssmBatchClient.queryObject("getAssignmentInfo", queryParam);

        if (abcAssignmentPk == null) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0148E, COL_ABC_ASG_NM);
            checkResult = false;
        }
        // ItemNumber
        queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_MDSE_CD, rs.getObject(COL_MDSE_CD));
        BigDecimal result = (BigDecimal) ssmBatchClient.queryObject("getMdseInfo", queryParam);
        if (BigDecimal.ZERO.compareTo(result) == 0) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0148E, COL_MDSE_CD);
            checkResult = false;
        }
        // Warehouse Code
        queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_RTL_WH_CD, rs.getObject(COL_RTL_WH_CD));
        result = (BigDecimal) ssmBatchClient.queryObject("getWhInfo", queryParam);
        if (BigDecimal.ZERO.compareTo(result) == 0) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0148E, COL_RTL_WH_CD);
            checkResult = false;
        }
        // Sub Warehouse Code
        if (ZYPCommonFunc.hasValue(rs.getString(COL_RTL_SWH_CD))) {
            queryParam = new HashMap<String, Object>();
            queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(COL_RTL_WH_CD, rs.getObject(COL_RTL_WH_CD));
            queryParam.put(COL_RTL_SWH_CD, rs.getObject(COL_RTL_SWH_CD));
            result = (BigDecimal) ssmBatchClient.queryObject("getSwhInfo", queryParam);
            if (BigDecimal.ZERO.compareTo(result) == 0) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0148E, COL_RTL_SWH_CD);
                checkResult = false;
            }
        }
        // Stock Status Code
        if (ZYPCommonFunc.hasValue(rs.getString(COL_STK_STS_CD))) {
            queryParam = new HashMap<String, Object>();
            queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(COL_STK_STS_CD, rs.getObject(COL_STK_STS_CD));
            result = (BigDecimal) ssmBatchClient.queryObject("getStkStsInfo", queryParam);
            if (BigDecimal.ZERO.compareTo(result) == 0) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0148E, COL_STK_STS_CD);
                checkResult = false;
            }
        }
        // ABC Class Name& ABC Class Tag
        queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_ABC_ANLS_CLS_NM, rs.getObject(COL_ABC_ANLS_CLS_NM));
        queryParam.put(COL_ABC_ANLS_CLS_TAG_CD, rs.getObject(COL_ABC_ANLS_CLS_TAG_CD));
        abcClassNum = (String) ssmBatchClient.queryObject("getAbcClassInfo", queryParam);
        if (abcClassNum == null) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0148E, COL_ABC_ANLS_CLS_NM + " or/and " + COL_ABC_ANLS_CLS_TAG_CD);
            checkResult = false;
        }

        return checkResult;
    }

    /**
     * Check Duplicate
     * @param rs
     * @param prevItemNumber
     * @param prevWHCode
     * @param prevSWHCode
     * @param prevStockStatusCode
     * @return if item value and previous item value is not same,then
     * return true ,else return false.
     * @throws SQLException
     */
    private boolean checkDuplicate(ResultSet rs, String prevItemNumber, String prevWHCode, String prevSWHCode, String prevStockStatusCode) throws SQLException {
        StringBuilder checkString = new StringBuilder();
        checkString.append(rs.getString(COL_MDSE_CD));
        checkString.append(rs.getString(COL_RTL_WH_CD));
        checkString.append(rs.getString(COL_RTL_SWH_CD));
        checkString.append(rs.getString(COL_STK_STS_CD));

        StringBuilder prevString = new StringBuilder();
        prevString.append(prevItemNumber);
        prevString.append(prevWHCode);
        prevString.append(prevSWHCode);
        prevString.append(prevStockStatusCode);

        if (checkString.toString().equals(prevString.toString())) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0190E, null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check ClassTag
     * @param rs
     * @param prevItemNumber
     * @param prevWHCode
     * @param prevAbcClassTag
     * @return
     * @throws SQLException
     */
    private boolean checkClassTag(ResultSet rs, String prevItemNumber, String prevWHCode, String prevAbcClassTag) throws SQLException {
        if (new StringBuilder(prevItemNumber).append(prevWHCode).toString().equals(new StringBuilder(rs.getString(COL_MDSE_CD)).append(rs.getString(COL_RTL_WH_CD)).toString())) {
            if (!prevAbcClassTag.equals(rs.getString(COL_ABC_ANLS_CLS_TAG_CD))) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(COL_UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, MSG_NLCM0191E, null);
                return false;
            }
        }
        return true;
    }

    /**
     * Add ResultDetail
     * @param rs
     * @param reqPk
     * @throws SQLException
     */
    private void addResultDetail(ResultSet rs, BigDecimal reqPk) throws SQLException {
        // create latest result detail
        ABC_ANLS_RSLT_DTLTMsg tMsg = new ABC_ANLS_RSLT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRsltDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RSLT_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAsgPk, abcAssignmentPk);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstPk, reqPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString(COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rs.getString(COL_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, rs.getString(COL_STK_STS_CD));
        if (ZYPCommonFunc.hasValue(rs.getString(COL_CUR_INVTY_QTY))) {
            ZYPEZDItemValueSetter.setValue(tMsg.curInvtyQty, rs.getBigDecimal(COL_CUR_INVTY_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.curInvtyQty, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_CUR_INVTY_COST_AMT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.curInvtyCostAmt, rs.getBigDecimal(COL_CUR_INVTY_COST_AMT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.curInvtyCostAmt, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_HIST_INVTY_TRX_QTY))) {
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxQty, rs.getBigDecimal(COL_HIST_INVTY_TRX_QTY));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxQty, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_HIST_INVTY_TRX_COST_AMT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxCostAmt, rs.getBigDecimal(COL_HIST_INVTY_TRX_COST_AMT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxCostAmt, BigDecimal.ZERO);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_HIST_INVTY_TRX_REC_CNT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxRecCnt, rs.getBigDecimal(COL_HIST_INVTY_TRX_REC_CNT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxRecCnt, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsTagCd, rs.getString(COL_ABC_ANLS_CLS_TAG_CD));
        // add to list for insert later
        detailMsgList.add(tMsg);
    }

    /**
     * Add Request
     * @param rs
     * @return latestReqPk
     */
    private BigDecimal addRequest(ResultSet rs) {
        if (reqMsgMap.get(latestReqPk) == null) {
            // create latest request.
            ABC_ANLS_RQSTTMsg tMsg = new ABC_ANLS_RQSTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            latestReqPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RQST_SQ);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstPk, latestReqPk);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAsgPk, abcAssignmentPk);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstSrcTpCd, IMPLEMENT);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsLastProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstStsCd, COMPLETE_CD);
            ZYPEZDItemValueSetter.setValue(tMsg.rqstLtstFlg, ZYPConstant.FLG_ON_Y);
            // add to list for insert later
            reqMsgMap.put(latestReqPk, tMsg);
        }
        return latestReqPk;
    }

    /**
     * Add Result
     * @param rs
     * @param reqPk
     * @param prevMdseCd
     * @param prevWHCode
     * @param reqPk
     * @throws SQLException
     */
    private void addResult(ResultSet rs, BigDecimal reqPk, String prevWHCode, String prevMdseCd) throws SQLException {
        if (!new StringBuilder(prevWHCode).append(prevMdseCd).toString().equals(new StringBuilder(rs.getString(COL_RTL_WH_CD)).append(rs.getString(COL_MDSE_CD)).toString())) {
            // create latest result
            ABC_ANLS_RSLTTMsg tMsg = new ABC_ANLS_RSLTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRsltPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RSLT_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsProcTs, ZYPDateUtil.getBatProcDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(tMsg.abcAsgPk, abcAssignmentPk);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstPk, reqPk);
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString(COL_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsValAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsCumValAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsCumValPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsNum, abcClassNum);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsTagCd, rs.getString(COL_ABC_ANLS_CLS_TAG_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsLtstFlg, ZYPConstant.FLG_ON_Y);
            // add to list for insert later
            rsltMsgList.add(tMsg);
        }
    }

    /**
     * update current request to old and create new request
     */
    private void updateRequest() {
        // get PK for update
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_ABC_ASG_PK, abcAssignmentPk);
        BigDecimal abcRequestPk = (BigDecimal) ssmBatchClient.queryObject("getRequestKey", queryParam);
        // update current request to old
        if (abcRequestPk != null) {
            ABC_ANLS_RQSTTMsg currentMsg = new ABC_ANLS_RQSTTMsg();
            ZYPEZDItemValueSetter.setValue(currentMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(currentMsg.abcAnlsRqstPk, abcRequestPk);
            currentMsg = (ABC_ANLS_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(currentMsg);
            ZYPEZDItemValueSetter.setValue(currentMsg.rqstLtstFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(currentMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(currentMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLCM0206E, new String[] {TBL_ABC_ANLS_RQST });
            }
        }
        // create new request
        for (ABC_ANLS_RQSTTMsg tMsg : reqMsgMap.values()) {
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLCM0206E, new String[] {TBL_ABC_ANLS_RQST });
            }
        }
    }

    /**
     * insert result detail
     */
    private void insertResultDetail() {
        for (ABC_ANLS_RSLT_DTLTMsg tMsg : detailMsgList) {
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLCM0100E, new String[] {TBL_ABC_ANLS_RSLT_DTL });
            }
        }
    }

    /**
     * update current result to old and create new result
     */
    private void updateResult() {
        for (ABC_ANLS_RSLTTMsg tMsg : rsltMsgList) {
            // get PK for update
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(COL_RTL_WH_CD, tMsg.rtlWhCd);
            queryParam.put(COL_MDSE_CD, tMsg.mdseCd);
            BigDecimal abcResultPk = (BigDecimal) ssmBatchClient.queryObject("getResultKey", queryParam);
            if (abcResultPk != null) {
                // update current result to old
                ABC_ANLS_RSLTTMsg currentMsg = new ABC_ANLS_RSLTTMsg();
                ZYPEZDItemValueSetter.setValue(currentMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(currentMsg.abcAnlsRsltPk, abcResultPk);
                currentMsg = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(currentMsg);
                ZYPEZDItemValueSetter.setValue(currentMsg.abcAnlsLtstFlg, ZYPConstant.FLG_OFF_N);
                EZDTBLAccessor.update(currentMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(currentMsg.getReturnCode())) {
                    throw new S21AbendException(MSG_ID_NLCM0206E, new String[] {TBL_ABC_ANLS_RSLT });
                }
            }
            // insert new result
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLCM0100E, new String[] {TBL_ABC_ANLS_RSLT });
            }
        }
    }

}
