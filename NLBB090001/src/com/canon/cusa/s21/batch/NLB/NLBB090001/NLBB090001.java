/*
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB090001;

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
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NLBI1410_01TMsg;
import business.db.NLBI1410_02TMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.TPL_LOCTMsg;
import business.db.WMS_INBD_SO_BILL_TOTMsg;
import business.db.WMS_INBD_SO_CHRG_TOTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_SO_SHIP_TOTMsg;
import business.db.WMS_INBD_SO_TEXTTMsg;

import com.canon.cusa.s21.batch.NLB.NLBB090001.constant.NLBB090001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
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
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.CarrierInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.CountryCodeType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.LocationInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrderType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrdersType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglcreateOrderRequest;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglcreateOrderResponse;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.PULocationInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.PartInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.RefInfoType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.ShipperType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.StatusType;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrderType.PartsLoop;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.data.NglOrderType.RefLoop;
import com.canon.usa.s21.integration.service.mnx.integration.nlbi1410.wrapper.NglShippingOrderServiceProxy;

/**
 * <pre>    
 * Business ID : NLBB0900 Ship Parts Request to MNX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/28/2020   CITS            H.Dimay         Create          QC#57659
 * 11/14/2020   CITS            K.Ogino         Update          QC#57659
 * 12/08/2020   CITS            K.Ogino         Update          QC#57659-1
 * 12/08/2020   CITS            K.Ogino         Update          QC#57659-2
 * 12/21/2020   CITS            K.Ogino         Update          QC#58137, QC#58151
 * 01/26/2020   CITS            A.Marte         Update          QC#58281
 * 08/10/2022   CITS            A.Cullano       Update          QC#60324
 * 03/08/2023   Hitachi         T.Kuroda        Update          QC#61103
 * 04/06/2023   Hitachi         TZ.Win          Update          QC#61267
 * 04/26/2023   Hitachi         TZ.Win          Update          QC#61414
 *</pre>
 */
public class NLBB090001 extends S21BatchMain {

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

    /** Commit Count */
    private int commitCount = 0;

    // *********************************************************
    // Instance Variables: Constant values on load
    // *********************************************************

    /** Target whOwnrCd */
    private String trgtWhOwnrCd;

    /** Target PRCH_REQ_LINE_TP_CD to process */
    private String trgtPrchReqLineTpCd;

    /** Batch Proc Date */
    private String salesDate;

    // Add QC#57659-2
    /** Default Ship Contact Name */
    private String defShipCtacNm;
    // Add QC#57659-2
    /** Default Ship Contact Phone number */
    private String defShipPhoNum;

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
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NLBB090001().executeBatch(NLBB090001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of variable
        successCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();

        // Global Company Code
        this.glblCmpyCd = prof.getGlobalCompanyCode();

        // Interface ID
        this.interfaceId = getInterfaceID();

        // WH_OWNR_CD
        this.trgtWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB090001Constant.VAR_CHAR_CONST_NM_TARGET_ORDER, glblCmpyCd);
        //trgtWhOwnrCd = WH_OWNR.MNX;

        // PRCH_REQ_LINE_TP_CD
        this.trgtPrchReqLineTpCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB090001Constant.VAR_CHAR_CONST_NM_TARGET_PR_LINE_TP_CD, glblCmpyCd);
        //trgtPrchReqLineTpCd = PRCH_REQ_LINE_TP.INSOURCED_ST;
        // Add QC#57659-2
        // Default Ship Contact Name
        this.defShipCtacNm = ZYPCodeDataUtil.getVarCharConstValue(NLBB090001Constant.VAR_CHAR_CONST_NM_DEF_SHIP_CTAC_NM, glblCmpyCd);
        // Add QC#57659-2
        // Default Ship Contact Phone number
        this.defShipPhoNum = ZYPCodeDataUtil.getVarCharConstValue(NLBB090001Constant.VAR_CHAR_CONST_NM_DEF_SHIP_PHO_NUM, glblCmpyCd);

        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // QC#58137
        String constVal = ZYPCodeDataUtil.getVarCharConstValue("NLBB0900_SYSERR_ABEND_FLG", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal) && ZYPConstant.FLG_ON_Y.equals(constVal)) {
            this.isAbend = true;
        }

        // Check on input parameter
        checkParameter();
    }

    @Override
    protected void mainRoutine() {

        // Create SO Interface data and Transaction table.
        try {
            // Create SO interface data
            createSoIntfcRecords();

            // Create PR Interface data.
            createPrIntfcRecords();

            // Create MNX order requests.
            createMnxOrderRequests();

            commit();

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB090001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
            // QC#58137 Add Start
            if (errMsgList4It.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB090001Constant.BUSINESS_ID, errMsgList4It, new String[]{"MNXAPIM001"});
                commit();
            }
            if (errMsgList4User.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB090001Constant.BUSINESS_ID, errMsgList4User, new String[]{"MNXAPIM002"});
                commit();
            }
            // QC#58137 Add end
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(NLBB090001Constant.ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount)});

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLBB090001Constant.ZZM9000E, new String[] {NLBB090001Constant.PARAM_NM_GLBL_CMPY_CD });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB090001Constant.ZZM9000E, new String[] {NLBB090001Constant.PARAM_NM_INTERFACE_ID });
        }

        // Commit Count
        String str = getUserVariable1();

        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(NLBB090001Constant.ZZM9000E, new String[] {NLBB090001Constant.PARAM_NM_VAR_USER1 });
        }
        try {
            commitCount = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new S21AbendException(NLBB090001Constant.ZZM9004E, new String[] {"Commit Count(" + str + ")" });
        }
    }

    /**
     * createSoInterface
     * @param trxId
     * @param execParam
     */
    private void createSoIntfcRecords() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLBB090001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get SO#
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(NLBB090001Constant.COL_WH_SYS_TP_CD, WH_SYS_TP._3PL);
            queryParam.put(NLBB090001Constant.COL_WH_OWNR_CD, this.trgtWhOwnrCd);
            queryParam.put(NLBB090001Constant.COL_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
            queryParam.put(NLBB090001Constant.BIND_SO_CUST_DATA_TP_CD_SHIP, SO_CUST_DATA_TP.SHIP_TO);
            queryParam.put(NLBB090001Constant.BIND_SO_CUST_DATA_TP_CD_SELL, SO_CUST_DATA_TP.SELL_TO);
            queryParam.put(NLBB090001Constant.BIND_SO_CUST_DATA_TP_CD_BILL, SO_CUST_DATA_TP.BILL_TO);

            prdStmt = ssmLLClient.createPreparedStatement("findSo", queryParam, execParam);
            rs = prdStmt.executeQuery();

            int processedCount = 0;

            // Loop by SO#
            while (rs.next()) {

                String soNum = rs.getString(NLBB090001Constant.COL_SO_NUM);

                // START 2023/04/06 TZ.Win [QC#61267, DEL]
                // To Canada Order is not SO download
//                if (!isShipToUS(rs.getString(NLBB090001Constant.COL_SHIP_CTRY_CD), rs.getString(NLBB090001Constant.COL_SHIP_ST_CD))) {
//                    // Update WMS DropRqstFlg
//                    updateWmsDropRqstFlg(glblCmpyCd, soNum, ZYPConstant.FLG_OFF_N);
//                    ++this.successCount;
//                    continue;
//                }
                // END 2023/04/06 TZ.Win [QC#61267, DEL]

                // get SO Detail
                List<Map<String, Object>> soDetailList = getSoDetailList(glblCmpyCd, soNum);

                // Check processing
                if (!validateIFDataSo(rs, soDetailList)) {
                    rollback();
                    ++this.errorCount;
                    processedCount = 0;
                    continue;
                }

                // get SO Text
                List<Map<String, Object>> soTextList = getSoTextList(glblCmpyCd, soNum);

                // Get CARR_CD and SHPG_SVC_LVL_CD
                Map<String, Object> tplCarrSvcLvl = null;
                String carrCd = rs.getString(NLBB090001Constant.COL_CARR_CD);
                String shpgSvcLvlCd = rs.getString(NLBB090001Constant.COL_SHPG_SVC_LVL_CD);
                tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(this.glblCmpyCd, this.trgtWhOwnrCd, carrCd, shpgSvcLvlCd);

                // Get Transaction ID.(SO)
                BigDecimal trxId = trxAccess.getNextTransactionId();

                String positionKey = NLXCMsgHelper.toListedString(NLBB090001Constant.COL_GLBL_CMPY_CD, NLBB090001Constant.COL_SO_NUM);
                String position = NLXCMsgHelper.toListedString(this.glblCmpyCd, soNum);

                // NLBI1410_01
                NLBI1410_01TMsg if01TMsg = createNLBI1410_01_SO(rs, tplCarrSvcLvl, soDetailList, soTextList, trxId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_01, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                    throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_01, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                }

                // WMS_INBD_SO_HDR
                Map<String, Object> soHeader = convertSoHeaderToMap(rs);
                WMS_INBD_SO_HDRTMsg wmsSoHdrTMsg = createWmsInbdSoHdr(soHeader, tplCarrSvcLvl, this.salesDate);
                EZDTBLAccessor.insert(wmsSoHdrTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoHdrTMsg.getReturnCode())) {
                    outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_HDR, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                    throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_HDR, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                }

                // WMS_INBD_SO_TEXT
                if (soTextList != null && soTextList.size() > 0) {
                    WMS_INBD_SO_TEXTTMsg wmsSoTextTMsg = createWmsInbdSoText(wmsSoHdrTMsg, soTextList);
                    EZDTBLAccessor.insert(wmsSoTextTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoTextTMsg.getReturnCode())) {
                        outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_TEXT, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_TEXT, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_SHIP_TO
                WMS_INBD_SO_SHIP_TOTMsg wmsSoShipToTMsg = createWmsInbdSoShipTo(wmsSoHdrTMsg, soHeader);
                if (wmsSoShipToTMsg != null) {
                    EZDTBLAccessor.insert(wmsSoShipToTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoShipToTMsg.getReturnCode())) {
                        outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_CHRG_TO
                WMS_INBD_SO_CHRG_TOTMsg wmsSoChrgToTMsg = createWmsInbdSoChrgTo(wmsSoHdrTMsg, soHeader);
                if (wmsSoChrgToTMsg != null) {
                    EZDTBLAccessor.insert(wmsSoChrgToTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoChrgToTMsg.getReturnCode())) {
                        outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_BILL_TO
                WMS_INBD_SO_BILL_TOTMsg wmsSoBillToTMsg = createWmsInbdSoBillTo(wmsSoHdrTMsg, soHeader);
                if (wmsSoBillToTMsg != null) {
                    EZDTBLAccessor.insert(wmsSoBillToTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoBillToTMsg.getReturnCode())) {
                        outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_BILL_TO, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_BILL_TO, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // NLBI1410_02
                if (soDetailList != null && soDetailList.size() > 0) {
                    List<NLBI1410_02TMsg> if02TMsgList = createNLBI1410_02_SO(rs, soDetailList, trxId);
                    int count = S21FastTBLAccessor.insert(if02TMsgList.toArray(new NLBI1410_02TMsg[if02TMsgList.size()]));
                    if (count != if02TMsgList.size()) {
                        outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_02, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_02, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_DTL
                if (soDetailList != null && soDetailList.size() > 0) {
                    List<WMS_INBD_SO_DTLTMsg> wmsSoDtlTMsgList = createWmsInbdSoDtlList(wmsSoHdrTMsg, soDetailList);
                    int count = S21FastTBLAccessor.insert(wmsSoDtlTMsgList.toArray(new WMS_INBD_SO_DTLTMsg[wmsSoDtlTMsgList.size()]));
                    if (count != wmsSoDtlTMsgList.size()) {
                        outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_DTL, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_WMS_INBD_SO_DTL, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
                    }

                    updateShpgOrdDtl(wmsSoDtlTMsgList);
                }

                // Create Transaction table.
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);

                // Update WMS send status
                updateWmsDropFlg(glblCmpyCd, soNum, ZYPConstant.FLG_ON_Y);

                ++processedCount;
                if (processedCount >= commitCount) {
                    commit();
                    processedCount = 0;
                }

                ++this.successCount;
            }
            // Loop by SO#

            if (processedCount > 0) {
                commit();
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB090001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSoDetailList(String gCmpyCd, String soNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_SO_NUM, soNum);
        List<Map<String, Object>> soDtlList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSoDetail", queryParam);

        return soDtlList;
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

    private boolean validateIFDataSo(ResultSet so, List<Map<String, Object>> soDetailList) throws SQLException {
        // MANDATORY CHECK
        boolean valid = true;

        String gCmpyCd = so.getString(NLBB090001Constant.COL_GLBL_CMPY_CD);
        String soNum = so.getString(NLBB090001Constant.COL_SO_NUM);
        String position = NLXCMsgHelper.toListedString(gCmpyCd, soNum);

        // TPL_LOC_TXT
        if (so.getString(NLBB090001Constant.COL_TPL_LOC_NM) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_TPL_LOC_NM, NLBB090001Constant.TBL_SHPG_ORD, position });
            valid = false;
        }

        // SHIP_NM_ALL_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01) == null && so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {"SHIP_SO_CUST_LINE_LOC_NM", NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_ADDR_ALL_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01) == null && so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02) == null
                && so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03) == null && so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {"SHIP_SO_CUST_LINE_ADDR", NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_CTY_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_CTY_ADDR) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_CTY_ADDR, NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_ST_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_ST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_ST_CD, NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_POST_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_POST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_POST_CD, NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_FROM_NM_ALL_TXT
        if (so.getString(NLBB090001Constant.COL_FR_SHIP_SO_CUST_LINE_LOC_NM_01) == null && so.getString(NLBB090001Constant.COL_FR_SHIP_SO_CUST_LINE_LOC_NM_02) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {"FR_SHIP_SO_CUST_LINE_LOC_NM", NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_FROM_ADDR_ALL_TXT
        if (so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_01) == null && so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_02) == null
                && so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_03) == null && so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_04) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {"FR_SHIP_SO_CUST_LINE_ADDR", NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_FROM_CTY_TXT
        if (so.getString(NLBB090001Constant.COL_FR_CTY_ADDR) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FR_CTY_ADDR, NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_FROM_ST_TXT
        if (so.getString(NLBB090001Constant.COL_FR_ST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FR_ST_CD, NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_FROM_POST_TXT
        if (so.getString(NLBB090001Constant.COL_FR_POST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FR_POST_CD, NLBB090001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }

        for (int i = 0; i < soDetailList.size(); i++) {
            Map<String, Object> soDetail = soDetailList.get(i);

            String soSlpNum = (String) soDetail.get(NLBB090001Constant.COL_SO_SLP_NUM);
            position = NLXCMsgHelper.toListedString(gCmpyCd, soNum, soSlpNum);

            // QTY_ORD_TXT
            BigDecimal shpgQty = (BigDecimal) soDetail.get(NLBB090001Constant.COL_SHPG_QTY);
            if (shpgQty == null || BigDecimal.ZERO.compareTo(shpgQty) >= 0) {
                outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHPG_QTY, NLBB090001Constant.TBL_SHPG_ORD_DTL, position });
                valid = false;
            }
            // ITEM_CD_TXT
            if (soDetail.get(NLBB090001Constant.COL_MDSE_CD) == null) {
                outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_MDSE_CD, NLBB090001Constant.TBL_SHPG_ORD_DTL, position });
                valid = false;
            }
        }
        return valid;
    }

    private NLBI1410_01TMsg createNLBI1410_01_SO(ResultSet so, Map<String, Object> tplCarrSvcLvl, List<Map<String, Object>> soDetailList, List<Map<String, Object>> soTextList, BigDecimal trxId) throws SQLException {
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

        // TPL_FROM_PTNR_ID
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, NLBB090001Constant.VAL_TPL_FROM_PTNR_ID);
        // TPL_TO_PTNR_ID
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, NLBB090001Constant.VAL_TPL_TO_PTNR_ID);

        // ORD_ID_TXT
        if (so.getString(NLBB090001Constant.COL_SO_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, so.getString(NLBB090001Constant.COL_SO_NUM));
        }
        // CARR_CD, SHPG_SVC_LVL_CD
        if (tplCarrSvcLvl != null) {
            // CARR_CD
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_CD));
            }
            // SHPG_SVC_LVL_CD
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_CD));
            }
            // TPL_CARR_NM
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplCarrNm, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_NM));
            }
            // TPL_SVC_LVL_NM
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplSvcLvlNm, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_NM));
            }
            // TPL_SPCL_SVC_NM
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SPCL_SVC_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplSpclSvcNm, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SPCL_SVC_NM));
            }
        }
        // TPL_ACCT_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, NLBB090001Constant.VAL_TPL_ACCT_TXT);
        // TPL_LOC_TXT
        if (so.getString(NLBB090001Constant.COL_TPL_LOC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, so.getString(NLBB090001Constant.COL_TPL_LOC_NM));
        }
        // SHIP_NM_ALL_TXT
        String shipNmAllTxt = "";
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01) != null) {
            shipNmAllTxt += so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01);
        }
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02) != null) {
            shipNmAllTxt += so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02);
        }
        if (ZYPCommonFunc.hasValue(shipNmAllTxt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, shipNmAllTxt);
        }
        // SHIP_ADDR_ALL_TXT
        String shipAddrAllText = "";
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01) != null) {
            shipAddrAllText += so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01);
        }
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02) != null) {
            shipAddrAllText += so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02);
        }
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03) != null) {
            shipAddrAllText += so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03);
        }
        if (so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04) != null) {
            shipAddrAllText += so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04);
        }
        if (ZYPCommonFunc.hasValue(shipAddrAllText)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, shipAddrAllText);
        }
        // SHIP_CTY_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, so.getString(NLBB090001Constant.COL_SHIP_CTY_ADDR));
        }
        // SHIP_ST_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStTxt, so.getString(NLBB090001Constant.COL_SHIP_ST_CD));
        }
        // SHIP_POST_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPostTxt, so.getString(NLBB090001Constant.COL_SHIP_POST_CD));
        }
        // SHIP_CTRY_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, so.getString(NLBB090001Constant.COL_SHIP_CTRY_CD));
        }

        // Get Technician WH info
        RTL_WHTMsg rtlWhTMsg = null;
        if (ZYPCommonFunc.hasValue(so.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD))) {
            rtlWhTMsg = getRtlWhTMsg(this.glblCmpyCd, so.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
        }

        // SHIP_CTAC_NM_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, so.getString(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_NM));
        } else if (rtlWhTMsg != null) {
            int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
            String ctacPsnNm = rtlWhTMsg.rtlWhNm.getValue();
            if (ctacPsnNm.length() > digit) {
                ctacPsnNm = ctacPsnNm.substring(0, digit);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, this.defShipCtacNm);
        }
        // SHIP_PHO_NUM_TXT
        if (so.getString(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, so.getString(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM));
        } else if (rtlWhTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, rtlWhTMsg.telNum);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, this.defShipPhoNum);
        }

        // ORD_CMNT_ALL_TXT
        String soMsgDescTxt = "";
        for (int i = 0; i < soTextList.size(); i++) {
            if (soTextList.get(i).get(NLBB090001Constant.COL_SO_MSG_DESC_TXT) != null) {
                soMsgDescTxt += (String) soTextList.get(i).get(NLBB090001Constant.COL_SO_MSG_DESC_TXT);
            }
        }
        if (soMsgDescTxt.length() > NLBB090001Constant.SO_TEXT_MAX_SIZE) {
            soMsgDescTxt = soMsgDescTxt.substring(0, NLBB090001Constant.SO_TEXT_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, soMsgDescTxt);

        // ORIG_ORD_QTY_TXT
        if (so.getString(NLBB090001Constant.COL_ORIG_ORD_QTY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, so.getString(NLBB090001Constant.COL_ORIG_ORD_QTY_TXT));
        }
        // WMS_REC_ID
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB090001Constant.VAL_WMS_REC_ID_H);

        // SHIP_TO_CUST_CD
        if (so.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, so.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
        }
        // SVC_TP_TXT
        if (so.getString(NLBB090001Constant.COL_SVC_TP_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, so.getString(NLBB090001Constant.COL_SVC_TP_CD));
        }

        // SHIP_FROM_NM_ALL_TXT
        String shipFromNmAllTxt = "";
        if (so.getString(NLBB090001Constant.COL_FR_SHIP_SO_CUST_LINE_LOC_NM_01) != null) {
            shipFromNmAllTxt += so.getString(NLBB090001Constant.COL_FR_SHIP_SO_CUST_LINE_LOC_NM_01);
        }
        if (so.getString(NLBB090001Constant.COL_FR_SHIP_SO_CUST_LINE_LOC_NM_02) != null) {
            shipFromNmAllTxt += so.getString(NLBB090001Constant.COL_FR_SHIP_SO_CUST_LINE_LOC_NM_02);
        }
        if (ZYPCommonFunc.hasValue(shipFromNmAllTxt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromNmAllTxt, shipFromNmAllTxt);
        }

        // SHIP_FROM_ADDR_ALL_TXT
        String shipFromAddrAllTxt = "";
        if (so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_01) != null) {
            shipFromAddrAllTxt += so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_01);
        }
        if (so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_02) != null) {
            shipFromAddrAllTxt += so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_02);
        }
        if (so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_03) != null) {
            shipFromAddrAllTxt += so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_03);
        }
        if (so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_04) != null) {
            shipFromAddrAllTxt += so.getString(NLBB090001Constant.COL_FR_SO_CUST_LINE_ADDR_04);
        }
        if (ZYPCommonFunc.hasValue(shipFromAddrAllTxt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromAddrAllTxt, shipFromAddrAllTxt);
        }

        // SHIP_FROM_CTY_TXT
        if (so.getString(NLBB090001Constant.COL_FR_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtyTxt, so.getString(NLBB090001Constant.COL_FR_CTY_ADDR));
        }
        // SHIP_FROM_ST_TXT
        if (so.getString(NLBB090001Constant.COL_FR_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromStTxt, so.getString(NLBB090001Constant.COL_FR_ST_CD));
        }
        // SHIP_FROM_POST_TXT
        if (so.getString(NLBB090001Constant.COL_FR_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromPostTxt, so.getString(NLBB090001Constant.COL_FR_POST_CD));
        }
        // SHIP_FROM_CTRY_TXT
        if (so.getString(NLBB090001Constant.COL_FR_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipFromCtryTxt, so.getString(NLBB090001Constant.COL_FR_CTRY_CD));
        }

        // REQ_DT_TM_TS_TXT
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(so.getString(NLBB090001Constant.COL_PRCH_REQ_TP_CD))
                && SHPG_SVC_LVL.SCHD_DELIVERY.equals(so.getString(NLBB090001Constant.COL_SHPG_SVC_LVL_CD))) {

            for (Map<String, Object> soDetail : soDetailList) {

                String rqstRcvDtTm = (String) soDetail.get(NLBB090001Constant.COL_RQST_RCV_DT_TM);
                if (ZYPCommonFunc.hasValue(rqstRcvDtTm) && ZYPCommonFunc.hasValue((String) soDetail.get(NLBB090001Constant.COL_RTL_WH_CD))) {

                    Map<String, Object> shipTo = getShipToCust(this.glblCmpyCd, (String) soDetail.get(NLBB090001Constant.COL_RTL_WH_CD));
                    String postCd = (String) shipTo.get(NLBB090001Constant.COL_POST_CD);
                    String ctryCd = (String) shipTo.get(NLBB090001Constant.COL_CTRY_CD);
                    rqstRcvDtTm = getTimezoneTime(rqstRcvDtTm, postCd, ctryCd, "yyyyMMddHHmm", "MM/dd/yyyy HH:mm");
                    ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, rqstRcvDtTm);
                    break;
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, rqstRcvDtTm);
                    break;
                }
            }
        }

        return tMsg;
    }

    private List<NLBI1410_02TMsg> createNLBI1410_02_SO(ResultSet so, List<Map<String, Object>> soDetailList, BigDecimal trxId) throws SQLException {
        ArrayList<NLBI1410_02TMsg> tMsgList = new ArrayList<NLBI1410_02TMsg>();

        // Detail seqNum always starts from 2.
        int seqNum = 2;

        for (int i = 0; i < soDetailList.size(); ++i, ++seqNum) {
            NLBI1410_02TMsg tMsg = new NLBI1410_02TMsg();

            Map<String, Object> soDetail = soDetailList.get(i);

            // INTERFACE_ID
            tMsg.interfaceId.setValue(interfaceId);
            // TRANSACTION_ID
            tMsg.transactionId.setValue(trxId);
            // SEGMENT_ID
            tMsg.segmentId.setValue(1);
            // UNIT_ID
            tMsg.unitId.setValue(1);
            // SEQ_NUMBER
            tMsg.seqNumber.setValue(seqNum);

            // QTY_ORD_TXT
            BigDecimal shpgQty = (BigDecimal) soDetail.get(NLBB090001Constant.COL_SHPG_QTY);
            if (shpgQty != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, shpgQty.toPlainString());
            }
            // ITEM_CD_TXT
            if (soDetail.get(NLBB090001Constant.COL_MDSE_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, (String) soDetail.get(NLBB090001Constant.COL_MDSE_CD));
            }
            // WMS_REC_ID
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB090001Constant.VAL_WMS_REC_ID_D);
            // TPL_LOC_TXT
            if (so.getString(NLBB090001Constant.COL_TPL_LOC_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, so.getString(NLBB090001Constant.COL_TPL_LOC_NM));
            }
            // ORD_LINE_TXT
            if (soDetail.get(NLBB090001Constant.COL_SO_SLP_NUM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.ordLineTxt, (String) soDetail.get(NLBB090001Constant.COL_SO_SLP_NUM));
            }

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    /**
     * @param gCmpyCd Global Company Code
     * @param soNum Shipping Order Number
     * @param wmsDropFlg New value for WMS_DROP_FLG
     */
    public void updateWmsDropFlg(String gCmpyCd, String soNum, String wmsDropFlg) {
        String positionKey = NLXCMsgHelper.toListedString(NLBB090001Constant.COL_GLBL_CMPY_CD, NLBB090001Constant.COL_SO_NUM);
        String position = NLXCMsgHelper.toListedString(gCmpyCd, soNum);

        SHPG_ORDTMsg tMsg = new SHPG_ORDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.soNum, soNum);

        tMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsDropFlg, wmsDropFlg);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_SHPG_ORD, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});

            throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_SHPG_ORD, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position});
        }
    }

    private void createPrIntfcRecords() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLBB090001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get PR#
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(NLBB090001Constant.COL_PRCH_REQ_REL_STS_CD, PRCH_REQ_REL_STS.IN_COMPLETED);
            queryParam.put(NLBB090001Constant.COL_PRCH_REQ_LINE_TP_CD, trgtPrchReqLineTpCd);
            queryParam.put(NLBB090001Constant.COL_OPEN_STS_FLG, ZYPConstant.FLG_ON_Y);

            prdStmt = ssmLLClient.createPreparedStatement("findPr", queryParam, execParam);
            rs = prdStmt.executeQuery();

            // Loop by PR#
            int processedCount = 0;

            while (rs.next()) {

                // Get Transaction ID.(PR)
                BigDecimal trxId = trxAccess.getNextTransactionId();

                // target PR#
                String prNum = rs.getString(NLBB090001Constant.COL_PRCH_REQ_NUM);

                // get PR Detail
                List<Map<String, Object>> prDetailList = getPrDetail(glblCmpyCd, prNum);

                // Get TPL_CARR_CD and TPL_SVC_LVL_CD
                Map<String, Object> tplCarrSvcLvl = null;
                for (Map<String, Object> prDetail : prDetailList) {

                    String carrCd = (String) prDetail.get(NLBB090001Constant.COL_CARR_CD);
                    String shpgSvcLvlCd = (String) prDetail.get(NLBB090001Constant.COL_SHPG_SVC_LVL_CD);
                    tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(this.glblCmpyCd, this.trgtWhOwnrCd, carrCd, shpgSvcLvlCd);
                    if (tplCarrSvcLvl != null) {
                        break;
                    }
                }

                // Get TPL_LOC_NM
                TPL_LOCTMsg tplLocTMsg = null;
                String srcRtlWhCd = "";
                for (Map<String, Object> prDetail : prDetailList) {

                    srcRtlWhCd = (String) prDetail.get(NLBB090001Constant.COL_SRC_RTL_WH_CD);
                    if (ZYPCommonFunc.hasValue(srcRtlWhCd)) {
                        tplLocTMsg = getTplLocTxt(glblCmpyCd, srcRtlWhCd);
                        if (tplLocTMsg != null && tplLocTMsg.tplLocNm != null) {
                            break;
                        }
                    }
                }
                if (tplLocTMsg == null) {
                    tplLocTMsg = new TPL_LOCTMsg();
                    ZYPEZDItemValueSetter.setValue(tplLocTMsg.tplLocNm, srcRtlWhCd);
                }

                // Get Destination Customer Information
                Map<String, Object> prCustomer = getPrCustomer(glblCmpyCd, prNum);

                // Get Source Customer Information
                Map<String, Object> frCustomer = getFrCustomer(glblCmpyCd, prNum);

                // Validation Check
                if (!validateIFDataPr(rs, prDetailList, tplCarrSvcLvl, tplLocTMsg, prCustomer, frCustomer, prNum)) {
                    rollback();
                    updatePrchReqRelStsCd(glblCmpyCd, prNum, prDetailList, PRCH_REQ_REL_STS.ERROR);
                    commit();
                    processedCount = 0;
                    ++this.errorCount;
                    continue;
                }

                String positionKey = NLXCMsgHelper.toListedString(NLBB090001Constant.COL_GLBL_CMPY_CD, NLBB090001Constant.COL_PRCH_REQ_NUM);
                String position = NLXCMsgHelper.toListedString(this.glblCmpyCd, prNum);

                // create IF Table Data
                NLBI1410_01TMsg if01TMsg = createNLBI1410_01_PR(rs, tplCarrSvcLvl, tplLocTMsg, prDetailList, prCustomer, trxId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_01, NLBB090001Constant.TBL_PRCH_REQ, positionKey, position});

                    throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_01, NLBB090001Constant.TBL_PRCH_REQ, positionKey, position});
                }

                List<NLBI1410_02TMsg> if02TMsgList = createNLBI1410_02_PR(prDetailList, tplLocTMsg, trxId);
                int count = S21FastTBLAccessor.insert(if02TMsgList.toArray(new NLBI1410_02TMsg[if02TMsgList.size()]));
                if (count != if02TMsgList.size()) {
                    outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_02, NLBB090001Constant.TBL_PRCH_REQ, positionKey, position});

                    throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_NLBI1410_02, NLBB090001Constant.TBL_PRCH_REQ, positionKey, position});
                }

                // Create Transaction table.
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);

                // Update PR status
                updatePrchReqRelStsCd(glblCmpyCd, prNum, prDetailList, PRCH_REQ_REL_STS.COMPLEATED);

                ++processedCount;
                if (processedCount >= commitCount) {
                    commit();
                    processedCount = 0;
                }

                ++this.successCount;
            }
            // Loop by PR#

            if (processedCount > 0) {
                commit();
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB090001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getPrDetail(String gCmpyCd, String prNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_PRCH_REQ_NUM, prNum);
        queryParam.put(NLBB090001Constant.COL_PRCH_REQ_LINE_TP_CD, trgtPrchReqLineTpCd);
        // START 2023/03/08 T.Kuroda [QC#61103, ADD]
        queryParam.put(NLBB090001Constant.COL_PRCH_REQ_REL_STS_CD, PRCH_REQ_REL_STS.IN_COMPLETED);
        // END   2023/03/08 T.Kuroda [QC#61103, ADD]

        List<Map<String, Object>> prDtlList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrDetail", queryParam);

        return prDtlList;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getPrCustomer(String gCmpyCd, String prNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_PRCH_REQ_NUM, prNum);

        Map<String, Object> soCstmDtl = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", queryParam);

        return soCstmDtl;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getFrCustomer(String gCmpyCd, String prNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_PRCH_REQ_NUM, prNum);

        Map<String, Object> soCstmDtl = (Map<String, Object>) this.ssmBatchClient.queryObject("getFrCustomer", queryParam);

        return soCstmDtl;
    }

    private TPL_LOCTMsg getTplLocTxt(String gCmpyCd, String tplLocCd) {
        TPL_LOCTMsg inMsg = new TPL_LOCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tplLocCd, tplLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tplStkStsCd, ZYPConstant.FLG_OFF_N);

        TPL_LOCTMsg outMsg = (TPL_LOCTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    private boolean validateIFDataPr(ResultSet pr, List<Map<String, Object>> prDetailList, Map<String, Object> tplCarrSvcLvl, TPL_LOCTMsg tplLocTMsg, Map<String, Object> prCustomer, Map<String, Object> frCustomer, String prNum) throws SQLException {

        // MANDATORY CHECK
        boolean flg = true;

        // TPL_LOC_TXT
        if (tplLocTMsg == null || !ZYPCommonFunc.hasValue(tplLocTMsg.tplLocNm)) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_TPL_LOC_NM, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            flg = false;
        }

        // SHIP_NM_ALL_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_LOC_NM) == null && prCustomer.get(NLBB090001Constant.COL_LOC_NM) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {"SHIP_TO_LOC_NM", NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {"LOC_NM", NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_ADDR_ALL_TXT
        boolean isPrShipToLineAddrNull = pr.getString(NLBB090001Constant.COL_SHIP_TO_FIRST_LINE_ADDR) == null && pr.getString(NLBB090001Constant.COL_SHIP_TO_SCD_LINE_ADDR) == null
            && pr.getString(NLBB090001Constant.COL_SHIP_TO_THIRD_LINE_ADDR) == null && pr.getString(NLBB090001Constant.COL_SHIP_TO_FRTH_LINE_ADDR) == null;
        boolean isPrCustomerLineAddrNull = prCustomer.get(NLBB090001Constant.COL_FIRST_LINE_ADDR) == null && prCustomer.get(NLBB090001Constant.COL_SCD_LINE_ADDR) == null
            && prCustomer.get(NLBB090001Constant.COL_THIRD_LINE_ADDR) == null && prCustomer.get(NLBB090001Constant.COL_FRTH_LINE_ADDR) == null;
        if (isPrShipToLineAddrNull && isPrCustomerLineAddrNull) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_FIRST_LINE_ADDR, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_SCD_LINE_ADDR, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_THIRD_LINE_ADDR, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_FRTH_LINE_ADDR, NLBB090001Constant.TBL_PRCH_REQ, prNum });

            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FIRST_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SCD_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_THIRD_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FRTH_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });

            flg = false;
        }
        // SHIP_CTY_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_CTY_ADDR) == null && prCustomer.get(NLBB090001Constant.COL_CTY_ADDR) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_CTY_ADDR, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_CTY_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_ST_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_ST_CD) == null && prCustomer.get(NLBB090001Constant.COL_ST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_ST_CD, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_ST_CD, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_POST_CD_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_POST_CD) == null && prCustomer.get(NLBB090001Constant.COL_POST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SHIP_TO_POST_CD, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_POST_CD, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // LOC_NM_TXT
        if (frCustomer.get(NLBB090001Constant.COL_LOC_NM) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_LOC_NM, NLBB090001Constant.TBL_PRCH_REQ, prNum });
            flg = false;
        }
        // SHIP_FROM_ADDR_ALL_TXT
        if (frCustomer.get(NLBB090001Constant.COL_FIRST_LINE_ADDR) == null && frCustomer.get(NLBB090001Constant.COL_SCD_LINE_ADDR) == null
                && frCustomer.get(NLBB090001Constant.COL_THIRD_LINE_ADDR) == null && frCustomer.get(NLBB090001Constant.COL_FRTH_LINE_ADDR) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FIRST_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_SCD_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_THIRD_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_FRTH_LINE_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });

            flg = false;
        }
        // SHIP_FROM_CTY_TXT
        if (frCustomer.get(NLBB090001Constant.COL_CTY_ADDR) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_CTY_ADDR, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_FROM_ST_TXT
        if (frCustomer.get(NLBB090001Constant.COL_ST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_ST_CD, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_FROM_POST_TXT
        if (frCustomer.get(NLBB090001Constant.COL_POST_CD) == null) {
            outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_POST_CD, NLBB090001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }

        for (int i = 0; i < prDetailList.size(); i++) {
            Map<String, Object> prDetail = prDetailList.get(i);
            // QTY_ORD_TXT
            BigDecimal ordQty = (BigDecimal) prDetail.get(NLBB090001Constant.COL_PRCH_REQ_QTY);
            if (ordQty == null || BigDecimal.ZERO.compareTo(ordQty) >= 0) {
                outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_PRCH_REQ_QTY, NLBB090001Constant.TBL_PRCH_REQ_DTL, prNum });
                flg = false;
            }
            // ITEM_CD_TXT
            if (prDetail.get(NLBB090001Constant.COL_MDSE_CD) == null) {
                outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_MDSE_CD, NLBB090001Constant.TBL_PRCH_REQ_DTL, prNum });
                flg = false;
            }
            // PRCH_REQ_LINE_NUM
            if (prDetail.get(NLBB090001Constant.COL_PRCH_REQ_LINE_NUM) == null) {
                outputErr(NLBB090001Constant.NLGM0041E, new String[] {NLBB090001Constant.COL_PRCH_REQ_LINE_NUM, NLBB090001Constant.TBL_PRCH_REQ_DTL, prNum });
                flg = false;
            }
        }
        return flg;
    }

    private NLBI1410_01TMsg createNLBI1410_01_PR(ResultSet pr, Map<String, Object> tplCarrSvcLvl, TPL_LOCTMsg tplLocTMsg, List<Map<String, Object>> prDetailList, Map<String, Object> prCustomer, BigDecimal trxId) throws SQLException {
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

        // TPL_FROM_PTNR_ID
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, NLBB090001Constant.VAL_TPL_FROM_PTNR_ID);
        // TPL_TO_PTNR_ID
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, NLBB090001Constant.VAL_TPL_TO_PTNR_ID);
        // REQ_DT_TM_TS_TXT - Not set
        // ORD_ID_TXT
        if (pr.getString(NLBB090001Constant.COL_PRCH_REQ_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, pr.getString(NLBB090001Constant.COL_PRCH_REQ_NUM));
        }
        // CARR_CD, SHPG_SVC_LVL_CD
        if (tplCarrSvcLvl != null) {
            // CARR_CD
            ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_CD));
            // TPL_CARR_NM
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplCarrNm, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_NM));
            }
            // SHPG_SVC_LVL_CD
            ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_CD));
            // TPL_SHPG_SVC_LVL_NM
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplSvcLvlNm, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_NM));
            }
            // TPL_SPCL_SVC_NM
            if (tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SPCL_SVC_NM) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplSpclSvcNm, (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SPCL_SVC_NM));
            }
        }
        // TPL_ACCT_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, NLBB090001Constant.VAL_TPL_ACCT_TXT);
        // TPL_LOC_TXT
        if (tplLocTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, tplLocTMsg.tplLocNm);
        }
        // SHIP_NM_ALL_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_LOC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, pr.getString(NLBB090001Constant.COL_SHIP_TO_LOC_NM));
        } else if (prCustomer.get(NLBB090001Constant.COL_LOC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, (String) prCustomer.get(NLBB090001Constant.COL_LOC_NM));
        }
        // SHIP_ADDR_ALL_TXT
        String shipAddrAllText = "";
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_FIRST_LINE_ADDR) != null) {
            shipAddrAllText += pr.getString(NLBB090001Constant.COL_SHIP_TO_FIRST_LINE_ADDR);

            if (pr.getString(NLBB090001Constant.COL_SHIP_TO_SCD_LINE_ADDR) != null) {
                shipAddrAllText += pr.getString(NLBB090001Constant.COL_SHIP_TO_SCD_LINE_ADDR);
            }
            if (pr.getString(NLBB090001Constant.COL_SHIP_TO_THIRD_LINE_ADDR) != null) {
                shipAddrAllText += pr.getString(NLBB090001Constant.COL_SHIP_TO_THIRD_LINE_ADDR);
            }
            if (pr.getString(NLBB090001Constant.COL_SHIP_TO_FRTH_LINE_ADDR) != null) {
                shipAddrAllText += pr.getString(NLBB090001Constant.COL_SHIP_TO_FRTH_LINE_ADDR);
            }
        } else if (prCustomer.get(NLBB090001Constant.COL_FIRST_LINE_ADDR) != null) {
            shipAddrAllText += (String) prCustomer.get(NLBB090001Constant.COL_FIRST_LINE_ADDR);

            if (prCustomer.get(NLBB090001Constant.COL_SCD_LINE_ADDR) != null) {
                shipAddrAllText += (String) prCustomer.get(NLBB090001Constant.COL_SCD_LINE_ADDR);
            }
            if (prCustomer.get(NLBB090001Constant.COL_THIRD_LINE_ADDR) != null) {
                shipAddrAllText += (String) prCustomer.get(NLBB090001Constant.COL_THIRD_LINE_ADDR);
            }
            if (prCustomer.get(NLBB090001Constant.COL_FRTH_LINE_ADDR) != null) {
                shipAddrAllText += (String) prCustomer.get(NLBB090001Constant.COL_FRTH_LINE_ADDR);
            }
        }
        ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, shipAddrAllText);

        // SHIP_CTY_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, pr.getString(NLBB090001Constant.COL_SHIP_TO_CTY_ADDR));
        } else if (prCustomer.get(NLBB090001Constant.COL_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, (String) prCustomer.get(NLBB090001Constant.COL_CTY_ADDR));
        }
        // SHIP_ST_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStTxt, pr.getString(NLBB090001Constant.COL_SHIP_TO_ST_CD));
        } else if (prCustomer.get(NLBB090001Constant.COL_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStTxt, (String) prCustomer.get(NLBB090001Constant.COL_ST_CD));
        }
        // SHIP_POST_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPostTxt, pr.getString(NLBB090001Constant.COL_SHIP_TO_POST_CD));
        } else if (prCustomer.get(NLBB090001Constant.COL_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPostTxt, (String) prCustomer.get(NLBB090001Constant.COL_POST_CD));
        }
        // SHIP_CTRY_TXT
        if (pr.getString(NLBB090001Constant.COL_SHIP_TO_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, pr.getString(NLBB090001Constant.COL_SHIP_TO_CTRY_CD));
        } else if (prCustomer.get(NLBB090001Constant.COL_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, (String) prCustomer.get(NLBB090001Constant.COL_CTRY_CD));
        }

        // ORD_CMNT_ALL_TXT
        if (pr.getString(NLBB090001Constant.COL_DELY_ADDL_CMNT_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, pr.getString(NLBB090001Constant.COL_DELY_ADDL_CMNT_TXT));
        }
        // ORIG_ORD_QTY_TXT
        if (pr.getString(NLBB090001Constant.COL_ORIG_ORD_QTY_TXT) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, pr.getString(NLBB090001Constant.COL_ORIG_ORD_QTY_TXT));
        }

        // WMS_REC_ID
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB090001Constant.VAL_WMS_REC_ID_H);

        // SHIP_TO_CUST_CD
        for (Map<String, Object> prDetail : prDetailList) {

            String destRtlWhCd = (String) prDetail.get(NLBB090001Constant.COL_DEST_RTL_WH_CD);
            if (ZYPCommonFunc.hasValue(destRtlWhCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, destRtlWhCd);
                break;
            }
        }

        // Get Technician WH info
        RTL_WHTMsg rtlWhTMsg = null;
        if (ZYPCommonFunc.hasValue(tMsg.shipToCustCd)) {
            rtlWhTMsg = getRtlWhTMsg(this.glblCmpyCd, tMsg.shipToCustCd.getValue());
        }

        // SHIP_CTAC_NM_TXT
        if (pr.getString(NLBB090001Constant.COL_CTAC_PSN_NM) != null) {
            int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
            String ctacPsnNm = pr.getString(NLBB090001Constant.COL_CTAC_PSN_NM);
            if (ctacPsnNm.length() > digit) {
                ctacPsnNm = ctacPsnNm.substring(0, digit);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
        } else if (rtlWhTMsg != null) {
            int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
            String ctacPsnNm = rtlWhTMsg.rtlWhNm.getValue();
            if (ctacPsnNm.length() > digit) {
                ctacPsnNm = ctacPsnNm.substring(0, digit);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
        }

        // SHIP_PHO_NUM_TXT
        if (rtlWhTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, rtlWhTMsg.telNum);
        }

        // SVC_TP_TXT
        Map<String, Object> prDetailFirstData = prDetailList.get(0);
        if (PRCH_REQ_TP.PREMIUM_RUSH.equals(pr.getString(NLBB090001Constant.COL_PRCH_REQ_TP_CD))) {
            if (SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals((String) prDetailFirstData.get(NLBB090001Constant.COL_SHPG_SVC_LVL_CD))) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, NLBB090001Constant.VAL_SVC_TP_TXT_CPU);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, NLBB090001Constant.VAL_SVC_TP_TXT_LOC);
            }
        } else if (PRCH_REQ_TP.RUSH.equals(pr.getString(NLBB090001Constant.COL_PRCH_REQ_TP_CD))) {
            if (SHPG_SVC_LVL.CUSTOMER_PICK_UP.equals((String) prDetailFirstData.get(NLBB090001Constant.COL_SHPG_SVC_LVL_CD))) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, NLBB090001Constant.VAL_SVC_TP_TXT_CPU);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.svcTpTxt, NLBB090001Constant.VAL_SVC_TP_TXT_H3P);
            }
        }

        return tMsg;
    }

    private List<NLBI1410_02TMsg> createNLBI1410_02_PR(List<Map<String, Object>> prDetailList, TPL_LOCTMsg tplLocTMsg, BigDecimal trxId) {
        ArrayList<NLBI1410_02TMsg> tMsgList = new ArrayList<NLBI1410_02TMsg>();

        // Detail seqNum always starts from 2.
        int seqNum = 2;

        for (int i = 0; i < prDetailList.size(); ++i, ++seqNum) {
            NLBI1410_02TMsg tMsg = new NLBI1410_02TMsg();

            Map<String, Object> prDetail = prDetailList.get(i);

            // INTERFACE_ID
            tMsg.interfaceId.setValue(interfaceId);
            // TRANSACTION_ID
            tMsg.transactionId.setValue(trxId);
            // SEGMENT_ID
            tMsg.segmentId.setValue(1);
            // UNIT_ID
            tMsg.unitId.setValue(1);
            // SEQ_NUMBER
            tMsg.seqNumber.setValue(seqNum);

            // QTY_ORD_TXT
            BigDecimal prchReqQty = (BigDecimal) prDetail.get(NLBB090001Constant.COL_PRCH_REQ_QTY);
            if (prchReqQty != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, prchReqQty.toPlainString());
            }
            // ITEM_CD_TXT
            if (prDetail.get(NLBB090001Constant.COL_MDSE_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, (String) prDetail.get(NLBB090001Constant.COL_MDSE_CD));
            }
            // WMS_REC_ID
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB090001Constant.VAL_WMS_REC_ID_D);
            // TPL_LOC_TXT
            if (tplLocTMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, tplLocTMsg.tplLocNm);
            }
            // ORD_LINE_TXT
            if (prDetail.get(NLBB090001Constant.COL_PRCH_REQ_LINE_NUM) != null) {
                // START 2023/04/26 TZ.Win [QC#61414, MOD]
                String lineNum = prDetail.get(NLBB090001Constant.COL_PRCH_REQ_LINE_NUM) + "." + prDetail.get(NLBB090001Constant.COL_PRCH_REQ_LINE_SUB_NUM);
                ZYPEZDItemValueSetter.setValue(tMsg.ordLineTxt, lineNum);
                // END 2023/04/26 TZ.Win [QC#61414, MOD]
            }

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    /**
     * Convert value type from ResultSet to Map to reuse the method
     * "createWmsInbdSoHdr" which is used in NLBB061001 as well.
     * @param so
     * @return
     * @throws SQLException
     */
    private Map<String, Object> convertSoHeaderToMap(ResultSet so) throws SQLException {
        HashMap<String, Object> soHdr = new HashMap<String, Object>();

        soHdr.put(NLBB090001Constant.COL_GLBL_CMPY_CD, so.getString(NLBB090001Constant.COL_GLBL_CMPY_CD));
        soHdr.put(NLBB090001Constant.COL_WH_CD, so.getString(NLBB090001Constant.COL_WH_CD));
        soHdr.put(NLBB090001Constant.COL_S80_CMPY_CD, so.getString(NLBB090001Constant.COL_S80_CMPY_CD));
        soHdr.put(NLBB090001Constant.COL_SO_NUM, so.getString(NLBB090001Constant.COL_SO_NUM));
        soHdr.put(NLBB090001Constant.COL_TRX_HDR_NUM, so.getString(NLBB090001Constant.COL_TRX_HDR_NUM));
        soHdr.put(NLBB090001Constant.COL_PICK_TKT_NUM, so.getString(NLBB090001Constant.COL_PICK_TKT_NUM));
        soHdr.put(NLBB090001Constant.COL_CUST_ISS_PO_NUM, so.getString(NLBB090001Constant.COL_CUST_ISS_PO_NUM));
        soHdr.put(NLBB090001Constant.COL_SELL_TO_CUST_CD, so.getString(NLBB090001Constant.COL_SELL_TO_CUST_CD));
        soHdr.put(NLBB090001Constant.COL_BILL_TO_CUST_CD, so.getString(NLBB090001Constant.COL_BILL_TO_CUST_CD));
        soHdr.put(NLBB090001Constant.COL_SHIP_TO_CUST_CD, so.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
        soHdr.put(NLBB090001Constant.COL_SCE_ORD_TP_CD, so.getString(NLBB090001Constant.COL_SCE_ORD_TP_CD));
        soHdr.put(NLBB090001Constant.COL_SCE_ORD_TP_NM, so.getString(NLBB090001Constant.COL_SCE_ORD_TP_NM));
        soHdr.put(NLBB090001Constant.COL_S80_ORD_TP_CD, so.getString(NLBB090001Constant.COL_S80_ORD_TP_CD));
        soHdr.put(NLBB090001Constant.COL_S80_TRX_CD, so.getString(NLBB090001Constant.COL_S80_TRX_CD));
        soHdr.put(NLBB090001Constant.COL_S80_ORD_SRC_CD, so.getString(NLBB090001Constant.COL_S80_ORD_SRC_CD));
        soHdr.put(NLBB090001Constant.COL_SO_SHIP_VIA_CD, so.getString(NLBB090001Constant.COL_SO_SHIP_VIA_CD));
        soHdr.put(NLBB090001Constant.COL_SHIP_VIA_DESC_TXT, so.getString(NLBB090001Constant.COL_SHIP_VIA_DESC_TXT));
        soHdr.put(NLBB090001Constant.COL_SO_CRAT_TS, so.getString(NLBB090001Constant.COL_SO_CRAT_TS));
        soHdr.put(NLBB090001Constant.COL_PSD_DT, so.getString(NLBB090001Constant.COL_PSD_DT));
        soHdr.put(NLBB090001Constant.COL_RDD_DT, so.getString(NLBB090001Constant.COL_RDD_DT));
        soHdr.put(NLBB090001Constant.COL_DNLD_TM_TS, so.getString(NLBB090001Constant.COL_DNLD_TM_TS));
        soHdr.put(NLBB090001Constant.COL_S80_SHPG_TERM_CD, so.getString(NLBB090001Constant.COL_S80_SHPG_TERM_CD));
        soHdr.put(NLBB090001Constant.COL_S80_SHPG_TERM_NM, so.getString(NLBB090001Constant.COL_S80_SHPG_TERM_NM));
        soHdr.put(NLBB090001Constant.COL_CARR_CD, so.getString(NLBB090001Constant.COL_CARR_CD));
        soHdr.put(NLBB090001Constant.COL_DROP_SHIP_FLG, so.getString(NLBB090001Constant.COL_DROP_SHIP_FLG));
        soHdr.put(NLBB090001Constant.COL_PRINT_SCC_LB_FLG, so.getString(NLBB090001Constant.COL_PRINT_SCC_LB_FLG));
        soHdr.put(NLBB090001Constant.COL_PRINT_UCC_LB_FLG, so.getString(NLBB090001Constant.COL_PRINT_UCC_LB_FLG));
        soHdr.put(NLBB090001Constant.COL_MIX_PLT_ALLW_FLG, so.getString(NLBB090001Constant.COL_MIX_PLT_ALLW_FLG));
        soHdr.put(NLBB090001Constant.COL_PRINT_PLT_UCC_LB_FLG, so.getString(NLBB090001Constant.COL_PRINT_PLT_UCC_LB_FLG));
        soHdr.put(NLBB090001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG, so.getString(NLBB090001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG));
        soHdr.put(NLBB090001Constant.COL_ASN_REQ_FLG, so.getString(NLBB090001Constant.COL_ASN_REQ_FLG));
        soHdr.put(NLBB090001Constant.COL_IND_UCC_NUM_FLG, so.getString(NLBB090001Constant.COL_IND_UCC_NUM_FLG));
        soHdr.put(NLBB090001Constant.COL_EDI_TP_CD, so.getString(NLBB090001Constant.COL_EDI_TP_CD));
        soHdr.put(NLBB090001Constant.COL_CUST_STORE_NUM, so.getString(NLBB090001Constant.COL_CUST_STORE_NUM));
        soHdr.put(NLBB090001Constant.COL_SO_DEPT_NUM, so.getString(NLBB090001Constant.COL_SO_DEPT_NUM));
        soHdr.put(NLBB090001Constant.COL_TOT_SHIP_AMT, so.getBigDecimal(NLBB090001Constant.COL_TOT_SHIP_AMT));
        soHdr.put(NLBB090001Constant.COL_TOT_WT_AMT_NUM, so.getBigDecimal(NLBB090001Constant.COL_TOT_WT_AMT_NUM));
        soHdr.put(NLBB090001Constant.COL_RTRN_LB_CD, so.getString(NLBB090001Constant.COL_RTRN_LB_CD));
        soHdr.put(NLBB090001Constant.COL_SO_CONFIG_FLG, so.getString(NLBB090001Constant.COL_SO_CONFIG_FLG));
        soHdr.put(NLBB090001Constant.COL_CARR_ACCT_NUM, so.getString(NLBB090001Constant.COL_CARR_ACCT_NUM));
        soHdr.put(NLBB090001Constant.COL_WMS_SCHD_DELY_DT, so.getString(NLBB090001Constant.COL_WMS_SCHD_DELY_DT));
        soHdr.put(NLBB090001Constant.COL_WMS_CARR_CD, so.getString(NLBB090001Constant.COL_WMS_CARR_CD));
        soHdr.put(NLBB090001Constant.COL_SHPG_SVC_LVL_CD, so.getString(NLBB090001Constant.COL_SHPG_SVC_LVL_CD));
        soHdr.put(NLBB090001Constant.COL_RTRN_ITEM_INCL_FLG, so.getString(NLBB090001Constant.COL_RTRN_ITEM_INCL_FLG));
        soHdr.put(NLBB090001Constant.COL_SVC_CONFIG_MSTR_PK, so.getBigDecimal(NLBB090001Constant.COL_SVC_CONFIG_MSTR_PK));
        soHdr.put(NLBB090001Constant.COL_PRE_ISTL_SHOP_RQST_FLG, so.getString(NLBB090001Constant.COL_PRE_ISTL_SHOP_RQST_FLG));
        soHdr.put(NLBB090001Constant.COL_STAGE_ACT_CD, so.getString(NLBB090001Constant.COL_STAGE_ACT_CD));
        soHdr.put(NLBB090001Constant.COL_WMS_RTE_PATH_CD, so.getString(NLBB090001Constant.COL_WMS_RTE_PATH_CD));
        soHdr.put(NLBB090001Constant.COL_SCE_ORD_TP_CD, so.getString(NLBB090001Constant.COL_SCE_ORD_TP_CD));
        soHdr.put(NLBB090001Constant.COL_SRC_ORD_NUM, so.getString(NLBB090001Constant.COL_SRC_ORD_NUM));
        soHdr.put(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01, so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01));
        soHdr.put(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02, so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02));
        soHdr.put(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01, so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01));
        soHdr.put(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02, so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02));
        soHdr.put(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03, so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03));
        soHdr.put(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04, so.getString(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04));
        soHdr.put(NLBB090001Constant.COL_SHIP_CTY_ADDR, so.getString(NLBB090001Constant.COL_SHIP_CTY_ADDR));
        soHdr.put(NLBB090001Constant.COL_SHIP_ST_CD, so.getString(NLBB090001Constant.COL_SHIP_ST_CD));
        soHdr.put(NLBB090001Constant.COL_SHIP_POST_CD, so.getString(NLBB090001Constant.COL_SHIP_POST_CD));
        soHdr.put(NLBB090001Constant.COL_SHIP_CTRY_CD, so.getString(NLBB090001Constant.COL_SHIP_CTRY_CD));
        soHdr.put(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_NM, so.getString(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_NM));
        soHdr.put(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM, so.getString(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM));
        soHdr.put(NLBB090001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_01, so.getString(NLBB090001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_01));
        soHdr.put(NLBB090001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_02, so.getString(NLBB090001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_02));
        soHdr.put(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_01, so.getString(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_01));
        soHdr.put(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_02, so.getString(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_02));
        soHdr.put(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_03, so.getString(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_03));
        soHdr.put(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_04, so.getString(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_04));
        soHdr.put(NLBB090001Constant.COL_SELL_CTY_ADDR, so.getString(NLBB090001Constant.COL_SELL_CTY_ADDR));
        soHdr.put(NLBB090001Constant.COL_SELL_ST_CD, so.getString(NLBB090001Constant.COL_SELL_ST_CD));
        soHdr.put(NLBB090001Constant.COL_SELL_POST_CD, so.getString(NLBB090001Constant.COL_SELL_POST_CD));
        soHdr.put(NLBB090001Constant.COL_SELL_CTRY_CD, so.getString(NLBB090001Constant.COL_SELL_CTRY_CD));
        soHdr.put(NLBB090001Constant.COL_SELL_CTAC_PTNR_PSN_NM, so.getString(NLBB090001Constant.COL_SELL_CTAC_PTNR_PSN_NM));
        soHdr.put(NLBB090001Constant.COL_SELL_CTAC_PTNR_PSN_TEL_NUM, so.getString(NLBB090001Constant.COL_SELL_CTAC_PTNR_PSN_TEL_NUM));
        soHdr.put(NLBB090001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_01, so.getString(NLBB090001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_01));
        soHdr.put(NLBB090001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_02, so.getString(NLBB090001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_02));
        soHdr.put(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_01, so.getString(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_01));
        soHdr.put(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_02, so.getString(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_02));
        soHdr.put(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_03, so.getString(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_03));
        soHdr.put(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_04, so.getString(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_04));
        soHdr.put(NLBB090001Constant.COL_BILL_CTY_ADDR, so.getString(NLBB090001Constant.COL_BILL_CTY_ADDR));
        soHdr.put(NLBB090001Constant.COL_BILL_ST_CD, so.getString(NLBB090001Constant.COL_BILL_ST_CD));
        soHdr.put(NLBB090001Constant.COL_BILL_POST_CD, so.getString(NLBB090001Constant.COL_BILL_POST_CD));
        soHdr.put(NLBB090001Constant.COL_BILL_CTRY_CD, so.getString(NLBB090001Constant.COL_BILL_CTRY_CD));
        soHdr.put(NLBB090001Constant.COL_BILL_CTAC_PTNR_PSN_NM, so.getString(NLBB090001Constant.COL_BILL_CTAC_PTNR_PSN_NM));
        soHdr.put(NLBB090001Constant.COL_BILL_CTAC_PTNR_PSN_TEL_NUM, so.getString(NLBB090001Constant.COL_BILL_CTAC_PTNR_PSN_TEL_NUM));

        return soHdr;
    }

    private void updatePrchReqRelStsCd(String gCmpyCd, String prNum, List<Map<String, Object>> prDetailList, String prchReqRelStsCd) {
        String positionKey = NLXCMsgHelper.toListedString(NLBB090001Constant.COL_GLBL_CMPY_CD, NLBB090001Constant.COL_PRCH_REQ_NUM, NLBB090001Constant.COL_PRCH_REQ_LINE_NUM, NLBB090001Constant.COL_PRCH_REQ_LINE_SUB_NUM);

        PRCH_REQ_DTLTMsg tMsg = new PRCH_REQ_DTLTMsg();

        for (int i = 0; i < prDetailList.size(); i++) {
            Map<String, Object> prDetail = prDetailList.get(i);

            String prLineNum = (String) prDetail.get(NLBB090001Constant.COL_PRCH_REQ_LINE_NUM);
            BigDecimal prLineSubNum = (BigDecimal) prDetail.get(NLBB090001Constant.COL_PRCH_REQ_LINE_SUB_NUM);
            String position = NLXCMsgHelper.toListedString(gCmpyCd, prNum, prLineNum, prLineSubNum);

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, prNum);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineNum, prLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineSubNum, prLineSubNum);

            tMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqRelStsCd, prchReqRelStsCd);

            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_PRCH_REQ_DTL, NLBB090001Constant.TBL_PRCH_REQ_DTL, positionKey, position});

                throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_PRCH_REQ_DTL, NLBB090001Constant.TBL_PRCH_REQ_DTL, positionKey, position});
            }
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
     * @param soHdr Shipping Order Header
     * @param tplCarrSvcLvl TPL Carrier Service Level
     * @param slsDate Sales Date
     * @return WMS In-bound SO Header TMsg
     */
    public WMS_INBD_SO_HDRTMsg createWmsInbdSoHdr(Map<String, Object> soHdr, Map<String, Object> tplCarrSvcLvl, String slsDate) {
        WMS_INBD_SO_HDRTMsg tMsg = new WMS_INBD_SO_HDRTMsg();

        String gCmpyCd = (String) soHdr.get(NLBB090001Constant.COL_GLBL_CMPY_CD);
        String soNum = (String) soHdr.get(NLBB090001Constant.COL_SO_NUM);

        // WMS_SQ_NUM
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
        // TPL_CARR_CD, TPL_SVC_LVL_CD
        String tplCarrCd = null;
        String tplSvcLvlCd = null;
        if (tplCarrSvcLvl != null) {
            tplCarrCd = (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_CARR_CD);
            tplSvcLvlCd = (String) tplCarrSvcLvl.get(NLBB090001Constant.COL_TPL_SVC_LVL_CD);
        }
        // WMS_RQST_DT_TM_TS<-PSD_DT
        String psdDt = (String) soHdr.get(NLBB090001Constant.COL_PSD_DT);
        if (ZYPCommonFunc.hasValue(psdDt)) {
            psdDt = psdDt + "000000";
        }
        // WMS_PRINT_DT_TM_TS<-RDD_DT
        String rddDt = (String) soHdr.get(NLBB090001Constant.COL_RDD_DT);
        if (ZYPCommonFunc.hasValue(rddDt)) {
            rddDt = rddDt + "000000";
        }
        // OTBD_SRC_ORD_TP_TXT<-DS_ORD_CATG.DS_ORD_CATG_DESC_TXT/SCE_ORD_TP.SCE_ORD_TP_NM
        String otbdSrcOrdTpTxt = null;
        String sceOrdTpCd = (String) soHdr.get(NLBB090001Constant.COL_SCE_ORD_TP_CD);
        String trxHdrNum = (String) soHdr.get(NLBB090001Constant.COL_TRX_HDR_NUM);
        if (Arrays.asList(SCE_ORD_TP.DIRECT_SALES, SCE_ORD_TP.DC_TRANSFER).contains(sceOrdTpCd)) {
            Map<String, Object> dsOrdCatg = getDsOrdCatg(gCmpyCd, trxHdrNum);
            if (dsOrdCatg != null) {
                otbdSrcOrdTpTxt = (String) dsOrdCatg.get(NLBB090001Constant.COL_DS_ORD_CATG_DESC_TXT);
            }
        } else {
            otbdSrcOrdTpTxt = (String) soHdr.get(NLBB090001Constant.COL_SCE_ORD_TP_NM);
        }
        // PRT_TO_CUST_FLG
        String prtToCustFlg = ZYPConstant.FLG_OFF_N;
        if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {
            String shipToCustCd = (String) soHdr.get(NLBB090001Constant.COL_SHIP_TO_CUST_CD);
            SHIP_TO_CUSTTMsg shipToCust = new SHIP_TO_CUSTTMsg();
            ZYPEZDItemValueSetter.setValue(shipToCust.glblCmpyCd, gCmpyCd);
            ZYPEZDItemValueSetter.setValue(shipToCust.shipToCustCd, shipToCustCd);
            shipToCust = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKey(shipToCust);
            if (shipToCust != null && EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCust.getReturnCode()) && LOC_GRP.CUSTOMER.equals(shipToCust.locGrpCd.getValue())) {
                prtToCustFlg = ZYPConstant.FLG_ON_Y;
            }
        }
        // SLS_ORD_ADMIN_NM
        String slsOrdAdminNm = null;
        Map<String, Object> slsOrdAdmin = null;
        if (Arrays.asList(SCE_ORD_TP.DIRECT_SALES, SCE_ORD_TP.DC_TRANSFER).contains(sceOrdTpCd)) {
            // TRX_HDR_NUM=CPO_ORD_NUM
            slsOrdAdmin = getAdminPsnFromCPO(gCmpyCd, trxHdrNum);
        } else if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {
            // TRX_HDR_NUM=PO_ORD_NUM
            slsOrdAdmin = getSubmtPsnFromPO(gCmpyCd, trxHdrNum);
        } else if (Arrays.asList(SCE_ORD_TP.DISPOSAL, SCE_ORD_TP.REPAIR_SUBCONTRACT, SCE_ORD_TP.REFURBISH_EXPENSE, SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC, SCE_ORD_TP.TECH_REQUEST).contains(sceOrdTpCd)) {
            // Retrieve PRCH_REQ_NUM from SHPG_ORD_DTL
            slsOrdAdmin = getRqstPsnFromPR(gCmpyCd, soNum);
        }
        if (slsOrdAdmin != null) {
            String psnFirstNm = (String) slsOrdAdmin.get(NLBB090001Constant.COL_PSN_FIRST_NM);
            String psnLastNm = (String) slsOrdAdmin.get(NLBB090001Constant.COL_PSN_LAST_NM);
            slsOrdAdminNm = psnFirstNm + " " + psnLastNm;
        }
        // SLS_REP_PSN_NM
        String slsRepPsnNm = null;
        // TRX_HDR_NUM=CPO_ORD_NUM
        Map<String, Object> slsRepPsn = getSlsRepPsnFromCPO(gCmpyCd, trxHdrNum, slsDate);
        if (slsRepPsn != null) {
            String psnFirstNm = (String) slsRepPsn.get(NLBB090001Constant.COL_PSN_FIRST_NM);
            String psnLastNm = (String) slsRepPsn.get(NLBB090001Constant.COL_PSN_LAST_NM);
            slsRepPsnNm = psnFirstNm + " " + psnLastNm;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) soHdr.get(NLBB090001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) soHdr.get(NLBB090001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB090001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB090001Constant.VAL_INTFC_REC_TP_ID_HDR);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String) soHdr.get(NLBB090001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB090001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdNum, (String) soHdr.get(NLBB090001Constant.COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.altDocNum, (String) soHdr.get(NLBB090001Constant.COL_PICK_TKT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.custOrdNum, adjustString((String) soHdr.get(NLBB090001Constant.COL_CUST_ISS_PO_NUM), NLBB090001Constant.MAXLEN_CUST_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.chrgToCustCd, (String) soHdr.get(NLBB090001Constant.COL_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, (String) soHdr.get(NLBB090001Constant.COL_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, (String) soHdr.get(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrtyCd, NLBB090001Constant.VAL_WMS_PRTY_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdTpCd, (String) soHdr.get(NLBB090001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxCd, (String) soHdr.get(NLBB090001Constant.COL_S80_TRX_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdSrcCd, (String) soHdr.get(NLBB090001Constant.COL_S80_ORD_SRC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoStsCd, NLBB090001Constant.VAL_WMS_SO_STS_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.soShipViaCd, (String) soHdr.get(NLBB090001Constant.COL_SO_SHIP_VIA_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipViaDescTxt, (String) soHdr.get(NLBB090001Constant.COL_SHIP_VIA_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cratDtTmTs, adjustString((String) soHdr.get(NLBB090001Constant.COL_SO_CRAT_TS), NLBB090001Constant.MAXLEN_CRAT_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(tMsg.estShipDtTmTs, psdDt);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRqstDtTmTs, rddDt);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrintDtTmTs, (String) soHdr.get(NLBB090001Constant.COL_DNLD_TM_TS));
        ZYPEZDItemValueSetter.setValue(tMsg.frtOutCd, (String) soHdr.get(NLBB090001Constant.COL_S80_SHPG_TERM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frtOutDescTxt, (String) soHdr.get(NLBB090001Constant.COL_S80_SHPG_TERM_NM));
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsDeptCd, );
        // ZYPEZDItemValueSetter.setValue(tMsg.payTermCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoCarrCd, (String) soHdr.get(NLBB090001Constant.COL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.indOtmAddrSwthFlg, (String) soHdr.get(NLBB090001Constant.COL_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indScc14Flg, (String) soHdr.get(NLBB090001Constant.COL_PRINT_SCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indUccFlg, (String) soHdr.get(NLBB090001Constant.COL_PRINT_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indMixedPltFlg, (String) soHdr.get(NLBB090001Constant.COL_MIX_PLT_ALLW_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indPltLbFlg, (String) soHdr.get(NLBB090001Constant.COL_PRINT_PLT_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.sccNumFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.indNonAsnFlg, (String) soHdr.get(NLBB090001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indAsnFlg, (String) soHdr.get(NLBB090001Constant.COL_ASN_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indUccNumFlg, (String) soHdr.get(NLBB090001Constant.COL_IND_UCC_NUM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsLbNum, (String) soHdr.get(NLBB090001Constant.COL_EDI_TP_CD));
        // ZYPEZDItemValueSetter.setValue(tMsg.cancByDtTmTs, );
        ZYPEZDItemValueSetter.setValue(tMsg.custStoreNum, (String) soHdr.get(NLBB090001Constant.COL_CUST_STORE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.custDcNum, adjustString((String) soHdr.get(NLBB090001Constant.COL_CUST_STORE_NUM), NLBB090001Constant.MAXLEN_CUST_DC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustDeptNum, (String) soHdr.get(NLBB090001Constant.COL_SO_DEPT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsConslFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.totShipPrcAmtNum, adjustBigDecimal((BigDecimal) soHdr.get(NLBB090001Constant.COL_TOT_SHIP_AMT), NLBB090001Constant.PRECN_TOT_SHIP_PRC_AMT_NUM, NLBB090001Constant.SCALE_TOT_SHIP_PRC_AMT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.totWtAmtNum, adjustBigDecimal((BigDecimal) soHdr.get(NLBB090001Constant.COL_TOT_WT_AMT_NUM), NLBB090001Constant.PRECN_TOT_WT_AMT_NUM, NLBB090001Constant.SCALE_TOT_WT_AMT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnLbCd, (String) soHdr.get(NLBB090001Constant.COL_RTRN_LB_CD));
        // ZYPEZDItemValueSetter.setValue(tMsg.tpVndCd, );
        // ZYPEZDItemValueSetter.setValue(tMsg.ediTrnspTpCd, );
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsPmtTermCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.indConfigFlg, (String) soHdr.get(NLBB090001Constant.COL_SO_CONFIG_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.asgShipViaCd, tplCarrCd);
        ZYPEZDItemValueSetter.setValue(tMsg.asgPrtyCd, tplSvcLvlCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.hostOrdDtTmTs, );
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsNetAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsNetDiscAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.shpgHdlgAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.shpgHdlgDiscAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.totDiscAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.netTaxAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.shpgHdlgTaxAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.totTaxAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.totOrdAmtNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.totOrdQty, );
        ZYPEZDItemValueSetter.setValue(tMsg.indSgnReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.billAcctNum, (String) soHdr.get(NLBB090001Constant.COL_CARR_ACCT_NUM));
        // ZYPEZDItemValueSetter.setValue(tMsg.endCustOrdNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.altCustOrdNum, );
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) soHdr.get(NLBB090001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.schdDelyDt, (String) soHdr.get(NLBB090001Constant.COL_WMS_SCHD_DELY_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) soHdr.get(NLBB090001Constant.COL_WMS_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) soHdr.get(NLBB090001Constant.COL_SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, (String) soHdr.get(NLBB090001Constant.COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.usrCdIstlRefTxt, (String) soHdr.get(NLBB090001Constant.COL_SO_CONFIG_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnItemInclFlg, (String) soHdr.get(NLBB090001Constant.COL_RTRN_ITEM_INCL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, (BigDecimal) soHdr.get(NLBB090001Constant.COL_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.asmReqFlg, (String) soHdr.get(NLBB090001Constant.COL_PRE_ISTL_SHOP_RQST_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.stageActCd, (String) soHdr.get(NLBB090001Constant.COL_STAGE_ACT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stageRecStsCd, NLBB090001Constant.VAL_STAGE_REC_STS_CD);
        // ZYPEZDItemValueSetter.setValue(tMsg.addlCarrSvcCd_01, );
        // ZYPEZDItemValueSetter.setValue(tMsg.addlCarrSvcCd_02, );
        // ZYPEZDItemValueSetter.setValue(tMsg.addlCarrSvcCd_03, );
        // ZYPEZDItemValueSetter.setValue(tMsg.addlCarrSvcCd_04, );
        // ZYPEZDItemValueSetter.setValue(tMsg.addlCarrSvcCd_05, );
        ZYPEZDItemValueSetter.setValue(tMsg.carrSvcHldAtLocFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(tMsg.custRefTxt_01, );
        // ZYPEZDItemValueSetter.setValue(tMsg.custRefTxt_02, );
        // ZYPEZDItemValueSetter.setValue(tMsg.custRefTxt_03, );
        // ZYPEZDItemValueSetter.setValue(tMsg.custEmlTxt, );
        ZYPEZDItemValueSetter.setValue(tMsg.rtePathCd, (String) soHdr.get(NLBB090001Constant.COL_WMS_RTE_PATH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnTagReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.otbdSrcOrdTpTxt, adjustString(otbdSrcOrdTpTxt, NLBB090001Constant.MAXLEN_OTBD_SRC_ORD_TP_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.prtToCustFlg, prtToCustFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.otbdSrcOrdNum, (String) soHdr.get(NLBB090001Constant.COL_SRC_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.slsOrdAdminNm, adjustString(slsOrdAdminNm, NLBB090001Constant.MAXLEN_SLS_ORD_ADMIN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepPsnNm, adjustString(slsRepPsnNm, NLBB090001Constant.MAXLEN_SLS_REP_PSN_NM));
        // ZYPEZDItemValueSetter.setValue(tMsg.svcLbFmtTxt, );

        return tMsg;
    }

    /**
     * @param hdrTMsg WMS In-bound SO Header
     * @param soDtlList SO Detail List
     * @return WMS In-bound SO Detail TMsg List
     */
    public List<WMS_INBD_SO_DTLTMsg> createWmsInbdSoDtlList(WMS_INBD_SO_HDRTMsg hdrTMsg, List<Map<String, Object>> soDtlList) {
        ArrayList<WMS_INBD_SO_DTLTMsg> tMsgList = new ArrayList<WMS_INBD_SO_DTLTMsg>();

        for (Map<String, Object> soDtl : soDtlList) {
            WMS_INBD_SO_DTLTMsg tMsg = new WMS_INBD_SO_DTLTMsg();

            // SO_SLP_NUM
            BigDecimal soSlpNum = new BigDecimal((String) soDtl.get(NLBB090001Constant.COL_SO_SLP_NUM));

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsLineNum, soSlpNum);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB090001Constant.VAL_INTFC_TP_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB090001Constant.VAL_INTFC_REC_TP_ID_DTL);
            // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soDtl.get(NLBB090001Constant.COL_SO_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) soDtl.get(NLBB090001Constant.COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.s80StkStsCd, (String) soDtl.get(NLBB090001Constant.COL_S80_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd, (String) soDtl.get(NLBB090001Constant.COL_CUST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdQty, (BigDecimal) soDtl.get(NLBB090001Constant.COL_RQST_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.backOrdQtyNum, (BigDecimal) soDtl.get(NLBB090001Constant.COL_SHPG_BAL_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipQty, (BigDecimal) soDtl.get(NLBB090001Constant.COL_SHPG_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.unitPrcAmtNum, adjustBigDecimal((BigDecimal) soDtl.get(NLBB090001Constant.COL_UNIT_PRC_AMT), NLBB090001Constant.PRECN_UNIT_PRC_AMT_NUM, NLBB090001Constant.SCALE_UNIT_PRC_AMT_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.unitDiscAmtNum, adjustBigDecimal((BigDecimal) soDtl.get(NLBB090001Constant.COL_DISC_PRC_AMT), NLBB090001Constant.PRECN_UNIT_DISC_AMT_NUM, NLBB090001Constant.SCALE_UNIT_DISC_AMT_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.unitDiscPrcAmtNum, adjustBigDecimal((BigDecimal) soDtl.get(NLBB090001Constant.COL_DISC_UNIT_PRC_AMT), NLBB090001Constant.PRECN_UNIT_DISC_PRC_AMT_NUM, NLBB090001Constant.SCALE_UNIT_DISC_PRC_AMT_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTotAmtNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.indSerId, (String) soDtl.get(NLBB090001Constant.COL_SER_NUM_TAKE_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.indVoidAllwFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.s80StkStsCdToCd, (String) soDtl.get(NLBB090001Constant.COL_S80_STK_STS_TO_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCdSetCd, (String) soDtl.get(NLBB090001Constant.COL_SET_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCdSetDescTxt, (String) soDtl.get(NLBB090001Constant.COL_SET_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipSetQty, (BigDecimal) soDtl.get(NLBB090001Constant.COL_SET_SHPG_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.unitInsdQty, (BigDecimal) soDtl.get(NLBB090001Constant.COL_IN_EACH_QTY));
            // ZYPEZDItemValueSetter.setValue(tMsg.essPoSqNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.essMdseLineNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.essLineNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.essMsgLineNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.totWtAmtNum, (BigDecimal) soDtl.get(NLBB090001Constant.COL_TOT_SHPG_WT));
            ZYPEZDItemValueSetter.setValue(tMsg.totVolAmtNum, (BigDecimal) soDtl.get(NLBB090001Constant.COL_TOT_SHPG_VOL));
            // ZYPEZDItemValueSetter.setValue(tMsg.estCseAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.estPltAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.soMdseTpCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsPackTpCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.batCptrReqFlg, (String) soDtl.get(NLBB090001Constant.COL_BAT_NUM_TAKE_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.indConfigFlg, (String) soDtl.get(NLBB090001Constant.COL_CONFIG_ITEM_FLG));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsNetAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsDiscAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsTaxAmtNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) soDtl.get(NLBB090001Constant.COL_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String) soDtl.get(NLBB090001Constant.COL_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, (BigDecimal) soDtl.get(NLBB090001Constant.COL_PICK_SVC_CONFIG_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(tMsg.backOrdImpctTpCd, (String) soDtl.get(NLBB090001Constant.COL_BACK_ORD_IMPCT_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdUomCd, WMS_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(tMsg.poLineTxt, (String) soDtl.get(NLBB090001Constant.COL_TRX_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.usrCdIstlRefTxt, (String) soDtl.get(NLBB090001Constant.COL_CONFIG_ITEM_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.backOrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rmvConfigFlg, (String) soDtl.get(NLBB090001Constant.COL_RMV_CONFIG_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.origOrdQty, (BigDecimal) soDtl.get(NLBB090001Constant.COL_ORIG_ORD_QTY));

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    /**
     * @param hdrTMsg WMS In-bound SO Header
     * @param soTextList SO Text
     * @return WMS In-bound SO Text TMsg
     */
    public WMS_INBD_SO_TEXTTMsg createWmsInbdSoText(WMS_INBD_SO_HDRTMsg hdrTMsg, List<Map<String, Object>> soTextList) {
        WMS_INBD_SO_TEXTTMsg tMsg = new WMS_INBD_SO_TEXTTMsg();

        Map<String, Object> soText = soTextList.get(0);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTxtCd, (String) soText.get(NLBB090001Constant.COL_SO_MSG_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB090001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB090001Constant.VAL_INTFC_REC_TP_ID_TEXT);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soText.get(NLBB090001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrintTpCd, NLBB090001Constant.VAL_WMS_PRINT_TP_CD);

        EZDTStringItem[] fieldItems = new EZDTStringItem[] {tMsg.inbdSoMsgTxt_01, tMsg.inbdSoMsgTxt_02, tMsg.inbdSoMsgTxt_03, tMsg.inbdSoMsgTxt_04 };
        for (int n = 0; n < soTextList.size() && n < fieldItems.length; ++n) {
            String text = (String) soTextList.get(n).get(NLBB090001Constant.COL_SO_MSG_DESC_TXT);
            if (ZYPCommonFunc.hasValue(text)) {
                ZYPEZDItemValueSetter.setValue(fieldItems[n], text);
            }
        }

        return tMsg;
    }

    /**
     * @param hdrTMsg WMS In-bound SO Header
     * @param soHdr SO Header
     * @return WMS In-bound SO Ship-to TMsg
     */
    public WMS_INBD_SO_SHIP_TOTMsg createWmsInbdSoShipTo(WMS_INBD_SO_HDRTMsg hdrTMsg, Map<String, Object> soHdr) {
        WMS_INBD_SO_SHIP_TOTMsg tMsg = new WMS_INBD_SO_SHIP_TOTMsg();

        String shipToCustCd = (String) soHdr.get(NLBB090001Constant.COL_SHIP_TO_CUST_CD);
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB090001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB090001Constant.VAL_INTFC_REC_TP_ID_SHIPTO);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB090001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsShipToNm_01, adjustString((String) soHdr.get(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01), NLBB090001Constant.MAXLEN_WMS_SHIP_TO_NM_01));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsShipToNm_02, adjustString((String) soHdr.get(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02), NLBB090001Constant.MAXLEN_WMS_SHIP_TO_NM_02));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) soHdr.get(NLBB090001Constant.COL_SHIP_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) soHdr.get(NLBB090001Constant.COL_SHIP_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) soHdr.get(NLBB090001Constant.COL_SHIP_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) soHdr.get(NLBB090001Constant.COL_SHIP_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsShipToCtacNm, (String) soHdr.get(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCtacNum, adjustString((String) soHdr.get(NLBB090001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM), NLBB090001Constant.MAXLEN_SHIP_TO_CTAC_NUM));

        return tMsg;
    }

    /**
     * @param hdrTMsg WMS In-bound SO Header
     * @param soHdr SO Header
     * @return WMS In-bound SO Charge-to TMsg
     */
    public WMS_INBD_SO_CHRG_TOTMsg createWmsInbdSoChrgTo(WMS_INBD_SO_HDRTMsg hdrTMsg, Map<String, Object> soHdr) {
        WMS_INBD_SO_CHRG_TOTMsg tMsg = new WMS_INBD_SO_CHRG_TOTMsg();

        String sellToCustCd = (String) soHdr.get(NLBB090001Constant.COL_SELL_TO_CUST_CD);
        if (!ZYPCommonFunc.hasValue(sellToCustCd)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB090001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB090001Constant.VAL_INTFC_REC_TP_ID_CHRGTO);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB090001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustCd, sellToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsChrgToNm_01, adjustString((String) soHdr.get(NLBB090001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_01), NLBB090001Constant.MAXLEN_WMS_CHRG_TO_NM_01));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsChrgToNm_02, adjustString((String) soHdr.get(NLBB090001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_02), NLBB090001Constant.MAXLEN_WMS_CHRG_TO_NM_02));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) soHdr.get(NLBB090001Constant.COL_SELL_SO_CUST_LINE_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) soHdr.get(NLBB090001Constant.COL_SELL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) soHdr.get(NLBB090001Constant.COL_SELL_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) soHdr.get(NLBB090001Constant.COL_SELL_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) soHdr.get(NLBB090001Constant.COL_SELL_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsChrgToCtacNm, (String) soHdr.get(NLBB090001Constant.COL_SELL_CTAC_PTNR_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.chrgToCtacNum, adjustString((String) soHdr.get(NLBB090001Constant.COL_SELL_CTAC_PTNR_PSN_TEL_NUM), NLBB090001Constant.MAXLEN_CHRG_TO_CTAC_NUM));

        return tMsg;
    }

    /**
     * @param hdrTMsg WMS In-bound SO Header
     * @param soHdr SO Header
     * @return WMS In-bound SO Bill-to TMsg
     */
    public WMS_INBD_SO_BILL_TOTMsg createWmsInbdSoBillTo(WMS_INBD_SO_HDRTMsg hdrTMsg, Map<String, Object> soHdr) {
        WMS_INBD_SO_BILL_TOTMsg tMsg = new WMS_INBD_SO_BILL_TOTMsg();

        String billToCustCd = (String) soHdr.get(NLBB090001Constant.COL_BILL_TO_CUST_CD);
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB090001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB090001Constant.VAL_INTFC_REC_TP_ID_BILLTO);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB090001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBillToNm_01, adjustString((String) soHdr.get(NLBB090001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_01), NLBB090001Constant.MAXLEN_WMS_BILL_TO_NM_01));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBillToNm_02, adjustString((String) soHdr.get(NLBB090001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_02), NLBB090001Constant.MAXLEN_WMS_BILL_TO_NM_02));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) soHdr.get(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) soHdr.get(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) soHdr.get(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) soHdr.get(NLBB090001Constant.COL_BILL_SO_CUST_LINE_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) soHdr.get(NLBB090001Constant.COL_BILL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) soHdr.get(NLBB090001Constant.COL_BILL_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) soHdr.get(NLBB090001Constant.COL_BILL_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) soHdr.get(NLBB090001Constant.COL_BILL_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBillToCtacNm, (String) soHdr.get(NLBB090001Constant.COL_BILL_CTAC_PTNR_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCtacNum, adjustString((String) soHdr.get(NLBB090001Constant.COL_BILL_CTAC_PTNR_PSN_TEL_NUM), NLBB090001Constant.MAXLEN_BILL_TO_CTAC_NUM));

        return tMsg;
    }

    /**
     * getTplCarrSvcLvl
     * @param whOwnrCd WH Owner Code
     * @param carrCd Carrier Code
     * @param shpgSvcLvlCd Shipping Service Level Code
     * @return TPL Carrier Service Level
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getTplCarrSvcLvl(String gCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_WH_OWNR_CD, whOwnrCd);
        queryParam.put(NLBB090001Constant.COL_CARR_CD, carrCd);
        queryParam.put(NLBB090001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam);
    }

    /**
     * getTplCarrSvcLvlWithEffort
     * @param gCmpyCd Global Company Code
     * @param whOwnrCd WH Owner Code
     * @param carrCd Carrier Code
     * @param shpgSvcLvlCd Shipping Service Level Code
     * @return TPL Carrier Service Level
     */
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

    @SuppressWarnings("unchecked")
    private Map<String, Object> getDsOrdCatg(String gCmpyCd, String cpoOrdNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_CPO_ORD_NUM, cpoOrdNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getDsOrdCatg", queryParam, execParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getAdminPsnFromCPO(String gCmpyCd, String cpoOrdNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_CPO_ORD_NUM, cpoOrdNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getAdminPsnFromCPO", queryParam, execParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSubmtPsnFromPO(String gCmpyCd, String poOrdNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_PO_ORD_NUM, poOrdNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSubmtPsnFromPO", queryParam, execParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getRqstPsnFromPR(String gCmpyCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_SO_NUM, soNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRqstPsnFromPR", queryParam, execParam);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSlsRepPsnFromCPO(String gCmpyCd, String cpoOrdNum, String slsDate) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_CPO_ORD_NUM, cpoOrdNum);
        queryParam.put(NLBB090001Constant.COL_MIN_DATE, NLBB090001Constant.VAL_MIN_DATE);
        queryParam.put(NLBB090001Constant.COL_MAX_DATE, NLBB090001Constant.VAL_MAX_DATE);
        queryParam.put(NLBB090001Constant.BIND_SALES_DATE, slsDate);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSlsRepPsnFromCPO", queryParam, execParam);
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

        final BigDecimal maxValue = BigDecimal.ONE.movePointRight(precision).subtract(BigDecimal.ONE).movePointLeft(scale);
        final BigDecimal minValue = maxValue.negate();
        if (maxValue.compareTo(val) < 0) {
            return maxValue;
        }
        if (minValue.compareTo(val) > 0) {
            return minValue;
        }

        return val.setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    private boolean updateShpgOrdDtl(List<WMS_INBD_SO_DTLTMsg> dtlList) {
        for (WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg : dtlList) {
            updateShpgOrdDtl(wmsInbdSoDtlTMsg);
        }

        return true;
    }

    private boolean updateShpgOrdDtl(WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg) {
        SHPG_ORD_DTLTMsg soDtlT = new SHPG_ORD_DTLTMsg();
        String soSlpNum = ZYPCommonFunc.leftPad(String.valueOf(wmsInbdSoDtlTMsg.wmsLineNum.getValue()), NLBB090001Constant.PADNUM_SO_SLP_NUM, "0");
        ZYPEZDItemValueSetter.setValue(soDtlT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soDtlT.soNum, wmsInbdSoDtlTMsg.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(soDtlT.soSlpNum, soSlpNum);
        soDtlT = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(soDtlT);

        if (soDtlT != null && ZYPCommonFunc.hasValue(soDtlT.shpgStsCd) && soDtlT.shpgStsCd.getValue().equals(SHPG_STS.S_OR_O_PRINTED)) {
            soDtlT.dsSoLineStsCd.setValue(DS_SO_LINE_STS.AWAITING_PICK_CONFIRMATION);
            EZDTBLAccessor.update(soDtlT);
        }

        return true;
    }

    // START 2023/04/06 TZ.Win [QC#61267, DEL]
//    private boolean isShipToUS(String shipCtryCd, String stCd) {
//
//        if (ZYPCommonFunc.hasValue(shipCtryCd) && ZYPCommonFunc.hasValue(stCd)) {
//            if (CTRY.UNITED_STATES_OF_AMERICA.equals(shipCtryCd)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void updateWmsDropRqstFlg(String gCmpyCd, String soNum, String wmsDropRqstFlg) {
//        String positionKey = NLXCMsgHelper.toListedString(NLBB090001Constant.COL_GLBL_CMPY_CD, NLBB090001Constant.COL_SO_NUM);
//        String position = NLXCMsgHelper.toListedString(gCmpyCd, soNum);
//
//        SHPG_ORDTMsg tMsg = new SHPG_ORDTMsg();
//
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.soNum, soNum);
//
//        tMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
//        ZYPEZDItemValueSetter.setValue(tMsg.wmsDropRqstFlg, wmsDropRqstFlg);
//
//        EZDTBLAccessor.update(tMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            outputErr(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_SHPG_ORD, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position });
//
//            throw new S21AbendException(NLBB090001Constant.NLGM0045E, new String[] {NLBB090001Constant.TBL_SHPG_ORD, NLBB090001Constant.TBL_SHPG_ORD, positionKey, position });
//        }
//    }
    // END 2023/04/06 TZ.Win [QC#61267, DEL]

    private void createMnxOrderRequests() {

        // Get transaction ID
        BigDecimal[] transactionIdList = trxAccess.getIntegrationRecord(interfaceId);

        for (BigDecimal transactionId : transactionIdList) {
            // START 2022/08/10 A.Cullano [QC#60324, ADD]
            errItemList.clear();
            // END 2022/08/10 A.Cullano [QC#60324, ADD]
            createMnxOrderForTransactionId(transactionId);
            trxAccess.endIntegrationProcess(interfaceId, transactionId);
        }

        commit();
    }

    private void createMnxOrderForTransactionId(BigDecimal transactionId) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLBB090001Constant.FETCH_SIZE);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB090001Constant.COL_WH_OWNR_CD, trgtWhOwnrCd);
        queryParam.put(NLBB090001Constant.COL_INTERFACE_ID, interfaceId);
        queryParam.put(NLBB090001Constant.COL_TRANSACTION_ID, transactionId);

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
            if (NLBB090001Constant.VAL_MNX_RESPONSE_TYPE_ERROR.equals(stType.getResponseType())) {

                // QC#58137 Add Start
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
                // QC#58137 Add emd
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(NLBB090001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } catch (S21AbendException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            // QC#58137 Add Start
            if (isAbend) {
                throw new S21AbendException(e.getMessage());
            } else {
                // START 2021/01/26 A.Marte [QC#58281, MOD]
                outputMnxErrKeyIt("NLBM1257E", e.getMessage(), transactionId, interfaceId, errItemList);
                // END 2021/01/26 A.Marte [QC#58281, MOD]
            }
            // QC#58137 Add end
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    private NglcreateOrderRequest createMnxRequest(ResultSet rs) throws SQLException {

        // nglcreateOrderRequest
        NglcreateOrderRequest request = new NglcreateOrderRequest();

        // Order Information
        NglOrdersType orderHead = new NglOrdersType();

        // Order Detail Information
        NglOrderType order = new NglOrderType();

        // Parts Information
        PartsLoop partsloop = new PartsLoop();
        List<PartInfoType> partList = partsloop.getPartInfo();

        if (rs.next()) {
            // To convert time to location time zone
            // QC#58151
//            String pickupTime = ZYPDateUtil.getCurrentSystemTime(NLBB090001Constant.FMT_MMDDYYYYHHMM);
            String toPostCd = rs.getString(NLBB090001Constant.COL_SHIP_POST_TXT);
            String toCtryCd = rs.getString(NLBB090001Constant.COL_SHIP_CTRY_TXT);
            String rqstDtTmTxTxt = rs.getString(NLBB090001Constant.COL_REQ_DT_TM_TS_TXT);

            // Order Header
            order.setOrderPerson(rs.getString(NLBB090001Constant.COL_SHIP_CTAC_NM_TXT));
            order.setOrderPersonPhoneNum(rs.getString(NLBB090001Constant.COL_SHIP_PHO_NUM_TXT));
            // QC#58151
            order.setPickupReadyDateTime("");
            if (ZYPCommonFunc.hasValue(rqstDtTmTxTxt)) {
                order.setDeliveryRequestedDateTime(rqstDtTmTxTxt);
            }
            order.setRefNum(rs.getString(NLBB090001Constant.COL_ORD_ID_TXT));
            order.setServiceId(rs.getString(NLBB090001Constant.COL_SVC_TP_TXT));

            // From Location Information
            PULocationInfoType fromLoc = new PULocationInfoType();
            fromLoc.setFslId(rs.getString(NLBB090001Constant.COL_TPL_LOC_TXT));
            String shipFromNmAllTxt = rs.getString(NLBB090001Constant.COL_SHIP_FROM_NM_ALL_TXT);
            if (ZYPCommonFunc.hasValue(shipFromNmAllTxt) && shipFromNmAllTxt.length() > NLBB090001Constant.MAXLEN_COMPANY_NAME) {
                shipFromNmAllTxt = shipFromNmAllTxt.substring(0, NLBB090001Constant.MAXLEN_COMPANY_NAME);
            }
            fromLoc.setCompanyName(shipFromNmAllTxt);
            fromLoc.setAddr1(rs.getString(NLBB090001Constant.COL_SHIP_FROM_ADDR_ALL_TXT));
            fromLoc.setCity(rs.getString(NLBB090001Constant.COL_SHIP_FROM_CTY_TXT));
            fromLoc.setState(rs.getString(NLBB090001Constant.COL_SHIP_FROM_ST_TXT));
            fromLoc.setZipCode(rs.getString(NLBB090001Constant.COL_SHIP_FROM_POST_TXT));
            String ctryCd = rs.getString(NLBB090001Constant.COL_SHIP_FROM_CTRY_TXT);
            if (ZYPCommonFunc.hasValue(ctryCd)) {
                if (CTRY.UNITED_STATES_OF_AMERICA.equals(ctryCd)) {
                    fromLoc.setCountry(CountryCodeType.USA);
                } else {
                    fromLoc.setCountry(CountryCodeType.fromValue(ctryCd));
                }
            }
            fromLoc.setRes(ZYPConstant.FLG_OFF_N);
            fromLoc.setContactName(rs.getString(NLBB090001Constant.COL_SHIP_FROM_CTAC_NM));
            fromLoc.setContactPhone(rs.getString(NLBB090001Constant.COL_SHIP_FROM_PHO_NUM));
            fromLoc.setInstructions(rs.getString(NLBB090001Constant.COL_SHIP_FROM_CMNT_ALL_TXT));

            // To Location Information
            LocationInfoType toLoc = new LocationInfoType();
            if (ZYPCommonFunc.hasValue(rs.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD))) {
                RTL_WHTMsg toRtlWhTMsg = getRtlWhTMsg(this.glblCmpyCd, rs.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
                if (toRtlWhTMsg != null && WH_OWNR.MNX.equals(toRtlWhTMsg.whOwnrCd.getValue())) {
                    toLoc.setFslId(rs.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
                }
            }
            String shipToNmAllTxt = rs.getString(NLBB090001Constant.COL_SHIP_NM_ALL_TXT);
            if (ZYPCommonFunc.hasValue(shipToNmAllTxt) && shipToNmAllTxt.length() > NLBB090001Constant.MAXLEN_COMPANY_NAME) {
                shipToNmAllTxt = shipToNmAllTxt.substring(0, NLBB090001Constant.MAXLEN_COMPANY_NAME);
            }
            toLoc.setCompanyName(shipToNmAllTxt);
            toLoc.setAddr1(rs.getString(NLBB090001Constant.COL_SHIP_ADDR_ALL_TXT));
            toLoc.setCity(rs.getString(NLBB090001Constant.COL_SHIP_CTY_TXT));
            toLoc.setState(rs.getString(NLBB090001Constant.COL_SHIP_ST_TXT));
            toLoc.setZipCode(toPostCd);
            if (ZYPCommonFunc.hasValue(toCtryCd) && CTRY.UNITED_STATES_OF_AMERICA.equals(toCtryCd)) {
                toLoc.setCountry(CountryCodeType.USA);
            } else {
                toLoc.setCountry(CountryCodeType.fromValue(toCtryCd));
            }
            toLoc.setRes(ZYPConstant.FLG_OFF_N);
            toLoc.setContactName(rs.getString(NLBB090001Constant.COL_SHIP_CTAC_NM_TXT));
            toLoc.setContactPhone(rs.getString(NLBB090001Constant.COL_SHIP_PHO_NUM_TXT));
            toLoc.setInstructions(rs.getString(NLBB090001Constant.COL_ORD_CMNT_ALL_TXT));

            // Carrier Information
            CarrierInfoType carrType = new CarrierInfoType();
            String tplCarrNm = rs.getString(NLBB090001Constant.COL_TPL_CARR_NM);
            if (ZYPCommonFunc.hasValue(tplCarrNm)) {
                carrType.setShipper(ShipperType.fromValue(tplCarrNm));
            }
            carrType.setShipMethod(rs.getString(NLBB090001Constant.COL_TPL_SVC_LVL_NM));
            carrType.setSpecialShipMethod(rs.getString(NLBB090001Constant.COL_TPL_SPCL_SVC_NM));

            // Reference
            RefLoop refLoop = new RefLoop();
            List<RefInfoType> refList = refLoop.getRefInfo();
            RefInfoType refInfoType = new RefInfoType();
            refInfoType.setRef(rs.getString(NLBB090001Constant.COL_SHIP_TO_CUST_CD));
            refList.add(refInfoType);

            PartInfoType partInfo = new PartInfoType();
            // START 2023/04/26 TZ.Win [QC#61414, MOD]
            double ordLineTxt = Double.parseDouble(rs.getString(NLBB090001Constant.COL_ORD_LINE_TXT));
            partInfo.setID((int)ordLineTxt);
            // END 2023/04/26 TZ.Win [QC#61414, MOD]
            partInfo.setFslId(rs.getString(NLBB090001Constant.COL_TPL_LOC_TXT));
            partInfo.setQty(Integer.parseInt(rs.getString(NLBB090001Constant.COL_QTY_ORD_TXT)));
            partInfo.setField1(rs.getString(NLBB090001Constant.COL_ITEM_CD_TXT));
            partInfo.setField5(rs.getString(NLBB090001Constant.COL_ORD_LINE_TXT));
            partList.add(partInfo);

            order.setFromInfo(fromLoc);
            order.setToInfo(toLoc);
            order.setCarrierInfo(carrType);
            order.setRefLoop(refLoop);

            // START 2021/01/26 A.Marte [QC#58281, ADD]
            // Save Segment and Order for Error Info
            Map<String, String> orderParam = new HashMap<String, String>();
            orderParam.put(NLBB090001Constant.SEGMENT_ID, rs.getString(NLBB090001Constant.SEGMENT_ID));
            orderParam.put(NLBB090001Constant.COL_ORD_ID_TXT, rs.getString(NLBB090001Constant.COL_ORD_ID_TXT));
            errItemList.add(orderParam);
            // END 2021/01/26 A.Marte [QC#58281, ADD]
        }

        while (rs.next()) {
            PartInfoType partInfo = new PartInfoType();
            // START 2023/04/26 TZ.Win [QC#61414, MOD]
            double ordLineTxt = Double.parseDouble(rs.getString(NLBB090001Constant.COL_ORD_LINE_TXT));
            partInfo.setID((int)ordLineTxt);
            // END 2023/04/26 TZ.Win [QC#61414, MOD]
            partInfo.setFslId(rs.getString(NLBB090001Constant.COL_TPL_LOC_TXT));
            partInfo.setQty(Integer.parseInt(rs.getString(NLBB090001Constant.COL_QTY_ORD_TXT)));
            partInfo.setField1(rs.getString(NLBB090001Constant.COL_ITEM_CD_TXT));
            partInfo.setField5(rs.getString(NLBB090001Constant.COL_ORD_LINE_TXT));
            partList.add(partInfo);
        }

        // Set request parameters.
        order.setPartsLoop(partsloop);
        orderHead.setNglOrder(order);
        request.setNglOrders(orderHead);

        return request;
    }

    private NglcreateOrderResponse sendMnxRequest(NglcreateOrderRequest request) throws Throwable {

        NglcreateOrderResponse response = null;

        try {
            String invokeMode = ZYPCodeDataUtil.getVarCharConstValue(NLBB090001Constant.VAR_CHAR_CONST_NM_MNX_INVOKE_MODE, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(invokeMode) && NLBB090001Constant.VAL_MNX_INVOKE_MODE_STUB_SUCCESS.equals(invokeMode)) {
                response = stub_stiShippingOrder_Success(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && NLBB090001Constant.VAL_MNX_INVOKE_MODE_STUB_ERROR.equals(invokeMode)) {
                response = stub_stiShippingOrder_ApplicationError(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && NLBB090001Constant.VAL_MNX_INVOKE_MODE_STUB_TIMEOUT.equals(invokeMode)) {
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

    private static NglcreateOrderResponse stub_stiShippingOrder_Success(NglcreateOrderRequest request) {

        StatusType stType = new StatusType();
        stType.setResponseType(NLBB090001Constant.VAL_MNX_RESPONSE_TYPE_SUCCESS);
        stType.setCode("");
        stType.setMessage("");

        NglcreateOrderResponse response = new NglcreateOrderResponse();
        response.setStatus(stType);
        response.setOrderNumber(NLBB090001Constant.STUB_SO_ORDER_NUM);
        response.setPickupNumber(NLBB090001Constant.STUB_SO_PICKUP_NUM);
        response.setVoucherNumber(NLBB090001Constant.STUB_SO_VOUCHER_NUM);
        response.setRefNum(request.getNglOrders().getNglOrder().getRefNum());

        return response;
    }

    private static NglcreateOrderResponse stub_stiShippingOrder_ApplicationError(NglcreateOrderRequest request) {

        StatusType stType = new StatusType();
        stType.setResponseType(NLBB090001Constant.VAL_MNX_RESPONSE_TYPE_ERROR);
        stType.setCode("NE0001");
        stType.setMessage("Value of 'UserName' is invalid.");

        NglcreateOrderResponse response = new NglcreateOrderResponse();
        response.setStatus(stType);
        response.setRefNum(request.getNglOrders().getNglOrder().getRefNum());

        return response;
    }

    private RTL_WHTMsg getRtlWhTMsg(String gCmpyCd, String rtlWhCd) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, gCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);

        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        return tMsg;
    }

    // QC#57659-1 Add
    private String getTimezoneTime(String time, String postCd, String ctryCd, String oldFormat, String newFormat) {

        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, time, ctryCd, postCd);
        String time2 = timeInfo.getDateTime().substring(0, 12);
        time2 = ZYPDateUtil.DateFormatter(time2, oldFormat, newFormat);

        return time2;
    }

    private Map<String, Object> getShipToCust(String gCmpyCd, String shipToCustCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB090001Constant.COL_SHIP_TO_CUST_CD, shipToCustCd);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getShipToCust", queryParam, execParam);
    }
    // QC#57659-1 end

    // QC#58137 Add start
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
        queryParam.put(NLBB090001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put("DS_COND_CONST_GRP_ID", "MNX_API_ERR_CD");
        queryParam.put("DS_COND_CONST_CD", errCd);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMnxErrCond", queryParam, execParam);
    }
    // QC#58137 Add end

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

                String errSegmentId = orderItem.get(NLBB090001Constant.COL_SEGMENT_ID);
                String errItemId = orderItem.get(NLBB090001Constant.COL_ORD_ID_TXT);

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
        builder.append(NLBB090001Constant.LINE_SEPT);
        builder.append(NLBB090001Constant.VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append(ifId);
        builder.append(NLBB090001Constant.LINE_SEPT);
        builder.append(NLBB090001Constant.VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        if (ZYPCommonFunc.hasValue(transactionId)) {
            builder.append(transactionId.toPlainString());
        } else {
            builder.append(NLBB090001Constant.VAL_BLANK);
        }
        builder.append(NLBB090001Constant.LINE_SEPT);
        builder.append(NLBB090001Constant.VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue(errSegmentId)) {
            builder.append(errSegmentId);
        } else {
            builder.append(NLBB090001Constant.VAL_BLANK);
        }
        builder.append(NLBB090001Constant.LINE_SEPT);
        builder.append(NLBB090001Constant.VAL_BLANK_14);
        builder.append("Order #           : ");
        if (ZYPCommonFunc.hasValue(errItemId)) {
            builder.append(errItemId);
        } else {
            builder.append(NLBB090001Constant.VAL_BLANK);
        }
        builder.append(NLBB090001Constant.LINE_SEPT);
        builder.append(NLBB090001Constant.VAL_SEP_LINE);

        return builder.toString();
    }
    // END 2021/01/26 A.Marte [QC#58281, ADD]
}
