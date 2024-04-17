/**
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB091001;

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
import business.parts.NPZC103001PMsg;
import business.parts.NPZC103001_prchReqInfoPMsg;
import business.parts.NPZC103001_prchReqInfoPMsgArray;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.batch.NLB.NLBB090001.NLBB090001;
import com.canon.cusa.s21.batch.NLB.NLBB090001.constant.NLBB090001Constant;
import com.canon.cusa.s21.batch.NLB.NLBB091001.constant.NLBB091001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
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
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Batch Program Class for Ship Order Confirmation from MNX
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2020   CITS            H.Dimay         Create          QC#57659
 * 11/14/2020   CITS            K.Ogino         Update          QC#57659
 * 11/20/2020   CITS            J.Evangelista   Update          QC#57659
 * 11/28/2020   CITS            M.Furugoori     Update          QC#57659
 * 12/05/2020   CITS            K.Ogino         Update          QC#57659-1
 * 12/16/2020   CITS            K.Ogino         Update          QC#57659-3
 * 02/05/2021   CITS            K.Ogino         Update          QC#58135
 * 03/02/2021   CITS            K.Ogino         Update          QC#58135-1
 * 03/23/2021   CITS            J.Evangelista   Update          QC#58577
 * 05/27/2021   CITS            J.Evangelista   Update          QC#58845
 * 07/01/2021   CITS            J.Evangelista   Update          QC#58876
 * 08/18/2021   CITS            K.Ogino         Update          QC#58876-1
 * 10/11/2021   CITS            K.Ogino         Update          QC#58876-2
 * 08/17/2022   CITS            A.Cullano       Update          QC#60423
 *</pre>
 */
public class NLBB091001 extends S21BatchMain {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());


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
    private String wmsPackCdMode;

    /** SalesDate */
    private String salesDate = null;

    /**
     * Startups.
     * @param args arguments
     */
    public static void main(String[] args) {

        new NLBB091001().executeBatch(NLBB091001.class.getSimpleName());
    }

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

        // Get current sales date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Aquiring mode for WMS_PACK_CD. Normally "Y"
        this.wmsPackCdMode = ZYPCodeDataUtil.getVarCharConstValue(NLBB091001Constant.VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG, glblCmpyCd);

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

                //target record header information extraction
                createSoConfTrxRecords(trxId);
            }
        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                this.termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB091001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {
        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(NLBB091001Constant.ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount)});

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);
    }

    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLBB091001Constant.NLGM0049E, new String[] {NLBB091001Constant.PARAM_NM_GLBL_CMPY_CD });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB091001Constant.NLGM0049E, new String[] {NLBB091001Constant.PARAM_NM_INTERFACE_ID });
        }
    }

    private void createSoConfTrxRecords(BigDecimal trxId) {

        List<Map<String, Object>> wmsSoHdrList = getWmsSoHeader(this.interfaceId, trxId, glblCmpyCd);

        boolean valid = true;
        // Loop by header
        for (Map<String, Object> wmsSoHdr : wmsSoHdrList) {
            boolean soCreated = false;
            // QC#57659-1
            List<String> soSlpNumList = new ArrayList<String>();

            BigDecimal unitId = (BigDecimal) wmsSoHdr.get(NLBB091001Constant.COL_UNIT_ID);
            BigDecimal segmentId = (BigDecimal) wmsSoHdr.get(NLBB091001Constant.COL_SEGMENT_ID);
            String soNum = (String) wmsSoHdr.get(NLBB091001Constant.COL_SO_NUM);
            //Get TECH_TOC_CD
            wmsSoHdr = setTechTocCd(wmsSoHdr);

            // Get WMS [SO] Details
            List<Map<String, Object>> wmsSoDtlList = getWmsSoDetail(this.interfaceId, trxId, segmentId, unitId, glblCmpyCd);
            // No line record
            if (wmsSoDtlList == null || wmsSoDtlList.isEmpty()) {
                continue;
            }

            // START 2020/11/28 [QC#57659,MOD]
            String cpltLclTxt = (String) wmsSoHdr.get(NLBB091001Constant.COL_CPLT_LCL_TXT);
            String shipTaskId = (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_TASK_ID);
            String trackingNum = (String) wmsSoHdr.get(NLBB091001Constant.COL_WMS_JOB_ID);
            String whCd = (String) wmsSoHdr.get(NLBB091001Constant.COL_TPL_LOC_TXT);
            // QC#58135 Add
            String wmsTaskCd = (String) wmsSoHdr.get(NLBB091001Constant.COL_WMS_TASK_CD);

            valid = true;
            // QC#58135 Mod
            if (!ZYPCommonFunc.hasValue(soNum) //
                    && ZYPCommonFunc.hasValue(cpltLclTxt) //
                    && ZYPCommonFunc.hasValue(shipTaskId) //
                    && WMS_TASK.SHIP.equals(wmsTaskCd)) {
                // && "PICKUP".equals(shipTaskId)) {
                // QC#57659-3 Mod End

                // START 2021/03/23 J.Evangelista [QC#58577,ADD]
                if (!ZYPCommonFunc.hasValue(trackingNum)) {
                    soNum = (String) wmsSoHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM);
                    outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_WMS_JOB_ID, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                    ++this.errorCount;
                    continue;
                }
                // END 2021/03/23 J.Evangelista [QC#58577,ADD]

                boolean extWmsInbdTrx = isExistWmsInbdTrx(cpltLclTxt, trackingNum, whCd, glblCmpyCd);
                // Unspecified SO_NUM

                if (!extWmsInbdTrx) {
                    // IF Data Validation Check
                    valid = validateIFData(wmsSoHdr, wmsSoDtlList, trxId, segmentId);

                    if (valid) {
                        NPZC103001PMsg pMsg = executePRUpdateAPI(wmsSoHdr, wmsSoDtlList, glblCmpyCd);

                        valid = validatePRUpdateAPIResult(wmsSoHdr, pMsg, trxId, segmentId, unitId);

                        if (valid) {
                            soNum = pMsg.prchReqInfo.no(0).soNum.getValue();
                            soCreated = true;
                            // QC#57659-1
                            for (int i = 0; i < pMsg.prchReqInfo.getValidCount(); i++) {
                                soSlpNumList.add(ZYPCommonFunc.leftPad(String.valueOf(i + 1) ,3, "0"));
                            }
                        } else {
                            rollback();
                        }
                        // END 2020/11/28 [QC#57659,MOD]
                    }
                }
            }

            Map<String, Object> soHeader = new HashMap<String, Object>();
            List<Map<String, Object>> soDetailList = new ArrayList<Map<String, Object>>();

            // SO# present
            if (ZYPCommonFunc.hasValue(soNum)) {
                // QC#57659-1
                if (soCreated == false) {
                    for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {
                        soSlpNumList.add(ZYPCommonFunc.leftPad((String) wmsSoDtl.get("ORD_LINE_NUM") ,3, "0"));
                    }
                }
                // SO Header acquisition
                soHeader = getSoHeader(glblCmpyCd, soNum);
                // SO Line acquisition. QC#57659-1
                soDetailList = getSoDetailList(glblCmpyCd, soNum, soSlpNumList);

                // Error handling
                if (soHeader == null || soDetailList == null) {
                    valid = false;
                    setIntfcProcStsAsError(wmsSoHdr, NLBB091001Constant.NLGM0089E);
                    outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_SO_NUM, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                    ++this.errorCount;
                    continue;
                }

                if (soCreated) {

                    // Register Shipping Order Information for WMS
                    NLBB090001 nlbObj = new NLBB090001();

                    // SO WMS Drop Flag update
                    nlbObj.updateWmsDropFlg(this.glblCmpyCd, soNum, ZYPConstant.FLG_ON_Y);

                    // Get SO Carrier
                    Map<String, Object> tplCarrSvcLvl = null;
                    String whOwnrCd = "MNX";
                    String carrCd = (String) soHeader.get(NLBB091001Constant.COL_CARR_CD);
                    String shpgSvcLvlCd = (String) soHeader.get(NLBB091001Constant.COL_SHPG_SVC_LVL_CD);
                    tplCarrSvcLvl = nlbObj.getTplCarrSvcLvlWithEffort(this.glblCmpyCd, whOwnrCd, carrCd, shpgSvcLvlCd);

                    // Register WMS_INBD_SO_HDR
                    WMS_INBD_SO_HDRTMsg wmsInbdSoHdr = nlbObj.createWmsInbdSoHdr(soHeader, tplCarrSvcLvl, this.salesDate);
                    EZDTBLAccessor.insert(wmsInbdSoHdr);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoHdr.getReturnCode())) {
                        outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_HDR, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                        throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_HDR, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                    }

                    // Register WMS_INBD_SO_DTL
                    if (soDetailList != null && soDetailList.size() > 0) {
                        List<WMS_INBD_SO_DTLTMsg> wmsInbdSoDtlList = nlbObj.createWmsInbdSoDtlList(wmsInbdSoHdr, soDetailList);
                        int count = S21FastTBLAccessor.insert(wmsInbdSoDtlList.toArray(new WMS_INBD_SO_DTLTMsg[wmsInbdSoDtlList.size()]));
                        if (count != wmsInbdSoDtlList.size()) {
                            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_DTL, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                            throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_DTL, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                        }
                    }

                    // Get SO Text list
                    List<Map<String, Object>> soTextList = getSoTextList(glblCmpyCd, soNum);

                    // Register WMS_INBD_SO_TEXT
                    if (soTextList != null && soTextList.size() > 0) {
                        WMS_INBD_SO_TEXTTMsg wmsInbdSoText = nlbObj.createWmsInbdSoText(wmsInbdSoHdr, soTextList);
                        EZDTBLAccessor.insert(wmsInbdSoText);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoText.getReturnCode())) {
                            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_TEXT, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                        throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_TEXT, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                        }
                    }

                    // Register WMS_INBD_SO_SHIP_TO
                    WMS_INBD_SO_SHIP_TOTMsg wmsInbdSoShipTo = nlbObj.createWmsInbdSoShipTo(wmsInbdSoHdr, soHeader);
                    EZDTBLAccessor.insert(wmsInbdSoShipTo);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoShipTo.getReturnCode())) {
                        outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_SHIP_TO, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                        throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_SHIP_TO, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                    }

                    // Register WMS_INBD_SO_CHRG_TO
                    WMS_INBD_SO_CHRG_TOTMsg wmsInbdSoChrgTo = nlbObj.createWmsInbdSoChrgTo(wmsInbdSoHdr, soHeader);
                    EZDTBLAccessor.insert(wmsInbdSoChrgTo);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoChrgTo.getReturnCode())) {
                        outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_CHRG_TO, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                        throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_CHRG_TO, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                    }

                    // Register WMS_INBD_SO_BILL_TO
                    WMS_INBD_SO_BILL_TOTMsg wmsInbdSoBillTo = nlbObj.createWmsInbdSoBillTo(wmsInbdSoHdr, soHeader);
                    if (wmsInbdSoBillTo != null) {
                        EZDTBLAccessor.insert(wmsInbdSoBillTo);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdSoBillTo.getReturnCode())) {
                            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_BILL_TO, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                            throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_BILL_TO, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                        }
                    }
                }

                if (valid) {
                    // QC#57659-1
                    // Carrier Mapping.
                    Map<String, Object> carrSvcLvl = null;
                    String tplCarrNm = (String) wmsSoHdr.get("TPL_CARR_NM");
                    String tplSvcLvlNm = (String) wmsSoHdr.get("TPL_SVC_LVL_NM");
                    carrSvcLvl = getCarrSvcLvl(this.glblCmpyCd, tplCarrNm, tplSvcLvlNm);

                    String tplCarrSvcLvlCd = "";
                    String carrCd = "";
                    String shpgSvcLvlCd = "";
                    if (carrSvcLvl != null) {
                        tplCarrSvcLvlCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD);
                        carrCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_CARR_CD);
                        shpgSvcLvlCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_SHPG_SVC_LVL_CD);
                    }
                    wmsSoHdr.put(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD, tplCarrSvcLvlCd);
                    wmsSoHdr.put(NLBB091001Constant.COL_CARR_CD, carrCd);
                    wmsSoHdr.put(NLBB091001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);

                    // Inbound transaction registration check
                    valid = validateInbdTrxData(wmsSoHdr, soHeader, wmsSoDtlList, soNum, trxId, segmentId);
                    if (!valid) {
                        setIntfcProcStsAsError(wmsSoHdr, NLBB091001Constant.NLGM0089E);
                        outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_TRX, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                        ++this.errorCount;
                        continue;
                    }
                }

                // START 2020/11/28 [QC#57659,MOD]
                // QC#57659-3 Mod Start
                // QC#58135 Mod
                if (ZYPCommonFunc.hasValue(cpltLclTxt) //
                        && ZYPCommonFunc.hasValue(shipTaskId) //
                        && WMS_TASK.SHIP.equals(wmsTaskCd)) {
                    // && "PICKUP".equals(shipTaskId)) {
                    // QC#57659-3 Mod End

                    // START 2021/05/27 J.Evangelista [QC#58845,ADD]
                    if (!isExistWmsInbdSoHdr(soNum)) {
                        outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_SO_HDR, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                        ++this.errorCount;
                        continue;
                    }
                    // END 2021/05/27 J.Evangelista [QC#58845,ADD]

                    // START 2021/03/23 J.Evangelista [QC#58577,ADD]
                    if (!ZYPCommonFunc.hasValue(trackingNum)) {
                        outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_WMS_JOB_ID, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                        ++this.errorCount;
                        continue;
                    }
                    // END 2021/03/23 J.Evangelista [QC#58577,ADD]

                    boolean extWmsInbdTrx = isExistWmsInbdTrx(cpltLclTxt, trackingNum, whCd, glblCmpyCd);
                    if (!extWmsInbdTrx) {
                        // Create WMS_INBD_TRX msg
                        List<WMS_INBD_TRXTMsg> wmsInbdTrxList = createWmsInbdTrx(trxId.toPlainString(), unitId.toPlainString(),
                                soNum, wmsSoHdr, wmsSoDtlList, soHeader, soDetailList);

                        if (wmsInbdTrxList == null || wmsInbdTrxList.size() == 0) {
                            ++this.errorCount;
                            continue;
                        }

                        for (WMS_INBD_TRXTMsg wmsInbdTrx : wmsInbdTrxList) {
                            EZDTBLAccessor.insert(wmsInbdTrx);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrx.getReturnCode())) {
                                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_TRX, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});

                                throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_TRX, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString()});
                            }
                        }
                    }
                }
            }

            // START 2021/03/23 J.Evangelista [QC#58577,ADD]
            // If WMS_JOB_ID is null, then skip transaction.
            if (!ZYPCommonFunc.hasValue(trackingNum)) {
                ++this.errorCount;
                continue;
            }
            // END 2021/03/23 J.Evangelista [QC#58577,ADD]

            // QC#58135 Add
            // Create WMS_INBD_TRX Complete Data
            Map<String, Object> carrSvcLvl = null;
            String tplCarrNm = (String) wmsSoHdr.get("TPL_CARR_NM");
            String tplSvcLvlNm = (String) wmsSoHdr.get("TPL_SVC_LVL_NM");
            carrSvcLvl = getCarrSvcLvl(this.glblCmpyCd, tplCarrNm, tplSvcLvlNm);

            String tplCarrSvcLvlCd = "";
            String carrCd = "";
            String shpgSvcLvlCd = "";
            if (carrSvcLvl != null) {
                tplCarrSvcLvlCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD);
                carrCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_CARR_CD);
                shpgSvcLvlCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_SHPG_SVC_LVL_CD);
            }
            wmsSoHdr.put(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD, tplCarrSvcLvlCd);
            wmsSoHdr.put(NLBB091001Constant.COL_CARR_CD, carrCd);
            wmsSoHdr.put(NLBB091001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
            List<WMS_INBD_TRXTMsg> wmsInbdTrxList = createWmsInbdTrxComplete(trxId.toPlainString(), unitId.toPlainString(), soNum, wmsSoHdr, wmsSoDtlList);

            if (wmsInbdTrxList == null || wmsInbdTrxList.size() == 0) {
                ++this.errorCount;
                continue;
            }

            for (WMS_INBD_TRXTMsg wmsInbdTrx : wmsInbdTrxList) {
                EZDTBLAccessor.insert(wmsInbdTrx);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrx.getReturnCode())) {
                    outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_TRX, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });

                    throw new S21AbendException(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.TBL_WMS_INBD_TRX, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                }
            }

            commit();
            // QC#58135 Add End
            if (valid) {
                ++this.successCount;
            } else {
                ++this.errorCount;
            }
        } // End loop

        // Update the flag to processed in any case - success/fail -
        trxAccess.endIntegrationProcess(this.interfaceId, trxId);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getWmsSoHeader(String ifId, BigDecimal tranId, String glbCmpCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_INTERFACE_ID, ifId);
        queryParam.put(NLBB091001Constant.COL_TRANSACTION_ID, tranId);
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glbCmpCd);
        queryParam.put(NLBB091001Constant.COL_RTL_SWH_DSBL_FLG, ZYPConstant.FLG_OFF_N);
        queryParam.put(NLBB091001Constant.COL_PRTY_LOC_FLG, ZYPConstant.FLG_ON_Y);
        queryParam.put(NLBB091001Constant.BIND_SALES_DATE, this.salesDate);
        // START 2020/11/28 [QC#57659,ADD]
        queryParam.put(NLBB091001Constant.BIND_WH_OWNR_CD, "MNX");
        // END 2020/11/28 [QC#57659,ADD]
        // QC#58135 Add
        queryParam.put(NLBB091001Constant.COL_DS_COND_CONST_GRP_ID, NLBB091001Constant.VAL_DS_COND_CONST_GRP_ID);

        return this.ssmBatchClient.queryObjectList("getWmsSoHeader", queryParam, execParam);
    }

    private Map<String, Object> setTechTocCd(Map<String, Object> wmsSoHdr) {

        String tplEmpId = (String) wmsSoHdr.get(NLBB091001Constant.COL_TPL_EMP_ID);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB091001Constant.COL_TECH_TOC_CD, tplEmpId);

        String techTocCd = (String) this.ssmBatchClient.queryObject("getTechTocCd", queryParam, execParam);

        if (!ZYPCommonFunc.hasValue(techTocCd)) {
            // TPL_EMP_ID is TECH_LOC_CD
            queryParam.clear();
            queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(NLBB091001Constant.COL_TECH_LOC_CD, tplEmpId);

            techTocCd = (String) this.ssmBatchClient.queryObject("getTechTocCd", queryParam, execParam);
        }

        wmsSoHdr.put(NLBB091001Constant.COL_TECH_TOC_CD, techTocCd);

        return wmsSoHdr;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getWmsSoDetail(String ifId, BigDecimal tranId, BigDecimal segmentId, BigDecimal unitId, String glbCmpCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glbCmpCd);
        queryParam.put(NLBB091001Constant.COL_INTERFACE_ID, ifId);
        queryParam.put(NLBB091001Constant.COL_TRANSACTION_ID, tranId);
        queryParam.put(NLBB091001Constant.COL_SEGMENT_ID, segmentId);
        queryParam.put(NLBB091001Constant.COL_UNIT_ID, unitId);
        return this.ssmBatchClient.queryObjectList("getWmsSoDetail", queryParam, execParam);
    }

    private boolean validateIFData(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, BigDecimal tranId, BigDecimal segmentId) {

        // Assume record has invalid data.
        setIntfcProcStsAsError(wmsSoHdr, NLBB091001Constant.NLGM0089E);

        if (wmsSoHdr.get(NLBB091001Constant.COL_TPL_LOC_CD) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_TPL_LOC_CD, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_INVTY_LOC_CD) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_INVTY_LOC_CD, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_OTBD_ORD_NUM, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
            return false;
        }

        for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {

            if (wmsSoDtl.get(NLBB091001Constant.COL_ITEM_CD_TXT) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_ITEM_CD_TXT, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsSoDtl.get(NLBB091001Constant.COL_QTY_ORD_TXT) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_QTY_ORD_TXT, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsSoDtl.get(NLBB091001Constant.COL_TPL_LOC_TXT) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_TPL_LOC_TXT, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsSoDtl.get(NLBB091001Constant.COL_ORD_LINE_NUM) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_ORD_LINE_NUM, this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
        }

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsSoHdr);

        return true;
    }

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
            last = first + NLBB091001Constant.ADDR_LENGTH;
            if (last > shipToAddr.length()) {
                last = shipToAddr.length();
            }
            ZYPEZDItemValueSetter.setValue(items[n], shipToAddr.substring(first, last));
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getCarrSvcLvl(String glbCmpCd, String tplCarrNm, String tplShpgSvcLvlNm) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glbCmpCd);
        queryParam.put(NLBB091001Constant.COL_TPL_CARR_NM, tplCarrNm);
        queryParam.put(NLBB091001Constant.COL_TPL_SVC_LVL_NM, tplShpgSvcLvlNm);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getCarrSvcLvl", queryParam, execParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getDeliveryInfo(String glbCmpCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            return null;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glbCmpCd);
        queryParam.put(NLBB091001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        queryParam.put(NLBB091001Constant.COL_DS_COND_CONST_GRP_ID, NLBB091001Constant.VAL_DS_COND_CONST_GRP_ID);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getDeliveryInfo", queryParam, execParam);

        if (result == null) {
            return null;
        }

        // Populate delivery date/time.
        String delyDays = (String) result.get(NLBB091001Constant.COL_DELY_DAYS);
        String delyTm = (String) result.get(NLBB091001Constant.COL_DELY_TM);
        String delyHours = (String) result.get(NLBB091001Constant.COL_DELY_HOURS);

        Date delyDtTm = null;
        if (ZYPCommonFunc.hasValue(delyTm)) {
            // RUSH:Delivery time is specified. Add transport days and
            // set arrival time.
            String delyDate = this.salesDate;
            if (ZYPCommonFunc.hasValue(delyDays)) {
                delyDate = ZYPDateUtil.addDays(delyDate, Integer.parseInt(delyDays));
            }
            // Convert
            int year = Integer.parseInt(delyDate.substring(0, NLBB091001Constant.IDX_PARSE_YEAR_END));
            int month = Integer.parseInt(delyDate.substring(NLBB091001Constant.IDX_PARSE_YEAR_END,
                    NLBB091001Constant.IDX_PARSE_MONTH_END)) - 1;
            int date = Integer.parseInt(delyDate.substring(NLBB091001Constant.IDX_PARSE_MONTH_END,
                    NLBB091001Constant.IDX_PARSE_DATE_END));
            // delyTm format => hh:mm a
            int hourOfDay = Integer.parseInt(delyTm.substring(0, 2));
            if (delyTm.contains("PM")) {
                hourOfDay += NLBB091001Constant.ADD_PM_HRS;
            }
            int minute = Integer.parseInt(delyTm.substring(NLBB091001Constant.IDX_PARSE_MIN_START, NLBB091001Constant.IDX_PARSE_MIN_END));

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
            if (minute <= NLBB091001Constant.PREM_RUSH_MIN) {
                cal.set(Calendar.MINUTE, NLBB091001Constant.PREM_RUSH_MIN);
            } else {
                cal.add(Calendar.HOUR_OF_DAY, 1);
                cal.set(Calendar.MINUTE, 0);
            }
            delyDtTm = cal.getTime();
        }

        // Format delivery date/time into string.
        DateFormat dfDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
        result.put(NLBB091001Constant.COL_DELY_DATE, dfDate.format(delyDtTm));
        DateFormat dfTime = new SimpleDateFormat("HHmm", Locale.US);
        result.put(NLBB091001Constant.COL_DELY_TIME, dfTime.format(delyDtTm));

        return result;
    }

    private NPZC103001PMsg executePRUpdateAPI(Map<String, Object> wmsSoHdr, List<Map<String, Object>> wmsSoDtlList, String glbCmpCd) {
        NPZC103001PMsg pMsg = new NPZC103001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC103001Constant.MODE_TP_SHIP_CONF);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glbCmpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.MNX_SHIP_CONFIRMATION);
        String shipSvcId = (String) wmsSoHdr.get("SHIP_SVC_ID");
        if (ZYPCommonFunc.hasValue(shipSvcId) && ("H3P".equals(shipSvcId))) {
            ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.RUSH);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) wmsSoHdr.get(NLBB091001Constant.COL_TECH_TOC_CD));
        // START 2022/08/17 A.Cullano [QC#60423, MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_NM_ALL_TXT));
        int maxLen = pMsg.getAttr("shipToLocNm").getDigit();
        String shipNmAllTxt = (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_NM_ALL_TXT);
        if (ZYPCommonFunc.hasValue(shipNmAllTxt)) {
            if (maxLen < shipNmAllTxt.length()) {
                shipNmAllTxt = shipNmAllTxt.substring(0, maxLen);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, shipNmAllTxt);
        }
        // END 2022/08/17 A.Cullano [QC#60423, MOD]
        setShipToAddr(pMsg, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_ADDR_ALL_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_CTY_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_ST_OR_PROV_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_ZIP_OR_POST_CD_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_CTRY_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) wmsSoHdr.get(NLBB091001Constant.COL_SHIP_CTAC_NM_TXT));

        String carrCd = null;
        String shpgSvcLvlCd = null;
        String tplCarrNm = (String) wmsSoHdr.get(NLBB091001Constant.COL_TPL_CARR_NM);
        String tplShpgSvcLvlNm = (String) wmsSoHdr.get(NLBB091001Constant.COL_TPL_SVC_LVL_NM);
        Map<String, Object> carrSvcLvl = getCarrSvcLvl(glblCmpyCd, tplCarrNm, tplShpgSvcLvlNm);
        if (carrSvcLvl != null) {
            carrCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_CARR_CD);
            shpgSvcLvlCd = (String) carrSvcLvl.get(NLBB091001Constant.COL_SHPG_SVC_LVL_CD);
            wmsSoHdr.put(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD, (String) carrSvcLvl.get(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD));
        }

        String srcInvtyLocCd = (String) wmsSoHdr.get(NLBB091001Constant.COL_INVTY_LOC_CD);

        String delyDate = null;
        String delyTime = null;
        Map<String, Object> delyInfo = getDeliveryInfo(glblCmpyCd, shpgSvcLvlCd);
        if (delyInfo != null) {
            delyDate = (String) delyInfo.get(NLBB091001Constant.COL_DELY_DATE);
            delyTime = (String) delyInfo.get(NLBB091001Constant.COL_DELY_TIME);
        }
        if (!ZYPCommonFunc.hasValue(delyDate)) {
            delyDate = this.salesDate;
        }

        NPZC103001_prchReqInfoPMsgArray reqInfoPMsgArray = pMsg.prchReqInfo;
        for (int pos = 0; pos < wmsSoDtlList.size(); ++pos) {
            Map<String, Object> wmsSoDtl = wmsSoDtlList.get(pos);
            NPZC103001_prchReqInfoPMsg reqInfoPMsg = reqInfoPMsgArray.no(pos);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.carrCd, carrCd);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.shpgSvcLvlCd, shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.srcInvtyLocCd, srcInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.mdseCd, (String) wmsSoDtl.get(NLBB091001Constant.COL_ITEM_CD_TXT));
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.prchReqQty, new BigDecimal((String) wmsSoDtl.get(NLBB091001Constant.COL_QTY_ORD_TXT)));
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.proNum, (String) wmsSoHdr.get(NLBB091001Constant.COL_WMS_JOB_ID));
            // START 2022/08/17 A.Cullano [QC#60423, MOD]
            // ZYPEZDItemValueSetter.setValue(reqInfoPMsg.trxRefNum, (String) wmsSoHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM));
            maxLen = reqInfoPMsg.getAttr("trxRefNum").getDigit();
            String otbdOrdNum = (String) wmsSoHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM);
            if (ZYPCommonFunc.hasValue(otbdOrdNum)) {
                if (maxLen < otbdOrdNum.length()) {
                    otbdOrdNum = otbdOrdNum.substring(0, maxLen);
                }
                ZYPEZDItemValueSetter.setValue(reqInfoPMsg.trxRefNum, otbdOrdNum);
            }
            // END 2022/08/17 A.Cullano [QC#60423, MOD]
            ZYPEZDItemValueSetter.setValue(reqInfoPMsg.rqstRcvDt, delyDate);
            if (delyTime != null) {
                ZYPEZDItemValueSetter.setValue(reqInfoPMsg.rqstRcvTm, delyTime);
            }
        }
        reqInfoPMsgArray.setValidCount(wmsSoDtlList.size());

        NPZC103001 prUpdateApi = new NPZC103001();
        prUpdateApi.execute(pMsg, ONBATCH_TYPE.BATCH);

        return pMsg;
    }

    private boolean validatePRUpdateAPIResult(Map<String, Object> wmsSoHdr, NPZC103001PMsg pMsg, BigDecimal tranId, BigDecimal segmentId, BigDecimal unitId) {
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            if (msgIdList.size() > 0) {
                setIntfcProcStsAsError(wmsSoHdr, NLBB091001Constant.NLBM1374E);
            }
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            int i = 0;
            for (S21ApiMessage s21ApiMsg : msgList) {
                String msgId = s21ApiMsg.getXxMsgid();
                if (pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0 != null && pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1 != null) {
                    String prNum = pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();
                    String mdseCd = pMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue();
                    if (ZYPCommonFunc.hasValue(prNum) && ZYPCommonFunc.hasValue(mdseCd)) {
                        String msg = S21MessageFunc.clspGetMessage(msgId);
                        String tranIdSegId = getConnectVal(tranId.toPlainString(), NLBB091001Constant.VAL_CONNECT_SEGMENT_ID, segmentId.toPlainString());
                        outputErr(NLBB091001Constant.NLBM1374E, new String[] {msg, prNum, mdseCd, this.interfaceId, tranIdSegId });
                    } else {
                        String msg = S21MessageFunc.clspGetMessage(msgId);
                        outputErr(NLBB091001Constant.NLBM1373E, new String[] {msg, (String) wmsSoHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM), this.interfaceId, tranId.toPlainString(), segmentId.toPlainString() });
                    }
                }
                i++;
            }
            return false;
        }

        // Assume record has invalid data.
        setIntfcProcStsAsError(wmsSoHdr, NLBB091001Constant.NLGM0045E);

        NPZC103001_prchReqInfoPMsgArray reqInfoPMsgArray = pMsg.prchReqInfo;
        if (reqInfoPMsgArray.getValidCount() <= 0) {
            outputErr(NLBB091001Constant.NLGM0045E, new String[] {});
            return false;
        }
        String soNum = reqInfoPMsgArray.no(0).soNum.getValue();
        if (!ZYPCommonFunc.hasValue(soNum)) {
            outputErr(NLBB091001Constant.NLGM0045E, new String[] {});
            return false;
        }
        for (int pos = 1; pos < reqInfoPMsgArray.getValidCount(); ++pos) {
            String other = reqInfoPMsgArray.no(pos).soNum.getValue();
            if (!soNum.equals(other)) {
                outputErr(NLBB091001Constant.NLGM0045E, new String[] {});
                return false;
            }
        }

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsSoHdr);

        return true;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSoHeader(String glbCmpCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glbCmpCd);
        queryParam.put(NLBB091001Constant.COL_SO_NUM, soNum);
        queryParam.put(NLBB091001Constant.COL_WH_SYS_TP_CD, WH_SYS_TP._3PL);
        queryParam.put(NLBB091001Constant.COL_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
        queryParam.put(NLBB091001Constant.BIND_SO_CUST_DATA_TP_CD_SHIP, SO_CUST_DATA_TP.SHIP_TO);
        queryParam.put(NLBB091001Constant.BIND_SO_CUST_DATA_TP_CD_SELL, SO_CUST_DATA_TP.SELL_TO);
        queryParam.put(NLBB091001Constant.BIND_SO_CUST_DATA_TP_CD_BILL, SO_CUST_DATA_TP.BILL_TO);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSoHeader", queryParam, execParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSoDetailList(String glbCmpCd, String soNum, List<String> soSlpNumList) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB091001Constant.COL_GLBL_CMPY_CD, glbCmpCd);
        queryParam.put(NLBB091001Constant.COL_SO_NUM, soNum);
        // QC#57659-1
        queryParam.put("soSlpNumList", soSlpNumList);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSoDetail", queryParam, execParam);
    }
     /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        if (msgParam.length > 0) {
            mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        } else {
            mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId));
        }
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    private void setIntfcProcStsAsSuccess(Map<String, Object> wmsSoHdr) {
        wmsSoHdr.put(NLBB091001Constant.COL_INTFC_PROC_STS_CD, NLBB091001Constant.VAL_INTFC_PROC_STS_CD_SUCCESS);
        wmsSoHdr.put(NLBB091001Constant.COL_INTFC_ERR_MSG_CD, null);
    }

    private void setIntfcProcStsAsError(Map<String, Object> wmsSoHdr, String msgId) {
        wmsSoHdr.put(NLBB091001Constant.COL_INTFC_PROC_STS_CD, NLBB091001Constant.VAL_INTFC_PROC_STS_CD_ERROR);
        wmsSoHdr.put(NLBB091001Constant.COL_INTFC_ERR_MSG_CD, msgId);
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

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSoTextList(String gCmpyCd, String soNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_SO_NUM, soNum);
        queryParam.put(NLBB090001Constant.COL_SO_MSG_TP_CD, SHPG_ORD_MSG_TP.CPO_INFORMATION);
        List<Map<String, Object>> soTxtList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSoText", queryParam);

        return soTxtList;
    }

    private boolean validateInbdTrxData(Map<String, Object> wmsSoHdr, Map<String, Object> soHdr, List<Map<String, Object>> wmsSoDtlList, String soNum, BigDecimal trxId, BigDecimal segmentId) {
        // Assume record has invalid data.
        setIntfcProcStsAsError(wmsSoHdr, NLBB091001Constant.NLGM0089E);

        if (wmsSoHdr.get(NLBB091001Constant.COL_TPL_LOC_CD) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_TPL_LOC_CD, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_SEQ_NUMBER) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_SEQ_NUMBER, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_OTBD_ORD_NUM, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (soHdr.get(NLBB091001Constant.COL_S80_ORD_TP_CD) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_S80_ORD_TP_CD, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_REQ_DT_TM_TS_TXT) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_REQ_DT_TM_TS_TXT, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_RTL_SWH_CD) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_RTL_SWH_CD, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }
        if (wmsSoHdr.get(NLBB091001Constant.COL_WMS_REC_ID) == null) {
            outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_WMS_REC_ID, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
            return false;
        }

        for (Map<String, Object> wmsSoDtl : wmsSoDtlList) {
            if (wmsSoDtl.get(NLBB091001Constant.COL_ITEM_CD_TXT) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_ITEM_CD_TXT, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsSoDtl.get(NLBB091001Constant.COL_QTY_ORD_TXT) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_QTY_ORD_TXT, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
            if (wmsSoDtl.get(NLBB091001Constant.COL_ORD_LINE_NUM) == null) {
                outputErr(NLBB091001Constant.NLGM0089E, new String[] {NLBB091001Constant.COL_ORD_LINE_NUM, soNum, this.interfaceId, trxId.toPlainString(), segmentId.toPlainString() });
                return false;
            }
        }

        // No error. Set status to success.
        setIntfcProcStsAsSuccess(wmsSoHdr);

        return true;
    }

    private List<WMS_INBD_TRXTMsg> createWmsInbdTrx(String trxId, String untId, String soNum, Map<String, Object> wmsHdr,
            List<Map<String, Object>> wmsDtlList, Map<String, Object> soHdr, List<Map<String, Object>> soDtlList) {

        ArrayList<WMS_INBD_TRXTMsg> tMsgList = new ArrayList<WMS_INBD_TRXTMsg>();

        // GLBL_CMPY_CD
        String glbCmpCd = this.glblCmpyCd;

        // TRANSACTION_ID
        String transId = ZYPCommonFunc.leftPad(trxId, NLBB091001Constant.LEN_INTFC_TRX, NLBB091001Constant.VAL_ZERO);

        // UNIT_ID
        String unitId = ZYPCommonFunc.leftPad(untId, NLBB091001Constant.LEN_INTFC_TRX, NLBB091001Constant.VAL_ZERO);

        // INTFC_PROC_STS_CD
        String intfcProcStsCd = (String) wmsHdr.get(NLBB091001Constant.COL_INTFC_PROC_STS_CD);

        // INTFC_ERR_MSG_CD
        String intfcErrMsgCd = (String) wmsHdr.get(NLBB091001Constant.COL_INTFC_ERR_MSG_CD);

        // REQ_DT_TM_TS
        String reqDtTmTs = (String) wmsHdr.get(NLBB091001Constant.COL_REQ_DT_TM_TS_TXT);
        reqDtTmTs = adjustString(reqDtTmTs, NLBB091001Constant.LEN_REQ_DT_TM_TS);

        // START 2020/11/28 [QC#57659,ADD]
        // SHIP_DT_TM_TS
        String shipDtTmTs = (String) wmsHdr.get(NLBB091001Constant.COL_CPLT_LCL_TXT);
        shipDtTmTs = adjustString(shipDtTmTs, NLBB091001Constant.LEN_SHIP_DT_TM_TS);
        // END 2020/11/28 [QC#57659,ADD]

        String wmsTaskCd = (String) wmsHdr.get(NLBB091001Constant.COL_WMS_TASK_CD);
        String wmsOrdStsCd = null;
        String intfcTpId = null;

        // QC#57659-1 Mod Loop process.
        int cnt = 0;
        for (Map<String, Object> wmsDtl : wmsDtlList) {
            WMS_INBD_TRXTMsg tMsg = new WMS_INBD_TRXTMsg();

            // WMS_INBD_TRX_PK
            BigDecimal wmsInbdTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);

            // WMS_PKG_CD = INVTY_OWNR_CD+RTL_SWH_CD;
            String wmsPkgCd = (String) soHdr.get(NLBB091001Constant.COL_INVTY_OWNR_CD);
            String tmpPkgCd = wmsPkgCd;

            for (int i = cnt; i < soDtlList.size(); i++) {
                String rtlSwhCd = (String) soDtlList.get(cnt).get(NLBB091001Constant.COL_RTL_SWH_CD);
                tmpPkgCd = wmsPkgCd + rtlSwhCd;
                ZYPEZDItemValueSetter.setValue(tMsg.wmsStkStsCd, (String) soDtlList.get(cnt).get(NLBB091001Constant.COL_FROM_STK_STS_CD));
                if (ZYPCommonFunc.hasValue((String) soDtlList.get(cnt).get("SO_SLP_NUM"))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdLineNum, new BigDecimal((String) soDtlList.get(cnt).get("SO_SLP_NUM")));
                }
                break;
            }

            if (!ZYPCommonFunc.hasValue(tMsg.otbdOrdLineNum)) {
                // OTBD_ORD_LINE_NUM
                BigDecimal otbdOrdLineNum = null;
                String ordLineNum = (String) wmsDtl.get(NLBB091001Constant.COL_ORD_LINE_NUM);
                if (ZYPCommonFunc.hasValue(ordLineNum)) {
                    otbdOrdLineNum = new BigDecimal(ordLineNum);
                    ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdLineNum, otbdOrdLineNum);
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(this.wmsPackCdMode)) {
                wmsPkgCd = tmpPkgCd;
            }

            // WMS_ORD_QTY
            BigDecimal wmsOrdQty = null;
            String qtyOrdTxt = (String) wmsDtl.get(NLBB091001Constant.COL_QTY_ORD_TXT);
            if (ZYPCommonFunc.hasValue(qtyOrdTxt)) {
                wmsOrdQty = new BigDecimal(qtyOrdTxt);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glbCmpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInbdTrxPk, wmsInbdTrxPk);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_REC_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) soHdr.get(NLBB091001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTaskCd, wmsTaskCd);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcId, (String) wmsHdr.get(NLBB091001Constant.COL_INTERFACE_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRcvTs, (String) wmsHdr.get(NLBB091001Constant.COL_EZINTIME));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxId, transId);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxSqNum, unitId);
            ZYPEZDItemValueSetter.setValue(tMsg.wrkTrxId, NLBB091001Constant.VAL_WRK_TRX_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcProcStsCd, intfcProcStsCd);
            if (intfcProcStsCd.equals(NLBB091001Constant.VAL_INTFC_PROC_STS_CD_ERROR)) {
                ZYPEZDItemValueSetter.setValue(tMsg.intfcErrMsgCd, intfcErrMsgCd);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, NLBB091001Constant.VAL_ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) wmsDtl.get(NLBB091001Constant.COL_ITEM_CD_TXT));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsPkgCd, wmsPkgCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdQty, wmsOrdQty);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdNum, soNum);
            // START 2020/11/28 [QC#57659,MOD]
            // ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdTpCd,
            // (String)
            // wmsHdr.get(NLBB091001Constant.COL_OTBD_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdTpCd, NLBB091001Constant.VAL_OTBD_ORD_TP_CD);
            // END 2020/11/28 [QC#57659,MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdStsCd, wmsOrdStsCd);
            // START 2020/11/28 [QC#57659,MOD]
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsShipDtTmTs,
            // reqDtTmTs);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipDtTmTs, shipDtTmTs);
            // END 2020/11/28 [QC#57659,MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxDtTmTs, reqDtTmTs);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTotWt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCarrCd, (String) wmsHdr.get(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.proNum, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_JOB_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.tmsTrkNum, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_JOB_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipId, soNum);
            ZYPEZDItemValueSetter.setValue(tMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOutCntnrNum, soNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsFrtChrgAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsBatId, ZYPDateUtil.getCurrentSystemTime(NLBB091001Constant.FMT_YYYYMMDDHHMMSS));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, intfcTpId);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrgHostId, (String) soHdr.get(NLBB091001Constant.COL_WMS_ORG_HOST_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsUpdHistNum, BigDecimal.ZERO);
            // START 2020/11/20 J.Evangelista [QC#57659,ADD]
            ZYPEZDItemValueSetter.setValue(tMsg.carrEncTrkUrl, (String) wmsHdr.get(NLBB091001Constant.COL_CARR_ENC_TRK_URL));
            // END 2020/11/20 J.Evangelista [QC#57659,ADD]
            // QC#58876-2 Add start
            ZYPEZDItemValueSetter.setValue(tMsg.shipLclTz, (String) wmsHdr.get("SHIP_LCL_TZ"));
            ZYPEZDItemValueSetter.setValue(tMsg.shipLclTzShortTxt, (String) wmsHdr.get("SHIP_LCL_TZ_SHORT_TXT"));
            // QC#58876-2 Add end

            tMsgList.add(tMsg);

            cnt++;
        }


        return tMsgList;
    }

    private String getConnectVal(String origVal, String connectVal, String addVal) {

        StringBuilder sb = new StringBuilder();
        sb.append(origVal);
        sb.append(connectVal);
        sb.append(addVal);

        return sb.toString();
    }

    // START 2020/11/28 [QC#57659,ADD]
    /**
     * Judge whether WMS_INBD_TRX exists or not.
     * @param glbCmpCd
     * @param whCd
     * @param trackingNum
     * @param shipTaskId
     * @param cpltLclTxt
     * @return true / exists, false / not exists
     */
    private boolean isExistWmsInbdTrx(String cpltLclTxt, String trackingNum, String whCd, String glbCmpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glbCmpCd);
        ssmParam.put("whCd", whCd);
        ssmParam.put("proNum", trackingNum);
        ssmParam.put("wmsTaskCd", WMS_TASK.SHIP);
        ssmParam.put("wmsShipDtTmTs", cpltLclTxt);
        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("isExistWmsInbdTrx", ssmParam);

        if (ret.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // END 2020/11/28 [QC#57659,ADD]
    // QC#58135 Add
    private List<WMS_INBD_TRXTMsg> createWmsInbdTrxComplete(String trxId, String untId, String soNum, Map<String, Object> wmsHdr, List<Map<String, Object>> wmsDtlList) {

        if (!ZYPCommonFunc.hasValue(soNum)) {
            soNum = (String) wmsHdr.get(NLBB091001Constant.COL_OTBD_ORD_NUM);
        }

        ArrayList<WMS_INBD_TRXTMsg> tMsgList = new ArrayList<WMS_INBD_TRXTMsg>();

        // GLBL_CMPY_CD
        String glbCmpCd = this.glblCmpyCd;

        // TRANSACTION_ID
        String transId = ZYPCommonFunc.leftPad(trxId, NLBB091001Constant.LEN_INTFC_TRX, NLBB091001Constant.VAL_ZERO);

        // UNIT_ID
        String unitId = ZYPCommonFunc.leftPad(untId, NLBB091001Constant.LEN_INTFC_TRX, NLBB091001Constant.VAL_ZERO);

        // REQ_DT_TM_TS
        String reqDtTmTs = (String) wmsHdr.get(NLBB091001Constant.COL_REQ_DT_TM_TS_TXT);
        reqDtTmTs = adjustString(reqDtTmTs, NLBB091001Constant.LEN_REQ_DT_TM_TS);

        // SHIP_DT_TM_TS
        String shipDtTmTs = (String) wmsHdr.get(NLBB091001Constant.COL_CPLT_LCL_TXT);
        shipDtTmTs = adjustString(shipDtTmTs, NLBB091001Constant.LEN_SHIP_DT_TM_TS);

        int cnt = 0;
        for (Map<String, Object> wmsDtl : wmsDtlList) {
            WMS_INBD_TRXTMsg tMsg = new WMS_INBD_TRXTMsg();

            // WMS_INBD_TRX_PK
            BigDecimal wmsInbdTrxPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);

            // OTBD_ORD_LINE_NUM
            BigDecimal otbdOrdLineNum = null;
            String ordLineNum = (String) wmsDtl.get(NLBB091001Constant.COL_ORD_LINE_NUM);
            if (ZYPCommonFunc.hasValue(ordLineNum)) {
                otbdOrdLineNum = new BigDecimal(ordLineNum);
                ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdLineNum, otbdOrdLineNum);
            }

            // WMS_ORD_QTY
            BigDecimal wmsOrdQty = null;
            String qtyOrdTxt = (String) wmsDtl.get(NLBB091001Constant.COL_QTY_ORD_TXT);
            if (ZYPCommonFunc.hasValue(qtyOrdTxt)) {
                wmsOrdQty = new BigDecimal(qtyOrdTxt);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glbCmpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInbdTrxPk, wmsInbdTrxPk);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_REC_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) wmsHdr.get(NLBB091001Constant.COL_TPL_LOC_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTaskCd, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_TASK_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcId, (String) wmsHdr.get(NLBB091001Constant.COL_INTERFACE_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRcvTs, (String) wmsHdr.get(NLBB091001Constant.COL_EZINTIME));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxId, transId);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTrxSqNum, unitId);
            // QC#58876-1
            if (ZYPCommonFunc.hasValue((BigDecimal)wmsHdr.get(NLBB091001Constant.COL_SEGMENT_ID))) {
                ZYPEZDItemValueSetter.setValue(tMsg.wrkTrxId, ZYPCommonFunc.leftPad(((BigDecimal) wmsHdr.get(NLBB091001Constant.COL_SEGMENT_ID)).toPlainString(), NLBB091001Constant.LEN_INTFC_TRX, NLBB091001Constant.VAL_ZERO));
            }
            if (WMS_TASK.POD.equals(tMsg.wmsTaskCd.getValue()) && ZYPCommonFunc.hasValue(shipDtTmTs)) {
                ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, ZYPConstant.FLG_OFF_0);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, ZYPConstant.FLG_ON_1);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) wmsDtl.get(NLBB091001Constant.COL_ITEM_CD_TXT));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsPkgCd, (String) wmsHdr.get(NLBB091001Constant.COL_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdQty, wmsOrdQty);
            if (ZYPCommonFunc.hasValue(soNum)) {
                int digit = tMsg.getAttr("otbdOrdNum").getDigit();
                if (digit < soNum.length()) {
                    soNum = soNum.substring(0, digit);
                }
            }
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdNum, soNum);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdOrdTpCd, NLBB091001Constant.VAL_OTBD_ORD_TP_CD);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdStsCd, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_ORD_STS_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipDtTmTs, shipDtTmTs);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxDtTmTs, reqDtTmTs);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTotWt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCarrCd, (String) wmsHdr.get(NLBB091001Constant.COL_TPL_CARR_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.proNum, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_JOB_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.tmsTrkNum, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_JOB_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
            // START 2022/08/17 A.Cullano [QC#60423, ADD]
            if (ZYPCommonFunc.hasValue(soNum)) {
                int maxLen = tMsg.getAttr("wmsOutCntnrNum").getDigit();
                if (maxLen < soNum.length()) {
                    soNum = soNum.substring(0, maxLen);
                }
            }
            // END 2022/08/17 A.Cullano [QC#60423, ADD]
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOutCntnrNum, soNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsFrtChrgAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsBatId, ZYPDateUtil.getCurrentSystemTime(NLBB091001Constant.FMT_YYYYMMDDHHMMSS));
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, (String) wmsHdr.get(NLBB091001Constant.COL_INTF_TP_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrgHostId, (String) wmsHdr.get(NLBB091001Constant.COL_WMS_ORG_HOST_ID));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsUpdHistNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.carrEncTrkUrl, (String) wmsHdr.get(NLBB091001Constant.COL_CARR_ENC_TRK_URL));
            // QC#58135-1
            ZYPEZDItemValueSetter.setValue(tMsg.quoteDelyTxt, (String) wmsHdr.get("QUOTE_DLV_DT_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.plnLocalTxt, (String) wmsHdr.get("PLN_LCL_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.cpltLocalTxt, (String) wmsHdr.get("CPLT_LCL_TXT"));
            ZYPEZDItemValueSetter.setValue(tMsg.descHistTxt, (String) wmsHdr.get("DESC_HIST_TXT"));
            // START 2021/07/01 J.Evangelista [QC#58876,ADD]
            ZYPEZDItemValueSetter.setValue(tMsg.bolVchNum, (String) wmsHdr.get("BOL_VCH_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.rqstQuoteDelyTxt, (String) wmsHdr.get("REQ_QUOTE_DLV_DT_TXT"));
            // END 2021/07/01 J.Evangelista [QC#58876,ADD]
            // QC#58876-2 Add start
            ZYPEZDItemValueSetter.setValue(tMsg.shipLclTz, (String) wmsHdr.get("SHIP_LCL_TZ"));
            ZYPEZDItemValueSetter.setValue(tMsg.shipLclTzShortTxt, (String) wmsHdr.get("SHIP_LCL_TZ_SHORT_TXT"));
            // QC#58876-2 Add end

            tMsgList.add(tMsg);

            cnt++;
        }


        return tMsgList;
    }

    // START 2021/05/27 J.Evangelista [QC#58845,ADD]
    private boolean isExistWmsInbdSoHdr(String soNum) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("soNum", soNum);

        Integer count = (Integer) this.ssmBatchClient.queryObject("isExistWmsInbdSoHdr", map);

        return (count.intValue() > 0);
    }
    // END 2021/05/27 J.Evangelista [QC#58845,ADD]    
}
