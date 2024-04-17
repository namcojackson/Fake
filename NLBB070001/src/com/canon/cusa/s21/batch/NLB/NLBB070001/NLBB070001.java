/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB070001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.NLBI1210_01TMsg;
import business.db.NLBI1210_02TMsg;
import business.db.NLBI1210_03TMsg;
import business.db.NLBI1210_04TMsg;
import business.db.NLBI1210_05TMsg;
import business.db.PRCH_REQTMsg;
import business.db.RWS_HDRTMsg;
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

import com.canon.cusa.s21.batch.NLB.NLBB070001.constant.NLBB070001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
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
 * Batch Program Class for SO (EDI 940) Download to APEX
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/15/2016   CITS            T.Wada          Create
 * 07/03/2017   CITS            Y.Imazu         Update          QC#19720
 * 09/07/2017   CITS            K.Ogino         Update          QC#19887
 * 09/29/2017   CITS            T.Wada          Update          QC#21283
 * 10/24/2017   CITS           T.Tokutomi       Update          QC#21657-1
 * 10/27/2017   CITS            T.Wada          Update          QC#22134
 * 10/30/2017   CITS            Y.Iwasaki       Update          QC#21281
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 01/11/2018   CITS            T.Wada          Update          QC#18460
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 * 06/05/2018   CITS            K.Ogino         Update          QC#25233
 * 06/28/2018   CITS            K.Ogino         Update          QC#26824
 * 01/23/2019   CITS            R.Shimamoto     Update          QC#30024
 * 04/22/2019   CITS            K.Ogino         Update          QC#31196
 * 05/14/2019   CTIS            K.Ogino         Update          QC#50027
 * 07/05/2019   CITS            K.Ogino         Update          QC#50965
 * 08/06/2019   CITS            R.Shimamoto     Update          QC#52369
 * 09/05/2019   CITS            R.Shimamoto     Update          QC#53305
 * 11/29/2019   Fujitsu         R.Nakamura      Update          QC#54389
 *</pre>
 */
public class NLBB070001 extends S21BatchMain {

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

    /** Sales Date */
    private String salesDate = null;

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NLBB070001().executeBatch(NLBB070001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        trxAccess = new S21TransactionTableAccessor();

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

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
        
        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {

        // Create Interface data and Transaction table.
        try {
            createInterface();
        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB070001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {

        String[] msg = new String[] {interfaceId, "Insert", Integer.toString(successCount) };

        // The number of cases : Insert is output
        S21InfoLogOutput.println(NLBB070001Constant.ZZBM0009I, msg);

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
            throw new S21AbendException(NLBB070001Constant.NLGM0049E, new String[] {MSG_STR_COMP_CODE });
        }

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB070001Constant.NLGM0049E, new String[] {MSG_STR_INTERFACE_ID });
        }

        // Commit Count
        String str = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(NLBB070001Constant.NLGM0049E, new String[] {MSG_STR_PARAM_01 });
        }
        try {
            commitCount = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new S21AbendException(NLBB070001Constant.ZZM9004E, new String[] {"Commit Count(" + str + ")" });
        }
    }

    /**
     * <pre>
     * xxx
     * </pre>
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void createInterface() {

        // Get Transaction ID.
        BigDecimal trxId = trxAccess.getNextTransactionId();
        int unitId = 0;

        // Create SO Interface data.
        unitId = createSoInterface(trxId, unitId);

        // Create Transaction table.
        if (unitId > 0) {
            trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);
        }
        commit();
        
        // Get Transaction ID.
        trxId = trxAccess.getNextTransactionId();
        unitId = 0;
        
        // Create RWS Interface data.
        unitId = createRwsInterface(trxId, unitId);

        // Create Transaction table.
        if (unitId > 0) {
            trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);
        }
        commit();
    }

    /**
     * <pre>
     * createSoInterface
     * </pre>
     * @param regTrxId TransactionId.
     * @param execParam SSM parameter.
     */
    private int createSoInterface(BigDecimal regTrxId, int unitId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Get target SO_NUMs
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            bindParam.put(NLBB070001Constant.COL_WH_OWNR_CD, whOwnrCd);
            bindParam.put(NLBB070001Constant.COL_CARR_TP_NM, carrTpCd);
            bindParam.put("salesDate", this.salesDate);

            stmt = ssmLLClient.createPreparedStatement("findSo", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop by SO#
            int processedCount = 0;
            BigDecimal wmsSqNum = null;
            while (rs.next()) {

                ++unitId;
                BigDecimal regUnitId = new BigDecimal(unitId);
                int regSeqNumber = 0;

                // SO_NUM
                String soNum = rs.getString(NLBB070001Constant.COL_SO_NUM);

                // TPL_CARR_SVC_LVL
                Map<String, Object> tplCarrSvcLvl = new HashMap<String, Object>();
                String carrCd = rs.getString(NLBB070001Constant.COL_CARR_CD);
                String shpgSvcLvlCd = rs.getString(NLBB070001Constant.COL_SHPG_SVC_LVL_CD);

                tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(carrCd, shpgSvcLvlCd);

                if (tplCarrSvcLvl != null) {
                    if (!ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_CD))) {
                        tplCarrSvcLvl.put(NLBB070001Constant.COL_TPL_CARR_CD, NLBB070001Constant.VAL_CONST_CARR_CUST_TXT);
                    }
                    if (!ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_NM))) {
                        tplCarrSvcLvl.put(NLBB070001Constant.COL_TPL_CARR_NM, NLBB070001Constant.VAL_CONST_CARR_NM_01);
                    }
                } else {
                    tplCarrSvcLvl = new HashMap<String, Object>();
                    tplCarrSvcLvl.put(NLBB070001Constant.COL_TPL_CARR_CD, NLBB070001Constant.VAL_CONST_CARR_CUST_TXT);
                    tplCarrSvcLvl.put(NLBB070001Constant.COL_TPL_CARR_NM, NLBB070001Constant.VAL_CONST_CARR_NM_01);
                }
                // QC#25233
                Map<String, String> ctacPsnInfo = new HashMap<String, String>();
                // QC#52369
                Map<String, String> customerInfo = new HashMap<String, String>();
                regSeqNumber = createSoHeader(rs, tplCarrSvcLvl, regTrxId, regUnitId, regSeqNumber, ctacPsnInfo, customerInfo);
                // QC#26824
                // COL_HIT_BY_WMS_DROP_FLG = 1 : From WH Apex
                // COL_HIT_BY_WMS_DROP_FLG = 2 : From WH Apex and Carrier Apex
                // COL_HIT_BY_WMS_DROP_FLG = 0 : Other
                if (ZYPConstant.FLG_ON_1.equals(rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG)) //
                        || "2".equals(rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
                    // (WMS) Get 
                    wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
                    // WMS_INBD_SO_HDR
                    createWmsInbdSoHdr(rs, tplCarrSvcLvl, regTrxId, regUnitId, wmsSqNum);
                    // WMS_INBD_SO_SHIP_TO,WMS_INBD_SO_CHRG_TO,WMS_INBD_SO_BILL_TO
                    List<Map<String, Object>> wmsInbdSoToCustList = getWmsSoToCust(soNum);
                    if (!wmsInbdSoToCustList.isEmpty()) {
                        createWmsInbdSoShipTo(rs , wmsInbdSoToCustList.get(0) , regTrxId, regUnitId, wmsSqNum);
                        createWmsInbdSoChrgTo(rs , wmsInbdSoToCustList.get(0) , regTrxId, regUnitId, wmsSqNum);
                        createWmsInbdSoBillTo(rs , wmsInbdSoToCustList.get(0) , regTrxId, regUnitId, wmsSqNum);
                    }
                    // WMS_INBD_SO_TEXT
                    List<Map<String, Object>> wmsInbdSoTextList = getWmsInbdSoText(soNum);
                    if (!wmsInbdSoTextList.isEmpty()) {
                        createWmsInbdSoText(rs, wmsInbdSoTextList, regTrxId, regUnitId, wmsSqNum);
                    }
                }

                // SO Text
                List<Map<String, Object>> soTextList = getSoText(soNum);
                regSeqNumber = createSoText(soTextList, regTrxId, regUnitId, regSeqNumber);

                // SO Site Survey
                Map<String, Object> soSiteSurvey = getSoSiteSurvey(soNum);
                // QC#25233
                if (ctacPsnInfo != null) {
                    soSiteSurvey.put("CONTACT", ctacPsnInfo.get("CONTACT"));
                    soSiteSurvey.put("PHONE", ctacPsnInfo.get("PHONE"));
                }

                // QC#52369 customerInfo
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

                    // SO_SLP_NUM
                    String soSlpNum = (String) soDetail.get(NLBB070001Constant.COL_SO_SLP_NUM);

                    regSeqNumber = createSoDetail(soDetail, regTrxId, regUnitId, regSeqNumber);
                    // QC#26824
                    if (ZYPConstant.FLG_ON_1.equals(rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG)) //
                            || "2".equals(rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
                        // (WMS) WMS_INBD_SO_DTL
                        createWmsInbdSoDetail(rs , soDetail , regTrxId, regUnitId, wmsSqNum);
                    }
                    
                    // SO Serial. Mod QC#50965
                    BigDecimal shpgQty = (BigDecimal) soDetail.get("SHPG_QTY");
                    List<Map<String, Object>> soSerialList = getSoSerial(soNum, soSlpNum, shpgQty);

                    regSeqNumber = createSoSerial(soSerialList, regTrxId, regUnitId, regSeqNumber);

                    if (soSiteSurvey != null) {
                        regSeqNumber = createSoSiteSurvey(soSiteSurvey, regTrxId, regUnitId, regSeqNumber);
                        soSiteSurvey = null;
                    }

                    if (soMdseSurveyList != null) {
                        regSeqNumber = createSoMdseSurvey(soMdseSurveyList, regTrxId, regUnitId, regSeqNumber);
                        soMdseSurveyList = null;
                    }
                }
                // Update of WMS send status
                updateSoWmsStstusFlg(soNum ,rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG), rs.getString(NLBB070001Constant.COL_HIT_BY_DELY_REQ_FLG));

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

        return unitId;
    }

    // Mod QC#52369
    private int createSoHeader(ResultSet soHeader, Map<String, Object> tplCarrSvcLvl, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber, Map<String, String> ctacPsnInfo, Map<String, String> customerInfo) throws SQLException {

        // Set to TMsg(NLBI1210_01)
        NLBI1210_01TMsg tMsgSoHeader = new NLBI1210_01TMsg();

        ++regSeqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.transactionId, regTrxId);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.unitId, regUnitId);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.seqNumber, new BigDecimal(regSeqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsRecId, "01");

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordIdTxt, soHeader.getString(NLBB070001Constant.COL_SO_NUM));

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplLocTxt, getShpgPntCd(soHeader.getString(NLBB070001Constant.COL_WH_CD)));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplOrgTxt, "XX");

        BigDecimal totShipWt = this.getRoundMax(soHeader.getBigDecimal(NLBB070001Constant.COL_TOT_SHPG_WT), 3, new BigDecimal("999999999.999"));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsTotGrsWt, totShipWt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsTotWt, totShipWt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsWtUomCd, "LBR");

        BigDecimal totShpgVol = this.getRoundMax(soHeader.getBigDecimal(NLBB070001Constant.COL_TOT_SHPG_VOL), 1, new BigDecimal("99999999999.9"));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsTotVol, totShpgVol);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsVolUomCd, "INQ");

        // QC#52369 Convert Customer Timezone
        String delyDt = "";
        String reqDtTmTsTxt = "";
        if (ZYPCommonFunc.hasValue(soHeader.getString(NLBB070001Constant.COL_SCHD_DELY_TM)) && !"0000".equals(soHeader.getString(NLBB070001Constant.COL_SCHD_DELY_TM))) {
            reqDtTmTsTxt = soHeader.getString(NLBB070001Constant.COL_DELY_DT) + soHeader.getString(NLBB070001Constant.COL_SCHD_DELY_TM);
        } else {
            reqDtTmTsTxt = soHeader.getString(NLBB070001Constant.COL_DELY_DT) + "0000";
        }
        if (ZYPCommonFunc.hasValue(reqDtTmTsTxt)) {
            if (reqDtTmTsTxt.length() == NLBB070001Constant.VAL_DATE_FORMAT_YYYYMMDDHHMM.length()) {
                reqDtTmTsTxt = ZYPDateUtil.DateFormatter(reqDtTmTsTxt, NLBB070001Constant.VAL_DATE_FORMAT_YYYYMMDDHHMM, NLBB070001Constant.FMT_YYYYMMDDHHMMSSSSS);
            } else {
                reqDtTmTsTxt = ZYPDateUtil.DateFormatter(reqDtTmTsTxt, NLBB070001Constant.VAL_DATE_FORMAT_FROM, NLBB070001Constant.FMT_YYYYMMDDHHMMSSSSS);
            }
            if (ZYPCommonFunc.hasValue(soHeader.getString(NLBB070001Constant.COL_SCHD_DELY_TM))) {
                reqDtTmTsTxt = convCustomerTime(reqDtTmTsTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_CTRY_TXT), soHeader.getString(NLBB070001Constant.COL_SHIP_ZIP_OR_POST_CD_TXT));
            }
            delyDt = reqDtTmTsTxt.substring(0, 8);
        } else {
            delyDt = soHeader.getString(NLBB070001Constant.COL_DELY_DT);
        }

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsHdrDelyDtTxt, delyDt);

        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ordTpTxt, "LF");
        // SHIP_TO_CUST
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplCtrlId, soHeader.getString(NLBB070001Constant.COL_TPL_CTRL_ID));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCustTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipNmTxt_01, this.getSubstring(soHeader.getString(NLBB070001Constant.COL_SHIP_NM_TXT_01),35));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipNmTxt_02, this.getSubstring(soHeader.getString(NLBB070001Constant.COL_SHIP_NM_TXT_02),35));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipFirstLineAddrTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_FIRST_LINE_ADDR_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipScdLineAddrTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_SCD_LINE_ADDR_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipZipOrPostCdTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_ZIP_OR_POST_CD_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtyTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_CTY_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtryTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_CTRY_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipStOrProvTxt, soHeader.getString(NLBB070001Constant.COL_SHIP_ST_OR_PROV_TXT));

        // QC#21657-1 Modify Start.
        String soNum = soHeader.getString(NLBB070001Constant.COL_SO_NUM);
        SHPG_ORDTMsg shpgOrdT = getShpgOrd(glblCmpyCd, soNum);
        if (shpgOrdT != null && SCE_ORD_TP.TECH_REQUEST.equals(shpgOrdT.sceOrdTpCd.getValue())) {
            PRCH_REQTMsg prchReqT = getPrchReq(glblCmpyCd, shpgOrdT.srcOrdNum.getValue());

            if (prchReqT != null && ZYPCommonFunc.hasValue(prchReqT.shipToCustCd)) {
                // [Drop Ship] Update Ship Address.
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipNmTxt_01, this.getSubstring(prchReqT.shipToLocNm.getValue(), 35));
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipNmTxt_02, this.getSubstring(prchReqT.shipToAddlLocNm.getValue(), 35));

                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipFirstLineAddrTxt, prchReqT.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipScdLineAddrTxt, prchReqT.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipZipOrPostCdTxt, prchReqT.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtyTxt, prchReqT.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipCtryTxt, prchReqT.shipToCtryCd);
                ZYPEZDItemValueSetter.setValue(tMsgSoHeader.shipStOrProvTxt, prchReqT.shipToStCd);
            }
        }
        // QC#21657-1 Modify End.

        // BILL_TO_CUST
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billCustTxt, soHeader.getString(NLBB070001Constant.COL_BILL_CUST_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billNmTxt_01, this.getSubstring(soHeader.getString(NLBB070001Constant.COL_BILL_NM_TXT_01),35));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billNmTxt_02, this.getSubstring(soHeader.getString(NLBB070001Constant.COL_BILL_NM_TXT_02),35));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billFirstLineAddrTxt, soHeader.getString(NLBB070001Constant.COL_BILL_FIRST_LINE_ADDR_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billScdLineAddrTxt, soHeader.getString(NLBB070001Constant.COL_BILL_SCD_LINE_ADDR_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billZipOrPostCdTxt, soHeader.getString(NLBB070001Constant.COL_BILL_ZIP_OR_POST_CD_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billCtyTxt, soHeader.getString(NLBB070001Constant.COL_BILL_CTY_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billCtryTxt, soHeader.getString(NLBB070001Constant.COL_BILL_CTRY_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.billStOrProvTxt, soHeader.getString(NLBB070001Constant.COL_BILL_ST_OR_PROV_TXT));

        // CTAC_CUST
        // QC#31196 Mod. CTAC_CUST_TXT:Blank. CTAC_CUST_NM_01:CTAC_PTNR_PSN_NM
        tMsgSoHeader.ctacCustTxt.clear();

        String ctacPsnNm = soHeader.getString(NLBB070001Constant.COL_SHIP_TO_CTAC_PSN_NM);
        if (ZYPCommonFunc.hasValue(ctacPsnNm) && ctacPsnNm.length() > NLBB070001Constant.LG_CUT_CTAC_CUST_NM_01_MAX_SIZE) {
            ctacPsnNm = ctacPsnNm.substring(0, NLBB070001Constant.LG_CUT_CTAC_CUST_NM_01_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ctacCustNm_01, ctacPsnNm);
        // QC#31196 Mod End.

        String ctacPhoNum = soHeader.getString(NLBB070001Constant.COL_CTAC_PTNR_PSN_TEL_NUM);
        if (ZYPCommonFunc.hasValue(ctacPhoNum) && ctacPhoNum.length() > NLBB070001Constant.LG_CUT_CTAC_PHO_NUM_MAX_SIZE) {
            ctacPhoNum = ctacPhoNum.substring(0, NLBB070001Constant.LG_CUT_CTAC_PHO_NUM_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.ctacPhoNum, ctacPhoNum);
        // QC#25233
        ctacPsnInfo.put("CONTACT", ctacPsnNm);
        ctacPsnInfo.put("PHONE", ctacPhoNum);

        // CARR_CD CARR_NM
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.carrCustTxt, (String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.carrNm_01, (String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_NM));

        // WMS_REQ_DT_TXT...WMS_FNSH_DELY_DT_TXT
        String psdDt = soHeader.getString(NLBB070001Constant.COL_PSD_DT);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsReqDtTxt, delyDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsFnshReqDtTxt, delyDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsActDtTxt, delyDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsFnshActDtTxt, delyDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsOrdDtTxt, psdDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsFnshOrdDtTxt, psdDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsDelyDtTxt, delyDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsFnshDelyDtTxt, delyDt);
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplSrcOrdNum, soHeader.getString(NLBB070001Constant.COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgSoHeader.tplCustOrdNum, this.getSubstring(soHeader.getString(NLBB070001Constant.COL_CUST_ISS_PO_NUM), 20));

        // QC#18460
        // QC#52369 Mod
//        String delyTm = soHeader.getString(NLBB070001Constant.COL_SCHD_DELY_TM);
        String delyTm = "";
        if (ZYPCommonFunc.hasValue(reqDtTmTsTxt) && ZYPCommonFunc.hasValue(soHeader.getString(NLBB070001Constant.COL_SCHD_DELY_TM))) {
        	delyTm = reqDtTmTsTxt.substring(8, 12);
        }

        if (ZYPCommonFunc.hasValue(delyTm)) {
            ZYPEZDItemValueSetter.setValue(tMsgSoHeader.wmsDelyTs, delyTm);
        }

        // QC#52369 customerInfo
        if (ZYPCommonFunc.hasValue(reqDtTmTsTxt)) {
            customerInfo.put("reqDtTmTsTxt", reqDtTmTsTxt.substring(0, 8));
        } else {
            customerInfo.put("reqDtTmTsTxt", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgSoHeader.shipZipOrPostCdTxt)) {
            customerInfo.put("postCd", tMsgSoHeader.shipZipOrPostCdTxt.getValue());
        } else {
            customerInfo.put("postCd", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgSoHeader.shipCtryTxt)) {
            customerInfo.put("ctryCd", tMsgSoHeader.shipCtryTxt.getValue());
        } else {
            customerInfo.put("ctryCd", "");
        }

        // insert
        EZDTBLAccessor.insert(tMsgSoHeader);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoHeader.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_01, "SHPG_ORD",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_01, "SHPG_ORD",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }

        return regSeqNumber;
    }

    private void createWmsInbdSoHdr(ResultSet soHeader, Map<String, Object> tplCarrSvcLvl, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSeqNum) throws SQLException {

        // Set to TMsg(WMS_INBD_SO_HDR)
        WMS_INBD_SO_HDRTMsg tMsgWmsInbdSoHeader = new WMS_INBD_SO_HDRTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.glblCmpyCd, soHeader.getString(NLBB070001Constant.COL_GLBL_CMPY_CD));
        // QC#26824
        if ("2".equals(soHeader.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.whCd, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.rtlWhCd , soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.whCd, soHeader.getString(NLBB070001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.rtlWhCd , soHeader.getString(NLBB070001Constant.COL_WH_CD));
        }
        
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsSqNum, wmsSeqNum);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.intfcTpId, NLBB070001Constant.VAL_INTFC_TP_ID_02);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.intfcRecTpId, NLBB070001Constant.VAL_INTFC_REC_TP_ID_1);

        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.intfcRecActCd, " ");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsCmpyCd, soHeader.getString(NLBB070001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsSoId, soHeader.getString(NLBB070001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsOrdNum, soHeader.getString(NLBB070001Constant.COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.altDocNum, soHeader.getString(NLBB070001Constant.COL_PICK_TKT_NUM));

        String custIssPoNum = this.getSubstring(soHeader.getString(NLBB070001Constant.COL_CUST_ISS_PO_NUM),30);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.custOrdNum, custIssPoNum);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.chrgToCustCd, soHeader.getString(NLBB070001Constant.COL_BILL_CUST_TXT));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.billToCustCd, soHeader.getString(NLBB070001Constant.COL_BILL_TO_CUST_CD));

        String strDropShipFlug = soHeader.getString(NLBB070001Constant.COL_DROP_SHIP_FLG);
        if (ZYPCommonFunc.hasValue(strDropShipFlug) && ZYPConstant.FLG_ON_Y.equals(strDropShipFlug)) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.shipToCustCd, "999999");
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.shipToCustCd, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
        }
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsPrtyCd , "5");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsOrdTpCd, soHeader.getString(NLBB070001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsTrxCd, soHeader.getString(NLBB070001Constant.COL_S80_TRX_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsOrdSrcCd, soHeader.getString(NLBB070001Constant.COL_S80_ORD_SRC_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsSoStsCd , "R");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.soShipViaCd , soHeader.getString("SO_SHIP_VIA_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.shipViaDescTxt, soHeader.getString("SHIP_VIA_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.cratDtTmTs , soHeader.getString(NLBB070001Constant.COL_SO_CRAT_TS));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.estShipDtTmTs , ZYPCommonFunc.rightPad(soHeader.getString("PSD_DT")
                                                                                                , NLBB070001Constant.LG_DATE_COLUMN_SIZE
                                                                                                , "0"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsRqstDtTmTs , ZYPCommonFunc.rightPad(soHeader.getString("RDD_DT")
                                                                                                , NLBB070001Constant.LG_DATE_COLUMN_SIZE
                                                                                                , "0"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsPrintDtTmTs , soHeader.getString(NLBB070001Constant.COL_DNLD_TM_TS));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.frtOutCd , soHeader.getString(NLBB070001Constant.COL_S80_SHPG_TERM_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.frtOutDescTxt , soHeader.getString(NLBB070001Constant.COL_S80_SHPG_TERM_NM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsSoCarrCd ,soHeader.getString(NLBB070001Constant.COL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indOtmAddrSwthFlg , soHeader.getString(NLBB070001Constant.COL_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indScc14Flg , soHeader.getString(NLBB070001Constant.COL_PRINT_SCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indUccFlg , soHeader.getString(NLBB070001Constant.COL_PRINT_UCC_LB_FLG));   
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indMixedPltFlg , soHeader.getString(NLBB070001Constant.COL_MIX_PLT_ALLW_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indPltLbFlg , soHeader.getString(NLBB070001Constant.COL_PRINT_PLT_UCC_LB_FLG)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.sccNumFlg , ZYPConstant.FLG_OFF_N); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indNonAsnFlg , soHeader.getString(NLBB070001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indAsnFlg , soHeader.getString(NLBB070001Constant.COL_ASN_REQ_FLG)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indUccNumFlg , soHeader.getString(NLBB070001Constant.COL_UCC_NUM_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsLbNum , soHeader.getString(NLBB070001Constant.COL_EDI_TP_CD)); 

        String custStoreNum = soHeader.getString(NLBB070001Constant.COL_CUST_STORE_NUM);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.custStoreNum , custStoreNum); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.custDcNum , this.getSubstring(custStoreNum, 8)); 

        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsCustDeptNum , soHeader.getString(NLBB070001Constant.COL_SO_DEPT_NUM)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.wmsConslFlg , ZYPConstant.FLG_OFF_N); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.totShipPrcAmtNum , this.getRoundMax(soHeader.getBigDecimal(NLBB070001Constant.COL_TOT_SHIP_AMT), 2, new BigDecimal("9999999999999.99"))); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.totWtAmtNum , this.getRoundMax(soHeader.getBigDecimal(NLBB070001Constant.COL_TOT_SHPG_WT), 2, new BigDecimal("99999999999.9999"))); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.rtrnLbCd , soHeader.getString(NLBB070001Constant.COL_RTRN_LB_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indConfigFlg , soHeader.getString(NLBB070001Constant.COL_SO_CONFIG_FLG)); 

        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.asgShipViaCd , (String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.asgPrtyCd , (String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_SVC_LVL_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.indSgnReqFlg , ZYPConstant.FLG_OFF_N); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.billAcctNum , soHeader.getString(NLBB070001Constant.COL_CARR_ACCT_NUM)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.schdDelyDt , soHeader.getString(NLBB070001Constant.COL_DELY_DT)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.carrCd , soHeader.getString(NLBB070001Constant.COL_CARR_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.shpgSvcLvlCd , soHeader.getString(NLBB070001Constant.COL_SHPG_SVC_LVL_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.trxHdrNum , soHeader.getString(NLBB070001Constant.COL_TRX_HDR_NUM)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.usrCdIstlRefTxt , soHeader.getString(NLBB070001Constant.COL_SO_CONFIG_FLG)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.rtrnItemInclFlg , soHeader.getString(NLBB070001Constant.COL_RTRN_REQ_PRT_FLG)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.svcConfigMstrPk , soHeader.getBigDecimal(NLBB070001Constant.COL_SVC_CONFIG_MSTR_PK)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.asmReqFlg , soHeader.getString(NLBB070001Constant.COL_PRE_ISTL_SHOP_RQST_FLG)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.stageActCd , soHeader.getString(NLBB070001Constant.COL_STAGE_ACT_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.stageRecStsCd , "2");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.carrSvcHldAtLocFlg , ZYPConstant.FLG_OFF_N); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.rtePathCd , soHeader.getString(NLBB070001Constant.COL_WMS_RTE_PATH_CD)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.rtrnTagReqFlg , ZYPConstant.FLG_OFF_N); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.otbdSrcOrdTpTxt , this.getSubstring(soHeader.getString(NLBB070001Constant.COL_OTBD_SRC_ORD_TP_TXT), 30)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.prtToCustFlg , soHeader.getString("PRT_TO_CUST_FLG")); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.otbdSrcOrdNum , soHeader.getString(NLBB070001Constant.COL_SRC_ORD_NUM)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.slsOrdAdminNm , soHeader.getString(NLBB070001Constant.COL_SLS_ORD_ADMIN_NM)); 
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoHeader.slsRepPsnNm , soHeader.getString("SLS_REP_PSN_NM")); 

        // insert
        EZDTBLAccessor.insert(tMsgWmsInbdSoHeader);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgWmsInbdSoHeader.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_HDR, "SHPG_ORD",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_HDR, "SHPG_ORD",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    // Get WMS SO data (To Cust)
    private List<Map<String, Object>> getWmsSoToCust(String soNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findWmsInbdToCust", bindParam, execParam);
    }

    private void createWmsInbdSoShipTo(ResultSet soHeader ,Map<String, Object> soToCust, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSeqNum) throws SQLException {
        WMS_INBD_SO_SHIP_TOTMsg tMsgWmsInbdTo = new WMS_INBD_SO_SHIP_TOTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.glblCmpyCd, soHeader.getString(NLBB070001Constant.COL_GLBL_CMPY_CD));
        // QC#26824
        if ("2".equals(soHeader.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.whCd, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.whCd, soHeader.getString(NLBB070001Constant.COL_WH_CD));
        }
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsSqNum, wmsSeqNum);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.intfcTpId,"02");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.intfcRecTpId,"3");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCmpyCd, soHeader.getString(NLBB070001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsSoId , soHeader.getString(NLBB070001Constant.COL_SO_NUM));
        if (ZYPConstant.FLG_ON_Y.equals(soHeader.getString(NLBB070001Constant.COL_DROP_SHIP_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCustCd , "999999");
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCustCd , (String) soToCust.get("SELL_TO_CUST_CD"));
        }
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCustCd , (String) soToCust.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsShipToNm_01 , this.getSubstring((String) soToCust.get("SHIP_SO_CUST_LINE_LOC_NM_01"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsShipToNm_02 , this.getSubstring((String) soToCust.get("SHIP_SO_CUST_LINE_LOC_NM_02"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.firstLineAddr , (String) soToCust.get("SHIP_SO_CUST_LINE_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.scdLineAddr , (String) soToCust.get("SHIP_SO_CUST_LINE_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.thirdLineAddr , (String) soToCust.get("SHIP_SO_CUST_LINE_ADDR_03"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.frthLineAddr , (String) soToCust.get("SHIP_SO_CUST_LINE_ADDR_04"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.ctyAddr, (String) soToCust.get("SHIP_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.stCd , (String) soToCust.get("SHIP_ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.postCd , (String) soToCust.get("SHIP_POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.ctryCd , (String) soToCust.get("SHIP_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsShipToCtacNm , (String) soToCust.get("SHIP_CTAC_PTNR_PSN_NM"));
        // QC#31196
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.shipToCtacPsnNm , (String) soToCust.get("SHIP_TO_CTAC_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.shipToCtacNum , this.getSubstring((String) soToCust.get("SHIP_CTAC_PTNR_PSN_TEL_NUM"), 15));

        // insert
        EZDTBLAccessor.insert(tMsgWmsInbdTo);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgWmsInbdTo.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_SHIP_TO, "SHPG_ORD_CUST_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_SHIP_TO, "SHPG_ORD_CUST_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    private void createWmsInbdSoChrgTo(ResultSet soHeader ,Map<String, Object> soToCust, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSeqNum) throws SQLException {
        WMS_INBD_SO_CHRG_TOTMsg tMsgWmsInbdTo = new WMS_INBD_SO_CHRG_TOTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.glblCmpyCd, soHeader.getString(NLBB070001Constant.COL_GLBL_CMPY_CD));
        // QC#26824
        if ("2".equals(soHeader.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.whCd, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.whCd, soHeader.getString(NLBB070001Constant.COL_WH_CD));
        }
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsSqNum, wmsSeqNum);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.intfcTpId,"02");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.intfcRecTpId,"5");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCmpyCd, soHeader.getString(NLBB070001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsSoId , soHeader.getString(NLBB070001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCustCd , (String) soToCust.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsChrgToNm_01 , this.getSubstring((String) soToCust.get("SELL_SO_CUST_LINE_LOC_NM_01"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsChrgToNm_02 , this.getSubstring((String) soToCust.get("SELL_SO_CUST_LINE_LOC_NM_02"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.firstLineAddr , (String) soToCust.get("SELL_SO_CUST_LINE_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.scdLineAddr , (String) soToCust.get("SELL_SO_CUST_LINE_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.thirdLineAddr , (String) soToCust.get("SELL_SO_CUST_LINE_ADDR_03"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.frthLineAddr , (String) soToCust.get("SELL_SO_CUST_LINE_ADDR_04"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.ctyAddr, (String) soToCust.get("SELL_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.stCd , (String) soToCust.get("SELL_ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.postCd , (String) soToCust.get("SELL_POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.ctryCd , (String) soToCust.get("SELL_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsChrgToCtacNm , (String) soToCust.get("SELL_CTAC_PTNR_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.chrgToCtacNum , this.getSubstring((String) soToCust.get("SELL_CTAC_PTNR_PSN_TEL_NUM"), 15));

        // insert
        EZDTBLAccessor.insert(tMsgWmsInbdTo);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgWmsInbdTo.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_CHRG_TO, "SHPG_ORD_CUST_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_CHRG_TO, "SHPG_ORD_CUST_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    private void createWmsInbdSoBillTo(ResultSet soHeader ,Map<String, Object> soToCust, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSeqNum) throws SQLException {
        WMS_INBD_SO_BILL_TOTMsg tMsgWmsInbdTo = new WMS_INBD_SO_BILL_TOTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.glblCmpyCd, soHeader.getString(NLBB070001Constant.COL_GLBL_CMPY_CD));
        // QC#26824
        if ("2".equals(soHeader.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.whCd, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.whCd, soHeader.getString(NLBB070001Constant.COL_WH_CD));
        }
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsSqNum, wmsSeqNum);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.intfcTpId,"02");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.intfcRecTpId,"6");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCmpyCd, soHeader.getString(NLBB070001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsSoId , soHeader.getString(NLBB070001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsCustCd , (String) soToCust.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsBillToNm_01 , this.getSubstring((String) soToCust.get("BILL_SO_CUST_LINE_LOC_NM_01"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsBillToNm_02 , this.getSubstring((String) soToCust.get("BILL_SO_CUST_LINE_LOC_NM_02"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.firstLineAddr , (String) soToCust.get("BILL_SO_CUST_LINE_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.scdLineAddr , (String) soToCust.get("BILL_SO_CUST_LINE_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.thirdLineAddr , (String) soToCust.get("BILL_SO_CUST_LINE_ADDR_03"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.frthLineAddr , (String) soToCust.get("BILL_SO_CUST_LINE_ADDR_04"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.ctyAddr, (String) soToCust.get("BILL_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.stCd , (String) soToCust.get("BILL_ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.postCd , (String) soToCust.get("BILL_POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.ctryCd , (String) soToCust.get("BILL_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.wmsBillToCtacNm , (String) soToCust.get("BILL_CTAC_PTNR_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdTo.billToCtacNum , this.getSubstring((String) soToCust.get("BILL_CTAC_PTNR_PSN_TEL_NUM"),15));

        // insert
        EZDTBLAccessor.insert(tMsgWmsInbdTo);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgWmsInbdTo.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_BILL_TO, "SHPG_ORD_CUST_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_BILL_TO, "SHPG_ORD_CUST_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    private int createWmsInbdSoDetail(ResultSet soHeader ,Map<String, Object> soDetail, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSeqNum) throws SQLException {
        WMS_INBD_SO_DTLTMsg tMsgWmsInbdSoDetail = new WMS_INBD_SO_DTLTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.glblCmpyCd, soHeader.getString(NLBB070001Constant.COL_GLBL_CMPY_CD));
        // QC#26824
        if ("2".equals(soHeader.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.whCd, soHeader.getString(NLBB070001Constant.COL_SHIP_CUST_TXT));
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.rtlWhCd , (String) soDetail.get("SHIP_TO_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.rtlSwhCd , (String) soDetail.get("SHIP_TO_RTL_SWH_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.whCd, soHeader.getString(NLBB070001Constant.COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.rtlWhCd , (String) soDetail.get(NLBB070001Constant.COL_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.rtlSwhCd , (String) soDetail.get(NLBB070001Constant.COL_RTL_SWH_CD));
        }
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsSqNum, wmsSeqNum);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsLineNum, this.GetNumericOrNull((String) soDetail.get(NLBB070001Constant.COL_SO_SLP_NUM)));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.intfcTpId, "02");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.intfcRecTpId, "2");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.intfcRecActCd, "");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsCmpyCd, soHeader.getString(NLBB070001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsSoId   , soHeader.getString(NLBB070001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsMdseCd , (String) soDetail.get(NLBB070001Constant.COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.s80StkStsCd , (String) soDetail.get(NLBB070001Constant.COL_S80_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.custMdseCd , (String) soDetail.get(NLBB070001Constant.COL_CUST_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsOrdQty , (BigDecimal) soDetail.get(NLBB070001Constant.COL_RQST_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.backOrdQtyNum ,(BigDecimal) soDetail.get(NLBB070001Constant.COL_SHPG_BAL_QTY));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsShipQty,(BigDecimal) soDetail.get(NLBB070001Constant.COL_SHPG_QTY));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.unitPrcAmtNum,     this.getRoundMax((BigDecimal) soDetail.get(NLBB070001Constant.COL_UNIT_PRC_AMT), 2, new BigDecimal("9999999999999.99")));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.unitDiscAmtNum,    this.getRoundMax((BigDecimal) soDetail.get(NLBB070001Constant.COL_DISC_PRC_AMT), 2, new BigDecimal("9999999999999.99")));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.unitDiscPrcAmtNum, this.getRoundMax((BigDecimal) soDetail.get(NLBB070001Constant.COL_DISC_UNIT_PRC_AMT), 2, new BigDecimal("9999999999999.99")));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsTotAmtNum, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.indSerId, (String) soDetail.get(NLBB070001Constant.COL_SER_NUM_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.indVoidAllwFlg ,"Y");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.s80StkStsCdToCd , (String) soDetail.get(NLBB070001Constant.COL_S80_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.mdseCdSetCd , (String) soDetail.get(NLBB070001Constant.COL_SET_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.mdseCdSetDescTxt , (String) soDetail.get(NLBB070001Constant.COL_SET_MDSE_NM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.shipSetQty,(BigDecimal) soDetail.get(NLBB070001Constant.COL_SET_SHPG_QTY));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.unitInsdQty,(BigDecimal) soDetail.get(NLBB070001Constant.COL_IN_EACH_QTY));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.totWtAmtNum,(BigDecimal) soDetail.get(NLBB070001Constant.COL_TOT_SHPG_WT));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.totVolAmtNum,(BigDecimal) soDetail.get(NLBB070001Constant.COL_TOT_SHPG_VOL));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.soMdseTpCd , "");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsPackTpCd , "");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.batCptrReqFlg , (String) soDetail.get(NLBB070001Constant.COL_BAT_NUM_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.indConfigFlg , (String) soDetail.get(NLBB070001Constant.COL_CONFIG_ITEM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.svcConfigMstrPk , (BigDecimal) soDetail.get(NLBB070001Constant.COL_PICK_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.backOrdImpctTpCd , (String) soDetail.get(NLBB070001Constant.COL_BACK_ORD_IMPCT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.wmsOrdUomCd , "EA");
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.poLineTxt, (String) soDetail.get(NLBB070001Constant.COL_TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.usrCdIstlRefTxt , (String) soDetail.get(NLBB070001Constant.COL_CONFIG_ITEM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.backOrdFlg ,ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.rmvConfigFlg , (String) soDetail.get(NLBB070001Constant.COL_RMV_CONFIG_FLG));
        ZYPEZDItemValueSetter.setValue(tMsgWmsInbdSoDetail.origOrdQty ,(BigDecimal) soDetail.get(NLBB070001Constant.COL_ORD_QTY));

        // insert
        EZDTBLAccessor.insert(tMsgWmsInbdSoDetail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgWmsInbdSoDetail.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_DTL, "SHPG_ORD_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_DTL, "SHPG_ORD_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }

        // START 2017/12/20 S.Katsuma [QC#22592,ADD]
        updateShpgOrdDtl(tMsgWmsInbdSoDetail);
        // END 2017/12/20 S.Katsuma [QC#22592,ADD]

        return 1;
    }

    
    // Get SO data (Detail)
    private List<Map<String, Object>> getSoDetail(String soNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoDetail", bindParam, execParam);
    }

    private int createSoDetail(Map<String, Object> soDetail, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        NLBI1210_02TMsg tMsgSoDetail = new NLBI1210_02TMsg();

        ++regSeqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.transactionId, regTrxId);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.unitId, regUnitId);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.seqNumber, new BigDecimal(regSeqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.wmsRecId, "02");

        BigDecimal decOrdLineTxt = this.GetNumericOrNull((String) soDetail.get("SO_SLP_NUM"));
        String ordLineTxt = String.format("%06.0f", decOrdLineTxt);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.ordLineTxt, ordLineTxt);

        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.itemCdTxt, (String) soDetail.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.tplItemDescTxt, (String) soDetail.get("WMS_MDSE_DESC_TXT_01"));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.tplLocTxt, (String) soDetail.get("PLANT_CD"));
        // QC#21281:Change the source of TPL_SWH_CD from RTL_SWH_CD to TPL_LOC_NM.
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.tplSwhCd, (String) soDetail.get("TPL_LOC_NM"));

        BigDecimal shpgQty = (BigDecimal) soDetail.get("SHPG_QTY");
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.qtyOrdTxt, shpgQty.toPlainString());

        BigDecimal totShipWt = this.getRoundMax((BigDecimal) soDetail.get("TOT_SHPG_WT"), 3, new BigDecimal("999999999.999"));
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.wmsGrsWt, totShipWt);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.wmsNetWt, totShipWt);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.wmsWtUomCd, "LBR");

        BigDecimal decSrcOrdLineTxt = this.GetNumericOrNull((String) soDetail.get("TRX_LINE_NUM"));
        String srcOrdLineTxt = String.format("%06.0f", decSrcOrdLineTxt);
        ZYPEZDItemValueSetter.setValue(tMsgSoDetail.srcOrdLineTxt, srcOrdLineTxt);

        // insert
        EZDTBLAccessor.insert(tMsgSoDetail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoDetail.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_02, "SHPG_ORD_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_02, "SHPG_ORD_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }

        return regSeqNumber;
    }

    // Get SO data(Text)
    private List<Map<String, Object>> getSoText(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoText", bindParam, execParam);
    }

    // Get WMS SO data(Text)
    private List<Map<String, Object>> getWmsInbdSoText(String soNum) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findWmsInbdSoText", bindParam, execParam);
    }
    
    private int createSoText(List<Map<String, Object>> soTextList, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        int len = 0;
        for (Map<String, Object> soText : soTextList) {
            NLBI1210_03TMsg tMsgSoText = new NLBI1210_03TMsg();

            String text = (String) soText.get("SO_MSG_DESC_TXT");
            if (!ZYPCommonFunc.hasValue(text)) {
                continue;
            }
            // QC#26708
            text = text.replaceAll("\r\n", " ");
            len += text.getBytes().length;
            if (len > NLBB070001Constant.VAL_MAX_TEXT_SIZE) {
                break;
            }

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoText.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoText.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsRecId, "03");

            ZYPEZDItemValueSetter.setValue(tMsgSoText.soMsgDescTxt, text);

            // insert
            EZDTBLAccessor.insert(tMsgSoText);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoText.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_03, "SHPG_ORD_MSG",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_03, "SHPG_ORD_MSG",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    private int createWmsInbdSoText(ResultSet soHeader, List<Map<String, Object>> wmsInbdSoTextList, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSeqNum) throws SQLException {

        WMS_INBD_SO_TEXTTMsg tMsgSoText = new WMS_INBD_SO_TEXTTMsg();

        Map<String, Object> wmsInbdSoText = new HashMap<String, Object>();
        wmsInbdSoText = wmsInbdSoTextList.get(0);
        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgSoText.glblCmpyCd, soHeader.getString(NLBB070001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.whCd, soHeader.getString(NLBB070001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsSqNum, wmsSeqNum);
        ZYPEZDItemValueSetter.setValue(tMsgSoText.intfcTpId, "02");
        ZYPEZDItemValueSetter.setValue(tMsgSoText.intfcRecTpId, "4");
        ZYPEZDItemValueSetter.setValue(tMsgSoText.intfcRecActCd, "");
        ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsCmpyCd, soHeader.getString(NLBB070001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsSoId, soHeader.getString(NLBB070001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsPrintTpCd, "B");
        ZYPEZDItemValueSetter.setValue(tMsgSoText.wmsTxtCd, (String) wmsInbdSoText.get(NLBB070001Constant.COL_SO_MSG_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.inbdSoMsgTxt_01, (String) wmsInbdSoText.get(NLBB070001Constant.COL_INBD_SO_MSG_TXT_01));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.inbdSoMsgTxt_02, (String) wmsInbdSoText.get(NLBB070001Constant.COL_INBD_SO_MSG_TXT_02));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.inbdSoMsgTxt_03, (String) wmsInbdSoText.get(NLBB070001Constant.COL_INBD_SO_MSG_TXT_03));
        ZYPEZDItemValueSetter.setValue(tMsgSoText.inbdSoMsgTxt_04, (String) wmsInbdSoText.get(NLBB070001Constant.COL_INBD_SO_MSG_TXT_04));

        // insert
        EZDTBLAccessor.insert(tMsgSoText);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoText.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_TEXT, "SHPG_ORD_MSG",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_SO_TEXT, "SHPG_ORD_MSG",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }

        return 1;
    }
    
    // Get SO data(Site Survey)
    private Map<String, Object> getSoSiteSurvey(String soNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);

        return (Map<String, Object>) ssmBatchClient.queryObject("findSoSiteSurvey", bindParam, execParam);
    }

    private int createSoSiteSurvey(Map<String, Object> soSiteSurvey, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
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

        // QC#52369
        String rqstDt = (String) soSiteSurvey.get("reqDtTmTsTxt");
        String ctryCd = (String) soSiteSurvey.get("ctryCd");
        String postCd = (String) soSiteSurvey.get("postCd");

        for (String[] nameAndKey : nameAndKeys) {
            NLBI1210_04TMsg tMsgSoSiteSurvey = new NLBI1210_04TMsg();
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
                if (ZYPCommonFunc.hasValue(value)) {
                    value = value.replaceAll("\r\n", " ");
                }
            }

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.wmsRecId, "04");
            if (ZYPCommonFunc.hasValue(value)) {
                ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name);
            }

            // 01/23/2019 R.Shimamoto QC#30024 Add
            // 09/05/2019 QC#53305 Delete Start
//            if (ZYPCommonFunc.hasValue(tMsgSoSiteSurvey.tplSiteSrvyTxt) && tMsgSoSiteSurvey.tplSiteSrvyTxt.getValue().length() >= NLBB070001Constant.TPL_SITE_SRVY_TXT) {
//            	ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, tMsgSoSiteSurvey.tplSiteSrvyTxt.getValue().substring(NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_FROM, NLBB070001Constant.TPL_SITE_SRVY_TXT));
//            }
            // 09/05/2019 QC#53305 Delete End

            // QC#52369
            if (ZYPCommonFunc.hasValue(value)) {

                if ("DELIVERY HOURS FROM".equals(name) || //
                        "DELIVERY HOURS TO".equals(name) || //
                        "TIMESTOP".equals(name) || //
                        "ELEVATOR HOURS FROM".equals(name) || //
                        "ELEVATOR HOURS TO".equals(name)) {

                	value = convertAMPM(convertTime(rqstDt, value, ctryCd, postCd));
                    ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                } else {

                    ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name);
            }

            // 09/05/2019 QC#53305 Add Start
            if (ZYPCommonFunc.hasValue(tMsgSoSiteSurvey.tplSiteSrvyTxt) && tMsgSoSiteSurvey.tplSiteSrvyTxt.getValue().length() >= NLBB070001Constant.TPL_SITE_SRVY_TXT) {
            	ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, tMsgSoSiteSurvey.tplSiteSrvyTxt.getValue().substring(NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_FROM, NLBB070001Constant.TPL_SITE_SRVY_TXT));
            }
            // 09/05/2019 QC#53305 Add End

            // insert
            EZDTBLAccessor.insert(tMsgSoSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoSiteSurvey.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "DS_SITE_SRVY",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "DS_SITE_SRVY",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    // Get SO data(Mdse Survey)
    private List<Map<String, Object>> getSoMdseSurvey(String soNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoMdseSurvey", bindParam, execParam);
    }

    private int createSoMdseSurvey(List<Map<String, Object>> soMdseSurveyList, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        for (Map<String, Object> soMdseSurvey : soMdseSurveyList) {
            regSeqNumber = createSoMdseSurvey(soMdseSurvey, regTrxId, regUnitId, regSeqNumber);
        }
        return regSeqNumber;
    }

    private int createSoMdseSurvey(Map<String, Object> soMdseSurvey, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
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
            NLBI1210_04TMsg tMsgSoSiteSurvey = new NLBI1210_04TMsg();
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

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.wmsRecId, "04");

            ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, name + " " + value);

            // 01/23/2019 R.Shimamoto QC#30024 Add
            if (ZYPCommonFunc.hasValue(tMsgSoSiteSurvey.tplSiteSrvyTxt) && tMsgSoSiteSurvey.tplSiteSrvyTxt.getValue().length() >= NLBB070001Constant.TPL_SITE_SRVY_TXT) {
            	ZYPEZDItemValueSetter.setValue(tMsgSoSiteSurvey.tplSiteSrvyTxt, tMsgSoSiteSurvey.tplSiteSrvyTxt.getValue().substring(NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_FROM, NLBB070001Constant.TPL_SITE_SRVY_TXT));
            }

            // insert
            EZDTBLAccessor.insert(tMsgSoSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoSiteSurvey.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "MDSE",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "MDSE",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    // Get SO data (Serial No)
    private List<Map<String, Object>> getSoSerial(String soNum, String soSlpNum, BigDecimal shpgQty) {
        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_SO_NUM, soNum);
        bindParam.put(NLBB070001Constant.COL_SO_SLP_NUM, soSlpNum);
        // QC#50965
        bindParam.put("rowNum", shpgQty);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findSoSerial", bindParam, execParam);
    }

    private int createSoSerial(List<Map<String, Object>> soSerialList, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        for (Map<String, Object> soSerial : soSerialList) {
            NLBI1210_05TMsg tMsgSoSerial = new NLBI1210_05TMsg();

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.wmsRecId, "05");

            ZYPEZDItemValueSetter.setValue(tMsgSoSerial.tplSerNum, (String) soSerial.get("SER_NUM"));

            // insert
            EZDTBLAccessor.insert(tMsgSoSerial);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgSoSerial.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_05, "SO_SER",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_05, "SO_SER",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    /**
     * getTplCarrSvcLvl
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvl(String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB070001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(NLBB070001Constant.COL_CARR_CD, carrCd);
        queryParam.put(NLBB070001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam);
    }

    /**
     * getTplCarrSvcLvlWithEffort
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    public Map<String, Object> getTplCarrSvcLvlWithEffort(String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> tplCarrSvcLvl = null;

        tplCarrSvcLvl = getTplCarrSvcLvl(carrCd, shpgSvcLvlCd);
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(null, shpgSvcLvlCd);
        }
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(null, null);
        }

        return tplCarrSvcLvl;
    }

    /**
     * updateSoWmsStstusFlg
     * @param soNum
     * @param hitByWmsDropFlg
     * @param hitByDelyReqFlg
     */
    private void updateSoWmsStstusFlg(String soNum, String hitByWmsDropFlg ,String hitByDelyReqFlg) {
        SHPG_ORDTMsg tMsgTbl = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsgTbl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsgTbl.soNum, soNum);

        tMsgTbl = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgTbl);

        // Update of WMS send status Mod QC#26824
        if (ZYPConstant.FLG_ON_1.equals(hitByWmsDropFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.wmsDropFlg, ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(hitByDelyReqFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.delyReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsgTbl.delyReqTs, ZYPDateUtil.getCurrentSystemTime(NLBB070001Constant.FMT_YYYYMMDDHHMMSSSSS));
        }
        EZDTBLAccessor.update(tMsgTbl);
    }

    /**
     * <pre>
     * xxx
     * </pre>
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private int createRwsInterface(BigDecimal regTrxId, int unitId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get target RWS_NUMs
            Map<String, Object> bindParam = new HashMap<String, Object>();
            bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            bindParam.put(NLBB070001Constant.COL_WH_OWNR_CD, whOwnrCd);
            bindParam.put(NLBB070001Constant.COL_CARR_TP_NM, carrTpCd);
            /* 07/03/2017 CSAI Y.Imazu Add QC#19720 START */
            bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
            /* 07/03/2017 CSAI Y.Imazu Add QC#19720 END */

            stmt = ssmLLClient.createPreparedStatement("findRws", bindParam, execParam);
            rs = stmt.executeQuery();

            // Loop by RWS#
            int processedCount = 0;
            BigDecimal wmsSqNum = null;
            while (rs.next()) {

                ++unitId;
                BigDecimal regUnitId = new BigDecimal(unitId);
                int regSeqNumber = 0;

                // RWS_NUM
                String rwsNum = rs.getString(NLBB070001Constant.COL_RWS_NUM);

                // TPL_CARR_SVC_LVL
                Map<String, Object> tplCarrSvcLvl = getTplCarrSvcLvlByRWS(rwsNum);

                // RWS Header
                // QC#25233
                Map<String, String> ctacPsnInfo = new HashMap<String, String>();
                // QC#52369
                Map<String, String> customerInfo = new HashMap<String, String>();
                regSeqNumber = createRwsHeader(rs, tplCarrSvcLvl, regTrxId, regUnitId, regSeqNumber, ctacPsnInfo, customerInfo);

                // RWS Text
                List<Map<String, Object>> rwsTextList = getRwsText(rwsNum);

                regSeqNumber = createRwsText(rwsTextList, regTrxId, regUnitId, regSeqNumber);

                // RWS Site Survey
                Map<String, Object> rwsSiteSurvey = getRwsSiteSurvey(rwsNum);
                // QC#25233
                if (ctacPsnInfo != null) {
                    rwsSiteSurvey.put("CONTACT", ctacPsnInfo.get("CONTACT"));
                    rwsSiteSurvey.put("PHONE", ctacPsnInfo.get("PHONE"));
                }

                // QC#52369 customerInfo
                if (customerInfo != null) {
                    rwsSiteSurvey.put("reqDtTmTsTxt", customerInfo.get("reqDtTmTsTxt"));
                    rwsSiteSurvey.put("postCd", customerInfo.get("postCd"));
                    rwsSiteSurvey.put("ctryCd", customerInfo.get("ctryCd"));
                }

                // RWS Detail
                List<Map<String, Object>> rwsDetailList = getRwsDetail(rwsNum);

                // RWS MDSE Survey
                List<Map<String, Object>> rwsMdseSurveyList = getRwsMdseSurvey(rwsNum);

                // WMS
                //-----------------------------------------------------------------------------

                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_STS_CD))) {
                    // Get WMS SEQ No.
                    wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
                    // Get RWS Text(WMS)
                    List<Map<String, Object>> rwsWmsTextList = getWmsRwsText(rwsNum);
                    // (WMS) WMS_INBD_PO_HDR
                    createWmsRwsHeader(rs, rwsWmsTextList, regTrxId, regUnitId, wmsSqNum);
                    // (WMS) WMS_INBD_PO_VND
                    createWmsRwsVendor(rs, regTrxId, regUnitId, wmsSqNum);
                }

                for (Map<String, Object> rwsDetail : rwsDetailList) {
                    // QC#50027
                    if (rwsSiteSurvey != null) {
                        rwsSiteSurvey.put("CARR_DELY_TM_HOUR_MN", rwsDetail.get("SCHD_PICK_UP_TM"));
                    }

                    // RWS_DTL_LINE_NUM
                    String rwsDtlLineNum = (String) rwsDetail.get(NLBB070001Constant.COL_RWS_DTL_LINE_NUM);

                    regSeqNumber = createRwsDetail(rwsDetail, regTrxId, regUnitId, regSeqNumber);

                    // RWS Serial
                    List<Map<String, Object>> rwsSerialList = getRwsSerial(rwsNum, rwsDtlLineNum);

                    regSeqNumber = createRwsSerial(rwsSerialList, regTrxId, regUnitId, regSeqNumber);

                    if (rwsSiteSurvey != null) {
                        regSeqNumber = createRwsSiteSurvey(rwsSiteSurvey, regTrxId, regUnitId, regSeqNumber);
                        rwsSiteSurvey = null;
                    }

                    if (rwsMdseSurveyList != null) {
                        regSeqNumber = createRwsMdseSurvey(rwsMdseSurveyList, regTrxId, regUnitId, regSeqNumber);
                        rwsMdseSurveyList = null;
                    }

                    if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_STS_CD))) {
                        // (WMS) WMS_INBD_PO_DTL
                        createWmsRwsDetail(rs, rwsDetail, regTrxId, regUnitId, wmsSqNum);
                    }
                }

                // Update of WMS send status
                updateRwsStsCd(rwsNum, rs.getString(NLBB070001Constant.COL_HIT_BY_WMS_DROP_STS_CD), rs.getString(NLBB070001Constant.COL_HIT_BY_PICK_UP_RQST_FLG));

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

        return unitId;
    }

    // QC#52369 Mod
    private int createRwsHeader(ResultSet rwsHeader, Map<String, Object> tplCarrSvcLvl, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber, Map<String, String> ctacPsnInfo, Map<String, String> customerInfo) throws SQLException {

        // Set to TMsg(NLBI1210_01)
        NLBI1210_01TMsg tMsgRwsHeader = new NLBI1210_01TMsg();

        ++regSeqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.transactionId, regTrxId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.unitId, regUnitId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.seqNumber, new BigDecimal(regSeqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsRecId, "01");

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordIdTxt, rwsHeader.getString("RWS_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplLocTxt, getRcvPntCd(rwsHeader.getString("WH_CD")));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplOrgTxt, rwsHeader.getString("TPL_ORG_TXT"));

        // QC#52369
        String whInEtaDt = rwsHeader.getString("WH_IN_ETA_DT");
        String whInEtaTm = rwsHeader.getString("WH_IN_ETA_TM");
        String delyDtTxt = whInEtaDt;
        if (ZYPCommonFunc.hasValue(whInEtaTm)) {
            whInEtaDt = whInEtaDt + whInEtaTm;
        }

        if (ZYPCommonFunc.hasValue(whInEtaDt)) {

            // QC#52369 Convert Customer Timezone
            if (whInEtaDt.length() == NLBB070001Constant.VAL_DATE_FORMAT_YYYYMMDDHHMM.length()) {
                whInEtaDt = ZYPDateUtil.DateFormatter(whInEtaDt, NLBB070001Constant.VAL_DATE_FORMAT_YYYYMMDDHHMM, NLBB070001Constant.FMT_YYYYMMDDHHMMSSSSS);
            } else {
                whInEtaDt = ZYPDateUtil.DateFormatter(whInEtaDt, NLBB070001Constant.VAL_DATE_FORMAT_FROM, NLBB070001Constant.FMT_YYYYMMDDHHMMSSSSS);
            }

            if (ZYPCommonFunc.hasValue(whInEtaTm)) {
                whInEtaDt = convCustomerTime(whInEtaDt, rwsHeader.getString("FROM_LOC_CTRY_CD"), rwsHeader.getString("FROM_LOC_POST_CD"));
            }
            delyDtTxt = whInEtaDt.substring(0, 8);
        }
        
//        String whInEtaDt = rwsHeader.getString("WH_IN_ETA_DT");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsHdrDelyDtTxt, delyDtTxt);

        // QC#52369 Mod
        String delyTm = "";
        if (ZYPCommonFunc.hasValue(whInEtaDt) && ZYPCommonFunc.hasValue(whInEtaTm)) {
            delyTm = whInEtaDt.substring(8, 12);
        }

        if (ZYPCommonFunc.hasValue(delyTm)) {
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsDelyTs, delyTm);
        }

        // QC#21283
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordIdTxt, rwsHeader.getString("RWS_NUM"));
//        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordIdTxt, rwsHeader.getString("RWS_REF_NUM"));

        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplCtrlId, rwsHeader.getString(NLBB070001Constant.COL_TPL_CTRL_ID));

        // SHIP_TO_CUST
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCustTxt, rwsHeader.getString("FROM_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipNmTxt_01, this.getSubstring(rwsHeader.getString("FROM_LOC_NM_01"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipNmTxt_02, this.getSubstring(rwsHeader.getString("FROM_LOC_NM_02"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipFirstLineAddrTxt, rwsHeader.getString("FROM_LOC_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipScdLineAddrTxt, rwsHeader.getString("FROM_LOC_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipZipOrPostCdTxt, rwsHeader.getString("FROM_LOC_POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCtyTxt, rwsHeader.getString("FROM_LOC_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipCtryTxt, rwsHeader.getString("FROM_LOC_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.shipStOrProvTxt, rwsHeader.getString("FROM_LOC_ST_CD"));

        // BILL_TO_CUST
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billCustTxt, rwsHeader.getString("FROM_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billNmTxt_01, this.getSubstring(rwsHeader.getString("FROM_LOC_NM_01"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billNmTxt_02, this.getSubstring(rwsHeader.getString("FROM_LOC_NM_02"), 35));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billFirstLineAddrTxt, rwsHeader.getString("FROM_LOC_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billScdLineAddrTxt, rwsHeader.getString("FROM_LOC_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billZipOrPostCdTxt, rwsHeader.getString("FROM_LOC_POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billCtyTxt, rwsHeader.getString("FROM_LOC_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billCtryTxt, rwsHeader.getString("FROM_LOC_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.billStOrProvTxt, rwsHeader.getString("FROM_LOC_ST_CD"));

        // CTAC_CUST
        String ctacCustTxt = rwsHeader.getString("FROM_LOC_PSN_NM");
        if (ZYPCommonFunc.hasValue(ctacCustTxt) && ctacCustTxt.length() > NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_MAX_SIZE) {
            ctacCustTxt = ctacCustTxt.substring(0, NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ctacCustTxt, ctacCustTxt);
        String ctacPhoNum = rwsHeader.getString("FROM_LOC_TEL_NUM");
        if (ZYPCommonFunc.hasValue(ctacPhoNum) && ctacPhoNum.length() > NLBB070001Constant.LG_CUT_CTAC_PHO_NUM_MAX_SIZE) {
            ctacPhoNum = ctacPhoNum.substring(0, NLBB070001Constant.LG_CUT_CTAC_PHO_NUM_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ctacPhoNum, ctacPhoNum);
        // QC#25233
        ctacPsnInfo.put("CONTACT", ctacCustTxt);
        ctacPsnInfo.put("PHONE", ctacPhoNum);

        // CARR_CD CARR_NM
        String tplCarrCd = NLBB070001Constant.VAL_CONST_CARR_CUST_TXT;
        String tplCarrNm = NLBB070001Constant.VAL_CONST_CARR_NM_01;
        if (tplCarrSvcLvl != null) {
            if (ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_CD))) {
                tplCarrCd = (String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_CD);
            }
            if (ZYPCommonFunc.hasValue((String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_NM))) {
                tplCarrNm = (String) tplCarrSvcLvl.get(NLBB070001Constant.COL_TPL_CARR_NM);
            }
        }
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.carrCustTxt, tplCarrCd);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.carrNm_01, tplCarrNm);

        // WMS_REQ_DT_TXT...WMS_FNSH_DELY_DT_TXT
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsReqDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsFnshReqDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsActDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsFnshActDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsOrdDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsFnshOrdDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsDelyDtTxt, delyDtTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsFnshDelyDtTxt, delyDtTxt);

        // QC#21283
        //ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplSrcOrdNum, rwsHeader.getString("TRX_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.tplSrcOrdNum, rwsHeader.getString("RWS_REF_NUM"));

        // QC#22134
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.ordTpTxt, rwsHeader.getString("ORD_TP_TXT"));

        // QC#52369 customerInfo
        if (ZYPCommonFunc.hasValue(whInEtaDt)) {
            customerInfo.put("reqDtTmTsTxt", whInEtaDt.substring(0, 8));
        } else {
            customerInfo.put("reqDtTmTsTxt", "");
        }
        if (ZYPCommonFunc.hasValue(tMsgRwsHeader.shipZipOrPostCdTxt)) {
            customerInfo.put("postCd", tMsgRwsHeader.shipZipOrPostCdTxt.getValue());
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
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_01, "RWS_HDR",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_01, "RWS_HDR",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }

        return regSeqNumber;
    }

    // Get RWS data (Detail)
    private List<Map<String, Object>> getRwsDetail(String rwsNum) {

        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 START */
        bindParam.put("flgY", ZYPConstant.FLG_ON_Y);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 END */

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsDetail", bindParam, execParam);
    }

    private int createRwsDetail(Map<String, Object> rwsDetail, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {

        // Set to TMsg(NLBI1210_02)
        NLBI1210_02TMsg tMsgRwsDetail = new NLBI1210_02TMsg();

        ++regSeqNumber;

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.transactionId, regTrxId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.unitId, regUnitId);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.seqNumber, new BigDecimal(regSeqNumber));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsRecId, "02");

        BigDecimal decOrdLineTxt = this.GetNumericOrNull((String) rwsDetail.get("RWS_DTL_LINE_NUM"));
        String ordLineTxt = String.format("%06.0f", decOrdLineTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.ordLineTxt, ordLineTxt);

        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.itemCdTxt, (String) rwsDetail.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.tplItemDescTxt, (String) rwsDetail.get("WMS_MDSE_DESC_TXT_01"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.tplLocTxt, (String) rwsDetail.get("PLANT_CD"));
        // QC#21281:Change the source of TPL_SWH_CD from RTL_SWH_CD to TPL_LOC_NM.
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.tplSwhCd, (String) rwsDetail.get("TPL_LOC_NM"));

        BigDecimal rwsQty = (BigDecimal) rwsDetail.get("RWS_QTY");
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.qtyOrdTxt, rwsQty.toPlainString());

        BigDecimal decSrcOrdLineTxt = this.GetNumericOrNull((String) rwsDetail.get("TRX_LINE_NUM"));
        String srcOrdLineTxt = String.format("%06.0f", decSrcOrdLineTxt);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.srcOrdLineTxt, srcOrdLineTxt);

        // insert
        EZDTBLAccessor.insert(tMsgRwsDetail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsDetail.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_02, "RWS_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_02, "RWS_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }

        return regSeqNumber;
    }

    // Get RWS data(Text)
    private List<Map<String, Object>> getRwsText(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsText", bindParam, execParam);
    }

    private int createRwsText(List<Map<String, Object>> rwsTextList, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        int len = 0;
        for (Map<String, Object> rwsText : rwsTextList) {
            NLBI1210_03TMsg tMsgRwsText = new NLBI1210_03TMsg();

            String text = (String) rwsText.get("RWS_MSG_TXT");
            if (!ZYPCommonFunc.hasValue(text)) {
                continue;
            }
            // QC#26708
            text = text.replaceAll("\r\n", " ");
            len += text.getBytes().length;
            if (len > NLBB070001Constant.VAL_MAX_TEXT_SIZE) {
                break;
            }

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsText.wmsRecId, "03");

            ZYPEZDItemValueSetter.setValue(tMsgRwsText.soMsgDescTxt, text);

            // insert
            EZDTBLAccessor.insert(tMsgRwsText);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsText.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_03, "RWS_MSG",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_03, "RWS_MSG",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    // Get RWS data(Site Survey)
    private Map<String, Object> getRwsSiteSurvey(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);
        bindParam.put(NLBB070001Constant.COL_SCE_ORD_TP_CD, SCE_ORD_TP.RETURN);

        return (Map<String, Object>) ssmBatchClient.queryObject("findRwsSiteSurvey", bindParam, execParam);
    }

    private int createRwsSiteSurvey(Map<String, Object> rwsSiteSurvey, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
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
            // QC#23044 VALIDATION
            new String[] {"CUSTOMER INFORMED ABOUT B/O", "CUST_INFO_BO_FLG" },
            new String[] {"PICKUP EXTRA TONER", "PICK_UP_XTR_TONER_FLG" },
            new String[] {"PICKUP AT SAME TIME", "PICK_UP_AT_SAME_TM_FLG" },
        };

        // QC#52369
        String rqstDt = (String) rwsSiteSurvey.get("reqDtTmTsTxt");
        String ctryCd = (String) rwsSiteSurvey.get("ctryCd");
        String postCd = (String) rwsSiteSurvey.get("postCd");

        for (String[] nameAndKey : nameAndKeys) {
            NLBI1210_04TMsg tMsgRwsSiteSurvey = new NLBI1210_04TMsg();
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
                if (ZYPCommonFunc.hasValue(value)) {
                    value = value.replaceAll("\r\n", " ");
                }
            }

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.wmsRecId, "04");
            if (ZYPCommonFunc.hasValue(value)) {
                ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name);
            }

            // 01/23/2019 R.Shimamoto QC#30024 Add
            // 09/05/2019 QC#53305 Delete Start
//            if (ZYPCommonFunc.hasValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt) && tMsgRwsSiteSurvey.tplSiteSrvyTxt.getValue().length() >= NLBB070001Constant.TPL_SITE_SRVY_TXT) {
//            	ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, tMsgRwsSiteSurvey.tplSiteSrvyTxt.getValue().substring(NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_FROM, NLBB070001Constant.TPL_SITE_SRVY_TXT));
//            }
            // 09/05/2019 QC#53305 Delete End

            // QC#52369
            if (ZYPCommonFunc.hasValue(value)) {

                if ("DELIVERY HOURS FROM".equals(name) || //
                        "DELIVERY HOURS TO".equals(name) || //
                        "TIMESTOP".equals(name) || //
                        "ELEVATOR HOURS FROM".equals(name) || //
                        "ELEVATOR HOURS TO".equals(name)) {

                	value = convertAMPM(convertTime(rqstDt, value, ctryCd, postCd));
                    ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                } else {

                    ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);

                }
            } else {
                ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name);
            }

            // 09/05/2019 QC#53305 Add Start
            if (ZYPCommonFunc.hasValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt) && tMsgRwsSiteSurvey.tplSiteSrvyTxt.getValue().length() >= NLBB070001Constant.TPL_SITE_SRVY_TXT) {
            	ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, tMsgRwsSiteSurvey.tplSiteSrvyTxt.getValue().substring(NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_FROM, NLBB070001Constant.TPL_SITE_SRVY_TXT));
            }
            // 09/05/2019 QC#53305 Add Start

            // insert
            EZDTBLAccessor.insert(tMsgRwsSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsSiteSurvey.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "DS_SITE_SRVY",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "DS_SITE_SRVY",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    // Get RWS data(Site Survey)
    private List<Map<String, Object>> getRwsMdseSurvey(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsMdseSurvey", bindParam, execParam);
    }

    private int createRwsMdseSurvey(List<Map<String, Object>> rwsMdseSurveyList, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        for (Map<String, Object> rwsMdseSurvey : rwsMdseSurveyList) {
            regSeqNumber = createRwsMdseSurvey(rwsMdseSurvey, regTrxId, regUnitId, regSeqNumber);
        }
        return regSeqNumber;
    }

    private int createRwsMdseSurvey(Map<String, Object> rwsMdseSurvey, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
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
            NLBI1210_04TMsg tMsgRwsSiteSurvey = new NLBI1210_04TMsg();
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
            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.wmsRecId, "04");

            ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, name + " " + value);

            // 01/23/2019 R.Shimamoto QC#30024 Add
            if (ZYPCommonFunc.hasValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt) && tMsgRwsSiteSurvey.tplSiteSrvyTxt.getValue().length() >= NLBB070001Constant.TPL_SITE_SRVY_TXT) {
            	ZYPEZDItemValueSetter.setValue(tMsgRwsSiteSurvey.tplSiteSrvyTxt, tMsgRwsSiteSurvey.tplSiteSrvyTxt.getValue().substring(NLBB070001Constant.LG_CUT_CTAC_CUST_TXT_FROM, NLBB070001Constant.TPL_SITE_SRVY_TXT));
            }

            // insert
            EZDTBLAccessor.insert(tMsgRwsSiteSurvey);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsSiteSurvey.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "MDSE",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_04, "MDSE",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    // Get RWS data (Serial No)
    private List<Map<String, Object>> getRwsSerial(String rwsNum, String rwsLineNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);
        bindParam.put(NLBB070001Constant.COL_RWS_LINE_NUM, rwsLineNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findRwsSerial", bindParam, execParam);
    }

    private int createRwsSerial(List<Map<String, Object>> rwsSerialList, BigDecimal regTrxId, BigDecimal regUnitId, int regSeqNumber) {
        for (Map<String, Object> rwsSerial : rwsSerialList) {
            NLBI1210_05TMsg tMsgRwsSerial = new NLBI1210_05TMsg();

            ++regSeqNumber;

            // Set parameters.
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.interfaceId, this.interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.transactionId, regTrxId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.unitId, regUnitId);
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.seqNumber, new BigDecimal(regSeqNumber));
            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.wmsRecId, "05");

            ZYPEZDItemValueSetter.setValue(tMsgRwsSerial.tplSerNum, (String) rwsSerial.get("SER_NUM"));

            // insert
            EZDTBLAccessor.insert(tMsgRwsSerial);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsSerial.getReturnCode())) {
                outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_05, "RWS_SER",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

                throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_NLBI1210_05, "RWS_SER",
                        NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                        , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
            }
        }

        return regSeqNumber;
    }

    private List<Map<String, Object>> getWmsRwsText(String rwsNum) {
        Map<String, String> bindParam = new HashMap<String, String>();
        bindParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        bindParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("findWmsRwsText", bindParam, execParam);
    }

    private void createWmsRwsHeader(ResultSet rwsHeader, List<Map<String, Object>> rwsWmsMsgList, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSqNum) throws SQLException {

        // Set to TMsg(WMS_INBD_PO_HDRT)
        WMS_INBD_PO_HDRTMsg tMsgRwsHeader = new WMS_INBD_PO_HDRTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.glblCmpyCd, rwsHeader.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.whCd, rwsHeader.getString("WH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.intfcTpId, "01");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.intfcRecTpId, "1");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.intfcRecActCd, "");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsCmpyCd, rwsHeader.getString("S80_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsPoId, rwsHeader.getString("RWS_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.vndCd, rwsHeader.getString("FROM_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsPrchTpCd, rwsHeader.getString("S80_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsTrxCd, rwsHeader.getString("S80_TRX_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsPoStsCd, "");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.poFromDtTmTs, rwsHeader.getString("WH_IN_ETA_DT"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.poFromDtTmTs, ZYPCommonFunc.rightPad(rwsHeader.getString("WH_IN_ETA_DT")
                                                                                        , NLBB070001Constant.LG_DATE_COLUMN_SIZE
                                                                                        , "0"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.poUserId, "");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.printSwthCd, "P");
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsVeslNm, rwsHeader.getString("IMPT_INV_VESL_NM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.wmsBolNum, rwsHeader.getString("IMPT_INV_BOL_NUM"));

        if (rwsWmsMsgList.isEmpty()){
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_01, "");
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_02, "");
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_03, "");
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_04, "");
        } else {
            Map<String, Object> rwsWmsMsg = new HashMap<String, Object>();
            rwsWmsMsg = rwsWmsMsgList.get(0);
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_01, (String) rwsWmsMsg.get("RWS_MSG_TXT_01"));
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_02, (String) rwsWmsMsg.get("RWS_MSG_TXT_02"));
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_03, (String) rwsWmsMsg.get("RWS_MSG_TXT_03"));
            ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.inbdPoMsgTxt_04, (String) rwsWmsMsg.get("RWS_MSG_TXT_04"));
        }
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.rtlWhCd, rwsHeader.getString("WH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.rwsNum, rwsHeader.getString("RWS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.svcConfigMstrPk, rwsHeader.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.stageActCd, rwsHeader.getString("STAGE_ACT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsHeader.stageRecStsCd, "2");

        // insert
        EZDTBLAccessor.insert(tMsgRwsHeader);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsHeader.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_PO_HDR, "RWS_HDR",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_PO_HDR, "RWS_HDR",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    private void createWmsRwsVendor(ResultSet rwsHeader, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSqNum) throws SQLException {

        // Set to TMsg(WMS_INBD_PO_HDRT)
        WMS_INBD_PO_VNDTMsg tMsgRwsVendor = new WMS_INBD_PO_VNDTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.glblCmpyCd, rwsHeader.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.whCd, rwsHeader.getString("WH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.intfcTpId, "01");
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.intfcRecTpId, "3");
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.intfcRecActCd, "");
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.wmsCmpyCd, rwsHeader.getString("S80_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.wmsPoId, rwsHeader.getString("RWS_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.vndCd, rwsHeader.getString("FROM_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.wmsVndNm_01, this.getSubstring(rwsHeader.getString("FROM_LOC_NM_01") ,35));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.wmsVndNm_02, this.getSubstring(rwsHeader.getString("FROM_LOC_NM_02") ,35));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.firstLineAddr ,rwsHeader.getString("FROM_LOC_ADDR_01"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.scdLineAddr ,rwsHeader.getString("FROM_LOC_ADDR_02"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.thirdLineAddr ,rwsHeader.getString("FROM_LOC_ADDR_03"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.frthLineAddr ,rwsHeader.getString("FROM_LOC_ADDR_04"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.ctyAddr ,rwsHeader.getString("FROM_LOC_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.stCd ,rwsHeader.getString("FROM_LOC_ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.postCd ,rwsHeader.getString("FROM_LOC_POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.ctryCd ,rwsHeader.getString("FROM_LOC_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.vndToCtacNm ,rwsHeader.getString("FROM_LOC_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsVendor.vndToCtacNum ,this.getSubstring(rwsHeader.getString("FROM_LOC_TEL_NUM"), 15));
        // insert
        EZDTBLAccessor.insert(tMsgRwsVendor);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsVendor.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_PO_VNDL, "RWS_HDR",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_PO_VNDL, "RWS_HDR",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    private void createWmsRwsDetail(ResultSet rwsHeader, Map<String, Object> rwsDetail, BigDecimal regTrxId, BigDecimal regUnitId, BigDecimal wmsSqNum) throws SQLException {

        // Set to TMsg(NLBI1210_02)
        WMS_INBD_PO_DTLTMsg tMsgRwsDetail = new WMS_INBD_PO_DTLTMsg();

        // Set parameters.
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.glblCmpyCd, rwsHeader.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.whCd, rwsHeader.getString("WH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsLineNum, this.GetNumericOrNull((String) rwsDetail.get("RWS_DTL_LINE_NUM")));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.intfcTpId, "01");
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.intfcRecTpId, "2");
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.intfcRecActCd, "");
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsCmpyCd, rwsHeader.getString("S80_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsPoId, rwsHeader.getString("RWS_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsMdseCd, (String) rwsDetail.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.s80StkStsCd, (String) rwsDetail.get("S80_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsInvInd, "S");
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsOpenQty, (BigDecimal) rwsDetail.get("RWS_QTY"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsEstDtTmTs, ZYPCommonFunc.rightPad( (String) rwsDetail.get("WH_IN_ETA_DT")
                                                                                          , NLBB070001Constant.LG_DATE_COLUMN_SIZE
                                                                                          , "0"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsInvId, (String) rwsDetail.get("IMPT_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.wmsDoId, (String) rwsDetail.get("IMPT_INV_DO_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.cseFromNum, (BigDecimal) rwsDetail.get("OUT_PACK_FROM_CSE_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.cseToNum, (BigDecimal) rwsDetail.get("OUT_PACK_TO_CSE_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.rtlWhCd, rwsHeader.getString("WH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.rtlSwhCd, (String) rwsDetail.get("RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.usrCdRefTxt, (String) rwsDetail.get("THIRD_PTY_DSP_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsgRwsDetail.serApvlReqFlg, (String) rwsDetail.get("SER_APVL_REQ_FLG"));

        // insert
        EZDTBLAccessor.insert(tMsgRwsDetail);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgRwsDetail.getReturnCode())) {
            outputErr(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_PO_DTL, "RWS_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });

            throw new S21AbendException(NLBB070001Constant.NLGM0045E, new String[] {NLBB070001Constant.TBL_WMS_INBD_PO_DTL, "RWS_DTL",
                    NLXCMsgHelper.toListedString(NLBB070001Constant.COL_INTERFACE_ID, NLBB070001Constant.COL_TRANSACTION_ID, NLBB070001Constant.COL_UNIT_ID) //
                    , NLXCMsgHelper.toListedString(this.interfaceId, regTrxId, regUnitId) });
        }
    }

    /**
     * getTplCarrSvcLvlForRWS
     * @param rwsNum
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvlByRWS(String rwsNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB070001Constant.COL_WH_OWNR_CD, this.whOwnrCd);
        queryParam.put(NLBB070001Constant.COL_RWS_NUM, rwsNum);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvlByRWS", queryParam, execParam);
        return result;
    }

    /**
     * updateRwsWmsDropStsCd
     * @param gCmpyCd
     * @param rwsNum
     */
    private void updateRwsStsCd(String rwsNum, String hitByWmsDropStsCd, String hitByPickUpRqstFlg ) {
        RWS_HDRTMsg tMsgTbl = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsgTbl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsgTbl.rwsNum, rwsNum);

        tMsgTbl = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgTbl);

        // Update of WMS send status
        if (ZYPConstant.FLG_ON_Y.equals(hitByWmsDropStsCd)) {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.wmsDropStsCd, ZYPConstant.FLG_ON_1);
        }
        if (ZYPConstant.FLG_ON_Y.equals(hitByPickUpRqstFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsgTbl.pickUpRqstFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsgTbl.pickUpRqstTs, ZYPDateUtil.getCurrentSystemTime(NLBB070001Constant.FMT_YYYYMMDDHHMMSSSSS));
        }

        EZDTBLAccessor.update(tMsgTbl);
    }

    /**
     * getWhOwnrCd
     * @return
     */
    private String getWhOwnrCd() {
        return ZYPCodeDataUtil.getVarCharConstValue(NLBB070001Constant.VAR_CHAR_CONCT_NM_TG_ORDER, glblCmpyCd);
    }

    /**
     * getCarrTpNm
     * @return
     */
    private String getCarrTpNm() {
        return ZYPCodeDataUtil.getVarCharConstValue(NLBB070001Constant.VAR_CHAR_CONCT_NM_TG_CARRIER, glblCmpyCd);
    }

    private BigDecimal getRoundMax(BigDecimal targetVal, int decCount, BigDecimal maxVal){

        if (targetVal == null) {
            return null;
        }

        BigDecimal retVal = targetVal.setScale(decCount, BigDecimal.ROUND_HALF_UP);
        if (retVal.compareTo(maxVal) > 0 ) {
            retVal = maxVal;
        }
        return retVal;
    }

    private String getSubstring(String targetVal, int length) {

        if (targetVal == null) {
            return null;
        }

        String retVal = null;
        if (targetVal.length() > length) {
            retVal = targetVal.substring(0, length);
        } else {
            retVal = targetVal;
        }
        return retVal;
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
     * getShpgOrd
     * QC#21657-1 Add Method.
     * @param String glblCmpyCd, String soNum
     * @return SHPG_ORDTMsg
     */
    private SHPG_ORDTMsg getShpgOrd(String gCompCd, String soNum) {

        SHPG_ORDTMsg inMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
        ZYPEZDItemValueSetter.setValue(inMsg.soNum, soNum);
        SHPG_ORDTMsg outMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getPrchReq
     * QC#21657-1 Add Method.
     * @param gCompCd
     * @param prchReqNum
     * @return PRCH_REQTMsg
     */
    private PRCH_REQTMsg getPrchReq(String gCompCd, String prchReqNum) {

        PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
        PRCH_REQTMsg outMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getShpgPntCd
     * @param rtlWhCd String
     * @return String
     */
    private String getShpgPntCd(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB070001Constant.COL_RTL_WH_CD, rtlWhCd);
        String result = (String) this.ssmBatchClient.queryObject("getShpgPntCd", queryParam, execParam);
        return result;
    }

    /**
     * getRcvPntCd
     * @param rtlWhCd String
     * @return String
     */
    private String getRcvPntCd(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB070001Constant.COL_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(NLBB070001Constant.COL_RTL_WH_CD, rtlWhCd);
        String result = (String) this.ssmBatchClient.queryObject("getRcvPntCd", queryParam, execParam);
        return result;
    }

    // START 2017/12/20 S.Katsuma [QC#22592,ADD]
    private boolean updateShpgOrdDtl(WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg) {
        SHPG_ORD_DTLTMsg soDtlT = new SHPG_ORD_DTLTMsg();
        String soSlpNum = ZYPCommonFunc.leftPad(String.valueOf(wmsInbdSoDtlTMsg.wmsLineNum.getValue()), 3,"0");
        ZYPEZDItemValueSetter.setValue(soDtlT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soDtlT.soNum, wmsInbdSoDtlTMsg.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(soDtlT.soSlpNum, soSlpNum);
        soDtlT = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(soDtlT);

        // Mod Start 2019/11/29 QC#54389
//        if (soDtlT != null) {
        if (soDtlT != null && ZYPCommonFunc.hasValue(soDtlT.shpgStsCd) && soDtlT.shpgStsCd.getValue().equals(SHPG_STS.S_OR_O_PRINTED)) {
            soDtlT.dsSoLineStsCd.setValue(DS_SO_LINE_STS.AWAITING_PICK_CONFIRMATION);
            EZDTBLAccessor.update(soDtlT);
        }
        // Mod End 2019/11/29 QC#54389

        return true;
    }
    // END 2017/12/20 S.Katsuma [QC#22592,ADD]

    // Add QC#52369 Start
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
     * @param ctryCd CTRY_CD SO : SHIP_TO / RWS : FROM_LOC
     * @param postCd POST_CD SO : SHIP_TO / RWS : FROM_LOC
     * @return
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

    public static String formatHHmm(String hhmm) {
        if (hhmm.length() < 4) {
            hhmm = "0" + hhmm;
        }
        return hhmm;
    }

    public static String convertTimeStamp(String ctryCd, String postCd, String timeStamp) {
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, timeStamp, ctryCd, postCd);
        return timeInfo.getDateTime();
    }

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
    // Add QC#52369 End
}
