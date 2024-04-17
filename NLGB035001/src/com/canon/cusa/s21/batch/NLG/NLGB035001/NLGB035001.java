/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB035001;

import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_BOL_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_EDI_GS_CTRL_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_EDI_LN_CTRL_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_EDI_ST_CTRL_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_EDI_TRX_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_INTFC_TRX_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_OTBD_ORD_LINE_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_OTBD_ORD_NUM;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_WH_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_WMS_CARR_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_WMS_INBD_TRX_PK;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_WMS_PROC_QTY;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_WMS_REC_ID;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.COL_WMS_SHIP_DT_TM_TS;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.KEY_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.KEY_WMS_TASK_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.KEY_WMS_TRGT_WH_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.LG_EDI_ST_CTRL_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.LG_WMS_SHIP_DT_TO_FOR_POD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.LG_WMS_SHIP_TM_FROM_FOR_POD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.LG_WMS_SHIP_TM_TO_FOR_POD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.MSG_ID_NLGM0044E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.MSG_ID_NLGM0046E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.MSG_ID_NLGM0048E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.NLGM0045E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.NLGM0047E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.NLGM0049E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.NLGM0060E;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.PRAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.PRAM_NM_PROC_TP;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.PRAM_NM_WH_GRP_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.SQL_STMT_ID_GET_WMS_INBD_TRX_POD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.SQL_STMT_ID_GET_WMS_INBD_TRX_POD_4_MNX;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.ST_NORMAL_END;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.TBL_POD_ADDR_WRK;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.TBL_POD_STS_WRK;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.TBL_WMS_INBD_TRX;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.VAL_CONST_EDI_TM_CD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.VAL_EDI_LN_CTRL_CD_INIT;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.VAL_PROC_TP_POD;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.VAL_ZERO;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.WH_GP_CD_3;
import static com.canon.cusa.s21.batch.NLG.NLGB035001.constant.NLGB035001Constant.ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.POD_ADDR_WRKTMsg;
import business.db.POD_STS_WRKTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.parts.NLZC405001PMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLZ.NLZC405001.NLZC405001;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for Translate POD from WMS for DBS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            N.Akaishi       Create          V1.0
 * 10/11/2016   CITS            T.Hakodate      UPDATE          QC#15066
 * 12/27/2016   CITS            S.Endo          Update          QC#16712
 * 09/25/2017   CITS            T.Wada          Update          QC#21305
 * 10/10/2017   CITS            T.Wada          Update          QC#21305-1
 * 02/05/2021   CITS            K.Ogino         Update          QC#58135
 *</pre>
 */
public class NLGB035001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Warehouse Group Cd */
    private String whGpCd = null;

    /** Process Type */
    private String procTp = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Counter of Records: Successful */
    private int normalRecCount = 0;

    /** Counter of Records: Error */
    private int totalErrCount = 0;

    /** Target Warehouse Code */
    String[] trgtWhCdAry = null;

    // QC#21305 Add
    /** Sales Date */
    private String salesDate = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // QC#58135
    private String[] shpgStsCdList = null;

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arguments
     */
    public static void main(String[] args) {
        new NLGB035001().executeBatch(NLGB035001.class.getSimpleName());
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

        // Getting excute Warehouse Group Code
        whGpCd = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        // Getting Process Type
        procTp = S21BatchUtil.getUserVariable2();
        if (!ZYPCommonFunc.hasValue(procTp)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_PROC_TP });
        } else if (!(VAL_PROC_TP_POD.equals(procTp))) {
            throw new S21AbendException(NLGM0060E, new String[] {procTp, PRAM_NM_PROC_TP });
        }

        // QC#21305 Add
        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        // QC#58135
        String constVal = ZYPCodeDataUtil.getVarCharConstValue("NLGB035001_GP3_SHPG_STS_LIST", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal)) {
            this.shpgStsCdList = constVal.split(",");
        } else {
            this.shpgStsCdList = new String[]{SHPG_STS.SHIPPED};
        }

    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {
        // QC#58135 Mod
        if (!WH_GP_CD_3.equals(whGpCd)) {
            trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);
        }
        if (trgtWhCdAry != null || WH_GP_CD_3.equals(whGpCd)) {
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
        setTermState(termCd, this.normalRecCount, this.totalErrCount, this.normalRecCount + this.totalErrCount);
    }

    /**
     * QC#21305 Add
     * @param wmsInbdTrx
     */
    private boolean callNLZC405001(Map<String, Object> wmsInbdTrx) {
		NLZC405001PMsg nlzc405001PMsg = new NLZC405001PMsg();

		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.glblCmpyCd, this.glblCmpyCd);
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.slsDt, this.salesDate);
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.inbdOtbdCd, "2");
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxHdrNum, (String) wmsInbdTrx.get(COL_OTBD_ORD_NUM));
		if ((String) wmsInbdTrx.get(COL_OTBD_ORD_LINE_NUM) != null) {
			String trxLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) wmsInbdTrx.get(COL_OTBD_ORD_LINE_NUM)), 3, "0");
			ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxLineNum, trxLineNum);
		}
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum, (String) wmsInbdTrx.get(COL_BOL_NUM));
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.DELIVERED);
        if (wmsInbdTrx.get(COL_WMS_SHIP_DT_TM_TS) != null) {
            // EDI_STS_DT
            String wmsTrxDt = wmsInbdTrx.get(COL_WMS_SHIP_DT_TM_TS).toString().substring(0, LG_WMS_SHIP_DT_TO_FOR_POD);
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.xxRqstDt ,wmsTrxDt);
            // EDI_STS_TM
            String wmsTrxDTmTst = wmsInbdTrx.get(COL_WMS_SHIP_DT_TM_TS).toString().substring(LG_WMS_SHIP_TM_FROM_FOR_POD, LG_WMS_SHIP_TM_TO_FOR_POD);
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm ,wmsTrxDTmTst);
        }
        // QC#58135 Add
        if (!WH_GP_CD_3.equals(whGpCd)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP.DBS);
        } else if (WH_GP_CD_3.equals(whGpCd)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP.MNX);
        }

        NLZC405001 nlzc405001 = new NLZC405001();
        nlzc405001.execute(nlzc405001PMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc405001PMsg);

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                	return false;
                }
            }
        }
        return true;
    }

    /**
     * Translate POD process. WMS_INBD_TRX to POD_ADDR_WRK,
     * POD_STS_WRK
     */
    private void doProcess() {

        PreparedStatement prdStmtWmsInbdTrxPod = null;
        ResultSet rsWmsInbdTrxPod = null;

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            // QC#58135 Mod
            if (!WH_GP_CD_3.equals(whGpCd)) {
                // DBS
                queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
                queryParam.put(KEY_PROC_STS_CD, PROC_STS.IN_COMPLETED);
                queryParam.put(KEY_WMS_TASK_CD, WMS_TASK.POD);
                prdStmtWmsInbdTrxPod = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_WMS_INBD_TRX_POD, queryParam, execParam);
                rsWmsInbdTrxPod = prdStmtWmsInbdTrxPod.executeQuery();   

            } else {
                // MNX
                queryParam.put(KEY_PROC_STS_CD, PROC_STS.IN_COMPLETED);
                queryParam.put(KEY_WMS_TASK_CD, WMS_TASK.POD);
                queryParam.put("shpgStsCdList", this.shpgStsCdList);
                prdStmtWmsInbdTrxPod = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_WMS_INBD_TRX_POD_4_MNX, queryParam, execParam);
                rsWmsInbdTrxPod = prdStmtWmsInbdTrxPod.executeQuery();
            }

            // BigDecimal podTrxId =
            // trxAccessor.getNextTransactionId();
            BigDecimal podTrxId = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");

            BigDecimal ediStCtrlCdSeq = BigDecimal.ONE;

            while (rsWmsInbdTrxPod.next()) {

                Map<String, Object> wmsInbdTrx = new HashMap<String, Object>();
                wmsInbdTrx.put(COL_GLBL_CMPY_CD, glblCmpyCd);
                wmsInbdTrx.put(COL_WMS_INBD_TRX_PK, rsWmsInbdTrxPod.getString(COL_WMS_INBD_TRX_PK));
                wmsInbdTrx.put(COL_INTFC_TRX_ID, rsWmsInbdTrxPod.getString(COL_INTFC_TRX_ID));
                wmsInbdTrx.put(COL_WMS_REC_ID, rsWmsInbdTrxPod.getString(COL_WMS_REC_ID));
                wmsInbdTrx.put(COL_WH_CD, rsWmsInbdTrxPod.getString(COL_WH_CD));
                wmsInbdTrx.put(COL_BOL_NUM, rsWmsInbdTrxPod.getString(COL_BOL_NUM));
                wmsInbdTrx.put(COL_WMS_SHIP_DT_TM_TS, rsWmsInbdTrxPod.getString(COL_WMS_SHIP_DT_TM_TS));
                wmsInbdTrx.put(COL_WMS_PROC_QTY, rsWmsInbdTrxPod.getString(COL_WMS_PROC_QTY));
                wmsInbdTrx.put(COL_WMS_CARR_CD, rsWmsInbdTrxPod.getString(COL_WMS_CARR_CD));
                wmsInbdTrx.put(COL_BOL_NUM, rsWmsInbdTrxPod.getString(COL_BOL_NUM));
                wmsInbdTrx.put(COL_OTBD_ORD_NUM, rsWmsInbdTrxPod.getString(COL_OTBD_ORD_NUM));
                wmsInbdTrx.put(COL_OTBD_ORD_LINE_NUM, rsWmsInbdTrxPod.getString(COL_OTBD_ORD_LINE_NUM));

                // QC#20762 Call NLZC4050 API
                if (!callNLZC405001(wmsInbdTrx)) {

	                String ediTrxIdWk = podTrxId.toPlainString();
	                if (ediTrxIdWk.length() > 10) {
	                    ediTrxIdWk = ediTrxIdWk.substring(ediTrxIdWk.length() - 10);
	                }
	
	                wmsInbdTrx.put(COL_EDI_GS_CTRL_CD, ediTrxIdWk);
	
	                // EDI_ST_CTRL_CD
	                String ediStCtrlCd = podTrxId.toPlainString() + ZYPCommonFunc.leftPad(ediStCtrlCdSeq.toString(), LG_EDI_ST_CTRL_CD, VAL_ZERO);
	
	                if (ediStCtrlCd.length() > 10) {
	                    ediStCtrlCd = ediStCtrlCd.substring(ediStCtrlCd.length() - 10);
	                }
	
	                wmsInbdTrx.put(COL_EDI_ST_CTRL_CD, ediStCtrlCd);
	
	                // insert POD_ADDR_WRK
	                createPodAddrWrk(wmsInbdTrx);
	
	                // EDI_LN_CTRL_CD numbering unit of EDI_ST_CTRL_CD,
	                // BOL_NUM
	                wmsInbdTrx.put(COL_EDI_LN_CTRL_CD, VAL_EDI_LN_CTRL_CD_INIT);
	
	                // insert POD_STS_WRK
	                createPodStsWrk(wmsInbdTrx);
	
	                // increment Seq for EDI_ST_CTRL_CODE
	                ediStCtrlCdSeq = ediStCtrlCdSeq.add(BigDecimal.ONE);
        		}

                // update WMS_INBD_TRX
                WMS_INBD_TRXTMsg findParam = new WMS_INBD_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(findParam.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(findParam.wmsInbdTrxPk, rsWmsInbdTrxPod.getBigDecimal(COL_WMS_INBD_TRX_PK));
                WMS_INBD_TRXTMsg target = findWmsInbdTrx(findParam);
                // PROC_STS_CD
                ZYPEZDItemValueSetter.setValue(target.procStsCd, PROC_STS.COMPLEATED);
                updateWmsInbdTrx(target);

                normalRecCount++;

                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmtWmsInbdTrxPod, rsWmsInbdTrxPod);
        }

    }

    /**
     * createPodAddrWrk
     * @param pWmsInbdTrx
     * @return return code
     */
    private int createPodAddrWrk(Map<String, Object> pWmsInbdTrx) {
        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, (String) pWmsInbdTrx.get(COL_INTFC_TRX_ID));

        // EDI_SQ_ID
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toString());

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, PROC_STS.IN_COMPLETED);

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, (String) pWmsInbdTrx.get(COL_WMS_CARR_CD));

        // EDI_GS_CTRL_CD (For each batch processing of once, Sql ID
        // numbering)
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, (String) pWmsInbdTrx.get(COL_EDI_GS_CTRL_CD));

        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, (String) pWmsInbdTrx.get(COL_EDI_ST_CTRL_CD));

        // EDI_SHPPR_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, (String) pWmsInbdTrx.get(COL_WMS_CARR_CD));

        // PROC_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, POD_SRC_TP.DBS);

        // insert POD_ADDR_WRK
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_ADDR_WRK, TBL_WMS_INBD_TRX, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, pWmsInbdTrx.get(COL_TRANSACTION_ID)) });
        }
        return ST_NORMAL_END;
    }

    /**
     * createPodStsWrk
     * @param pWmsInbdTrx
     * @return return code
     */
    private int createPodStsWrk(Map<String, Object> pWmsInbdTrx) {
        POD_STS_WRKTMsg podStsWrk = new POD_STS_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTrxId, pWmsInbdTrx.get(COL_INTFC_TRX_ID).toString());

        // EDI_SQ_ID
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_STS_SQ);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediSqId, ediSqId.toString());

        // ERR_MSG_CD

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.procStsCd, PROC_STS.IN_COMPLETED);

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, (String) pWmsInbdTrx.get(COL_WMS_CARR_CD));

        // EDI_GS_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, (String) pWmsInbdTrx.get(COL_EDI_GS_CTRL_CD));

        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, (String) pWmsInbdTrx.get(COL_EDI_ST_CTRL_CD));

        // EDI_LN_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, (String) pWmsInbdTrx.get(COL_EDI_LN_CTRL_CD));

        // EDI_PRO_NUM(BOL_NUM)
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, pWmsInbdTrx.get(COL_BOL_NUM).toString());

        // EDI_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION);

        if (pWmsInbdTrx.get(COL_WMS_SHIP_DT_TM_TS) != null) {
            // EDI_STS_DT
            String wmsTrxDt = pWmsInbdTrx.get(COL_WMS_SHIP_DT_TM_TS).toString().substring(0, LG_WMS_SHIP_DT_TO_FOR_POD);
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, wmsTrxDt);
            // EDI_STS_TM
            String wmsTrxDTmTst = pWmsInbdTrx.get(COL_WMS_SHIP_DT_TM_TS).toString().substring(LG_WMS_SHIP_TM_FROM_FOR_POD, LG_WMS_SHIP_TM_TO_FOR_POD);
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, wmsTrxDTmTst);
        }

        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, VAL_CONST_EDI_TM_CD);

        // EDI_LOAD_QTY
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLoadQty, new BigDecimal(pWmsInbdTrx.get(COL_WMS_PROC_QTY).toString()));

        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_POD_STS_WRK, TBL_WMS_INBD_TRX, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID) //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, pWmsInbdTrx.get(COL_INTFC_TRX_ID)) });
        }
        return ST_NORMAL_END;
    }

    /**
     * <pre>
     * find WMS_INBD_TRX for Update
     * @param keyParam WMS_INBD_TRXTMsg Search Key
     * @return WMS_INBD_TRXTMsg search result
     * </pre>
     **/
    private WMS_INBD_TRXTMsg findWmsInbdTrx(WMS_INBD_TRXTMsg keyParam) {
        WMS_INBD_TRXTMsg target = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdateWait(keyParam);
        if (target == null) {
            throw new S21AbendException(MSG_ID_NLGM0044E, new String[] {TBL_WMS_INBD_TRX, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_TRX_PK),
                    NLXCMsgHelper.toListedString(keyParam.glblCmpyCd.getValue(), keyParam.wmsInbdTrxPk.getValue().toPlainString()) });
        } else {
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(target.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLGM0048E, new String[] {TBL_WMS_INBD_TRX, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_TRX_PK),
                        NLXCMsgHelper.toListedString(keyParam.glblCmpyCd.getValue(), keyParam.wmsInbdTrxPk.getValue().toPlainString()) });
            }
        }
        return target;
    }

    /**
     * <pre>
     * update  WMS_INBD_TRX
     * @param updateParam WMS_INBD_TRXTMsg Search Key
     * </pre>
     **/
    private void updateWmsInbdTrx(WMS_INBD_TRXTMsg updateParam) {
        EZDTBLAccessor.update(updateParam);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateParam.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NLGM0046E, new String[] {TBL_WMS_INBD_TRX, COL_WMS_INBD_TRX_PK, updateParam.wmsInbdTrxPk.getValue().toPlainString() });
        }
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
