/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB264001;

import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.CHK_DATE_PATTERN;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.INFO_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM0185E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8440E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8488E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8567E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8577E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8605E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8606E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NMAM8607E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB264001.constant.NMAB264001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;

import business.db.ART10FMsg;
import business.db.MOVE_ORG_RQST_DTLTMsg;
import business.db.MOVE_ORG_RQST_HDRTMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.TOC_ORG_STRU_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_DTL_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Mass Move Organization Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Fujitsu         M.Nakamura      Create          N/A
 * 2016/10/19   Hitachi         T.Mizuki        Update          QC#8156
 *</pre>
 */
public class NMAB264001 extends S21BatchMain {

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** userConstantVariable */
    private String userConstantVariable = null;

    /** process date time */
    private String procDt = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Move Resource Effective */
    private Map<String, Map<String, String>> moveEffMap = new HashMap<String, Map<String, String>>();

    /** ONLINE errorMsg */
    private StringBuilder onlineMsg = new StringBuilder();

    /** Total Process Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Current Normal Count */
    private int curNmlCount = 0;

    /** Current Error Count */
    private int curErrCount = 0;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB264001().executeBatch(NMAB264001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.userConstantVariable = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(userConstantVariable)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"User Constant Variable" });
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        if (this.userConstantVariable.equals(RQST_CRAT_SYS_TP.CSV_UPLOAD)) {
            this.batchHelper = new S21RequestBatchHelper();
        }

        this.moveEffMap.clear();
    }

    @Override
    protected void mainRoutine() {

        if (this.userConstantVariable.equals(RQST_CRAT_SYS_TP.ONLINE)) {
            Map<String, Object> inTrgtMap = new HashMap<String, Object>();

            inTrgtMap.put("glblCmpyCd", glblCmpyCd);
            inTrgtMap.put("rqstCratSysTpCd", RQST_CRAT_SYS_TP.ONLINE);
            inTrgtMap.put("rqstRsltTpCd", RQST_RSLT_TP.SUBMITTED);
            List<BigDecimal> trgtList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getTargetResource", inTrgtMap);

            if (trgtList == null) {
                // Target Data none.
                return;
            }

            for (BigDecimal trgtPk : trgtList) {
                this.doProcess(trgtPk, null);
            }

        } else {
            batchHelper = new S21RequestBatchHelper();
            if (!this.batchHelper.isRecord()) {
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            String upldCsvId = null;
            BigDecimal upldCsvRqstPk = null;

            for (ART10FMsg request : this.batchHelper.getRequestList()) {

                // Upload ID
                upldCsvId = getUpldCsvId(request);

                // Upload Request PK
                upldCsvRqstPk = getUpldCsvReqPk(request);
                this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

                this.doProcess(upldCsvRqstPk, request);
            }
        }
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    private void doProcess(BigDecimal trgtPk, ART10FMsg reqMsg) {
        PreparedStatement psWrk = null;
        ResultSet rsSet = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            BigDecimal moveOrgRqstHdrPk = null;

            if (this.userConstantVariable.equals(RQST_CRAT_SYS_TP.ONLINE)) {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("moveOrgRqstHdrPk", trgtPk);

                moveOrgRqstHdrPk = trgtPk;

                psWrk = ssmLLClient.createPreparedStatement("getRequestInfo", inWrkMap, execParam);
            } else {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("upldCsvRqstPk", trgtPk);

                moveOrgRqstHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MOVE_ORG_RQST_HDR_SQ);

                psWrk = ssmLLClient.createPreparedStatement("getWrkInfo", inWrkMap, execParam);
            }

            rsSet = psWrk.executeQuery();

            doProcessMoveOrgRqst(rsSet, reqMsg, moveOrgRqstHdrPk);

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(psWrk, rsSet);
        }

    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();

        if (S21StringUtil.isEmpty(uploadCsvId)) {
            throw new S21AbendException(NMAM8228E, new String[] {MSG_UPLD_CSV_RQST_PK});
        }

        return uploadCsvId;
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, new String[] {MSG_UPLD_CSV_RQST_PK});
        }

        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, new String[] {MSG_UPLD_CSV_RQST_PK});
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * doProcessMoveOrgRqst.
     * @param rsSet ResultSet
     * @throws SQLException 
     */
    private void doProcessMoveOrgRqst(ResultSet rsSet, ART10FMsg reqMsg, BigDecimal moveOrgRqstHdrPk) throws SQLException {

        boolean isSccess = true;
        String msgValue = null;
        String inUserId = null;
        Map<String, Object> curResrcInfo = new HashMap<String, Object>();
        Map<String, Object> moveResrcInfo = new HashMap<String, Object>();

        this.curErrCount = 0;
        this.curNmlCount = 0;
        this.onlineMsg.setLength(0);

        while (rsSet.next()) {
            isSccess = true;

            if (!ZYPCommonFunc.hasValue(inUserId)) {
                inUserId = rsSet.getString("RQST_USR_ID");
            }

            curResrcInfo = getResrcInfo(rsSet.getString("CUR_PSN_NUM"), rsSet.getString("CUR_TOC_CD"), null, "getCurResrcInfo");
            moveResrcInfo = getResrcInfo(rsSet.getString("MOVE_PSN_NUM"), null, convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, ""), "getMoveResrcInfo");

            if (curResrcInfo == null || moveResrcInfo == null) {
                String msg = "";
                if (curResrcInfo == null) {
                    if (moveResrcInfo == null) {
                        msg = "Current/Move Resource";
                    } else {
                        msg = "Current Resource";
                    }
                } else {
                    if (moveResrcInfo == null) {
                        msg = "Move Resource";
                    }
                }

                updateError(NMAM8440E, msg, reqMsg, rsSet);
                this.curErrCount++;
                if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    insMoveOrgRqstDtl(rsSet, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo, true);
                }
                continue;
            }

            // Same Organization.
            if (BigDecimal.ONE.compareTo(rsSet.getBigDecimal("ORG_REC_CNT")) < 0) {
                updateError(NMAM8577E, null, reqMsg, rsSet);
                this.curErrCount++;
                if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    insMoveOrgRqstDtl(rsSet, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo, true);
                }
                continue;
            }

            if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {

                // Check CSV Only.
                if (!checkMandatory(rsSet, reqMsg)) {
                    isSccess = false;
                }

                if (!checkDateFormat(rsSet, reqMsg)) {
                    isSccess = false;
                }

                if (!isSccess) {
                    this.curErrCount++;
                    insMoveOrgRqstDtl(rsSet, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo, true);
                    continue;
                }
            }

            if (!checkExistsMaster(rsSet, reqMsg)) {
                isSccess = false;
            }

            if (!checkDateFromThru(rsSet, reqMsg)) {
                isSccess = false;
            }

            if (!checkNotChngRqst(rsSet, reqMsg, curResrcInfo, moveResrcInfo)) {
                this.curErrCount++;
                if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    insMoveOrgRqstDtl(rsSet, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo, true);
                }
                continue;
            }

            if (!checkResourceBizArea(rsSet, reqMsg, curResrcInfo, moveResrcInfo)) {
                isSccess = false;
            }

            if (!existSameResrc(rsSet, reqMsg, curResrcInfo, moveResrcInfo)) {
                isSccess = false;
            }

            if (!checkResourceEffDt(rsSet, reqMsg, curResrcInfo, moveResrcInfo)) {
                isSccess = false;
            }

            if (!isSccess) {
                this.curErrCount++;
                if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                    insMoveOrgRqstDtl(rsSet, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo, true);
                }

                continue;
            }

            if (!insUpdTbl(rsSet, reqMsg, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo)) {
                rollback();
                isSccess = false;
            }

            if (isSccess) {
                this.curNmlCount++;
            } else {
                this.curErrCount++;
            }

        }

        String msgId = null;

        if (this.curErrCount > 0) {
            // mod start 2016/10/19 CSA QC#8156
            if (this.curNmlCount > 0) {
                msgValue = this.curNmlCount + INFO_MSG + " " + this.curErrCount + ERR_MSG;
            } else {
                msgValue = this.curErrCount + ERR_MSG;
            }
            // mod end 2016/10/19 CSA QC#8156
            msgId = NYXM0002E;
        } else {
            msgValue = this.curNmlCount + INFO_MSG;
            msgId = NYXM0001I;
        }
        // mod start 2016/10/19 CSA QC#8156
        StringBuilder sb = new StringBuilder();
        sb.append(msgId);
        sb.append(" ");
        sb.append(msgValue);

        if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            // CSV.
            // MOVE_ORG_RQST_HDR
            insMoveOrgRqstHdr(rsSet, inUserId, moveOrgRqstHdrPk, sb.toString());

            this.messenger.addMessageForFile(msgId, msgValue);
            this.messenger.uploadMessage();
        } else {
            // Online.
            updMoveOrgRqstHdr(moveOrgRqstHdrPk, sb.toString());
        }
        // mod end 2016/10/19 CSA QC#8156
        commit();

        this.totalNmlCount = this.totalNmlCount + this.curNmlCount;
        this.totalErrCount = this.totalErrCount + this.curErrCount;
    }

    private void updateError(String msgId, String msgPrm, ART10FMsg reqMsg, ResultSet rsSet) throws SQLException {

        if (!ZYPCommonFunc.hasValue(this.onlineMsg.toString())) {
            this.onlineMsg.append(S21MessageFunc.clspGetMessage(msgId, new String[] {msgPrm}));
        } else {
            this.onlineMsg.append(",").append(S21MessageFunc.clspGetMessage(msgId, new String[] {msgPrm}));
        }

        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
            MOVE_ORG_RQST_DTLTMsg inTMsg = new MOVE_ORG_RQST_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.moveOrgRqstDtlPk, rsSet.getBigDecimal("MOVE_ORG_RQST_DTL_PK"));

            inTMsg = (MOVE_ORG_RQST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);

            if (ZYPCommonFunc.hasValue(inTMsg.rqstDtlRsltCmntTxt)) {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
            } else {
                ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltCmntTxt, S21MessageFunc.clspGetMessage(msgId, new String[] {msgPrm}));
            }
            ZYPEZDItemValueSetter.setValue(inTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);

            EZDTBLAccessor.update(inTMsg);

        } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            this.messenger.addMessageForRecord(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), msgId, msgPrm);
            this.batchHelper.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
        }

        this.termCd = TERM_CD.WARNING_END;

    }

    private boolean checkMandatory(ResultSet rsSet, ART10FMsg reqMsg) throws SQLException {
        boolean isSuccess = true;

        if (!ZYPCommonFunc.hasValue(rsSet.getString("CUR_ORG_NM"))) {
            updateError(ZZZM9025E, "Current Organization", reqMsg, rsSet);
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("CUR_PSN_NUM"))) {
            updateError(ZZZM9025E, "Current Resource #", reqMsg, rsSet);
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("MOVE_PSN_NUM"))) {
            updateError(ZZZM9025E, "Move Resource #", reqMsg, rsSet);
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("MOVE_EFF_FROM_DT_TXT"))) {
            updateError(ZZZM9025E, "Move Effective From", reqMsg, rsSet);
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean checkDateFormat(ResultSet rsSet, ART10FMsg reqMsg) throws SQLException {
        boolean isSuccess = true;


        if (ZYPCommonFunc.hasValue(rsSet.getString("MOVE_EFF_FROM_DT_TXT"))) {
            String fromDtTxt = rsSet.getString("MOVE_EFF_FROM_DT_TXT");
            if (fromDtTxt.matches(CHK_DATE_PATTERN)) {
                fromDtTxt = convDate(fromDtTxt, ZYPDateUtil.TYPE_YYYYMMDD, "");

                if (!ZYPDateUtil.isValidDate(fromDtTxt, ZYPDateUtil.TYPE_YYYYMMDD)) {
                    updateError(NMAM8567E, "Move Effective From", reqMsg, rsSet);
                    isSuccess = false;
                }

            } else {
                updateError(NMAM8567E, "Move Effective From", reqMsg, rsSet);
                isSuccess = false;
            }

        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("MOVE_EFF_THRU_DT_TXT"))) {
            String thruDtTxt = rsSet.getString("MOVE_EFF_THRU_DT_TXT");
            if (thruDtTxt.matches(CHK_DATE_PATTERN)) {
                thruDtTxt = convDate(thruDtTxt, ZYPDateUtil.TYPE_YYYYMMDD, "");

                if (!ZYPDateUtil.isValidDate(thruDtTxt, ZYPDateUtil.TYPE_YYYYMMDD)) {
                    updateError(NMAM8567E, "Move Effective Thru", reqMsg, rsSet);
                    isSuccess = false;
                }
            } else {
                updateError(NMAM8567E, "Move Effective Thru", reqMsg, rsSet);
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    private boolean checkExistsMaster(ResultSet rsSet, ART10FMsg reqMsg) throws SQLException {
        boolean isSuccess = true;

        if (!ZYPCommonFunc.hasValue(rsSet.getString("CUR_PSN_NUM_MSTR"))) {
            updateError(NMAM8121E, "Current Resource #", reqMsg, rsSet);
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("MOVE_PSN_NUM_MSTR"))) {
            updateError(NMAM8121E, "Move Resource #", reqMsg, rsSet);
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean checkDateFromThru(ResultSet rsSet, ART10FMsg reqMsg) throws SQLException {
        boolean isSuccess = true;

        if (ZYPCommonFunc.hasValue(rsSet.getString("MOVE_EFF_FROM_DT_TXT"))
                && ZYPCommonFunc.hasValue(rsSet.getString("MOVE_EFF_THRU_DT_TXT"))) {
            String fromDt = convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
            String thruDt = convDate(rsSet.getString("MOVE_EFF_THRU_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
            if (ZYPDateUtil.compare(fromDt, thruDt) > 0) {
                updateError(NMAM0185E, null, reqMsg, rsSet);
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    private Map<String, Object> getResrcInfo(String psnNum, String tocCd, String moveEffFromDt, String statementId) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("gnrnTpCur", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpFut", GNRN_TP.FUTURE);
        ssmParam.put("slsDt", this.procDt);
        ssmParam.put("tocCd", tocCd);
        ssmParam.put("moveEffFromDt", moveEffFromDt);
        ssmParam.put("psnNum", psnNum);
        return (Map<String, Object>) this.ssmBatchClient.queryObject(statementId, ssmParam);

    }

    private boolean checkNotChngRqst(ResultSet rsSet, ART10FMsg reqMsg, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        boolean isSuccess = true;

        boolean curErrFlg = false;
        boolean moveErrFlg = false;
        if (curResrcInfo != null) {
            if (rsSet.getString("CUR_PSN_NUM").equals((String) curResrcInfo.get("PSN_NUM"))) {
                if (Long.valueOf(rsSet.getString("RQST_UPTIME")) < Long.valueOf((String) curResrcInfo.get("EZUPTIME_TOC"))
                        || Long.valueOf(rsSet.getString("RQST_UPTIME")) < Long.valueOf((String) curResrcInfo.get("EZUPTIME_REQ"))) {
                    curErrFlg = true;
                }
            }
        }

        if (moveResrcInfo != null) {
            if (rsSet.getString("MOVE_PSN_NUM").equals((String) moveResrcInfo.get("PSN_NUM"))) {
                String upTimeToc = null;
                String upTimeReq = null;
                String effDtTosr = null;

                if (this.moveEffMap.containsKey((String) moveResrcInfo.get("PSN_NUM"))) {
                    upTimeToc = (String) this.moveEffMap.get((String) moveResrcInfo.get("PSN_NUM")).get("EZUPTIME_TOC");
                    upTimeReq = (String) this.moveEffMap.get((String) moveResrcInfo.get("PSN_NUM")).get("EZUPTIME_REQ");
                } else {
                    Map<String, String> moveMap = new HashMap<String, String>();
                    upTimeToc = (String) moveResrcInfo.get("EZUPTIME_TOC");
                    upTimeReq = (String) moveResrcInfo.get("EZUPTIME_REQ");
                    effDtTosr = (String) moveResrcInfo.get("EFF_FROM_DT_TOSR");
                    moveMap.put("EZUPTIME_TOC", upTimeToc);
                    moveMap.put("EZUPTIME_REQ", upTimeReq);
                    moveMap.put("EFF_FROM_DT_TOSR", effDtTosr);
                    this.moveEffMap.put((String) moveResrcInfo.get("PSN_NUM"), moveMap);
                }

                if (ZYPCommonFunc.hasValue(upTimeToc) && ZYPCommonFunc.hasValue(upTimeReq)) {
                    if (Long.valueOf(rsSet.getString("RQST_UPTIME")) < Long.valueOf(upTimeToc)
                            || Long.valueOf(rsSet.getString("RQST_UPTIME")) < Long.valueOf(upTimeReq)) {
                        moveErrFlg = true;
                    }
                }
            }
        }

        String msgId = null;
        if (curErrFlg) {
            if (moveErrFlg) {
                msgId = NMAM8607E;
            } else {
                msgId = NMAM8605E;
            }
        } else {
            if (moveErrFlg) {
                msgId = NMAM8606E;
            }
        }

        if (msgId != null) {
            updateError(msgId, null, reqMsg, rsSet);
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean checkResourceBizArea(ResultSet rsSet, ART10FMsg reqMsg, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        boolean isSuccess = true;

        if (moveResrcInfo == null
                || !ZYPCommonFunc.hasValue((String) moveResrcInfo.get("PSN_NUM"))) {
            updateError(NMAM8121E, "Move Resource", reqMsg, rsSet);
            isSuccess = false;
            return isSuccess;
        }

        if (ZYPCommonFunc.hasValue((String) moveResrcInfo.get("CTRY_CD"))) {
            if (CTRY.UNITED_STATES_OF_AMERICA.equals((String) moveResrcInfo.get("CTRY_CD"))) {
                if (!ZYPCommonFunc.hasValue((String) moveResrcInfo.get("POST_CD"))
                        || !ZYPCommonFunc.hasValue((String) moveResrcInfo.get("ST_CD"))
                        || !ZYPCommonFunc.hasValue((String) moveResrcInfo.get("CTY_ADDR"))) {
                    updateError(NMAM8121E, "Physical Address", reqMsg, rsSet);
                    isSuccess = false;
                }
            } else {
                if (!ZYPCommonFunc.hasValue((String) moveResrcInfo.get("CTY_ADDR"))) {
                    updateError(NMAM8121E, "Physical Address", reqMsg, rsSet);
                    isSuccess = false;
                }
            }
        } else {
            updateError(NMAM8121E, "Country Code", reqMsg, rsSet);
            isSuccess = false;
        }

        if (BIZ_AREA_ORG.SALES.equals(curResrcInfo.get("BIZ_AREA_ORG_CD"))
                && BIZ_AREA_ORG.SALES.equals(moveResrcInfo.get("BIZ_AREA_ORG_CD"))) {
            updateError(NMAM8440E, "This Resource", reqMsg, rsSet);
            isSuccess = false;
            return isSuccess;
        }

        if (ZYPCommonFunc.hasValue((String) moveResrcInfo.get("BIZ_AREA_ORG_CD"))) {
            if (BIZ_AREA_ORG.SALES.equals(moveResrcInfo.get("BIZ_AREA_ORG_CD"))
                    || BIZ_AREA_ORG.SERVICE.equals(moveResrcInfo.get("BIZ_AREA_ORG_CD"))) {
                if (!ZYPCommonFunc.hasValue((String) moveResrcInfo.get("LINE_BIZ_TP_CD"))) {
                    updateError(NMAM8121E, "Move Resource#", reqMsg, rsSet);
                    isSuccess = false;
                    return isSuccess;
                }
            }
        }

        String fromDt = convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        String effDtTosr = null;
        if (ZYPCommonFunc.hasValue((String) curResrcInfo.get("EFF_FROM_DT_TOSR"))) {
            effDtTosr = (String) curResrcInfo.get("EFF_FROM_DT_TOSR");
        }

        if (ZYPCommonFunc.hasValue(effDtTosr)) {
            if (ZYPDateUtil.compare(ZYPDateUtil.addDays(fromDt, -1), effDtTosr) < 0) {
                updateError(NMAM8440E, "This Resource", reqMsg, rsSet);
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    private boolean existSameResrc(ResultSet rsSet, ART10FMsg reqMsg, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        boolean isSuccess = true;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("gnrnTpCdCur", GNRN_TP.CURRENT);
        params.put("gnrnTpCdFut", GNRN_TP.FUTURE);
        params.put("bizAreaOrgCd", (String) curResrcInfo.get("BIZ_AREA_ORG_CD"));
        params.put("orgCd", rsSet.getString("CUR_ORG_CD"));
        params.put("tocCd", rsSet.getString("CUR_TOC_CD"));
        params.put("psnNum", rsSet.getString("MOVE_PSN_NUM"));
        params.put("orgFuncRoleTpCd", (String) curResrcInfo.get("ORG_FUNC_ROLE_TP_CD"));

        String fromDt = convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        params.put("effFromDt", fromDt);

        String thruDt = convDate(rsSet.getString("MOVE_EFF_THRU_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        if (ZYPCommonFunc.hasValue(thruDt)) {
            params.put("effThruDt", thruDt);
        } else {
            params.put("effThruDt", "99991231");
        }

        Map<String, Object> rsltMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getSameResrc", params);
        if (rsltMap != null) {
            isSuccess = false;
            return isSuccess;
        }

        return isSuccess;
    }

    private boolean checkResourceEffDt(ResultSet rsSet, ART10FMsg reqMsg, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        boolean isSuccess = true;

        String fromDt = convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        if (!(ZYPDateUtil.compare((String) moveResrcInfo.get("EFF_FROM_DT_PSN"), fromDt) <= 0)
                || !(ZYPDateUtil.compare((String) moveResrcInfo.get("EFF_THRU_DT_PSN"), fromDt) >= 0)) {
            updateError(NMAM8488E, null, reqMsg, rsSet);
            isSuccess = false;
        }

        String thruDt = convDate(rsSet.getString("MOVE_EFF_THRU_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        if (!ZYPCommonFunc.hasValue(thruDt)) {
            thruDt = "99991231";
        }
        if (!(ZYPDateUtil.compare((String) moveResrcInfo.get("EFF_FROM_DT_PSN"), thruDt) <= 0)
                || !(ZYPDateUtil.compare((String) moveResrcInfo.get("EFF_THRU_DT_PSN"), thruDt) >= 0)) {
            updateError(NMAM8488E, null, reqMsg, rsSet);
            isSuccess = false;
        }

        return isSuccess;
    }

    private boolean insUpdTbl(ResultSet rsSet, ART10FMsg reqMsg, BigDecimal moveOrgRqstHdrPk, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        boolean isSuccess = true;

        // TOC_ORG_STRU_RELN(Update)
        if (!updTocOrgStruReln(rsSet, curResrcInfo, moveResrcInfo)) {
            isSuccess = false;
            return isSuccess;
        }

        // ORG _FUNC_ASG(Update)
        if (!updOrgFuncAgn(rsSet, curResrcInfo, moveResrcInfo)) {
            isSuccess = false;
            return isSuccess;
        }

        String tocCd = ZYPExtnNumbering.getUniqueID("TOC_CD");
        // ORG_TOC_CHNG_RQST(Insert)
        if (!insOrgTocChngRqst(rsSet, tocCd, curResrcInfo, moveResrcInfo)) {
            isSuccess = false;
            return isSuccess;
        }

        // TOC_ORG_STRU_RELN(Insert)
        if (!insTocOrgStruReln(rsSet, tocCd, curResrcInfo, moveResrcInfo)) {
            isSuccess = false;
            return isSuccess;
        }

        // ORG _FUNC_ASG(Insert)
        if (!insOrgFuncAgn(rsSet, tocCd, curResrcInfo, moveResrcInfo)) {
            isSuccess = false;
            return isSuccess;
        }

        // MOVE_ORG_RQST_DTL
        if (!insMoveOrgRqstDtl(rsSet, moveOrgRqstHdrPk, curResrcInfo, moveResrcInfo, false)) {
            isSuccess = false;
            return isSuccess;
        }

        return isSuccess;
    }

    private boolean updTocOrgStruReln(ResultSet rsSet, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        TOC_ORG_STRU_RELNTMsg updTMsg = new TOC_ORG_STRU_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.orgStruTpCd, (String) curResrcInfo.get("ORG_STRU_TP_CD"));
        ZYPEZDItemValueSetter.setValue(updTMsg.tocCd, (String) curResrcInfo.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(updTMsg.orgCd, (String) curResrcInfo.get("ORG_CD"));

        updTMsg = (TOC_ORG_STRU_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
        if (updTMsg == null) {
            return false;
        }

        String fromDt = convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        ZYPEZDItemValueSetter.setValue(updTMsg.effThruDt, ZYPDateUtil.addDays(fromDt, -1));

        EZDTBLAccessor.update(updTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean updOrgFuncAgn(ResultSet rsSet, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        ORG_FUNC_ASGTMsg updTMsg = new ORG_FUNC_ASGTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.tocCd, (String) curResrcInfo.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(updTMsg.psnCd, (String) curResrcInfo.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(updTMsg.effFromDt, (String) curResrcInfo.get("EFF_FROM_DT_OFA"));

        updTMsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
        if (updTMsg == null) {
            return false;
        }

        String fromDt = convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, "");
        ZYPEZDItemValueSetter.setValue(updTMsg.effThruDt, ZYPDateUtil.addDays(fromDt, -1));
        ZYPEZDItemValueSetter.setValue(updTMsg.gnrnTpCd, GNRN_TP.FUTURE);

        EZDTBLAccessor.update(updTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean insOrgTocChngRqst(ResultSet rsSet, String tocCd, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        ORG_TOC_CHNG_RQSTTMsg insTMsg = new ORG_TOC_CHNG_RQSTTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.orgChngRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORG_CHNG_RQST_SQ));
        String tocNm = ZYPCommonFunc.concatString((String) moveResrcInfo.get("PSN_FIRST_NM"), " ", (String) moveResrcInfo.get("PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(insTMsg.tocNm, S21StringUtil.subStringByLength(tocNm, 0, insTMsg.getAttr("tocNm").getDigit()));
        ZYPEZDItemValueSetter.setValue(insTMsg.coaBrCd, (String) moveResrcInfo.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.coaChCd, (String) moveResrcInfo.get("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.coaProdCd, (String) moveResrcInfo.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.jobTtlCd, (String) moveResrcInfo.get("JOB_TTL_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.orgFuncRoleTpCd, (String) curResrcInfo.get("ORG_FUNC_ROLE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.orgCd, (String) curResrcInfo.get("ORG_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.orgLayerNum, (BigDecimal) curResrcInfo.get("ORG_LAYER_NUM"));
        ZYPEZDItemValueSetter.setValue(insTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        ZYPEZDItemValueSetter.setValue(insTMsg.coaExtnCd, (String) moveResrcInfo.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.coaCcCd, (String) moveResrcInfo.get("COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.asgCustFromNm, (String) curResrcInfo.get("ASG_CUST_FROM_NM"));
        ZYPEZDItemValueSetter.setValue(insTMsg.asgCustToNm, (String) curResrcInfo.get("ASG_CUST_TO_NM"));
        ZYPEZDItemValueSetter.setValue(insTMsg.coaCmpyCd, (String) moveResrcInfo.get("COA_CMPY_CD"));

        EZDTBLAccessor.insert(insTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean insTocOrgStruReln(ResultSet rsSet, String tocCd, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        TOC_ORG_STRU_RELNTMsg insTMsg = new TOC_ORG_STRU_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.orgStruTpCd, (String) curResrcInfo.get("ORG_STRU_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.orgCd, (String) curResrcInfo.get("ORG_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.orgLayerNum, (BigDecimal) curResrcInfo.get("ORG_LAYER_NUM"));
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, ""));
        ZYPEZDItemValueSetter.setValue(insTMsg.effThruDt, convDate(rsSet.getString("MOVE_EFF_THRU_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, ""));

        EZDTBLAccessor.insert(insTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean insOrgFuncAgn(ResultSet rsSet, String tocCd, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo) throws SQLException {
        ORG_FUNC_ASGTMsg insTMsg = new ORG_FUNC_ASGTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.psnCd, (String) moveResrcInfo.get("PSN_CD"));
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, ""));
        ZYPEZDItemValueSetter.setValue(insTMsg.effThruDt, convDate(rsSet.getString("MOVE_EFF_THRU_DT_TXT"), ZYPDateUtil.TYPE_YYYYMMDD, ""));
        ZYPEZDItemValueSetter.setValue(insTMsg.gnrnTpCd, GNRN_TP.FUTURE);

        EZDTBLAccessor.insert(insTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean insMoveOrgRqstHdr(ResultSet rsSet, String inUserId, BigDecimal moveOrgRqstHdrPk, String rqstRsltCmntTxt) throws SQLException {
        if (!this.userConstantVariable.equals(RQST_CRAT_SYS_TP.CSV_UPLOAD)) {
            return true;
        }

        MOVE_ORG_RQST_HDRTMsg insTMsg = new MOVE_ORG_RQST_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.moveOrgRqstHdrPk, moveOrgRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstUsrId, inUserId);
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstCratTs, this.procDt + ZYPDateUtil.getCurrentSystemTime("HHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.CSV_UPLOAD);
        if (this.curErrCount > 0) {
            ZYPEZDItemValueSetter.setValue(insTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
        }
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstRsltCmntTxt, rqstRsltCmntTxt);

        EZDTBLAccessor.insert(insTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean updMoveOrgRqstHdr(BigDecimal moveOrgRqstHdrPk, String rqstRsltCmntTxt) throws SQLException {

        MOVE_ORG_RQST_HDRTMsg updTMsg = new MOVE_ORG_RQST_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updTMsg.moveOrgRqstHdrPk, moveOrgRqstHdrPk);

        updTMsg = (MOVE_ORG_RQST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
        if (updTMsg == null) {
            return false;
        }

        if (this.curErrCount > 0) {
            ZYPEZDItemValueSetter.setValue(updTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
        }
        ZYPEZDItemValueSetter.setValue(updTMsg.rqstRsltCmntTxt, rqstRsltCmntTxt);

        EZDTBLAccessor.update(updTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    private boolean insMoveOrgRqstDtl(ResultSet rsSet, BigDecimal moveOrgRqstHdrPk, Map<String, Object> curResrcInfo, Map<String, Object> moveResrcInfo, boolean isError) throws SQLException {

        if (!this.userConstantVariable.equals(RQST_CRAT_SYS_TP.CSV_UPLOAD)) {
            return true;
        }

        MOVE_ORG_RQST_DTLTMsg insTMsg = new MOVE_ORG_RQST_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.moveOrgRqstDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MOVE_ORG_RQST_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.moveOrgRqstHdrPk, moveOrgRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.curOrgCd, rsSet.getString("CUR_ORG_CD_MSTR"));
        ZYPEZDItemValueSetter.setValue(insTMsg.curPsnNum, rsSet.getString("CUR_PSN_NUM_MSTR"));
        ZYPEZDItemValueSetter.setValue(insTMsg.movePsnNum, rsSet.getString("MOVE_PSN_NUM_MSTR"));
        ZYPEZDItemValueSetter.setValue(insTMsg.moveEffFromDtTxt, convDate(rsSet.getString("MOVE_EFF_FROM_DT_TXT"), ZYPDateUtil.getJavaDateFormatString(), "/"));
        ZYPEZDItemValueSetter.setValue(insTMsg.moveEffThruDtTxt, convDate(rsSet.getString("MOVE_EFF_THRU_DT_TXT"), ZYPDateUtil.getJavaDateFormatString(), "/"));
        ZYPEZDItemValueSetter.setValue(insTMsg.massUpdRsnCmntTxt, rsSet.getString("MASS_UPD_RSN_CMNT_TXT"));
        if (isError) {
            ZYPEZDItemValueSetter.setValue(insTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
        }

        if (this.curErrCount > 0) {
            ZYPEZDItemValueSetter.setValue(insTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
        } else {
            insTMsg.rqstDtlRsltCmntTxt.clear();
        }

        EZDTBLAccessor.insert(insTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * Conversion Date Format
     * @param date String
     * @param rtnFmt String
     * @param rtnSep String
     * @throws SQLException 
     */
    private String convDate(String date,  String rtnFmt, String rtnSep) {

        if (!ZYPCommonFunc.hasValue(date)) {
            return "";
        }

        if (!date.matches(CHK_DATE_PATTERN)) {
            return date;
        }

        // Conversion Date Format
        String[] fromDateList =  date.split("/");

        if (fromDateList[0].length() == 1) {
            fromDateList[0] = ZYPCommonFunc.leftPad(fromDateList[0], 2, "0");
        }

        if (fromDateList[1].length() == 1) {
            fromDateList[1] = ZYPCommonFunc.leftPad(fromDateList[1], 2, "0");
        }

        if (ZYPDateUtil.TYPE_MMDDYYYY.equals(rtnFmt)) {
            return fromDateList[0] + rtnSep + fromDateList[1] + rtnSep + fromDateList[2];
        } else if (ZYPDateUtil.TYPE_YYYYMMDD.equals(rtnFmt)) {
            return fromDateList[2] + rtnSep + fromDateList[0] + rtnSep + fromDateList[1];
        }

        return fromDateList[2] + rtnSep + fromDateList[0] + rtnSep + fromDateList[1];
    }
}
