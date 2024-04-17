/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB253001;

import static com.canon.cusa.s21.batch.NWA.NWAB253001.constant.NWAB253001Constant.*;

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
import business.db.DEF_WH_MAP_TMPLTMsg;
import business.db.ORD_RMA_DEF_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Mass Upload RMA Default WH Mapping Template
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/12   Fujitsu         T.Ogura         Create          QC#19805
 *</pre>
 */
public class NWAB253001 extends S21BatchMain {

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

    /** Code Map (MDSE_ITEM_CLS_TP) */
    private Map<String, Map<String, Object>> mdseItemClsTpMap = null;

    /** Code Map (THIRD_PTY_DSP_TP) */
    private Map<String, Map<String, Object>> thirdPtyDspTpMap = null;

    /** Code Map (DROP_RTRN_VND) */
    private Map<String, Map<String, Object>> dropRtrnVndMap = null;

    /** Code Map (RTL_WH) */
    private Map<String, Map<String, Object>> rtlWhMap = null;

    /** Duplicated File Map */
    private Map<String, List<Map<String, Object>>> duplicatedFileMap = null;

    /** Duplicated Record Map */
    private Map<String, List<Map<String, Object>>> duplicatedRecordMap = null;

    /** Insert List ORD_RMA_DEF_WHTMsg */
    private List<ORD_RMA_DEF_WHTMsg> tMsgList = null;

    /**
     * initRoutine
     */
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
        this.mdseItemClsTpMap = new HashMap<String, Map<String, Object>>();
        this.thirdPtyDspTpMap = new HashMap<String, Map<String, Object>>();
        this.dropRtrnVndMap = new HashMap<String, Map<String, Object>>();
        this.rtlWhMap = new HashMap<String, Map<String, Object>>();
    }

    /**
     * mainRoutine
     */
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

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg reqMsg) {

        this.duplicatedFileMap = new HashMap<String, List<Map<String, Object>>>();
        this.duplicatedRecordMap = new HashMap<String, List<Map<String, Object>>>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String glblCmpyCd = reqMsg.EZInCompanyCode.getValue();
            String upldCsvRqstPk = reqMsg.EZREQCondition.getValue();
            String upldCsvId = reqMsg.EZREQUserKey.getValue();

            this.messenger = new ZYPCSVUploadMessenger(upldCsvId, new BigDecimal(upldCsvRqstPk));

            BigDecimal wrkCount = getCountWrkData(glblCmpyCd, upldCsvRqstPk);
            if (wrkCount.intValue() == 0) {
                // NWAM0803E=0,Upload CSV Request PK was not found at Upload Work Table.
                this.messenger.addMessageForFile(NWAM0803E, null);
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

            this.tMsgList = new ArrayList<ORD_RMA_DEF_WHTMsg>(wrkCount.intValue());

            int recordCount = 0;
            while (rs.next()) {
                recordCount++;

                if (!checkMandatory(rs)) {
                    this.tMsgList = null;
                    continue;
                }

                if (!checkFormat(rs)) {
                    this.tMsgList = null;
                    continue;
                }

                if (!checkMaster1(glblCmpyCd, rs)) {
                    this.tMsgList = null;
                    continue;
                }

                if (!checkMaster2(glblCmpyCd, upldCsvId, upldCsvRqstPk, rs)) {
                    this.tMsgList = null;
                    continue;
                }
            }
            if (this.tMsgList == null) {
                // NYXM0002E=0,@
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, recordCount));
                this.messenger.uploadMessage();
                this.errorCount += recordCount;
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
            } else {
                if (UPLOAD_CSV_ID_REPLACE.equals(upldCsvId)) {
                    ORD_RMA_DEF_WHTMsg removeTMsg = new ORD_RMA_DEF_WHTMsg();
                    ZYPEZDItemValueSetter.setValue(removeTMsg.glblCmpyCd, glblCmpyCd);
                    S21FastTBLAccessor.logicalRemoveByPartialValue(removeTMsg, new String[] {"glblCmpyCd" });
                }
                int insertCount = 0;
                for (int i = 0; i < this.tMsgList.size(); i++) {
                    if (UPLOAD_CSV_ID_REPLACE.equals(upldCsvId)) {
                        ORD_RMA_DEF_WHTMsg removeTMsg = new ORD_RMA_DEF_WHTMsg();
                        ZYPEZDItemValueSetter.setValue(removeTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(removeTMsg.defWhMapTmplCd, this.tMsgList.get(i).defWhMapTmplCd);
                        ZYPEZDItemValueSetter.setValue(removeTMsg.mdseItemClsTpCd, this.tMsgList.get(i).mdseItemClsTpCd);
                        ZYPEZDItemValueSetter.setValue(removeTMsg.thirdPtyItemFlg, this.tMsgList.get(i).thirdPtyItemFlg);
                        ZYPEZDItemValueSetter.setValue(removeTMsg.fromPostCd, this.tMsgList.get(i).fromPostCd);
                        ZYPEZDItemValueSetter.setValue(removeTMsg.toPostCd, this.tMsgList.get(i).toPostCd);
                        if (ZYPCommonFunc.hasValue(this.tMsgList.get(i).thirdPtyDspTpCd)) {
                            ZYPEZDItemValueSetter.setValue(removeTMsg.thirdPtyDspTpCd, this.tMsgList.get(i).thirdPtyDspTpCd);
                        }
                        if (ZYPCommonFunc.hasValue(this.tMsgList.get(i).dropRtrnVndCd)) {
                            ZYPEZDItemValueSetter.setValue(removeTMsg.dropRtrnVndCd, this.tMsgList.get(i).dropRtrnVndCd);
                        }
                        S21FastTBLAccessor.removeByPartialValue(removeTMsg
                                , new String[]{"glblCmpyCd", "defWhMapTmplCd", "mdseItemClsTpCd", "thirdPtyItemFlg", "fromPostCd", "toPostCd", "thirdPtyDspTpCd", "dropRtrnVndCd"});
                    }
                    EZDTBLAccessor.insert(this.tMsgList.get(i));
                    insertCount++;
                    if (insertCount == this.commitNumber) {
                        commit();
                        insertCount = 0;
                    }
                }
                if (insertCount > 0) {
                    commit();
                    insertCount = 0;
                }
                // NYXM0001I=0,@
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(recordCount, 0, 0));
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
                this.normalCount += recordCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * termRoutine
     */
    @Override
    protected void termRoutine() {
        int requestCnt = this.reqbatch.getRecordCount();
        // ZZBM0009I=0,The  (@)  was (@) . ResultCount = (@)
        S21InfoLogOutput.printlnv(ZZBM0009I, this.reqbatch.getFilePath(), "read", requestCnt);
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB253001().executeBatch(NWAB253001.class.getSimpleName());
    }

    /**
     * checkMandatory
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatory(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkMandatoryColumn(rs, DEF_WH_MAP_TMPL_CD, CSV_DEF_WH_MAP_TMPL_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, MDSE_ITEM_CLS_TP_DESC_TXT, CSV_MDSE_ITEM_CLS_TP_DESC_TXT)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, FROM_POST_CD, CSV_FROM_POST_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, TO_POST_CD, CSV_TO_POST_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryCond(rs, THIRD_PTY_DSP_TP_NM, DROP_RTRN_VND_NM, CSV_THIRD_PTY_DSP_TP_NM)) {
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

        if (!checkFormatFlag(rs, THIRD_PTY_ITEM_FLG, CSV_THIRD_PTY_ITEM_FLG)) {
            checkResult = false;
        }
        if (!checkFormatFlag(rs, ON_HND_CHK_FLG, CSV_ON_HND_CHK_FLG)) {
            checkResult = false;
        }

        return checkResult;
    }

    /**
     * checkMaster1
     * @param glblCmpyCd String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMaster1(String glblCmpyCd, ResultSet rs) throws SQLException {
        String defWhMapTmplCd = rs.getString(DEF_WH_MAP_TMPL_CD);
        DEF_WH_MAP_TMPLTMsg tMsg = findByKeyDefWhMapTmpl(glblCmpyCd, defWhMapTmplCd);
        if (tMsg == null) {
            BigDecimal upldCsvRqstRowNum = (BigDecimal) rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            // NWAM0805E=0,[@] is not exists in master.
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_DEF_WH_MAP_TMPL_CD);
            return false;
        }
        return true;
    }

    /**
     * checkMaster2
     * @param glblCmpyCd String
     * @param upldCsvId String
     * @param upldCsvRqstPk String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMaster2(String glblCmpyCd, String upldCsvId, String upldCsvRqstPk, ResultSet rs) throws SQLException {
        boolean checkResult = true;

        BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);

        // Get Code MDSE_ITEM_CLS_TP_DESC_TXT
        String mdseItemClsTpDescTxt = rs.getString(MDSE_ITEM_CLS_TP_DESC_TXT);
        String mdseItemClsTpCd = null;
        if (!"*".equals(mdseItemClsTpDescTxt)) {
            Map<String, Object> mdseItemClsTpDescTxtInfo = getMdseItemClsTpInfo(glblCmpyCd, mdseItemClsTpDescTxt);
            if (mdseItemClsTpDescTxtInfo == null) {
                // NWAM0805E=0,[@] is not exists in master.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_MDSE_ITEM_CLS_TP_DESC_TXT);
                checkResult = false;
            } else {
                mdseItemClsTpCd = (String) mdseItemClsTpDescTxtInfo.get(MDSE_ITEM_CLS_TP_CD);
            }
        } else {
            mdseItemClsTpCd = "*";
        }

        // Get Code THIRD_PTY_DSP_TP_NM
        String thirdPtyDspTpNm = rs.getString(THIRD_PTY_DSP_TP_NM);
        String thirdPtyDspTpCd = null;
        if (thirdPtyDspTpNm != null && thirdPtyDspTpNm.length() > 0) {
            Map<String, Object> thirdPtyDspTpNmInfo = getThirdPtyDspTpInfo(glblCmpyCd, thirdPtyDspTpNm);
            if (thirdPtyDspTpNmInfo == null) {
                // NWAM0805E=0,[@] is not exists in master.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_THIRD_PTY_DSP_TP_NM);
                checkResult = false;
                thirdPtyDspTpCd = NWAM0805E;
            } else {
                thirdPtyDspTpCd = (String) thirdPtyDspTpNmInfo.get(THIRD_PTY_DSP_TP_CD);
            }
        }

        // Get Code DROP_RTRN_VND_NM
        String dropRtrnVndNm = rs.getString(DROP_RTRN_VND_NM);
        String dropRtrnVndCd = null;
        if (dropRtrnVndNm != null && dropRtrnVndNm.length() > 0) {
            Map<String, Object> dropRtrnVndNmInfo = getDropRtrnVndInfo(glblCmpyCd, dropRtrnVndNm);
            if (dropRtrnVndNmInfo == null) {
                // NWAM0805E=0,[@] is not exists in master.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_DROP_RTRN_VND_NM);
                checkResult = false;
                dropRtrnVndCd = NWAM0805E;
            } else {
                dropRtrnVndCd = (String) dropRtrnVndNmInfo.get(DROP_RTRN_VND_CD);
            }
        }

        // Check Master : RMA_DEF_WH_CD
        String rmaDefWhCd = rs.getString(RMA_DEF_WH_CD);
        if (rmaDefWhCd != null && rmaDefWhCd.length() > 0) {
            Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd, rmaDefWhCd);
            if (rtlWhInfo == null) {
                // NWAM0805E=0,[@] is not exists in master.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_RMA_DEF_WH_CD);
                checkResult = false;
            }
        }

        String defWhMapTmplCd = rs.getString(DEF_WH_MAP_TMPL_CD);
        String thirdPtyItemFlg = rs.getString(THIRD_PTY_ITEM_FLG);
        String fromPostCd = rs.getString(FROM_POST_CD);
        String toPostCd = rs.getString(TO_POST_CD);

        // Check Digits
        if (fromPostCd != null && fromPostCd.length() > 0 && fromPostCd.length() != 5) {
            // NWAM0841E=0,[@] should be 5 Digits.
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0841E, CSV_FROM_POST_CD);
            checkResult = false;
        }
        
        if (toPostCd != null && toPostCd.length() > 0 && toPostCd.length() != 5) {
            // NWAM0841E=0,[@] should be 5 Digits.
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0841E, CSV_TO_POST_CD);
            checkResult = false;
        }
        
        // Check Duplicated
        if (UPLOAD_CSV_ID_REPLACE.equals(upldCsvId)) {
            if (!checkDuplicatedFile(glblCmpyCd, upldCsvRqstPk, upldCsvRqstRowNum, defWhMapTmplCd, mdseItemClsTpDescTxt, thirdPtyItemFlg, thirdPtyDspTpNm, dropRtrnVndNm)) {
                // NWAM0806E=0,The data is duplicated. Please confirm them.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
                checkResult = false;
            }
        } else if (UPLOAD_CSV_ID_APPEND.equals(upldCsvId)) {
            if (!checkDuplicatedFile(glblCmpyCd, upldCsvRqstPk, upldCsvRqstRowNum, defWhMapTmplCd, mdseItemClsTpDescTxt, thirdPtyItemFlg, thirdPtyDspTpNm, dropRtrnVndNm)) {
                // NWAM0806E=0,The data is duplicated. Please confirm them.
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
                checkResult = false;
            } else if (mdseItemClsTpCd != null && !NWAM0805E.equals(thirdPtyDspTpCd) && !NWAM0805E.equals(dropRtrnVndCd)) {
                if (!checkDuplicatedRecord(glblCmpyCd, upldCsvRqstPk, upldCsvRqstRowNum, defWhMapTmplCd, mdseItemClsTpCd, thirdPtyItemFlg, fromPostCd, toPostCd, thirdPtyDspTpCd, dropRtrnVndCd)) {
                    // NWAM0806E=0,The data is duplicated. Please confirm them.
                    uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
                    checkResult = false;
                }
            }
        }

        // Create Insert TMsg
        if (checkResult && this.tMsgList != null) {
            ORD_RMA_DEF_WHTMsg tMsg = new ORD_RMA_DEF_WHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ordRmaDefWhPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_RMA_DEF_WH_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.defWhMapTmplCd, rs.getString(DEF_WH_MAP_TMPL_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpCd, mdseItemClsTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyItemFlg, rs.getString(THIRD_PTY_ITEM_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.fromPostCd, formatPostCdForInsert(rs.getString(FROM_POST_CD)));
            ZYPEZDItemValueSetter.setValue(tMsg.toPostCd, formatPostCdForInsert(rs.getString(TO_POST_CD)));
            ZYPEZDItemValueSetter.setValue(tMsg.onHndChkFlg, rs.getString(ON_HND_CHK_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyDspTpCd, thirdPtyDspTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dropRtrnVndCd, dropRtrnVndCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmaDefWhCd, rmaDefWhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_ON_Y);
            tMsgList.add(tMsg);
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
        if (targetColumn == null || targetColumn.length() == 0) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            //ZZM9000E=0,[@] field is mandatory.
            uploadMesageForRecord(upldCsvRqstRowNum, ZZM9000E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkMandatoryCond
     * @param rs ResultSet
     * @param dispositionName String
     * @param supplierName String
     * @param csvColumnName1 String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatoryCond(ResultSet rs, String dispositionName, String supplierName, String csvColumnName) throws SQLException {
        String disNm = rs.getString(dispositionName);
        String supNm = rs.getString(supplierName);
        if (ZYPCommonFunc.hasValue(disNm) && ZYPCommonFunc.hasValue(supNm)) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            // NWAM0940E=0,Only one of RMA Disposition and Supplier can be set.
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0940E, csvColumnName);
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
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0807E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkDuplicatedFile
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param upldCsvRqstRowNum BigDecimal
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpDescTxt String
     * @param thirdPtyItemFlg String
     * @param thirdPtyDspTpNm String
     * @param dropRtrnVndNm String
     * @return boolean
     */
    private boolean checkDuplicatedFile(String glblCmpyCd, String upldCsvRqstPk, BigDecimal upldCsvRqstRowNum, String defWhMapTmplCd, String mdseItemClsTpDescTxt, String thirdPtyItemFlg, String thirdPtyDspTpNm, String dropRtrnVndNm) {

        String key = defWhMapTmplCd + " - " + mdseItemClsTpDescTxt + " - " + thirdPtyItemFlg + " - " + thirdPtyDspTpNm + " - " + dropRtrnVndNm;
        List<Map<String, Object>> duplicateFileList = null;
        if (this.duplicatedFileMap.containsKey(key)) {
            duplicateFileList = this.duplicatedFileMap.get(key);
        } else {
            duplicateFileList = getDuplicatedFile(glblCmpyCd, upldCsvRqstPk, defWhMapTmplCd, mdseItemClsTpDescTxt, thirdPtyItemFlg, thirdPtyDspTpNm, dropRtrnVndNm);
            // Format Post Code
            for (int num1 = 0; num1 < duplicateFileList.size(); num1++) {
                Map<String, Object> map = duplicateFileList.get(num1);
                map.put(FROM_POST_CD, formatPostCdForCheck((String) map.get(FROM_POST_CD)));
                map.put(TO_POST_CD, formatPostCdForCheck((String) map.get(TO_POST_CD)));
                duplicateFileList.set(num1, map);
            }
            // All Data Check
            for (int num1 = 0; num1 < duplicateFileList.size(); num1++) {
                Map<String, Object> map1 = duplicateFileList.get(num1);
                String from1 = (String) map1.get(FROM_POST_CD);
                String to1 = (String) map1.get(TO_POST_CD);
                String checkResultKey = (String) map1.get(CHECK_RESULT_KEY);
                boolean checkResult = true;
                for (int num2 = num1 + 1; num2 < duplicateFileList.size(); num2++) {
                    Map<String, Object> map2 = duplicateFileList.get(num2);
                    String from2 = (String) map2.get(FROM_POST_CD);
                    String to2 = (String) map2.get(TO_POST_CD);
                    if (checkPostCdDuplicated(from1, to1, from2, to2) == false) {
                        map2.put(CHECK_RESULT_KEY, CHECK_RESULT_NG);
                        duplicateFileList.set(num2, map2);
                        checkResult = false;
                    }
                }
                if (checkResult == true) {
                    if (checkResultKey == null) {
                        map1.put(CHECK_RESULT_KEY, CHECK_RESULT_OK);
                    }
                } else {
                    map1.put(CHECK_RESULT_KEY, CHECK_RESULT_NG);
                }
                duplicateFileList.set(num1, map1);
            }
            duplicatedFileMap.put(key, duplicateFileList);
        }
        boolean result = true;
        for (int i = 0; i < duplicateFileList.size(); i++) {
            Map<String, Object> map = duplicateFileList.get(i);
            BigDecimal upldCsvRqstRowNum2 = (BigDecimal) map.get(UPLD_CSV_RQST_ROW_NUM);
            if (upldCsvRqstRowNum.intValue() == upldCsvRqstRowNum2.intValue()) {
                String checkResult = (String) map.get(CHECK_RESULT_KEY);
                if (CHECK_RESULT_OK.equals(checkResult)) {
                    result = true;
                    break;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * checkDuplicatedRecord
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param upldCsvRqstRowNum BigDecimal
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpCd String
     * @param thirdPtyItemFlg String
     * @param fromPostCd String
     * @param toPostCd String
     * @param thirdPtyDspTpCd String
     * @param dropRtrnVndCd String
     * @return boolean
     */
    private boolean checkDuplicatedRecord(String glblCmpyCd, String upldCsvRqstPk, BigDecimal upldCsvRqstRowNum, String defWhMapTmplCd, String mdseItemClsTpCd, String thirdPtyItemFlg, String fromPostCd, String toPostCd, String thirdPtyDspTpCd, String dropRtrnVndCd) {

        String key = defWhMapTmplCd + " - " + mdseItemClsTpCd + " - " + thirdPtyItemFlg + " - " + thirdPtyDspTpCd + " - " + dropRtrnVndCd;
        List<Map<String, Object>> duplicateFileList = null;
        if (this.duplicatedRecordMap.containsKey(key)) {
            duplicateFileList = this.duplicatedRecordMap.get(key);
        } else {
            duplicateFileList = getDuplicatedRecord(glblCmpyCd, upldCsvRqstPk, defWhMapTmplCd, mdseItemClsTpCd, thirdPtyItemFlg, thirdPtyDspTpCd, dropRtrnVndCd);
            // Format Post Code
            for (int num1 = 0; num1 < duplicateFileList.size(); num1++) {
                Map<String, Object> map = duplicateFileList.get(num1);
                map.put(FROM_POST_CD, formatPostCdForCheck((String) map.get(FROM_POST_CD)));
                map.put(TO_POST_CD, formatPostCdForCheck((String) map.get(TO_POST_CD)));
                duplicateFileList.set(num1, map);
            }
            duplicatedRecordMap.put(key, duplicateFileList);
        }
        boolean result = true;
        fromPostCd = formatPostCdForCheck(fromPostCd);
        toPostCd = formatPostCdForCheck(toPostCd);
        for (int i = 0; i < duplicateFileList.size(); i++) {
            Map<String, Object> map = duplicateFileList.get(i);
            String from2 = (String) map.get(FROM_POST_CD);
            String to2 = (String) map.get(TO_POST_CD);
            if (checkPostCdDuplicated(fromPostCd, toPostCd, from2, to2) == false) {
                result = false;
                break;
            }
        }
        return result;
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
        queryParam.put("upldCsvRqstPk", upldCsvRqstPk);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getCountWrkData", queryParam);
        return result;
    }

    /**
     * getMdseItemClsTpInfo
     * @param glblCmpyCd String
     * @param mdseItemClsTpDescTxt String
     * @return Map<String, Object>
     */
    private Map<String, Object> getMdseItemClsTpInfo(String glblCmpyCd, String mdseItemClsTpDescTxt) {
        if (this.mdseItemClsTpMap.containsKey(mdseItemClsTpDescTxt)) {
            return this.mdseItemClsTpMap.get(mdseItemClsTpDescTxt);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("mdseItemClsTpDescTxt", mdseItemClsTpDescTxt);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getMdseItemClsTpInfo", queryParam);
            if (result != null) {
                this.mdseItemClsTpMap.put(mdseItemClsTpDescTxt, result);
            }
            return result;
        }
    }

    /**
     * getRtlWhInfo
     * @param glblCmpyCd String
     * @param physWhCd String
     * @return List<Map<String, Object>>
     */
    private Map<String, Object> getRtlWhInfo(String glblCmpyCd, String physWhCd) {
        if (this.rtlWhMap.containsKey(physWhCd)) {
            return this.rtlWhMap.get(physWhCd);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("physWhCd", physWhCd);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getRtlWhInfo", queryParam);
            if (result != null) {
                this.rtlWhMap.put(physWhCd, result);
            }
            return result;
        }
    }

    /**
     * getThirdPtyDspTpInfo
     * @param glblCmpyCd String
     * @param thirdPtyDspTpNm String
     * @return Map<String, Object>
     */
    private Map<String, Object> getThirdPtyDspTpInfo(String glblCmpyCd, String thirdPtyDspTpNm) {
        if (this.thirdPtyDspTpMap.containsKey(thirdPtyDspTpNm)) {
            return this.thirdPtyDspTpMap.get(thirdPtyDspTpNm);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("thirdPtyDspTpNm", thirdPtyDspTpNm);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getThirdPtyDspTpInfo", queryParam);
            if (result != null) {
                this.thirdPtyDspTpMap.put(thirdPtyDspTpNm, result);
            }
            return result;
        }
    }

    /**
     * getDropRtrnVndInfo
     * @param glblCmpyCd String
     * @param dropRtrnVndNm String
     * @return Map<String, Object>
     */
    private Map<String, Object> getDropRtrnVndInfo(String glblCmpyCd, String dropRtrnVndNm) {
        if (this.dropRtrnVndMap.containsKey(dropRtrnVndNm)) {
            return this.dropRtrnVndMap.get(dropRtrnVndNm);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("dropRtrnVndNm", dropRtrnVndNm);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDropRtrnVndInfo", queryParam);
            if (result != null) {
                this.dropRtrnVndMap.put(dropRtrnVndNm, result);
            }
            return result;
        }
    }

    /**
     * getDuplicatedFile
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpDescTxt String
     * @param thirdPtyItemFlg String
     * @param thirdPtyDspTpNm String
     * @param dropRtrnVndNm String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getDuplicatedFile(String glblCmpyCd, String upldCsvRqstPk, String defWhMapTmplCd, String mdseItemClsTpDescTxt, String thirdPtyItemFlg, String thirdPtyDspTpNm, String dropRtrnVndNm) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("upldCsvRqstPk", new BigDecimal(upldCsvRqstPk));
        queryParam.put("defWhMapTmplCd", defWhMapTmplCd);
        queryParam.put("mdseItemClsTpDescTxt", mdseItemClsTpDescTxt);
        queryParam.put("thirdPtyItemFlg", thirdPtyItemFlg);
        if (ZYPCommonFunc.hasValue(thirdPtyDspTpNm)) {
            queryParam.put("thirdPtyDspTpNm", thirdPtyDspTpNm);
        }
        if (ZYPCommonFunc.hasValue(dropRtrnVndNm)) {
            queryParam.put("dropRtrnVndNm", dropRtrnVndNm);
        }
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplicatedFile", queryParam);
        return result;
    }

    /**
     * getDuplicatedRecord
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpCd String
     * @param thirdPtyItemFlg String
     * @param thirdPtyDspTpCd String
     * @param dropRtrnVndCd String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getDuplicatedRecord(String glblCmpyCd, String upldCsvRqstPk, String defWhMapTmplCd, String mdseItemClsTpCd, String thirdPtyItemFlg, String thirdPtyDspTpCd, String dropRtrnVndCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("defWhMapTmplCd", defWhMapTmplCd);
        queryParam.put("mdseItemClsTpCd", mdseItemClsTpCd);
        queryParam.put("thirdPtyItemFlg", thirdPtyItemFlg);
        if (ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
            queryParam.put("thirdPtyDspTpCd", thirdPtyDspTpCd);
        }
        if (ZYPCommonFunc.hasValue(dropRtrnVndCd)) {
            queryParam.put("dropRtrnVndCd", dropRtrnVndCd);
        }
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplicatedRecord", queryParam);
        return result;
    }

    /**
     * findByKeyDefWhMapTmpl
     * @param glblCmpyCd String
     * @param defWhMapTmplCd String
     * @return DEF_WH_MAP_TMPLTMsg
     */
    private static DEF_WH_MAP_TMPLTMsg findByKeyDefWhMapTmpl(String glblCmpyCd, String defWhMapTmplCd) {
        DEF_WH_MAP_TMPLTMsg inMsg = new DEF_WH_MAP_TMPLTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.defWhMapTmplCd.setValue(defWhMapTmplCd);
        return (DEF_WH_MAP_TMPLTMsg) S21CacheTBLAccessor.findByKey(inMsg);
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
     * checkPostCdDuplicated
     * @param from1 String
     * @param to1 String
     * @param from2 String
     * @param to2 String
     * @return boolean
     */
    private static boolean checkPostCdDuplicated(String from1, String to1, String from2, String to2) {
        if (!"***".equals(from1) && !"***".equals(to1) && !"***".equals(from2) && !"***".equals(to2)) {
            if (from1.compareTo(to2) <= 0 && from2.compareTo(to1) <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * formatPostCdForCheck
     * @param postCd String
     * @return String
     */
    private static String formatPostCdForCheck(String postCd) {
        if (ZYPCommonFunc.hasValue(postCd)) {
            String[] str = postCd.split("-");
            if (str.length == 2) {
                return ZYPCommonFunc.leftPad(str[0], 15, "0") + "-" + ZYPCommonFunc.rightPad(str[1], 15, "0");
            } else {
                return ZYPCommonFunc.leftPad(postCd, 15, "0") + "-" + ZYPCommonFunc.rightPad("", 15, "0");
            }
        } else {
            return "***";
        }
    }

    /**
     * formatPostCdForInsert
     * @param postCd String
     * @return String
     */
    private static String formatPostCdForInsert(String postCd) {
        String[] str = postCd.split("-");
        if (str.length == 2) {
            if ((ZYPCommonFunc.hasValue(str[0]) && ZYPCommonFunc.isNumeric(str[0])) && (ZYPCommonFunc.hasValue(str[1]) && ZYPCommonFunc.isNumeric(str[1]))) {
                return ZYPCommonFunc.leftPad(str[0], 5, "0") + "-" + str[1];
            }
        } else if (str.length == 1 && ZYPCommonFunc.isNumeric(postCd)) {
            return ZYPCommonFunc.leftPad(postCd, 5, "0");
        }
        return postCd;
    }

    private static String createResultMessageArg(int insCount, int updCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insCount> 0) {
            builder.append(String.format(RESULT_MSG_INS, insCount));
        }
        if (updCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, updCount));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }
}
