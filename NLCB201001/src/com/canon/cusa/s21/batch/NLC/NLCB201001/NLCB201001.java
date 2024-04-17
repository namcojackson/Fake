/*
 * <Pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLC.NLCB201001;

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

import business.db.NLCI2010_01TMsg;
import business.db.NLCI2011_01TMsg;

import com.canon.cusa.s21.batch.NLC.NLCB201001.constant.NLCB201001Constant;
import com.canon.cusa.s21.common.NLX.NLXC043001.S21TransactionParamTableAccessor;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Batch Program Class for Inventory Master to Baxter
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/13/2021   CITS            A.Marte         Create          QC#58267
 * 11/04/2021   CITS            A.Marte         Update          QC#58989-2
 * 06/22/2022   CITS            R.Azucena       Update          QC#60240
 * 07/06/2022   CITS            R.Azucena       Update          QC#60240-1
 * 07/21/2022   CITS            R.Azucena       Update          QC#60240-2
 * 04/03/2024   CITS            D.Quan          Update          QC#63814
 * 
 *</pre>
 */

public class NLCB201001 extends S21BatchMain {

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
    private List<String> incluMdseItemClsTpList = new ArrayList<String>();

    /** */
    private List<String> inclMdseItemStsList = new ArrayList<String>();

    /** */
    private List<String> inclMdseItemTpList = new ArrayList<String>();

    /** */
    private List<String> exclPoLineTp = new ArrayList<String>();

    /** */
    private List<String> inclPrchReqSts = new ArrayList<String>();

    /** */
    private List<String> inclWrkOrdSts = new ArrayList<String>();


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
            S21InfoLogOutput.println(NLCB201001Constant.ZZM9000E, new String[]{NLCB201001Constant.PARAM_NM_GLBL_CMPY_CD });
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLCB201001Constant.ZZM9000E, new String[] {NLCB201001Constant.PARAM_NM_GLBL_CMPY_CD});
        }

        // Get Interface ID Parameter: NLCI2010
        interfaceId01 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(interfaceId01) || !NLCB201001Constant.IF_NLCI2010.equals(interfaceId01)) {
            S21InfoLogOutput.println(NLCB201001Constant.ZZM9000E, new String[]{NLCB201001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB201001Constant.PARAM_NM_VAR_USER1 + ")" });
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLCB201001Constant.ZZM9000E, new String[] {NLCB201001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB201001Constant.PARAM_NM_VAR_USER1 + ")" });
        }

        // Get Interface ID Parameter: NLCI2011
        interfaceId02 = getUserVariable2();
        if (!ZYPCommonFunc.hasValue(interfaceId02) || !NLCB201001Constant.IF_NLCI2011.equals(interfaceId02)) {
            S21InfoLogOutput.println(NLCB201001Constant.ZZM9000E, new String[]{NLCB201001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB201001Constant.PARAM_NM_VAR_USER2 + ")" });
            termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLCB201001Constant.ZZM9000E, new String[] {NLCB201001Constant.PARAM_NM_INTERFACE_ID + " (" + NLCB201001Constant.PARAM_NM_VAR_USER2 + ")" });
        }

        // initialize values for SQL condition
        //INCLUDE_MDSE_ITEM_CLS_TP_LST
        incluMdseItemClsTpList = Arrays.asList(MDSE_ITEM_CLS_TP.OCEOEM, "OD");

        //INCLUDE_MDSE_ITEM_STS
        inclMdseItemStsList = Arrays.asList(MDSE_ITEM_STS.PRELAUNCH_APPROVED
                                                            , MDSE_ITEM_STS.CURRENT
                                                            , MDSE_ITEM_STS.CURRENT_AT_RISK
                                                            , MDSE_ITEM_STS.NON_SERVICE
                                                            , MDSE_ITEM_STS.DESUPPORTED);
        //INCLUDE_MDSE_ITEM_TP
        inclMdseItemTpList = Arrays.asList(MDSE_ITEM_TP.PARTS, MDSE_ITEM_TP.PART_CONSUMABLES);

        //EXCLUDE_PO_LINE_TP
        exclPoLineTp = Arrays.asList(PO_LINE_TP.EXPENSE, PO_LINE_TP.EXPENSE_WITH_RECEIPT);

        //INCLUDE_PRCH_REQ_STS
        inclPrchReqSts = Arrays.asList(PRCH_REQ_STS.OPEN, PRCH_REQ_STS.RELEASE_ERROR);

        //INCLUDE_WRK_ORD_STS
        inclWrkOrdSts = Arrays.asList(WRK_ORD_STS.SAVED, WRK_ORD_STS.VALIDATED, WRK_ORD_STS.SHIPPED, WRK_ORD_STS.RECEIVING);
    }

    @Override
    protected void mainRoutine() {

        try {
            processInvty();

            commit();

        } catch (S21AbendException e) {
            rollback();
            throw e;
        }

    }

    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /** Startup
     * @param args arguments
     */
    public static void main(String[] args) {
        new NLCB201001().executeBatch(NLCB201001.class.getSimpleName());

    }

    /**
     * processInvty
     * @param execParam
     */
    private void processInvty() {
         // Set the fetch size.
        S21SsmExecutionParameter execParam = null;
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(NLCB201001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get Invty Information
            Map<String, Object> queryParam = new HashMap<String, Object>();

            // DB constants
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("flagY", ZYPConstant.FLG_ON_Y);
            queryParam.put("goodStkSts", STK_STS.GOOD);
            queryParam.put("whOwnrCd", WH_OWNR.MNX);
            queryParam.put("openPoHdr", PO_HDR_STS.OPEN);
            queryParam.put("prchReqRecTp", PRCH_REQ_REC_TP.PO_REQUISITION);
            queryParam.put("prchReqApvlSts", PRCH_REQ_APVL_STS.ENTERED);
            queryParam.put("prchReqSrcTp", PRCH_REQ_SRC_TP.WH_PLANNING);
            queryParam.put("whTransfer", PRCH_REQ_TP.WH_TRANSFER);
            queryParam.put("expenseOrder", PRCH_REQ_TP.EXPENSE_ORDER);
            queryParam.put("openStatus", PRCH_REQ_STS.OPEN);
            queryParam.put("kitting", PRCH_REQ_TP.KITTING);
            queryParam.put("kitDsWrkOrdTp", DS_WRK_ORD_TP.KIT);
            queryParam.put("shippedShpgSts", SHPG_STS.SHIPPED);
            queryParam.put("invtyReq", PRCH_REQ_REC_TP.INVENTORY_REQUEST);
            queryParam.put("dcTransferReceive", INBD_VIS_EVENT.DC_TRANSFER_RECEIVE);
            queryParam.put("stockInDc", INBD_VIS_DATA_TP.STOCK_IN_DC);
            queryParam.put("dcStock", LOC_STS.DC_STOCK);

            //other constants
            queryParam.put("S21", NLCB201001Constant.S21);
            queryParam.put("inboundInvty", NLCB201001Constant.VAL_INBD_INVTY_STS_CD);
            queryParam.put("onhandInvty", NLCB201001Constant.VAL_ONHND_INVTY_STS_CD);
            queryParam.put("allocatedInvty", NLCB201001Constant.VAL_ALLOCATED_INVTY_STS_CD);
            queryParam.put("stkLocCd", NLCB201001Constant.VAL_STK_LOC_CD);
            queryParam.put("3Z1", NLCB201001Constant.VAL_3Z1_WH);
            queryParam.put("2P1", NLCB201001Constant.VAL_2P1_WH);
            queryParam.put("ZERO", NLCB201001Constant.VAL_ZERO);
            // START 2021/11/04 A.Marte [QC#58989-2, ADD]
            queryParam.put("emptyList", NLCB201001Constant.VAL_EMPTY);
            // END 2021/11/04 A.Marte [QC#58989-2, ADD]
            // START 2022/06/22 R.Azucena [QC#60240 ADD]
            queryParam.put("prchReqLineStsOpen", PRCH_REQ_LINE_STS.OPEN);
            queryParam.put("prchReqLineStsPartialRcvd", PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);
            queryParam.put("swhCdG", NLCB201001Constant.VAL_SWH_CD_G);
            queryParam.put("techReq", PRCH_REQ_REC_TP.TECH_REQUEST);
            // START 2022/07/06 R.Azucena [QC#60240-1 DEL]
            // queryParam.put("subWarehouse", NLCB201001Constant.VAL_SUBWAREHOUSE);
            // END 2022/07/06 R.Azucena [QC#60240-1 DEL]
            // START 2022/07/06 R.Azucena [QC#60240-2 ADD]
            String rtlWhCatgCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_RTL_WH_CATG_CD, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(rtlWhCatgCd)) {
                List<String> rtlWhCatgCdList = Arrays.asList(rtlWhCatgCd.split(","));
                queryParam.put("rtlWhCatgCdList", rtlWhCatgCdList);
            } else {
                queryParam.put("rtlWhCatgCdList", null);
            }

            queryParam.put("goodNm", NLCB201001Constant.VAL_GOOD);
            // END 2022/07/06 R.Azucena [QC#60240-2 ADD]
            queryParam.put("createMaterial", NLCB201001Constant.VAL_CREATE_MATERIAL);

            List<String> techReqPrchReqTpCdList = Arrays.asList(PRCH_REQ_TP.PREMIUM_RUSH, PRCH_REQ_TP.RUSH, PRCH_REQ_TP.STANDARD, PRCH_REQ_TP.MIN_MAX);
            queryParam.put("techReqPrchReqTpCdList", techReqPrchReqTpCdList);

            List<String> techReqPrchReqApvlStsCdList = Arrays.asList(PRCH_REQ_APVL_STS.APPROVED, PRCH_REQ_APVL_STS.PRE_APPROVED);
            queryParam.put("techReqPrchReqApvlStsCdList", techReqPrchReqApvlStsCdList);

            List<String> techReqPrchReqLineStsCdList = Arrays.asList(PRCH_REQ_LINE_STS.OPEN, PRCH_REQ_LINE_STS.AWAITING_SHIPPING,
                    PRCH_REQ_LINE_STS.PARTIALLY_SHIPPED, PRCH_REQ_LINE_STS.SHIPPED, PRCH_REQ_LINE_STS.PARTIALLY_RECEIVED);
            queryParam.put("techReqPrchReqLineStsCdList", techReqPrchReqLineStsCdList);

            List<String> poTechLineStsCdList = Arrays.asList(PO_LINE_STS.OPEN, PO_LINE_STS.OPEN_FOR_RECEIPT);
            queryParam.put("poTechLineStsCdList", poTechLineStsCdList);

            List<String> locTpCdList = Arrays.asList(LOC_TP.TECHNICIAN, LOC_TP.CUSTOMER);
            queryParam.put("locTpCdList", locTpCdList);
            // END 2022/06/22 R.Azucena [QC#60240 ADD]

            // list parameters
            queryParam.put("includeMdseClsTp", incluMdseItemClsTpList);
            queryParam.put("includeMdseSts", inclMdseItemStsList);
            queryParam.put("includeMdseTp", inclMdseItemTpList);
            queryParam.put("poLineExclusionTp", exclPoLineTp);
            queryParam.put("incldPrchReqSts", inclPrchReqSts);
            queryParam.put("incldWrkOrdSts", inclWrkOrdSts);

            // START 2021/11/04 A.Marte [QC#58989-2, ADD]
            //set RTL_WH_CD conversion params
            Map<String, Object> rtlWhConversionParams = setRtlWhCdConversionParam();
            queryParam.putAll(rtlWhConversionParams);
            // END 2021/11/04 A.Marte [QC#58989-2, ADD]

            prdStmt = ssmLLClient.createPreparedStatement("getInvty", queryParam, execParam);
            rs = prdStmt.executeQuery();

            // Initialize Transaction ID.
            BigDecimal trxId = trxAccess.getNextTransactionId();
            Integer seqCount = 1;

            // START 2021/11/04 A.Marte [QC#58989-2, DEL]
            // trxAccess.createIntegrationRecordForBatch(interfaceId01, trxId);
            // trxAccess.createIntegrationRecordForBatch(interfaceId02, trxId);
            // END 2021/11/04 A.Marte [QC#58989-2, DEL]

            while (rs.next()) {

                // Insert to NLCI2010_01
                NLCI2010_01TMsg if01TMsg = createNLCI2010_01(seqCount, rs, trxId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    S21InfoLogOutput.println(NLCB201001Constant.NLAM1295E, new String[]{NLCB201001Constant.TBL_NLCI2010_01});
                    throw new S21AbendException(NLCB201001Constant.NLAM1295E, new String[] {NLCB201001Constant.TBL_NLCI2010_01 });
                }

                seqCount++;
                this.successCount++;
            }

            // START 2021/11/04 A.Marte [QC#58989-2, MOD]
            if (this.successCount > 0) {
            
                trxAccess.createIntegrationRecordForBatch(interfaceId01, trxId);
                trxAccess.createIntegrationRecordForBatch(interfaceId02, trxId);
                
                // Insert to NLCI2011_01
                NLCI2011_01TMsg if02TMsg = createNLCI2011_01(rs, trxId, this.successCount);
                EZDTBLAccessor.insert(if02TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if02TMsg.getReturnCode())) {
                    S21InfoLogOutput.println(NLCB201001Constant.NLAM1295E, new String[]{NLCB201001Constant.TBL_NLCI2011_01});
                    throw new S21AbendException(NLCB201001Constant.NLAM1295E, new String[] {NLCB201001Constant.TBL_NLCI2011_01 });
                }

                //Insert to INTERFACE_TRANSACTION_PARAM
                trxParamAccess.createIntegrationRecordForBatch(interfaceId01, trxId, ZYPDateUtil.getCurrentSystemTime(NLCB201001Constant.FMT_YYYYMMDDHHMMSS));
            }
            // END 2021/11/04 A.Marte [QC#58989-2, MOD]

        } catch (SQLException e) {
              EZDDebugOutput.println(NLCB201001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
              termCd = TERM_CD.ABNORMAL_END;
              sqlExceptionHandler(e);
           } finally {
               S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    // START 2021/11/04 A.Marte [QC#58989-2, ADD]
    /**
     * Set RTL_WH_CD Conversion Params
     * @return Map<String, Object>
     */
    private Map<String, Object> setRtlWhCdConversionParam() {

        // Set up parameters for RTL_WH conversion
        String convtFromWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_CONVT_FROM_WH, glblCmpyCd);
        String convtIntoWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_CONVT_INTO_WH, glblCmpyCd);
        // 2024/04/03 QC#63814 Add Start
        String convtFromSWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_CONVT_FROM_SWH, glblCmpyCd);
        String cmpToSWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_CMP_TO_SWH, glblCmpyCd);
        // 2024/04/03 QC#63814 Add End

        Map<String, Object> params = new HashMap<String, Object>();

        // RTL_WH_CD conversion parameters
        if (ZYPCommonFunc.hasValue(convtFromWh)) {
            List<String> convtFromWhList = Arrays.asList(convtFromWh.split(","));
            params.put("convtFlg", ZYPConstant.FLG_ON_Y);
            params.put("convtFromWhList", convtFromWhList);
        } else {
            params.put("convtFlg", ZYPConstant.FLG_OFF_N);
        }
        // 2024/04/03 QC#63814 Add Start
        if (ZYPCommonFunc.hasValue(convtFromSWh)) {
            List<String> convtFromSWhList = Arrays.asList(convtFromSWh.split(","));
            params.put("convtFromSWhList", convtFromSWhList);
        } else {
            params.put("convtFlg", ZYPConstant.FLG_OFF_N);
        }
        // 2024/04/03 QC#63814 Add End

        if (ZYPCommonFunc.hasValue(convtIntoWh)) {
            params.put("convtIntoWh", convtIntoWh);
        } else {
            params.put("convtFlg", ZYPConstant.FLG_OFF_N);
        }

        // 2024/04/03 QC#63814 Add Start
        if (ZYPCommonFunc.hasValue(cmpToSWh)) {
            params.put("cmpToSWh", cmpToSWh);
        } else {
            params.put("convtFlg", ZYPConstant.FLG_OFF_N);
        }
        params.put("trdPtnrWhTgTpCdCSA", NLCB201001Constant.TRD_PTNR_WH_RG_TP_CD_CSA);
        // 2024/04/03 QC#63814 Add End

        //STK_LOC_CD replacement
        String stkLocCd = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_STK_LOC_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(stkLocCd)) {
            List<String> stkLocCdList = Arrays.asList(stkLocCd.split(","));
            params.put("stkLocCdList", stkLocCdList);
        // START 2021/11/04 A.Marte [QC#58989-2, ADD]
        } else {
            params.put("stkLocCdList", null);
        }
        // END 2021/11/04 A.Marte [QC#58989-2, ADD]

        // target WH of INBOUND inventory data acquisition
        String inboundTrgtWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_INBD_TRGT_WH, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(inboundTrgtWh)) {
            List<String> inboundTrgtWhList = Arrays.asList(inboundTrgtWh.split(","));
            // WH List contains MNX
            if (inboundTrgtWhList.contains(NLCB201001Constant.VAL_MNX)) {
                params.put("inbdInclMNX", ZYPConstant.FLG_ON_Y);
            // WH List does not include MNX
            } else {
               params.put("inbdInclMNX", ZYPConstant.FLG_OFF_N);
            }
            params.put("inboundTrgtWhList", inboundTrgtWhList);
        // START 2021/11/04 A.Marte [QC#58989-2, ADD]
        } else {
            params.put("inboundTrgtWhList", null);
        }
        // END 2021/11/04 A.Marte [QC#58989-2, ADD]

        // target WH of INBOUND(IR only) inventory data acquisition
        String inboundIrTrgtWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_INBD_IR_TRGT_WH, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(inboundIrTrgtWh)) {
            List<String> inboundIrTrgtWhList = Arrays.asList(inboundIrTrgtWh.split(","));
            // WH List contains MNX
            if (inboundIrTrgtWhList.contains(NLCB201001Constant.VAL_MNX)) {
                params.put("inbdIrInclMNX", ZYPConstant.FLG_ON_Y);
            // WH List does not include MNX
            } else {
                params.put("inbdIrInclMNX", ZYPConstant.FLG_OFF_N);
            }
            params.put("inboundIrTrgtWhList", inboundIrTrgtWhList);
        // START 2021/11/04 A.Marte [QC#58989-2, ADD]
        } else {
            params.put("inboundIrTrgtWhList", null);
        }
        // END 2021/11/04 A.Marte [QC#58989-2, ADD]

        //Target WH of ONHAND inventory data acquisition
        List<String> onhandTrgtWhList = new ArrayList<String>();
        String onhandTrgtWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_OH_TRGT_WH, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(onhandTrgtWh)) {
            onhandTrgtWhList = Arrays.asList(onhandTrgtWh.split(","));
            if (onhandTrgtWhList.contains(NLCB201001Constant.VAL_MNX)) {
                params.put("ohInclMNX", ZYPConstant.FLG_ON_Y);
            // WH List does not include MNX
            } else {
                params.put("ohInclMNX", ZYPConstant.FLG_OFF_N);
            }

            // START 2022/06/22 R.Azucena [QC#60240 ADD]
            if (onhandTrgtWhList.contains(NLCB201001Constant.VAL_TECH)) {
                params.put("ohInclTECH", ZYPConstant.FLG_ON_Y);
            } else {
                params.put("ohInclTECH", ZYPConstant.FLG_OFF_N);
            }
            // END 2022/06/22 R.Azucena [QC#60240 ADD]

            params.put("onhandTrgtWhList", onhandTrgtWhList);
        // START 2021/11/04 A.Marte [QC#58989-2, ADD]
        } else {
            params.put("onhandTrgtWhList", null);
        }
        // END 2021/11/04 A.Marte [QC#58989-2, ADD]

        //Target WH of ALLOCATED inventory data acquisition
        String allocTrgtWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_ALLOC_TRGT_WH, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(allocTrgtWh)) {
            List<String> allocTrgtWhList = Arrays.asList(allocTrgtWh.split(","));
            if (allocTrgtWhList.contains(NLCB201001Constant.VAL_MNX)) {
                params.put("allocInclMNX", ZYPConstant.FLG_ON_Y);
            // WH List does not include MNX
            } else {
                params.put("allocInclMNX", ZYPConstant.FLG_OFF_N);
            }

            // START 2022/06/22 R.Azucena [QC#60240 ADD]
            if (allocTrgtWhList.contains(NLCB201001Constant.VAL_TECH)) {
                params.put("allocInclTECH", ZYPConstant.FLG_ON_Y);
            } else {
                params.put("allocInclTECH", ZYPConstant.FLG_OFF_N);
            }
            // END 2022/06/22 R.Azucena [QC#60240 ADD]
        // START 2021/11/04 A.Marte [QC#58989-2, MOD]
            params.put("allocTrgtWhList", allocTrgtWhList);
        } else {
            params.put("allocTrgtWhList", null);
        }
        // END 2021/11/04 A.Marte [QC#58989-2, MOD]

        // START 2022/06/22 R.Azucena [QC#60240 ADD]
        // Target WH of INBOUND TR/PO inventory data acquisition
        String inboundTrPoTrgtWh = ZYPCodeDataUtil.getVarCharConstValue(NLCB201001Constant.VAR_CHAR_CONST_NLCB2010_INBD_TRPO_TRGT_WH, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(inboundTrPoTrgtWh)) {
            List<String> inboundTrPoTrgtWhList = Arrays.asList(inboundTrPoTrgtWh.split(","));

            // START 2022/07/06 R.Azucena [QC#60240-2 DEL]
            // if (inboundTrPoTrgtWhList.contains(NLCB201001Constant.VAL_MNX)) {
            //     params.put("inboundTrPoInclMNX", ZYPConstant.FLG_ON_Y);
            // } else {
            //     params.put("inboundTrPoInclMNX", ZYPConstant.FLG_OFF_N);
            // }
            // END 2022/07/06 R.Azucena [QC#60240-2 DEL]

            if (inboundTrPoTrgtWhList.contains(NLCB201001Constant.VAL_TECH)) {
                params.put("inboundTrPoInclTECH", ZYPConstant.FLG_ON_Y);
            } else {
                params.put("inboundTrPoInclTECH", ZYPConstant.FLG_OFF_N);
            }

            params.put("inboundTrPoTrgtWhList", inboundTrPoTrgtWhList);
        } else {
            params.put("inboundTrPoTrgtWhList", null);
        }
        // END 2022/06/22 R.Azucena [QC#60240 ADD]

        return params;
    }
    // END 2021/11/04 A.Marte [QC#58989-2, ADD]

    /**
     * createNLCI2010_01
     * @param Integer seqCount
     * @param ResultSet rs
     * @param BigDecimal trxId
     */
    private NLCI2010_01TMsg createNLCI2010_01(Integer seqCount, ResultSet rs, BigDecimal trxId) throws SQLException {
        NLCI2010_01TMsg tMsg = new NLCI2010_01TMsg();

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
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(NLCB201001Constant.MDSE_CD));

        // WH_RG_CD 
        ZYPEZDItemValueSetter.setValue(tMsg.whRgCd, rs.getString(NLCB201001Constant.WH_RG_CD));

        // WH_SITE_CD
        String whSiteCd = rs.getString(NLCB201001Constant.RTL_WH_CD);
        if (ZYPCommonFunc.hasValue(whSiteCd) && whSiteCd.length() > NLCB201001Constant.VAL_MAX_CD_LEN) {
            whSiteCd =  whSiteCd.substring(0, NLCB201001Constant.VAL_MAX_CD_LEN);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.whSiteCd, whSiteCd);

        // STK_LOC_CD
        ZYPEZDItemValueSetter.setValue(tMsg.stkLocCd, rs.getString(NLCB201001Constant.STK_LOC_CD));

        // STK_COND_CD
        String stkStsNm = rs.getString(NLCB201001Constant.STK_STS_NM);
        // START 2022/06/22 R.Azucena [QC#60240 MOD]
        // if (ZYPCommonFunc.hasValue(stkStsNm) && stkStsNm.length() > NLCB201001Constant.VAL_MAX_CD_LEN) {
        //     stkStsNm =  stkStsNm.substring(0, NLCB201001Constant.VAL_MAX_CD_LEN);
        if (ZYPCommonFunc.hasValue(stkStsNm) && stkStsNm.length() > NLCB201001Constant.STK_STS_NM_MAX_LEN) {
            stkStsNm =  stkStsNm.substring(0, NLCB201001Constant.STK_STS_NM_MAX_LEN);
        // END 2022/06/22 R.Azucena [QC#60240 MOD]
        }
        ZYPEZDItemValueSetter.setValue(tMsg.stkCondCd, stkStsNm);

        // INVTY_STS_CD
        ZYPEZDItemValueSetter.setValue(tMsg.invtyStsCd, rs.getString(NLCB201001Constant.INVTY_STS_CD));

        // INVTY_QTY_TOT_AMT
        ZYPEZDItemValueSetter.setValue(tMsg.invtyQtyTotAmt, rs.getBigDecimal(NLCB201001Constant.VIS_QTY));

        // SER_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(NLCB201001Constant.SER_NUM));

        // RSVD_FIRST_CD
        ZYPEZDItemValueSetter.setValue(tMsg.rsvdFirstCd, rs.getString(NLCB201001Constant.RSVD_FIRST_CD));

        // INVTY_SRC_CD
        ZYPEZDItemValueSetter.setValue(tMsg.invtySrcCd, rs.getString(NLCB201001Constant.INVTY_SRC_CD));

        return tMsg;
    }

    /**
     * createNLCI2011_01
     * @param ResultSet rs
     * @param BigDecimal trxId
     * @param Integer recordCount
     */
    private NLCI2011_01TMsg createNLCI2011_01(ResultSet rs, BigDecimal trxId, Integer recordCount) throws SQLException {
        NLCI2011_01TMsg tMsg = new NLCI2011_01TMsg();

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
        tMsg.invtyTotRecCnt.setValue(recordCount);

        return tMsg;
    }

}
