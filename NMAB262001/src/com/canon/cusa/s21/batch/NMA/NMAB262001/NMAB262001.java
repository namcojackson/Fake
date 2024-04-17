/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB262001;

import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.COMPLETED;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.CUR_PSN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.INCLUDING_ERROR;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.MOVE_EFF_FROM_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.MOVE_EFF_THRU_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.MOVE_PSN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.MSG_TRTY_UPD_MODE_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM0071E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM0179E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM0183E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM0185E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8406E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8449E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8450E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8478E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8486E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8487E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8488E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NMAM8511E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.ONLINE;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.SLASH;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.SUBMITTED;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.TRTY_END_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.TRTY_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.TRTY_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.TRTY_UPD_MODE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.TRTY_UPD_RQST_HDR_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.UPLOAD;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.ZZZM9026E;

import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB262001.constant.NMAB262001Constant.RESULT_MSG_ERR;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.DS_ORG_RELNTMsg;
import business.db.DS_ORG_RELNTMsgArray;
import business.db.DS_ORG_RESRC_RELNTMsg;
import business.db.DS_ORG_RESRC_RELNTMsgArray;
import business.db.DS_ORG_UNITTMsg;
import business.db.DS_ORG_UNITTMsgArray;
import business.db.TRTY_RULETMsg;
import business.db.TRTY_RULETMsgArray;
import business.db.TRTY_UPD_MODE_TPTMsg;
import business.db.TRTY_UPD_RQST_DTLTMsg;
import business.db.TRTY_UPD_RQST_HDRTMsg;
import business.db.TRTY_UPD_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_UPD_MODE_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Territory Mass Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         T.Mizuki        Create          N/A
 * 2016/06/20   Hitachi         Y.Takeno        Update          QC#8156
 * 2016/07/22   Hitachi         J.Kim           Update          QC#11908
 * 2016/09/23   Fujitsu         C.Yokoi         Update          QC#8156
 * 2016/10/19   Hitachi         T.Mizuki        Update          QC#8156
 *</pre>
 */

public class NMAB262001 extends S21BatchMain {

    /** Mode */
    private String mode = null;

    /** UserProfile */
    private S21UserProfileService profile = null;

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

// START 2016/06/20 [QC#8156, ADD]
    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;
// END 2016/06/20 [QC#8156, ADD]

    /** userConstantVariable */
    private String userConstantVariable = null;

    /** process date time */
    private String procDt = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Process Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /**
     */
    @Override
    protected void initRoutine() {
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.procDt = ZYPDateUtil.getBatProcDate();
        if (!ZYPCommonFunc.hasValue(procDt)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Process Date" });
        }

        this.userConstantVariable = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(userConstantVariable)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"User Constant Variable" });
        }

    }

    /**
     */
    @Override
    protected void mainRoutine() {

        glblCmpyCd = getGlblCmpyCd();

        if (this.userConstantVariable.equals(ONLINE)) {
            this.doProcess(null);
        } else {
            batchHelper = new S21RequestBatchHelper();
            if (!this.batchHelper.isRecord()) {
                this.termCd = TERM_CD.WARNING_END;
                return;
            }
            for (ART10FMsg request : this.batchHelper.getRequestList()) {
                this.doProcess(request);
            }
        }
    }

    /**
     */
    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB262001().executeBatch(NMAB262001.class.getSimpleName());
    }

    /**
     * @param fMsg ART10FMsg
     */
    private void doProcess(ART10FMsg fMsg) {

        PreparedStatement psWrk = null;
        ResultSet rsWrk = null;
        String upldCsvId = null;
        BigDecimal upldCsvRqstPk = null;

        // Upload Mode : Get key for Request Data
        if (this.userConstantVariable.equals(UPLOAD)) {
            // Upload ID
            upldCsvId = getUpldCsvId(fMsg);
            EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

            // Upload Request PK
            upldCsvRqstPk = getUpldCsvReqPk(fMsg);
            EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);

            // START 2016/06/20 [QC#8156, ADD]
            this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);
            // END 2016/06/20 [QC#8156, ADD]
        }

        try {
            // 1.1 Get active request data
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            BigDecimal hdrSq = null;
            List<TRTY_UPD_RQST_DTLTMsg> msgLst = new ArrayList<TRTY_UPD_RQST_DTLTMsg>();
            Boolean isError = false;

            if (this.userConstantVariable.equals(ONLINE)) {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("rqstCratSysTpCd", ONLINE);
                inWrkMap.put("rqstRsltTpCd", SUBMITTED);

                psWrk = ssmLLClient.createPreparedStatement("getRequestInfo", inWrkMap, execParam);
                rsWrk = psWrk.executeQuery();

            } else if (this.userConstantVariable.equals(UPLOAD)) {
                hdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_UPD_RQST_HDR_SQ);
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("upldCsvRqstPk", upldCsvRqstPk);

                psWrk = ssmLLClient.createPreparedStatement("getWrkInfo", inWrkMap, execParam);
                rsWrk = psWrk.executeQuery();
            }

            // do nothing if there is no request
            if (!rsWrk.next()) {
                return;
            }

            List<Map<String, Object>> trtyUpdRqstHdrPkMapList = new ArrayList<Map<String,Object>>();
            Map<String, Object> trtyUpdRqstPkMap = new HashMap<String, Object>();
            BigDecimal hdrPk = null;
            BigDecimal nextHdrPk = null;
            String rqstCmntTxt = null;
            // START 2016/06/20 [QC#8156,ADD]
            int insertCount = 0;
            int updateCount = 0;
            int errorCount = 0;
            // END 2016/06/20 [QC#8156,ADD]
            int errorInfo = 0;
            do {
                // START 2016/06/20 [QC#8156,ADD]
                if (totalCount > 0) {
                    if (!isError) {
                        updateCount++;
                    } else {
                        errorCount++;
                        errorInfo++;
                    }
                }
                // END 2016/06/20 [QC#8156,ADD]
                totalCount++;

                // START 2016/06/20 [QC#8156,MOD]
                if (this.userConstantVariable.equals(ONLINE)) {
                    // HeaderCheck
                    nextHdrPk = rsWrk.getBigDecimal("TRTY_UPD_RQST_HDR_PK");
                    if (hasValue(hdrPk) && !nextHdrPk.equals(hdrPk)) {
                        // commit or rollback organization master
                        // 2016/07/22 CSA-QC#11908 Mod Start
                        // if (!isError) {
                        if (errorInfo == 0) {
                        // 2016/07/22 CSA-QC#11908 Mod End
                            // register request result to TRTY_UPD_RQST_HDR
                            registRequestResultToHeader(hdrPk, hdrSq, upldCsvId, rqstCmntTxt, insertCount, updateCount, errorCount, false);
                            commit();
                            totalNmlCount++;
                        } else {
                            // rollback organization master
                            rollback();

                            // register request result to TRTY_UPD_RQST_HDR
                            registRequestResultToHeader(hdrPk, hdrSq, upldCsvId, rqstCmntTxt, insertCount, updateCount, errorCount, true);
                            commit();
                            totalErrCount++;
                        }
                        errorInfo = 0;
                    }
                }
                // END 2016/06/20 [QC#8156,MOD]

                // START 2016/06/20 [QC#8156,MOD]
                if (this.userConstantVariable.equals(UPLOAD)) {
                    // rqstCmntTxt = rsWrk.getString("UPLD_CSV_RQST_CMNT_TXT");
                    rqstCmntTxt = rsWrk.getString("RQST_CMNT_TXT");
                }
                if (this.userConstantVariable.equals(ONLINE)) {
                    hdrPk = rsWrk.getBigDecimal("TRTY_UPD_RQST_HDR_PK");
                }
                // END 2016/06/20 [QC#8156,MOD]
                isError = false;
                Integer cnt = 0;
                String psnCd = null;

                // 2.1 count same territory request
                if (this.userConstantVariable.equals(ONLINE)) {
                    inWrkMap.put("trtyOrgCd", rsWrk.getString(TRTY_ORG_CD));
                    cnt = (Integer) this.ssmBatchClient.queryObject("cntRequestInfo", inWrkMap, execParam);
                } else if (userConstantVariable.equals(UPLOAD)) {
                    inWrkMap.put("trtyOrgNm", rsWrk.getString(TRTY_ORG_NM));
                    cnt =  (Integer) this.ssmBatchClient.queryObject("cntWrkInfo", inWrkMap, execParam);
                }

                String moveEffFromDtTxt = null;
                String moveEffThruDtTxt = "99991231";
                String trtyEndDtTxt = "99991231";
                List<String> dtLst = new ArrayList<String>();

                // CSA-QC#8075 2016/05/10 Add Start
                if (S21StringUtil.isEmpty(rsWrk.getString(TRTY_UPD_MODE_TP_CD))) {
                    String err = S21MessageFunc.clspGetMessage(NMAM8228E, new String[] {MSG_TRTY_UPD_MODE_TP});
                    isError = true;
                    dtLst.add(rsWrk.getString(MOVE_EFF_FROM_DT_TXT));
                    dtLst.add(rsWrk.getString(MOVE_EFF_THRU_DT_TXT));
                    dtLst.add(rsWrk.getString(TRTY_END_DT_TXT));
                    TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                    msgLst.add(tMsg);
                    continue;
                } else {
                    this.mode = rsWrk.getString(TRTY_UPD_MODE_TP_CD);
                }
                // CSA-QC#8075 2016/05/10 Add End

                // 2.3 Check required data : Move Effective From Date
                if (TRTY_UPD_MODE_TP.MOVE_RESOURCE.equals(this.mode)) {
                    if (S21StringUtil.isEmpty(rsWrk.getString(MOVE_EFF_FROM_DT_TXT))) {
                        String[] msg = {MOVE_EFF_FROM_DT_TXT };
                        String err = S21MessageFunc.clspGetMessage(NMAM8228E, msg);
                        dtLst.add(moveEffFromDtTxt);
                        dtLst.add(moveEffThruDtTxt);
                        dtLst.add(trtyEndDtTxt);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // Change Format : Move Effective From Date
                    if (rsWrk.getString(MOVE_EFF_FROM_DT_TXT).indexOf(SLASH) != -1) {
                        moveEffFromDtTxt = dateFormat(rsWrk.getString(MOVE_EFF_FROM_DT_TXT));
                        if (!hasValue(moveEffFromDtTxt)) {
                            String[] msg = {MOVE_EFF_FROM_DT_TXT };
                            String err = S21MessageFunc.clspGetMessage(NMAM8406E, msg);
                            dtLst.add(moveEffFromDtTxt);
                            dtLst.add(moveEffThruDtTxt);
                            dtLst.add(trtyEndDtTxt);
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }
                    } else {
                        moveEffFromDtTxt = rsWrk.getString(MOVE_EFF_FROM_DT_TXT);
                    }

                    // Check required data : Move Effective Through Date
                    if (!S21StringUtil.isEmpty(rsWrk.getString(MOVE_EFF_THRU_DT_TXT))) {
                        if (rsWrk.getString(MOVE_EFF_THRU_DT_TXT).indexOf(SLASH) != -1) {
                            moveEffThruDtTxt = dateFormat(rsWrk.getString(MOVE_EFF_THRU_DT_TXT));
                            if (!hasValue(moveEffThruDtTxt)) {
                                String[] msg = {MOVE_EFF_THRU_DT_TXT };
                                String err = S21MessageFunc.clspGetMessage(NMAM8406E, msg);
                                dtLst.add(moveEffFromDtTxt);
                                dtLst.add(moveEffThruDtTxt);
                                dtLst.add(trtyEndDtTxt);
                                isError = true;
                                TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                                msgLst.add(tMsg);
                                continue;
                            }
                        } else {
                            moveEffThruDtTxt = rsWrk.getString(MOVE_EFF_THRU_DT_TXT);
                        }
                    }
                } else if (TRTY_UPD_MODE_TP.END_TERRITORIES.equals(this.mode)) {
                    // Check required data : Territory End Date
                    if (!S21StringUtil.isEmpty(rsWrk.getString(TRTY_END_DT_TXT))) {
                        if (rsWrk.getString(TRTY_END_DT_TXT).indexOf(SLASH) != -1) {
                            trtyEndDtTxt = dateFormat(rsWrk.getString(TRTY_END_DT_TXT));
                            if (!hasValue(trtyEndDtTxt)) {
                                String[] msg = {TRTY_END_DT_TXT };
                                String err = S21MessageFunc.clspGetMessage(NMAM8406E, msg);
                                dtLst.add(moveEffFromDtTxt);
                                dtLst.add(moveEffThruDtTxt);
                                dtLst.add(trtyEndDtTxt);
                                isError = true;
                                TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                                msgLst.add(tMsg);
                                continue;
                            }
                        } else {
                            trtyEndDtTxt = rsWrk.getString(TRTY_END_DT_TXT);
                        }
                    }
                }

                // Error if other active request contains same Territory
                dtLst.add(moveEffFromDtTxt);
                dtLst.add(moveEffThruDtTxt);
                dtLst.add(trtyEndDtTxt);
                if (cnt > 1) {
                    String err = S21MessageFunc.clspGetMessage(NMAM8486E);
                    isError = true;
                    TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                    msgLst.add(tMsg);
                    continue;
                }

                // 2.2 Check required data : Territory ID or Name
                if (this.userConstantVariable.equals(ONLINE)) {
                    if (S21StringUtil.isEmpty(rsWrk.getString(TRTY_ORG_CD))) {
                        String[] msg = {TRTY_ORG_CD };
                        String err = S21MessageFunc.clspGetMessage(NMAM8228E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }
                } else if (userConstantVariable.equals(UPLOAD)) {
                    if (S21StringUtil.isEmpty(rsWrk.getString(TRTY_ORG_NM))) {
                        String[] msg = {TRTY_ORG_NM };
                        String err = S21MessageFunc.clspGetMessage(NMAM8228E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }
                }

                // Check required data : Person Number
                if (S21StringUtil.isEmpty(rsWrk.getString(CUR_PSN_NUM))) {
                    String[] msg = {CUR_PSN_NUM };
                    String err = S21MessageFunc.clspGetMessage(NMAM8228E, msg);
                    isError = true;
                    TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                    msgLst.add(tMsg);
                    continue;
                }

                // 2.4 Master Check : Territory Update Mode Type
                TRTY_UPD_MODE_TPTMsg tpTMsg = getTpCd(this.mode);
                if (tpTMsg == null) {
                    String[] msg = {TRTY_UPD_MODE_TP_CD };
                    String err = S21MessageFunc.clspGetMessage(NMAM8487E, msg);
                    isError = true;
                    TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                    msgLst.add(tMsg);
                    continue;
                }

                // Master Check : Territory Update Request Header
                String reqEzuptime = null;
                if (this.userConstantVariable.equals(ONLINE)) {
                    TRTY_UPD_RQST_HDRTMsg hdrtMsg = getTrtyUpdRqstHdr(rsWrk.getBigDecimal(TRTY_UPD_RQST_HDR_PK));
                    reqEzuptime = hdrtMsg.ezUpTime.getValue();
                } else if (userConstantVariable.equals(UPLOAD)) {
                    TRTY_UPD_WRKTMsg wrkMsg = getTrtyUpdWrk(rsWrk.getBigDecimal(UPLD_CSV_RQST_PK), rsWrk.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
                    reqEzuptime = wrkMsg.ezUpTime.getValue();
                }

                // 3. MODE : Move Resource
                if (TRTY_UPD_MODE_TP.MOVE_RESOURCE.equals(this.mode)) {
                    // 3.1 Check required data : Move Person Number
                    if (S21StringUtil.isEmpty(rsWrk.getString(MOVE_PSN_NUM))) {
                        String err = S21MessageFunc.clspGetMessage(NMAM8121E, new String[]{"Move To Resource"});
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // get Person Code
                    psnCd = getS21Psn(rsWrk.getString(MOVE_PSN_NUM), execParam);
                    if (!hasValue(psnCd)) {
                        String err = S21MessageFunc.clspGetMessage(NMAM8121E, new String[]{"Move To Resource"});
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 3.2 Check validate date : Move To / From Date
                    if (Integer.parseInt(moveEffThruDtTxt) < Integer.parseInt(moveEffFromDtTxt)) {
                        String err = S21MessageFunc.clspGetMessage(NMAM0185E);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 3.3 Check exclusive
                    Map<String, Object> ezuptimeMap = new HashMap<String, Object>();
                    ezuptimeMap.put("glblCmpyCd", glblCmpyCd);
                    ezuptimeMap.put("psnNum", rsWrk.getString(CUR_PSN_NUM));
                    if (userConstantVariable.equals(ONLINE)) {
                        ezuptimeMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        ezuptimeMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    ezuptimeMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

                    String curEzuptime = (String) this.ssmBatchClient.queryObject("getEzuptime", ezuptimeMap, execParam);
                    if (!hasValue(curEzuptime)) {
                        String err = S21MessageFunc.clspGetMessage(NMAM8121E, new String[]{"Current Resource"});
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 2016/05/13 CSA-QC#8255 Del Start
                    // ezuptimeMap.put("psnNum", rsWrk.getString(MOVE_PSN_NUM));
                    // String movEzuptime = (String) this.ssmBatchClient.queryObject("getEzuptime", ezuptimeMap, execParam);
                    // 2016/05/13 CSA-QC#8255 Del End

                    if (Long.valueOf(curEzuptime) > Long.valueOf(reqEzuptime)) {
                     // 2016/05/13 CSA-QC#8255 Del Start
                     // if (Long.valueOf(movEzuptime) > Long.valueOf(reqEzuptime)) {
                     //   String[] msg = {"Current/Move Resource#", "current/move resource#'s last update time is after request date time." };
                     //   String err = S21MessageFunc.clspGetMessage(NMAM8478E, msg);
                     //     isError = true;
                     //     TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                     //     msgLst.add(tMsg);
                     //     continue;
                     // } else {
                     // 2016/05/13 CSA-QC#8255 Del End
                            String[] msg = {"Current Resource#", "current resource#'s last update time is after request date time." };
                            String err = S21MessageFunc.clspGetMessage(NMAM8478E, msg);
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                     // }
                     // 2016/05/13 CSA-QC#8255 Del Start
                     // } else if (ZYPCommonFunc.hasValue(movEzuptime) && Long.valueOf(movEzuptime) > Long.valueOf(reqEzuptime)) {
                     // String[] msg = {"Move Resource#", "Move resource#'s last update time is after request date time." };
                     // String err = S21MessageFunc.clspGetMessage(NMAM8478E, msg);
                     // isError = true;
                     // TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                     // msgLst.add(tMsg);
                     // continue;
                     // 2016/05/13 CSA-QC#8255 Del End
                    }

                    // 2016/05/13 CSA-QC#8255 Add Start
                    Map<String, Object> chkMoveToDsOrgRsrcRelnMap = new HashMap<String, Object>();
                    chkMoveToDsOrgRsrcRelnMap.put("glblCmpyCd", glblCmpyCd);
                    if (userConstantVariable.equals(ONLINE)) {
                        chkMoveToDsOrgRsrcRelnMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        chkMoveToDsOrgRsrcRelnMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    chkMoveToDsOrgRsrcRelnMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                    chkMoveToDsOrgRsrcRelnMap.put("gnrnTp_current", GNRN_TP.CURRENT);
                    chkMoveToDsOrgRsrcRelnMap.put("gnrnTp_future", GNRN_TP.FUTURE);
                    chkMoveToDsOrgRsrcRelnMap.put("maxDt", "99991231");
                    chkMoveToDsOrgRsrcRelnMap.put("psnNum", rsWrk.getString(MOVE_PSN_NUM));
                    List<Map<String, String>> relnTermList = new ArrayList<Map<String, String>>();
                    relnTermList = this.ssmBatchClient.queryObjectList("checkMoveToResrcTrtyRelnExist", chkMoveToDsOrgRsrcRelnMap, execParam);

                    boolean hasRelnTermError = false;
                    for (Map<String, String> relnTermMap : relnTermList) {
                        if (moveEffFromDtTxt.compareTo(relnTermMap.get("EFF_FROM_DT")) >= 0 && moveEffFromDtTxt.compareTo(relnTermMap.get("EFF_THRU_DT")) <= 0) {
                            hasRelnTermError = true;
                            break;
                        }

                        if (moveEffThruDtTxt.compareTo(relnTermMap.get("EFF_FROM_DT")) >= 0 && moveEffThruDtTxt.compareTo(relnTermMap.get("EFF_THRU_DT")) <= 0) {
                            hasRelnTermError = true;
                            break;
                        }

                        if (moveEffFromDtTxt.compareTo(relnTermMap.get("EFF_FROM_DT")) <= 0 && moveEffFromDtTxt.compareTo(relnTermMap.get("EFF_THRU_DT")) >= 0) {
                            hasRelnTermError = true;
                            break;
                        }
                    }

                    if (hasRelnTermError) {
                        String err = S21MessageFunc.clspGetMessage(NMAM8511E);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }
                    // 2016/05/13 CSA-QC#8255 Add End

                    // 3.4 Check validate date : Territory Date
                    Map<String, Object> dtMap = new HashMap<String, Object>();
                    dtMap.put("glblCmpyCd", glblCmpyCd);
                    if (userConstantVariable.equals(ONLINE)) {
                        dtMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        dtMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    dtMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                    Map<String, String> rsMap = (Map<String, String>) this.ssmBatchClient.queryObject("getResourceDt", dtMap, execParam);
                    String effFromDt = null;
                    String effThruDt = null;
                    effFromDt = rsMap.get("EFF_FROM_DT");
                    effThruDt = rsMap.get("EFF_THRU_DT");

                    if (Long.valueOf(effFromDt) > Long.valueOf(moveEffFromDtTxt)) {
                        String err = S21MessageFunc.clspGetMessage(NMAM8488E);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }
                    if (hasValue(effThruDt)) {
                        if (Long.valueOf(effThruDt) < Long.valueOf(moveEffThruDtTxt)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM8488E);
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }
                    }

                 // 2016/05/13 CSA-QC#8255 Del Start
                 // 3.5 Error if Business area of Move to and Move from does not match
                 // String businessAreaBase = null;
                 // String businessArea = null;
                 // Map<String, Object> areaMap = new HashMap<String, Object>();
                 // areaMap.put("glblCmpyCd", glblCmpyCd);
                 // // areaMap.put("psnNum", rsWrk.getString(MOVE_PSN_NUM));
                 // if (userConstantVariable.equals(ONLINE)) {
                 //     dtMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                 //  } else if (userConstantVariable.equals(UPLOAD)) {
                 //     dtMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                 // }
                 // areaMap.put("slsDate", ZYPDateUtil.getSalesDate());
                 // areaMap.put("hitDate", "99991231");
                 // areaMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                 // businessAreaBase = (String) this.ssmBatchClient.queryObject("getBusinessAreaBase", areaMap, execParam);
                 // businessArea = (String) this.ssmBatchClient.queryObject("getBusinessArea", areaMap, execParam);
                 // if (ZYPCommonFunc.hasValue(businessAreaBase) && !businessAreaBase.equals(businessArea)) {
                 //     String[] msg = {"selected resource name's" };
                 //     String err = S21MessageFunc.clspGetMessage(NMAM8397E, msg);
                 //     isError = true;
                 //     TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                 //     msgLst.add(tMsg);
                 //     continue;
                 // }
                 // 2016/05/13 CSA-QC#8255 Del Start

                    // 3.6 Error if resource of Move to and Move from are same
                    if (rsWrk.getString(CUR_PSN_NUM).equals(rsWrk.getString(MOVE_PSN_NUM))) {
                        String[] msg = {"if selected resource is same resource(Move Territory To), it" };
                        String err = S21MessageFunc.clspGetMessage(NMAM0183E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 3.7 Error if Role of move to resource is not sales
                    Map<String, Object> getRoleMap = new HashMap<String, Object>();
                    getRoleMap.put("glblCmpyCd", glblCmpyCd);
                    getRoleMap.put("psnNum", rsWrk.getString(MOVE_PSN_NUM));
                    getRoleMap.put("slsDate", ZYPDateUtil.getSalesDate());
                    getRoleMap.put("hitDate", "99991231");
                    getRoleMap.put("slsFlg", ZYPConstant.FLG_ON_Y);
                    List<String> gnrnTpCd = new ArrayList<String>();
                    gnrnTpCd.add(GNRN_TP.CURRENT);
                    gnrnTpCd.add(GNRN_TP.FUTURE);
                    getRoleMap.put("gnrnTpCd", gnrnTpCd);
                    Integer cntRole = (Integer) this.ssmBatchClient.queryObject("cntRsrcRl", getRoleMap, execParam);
                    if (cntRole == 0) {
                        String[] msg = {"resource(Move To Territory)", "Sales" };
                        String err = S21MessageFunc.clspGetMessage(NMAM8449E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                // 4. MODE : End Territories
                } else if (TRTY_UPD_MODE_TP.END_TERRITORIES.equals(this.mode)) {
                    // 4.1 Check required data : Territory End Date
                    if (S21StringUtil.isEmpty(rsWrk.getString(TRTY_END_DT_TXT))) {
                        String err = S21MessageFunc.clspGetMessage(NMAM8121E);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 4.2 Check exclusive date
                    Map<String, Object> ezuptimeMap = new HashMap<String, Object>();
                    ezuptimeMap.put("glblCmpyCd", glblCmpyCd);
                    if (userConstantVariable.equals(ONLINE)) {
                        ezuptimeMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        ezuptimeMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    ezuptimeMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                    String ezuptime = (String) this.ssmBatchClient.queryObject("getEzuptimeTrty", ezuptimeMap, execParam);

                    if (Long.valueOf(ezuptime) > Long.valueOf(reqEzuptime)) {
                        String[] msg = {"Current Resource#", "current resource#'s last update time is after request date time." };
                        String err = S21MessageFunc.clspGetMessage(NMAM8478E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 4.3 Error if Territory is inactive
                    Map<String, Object> effFromDtMap = new HashMap<String, Object>();
                    effFromDtMap.put("glblCmpyCd", glblCmpyCd);
                    if (userConstantVariable.equals(ONLINE)) {
                        effFromDtMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        effFromDtMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    effFromDtMap.put("slsDate", ZYPDateUtil.getSalesDate());
                    effFromDtMap.put("hitDate", "99991231");
                    effFromDtMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                    String effFromDt = (String) this.ssmBatchClient.queryObject("getEffFromDt", effFromDtMap, execParam);

                    if (S21StringUtil.isEmpty(effFromDt)) {
                        String[] msg = {"End Territory Date", "inactive Territory" };
                        String err = S21MessageFunc.clspGetMessage(NMAM0179E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 4.4 Check Validate date : Territory Date
                    if (Long.valueOf(trtyEndDtTxt) < Long.valueOf(effFromDt)) {
                        String[] msg = {"End Terriories Date", "Before Territory Effective Date From"};
                        String err = S21MessageFunc.clspGetMessage(NMAM8450E, msg);
                        isError = true;
                        TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                        msgLst.add(tMsg);
                        continue;
                    }

                    // 4.5 Check Validate date : Territory Resource Relation (DS_ORG_RESRC_RELN)
                    Map<String, Object> effFromDtRRMap = new HashMap<String, Object>();
                    effFromDtRRMap.put("glblCmpyCd", glblCmpyCd);
                    if (userConstantVariable.equals(ONLINE)) {
                        effFromDtRRMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        effFromDtRRMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    effFromDtRRMap.put("slsDate", ZYPDateUtil.getSalesDate());
                    effFromDtRRMap.put("hitDate", "99991231");
                    effFromDtRRMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                    List<String> gnrnTpCd = new ArrayList<String>();
                    gnrnTpCd.add(GNRN_TP.CURRENT);
                    gnrnTpCd.add(GNRN_TP.FUTURE);
                    effFromDtRRMap.put("gnrnTpCd", gnrnTpCd);
                    String effFromDtRR = (String) this.ssmBatchClient.queryObject("getEffFromDtRR", effFromDtRRMap, execParam);

                    if (hasValue(effFromDtRR)) {
                        if (Long.valueOf(trtyEndDtTxt) < Long.valueOf(effFromDtRR)) {
                            String[] msg = {"End Terriories Date", "Before Territory Resource Effective Date From"};
                            String err = S21MessageFunc.clspGetMessage(NMAM8450E, msg);
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }
                    }

                    // 4.6 Check Validate date : Territory Rule (TRTY_RULE)
                    Map<String, Object> effFromDtTRMap = new HashMap<String, Object>();
                    effFromDtTRMap.put("glblCmpyCd", glblCmpyCd);
                    if (userConstantVariable.equals(ONLINE)) {
                        effFromDtTRMap.put("orgCd", rsWrk.getString(TRTY_ORG_CD));
                    } else if (userConstantVariable.equals(UPLOAD)) {
                        effFromDtTRMap.put("orgNm", rsWrk.getString(TRTY_ORG_NM));
                    }
                    effFromDtTRMap.put("slsDate", ZYPDateUtil.getSalesDate());
                    effFromDtTRMap.put("hitDate", "99991231");
                    effFromDtTRMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
                    String effFromDtTR = (String) this.ssmBatchClient.queryObject("getEffFromDtTR", effFromDtTRMap, execParam);

                    if (hasValue(effFromDtTR)) {
                        if (Long.valueOf(trtyEndDtTxt) < Long.valueOf(effFromDtTR)) {
                            String[] msg = {"End Terriories Date", "Before Territory Rule Effective Date From"};
                            String err = S21MessageFunc.clspGetMessage(NMAM8450E, msg);
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }
                    }

                    // 2016/05/10 CSA-QC#8081 Add Start
                    // Check Validate date : Territory Relation (DS_ORG_RELN)
                    String effFromDtDsOrgReln = (String) this.ssmBatchClient.queryObject("getEffFromDtDsOrgReln", effFromDtRRMap, execParam);
                    if (hasValue(effFromDtDsOrgReln)) {
                        if (Long.valueOf(trtyEndDtTxt) < Long.valueOf(effFromDtDsOrgReln)) {
                            String[] msg = {"End Terriories Date", "Before Territory Relation Effective Date From"};
                            String err = S21MessageFunc.clspGetMessage(NMAM8450E, msg);
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }
                    }
                    // 2016/05/10 CSA-QC#8081 Add End
                }

                // 5.1 Register process
                // get Territory ID
                String orgCd = null;
                if (this.userConstantVariable.equals(ONLINE)) {
                    orgCd = rsWrk.getString(TRTY_ORG_CD);
                    // 2016/07/22 CSA-QC#11908 Add Start
                    trtyUpdRqstPkMap = new HashMap<String, Object>();
                    trtyUpdRqstPkMap.put("TRTY_UPD_RQST_HDR_PK", rsWrk.getBigDecimal("TRTY_UPD_RQST_HDR_PK"));
                    trtyUpdRqstPkMap.put("TRTY_UPD_RQST_DTL_PK", rsWrk.getBigDecimal("TRTY_UPD_RQST_DTL_PK"));
                    // 2016/07/22 CSA-QC#11908 Add End
                } else if (userConstantVariable.equals(UPLOAD)) {
                    DS_ORG_UNITTMsg inMsg =  new DS_ORG_UNITTMsg();
                    inMsg.setSQLID("001");
                    setValue(inMsg.glblCmpyCd, glblCmpyCd);
                    setValue(inMsg.orgNm, rsWrk.getString(TRTY_ORG_NM));
                    DS_ORG_UNITTMsgArray inMsgList = (DS_ORG_UNITTMsgArray) ZYPCodeDataUtil.findByCondition(inMsg);
                    if (inMsgList.length() == 0) {
                        isError = true;
                        continue;
                    }
                    orgCd = inMsgList.no(0).orgCd.getValue();
                }

                if (!isError) {
                    // MODE : Move Resource
                    if (TRTY_UPD_MODE_TP.MOVE_RESOURCE.equals(this.mode)) {

                        String curPsnCd = getS21Psn(rsWrk.getString(CUR_PSN_NUM), execParam);
                        Map<String, Object> insertMap = new HashMap<String, Object>();
                        insertMap.put("glblCmpyCd", glblCmpyCd);
                        insertMap.put("orgCd", orgCd);
                        insertMap.put("psnCd", curPsnCd);
                        Map<String, String> rsInfMap = (Map<String, String>) this.ssmBatchClient.queryObject("getDsOrgResrcReln", insertMap, execParam);

                        // Insert Territory / Move To Resource Relation
                        if (!insertDsOrgResrcReln(orgCd, trtyEndDtTxt, psnCd, moveEffFromDtTxt, moveEffThruDtTxt, rsInfMap)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM0176E, new String[]{"DS_ORG_RESRC_RELN"});
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }

                        // Update Territory / Move from Resource Relation
                        if (!updateDsOrgResrcRelnForMoveResource(orgCd, trtyEndDtTxt, curPsnCd, moveEffFromDtTxt, rsInfMap)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM0177E, new String[]{"DS_ORG_RESRC_RELN"});
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }

                    // MODE : End Territories
                    } else if (TRTY_UPD_MODE_TP.END_TERRITORIES.equals(this.mode)) {
                        // Update DS_ORG_RESRC_RELN
                        if (!updateDsOrgResrcRelnForEndTerritory(orgCd, trtyEndDtTxt)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM0177E, new String[]{"DS_ORG_RESRC_RELN"});
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }

                        // Update DS_ORG_UNIT
                        if (!updateDsOrgUnit(orgCd, trtyEndDtTxt)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM0177E, new String[]{"DS_ORG_UNIT"});
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }

                        // Update TRTY_RULE
                        if (!updateTrtyRule(orgCd, trtyEndDtTxt)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM0177E, new String[]{"TRTY_RULE"});
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }

                        // 2016/05/10 CSA-QC#8081 Add Start
                        // Update DS_ORG_RELN
                        if (!updateDsOrgReln(orgCd, trtyEndDtTxt)) {
                            String err = S21MessageFunc.clspGetMessage(NMAM0177E, new String[]{"DS_ORG_RELN"});
                            isError = true;
                            TRTY_UPD_RQST_DTLTMsg tMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, err);
                            msgLst.add(tMsg);
                            continue;
                        }
                        // 2016/05/10 CSA-QC#8081 Add End
                    }
                    // 2016/07/22 CSA-QC#11908 Add Start
                    if (this.userConstantVariable.equals(ONLINE)) {
                        if (!isError) {
                            trtyUpdRqstPkMap.put("IS_ERROR", ZYPConstant.FLG_OFF_0);
                        } else {
                            trtyUpdRqstPkMap.put("IS_ERROR", ZYPConstant.FLG_ON_1);
                        }
                        trtyUpdRqstHdrPkMapList.add(trtyUpdRqstPkMap);
                    }
                    // 2016/07/22 CSA-QC#11908 Add End
                }
            } while (rsWrk.next());

            if (!isError) {
                List<String> dtLst = new ArrayList<String>();
                rsWrk.last();

                dtLst.add(rsWrk.getString(MOVE_EFF_FROM_DT_TXT));
                dtLst.add(rsWrk.getString(MOVE_EFF_THRU_DT_TXT));
                dtLst.add(rsWrk.getString(TRTY_END_DT_TXT));

                TRTY_UPD_RQST_DTLTMsg updTrtyUpdRqstDtlTMsg = makeRqstMsgList(rsWrk, isError, hdrSq, dtLst, null);
                if (this.userConstantVariable.equals(ONLINE)) {
                    S21FastTBLAccessor.update(updTrtyUpdRqstDtlTMsg);
                } else if (this.userConstantVariable.equals(UPLOAD)) {
                    S21FastTBLAccessor.insert(updTrtyUpdRqstDtlTMsg);
                }

                commit();
                totalNmlCount++;
                // START 2016/06/20 [QC#8156,ADD]
                updateCount++;
                // END 2016/06/20 [QC#8156,ADD]
            } else {
                rollback();
                totalErrCount++;
                // START 2016/06/20 [QC#8156,ADD]
                errorCount++;
                // END 2016/06/20 [QC#8156,ADD]
            }

            // 2016/07/22 CSA-QC#11908 Add Start
            for (int index = 0; index < trtyUpdRqstHdrPkMapList.size(); index++) {
                Map<String, Object> hdrPkMap = trtyUpdRqstHdrPkMapList.get(index);
                TRTY_UPD_RQST_DTLTMsg updTrtyUpdRqstDtlTMsg = makeRqstMsgList(hdrPkMap);
                S21FastTBLAccessor.update(updTrtyUpdRqstDtlTMsg);
                commit();
                updateCount++;
            }
            // 2016/07/22 CSA-QC#11908 Add End

            // Update Error message
            for (int i = 0; i < msgLst.size(); i++) {
                if (this.userConstantVariable.equals(ONLINE)) {
                    S21FastTBLAccessor.update(msgLst.get(i));
                } else if (this.userConstantVariable.equals(UPLOAD)) {
                    S21FastTBLAccessor.insert(msgLst.get(i));
                }
            }

            // 2016/07/22 CSA-QC#11908 Add Start
            if (errorInfo > 0) {
                isError = true;
            }
            // 2016/07/22 CSA-QC#11908 Add End
            // Update request result to TRTY_UPD_RQST_HDR
            registRequestResultToHeader(nextHdrPk, hdrSq, upldCsvId, rqstCmntTxt, insertCount, updateCount, errorCount, isError);
            if (!isError) {
                if (this.userConstantVariable.equals(UPLOAD)) {
                    batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                    // START 2016/06/20 [QC#8156,ADD]
                    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(insertCount, updateCount, errorCount));
                    this.messenger.uploadMessage();
                    // END 2016/06/20 [QC#8156,ADD]
                }
            } else {
                if (this.userConstantVariable.equals(UPLOAD)) {
                    batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                    // START 2016/06/20 [QC#8156,ADD]
                    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(insertCount, updateCount, errorCount));
                    this.messenger.uploadMessage();
                    // END 2016/06/20 [QC#8156,ADD]
                }
            }

            commit();

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(psWrk, rsWrk);
        }
    }

    /**
     * registRequestResult
     */
    private void registRequestResultToHeader(BigDecimal hdrPk, BigDecimal hdrSq, String upldCsvId, String rqstCmntTxt, int insertCount, int updateCount, int errorCount, boolean isError) {
        String msg = null;
        // TRTY_UPD_RQST_HDR
        if (userConstantVariable.equals(ONLINE)) {
            TRTY_UPD_RQST_HDRTMsg keyTurhTMsg = new TRTY_UPD_RQST_HDRTMsg();
            setValue(keyTurhTMsg.glblCmpyCd, glblCmpyCd);
            setValue(keyTurhTMsg.trtyUpdRqstHdrPk, hdrPk);
            TRTY_UPD_RQST_HDRTMsg turhTMsg = (TRTY_UPD_RQST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(keyTurhTMsg);
            if (!isError) {
                setValue(turhTMsg.rqstRsltTpCd, COMPLETED);
                // 2016/09/23 CSA-QC#8156 Add Start
                msg = createResultMessageArg(0, 1, 0);
                setValue(turhTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0001I, msg));
                // 2016/09/23 CSA-QC#8156 Add Start
            } else {
                setValue(turhTMsg.rqstRsltTpCd, INCLUDING_ERROR);
                // 2016/09/23 CSA-QC#8156 Add Start
                msg = createResultMessageArg(0, 0, 1);
                setValue(turhTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0002E, msg));
                // 2016/09/23 CSA-QC#8156 Add End
            }
            EZDTBLAccessor.update(turhTMsg);
        } else if (userConstantVariable.equals(UPLOAD)) {
            TRTY_UPD_RQST_HDRTMsg trtyUpdRqstHdrTMsg = new TRTY_UPD_RQST_HDRTMsg();
            setValue(trtyUpdRqstHdrTMsg.glblCmpyCd, glblCmpyCd);
            setValue(trtyUpdRqstHdrTMsg.trtyUpdRqstHdrPk, hdrSq);
            setValue(trtyUpdRqstHdrTMsg.rqstUsrId, upldCsvId);
            setValue(trtyUpdRqstHdrTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
            setValue(trtyUpdRqstHdrTMsg.rqstCratSysTpCd, UPLOAD);
            setValue(trtyUpdRqstHdrTMsg.rqstRsltTpCd, SUBMITTED);

            msg =  createResultMessageArg(insertCount, updateCount, errorCount);
            if (!isError) {
                setValue(trtyUpdRqstHdrTMsg.rqstRsltTpCd, COMPLETED);
                // 2016/09/23 CSA-QC#8156 Add Start
                setValue(trtyUpdRqstHdrTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0001I, msg));
                // 2016/09/23 CSA-QC#8156 Add end
            } else {
                setValue(trtyUpdRqstHdrTMsg.rqstRsltTpCd, INCLUDING_ERROR);
                // 2016/09/23 CSA-QC#8156 Mod Start
                setValue(trtyUpdRqstHdrTMsg.rqstRsltCmntTxt, createResultMsg(NYXM0002E, msg));
                // setValue(trtyUpdRqstHdrTMsg.rqstRsltCmntTxt, rqstCmntTxt);
                // 2016/09/23 CSA-QC#8156 Mod Start
                this.messenger.uploadMessage();
            }
            EZDTBLAccessor.insert(trtyUpdRqstHdrTMsg);
        }
    }

    private static String createResultMsg(String msgId, String msgTxt) {
        StringBuilder sb = new StringBuilder();

        sb.append(msgId);
        // mod start 2016/10/19 CSA QC#8156
        sb.append(" ");
        // mod end 2016/10/19 CSA QC#8156
        sb.append(msgTxt);

        return sb.toString();
    }

    /**
     * @return boolean
     */
    private boolean insertDsOrgResrcReln(String orgCd, String trtyEndDtTxt, String psnCd, String moveEffFromDtTxt, String moveEffThruDtTxt, Map<String, String> rsInfMap) {
        DS_ORG_RESRC_RELNTMsg dorrTMsg = new DS_ORG_RESRC_RELNTMsg();
        setValue(dorrTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dorrTMsg.orgCd, orgCd);
        setValue(dorrTMsg.psnCd, psnCd);
        setValue(dorrTMsg.effFromDt, moveEffFromDtTxt);
        if (!moveEffThruDtTxt.equals("99991231")) {
            setValue(dorrTMsg.effThruDt, moveEffThruDtTxt);
        }
        setValue(dorrTMsg.orgStruTpCd, rsInfMap.get("ORG_STRU_TP_CD"));
        setValue(dorrTMsg.coaCcCd, rsInfMap.get("COA_CC_CD"));
        setValue(dorrTMsg.coaBrCd, rsInfMap.get("COA_BR_CD"));
        setValue(dorrTMsg.gnrnTpCd, GNRN_TP.FUTURE); // 2016/04/10 CSA-QC#8077 Mod
        setValue(dorrTMsg.nonSlsRepFlg, rsInfMap.get("NON_SLS_REP_FLG"));
        setValue(dorrTMsg.orgFuncRoleTpCd, rsInfMap.get("ORG_FUNC_ROLE_TP_CD"));
        setValue(dorrTMsg.acctTeamRoleTpCd, rsInfMap.get("ACCT_TEAM_ROLE_TP_CD"));
        S21FastTBLAccessor.insert(dorrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dorrTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * @return boolean
     */
    private boolean updateDsOrgResrcRelnForMoveResource(String orgCd, String trtyEndDtTxt, String curPsnCd, String moveEffFromDtTxt, Map<String, String> rsInfMap) {
        DS_ORG_RESRC_RELNTMsg updtRelnTMsg = new DS_ORG_RESRC_RELNTMsg();
        setValue(updtRelnTMsg.glblCmpyCd, glblCmpyCd);
        setValue(updtRelnTMsg.orgCd, orgCd);
        setValue(updtRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        setValue(updtRelnTMsg.psnCd, curPsnCd);
        setValue(updtRelnTMsg.orgFuncRoleTpCd, rsInfMap.get("ORG_FUNC_ROLE_TP_CD"));
        setValue(updtRelnTMsg.effFromDt, rsInfMap.get("EFF_FROM_DT"));

        DS_ORG_RESRC_RELNTMsg inMsg = (DS_ORG_RESRC_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updtRelnTMsg);
        if (inMsg == null) {
            return false;
        } else {
            setValue(inMsg.nonSlsRepFlg, (String) rsInfMap.get("NON_SLS_REP_FLG"));
            setValue(inMsg.effThruDt, ZYPDateUtil.addDays(moveEffFromDtTxt, -1));
            EZDTBLAccessor.update(inMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }


    /**
     * @return boolean
     */
    private boolean updateDsOrgResrcRelnForEndTerritory(String orgCd, String trtyEndDtTxt) {
        List<DS_ORG_RESRC_RELNTMsg> updTMsgList = new ArrayList<DS_ORG_RESRC_RELNTMsg>();

        DS_ORG_RESRC_RELNTMsg keyDsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();
        keyDsOrgResrcRelnTMsg.setSQLID("002");
        keyDsOrgResrcRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        keyDsOrgResrcRelnTMsg.setConditionValue("orgCd01", orgCd);
        keyDsOrgResrcRelnTMsg.setConditionValue("orgStruTpCd01", ORG_STRU_TP.TERRITORY_STRUCTURE);
        keyDsOrgResrcRelnTMsg.setConditionValue("gnrnTpCd01", GNRN_TP.CURRENT);
        keyDsOrgResrcRelnTMsg.setConditionValue("gnrnTpCd02", GNRN_TP.FUTURE);

        DS_ORG_RESRC_RELNTMsgArray inMsgArry = (DS_ORG_RESRC_RELNTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(keyDsOrgResrcRelnTMsg);
        if (inMsgArry != null && 0 < inMsgArry.getValidCount()) {
            for (int i = 0; i < inMsgArry.getValidCount(); i++) {
                setValue(inMsgArry.no(i).effThruDt, trtyEndDtTxt);
                updTMsgList.add(inMsgArry.no(i));
            }

            // update
            if (!updTMsgList.isEmpty()) {
                int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_ORG_RESRC_RELNTMsg[0]));
                if (updCnt != updTMsgList.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * updateDsOrgUnit
     * @return boolean
     */
    private boolean updateDsOrgUnit(String orgCd, String trtyEndDtTxt) {
    // Update DS_ORG_UNIT
        DS_ORG_UNITTMsg douTMsg = new DS_ORG_UNITTMsg();
        setValue(douTMsg.glblCmpyCd, glblCmpyCd);
        setValue(douTMsg.orgCd, orgCd);
        DS_ORG_UNITTMsg inMsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKeyForUpdateWait(douTMsg);
        if (inMsg == null) {
            return false;
        } else {
            setValue(inMsg.effThruDt, trtyEndDtTxt);
            EZDTBLAccessor.update(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * updateTrtyRule
     * @return boolean
     */
    private boolean updateTrtyRule(String orgCd, String trtyEndDtTxt) {
        List<TRTY_RULETMsg> updTMsgList = new ArrayList<TRTY_RULETMsg>();

        TRTY_RULETMsg keyTrtyRuleTMsg = new TRTY_RULETMsg();
        keyTrtyRuleTMsg.setSQLID("001");
        keyTrtyRuleTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        keyTrtyRuleTMsg.setConditionValue("orgCd01", orgCd);
        keyTrtyRuleTMsg.setConditionValue("orgStruTpCd01", ORG_STRU_TP.TERRITORY_STRUCTURE);
        keyTrtyRuleTMsg.setConditionValue("effFromDt01", procDt);
        keyTrtyRuleTMsg.setConditionValue("effThruDt01", procDt);

        TRTY_RULETMsgArray inMsgArry = (TRTY_RULETMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(keyTrtyRuleTMsg);
        if (inMsgArry != null && 0 < inMsgArry.getValidCount()) {
            for (int i = 0; i < inMsgArry.getValidCount(); i++) {
                setValue(inMsgArry.no(i).effThruDt, trtyEndDtTxt);
                updTMsgList.add(inMsgArry.no(i));
            }

            // update
            if (!updTMsgList.isEmpty()) {
                int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new TRTY_RULETMsg[0]));
                if (updCnt != updTMsgList.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * updateDsOrgReln
     * @return boolean
     */
    private boolean updateDsOrgReln(String orgCd, String trtyEndDtTxt) {
        List<DS_ORG_RELNTMsg> updTMsgList = new ArrayList<DS_ORG_RELNTMsg>();

        DS_ORG_RELNTMsg keyDsOrgRelnTMsg = new DS_ORG_RELNTMsg();
        keyDsOrgRelnTMsg.setSQLID("001");
        keyDsOrgRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        keyDsOrgRelnTMsg.setConditionValue("orgCd01", orgCd);
        keyDsOrgRelnTMsg.setConditionValue("orgStruTpCd01", ORG_STRU_TP.TERRITORY_STRUCTURE);
        keyDsOrgRelnTMsg.setConditionValue("gnrnTpCd01", GNRN_TP.CURRENT);
        keyDsOrgRelnTMsg.setConditionValue("gnrnTpCd02", GNRN_TP.FUTURE);

        DS_ORG_RELNTMsgArray inMsgArry = (DS_ORG_RELNTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(keyDsOrgRelnTMsg);
        if (inMsgArry != null && 0 < inMsgArry.getValidCount()) {
            for (int i = 0; i < inMsgArry.getValidCount(); i++) {
                setValue(inMsgArry.no(i).effThruDt, trtyEndDtTxt);
                updTMsgList.add(inMsgArry.no(i));
            }

            // update
            if (!updTMsgList.isEmpty()) {
                int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new DS_ORG_RELNTMsg[0]));
                if (updCnt != updTMsgList.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    // 2016/07/22 CSA-QC#11908 Add Start
    private TRTY_UPD_RQST_DTLTMsg makeRqstMsgList(Map<String, Object> hdrPkMap) {

        TRTY_UPD_RQST_DTLTMsg updTrtyUpdRqstDtlTMsg = new TRTY_UPD_RQST_DTLTMsg();
        setValue(updTrtyUpdRqstDtlTMsg.glblCmpyCd, glblCmpyCd);
        setValue(updTrtyUpdRqstDtlTMsg.trtyUpdRqstHdrPk, (BigDecimal) hdrPkMap.get("TRTY_UPD_RQST_HDR_PK"));
        setValue(updTrtyUpdRqstDtlTMsg.trtyUpdRqstDtlPk, (BigDecimal) hdrPkMap.get("TRTY_UPD_RQST_DTL_PK"));
        updTrtyUpdRqstDtlTMsg = (TRTY_UPD_RQST_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(updTrtyUpdRqstDtlTMsg);
        String errFlg = (String)hdrPkMap.get("IS_ERROR");
        if (ZYPConstant.FLG_OFF_0.equals(errFlg)) {
            setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltTpCd, COMPLETED);
        } else {
            setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltTpCd, INCLUDING_ERROR);
        }
        return updTrtyUpdRqstDtlTMsg;
    }
    // 2016/07/22 CSA-QC#11908 Add End

    /**
     * @return TRTY_UPD_RQST_DTLTMsg
     */
    private TRTY_UPD_RQST_DTLTMsg makeRqstMsgList(ResultSet rsWrk, boolean errFlg, BigDecimal hdrSq, List<String> dtLst, String err) throws SQLException {
        TRTY_UPD_RQST_DTLTMsg updTrtyUpdRqstDtlTMsg = null;

        // ONLINE MODE : Update TRTY_UPD_RQST_DTL
        if (userConstantVariable.equals(ONLINE)) {
            TRTY_UPD_RQST_DTLTMsg keyTrtyUpdRqstDtltMsg = new TRTY_UPD_RQST_DTLTMsg();

            setValue(keyTrtyUpdRqstDtltMsg.glblCmpyCd, glblCmpyCd);
            setValue(keyTrtyUpdRqstDtltMsg.trtyUpdRqstHdrPk, rsWrk.getBigDecimal("TRTY_UPD_RQST_HDR_PK"));
            setValue(keyTrtyUpdRqstDtltMsg.trtyUpdRqstDtlPk, rsWrk.getBigDecimal("TRTY_UPD_RQST_DTL_PK"));
            updTrtyUpdRqstDtlTMsg = (TRTY_UPD_RQST_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(keyTrtyUpdRqstDtltMsg);
            if (!errFlg) {
                setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltTpCd, COMPLETED);
            } else {
                setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltTpCd, INCLUDING_ERROR);
                setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, err);
            }

        // ONLINE MODE : Insert TRTY_UPD_RQST_DTL
        } else if (userConstantVariable.equals(UPLOAD)) {
            updTrtyUpdRqstDtlTMsg = new TRTY_UPD_RQST_DTLTMsg();

            BigDecimal dtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_UPD_RQST_DTL_SQ);

            setValue(updTrtyUpdRqstDtlTMsg.glblCmpyCd, glblCmpyCd);
            setValue(updTrtyUpdRqstDtlTMsg.trtyUpdRqstDtlPk, dtlSq);
            setValue(updTrtyUpdRqstDtlTMsg.trtyUpdRqstHdrPk, hdrSq);
            setValue(updTrtyUpdRqstDtlTMsg.trtyOrgCd, rsWrk.getString(TRTY_ORG_CD));
            setValue(updTrtyUpdRqstDtlTMsg.curPsnNum, rsWrk.getString(CUR_PSN_NUM));
            setValue(updTrtyUpdRqstDtlTMsg.movePsnNum, rsWrk.getString(MOVE_PSN_NUM));
            setValue(updTrtyUpdRqstDtlTMsg.moveEffFromDt, dtLst.get(0));
            setValue(updTrtyUpdRqstDtlTMsg.moveEffThruDt, dtLst.get(1));
            setValue(updTrtyUpdRqstDtlTMsg.trtyEndDt, dtLst.get(2));
            if (!errFlg) {
                setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltTpCd, COMPLETED);
            } else {
                setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltTpCd, INCLUDING_ERROR);
                setValue(updTrtyUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, err);
            }
        }

        return updTrtyUpdRqstDtlTMsg;
    }

    private TRTY_UPD_WRKTMsg getTrtyUpdWrk(BigDecimal searchKey, BigDecimal searchKey2) {
        TRTY_UPD_WRKTMsg inMsg = new TRTY_UPD_WRKTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.upldCsvRqstPk, searchKey);
        setValue(inMsg.upldCsvRqstRowNum, searchKey2);
        inMsg = (TRTY_UPD_WRKTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    private TRTY_UPD_RQST_HDRTMsg getTrtyUpdRqstHdr(BigDecimal searchKey) {
        TRTY_UPD_RQST_HDRTMsg inMsg = new TRTY_UPD_RQST_HDRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.trtyUpdRqstHdrPk, searchKey);
        inMsg = (TRTY_UPD_RQST_HDRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    private TRTY_UPD_MODE_TPTMsg getTpCd(String searchKey) {
        TRTY_UPD_MODE_TPTMsg inMsg = new TRTY_UPD_MODE_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.trtyUpdModeTpCd, searchKey);
        inMsg = (TRTY_UPD_MODE_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    private String getS21Psn(String searchKey, S21SsmExecutionParameter execParam) {
        Map<String, Object> s21Map = new HashMap<String, Object>();
        s21Map.put("glblCmpyCd", glblCmpyCd);
        s21Map.put("psnNum", searchKey);
        String psnCd = (String) this.ssmBatchClient.queryObject("getPsnCd", s21Map, execParam);

        return psnCd;
    }

    private String dateFormat(String date) {
        String dateFormat = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date dt = format.parse(date);
            format.applyPattern("yyyyMMdd");
            dateFormat = format.format(dt);
        } catch (java.text.ParseException e) {
            return null;
        }
        return dateFormat;
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (S21StringUtil.isEmpty(uploadCsvId)) {
            String[] msg = {MSG_UPLD_CSV_ID };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NMAM0071E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @return GlobalCompanyCode
     */
    private String getGlblCmpyCd() {

        String cmpyCd = this.profile.getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(cmpyCd)) {
            String[] msg = {GLOBAL_COMPANY_CODE };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return cmpyCd;
    }

    // START 2016/06/20 [QC#8156,ADD]
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
    // END 2016/06/20 [QC#8156,ADD]
}
