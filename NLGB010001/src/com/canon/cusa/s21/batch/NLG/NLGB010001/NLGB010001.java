/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB010001;

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
import business.db.WMS_INBD_TRXTMsg;
import business.db.WMS_RWS_ORIG_LINE_SAVETMsg;
import business.db.WMS_SO_ORIG_LINE_SAVETMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
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
 * Batch Program Class for WMS to MW Transaction.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/01/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 11/10/2015   CSAI            K.Lee           Create          WDSNA Initial
 * 04/19/2016   CSAI            D.Fukaya        Update          QC#5252
 * 09/01/2016   CITS            T.Wada          Update          QC#13318
 * 12/27/2016   CITS            S.Endo          Update          QC#15340
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 05/15/2020   CITS            K.Fukumura      Update          QC#56071
 * 08/28/2020   CITS            JR.Mercado      Update          QC#57442
 *</pre>
 */
public class NLGB010001 extends S21BatchMain implements NLGB010001Constant {

    // *********************************************************
    // Instance Variables: Basic
    // *********************************************************
    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Warehouse Group Cd */
    private String whGpCd = null;

    /** Interface ID */
    private String interfaceId = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** Counter of Records: Successful */
    private int normalRecCount = 0;

    /** Counter of Records: Error */
    private int totalErrCount = 0;

    /** Length to cut the warehouse code for Tecsys */
    private int cutLg = 0;

    /** Batch Id */
    private String wmsBatId = "";

    /** Target Warehouse Code */
    String[] trgtWhCdAry = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** caching WMS_ORD_TP_CD for S/O */
    private S21LRUMap<String, String> cacheMapWmsOrdTpCdForSo = new S21LRUMap<String, String>();

    /** caching WMS_ORD_TP_CD for P/O */
    private S21LRUMap<String, String> cacheMapWmsOrdTpCdForPo = new S21LRUMap<String, String>();

    // QC#17981 Add.
    /** NLGB0100_SEARCHKEY_SO_NUM */
    private String[] searchKeySoNumFromWmsOrdTp = null;

    /** NLGB0100_SEARCHKEY_SONUM_SC */
    private String[] searchKeySoNumFromWmsOrdTpByPCC = null;

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {

        new NLGB010001().executeBatch(NLGB010001.class.getSimpleName());
    }

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
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_INTERFACE_ID });
        }

        // Getting excute Warehouse Group Code
        whGpCd = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        // Getting Length to cut the warehouse code for Tecsys
        cutLg = Integer.parseInt(ZYPCodeDataUtil.getVarCharConstValue(CD_KEY_LG_CUT_WH_CD_FOR_TECSYS, glblCmpyCd));

        // Getting Bat Id
        wmsBatId = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        try {
            trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);

            if (trgtWhCdAry != null) {
                doProcess();
            } else {
                outputErr(NLGM0047E, new String[] {whGpCd });
            }

        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
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
        setTermState(termCd, this.normalRecCount, this.totalErrCount, this.normalRecCount + this.totalErrCount);
    }

    /**
     * WMS Upload Data Creation Process
     */
    private void doProcess() {

        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);
        // EZDDebugOutput.println(1, PROGRAM_ID + "tranId Cnt:" +
        // tranIds.length, this);

        // QC#17981 Add.
        searchKeySoNumFromWmsOrdTp = ZYPCodeDataUtil.getVarCharConstValue(NLGB0100_SEARCHKEY_SO_NUM, glblCmpyCd).split(",");
        searchKeySoNumFromWmsOrdTpByPCC = ZYPCodeDataUtil.getVarCharConstValue(NLGB0100_SEARCHKEY_SONUM_SC, glblCmpyCd).split(",");

        for (BigDecimal tranId : tranIds) {

            // Search for data to be processed from NLGI0100_01
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            List ifList = this.ssmBatchClient.queryObjectList("getIfData", queryParam);

            for (int i = 0; i < ifList.size(); i++) {
                WMS_INBD_TRXTMsg wmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) ifList.get(i);

                String errCd = null;

                // QC#57442 Add - Start
                String otbdOrdNum = wmsInbdTrxTMsg.otbdOrdNum.getValue();
                String inbdOrdNum = wmsInbdTrxTMsg.inbdOrdNum.getValue();
                if (ZYPCommonFunc.hasValue(otbdOrdNum) && !isShpgOrdExist(otbdOrdNum)) {
                    setRequiredData(wmsInbdTrxTMsg);
                    errCd = "NLZM2391E";
                } else if (ZYPCommonFunc.hasValue(inbdOrdNum) && !isRwsHdrExist(inbdOrdNum)) {
                    setRequiredData(wmsInbdTrxTMsg);
                    errCd = "NLZM1018E";
                } else { // QC#57442 Add - End
                    // Build Insert Data
                    cnvIfData(wmsInbdTrxTMsg);

                    // To verify the data obtained from NLGI0100_01 Table
                    errCd = validateIfData(wmsInbdTrxTMsg);
                }

                if (ZYPCommonFunc.hasValue(errCd)) {
                    this.totalErrCount++;
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd, PROC_STS.ERROR);
                    ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcErrMsgCd, errCd);
                }

                // insert data into WMS_INBD_TRX
                BigDecimal inbdTrxSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ);
                ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, inbdTrxSq);
                S21FastTBLAccessor.insert(wmsInbdTrxTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(wmsInbdTrxTMsg.getReturnCode())) {
                    this.totalErrCount++;
                    outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_TRX, TBL_NLGI0100_01 //
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_TRANSACTION_ID) //
                            , NLXCMsgHelper.toListedString(wmsInbdTrxTMsg.glblCmpyCd.getValue(), tranId) });
                }

                if (!ZYPCommonFunc.hasValue(errCd)) {
                    this.normalRecCount++;
                }
            }
            trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();
        }
    }

    /**
     * Convert The Interface Data
     * @param wmsInbdTrxTMsg Interface Data
     */
    private void cnvIfData(WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, ZYPCommonFunc.leftPad(wmsInbdTrxTMsg.intfcTrxId.getValue(), LEN_PD_TRX_ID, "0"));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, ZYPCommonFunc.leftPad(wmsInbdTrxTMsg.intfcTrxSqNum.getValue(), LEN_PD_TRX_ID, "0"));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, CON_WRK_TRX_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcProcStsCd, PROC_STS.COMPLEATED);

        // K.Lee Update Start 2015/11/10 
        //if (WMS_TASK.CADJ.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue()) || WMS_TASK.STAT.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue()) //
        // || WMS_TASK.OCAN.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue()) || WMS_TASK.OCHG.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue())) {
        if (WMS_TASK.OCAN.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue())) {
        // K.Lee Update End 2015/11/10 
            // In case of STAT, OCAN set "1".
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.COMPLEATED);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        }

        String wmsGrpId_03 = wmsInbdTrxTMsg.wmsGrpId_03.getValue();
        if (ZYPCommonFunc.hasValue(wmsGrpId_03) && wmsGrpId_03.length() >= cutLg) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, wmsGrpId_03.substring(LG_CUT_WMS_GROUP_ID_03_FROM, cutLg));
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsStkStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, DEFAULT_STK_STS_CD);
        }

        // K.Lee Update Start 2015/11/10 
        //if (WMS_TASK.CADJ.equals(wmsInbdTrxTMsg.wmsTaskCd.getValue()) && (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOldStkStsCd.getValue()))) {
        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOldStkStsCd.getValue())) {
        // K.Lee Update End 2015/11/10 
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldStkStsCd, DEFAULT_STK_STS_CD);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsDmgFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOldDmgFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.otbdOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdTpCd, this.getWmsOrdTpCdForSoFromCache(wmsInbdTrxTMsg.otbdOrdNum.getValue()));
        }

        if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdTpCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdTpCd, this.getWmsOrdTpCdForPoFromCache(wmsInbdTrxTMsg.inbdOrdNum.getValue()));
        }

        BigDecimal inbdOrdLineNum = wmsInbdTrxTMsg.inbdOrdLineNum.getValue();

        // QC13318 Start
        if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdNum) && ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.whCd)) {

            ///////////////////////////////////
            // Search Original Line Num
            ///////////////////////////////////
            String searchKey = null;
            String inbdOrdTpCd =  null;
            if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.inbdOrdTpCd)) {

                inbdOrdTpCd =  this.getWmsOrdTpCdForPoFromCache(wmsInbdTrxTMsg.inbdOrdNum.getValue());

                // QC#17981 Mod.
                /*
                if(WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(inbdOrdTpCd)
                        || WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(inbdOrdTpCd)
                        || WMS_ORD_TP.OUTBOUND_ITEM_CHANGE_FOR_REMAN.equals(inbdOrdTpCd)) {
                */
                if (Arrays.asList(searchKeySoNumFromWmsOrdTp).contains(inbdOrdTpCd)) { 
                    // searchKey = soNum
                    searchKey = wmsInbdTrxTMsg.inbdOrdNum.getValue();
                } else {
                    // Get RWS Num(searchKey = rwsNum)
                    searchKey = getRwsNum(wmsInbdTrxTMsg.whCd.getValue(), wmsInbdTrxTMsg.inbdOrdNum.getValue());
                    if (!ZYPCommonFunc.hasValue(searchKey)) {
//                        if (WMS_ORD_TP.OUTBOUND_PACKAGE_CODE_CHANGE.equals(inbdOrdTpCd)) {
                        if (Arrays.asList(searchKeySoNumFromWmsOrdTpByPCC).contains(inbdOrdTpCd)) { 
                            // searchKey = soNum(Case Of SWH Change)
                            searchKey = wmsInbdTrxTMsg.inbdOrdNum.getValue();
                        }
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(searchKey)) {
                // Search Original Line Num
                BigDecimal origLineNum = getRwsOrigDtlLineNum(searchKey, inbdOrdLineNum);
                if (ZYPCommonFunc.hasValue(origLineNum)) {
                    inbdOrdLineNum = origLineNum;
                }
            }
        }
        // QC13318 End

        // Inboud Order Line Number of Tekushisu WMS is a
        // specification that multiplied by 1000.
        // Divide by 1000 for use in S21.
        if (inbdOrdLineNum != null) {
            inbdOrdLineNum = inbdOrdLineNum.divide(new BigDecimal(ORD_LINE_DIV_NUM), BigDecimal.ROUND_HALF_DOWN);
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.inbdOrdLineNum, inbdOrdLineNum);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.ordLineCpltFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.packMarkSeptFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, wmsBatId);
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, BigDecimal.ZERO);

        // A WMS_FRT_CHRG_AMT sets up null by fixation.
        wmsInbdTrxTMsg.wmsFrtChrgAmt.setValue(null);

        // QC13318 Start
        BigDecimal otbdOrdLineNum = wmsInbdTrxTMsg.otbdOrdLineNum.getValue();
        if (ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.otbdOrdNum)) {
            ///////////////////////////////////
            // Search Original Line Num
            ///////////////////////////////////
            BigDecimal origLineNum = getSoOrigDtlLineNum(wmsInbdTrxTMsg.otbdOrdNum.getValue(), otbdOrdLineNum);
            if (ZYPCommonFunc.hasValue(origLineNum)) {
                otbdOrdLineNum = origLineNum;
            }
        }
        if (otbdOrdLineNum != null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.otbdOrdLineNum, otbdOrdLineNum);
        }
        // QC13318 End
    }

    /**
     * Validate The Interface Data
     * @param wmsInbdTrxTMsg Interface Data
     * @return String Error Code
     */
    private String validateIfData(WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {

        // check if WMS Rec Id Exists
        String wmsRecId = wmsInbdTrxTMsg.wmsRecId.getValue();

        if (!ZYPCommonFunc.hasValue(wmsRecId)) {
            String intfcTrxId = wmsInbdTrxTMsg.intfcTrxId.getValue();
            outputErr(NLGM0041E, new String[] {COL_WMS_REC_ID, TBL_WMS_INBD_TRX, intfcTrxId });
            return NLGM0041E;
        }
        return "";
    }

    /**
     * Add Error ID and Message
     * @param msgId Message Id
     * @param msgParam Message Parameter
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);
        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * Get WMS_ORD_TP_CD for S/O from cache
     * @param soNum String
     * @return String WMS_ORD_TP_CD
     */
    private String getWmsOrdTpCdForSoFromCache(String soNum) {
        if (!ZYPCommonFunc.hasValue(soNum)) {
            return null;
        }
        // Get data from cache
        String wmsOrdTpCd = this.cacheMapWmsOrdTpCdForSo.get(soNum);
        if (ZYPCommonFunc.hasValue(wmsOrdTpCd)) {
            return wmsOrdTpCd;
        }
        // Get data from DB
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("soNum", soNum);

            stmt = ssmLLClient.createPreparedStatement("getWmsOrdTpCdForSo", queryParam);
            rs = stmt.executeQuery();
            if (rs.next()) {
                wmsOrdTpCd = rs.getString(COL_WMS_ORD_TP_CD);
                this.cacheMapWmsOrdTpCdForSo.put(soNum, wmsOrdTpCd);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return wmsOrdTpCd;
    }

    /**
     * Get WMS_ORD_TP_CD for P/O from cache
     * @param soNum String
     * @return String WMS_ORD_TP_CD
     */
    private String getWmsOrdTpCdForPoFromCache(String poNum) {
        if (!ZYPCommonFunc.hasValue(poNum)) {
            return null;
        }
        // Get data from cache
        String wmsOrdTpCd = this.cacheMapWmsOrdTpCdForPo.get(poNum);
        if (ZYPCommonFunc.hasValue(wmsOrdTpCd)) {
            return wmsOrdTpCd;
        }
        // Get data from DB
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("poNum", poNum);

            stmt = ssmLLClient.createPreparedStatement("getWmsOrdTpCdForPo", queryParam);
            rs = stmt.executeQuery();
            if (rs.next()) {
                wmsOrdTpCd = rs.getString(COL_WMS_PRCH_TP_CDD);
                this.cacheMapWmsOrdTpCdForPo.put(poNum, wmsOrdTpCd);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return wmsOrdTpCd;
    }

    /**
     * getRwsOrigDtlLineNum
     * @param inbdOrdNum
     * @param inbdOrdLineNum
     * @return
     */
    private BigDecimal getRwsOrigDtlLineNum(String inbdOrdNum, BigDecimal inbdOrdLineNum) {
        WMS_RWS_ORIG_LINE_SAVETMsg inMsg = new WMS_RWS_ORIG_LINE_SAVETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, inbdOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.rwsToWmsDtlLineNum, inbdOrdLineNum.toPlainString());
        WMS_RWS_ORIG_LINE_SAVETMsg outMsg = (WMS_RWS_ORIG_LINE_SAVETMsg) EZDTBLAccessor.findByKey(inMsg);

        if(outMsg != null) {
            return new BigDecimal(outMsg.rwsOrigDtlLineNum.getValue());
        } else {
            return null;
        }
    }

    /**
     * getSoOrigDtlLineNum
     * @param otbdOrdNum
     * @param otbdOrdLineNum
     * @return
     */
    private BigDecimal getSoOrigDtlLineNum(String otbdOrdNum, BigDecimal otbdOrdLineNum) {

        WMS_SO_ORIG_LINE_SAVETMsg inMsg = new WMS_SO_ORIG_LINE_SAVETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.soNum, otbdOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.soToWmsDtlLineNum, otbdOrdLineNum.toPlainString());
        WMS_SO_ORIG_LINE_SAVETMsg outMsg = (WMS_SO_ORIG_LINE_SAVETMsg) EZDTBLAccessor.findByKey(inMsg);

        if(outMsg != null) {
            return new BigDecimal(outMsg.soOrigDtlLineNum.getValue());
        } else {
            return null;
        }

    }
    /**
     * getRwsNum
     * @param whCd
     * @param inbdOrdNum
     * @return
     */
    private String getRwsNum(String whCd, String inbdOrdNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("whCd", whCd);
        ssmParam.put("wmsPoId", inbdOrdNum);

        String rwsNum = (String) ssmBatchClient.queryObject("getRwsNum", ssmParam);

        return rwsNum;
    }

    /**
     * isShpgOrdExist
     * QC#57442 add method. 
     * @param otbdOrdNum String
     * @return boolean
     */
    private boolean isShpgOrdExist(String otbdOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put("otbdOrdNum", otbdOrdNum);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("getShpgOrdCnt", queryParam);

        if (!BigDecimal.ZERO.equals(count)) {
            return true;
        }

        return false;
    }

    /**
     * isShpgOrdExist
     * QC#57442 add method. 
     * @param inbdOrdNum String
     * @return boolean
     */
    private boolean isRwsHdrExist(String inbdOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put("inbdOrdNum", inbdOrdNum);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("getRwsHdrCnt", queryParam);

        if (!BigDecimal.ZERO.equals(count)) {
            return true;
        }

        return false;
    }

    /**
     * setRequiredData
     * QC#57442 add method. 
     * @param wmsInbdTrxTMsg Interface Data
     */
    private void setRequiredData(WMS_INBD_TRXTMsg wmsInbdTrxTMsg) {
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxId, ZYPCommonFunc.leftPad(wmsInbdTrxTMsg.intfcTrxId.getValue(), LEN_PD_TRX_ID, "0"));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.intfcTrxSqNum, ZYPCommonFunc.leftPad(wmsInbdTrxTMsg.intfcTrxSqNum.getValue(), LEN_PD_TRX_ID, "0"));
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wrkTrxId, CON_WRK_TRX_ID);
        String wmsGrpId03 = wmsInbdTrxTMsg.wmsGrpId_03.getValue();
        if (ZYPCommonFunc.hasValue(wmsGrpId03) && wmsGrpId03.length() >= cutLg) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.whCd, wmsGrpId03.substring(LG_CUT_WMS_GROUP_ID_03_FROM, cutLg));
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.procStsCd, PROC_STS.IN_COMPLETED);

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsStkStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsStkStsCd, DEFAULT_STK_STS_CD);
        }
        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOldStkStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldStkStsCd, DEFAULT_STK_STS_CD);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsDmgFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsDmgFlg, ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.wmsOldDmgFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsOldDmgFlg, ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.ordLineCpltFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.ordLineCpltFlg, ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPCommonFunc.hasValue(wmsInbdTrxTMsg.packMarkSeptFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.packMarkSeptFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsBatId, wmsBatId);
        wmsInbdTrxTMsg.wmsResrcTxt.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsUpdHistNum, BigDecimal.ZERO);

        // A WMS_FRT_CHRG_AMT sets up null by fixation.
        wmsInbdTrxTMsg.wmsFrtChrgAmt.setValue(null);
    }
}
