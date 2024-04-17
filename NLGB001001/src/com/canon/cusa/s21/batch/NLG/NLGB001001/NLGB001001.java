/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB001001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CPO_DTLTMsg;
import business.db.NLGI1100_01TMsg;
import business.db.NLGI1100_02TMsg;
import business.db.NLGI1100_03TMsg;
import business.db.NLGI1100_04TMsg;
import business.db.NLGI2100_01TMsg;
import business.db.NLGI2100_04TMsg;
import business.db.NLGI2100_05TMsg;
import business.db.NLGI2100_06TMsg;
import business.db.PRCH_REQTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SHPG_ORD_MSGTMsg;
import business.db.SHPG_ORD_MSGTMsgArray;
import business.db.WMS_CTRY_CTACTMsg;
import business.db.WMS_FRT_OUT_CD_MAPTMsg;
import business.db.WMS_INBD_SO_BILL_TOTMsg;
import business.db.WMS_INBD_SO_CHRG_TOTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_SO_RTRN_TOTMsg;
import business.db.WMS_INBD_SO_SHIP_TOTMsg;
import business.db.WMS_INBD_SO_TEXTTMsg;
import business.db.WMS_INTFC_CTRLTMsg;
import business.db.WMS_RWS_ORIG_LINE_SAVETMsg;
import business.db.WMS_SHIP_VIA_RTE_MAPTMsg;
import business.db.WMS_SO_ORIG_LINE_SAVETMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TXT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmIntegerResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * WMS SO Download
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/26/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 11/10/2015   CSAI            K.Lee           Update          S21NA Initial
 * 04/19/2016   CSAI            D.Fukaya        Update          QC#5252
 * 05/17/2016   CSAI            D.Fukaya        Update          QC#7224
 * 08/02/2016   CSAI            K.Lee           Update          QC#12671
 * 09/09/2016   CITS            T.Wada          Update          QC#14424
 * 09/12/2016   CITS            T.Wada          Update          QC#13318
 * 12/02/2016   CITS            R.Shimamoto     Update          QC#14398
 * 12/06/2016   CITS            R.Shimamoto     Update          QC#15084
 * 12/12/2016   CITS            R.Shimamoto     Update          QC#14398-2
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 05/11/2017   CITS            T.Kikuhara      Update          RS#3126
 * 06/14/2017   CITS            T.Tokutomi      Update          QC#19109
 * 06/23/2017   CITS            T.Kikuhara      Update          QC#19529
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 * 07/18/2017   CITS            K.Ogino         Update          QC#19967
 * 07/26/2017   CITS            T.Tokutomi      Update          QC#20124
 * 07/27/2017   CITS            Y.Iwasaki       Update          QC#20077
 * 07/31/2017   CITS            S.Endo          Update          Sol#169(QC#12030)
 * 08/01/2017   CITS            T.Tokutomi      Update          QC#20176
 * 10/24/2017   CITS            T.Tokutomi      Update          QC#21657-1
 * 11/02/2017   CITS            S.Katsuma       Update          SOL#170(QC#18624)
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 03/05/2017   CITS            T.Gotoda        Update          QC#24254
 * 03/08/2017   CITS            K.Ogino         Update          QC#15629
 * 03/16/2018   CITS            K.Ogino         Update          QC#24941
 * 05/11/2018   CITS            S.Katsuma       Update          QC#24714
 * 06/26/2018   CITS            T,Hakodate      UPDATE          QC#15674
 * 03/08/2019   CITS            K.Ogino         Update          QC#29011
 * 08/07/2019   Fujitsu         T.Ogura         Update          QC#52399
 * 12/12/2019   CITS            T.Wada          Update          QC#54989
 *</pre>
 */
public class NLGB001001 extends S21BatchMain implements NLGB001001Constant {

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd = null;

    /** User Profile */
    private S21UserProfileService profile = null;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Error Message List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** caching WMS_ORD_TP_XPND_CD */
    private S21LRUMap<String, String> cacheMapWmsOrdTpXpndCd = new S21LRUMap<String, String>(200);

    // QC#17981 Add.
    /** NLGB0010 Create Ship To Not Setting WtAndShipVia */
    private String[] notCrtWtandShipViaWmsOrdCd = null;

    /** NLGB0010 Create Return To Not Setting */
    private String[] notCrtRtrnToWmsOrdCd = null;

    /** NLGB0010 Regist SO and PO */
    private String[] regSoPoWmsOrdCd = null;

    /** NLGB0010 Regist PO */
    private String[] regPoWmsOrdCd = null;

    /** NLGB0010 SO Detail Not Setting Caseandpallet */
    private String[] notCaseandPalletWmsOrdCd = null;

    /** NLGB0010 Setting Outbound Kitting For NLGI1100 */
    private String[] setNlgi1100OtbdKittingWmsOrdCd = null;

    /** NLGB0010 Setting Outbound Item Change For NLGI1100 */
    private String[] setNlgi1100OtbdItemChangeWmsOrdCd = null;

    /** NLGB0010 Setting Outbound Item Change For NLGI1100 */
    private String[] setNlgi1100InbdItemChangeWmsOrdCd = null;

    /** NLGB0010 Setting Outbound Export For NLGI1100 */
    private String[] setNlgi1100OtbdExportWmsOrdCd = null;

    /** NLGB0010 Not Set Inbound Item Change For NLGI1100 */
    private String[] notSetNlgi1100InbdItemChangeWmsOrdCd = null;

    // START 2018/05/11 S.Katsuma [QC#24714,ADD]
    /** NLGB0010 Setting Outbound Reman For NLGI1100 */
    private String[] setNlgi1100OtbdRemanWmsOrdCd = null;
    // END 2018/05/11 S.Katsuma [QC#24714,ADD]

    /** NLGB0010 Not Create NLGI2100_05 */
    private String[] notCrtNlgi210005WmsOrdCd = null;

    /** NLGB0010 Create NLGI2100_05 Setting ORD_CMNT_TXT */
    private String[] crtSetOrdCmntTxtWmsOrdCd = null;

    /** NLGB0010 Is Create PO/IF04 */
    private String[] isCreatePoIf04WmsOrdCd = null;

    @Override
    protected void initRoutine() {

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        totalCommitCount = 0;
        totalErrCount = 0;
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();
        profile = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = profile.getGlobalCompanyCode();
        this.createCacheForWmsOrdTpXpndCd();
    }

    @Override
    protected void mainRoutine() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        String whGpCd = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        try {
            String[] trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);
            if (trgtWhCdAry != null) {
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("notDrop", ZYPConstant.FLG_OFF_N);
                queryParam.put("dropReady", ZYPConstant.FLG_ON_Y);
                queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
                queryParam.put("wms", WH_SYS_TP.WMS);
                queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
                queryParam.put("shipToCustCd", VAL_SHIP_TO_CUST_CD);
                queryParam.put("outbound", VAL_OUTBOUND);
                queryParam.put("lgSoCratTs", LG_SO_CRAT_TS);
                queryParam.put("val1", VAL_1);
                queryParam.put("val2", VAL_2);
                queryParam.put("valTs", VAL_000000);
                queryParam.put("maxTotShipAmt", VAL_MAX_TOT_SHIP_PRC_AMT_NUM);
                queryParam.put("lgCustIssPoNum", MAX_LENGTH_CUST_ISS_PO_NUM);
                queryParam.put("lgOtbdSrcOrdTpTxt", MAX_LENGTH_OTBD_SRC_ORD_TP_TXT);
                queryParam.put("lgPsnNm", VAL_PSN_NM_SIZE);
                queryParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
                queryParam.put("minDt", VAL_MIN_DATE);
                queryParam.put("maxDt", VAL_MAX_DATE);
                ssmBatchClient.queryObject("getSo", queryParam, new SoToWms());
            } else {
                outputErr(NLGM0047E, new String[] {whGpCd });
            }
        } finally {
            if (!errMsgList.isEmpty()) {
                NLXMailSend mailSender = new NLXMailSend(glblCmpyCd);
                mailSender.send(BUSINESS_ID, errMsgList);
                commit();
                termCd = TERM_CD.WARNING_END;
            }
        }
    }

    @Override
    protected void termRoutine() {

        int totalCount = totalCommitCount + totalErrCount;
        setTermState(termCd, totalCommitCount, totalErrCount, totalCount);
    }

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {

        // S21BatchMain format
        new NLGB001001().executeBatch(NLGB001001.class.getSimpleName());
    }

    /**
     * SO Download to WMS
     */
    private class SoToWms extends S21SsmIntegerResultSetHandlerSupport {

        /** Return Commit Count */
        private int rtrnCommitCount = 0;

        /** S21TransactionTableAccessor */
        private S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();

        /** Transaction Id for SO IF Table */
        private BigDecimal soTrxId = null;

        /** Transaction Id for PO IF Table */
        private BigDecimal poTrxId = null;

        /** Unit Id for SO IF Table */
        private BigDecimal soUnitId = BigDecimal.ONE;

        /** Unit Id for PO IF Table */
        private BigDecimal poUnitId = BigDecimal.ONE;

        /** WMS_SHIP_VIA_RTE_MAP Map */
        private S21LRUMap<String, WMS_SHIP_VIA_RTE_MAPTMsg> wmsShipViaRteMap = new S21LRUMap<String, WMS_SHIP_VIA_RTE_MAPTMsg>(VAL_WMS_SHIP_VIA_RTE_MAP_LIST_SIZE);

        /** WMS_FRT_OUT_CD_MAP Map */
        private S21LRUMap<String, WMS_FRT_OUT_CD_MAPTMsg> wmsFrtOutCdMap = new S21LRUMap<String, WMS_FRT_OUT_CD_MAPTMsg>(VAL_WMS_FRT_OUT_CD_MAP_LIST_SIZE);

        /** WMS_CTRY_CTAC Map */
        private S21LRUMap<String, WMS_CTRY_CTACTMsg> wmsCtryCtacMap = new S21LRUMap<String, WMS_CTRY_CTACTMsg>(VAL_WMS_CTRY_CTAC_LIST_SIZE);

        /** WMS_SO_DNLD_SO_INTF_ID */
        private String wmsSoDnldSoIntfcId = null;

        /** WMS_SO_DNLD_PO_INTF_ID */
        private String wmsSoDnldPoIntfcId = null;

        /**
         * Default Constructor.
         */
        public SoToWms() {
            // Do nothing.
        }

        /**
         * Do process SO to WMS.
         * @param rs ResultSet
         * @throws SQLException
         * @return result
         */
        public Integer doProcessQueryResult(ResultSet rs) throws SQLException {

            // QC#17981 Add.
            getWmsOrdTpCdFromVarCharConst();

            while (rs.next()) {

                WMS_INBD_SO_SHIP_TOTMsg wmsInbdSoShipToT = null;
                List<WMS_INBD_SO_DTLTMsg> wmsInbdSoDtlTList = new ArrayList<WMS_INBD_SO_DTLTMsg>();
                WMS_INBD_SO_TEXTTMsg wmsInbdSoTextT = null;
                WMS_INBD_SO_CHRG_TOTMsg wmsInbdSoChrgToT = null;
                WMS_INBD_SO_BILL_TOTMsg wmsInbdSoBillToT = null;
                WMS_INBD_SO_RTRN_TOTMsg wmsInbdSoRtrnToT = null;
                boolean isRegistSoTrxId = false;
                boolean isRegistPoTrxId = false;
                boolean isRegistInbdSoData = false;
                String wmsUniqId = rs.getString(COL_WMS_UNIQ_ID);
                wmsSoDnldSoIntfcId = rs.getString(COL_WMS_SO_DNLD_SO_INTFC_ID);
                wmsSoDnldPoIntfcId = rs.getString(COL_WMS_SO_DNLD_PO_INTFC_ID);
                wmsInbdSoDtlTList.clear();
                wmsInbdSoShipToT = null;
                soTrxId = null;
                poTrxId = null;
                soUnitId = BigDecimal.ONE;
                poUnitId = BigDecimal.ONE;
                isRegistSoTrxId = false;
                isRegistPoTrxId = false;

                WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT = createHdr(rs);
                if (wmsInbdSoHdrT == null) {
                    isRegistInbdSoData = false;
                } else {
                    isRegistInbdSoData = createDtl(wmsInbdSoDtlTList, wmsInbdSoHdrT, wmsUniqId, rs.getString(COL_SCE_ORD_TP_CD));

                    createBoDtl(wmsInbdSoHdrT, wmsInbdSoDtlTList);

                    registerHdr(wmsInbdSoHdrT);

                    wmsInbdSoTextT = createText(wmsInbdSoHdrT);

                    wmsInbdSoShipToT = createShipTo(wmsInbdSoHdrT);

                    wmsInbdSoChrgToT = createChrgTo(wmsInbdSoHdrT);

                    wmsInbdSoBillToT = createBillTo(wmsInbdSoHdrT);

                    wmsInbdSoRtrnToT = createRtrnTo(wmsInbdSoHdrT);
                    if (wmsInbdSoRtrnToT == null) {
                        if (notCrtRtrnToWmsOrdCd != null
                        && !Arrays.asList(notCrtRtrnToWmsOrdCd).contains(wmsInbdSoHdrT.wmsOrdTpCd.getValue())
                        && (VAL_WMS_UNIQ_ID_TECSYS.equals(wmsUniqId) || VAL_WMS_UNIQ_ID_TECSYS_2.equals(wmsUniqId))) {
                            wmsInbdSoRtrnToT = createRtrnTob(wmsInbdSoHdrT);
                        }
                    }
                }

                if (!isRegistInbdSoData) {
                    rollback();
                    totalErrCount++;
                    continue;
                }

                // Register SO I/F Tables, PO I/F Tables.
                String wmsDropFlg = ZYPConstant.FLG_ON_Y;

                if (regSoPoWmsOrdCd != null && Arrays.asList(regSoPoWmsOrdCd).contains(wmsInbdSoHdrT.wmsOrdTpCd.getValue())) {
                    // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
                    Map<String, Object> soIf1AddValueMap = getSoIf1AddValue(rs);
                    // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]

                    // Register SO, PO.
                    if (!registerSo(wmsInbdSoHdrT, wmsInbdSoDtlTList, wmsInbdSoShipToT, wmsInbdSoTextT, wmsInbdSoChrgToT, wmsInbdSoBillToT, wmsInbdSoRtrnToT, soIf1AddValueMap)) {
                        wmsDropFlg = ZYPConstant.FLG_OFF_N;
                        rollback();
                    }
                    if (ZYPConstant.FLG_ON_Y.equals(wmsDropFlg)) {
                        if (!registerPo(wmsInbdSoHdrT, wmsInbdSoDtlTList, wmsInbdSoShipToT, wmsInbdSoTextT)) {
                            wmsDropFlg = ZYPConstant.FLG_OFF_N;
                            rollback();
                        }
                    }
                } else if (regPoWmsOrdCd != null && Arrays.asList(regPoWmsOrdCd).contains(wmsInbdSoHdrT.wmsOrdTpCd.getValue())) {
                    // Register PO.
                    if (!registerPo(wmsInbdSoHdrT, wmsInbdSoDtlTList, wmsInbdSoShipToT, wmsInbdSoTextT)) {
                        wmsDropFlg = ZYPConstant.FLG_OFF_N;
                        rollback();
                    }
                } else {
                    // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
                    Map<String, Object> soIf1AddValueMap = getSoIf1AddValue(rs);
                    // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]

                    // Register SO.
                    if (!registerSo(wmsInbdSoHdrT, wmsInbdSoDtlTList, wmsInbdSoShipToT, wmsInbdSoTextT, wmsInbdSoChrgToT, wmsInbdSoBillToT, wmsInbdSoRtrnToT, soIf1AddValueMap)) {
                        wmsDropFlg = ZYPConstant.FLG_OFF_N;
                        rollback();
                    }
                }

                // Update WMS_DROP_FLG of SHPG_ORD.
                if (updateShpgOrd(wmsInbdSoHdrT, wmsDropFlg)) {

                    updateWmsIntfcCtrl(wmsInbdSoHdrT.whCd.getValue());
                    commit();
                    if (ZYPConstant.FLG_OFF_N.equals(wmsDropFlg)) {
                        totalErrCount++;
                    } else {
                        totalCommitCount++;

                        // If transaction id isn't registered,
                        // register transaction id.
                        if (!isRegistSoTrxId && soUnitId.intValue() > 1) {
                            s21TrxTblAccessor.createIntegrationRecordForBatch(wmsSoDnldSoIntfcId, soTrxId);
                            isRegistSoTrxId = true;
                            commit();
                        }
                        if (!isRegistPoTrxId && poUnitId.intValue() > 1) {
                            s21TrxTblAccessor.createIntegrationRecordForBatch(wmsSoDnldPoIntfcId, poTrxId);
                            isRegistPoTrxId = true;
                            commit();
                        }
                    }
                } else {
                    rollback();
                    totalErrCount++;
                }
            }
            return rtrnCommitCount;
        }

        /**
         * Register SO I/F Tables.
         * @param wmsInbdSoHdrT WMS_INBD_SO_HDRTMsg
         * @param wmsInbdSoDtlTList WMS_INBD_SO_DTLTMsg List
         * @param wmsInbdSoShipToT WMS_INBD_SO_SHIP_TOTMsg
         * @param wmsInbdSoTextTList WMS_INBD_SO_TEXTTMsg List
         * @param wmsInbdSoChrgToT WMS_INBD_SO_CHRG_TOTMsg
         * @param wmsInbdSoBillToT WMS_INBD_SO_BILL_TOTMsg
         * @param wmsInbdSoRtrnToT WMS_INBD_SO_RTRN_TOTMsg
         * @return true / success, false / error
         */
        private boolean registerSo(WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT, List<WMS_INBD_SO_DTLTMsg> wmsInbdSoDtlTList, WMS_INBD_SO_SHIP_TOTMsg wmsInbdSoShipToT
                , WMS_INBD_SO_TEXTTMsg wmsInbdSoTextT, WMS_INBD_SO_CHRG_TOTMsg wmsInbdSoChrgToT, WMS_INBD_SO_BILL_TOTMsg wmsInbdSoBillToT
                , WMS_INBD_SO_RTRN_TOTMsg wmsInbdSoRtrnToT, Map<String, Object> soIf1AddValueMap) {

            if (!ZYPCommonFunc.hasValue(soTrxId)) {
                soTrxId = s21TrxTblAccessor.getNextTransactionId();
            }

            BigDecimal soSeqNumber = BigDecimal.ZERO;
            // Register SO IF data.
            soSeqNumber = createSoIf01(wmsInbdSoHdrT, wmsInbdSoShipToT, wmsInbdSoChrgToT, wmsInbdSoBillToT, wmsInbdSoRtrnToT, soSeqNumber, soIf1AddValueMap);
            if (!ZYPCommonFunc.hasValue(soSeqNumber)) {
                return false;
            }
            soSeqNumber = createSoIf02(wmsInbdSoHdrT, wmsInbdSoDtlTList, soSeqNumber);
            if (!ZYPCommonFunc.hasValue(soSeqNumber)) {
                return false;
            }
            soSeqNumber = createSoIf03(wmsInbdSoHdrT, wmsInbdSoTextT, soSeqNumber);
            if (!ZYPCommonFunc.hasValue(soSeqNumber)) {
                return false;
            }
            soUnitId = soUnitId.add(BigDecimal.ONE);
            return true;
        }

        /**
         * Register PO I/F Tables.
         * @param wmsInbdSoHdrT WMS_INBD_SO_HDRTMsg
         * @param wmsInbdSoDtlTList WMS_INBD_SO_DTLTMsg List
         * @param wmsInbdSoShipToT WMS_INBD_SO_SHIP_TOTMsg
         * @param wmsInbdSoTextTList WMS_INBD_SO_TEXTTMsg List
         * @return true / success, false / error
         */
        private boolean registerPo(WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT, List<WMS_INBD_SO_DTLTMsg> wmsInbdSoDtlTList, WMS_INBD_SO_SHIP_TOTMsg wmsInbdSoShipToT, WMS_INBD_SO_TEXTTMsg wmsInbdSoTextT) {

            if (!ZYPCommonFunc.hasValue(poTrxId)) {
                poTrxId = s21TrxTblAccessor.getNextTransactionId();
            }

            BigDecimal poSeqNumber = BigDecimal.ZERO;
            // Register PO IF data.
            poSeqNumber = createPoIf01(wmsInbdSoHdrT, wmsInbdSoShipToT, poSeqNumber);
            if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
                return false;
            }
            poSeqNumber = createPoIf04(wmsInbdSoHdrT, wmsInbdSoDtlTList, poSeqNumber);
            if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
                return false;
            }
            poUnitId = poUnitId.add(BigDecimal.ONE);
            return true;
        }

        /**
         * Create WMS_INBD_SO_HDRTMsg.
         * @param rs ResultSet
         * @param pk WMS_OTBD_SO_WRK_PK
         * @return WMS_INBD_SO_HDRTMsg
         */
        private WMS_INBD_SO_HDRTMsg createHdr(ResultSet rs) {

            WMS_INBD_SO_HDRTMsg hdrT = new WMS_INBD_SO_HDRTMsg();

            try {

                // check S80 code value
                if (!ZYPCommonFunc.hasValue(rs.getString(COL_S80_CMPY_CD))) {
                    outputErr(NLAM1001E, new String[] {TBL_CMPY_CD_CONV, COL_GLBL_CMPY_CD, glblCmpyCd});
                    return null;
                }

                // QC#19634 Start
                if (ZYPCommonFunc.hasValue(rs.getString(COL_SCE_ORD_TP_CD))) {
                    if(!ZYPCommonFunc.hasValue(rs.getString(CONV_SCE_ORD_TP_CD))) {
                        outputErr(NLAM1001E, new String[] {TBL_SCE_ORD_TP, COL_SCE_ORD_TP_CD, rs.getString(COL_SCE_ORD_TP_CD)});
                        return null;   
                    }
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COL_TRX_SRC_TP_CD))) {
                    if (!ZYPCommonFunc.hasValue(rs.getString(CONV_TRX_SRC_TP_CD))) {
                        outputErr(NLAM1001E, new String[] {TBL_ORD_SRC_CONV, COL_TRX_SRC_TP_CD, rs.getString(COL_TRX_SRC_TP_CD) });
                        return null;
                    }
                }

                if (ZYPCommonFunc.hasValue(rs.getString(COL_SHPG_SVC_LVL_CD))) {
                    if (!ZYPCommonFunc.hasValue(rs.getString(CONV_SHPG_SVC_LVL_CD))) {
                        outputErr(NLAM1001E, new String[] {TBL_SHIP_VIA_CONV, COL_SHPG_SVC_LVL_CD, rs.getString(COL_SHPG_SVC_LVL_CD)});
                        return null;
                    }
                }

                if ((ZYPCommonFunc.hasValue(rs.getString(COL_FRT_CHRG_TO_CD)) && ZYPCommonFunc.hasValue(rs.getString(COL_FRT_CHRG_METH_CD)))) {
                    if((!ZYPCommonFunc.hasValue(rs.getString(CONV_FRT_CHRG_TO_CD)) || !ZYPCommonFunc.hasValue(rs.getString(CONV_FRT_CHRG_METH_CD)))) {
                        outputErr(NLAM1001E, new String[] {TBL_SHPG_TERM_CONV, COL_FRT_CHRG_TO_CD, rs.getString(COL_FRT_CHRG_TO_CD)});
                        return null;
                    }
                }
                // QC#19634 End
                BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);

                ZYPEZDItemValueSetter.setValue(hdrT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(hdrT.whCd, rs.getString(COL_WH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSqNum, wmsSqNum);
                ZYPEZDItemValueSetter.setValue(hdrT.intfcTpId, VAL_INTFC_TP_ID_02);
                ZYPEZDItemValueSetter.setValue(hdrT.intfcRecTpId, VAL_INTFC_REC_TP_ID_HDR);
                ZYPEZDItemValueSetter.setValue(hdrT.wmsCmpyCd, rs.getString(COL_S80_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSoId, rs.getString(COL_SO_NUM));
                // QC#19109
                ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdNum, rs.getString(COL_WMS_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.altDocNum, rs.getString(COL_PICK_TKT_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.custOrdNum, rs.getString(COL_CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.chrgToCustCd, rs.getString(COL_SELL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.billToCustCd, rs.getString(COL_BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.shipToCustCd, rs.getString(COL_SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsPrtyCd, VAL_WMS_PRTY_CD);
                ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdTpCd, rs.getString(COL_S80_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsTrxCd, rs.getString(COL_S80_TRX_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdSrcCd, rs.getString(COL_S80_ORD_SRC_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSoStsCd, VAL_WMS_SO_STS_CD);
                ZYPEZDItemValueSetter.setValue(hdrT.soShipViaCd, rs.getString(COL_EXPT_SHPG_METH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.shipViaDescTxt, rs.getString(COL_SHPG_METH_NM));
                ZYPEZDItemValueSetter.setValue(hdrT.cratDtTmTs, rs.getString(COL_SO_CRAT_TS));
                ZYPEZDItemValueSetter.setValue(hdrT.estShipDtTmTs, rs.getString(COL_PSD_DT));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsRqstDtTmTs, rs.getString(COL_RDD_DT));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsPrintDtTmTs, rs.getString(COL_DNLD_TM_TS));
                ZYPEZDItemValueSetter.setValue(hdrT.frtOutCd, rs.getString(COL_S80_SHPG_TERM_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.frtOutDescTxt, rs.getString(COL_S80_SHPG_TERM_NM));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSoCarrCd, rs.getString(COL_CARR_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.indOtmAddrSwthFlg, rs.getString(COL_DROP_SHIP_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indScc14Flg, rs.getString(COL_PRINT_SCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indUccFlg, rs.getString(COL_PRINT_UCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indMixedPltFlg, rs.getString(COL_MIX_PLT_ALLW_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indPltLbFlg, rs.getString(COL_PRINT_PLT_UCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.sccNumFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.indNonAsnFlg, rs.getString(COL_PRINT_NON_ASN_UCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indAsnFlg, rs.getString(COL_ASN_REQ_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indUccNumFlg, rs.getString(COL_UCC_NUM_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsLbNum, rs.getString(COL_EDI_TP_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.custStoreNum, rs.getString(COL_CUST_STORE_NUM));
                String custDcNum = rs.getString(COL_CUST_STORE_NUM);
                if (ZYPCommonFunc.hasValue(custDcNum) && custDcNum.length() > LG_CUST_DC_NUM) {
                    custDcNum = custDcNum.substring(0, LG_CUST_DC_NUM);
                }
                ZYPEZDItemValueSetter.setValue(hdrT.custDcNum, custDcNum);
                ZYPEZDItemValueSetter.setValue(hdrT.wmsCustDeptNum, rs.getString(COL_SO_DEPT_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsConslFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.totShipPrcAmtNum, rs.getBigDecimal(COL_TOT_SHIP_AMT));
                // totWtAmtNum is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.rtrnLbCd, rs.getString(COL_RTRN_LB_CD));
                // tpVndCd is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.indConfigFlg, rs.getString(COL_SO_CONFIG_FLG));
                // asgShipViaCd is set after SO_DTL process.
                // asgPrtyCd is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.indSgnReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.billAcctNum, rs.getString(COL_CARR_ACCT_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.rtlWhCd, rs.getString(COL_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.schdDelyDt, rs.getString(COL_SCHD_DELY_DT));
                ZYPEZDItemValueSetter.setValue(hdrT.carrCd, rs.getString(COL_CARR_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.shpgSvcLvlCd, rs.getString(COL_SHPG_SVC_LVL_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.trxHdrNum, rs.getString(COL_TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.usrCdIstlRefTxt, rs.getString(COL_SO_CONFIG_FLG));
                // rtrnItemInclFlg is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.svcConfigMstrPk, rs.getBigDecimal(COL_SVC_CONFIG_MSTR_PK));
                // asmReqFlg is set after SO_DTL process.
                // stageActCd is set after additionalSoHeaderValSet funcition.
                ZYPEZDItemValueSetter.setValue(hdrT.stageRecStsCd, STAGE_REC_STS_NEW_UPDATE);
                ZYPEZDItemValueSetter.setValue(hdrT.carrSvcHldAtLocFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.rtePathCd, rs.getString(COL_WMS_RTE_PATH_CD));
                // rtrnTagReqFlg is set after SO_DTL process.
                // otbdSrcOrdTpTxt is set after additionalSoHeaderValSet funcition.
                // prtToCustFlg is set after additionalSoHeaderValSet funcition.
                ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdNum, rs.getString(COL_SRC_ORD_NUM));
                // slsOrdAdminNm is set after additionalSoHeaderValSet funcition.
                ZYPEZDItemValueSetter.setValue(hdrT.slsRepPsnNm, rs.getString(COL_SR_PSN_NM));

                additionalSoHeaderValSet(rs, hdrT);

            } catch (SQLException e) {
                sqlExceptionHandler(e);
                return null;
            }
            return hdrT;
        }

        /**
         * set additional value to WMS_INBD_SO_HDRTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param rs ResultSet
         * @param sceOrdTpCd String
         */
        private void additionalSoHeaderValSet(ResultSet rs, WMS_INBD_SO_HDRTMsg hdrT) {
            try {
                String sceOrdTpCd = rs.getString(COL_SCE_ORD_TP_CD);
                if (sceOrdTpCd != null) {
                    if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                     || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {
                        ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdTpTxt, rs.getString(COL_OTBD_SRC_ORD_TP_TXT));
                        ZYPEZDItemValueSetter.setValue(hdrT.slsOrdAdminNm, rs.getString(COL_CPO_PSN_NM));
                    } else {
                        ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdTpTxt, rs.getString(COL_SCE_ORD_TP_NM));
                    }
                    if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)
                     && rs.getString(COL_LOC_GRP_CD) != null
                     && LOC_GRP.CUSTOMER.equals(rs.getString(COL_LOC_GRP_CD))) {
                        ZYPEZDItemValueSetter.setValue(hdrT.prtToCustFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(hdrT.prtToCustFlg, ZYPConstant.FLG_OFF_N);
                    }
                    if (SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {
                        ZYPEZDItemValueSetter.setValue(hdrT.slsOrdAdminNm, rs.getString(COL_PO_PSN_NM));
                    }
                    // when SCE_ORD_TP_CD IN ('DI', 'RP', 'RE', 'RD', 'TR')
                    // slsOrdAdminNm set after SO_DTL process.
                }

                if (!isWmsInbdSoUpdate(glblCmpyCd, hdrT.wmsSoId.getValue())) {
                    ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, STAGE_ACT_NEW);
                } else {
                    ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, STAGE_ACT_UPDATE);
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            }
        }

        /**
         * Create WMS_INBD_SO_DTLTMsg.
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param wmsUniqId WMS_UNIQ_ID
         * @param sceOrdTpCd String
         * @return true / success, false / error
         */
        private boolean createDtl(List<WMS_INBD_SO_DTLTMsg> dtlTList, WMS_INBD_SO_HDRTMsg hdrT, String wmsUniqId, String sceOrdTpCd) {

            WMS_INBD_SO_DTLTMsg dtlT = new WMS_INBD_SO_DTLTMsg();
            
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("soNum", hdrT.wmsSoId.getValue());
            ssmParam.put("val2", VAL_2);
            ssmParam.put("maxQty", VAL_MAX_QTY);
            List<Map<String, Object>> soDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoDtl", ssmParam);

            // additional so header set default.
            ZYPEZDItemValueSetter.setValue(hdrT.rtrnItemInclFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(hdrT.asmReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(hdrT.rtrnTagReqFlg, ZYPConstant.FLG_OFF_N);

            if (soDtlList != null) {
                for (Map<String, Object> soDtl : soDtlList) {
                    // QC#19634 Start
                    // check S80 code value
                    if (ZYPCommonFunc.hasValue((String) soDtl.get(COL_FROM_STK_STS_CD))) {
                        if (!ZYPCommonFunc.hasValue((String) soDtl.get(CONV_FROM_STK_STS_CD))) {
                            outputErr(NLAM1001E, new String[] {TBL_STS_STK_CONV, COL_S80_STK_STS_CD_FROM, (String) soDtl.get(COL_FROM_STK_STS_CD) });
                            return false;
                        }
                    }

                    // check S80 code value
                    if (ZYPCommonFunc.hasValue((String) soDtl.get(COL_TO_STK_STS_CD))) {
                        if (!ZYPCommonFunc.hasValue((String) soDtl.get(CONV_TO_STK_STS_CD))) {
                            outputErr(NLAM1001E, new String[] {TBL_STS_STK_CONV, COL_S80_STK_STS_CD_TO, (String) soDtl.get(COL_TO_STK_STS_CD) });
                            return false;
                        }
                    }
                    // QC#19634 End
                    ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, (BigDecimal) soDtl.get(COL_SO_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, VAL_INTFC_TP_ID_02);
                    ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, VAL_INTFC_REC_TP_ID_DTL);
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsSoId, hdrT.wmsSoId.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) soDtl.get(COL_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) soDtl.get(COL_S80_STK_STS_CD_FROM));
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
                    ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCdToCd, (String) soDtl.get(COL_S80_STK_STS_CD_TO));
                    ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetCd, (String) soDtl.get(COL_SET_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetDescTxt, (String) soDtl.get(COL_SET_MDSE_NM));
                    ZYPEZDItemValueSetter.setValue(dtlT.shipSetQty, (BigDecimal) soDtl.get(COL_SET_SHPG_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.unitInsdQty, (BigDecimal) soDtl.get(COL_IN_EACH_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.totWtAmtNum, (BigDecimal) soDtl.get(COL_TOT_SHPG_WT));
                    ZYPEZDItemValueSetter.setValue(dtlT.totVolAmtNum, (BigDecimal) soDtl.get(COL_TOT_SHPG_VOL));
                    if (notCaseandPalletWmsOrdCd != null && !Arrays.asList(notCaseandPalletWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())
                            && (VAL_WMS_UNIQ_ID_TECSYS.equals(wmsUniqId) || VAL_WMS_UNIQ_ID_MA.equals(wmsUniqId) || VAL_WMS_UNIQ_ID_MENLO.equals(wmsUniqId) || VAL_WMS_UNIQ_ID_TECSYS_2.equals(wmsUniqId))) {
                        setCaseAndPallet(dtlT);
                    }
                    ZYPEZDItemValueSetter.setValue(dtlT.batCptrReqFlg, (String) soDtl.get(COL_BAT_NUM_TAKE_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.indConfigFlg, (String) soDtl.get(COL_CONFIG_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, (String) soDtl.get(COL_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) soDtl.get(COL_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.svcConfigMstrPk, (BigDecimal) soDtl.get(COL_PICK_SVC_CONFIG_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdImpctTpCd, (String) soDtl.get(COL_BACK_ORD_IMPCT_TP_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdUomCd, WMS_UOM.EACH);
                    //QC# 20176 TRX_LINE_NUM => DPLY_LINE_NUM
                    ZYPEZDItemValueSetter.setValue(dtlT.poLineTxt, (String) soDtl.get(COL_DPLY_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(dtlT.usrCdIstlRefTxt, (String) soDtl.get(COL_CONFIG_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(dtlT.rmvConfigFlg, (String) soDtl.get(COL_RMV_CONFIG_FLG));

                    CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(glblCmpyCd, (String) soDtl.get(COL_TRX_HDR_NUM), (String) soDtl.get(COL_TRX_LINE_NUM), (String) soDtl.get(COL_TRX_LINE_SUB_NUM));
                    if (cpoDtlTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, cpoDtlTMsg.ordQty);
                    } else {
                        ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, dtlT.wmsOrdQty);
                    }

                    // additional so header set
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
                    if (SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd)
                            || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {
                        String slsOrdAdminNm = getPsnNmByPr((String) soDtl.get(COL_PRCH_REQ_NUM));
                        if (hdrT.slsOrdAdminNm.getValue() != null && slsOrdAdminNm != null) {
                            ZYPEZDItemValueSetter.setValue(hdrT.slsOrdAdminNm, slsOrdAdminNm);
                        }
                    }

                    EZDTBLAccessor.insert(dtlT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlT.getReturnCode())) {
                        throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_DTL, TBL_SHPG_ORD_DTL, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM),
                                NLXCMsgHelper.toListedString(glblCmpyCd, dtlT.wmsSoId.getValue()) });
                    }

                    WMS_INBD_SO_DTLTMsg wmsInbdSoDtlT = (WMS_INBD_SO_DTLTMsg) dtlT.clone();
                    dtlTList.add(wmsInbdSoDtlT);
                }

                // additional so header set
                if (notCrtWtandShipViaWmsOrdCd != null && !Arrays.asList(notCrtWtandShipViaWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) {
                    if (VAL_WMS_UNIQ_ID_TECSYS.equals(wmsUniqId) || VAL_WMS_UNIQ_ID_TECSYS_2.equals(wmsUniqId)) {
                        setHazmat(hdrT, dtlTList);
                    }
                    setWtAndShipVia(hdrT, dtlTList);
                }
            }
            return true;
        }

        /**
         * Get PSN_NM By PR.
         * @param prchReqNum String
         * @return String
         */
        private String getPsnNmByPr(String prchReqNum) {

            if (prchReqNum == null) {
                return null;
            }

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("prchReqNum", prchReqNum);
            queryParam.put("lgPsnNm", VAL_PSN_NM_SIZE);
            return (String) ssmBatchClient.queryObject("getPsnNmByPr", queryParam);
        }

        /**
         * Create WMS_INBD_SO_DTLTMsg.
         * @param rs ResultSet
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param wmsUniqId WMS_UNIQ_ID
         */
        private void createBoDtl(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList) {

            List<Map<String, Object>> shpgBoDtlList = getShpippingBackOrderDetailList(glblCmpyCd, hdrT.wmsSoId.getValue());

            for (Map<String, Object> shpgBoDtl : shpgBoDtlList) {
                WMS_INBD_SO_DTLTMsg dtlT = new WMS_INBD_SO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, new BigDecimal((String) shpgBoDtl.get("SO_SLP_NUM")));
                ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, BACK_ORDER_INTFC_TP_ID);
                ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, BACK_ORDER_INTFC_REC_TP_ID);
                dtlT.intfcRecActCd.clear();
                ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsSoId, (String) shpgBoDtl.get("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) shpgBoDtl.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) shpgBoDtl.get("S80_FROM_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.custMdseCd, (String) shpgBoDtl.get("CUST_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdQty, (BigDecimal) shpgBoDtl.get("RQST_ORD_QTY"));
                ZYPEZDItemValueSetter.setValue(dtlT.backOrdQtyNum, (BigDecimal) shpgBoDtl.get("SHPG_BAL_QTY"));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsShipQty, (BigDecimal) shpgBoDtl.get("BO_QTY"));
                ZYPEZDItemValueSetter.setValue(dtlT.unitPrcAmtNum, (BigDecimal) shpgBoDtl.get("UNIT_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlT.unitDiscAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.unitDiscPrcAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsTotAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.indSerId, (String) shpgBoDtl.get("SHPG_SER_TAKE_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlT.indVoidAllwFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCdToCd, (String) shpgBoDtl.get("S80_TO_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetCd, (String) shpgBoDtl.get("SET_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetDescTxt, (String) shpgBoDtl.get("SET_MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(dtlT.shipSetQty, BigDecimal.ZERO);
                dtlT.unitInsdQty.clear();
                dtlT.essPoSqNum.clear();
                dtlT.essMdseLineNum.clear();
                dtlT.essLineNum.clear();
                dtlT.essMsgLineNum.clear();
                ZYPEZDItemValueSetter.setValue(dtlT.totWtAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.totVolAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.estCseAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.estPltAmtNum, BigDecimal.ZERO);
                dtlT.soMdseTpCd.clear();
                dtlT.wmsPackTpCd.clear();
                ZYPEZDItemValueSetter.setValue(dtlT.batCptrReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dtlT.indConfigFlg, (String) shpgBoDtl.get("CONFIG_ITEM_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsNetAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsDiscAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsTaxAmtNum, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, (String) shpgBoDtl.get("RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) shpgBoDtl.get("RTL_SWH_CD"));
                dtlT.svcConfigMstrPk.clear();
                ZYPEZDItemValueSetter.setValue(dtlT.backOrdImpctTpCd, (String) shpgBoDtl.get("BACK_ORD_IMPCT_TP_CD"));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdUomCd, WMS_UOM.EACH);
                //QC# 20176 TRX_LINE_NUM => DPLY_LINE_NUM
                ZYPEZDItemValueSetter.setValue(dtlT.poLineTxt, (String) shpgBoDtl.get("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlT.usrCdIstlRefTxt, (String) shpgBoDtl.get("CONFIG_ITEM_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlT.backOrdFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(dtlT.rmvConfigFlg, ZYPConstant.FLG_OFF_N);

                CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(glblCmpyCd, (String) shpgBoDtl.get("TRX_HDR_NUM"), (String) shpgBoDtl.get("TRX_LINE_NUM"), (String) shpgBoDtl.get("TRX_LINE_SUB_NUM"));
                if (cpoDtlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, cpoDtlTMsg.ordQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, dtlT.wmsOrdQty);
                }

                EZDTBLAccessor.insert(dtlT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlT.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_DTL, TBL_WMS_OTBD_SO_WRK //
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, dtlT.wmsSoId.getValue()) });
                }
                dtlTList.add(dtlT);
            }
        }

        /**
         * Create WMS_INBD_SO_TEXTTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param textTList WMS_INBD_SO_TEXTTMsg List
         */
        private WMS_INBD_SO_TEXTTMsg createText(WMS_INBD_SO_HDRTMsg hdrT) {

            WMS_INBD_SO_TEXTTMsg textT = new WMS_INBD_SO_TEXTTMsg();

            ZYPEZDItemValueSetter.setValue(textT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(textT.whCd, hdrT.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(textT.wmsSqNum, hdrT.wmsSqNum.getValue());
            ZYPEZDItemValueSetter.setValue(textT.intfcTpId, VAL_INTFC_TP_ID_02);
            ZYPEZDItemValueSetter.setValue(textT.intfcRecTpId, VAL_INTFC_REC_TP_ID_TXT);
            ZYPEZDItemValueSetter.setValue(textT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(textT.wmsSoId, hdrT.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(textT.wmsPrintTpCd, VAL_PRINT_TP_CD_B);

            SHPG_ORD_MSGTMsg inMsg = new SHPG_ORD_MSGTMsg();
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("soNum01", hdrT.wmsSoId.getValue());
            SHPG_ORD_MSGTMsgArray soMsgArray = (SHPG_ORD_MSGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if (soMsgArray == null || soMsgArray.length() <= 0) {
                return null;
            }

            // Repeat the split registration
            for (int i = 0; i < soMsgArray.length(); i++) {
                switch (i) {
                    case 0:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_01, soMsgArray.no(i).soMsgDescTxt.getValue());
                        ZYPEZDItemValueSetter.setValue(textT.wmsTxtCd, soMsgArray.no(i).soMsgTpCd.getValue());
                        break;
                    case 1:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_02, soMsgArray.no(i).soMsgDescTxt.getValue());
                        break;
                    case 2:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_03, soMsgArray.no(i).soMsgDescTxt.getValue());
                        break;
                    case 3:
                        ZYPEZDItemValueSetter.setValue(textT.inbdSoMsgTxt_04, soMsgArray.no(i).soMsgDescTxt.getValue());
                        break;
                    default:
                        break;
                }
            }
            EZDTBLAccessor.insert(textT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(textT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_TEXT, TBL_SHPG_ORD_MSG, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM), NLXCMsgHelper.toListedString(glblCmpyCd, textT.wmsSoId.getValue()) });
            }

            return textT;
        }

        /**
         * Create WMS_INBD_SO_SHIP_TOTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return WMS_INBD_SO_SHIP_TOTMsg
         */
        private WMS_INBD_SO_SHIP_TOTMsg createShipTo(WMS_INBD_SO_HDRTMsg hdrT) {

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
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_SHIP_TO, TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, shipToT.wmsSoId.getValue()) });
                }
            }
            return shipToT;
        }

        /**
         * Create WMS_INBD_SO_CHRG_TOTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return WMS_INBD_SO_CHRG_TOTMsg
         */
        private WMS_INBD_SO_CHRG_TOTMsg createChrgTo(WMS_INBD_SO_HDRTMsg hdrT) {

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
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_CHRG_TO, TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, chrgToT.wmsSoId.getValue()) });
                }
            }
            return chrgToT;
        }

        /**
         * Create WMS_INBD_SO_BILL_TOTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return WMS_INBD_SO_BILL_TOTMsg
         */
        private WMS_INBD_SO_BILL_TOTMsg createBillTo(WMS_INBD_SO_HDRTMsg hdrT) {

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
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_BILL_TO, TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, billToT.wmsSoId.getValue()) });
                }
            }
            return billToT;
        }

        /**
         * Create WMS_INBD_SO_RTRN_TOTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return WMS_INBD_SO_RTRN_TOTMsg
         */
        private WMS_INBD_SO_RTRN_TOTMsg createRtrnTo(WMS_INBD_SO_HDRTMsg hdrT) {

            WMS_INBD_SO_RTRN_TOTMsg rtrnToT = new WMS_INBD_SO_RTRN_TOTMsg();

            ZYPEZDItemValueSetter.setValue(rtrnToT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtrnToT.whCd, hdrT.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnToT.wmsSqNum, hdrT.wmsSqNum.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnToT.intfcTpId, VAL_INTFC_TP_ID_02);
            ZYPEZDItemValueSetter.setValue(rtrnToT.intfcRecTpId, VAL_INTFC_REC_TP_ID_RTRN);
            ZYPEZDItemValueSetter.setValue(rtrnToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnToT.wmsSoId, hdrT.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(rtrnToT.wmsCustCd, hdrT.billToCustCd.getValue());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("soNum", hdrT.wmsSoId.getValue());
            ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.RETURN_TO);
            ssmParam.put("lgLineColNm", MAX_LENGTH_SO_CUST_LINE_LOC_NM);
            ssmParam.put("lgCtacTelNum", MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);
            Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);

            if (soCustDtl != null) {
                ZYPEZDItemValueSetter.setValue(rtrnToT.wmsRtrnToNm_01, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_01));
                ZYPEZDItemValueSetter.setValue(rtrnToT.wmsRtrnToNm_02, (String) soCustDtl.get(COL_SO_CUST_LINE_LOC_NM_02));
                ZYPEZDItemValueSetter.setValue(rtrnToT.firstLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_01));
                ZYPEZDItemValueSetter.setValue(rtrnToT.scdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_02));
                ZYPEZDItemValueSetter.setValue(rtrnToT.thirdLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_03));
                ZYPEZDItemValueSetter.setValue(rtrnToT.frthLineAddr, (String) soCustDtl.get(COL_SO_CUST_LINE_ADDR_04));
                ZYPEZDItemValueSetter.setValue(rtrnToT.ctyAddr, (String) soCustDtl.get(COL_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(rtrnToT.stCd, (String) soCustDtl.get(COL_ST_CD));
                ZYPEZDItemValueSetter.setValue(rtrnToT.postCd, (String) soCustDtl.get(COL_POST_CD));
                ZYPEZDItemValueSetter.setValue(rtrnToT.ctryCd, (String) soCustDtl.get(COL_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(rtrnToT.wmsRtrnToCtacNm, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_NM));
                ZYPEZDItemValueSetter.setValue(rtrnToT.rtrnToCtacNum, (String) soCustDtl.get(COL_CTAC_PTNR_PSN_TEL_NUM));

                EZDTBLAccessor.insert(rtrnToT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnToT.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_RTRN_TO, TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, rtrnToT.wmsSoId.getValue()) });
                }
            } else {
                rtrnToT = null;
            }
            return rtrnToT;
        }

        /**
         * Create WMS_INBD_SO_RTRN_TOTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return WMS_INBD_SO_RTRN_TOTMsg
         */
        private WMS_INBD_SO_RTRN_TOTMsg createRtrnTob(WMS_INBD_SO_HDRTMsg hdrT) {

            WMS_INBD_SO_RTRN_TOTMsg rtrnToT = new WMS_INBD_SO_RTRN_TOTMsg();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", hdrT.whCd.getValue());
                queryParam.put("whCdAll", VAL_WH_CD_ALL);
                queryParam.put("rtrnLblCd", hdrT.rtrnLbCd.getValue());
                queryParam.put("sysdateFmt", FMT_YYYYMMDDHHMMSS);

                stmt = ssmLLClient.createPreparedStatement("getRtrn", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ZYPEZDItemValueSetter.setValue(rtrnToT.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(rtrnToT.whCd, hdrT.whCd);
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsSqNum, hdrT.wmsSqNum);
                    ZYPEZDItemValueSetter.setValue(rtrnToT.intfcTpId, VAL_INTFC_TP_ID_02);
                    ZYPEZDItemValueSetter.setValue(rtrnToT.intfcRecTpId, VAL_INTFC_REC_TP_ID_RTRN);
                    rtrnToT.intfcRecActCd.clear();
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsCmpyCd, hdrT.wmsCmpyCd);
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsSoId, hdrT.wmsSoId);
                    if (ZYPCommonFunc.hasValue(hdrT.rtrnLbCd)) {
                        ZYPEZDItemValueSetter.setValue(rtrnToT.wmsCustCd, hdrT.rtrnLbCd);
                    } else {
                        ZYPEZDItemValueSetter.setValue(rtrnToT.wmsCustCd, hdrT.whCd);
                    }
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsRtrnToNm_01, rs.getString(COL_WMS_RTRN_TO_NM_01));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsRtrnToNm_02, rs.getString(COL_WMS_RTRN_TO_NM_02));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.firstLineAddr, rs.getString(COL_RTRN_TO_FIRST_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.scdLineAddr, rs.getString(COL_RTRN_TO_SCD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.thirdLineAddr, rs.getString(COL_RTRN_TO_THIRD_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.frthLineAddr, rs.getString(COL_RTRN_TO_FRTH_LINE_ADDR));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.ctyAddr, rs.getString(COL_RTRN_TO_CTY_ADDR));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.stCd, rs.getString(COL_RTRN_TO_ST_CD));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.postCd, rs.getString(COL_RTRN_TO_POST_CD));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.ctryCd, rs.getString(COL_RTRN_TO_CTRY_CD));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsRtrnToCtacNm, rs.getString(COL_WMS_RTRN_TO_CTAC_NM));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.rtrnToCtacNum, rs.getString(COL_RTRN_TO_CTAC_NUM));
                    ZYPEZDItemValueSetter.setValue(rtrnToT.wmsResrcTxt, VAL_WMS_RESRC_TXT);

                    EZDTBLAccessor.insert(rtrnToT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtrnToT.getReturnCode())) {
                        throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_RTRN_TO, TBL_SHPG_ORD
                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                                , NLXCMsgHelper.toListedString(glblCmpyCd, rtrnToT.wmsSoId.getValue()) });
                    }
                } else {
                    outputErr(NLGM0041E, new String[] {TBL_WMS_WH_RTRN_ADDR
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_RTRN_LB_CD, COL_WH_CD)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.rtrnLbCd.getValue(), hdrT.whCd.getValue()) });
                    return null;
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
                return null;
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
            return rtrnToT;
        }

        /**
         * Calculate and set Case and Pallet.
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         */
        private void setCaseAndPallet(WMS_INBD_SO_DTLTMsg dtlT) {

            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", dtlT.whCd.getValue());
                queryParam.put("mdseCd", dtlT.wmsMdseCd.getValue());
                queryParam.put("wmsShipQty", dtlT.wmsShipQty.getValue());
                queryParam.put("qtyDecimalPlace", VAL_EST_DECIMAL_PLACE);

                stmt = ssmLLClient.createPreparedStatement("getCaseAndPallet", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ZYPEZDItemValueSetter.setValue(dtlT.estCseAmtNum, rs.getBigDecimal(COL_CASES));
                    ZYPEZDItemValueSetter.setValue(dtlT.estPltAmtNum, rs.getBigDecimal(COL_PALLETS));
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        }

        /**
         * Calculate and set "HAZMAT" to WMS_INBD_SO_HDR
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         */
        private void setHazmat(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList) {

            for (WMS_INBD_SO_DTLTMsg dtlT : dtlTList) {
                if (isHazmat(dtlT)) {
                    ZYPEZDItemValueSetter.setValue(hdrT.tpVndCd, VAL_HAZMAT);
                    return;
                }
            }
        }

        /**
         * Judge whether this MDSE is hazmat or not.
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         * @return true/hazmat, false/not hazmat
         */
        private boolean isHazmat(WMS_INBD_SO_DTLTMsg dtlT) {

            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("whCd", dtlT.whCd.getValue());
                queryParam.put("mdseCd", dtlT.wmsMdseCd.getValue());
                queryParam.put("wmsHazMatFlg", ZYPConstant.FLG_ON_Y);

                stmt = ssmLLClient.createPreparedStatement("getHazmatMdse", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
            return false;
        }

        /**
         * Calculate and set Total weight and Ship via.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         */
        private void setWtAndShipVia(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList) {

            // Calculate and set weight.
            BigDecimal amt = BigDecimal.ZERO;
            for (WMS_INBD_SO_DTLTMsg dtlT : dtlTList) {
                amt = amt.add(NLXC014001.nullToZero(dtlT.totWtAmtNum.getValue()));
            }
            if (BigDecimal.ZERO.compareTo(amt) == 0) {
                ZYPEZDItemValueSetter.setValue(hdrT.totWtAmtNum, BigDecimal.ONE);
            } else {
                ZYPEZDItemValueSetter.setValue(hdrT.totWtAmtNum, amt);
            }

            // Calculate and set Ship Via.
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("mdBreakTpCd", VAL_MD_BREAK_TP_CD_WGT);
                queryParam.put("wt", amt);
                queryParam.put("whCd", hdrT.whCd.getValue());
                queryParam.put("wmsShipViaCd", hdrT.soShipViaCd.getValue());
                queryParam.put("wmsShipViaCdAll", VAL_SHIP_VIA_CD_ALL);

                stmt = ssmLLClient.createPreparedStatement("getShipVia", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ZYPEZDItemValueSetter.setValue(hdrT.asgShipViaCd, rs.getString(COL_NEW_SHIP_VIA));
                    ZYPEZDItemValueSetter.setValue(hdrT.asgPrtyCd, rs.getString(COL_NEW_PRTY_CD));
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        }

        /**
         * Create SO Interface Table(NLGI1100_01TMsg) List.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param shipToT WMS_INBD_SO_SHIP_TOTMsg
         * @param chrgToT WMS_INBD_SO_CHRG_TOTMsg
         * @param billToT WMS_INBD_SO_BILL_TOTMsg
         * @param rtrnToT WMS_INBD_SO_RTRN_TOTMsg
         * @param soSeqNumber SO_SEQ_NUMBER
         * @return SO_SEQ_NUMBER
         */
        private BigDecimal createSoIf01(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_SHIP_TOTMsg shipToT, WMS_INBD_SO_CHRG_TOTMsg chrgToT //
                , WMS_INBD_SO_BILL_TOTMsg billToT, WMS_INBD_SO_RTRN_TOTMsg rtrnToT, BigDecimal soSeqNumber, Map<String, Object> soIf1AddValueMap) {

            NLGI1100_01TMsg algi110001T = new NLGI1100_01TMsg();
            ZYPEZDItemValueSetter.setValue(algi110001T.interfaceId, wmsSoDnldSoIntfcId);
            ZYPEZDItemValueSetter.setValue(algi110001T.transactionId, soTrxId);
            ZYPEZDItemValueSetter.setValue(algi110001T.segmentId, VAL_SEGMENT_ID_1);
            ZYPEZDItemValueSetter.setValue(algi110001T.unitId, soUnitId);
            soSeqNumber = soSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi110001T.seqNumber, soSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi110001T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_01);

            ZYPEZDItemValueSetter.setValue(algi110001T.ordIdTxt, hdrT.wmsSoId);
            ZYPEZDItemValueSetter.setValue(algi110001T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.OUTBOUND, hdrT.wmsOrdTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(algi110001T.firstGrpCdTxt, hdrT.wmsTrxCd);
            if (ZYPConstant.FLG_ON_Y.equals(hdrT.indAsnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.thirdGrpCdTxt, hdrT.whCd.getValue() + ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.thirdGrpCdTxt, hdrT.whCd.getValue() + ZYPConstant.FLG_OFF_N);
            }

            // ITG#562871 - DEL Start
//            if (ZYPConstant.FLG_ON_Y.equals(hdrT.indAsnFlg.getValue())) {
//                if (!asnWhCdList.contains(hdrT.whCd.getValue())) {
//                    // Same value as above.
//                    ZYPEZDItemValueSetter.setValue(algi110001T.addrLbFmtTxt, VAL_ADDR_LB_FMT_TXT_ASN);
//                }
//            } else {
//                if (VAL_ADDR_LB_FMT_TXT_S329.equals(hdrT.chrgToCustCd.getValue()) && addrLbFmtWhCdList.contains(hdrT.whCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(algi110001T.addrLbFmtTxt, VAL_ADDR_LB_FMT_TXT_S329);
//                } else if (ZYPCommonFunc.hasValue(hdrT.wmsLbNum)) {
//                    ZYPEZDItemValueSetter.setValue(algi110001T.addrLbFmtTxt, hdrT.wmsLbNum);
//                } else if (WMS_ORD_TP.OUTBOUND_EXPORT.equals(hdrT.wmsOrdTpCd.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(algi110001T.addrLbFmtTxt, VAL_ADDR_LB_FMT_TXT_EXPORT);
//                }
//            }
            // ITG#562871 - DEL End

            if (ZYPCommonFunc.hasValue(hdrT.wmsRqstDtTmTs)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.rqstDtTmTsTxt, dateFormat(hdrT.wmsRqstDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            }
            String sysDate = ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS);
            if (ZYPDateUtil.compare(sysDate, hdrT.estShipDtTmTs.getValue()) == 1) {
                ZYPEZDItemValueSetter.setValue(algi110001T.rqstShipDtTmTsTxt, dateFormat(sysDate, FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            } else if (ZYPCommonFunc.hasValue(hdrT.estShipDtTmTs)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.rqstShipDtTmTsTxt, dateFormat(hdrT.estShipDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDDHHMMSS2));
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.rqstShipDtTmTsTxt, dateFormat(sysDate, FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.prtyNumTxt, VAL_PRTY_NUM_TXT_10);

            String tmpSfx = "";
            if (WMS_ORD_SRC.ESTORE.equals(hdrT.wmsOrdSrcCd.getValue())) {
                tmpSfx = VAL_RTE_CD_TXT_SFX_W + hdrT.indSgnReqFlg.getValue();
            }
            WMS_SHIP_VIA_RTE_MAPTMsg wmsShipViaRteMapT = getWmsShipViaRteMap(hdrT.whCd.getValue(), hdrT.soShipViaCd.getValue());
            // QC#17981 Mod.
            /*
            if (WMS_ORD_TP.OUTBOUND_KITTING.equals(hdrT.wmsOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.carrCdTxt, VAL_CARR_CD_TXT_ZZZZ);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, VAL_RTE_CD_TXT_K);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, VAL_RTE_NM_TXT_ITEM);
            */
            if (setNlgi1100OtbdKittingWmsOrdCd != null && Arrays.asList(setNlgi1100OtbdKittingWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) { 
                ZYPEZDItemValueSetter.setValue(algi110001T.carrCdTxt, VAL_CARR_CD_TXT_ZZZZ);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, VAL_RTE_CD_TXT_K);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, VAL_RTE_NM_TXT_ITEM);
            // START 2018/05/11 S.Katsuma [QC#24714,ADD]
            } else if (setNlgi1100OtbdRemanWmsOrdCd != null && Arrays.asList(setNlgi1100OtbdRemanWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.carrCdTxt, getCarrCdTxtForReman(glblCmpyCd, hdrT.wmsSoId.getValue()));
                if (ZYPCommonFunc.hasValue(hdrT.soShipViaCd)) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, hdrT.soShipViaCd.getValue() + tmpSfx);
                    ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, hdrT.shipViaDescTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, VAL_RTE_CD_TXT_1 + tmpSfx);
                    ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, VAL_RTE_NM_TXT_GROUND);
                }
            // END 2018/05/11 S.Katsuma [QC#24714,ADD]
            } else if (wmsShipViaRteMapT != null && ZYPCommonFunc.hasValue(wmsShipViaRteMapT.rteGuideNum)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.carrCdTxt, wmsShipViaRteMapT.rteGuideNum);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, wmsShipViaRteMapT.rteGuideNum.getValue() + tmpSfx);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, wmsShipViaRteMapT.wmsDescTxt);
            } else if (ZYPCommonFunc.hasValue(hdrT.soShipViaCd)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.carrCdTxt, hdrT.soShipViaCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, hdrT.soShipViaCd.getValue() + tmpSfx);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, hdrT.shipViaDescTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.carrCdTxt, VAL_CARR_CD_TXT_1);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteCdTxt, VAL_RTE_CD_TXT_1 + tmpSfx);
                ZYPEZDItemValueSetter.setValue(algi110001T.rteNmTxt, VAL_RTE_NM_TXT_GROUND);
            }
            WMS_FRT_OUT_CD_MAPTMsg wmsFrtOutCdMapT = getWmsFrtOutCdMap(hdrT.whCd.getValue(), hdrT.frtOutCd.getValue());
            if (wmsFrtOutCdMapT == null) {
                if (VAL_CHRG_TO_CUST_CD_E6026.equals(hdrT.chrgToCustCd.getValue()) || VAL_CHRG_TO_CUST_CD_E6A26.equals(hdrT.chrgToCustCd.getValue()) || VAL_CHRG_TO_CUST_CD_E6338.equals(hdrT.chrgToCustCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.frtTermTxt, VAL_FRT_TERM_TXT_C);
                }
            } else if (VAL_WMS_FRT_OUT_CD_0.equals(wmsFrtOutCdMapT.wmsFrtOutCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.frtTermTxt, VAL_FRT_TERM_TXT_P);
            } else if (VAL_WMS_FRT_OUT_CD_1.equals(wmsFrtOutCdMapT.wmsFrtOutCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.frtTermTxt, VAL_FRT_TERM_TXT_C);
            } else if (VAL_WMS_FRT_OUT_CD_2.equals(wmsFrtOutCdMapT.wmsFrtOutCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.frtTermTxt, VAL_FRT_TERM_TXT_H);
            } else if (VAL_WMS_FRT_OUT_CD_3.equals(wmsFrtOutCdMapT.wmsFrtOutCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.frtTermTxt, VAL_FRT_TERM_TXT_O);
            }
            if (!ZYPCommonFunc.hasValue(algi110001T.frtTermTxt)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.frtTermTxt, VAL_FRT_TERM_TXT_P);
            }

            ZYPEZDItemValueSetter.setValue(algi110001T.parkAndMarkSeptTxt, ZYPConstant.FLG_OFF_N);
            if (ZYPConstant.FLG_ON_Y.equals(hdrT.indAsnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.ediAsnOrdTxt, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.ediAsnOrdTxt, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.shipCpltTxt, ZYPConstant.FLG_ON_Y);
            if (ZYPCommonFunc.hasValue(hdrT.cancByDtTmTs)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.cancByDtTmTsTxt, dateFormat(hdrT.cancByDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.orgCdTxt, hdrT.wmsOrdTpCd);
            // QC#17981 Mod.
            /*
            if (!WMS_ORD_TP.OUTBOUND_ITEM_CHANGE.equals(hdrT.wmsOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.divCdTxt, hdrT.wmsOrdSrcCd);
            }
            */
            if (setNlgi1100OtbdItemChangeWmsOrdCd != null && !Arrays.asList(setNlgi1100OtbdItemChangeWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.divCdTxt, hdrT.wmsOrdSrcCd);
            }
// QC#24254 MOD START
//            ZYPEZDItemValueSetter.setValue(algi110001T.subTotAmtTxt, decimalFormat(hdrT.wmsNetAmtNum.getValue(), FMT_0_00));
//            ZYPEZDItemValueSetter.setValue(algi110001T.discAmtTxt, decimalFormat(hdrT.totDiscAmtNum.getValue(), FMT_0_00));
//            ZYPEZDItemValueSetter.setValue(algi110001T.shpgAndHdlgAmtTxt, decimalFormat(hdrT.shpgHdlgAmtNum.getValue(), FMT_0_00));
//            ZYPEZDItemValueSetter.setValue(algi110001T.taxAmtTxt, decimalFormat(hdrT.totTaxAmtNum.getValue(), FMT_0_00));
//            ZYPEZDItemValueSetter.setValue(algi110001T.totAmtTxt, decimalFormat(hdrT.totOrdAmtNum.getValue(), FMT_0_00));
// QC#24254 MOD END
            if (ZYPCommonFunc.hasValue(hdrT.hostOrdDtTmTs)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.ordDtTmTsTxt, dateFormat(hdrT.hostOrdDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.custStoreNumTxt, hdrT.custStoreNum);
            ZYPEZDItemValueSetter.setValue(algi110001T.custDeptNumTxt, hdrT.wmsCustDeptNum);
            ZYPEZDItemValueSetter.setValue(algi110001T.custDcNumTxt, hdrT.custDcNum);
            ZYPEZDItemValueSetter.setValue(algi110001T.custPoTpTxt, hdrT.custPoTpCd);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipToPoTxt, hdrT.custOrdNum);
            ZYPEZDItemValueSetter.setValue(algi110001T.rtrnLbCdTxt, hdrT.rtrnLbCd);

            //QC#15674 MOD START
//            if (WMS_TRX.DIRECT_SALE_SHIPMENT.equals(hdrT.wmsTrxCd.getValue())) {
            if (WMS_TRX.REGULAR_SALES.equals(hdrT.wmsTrxCd.getValue())) {
              //QC#15674 MOD END
                // Use WMS_INBD_SO_BILL_TO.
                // QC#17981 Mod.
                /*
                if (WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(hdrT.wmsOrdTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.billCustTxt, hdrT.billToCustCd);
                */
                if (setNlgi1100InbdItemChangeWmsOrdCd != null && Arrays.asList(setNlgi1100InbdItemChangeWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) { 
                    ZYPEZDItemValueSetter.setValue(algi110001T.billCustTxt, hdrT.billToCustCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(algi110001T.billCustTxt, hdrT.chrgToCustCd);
                }
                ZYPEZDItemValueSetter.setValue(algi110001T.billNmTxt_01, billToT.wmsBillToNm_01);
                ZYPEZDItemValueSetter.setValue(algi110001T.billNmTxt_02, billToT.wmsBillToNm_02);
                ZYPEZDItemValueSetter.setValue(algi110001T.billFirstLineAddrTxt, billToT.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billScdLineAddrTxt, billToT.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billThirdLineAddrTxt, billToT.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billFrthLineAddrTxt, billToT.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billCtyTxt, billToT.ctyAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billStOrProvTxt, billToT.stCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billZipOrPostCdTxt, billToT.postCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billCtryTxt, billToT.ctryCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billPhoNumTxt, billToT.billToCtacNum);
            // QC#54989 Add Start
            } else if (WMS_ORD_TP.OUTBOUND_DC_TRANSFER.equals(hdrT.wmsOrdTpCd.getValue()) 
                    ||WMS_ORD_TP.OUTBOUND_DIPOSAL.equals(hdrT.wmsOrdTpCd.getValue())
                    ||WMS_ORD_TP.OUTBOUND_KITTING.equals(hdrT.wmsOrdTpCd.getValue())
                    ||WMS_ORD_TP.OUTBOUND_ITEM_CHANGE.equals(hdrT.wmsOrdTpCd.getValue()) ){
                ZYPEZDItemValueSetter.setValue(algi110001T.billNmTxt_01, billToT.wmsBillToNm_01);
                ZYPEZDItemValueSetter.setValue(algi110001T.billNmTxt_02, billToT.wmsBillToNm_02);
                ZYPEZDItemValueSetter.setValue(algi110001T.billFirstLineAddrTxt, billToT.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billScdLineAddrTxt, billToT.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billThirdLineAddrTxt, billToT.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billFrthLineAddrTxt, billToT.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billCtyTxt, billToT.ctyAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billStOrProvTxt, billToT.stCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billZipOrPostCdTxt, billToT.postCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billCtryTxt, billToT.ctryCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billPhoNumTxt, billToT.billToCtacNum);
            // QC#54989 Add End
            } else {
                // Use WMS_INBD_SO_CHRG_TO.
                ZYPEZDItemValueSetter.setValue(algi110001T.billCustTxt, hdrT.chrgToCustCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billNmTxt_01, chrgToT.wmsChrgToNm_01);
                ZYPEZDItemValueSetter.setValue(algi110001T.billNmTxt_02, chrgToT.wmsChrgToNm_02);
                ZYPEZDItemValueSetter.setValue(algi110001T.billFirstLineAddrTxt, chrgToT.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billScdLineAddrTxt, chrgToT.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billThirdLineAddrTxt, chrgToT.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billFrthLineAddrTxt, chrgToT.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billCtyTxt, chrgToT.ctyAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.billStOrProvTxt, chrgToT.stCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billZipOrPostCdTxt, chrgToT.postCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billCtryTxt, chrgToT.ctryCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.billPhoNumTxt, chrgToT.chrgToCtacNum);
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.shipCustTxt, shipToT.wmsCustCd);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipNmTxt_01, shipToT.wmsShipToNm_01);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipNmTxt_02, shipToT.wmsShipToNm_02);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipFirstLineAddrTxt, shipToT.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipScdLineAddrTxt, shipToT.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipThirdLineAddrTxt, shipToT.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(algi110001T.shipFrthLineAddrTxt, shipToT.frthLineAddr);

            // QC#17981 Mod.
            /*
            if (WMS_ORD_TP.OUTBOUND_EXPORT.equals(hdrT.wmsOrdTpCd.getValue()) && !ZYPCommonFunc.hasValue(shipToT.ctyAddr)) {
                if (ZYPCommonFunc.hasValue(shipToT.frthLineAddr) && shipToT.frthLineAddr.getValue().length() > VAL_SHIP_CTY_TXT_SIZE) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtyTxt, shipToT.frthLineAddr.getValue().substring(0, VAL_SHIP_CTY_TXT_SIZE));
                } else {
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtyTxt, shipToT.frthLineAddr);
                }
            */
            if (setNlgi1100OtbdExportWmsOrdCd != null && Arrays.asList(setNlgi1100OtbdExportWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue()) && !ZYPCommonFunc.hasValue(shipToT.ctyAddr)) {
                if (ZYPCommonFunc.hasValue(shipToT.frthLineAddr) && shipToT.frthLineAddr.getValue().length() > VAL_SHIP_CTY_TXT_SIZE) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtyTxt, shipToT.frthLineAddr.getValue().substring(0, VAL_SHIP_CTY_TXT_SIZE));
                } else {
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtyTxt, shipToT.frthLineAddr);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipCtyTxt, shipToT.ctyAddr);
            }
            if (ZYPCommonFunc.hasValue(shipToT.stCd)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipStOrProvTxt, shipToT.stCd);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipStOrProvTxt, VAL_ST_CD_XX);
            }
            if (ZYPCommonFunc.hasValue(shipToT.postCd)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipZipOrPostCdTxt, shipToT.postCd);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipZipOrPostCdTxt, VAL_POST_CD_99999);
            }
            if (VAL_ST_PUERTO_RICO.equals(shipToT.stCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipCtryTxt, shipToT.stCd);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipCtryTxt, shipToT.ctryCd);
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.shipCtacNmTxt, shipToT.wmsShipToCtacNm);
            if (ZYPCommonFunc.hasValue(shipToT.shipToCtacNum)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipPhoNumTxt, shipToT.shipToCtacNum);
            } else if (rtrnToT != null && ZYPCommonFunc.hasValue(rtrnToT.rtrnToCtacNum)) {
                ZYPEZDItemValueSetter.setValue(algi110001T.shipPhoNumTxt, rtrnToT.rtrnToCtacNum);
            }
            
            // QC#21657-1 Modify Start.
            SHPG_ORDTMsg shpgOrdT = getShpgOrd(glblCmpyCd, hdrT.wmsSoId.getValue());
            if (shpgOrdT != null && SCE_ORD_TP.TECH_REQUEST.equals(shpgOrdT.sceOrdTpCd.getValue())) {
                PRCH_REQTMsg prchReqT = getPrchReq(glblCmpyCd, shpgOrdT.srcOrdNum.getValue());
                if (prchReqT != null && ZYPCommonFunc.hasValue(prchReqT.shipToCustCd)) {
                    // [Drop Ship] Update Ship to Address.
                    // QC#24941 Digit Error Fix Source
                    String[] locNum = new String[] {prchReqT.shipToLocNm.getValue(), prchReqT.shipToAddlLocNm.getValue() };
                    String[] cutlocNum = getCutString(locNum, MAX_LENGTH_SO_CUST_LINE_LOC_NM);

                    String[] LineAddr = new String[] {prchReqT.shipToFirstLineAddr.getValue(), prchReqT.shipToScdLineAddr.getValue(), prchReqT.shipToThirdLineAddr.getValue(), prchReqT.shipToFrthLineAddr.getValue() };
                    String[] cutLineAddr = getCutString(LineAddr, MAX_LENGTH_SO_CUST_LINE_LOC_NM);

                    ZYPEZDItemValueSetter.setValue(algi110001T.shipNmTxt_01, cutlocNum[0]);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipNmTxt_02, cutlocNum[1]);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipFirstLineAddrTxt, cutLineAddr[0]);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipScdLineAddrTxt, cutLineAddr[1]);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipThirdLineAddrTxt, cutLineAddr[2]);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipFrthLineAddrTxt, cutLineAddr[3]);

                    // Digit Error Source.
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipNmTxt_01, prchReqT.shipToLocNm);
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipNmTxt_02, prchReqT.shipToAddlLocNm);
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipFirstLineAddrTxt, prchReqT.shipToFirstLineAddr);
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipScdLineAddrTxt, prchReqT.shipToScdLineAddr);
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipThirdLineAddrTxt, prchReqT.shipToThirdLineAddr);
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipFrthLineAddrTxt, prchReqT.shipToFrthLineAddr);
                    // QC#24941 End
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtyTxt, prchReqT.shipToCtyAddr);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipStOrProvTxt, prchReqT.shipToStCd);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipZipOrPostCdTxt, prchReqT.shipToPostCd);
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtryTxt, prchReqT.shipToCtryCd);
                    // START 2019/08/07 T.Ogura [QC#52399,MOD]
//                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtacNmTxt, prchReqT.ctacPsnNm);
                    String[] shipCtacNm = new String[]{prchReqT.ctacPsnNm.getValue()};
                    String[] custShipCtacNmTxt = getCutString(shipCtacNm, algi110001T.getAttr("shipCtacNmTxt").getDigit());
                    ZYPEZDItemValueSetter.setValue(algi110001T.shipCtacNmTxt, custShipCtacNmTxt[0]);
                    // END   2019/08/07 T.Ogura [QC#52399,MOD]
                }
            }
            // QC#21657-1 Modify End.

            if (rtrnToT != null) {
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnCustTxt, rtrnToT.wmsCustCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnNmTxt_01, rtrnToT.wmsRtrnToNm_01);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnNmTxt_02, rtrnToT.wmsRtrnToNm_02);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnFirstLineAddrTxt, rtrnToT.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnScdLineAddrTxt, rtrnToT.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnThirdLineAddrTxt, rtrnToT.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnFrthLineAddrTxt, rtrnToT.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnCtyTxt, rtrnToT.ctyAddr);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnStOrProvTxt, rtrnToT.stCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnZipOrPostCdTxt, rtrnToT.postCd);
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnCtryTxt, rtrnToT.ctryCd);
                if (ZYPCommonFunc.hasValue(rtrnToT.wmsRtrnToCtacNm)) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.rtrnCtacNmTxt, rtrnToT.wmsRtrnToCtacNm);
                } else {
                    ZYPEZDItemValueSetter.setValue(algi110001T.rtrnCtacNmTxt, VAL_RTRN_CTAC_NM_TXT_ATTN);
                }
                ZYPEZDItemValueSetter.setValue(algi110001T.rtrnPhoNumTxt, rtrnToT.rtrnToCtacNum);
            }

            // QC#17981 Mod.
            /*
            if (!WMS_ORD_TP.INBOUND_ITEM_CHANGE.equals(hdrT.wmsOrdTpCd.getValue())) {
            */
            if (notSetNlgi1100InbdItemChangeWmsOrdCd != null && !Arrays.asList(notSetNlgi1100InbdItemChangeWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) { 
                ZYPEZDItemValueSetter.setValue(algi110001T.billAcctNum, hdrT.billAcctNum);
                if (ZYPCommonFunc.hasValue(hdrT.endCustOrdNum) && ZYPCommonFunc.hasValue(hdrT.altCustOrdNum)) {
                    String endCustOrdNum = hdrT.endCustOrdNum.getValue() + VAL_SLASH + hdrT.altCustOrdNum.getValue();
                    if (endCustOrdNum.length() > VAL_END_CUST_ORD_NUM_SIZE) {
                        ZYPEZDItemValueSetter.setValue(algi110001T.endCustOrdNum, endCustOrdNum.substring(0, VAL_END_CUST_ORD_NUM_SIZE));
                    } else {
                        ZYPEZDItemValueSetter.setValue(algi110001T.endCustOrdNum, endCustOrdNum);
                    }
                } else if (ZYPCommonFunc.hasValue(hdrT.endCustOrdNum)) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.endCustOrdNum, hdrT.endCustOrdNum);
                } else if (ZYPCommonFunc.hasValue(hdrT.altCustOrdNum)) {
                    ZYPEZDItemValueSetter.setValue(algi110001T.endCustOrdNum, hdrT.altCustOrdNum);
                }

                if (VAL_TP_VND_CD_HAZMAT.equals(hdrT.tpVndCd.getValue())) {
                    WMS_CTRY_CTACTMsg wmsCtryCtacT = getWmsCtryCtac(VAL_DEF_WMS_ORD_SRC_CD, hdrT.whCd.getValue(), shipToT.ctryCd.getValue());
                    if (wmsCtryCtacT == null) {
                        wmsCtryCtacT = getWmsCtryCtac(VAL_DEF_WMS_ORD_SRC_CD, hdrT.whCd.getValue(), VAL_DEF_CTRY_CD);
                    }
                    if (wmsCtryCtacT == null) {
                        wmsCtryCtacT = getWmsCtryCtac(VAL_DEF_WMS_ORD_SRC_CD, VAL_DEF_WH_CD, VAL_DEF_CTRY_CD);
                    }
                    if (wmsCtryCtacT != null) {
                        ZYPEZDItemValueSetter.setValue(algi110001T.wmsCtryCtacNum, wmsCtryCtacT.wmsCtryCtacNum);
                        ZYPEZDItemValueSetter.setValue(algi110001T.wmsCtryCtacNm, wmsCtryCtacT.wmsCtryCtacNm);
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.trxHdrNum, hdrT.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(algi110001T.carrCd, hdrT.carrCd);
            ZYPEZDItemValueSetter.setValue(algi110001T.shpgSvcLvlCd, hdrT.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(algi110001T.usrCdIstlRefTxt, hdrT.usrCdIstlRefTxt);
            ZYPEZDItemValueSetter.setValue(algi110001T.rtrnItemInclFlg, hdrT.rtrnItemInclFlg);
            if (ZYPCommonFunc.hasValue(hdrT.schdDelyDt)) {
                String schdDelyDtTmTs = hdrT.schdDelyDt.getValue().concat("000000");
                ZYPEZDItemValueSetter.setValue(algi110001T.schdDelyDtTxt, dateFormat(schdDelyDtTmTs, FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            }
            ZYPEZDItemValueSetter.setValue(algi110001T.svcConfigMstrPk, hdrT.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(algi110001T.asmReqFlg, hdrT.asmReqFlg);
            ZYPEZDItemValueSetter.setValue(algi110001T.stageActCd, hdrT.stageActCd);
            ZYPEZDItemValueSetter.setValue(algi110001T.stageRecStsCd, hdrT.stageRecStsCd);

            ZYPEZDItemValueSetter.setValue(algi110001T.addlCarrSvcCd_01, hdrT.addlCarrSvcCd_01);
            ZYPEZDItemValueSetter.setValue(algi110001T.addlCarrSvcCd_02, hdrT.addlCarrSvcCd_02);
            ZYPEZDItemValueSetter.setValue(algi110001T.addlCarrSvcCd_03, hdrT.addlCarrSvcCd_03);
            ZYPEZDItemValueSetter.setValue(algi110001T.addlCarrSvcCd_04, hdrT.addlCarrSvcCd_04);
            ZYPEZDItemValueSetter.setValue(algi110001T.addlCarrSvcCd_05, hdrT.addlCarrSvcCd_05);
            ZYPEZDItemValueSetter.setValue(algi110001T.carrSvcHldAtLocFlg, hdrT.carrSvcHldAtLocFlg);
            ZYPEZDItemValueSetter.setValue(algi110001T.custRefTxt_01, hdrT.custRefTxt_01);
            ZYPEZDItemValueSetter.setValue(algi110001T.custRefTxt_02, hdrT.custRefTxt_02);
            ZYPEZDItemValueSetter.setValue(algi110001T.custRefTxt_03, hdrT.custRefTxt_03);
            ZYPEZDItemValueSetter.setValue(algi110001T.custEmlTxt, hdrT.custEmlTxt);
            ZYPEZDItemValueSetter.setValue(algi110001T.rtePathCd, hdrT.rtePathCd);
            ZYPEZDItemValueSetter.setValue(algi110001T.rtrnTagReqFlg, hdrT.rtrnTagReqFlg);
            ZYPEZDItemValueSetter.setValue(algi110001T.otbdSrcOrdTpTxt, hdrT.otbdSrcOrdTpTxt);
            ZYPEZDItemValueSetter.setValue(algi110001T.prtToCustFlg, hdrT.prtToCustFlg);
            ZYPEZDItemValueSetter.setValue(algi110001T.otbdSrcOrdNum, hdrT.otbdSrcOrdNum);
            ZYPEZDItemValueSetter.setValue(algi110001T.slsOrdAdminNm, hdrT.slsOrdAdminNm);
            ZYPEZDItemValueSetter.setValue(algi110001T.slsRepPsnNm, hdrT.slsRepPsnNm);
            ZYPEZDItemValueSetter.setValue(algi110001T.svcLbFmtTxt, hdrT.svcLbFmtTxt);
            // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
            if (soIf1AddValueMap != null && !soIf1AddValueMap.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(algi110001T.mdlNm, (String)soIf1AddValueMap.get(T_MDL_NM));
                ZYPEZDItemValueSetter.setValue(algi110001T.svcLbGrpCd, (String)soIf1AddValueMap.get(SVC_LB_GRP_CD));
            } else {
                algi110001T.mdlNm.clear();
                algi110001T.svcLbGrpCd.clear();
            }
            // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]

            EZDTBLAccessor.insert(algi110001T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi110001T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI1100_01, TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi110001T.ordIdTxt.getValue()) });
            }
            return soSeqNumber;
        }

        /**
         * createSoIf02
         * @param hdrT
         * @param dtlT
         * @param soSeqNumber
         * @param wmsOrdLineNum
         * @param wmsQtyOrd
         * @param pausedPickExceptionCd 08/03/2017 CITS S.Endo Add Sol#072(QC#18386)
         * @return
         */
        private BigDecimal createSoIf02(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_DTLTMsg dtlT, BigDecimal soSeqNumber, 
                BigDecimal wmsOrdLineNum, BigDecimal wmsQtyOrd, String pausedPickExceptionCd) {


            NLGI1100_02TMsg algi110002T = new NLGI1100_02TMsg();
            ZYPEZDItemValueSetter.setValue(algi110002T.interfaceId, wmsSoDnldSoIntfcId);
            ZYPEZDItemValueSetter.setValue(algi110002T.transactionId, soTrxId);
            ZYPEZDItemValueSetter.setValue(algi110002T.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi110002T.unitId, soUnitId);
            soSeqNumber = soSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi110002T.seqNumber, soSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi110002T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_02);
            ZYPEZDItemValueSetter.setValue(algi110002T.ordIdTxt, dtlT.wmsSoId);
            ZYPEZDItemValueSetter.setValue(algi110002T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.OUTBOUND, hdrT.wmsOrdTpCd.getValue()));

            ZYPEZDItemValueSetter.setValue(algi110002T.ordLineTxt, getString(wmsOrdLineNum));
//            ZYPEZDItemValueSetter.setValue(algi110002T.ordLineTxt, getString(dtlT.wmsLineNum.getValue()));

            ZYPEZDItemValueSetter.setValue(algi110002T.itemCdTxt, dtlT.wmsMdseCd);
            //ZYPEZDItemValueSetter.setValue(algi110002T.packCdTxt, VAL_PACK_CD_TXT_ALL);
            ZYPEZDItemValueSetter.setValue(algi110002T.packCdTxt, getPackageCode(dtlT.rtlWhCd.getValue(), dtlT.rtlSwhCd.getValue()));

//            ZYPEZDItemValueSetter.setValue(algi110002T.qtyOrdTxt, decimalFormat(dtlT.wmsShipQty.getValue(), FMT_0));
            ZYPEZDItemValueSetter.setValue(algi110002T.qtyOrdTxt, decimalFormat(wmsQtyOrd, FMT_0));

            ZYPEZDItemValueSetter.setValue(algi110002T.custPoLineTxt, getString(dtlT.essPoSqNum.getValue()));
            ZYPEZDItemValueSetter.setValue(algi110002T.custItemCd, dtlT.custMdseCd);
// QC#24254 MOD START
//            ZYPEZDItemValueSetter.setValue(algi110002T.unitPrcTxt, decimalFormat(dtlT.unitPrcAmtNum.getValue(), FMT_0_00));
// QC#24254 MOD END
            if (ZYPCommonFunc.hasValue(dtlT.s80StkStsCd) && !STK_STS.GOOD.equals(dtlT.s80StkStsCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO))) {
                ZYPEZDItemValueSetter.setValue(algi110002T.hldCdTxt, dtlT.s80StkStsCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO));
            }
            ZYPEZDItemValueSetter.setValue(algi110002T.usrCdTxt_01, VAL_USR_CD_TXT_ALL);
            ZYPEZDItemValueSetter.setValue(algi110002T.usrCdTxt_02, VAL_USR_CD_TXT_ALL);
            ZYPEZDItemValueSetter.setValue(algi110002T.usrCdTxt_03, VAL_USR_CD_TXT_ALL);
            if (ZYPConstant.FLG_ON_Y.equals(dtlT.indSerId.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110002T.serNumReqTxt, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(algi110002T.serNumUomTxt, VAL_SER_NUM_UOM_TXT_EA);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110002T.serNumReqTxt, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(algi110002T.openOrdQtyTxt, decimalFormat(dtlT.wmsOrdQty.getValue(), FMT_0));
            ZYPEZDItemValueSetter.setValue(algi110002T.origSlsOrdIdTxt, dtlT.origSoId);

            ZYPEZDItemValueSetter.setValue(algi110002T.origOrdLineTxt, getString(dtlT.origLineNum.getValue()));
//            ZYPEZDItemValueSetter.setValue(algi110002T.origOrdLineTxt, getString(wmsOrdLineNum));

            if (ZYPConstant.FLG_ON_Y.equals(dtlT.indConfigFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi110002T.itemSetTxt, dtlT.mdseCdSetCd.getValue() + VAL_ITEM_CD_TXT_SFX);
            } else {
                ZYPEZDItemValueSetter.setValue(algi110002T.itemSetTxt, dtlT.mdseCdSetCd);
            }
            ZYPEZDItemValueSetter.setValue(algi110002T.itemSetDescTxt, dtlT.mdseCdSetDescTxt);
            ZYPEZDItemValueSetter.setValue(algi110002T.itemSetQtyShipTxt, decimalFormat(dtlT.shipSetQty.getValue(), FMT_0));
// QC#24254 MOD START
//            if (ZYPCommonFunc.hasValue(dtlT.unitPrcAmtNum.getValue()) && BigDecimal.ZERO.compareTo(dtlT.unitPrcAmtNum.getValue()) == -1) {
//                ZYPEZDItemValueSetter.setValue(algi110002T.prcAmtTxt, decimalFormat(dtlT.unitPrcAmtNum.getValue(), FMT_0_00));
//            }
//            if (ZYPCommonFunc.hasValue(dtlT.wmsNetAmtNum.getValue()) && BigDecimal.ZERO.compareTo(dtlT.wmsNetAmtNum.getValue()) == -1) {
//                ZYPEZDItemValueSetter.setValue(algi110002T.amtNumTxt, decimalFormat(dtlT.wmsNetAmtNum.getValue(), FMT_0_00));
//            }
// QC#24254 MOD END
            ZYPEZDItemValueSetter.setValue(algi110002T.svcConfigMstrPk, dtlT.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(algi110002T.backOrdImpctTpCd, dtlT.backOrdImpctTpCd);
            ZYPEZDItemValueSetter.setValue(algi110002T.wmsOrdUomCd, dtlT.wmsOrdUomCd);
            ZYPEZDItemValueSetter.setValue(algi110002T.poLineTxt, dtlT.poLineTxt);
            ZYPEZDItemValueSetter.setValue(algi110002T.usrCdIstlRefTxt, dtlT.usrCdIstlRefTxt);
            ZYPEZDItemValueSetter.setValue(algi110002T.backOrdFlg, dtlT.backOrdFlg);
            ZYPEZDItemValueSetter.setValue(algi110002T.rmvConfigFlg, dtlT.rmvConfigFlg);
            ZYPEZDItemValueSetter.setValue(algi110002T.origOrdQtyTxt, dtlT.origOrdQty.getValue().toPlainString());

            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
            ZYPEZDItemValueSetter.setValue(algi110002T.pauPickExCd, pausedPickExceptionCd);
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
            EZDTBLAccessor.insert(algi110002T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi110002T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI1100_02, TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi110002T.ordIdTxt.getValue()) });
            }

            // START 2017/12/20 S.Katsuma [QC#22592,ADD]
            updateShpgOrdDtl(dtlT);
            // END 2017/12/20 S.Katsuma [QC#22592,ADD]

            return soSeqNumber;
        }
        /**
         * Create SO Interface Table(NLGI1100_02TMsg) List.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         * @param soSeqNumber SO_SEQ_NUMBER
         * @return SO_SEQ_NUMBER
         */
        private BigDecimal createSoIf02(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList, BigDecimal soSeqNumber) {

            //  Line Num TO WMS
            int wmsOrdLineNum = 0;
            //  Qty TO WMS
            int wmsQtyOrd = 0;

            // SoNum
            String soNum = hdrT.wmsSoId.getValue();

            // Serial specification to check whether there at RWS units at the beginning 
            boolean extSerSpec = isExistsSerialSpec(glblCmpyCd, soNum);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("soNum", hdrT.wmsSoId.getValue());
            List<Map<String, Object>> soDtlList = null;

            if (STAGE_ACT_UPDATE.equals(hdrT.stageActCd.getValue())) {
                dtlTList.clear();

                // Update WMS SO Download
                ssmParam.put("stageActCd", STAGE_ACT_NEW);
                BigDecimal wmsSqNum = (BigDecimal) ssmBatchClient.queryObject("getWmsSqNum", ssmParam);
                ssmParam.put("wmsSqNum", wmsSqNum);
                ssmParam.put("whCd", hdrT.whCd.getValue());
                soDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getWmsSoDtl", ssmParam);

                for (Map<String, Object> soDtl : soDtlList) {
                    WMS_INBD_SO_DTLTMsg dtlT = new WMS_INBD_SO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, (BigDecimal) soDtl.get(COL_SO_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, VAL_INTFC_TP_ID_02);
                    ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, VAL_INTFC_REC_TP_ID_DTL);
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsSoId, hdrT.wmsSoId.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) soDtl.get(COL_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) soDtl.get(COL_S80_STK_STS_CD_FROM));
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
                    ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCdToCd, (String) soDtl.get(COL_S80_STK_STS_CD_TO));
                    ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetCd, (String) soDtl.get(COL_SET_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetDescTxt, (String) soDtl.get(COL_SET_MDSE_NM));
                    ZYPEZDItemValueSetter.setValue(dtlT.shipSetQty, (BigDecimal) soDtl.get(COL_SET_SHPG_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.unitInsdQty, (BigDecimal) soDtl.get(COL_IN_EACH_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.totWtAmtNum, (BigDecimal) soDtl.get(COL_TOT_SHPG_WT));
                    ZYPEZDItemValueSetter.setValue(dtlT.totVolAmtNum, (BigDecimal) soDtl.get(COL_TOT_SHPG_VOL));
                    ZYPEZDItemValueSetter.setValue(dtlT.estCseAmtNum, (BigDecimal) soDtl.get(COL_CASES));
                    ZYPEZDItemValueSetter.setValue(dtlT.estPltAmtNum, (BigDecimal) soDtl.get(COL_PALLETS));
                    ZYPEZDItemValueSetter.setValue(dtlT.batCptrReqFlg, (String) soDtl.get(COL_BAT_NUM_TAKE_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.indConfigFlg, (String) soDtl.get(COL_CONFIG_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, (String) soDtl.get(COL_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) soDtl.get(COL_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.svcConfigMstrPk, (BigDecimal) soDtl.get(COL_PICK_SVC_CONFIG_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdImpctTpCd, (String) soDtl.get(COL_BACK_ORD_IMPCT_TP_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdUomCd, WMS_UOM.EACH);
                    ZYPEZDItemValueSetter.setValue(dtlT.poLineTxt, (String) soDtl.get(COL_TRX_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(dtlT.usrCdIstlRefTxt, (String) soDtl.get(COL_CONFIG_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(dtlT.rmvConfigFlg, (String) soDtl.get(COL_RMV_CONFIG_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, (BigDecimal) soDtl.get("ORIG_ORD_QTY"));

                    dtlTList.add(dtlT);

                }
            } 

            for (WMS_INBD_SO_DTLTMsg dtlT : dtlTList) {
                if (BigDecimal.ZERO.compareTo(dtlT.wmsShipQty.getValue()) != -1) {
                    continue;
                }
                // Original Line Num
                BigDecimal orgOrdLineNum = dtlT.wmsLineNum.getValue();
                // Original Qty
                BigDecimal orgQtyOrd = dtlT.wmsShipQty.getValue(); 

                // To check whether a serial specified
                String soSlpNum  = ZYPCommonFunc.leftPad(String.valueOf(dtlT.wmsLineNum.getValue()), 3,"0");
                List<String> soSerList = getSoSerList(glblCmpyCd, dtlT.wmsSoId.getValue(), soSlpNum);

                //08/10/2017 CITS S.Endo Add Sol#072(QC#18386) START
                //get PausedPickExceptionCd
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", glblCmpyCd);
                ssmParam.put("soId", hdrT.wmsSoId.getValue());
                ssmParam.put("soSlpNum", dtlT.wmsLineNum.getValue());
                String pausedPickExceptionCd = (String) ssmBatchClient.queryObject("getPausedPickExptCd", ssmParam);
                if (!ZYPCommonFunc.hasValue(pausedPickExceptionCd)) {
                    pausedPickExceptionCd = ZYPConstant.FLG_OFF_0;
                }
                //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
                if (soSerList != null && soSerList.size() > 0) {
                    // This is serial specified count
                    int serTakeCnt = soSerList.size();

                    // Repeat the split registration
                    for (int i = 0; i < serTakeCnt; i++) {
                        wmsOrdLineNum++;
                        wmsQtyOrd = 1;

                        // Create SoIf02
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) START
                        //soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd));
                        soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), pausedPickExceptionCd);
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) END

                        // Create SoIf04
                        String serNum = null;
                        if (ZYPCommonFunc.hasValue(soSerList.get(i))) {
                            serNum = soSerList.get(i);
                        }
                        soSeqNumber = createSoIf04(hdrT, dtlT, soSeqNumber, i, serNum, new BigDecimal(wmsOrdLineNum));

                        // Create WMS_SO_ORIG_LINE_SAVE
                        createWmsSoSave(soNum, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), orgOrdLineNum);

                    }
                    // If the serial specified count and quantity are different
                    if (orgQtyOrd.intValue() != serTakeCnt) {
                        // Register Together
                        wmsOrdLineNum++;
                        wmsQtyOrd = orgQtyOrd.intValue() - serTakeCnt;
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) START
                        //soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber,  new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd));
                        soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber,  new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), pausedPickExceptionCd);
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) END
                        // Create WMS_SO_ORIG_LINE_SAVE
                        createWmsSoSave(soNum, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), orgOrdLineNum);
                    }
                } else {
                    if(extSerSpec) {
                        wmsOrdLineNum++;
                        // Create SoIf02
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) START
                        //soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber, new BigDecimal(wmsOrdLineNum), orgQtyOrd);
                        soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber, new BigDecimal(wmsOrdLineNum), orgQtyOrd, pausedPickExceptionCd);
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) END
                        // Create WMS_SO_ORIG_LINE_SAVE
                        createWmsSoSave (soNum, new BigDecimal(wmsOrdLineNum), orgQtyOrd, orgOrdLineNum);
                    } else {
                        // Create SoIf02 (The serial specified does not exist, not registration To WMS_RWS_ORIG_LINE_SAVE)
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) START
                        //soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber, orgOrdLineNum, orgQtyOrd);
                        soSeqNumber = createSoIf02(hdrT, dtlT, soSeqNumber, orgOrdLineNum, orgQtyOrd, pausedPickExceptionCd);
                        //08/10/2017 CITS S.Endo Mod Sol#072(QC#18386) END
                    }
                }
                

//                soSeqNumber = createSoIf04(hdrT, dtlT, soSeqNumber);
            }
            return soSeqNumber;
        }
        private void createWmsSoSave(String soNum, BigDecimal wmsOrdLineNum, BigDecimal wmsQty, BigDecimal orgOrdLineNum) {
            WMS_SO_ORIG_LINE_SAVETMsg tMsg = new WMS_SO_ORIG_LINE_SAVETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.soNum, soNum);
            ZYPEZDItemValueSetter.setValue(tMsg.soToWmsDtlLineNum, wmsOrdLineNum.toPlainString());

            WMS_SO_ORIG_LINE_SAVETMsg findMsg = (WMS_SO_ORIG_LINE_SAVETMsg) EZDTBLAccessor.findByKey(tMsg);
            if (findMsg != null) {
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0040E, new String[] {TBL_WMS_SO_ORIG_LINE_SAVE, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM, COL_SO_TO_WMS_DTL_LINE_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, soNum, wmsOrdLineNum.toPlainString()) });
                }
            }

            ZYPEZDItemValueSetter.setValue(tMsg.soToWmsQty, wmsQty);
            ZYPEZDItemValueSetter.setValue(tMsg.soOrigDtlLineNum, orgOrdLineNum.toPlainString());

            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_SO_ORIG_LINE_SAVE 
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM, COL_SO_TO_WMS_DTL_LINE_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, soNum, wmsOrdLineNum.toPlainString()) });
            }

        }
        /**
         * createWmsRwsSave
         * @param wmsOrdLineNum
         * @param wmsQty
         * @param orgOrdLineNum
         */
        private void createWmsRwsSave(String rwsNum, BigDecimal wmsOrdLineNum, BigDecimal wmsQty, BigDecimal orgOrdLineNum) {

            WMS_RWS_ORIG_LINE_SAVETMsg tMsg = new WMS_RWS_ORIG_LINE_SAVETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, rwsNum);
//            ZYPEZDItemValueSetter.setValue(tMsg.rwsToWmsDtlLineNum, wmsOrdLineNum.toPlainString());
            ZYPEZDItemValueSetter.setValue(tMsg.rwsToWmsDtlLineNum, NLXC014001.nullToZero(wmsOrdLineNum).multiply(VAL_ORD_LINE_TXT_MUL).toPlainString());

            
            
            WMS_RWS_ORIG_LINE_SAVETMsg findMsg = (WMS_RWS_ORIG_LINE_SAVETMsg) EZDTBLAccessor.findByKey(tMsg);
            if (findMsg != null) {
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0040E, new String[] {TBL_WMS_RWS_ORIG_LINE_SAVE, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM, COL_RWS_TO_WMS_DTL_LINE_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, rwsNum, wmsOrdLineNum.toPlainString()) });
                }
            }

            ZYPEZDItemValueSetter.setValue(tMsg.rwsToWmsQty, wmsQty);
//            ZYPEZDItemValueSetter.setValue(tMsg.rwsOrigDtlLineNum, orgOrdLineNum.toPlainString());
            ZYPEZDItemValueSetter.setValue(tMsg.rwsOrigDtlLineNum, NLXC014001.nullToZero(orgOrdLineNum).multiply(VAL_ORD_LINE_TXT_MUL).toPlainString());

            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_RWS_ORIG_LINE_SAVE, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM, COL_RWS_TO_WMS_DTL_LINE_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, rwsNum, wmsOrdLineNum.toPlainString()) });
            }
        }
        /**
         * Create SO Interface Table(NLGI1100_03TMsg) List.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param textTList WMS_INBD_SO_TEXTTMsg List
         * @param soSeqNumber SO_SEQ_NUMBER
         * @return SO_SEQ_NUMBER
         */
        private BigDecimal createSoIf03(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_TEXTTMsg textT, BigDecimal soSeqNumber) {

            if (textT == null) {
                return soSeqNumber;
            }

            if (!WMS_TXT.CPO_INFORMATION.equals(textT.wmsTxtCd.getValue())) {
                return soSeqNumber;
            }
            String ordCmntTxt = (NLXC014001.nullToEmpty(textT.inbdSoMsgTxt_01.getValue()) + NLXC014001.nullToEmpty(textT.inbdSoMsgTxt_02.getValue()) //
                    + NLXC014001.nullToEmpty(textT.inbdSoMsgTxt_03.getValue()) + NLXC014001.nullToEmpty(textT.inbdSoMsgTxt_04.getValue())).replaceAll("\\s{2,}", VAL_BLANK).trim();
            if (!ZYPCommonFunc.hasValue(ordCmntTxt)) {
                return soSeqNumber;
            }

            int ordCmntSqTxt = 1;
            int len = 0;
            for (int pos = 0; pos < ordCmntTxt.length(); pos += len, ++ordCmntSqTxt) {
                NLGI1100_03TMsg algi110003T = new NLGI1100_03TMsg();
                ZYPEZDItemValueSetter.setValue(algi110003T.interfaceId, wmsSoDnldSoIntfcId);
                ZYPEZDItemValueSetter.setValue(algi110003T.transactionId, soTrxId);
                ZYPEZDItemValueSetter.setValue(algi110003T.segmentId, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(algi110003T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_03);
                ZYPEZDItemValueSetter.setValue(algi110003T.unitId, soUnitId);
                ZYPEZDItemValueSetter.setValue(algi110003T.ordIdTxt, textT.wmsSoId);
                ZYPEZDItemValueSetter.setValue(algi110003T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.OUTBOUND, hdrT.wmsOrdTpCd.getValue()));
                soSeqNumber = soSeqNumber.add(BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(algi110003T.seqNumber, soSeqNumber);
                len = ordCmntTxt.length() - pos;
                if (len > VAL_ORD_CMNT_TXT_SIZE) {
                    len = VAL_ORD_CMNT_TXT_SIZE;
                }
                ZYPEZDItemValueSetter.setValue(algi110003T.ordCmntSqTxt, String.valueOf(ordCmntSqTxt));
                ZYPEZDItemValueSetter.setValue(algi110003T.ordCmntTxt, ordCmntTxt.substring(pos, pos + len));

                EZDTBLAccessor.insert(algi110003T);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi110003T.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI1100_03, TBL_WMS_OTBD_SO_WRK //
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, algi110003T.ordIdTxt.getValue()) });
                }
            }
            return soSeqNumber;
        }

        /**
         * Create and Insert NLGI2100_06. Call create NLGI2100_06 process.
         * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
         * @param wmsInbdPoDtlTList WMS_INBD_PO_DTLTMsg List
         * @param poSeqNumber PO_SEQ_NUMBER
         * @return PO_SEQ_NUMBER
         * @param wmsPoDnldIntfcId Interface ID
         */
//        private BigDecimal createSoIf04(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_DTLTMsg dtlT, BigDecimal soSeqNumber) {
//
//            String soSlpNum  = ZYPCommonFunc.leftPad(String.valueOf(dtlT.wmsLineNum.getValue()), 3,"0");
//            List<String> soSerList = getSoSerList(glblCmpyCd, dtlT.wmsSoId.getValue(), soSlpNum);
//            if (soSerList == null || soSerList.size() == 0) {
//                return soSeqNumber;
//            } else {
//                for (int i = 0; i < soSerList.size(); i++) {
//
//                    NLGI1100_04TMsg algi110004T = new NLGI1100_04TMsg();
//                    ZYPEZDItemValueSetter.setValue(algi110004T.interfaceId, wmsSoDnldSoIntfcId);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.transactionId, soTrxId);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.segmentId, VAL_SEGMENT_ID_1);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.unitId, soUnitId);
//                    soSeqNumber = soSeqNumber.add(BigDecimal.ONE);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.seqNumber, soSeqNumber);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_04);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.ordIdTxt, dtlT.wmsSoId);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.ordLineTxt, getString(dtlT.wmsLineNum.getValue()));
//                    ZYPEZDItemValueSetter.setValue(algi110004T.serSqTxt, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 8,"0"));
//                    ZYPEZDItemValueSetter.setValue(algi110004T.serNum, soSerList.get(i));
//                    
//
//                    EZDTBLAccessor.insert(algi110004T);
//                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi110004T.getReturnCode())) {
//                        throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI1100_04, TBL_WMS_OTBD_SO_WRK //
//                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
//                                , NLXCMsgHelper.toListedString(glblCmpyCd, algi110004T.ordIdTxt.getValue()) });
//                    }
//                }
//            }
//            return soSeqNumber;
//        }
        private BigDecimal createSoIf04(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_DTLTMsg dtlT, BigDecimal soSeqNumber, int serSqTxt, String serNum, 
                BigDecimal wmsOrdLineNum) {

//            String soSlpNum  = ZYPCommonFunc.leftPad(String.valueOf(dtlT.wmsLineNum.getValue()), 3,"0");
//            List<String> soSerList = getSoSerList(glblCmpyCd, dtlT.wmsSoId.getValue(), soSlpNum);
//            if (soSerList == null || soSerList.size() == 0) {
//                return soSeqNumber;
//            } else {
//                for (int i = 0; i < soSerList.size(); i++) {

                    NLGI1100_04TMsg algi110004T = new NLGI1100_04TMsg();
                    ZYPEZDItemValueSetter.setValue(algi110004T.interfaceId, wmsSoDnldSoIntfcId);
                    ZYPEZDItemValueSetter.setValue(algi110004T.transactionId, soTrxId);
                    ZYPEZDItemValueSetter.setValue(algi110004T.segmentId, VAL_SEGMENT_ID_1);
                    ZYPEZDItemValueSetter.setValue(algi110004T.unitId, soUnitId);
                    soSeqNumber = soSeqNumber.add(BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(algi110004T.seqNumber, soSeqNumber);
                    ZYPEZDItemValueSetter.setValue(algi110004T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_04);
                    ZYPEZDItemValueSetter.setValue(algi110004T.ordIdTxt, dtlT.wmsSoId);
//                    ZYPEZDItemValueSetter.setValue(algi110004T.ordLineTxt, getString(dtlT.wmsLineNum.getValue()));
                    ZYPEZDItemValueSetter.setValue(algi110004T.ordLineTxt, wmsOrdLineNum.toPlainString());
                    ZYPEZDItemValueSetter.setValue(algi110004T.serSqTxt, ZYPCommonFunc.leftPad(String.valueOf(serSqTxt + 1), 8,"0"));
                    ZYPEZDItemValueSetter.setValue(algi110004T.serNum, serNum);
                    

                    EZDTBLAccessor.insert(algi110004T);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi110004T.getReturnCode())) {
                        throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI1100_04, TBL_WMS_OTBD_SO_WRK //
                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                                , NLXCMsgHelper.toListedString(glblCmpyCd, algi110004T.ordIdTxt.getValue()) });
                    }
//                }
//            }
            return soSeqNumber;
        }
        /**
         * Create PO Interface Table(NLGI2100_01TMsg).
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param shipToT WMS_INBD_SO_SHIP_TOTMsg
         * @param poSeqNumber PO_SEQ_NUMBER
         * @return PO_SEQ_NUMBER
         */
        private BigDecimal createPoIf01(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_SHIP_TOTMsg shipToT, BigDecimal poSeqNumber) {

            NLGI2100_01TMsg algi210001T = new NLGI2100_01TMsg();
            ZYPEZDItemValueSetter.setValue(algi210001T.interfaceId, wmsSoDnldPoIntfcId);
            ZYPEZDItemValueSetter.setValue(algi210001T.transactionId, poTrxId);
            ZYPEZDItemValueSetter.setValue(algi210001T.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210001T.unitId, poUnitId);
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210001T.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi210001T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_01);
            ZYPEZDItemValueSetter.setValue(algi210001T.ordIdTxt, hdrT.wmsSoId);
            ZYPEZDItemValueSetter.setValue(algi210001T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.INBOUND, hdrT.wmsOrdTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(algi210001T.reqDtTmTsTxt, dateFormat(hdrT.wmsPrintDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
            ZYPEZDItemValueSetter.setValue(algi210001T.firstGrpCdTxt, hdrT.wmsTrxCd);
            ZYPEZDItemValueSetter.setValue(algi210001T.thirdGrpCdTxt, hdrT.whCd);
            ZYPEZDItemValueSetter.setValue(algi210001T.splyNmTxt, shipToT.wmsShipToNm_01.getValue());
            ZYPEZDItemValueSetter.setValue(algi210001T.splyCdTxt, hdrT.shipToCustCd);
            // QC#18794
            ZYPEZDItemValueSetter.setValue(algi210001T.stageActCd, "1");
            ZYPEZDItemValueSetter.setValue(algi210001T.stageRecStsCd, "2");

            EZDTBLAccessor.insert(algi210001T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210001T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_01, TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi210001T.ordIdTxt.getValue()) });
            }
            return poSeqNumber;
        }

        private BigDecimal createPoIf04(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_DTLTMsg dtlT, BigDecimal poSeqNumber, 
               BigDecimal wmsOrdLineNum, BigDecimal wmsQtyOrd, String mtrMachFlg, String serApvlReqFlg) {
            NLGI2100_04TMsg algi210004T = new NLGI2100_04TMsg();
            ZYPEZDItemValueSetter.setValue(algi210004T.interfaceId, wmsSoDnldPoIntfcId);
            ZYPEZDItemValueSetter.setValue(algi210004T.transactionId, poTrxId);
            ZYPEZDItemValueSetter.setValue(algi210004T.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210004T.unitId, poUnitId);
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210004T.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi210004T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_04);
            ZYPEZDItemValueSetter.setValue(algi210004T.ordIdTxt, dtlT.wmsSoId);
            ZYPEZDItemValueSetter.setValue(algi210004T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.INBOUND, hdrT.wmsOrdTpCd.getValue()));

            // Line
//            ZYPEZDItemValueSetter.setValue(algi210004T.ordLineTxt, dtlT.wmsLineNum.getValue().multiply(VAL_ORD_LINE_TXT_MULT).toString());
            ZYPEZDItemValueSetter.setValue(algi210004T.ordLineTxt, wmsOrdLineNum.multiply(VAL_ORD_LINE_TXT_MULT).toString());

            ZYPEZDItemValueSetter.setValue(algi210004T.itemCdTxt, dtlT.wmsMdseCd);
            // QTY
            // QC#14398 Mod.
//            if (isCreatePoIf04OrderType(hdrT.wmsOrdTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(dtlT.wmsShipQty.getValue().multiply(new BigDecimal(-1)), FMT_0));
//                ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(wmsQtyOrd.multiply(new BigDecimal(-1)), FMT_0));
//            } else {
//                ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(dtlT.wmsShipQty.getValue(), FMT_0));
//                ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(wmsQtyOrd, FMT_0));
//            }
            ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(wmsQtyOrd.abs(), FMT_0));

            if (isCreatePoIf04OrderType(hdrT.wmsOrdTpCd.getValue()) && ZYPCommonFunc.hasValue(dtlT.s80StkStsCd) && dtlT.s80StkStsCd.getValue().length() >= LG_CUT_STK_STS_CD_TO
                    && !STK_STS.GOOD.equals(dtlT.s80StkStsCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO))) {
                ZYPEZDItemValueSetter.setValue(algi210004T.hldCdTxt, dtlT.s80StkStsCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO));
            } else if (ZYPCommonFunc.hasValue(dtlT.s80StkStsCdToCd) && dtlT.s80StkStsCdToCd.getValue().length() >= LG_CUT_STK_STS_CD_TO
                    && !STK_STS.GOOD.equals(dtlT.s80StkStsCdToCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO))) {
                ZYPEZDItemValueSetter.setValue(algi210004T.hldCdTxt, dtlT.s80StkStsCdToCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO));
            }
            // QC#14398 Mod.
//            ZYPEZDItemValueSetter.setValue(algi210004T.packCdTxt, getPackageCode(dtlT.rtlWhCd.getValue(), dtlT.rtlSwhCd.getValue()));
            String shipToRtlSwhCd = getShipToRtlSwhCd(glblCmpyCd, dtlT.wmsSoId.getValue(), String.format("%03d", wmsOrdLineNum.intValue()));
            ZYPEZDItemValueSetter.setValue(algi210004T.packCdTxt, getPackageCode(dtlT.rtlWhCd.getValue(), shipToRtlSwhCd));

            // QC#15629
            ZYPEZDItemValueSetter.setValue(algi210004T.mtrMachFlg, mtrMachFlg);
            ZYPEZDItemValueSetter.setValue(algi210004T.serApvlReqFlg, serApvlReqFlg);

            EZDTBLAccessor.insert(algi210004T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210004T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_04, TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi210004T.ordIdTxt.getValue()) });
            }

            poSeqNumber = createPoIf05(hdrT, dtlT, poSeqNumber);
            if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
                return null;
            }
            return poSeqNumber;
        }
        /**
         * Create PO Interface Table(NLGI2100_04TMsg) List.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param dtlTList WMS_INBD_SO_DTLTMsg List
         * @param poSeqNumber PO_SEQ_NUMBER
         * @return PO_SEQ_NUMBER
         */
        private BigDecimal createPoIf04(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList, BigDecimal poSeqNumber) {
            //  Line Num TO WMS
            int wmsOrdLineNum = 0;
            //  Qty TO WMS
            int wmsQtyOrd = 0;

            // SoNum
            String soNum = hdrT.wmsSoId.getValue();

            // Serial specification to check whether there at RWS units at the beginning 
            boolean extSerSpec = isExistsSerialSpec(glblCmpyCd, soNum);

            for (WMS_INBD_SO_DTLTMsg dtlT : dtlTList) {

                // Meter Machine Flag Add QC#15629
                String mtrMachFlg = ZYPConstant.FLG_OFF_N;

                // QC#14398 Mod.
                if ((isCreatePoIf04OrderType(hdrT.wmsOrdTpCd.getValue()) && ZYPCommonFunc.hasValue(dtlT.s80StkStsCd)) || ZYPCommonFunc.hasValue(dtlT.s80StkStsCdToCd)) {

                    // Original Line Num
                    BigDecimal orgOrdLineNum = dtlT.wmsLineNum.getValue();
                    // Original Qty
                    BigDecimal orgQtyOrd = dtlT.wmsShipQty.getValue();

                    // To check whether a serial specified
                    String soSlpNum = ZYPCommonFunc.leftPad(String.valueOf(dtlT.wmsLineNum.getValue()), 3, "0");
                    List<String> soSerList = getSoSerList(glblCmpyCd, dtlT.wmsSoId.getValue(), soSlpNum);

                    if (soSerList != null && soSerList.size() > 0) {
                        // This is serial specified count
                        int serTakeCnt = soSerList.size();

                        // Repeat the split registration
                        for (int i = 0; i < serTakeCnt; i++) {
                            wmsOrdLineNum++;
                            wmsQtyOrd = 1;

                            // QC#15629
                            String serNum = null;
                            if (ZYPCommonFunc.hasValue(soSerList.get(i))) {
                                serNum = soSerList.get(i);
                            }

                            // Create PoIf04
                            mtrMachFlg = getMtrMachFlg(glblCmpyCd, serNum);
                            poSeqNumber = createPoIf04(hdrT, dtlT, poSeqNumber, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), mtrMachFlg, ZYPConstant.FLG_ON_Y);

                            // Create PoIf06
                            poSeqNumber = createPoIf06(hdrT, dtlT, poSeqNumber, i, serNum, new BigDecimal(wmsOrdLineNum));

                            // Create WMS_PO_ORIG_LINE_SAVE
                            createWmsRwsSave(soNum, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), orgOrdLineNum);

                        }
                        // If the serial specified count and quantity
                        // are different
                        if (orgQtyOrd.intValue() != serTakeCnt) {
                            // Register Together
                            wmsOrdLineNum++;
                            wmsQtyOrd = orgQtyOrd.intValue() - serTakeCnt;
                            poSeqNumber = createPoIf04(hdrT, dtlT, poSeqNumber, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), mtrMachFlg, ZYPConstant.FLG_ON_Y);

                            // Create WMS_PO_ORIG_LINE_SAVE
                            createWmsRwsSave(soNum, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), orgOrdLineNum);
                        }
                    } else {
                        if (extSerSpec) {
                            wmsOrdLineNum++;
                            // Create PoIf04
                            poSeqNumber = createPoIf04(hdrT, dtlT, poSeqNumber, new BigDecimal(wmsOrdLineNum), orgQtyOrd, mtrMachFlg, ZYPConstant.FLG_ON_Y);

                            // Create WMS_PO_ORIG_LINE_SAVE
                            createWmsRwsSave (soNum, new BigDecimal(wmsOrdLineNum), orgQtyOrd, orgOrdLineNum);
                        } else {
                            // Create PoIf04 (The serial specified does not exist, not registration To WMS_RWS_ORIG_LINE_SAVE)
                            poSeqNumber = createPoIf04(hdrT, dtlT, poSeqNumber, orgOrdLineNum, orgQtyOrd, mtrMachFlg, ZYPConstant.FLG_OFF_N);
                        }
                    }
            }
            }
            return poSeqNumber;
        }
        /**
         * createPoIf06
         * @param wmsInbdSoHdrT
         * @param wmsInbdSoDtlT
         * @param poSeqNumber
         * @param wmsPoDnldIntfcId
         * @param serSqTxt
         * @param serNum
         * @param wmsOrdLineNum
         * @return
         */
        private BigDecimal createPoIf06(WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT, WMS_INBD_SO_DTLTMsg wmsInbdSoDtlT, BigDecimal poSeqNumber, 
                int serSqTxt, String serNum, BigDecimal wmsOrdLineNum) {
            NLGI2100_06TMsg algi210006T = new NLGI2100_06TMsg();
            ZYPEZDItemValueSetter.setValue(algi210006T.interfaceId, wmsSoDnldPoIntfcId);
            ZYPEZDItemValueSetter.setValue(algi210006T.transactionId, poTrxId);
            ZYPEZDItemValueSetter.setValue(algi210006T.segmentId, VAL_SEGMENT_ID_1);
            ZYPEZDItemValueSetter.setValue(algi210006T.unitId, poUnitId);
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210006T.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi210006T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_06);
            ZYPEZDItemValueSetter.setValue(algi210006T.ordIdTxt, wmsInbdSoHdrT.wmsSoId);
            ZYPEZDItemValueSetter.setValue(algi210006T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.INBOUND, wmsInbdSoHdrT.wmsOrdTpCd.getValue()));

            // ZYPEZDItemValueSetter.setValue(algi210006T.ordLineTxt,
            // NLXC014001.nullToZero(wmsInbdPoDtlT.wmsLineNum.getValue()).multiply(VAL_ORD_LINE_TXT_MUL).toString());
//            ZYPEZDItemValueSetter.setValue(algi210006T.ordLineTxt, NLXC014001.nullToZero(wmsInbdPoDtlT.wmsLineNum.getValue()).multiply(VAL_ORD_LINE_TXT_MUL).toString());
            ZYPEZDItemValueSetter.setValue(algi210006T.ordLineTxt, wmsOrdLineNum.multiply(VAL_ORD_LINE_TXT_MULT).toString());

            ZYPEZDItemValueSetter.setValue(algi210006T.serSqTxt, ZYPCommonFunc.leftPad(String.valueOf(serSqTxt + 1), 8, "0"));
            // ZYPEZDItemValueSetter.setValue(algi210006T.serNum,
            // rwsSerArr.no(i).serNum);
            ZYPEZDItemValueSetter.setValue(algi210006T.serNum, serNum);

            EZDTBLAccessor.insert(algi210006T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210006T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_06, TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi210006T.ordIdTxt.getValue()) });
            }
            return poSeqNumber;

        }
        /**
         * Create PO Interface Table(NLGI2100_05TMsg) List.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param dtlT WMS_INBD_SO_DTLTMsg
         * @param poSeqNumber PO_SEQ_NUMBER
         * @return PO_SEQ_NUMBER
         */
        private BigDecimal createPoIf05(WMS_INBD_SO_HDRTMsg hdrT, WMS_INBD_SO_DTLTMsg dtlT, BigDecimal poSeqNumber) {

            // QC#17981 Mod.
            /*
            if (!WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(hdrT.wmsOrdTpCd.getValue()) || !WMS_ORD_TP.OUTBOUND_ITEM_CHANGE.equals(hdrT.wmsOrdTpCd.getValue())) {
                return poSeqNumber;
            }
            */
            if (notCrtNlgi210005WmsOrdCd != null && !Arrays.asList(notCrtNlgi210005WmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) { 
                return poSeqNumber;
            }

            NLGI2100_05TMsg algi210005T = new NLGI2100_05TMsg();
            ZYPEZDItemValueSetter.setValue(algi210005T.interfaceId, wmsSoDnldPoIntfcId);
            ZYPEZDItemValueSetter.setValue(algi210005T.transactionId, poTrxId);
            ZYPEZDItemValueSetter.setValue(algi210005T.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210005T.unitId, poUnitId);
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210005T.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi210005T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_05);
            ZYPEZDItemValueSetter.setValue(algi210005T.ordIdTxt, dtlT.wmsSoId);
            ZYPEZDItemValueSetter.setValue(algi210005T.ordTpTxt, getWmsOrdTpXpndCdFromCache(INBD_OTBD.INBOUND, hdrT.wmsOrdTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(algi210005T.ordLineTxt, dtlT.wmsLineNum.getValue().multiply(VAL_ORD_LINE_TXT_MULT).toString());
            ZYPEZDItemValueSetter.setValue(algi210005T.ordCmntSqTxt, VAL_1);
            
            // QC#17981 Mod.
            /*
            if (WMS_ORD_TP.INBOUND_STOCK_STATUS_CHANGE.equals(hdrT.wmsOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(algi210005T.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX
                        + (NLXC014001.nullToEmpty(dtlT.s80StkStsCd.getValue()) + VAL_ORD_CMNT_TXT_CONJ + NLXC014001.nullToEmpty(dtlT.s80StkStsCdToCd.getValue()) + VAL_ORD_CMNT_TXT_SFX));
            } else if (WMS_ORD_TP.OUTBOUND_ITEM_CHANGE_FOR_REMAN.equals(hdrT.wmsOrdTpCd.getValue())) {
                
                ZYPEZDItemValueSetter.setValue(algi210005T.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX
                        + (NLXC014001.nullToEmpty(dtlT.s80StkStsCd.getValue()) + VAL_ORD_CMNT_TXT_CONJ + NLXC014001.nullToEmpty(dtlT.s80StkStsCdToCd.getValue()) + VAL_ORD_CMNT_TXT_SFX));
            }
            */
            if (crtSetOrdCmntTxtWmsOrdCd != null && Arrays.asList(crtSetOrdCmntTxtWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) { 
                ZYPEZDItemValueSetter.setValue(algi210005T.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX
                        + (NLXC014001.nullToEmpty(dtlT.s80StkStsCd.getValue()) + VAL_ORD_CMNT_TXT_CONJ + NLXC014001.nullToEmpty(dtlT.s80StkStsCdToCd.getValue()) + VAL_ORD_CMNT_TXT_SFX));
            }

            EZDTBLAccessor.insert(algi210005T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210005T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_05, TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi210005T.ordIdTxt.getValue()) });
            }
            return poSeqNumber;
        }

        /**
         * Register WMS_INBD_SO_HDR.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return true / success, false / error
         */
        private boolean registerHdr(WMS_INBD_SO_HDRTMsg hdrT) {

            EZDTBLAccessor.insert(hdrT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_HDR, TBL_SHPG_ORD
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                        , NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.wmsSoId.getValue()) });
            }
            return true;
        }

        /**
         * Update SHPG_ORD.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param procStsCd Proc Status Code
         * @return true / success, false / error
         */
        private boolean updateShpgOrd(WMS_INBD_SO_HDRTMsg hdrT, String wmsDropFlg) {

            SHPG_ORDTMsg soT = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(soT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soT.soNum, hdrT.wmsSoId.getValue());
            soT = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdate(soT);

            if (soT == null) {
                throw new S21AbendException(NLGM0046E, new String[] {TBL_SHPG_ORD
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_SO_NUM)
                        , NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.wmsSoId.getValue()) });
            }

            soT.wmsDropFlg.setValue(wmsDropFlg);
            EZDTBLAccessor.update(soT);
            return true;
        }

        /**
         * Get WMS_SHIP_VIA_RTE_MAP.
         * @param whCd Warehouse Code
         * @param wmsShipViaTpCd WMS Ship Via Type Code
         * @return WMS_SHIP_VIA_RTE_MAPTMsg
         */
        private WMS_SHIP_VIA_RTE_MAPTMsg getWmsShipViaRteMap(String whCd, String wmsShipViaTpCd) {

            // Key = WH_CD + WMS_SHIP_VIA_TP_CD.
            WMS_SHIP_VIA_RTE_MAPTMsg tMsg = wmsShipViaRteMap.get(whCd + wmsShipViaTpCd);

            if (tMsg == null) {
                tMsg = new WMS_SHIP_VIA_RTE_MAPTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(tMsg.wmsShipViaTpCd, wmsShipViaTpCd);

                tMsg = (WMS_SHIP_VIA_RTE_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    wmsShipViaRteMap.put(tMsg.whCd.getValue() + tMsg.wmsShipViaTpCd.getValue(), tMsg);
                }
            }
            return tMsg;
        }

        /**
         * Get WMS_CTRY_CTAC.
         * @param wmsOrdSrcCd WMS Order Source Code
         * @param whCd Warehouse Code
         * @param ctryCd Country Code
         * @return WMS_CTRY_CTACTMsg
         */
        private WMS_CTRY_CTACTMsg getWmsCtryCtac(String wmsOrdSrcCd, String whCd, String ctryCd) {

            // Key = WMS_ORD_SRC_CD + WH_CD + CTRY_CD
            WMS_CTRY_CTACTMsg tMsg = wmsCtryCtacMap.get(wmsOrdSrcCd + whCd + ctryCd);

            if (tMsg == null) {
                tMsg = new WMS_CTRY_CTACTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.wmsOrdSrcCd, wmsOrdSrcCd);
                ZYPEZDItemValueSetter.setValue(tMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, ctryCd);

                tMsg = (WMS_CTRY_CTACTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    wmsCtryCtacMap.put(tMsg.wmsOrdSrcCd.getValue() + tMsg.whCd.getValue() + tMsg.ctryCd.getValue(), tMsg);
                }
            }
            return tMsg;
        }

        /**
         * Get WMS_FRT_OUT_CD_MAP.
         * @param whCd Warehouse Code
         * @param frtOutCd Freight Out Code
         * @return WMS_FRT_OUT_CD_MAPTMsg
         */
        private WMS_FRT_OUT_CD_MAPTMsg getWmsFrtOutCdMap(String whCd, String frtOutCd) {

            // Key = WH_CD + FRT_OUT_CD.
            WMS_FRT_OUT_CD_MAPTMsg tMsg = wmsFrtOutCdMap.get(whCd + frtOutCd);

            if (tMsg == null) {
                tMsg = new WMS_FRT_OUT_CD_MAPTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.whCd, whCd);
                ZYPEZDItemValueSetter.setValue(tMsg.frtOutCd, frtOutCd);

                tMsg = (WMS_FRT_OUT_CD_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    wmsFrtOutCdMap.put(tMsg.whCd.getValue() + tMsg.frtOutCd.getValue(), tMsg);
                }
            }
            return tMsg;
        }

//        /**
//         * Get ORD_TP_TXT string.
//         * @param hdrT WMS_INBD_SO_HDRTMsg
//         * @return ORD_TP_TXT
//         */
//        private String getOrdTpTxt(WMS_INBD_SO_HDRTMsg hdrT) {
//            if (hdrT.wmsOrdTpCd.getValue().equals(WMS_ORD_TP.OUTBOUND_CONFIGURATION_CHANGE)) {
//                return VAL_ORD_TP_TXT_OUTBOUND_CONFIGURATION_CHANGE;               
//            } else if (hdrT.wmsOrdTpCd.getValue().equals(WMS_ORD_TP.OUTBOUND_DIPOSAL)) {
//                return VAL_ORD_TP_TXT_OUTBOUND_DIPOSAL;
//            } else if (ZYPConstant.FLG_ON_Y.equals(hdrT.indConfigFlg.getValue())) {
//                return VAL_ORD_TP_TXT_PFX_SO + hdrT.wmsOrdTpCd.getValue() + VAL_ORD_TP_TXT_SFX_C;
//            } else if (WMS_ORD_SRC.ESTORE.equals(hdrT.wmsOrdSrcCd.getValue())) {
//                return VAL_ORD_TP_TXT_PFX_SO + hdrT.wmsOrdTpCd.getValue() + VAL_ORD_TP_TXT_SFX_W;
//            } else {
//                return VAL_ORD_TP_TXT_PFX_SO + hdrT.wmsOrdTpCd.getValue();
//            }
//        }

        /**
         * Date format.
         * @param date target string
         * @param inFormat input format
         * @param outFormat output format
         * @return formatted string
         */
        private String dateFormat(String date, String inFormat, String outFormat) {

            if (!ZYPCommonFunc.hasValue(date)) {
                return null;
            }
            return ZYPDateUtil.DateFormatter(date, inFormat, outFormat);
        }

        /**
         * Decimal format.
         * @param decimal target
         * @param outFormat output format
         * @return formatted string
         */
        private String decimalFormat(BigDecimal decimal, String outFormat) {

            if (!ZYPCommonFunc.hasValue(decimal)) {
                return null;
            }
            DecimalFormat df = new DecimalFormat(outFormat);
            return df.format(decimal);
        }

        /**
         * Get string value.
         * @param decimal target
         * @return string value
         */
        private String getString(BigDecimal decimal) {
            if (!ZYPCommonFunc.hasValue(decimal)) {
                return null;
            }
            return String.valueOf(decimal);
        }

        // START 2017/12/20 S.Katsuma [QC#22592,ADD]
        private boolean updateShpgOrdDtl(WMS_INBD_SO_DTLTMsg wmsInbdSoDtlTMsg) {
            SHPG_ORD_DTLTMsg soDtlT = new SHPG_ORD_DTLTMsg();
            String soSlpNum = ZYPCommonFunc.leftPad(String.valueOf(wmsInbdSoDtlTMsg.wmsLineNum.getValue()), 3,"0");
            ZYPEZDItemValueSetter.setValue(soDtlT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soDtlT.soNum, wmsInbdSoDtlTMsg.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(soDtlT.soSlpNum, soSlpNum);
            soDtlT = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(soDtlT);

            if (soDtlT != null) {
                // QC#29011
                if (DS_SO_LINE_STS.ALLOCATED.equals(soDtlT.dsSoLineStsCd.getValue())) {
                    soDtlT.dsSoLineStsCd.setValue(DS_SO_LINE_STS.AWAITING_PICK_CONFIRMATION);
                }
                EZDTBLAccessor.update(soDtlT);
            }

            return true;
        }
        // END 2017/12/20 S.Katsuma [QC#22592,ADD]
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
     * getCpoDtl
     * @param String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum
     * @return CPO_DTLTMsg
     */
    private CPO_DTLTMsg getCpoDtl(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg inMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        CPO_DTLTMsg outMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getRtlWh
     * @param String glblCmpyCd, String rtlWhCd
     * @return MDSETMsg
     */
    private RTL_WHTMsg getRtlWh(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * isWmsInbdSoUpdate
     * @param  String glblCmpyCd, String soNum
     * @return Map<String, Object>
     */
    private boolean isWmsInbdSoUpdate(String glblCmpyCd, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);

        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countWmsInbdSo", ssmParam);
        
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getSoSerList
     * @param String glblCmpyCd, String soNum, String soSlpNum
     * @return List<String>
     */
    private List<String> getSoSerList(String glblCmpyCd, String soNum, String soSlpNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        ssmParam.put("soSlpNum", soSlpNum);
        // QC#19967 Start
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // QC#19967 End
        List<String> serNumList = (List<String>) ssmBatchClient.queryObjectList("getSoSerList", ssmParam);
        
        return serNumList;
    }
    /**
     * isExistsSerialSpec
     * @param glblCmpyCd
     * @param soNum
     * @param soSlpNum
     * @return
     */
    private boolean isExistsSerialSpec(String glblCmpyCd, String soNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        // QC#19967 Start
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // QC#19967 End
        BigDecimal ret = (BigDecimal) ssmBatchClient.queryObject("isExistsSerialSpec", ssmParam);
        
        if (ret.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

   /**
    * getShpippingBackOrderDetailList
    * @param String glblCmpyCd, String rwsNum
    * @return List<SHPG_BO_DTLTMsg>
    */
   private List<Map<String, Object>> getShpippingBackOrderDetailList(String glblCmpyCd, String soNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("soNum", soNum);
       List<Map<String, Object>> boList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShpippingBackOrderDetailList", ssmParam);
       
       return boList;
   }

    /**
     * isWmsInbdSoUpdate
     * @param  String glblCmpyCd, String soNum
     * @return Map<String, Object>
     */
    private boolean isCreatePoIf04OrderType(String wmsOrdTpCd) {

        // QC#17981 Mod.
        /*
        if (wmsOrdTpCd.equals(WMS_ORD_TP.INBOUND_ITEM_CHANGE)) {
            return true;
        } else if (wmsOrdTpCd.equals(WMS_ORD_TP.OUTBOUND_PACKAGE_CODE_CHANGE)) {
            return true;
        } else if (wmsOrdTpCd.equals(WMS_ORD_TP.OUTBOUND_ITEM_CHANGE_FOR_REMAN)) {
            return true;
        }
        */
        if (isCreatePoIf04WmsOrdCd != null && Arrays.asList(isCreatePoIf04WmsOrdCd).contains(wmsOrdTpCd)) { 
            return true;
        }
        return false;
    }

    /**
     * getPackageCode
     * @param  String glblCmpyCd, String rtlWhCd, String rtlSwhCd
     * @return Map<String, Object>
     */
    private String getPackageCode(String rtlWhCd, String rtlSwhCd) {

        String flg = ZYPCodeDataUtil.getVarCharConstValue("WMS_PACK_CD_SET_OWNER_CD_FLG", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(flg) && flg.equals(ZYPConstant.FLG_ON_Y)) {
            RTL_WHTMsg rtlWhTMsg = getRtlWh(glblCmpyCd, rtlWhCd);
            if (rtlWhTMsg == null) {
                return null;
            }
            return rtlWhTMsg.invtyOwnrCd.getValue().concat(rtlSwhCd);
        } else {
            return rtlWhCd.concat(rtlSwhCd);
        }
    }

    /**
     * Register WMS_INBD_SO_HDR.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     * @return true / success, false / error
     */
    private boolean updateWmsIntfcCtrl(String whCd) {

        WMS_INTFC_CTRLTMsg inMsg = new WMS_INTFC_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(inMsg.wmsIntfcTaskNm, WMS_INTFC_TASK_NM_SO);

        WMS_INTFC_CTRLTMsg updMsg = (WMS_INTFC_CTRLTMsg) EZDTBLAccessor.findByKey(inMsg);
        String sysDate = ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS);
        if (updMsg == null) {
            ZYPEZDItemValueSetter.setValue(inMsg.taskDtTmTs, sysDate);
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_HDR});
            }
        } else {
            ZYPEZDItemValueSetter.setValue(updMsg.taskDtTmTs, sysDate);
            EZDTBLAccessor.update(updMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_SO_HDR});
            }
        }
        return true;
    }

    /**
     * Create cache for WMS_ORD_TP_XPND_CD
     */
    private void createCacheForWmsOrdTpXpndCd() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);

            stmt = ssmLLClient.createPreparedStatement("getWmsOrdTpXpndCdForCache", queryParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                this.cacheMapWmsOrdTpXpndCd.put(rs.getString(WMS_ORD_TP_CACHE_KEY), rs.getString(COL_WMS_ORD_TP_XPND_CD));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Get WMS_ORD_TP_XPND_CD from cache
     * @param inbdOtbdCd String
     * @param wmsOrdTpCd String
     * @return String WMS_ORD_TP_XPND_CD
     */
    private String getWmsOrdTpXpndCdFromCache(String inbdOtbdCd, String wmsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(inbdOtbdCd) || !ZYPCommonFunc.hasValue(wmsOrdTpCd)) {
            return null;
        }
        String key = S21StringUtil.concatStrings(inbdOtbdCd, wmsOrdTpCd);
        return this.cacheMapWmsOrdTpXpndCd.get(key);
    }

    /**
     * Get ShipToRtlSwhCd
     * @param glblCmpyCd String
     * @param wmsSoId String
     * @param wmsOrdLineNum String
     * @return String shipToRtlSwhCd
     */
    private String getShipToRtlSwhCd(String glblCmpyCd, String wmsSoId, String wmsOrdLineNum) {
        String shipToRtlSwhCd = "";
        SHPG_ORD_DTLTMsg tMsg = new SHPG_ORD_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("soNum01", wmsSoId);
        SHPG_ORD_DTLTMsgArray tMsgArray = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(tMsgArray.no(i).soSlpNum) && tMsgArray.no(i).soSlpNum.getValue().equals(wmsOrdLineNum)) {
                shipToRtlSwhCd = tMsgArray.no(i).shipToRtlSwhCd.getValue();
                break;
            }
        }

        return shipToRtlSwhCd;
    }

    /**
     * getWmsOrdTpCdFromVarCharConst
     */
    private void getWmsOrdTpCdFromVarCharConst() {

        String varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_CRAT_WTANDSHIPVIA_NOT, glblCmpyCd);
        if (varcharConstVal != null) {
            notCrtWtandShipViaWmsOrdCd = varcharConstVal.split(",");
        }

        //QC# 20124
        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_CREATE_RTRN_TO_NOT, glblCmpyCd);
        if (varcharConstVal != null) {
            notCrtRtrnToWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_REGIST_SO_PO, glblCmpyCd);
        if (varcharConstVal != null) {
            regSoPoWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_REGIST_PO, glblCmpyCd);
        if (varcharConstVal != null) {
            regPoWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_CASEANDPALLET_NOT, glblCmpyCd);
        if (varcharConstVal != null) {
            notCaseandPalletWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_SET_NLGI1100_OUT_KIT, glblCmpyCd);
        if (varcharConstVal != null) {
            setNlgi1100OtbdKittingWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_SET_NLGI1100_OUT_IC, glblCmpyCd);
        if (varcharConstVal != null) {
            setNlgi1100OtbdItemChangeWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_SET_NLGI1100_IN_IC, glblCmpyCd);
        if (varcharConstVal != null) {
            setNlgi1100InbdItemChangeWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_SET_NLGI1100_OUT_EXP, glblCmpyCd);
        if (varcharConstVal != null) {
            setNlgi1100OtbdExportWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_SET_NLGI1100_IN_ICNOT, glblCmpyCd);
        if (varcharConstVal != null) {
            notSetNlgi1100InbdItemChangeWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_CREATE_NLGI210005_NOT, glblCmpyCd);
        if (varcharConstVal != null) {
            notCrtNlgi210005WmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_CRAT_SET_ORDCMNTTXT, glblCmpyCd);
        if (varcharConstVal != null) {
            crtSetOrdCmntTxtWmsOrdCd = varcharConstVal.split(",");
        }

        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_IS_CREATE_POIF04, glblCmpyCd);
        if (varcharConstVal != null) {
            isCreatePoIf04WmsOrdCd = varcharConstVal.split(",");
        }

        // START 2018/05/11 S.Katsuma [QC#24714,ADD]
        varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB0010_SET_NLGI1100_OUT_RMN, glblCmpyCd);
        if (varcharConstVal != null) {
            setNlgi1100OtbdRemanWmsOrdCd = varcharConstVal.split(",");
        }
        // END 2018/05/11 S.Katsuma [QC#24714,ADD]
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

    // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
    private Map<String, Object> getSoIf1AddValue(ResultSet rs) throws SQLException {
        Map<String, Object> rtnMap = new HashMap<String, Object>();

        String svcLbGrpReqFlg = rs.getString(SVC_LB_GRP_REQ_FLG);
        if (ZYPCommonFunc.hasValue(svcLbGrpReqFlg) && !ZYPConstant.FLG_ON_Y.equals(svcLbGrpReqFlg)) {
            return null;
        }
        BigDecimal dsCpoConfigPk = rs.getBigDecimal(DS_CPO_CONFIG_PK);
        if (!ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);
        Map<String, Object> soIf1AddValMap = (Map<String, Object>) ssmBatchClient.queryObject("getSoIf1AddValue", ssmParam);
        if (soIf1AddValMap != null && !soIf1AddValMap.isEmpty()) {
            rtnMap.put(SVC_LB_GRP_CD, soIf1AddValMap.get(SVC_LB_GRP_CD));
            rtnMap.put(T_MDL_NM, soIf1AddValMap.get(T_MDL_NM));
        } else {
            return null;
        }

        return rtnMap;
    }
    // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]

    /**
     * getMtrMachFlg
     * QC#15629 Add Method.
     * @param gCompCd String
     * @param serNum String
     * @return String
     */
    private String getMtrMachFlg(String gCompCd, String serNum) {

        String retVal = ZYPConstant.FLG_OFF_N;

        if (!ZYPCommonFunc.hasValue(serNum)) {
            return retVal;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseItemTpMachine", MDSE_ITEM_TP.MACHINE);

        retVal = (String) ssmBatchClient.queryObject("getMtrMachFlg", ssmParam);

        if (!ZYPCommonFunc.hasValue(retVal)) {
            return ZYPConstant.FLG_OFF_N;
        }

        return retVal;
    }

    /**
     * Create getCutString Mod QC#52399.
     * @param String[] cutString
     * @param int maxLength
     */
    private String[] getCutString(String[] val, int maxLength) {

        boolean length_chk = false;
        String[] retval = new String[val.length];

        for (int i = 0; i < val.length; i++) {
            // QC#51804
            // if (val[i].length() > maxLength){
            if (val[i].getBytes().length > maxLength) {

                length_chk = true;
                break;
            }
        }

        if (length_chk) {

            String st = "";

            for (int i = 0; i < val.length; i++) {

                if (!("").equals(val[i])) {

                    st = st + val[i] + " ";
                }
            }

            StringBuffer sb = new StringBuffer();
            sb.append(st.trim());

            StringBuffer bb;
            String tmp = "";
            byte[] retbyte;
            int cnt = 0;
            int strlen = 0;

            for (int i = 0; i < retval.length; i++) {

                if (sb.toString().matches("\\p{ASCII}*")) {

                    if (sb.length() > maxLength) {

                        retval[i] = sb.substring(0, maxLength);
                        sb.delete(0, maxLength);

                    } else {

                        retval[i] = sb.toString();
                        sb.delete(0, sb.length());
                    }

                } else {

                    if (sb.toString().getBytes().length > maxLength) {

                        cnt = 0;
                        strlen = 0;
                        bb = new StringBuffer();

                        for (int j = 0; j < sb.toString().length(); j++) {

                            tmp = sb.substring(j, j + 1);
                            retbyte = tmp.getBytes();

                            if (cnt + retbyte.length > maxLength) {

                                retval[i] = bb.toString();
                                break;

                            } else {

                                bb.append(tmp);
                                cnt += retbyte.length;
                            }

                            strlen++;
                        }

                        retval[i] = bb.toString();
                        sb.delete(0, strlen);

                    } else {

                        retval[i] = sb.toString();
                        sb.delete(0, sb.length());
                    }
                }
            }

        } else {

            retval = val;
        }

        return retval;
    }

    // START 2018/05/11 S.Katsuma [QC#24714,ADD]
    /**
     * getCarrCdTxtForReman
     * @param String glblCmpyCd
     * @param String soNum
     * @return List<String>
     */
    private String getCarrCdTxtForReman(String glblCmpyCd, String soNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);

        String ret = (String) ssmBatchClient.queryObject("getCarrCdTxtForReman", ssmParam);
        if (!ZYPCommonFunc.hasValue(ret)) {
            ret = "";
        }

        return ret;
    }
    // END 2018/05/11 S.Katsuma [QC#24714,ADD]

    // START 2019/08/07 T.Ogura [QC#52399,ADD]
    /**
     * adjustString
     * @param val
     * @param len
     * @return
     */
    private String adjustString(String val, int len) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return val;
        }

        if (val.length() > len) {
            val = val.substring(0, len);
        }

        return val;
    }
    // END   2019/08/07 T.Ogura [QC#52399,ADD]

    
}
