package com.canon.cusa.s21.batch.NMA.NMAB702001;

import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.CSV_DEF_PRC_LIST_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.CSV_DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.CSV_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.CSV_PRC_CATG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.CSV_REQ_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.DEF_PRC_LIST_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.NMAM8454E;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.NMAM8455E;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.NMAM8456E;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.NMAM8457E;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.NMAM8458E;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.PRC_CATG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.REQ_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.ZZBM0009I;
import static com.canon.cusa.s21.batch.NMA.NMAB702001.constant.NMAB702001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CATGTMsgArray;
import business.db.PRC_CUST_AUDCTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
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
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Mass Upload Customer Regstration
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         O.Okuma         Create          N/A
 * 2018/05/09   Fujitsu         H.Tomimatsu     Update          QC#20269
 *</pre>
 */
public class NMAB702001 extends S21BatchMain {

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** S21RequestBatchHelper */
    private S21RequestBatchHelper reqbatch = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    // QC#20269 2018/05/09 Del Start
//    /** Insert List PRC_CUST_AUDCTMsgTMsg */
//    private List<PRC_CUST_AUDCTMsg> tMsgList = null;
    // QC#20269 2018/05/09 Del Start

    /** Code Map (PRC_CATG_CD) */
    private Map<String, String> prcCatgCdMap = null;

    @Override
    protected void initRoutine() {

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.reqbatch = new S21RequestBatchHelper();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.prcCatgCdMap = new HashMap<String, String>();
    }

    @Override
    protected void mainRoutine() {
        if (!this.reqbatch.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg reqmsg : this.reqbatch.getRequestList()) {
            this.doProcess(reqmsg);
        }
    }

    @Override
    protected void termRoutine() {
        int requestCnt = this.reqbatch.getRecordCount();
        S21InfoLogOutput.printlnv(ZZBM0009I, this.reqbatch.getFilePath(), "read", requestCnt);
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NMAB702001().executeBatch(NMAB702001.class.getSimpleName());
    }

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg reqMsg) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String glblCmpyCd = reqMsg.EZInCompanyCode.getValue();
            String upldCsvRqstPk = reqMsg.EZREQCondition.getValue();
            String upldCsvId = reqMsg.EZREQUserKey.getValue();

            this.messenger = new ZYPCSVUploadMessenger(upldCsvId, new BigDecimal(upldCsvRqstPk));

            BigDecimal wrkCount = getCountWrkData(glblCmpyCd, upldCsvRqstPk);
            if (wrkCount.intValue() == 0) {
                this.messenger.addMessageForFile(NMAM8456E, null);
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", glblCmpyCd);
            stesParam.put("upldCsvRqstPk", new BigDecimal(upldCsvRqstPk));
            ps = this.ssmLLClient.createPreparedStatement("getWrkData", stesParam, execParam());
            rs = ps.executeQuery();

            // QC#20269 2018/05/09 Mod Start
//            this.tMsgList = new ArrayList<PRC_CUST_AUDCTMsg>(wrkCount.intValue());
//
//            int recordCount = 0;
//            while (rs.next()) {
//                recordCount++;
//
//                if (!checkMandatory(rs)) {
//                    this.tMsgList = null;
//                    continue;
//                }
//
//                if (!checkFormat(rs)) {
//                    this.tMsgList = null;
//                    continue;
//                }
//
//                if (!checkDate(rs)) {
//                    this.tMsgList = null;
//                    continue;
//                }
//
//                if (!checkMasterForPrcCatgNm(glblCmpyCd, rs)) {
//                    this.tMsgList = null;
//                    continue;
//                }
//
//                if (!checkMasterForDsAcctCustNum(glblCmpyCd, rs)) {
//                    this.tMsgList = null;
//                    continue;
//                }
//
//                if (this.tMsgList != null) {
//                    createTMsg(glblCmpyCd, rs);
//                }
//            }
//            if (this.tMsgList == null) {
//                this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.uploadMessage();
//                this.errorCount += recordCount;
//                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
//                this.termCd = TERM_CD.WARNING_END;
//            } else {
//                int insertCount = 0;
//                for (int i = 0; i < this.tMsgList.size(); i++) {
//                    PRC_CUST_AUDCTMsg tMsg = this.tMsgList.get(i);
//                    ZYPEZDItemValueSetter.setValue(tMsg.prcCustAudcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CUST_AUDC_SQ));
//                    EZDTBLAccessor.create(this.tMsgList.get(i));
//                    insertCount++;
//                }
//                if (insertCount > 0) {
//                    commit();
//                    insertCount = 0;
//                }
//                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
//                this.normalCount += recordCount;
//            }

            int recordCount = 0;
            int insertCount = 0;
            while (rs.next()) {
                recordCount++;

                if (!checkMandatory(rs)) {
                    continue;
                }

                if (!checkFormat(rs)) {
                    continue;
                }

                if (!checkDate(rs)) {
                    continue;
                }

                if (!checkMasterForPrcCatgNm(glblCmpyCd, rs)) {
                    continue;
                }

                if (!checkMasterForDsAcctCustNum(glblCmpyCd, rs)) {
                    continue;
                }

                PRC_CUST_AUDCTMsg tMsg = createTMsg(glblCmpyCd, rs);
                ZYPEZDItemValueSetter.setValue(tMsg.prcCustAudcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CUST_AUDC_SQ));
                EZDTBLAccessor.create(tMsg);
                commit();
                insertCount++;
            }
            
            if (recordCount == insertCount) {
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
            
            } else {
                this.messenger.addMessageForFile(NMAM8457E, null);
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
            }

            this.normalCount += insertCount;
            this.errorCount += (recordCount - insertCount);
            // QC#20269 2018/05/09 Mod End
            

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * uploadMesageForRecord
     * @param upldCsvRqstRowNum BigDecimal
     * @param msgId String
     * @param msgStr String
     */
    private void uploadMesageForRecord(BigDecimal upldCsvRqstRowNum, String msgId, String msgStr) {
        this.messenger.addMessageForRecord(upldCsvRqstRowNum, msgId, msgStr);
    }

    /**
     * getCountWrkData
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @return BigDecimal
     */
    private BigDecimal getCountWrkData(String glblCmpyCd, String upldCsvRqstPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("upldCsvRqstPk", new BigDecimal(upldCsvRqstPk));
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getCountWrkData", queryParam);
        return result;
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
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatory(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkMandatoryColumn(rs, PRC_CATG_NM, CSV_PRC_CATG_NM)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, DS_ACCT_NUM, CSV_DS_ACCT_NUM)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, EFF_FROM_DT, CSV_EFF_FROM_DT)) {
            checkResult = false;
        }
        return checkResult;
    }

    /**
     * checkFormat
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkFormat(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkFormatFlag(rs, REQ_FLG, CSV_REQ_FLG)) {
            checkResult = false;
        }
        if (!checkFormatFlag(rs, DEF_PRC_LIST_FLG, CSV_DEF_PRC_LIST_FLG)) {
            checkResult = false;
        }
        return checkResult;
    }

    /**
     * checkMasterForPrcCatg
     * @param glblCmpyCd String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMasterForPrcCatgNm(String glblCmpyCd, ResultSet rs) throws SQLException {
        String prcCatgNm = rs.getString(PRC_CATG_NM);
        PRC_CATGTMsg inMsg = new PRC_CATGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("prcCatgNm01", prcCatgNm);

        PRC_CATGTMsgArray tMsgAry = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            BigDecimal upldCsvRqstRowNum = (BigDecimal) rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, NMAM8454E, CSV_PRC_CATG_NM);
            return false;
        }
        prcCatgCdMap.put(prcCatgNm, tMsgAry.no(0).prcCatgCd.getValue());
        return true;
    }

    /**
     * checkMasterForDsAcctCustNum
     * @param glblCmpyCd String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMasterForDsAcctCustNum(String glblCmpyCd, ResultSet rs) throws SQLException {
        String dsAcctNum = rs.getString(DS_ACCT_NUM);

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("504");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", dsAcctNum);

        SELL_TO_CUSTTMsgArray tMsgAry = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            BigDecimal upldCsvRqstRowNum = (BigDecimal) rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, NMAM8454E, CSV_DS_ACCT_NUM);
            return false;
        }
        return true;
    }

    /**
     * checkDate
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkDate(ResultSet rs) throws SQLException {
        String effFromDt = rs.getString(EFF_FROM_DT);
        String effThruDt = rs.getString(EFF_THRU_DT);

        if (effThruDt != null && effThruDt.length() != 0) {
            if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, NMAM8458E, null);
                return false;
            }
        }
        return true;
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
        if (targetColumn == null || targetColumn.length() == 0) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, ZZM9000E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkFormatFlag
     * @param rs ResultSet
     * @param targetColumnName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkFormatFlag(ResultSet rs, String targetColumnName, String csvColumnName) throws SQLException {
        String targetColumn = rs.getString(targetColumnName);
        if (!ZYPConstant.FLG_ON_Y.equals(targetColumn) && !ZYPConstant.FLG_OFF_N.equals(targetColumn)) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, NMAM8455E, csvColumnName);
            return false;
        }
        return true;
    }

 // QC#20269 2018/05/09 Mod End
//    /**
//     * createTMsg
//     * @param glblCmpyCd String
//     * @param rs ResultSet
//     * @throws SQLException SQLException
//     */
//    private void createTMsg(String glblCmpyCd, ResultSet rs) throws SQLException {
//        PRC_CUST_AUDCTMsg tMsg = new PRC_CUST_AUDCTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCdMap.get(rs.getString(PRC_CATG_NM)));
//        ZYPEZDItemValueSetter.setValue(tMsg.pubFlg_01, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_01, rs.getString(DS_ACCT_NUM));
//        ZYPEZDItemValueSetter.setValue(tMsg.reqFlg, rs.getString(REQ_FLG));
//        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, rs.getString(EFF_FROM_DT));
//        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, rs.getString(EFF_THRU_DT));
//        ZYPEZDItemValueSetter.setValue(tMsg.defPrcListFlg, rs.getString(DEF_PRC_LIST_FLG));
//        tMsgList.add(tMsg);
//    }
    
    /**
     * createTMsg
     * @param glblCmpyCd String
     * @param rs ResultSet
     * @return PRC_CUST_AUDCTMsg
     * @throws SQLException SQLException
     */
    private PRC_CUST_AUDCTMsg createTMsg(String glblCmpyCd, ResultSet rs) throws SQLException {
        PRC_CUST_AUDCTMsg tMsg = new PRC_CUST_AUDCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCdMap.get(rs.getString(PRC_CATG_NM)));
        ZYPEZDItemValueSetter.setValue(tMsg.pubFlg_01, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum_01, rs.getString(DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.reqFlg, rs.getString(REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, rs.getString(EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, rs.getString(EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.defPrcListFlg, rs.getString(DEF_PRC_LIST_FLG));
        return tMsg;
    }
 // QC#20269 2018/05/09 Mod End
}
