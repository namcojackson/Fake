/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB030001;

import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_BOL_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INBD_ORD_LINE_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INBD_ORD_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INBD_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_ERR_MSG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_RCV_TS;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_TP_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_TRX_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INTFC_TRX_SQ_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_INVTY_OWNR_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_OTBD_ORD_LINE_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_OTBD_ORD_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_RCV_SER_TAKE_FLG;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_SER_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_UNIT_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_USR_ID_02;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_USR_ID_03;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WH_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_ADJ_PCT;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_BATCH_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_CARR_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_FROM_PKG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_FROM_STK_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_FRT_CHRG_AMT;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_MDSE_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_ORG_HOST_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_PKG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_PROC_QTY;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_RCPT_DT_TM_TS;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_RCPT_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_REC_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_RSN_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_SHIP_DT_TM_TS;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_STK_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_TASK_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_TO_PKG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_TO_STK_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_TRX_DT_TM_TS;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_UOM_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WMS_UPD_HIST_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_WRK_TRX_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.COL_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.CON_WRK_TRX_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_INBD_ORD_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_OTBD_ORD_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_WMS_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_WMS_TASK_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_WMS_TRGT_WH_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.KEY_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.LEN_PD_TRX_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.LG_CUT_WMS_PKG_CD_FROM;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.LG_CUT_WMS_PKG_CD_TO;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.LG_WMS_PKG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.NLGM0041E;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.NLGM0045E;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.NLGM0047E;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.NLGM0049E;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.PRAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.PRAM_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.PRAM_NM_WH_GRP_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SQL_STMT_ID_GET_ADJ_IF_DATA;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SQL_STMT_ID_GET_INVTY_IF_DATA;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SQL_STMT_ID_GET_POD_IF_DATA;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SQL_STMT_ID_GET_PO_IF_DATA;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SQL_STMT_ID_GET_WMS_TASK_INFO;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SQL_STMT_ID_GET_WMS_PKG_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.TBL_NLBI0250_01;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.TBL_NLCI0250_01;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.TBL_NLGI0200_01;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.TBL_NLGI0200_02;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.TBL_NLGI0200_03;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.TBL_WMS_INBD_TRX;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.VAL_CAMMA;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.VAL_EMPTY;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.VAL_ZERO;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.WMS_STS_CD_A;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.WMS_STS_CD_M;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.WMS_STS_CD_T;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.ZERO;
import static com.canon.cusa.s21.batch.NLG.NLGB030001.constant.NLGB030001Constant.SIX;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.WMS_INBD_TRXTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for WMS(DBS) to Middleware Transaction.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/25/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 04/05/2016   CITS            N.Akaishi       Update          V1.0
 * 12/28/2016   CITS            T.Tokutomi      Update          QC#16682
 * 01/20/2017   CITS            T.Tokutomi      Update          QC#17204
 * 02/01/2017   CITS            K.Ogino         Update          QC#17352
 * 03/03/2017   CITS            Y.Fujii         Update          QC#17653-2
 * 09/07/2017   CITS            T.Wada          Update          QC#19930
 * 10/25/2017   CITS            T.Wada          Update          QC#21989
 * 07/23/2018   CITS            Y.Iwasaki       Update          QC#26674
 * 01/22/2019   CITS            K.Ogino         Update          QC#29924
 * 01/25/2019   CITS            T.Tokutomi      Update          QC#29979
 * 09/06/2019   CITS            R.Kurahashi     Update          QC#53048
 * 02/17/2020   CITS            T.Wada          Update          QC#55454
 *</pre>
 */
public class NLGB030001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Warehouse Group Cd */
    private String whGpCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

// 2016/04/06 N.Akaishi [V1.0 Del]
//    /** Length to cut the warehouse code for Tecsys */
//    private int cutLg = 0;

    /** Batch Id */
    private String wmsBatId = VAL_EMPTY;

    /** Target Warehouse Code */
    String[] trgtWhCdAry = null;

    // 2016/04/06 N.Akaishi [V1.0 Add] Start
    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** OSHP Create List */
    private ArrayList<String> oshpCrtList = new ArrayList<String>();

    /** Put Away Skip Sce Order Type List */
    private final List<String> putAwaySkipSceTpList = new ArrayList<String>();


    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {

        new NLGB030001().executeBatch(NLGB030001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_INTERFACE_ID });
        }

        // Getting excute Warehouse Group Code
        whGpCd = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

// 2016/04/06 N.Akaishi [V1.0 Del]
//        // Getting Length to cut the warehouse code for Tecsys
//        cutLg = Integer.parseInt(ZYPCodeDataUtil.getVarCharConstValue(CD_KEY_LG_CUT_WH_CD_FOR_TECSYS, glblCmpyCd));

        // Getting Bat Id
        wmsBatId = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);

        // QC#21989
        String putAwaySkipSceTpConst = ZYPCodeDataUtil.getVarCharConstValue("NLGB0110_PUT_AWAY_SKIP_LIST", getGlobalCompanyCode());

        if (putAwaySkipSceTpConst != null) {

            String[] putAwaySkipSceTp = putAwaySkipSceTpConst.split(",");

            for (int i = 0; i < putAwaySkipSceTp.length; i++) {

                putAwaySkipSceTpList.add(putAwaySkipSceTp[i]);
            }

        }

    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);

        if (trgtWhCdAry != null) {
            doProcess();
        } else {
            outputErr(NLGM0047E, new String[] {whGpCd });
        }

        if (errMsgList.size() > ZERO) {
            NLXMailSend mail = new NLXMailSend(glblCmpyCd);
            mail.send(BUSINESS_ID, errMsgList);
            commit();
        }

    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {

        // Setting Process Termination Code
        setTermState(termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * WMS Upload Data Creation Process
     */
    private void doProcess() {

        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);

        // 2016/04/06 N.Akaishi [V1.0 Add] Start
        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // 2016/04/06 N.Akaishi [V1.0 Mod] Start
        for (BigDecimal tranId : tranIds) {

            // insert data into WMS_INBD_TRX(PO) NLGI0200_01
            int cntWmsInbdTrxPo = createWmsInbdTrxPo(execParam, tranId);

            // insert data into WMS_INBD_TRX(ADJ) NLGI0200_02
            int cntWmsInbdTrxAdj = createWmsInbdTrxAdj(execParam, tranId);

            // insert data into WMS_INBD_TRX(SO) NLGI0200_03
            int cntWmsInbdTrxSo = createWmsInbdTrxSo(execParam, tranId);

            // insert data into WMS_INBD_TRX(POD) NLBI0250_01
            int cntWmsInbdTrxPod = createWmsInbdTrxPod(execParam, tranId);

            // insert data into WMS_INBD_TRX(INV) NLCI0250_01
            int cntWmsInbdTrxInvty = createWmsInbdTrxInvty(execParam, tranId);

            trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();

        }
    }

    /**
     * create WMS_INBD_TRX (PO) from NLGI0200_01
     * @param tranId BigDecimal
     * @return int NLGI0200_01's record count 
     */
    private int createWmsInbdTrxPo(S21SsmExecutionParameter execParam, BigDecimal tranId) {

        PreparedStatement prdStmtPoIf = null;
        ResultSet rsPoIf = null;

        int retCnt = ZERO;
        try {

            // get wmsTaskInfo
            Map<String, Object> wmsTaskInfo = getWmsTaskInfo(WMS_TASK.RCVD);
            String intfcTpCd = VAL_EMPTY;
            if (wmsTaskInfo != null) {
                intfcTpCd = (String) wmsTaskInfo.get(COL_INTFC_TP_ID);
            }

            // Search for data to be processed from NLGI0200_01
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            prdStmtPoIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_PO_IF_DATA, queryParam, execParam);
            rsPoIf = prdStmtPoIf.executeQuery();

            S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
            while (rsPoIf.next()) {

                // Get Current UnitId
                String curUnitId = rsPoIf.getString(COL_INTFC_TRX_SQ_NUM);

                Map<String, Object> wmsPoData = new HashMap<String, Object>();
                wmsPoData.put(COL_GLBL_CMPY_CD, glblCmpyCd);
                wmsPoData.put(COL_WMS_REC_ID, rsPoIf.getString(COL_WMS_REC_ID));
                wmsPoData.put(COL_WH_CD, rsPoIf.getString(COL_WH_CD));
                wmsPoData.put(COL_WMS_TASK_CD, WMS_TASK.RCVD);

                wmsPoData.put(COL_INTFC_ID, rsPoIf.getString(COL_INTFC_ID));
                wmsPoData.put(COL_INTFC_RCV_TS, rsPoIf.getString(COL_INTFC_RCV_TS));
                wmsPoData.put(COL_INTFC_TRX_ID, ZYPCommonFunc.leftPad(rsPoIf.getString(COL_INTFC_TRX_ID), LEN_PD_TRX_ID, VAL_ZERO));
                wmsPoData.put(COL_INTFC_TRX_SQ_NUM, ZYPCommonFunc.leftPad(rsPoIf.getString(COL_INTFC_TRX_SQ_NUM), LEN_PD_TRX_ID, VAL_ZERO));
                wmsPoData.put(COL_WRK_TRX_ID, CON_WRK_TRX_ID);
                wmsPoData.put(COL_INTFC_PROC_STS_CD, PROC_STS.COMPLEATED);

                wmsPoData.put(COL_WMS_MDSE_CD, rsPoIf.getString(COL_WMS_MDSE_CD));
                wmsPoData.put(COL_WMS_PKG_CD, rsPoIf.getString(COL_WMS_PKG_CD));
                wmsPoData.put(COL_WMS_STK_STS_CD, rsPoIf.getString(COL_WMS_STK_STS_CD));
                wmsPoData.put(COL_WMS_PROC_QTY, rsPoIf.getBigDecimal(COL_WMS_PROC_QTY));
                wmsPoData.put(COL_USR_ID_03, rsPoIf.getString(COL_USR_ID_03));
                wmsPoData.put(COL_WMS_RCPT_DT_TM_TS, rsPoIf.getString(COL_WMS_RCPT_DT_TM_TS));
                wmsPoData.put(COL_INBD_ORD_NUM, rsPoIf.getString(COL_INBD_ORD_NUM));

                /* 01/20/2017 CITS T.Tokutomi QC#17204 START */
                wmsPoData.put(COL_INBD_ORD_TP_CD, getInbdOrdTpCd(rsPoIf.getString(COL_INBD_ORD_NUM)));
                /* 01/20/2017 CITS T.Tokutomi QC#17204 END */

                wmsPoData.put(COL_INBD_ORD_LINE_NUM, rsPoIf.getBigDecimal(COL_INBD_ORD_LINE_NUM));
                wmsPoData.put(COL_SER_NUM, rsPoIf.getString(COL_SER_NUM));
                wmsPoData.put(COL_WMS_RCPT_ID, rsPoIf.getString(COL_WMS_RCPT_ID));

                wmsPoData.put(COL_WMS_BATCH_ID, wmsBatId);
                wmsPoData.put(COL_INTFC_TP_ID, intfcTpCd);
                wmsPoData.put(COL_WMS_ORG_HOST_ID, rsPoIf.getString(COL_WMS_ORG_HOST_ID));
                wmsPoData.put(COL_WMS_UPD_HIST_NUM, BigDecimal.ZERO);

                // 06/28/2016 CITS N.Akaishi Mod QC9832
                wmsPoData.put(COL_INVTY_OWNR_CD, rsPoIf.getString(COL_INVTY_OWNR_CD));

                // QC#53048
                // Validation WMS_PKG_CD check
                boolean validatePkgCdFlg = validateWmsPkgCd(wmsPoData, tranId, curUnitId, execParam);
                if (validatePkgCdFlg) {
                    // Validation check
                    boolean validateFlg = validatePoIfData(wmsPoData, tranId, curUnitId);
                    if (!validateFlg) {
                        this.errorCount++;
                        wmsPoData.put(COL_INTFC_PROC_STS_CD, PROC_STS.ERROR);
                        wmsPoData.put(COL_INTFC_ERR_MSG_CD, NLGM0041E);
                    }
    
                    // Build Insert Data
                    WMS_INBD_TRXTMsg wmsInbdTrxTMsg = cnvPoIfData(wmsPoData);
    
                    // insert data into WMS_INBD_TRX(PO) NLGI0200_01
                    BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);
                    S21FastTBLAccessor.insert(wmsInbdTrxTMsg);
    
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                        throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_01
                                , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                                , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                    }
    
                    if (validateFlg) {
                        this.successCount++;
                    }
    
                    retCnt++;
                    ///////////////////////////////////////////////////////
                    //  QC#21989 PCLS Create
                    //  All goods receipt ?
                    ///////////////////////////////////////////////////////
                    if (!isAllRecept(wmsInbdTrxTMsg)) {
                    	continue;
                    }
                    // Create PCLS
                    createPcls(wmsInbdTrxTMsg, wmsPoData, trxAccessor.getNextTransactionId());
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtPoIf, rsPoIf);
        }

        return retCnt;
    }

    /**
     * createPcls
     * @param wmsInbdTrxTMsg
     * @param wmsRcvdData
     * @param intfcTrxId BigDecimal
     */
    private void createPcls(WMS_INBD_TRXTMsg wmsInbdTrxTMsg, Map<String, Object> wmsRcvdData, BigDecimal intfcTrxId) {
        // Do Something
        WMS_INBD_TRXTMsg wmsInbdTrx4PclsTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.glblCmpyCd, wmsInbdTrxTMsg.glblCmpyCd.getValue());

        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsRecId, (String) wmsRcvdData.get(COL_WMS_REC_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.whCd, wmsInbdTrxTMsg.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsTaskCd, WMS_TASK.PCLS);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.intfcId, wmsInbdTrxTMsg.intfcId.getValue());
        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.intfcRcvTs, wmsInbdTrxTMsg.intfcRcvTs.getValue());

        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.intfcTrxId, ZYPCommonFunc.leftPad(String.valueOf(intfcTrxId), 30, "0"));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.intfcTrxSqNum, "000000000000000000000000000001");
         
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wrkTrxId, wmsInbdTrxTMsg.wrkTrxId.getValue());
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.procStsCd, PROC_STS.IN_COMPLETED);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsStkStsCd, STK_STS.GOOD);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsOldStkStsCd, STK_STS.GOOD);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsOrgQty, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsOrdQty, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsProcQty, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsRcptDtTmTs, wmsInbdTrxTMsg.wmsRcptDtTmTs.getValue());  
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsShipDtTmTs, wmsInbdTrxTMsg.wmsShipDtTmTs.getValue());
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsTrxDtTmTs, wmsInbdTrxTMsg.wmsTrxDtTmTs.getValue());
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsTotPieceNum, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsTotVol, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsTotWt, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.inbdOrdNum, wmsInbdTrxTMsg.inbdOrdNum.getValue());
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.inbdOrdTpCd, wmsInbdTrxTMsg.inbdOrdTpCd.getValue());
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.inbdOrdLineNum, BigDecimal.ZERO);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.ordLineCpltFlg, ZYPConstant.FLG_ON_Y);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.intfcProcStsCd, PROC_STS.COMPLEATED);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

         BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
         ZYPEZDItemValueSetter.setValue(wmsInbdTrx4PclsTMsg.wmsInbdTrxPk, inbdTrxSq);
         S21FastTBLAccessor.insert(wmsInbdTrx4PclsTMsg);
         if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrx4PclsTMsg.getReturnCode())) {
             throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_01
                     , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                     , NLXCMsgHelper.toListedString(this.interfaceId, "", "000000000000000000000000000001") });
         }

    }

    /**
     * isAllRecept
     * @param wmsInbdTrxTMsg
     * @return
     */
    private boolean isAllRecept(WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {
        if (wmsInbdTrxTMsg.inbdOrdNum == null) {
            return false;
        }
        // get 'RWS vs WMS_INBD_TRX(RCVD)' Data
        String inbdOrdNum = wmsInbdTrxTMsg.inbdOrdNum.getValue();
        List<Map<String, Object>> rwsVsMwList = getRwsVsMwRcvdData(inbdOrdNum);
        if (rwsVsMwList.isEmpty()) {
            return false;
        }

        for (Map<String, Object> rwsVsMw : rwsVsMwList) {
            // SCE_ORD_TP Check
            String sceOrdTpCd = (String) rwsVsMw.get("SCE_ORD_TP_CD");
            if (!putAwaySkipSceTpList.contains(sceOrdTpCd)) {
                return false;
            }
            // DTL Received Check
            String rwsRefNum = (String) rwsVsMw.get("RWS_REF_NUM");
            String mwInbdOrdNum = (String) rwsVsMw.get("INBD_ORD_NUM");
            if (ZYPCommonFunc.hasValue(rwsRefNum) && !ZYPCommonFunc.hasValue(mwInbdOrdNum)) {
                // Not Received Yet
                return false;
            }
            // QTY Check
            BigDecimal rws_qty = (BigDecimal) rwsVsMw.get("RWS_QTY");
            BigDecimal mw_qty = (BigDecimal) rwsVsMw.get("WMS_QTY");
            if (rws_qty != null && mw_qty != null) {
                if (rws_qty.compareTo(mw_qty) != 0) {
                    return false;
                }
            } else {
                return false;
            }

        }
        // All goods Received
        return true;

    }

	private List<Map<String, Object>> getRwsVsMwRcvdData(String inbdOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("inbdOrdNum", inbdOrdNum);
        queryParam.put("wmsTaskCd", WMS_TASK.RCVD);
        queryParam.put("procInComp", PROC_STS.IN_COMPLETED);
        queryParam.put("procComp", PROC_STS.COMPLEATED);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRwsVsMwRcvdData", queryParam);

        return result;
    }
    /**
     * getInbdOrdTpCd
     * @param inbdOrdNum
     * @return inbdOrdTpCd
     */
    private String getInbdOrdTpCd(String inbdOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(KEY_INBD_ORD_NUM, inbdOrdNum);
        String result = (String) this.ssmBatchClient.queryObject("getInbdOrdTpCd", queryParam);

        return result;
    }

    /**
     * Convert The Interface Data (PO)
     * @param wmsPoData Map<String, Object>
     * @return WMS_INBD_TRXTMsg
     */
    private WMS_INBD_TRXTMsg cnvPoIfData(Map<String, Object> wmsPoData) {
        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, (String) wmsPoData.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, (String) wmsPoData.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, (String) wmsPoData.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, (String) wmsPoData.get(COL_WMS_TASK_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcId, (String) wmsPoData.get(COL_INTFC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcRcvTs, (String) wmsPoData.get(COL_INTFC_RCV_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, (String) wmsPoData.get(COL_INTFC_TRX_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, (String) wmsPoData.get(COL_INTFC_TRX_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, (String) wmsPoData.get(COL_WRK_TRX_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, (String) wmsPoData.get(COL_WMS_MDSE_CD));
        // 06/28/2016 CITS N.Akaishi Mod QC9832
        String wmsPkgCd = (String) wmsPoData.get(COL_WMS_PKG_CD);
        if (wmsPkgCd == null) {
            wmsPkgCd = "";
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsPoData.get(COL_INVTY_OWNR_CD) + wmsPkgCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsPoData.get(COL_WMS_STK_STS_CD));
        // 06/28/2016 CITS N.Akaishi Mod QC9832
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, (BigDecimal) wmsPoData.get(COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.usrId_03, (String) wmsPoData.get(COL_USR_ID_03));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRcptDtTmTs, (String) wmsPoData.get(COL_WMS_RCPT_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdNum, (String) wmsPoData.get(COL_INBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdTpCd, (String) wmsPoData.get(COL_INBD_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdLineNum, (BigDecimal) wmsPoData.get(COL_INBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, (String) wmsPoData.get(COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRcptId, (String) wmsPoData.get(COL_WMS_RCPT_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, (String) wmsPoData.get(COL_WMS_BATCH_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTpId, (String) wmsPoData.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, (String) wmsPoData.get(COL_WMS_ORG_HOST_ID));
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, (BigDecimal) wmsPoData.get(COL_WMS_UPD_HIST_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd,  (String) wmsPoData.get(COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, (String) wmsPoData.get(COL_INTFC_ERR_MSG_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

        return wmsInbdTrxTMsg;
    }

    // QC#53048
    /**
     * Validate WMS_PKG_CD
     * @param wmsPoData Map<String, Object>
     * @param tranId transaction id
     * @param unitId unit id
     * @return boolean check result true : ok, false : ng
     */
    private boolean validateWmsPkgCd(Map<String, Object> wmsPoData, BigDecimal tranId, String unitId, S21SsmExecutionParameter execParam) {
        String checkIfTableName = TBL_NLGI0200_01;
        String whCd = (String) wmsPoData.get(COL_WH_CD);

        if (whCd == null) {
            return true;
        }

        PreparedStatement prdStmtRtlSwhCd = null;
        ResultSet rsrRtlSwhCd = null;
        List<String> rtlSwhCdList = new ArrayList<String>();

        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_RTL_WH_CD, whCd + "%");
            prdStmtRtlSwhCd = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_WMS_PKG_CD, queryParam, execParam);
            rsrRtlSwhCd = prdStmtRtlSwhCd.executeQuery();

            while (rsrRtlSwhCd.next()) {
                rtlSwhCdList.add(rsrRtlSwhCd.getString(COL_RTL_SWH_CD));
            }

            if (rtlSwhCdList.contains(wmsPoData.get(COL_WMS_PKG_CD)) && ((String) wmsPoData.get(COL_INVTY_OWNR_CD) + (String) wmsPoData.get(COL_WMS_PKG_CD)).length() == SIX) {
                return true;
            } else {
                outputErr(NLGM0041E, new String[] {COL_WMS_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtRtlSwhCd, rsrRtlSwhCd);
        }
        return false;
    }

    /**
     * Validate The Interface Data (PO)
     * @param wmsPoData Map<String, Object>
     * @param tranId transaction id
     * @param unitId unit id
     * @return boolean check result true : ok, false : ng
     */
    private boolean validatePoIfData(Map<String, Object> wmsPoData, BigDecimal tranId, String unitId) {
        String checkIfTableName = TBL_NLGI0200_01;

        if (wmsPoData.get(COL_WMS_REC_ID) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_REC_ID, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_WH_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WH_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        // 06/28/2016 CITS N.Akaishi Mod QC9832
        if (wmsPoData.get(COL_INVTY_OWNR_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_INVTY_OWNR_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_WMS_MDSE_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_MDSE_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_WMS_PKG_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_WMS_STK_STS_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_WMS_PROC_QTY) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PROC_QTY, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_USR_ID_03) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_UOM_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_WMS_RCPT_DT_TM_TS) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_RCPT_DT_TM_TS, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_INBD_ORD_NUM) == null) {
            outputErr(NLGM0041E, new String[] {COL_INBD_ORD_NUM, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPoData.get(COL_INBD_ORD_LINE_NUM) == null) {
            outputErr(NLGM0041E, new String[] {COL_INBD_ORD_LINE_NUM, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        return true;
    }

    /**
     * create WMS_INBD_TRX (ADJ, ADJ2 Sub Inventory Transfer) from NLGI0200_02
     * @param tranId BigDecimal
     * @return int NLGI0200_02's record count 
     */
    private int createWmsInbdTrxAdj(S21SsmExecutionParameter execParam, BigDecimal tranId) {

        PreparedStatement prdStmtAdjIf = null;
        ResultSet rsAdjIf = null;

        int retCnt = ZERO;
        try {

            // get wmsTaskInfo CADJ
            Map<String, Object> wmsTaskInfoCadj = getWmsTaskInfo(WMS_TASK.CADJ);
            String intfcTpIdCadj = VAL_EMPTY;
            if (wmsTaskInfoCadj != null) {
                intfcTpIdCadj = (String) wmsTaskInfoCadj.get(COL_INTFC_TP_ID);
            }
            // get wmsTaskInfo SERA
            Map<String, Object> wmsTaskInfoSera = getWmsTaskInfo(WMS_TASK.SERA);
            String intfcTpIdSera = VAL_EMPTY;
            if (wmsTaskInfoSera != null) {
                intfcTpIdSera = (String) wmsTaskInfoSera.get(COL_INTFC_TP_ID);
            }
            // get wmsTaskInfo STAT
            Map<String, Object> wmsTaskInfoStat = getWmsTaskInfo(WMS_TASK.STAT);
            String intfcTpIdStat = VAL_EMPTY;
            if (intfcTpIdStat != null) {
                intfcTpIdStat = (String) wmsTaskInfoStat.get(COL_INTFC_TP_ID);
            }

            // Search for data to be processed from NLGI0200_02
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            queryParam.put(KEY_WMS_STS_CD, WMS_STS_CD_A);
            prdStmtAdjIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_ADJ_IF_DATA, queryParam, execParam);
            rsAdjIf = prdStmtAdjIf.executeQuery();

            String transactionId = "";
            Integer transSeqNum = 0;

            while (rsAdjIf.next()) {

                if (!transactionId.equals(rsAdjIf.getString(COL_INTFC_TRX_ID))){
                    transactionId = rsAdjIf.getString(COL_INTFC_TRX_ID);
                    transSeqNum = 0;
                }
                transSeqNum++;

                // Get Current UnitId
                String curUnitId = rsAdjIf.getString(COL_INTFC_TRX_SQ_NUM);

                Map<String, Object> wmsAdjData = new HashMap<String, Object>();
                wmsAdjData.put(COL_GLBL_CMPY_CD, glblCmpyCd);
                wmsAdjData.put(COL_WMS_REC_ID, rsAdjIf.getString(COL_WMS_REC_ID));
                wmsAdjData.put(COL_WH_CD, rsAdjIf.getString(COL_WH_CD));
                // QC#17352
                String rcvSerTakeFlg = rsAdjIf.getString(COL_RCV_SER_TAKE_FLG);
                if (WMS_STS_CD_M.equals(rsAdjIf.getString(COL_WMS_STS_CD))) {
                    // Stock Status Change
                    wmsAdjData.put(COL_WMS_TASK_CD, WMS_TASK.STAT);
                    wmsAdjData.put(COL_INTFC_TP_ID, intfcTpIdStat);
                } else if (ZYPCommonFunc.hasValue(rcvSerTakeFlg) && ZYPConstant.FLG_ON_Y.equals(rcvSerTakeFlg)) {
                    // Adjustment - Serial
                    wmsAdjData.put(COL_WMS_TASK_CD, WMS_TASK.SERA);
                    wmsAdjData.put(COL_INTFC_TP_ID, intfcTpIdSera);
                } else {
                    // Adjustment - Non Serial
                    wmsAdjData.put(COL_WMS_TASK_CD, WMS_TASK.CADJ);
                    wmsAdjData.put(COL_INTFC_TP_ID, intfcTpIdCadj);
                }

                wmsAdjData.put(COL_INTFC_ID, rsAdjIf.getString(COL_INTFC_ID));
                wmsAdjData.put(COL_INTFC_RCV_TS, rsAdjIf.getString(COL_INTFC_RCV_TS));
                wmsAdjData.put(COL_INTFC_TRX_ID, ZYPCommonFunc.leftPad(rsAdjIf.getString(COL_INTFC_TRX_ID), LEN_PD_TRX_ID, VAL_ZERO));
                wmsAdjData.put(COL_INTFC_TRX_SQ_NUM, ZYPCommonFunc.leftPad(transSeqNum.toString(), LEN_PD_TRX_ID, VAL_ZERO));
                wmsAdjData.put(COL_WRK_TRX_ID, CON_WRK_TRX_ID);
                wmsAdjData.put(COL_INTFC_PROC_STS_CD, PROC_STS.COMPLEATED);

                wmsAdjData.put(COL_WMS_STS_CD, rsAdjIf.getString(COL_WMS_STS_CD));
                wmsAdjData.put(COL_WMS_RSN_CD, rsAdjIf.getString(COL_WMS_RSN_CD));
                wmsAdjData.put(COL_WMS_MDSE_CD, rsAdjIf.getString(COL_WMS_MDSE_CD));

                wmsAdjData.put(COL_WMS_FROM_PKG_CD, rsAdjIf.getString(COL_WMS_FROM_PKG_CD));
                wmsAdjData.put(COL_WMS_TO_PKG_CD, rsAdjIf.getString(COL_WMS_TO_PKG_CD));
                wmsAdjData.put(COL_WMS_FROM_STK_STS_CD, rsAdjIf.getString(COL_WMS_FROM_STK_STS_CD));
                wmsAdjData.put(COL_WMS_TO_STK_STS_CD, rsAdjIf.getString(COL_WMS_TO_STK_STS_CD));

                wmsAdjData.put(COL_WMS_PROC_QTY, rsAdjIf.getBigDecimal(COL_WMS_PROC_QTY));
                wmsAdjData.put(COL_USR_ID_02, rsAdjIf.getString(COL_USR_ID_02));
                wmsAdjData.put(COL_USR_ID_03, rsAdjIf.getString(COL_USR_ID_03));
                wmsAdjData.put(COL_SER_NUM, rsAdjIf.getString(COL_SER_NUM));

                wmsAdjData.put(COL_WMS_TRX_DT_TM_TS, rsAdjIf.getString(COL_WMS_TRX_DT_TM_TS));

                wmsAdjData.put(COL_WMS_BATCH_ID, wmsBatId);
                wmsAdjData.put(COL_WMS_ORG_HOST_ID, rsAdjIf.getString(COL_WMS_ORG_HOST_ID));
                wmsAdjData.put(COL_WMS_UPD_HIST_NUM, BigDecimal.ZERO);

                // 06/28/2016 CITS N.Akaishi Mod QC9832
                wmsAdjData.put(COL_INVTY_OWNR_CD, rsAdjIf.getString(COL_INVTY_OWNR_CD));

                // Validation check
                boolean validateFlg = validateAdjIfData(wmsAdjData, tranId, curUnitId);
                if (!validateFlg) {
                    this.errorCount++;
                    wmsAdjData.put(COL_INTFC_PROC_STS_CD, PROC_STS.ERROR);
                    wmsAdjData.put(COL_INTFC_ERR_MSG_CD, NLGM0041E);
                }

                // Build Insert Data
                WMS_INBD_TRXTMsg wmsInbdTrxTMsg = cnvAdjIfData(wmsAdjData);

                // insert data into WMS_INBD_TRX(ADJ) NLGI0200_02
                BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);
                S21FastTBLAccessor.insert(wmsInbdTrxTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                    this.errorCount++;
                    outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_02 //
                            , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID) //
                            , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_02
                            , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID) //
                            , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                }

                if (validateFlg) {
                    this.successCount++;
                }
                retCnt++;

            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtAdjIf, rsAdjIf);
        }

        return retCnt;
    }

    /**
     * Convert The Interface Data (ADJ)
     * @param wmsAdjData Map<String, Object>
     * @return WMS_INBD_TRXTMsg
     */
    private WMS_INBD_TRXTMsg cnvAdjIfData(Map<String, Object> wmsAdjData) {
        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, (String) wmsAdjData.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, (String) wmsAdjData.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, (String) wmsAdjData.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, (String) wmsAdjData.get(COL_WMS_TASK_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcId, (String) wmsAdjData.get(COL_INTFC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcRcvTs, (String) wmsAdjData.get(COL_INTFC_RCV_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, (String) wmsAdjData.get(COL_INTFC_TRX_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, (String) wmsAdjData.get(COL_INTFC_TRX_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, (String) wmsAdjData.get(COL_WRK_TRX_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRsnCd, (String) wmsAdjData.get(COL_WMS_RSN_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStsCd, (String) wmsAdjData.get(COL_WMS_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, (String) wmsAdjData.get(COL_WMS_MDSE_CD));
        
        String wmsStsCd = (String) wmsAdjData.get(COL_WMS_STS_CD);
        if (WMS_STS_CD_A.equals(wmsStsCd)) {
            String wmsFromPkgCd = (String) wmsAdjData.get(COL_WMS_FROM_PKG_CD);
            if (wmsFromPkgCd == null) {
                wmsFromPkgCd = "";
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsAdjData.get(COL_INVTY_OWNR_CD) + wmsFromPkgCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsAdjData.get(COL_WMS_FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, (BigDecimal) wmsAdjData.get(COL_WMS_PROC_QTY));
        } else if(WMS_STS_CD_T.equals(wmsStsCd)){
            String wmsToPkgCd = (String) wmsAdjData.get(COL_WMS_TO_PKG_CD);
            if (wmsToPkgCd == null) {
                wmsToPkgCd = "";
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsAdjData.get(COL_INVTY_OWNR_CD) + wmsToPkgCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsAdjData.get(COL_WMS_TO_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, (BigDecimal) wmsAdjData.get(COL_WMS_PROC_QTY));
        } else { //WMS_STS_CD=M
            String wmsFromPkgCd = (String) wmsAdjData.get(COL_WMS_FROM_PKG_CD);
            if (wmsFromPkgCd == null) {
                wmsFromPkgCd = "";
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsAdjData.get(COL_INVTY_OWNR_CD) + wmsFromPkgCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldStkStsCd, (String) wmsAdjData.get(COL_WMS_FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsAdjData.get(COL_WMS_TO_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsProcQty, (BigDecimal) wmsAdjData.get(COL_WMS_PROC_QTY));
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.usrId_02, (String) wmsAdjData.get(COL_USR_ID_02));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.usrId_03, (String) wmsAdjData.get(COL_USR_ID_03));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, (String) wmsAdjData.get(COL_SER_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, (String) wmsAdjData.get(COL_WMS_TRX_DT_TM_TS));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, (String) wmsAdjData.get(COL_WMS_BATCH_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTpId, (String) wmsAdjData.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, (String) wmsAdjData.get(COL_WMS_ORG_HOST_ID));
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, (BigDecimal) wmsAdjData.get(COL_WMS_UPD_HIST_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd,  (String) wmsAdjData.get(COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, (String) wmsAdjData.get(COL_INTFC_ERR_MSG_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

        return wmsInbdTrxTMsg;
    }

    /**
     * Validate The Interface Data (ADJ)
     * @param wmsAdjData Map<String, Object>
     * @param tranId transaction id
     * @param unitId unit id
     * @return boolean check result true : ok, false : ng
     */
    private boolean validateAdjIfData(Map<String, Object> wmsAdjData, BigDecimal tranId, String unitId) {
        String checkIfTableName = TBL_NLGI0200_02;

        if (wmsAdjData.get(COL_WMS_REC_ID) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_REC_ID, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsAdjData.get(COL_WH_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WH_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        // 06/28/2016 CITS N.Akaishi Mod QC9832
        if (wmsAdjData.get(COL_INVTY_OWNR_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_INVTY_OWNR_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsAdjData.get(COL_WMS_STS_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsAdjData.get(COL_WMS_MDSE_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_MDSE_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        String wmsStsCd = (String) wmsAdjData.get(COL_WMS_STS_CD);
        if (WMS_STS_CD_A.equals(wmsStsCd)) {
            if (wmsAdjData.get(COL_WMS_FROM_PKG_CD) == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_FROM_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }

            if (wmsAdjData.get(COL_WMS_FROM_STK_STS_CD) == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_FROM_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
        } else if(WMS_STS_CD_T.equals(wmsStsCd)) {
            if (wmsAdjData.get(COL_WMS_TO_PKG_CD) == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_TO_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }

            if (wmsAdjData.get(COL_WMS_TO_STK_STS_CD) == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_TO_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
        } else { //WMS_STS_CD=M
            String wmsFromPkgCd = (String) wmsAdjData.get(COL_WMS_FROM_PKG_CD);
            if (wmsFromPkgCd == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_FROM_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
            if (wmsAdjData.get(COL_WMS_FROM_STK_STS_CD) == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_FROM_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
            String wmsToPkgCd = (String) wmsAdjData.get(COL_WMS_TO_PKG_CD);
            if (wmsToPkgCd == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_TO_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
            if (wmsAdjData.get(COL_WMS_TO_STK_STS_CD) == null) {
                outputErr(NLGM0041E, new String[] {COL_WMS_TO_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
            if(!wmsFromPkgCd.equals(wmsToPkgCd)) {
                outputErr(NLGM0041E, new String[] {COL_WMS_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
                return false;
            }
        }

        if (wmsAdjData.get(COL_WMS_PROC_QTY) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PROC_QTY, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsAdjData.get(COL_USR_ID_02) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_ADJ_PCT, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsAdjData.get(COL_USR_ID_03) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_UOM_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsAdjData.get(COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_TRX_DT_TM_TS, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        return true;
    }

    /**
     * create WMS_INBD_TRX (SO) from NLGI0200_03
     * @param tranId BigDecimal
     * @return int NLGI0200_03's record count 
     */
    private int createWmsInbdTrxSo(S21SsmExecutionParameter execParam, BigDecimal tranId) {

        PreparedStatement prdStmtSoIf = null;
        ResultSet rsSoIf = null;

        int retCnt = ZERO;
        try {

            // get wmsTaskInfo
            Map<String, Object> wmsTaskInfo = getWmsTaskInfo(WMS_TASK.SHIP);
            String intfcTpCd = VAL_EMPTY;
            if (wmsTaskInfo != null) {
                intfcTpCd = (String) wmsTaskInfo.get(COL_INTFC_TP_ID);
            }

            // Search for data to be processed from NLGI0200_03(SO)
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            prdStmtSoIf = ssmLLClient.createPreparedStatement("getSoIfData", queryParam, execParam);
            rsSoIf = prdStmtSoIf.executeQuery();

            while (rsSoIf.next()) {

                // Get Current UnitId
                String curUnitId = rsSoIf.getString(COL_INTFC_TRX_SQ_NUM);

                Map<String, Object> wmsSoData = new HashMap<String, Object>();
                wmsSoData.put(COL_GLBL_CMPY_CD, glblCmpyCd);
                wmsSoData.put(COL_WMS_REC_ID, rsSoIf.getString(COL_WMS_REC_ID));
                wmsSoData.put(COL_WH_CD, rsSoIf.getString(COL_WH_CD));
                wmsSoData.put(COL_WMS_TASK_CD, WMS_TASK.SHIP);

                wmsSoData.put(COL_INTFC_ID, rsSoIf.getString(COL_INTFC_ID));
                wmsSoData.put(COL_INTFC_RCV_TS, rsSoIf.getString(COL_INTFC_RCV_TS));
                wmsSoData.put(COL_INTFC_TRX_ID, ZYPCommonFunc.leftPad(rsSoIf.getString(COL_INTFC_TRX_ID), LEN_PD_TRX_ID, VAL_ZERO));
                wmsSoData.put(COL_INTFC_TRX_SQ_NUM, ZYPCommonFunc.leftPad(rsSoIf.getString(COL_INTFC_TRX_SQ_NUM), LEN_PD_TRX_ID, VAL_ZERO));
                wmsSoData.put(COL_WRK_TRX_ID, CON_WRK_TRX_ID);
                wmsSoData.put(COL_INTFC_PROC_STS_CD, PROC_STS.COMPLEATED);

                wmsSoData.put(COL_WMS_MDSE_CD, rsSoIf.getString(COL_WMS_MDSE_CD));
                wmsSoData.put(COL_WMS_PKG_CD, rsSoIf.getString(COL_WMS_PKG_CD));
                wmsSoData.put(COL_WMS_STK_STS_CD, rsSoIf.getString(COL_WMS_STK_STS_CD));
                wmsSoData.put(COL_WMS_PROC_QTY, rsSoIf.getBigDecimal(COL_WMS_PROC_QTY));
                wmsSoData.put(COL_OTBD_ORD_NUM, rsSoIf.getString(COL_OTBD_ORD_NUM));
                wmsSoData.put(COL_OTBD_ORD_LINE_NUM, rsSoIf.getBigDecimal(COL_OTBD_ORD_LINE_NUM));
                wmsSoData.put(COL_WMS_SHIP_DT_TM_TS, rsSoIf.getString(COL_WMS_SHIP_DT_TM_TS));
                // QC#E29979 Update.WMS_INBD_TRX.WMS_CARR_CD = IF.WMS_CARR_CD. Same Tecsys.
                // Delete old QC. QC#16682,19930.
                wmsSoData.put(COL_WMS_CARR_CD, rsSoIf.getString(COL_WMS_CARR_CD));

                wmsSoData.put(COL_BOL_NUM, rsSoIf.getString(COL_BOL_NUM));
                wmsSoData.put(COL_WMS_FRT_CHRG_AMT, rsSoIf.getBigDecimal(COL_WMS_FRT_CHRG_AMT));
                wmsSoData.put(COL_SER_NUM, rsSoIf.getString(COL_SER_NUM));

                wmsSoData.put(COL_WMS_BATCH_ID, wmsBatId);
                wmsSoData.put(COL_INTFC_TP_ID, intfcTpCd);
                wmsSoData.put(COL_WMS_ORG_HOST_ID, rsSoIf.getString(COL_WMS_ORG_HOST_ID));
                wmsSoData.put(COL_WMS_UPD_HIST_NUM, BigDecimal.ZERO);

                // 06/28/2016 CITS N.Akaishi Mod QC9832
                wmsSoData.put(COL_INVTY_OWNR_CD, rsSoIf.getString(COL_INVTY_OWNR_CD));

                // QC12529 Start
                String wmsOrdTpCd = getWmsOrdTpCd(rsSoIf.getString(COL_OTBD_ORD_NUM));
//                if (ZYPCommonFunc.hasValue(wmsOrdTpCd)) {
//                    wmsSoData.put(COL_WMS_ORD_TP_CD, wmsOrdTpCd);
//                }
                wmsSoData.put(COL_WMS_ORD_TP_CD, wmsOrdTpCd);
                // QC12529 End

                // Validation check
                boolean validateFlg = validateSoIfData(wmsSoData, tranId, curUnitId);
                if (!validateFlg) {
                    this.errorCount++;
                    wmsSoData.put(COL_INTFC_PROC_STS_CD, PROC_STS.ERROR);
                    wmsSoData.put(COL_INTFC_ERR_MSG_CD, NLGM0041E);
                }

                // QC12529 Start
                // Create OSHP Record
                boolean flCrtOshp = true;
                String otbdOrdNum = rsSoIf.getString(COL_OTBD_ORD_NUM);
                if (oshpCrtList.size() != 0) {
                    for (int i = 0; i < oshpCrtList.size(); i++) {
                        if (otbdOrdNum.equals(oshpCrtList.get(i))) {
                            flCrtOshp = false;
                        }
                    }
                }
                if (flCrtOshp) {
                    WMS_INBD_TRXTMsg wmsInbdTrxTMsg = cnvOshpIfData(wmsSoData);
                    BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);
                    S21FastTBLAccessor.insert(wmsInbdTrxTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                        throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_03
                                , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID) //
                                , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                    }
                    oshpCrtList.add(otbdOrdNum);
                }
                // QC12529 End

                // Build Insert Data
                WMS_INBD_TRXTMsg wmsInbdTrxTMsg = cnvSoIfData(wmsSoData, WMS_TASK.SHIP);

                // insert data into WMS_INBD_TRX(SO) NLGI0200_03
                BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);

                S21FastTBLAccessor.insert(wmsInbdTrxTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_03
                            , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID) //
                            , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                }

                // QC12529 Start
                if (ZYPCommonFunc.hasValue(rsSoIf.getString(COL_SER_NUM))) {
                    // Create SERL Record
                    wmsInbdTrxTMsg.clear();
                    wmsInbdTrxTMsg = cnvSoIfData(wmsSoData, WMS_TASK.SERL);
                    inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);

                    S21FastTBLAccessor.insert(wmsInbdTrxTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                        throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0200_03
                                , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID) //
                                , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                    }
                }
                // QC12529 End

                if (validateFlg) {
                    this.successCount++;
                }
                retCnt++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtSoIf, rsSoIf);
        }

        return retCnt;
    }

    /**
     * cnvOshpIfData
     * @param wmsSoData
     * @return
     */
    private WMS_INBD_TRXTMsg cnvOshpIfData(Map<String, Object> wmsSoData) {
        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, (String) wmsSoData.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, (String) wmsSoData.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, (String) wmsSoData.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, WMS_TASK.OSHP);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcId, (String) wmsSoData.get(COL_INTFC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcRcvTs, (String) wmsSoData.get(COL_INTFC_RCV_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, (String) wmsSoData.get(COL_INTFC_TRX_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, (String) wmsSoData.get(COL_INTFC_TRX_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, (String) wmsSoData.get(COL_WRK_TRX_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);


        String wmsPkgCd = (String) wmsSoData.get(COL_WMS_PKG_CD);
        if (wmsPkgCd == null) {
            wmsPkgCd = "";
        } else if (wmsPkgCd.getBytes().length > LG_WMS_PKG_CD) {
            wmsPkgCd = ZYPCommonFunc.subByteString(wmsPkgCd, LG_CUT_WMS_PKG_CD_FROM, LG_CUT_WMS_PKG_CD_TO);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsSoData.get(COL_INVTY_OWNR_CD) + wmsPkgCd);


        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdNum, (String) wmsSoData.get(COL_OTBD_ORD_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdTpCd, (String) wmsSoData.get(COL_WMS_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdStsCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, (String) wmsSoData.get(COL_WMS_SHIP_DT_TM_TS));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsFrtChrgAmt, (BigDecimal) wmsSoData.get(COL_WMS_FRT_CHRG_AMT));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsShipDtTmTs, (String) wmsSoData.get(COL_WMS_SHIP_DT_TM_TS));

//        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsCarrCd, (String) wmsSoData.get(COL_WMS_CARR_CD));
//        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.bolNum, (String) wmsSoData.get(COL_BOL_NUM));
//
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, (String) wmsSoData.get(COL_WMS_BATCH_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTpId, (String) wmsSoData.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, (String) wmsSoData.get(COL_WMS_ORG_HOST_ID));
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, (BigDecimal) wmsSoData.get(COL_WMS_UPD_HIST_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd,  (String) wmsSoData.get(COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, (String) wmsSoData.get(COL_INTFC_ERR_MSG_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsShipId, (String) wmsSoData.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOutCntnrNum, (String) wmsSoData.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTotWt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsCarrCd, (String) wmsSoData.get(COL_WMS_CARR_CD));

        return wmsInbdTrxTMsg;
    }
    /**
     * Convert The Interface Data (SO)
     * @param wmsSoData Map<String, Object>
     * @return WMS_INBD_TRXTMsg
     */
    private WMS_INBD_TRXTMsg cnvSoIfData(Map<String, Object> wmsSoData, String wmsTaskCd) {
        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, (String) wmsSoData.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, (String) wmsSoData.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, (String) wmsSoData.get(COL_WH_CD));

        // QC12529
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, wmsTaskCd);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcId, (String) wmsSoData.get(COL_INTFC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcRcvTs, (String) wmsSoData.get(COL_INTFC_RCV_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, (String) wmsSoData.get(COL_INTFC_TRX_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, (String) wmsSoData.get(COL_INTFC_TRX_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, (String) wmsSoData.get(COL_WRK_TRX_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, (String) wmsSoData.get(COL_WMS_MDSE_CD));

        // 05/31/2016 CITS N.Akaishi Mod QC8770 START
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsSoData.get(COL_WH_CD) + (String) wmsSoData.get(COL_WMS_PKG_CD));
        // 06/28/2016 CITS N.Akaishi Mod QC9832 START
//        String whCd = (String) wmsSoData.get(COL_WH_CD);
//        if (whCd.getBytes().length > LG_WMS_WH_CD) {
//            whCd = ZYPCommonFunc.subByteString(whCd, LG_CUT_WMS_WH_CD_FROM, LG_CUT_WMS_WH_CD_TO);
//        }
        String wmsPkgCd = (String) wmsSoData.get(COL_WMS_PKG_CD);
        if (wmsPkgCd == null) {
            wmsPkgCd = "";
        } else if (wmsPkgCd.getBytes().length > LG_WMS_PKG_CD) {
            wmsPkgCd = ZYPCommonFunc.subByteString(wmsPkgCd, LG_CUT_WMS_PKG_CD_FROM, LG_CUT_WMS_PKG_CD_TO);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsSoData.get(COL_INVTY_OWNR_CD) + wmsPkgCd);
        // 06/28/2016 CITS N.Akaishi Mod QC9832 END
        // 05/31/2016 CITS N.Akaishi Mod QC8770 END

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsSoData.get(COL_WMS_STK_STS_CD));
        // 06/28/2016 CITS N.Akaishi Mod QC9832
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, (BigDecimal) wmsSoData.get(COL_WMS_PROC_QTY));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdNum, (String) wmsSoData.get(COL_OTBD_ORD_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdLineNum, (BigDecimal) wmsSoData.get(COL_OTBD_ORD_LINE_NUM));

        // QC12529 Start
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdTpCd, (String) wmsSoData.get(COL_WMS_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdStsCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, (String) wmsSoData.get(COL_WMS_SHIP_DT_TM_TS));
        // QC12529 End

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsFrtChrgAmt, (BigDecimal) wmsSoData.get(COL_WMS_FRT_CHRG_AMT));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, (String) wmsSoData.get(COL_SER_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsShipDtTmTs, (String) wmsSoData.get(COL_WMS_SHIP_DT_TM_TS));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsCarrCd, (String) wmsSoData.get(COL_WMS_CARR_CD));
        /* 12/26/2016 CITS T.Tokuotomi QC#16713 START */
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.proNum, (String) wmsSoData.get(COL_BOL_NUM));
        /* 12/26/2016 CITS T.Tokuotomi QC#16713 END */

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, (String) wmsSoData.get(COL_WMS_BATCH_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTpId, (String) wmsSoData.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, (String) wmsSoData.get(COL_WMS_ORG_HOST_ID));
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, (BigDecimal) wmsSoData.get(COL_WMS_UPD_HIST_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd,  (String) wmsSoData.get(COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, (String) wmsSoData.get(COL_INTFC_ERR_MSG_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsShipId, (String) wmsSoData.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOutCntnrNum, (String) wmsSoData.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTotWt, BigDecimal.ZERO);
        // QC#29924
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.tmsTrkNum, wmsInbdTrxTMsg.proNum);

        return wmsInbdTrxTMsg;
    }

    /**
     * Validate The Interface Data (SO)
     * @param wmsSoData Map<String, Object>
     * @param tranId transaction id
     * @param unitId unit id
     * @return boolean check result true : ok, false : ng
     */
    private boolean validateSoIfData(Map<String, Object> wmsSoData, BigDecimal tranId, String unitId) {
        String checkIfTableName = TBL_NLGI0200_03;

        if (wmsSoData.get(COL_WMS_REC_ID) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_REC_ID, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_WH_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WH_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        // 06/28/2016 CITS N.Akaishi Mod QC9832
        if (wmsSoData.get(COL_INVTY_OWNR_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_INVTY_OWNR_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_WMS_MDSE_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_MDSE_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_WMS_PROC_QTY) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PROC_QTY, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        // QC#55454 Add Start
        } else {
            BigDecimal wmsProcQty = (BigDecimal) wmsSoData.get(COL_WMS_PROC_QTY);
            if (wmsProcQty.intValue() == 0) {
                outputErr("NLGM0092E", new String[] {(String)wmsSoData.get(COL_OTBD_ORD_NUM), (String)wmsSoData.get(COL_WMS_MDSE_CD), (String)wmsSoData.get(COL_WH_CD)});
                return false;
            }
        }
        // QC#55454 Add End

        if (wmsSoData.get(COL_WMS_PKG_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_WMS_STK_STS_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_OTBD_ORD_NUM) == null) {
            outputErr(NLGM0041E, new String[] {COL_OTBD_ORD_NUM, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_OTBD_ORD_LINE_NUM) == null) {
            outputErr(NLGM0041E, new String[] {COL_OTBD_ORD_LINE_NUM, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsSoData.get(COL_WMS_SHIP_DT_TM_TS) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_SHIP_DT_TM_TS, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        return true;
    }

    /**
     * create WMS_INBD_TRX (POD) from NLBI0250_01
     * @param tranId BigDecimal
     * @return int NLBI0250_01's record count 
     */
    private int createWmsInbdTrxPod(S21SsmExecutionParameter execParam, BigDecimal tranId) {

        PreparedStatement prdStmtPodIf = null;
        ResultSet rsPodIf = null;

        int retCnt = ZERO;
        try {

            // get wmsTaskInfo
            Map<String, Object> wmsTaskInfo = getWmsTaskInfo(WMS_TASK.POD);
            String intfcTpCd = VAL_EMPTY;
            if (wmsTaskInfo != null) {
                intfcTpCd = (String) wmsTaskInfo.get(COL_INTFC_TP_ID);
            }

            // Search for data to be processed from NLGI0200_03(SO)
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            prdStmtPodIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_POD_IF_DATA, queryParam, execParam);
            rsPodIf = prdStmtPodIf.executeQuery();

            while (rsPodIf.next()) {

                // Get Current UnitId
                String curUnitId = rsPodIf.getString(COL_INTFC_TRX_SQ_NUM);

                Map<String, Object> wmsPodData = new HashMap<String, Object>();
                wmsPodData.put(COL_GLBL_CMPY_CD, glblCmpyCd);
                wmsPodData.put(COL_WMS_REC_ID, rsPodIf.getString(COL_WMS_REC_ID));
                wmsPodData.put(COL_WH_CD, rsPodIf.getString(COL_WH_CD));
                wmsPodData.put(COL_WMS_TASK_CD, WMS_TASK.POD);

                wmsPodData.put(COL_INTFC_ID, rsPodIf.getString(COL_INTFC_ID));
                wmsPodData.put(COL_INTFC_RCV_TS, rsPodIf.getString(COL_INTFC_RCV_TS));
                wmsPodData.put(COL_INTFC_TRX_ID, ZYPCommonFunc.leftPad(rsPodIf.getString(COL_INTFC_TRX_ID), LEN_PD_TRX_ID, VAL_ZERO));
                wmsPodData.put(COL_INTFC_TRX_SQ_NUM, ZYPCommonFunc.leftPad(rsPodIf.getString(COL_INTFC_TRX_SQ_NUM), LEN_PD_TRX_ID, VAL_ZERO));
                wmsPodData.put(COL_WRK_TRX_ID, CON_WRK_TRX_ID);
                wmsPodData.put(COL_INTFC_PROC_STS_CD, PROC_STS.COMPLEATED);

                wmsPodData.put(COL_WMS_MDSE_CD, rsPodIf.getString(COL_WMS_MDSE_CD));
                wmsPodData.put(COL_WMS_PKG_CD, rsPodIf.getString(COL_WMS_PKG_CD));
                wmsPodData.put(COL_WMS_STK_STS_CD, rsPodIf.getString(COL_WMS_STK_STS_CD));
                wmsPodData.put(COL_WMS_PROC_QTY, rsPodIf.getBigDecimal(COL_WMS_PROC_QTY));
                wmsPodData.put(COL_OTBD_ORD_NUM, rsPodIf.getString(COL_OTBD_ORD_NUM));
                wmsPodData.put(COL_OTBD_ORD_LINE_NUM, rsPodIf.getBigDecimal(COL_OTBD_ORD_LINE_NUM));
                wmsPodData.put(COL_WMS_TRX_DT_TM_TS, rsPodIf.getString(COL_WMS_TRX_DT_TM_TS));
                wmsPodData.put(COL_WMS_CARR_CD, rsPodIf.getString(COL_WMS_CARR_CD));
                wmsPodData.put(COL_BOL_NUM, rsPodIf.getString(COL_BOL_NUM));
                wmsPodData.put(COL_SER_NUM, rsPodIf.getString(COL_SER_NUM));

                wmsPodData.put(COL_WMS_BATCH_ID, wmsBatId);
                wmsPodData.put(COL_INTFC_TP_ID, intfcTpCd);
                wmsPodData.put(COL_WMS_ORG_HOST_ID, rsPodIf.getString(COL_WMS_ORG_HOST_ID));
                wmsPodData.put(COL_WMS_UPD_HIST_NUM, BigDecimal.ZERO);

                // 06/28/2016 CITS N.Akaishi Mod QC9832
                wmsPodData.put(COL_INVTY_OWNR_CD, rsPodIf.getString(COL_INVTY_OWNR_CD));

                // Validation check
                boolean validateFlg = validatePodIfData(wmsPodData, tranId, curUnitId);
                if (!validateFlg) {
                    this.errorCount++;
                    wmsPodData.put(COL_INTFC_PROC_STS_CD, PROC_STS.ERROR);
                    wmsPodData.put(COL_INTFC_ERR_MSG_CD, NLGM0041E);
                }

                // Build Insert Data
                WMS_INBD_TRXTMsg wmsInbdTrxTMsg = cnvPodIfData(wmsPodData);

                // insert data into WMS_INBD_TRX(POD) NLBI0250_01
                BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);
                S21FastTBLAccessor.insert(wmsInbdTrxTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLBI0250_01
                            , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                            , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                }

                if (validateFlg) {
                    this.successCount++;
                }
                retCnt++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtPodIf, rsPodIf);
        }

        return retCnt;
    }

    /**
     * Convert The Interface Data (POD)
     * @param wmsPodData Map<String, Object>
     * @return WMS_INBD_TRXTMsg
     */
    private WMS_INBD_TRXTMsg cnvPodIfData(Map<String, Object> wmsPodData) {
        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, (String) wmsPodData.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, (String) wmsPodData.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, (String) wmsPodData.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, (String) wmsPodData.get(COL_WMS_TASK_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcId, (String) wmsPodData.get(COL_INTFC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcRcvTs, (String) wmsPodData.get(COL_INTFC_RCV_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, (String) wmsPodData.get(COL_INTFC_TRX_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, (String) wmsPodData.get(COL_INTFC_TRX_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, (String) wmsPodData.get(COL_WRK_TRX_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, (String) wmsPodData.get(COL_WMS_MDSE_CD));
        // 06/28/2016 CITS N.Akaishi Mod QC9832
        String wmsPkgCd = (String) wmsPodData.get(COL_WMS_PKG_CD);
        if (wmsPkgCd == null) {
            wmsPkgCd = "";
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsPodData.get(COL_INVTY_OWNR_CD) + wmsPkgCd);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsPodData.get(COL_WMS_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsProcQty, (BigDecimal) wmsPodData.get(COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdNum, (String) wmsPodData.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdLineNum, (BigDecimal) wmsPodData.get(COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, (String) wmsPodData.get(COL_SER_NUM));

        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, (String) wmsPodData.get(COL_WMS_TRX_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsShipDtTmTs, (String) wmsPodData.get(COL_WMS_TRX_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsCarrCd, (String) wmsPodData.get(COL_WMS_CARR_CD));
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.bolNum, (String) wmsPodData.get(COL_BOL_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, (String) wmsPodData.get(COL_WMS_BATCH_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTpId, (String) wmsPodData.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, (String) wmsPodData.get(COL_WMS_ORG_HOST_ID));
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, (BigDecimal) wmsPodData.get(COL_WMS_UPD_HIST_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd,  (String) wmsPodData.get(COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, (String) wmsPodData.get(COL_INTFC_ERR_MSG_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

        return wmsInbdTrxTMsg;
    }

    /**
     * Validate The Interface Data (POD)
     * @param wmsPodData Map<String, Object>
     * @param tranId transaction id
     * @param unitId unit id
     * @return boolean check result true : ok, false : ng
     */
    private boolean validatePodIfData(Map<String, Object> wmsPodData, BigDecimal tranId, String unitId) {
        String checkIfTableName = TBL_NLBI0250_01;

        if (wmsPodData.get(COL_WMS_REC_ID) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_REC_ID, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WH_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WH_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        // 06/28/2016 CITS N.Akaishi Mod QC9832
        if (wmsPodData.get(COL_INVTY_OWNR_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_INVTY_OWNR_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WMS_MDSE_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_MDSE_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WMS_PKG_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WMS_STK_STS_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WMS_PROC_QTY) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PROC_QTY, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_OTBD_ORD_NUM) == null) {
            outputErr(NLGM0041E, new String[] {COL_OTBD_ORD_NUM, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_OTBD_ORD_LINE_NUM) == null) {
            outputErr(NLGM0041E, new String[] {COL_OTBD_ORD_LINE_NUM, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_TRX_DT_TM_TS, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsPodData.get(COL_WMS_CARR_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_CARR_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        return true;
    }

    // createWmsInbdTrxInvty
    /**
     * create WMS_INBD_TRX (INVTY) from NLCI0250_01
     * @param tranId BigDecimal
     * @return int NLCI0250_01's record count 
     */
    private int createWmsInbdTrxInvty(S21SsmExecutionParameter execParam, BigDecimal tranId) {

        PreparedStatement prdStmtInvtyIf = null;
        ResultSet rsInvtyIf = null;

        int retCnt = ZERO;
        try {

            // get wmsTaskInfo
            Map<String, Object> wmsTaskInfo = getWmsTaskInfo(WMS_TASK.RCNR);
            String intfcTpCd = VAL_EMPTY;
            if (wmsTaskInfo != null) {
                intfcTpCd = (String) wmsTaskInfo.get(COL_INTFC_TP_ID);
            }

            // Search for data to be processed from NLGI0200_03(SO)
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            prdStmtInvtyIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_INVTY_IF_DATA, queryParam, execParam);
            rsInvtyIf = prdStmtInvtyIf.executeQuery();

            while (rsInvtyIf.next()) {

                // Get Current UnitId
                String curUnitId = rsInvtyIf.getString(COL_INTFC_TRX_SQ_NUM);

                Map<String, Object> wmsInvtyData = new HashMap<String, Object>();
                wmsInvtyData.put(COL_GLBL_CMPY_CD, glblCmpyCd);
                wmsInvtyData.put(COL_WH_CD, rsInvtyIf.getString(COL_WH_CD));
                wmsInvtyData.put(COL_WMS_TASK_CD, WMS_TASK.RCNR);

                wmsInvtyData.put(COL_INTFC_ID, rsInvtyIf.getString(COL_INTFC_ID));
                wmsInvtyData.put(COL_INTFC_RCV_TS, rsInvtyIf.getString(COL_INTFC_RCV_TS));
                wmsInvtyData.put(COL_INTFC_TRX_ID, ZYPCommonFunc.leftPad(rsInvtyIf.getString(COL_INTFC_TRX_ID), LEN_PD_TRX_ID, VAL_ZERO));
                wmsInvtyData.put(COL_INTFC_TRX_SQ_NUM, ZYPCommonFunc.leftPad(rsInvtyIf.getString(COL_INTFC_TRX_SQ_NUM), LEN_PD_TRX_ID, VAL_ZERO));
                wmsInvtyData.put(COL_WRK_TRX_ID, CON_WRK_TRX_ID);
                wmsInvtyData.put(COL_INTFC_PROC_STS_CD, PROC_STS.COMPLEATED);

                wmsInvtyData.put(COL_WMS_MDSE_CD, rsInvtyIf.getString(COL_WMS_MDSE_CD));
                wmsInvtyData.put(COL_WMS_PKG_CD, rsInvtyIf.getString(COL_WMS_PKG_CD));
                wmsInvtyData.put(COL_WMS_STK_STS_CD, rsInvtyIf.getString(COL_WMS_STK_STS_CD));
                wmsInvtyData.put(COL_WMS_PROC_QTY, rsInvtyIf.getBigDecimal(COL_WMS_PROC_QTY));
                wmsInvtyData.put(COL_USR_ID_03, rsInvtyIf.getString(COL_USR_ID_03));
                wmsInvtyData.put(COL_WMS_TRX_DT_TM_TS, rsInvtyIf.getString(COL_WMS_TRX_DT_TM_TS));
                wmsInvtyData.put(COL_SER_NUM, rsInvtyIf.getString(COL_SER_NUM));

                wmsInvtyData.put(COL_WMS_BATCH_ID, wmsBatId);
                wmsInvtyData.put(COL_INTFC_TP_ID, intfcTpCd);
                wmsInvtyData.put(COL_WMS_ORG_HOST_ID, rsInvtyIf.getString(COL_WMS_ORG_HOST_ID));
                wmsInvtyData.put(COL_WMS_UPD_HIST_NUM, BigDecimal.ZERO);

                // 06/28/2016 CITS N.Akaishi Mod QC9832
                wmsInvtyData.put(COL_INVTY_OWNR_CD, rsInvtyIf.getString(COL_INVTY_OWNR_CD));

                // Validation check
                boolean validateFlg = validateInvtyIfData(wmsInvtyData, tranId, curUnitId);
                if (!validateFlg) {
                    this.errorCount++;
                    wmsInvtyData.put(COL_INTFC_PROC_STS_CD, PROC_STS.ERROR);
                    wmsInvtyData.put(COL_INTFC_ERR_MSG_CD, NLGM0041E);
                }

                // Build Insert Data
                WMS_INBD_TRXTMsg wmsInbdTrxTMsg = cnvInvtyIfData(wmsInvtyData);

                // insert data into WMS_INBD_TRX(POD) NLBI0250_01
                BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);
                /* 12/26/2016 CITS T.Tokuotomi QC#16683 START */
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsRecId, inbdTrxSq.toPlainString());
                /* 12/26/2016 CITS T.Tokuotomi QC#16683 END */
                S21FastTBLAccessor.insert(wmsInbdTrxTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLCI0250_01
                            , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                            , NLXCMsgHelper.toListedString(this.interfaceId, tranId, curUnitId) });
                }

                if (validateFlg) {
                    this.successCount++;
                }
                retCnt++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtInvtyIf, rsInvtyIf);
        }

        return retCnt;
    }

    /**
     * Convert The Interface Data (Inventory)
     * @param wmsInvtyData Map<String, Object>
     * @return WMS_INBD_TRXTMsg
     */
    private WMS_INBD_TRXTMsg cnvInvtyIfData(Map<String, Object> wmsInvtyData) {
        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, (String) wmsInvtyData.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, (String) wmsInvtyData.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTaskCd, (String) wmsInvtyData.get(COL_WMS_TASK_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcId, (String) wmsInvtyData.get(COL_INTFC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcRcvTs, (String) wmsInvtyData.get(COL_INTFC_RCV_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, (String) wmsInvtyData.get(COL_INTFC_TRX_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, (String) wmsInvtyData.get(COL_INTFC_TRX_SQ_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, (String) wmsInvtyData.get(COL_WRK_TRX_ID));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsMdseCd, (String) wmsInvtyData.get(COL_WMS_MDSE_CD));

        // 06/28/2016 CITS N.Akaishi Mod QC9832
        String wmsPkgCd = (String) wmsInvtyData.get(COL_WMS_PKG_CD);
        if (wmsPkgCd == null) {
            wmsPkgCd = "";
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsPkgCd, (String) wmsInvtyData.get(COL_INVTY_OWNR_CD) + wmsPkgCd);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, (String) wmsInvtyData.get(COL_WMS_STK_STS_CD));
        // 06/28/2016 CITS N.Akaishi Mod QC9832
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrdQty, (BigDecimal) wmsInvtyData.get(COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.usrId_03, (String) wmsInvtyData.get(COL_USR_ID_03));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.serNum, (String) wmsInvtyData.get(COL_SER_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsTrxDtTmTs, (String) wmsInvtyData.get(COL_WMS_TRX_DT_TM_TS));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, (String) wmsInvtyData.get(COL_WMS_BATCH_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTpId, (String) wmsInvtyData.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOrgHostId, (String) wmsInvtyData.get(COL_WMS_ORG_HOST_ID));
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, (BigDecimal) wmsInvtyData.get(COL_WMS_UPD_HIST_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd,  (String) wmsInvtyData.get(COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, (String) wmsInvtyData.get(COL_INTFC_ERR_MSG_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);

        return wmsInbdTrxTMsg;
    }

    /**
     * Validate The Interface Data (Inventory)
     * @param wmsInvtyData Map<String, Object>
     * @param tranId transaction id
     * @param unitId unit id
     * @return String Error Code
     */
    private boolean validateInvtyIfData(Map<String, Object> wmsInvtyData, BigDecimal tranId, String unitId) {
        String checkIfTableName = TBL_NLCI0250_01;

        if (wmsInvtyData.get(COL_WH_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WH_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        // 06/28/2016 CITS N.Akaishi Mod QC9832
        if (wmsInvtyData.get(COL_INVTY_OWNR_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_INVTY_OWNR_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsInvtyData.get(COL_WMS_MDSE_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_MDSE_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsInvtyData.get(COL_WMS_PKG_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PKG_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsInvtyData.get(COL_WMS_STK_STS_CD) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_STK_STS_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsInvtyData.get(COL_WMS_PROC_QTY) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_PROC_QTY, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsInvtyData.get(COL_USR_ID_03) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_UOM_CD, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        if (wmsInvtyData.get(COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLGM0041E, new String[] {COL_WMS_TRX_DT_TM_TS, checkIfTableName, interfaceId + VAL_CAMMA + tranId.toPlainString() + VAL_CAMMA + unitId });
            return false;
        }

        return true;
    }

    /**
     * getWmsOrdTpCd
     * @param otbdOrdNum
     * @return
     */
    private String getWmsOrdTpCd(String otbdOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(KEY_OTBD_ORD_NUM, otbdOrdNum);
        String result = (String) this.ssmBatchClient.queryObject("getWmsOrdTpCd", queryParam);

        return result;
    }

    /**
     * getWmsTaskInfo
     * @param wmsTaskCd
     * @return
     */
    private Map<String, Object> getWmsTaskInfo(String wmsTaskCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(KEY_WMS_TASK_CD, wmsTaskCd);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject(SQL_STMT_ID_GET_WMS_TASK_INFO, queryParam);

        return result;
    }

    /**
     * Add Error ID and Message
     * @param msgId Message Id
     * @param msgParam Message Parameter
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);
        S21InfoLogOutput.println(msgId, msgParam);
    }

}
