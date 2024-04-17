/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB060001;

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
import business.db.NLBI1010_01TMsg;
import business.db.NLBI1010_02TMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
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

import com.canon.cusa.s21.batch.NLB.NLBB060001.constant.NLBB060001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
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

/**
 * <pre>    
 * Business ID : NLBB0600 Ship Parts Request to Choice
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 02/03/2016   CITS            S.Tanikawa      Create          
 * 04/14/2016   CITS            Y.IWASAKI       Update          QC#6817
 * 05/12/2016   CITS            S.Tanikawa      Update          QC#5649
 * 02/20/2017   CITS            R.Shimamoto     Update          QC#17657
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19370
 * 07/11/2017   CITS            Y.IWASAKI       Update          QC#19695
 * 10/24/2017   CITS            T.Tokutomi      Update          QC#21657-1
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 10/12/2018   CITS            T.Wada          Update          QC#28778
 * 05/20/2019   CITS            K.Ogino         Update          QC#50072
 * 06/19/2019   CITS            T.Wada          Update          QC#50866
 * 07/31/2019   CITS            M.Naito         Update          QC#52027
 * 11/28/2019   Fujitsu         R.Nakamura      Update          QC#54389
 *</pre>
 */
public class NLBB060001 extends S21BatchMain {

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

    // QC#28778 Add
    private boolean isSendPrIntfcFlg = false;

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NLBB060001().executeBatch(NLBB060001.class.getSimpleName());
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
        this.trgtWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB060001Constant.VAR_CHAR_CONST_NM_TARGET_ORDER, glblCmpyCd);
        //trgtWhOwnrCd = WH_OWNR.CHOICE;

        // PRCH_REQ_LINE_TP_CD
        this.trgtPrchReqLineTpCd = ZYPCodeDataUtil.getVarCharConstValue(NLBB060001Constant.VAR_CHAR_CONST_NM_TARGET_PR_LINE_TP_CD, glblCmpyCd);
        //trgtPrchReqLineTpCd = PRCH_REQ_LINE_TP.INSOURCED_CH;

        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // QC#28778 Add Start
        String constSendPrIntfcFlg = ZYPCodeDataUtil.getVarCharConstValue("NLBB0600_SEND_PR_INTFC_FLG", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constSendPrIntfcFlg) && ZYPConstant.FLG_ON_Y.equals(constSendPrIntfcFlg)) {
            this.isSendPrIntfcFlg = true;
        } else {
            this.isSendPrIntfcFlg = false;
        }
        // QC#28778 Add End
        
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

            commit();

        } catch (S21AbendException e) {
            rollback();
            throw e;
        } finally {
            if (errMsgList.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(NLBB060001Constant.BUSINESS_ID, errMsgList);
                commit();
            }
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = successCount + errorCount;

        // The number of cases : Insert is output
        S21InfoLogOutput.println(NLBB060001Constant.ZZBM0009I, new String[] {interfaceId, "Insert", Integer.toString(totalCount)});

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
            throw new S21AbendException(NLBB060001Constant.ZZM9000E, new String[] {NLBB060001Constant.PARAM_NM_GLBL_CMPY_CD });
        }

        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLBB060001Constant.ZZM9000E, new String[] {NLBB060001Constant.PARAM_NM_INTERFACE_ID });
        }

        // Commit Count
        String str = getUserVariable1();

        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(NLBB060001Constant.ZZM9000E, new String[] {NLBB060001Constant.PARAM_NM_VAR_USER1 });
        }
        try {
            commitCount = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new S21AbendException(NLBB060001Constant.ZZM9004E, new String[] {"Commit Count(" + str + ")" });
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
        execParam.setFetchSize(NLBB060001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get SO#
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(NLBB060001Constant.COL_WH_SYS_TP_CD, WH_SYS_TP._3PL);
            queryParam.put(NLBB060001Constant.COL_WH_OWNR_CD, this.trgtWhOwnrCd);
            queryParam.put(NLBB060001Constant.COL_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);
            queryParam.put(NLBB060001Constant.BIND_SO_CUST_DATA_TP_CD_SHIP, SO_CUST_DATA_TP.SHIP_TO);
            queryParam.put(NLBB060001Constant.BIND_SO_CUST_DATA_TP_CD_SELL, SO_CUST_DATA_TP.SELL_TO);
            queryParam.put(NLBB060001Constant.BIND_SO_CUST_DATA_TP_CD_BILL, SO_CUST_DATA_TP.BILL_TO);

            prdStmt = ssmLLClient.createPreparedStatement("findSo", queryParam, execParam);
            rs = prdStmt.executeQuery();

            int processedCount = 0;

            // Loop by SO#
            while (rs.next()) {

                String soNum = rs.getString(NLBB060001Constant.COL_SO_NUM);

                // START 2019/07/31 M.Naito [QC#52027,ADD]
                // To Canada Order is not SO download
                if (!isShipToUS(rs.getString(NLBB060001Constant.COL_SHIP_CTRY_CD), rs.getString(NLBB060001Constant.COL_SHIP_ST_CD))) {
                    // Update WMS DropRqstFlg
                    updateWmsDropRqstFlg(glblCmpyCd, soNum, ZYPConstant.FLG_OFF_N);
                    ++this.successCount;
                    continue;
                }
                // END 2019/07/31 M.Naito [QC#52027,ADD]

                // get SO Detail
                List<Map<String, Object>> soDetailList = getSoDetailList(glblCmpyCd, soNum);

                // get SO Text
                List<Map<String, Object>> soTextList = getSoTextList(glblCmpyCd, soNum);

                // Get CARR_CD and SHPG_SVC_LVL_CD
                Map<String, Object> tplCarrSvcLvl = null;
                String carrCd = rs.getString(NLBB060001Constant.COL_CARR_CD);
                String shpgSvcLvlCd = rs.getString(NLBB060001Constant.COL_SHPG_SVC_LVL_CD);
                tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(this.glblCmpyCd, this.trgtWhOwnrCd, carrCd, shpgSvcLvlCd);

                // Validation Check
                if (!validateIFDataSo(rs, soDetailList, tplCarrSvcLvl, soTextList)) {
                    rollback();
                    ++this.errorCount;
                    // updateWmsDropFlg(glblCmpyCd, soNum,
                    // ZYPConstant.FLG_OFF_N);
                    // commit();
                    processedCount = 0;
                    continue;
                }

                // Get Transaction ID.(SO)
                BigDecimal trxId = trxAccess.getNextTransactionId();

                String positionKey=NLXCMsgHelper.toListedString(NLBB060001Constant.COL_GLBL_CMPY_CD, NLBB060001Constant.COL_SO_NUM);
                String position=NLXCMsgHelper.toListedString(this.glblCmpyCd, soNum);

                // NLBI1010_01
                NLBI1010_01TMsg if01TMsg = createNLBI1010_01_SO(rs, tplCarrSvcLvl, soTextList, trxId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_01, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                    throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_01, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                }

                // NLBI1010_02
                if (soDetailList != null && soDetailList.size() > 0) {
                    List<NLBI1010_02TMsg> if02TMsgList = createNLBI1010_02_SO(soDetailList, trxId);
                    int count = S21FastTBLAccessor.insert(if02TMsgList.toArray(new NLBI1010_02TMsg[if02TMsgList.size()]));
                    if (count != if02TMsgList.size()) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_02, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_02, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_HDR
                Map<String, Object> soHeader = convertSoHeaderToMap(rs);
                WMS_INBD_SO_HDRTMsg wmsSoHdrTMsg = createWmsInbdSoHdr(soHeader, tplCarrSvcLvl, this.salesDate);
                EZDTBLAccessor.insert(wmsSoHdrTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoHdrTMsg.getReturnCode())) {
                    outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_HDR, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                    throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_HDR, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                }

                // WMS_INBD_SO_DTL
                if (soDetailList != null && soDetailList.size() > 0) {
                    List<WMS_INBD_SO_DTLTMsg> wmsSoDtlTMsgList = createWmsInbdSoDtlList(wmsSoHdrTMsg, soDetailList);
                    int count = S21FastTBLAccessor.insert(wmsSoDtlTMsgList.toArray(new WMS_INBD_SO_DTLTMsg[wmsSoDtlTMsgList.size()]));
                    if (count != wmsSoDtlTMsgList.size()) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_DTL, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_DTL, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                    }

                    // START 2017/12/20 S.Katsuma [QC#22592,ADD]
                    updateShpgOrdDtl(wmsSoDtlTMsgList);
                    // END 2017/12/20 S.Katsuma [QC#22592,ADD]
                }

                // WMS_INBD_SO_TEXT
                if (soTextList != null && soTextList.size() > 0) {
                    WMS_INBD_SO_TEXTTMsg wmsSoTextTMsg = createWmsInbdSoText(wmsSoHdrTMsg, soTextList);
                    EZDTBLAccessor.insert(wmsSoTextTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoTextTMsg.getReturnCode())) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_TEXT, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_TEXT, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_SHIP_TO
                WMS_INBD_SO_SHIP_TOTMsg wmsSoShipToTMsg = createWmsInbdSoShipTo(wmsSoHdrTMsg, soHeader);
                if (wmsSoShipToTMsg != null) {
                    EZDTBLAccessor.insert(wmsSoShipToTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoShipToTMsg.getReturnCode())) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_SO_CHRG_TO
                WMS_INBD_SO_CHRG_TOTMsg wmsSoChrgToTMsg = createWmsInbdSoChrgTo(wmsSoHdrTMsg, soHeader);
                if (wmsSoChrgToTMsg != null) {
                    EZDTBLAccessor.insert(wmsSoChrgToTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoChrgToTMsg.getReturnCode())) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // WMS_INBD_BILL_TO
                WMS_INBD_SO_BILL_TOTMsg wmsSoBillToTMsg = createWmsInbdSoBillTo(wmsSoHdrTMsg, soHeader);
                if (wmsSoBillToTMsg != null) {
                    EZDTBLAccessor.insert(wmsSoBillToTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsSoBillToTMsg.getReturnCode())) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_BILL_TO, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});

                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_WMS_INBD_SO_BILL_TO, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
                    }
                }

                // Create Transaction table.
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);

                // Update WMS send status
                updateWmsDropFlg(glblCmpyCd, soNum, ZYPConstant.FLG_ON_Y);

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
            EZDDebugOutput.println(NLBB060001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    private List<Map<String, Object>> getSoDetailList(String gCmpyCd, String soNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB060001Constant.COL_SO_NUM, soNum);
        List soDtlList = this.ssmBatchClient.queryObjectList("getSoDetail", queryParam);

        return soDtlList;
    }

    private List<Map<String, Object>> getSoTextList(String gCmpyCd, String soNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB060001Constant.COL_SO_NUM, soNum);
        queryParam.put(NLBB060001Constant.COL_SO_MSG_TP_CD, SHPG_ORD_MSG_TP.CPO_INFORMATION);
        List soTxtList = this.ssmBatchClient.queryObjectList("getSoText", queryParam);
        return soTxtList;
    }

    private boolean validateIFDataSo(ResultSet so, List<Map<String, Object>> soDetailList, Map<String, Object> tplCarrSvcLvl, List<Map<String, Object>> soTextList) throws SQLException {
        // MANDATORY CHECK
        boolean valid = true;
    
        String glblCmpyCd = so.getString(NLBB060001Constant.COL_GLBL_CMPY_CD);
        String soNum = so.getString(NLBB060001Constant.COL_SO_NUM);
        String position = NLXCMsgHelper.toListedString(glblCmpyCd, soNum);
        
        // TPL_LOC_TXT
        if (so.getString(NLBB060001Constant.COL_TPL_LOC_NM) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_TPL_LOC_NM, NLBB060001Constant.TBL_SHPG_ORD, position });
            valid = false;
        }
    
        // SHIP_NM_ALL_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01) == null && so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {"SHIP_SO_CUST_LINE_LOC_NM", NLBB060001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_ADDR_ALL_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01) == null && so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02) == null
                && so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03) == null && so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {"SHIP_SO_CUST_LINE_ADDR", NLBB060001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_CTY_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_CTY_ADDR) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_CTY_ADDR, NLBB060001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_ST_OR_PROV_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_ST_CD) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_ST_CD, NLBB060001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
        // SHIP_ZIP_OR_POST_CD_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_POST_CD) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_POST_CD, NLBB060001Constant.TBL_SHPG_ORD_CUST_DTL, position });
            valid = false;
        }
    
        for (int i = 0; i < soDetailList.size(); i++) {
            Map<String, Object> soDetail = soDetailList.get(i);
            
            String soSlpNum=(String)soDetail.get(NLBB060001Constant.COL_SO_SLP_NUM);
            position = NLXCMsgHelper.toListedString(glblCmpyCd, soNum, soSlpNum);
            
            // QTY_ORD_TXT
            BigDecimal shpgQty = (BigDecimal) soDetail.get(NLBB060001Constant.COL_SHPG_QTY);
            if (shpgQty == null || BigDecimal.ZERO.compareTo(shpgQty) >= 0) {
                outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHPG_QTY, NLBB060001Constant.TBL_SHPG_ORD_DTL, position });
                valid = false;
            }
            // ITEM_CD_TXT
            if (soDetail.get(NLBB060001Constant.COL_MDSE_CD) == null) {
                outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_MDSE_CD, NLBB060001Constant.TBL_SHPG_ORD_DTL, position });
                valid = false;
            }
        }
        return valid;
    }

    private NLBI1010_01TMsg createNLBI1010_01_SO(ResultSet so, Map<String, Object> tplCarrSvcLvl, List<Map<String, Object>> soTextList, BigDecimal trxId) throws SQLException {
        NLBI1010_01TMsg tMsg = new NLBI1010_01TMsg();
    
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
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, NLBB060001Constant.VAL_TPL_FROM_PTNR_ID);
        // TPL_TO_PTNR_ID
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, NLBB060001Constant.VAL_TPL_TO_PTNR_ID);
        // REQ_DT_TM_TS_TXT(BatProcDt)
        String batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        batProcDt += ZYPDateUtil.getCurrentSystemTime(NLBB060001Constant.FMT_HHMMSSSSS);
        ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, batProcDt);
        // ORD_ID_TXT
        if (so.getString(NLBB060001Constant.COL_SO_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, so.getString(NLBB060001Constant.COL_SO_NUM));
        }
        // CARR_CD, SHPG_SVC_LVL_CD
        if (tplCarrSvcLvl != null) {
            // CARR_CD
            if (tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_CARR_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_CARR_CD));
            }
            // SHPG_SVC_LVL_CD
            if (tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_SVC_LVL_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_SVC_LVL_CD));
            }
        }
        // TPL_ACCT_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, NLBB060001Constant.VAL_TPL_ACCT_TXT);
        // TPL_LOC_TXT
        if (so.getString(NLBB060001Constant.COL_TPL_LOC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, so.getString(NLBB060001Constant.COL_TPL_LOC_NM));
        }
        // SHIP_NM_ALL_TXT
        String shipNmAllTxt = "";
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01) != null) {
            shipNmAllTxt += so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01).toString();
        }
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02) != null) {
            shipNmAllTxt += so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02).toString();
        }
        if (ZYPCommonFunc.hasValue(shipNmAllTxt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, shipNmAllTxt);
        }
        // SHIP_ADDR_ALL_TXT
        String shipAddrAllText = "";
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01) != null) {
            shipAddrAllText += so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01).toString();
        }
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02) != null) {
            shipAddrAllText += so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02).toString();
        }
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03) != null) {
            shipAddrAllText += so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03).toString();
        }
        if (so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04) != null) {
            shipAddrAllText += so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04).toString();
        }
        if (ZYPCommonFunc.hasValue(shipAddrAllText)) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, shipAddrAllText);
        }
        // SHIP_CTY_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, so.getString(NLBB060001Constant.COL_SHIP_CTY_ADDR).toString());
        }
        // SHIP_ST_OR_PROV_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStOrProvTxt, so.getString(NLBB060001Constant.COL_SHIP_ST_CD).toString());
        }
        // SHIP_ZIP_OR_POST_CD_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipZipOrPostCdTxt, so.getString(NLBB060001Constant.COL_SHIP_POST_CD).toString());
        }
        // SHIP_CTRY_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, so.getString(NLBB060001Constant.COL_SHIP_CTRY_CD).toString());
        }
        // SHIP_CTAC_NM_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, so.getString(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_NM).toString());
        }
        // SHIP_PHO_NUM_TXT
        if (so.getString(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipPhoNumTxt, so.getString(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM).toString());
        }

        // QC#21657-1 Modify Start.
        String sceOrdTpCd = so.getString(NLBB060001Constant.COL_SCE_ORD_TP_CD);
        if(SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)){
            PRCH_REQTMsg prchReqT = getPrchReq(glblCmpyCd, so.getString(NLBB060001Constant.COL_SRC_ORD_NUM));
            if(prchReqT != null && ZYPCommonFunc.hasValue(prchReqT.shipToCustCd)){
                // [Drop Ship] Update ship to Address.
                
                String shipToLocNmAll = "";
                if (ZYPCommonFunc.hasValue(prchReqT.shipToLocNm)) {
                    shipToLocNmAll += prchReqT.shipToLocNm.getValue();
                }
                if (ZYPCommonFunc.hasValue(prchReqT.shipToAddlLocNm)) {
                    shipToLocNmAll += prchReqT.shipToAddlLocNm.getValue();
                }
                if (ZYPCommonFunc.hasValue(shipToLocNmAll)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, shipToLocNmAll);
                }
                // SHIP_ADDR_ALL_TXT
                String shipToAddrLineAll = "";
                if (ZYPCommonFunc.hasValue(prchReqT.shipToFirstLineAddr)) {
                    shipToAddrLineAll += prchReqT.shipToFirstLineAddr.getValue();
                }
                if (ZYPCommonFunc.hasValue(prchReqT.shipToScdLineAddr)) {
                    shipToAddrLineAll += prchReqT.shipToScdLineAddr.getValue();
                }
                if (ZYPCommonFunc.hasValue(prchReqT.shipToThirdLineAddr)) {
                    shipToAddrLineAll += prchReqT.shipToThirdLineAddr.getValue();
                }
                if (ZYPCommonFunc.hasValue(prchReqT.shipToFrthLineAddr)) {
                    shipToAddrLineAll += prchReqT.shipToFrthLineAddr.getValue();
                }
                if (ZYPCommonFunc.hasValue(shipToAddrLineAll)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, shipToAddrLineAll);
                }
                // SHIP_CTY_TXT
                if (ZYPCommonFunc.hasValue(prchReqT.shipToCtyAddr)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, prchReqT.shipToCtyAddr);
                }
                // SHIP_ST_OR_PROV_TXT
                if (ZYPCommonFunc.hasValue(prchReqT.shipToStCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.shipStOrProvTxt, prchReqT.shipToStCd);
                }
                // SHIP_ZIP_OR_POST_CD_TXT
                if (ZYPCommonFunc.hasValue(prchReqT.shipToPostCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.shipZipOrPostCdTxt, prchReqT.shipToPostCd);
                }
                // SHIP_CTRY_TXT
                if (ZYPCommonFunc.hasValue(prchReqT.shipToCtryCd)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, prchReqT.shipToCtryCd);
                }
                // SHIP_CTAC_NM_TXT
                // QC#50072
                if (ZYPCommonFunc.hasValue(prchReqT.ctacPsnNm)) {
                    int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
                    String ctacPsnNm = prchReqT.ctacPsnNm.getValue();
                    if (ctacPsnNm.length() > digit) {
                        ctacPsnNm = ctacPsnNm.substring(0, digit);
                    }
                    ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
                }

                tMsg.shipPhoNumTxt.clear();
            }
        }
        // QC#21657-1 Modify End.

        // ORD_CMNT_ALL_TXT
        String soMsgDescTxt = "";
        for (int i = 0; i < soTextList.size(); i++) {
            if (soTextList.get(i).get(NLBB060001Constant.COL_SO_MSG_DESC_TXT) != null) {
                soMsgDescTxt += soTextList.get(i).get(NLBB060001Constant.COL_SO_MSG_DESC_TXT).toString();
            }
        }
        if (soMsgDescTxt.length() > NLBB060001Constant.SO_TEXT_MAX_SIZE) {
            soMsgDescTxt = soMsgDescTxt.substring(0, NLBB060001Constant.SO_TEXT_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, soMsgDescTxt);
    
        // ORIG_ORD_QTY_TXT
        String origOrdQtyTxt = so.getString(NLBB060001Constant.COL_ORIG_ORD_QTY_TXT);
        ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, origOrdQtyTxt);
        // WMS_REC_ID
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB060001Constant.VAL_WMS_REC_ID_H);
    
        return tMsg;
    }

    private List<NLBI1010_02TMsg> createNLBI1010_02_SO(List<Map<String, Object>> soDetailList, BigDecimal trxId) {
        ArrayList<NLBI1010_02TMsg> tMsgList = new ArrayList<NLBI1010_02TMsg>();
    
        // Detail seqNum always starts from 2.
        int seqNum = 2;
    
        for (int i = 0; i < soDetailList.size(); ++i, ++seqNum) {
            NLBI1010_02TMsg tMsg = new NLBI1010_02TMsg();
    
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
            if (soDetail.get(NLBB060001Constant.COL_SHPG_QTY) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, soDetail.get(NLBB060001Constant.COL_SHPG_QTY).toString());
            } else {
                // Update 2016/05/12 QC#6549
                tMsg.qtyOrdTxt.clear();
            }
            // ITEM_CD_TXT
            if (soDetail.get(NLBB060001Constant.COL_MDSE_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, soDetail.get(NLBB060001Constant.COL_MDSE_CD).toString());
            } else {
                // Update 2016/05/12 QC#6549
                tMsg.itemCdTxt.clear();
            }
            // WMS_REC_ID
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB060001Constant.VAL_WMS_REC_ID_D);
    
            tMsgList.add(tMsg);
        }
    
        return tMsgList;
    }

    public void updateWmsDropFlg(String glblCmpyCd, String soNum, String wmsDropFlg) {
        String positionKey=NLXCMsgHelper.toListedString(NLBB060001Constant.COL_GLBL_CMPY_CD, NLBB060001Constant.COL_SO_NUM);
        String position=NLXCMsgHelper.toListedString(glblCmpyCd, soNum);
        
        SHPG_ORDTMsg tMsg = new SHPG_ORDTMsg();
    
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.soNum, soNum);
    
        tMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsDropFlg, wmsDropFlg);
    
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_SHPG_ORD, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
    
            throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_SHPG_ORD, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position});
        }
    }

    private void createPrIntfcRecords() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(NLBB060001Constant.FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            // Get PR#
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(NLBB060001Constant.COL_PRCH_REQ_REL_STS_CD, PRCH_REQ_REL_STS.IN_COMPLETED);
            queryParam.put(NLBB060001Constant.COL_PRCH_REQ_LINE_TP_CD, trgtPrchReqLineTpCd);
            queryParam.put(NLBB060001Constant.COL_OPEN_STS_FLG, ZYPConstant.FLG_ON_Y);

            prdStmt = ssmLLClient.createPreparedStatement("findPr", queryParam, execParam);
            rs = prdStmt.executeQuery();

            // Loop by SO#
            int processedCount = 0;

            while (rs.next()) {

                // target PR#
                String prNum = rs.getString(NLBB060001Constant.COL_PRCH_REQ_NUM);

                // get PR Detail
                List<Map<String, Object>> prDetailList = getPrDetail(glblCmpyCd, prNum);

                // QC#28778 Add
                if (isSendPrIntfcFlg) {

                    // Get TPL_CARR_CD and TPL_SVC_LVL_CD
                    Map<String, Object> tplCarrSvcLvl = null;
                    for (Map<String, Object> prDetail : prDetailList) {
    
                        String carrCd = (String) prDetail.get(NLBB060001Constant.COL_CARR_CD);
                        String shpgSvcLvlCd = (String) prDetail.get(NLBB060001Constant.COL_SHPG_SVC_LVL_CD);
                        tplCarrSvcLvl = getTplCarrSvcLvlWithEffort(this.glblCmpyCd, this.trgtWhOwnrCd, carrCd, shpgSvcLvlCd);
                        if (tplCarrSvcLvl != null) {
                            break;
                        }
                    }
    
                    // Get TPL_LOC_NM
                    TPL_LOCTMsg tplLocTMsg = null;
                    for (Map<String, Object> prDetail : prDetailList) {
    
                        String srcRtlWhCd = (String) prDetail.get(NLBB060001Constant.COL_SRC_RTL_WH_CD);
                        if (ZYPCommonFunc.hasValue(srcRtlWhCd)) {
                            tplLocTMsg = getTplLocTxt(glblCmpyCd, srcRtlWhCd);
                            if (tplLocTMsg != null && tplLocTMsg.tplLocNm != null) {
                                break;
                            }
                        }
                    }
    
                    // get PR Customer
                    Map<String, Object> prCustomer = getPrCustomer(glblCmpyCd, prNum);
    
                    // Validation Check
                    if (!validateIFDataPr(rs, prDetailList, tplCarrSvcLvl, tplLocTMsg, prCustomer, prNum)) {
                        rollback();
                        updatePrchReqRelStsCd(glblCmpyCd, prNum, prDetailList, PRCH_REQ_REL_STS.ERROR);
                        commit();
                        processedCount = 0;
                        ++this.errorCount;
                        continue;
                    }
    
                    // Get Transaction ID.(PR)
                    BigDecimal trxId = trxAccess.getNextTransactionId();
    
                    String positionKey=NLXCMsgHelper.toListedString(NLBB060001Constant.COL_GLBL_CMPY_CD, NLBB060001Constant.COL_PRCH_REQ_NUM);
                    String position=NLXCMsgHelper.toListedString(this.glblCmpyCd, prNum);
    
                    // create IF Table Data
                    NLBI1010_01TMsg if01TMsg = createNLBI1010_01_PR(rs, tplCarrSvcLvl, tplLocTMsg, prCustomer, trxId);
                    EZDTBLAccessor.insert(if01TMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_01, NLBB060001Constant.TBL_PRCH_REQ, positionKey, position});
    
                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_01, NLBB060001Constant.TBL_PRCH_REQ, positionKey, position});
                    }
    
                    List<NLBI1010_02TMsg> if02TMsgList= createNLBI1010_02_PR(prDetailList, trxId);
                    int count = S21FastTBLAccessor.insert(if02TMsgList.toArray(new NLBI1010_02TMsg[if02TMsgList.size()]));
                    if (count != if02TMsgList.size()) {
                        outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_02, NLBB060001Constant.TBL_PRCH_REQ, positionKey, position});
    
                        throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_NLBI1010_02, NLBB060001Constant.TBL_PRCH_REQ, positionKey, position});
                    }
    
                    // Create Transaction table.
                    trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);
                }

                // Update PR status
                updatePrchReqRelStsCd(glblCmpyCd, prNum, prDetailList, PRCH_REQ_REL_STS.COMPLEATED);

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
            EZDDebugOutput.println(NLBB060001Constant.CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    private List<Map<String, Object>> getPrDetail(String gCmpyCd, String prNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB060001Constant.COL_PRCH_REQ_NUM, prNum);
        // QC#17657 Add.
        queryParam.put(NLBB060001Constant.COL_PRCH_REQ_LINE_TP_CD, trgtPrchReqLineTpCd);
        List prDtlList = this.ssmBatchClient.queryObjectList("getPrDetail", queryParam);

        return prDtlList;
    }

    private Map<String, Object> getPrCustomer(String gCmpyCd, String prNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put(NLBB060001Constant.COL_PRCH_REQ_NUM, prNum);
        Map<String, Object> soCstmDtl = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrCustomer", queryParam);

        return soCstmDtl;
    }

    private TPL_LOCTMsg getTplLocTxt(String gCmpyCd, String tplLocCd) {
        TPL_LOCTMsg inMsg = new TPL_LOCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tplLocCd, tplLocCd);

        // QC#28778 Add Start
        ZYPEZDItemValueSetter.setValue(inMsg.tplStkStsCd, ZYPConstant.FLG_OFF_N);
        // QC#28778 Add End

        TPL_LOCTMsg outMsg = (TPL_LOCTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    private boolean validateIFDataPr(ResultSet pr, List<Map<String, Object>> prDetailList, Map<String, Object> tplCarrSvcLvl, TPL_LOCTMsg tplLocTMsg, Map<String, Object> prCustomer, String prNum) throws SQLException {

        // MANDATORY CHECK
        boolean flg = true;

        // TPL_LOC_TXT
        if (tplLocTMsg == null || !ZYPCommonFunc.hasValue(tplLocTMsg.tplLocNm)) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_TPL_LOC_NM, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            flg = false;
        }

        // SHIP_NM_ALL_TXT
        if (prCustomer.get(NLBB060001Constant.COL_SHIP_TO_LOC_NM) == null && prCustomer.get(NLBB060001Constant.COL_LOC_NM) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {"SHIP_TO_LOC_NM", NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {"LOC_NM", NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_ADDR_ALL_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_FIRST_LINE_ADDR) == null && pr.getString(NLBB060001Constant.COL_SHIP_TO_SCD_LINE_ADDR) == null && pr.getString(NLBB060001Constant.COL_SHIP_TO_THIRD_LINE_ADDR) == null
                && pr.getString(NLBB060001Constant.COL_SHIP_TO_FRTH_LINE_ADDR) == null && prCustomer.get(NLBB060001Constant.COL_FIRST_LINE_ADDR) == null && prCustomer.get(NLBB060001Constant.COL_SCD_LINE_ADDR) == null
                && prCustomer.get(NLBB060001Constant.COL_THIRD_LINE_ADDR) == null && prCustomer.get(NLBB060001Constant.COL_FRTH_LINE_ADDR) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_FIRST_LINE_ADDR, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_SCD_LINE_ADDR, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_THIRD_LINE_ADDR, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_FRTH_LINE_ADDR, NLBB060001Constant.TBL_PRCH_REQ, prNum });

            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_FIRST_LINE_ADDR, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SCD_LINE_ADDR, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_THIRD_LINE_ADDR, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_FRTH_LINE_ADDR, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });

            flg = false;
        }
        // SHIP_CTY_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_CTY_ADDR) == null && prCustomer.get(NLBB060001Constant.COL_CTY_ADDR) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_CTY_ADDR, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_CTY_ADDR, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_ST_OR_PROV_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_ST_CD) == null && prCustomer.get(NLBB060001Constant.COL_ST_CD) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_ST_CD, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_ST_CD, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }
        // SHIP_ZIP_OR_POST_CD_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_POST_CD) == null && prCustomer.get(NLBB060001Constant.COL_POST_CD) == null) {
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_SHIP_TO_POST_CD, NLBB060001Constant.TBL_PRCH_REQ, prNum });
            outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_POST_CD, NLBB060001Constant.TBL_SHIP_TO_CUST, prNum });
            flg = false;
        }

        for (int i = 0; i < prDetailList.size(); i++) {
            Map<String, Object> prDetail = prDetailList.get(i);
            // QTY_ORD_TXT
            // QC#17657 Mod.
            // BigDecimal ordQty = (BigDecimal)
            // prDetail.get(NLBB060001Constant.COL_ORD_QTY);
            BigDecimal ordQty = (BigDecimal) prDetail.get(NLBB060001Constant.COL_PRCH_REQ_QTY);
            if (ordQty == null || BigDecimal.ZERO.compareTo(ordQty) >= 0) {
                // outputErr(NLBB060001Constant.NLGM0041E, new
                // String[] {NLBB060001Constant.COL_ORD_QTY,
                // NLBB060001Constant.TBL_PRCH_REQ_DTL, prNum });
                outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_PRCH_REQ_QTY, NLBB060001Constant.TBL_PRCH_REQ_DTL, prNum });
                flg = false;
            }
            // ITEM_CD_TXT
            if (prDetail.get(NLBB060001Constant.COL_MDSE_CD) == null) {
                outputErr(NLBB060001Constant.NLGM0041E, new String[] {NLBB060001Constant.COL_MDSE_CD, NLBB060001Constant.TBL_PRCH_REQ_DTL, prNum });
                flg = false;
            }
        }
        return flg;
    }

    private NLBI1010_01TMsg createNLBI1010_01_PR(ResultSet pr, Map<String, Object> tplCarrSvcLvl, TPL_LOCTMsg tplLocTMsg, Map<String, Object> prCustomer, BigDecimal trxId) throws SQLException {

        NLBI1010_01TMsg tMsg = new NLBI1010_01TMsg();

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
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, NLBB060001Constant.VAL_TPL_FROM_PTNR_ID);
        // TPL_TO_PTNR_ID
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, NLBB060001Constant.VAL_TPL_TO_PTNR_ID);
        // REQ_DT_TM_TS_TXT
        String batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        batProcDt += ZYPDateUtil.getCurrentSystemTime(NLBB060001Constant.FMT_HHMMSSSSS);
        ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, batProcDt);
        // ORD_ID_TXT
        if (pr.getString(NLBB060001Constant.COL_PRCH_REQ_NUM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, pr.getString(NLBB060001Constant.COL_PRCH_REQ_NUM));
        }
        // CARR_CD, SHPG_SVC_LVL_CD
        if (tplCarrSvcLvl != null) {
            // CARR_CD
            ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_CARR_CD));
            // SHPG_SVC_LVL_CD
            ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_SVC_LVL_CD));
        }
        // TPL_ACCT_TXT
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, NLBB060001Constant.VAL_TPL_ACCT_TXT);
        // TPL_LOC_TXT
        if (tplLocTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, tplLocTMsg.tplLocNm);
        }
        // SHIP_NM_ALL_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_LOC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, pr.getString(NLBB060001Constant.COL_SHIP_TO_LOC_NM));
        } else if (prCustomer.get(NLBB060001Constant.COL_LOC_NM) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipNmAllTxt, prCustomer.get(NLBB060001Constant.COL_LOC_NM).toString());
        }
        // SHIP_ADDR_ALL_TXT
        String shipAddrAllText = "";
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_FIRST_LINE_ADDR) != null) {
            shipAddrAllText += pr.getString(NLBB060001Constant.COL_SHIP_TO_FIRST_LINE_ADDR);

            if (pr.getString(NLBB060001Constant.COL_SHIP_TO_SCD_LINE_ADDR) != null) {
                shipAddrAllText += pr.getString(NLBB060001Constant.COL_SHIP_TO_SCD_LINE_ADDR);
            }
            if (pr.getString(NLBB060001Constant.COL_SHIP_TO_THIRD_LINE_ADDR) != null) {
                shipAddrAllText += pr.getString(NLBB060001Constant.COL_SHIP_TO_THIRD_LINE_ADDR);
            }
            if (pr.getString(NLBB060001Constant.COL_SHIP_TO_FRTH_LINE_ADDR) != null) {
                shipAddrAllText += pr.getString(NLBB060001Constant.COL_SHIP_TO_FRTH_LINE_ADDR);
            }
        } else if (prCustomer.get(NLBB060001Constant.COL_FIRST_LINE_ADDR) != null) {
            shipAddrAllText += prCustomer.get(NLBB060001Constant.COL_FIRST_LINE_ADDR).toString();

            if (prCustomer.get(NLBB060001Constant.COL_SCD_LINE_ADDR) != null) {
                shipAddrAllText += prCustomer.get(NLBB060001Constant.COL_SCD_LINE_ADDR).toString();
            }
            if (prCustomer.get(NLBB060001Constant.COL_THIRD_LINE_ADDR) != null) {
                shipAddrAllText += prCustomer.get(NLBB060001Constant.COL_THIRD_LINE_ADDR).toString();
            }
            if (prCustomer.get(NLBB060001Constant.COL_FRTH_LINE_ADDR) != null) {
                shipAddrAllText += prCustomer.get(NLBB060001Constant.COL_FRTH_LINE_ADDR).toString();
            }
        }
        ZYPEZDItemValueSetter.setValue(tMsg.shipAddrAllTxt, shipAddrAllText);

        // SHIP_CTY_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, pr.getString(NLBB060001Constant.COL_SHIP_TO_CTY_ADDR));
        } else if (prCustomer.get(NLBB060001Constant.COL_CTY_ADDR) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtyTxt, prCustomer.get(NLBB060001Constant.COL_CTY_ADDR).toString());
        }
        // SHIP_ST_OR_PROV_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStOrProvTxt, pr.getString(NLBB060001Constant.COL_SHIP_TO_ST_CD));
        } else if (prCustomer.get(NLBB060001Constant.COL_ST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipStOrProvTxt, prCustomer.get(NLBB060001Constant.COL_ST_CD).toString());
        }
        // SHIP_ZIP_OR_POST_CD_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipZipOrPostCdTxt, pr.getString(NLBB060001Constant.COL_SHIP_TO_POST_CD));
        } else if (prCustomer.get(NLBB060001Constant.COL_POST_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipZipOrPostCdTxt, prCustomer.get(NLBB060001Constant.COL_POST_CD).toString());
        }
        // SHIP_CTRY_TXT
        if (pr.getString(NLBB060001Constant.COL_SHIP_TO_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, pr.getString(NLBB060001Constant.COL_SHIP_TO_CTRY_CD));
        } else if (prCustomer.get(NLBB060001Constant.COL_CTRY_CD) != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtryTxt, prCustomer.get(NLBB060001Constant.COL_CTRY_CD).toString());
        }

        // ORD_CMNT_ALL_TXT
        if (pr.getString(NLBB060001Constant.COL_DELY_ADDL_CMNT_TXT) != null) {
            // 2019.06.19 QC#50866 Mod Start
            String delyAddlCmntTxt = pr.getString(NLBB060001Constant.COL_DELY_ADDL_CMNT_TXT).replaceAll(NLBB060001Constant.REG_CRLF, NLBB060001Constant.SPACE);
//            delyAddlCmntTxt.replaceAll(NLBB060001Constant.REG_CRLF, NLBB060001Constant.SPACE);
/*            delyAddlCmntTxt.replace("\r", "ABC");
            delyAddlCmntTxt.replace("\n", "EFG");
            delyAddlCmntTxt.replace("TEST", "EFG");
*/
            ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, delyAddlCmntTxt);
            //ZYPEZDItemValueSetter.setValue(tMsg.ordCmntAllTxt, pr.getString(NLBB060001Constant.COL_DELY_ADDL_CMNT_TXT));
            // 2019.06.19 QC#50866 Mod End
        }

        // ORIG_ORD_QTY_TXT
        String origOrdQtyTxt = pr.getString(NLBB060001Constant.COL_ORIG_ORD_QTY_TXT);
        ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, origOrdQtyTxt);

        // WMS_REC_ID
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB060001Constant.VAL_WMS_REC_ID_H);
        // QC#50072
        if (ZYPCommonFunc.hasValue(pr.getString(NLBB060001Constant.COL_CTAC_PSN_NM))) {
            int digit = tMsg.getAttr("shipCtacNmTxt").getDigit();
            String ctacPsnNm = pr.getString(NLBB060001Constant.COL_CTAC_PSN_NM);
            if (ctacPsnNm.length() > digit) {
                ctacPsnNm = ctacPsnNm.substring(0, digit);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.shipCtacNmTxt, ctacPsnNm);
        }

        return tMsg;
    }

    private List<NLBI1010_02TMsg> createNLBI1010_02_PR(List<Map<String, Object>> prDetailList, BigDecimal trxId) {
        ArrayList<NLBI1010_02TMsg> tMsgList=new ArrayList<NLBI1010_02TMsg>();

        // Detail seqNum always starts from 2.
        int seqNum = 2;

        for (int i = 0; i < prDetailList.size(); ++i, ++seqNum) {
            NLBI1010_02TMsg tMsg = new NLBI1010_02TMsg();
            
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
            // QC#17657 Mod.
            // if (prDetail.get(NLBB060001Constant.COL_ORD_QTY) !=
            // null) {
            if (prDetail.get(NLBB060001Constant.COL_PRCH_REQ_QTY) != null) {
                // ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt,
                // prDetail.get(NLBB060001Constant.COL_ORD_QTY).toString());
                ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, prDetail.get(NLBB060001Constant.COL_PRCH_REQ_QTY).toString());
            }
            // ITEM_CD_TXT
            if (prDetail.get(NLBB060001Constant.COL_MDSE_CD) != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, prDetail.get(NLBB060001Constant.COL_MDSE_CD).toString());
            }
            // WMS_REC_ID
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, NLBB060001Constant.VAL_WMS_REC_ID_D);

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

        soHdr.put(NLBB060001Constant.COL_GLBL_CMPY_CD, so.getString(NLBB060001Constant.COL_GLBL_CMPY_CD));
        soHdr.put(NLBB060001Constant.COL_WH_CD, so.getString(NLBB060001Constant.COL_WH_CD));
        soHdr.put(NLBB060001Constant.COL_S80_CMPY_CD, so.getString(NLBB060001Constant.COL_S80_CMPY_CD));
        soHdr.put(NLBB060001Constant.COL_SO_NUM, so.getString(NLBB060001Constant.COL_SO_NUM));
        soHdr.put(NLBB060001Constant.COL_TRX_HDR_NUM, so.getString(NLBB060001Constant.COL_TRX_HDR_NUM));
        soHdr.put(NLBB060001Constant.COL_PICK_TKT_NUM, so.getString(NLBB060001Constant.COL_PICK_TKT_NUM));
        soHdr.put(NLBB060001Constant.COL_CUST_ISS_PO_NUM, so.getString(NLBB060001Constant.COL_CUST_ISS_PO_NUM));
        soHdr.put(NLBB060001Constant.COL_SELL_TO_CUST_CD, so.getString(NLBB060001Constant.COL_SELL_TO_CUST_CD));
        soHdr.put(NLBB060001Constant.COL_BILL_TO_CUST_CD, so.getString(NLBB060001Constant.COL_BILL_TO_CUST_CD));
        soHdr.put(NLBB060001Constant.COL_SHIP_TO_CUST_CD, so.getString(NLBB060001Constant.COL_SHIP_TO_CUST_CD));
        soHdr.put(NLBB060001Constant.COL_SCE_ORD_TP_CD, so.getString(NLBB060001Constant.COL_SCE_ORD_TP_CD));
        soHdr.put(NLBB060001Constant.COL_SCE_ORD_TP_NM, so.getString(NLBB060001Constant.COL_SCE_ORD_TP_NM));
        soHdr.put(NLBB060001Constant.COL_S80_ORD_TP_CD, so.getString(NLBB060001Constant.COL_S80_ORD_TP_CD));
        soHdr.put(NLBB060001Constant.COL_S80_TRX_CD, so.getString(NLBB060001Constant.COL_S80_TRX_CD));
        soHdr.put(NLBB060001Constant.COL_S80_ORD_SRC_CD, so.getString(NLBB060001Constant.COL_S80_ORD_SRC_CD));
        soHdr.put(NLBB060001Constant.COL_SO_SHIP_VIA_CD, so.getString(NLBB060001Constant.COL_SO_SHIP_VIA_CD));
        soHdr.put(NLBB060001Constant.COL_SHIP_VIA_DESC_TXT, so.getString(NLBB060001Constant.COL_SHIP_VIA_DESC_TXT));
        soHdr.put(NLBB060001Constant.COL_SO_CRAT_TS, so.getString(NLBB060001Constant.COL_SO_CRAT_TS));
        soHdr.put(NLBB060001Constant.COL_PSD_DT, so.getString(NLBB060001Constant.COL_PSD_DT));
        soHdr.put(NLBB060001Constant.COL_RDD_DT, so.getString(NLBB060001Constant.COL_RDD_DT));
        soHdr.put(NLBB060001Constant.COL_DNLD_TM_TS, so.getString(NLBB060001Constant.COL_DNLD_TM_TS));
        soHdr.put(NLBB060001Constant.COL_S80_SHPG_TERM_CD, so.getString(NLBB060001Constant.COL_S80_SHPG_TERM_CD));
        soHdr.put(NLBB060001Constant.COL_S80_SHPG_TERM_NM, so.getString(NLBB060001Constant.COL_S80_SHPG_TERM_NM));
        soHdr.put(NLBB060001Constant.COL_CARR_CD, so.getString(NLBB060001Constant.COL_CARR_CD));
        soHdr.put(NLBB060001Constant.COL_DROP_SHIP_FLG, so.getString(NLBB060001Constant.COL_DROP_SHIP_FLG));
        soHdr.put(NLBB060001Constant.COL_PRINT_SCC_LB_FLG, so.getString(NLBB060001Constant.COL_PRINT_SCC_LB_FLG));
        soHdr.put(NLBB060001Constant.COL_PRINT_UCC_LB_FLG, so.getString(NLBB060001Constant.COL_PRINT_UCC_LB_FLG));
        soHdr.put(NLBB060001Constant.COL_MIX_PLT_ALLW_FLG, so.getString(NLBB060001Constant.COL_MIX_PLT_ALLW_FLG));
        soHdr.put(NLBB060001Constant.COL_PRINT_PLT_UCC_LB_FLG, so.getString(NLBB060001Constant.COL_PRINT_PLT_UCC_LB_FLG));
        soHdr.put(NLBB060001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG, so.getString(NLBB060001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG));
        soHdr.put(NLBB060001Constant.COL_ASN_REQ_FLG, so.getString(NLBB060001Constant.COL_ASN_REQ_FLG));
        soHdr.put(NLBB060001Constant.COL_IND_UCC_NUM_FLG, so.getString(NLBB060001Constant.COL_IND_UCC_NUM_FLG));
        soHdr.put(NLBB060001Constant.COL_EDI_TP_CD, so.getString(NLBB060001Constant.COL_EDI_TP_CD));
        soHdr.put(NLBB060001Constant.COL_CUST_STORE_NUM, so.getString(NLBB060001Constant.COL_CUST_STORE_NUM));
        soHdr.put(NLBB060001Constant.COL_SO_DEPT_NUM, so.getString(NLBB060001Constant.COL_SO_DEPT_NUM));
        soHdr.put(NLBB060001Constant.COL_TOT_SHIP_AMT, so.getBigDecimal(NLBB060001Constant.COL_TOT_SHIP_AMT));
        soHdr.put(NLBB060001Constant.COL_TOT_WT_AMT_NUM, so.getBigDecimal(NLBB060001Constant.COL_TOT_WT_AMT_NUM));
        soHdr.put(NLBB060001Constant.COL_RTRN_LB_CD, so.getString(NLBB060001Constant.COL_RTRN_LB_CD));
        soHdr.put(NLBB060001Constant.COL_SO_CONFIG_FLG, so.getString(NLBB060001Constant.COL_SO_CONFIG_FLG));
        soHdr.put(NLBB060001Constant.COL_CARR_ACCT_NUM, so.getString(NLBB060001Constant.COL_CARR_ACCT_NUM));
        soHdr.put(NLBB060001Constant.COL_WMS_SCHD_DELY_DT, so.getString(NLBB060001Constant.COL_WMS_SCHD_DELY_DT));
        soHdr.put(NLBB060001Constant.COL_WMS_CARR_CD, so.getString(NLBB060001Constant.COL_WMS_CARR_CD));
        soHdr.put(NLBB060001Constant.COL_SHPG_SVC_LVL_CD, so.getString(NLBB060001Constant.COL_SHPG_SVC_LVL_CD));
        soHdr.put(NLBB060001Constant.COL_RTRN_ITEM_INCL_FLG, so.getString(NLBB060001Constant.COL_RTRN_ITEM_INCL_FLG));
        soHdr.put(NLBB060001Constant.COL_SVC_CONFIG_MSTR_PK, so.getBigDecimal(NLBB060001Constant.COL_SVC_CONFIG_MSTR_PK));
        soHdr.put(NLBB060001Constant.COL_PRE_ISTL_SHOP_RQST_FLG, so.getString(NLBB060001Constant.COL_PRE_ISTL_SHOP_RQST_FLG));
        soHdr.put(NLBB060001Constant.COL_STAGE_ACT_CD, so.getString(NLBB060001Constant.COL_STAGE_ACT_CD));
        soHdr.put(NLBB060001Constant.COL_WMS_RTE_PATH_CD, so.getString(NLBB060001Constant.COL_WMS_RTE_PATH_CD));
        soHdr.put(NLBB060001Constant.COL_SCE_ORD_TP_CD, so.getString(NLBB060001Constant.COL_SCE_ORD_TP_CD));
        soHdr.put(NLBB060001Constant.COL_SRC_ORD_NUM, so.getString(NLBB060001Constant.COL_SRC_ORD_NUM));
        soHdr.put(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01, so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01));
        soHdr.put(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02, so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02));
        soHdr.put(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01, so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01));
        soHdr.put(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02, so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02));
        soHdr.put(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03, so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03));
        soHdr.put(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04, so.getString(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04));
        soHdr.put(NLBB060001Constant.COL_SHIP_CTY_ADDR, so.getString(NLBB060001Constant.COL_SHIP_CTY_ADDR));
        soHdr.put(NLBB060001Constant.COL_SHIP_ST_CD, so.getString(NLBB060001Constant.COL_SHIP_ST_CD));
        soHdr.put(NLBB060001Constant.COL_SHIP_POST_CD, so.getString(NLBB060001Constant.COL_SHIP_POST_CD));
        soHdr.put(NLBB060001Constant.COL_SHIP_CTRY_CD, so.getString(NLBB060001Constant.COL_SHIP_CTRY_CD));
        soHdr.put(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_NM, so.getString(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_NM));
        soHdr.put(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM, so.getString(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM));
        soHdr.put(NLBB060001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_01, so.getString(NLBB060001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_01));
        soHdr.put(NLBB060001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_02, so.getString(NLBB060001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_02));
        soHdr.put(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_01, so.getString(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_01));
        soHdr.put(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_02, so.getString(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_02));
        soHdr.put(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_03, so.getString(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_03));
        soHdr.put(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_04, so.getString(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_04));
        soHdr.put(NLBB060001Constant.COL_SELL_CTY_ADDR, so.getString(NLBB060001Constant.COL_SELL_CTY_ADDR));
        soHdr.put(NLBB060001Constant.COL_SELL_ST_CD, so.getString(NLBB060001Constant.COL_SELL_ST_CD));
        soHdr.put(NLBB060001Constant.COL_SELL_POST_CD, so.getString(NLBB060001Constant.COL_SELL_POST_CD));
        soHdr.put(NLBB060001Constant.COL_SELL_CTRY_CD, so.getString(NLBB060001Constant.COL_SELL_CTRY_CD));
        soHdr.put(NLBB060001Constant.COL_SELL_CTAC_PTNR_PSN_NM, so.getString(NLBB060001Constant.COL_SELL_CTAC_PTNR_PSN_NM));
        soHdr.put(NLBB060001Constant.COL_SELL_CTAC_PTNR_PSN_TEL_NUM, so.getString(NLBB060001Constant.COL_SELL_CTAC_PTNR_PSN_TEL_NUM));
        soHdr.put(NLBB060001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_01, so.getString(NLBB060001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_01));
        soHdr.put(NLBB060001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_02, so.getString(NLBB060001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_02));
        soHdr.put(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_01, so.getString(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_01));
        soHdr.put(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_02, so.getString(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_02));
        soHdr.put(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_03, so.getString(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_03));
        soHdr.put(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_04, so.getString(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_04));
        soHdr.put(NLBB060001Constant.COL_BILL_CTY_ADDR, so.getString(NLBB060001Constant.COL_BILL_CTY_ADDR));
        soHdr.put(NLBB060001Constant.COL_BILL_ST_CD, so.getString(NLBB060001Constant.COL_BILL_ST_CD));
        soHdr.put(NLBB060001Constant.COL_BILL_POST_CD, so.getString(NLBB060001Constant.COL_BILL_POST_CD));
        soHdr.put(NLBB060001Constant.COL_BILL_CTRY_CD, so.getString(NLBB060001Constant.COL_BILL_CTRY_CD));
        soHdr.put(NLBB060001Constant.COL_BILL_CTAC_PTNR_PSN_NM, so.getString(NLBB060001Constant.COL_BILL_CTAC_PTNR_PSN_NM));
        soHdr.put(NLBB060001Constant.COL_BILL_CTAC_PTNR_PSN_TEL_NUM, so.getString(NLBB060001Constant.COL_BILL_CTAC_PTNR_PSN_TEL_NUM));

        return soHdr;
    }

    /**
     * @param glblCmpyCd
     * @param soNum
     * @return
     */
    private Map<String, Object> getSoHeaderForWms(String glblCmpyCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_SO_NUM, soNum);
        queryParam.put(NLBB060001Constant.COL_INBD_OTBD_CD, INBD_OTBD.OUTBOUND);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSoHeaderForWms", queryParam, execParam);
    }

    /**
     * @param glblCmpyCd
     * @param soNum
     * @return
     */
    private List<Map<String, Object>> getSoDetailForWms(String glblCmpyCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_SO_NUM, soNum);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSoDetailForWms", queryParam, execParam);
    }

    private void updatePrchReqRelStsCd(String glblCmpyCd, String prNum, List<Map<String, Object>> prDetailList, String prchReqRelStsCd) {
        String positionKey=NLXCMsgHelper.toListedString(NLBB060001Constant.COL_GLBL_CMPY_CD, NLBB060001Constant.COL_PRCH_REQ_NUM, NLBB060001Constant.COL_PRCH_REQ_LINE_NUM, NLBB060001Constant.COL_PRCH_REQ_LINE_SUB_NUM);
        
        PRCH_REQ_DTLTMsg tMsg = new PRCH_REQ_DTLTMsg();
    
        for (int i = 0; i < prDetailList.size(); i++) {
            Map<String, Object> prDetail = prDetailList.get(i);
    
            String prLineNum=(String)prDetail.get(NLBB060001Constant.COL_PRCH_REQ_LINE_NUM);
            BigDecimal prLineSubNum=(BigDecimal)prDetail.get(NLBB060001Constant.COL_PRCH_REQ_LINE_SUB_NUM);
            String position=NLXCMsgHelper.toListedString(glblCmpyCd, prNum, prLineNum, prLineSubNum);
            
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, prNum);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineNum, prLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineSubNum, prLineSubNum);
    
            tMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqRelStsCd, prchReqRelStsCd);
    
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_PRCH_REQ_DTL, NLBB060001Constant.TBL_PRCH_REQ_DTL, positionKey, position});

                throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_PRCH_REQ_DTL, NLBB060001Constant.TBL_PRCH_REQ_DTL, positionKey, position});
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

    public WMS_INBD_SO_HDRTMsg createWmsInbdSoHdr(Map<String, Object> soHdr, Map<String, Object> tplCarrSvcLvl, String salesDate) {
        WMS_INBD_SO_HDRTMsg tMsg = new WMS_INBD_SO_HDRTMsg();

        String glblCmpyCd = (String) soHdr.get(NLBB060001Constant.COL_GLBL_CMPY_CD);
        String soNum = (String) soHdr.get(NLBB060001Constant.COL_SO_NUM);

        // WMS_SQ_NUM
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
        // TPL_CARR_CD, TPL_SVC_LVL_CD
        String tplCarrCd = null;
        String tplSvcLvlCd = null;
        if (tplCarrSvcLvl != null) {
            tplCarrCd = (String) tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_CARR_CD);
            tplSvcLvlCd = (String) tplCarrSvcLvl.get(NLBB060001Constant.COL_TPL_SVC_LVL_CD);
        }
        // WMS_RQST_DT_TM_TS<-PSD_DT
        String psdDt = (String) soHdr.get(NLBB060001Constant.COL_PSD_DT);
        if (ZYPCommonFunc.hasValue(psdDt)) {
            psdDt = psdDt + "000000";
        }
        // WMS_PRINT_DT_TM_TS<-RDD_DT
        String rddDt = (String) soHdr.get(NLBB060001Constant.COL_RDD_DT);
        if (ZYPCommonFunc.hasValue(rddDt)) {
            rddDt = rddDt + "000000";
        }
        // OTBD_SRC_ORD_TP_TXT<-DS_ORD_CATG.DS_ORD_CATG_DESC_TXT/SCE_ORD_TP.SCE_ORD_TP_NM
        String otbdSrcOrdTpTxt = null;
        String sceOrdTpCd = (String) soHdr.get(NLBB060001Constant.COL_SCE_ORD_TP_CD);
        String trxHdrNum = (String) soHdr.get(NLBB060001Constant.COL_TRX_HDR_NUM);
        if (Arrays.asList(SCE_ORD_TP.DIRECT_SALES, SCE_ORD_TP.DC_TRANSFER).contains(sceOrdTpCd)) {
            Map<String, Object> dsOrdCatg = getDsOrdCatg(glblCmpyCd, trxHdrNum);
            if (dsOrdCatg != null) {
                otbdSrcOrdTpTxt = (String) dsOrdCatg.get(NLBB060001Constant.COL_DS_ORD_CATG_DESC_TXT);
            }
        } else {
            otbdSrcOrdTpTxt = (String) soHdr.get(NLBB060001Constant.COL_SCE_ORD_TP_NM);
        }
        // PRT_TO_CUST_FLG
        String prtToCustFlg = ZYPConstant.FLG_OFF_N;
        if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {
            String shipToCustCd = (String) soHdr.get(NLBB060001Constant.COL_SHIP_TO_CUST_CD);
            SHIP_TO_CUSTTMsg shipToCust = new SHIP_TO_CUSTTMsg();
            ZYPEZDItemValueSetter.setValue(shipToCust.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shipToCust.shipToCustCd, shipToCustCd);
            shipToCust = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKey(shipToCust);
            if (shipToCust != null && EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCust.getReturnCode()) && LOC_GRP.CUSTOMER.equals(shipToCust.locGrpCd)) {
                prtToCustFlg = ZYPConstant.FLG_ON_Y;
            }
        }
        // SLS_ORD_ADMIN_NM
        String slsOrdAdminNm = null;
        Map<String, Object> slsOrdAdmin = null;
        if (Arrays.asList(SCE_ORD_TP.DIRECT_SALES, SCE_ORD_TP.DC_TRANSFER).contains(sceOrdTpCd)) {
            // TRX_HDR_NUM=CPO_ORD_NUM
            slsOrdAdmin = getAdminPsnFromCPO(glblCmpyCd, trxHdrNum);
        } else if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {
            // TRX_HDR_NUM=PO_ORD_NUM
            slsOrdAdmin = getSubmtPsnFromPO(glblCmpyCd, trxHdrNum);
        } else if (Arrays.asList(SCE_ORD_TP.DISPOSAL, SCE_ORD_TP.REPAIR_SUBCONTRACT, SCE_ORD_TP.REFURBISH_EXPENSE, SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC, SCE_ORD_TP.TECH_REQUEST).contains(sceOrdTpCd)) {
            // Retrieve PRCH_REQ_NUM from SHPG_ORD_DTL
            slsOrdAdmin = getRqstPsnFromPR(glblCmpyCd, soNum);
        }
        if (slsOrdAdmin != null) {
            String psnFirstNm = (String) slsOrdAdmin.get(NLBB060001Constant.COL_PSN_FIRST_NM);
            String psnLastNm = (String) slsOrdAdmin.get(NLBB060001Constant.COL_PSN_LAST_NM);
            slsOrdAdminNm = psnFirstNm + " " + psnLastNm;
        }
        // SLS_REP_PSN_NM
        String slsRepPsnNm = null;
        // TRX_HDR_NUM=CPO_ORD_NUM
        Map<String, Object> slsRepPsn = getSlsRepPsnFromCPO(glblCmpyCd, trxHdrNum, salesDate);
        if (slsRepPsn != null) {
            String psnFirstNm = (String) slsRepPsn.get(NLBB060001Constant.COL_PSN_FIRST_NM);
            String psnLastNm = (String) slsRepPsn.get(NLBB060001Constant.COL_PSN_LAST_NM);
            slsRepPsnNm = psnFirstNm + " " + psnLastNm;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) soHdr.get(NLBB060001Constant.COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) soHdr.get(NLBB060001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB060001Constant.VAL_INTFC_REC_TP_ID_HDR);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String) soHdr.get(NLBB060001Constant.COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB060001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdNum, (String) soHdr.get(NLBB060001Constant.COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.altDocNum, (String) soHdr.get(NLBB060001Constant.COL_PICK_TKT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.custOrdNum, adjustString((String) soHdr.get(NLBB060001Constant.COL_CUST_ISS_PO_NUM), 30));
        ZYPEZDItemValueSetter.setValue(tMsg.chrgToCustCd, (String) soHdr.get(NLBB060001Constant.COL_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, (String) soHdr.get(NLBB060001Constant.COL_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, (String) soHdr.get(NLBB060001Constant.COL_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrtyCd, NLBB060001Constant.VAL_WMS_PRTY_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdTpCd, (String) soHdr.get(NLBB060001Constant.COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxCd, (String) soHdr.get(NLBB060001Constant.COL_S80_TRX_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdSrcCd, (String) soHdr.get(NLBB060001Constant.COL_S80_ORD_SRC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoStsCd, NLBB060001Constant.VAL_WMS_SO_STS_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.soShipViaCd, (String) soHdr.get(NLBB060001Constant.COL_SO_SHIP_VIA_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipViaDescTxt, (String) soHdr.get(NLBB060001Constant.COL_SHIP_VIA_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cratDtTmTs, adjustString((String) soHdr.get(NLBB060001Constant.COL_SO_CRAT_TS), 14));
        ZYPEZDItemValueSetter.setValue(tMsg.estShipDtTmTs, psdDt);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRqstDtTmTs, rddDt);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrintDtTmTs, (String) soHdr.get(NLBB060001Constant.COL_DNLD_TM_TS));
        ZYPEZDItemValueSetter.setValue(tMsg.frtOutCd, (String) soHdr.get(NLBB060001Constant.COL_S80_SHPG_TERM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frtOutDescTxt, (String) soHdr.get(NLBB060001Constant.COL_S80_SHPG_TERM_NM));
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsDeptCd, );
        // ZYPEZDItemValueSetter.setValue(tMsg.payTermCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoCarrCd, (String) soHdr.get(NLBB060001Constant.COL_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.indOtmAddrSwthFlg, (String) soHdr.get(NLBB060001Constant.COL_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indScc14Flg, (String) soHdr.get(NLBB060001Constant.COL_PRINT_SCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indUccFlg, (String) soHdr.get(NLBB060001Constant.COL_PRINT_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indMixedPltFlg, (String) soHdr.get(NLBB060001Constant.COL_MIX_PLT_ALLW_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indPltLbFlg, (String) soHdr.get(NLBB060001Constant.COL_PRINT_PLT_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.sccNumFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.indNonAsnFlg, (String) soHdr.get(NLBB060001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indAsnFlg, (String) soHdr.get(NLBB060001Constant.COL_ASN_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.indUccNumFlg, (String) soHdr.get(NLBB060001Constant.COL_IND_UCC_NUM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsLbNum, (String) soHdr.get(NLBB060001Constant.COL_EDI_TP_CD));
        // ZYPEZDItemValueSetter.setValue(tMsg.cancByDtTmTs, );
        ZYPEZDItemValueSetter.setValue(tMsg.custStoreNum, (String) soHdr.get(NLBB060001Constant.COL_CUST_STORE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.custDcNum, adjustString((String) soHdr.get(NLBB060001Constant.COL_CUST_STORE_NUM), 8));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustDeptNum, (String) soHdr.get(NLBB060001Constant.COL_SO_DEPT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsConslFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.totShipPrcAmtNum, adjustBigDecimal((BigDecimal) soHdr.get(NLBB060001Constant.COL_TOT_SHIP_AMT), 15, 2));
        ZYPEZDItemValueSetter.setValue(tMsg.totWtAmtNum, adjustBigDecimal((BigDecimal) soHdr.get(NLBB060001Constant.COL_TOT_WT_AMT_NUM), 15, 4));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnLbCd, (String) soHdr.get(NLBB060001Constant.COL_RTRN_LB_CD));
        // ZYPEZDItemValueSetter.setValue(tMsg.tpVndCd, );
        // ZYPEZDItemValueSetter.setValue(tMsg.ediTrnspTpCd, );
        // ZYPEZDItemValueSetter.setValue(tMsg.wmsPmtTermCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.indConfigFlg, (String) soHdr.get(NLBB060001Constant.COL_SO_CONFIG_FLG));
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
        ZYPEZDItemValueSetter.setValue(tMsg.billAcctNum, (String) soHdr.get(NLBB060001Constant.COL_CARR_ACCT_NUM));
        // ZYPEZDItemValueSetter.setValue(tMsg.endCustOrdNum, );
        // ZYPEZDItemValueSetter.setValue(tMsg.altCustOrdNum, );
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) soHdr.get(NLBB060001Constant.COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.schdDelyDt, (String) soHdr.get(NLBB060001Constant.COL_WMS_SCHD_DELY_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.carrCd, (String) soHdr.get(NLBB060001Constant.COL_WMS_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, (String) soHdr.get(NLBB060001Constant.COL_SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, (String) soHdr.get(NLBB060001Constant.COL_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.usrCdIstlRefTxt, (String) soHdr.get(NLBB060001Constant.COL_SO_CONFIG_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnItemInclFlg, (String) soHdr.get(NLBB060001Constant.COL_RTRN_ITEM_INCL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, (BigDecimal) soHdr.get(NLBB060001Constant.COL_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.asmReqFlg, (String) soHdr.get(NLBB060001Constant.COL_PRE_ISTL_SHOP_RQST_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.stageActCd, (String) soHdr.get(NLBB060001Constant.COL_STAGE_ACT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stageRecStsCd, NLBB060001Constant.VAL_STAGE_REC_STS_CD);
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
        ZYPEZDItemValueSetter.setValue(tMsg.rtePathCd, (String) soHdr.get(NLBB060001Constant.COL_WMS_RTE_PATH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnTagReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.otbdSrcOrdTpTxt, adjustString(otbdSrcOrdTpTxt, 30));
        ZYPEZDItemValueSetter.setValue(tMsg.prtToCustFlg, prtToCustFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.otbdSrcOrdNum, (String) soHdr.get(NLBB060001Constant.COL_SRC_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.slsOrdAdminNm, adjustString(slsOrdAdminNm, 20));
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepPsnNm, adjustString(slsRepPsnNm, 20));
        // ZYPEZDItemValueSetter.setValue(tMsg.svcLbFmtTxt, );

        return tMsg;
    }

    public List<WMS_INBD_SO_DTLTMsg> createWmsInbdSoDtlList(WMS_INBD_SO_HDRTMsg hdrTMsg, List<Map<String, Object>> soDtlList) {
        ArrayList<WMS_INBD_SO_DTLTMsg> tMsgList = new ArrayList<WMS_INBD_SO_DTLTMsg>();

        for (Map<String, Object> soDtl : soDtlList) {
            WMS_INBD_SO_DTLTMsg tMsg = new WMS_INBD_SO_DTLTMsg();

            // SO_SLP_NUM
            BigDecimal soSlpNum = new BigDecimal((String) soDtl.get(NLBB060001Constant.COL_SO_SLP_NUM));

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsLineNum, soSlpNum);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB060001Constant.VAL_INTFC_TP_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB060001Constant.VAL_INTFC_REC_TP_ID_DTL);
            // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soDtl.get(NLBB060001Constant.COL_SO_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) soDtl.get(NLBB060001Constant.COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.s80StkStsCd, (String) soDtl.get(NLBB060001Constant.COL_S80_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd, (String) soDtl.get(NLBB060001Constant.COL_CUST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdQty, (BigDecimal) soDtl.get(NLBB060001Constant.COL_RQST_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.backOrdQtyNum, (BigDecimal) soDtl.get(NLBB060001Constant.COL_SHPG_BAL_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsShipQty, (BigDecimal) soDtl.get(NLBB060001Constant.COL_SHPG_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.unitPrcAmtNum, adjustBigDecimal((BigDecimal) soDtl.get(NLBB060001Constant.COL_UNIT_PRC_AMT), 15, 2));
            ZYPEZDItemValueSetter.setValue(tMsg.unitDiscAmtNum, adjustBigDecimal((BigDecimal) soDtl.get(NLBB060001Constant.COL_DISC_PRC_AMT), 15, 2));
            ZYPEZDItemValueSetter.setValue(tMsg.unitDiscPrcAmtNum, adjustBigDecimal((BigDecimal) soDtl.get(NLBB060001Constant.COL_DISC_UNIT_PRC_AMT), 15, 2));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsTotAmtNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tMsg.indSerId, (String) soDtl.get(NLBB060001Constant.COL_SER_NUM_TAKE_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.indVoidAllwFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.s80StkStsCdToCd, (String) soDtl.get(NLBB060001Constant.COL_S80_STK_STS_TO_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCdSetCd, (String) soDtl.get(NLBB060001Constant.COL_SET_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCdSetDescTxt, (String) soDtl.get(NLBB060001Constant.COL_SET_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.shipSetQty, (BigDecimal) soDtl.get(NLBB060001Constant.COL_SET_SHPG_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.unitInsdQty, (BigDecimal) soDtl.get(NLBB060001Constant.COL_IN_EACH_QTY));
            // ZYPEZDItemValueSetter.setValue(tMsg.essPoSqNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.essMdseLineNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.essLineNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.essMsgLineNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.totWtAmtNum, (BigDecimal) soDtl.get(NLBB060001Constant.COL_TOT_SHPG_WT));
            ZYPEZDItemValueSetter.setValue(tMsg.totVolAmtNum, (BigDecimal) soDtl.get(NLBB060001Constant.COL_TOT_SHPG_VOL));
            // ZYPEZDItemValueSetter.setValue(tMsg.estCseAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.estPltAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.soMdseTpCd, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsPackTpCd, );
            ZYPEZDItemValueSetter.setValue(tMsg.batCptrReqFlg, (String) soDtl.get(NLBB060001Constant.COL_BAT_NUM_TAKE_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.indConfigFlg, (String) soDtl.get(NLBB060001Constant.COL_CONFIG_ITEM_FLG));
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsNetAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsDiscAmtNum, );
            // ZYPEZDItemValueSetter.setValue(tMsg.wmsTaxAmtNum, );
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) soDtl.get(NLBB060001Constant.COL_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String) soDtl.get(NLBB060001Constant.COL_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, (BigDecimal) soDtl.get(NLBB060001Constant.COL_PICK_SVC_CONFIG_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(tMsg.backOrdImpctTpCd, (String) soDtl.get(NLBB060001Constant.COL_BACK_ORD_IMPCT_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdUomCd, WMS_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(tMsg.poLineTxt, (String) soDtl.get(NLBB060001Constant.COL_TRX_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.usrCdIstlRefTxt, (String) soDtl.get(NLBB060001Constant.COL_CONFIG_ITEM_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.backOrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.rmvConfigFlg, (String) soDtl.get(NLBB060001Constant.COL_RMV_CONFIG_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.origOrdQty, (BigDecimal) soDtl.get(NLBB060001Constant.COL_ORIG_ORD_QTY));

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    public WMS_INBD_SO_TEXTTMsg createWmsInbdSoText(WMS_INBD_SO_HDRTMsg hdrTMsg, List<Map<String, Object>> soTextList) {
        WMS_INBD_SO_TEXTTMsg tMsg = new WMS_INBD_SO_TEXTTMsg();

        Map<String, Object> soText = soTextList.get(0);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTxtCd, (String) soText.get(NLBB060001Constant.COL_SO_MSG_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB060001Constant.VAL_INTFC_REC_TP_ID_TEXT);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soText.get(NLBB060001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrintTpCd, NLBB060001Constant.VAL_WMS_PRINT_TP_CD);

        EZDTStringItem[] fieldItems = new EZDTStringItem[] {tMsg.inbdSoMsgTxt_01, tMsg.inbdSoMsgTxt_02, tMsg.inbdSoMsgTxt_03, tMsg.inbdSoMsgTxt_04 };
        for (int n = 0; n < soTextList.size() && n < fieldItems.length; ++n) {
            String text = (String) soTextList.get(n).get(NLBB060001Constant.COL_SO_MSG_DESC_TXT);
            if (ZYPCommonFunc.hasValue(text)) {
                ZYPEZDItemValueSetter.setValue(fieldItems[n], text);
            }
        }

        return tMsg;
    }

    public WMS_INBD_SO_SHIP_TOTMsg createWmsInbdSoShipTo(WMS_INBD_SO_HDRTMsg hdrTMsg, Map<String, Object> soHdr) {
        WMS_INBD_SO_SHIP_TOTMsg tMsg = new WMS_INBD_SO_SHIP_TOTMsg();

        String shipToCustCd = (String) soHdr.get(NLBB060001Constant.COL_SHIP_TO_CUST_CD);
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB060001Constant.VAL_INTFC_REC_TP_ID_SHIPTO);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB060001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsShipToNm_01, adjustString((String) soHdr.get(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_01), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsShipToNm_02, adjustString((String) soHdr.get(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_LOC_NM_02), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SHIP_SO_CUST_LINE_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) soHdr.get(NLBB060001Constant.COL_SHIP_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) soHdr.get(NLBB060001Constant.COL_SHIP_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) soHdr.get(NLBB060001Constant.COL_SHIP_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) soHdr.get(NLBB060001Constant.COL_SHIP_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsShipToCtacNm, (String) soHdr.get(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCtacNum, adjustString((String) soHdr.get(NLBB060001Constant.COL_SHIP_CTAC_PTNR_PSN_TEL_NUM), 15));

        return tMsg;
    }

    public WMS_INBD_SO_CHRG_TOTMsg createWmsInbdSoChrgTo(WMS_INBD_SO_HDRTMsg hdrTMsg, Map<String, Object> soHdr) {
        WMS_INBD_SO_CHRG_TOTMsg tMsg = new WMS_INBD_SO_CHRG_TOTMsg();

        String sellToCustCd = (String) soHdr.get(NLBB060001Constant.COL_SELL_TO_CUST_CD);
        if (!ZYPCommonFunc.hasValue(sellToCustCd)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB060001Constant.VAL_INTFC_REC_TP_ID_CHRGTO);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB060001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustCd, sellToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsChrgToNm_01, adjustString((String) soHdr.get(NLBB060001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_01), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsChrgToNm_02, adjustString((String) soHdr.get(NLBB060001Constant.COL_SELL_SO_CUST_LINE_LOC_NM_02), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) soHdr.get(NLBB060001Constant.COL_SELL_SO_CUST_LINE_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) soHdr.get(NLBB060001Constant.COL_SELL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) soHdr.get(NLBB060001Constant.COL_SELL_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) soHdr.get(NLBB060001Constant.COL_SELL_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) soHdr.get(NLBB060001Constant.COL_SELL_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsChrgToCtacNm, (String) soHdr.get(NLBB060001Constant.COL_SELL_CTAC_PTNR_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.chrgToCtacNum, adjustString((String) soHdr.get(NLBB060001Constant.COL_SELL_CTAC_PTNR_PSN_TEL_NUM), 15));

        return tMsg;
    }

    public WMS_INBD_SO_BILL_TOTMsg createWmsInbdSoBillTo(WMS_INBD_SO_HDRTMsg hdrTMsg, Map<String, Object> soHdr) {
        WMS_INBD_SO_BILL_TOTMsg tMsg = new WMS_INBD_SO_BILL_TOTMsg();

        String billToCustCd = (String) soHdr.get(NLBB060001Constant.COL_BILL_TO_CUST_CD);
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, hdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, NLBB060001Constant.VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, NLBB060001Constant.VAL_INTFC_REC_TP_ID_BILLTO);
        // ZYPEZDItemValueSetter.setValue(tMsg.intfcRecActCd, );
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, hdrTMsg.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSoId, (String) soHdr.get(NLBB060001Constant.COL_SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBillToNm_01, adjustString((String) soHdr.get(NLBB060001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_01), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBillToNm_02, adjustString((String) soHdr.get(NLBB060001Constant.COL_BILL_SO_CUST_LINE_LOC_NM_02), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) soHdr.get(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) soHdr.get(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) soHdr.get(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) soHdr.get(NLBB060001Constant.COL_BILL_SO_CUST_LINE_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) soHdr.get(NLBB060001Constant.COL_BILL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) soHdr.get(NLBB060001Constant.COL_BILL_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) soHdr.get(NLBB060001Constant.COL_BILL_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) soHdr.get(NLBB060001Constant.COL_BILL_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBillToCtacNm, (String) soHdr.get(NLBB060001Constant.COL_BILL_CTAC_PTNR_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCtacNum, adjustString((String) soHdr.get(NLBB060001Constant.COL_BILL_CTAC_PTNR_PSN_TEL_NUM), 15));

        return tMsg;
    }

    /**
     * getTplCarrSvcLvl
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    private Map<String, Object> getTplCarrSvcLvl(String glblCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_WH_OWNR_CD, whOwnrCd);
        queryParam.put(NLBB060001Constant.COL_CARR_CD, carrCd);
        queryParam.put(NLBB060001Constant.COL_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam, execParam);
    }

    /**
     * getTplCarrSvcLvlWithEffort
     * @param glblCmpyCd
     * @param whOwnrCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @return
     */
    public Map<String, Object> getTplCarrSvcLvlWithEffort(String glblCmpyCd, String whOwnrCd, String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> tplCarrSvcLvl = null;

        tplCarrSvcLvl = getTplCarrSvcLvl(glblCmpyCd, whOwnrCd, carrCd, shpgSvcLvlCd);
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(glblCmpyCd, whOwnrCd, null, shpgSvcLvlCd);
        }
        if (tplCarrSvcLvl == null) {
            tplCarrSvcLvl = getTplCarrSvcLvl(glblCmpyCd, whOwnrCd, null, null);
        }

        return tplCarrSvcLvl;
    }

    private Map<String, Object> getDsOrdCatg(String glblCmpyCd, String cpoOrdNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_CPO_ORD_NUM, cpoOrdNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getDsOrdCatg", queryParam, execParam);
    }

    private Map<String, Object> getAdminPsnFromCPO(String glblCmpyCd, String cpoOrdNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_CPO_ORD_NUM, cpoOrdNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getAdminPsnFromCPO", queryParam, execParam);
    }

    private Map<String, Object> getSubmtPsnFromPO(String glblCmpyCd, String poOrdNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_PO_ORD_NUM, poOrdNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSubmtPsnFromPO", queryParam, execParam);
    }

    private Map<String, Object> getRqstPsnFromPR(String glblCmpyCd, String soNum) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_SO_NUM, soNum);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRqstPsnFromPR", queryParam, execParam);
    }

    private Map<String, Object> getSlsRepPsnFromCPO(String glblCmpyCd, String cpoOrdNum, String salesDate) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NLBB060001Constant.COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NLBB060001Constant.COL_CPO_ORD_NUM, cpoOrdNum);
        queryParam.put(NLBB060001Constant.COL_MIN_DATE, NLBB060001Constant.VAL_MIN_DATE);
        queryParam.put(NLBB060001Constant.COL_MAX_DATE, NLBB060001Constant.VAL_MAX_DATE);
        queryParam.put(NLBB060001Constant.BIND_SALES_DATE, salesDate);

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
    
    /**
     * getPrchReq
     * QC#21657-1 
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

    // START 2017/12/20 S.Katsuma [QC#22592,ADD]
    private boolean updateShpgOrdDtl(List<WMS_INBD_SO_DTLTMsg> dtlList) {
        for (WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg: dtlList) {
            updateShpgOrdDtl(wmsInbdSoDtlTMsg);
        }

        return true;
    }

    private boolean updateShpgOrdDtl(WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg) {
        SHPG_ORD_DTLTMsg soDtlT = new SHPG_ORD_DTLTMsg();
        String soSlpNum = ZYPCommonFunc.leftPad(String.valueOf(wmsInbdSoDtlTMsg.wmsLineNum.getValue()), 3,"0");
        ZYPEZDItemValueSetter.setValue(soDtlT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(soDtlT.soNum, wmsInbdSoDtlTMsg.wmsSoId.getValue());
        ZYPEZDItemValueSetter.setValue(soDtlT.soSlpNum, soSlpNum);
        soDtlT = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(soDtlT);

        // Mod Start 2019/11/28 QC#54389
//        if (soDtlT != null) {
        if (soDtlT != null && ZYPCommonFunc.hasValue(soDtlT.shpgStsCd) && soDtlT.shpgStsCd.getValue().equals(SHPG_STS.S_OR_O_PRINTED)) {
            soDtlT.dsSoLineStsCd.setValue(DS_SO_LINE_STS.AWAITING_PICK_CONFIRMATION);
            EZDTBLAccessor.update(soDtlT);
        }
        // Mod End 2019/11/28 QC#54389

        return true;
    }
    // END 2017/12/20 S.Katsuma [QC#22592,ADD]

    // START 2019/07/31 M.Naito [QC#52027,ADD]
    private boolean isShipToUS(String shipCtryCd, String stCd) {

        if (ZYPCommonFunc.hasValue(shipCtryCd) && ZYPCommonFunc.hasValue(stCd)) {
            if (CTRY.UNITED_STATES_OF_AMERICA.equals(shipCtryCd)) {
                return true;
            }
        }
        return false;
    }

    private void updateWmsDropRqstFlg(String glblCmpyCd, String soNum, String wmsDropRqstFlg) {
        String positionKey = NLXCMsgHelper.toListedString(NLBB060001Constant.COL_GLBL_CMPY_CD, NLBB060001Constant.COL_SO_NUM);
        String position = NLXCMsgHelper.toListedString(glblCmpyCd, soNum);

        SHPG_ORDTMsg tMsg = new SHPG_ORDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.soNum, soNum);

        tMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsDropRqstFlg, wmsDropRqstFlg);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            outputErr(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_SHPG_ORD, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position });

            throw new S21AbendException(NLBB060001Constant.NLGM0045E, new String[] {NLBB060001Constant.TBL_SHPG_ORD, NLBB060001Constant.TBL_SHPG_ORD, positionKey, position });
        }
    }
    // END 2019/07/31 M.Naito [QC#52027,ADD]
}
