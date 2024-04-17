package com.canon.cusa.s21.batch.NLB.NLBB071001;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.POD_ADDR_WRKTMsg;
import business.db.POD_STS_WRKTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.parts.NLZC405001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC405001.NLZC405001;
import com.canon.cusa.s21.batch.NLB.NLBB071001.constant.NLBB071001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
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
 * Batch Program Class for Shipping Advice (EDI 945) from APEX.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS            T.Wada          Create
 * 10/11/2016   CITS            T.Hakodate      UPDATE          QC#15066
 * 07/05/2017   CITS            T.Tokutomi      UPDATE          QC#19755
 * 07/07/2017   CITS            T.Tokutomi      UPDATE          QC#19815
 * 10/30/2017   CITS            T.Wada          UPDATE          QC#21281
 * 11/07/2017   CITS            T.Wada          UPDATE          QC#21302
 * 12/04/2017   CITS            T.Wada          UPDATE          QC#22894
 * 12/11/2017   CITS            T.Wada          UPDATE          QC#22934
 * 03/29/2019   Fujitsu         T.Ogura         Update          QC#30594
 * 05/21/2019   CITS            K.Ogino         Update          QC#50389
 * 06/03/2019   CITS            K.Ogino         Update          QC#50591
 * 02/12/2020   Fujitsu         R.Nakamura      Update          QC#55733
 * 06/26/2020   CITS            K.Ogino         Update          QC#56444
 *</pre>
 */
public class NLBB071001 extends S21BatchMain {

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

    /** Const Value WMS_PACK_CD_SET_OWNER_CD_FLG*/
    private String wmsPackCdSetOwnerCdFlg = "";

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

        new NLBB071001().executeBatch(NLBB071001.class.getSimpleName());
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
            throw new S21AbendException(NLBB071001Constant.NLGM0049E, new String[] {NLBB071001Constant.PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB071001Constant.NLGM0049E, new String[] {NLBB071001Constant.PRAM_NM_INTERFACE_ID });
        }

        // WH_OWNER_CD
        whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB071001Constant.VAR_CHAR_CONCT_NM_TG_ORDER, glblCmpyCd);
        
        // WMS_PACK_CD_SET_OWNER_CD_FLG
        wmsPackCdSetOwnerCdFlg = ZYPCodeDataUtil.getVarCharConstValue("WMS_PACK_CD_SET_OWNER_CD_FLG", glblCmpyCd);

        // QC#21281
        wmsOrgHostId = ZYPCodeDataUtil.getVarCharConstValue("WMS_ORG_HOST_ID_3PL", getGlobalCompanyCode());
        // QC#21303 Add
        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // QC#50389
        this.defWmsCarrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB071001Constant.NLBB0710_DEF_WMS_CARR_CD, getGlobalCompanyCode());

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

                // SO
                createSoInterface(execParam, tranId);
                // POD (Proof of Delivery)
                createPodInterface(execParam, tranId);

                // QC#21302
                createRwsInterface(execParam, tranId);
                createPodWrkInterface(execParam, tranId);

                // Update processed flag
                trxAccess.endIntegrationProcess(this.interfaceId, tranId);

                commit();
            }
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB071001Constant.BUSINESS_ID, errMsgList);
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
     * WMS Upload SO Data Creation Process
     */
    private void createSoInterface(S21SsmExecutionParameter execParam, BigDecimal trxId) {

        // Err Manage
        Map<String, Object> errInfo = new HashMap<String, Object>();
        errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
        errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, "");
        // ---------------------------------
        // Header Read
        // ---------------------------------
        List<Map<String, Object>> wmsSoHdrList = getWmsSoHeader(execParam, trxId);
        for (Map<String, Object> wmsSoHdr : wmsSoHdrList) {
            // HeaderErrFlug
            boolean flgSoHasError = false;
            boolean flgErrorHeader = false;
            String errMsgCdHeader = "";
            // Add Start 2020/02/12 QC#55733
            List<Map<String, Object>> serItemMapList = new ArrayList<Map<String,Object>>();
            List<Map<String, Object>> rmvItemMapList = new ArrayList<Map<String,Object>>();
            // Add End 2020/02/12 QC#55733

            // Get Interface Key
            BigDecimal unitId = (BigDecimal) wmsSoHdr.get(NLBB071001Constant.COL_UNIT_ID);
            BigDecimal segmId = (BigDecimal) wmsSoHdr.get(NLBB071001Constant.COL_SEGMENT_ID);
            // QC#56444
            String otbdOrdNum = (String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM);

            // Check Data
            errMsgCdHeader = validateSoIFData_Header(wmsSoHdr, trxId, unitId);
            if (ZYPCommonFunc.hasValue(errMsgCdHeader)) {
                flgSoHasError = true;
                flgErrorHeader = true;
            }
            // ---------------------------------
            // Detail Read
            // ---------------------------------
            List<Map<String, Object>> wmsSoDtlList = getWmsSoDetail(execParam, trxId, unitId, segmId);
            // QC#56444 Start
            List<BigDecimal> soSlpNumList = new ArrayList<BigDecimal>();
            for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {
                String errMsgCdDetail = "";
                errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, "");
                errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                // Check Data(Detail)
                if (flgErrorHeader) {
                    errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                    errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, errMsgCdHeader);
                } else {
                    errMsgCdDetail = validateSoIFData_Detail(wmsSoDtl, trxId, unitId);
                    if (ZYPCommonFunc.hasValue(errMsgCdDetail)) {
                        flgSoHasError = true;
                        errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                        errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, errMsgCdDetail);
                    } else {

                        if (wmsSoDtl.get(NLBB071001Constant.COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
                            String wmsMdseCd = (String) wmsSoDtl.get(NLBB071001Constant.COL_WMS_MDSE_CD);
                            String soMdseCd = (String) wmsSoDtl.get(NLBB071001Constant.COL_MDSE_CD);
                            if (!wmsMdseCd.equals(soMdseCd)) {
                                continue;
                            }
                        }
                        errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                    }
                    BigDecimal soSlpNum = (BigDecimal) wmsSoDtl.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM);
                    soSlpNumList.add(soSlpNum);

                }
                // Create I/F(Detail Data)
                createSoConfDtl(wmsSoHdr, wmsSoDtl, errInfo, trxId, unitId);

                // Add Start 2020/02/12 QC#55733
                Map<String, Object> serItemMap = getSerItemMap(wmsSoHdr, wmsSoDtl);
                if (null != serItemMap) {
                    serItemMapList.add(serItemMap);
                }
                // Add End 2020/02/12 QC#55733
            }

            List<Map<String, Object>> soConfigDtlList = getConfigSoDtl(execParam, soSlpNumList, trxId, unitId, segmId, otbdOrdNum);
            if (soConfigDtlList != null && !soConfigDtlList.isEmpty()) {
                for (Map<String, Object> wmsSoDtl : soConfigDtlList) {
                    createSoConfDtl(wmsSoHdr, wmsSoDtl, errInfo, trxId, unitId);
                }
            }
            // QC#56444 End
            // ------------------------
            // Serial Read
            // ------------------------
            List<Map<String, Object>> wmsSoSerList = getWmsSoSerial(execParam, trxId, unitId, segmId);
            // SerialError
            for (Map<String, Object> wmsSoSer : wmsSoSerList) {
                String errMsgCdDetail = "";
                errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, "");
                errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                // Check Data Serial
                if(flgErrorHeader) {
                    errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                    errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, errMsgCdHeader);
                }
                else {
                    errMsgCdDetail = validateSoIFData_Serial(wmsSoSer, trxId, unitId);
                    if (ZYPCommonFunc.hasValue(errMsgCdDetail)) {
                        flgSoHasError = true;
                        errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                        errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, errMsgCdDetail);
                    }
                    else {
                        // QC#56444
                        if (wmsSoSer.get(NLBB071001Constant.COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
                            String wmsMdseCd = (String) wmsSoSer.get(NLBB071001Constant.COL_WMS_MDSE_CD);
                            String soMdseCd = (String) wmsSoSer.get(NLBB071001Constant.COL_MDSE_CD);
                            if (!wmsMdseCd.equals(soMdseCd)) {
                                continue;
                            }
                        }

                        errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                    }
                }
                // ------------------------
                // Create I/F Data(Serial)
                // ------------------------
                createSoConfSer(wmsSoHdr, wmsSoSer, trxId, unitId, errInfo);

                // Add Start 2020/02/12 QC#55733
                Map<String, Object> rmvItemMap = getRmvItemMap(wmsSoHdr, wmsSoSer, serItemMapList);
                if (null != rmvItemMap) {
                    rmvItemMapList.add(rmvItemMap);
                }
                // Add End 2020/02/12 QC#55733
            }

            // Add Start 2020/02/12 QC#55733
            if (null != rmvItemMapList && rmvItemMapList.size() > 0) {
                serItemMapList.removeAll(rmvItemMapList);
            }

            // ---------------------------
            // Serial Read(Not Interfaced)
            // ---------------------------
            for (Map<String, Object> serItemMap : serItemMapList) {
                List<Map<String, Object>> wmsSoSerOrgList = getWmsSoSerialOriginal(execParam, trxId, unitId, segmId, serItemMap);

                // SerialError
                for (Map<String, Object> wmsSoSerOrg : wmsSoSerOrgList) {
                    String errMsgCdDetail = "";
                    errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, "");
                    errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                    // Check Data Serial
                    if(flgErrorHeader) {
                        errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                        errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, errMsgCdHeader);
                    }
                    else {
                        errMsgCdDetail = validateSoIFData_Serial(wmsSoSerOrg, trxId, unitId);
                        if (ZYPCommonFunc.hasValue(errMsgCdDetail)) {
                            flgSoHasError = true;
                            errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                            errInfo.put(NLBB071001Constant.COL_ERR_MSG_CD, errMsgCdDetail);
                        }
                        else {
                            errInfo.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                        }
                    }
                    // ------------------------
                    // Create I/F Data(Serial)
                    // ------------------------
                    createSoConfSer(wmsSoHdr, wmsSoSerOrg, trxId, unitId, errInfo);
                }
            }
            // Add End 2020/02/12 QC#55733
            // QC#56444
            if (soSlpNumList.size() > 0) {
                List<Map<String, Object>> soConfigSerList = getConfigSoSerial(execParam, soSlpNumList, trxId, unitId, segmId, otbdOrdNum);
                if (soConfigSerList != null && !soConfigSerList.isEmpty()) {
                    for (Map<String, Object> wmsSoSer : soConfigSerList) {
                        createSoConfSer(wmsSoHdr, wmsSoSer, trxId, unitId, errInfo);
                    }
                }
            }

            if (flgSoHasError) {
                ++errorCount;
            }
            else {
                ++successCount;
            }
        }
    }

    /**
     * @param execParam
     * @param trxId
     * @return
     */
    private List<Map<String, Object>> getWmsSoHeader(S21SsmExecutionParameter execParam, BigDecimal trxId) {
        // Header Parameter
        Map<String, Object> bndPrmHdr = new HashMap<String, Object>();
        bndPrmHdr.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bndPrmHdr.put(NLBB071001Constant.COL_WMS_TASK_CD_SHIP, NLBB071001Constant.VAL_WMS_TASK_CD_SHIP);
        bndPrmHdr.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        bndPrmHdr.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
        bndPrmHdr.put(NLBB071001Constant.COL_WMS_TASK_NM, NLBB071001Constant.VAL_WMS_TASK_NM_PGI);
        bndPrmHdr.put(NLBB071001Constant.COL_WMS_TASK_CD, NLBB071001Constant.VAL_WMS_TASK_CD_LF);
        bndPrmHdr.put(NLBB071001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
        bndPrmHdr.put(NLBB071001Constant.COL_INBD_OTBD_CD, NLBB071001Constant.VAR_INBD_OTBD_CD_OTBD);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoHeader", bndPrmHdr, execParam);
    }
    
    /**
     * getWmsSoDtl
     * @param execParam
     * @param trxId
     * @param unitId
     * @param segmId
     * @return
     */
    private List<Map<String, Object>> getWmsSoDetail(S21SsmExecutionParameter execParam, BigDecimal trxId, BigDecimal unitId, BigDecimal segmId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB071001Constant.COL_UNIT_ID, unitId);
        bindParam.put(NLBB071001Constant.COL_SEGMENT_ID, segmId);
        // Add Start 2020/02/07 QC#55733
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        bindParam.put("flgN", ZYPConstant.FLG_OFF_N);
        // Add End 2020/02/07 QC#55733
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoDetail", bindParam, execParam);
    }
    
    
    /**
     * getWmsSoSerial
     * @param execParam
     * @param trxId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsSoSerial(S21SsmExecutionParameter execParam, BigDecimal trxId, BigDecimal unitId, BigDecimal segmId) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB071001Constant.COL_UNIT_ID, unitId);
        bindParam.put(NLBB071001Constant.COL_SEGMENT_ID, segmId);
        bindParam.put(NLBB071001Constant.COL_WMS_TASK_CD_SERL, NLBB071001Constant.VAL_WMS_TASK_CD_SERL);
        // QC#50591
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        bindParam.put("flgN", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoSerial", bindParam, execParam);
    }

    /**
     * validateSoIFData_Header
     * @param wmsSoHdr
     * @param trxId
     * @param unitId
     * @return
     */
    private String validateSoIFData_Header(Map<String, Object> wmsSoHdr, BigDecimal trxId, BigDecimal unitId) {

        String errorLocation = this.interfaceId + "," + trxId.toPlainString() + "," + unitId.toPlainString();
        // ------------------------
        // NLBI1220_01 Column Check
        // ------------------------intfcErrMsgCd
        if (wmsSoHdr.get(NLBB071001Constant.COL_WMS_REC_ID) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_REC_ID, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoHdr.get(NLBB071001Constant.COL_WH_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WH_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoHdr.get(NLBB071001Constant.COL_EZINTIME) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_EZINTIME, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_OTBD_ORD_NUM, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoHdr.get(NLBB071001Constant.COL_S80_ORD_TP_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_S80_ORD_TP_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        
        if (wmsSoHdr.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_TRX_DT_TM_TS, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_OTBD_ORD_NUM, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoHdr.get(NLBB071001Constant.COL_INTFC_TP_ID) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_INTFC_TP_ID, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        //QC#19815  Delete mandatory check WMS_ORG_HOST_ID.

        if (wmsSoHdr.get(NLBB071001Constant.COL_INVTY_OWNR_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_INVTY_OWNR_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        
        return "";
    }

    /**
     * validateSoIFData_Header
     * @param wmsSoDtl
     * @param trxId
     * @param unitId
     * @return
     */
    private String validateSoIFData_Detail(Map<String, Object> wmsSoDtl, BigDecimal trxId, BigDecimal unitId) {

        String errorLocation = this.interfaceId + "," + trxId.toPlainString() + "," + unitId.toPlainString();
        // ------------------------
        // NLBI1220_02 Column Check
        // ------------------------
        if (wmsSoDtl.get(NLBB071001Constant.COL_WMS_MDSE_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_MDSE_CD, NLBB071001Constant.TBL_NLBI1220_02, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoDtl.get(NLBB071001Constant.COL_FROM_STK_STS_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_FROM_STK_STS_CD, NLBB071001Constant.TBL_NLBI1220_02, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoDtl.get(NLBB071001Constant.COL_RTL_SWH_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_RTL_SWH_CD, NLBB071001Constant.TBL_NLBI1220_02, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoDtl.get(NLBB071001Constant.COL_WMS_PROC_QTY) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_PROC_QTY, NLBB071001Constant.TBL_NLBI1220_02, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoDtl.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_OTBD_ORD_LINE_NUM, NLBB071001Constant.TBL_NLBI1220_02, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        return "";
    }

    /**
     * validateSoIFData_Serial
     * @param wmsSoSerial
     * @param trxId
     * @param unitId
     * @return
     */
    private String validateSoIFData_Serial(Map<String, Object> wmsSoSerial, BigDecimal trxId, BigDecimal unitId) {

        String errorLocation = this.interfaceId + "," + trxId.toPlainString() + "," + unitId.toPlainString();
        // Serial Record Check
        if (wmsSoSerial.get(NLBB071001Constant.COL_SER_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_SER_NUM, NLBB071001Constant.TBL_NLBI1220_03, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        if (wmsSoSerial.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_OTBD_ORD_LINE_NUM, NLBB071001Constant.TBL_NLBI1220_03, errorLocation });
            return NLBB071001Constant.NLGM0041E;
        }
        return "";
    }

    /**
     * createSOConfDtl
     * @param wmsSoHdr
     * @param wmsSoDtl
     * @param errInf
     * @param trnId
     * @param regUintId
     * @return
     */
    private void createSoConfDtl(Map<String, Object> wmsSoHdr, Map<String, Object> wmsSoDtl, Map<String, Object> errInf,  BigDecimal trnId, BigDecimal unitId) {
        WMS_INBD_TRXTMsg wmsInbdTrxtTmsg = new WMS_INBD_TRXTMsg();

        // 1-10
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.glblCmpyCd , this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRecId , (String) wmsSoHdr.get(NLBB071001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.whCd , (String) wmsSoHdr.get(NLBB071001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTaskCd , "SHIP");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcId , (String) wmsSoHdr.get(NLBB071001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcRcvTs , (String) wmsSoHdr.get(NLBB071001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxId, ZYPCommonFunc.leftPad(this.objNullToString(wmsSoHdr.get(NLBB071001Constant.COL_TRANSACTION_ID))
                                                                                       , NLBB071001Constant.LG_INTFC_TRX_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxSqNum, ZYPCommonFunc.leftPad(this.objNullToString(wmsSoHdr.get(NLBB071001Constant.COL_UNIT_ID))
                                                                                       , NLBB071001Constant.LG_UNIT_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wrkTrxId, ZYPCommonFunc.leftPad("1"
                                                                                       , NLBB071001Constant.LG_WRK_TRX_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcProcStsCd , (String) errInf.get(NLBB071001Constant.COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcErrMsgCd , (String) errInf.get(NLBB071001Constant.COL_ERR_MSG_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.procStsCd , NLBB071001Constant.VAL_PROC_STS_CD_UNTREATED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsMdseCd, (String) wmsSoDtl.get(NLBB071001Constant.COL_WMS_MDSE_CD));
        if ("Y".equals(wmsPackCdSetOwnerCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd, (String) ZYPCommonFunc.concatString((String) wmsSoHdr.get(NLBB071001Constant.COL_INVTY_OWNR_CD)
                                                                                                            ,""
                                                                                                            ,(String) wmsSoDtl.get(NLBB071001Constant.COL_RTL_SWH_CD)));
        }
        else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd, (String) wmsSoDtl.get("INVTY_LOC_CD"));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsStkStsCd, (String) wmsSoDtl.get(NLBB071001Constant.COL_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsDmgFlg , "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOldDmgFlg , "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdQty ,(BigDecimal)wmsSoDtl.get(NLBB071001Constant.COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.otbdOrdNum ,(String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.otbdOrdTpCd ,(String) wmsSoHdr.get(NLBB071001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.otbdOrdLineNum ,(BigDecimal) wmsSoDtl.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdStsCd, "SHIP");
        String wmsTrxDtTmTs = (String) wmsSoHdr.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(wmsTrxDtTmTs)) {
            // QC#19755 delete substring
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsShipDtTmTs, wmsTrxDtTmTs);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTrxDtTmTs, wmsTrxDtTmTs);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTotWt ,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.ordLineCpltFlg, "N");
//        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd ,(String) wmsSoHdr.get(NLBB071001Constant.COL_CARR_CD));
        Map<String, Object> tplCarrSvcLvl = getTplCarrSvcLvl((String) wmsSoHdr.get(NLBB071001Constant.COL_WH_OWNR_CD), (String) wmsSoHdr.get(NLBB071001Constant.COL_CARR_CD), (String) wmsSoHdr.get(NLBB071001Constant.COL_SHPG_SVC_LVL_CD));
        // QC#50389
        if (tplCarrSvcLvl == null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd , this.defWmsCarrCd);
        } else if (!ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get("CARR_CD"))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd , this.defWmsCarrCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd , (String) tplCarrSvcLvl.get("TPL_CARR_SVC_LVL_CD"));
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.proNum ,(String) wmsSoHdr.get(NLBB071001Constant.COL_BOL_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsShipId ,(String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.packMarkSeptFlg, "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOutCntnrNum ,(String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsFrtChrgAmt ,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsBatId, (String) wmsSoHdr.get(NLBB071001Constant.COL_DATE_TIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTpId, (String) wmsSoHdr.get(NLBB071001Constant.COL_INTFC_TP_ID));

        // QC#21281
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, (String) wmsSoHdr.get(NLBB071001Constant.COL_WMS_ORG_HOST_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, wmsOrgHostId);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsUpdHistNum, BigDecimal.ZERO);

        // Edit WMS_SO_CONF_DATA_IF

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxtTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxtTmsg.getReturnCode())) {
            outputErr(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_02,
                    NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });

            throw new S21AbendException(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_02,
                    NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });
        }

    }

    /**
     * @param wmsSoHdr
     * @param wmsSoSerList
     * @param regUintId
     * @return
     */
    private void createSoConfSer(Map<String, Object> wmsSoHdr, Map<String, Object> wmsSoSer, BigDecimal trnId, BigDecimal unitId, Map<String, Object> serErr) {
        WMS_INBD_TRXTMsg wmsInbdTrxtTmsg = new WMS_INBD_TRXTMsg();

        // 1-10
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.glblCmpyCd , this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsInbdTrxPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRecId , (String) wmsSoHdr.get(NLBB071001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.whCd , (String) wmsSoHdr.get(NLBB071001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTaskCd , "SERL");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcId , (String) wmsSoHdr.get(NLBB071001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcRcvTs , (String) wmsSoHdr.get(NLBB071001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxId, ZYPCommonFunc.leftPad(this.objNullToString(wmsSoHdr.get(NLBB071001Constant.COL_TRANSACTION_ID))
                                                                                       , NLBB071001Constant.LG_INTFC_TRX_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxSqNum, ZYPCommonFunc.leftPad(this.objNullToString(wmsSoHdr.get(NLBB071001Constant.COL_UNIT_ID))
                                                                                       , NLBB071001Constant.LG_UNIT_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wrkTrxId, ZYPCommonFunc.leftPad("1"
                                                                                       , NLBB071001Constant.LG_WRK_TRX_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcProcStsCd , (String) serErr.get(NLBB071001Constant.COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcErrMsgCd , (String) serErr.get(NLBB071001Constant.COL_ERR_MSG_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.procStsCd , NLBB071001Constant.VAL_PROC_STS_CD_UNTREATED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsMdseCd, (String) wmsSoSer.get(NLBB071001Constant.COL_WMS_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd,  (String) ZYPCommonFunc.concatString((String) wmsSoHdr.get(NLBB071001Constant.COL_INVTY_OWNR_CD)
                                                                                                     ,""
                                                                                                     ,(String) wmsSoSer.get(NLBB071001Constant.COL_RTL_SWH_CD)));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsStkStsCd, (String) wmsSoSer.get(NLBB071001Constant.COL_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsDmgFlg , "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOldDmgFlg , "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdQty ,(BigDecimal) wmsSoSer.get(NLBB071001Constant.COL_WMS_PROC_QTY));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.otbdOrdNum ,(String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.otbdOrdTpCd ,(String) wmsSoHdr.get(NLBB071001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.otbdOrdLineNum ,(BigDecimal) wmsSoSer.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrdStsCd, "SHIP");
        String wmsTrxDtTmTs = (String) wmsSoHdr.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(wmsTrxDtTmTs)) {
            // QC#19755 delete substring
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsShipDtTmTs, wmsTrxDtTmTs);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTrxDtTmTs, wmsTrxDtTmTs);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTotWt ,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.ordLineCpltFlg, "N");
        
        Map<String, Object> tplCarrSvcLvl = getTplCarrSvcLvl((String) wmsSoHdr.get(NLBB071001Constant.COL_WH_OWNR_CD), (String) wmsSoHdr.get(NLBB071001Constant.COL_CARR_CD), (String) wmsSoHdr.get(NLBB071001Constant.COL_SHPG_SVC_LVL_CD));
        if (tplCarrSvcLvl == null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd , this.defWmsCarrCd);
        } else if (!ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get("CARR_CD"))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd , this.defWmsCarrCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd , (String) tplCarrSvcLvl.get("TPL_CARR_SVC_LVL_CD"));
        }
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsCarrCd ,(String) wmsSoHdr.get(NLBB071001Constant.COL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.proNum ,(String) wmsSoHdr.get(NLBB071001Constant.COL_BOL_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsShipId ,(String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.packMarkSeptFlg, "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOutCntnrNum ,(String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsFrtChrgAmt ,BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.serNum ,(String) wmsSoSer.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsBatId, (String) wmsSoHdr.get(NLBB071001Constant.COL_DATE_TIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTpId, (String) wmsSoSer.get(NLBB071001Constant.COL_INTFC_TP_ID));

        // QC#21281
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, (String) wmsSoHdr.get(NLBB071001Constant.COL_WMS_ORG_HOST_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, wmsOrgHostId);

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsUpdHistNum, BigDecimal.ZERO);

        // Edit WMS_SO_CONF_DATA_IF
        // insert
        EZDTBLAccessor.insert(wmsInbdTrxtTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxtTmsg.getReturnCode())) {
            outputErr(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_03,
                    NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });

            throw new S21AbendException(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_03,
                    NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });
        }
    }
    /**
     * 
     * @param wmsPodData
     * @return
     */
    private boolean callNLZC405001(Map<String, Object> wmsPodData, String inbdOtbdCd) {
		NLZC405001PMsg nlzc405001PMsg = new NLZC405001PMsg();

		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.glblCmpyCd, this.glblCmpyCd);
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.slsDt, this.salesDate);
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.inbdOtbdCd, inbdOtbdCd);
		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxHdrNum, (String) wmsPodData.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
		if ((String) wmsPodData.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM) != null) {
			String trxLineNum = ZYPCommonFunc.leftPad(String.valueOf((String) wmsPodData.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM)), 3, "0");
			ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.trxLineNum, trxLineNum);
		}

		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.proNum, (String) wmsPodData.get(NLBB071001Constant.COL_BOL_NUM));

		if (INBD_OTBD.OUTBOUND.equals(inbdOtbdCd)) {
			ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.DELIVERED);
		}
		else if (INBD_OTBD.INBOUND.equals(inbdOtbdCd)) {
			ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.carrRsnCd, CARR_RSN.PICKED_UP);
		}

		// QC#22894
		String wmsTrxDtTmTs = (String) wmsPodData.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(wmsTrxDtTmTs)) {
            // EDI_STS_DT
            String strEdiStsDt = wmsTrxDtTmTs.substring(0, 8);
            ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.xxRqstDt ,strEdiStsDt);
            // EDI_STS_TM
            if (wmsTrxDtTmTs.length() > "YYYYMMDD".length()) {
                String strEdiStsTm = wmsTrxDtTmTs.substring(8, 14);
                ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm ,strEdiStsTm);
            } else {
            	ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.actlDelyTm ,"000000");
            }
        }

		ZYPEZDItemValueSetter.setValue(nlzc405001PMsg.podSrcTpCd, POD_SRC_TP.APEX);

        NLZC405001 nlzc405001 = new NLZC405001();
        nlzc405001.execute(nlzc405001PMsg, ONBATCH_TYPE.BATCH);
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nlzc405001PMsg);

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (msgId.endsWith("E")) {
                    outputApiCallErr(msgId, null, wmsPodData);    // 2019/03/29 T.Ogura [QC#30594,ADD]
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * QC#21302
     * createPodInterface
     * @param execParam
     */
    private void createPodInterface(S21SsmExecutionParameter execParam, BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
            bindParam.put(NLBB071001Constant.COL_WMS_TASK_NM_ARY, new String[] {NLBB071001Constant.VAL_WMS_TASK_NM_ORI });

            stmt = ssmLLClient.createPreparedStatement("getWmsPodData", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                // Get current BOL_NUM
                String bolNum = rs.getString(NLBB071001Constant.COL_BOL_NUM);

                // Get WMS Upload [POD] Header
                Map<String, Object> wmsPodData = new HashMap<String, Object>();

                wmsPodData.put(NLBB071001Constant.COL_TRANSACTION_ID, rs.getString(NLBB071001Constant.COL_TRANSACTION_ID));
                wmsPodData.put(NLBB071001Constant.COL_BOL_NUM, rs.getString(NLBB071001Constant.COL_BOL_NUM));
                wmsPodData.put(NLBB071001Constant.COL_WMS_TASK_CD, rs.getString(NLBB071001Constant.COL_WMS_TASK_CD));
                wmsPodData.put(NLBB071001Constant.COL_WMS_TASK_NM, rs.getString(NLBB071001Constant.COL_WMS_TASK_NM));
                wmsPodData.put(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS, rs.getString(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS));

                wmsPodData.put(NLBB071001Constant.COL_OTBD_ORD_NUM, rs.getString(NLBB071001Constant.COL_OTBD_ORD_NUM));
                wmsPodData.put(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM, rs.getString(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM));
                // Validation check
                if (!validatePodIFData(wmsPodData, trxId, bolNum)) {
                	// QC#21302
                    continue;
                    // All POD data go to output table including
                    // invalid record
                    // ++errorCount;
                    // continue;
                }

                String inbdOtbd = null;
                String wmsTaskCd = (String) wmsPodData.get(NLBB071001Constant.COL_WMS_TASK_CD);
                if (ZYPCommonFunc.hasValue(wmsTaskCd)) {
                    if (NLBB071001Constant.VAL_WMS_TASK_CD_LF.equals(wmsTaskCd)) {
                    	inbdOtbd = INBD_OTBD.OUTBOUND;
                        if (!callNLZC405001(wmsPodData, inbdOtbd)) {
//                            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_BOL_NUM, NLBB071001Constant.TBL_NLBI1220_02, this.interfaceId + "," + trxId.toPlainString() + "," + bolNum });    // 2019/03/29 T.Ogura [QC#30594,DEL]
                            continue;
                        }
//                    } else if (NLBB071001Constant.VAL_WMS_TASK_NM_PIC.equals(wmsTaskNm) && NLBB071001Constant.VAL_WMS_TASK_CD_LR.equals(wmsTaskCd)) {
//                        // Create WMS_INBD_TRX (RCVD)
//                    } else if (NLBB071001Constant.VAL_WMS_TASK_NM_PIC.equals(wmsTaskNm) && NLBB071001Constant.VAL_WMS_TASK_CD_LF.equals(wmsTaskCd)) {
//                        // Do Nothing
//                        continue;
                    } else if (NLBB071001Constant.VAL_WMS_TASK_CD_LR.equals(wmsTaskCd)) {
                        inbdOtbd = INBD_OTBD.INBOUND;
                        if (!callNLZC405001(wmsPodData, inbdOtbd)) {
//                            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_BOL_NUM, NLBB071001Constant.TBL_NLBI1220_02, this.interfaceId + "," + trxId.toPlainString() + "," + bolNum });    // 2019/03/29 T.Ogura [QC#30594,DEL]
                            continue;
                        }
                    }
                }

                // Break off at new BOL_NUM
//                if (bolNum == null || !bolNum.equals(lastBolNum)) {
//                    ++ediLnCtrlCd;
//                    lastBolNum = bolNum;
//                }
//
//                ++ediStCtrlCd;
//                createPodData(wmsPodData, ediGsCtrlCd, ediStCtrlCd, ediLnCtrlCd);
                ++successCount;

            }
        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB071001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * validatePodIFData
     * @param wmsPodData
     * @param trxId
     * @param bolNum
     * @return
     */
    private boolean validatePodIFData(Map<String, Object> wmsPodData, BigDecimal trxId, String bolNum) {

        String errorLocation = this.interfaceId + "," + trxId.toPlainString() + "," + bolNum;

        if (wmsPodData.get(NLBB071001Constant.COL_BOL_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_BOL_NUM, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            return false;
        }
        if (wmsPodData.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_TRX_DT_TM_TS, NLBB071001Constant.TBL_NLBI1220_02, errorLocation });
            return false;
        }

        return true;
    }
    /**
     * 
     * @param execParam
     */
    private void createRwsInterface(S21SsmExecutionParameter execParam, BigDecimal tranId) {
        // if(true){
        // return;
        // }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Loop in Target TRANSACTION_ID unit

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
            queryParam.put(NLBB071001Constant.COL_TRANSACTION_ID, tranId);
            queryParam.put(NLBB071001Constant.COL_INBD_OTBD_CD, NLBB071001Constant.VAL_INBD_OTBD_CD_IS_INBD);
            queryParam.put(NLBB071001Constant.COL_WH_OWNR_CD, NLBB071001Constant.VAL_WH_OWNR_CD_IS_APX);
            queryParam.put(NLBB071001Constant.COL_WMS_TASK_CD, NLBB071001Constant.VAL_WMS_TASK_CD_RCVD);
            queryParam.put(NLBB071001Constant.COL_TASK_PIC, NLBB071001Constant.VAL_WMS_TASK_NM_PIC);
            queryParam.put(NLBB071001Constant.COL_TASK_LR, NLBB071001Constant.VAL_WMS_TASK_CD_LR);
            
            // Get WMS Upload [Header] Detail
            stmt = ssmLLClient.createPreparedStatement("getWmsRwsHeader", queryParam, execParam);
            rs = stmt.executeQuery();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                // Get Current UnitId
                BigDecimal unitId = rs.getBigDecimal("UNIT_ID");
                
                // QC#22934
                // Get Current SegmentId
                BigDecimal segmentId = rs.getBigDecimal("SEGMENT_ID");

                // Get WMS Upload [SO] Header
                Map<String, Object> wmsRwsHdr = new HashMap<String, Object>();
                wmsRwsHdr.put(NLBB071001Constant.COL_INTERFACE_ID, rs.getString(NLBB071001Constant.COL_INTERFACE_ID));
                wmsRwsHdr.put(NLBB071001Constant.COL_TRANSACTION_ID, rs.getString(NLBB071001Constant.COL_TRANSACTION_ID));
                wmsRwsHdr.put(NLBB071001Constant.COL_SEGMENT_ID, rs.getString(NLBB071001Constant.COL_SEGMENT_ID));
                wmsRwsHdr.put(NLBB071001Constant.COL_UNIT_ID, rs.getString(NLBB071001Constant.COL_UNIT_ID));
                wmsRwsHdr.put(NLBB071001Constant.COL_SEQ_NUMBER, rs.getString(NLBB071001Constant.COL_SEQ_NUMBER));
                wmsRwsHdr.put(NLBB071001Constant.COL_WMS_REC_ID, rs.getString(NLBB071001Constant.COL_WMS_REC_ID));
                wmsRwsHdr.put(NLBB071001Constant.COL_BOL_NUM, rs.getString(NLBB071001Constant.COL_BOL_NUM));
                wmsRwsHdr.put(NLBB071001Constant.COL_WMS_TASK_CD, rs.getString(NLBB071001Constant.COL_WMS_TASK_CD));
                wmsRwsHdr.put(NLBB071001Constant.COL_WMS_TASK_NM, rs.getString(NLBB071001Constant.COL_WMS_TASK_NM));
                wmsRwsHdr.put(NLBB071001Constant.COL_OTBD_ORD_NUM, rs.getString(NLBB071001Constant.COL_OTBD_ORD_NUM));
                wmsRwsHdr.put(NLBB071001Constant.COL_SHPG_PNT_CD, rs.getString(NLBB071001Constant.COL_SHPG_PNT_CD));
                wmsRwsHdr.put(NLBB071001Constant.COL_RCV_PNT_CD, rs.getString(NLBB071001Constant.COL_RCV_PNT_CD));
                wmsRwsHdr.put(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS, rs.getString(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS));
                wmsRwsHdr.put(NLBB071001Constant.COL_EZINTIME, rs.getString(NLBB071001Constant.COL_EZINTIME));
                wmsRwsHdr.put(NLBB071001Constant.COL_WH_CD, rs.getString(NLBB071001Constant.COL_WH_CD));
                wmsRwsHdr.put(NLBB071001Constant.COL_RWS_NUM, rs.getString(NLBB071001Constant.COL_RWS_NUM));
                wmsRwsHdr.put(NLBB071001Constant.COL_INVTY_OWNR_CD,rs.getString(NLBB071001Constant.COL_INVTY_OWNR_CD));
                wmsRwsHdr.put(NLBB071001Constant.COL_S80_ORD_TP_CD, rs.getString(NLBB071001Constant.COL_S80_ORD_TP_CD));
                wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_TP_ID,rs.getString(NLBB071001Constant.COL_INTFC_TP_ID));
                wmsRwsHdr.put(NLBB071001Constant.COL_DATE_TIME, rs.getString(NLBB071001Constant.COL_DATE_TIME));
                wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
                wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, "");
                wmsRwsHdr.put(NLBB071001Constant.COL_RWS_REF_NUM, rs.getString(NLBB071001Constant.COL_RWS_REF_NUM));

                // Get WMS Upload [RWS] Detail
                List<Map<String, Object>> wmsRwsDtlList = getWmsRwsDetail(execParam, tranId, unitId, segmentId);
                
                // ErrFlug
                boolean flgError = false;
                
                for (Map<String, Object> wmsRwsDtl : wmsRwsDtlList) {
                    // Validation check
                    wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, "");
                    if (!validateRwsIFData(wmsRwsHdr, wmsRwsDtl, tranId, unitId)) {
                        flgError = true;
                        wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_NG);
                    }
                    else {
                        wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_PROC_STS_CD, NLBB071001Constant.VAL_INTFC_PROC_STS_CD_OK);
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
        } catch (SQLException e) {
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
        queryParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        queryParam.put(NLBB071001Constant.COL_TRANSACTION_ID, tranId);
        // QC#22934
        queryParam.put(NLBB071001Constant.COL_SEGMENT_ID, segmentId);
        queryParam.put(NLBB071001Constant.COL_UNIT_ID, unitId);
        List<Map<String, Object>> wmsRwsDtlList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsRwsDetail", queryParam, execParam);
        return wmsRwsDtlList;
    }

    /**
     * 
     * @param wmsRwsHdr
     * @param wmsRwsDtl
     * @param tranId
     * @param unitId
     * @return
     */
    private boolean validateRwsIFData(Map<String, Object> wmsRwsHdr, Map<String, Object> wmsRwsDtl, BigDecimal tranId, BigDecimal unitId) {

        String errorLocation = interfaceId + "," + tranId.toPlainString() + "," + unitId.toPlainString();
        
        // Header Check
        if (wmsRwsHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_OTBD_ORD_NUM, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_TRX_DT_TM_TS, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_EZINTIME) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_EZINTIME, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_WH_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WH_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_UNIT_ID) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_UNIT_ID, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_INVTY_OWNR_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_INVTY_OWNR_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_WMS_REC_ID) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_REC_ID, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_INTFC_TP_ID) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_INTFC_TP_ID, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsHdr.get(NLBB071001Constant.COL_S80_ORD_TP_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_S80_ORD_TP_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }

        // Body Check
        if (wmsRwsDtl.get(NLBB071001Constant.COL_WMS_MDSE_CD) == null) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_MDSE_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        BigDecimal wmsProcQty = (BigDecimal) wmsRwsDtl.get(NLBB071001Constant.COL_WMS_PROC_QTY);
        if (wmsProcQty == null || BigDecimal.ZERO.compareTo(wmsProcQty) >= 0) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_PROC_QTY, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            wmsRwsDtl.put(NLBB071001Constant.COL_WMS_PROC_QTY, BigDecimal.ZERO);
            return false;
        }
        BigDecimal otbdOrdLineNum = (BigDecimal) wmsRwsDtl.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM);
        if (otbdOrdLineNum == null || BigDecimal.ZERO.compareTo(otbdOrdLineNum) >= 0) {
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_OTBD_ORD_LINE_NUM, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsDtl.get(NLBB071001Constant.COL_RTL_SWH_CD) == null) {
            // err
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_RTL_SWH_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        // add
        if (wmsRwsDtl.get(NLBB071001Constant.COL_INVTY_STK_STS_CD) == null) {
            // err
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_INVTY_STK_STS_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
        if (wmsRwsDtl.get(NLBB071001Constant.COL_WMS_UOM_CD) == null) {
            // err
            outputErr(NLBB071001Constant.NLGM0041E, new String[] {NLBB071001Constant.COL_WMS_UOM_CD, NLBB071001Constant.TBL_NLBI1220_01, errorLocation });
            wmsRwsHdr.put(NLBB071001Constant.COL_INTFC_ERR_MSG_CD, NLBB071001Constant.NLGM0041E);
            return false;
        }
            // add end

        return true;
    }
    /**
     * createRwsConfDtl
     * @param wmsRwsHdr
     * @param wmsRwsDtl
     * @param trnId
     * @param unitId
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

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRecId , (String) wmsRwsHdr.get(NLBB071001Constant.COL_WMS_REC_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.whCd , (String) wmsRwsHdr.get(NLBB071001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTaskCd , NLBB071001Constant.VAL_WMS_TASK_CD_RCVD);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcId , (String) wmsRwsHdr.get(NLBB071001Constant.COL_INTERFACE_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcRcvTs , (String) wmsRwsHdr.get(NLBB071001Constant.COL_EZINTIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxId, ZYPCommonFunc.leftPad(this.objNullToString(wmsRwsHdr.get(NLBB071001Constant.COL_TRANSACTION_ID))
                                                                                       , NLBB071001Constant.LG_INTFC_TRX_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTrxSqNum, ZYPCommonFunc.leftPad(this.objNullToString(wmsRwsHdr.get(NLBB071001Constant.COL_UNIT_ID))
                                                                                       , NLBB071001Constant.LG_UNIT_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wrkTrxId, ZYPCommonFunc.leftPad("1"
                                                                                       , NLBB071001Constant.LG_WRK_TRX_ID
                                                                                       , NLBB071001Constant.VAL_ZERO));
        
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcProcStsCd , (String) wmsRwsHdr.get(NLBB071001Constant.COL_INTFC_PROC_STS_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcErrMsgCd , (String) wmsRwsHdr.get(NLBB071001Constant.COL_INTFC_ERR_MSG_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.procStsCd , NLBB071001Constant.VAL_PROC_STS_CD_UNTREATED);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.errMsgCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsStsCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRsnCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsMdseCd, (String) wmsRwsDtl.get(NLBB071001Constant.COL_WMS_MDSE_CD));
        if ("Y".equals(wmsPackCdSetOwnerCdFlg)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd,  (String) ZYPCommonFunc.concatString((String) wmsRwsHdr.get(NLBB071001Constant.COL_INVTY_OWNR_CD)
                                                                                                            ,""
                                                                                                            ,(String) wmsRwsDtl.get(NLBB071001Constant.COL_RTL_SWH_CD)));
        }
        else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsPkgCd, (String) wmsRwsDtl.get("INVTY_LOC_CD"));
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsTagId, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsStkStsCd, (String) wmsRwsDtl.get(NLBB071001Constant.COL_INVTY_STK_STS_CD));
        
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOldStkStsCd, "");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsDmgFlg , "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOldDmgFlg , "N");
        //ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgQty , null);
        
        BigDecimal numWmsProcQty = (BigDecimal)(wmsRwsDtl.get(NLBB071001Constant.COL_WMS_PROC_QTY));
        BigDecimal numSerialCount = (BigDecimal)(wmsRwsDtl.get(NLBB071001Constant.COL_SER_CHK_CNT));
        BigDecimal numSerialRowNo = (BigDecimal)(wmsRwsDtl.get(NLBB071001Constant.COL_SER_CHK_ROWNUM));
        if (wmsRwsDtl.get(NLBB071001Constant.COL_SER_NUM) == null) {
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
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.usrId_03, (String) wmsRwsDtl.get(NLBB071001Constant.COL_WMS_UOM_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsRcptDtTmTs, (String) wmsRwsHdr.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdNum, (String) wmsRwsHdr.get(NLBB071001Constant.COL_RWS_REF_NUM));

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdTpCd, (String) wmsRwsHdr.get(NLBB071001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.inbdOrdLineNum, (BigDecimal)(wmsRwsDtl.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM)));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.ordLineCpltFlg, "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.packMarkSeptFlg, "N");
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.serNum, (String) wmsRwsDtl.get(NLBB071001Constant.COL_SER_NUM));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsBatId, (String) wmsRwsHdr.get(NLBB071001Constant.COL_DATE_TIME));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.intfcTpId, (String) wmsRwsHdr.get(NLBB071001Constant.COL_INTFC_TP_ID));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsOrgHostId, wmsOrgHostId);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxtTmsg.wmsUpdHistNum, BigDecimal.ZERO);

        // Edit WMS_SO_CONF_DATA_IF

        // insert
        EZDTBLAccessor.insert(wmsInbdTrxtTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxtTmsg.getReturnCode())) {
            outputErr(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_01,
                    NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });

            throw new S21AbendException(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_01,
                    NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });
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
                outputErr(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_01,
                        NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });

                throw new S21AbendException(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TARGET_ID, NLBB071001Constant.TBL_NLBI1220_01,
                        NLXCMsgHelper.toListedString(NLBB071001Constant.COL_INTERFACE_ID, NLBB071001Constant.COL_TRANSACTION_ID, NLBB071001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(NLBB071001Constant.TARGET_ID, trnId, unitId) });
            }
            
        }

    }
    /**
     * createPodWrkInterface
     * @param execParam
     * @param trxId
     */
    private void createPodWrkInterface(S21SsmExecutionParameter execParam, BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // BigDecimal ediGsCtrlCd = trxAccess.getNextTransactionId();
        BigDecimal ediGsCtrlCd = ZYPOracleSeqAccessor.getNumberBigDecimal("EDI_POD_CTRL_SQ");

        int ediStCtrlCd = 0;
        int ediLnCtrlCd = 0;
        String lastBolNum = null;

        try {

            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
            bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
            bindParam.put(NLBB071001Constant.COL_WMS_TASK_NM, NLBB071001Constant.VAL_WMS_TASK_NM_PIC);
            bindParam.put(NLBB071001Constant.COL_WMS_TASK_CD, NLBB071001Constant.VAL_WMS_TASK_CD_LF);

            stmt = ssmLLClient.createPreparedStatement("getWmsPodWrkData", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop n UNIT_ID unit
            while (rs.next()) {

                // Get current BOL_NUM
                String bolNum = rs.getString(NLBB071001Constant.COL_BOL_NUM);

                // Get WMS Upload [POD] Header
                Map<String, Object> wmsPodData = new HashMap<String, Object>();

                wmsPodData.put(NLBB071001Constant.COL_TRANSACTION_ID, rs.getString(NLBB071001Constant.COL_TRANSACTION_ID));
                wmsPodData.put(NLBB071001Constant.COL_BOL_NUM, rs.getString(NLBB071001Constant.COL_BOL_NUM));
                wmsPodData.put(NLBB071001Constant.COL_WMS_TASK_CD, rs.getString(NLBB071001Constant.COL_WMS_TASK_CD));
                wmsPodData.put(NLBB071001Constant.COL_WMS_TASK_NM, rs.getString(NLBB071001Constant.COL_WMS_TASK_NM));
                wmsPodData.put(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS, rs.getString(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS));
                wmsPodData.put(NLBB071001Constant.COL_WMS_SUM_PROC_QTY, rs.getBigDecimal(NLBB071001Constant.COL_WMS_SUM_PROC_QTY));

                // Validation check
                if (!validatePodIFData(wmsPodData, trxId, bolNum)) {
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
                createPodData(wmsPodData, ediGsCtrlCd, ediStCtrlCd, ediLnCtrlCd);
                ++successCount;

            }
        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB071001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    /**
     * createPodData
     * @param wmsPodData
     * @param ediGsCtrlCd
     * @param ediStCtrlCd
     * @param ediLnCtrlCd
     */
    private void createPodData(Map<String, Object> wmsPodData, BigDecimal ediGsCtrlCd, int ediStCtrlCd, int ediLnCtrlCd) {

        // Register POD_ADDR_WRK...
        POD_ADDR_WRKTMsg podAddrWrk = new POD_ADDR_WRKTMsg();

        // for (Map<String, Object> wmsPodData : wmsPodDataList) {

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.glblCmpyCd, this.glblCmpyCd);

        // EDI_TRX_ID
        String strTrxId = (String) wmsPodData.get(NLBB071001Constant.COL_TRANSACTION_ID);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTrxId, strTrxId);

        // EDI_SQ_ID (SeqID（AutoSeq) EDI_POD_ADDR_SQ.nextval)
        BigDecimal ediSqId = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_POD_ADDR_SQ);
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediSqId, ediSqId.toPlainString());

        // PROC_STS_CD
        String strProcStsCd = "0";
        ZYPEZDItemValueSetter.setValue(podAddrWrk.procStsCd, strProcStsCd);

        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediTpCd, NLBB071001Constant.VAL_EDI_TP_CD);

        // EDI_GS_CTRL_CD (For each batch processing of once, Sql ID
        // numbering)
        String ediGsCtrlCdWk = ediGsCtrlCd.toPlainString();
        if (ediGsCtrlCdWk.length() > 10) {
            ediGsCtrlCdWk = ediGsCtrlCdWk.substring(ediGsCtrlCdWk.length() - 10);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediGsCtrlCd, ediGsCtrlCdWk);

        // EDI_ST_CTRL_CD
        String ediStCtrlCdWk = ediGsCtrlCdWk + ZYPCommonFunc.leftPad(String.valueOf(ediStCtrlCd), NLBB071001Constant.LG_EDI_ST_CTRL_CD, NLBB071001Constant.VAL_ZERO);
        if (ediStCtrlCdWk.length() > 10) {
            ediStCtrlCdWk = ediStCtrlCdWk.substring(ediStCtrlCdWk.length() - 10);
        }
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediStCtrlCd, ediStCtrlCdWk);

        // EDI_SHPPR_NM
        ZYPEZDItemValueSetter.setValue(podAddrWrk.ediShpprNm, NLBB071001Constant.VAL_EDI_TP_CD);

        // POD_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(podAddrWrk.podSrcTpCd, "07");

        // //////////////////////////////////////////////
        // insert POD_ADDR_WRK
        EZDTBLAccessor.insert(podAddrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podAddrWrk.getReturnCode())) {
            outputErr(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TBL_POD_ADDR_WRK, NLBB071001Constant.TBL_NLBI1220_01, NLXCMsgHelper.toListedString(NLBB071001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB071001Constant.COL_TRANSACTION_ID)) });
            throw new S21AbendException(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TBL_POD_ADDR_WRK, NLBB071001Constant.TBL_NLBI1220_01, NLXCMsgHelper.toListedString(NLBB071001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB071001Constant.COL_TRANSACTION_ID)) });
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
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTpCd, NLBB071001Constant.VAL_EDI_TP_CD);

        // EDI_GS_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediGsCtrlCd, ediGsCtrlCdWk);

        // EDI_ST_CTRL_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediStCtrlCd, ediStCtrlCdWk);

        // EDI_LN_CTRL_CD
        String strEdiLnCtrlCd = ZYPCommonFunc.leftPad(String.valueOf(ediLnCtrlCd), NLBB071001Constant.LG_EDI_LN_CTRL_CD, NLBB071001Constant.VAL_ZERO);
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLnCtrlCd, strEdiLnCtrlCd);

        // EDI_PRO_NUM
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediProNum, (String) wmsPodData.get(NLBB071001Constant.COL_BOL_NUM));

        // EDI_STS_CD
        String wmsTaskNm = (String) wmsPodData.get(NLBB071001Constant.COL_WMS_TASK_NM);
        String wmsTaskCd = (String) wmsPodData.get(NLBB071001Constant.COL_WMS_TASK_CD);
        if (ZYPCommonFunc.hasValue(wmsTaskNm) && ZYPCommonFunc.hasValue(wmsTaskCd)) {
            String strEdiStsCd = null;
            if (NLBB071001Constant.VAL_WMS_TASK_NM_ORI.equals(wmsTaskNm) && NLBB071001Constant.VAL_WMS_TASK_CD_LF.equals(wmsTaskCd)) {
                strEdiStsCd = POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION;
            } else if (NLBB071001Constant.VAL_WMS_TASK_NM_PIC.equals(wmsTaskNm) && NLBB071001Constant.VAL_WMS_TASK_CD_LR.equals(wmsTaskCd)) {
                strEdiStsCd = POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION;
            } else if (NLBB071001Constant.VAL_WMS_TASK_NM_PIC.equals(wmsTaskNm) && NLBB071001Constant.VAL_WMS_TASK_CD_LF.equals(wmsTaskCd)) {
                strEdiStsCd = POD_STS.COMPLETED_LOADING_AT_PICK_UP_LOCATION;
            } else if (NLBB071001Constant.VAL_WMS_TASK_NM_ORI.equals(wmsTaskNm) && NLBB071001Constant.VAL_WMS_TASK_CD_LR.equals(wmsTaskCd)) {
                strEdiStsCd = POD_STS.COMPLETED_LOADING_AT_PICK_UP_LOCATION;
            }
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsCd, strEdiStsCd);
        }

        String wmsTrxDtTmTs = (String) wmsPodData.get(NLBB071001Constant.COL_WMS_TRX_DT_TM_TS);
        if (ZYPCommonFunc.hasValue(wmsTrxDtTmTs)) {
            // EDI_STS_DT
            String strEdiStsDt = wmsTrxDtTmTs.substring(0, 8);
            ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsDt, strEdiStsDt);
            // QC#19755
            // EDI_STS_TM
            if (wmsTrxDtTmTs.length() > "YYYYMMDD".length()) {
                String strEdiStsTm = wmsTrxDtTmTs.substring(8, 14);
                ZYPEZDItemValueSetter.setValue(podStsWrk.ediStsTm, strEdiStsTm);
            }
        }

        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediTmCd, NLBB071001Constant.VAL_CONST_EDI_TM_CD);

        // EDI_LOAD_QTY
        ZYPEZDItemValueSetter.setValue(podStsWrk.ediLoadQty, (BigDecimal) wmsPodData.get(NLBB071001Constant.COL_WMS_SUM_PROC_QTY));

        // //////////////////////////////////////////////
        // insert POD_STS_WRK
        EZDTBLAccessor.insert(podStsWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(podStsWrk.getReturnCode())) {
            outputErr(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TBL_POD_STS_WRK, NLBB071001Constant.TBL_NLBI1220_01, NLXCMsgHelper.toListedString(NLBB071001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB071001Constant.COL_TRANSACTION_ID)) });
            throw new S21AbendException(NLBB071001Constant.NLGM0045E, new String[] {NLBB071001Constant.TBL_POD_STS_WRK, NLBB071001Constant.TBL_NLBI1220_01, NLXCMsgHelper.toListedString(NLBB071001Constant.COL_GLBL_CMPY_CD, "EDI_TRX_ID") //
                    , NLXCMsgHelper.toListedString(this.glblCmpyCd, wmsPodData.get(NLBB071001Constant.COL_TRANSACTION_ID)) });
        }
    }

    /**
     * convZeroPaddingDeclPntNone
     * @param val
     * @param lg
     * @param declLg
     * @return
     */
    private String convZeroPaddingDeclPntNone(final BigDecimal val, final int lg, final int declLg) {

        BigDecimal bigDeclVal = val.movePointRight(declLg);
        bigDeclVal = bigDeclVal.setScale(0, BigDecimal.ROUND_DOWN);
        BigInteger bigIntVal = bigDeclVal.toBigInteger();

        String strVal = String.format("%0" + Integer.toString(lg) + "d", bigIntVal);

        if (strVal.length() > lg) {
            return String.format("%" + Integer.toString(lg) + "s", "");
        } else {
            return strVal;
        }
    }

    /**
     * GetNumericOrNull
     * @param strVal
     * @return numericValue
     */
    private BigDecimal GetNumericOrNull(String strVal) {
        BigDecimal retVal = null;
        if (ZYPCommonFunc.hasValue(strVal)) {
            retVal = new BigDecimal(strVal);
        }
        else {
            retVal = null;
        }
        return retVal;
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
    /**
     * getTplCarrSvcLvl
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvl(String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB071001Constant.COL_WH_OWNR_CD, whOwnrCd);
        queryParam.put(NLBB071001Constant.COL_CARR_CD, carrCd);
        queryParam.put(NLBB071001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return ((Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam));
    }

    // START 2019/03/29 T.Ogura [QC#30594,ADD]
    private void outputApiCallErr(String msgId, String[] msgParam, Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        builder.append(S21MessageFunc.clspGetMessage(msgId, msgParam));
        builder.append(NLBB071001Constant.VAL_BLANK);
        builder.append(generateWmsPodKeyInfo(wmsPodData));

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }
    // END   2019/03/29 T.Ogura [QC#30594,ADD]

    // START 2019/03/29 T.Ogura [QC#30594,ADD]
    private String generateWmsPodKeyInfo(Map<String, Object> wmsPodData) {
        StringBuilder builder = new StringBuilder();
        builder.append("Table:[");
        builder.append(NLBB071001Constant.TBL_NLBI1220_02);
        builder.append("]");
        builder.append(NLBB071001Constant.VAL_BLANK);
        builder.append("Search Key:[");
        builder.append(this.interfaceId);
        builder.append(",");
        builder.append((String)wmsPodData.get(NLBB071001Constant.COL_TRANSACTION_ID));
        builder.append(",");
        builder.append((String)wmsPodData.get(NLBB071001Constant.COL_BOL_NUM));
        builder.append("]");
        return builder.toString();
    }
    // END   2019/03/29 T.Ogura [QC#30594,ADD]

    // Add Start 2020/02/12 QC#55733
    /**
     * getSerItemMap
     * @param wmsSoHdr
     * @param wmsSoDtl
     * @return
     */
    private Map<String, Object> getSerItemMap(Map<String, Object> wmsSoHdr, Map<String, Object> wmsSoDtl) {
        if (S21StringUtil.isEquals((String) wmsSoDtl.get(NLBB071001Constant.COL_SER_ITEM_FLG), ZYPConstant.FLG_OFF_N)) {
            return null;
        }

        Map<String, Object> serItemMap = new HashMap<String, Object>();
        serItemMap.put(NLBB071001Constant.COL_OTBD_ORD_NUM, (String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        serItemMap.put(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM, (BigDecimal) wmsSoDtl.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM));
        serItemMap.put(NLBB071001Constant.COL_SO_SLP_NUM, (String) wmsSoDtl.get(NLBB071001Constant.COL_SO_SLP_NUM));
        serItemMap.put(NLBB071001Constant.COL_WMS_MDSE_CD, (String) wmsSoDtl.get(NLBB071001Constant.COL_WMS_MDSE_CD));
        serItemMap.put(NLBB071001Constant.COL_WMS_PROC_QTY, (BigDecimal)wmsSoDtl.get(NLBB071001Constant.COL_WMS_PROC_QTY));

        return serItemMap;
    }

    /**
     * getRmvItemMap
     * @param wmsSoHdr
     * @param wmsSoSer
     * @param serItemMapList
     * @return
     */
    private Map<String, Object> getRmvItemMap(Map<String, Object> wmsSoHdr, Map<String, Object> wmsSoSer, List<Map<String, Object>> serItemMapList) {
        for (Map<String, Object> serItemMap : serItemMapList) {
            BigDecimal mapLineNum = (BigDecimal) serItemMap.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM);
            BigDecimal soDtlLineNum = (BigDecimal) wmsSoSer.get(NLBB071001Constant.COL_OTBD_ORD_LINE_NUM);

            if (S21StringUtil.isEquals((String) serItemMap.get(NLBB071001Constant.COL_OTBD_ORD_NUM), (String) wmsSoHdr.get(NLBB071001Constant.COL_OTBD_ORD_NUM)) //
                    && mapLineNum.compareTo(soDtlLineNum) == 0) {
                return serItemMap;
            }
        }

        return null;
    }

    /**
     * getWmsSoSerialOriginal
     * @param execParam
     * @param trxId
     * @param unitId
     * @param serItemMap
     * @return
     */
    private List<Map<String, Object>> getWmsSoSerialOriginal(S21SsmExecutionParameter execParam, BigDecimal trxId, BigDecimal unitId, BigDecimal segmId, Map<String, Object> serItemMap) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB071001Constant.COL_UNIT_ID, unitId);
        bindParam.put(NLBB071001Constant.COL_SEGMENT_ID, segmId);
        bindParam.put(NLBB071001Constant.COL_WMS_TASK_CD_SERL, NLBB071001Constant.VAL_WMS_TASK_CD_SERL);
        bindParam.put(NLBB071001Constant.COL_OTBD_ORD_NUM, (String) serItemMap.get(NLBB071001Constant.COL_OTBD_ORD_NUM));
        bindParam.put(NLBB071001Constant.COL_SO_SLP_NUM, (String) serItemMap.get(NLBB071001Constant.COL_SO_SLP_NUM));
        bindParam.put(NLBB071001Constant.COL_SVC_MACH_USG_STS_CD_IN_INVTY, SVC_MACH_USG_STS.IN_INVENTORY);
        bindParam.put(NLBB071001Constant.COL_LOC_STS_CD_DC_STOC, LOC_STS.DC_STOCK);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        bindParam.put(NLBB071001Constant.COL_SVC_MACH_MSTR_STS_CD, svcMachMstrStsCdList);
        bindParam.put(NLBB071001Constant.COL_ROW_NUM, (BigDecimal) serItemMap.get(NLBB071001Constant.COL_WMS_PROC_QTY));
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        bindParam.put("flgN", ZYPConstant.FLG_OFF_N);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWmsSoSerialOriginal", bindParam, execParam);
    }
    // Add End 2020/02/12 QC#55733

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
        bindParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB071001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB071001Constant.COL_UNIT_ID, unitId);
        bindParam.put(NLBB071001Constant.COL_SO_NUM, otbdOrdNum);

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
        bindParam.put(NLBB071001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        bindParam.put(NLBB071001Constant.COL_INTERFACE_ID, this.interfaceId);
        bindParam.put(NLBB071001Constant.COL_TRANSACTION_ID, trxId);
        bindParam.put(NLBB071001Constant.COL_SEGMENT_ID, segmentId);
        bindParam.put(NLBB071001Constant.COL_UNIT_ID, unitId);
        bindParam.put(WMS_TASK.SERL, WMS_TASK.SERL);
        bindParam.put(NLBB071001Constant.COL_SO_NUM, otbdOrdNum);
        if (soSlpNumList != null && !soSlpNumList.isEmpty()) {
            bindParam.put("soSlpNumList", soSlpNumList);
        }
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        bindParam.put("flgN", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getConfigSoSerial", bindParam, execParam);
    }
}
