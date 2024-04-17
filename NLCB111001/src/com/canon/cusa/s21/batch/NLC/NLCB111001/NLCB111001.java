/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB111001;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ABC_ANLS_RQSTTMsg;
import business.db.ABC_ANLS_RSLTTMsg;
import business.db.ABC_ANLS_RSLT_DTLTMsg;

import com.canon.cusa.s21.batch.NLC.NLCB111001.constant.NLCB111001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Batch Program Class for ABC Analysis Batch
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 06/29/2016   CITS            S.Endo      Create          
 * 
 *</pre>
 */
public class NLCB111001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    /** Program ID */
    private static final String PROGRAM_ID = "NLCB1110";

    // -- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Insert */
    private int insertCount = 0;

    /** The number of case : Skip */
    private int skipCount;

    // -- Other Internal Variable ---------------
    /** Error Code for ABC Analysis Request */
    private static final String ERROR_CD = "9";

    /** Complete Code for ABC Analysis Request */
    private static final String COMPLETE_CD = "2";

    /** In Process Code for ABC Analysis Request */
    private static final String IN_PROCESS_CD = "1";

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Termination code */
    private TERM_CD termCd;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** ABC Analysis Request PK */
    private BigDecimal analysisRequestPk;

    /** ABC Analysis Assignment PK */
    private BigDecimal assignmentPk;

    /** Use Inventory history Flag */
    private String invtyHistoryFlg;

    /** ABC Analysis ColTpCode */
    private String analysisColTpCd;

    /** ABC Analysis Class Number */
    private String abcClsNum;

    /** ABC Analysis Cumulative Value Amount */
    private BigDecimal abcAnlsCumValAmt = BigDecimal.ZERO;

    /** ABC Analysis Cumulative Value Percent */
    private BigDecimal abcAnlsCumValPct = BigDecimal.ZERO;

    @Override
    protected void initRoutine() {
        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();

    }

    @Override
    protected void mainRoutine() {
        // Get ABC Analysis Request List.
        List<Map<String, Object>> analysisRequestList = getABCAnalysisRequestList();
        selectCount = analysisRequestList.size();
        // analysisRequestList Loop
        for (Iterator<Map<String, Object>> requestIter = analysisRequestList.iterator(); requestIter.hasNext();) {
            Map<String, Object> analysisRequest = requestIter.next();
            analysisRequestPk = (BigDecimal) analysisRequest.get(NLCB111001Constant.COL_ABC_ANLS_RQST_PK);
            assignmentPk = (BigDecimal) analysisRequest.get(NLCB111001Constant.COL_ABC_ASG_PK);

            // Get ABC Analysis Assignment
            Map<String, Object> analysisAssignment = getABCAnalysisAssignment();
            if (analysisAssignment == null) {
                handleError(NLCB111001Constant.MSG_ID_NLCM0182E, NLCB111001Constant.EMPTY, analysisRequest);
                continue;
            }
            // Get ABC Analysis Assignment WH
            List<String> analysisAssignmentWHList = getABCAnalysisAssignmentWHList();
            if (analysisAssignmentWHList.size() == 0) {
                handleError(NLCB111001Constant.MSG_ID_NLCM0183E, (String) analysisAssignment.get(NLCB111001Constant.COL_ABC_ASG_NM), analysisRequest);
                continue;
            }
            // Get ABC Analysis Assignment SWH
            List<String> analysisAssignmentSWHList = getABCAnalysisAssignmentSWHList();
            // Get ABC Analysis Assignment StockStatus
            List<String> analysisAssignmentStockStatusList = getABCAnalysisAssignmentStockStatusList();
            if (analysisAssignmentStockStatusList.size() == 0) {
                handleError(NLCB111001Constant.MSG_ID_NLCM0184E, (String) analysisAssignment.get(NLCB111001Constant.COL_ABC_ASG_NM), analysisRequest);
                continue;
            }
            invtyHistoryFlg = (String) analysisAssignment.get(NLCB111001Constant.COL_INVTY_TRX_HIST_ANLS_FLG);
            analysisColTpCd = (String) analysisAssignment.get(NLCB111001Constant.COL_ABC_ANLS_COL_TP_CD);
            abcClsNum       = (String) analysisAssignment.get(NLCB111001Constant.COL_ABC_ANLS_CLS_NUM);
            // ABC Analysis Assignment WH Loop
            for (String rtlWhCd : analysisAssignmentWHList) {
                List<Map<String, Object>> inventoryList;

                if (ZYPConstant.FLG_OFF_N.equals(invtyHistoryFlg)) {
                    inventoryList = getInventory((String) analysisAssignment.get(NLCB111001Constant.COL_MDSE_ITEM_TP_CD), rtlWhCd, analysisAssignmentSWHList, analysisAssignmentStockStatusList);
                } else {
                    inventoryList = getHistoricalInventory((String) analysisAssignment.get(NLCB111001Constant.COL_MDSE_ITEM_TP_CD), (String) analysisAssignment.get(NLCB111001Constant.COL_EFF_FROM_DT), (String) analysisAssignment.get(
                            NLCB111001Constant.COL_EFF_THRU_DT), rtlWhCd, analysisAssignmentSWHList, analysisAssignmentStockStatusList);
                }
                // inventoryList Loop
                for (Map<String, Object> inventory : inventoryList) {
                    createABCAnalysisResultDetail(inventory);
                }
                // Get ABC Class
                List<Map<String, Object>> abcClassList = getABCClassList();
                // Get ABC AnalysisResultDetail WH Summary
                Map<String, Object> resultDetailWHSummary = getABCAnalysisResultDetailWHSummary(rtlWhCd);
                // Get ABC Analysis Result WH & MDSE Summary
                List<Map<String, Object>> resultSummary = getAbcAnalysisResultSummary(rtlWhCd);
                // Define Tag Code And update Result
                for (Map<String, Object> result : resultSummary) {
                    defineTag(abcClassList, resultDetailWHSummary, result);
                    // update latest ABC Analysis Result
                    updateABCAnalysisResult(rtlWhCd, result);
                }
                abcAnlsCumValAmt = BigDecimal.ZERO;
                abcAnlsCumValPct = BigDecimal.ZERO;
            }
            // Change ABC Analysis Request /ABC Analysis Request
            // Status Code
            updateAbcAnalysisRequest(analysisRequest, COMPLETE_CD, NLCB111001Constant.EMPTY);
            insertCount = insertCount + 1;
        }
    }

    @Override
    protected void termRoutine() {
        // Setting of termination code
        skipCount = selectCount - insertCount;
        setTermState(termCd, selectCount, skipCount, insertCount);
    }

    /**
     * @param args execution parameters
     */
    public static void main(String[] args) {
        new NLCB111001().executeBatch(NLCB111001.class.getSimpleName());

    }

    /**
     * Get ABC AnalysisRequest List.
     * @return ABC AnalysisRequest List
     */
    private List<Map<String, Object>> getABCAnalysisRequestList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("getABCAnalysisRequest", queryParam);
        return result;
    }

    /**
     * Get ABC AnalysisAssignment.
     * @return ABC AnalysisAssignment
     * @throws S21AbendException when ABC AnalysisAssignment.size==0
     */
    private Map<String, Object> getABCAnalysisAssignment() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ASG_PK, assignmentPk);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getABCAnalysisAssignment", queryParam);
        return result;
    }

    /**
     * Get ABC AnalysisAssignment WH List.
     * @return ABC AnalysisAssignment WH List
     * @throws S21AbendException when ABC AnalysisAssignment WH
     * List.size==0
     */
    private List<String> getABCAnalysisAssignmentWHList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ASG_PK, assignmentPk);
        List<String> result = (List<String>) this.ssmBatchClient.queryObjectList("getABCAnalysisAssignmentWH", queryParam);
        return result;
    }

    /**
     * Get ABC AnalysisAssignment SWH List.
     * @return ABC AnalysisAssignment WH List
     */
    private List<String> getABCAnalysisAssignmentSWHList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ASG_PK, assignmentPk);
        List<String> result = (List<String>) this.ssmBatchClient.queryObjectList("getABCAnalysisAssignmentSWH", queryParam);
        return result;
    }

    /**
     * Get ABC AnalysisAssignment Stock Status List.
     * @return ABC AnalysisAssignment Stock Status List
     * @throws S21AbendException when ABC AnalysisAssignment Stock
     * Status List.size==0
     */
    private List<String> getABCAnalysisAssignmentStockStatusList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ASG_PK, assignmentPk);
        List<String> result = (List<String>) this.ssmBatchClient.queryObjectList("getABCAnalysisAssignmentStockStatus", queryParam);
        return result;
    }

    /**
     * Get ABC Class List.
     * @param WHCode
     */
    private List<Map<String, Object>> getABCClassList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ANLS_CLS_NUM, abcClsNum);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getABCClass", queryParam);
    }

    /**
     * Get Inventory(from OnHand)
     * @param mdseItemTpCd
     * @param rtlWhCd
     * @param analysisAssignmentStockStatusList
     * @param analysisAssignmentSWHList
     * @return Inventory List
     */
    private List<Map<String, Object>> getInventory(String mdseItemTpCd, String rtlWhCd, List<String> analysisAssignmentSWHList, List<String> analysisAssignmentStockStatusList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_RTL_WH_CD, rtlWhCd);
        queryParam.put(NLCB111001Constant.PARAM_SWH_LIST, analysisAssignmentSWHList);
        queryParam.put(NLCB111001Constant.PARAM_STOCKSTATUS_LIST, analysisAssignmentStockStatusList);
        queryParam.put(NLCB111001Constant.PARAM_MDSE_ITEM_TP_CD, mdseItemTpCd);
        queryParam.put(NLCB111001Constant.COL_LOC_STS_CD, LOC_STS.DC_STOCK);
        return this.ssmBatchClient.queryObjectList("getInventory", queryParam);
    }

    /**
     * Get Inventory(from Historical/Transaction)
     * @param mdseItemTpCd
     * @param effThrouDt
     * @param effFromDt
     * @param rtlWhCd
     * @param analysisAssignmentStockStatusList
     * @param analysisAssignmentSWHList
     * @return Inventory List
     */
    private List<Map<String, Object>> getHistoricalInventory(String mdseItemTpCd, String effFromDt, String effThruDt, String rtlWhCd, List<String> analysisAssignmentSWHList, List<String> analysisAssignmentStockStatusList) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.PARAM_ABC_ANLS_COL_TP_CD, analysisColTpCd);
        queryParam.put(NLCB111001Constant.COL_RTL_WH_CD, rtlWhCd);
        if (ZYPCommonFunc.hasValue(effFromDt) && ZYPCommonFunc.hasValue(effThruDt)) {
            queryParam.put(NLCB111001Constant.COL_EFF_FROM_DT, effFromDt);
            queryParam.put(NLCB111001Constant.COL_EFF_THRU_DT, effThruDt);
            queryParam.put(NLCB111001Constant.PARAM_DTCONDITION, ZYPConstant.FLG_ON_Y);
        }
        queryParam.put(NLCB111001Constant.PARAM_SWH_LIST, analysisAssignmentSWHList);
        queryParam.put(NLCB111001Constant.PARAM_STOCKSTATUS_LIST, analysisAssignmentStockStatusList);
        queryParam.put(NLCB111001Constant.PARAM_MDSE_ITEM_TP_CD, mdseItemTpCd);
        queryParam.put(NLCB111001Constant.COL_LOC_STS_CD, LOC_STS.DC_STOCK);
        return this.ssmBatchClient.queryObjectList("getHistoricalInventory", queryParam);
    }

    /**
     * Get ABCAnalysisResultDetail Group By WHSummary
     * @param analysisRequestPk
     * @param rtlWhCd
     * @return ABCAnalysisResultDetail Group By WHSummary
     */
    private Map<String, Object> getABCAnalysisResultDetailWHSummary(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ANLS_RQST_PK, analysisRequestPk);
        queryParam.put(NLCB111001Constant.COL_RTL_WH_CD, rtlWhCd);
        queryParam.put(NLCB111001Constant.PARAM_ABC_ANLS_COL_TP_CD, analysisColTpCd);
        queryParam.put(NLCB111001Constant.PARAM_INVTRY_TRX_HIST_ANLS_FLG, invtyHistoryFlg);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getABCResultDetailGroupByWH", queryParam);
    }

    /**
     * Get ABCAnalysis Result Group By WH And MDSE Summary
     * @param analysisRequestPk
     * @param rtlWhCd
     * @return ABCAnalysisResultDetail Group By WH And MDSE Summary
     */
    private List<Map<String, Object>> getAbcAnalysisResultSummary(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ANLS_RQST_PK, analysisRequestPk);
        queryParam.put(NLCB111001Constant.COL_RTL_WH_CD, rtlWhCd);
        queryParam.put(NLCB111001Constant.PARAM_ABC_ANLS_COL_TP_CD, analysisColTpCd);
        queryParam.put(NLCB111001Constant.PARAM_INVTRY_TRX_HIST_ANLS_FLG, invtyHistoryFlg);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getABCResultGroupByWhMdse", queryParam);
    }

    /**
     * Get ABC Analysis Result Detail PK
     * @param analysisRequestPk
     * @param rtlWhCd
     * @param mdseCd
     * @return ABC AnalysisResultDetail PK
     */
    private List<Map<String, Object>> getAbcAnalysisResultDetailPk(String rtlWhCd, String mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_ABC_ANLS_RQST_PK, analysisRequestPk);
        queryParam.put(NLCB111001Constant.COL_RTL_WH_CD, rtlWhCd);
        queryParam.put(NLCB111001Constant.COL_MDSE_CD, mdseCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getABCResultDetailPk", queryParam);
    }

    /**
     * Create ABC Analysis Result Detail.
     * @param inventory
     * @param invtyTrxHistAnlsFlg
     */
    private void createABCAnalysisResultDetail(Map<String, Object> inventory) {
        ABC_ANLS_RSLT_DTLTMsg tMsg = new ABC_ANLS_RSLT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRsltDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RSLT_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAsgPk, assignmentPk);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstPk, analysisRequestPk);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) inventory.get(NLCB111001Constant.COL_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) inventory.get(NLCB111001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String) inventory.get(NLCB111001Constant.COL_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, (String) inventory.get(NLCB111001Constant.COL_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.curInvtyQty, (BigDecimal) inventory.get(NLCB111001Constant.COL_INVTY_QTY));
        if (ZYPConstant.FLG_OFF_N.equals(invtyHistoryFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.curInvtyCostAmt, (BigDecimal) inventory.get(NLCB111001Constant.COL_THIS_MTH_TOT_STD_COST_AMT));
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxCostAmt, BigDecimal.ZERO);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.curInvtyCostAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxCostAmt, (BigDecimal) inventory.get(NLCB111001Constant.COL_THIS_MTH_TOT_STD_COST_AMT));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxQty, (BigDecimal) inventory.get(NLCB111001Constant.COL_HIST_INVTY_TRX_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.histInvtyTrxRecCnt, (BigDecimal) inventory.get(NLCB111001Constant.COL_HIST_INVTY_TRX_REC_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsTagCd, NLCB111001Constant.EMPTY);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLCB111001Constant.MSG_ID_NLCM0100E, new String[] {NLCB111001Constant.TBL_ABC_ANLS_RSLT_DTL, NLCB111001Constant.EMPTY, NLCB111001Constant.EMPTY });
        }
    }

    /**
     * Define Tag
     * @param abcClassList
     * @param resultDetailWHSummary
     * @param result
     */
    private void defineTag(List<Map<String, Object>> abcClassList, Map<String, Object> resultDetailWHSummary, Map<String, Object> result) {
        if (result.get(NLCB111001Constant.COL_ABC_ANLS_VAL_AMT) != null) {
            abcAnlsCumValAmt = abcAnlsCumValAmt.add((BigDecimal) result.get(NLCB111001Constant.COL_ABC_ANLS_VAL_AMT));
        }
        if (resultDetailWHSummary.get(NLCB111001Constant.COL_ABC_ANLS_VAL_AMT_TOTAL) != null) {
            abcAnlsCumValPct = abcAnlsCumValAmt.divide((BigDecimal) resultDetailWHSummary.get(NLCB111001Constant.COL_ABC_ANLS_VAL_AMT_TOTAL), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        // find ClassTag.
        for (Map<String, Object> abcClass : abcClassList) {
            if (((BigDecimal) (abcClass.get(NLCB111001Constant.COL_CUM_VAL_ASG_FROM_PCT))).compareTo(abcAnlsCumValPct) < 0
                    && ((BigDecimal) (abcClass.get(NLCB111001Constant.COL_CUM_VAL_ASG_TO_PCT))).compareTo(abcAnlsCumValPct) >= 0) {
                result.put(NLCB111001Constant.COL_ABC_ANLS_CLS_TAG_CD, abcClass.get(NLCB111001Constant.COL_ABC_ANLS_CLS_TAG_CD));
                result.put(NLCB111001Constant.COL_CYCLE_CNT_FREQ_DAYS_AOT, abcClass.get(NLCB111001Constant.COL_CYCLE_CNT_FREQ_DAYS_AOT));
                break;
            }
        }
    }

    /**
     * update latest ABCAnalysisResult
     * @param rtlWhCd
     * @param result
     */
    private void updateABCAnalysisResult(String rtlWhCd, Map<String, Object> result) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLCB111001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLCB111001Constant.COL_RTL_WH_CD, rtlWhCd);
        queryParam.put(NLCB111001Constant.COL_MDSE_CD, (String) result.get(NLCB111001Constant.COL_MDSE_CD));
        BigDecimal abcAnlsRsltPk = (BigDecimal) this.ssmBatchClient.queryObject("getABCAnalysisResultPK", queryParam);

        // update latest result to old result.
        ABC_ANLS_RSLTTMsg tMsg;
        if (abcAnlsRsltPk != null) {
            tMsg = new ABC_ANLS_RSLTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRsltPk, abcAnlsRsltPk);
            tMsg = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
            // set column for update (ABC_ANLS_LTST_FLG)
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsLtstFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NLCB111001Constant.MSG_ID_NLCM0206E, new String[] {NLCB111001Constant.TBL_ABC_ANLS_RSLT, NLCB111001Constant.COL_ABC_ANLS_LTST_FLG, ZYPConstant.FLG_OFF_N });
            }
        }

        // create latest result.
        tMsg = new ABC_ANLS_RSLTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRsltPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ABC_ANLS_RSLT_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsProcTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAsgPk, assignmentPk);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstPk, analysisRequestPk);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) result.get(NLCB111001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsValAmt, (BigDecimal) result.get(NLCB111001Constant.COL_ABC_ANLS_VAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsCumValAmt, abcAnlsCumValAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsCumValPct, abcAnlsCumValPct);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsNum, abcClsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsTagCd, (String) result.get(NLCB111001Constant.COL_ABC_ANLS_CLS_TAG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cycleCntFreqDaysAot, (BigDecimal) result.get(NLCB111001Constant.COL_CYCLE_CNT_FREQ_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsLtstFlg, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLCB111001Constant.MSG_ID_NLCM0100E, new String[] {NLCB111001Constant.TBL_ABC_ANLS_RSLT });
        }

        // Get ABC Analysis PK
        List<Map<String, Object>> resultDetailPk = getAbcAnalysisResultDetailPk(tMsg.rtlWhCd.getValue(), tMsg.mdseCd.getValue());
        // Define Tag Code And update Result Detail
        for (Map<String, Object> resultDtl : resultDetailPk) {
            // update ABC ABC Analysis Result Detail
            updateABCAnalysisResultDetail(resultDtl, tMsg.abcAnlsClsTagCd.getValue());
        }

    }

    /**
     * update ABC AnalysisResult Detail
     * @param abcAnlsClsTagCd
     * @param result
     */
    private void updateABCAnalysisResultDetail(Map<String, Object> resultDtl, String abcAnlsClsTagCd) {

        ABC_ANLS_RSLT_DTLTMsg tMsg = new ABC_ANLS_RSLT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRsltDtlPk, (BigDecimal) resultDtl.get(NLCB111001Constant.COL_ABC_ANLS_RSLT_DTL_PK));
        tMsg = (ABC_ANLS_RSLT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsClsTagCd, abcAnlsClsTagCd);
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLCB111001Constant.MSG_ID_NLCM0206E, new String[] {NLCB111001Constant.TBL_ABC_ANLS_RSLT_DTL });
        }
    }

    /**
     * update ABC_ANLS_RQST set ABC_ANLS_RQST_STS_CD='xx'
     * @param conditionMap conditionMap
     * @param stsCd stsCd
     */
    private void updateAbcAnalysisRequest(Map<String, Object> conditionMap, String stsCd, String errorMsgId) {

        ABC_ANLS_RQSTTMsg tMsg = new ABC_ANLS_RQSTTMsg();
        // PrimaryKey Condition
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstPk, (BigDecimal) conditionMap.get(NLCB111001Constant.COL_ABC_ANLS_RQST_PK));

        tMsg = (ABC_ANLS_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        // set column for update (ABC_ANLS_RQST_STS_CD)
        ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsRqstStsCd, stsCd);
        if (COMPLETE_CD.equals(stsCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.abcAnlsLastProcTs, ZYPDateUtil.getCurrentSystemTime(NLCB111001Constant.SYSTEM_TIME_STAMP));
        }

        if (ERROR_CD.equals(stsCd)) {
            ZYPEZDItemValueSetter.setValue(tMsg.batErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
        }
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLCB111001Constant.MSG_ID_NLCM0206E, new String[] {NLCB111001Constant.TBL_ABC_ANLS_RQST, NLCB111001Constant.COL_ABC_ANLS_RQST_STS_CD, stsCd });
        }
        commit();
    }

    /**
     * handle Error. do rollback,update request status to error,send
     * mail.
     * @param errorMsg
     * @param abcNam
     * @param analysisRequest
     */
    private void handleError(String errorMsgId, String abcName, Map<String, Object> analysisRequest) {
        rollback();
        updateAbcAnalysisRequest(analysisRequest, ERROR_CD, errorMsgId);
        sendMail(errorMsgId, analysisRequestPk, abcName);
        termCd = TERM_CD.ABNORMAL_END;
    }

    /**
     * send mail.
     * @param errorMsgId
     * @param requestId
     * @param abcName
     */
    private void sendMail(String errorMsgId, BigDecimal requestId, String abcName) {
        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, NLCB111001Constant.FROM_BIZID);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        S21Mail mail = new S21Mail(glblCmpyCd);
        if (fromAddrList.size() > 0) {
            mail.setFromAddress(fromAddrList.get(0));
            S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, NLCB111001Constant.TO_BIZID);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {
                mail.setToAddressList(toAddrList);
                S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, NLCB111001Constant.TEMPLATE_ID);
                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {
                    SimpleDateFormat errTmFmt = new SimpleDateFormat(NLCB111001Constant.ERROR_TIME_FORMAT);
                    tmpl.setTemplateParameter(NLCB111001Constant.PARAM_REQID, requestId);
                    tmpl.setTemplateParameter(NLCB111001Constant.PARAM_ABCNM, abcName);
                    tmpl.setTemplateParameter(NLCB111001Constant.PARAM_BATCHID, PROGRAM_ID);
                    tmpl.setTemplateParameter(NLCB111001Constant.PARAM_ERRDATE, errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter(NLCB111001Constant.PARAM_MESSAGE, S21MessageFunc.clspGetMessage(errorMsgId));
                    mail.setMailTemplate(tmpl);
                    mail.postMail();
                    commit();
                }
            }
        }
    }
}
