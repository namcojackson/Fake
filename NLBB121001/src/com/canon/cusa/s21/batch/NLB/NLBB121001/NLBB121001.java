/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB121001;

import static com.canon.cusa.s21.batch.NLB.NLBB121001.constant.NLBB121001Constant.*;

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
 * Shipping Order to AGW
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2023   Hitachi         M.Nakajima      Create          N/A
 *</pre>
 */
public class NLBB121001 extends S21BatchMain {

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
    private int soUnitId = 0;

    /** WMS_BAT_ID */
    private String wmsBatId = "";

    /** WMS_ORG_HOST_ID */
    private String wmsOrgHostId = null;

    /** Sales Date */
    private String salesDate = null;

    /** DEF_WMS_CARR_CD */
    private String defWmsCarrCd = "";

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NLBB121001().executeBatch(NLBB121001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {GLBL_CMPY_CD });
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLGM0049E, new String[] {INTERFACE_ID });
        }

        // Batch ID
        wmsBatId = ZYPDateUtil.getCurrentSystemTime(VAL_DATE_TIME_FORMAT_FROM);

        // WH_OWNER_CD
        whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB1200_TARGET_ORDER, glblCmpyCd);

        // WMS_PACK_CD_SET_OWNER_CD_FLG
        wmsPackCdFlg = ZYPCodeDataUtil.getVarCharConstValue(WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);
        if (wmsPackCdFlg == null) {
            wmsPackCdFlg = ZYPConstant.FLG_OFF_N;
        }

        // WMS_ORG_HOST_ID
        wmsOrgHostId = ZYPCodeDataUtil.getVarCharConstValue(WMS_ORG_HOST_ID_3PL, glblCmpyCd);

        // DEF_WMS_CARR_CD
        this.defWmsCarrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB1210_DEF_WMS_CARR_CD, glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {

        // Set the fetch size.
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIdList = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIdList.length == 0) {
            return;
        }

        try {
            // Loop Transaction Id Unit
            for (BigDecimal tranId : tranIdList) {

                // SO
                String processNm = "Ship Confirm";
                createSoInterface(execParam, tranId, processNm);

                // RWS
                processNm = "RWS Receive";
                createRwsInterface(execParam, tranId, processNm);

                // POD (Proof of Delivery)
                processNm = "Proof of Delivery";
                createPodInterface(execParam, tranId, processNm);

                // POD (Proof of Delivery) WRK Data
                processNm = "Proof of Delivery WRK";
                createPodWrkInterface(execParam, tranId, processNm);

                // Check Detail Data
                processNm = "Check Detail Data";
                checkDetailData(execParam, tranId, processNm);

                // Update processed flag
                trxAccess.endIntegrationProcess(this.interfaceId, tranId);

                commit();
            }
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(this.termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * WMS Upload SO Data Creation Process.
     */
    private void createSoInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(COL_TRANSACTION_ID, trxId);
            bindParam.put(COL_WMS_TASK_CD, VAL_WMS_TASK_CD_101);
            bindParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(COL_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
            bindParam.put(COL_WMS_RSN_CD, VAL_WMS_RSN_CD);
            bindParam.put("maxLen", MAX_LG_30);
            bindParam.put(WMS_TASK.SHIP, WMS_TASK.SHIP);

            stmt = ssmLLClient.createPreparedStatement("getWmsSoHeader", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop n UNIT_ID unit
            while (rs.next()) {
                Map<String, Object> processInfoMap = new HashMap<String, Object>();

                try {
                    processInfoMap = new HashMap<String, Object>();

                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(COL_UNIT_ID);

                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(COL_SEGMENT_ID);

                    // Convert ResultSet to Map
                    Map<String, Object> wmsSoHdr = new HashMap<String, Object>();
                    ResultSetMetaData rsm = rs.getMetaData();
                    for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
                        String colName = rsm.getColumnName(i);
                        Object colVal = rs.getObject(colName);
                        wmsSoHdr.put(colName, colVal);
                    }

                    // Get OTBD_ORD_NUM
                    String otbdOrdNum = (String) wmsSoHdr.get(COL_OTBD_ORD_NUM);

                    processInfoMap.put(COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(COL_UNIT_ID, unitId);
                    processInfoMap.put(COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(COL_OTBD_ORD_NUM, otbdOrdNum);
                    processInfoMap.put(COL_WMS_TASK_NM, rs.getString(COL_WMS_TASK_NM));
                    processInfoMap.put(COL_WMS_RSN_CD, (String) wmsSoHdr.get(COL_WMS_RSN_CD));

                    // Get SO Detail
                    List<Map<String, Object>> wmsSoDtlList = getWmsSoDetail(execParam, trxId, unitId, segmentId);

                    List<Map<String, Object>> tmpSoDtlList = new ArrayList<Map<String, Object>>();
                    List<BigDecimal> soSlpNumList = new ArrayList<BigDecimal>();

                    if (wmsSoDtlList != null && wmsSoDtlList.size() > 0) {
                        for (Map<String, Object> wmsDtl : wmsSoDtlList) {
                            if (wmsDtl.get(COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
                                String wmsMdseCd = (String) wmsDtl.get(COL_WMS_MDSE_CD);
                                String soMdseCd = (String) wmsDtl.get(COL_MDSE_CD);
                                if (!wmsMdseCd.equals(soMdseCd)) {
                                    continue;
                                }
                            }
                            BigDecimal soSlpNum = (BigDecimal) wmsDtl.get(COL_OTBD_ORD_LINE_NUM);
                            soSlpNumList.add(soSlpNum);
                            tmpSoDtlList.add(wmsDtl);
                        }
                    }
                    wmsSoDtlList.clear();
                    wmsSoDtlList.addAll(tmpSoDtlList);
                    List<Map<String, Object>> soConfigDtlList = getConfigSoDtl(execParam, soSlpNumList, trxId, unitId, segmentId, otbdOrdNum);
                    if (soConfigDtlList != null && !soConfigDtlList.isEmpty()) {
                        wmsSoDtlList.addAll(soConfigDtlList);
                    }

                    // Validation check
                    String intfcProcStsCd = PROC_STS.COMPLEATED;
                    if (!validateIFData(wmsSoHdr, wmsSoDtlList, trxId, unitId, WMS_TASK.SHIP, processNm, processInfoMap) || soSlpNumList.isEmpty()) {
                        intfcProcStsCd = PROC_STS.ERROR;
                    }

                    for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {

                        BigDecimal otbdOrdLineNum = (BigDecimal) wmsSoDtl.get(COL_OTBD_ORD_LINE_NUM);
                        if (otbdOrdLineNum != null){
                            processInfoMap.put(COL_OTBD_ORD_LINE_NUM, otbdOrdLineNum.toPlainString());
                        }
                        createWmsInbdTrxShip(wmsSoHdr, wmsSoDtl, trxId, intfcProcStsCd, processNm, processInfoMap);

                        // Get SO Serial
                        List<Map<String, Object>> wmsSoSerList = getWmsSoSerial(execParam, trxId, unitId, otbdOrdLineNum, segmentId);
                        for (Map<String, Object> wmsSoSer : wmsSoSerList) {
                            if (wmsSoSer.get(COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
                                String wmsMdseCd = (String) wmsSoSer.get(COL_WMS_MDSE_CD);
                                String soMdseCd = (String) wmsSoSer.get(COL_MDSE_CD);
                                if (!wmsMdseCd.equals(soMdseCd)) {
                                    continue;
                                }
                            }
                            createWmsInbdTrxSer(wmsSoHdr, wmsSoSer, trxId, intfcProcStsCd, processNm, processInfoMap);
                        }

                        if (PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {
                            successCount++;
                        } else {
                            errorCount++;
                        }
                    }
                    if (soSlpNumList.size() > 0) {
                        List<Map<String, Object>> soConfigSerList = getConfigSoSerial(execParam, soSlpNumList, trxId, unitId, segmentId, otbdOrdNum);
                        if (soConfigSerList != null && !soConfigSerList.isEmpty()) {
                            for (Map<String, Object> wmsSoSer : soConfigSerList) {
                                createWmsInbdTrxSer(wmsSoHdr, wmsSoSer, trxId, intfcProcStsCd, processNm, processInfoMap);
                            }
                            if (PROC_STS.COMPLEATED.equals(intfcProcStsCd)) {
                                successCount++;
                            } else {
                                errorCount++;
                            }
                        }
                    } else {
                        if (processInfoMap.containsKey(COL_OTBD_ORD_LINE_NUM)) {
                            processInfoMap.remove(COL_OTBD_ORD_LINE_NUM);
                        }
                        outputErr(processNm, S21MessageFunc.clspGetMessage(NLBM1375E), processInfoMap);
                    }
                } catch (EZDAbendException e) {
                    e.printStackTrace();
                    outputErr(processNm, e.getCause().getLocalizedMessage(), processInfoMap);
                    errorCount++;
                }
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
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
    private List<Map<String, Object>> getWmsSoDetail(S21SsmExecutionParameter execParam
                                                      , BigDecimal trxId
                                                      , BigDecimal unitId
                                                      , BigDecimal segmentId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(COL_TRANSACTION_ID, trxId);
        bindParam.put(COL_SEGMENT_ID, segmentId);
        bindParam.put(COL_UNIT_ID, unitId);
        bindParam.put("OTBD_ORD_LINE_NUM_ZERO", LINE_NUM_ZERO);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoDetail", bindParam, execParam);
    }

    /**
     * getWmsSoDetail
     * @param execParam
     * @param soSlpNumList
     * @param trxId
     * @param unitId
     * @param otbdOrdNum
     * @return
     */
    private List<Map<String, Object>> getConfigSoDtl(S21SsmExecutionParameter execParam
                                                      , List<BigDecimal> soSlpNumList
                                                      , BigDecimal trxId
                                                      , BigDecimal unitId
                                                      , BigDecimal segmentId
                                                      , String otbdOrdNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(COL_TRANSACTION_ID, trxId);
        bindParam.put(COL_SEGMENT_ID, segmentId);
        bindParam.put(COL_UNIT_ID, unitId);
        bindParam.put(COL_SO_NUM, otbdOrdNum);

        if (soSlpNumList != null && !soSlpNumList.isEmpty()) {
            bindParam.put("soSlpNumList", soSlpNumList);
        }

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getConfigSoDtl", bindParam, execParam);
    }

    /**
     * getConfigSoSerial
     * @param execParam
     * @param soSlpNumList
     * @param trxId
     * @param unitId
     * @param segmentId
     * @param otbdOrdNum
     * @return
     */
    private List<Map<String, Object>> getConfigSoSerial(S21SsmExecutionParameter execParam
                                                         , List<BigDecimal> soSlpNumList
                                                         , BigDecimal trxId
                                                         , BigDecimal unitId
                                                         , BigDecimal segmentId
                                                         , String otbdOrdNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(COL_TRANSACTION_ID, trxId);
        bindParam.put(COL_SEGMENT_ID, segmentId);
        bindParam.put(COL_UNIT_ID, unitId);
        bindParam.put(WMS_TASK.SERL, WMS_TASK.SERL);
        bindParam.put(COL_SO_NUM, otbdOrdNum);
        if (soSlpNumList != null && !soSlpNumList.isEmpty()) {
            bindParam.put("soSlpNumList", soSlpNumList);
        }
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        bindParam.put("flgN", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getConfigSoSerial", bindParam, execParam);
    }

    /**
     * getWmsSoSerial
     * @param execParam
     * @param trxId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsSoSerial(S21SsmExecutionParameter execParam
                                                      , BigDecimal trxId
                                                      , BigDecimal unitId
                                                      , BigDecimal otbdOrdLineNum
                                                      , BigDecimal segmentId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(COL_TRANSACTION_ID, trxId);
        bindParam.put(COL_SEGMENT_ID, segmentId);
        bindParam.put(COL_UNIT_ID, unitId);
        bindParam.put(COL_OTBD_ORD_LINE_NUM, otbdOrdLineNum);
        bindParam.put(WMS_TASK.SERL, WMS_TASK.SERL);
        bindParam.put("OTBD_ORD_LINE_NUM_ZERO", LINE_NUM_ZERO);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoSerial", bindParam, execParam);
    }

    /**
     * createWmsInbdTrxShip
     * @param wmsSoHdr
     * @param wmsSoDtl
     * @param trxId
     * @param procStsCd
     * @return
     */
    private void createWmsInbdTrxShip(Map<String, Object> wmsSoHdr
                                       , Map<String, Object> wmsSoDtl
                                       , BigDecimal trxId
                                       , String intfcProcStsCd
                                       , String processNm
                                       , Map<String, Object> processInfoMap) {
        WMS_INBD_TRXTMsg wmsInbdTrxT = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.glblCmpyCd, this.glblCmpyCd);
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsInbdTrxPk, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRecId, (String) wmsSoHdr.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.whCd, (String) wmsSoHdr.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTaskCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcId, (String) wmsSoHdr.get(COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcRcvTs, (String) wmsSoHdr.get(COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxId, (String) wmsSoHdr.get(COL_TRANSACTION_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxSqNum, (String) wmsSoHdr.get(COL_UNIT_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wrkTrxId, VAL_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcProcStsCd, intfcProcStsCd);
        if (PROC_STS.ERROR.equals(intfcProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.errMsgCd, NLGM0041E);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsMdseCd, (String) wmsSoDtl.get(COL_WMS_MDSE_CD));
        if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoDtl.get(COL_INVTY_OWNR_CD) + (String) wmsSoDtl.get(COL_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoDtl.get(COL_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsStkStsCd, (String) wmsSoDtl.get(COL_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdQty, (BigDecimal) wmsSoDtl.get(COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdNum, (String) wmsSoHdr.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdTpCd, (String) wmsSoHdr.get(COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdLineNum, (BigDecimal) wmsSoDtl.get(COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdStsCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipDtTmTs, (String) wmsSoHdr.get(COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTrxDtTmTs, (String) wmsSoHdr.get(COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTotWt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);

        Map<String, Object> tplCarrSvcLvl = getTplCarrSvcLvl((String) wmsSoHdr.get(COL_CARR_CD), (String) wmsSoHdr.get(COL_SHPG_SVC_LVL_CD));

        if (tplCarrSvcLvl == null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, this.defWmsCarrCd);
        } else if (!ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(COL_CARR_CD))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, this.defWmsCarrCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, (String) tplCarrSvcLvl.get(COL_TPL_CARR_SVC_LVL_CD));
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.proNum, (String) wmsSoHdr.get(COL_WMS_RSN_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipId, (String) wmsSoHdr.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOutCntnrNum, (String) wmsSoHdr.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsFrtChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsBatId, wmsBatId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTpId, (String) wmsSoHdr.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsUpdHistNum, BigDecimal.ZERO);

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxT.getReturnCode())) {

            S21InfoLogOutput.println(wmsInbdTrxT.getTableName() + " ReturnCode : " + wmsInbdTrxT.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBI1520_03
                    , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                    , NLXCMsgHelper.toListedString(this.interfaceId, trxId, this.soUnitId) })
                    , processInfoMap);

            throw new S21AbendException(NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBI1520_03
                    , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                    , NLXCMsgHelper.toListedString(this.interfaceId, trxId, this.soUnitId) });
        }
    }

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
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(COL_CARR_CD, carrCd);
        queryParam.put(COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return ((Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam));
    }

    /**
     * createWmsInbdTrxSer
     * @param wmsSoHdr
     * @param wmsSoSer
     * @param trxId
     * @param procStsCd
     * @return
     */
    private void createWmsInbdTrxSer(Map<String, Object> wmsSoHdr
                                      , Map<String, Object> wmsSoSer
                                      , BigDecimal trxId
                                      , String intfcProcStsCd
                                      , String processNm
                                      , Map<String, Object> processInfoMap) {
        WMS_INBD_TRXTMsg wmsInbdTrxT = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.glblCmpyCd, this.glblCmpyCd);
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsInbdTrxPk, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRecId, (String) wmsSoHdr.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.whCd, (String) wmsSoHdr.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTaskCd, WMS_TASK.SERL);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcId, (String) wmsSoHdr.get(COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcRcvTs, (String) wmsSoHdr.get(COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxId, (String) wmsSoHdr.get(COL_TRANSACTION_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxSqNum, (String) wmsSoHdr.get(COL_UNIT_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wrkTrxId, VAL_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcProcStsCd, intfcProcStsCd);
        if (PROC_STS.ERROR.equals(intfcProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.errMsgCd, NLGM0041E);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsMdseCd, (String) wmsSoSer.get(COL_WMS_MDSE_CD));
        if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoSer.get(COL_INVTY_OWNR_CD) + (String) wmsSoSer.get(COL_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsSoSer.get(COL_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsStkStsCd, (String) wmsSoSer.get(COL_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdQty, (BigDecimal) wmsSoSer.get(COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdNum, (String) wmsSoHdr.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdTpCd, (String) wmsSoHdr.get(COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.otbdOrdLineNum, (BigDecimal) wmsSoSer.get(COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdStsCd, WMS_TASK.SHIP);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipDtTmTs, (String) wmsSoHdr.get(COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTrxDtTmTs, (String) wmsSoHdr.get(COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTotWt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsCarrCd, (String) wmsSoHdr.get(COL_WMS_CARR_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.proNum, (String) wmsSoHdr.get(COL_WMS_RSN_CD));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsShipId, (String) wmsSoHdr.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOutCntnrNum, (String) wmsSoHdr.get(COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsFrtChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.serNum, (String) wmsSoSer.get(COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsBatId, wmsBatId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTpId, (String) wmsSoHdr.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsUpdHistNum, BigDecimal.ZERO);

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxT.getReturnCode())) {

            S21InfoLogOutput.println(wmsInbdTrxT.getTableName() + " ReturnCode : " + wmsInbdTrxT.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBI1520_03
                    , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                    , NLXCMsgHelper.toListedString(this.interfaceId, trxId, this.soUnitId) })
                    , processInfoMap);

            throw new S21AbendException(NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBI1520_03
                    , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                    , NLXCMsgHelper.toListedString(this.interfaceId, trxId, this.soUnitId) });
        }
    }

    /**
     * WMS Upload Rws Data Creation Process
     */
    private void createRwsInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(COL_TRANSACTION_ID, trxId);
            bindParam.put(COL_WMS_TASK_CD, VAL_WMS_TASK_CD_106);
            bindParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(COL_INBD_OTBD_CD, INBD_OTBD.INBOUND);
            bindParam.put(COL_WMS_RSN_CD, VAL_WMS_RSN_CD);
            bindParam.put("maxLen", MAX_LG_30);
            bindParam.put(WMS_TASK.RCVD, WMS_TASK.RCVD);

            stmt = ssmLLClient.createPreparedStatement("getWmsRwsHeader", bindParam, execParam);
            rs = stmt.executeQuery();

            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                try {
                    processInfoMap = new HashMap<String, Object>();
                    // Get Task Name
                    String wmsTaskNm = rs.getString(COL_WMS_TASK_NM);
                    // Get Order#
                    String otbdOrdNum = rs.getString(COL_OTBD_ORD_NUM);
                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(COL_UNIT_ID);
                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(COL_SEGMENT_ID);

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

                    processInfoMap.put(COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(COL_UNIT_ID, unitId);
                    processInfoMap.put(COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(COL_WMS_TASK_NM, wmsTaskNm);
                    processInfoMap.put(COL_OTBD_ORD_NUM, otbdOrdNum);
                    processInfoMap.put(COL_WMS_RSN_CD, (String) wmsRwsHdr.get(COL_WMS_RSN_CD));

                    // Validation check
                    String intfcProcStsCd = PROC_STS.COMPLEATED;
                    if (!validateIFData(wmsRwsHdr, wmsRwsDtlList, trxId, unitId, WMS_TASK.RCVD, processNm, processInfoMap)) {
                        intfcProcStsCd = PROC_STS.ERROR;
                    }

                    // Create WMS_INBD_TRX Data
                    for (Map<String, Object> wmsRwsDtl : wmsRwsDtlList) {

                        if (wmsRwsDtl.get(COL_OTBD_ORD_LINE_NUM) != null){
                            processInfoMap.put(COL_OTBD_ORD_LINE_NUM, ((BigDecimal) wmsRwsDtl.get(COL_OTBD_ORD_LINE_NUM)).toPlainString());
                        }
                        createWmsInbdTrxRcvd(wmsRwsHdr, wmsRwsDtl, trxId, intfcProcStsCd, processNm, processInfoMap);

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
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * getWmsRwsDetail
     * @param ifaceId
     * @param trxId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsRwsDetail(S21SsmExecutionParameter execParam
                                                       , BigDecimal trxId
                                                       , BigDecimal unitId
                                                       , BigDecimal segmentId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(COL_TRANSACTION_ID, trxId);
        bindParam.put(COL_SEGMENT_ID, segmentId);
        bindParam.put(COL_UNIT_ID, unitId);
        bindParam.put("OTBD_ORD_LINE_NUM_ZERO", LINE_NUM_ZERO);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsRwsDetail", bindParam, execParam);
    }

    /**
     * createWmsInbdTrxRcvd
     * @param wmsRcvHdr
     * @param wmsRcvDtl
     * @param trxId
     * @param procStsCd
     * @return
     */
    private void createWmsInbdTrxRcvd(Map<String, Object> wmsRcvHdr
                                       , Map<String, Object> wmsRcvDtl
                                       , BigDecimal trxId
                                       , String intfcProcStsCd
                                       , String processNm
                                       , Map<String, Object> processInfoMap) {
        WMS_INBD_TRXTMsg wmsInbdTrxT = new WMS_INBD_TRXTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.glblCmpyCd, this.glblCmpyCd);
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsInbdTrxPk, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRecId, (String) wmsRcvHdr.get(COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.whCd, (String) wmsRcvHdr.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsTaskCd, WMS_TASK.RCVD);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcId, (String) wmsRcvHdr.get(COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcRcvTs, (String) wmsRcvHdr.get(COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxId, (String) wmsRcvHdr.get(COL_TRANSACTION_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTrxSqNum, (String) wmsRcvHdr.get(COL_UNIT_ID_CHAR));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wrkTrxId, VAL_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcProcStsCd, intfcProcStsCd);
        if (PROC_STS.ERROR.equals(intfcProcStsCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.errMsgCd, NLGM0041E);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsMdseCd, (String) wmsRcvDtl.get(COL_WMS_MDSE_CD));
        if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsRcvDtl.get(COL_INVTY_OWNR_CD) + (String) wmsRcvDtl.get(COL_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsPkgCd, (String) wmsRcvDtl.get(COL_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsStkStsCd, (String) wmsRcvDtl.get(COL_INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrdQty, (BigDecimal) wmsRcvDtl.get(COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.usrId_03, (String) wmsRcvDtl.get(COL_WMS_UOM_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsRcptDtTmTs, (String) wmsRcvHdr.get(COL_WMS_SHIP_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdNum, (String) wmsRcvHdr.get(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdTpCd, (String) wmsRcvHdr.get(COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.inbdOrdLineNum, (BigDecimal) wmsRcvDtl.get(COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.serNum, (String) wmsRcvDtl.get(COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsBatId, wmsBatId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.intfcTpId, (String) wmsRcvHdr.get(COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxT.wmsUpdHistNum, BigDecimal.ZERO);

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxT.getReturnCode())) {
            S21InfoLogOutput.println(wmsInbdTrxT.getTableName() + " ReturnCode : " + wmsInbdTrxT.getReturnCode());
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBI1520_03
                    , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                    , NLXCMsgHelper.toListedString(this.interfaceId, trxId, this.soUnitId) })
                    , processInfoMap);

            throw new S21AbendException(NLGM0045E, new String[] {wmsInbdTrxT.getTableName(), NLBI1520_03
                    , NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                    , NLXCMsgHelper.toListedString(this.interfaceId, trxId, this.soUnitId) });
        }
    }

    /**
     * validateIFData.
     * @param wmsHdr
     * @param wmsDtlList
     * @param trxId
     * @param unitId
     * @param wmsTask
     * @return
     */
    private boolean validateIFData(Map<String, Object> wmsHdr
                                    , List<Map<String, Object>> wmsDtlList
                                    , BigDecimal trxId
                                    , BigDecimal unitId
                                    , String wmsTask
                                    , String processNm
                                    , Map<String, Object> processInfoMap) {

        if (wmsHdr.get(COL_WMS_REC_ID) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_REC_ID }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(COL_WH_CD) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WH_CD }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(COL_EZINTIME) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_EZINTIME }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(COL_OTBD_ORD_NUM) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_OTBD_ORD_NUM }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(COL_S80_ORD_TP_CD) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_S80_ORD_TP_CD }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(COL_WMS_SHIP_DT_TM_TS) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_SHIP_DT_TM_TS }), processInfoMap);
            return false;
        }
        if (wmsHdr.get(COL_INTFC_TP_ID) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_INTFC_TP_ID }), processInfoMap);
            return false;
        }

        if (wmsDtlList.size() > 0) {
            for (Map<String, Object> wmsDtl : wmsDtlList) {
                if (wmsDtl.get(COL_WMS_MDSE_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_MDSE_CD }), processInfoMap);
                    return false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg) && wmsDtl.get(COL_INVTY_OWNR_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_INVTY_OWNR_CD }), processInfoMap);
                    this.wmsPackCdFlg = ZYPConstant.FLG_OFF_N;
                    return false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(wmsPackCdFlg) && wmsDtl.get(COL_RTL_SWH_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_RTL_SWH_CD }), processInfoMap);
                    this.wmsPackCdFlg = ZYPConstant.FLG_OFF_N;
                    return false;
                }
                if (ZYPConstant.FLG_OFF_N.equals(wmsPackCdFlg) && wmsDtl.get(COL_INVTY_LOC_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_INVTY_LOC_CD }), processInfoMap);
                    return false;
                }
                BigDecimal wmsProcQty = (BigDecimal) wmsDtl.get(COL_WMS_PROC_QTY);
                if (wmsProcQty == null || BigDecimal.ZERO.compareTo(wmsProcQty) >= 0) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_PROC_QTY }), processInfoMap);
                    return false;
                }
                if (wmsDtl.get(COL_OTBD_ORD_LINE_NUM) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_OTBD_ORD_LINE_NUM }), processInfoMap);
                    return false;
                }
                if (WMS_TASK.RCVD.equals(wmsTask) && wmsDtl.get(COL_INVTY_STK_STS_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_INVTY_STK_STS_CD }), processInfoMap);
                    return false;
                }
                if (WMS_TASK.RCVD.equals(wmsTask) && wmsDtl.get(COL_WMS_UOM_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_UOM_CD }), processInfoMap);
                    return false;
                }
                if (WMS_TASK.SHIP.equals(wmsTask) && wmsDtl.get(COL_FROM_STK_STS_CD) == null) {
                    outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_FROM_STK_STS_CD }), processInfoMap);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * createPodInterface
     * @param execParam
     */
    private void createPodInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(COL_TRANSACTION_ID, trxId);
            bindParam.put(COL_WMS_TASK_CD_ARY, new String[] {VAL_WMS_TASK_CD_126 });
            bindParam.put(COL_WMS_TASK_CD, VAL_WMS_TASK_CD_101);
            bindParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(COL_WMS_RSN_CD, VAL_WMS_RSN_CD);
            bindParam.put("OTBD_ORD_LINE_NUM_ZERO", LINE_NUM_ZERO);

            stmt = ssmLLClient.createPreparedStatement("getWmsPodData", bindParam, execParam);
            rs = stmt.executeQuery();

            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                try {
                    processInfoMap = new HashMap<String, Object>();

                    // Get Task Name
                    String wmsTaskNm = rs.getString(COL_WMS_TASK_NM);
                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal(COL_UNIT_ID);
                    // Get Current SegmentId
                    BigDecimal segmentId = rs.getBigDecimal(COL_SEGMENT_ID);

                    // Get WMS Upload [POD] Header
                    Map<String, Object> wmsPodData = new HashMap<String, Object>();

                    wmsPodData.put(COL_TRANSACTION_ID, rs.getString(COL_TRANSACTION_ID));
                    wmsPodData.put(COL_BOL_NUM, rs.getString(COL_BOL_NUM));
                    wmsPodData.put(COL_WMS_TASK_CD, rs.getString(COL_WMS_TASK_CD));
                    wmsPodData.put(COL_WMS_CARR_CD, rs.getString(COL_WMS_CARR_CD));
                    wmsPodData.put(COL_WMS_SHIP_DT_TM_TS, rs.getString(COL_WMS_SHIP_DT_TM_TS));
                    wmsPodData.put(COL_OTBD_ORD_NUM, rs.getString(COL_OTBD_ORD_NUM));
                    wmsPodData.put(COL_WMS_TRX_DT_TM_TS, rs.getString(COL_WMS_TRX_DT_TM_TS));
                    wmsPodData.put(COL_OTBD_ORD_LINE_NUM, rs.getString(COL_OTBD_ORD_LINE_NUM));
                    wmsPodData.put(COL_INBD_OTBD_CD, rs.getString(COL_INBD_OTBD_CD));
                    wmsPodData.put(COL_WMS_RSN_CD, rs.getString(COL_WMS_RSN_CD));
                    wmsPodData.put(COL_WMS_TASK_NM, rs.getString(COL_WMS_TASK_NM));
                    wmsPodData.put(COL_SEGMENT_ID, rs.getString(COL_SEGMENT_ID));

                    processInfoMap.put(COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(COL_UNIT_ID, unitId);
                    processInfoMap.put(COL_SEGMENT_ID, segmentId);
                    processInfoMap.put(COL_OTBD_ORD_NUM, wmsPodData.get(COL_OTBD_ORD_NUM));
                    processInfoMap.put(COL_OTBD_ORD_LINE_NUM, wmsPodData.get(COL_OTBD_ORD_LINE_NUM));
                    processInfoMap.put(COL_WMS_RSN_CD, wmsPodData.get(COL_OTBD_ORD_LINE_NUM));
                    processInfoMap.put(COL_WMS_TASK_NM, wmsTaskNm);

                    // Validation check
                    if (!validatePodIFData(wmsPodData, processNm, processInfoMap)) {
                        continue;
                    }

                    if (!callNLZC405001(wmsPodData)) {
                        this.errorCount++;
                        continue;
                    }

                    ++successCount;

                } catch (EZDAbendException e) {
                    e.printStackTrace();
                    outputErr(processNm, e.getCause().getLocalizedMessage(), processInfoMap);
                    errorCount++;
                }
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private boolean callNLZC405001(Map<String, Object> wmsPodData) {
        NLZC405001PMsg nlzc405001PMsg = new NLZC405001PMsg();

        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.slsDt, this.salesDate);
        String inbdOtbdCd = (String) wmsPodData.get(COL_INBD_OTBD_CD);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.inbdOtbdCd, inbdOtbdCd);
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxHdrNum, (String) wmsPodData.get(COL_OTBD_ORD_NUM));

        if ((String) wmsPodData.get(COL_OTBD_ORD_LINE_NUM) != null) {
            String trxLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) wmsPodData.get(COL_OTBD_ORD_LINE_NUM)), VAL_3, "0");
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxLineNum, trxLineNum);
        }
        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum, (String) wmsPodData.get(COL_WMS_RSN_CD));

        if (INBD_OTBD.OUTBOUND.equals(inbdOtbdCd) && VAL_WMS_TASK_CD_101.equals(wmsPodData.get(COL_WMS_TASK_CD))) {
            // "Picked Up at WH"
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, "04");
        } else if (INBD_OTBD.OUTBOUND.equals(inbdOtbdCd)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.DELIVERED);
        } else if (INBD_OTBD.INBOUND.equals(inbdOtbdCd) && VAL_WMS_TASK_CD_126.equals(wmsPodData.get(COL_WMS_TASK_CD))) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.DELIVERED);
        } else if (INBD_OTBD.INBOUND.equals(inbdOtbdCd)) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.PICKED_UP);
        }

        if (wmsPodData.get(COL_WMS_SHIP_DT_TM_TS) != null) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.xxRqstDt, wmsPodData.get(COL_WMS_SHIP_DT_TM_TS).toString().substring(0, LENGTH_DT));
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm, wmsPodData.get(COL_WMS_SHIP_DT_TM_TS).toString().substring(LENGTH_TM_FROM, LENGTH_TM_TO));
        }

        ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP.AGW);
        if (VAL_WMS_TASK_CD_101.equals(wmsPodData.get(COL_WMS_TASK_CD))) {
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.ediStsCd, POD_STS.COMPLETED_LOADING_AT_PICK_UP_LOCATION);
        }

        NLZC405001 nlzc405001 = new NLZC405001();
        nlzc405001.execute(nlzc405001PMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc405001PMsg);

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                    outputApiCallErr(msgId, null, wmsPodData);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * createPodWrkInterface
     * @param execParam
     * @param trxId
     */
    private void createPodWrkInterface(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        BigDecimal ediGsCtrlCd = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");

        int ediStCtrlCd = 0;
        int ediLnCtrlCd = 0;
        String lastBolNum = null;

        try {

            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
            bindParam.put(COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(COL_TRANSACTION_ID, trxId);
            bindParam.put(COL_WMS_TASK_CD, VAL_WMS_TASK_CD_100);
            bindParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
            bindParam.put(COL_WMS_RSN_CD, VAL_WMS_RSN_CD);
            bindParam.put("OTBD_ORD_LINE_NUM_ZERO", LINE_NUM_ZERO);

            stmt = ssmLLClient.createPreparedStatement("getWmsPodWrkData", bindParam, execParam);
            rs = stmt.executeQuery();

            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            // Loop n UNIT_ID unit
            while (rs.next()) {
                try {
                    processInfoMap = new HashMap<String, Object>();

                    // Get WMS Upload [POD] Header
                    Map<String, Object> wmsPodData = new HashMap<String, Object>();

                    wmsPodData.put(COL_TRANSACTION_ID, rs.getString(COL_TRANSACTION_ID));
                    wmsPodData.put(COL_WMS_RSN_CD, rs.getString(COL_WMS_RSN_CD));
                    wmsPodData.put(COL_WMS_TASK_CD, rs.getString(COL_WMS_TASK_CD));
                    wmsPodData.put(COL_WMS_CARR_CD, rs.getString(COL_WMS_CARR_CD));
                    wmsPodData.put(COL_WMS_SHIP_DT_TM_TS, rs.getString(COL_WMS_SHIP_DT_TM_TS));
                    wmsPodData.put(COL_WMS_SUM_PROC_QTY, rs.getBigDecimal(COL_WMS_SUM_PROC_QTY));

                    processInfoMap.put(COL_TRANSACTION_ID, trxId);
                    processInfoMap.put(COL_UNIT_ID, rs.getBigDecimal(COL_UNIT_ID));
                    processInfoMap.put(COL_SEGMENT_ID, rs.getBigDecimal(COL_SEGMENT_ID));
                    processInfoMap.put(COL_WMS_TASK_NM, rs.getString(COL_WMS_TASK_NM));
                    // Get current BOL_NUM
                    String bolNum = rs.getString(COL_WMS_RSN_CD);
                    processInfoMap.put(COL_WMS_RSN_CD, bolNum);

                    // Validation check
                    validatePodIFData(wmsPodData, processNm, processInfoMap);

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
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * validatePodIFData
     * @param wmsPodData
     * @return
     */
    private boolean validatePodIFData(Map<String, Object> wmsPodData, String processNm, Map<String, Object> processInfoMap) {

        if (wmsPodData.get(COL_WMS_RSN_CD) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_RSN_CD }), processInfoMap);
            return false;
        }
        if (wmsPodData.get(COL_WMS_SHIP_DT_TM_TS) == null) {
            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0028E, new String[] {COL_WMS_SHIP_DT_TM_TS }), processInfoMap);
            return false;
        }

        return true;
    }

    private void createPodData(Map<String, Object> wmsPodData
                                , BigDecimal ediGsCtrlCd
                                , int ediStCtrlCd
                                , int ediLnCtrlCd
                                , String processNm
                                , Map<String, Object> processInfoMap) {

        // Register POD_ADDR_WRK
        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, (String) wmsPodData.get(COL_TRANSACTION_ID));

        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toPlainString());

        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, (String) wmsPodData.get(COL_WMS_CARR_CD));

        // For each batch processing of once, SQL ID numbering
        String ediGsCtrlCdWk = ediGsCtrlCd.toPlainString();
        if (ediGsCtrlCdWk.length() > LENGTH_EDI_CTRL_CD_WK) {
            ediGsCtrlCdWk = ediGsCtrlCdWk.substring(ediGsCtrlCdWk.length() - LENGTH_EDI_CTRL_CD_WK);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, ediGsCtrlCdWk);

        String ediStCtrlCdWk = ediGsCtrlCdWk + ZYPCommonFunc.leftPad(String.valueOf(ediStCtrlCd), LENGTH_EDI_CTRL_CD, VAL_ZERO);
        if (ediStCtrlCdWk.length() > LENGTH_EDI_CTRL_CD_WK) {
            ediStCtrlCdWk = ediStCtrlCdWk.substring(ediStCtrlCdWk.length() - LENGTH_EDI_CTRL_CD_WK);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, ediStCtrlCdWk);

        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, (String) wmsPodData.get(COL_WMS_CARR_CD));
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, POD_SRC_TP.AGW);

        // insert POD_ADDR_WRK
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {
            S21InfoLogOutput.println(podAddrWrk.getTableName() + " ReturnCode : " + podAddrWrk.getReturnCode());

            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {POD_ADDR_WRK, NLBI1520_02
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID)
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) })
                    , processInfoMap);

            throw new S21AbendException(NLGM0045E, new String[] {POD_ADDR_WRK, NLBI1520_02
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID)
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
        }

        // Register POD_STS_WRK
        POD_STS_WRKTMsg podStsWrk = new POD_STS_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(podStsWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTrxId, (String) wmsPodData.get(COL_TRANSACTION_ID));

        BigDecimal ediSqIdSts = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_STS_SQ);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediSqId, ediSqIdSts.toPlainString());

        ZYPEZDItemValueSetter.setValue(podStsWrk.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, (String) wmsPodData.get(COL_WMS_CARR_CD));
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, ediGsCtrlCdWk);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, ediStCtrlCdWk);

        String strEdiLnCtrlCd = ZYPCommonFunc.leftPad(String.valueOf(ediLnCtrlCd), LENGTH_EDI_CTRL_CD, VAL_ZERO);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, strEdiLnCtrlCd);

        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, (String) wmsPodData.get(COL_WMS_RSN_CD));

        String wmsTaskCd = (String) wmsPodData.get(COL_WMS_TASK_CD);
        if (ZYPCommonFunc.hasValue(wmsTaskCd)) {
            String strWmsTaskCd = null;
            if (VAL_WMS_TASK_CD_126.equals(wmsTaskCd)) {
                strWmsTaskCd = POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION;
            } else if (VAL_WMS_TASK_CD_100.equals(wmsTaskCd)) {
                strWmsTaskCd = POD_STS.OTHER;
            } else if (VAL_WMS_TASK_CD_101.equals(wmsTaskCd)) {
                strWmsTaskCd = POD_STS.COMPLETED_LOADING_AT_PICK_UP_LOCATION;
            }
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, strWmsTaskCd);
        }

        String wmsShipDtTmTs = (String) wmsPodData.get(COL_WMS_SHIP_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(wmsShipDtTmTs)) {
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, wmsShipDtTmTs.substring(0, LENGTH_DT));
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, wmsShipDtTmTs.substring(LENGTH_TM_FROM, LENGTH_TM_TO));
        }

        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, VAL_CONST_EDI_TM_CD);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLoadQty, (BigDecimal) wmsPodData.get(COL_WMS_SUM_PROC_QTY));

        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {
            S21InfoLogOutput.println(podStsWrk.getTableName() + " ReturnCode : " + podStsWrk.getReturnCode());

            outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {POD_STS_WRK, NLBI1520_02
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID)
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) })
                    , processInfoMap);

            throw new S21AbendException(NLGM0045E, new String[] {POD_STS_WRK, NLBI1520_02
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_EDI_TRX_ID)
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(COL_TRANSACTION_ID)) });
        }
    }

    /**
     * checkDetailData
     * @param execParam
     * @param trxId
     * @param processNm
     */
    private void checkDetailData(S21SsmExecutionParameter execParam, BigDecimal trxId, String processNm) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(COL_TRANSACTION_ID, trxId);

            stmt = ssmLLClient.createPreparedStatement("getNotExistDetail", bindParam, execParam);
            rs = stmt.executeQuery();
            Map<String, Object> processInfoMap = new HashMap<String, Object>();

            while (rs.next()) {

                processInfoMap.put(COL_TRANSACTION_ID, rs.getBigDecimal(COL_TRANSACTION_ID));
                processInfoMap.put(COL_UNIT_ID, rs.getBigDecimal(COL_UNIT_ID));
                processInfoMap.put(COL_SEGMENT_ID, rs.getBigDecimal(COL_SEGMENT_ID));
                processInfoMap.put(COL_OTBD_ORD_NUM, rs.getString(COL_OTBD_ORD_NUM));
                processInfoMap.put(COL_WMS_RSN_CD, rs.getString(COL_WMS_RSN_CD));

                outputErr(processNm, S21MessageFunc.clspGetMessage(NLGM0090E, new String[] {rs.getString(COL_OTBD_ORD_NUM) }), processInfoMap);
                errorCount++;
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void outputErr(String processNm, String errMsg, Map<String, Object> processInfoMap) {
        StringBuilder builder = new StringBuilder();
        builder.append(generateErrorInfo(processNm, errMsg, processInfoMap));

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, "NLBM1257E");
        mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(builder.toString());
    }

    private String generateErrorInfo(String operationNm, String errMsg, Map<String, Object> processInfoMap) {
        StringBuilder builder = new StringBuilder();
        builder.append(errMsg);
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Operation         : ");
        builder.append(operationNm);
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append(this.interfaceId);
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        if (ZYPCommonFunc.hasValue((BigDecimal) processInfoMap.get(COL_TRANSACTION_ID))) {
            builder.append(((BigDecimal) processInfoMap.get(COL_TRANSACTION_ID)).toPlainString());
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue((BigDecimal) processInfoMap.get(COL_SEGMENT_ID))) {
            builder.append(((BigDecimal) processInfoMap.get(COL_SEGMENT_ID)).toPlainString());
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Tracking#         : ");
        if (ZYPCommonFunc.hasValue((String) processInfoMap.get(COL_WMS_RSN_CD))) {
            builder.append((String) processInfoMap.get(COL_WMS_RSN_CD));
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Order#            : ");
        if (ZYPCommonFunc.hasValue((String) processInfoMap.get(COL_OTBD_ORD_NUM))) {
            builder.append((String) processInfoMap.get(COL_OTBD_ORD_NUM));
        } else {
            builder.append(VAL_BLANK);
        }
        if (processInfoMap.containsKey(COL_OTBD_ORD_LINE_NUM)) {
            builder.append(LINE_SEPT);
            builder.append(VAL_BLANK_14);
            builder.append("Line#             : ");
            if (ZYPCommonFunc.hasValue((String) processInfoMap.get(COL_OTBD_ORD_LINE_NUM))) {
                String otbdLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) processInfoMap.get(COL_OTBD_ORD_LINE_NUM)), VAL_3, "0");
                builder.append(otbdLineNum);
            } else {
                builder.append(VAL_BLANK);
            }
        }
        if (processInfoMap.containsKey(COL_WMS_TASK_NM)) {
            builder.append(LINE_SEPT);
            builder.append(VAL_BLANK_14);
            builder.append("Process Name      : ");
            if (ZYPCommonFunc.hasValue((String) processInfoMap.get(COL_WMS_TASK_NM))) {
                builder.append((String) processInfoMap.get(COL_WMS_TASK_NM));
            } else {
                builder.append(VAL_BLANK);
            }
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_SEP_LINE);
        return builder.toString();
    }

    private void outputApiCallErr(String msgId, String[] msgParam, Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        builder.append(generateWmsPodKeyInfo(wmsPodData, msgId, msgParam));

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, "NLBM1257E");
        mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    private String generateWmsPodKeyInfo(Map<String, Object> wmsPodData, String msgId, String[] msgParam) {
        StringBuilder builder = new StringBuilder();
        builder.append("POD Process Error : ");
        builder.append(S21MessageFunc.clspGetMessage(msgId, msgParam));
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append("");
        builder.append(this.interfaceId);
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        builder.append("");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(COL_TRANSACTION_ID))) {
            builder.append((String) wmsPodData.get(COL_TRANSACTION_ID));
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(COL_SEGMENT_ID))) {
            builder.append((String) wmsPodData.get(COL_SEGMENT_ID));
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Order#            : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(COL_OTBD_ORD_NUM))) {
            builder.append((String) wmsPodData.get(COL_OTBD_ORD_NUM));
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Line#             : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(COL_OTBD_ORD_LINE_NUM))) {
            String otbdLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) wmsPodData.get(COL_OTBD_ORD_LINE_NUM)), VAL_3, "0");
            builder.append(otbdLineNum);
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Process Name      : ");
        if (ZYPCommonFunc.hasValue((String) wmsPodData.get(COL_WMS_TASK_NM))) {
            builder.append((String) wmsPodData.get(COL_WMS_TASK_NM));
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_SEP_LINE);
        return builder.toString();
    }
}
