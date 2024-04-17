package com.canon.cusa.s21.batch.NLG.NLGB021001;

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
import business.db.DS_ORD_CATGTMsg;
import business.db.INVTY_ORD_DTLTMsg;
import business.db.NLGI1200_01TMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_MSGTMsg;
import business.db.SHPG_ORD_MSGTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.TECH_MSTRTMsg;
import business.db.TOCTMsg;
import business.db.WMS_INBD_SO_BILL_TOTMsg;
import business.db.WMS_INBD_SO_CHRG_TOTMsg;
import business.db.WMS_INBD_SO_DTLTMsg;
import business.db.WMS_INBD_SO_HDRTMsg;
import business.db.WMS_INBD_SO_SHIP_TOTMsg;
import business.db.WMS_INBD_SO_TEXTTMsg;
import business.db.WMS_INTFC_CTRLTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
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
 * Batch Program Class for WMS SO Download for DBS.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   CITS            T.Wada          Create          
 * 08/24/2016   CITS            T.Wada          Update          QC13784
 * 01/30/2017   CITS            R.Shimamoto     Update          QC17315
 * 02/02/2017   CITS            K.Ogino         Update          QC#17396
 * 02/05/2017   CITS            K.Ogino         Update          QC#17450
 * 03/23/2017   CITS            K.Ogino         Update          QC#17998
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 04/21/2017   CITS            K.Ogino         Update          QC#18366
 * 05/29/2017   CITS            T.Kikuhara      Update          RS#3155
 * 06/23/2017   CITS            T.Kikuhara      Update          QC#19529
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 * 08/03/2017   CITS            K.Ogino         Update          QC#20423
 * 10/24/2017   CITS            T.Tokutomi      Update          QC#21657-1
 * 11/06/2017   CITS            T.Wada          Update          QC#22214
 * 12/18/2017   CITS            T.Wada          Update          QC#21657-2
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 2018/05/09   CITS            K.Ogino         Update          QC#26027
 * 2018/06/01   CITS            K.Ogino         Update          QC#26429
 * 2018/06/08   CITS            Y.Iwasaki       Update          QC#26037
 * 2018/07/13   CITS            S.Katsuma       Update          QC#26710
 * 2019/03/08   CITS            K.Ogino         Update          QC#29011
 * 2019/04/10   CITS            K.Ogino         Update          QC#31042
 * 05/20/2019   CITS            K.Ogino         Update          QC#50072
 * 12/20/2019   Fujitsu         R.Nakamura      Update          QC#55070
 * 02/19/2020   CITS            M.Furugoori     Update          QC#55641
 *</pre>
 */
public class NLGB021001 extends S21BatchMain {

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

    /** NLGB0210_SET_CASEANDPALLET_NOT */
    private String[] notSetCaseAndPalletWmsOrdTp = null;

    /** NLGB0210 Create Ship To Not Setting WtAndShipVia */
    private String[] notCrtWtandShipViaWmsOrdCd = null;

    /** Add QC#26429 tplSoLineId */
    private String tplSoLineId = "";

    @Override
    protected void initRoutine() {

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        totalCommitCount = 0;
        totalErrCount = 0;
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();
        profile = S21UserProfileServiceFactory.getInstance().getService();
    }

    @Override
    protected void mainRoutine() {

        glblCmpyCd = profile.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGB021001Constant.NLGM0049E, new String[] {NLGB021001Constant.PRAM_NM_GLBL_CMPY_CD });
        }

        String whGpCd = S21BatchUtil.getUserVariable1();

        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGB021001Constant.NLGM0049E, new String[] {NLGB021001Constant.PRAM_NM_WH_GRP_CD });
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
                queryParam.put("shipToCustCd", NLGB021001Constant.VAL_SHIP_TO_CUST_CD);
                queryParam.put("outbound", NLGB021001Constant.VAL_OUTBOUND);
                queryParam.put("lgSoCratTs", NLGB021001Constant.LG_SO_CRAT_TS);
                queryParam.put("val1", NLGB021001Constant.VAL_1);
                queryParam.put("val2", NLGB021001Constant.VAL_2);
                queryParam.put("valTs", NLGB021001Constant.VAL_000000);
                queryParam.put("maxTotShipAmt", NLGB021001Constant.VAL_MAX_TOT_SHIP_PRC_AMT_NUM);
                queryParam.put("lgCustIssPoNum", NLGB021001Constant.MAX_LENGTH_CUST_ISS_PO_NUM);
                queryParam.put("lgOtbdSrcOrdTpTxt", NLGB021001Constant.MAX_LENGTH_OTBD_SRC_ORD_TP_TXT);
                queryParam.put("lgPsnNm", NLGB021001Constant.VAL_PSN_NM_SIZE);
                queryParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
                queryParam.put("minDt", NLGB021001Constant.VAL_MIN_DATE);
                queryParam.put("maxDt", NLGB021001Constant.VAL_MAX_DATE);
                ssmBatchClient.queryObject("getSo", queryParam, new SoToWms());
            } else {
                outputErr(NLGB021001Constant.NLGM0047E, new String[] {whGpCd });
            }
        } finally {
            if (!errMsgList.isEmpty()) {
                NLXMailSend mailSender = new NLXMailSend(glblCmpyCd);
                mailSender.send(NLGB021001Constant.BUSINESS_ID, errMsgList);
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
        new NLGB021001().executeBatch(NLGB021001.class.getSimpleName());
    }

    /**
     * SO Download to WMSommit
     */
    private class SoToWms extends S21SsmIntegerResultSetHandlerSupport {

        /** Return Commit Count */
        private int rtrnCommitCount = 0;

        /** S21TransactionTableAccessor */
        private S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();

        /** Transaction Id for SO IF Table */
        private BigDecimal soTrxId = null;

        /** Transaction Id for PO IF Table */
        // private BigDecimal poTrxId = null;
        /** Unit Id for SO IF Table */
        private BigDecimal soUnitId = BigDecimal.ONE;

        /** WMS_SO_DNLD_SO_INTF_ID */
        private String wmsSoDnldSoIntfcId = null;

        /** WMS_SO_DNLD_PO_INTF_ID */
        // private String wmsSoDnldPoIntfcId = null;
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

            String varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB021001Constant.NLGB0210_PSHIPVIA_TRUE, glblCmpyCd);
            if (varcharConstVal != null) {
                notCrtWtandShipViaWmsOrdCd = varcharConstVal.split(",");
            }

            varcharConstVal = ZYPCodeDataUtil.getVarCharConstValue(NLGB021001Constant.NLGB0210_SET_CASEANDPALLET_NOT, glblCmpyCd);
            if (varcharConstVal != null) {
                notSetCaseAndPalletWmsOrdTp = varcharConstVal.split(",");
            }

            WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT = null;
            List<WMS_INBD_SO_DTLTMsg> wmsInbdSoDtlTList = new ArrayList<WMS_INBD_SO_DTLTMsg>();

            while (rs.next()) {
                List<WMS_INBD_SO_TEXTTMsg> wmsInbdSoTextTList = new ArrayList<WMS_INBD_SO_TEXTTMsg>();
                WMS_INBD_SO_SHIP_TOTMsg wmsInbdSoShipToT = null;
                WMS_INBD_SO_CHRG_TOTMsg wmsInbdSoChrgToT = null;
                WMS_INBD_SO_BILL_TOTMsg wmsInbdSoBillToT = null;
                boolean isRegistSoTrxId = false;
                boolean isRegistInbdSoData = false;
                String wmsUniqId = rs.getString(NLGB021001Constant.COL_WMS_UNIQ_ID);
                wmsSoDnldSoIntfcId = rs.getString(NLGB021001Constant.COL_WMS_SO_DNLD_SO_INTFC_ID);
                wmsInbdSoDtlTList.clear();
                soTrxId = null;
                soUnitId = BigDecimal.ONE;

                wmsInbdSoHdrT = createHdr(rs);
                if (wmsInbdSoHdrT == null) {
                    isRegistInbdSoData = false;
                } else {

                    isRegistInbdSoData = createDtl(wmsInbdSoDtlTList, wmsInbdSoHdrT, wmsUniqId, rs.getString(NLGB021001Constant.COL_SCE_ORD_TP_CD));

                    registerHdr(wmsInbdSoHdrT);

                    createText(wmsInbdSoHdrT, wmsInbdSoTextTList);

                    wmsInbdSoShipToT = createShipTo(wmsInbdSoHdrT);

                    wmsInbdSoChrgToT = createChrgTo(wmsInbdSoHdrT);

                    wmsInbdSoBillToT = createBillTo(wmsInbdSoHdrT);

                }

                if (!isRegistInbdSoData) {
                    rollback();
                    totalErrCount++;
                    continue;
                }

                // Register SO I/F Tables, PO I/F Tables.
                String wmsDropFlg = ZYPConstant.FLG_ON_Y;
                    // Register SO.
                    if (!registerSo(wmsInbdSoHdrT, wmsInbdSoDtlTList, wmsInbdSoShipToT, wmsInbdSoTextTList, wmsInbdSoChrgToT, wmsInbdSoBillToT, rs.getString(NLGB021001Constant.COL_SCE_ORD_TP_CD), rs.getString(NLGB021001Constant.COL_VND_CD))) {
                        wmsDropFlg = ZYPConstant.FLG_OFF_N;
                        rollback();
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
                    }
                } else {
                    rollback();
                    totalErrCount++;
                }
            }
            return rtrnCommitCount;
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
                if (!ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.COL_S80_CMPY_CD))) {
                    outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_CMPY_CD_CONV, NLGB021001Constant.COL_GLBL_CMPY_CD, glblCmpyCd});
                    return null;
                }

                // QC#19634 Start
                if (ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.COL_SCE_ORD_TP_CD))) {
                    if(!ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.CONV_SCE_ORD_TP_CD))) {
                        outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_SCE_ORD_TP, NLGB021001Constant.COL_SCE_ORD_TP_CD, rs.getString(NLGB021001Constant.COL_SCE_ORD_TP_CD)});
                        return null;   
                    }
                }

                if (ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.COL_TRX_SRC_TP_CD))) {
                    if (!ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.CONV_TRX_SRC_TP_CD))) {
                        outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_ORD_SRC_CONV, NLGB021001Constant.COL_TRX_SRC_TP_CD, rs.getString(NLGB021001Constant.COL_TRX_SRC_TP_CD) });
                        return null;
                    }
                }

                if (ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.COL_SHPG_SVC_LVL_CD))) {
                    if (!ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.CONV_SHPG_SVC_LVL_CD))) {
                        outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_SHIP_VIA_CONV, NLGB021001Constant.COL_SHPG_SVC_LVL_CD, rs.getString(NLGB021001Constant.COL_SHPG_SVC_LVL_CD)});
                        return null;
                    }
                }

                if ((ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.COL_FRT_CHRG_TO_CD)) && ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.COL_FRT_CHRG_METH_CD)))) {
                    if((!ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.CONV_FRT_CHRG_TO_CD)) || !ZYPCommonFunc.hasValue(rs.getString(NLGB021001Constant.CONV_FRT_CHRG_METH_CD)))) {
                        outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_SHPG_TERM_CONV, NLGB021001Constant.COL_FRT_CHRG_TO_CD, rs.getString(NLGB021001Constant.COL_FRT_CHRG_TO_CD)});
                        return null;
                    }
                }
                // QC#19634 End

                BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
                // QC#26429
                tplSoLineId = ZYPNumbering.getUniqueID(NLGB021001Constant.TPL_SO_LINE_ID);

                ZYPEZDItemValueSetter.setValue(hdrT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(hdrT.whCd, rs.getString(NLGB021001Constant.COL_WH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSqNum, wmsSqNum);
                ZYPEZDItemValueSetter.setValue(hdrT.intfcTpId, NLGB021001Constant.VAL_INTFC_TP_ID_02);
                ZYPEZDItemValueSetter.setValue(hdrT.intfcRecTpId, NLGB021001Constant.VAL_INTFC_REC_TP_ID_HDR);
                ZYPEZDItemValueSetter.setValue(hdrT.wmsCmpyCd, rs.getString(NLGB021001Constant.COL_S80_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSoId, rs.getString(NLGB021001Constant.COL_SO_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdNum, rs.getString(NLGB021001Constant.COL_TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.altDocNum, rs.getString(NLGB021001Constant.COL_PICK_TKT_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.custOrdNum, rs.getString(NLGB021001Constant.COL_CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.chrgToCustCd, rs.getString(NLGB021001Constant.COL_SELL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.billToCustCd, rs.getString(NLGB021001Constant.COL_BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.shipToCustCd, rs.getString(NLGB021001Constant.COL_SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsPrtyCd, NLGB021001Constant.VAL_WMS_PRTY_CD);
                ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdTpCd, rs.getString(NLGB021001Constant.COL_S80_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsTrxCd, rs.getString(NLGB021001Constant.COL_S80_TRX_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsOrdSrcCd, rs.getString(NLGB021001Constant.COL_S80_ORD_SRC_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSoStsCd, NLGB021001Constant.VAL_WMS_SO_STS_CD);
                ZYPEZDItemValueSetter.setValue(hdrT.soShipViaCd, rs.getString(NLGB021001Constant.COL_EXPT_SHPG_METH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.shipViaDescTxt, rs.getString(NLGB021001Constant.COL_SHPG_METH_NM));
                ZYPEZDItemValueSetter.setValue(hdrT.cratDtTmTs, rs.getString(NLGB021001Constant.COL_SO_CRAT_TS));
                ZYPEZDItemValueSetter.setValue(hdrT.estShipDtTmTs, rs.getString(NLGB021001Constant.COL_PSD_DT));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsRqstDtTmTs, rs.getString(NLGB021001Constant.COL_RDD_DT));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsPrintDtTmTs, rs.getString(NLGB021001Constant.COL_DNLD_TM_TS));
                ZYPEZDItemValueSetter.setValue(hdrT.frtOutCd, rs.getString(NLGB021001Constant.COL_S80_SHPG_TERM_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.frtOutDescTxt, rs.getString(NLGB021001Constant.COL_S80_SHPG_TERM_NM));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsSoCarrCd, rs.getString(NLGB021001Constant.COL_WMS_SO_CARR_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.indOtmAddrSwthFlg, rs.getString(NLGB021001Constant.COL_DROP_SHIP_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indScc14Flg, rs.getString(NLGB021001Constant.COL_PRINT_SCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indUccFlg, rs.getString(NLGB021001Constant.COL_PRINT_UCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indMixedPltFlg, rs.getString(NLGB021001Constant.COL_MIX_PLT_ALLW_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indPltLbFlg, rs.getString(NLGB021001Constant.COL_PRINT_PLT_UCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.sccNumFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.indNonAsnFlg, rs.getString(NLGB021001Constant.COL_PRINT_NON_ASN_UCC_LB_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indAsnFlg, rs.getString(NLGB021001Constant.COL_ASN_REQ_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.indUccNumFlg, rs.getString(NLGB021001Constant.COL_UCC_NUM_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsLbNum, rs.getString(NLGB021001Constant.COL_EDI_TP_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.custStoreNum, rs.getString(NLGB021001Constant.COL_CUST_STORE_NUM));
                String custDcNum = rs.getString(NLGB021001Constant.COL_CUST_STORE_NUM);
                if (ZYPCommonFunc.hasValue(custDcNum) && custDcNum.length() > NLGB021001Constant.LG_CUST_DC_NUM) {
                    custDcNum = custDcNum.substring(0, NLGB021001Constant.LG_CUST_DC_NUM);
                }
                ZYPEZDItemValueSetter.setValue(hdrT.custDcNum, custDcNum);
                ZYPEZDItemValueSetter.setValue(hdrT.wmsCustDeptNum, rs.getString(NLGB021001Constant.COL_SO_DEPT_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.wmsConslFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.totShipPrcAmtNum, rs.getBigDecimal(NLGB021001Constant.COL_TOT_SHIP_AMT));
                // totWtAmtNum is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.rtrnLbCd, rs.getString(NLGB021001Constant.COL_RTRN_LB_CD));
                // tpVndCd is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.indConfigFlg, rs.getString(NLGB021001Constant.COL_SO_CONFIG_FLG));
                // asgShipViaCd is set after SO_DTL process.
                // asgPrtyCd is set after SO_DTL process.
                ZYPEZDItemValueSetter.setValue(hdrT.indSgnReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.billAcctNum, rs.getString(NLGB021001Constant.COL_CARR_ACCT_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.rtlWhCd, rs.getString(NLGB021001Constant.COL_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.schdDelyDt, rs.getString(NLGB021001Constant.COL_SCHD_DELY_DT));
                ZYPEZDItemValueSetter.setValue(hdrT.carrCd, rs.getString(NLGB021001Constant.COL_CARR_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.shpgSvcLvlCd, rs.getString(NLGB021001Constant.COL_SHPG_SVC_LVL_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.trxHdrNum, rs.getString(NLGB021001Constant.COL_TRX_HDR_NUM));
                ZYPEZDItemValueSetter.setValue(hdrT.usrCdIstlRefTxt, rs.getString(NLGB021001Constant.COL_SO_CONFIG_FLG));
                ZYPEZDItemValueSetter.setValue(hdrT.rtrnItemInclFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.svcConfigMstrPk, rs.getBigDecimal(NLGB021001Constant.COL_SVC_CONFIG_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(hdrT.asmReqFlg, ZYPConstant.FLG_OFF_N);
                if (!isWmsInbdSoUpdate(glblCmpyCd, hdrT.wmsSoId.getValue())) {
                    ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, NLGB021001Constant.STAGE_ACT_NEW);
                } else {
                    ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, NLGB021001Constant.STAGE_ACT_UPDATE);
                }
                ZYPEZDItemValueSetter.setValue(hdrT.stageRecStsCd, NLGB021001Constant.STAGE_REC_STS_NEW_UPDATE);
                ZYPEZDItemValueSetter.setValue(hdrT.carrSvcHldAtLocFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.rtePathCd, rs.getString(NLGB021001Constant.COL_WMS_RTE_PATH_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.rtrnTagReqFlg, ZYPConstant.FLG_OFF_N);
                String sceOrdTpCd = rs.getString(NLGB021001Constant.COL_SCE_ORD_TP_CD);
                if (sceOrdTpCd != null) {
                    if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                     || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {
                        ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdTpTxt, rs.getString(NLGB021001Constant.COL_OTBD_SRC_ORD_TP_TXT));
                    } else {
                        ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdTpTxt, rs.getString(NLGB021001Constant.COL_SCE_ORD_TP_NM));
                    }
                }
                ZYPEZDItemValueSetter.setValue(hdrT.prtToCustFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(hdrT.otbdSrcOrdNum, rs.getString(NLGB021001Constant.COL_SRC_ORD_NUM));

            } catch (SQLException e) {
                sqlExceptionHandler(e);
                return null;
            }
            return hdrT;
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
            ssmParam.put("val2", NLGB021001Constant.VAL_2);
            ssmParam.put("maxQty", NLGB021001Constant.VAL_MAX_QTY);
            List<Map<String, Object>> soDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoDtl", ssmParam);

            if (soDtlList != null) {
                for (Map<String, Object> soDtl : soDtlList) {

                    // QC#19634 Start
                    // check S80 code value
                    if (ZYPCommonFunc.hasValue((String) soDtl.get(NLGB021001Constant.COL_FROM_STK_STS_CD))) {
                        if (!ZYPCommonFunc.hasValue((String) soDtl.get(NLGB021001Constant.CONV_FROM_STK_STS_CD))) {
                            outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_STS_STK_CONV, NLGB021001Constant.COL_S80_STK_STS_CD_FROM, (String) soDtl.get(NLGB021001Constant.COL_FROM_STK_STS_CD)});
                            return false;
                        }
                    }

                    // check S80 code value
                    if (ZYPCommonFunc.hasValue((String) soDtl.get(NLGB021001Constant.COL_TO_STK_STS_CD))) {
                        if (!ZYPCommonFunc.hasValue((String) soDtl.get(NLGB021001Constant.CONV_TO_STK_STS_CD))) {
                            outputErr(NLGB021001Constant.NLAM1001E, new String[] {NLGB021001Constant.TBL_STS_STK_CONV, NLGB021001Constant.COL_S80_STK_STS_CD_TO, (String) soDtl.get(NLGB021001Constant.COL_TO_STK_STS_CD)});
                            return false;
                        }
                    }
                    // QC#19634 End

                    ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_SO_SLP_NUM));
                    ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, NLGB021001Constant.VAL_INTFC_TP_ID_02);
                    ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, NLGB021001Constant.VAL_INTFC_REC_TP_ID_DTL);
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsSoId, hdrT.wmsSoId.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) soDtl.get(NLGB021001Constant.COL_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) soDtl.get(NLGB021001Constant.COL_S80_STK_STS_CD_FROM));
                    ZYPEZDItemValueSetter.setValue(dtlT.custMdseCd, (String) soDtl.get(NLGB021001Constant.COL_CUST_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdQty, (BigDecimal) soDtl.get(NLGB021001Constant.COL_RQST_ORD_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdQtyNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_SHPG_BAL_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsShipQty, (BigDecimal) soDtl.get(NLGB021001Constant.COL_SHPG_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.unitPrcAmtNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(dtlT.unitDiscAmtNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_DISC_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(dtlT.unitDiscPrcAmtNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_DISC_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsTotAmtNum, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlT.indSerId, (String) soDtl.get(NLGB021001Constant.COL_SER_NUM_TAKE_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.indVoidAllwFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCdToCd, (String) soDtl.get(NLGB021001Constant.COL_S80_STK_STS_CD_TO));
                    ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetCd, (String) soDtl.get(NLGB021001Constant.COL_SET_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.mdseCdSetDescTxt, (String) soDtl.get(NLGB021001Constant.COL_SET_MDSE_NM));
                    ZYPEZDItemValueSetter.setValue(dtlT.shipSetQty, (BigDecimal) soDtl.get(NLGB021001Constant.COL_SET_SHPG_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.unitInsdQty, (BigDecimal) soDtl.get(NLGB021001Constant.COL_IN_EACH_QTY));
                    ZYPEZDItemValueSetter.setValue(dtlT.totWtAmtNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_TOT_SHPG_WT));
                    ZYPEZDItemValueSetter.setValue(dtlT.totVolAmtNum, (BigDecimal) soDtl.get(NLGB021001Constant.COL_TOT_SHPG_VOL));
                    if (notSetCaseAndPalletWmsOrdTp != null
                    && !Arrays.asList(notSetCaseAndPalletWmsOrdTp).contains(hdrT.wmsOrdTpCd.getValue())
                    && (NLGB021001Constant.VAL_WMS_UNIQ_ID_TECSYS.equals(wmsUniqId) || NLGB021001Constant.VAL_WMS_UNIQ_ID_MA.equals(wmsUniqId) || NLGB021001Constant.VAL_WMS_UNIQ_ID_MENLO.equals(wmsUniqId) || NLGB021001Constant.VAL_WMS_UNIQ_ID_TECSYS_2.equals(wmsUniqId))) { 
                        setCaseAndPallet(dtlT);
                    }
                    ZYPEZDItemValueSetter.setValue(dtlT.batCptrReqFlg, (String) soDtl.get(NLGB021001Constant.COL_BAT_NUM_TAKE_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.indConfigFlg, (String) soDtl.get(NLGB021001Constant.COL_CONFIG_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, (String) soDtl.get(NLGB021001Constant.COL_RTL_WH_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) soDtl.get(NLGB021001Constant.COL_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.svcConfigMstrPk, (BigDecimal) soDtl.get(NLGB021001Constant.COL_PICK_SVC_CONFIG_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdImpctTpCd, (String) soDtl.get(NLGB021001Constant.COL_BACK_ORD_IMPCT_TP_CD));
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsOrdUomCd, WMS_UOM.EACH);
                    ZYPEZDItemValueSetter.setValue(dtlT.poLineTxt, (String) soDtl.get(NLGB021001Constant.COL_TRX_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(dtlT.usrCdIstlRefTxt, (String) soDtl.get(NLGB021001Constant.COL_CONFIG_ITEM_FLG));
                    ZYPEZDItemValueSetter.setValue(dtlT.backOrdFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(dtlT.rmvConfigFlg, (String) soDtl.get(NLGB021001Constant.COL_RMV_CONFIG_FLG));

                    CPO_DTLTMsg cpoDtlTMsg = getCpoDtl(glblCmpyCd, (String) soDtl.get(NLGB021001Constant.COL_TRX_HDR_NUM), (String) soDtl.get(NLGB021001Constant.COL_TRX_LINE_NUM), (String) soDtl.get(NLGB021001Constant.COL_TRX_LINE_SUB_NUM));
                    if (cpoDtlTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, cpoDtlTMsg.ordQty);
                    } else {
                        ZYPEZDItemValueSetter.setValue(dtlT.origOrdQty, dtlT.wmsOrdQty);
                    }

                    EZDTBLAccessor.insert(dtlT);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlT.getReturnCode())) {
                        throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_DTL, NLGB021001Constant.TBL_SHPG_ORD_DTL
                                , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM)
                                , NLXCMsgHelper.toListedString(glblCmpyCd, dtlT.wmsSoId.getValue()) });
                    }
                    WMS_INBD_SO_DTLTMsg wmsInbdSoDtlT = (WMS_INBD_SO_DTLTMsg) dtlT.clone();
                    dtlTList.add(wmsInbdSoDtlT);
                }

                // additional so header set
                // QC#22214
                if (NLGB021001Constant.VAL_WMS_UNIQ_ID_DBS.equals(wmsUniqId)) {
                    setWtAndShipVia(hdrT, dtlTList, soDtlList, sceOrdTpCd);
                } else {
                    if (notCrtWtandShipViaWmsOrdCd != null && !Arrays.asList(notCrtWtandShipViaWmsOrdCd).contains(hdrT.wmsOrdTpCd.getValue())) { 
                        if (NLGB021001Constant.VAL_WMS_UNIQ_ID_TECSYS.equals(wmsUniqId) || NLGB021001Constant.VAL_WMS_UNIQ_ID_TECSYS_2.equals(wmsUniqId)) {
                            setHazmat(hdrT, dtlTList);
                        }
                        setWtAndShipVia(hdrT, dtlTList, soDtlList, sceOrdTpCd);
                    }
                }
                

            }
            return true;
        }

        /**
         * Create WMS_INBD_SO_TEXTTMsg.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @param textTList WMS_INBD_SO_TEXTTMsg List
         */
        private void createText(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_TEXTTMsg> textTList) {

            WMS_INBD_SO_TEXTTMsg textT = new WMS_INBD_SO_TEXTTMsg();

            ZYPEZDItemValueSetter.setValue(textT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(textT.whCd, hdrT.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(textT.wmsSqNum, hdrT.wmsSqNum.getValue());
            ZYPEZDItemValueSetter.setValue(textT.intfcTpId, NLGB021001Constant.VAL_INTFC_TP_ID_02);
            ZYPEZDItemValueSetter.setValue(textT.intfcRecTpId, NLGB021001Constant.VAL_INTFC_REC_TP_ID_TXT);
            ZYPEZDItemValueSetter.setValue(textT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(textT.wmsSoId, hdrT.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(textT.wmsPrintTpCd, NLGB021001Constant.VAL_PRINT_TP_CD_B);

            SHPG_ORD_MSGTMsg inMsg = new SHPG_ORD_MSGTMsg();
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("soNum01", hdrT.wmsSoId.getValue());
            SHPG_ORD_MSGTMsgArray soMsgArray = (SHPG_ORD_MSGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if (soMsgArray != null && soMsgArray.length() > 0) {
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
                    WMS_INBD_SO_TEXTTMsg wmsInbdSoTxtT = (WMS_INBD_SO_TEXTTMsg) textT.clone();
                    textTList.add(wmsInbdSoTxtT);
                }
                EZDTBLAccessor.insert(textT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(textT.getReturnCode())) {
                    throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_TEXT, NLGB021001Constant.TBL_SHPG_ORD_MSG
                            , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, textT.wmsSoId.getValue()) });
                }
            }
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
            ZYPEZDItemValueSetter.setValue(shipToT.intfcTpId, NLGB021001Constant.VAL_INTFC_TP_ID_02);
            ZYPEZDItemValueSetter.setValue(shipToT.intfcRecTpId, NLGB021001Constant.VAL_INTFC_REC_TP_ID_SHIP);
            ZYPEZDItemValueSetter.setValue(shipToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shipToT.wmsSoId, hdrT.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(shipToT.wmsCustCd, hdrT.shipToCustCd.getValue());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("soNum", hdrT.wmsSoId.getValue());
            ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SHIP_TO);
            ssmParam.put("lgLineColNm", NLGB021001Constant.MAX_LENGTH_SO_CUST_LINE_LOC_NM);
            ssmParam.put("lgCtacTelNum", NLGB021001Constant.MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);
            Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);
            if (soCustDtl != null) {

                ZYPEZDItemValueSetter.setValue(shipToT.wmsShipToNm_01, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_LOC_NM_01));
                ZYPEZDItemValueSetter.setValue(shipToT.wmsShipToNm_02, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_LOC_NM_02));
                ZYPEZDItemValueSetter.setValue(shipToT.firstLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_01));
                ZYPEZDItemValueSetter.setValue(shipToT.scdLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_02));
                ZYPEZDItemValueSetter.setValue(shipToT.thirdLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_03));
                ZYPEZDItemValueSetter.setValue(shipToT.frthLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_04));
                ZYPEZDItemValueSetter.setValue(shipToT.ctyAddr, (String) soCustDtl.get(NLGB021001Constant.COL_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(shipToT.stCd, (String) soCustDtl.get(NLGB021001Constant.COL_ST_CD));
                ZYPEZDItemValueSetter.setValue(shipToT.postCd, (String) soCustDtl.get(NLGB021001Constant.COL_POST_CD));
                ZYPEZDItemValueSetter.setValue(shipToT.ctryCd, (String) soCustDtl.get(NLGB021001Constant.COL_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(shipToT.wmsShipToCtacNm, (String) soCustDtl.get(NLGB021001Constant.COL_CTAC_PTNR_PSN_NM));
                ZYPEZDItemValueSetter.setValue(shipToT.shipToCtacNum, (String) soCustDtl.get(NLGB021001Constant.COL_CTAC_PTNR_PSN_TEL_NUM));
                // QC#31042
                ZYPEZDItemValueSetter.setValue(shipToT.shipToCtacPsnNm, (String) soCustDtl.get(NLGB021001Constant.COL_SHIP_TO_CTAC_PSN_NM));

                EZDTBLAccessor.insert(shipToT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToT.getReturnCode())) {
                    throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_SHIP_TO, NLGB021001Constant.TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM)
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
            ZYPEZDItemValueSetter.setValue(chrgToT.intfcTpId, NLGB021001Constant.VAL_INTFC_TP_ID_02);
            ZYPEZDItemValueSetter.setValue(chrgToT.intfcRecTpId, NLGB021001Constant.VAL_INTFC_REC_TP_ID_CHRG);
            ZYPEZDItemValueSetter.setValue(chrgToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(chrgToT.wmsSoId, hdrT.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(chrgToT.wmsCustCd, hdrT.chrgToCustCd.getValue());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("soNum", hdrT.wmsSoId.getValue());
            ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SELL_TO);
            ssmParam.put("lgLineColNm", NLGB021001Constant.MAX_LENGTH_SO_CUST_LINE_LOC_NM);
            ssmParam.put("lgCtacTelNum", NLGB021001Constant.MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);
            Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);

            if (soCustDtl != null) {
                ZYPEZDItemValueSetter.setValue(chrgToT.wmsChrgToNm_01, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_LOC_NM_01));
                ZYPEZDItemValueSetter.setValue(chrgToT.wmsChrgToNm_02, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_LOC_NM_02));
                ZYPEZDItemValueSetter.setValue(chrgToT.firstLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_01));
                ZYPEZDItemValueSetter.setValue(chrgToT.scdLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_02));
                ZYPEZDItemValueSetter.setValue(chrgToT.thirdLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_03));
                ZYPEZDItemValueSetter.setValue(chrgToT.frthLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_04));
                ZYPEZDItemValueSetter.setValue(chrgToT.ctyAddr, (String) soCustDtl.get(NLGB021001Constant.COL_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(chrgToT.stCd, (String) soCustDtl.get(NLGB021001Constant.COL_ST_CD));
                ZYPEZDItemValueSetter.setValue(chrgToT.postCd, (String) soCustDtl.get(NLGB021001Constant.COL_POST_CD));
                ZYPEZDItemValueSetter.setValue(chrgToT.ctryCd, (String) soCustDtl.get(NLGB021001Constant.COL_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(chrgToT.wmsChrgToCtacNm, (String) soCustDtl.get(NLGB021001Constant.COL_CTAC_PTNR_PSN_NM));
                ZYPEZDItemValueSetter.setValue(chrgToT.chrgToCtacNum, (String) soCustDtl.get(NLGB021001Constant.COL_CTAC_PTNR_PSN_TEL_NUM));

                EZDTBLAccessor.insert(chrgToT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(chrgToT.getReturnCode())) {
                    throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_CHRG_TO, NLGB021001Constant.TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM)
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
            ZYPEZDItemValueSetter.setValue(billToT.intfcTpId, NLGB021001Constant.VAL_INTFC_TP_ID_02);
            ZYPEZDItemValueSetter.setValue(billToT.intfcRecTpId, NLGB021001Constant.VAL_INTFC_REC_TP_ID_BILL);
            ZYPEZDItemValueSetter.setValue(billToT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(billToT.wmsSoId, hdrT.wmsSoId.getValue());
            ZYPEZDItemValueSetter.setValue(billToT.wmsCustCd, hdrT.billToCustCd.getValue());

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("soNum", hdrT.wmsSoId.getValue());
            ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.BILL_TO);
            ssmParam.put("lgLineColNm", NLGB021001Constant.MAX_LENGTH_SO_CUST_LINE_LOC_NM);
            ssmParam.put("lgCtacTelNum", NLGB021001Constant.MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM);
            Map<String, Object> soCustDtl = (Map<String, Object>) ssmBatchClient.queryObject("getSoCustDtl", ssmParam);

            if (soCustDtl != null) {
                ZYPEZDItemValueSetter.setValue(billToT.wmsBillToNm_01, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_LOC_NM_01));
                ZYPEZDItemValueSetter.setValue(billToT.wmsBillToNm_02, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_LOC_NM_02));
                ZYPEZDItemValueSetter.setValue(billToT.firstLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_01));
                ZYPEZDItemValueSetter.setValue(billToT.scdLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_02));
                ZYPEZDItemValueSetter.setValue(billToT.thirdLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_03));
                ZYPEZDItemValueSetter.setValue(billToT.frthLineAddr, (String) soCustDtl.get(NLGB021001Constant.COL_SO_CUST_LINE_ADDR_04));
                ZYPEZDItemValueSetter.setValue(billToT.ctyAddr, (String) soCustDtl.get(NLGB021001Constant.COL_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(billToT.stCd, (String) soCustDtl.get(NLGB021001Constant.COL_ST_CD));
                ZYPEZDItemValueSetter.setValue(billToT.postCd, (String) soCustDtl.get(NLGB021001Constant.COL_POST_CD));
                ZYPEZDItemValueSetter.setValue(billToT.ctryCd, (String) soCustDtl.get(NLGB021001Constant.COL_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(billToT.wmsBillToCtacNm, (String) soCustDtl.get(NLGB021001Constant.COL_CTAC_PTNR_PSN_NM));
                ZYPEZDItemValueSetter.setValue(billToT.billToCtacNum, (String) soCustDtl.get(NLGB021001Constant.COL_CTAC_PTNR_PSN_TEL_NUM));

                EZDTBLAccessor.insert(billToT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(billToT.getReturnCode())) {
                    throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_BILL_TO, NLGB021001Constant.TBL_SHPG_ORD_CUST_DTL
                            , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM)
                            , NLXCMsgHelper.toListedString(glblCmpyCd, billToT.wmsSoId.getValue()) });
                }
            }
            return billToT;
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
                queryParam.put("qtyDecimalPlace", NLGB021001Constant.VAL_EST_DECIMAL_PLACE);

                stmt = ssmLLClient.createPreparedStatement("getCaseAndPallet", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ZYPEZDItemValueSetter.setValue(dtlT.estCseAmtNum, rs.getBigDecimal(NLGB021001Constant.COL_CASES));
                    ZYPEZDItemValueSetter.setValue(dtlT.estPltAmtNum, rs.getBigDecimal(NLGB021001Constant.COL_PALLETS));
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
        }

        /**
         * setWtAndShipVia
         * @param hdrT
         * @param dtlTList
         */
        private void setWtAndShipVia(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList, List<Map<String, Object>> soDtlList, String sceOrdTpCd) {

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

            // ShipVia
            String carrCd = null;
            if (ZYPCommonFunc.hasValue(hdrT.carrCd)) {
                carrCd = hdrT.carrCd.getValue();
            }
            String shpgSvcLvlCd = null;
            if (ZYPCommonFunc.hasValue(hdrT.shpgSvcLvlCd)) {
                shpgSvcLvlCd = hdrT.shpgSvcLvlCd.getValue();
            }

            Map<String, Object> shipViaInfo = null;

            if (soDtlList != null) {

                PRCH_REQ_DTLTMsg prchReqDtlTMsg = getPrchReqDtl(glblCmpyCd, (String) soDtlList.get(0).get(NLGB021001Constant.COL_PRCH_REQ_NUM), (String) soDtlList.get(0).get(NLGB021001Constant.COL_PRCH_REQ_LINE_NUM), (BigDecimal) soDtlList.get(0).get(NLGB021001Constant.COL_PRCH_REQ_LINE_SUB_NUM));

                if (NLGB021001Constant.TECH_REQUEST.equals(sceOrdTpCd) //
                        && (
                                (
                                        ZYPCommonFunc.hasValue(prchReqDtlTMsg.rqstRcvDt) //
                                        && ZYPCommonFunc.hasValue(prchReqDtlTMsg.rqstRcvTm)
                                ) //
                                || ZYPCommonFunc.hasValue(hdrT.schdDelyDt)
                            )
                    ) {

                    shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), carrCd, shpgSvcLvlCd, NLGB021001Constant.VAL_0);
                    if (shipViaInfo == null) {
                        shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), carrCd, shpgSvcLvlCd, NLGB021001Constant.VAL_1);
                    }
                    if (shipViaInfo == null) {
                        shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), null, shpgSvcLvlCd, NLGB021001Constant.VAL_1);
                    }
                    if (shipViaInfo == null) {
                        shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), null, null, NLGB021001Constant.VAL_1);
                    }
                } else {

                    shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), carrCd, shpgSvcLvlCd, NLGB021001Constant.VAL_1);
                    if (shipViaInfo == null) {
                        shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), null, shpgSvcLvlCd, NLGB021001Constant.VAL_1);
                    }
                    if (shipViaInfo == null) {
                        shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), null, null, NLGB021001Constant.VAL_1);
                    }
                }

            } else {

                shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), carrCd, shpgSvcLvlCd, NLGB021001Constant.VAL_1);
                if (shipViaInfo == null) {
                    shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), null, shpgSvcLvlCd, NLGB021001Constant.VAL_1);
                }
                if (shipViaInfo == null) {
                    shipViaInfo = getTplCarrSvcLvl(hdrT.glblCmpyCd.getValue(), null, null, NLGB021001Constant.VAL_1);
                }
            }

            if (shipViaInfo != null) {
                ZYPEZDItemValueSetter.setValue(hdrT.asgShipViaCd, (String) shipViaInfo.get(NLGB021001Constant.COL_TPL_CARR_CD));
                ZYPEZDItemValueSetter.setValue(hdrT.asgPrtyCd, (String) shipViaInfo.get(NLGB021001Constant.COL_TPL_SVC_LVL_CD));
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
                    ZYPEZDItemValueSetter.setValue(hdrT.tpVndCd, NLGB021001Constant.VAL_HAZMAT);
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
         * Register SO I/F Tables.
         * @param wmsInbdSoHdrT WMS_INBD_SO_HDRTMsg
         * @param wmsInbdSoDtlTList WMS_INBD_SO_DTLTMsg List
         * @param wmsInbdSoShipToT WMS_INBD_SO_SHIP_TOTMsg
         * @param wmsInbdSoTextTList WMS_INBD_SO_TEXTTMsg List
         * @param wmsInbdSoChrgToT WMS_INBD_SO_CHRG_TOTMsg
         * @param wmsInbdSoBillToT WMS_INBD_SO_BILL_TOTMsg
         * @return true / success, false / error
         */
        private boolean registerSo(WMS_INBD_SO_HDRTMsg wmsInbdSoHdrT, List<WMS_INBD_SO_DTLTMsg> wmsInbdSoDtlTList, WMS_INBD_SO_SHIP_TOTMsg wmsInbdSoShipToT, List<WMS_INBD_SO_TEXTTMsg> wmsInbdSoTextTList,
                WMS_INBD_SO_CHRG_TOTMsg wmsInbdSoChrgToT, WMS_INBD_SO_BILL_TOTMsg wmsInbdSoBillToT, String sceOrdTpCd, String vndCd) {

            if (!ZYPCommonFunc.hasValue(soTrxId)) {
                soTrxId = s21TrxTblAccessor.getNextTransactionId();
            }

            BigDecimal soSeqNumber = BigDecimal.ZERO;

            soSeqNumber = createSoIf(wmsInbdSoHdrT, wmsInbdSoDtlTList, wmsInbdSoShipToT, wmsInbdSoTextTList, wmsInbdSoChrgToT, soSeqNumber);
            if (!ZYPCommonFunc.hasValue(soSeqNumber)) {
                return false;
            }

            soUnitId = soUnitId.add(BigDecimal.ONE);
            return true;
        }

        /**
         * getStoreKeyTxt
         * @param gCompCd
         * @param whCd
         * @return
         */
        private String getStoreKeyTxt(String gCompCd, String whCd) {
            String storeKeyTxt = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", gCompCd);
                queryParam.put("whCd", whCd);

                stmt = ssmLLClient.createPreparedStatement("getStoreKeyTxt", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    storeKeyTxt = rs.getString("WMS_DESC_SHORT_NM");
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }

            return storeKeyTxt;
        }

        /**
         * getdsOrdCatgCd
         * @param gCompCd
         * @param wmsOrdNum
         * @return
         */
        private Map<String, String> getdsOrdCatgCd(String gCompCd, String wmsOrdNum) {
            Map<String, String> dsOrdCatgMap = new HashMap<String, String>();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", gCompCd);
                queryParam.put("cpoOrdNum", wmsOrdNum);

                stmt = ssmLLClient.createPreparedStatement("getdsOrdCatgCd", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    dsOrdCatgMap.put("DS_ORD_CATG_CD", rs.getString("DS_ORD_CATG_CD"));
                    dsOrdCatgMap.put("ORD_BOOK_TS", rs.getString("ORD_BOOK_TS"));
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
            return dsOrdCatgMap;
        }

        /**
         * getShpgPln
         * @param gCompCd
         * @param shpgPlnNum
         * @return
         */
        private SHPG_PLNTMsg getShpgPln(String gCompCd, String shpgPlnNum) {

            SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.shpgPlnNum, shpgPlnNum);
            SHPG_PLNTMsg outMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKey(inMsg);

            return outMsg;
        }

        /**
         * getToc
         * @param gCompCd
         * @param tocCd
         * @return
         */
        private TOCTMsg getToc(String gCompCd, String tocCd) {
            TOCTMsg inMsg = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.tocCd, tocCd);
            TOCTMsg outMsg = (TOCTMsg) EZDTBLAccessor.findByKey(inMsg);
            return outMsg;
        }

        /**
         * @param gCompCd
         * @param soNum
         * @return
         */
        private String getSoSer(String gCompCd, String soNum) {
            String ret = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", gCompCd);
                queryParam.put("soNum", soNum);

                stmt = ssmLLClient.createPreparedStatement("getSoSer", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ret = rs.getString("SER_NUM");
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
            return ret;
        }

        /**
         * getdsCpoCtacPsn
         * @param gCompCd
         * @param wmsOrdNum
         * @return
         */
        private Map<String, Object> getdsCpoCtacPsn(String gCompCd, String wmsOrdNum) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", gCompCd);
            queryParam.put("cpoOrdNum", wmsOrdNum);
            queryParam.put("ctacPsnTpCd", NLGB021001Constant.VAL_CTAC_PSN_TP_CD);
            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getdsCpoCtacPsn", queryParam);
            return result;
        }

        /**
         * getTechMstrInfo
         * @param gCompCd
         * @param techTocCd
         * @return
         */
        private TECH_MSTRTMsg getTechMstrInfo(String gCompCd, String techTocCd) {

            TECH_MSTRTMsg outMsg = new TECH_MSTRTMsg();

            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", gCompCd);
                queryParam.put("techTocCd", techTocCd);

                stmt = ssmLLClient.createPreparedStatement("getTechMstrInfo", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ZYPEZDItemValueSetter.setValue(outMsg.techTocCd, rs.getString("TECH_TOC_CD"));
                    ZYPEZDItemValueSetter.setValue(outMsg.techNm, rs.getString("TECH_NM"));
                    ZYPEZDItemValueSetter.setValue(outMsg.emlAddr, rs.getString("EML_ADDR"));

                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
            return outMsg;
        }

        /**
         * getRtlWhAcct
         * @param gCompCd
         * @param rtlWhCd
         * @param rtlWhAcctTpCd
         * @return
         */
        private RTL_WHTMsg getRtlWhAcct(String gCompCd, String rtlWhCd, String rtlWhAcctTpCd) {

            RTL_WHTMsg inMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);

            RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);

            return outMsg;
        }

        /**
         * getDsOrdCatg
         * @param gCompCd
         * @param dsOrdCatgCd
         * @return
         */
        private DS_ORD_CATGTMsg getDsOrdCatg(String gCompCd, String dsOrdCatgCd) {

            DS_ORD_CATGTMsg inMsg = new DS_ORD_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsOrdCatgCd, dsOrdCatgCd);

            DS_ORD_CATGTMsg outMsg = (DS_ORD_CATGTMsg) EZDTBLAccessor.findByKey(inMsg);

            return outMsg;
        }

        /**
         * getDsInvtyOrdDtl
         * @param gCompCd
         * @param invtyOrdNum
         * @param invtyOrdLineNum
         * @return
         */
        private INVTY_ORD_DTLTMsg getDsInvtyOrdDtl(String gCompCd, String invtyOrdNum, String invtyOrdLineNum) {

            INVTY_ORD_DTLTMsg inMsg = new INVTY_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.invtyOrdNum, invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(inMsg.invtyOrdLineNum, invtyOrdLineNum);

            INVTY_ORD_DTLTMsg outMsg = (INVTY_ORD_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

            return outMsg;
        }

        /**
         * getPrchReq
         * @param gCompCd
         * @param prchReqNum
         * @return
         */
        private PRCH_REQTMsg getPrchReq(String gCompCd, String prchReqNum) {

            PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
            PRCH_REQTMsg outMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(inMsg);

            return outMsg;
        }

        /**
         * getPrchReqDtl
         * @param gCompCd
         * @param prchReqNum
         * @param prchReqLineNum
         * @param prchReqLineSubNum
         * @return
         */
        private PRCH_REQ_DTLTMsg getPrchReqDtl(String gCompCd, String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {

            PRCH_REQ_DTLTMsg inMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
            ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
            ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineNum, prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(inMsg.prchReqLineSubNum, prchReqLineSubNum);
            PRCH_REQ_DTLTMsg outMsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

            return outMsg;
        }

        /**
         * getTplMoSoTpTxt
         * @param gCompCd
         * @param prchReqNum
         * @return
         */
        private String getTplMoSoTpTxt(String gCompCd, String prchReqNum) {
            String ret = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                // SQL bind parameter
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", gCompCd);
                queryParam.put("prchReqNum", prchReqNum);

                stmt = ssmLLClient.createPreparedStatement("getTplMoSoTpTxt", queryParam);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ret = rs.getString("PRCH_REQ_TP_NM");
                }
            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            }
            return ret;
        }

        /**
         * getTplCarrSvcLvl
         * @param whOwnrCd
         * @param carrCd
         * @param shpgSvcLvlCd
         * @param tplSvcLvlCdTpCond
         * @return
         */
        private Map<String, Object> getTplCarrSvcLvl(String gCompCd, String carrCd, String shpgSvcLvlCd, String tplSvcLvlCdTpCond) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", gCompCd);
            queryParam.put("whOwnrCd", NLGB021001Constant.VAL_WH_OWNR_CD_DBS);
            queryParam.put("carrCd", carrCd);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            queryParam.put("tplSvcLvlCdTpCond", tplSvcLvlCdTpCond);
            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getTplCarrSvcLvl", queryParam);
            return result;
        }

        /**
         * Create SO Interface Table(NLGI1200_01TMsg) List.
         * @param hdrT
         * @param dtlTList
         * @param shipToT
         * @param wmsInbdSoTextTList
         * @param chrgToT
         * @param soSeqNumber
         * @return
         */
        private BigDecimal createSoIf(WMS_INBD_SO_HDRTMsg hdrT, List<WMS_INBD_SO_DTLTMsg> dtlTList, WMS_INBD_SO_SHIP_TOTMsg shipToT, List<WMS_INBD_SO_TEXTTMsg> wmsInbdSoTextTList, WMS_INBD_SO_CHRG_TOTMsg chrgToT, BigDecimal soSeqNumber) {
            boolean ordTpTR = false;
            NLGI1200_01TMsg algi120001T = new NLGI1200_01TMsg();

            SHPG_ORDTMsg shpgOrdTMsg = getShpgOrd(glblCmpyCd, hdrT.wmsSoId.getValue());
            Map<String, Object> dsCpoCtacPsnInfo = getdsCpoCtacPsn(glblCmpyCd, hdrT.wmsOrdNum.getValue());
            String storeKeyTxt = getStoreKeyTxt(glblCmpyCd, hdrT.whCd.getValue());

            // getTplCarrSvcLvl
//            Map<String, Object> tplCarrSvcLvlInfo = null;
//            String carrCd = null;
//            if (ZYPCommonFunc.hasValue(hdrT.carrCd)) {
//                carrCd = hdrT.carrCd.getValue();
//            }
//            String shpgSvcLvlCd = null;
//            if (ZYPCommonFunc.hasValue(hdrT.shpgSvcLvlCd)) {
//                shpgSvcLvlCd = hdrT.shpgSvcLvlCd.getValue();
//            }
//            tplCarrSvcLvlInfo = getTplCarrSvcLvl(glblCmpyCd, carrCd, shpgSvcLvlCd);

            Map<String, String> dsOrdCatgMap = getdsOrdCatgCd(glblCmpyCd, hdrT.wmsOrdNum.getValue());
            String dsOrdCatgCd = dsOrdCatgMap.get("DS_ORD_CATG_CD");
            String ordBookTs = dsOrdCatgMap.get("ORD_BOOK_TS");

            for (WMS_INBD_SO_DTLTMsg dtlT : dtlTList) {
                if (BigDecimal.ZERO.compareTo(dtlT.wmsShipQty.getValue()) != -1) {
                    continue;
                }

                SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = getShpgOrdDtl(glblCmpyCd, hdrT.wmsSoId.getValue(), ZYPCommonFunc.leftPad(String.valueOf(dtlT.wmsLineNum.getValue()), 3, "0"));
                if (shpgOrdDtlTMsg == null) {
                    continue;
                }
                SHPG_PLNTMsg shpgPlnTMsg = getShpgPln(glblCmpyCd, shpgOrdDtlTMsg.shpgPlnNum.getValue());
                TECH_MSTRTMsg techMstrTMsg = getTechMstrInfo(glblCmpyCd, shpgOrdDtlTMsg.toInvtyLocCd.getValue());

                // is TECH_REQUEST ?
                if (NLGB021001Constant.TECH_REQUEST.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {
                    ordTpTR = true;
                } else {
                    ordTpTR = false;
                }
                ZYPEZDItemValueSetter.setValue(algi120001T.interfaceId, wmsSoDnldSoIntfcId);
                ZYPEZDItemValueSetter.setValue(algi120001T.transactionId, soTrxId);
                ZYPEZDItemValueSetter.setValue(algi120001T.segmentId, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(algi120001T.unitId, soUnitId);
                soSeqNumber = soSeqNumber.add(BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(algi120001T.seqNumber, soSeqNumber);

                // TPL_ORD_NUM
                ZYPEZDItemValueSetter.setValue(algi120001T.tplOrdNum, hdrT.wmsSoId.getValue());

                // TPL_SRC_ORD_NUM
                if (ordTpTR) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplSrcOrdNum, shpgOrdDtlTMsg.prchReqNum.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplSrcOrdNum, hdrT.wmsOrdNum.getValue());
                }

                // TPL_CUST_ORD_NUM
                if (!ordTpTR) {
                    // QC#26027
                    String custOrdNum = "";
                    if (ZYPCommonFunc.hasValue(hdrT.custOrdNum)) {
                        custOrdNum = hdrT.custOrdNum.getValue();
                        int degit = algi120001T.getAttr("tplCustOrdNum").getDigit();
                        if (custOrdNum.length() > degit) {
                            custOrdNum = custOrdNum.substring(0, degit);
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplCustOrdNum, custOrdNum);
                }
                // /////////////////////////////////////////
                // TPL_TRX_TP_CD
                // /////////////////////////////////////////
                // QC#17396
                Map<String, Object> hazMatMap = getHazMatInfo(glblCmpyCd, shpgOrdDtlTMsg.mdseCd.getValue());
                if (hazMatMap != null && !hazMatMap.isEmpty()) {
                    String wmsHazMatFlg = (String) hazMatMap.get("WMS_HAZ_MAT_FLG");
                    String serNumReqTpCd = (String) hazMatMap.get("SER_NUM_REQ_TP_CD");
                    if (ZYPConstant.FLG_ON_Y.equals(wmsHazMatFlg)) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplTrxTpCd, NLGB021001Constant.VAL_HAZMAT);
                    } else if("2".equals(serNumReqTpCd)) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplTrxTpCd, NLGB021001Constant.VAL_EQUIPMENT);
                    } else if ("2P".equals(hdrT.whCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplTrxTpCd, NLGB021001Constant.VAL_PARTS);
                    } else {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplTrxTpCd, NLGB021001Constant.VAL_SUPPLIES);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplTrxTpCd, NLGB021001Constant.VAL_SUPPLIES);
                }
                // /////////////////////////////////////////
                // TPL_STORE_KEY_TXT
                // /////////////////////////////////////////
                // WMS_WHTMsg wmsWhTMsg = getWmsWhInfo(glblCmpyCd,
                // hdrT.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(algi120001T.tplStoreKeyTxt, storeKeyTxt);

                // TPL_SHIP_TO_CTAC_NM_01
                // QC17315 Mod.
//                if (ordTpTR) {
//                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, techMstrTMsg.techNm);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, shipToT.wmsShipToCtacNm.getValue());
//                }
                // QC#31042. QC#50072   
                if (ordTpTR && ZYPCommonFunc.hasValue(shipToT.shipToCtacPsnNm)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, adjustString(convertDbsCharacter(shipToT.shipToCtacPsnNm.getValue()), algi120001T.getAttr("tplShipToCtacNm_01").getDigit()));
                } else if (ordTpTR && ZYPCommonFunc.hasValue(techMstrTMsg.techNm)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, adjustString(convertDbsCharacter(techMstrTMsg.techNm.getValue()), algi120001T.getAttr("tplShipToCtacNm_01").getDigit()));
                } else if (ZYPCommonFunc.hasValue(shipToT.shipToCtacPsnNm)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, adjustString(convertDbsCharacter(shipToT.shipToCtacPsnNm.getValue()), algi120001T.getAttr("tplShipToCtacNm_01").getDigit()));
                } else if (ZYPCommonFunc.hasValue(shipToT.wmsShipToCtacNm)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, adjustString(convertDbsCharacter(shipToT.wmsShipToCtacNm.getValue()), algi120001T.getAttr("tplShipToCtacNm_01").getDigit()));
                }

                // TPL_SHIP_TO_CTAC_NM_02 (blank)
                // TPL_SHIP_TO_PHO_NUM
                // START 2020/02/19 [QC#55641, MOD]
//                if (!ordTpTR) {
//                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToPhoNum, shipToT.shipToCtacNum.getValue());
//                }
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToPhoNum, shipToT.shipToCtacNum.getValue());
                // END 2020/02/19 [QC#55641, MOD]

                // TPL_SHIP_TO_NM (WMS_INBD_SO_SHIP_TO_TO
                // WMS_SHIP_TO_TO_NM_01)
                // START 2018/07/13 S.Katsuma [QC#26710,MOD]
//                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToNm, shipToT.wmsShipToNm_01.getValue());
                String tplShipToNum = shipToT.wmsShipToNm_01.getValue() + shipToT.wmsShipToNm_02.getValue();
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToNm, adjustString(tplShipToNum, algi120001T.getAttr("tplShipToNm").getDigit()));
                // END 2018/07/13 S.Katsuma [QC#26710,MOD]

                // TPL_SHIP_TO_FIRST_LINE_ADDR (WMS_INBD_SO_SHIP_TO_TO
                // FIRST_LINE_ADDR)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToFirstLineAddr, adjustString(shipToT.firstLineAddr.getValue(), algi120001T.getAttr("tplShipToFirstLineAddr").getDigit()));

                // TPL_SHIP_TO_SCD_LINE_ADDR (WMS_INBD_SO_SHIP_TO_TO
                // SCD_LINE_ADDR)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToScdLineAddr, adjustString(shipToT.scdLineAddr.getValue(), algi120001T.getAttr("tplShipToScdLineAddr").getDigit()));

                // TPL_SHIP_TO_THRD_LINE_ADDR (WMS_INBD_SO_SHIP_TO_TO
                // THIRD_LINE_ADDR)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToThrdLineAddr, adjustString(shipToT.thirdLineAddr.getValue(), algi120001T.getAttr("tplShipToThrdLineAddr").getDigit()));

                // TPL_SHIP_TO_FRTH_LINE_ADDR (WMS_INBD_SO_SHIP_TO_TO
                // FRTH_LINE_ADDR)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToFrthLineAddr, adjustString(shipToT.frthLineAddr.getValue(), algi120001T.getAttr("tplShipToFrthLineAddr").getDigit()));

                // TPL_SHIP_TO_CTY_TXT (WMS_INBD_SO_SHIP_TO_TO
                // CTY_ADDR)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtyTxt, shipToT.ctyAddr.getValue());

                // TPL_SHIP_TO_ST_OR_PROV_TXT (WMS_INBD_SO_SHIP_TO_TO
                // ST_CD)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToStOrProvTxt, shipToT.stCd.getValue());

                // TPL_SHIP_TO_ZIP_OR_POST_CD (WMS_INBD_SO_SHIP_TO_TO
                // POST_CD)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToZipOrPostCd, shipToT.postCd.getValue());

                // TPL_SHIP_TO_CNTY_TXT (blank)

                // TPL_SHIP_TO_CTRY_TXT (WMS_INBD_SO_SHIP_TO_TO
                // CTRY_CD)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtryTxt, shipToT.ctryCd.getValue());

                // QC#21657-1 Modify Start.
                // QC#21657-2 Modify
//                if(ordTpTR && ZYPConstant.FLG_ON_Y.equals(shpgPlnTMsg.dropShipFlg.getValue())){
                if(ordTpTR){
                    // [Drop Ship] Update Ship to Address.
                    PRCH_REQTMsg prchReqT = getPrchReq(glblCmpyCd, shpgOrdTMsg.srcOrdNum.getValue());
                    // QC#21657-2 Modify
//                  if(prchReqT != null){
                    if(prchReqT != null && ZYPCommonFunc.hasValue(prchReqT.shipToCustCd)){
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtacNm_01, adjustString(convertDbsCharacter(prchReqT.ctacPsnNm.getValue()), algi120001T.getAttr("tplShipToCtacNm_01").getDigit()));
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToNm, adjustString(prchReqT.shipToLocNm.getValue(), algi120001T.getAttr("tplShipToNm").getDigit()));
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToFirstLineAddr, adjustString(prchReqT.shipToFirstLineAddr.getValue(), algi120001T.getAttr("tplShipToFirstLineAddr").getDigit()));
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToScdLineAddr, adjustString(prchReqT.shipToScdLineAddr.getValue(), algi120001T.getAttr("tplShipToScdLineAddr").getDigit()));
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToThrdLineAddr, adjustString(prchReqT.shipToThirdLineAddr.getValue(), algi120001T.getAttr("tplShipToThrdLineAddr").getDigit()));
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToFrthLineAddr, adjustString(prchReqT.shipToFrthLineAddr.getValue(), algi120001T.getAttr("tplShipToFrthLineAddr").getDigit()));
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtyTxt, prchReqT.shipToCtyAddr);
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToStOrProvTxt, prchReqT.shipToStCd);
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToZipOrPostCd, prchReqT.shipToPostCd);
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCntyTxt, prchReqT.shipToCntyNm);
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCtryTxt, prchReqT.shipToCtryCd);
                    }
                }
               // QC#21657-1 Modify End.

                // TPL_CARR_CD
//                String tplCarrCd = null;
//                if (tplCarrSvcLvlInfo != null && tplCarrSvcLvlInfo.get(NLGB021001Constant.COL_TPL_CARR_CD) != null) {
//                    tplCarrCd = (String) tplCarrSvcLvlInfo.get(NLGB021001Constant.COL_TPL_CARR_CD);
//                }
//                ZYPEZDItemValueSetter.setValue(algi120001T.tplCarrCd, tplCarrCd);
                ZYPEZDItemValueSetter.setValue(algi120001T.tplCarrCd, hdrT.asgShipViaCd);

                // TPL_PRTY_NUM
                String tplPrtyNum = null;
                if (ZYPCommonFunc.hasValue(hdrT.asgPrtyCd)) {
                    tplPrtyNum = hdrT.asgPrtyCd.getValue();
                }
//                if (tplCarrSvcLvlInfo != null && tplCarrSvcLvlInfo.get(NLGB021001Constant.COL_TPL_SVC_LVL_CD) != null) {
//                    tplPrtyNum = (String) tplCarrSvcLvlInfo.get(NLGB021001Constant.COL_TPL_SVC_LVL_CD);
//                }
//                ZYPEZDItemValueSetter.setValue(algi120001T.tplPrtyNum, tplPrtyNum);
                ZYPEZDItemValueSetter.setValue(algi120001T.tplPrtyNum, tplPrtyNum);

                // /////////////////////////////////////////////
                // TPL_BILL_CUST_TXT
                // /////////////////////////////////////////////
                String tplBillCustTxt = null;
                if (ordTpTR) {
                    RTL_WHTMsg rtlWhTMsg = getRtlWhAcct(glblCmpyCd, shipToT.wmsCustCd.getValue(), "01");
                    if (rtlWhTMsg != null && rtlWhTMsg.coaBrCd != null && ZYPCommonFunc.hasValue(tplPrtyNum)) {
                        tplBillCustTxt = "P" + "-" + tplPrtyNum + "-" + rtlWhTMsg.coaBrCd.getValue();
                    }
                } else {
                    String coaBrCd = "";
                    if (shpgPlnTMsg != null && ZYPCommonFunc.hasValue(shpgPlnTMsg.slsRepTocCd)) {
                        TOCTMsg tocTMsg = getToc(glblCmpyCd, shpgPlnTMsg.slsRepTocCd.getValue());
                        if (tocTMsg != null && tocTMsg.coaBrCd != null) {
                            coaBrCd = tocTMsg.coaBrCd.getValue();
                        }
                    }

                    if ((ZYPCommonFunc.hasValue(dsOrdCatgCd)) && (ZYPCommonFunc.hasValue(tplPrtyNum)) && (ZYPCommonFunc.hasValue(coaBrCd))) {
                        DS_ORD_CATGTMsg dsOrdCatgTMsg = getDsOrdCatg(glblCmpyCd, dsOrdCatgCd);
                        if (dsOrdCatgTMsg != null) {
                            tplBillCustTxt = dsOrdCatgTMsg.dsOrdCatgNm.getValue().substring(0, 1) + "-" + tplPrtyNum + "-" + coaBrCd;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(tplBillCustTxt)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplBillCustTxt, tplBillCustTxt);
                }

                // /////////////////////////////////////////////
                // TPL_BILL_ACCT_NUM
                // /////////////////////////////////////////////
                if (ordTpTR) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplBillAcctNum, NLGB021001Constant.VAL_NA);
                } else {
                    if (shpgPlnTMsg != null && ZYPCommonFunc.hasValue(shpgPlnTMsg.carrAcctNum)) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplBillAcctNum, shpgPlnTMsg.carrAcctNum.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplBillAcctNum, NLGB021001Constant.VAL_NA);
                    }
                }

                // //////////////////////////////////////////
                // TPL_FRT_TERM_TXT
                // //////////////////////////////////////////
                if (ordTpTR) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplFrtTermTxt, NLGB021001Constant.VAL_PPD);
                } else {
                    if (!NLGB021001Constant.VAL_NA.equals(algi120001T.tplBillAcctNum.getValue())) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplFrtTermTxt, NLGB021001Constant.VAL_THIRD_PARTY);
                    } else {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplFrtTermTxt, NLGB021001Constant.VAL_PPD);
                    }
                }

                // /////////////////////////////////////////////
                // TPL_ORD_DATE_TXT (WMS_INBD_SO_HDR
                // HOST_ORD_DT_TM_TS)
                // /////////////////////////////////////////////
                if (ordTpTR) {
                    PRCH_REQTMsg prchReqTMsg = getPrchReq(glblCmpyCd, shpgOrdDtlTMsg.prchReqNum.getValue());
                    if (prchReqTMsg != null) {
                        if (prchReqTMsg.prchReqCratDt.getValue() != null) {
                            ZYPEZDItemValueSetter.setValue(algi120001T.tplOrdDateTxt, dateFormat(prchReqTMsg.prchReqCratDt.getValue() + prchReqTMsg.prchReqCratTm.getValue(), NLGB021001Constant.FMT_YYYYMMDDHHMMSS, NLGB021001Constant.FMT_DDMONYYYYHHMM));
                        }
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(ordBookTs)) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplOrdDateTxt, dateFormat(ordBookTs.substring(0, 14), NLGB021001Constant.FMT_YYYYMMDDHHMMSS, NLGB021001Constant.FMT_DDMONYYYYHHMM));
                    }
                }
                // ////////////////////////////////////////
                // TPL_ORD_CMNT_TXT
                // /////////////////////////////////////////
                // QC17315 Mod.
                for (WMS_INBD_SO_TEXTTMsg soTextTList : wmsInbdSoTextTList) {
                    if ("S".equals(soTextTList.wmsTxtCd.getValue())) {
                        String inbdSoMsgTxt01 = "";
                        String inbdSoMsgTxt02 = "";
                        String inbdSoMsgTxt03 = "";
                        String inbdSoMsgTxt04 = "";
                        if (ZYPCommonFunc.hasValue(soTextTList.inbdSoMsgTxt_01)) {
                            inbdSoMsgTxt01 = convertDbsCharacter(soTextTList.inbdSoMsgTxt_01.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(soTextTList.inbdSoMsgTxt_02)) {
                            inbdSoMsgTxt02 = convertDbsCharacter(soTextTList.inbdSoMsgTxt_02.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(soTextTList.inbdSoMsgTxt_03)) {
                            inbdSoMsgTxt03 = convertDbsCharacter(soTextTList.inbdSoMsgTxt_03.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(soTextTList.inbdSoMsgTxt_04)) {
                            inbdSoMsgTxt04 = convertDbsCharacter(soTextTList.inbdSoMsgTxt_04.getValue());
                        }
                        String soMsgTxt = inbdSoMsgTxt01 + inbdSoMsgTxt02 + inbdSoMsgTxt03 + inbdSoMsgTxt04;
                        if (ZYPCommonFunc.hasValue(soMsgTxt) && soMsgTxt.length() > 255) {
                            soMsgTxt = soMsgTxt.substring(0, 255);
                        }
                        
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplOrdCmntTxt, soMsgTxt);

                        break;
                    }
                }
                // TPL_ORD_CMNT_TXT_02(blank)
                // TPL_SO_LINE_ID
                // Mod QC#26429
                String wkTplSoLineId = tplSoLineId + decimalFormat(dtlT.wmsLineNum.getValue(), NLGB021001Constant.VAL_000);
                String tplSoLineId = null;
                if (NLGB021001Constant.TECH_REQUEST.equals(shpgOrdTMsg.sceOrdTpCd.getValue())) {
                    tplSoLineId = "M" + wkTplSoLineId;
                } else {
                    tplSoLineId = "S" + wkTplSoLineId;
                }
                ZYPEZDItemValueSetter.setValue(algi120001T.tplSoLineId, tplSoLineId);

                // TPL_ITEM_CD (WMS_INBD_SO_DTL WMS_MDSE_CD)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplItemCd, dtlT.wmsMdseCd.getValue());

                // TPL_HLD_CD (WMS_INBD_SO_DTL S80_STK_STS_CD)
                if ((ZYPCommonFunc.hasValue(dtlT.s80StkStsCd))) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplHldCd, dtlT.s80StkStsCd.getValue().substring(1, 2));
                }
                // ////////////////////////////////////////
                // TPL_SER_NUM
                // ////////////////////////////////////////
                if (!ordTpTR) {
                    String soSer = getSoSer(glblCmpyCd, hdrT.wmsSoId.getValue());
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplSerNum, soSer);
                }

                // TPL_QTY_ORD_TXT ( WMS_INBD_SO_DTL WMS_SHIP_TO_QTY )
                if (ZYPCommonFunc.hasValue(dtlT.wmsShipQty)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplQtyOrdTxt, decimalFormat(dtlT.wmsShipQty.getValue(), NLGB021001Constant.FMT_0));
                }

                // TPL_ITEM_UOM_CD ( WMS_INBD_SO_DTL WMS_ORD_UOM_CD )
                if (ZYPCommonFunc.hasValue(dtlT.wmsOrdUomCd)) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplItemUomCd, dtlT.wmsOrdUomCd.getValue());
                }

                // TPL_SHIP_SET_ID (blank)

                // TPL_LINE_DATE_TXT ( blank (on v12 mapping))

                // TPL_REP_EMP_ID
                if (ordTpTR && shpgOrdDtlTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplRepEmpId, techMstrTMsg.techTocCd.getValue());
                }

                // TPL_REP_EMP_NM (blank)
                if (ordTpTR) {
                    // QC#20423
                    String techNm = "";
                    int size = algi120001T.getAttr("tplRepEmpNm").getDigit();
                    if (ZYPCommonFunc.hasValue(techMstrTMsg.techNm)) {
                        techNm = techMstrTMsg.techNm.getValue();
                        if (size < techNm.length()) {
                            techNm = techNm.substring(0, size);
                        } 
                    }
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplRepEmpNm, convertDbsCharacter(techNm));
                }
                // TPL_REP_DSTR_ID (blank)

                // TPL_REP_DSTR_NM (blank)

                // /////////////////////////////////////
                // TPL_SHIP_TO_CUST_TXT
                // /////////////////////////////////////
                if (ordTpTR) {
                    INVTY_ORD_DTLTMsg invtyOrdDtlTMsg = getDsInvtyOrdDtl(glblCmpyCd, shpgOrdDtlTMsg.trxHdrNum.getValue(), shpgOrdDtlTMsg.trxLineNum.getValue());
                    if (invtyOrdDtlTMsg != null && ZYPCommonFunc.hasValue(invtyOrdDtlTMsg.shipToCustCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCustTxt, invtyOrdDtlTMsg.shipToCustCd.getValue());
                    } else {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCustTxt, shpgOrdDtlTMsg.toInvtyLocCd.getValue());
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplShipToCustTxt, shipToT.wmsCustCd.getValue());
                }

                // TPL_BAK_ORD_TXT (0)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplBakOrdTxt, "0");

                // //////////////////////////////////////////
                // TPL_TECH_EMAIL
                // //////////////////////////////////////////
                if (ordTpTR) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplTechEmail, techMstrTMsg.emlAddr);
                }

                // TPL_ATTN_TO_TXT
                if (ordTpTR) {
                    // START 2018/07/13 S.Katsuma [QC#26710,DEL]
//                    ZYPEZDItemValueSetter.setValue(algi120001T.tplAttnToTxt, shipToT.wmsShipToNm_02.getValue());
                    // END 2018/07/13 S.Katsuma [QC#26710,DEL]
                } else {
                    // Mod Start 2019/12/20 QC#55070
                    String tplAttnToTxt = null;
                    if (dsCpoCtacPsnInfo != null) {
                        String ctacPsnFirstNm = "";
                        String ctacPsnMidNm = "";
                        String ctacPsnLastNm = "";
                        String ctacPsnTelNum = "";
                        if (ZYPCommonFunc.hasValue((String) dsCpoCtacPsnInfo.get("CTAC_PSN_FIRST_NM"))) {
                            ctacPsnFirstNm = (String) dsCpoCtacPsnInfo.get("CTAC_PSN_FIRST_NM");
                        }
                        if (ZYPCommonFunc.hasValue((String) dsCpoCtacPsnInfo.get("CTAC_PSN_MID_NM"))) {
                            ctacPsnMidNm = (String) dsCpoCtacPsnInfo.get("CTAC_PSN_MID_NM");
                        }
                        if (ZYPCommonFunc.hasValue((String) dsCpoCtacPsnInfo.get("CTAC_PSN_LAST_NM"))) {
                            ctacPsnLastNm = (String) dsCpoCtacPsnInfo.get("CTAC_PSN_LAST_NM");
                        }
                        if (ZYPCommonFunc.hasValue((String) dsCpoCtacPsnInfo.get("CTAC_PSN_TEL_NUM"))) {
                            ctacPsnTelNum = (String) dsCpoCtacPsnInfo.get("CTAC_PSN_TEL_NUM");
                        }
//                        String tplAttnToTxt = ctacPsnFirstNm + " " + ctacPsnMidNm + " " + ctacPsnLastNm + " " + ctacPsnTelNum;
                        tplAttnToTxt = ctacPsnFirstNm + " " + ctacPsnMidNm + " " + ctacPsnLastNm + " " + ctacPsnTelNum;
//                        ZYPEZDItemValueSetter.setValue(algi120001T.tplAttnToTxt, tplAttnToTxt);
                    }
                    if (!ZYPCommonFunc.hasValue(tplAttnToTxt) && checkGetShipToCtacPsnNm(shpgOrdTMsg.sceOrdTpCd.getValue())) {
                        tplAttnToTxt = getShipToCtacPsnNm(glblCmpyCd, hdrT.wmsOrdNum.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplAttnToTxt, tplAttnToTxt);
                    // Mod End 2019/12/20 QC#55070
                }
                // TPL_MO_SO_TP_TXT (blank)
                if (ordTpTR) {
                    String tplMoSoTpTxt = getTplMoSoTpTxt(glblCmpyCd, shpgOrdDtlTMsg.prchReqNum.getValue());
                    if ((ZYPCommonFunc.hasValue(tplMoSoTpTxt)) && ("Replenishment".equals(tplMoSoTpTxt))) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplMoSoTpTxt, NLGB021001Constant.VAL_REPLENISHMENT);
                    } else {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplMoSoTpTxt, NLGB021001Constant.VAL_REQUISITION);
                    }
                }
                // /////////////////////////////////////
                // TPL_SCHD_DELY_DATE_TXT
                // /////////////////////////////////////
                if (shpgOrdDtlTMsg != null) {
                    PRCH_REQ_DTLTMsg prchReqDtlTMsg = getPrchReqDtl(glblCmpyCd, shpgOrdDtlTMsg.prchReqNum.getValue(), shpgOrdDtlTMsg.prchReqLineNum.getValue(), shpgOrdDtlTMsg.prchReqLineSubNum.getValue());
                    if (prchReqDtlTMsg == null || !ZYPCommonFunc.hasValue(prchReqDtlTMsg.rqstRcvDt)) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplSchdDelyDateTxt, dateFormat(hdrT.schdDelyDt.getValue(), NLGB021001Constant.FMT_YYYYMMDD, NLGB021001Constant.FMT_DDMONYYYYHHMM));
                    } else {
                        if (!ZYPCommonFunc.hasValue(prchReqDtlTMsg.rqstRcvTm)) {
                            ZYPEZDItemValueSetter.setValue(algi120001T.tplSchdDelyDateTxt, dateFormat(prchReqDtlTMsg.rqstRcvDt.getValue(), NLGB021001Constant.FMT_YYYYMMDD, NLGB021001Constant.FMT_DDMONYYYYHHMM));
                        } else {
                            ZYPEZDItemValueSetter.setValue(algi120001T.tplSchdDelyDateTxt, dateFormat(prchReqDtlTMsg.rqstRcvDt.getValue() + prchReqDtlTMsg.rqstRcvTm.getValue(), NLGB021001Constant.FMT_YYYYMMDDHHMM, NLGB021001Constant.FMT_DDMONYYYYHHMM));
                        }
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplSchdDelyDateTxt, dateFormat(hdrT.schdDelyDt.getValue(), NLGB021001Constant.FMT_YYYYMMDD, NLGB021001Constant.FMT_DDMONYYYYHHMM));
                }

                // /////////////////////////////////////
                // TPL_FRT_CARR_CD
                // /////////////////////////////////////
                ZYPEZDItemValueSetter.setValue(algi120001T.tplFrtCarrCd, shpgOrdTMsg.carrCd.getValue());
/*
                if (ordTpTR) {
                    ZYPEZDItemValueSetter.setValue(algi120001T.tplFrtCarrCd, shpgOrdTMsg.carr.getValue());
                } else {
                    if (shpgOrdTMsg != null && ZYPCommonFunc.hasValue(shpgOrdTMsg.vndCd)) {
                        ZYPEZDItemValueSetter.setValue(algi120001T.tplFrtCarrCd, shpgOrdTMsg.vndCd.getValue());
                    }
                }
*/

                // TPL_SWH_CD (WMS_INBD_SO_DTL RTL_SWH_CD)
                ZYPEZDItemValueSetter.setValue(algi120001T.tplSwhCd, dtlT.rtlSwhCd.getValue());

                EZDTBLAccessor.insert(algi120001T);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi120001T.getReturnCode())) {
                    throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_NLGI1100_02, NLGB021001Constant.TBL_WMS_OTBD_SO_WRK //
                            , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM) //
                            // ,
                            // NLXCMsgHelper.toListedString(glblCmpyCd,
                            // algi120001T.ordIdTxt.getValue())
                            });
                }
                algi120001T.clear();

                // START 2017/12/20 S.Katsuma [QC#22592,ADD]
                updateShpgOrdDtl(dtlT);
                // END 2017/12/20 S.Katsuma [QC#22592,ADD]
            }
            return soSeqNumber;
        }

        /**
         * Register WMS_INBD_SO_HDR.
         * @param hdrT WMS_INBD_SO_HDRTMsg
         * @return true / success, false / error
         */
        private boolean registerHdr(WMS_INBD_SO_HDRTMsg hdrT) {

            EZDTBLAccessor.insert(hdrT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrT.getReturnCode())) {
                throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_HDR, NLGB021001Constant.TBL_WMS_OTBD_SO_WRK //
                        , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.wmsSoId.getValue()) });
            }
            return true;
        }

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
         * getHazMatInfo
         * @param gCompCd String
         * @param wmsMdseCd String
         * @return Map<String, Object>
         */
        private Map<String, Object> getHazMatInfo(String gCompCd, String wmsMdseCd) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", gCompCd);
            queryParam.put("mdseCd", wmsMdseCd);
            Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getHazMatInfo", queryParam);
            return result;
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
                throw new S21AbendException(NLGB021001Constant.NLGM0046E, new String[] {NLGB021001Constant.TBL_SHPG_ORD
                        , NLXCMsgHelper.toListedString(NLGB021001Constant.COL_GLBL_CMPY_CD, NLGB021001Constant.COL_SO_NUM)
                        , NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.wmsSoId.getValue()) });
            }

            soT.wmsDropFlg.setValue(wmsDropFlg);
            EZDTBLAccessor.update(soT);
            return true;
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
     * @param String glblCmpyCd, String cpoOrdNum, String
     * cpoDtlLineNum, String cpoDtlLineSubNum
     * @return CPO_DTLTMsg
     */
    private CPO_DTLTMsg getCpoDtl(String gCompCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg inMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        CPO_DTLTMsg outMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * isWmsInbdSoUpdate
     * @param String glblCmpyCd, String soNum
     * @return Map<String, Object>
     */
    private boolean isWmsInbdSoUpdate(String gCompCd, String soNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", gCompCd);
        ssmParam.put("soNum", soNum);

        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countWmsInbdSo", ssmParam);

        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        } else {
            return false;
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
        ZYPEZDItemValueSetter.setValue(inMsg.wmsIntfcTaskNm, "SO");

        WMS_INTFC_CTRLTMsg updMsg = (WMS_INTFC_CTRLTMsg) EZDTBLAccessor.findByKey(inMsg);
        String sysDate = ZYPDateUtil.getCurrentSystemTime(NLGB021001Constant.FMT_YYYYMMDDHHMMSS);
        if (updMsg == null) {
            ZYPEZDItemValueSetter.setValue(inMsg.taskDtTmTs, sysDate);
            EZDTBLAccessor.create(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_HDR });
            }
        } else {
            ZYPEZDItemValueSetter.setValue(updMsg.taskDtTmTs, sysDate);
            EZDTBLAccessor.update(updMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                throw new S21AbendException(NLGB021001Constant.NLGM0045E, new String[] {NLGB021001Constant.TBL_WMS_INBD_SO_HDR });
            }
        }
        return true;
    }

    /**
     * getShpgOrd
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
     * getDsShpgOrdDtl
     * @param String glblCmpyCd, String soNum, String soSlpNum
     * @return DS_SHPG_ORD_DTLTMsg
     */
    private SHPG_ORD_DTLTMsg getShpgOrdDtl(String gCompCd, String soNum, String soSlpNum) {

        SHPG_ORD_DTLTMsg inMsg = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, gCompCd);
        ZYPEZDItemValueSetter.setValue(inMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(inMsg.soSlpNum, soSlpNum);
        SHPG_ORD_DTLTMsg outMsg = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * convertDbsCharacter
     * 
     * @param str convertDbsCharacter
     * @return String
     */
    private String convertDbsCharacter(String str) {

        // '' replace to 'E'
        str = str.replaceAll(NLGB021001Constant.VAL_DBS_CHAR_01, NLGB021001Constant.VAL_DBS_CONV_CHAR_01);
        // '' replace to 'e'
        str = str.replaceAll(NLGB021001Constant.VAL_DBS_CHAR_02, NLGB021001Constant.VAL_DBS_CONV_CHAR_02);
        // TAB replace to ' ' (with one space)
        str = str.replaceAll(NLGB021001Constant.VAL_DBS_CHAR_03, NLGB021001Constant.VAL_DBS_CONV_CHAR_03);
        // LINE FEED(LF) replace to ' ' (with one space)
        str = str.replaceAll(NLGB021001Constant.VAL_DBS_CHAR_04, NLGB021001Constant.VAL_DBS_CONV_CHAR_03);
        // Carriage Return(CR) remove (with NULL)
        str = str.replaceAll(NLGB021001Constant.VAL_DBS_CHAR_05, NLGB021001Constant.VAL_DBS_CONV_CHAR_04);

        return str;
    }

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

    // Add Start 2019/12/20 QC#55070
    /**
     * checkGetShipToCtacPsnNm
     * @param sceOrdTpCd
     */
    private boolean checkGetShipToCtacPsnNm(String sceOrdTpCd) {

        if (SCE_ORD_TP.DIRECT_SALES.equals(sceOrdTpCd)
                || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(sceOrdTpCd)
                || SCE_ORD_TP.DISPOSAL.equals(sceOrdTpCd)
                || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(sceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * getShipToCtacPsnNm
     * @param glblCmpyCd
     * @param trxHdrNum
     */
    private String getShipToCtacPsnNm(String glblCmpyCd, String trxHdrNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("trxHdrNum", trxHdrNum);
        queryParam.put("invtyReqEntry", CPO_SRC_TP.INVENTORY_REQUEST_ENTRY);

        String shipToCtacPsnNm = (String) ssmBatchClient.queryObject("getShipToCtacPsnNm", queryParam);

        if (shipToCtacPsnNm == null || shipToCtacPsnNm.isEmpty()) {
            return null;
        }

        return shipToCtacPsnNm;
    }
    // Add End 2019/12/20 QC#55070
}
