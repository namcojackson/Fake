/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB061001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.WMS_INBD_SO_BILL_TOTMsg;
import business.db.WMS_INBD_SO_CHRG_TOTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_SO_SHIP_TOTMsg;
import business.db.WMS_INBD_SO_TEXTTMsg;
import business.db.WMS_INBD_TRXTMsg;
import business.db.WMS_TASKTMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;
import business.parts.NPZC103001_prchReqInfoPMsgArray;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.batch.NLB.NLBB060001.NLBB060001;
import com.canon.cusa.s21.batch.NLB.NLBB061001.constant.NLBB061001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 * Batch Program Class for Ship Order Confirmation(PostGoodsIssue) from Choice
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2016   CITS            Y.IWASAKI       Create          
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19371
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19434
 * 07/11/2017   CITS            Y.IWASAKI       Update          QC#19695
 * 07/19/2017   CITS            Y.Iwasaki       Update          QC#19981
 * 09/07/2017   CITS            Y.Iwasaki       Update          QC#18834
 * 09/11/2017   CITS            T.Tokutomi      Update          QC#20379
 * 10/04/2017   CITS            Y.Iwasaki       Update          QC#21620
 * 07/31/2018   CITS            Y.Iwasaki       Update          QC#27459
 * 10/22/2018   CITS            Y.Iwasaki       Update          QC#28874
 * 01/22/2019   CITS            K.Ogino         Update          QC#29924
 * 07/12/2019   CITS            K.Ogino         Update          QC#51274
 * 07/29/2019   CITS            T.Wada          Update          QC#52077
 * 08/27/2019   CITS            T.Wada          Update          QC#52509
 * 10/02/2019   CITS            K.Ogino         Update          QC#52894
 * 11/26/2019   Fujitsu         R.Nakamura      Update          QC#52804
 * 12/01/2020   CITS            M.Furugoori     Update          QC#57659
 *</pre>
 */
public class NLBB061001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    // *********************************************************
    // Instance Variables: Constant values on load
    // *********************************************************

    /** */
    private String trgtWhOwnrCd = null;

    /** */
    private String rtlWhCdColumbus = null;

    /** */
    private String intfcTpId = null;

    /** */
    private String wmsPackCdMode;

    /** SalesDate */
    private String salesDate = null;

    // QC#52894 Start
    private String[] shipConfStsZTickets = null;

    private String[] shipConfStsGTickets = null;
    // QC#52894 End

    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {

        new NLBB061001().executeBatch(NLBB061001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();

        // Getting Global Company Code
        this.glblCmpyCd = prof.getGlobalCompanyCode();

        // Getting Interface ID
        this.interfaceId = getInterfaceID();

        // Get target WH code
        this.trgtWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_TARGET_ORDER, glblCmpyCd);
        //trgtWhOwnrCd = WH_OWNR.CHOICE;

        // Get Columbus WH code
        this.rtlWhCdColumbus = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_RTL_WH_CD_COLUMBUS, glblCmpyCd);
        //rtlWhCdColumbus = "2P1";

        // Get INTFC_TP_ID for WMS_TASK_CD=SHIP
        this.intfcTpId = getIntfcTpId(glblCmpyCd, WMS_TASK.SHIP);

        // Get current sales date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Aquiring mode for WMS_PACK_CD. Normally "Y"
        this.wmsPackCdMode = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);

        // Aquiring mode for WMS_PACK_CD. Normally "Y"
        this.wmsPackCdMode = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);

        // Aquiring mode for WMS_PACK_CD. Normally "Y"
        this.wmsPackCdMode = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);

        // QC#52894 Add Start
        String varCharConstGTickets = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_NLBB0610_SO_CONF_G, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConstGTickets)) {
            this.shipConfStsGTickets = varCharConstGTickets.split(",");
        } else {
            this.shipConfStsGTickets = new String[]{"B"};
        }
        String varCharConstZTickets = ZYPCodeDataUtil.getVarCharConstValue(NLBB061001Constant.VAR_CHAR_CONST_NM_NLBB0610_SO_CONF_Z, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(varCharConstZTickets)) {
            this.shipConfStsZTickets = varCharConstZTickets.split(",");
        } else {
            this.shipConfStsZTickets = new String[]{"B"};
        }
        // QC#52894 Add End

        // Check on input parameter
        checkParameter();
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        // Get Target TRANSACTION_ID
        BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);

        if (tranIds.length <= 0) {
            return;
        }

        try {
            for (BigDecimal trxId : tranIds) {
                // SO
                createSoConfTrxRecords(trxId);
                commit();
            }

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB061001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {
        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(NLBB061001Constant.ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount)});

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);
    }

    private String getIntfcTpId(String glblCmpyCd, String wmsTaskCd) {
        String intfcTpId=null;
        WMS_TASKTMsg tMsg=new WMS_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTaskCd, wmsTaskCd);
        tMsg=(WMS_TASKTMsg)EZDTBLAccessor.findByKey(tMsg);
        if(tMsg!=null) {
            intfcTpId=tMsg.intfcTpId.getValue();
        }
        return intfcTpId;
    }

    private void checkParameter() {
        
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLBB061001Constant.NLGM0049E, new String[] {NLBB061001Constant.PARAM_NM_GLBL_CMPY_CD });
        }
        
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB061001Constant.NLGM0049E, new String[] {NLBB061001Constant.PARAM_NM_INTERFACE_ID });
        }
    }

    /**
     * WMS Upload SO Data Creation Process
     */
    private void createSoConfTrxRecords(BigDecimal trxId) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        BigDecimal regTrxId = trxAccess.getNextTransactionId();
        int regUnitId = 0;

        List<Map<String, Object>> wmsSoHdrList = getWmsSoHeader(this.interfaceId, trxId, glblCmpyCd);

        // Loop in each UNIT_ID
        for (Map<String, Object> wmsSoHdr : wmsSoHdrList) {
            boolean soCreated=false;

            // Get current UNIT_ID and SO_NUM
            // Add Start 2019/11/26 QC#52804
            String ordNum = (String) wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM);
            // Add End 2019/11/26 QC#52804
            BigDecimal unitId = (BigDecimal) wmsSoHdr.get(NLBB061001Constant.COL_UNIT_ID);
            BigDecimal segmentId = (BigDecimal) wmsSoHdr.get(NLBB061001Constant.COL_SEGMENT_ID);
            String soNum = (String) wmsSoHdr.get(NLBB061001Constant.COL_SO_NUM);
            wmsSoHdr = setTechTocCd(wmsSoHdr);

            // Get WMS Upload [SO] Details
            List<Map<String, Object>> wmsSoDtlList = getWmsSoDetail(this.interfaceId, trxId, segmentId, unitId, glblCmpyCd);
            // QC#51274
            if (wmsSoDtlList == null || wmsSoDtlList.isEmpty()) {
                continue;
            }

            boolean valid=true;
            if (!ZYPCommonFunc.hasValue(soNum)) {
                // SO_NUM is not in SHPG_ORD

                // Validation check with input to PR Update API
                // Mod Start 2019/11/26 QC#52804
//                valid=validatePRUpdateAPIInputData(wmsSoHdr, wmsSoDtlList, trxId, unitId);
                valid = validatePRUpdateAPIInputData(wmsSoHdr, wmsSoDtlList, ordNum, trxId, segmentId);
                // Mod End 2019/11/26 QC#52804

                // Call PR update API
                if(valid) {
                    NPZC103001PMsg pMsg = executePRUpdateAPI(wmsSoHdr, wmsSoDtlList, this.glblCmpyCd, this.trgtWhOwnrCd);
    
                    // Validation check with output from PR Update API
                    // Mod Start 2019/11/26 QC#52804
//                    valid=validatePRUpdateAPIResult(wmsSoHdr, pMsg, trxId, unitId);
                    valid = validatePRUpdateAPIResult(wmsSoHdr, pMsg, trxId, segmentId, unitId);
                    // Mod End 2019/11/26 QC#52804
    
                    if(valid) {
                        soNum = pMsg.prchReqInfo.no(0).soNum.getValue();
                        soCreated = true;
                    } else {
                        //QC# Rollback updated record by API and restart transaction to write ERRMSG to WMS_INBD_TRX. 
                        rollback();
                    }
                }
            }

            // Skip creating output record if shipped from Columbus
            if (isShipFromColumbus(wmsSoHdr)) {
                if(valid) {
                    ++this.successCount;
                    // QC#52509 Add
                    commit();
                } else {
                    ++this.errorCount;
                }
                continue;
            }

            // Shipped from Choice

            String positionKey = NLXCMsgHelper.toListedString(NLBB061001Constant.COL_INTERFACE_ID, NLBB061001Constant.COL_TRANSACTION_ID, NLBB061001Constant.COL_UNIT_ID);
            String position = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

            Map<String, Object> soHeader = new HashMap<String, Object>();
            List<Map<String, Object>> soDetailList = new ArrayList<Map<String, Object>>();

            if (ZYPCommonFunc.hasValue(soNum)) {
                soHeader = getSoHeader(glblCmpyCd, soNum);
                soDetailList = getSoDetailList(glblCmpyCd, soNum);

                // Under developing environment, queried SO might match to the SO of other WH by accident, and causes abend.
                // Confirm soHeader/soDetailList is valid.
                if (soHeader == null || soDetailList == null) {
                    valid = false;
                    // Mod Start 2019/11/26 QC#52804
//                    setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0041E);
//                    outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_SO_NUM, NLBB061001Constant.TBL_SHPG_ORD, position });
                    setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0089E);
                    outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_SO_NUM, ordNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                    // Mod End 2019/11/26 QC#52804
                }
            }

            if (soCreated) {
                // Use logic in NLBB060001
                NLBB060001 instance = new NLBB060001();

                // Suppress sending SO to Choice if SO is post-created and Choice SO. - DBS SO should be sent to DBS.
                instance.updateWmsDropFlg(glblCmpyCd, soNum, ZYPConstant.FLG_ON_Y);

                if(valid) {
                    // Create WMS_INBD_SO_*
                    List<Map<String, Object>> soTextList = getSoTextList(glblCmpyCd, soNum);
    
                    // Get CARR_CD and SHPG_SVC_LVL_CD
                    Map<String, Object> tplCarrSvcLvl = null;
                    String carrCd = (String) soHeader.get(NLBB061001Constant.COL_CARR_CD);
                    String shpgSvcLvlCd = (String) soHeader.get(NLBB061001Constant.COL_SHPG_SVC_LVL_CD);
                    tplCarrSvcLvl = instance.getTplCarrSvcLvlWithEffort(this.glblCmpyCd, this.trgtWhOwnrCd, carrCd, shpgSvcLvlCd);
    
                    // WMS_INBD_SO_HDR
                    WMS_INBD_SO_HDRTMsg wmsSoHdrTMsg = instance.createWmsInbdSoHdr(soHeader, tplCarrSvcLvl, this.salesDate);
                    EZDTBLAccessor.insert(wmsSoHdrTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoHdrTMsg.getReturnCode())) {
                        outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_HDR, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
    
                        throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_HDR, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                    }
    
                    // WMS_INBD_SO_DTL
                    if (soDetailList != null && soDetailList.size() > 0) {
                        List<WMS_INBD_SO_DTLTMsg> wmsSoDtlTMsgList = instance.createWmsInbdSoDtlList(wmsSoHdrTMsg, soDetailList);
                        int count = S21FastTBLAccessor.insert(wmsSoDtlTMsgList.toArray(new WMS_INBD_SO_DTLTMsg[wmsSoDtlTMsgList.size()]));
                        if (count != wmsSoDtlTMsgList.size()) {
                            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_DTL, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
    
                            throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_DTL, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                        }
                    }
    
                    // WMS_INBD_SO_TEXT
                    if (soTextList != null && soTextList.size() > 0) {
                        WMS_INBD_SO_TEXTTMsg wmsSoTextTMsg = instance.createWmsInbdSoText(wmsSoHdrTMsg, soTextList);
                        EZDTBLAccessor.insert(wmsSoTextTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoTextTMsg.getReturnCode())) {
                            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_TEXT, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
    
                            throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_TEXT, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                        }
                    }
    
                    // WMS_INBD_SO_SHIP_TO
                    WMS_INBD_SO_SHIP_TOTMsg wmsSoShipToTMsg = instance.createWmsInbdSoShipTo(wmsSoHdrTMsg, soHeader);
                    if (wmsSoShipToTMsg != null) {
                        EZDTBLAccessor.insert(wmsSoShipToTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoShipToTMsg.getReturnCode())) {
                            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
    
                            throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                        }
                    }
    
                    // WMS_INBD_SO_CHRG_TO
                    WMS_INBD_SO_CHRG_TOTMsg wmsSoChrgToTMsg = instance.createWmsInbdSoChrgTo(wmsSoHdrTMsg, soHeader);
                    if (wmsSoChrgToTMsg != null) {
                        EZDTBLAccessor.insert(wmsSoChrgToTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoChrgToTMsg.getReturnCode())) {
                            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
    
                            throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                        }
                    }
    
                    // WMS_INBD_BILL_TO
                    WMS_INBD_SO_BILL_TOTMsg wmsSoBillToTMsg = instance.createWmsInbdSoBillTo(wmsSoHdrTMsg, soHeader);
                    if (wmsSoBillToTMsg != null) {
                        EZDTBLAccessor.insert(wmsSoBillToTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoBillToTMsg.getReturnCode())) {
                            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_BILL_TO, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
    
                            throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_SO_BILL_TO, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                        }
                    }
                }
            }

            if (valid) {
                // Populate additional data
                Map<String, Object> carrSvcLvl = null;
                String tplCarrCd = (String) wmsSoHdr.get(NLBB061001Constant.COL_CARR_CD);
                String tplSvcLvlCd = (String) wmsSoHdr.get(NLBB061001Constant.COL_SHPG_SVC_LVL_CD);
                carrSvcLvl = getCarrSvcLvl(this.glblCmpyCd, this.trgtWhOwnrCd, tplCarrCd, tplSvcLvlCd);
                populateWmsSoHeaderDetail(wmsSoHdr, wmsSoDtlList, soHeader, soDetailList, carrSvcLvl);

                // Validation check
                // Mod Start 2019/11/26 QC#52804
//                valid = validateSoConfData(wmsSoHdr, wmsSoDtlList, trxId, unitId);
                valid = validateSoConfData(wmsSoHdr, wmsSoDtlList, ordNum, trxId, segmentId);
                // Mod End 2019/11/26 QC#52804
            }

            List<WMS_INBD_TRXTMsg> wmsTrxTMsgList = createWmsInbdTrx(wmsSoHdr, wmsSoDtlList);
            int count = S21FastTBLAccessor.insert(wmsTrxTMsgList.toArray(new WMS_INBD_TRXTMsg[wmsTrxTMsgList.size()]));
            if (count != wmsTrxTMsgList.size()) {
                outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_TRX, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });

                throw new S21AbendException(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.TBL_WMS_INBD_TRX, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
            }

            // QC#52077 Add Start
            commit();
            // QC#52077 Add End

            if (valid) {
                ++this.successCount;
            } else {
                ++this.errorCount;
            }
        }

        // Update the flag to processed in any case - success/fail -
        trxAccess.endIntegrationProcess(this.interfaceId, trxId);

    }

    //QC#20379 add method.
    private Map<String, Object> setTechTocCd(Map<String, Object> wmsSoHdr) {

        String tplEmpId = (String) wmsSoHdr.get(NLBB061001Constant.COL_TPL_EMP_ID);

        // TPL_EMP_ID is TECH_TOC_CD
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_TECH_TOC_CD, tplEmpId);

        String techTocCd = (String) this.ssmBatchClient.queryObject("getTechTocCd", queryParam, execParam);

        if (!ZYPCommonFunc.hasValue(techTocCd)) {
            // TPL_EMP_ID is TECH_LOC_CD
            queryParam.clear();
            queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(NLBB061001Constant.COL_TECH_LOC_CD, tplEmpId);

            techTocCd = (String) this.ssmBatchClient.queryObject("getTechTocCd", queryParam, execParam);
        }

        wmsSoHdr.put(NLBB061001Constant.COL_TECH_TOC_CD, techTocCd);

        return wmsSoHdr;
    }

    private List<Map<String, Object>> getWmsSoHeader(String ifId, BigDecimal tranId, String glblCmpyCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_INTERFACE_ID, ifId);
        queryParam.put(NLBB061001Constant.COL_TRANSACTION_ID, tranId);
        queryParam.put(NLBB061001Constant.COL_WMS_ORD_STS_CD, NLBB061001Constant.VAL_WMS_ORD_STS_CD_A);
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_RTL_SWH_DSBL_FLG, ZYPConstant.FLG_OFF_N);
        queryParam.put(NLBB061001Constant.COL_PRTY_LOC_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(NLBB061001Constant.BIND_SALES_DATE, this.salesDate);
        // QC#52894 Start
        queryParam.put(NLBB061001Constant.BIND_SHIP_CONF_STS_G, this.shipConfStsGTickets);
        queryParam.put(NLBB061001Constant.BIND_SHIP_CONF_STS_Z, this.shipConfStsZTickets);
        queryParam.put(NLBB061001Constant.BIND_G_TICKETS, NLBB061001Constant.G_TICKETS);
        queryParam.put(NLBB061001Constant.BIND_Z_TICKETS, NLBB061001Constant.Z_TICKETS);
        // QC#52894 End
        // START 2020/12/01 [QC#57659,ADD]
        queryParam.put(NLBB061001Constant.BIND_WH_OWNR_CD, "CHO");
        // END 2020/12/01 [QC#57659,ADD]

        return this.ssmBatchClient.queryObjectList("getWmsSoHeader", queryParam, execParam);
    }

    /**
     * getWmsSoDetail
     * @param ifaceId
     * @param tranId
     * @param unitId
     * @return
     */
    private List<Map<String, Object>> getWmsSoDetail(String ifId, BigDecimal tranId, BigDecimal segmentId, BigDecimal unitId, String glblCmpyCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_INTERFACE_ID, ifId);
        queryParam.put(NLBB061001Constant.COL_TRANSACTION_ID, tranId);
        queryParam.put(NLBB061001Constant.COL_SEGMENT_ID, segmentId);
        queryParam.put(NLBB061001Constant.COL_UNIT_ID, unitId);
        return this.ssmBatchClient.queryObjectList("getWmsSoDetail", queryParam, execParam);
    }

    /**
     * validateSoIFData
     * @param wmsSoHdr
     * @param wmsSoDtlList
     * @param wmsSoSerList
     * @return
     */
    // Mod Start 2019/11/26 QC#52804
//    private boolean validatePRUpdateAPIInputData(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, BigDecimal tranId, BigDecimal unitId) {
    private boolean validatePRUpdateAPIInputData(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, String ordNum, BigDecimal tranId, BigDecimal segmentId) {

        // Del Start 2019/11/26 QC#52804
//        String position = NLXCMsgHelper.toListedString(interfaceId, tranId.toPlainString(), unitId.toPlainString());
        // Del End 2019/11/26 QC#52804

        // Assume record has invalid data.
        // Mod Start 2019/11/26 QC#52804
//        setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0041E);
        setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0089E);

        if (wmsSoHdr.get(NLBB061001Constant.COL_TECH_TOC_CD) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_TECH_TOC_CD, NLBB061001Constant.TBL_TECH_LOC, position });
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_TECH_TOC_CD, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_INVTY_LOC_CD) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_INVTY_LOC_CD, NLBB061001Constant.TBL_RTL_SWH, position });
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_INVTY_LOC_CD, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_OTBD_ORD_NUM, NLBB061001Constant.TBL_NLBI1020_01, position });
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_OTBD_ORD_NUM, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
            return false;
        }

        for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {
//            BigDecimal seqNumber = (BigDecimal) wmsSoDtl.get(NLBB061001Constant.COL_SEQ_NUMBER);
//            position = NLXCMsgHelper.toListedString(interfaceId, tranId.toPlainString(), unitId, seqNumber);
            String mdseCd = (String) wmsSoDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT);
            String ordNumSegNumber = getConnectVal(ordNum, NLBB061001Constant.VAL_CONNECT_MDSE_CD, mdseCd);

            if (wmsSoDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT) == null) {
//                outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_ITEM_CD_TXT, NLBB061001Constant.TBL_NLBI1020_02, position });
                outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_ITEM_CD_TXT, ordNumSegNumber, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsSoDtl.get(NLBB061001Constant.COL_QTY_ORD_TXT) == null) {
//                outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_QTY_ORD_TXT, NLBB061001Constant.TBL_NLBI1020_02, position });
                outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_QTY_ORD_TXT, ordNumSegNumber, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
        }
        // Mod End 2019/11/26 QC#52804

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsSoHdr);

        return true;
    }
    // Mod End 2019/11/26 QC#52804

    private void setShipToAddr(NPZC103001PMsg pMsg, String shipToAddr) {
        pMsg.shipToFirstLineAddr.clear();
        pMsg.shipToScdLineAddr.clear();
        pMsg.shipToThirdLineAddr.clear();
        pMsg.shipToFrthLineAddr.clear();

        if (!ZYPCommonFunc.hasValue(shipToAddr)) {
            return;
        }

        EZDPStringItem[] items = new EZDPStringItem[] {
                pMsg.shipToFirstLineAddr,
                pMsg.shipToScdLineAddr,
                pMsg.shipToThirdLineAddr,
                pMsg.shipToFrthLineAddr
        };
        int first = 0;
        int last = 0;
        for (int n = 0; n < items.length && last < shipToAddr.length(); ++n, first = last) {
            last = first + NLBB061001Constant.ADDR_LENGTH;
            if (last > shipToAddr.length()) {
                last = shipToAddr.length();
            }
            ZYPEZDItemValueSetter.setValue(items[n], shipToAddr.substring(first, last));
        }
    }

    // QC#20379 Update method. 09/11/2017
    private NPZC103001PMsg executePRUpdateAPI(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, String glblCmpyCd, String whOwnrCd) {
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_TP_SHIP_CONF);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) wmsSoHdr.get(NLBB061001Constant.COL_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_NM_ALL_TXT));
        setShipToAddr(pMsg, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_ADDR_ALL_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_CTY_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_ST_OR_PROV_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_ZIP_OR_POST_CD_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_CTRY_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) wmsSoHdr.get(NLBB061001Constant.COL_SHIP_CTAC_NM_TXT));

        String carrCd = null;
        String shpgSvcLvlCd = null;
        String tplCarrCd = (String) wmsSoHdr.get(NLBB061001Constant.COL_CARR_CD);
        String tplShpgSvcLvlCd = (String) wmsSoHdr.get(NLBB061001Constant.COL_SHPG_SVC_LVL_CD);
        Map<String, Object> carrSvcLvl = getCarrSvcLvl(glblCmpyCd, whOwnrCd, tplCarrCd, tplShpgSvcLvlCd);
        if (carrSvcLvl != null) {
            carrCd = (String) carrSvcLvl.get(NLBB061001Constant.COL_CARR_CD);
            shpgSvcLvlCd = (String) carrSvcLvl.get(NLBB061001Constant.COL_SHPG_SVC_LVL_CD);
        }

        String srcInvtyLocCd = (String) wmsSoHdr.get(NLBB061001Constant.COL_INVTY_LOC_CD);

        String prchReqTpCd=null;
        String delyDate=null;
        String delyTime=null;
        Map<String, Object> delyInfo=getDeliveryInfo(glblCmpyCd, shpgSvcLvlCd);
        if(delyInfo!=null) {
            prchReqTpCd=(String)delyInfo.get(NLBB061001Constant.COL_PRCH_REQ_TP_CD);
            delyDate=(String)delyInfo.get(NLBB061001Constant.COL_DELY_DATE);
            delyTime=(String)delyInfo.get(NLBB061001Constant.COL_DELY_TIME);
        }
        if(!ZYPCommonFunc.hasValue(delyDate)) {
            delyDate=this.salesDate;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, prchReqTpCd);

        NPZC103001_prchReqInfoPMsgArray reqInfoPMsgArray = pMsg.prchReqInfo;
        for (int pos = 0; pos < wmsSoDtlList.size(); ++pos) {
            Map<String, Object> wmsSoDtl = wmsSoDtlList.get(pos);
            NPZC103001_prchReqInfoPMsg reqInfoPMsg = reqInfoPMsgArray.no(pos);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.carrCd, carrCd);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.shpgSvcLvlCd, shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.srcInvtyLocCd, srcInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.mdseCd, (String) wmsSoDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT));
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.prchReqQty, new BigDecimal((String) wmsSoDtl.get(NLBB061001Constant.COL_QTY_ORD_TXT)));
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.proNum, (String) wmsSoHdr.get(NLBB061001Constant.COL_WMS_JOB_ID));
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.trxRefNum, (String) wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.rqstRcvDt, delyDate);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.rqstRcvTm, delyTime);
        }
        reqInfoPMsgArray.setValidCount(wmsSoDtlList.size());

        NPZC103001 prUpdateApi = new NPZC103001();
        prUpdateApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        return pMsg;
    }

    // QC#20379 09/11/2017 Add method.
    private Map<String, Object> getDeliveryInfo(String glblCmpyCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        if(!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            return null;
        }
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        queryParam.put(NLBB061001Constant.COL_DS_COND_CONST_GRP_ID, NLBB061001Constant.VAL_DS_COND_CONST_GRP_ID);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDeliveryInfo", queryParam, execParam);

        if (result == null) {
            return null;
        }

        // Populate delivery date/time.
        String delyDays = (String) result.get(NLBB061001Constant.COL_DELY_DAYS);
        String delyTm = (String) result.get(NLBB061001Constant.COL_DELY_TM);
        String delyHours = (String) result.get(NLBB061001Constant.COL_DELY_HOURS);

        Date delyDtTm = null;
        if (ZYPCommonFunc.hasValue(delyTm)) {
            // RUSH:Delivery time is specified. Add transport days and
            // set arrival time.
            String delyDate = this.salesDate;
            if (ZYPCommonFunc.hasValue(delyDays)) {
                delyDate = ZYPDateUtil.addDays(delyDate, Integer.parseInt(delyDays));
            }
            // Convert
            int year = Integer.parseInt(delyDate.substring(0, 4));
            int month = Integer.parseInt(delyDate.substring(4, 6)) - 1;
            int date = Integer.parseInt(delyDate.substring(6, 8));
            // delyTm format => hh:mm a
            int hourOfDay = Integer.parseInt(delyTm.substring(0, 2));
            if (delyTm.contains("PM")) {
                hourOfDay += 12;
            }
            int minute = Integer.parseInt(delyTm.substring(3, 5));

            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(year, month, date, hourOfDay, minute);

            delyDtTm = cal.getTime();
        } else {
            // PREMIUM RUSH:Delivery time is not specified. Add
            // transport hours and calculate arrival time.
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            if (ZYPCommonFunc.hasValue(delyHours)) {
                int addHours = Integer.parseInt(delyHours);
                cal.add(Calendar.HOUR_OF_DAY, addHours);
            }
            int minute = cal.get(Calendar.MINUTE);
            if (minute <= 30) {
                cal.set(Calendar.MINUTE, 30);
            } else {
                cal.add(Calendar.HOUR_OF_DAY, 1);
                cal.set(Calendar.MINUTE, 0);
            }
            delyDtTm = cal.getTime();
        }

        // Format delivery date/time into string.
        DateFormat dfDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
        result.put(NLBB061001Constant.COL_DELY_DATE, dfDate.format(delyDtTm));
        DateFormat dfTime = new SimpleDateFormat("HHmm", Locale.US);
        result.put(NLBB061001Constant.COL_DELY_TIME, dfTime.format(delyDtTm));

        return result;
    }

    // Mod Start 2019/11/26 QC#52804
//    private boolean validatePRUpdateAPIResult(Map<String, Object> wmsSoHdr, NPZC103001PMsg pMsg, BigDecimal tranId, BigDecimal unitId) {
    private boolean validatePRUpdateAPIResult(Map<String, Object> wmsSoHdr, NPZC103001PMsg pMsg, BigDecimal tranId, BigDecimal segmentId, BigDecimal unitId) {

        String positionKey=NLXCMsgHelper.toListedString(NLBB061001Constant.COL_INTERFACE_ID, NLBB061001Constant.COL_TRANSACTION_ID, NLBB061001Constant.COL_UNIT_ID);
        String position = NLXCMsgHelper.toListedString(interfaceId, tranId.toPlainString(), unitId);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            if(msgIdList.size()>0) {
                setIntfcProcStsAsError(wmsSoHdr, msgIdList.get(0));
            }
            // QC#52509 Add Start
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            int i=0;
            for (S21ApiMessage s21ApiMsg : msgList) {
                String msgId = s21ApiMsg.getXxMsgid();
                if (pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0 != null && pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1 != null) {
                    String prNum = pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();
                    String mdseCd = pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue();
                    if (ZYPCommonFunc.hasValue(prNum) && ZYPCommonFunc.hasValue(mdseCd)) {
                        String msg = S21MessageFunc.clspGetMessage(msgId);
                        // Mod Start 2019/11/26 QC#52804
                        String tranIdSegId = getConnectVal(tranId.toPlainString(), NLBB061001Constant.VAL_CONNECT_SEGMENT_ID, segmentId.toPlainString());
//                        outputErr( NLBB061001Constant.NLBM1374E, new String[] {msg, prNum, mdseCd, interfaceId, tranId.toPlainString()});
                        outputErr( NLBB061001Constant.NLBM1374E, new String[] {msg, prNum, mdseCd, this.interfaceId, tranIdSegId});
                        // Mod End 2019/11/26 QC#52804
                    } else {
                        String msg = S21MessageFunc.clspGetMessage(msgId);
                        // Mod Start 2019/11/26 QC#52804
//                        outputErr( NLBB061001Constant.NLBM1373E, new String[] {msg, (String) wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM), interfaceId, tranId.toPlainString(), unitId.toPlainString()});
                        outputErr( NLBB061001Constant.NLBM1373E, new String[] {msg, (String) wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM), this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
                        // Mod End 2019/11/26 QC#52804
                    }
                }
                i++;
            }
            // QC#52509 Add End
            // QC#52509 Del Start
//            for (String msgId : msgIdList) {
//                // QC#52077 Mod Start
//                //outputErr(msgId, new String[] {position});
//                String msg = S21MessageFunc.clspGetMessage(msgId);
//                outputErr( NLBB061001Constant.NLBM1373E, new String[] {msg, (String) wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM), interfaceId, tranId.toPlainString(), unitId.toPlainString()});
//                // QC#52077 Mod End
//            }
            // QC#52509 Del End
            return false;
        }

        // Assume record has invalid data.
        setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0045E);

        NPZC103001_prchReqInfoPMsgArray reqInfoPMsgArray = pMsg.prchReqInfo;
        if (reqInfoPMsgArray.getValidCount() <= 0) {
            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.VAL_NPZC103001, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
            return false;
        }
        String soNum = reqInfoPMsgArray.no(0).soNum.getValue();
        if (!ZYPCommonFunc.hasValue(soNum)) {
            outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.VAL_NPZC103001, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
            return false;
        }
        for (int pos = 1; pos < reqInfoPMsgArray.getValidCount(); ++pos) {
            String other = reqInfoPMsgArray.no(pos).soNum.getValue();
            if (!soNum.equals(other)) {
                outputErr(NLBB061001Constant.NLGM0045E, new String[] {NLBB061001Constant.VAL_NPZC103001, NLBB061001Constant.TBL_NLBI1020_01, positionKey, position });
                return false;
            }
        }

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsSoHdr);

        return true;
    }
    // Mod End 2019/11/26 QC#52804

    /**
     * 
     * @param glblCmpyCd
     * @param soNum
     * @return
     */
    private Map<String, Object> getSoHeader(String glblCmpyCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_SO_NUM, soNum);
        queryParam.put(NLBB061001Constant.COL_WH_SYS_TP_CD, WH_SYS_TP._3PL);
        queryParam.put(NLBB061001Constant.COL_WH_OWNR_CD, this.trgtWhOwnrCd);
        queryParam.put(NLBB061001Constant.COL_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
        queryParam.put(NLBB061001Constant.BIND_SO_CUST_DATA_TP_CD_SHIP, SO_CUST_DATA_TP.SHIP_TO);
        queryParam.put(NLBB061001Constant.BIND_SO_CUST_DATA_TP_CD_SELL, SO_CUST_DATA_TP.SELL_TO);
        queryParam.put(NLBB061001Constant.BIND_SO_CUST_DATA_TP_CD_BILL, SO_CUST_DATA_TP.BILL_TO);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSoHeader", queryParam, execParam);
    }

    /**
     * 
     * @param glblCmpyCd
     * @param soNum
     * @return
     */
    private List<Map<String, Object>> getSoDetailList(String glblCmpyCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_SO_NUM, soNum);
    
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSoDetail", queryParam, execParam);
    }

    private List<Map<String, Object>> getSoTextList(String glblCmpyCd, String soNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_SO_NUM, soNum);
        queryParam.put(NLBB061001Constant.COL_SO_MSG_TP_CD, SHPG_ORD_MSG_TP.CPO_INFORMATION);
        List soTxtList = this.ssmBatchClient.queryObjectList("getSoText", queryParam);
        return soTxtList;
    }

    private boolean isShipFromColumbus(Map<String, Object> wmsSoHdr) {
        String rtlWhCd = (String) wmsSoHdr.get(NLBB061001Constant.COL_TPL_LOC_CD);
        return rtlWhCdColumbus.equals(rtlWhCd);
    }

    private Map<String, Object> getCarrSvcLvl(String glblCmpyCd, String whOwnrCd, String tplCarrCd, String tplShpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB061001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB061001Constant.COL_WH_OWNR_CD, whOwnrCd);
        queryParam.put(NLBB061001Constant.COL_TPL_CARR_CD, tplCarrCd);
        queryParam.put(NLBB061001Constant.COL_TPL_SVC_LVL_CD, tplShpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getCarrSvcLvl", queryParam, execParam);
    }

    private void populateWmsSoHeaderDetail(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, Map<String, Object> soHdr, List<Map<String, Object>> soDtlList, Map<String, Object> carrSvcLvl) {

        wmsSoHdr.put(NLBB061001Constant.COL_GLBL_CMPY_CD, (String)soHdr.get(NLBB061001Constant.COL_GLBL_CMPY_CD));
        wmsSoHdr.put(NLBB061001Constant.COL_SO_NUM, (String)soHdr.get(NLBB061001Constant.COL_SO_NUM));
        wmsSoHdr.put(NLBB061001Constant.COL_WH_CD, (String)soHdr.get(NLBB061001Constant.COL_WH_CD));
        wmsSoHdr.put(NLBB061001Constant.COL_S80_ORD_TP_CD, (String)soHdr.get(NLBB061001Constant.COL_S80_ORD_TP_CD));
        wmsSoHdr.put(NLBB061001Constant.COL_INVTY_OWNR_CD, (String)soHdr.get(NLBB061001Constant.COL_INVTY_OWNR_CD));
        wmsSoHdr.put(NLBB061001Constant.COL_WMS_ORG_HOST_ID, (String)soHdr.get(NLBB061001Constant.COL_WMS_ORG_HOST_ID));

        String tplCarrSvcLvlCd = null;
        String carrCd = null;
        String shpgSvcLvlCd = null;
        if (carrSvcLvl != null) {
            tplCarrSvcLvlCd = (String) carrSvcLvl.get(NLBB061001Constant.COL_TPL_CARR_SVC_LVL_CD);
            carrCd = (String) carrSvcLvl.get(NLBB061001Constant.COL_CARR_CD);
            shpgSvcLvlCd = (String) carrSvcLvl.get(NLBB061001Constant.COL_SHPG_SVC_LVL_CD);
        }
        wmsSoHdr.put(NLBB061001Constant.COL_TPL_CARR_SVC_LVL_CD, tplCarrSvcLvlCd);
        wmsSoHdr.put(NLBB061001Constant.COL_CARR_CD, carrCd);
        wmsSoHdr.put(NLBB061001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);

        ArrayList<Map<String, Object>> wmsExtraDtlList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {
            String wmsMdseCd = (String) wmsSoDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT);
            if (!ZYPCommonFunc.hasValue(wmsMdseCd)) {
                continue;
            }

            // SO_SLP_NUM is not in the response from Choice, and
            // response MIGHT have same MDSE in its details.
            // Try mapping SO_SLP_NUM considering shipped qty with the
            // lines in reponse.

            // Shipped qty from Choice
            BigDecimal wmsShipQty = new BigDecimal((String) wmsSoDtl.get(NLBB061001Constant.COL_QTY_ORD_TXT));

            // TRY1: Search the line that mateches a qty - fully
            // received items.
            for (int n = 0; BigDecimal.ZERO.compareTo(wmsShipQty) < 0 && n < soDtlList.size(); ++n) {
                Map<String, Object> soDtl = soDtlList.get(n);
                String soMdseCd = (String) soDtl.get(NLBB061001Constant.COL_MDSE_CD);
                BigDecimal soUnshipQty = (BigDecimal) soDtl.get(NLBB061001Constant.COL_UNSHIP_QTY);
                if (wmsMdseCd.equals(soMdseCd) && wmsShipQty.compareTo(soUnshipQty) == 0) {
                    wmsSoDtl.put(NLBB061001Constant.COL_SO_SLP_NUM, soDtl.get(NLBB061001Constant.COL_SO_SLP_NUM));
                    wmsSoDtl.put(NLBB061001Constant.COL_RTL_SWH_CD, soDtl.get(NLBB061001Constant.COL_RTL_SWH_CD));
                    wmsSoDtl.put(NLBB061001Constant.COL_FROM_STK_STS_CD, soDtl.get(NLBB061001Constant.COL_FROM_STK_STS_CD));
                    soUnshipQty = BigDecimal.ZERO;
                    soDtl.put(NLBB061001Constant.COL_UNSHIP_QTY, soUnshipQty);
                    wmsShipQty = BigDecimal.ZERO;
                }
            }

            // TRY2: Search the line that soUnshipQty is greater than
            // wmsQty - partially received items.
            for (int n = 0; BigDecimal.ZERO.compareTo(wmsShipQty) < 0 && n < soDtlList.size(); ++n) {
                Map<String, Object> soDtl = soDtlList.get(n);
                String soMdseCd = (String) soDtl.get(NLBB061001Constant.COL_MDSE_CD);
                BigDecimal soUnshipQty = (BigDecimal) soDtl.get(NLBB061001Constant.COL_UNSHIP_QTY);
                if (wmsMdseCd.equals(soMdseCd) && wmsShipQty.compareTo(soUnshipQty) < 0) {
                    wmsSoDtl.put(NLBB061001Constant.COL_SO_SLP_NUM, soDtl.get(NLBB061001Constant.COL_SO_SLP_NUM));
                    wmsSoDtl.put(NLBB061001Constant.COL_RTL_SWH_CD, soDtl.get(NLBB061001Constant.COL_RTL_SWH_CD));
                    wmsSoDtl.put(NLBB061001Constant.COL_FROM_STK_STS_CD, soDtl.get(NLBB061001Constant.COL_FROM_STK_STS_CD));
                    soUnshipQty = soUnshipQty.subtract(wmsShipQty);
                    soDtl.put(NLBB061001Constant.COL_UNSHIP_QTY, soUnshipQty);
                    wmsShipQty = BigDecimal.ZERO;
                }
            }

            // TRY3: Substract qty that mateches mdseCd - received
            // items in bulk.
            ArrayList<Map<String, Object>> wmsAddedDtlList = new ArrayList<Map<String, Object>>();
            for (int n = 0; BigDecimal.ZERO.compareTo(wmsShipQty) < 0 && n < soDtlList.size(); ++n) {
                Map<String, Object> soDtl = soDtlList.get(n);
                String soMdseCd = (String) soDtl.get(NLBB061001Constant.COL_MDSE_CD);
                BigDecimal soUnshipQty = (BigDecimal) soDtl.get(NLBB061001Constant.COL_UNSHIP_QTY);
                if (wmsMdseCd.equals(soMdseCd) && BigDecimal.ZERO.compareTo(soUnshipQty) < 0) {
                    HashMap<String, Object> wmsAddedDtl = new HashMap<String, Object>(wmsSoDtl);
                    wmsAddedDtl.put(NLBB061001Constant.COL_SO_SLP_NUM, soDtl.get(NLBB061001Constant.COL_SO_SLP_NUM));
                    wmsAddedDtl.put(NLBB061001Constant.COL_RTL_SWH_CD, soDtl.get(NLBB061001Constant.COL_RTL_SWH_CD));
                    wmsAddedDtl.put(NLBB061001Constant.COL_FROM_STK_STS_CD, soDtl.get(NLBB061001Constant.COL_FROM_STK_STS_CD));
                    BigDecimal minQty = wmsShipQty.min(soUnshipQty);
                    wmsAddedDtl.put(NLBB061001Constant.COL_QTY_ORD_TXT, minQty.toPlainString());
                    wmsShipQty = wmsShipQty.subtract(minQty);
                    wmsAddedDtlList.add(wmsAddedDtl);
                    soUnshipQty = soUnshipQty.subtract(minQty);
                    soDtl.put(NLBB061001Constant.COL_UNSHIP_QTY, soUnshipQty);
                }
            }
            // Fix details
            if (wmsAddedDtlList.size() > 0) {
                Map<String, Object> wmsAddedDtl = wmsAddedDtlList.get(0);
                wmsSoDtl.clear();
                wmsSoDtl.putAll(wmsAddedDtl);
                wmsAddedDtlList.remove(0);
                wmsExtraDtlList.addAll(wmsAddedDtlList);
            }
        }
        // Merge extra details
        wmsSoDtlList.addAll(wmsExtraDtlList);
    }

    // Mod Start 2019/11/26 QC#52804
//    private boolean validateSoConfData(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, BigDecimal tranId, BigDecimal unitId) {
    private boolean validateSoConfData(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, String ordNum, BigDecimal tranId, BigDecimal segmentId) {

        // Del Start 2019/11/26 QC#52804
//        String position = NLXCMsgHelper.toListedString(interfaceId, tranId.toPlainString(), unitId);
        // Del End 2019/11/26 QC#52804

        // Assume record has invalid data.
        // Mod Start 2019/11/26 QC#52804
//        setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0041E);
        setIntfcProcStsAsError(wmsSoHdr, NLBB061001Constant.NLGM0089E);

        if (wmsSoHdr.get(NLBB061001Constant.COL_TPL_LOC_CD) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_TPL_LOC_CD, NLBB061001Constant.TBL_TPL_LOC, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_TPL_LOC_CD, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_SEQ_NUMBER) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_SEQ_NUMBER, NLBB061001Constant.TBL_NLBI1020_01, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_SEQ_NUMBER, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_OTBD_ORD_NUM) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_OTBD_ORD_NUM, NLBB061001Constant.TBL_NLBI1020_01, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_OTBD_ORD_NUM, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_S80_ORD_TP_CD) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_S80_ORD_TP_CD, NLBB061001Constant.TBL_SCE_ORD_TP, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_S80_ORD_TP_CD, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_REQ_DT_TM_TS_TXT) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_REQ_DT_TM_TS_TXT, NLBB061001Constant.TBL_NLBI1020_01, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_REQ_DT_TM_TS_TXT, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_RTL_SWH_CD) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_RTL_SWH_CD, NLBB061001Constant.TBL_RTL_SWH, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_RTL_SWH_CD, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }
        if (wmsSoHdr.get(NLBB061001Constant.COL_WMS_REC_ID) == null) {
//            outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_WMS_REC_ID, NLBB061001Constant.TBL_NLBI1020_01, position});
            outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_WMS_REC_ID, ordNum, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
            return false;
        }

        for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {

//            BigDecimal seqNumber = (BigDecimal) wmsSoDtl.get(NLBB061001Constant.COL_SEQ_NUMBER);
//            position = NLXCMsgHelper.toListedString(interfaceId, tranId.toPlainString(), unitId, seqNumber);
            String mdseCd = (String) wmsSoDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT);
            String ordNumSegNumber = getConnectVal(ordNum, NLBB061001Constant.VAL_CONNECT_MDSE_CD, mdseCd);

            if (wmsSoDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT) == null) {
//                outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_ITEM_CD_TXT, NLBB061001Constant.TBL_NLBI1020_02, position});
                outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_ITEM_CD_TXT, ordNumSegNumber, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
                return false;
            }
            if (wmsSoDtl.get(NLBB061001Constant.COL_QTY_ORD_TXT) == null) {
//                outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_QTY_ORD_TXT, NLBB061001Constant.TBL_NLBI1020_02, position});
                outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_QTY_ORD_TXT, ordNumSegNumber, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
                return false;
            }
            if (wmsSoDtl.get(NLBB061001Constant.COL_SO_SLP_NUM) == null) {
//                outputErr(NLBB061001Constant.NLGM0041E, new String[] {NLBB061001Constant.COL_SO_SLP_NUM, NLBB061001Constant.TBL_SHPG_ORD_DTL, position});
                outputErr(NLBB061001Constant.NLGM0089E, new String[] {NLBB061001Constant.COL_SO_SLP_NUM, ordNumSegNumber, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString()});
                return false;
            }
        }
        // Mod End 2019/11/26 QC#52804

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsSoHdr);

        return true;
    }
    // Mod End 2019/11/26 QC#52804

    private void setIntfcProcStsAsSuccess(Map<String, Object> wmsSoHdr) {
        wmsSoHdr.put(NLBB061001Constant.COL_INTFC_PROC_STS_CD, NLBB061001Constant.VAL_INTFC_PROC_STS_CD_SUCCESS);
        wmsSoHdr.put(NLBB061001Constant.COL_INTFC_ERR_MSG_CD, null);
    }
    
    private void setIntfcProcStsAsError(Map<String, Object> wmsSoHdr, String msgId) {
        wmsSoHdr.put(NLBB061001Constant.COL_INTFC_PROC_STS_CD, NLBB061001Constant.VAL_INTFC_PROC_STS_CD_ERROR);
        wmsSoHdr.put(NLBB061001Constant.COL_INTFC_ERR_MSG_CD, msgId);
    }
    
    /**
     * createWmsInbdTrx
     * @param wmsHdr
     * @param wmsDtl
     * @return int
     */
    private List<WMS_INBD_TRXTMsg> createWmsInbdTrx(Map<String, Object> wmsHdr, List<Map<String, Object>> wmsDtlList) {

        ArrayList<WMS_INBD_TRXTMsg> tMsgList = new ArrayList<WMS_INBD_TRXTMsg>();

        // GLBL_CMPY_CD
        String glblCmpyCd = this.glblCmpyCd;

        // TRANSACTION_ID
        String trxId = objNullToString(wmsHdr.get(NLBB061001Constant.COL_TRANSACTION_ID));
        trxId = ZYPCommonFunc.leftPad(trxId, 30, NLBB061001Constant.VAL_ZERO);

        // UNIT_ID
        String unitId = objNullToString(wmsHdr.get(NLBB061001Constant.COL_UNIT_ID));
        unitId = ZYPCommonFunc.leftPad(unitId, 30, NLBB061001Constant.VAL_ZERO);

        // INTFC_PROC_STS_CD
        String intfcProcStsCd = (String) wmsHdr.get(NLBB061001Constant.COL_INTFC_PROC_STS_CD);

        // INTFC_ERR_MSG_CD
        String intfcErrMsgCd = (String) wmsHdr.get(NLBB061001Constant.COL_INTFC_ERR_MSG_CD);

        // WMS_PKG_CD = INVTY_OWNR_CD+RTL_SWH_CD;
        String invtyOwnrCd = objNullToString(wmsHdr.get(NLBB061001Constant.COL_INVTY_OWNR_CD));

        // REQ_DT_TM_TS
        String reqDtTmTs = (String) wmsHdr.get(NLBB061001Constant.COL_REQ_DT_TM_TS_TXT);
        reqDtTmTs = adjustString(reqDtTmTs, 14);

        // INTFC_TP_ID
        String intfcTpId = this.intfcTpId;

        for (Map<String, Object> wmsDtl : wmsDtlList) {
            WMS_INBD_TRXTMsg tMsg = new WMS_INBD_TRXTMsg();

            // WMS_INBD_TRX_PK
            BigDecimal wmsInbdTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);

            // WMS_PKG_CD = INVTY_OWNR_CD+RTL_SWH_CD;
            String wmsPkgCd = (String) wmsHdr.get(NLBB061001Constant.COL_INVTY_LOC_CD);
            if (ZYPConstant.FLG_ON_Y.equals(this.wmsPackCdMode)) {
                String rtlSwhCd = objNullToString(wmsDtl.get(NLBB061001Constant.COL_RTL_SWH_CD));
                wmsPkgCd = invtyOwnrCd + rtlSwhCd;
            }

            // WMS_ORD_QTY
            BigDecimal wmsOrdQty = null;
            String qtyOrdTxt = (String) wmsDtl.get(NLBB061001Constant.COL_QTY_ORD_TXT);
            if (ZYPCommonFunc.hasValue(qtyOrdTxt)) {
                wmsOrdQty = new BigDecimal(qtyOrdTxt);
            }

            // OTBD_ORD_LINE_NUM
            BigDecimal otbdOrdLineNum = null;
            String soSlpNum = (String) wmsDtl.get(NLBB061001Constant.COL_SO_SLP_NUM);
            if (ZYPCommonFunc.hasValue(soSlpNum)) {
                otbdOrdLineNum = new BigDecimal(soSlpNum);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInbdTrxPk, wmsInbdTrxPk);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, (String) wmsHdr.get(NLBB061001Constant.COL_WMS_REC_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) wmsHdr.get(NLBB061001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTaskCd, WMS_TASK.SHIP);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcId, (String) wmsHdr.get(NLBB061001Constant.COL_INTERFACE_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRcvTs, (String) wmsHdr.get(NLBB061001Constant.COL_EZINTIME));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxSqNum, unitId);
            ZYPEZDItemValueSetter.setValue(tMsg.wrkTrxId, NLBB061001Constant.VAL_WRK_TRX_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcProcStsCd, intfcProcStsCd);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcErrMsgCd, intfcErrMsgCd);
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, NLBB061001Constant.VAL_PROC_STS_CD);
            // ZYPEZDItemValueSetter.setValue(tMsg.errMsgCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsStsCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsRsnCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) wmsDtl.get(NLBB061001Constant.COL_ITEM_CD_TXT));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsPkgCd, wmsPkgCd);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsTagId, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsStkStsCd, (String) wmsDtl.get(NLBB061001Constant.COL_FROM_STK_STS_CD));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsOldStkStsCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsOrgQty, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdQty, wmsOrdQty);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsProcQty, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsOpId, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsLotNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.usrId_01, );
            // ZYPEZDItemValueSetter.setValue(tMsg.usrId_02, );
            // ZYPEZDItemValueSetter.setValue(tMsg.usrId_03, );
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdNum, (String) wmsHdr.get(NLBB061001Constant.COL_SO_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdTpCd, (String) wmsHdr.get(NLBB061001Constant.COL_S80_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdLineNum, otbdOrdLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdStsCd, WMS_ORD_STS.SHIP);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsRcptDtTmTs, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipDtTmTs, reqDtTmTs);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxDtTmTs, reqDtTmTs);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsWaveId, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsTotPieceNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTotWt, BigDecimal.ZERO);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsTotVol, );
            // ZYPEZDItemValueSetter.setValue(tMsg.inbdOrdNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.inbdOrdTpCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.inbdOrdLineNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCarrCd, (String) wmsHdr.get(NLBB061001Constant.COL_TPL_CARR_SVC_LVL_CD));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsTrailId, );
            ZYPEZDItemValueSetter.setValue(tMsg.proNum, (String) wmsHdr.get(NLBB061001Constant.COL_WMS_JOB_ID));
            // ZYPEZDItemValueSetter.setValue(tMsg.bolNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsMnifNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsGrpId_01, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsGrpId_02, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsGrpId_03, );
            // ZYPEZDItemValueSetter.setValue(tMsg.whSrchId, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipId, (String) wmsHdr.get(NLBB061001Constant.COL_SO_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsFrtTermCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.modByResrcTxt, );
            // ZYPEZDItemValueSetter.setValue(tMsg.modByTxt, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsModCnt, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsDataCratDtTmTs, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsDataModDtTmTs, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsCntnrId, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOutCntnrNum, (String) wmsHdr.get(NLBB061001Constant.COL_SO_NUM));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsExpdQty, );
            // ZYPEZDItemValueSetter.setValue(tMsg.rcvToDateQty, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipToScdLineAddr, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipToStNm, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsAwbNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsLocId, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsRcvLocId, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsSplyNm, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsInvNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsFrtChrgAmt, BigDecimal.ZERO);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsFrtId, );
            // ZYPEZDItemValueSetter.setValue(tMsg.serNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsRcptId, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsRtnDtTmTs, );
            // ZYPEZDItemValueSetter.setValue(tMsg.shipUnitWt, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsRcvNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsLineSqNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsCntnrNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsAsnNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.estDockDtTmTs, );
            // ZYPEZDItemValueSetter.setValue(tMsg.tmsFrtChrgAmt, );
            // ZYPEZDItemValueSetter.setValue(tMsg.tmsFrtChrgConstAmt, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsBatId, ZYPDateUtil.getCurrentSystemTime(NLBB061001Constant.FMT_YYYYMMDDHHMMSS));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, intfcTpId);
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsResrcTxt, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrgHostId, (String) wmsHdr.get(NLBB061001Constant.COL_WMS_ORG_HOST_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsUpdHistNum, BigDecimal.ZERO);
            // ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, );
            // ZYPEZDItemValueSetter.setValue(tMsg.tmsTrkNum, );
            // QC#29924
            ZYPEZDItemValueSetter.setValue(tMsg.tmsTrkNum, tMsg.proNum);

            tMsgList.add(tMsg);
        }

        return tMsgList;
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

    private String adjustString(String val, int len) {
        if (val == null) {
            return null;
        }

        if (val.length() > len) {
            val = val.substring(0, len);
        }

        return val;
    }

    private BigDecimal adjustBigDecimal(BigDecimal val, int precision, int scale) {
        if (val == null) {
            return null;
        }

        final BigDecimal MAX_VALUE = BigDecimal.ONE.movePointRight(precision).subtract(BigDecimal.ONE).movePointLeft(scale);
        final BigDecimal MIN_VALUE = MAX_VALUE.negate();
        if (MAX_VALUE.compareTo(val) < 0) {
            return MAX_VALUE;
        }
        if (MIN_VALUE.compareTo(val) > 0) {
            return MIN_VALUE;
        }

        return val.setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    // Add Start 2019/11/26 QC#52804
    private String getConnectVal(String origVal, String connectVal, String addVal) {

        StringBuilder sb = new StringBuilder();
        sb.append(origVal);
        sb.append(connectVal);
        sb.append(addVal);

        return sb.toString();
    }
    // Add End 2019/11/26 QC#52804
}
