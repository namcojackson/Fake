/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB263001;

import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.CHK_DATE_PATTERN;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.COMMA;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_DTL_MASS_UPD_RSN_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_DTL_MOVE_EFF_FROM_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_DTL_MOVE_EFF_THRU_DT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_DTL_TRTY_RULE_FROM_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_DTL_TRTY_RULE_OPRD_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_DTL_TRTY_RULE_THRU_VAL_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_ERR_MSG;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_ERR_MSG_LIST;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_NEW_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_OLD_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATA_MAP_KEY_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DATE_FORMAT_PADDING_ZERO;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM0071E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM0185E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM0282E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8121E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8186E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8459E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8497E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8578E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8599E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8600E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8601E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8602E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8603E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8604E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8609E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NMAM8619E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.SLASH;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.SPACE;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.ZZZM9026E;
import static com.canon.cusa.s21.batch.NMA.NMAB263001.constant.NMAB263001Constant.MESSAGE_KIND_ERROR;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.POST_UPD_RQST_DTLTMsg;
import business.db.POST_UPD_RQST_HDRTMsg;
import business.db.POST_UPD_WRKTMsg;
import business.db.TRTY_RULETMsg;
import business.parts.NMZC270001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC270001.NMZC270001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_DTL_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Reassign Postal Codes Upload Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         S.Ohki          Create
 * 2017/08/16   Fujitsu         A.Kosai         Update          QC#20552
 * 2017/11/16   Fujitsu         N.Sugiura       Update          QC#20597
 * 2017/11/29   Fujitsu         N.Sugiura       Update          QC#20551
 *</pre>
 */
public class NMAB263001 extends S21BatchMain {

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

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

    /** Update CSV User ID */
    private String upldCsvId = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** ONLINE ErrorMsg */
    private StringBuilder onlineMsg = new StringBuilder();

    /** Territory Group Error Count */
    private int trtyGrpErrCount = 0;

    /** Total Normal Count */
    private int reqTotNmlCount = 0;

    /** Total Error Count */
    private int reqTotErrCount = 0;

    /** Total Process Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Error Flag */
    private boolean isErrFlg = false;

    /** Group Error Message Map List */
    List<Map<String, Object>> trtyGrpDataMapList = null;

    /** Error Message List */
    List<List<String>> msgRepList = null;

    /** Current System Time */
    private String currentSysTime = null;

    /** Insert Post Update Request Header PK */
    private BigDecimal insertPostUpdRqstHdrPk = null;

    /**
     */
    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.userConstantVariable = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(userConstantVariable)) {
            throw new S21AbendException(ZZZM9026E, new String[] {"User Constant Variable" });
        }

        this.currentSysTime = EZDDBCICarrier.getStartDateTime();

        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NMAB263001().executeBatch(NMAB263001.class.getSimpleName());

    }

    @Override
    protected void mainRoutine() {
        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", glblCmpyCd);
            param.put("rqstCratSysTpCd", RQST_CRAT_SYS_TP.ONLINE);
            param.put("rqstRsltTpCd", RQST_RSLT_TP.SUBMITTED);

            List<Map<String, Object>> pkMapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRequestHdrPk", param);

            ART10FMsg fMsg = new ART10FMsg();
            for (Map<String, Object> pkMap : pkMapList) {
                this.reqTotNmlCount = 0;
                this.reqTotErrCount = 0;
                BigDecimal rqstPk = (BigDecimal) pkMap.get("POST_UPD_RQST_HDR_PK");
                this.doProcess(fMsg, rqstPk);
            }

        } else {
            batchHelper = new S21RequestBatchHelper();
            if (!this.batchHelper.isRecord()) {
                this.termCd = TERM_CD.WARNING_END;
                return;
            }
            for (ART10FMsg request : this.batchHelper.getRequestList()) {
                this.reqTotNmlCount = 0;
                this.reqTotErrCount = 0;
                this.doProcess(request, null);

            }
        }
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * @param fMsg ART10FMsg
     */
    private void doProcess(ART10FMsg fMsg, BigDecimal rqstPk) {

        PostUpdRequestBean reqBean = null;
        PreparedStatement psWrk = null;
        ResultSet rsSet = null;
        BigDecimal upldCsvRqstPk = null;

        List<NMZC270001PMsg> apiParamMsgList = new ArrayList<NMZC270001PMsg>();

        if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            // Upload ID
            this.upldCsvId = getUpldCsvId(fMsg);

            // Upload Request PK
            upldCsvRqstPk = getUpldCsvReqPk(fMsg);
            this.messenger = new ZYPCSVUploadMessenger(this.upldCsvId, upldCsvRqstPk);
        }

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, Object> inWrkMap = new HashMap<String, Object>();
            execParam.setFetchSize(DEFAULT_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("postUpdRqstHdrPk", rqstPk);

                psWrk = ssmLLClient.createPreparedStatement("getRequestInfo", inWrkMap, execParam);
                rsSet = psWrk.executeQuery();

            } else if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
                inWrkMap.put("glblCmpyCd", glblCmpyCd);
                inWrkMap.put("upldCsvRqstPk", upldCsvRqstPk);
                inWrkMap.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
                inWrkMap.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

                List<String> gnrnTpCdList = new ArrayList<String>();
                gnrnTpCdList.add(GNRN_TP.CURRENT);
                gnrnTpCdList.add(GNRN_TP.FUTURE);
                inWrkMap.put("gnrnTpCdList", gnrnTpCdList);

                psWrk = ssmLLClient.createPreparedStatement("getWrkInfo", inWrkMap, execParam);
                rsSet = psWrk.executeQuery();
            }

            List<BigDecimal> errSetPkList = new ArrayList<BigDecimal>();
            trtyGrpDataMapList = new ArrayList<Map<String, Object>>();
            msgRepList = new ArrayList<List<String>>();

            String newOrgCdPrev = null;
            boolean isMdtryChkSccess = true;
            insertPostUpdRqstHdrPk = BigDecimal.valueOf(0);

            this.trtyGrpErrCount = 0;
            int reqTotErrCountPre = reqTotErrCount;
            int reqTotNmlCountPre = reqTotNmlCount;
            int resultCount = 0;
            int newGrpCount = 0;

            while (rsSet.next()) {

                // New Territory Change
                if (newOrgCdPrev != null && !newOrgCdPrev.equals(rsSet.getString("NEW_ORG_CD"))) {

                    if (trtyGrpErrCount == 0) {
                        setRuleValidationApiParam(reqBean, apiParamMsgList, reqBean.getNewOrgCd());
                        if (!callRuleValidationApi(apiParamMsgList, reqBean, errSetPkList)) {
                            trtyGrpErrCount++;
                            reqTotErrCount = reqTotErrCountPre + newGrpCount;
                            reqTotNmlCount = reqTotNmlCountPre;
                        }
                    } else {
                        reqTotErrCount = reqTotErrCountPre + newGrpCount;
                        reqTotNmlCount = reqTotNmlCountPre;
                    }

                    if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                        if (this.trtyGrpErrCount > 0) {
                            rollback();
                            updatePostUpdPostDtlErrMsg();
                            updateAnotherErrMessageOnline(errSetPkList);
                        }

                    } else {

                        if (this.trtyGrpErrCount > 0) {
                            rollback();
                            updateAnotherErrMessageCsvUpload(errSetPkList, upldCsvRqstPk);
                            insertPostUpdRqst(reqBean);
                        }
                        updatePostUpdWrkErrMsg();
                    }

                    commit();

                    trtyGrpDataMapList = new ArrayList<Map<String, Object>>();
                    errSetPkList = new ArrayList<BigDecimal>();
                    this.trtyGrpErrCount = 0;
                    reqTotErrCountPre = reqTotErrCount;
                    reqTotNmlCountPre = reqTotNmlCount;
                    newGrpCount = 0;
                }

                this.isErrFlg = false;
                this.onlineMsg = new StringBuilder();

                reqBean = new PostUpdRequestBean();

                setRequestBean(reqBean, rsSet);

                isMdtryChkSccess = validationCheck(reqBean, rsSet);

                if (isMdtryChkSccess) {
                    getDsOrgUnitInfo(reqBean);
                    reassignPostalUpdate(reqBean);
                    setRuleValidationApiParam(reqBean, apiParamMsgList, reqBean.getOldOrgCd());
                }

                if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    dataMap.put(DATA_MAP_KEY_RQST_PK, reqBean.getPostUpdRqstDtlPk());
                    dataMap.put(DATA_MAP_KEY_NEW_ORG_CD, reqBean.getNewOrgCd());
                    dataMap.put(DATA_MAP_KEY_OLD_ORG_CD, reqBean.getOldOrgCd());
                    dataMap.put(DATA_MAP_KEY_ERR_MSG, this.onlineMsg.toString());
                    trtyGrpDataMapList.add(dataMap);
                } else {
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    dataMap.put(DATA_MAP_KEY_RQST_PK, reqBean.getUpldCsvRqstRowNum());
                    dataMap.put(DATA_MAP_KEY_NEW_ORG_CD, reqBean.getNewOrgCd());
                    dataMap.put(DATA_MAP_KEY_OLD_ORG_CD, reqBean.getOldOrgCd());
                    dataMap.put(DATA_MAP_KEY_ERR_MSG, this.onlineMsg.toString());
                    dataMap.put(DATA_MAP_KEY_ERR_MSG_LIST, msgRepList);
                    dataMap.put(DATA_MAP_KEY_DTL_TRTY_RULE_OPRD_TP_DESC_TXT, reqBean.getTrtyRuleOprdTpDescTxt());
                    dataMap.put(DATA_MAP_KEY_DTL_TRTY_RULE_FROM_VAL_TXT, reqBean.getTrtyRuleFromValTxt());
                    dataMap.put(DATA_MAP_KEY_DTL_TRTY_RULE_THRU_VAL_TXT, reqBean.getTrtyRuleThruValTxt());
                    dataMap.put(DATA_MAP_KEY_DTL_MOVE_EFF_FROM_DT_TXT, reqBean.getMoveEffFromDtTxt());
                    dataMap.put(DATA_MAP_KEY_DTL_MOVE_EFF_THRU_DT_TXT, reqBean.getMoveEffThruDtTxt());
                    dataMap.put(DATA_MAP_KEY_DTL_MASS_UPD_RSN_CMNT_TXT, reqBean.getMassUpdRsnCmntTxt());

                    trtyGrpDataMapList.add(dataMap);
                }

                if (this.isErrFlg) {
                    this.reqTotErrCount++;
                } else {

                    if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                        errSetPkList.add(reqBean.getPostUpdRqstDtlPk());
                    } else {
                        errSetPkList.add(reqBean.getUpldCsvRqstRowNum());
                    }

                    this.reqTotNmlCount++;
                }

                msgRepList = new ArrayList<List<String>>();
                newOrgCdPrev = reqBean.getNewOrgCd();
                newGrpCount++;
                resultCount++;
            }

            if (resultCount == 0) {
                return;
            }

            if (trtyGrpErrCount == 0) {
                setRuleValidationApiParam(reqBean, apiParamMsgList, reqBean.getNewOrgCd());
                if (!callRuleValidationApi(apiParamMsgList, reqBean, errSetPkList)) {
                    trtyGrpErrCount++;
                    reqTotErrCount = reqTotErrCountPre + newGrpCount;
                    reqTotNmlCount = reqTotNmlCountPre;
                }
            } else {
                reqTotErrCount = reqTotErrCountPre + newGrpCount;
                reqTotNmlCount = reqTotNmlCountPre;
            }

            if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                if (this.trtyGrpErrCount > 0) {
                    rollback();
                    updatePostUpdPostDtlErrMsg();
                    updateAnotherErrMessageOnline(errSetPkList);
                }
                updatePostUpdPostHdrCmnt(rqstPk);

            } else {

                if (this.trtyGrpErrCount > 0) {
                    rollback();
                    updateAnotherErrMessageCsvUpload(errSetPkList, upldCsvRqstPk);
                    insertPostUpdRqst(reqBean);
                }
                updatePostUpdPostHdrCmnt(insertPostUpdRqstHdrPk);
                updatePostUpdWrkErrMsg();

                if (this.reqTotErrCount > 0) {
                    this.messenger.addMessageForFile(NYXM0002E, createSuccessErrRcdCmnt());
                    this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                } else {
                    this.messenger.addMessageForFile(NYXM0001I, createSuccessErrRcdCmnt());
                    this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                }
                this.messenger.uploadMessage();
            }

            commit();
            EZDDebugOutput.println(0, "POST_UPD_RQST_HDR_PK:" + insertPostUpdRqstHdrPk, this);
            insertPostUpdRqstHdrPk = BigDecimal.valueOf(0);

            if (this.reqTotErrCount > 0) {
                this.termCd = TERM_CD.WARNING_END;
                totalErrCount++;
            } else {
                totalNmlCount++;
            }

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(psWrk, rsSet);
        }
    }

    /**
     * Set Request Bean
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void setRequestBean(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        reqBean.setRqstPk(rsSet.getBigDecimal("RQST_PK"));
        reqBean.setOldOrgCd(rsSet.getString("OLD_ORG_CD"));
        reqBean.setNewOrgCd(rsSet.getString("NEW_ORG_CD"));
        reqBean.setMoveEffFromDtTxt(rsSet.getString("MOVE_EFF_FROM_DT_TXT"));
        reqBean.setMoveEffThruDtTxt(rsSet.getString("MOVE_EFF_THRU_DT_TXT"));
        reqBean.setTrtyRuleOprdTpDescTxt(rsSet.getString("TRTY_RULE_OPRD_TP_DESC_TXT"));
        reqBean.setTrtyRuleFromValTxt(rsSet.getString("TRTY_RULE_FROM_VAL_TXT"));
        reqBean.setTrtyRuleThruValTxt(rsSet.getString("TRTY_RULE_THRU_VAL_TXT"));
        reqBean.setPostUpdRqstDtlPk(rsSet.getBigDecimal("POST_UPD_RQST_DTL_PK"));
        reqBean.setUpldCsvRqstRowNum(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        // 2017/11/29 QC#20551 Add Start
        reqBean.setMassUpdRsnCmntTxt(rsSet.getString("MASS_UPD_RSN_CMNT_TXT"));
        // 2017/11/29 QC#20551 Add End
        reqBean.setRqstUserId(rsSet.getString("USER_ID"));
    }

    /**
     * Validation Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean validationCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        if (!mandatoryCheck(rsSet)) {
            dateAttributeCheck(reqBean, rsSet);
            return false;
        } else {
            if (!dateAttributeCheck(reqBean, rsSet)) {
                return false;
            }
        }

        sameValueCheck(rsSet);

        masterExistCheck(reqBean, rsSet);

        // Territory/Territory Rule Active Check
        territryActiveCheck(reqBean, rsSet);

        // Operand Master Check
        if (!operandMasterCheck(reqBean, rsSet)) {
            return false;
        }

        // Territory Effective Date Period Check
        territoryEffectiveDatePeriodCheck(reqBean, rsSet);

        // Territory Effective Date Integrity Check
        territoryEffectiveDateIntegrityCheck(reqBean, rsSet);

        // Territory Rule Logic Type Check
        territoryRuleLogicTpCheck(reqBean, rsSet);

        // Territory Rule Update Time Check
        territoryRuleUpTimeCheck(reqBean, rsSet);

        return true;
    }

    /**
     * Mandatory Check
     * @param rsSet ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean mandatoryCheck(ResultSet rsSet) throws SQLException {

        boolean sccessFlg = true;

        if (!ZYPCommonFunc.hasValue(rsSet.getString("OLD_ORG_CD"))) {
            if (!ZYPCommonFunc.hasValue(rsSet.getString("OLD_ORG_NM"))) {
                setErrorMsg(rsSet, NMAM8228E, "Old Territory Name");
            } else {
                setErrorMsg(rsSet, NMAM8186E, "Old Territory Name");
            }
            sccessFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("NEW_ORG_CD"))) {
            if (!ZYPCommonFunc.hasValue(rsSet.getString("NEW_ORG_NM"))) {
                setErrorMsg(rsSet, NMAM8228E, "New Territory Name");
            } else {
                setErrorMsg(rsSet, NMAM8186E, "New Territory Name");
            }
            sccessFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("MOVE_EFF_FROM_DT_TXT"))) {
            setErrorMsg(rsSet, NMAM8228E, "Move Effective From");
            sccessFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("TRTY_RULE_OPRD_TP_DESC_TXT"))) {
            setErrorMsg(rsSet, NMAM8228E, "Operand");
            sccessFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("TRTY_RULE_FROM_VAL_TXT"))) {
            setErrorMsg(rsSet, NMAM8228E, "Value From");
            sccessFlg = false;
        }

        return sccessFlg;
    }

    /**
     * Same Value Check
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void sameValueCheck(ResultSet rsSet) throws SQLException {

        if (ZYPCommonFunc.hasValue(rsSet.getString("OLD_ORG_CD")) && ZYPCommonFunc.hasValue(rsSet.getString("NEW_ORG_CD"))) {
            if (S21StringUtil.isEquals(rsSet.getString("OLD_ORG_CD"), rsSet.getString("NEW_ORG_CD"))) {
                setErrorMsg(rsSet, NMAM8578E, null);
            }
        }
    }

    /**
     * Master Exist Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void masterExistCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("orgCd", reqBean.getOldOrgCd());
        param.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        param.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        List<String> gnrnTpCdList = new ArrayList<String>();
        gnrnTpCdList.add(GNRN_TP.CURRENT);
        gnrnTpCdList.add(GNRN_TP.FUTURE);
        param.put("gnrnTpCdList", gnrnTpCdList);

        BigDecimal resCount = (BigDecimal) this.ssmBatchClient.queryObject("getOrgUnitCount", param);

        if (BigDecimal.ZERO.compareTo(resCount) == 0) {
            setErrorMsg(rsSet, NMAM8121E, "Old Territory");
        }

        param.put("orgCd", reqBean.getNewOrgCd());

        resCount = (BigDecimal) this.ssmBatchClient.queryObject("getOrgUnitCount", param);

        if (BigDecimal.ZERO.compareTo(resCount) == 0) {
            setErrorMsg(rsSet, NMAM8121E, "New Territory");
        }
    }

    /**
     * Territory Active Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void territryActiveCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("orgCd", reqBean.getOldOrgCd());
        param.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        param.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
        param.put("trtyRuleFromValTxt", reqBean.getTrtyRuleFromValTxt());
        param.put("trtyRuleThruValTxt", reqBean.getTrtyRuleThruValTxt());
        // 2017/08/16 QC#20552 Del Start
        // param.put("trtyRuleOprdTpDescTxt", reqBean.getTrtyRuleOprdTpDescTxt());
        // 2017/08/16 QC#20552 Del End
        param.put("slsDt", this.slsDt);

        List<String> gnrnTpCdList = new ArrayList<String>();
        gnrnTpCdList.add(GNRN_TP.CURRENT);
        gnrnTpCdList.add(GNRN_TP.FUTURE);
        param.put("gnrnTpCdList", gnrnTpCdList);

        Map<String, Object> trtyActvMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getActiveTrty", param);

        if (trtyActvMap == null || !ZYPCommonFunc.hasValue((String) trtyActvMap.get("ORG_CD"))) {
            setErrorMsg(rsSet, NMAM8497E, "Old Territory");
            return;
        } else {
            reqBean.setEffFromDt((String) trtyActvMap.get("EFF_FROM_DT"));
            reqBean.setEffThruDt((String) trtyActvMap.get("EFF_THRU_DT"));
            reqBean.setOrgStruTpCd((String) trtyActvMap.get("ORG_STRU_TP_CD"));
            reqBean.setTrtyGrpTpCd((String) trtyActvMap.get("TRTY_GRP_TP_CD"));
            reqBean.setTrtyTpCd((String) trtyActvMap.get("TRTY_TP_CD"));
            reqBean.setFirstOrgCd((String) trtyActvMap.get("FIRST_ORG_CD"));

        }

        if (ZYPCommonFunc.hasValue((BigDecimal) trtyActvMap.get("TRTY_RULE_PK"))) {
            reqBean.setActiveTrtyRulePk((BigDecimal) trtyActvMap.get("TRTY_RULE_PK"));
            reqBean.setActiveTrtyRuleTpCd((String) trtyActvMap.get("TRTY_RULE_TP_CD"));
            reqBean.setActiveTrtyRuleOrgTierCd((String) trtyActvMap.get("TRTY_RULE_ORG_TIER_CD"));
            reqBean.setActiveTrtyRuleOprdTpCd((String) trtyActvMap.get("TRTY_RULE_OPRD_TP_CD"));
            reqBean.setActiveTrtyRuleLogicTpCd((String) trtyActvMap.get("TRTY_RULE_LOGIC_TP_CD"));
            reqBean.setActiveTrtyRuleFromValTxt((String) trtyActvMap.get("TRTY_RULE_FROM_VAL_TXT"));
            reqBean.setActiveTrtyRuleThruValTxt((String) trtyActvMap.get("TRTY_RULE_THRU_VAL_TXT"));
            reqBean.setActiveEffFromDt((String) trtyActvMap.get("TRTY_RULE_EFF_FROM_DT"));
            reqBean.setActiveEffThruDt((String) trtyActvMap.get("TRTY_RULE_EFF_THRU_DT"));

        } else {
            setErrorMsg(rsSet, NMAM8497E, "Old Territory Rule");
        }
    }

    /**
     * Date Attribute Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean dateAttributeCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
            return true;
        }

        String fromDate = rsSet.getString("MOVE_EFF_FROM_DT_TXT");
        String thruDate = rsSet.getString("MOVE_EFF_THRU_DT_TXT");
        boolean successFlg = true;

        if (ZYPCommonFunc.hasValue(fromDate)) {
            // Move Effective From
            if (fromDate.matches(CHK_DATE_PATTERN)) {
                fromDate = convDate(fromDate);

                if (ZYPDateUtil.isValidDate(fromDate, ZYPDateUtil.TYPE_YYYYMMDD)) {
                    reqBean.setMoveEffFromDtTxt(fromDate);
                } else {
                    reqBean.setMoveEffFromDtTxt(fromDate);
                    setErrorMsg(rsSet, NMAM8459E, "Move Effective From");
                    successFlg = false;
                }
            } else {
                setErrorMsg(rsSet, NMAM8459E, "Move Effective From");
                successFlg = false;
            }
        }

        // Move Effective Thru
        if (ZYPCommonFunc.hasValue(thruDate)) {
            if (thruDate.matches(CHK_DATE_PATTERN)) {
                thruDate = convDate(thruDate);

                if (ZYPDateUtil.isValidDate(thruDate, ZYPDateUtil.TYPE_YYYYMMDD)) {
                    reqBean.setMoveEffThruDtTxt(thruDate);
                } else {
                    reqBean.setMoveEffThruDtTxt(thruDate);
                    setErrorMsg(rsSet, NMAM8459E, "Move Effective Thru");
                    successFlg = false;
                }

            } else {
                setErrorMsg(rsSet, NMAM8459E, "Move Effective Thru");
                successFlg = false;
            }
        }

        // Chronological Sequence Check
        if (successFlg && ZYPCommonFunc.hasValue(fromDate) && ZYPCommonFunc.hasValue(thruDate)) {
            if (fromDate.compareTo(thruDate) > 0) {
                setErrorMsg(rsSet, NMAM0185E, null);
            }
        }

        return successFlg;
    }

    /**
     * Conversion Date Format
     * @param date String
     * @return String
     */
    private String convDate(String date) {

        // Conversion Date Format
        String[] fromDateList = date.split(SLASH);

        if (fromDateList[0].length() == 1) {
            fromDateList[0] = DATE_FORMAT_PADDING_ZERO + fromDateList[0];
        }

        if (fromDateList[1].length() == 1) {
            fromDateList[1] = DATE_FORMAT_PADDING_ZERO + fromDateList[1];
        }

        return fromDateList[2] + fromDateList[0] + fromDateList[1];
    }

    /**
     * Operand Master Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @return boolean
     * @throws SQLException
     */
    private boolean operandMasterCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        // 2017/08/16 QC#20552 Mod Start
        // TRTY_RULE_OPRD_TPTMsg operandTMsg = new TRTY_RULE_OPRD_TPTMsg();
        // ZYPEZDItemValueSetter.setValue(operandTMsg.glblCmpyCd, this.glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(operandTMsg.trtyRuleOprdTpDescTxt, reqBean.getTrtyRuleOprdTpDescTxt());

        // TRTY_RULE_OPRD_TPTMsgArray outMsg = (TRTY_RULE_OPRD_TPTMsgArray) S21CodeTableAccessor.findByCondition(operandTMsg);
        // if (outMsg == null || outMsg.length() == 0) {
        //     setErrorMsg(rsSet, NMAM8121E, "Operand");
        //     return false;
        // } else {
        //     reqBean.setTrtyRuleOprdTpCd(outMsg.no(0).trtyRuleOprdTpCd.getValue());
        // }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("trtyRuleOprdTpDescTxt", reqBean.getTrtyRuleOprdTpDescTxt());

        List<Map<String, Object>> trtyOprdList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryOperandTp", param); 
        if (trtyOprdList == null || trtyOprdList.size() == 0) {
            setErrorMsg(rsSet, NMAM8121E, "Operand");
            return false;
        } else {
            reqBean.setTrtyRuleOprdTpCd((String) trtyOprdList.get(0).get("TRTY_RULE_OPRD_TP_CD"));
        }
        // 2017/08/16 QC#20552 Mod End
        return true;
    }

    /**
     * Territory Effective Date Period Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void territoryEffectiveDatePeriodCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("orgCd", reqBean.getNewOrgCd());
        param.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        param.put("moveEffFromDt", reqBean.getMoveEffFromDtTxt());
        param.put("moveEffThruDt", reqBean.getMoveEffThruDtTxt());

        BigDecimal resCount = (BigDecimal) this.ssmBatchClient.queryObject("getEffectiveDateCount", param);

        if (BigDecimal.ZERO.compareTo(resCount) == 0) {
            setErrorMsg(rsSet, NMAM8599E, null);
        }

        param.put("orgCd", reqBean.getOldOrgCd());
        param.put("moveEffThruDt", ZYPDateUtil.addDays(reqBean.getMoveEffFromDtTxt(), -1));

        resCount = (BigDecimal) this.ssmBatchClient.queryObject("getEffectiveDateCount", param);

        if (BigDecimal.ZERO.compareTo(resCount) == 0) {
            setErrorMsg(rsSet, NMAM8619E, null);
        }
    }

    /**
     * Territory Effective DateIntegrity Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void territoryEffectiveDateIntegrityCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("orgCd", reqBean.getOldOrgCd());
        param.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        param.put("slsDt", this.slsDt);
        param.put("trtyRuleTpCd", TRTY_RULE_TP.POSTAL_CODE);
        param.put("trtyRuleOprdTpCd", reqBean.getTrtyRuleOprdTpCd());
        param.put("trtyRuleFromValTxt", reqBean.getTrtyRuleFromValTxt());
        param.put("trtyRuleThruValTxt", reqBean.getTrtyRuleThruValTxt());

        Map<String, String> resMap = (Map<String, String>) this.ssmBatchClient.queryObject("getTerritoryEffectiveFromDate", param);

        if (resMap == null || !ZYPCommonFunc.hasValue(resMap.get("EFF_FROM_DT"))) {
            setErrorMsg(rsSet, NMAM8604E, "Territory Effective Date Integrity Check");
            return;
        }

        String oldFromDt = resMap.get("EFF_FROM_DT");
        String chngThruDt = ZYPDateUtil.addDays(reqBean.getMoveEffFromDtTxt(), -1);

        if (oldFromDt.compareTo(chngThruDt) > 0) {
            setErrorMsg(rsSet, NMAM8600E, null);
        }
    }

    /**
     * Territory Rule Logic Type Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void territoryRuleLogicTpCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        Map<String, Object> oldParam = new HashMap<String, Object>();
        oldParam.put("glblCmpyCd", glblCmpyCd);
        oldParam.put("orgCd", reqBean.getOldOrgCd());
        oldParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        oldParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        oldParam.put("oldRuleFlg", ZYPConstant.FLG_ON_Y);

        Map<String, String> oldResMap = (Map<String, String>) this.ssmBatchClient.queryObject("getTerritoryLogicTp", oldParam);

        if (oldResMap == null) {
            return;
        }

        Map<String, Object> newParam = new HashMap<String, Object>();
        newParam.put("glblCmpyCd", glblCmpyCd);
        newParam.put("orgCd", reqBean.getNewOrgCd());
        newParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        newParam.put("oldRuleFlg", ZYPConstant.FLG_OFF_N);

        Map<String, String> newResMap = (Map<String, String>) this.ssmBatchClient.queryObject("getTerritoryLogicTp", newParam);

        if (newResMap == null) {
            return;
        }

        String oldLogicTpCd = oldResMap.get("TRTY_RULE_LOGIC_TP_CD");
        String newLogicTpCd = newResMap.get("TRTY_RULE_LOGIC_TP_CD");

        if (oldLogicTpCd != null && !oldLogicTpCd.equals(newLogicTpCd)) {
            setErrorMsg(rsSet, NMAM8601E, null);
        }
    }

    /**
     * Territory Rule Update Time Check
     * @param reqBean PostUpdRequestBean
     * @param rsSet ResultSet
     * @throws SQLException
     */
    private void territoryRuleUpTimeCheck(PostUpdRequestBean reqBean, ResultSet rsSet) throws SQLException {

        Map<String, Object> oldParam = new HashMap<String, Object>();
        oldParam.put("glblCmpyCd", glblCmpyCd);
        oldParam.put("orgCd", reqBean.getOldOrgCd());
        oldParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        oldParam.put("crntSysTime", this.currentSysTime);

        Map<String, String> oldResMap = (Map<String, String>) this.ssmBatchClient.queryObject("getTerritoryRuleUpTime", oldParam);

        if (oldResMap == null) {
            setErrorMsg(rsSet, NMAM8604E, "Territory Rule Update Time Check");
            return;
        }

        Map<String, Object> newParam = new HashMap<String, Object>();
        newParam.put("glblCmpyCd", glblCmpyCd);
        newParam.put("orgCd", reqBean.getNewOrgCd());
        newParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        newParam.put("crntSysTime", this.currentSysTime);

        Map<String, String> newResMap = (Map<String, String>) this.ssmBatchClient.queryObject("getTerritoryRuleUpTime", newParam);

        if (newResMap == null) {
            setErrorMsg(rsSet, NMAM8604E, "Territory Rule Update Time Check");
            return;
        }

        String oldRuleUpTime = oldResMap.get("EZUPTIME");
        String newRuleUpTime = newResMap.get("EZUPTIME");
        String targetUpTime = null;

        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {

            POST_UPD_RQST_HDRTMsg postUpdRqstHdrTMsg = new POST_UPD_RQST_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.postUpdRqstHdrPk, reqBean.getRqstPk());

            POST_UPD_RQST_HDRTMsg outMsg = (POST_UPD_RQST_HDRTMsg) EZDTBLAccessor.findByKey(postUpdRqstHdrTMsg);
            if (outMsg == null) {
                setErrorMsg(rsSet, NMAM8604E, "Territory Rule Update Time Check");
                return;
            } else {
                targetUpTime = outMsg.ezUpTime.getValue();
            }

        } else {

            POST_UPD_WRKTMsg postUpdWrkTMsg = new POST_UPD_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.upldCsvRqstPk, reqBean.getRqstPk());
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.upldCsvRqstRowNum, reqBean.getUpldCsvRqstRowNum());

            POST_UPD_WRKTMsg outMsg = (POST_UPD_WRKTMsg) EZDTBLAccessor.findByKey(postUpdWrkTMsg);
            if (outMsg == null) {
                setErrorMsg(rsSet, NMAM8604E, "Territory Rule Update Time Check");
                return;
            } else {
                targetUpTime = outMsg.ezUpTime.getValue();
            }
        }

        if (ZYPCommonFunc.hasValue(oldRuleUpTime) && oldRuleUpTime.compareTo(targetUpTime) > 0) {
            setErrorMsg(rsSet, NMAM8602E, null);
        }

        if (ZYPCommonFunc.hasValue(newRuleUpTime) && newRuleUpTime.compareTo(targetUpTime) > 0) {
            setErrorMsg(rsSet, NMAM8603E, null);
        }
    }

    /**
     * Reassign Postal Update
     * @param reqBean PostUpdRequestBean
     * @throws SQLException
     */
    private void reassignPostalUpdate(PostUpdRequestBean reqBean) throws SQLException {

        // TRTY_RULE Insert
        insertTrtyRule(reqBean);

        // TRTY_RULE Update
        updateTrtyRule(reqBean);

        if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {

            // POST_UPD_RQST_HDR Update
            updatePostUpdPostHdr(reqBean);

            // POST_UPD_RQST_DTL Update
            updatePostUpdPostDtl(reqBean);

        } else {

            // POST_UPD_RQST_HDR Insert
            if (BigDecimal.valueOf(0).compareTo(insertPostUpdRqstHdrPk) == 0) {
                insertPostUpdRqstHdrPk = insertPostUpdPostHdr(reqBean);
            }

            // POST_UPD_RQST_DTL Insert
            insertPostUpdPostDtl(reqBean);
        }
    }

    /**
     * Insert TRTY_RULE
     * @param reqBean PostUpdRequestBean
     */
    private void insertTrtyRule(PostUpdRequestBean reqBean) {

        TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgCd, reqBean.getNewOrgCd());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, reqBean.getActiveTrtyRuleTpCd());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleFromValTxt, reqBean.getTrtyRuleFromValTxt());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleThruValTxt, reqBean.getTrtyRuleThruValTxt());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effFromDt, reqBean.getMoveEffFromDtTxt());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effThruDt, reqBean.getMoveEffThruDtTxt());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgTierCd, reqBean.getOrgTierCd());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleOprdTpCd, reqBean.getActiveTrtyRuleOprdTpCd());
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleLogicTpCd, reqBean.getActiveTrtyRuleLogicTpCd());

        EZDTBLAccessor.insert(trtyRuleTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0282E, toArray(trtyRuleTMsg.getTableName() + " :" + trtyRuleTMsg.trtyRulePk.getValue().toString()));
        }
    }

    /**
     * Update TRTY_RULE
     * @param reqBean PostUpdRequestBean
     */
    private void updateTrtyRule(PostUpdRequestBean reqBean) {

        if (!ZYPCommonFunc.hasValue(reqBean.getActiveTrtyRulePk())) {
            return;
        }

        TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, reqBean.getActiveTrtyRulePk());

        trtyRuleTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateWait(trtyRuleTMsg);

        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        String chngThruDt = ZYPDateUtil.addDays(reqBean.getMoveEffFromDtTxt(), -1);
        ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effThruDt, chngThruDt);

        EZDTBLAccessor.update(trtyRuleTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0177E, toArray(trtyRuleTMsg.getTableName() + " :" + reqBean.getActiveTrtyRulePk().toString()));
        }
    }

    /**
     * Update POST_UPD_RQST_HDR
     * @param reqBean PostUpdRequestBean
     */
    private void updatePostUpdPostHdr(PostUpdRequestBean reqBean) {

        POST_UPD_RQST_HDRTMsg postUpdRqstHdrTMsg = new POST_UPD_RQST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.postUpdRqstHdrPk, reqBean.getRqstPk());

        postUpdRqstHdrTMsg = (POST_UPD_RQST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(postUpdRqstHdrTMsg);

        if (this.isErrFlg) {
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
        }
        EZDTBLAccessor.update(postUpdRqstHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstHdrTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0177E, toArray(postUpdRqstHdrTMsg.getTableName() + " :" + reqBean.getRqstPk().toString()));
        }
    }

    /**
     * Update POST_UPD_RQST_DTL
     * @param reqBean PostUpdRequestBean
     */
    private void updatePostUpdPostDtl(PostUpdRequestBean reqBean) {

        POST_UPD_RQST_DTLTMsg postUpdRqstDtlTMsg = new POST_UPD_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstDtlPk, reqBean.getPostUpdRqstDtlPk());

        postUpdRqstDtlTMsg = (POST_UPD_RQST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(postUpdRqstDtlTMsg);

        if (this.isErrFlg) {
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
        } else {
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
        }
        EZDTBLAccessor.update(postUpdRqstDtlTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstDtlTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0177E, toArray(postUpdRqstDtlTMsg.getTableName() + " :" + reqBean.getPostUpdRqstDtlPk().toString()));
        }
    }

    /**
     * Update POST_UPD_RQST_DTL(Error Msg)
     */
    private void updatePostUpdPostDtlErrMsg() {

        for (Map<String, Object> errMap : this.trtyGrpDataMapList) {
            if (((String) errMap.get(DATA_MAP_KEY_ERR_MSG)).length() > 0) {
                POST_UPD_RQST_DTLTMsg postUpdRqstDtlTMsg = new POST_UPD_RQST_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstDtlPk, (BigDecimal) errMap.get(DATA_MAP_KEY_RQST_PK));

                postUpdRqstDtlTMsg = (POST_UPD_RQST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(postUpdRqstDtlTMsg);

                ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
                ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, (String) errMap.get(DATA_MAP_KEY_ERR_MSG));
                EZDTBLAccessor.update(postUpdRqstDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstDtlTMsg.getReturnCode())) {
                    throw new S21AbendException(NMAM0177E, toArray(postUpdRqstDtlTMsg.getTableName() + " :" + errMap.get(DATA_MAP_KEY_RQST_PK).toString()));
                }
            }
        }
    }

    /**
     * Update POST_UPD_RQST_HDR(Comment)
     * @param rqstPk BigDecimal
     */
    private void updatePostUpdPostHdrCmnt(BigDecimal rqstPk) {

        POST_UPD_RQST_HDRTMsg postUpdRqstHdrTMsg = new POST_UPD_RQST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.postUpdRqstHdrPk, rqstPk);

        postUpdRqstHdrTMsg = (POST_UPD_RQST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(postUpdRqstHdrTMsg);

        String msgTxt = createSuccessErrRcdCmnt();
        if (reqTotErrCount > 0) {
            msgTxt = S21MessageFunc.clspGetMessage(NYXM0002E, new String[] {msgTxt });
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
        } else {
            msgTxt = S21MessageFunc.clspGetMessage(NYXM0001I, new String[] {msgTxt });
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
        }
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltCmntTxt, msgTxt);

        EZDTBLAccessor.update(postUpdRqstHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstHdrTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0177E, toArray(postUpdRqstHdrTMsg.getTableName() + " :" + rqstPk));
        }
    }

    /**
     * Create Success Error Recode Comment
     * @return String
     */
    private String createSuccessErrRcdCmnt() {

        StringBuilder sbMsgTxt = new StringBuilder();
        sbMsgTxt.append(reqTotNmlCount + reqTotErrCount);
        sbMsgTxt.append(" records successfully created ");
        sbMsgTxt.append(reqTotNmlCount);
        sbMsgTxt.append(" records successfully updated.");

        if (reqTotErrCount > 0) {
            sbMsgTxt.append(SPACE);
            sbMsgTxt.append(reqTotErrCount);
            sbMsgTxt.append(" records skipped/errored.");
        }
        return sbMsgTxt.toString();
    }

    /**
     * Update POST_UPD_WRK(Error Msg)
     */
    private void updatePostUpdWrkErrMsg() {

        for (Map<String, Object> errMap : trtyGrpDataMapList) {
            BigDecimal rqstPk = (BigDecimal) errMap.get(DATA_MAP_KEY_RQST_PK);
            List<List<String>> msgList = (List<List<String>>) errMap.get(DATA_MAP_KEY_ERR_MSG_LIST);

            if (msgList != null) {
                for (List<String> msgIdList : msgList) {

                    String msgId = msgIdList.get(0);
                    String msgStr = null;
                    if (msgIdList.size() > 1) {
                        msgStr = msgIdList.get(1);
                    }
                    messenger.addMessageForRecord(rqstPk, msgId, msgStr);
                }
            }
        }
        this.messenger.uploadMessage();
    }

    /**
     * Insert POST_UPD_RQST_HDR
     * @param reqBean PostUpdRequestBean
     * @return BigDecimal
     */
    private BigDecimal insertPostUpdPostHdr(PostUpdRequestBean reqBean) {

        POST_UPD_RQST_HDRTMsg postUpdRqstHdrTMsg = new POST_UPD_RQST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal hdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.POST_UPD_RQST_HDR_SQ);
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.postUpdRqstHdrPk, hdrPk);
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstUsrId, reqBean.getRqstUserId());
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.CSV_UPLOAD);

        String msgTxt = createSuccessErrRcdCmnt();
        if (reqTotErrCount > 0) {
            msgTxt = S21MessageFunc.clspGetMessage(NYXM0002E, new String[] {msgTxt });
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
        } else {
            msgTxt = S21MessageFunc.clspGetMessage(NYXM0001I, new String[] {msgTxt });
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
        }
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltCmntTxt, msgTxt);

        EZDTBLAccessor.insert(postUpdRqstHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstHdrTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0282E, toArray(postUpdRqstHdrTMsg.getTableName() + " :" + hdrPk.toString()));
        }

        return hdrPk;
    }

    /**
     * Insert POST_UPD_RQST_DTL
     * @param reqBean PostUpdRequestBean
     */
    private void insertPostUpdPostDtl(PostUpdRequestBean reqBean) {

        POST_UPD_RQST_DTLTMsg postUpdRqstDtlTMsg = new POST_UPD_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.POST_UPD_RQST_DTL_SQ));

        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstHdrPk, insertPostUpdRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.trtyRuleOprdTpDescTxt, reqBean.getTrtyRuleOprdTpDescTxt());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.trtyRuleFromValTxt, reqBean.getTrtyRuleFromValTxt());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.trtyRuleThruValTxt, reqBean.getTrtyRuleThruValTxt());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.oldOrgCd, reqBean.getOldOrgCd());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.newOrgCd, reqBean.getNewOrgCd());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.moveEffFromDtTxt, reqBean.getMoveEffFromDtTxt());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.moveEffThruDtTxt, reqBean.getMoveEffThruDtTxt());
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.massUpdRsnCmntTxt, reqBean.getMassUpdRsnCmntTxt());

        if (isErrFlg) {
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, this.onlineMsg.toString());
        } else {
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
        }
        EZDTBLAccessor.insert(postUpdRqstDtlTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstDtlTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0282E, toArray(postUpdRqstDtlTMsg.getTableName() + " :" + insertPostUpdRqstHdrPk.toString()));
        }
    }

    /**
     * Update Another Error Message Online
     * @param errSetPkList List<BigDecimal>
     */
    private void updateAnotherErrMessageOnline(List<BigDecimal> errSetPkList) {

        for (BigDecimal dtlPk : errSetPkList) {
            POST_UPD_RQST_DTLTMsg postUpdRqstDtlTMsg = new POST_UPD_RQST_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstDtlPk, dtlPk);

            postUpdRqstDtlTMsg = (POST_UPD_RQST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(postUpdRqstDtlTMsg);

            if (!ZYPCommonFunc.hasValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt.getValue())) {
                String msgTxt = S21MessageFunc.clspGetMessage(NMAM8609E, null);
                ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, msgTxt);
                ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);

                EZDTBLAccessor.update(postUpdRqstDtlTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstDtlTMsg.getReturnCode())) {
                    throw new S21AbendException(NMAM0177E, toArray(postUpdRqstDtlTMsg.getTableName() + " :" + dtlPk));
                }
            }
        }
    }

    /**
     * Update Another Error Message CSV
     * @param errSetPkList List<BigDecimal>
     * @param upldCsvRqstPk BigDecimal
     */
    private void updateAnotherErrMessageCsvUpload(List<BigDecimal> errSetPkList, BigDecimal upldCsvRqstPk) {

        for (BigDecimal dtlPk : errSetPkList) {

            POST_UPD_WRKTMsg postUpdWrkTMsg = new POST_UPD_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.upldCsvRqstPk, upldCsvRqstPk);
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.upldCsvRqstRowNum, dtlPk);

            postUpdWrkTMsg = (POST_UPD_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(postUpdWrkTMsg);

            String msgTxt = S21MessageFunc.clspGetMessage(NMAM8609E, null);
            ZYPEZDItemValueSetter.setValue(postUpdWrkTMsg.upldCsvRqstCmntTxt, msgTxt);

            EZDTBLAccessor.update(postUpdWrkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdWrkTMsg.getReturnCode())) {
                throw new S21AbendException(NMAM0177E, toArray(postUpdWrkTMsg.getTableName() + " :" + upldCsvRqstPk.toString()));
            }

            for (Map<String, Object> dataMap : trtyGrpDataMapList) {
                if (dtlPk.compareTo((BigDecimal) dataMap.get(DATA_MAP_KEY_RQST_PK)) == 0) {
                    dataMap.put(DATA_MAP_KEY_ERR_MSG, msgTxt);
                    break;
                }
            }
        }
    }

    /**
     * Insert Post Update Request
     * @param reqBean PostUpdRequestBean
     * @throws SQLException
     */
    private void insertPostUpdRqst(PostUpdRequestBean reqBean) throws SQLException {

        // POST_UPD_RQST_HDR Insert
        insertPostUpdPostHdrCmntAfterApi(reqBean);

        for (Map<String, Object> dataMap : trtyGrpDataMapList) {

            // POST_UPD_RQST_DTL Insert
            insertPostUpdPostDtlCmntAfterApi(dataMap);
        }
    }

    /**
     * Insert POST_UPD_RQST_HDR(After API)
     * @param reqBean PostUpdRequestBean
     */
    private void insertPostUpdPostHdrCmntAfterApi(PostUpdRequestBean reqBean) {

        POST_UPD_RQST_HDRTMsg postUpdRqstHdrTMsg = new POST_UPD_RQST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.glblCmpyCd, this.glblCmpyCd);

        if (BigDecimal.valueOf(0).compareTo(insertPostUpdRqstHdrPk) == 0) {
            insertPostUpdRqstHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.POST_UPD_RQST_HDR_SQ);
        }

        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.postUpdRqstHdrPk, insertPostUpdRqstHdrPk);

        POST_UPD_RQST_HDRTMsg outMsg = (POST_UPD_RQST_HDRTMsg) EZDTBLAccessor.findByKey(postUpdRqstHdrTMsg);
        if (outMsg != null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstUsrId, reqBean.getRqstUserId());
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstCratTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.CSV_UPLOAD);

        String msgTxt = createSuccessErrRcdCmnt();
        if (reqTotErrCount > 0) {
            msgTxt = S21MessageFunc.clspGetMessage(NYXM0002E, new String[] {msgTxt });
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED_INCLUDING_ERROR);
        } else {
            msgTxt = S21MessageFunc.clspGetMessage(NYXM0001I, new String[] {msgTxt });
            ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltTpCd, RQST_RSLT_TP.COMPLETED);
        }
        ZYPEZDItemValueSetter.setValue(postUpdRqstHdrTMsg.rqstRsltCmntTxt, msgTxt);
        EZDTBLAccessor.insert(postUpdRqstHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstHdrTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0282E, toArray(postUpdRqstHdrTMsg.getTableName() + " :" + insertPostUpdRqstHdrPk.toString()));
        }
    }

    /**
     * Insert POST_UPD_RQST_DTL(After API)
     * @param dataMap Map<String, Object>
     */
    private void insertPostUpdPostDtlCmntAfterApi(Map<String, Object> dataMap) {

        POST_UPD_RQST_DTLTMsg postUpdRqstDtlTMsg = new POST_UPD_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.POST_UPD_RQST_DTL_SQ));

        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.postUpdRqstHdrPk, insertPostUpdRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.trtyRuleOprdTpDescTxt, (String) dataMap.get(DATA_MAP_KEY_DTL_TRTY_RULE_OPRD_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.trtyRuleFromValTxt, (String) dataMap.get(DATA_MAP_KEY_DTL_TRTY_RULE_FROM_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.trtyRuleThruValTxt, (String) dataMap.get(DATA_MAP_KEY_DTL_TRTY_RULE_THRU_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.oldOrgCd, (String) dataMap.get(DATA_MAP_KEY_OLD_ORG_CD));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.newOrgCd, (String) dataMap.get(DATA_MAP_KEY_NEW_ORG_CD));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.moveEffFromDtTxt, (String) dataMap.get(DATA_MAP_KEY_DTL_MOVE_EFF_FROM_DT_TXT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.moveEffThruDtTxt, (String) dataMap.get(DATA_MAP_KEY_DTL_MOVE_EFF_THRU_DT_TXT));
        ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.massUpdRsnCmntTxt, (String) dataMap.get(DATA_MAP_KEY_DTL_MASS_UPD_RSN_CMNT_TXT));

        if (ZYPCommonFunc.hasValue((String) dataMap.get(DATA_MAP_KEY_ERR_MSG))) {
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.ERROR);
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, (String) dataMap.get(DATA_MAP_KEY_ERR_MSG));
        } else {
            String msgTxt = S21MessageFunc.clspGetMessage(NMAM8609E, null);
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltCmntTxt, msgTxt);
            ZYPEZDItemValueSetter.setValue(postUpdRqstDtlTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);
        }

        EZDTBLAccessor.insert(postUpdRqstDtlTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(postUpdRqstDtlTMsg.getReturnCode())) {
            throw new S21AbendException(NMAM0282E, toArray(postUpdRqstDtlTMsg.getTableName() + " :" + postUpdRqstDtlTMsg.postUpdRqstDtlPk.getValue().toString()));
        }
    }

    /**
     * Get DS_ORG_UNIT Information
     * @param reqBean PostUpdRequestBean
     * @throws SQLException
     */
    private void getDsOrgUnitInfo(PostUpdRequestBean reqBean) throws SQLException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", reqBean.getNewOrgCd());

        Map<String, Object> trtyInfoMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsOrgUnitInfo", param);

        reqBean.setOrgTierCd((String) trtyInfoMap.get("ORG_TIER_CD"));
    }

    /**
     * Set Rule Validation API Parameter
     * @param reqBean PostUpdRequestBean
     * @param apiParamMsgList List<NMZC270001PMsg>
     * @param orgCd String
     * @throws SQLException
     */
    private void setRuleValidationApiParam(PostUpdRequestBean reqBean, List<NMZC270001PMsg> apiParamMsgList, String orgCd) throws SQLException {

        for (NMZC270001PMsg checkPMsg : apiParamMsgList) {
            if (orgCd.equals(checkPMsg.orgCd.getValue())) {
                apiParamMsgList.remove(checkPMsg);
                break;
            }
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("orgCd", orgCd);
        param.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        param.put("moveEffFromDt", reqBean.getMoveEffFromDtTxt());
        param.put("moveEffThruDt", reqBean.getMoveEffThruDtTxt());

        List<Map<String, Object>> trtyInfoMapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTerritoryInfoList", param);

        if (trtyInfoMapList.size() > 0) {
            NMZC270001PMsg pMsg = new NMZC270001PMsg();
            for (int i = 0; i < trtyInfoMapList.size(); i++) {

                Map<String, Object> trtyMap = trtyInfoMapList.get(i);

                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.orgCd, (String) trtyMap.get("ORG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.firstOrgCd, (String) trtyMap.get("FIRST_ORG_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.trtyTpCd, (String) trtyMap.get("TRTY_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.orgTierCd, (String) trtyMap.get("ORG_TIER_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.orgStruTpCd, (String) trtyMap.get("ORG_STRU_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.trtyGrpTpCd, (String) trtyMap.get("TRTY_GRP_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(pMsg.effFromDt_T, (String) trtyMap.get("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);

                    if (ZYPCommonFunc.hasValue((String) trtyMap.get("EFF_THRU_DT"))) {
                        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt_T, (String) trtyMap.get("EFF_THRU_DT"));
                    }
                }

                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).trtyRuleTpCd, (String) trtyMap.get("TRTY_RULE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).trtyRuleOprdTpCd, (String) trtyMap.get("TRTY_RULE_OPRD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).trtyRuleFromValTxt, (String) trtyMap.get("TRTY_RULE_FROM_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).trtyRuleThruValTxt, (String) trtyMap.get("TRTY_RULE_THRU_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).trtyRuleLogicTpCd, (String) trtyMap.get("TRTY_RULE_LOGIC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).effFromDt_R, (String) trtyMap.get("TRTY_RULE_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(pMsg.trtyRuleList.no(i).effThruDt_R, (String) trtyMap.get("TRTY_RULE_EFF_THRU_DT"));
                pMsg.trtyRuleList.setValidCount(i + 1);
            }
            apiParamMsgList.add(pMsg);
        }
    }

    /**
     * Call Rule Validation API
     * @param apiParamMsgList List<NMZC270001PMsg>
     * @param reqBean PostUpdRequestBean
     * @param errSetPkList List<BigDecimal>
     * @return boolean
     * @throws SQLException
     */
    private boolean callRuleValidationApi(List<NMZC270001PMsg> apiParamMsgList, PostUpdRequestBean reqBean, List<BigDecimal> errSetPkList) throws SQLException {

        if (apiParamMsgList.size() == 0) {
            return true;
        }

        boolean isSuccessFlg = true;
        NMZC270001 ruleValidationApi = new NMZC270001();
        for (NMZC270001PMsg pMsg : apiParamMsgList) {

            ruleValidationApi.execute(pMsg, ONBATCH_TYPE.BATCH);

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int j = 0; j < msgList.size(); j++) {
                S21ApiMessage msg = msgList.get(j);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                if (msgPrms != null && msgPrms.length > 0) {
                    setApiErrorMsg(reqBean, msgId, pMsg.orgCd.getValue(), errSetPkList, msgPrms[0]);
                } else {
                    setApiErrorMsg(reqBean, msgId, pMsg.orgCd.getValue(), errSetPkList, null);
                }
                // 2017/11/16 CSA-QC#20597 Add Start
                if (msgId.endsWith(MESSAGE_KIND_ERROR)) {
                    isSuccessFlg = false;
                }
                // 2017/11/16 CSA-QC#20597 Add End
            }
        }
        return isSuccessFlg;
    }

    /**
     * Get Upload CSV ID
     * @param fMsg ART10FMsg
     * @return String
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();

        if (S21StringUtil.isEmpty(uploadCsvId)) {
            String[] msg = {UPLD_CSV_ID };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * Get Upload CSV Request PK
     * @param fMsg ART10FMsg
     * @return BigDecimal
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {UPLD_CSV_RQST_PK };
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
     * Set Error Message
     * @param rsSet ResultSet
     * @param msgId String
     * @param msgStr String
     */
    private void setErrorMsg(ResultSet rsSet, String msgId, String msgStr) throws SQLException {

        if (RQST_CRAT_SYS_TP.CSV_UPLOAD.equals(this.userConstantVariable)) {
            messenger.addMessageForRecord(rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), msgId, msgStr);
        }

        String msgTxt = null;
        if (ZYPCommonFunc.hasValue(msgStr)) {
            msgTxt = S21MessageFunc.clspGetMessage(msgId, new String[] {msgStr });
            S21InfoLogOutput.println(msgId, toArray(msgStr));
        } else {
            msgTxt = S21MessageFunc.clspGetMessage(msgId);
            S21InfoLogOutput.println(msgId);
        }

        this.termCd = TERM_CD.WARNING_END;

        if (this.onlineMsg.length() > 0) {
            this.onlineMsg.append(COMMA);
        }
        this.onlineMsg.append(msgTxt);
        this.trtyGrpErrCount++;
        this.isErrFlg = true;
    }

    /**
     * Set API Error Message
     * @param reqBean PostUpdRequestBean
     * @param msgId String
     * @param orgCd String
     * @param errSetPkList List<BigDecimal>
     * @param msgParam String
     */
    private void setApiErrorMsg(PostUpdRequestBean reqBean, String msgId, String orgCd, List<BigDecimal> errSetPkList, String msgParam) throws SQLException {

        String msgTxt = null;
        if (ZYPCommonFunc.hasValue(msgParam)) {
            msgTxt = S21MessageFunc.clspGetMessage(msgId, new String[] {msgParam });
        } else {
            msgTxt = S21MessageFunc.clspGetMessage(msgId);
        }

        for (Map<String, Object> errMap : trtyGrpDataMapList) {

            BigDecimal delPk = (BigDecimal) errMap.get(DATA_MAP_KEY_RQST_PK);
            if (RQST_CRAT_SYS_TP.ONLINE.equals(this.userConstantVariable)) {
                if (orgCd.equals(errMap.get(DATA_MAP_KEY_OLD_ORG_CD))) {
                    setApiErrorMsgCombiOnlineMsg(errMap, msgTxt);
                    errSetPkList.remove(delPk);
                } else if (orgCd.equals(errMap.get(DATA_MAP_KEY_NEW_ORG_CD))) {
                    setApiErrorMsgCombiOnlineMsg(errMap, msgTxt);
                    errSetPkList.remove(delPk);
                }
            } else {
                if (orgCd.equals(errMap.get(DATA_MAP_KEY_OLD_ORG_CD))) {
                    setApiErrorMsgCombiOnlineMsg(errMap, msgTxt);
                    setApiErrorMsgCombiCsv(errMap, msgId, msgParam);
                    errSetPkList.remove(delPk);
                } else if (orgCd.equals(errMap.get(DATA_MAP_KEY_NEW_ORG_CD))) {
                    setApiErrorMsgCombiOnlineMsg(errMap, msgTxt);
                    setApiErrorMsgCombiCsv(errMap, msgId, msgParam);
                    errSetPkList.remove(delPk);
                }
            }
        }

        S21InfoLogOutput.println(msgId);
        // 2017/11/16 CSA-QC#20597 Add Start
        if (msgId.endsWith(MESSAGE_KIND_ERROR)) {
            this.termCd = TERM_CD.WARNING_END;
            this.trtyGrpErrCount++;
        }
        // 2017/11/16 CSA-QC#20597 Add End
    }

    /**
     * Set Error Message Combination Online
     * @param errMap Map<String, Object>
     * @param msgTxt String
     */
    private void setApiErrorMsgCombiOnlineMsg(Map<String, Object> errMap, String msgTxt) {

        StringBuilder errMsg = new StringBuilder((String) errMap.get(DATA_MAP_KEY_ERR_MSG));
        if (errMsg.length() > 0) {
            errMsg.append(COMMA);
        }
        errMsg.append(msgTxt);
        errMap.put(DATA_MAP_KEY_ERR_MSG, errMsg.toString());
    }

    /**
     * Set Error Message Combination CSV
     * @param errMap Map<String, Object>
     * @param msgId String
     * @param msgParam String
     */
    private void setApiErrorMsgCombiCsv(Map<String, Object> errMap, String msgId, String msgParam) {

        List<List<String>> errList = (List<List<String>>) errMap.get(DATA_MAP_KEY_ERR_MSG_LIST);

        if (errList != null) {
            List<String> errAry = new ArrayList<String>();
            errAry.add(msgId);
            if (ZYPCommonFunc.hasValue(msgParam)) {
                errAry.add(msgParam);
            }
            errList.add(errAry);
        }
    }

    /**
     * <pre>
     * To Array
     * </pre>
     * @param appendMsgList String[]
     * @return String[]
     */
    private String[] toArray(String... appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

}
