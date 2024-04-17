/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB266001;

import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_CNTY_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_FIRST_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_SCD_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.API_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.APP_FUNC_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.DEF_DS_ACCT_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.DEF_DS_ACCT_ITRL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_ACCT;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_CTAC_PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_CTAC_PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_CTAC_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_DS_CTAC_PNT_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_DS_CTAC_PSN_SALT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_DS_CTAC_PSN_TTL_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_INFO;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_PROS_CTAC_CELL_PHO_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_PROS_CTAC_EML_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_PROS_CTAC_FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_PROS_CTAC_PHO_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_TRTY_ASG_RESRC;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM0010E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM0072E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8414E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8461E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8559I;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8563E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8564E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8565E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8566E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8572E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8573I;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMAM8597I;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NMZM0179W;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_CTAC_PNT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_CTAC_PSN_SALT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_CTAC_PSN_TTL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_CTAC_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ORIG_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.PARAM_LOC_PMSG;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.PARAM_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.PARAM_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.RTNCD_NORMAL;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.SUBS_LEN_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.SUBS_LEN_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.UPLD_CSV_RQST_ROW_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ZYPM0181E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ZZZM9013E;
import static com.canon.cusa.s21.batch.NMA.NMAB266001.Constant.NMAB266001Constant.ZZZM9026E;

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
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DEF_DPLY_COA_INFOTMsgArray;
import business.db.PROS_UPLD_WRKTMsg;
import business.db.TRTY_RULETMsg;
import business.db.TRTY_RULETMsgArray;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;
import business.parts.NMZC001002_xxMsgIdListPMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsgArray;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * NMAB266001 Prospect Upload Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         C.Tanaka        Create          N/A
 * 2016/06/29   Fujitsu         H.Ikeda         Update          QC#10859
 * 2016/07/01   Fujitsu         M.Ohno          Update          QC#11147
 * 2016/09/16   Fujitsu         C.Yokoi         Update          QC#8156
 * 2016/10/17   Hitachi         T.Mizuki        Update          QC#14721
 *</pre>
 */
public class NMAB266001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Process Date */
    private String procDt = null;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Process Count */
    private int procCount = 0;

    /** Error Count */
    private int errCount = 0;
    // mod start 2016/10/17 CSA QC#14721
    /** Current Error Count */
    private int currentError = 0;
    // mod end 2016/10/17 CSA QC#14721
    /** Warning Flag */
    private boolean warnFlg = false;

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        batchHelper = new S21RequestBatchHelper();
    }

    @Override
    protected void mainRoutine() {

        glblCmpyCd = getGlobalCompanyCode();
        procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            this.doProcess(request);
        }

    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, procCount, errCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB266001().executeBatch(NMAB266001.class.getSimpleName());
    }

    protected void doProcess(ART10FMsg fMsg) {

        // Upload ID
        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, UPLD_CSV_ID + upldCsvId, this);

        // Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        EZDDebugOutput.println(0, UPLD_CSV_RQST_PK + upldCsvRqstPk, this);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        BigDecimal csvReqPk = upldCSVRqstTMsg.upldCsvRqstPk.getValue();
        // mod start 2016/10/20 CSA QC#14721
        this.currentError = 0;
        // mod end 2016/10/20 CSA QC#14721
        // check error
        checkErr(csvReqPk);

        // register Prospect info
        saveProsInfo(csvReqPk);

        String msgValue = null;
        // mod start 2016/10/17 CSA QC#14721
        if (this.currentError == 0) {
        // mod end 2016/10/17 CSA QC#14721
            batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
            // 2016/09/16 CSA-QC#8156 Add Start
            msgValue = this.procCount + MSG_INFO;
            this.messenger.addMessageForFile(NYXM0001I, msgValue);
            // 2016/09/16 CSA-QC#8156 Add End
        } else {
            termCd = TERM_CD.WARNING_END;
            batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
            // 2016/09/16 CSA-QC#8156 Mod Start
            // mod start 2016/10/24 CSA QC#15496 
            if (this.procCount > 0) {
                msgValue = this.procCount + MSG_INFO + " " + this.currentError + MSG_ERR;
            } else {
                msgValue = this.currentError + MSG_ERR;
            }
            // mod end 2016/10/24 CSA QC#15496
            this.messenger.addMessageForFile(NYXM0002E, msgValue);
            // this.messenger.addMessageForFile(NMAM8463E, null);
            // 2016/09/16 CSA-QC#8156 Mod End
        }
        this.messenger.uploadMessage();
        commit();
    }

    private void saveProsInfo(BigDecimal upldCsvRqstPk) {

        PreparedStatement prosWrk = null;
        ResultSet prosRs = null;

        try {
            // get non error work data
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> prosWrkMap = new HashMap<String, Object>();
            prosWrkMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
            prosWrkMap.put(PARAM_UPLD_CSV_RQST_PK, upldCsvRqstPk);
            prosWrkMap.put("cmntFlg", ZYPConstant.FLG_ON_Y);
            prosWrk = ssmLLClient.createPreparedStatement("getProsUpldWrk", prosWrkMap, execParam);
            prosRs = prosWrk.executeQuery();
            PROS_UPLD_WRKTMsg wrkTMsg = null;

            int currentNormal = 0;
            // mod start 2016/10/17 CSA QC#14721
//            this.currentError = 0;
            // mod end 2016/10/17 CSA QC#14721
            boolean err = false;
            String acctNm = null;
            String prevAcctNm = null;
            int index = 0;
            NMZC001001PMsg pMsg = null;
            List<BigDecimal> errRowNum = new ArrayList<BigDecimal>();
            List<Map<String, Object>> locList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> ctacList = new ArrayList<Map<String, Object>>();

            while (prosRs.next()) {
                acctNm = prosRs.getString(DS_ACCT_NM);
                wrkTMsg = setProsUpldWrkTMsg(prosRs);

                if (!acctNm.equals(prevAcctNm)) {
                    if (pMsg != null) {
                        pMsg.NMZC001002PMsg.setValidCount(index);
                        if (callCustUpdateApi(pMsg, locList, upldCsvRqstPk)) {
                            // Mod Start 2016/06/29 QC#10859
                            if (ctacList.size() != 0) {
                                if (callCtacUpdateApi(ctacList, pMsg.dsAcctNum.getValue()) && insertTrtyRule(locList)) {
                                    // mod start 2016/10/24 CSA QC#15496
//                                    commit();
//                                    currentNormal += pMsg.NMZC001002PMsg.getValidCount();
                                    // mod end 2016/10/24 CSA QC#15496
                                } else {
                                    err = true;
                                }
                            }
                            // Mod End 2016/06/29 QC#10859
                        } else {
                            err = true;
                            if (warnFlg) {
                                commit();
                            }
                        }
                        if (err) {
                            if (!warnFlg) {
                                rollback();
                            }
                            currentError += pMsg.NMZC001002PMsg.getValidCount();
                            setAcctErrMsg(upldCsvRqstPk, errRowNum);
                        // mod start 2016/10/24 CSA QC#15496
                        } else {
                            commit();
                            currentNormal += pMsg.NMZC001002PMsg.getValidCount();
                        }
                        // mod end 2016/10/24 CSA QC#15496
                        // reset flag
                        pMsg = null;
                        err = false;
                        index = 0;
                        locList.clear();
                        ctacList.clear();
                        errRowNum.clear();
                    }
                    if (pMsg == null) {
                        pMsg = setNMZC001001PMsg(wrkTMsg);
                    }
                }

                locList.add(setNMZC001002PMsg(pMsg, index, wrkTMsg));
                // Mod Start 2016/06/29 QC#10859
                if (nmChk(wrkTMsg)) {
                    ctacList.add(setNMZC002001PMsg(index, wrkTMsg, prosRs));
                }
                // Mod End   2016/06/29 QC#10859
                index++;

                prevAcctNm = acctNm;
                errRowNum.add(prosRs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
            }
            // execute for the last account
            if (pMsg != null) {
                pMsg.NMZC001002PMsg.setValidCount(index);
                if (callCustUpdateApi(pMsg, locList, upldCsvRqstPk)) {
                    // Mod Start 2016/06/29 QC#10859
                    if (ctacList.size() != 0) {
                        if (callCtacUpdateApi(ctacList, pMsg.dsAcctNum.getValue()) && insertTrtyRule(locList)) {
                            // mod start 2016/10/24 CSA QC#15496
//                          commit();
//                          currentNormal += pMsg.NMZC001002PMsg.getValidCount();
                            // mod end 2016/10/24 CSA QC#15496
                        } else {
                            err = true;
                        }
                    }
                    // Mod End   2016/06/29 QC#10859
                } else {
                    err = true;
                    if (warnFlg) {
                        commit();
                    }
                }
                if (err) {
                    if (!warnFlg) {
                        rollback();
                    }
                    currentError += pMsg.NMZC001002PMsg.getValidCount();
                    setAcctErrMsg(upldCsvRqstPk, errRowNum);
                // mod start 2016/10/24 CSA QC#15496
                } else {
                    commit();
                    currentNormal += pMsg.NMZC001002PMsg.getValidCount();
                }
                // mod end 2016/10/24 CSA QC#15496
            }

            this.procCount = this.procCount + currentNormal;
            this.errCount = this.errCount + currentError;

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prosWrk, prosRs);
        }
    }

    // Add Start 2016/06/29 QC#10859
    private boolean nmChk(PROS_UPLD_WRKTMsg wrkTMsg) {
        if (!ZYPCommonFunc.hasValue(wrkTMsg.ctacPsnFirstNm) && !ZYPCommonFunc.hasValue(wrkTMsg.ctacPsnLastNm)) {
            return false;
        } else {
            return true;
        }
    }
    // Add End 2016/06/29 QC#10859

    private void checkErr(BigDecimal upldCsvRqstPk) {

        PreparedStatement prosWrk = null;
        ResultSet prosRs = null;

        try {
            // check duplicate date from work table
            List<BigDecimal> errDupList = checkDupProsWrk(upldCsvRqstPk);

            // get data from work table
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            Map<String, Object> prosWrkMap = new HashMap<String, Object>();
            prosWrkMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
            prosWrkMap.put(PARAM_UPLD_CSV_RQST_PK, upldCsvRqstPk);

            prosWrk = ssmLLClient.createPreparedStatement("getProsUpldWrk", prosWrkMap, execParam);
            prosRs = prosWrk.executeQuery();
            PROS_UPLD_WRKTMsg wrkTMsg = null;

            int currentError = 0;
            int cnt = 0;
            String acctNm = null;
            String prevAcctNm = null;
            boolean errFlg = false;
            boolean acctErr = false;
            List<BigDecimal> errRowNum = new ArrayList<BigDecimal>();

            while (prosRs.next()) {
                acctNm = prosRs.getString(DS_ACCT_NM);
                wrkTMsg = setProsUpldWrkTMsg(prosRs);

                if ((acctNm != null && !acctNm.equals(prevAcctNm)) || acctNm == null) {
                    if (acctErr) {
                        // set Account error message
                        setAcctErrMsg(upldCsvRqstPk, errRowNum);
                        currentError += cnt;
                    }
                    // reset error flag
                    acctErr = false;
                    errFlg = false;
                    cnt = 0;
                    errRowNum.clear();
                }

                // error if duplicate error occurs
                if (errDupList != null && errDupList.contains(prosRs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM))) {
                    errFlg = true;
                }
                // error check
                if (hasValueErr(wrkTMsg, prosRs)) {
                    errFlg = true;
                }
                if (hasDupS21Loc(wrkTMsg)) {
                    errFlg = true;
                }

                if (errFlg) {
                    acctErr = true;
                }
                prevAcctNm = acctNm;
                cnt++;
                errRowNum.add(prosRs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
            }
            // execute the last account
            if (acctErr) {
                // set Account error message
                setAcctErrMsg(upldCsvRqstPk, errRowNum);
                currentError += cnt;
            }

            if (currentError > 0) {
                // mod start 2016/10/24 CSA QC#15496
//                this.errCount = this.errCount + currentError;
                // mod start 2016/10/20 CSA QC#14721
                this.currentError = this.currentError + currentError;
                // mod end 2016/10/20 CSA QC#14721
                this.messenger.uploadMessage();
                commit();
                // mod end 2016/10/24 CSA QC#15496
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prosWrk, prosRs);
        }
    }

    private List<BigDecimal> checkDupProsWrk(BigDecimal upldCsvRqstPk) {

        List<BigDecimal> errRowNum = new ArrayList<BigDecimal>();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> dupWrkMap = new HashMap<String, Object>();
        dupWrkMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        dupWrkMap.put(PARAM_UPLD_CSV_RQST_PK, upldCsvRqstPk);
        dupWrkMap.put("acctNmLen", SUBS_LEN_ACCT_NM);
        dupWrkMap.put("addrLen", SUBS_LEN_ADDR);

        // check duplicate
        List<Map<String, Object>> acctDup = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDupProsUpldWrk", dupWrkMap, execParam);
        for (Map<String, Object> map : acctDup) {
            BigDecimal rowNum = (BigDecimal) map.get(UPLD_CSV_RQST_ROW_NUM);
            this.messenger.addMessageForRecord(rowNum, NMAM0072E, MSG_ACCT);
            errRowNum.add(rowNum);
        }

        // check if account is same
        List<Map<String, Object>> dunsDup = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDiffProsUpldWrk", dupWrkMap, execParam);
        for (Map<String, Object> map : dunsDup) {
            BigDecimal rowNum = (BigDecimal) map.get(UPLD_CSV_RQST_ROW_NUM);
            this.messenger.addMessageForRecord(rowNum, NMAM8564E, null);
            if (!errRowNum.contains(rowNum)) {
                errRowNum.add(rowNum);
            }
        }

        // check contact
        List<Map<String, Object>> ctacDup = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDupContact", dupWrkMap, execParam);
        for (Map<String, Object> map : ctacDup) {
            BigDecimal rowNum = (BigDecimal) map.get(UPLD_CSV_RQST_ROW_NUM);
            this.messenger.addMessageForRecord(rowNum, NMAM8565E, null);
            if (!errRowNum.contains(rowNum)) {
                errRowNum.add(rowNum);
            }
        }

        return errRowNum;
    }

    private boolean hasValueErr(PROS_UPLD_WRKTMsg tMsg, ResultSet rs) throws SQLException {

        boolean errFlg = false;

        // Mandatory
        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        if (!ZYPCommonFunc.hasValue(tMsg.dsAcctNm.getValue())) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_DS_ACCT_NM);
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.firstLineAddr.getValue())) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_FIRST_LINE_ADDR);
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.ctyAddr.getValue())) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_CTY_ADDR);
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.stCd.getValue())) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_ST_CD);
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.postCd.getValue())) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_POST_CD);
            errFlg = true;
        }

        // Master
        if (!ZYPCommonFunc.hasValue(rs.getString(ORIG_CTRY_CD))) {
            this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_CTRY_CD);
            errFlg = true;
        }
        if (ZYPCommonFunc.hasValue(tMsg.lineBizTpCd.getValue()) && !ZYPCommonFunc.hasValue(rs.getString(ORIG_LINE_BIZ_TP_CD))) {
            this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_LINE_BIZ_TP_CD);
            errFlg = true;
        }
        if (ZYPCommonFunc.hasValue(tMsg.stCd.getValue()) && !ZYPCommonFunc.hasValue(rs.getString(ORIG_ST_CD))) {
            this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_ST_CD);
            errFlg = true;
        }

        // Account
        if (ZYPCommonFunc.hasValue(rs.getString(ORIG_ACCT_NM))) {
            this.messenger.addMessageForRecord(rowNum, NMAM0010E, MSG_ACCT);
            errFlg = true;
        }

        // Contact
        if (ZYPCommonFunc.hasValue(tMsg.ctacPsnFirstNm.getValue()) || ZYPCommonFunc.hasValue(tMsg.ctacPsnLastNm.getValue())) {
            // Mandatory
            if (!ZYPCommonFunc.hasValue(tMsg.ctacPsnFirstNm.getValue())) {
                this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_CTAC_PSN_FIRST_NM);
                errFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(tMsg.ctacPsnLastNm.getValue())) {
                this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_CTAC_PSN_LAST_NM);
                errFlg = true;
            }

            // Code
            if (!ZYPCommonFunc.hasValue(rs.getString(ORIG_CTAC_TP_CD))) {
                this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_CTAC_TP_DESC_TXT);
                errFlg = true;
            }
            if (ZYPCommonFunc.hasValue(tMsg.dsCtacPsnSaltDescTxt.getValue()) && !ZYPCommonFunc.hasValue(rs.getString(ORIG_CTAC_PSN_SALT_CD))) {
                this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_DS_CTAC_PSN_SALT_DESC_TXT);
                errFlg = true;
            }
            if (ZYPCommonFunc.hasValue(tMsg.dsCtacPsnTtlDescTxt.getValue()) && !ZYPCommonFunc.hasValue(rs.getString(ORIG_CTAC_PSN_TTL_CD))) {
                this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_DS_CTAC_PSN_TTL_DESC_TXT);
                errFlg = true;
            }
            String pntTp = rs.getString(ORIG_CTAC_PNT_TP_CD);
            if (!ZYPCommonFunc.hasValue(pntTp)) {
                this.messenger.addMessageForRecord(rowNum, NMAM8414E, MSG_DS_CTAC_PNT_TP_DESC_TXT);
                errFlg = true;
            } else {
                // Contact Point
                if (pntTp.equals(DS_CTAC_PNT_TP.PHONE_WORK) && !ZYPCommonFunc.hasValue(tMsg.prosCtacPhoNum)) {
                    this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_PROS_CTAC_PHO_NUM);
                    errFlg = true;
                }
                if (pntTp.equals(DS_CTAC_PNT_TP.PHONE_MOBILE) && !ZYPCommonFunc.hasValue(tMsg.prosCtacCellPhoNum)) {
                    this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_PROS_CTAC_CELL_PHO_NUM);
                    errFlg = true;
                }
                if (pntTp.equals(DS_CTAC_PNT_TP.FAX_WORK) && !ZYPCommonFunc.hasValue(tMsg.prosCtacFaxNum)) {
                    this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_PROS_CTAC_FAX_NUM);
                    errFlg = true;
                }
                if (pntTp.equals(DS_CTAC_PNT_TP.EMAIL_WORK) && !ZYPCommonFunc.hasValue(tMsg.prosCtacEmlAddr)) {
                    this.messenger.addMessageForRecord(rowNum, ZZM9000E, MSG_PROS_CTAC_EML_ADDR);
                    errFlg = true;
                }
            }
        }

        return errFlg;
    }

    private boolean hasDupS21Loc(PROS_UPLD_WRKTMsg tMsg) {

        String dsAcctNm = tMsg.dsAcctNm.getValue();
        String firstLineAddr = tMsg.firstLineAddr.getValue();
        if (dsAcctNm != null && dsAcctNm.length() > SUBS_LEN_ACCT_NM) {
            dsAcctNm = dsAcctNm.substring(0, SUBS_LEN_ACCT_NM);
        }
        if (firstLineAddr != null && firstLineAddr.length() > SUBS_LEN_ADDR) {
            firstLineAddr = firstLineAddr.substring(0, SUBS_LEN_ADDR);
        }

        boolean errFlg = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            Map<String, Object> dupMap = new HashMap<String, Object>();
            dupMap.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
            dupMap.put("firstLineAddr", firstLineAddr);
            dupMap.put("dunsNum", tMsg.dunsNum.getValue());
            dupMap.put("glnNum", tMsg.glnNum.getValue());
            dupMap.put("dsAcctNm", dsAcctNm);
            dupMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            dupMap.put("acctNmLen", SUBS_LEN_ACCT_NM);
            dupMap.put("addrLen", SUBS_LEN_ADDR);

            ps = this.ssmLLClient.createPreparedStatement("getDupPtyLocWrk", dupMap, execParam);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NMAM8563E, rs.getString("LOC_NUM"));
                errFlg = true;
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        return errFlg;
    }

    private void setAcctErrMsg(BigDecimal upldCsvRqstPk, List<BigDecimal> errRowNum) {

        for (BigDecimal rowNum : errRowNum) {
            this.messenger.addMessageForRecord(rowNum, NMAM8566E, null);
        }
    }

    private PROS_UPLD_WRKTMsg setProsUpldWrkTMsg(ResultSet rs) throws SQLException {

        PROS_UPLD_WRKTMsg tMsg = new PROS_UPLD_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.upldCsvRqstPk, rs.getBigDecimal(UPLD_CSV_RQST_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.upldCsvRqstRowNum, rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.orgNm, rs.getString("ORG_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString("ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString("POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, rs.getString("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString("TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.faxNum, rs.getString("FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctUrl, rs.getString("DS_ACCT_URL"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicDescTxt, rs.getString("DS_CUST_SIC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocRevAmt, rs.getBigDecimal("DS_LOC_REV_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocEmpNum, rs.getBigDecimal("DS_LOC_EMP_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString("DS_CUST_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustCmpySicCd, rs.getString("DS_CUST_CMPY_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rs.getString("DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString("DS_ULT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsPrntDunsNum, rs.getString("DS_PRNT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctDunsNm, rs.getString("DS_ACCT_DUNS_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.glnNum, rs.getBigDecimal("GLN_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.acctSrcTxt, rs.getString("ACCT_SRC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, rs.getString("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPsnSaltDescTxt, rs.getString("DS_CTAC_PSN_SALT_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, rs.getString("CTAC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, rs.getString("CTAC_PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPsnTtlDescTxt, rs.getString("DS_CTAC_PSN_TTL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacTpDescTxt, rs.getString("CTAC_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntTpDescTxt, rs.getString("DS_CTAC_PNT_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.prosCtacPhoNum, rs.getString("PROS_CTAC_PHO_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.prosCtacFaxNum, rs.getString("PROS_CTAC_FAX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.prosCtacEmlAddr, rs.getString("PROS_CTAC_EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.prosCtacCellPhoNum, rs.getString("PROS_CTAC_CELL_PHO_NUM"));

        return tMsg;
    }

    private boolean callCustUpdateApi(NMZC001001PMsg pMsg, List<Map<String, Object>> list, BigDecimal upldCsvRqstPk) {

        boolean success = true;

        NMZC001001 api = new NMZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < S21ApiUtil.getXxMsgList(pMsg).size(); i++) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(i);
                for (Map<String, Object> map : list) {
                    this.messenger.addMessageForRecord((BigDecimal) map.get(PARAM_ROW_NUM), msg.getXxMsgid(), null);
                }
            }
            success = false;
        }

        NMZC001002PMsg locPMsg = null;
        String errId = null;
        BigDecimal rowNum = null;
        NMZC001002PMsg prevLocPMsg = null;
        String cntyNm = null;
        Map<String, String> suggestMap = new HashMap<String, String>();
        NMZC001002_xxMsgIdListPMsg msgListPMsg = null;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            locPMsg = pMsg.NMZC001002PMsg.no(i);
            rowNum = (BigDecimal) list.get(i).get(PARAM_ROW_NUM);
            prevLocPMsg = (NMZC001002PMsg) list.get(i).get(PARAM_LOC_PMSG);
            for (int j = 0; j < locPMsg.xxMsgIdList.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(locPMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgListPMsg = locPMsg.xxMsgIdList.no(j);
                    errId = msgListPMsg.xxMsgId.getValue();
                    this.messenger.addMessageForRecord(rowNum, errId, null);
                    success = false;

                    if (NMZM0179W.equals(errId)) {
                        suggestMap.put(msgListPMsg.xxMsgPrmTxt_0.getValue(), msgListPMsg.xxMsgPrmTxt_1.getValue());
                        warnFlg = true;
                    }
                }
            }

            cntyNm = null;
            if (ZYPCommonFunc.hasValue(locPMsg.cntyNm)) {
                cntyNm = locPMsg.cntyNm.getValue();
                if (ZYPCommonFunc.hasValue(prevLocPMsg.cntyNm) && cntyNm.equals(prevLocPMsg.cntyNm.getValue())) {
                    suggestMap.put(API_CNTY_NM, cntyNm);
                }
            }

            if (!suggestMap.isEmpty()) {
                if (!updateProsUpldWrk(suggestMap, upldCsvRqstPk, rowNum)) {
                    success = false;
                }
            }
        }

        return success;
    }

    private NMZC001001PMsg setNMZC001001PMsg(PROS_UPLD_WRKTMsg tMsg) {

        NMZC001001PMsg pMsg = new NMZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_PROS_CRAT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, procDt);

        // Account
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNm, tMsg.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctUrl, tMsg.dsAcctUrl);

        // get Internal/External
        String dsAcctItrlFlg = ZYPCodeDataUtil.getVarCharConstValue(DEF_DS_ACCT_ITRL_FLG, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctItrlFlg, dsAcctItrlFlg);

        // get Classification Code
        String dsAcctClsCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_DS_ACCT_CLS_CD, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctClsCd, dsAcctClsCd);

        // get Default COA
        DEF_DPLY_COA_INFOTMsgArray defDplyCoaInfotMsgArray = getDefDplyCoaInfo();
        if (defDplyCoaInfotMsgArray != null && defDplyCoaInfotMsgArray.getValidCount() > 0) {
            DEF_DPLY_COA_INFOTMsg defDplyCoaInfotMsg = (DEF_DPLY_COA_INFOTMsg) defDplyCoaInfotMsgArray.get(0);
            ZYPEZDItemValueSetter.setValue(pMsg.coaChCd, defDplyCoaInfotMsg.coaChCd);
            ZYPEZDItemValueSetter.setValue(pMsg.coaAfflCd, defDplyCoaInfotMsg.coaAfflCd);
        }

        // Account DUNS
        if (ZYPCommonFunc.hasValue(tMsg.dsAcctDunsNm) || ZYPCommonFunc.hasValue(tMsg.dsCustCmpySicCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctDunsNm, tMsg.dsAcctDunsNm);
            ZYPEZDItemValueSetter.setValue(pMsg.dsCustSicCd, tMsg.dsCustCmpySicCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsLastUpdDunsDt, procDt);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.xxAcctCrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAcctCltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.xxAcctTaxFlg, ZYPConstant.FLG_OFF_N);

        return pMsg;
    }

    private Map<String, Object> setNMZC001002PMsg(NMZC001001PMsg pMsg, int index, PROS_UPLD_WRKTMsg tMsg) {

        NMZC001002PMsg pMsg2 = pMsg.NMZC001002PMsg.no(index);
        ZYPEZDItemValueSetter.setValue(pMsg2.xxPrinFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg2.effFromDt, procDt);
        ZYPEZDItemValueSetter.setValue(pMsg2.actvFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(pMsg2.firstLineAddr, tMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg2.scdLineAddr, tMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg2.thirdLineAddr, tMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg2.frthLineAddr, tMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg2.ctyAddr, tMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg2.stCd, tMsg.stCd);
        ZYPEZDItemValueSetter.setValue(pMsg2.postCd, tMsg.postCd);
        ZYPEZDItemValueSetter.setValue(pMsg2.cntyNm, tMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(pMsg2.ctryCd, tMsg.ctryCd);

        // Mod Start 2016/07/01 M.Ohno QC#11147
        // ZYPEZDItemValueSetter.setValue(pMsg2.locNm, tMsg.dsAcctNm);
        // Mod End 2016/07/01 M.Ohno QC#11147

        ZYPEZDItemValueSetter.setValue(pMsg2.glnNum, tMsg.glnNum);
        ZYPEZDItemValueSetter.setValue(pMsg2.telNum, tMsg.telNum);
        ZYPEZDItemValueSetter.setValue(pMsg2.faxNum, tMsg.faxNum);
        ZYPEZDItemValueSetter.setValue(pMsg2.lineBizTpCd, tMsg.lineBizTpCd);

        ZYPEZDItemValueSetter.setValue(pMsg2.xxLocXrefInfoFlg, ZYPConstant.FLG_OFF_N);

        // Location DUNS
        if (ZYPCommonFunc.hasValue(tMsg.dsCustSicCd) || ZYPCommonFunc.hasValue(tMsg.dsCustSicDescTxt)) {
            ZYPEZDItemValueSetter.setValue(pMsg2.xxLocDunsFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg2.dunsNum, tMsg.dunsNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsUltDunsNum, tMsg.dsUltDunsNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsPrntDunsNum, tMsg.dsPrntDunsNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsLocEmpNum, tMsg.dsLocEmpNum);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsLocRevAmt, tMsg.dsLocRevAmt);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsLastUpdDunsDt, procDt);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsCustSicCd, tMsg.dsCustSicCd);
            ZYPEZDItemValueSetter.setValue(pMsg2.dsCustSicDescTxt, tMsg.dsCustSicDescTxt);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(PARAM_LOC_PMSG, pMsg2);
        map.put("orgNm", tMsg.orgNm.getValue());
        map.put(PARAM_ROW_NUM, tMsg.upldCsvRqstRowNum.getValue());

        return map;
    }

    private boolean callCtacUpdateApi(List<Map<String, Object>> list, String dsAcctNum) {

        boolean success = true;

        NMZC002001 api = new NMZC002001();
        NMZC002001PMsg pMsg = null;
        BigDecimal rowNum = null;

        for (Map<String, Object> map : list) {
            if (!success) {
                continue;
            }

            rowNum = (BigDecimal) map.get(PARAM_ROW_NUM);
            pMsg = (NMZC002001PMsg) map.get("ctacPMsg");
            ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, dsAcctNum);

            api.execute(pMsg, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                for (int i = 0; i < S21ApiUtil.getXxMsgList(pMsg).size(); i++) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(i);
                    this.messenger.addMessageForRecord(rowNum, msg.getXxMsgid(), null);
                }
                success = false;
            }
        }

        return success;
    }

    private Map<String, Object> setNMZC002001PMsg(int index, PROS_UPLD_WRKTMsg tMsg, ResultSet rs) throws SQLException {

        NMZC002001PMsg pMsg = new NMZC002001PMsg();

        // Contact
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, procDt);
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, procDt);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnFirstNm, tMsg.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnLastNm, tMsg.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, rs.getString(ORIG_CTAC_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.dsCtacPsnSaltCd, rs.getString(ORIG_CTAC_PSN_SALT_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.dsCtacPsnTtlCd, rs.getString(ORIG_CTAC_PSN_TTL_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.dsPrimCtacTpCd, rs.getString(ORIG_CTAC_PNT_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
        }

        // Point
        int cnt = 0;
        NMZC002001_ContactPointInfoListPMsgArray pntList = pMsg.ContactPointInfoList;
        if (ZYPCommonFunc.hasValue(tMsg.prosCtacPhoNum)) {
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntValTxt, tMsg.prosCtacPhoNum);
            cnt++;
        }
        if (ZYPCommonFunc.hasValue(tMsg.prosCtacFaxNum)) {
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntValTxt, tMsg.prosCtacFaxNum);
            cnt++;
        }
        if (ZYPCommonFunc.hasValue(tMsg.prosCtacCellPhoNum)) {
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_MOBILE);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntValTxt, tMsg.prosCtacCellPhoNum);
            cnt++;
        }
        if (ZYPCommonFunc.hasValue(tMsg.prosCtacEmlAddr)) {
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
            ZYPEZDItemValueSetter.setValue(pntList.no(cnt).dsCtacPntValTxt, tMsg.prosCtacEmlAddr);
            cnt++;
        }
        pntList.setValidCount(cnt);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ctacPMsg", pMsg);
        map.put(PARAM_ROW_NUM, tMsg.upldCsvRqstRowNum.getValue());

        return map;
    }

    private boolean insertTrtyRule(List<Map<String, Object>> locList) {

        boolean success = true;

        TRTY_RULETMsg trtyRuleTMsg = new TRTY_RULETMsg();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        // check Territory for rule
        for (Map<String, Object> param : locList) {
            if (!ZYPCommonFunc.hasValue((String) param.get("orgNm"))) {
                continue;
            }
            map = checkTrtyForRule(param);
            if (map == null) {
                success = false;
            } else {
                NMZC001002PMsg pMsg = (NMZC001002PMsg) param.get(PARAM_LOC_PMSG);
                map.put("locNum", pMsg.locNum.getValue());
                list.add(map);
            }
        }
        if (!success) {
            return false;
        }

        // insert Territory rule
        for (Map<String, Object> paramMap : list) {
            if (paramMap.get("insert").equals(ZYPConstant.FLG_ON_Y)) {
                // insert TRTY_RULE
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ));
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, (String) paramMap.get("orgStruTpCd"));
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgCd, (String) paramMap.get("orgCd"));
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleFromValTxt, (String) paramMap.get("locNum"));
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effFromDt, procDt);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

                EZDTBLAccessor.insert(trtyRuleTMsg);
                if (!RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
                    this.messenger.addMessageForRecord((BigDecimal) paramMap.get(PARAM_ROW_NUM), ZZZM9012E, trtyRuleTMsg.getReturnCode());
                    success = false;
                }
            }
        }

        return success;
    }

    private boolean updateProsUpldWrk(Map<String, String> suggestMap, BigDecimal upldCsvRqstPk, BigDecimal rowNum) {

        PROS_UPLD_WRKTMsg tMsg = new PROS_UPLD_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.upldCsvRqstPk, upldCsvRqstPk);
        ZYPEZDItemValueSetter.setValue(tMsg.upldCsvRqstRowNum, rowNum);
        tMsg = (PROS_UPLD_WRKTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_FIRST_ADDR))) {
                ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, suggestMap.get(API_FIRST_ADDR));
            }
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_SCD_ADDR))) {
                ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, suggestMap.get(API_SCD_ADDR));
            }
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_CTY_ADDR))) {
                ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, suggestMap.get(API_CTY_ADDR));
            }
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_ST_CD))) {
                ZYPEZDItemValueSetter.setValue(tMsg.stCd, suggestMap.get(API_ST_CD));
            }
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_POST_CD))) {
                ZYPEZDItemValueSetter.setValue(tMsg.postCd, suggestMap.get(API_POST_CD));
            }
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_CTRY_CD))) {
                ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, suggestMap.get(API_CTRY_CD));
            }
            if (ZYPCommonFunc.hasValue(suggestMap.get(API_CNTY_NM))) {
                ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, suggestMap.get(API_CNTY_NM));
            }

            EZDTBLAccessor.update(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.messenger.addMessageForRecord(rowNum, ZZZM9013E, tMsg.getReturnCode());
                return false;
            }
        }

        return true;
    }

    private Map<String, Object> checkTrtyForRule(Map<String, Object> paramMap) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        String orgStruTpCd = null;
        String orgCd = null;
        String orgNm = (String) paramMap.get("orgNm");
        BigDecimal rowNum = (BigDecimal) paramMap.get(PARAM_ROW_NUM);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // check if Territory is active
        Map<String, Object> orgParams = new HashMap<String, Object>();
        orgParams.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        orgParams.put("orgNm", orgNm);
        String[] gnrnTpCd = new String[] {GNRN_TP.CURRENT, GNRN_TP.FUTURE };
        orgParams.put("gnrnTpCd", gnrnTpCd);

        Map<String, Object> resultMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getTrtyInfo", orgParams, execParam);
        if (resultMap == null) {
            this.messenger.addMessageForRecord(rowNum, NMAM8572E, orgNm);
            return null;
        } else {
            orgStruTpCd = (String) resultMap.get("ORG_STRU_TP_CD");
            orgCd = (String) resultMap.get("ORG_CD");
            map.put("orgStruTpCd", orgStruTpCd);
            map.put("orgCd", orgCd);
            map.put(PARAM_ROW_NUM, rowNum);
            map.put("insert", ZYPConstant.FLG_ON_Y);

            // check if Sales Rep exists
            String repCheck = (String) resultMap.get("PSN_CD");
            if (repCheck == null || repCheck.isEmpty()) {
                this.messenger.addMessageForRecord(rowNum, NMAM8597I, orgNm);
            }

            // check if Sales Rep for Territory exists
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
            params.put("orgNm", orgNm);
            params.put("gnrnTpCd", GNRN_TP.CURRENT);
            params.put("nonSlsRepFlg", ZYPConstant.FLG_OFF_N);
            List<String> orgCheck = (List<String>) this.ssmBatchClient.queryObjectList("getTrtyGrpInfo", params, execParam);
            if (orgCheck == null || orgCheck.size() != 1) {
                // check if other type of resources exist
                params.put("nonSlsRepFlg", ZYPConstant.FLG_ON_Y);
                params.put("nonSlsRep", ZYPConstant.FLG_ON_Y);
                orgCheck = (List<String>) this.ssmBatchClient.queryObjectList("getTrtyGrpInfo", params, execParam);
                if (orgCheck == null || orgCheck.size() == 0) {
                    this.messenger.addMessageForRecord(rowNum, NMAM8461E, MSG_TRTY_ASG_RESRC);
                    return null;
                }
            }

            // check if other type of Territory Rule exists
            if (existTrtyRuleOtherType(orgStruTpCd, orgCd)) {
                this.messenger.addMessageForRecord(rowNum, NMAM8559I, null);
                map.put("insert", ZYPConstant.FLG_OFF_N);
            }

            // check if Territory Rule is AND operation
            if (existTrtyRuleAndLogic(orgStruTpCd, orgCd)) {
                this.messenger.addMessageForRecord(rowNum, NMAM8573I, null);
                map.put("insert", ZYPConstant.FLG_OFF_N);
            }
        }

        return map;
    }

    private DEF_DPLY_COA_INFOTMsgArray getDefDplyCoaInfo() {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("appFuncId01", APP_FUNC_ID);

        return (DEF_DPLY_COA_INFOTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    private boolean existTrtyRuleOtherType(String orgStruTpCd, String orgCd) {

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("orgCd01", orgCd);
        tMsg.setConditionValue("orgStruTpCd01", orgStruTpCd);
        tMsg.setConditionValue("effFromDt01", procDt);
        tMsg.setConditionValue("effThruDt01", procDt);
        tMsg.setConditionValue("trtyRuleTpCd01", TRTY_RULE_TP.LOCATION_NUMBER);

        TRTY_RULETMsgArray tMsgArray = (TRTY_RULETMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    private boolean existTrtyRuleAndLogic(String orgStruTpCd, String orgCd) {

        TRTY_RULETMsg tMsg = new TRTY_RULETMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("orgCd01", orgCd);
        tMsg.setConditionValue("orgStruTpCd01", orgStruTpCd);
        tMsg.setConditionValue("effFromDt01", procDt);
        tMsg.setConditionValue("effThruDt01", procDt);
        tMsg.setConditionValue("trtyRuleTpCd01", TRTY_RULE_TP.LOCATION_NUMBER);
        tMsg.setConditionValue("TRTY_RULE_LOGIC_TP_CD", TRTY_RULE_LOGIC_TP.OR);

        TRTY_RULETMsgArray tMsgArray = (TRTY_RULETMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Get Upload CSV Request PK
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (!ZYPCommonFunc.hasValue(removeDQRequestPK)) {
            throw new S21AbendException(ZZZM9006E, msg);
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(ZZZM9026E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * Get Upload CSV ID
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!ZYPCommonFunc.hasValue(uploadCsvId)) {
            String[] msg = {MSG_UPLD_CSV_ID };
            throw new S21AbendException(ZZZM9006E, msg);
        }
        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * Get Upload CSV Request
     * @param upldCsvReqPk upload CSV Request Pk
     * @return UPLD_CSV_RQSTTMsg
     */
    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {

        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }

        return outTMsg;
    }

}
