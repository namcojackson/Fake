/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB101001;

import static com.canon.cusa.s21.batch.NSB.NSBB101001.constant.NSBB101001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCommonFunc;
import business.db.ART10FMsg;
import business.db.SVC_MOD_STSTMsg;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

import parts.dbcommon.EZDTBLAccessor;

/**
 * <pre>
 * Meter Read Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/02   Hitachi         S.Naya          Create          QC#60927
 * 2023/03/31   Hitachi         S.Kitamura      Update          QC#60927
 * </pre>
 */

public class NSBB101001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** Total Normal Count */
    private int totalNormalCount = 0;

    /** Total Err Count */
    private int totalErrCount = 0;

    // START 2023/03/31 S.Kitamura [QC#60927, ADD]
    private boolean updateFlg = false;

    private boolean insertFlg = false;
    // END 2023/03/31 S.Kitamura [QC#60927, ADD]

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_GLOBAL_COMPANY_CODE });
        }
        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.batchHelper = new S21RequestBatchHelper();
    }

    @Override
    protected void mainRoutine() {

        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            doProcess(request);
            commit();
        }
    }

    @Override
    protected void termRoutine() {
        if (this.totalErrCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalNormalCount, this.totalErrCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB101001().executeBatch(NSBB101001.class.getSimpleName());
    }

    private void doProcess(ART10FMsg fMsg) {

        int normalCount = 0;
        int errCount = 0;
        
        PreparedStatement stmt = null;
        ResultSet rsSvcModUpldWrk = null;

        // Get Upload ID
        String upldCsvId = getUpldCsvId(fMsg);

        // Get Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);

        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("upldCsvRqstPk", upldCsvRqstPk);

            // Get CSV Data from SVC_MOD_UPLD_WRK
            stmt = this.ssmLLClient.createPreparedStatement("getSvcModUpldWrk", paramMap, setExecParam());
            rsSvcModUpldWrk = stmt.executeQuery();

            while (rsSvcModUpldWrk.next() ) {
                Map<String, Object> updDataMap = null;
                updDataMap = validation(rsSvcModUpldWrk);
                // START 2023/03/31 S.Kitamura [QC#60927, MOD]
                if (updDataMap == null) {
                    errCount++;
                    continue;
                }
                if (this.updateFlg) {
                    // Update SVC_MOD_STS
                    if (updateSvcModSts(rsSvcModUpldWrk, updDataMap)){
                        // do nothing
                    } else {
                        this.messenger.addMessageForRecord(rsSvcModUpldWrk.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), NSBM0196E, null);
                        errCount++;
                        continue;
                    }
                }
                if (this.insertFlg) {
                    // Insert SVC_MOD_STS
                    if (insertSvcModSts(rsSvcModUpldWrk, updDataMap)){
                        // do nothing
                    } else {
                        this.messenger.addMessageForRecord(rsSvcModUpldWrk.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), NSBM0196E, null);
                        errCount++;
                        continue;
                    }
                }
                normalCount++;
                // END 2023/03/31 S.Kitamura [QC#60927, MOD]
            }
            
            // Output Result Message
            String arg = createResultMessageArg(normalCount, errCount);

            if (errCount == 0) {
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                this.messenger.addMessageForFile(NYXM0001I, arg);
            } else {
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                this.messenger.addMessageForFile(NYXM0002E, arg);
            }
            this.messenger.uploadMessage();
            
            this.totalNormalCount += normalCount;
            this.totalErrCount += errCount;
            
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSvcModUpldWrk);
        }
    }

    private String getUpldCsvId(ART10FMsg fMsg) {
        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!hasValue(uploadCsvId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_UPLD_CSV_RQST_PK });
        }
        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {
        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (!hasValue(removeDQRequestPK)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_UPLD_CSV_RQST_PK });
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NSAM0040E, new String[] {MSG_UPLD_CSV_RQST_PK });
        }
        return new BigDecimal(removeDQRequestPK);
    }

    private S21SsmExecutionParameter setExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> validation(ResultSet rsSvcModUpldWrk) throws SQLException {

        boolean checkStatusFlg = false;
        this.updateFlg = false;
        this.insertFlg = false;

        List<Map<String, BigDecimal>> svcModStsMapList = null;
        List<Map<String, String>>     svcModOptMapList = null;

        // Row Numbers in CSV
        BigDecimal rowNum = rsSvcModUpldWrk.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        // Mandatory Check
        if (!hasValue(rsSvcModUpldWrk.getString("SVC_MOD_PLN_ID"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_SVC_MOD_PLN_ID);
            return null;
        }
        if (!hasValue(rsSvcModUpldWrk.getString("SER_NUM"))) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_SERIAL_NUM);
            return null;
        }

        // Master Check
        if (!isSvcModPk(rsSvcModUpldWrk)) {
            this.messenger.addMessageForRecord(rowNum, NSBM0191E, null);
            return null;
        }
        if (!isSvcMachMstrPk(rsSvcModUpldWrk)) {
            this.messenger.addMessageForRecord(rowNum, NSBM0192E, null);
            return null;
        }
        if (hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DESC_TXT")) || hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"))) {
            if (!hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DESC_TXT"))) {
                this.messenger.addMessageForRecord(rowNum, NSBM0194E, MSG_SVC_MOD_OPT_DESC_TXT);
                return null;
            }
            if (!hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"))) {
                this.messenger.addMessageForRecord(rowNum, NSBM0194E, MSG_SVC_MOD_OPT_DT);
                return null;
            }
            if (!ZYPDateUtil.isValidDate(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"), ZYPDateUtil.TYPE_YYYYMMDD)) {
                this.messenger.addMessageForRecord(rowNum, ZZZM9026E, MSG_SVC_MOD_OPT_DT);
                return null;
            }
            if (ZYPDateUtil.compare(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"), this.salesDate) < 0) {
                this.messenger.addMessageForRecord(rowNum, ZZZM9026E, MSG_SVC_MOD_OPT_DT);
                return null;
            }
            // Get SVC_MOD_OPT
            svcModOptMapList = (List<Map<String, String>>) getSvcModOpt(rsSvcModUpldWrk);
            if (svcModOptMapList == null || svcModOptMapList.size() == 0) {
                this.messenger.addMessageForRecord(rowNum, NSBM0193E, null);
                return null;
            }
        } else {
            checkStatusFlg = true;
        }

        // Get SVC_MOD_STS and Exist Check
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcModPlnId", rsSvcModUpldWrk.getString("SVC_MOD_PLN_ID"));
        paramMap.put("serNum", rsSvcModUpldWrk.getString("SER_NUM"));
        svcModStsMapList = (List<Map<String, BigDecimal>>) ssmBatchClient.queryObjectList("getSvcModSts", paramMap);
        if (svcModStsMapList == null || svcModStsMapList.size() == 0) {
            // START 2023/03/31 S.Kitamura [QC#60927, MOD]
            // this.messenger.addMessageForRecord(rowNum, NSBM0195E, null);
            // return null;
            this.updateFlg = false;
        } else {
            this.updateFlg = true;
        }
        if (checkStatusFlg && this.updateFlg) {
            int i;
            for (i = 0; i < svcModStsMapList.size(); i++) {
                if (SVC_MOD_PROC_STS.CLOSED.equals(svcModStsMapList.get(i).get("SVC_MOD_PROC_STS_CD"))) {
                    break;
                }
            }
            if (i == svcModStsMapList.size()) {
                this.messenger.addMessageForRecord(rowNum, NSBM0218E, null);
                return null;
            }
        } else if (checkStatusFlg && !this.updateFlg) {
            this.messenger.addMessageForRecord(rowNum, NSBM0194E, MSG_SVC_MOD_OPT_DESC_TXT + " and " + MSG_SVC_MOD_OPT_DT);
            return null;
        }
        // END 2023/03/31 S.Kitamura [QC#60927, MOD]
        // START 2023/03/31 S.Kitamura [QC#60927, ADD]
        // Case of no data in SVC_MOD_STS table and exists SVC_MOD & SVC_MOD_DTL
        List<Map<String, BigDecimal>> svcModMapList = null;
        paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("svcModPk", getSvcModPk(rsSvcModUpldWrk));
        paramMap.put("serNum", rsSvcModUpldWrk.getString("SER_NUM"));
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("mdseCdLen", "8");
        paramMap.put("svcModProcStsCd_op", SVC_MOD_PROC_STS.OPEN);
        paramMap.put("svcModProcStsCd_canc", SVC_MOD_PROC_STS.CANCELLED);
        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
        paramMap.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
        svcModMapList = (List<Map<String, BigDecimal>>) ssmBatchClient.queryObjectList("getSvcMod", paramMap);
        if (svcModMapList == null || svcModMapList.size() == 0) {
            this.insertFlg = false;
        } else {
            this.insertFlg = true;
        }
        if (!this.updateFlg && !this.insertFlg) {
            this.messenger.addMessageForRecord(rowNum, NSBM0195E, null);
            return null;
        }
        // END 2023/03/31 S.Kitamura [QC#60927, ADD]

        Map<String, Object> updDataMap = new HashMap<String, Object>();
        updDataMap.put("svcModStsMapList", svcModStsMapList);
        updDataMap.put("svcModOptMapList", svcModOptMapList);
        // START 2023/03/31 S.Kitamura [QC#60927, ADD]
        updDataMap.put("svcModMapList", svcModMapList);
        // END 2023/03/31 S.Kitamura [QC#60927, ADD]

        return updDataMap;
    }

    private boolean isSvcModPk(ResultSet rsSvcModUpldWrk) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> rs = null;
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcModPlnId", rsSvcModUpldWrk.getString("SVC_MOD_PLN_ID"));
        rs = (Map<String, Object>) this.ssmBatchClient.queryObject("isSvcModPk", paramMap);
        
        if (rs != null){
            return true;
        } else {
            return false;
        }
    }
    // START 2023/03/31 S.Kitamura [QC#60927, ADD]
    private BigDecimal getSvcModPk(ResultSet rsSvcModUpldWrk) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> rs = null;
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcModPlnId", rsSvcModUpldWrk.getString("SVC_MOD_PLN_ID"));
        rs = (Map<String, Object>) this.ssmBatchClient.queryObject("isSvcModPk", paramMap);
        
        if (rs != null){
            return (BigDecimal) rs.get("SVC_MOD_PK");
        } else {
            return null;
        }
    }
    // END 2023/03/31 S.Kitamura [QC#60927, ADD]
    private boolean isSvcMachMstrPk(ResultSet rsSvcModUpldWrk) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> rs = null;
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("serNum", rsSvcModUpldWrk.getString("SER_NUM"));
        rs = (Map<String, Object>) this.ssmBatchClient.queryObject("isSvcMachMstrPk", paramMap);
        
        if (rs != null){
            return true;
        } else {
            return false;
        }
    } 

    private List<Map<String, String>> getSvcModOpt(ResultSet rsSvcModUpldWrk) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcModOptDescTxt", rsSvcModUpldWrk.getString("SVC_MOD_OPT_DESC_TXT"));
        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getSvcModOpt", paramMap);
    }

    private boolean updateSvcModSts(ResultSet rsSvcModUpldWrk, Map<String, Object> updDataMap) throws SQLException {

        List<Map<String, BigDecimal>> svcModStsMapList = (List<Map<String, BigDecimal>>) updDataMap.get("svcModStsMapList");
        List<Map<String, String>>     svcModOptMapList = (List<Map<String, String>>) updDataMap.get("svcModOptMapList");
        
        for (int i = 0; i < svcModStsMapList.size(); i++) {
            SVC_MOD_STSTMsg updSvcModStsTMsg = new SVC_MOD_STSTMsg();
            ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModStsPk, svcModStsMapList.get(i).get("SVC_MOD_STS_PK"));
            updSvcModStsTMsg = (SVC_MOD_STSTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updSvcModStsTMsg);
            
            if (updSvcModStsTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(updSvcModStsTMsg.getReturnCode())){
                return false;
            }
            
            if (!hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DESC_TXT")) && !hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"))) {
                if (SVC_MOD_PROC_STS.CLOSED.equals(updSvcModStsTMsg.svcModProcStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcTaskNum, "");
                    ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModProcStsCd, SVC_MOD_PROC_STS.OPEN);
                    ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModOptCd, "");
                    ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModOptDt, "");
                } else {
                    // Skip Update
                    continue;
                }
            } else {
                if (hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"))){
                    ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModOptDt, rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"));
                }
                if (svcModOptMapList != null && svcModOptMapList.size() > 0) {
                    ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModOptCd, svcModOptMapList.get(0).get("SVC_MOD_OPT_CD"));
                }
            }
            ZYPEZDItemValueSetter.setValue(updSvcModStsTMsg.svcModNoteTxt, rsSvcModUpldWrk.getString("SVC_MOD_NOTE_TXT"));

            EZDTBLAccessor.update(updSvcModStsTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updSvcModStsTMsg.getReturnCode())) {
                return false;
            }
        }

        return true;
    }
    // START 2023/03/31 S.Kitamura [QC#60927, ADD]
    private boolean insertSvcModSts(ResultSet rsSvcModUpldWrk, Map<String, Object> updDataMap) throws SQLException {
        List<Map<String, Object>> svcModMapList = (List<Map<String, Object>>) updDataMap.get("svcModMapList");
        List<Map<String, String>> svcModOptMapList = (List<Map<String, String>>) updDataMap.get("svcModOptMapList");

        for (int i = 0; i < svcModMapList.size(); i++) {
            SVC_MOD_STSTMsg insSvcModStsTMsg = new SVC_MOD_STSTMsg();
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModStsPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_STS_SQ));
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModPk, (BigDecimal) svcModMapList.get(i).get("SVC_MOD_PK"));
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModDtlPk, (BigDecimal) svcModMapList.get(i).get("SVC_MOD_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcMachMstrPk, (BigDecimal) svcModMapList.get(i).get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModProcStsCd, (String) svcModMapList.get(i).get("SVC_MOD_PROC_STS_CD"));
            if (hasValue(rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"))){
                ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModOptDt, rsSvcModUpldWrk.getString("SVC_MOD_OPT_DT"));
            }
            if (svcModOptMapList != null && svcModOptMapList.size() > 0) {
                ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModOptCd, svcModOptMapList.get(0).get("SVC_MOD_OPT_CD"));
            }
            ZYPEZDItemValueSetter.setValue(insSvcModStsTMsg.svcModNoteTxt, rsSvcModUpldWrk.getString("SVC_MOD_NOTE_TXT"));

            EZDTBLAccessor.insert(insSvcModStsTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insSvcModStsTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // END 2023/03/31 S.Kitamura [QC#60927, ADD]

    private String createResultMessageArg(int normalCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (normalCount > 0 || errCount == 0) {
            builder.append(String.format(RESULT_MSG_INS, normalCount));
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
