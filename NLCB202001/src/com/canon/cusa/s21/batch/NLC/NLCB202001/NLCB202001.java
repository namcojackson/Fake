/**
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB202001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NLCI2020_01TMsg;
import business.db.NLCI2021_01TMsg;

import com.canon.cusa.s21.batch.NLC.NLCB202001.constant.NLCB202001Constant;
import com.canon.cusa.s21.common.NLX.NLXC043001.S21TransactionParamTableAccessor;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_RSN;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for Demand Master to Baxter
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/07/2021   CITS            A.Marte         Create          QC#58267
 * 05/25/2022   CITS            F.Fadriquela    Update          QC#60051
 * 06/16/2022   CITS            F.Fadriquela    Update          QC#60241
 * 09/20/2023   CITS            F.Komaki        Update          QC#61856
 * 11/06/2023   CITS            K.Ikeda         Update          QC#61856
 *</pre>
 */

public class NLCB202001 extends S21BatchMain {

   /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Transaction Table Accessor */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** Transaction Param Table Accessor */
    private S21TransactionParamTableAccessor trxParamAccess = new S21TransactionParamTableAccessor();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId01 = null;

    /** Interface ID */
    private String interfaceId02 = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** */
    private List<String> inclDsSoLineStsCdList = new ArrayList<String>();

    /** */
    private List<String> inclMdseItemStsList = new ArrayList<String>();

    /** */
    private List<String> inclMdseItemTpList = new ArrayList<String>();

    /** */
    private List<String> incluMdseItemClsTpList = new ArrayList<String>();

    /** */
    private List<String> inclPrchReqTp = new ArrayList<String>();

    // START 2022/05/25 F.Fadriquela [QC#60051, ADD]
    /** */
    private List<String> inclDsOrdCatgCd = new ArrayList<String>();
    // 2023/11/06 QC#61856 K.Ikeda STERT
    // /** */
    // private List<String> inclFsrStsCd = new ArrayList<String>();
    // END 2022/05/25 F.Fadriquela [QC#60051, ADD]
    
    /** */
    private List<String> inclFsrVisitStsCd = new ArrayList<String>();
    // 2023/11/06 QC#61856 K.Ikeda END

    // START 2022/06/16 F.Fadriquela [QC#60241, ADD]
    private List<String> inclTrxRsnCd = new ArrayList<String>();
    // END 2022/06/16 F.Fadriquela [QC#60241, ADD]

    /** */
    private List<String> exclPrchReqSrcTpCd = new ArrayList<String>();


    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();

        // Get Global Company Code Parameter
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            S21InfoLogOutput.println(NLCB202001Constant.ZZM9000E, new String[]{NLCB202001Constant.PARAM_NM_GLBL_CMPY_CD });
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLCB202001Constant.ZZM9000E, new String[] {NLCB202001Constant.PARAM_NM_GLBL_CMPY_CD});
        }

        // Get Interface ID Parameter: NLCI2020
        interfaceId01 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(interfaceId01) || !NLCB202001Constant.IF_NLCI2020.equals(interfaceId01)) {
            S21InfoLogOutput.println(NLCB202001Constant.ZZM9000E, new String[]{NLCB202001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB202001Constant.PARAM_NM_VAR_USER1 + ")" });
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLCB202001Constant.ZZM9000E, new String[] {NLCB202001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB202001Constant.PARAM_NM_VAR_USER1 + ")" });
        }

        // Get Interface ID Parameter: NLCI2021
        interfaceId02 = getUserVariable2();
        if (!ZYPCommonFunc.hasValue(interfaceId02) || !NLCB202001Constant.IF_NLCI2021.equals(interfaceId02)) {
            S21InfoLogOutput.println(NLCB202001Constant.ZZM9000E, new String[]{NLCB202001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB202001Constant.PARAM_NM_VAR_USER2 + ")" });
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLCB202001Constant.ZZM9000E, new String[] {NLCB202001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB202001Constant.PARAM_NM_VAR_USER2 + ")" });
        }

        // initialize values for SQL condition
        //INCLUDE_DS_SO_LINE_STS_CD_LIST
        inclDsSoLineStsCdList = Arrays.asList(NLCB202001Constant.INCLUDE_DS_SO_LINE_STS_CD_LIST.split(","));

        //INCLUDE_MDSE_ITEM_STS
        inclMdseItemStsList = Arrays.asList(MDSE_ITEM_STS.PRELAUNCH_APPROVED
                                                            , MDSE_ITEM_STS.CURRENT
                                                            , MDSE_ITEM_STS.CURRENT_AT_RISK
                                                            , MDSE_ITEM_STS.NON_SERVICE
                                                            , MDSE_ITEM_STS.DESUPPORTED);
        //INCLUDE_MDSE_ITEM_TP
        inclMdseItemTpList = Arrays.asList(MDSE_ITEM_TP.PARTS, MDSE_ITEM_TP.PART_CONSUMABLES);

        //INCLUDE_MDSE_ITEM_TP
        incluMdseItemClsTpList = Arrays.asList(MDSE_ITEM_CLS_TP.OCEOEM, "OD");

        //INCLUDE_PRCH_REQ_TP
        inclPrchReqTp = Arrays.asList(PRCH_REQ_TP.PREMIUM_RUSH, PRCH_REQ_TP.RUSH);

        // START 2022/05/25 F.Fadriquela [QC#60051, ADD]
        //INCLUDE_DS_ORD_CATG_CD
        inclDsOrdCatgCd = Arrays.asList(DS_ORD_CATG.CONTRACT_SUPPLY_CSA, DS_ORD_CATG.PARTS_CSA);

        // 2023/09/20 QC#61856 START
        //INCLUDE_FSR_STS_CD
        // inclFsrStsCd = Arrays.asList(NLCB202001Constant.FSR_STS_CD_COMPLETED, NLCB202001Constant.FSR_STS_CD_CLOSED);
        // inclFsrStsCd = Arrays.asList(NLCB202001Constant.FSR_STS_CD_CANCELLED, NLCB202001Constant.FSR_STS_CD_CLOSED);
        // 2023/09/20 QC#61856 END
        // END 2022/05/25 F.Fadriquela [QC#60051, ADD]

        // 2023/11/06 QC#61856 K.Ikeda STERT
        inclFsrVisitStsCd = Arrays.asList(NLCB202001Constant.FSR_VISIT_STS_CD_COMPLETED, NLCB202001Constant.FSR_VISIT_STS_CD_CLOSED);
        // 2023/11/06 QC#61856 K.Ikeda END

        //EXCLUDE_PRCH_REQ_SRC_TP
        exclPrchReqSrcTpCd = Arrays.asList(PRCH_REQ_SRC_TP.TECH_PLANNING, PRCH_REQ_SRC_TP.WH_PLANNING);

        // START 2022/06/16 F.Fadriquela [QC#60241, ADD]
        //INCLUDE_TRX_RSN_CD
        inclTrxRsnCd = Arrays.asList(TRX_RSN.ADJUSTMENT, TRX_RSN.REPLEN_TOOL_EXPENSE_OUT);
        // END 2022/06/16 F.Fadriquela [QC#60241, ADD]
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {
        try {
            processDemand();

            commit();

        } catch (S21AbendException e) {
            rollback();
            throw e;
        }

    }

    /**
     * Term Routine.
     */
    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);

    }

    /** Startup
     * @param args arguments
     */
    public static void main(String[] args) {
        new NLCB202001().executeBatch(NLCB202001.class.getSimpleName());

    }

    /**
     * processDemand
     * @param execParam
     */
    private void processDemand() {
         // Set the fetch size.
        S21SsmExecutionParameter execParam = null;
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(NLCB202001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get Demand Information
            Map<String, Object> queryParam = new HashMap<String, Object>();

            // DB constants
            queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
            queryParam.put("FLG_ON_Y", ZYPConstant.FLG_ON_Y);
            queryParam.put("FLG_OFF_N", ZYPConstant.FLG_OFF_N);
            queryParam.put("GOOD", STK_STS.GOOD);
            queryParam.put("CANCELLED", ORD_LINE_STS.CANCELLED);
            queryParam.put("ORD_HDR_DPLY_STS_BOOK", ORD_HDR_DPLY_STS.BOOKED);
            queryParam.put("MDSE_ITEM_CLS_TP_OCEOEM", MDSE_ITEM_CLS_TP.OCEOEM);
            queryParam.put("VALIDATED", SHPG_STS.VALIDATED);
            queryParam.put("INSHED", SHPG_STS.INSHED);
            queryParam.put("PRCH_REQ_REC_TP_CD", PRCH_REQ_REC_TP.TECH_REQUEST);
            queryParam.put("PRCH_REQ_REL_STS_COMPLEATED", PRCH_REQ_REL_STS.COMPLEATED);
            queryParam.put("PRCH_REQ_APVL_STS_CD", PRCH_REQ_APVL_STS.ENTERED);
            queryParam.put("PRCH_REQ_SRC_TP_PLANNING", PRCH_REQ_SRC_TP.TECH_PLANNING);
            queryParam.put("PRCH_REQ_TP_KT", PRCH_REQ_TP.KITTING);

            //other constants
            queryParam.put("S21", NLCB202001Constant.S21);
            queryParam.put("hyphen", NLCB202001Constant.VAL_HYPHEN);
            queryParam.put("SO", NLCB202001Constant.VAL_SO);
            queryParam.put("SR", NLCB202001Constant.VAL_SR);
            queryParam.put("SVC_TERM_COND_ATTRB_PK", NLCB202001Constant.VAL_SVC_TERM_COND_ATTRB_PK);
            queryParam.put("WH_RG_CD", NLCB202001Constant.VAL_WH_RG_CD);
            queryParam.put("RTL_WH_CD", NLCB202001Constant.VAL_RTL_WH_CD);
            queryParam.put("RTL_SWH_CD", NLCB202001Constant.VAL_RTL_SWH_CD);
            queryParam.put("US", NLCB202001Constant.VAL_US_CTRY_CD);
            queryParam.put("INVENTORY_TRANSFER", NLCB202001Constant.VAL_DS_ORD_TP_INVENTORY_REQUEST);
            queryParam.put("SCE_ORD_TP_CD", NLCB202001Constant.VAL_SCE_ORD_TP_CD);
            // 2023/09/20 QC#61856 START
            // queryParam.put("PRT_USED_BY_TECH_CD", NLCB202001Constant.VAL_PRT_USED_BY_TECH_CD);
            // queryParam.put("SVC_BR_CD", NLCB202001Constant.VAL_SVC_BR_CD);
            // 2023/11/06 QC#61856 K.Ikeda START
            // queryParam.put("FSR_VISIT_STS_CD", NLCB202001Constant.FSR_VISIT_STS_CD_CLOSED);
            queryParam.put("FSR_VISIT_STS_CD", inclFsrVisitStsCd);
            // 2023/11/06 QC#61856 K.Ikeda END
            // 2023/09/20 QC#61856 END
            queryParam.put("TR", NLCB202001Constant.VAL_SCE_ORD_TP_CD + NLCB202001Constant.VAL_SPACE);

            // list parameters
            queryParam.put("DS_SO_LINE_STS_CD", inclDsSoLineStsCdList);
            queryParam.put("MDSE_ITEM_CLS_TP_CD", incluMdseItemClsTpList);
            queryParam.put("MDSE_ITEM_STS_CD", inclMdseItemStsList);
            queryParam.put("MDSE_ITEM_TP_CD", inclMdseItemTpList);
            queryParam.put("PRCH_REQ_SRC_TP_CD", exclPrchReqSrcTpCd);
            queryParam.put("PRCH_REQ_TP_CD", inclPrchReqTp);
            // START 2022/05/25 F.Fadriquela [QC#60051, ADD]
            queryParam.put("DS_ORD_CATG_CD", inclDsOrdCatgCd);
            // 2023/09/20 QC#61856 START
            // queryParam.put("FSR_STS_CD", inclFsrStsCd);
            // 2023/09/20 QC#61856 END
            // END 2022/05/25 F.Fadriquela [QC#60051, ADD]
            // START 2022/06/16 F.Fadriquela [QC#60241, ADD]
            queryParam.put("PPS_TECH_TM", NLCB202001Constant.VAL_PPS_TECH_TM);
            // 2023/11/06 QC#61856 K.Ikeda START
            // queryParam.put("PPS", NLCB202001Constant.VAL_PPS);
            // 2023/11/06 QC#61856 K.Ikeda END
            queryParam.put("TRX_CD_ADJ", TRX.ADJUSTMENT);
            queryParam.put("TRX_RSN_CD_TOOL_EXPN", TRX_RSN.REPLEN_TOOL_EXPENSE_OUT);
            queryParam.put("TRX_RSN_CD_FIELD_ADJ", TRX_RSN.ADJUSTMENT);
            queryParam.put("TOOL_EXPENSE", NLCB202001Constant.VAL_TOOL_EXPENSE);
            queryParam.put("FIELD_ADJUSTMENT", NLCB202001Constant.VAL_FIELD_ADJUSTMENT);
            queryParam.put("LOC_STS_CD_DC_STOCK", LOC_STS.DC_STOCK);
            queryParam.put("RTL_SWH_CD_GOOD_PARTS", NLCB202001Constant.RTL_SWH_CD_GOOD_PARTS);
            queryParam.put("TRX_RSN_CD", inclTrxRsnCd);
            // END 2022/06/16 F.Fadriquela [QC#60241, ADD]

            prdStmt = ssmLLClient.createPreparedStatement("getDemandInfo", queryParam, execParam);
            rs = prdStmt.executeQuery();

            // Initialize Transaction ID.
            BigDecimal trxId = trxAccess.getNextTransactionId();
            Integer seqCount = 1;

            trxAccess.createIntegrationRecordForBatch(interfaceId01, trxId);
            trxAccess.createIntegrationRecordForBatch(interfaceId02, trxId);

            while (rs.next()) {

                // Insert to NLCI2020_01
                NLCI2020_01TMsg if01TMsg = createNLCI2020_01(seqCount, rs, trxId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    S21InfoLogOutput.println(NLCB202001Constant.NLAM1295E, new String[]{NLCB202001Constant.TBL_NLCI2020_01});
                    throw new S21AbendException(NLCB202001Constant.NLAM1295E, new String[] {NLCB202001Constant.TBL_NLCI2020_01 });
                }

                seqCount++;
                this.successCount++;
            }

            // Insert to NLCI2021_01
            NLCI2021_01TMsg if02TMsg = createNLCI2021_01(rs, trxId, this.successCount);
            EZDTBLAccessor.insert(if02TMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if02TMsg.getReturnCode())) {
                S21InfoLogOutput.println(NLCB202001Constant.NLAM1295E, new String[]{NLCB202001Constant.TBL_NLCI2021_01});
                throw new S21AbendException(NLCB202001Constant.NLAM1295E, new String[] {NLCB202001Constant.TBL_NLCI2021_01 });
            }

            //Insert to INTERFACE_TRANSACTION_PARAM
            trxParamAccess.createIntegrationRecordForBatch(interfaceId01, trxId, ZYPDateUtil.getCurrentSystemTime(NLCB202001Constant.FMT_YYYYMMDDHHMMSS));

        } catch (SQLException e) {
              EZDDebugOutput.println(NLCB202001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
              termCd = TERM_CD.ABNORMAL_END;
              sqlExceptionHandler(e);
           } finally {
               S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    /**
     * createNLCI2020_01
     * @param Integer seqCount
     * @param ResultSet rs
     * @param BigDecimal trxId
     */
    private NLCI2020_01TMsg createNLCI2020_01(Integer seqCount, ResultSet rs, BigDecimal trxId) throws SQLException {
        NLCI2020_01TMsg tMsg = new NLCI2020_01TMsg();

        // INTERFACE_ID
        tMsg.interfaceId.setValue(interfaceId01);
        // TRANSACTION_ID
        tMsg.transactionId.setValue(trxId);
        // SEGMENT_ID
        tMsg.segmentId.setValue(1);
        // UNIT_ID
        tMsg.unitId.setValue(1);
        // SEQ_NUMBER
        tMsg.seqNumber.setValue(seqCount);

        // MDSE_CD
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(NLCB202001Constant.MDSE_CD));

        // WH_RG_CD 
        ZYPEZDItemValueSetter.setValue(tMsg.whRgCd, rs.getString(NLCB202001Constant.WH_RG_CD));

        // INP_SRC_CD 
        ZYPEZDItemValueSetter.setValue(tMsg.inpSrcCd, rs.getString(NLCB202001Constant.INP_SRC_CD));

        // MAT_ORD_QTY 
        ZYPEZDItemValueSetter.setValue(tMsg.matOrdQty, rs.getBigDecimal(NLCB202001Constant.MAT_ORD_QTY));

        // MAT_ORD_DT 
        ZYPEZDItemValueSetter.setValue(tMsg.matOrdDt, rs.getString(NLCB202001Constant.MAT_ORD_DT));

        // MAT_ORD_TM 
        ZYPEZDItemValueSetter.setValue(tMsg.matOrdTm, rs.getString(NLCB202001Constant.MAT_ORD_TM));

        // MAT_SHIP_QTY
        ZYPEZDItemValueSetter.setValue(tMsg.matShipQty, rs.getBigDecimal(NLCB202001Constant.MAT_SHIP_QTY));

        // MAT_SHIP_DT
        ZYPEZDItemValueSetter.setValue(tMsg.matShipDt, rs.getString(NLCB202001Constant.MAT_SHIP_DT));

        // MAT_SHIP_TM
        ZYPEZDItemValueSetter.setValue(tMsg.matShipTm, rs.getString(NLCB202001Constant.MAT_SHIP_TM));

        // MAT_REQ_DT
        ZYPEZDItemValueSetter.setValue(tMsg.matReqDt, rs.getString(NLCB202001Constant.MAT_REQ_DT));

        // MAT_REQ_TM
        ZYPEZDItemValueSetter.setValue(tMsg.matReqTm, rs.getString(NLCB202001Constant.MAT_REQ_TM));

        // MAT_RCV_DT
        ZYPEZDItemValueSetter.setValue(tMsg.matRcvDt, rs.getString(NLCB202001Constant.MAT_RCV_DT));

        // MAT_RCV_TM
        ZYPEZDItemValueSetter.setValue(tMsg.matRcvTm, rs.getString(NLCB202001Constant.MAT_RCV_TM));

        // DMND_INFO_ID
        ZYPEZDItemValueSetter.setValue(tMsg.dmndInfoId, rs.getString(NLCB202001Constant.DMND_INFO_ID));

        // CUST_ORD_ID
        ZYPEZDItemValueSetter.setValue(tMsg.custOrdId, rs.getString(NLCB202001Constant.CUST_ORD_ID));

        // SER_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(NLCB202001Constant.SER_NUM));

        // DELY_POST_CD
        ZYPEZDItemValueSetter.setValue(tMsg.delyPostCd, rs.getString(NLCB202001Constant.DELY_POST_CD));

        // DELY_CTRY_CD
        ZYPEZDItemValueSetter.setValue(tMsg.delyCtryCd, rs.getString(NLCB202001Constant.DELY_CTRY_CD));

        // DELY_CTY_ADDR
        ZYPEZDItemValueSetter.setValue(tMsg.delyCtyAddr, rs.getString(NLCB202001Constant.DELY_CTY_ADDR));

        // DELY_ST_CD
        ZYPEZDItemValueSetter.setValue(tMsg.delyStCd, rs.getString(NLCB202001Constant.DELY_ST_CD));

        // DELY_CUST_NM
        ZYPEZDItemValueSetter.setValue(tMsg.delyCustNm, rs.getString(NLCB202001Constant.DELY_CUST_NM));

        // TRX_TP_CD
        ZYPEZDItemValueSetter.setValue(tMsg.trxTpCd, rs.getString(NLCB202001Constant.TRX_TP_CD));

        // DMND_RSP_COMIT_CD
        ZYPEZDItemValueSetter.setValue(tMsg.dmndRspComitCd, rs.getString(NLCB202001Constant.DMND_RSP_COMIT_CD));

        // COV_DAYS_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.covDaysNum, rs.getString(NLCB202001Constant.COV_DAYS_NUM));

        // COV_HOURS_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.covHoursNum, rs.getString(NLCB202001Constant.COV_HOURS_NUM));

        // MAJ_ACCT_ID
        ZYPEZDItemValueSetter.setValue(tMsg.majAcctId, rs.getString(NLCB202001Constant.MAJ_ACCT_ID));

        // DMND_PRTY_CD
        ZYPEZDItemValueSetter.setValue(tMsg.dmndPrtyCd, rs.getString(NLCB202001Constant.DMND_PRTY_CD));

        // DMND_PROD_CD
        ZYPEZDItemValueSetter.setValue(tMsg.dmndProdCd, rs.getString(NLCB202001Constant.DMND_PROD_CD));

        // SPEC_RG_CD
        ZYPEZDItemValueSetter.setValue(tMsg.specRgCd, rs.getString(NLCB202001Constant.SPEC_RG_CD));

        // SPEC_SITE_CD
        ZYPEZDItemValueSetter.setValue(tMsg.specSiteCd, rs.getString(NLCB202001Constant.SPEC_SITE_CD));

        // SHIP_FROM_RG_CD
        ZYPEZDItemValueSetter.setValue(tMsg.shipFromRgCd, rs.getString(NLCB202001Constant.SHIP_FROM_RG_CD));

        // SHIP_FROM_SITE_CD
        ZYPEZDItemValueSetter.setValue(tMsg.shipFromSiteCd, rs.getString(NLCB202001Constant.SHIP_FROM_SITE_CD));

        // SHIP_TO_RG_CD
        ZYPEZDItemValueSetter.setValue(tMsg.shipToRgCd, rs.getString(NLCB202001Constant.SHIP_TO_RG_CD));

        // SHIP_TO_SITE_CD
        ZYPEZDItemValueSetter.setValue(tMsg.shipToSiteCd, rs.getString(NLCB202001Constant.SHIP_TO_SITE_CD));

        // NTWK_CONFIG_NM
        ZYPEZDItemValueSetter.setValue(tMsg.ntwkConfigNm, rs.getString(NLCB202001Constant.NTWK_CONFIG_NM));

        // CUST_RPT_HIT_FLG
        ZYPEZDItemValueSetter.setValue(tMsg.custRptHitFlg, rs.getString(NLCB202001Constant.CUST_RPT_HIT_FLG));

        return tMsg;
    }

    /**
     * createNLCI2021_01
     * @param ResultSet rs
     * @param BigDecimal trxId
     * @param Integer recordCount
     */
    private NLCI2021_01TMsg createNLCI2021_01(ResultSet rs, BigDecimal trxId, Integer recordCount) throws SQLException {
        NLCI2021_01TMsg tMsg = new NLCI2021_01TMsg();

        // INTERFACE_ID
        tMsg.interfaceId.setValue(interfaceId02);
        // TRANSACTION_ID
        tMsg.transactionId.setValue(trxId);
        // SEGMENT_ID
        tMsg.segmentId.setValue(1);
        // UNIT_ID
        tMsg.unitId.setValue(1);
        // SEQ_NUMBER
        tMsg.seqNumber.setValue(1);
        // INVTY_TOT_REC_CNT
        tMsg.dmndTotRecCnt.setValue(recordCount);

        return tMsg;
    }

}
