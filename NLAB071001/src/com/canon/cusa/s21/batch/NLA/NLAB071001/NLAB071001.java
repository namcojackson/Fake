package com.canon.cusa.s21.batch.NLA.NLAB071001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.WMS_INBD_TRXTMsg;

import com.canon.cusa.s21.batch.NLA.NLAB071001.constant.NLAB071001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
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
 * Batch Program Class for Receiving Advice (944) from Apex.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/02/2016   CITS            T.Wada          Create
 * 02/22/2017   CITS            T.Kikuhara      Update          QC#17545
 * 07/10/2017   CITS            T.Tokutomi      UPDATE          QC#19815
 * 09/20/2017   CITS            T.Wada          UPDATE          QC#21283
 * 10/30/2017   CITS            T.Wada          UPDATE          QC#21281
 * 12/04/2017   CITS            T.Wada          UPDATE          QC#22894
 * 12/11/2017   CITS            T.Wada          UPDATE          QC#22934
 *</pre>
 */
public class NLAB071001 extends S21BatchMain {

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

    /** Const Value WMS_PACK_CD_SET_OWNER_CD_FLG*/
    private String wmsPackCdSetOwnerCdFlg = "";
    
    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    // QC#21281
    /** WMS_ORG_HOST_ID */
    private String wmsOrgHostId = null;

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {

        new NLAB071001().executeBatch(NLAB071001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLAB071001Constant.NLGM0049E, new String[] {NLAB071001Constant.PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLAB071001Constant.NLGM0049E, new String[] {NLAB071001Constant.PRAM_NM_INTERFACE_ID });
        }

        wmsPackCdSetOwnerCdFlg = ZYPCodeDataUtil.getVarCharConstValue("WMS_PACK_CD_SET_OWNER_CD_FLG", glblCmpyCd);

        // QC#21281
        wmsOrgHostId = ZYPCodeDataUtil.getVarCharConstValue("WMS_ORG_HOST_ID_3PL", getGlobalCompanyCode());
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

        try {
            // SO
            createRwsInterface(execParam);
            commit();

        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLAB071001Constant.BUSINESS_ID, errMsgList);
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
        setTermState(termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * WMS Upload Put Away Data Creation Process
     */
    private void createRwsInterface(S21SsmExecutionParameter execParam) {
        // if(true){
        // return;
        // }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Get Target TRANSACTION_ID
        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);
        try {
            // Loop in Target TRANSACTION_ID unit
            for (BigDecimal tranId : tranIds) {

                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(NLAB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
                queryParam.put(NLAB071001Constant.COL_INTERFACE_ID, this.interfaceId);
                queryParam.put(NLAB071001Constant.COL_TRANSACTION_ID, tranId);
                queryParam.put(NLAB071001Constant.COL_INBD_OTBD_CD, NLAB071001Constant.VAL_INBD_OTBD_CD_IS_INBD);
                queryParam.put(NLAB071001Constant.COL_WH_OWNR_CD, NLAB071001Constant.VAL_WH_OWNR_CD_IS_APX);
                queryParam.put(NLAB071001Constant.COL_WMS_TASK_CD, NLAB071001Constant.VAL_WMS_TASK_CD_RCVD);
                
                // Get WMS Upload [Header] Detail
                stmt = ssmLLClient.createPreparedStatement("getWmsRwsHeader", queryParam, execParam);
                rs = stmt.executeQuery();

                // Loop n UNIT_ID unit
                while (rs.next()) {

                    // Get Current UnitId
                    BigDecimal unitId = rs.getBigDecimal("UNIT_ID");

                    // QC#22934
                    // Get Current UnitId
                    BigDecimal segmentId = rs.getBigDecimal("SEGMENT_ID");
                    
                    // Get WMS Upload [SO] Header
                    Map<String, Object> wmsRwsHdr = new HashMap<String, Object>();
                    wmsRwsHdr.put(NLAB071001Constant.COL_INTERFACE_ID, rs.getString(NLAB071001Constant.COL_INTERFACE_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_TRANSACTION_ID, rs.getString(NLAB071001Constant.COL_TRANSACTION_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_SEGMENT_ID, rs.getString(NLAB071001Constant.COL_SEGMENT_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_UNIT_ID, rs.getString(NLAB071001Constant.COL_UNIT_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_SEQ_NUMBER, rs.getString(NLAB071001Constant.COL_SEQ_NUMBER));
                    wmsRwsHdr.put(NLAB071001Constant.COL_WMS_REC_ID, rs.getString(NLAB071001Constant.COL_WMS_REC_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_BOL_NUM, rs.getString(NLAB071001Constant.COL_BOL_NUM));
                    wmsRwsHdr.put(NLAB071001Constant.COL_WMS_TASK_CD, rs.getString(NLAB071001Constant.COL_WMS_TASK_CD));
                    wmsRwsHdr.put(NLAB071001Constant.COL_WMS_TASK_NM, rs.getString(NLAB071001Constant.COL_WMS_TASK_NM));
                    wmsRwsHdr.put(NLAB071001Constant.COL_OTBD_ORD_NUM, rs.getString(NLAB071001Constant.COL_OTBD_ORD_NUM));
                    wmsRwsHdr.put(NLAB071001Constant.COL_SHPG_PNT_CD, rs.getString(NLAB071001Constant.COL_SHPG_PNT_CD));
                    wmsRwsHdr.put(NLAB071001Constant.COL_RCV_PNT_CD, rs.getString(NLAB071001Constant.COL_RCV_PNT_CD));
                    wmsRwsHdr.put(NLAB071001Constant.COL_WMS_TRX_DT_TM_TS, rs.getString(NLAB071001Constant.COL_WMS_TRX_DT_TM_TS));
                    wmsRwsHdr.put(NLAB071001Constant.COL_EZINTIME, rs.getString(NLAB071001Constant.COL_EZINTIME));
                    wmsRwsHdr.put(NLAB071001Constant.COL_WH_CD, rs.getString(NLAB071001Constant.COL_WH_CD));
                    wmsRwsHdr.put(NLAB071001Constant.COL_RWS_NUM, rs.getString(NLAB071001Constant.COL_RWS_NUM));
                    wmsRwsHdr.put(NLAB071001Constant.COL_INVTY_OWNR_CD,rs.getString(NLAB071001Constant.COL_INVTY_OWNR_CD));
                    // QC#21281
                    //wmsRwsHdr.put(NLAB071001Constant.COL_WMS_ORG_HOST_ID,rs.getString(NLAB071001Constant.COL_WMS_ORG_HOST_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_S80_ORD_TP_CD, rs.getString(NLAB071001Constant.COL_S80_ORD_TP_CD));
                    wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_TP_ID,rs.getString(NLAB071001Constant.COL_INTFC_TP_ID));
                    wmsRwsHdr.put(NLAB071001Constant.COL_DATE_TIME, rs.getString(NLAB071001Constant.COL_DATE_TIME));
                    wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_PROC_STS_CD, NLAB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                    wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, "");
                    // QC#21283
                    wmsRwsHdr.put(NLAB071001Constant.COL_RWS_REF_NUM, rs.getString(NLAB071001Constant.COL_RWS_REF_NUM));

                    // Get WMS Upload [RWS] Detail
                    List<Map<String, Object>> wmsRwsDtlList = getWmsRwsDetail(execParam, tranId, unitId, segmentId);
                    
                    // ErrFlug
                    boolean flgError = false;
                    
                    for (Map<String, Object> wmsRwsDtl : wmsRwsDtlList) {
                        // Validation check
                        wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, "");
                        if (!validateRwsIFData(wmsRwsHdr, wmsRwsDtl, tranId, unitId)) {
                            flgError = true;
                            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_PROC_STS_CD, NLAB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                        }
                        else {
                            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_PROC_STS_CD, NLAB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                        }
                        // Create I/F Data
                        createRwsConfDtl(wmsRwsHdr, wmsRwsDtl , tranId, unitId);
                    }
                    if (flgError) {
                        ++errorCount;
                    }
                    else {
                        ++successCount;
                    }
                }
                trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
                commit();
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        } catch (SQLException e) {
            EZDDebugOutput.println(NLAB071001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
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
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put(NLAB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        queryParam.put(NLAB071001Constant.COL_TRANSACTION_ID, tranId);
        queryParam.put(NLAB071001Constant.COL_SEGMENT_ID, segmentId);
        queryParam.put(NLAB071001Constant.COL_UNIT_ID, unitId);
        List<Map<String, Object>> wmsRwsDtlList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsRwsDetail", queryParam, execParam);
        return wmsRwsDtlList;
    }

    /**
     * validateRwsIFData
     * @param wmsRwsHdr
     * @param wmsRwsDtl
     * @param tranId
     * @param unitId
     * @param interfaceErrMsgCD
     * @return
     */
    private boolean validateRwsIFData(Map<String, Object> wmsRwsHdr, Map<String, Object> wmsRwsDtl, BigDecimal tranId, BigDecimal unitId) {

        String errorLocation = interfaceId + "," + tranId.toPlainString() + "," + unitId.toPlainString();
        
        // Header Check
        if (wmsRwsHdr.get(NLAB071001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_OTBD_ORD_NUM, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLAB071001Constant.COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_WMS_TRX_DT_TM_TS, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLAB071001Constant.COL_EZINTIME) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_EZINTIME, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLAB071001Constant.COL_WH_CD) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_WH_CD, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        // add start
        if (wmsRwsHdr.get(NLAB071001Constant.COL_UNIT_ID) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_UNIT_ID, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLAB071001Constant.COL_INVTY_OWNR_CD) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_INVTY_OWNR_CD, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLAB071001Constant.COL_WMS_REC_ID) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_WMS_REC_ID, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLAB071001Constant.COL_INTFC_TP_ID) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_INTFC_TP_ID, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        //QC#19815  Delete mandatory check WMS_ORG_HOST_ID.

        // add end
        if (wmsRwsHdr.get(NLAB071001Constant.COL_S80_ORD_TP_CD) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_S80_ORD_TP_CD, NLAB071001Constant.TBL_NLAI2230_01, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }

        // Body Check
        if (wmsRwsDtl.get(NLAB071001Constant.COL_WMS_MDSE_CD) == null) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_WMS_MDSE_CD, NLAB071001Constant.TBL_NLAI2230_02, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        BigDecimal wmsProcQty = (BigDecimal) wmsRwsDtl.get(NLAB071001Constant.COL_WMS_PROC_QTY);
        if (wmsProcQty == null || BigDecimal.ZERO.compareTo(wmsProcQty) >= 0) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_WMS_PROC_QTY, NLAB071001Constant.TBL_NLAI2230_02, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            wmsRwsDtl.put(NLAB071001Constant.COL_WMS_PROC_QTY, BigDecimal.ZERO);
            return false;
        }
        BigDecimal otbdOrdLineNum = (BigDecimal) wmsRwsDtl.get(NLAB071001Constant.COL_OTBD_ORD_LINE_NUM);
        if (otbdOrdLineNum == null || BigDecimal.ZERO.compareTo(otbdOrdLineNum) >= 0) {
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_OTBD_ORD_LINE_NUM, NLAB071001Constant.TBL_NLAI2230_02, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsDtl.get(NLAB071001Constant.COL_RTL_SWH_CD) == null) {
            // err
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_RTL_SWH_CD, NLAB071001Constant.TBL_NLAI2230_02, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        // add
        if (wmsRwsDtl.get(NLAB071001Constant.COL_INVTY_STK_STS_CD) == null) {
            // err
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_INVTY_STK_STS_CD, NLAB071001Constant.TBL_NLAI2230_02, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsDtl.get(NLAB071001Constant.COL_WMS_UOM_CD) == null) {
            // err
            outputErr(NLAB071001Constant.NLGM0041E, new String[] {NLAB071001Constant.COL_WMS_UOM_CD, NLAB071001Constant.TBL_NLAI2230_02, errorLocation });
            wmsRwsHdr.put(NLAB071001Constant.COL_INTFC_ERR_MSG_CD, NLAB071001Constant.NLGM0041E);
            return false;
        }
            // add end

        return true;
    }

    /**
     * createSOConfDtl
     * @param wmsRwsHdr
     * @param wmsRwsDtlList
     * @param regSeqNum
     * @return
     */
    private void createRwsConfDtl(Map<String, Object> wmsRwsHdr, Map<String, Object> wmsRwsDtl, BigDecimal trnId, BigDecimal unitId) {

        WMS_INBD_TRXTMsg wmsInbdTrxtTmsg = new WMS_INBD_TRXTMsg();

        boolean flgAddNoSerialRecord = false;
        BigDecimal numNoSerialQty = BigDecimal.ZERO;
        // 1-10
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.glblCmpyCd , this.glblCmpyCd);
        // QC#22894
//        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_INVTY_SQ));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRecId , (String) wmsRwsHdr.get(NLAB071001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.whCd , (String) wmsRwsHdr.get(NLAB071001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTaskCd , NLAB071001Constant.VAL_WMS_TASK_CD_RCVD);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcId , (String) wmsRwsHdr.get(NLAB071001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcRcvTs , (String) wmsRwsHdr.get(NLAB071001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxId, ZYPCommonFunc.leftPad(this.objNullToString(wmsRwsHdr.get(NLAB071001Constant.COL_TRANSACTION_ID))
                                                                                       , NLAB071001Constant.LG_INTFC_TRX_ID
                                                                                       , NLAB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxSqNum, ZYPCommonFunc.leftPad(this.objNullToString(wmsRwsHdr.get(NLAB071001Constant.COL_UNIT_ID))
                                                                                       , NLAB071001Constant.LG_UNIT_ID
                                                                                       , NLAB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wrkTrxId, ZYPCommonFunc.leftPad("1"
                                                                                       , NLAB071001Constant.LG_WRK_TRX_ID
                                                                                       , NLAB071001Constant.VAL_ZERO));
        
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcProcStsCd , (String) wmsRwsHdr.get(NLAB071001Constant.COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcErrMsgCd , (String) wmsRwsHdr.get(NLAB071001Constant.COL_INTFC_ERR_MSG_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.procStsCd , NLAB071001Constant.VAL_PROC_STS_CD_UNTREATED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.errMsgCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsStsCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRsnCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsMdseCd, (String) wmsRwsDtl.get(NLAB071001Constant.COL_WMS_MDSE_CD));
        if ("Y".equals(wmsPackCdSetOwnerCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd,  (String) ZYPCommonFunc.concatString((String) wmsRwsHdr.get(NLAB071001Constant.COL_INVTY_OWNR_CD)
                                                                                                            ,""
                                                                                                            ,(String) wmsRwsDtl.get(NLAB071001Constant.COL_RTL_SWH_CD)));
        }
        else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd, (String) wmsRwsDtl.get("INVTY_LOC_CD"));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTagId, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsStkStsCd, (String) wmsRwsDtl.get(NLAB071001Constant.COL_INVTY_STK_STS_CD));
        
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOldStkStsCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsDmgFlg , "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOldDmgFlg , "N");
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgQty , null);
        
        BigDecimal numWmsProcQty = (BigDecimal)(wmsRwsDtl.get(NLAB071001Constant.COL_WMS_PROC_QTY));
        BigDecimal numSerialCount = (BigDecimal)(wmsRwsDtl.get(NLAB071001Constant.COL_SER_CHK_CNT));
        BigDecimal numSerialRowNo = (BigDecimal)(wmsRwsDtl.get(NLAB071001Constant.COL_SER_CHK_ROWNUM));
        if (wmsRwsDtl.get(NLAB071001Constant.COL_SER_NUM) == null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdQty , numWmsProcQty);
        }
        else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdQty , BigDecimal.ONE);
            if (numSerialCount.compareTo(numSerialRowNo) == 0 && numWmsProcQty.compareTo(numSerialCount) > 0) {
                // Serial Last Record : wmsOrdQty = numWmsProcQty - numSerialCount + 1
                flgAddNoSerialRecord = true;
                numNoSerialQty = numWmsProcQty.subtract(numSerialCount).add(BigDecimal.ONE);
            }
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.usrId_03, (String) wmsRwsDtl.get(NLAB071001Constant.COL_WMS_UOM_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRcptDtTmTs, (String) wmsRwsHdr.get(NLAB071001Constant.COL_WMS_TRX_DT_TM_TS));
        // QC#21283
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdNum, (String) wmsRwsHdr.get(NLAB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdNum, (String) wmsRwsHdr.get(NLAB071001Constant.COL_RWS_REF_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdTpCd, (String) wmsRwsHdr.get(NLAB071001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdLineNum, (BigDecimal)(wmsRwsDtl.get(NLAB071001Constant.COL_OTBD_ORD_LINE_NUM)));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.ordLineCpltFlg, "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.packMarkSeptFlg, "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.serNum, (String) wmsRwsDtl.get(NLAB071001Constant.COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsBatId, (String) wmsRwsHdr.get(NLAB071001Constant.COL_DATE_TIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTpId, (String) wmsRwsHdr.get(NLAB071001Constant.COL_INTFC_TP_ID));
        // QC#21281
//        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, (String) wmsRwsHdr.get(NLAB071001Constant.COL_WMS_ORG_HOST_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsUpdHistNum, BigDecimal.ZERO);

        // Edit WMS_SO_CONF_DATA_IF

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxtTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxtTmsg.getReturnCode())) {
            throwInsertError(trnId , unitId);
        }
        if (flgAddNoSerialRecord) {
            // wmsOrdQty = numWmsProcQty - numSerialCount
        	// QC#22894
//            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_INVTY_SQ));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ));

            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdQty , numWmsProcQty.subtract(numSerialCount));
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.serNum, "");
            // insert
            EZDTBLAccessor.insert(wmsInbdTrxtTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxtTmsg.getReturnCode())) {
                throwInsertError(trnId , unitId);
            }
            
        }

    }

    private void throwInsertError(BigDecimal trnID , BigDecimal unitID) {
        outputErr(NLAB071001Constant.NLGM0045E, new String[] {NLAB071001Constant.TARGET_ID, NLAB071001Constant.TBL_NLAI2230_01,
                NLXCMsgHelper.toListedString(NLAB071001Constant.COL_INTERFACE_ID, NLAB071001Constant.COL_TRANSACTION_ID, NLAB071001Constant.COL_UNIT_ID) //
                , NLXCMsgHelper.toListedString(NLAB071001Constant.TARGET_ID, trnID, unitID) });

        throw new S21AbendException(NLAB071001Constant.NLGM0045E, new String[] {NLAB071001Constant.TARGET_ID, NLAB071001Constant.TBL_NLAI2230_01,
                NLXCMsgHelper.toListedString(NLAB071001Constant.COL_INTERFACE_ID, NLAB071001Constant.COL_TRANSACTION_ID, NLAB071001Constant.COL_UNIT_ID) //
                , NLXCMsgHelper.toListedString(NLAB071001Constant.TARGET_ID, trnID, unitID) });
    }

    /**
     * objNullToString
     * @param obj
     * @return
     */
    private String objNullToString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }
}
