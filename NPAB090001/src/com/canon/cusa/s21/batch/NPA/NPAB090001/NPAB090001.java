/*
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB090001;

import static com.canon.cusa.s21.batch.NPA.NPAB090001.constant.NPAB090001Constant.DEFAULT_SEGMENT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB090001.constant.NPAB090001Constant.MODE_ACK;
import static com.canon.cusa.s21.batch.NPA.NPAB090001.constant.NPAB090001Constant.MODE_MNX;
import static com.canon.cusa.s21.batch.NPA.NPAB090001.constant.NPAB090001Constant.VAL_EMPTY;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.NLBI1410_01TMsg;

import com.canon.cusa.s21.batch.NPA.NPAB090001.constant.NPAB090001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACK_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.CarrierInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.CountryCodeType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.LocationInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrderType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrdersType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglcreateOrderRequest;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglcreateOrderResponse;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.PULocationInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.RefInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.ShipperType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.StatusType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrderType.RefLoop;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.wrapper.NglShippingOrderServiceProxy;
/**
 * <pre>
 * NPAB090001: Courier Job Request for MNX
 * 
 * Date         Company      Name             Create/Update    Defect No
 * ---------------------------------------------------------------------
 * 2020/10/27   CITS         A.Marte          Create           QC#57659
 * 11/14/2020   CITS         K.Ogino          Update           QC#57659
 * 12/21/2020   CITS         K.Ogino          Update           QC#58137, QC#58151
 * 01/05/2021   CITS         K.Ogino          Update           QC#58209
 * 01/27/2021   CITS         A.Marte          Create           QC#58281
 * <pre>
 */
public class NPAB090001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Interface Id */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Counter of Records: Successful */
    private int successCount = 0;

    /** Counter of Records: Error */
    private int errorCount = 0;

    /** mode */
    private String mode = null;

    /** processMode. */
    private String processMode = null;

    /** bizProcLogPk. */
    private BigDecimal dsBizProcLogPk = null;

    /** DS_BIZ_LAST_UPD_TS */
    private String lastUpdTs = null;

    /** cusaPartsWHList */
    private List<String> cusaPartsWHList = new ArrayList<String>();

    /** */
    private String mnxSvcTpCd;

    /** */
    private String cusaDbsShipFromNm;

    /** */
    private String cusaDbsShipFromAddr;

    /** */
    private String cusaDbsShipFromCty;

    /** */
    private String cusaDbsShipFromSt;

    /** */
    private String cusaDbsShipFromPost;

    /** */
    private String cusaDbsShipFromCtryTxt;

    /** */
    private String cusaDbsShipFromCtacNm;

    /** */
    private String cusaDbsShipFromPhoNum;

    /** */
    private String cusaDbsShipFromCmntAll;

    /** */
    private String whOwnrMNX;

    /** */
    private String insourcing;

    /** */
    private String premiumRush;

    /** Add QC#58209 */
    private String[] ackLineStsCds;

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** */
    private S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** */
    private StringBuilder sb;

    // QC#58137 Add Start
    private boolean isAbend = false;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList4User = new ArrayList<Map<String, String>>();

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList4It = new ArrayList<Map<String, String>>();
    // QC#58137 Add End

    // START 2020/01/26 A.Marte [QC#58281, ADD]
    /** Error Items List */
    private ArrayList<Map<String, String>> errItemList = new ArrayList<Map<String, String>>();
    // END 2020/01/26 A.Marte [QC#58281, ADD]

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
            throw new S21AbendException(NPAB090001Constant.NPAM1173E, new String[] {NPAB090001Constant.PARAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NPAB090001Constant.NPAM1173E, new String[] {NPAB090001Constant.PARAM_NM_INTERFACE_ID });
        }

        // Get execution mode (userVariable1)
        // 1 : ACK Process
        // 2 : MNX Federated Process
        mode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mode)) {
            mode = "1";
        }

        // Get Courier Job Request WH List
        String courJobReqWhConst = ZYPCodeDataUtil.getVarCharConstValue("NPAB0900_COURIER_JOB_REQ_WH", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(courJobReqWhConst)) {
            cusaPartsWHList = Arrays.asList(courJobReqWhConst.split(","));
        }

        // Get MNX_SVC_TP_CD
        this.mnxSvcTpCd = ZYPCodeDataUtil.getVarCharConstValue("NPAB0900_MNX_SVC_TP_CD", glblCmpyCd);

        // Get CUSA_DBS_SHIP_FROM_NM,
        cusaDbsShipFromNm = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_NM, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromNm)) {
            cusaDbsShipFromNm = "";
        }

        // Get CUSA_DBS_SHIP_FROM_ADDR,
        cusaDbsShipFromAddr = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_ADDR, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromNm)) {
            cusaDbsShipFromAddr = "";
        }

        // Get CUSA_DBS_SHIP_FROM_CTY,
        this.cusaDbsShipFromCty = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_CTY, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromCty)) {
            cusaDbsShipFromCty = "";
        }

        // Get CUSA_DBS_SHIP_FROM_ST,
        this.cusaDbsShipFromSt = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_ST, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromSt)) {
            cusaDbsShipFromSt = "";
        }

        // Get CUSA_DBS_SHIP_FROM_POST,
        this.cusaDbsShipFromPost = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_POST, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromPost)) {
            cusaDbsShipFromPost = "";
        }

        // Get CUSA_DBS_SHIP_FROM_CTRY_TXT,
        this.cusaDbsShipFromCtryTxt = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_CTRY_TXT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromCtryTxt)) {
            cusaDbsShipFromCtryTxt = "";
        }

        // Get CUSA_DBS_SHIP_FROM_CTAC_NM,
        this.cusaDbsShipFromCtacNm = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_CTAC_NM, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromCtacNm)) {
            cusaDbsShipFromCtacNm = "";
        }

        // Get CUSA_DBS_SHIP_FROM_PHO_NUM,
        this.cusaDbsShipFromPhoNum = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_PHO_NUM, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromPhoNum)) {
            cusaDbsShipFromPhoNum = "";
        }

        // Get CUSA_DBS_SHIP_FROM_CMNT_ALL,
        this.cusaDbsShipFromCmntAll = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CUSA_DBS_SHIP_FROM_CMNT_ALL, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaDbsShipFromCmntAll)) {
            cusaDbsShipFromCmntAll = "";
        }

        // QC#58137
        String constVal = ZYPCodeDataUtil.getVarCharConstValue("NPBB0900_SYSERR_ABEND_FLG", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal) && ZYPConstant.FLG_ON_Y.equals(constVal)) {
            this.isAbend = true;
        }
        // Add QC#58209
        constVal = ZYPCodeDataUtil.getVarCharConstValue("NPAB090001_ACK_LINE_STS_CD", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal)) {
            this.ackLineStsCds = constVal.split(",");
        } else {
            this.ackLineStsCds = new String[]{PO_ACK_LINE_STS.SO_PRINTED, PO_ACK_LINE_STS.SHIPPED};
        }

        // Get target WH code
        this.whOwnrMNX = WH_OWNR.MNX;

        this.insourcing = PO_ORD_SRC.INSOURCING;

        this.premiumRush = PRCH_REQ_TP.PREMIUM_RUSH;
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        // Check processing mode
        try {
            //ACK Processing MODE
            if (mode.equals(MODE_ACK)) {
                 processMode = "1";
                 getLastUpdateTs();

                 //inform the time for processing data from last task (this message can be changed)
                 S21InfoLogOutput.println("TARGET DATA : " + lastUpdTs);

                 processAck();

                  // Last Proc Ts Update
                 updateDsBizProcLog();

                 // inform the time to finish and next time processing (this message can be changed)
                 S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + lastUpdTs);

            } else if (mode.equals(MODE_MNX)) {
                // Process MNX Federation MOde

                processMNX();
            }

            commit();

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NPAB090001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
            // QC#58137
            if (errMsgList4It.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NPAB090001Constant.BUSINESS_ID, errMsgList4It, new String[]{"MNXAPIM001"});
                commit();
            }
            if (errMsgList4User.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NPAB090001Constant.BUSINESS_ID, errMsgList4User, new String[]{"MNXAPIM002"});
                commit();
            }
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

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB090001().executeBatch(NPAB090001.class.getSimpleName());

    }

    /**
     * getLastUpdateTs
     */
    private void getLastUpdateTs() {
        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", NPAB090001Constant.BUSINESS_ID.concat(processMode));

        DS_BIZ_PROC_LOGTMsgArray getTMsgArray = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (getTMsgArray.getValidCount() > 0) {
            if (getTMsgArray.no(0).dsBizProcLogPk != null) {
                this.lastUpdTs = getTMsgArray.no(0).dsBizLastUpdTs.getValue();
                this.dsBizProcLogPk = getTMsgArray.no(0).dsBizProcLogPk.getValue();
            } else {
                this.lastUpdTs = NPAB090001Constant.DS_BIZ_LAST_UPD_TS_DEFAULT;
            }
        } else {
            this.lastUpdTs = NPAB090001Constant.DS_BIZ_LAST_UPD_TS_DEFAULT;
        }
    }

    /**
     * processAck
     * @param execParam
     */
    private void processAck() {
         // Set the fetch size.
        S21SsmExecutionParameter execParam = null;
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(NPAB090001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get Ack Information
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("targetTime", this.lastUpdTs);
            queryParam.put("cusaPartsIntfcId", "NPAA0041");
            queryParam.put("cusaPartsWHList", this.cusaPartsWHList);
            queryParam.put("svcTpTxt", this.mnxSvcTpCd);
            queryParam.put("shipFromNmAll", this.cusaDbsShipFromNm);
            queryParam.put("shipFromAddrAll", this.cusaDbsShipFromAddr);
            queryParam.put("shipFromCty", this.cusaDbsShipFromCty);
            queryParam.put("shipFromSt", this.cusaDbsShipFromSt);
            queryParam.put("shipFromPost", this.cusaDbsShipFromPost);
            queryParam.put("shipFromCtry", this.cusaDbsShipFromCtryTxt);
            queryParam.put("shipFromCtacNm", this.cusaDbsShipFromCtacNm);
            queryParam.put("shipFromPhoNum", this.cusaDbsShipFromPhoNum);
            queryParam.put("shipFromCmntAll", this.cusaDbsShipFromCmntAll);
            // QC#58209
            queryParam.put("ackLineStsCds", this.ackLineStsCds);
            queryParam.put(NPAB090001Constant.BIND_PO_ORD_SRC, this.insourcing);
            queryParam.put(NPAB090001Constant.BIND_PRCH_REQ_TP, this.premiumRush);
            queryParam.put(NPAB090001Constant.BIND_WH_OWNR, this.whOwnrMNX);

            prdStmt = ssmLLClient.createPreparedStatement("getAckData", queryParam, execParam);
            rs = prdStmt.executeQuery();

            //Prepare initial values for PROC_1 case
            String curOrdIdTxt = VAL_EMPTY;
            String lastOrdIdTxt = VAL_EMPTY;
            int curSeqId = Integer.parseInt(DEFAULT_SEGMENT_ID);
            BigDecimal lastTrxId = new BigDecimal(0);

            // Initialize Transaction ID.
            BigDecimal trxId = new BigDecimal(0);

            //Declarations for PROC_2 case
            this.sb = new StringBuilder();
            S21Mail mail = null;
            String lastWh = VAL_EMPTY;
            String curWh = VAL_EMPTY;

            //Declarations for comparing last update time
            String curLastUpdTs = VAL_EMPTY;

            // Loop by ORD_ID_TX
            while (rs.next()) {

                String procCd = rs.getString(NPAB090001Constant.COL_PROC_CD);
                curOrdIdTxt = rs.getString(NPAB090001Constant.COL_ORD_ID_TXT);
                curWh = rs.getString(NPAB090001Constant.COL_VND_INVTY_LOC_CD);

                //Since results from getAckData may not be ordered by time, get the latest time from ezintime
                curLastUpdTs = rs.getString(NPAB090001Constant.COL_EZINTIME);
                if (curLastUpdTs.compareTo(this.lastUpdTs) > 0) {
                    this.lastUpdTs = curLastUpdTs;
                }

                if (procCd.equals("1")) {
                    String positionKey = NLXCMsgHelper.toListedString(NPAB090001Constant.COL_PROC_CD, NPAB090001Constant.COL_ORD_ID_TXT);
                    String position = NLXCMsgHelper.toListedString(this.glblCmpyCd, curOrdIdTxt);

                    boolean isResist = false;
                    // Insert HDR only when curOrdIdTxt is not same as lastOrdIdTxt
                    if (!curOrdIdTxt.equals(lastOrdIdTxt)) {

                        //Get new Transaction ID for new/first ORD_ID_TXT entries
                        trxId = trxAccess.getNextTransactionId();
                        // QC#58209 Start
                        if (ZYPCommonFunc.hasValue(rs.getString(NPAB090001Constant.COL_ORD_ID_TXT))) {
                            BigDecimal cnt = chkRegistIf(this.interfaceId, rs.getString(NPAB090001Constant.COL_ORD_ID_TXT));
                            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                                isResist = true;
                            }
                        }

                        if (isResist) {
                            // Insert to NLBI1410_01
                            NLBI1410_01TMsg if01TMsg = createNLBI1410_01_SO(rs, trxId);
                            EZDTBLAccessor.insert(if01TMsg);
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                                outputErr(NPAB090001Constant.NLGM0045E, new String[] {NPAB090001Constant.TBL_NLBI1410_01, NPAB090001Constant.TBL_NLBI1411, positionKey, position });
                                throw new S21AbendException(NPAB090001Constant.NLGM0045E, new String[] {NPAB090001Constant.TBL_NLBI1410_01, NPAB090001Constant.TBL_NLBI1411, positionKey, position });
                            }
                            //Reset sequence ID for new ORD_ID_TXT
                            curSeqId = 2;
                        }
                        // QC#58209 End
                    }

                    // Create Transaction table.
                    if (isResist && lastTrxId != trxId) {
                        trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);
                    }

                    this.successCount++;
                    curSeqId++;

                    //Set last order to current OrdIdTxt
                    lastOrdIdTxt = curOrdIdTxt;

                    //Set last TransactionID
                    lastTrxId = trxId;

                } else if (procCd.equals("2")) {
                    //send mail

                    mail = sendMail(rs, curWh, lastWh, mail);
                    lastWh = curWh;

                }
            }
            //send last mail
            if (mail != null) {
                // Post
                mail.postMail();
            }
        } catch (SQLException e) {
              EZDDebugOutput.println(NPAB090001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
              sqlExceptionHandler(e);
           } finally {
               S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    /**
     * createNLBI1410_01_SO
     * @param execParam
     */
    private NLBI1410_01TMsg createNLBI1410_01_SO(ResultSet so, BigDecimal trxId) throws SQLException {
        NLBI1410_01TMsg tMsg = new NLBI1410_01TMsg();

        // INTERFACE_ID
        tMsg.interfaceId.setValue(interfaceId);
        // TRANSACTION_ID
        tMsg.transactionId.setValue(trxId);
        // SEGMENT_ID
        tMsg.segmentId.setValue(1);
        // UNIT_ID
        tMsg.unitId.setValue(1);
        // SEQ_NUMBER
        tMsg.seqNumber.setValue(1);

        // TPL_FROM_PTNR_ID. Mod QC#58209
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, so.getString(NPAB090001Constant.COL_TPL_FROM_PTNR_ID));

        // TPL_TO_PTNR_ID. Mod QC#58209
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, so.getString(NPAB090001Constant.COL_TPL_TO_PTNR_ID));

        // ORD_ID_TXT
        if (so.getString(NPAB090001Constant.COL_ORD_ID_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, so.getString(NPAB090001Constant.COL_ORD_ID_TXT));
        }

        // TPL_ACCT_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, so.getString(NPAB090001Constant.COL_TPL_ACCT_CD));

        // SHIP_NM_ALL_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_NM_ALL_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, so.getString(NPAB090001Constant.COL_SHIP_NM_ALL_TXT));
        }

        // SHIP_ADDR_ALL_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_ADDR_ALL_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, so.getString(NPAB090001Constant.COL_SHIP_ADDR_ALL_TXT));
        }

        // SHIP_CTY_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_CTY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, so.getString(NPAB090001Constant.COL_SHIP_CTY_TXT));
        }
        // SHIP_ST_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_ST_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStTxt, so.getString(NPAB090001Constant.COL_SHIP_ST_TXT));
        }
        // SHIP_POST_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_POST_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPostTxt, so.getString(NPAB090001Constant.COL_SHIP_POST_TXT));
        }
        // SHIP_CTRY_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_CTRY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, so.getString(NPAB090001Constant.COL_SHIP_CTRY_TXT));
        }
        // SHIP_CTAC_NM_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_CTAC_NM_TXT) != null) {
            int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
            String ctacPsnNm = so.getString(NPAB090001Constant.COL_SHIP_CTAC_NM_TXT);
            if (ctacPsnNm.length() > digit) {
                ctacPsnNm = ctacPsnNm.substring(0, digit);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
        }
        // SHIP_PHO_NUM_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_PHO_NUM_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, so.getString(NPAB090001Constant.COL_SHIP_PHO_NUM_TXT));

        }
        // ORD_CMNT_ALL_TXT
        if (so.getString(NPAB090001Constant.COL_ORD_CMNT_ALL_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, so.getString(NPAB090001Constant.COL_ORD_CMNT_ALL_TXT));
        }

        // ORIG_ORD_QTY_TXT
        if (so.getString(NPAB090001Constant.COL_ORIG_ORD_QTY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, so.getString(NPAB090001Constant.COL_ORIG_ORD_QTY_TXT));
        }

        //WMS_REC_ID
        if (so.getString(NPAB090001Constant.COL_WMS_REC_HDR_ID) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, so.getString(NPAB090001Constant.COL_WMS_REC_HDR_ID));
        }

//        //SHIP_TO_CUST_CD
        if (so.getString(NPAB090001Constant.COL_SHIP_TO_CUST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, so.getString(NPAB090001Constant.COL_SHIP_TO_CUST_CD));
        }

        //SVC_TP_TXT
        if (so.getString(NPAB090001Constant.COL_SVC_TP_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, so.getString(NPAB090001Constant.COL_SVC_TP_TXT));
        }

        //SHIP_FROM_NM_ALL_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_NM_ALL_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromNmAllTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_NM_ALL_TXT));
        }

        //SHIP_FROM_ADDR_ALL_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_ADDR_ALL_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromAddrAllTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_ADDR_ALL_TXT));
        }

        //SHIP_FROM_CTY_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_CTY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtyTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_CTY_TXT));
        }

        //SHIP_FROM_ST_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_ST_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromStTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_ST_TXT));
        }

        //SHIP_FROM_POST_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_POST_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromPostTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_POST_TXT));
        }

        //SHIP_FROM_CTRY_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_CTRY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtryTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_CTRY_TXT));
        }

        //SHIP_FROM_CTAC_NM
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_CTAC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtacNm, so.getString(NPAB090001Constant.COL_SHIP_FROM_CTAC_NM));
        }

        //SHIP_FROM_PHO_NUM
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_PHO_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromPhoNum, so.getString(NPAB090001Constant.COL_SHIP_FROM_PHO_NUM));
        }

        //SHIP_FROM_CMNT_ALL_TXT
        if (so.getString(NPAB090001Constant.COL_SHIP_FROM_CMNT_ALL_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromCmntAllTxt, so.getString(NPAB090001Constant.COL_SHIP_FROM_CMNT_ALL_TXT));
        }

        Map<String, Object> tplCarrSvcLvl = null;
        tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(this.glblCmpyCd, this.whOwnrMNX, so.getString("PO_CARR_CD"), so.getString("PO_SHPG_SVC_LVL_CD"));

        // TPL_CARR_CD
        if (so.getString(NPAB090001Constant.COL_TPL_CARR_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.carrCd, so.getString(NPAB090001Constant.COL_TPL_CARR_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) tplCarrSvcLvl.get("TPL_CARR_CD"));
        }

        // TPL_CARR_NM
        if (so.getString(NPAB090001Constant.COL_TPL_CARR_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplCarrNm, so.getString(NPAB090001Constant.COL_TPL_CARR_NM));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.tplCarrNm, (String) tplCarrSvcLvl.get("TPL_CARR_NM"));
        }

        // TPL_SVC_LVL_CD
        if (so.getString(NPAB090001Constant.COL_TPL_SVC_LVL_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, so.getString(NPAB090001Constant.COL_TPL_SVC_LVL_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) tplCarrSvcLvl.get("TPL_SVC_LVL_CD"));
        }

        // TPL_SVC_LVL_NM
        if (so.getString(NPAB090001Constant.COL_TPL_SVC_LVL_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplSvcLvlNm, so.getString(NPAB090001Constant.COL_TPL_SVC_LVL_NM));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.tplSvcLvlNm, (String) tplCarrSvcLvl.get("TPL_SVC_LVL_NM"));
        }

        // TPL_SPCL_SVC_NM
        if (so.getString(NPAB090001Constant.COL_TPL_SPCL_SVC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplSpclSvcNm, so.getString(NPAB090001Constant.COL_TPL_SPCL_SVC_NM));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.tplSpclSvcNm, (String) tplCarrSvcLvl.get("TPL_SPCL_SVC_NM"));
        }

        return tMsg;
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
     * Send Mail Notification
     * @param ResultSet so
     * @param String curWh
     * @param String lastWh
     * @param StringBuilder sb
     * @param S21Mail mail
     */
    private S21Mail sendMail(ResultSet so, String curWh, String lastWh, S21Mail mail) {

        String colReqTechTocCd = VAL_EMPTY;
        String colPrchReqNum = VAL_EMPTY;
        String colFsrNum = VAL_EMPTY;
        String colSvcTaskNum = VAL_EMPTY;
        String colVndSoNum = VAL_EMPTY;
        String colSvcMachSerNum = VAL_EMPTY;
        String colEtaDt = VAL_EMPTY;
        String colPrchReqLineNum = VAL_EMPTY;
        String colMdseCd = VAL_EMPTY;
        String colVndMdseCd = VAL_EMPTY;
        String colOrdQty = VAL_EMPTY;

        if (!lastWh.equals(curWh)) {

            if (mail != null) {
                // Post Email for new VND_ITEM_LOC_CD
                mail.postMail();
                this.sb = new StringBuilder();
            }

            //Only create new instance of mail for new VND_SO unit
            mail = new S21Mail("ADB");
            // Get mail group
            S21MailGroup groupTo = new S21MailGroup("ADB", "NPAB0900");
            // Get mail group
            S21MailGroup groupFrom = new S21MailGroup("ADB", "FROM0005");

            // Set address
            groupTo.setMailKey1(curWh);
            List<S21MailAddress> toAddrList = groupTo.getMailAddress();
            if (toAddrList.isEmpty()) {
                throw new S21AbendException("NPZM0161E");
            }

            mail.setToAddressList(toAddrList);
            List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
            mail.setFromAddress(fromAddrList.get(0));
        }


        S21MailTemplate template = new S21MailTemplate("ADB", "NPAB0900M001");
        if (template == null) {
            throw new S21AbendException("NPZM0161E");
        }
        // Set template parameter
        Date execDate = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat();
        sdfDate.applyPattern("MM/dd/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat();
        sdfTime.applyPattern("HH:mm");

        try {

            if (so.getString(NPAB090001Constant.COL_RQST_TECH_TOC_CD) != null) {
                colReqTechTocCd = so.getString(NPAB090001Constant.COL_RQST_TECH_TOC_CD);
            }

            if (so.getString(NPAB090001Constant.COL_PRCH_REQ_NUM) != null) {
                colPrchReqNum = so.getString(NPAB090001Constant.COL_PRCH_REQ_NUM);
            }

            if (so.getString(NPAB090001Constant.COL_FSR_NUM) != null) {
                colFsrNum = so.getString(NPAB090001Constant.COL_FSR_NUM);
            }

            if (so.getString(NPAB090001Constant.COL_SVC_TASK_NUM) != null) {
                colSvcTaskNum = so.getString(NPAB090001Constant.COL_SVC_TASK_NUM);
            }

            if (so.getString(NPAB090001Constant.COL_SVC_MACH_SER_NUM) != null) {
                colSvcMachSerNum = so.getString(NPAB090001Constant.COL_SVC_MACH_SER_NUM);
            }

            if (so.getString(NPAB090001Constant.COL_VND_SO_NUM) != null) {
                colVndSoNum = so.getString(NPAB090001Constant.COL_VND_SO_NUM);
            }

            if (so.getString(NPAB090001Constant.COL_ETA_DT) != null) {
                colEtaDt = so.getString(NPAB090001Constant.COL_ETA_DT);
            }

            if (so.getString(NPAB090001Constant.COL_PRCH_REQ_LINE_NUM) != null) {
                colPrchReqLineNum = so.getString(NPAB090001Constant.COL_PRCH_REQ_LINE_NUM);
            }

            if (so.getString(NPAB090001Constant.COL_MDSE_CD) != null) {
                colMdseCd = so.getString(NPAB090001Constant.COL_MDSE_CD);
            }

            if (so.getString(NPAB090001Constant.COL_VND_MDSE_CD) != null) {
                colVndMdseCd = so.getString(NPAB090001Constant.COL_VND_MDSE_CD);
            }

            if (so.getString(NPAB090001Constant.COL_ORD_QTY) != null) {
                colOrdQty = so.getString(NPAB090001Constant.COL_ORD_QTY);
            }

            // Create Message
            this.sb.append("Technician Number             : " + colReqTechTocCd + "\n");
            this.sb.append("Request Number                : " + colPrchReqNum + "\n");
            this.sb.append("Service Request Number        : " + colFsrNum + "\n");
            this.sb.append("Service Request Task Number   : " + colSvcTaskNum + "\n");
            this.sb.append("Service Request Serial Number : " + colSvcMachSerNum + "\n");
            this.sb.append("Vendor Shipment Number        : " + colVndSoNum + "\n");
            this.sb.append("Estimated Time of Arrival     : " + colEtaDt + "\n");
            this.sb.append("Request Line Number           : " + colPrchReqLineNum + "\n");
            this.sb.append("Request Item Number           : " + colMdseCd + "\n");
            this.sb.append("Vendor Item Number            : " + colVndMdseCd + "\n");
            this.sb.append("Vendor Quantity               : " + colOrdQty  + "\n");
            this.sb.append("--------------------------------------------------------------------" + "\n");
        } catch (SQLException e) {
            EZDDebugOutput.println(NPAB090001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        }

        // set Mail Body Parameter
        template.setTemplateParameter("message", this.sb.toString());

        // set Mail Subject Parameter
        template.setTemplateParameter("TODAY", sdfDate.format(execDate));
        template.setTemplateParameter("TIME", sdfTime.format(execDate));
        template.setTemplateParameter("cusaWh", curWh);

        // Set mail subject
        mail.setSubject(template.getSubject("en"), "ISO-8859-1");
        mail.setMailTemplate(template);

        return mail;
    }

    /**
     * updateDsBizProcLog
     */
    private void updateDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg outMsg = new DS_BIZ_PROC_LOGTMsg();

        if (!ZYPCommonFunc.hasValue(dsBizProcLogPk)) {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, NPAB090001Constant.BUSINESS_ID.concat(processMode));
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(NPAB090001Constant.FMT_YYYYMMDDHHMMSSSSS));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, this.lastUpdTs);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(NPAB090001Constant.DS_BIZ_PROC_LOG_SQ));

            EZDTBLAccessor.insert(outMsg);

        } else {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, dsBizProcLogPk);
            outMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, NPAB090001Constant.BUSINESS_ID.concat(processMode));
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(NPAB090001Constant.FMT_YYYYMMDDHHMMSSSSS));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, this.lastUpdTs);

            EZDTBLAccessor.update(outMsg);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            StringBuilder sbMsg = new StringBuilder();

            sbMsg.append(outMsg.getTableName());

            rollback();

            termCd = TERM_CD.ABNORMAL_END;

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAB090001Constant.NPAM1003E, new String[] {sbMsg.toString() }));

        } else {
            //Update Last Update Time
            this.lastUpdTs =  outMsg.dsBizLastUpdTs.getValue();
            commit();

        }

    }

    /**
     * processMNX
     */
    private void processMNX() {
        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] transactionIdList = trxAccessor.getIntegrationRecord(this.interfaceId);

        for (BigDecimal transactionId : transactionIdList) {
            createMnxOrderForTransactionId(transactionId);
            trxAccess.endIntegrationProcess(interfaceId, transactionId);
        }

        commit();
    }

    /**
     * createMnxOrderForTransactionId
     * @param transactionId
     */
    private void createMnxOrderForTransactionId(BigDecimal transactionId) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NPAB090001Constant.FETCH_SIZE);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("interfaceId", this.interfaceId);
        queryParam.put("transactionId", transactionId);
        queryParam.put("whOwnrMNX", this.whOwnrMNX);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;
        NglcreateOrderResponse response = null;

        try {
            // Get Interface Data
            prdStmt = ssmLLClient.createPreparedStatement("getIfData", queryParam, execParam);
            rs = prdStmt.executeQuery();

            // Create Order Request
            NglcreateOrderRequest request = createMnxRequest(rs);

            // Send Order Request
            response = sendMnxRequest(request);

            StatusType stType = response.getStatus();
            if (NPAB090001Constant.VAL_MNX_RESPONSE_TYPE_ERROR.equals(stType.getResponseType())) {
                // QC#58137 Start
                this.errorCount++;
                String errCd = stType.getCode();
                String msgWithRefNum = stType.getMessage() + " Ref#:[" + response.getRefNum() + "]";

                if (ZYPCommonFunc.hasValue(errCd)) {
                    Map<String, Object> errMap = getMnxErrCond(glblCmpyCd, errCd);
                    if (errMap != null && !errMap.isEmpty()) {

                        String flAbend = (String) errMap.get("FL_ABEND");
                        String mlTp = (String) errMap.get("ML_TP");

                        if (ZYPCommonFunc.hasValue(flAbend) && ZYPConstant.FLG_ON_Y.equals(flAbend)) {
                            S21InfoLogOutput.println(errCd + " " + msgWithRefNum);
                            throw new S21AbendException(errCd + " : " + msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "0".equals(mlTp)){
                            outputMnxErrIt(errCd, msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "1".equals(mlTp)){
                            outputMnxErrUser(errCd, msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "2".equals(mlTp)){
                            outputMnxErrIt(errCd, msgWithRefNum);
                            outputMnxErrUser(errCd, msgWithRefNum);
                        } else {
                            outputMnxErrIt(errCd, msgWithRefNum);
                        }

                    } else {
                        outputMnxErrIt(errCd, msgWithRefNum);
                    }

                } else {
                    outputMnxErrIt("UNKNOWN", msgWithRefNum);
                }
                // QC#58137 End
            }
            

            this.successCount++;

        } catch (SQLException e) {
            EZDDebugOutput.println(NPAB090001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        // QC#58137
        } catch (S21AbendException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            // QC#58137 Start
            if (isAbend) {
                throw new S21AbendException(e.getMessage());
            } else {
                // START 2021/01/26 A.Marte [QC#58281, MOD]
                outputMnxErrKeyIt("NLBM1257E", e.getMessage(), transactionId, interfaceId, errItemList);
                // END 2021/01/26 A.Marte [QC#58281, MOD]
            }
            // QC#58137 end
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * createMnxRequest
     * @param ResultSet rs
     */
    private NglcreateOrderRequest createMnxRequest(ResultSet rs) throws SQLException {

        // nglcreateOrderRequest
        NglcreateOrderRequest request = new NglcreateOrderRequest();

        // Order Information
        NglOrdersType orderHead = new NglOrdersType();

        // Order Detail Information
        NglOrderType order = new NglOrderType();

        if (rs.next()) {
            // QC#58151
            // To convert time to location time zone
//            String pickupTime = ZYPDateUtil.getCurrentSystemTime(NPAB090001Constant.FMT_MMDDYYYYHHMM);

            // Order Header
            order.setOrderPerson(rs.getString(NPAB090001Constant.COL_SHIP_CTAC_NM_TXT));
            order.setOrderPersonPhoneNum(rs.getString(NPAB090001Constant.COL_SHIP_PHO_NUM_TXT));
            // QC#58151
            order.setPickupReadyDateTime("");
            order.setRefNum(rs.getString(NPAB090001Constant.COL_ORD_ID_TXT));

            // From Location Information
            PULocationInfoType fromLoc = new PULocationInfoType();
            String shipFromNmAllTxt = rs.getString(NPAB090001Constant.COL_SHIP_FROM_NM_ALL_TXT);
            if (ZYPCommonFunc.hasValue(shipFromNmAllTxt) && shipFromNmAllTxt.length() > NPAB090001Constant.MAXLEN_COMPANY_NAME) {
                shipFromNmAllTxt = shipFromNmAllTxt.substring(0, NPAB090001Constant.MAXLEN_COMPANY_NAME);
            }

            fromLoc.setCompanyName(shipFromNmAllTxt);
            fromLoc.setAddr1(rs.getString(NPAB090001Constant.COL_SHIP_FROM_ADDR_ALL_TXT));
            fromLoc.setCity(rs.getString(NPAB090001Constant.COL_SHIP_FROM_CTY_TXT));
            fromLoc.setState(rs.getString(NPAB090001Constant.COL_SHIP_FROM_ST_TXT));
            fromLoc.setZipCode(rs.getString(NPAB090001Constant.COL_SHIP_FROM_POST_TXT));
            String ctryCd = rs.getString(NPAB090001Constant.COL_SHIP_FROM_CTRY_TXT);
            if (ZYPCommonFunc.hasValue(ctryCd)) {
                if (CTRY.UNITED_STATES_OF_AMERICA.equals(ctryCd)) {
                    fromLoc.setCountry(CountryCodeType.USA);
                } else {
                    fromLoc.setCountry(CountryCodeType.fromValue(ctryCd));
                }
            }
            fromLoc.setRes(ZYPConstant.FLG_OFF_N);
            fromLoc.setContactName(rs.getString(NPAB090001Constant.COL_SHIP_FROM_CTAC_NM));
            fromLoc.setContactPhone(rs.getString(NPAB090001Constant.COL_SHIP_FROM_PHO_NUM));
            fromLoc.setInstructions(rs.getString(NPAB090001Constant.COL_SHIP_FROM_CMNT_ALL_TXT));

            // To Location Information
            LocationInfoType toLoc = new LocationInfoType();
//            toLoc.setFslId(rs.getString(NPAB090001Constant.COL_SHIP_TO_CUST_CD));
            String shipToNmAllTxt = rs.getString(NPAB090001Constant.COL_SHIP_NM_ALL_TXT);
            if (ZYPCommonFunc.hasValue(shipToNmAllTxt) && shipToNmAllTxt.length() > NPAB090001Constant.MAXLEN_COMPANY_NAME) {
                shipToNmAllTxt = shipToNmAllTxt.substring(0, NPAB090001Constant.MAXLEN_COMPANY_NAME);
            }
            toLoc.setCompanyName(shipToNmAllTxt);
            toLoc.setAddr1(rs.getString(NPAB090001Constant.COL_SHIP_ADDR_ALL_TXT));
            toLoc.setCity(rs.getString(NPAB090001Constant.COL_SHIP_CTY_TXT));
            toLoc.setState(rs.getString(NPAB090001Constant.COL_SHIP_ST_TXT));
            toLoc.setZipCode(rs.getString(NPAB090001Constant.COL_SHIP_POST_TXT));
            String toCtryCd = rs.getString(NPAB090001Constant.COL_SHIP_CTRY_TXT);
            if (ZYPCommonFunc.hasValue(toCtryCd) && CTRY.UNITED_STATES_OF_AMERICA.equals(toCtryCd)) {
                toLoc.setCountry(CountryCodeType.USA);
            } else {
                toLoc.setCountry(CountryCodeType.fromValue(toCtryCd));
            }
            toLoc.setRes(ZYPConstant.FLG_OFF_N);
            toLoc.setContactName(rs.getString(NPAB090001Constant.COL_SHIP_CTAC_NM_TXT));
            toLoc.setContactPhone(rs.getString(NPAB090001Constant.COL_SHIP_PHO_NUM_TXT));
            toLoc.setInstructions(rs.getString(NPAB090001Constant.COL_ORD_CMNT_ALL_TXT));

            // Carrier Information
            CarrierInfoType carrType = new CarrierInfoType();
            String tplCarrNm = rs.getString(NPAB090001Constant.COL_TPL_CARR_NM);
            if (ZYPCommonFunc.hasValue(tplCarrNm)) {
                carrType.setShipper(ShipperType.fromValue(tplCarrNm));
            }
            carrType.setShipMethod(rs.getString(NPAB090001Constant.COL_TPL_SVC_LVL_NM));
            carrType.setSpecialShipMethod(rs.getString(NPAB090001Constant.COL_TPL_SPCL_SVC_NM));

            // Reference
            RefLoop refLoop = new RefLoop();
            List<RefInfoType> refList = refLoop.getRefInfo();
            RefInfoType refInfoType = new RefInfoType();
            refInfoType.setRef(rs.getString(NPAB090001Constant.COL_SHIP_TO_CUST_CD));
            refList.add(refInfoType);

            order.setFromInfo(fromLoc);
            order.setToInfo(toLoc);
            order.setCarrierInfo(carrType);
            order.setRefLoop(refLoop);

            // START 2021/01/26 A.Marte [QC#58281, ADD]
            // Save Segment and Order for Error Info
            Map<String, String> orderParam = new HashMap<String, String>();
            orderParam.put(NPAB090001Constant.COL_SEGMENT_ID, rs.getString(NPAB090001Constant.COL_SEGMENT_ID));
            orderParam.put(NPAB090001Constant.COL_ORD_ID_TXT, rs.getString(NPAB090001Constant.COL_ORD_ID_TXT));
            errItemList.add(orderParam);
            // END 2021/01/26 A.Marte [QC#58281, ADD]
        }

        // Set request parameters.
        orderHead.setNglOrder(order);
        request.setNglOrders(orderHead);

        return request;
    }

    /**
     * sendMnxRequest
     * @param NglcreateOrderRequest request
     */
    private NglcreateOrderResponse sendMnxRequest(NglcreateOrderRequest request) throws Throwable {

        NglcreateOrderResponse response = null;

        try {
            String invokeMode = ZYPCodeDataUtil.getVarCharConstValue(NPAB090001Constant.VAR_CHAR_CONST_NM_MNX_INVOKE_MODE, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(invokeMode) && NPAB090001Constant.VAL_MNX_INVOKE_MODE_STUB_SUCCESS.equals(invokeMode)) {
                response = stub_stiShippingOrder_Success(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && NPAB090001Constant.VAL_MNX_INVOKE_MODE_STUB_ERROR.equals(invokeMode)) {
                response = stub_stiShippingOrder_ApplicationError(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && NPAB090001Constant.VAL_MNX_INVOKE_MODE_STUB_TIMEOUT.equals(invokeMode)) {
                throw new Exception("Timeout expired. The timeout period elapsed prior to completion of the operation or the server is not responding.");
            } else {
                NglShippingOrderServiceProxy soServiceProxy = new NglShippingOrderServiceProxy();
                response = soServiceProxy.nglShippingOrder(request);
            }
        } catch (Throwable t) {
            throw t;
        }

        return response;
    }

    /**
     * outputMnxErr
     */
    private void outputMnxErr(String errCd, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
        mailParam.put(NLXMailSend.KEY_MESSAGE, errMsg);
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(errCd + " " + errMsg);
    }

    /**
     * stub_stiShippingOrder_Success
     * @param NglcreateOrderRequest request
     */
    private static NglcreateOrderResponse stub_stiShippingOrder_Success(NglcreateOrderRequest request) {

        StatusType stType = new StatusType();
        stType.setResponseType(NPAB090001Constant.VAL_MNX_RESPONSE_TYPE_SUCCESS);
        stType.setCode("");
        stType.setMessage("");

        NglcreateOrderResponse response = new NglcreateOrderResponse();
        response.setStatus(stType);
        response.setOrderNumber(NPAB090001Constant.STUB_SO_ORDER_NUM);
        response.setPickupNumber(NPAB090001Constant.STUB_SO_PICKUP_NUM);
        response.setVoucherNumber(NPAB090001Constant.STUB_SO_VOUCHER_NUM);
        response.setRefNum(request.getNglOrders().getNglOrder().getRefNum());

        return response;
    }

    /**
     * stub_stiShippingOrder_ApplicationError
     * @param NglcreateOrderRequest request
     */
    private static NglcreateOrderResponse stub_stiShippingOrder_ApplicationError(NglcreateOrderRequest request) {

        StatusType stType = new StatusType();
        stType.setResponseType(NPAB090001Constant.VAL_MNX_RESPONSE_TYPE_ERROR);
        stType.setCode("NE0001");
        stType.setMessage("Value of 'UserName' is invalid.");

        NglcreateOrderResponse response = new NglcreateOrderResponse();
        response.setStatus(stType);
        response.setRefNum(request.getNglOrders().getNglOrder().getRefNum());

        return response;
    }

    public Map<String, Object> getTplCarrSvcLvlWithEffort(String gCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> tplCarrSvcLvl = null;

        tplCarrSvcLvl = getTplCarrSvcLvl(gCmpyCd, whOwnrCd, carrCd, shpgSvcLvlCd);
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(gCmpyCd, whOwnrCd, null, shpgSvcLvlCd);
        }
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(gCmpyCd, whOwnrCd, null, null);
        }

        return tplCarrSvcLvl;
    }
    private Map<String, Object> getTplCarrSvcLvl(String gCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", gCmpyCd);
        queryParam.put("WH_OWNR_CD", whOwnrCd);
        queryParam.put("CARR_CD", carrCd);
        queryParam.put("SHPG_SVC_LVL_CD", shpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam);
    }

    // QC#58137 Start
    private void outputMnxErrIt(String errCd, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
        mailParam.put(NLXMailSend.KEY_MESSAGE, errMsg);
        errMsgList4It.add(mailParam);

        S21InfoLogOutput.println(errCd + " " + errMsg);
    }

    private void outputMnxErrUser(String errCd, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
        mailParam.put(NLXMailSend.KEY_MESSAGE, errMsg);
        errMsgList4User.add(mailParam);

        S21InfoLogOutput.println(errCd + " " + errMsg);
    }

    private Map<String, Object> getMnxErrCond(String gCmpyCd, String errCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", gCmpyCd);
        queryParam.put("DS_COND_CONST_GRP_ID", "MNX_API_ERR_CD");
        queryParam.put("DS_COND_CONST_CD", errCd);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMnxErrCond", queryParam, execParam);
    }
    // QC#58137 end

    // QC#58209
    private BigDecimal chkRegistIf(String interfaceId, String ordIdTxt) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("interfaceId", interfaceId);
        queryParam.put("ordIdTxt", ordIdTxt);

        return (BigDecimal) this.ssmBatchClient.queryObject("chkRegistIf", queryParam, execParam);
    }

    // START 2021/01/26 A.Marte [QC#58281, ADD]
    private void outputMnxErrKeyIt(String errCd, String errMsg, BigDecimal transactionId, String ifId, List<Map<String, String>> errItemsList) {

        if (errItemsList.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append(generateErrorInfo(errMsg, transactionId, ifId, null, null));

            Map<String, String> mailParam = new HashMap<String, String>();
            mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
            mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
            errMsgList4It.add(mailParam);

            S21InfoLogOutput.println(errCd + " " + builder.toString());


        } else {
            // Loop through all Error Items
            for (Map<String, String> orderItem : errItemsList) {
                StringBuilder builder = new StringBuilder();

                String errSegmentId = orderItem.get(NPAB090001Constant.COL_SEGMENT_ID);
                String errItemId =  orderItem.get(NPAB090001Constant.COL_ORD_ID_TXT);

                builder.append(generateErrorInfo(errMsg, transactionId, ifId, errSegmentId, errItemId));

                Map<String, String> mailParam = new HashMap<String, String>();
                mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
                mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
                errMsgList4It.add(mailParam);

                S21InfoLogOutput.println(errCd + " " + builder.toString());
            }
        }
    }

    private String generateErrorInfo(String errMsg, BigDecimal transactionId, String ifId, String errSegmentId, String errItemId) {

        StringBuilder builder = new StringBuilder();
        builder.append(errMsg);
        builder.append(NPAB090001Constant.LINE_SEPT);
        builder.append(NPAB090001Constant.VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append(ifId);
        builder.append(NPAB090001Constant.LINE_SEPT);
        builder.append(NPAB090001Constant.VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        if (ZYPCommonFunc.hasValue(transactionId)) {
            builder.append(transactionId.toPlainString());
        } else {
            builder.append(NPAB090001Constant.VAL_BLANK);
        }
        builder.append(NPAB090001Constant.LINE_SEPT);
        builder.append(NPAB090001Constant.VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue(errSegmentId)) {
            builder.append(errSegmentId);
        } else {
            builder.append(NPAB090001Constant.VAL_BLANK);
        }
        builder.append(NPAB090001Constant.LINE_SEPT);
        builder.append(NPAB090001Constant.VAL_BLANK_14);
        builder.append("Order #           : ");
        if (ZYPCommonFunc.hasValue(errItemId)) {
            builder.append(errItemId);
        } else {
            builder.append(NPAB090001Constant.VAL_BLANK);
        }
        builder.append(NPAB090001Constant.LINE_SEPT);
        builder.append(NPAB090001Constant.VAL_SEP_LINE);

        return builder.toString();
    }
    // END 2021/01/26 A.Marte [QC#58281, ADD]
}
