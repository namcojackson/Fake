/*
 * Copyright (c) 2016 Canon USA Inc. All rights reserved. Original
 */
package com.canon.cusa.s21.batch.NMA.NMAB269001;

import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.APP_FUNC_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.CLSS_FUNC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.COA_CH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.DATE_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.INFO_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.MSG_LOCATION;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.MSG_PAREN_END;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.MSG_PAREN_START;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM0050E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM8411E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM8412E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM8567E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NMAM8568E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.PROCESS_MODE_CUST_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.PROCESS_MODE_PROS_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.S21_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.S21_LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.SALE_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.SET_DATE_TYPE;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.SET_TIME_TYPE;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.UPDATE_COUNT_CONFIGRATION;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.UPLOAD_DUNS_FILE;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.UPLOAD_REVIEW_DATA;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.VAR_CHAR_CONST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.ZYPM0181E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB269001.constant.NMAB269001Constant.ZZZM9026E;
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
import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DUNS_RVW_INFOTMsg;
import business.db.DUNS_TRX_FILETMsg;
import business.db.DUNS_TRX_HDRTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
* Duns Upload Batch
*
* Date           Company    Name         Create/Update     Defect No
* ----------------------------------------------------------------------
* 2016/06/03     Fujitsu    R.Nakamura   Create            N/A
* 2016/06/17     Fujitsu    R.Nakamura   Update            QC#10340
* 2016/06/22     Fujitsu    R.Nakamura   Update            QC#10340
* 2016/06/23     Fujitsu    R.Nakamura   Update            QC#10340
* 2016/06/24     Fujitsu    R.Nakamura   Update            QC#10340
* 2016/06/24     Fujitsu    H.Ikeda      Update            QC#10816
* 2016/06/24     Fujitsu    R.Nakamura   Update            QC#10905
* 2016/07/01     Fujitsu    M.Ohno       Update            QC#11147
* 2016/07/21     Fujitsu    R.Nakamura   Update            QC#9955
* 2016/09/21     Fujitsu    C.Yokoi      Update            QC#14694
* 2016/10/13     Fujitsu    R.Nakamura   Update            QC#10696
* 2017/09/04     Fujitsu    W.Honda      Update            QC#20920
* 2017/09/05     Fujitsu    R.Nakamura   Update            QC#20907
*</pre>
 */
public class NMAB269001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** sale date time */
    private String saleDt = null;

    /** class Function Code */
    private String clssFuncCode = null;

    /** Coa Ch Cd */
    private String coaChCd = null;

    /** Coa Affl Cd */
    private String coaAfflCd = null;

    /** end Code */
    private TERM_CD termCd = null;

    /** SeqNum DUNS_TRX_FILE */
    private BigDecimal seqDRI = null;

    /** SeqNum DUNS_TRX_HDR */
    private BigDecimal seqDTH = null;

    /** SeqNum DUNS_TRX_FILE */
    private BigDecimal seqDTF = null;

    /** S21RequestBatchHelper */
    private S21RequestBatchHelper reqbatch = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** Error Code */
    private String errCode = null;

    /** Counter of Records: Successful */
    private int normalRecCount = 0;

    /** Counter of Records: Error */
    private int errRecCount = 0;

    /** Error Flag */
    private boolean errFlg = false;

    /** Max Upload Count */
    private int maxUploadCnt = 0;

    @Override
    protected void initRoutine() {

        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        checkDataValue(this.glblCmpyCd, GLBL_CMPY_CD);

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (null == glblCmpyTMsg) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9026E, new String[] {GLBL_CMPY_CD });
        }

        this.saleDt = ZYPDateUtil.getSalesDate();
        checkDataValue(this.saleDt, SALE_DATE);

        this.clssFuncCode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM, this.glblCmpyCd);
        checkDataValue(this.clssFuncCode, CLSS_FUNC_CD);

        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(APP_FUNC_ID);
        this.coaChCd = tMsg.coaChCd.getValue();
        checkDataValue(this.coaChCd, COA_CH_CD);
        this.coaAfflCd = tMsg.coaAfflCd.getValue();
        checkDataValue(this.coaAfflCd, COA_AFFL_CD);

        // Add Start 2016/06/24 QC#10340
        this.maxUploadCnt = getMaxUpdateCount();
        // Add End 2016/06/24 QC#10340

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.reqbatch = new S21RequestBatchHelper();
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

        setTermState(this.termCd, this.normalRecCount, this.errRecCount, this.normalRecCount + this.errRecCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB269001().executeBatch(NMAB269001.class.getSimpleName());
    }

    /**
     * checkDataValue
     * @param dataValue String
     */
    private void checkDataValue(String dataValue, String errWord) {
        if (!ZYPCommonFunc.hasValue(dataValue)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9025E, new String[] {errWord });
        }
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    private DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String appFuncId) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg fMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        DUNS_RVW_INFOTMsg driTMsg = null;
        DUNS_TRX_HDRTMsg dthTmsg = null;
        DUNS_TRX_FILETMsg dtfTmsg = null;

        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

        BigDecimal upldCsvRqstPk = getUploadRequestPK(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);
        this.termCd = TERM_CD.NORMAL_END;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            inWrkMap.put("glblCmpyCd", this.glblCmpyCd);
            inWrkMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);
            inWrkMap.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
            inWrkMap.put("rgtnStsCdP99", RGTN_STS.TERMINATED);
            inWrkMap.put("typePros", PROCESS_MODE_PROS_UPD);
            inWrkMap.put("typeCust", PROCESS_MODE_CUST_UPD);

            List<String> checkValue = new ArrayList<String>();

            stmt = ssmLLClient.createPreparedStatement("getDunsInfoWrk", inWrkMap, execParam);
            rs = stmt.executeQuery();

            String userId = fMsg.EZREQRegisteredUserID.getValue();
            BigDecimal idx;
            int nomalCnt = 0;
            int errCnt = 0;
            int cnt = 0;
            boolean totalErrFlg = false;

            while (rs.next()) {

                this.errFlg = false;
                this.errCode = null;
                idx = rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");
                cnt++;
                // Mod Start 2016/06/24 QC#10340
                if (cnt > this.maxUploadCnt) {
                    this.messenger.addMessageForRecord(idx, NMAM0050E, String.valueOf(this.maxUploadCnt));
                    this.errFlg = true;
                    break;
                }
                // Mod End 2016/06/24 QC#10340

                String acctNum = rs.getString("DS_ACCT_NUM");
                String locNum = rs.getString("LOC_NUM");
                String origAcctNum = rs.getString("ORG_DS_ACCT_NUM");
                String origLocNum = rs.getString("ORG_LOC_NUM");
                if (!ZYPCommonFunc.hasValue(acctNum)) {
                    this.messenger.addMessageForRecord(idx, NMAM8228E, S21_ACCT_NUM);
                    setErrCode(NMAM8228E);
                    this.errFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(locNum)) {
                    this.messenger.addMessageForRecord(idx, NMAM8228E, S21_LOC_NUM);
                    setErrCode(NMAM8228E);
                    this.errFlg = true;
                }
                // Mod Start 2016/06/22 QC#10340
//                if (null != rs.getDate("CUR_DS_LAST_UPD_DUNS_DT") && //
//                        checkDateValue(rs.getDate("CUR_DS_LAST_UPD_DUNS_DT").toString())) {
                if (checkDateValue(rs.getString("CUR_DS_LAST_UPD_DUNS_DT"))) {
                    // this.messenger.addMessageForRecord(idx, NMAM8567E, rs.getDate("CUR_DS_LAST_UPD_DUNS_DT").toString());
                    this.messenger.addMessageForRecord(idx, NMAM8567E, rs.getString("CUR_DS_LAST_UPD_DUNS_DT"));
                    setErrCode(NMAM8567E);
                    this.errFlg = true;
                }
                if (checkDateValue(rs.getString("CUR_DUNS_PROC_DT"))) {
                    this.messenger.addMessageForRecord(idx, NMAM8567E, rs.getString("CUR_DUNS_PROC_DT"));
                    setErrCode(NMAM8567E);
                    this.errFlg = true;
                }
                // Mod End 2016/06/22 QC#10340
                if (!ZYPCommonFunc.hasValue(origAcctNum)) {
                    this.messenger.addMessageForRecord(idx, NMAM8411E, acctNum);
                    setErrCode(NMAM8411E);
                    this.errFlg = true;
                }

                if (!ZYPCommonFunc.hasValue(origLocNum)) {
                    this.messenger.addMessageForRecord(idx, NMAM8121E, getMessageLocation(locNum));
                    // this.messenger.addMessageForRecord(idx, NMAM8412E, origAcctNum);
                    setErrCode(NMAM8412E);
                    this.errFlg = true;
                }

                String getType = rs.getString("DS_ACCT_TYPE");
                String setValue = acctNum + "," + locNum;

                if (!checkOrverlapValue(setValue, checkValue, idx)) {
                    checkValue.add(setValue);
                } else {
                    this.errFlg = true;
                }

                if (!this.errFlg) {
                    NMZC001001PMsg pMsg = setUpdateAPI(getType, rs);
                    if (callApiNMZC001001(pMsg, idx)) {
                        rollback();
                        this.errFlg = true;
                    }
                }

                commit();

                // Mod Start 2017/09/05 QC#20907
//                dthTmsg = setDunsTrxHdr(userId);
//                setInsertData(dthTmsg);
//                dtfTmsg = setDunsTrxFile();
//                setInsertData(dtfTmsg);
//                driTMsg = setDunsRvwInfo();
//                setInsertData(driTMsg);
                if (cnt == 1) {
                    driTMsg = setDunsRvwInfo(rs);
                }
//                commit();
                // Mod End 2017/09/05 QC#20907

                // Add Start 2016/07/21 QC#9955
                if (this.errFlg) {
                    errCnt++;
                    totalErrFlg = true;
                } else {
                    nomalCnt++;
                }
                // Add End 2016/07/21 QC#9955

                // Del Start 2017/09/05 QC#20907
                // Mod Start 2016/07/21 QC#9955
//                if (totalErrFlg) {
//                    if (!TERM_CD.ABNORMAL_END.equals(this.termCd)) {
//                        this.termCd = TERM_CD.WARNING_END;
//                    }
//                } else {
//                    this.termCd = TERM_CD.NORMAL_END;
//                }
                // Mod End 2016/07/21 QC#9955
                // Del End 2017/09/05 QC#20907

                // Del Start 2016/06/22 QC#10340
//                commit();
                // Del End 2016/06/22 QC#10340
            }

            // Add Start 2017/09/05 QC#20907
            this.errFlg = false;
            dthTmsg = setDunsTrxHdr(userId);
            setInsertData(dthTmsg);
            dtfTmsg = setDunsTrxFile(cnt);
            setInsertData(dtfTmsg);
            setValue(driTMsg.dunsTrxHdrPk, this.seqDTH);
            setInsertData(driTMsg);
            commit();

            if (this.errFlg) {
                errCnt++;
                totalErrFlg = true;
            }
            if (totalErrFlg) {
                if (!TERM_CD.ABNORMAL_END.equals(this.termCd)) {
                    this.termCd = TERM_CD.WARNING_END;
                }
            } else {
                this.termCd = TERM_CD.NORMAL_END;
            }
            // Add End 2017/09/05 QC#20907

            String msgValue = null;
            this.normalRecCount += nomalCnt;
            this.errRecCount += errCnt;
            // Mod Start 2016/07/21 QC#9955
            if (totalErrFlg) {
                msgValue = this.normalRecCount + INFO_MSG + " " + this.errRecCount + ERR_MSG;
                this.messenger.addMessageForFile(NYXM0002E, msgValue);
                // Add Start 2016/06/24 QC#10340
                this.messenger.uploadMessage();
                // Add End 2016/06/24 QC#10340
                // Add Start 2016/06/22 QC#10340
                this.reqbatch.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                // Add End 2016/06/22 QC#10340
            } else {
                msgValue = this.normalRecCount + INFO_MSG;
                this.messenger.addMessageForFile(NYXM0001I, msgValue);
                // Add Start 2016/06/24 QC#10340
                this.messenger.uploadMessage();
                // Add End 2016/06/24 QC#10340
                // Add Start 2016/06/22 QC#10340
                this.reqbatch.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                // Add End 2016/06/22 QC#10340
            }
            // Mod End 2016/07/21 QC#9955
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);

        }
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private BigDecimal getUploadRequestPK(ART10FMsg reqMsg) {

        String upldCsvReqPk = reqMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }

        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(ZZZM9026E, msg);
        }

        return new BigDecimal(removeDQRequestPK);
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
     * getUpldCSVRqst
     * @param upldCsvReqPk upload CSV Request Pk
     * @return UPLD_CSV_RQSTTMsg
     */
    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {

        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        // Mod Start 2016/07/21 QC#9955
//        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        // Mod End 2016/07/21 QC#9955
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }

        return outTMsg;
    }

    /**
     * checkDateValue
     * @param getDate String
     * @return errReturnFlg
     */
    private boolean checkDateValue(String getDate) {

        // Del Start 2016/07/21 QC#9955
//        boolean errReturnFlg = false;
        // Del End 2016/07/21 QC#9955

        if (!ZYPCommonFunc.hasValue(getDate)) {
            // Mod Start 2016/07/21 QC#9955
//            errReturnFlg = true;
//            return errReturnFlg;
            return false;
            // Mod End 2016/07/21 QC#9955
        }

        if (!ZYPDateUtil.isValidDate(getDate, SET_DATE_TYPE)) {
            // Mod Start 2016/07/21 QC#9955
//            errReturnFlg = true;
            return true;
            // Mod End 2016/07/21 QC#9955
        }

        // Mod Start 2016/07/21 QC#9955
//        return errReturnFlg;
        return false;
        // Mod Start 2016/07/21 QC#9955
    }

    /**
     * checkOrverlapValue
     * @param setValue String[]
     * @param checkValue String[]
     * @param idx BigDecimal
     * @return errReturnFlg
     */
    private boolean checkOrverlapValue(String setValue, List<String> checkValue, BigDecimal idx) {

        boolean errReturnFlg = false;

        for (String getValue : checkValue) {
            if (getValue.equals(setValue)) {
                this.messenger.addMessageForRecord(idx, NMAM8568E, null);
                setErrCode(NMAM8568E);
                errReturnFlg = true;
                break;
            }
        }

        return errReturnFlg;
    }

    /**
     * setResultParam
     * @param rs ResultSet
     * @return DUNS_RVW_INFOTMsg
     */
    private DUNS_RVW_INFOTMsg setDunsRvwInfo(ResultSet rs) throws SQLException {

        this.seqDRI = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_RCV_INFO_SQ);
        DUNS_RVW_INFOTMsg driTMsg = new DUNS_RVW_INFOTMsg();

        setValue(driTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(driTMsg.dunsRvwInfoPk, this.seqDRI);
        setValue(driTMsg.dunsTrxHdrPk, this.seqDTH);
        setValue(driTMsg.dunsFileTpCd, UPLOAD_REVIEW_DATA);
        setValue(driTMsg.upldCsvRqstRowNum, rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        setValue(driTMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
        setValue(driTMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
        setValue(driTMsg.locNum, rs.getString("LOC_NUM"));
        setValue(driTMsg.curFirstLineAddr, rs.getString("CUR_FIRST_LINE_ADDR"));
        setValue(driTMsg.curScdLineAddr, rs.getString("CUR_SCD_LINE_ADDR"));
        setValue(driTMsg.curCtyAddr, rs.getString("CUR_CTY_ADDR"));
        setValue(driTMsg.curStCd, rs.getString("CUR_ST_CD"));
        setValue(driTMsg.curPostCd, rs.getString("CUR_POST_CD"));
        setValue(driTMsg.curDsLocEmpNum, rs.getBigDecimal("CUR_DS_LOC_EMP_NUM"));
        setValue(driTMsg.curDsLocRevAmt, rs.getBigDecimal("CUR_DS_LOC_REV_AMT"));
        setValue(driTMsg.curDsCustSicDescTxt, rs.getString("CUR_DS_CUST_SIC_DESC_TXT"));
        setValue(driTMsg.curDsCustSicCd, rs.getString("CUR_DS_CUST_SIC_CD"));
        setValue(driTMsg.curDunsNum, rs.getString("CUR_DUNS_NUM"));
        setValue(driTMsg.curDsUltDunsNum, rs.getString("CUR_DS_ULT_DUNS_NUM"));
        setValue(driTMsg.curHqDunsNum, rs.getString("CUR_HQ_DUNS_NUM"));
        setValue(driTMsg.curDsPrntDunsNum, rs.getString("CUR_DS_PRNT_DUNS_NUM"));
        // Mod Start 2016/06/23 QC#10340
         setValue(driTMsg.curDunsProcDtTxt, rs.getString("CUR_DUNS_PROC_DT"));
//        setValue(driTMsg.curDunsProcDt, formatDate(rs.getString("CUR_DUNS_PROC_DT")));
        // Mod End 2016/06/23 QC#103400
        setValue(driTMsg.curDunsBizNm, rs.getString("CUR_DUNS_BIZ_NM"));
        setValue(driTMsg.curDunsTradeStyleNm, rs.getString("CUR_DUNS_TRADE_STYLE_NM"));
        setValue(driTMsg.curDunsLineAddr, rs.getString("CUR_DUNS_LINE_ADDR"));
        setValue(driTMsg.curDunsActvCd, rs.getString("CUR_DUNS_ACTV_CD"));
        setValue(driTMsg.curDsLastUpdDunsDtTxt, rs.getString("CUR_DS_LAST_UPD_DUNS_DT"));
        setValue(driTMsg.curDsCmpySicCd, rs.getString("CUR_DS_CMPY_SIC_CD"));
        setValue(driTMsg.curDsCmpySicDescTxt, rs.getString("CUR_DS_CMPY_SIC_DESC_TXT"));
        setValue(driTMsg.rcvDunsMatchCd, rs.getString("RCV_DUNS_MATCH_CD"));
        setValue(driTMsg.rcvNmPrflCd, rs.getString("RCV_NM_PRFL_CD"));
        setValue(driTMsg.rcvStrNoPrflCd, rs.getString("RCV_STR_NO_PRFL_CD"));
        setValue(driTMsg.rcvStrNmPrflCd, rs.getString("RCV_STR_NM_PRFL_CD"));
        setValue(driTMsg.rcvDunsCnfdCd, rs.getString("RCV_DUNS_CNFD_CD"));
        setValue(driTMsg.rcvMatchGrdCd, rs.getString("RCV_MATCH_GRD_CD"));
        setValue(driTMsg.rcvNixieACd, rs.getString("RCV_NIXIE_A_CD"));
        setValue(driTMsg.rcvDunsActvCd, rs.getString("RCV_DUNS_ACTV_CD"));
        setValue(driTMsg.rcvDunsBizNm, rs.getString("RCV_DUNS_BIZ_NM"));
        setValue(driTMsg.rcvDunsTradeStyleNm, rs.getString("RCV_DUNS_TRADE_STYLE_NM"));
        setValue(driTMsg.rcvDunsLineAddr, rs.getString("RCV_DUNS_LINE_ADDR"));
        setValue(driTMsg.rcvDunsCtyNm, rs.getString("RCV_DUNS_CTY_NM"));
        setValue(driTMsg.rcvDunsStCd, rs.getString("RCV_DUNS_ST_CD"));
        setValue(driTMsg.rcvPostCd, rs.getString("RCV_POST_CD"));
        setValue(driTMsg.rcvDunsNum, rs.getString("RCV_DUNS_NUM"));
        setValue(driTMsg.rcvEmpTotNum, rs.getBigDecimal("RCV_EMP_TOT_NUM"));
        setValue(driTMsg.rcvAnnSlsAmt, rs.getBigDecimal("RCV_ANN_SLS_AMT"));
        setValue(driTMsg.rcvLineBizNm, rs.getString("RCV_LINE_BIZ_NM"));
        setValue(driTMsg.rcvFirstSicCd, rs.getString("RCV_FIRST_SIC_CD"));
        setValue(driTMsg.rcvGlblUltDunsNum, rs.getString("RCV_GLBL_ULT_DUNS_NUM"));
        setValue(driTMsg.rcvHqDunsNum, rs.getString("RCV_HQ_DUNS_NUM"));
        setValue(driTMsg.rcvPrntDunsNum, rs.getString("RCV_PRNT_DUNS_NUM"));
        setValue(driTMsg.rcvGlblUltBizNm, rs.getString("RCV_GLBL_ULT_BIZ_NM"));
        setValue(driTMsg.rcvCmpySicCd, rs.getString("RCV_CMPY_SIC_CD"));
        setValue(driTMsg.rcvCmpySicDescTxt, rs.getString("RCV_CMPY_SIC_DESC_TXT"));
        setValue(driTMsg.upldCsvRqstCmntTxt, this.errCode);

        return driTMsg;
    }

    /**
     * setUpdateAPI
     * @param getType String
     * @param rs ResultSet
     * @return pMsg
     */
    private NMZC001001PMsg setUpdateAPI(String getType, ResultSet rs) throws SQLException {

        String lineAddr = rs.getString("RCV_DUNS_LINE_ADDR") + " " + rs.getString("RCV_DUNS_CTY_NM");
        NMZC001001PMsg pMsg = new NMZC001001PMsg();

        // Default set
        setValue(pMsg.xxModeCd, getType);
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.saleDt);
        setValue(pMsg.dsAcctNum, rs.getString("DS_ACCT_NUM"));
        // Mod Start 2017/09/04 QC#20920
//        setValue(pMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
        if (ZYPCommonFunc.hasValue(rs.getString("DS_ACCT_NM"))) {
            setValue(pMsg.dsAcctNm, rs.getString("DS_ACCT_NM"));
        } else {
            setValue(pMsg.dsAcctNm, rs.getString("ORIG_DS_ACCT_NM"));
        }
        // Mod End 2017/09/04 QC#20920
        setValue(pMsg.dsAcctClsCd, this.clssFuncCode);
        setValue(pMsg.coaChCd, this.coaChCd);
        setValue(pMsg.coaAfflCd, this.coaAfflCd);
        setValue(pMsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
        setValue(pMsg.dsAcctLegalNm, rs.getString("DS_ACCT_LEGAL_NM"));
        setValue(pMsg.dbaNm, rs.getString("DBA_NM"));
        if (null != getType && getType.equals(PROCESS_MODE_CUST_UPD)) {
            setValue(pMsg.rgtnStsCd, rs.getString("RGTN_STS_CD"));
        }
        setValue(pMsg.dsAcctUrl, rs.getString("DS_ACCT_URL"));
        setValue(pMsg.firstRefCmntTxt, rs.getString("FIRST_REF_CMNT_TXT"));
        setValue(pMsg.scdRefCmntTxt, rs.getString("SCD_REF_CMNT_TXT"));
        setValue(pMsg.dsAcctItrlFlg, rs.getString("DS_ACCT_ITRL_FLG"));

        // Account DUNS Information
        if (ZYPCommonFunc.hasValue(rs.getString("RCV_CMPY_SIC_CD")) //
                || ZYPCommonFunc.hasValue(rs.getString("RCV_CMPY_SIC_DESC_TXT"))) {
            // Add Start 2016/10/13 QC#10696
            setValue(pMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_ON_Y);
            // Add End 2016/10/13 QC#10696
            setValue(pMsg.dsAcctDunsNum, rs.getString("CUR_DUNS_NUM"));
            setValue(pMsg.dsUltDunsNum, rs.getString("CUR_DS_ULT_DUNS_NUM"));
            setValue(pMsg.dsAcctDunsNm, rs.getString("DS_ACCT_DUNS_NM"));
            setValue(pMsg.dsLastUpdDunsDt, this.saleDt);
            setValue(pMsg.dsAcctAltNm, rs.getString("DS_ACCT_ALT_NM"));
            setValue(pMsg.dsLocEmpNum, rs.getBigDecimal("CUR_DS_LOC_EMP_NUM"));
            setValue(pMsg.dsLocRevAmt, rs.getBigDecimal("CUR_DS_LOC_REV_AMT"));
            setValue(pMsg.dsCustSicCd, rs.getString("RCV_CMPY_SIC_CD"));
            setValue(pMsg.dsXtrnlRefTxt, rs.getString("DS_XTRNL_REF_TXT"));
            setValue(pMsg.dsDataSrcTxt, rs.getString("DS_DATA_SRC_TXT"));
            setValue(pMsg.dsCustSicDescTxt, rs.getString("RCV_CMPY_SIC_DESC_TXT"));
        }

        // Default set
        setValue(pMsg.NMZC001002PMsg.no(0).locNum, rs.getString("LOC_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(pMsg.NMZC001002PMsg.no(0).effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(pMsg.NMZC001002PMsg.no(0).actvFlg, ZYPConstant.FLG_ON_Y);

        // Location Address Information
        setValue(pMsg.NMZC001002PMsg.no(0).firstLineAddr, rs.getString("ORG_FIRST_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).scdLineAddr, rs.getString("ORG_SCD_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).thirdLineAddr, rs.getString("ORG_THIRD_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).frthLineAddr, rs.getString("ORG_FRTH_LINE_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).ctyAddr, rs.getString("ORG_CTY_ADDR"));
        setValue(pMsg.NMZC001002PMsg.no(0).cntyNm, rs.getString("ORG_CNTY_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).stCd, rs.getString("ORG_ST_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).postCd, rs.getString("ORG_POST_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).ctryCd, rs.getString("ORG_CTRY_CD"));
        // Mod Start 2016/06/24 QC#10816
        // Mod Start 2016/07/01 M.Ohno QC#11147
        setValue(pMsg.NMZC001002PMsg.no(0).locNm, rs.getString("DS_LOC_NM"));
        // Mod Start 2016/07/01 M.Ohno QC#11147
        // Mod End 2016/06/24 QC#10816
        setValue(pMsg.NMZC001002PMsg.no(0).addlLocNm, rs.getString("ORG_ADDL_LOC_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).glnNum, rs.getBigDecimal("ORG_GLN_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).telNum, rs.getString("ORG_TEL_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).faxNum, rs.getString("ORG_FAX_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).provNm, rs.getString("ORG_PROV_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).lineBizTpCd, rs.getString("ORG_LINE_BIZ_TP_CD"));

        // Location TAX Area
        setValue(pMsg.NMZC001002PMsg.no(0).geoCd, rs.getString("ORG_GEO_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsInsdCtyLimitFlg, rs.getString("ORG_DS_INSD_CTY_LIMIT_FLG"));

        // Location DUNS Information
        // Mod Start 2016/10/14 QC#10696
//        setValue(pMsg.NMZC001002PMsg.no(0).dunFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.NMZC001002PMsg.no(0).xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
        // Mod End 2016/10/14 QC#10696
        setValue(pMsg.NMZC001002PMsg.no(0).dunsNum, rs.getString("RCV_DUNS_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).hqDunsNum, rs.getString("RCV_HQ_DUNS_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsUltDunsNum, rs.getString("RCV_GLBL_ULT_DUNS_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsPrntDunsNum, rs.getString("RCV_PRNT_DUNS_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsLocEmpNum, rs.getBigDecimal("RCV_EMP_TOT_NUM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsLocRevAmt, rs.getBigDecimal("RCV_ANN_SLS_AMT"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsLastUpdDunsDt, this.saleDt);
        setValue(pMsg.NMZC001002PMsg.no(0).dsCustSicCd, rs.getString("RCV_FIRST_SIC_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsCustSicDescTxt, rs.getString("RCV_LINE_BIZ_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dunsTradeStyleNm, rs.getString("RCV_DUNS_TRADE_STYLE_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dunsActvCd, rs.getString("RCV_DUNS_ACTV_CD"));
        setValue(pMsg.NMZC001002PMsg.no(0).dunsLineAddr, lineAddr);
        setValue(pMsg.NMZC001002PMsg.no(0).dunsBizNm, rs.getString("RCV_DUNS_BIZ_NM"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsLastRcvDunsTxt, rs.getString("DS_LAST_RCV_DUNS_TXT"));
        setValue(pMsg.NMZC001002PMsg.no(0).dsLastRcvDunsDt, rs.getString("DS_LAST_RCV_DUNS_DT"));
        setValue(pMsg.NMZC001002PMsg.no(0).dunsSendCnt, rs.getBigDecimal("ORG_DUNS_SEND_CNT"));

        pMsg.NMZC001002PMsg.setValidCount(1);

        return pMsg;
    }

    /**
     * callApiNMZC001001
     * @param pMsg NMZC001001PMsg
     * @param upldCsvRqstRowNum BigDecimal
     * @return errExist
     */
    private boolean callApiNMZC001001(NMZC001001PMsg pMsg, BigDecimal upldCsvRqstRowNum) {
        NMZC001001 api = new NMZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);

        boolean errExist = false;
        if (!errList.isEmpty()) {
            for (String xxMsgId : errList) {
                if (xxMsgId.endsWith("E") || xxMsgId.endsWith("W")) {
                    this.messenger.addMessageForRecord(upldCsvRqstRowNum, xxMsgId, null);
                    setErrCode(xxMsgId);
                    errExist = true;
                }
            }
        } else {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(0);
            errList = S21ApiUtil.getXxMsgIdList(linePrm);
            for (String xxMsgId : errList) {
                if (xxMsgId.endsWith("E") || xxMsgId.endsWith("W")) {
                    this.messenger.addMessageForRecord(upldCsvRqstRowNum, xxMsgId, null);
                    setErrCode(xxMsgId);
                    errExist = true;
                }
            }
        }

        return errExist;
    }

    /**
     * setDunsTrxHdr
     * @param userId String
     * @return inMsg
     */
    private DUNS_TRX_HDRTMsg setDunsTrxHdr(String userId) {

        this.seqDTH = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_TRX_HDR_SQ);

        DUNS_TRX_HDRTMsg inMsg = new DUNS_TRX_HDRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dunsTrxHdrPk, this.seqDTH);
        setValue(inMsg.dunsProcTpCd, UPLOAD_DUNS_FILE);
        // Mod Start 2016/07/21 QC#9955
//        setValue(inMsg.dunsProcStsCd, DUNS_PROC_STS_SONE);
        setValue(inMsg.dunsProcStsCd, DUNS_PROC_STS.DONE);
        // Mod End 2016/07/21 QC#9955
        setValue(inMsg.dunsProcTs, ZYPDateUtil.getCurrentSystemTime(SET_TIME_TYPE));
        setValue(inMsg.dunsProcById, userId);

        return inMsg;
    }

    /**
     * setDunsTrxHdr
     * @param cnt int
     * @return inMsg
     */
    private DUNS_TRX_FILETMsg setDunsTrxFile(int cnt) {

        this.seqDTF = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_TRX_FILE_SQ);
        String fileNm = getFileName(this.reqbatch.getFilePath());
        // Mod Start 2017/09/05 QC#20907
//        BigDecimal lineNum = BigDecimal.valueOf(this.reqbatch.getRecordCount());
        BigDecimal lineNum = BigDecimal.valueOf(cnt);
        // Mod End 2017/09/05 QC#20907

        DUNS_TRX_FILETMsg inMsg = new DUNS_TRX_FILETMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dunsTrxFilePk, this.seqDTF);
        setValue(inMsg.dunsTrxHdrPk, this.seqDTH);
        setValue(inMsg.dunsFileTpCd, UPLOAD_REVIEW_DATA);
        setValue(inMsg.dunsFileNm, fileNm);
        setValue(inMsg.dunsFileLineNum, lineNum);

        return inMsg;
    }

    /**
     * getFileName
     * @param path String
     * @return fileNm
     */
    private String getFileName(String path) {

        // Mod Start 2016/07/21 QC#9955
        int point = path.lastIndexOf("/");
        if (point < 0) {
            point = path.lastIndexOf("\\");
        }
        point++;
        // Mod End 2016/07/21 QC#9955
        String fileNm = path.substring(point);

        return fileNm;
    }

    /**
     * setErrCode
     * @param path String
     * @return fileNm
     */
    private void setErrCode(String msgId) {

        if (null != this.errCode) {
            String setMsgId = "," + msgId;
            this.errCode += setMsgId;
        } else {
            this.errCode = msgId;
        }
    }

    private void setInsertData(EZDTMsg tMsg) {

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            this.errFlg = true;
            this.termCd = TERM_CD.ABNORMAL_END;
            rollback();
        }
    }

    /**
     * formatDt
     * @param dt date
     * @return after format date
     */
    public static String formatDate(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > DATE_LENGTH) {
            dt = dt.substring(0, DATE_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, false);
    }

    /**
     * getMessageLocation
     * @param locNum String
     * @return String
     */
    private String getMessageLocation(String locNum) {

        StringBuilder sb = new StringBuilder();
        sb.append(MSG_LOCATION);
        sb.append(" ");
        sb.append(MSG_PAREN_START);
        sb.append(locNum);
        sb.append(MSG_PAREN_END);

        return sb.toString();
    }

    /**
     * getLastSextractSendDate
     * @param cMsg NMAL3000CMsg
     */
    private int getMaxUpdateCount() {

        // Mod Start 2016/06/28 QC#10905
        // Add Start 2016/06/24 QC#10340
//        NUM_CONSTTMsg tMsg = new NUM_CONSTTMsg();
//        setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
//        setValue(tMsg.numConstNm, UPDATE_COUNT_CONFIGRATION);
//        
//        NUM_CONSTTMsg outtMsg = (NUM_CONSTTMsg) EZDTBLAccessor.findByKey(tMsg);
//        
//        if (null == outtMsg) {
//            throw new S21AbendException(ZZZM9006E, new String[] {"Max Upload Count"});
//        }

//        int resultValue = outtMsg.numConstVal.getValue().intValue();
        BigDecimal resultValue = ZYPCodeDataUtil.getNumConstValue(UPDATE_COUNT_CONFIGRATION, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(resultValue)) {
            throw new EZDAbendException(ZZZM9006E, "Max Upload Count");
        }

        return resultValue.intValue();
        // Add End 2016/06/24 QC#10340
        // Mod End 2016/06/28 QC#10905
    }
}
