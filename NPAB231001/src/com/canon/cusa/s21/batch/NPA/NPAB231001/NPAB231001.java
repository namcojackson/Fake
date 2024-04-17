/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB231001;

import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.CNTY_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DATE_FORMAT_HHMM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DATE_FORMAT_HHMM_AM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DB_PARAM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DB_PARAM_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DS_COND_CONST;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.EMAIL_PARAM_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.EMAIL_PARAM_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.FR_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.FR_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.FR_WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.GRP_ID_NPZC1170;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.IF_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ML_GRP_CD_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ML_GRP_CD_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ML_LANG;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ML_LANG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ML_TMPL_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.NPAB2310_REPLENORD_CRAT_USER;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.NPAM1653E;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.NPZM0161E;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.NZZM0010E;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.PARAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.PARAM_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.PRCH_REQ_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.REQ_TP_MINMAX;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.SHIP_FR_WH_RG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.SHIP_TO_WH_RG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.TECH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.TO_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.TO_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.TO_RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.TO_TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.DEFAULT_TIME_STR;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ZZBM0009I;
import static com.canon.cusa.s21.batch.NPA.NPAB231001.constant.NPAB231001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_COND_CONSTTMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Baxter IF for RPLENORD
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/20/2021   CITS            K.Ogino         Create          QC#58990
 * 07/05/2021   CITS            F.Fadriquela    Update          QC#60262
 *</pre>
 */
public class NPAB231001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<String> errMsgList = new ArrayList<String>();

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** Sales Date */
    private String salesDate = null;

    // *********************************************************
    // Instance Variables: Constant values on load
    // *********************************************************
    /** var_char_const_val */
    private String varVal = "";

    /**
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NPAB231001().executeBatch(NPAB231001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of variable
        successCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();

        // Global Company Code
        this.glblCmpyCd = prof.getGlobalCompanyCode();

        // Interface ID
        this.interfaceId = getInterfaceID();

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // VAR_CHAR_CONST
        this.varVal = ZYPCodeDataUtil.getVarCharConstValue(NPAB2310_REPLENORD_CRAT_USER, this.glblCmpyCd);
        if (varVal == null) {
            varVal = "BAXTER";
        }

        // Check on input parameter
        checkParameter();
    }

    @Override
    protected void mainRoutine() {

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length <= 0) {
            return;
        }

        try {
            for (BigDecimal trxId : tranIds) {

                cratWHTransfer(trxId);

                // Update the flag to processed in any case - success/fail -
                trxAccess.endIntegrationProcess(this.interfaceId, trxId);
                
                commit();
            }

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                // ERROR MAIL
                sendErrMail();
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount) });

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_GLBL_CMPY_CD });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_INTERFACE_ID });
        }
    }

    private void cratWHTransfer(BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(DB_PARAM_INTERFACE_ID, this.interfaceId);
        bindParam.put(DB_PARAM_TRANSACTION_ID, trxId);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            stmt = ssmLLClient.createPreparedStatement("findIFData", bindParam, execParam);
            rs = stmt.executeQuery();

            NPZC103001PMsg pMsg = null;
            String prevGrpKey = "";
            int apiLimitCnt = 999;
            int apiLineCnt = 0;

            while (rs.next()) {

                // IF Data Validation Check.
                boolean isErr = isValidIfData(rs);
                if (isErr) {
                    continue;
                }

                String grpKey = rs.getString(FR_RTL_WH_CD) + rs.getString(FR_RTL_SWH_CD) + rs.getString(TO_RTL_WH_CD) + rs.getString(TO_RTL_SWH_CD);

                if (!prevGrpKey.equals(grpKey)) {
                    // Header
                    if (pMsg == null) {
                        pMsg = createHeaderParam(rs);
                        apiLineCnt = 0;
                        createDetailParam(pMsg, rs, apiLineCnt);
                        apiLineCnt++;
                    } else {
                        isErr = callPrUpdateAPI(pMsg, trxId);
                        if (isErr) {
                            rollback();
                        } else {
                            // START 2022/07/12 F.Fadriquela [QC#60262, ADD]
                            this.successCount++;
                            // END 2022/07/12 F.Fadriquela [QC#60262, ADD]
                            commit();
                        }
                        pMsg = createHeaderParam(rs);
                        apiLineCnt = 0;
                        createDetailParam(pMsg, rs, apiLineCnt);
                        apiLineCnt++;
                    }
                    prevGrpKey = grpKey;
                } else {
                    // Detail
                    createDetailParam(pMsg, rs, apiLineCnt);
                    apiLineCnt++;
                }

                // API Line Limit
                if (apiLimitCnt == pMsg.prchReqInfo.getValidCount()) {
                    isErr = callPrUpdateAPI(pMsg, trxId);
                    if (isErr) {
                        rollback();
                    } else {
                        // START 2022/07/12 F.Fadriquela [QC#60262, ADD]
                        this.successCount++;
                        // END 2022/07/12 F.Fadriquela [QC#60262, ADD]
                        commit();
                    }
                    pMsg = null;
                    prevGrpKey = "";
                    apiLineCnt = 0;
                }
            }

            if (pMsg != null) {
                boolean isErr = callPrUpdateAPI(pMsg, trxId);
                if (isErr) {
                    rollback();
                } else {
                    // START 2022/07/12 F.Fadriquela [QC#60262, ADD]
                    this.successCount++;
                    // END 2022/07/12 F.Fadriquela [QC#60262, ADD]
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private NPZC103001PMsg createHeaderParam(ResultSet rs) throws SQLException {

        String frWhOwnrCd = rs.getString(FR_WH_OWNR_CD);
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.salesDate);
        // START 2022/07/05 F.Fadriquela [QC#60262, MOD]
        //ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        //ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.WH_TRANSFER);
        //ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, varVal);
        //ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, varVal);
        //ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.WH_PLANNING);
        //if (WH_OWNR.DBS.equals(frWhOwnrCd)) {
        //    ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
        //} else {
        //    ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.ENTERED);
        //}
        if (RTL_WH_CATG.TECH_CAR_STOCK.equals(rs.getString(TO_RTL_WH_CATG_CD)) || RTL_WH_CATG.CUSTOMER.equals(rs.getString(TO_RTL_WH_CATG_CD))) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_REQUEST);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.MIN_MAX);
            ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, rs.getString(TO_TECH_TOC_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.TECH_PLANNING);
            if (RTL_WH_CATG.CUSTOMER.equals(rs.getString(TO_RTL_WH_CATG_CD))) {
                ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, rs.getString(TECH_NM));
            }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.WH_TRANSFER);
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.WH_PLANNING);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, rs.getString(FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, rs.getString(SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, rs.getString(THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, rs.getString(FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, rs.getString(LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, rs.getString(ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, rs.getString(CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, rs.getString(ST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, rs.getString(PROV_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, rs.getString(CNTY_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, rs.getString(POST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, rs.getString(CTRY_CD));
        }
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, varVal);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, varVal);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
        // END 2022/07/05 F.Fadriquela [QC#60262, MOD]
        // START 2022/07/15 F.Fadriquela [QC#60262, DEL]
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, rs.getString(FIRST_LINE_ADDR));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, rs.getString(SCD_LINE_ADDR));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, rs.getString(FRTH_LINE_ADDR));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, rs.getString(LOC_NM));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, rs.getString(ADDL_LOC_NM));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, rs.getString(CTY_ADDR));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, rs.getString(ST_CD));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, rs.getString(PROV_NM));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, rs.getString(CNTY_NM));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, rs.getString(POST_CD));
        //ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, rs.getString(CTRY_CD));
        // END 2022/07/15 F.Fadriquela [QC#60262, DEL]

        return pMsg;
    }

    private void createDetailParam(NPZC103001PMsg pMsg, ResultSet rs, int apiLineCnt) throws SQLException {

        NPZC103001_prchReqInfoPMsg prInfo = pMsg.prchReqInfo.no(apiLineCnt);
        // START 2022/07/05 F.Fadriquela [QC#60262, MOD]
        //ZYPEZDItemValueSetter.setValue(prInfo.prchReqLineTpCd, PRCH_REQ_LINE_TP.GOODS_2);
        String rtlWhCatgCd = rs.getString(TO_RTL_WH_CATG_CD);
        if (RTL_WH_CATG.TECH_CAR_STOCK.equals(rtlWhCatgCd) || RTL_WH_CATG.CUSTOMER.equals(rtlWhCatgCd)) {
            DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(getGlobalCompanyCode(), GRP_ID_NPZC1170, REQ_TP_MINMAX);
            ZYPEZDItemValueSetter.setValue(prInfo.rqstRcvTm, convertRequestTime(dsCondConstTMsg.dsCondConstValTxt_03.getValue()));
            ZYPEZDItemValueSetter.setValue(prInfo.shpgSvcLvlCd, dsCondConstTMsg.dsCondConstValTxt_06.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(prInfo.prchReqLineTpCd, PRCH_REQ_LINE_TP.GOODS_2);
        }
        // END 2022/07/05 F.Fadriquela [QC#60262, MOD]
        ZYPEZDItemValueSetter.setValue(prInfo.rqstRcvDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(prInfo.procrTpCd, PROCR_TP.WAREHOUSE);
        ZYPEZDItemValueSetter.setValue(prInfo.destInvtyLocCd, rs.getString(TO_RTL_WH_CD) + rs.getString(TO_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.srcInvtyLocCd, rs.getString(FR_RTL_WH_CD) + rs.getString(FR_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqQty, rs.getBigDecimal(PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqDispQty, rs.getBigDecimal(PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(prInfo.prchReqDsplUomCd, "EA");
        ZYPEZDItemValueSetter.setValue(prInfo.ccyCd, CCY.US_DOLLAR);
        ZYPEZDItemValueSetter.setValue(prInfo.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(prInfo.fromStkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(prInfo.toStkStsCd, STK_STS.GOOD);

        pMsg.prchReqInfo.setValidCount(apiLineCnt + 1);

    }

    private boolean callPrUpdateAPI(NPZC103001PMsg pMsg, BigDecimal tranId) {

        NPZC103001 prUpdateApi = new NPZC103001();
        prUpdateApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);

            for (S21ApiMessage s21ApiMsg : msgList) {
                String msgId = s21ApiMsg.getXxMsgid();
                String msg = S21MessageFunc.clspGetMessage(msgId);
                setErrMsg(NPAM1653E, new String[] {msg, this.interfaceId, tranId.toPlainString() });
            }
            return true;
        }

        return false;
    }

    private void setErrMsg(String errMsgId, String errMsgParam[]) {

        S21InfoLogOutput.println(errMsgId, errMsgParam);
        errMsgList.add(S21MessageFunc.clspGetMessage(errMsgId, errMsgParam));

        errorCount++;
        rollback();
    }

    private boolean isValidIfData(ResultSet rs) throws SQLException {

        boolean isErr = false;

        String mdseCd = rs.getString(MDSE_CD);
        String frRtlWhCd = rs.getString(FR_RTL_WH_CD);
        String frRtlSwhCd = rs.getString(FR_RTL_SWH_CD);
        String frWhRgCd = rs.getString(SHIP_FR_WH_RG_CD);
        String toRtlWhCd = rs.getString(TO_RTL_WH_CD);
        String toRtlSwhCd = rs.getString(TO_RTL_SWH_CD);
        String toWhRgCd = rs.getString(SHIP_TO_WH_RG_CD);
        BigDecimal prchReqQty = rs.getBigDecimal(PRCH_REQ_QTY);
        String ifMdseCd = rs.getString(IF_MDSE_CD);

        // MDSE_CD
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            isErr = true;
        }
        // FR_RTL_WH_CD(Region Mapping)
        if (!ZYPCommonFunc.hasValue(frRtlWhCd)) {
            isErr = true;
        }
        // TO_RTL_WH_CD(Region Mapping)
        if (!ZYPCommonFunc.hasValue(toRtlWhCd)) {
            isErr = true;
        }
        // PRCH_REQ_QTY
        if (!ZYPCommonFunc.hasValue(prchReqQty) || BigDecimal.ZERO.compareTo(prchReqQty) >= 0) {
            isErr = true;
        }

        if (isErr) {
            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                sb.append("\tMDSE_CD:[" + mdseCd + "]");
            } else {
                sb.append("\tIF_MDSE_CD:[" + ifMdseCd + "] There is no S21 MDSE Master.");
            }
            if (ZYPCommonFunc.hasValue(frRtlWhCd)) {
                sb.append(" / SRC_INVTY_LOC_CD:" + frRtlWhCd + frRtlSwhCd);
            } else {
                sb.append(" / TRD_PTNR_WH_RG_XREF:[" + frWhRgCd + "] There is no Region Mapping.");
            }
            if (ZYPCommonFunc.hasValue(toRtlWhCd)) {
                sb.append(" / DEST_INVTY_LOC_CD:" + toRtlWhCd + toRtlSwhCd);
            } else {
                sb.append(" / TRD_PTNR_WH_RG_XREF:[" + toWhRgCd + "] There is no Region Mapping.");
            }
            if (ZYPCommonFunc.hasValue(prchReqQty)) {
                sb.append(" / QTY:" + prchReqQty.toPlainString());
            } else {
                sb.append(" / QTY: Null");
            }
            errMsgList.add(sb.toString());
        }

        return isErr;
    }

    private void sendErrMail() {
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, ML_GRP_CD_FROM);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, ML_GRP_CD_TO);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            return;
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, ML_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NPZM0161E);
        }

        String crlf = System.getProperty("line.separator");

        // Message
        StringBuffer msg = new StringBuffer();
        for (String errMsg : this.errMsgList) {
            // line end.
            msg.append(errMsg);
            msg.append(crlf);
        }

        template.setTemplateParameter(EMAIL_PARAM_BATCH_ID, BUSINESS_ID);
        template.setTemplateParameter(EMAIL_PARAM_MESSAGE, msg.toString());

        // Set mail subject
        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }

    // START 2022/07/05 F.Fadriquela [QC#60262, ADD]
    /**
     * getDsCondConstTMsg
     * @param glblCmpyCd String
     * @param dsCondConstGrpId String
     * @param dsCondConstCd String
     * @return DS_COND_CONSTTMsg
     */
    private DS_COND_CONSTTMsg getDsCondConstTMsg(String glblCmpyCd, String dsCondConstGrpId, String dsCondConstCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, dsCondConstGrpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, dsCondConstCd);
        DS_COND_CONSTTMsg resultTMsg = (DS_COND_CONSTTMsg) EZDTBLAccessor.findByKey(dsCondConstTMsg);

        if (resultTMsg == null) {
            throw new S21AbendException(NZZM0010E, new String[] {DS_COND_CONST + dsCondConstCd});
        }

        return resultTMsg;
    }

    /**
     * convert Request Time
     * @param time String 
     * @return String
     */
    public static String convertRequestTime(String time) {

        try {
            SimpleDateFormat sfd = new SimpleDateFormat(DATE_FORMAT_HHMM_AM, Locale.US);
            Date rqstRcvTm = sfd.parse(time);
            sfd = new SimpleDateFormat(DATE_FORMAT_HHMM);
            return sfd.format(rqstRcvTm);
        } catch (ParseException e) {
            return DEFAULT_TIME_STR;
        }
    }
    // END 2022/07/05 F.Fadriquela [QC#60262, ADD]
}
