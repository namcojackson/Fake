/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB120001;
import static com.canon.cusa.s21.batch.NLB.NLBB120001.constant.NLBB120001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CPO_DTLTMsg;
import business.db.NLBI1510_01TMsg;
import business.db.NLBI1510_02TMsg;
import business.db.NLBI1510_03TMsg;
import business.db.NLBI1510_04TMsg;
import business.db.NLBI1510_05TMsg;
import business.db.PRCH_REQTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.WMS_INBD_PO_DTLTMsg;
import business.db.WMS_INBD_PO_HDRTMsg;
import business.db.WMS_INBD_PO_VNDTMsg;
import business.db.WMS_INBD_SO_BILL_TOTMsg;
import business.db.WMS_INBD_SO_CHRG_TOTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_SO_SHIP_TOTMsg;
import business.db.WMS_INBD_SO_TEXTTMsg;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
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
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Shipping Order to AGW
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/24/2023   Hitachi         M.Nakajima      Create          N/A
 * 07/20/2023   Hitachi         M.Kikushima     Update          QC#61591
 * 12/01/2023   Hitachi         G.Quan          Update          QC#62451
 *</pre>
 */
public class NLBB120001 extends S21BatchMain {

    // -- Error Message Parameter --------------------
    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Global Company Code */
    private static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : Commit Count */
    private static final String MSG_STR_PARAM_01 = "Commit Count(VAR_USER1)";

    // -- Other Internal constants --------------

    /** Fetch Size */
    private static final int FETCH_SIZE = 1000;

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    /**Sales Date */
    private String slsDt;

    /** Interface ID */
    private String interfaceId;

    /** Commit Count */
    private int commitCount = 0;

    // -- Count of processing -------------------
    /** The number of success */
    private int successCount;

    /** The number of error */
    private int errorCount;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Exec Param */
    private S21SsmExecutionParameter execParam = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Termination code */
    private TERM_CD termCd;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess;

    /** WH_OWNR_CD */
    private String whOwnrCd = null;

    /** CARR_TP_NM */
    private String carrTpCd = null;

    /** SCE_ORD_TP List */
    private List<String> sceOrdTpList;

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {
        //initialization of S21BatchMain
        new NLBB120001().executeBatch(NLBB120001.class.getSimpleName());
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

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Set the fetch size.
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Check on input parameter
        checkParameter();

        // WH_OWNER_CD
        whOwnrCd = getWhOwnrCd();

        // CARR_TP_CD
        carrTpCd = getCarrTpNm();

        // SCE_ORD_TP_LIST
        sceOrdTpList = getSceOrdTpList();
    }

    @Override
    protected void mainRoutine() {

        // Create Interface data and Transaction table.
        try {
            createInterface();
        } finally {
            if (errMsgList.size() > 0) {
                sendErrorMail();
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {

        String[] msg = new String[] {interfaceId, "Insert", Integer.toString(successCount)};

        // The number of cases : Insert is output
        S21InfoLogOutput.println(ZZBM0009I, msg);

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, successCount + errorCount);
    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_COMP_CODE });
        }

        //Sales Date
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_INTERFACE_ID });
        }

        // Commit Count
        String str = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_PARAM_01 });
        }
        try {
            commitCount = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new S21AbendException(ZZM9004E, new String[] {"Commit Count(" + str + ")" });
        }
    }

    /**
     * getWhOwnrCd
     * @return
     */
    private String getWhOwnrCd() {
        return ZYPCodeDataUtil.getVarCharConstValue(NLBB1200_TARGET_ORDER, glblCmpyCd);
    }

    /**
     * getCarrTpNm
     * @return
     */
    private String getCarrTpNm() {
        return ZYPCodeDataUtil.getVarCharConstValue(NLBB1200_TARGET_CARRIER, glblCmpyCd);
    }

    /**
     * getSceOrdTpList
     * @return
     */
    private List<String> getSceOrdTpList() {
        String sceOrdTp = ZYPCodeDataUtil.getVarCharConstValue(AGW_TARGET_SCE_ORD_TP_CD, glblCmpyCd);
        List<String> list = new ArrayList<String>();

        if (sceOrdTp != null) {
            String[] varSceOrdTpList = sceOrdTp.split(",");
            list = Arrays.asList(varSceOrdTpList);
        }
        return list;
    }

    /**
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void createInterface() {

        // Create SO Interface data.
        createSoInterface();
        // Create RWS Interface data.
        createRwsInterface();

        commit();
    }

    /**
     * <pre>
     * createSoInterface
     * </pre>
     * @param execParam SSM parameter.
     */
    private void createSoInterface() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Get target SO_NUMs
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
            bindParam.put(COL_WH_OWNR_CD, whOwnrCd);
            bindParam.put(COL_CARR_TP_NM, carrTpCd);

            stmt = ssmLLClient.createPreparedStatement("findSo", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop by SO#
            int processedCount = 0;
            while (rs.next()) {
                BigDecimal trxId = trxAccess.getNextTransactionId();
                int unitId = 1;
                int seqNumber = 0;
                boolean errFlg = false;

                String soNum = rs.getString(COL_SO_NUM);
                boolean fromWhAgwFlg = getSoFromWhAgw(soNum);
                boolean toWhAgwAndCarrAgwFlg = getSoToWhAGWandCarrAGW(soNum);

                // SO Header
                Map<String, Object> soHeader = getSoHeader(soNum);
                Map<String, String> ctacPsnInfo = new HashMap<String, String>();
                Map<String, String> customerInfo = new HashMap<String, String>();
                seqNumber = createSoHeader(soHeader
                                           , trxId
                                           , new BigDecimal(unitId)
                                           , seqNumber
                                           , ctacPsnInfo
                                           , customerInfo);

                // set SO Header to WMS_INBD_SO_HDR
                WMS_INBD_SO_HDRTMsg inbdSoHdrT = null;
                if (fromWhAgwFlg || toWhAgwAndCarrAgwFlg) {
                    inbdSoHdrT = getWmsInbdSoHdr(soHeader, toWhAgwAndCarrAgwFlg);
                    if (inbdSoHdrT == null) {
                        errorCount++;
                        rollback();
                        continue;
                    }
                }

                // SO Text
                List<Map<String, Object>> soTextList = getSoText(soNum);
                seqNumber = createSoText(soTextList, trxId, new BigDecimal(unitId), seqNumber);

                // SO Site Survey
                Map<String, Object> soSiteSurvey = getSoSiteSurvey(soNum);
                if (ctacPsnInfo != null) {
                    soSiteSurvey.put("CONTACT", ctacPsnInfo.get("CONTACT"));
                    soSiteSurvey.put("PHONE", ctacPsnInfo.get("PHONE"));
                }

                if (customerInfo != null) {
                    soSiteSurvey.put("reqDtTmTsTxt", customerInfo.get("reqDtTmTsTxt"));
                    soSiteSurvey.put("postCd", customerInfo.get("postCd"));
                    soSiteSurvey.put("ctryCd", customerInfo.get("ctryCd"));
                }

                // SO Detail
                List<Map<String, Object>> soDetailList = getSoDetail(soNum);

                // SO MDSE Survey
                List<Map<String, Object>> soMdseSurveyList = getSoMdseSurvey(soNum);

                for (Map<String, Object> soDetail : soDetailList) {

                    String soSlpNum = (String) soDetail.get(COL_SO_SLP_NUM);

                    ++unitId;
                    seqNumber = 0;

                    // Create SO Detail
                    seqNumber = createSoDetail(soDetail, trxId, new BigDecimal(unitId), seqNumber);

                    // Create WMS_INBD_SO_DTL
                    if ((fromWhAgwFlg || toWhAgwAndCarrAgwFlg) && !createWmsInbdSoDtl(inbdSoHdrT, soDetail, toWhAgwAndCarrAgwFlg)) {
                        errFlg = true;
                        break;
                    }

                    // SO Serial
                    List<Map<String, Object>> soSerialList = getSoSerial(soNum, soSlpNum, (BigDecimal) soDetail.get(COL_SHPG_QTY));

                    seqNumber = createSoSerial(soSerialList, trxId, new BigDecimal(unitId), seqNumber);

                    if (soSiteSurvey != null) {
                        seqNumber = createSoSiteSurvey(soSiteSurvey, trxId, new BigDecimal(unitId), seqNumber);
                        soSiteSurvey = null;
                    }

                    if (soMdseSurveyList != null) {
                        seqNumber = createSoMdseSurvey(soMdseSurveyList, trxId, new BigDecimal(unitId), seqNumber);
                        soMdseSurveyList = null;
                    }
                }

                if (errFlg) {
                    ++errorCount;
                    rollback();
                    continue;
                }

                if (fromWhAgwFlg || toWhAgwAndCarrAgwFlg) {
                    // create WMS_INBD_SO_HDR
                    createWmsInbdSoHdr(inbdSoHdrT);

                    // create WMS_INBD_SO_TEXT
                    createWmsInbdSoText(inbdSoHdrT, soTextList);

                    // create WMS_INBD_SO_SHIP_TO
                    createShipTo(inbdSoHdrT);

                    // create WMS_INBD_SO_CHRG_TO
                    createChrgTo(inbdSoHdrT);

                    // create WMS_INBD_SO_BILL_TO
                    createBillTo(inbdSoHdrT);
                }

                // Update of WMS send status
                updateSoWmsDropFlg(soNum, fromWhAgwFlg);
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);

                ++successCount;
                ++processedCount;

                // commit
                if (processedCount >= commitCount) {
                    commit();
                    processedCount = 0;
                }
            }
            // Loop by SO#

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * get SO where WH_OWNR_CD = AGW
     */
    private boolean getSoFromWhAgw(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put(COL_WH_OWNR_CD, whOwnrCd);

        if (null == ssmBatchClient.queryObject("getSoFromWhAgw", bindParam, execParam)) {
            return false;
        }
        return true;
    }

    /**
     * get SO where WH_OWNR_CD = AGW and CARR_TP_NM = AGW
     */
    private boolean getSoToWhAGWandCarrAGW(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put(COL_WH_OWNR_CD, whOwnrCd);
        bindParam.put(COL_CARR_TP_NM, carrTpCd);

        if (null == ssmBatchClient.queryObject("getSoToWhAGWandCarrAGW", bindParam, execParam)) {
            return false;
        }
        return true;
    }

    // Get SO data (Header)
    private Map<String, Object> getSoHeader(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put(COL_SO_CUST_DATA_TP_CD, SO_CUST_DATA_TP.SHIP_TO);
        bindParam.put("shipToCustCd", VAL_999999);
        bindParam.put("outbound", INBD_OTBD.OUTBOUND);
        bindParam.put("lgSoCratTs", LENGTH_TS);
        bindParam.put("change", VAL_CHANGE);
        bindParam.put("newOrder", VAL_NEW_ORDER);
        bindParam.put("val1", VAL_1);
        bindParam.put("val2", VAL_2);
        bindParam.put("valTs", VAL_000000);
        bindParam.put("maxTotShipAmt", MAX_VAL_TOT_SHIP_PRC_AMT_NUM);
        bindParam.put("lgCustIssPoNum", MAX_LENGTH_CUST_ISS_PO_NUM);
        bindParam.put("lgOtbdSrcOrdTpTxt", MAX_LENGTH_OTBD_SRC_ORD_TP_TXT);
        bindParam.put("lgPsnNm", VAL_PSN_NM_SIZE);
        bindParam.put("slsDt", slsDt);
        bindParam.put("minDt", VAL_MIN_DATE);
        bindParam.put("maxDt", VAL_MAX_DATE);

        Map<String, Object> soHeader = (Map<String, Object>) ssmBatchClient.queryObject("findSoHeader", bindParam, execParam);

        // get TPL_CARR_SVC_LVL
        String carrCd = VAL_CONST_CARR_CD;
        String tplCarrSvcLvlCd = null;
        String shpgSvcLvlCd = (String) soHeader.get(COL_SHPG_SVC_LVL_CD);
        Map<String, Object> tplCarrSvcLvl = getTplCarrSvcLvl(carrCd, shpgSvcLvlCd);
        if (tplCarrSvcLvl != null && ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(COL_TPL_CARR_CD))) {
            carrCd = (String) tplCarrSvcLvl.get(COL_CARR_CD);
            tplCarrSvcLvlCd = (String) tplCarrSvcLvl.get(COL_TPL_SVC_LVL_CD);
        }

        soHeader.put(COL_TPL_CARR_CD, carrCd);
        soHeader.put(COL_TPL_SVC_LVL_CD, tplCarrSvcLvlCd);

        return soHeader;
    }

    /**
     * getTplCarrSvcLvl
     * @param carrCd String
     * @param shpgSvcLvlCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getTplCarrSvcLvl(String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> tplCarrSvcLvl = null;

        tplCarrSvcLvl = getTplCarrSvcLvlBySo(carrCd, shpgSvcLvlCd);
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvlBySo(null, shpgSvcLvlCd);
        }
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvlBySo(null, null);
        }

        return tplCarrSvcLvl;
    }

    /**
     * getTplCarrSvcLvlBySo
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvlBySo(String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(COL_CARR_CD, carrCd);
        queryParam.put(COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvlBySo", queryParam, execParam);
    }

    private int createSoHeader(Map<String, Object> soHeader
                                , BigDecimal trxId
                                , BigDecimal unitId
                                , int seqNumber
                                , Map<String, String> ctacPsnInfo
                                , Map<String, String> customerInfo) {

        // Set to TMsg(NLBI1510_01)
        NLBI1510_01TMsg tMsgSoHeader = new NLBI1510_01TMsg();

        ++seqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.seqNumber, new BigDecimal(seqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsRecId, VAL_05);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplFromPtnrId, VAL_CONST_TPL_FROM_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplToPtnrId, VAL_CONST_TPL_TO_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordDtTmTsTxt, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSSSSS));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordIdTxt, (String) soHeader.get(COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplRefNm, trxId.toPlainString());
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplLocTxt, getShpgPntCd((String) soHeader.get(COL_RTL_WH_CD)));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplCondTxt, VAL_01);
        // START 2023/07/12 [QC#61591, MOD]
        if(SCE_ORD_TP.DISPOSAL.equals((String)soHeader.get(COL_SCE_ORD_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordTpTxt, VAL_ORD_TP_TXT_DP);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordTpTxt, VAL_ORD_TP_TXT_LF);
        }
        //ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordTpTxt, VAL_ORD_TP_TXT_LF);
        // END 2022/07/12 [QC#61591, MOD]

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplOrgTxt, VAL_CONST_TPL_ORG_TXT);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplCtrlId, (String) soHeader.get(COL_TPL_CTRL_ID));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.carrCd, (String) soHeader.get(COL_TPL_CARR_CD));

        String reqDtTmTsTxt = (String) soHeader.get(COL_REQ_DT_TM_TS_TXT);
        if (ZYPCommonFunc.hasValue(reqDtTmTsTxt)) {

            //convert customer time
            if (reqDtTmTsTxt.length() == FMT_YYYYMMDDHHMM.length()) {
                reqDtTmTsTxt = ZYPDateUtil.DateFormatter(reqDtTmTsTxt, FMT_YYYYMMDDHHMM, FMT_YYYYMMDDHHMMSSSSS);
            } else {
                reqDtTmTsTxt = ZYPDateUtil.DateFormatter(reqDtTmTsTxt, FMT_YYYYMMDD, FMT_YYYYMMDDHHMMSSSSS);
            }
            if (ZYPCommonFunc.hasValue((String) soHeader.get(COL_SCHD_DELY_TM))) {
                reqDtTmTsTxt = convCustomerTime(reqDtTmTsTxt
                                                , (String) soHeader.get(COL_CTRY_CD)
                                                , (String) soHeader.get(COL_POST_CD));
            }
        }

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.reqDtTmTsTxt, reqDtTmTsTxt);

        String psdDt = (String) soHeader.get(COL_PSD_DT);
        if (ZYPCommonFunc.hasValue(psdDt)) {
            psdDt = ZYPDateUtil.DateFormatter(psdDt, FMT_YYYYMMDD, FMT_YYYYMMDDHHMMSSSSS);
        }
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.rqstShipDtTmTsTxt, psdDt);

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplPtnrTxt, VAL_CONST_TPL_PTNR_TXT);
        String shipToCustCd = (String) soHeader.get(COL_SHIP_TO_CUST_CD);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCustTxt, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipFirstLineNm, (String) soHeader.get(COL_SO_CUST_LINE_LOC_NM_01));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipScdLineNm, (String) soHeader.get(COL_SO_CUST_LINE_LOC_NM_02));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipFirstLineAddrTxt, (String) soHeader.get(COL_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipScdLineAddrTxt, (String) soHeader.get(COL_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipToPostCd, (String) soHeader.get(COL_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtyTxt, (String) soHeader.get(COL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtryTxt, (String) soHeader.get(COL_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipToStCd, (String) soHeader.get(COL_ST_CD));

        if (ZYPCommonFunc.hasValue(reqDtTmTsTxt)) {
            customerInfo.put("reqDtTmTsTxt", reqDtTmTsTxt.substring(0, LENGTH_TS_8));
        } else {
            customerInfo.put("reqDtTmTsTxt", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgSoHeader.shipToPostCd)) {
            customerInfo.put("postCd", tMsgSoHeader.shipToPostCd.getValue());
        } else {
            customerInfo.put("postCd", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgSoHeader.shipCtryTxt)) {
            customerInfo.put("ctryCd", tMsgSoHeader.shipCtryTxt.getValue());
        } else {
            customerInfo.put("ctryCd", "");
        }

        String telNum = (String) soHeader.get(COL_CTAC_PTNR_PSN_TEL_NUM);
        String ctacPsnNm = (String) soHeader.get(COL_CTAC_PTNR_PSN_NM);
        Map<String, Object> dsCpoCtacPsn = new HashMap<String, Object>();
        if (!ZYPCommonFunc.hasValue(telNum) || !ZYPCommonFunc.hasValue(ctacPsnNm)) {
            dsCpoCtacPsn = getShipPhoNumTxtFromDsCpoCtacPsn((String) soHeader.get(COL_SO_NUM));
        }
        if (!ZYPCommonFunc.hasValue(telNum)) {
            if (dsCpoCtacPsn != null) {
                telNum = (String) dsCpoCtacPsn.get(COL_CTAC_PSN_TEL_NUM);
            }
        }
        if (!ZYPCommonFunc.hasValue(telNum) && ZYPCommonFunc.hasValue(shipToCustCd)) {
            SHIP_TO_CUSTTMsg tMsg = getShipToCust(shipToCustCd);
            if (tMsg != null) {
                telNum = tMsg.telNum.getValue();
            }
        }
        if (!ZYPCommonFunc.hasValue(telNum)) {
            Map<String, Object> rtrn = getReturn((String) soHeader.get(COL_RTL_WH_CD)
                                                 , (String) soHeader.get(COL_RTRN_LB_CD));
            if (rtrn != null) {
                telNum = (String) rtrn.get(COL_RTRN_TO_CTAC_NUM);
            }
        }
        if (!ZYPCommonFunc.hasValue(telNum)) {
            String rtlWhCd = (String) soHeader.get(COL_RTL_WH_CD);
            if (ZYPCommonFunc.hasValue(rtlWhCd)) {
                RTL_WHTMsg tMsg = getRtlWh(rtlWhCd);
                if (tMsg != null) {
                    telNum = tMsg.telNum.getValue();
                }
            }
        }

        int telLen = tMsgSoHeader.getAttr("shipCtacPhoNum").getDigit();
        if (ZYPCommonFunc.hasValue(telNum) && telNum.length() > telLen) {
            telNum = telNum.substring(0, telLen);
        }

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtacPhoNum, telNum);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipPhoNumTxt, telNum);

        if (!ZYPCommonFunc.hasValue(ctacPsnNm)) {
            if (dsCpoCtacPsn != null) {
                ctacPsnNm = (String) dsCpoCtacPsn.get(COL_CTAC_PSN_NM);
            }
        }

        if (ZYPCommonFunc.hasValue(ctacPsnNm) && ctacPsnNm.length() > MAX_LENGTH_CTAC_PSN_NM) {
            ctacPsnNm = ctacPsnNm.substring(0, MAX_LENGTH_CTAC_PSN_NM);
        }

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtacNmTxt, ctacPsnNm);

        ctacPsnInfo.put("CONTACT", ctacPsnNm);
        ctacPsnInfo.put("PHONE", telNum);

        String soNum = (String) soHeader.get(COL_SO_NUM);
        SHPG_ORDTMsg shpgOrdT = getShpgOrd(soNum);
        if (shpgOrdT != null && SCE_ORD_TP.TECH_REQUEST.equals(shpgOrdT.sceOrdTpCd.getValue())) {
            PRCH_REQTMsg prchReqT = getPrchReq(shpgOrdT.srcOrdNum.getValue());

            if (prchReqT != null && ZYPCommonFunc.hasValue(prchReqT.shipToCustCd)) {
                // [Drop Ship] Update Ship Address.
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipFirstLineNm, prchReqT.shipToLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipScdLineNm, prchReqT.shipToAddlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipFirstLineAddrTxt, prchReqT.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipScdLineAddrTxt, prchReqT.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipToPostCd, prchReqT.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtyTxt, prchReqT.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtryTxt, prchReqT.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipToStCd, prchReqT.shipToStCd);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtacNmTxt, prchReqT.ctacPsnNm);
                tMsgSoHeader.shipCtacPhoNum.clear();
                tMsgSoHeader.shipPhoNumTxt.clear();
            }
        }

        // insert
        EZDTBLAccessor.insert(tMsgSoHeader);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoHeader.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID);
            String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId);

            outputErr(NLGM0045E, new String[] {NLBI1510_01, SHPG_ORD, keyFieldName, keyValue});

            throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_01, SHPG_ORD, keyFieldName, keyValue});
        }
        return seqNumber;
    }

    /**
     * getShpgPntCd
     * @param rtlWhCd String
     * @return String
     */
    private String getShpgPntCd(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_RTL_WH_CD, rtlWhCd);
        String result = (String) this.ssmBatchClient.queryObject("getShpgPntCd", queryParam, execParam);
        return result;
    }

    /**
     * getShipPhoNumTxtFromDsCpoCtacPsn
     * @param soNum
     * @return
     */
    private Map<String, Object> getShipPhoNumTxtFromDsCpoCtacPsn(String soNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_SO_NUM, soNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getShipPhoNumTxtFromDsCpoCtacPsn", queryParam, execParam);
    }

    /**
     * @param shipToCustCd
     * @return SHIP_TO_CUST
     */
    private SHIP_TO_CUSTTMsg getShipToCust(String shipToCustCd) {
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return null;
        }

        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        inMsg.setMaxCount(0);
        inMsg.setSQLID("004");

        SHIP_TO_CUSTTMsgArray outMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (outMsgArray.length() >= 1) {
            return (SHIP_TO_CUSTTMsg) outMsgArray.get(0);
        } else {
            return null;
        }
    }

    private Map<String, Object> getReturn(String rtlWhCd, String rtrnLbCd) {
        // SQL bind parameter
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put("glblCmpyCd", glblCmpyCd);
        bindParam.put("whCdAll", "*");
        bindParam.put("rtrnLblCd", rtrnLbCd);
        bindParam.put("rtlWhCd", rtlWhCd);

        return (Map<String, Object>) ssmBatchClient.queryObject("getReturn", bindParam, execParam);
    }

    /**
     * getRtlWh
     * @param rtlWhCd
     * @return
     */
    private RTL_WHTMsg getRtlWh(String rtlWhCd) {

        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getShpgOrd
     * @param soNum
     * @return SHPG_ORDTMsg
     */
    private SHPG_ORDTMsg getShpgOrd(String soNum) {

        SHPG_ORDTMsg inMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.soNum, soNum);
        SHPG_ORDTMsg outMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getPrchReq
     * @param prchReqNum
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getPrchReq(String prchReqNum) {

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
        PRCH_REQTMsg outMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    // Get SO data (Detail)
    private List<Map<String, Object>> getSoDetail(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put("val2", VAL_2);
        bindParam.put("maxQty", VAL_MAX_QTY);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoDetail", bindParam, execParam);
    }

    private int createSoDetail(Map<String, Object> soDetail
                                , BigDecimal trxId
                                , BigDecimal unitId
                                , int seqNumber) {

        NLBI1510_02TMsg tMsgSoDetail = new NLBI1510_02TMsg();

        ++seqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.seqNumber, new BigDecimal(seqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.wmsRecId, VAL_06);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.itemCdTxt, (String) soDetail.get(COL_MDSE_CD));
        BigDecimal shpgQty = (BigDecimal) soDetail.get(COL_SHPG_QTY);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.qtyOrdTxt, shpgQty.toPlainString());
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.ordLineTxt, (String) soDetail.get(COL_SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.tplItemTpCd, VAL_CONST_TPL_ITEM_TP_CD);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.custPoTpTxt, (String) soDetail.get(COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.custPoLineTxt, (String) soDetail.get(COL_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.tplLocTxt, (String) soDetail.get(COL_PLANT_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.tplSwhCd, (String) soDetail.get(COL_TPL_LOC_NM));

        if (soDetail.get(COL_PICK_SVC_CONFIG_MSTR_PK) != null) {
            ZYPEZDItemValueSetter.setValue(tMsgSoDetail.svcConfigMstrPk, (BigDecimal) soDetail.get(COL_PICK_SVC_CONFIG_MSTR_PK));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgSoDetail.svcConfigMstrPk, (BigDecimal) soDetail.get(COL_SVC_CONFIG_MSTR_PK));
        }

        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.itrlItemFlg, (String) soDetail.get(COL_ITRL_ITEM_FLG));

        // insert
        EZDTBLAccessor.insert(tMsgSoDetail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoDetail.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
            String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

            outputErr(NLGM0045E, new String[] {NLBI1510_02, SHPG_ORD_DTL, keyFieldName, keyValue});

            throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_02, SHPG_ORD_DTL, keyFieldName, keyValue});
        }

        return 1;
    }

    // Get SO data(Text)
    private List<Map<String, Object>> getSoText(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put(COL_SO_MSG_TP_CD, VAL_CONST_SO_MSG_TP_CD);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoText", bindParam, execParam);
    }

    private int createSoText(List<Map<String, Object>> soTextList
                              , BigDecimal trxId
                              , BigDecimal unitId
                              , int seqNumber) {
        int len = 0;
        for (Map<String, Object> soText : soTextList) {
            NLBI1510_03TMsg tMsgSoText = new NLBI1510_03TMsg();

            String text = (String) soText.get(COL_SO_MSG_DESC_TXT);
            if (!ZYPCommonFunc.hasValue(text)) {
                continue;
            }
            // START 2023/12/01 G.Quan [QC#62451, ADD]
            text = text.replaceAll("\r\n", " ");
            // END 2023/12/01 G.Quan [QC#62451, ADD]
            len += text.getBytes().length;
            if (len > MAX_LENGTH_TXT) {
                break;
            }

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoText.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsRecId, VAL_07);

            ZYPEZDItemValueSetter.setValue(tMsgSoText.soMsgDescTxt, text);

            // insert
            EZDTBLAccessor.insert(tMsgSoText);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoText.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_03, SHPG_ORD_MSG, keyFieldName, keyValue});

                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_03, SHPG_ORD_MSG,
                        NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID)
                        , NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId) });
            }
        }

        return seqNumber;
    }

    // Get SO data(Site Survey)
    private Map<String, Object> getSoSiteSurvey(String soNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put(COL_SO_CUST_DATA_TP_CD, SO_CUST_DATA_TP.SHIP_TO);

        return (Map<String, Object>) ssmBatchClient.queryObject("findSoSiteSurvey", bindParam, execParam);
    }

    private int createSoSiteSurvey(Map<String, Object> soSiteSurvey
                                    , BigDecimal trxId
                                    , BigDecimal unitId
                                    , int seqNumber) {

        final String[][] nameAndKeys = new String[][] {
            // COMPANY INFORMATION
            new String[] {"SALES ORDER NUMBER", "CPO_ORD_NUM" },
            new String[] {"COMPANY NAME", "SO_CUST_LINE_LOC_NM_01" },
            new String[] {"CUSTOMER NUMBER", "SHIP_TO_CUST_CD" },
            new String[] {"STREET", "SO_CUST_LINE_ADDR_01" },
            new String[] {"CITY", "CTY_ADDR" },
            new String[] {"STATE", "ST_CD" },
            new String[] {"APT. OR BUILDING", "CMPY_INFO_APT_BLDG_NM" },
            new String[] {"CONTACT", "CONTACT" },
            new String[] {"DEPARTMENT", "CMPY_INFO_DEPT_NM" },
            new String[] {"PHONE", "PHONE" },
            new String[] {"FLOOR", "CMPY_INFO_FL_NM" },
            new String[] {"POSTAL", "POST_CD" },
            new String[] {"FLOOR PROTECTION REQ?", "ELEV_PROT_REQ_FLG" },
            // ADDITIONAL COMMENTS
            new String[] {"ADDITIONAL COMMENTS", "SITE_SRVY_ADDL_CMNT_TXT" },
            // SITE INFORMATION
            new String[] {"NO OF STEPS OUTSIDE", "OTSD_STEP_NUM" },
            new String[] {"NO OF STEPS INSIDE", "INSD_STEP_NUM" },
            new String[] {"STEP CRAWLER REQUIRED?", "STAIR_CRAW_REQ_FLG" },
            new String[] {"NO OF FLIGHTS", "FLGT_STAIR_NUM" },
            new String[] {"WIDTH OF STAIRS AND LANDINGS(IN,)", "STAIR_AND_LDG_WDT" },
            new String[] {"LOADING DOCK AVAILABLE", "LOAD_DOCK_AVAL_FLG" },
            new String[] {"DOCK HEIGHT(IN,)", "LOAD_DOCK_HGT" },
            new String[] {"DELIVERY HOURS FROM", "LOAD_DOCK_AVAL_FROM_HOUR_MN" },
            new String[] {"DELIVERY HOURS TO", "LOAD_DOCK_AVAL_TO_HOUR_MN" },
            new String[] {"TRACTOR TRAILER ACCESSIBLE", "TRCTR_AND_TRAIL_ACCS_FLG" },
            new String[] {"TIMESTOP", "CARR_DELY_TM_HOUR_MN" },
            new String[] {"TRANSPORT OPTION", "DELY_TRNSP_OPT_CD" },
            // ADD SITE INFORMATION
            new String[] {"SECURITY CLEARANCE REQUIRED", "SEC_CLNC_REQ_TXT" },
            new String[] {"INSURANCE CERTIFICATE REQUIRED", "INS_CERT_REQ_TXT" },
            new String[] {"FLOOR COVERING", "FL_COV_TXT" },
            new String[] {"RAMP?", "RAMP_AVAL_TXT" },
            new String[] {"STEPS?", "STEP_AVAL_TXT" },
            new String[] {"DOOR FRONT", "FRONT_DOOR_AVAL_TXT" },
            new String[] {"DOOR BACK", "BACK_DOOR_AVAL_TXT" },
            new String[] {"POWER OUTLET CONFIGURED", "PWR_OTLT_CONFIG_TXT" },
            new String[] {"SIGN ON BUILDING ROADSIDE", "SGN_ON_BLDG_RDSD_TXT" },
            new String[] {"BUILDING # OF STOREYS", "BLDG_STRY_NUM" },
            new String[] {"BUILDING GUARD NOTIFIED", "BLDG_GURD_NTFY_TXT" },
            new String[] {"INDUSTRIAL PARK", "INDL_PARK_TXT" },
            new String[] {"BUSINESS PARK", "BIZ_PARK_TXT" },
            new String[] {"PROFESSIONAL BUILDING", "PRO_BLDG_TXT" },
            new String[] {"SHOPPING CENTER", "SHPNG_CTR_TXT" },
            new String[] {"PRIVATE RESIDENCE", "PVT_RES_TXT" },
            // ELEVATOR INDORMATION & DIMENSIONS
            new String[] {"ELEVATOR AVAILABLE", "ELEV_AVAL_FLG" },
            new String[] {"ELEVATOR HOURS FROM", "ELEV_AVAL_FROM_HOUR_MN" },
            new String[] {"ELEVATOR HOURS TO", "ELEV_AVAL_TO_HOUR_MN" },
            new String[] {"ELEVATOR APPOINTMENT NEEDED?", "ELEV_APPT_REQ_FLG" },
            new String[] {"ELEVATOR CONTACT", "ELEV_CTAC_PSN_NM" },
            new String[] {"ELEVATOR PHONE", "ELEV_CTAC_TEL_NUM" },
            new String[] {"BUILDING ENTRANCE HEIGHT(IN,)", "BLDG_ENT_DOOR_HGT" },
            new String[] {"BUILDING ENTRANCE WIDTH(IN.)", "BLDG_ENT_DOOR_WDT" },
            new String[] {"BUILDING ENTRANCE CORRIDOR WIDTH(IN.)", "CRDR_WDT" },
            new String[] {"BUILDING ENTRANCE DOOR WIDTH(IN.)", "DOOR_WDT" },
            new String[] {"ELEVATOR WIDTH(IN.)", "ELEV_WDT" },
            new String[] {"ELEVATOR DEPTH(IN.)", "ELEV_DEPTH_NUM" },
            new String[] {"ELEVATOR CAPACITY(LBS.)", "ELEV_CAP_WT" },
            new String[] {"DOOR OPENING HEIGHT(IN.)", "ELEV_DOOR_HGT" },
            new String[] {"DOOR OPENING WIDTH (IN.)", "ELEV_DOOR_WDT" },
            // QC#23044 VALIDATION
            new String[] {"CUSTOMER INFORMED ABOUT B/O", "CUST_INFO_BO_FLG" },
            new String[] {"PICKUP EXTRA TONER", "PICK_UP_XTR_TONER_FLG" },
            new String[] {"PICKUP AT SAME TIME", "PICK_UP_AT_SAME_TM_FLG" },
        };

        String rqstDt = (String) soSiteSurvey.get("reqDtTmTsTxt");
        String ctryCd = (String) soSiteSurvey.get("ctryCd");
        String postCd = (String) soSiteSurvey.get("postCd");

        for (String[] nameAndKey : nameAndKeys) {
            NLBI1510_04TMsg tMsgSoSiteSurvey = new NLBI1510_04TMsg();
            String name = nameAndKey[0];
            String key = nameAndKey[1];
            Object val = soSiteSurvey.get(key);
            String value = null;

            if (val == null) {
                value = "";
            } else if (val instanceof BigDecimal) {
                value = ((BigDecimal) val).toPlainString();
            } else {
                value = val.toString();
                // START 2023/12/01 G.Quan [QC#62451, ADD]
                if (ZYPCommonFunc.hasValue(value)) {
                    value = value.replaceAll("\r\n", " ");
                }
                // END 2023/12/01 G.Quan [QC#62451, ADD]
            }

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.wmsRecId, VAL_08);

            if (ZYPCommonFunc.hasValue(value)) {

                if ("DELIVERY HOURS FROM".equals(name)
                 || "DELIVERY HOURS TO".equals(name)
                 || "TIMESTOP".equals(name)
                 || "ELEVATOR HOURS FROM".equals(name)
                 || "ELEVATOR HOURS TO".equals(name)) {

                    value = convertAMPM(convertTime(rqstDt, value, ctryCd, postCd));

                    ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                } else {

                    ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name);
            }

            // insert
            EZDTBLAccessor.insert(tMsgSoSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoSiteSurvey.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_04, tMsgSoSiteSurvey.getTableName(), keyFieldName, keyValue });

                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_04, tMsgSoSiteSurvey.getTableName(), keyFieldName, keyValue });
            }
        }

        return seqNumber;
    }

    // Get SO data(Mdse Survey)
    private List<Map<String, Object>> getSoMdseSurvey(String soNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoMdseSurvey", bindParam, execParam);
    }

    private int createSoMdseSurvey(List<Map<String, Object>> soMdseSurveyList
                                    , BigDecimal trxId
                                    , BigDecimal unitId
                                    , int seqNumber) {

        for (Map<String, Object> soMdseSurvey : soMdseSurveyList) {
            seqNumber = createSoMdseSurvey(soMdseSurvey, trxId, unitId, seqNumber);
        }
        return seqNumber;
    }

    private int createSoMdseSurvey(Map<String, Object> soMdseSurvey
                                    , BigDecimal trxId
                                    , BigDecimal unitId
                                    , int seqNumber) {

        final String[][] nameAndKeys = new String[][] {
            // MATELIAL WEIGHTS AND DIMENSIONS INFORMATION
            new String[] {"ITEM CODE", "MDSE_CD" },
            new String[] {"ITEM DESCRIPTION", "MDSE_NM" },
            new String[] {"CT WT", "IN_POUND_WT" },
            new String[] {"CR LEN", "IN_INCH_LG" },
            new String[] {"CR WDT", "IN_INCH_WDT" },
            new String[] {"CR HT", "IN_INCH_HGT" },
            new String[] {"CR DG", "IN_INCH_DG" },
        };

        for (String[] nameAndKey : nameAndKeys) {
            NLBI1510_04TMsg tMsgSoSiteSurvey = new NLBI1510_04TMsg();
            String name = nameAndKey[0];
            String key = nameAndKey[1];
            Object val = soMdseSurvey.get(key);
            String value = null;

            if (val == null) {
                continue;
            } else if (val instanceof BigDecimal) {
                value = ((BigDecimal) val).toPlainString();
            } else {
                value = val.toString();
            }

            if (!ZYPCommonFunc.hasValue(value)) {
                continue;
            }

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.wmsRecId, VAL_08);

            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);

            // insert
            EZDTBLAccessor.insert(tMsgSoSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoSiteSurvey.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_04, MDSE, keyFieldName, keyValue });

                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_04, MDSE, keyFieldName, keyValue });
            }
        }
        return seqNumber;
    }

    // Get SO data (Serial No)
    private List<Map<String, Object>> getSoSerial(String soNum, String soSlpNum, BigDecimal shpgQty) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_SO_NUM, soNum);
        bindParam.put(COL_SO_SLP_NUM, soSlpNum);
        bindParam.put("rowNum", shpgQty);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoSerial", bindParam, execParam);
    }

    private int createSoSerial(List<Map<String, Object>> soSerialList
                                , BigDecimal trxId
                                , BigDecimal unitId
                                , int seqNumber) {

        for (Map<String, Object> soSerial : soSerialList) {
            NLBI1510_05TMsg tMsgSoSerial = new NLBI1510_05TMsg();

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.wmsRecId, VAL_09);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.tplSerNum, (String) soSerial.get(COL_SER_NUM));

            // insert
            EZDTBLAccessor.insert(tMsgSoSerial);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoSerial.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_05, SO_SER, keyFieldName, keyValue });

                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_05, SO_SER, keyFieldName, keyValue });
            }
        }

        return seqNumber;
    }

    /**
     * get WMS_INBD_SO_HDR.
     * @param soHeader Map<String, Object>
     * @param toWhAgwAndCarrAgwFlg boolean
     * @return WMS_INBD_SO_HDRTMsg
     */
    private WMS_INBD_SO_HDRTMsg getWmsInbdSoHdr(Map<String, Object> soHeader, boolean toWhAgwAndCarrAgwFlg) {

        WMS_INBD_SO_HDRTMsg hdrT = new WMS_INBD_SO_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(hdrT.glblCmpyCd, glblCmpyCd);
        if (toWhAgwAndCarrAgwFlg) {
            ZYPEZDItemValueSetter.setValue(hdrT.whCd, (String) soHeader.get(COL_SHIP_TO_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(hdrT.rtlWhCd, (String) soHeader.get(COL_SHIP_TO_RTL_WH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(hdrT.whCd, (String) soHeader.get(COL_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(hdrT.rtlWhCd, (String) soHeader.get(COL_RTL_WH_CD));
        }
        ZYPEZDItemValueSetter.setValue(hdrT.wmsSqNum, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ));
        ZYPEZDItemValueSetter.setValue(hdrT.intfcTpId, VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(hdrT.intfcRecTpId, VAL_INTFC_REC_TP_ID_HDR);
        ZYPEZDItemValueSetter.setValue(hdrT.wmsCmpyCd, (String) soHeader.get(COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsSoId, (String) soHeader.get(COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdNum, (String) soHeader.get(COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.altDocNum, (String) soHeader.get(COL_PICK_TKT_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.custOrdNum, (String) soHeader.get(COL_CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.chrgToCustCd, (String) soHeader.get(COL_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.billToCustCd, (String) soHeader.get(COL_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.shipToCustCd, (String) soHeader.get(COL_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsPrtyCd, VAL_WMS_PRTY_CD);
        ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdTpCd, (String) soHeader.get(COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsTrxCd, (String) soHeader.get(COL_S80_TRX_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdSrcCd, (String) soHeader.get(COL_S80_ORD_SRC_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsSoStsCd, VAL_WMS_SO_STS_CD);
        ZYPEZDItemValueSetter.setValue(hdrT.soShipViaCd, (String) soHeader.get(COL_EXPT_SHPG_METH_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.shipViaDescTxt, (String) soHeader.get(COL_SHPG_METH_NM));
        ZYPEZDItemValueSetter.setValue(hdrT.cratDtTmTs, (String) soHeader.get(COL_SO_CRAT_TS));
        ZYPEZDItemValueSetter.setValue(hdrT.estShipDtTmTs, (String) soHeader.get(COL_PSD_DT_TM));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsRqstDtTmTs, (String) soHeader.get(COL_RDD_DT_TM));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsPrintDtTmTs, (String) soHeader.get(COL_DNLD_TM_TS));
        ZYPEZDItemValueSetter.setValue(hdrT.frtOutCd, (String) soHeader.get(COL_S80_SHPG_TERM_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.frtOutDescTxt, (String) soHeader.get(COL_S80_SHPG_TERM_NM));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsSoCarrCd, (String) soHeader.get(COL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.indOtmAddrSwthFlg, (String) soHeader.get(COL_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.indScc14Flg, (String) soHeader.get(COL_PRINT_SCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.indUccFlg, (String) soHeader.get(COL_PRINT_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.indMixedPltFlg, (String) soHeader.get(COL_MIX_PLT_ALLW_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.indPltLbFlg, (String) soHeader.get(COL_PRINT_PLT_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.sccNumFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrT.indNonAsnFlg, (String) soHeader.get(COL_PRINT_NON_ASN_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.indAsnFlg, (String) soHeader.get(COL_ASN_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.indUccNumFlg, (String) soHeader.get(COL_UCC_NUM_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsLbNum, (String) soHeader.get(COL_EDI_TP_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.custStoreNum, (String) soHeader.get(COL_CUST_STORE_NUM));
        String custDcNum = (String) soHeader.get(COL_CUST_STORE_NUM);
        if (ZYPCommonFunc.hasValue(custDcNum) && custDcNum.length() > LENGTH_CUST_DC_NUM) {
            custDcNum = custDcNum.substring(0, LENGTH_CUST_DC_NUM);
        }
        ZYPEZDItemValueSetter.setValue(hdrT.custDcNum, custDcNum);
        ZYPEZDItemValueSetter.setValue(hdrT.wmsCustDeptNum, (String) soHeader.get(COL_SO_DEPT_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsConslFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrT.totShipPrcAmtNum, (BigDecimal) soHeader.get(COL_TOT_SHIP_AMT));
        // totWtAmtNum is set after SO_DTL process.
        ZYPEZDItemValueSetter.setValue(hdrT.rtrnLbCd, (String) soHeader.get(COL_RTRN_LB_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.indConfigFlg, (String) soHeader.get(COL_SO_CONFIG_FLG));
        ZYPEZDItemValueSetter.setValue(hdrT.asgShipViaCd, (String) soHeader.get(COL_TPL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.asgPrtyCd, (String) soHeader.get(COL_TPL_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.indSgnReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrT.billAcctNum, (String) soHeader.get(COL_CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.schdDelyDt, (String) soHeader.get(COL_SCHD_DELY_DT));
        ZYPEZDItemValueSetter.setValue(hdrT.carrCd, (String) soHeader.get(COL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.shpgSvcLvlCd, (String) soHeader.get(COL_SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.trxHdrNum, (String) soHeader.get(COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.usrCdIstlRefTxt, (String) soHeader.get(COL_SO_CONFIG_FLG));
        // rtrnItemInclFlg is set after SO_DTL process.
        ZYPEZDItemValueSetter.setValue(hdrT.svcConfigMstrPk, (BigDecimal) soHeader.get(COL_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(hdrT.asmReqFlg, (String) soHeader.get(COL_PRE_ISTL_SHOP_RQST_FLG));
        // stageActCd is set after additionalSoHeaderValSet logic.
        ZYPEZDItemValueSetter.setValue(hdrT.stageRecStsCd, STAGE_REC_STS_NEW_UPDATE);
        ZYPEZDItemValueSetter.setValue(hdrT.carrSvcHldAtLocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrT.rtePathCd, (String) soHeader.get(COL_WMS_RTE_PATH_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.rtrnTagReqFlg, ZYPConstant.FLG_OFF_N);
        // otbdSrcOrdTpTxt is set after additionalSoHeaderValSet logic.
        // prtToCustFlg is set after additionalSoHeaderValSet logic.
        ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdNum, (String) soHeader.get(COL_SRC_ORD_NUM));
        // slsOrdAdminNm is set after additionalSoHeaderValSet logic.
        ZYPEZDItemValueSetter.setValue(hdrT.slsRepPsnNm, (String) soHeader.get(COL_SR_PSN_NM));

        // additionalSoHeaderValSet
        String sceOrdTpCd = (String) soHeader.get(COL_SCE_ORD_TP_CD);
        if (sceOrdTpCd != null) {
            if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
             || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {
                ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdTpTxt, (String) soHeader.get(COL_OTBD_SRC_ORD_TP_TXT));
                ZYPEZDItemValueSetter.setValue(hdrT.slsOrdAdminNm, (String) soHeader.get(COL_CPO_PSN_NM));
            } else {
                ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdTpTxt, (String) soHeader.get(COL_SCE_ORD_TP_NM));
            }
            if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)
             && soHeader.get(COL_LOC_GRP_CD) != null
             && LOC_GRP.CUSTOMER.equals((String) soHeader.get(COL_LOC_GRP_CD))) {
                ZYPEZDItemValueSetter.setValue(hdrT.prtToCustFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(hdrT.prtToCustFlg, ZYPConstant.FLG_OFF_N);
            }
            if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {
                ZYPEZDItemValueSetter.setValue(hdrT.slsOrdAdminNm, (String) soHeader.get(COL_PO_PSN_NM));
            }
            // when SCE_ORD_TP_CD IN ('DI', 'RP', 'RE', 'RD', 'TR')
            // slsOrdAdminNm set after SO_DTL process.
        }

        if (!isWmsInbdSoUpdate(hdrT.wmsSoId.getValue())) {
            ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, STAGE_ACT_NEW);
        } else {
            ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, STAGE_ACT_UPDATE);
        }

        // additional so header set default.
        ZYPEZDItemValueSetter.setValue(hdrT.rtrnItemInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrT.asmReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hdrT.rtrnTagReqFlg, ZYPConstant.FLG_OFF_N);

        return hdrT;
    }

    /**
     * isWmsInbdSoUpdate
     * @param  wmsSoId, String
     * @return Map<String, Object>
     */
    private boolean isWmsInbdSoUpdate(String wmsSoId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wmsSoId", wmsSoId);

        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countWmsInbdSo", ssmParam);

        return (count.compareTo(BigDecimal.ZERO) > 0);
    }

    /**
     * create WMS_INBD_SO_HDR.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     */
    private void createWmsInbdSoHdr(WMS_INBD_SO_HDRTMsg hdrT) {

        EZDTBLAccessor.insert(hdrT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrT.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM);
            String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.wmsSoId.getValue());

            throw new S21AbendException(NLGM0045E, new String[] {WMS_INBD_SO_HDR, SHPG_ORD, keyFieldName, keyValue });
        }
    }

    /**
     * Create WMS_INBD_SO_DTL
     * @param hdrT WMS_INBD_SO_HDRTMsg
     * @param soDetail Map<String, Object>
     * @param toWhAgwAndCarrAgwFlg boolean
     * @return WMS_INBD_SO_HDRTMsg
     */
    private boolean createWmsInbdSoDtl(WMS_INBD_SO_HDRTMsg hdrT
                                        , Map<String, Object> soDtl
                                        , boolean toWhAgwAndCarrAgwFlg) {

        WMS_INBD_SO_DTLTMsg dtlT = new WMS_INBD_SO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd.getValue());
        if (toWhAgwAndCarrAgwFlg) {
            ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, (String) soDtl.get(COL_SHIP_TO_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) soDtl.get(COL_SHIP_TO_RTL_SWH_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, (String) soDtl.get(COL_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) soDtl.get(COL_RTL_SWH_CD));
        }
        ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, new BigDecimal((String) soDtl.get(COL_SO_SLP_NUM)));
        ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, VAL_INTFC_REC_TP_ID_DTL);
        ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsSoId, hdrT.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) soDtl.get(COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) soDtl.get(COL_S80_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.custMdseCd, (String) soDtl.get(COL_CUST_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdQty, (BigDecimal) soDtl.get(COL_RQST_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(dtlT.backOrdQtyNum, (BigDecimal) soDtl.get(COL_SHPG_BAL_QTY));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsShipQty, (BigDecimal) soDtl.get(COL_SHPG_QTY));
        ZYPEZDItemValueSetter.setValue(dtlT.unitPrcAmtNum, (BigDecimal) soDtl.get(COL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(dtlT.unitDiscAmtNum, (BigDecimal) soDtl.get(COL_DISC_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(dtlT.unitDiscPrcAmtNum, (BigDecimal) soDtl.get(COL_DISC_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsTotAmtNum, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlT.indSerId, (String) soDtl.get(COL_SER_NUM_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(dtlT.indVoidAllwFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCdToCd, (String) soDtl.get(COL_S80_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetCd, (String) soDtl.get(COL_SET_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetDescTxt, (String) soDtl.get(COL_SET_MDSE_NM));
        ZYPEZDItemValueSetter.setValue(dtlT.shipSetQty, (BigDecimal) soDtl.get(COL_SET_SHPG_QTY));
        ZYPEZDItemValueSetter.setValue(dtlT.unitInsdQty, (BigDecimal) soDtl.get(COL_IN_EACH_QTY));
        ZYPEZDItemValueSetter.setValue(dtlT.totWtAmtNum, (BigDecimal) soDtl.get(COL_TOT_SHPG_WT));
        ZYPEZDItemValueSetter.setValue(dtlT.totVolAmtNum, (BigDecimal) soDtl.get(COL_TOT_SHPG_VOL));
        ZYPEZDItemValueSetter.setValue(dtlT.batCptrReqFlg, (String) soDtl.get(COL_BAT_NUM_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(dtlT.indConfigFlg, (String) soDtl.get(COL_CONFIG_ITEM_FLG));
        ZYPEZDItemValueSetter.setValue(dtlT.svcConfigMstrPk, (BigDecimal) soDtl.get(COL_PICK_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(dtlT.backOrdImpctTpCd, (String) soDtl.get(COL_BACK_ORD_IMPCT_TP_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdUomCd, WMS_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(dtlT.poLineTxt, (String) soDtl.get(COL_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(dtlT.usrCdIstlRefTxt, (String) soDtl.get(COL_CONFIG_ITEM_FLG));
        ZYPEZDItemValueSetter.setValue(dtlT.backOrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlT.rmvConfigFlg, (String) soDtl.get(COL_RMV_CONFIG_FLG));

        CPO_DTLTMsg cpoDtlTMsg = getCpoDtl((String) soDtl.get(COL_TRX_HDR_NUM)
                                 , (String) soDtl.get(COL_TRX_LINE_NUM)
                                 , (String) soDtl.get(COL_TRX_LINE_SUB_NUM));

        if (cpoDtlTMsg != null) {
            ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, cpoDtlTMsg.ordQty);
        } else {
            ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, dtlT.wmsOrdQty);
        }

        // additional so header set
        BigDecimal totWtAmtNum = BigDecimal.ZERO;
        if (hdrT.totWtAmtNum.getValue() != null && dtlT.totWtAmtNum.getValue() != null) {
            totWtAmtNum = hdrT.totWtAmtNum.getValue().add(dtlT.totWtAmtNum.getValue());
        } else if (hdrT.totWtAmtNum.getValue() == null && dtlT.totWtAmtNum.getValue() != null) {
            totWtAmtNum = dtlT.totWtAmtNum.getValue();
        }
        ZYPEZDItemValueSetter.setValue(hdrT.totWtAmtNum, totWtAmtNum);

        String rtrnReqPrtFlg = (String) soDtl.get(COL_RTRN_REQ_PRT_FLG);
        if (rtrnReqPrtFlg != null && ZYPConstant.FLG_ON_Y.equals(rtrnReqPrtFlg)) {
            ZYPEZDItemValueSetter.setValue(hdrT.rtrnItemInclFlg, ZYPConstant.FLG_ON_Y);
        }
        String preIstlShopRqstFlg = (String) soDtl.get(COL_PRE_ISTL_SHOP_RQST_FLG);
        if (preIstlShopRqstFlg != null && ZYPConstant.FLG_ON_Y.equals(preIstlShopRqstFlg)) {
            ZYPEZDItemValueSetter.setValue(hdrT.asmReqFlg, ZYPConstant.FLG_ON_Y);
        }
        String mdseItemTpCd = (String) soDtl.get(COL_MDSE_ITEM_TP_CD);
        if (mdseItemTpCd != null && MDSE_ITEM_TP.PARTS.equals(mdseItemTpCd)) {
            ZYPEZDItemValueSetter.setValue(hdrT.rtrnTagReqFlg, ZYPConstant.FLG_ON_Y);
        }

        EZDTBLAccessor.insert(dtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlT.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM);
            String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, dtlT.wmsSoId.getValue());

            throw new S21AbendException(NLGM0045E, new String[] {WMS_INBD_SO_DTL, SHPG_ORD_DTL, keyFieldName, keyValue });
        }

        updateShpgOrdDtl(dtlT);

        return true;
    }

    /**
     * getCpoDtl
     * @param String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum
     * @return CPO_DTLTMsg
     */
    private CPO_DTLTMsg getCpoDtl(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg inMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        CPO_DTLTMsg outMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    private boolean updateShpgOrdDtl(WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg) {

        SHPG_ORD_DTLTMsg soDtlT = new SHPG_ORD_DTLTMsg();

        String soSlpNum = ZYPCommonFunc.leftPad(String.valueOf(wmsInbdSoDtlTMsg.wmsLineNum.getValue()), SIZE_3, "0");
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

    /**
     * Create WMS_INBD_SO_TEXT.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     * @param soTextList List<Map<String, Object>>
     * @return WMS_INBD_SO_HDRTMsg
     */
    private void createWmsInbdSoText(WMS_INBD_SO_HDRTMsg hdrT, List<Map<String, Object>> soTextList) {

        WMS_INBD_SO_TEXTTMsg textT = new WMS_INBD_SO_TEXTTMsg();

        ZYPEZDItemValueSetter.setValue(textT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(textT.whCd, hdrT.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(textT.wmsSqNum, hdrT.wmsSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(textT.intfcTpId, VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(textT.intfcRecTpId, VAL_INTFC_REC_TP_ID_TXT);
        ZYPEZDItemValueSetter.setValue(textT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(textT.wmsSoId, hdrT.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(textT.wmsPrintTpCd, VAL_PRINT_TP_CD_B);

        if (soTextList != null && soTextList.size() > 0) {
            // Repeat the split registration
            for (int i = 0; i < soTextList.size(); i++) {
                switch (i) {
                    case 0:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_01, (String) soTextList.get(i).get(COL_SO_MSG_DESC_TXT));
                        ZYPEZDItemValueSetter.setValue(textT.wmsTxtCd, (String) soTextList.get(i).get(COL_SO_MSG_TP_CD));
                    break;
                    case 1:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_02, (String) soTextList.get(i).get(COL_SO_MSG_DESC_TXT));
                    break;
                    case 2:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_03, (String) soTextList.get(i).get(COL_SO_MSG_DESC_TXT));
                    break;
                    case 3:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_04, (String) soTextList.get(i).get(COL_SO_MSG_DESC_TXT));
                    break;
                    default:
                        break;
                }
            }
            EZDTBLAccessor.insert(textT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(textT.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM);
                String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, textT.wmsSoId.getValue());

                throw new S21AbendException(NLGM0045E, new String[] {WMS_INBD_SO_TEXT, SHPG_ORD_MSG, keyFieldName, keyValue });
            }
        }
    }

    /**
     * Create WMS_INBD_SO_SHIP_TOTMsg.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     */
    private void createShipTo(WMS_INBD_SO_HDRTMsg hdrT) {

        WMS_INBD_SO_SHIP_TOTMsg shipToT = new WMS_INBD_SO_SHIP_TOTMsg();

        ZYPEZDItemValueSetter.setValue(shipToT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipToT.whCd, hdrT.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(shipToT.wmsSqNum, hdrT.wmsSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(shipToT.intfcTpId, VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(shipToT.intfcRecTpId, VAL_INTFC_REC_TP_ID_SHIP);
        ZYPEZDItemValueSetter.setValue(shipToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(shipToT.wmsSoId, hdrT.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(shipToT.wmsCustCd, hdrT.shipToCustCd.getValue());

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", hdrT.wmsSoId.getValue());
        ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SHIP_TO);
        ssmParam.put("lgLineColNm", MAX_LENGTH_SO_CUST_LINE_LOC_NM);
        ssmParam.put("lgCtacTelNum", MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);

        Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);

        if (soCustDtl != null) {
            ZYPEZDItemValueSetter.setValue(shipToT.wmsShipToNm_01, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_01));
            ZYPEZDItemValueSetter.setValue(shipToT.wmsShipToNm_02, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_02));
            ZYPEZDItemValueSetter.setValue(shipToT.firstLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_01));
            ZYPEZDItemValueSetter.setValue(shipToT.scdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_02));
            ZYPEZDItemValueSetter.setValue(shipToT.thirdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_03));
            ZYPEZDItemValueSetter.setValue(shipToT.frthLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_04));
            ZYPEZDItemValueSetter.setValue(shipToT.ctyAddr, (String) soCustDtl.get(COL_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(shipToT.stCd, (String) soCustDtl.get(COL_ST_CD));
            ZYPEZDItemValueSetter.setValue(shipToT.postCd, (String) soCustDtl.get(COL_POST_CD));
            ZYPEZDItemValueSetter.setValue(shipToT.ctryCd, (String) soCustDtl.get(COL_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(shipToT.wmsShipToCtacNm, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_NM));
            ZYPEZDItemValueSetter.setValue(shipToT.shipToCtacNum, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_TEL_NUM));

            EZDTBLAccessor.insert(shipToT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToT.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM);
                String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, shipToT.wmsSoId.getValue());

                throw new S21AbendException(NLGM0045E, new String[] {shipToT.getTableName(), SHPG_ORD_CUST_DTL, keyFieldName, keyValue});
            }
        }
    }

    /**
     * Create WMS_INBD_SO_CHRG_TOTMsg.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     */
    private void createChrgTo(WMS_INBD_SO_HDRTMsg hdrT) {

        WMS_INBD_SO_CHRG_TOTMsg chrgToT = new WMS_INBD_SO_CHRG_TOTMsg();

        ZYPEZDItemValueSetter.setValue(chrgToT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chrgToT.whCd, hdrT.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(chrgToT.wmsSqNum, hdrT.wmsSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(chrgToT.intfcTpId, VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(chrgToT.intfcRecTpId, VAL_INTFC_REC_TP_ID_CHRG);
        ZYPEZDItemValueSetter.setValue(chrgToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(chrgToT.wmsSoId, hdrT.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(chrgToT.wmsCustCd, hdrT.chrgToCustCd.getValue());

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", hdrT.wmsSoId.getValue());
        ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SELL_TO);
        ssmParam.put("lgLineColNm", MAX_LENGTH_SO_CUST_LINE_LOC_NM);
        ssmParam.put("lgCtacTelNum", MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);
        Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);

        if (soCustDtl != null) {
            ZYPEZDItemValueSetter.setValue(chrgToT.wmsChrgToNm_01, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_01));
            ZYPEZDItemValueSetter.setValue(chrgToT.wmsChrgToNm_02, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_02));
            ZYPEZDItemValueSetter.setValue(chrgToT.firstLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_01));
            ZYPEZDItemValueSetter.setValue(chrgToT.scdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_02));
            ZYPEZDItemValueSetter.setValue(chrgToT.thirdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_03));
            ZYPEZDItemValueSetter.setValue(chrgToT.frthLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_04));
            ZYPEZDItemValueSetter.setValue(chrgToT.ctyAddr, (String) soCustDtl.get(COL_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(chrgToT.stCd, (String) soCustDtl.get(COL_ST_CD));
            ZYPEZDItemValueSetter.setValue(chrgToT.postCd, (String) soCustDtl.get(COL_POST_CD));
            ZYPEZDItemValueSetter.setValue(chrgToT.ctryCd, (String) soCustDtl.get(COL_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(chrgToT.wmsChrgToCtacNm, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_NM));
            ZYPEZDItemValueSetter.setValue(chrgToT.chrgToCtacNum, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_TEL_NUM));

            EZDTBLAccessor.insert(chrgToT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(chrgToT.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM);
                String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, chrgToT.wmsSoId.getValue());

                throw new S21AbendException(NLGM0045E, new String[] {chrgToT.getTableName(), SHPG_ORD_CUST_DTL, keyFieldName, keyValue});
            }
        }
    }

    /**
     * Create WMS_INBD_SO_BILL_TOTMsg.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     */
    private void createBillTo(WMS_INBD_SO_HDRTMsg hdrT) {

        WMS_INBD_SO_BILL_TOTMsg billToT = new WMS_INBD_SO_BILL_TOTMsg();

        ZYPEZDItemValueSetter.setValue(billToT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(billToT.whCd, hdrT.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(billToT.wmsSqNum, hdrT.wmsSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(billToT.intfcTpId, VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(billToT.intfcRecTpId, VAL_INTFC_REC_TP_ID_BILL);
        ZYPEZDItemValueSetter.setValue(billToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(billToT.wmsSoId, hdrT.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(billToT.wmsCustCd, hdrT.billToCustCd.getValue());

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", hdrT.wmsSoId.getValue());
        ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.BILL_TO);
        ssmParam.put("lgLineColNm", MAX_LENGTH_SO_CUST_LINE_LOC_NM);
        ssmParam.put("lgCtacTelNum", MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);
        Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);

        if (soCustDtl != null) {
            ZYPEZDItemValueSetter.setValue(billToT.wmsBillToNm_01, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_01));
            ZYPEZDItemValueSetter.setValue(billToT.wmsBillToNm_02, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_02));
            ZYPEZDItemValueSetter.setValue(billToT.firstLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_01));
            ZYPEZDItemValueSetter.setValue(billToT.scdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_02));
            ZYPEZDItemValueSetter.setValue(billToT.thirdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_03));
            ZYPEZDItemValueSetter.setValue(billToT.frthLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_04));
            ZYPEZDItemValueSetter.setValue(billToT.ctyAddr, (String) soCustDtl.get(COL_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(billToT.stCd, (String) soCustDtl.get(COL_ST_CD));
            ZYPEZDItemValueSetter.setValue(billToT.postCd, (String) soCustDtl.get(COL_POST_CD));
            ZYPEZDItemValueSetter.setValue(billToT.ctryCd, (String) soCustDtl.get(COL_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(billToT.wmsBillToCtacNm, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_NM));
            ZYPEZDItemValueSetter.setValue(billToT.billToCtacNum, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_TEL_NUM));

            EZDTBLAccessor.insert(billToT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(billToT.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM);
                String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, billToT.wmsSoId.getValue());

                throw new S21AbendException(NLGM0045E, new String[] {billToT.getTableName(), SHPG_ORD_CUST_DTL, keyFieldName, keyValue});
            }
        }
    }

    /**
     * updateSoWmsDropFlg
     * @param soNum
     * @param fromWhAgwFlg
     */
    private void updateSoWmsDropFlg(String soNum, boolean fromWhAgwFlg) {

        SHPG_ORDTMsg tMsgTbl = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsgTbl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsgTbl.soNum, soNum);

        tMsgTbl = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgTbl);

        if (fromWhAgwFlg) {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.wmsDropFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.delyReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsgTbl.delyReqTs, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSSSSS));
        }

        EZDTBLAccessor.update(tMsgTbl);
    }

    /**
     * <pre>
     * createRwsInterface
     * </pre>
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void createRwsInterface() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get target RWS_NUMs
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
            bindParam.put(COL_WH_OWNR_CD, whOwnrCd);
            bindParam.put(COL_CARR_TP_NM, carrTpCd);
            bindParam.put("flgY", ZYPConstant.FLG_ON_Y);

            stmt = ssmLLClient.createPreparedStatement("findRws", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop by RWS#
            int processedCount = 0;
            while (rs.next()) {
                BigDecimal trxId = trxAccess.getNextTransactionId();
                int unitId = 1;
                int seqNumber = 0;
                boolean errFlg = false;

                String rwsNum = rs.getString(COL_RWS_NUM);
                boolean toWhAgwFlg = getRwsToWhAgw(rwsNum);

                // RWS Header
                Map<String, Object> rwsHeader = getRwsHeader(rwsNum);

                Map<String, String> ctacPsnInfo = new HashMap<String, String>();
                Map<String, String> customerInfo = new HashMap<String, String>();
                seqNumber = createRwsHeader(rwsHeader
                                            , trxId
                                            , new BigDecimal(unitId)
                                            , seqNumber
                                            , ctacPsnInfo
                                            , customerInfo);

                // RWS Text
                List<Map<String, Object>> rwsTextList = getRwsText(rwsNum);
                seqNumber = createRwsText(rwsTextList, trxId, new BigDecimal(unitId), seqNumber);

                // Create WMS_INBD_PO_HDR
                WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT = null;
                if (toWhAgwFlg) {
                    wmsInbdPoHdrT = createWmsInbdPoHdr(rwsHeader, rwsTextList);
                    if (wmsInbdPoHdrT == null) {
                        errorCount++;
                        rollback();
                        continue;
                    } else {
                        // Create WMS_INBD_PO_VND
                        createWmsInbdPoVnd(rwsHeader, wmsInbdPoHdrT.wmsSqNum.getValue());
                    }
                }

                // RWS Site Survey
                Map<String, Object> rwsSiteSurvey = getRwsSiteSurvey(rwsNum);

                if (rwsSiteSurvey != null) {
                    if (ctacPsnInfo != null) {
                        rwsSiteSurvey.put("CONTACT", ctacPsnInfo.get("CONTACT"));
                        rwsSiteSurvey.put("PHONE", ctacPsnInfo.get("PHONE"));
                    }
                    if (customerInfo != null) {
                        rwsSiteSurvey.put("reqDtTmTsTxt", customerInfo.get("reqDtTmTsTxt"));
                        rwsSiteSurvey.put("postCd", customerInfo.get("postCd"));
                        rwsSiteSurvey.put("ctryCd", customerInfo.get("ctryCd"));
                    }
                }

                // RWS Detail
                List<Map<String, Object>> rwsDetailList = getRwsDetail(rwsNum);

                // RWS MDSE Survey
                List<Map<String, Object>> rwsMdseSurveyList = getRwsMdseSurvey(rwsNum);

                for (Map<String, Object> rwsDetail : rwsDetailList) {
                    if (rwsSiteSurvey != null) {
                        rwsSiteSurvey.put("CARR_DELY_TM_HOUR_MN", rwsDetail.get("SCHD_PICK_UP_TM"));
                    }

                    String rwsDtlLineNum = (String) rwsDetail.get(COL_RWS_DTL_LINE_NUM);

                    ++unitId;
                    seqNumber = 0;

                    // create RWS Detail
                    seqNumber = createRwsDetail(rwsDetail, trxId, new BigDecimal(unitId), seqNumber);

                    // Create WMS_INBD_PO_DTL
                    if (toWhAgwFlg && !createWmsInbdPoDtl(wmsInbdPoHdrT, rwsDetail)) {
                        errFlg = true;
                        break;
                    }

                    // RWS Serial
                    List<Map<String, Object>> rwsSerialList = getRwsSerial(rwsNum, rwsDtlLineNum);

                    seqNumber = createRwsSerial(rwsSerialList, trxId, new BigDecimal(unitId), seqNumber);

                    if (rwsSiteSurvey != null) {
                        seqNumber = createRwsSiteSurvey(rwsSiteSurvey, trxId, new BigDecimal(unitId), seqNumber);
                        rwsSiteSurvey = null;
                    }

                    if (rwsMdseSurveyList != null) {
                        seqNumber = createRwsMdseSurvey(rwsMdseSurveyList, trxId, new BigDecimal(unitId), seqNumber);
                        rwsMdseSurveyList = null;
                    }
                }

                if (errFlg) {
                    errorCount++;
                    rollback();
                    continue;
                }

                // Update of WMS send status
                updateRwsWmsDropFlg(rwsNum, toWhAgwFlg);
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);

                ++successCount;
                ++processedCount;

                // commit
                if (processedCount >= commitCount) {
                    commit();
                    processedCount = 0;
                }
            }
            // Loop by RWS#
            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // exist RWS where WH_OWNR_CD = AGW
    private boolean getRwsToWhAgw(String rwsNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);
        bindParam.put(COL_WH_OWNR_CD, whOwnrCd);

        if (null == ssmBatchClient.queryObject("getRwsToWhAgw", bindParam, execParam)) {
            return false;
        }
        return true;
    }

    /**
     * updateRwsWmsDropFlg
     * @param rwsNum
     * @param toWhAgwFlg
     */
    private void updateRwsWmsDropFlg(String rwsNum, boolean toWhAgwFlg) {

        RWS_HDRTMsg tMsgTbl = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsgTbl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsgTbl.rwsNum, rwsNum);

        tMsgTbl = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgTbl);

        if (toWhAgwFlg) {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.wmsDropStsCd, ZYPConstant.FLG_ON_1);

            if (tMsgTbl.pickUpRqstFlg != null && ZYPConstant.FLG_ON_Y.equals(tMsgTbl.pickUpRqstFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsgTbl.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsgTbl.pickUpRqstTs, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSSSSS));
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsgTbl.pickUpRqstTs, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSSSSS));
        }

        EZDTBLAccessor.update(tMsgTbl);
    }

    // Get RWS data (Header)
    private Map<String, Object> getRwsHeader(String rwsNum) {

        Map<String, Object> bindParam = new HashMap<String, Object>();

        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);
        bindParam.put("change", VAL_CHANGE);
        bindParam.put("newOrder", VAL_NEW_ORDER);
        bindParam.put("return", SCE_ORD_TP.RETURN);
        bindParam.put("rossReturn", VAL_ROSS_RETURN);
        bindParam.put("lr", VAL_ORD_TP_TXT_LR);
        bindParam.put("lf", VAL_ORD_TP_TXT_LF);
        bindParam.put("sceOrdTpList", this.sceOrdTpList);

        Map<String, Object> rwsHeader = (Map<String, Object>) ssmBatchClient.queryObject("findRwsHeader", bindParam, execParam);

        // get TPL_CARR_SVC_LVL
        String tplCarrCd = VAL_CONST_CARR_CD;
        String tplCarrSvcLvlCd = null;
        Map<String, Object> tplCarrSvcLvl = null;
        tplCarrSvcLvl = getTplCarrSvcLvlByRws(rwsNum);
        if (tplCarrSvcLvl != null && ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(COL_TPL_CARR_CD))) {
            tplCarrCd = (String) tplCarrSvcLvl.get(COL_TPL_CARR_CD);
            tplCarrSvcLvlCd = (String) tplCarrSvcLvl.get(COL_TPL_SVC_LVL_CD);
        }

        rwsHeader.put(COL_TPL_CARR_CD, tplCarrCd);
        rwsHeader.put(COL_TPL_SVC_LVL_CD, tplCarrSvcLvlCd);

        return rwsHeader;
    }

    /**
     * getTplCarrSvcLvlForRWS
     * @param rwsNum
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvlByRws(String rwsNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(COL_RWS_NUM, rwsNum);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvlByRws", queryParam, execParam);
        return result;
    }

    private int createRwsHeader(Map<String, Object> rwsHeader
                                 , BigDecimal trxId
                                 , BigDecimal unitId
                                 , int seqNumber
                                 , Map<String, String> ctacPsnInfo
                                 , Map<String, String> customerInfo) {

        // Set to TMsg(NLBI1510_01)
        NLBI1510_01TMsg tMsgRwsHeader = new NLBI1510_01TMsg();

        ++seqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.seqNumber, new BigDecimal(seqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsRecId, VAL_05);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplFromPtnrId, VAL_CONST_TPL_FROM_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplToPtnrId, VAL_CONST_TPL_TO_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordDtTmTsTxt, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSSSSS));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordIdTxt, (String) rwsHeader.get(COL_RWS_NUM));

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplRefNm, trxId.toPlainString());
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplLocTxt, getRcvPntCd((String) rwsHeader.get(COL_WH_CD)));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplCondTxt, VAL_01);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordTpTxt, (String) rwsHeader.get(COL_ORD_TP_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplOrgTxt, VAL_CONST_TPL_ORG_TXT);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplCtrlId, (String) rwsHeader.get(COL_TPL_CTRL_ID));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.carrCd, (String) rwsHeader.get(COL_TPL_CARR_CD));

        String fromLocCd = (String) rwsHeader.get(COL_FROM_LOC_CD);
        String whInEtaDt = (String) rwsHeader.get(COL_WH_IN_ETA_DT);
        String whInEtaTm = (String) rwsHeader.get(COL_WH_IN_ETA_TM);

        if (ZYPCommonFunc.hasValue(whInEtaTm)) {
            whInEtaDt = whInEtaDt + whInEtaTm;
        }

        if (ZYPCommonFunc.hasValue(whInEtaDt)) {

            // Convert Customer Timezone
            if (whInEtaDt.length() == FMT_YYYYMMDDHHMM.length()) {
                whInEtaDt = ZYPDateUtil.DateFormatter(whInEtaDt, FMT_YYYYMMDDHHMM, FMT_YYYYMMDDHHMMSSSSS);
            } else {
                whInEtaDt = ZYPDateUtil.DateFormatter(whInEtaDt, FMT_YYYYMMDD, FMT_YYYYMMDDHHMMSSSSS);
            }
            if (ZYPCommonFunc.hasValue(whInEtaTm)) {
                whInEtaDt = convCustomerTime(whInEtaDt
                                             , (String) rwsHeader.get(COL_FROM_LOC_CTRY_CD)
                                             , (String) rwsHeader.get(COL_FROM_LOC_POST_CD));
            }
        }

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.reqDtTmTsTxt, whInEtaDt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.rqstShipDtTmTsTxt, whInEtaDt);

        String telNum = (String) rwsHeader.get(COL_FROM_LOC_TEL_NUM);
        String ctacPsnNm = (String) rwsHeader.get(COL_FROM_LOC_PSN_NM);
        Map<String, Object> dsCpoCtacPsn = new HashMap<String, Object>();

        if (!ZYPCommonFunc.hasValue(telNum) || !ZYPCommonFunc.hasValue(ctacPsnNm)) {
            dsCpoCtacPsn = getShipCtacPhoNumFromDsCpoCtacPsn((String) rwsHeader.get(COL_RWS_NUM));
        }

        if (!ZYPCommonFunc.hasValue(telNum)) {
            if (dsCpoCtacPsn != null) {
                telNum = (String) dsCpoCtacPsn.get(COL_CTAC_PSN_TEL_NUM);
            }
        }

        int telLen = tMsgRwsHeader.getAttr("shipCtacPhoNum").getDigit();
        if (ZYPCommonFunc.hasValue(telNum) && telNum.length() > telLen) {
            telNum = telNum.substring(0, telLen);
        }

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCtacPhoNum, telNum);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipPhoNumTxt, telNum);
        if (!ZYPCommonFunc.hasValue(ctacPsnNm)) {
            if (dsCpoCtacPsn != null) {
                ctacPsnNm = (String) dsCpoCtacPsn.get(COL_CTAC_PSN_NM);
            }
        }

        if (ZYPCommonFunc.hasValue(ctacPsnNm) && ctacPsnNm.length() > MAX_LENGTH_CTAC_PSN_NM) {
            ctacPsnNm = ctacPsnNm.substring(0, MAX_LENGTH_CTAC_PSN_NM);
        }

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCtacNmTxt, ctacPsnNm);

        ctacPsnInfo.put("CONTACT", ctacPsnNm);
        ctacPsnInfo.put("PHONE", telNum);

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplPtnrTxt, VAL_CONST_TPL_PTNR_TXT);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCustTxt, fromLocCd);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipFirstLineNm, (String) rwsHeader.get(COL_FROM_LOC_NM_01));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipScdLineNm, (String) rwsHeader.get(COL_FROM_LOC_NM_02));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipFirstLineAddrTxt, (String) rwsHeader.get(COL_FROM_LOC_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipScdLineAddrTxt, (String) rwsHeader.get(COL_FROM_LOC_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipToPostCd, (String) rwsHeader.get(COL_FROM_LOC_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCtyTxt, (String) rwsHeader.get(COL_FROM_LOC_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCtryTxt, (String) rwsHeader.get(COL_FROM_LOC_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipToStCd, (String) rwsHeader.get(COL_FROM_LOC_ST_CD));

        //customerInfo
        if (ZYPCommonFunc.hasValue(whInEtaDt)) {
            customerInfo.put("reqDtTmTsTxt", whInEtaDt.substring(0, LENGTH_TS_8));
        } else {
            customerInfo.put("reqDtTmTsTxt", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgRwsHeader.shipToPostCd)) {
            customerInfo.put("postCd", tMsgRwsHeader.shipToPostCd.getValue());
        } else {
            customerInfo.put("postCd", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgRwsHeader.shipCtryTxt)) {
            customerInfo.put("ctryCd", tMsgRwsHeader.shipCtryTxt.getValue());
        } else {
            customerInfo.put("ctryCd", "");
        }

        // insert
        EZDTBLAccessor.insert(tMsgRwsHeader);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsHeader.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
            String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

            outputErr(NLGM0045E, new String[] {NLBI1510_01, tMsgRwsHeader.getTableName(), keyFieldName, keyValue });
            throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_01, tMsgRwsHeader.getTableName(), keyFieldName, keyValue });
        }

        return seqNumber;
    }

    /**
     * getRcvPntCd
     * @param rtlWhCd String
     * @return String
     */
    private String getRcvPntCd(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_RTL_WH_CD, rtlWhCd);
        String result = (String) this.ssmBatchClient.queryObject("getRcvPntCd", queryParam, execParam);
        return result;
    }

    // Get RWS data (Detail)
    private List<Map<String, Object>> getRwsDetail(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsDetail", bindParam, execParam);
    }

    private int createRwsDetail(Map<String, Object> rwsDetail
                                 , BigDecimal trxId
                                 , BigDecimal unitId
                                 , int seqNumber) {

        // Set to TMsg(NLBI1510_02)
        NLBI1510_02TMsg tMsgRwsDetail = new NLBI1510_02TMsg();

        ++seqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.seqNumber, new BigDecimal(seqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsRecId, VAL_06);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.itemCdTxt, (String) rwsDetail.get(COL_MDSE_CD));
        BigDecimal rwsQty = (BigDecimal) rwsDetail.get(COL_RWS_QTY);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.qtyOrdTxt, rwsQty.toPlainString());
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.ordLineTxt, (String) rwsDetail.get(COL_RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.tplItemTpCd, VAL_CONST_TPL_ITEM_TP_CD);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.custPoTpTxt, (String) rwsDetail.get(COL_TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.custPoLineTxt, (String) rwsDetail.get(COL_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.tplLocTxt, (String) rwsDetail.get(COL_PLANT_CD));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.svcConfigMstrPk, (BigDecimal) rwsDetail.get(COL_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.itrlItemFlg, (String) rwsDetail.get(COL_ITRL_ITEM_FLG));

        // insert
        EZDTBLAccessor.insert(tMsgRwsDetail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsDetail.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
            String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

            outputErr(NLGM0045E, new String[] {NLBI1510_02, tMsgRwsDetail.getTableName(), keyFieldName, keyValue});
            throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_02, tMsgRwsDetail.getTableName(), keyFieldName, keyValue});
        }

        return seqNumber;
    }

    /**
     * getShipCtacPhoNumFromDsCpoCtacPsn
     * @param soNum
     * @return
     */
    private Map<String, Object> getShipCtacPhoNumFromDsCpoCtacPsn(String rwsNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(COL_RWS_NUM, rwsNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getShipCtacPhoNumFromDsCpoCtacPsn", queryParam, execParam);
    }

    // Get RWS data(Text)
    private List<Map<String, Object>> getRwsText(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsText", bindParam, execParam);
    }

    private int createRwsText(List<Map<String, Object>> rwsTextList
                               , BigDecimal trxId
                               , BigDecimal unitId
                               , int seqNumber) {
        int len = 0;
        for (Map<String, Object> rwsText : rwsTextList) {
            NLBI1510_03TMsg tMsgRwsText = new NLBI1510_03TMsg();

            String text = (String) rwsText.get(COL_RWS_MSG_TXT);
            if (!ZYPCommonFunc.hasValue(text)) {
                continue;
            }
            // START 2023/12/01 G.Quan [QC#62451, ADD]
            text = text.replaceAll("\r\n", " ");
            // END 2023/12/01 G.Quan [QC#62451, ADD]
            len += text.getBytes().length;
            if (len > MAX_LENGTH_TXT) {
                break;
            }

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.wmsRecId, VAL_07);

            ZYPEZDItemValueSetter.setValue(tMsgRwsText.soMsgDescTxt, text);

            // insert
            EZDTBLAccessor.insert(tMsgRwsText);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsText.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_03, tMsgRwsText.getTableName(), keyFieldName, keyValue });
                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_03, tMsgRwsText.getTableName(), keyFieldName, keyValue });
            }
        }

        return seqNumber;
    }

    // Get RWS data(Site Survey)
    private Map<String, Object> getRwsSiteSurvey(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);
        bindParam.put(COL_SCE_ORD_TP_CD, SCE_ORD_TP.RETURN);

        return (Map<String, Object>) ssmBatchClient.queryObject("findRwsSiteSurvey", bindParam, execParam);
    }

    private int createRwsSiteSurvey(Map<String, Object> rwsSiteSurvey
                                     , BigDecimal trxId
                                     , BigDecimal unitId
                                     , int seqNumber) {

        final String[][] nameAndKeys = new String[][] {
            // COMPANY INFORMATION
            new String[] {"SALES ORDER NUMBER", "CPO_ORD_NUM" },
            new String[] {"COMPANY NAME", "FROM_LOC_NM_01" },
            new String[] {"CUSTOMER NUMBER", "FROM_LOC_CD" },
            new String[] {"STREET", "FROM_LOC_ADDR_01" },
            new String[] {"CITY", "FROM_LOC_CTY_ADDR" },
            new String[] {"STATE", "FROM_LOC_ST_CD" },
            new String[] {"APT. OR BUILDING", "CMPY_INFO_APT_BLDG_NM" },
            new String[] {"CONTACT", "CONTACT" },
            new String[] {"DEPARTMENT", "CMPY_INFO_DEPT_NM" },
            new String[] {"PHONE", "PHONE" },
            new String[] {"FLOOR", "CMPY_INFO_FL_NM" },
            new String[] {"POSTAL", "FROM_LOC_POST_CD" },
            new String[] {"FLOOR PROTECTION REQ?", "ELEV_PROT_REQ_FLG" },
            // ADDITIONAL COMMENTS
            new String[] {"ADDITIONAL COMMENTS", "SITE_SRVY_ADDL_CMNT_TXT" },
            // SITE INFORMATION
            new String[] {"NO OF STEPS OUTSIDE", "OTSD_STEP_NUM" },
            new String[] {"NO OF STEPS INSIDE", "INSD_STEP_NUM" },
            new String[] {"STEP CRAWLER REQUIRED?", "STAIR_CRAW_REQ_FLG" },
            new String[] {"NO OF FLIGHTS", "FLGT_STAIR_NUM" },
            new String[] {"WIDTH OF STAIRS AND LANDINGS(IN,)", "STAIR_AND_LDG_WDT" },
            new String[] {"LOADING DOCK AVAILABLE", "LOAD_DOCK_AVAL_FLG" },
            new String[] {"DOCK HEIGHT(IN,)", "LOAD_DOCK_HGT" },
            new String[] {"DELIVERY HOURS FROM", "LOAD_DOCK_AVAL_FROM_HOUR_MN" },
            new String[] {"DELIVERY HOURS TO", "LOAD_DOCK_AVAL_TO_HOUR_MN" },
            new String[] {"TRACTOR TRAILER ACCESSIBLE", "TRCTR_AND_TRAIL_ACCS_FLG" },
            new String[] {"TIMESTOP", "CARR_DELY_TM_HOUR_MN" },
            new String[] {"TRANSPORT OPTION", "DELY_TRNSP_OPT_CD" },
            // ADD SITE INFORMATION
            new String[] {"SECURITY CLEARANCE REQUIRED", "SEC_CLNC_REQ_TXT" },
            new String[] {"INSURANCE CERTIFICATE REQUIRED", "INS_CERT_REQ_TXT" },
            new String[] {"FLOOR COVERING", "FL_COV_TXT" },
            new String[] {"RAMP?", "RAMP_AVAL_TXT" },
            new String[] {"STEPS?", "STEP_AVAL_TXT" },
            new String[] {"DOOR FRONT", "FRONT_DOOR_AVAL_TXT" },
            new String[] {"DOOR BACK", "BACK_DOOR_AVAL_TXT" },
            new String[] {"POWER OUTLET CONFIGURED", "PWR_OTLT_CONFIG_TXT" },
            new String[] {"SIGN ON BUILDING ROADSIDE", "SGN_ON_BLDG_RDSD_TXT" },
            new String[] {"BUILDING # OF STOREYS", "BLDG_STRY_NUM" },
            new String[] {"BUILDING GUARD NOTIFIED", "BLDG_GURD_NTFY_TXT" },
            new String[] {"INDUSTRIAL PARK", "INDL_PARK_TXT" },
            new String[] {"BUSINESS PARK", "BIZ_PARK_TXT" },
            new String[] {"PROFESSIONAL BUILDING", "PRO_BLDG_TXT" },
            new String[] {"SHOPPING CENTER", "SHPNG_CTR_TXT" },
            new String[] {"PRIVATE RESIDENCE", "PVT_RES_TXT" },
            // ELEVATOR INDORMATION & DIMENSIONS
            new String[] {"ELEVATOR AVAILABLE", "ELEV_AVAL_FLG" },
            new String[] {"ELEVATOR HOURS FROM", "ELEV_AVAL_FROM_HOUR_MN" },
            new String[] {"ELEVATOR HOURS TO", "ELEV_AVAL_TO_HOUR_MN" },
            new String[] {"ELEVATOR APPOINTMENT NEEDED?", "ELEV_APPT_REQ_FLG" },
            new String[] {"ELEVATOR CONTACT", "ELEV_CTAC_PSN_NM" },
            new String[] {"ELEVATOR PHONE", "ELEV_CTAC_TEL_NUM" },
            new String[] {"BUILDING ENTRANCE HEIGHT(IN,)", "BLDG_ENT_DOOR_HGT" },
            new String[] {"BUILDING ENTRANCE WIDTH(IN.)", "BLDG_ENT_DOOR_WDT" },
            new String[] {"BUILDING ENTRANCE CORRIDOR WIDTH(IN.)", "CRDR_WDT" },
            new String[] {"BUILDING ENTRANCE DOOR WIDTH(IN.)", "DOOR_WDT" },
            new String[] {"ELEVATOR WIDTH(IN.)", "ELEV_WDT" },
            new String[] {"ELEVATOR DEPTH(IN.)", "ELEV_DEPTH_NUM" },
            new String[] {"ELEVATOR CAPACITY(LBS.)", "ELEV_CAP_WT" },
            new String[] {"DOOR OPENING HEIGHT(IN.)", "ELEV_DOOR_HGT" },
            new String[] {"DOOR OPENING WIDTH (IN.)", "ELEV_DOOR_WDT" },
            // VALIDATION
            new String[] {"CUSTOMER INFORMED ABOUT B/O", "CUST_INFO_BO_FLG" },
            new String[] {"PICKUP EXTRA TONER", "PICK_UP_XTR_TONER_FLG" },
            new String[] {"PICKUP AT SAME TIME", "PICK_UP_AT_SAME_TM_FLG" },
        };

        String rqstDt = (String) rwsSiteSurvey.get("reqDtTmTsTxt");
        String ctryCd = (String) rwsSiteSurvey.get("ctryCd");
        String postCd = (String) rwsSiteSurvey.get("postCd");

        for (String[] nameAndKey : nameAndKeys) {
            NLBI1510_04TMsg tMsgRwsSiteSurvey = new NLBI1510_04TMsg();
            String name = nameAndKey[0];
            String key = nameAndKey[1];
            Object val = rwsSiteSurvey.get(key);
            String value = null;

            if (val == null) {
                value = "";
            } else if (val instanceof BigDecimal) {
                value = ((BigDecimal) val).toPlainString();
            } else {
                value = val.toString();
                // START 2023/12/01 G.Quan [QC#62451, ADD]
                if (ZYPCommonFunc.hasValue(value)) {
                    value = value.replaceAll("\r\n", " ");
                }
                // END 2023/12/01 G.Quan [QC#62451, ADD]
            }

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.wmsRecId, VAL_08);

            if (ZYPCommonFunc.hasValue(value)) {

                if ("DELIVERY HOURS FROM".equals(name)
                 || "DELIVERY HOURS TO".equals(name)
                 || "TIMESTOP".equals(name)
                 || "ELEVATOR HOURS FROM".equals(name)
                 || "ELEVATOR HOURS TO".equals(name)) {

                    value = convertAMPM(convertTime(rqstDt, value, ctryCd, postCd));
                    ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                } else {

                    ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name);
            }

            // insert
            EZDTBLAccessor.insert(tMsgRwsSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsSiteSurvey.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_04, tMsgRwsSiteSurvey.getTableName(), keyFieldName, keyValue });

                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_04, tMsgRwsSiteSurvey.getTableName(), keyFieldName, keyValue });
            }
        }

        return seqNumber;
    }

    // Get RWS data(Site Survey)
    private List<Map<String, Object>> getRwsMdseSurvey(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsMdseSurvey", bindParam, execParam);
    }

    private int createRwsMdseSurvey(List<Map<String, Object>> rwsMdseSurveyList
                                     , BigDecimal regTrxId
                                     , BigDecimal regUnitId
                                     , int regSeqNumber) {

        for (Map<String, Object> rwsMdseSurvey : rwsMdseSurveyList) {
            regSeqNumber = createRwsMdseSurvey(rwsMdseSurvey, regTrxId, regUnitId, regSeqNumber);
        }
        return regSeqNumber;
    }

    private int createRwsMdseSurvey(Map<String, Object> rwsMdseSurvey
                                     , BigDecimal regTrxId
                                     , BigDecimal regUnitId
                                     , int regSeqNumber) {

        final String[][] nameAndKeys = new String[][] {
            // MATELIAL WEIGHTS AND DIMENSIONS INFORMATION
            new String[] {"ITEM CODE", "MDSE_CD" },
            new String[] {"ITEM DESCRIPTION", "MDSE_NM" },
            new String[] {"CT WT", "IN_POUND_WT" },
            new String[] {"CR LEN", "IN_INCH_LG" },
            new String[] {"CR WDT", "IN_INCH_WDT" },
            new String[] {"CR HT", "IN_INCH_HGT" },
            new String[] {"CR DG", "IN_INCH_DG" },
        };

        for (String[] nameAndKey : nameAndKeys) {
            NLBI1510_04TMsg tMsgRwsSiteSurvey = new NLBI1510_04TMsg();
            String name = nameAndKey[0];
            String key = nameAndKey[1];
            Object val = rwsMdseSurvey.get(key);
            String value = null;

            if (val == null) {
                continue;
            } else if (val instanceof BigDecimal) {
                value = ((BigDecimal) val).toPlainString();
            } else {
                value = val.toString();
            }

            if (!ZYPCommonFunc.hasValue(value)) {
                continue;
            }

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.wmsRecId, VAL_08);

            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);

            // insert
            EZDTBLAccessor.insert(tMsgRwsSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsSiteSurvey.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_04, tMsgRwsSiteSurvey.getTableName(), keyFieldName, keyValue });
                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_04, tMsgRwsSiteSurvey.getTableName(), keyFieldName, keyValue });
            }
        }

        return regSeqNumber;
    }

    // Get RWS data (Serial No)
    private List<Map<String, Object>> getRwsSerial(String rwsNum, String rwsLineNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(COL_RWS_NUM, rwsNum);
        bindParam.put(COL_RWS_LINE_NUM, rwsLineNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsSerial", bindParam, execParam);
    }

    private int createRwsSerial(List<Map<String, Object>> rwsSerialList
                                 , BigDecimal trxId
                                 , BigDecimal unitId
                                 , int seqNumber) {

        for (Map<String, Object> rwsSerial : rwsSerialList) {
            NLBI1510_05TMsg tMsgRwsSerial = new NLBI1510_05TMsg();

            ++seqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.seqNumber, new BigDecimal(seqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.wmsRecId, VAL_09);

            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.tplSerNum, (String) rwsSerial.get(COL_SER_NUM));

            // insert
            EZDTBLAccessor.insert(tMsgRwsSerial);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsSerial.getReturnCode())) {
                String keyFieldName = NLXCMsgHelper.toListedString(COL_INTERFACE_ID, COL_TRANSACTION_ID, COL_UNIT_ID);
                String keyValue = NLXCMsgHelper.toListedString(this.interfaceId, trxId, unitId);

                outputErr(NLGM0045E, new String[] {NLBI1510_05, tMsgRwsSerial.getTableName(), keyFieldName, keyValue });
                throw new S21AbendException(NLGM0045E, new String[] {NLBI1510_05, tMsgRwsSerial.getTableName(), keyFieldName, keyValue });
            }
        }

        return seqNumber;
    }

    /**
     * Create and Insert WMS_INBD_PO_HDR.
     * @param rwsHeader Map<String, Object>
     * @param rwsTextList List<Map<String, Object>>
     * @return WMS_INBD_PO_HDRTMsg
     */
    private WMS_INBD_PO_HDRTMsg createWmsInbdPoHdr(Map<String, Object> rwsHeader, List<Map<String, Object>> rwsTextList) {

        WMS_INBD_PO_HDRTMsg hdrT = new WMS_INBD_PO_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(hdrT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hdrT.whCd, (String) rwsHeader.get(COL_WH_CD));
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
        ZYPEZDItemValueSetter.setValue(hdrT.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(hdrT.intfcTpId, VAL_INTFC_TP_ID_01);
        ZYPEZDItemValueSetter.setValue(hdrT.intfcRecTpId, VAL_INTFC_REC_TP_ID_HDR);
        ZYPEZDItemValueSetter.setValue(hdrT.wmsCmpyCd, (String) rwsHeader.get(COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsPoId, (String) rwsHeader.get(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.vndCd, (String) rwsHeader.get(COL_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsPrchTpCd, (String) rwsHeader.get(COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsTrxCd, (String) rwsHeader.get(COL_S80_TRX_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.poFromDtTmTs, (String) rwsHeader.get(COL_WH_IN_ETA_DT) + VAL_000000);
        ZYPEZDItemValueSetter.setValue(hdrT.printSwthCd, VAL_PRINT_SWTH_CD_P);
        ZYPEZDItemValueSetter.setValue(hdrT.wmsVeslNm, (String) rwsHeader.get(COL_IMPT_INV_VESL_NM));
        ZYPEZDItemValueSetter.setValue(hdrT.wmsBolNum, (String) rwsHeader.get(COL_IMPT_INV_BOL_NUM));

        if (rwsTextList != null && rwsTextList.size() > 0) {
            // Repeat the split registration
            for (int i = 0; i < rwsTextList.size(); i++) {
                switch (i) {
                    case 0:
                        ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_01, (String) rwsTextList.get(i).get(COL_RWS_MSG_TXT));
                    break;
                    case 1:
                        ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_02, (String) rwsTextList.get(i).get(COL_RWS_MSG_TXT));
                    break;
                    case 2:
                        ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_03, (String) rwsTextList.get(i).get(COL_RWS_MSG_TXT));
                    break;
                    case 3:
                        ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_04, (String) rwsTextList.get(i).get(COL_RWS_MSG_TXT));
                    break;
                    default:
                        break;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(hdrT.rtlWhCd, (String) rwsHeader.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(hdrT.rwsNum, (String) rwsHeader.get(COL_RWS_NUM));
        ZYPEZDItemValueSetter.setValue(hdrT.svcConfigMstrPk, (BigDecimal) rwsHeader.get(COL_SVC_CONFIG_MSTR_PK));


        if (!isWmsInbdPoUpdate(hdrT.wmsPoId.getValue())) {
            ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, STAGE_ACT_NEW);
        } else {
            ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, STAGE_ACT_UPDATE);
        }

        ZYPEZDItemValueSetter.setValue(hdrT.stageRecStsCd, VAL_2);

        EZDTBLAccessor.insert(hdrT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrT.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM);
            String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, (String) rwsHeader.get(COL_RWS_NUM));

            throw new S21AbendException(NLGM0045E, new String[] {hdrT.getTableName(), RWS_HDR, keyFieldName, keyValue });
        }
        return hdrT;
    }

    /**
     * isWmsInbdPoUpdate
     * @param  wmsPoId, String
     * @return Map<String, Object>
     */
    private boolean isWmsInbdPoUpdate(String wmsPoId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wmsPoId", wmsPoId);

        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countWmsInbdPo", ssmParam);

        return (count.compareTo(BigDecimal.ZERO) > 0);
    }

    /**
     * Create WMS_INBD_PO_VND.
     * @param rwsHeader Map<String, Object>
     * @param wmsSqNum String
     */
    private void createWmsInbdPoVnd(Map<String, Object> rwsHeader, BigDecimal wmsSqNum) {
        WMS_INBD_PO_VNDTMsg vndT = new WMS_INBD_PO_VNDTMsg();

        ZYPEZDItemValueSetter.setValue(vndT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndT.whCd, (String) rwsHeader.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(vndT.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(vndT.intfcTpId, VAL_INTFC_TP_ID_01);
        ZYPEZDItemValueSetter.setValue(vndT.intfcRecTpId, VAL_INTFC_REC_TP_ID_VND);
        ZYPEZDItemValueSetter.setValue(vndT.wmsCmpyCd, (String) rwsHeader.get(COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(vndT.wmsPoId, (String) rwsHeader.get(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(vndT.vndCd, (String) rwsHeader.get(COL_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(vndT.wmsVndNm_01, adjustString((String) rwsHeader.get(COL_FROM_LOC_NM_01), LENGTH_FROM_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndT.wmsVndNm_02, adjustString((String) rwsHeader.get(COL_FROM_LOC_NM_02), LENGTH_FROM_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndT.firstLineAddr, (String) rwsHeader.get(COL_FROM_LOC_ADDR_01));
        ZYPEZDItemValueSetter.setValue(vndT.scdLineAddr, (String) rwsHeader.get(COL_FROM_LOC_ADDR_02));
        ZYPEZDItemValueSetter.setValue(vndT.thirdLineAddr, (String) rwsHeader.get(COL_FROM_LOC_ADDR_03));
        ZYPEZDItemValueSetter.setValue(vndT.frthLineAddr, (String) rwsHeader.get(COL_FROM_LOC_ADDR_04));
        ZYPEZDItemValueSetter.setValue(vndT.ctyAddr, (String) rwsHeader.get(COL_FROM_LOC_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(vndT.stCd, (String) rwsHeader.get(COL_FROM_LOC_ST_CD));
        ZYPEZDItemValueSetter.setValue(vndT.postCd, (String) rwsHeader.get(COL_FROM_LOC_POST_CD));
        ZYPEZDItemValueSetter.setValue(vndT.ctryCd, (String) rwsHeader.get(COL_FROM_LOC_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(vndT.vndToCtacNm, (String) rwsHeader.get(COL_FROM_LOC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(vndT.vndToCtacNum, (String) rwsHeader.get(COL_FROM_LOC_TEL_NUM));

        EZDTBLAccessor.insert(vndT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndT.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM);
            String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, (String) rwsHeader.get(COL_RWS_NUM));

            throw new S21AbendException(NLGM0045E, new String[] {vndT.getTableName(), RWS_HDR, keyFieldName, keyValue });
        }

    }

    /**
     * Create WMS_INBD_PO_DTL.
     * @param hdrT WMS_INBD_PO_HDRTMsg
     * @param soDetail Map<String, Object>
     * @return true/false boolean
     */
    private boolean createWmsInbdPoDtl(WMS_INBD_PO_HDRTMsg hdrT, Map<String, Object> rwsDtl) {
        WMS_INBD_PO_DTLTMsg dtlT = new WMS_INBD_PO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, new BigDecimal((String) rwsDtl.get(COL_RWS_DTL_LINE_NUM)));
        ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, VAL_INTFC_TP_ID_01);
        ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, VAL_INTFC_REC_TP_ID_DTL);
        ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsPoId, hdrT.wmsPoId.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) rwsDtl.get(COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) rwsDtl.get(COL_S80_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsInvInd, VAL_WMS_INV_IND_S);
        ZYPEZDItemValueSetter.setValue(dtlT.wmsOpenQty, (BigDecimal) rwsDtl.get(COL_RWS_QTY));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsEstDtTmTs, (String) rwsDtl.get(COL_WH_IN_ETA_DT) + VAL_000000);
        ZYPEZDItemValueSetter.setValue(dtlT.wmsInvId, (String) rwsDtl.get(COL_IMPT_INV_NUM));
        ZYPEZDItemValueSetter.setValue(dtlT.wmsDoId, (String) rwsDtl.get(COL_IMPT_INV_DO_NUM));
        ZYPEZDItemValueSetter.setValue(dtlT.cseFromNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_FROM_CSE_NUM));
        ZYPEZDItemValueSetter.setValue(dtlT.cseToNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_TO_CSE_NUM));
        ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, hdrT.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) rwsDtl.get(COL_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.usrCdRefTxt, (String) rwsDtl.get(COL_THIRD_PTY_DSP_TP_CD));
        ZYPEZDItemValueSetter.setValue(dtlT.serApvlReqFlg, (String) rwsDtl.get(COL_SER_APVL_REQ_FLG));

        EZDTBLAccessor.insert(dtlT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlT.getReturnCode())) {
            String keyFieldName = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM);
            String keyValue = NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.rwsNum.getValue());

            throw new S21AbendException(NLGM0045E, new String[] {dtlT.getTableName(), RWS_HDR, keyFieldName, keyValue });
        }

        return true;
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

    /**
     * convCustomerTime
     * @param dtTmts Request Date Time (yyyyMMddHHmmssSSS)
     * @param ctryCd CTRY_CD SO : SHIP_TO / RWS : FROM_LOC
     * @param postCd POST_CD SO : SHIP_TO / RWS : FROM_LOC
     * @return
     */
    private String convCustomerTime(String dtTmts, String ctryCd, String postCd) {

        if (ZYPCommonFunc.hasValue(dtTmts) && ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
            SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, dtTmts, ctryCd, postCd);
            return timeInfo.getDateTime();
        }

        return dtTmts;
    }

    /**
     * convertTime
     * @param rqstDt Request Date Time (yyyyMMdd)
     * @param time String
     * @param ctryCd CTRY_CD SO : SHIP_TO / RWS : FROM_LOC
     * @param postCd POST_CD SO : SHIP_TO / RWS : FROM_LOC
     * @return String
     */
    public static String convertTime(String rqstDt, String time, String ctryCd, String postCd) {

        if (ZYPCommonFunc.hasValue(rqstDt) && ZYPCommonFunc.hasValue(time)) {

            String convertTime = formatHHmm(time);
            String convertTimeStamp = rqstDt + convertTime;

            if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                return convertTimeStamp(ctryCd, postCd, convertTimeStamp).substring(8, 12);
            } else {
                return convertTime;
            }
        } else {
            return time;
        }
    }

    /**
     * formatHHmm
     * @param hhmm String
     * @return String
     */
    public static String formatHHmm(String hhmm) {
        if (hhmm.length() < 4) {
            hhmm = "0" + hhmm;
        }
        return hhmm;
    }

    /**
     * convertTimeStamp
     * @param ctryCd String
     * @param postCd String
     * @param timeStamp String
     * @return local time
     */
    public static String convertTimeStamp(String ctryCd, String postCd, String timeStamp) {
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, timeStamp, ctryCd, postCd);
        return timeInfo.getDateTime();
    }

    /**
     * convertAMPM
     * @param value String
     * @return String
     */
    public static String convertAMPM(String value) {

        String convertTime = null;
        if (ZYPCommonFunc.hasValue(value) && ZYPCommonFunc.isNumberCheck(value)) {

            String hh = null;
            String mm = null;
            int hour = 0;

            if (value.length() == 4) {

                hh = value.substring(0, 2);
                mm = value.substring(2);
                hour = Integer.valueOf(hh);

            } else if (value.length() == 3) {

                value = "0" + value;
                hh = value.substring(0, 2);
                mm = value.substring(2);
                hour = Integer.valueOf(hh);

            } else if (value.length() == 6) {

                hh = value.substring(0, 2);
                mm = value.substring(2, 4);
                hour = Integer.valueOf(hh);

            } else {

                return value;
            }

            if (12 <= hour) {
                hour -= 12;
                if (hour == 0) {
                    hour = 12;
                }
                convertTime = ZYPCommonFunc.concatString(String.format("%1$02d", hour), mm, " PM");

            } else {
                if (hour == 0) {
                    hour = 12;
                }
                convertTime = ZYPCommonFunc.concatString(String.format("%1$02d", hour), mm, " AM");
            }
        }

        return convertTime;
    }

    /**
     * Output Error Message.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(KEY_MESSAGE_ID, msgId);
        mailParam.put(KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * sendErrorMail
     */
    private void sendErrorMail() {

        S21MailGroup mailGrpFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);
        mailGrpFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> fromAddrList = mailGrpFrom.getMailAddress();
        S21MailAddress fromAddress = null;
        if (fromAddrList != null && fromAddrList.size() > 0) {
            fromAddress = fromAddrList.get(0);
        }

        S21MailGroup mailGrpTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);
        mailGrpTo.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> toAddrList = mailGrpTo.getMailAddress();

        S21MailTemplate mailTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        mailTemplate.setTemplateParameter(MAIL_KEY_BATCH_ID, BUSINESS_ID);
        String errorDate = ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS);
        mailTemplate.setTemplateParameter(MAIL_KEY_ERR_DATE, errorDate);
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> errMsg : errMsgList) {
            String msgId = errMsg.get(KEY_MESSAGE_ID);
            String msg = errMsg.get(KEY_MESSAGE);
            sb.append(msgId).append("    ").append(msg).append("\r\n");
        }
        mailTemplate.setTemplateParameter(MAIL_KEY_MESSAGE, sb.toString());

        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddrList);
        mail.setMailTemplate(mailTemplate);

        // Send immediately
        // mail.sendMail();
        // Queue mail request into ML_SEND
        mail.postMail();
    }
}
