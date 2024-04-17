package com.canon.cusa.s21.batch.NLB.NLBB081001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.POD_ADDR_WRKTMsg;
import business.db.POD_STS_WRKTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.parts.NLZC405001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC405001.NLZC405001;
import com.canon.cusa.s21.batch.NLB.NLBB081001.constant.NLBB081001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 * Batch Program Class for Shipment Confirmation from STI.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   CITS            T.Wada          Create          
 * 10/11/2016   CITS            T.Hakodate      UPDATE          QC#15066
 * 02/22/2017   CITS            T.Kikuhara      Update          QC#17545
 * 05/19/2017   CITS            T.Kikuhara      Update          RS#2838
 * 07/13/2017   CITS            T.Tokutomi      UPDATE          QC#19815
 * 09/20/2017   CITS            T.Wada          UPDATE          QC#21283
 * 10/30/2017   CITS            T.Wada          UPDATE          QC#21281
 * 11/02/2017   CITS            T.Wada          UPDATE          QC#21303
 * 12/08/2017   CITS            T.Wada          UPDATE          QC#22939
 * 12/08/2017   CITS            T.Wada          UPDATE          QC#21303
 * 12/11/2017   CITS            T.Wada          UPDATE          QC#22934
 * 12/19/2017   CITS            T.Wada          UPDATE          QC#23020
 * 02/18/2019   Hitachi         H.Umeda         UPDATE          QC#30410
 * 03/08/2019   Hitachi         Y.Takeno        UPDATE          QC#30594
 * 05/21/2019   CITS            K.Ogino         Update          QC#50389
 * 06/05/2019   CITS            K.Ogino         Update          QC#50666
 * 06/05/2019   CITS            K.Ogino         Update          QC#52940
 * 08/26/2019   CITS            K.Ogino         Update          QC#52584
 * 09/03/2019   CITS            K.Ogino         Update          QC#52035
 * 12/04/2019   Fujitsu         R.Nakamura      Update          QC#54887
 * 06/26/2020   CITS            K.Ogino         Update          QC#56444
 * 06/19/2021   CITS            M.Furugoori     Update          QC#58935
 * 09/28/2021   CITS            J.Evangelista   Update          QC#58445
 *</pre>
 */
public class NLBB081001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess = null;

    /** WH_OWNR_CD */
    private String whOwnrCd = null;

    /** WMS_PACK_CD_SET_OWNER_CD_FLG */
    private String wmsPackCdFlg = null;

    /** UNIT_ID for SO */
    private int regUnitId_SO = 0;

    /** WMS_BAT_ID */
    private String wmsBatId = "";

    // QC#21281
    /** WMS_ORG_HOST_ID */
    private String wmsOrgHostId = null;

    // QC#21303 Add
    /** Sales Date */
    private String salesDate = null;

    // QC#50389
    private String defWmsCarrCd = "";

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {
        new NLBB081001().executeBatch(NLBB081001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        trxAccess = new S21TransactionTableAccessor();

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLBB081001Constant.NLGM0049E, new String[] {NLBB081001Constant.PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB081001Constant.NLGM0049E, new String[] {NLBB081001Constant.PRAM_NM_INTERFACE_ID });
        }

        // Getting Batch ID
        wmsBatId = ZYPDateUtil.getCurrentSystemTime(NLBB081001Constant.VAL_DATE_TIME_FORMAT_FROM);

        // WH_OWNER_CD
        whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB081001Constant.VAR_CHAR_CONST_NM_TG_ORDER, glblCmpyCd);

        // WMS_PACK_CD_SET_OWNER_CD_FLG
        wmsPackCdFlg = ZYPCodeDataUtil.getVarCharConstValue(NLBB081001Constant.VAR_CHAR_CONST_NM_PACK_FLG, glblCmpyCd);
        if (wmsPackCdFlg == null) {
            wmsPackCdFlg = ZYPConstant.FLG_OFF_N;
        }
        // QC#21281
        wmsOrgHostId = ZYPCodeDataUtil.getVarCharConstValue("WMS_ORG_HOST_ID_3PL", getGlobalCompanyCode());

        // QC#21303 Add
        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // QC#50389
        this.defWmsCarrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB081001Constant.NLBB0810_DEF_WMS_CARR_CD, getGlobalCompanyCode());

    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length == 0) {
            return;
        }

        try {
            // Loop Transaction Id Unit
            for (BigDecimal tranId : tranIds) {
                // QC#52035 Start
                // SO
                String processNm = "Ship Confirm";
                createSoInterface(execParam, tranId, processNm);
                // RWS
                processNm = "RWS Receive";
                createRwsInterface(execParam, tranId, processNm);
                // POD (Proof of Delivery)
                processNm = "Proof of Delivery";
                createPodInterface(execParam, tranId, processNm);
                // QC#21303
                // POD (Proof of Delivery) WRK Data
                processNm = "Proof of Delivery WRK";
                createPodWrkInterface(execParam, tranId, processNm);
                // QC#52035 End

                // Add Start 2019/12/04 QC#54887
                processNm = "Check Detail Data";
                checkDetailData(execParam, tranId, processNm);
                // Add End 2019/12/04 QC#54887

                // Update processed flag
                trxAccess.endIntegrationProcess(this.interfaceId, tranId);

                commit();
            }
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB081001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(this.termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * WMS Upload SO Data Creation Process. Mod QC#52035
     */
    private void createSoInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        BigDecimal regTrxId = trxAccess.getNextTransactionId();

        try {
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
            bindParam.put(NLBB081001Constant.COL_WMS_TASK_CD, NLBB081001Constant.VAL_WMS_TASK_CD_101);
            bindParam.put(NLBB081001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(NLBB081001Constant.COL_INBD_OTBD_CD, NLBB081001Constant.VAL_INBD_OTBD_CD_OTBD);
            bindParam.put(NLBB081001Constant.COL_WMS_RSN_CD, NLBB081001Constant.VAL_WMS_RSN_CD);
            bindParam.put(NLBB081001Constant.MAX_LG, NLBB081001Constant.MAX_LG_30);
            bindParam.put(WMS_TASK.SHIP, WMS_TASK.SHIP);

            stmt = ssmLLClient.createPreparedStatement("getWmsSoHeader", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop n UNIT_ID unit
            while (rs.next()) {
                // QC#52035 Start
                Map<String, Object> processInfoMap = new HashMap<String, Object>();

                try {
                    processInfoMap = new HashMap<String, Object>();
                    // Get Task Name
                    String wmsTaskNm = rs.getString(NLBB081001Constant.COL_WMS_TASK_NM);

                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(NLBB081001Constant.COL_UNIT_ID);

                    // QC#22934
                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(NLBB081001Constant.COL_SEGMENT_ID);

                    // Convert ResultSet to Map
                    Map<String, Object> wmsSoHdr = new HashMap<String, Object>();
                    ResultSetMetaData rsm = rs.getMetaData();
                    for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
                        String colName = rsm.getColumnName(i);
                        Object colVal = rs.getObject(colName);
                        wmsSoHdr.put(colName, colVal);
                    }
                    // QC#50666
                    String otbdOrdNum = (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM);

                    processInfoMap.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(NLBB081001Constant.COL_UNIT_ID, unitId);
                    processInfoMap.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_NUM, otbdOrdNum);
                    processInfoMap.put(NLBB081001Constant.COL_WMS_TASK_NM, wmsTaskNm);
                    processInfoMap.put(NLBB081001Constant.COL_WMS_RSN_CD, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_RSN_CD));

                    // Get SO Detail
                    List<Map<String, Object>> wmsSoDtlList = getWmsSoDetail(execParam, trxId, unitId, segmentId);
                    // QC#56444 Start
                    List<Map<String, Object>> tmpSoDtlList = new ArrayList<Map<String,Object>>();
                    // QC#50666
                    List<BigDecimal> soSlpNumList = new ArrayList<BigDecimal>();
                    if (wmsSoDtlList != null && wmsSoDtlList.size() > 0) {
                        for (Map<String, Object> wmsDtl : wmsSoDtlList) {
                            if (wmsDtl.get(NLBB081001Constant.COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
                                String wmsMdseCd = (String) wmsDtl.get(NLBB081001Constant.COL_WMS_MDSE_CD);
                                String soMdseCd = (String) wmsDtl.get(NLBB081001Constant.COL_MDSE_CD);
                                if (!wmsMdseCd.equals(soMdseCd)) {
                                    continue;
                                }
                            }
                            BigDecimal soSlpNum = (BigDecimal) wmsDtl.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM);
                            soSlpNumList.add(soSlpNum);
                            tmpSoDtlList.add(wmsDtl);
                        }
                    }
                    wmsSoDtlList.clear();
                    wmsSoDtlList.addAll(tmpSoDtlList);
                    // QC#56444 End
                    List<Map<String, Object>> soConfigDtlList = getConfigSoDtl(execParam, soSlpNumList, trxId, unitId, segmentId, otbdOrdNum);
                    if (soConfigDtlList != null && !soConfigDtlList.isEmpty()) {
                        wmsSoDtlList.addAll(soConfigDtlList);
                    }

                    // Validation check
                    String intfcProcStsCd = PROC_STS.COMPLEATED;
                    // START 2021/09/28 J.Evangelista [QC#58445,MOD]
//                    if (!validateIFData(wmsSoHdr, wmsSoDtlList, trxId, unitId, WMS_TASK.SHIP, processNm, processInfoMap)) {
                    if (!validateIFData(wmsSoHdr, wmsSoDtlList, trxId, unitId, WMS_TASK.SHIP, processNm, processInfoMap)
                            || soSlpNumList.isEmpty()) {
                    // END 2021/09/28 J.Evangelista [QC#58445,MOD]
                        intfcProcStsCd = PROC_STS.ERROR;
                    }

                    for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {
                        BigDecimal otbdOrdLineNum = (BigDecimal) wmsSoDtl.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM);

                        processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM, otbdOrdLineNum.toPlainString());

                        createWmsInbdTrxShip(wmsSoHdr, wmsSoDtl, regTrxId, intfcProcStsCd, processNm, processInfoMap);

                        // Get SO Serial
                        List<Map<String, Object>> wmsSoSerList = getWmsSoSerial(execParam, trxId, unitId, otbdOrdLineNum, segmentId);
                        for (Map<String, Object> wmsSoSer : wmsSoSerList) {
                            // QC#56444
                            if (wmsSoSer.get(NLBB081001Constant.COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
                                String wmsMdseCd = (String) wmsSoSer.get(NLBB081001Constant.COL_WMS_MDSE_CD);
                                String soMdseCd = (String) wmsSoSer.get(NLBB081001Constant.COL_MDSE_CD);
                                if (!wmsMdseCd.equals(soMdseCd)) {
                                    continue;
                                }
                            }
                            createWmsInbdTrxSer(wmsSoHdr, wmsSoSer, regTrxId, intfcProcStsCd, processNm, processInfoMap);
                        }

                        if (PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {
                            successCount++;
                        } else {
                            errorCount++;
                        }

                    }
                    // QC#50666
                    // Mod Start 2019/12/04 QC#54887
                    // QC#56444
                    if (soSlpNumList.size() > 0) {
                        List<Map<String, Object>> soConfigSerList = getConfigSoSerial(execParam, soSlpNumList, trxId, unitId, segmentId, otbdOrdNum);
                        if (soConfigSerList != null && !soConfigSerList.isEmpty()) {
                            for (Map<String, Object> wmsSoSer : soConfigSerList) {
                                createWmsInbdTrxSer(wmsSoHdr, wmsSoSer, regTrxId, intfcProcStsCd, processNm, processInfoMap);
                            }
                            if (PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {
                                successCount++;
                            } else {
                                errorCount++;
                            }
                        }
                    // START 2021/09/28 J.Evangelista [QC#58445,MOD]
//                    }
                    } else {
                        processInfoMap.remove(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM);
                        outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLBM1375E), processInfoMap);
                    }
                    // END 2021/09/28 J.Evangelista [QC#58445,MOD]
                    // Mod End 2019/12/04 QC#54887
                } catch (EZDAbendException e) {
                    e.printStackTrace();
                    outputErr(processNm, e.getCause().getLocalizedMessage(), processInfoMap);
                    errorCount++;
                }
                // QC#52035 End
            }

            if (successCount > 0) {
                trxAccess.createIntegrationRecordForBatch(NLBB081001Constant.SO_TARGET_ID, regTrxId);
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB081001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getWmsSoDetail
     * @param execParam
     * @param trxId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsSoDetail(S21SsmExecutionParameter execParam, BigDecimal trxId, BigDecimal unitId, BigDecimal segmentId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
        // QC#22934
        bindParam.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB081001Constant.COL_UNIT_ID, unitId);
        // START 2019/08/20 [QC#52458, ADD]
        bindParam.put(NLBB081001Constant.OTBD_ORD_LINE_NUM_ZERO, NLBB081001Constant.LINE_NUM_ZERO);
        // END 2019/08/20 [QC#52458, ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoDetail", bindParam, execParam);
    }

    /**
     * getWmsSoSerial
     * @param execParam
     * @param trxId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsSoSerial(S21SsmExecutionParameter execParam, BigDecimal trxId, BigDecimal unitId, BigDecimal otbdOrdLineNum, BigDecimal segmentId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
        // QC#22934
        bindParam.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB081001Constant.COL_UNIT_ID, unitId);
        bindParam.put(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM, otbdOrdLineNum);
        bindParam.put(WMS_TASK.SERL, WMS_TASK.SERL);
        // START 2019/08/20 [QC#52458, ADD]
        bindParam.put(NLBB081001Constant.OTBD_ORD_LINE_NUM_ZERO, NLBB081001Constant.LINE_NUM_ZERO);
        // END 2019/08/20 [QC#52458, ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoSerial", bindParam, execParam);
    }

    /**
     * validateIFData. Mod QC#52035
     * @param wmsHdr
     * @param wmsDtlList
     * @param trxId
     * @param unitId
     * @param wmsTask
     * @return
     */
    private boolean validateIFData(Map<String, Object> wmsHdr, List<Map<String, Object>> wmsDtlList, BigDecimal trxId, BigDecimal unitId, String wmsTask, String processNm, Map<String, Object> processInfoMap) {

        if (wmsHdr.get(NLBB081001Constant.COL_WMS_REC_ID) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_REC_ID }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(NLBB081001Constant.COL_WH_CD) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WH_CD }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(NLBB081001Constant.COL_EZINTIME) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_EZINTIME }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_OTBD_ORD_NUM }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(NLBB081001Constant.COL_S80_ORD_TP_CD) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_S80_ORD_TP_CD }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(NLBB081001Constant.COL_INTFC_TP_ID) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_INTFC_TP_ID }), processInfoMap);
            return false;
        }
        // QC#19815 Delete mandatory check WMS_ORG_HOST_ID.

        if (wmsDtlList.size() > 0) {
            for (Map<String, Object> wmsDtl : wmsDtlList) {
                if (wmsDtl.get(NLBB081001Constant.COL_WMS_MDSE_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_MDSE_CD }), processInfoMap);
                    return false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg) && wmsDtl.get(NLBB081001Constant.COL_INVTY_OWNR_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_INVTY_OWNR_CD }), processInfoMap);
                    return false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg) && wmsDtl.get(NLBB081001Constant.COL_RTL_SWH_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_RTL_SWH_CD }), processInfoMap);
                    return false;
                }
                if (ZYPConstant.FLG_OFF_N.equals(wmsPackCdFlg) && wmsDtl.get(NLBB081001Constant.COL_INVTY_LOC_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_INVTY_LOC_CD }), processInfoMap);
                    return false;
                }
                if (WMS_TASK.RCVD.equals(wmsTask) && wmsDtl.get(NLBB081001Constant.COL_INVTY_STK_STS_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_INVTY_STK_STS_CD }), processInfoMap);
                    return false;
                }
                if (WMS_TASK.SHIP.equals(wmsTask) && wmsDtl.get(NLBB081001Constant.COL_FROM_STK_STS_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_FROM_STK_STS_CD }), processInfoMap);
                    return false;
                }
                BigDecimal wmsProcQty = (BigDecimal) wmsDtl.get(NLBB081001Constant.COL_WMS_PROC_QTY);
                if (wmsProcQty == null || BigDecimal.ZERO.compareTo(wmsProcQty) >= 0) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_PROC_QTY }), processInfoMap);
                    return false;
                }
                if (wmsDtl.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_OTBD_ORD_LINE_NUM }), processInfoMap);
                    return false;
                }
                if (WMS_TASK.RCVD.equals(wmsTask) && wmsDtl.get(NLBB081001Constant.COL_WMS_UOM_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_UOM_CD }), processInfoMap);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * createWmsInbdTrxShip. Mod QC#52035
     * @param wmsSoHdr
     * @param wmsSoDtl
     * @param regTrxId
     * @param procStsCd
     * @return
     */
    private void createWmsInbdTrxShip(Map<String, Object> wmsSoHdr, Map<String, Object> wmsSoDtl, BigDecimal regTrxId, String intfcProcStsCd, String processNm, Map<String, Object> processInfoMap) {
        WMS_INBD_TRXTMsg wmsInbdTrxT = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.glblCmpyCd, this.glblCmpyCd);
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsInbdTrxPk, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRecId, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.whCd, (String) wmsSoHdr.get(NLBB081001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTaskCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcId, (String) wmsSoHdr.get(NLBB081001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcRcvTs, (String) wmsSoHdr.get(NLBB081001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxId, (String) wmsSoHdr.get(NLBB081001Constant.COL_TRANSACTION_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxSqNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_UNIT_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wrkTrxId, NLBB081001Constant.VAL_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcProcStsCd, intfcProcStsCd);
        if (PROC_STS.ERROR.equals(intfcProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.errMsgCd, NLBB081001Constant.NLGM0041E);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsMdseCd, (String) wmsSoDtl.get(NLBB081001Constant.COL_WMS_MDSE_CD));
        if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoDtl.get(NLBB081001Constant.COL_INVTY_OWNR_CD) + (String) wmsSoDtl.get(NLBB081001Constant.COL_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoDtl.get(NLBB081001Constant.COL_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsStkStsCd, (String) wmsSoDtl.get(NLBB081001Constant.COL_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdQty, (BigDecimal) wmsSoDtl.get(NLBB081001Constant.COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdTpCd, (String) wmsSoHdr.get(NLBB081001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdLineNum, (BigDecimal) wmsSoDtl.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdStsCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipDtTmTs, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTrxDtTmTs, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTotWt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);

        // QC#23020
        Map<String, Object> tplCarrSvcLvl = getTplCarrSvcLvl((String) wmsSoHdr.get(NLBB081001Constant.COL_CARR_CD), (String) wmsSoHdr.get(NLBB081001Constant.COL_SHPG_SVC_LVL_CD));
        // QC#50389
        if (tplCarrSvcLvl == null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, this.defWmsCarrCd);
        } else if (!ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get("CARR_CD"))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, this.defWmsCarrCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, (String) tplCarrSvcLvl.get("TPL_CARR_SVC_LVL_CD"));
        }
        // String carrCd = (String) wmsSoHdr.get("CARR_CD");
        // String carrSvcLvlCd = getCarrSvcLvl((String)
        // wmsSoHdr.get(NLBB081001Constant.COL_WMS_CARR_CD));
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd,
        // carrSvcLvlCd);

        // QC#22939
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.proNum, (String)
        // wmsSoHdr.get(NLBB081001Constant.COL_BOL_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.proNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_RSN_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipId, (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOutCntnrNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsFrtChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsBatId, wmsBatId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTpId, (String) wmsSoHdr.get(NLBB081001Constant.COL_INTFC_TP_ID));
        // QC#21281
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId,
        // (String)
        // wmsSoHdr.get(NLBB081001Constant.COL_WMS_ORG_HOST_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsUpdHistNum, BigDecimal.ZERO);

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxT.getReturnCode())) {

            S21InfoLogOutput.println(wmsInbdTrxT.getTableName() + " ReturnCode : " + wmsInbdTrxT.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBB081001Constant.TBL_NLBI1120_03,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_INTERFACE_ID, NLBB081001Constant.COL_TRANSACTION_ID, NLBB081001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB081001Constant.SO_TARGET_ID, regTrxId, this.regUnitId_SO) }), processInfoMap);

            throw new S21AbendException(NLBB081001Constant.NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBB081001Constant.TBL_NLBI1120_03,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_INTERFACE_ID, NLBB081001Constant.COL_TRANSACTION_ID, NLBB081001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB081001Constant.SO_TARGET_ID, regTrxId, this.regUnitId_SO) });
        }

    }

    /**
     * createWmsInbdTrxSer. Mod QC#52035
     * @param wmsSoHdr
     * @param wmsSoSer
     * @param regTrxId
     * @param procStsCd
     * @return
     */
    private void createWmsInbdTrxSer(Map<String, Object> wmsSoHdr, Map<String, Object> wmsSoSer, BigDecimal regTrxId, String intfcProcStsCd, String processNm, Map<String, Object> processInfoMap) {
        WMS_INBD_TRXTMsg wmsInbdTrxT = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.glblCmpyCd, this.glblCmpyCd);
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsInbdTrxPk, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRecId, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.whCd, (String) wmsSoHdr.get(NLBB081001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTaskCd, WMS_TASK.SERL);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcId, (String) wmsSoHdr.get(NLBB081001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcRcvTs, (String) wmsSoHdr.get(NLBB081001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxId, (String) wmsSoHdr.get(NLBB081001Constant.COL_TRANSACTION_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxSqNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_UNIT_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wrkTrxId, NLBB081001Constant.VAL_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcProcStsCd, intfcProcStsCd);
        if (PROC_STS.ERROR.equals(intfcProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.errMsgCd, NLBB081001Constant.NLGM0041E);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsMdseCd, (String) wmsSoSer.get(NLBB081001Constant.COL_WMS_MDSE_CD));
        if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoSer.get(NLBB081001Constant.COL_INVTY_OWNR_CD) + (String) wmsSoSer.get(NLBB081001Constant.COL_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoSer.get(NLBB081001Constant.COL_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsStkStsCd, (String) wmsSoSer.get(NLBB081001Constant.COL_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdQty, (BigDecimal) wmsSoSer.get(NLBB081001Constant.COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdTpCd, (String) wmsSoHdr.get(NLBB081001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdLineNum, (BigDecimal) wmsSoSer.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdStsCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipDtTmTs, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTrxDtTmTs, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTotWt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_CARR_CD));
        // QC#22939
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.proNum, (String)
        // wmsSoHdr.get(NLBB081001Constant.COL_BOL_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.proNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_WMS_RSN_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipId, (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOutCntnrNum, (String) wmsSoHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsFrtChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.serNum, (String) wmsSoSer.get(NLBB081001Constant.COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsBatId, wmsBatId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTpId, (String) wmsSoHdr.get(NLBB081001Constant.COL_INTFC_TP_ID));
        // QC#21281
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId,
        // (String)
        // wmsSoHdr.get(NLBB081001Constant.COL_WMS_ORG_HOST_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsUpdHistNum, BigDecimal.ZERO);

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxT.getReturnCode())) {

            S21InfoLogOutput.println(wmsInbdTrxT.getTableName() + " ReturnCode : " + wmsInbdTrxT.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBB081001Constant.TBL_NLBI1120_03,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_INTERFACE_ID, NLBB081001Constant.COL_TRANSACTION_ID, NLBB081001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB081001Constant.SO_TARGET_ID, regTrxId, this.regUnitId_SO) }), processInfoMap);

            throw new S21AbendException(NLBB081001Constant.NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBB081001Constant.TBL_NLBI1120_03,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_INTERFACE_ID, NLBB081001Constant.COL_TRANSACTION_ID, NLBB081001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB081001Constant.SO_TARGET_ID, regTrxId, this.regUnitId_SO) });
        }
    }

    /**
     * getCarrSvcLvl
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private String getCarrSvcLvl(String tplCarrCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB081001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(NLBB081001Constant.COL_TPL_CARR_CD, tplCarrCd);
        String result = (String) this.ssmBatchClient.queryObject("getCarrSvcLvl", queryParam);
        return result;
    }

    /**
     * WMS Upload Rws Data Creation Process. Mod QC#52035
     */
    private void createRwsInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        BigDecimal regTrxId = trxAccess.getNextTransactionId();

        try {
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
            bindParam.put(NLBB081001Constant.COL_WMS_TASK_CD, NLBB081001Constant.VAL_WMS_TASK_CD_106);
            bindParam.put(NLBB081001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(NLBB081001Constant.COL_INBD_OTBD_CD, NLBB081001Constant.VAL_INBD_OTBD_CD_INBD);
            bindParam.put(NLBB081001Constant.COL_WMS_RSN_CD, NLBB081001Constant.VAL_WMS_RSN_CD);
            bindParam.put(NLBB081001Constant.MAX_LG, NLBB081001Constant.MAX_LG_30);
            bindParam.put(WMS_TASK.RCVD, WMS_TASK.RCVD);

            stmt = ssmLLClient.createPreparedStatement("getWmsRwsHeader", bindParam, execParam);
            rs = stmt.executeQuery();
            // QC#52035 Start
            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                try {
                    processInfoMap = new HashMap<String, Object>();
                    // Get Task Name
                    String wmsTaskNm = rs.getString(NLBB081001Constant.COL_WMS_TASK_NM);
                    // Get Order#
                    String otbdOrdNum = rs.getString(NLBB081001Constant.COL_OTBD_ORD_NUM);

                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(NLBB081001Constant.COL_UNIT_ID);

                    // QC#22934
                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(NLBB081001Constant.COL_SEGMENT_ID);

                    // Convert ResultSet to Map
                    Map<String, Object> wmsRwsHdr = new HashMap<String, Object>();
                    ResultSetMetaData rsm = rs.getMetaData();
                    for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
                        String colName = rsm.getColumnName(i);
                        Object colVal = rs.getObject(colName);
                        wmsRwsHdr.put(colName, colVal);
                    }

                    // Get RWS Detail
                    List<Map<String, Object>> wmsRwsDtlList = getWmsRwsDetail(execParam, trxId, unitId, segmentId);

                    processInfoMap.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(NLBB081001Constant.COL_UNIT_ID, unitId);
                    processInfoMap.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(NLBB081001Constant.COL_WMS_TASK_NM, wmsTaskNm);
                    processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_NUM, otbdOrdNum);
                    processInfoMap.put(NLBB081001Constant.COL_WMS_RSN_CD, (String) wmsRwsHdr.get(NLBB081001Constant.COL_WMS_RSN_CD));

                    // Validation check
                    String intfcProcStsCd = PROC_STS.COMPLEATED;
                    if (!validateIFData(wmsRwsHdr, wmsRwsDtlList, trxId, unitId, WMS_TASK.RCVD, processNm, processInfoMap)) {
                        intfcProcStsCd = PROC_STS.ERROR;
                    }

                    // Create WMS_INBD_TRX Data
                    for (Map<String, Object> wmsRwsDtl : wmsRwsDtlList) {

                        processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM, ((BigDecimal) wmsRwsDtl.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM)).toPlainString());
                        createWmsInbdTrxRcvd(wmsRwsHdr, wmsRwsDtl, regTrxId, intfcProcStsCd, processNm, processInfoMap);

                        if (PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {
                            successCount++;
                        } else {
                            errorCount++;
                        }
                    }
                } catch (EZDAbendException e) {
                    e.printStackTrace();
                    outputErr(processNm, e.getCause().getLocalizedMessage(), processInfoMap);
                    errorCount++;
                }
                // QC#52035 End

            }

            if (successCount > 0) {
                trxAccess.createIntegrationRecordForBatch(NLBB081001Constant.RWS_TARGET_ID, regTrxId);
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB081001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getWmsRwsDetail
     * @param ifaceId
     * @param tranId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsRwsDetail(S21SsmExecutionParameter execParam, BigDecimal tranId, BigDecimal unitId, BigDecimal segmentId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, tranId);
        // QC#22934
        bindParam.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB081001Constant.COL_UNIT_ID, unitId);
        // START 2019/08/20 [QC#52458, ADD]
        bindParam.put(NLBB081001Constant.OTBD_ORD_LINE_NUM_ZERO, NLBB081001Constant.LINE_NUM_ZERO);
        // END 2019/08/20 [QC#52458, ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsRwsDetail", bindParam, execParam);
    }

    /**
     * createWmsInbdTrxRcvd. Mod QC#52035
     * @param wmsRcvHdr
     * @param wmsRcvDtl
     * @param regTrxId
     * @param procStsCd
     * @return
     */
    private void createWmsInbdTrxRcvd(Map<String, Object> wmsRcvHdr, Map<String, Object> wmsRcvDtl, BigDecimal regTrxId, String intfcProcStsCd, String processNm, Map<String, Object> processInfoMap) {
        WMS_INBD_TRXTMsg wmsInbdTrxT = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.glblCmpyCd, this.glblCmpyCd);
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsInbdTrxPk, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRecId, (String) wmsRcvHdr.get(NLBB081001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.whCd, (String) wmsRcvHdr.get(NLBB081001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTaskCd, WMS_TASK.RCVD);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcId, (String) wmsRcvHdr.get(NLBB081001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcRcvTs, (String) wmsRcvHdr.get(NLBB081001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxId, (String) wmsRcvHdr.get(NLBB081001Constant.COL_TRANSACTION_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxSqNum, (String) wmsRcvHdr.get(NLBB081001Constant.COL_UNIT_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wrkTrxId, NLBB081001Constant.VAL_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcProcStsCd, intfcProcStsCd);
        if (PROC_STS.ERROR.equals(intfcProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.errMsgCd, NLBB081001Constant.NLGM0041E);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsMdseCd, (String) wmsRcvDtl.get(NLBB081001Constant.COL_WMS_MDSE_CD));
        if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsRcvDtl.get(NLBB081001Constant.COL_INVTY_OWNR_CD) + (String) wmsRcvDtl.get(NLBB081001Constant.COL_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsRcvDtl.get(NLBB081001Constant.COL_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsStkStsCd, (String) wmsRcvDtl.get(NLBB081001Constant.COL_INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdQty, (BigDecimal) wmsRcvDtl.get(NLBB081001Constant.COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.usrId_03, (String) wmsRcvDtl.get(NLBB081001Constant.COL_WMS_UOM_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRcptDtTmTs, (String) wmsRcvHdr.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));

        // QC#21283
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdNum,
        // (String)
        // wmsRcvHdr.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdNum, (String) wmsRcvHdr.get(NLBB081001Constant.COL_RWS_REF_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdTpCd, (String) wmsRcvHdr.get(NLBB081001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdLineNum, (BigDecimal) wmsRcvDtl.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.serNum, (String) wmsRcvDtl.get(NLBB081001Constant.COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsBatId, wmsBatId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTpId, (String) wmsRcvHdr.get(NLBB081001Constant.COL_INTFC_TP_ID));
        // QC#21281
        // ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId,
        // (String)
        // wmsRcvHdr.get(NLBB081001Constant.COL_WMS_ORG_HOST_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsUpdHistNum, BigDecimal.ZERO);

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxT.getReturnCode())) {
            S21InfoLogOutput.println(wmsInbdTrxT.getTableName() + " ReturnCode : " + wmsInbdTrxT.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBB081001Constant.TBL_NLBI1120_03,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_INTERFACE_ID, NLBB081001Constant.COL_TRANSACTION_ID, NLBB081001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB081001Constant.SO_TARGET_ID, regTrxId, this.regUnitId_SO) }), processInfoMap);


            throw new S21AbendException(NLBB081001Constant.NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBB081001Constant.TBL_NLBI1120_03,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_INTERFACE_ID, NLBB081001Constant.COL_TRANSACTION_ID, NLBB081001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB081001Constant.SO_TARGET_ID, regTrxId, this.regUnitId_SO) });
        }
    }

    private boolean callNLZC405001(Map<String, Object> wmsPodData) {
        NLZC405001PMsg nlzc405001PMsg = new NLZC405001PMsg();

        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.slsDt, this.salesDate);
        String inbdOtbdCd = (String) wmsPodData.get(NLBB081001Constant.COL_INBD_OTBD_CD);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.inbdOtbdCd, inbdOtbdCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxHdrNum, (String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        if ((String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM) != null) {
            String trxLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM)), 3, "0");
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxLineNum, trxLineNum);
        }

        // QC#22939
        // ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum,
        // (String) wmsPodData.get(NLBB081001Constant.COL_BOL_NUM));
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum, (String) wmsPodData.get(NLBB081001Constant.COL_WMS_RSN_CD));
        // QC#52940
        if (INBD_OTBD.OUTBOUND.equals(inbdOtbdCd) && NLBB081001Constant.VAL_WMS_TASK_CD_101.equals(wmsPodData.get(NLBB081001Constant.COL_WMS_TASK_CD))) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, "04"); // Picked
            // Up
            // at
            // WH
        } else if (INBD_OTBD.OUTBOUND.equals(inbdOtbdCd)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.DELIVERED);
        // START 2021/06/19 [QC#58935, ADD]
        // update the status to "Delivered" on RMA when we receive "Delivered" message from carrier.
        } else if (INBD_OTBD.INBOUND.equals(inbdOtbdCd) && NLBB081001Constant.VAL_WMS_TASK_CD_126.equals(wmsPodData.get(NLBB081001Constant.COL_WMS_TASK_CD))) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.DELIVERED);
        // END 2021/06/19 [QC#58935, ADD]
        } else if (INBD_OTBD.INBOUND.equals(inbdOtbdCd)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.PICKED_UP);
        }

        // QC#21303
        if (wmsPodData.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS) != null) {
            // EDI_STS_DT
            String wmsTrxDt = wmsPodData.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS).toString().substring(0, NLBB081001Constant.LG_WMS_SHIP_DT_TO_FOR_POD);
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.xxRqstDt, wmsTrxDt);
            // EDI_STS_TM
            String wmsTrxDTmTst = wmsPodData.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS).toString().substring(NLBB081001Constant.LG_WMS_SHIP_TM_FROM_FOR_POD, NLBB081001Constant.LG_WMS_SHIP_TM_TO_FOR_POD);
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm, wmsTrxDTmTst);
        }
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP.STI);

        // QC#52940 Start
        if (NLBB081001Constant.VAL_WMS_TASK_CD_101.equals(wmsPodData.get(NLBB081001Constant.COL_WMS_TASK_CD))) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.ediStsCd, POD_STS.COMPLETED_LOADING_AT_PICK_UP_LOCATION);
        }
        // QC#52940 End
        NLZC405001 nlzc405001 = new NLZC405001();
        nlzc405001.execute(nlzc405001PMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc405001PMsg);

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                    // START 2019/02/18 [QC#30410, ADD]
                    // START 2019/03/08 [QC#30594, MOD]
                    // outputErr(msgId, null);
                    outputApiCallErr(msgId, null, wmsPodData);
                    // END 2019/03/08 [QC#30594, MOD]
                    // END 2019/02/18 [QC#30410, ADD]
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * createPodInterface. Mod QC#52035
     * @param execParam
     */
    private void createPodInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);

            // QC#21303
            // bindParam.put(NLBB081001Constant.COL_WMS_TASK_CD_ARY,
            // new String[] {NLBB081001Constant.VAL_WMS_TASK_CD_100,
            // NLBB081001Constant.VAL_WMS_TASK_CD_126 });
            bindParam.put(NLBB081001Constant.COL_WMS_TASK_CD_ARY, new String[] {NLBB081001Constant.VAL_WMS_TASK_CD_126 });

            bindParam.put(NLBB081001Constant.COL_WMS_TASK_CD, NLBB081001Constant.VAL_WMS_TASK_CD_101);
            bindParam.put(NLBB081001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(NLBB081001Constant.COL_WMS_RSN_CD, NLBB081001Constant.VAL_WMS_RSN_CD);
            // START 2019/08/20 [QC#52458, ADD]
            bindParam.put(NLBB081001Constant.OTBD_ORD_LINE_NUM_ZERO, NLBB081001Constant.LINE_NUM_ZERO);
            // END 2019/08/20 [QC#52458, ADD]

            stmt = ssmLLClient.createPreparedStatement("getWmsPodData", bindParam, execParam);
            rs = stmt.executeQuery();
            // QC#52035 Start
            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                try {
                    processInfoMap = new HashMap<String, Object>();
                    // Get Task Name
                    String wmsTaskNm = rs.getString(NLBB081001Constant.COL_WMS_TASK_NM);
                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(NLBB081001Constant.COL_UNIT_ID);

                    // QC#22934
                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(NLBB081001Constant.COL_SEGMENT_ID);

                    // Get current BOL_NUM
                    // QC#22939
                    // String bolNum =
                    // rs.getString(NLBB081001Constant.COL_BOL_NUM);
                    String bolNum = rs.getString(NLBB081001Constant.COL_WMS_RSN_CD);

                    // Get WMS Upload [POD] Header
                    Map<String, Object> wmsPodData = new HashMap<String, Object>();

                    wmsPodData.put(NLBB081001Constant.COL_TRANSACTION_ID, rs.getString(NLBB081001Constant.COL_TRANSACTION_ID));
                    wmsPodData.put(NLBB081001Constant.COL_BOL_NUM, rs.getString(NLBB081001Constant.COL_BOL_NUM));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_TASK_CD, rs.getString(NLBB081001Constant.COL_WMS_TASK_CD));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_CARR_CD, rs.getString(NLBB081001Constant.COL_WMS_CARR_CD));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS, rs.getString(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));
                    // QC#21303
                    // wmsPodData.put(NLBB081001Constant.COL_WMS_SUM_PROC_QTY,
                    // rs.getBigDecimal(NLBB081001Constant.COL_WMS_SUM_PROC_QTY));
                    wmsPodData.put(NLBB081001Constant.COL_OTBD_ORD_NUM, rs.getString(NLBB081001Constant.COL_OTBD_ORD_NUM));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_TRX_DT_TM_TS, rs.getString(NLBB081001Constant.COL_WMS_TRX_DT_TM_TS));
                    wmsPodData.put(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM, rs.getString(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM));
                    wmsPodData.put(NLBB081001Constant.COL_INBD_OTBD_CD, rs.getString(NLBB081001Constant.COL_INBD_OTBD_CD));
                    // QC#22939
                    wmsPodData.put(NLBB081001Constant.COL_WMS_RSN_CD, rs.getString(NLBB081001Constant.COL_WMS_RSN_CD));
                    // QC#52584
                    wmsPodData.put(NLBB081001Constant.COL_WMS_TASK_NM, rs.getString(NLBB081001Constant.COL_WMS_TASK_NM));
                    wmsPodData.put(NLBB081001Constant.COL_SEGMENT_ID, rs.getString(NLBB081001Constant.COL_SEGMENT_ID));

                    processInfoMap.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(NLBB081001Constant.COL_UNIT_ID, unitId);
                    processInfoMap.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_NUM, wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
                    processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM, wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM));
                    processInfoMap.put(NLBB081001Constant.COL_WMS_RSN_CD, wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM));
                    processInfoMap.put(NLBB081001Constant.COL_WMS_TASK_NM, wmsTaskNm);

                    // Validation check
                    if (!validatePodIFData(wmsPodData, trxId, bolNum, processNm, processInfoMap)) {
                        // QC#21303
                        continue;
                        // All POD data go to output table including
                        // invalid record
                        // ++errorCount;
                        // continue;
                    }

                    // QC#21303 Mod Start
                    // Break off at new BOL_NUM
                    // if (bolNum == null ||
                    // !bolNum.equals(lastBolNum)) {
                    // ++ediLnCtrlCd;
                    // lastBolNum = bolNum;
                    // }
                    // API Call
                    if (!callNLZC405001(wmsPodData)) {
                        // QC#52584
                        this.errorCount++;
                        // START 2019/02/18 [QC#30410, DEL]
                        // outputErr(NLBB081001Constant.NLGM0041E, new
                        // String[]
                        // {NLBB081001Constant.COL_WMS_RSN_CD,
                        // NLBB081001Constant.TBL_NLBI1120_02,
                        // this.interfaceId + "," +
                        // trxId.toPlainString() + "," + bolNum });
                        // END 2019/02/18 [QC#30410, DEL]
                        continue;
                    }
                    // ++ediStCtrlCd;
                    // createPodData(wmsPodData, ediGsCtrlCd,
                    // ediStCtrlCd, ediLnCtrlCd);
                    // QC#21303 Mod End
                    ++successCount;

                } catch (EZDAbendException e) {
                    e.printStackTrace();
                    outputErr(processNm, e.getCause().getLocalizedMessage(), processInfoMap);
                    errorCount++;
                }
                // QC#52035 End

            }
        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB081001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * createPodWrkInterface. Mod QC#52035
     * @param execParam
     * @param trxId
     */
    private void createPodWrkInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // BigDecimal ediGsCtrlCd = trxAccess.getNextTransactionId();
        BigDecimal ediGsCtrlCd = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");

        int ediStCtrlCd = 0;
        int ediLnCtrlCd = 0;
        String lastBolNum = null;

        try {

            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
            bindParam.put(NLBB081001Constant.COL_WMS_TASK_CD, NLBB081001Constant.VAL_WMS_TASK_CD_100);
            bindParam.put(NLBB081001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(NLBB081001Constant.COL_WMS_RSN_CD, NLBB081001Constant.VAL_WMS_RSN_CD);
            // START 2019/08/20 [QC#52458, ADD]
            bindParam.put(NLBB081001Constant.OTBD_ORD_LINE_NUM_ZERO, NLBB081001Constant.LINE_NUM_ZERO);
            // END 2019/08/20 [QC#52458, ADD]

            stmt = ssmLLClient.createPreparedStatement("getWmsPodWrkData", bindParam, execParam);
            rs = stmt.executeQuery();
            // QC#52035 Start
            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                try {
                    // Get current BOL_NUM
                    // QC#22939
                    //                
                    processInfoMap = new HashMap<String, Object>();
                    // Get Task Name
                    String wmsTaskNm = rs.getString(NLBB081001Constant.COL_WMS_TASK_NM);
                    BigDecimal unitId = rs.getBigDecimal(NLBB081001Constant.COL_UNIT_ID);

                    // QC#22934
                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(NLBB081001Constant.COL_SEGMENT_ID);

                    String bolNum = rs.getString(NLBB081001Constant.COL_WMS_RSN_CD);

                    // Get WMS Upload [POD] Header
                    Map<String, Object> wmsPodData = new HashMap<String, Object>();

                    wmsPodData.put(NLBB081001Constant.COL_TRANSACTION_ID, rs.getString(NLBB081001Constant.COL_TRANSACTION_ID));

                    // QC#22939
                    // wmsPodData.put(NLBB081001Constant.COL_BOL_NUM,
                    // rs.getString(NLBB081001Constant.COL_BOL_NUM));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_RSN_CD, rs.getString(NLBB081001Constant.COL_WMS_RSN_CD));

                    wmsPodData.put(NLBB081001Constant.COL_WMS_TASK_CD, rs.getString(NLBB081001Constant.COL_WMS_TASK_CD));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_CARR_CD, rs.getString(NLBB081001Constant.COL_WMS_CARR_CD));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS, rs.getString(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS));
                    wmsPodData.put(NLBB081001Constant.COL_WMS_SUM_PROC_QTY, rs.getBigDecimal(NLBB081001Constant.COL_WMS_SUM_PROC_QTY));

                    processInfoMap.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(NLBB081001Constant.COL_UNIT_ID, unitId);
                    processInfoMap.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(NLBB081001Constant.COL_WMS_TASK_NM, wmsTaskNm);
                    processInfoMap.put(NLBB081001Constant.COL_WMS_RSN_CD, bolNum);

                    // Validation check
                    if (!validatePodIFData(wmsPodData, trxId, bolNum, processNm, processInfoMap)) {
                        // All POD data go to output table including
                        // invalid record
                        // ++errorCount;
                        // continue;
                    }

                    // Break off at new BOL_NUM
                    if (bolNum == null || !bolNum.equals(lastBolNum)) {
                        ++ediLnCtrlCd;
                        lastBolNum = bolNum;
                    }

                    ++ediStCtrlCd;
                    createPodData(wmsPodData, ediGsCtrlCd, ediStCtrlCd, ediLnCtrlCd, processNm, processInfoMap);
                    ++successCount;
                } catch (EZDAbendException e) {
                    e.printStackTrace();
                    outputErr(processNm, e.getCause().getLocalizedMessage(), processInfoMap);
                    errorCount++;
                }
                // QC#52035 End

            }
        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB081001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * validatePodIFData. Mod QC#52035
     * @param wmsPodData
     * @param trxId
     * @param bolNum
     * @return
     */
    private boolean validatePodIFData(Map<String, Object> wmsPodData, BigDecimal trxId, String bolNum, String processNm, Map<String, Object> processInfoMap) {

        if (wmsPodData.get(NLBB081001Constant.COL_WMS_RSN_CD) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_RSN_CD }), processInfoMap);
            return false;
        }
        if (wmsPodData.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0028E, new String[] {NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS }), processInfoMap);
            return false;
        }

        return true;
    }

    // QC#52035
    private void createPodData(Map<String, Object> wmsPodData, BigDecimal ediGsCtrlCd, int ediStCtrlCd, int ediLnCtrlCd, String processNm, Map<String, Object> processInfoMap) {

        // Register POD_ADDR_WRK...
        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();

        // for (Map<String, Object> wmsPodData : wmsPodDataList) {

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        String strTrxId = (String) wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, strTrxId);

        // EDI_SQ_ID (SeqIDtoSeq) EDI_POD_ADDR_SQ.nextval)
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toPlainString());

        // PROC_STS_CD
        String strProcStsCd = "0";
        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, strProcStsCd);

        // EDI_TP_CD
        String strEdiTpCd = (String) wmsPodData.get(NLBB081001Constant.COL_WMS_CARR_CD);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, strEdiTpCd);

        // EDI_GS_CTRL_CD (For each batch processing of once, Sql ID
        // numbering)
        String ediGsCtrlCdWk = ediGsCtrlCd.toPlainString();
        if (ediGsCtrlCdWk.length() > 10) {
            ediGsCtrlCdWk = ediGsCtrlCdWk.substring(ediGsCtrlCdWk.length() - 10);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, ediGsCtrlCdWk);

        // EDI_ST_CTRL_CD
        String ediStCtrlCdWk = ediGsCtrlCdWk + ZYPCommonFunc.leftPad(String.valueOf(ediStCtrlCd), NLBB081001Constant.LG_EDI_ST_CTRL_CD, NLBB081001Constant.VAL_ZERO);
        if (ediStCtrlCdWk.length() > 10) {
            ediStCtrlCdWk = ediStCtrlCdWk.substring(ediStCtrlCdWk.length() - 10);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, ediStCtrlCdWk);

        // EDI_SHPPR_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, strEdiTpCd);

        // POD_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, "06");

        // //////////////////////////////////////////////
        // insert POD_ADDR_WRK
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {

            S21InfoLogOutput.println(podAddrWrk.getTableName() + " ReturnCode : " + podAddrWrk.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0045E, new String[] {NLBB081001Constant.TBL_POD_ADDR_WRK, NLBB081001Constant.TBL_NLBI1120_02,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID)) }), processInfoMap);

            throw new S21AbendException(NLBB081001Constant.NLGM0045E, new String[] {NLBB081001Constant.TBL_POD_ADDR_WRK, NLBB081001Constant.TBL_NLBI1120_02, NLXCMsgHelper.toListedString(NLBB081001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID)) });
        }

        // Register POD_STS_WRK...
        POD_STS_WRKTMsg podStsWrk = new POD_STS_WRKTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.glblCmpyCd, this.glblCmpyCd);
        // EDI_TRX_ID
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTrxId, strTrxId);
        // EDI_SQ_ID
        BigDecimal ediSqIdSts = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_STS_SQ);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediSqId, ediSqIdSts.toPlainString());

        // PROC_STS_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.procStsCd, strProcStsCd);

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, strEdiTpCd);

        // EDI_GS_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, ediGsCtrlCdWk);

        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, ediStCtrlCdWk);

        // EDI_LN_CTRL_CD
        String strEdiLnCtrlCd = ZYPCommonFunc.leftPad(String.valueOf(ediLnCtrlCd), NLBB081001Constant.LG_EDI_LN_CTRL_CD, NLBB081001Constant.VAL_ZERO);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, strEdiLnCtrlCd);

        // EDI_PRO_NUM
        // QC#22939
        // ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum,
        // (String) wmsPodData.get(NLBB081001Constant.COL_BOL_NUM));
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, (String) wmsPodData.get(NLBB081001Constant.COL_WMS_RSN_CD));

        // EDI_STS_CD
        String wmsTaskCd = (String) wmsPodData.get(NLBB081001Constant.COL_WMS_TASK_CD);

        if (ZYPCommonFunc.hasValue(wmsTaskCd)) {
            String strWmsTaskCd = null;
            if (NLBB081001Constant.VAL_WMS_TASK_CD_126.equals(wmsTaskCd)) {
                strWmsTaskCd = POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION;
            } else if (NLBB081001Constant.VAL_WMS_TASK_CD_100.equals(wmsTaskCd)) {
                strWmsTaskCd = POD_STS.OTHER;
            } else if (NLBB081001Constant.VAL_WMS_TASK_CD_101.equals(wmsTaskCd)) {
                strWmsTaskCd = POD_STS.COMPLETED_LOADING_AT_PICK_UP_LOCATION;
            }
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, strWmsTaskCd);
        }

        String wmsShipDtTmTs = (String) wmsPodData.get(NLBB081001Constant.COL_WMS_SHIP_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(wmsShipDtTmTs)) {
            // EDI_STS_DT
            String strEdiStsDt = wmsShipDtTmTs.substring(0, 8);
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, strEdiStsDt);
            // EDI_STS_TM
            String strEdiStsTm = wmsShipDtTmTs.substring(8, 14);
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, strEdiStsTm);
        }

        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, NLBB081001Constant.VAL_CONST_EDI_TM_CD);

        // EDI_LOAD_QTY
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLoadQty, (BigDecimal) wmsPodData.get(NLBB081001Constant.COL_WMS_SUM_PROC_QTY));

        // //////////////////////////////////////////////
        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {

            S21InfoLogOutput.println(podStsWrk.getTableName() + " ReturnCode : " + podStsWrk.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0045E, new String[] {NLBB081001Constant.TBL_POD_STS_WRK, NLBB081001Constant.TBL_NLBI1120_02,
                    NLXCMsgHelper.toListedString(NLBB081001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID)) }), processInfoMap);
            throw new S21AbendException(NLBB081001Constant.NLGM0045E, new String[] {NLBB081001Constant.TBL_POD_STS_WRK, NLBB081001Constant.TBL_NLBI1120_02, NLXCMsgHelper.toListedString(NLBB081001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID)) });
        }
    }

    // Mod QC#52035
    private void outputErr(String processNm, String errMsg, Map<String, Object> processInfoMap) {
        StringBuilder builder = new StringBuilder();
        // QC#52584
        builder.append(generateErrorInfo(processNm, errMsg, processInfoMap));

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, "NLBM1257E");
        mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(builder.toString());
    }

    // Add QC#52035
    private String generateErrorInfo(String operationNm, String errMsg, Map<String, Object> processInfoMap) {
        StringBuilder builder = new StringBuilder();
        builder.append(errMsg);
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Operation         : ");
        builder.append(operationNm);
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append(this.interfaceId);
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        if (ZYPCommonFunc.hasValue((BigDecimal) processInfoMap.get(NLBB081001Constant.COL_TRANSACTION_ID))) {
            builder.append(((BigDecimal) processInfoMap.get(NLBB081001Constant.COL_TRANSACTION_ID)).toPlainString());
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue((BigDecimal) processInfoMap.get(NLBB081001Constant.COL_SEGMENT_ID))) {
            builder.append(((BigDecimal) processInfoMap.get(NLBB081001Constant.COL_SEGMENT_ID)).toPlainString());
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Tracking#         : ");
        if (ZYPCommonFunc.hasValue((String) processInfoMap.get(NLBB081001Constant.COL_WMS_RSN_CD))) {
            builder.append((String) processInfoMap.get(NLBB081001Constant.COL_WMS_RSN_CD));
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Order#            : ");
        if (ZYPCommonFunc.hasValue((String) processInfoMap.get(NLBB081001Constant.COL_OTBD_ORD_NUM))) {
            builder.append((String) processInfoMap.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        // Mod Start 2019/12/05 QC#54887
        if (processInfoMap.containsKey(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM)) {
            builder.append(NLBB081001Constant.LINE_SEPT);
            builder.append(NLBB081001Constant.VAL_BLANK_14);
            builder.append("Line#             : ");
            if (ZYPCommonFunc.hasValue((String) processInfoMap.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM))) {
                String otbdLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) processInfoMap.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM)), 3, "0");
                builder.append(otbdLineNum);
            } else {
                builder.append(NLBB081001Constant.VAL_BLANK);
            }
        }
        if (processInfoMap.containsKey(NLBB081001Constant.COL_WMS_TASK_NM)) {
            builder.append(NLBB081001Constant.LINE_SEPT);
            builder.append(NLBB081001Constant.VAL_BLANK_14);
            builder.append("Process Name      : ");
            if (ZYPCommonFunc.hasValue((String) processInfoMap.get(NLBB081001Constant.COL_WMS_TASK_NM))) {
                builder.append((String) processInfoMap.get(NLBB081001Constant.COL_WMS_TASK_NM));
            } else {
                builder.append(NLBB081001Constant.VAL_BLANK);
            }
        }
        // Mod End 2019/12/05 QC#54887
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_SEP_LINE);
        return builder.toString();
    }

    // /**
    // * Output Error Message.
    // * @param msgId MessageId
    // * @param msgParam MessageParameters
    // */
    // private void outputErr(String msgId, String[] msgParam) {
    //
    // Map<String, String> mailParam = new HashMap<String, String>();
    // mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
    // mailParam.put(NLXMailSend.KEY_MESSAGE,
    // S21MessageFunc.clspGetMessage(msgId, msgParam));
    // errMsgList.add(mailParam);
    //
    // S21InfoLogOutput.println(msgId, msgParam);
    // }
    /**
     * getTplCarrSvcLvl
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvl(String carrCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB081001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(NLBB081001Constant.COL_CARR_CD, carrCd);
        queryParam.put(NLBB081001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return ((Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam));
    }

    // START 2019/03/08 [QC#30594, ADD]
    private void outputApiCallErr(String msgId, String[] msgParam, Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        // QC#52584
        builder.append(generateWmsPodKeyInfo(wmsPodData, msgId, msgParam));

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, "NLBM1257E");
        mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    private String generateWmsPodKeyInfo(Map<String, Object> wmsPodData, String msgId, String[] msgParam) {
        // Mod QC#52584 Start
        StringBuilder builder = new StringBuilder();
        builder.append("POD Process Error : ");
        builder.append(S21MessageFunc.clspGetMessage(msgId, msgParam));
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append("");
        builder.append(this.interfaceId);
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        builder.append("");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID))) {
            builder.append((String) wmsPodData.get(NLBB081001Constant.COL_TRANSACTION_ID));
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(NLBB081001Constant.COL_SEGMENT_ID))) {
            builder.append((String) wmsPodData.get(NLBB081001Constant.COL_SEGMENT_ID));
        } else {

        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Order#            : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_NUM))) {
            builder.append((String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_NUM));
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Line#             : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM))) {
            String otbdLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) wmsPodData.get(NLBB081001Constant.COL_OTBD_ORD_LINE_NUM)), 3, "0");
            builder.append(otbdLineNum);
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_BLANK_14);
        builder.append("Process Name      : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(NLBB081001Constant.COL_WMS_TASK_NM))) {
            builder.append((String) wmsPodData.get(NLBB081001Constant.COL_WMS_TASK_NM));
        } else {
            builder.append(NLBB081001Constant.VAL_BLANK);
        }
        builder.append(NLBB081001Constant.LINE_SEPT);
        builder.append(NLBB081001Constant.VAL_SEP_LINE);
        return builder.toString();
        // QC#52584 End
    }

    // END 2019/03/08 [QC#30594, ADD]

    /**
     * getWmsSoDetail Add QC#50666
     * @param execParam
     * @param soSlpNumList
     * @param trxId
     * @param unitId
     * @param otbdOrdNum
     * @return
     */
    private List<Map<String, Object>> getConfigSoDtl(S21SsmExecutionParameter execParam, List<BigDecimal> soSlpNumList, BigDecimal trxId, BigDecimal unitId, BigDecimal segmentId, String otbdOrdNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB081001Constant.COL_UNIT_ID, unitId);
        bindParam.put(NLBB081001Constant.COL_SO_NUM, otbdOrdNum);

        if (soSlpNumList != null && !soSlpNumList.isEmpty()) {
            bindParam.put("soSlpNumList", soSlpNumList);
        }

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getConfigSoDtl", bindParam, execParam);
    }

    /**
     * getConfigSoSerial Add QC#50666
     * @param execParam
     * @param soSlpNumList
     * @param trxId
     * @param unitId
     * @param segmentId
     * @param otbdOrdNum
     * @return
     */
    private List<Map<String, Object>> getConfigSoSerial(S21SsmExecutionParameter execParam, List<BigDecimal> soSlpNumList, BigDecimal trxId, BigDecimal unitId, BigDecimal segmentId, String otbdOrdNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB081001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB081001Constant.COL_UNIT_ID, unitId);
        bindParam.put(WMS_TASK.SERL, WMS_TASK.SERL);
        bindParam.put(NLBB081001Constant.COL_SO_NUM, otbdOrdNum);
        if (soSlpNumList != null && !soSlpNumList.isEmpty()) {
            bindParam.put("soSlpNumList", soSlpNumList);
        }
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        bindParam.put("flgN", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getConfigSoSerial", bindParam, execParam);
    }

    // Add Start 2019/12/04 QC#54887
    /**
     * checkDetailData.
     * @param execParam
     * @param trxId
     * @param processNm
     */
    private void checkDetailData(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB081001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);

            stmt = ssmLLClient.createPreparedStatement("getNotExistDetail", bindParam, execParam);
            rs = stmt.executeQuery();
            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            while (rs.next()) {

                trxId = rs.getBigDecimal(NLBB081001Constant.COL_TRANSACTION_ID);
                BigDecimal segmentId = rs.getBigDecimal(NLBB081001Constant.COL_SEGMENT_ID);
                BigDecimal unitId = rs.getBigDecimal(NLBB081001Constant.COL_UNIT_ID);
                String otbdOrdNum = rs.getString(NLBB081001Constant.COL_OTBD_ORD_NUM);
                String wmsRsnCd = rs.getString(NLBB081001Constant.COL_WMS_RSN_CD);

                processInfoMap.put(NLBB081001Constant.COL_TRANSACTION_ID, trxId);
                processInfoMap.put(NLBB081001Constant.COL_UNIT_ID, unitId);
                processInfoMap.put(NLBB081001Constant.COL_SEGMENT_ID, segmentId);
                processInfoMap.put(NLBB081001Constant.COL_OTBD_ORD_NUM, otbdOrdNum);
                processInfoMap.put(NLBB081001Constant.COL_WMS_RSN_CD, wmsRsnCd);

                outputErr(processNm, S21MessageFunc.clspGetMessage(NLBB081001Constant.NLGM0090E, new String[] { otbdOrdNum }), processInfoMap);
                errorCount++;
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB081001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    // Add End 2019/12/04 QC#54887
}
