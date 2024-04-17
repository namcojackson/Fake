/*
 * Copyright (c) 2016 Canon USA Inc. All rights reserved. Original
 */
package com.canon.cusa.s21.batch.NMA.NMAB270001;

import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.DATE_LENGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.INFO_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.MSG_LOCATION;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.MSG_PAREN_END;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.MSG_PAREN_START;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NMAM0050E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NMAM8412E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NMAM8568E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.PROCESS_MODE_PROS_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.S21_LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.SALE_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.UPDATE_COUNT_CONFIGRATION;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.ZYPM0181E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB270001.constant.NMAB270001Constant.ZZZM9026E;

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
import business.db.ART10FMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
* 2016/07/21     Fujitsu    R.Nakamura   Create            N/A
* 2016/09/13     Fujitsu    N.Sugiura    Update            QC#14305
* 2016/09/21     Fujitsu    C.Yokoi      Update            QC#14694
*</pre>
 */
public class NMAB270001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** sale date time */
    private String saleDt = null;

    /** end Code */
    private TERM_CD termCd = null;

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

        this.maxUploadCnt = getMaxUpdateCount();

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

        new NMAB270001().executeBatch(NMAB270001.class.getSimpleName());

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
     * getMaxUpdateCount
     * @return maxUploadCount
     */
    private int getMaxUpdateCount() {

        BigDecimal maxUploadCount = ZYPCodeDataUtil.getNumConstValue(UPDATE_COUNT_CONFIGRATION, getGlobalCompanyCode());

        if (!ZYPCommonFunc.hasValue(maxUploadCount)) {
            throw new EZDAbendException(ZZZM9006E, "Max Upload Count");
        }

        return maxUploadCount.intValue();
    }

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg fMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

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

            List<String> checkValue = new ArrayList<String>();

            stmt = ssmLLClient.createPreparedStatement("getDunsInfoWrk", inWrkMap, execParam);
            rs = stmt.executeQuery();

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

                if (cnt > this.maxUploadCnt) {
                    this.messenger.addMessageForRecord(idx, NMAM0050E, String.valueOf(this.maxUploadCnt));
                    errCnt++;
                    totalErrFlg = true;
                    this.termCd = TERM_CD.WARNING_END;
                    break;
                }

                String locNum = rs.getString("LOC_NUM");
                String origAcctNum = rs.getString("ORG_DS_ACCT_NUM");
                String origLocNum = rs.getString("ORG_LOC_NUM");

                // Mandatory Check Location Number
                if (!ZYPCommonFunc.hasValue(locNum)) {
                    this.messenger.addMessageForRecord(idx, NMAM8228E, S21_LOC_NUM);
                    setErrCode(NMAM8228E);
                    this.errFlg = true;
                }

                // Location Number Check
                if (!this.errFlg) {
                     if (!ZYPCommonFunc.hasValue(origLocNum)) {
                        // 2016/09/21 CSA-QC#14694 Mod Start
                        this.messenger.addMessageForRecord(idx, NMAM8121E, getMessageLocation(locNum));
                        // this.messenger.addMessageForRecord(idx, NMAM8412E, locNum);
                        // 2016/09/21 CSA-QC#14694 Mod End
                        setErrCode(NMAM8412E);
                        this.errFlg = true;
                    }
                }

                // Overlap Check
                if (!this.errFlg) {
                    StringBuilder setValue = new StringBuilder();
                    setValue.append(origAcctNum);
                    setValue.append(",");
                    setValue.append(locNum);
                    if (!checkOrverlapValue(setValue.toString(), checkValue, idx)) {
                        checkValue.add(setValue.toString());
                    } else {
                        this.errFlg = true;
                    }
                }

                // Call Customer Update API
                if (!this.errFlg) {
                    NMZC001001PMsg pMsg = setUpdateAPI(rs);
                    if (callApiNMZC001001(pMsg, idx)) {
                        rollback();
                        this.errFlg = true;
                    }
                }

                commit();

//                dthTmsg = setDunsTrxHdr(userId);
//                setInsertData(dthTmsg);
//                dtfTmsg = setDunsTrxFile();
//                setInsertData(dtfTmsg);
//                driTMsg = setDunsRvwInfo(rs);
//                setInsertData(driTMsg);
//                commit();

                if (this.errFlg) {
                    errCnt++;
                    totalErrFlg = true;
                } else {
                    nomalCnt++;
                }
            }

            if (totalErrFlg) {
                if (!TERM_CD.ABNORMAL_END.equals(this.termCd)) {
                    this.termCd = TERM_CD.WARNING_END;
                }
            } else {
                this.termCd = TERM_CD.NORMAL_END;
            }

            this.normalRecCount += nomalCnt;
            this.errRecCount += errCnt;
            StringBuilder msgValue = new StringBuilder();
            msgValue.append(this.normalRecCount);
            msgValue.append(INFO_MSG);

            if (totalErrFlg) {
                msgValue.append(" ");
                msgValue.append(this.errRecCount);
                msgValue.append(ERR_MSG);
                this.messenger.addMessageForFile(NYXM0002E, msgValue.toString());
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
            } else {
                this.messenger.addMessageForFile(NYXM0001I, msgValue.toString());
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);

        }
    }

    /**
     * getUploadRequestPK
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
     * getMessageLocation
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
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
     * getUpldCsvId
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
        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) S21FastTBLAccessor.findByKey(inTMsg);

        if (null == outTMsg) {
            throw new S21AbendException(ZYPM0181E);
        }

        return outTMsg;
    }

    /**
     * checkOrverlapValue
     * @param setValue String
     * @param checkValue List<String>
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
     * setUpdateAPI
     * @param rs ResultSet
     * @return pMsg
     */
    private NMZC001001PMsg setUpdateAPI(ResultSet rs) throws SQLException {

        NMZC001001PMsg pMsg = new NMZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, PROCESS_MODE_PROS_UPD);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.saleDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, rs.getString("ORG_DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNm, rs.getString("ORG_DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctClsCd, rs.getString("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.coaChCd, rs.getString("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.coaAfflCd, rs.getString("COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctLegalNm, rs.getString("DS_ACCT_LEGAL_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dbaNm, rs.getString("DBA_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctUrl, rs.getString("DS_ACCT_URL"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsDataSrcTxt, rs.getString("DS_DATA_SRC_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsXtrnlRefTxt, rs.getString("DS_XTRNL_REF_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.firstRefCmntTxt, rs.getString("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.scdRefCmntTxt, rs.getString("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctItrlFlg, rs.getString("DS_ACCT_ITRL_FLG"));

        if (ZYPCommonFunc.hasValue(rs.getString("DS_CMPY_SIC_CD")) //
                || ZYPCommonFunc.hasValue(rs.getString("DS_ACCT_DUNS_NM"))) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDunsNum, rs.getString("DS_ACCT_DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsUltDunsNum, rs.getString("ACCT_DS_ULT_DUNS_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDunsNm, rs.getString("DS_ACCT_DUNS_NM")); //Update
            ZYPEZDItemValueSetter.setValue(pMsg.dsLastUpdDunsDt, this.saleDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctAltNm, rs.getString("DS_ACCT_ALT_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsLocEmpNum, rs.getBigDecimal("ACCT_DS_LOC_EMP_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsLocRevAmt, rs.getBigDecimal("ACCT_DS_LOC_REV_AMT"));
            ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicCd, rs.getString("DS_CMPY_SIC_CD")); //Update
            ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicDescTxt, rs.getString("ACCT_DS_CUST_SIC_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_ON_Y);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).locNum, rs.getString("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).effFromDt, rs.getString("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).effThruDt, rs.getString("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).actvFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).scdLineAddr, rs.getString("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).ctyAddr, rs.getString("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).cntyNm, rs.getString("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).stCd, rs.getString("ST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).postCd, rs.getString("POST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).ctryCd, rs.getString("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).locNm, rs.getString("DS_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).addlLocNm, rs.getString("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).glnNum, rs.getBigDecimal("GLN_NUM")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).telNum, rs.getString("TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).faxNum, rs.getString("FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).provNm, rs.getString("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));

        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).geoCd, rs.getString("GEO_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsInsdCtyLimitFlg, rs.getString("DS_INSD_CTY_LIMIT_FLG"));

        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsNum, rs.getString("DUNS_NUM")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).hqDunsNum, rs.getString("HQ_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsPrntDunsNum, rs.getString("DS_PRNT_DUNS_NUM")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsLocEmpNum, rs.getBigDecimal("DS_LOC_EMP_NUM")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsLocRevAmt, rs.getBigDecimal("DS_LOC_REV_AMT")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsLastUpdDunsDt, this.saleDt);
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsCustSicCd, rs.getString("DS_CUST_SIC_CD")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsCustSicDescTxt, rs.getString("DS_CUST_SIC_DESC_TXT")); //Update
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsTradeStyleNm, rs.getString("DUNS_TRADE_STYLE_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsActvCd, rs.getString("DUNS_ACTV_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsLineAddr, rs.getString("DUNS_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsBizNm, rs.getString("DUNS_BIZ_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsLastRcvDunsTxt, rs.getString("DS_LAST_RCV_DUNS_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dsLastRcvDunsDt, rs.getString("DS_LAST_RCV_DUNS_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.NMZC001002PMsg.no(0).dunsSendCnt, rs.getBigDecimal("DUNS_SEND_CNT"));

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
     * setErrCode
     * @param msgId String
     */
    private void setErrCode(String msgId) {

        StringBuilder setMsgId = new StringBuilder();

        if (ZYPCommonFunc.hasValue(this.errCode)) {
            setMsgId.append(this.errCode);
            setMsgId.append(",");
        }

        setMsgId.append(msgId);
        this.errCode = setMsgId.toString();
    }

    /**
     * formatDate
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

}
