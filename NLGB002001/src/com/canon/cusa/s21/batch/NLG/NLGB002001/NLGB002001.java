/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB002001;

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

import parts.common.EZDItemAttribute;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ABC_ANLS_RSLTTMsg;
import business.db.NLGI2100_01TMsg;
import business.db.NLGI2100_02TMsg;
import business.db.NLGI2100_03TMsg;
import business.db.NLGI2100_04TMsg;
import business.db.NLGI2100_05TMsg;
import business.db.NLGI2100_06TMsg;
import business.db.NLGI2100_07TMsg;
import business.db.NLGI3100_01TMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_MSGTMsg;
import business.db.RWS_MSGTMsgArray;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.db.WMS_INBD_ITEM_SER_WRKTMsg;
import business.db.WMS_INBD_ITEM_UPC_WRKTMsg;
import business.db.WMS_INBD_ITEM_WRKTMsg;
import business.db.WMS_INBD_MDSETMsg;
import business.db.WMS_INBD_MDSE_SERTMsg;
import business.db.WMS_INBD_MDSE_UPCTMsg;
import business.db.WMS_INBD_PO_DTLTMsg;
import business.db.WMS_INBD_PO_HDRTMsg;
import business.db.WMS_INBD_PO_VNDTMsg;
import business.db.WMS_INTFC_CTRLTMsg;
import business.db.WMS_MDSE_LISTTMsg;
import business.db.WMS_RWS_ORIG_LINE_SAVETMsg;
import business.db.WMS_WHTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001;
import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001Constant;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.THIRD_PTY_DSP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ITEM_DNLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
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
 * RWS and Item Download
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/12/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 01/22/2015   CSAI            K.Hayashida     Update          ITG#563549 Item download process in S21MW fails when recurring decimal occurs
 * 11/10/2015   CSAI            K.Lee           Update          S21NA Initial
 * 04/19/2016   CSAI            D.Fukaya        Update          QC#5252
 * 08/02/2016   CSAI            K.Lee           Update          QC#12645
 * 08/26/2016   CITS            K.Masaki        Update          QC#13873
 * 09/01/2016   CITS            T.Wada          Update          QC#13318
 * 11/07/2016   CITS            K.Ogino         Update          QC#15803
 * 11/10/2016   CITS            R.Shimamoto     Update          QC#15121
 * 01/19/2017   CITS            Y.Fujii         Update          QC#17074
 * 02/07/2017   CITS            Y.Fujii         Update          QC#17422
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 05/08/2017   CITS            T.Kikuhara      Update          RS#3135
 * 06/07/2017   CITS            T.Tokutomi      Update          QC#18870
 * 06/08/2017   CITS            T.Tokutomi      Update          QC#18950
 * 06/22/2017   CITS            T.Kikuhara      Update          QC#19477
 * 06/28/2017   CITS            T.Kikuhara      Update          QC#19630
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 * 06/30/2017   CITS            T.Kikuhara      Update          QC#19684
 * 07/03/2017   CITS            Y.Imazu         Update          QC#19720
 * 09/15/2017   CITS            T.Wada          Update          QC#21166
 * 12/25/2017   CITS            T.Wada          Update          QC#22477
 * 01/12/2018   CITS            K.Ogino         Update          QC#23337
 * 03/08/2017   CITS            K.Ogino         Update          QC#15629
 * 03/20/2018   CITS            T.hakodate      Update          QC#25002
 * 03/14/2022   Hitachi         A.Kohinata      Update          QC#59780
 * 05/13/2022   Hitachi         D.Yoshida       Update          QC#59780-1
 *</pre>
 */
public class NLGB002001 extends S21BatchMain implements NLGB002001Constant {

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

    /** Warehouse Group Code */
    private String whGpCd = null;

    /** Process Type */
    private String procTp = null;

    /** Target Warehouse Code */
    String[] trgtWhCdAry = null;

    /** Error Message List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();

    /** Transaction Id for PO IF Table */
    private BigDecimal poTrxId = null;

    /** Transaction Id for MDSE IF Table */
    private BigDecimal mdseTrxId = null;

    /** Unit Id for PO IF Table */
    private BigDecimal poUnitId = BigDecimal.ONE;

    /** Unit Id for MDSE IF Table */
    private BigDecimal mdseUnitId = BigDecimal.ONE;

    /** caching WMS_ORD_TP_XPND_CD */
    private S21LRUMap<String, String> cacheMapWmsOrdTpXpndCd = new S21LRUMap<String, String>(200);

    // QC#17981 Add.
    /** NLGB0020_CRAT_SET_THIRDPTYDSP */
    private String[] createSetThirdPtyDspTpDescTxt = null;

    @Override
    protected void initRoutine() {

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        totalCommitCount = 0;
        totalErrCount = 0;
        termCd = TERM_CD.NORMAL_END;
        errMsgList = new ArrayList<Map<String, String>>();
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // Get batch parameters.
        glblCmpyCd = profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        whGpCd = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        procTp = S21BatchUtil.getUserVariable2();
        if (!ZYPCommonFunc.hasValue(procTp)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_PROC_TP });
        } else if (!(VAL_PROC_TP_RWS.equals(procTp) || VAL_PROC_TP_ITEM.equals(procTp))) {
            throw new S21AbendException(NLGM0060E, new String[] {procTp, PRAM_NM_PROC_TP });
        }

        this.createCacheForWmsOrdTpXpndCd();
    }

    @Override
    protected void mainRoutine() {

        try {
            trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);
            if (trgtWhCdAry == null) {
                outputErr(NLGM0047E, new String[] {whGpCd });
                return;
            }
            // QC#17981 Add.
            String varCharConstTxt = ZYPCodeDataUtil.getVarCharConstValue(NLGB0020_CRAT_SET_THIRDPTYDSP, glblCmpyCd);
            if (varCharConstTxt != null) {
                createSetThirdPtyDspTpDescTxt = varCharConstTxt.split(",");
            }

            if (VAL_PROC_TP_RWS.equals(procTp)) {
                // RWS Download
                doRwsDnldProc();
                updateWmsIntfcCtrl("RWS");

            } else {
                // Item Download
                doItemDnldProc();
                updateWmsIntfcCtrl("ITEM");
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
        new NLGB002001().executeBatch(NLGB002001.class.getSimpleName());
    }

    /**
     * RWS download. If RWS has MDSE that has never sent to WMS then
     * do Item download.
     */
    private void doRwsDnldProc() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Get processed RWS_HDR Data.
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("notDrop", VAL_NOT_DROP);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("len2", VAL_LEN_2);
            queryParam.put("len3", VAL_LEN_3);
            queryParam.put("wms", WH_SYS_TP.WMS);
            queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
            queryParam.put("inbound", INBD_OTBD.INBOUND);
            queryParam.put("lenVndNm", VAL_VND_NM_SIZE);
            /* 07/03/2017 CSAI Y.Imazu Del QC#19720 START */
            // //QC#18950
            // queryParam.put("rwsStsCd", RWS_STS.PRINTED);
            /* 07/03/2017 CSAI Y.Imazu Del QC#19720 END */

            stmt = ssmLLClient.createPreparedStatement("getRws", queryParam);
            rs = stmt.executeQuery();
            while (rs.next()) {

                poTrxId = null;
                poUnitId = BigDecimal.ONE;
                mdseUnitId = BigDecimal.ONE;
                mdseTrxId = null;
                boolean isRegistNewItem = true;
                boolean isRegistInbdPoData = true;
                WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT = null;
                WMS_INBD_PO_VNDTMsg wmsInbdPoVndT = null;
                List<WMS_INBD_PO_DTLTMsg> wmsInbdPoDtlTList = null;

                // New Item check.
                boolean isNewItem = checkNewItem(rs.getString(COL_RWS_NUM), rs.getString(COL_WMS_WH_CD));
                if (isNewItem) {
                    // New Item Download Process.
                    isRegistNewItem = doNewItemDnldProc(rs.getString(COL_RWS_NUM), rs.getString(COL_WMS_WH_CD), rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID));
                } else {
                    // Create Wms Inbound Po Head.
                    wmsInbdPoHdrT = createWmsInbdPoHdr(rs);
                    if (wmsInbdPoHdrT == null) {
                        isRegistInbdPoData = false;
                    } else {
                        // Create Wms Inbound Po Detail.
                        wmsInbdPoDtlTList = createWmsInbdPoDtl(wmsInbdPoHdrT);
                        if (wmsInbdPoDtlTList == null) {
                            isRegistInbdPoData = false;
                        }
                        // Create Wms Inbound Po Vendor.
                        wmsInbdPoVndT = createWmsInbdPoVnd(rs, wmsInbdPoHdrT);
                        if (wmsInbdPoVndT == null) {
                            isRegistInbdPoData = false;
                        }
                    }
                }

                if (!isRegistInbdPoData || !isRegistNewItem) {
                    // Error.
                    // Rollback and Update RWS_HDR status.
                    rollback();
                    updateRwsHdr(rs.getString(COL_RWS_NUM), PROC_STS.ERROR);
                    commit();
                    totalErrCount++;
                } else {
                    // Not Error.
                    if (isNewItem) {
                        // New item.
                        if (mdseUnitId.intValue() > 1) {
                            s21TrxTblAccessor.createIntegrationRecordForBatch(rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID), mdseTrxId);
                        }
                        commit();
                    } else {
                        // Not New item.
                        // Register PO I/F Tables.
                        String procStsCd = PROC_STS.COMPLEATED;
                        if (!registerPo(wmsInbdPoHdrT, wmsInbdPoDtlTList, wmsInbdPoVndT, rs.getString(COL_WMS_PO_DNLD_INTFC_ID), rs.getString(COL_SRC_ORD_NUM))) {
                            rollback();
                            procStsCd = PROC_STS.ERROR;
                        }

                        // Update RWS_HDR status.
                        if (updateRwsHdr(rs.getString(COL_RWS_NUM), procStsCd)) {
                            commit();
                            if (PROC_STS.ERROR.equals(procStsCd)) {
                                totalErrCount++;
                            } else {
                                totalCommitCount++;
                                // If transaction id isn't registered,
                                // register transaction id.
                                if (poUnitId.intValue() > 1) {
                                    s21TrxTblAccessor.createIntegrationRecordForBatch(rs.getString(COL_WMS_PO_DNLD_INTFC_ID), poTrxId);
                                    commit();
                                }
                            }
                        } else {
                            rollback();
                            totalErrCount++;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
            rollback();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Create and Insert WMS_INBD_PO_HDR.
     * @param rs ResultSet
     * @return WMS_INBD_PO_HDRTMsg
     */
    private WMS_INBD_PO_HDRTMsg createWmsInbdPoHdr(ResultSet rs) {

        WMS_INBD_PO_HDRTMsg hdrT = new WMS_INBD_PO_HDRTMsg();
        try {

            // check S80 code value
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_WMS_CMPY_CD))) {
                outputErr(NLAM1001E, new String[] {TBL_CMPY_CD_CONV, COL_GLBL_CMPY_CD, glblCmpyCd});
                return null;
            }
            // QC#19634 Start
            if (ZYPCommonFunc.hasValue(rs.getString(COL_SCE_ORD_TP_CD))) {
                if (!ZYPCommonFunc.hasValue(rs.getString(CONV_SCE_ORD_TP_CD))) {
                    outputErr(NLAM1001E, new String[] {TBL_SCE_ORD_TP, COL_SCE_ORD_TP_CD, rs.getString(COL_SCE_ORD_TP_CD)});
                    return null;
                }
            }
            // QC#19634 End
            ZYPEZDItemValueSetter.setValue(hdrT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(hdrT.whCd, rs.getString(COL_WMS_WH_CD));
            BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
            ZYPEZDItemValueSetter.setValue(hdrT.wmsSqNum, wmsSqNum);
            ZYPEZDItemValueSetter.setValue(hdrT.intfcTpId, VAL_INTFC_TP_ID_01);
            ZYPEZDItemValueSetter.setValue(hdrT.intfcRecTpId, VAL_INTFC_REC_TP_ID_HDR);
            ZYPEZDItemValueSetter.setValue(hdrT.wmsCmpyCd, rs.getString(COL_WMS_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(hdrT.wmsPoId, rs.getString(COL_RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(hdrT.vndCd, rs.getString(COL_FROM_LOC_CD));
            ZYPEZDItemValueSetter.setValue(hdrT.wmsPrchTpCd, rs.getString(COL_WMS_PRCH_TP_CD));
            ZYPEZDItemValueSetter.setValue(hdrT.wmsTrxCd, rs.getString(COL_WMS_TRX_CD));
            // QC#23337
            if (ZYPCommonFunc.hasValue(rs.getString(COL_WH_IN_ETA_DT))) {
                ZYPEZDItemValueSetter.setValue(hdrT.poFromDtTmTs, rs.getString(COL_WH_IN_ETA_DT) + VAL_000000);
            }
            ZYPEZDItemValueSetter.setValue(hdrT.printSwthCd, VAL_PRINT_SWTH_CD_P);
            ZYPEZDItemValueSetter.setValue(hdrT.wmsVeslNm, rs.getString(COL_IMPT_INV_VESL_NM));
            ZYPEZDItemValueSetter.setValue(hdrT.wmsBolNum, rs.getString(COL_IMPT_INV_BOL_NUM));

            RWS_MSGTMsgArray rwsMsgArray = getRwsMsgArray(glblCmpyCd, rs.getString(COL_RWS_NUM));
            if (rwsMsgArray != null && rwsMsgArray.length() > 0) {
                // Repeat the split registration
                for (int i = 0; i < rwsMsgArray.length(); i++) {
                    switch (i) {
                        //QC#18870
                        case 0:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_01, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        case 1:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_02, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        case 2:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_03, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        case 3:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_04, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        default:
                            break;
                    }
                }
            }

            ZYPEZDItemValueSetter.setValue(hdrT.rtlWhCd, rs.getString(COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(hdrT.rwsNum, rs.getString(COL_RWS_NUM));
            ZYPEZDItemValueSetter.setValue(hdrT.svcConfigMstrPk, rs.getBigDecimal(COL_SVC_CONFIG_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(hdrT.stageActCd, VAL_ONE);
            ZYPEZDItemValueSetter.setValue(hdrT.stageRecStsCd, VAL_TWO);

            EZDTBLAccessor.insert(hdrT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdrT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_PO_HDR, TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, rs.getString(COL_RWS_NUM)) });
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
            return null;
        }
        return hdrT;
    }

    /**
     * Create and Insert WMS_INBD_PO_DTL.
     * @param hdrT WMS_INBD_PO_HDRTMsg
     * @return List<WMS_INBD_PO_DTLTMsg>
     */
    private List<WMS_INBD_PO_DTLTMsg> createWmsInbdPoDtl(WMS_INBD_PO_HDRTMsg hdrT) {

        WMS_INBD_PO_DTLTMsg dtlT = new WMS_INBD_PO_DTLTMsg();
        List<WMS_INBD_PO_DTLTMsg> wmsInbdPoDtlTList = new ArrayList<WMS_INBD_PO_DTLTMsg>();

        List<Map<String, Object>> rwsDtlList = getRwsDtl(glblCmpyCd, hdrT.rwsNum.getValue());
        if (rwsDtlList != null) {
            for (Map<String, Object> rwsDtl : rwsDtlList) {

                // QC#19634 Start
                // check S80 code value
                if (ZYPCommonFunc.hasValue((String) rwsDtl.get(COL_INVTY_STK_STS_CD))) {
                    if (!ZYPCommonFunc.hasValue((String) rwsDtl.get(CONV_STK_STS_CD))) {
                        outputErr(NLAM1001E, new String[] {TBL_STS_STK_CONV, COL_INVTY_STK_STS_CD, (String) rwsDtl.get(COL_INVTY_STK_STS_CD)});
                        return null;
                    }
                }
                // QC#19634 End
                ZYPEZDItemValueSetter.setValue(dtlT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dtlT.whCd, hdrT.whCd.getValue());
                ZYPEZDItemValueSetter.setValue(dtlT.wmsSqNum, hdrT.wmsSqNum.getValue());
                ZYPEZDItemValueSetter.setValue(dtlT.wmsLineNum, getNum((String) rwsDtl.get(COL_RWS_DTL_LINE_NUM)));
                ZYPEZDItemValueSetter.setValue(dtlT.intfcTpId, VAL_INTFC_TP_ID_01);
                ZYPEZDItemValueSetter.setValue(dtlT.intfcRecTpId, VAL_INTFC_REC_TP_ID_DTL);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(dtlT.wmsPoId, hdrT.wmsPoId.getValue());
                ZYPEZDItemValueSetter.setValue(dtlT.wmsMdseCd, (String) rwsDtl.get(COL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(dtlT.s80StkStsCd, (String) rwsDtl.get(COL_S80_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsInvInd, VAL_WMS_INV_IND);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsOpenQty, (BigDecimal) rwsDtl.get(COL_RWS_QTY));
                // QC#23337
                if (ZYPCommonFunc.hasValue((String) rwsDtl.get(COL_WH_IN_ETA_DT))) {
                    ZYPEZDItemValueSetter.setValue(dtlT.wmsEstDtTmTs, (String) rwsDtl.get(COL_WH_IN_ETA_DT) + VAL_000000);
                }
                ZYPEZDItemValueSetter.setValue(dtlT.wmsInvId, (String) rwsDtl.get(COL_IMPT_INV_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsDoId, (String) rwsDtl.get(COL_IMPT_INV_DO_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.cseFromNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_FROM_CSE_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.cseToNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_TO_CSE_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, hdrT.rtlWhCd.getValue());
                ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) rwsDtl.get(COL_RTL_SWH_CD));

                String thirdPtyDspTpCd = (String) rwsDtl.get(COL_THIRD_PTY_DSP_TP_CD);
                String thirdPtyDspTpDescTxt = "";
                if (ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
                    thirdPtyDspTpDescTxt = ZYPCodeDataUtil.getName(THIRD_PTY_DSP_TP.class, glblCmpyCd, thirdPtyDspTpCd); 
                } else {
                    if (createSetThirdPtyDspTpDescTxt != null && Arrays.asList(createSetThirdPtyDspTpDescTxt).contains(hdrT.wmsPrchTpCd.getValue())) {
                        if ("U00".equals(dtlT.rtlSwhCd.getValue())) {
                            thirdPtyDspTpCd = ZYPCodeDataUtil.getVarCharConstValue("RTRN_U00_DEF_DSP_TP_CD", glblCmpyCd);
                            thirdPtyDspTpDescTxt = ZYPCodeDataUtil.getName(THIRD_PTY_DSP_TP.class, glblCmpyCd, thirdPtyDspTpCd);
                        } else if ("SUP".equals(dtlT.rtlSwhCd.getValue())) {
                            thirdPtyDspTpCd = ZYPCodeDataUtil.getVarCharConstValue("RTRN_SUP_DEF_DSP_TP_CD", glblCmpyCd);
                            thirdPtyDspTpDescTxt = ZYPCodeDataUtil.getName(THIRD_PTY_DSP_TP.class, glblCmpyCd, thirdPtyDspTpCd);
                        }
                    }
                }
                ZYPEZDItemValueSetter.setValue(dtlT.usrCdRefTxt, thirdPtyDspTpDescTxt);

                ZYPEZDItemValueSetter.setValue(dtlT.serApvlReqFlg, (String) rwsDtl.get(COL_SER_APVL_REQ_FLG));

                EZDTBLAccessor.insert(dtlT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlT.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_PO_DTL, TBL_RWS_HDR //
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, hdrT.rwsNum.getValue()) });
                }

                WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT = (WMS_INBD_PO_DTLTMsg) dtlT.clone();
                wmsInbdPoDtlTList.add(wmsInbdPoDtlT);

            }
        }

        return wmsInbdPoDtlTList;
    }

    /**
     * Create and Insert WMS_INBD_PO_VND.
     * @param rs ResultSet
     * @param hdrT WMS_INBD_PO_HDRTMsg
     * @return WMS_INBD_PO_VNDTMsg
     */
    private WMS_INBD_PO_VNDTMsg createWmsInbdPoVnd(ResultSet rs, WMS_INBD_PO_HDRTMsg hdrT) {

        WMS_INBD_PO_VNDTMsg vndT = new WMS_INBD_PO_VNDTMsg();

        try {

            ZYPEZDItemValueSetter.setValue(vndT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(vndT.whCd, hdrT.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(vndT.wmsSqNum, hdrT.wmsSqNum.getValue());
            ZYPEZDItemValueSetter.setValue(vndT.intfcTpId, VAL_INTFC_TP_ID_01);
            ZYPEZDItemValueSetter.setValue(vndT.intfcRecTpId, VAL_INTFC_REC_TP_ID_VND);
            ZYPEZDItemValueSetter.setValue(vndT.wmsCmpyCd, rs.getString(COL_WMS_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(vndT.wmsPoId, rs.getString(COL_RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(vndT.vndCd, rs.getString(COL_FROM_LOC_CD));
            ZYPEZDItemValueSetter.setValue(vndT.wmsVndNm_01, adjustString(rs.getString(COL_FROM_LOC_NM_01), 35));
            ZYPEZDItemValueSetter.setValue(vndT.wmsVndNm_02, adjustString(rs.getString(COL_FROM_LOC_NM_02), 35));
            ZYPEZDItemValueSetter.setValue(vndT.firstLineAddr, rs.getString(COL_FROM_LOC_ADDR_01));
            ZYPEZDItemValueSetter.setValue(vndT.scdLineAddr, rs.getString(COL_FROM_LOC_ADDR_02));
            ZYPEZDItemValueSetter.setValue(vndT.thirdLineAddr, rs.getString(COL_FROM_LOC_ADDR_03));
            ZYPEZDItemValueSetter.setValue(vndT.frthLineAddr, rs.getString(COL_FROM_LOC_ADDR_04));
            ZYPEZDItemValueSetter.setValue(vndT.ctyAddr, rs.getString(COL_FROM_LOC_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(vndT.stCd, rs.getString(COL_FROM_LOC_ST_CD));
            ZYPEZDItemValueSetter.setValue(vndT.postCd, rs.getString(COL_FROM_LOC_POST_CD));
            ZYPEZDItemValueSetter.setValue(vndT.ctryCd, rs.getString(COL_FROM_LOC_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(vndT.vndToCtacNm, rs.getString(COL_FROM_LOC_PSN_NM));
            ZYPEZDItemValueSetter.setValue(vndT.vndToCtacNum, rs.getString(COL_FROM_LOC_TEL_NUM));

            EZDTBLAccessor.insert(vndT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_PO_VND, TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, rs.getString(COL_RWS_NUM)) });
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
            return null;
        }
        return vndT;
    }

    /**
     * Register PO I/F Tables.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param wmsInbdPoDtlTList WMS_INBD_PO_DTLTMsg List
     * @param wmsInbdPoVndT WMS_INBD_PO_VNDTMsg
     * @param wmsPoDnldIntfcId Interface ID
     * @return isSuccess
     */
    private boolean registerPo(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, List<WMS_INBD_PO_DTLTMsg> wmsInbdPoDtlTList, WMS_INBD_PO_VNDTMsg wmsInbdPoVndT, String wmsPoDnldIntfcId, String srcOrdNum) {

        if (!ZYPCommonFunc.hasValue(poTrxId)) {
            poTrxId = s21TrxTblAccessor.getNextTransactionId();
        }

        BigDecimal poSeqNumber = BigDecimal.ZERO;
        // Register PO IF data.
        poSeqNumber = createPoIf01(wmsInbdPoHdrT, wmsInbdPoVndT, poSeqNumber, wmsPoDnldIntfcId, srcOrdNum);
        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
            return false;
        }
        poSeqNumber = createPoIf04(wmsInbdPoHdrT, wmsInbdPoDtlTList, poSeqNumber, wmsPoDnldIntfcId);
        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
            return false;
        }
        poSeqNumber = createPoIf02(wmsInbdPoHdrT, poSeqNumber, wmsPoDnldIntfcId);
        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
            return false;
        }
        poSeqNumber = createPoIf03(wmsInbdPoHdrT, poSeqNumber, wmsPoDnldIntfcId);
        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
            return false;
        }
        // QC#25002 T.hakodate MOD START
        if (ZYPCommonFunc.hasValue(wmsInbdPoHdrT.rwsNum)) {
            poSeqNumber = createPoIf07(wmsInbdPoHdrT, poSeqNumber, wmsPoDnldIntfcId, wmsInbdPoHdrT.rwsNum.getValue());
        }
        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
            return false;
        }
        // QC#25002 T.hakodate MOD END
        
        
        poUnitId = poUnitId.add(BigDecimal.ONE);
        return true;
    }

    /**
     * Create and Insert NLGI2100_01.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param wmsInbdPoVndT WMS_INBD_PO_VNDTMsg
     * @param poSeqNumber PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     * @return PO_SEQ_NUMBER
     */
    private BigDecimal createPoIf01(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, WMS_INBD_PO_VNDTMsg wmsInbdPoVndT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId, String srcOrdNum) {

        NLGI2100_01TMsg algi210001T = new NLGI2100_01TMsg();
        ZYPEZDItemValueSetter.setValue(algi210001T.interfaceId, wmsPoDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(algi210001T.transactionId, poTrxId);
        ZYPEZDItemValueSetter.setValue(algi210001T.segmentId, VAL_SEGMENT_ID_1);
        ZYPEZDItemValueSetter.setValue(algi210001T.unitId, poUnitId);
        poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(algi210001T.seqNumber, poSeqNumber);
        ZYPEZDItemValueSetter.setValue(algi210001T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_01);
        ZYPEZDItemValueSetter.setValue(algi210001T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
        ZYPEZDItemValueSetter.setValue(algi210001T.splyCdTxt, wmsInbdPoHdrT.vndCd);
        ZYPEZDItemValueSetter.setValue(algi210001T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(algi210001T.firstGrpCdTxt, wmsInbdPoHdrT.wmsTrxCd);
        ZYPEZDItemValueSetter.setValue(algi210001T.thirdGrpCdTxt, wmsInbdPoHdrT.whCd);
        ZYPEZDItemValueSetter.setValue(algi210001T.reqDtTmTsTxt, dateFormat(wmsInbdPoHdrT.poFromDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000));
        ZYPEZDItemValueSetter.setValue(algi210001T.proBillTxt, wmsInbdPoHdrT.wmsBolNum);
        ZYPEZDItemValueSetter.setValue(algi210001T.splyNmTxt, NLXC014001.nullToEmpty(wmsInbdPoVndT.wmsVndNm_01.getValue()).replaceAll(VAL_APOS, VAL_BLANK));

        ZYPEZDItemValueSetter.setValue(algi210001T.rwsNum, wmsInbdPoHdrT.rwsNum);
        ZYPEZDItemValueSetter.setValue(algi210001T.svcConfigMstrPk, wmsInbdPoHdrT.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(algi210001T.stageActCd, wmsInbdPoHdrT.stageActCd);
        ZYPEZDItemValueSetter.setValue(algi210001T.stageRecStsCd, wmsInbdPoHdrT.stageRecStsCd);

        ZYPEZDItemValueSetter.setValue(algi210001T.inbdSrcOrdNum, srcOrdNum);

        EZDTBLAccessor.insert(algi210001T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210001T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_01, TBL_RWS_HDR //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, algi210001T.ordIdTxt.getValue()) });
        }

        
        // QC#25002 T.hakodate DEL START
        // QC#5141 Add.
//        if (ZYPCommonFunc.hasValue(algi210001T.rwsNum)) {
//            createPoIf07(wmsInbdPoHdrT, poSeqNumber, wmsPoDnldIntfcId, algi210001T.rwsNum.getValue());
//        }
        // QC#25002 T.hakodate DEL START
        return poSeqNumber;
    }

    /**
     * Create and Insert NLGI2100_02.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param poSeqNumber PO_SEQ_NUMBER
     * @return PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     */
    private BigDecimal createPoIf02(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId) {

        if (!ZYPCommonFunc.hasValue(wmsInbdPoHdrT.wmsVeslNm)) {
            return poSeqNumber;
        }

        NLGI2100_02TMsg algi210002T = new NLGI2100_02TMsg();
        ZYPEZDItemValueSetter.setValue(algi210002T.interfaceId, wmsPoDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(algi210002T.transactionId, poTrxId);
        ZYPEZDItemValueSetter.setValue(algi210002T.segmentId, VAL_SEGMENT_ID_1);
        ZYPEZDItemValueSetter.setValue(algi210002T.unitId, poUnitId);
        poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(algi210002T.seqNumber, poSeqNumber);
        ZYPEZDItemValueSetter.setValue(algi210002T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_02);
        ZYPEZDItemValueSetter.setValue(algi210002T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
        ZYPEZDItemValueSetter.setValue(algi210002T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(algi210002T.ordCmntSqTxt, VAL_ORD_CMNT_SQ_TXT_10);
        ZYPEZDItemValueSetter.setValue(algi210002T.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX_VESL + NLXC014001.nullToEmpty(wmsInbdPoHdrT.wmsVeslNm.getValue()).replaceAll(VAL_APOS, VAL_BLANK));

        EZDTBLAccessor.insert(algi210002T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210002T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_02, TBL_RWS_HDR //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, algi210002T.ordIdTxt.getValue()) });
        }
        return poSeqNumber;
    }

    /**
     * Create and Insert NLGI2100_03.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param poSeqNumber PO_SEQ_NUMBER
     * @return PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     */
    private BigDecimal createPoIf03(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId) {

        String ordCmntTxt = (NLXC014001.nullToEmpty(wmsInbdPoHdrT.inbdPoMsgTxt_01.getValue()) + VAL_BLANK + NLXC014001.nullToEmpty(wmsInbdPoHdrT.inbdPoMsgTxt_02.getValue()) + VAL_BLANK //
                + NLXC014001.nullToEmpty(wmsInbdPoHdrT.inbdPoMsgTxt_03.getValue()) + VAL_BLANK + NLXC014001.nullToEmpty(wmsInbdPoHdrT.inbdPoMsgTxt_04.getValue())).replaceAll("\\s{2,}", VAL_BLANK).trim();
        if (!ZYPCommonFunc.hasValue(ordCmntTxt)) {
            return poSeqNumber;
        }

        int count = 1;
        while (ZYPCommonFunc.hasValue(ordCmntTxt)) {
            NLGI2100_03TMsg algi210003T = new NLGI2100_03TMsg();
            ZYPEZDItemValueSetter.setValue(algi210003T.interfaceId, wmsPoDnldIntfcId);
            ZYPEZDItemValueSetter.setValue(algi210003T.transactionId, poTrxId);
            ZYPEZDItemValueSetter.setValue(algi210003T.segmentId, VAL_SEGMENT_ID_1);
            ZYPEZDItemValueSetter.setValue(algi210003T.unitId, poUnitId);
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(algi210003T.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(algi210003T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_03);
            ZYPEZDItemValueSetter.setValue(algi210003T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
            ZYPEZDItemValueSetter.setValue(algi210003T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));
            //ZYPEZDItemValueSetter.setValue(algi210003T.ordLineTxt, VAL_ORD_LINE_TXT_1);

            String tmp = "";
            if (ordCmntTxt.length() > VAL_ORD_CMNT_TXT_SIZE) {
                tmp = ordCmntTxt.substring(0, VAL_ORD_CMNT_TXT_SIZE);
                ordCmntTxt = ordCmntTxt.substring(VAL_ORD_CMNT_TXT_SIZE);
            } else {
                tmp = ordCmntTxt;
                ordCmntTxt = "";
            }
            ZYPEZDItemValueSetter.setValue(algi210003T.ordCmntSqTxt, String.valueOf(count++));
            ZYPEZDItemValueSetter.setValue(algi210003T.ordCmntTxt, tmp.replaceAll(VAL_APOS, VAL_BLANK));

            EZDTBLAccessor.insert(algi210003T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210003T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_03, TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, algi210003T.ordIdTxt.getValue()) });
            }
        }
        return poSeqNumber;
    }

    private BigDecimal createPoIf04(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT, BigDecimal poSeqNumber, 
            String wmsPoDnldIntfcId, BigDecimal wmsOrdLineNum, BigDecimal wmsQtyOrd, String mtrMachFlg) {
        NLGI2100_04TMsg algi210004T = new NLGI2100_04TMsg();
        ZYPEZDItemValueSetter.setValue(algi210004T.interfaceId, wmsPoDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(algi210004T.transactionId, poTrxId);
        ZYPEZDItemValueSetter.setValue(algi210004T.segmentId, VAL_SEGMENT_ID_1);
        ZYPEZDItemValueSetter.setValue(algi210004T.unitId, poUnitId);
        poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(algi210004T.seqNumber, poSeqNumber);
        ZYPEZDItemValueSetter.setValue(algi210004T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_04);
        ZYPEZDItemValueSetter.setValue(algi210004T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
        ZYPEZDItemValueSetter.setValue(algi210004T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));

        // Line
//        ZYPEZDItemValueSetter.setValue(algi210004T.ordLineTxt, NLXC014001.nullToZero(wmsInbdPoDtlT.wmsLineNum.getValue()).multiply(VAL_ORD_LINE_TXT_MUL).toString());
        ZYPEZDItemValueSetter.setValue(algi210004T.ordLineTxt, NLXC014001.nullToZero(wmsOrdLineNum).multiply(VAL_ORD_LINE_TXT_MUL).toString());

        ZYPEZDItemValueSetter.setValue(algi210004T.itemCdTxt, wmsInbdPoDtlT.wmsMdseCd);

        // Qty
//        ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(wmsInbdPoDtlT.wmsOpenQty.getValue(), FMT_0));
        ZYPEZDItemValueSetter.setValue(algi210004T.qtyOrdTxt, decimalFormat(wmsQtyOrd, FMT_0));

        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.s80StkStsCd) && !STK_STS.GOOD.equals(wmsInbdPoDtlT.s80StkStsCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO))) {
            ZYPEZDItemValueSetter.setValue(algi210004T.hldCdTxt, wmsInbdPoDtlT.s80StkStsCd.getValue().substring(LG_CUT_STK_STS_CD_FROM, LG_CUT_STK_STS_CD_TO));
        }
        ZYPEZDItemValueSetter.setValue(algi210004T.packCdTxt, getPackageCode(wmsInbdPoHdrT.rtlWhCd.getValue(), wmsInbdPoDtlT.rtlSwhCd.getValue()));
        ZYPEZDItemValueSetter.setValue(algi210004T.usrCdRefTxt, wmsInbdPoDtlT.usrCdRefTxt);
        ZYPEZDItemValueSetter.setValue(algi210004T.serApvlReqFlg, wmsInbdPoDtlT.serApvlReqFlg);

        // QC#15629
        ZYPEZDItemValueSetter.setValue(algi210004T.mtrMachFlg, mtrMachFlg);

        EZDTBLAccessor.insert(algi210004T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210004T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_04, TBL_RWS_HDR //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, algi210004T.ordIdTxt.getValue()) });
        }

        poSeqNumber = createPoIf05(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId, wmsOrdLineNum);
        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
            return null;
        }

//        poSeqNumber = createPoIf06(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId);
//        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
//            return null;
//        }
        return poSeqNumber;
    }
    /**
     * Create and Insert NLGI2100_04. Call create NLGI2100_05 proc.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param wmsInbdPoDtlTList WMS_INBD_PO_DTLTMsg List
     * @param poSeqNumber PO_SEQ_NUMBER
     * @return PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     */
    private BigDecimal createPoIf04(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, List<WMS_INBD_PO_DTLTMsg> wmsInbdPoDtlTList, BigDecimal poSeqNumber, String wmsPoDnldIntfcId) {

        //  Line Num TO WMS
        int wmsOrdLineNum = 0;
        //  Qty TO WMS
        int wmsQtyOrd = 0;

        // RWS Num
        String rwsNum = wmsInbdPoHdrT.rwsNum.getValue();

        // Serial specification to check whether there at RWS units at the beginning 
        boolean extSerSpec = isExistsSerialSpec(rwsNum);


        for (WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT : wmsInbdPoDtlTList) {
            // Original Line Num
            BigDecimal orgOrdLineNum = wmsInbdPoDtlT.wmsLineNum.getValue();
            // Original Qty
            BigDecimal orgQtyOrd = wmsInbdPoDtlT.wmsOpenQty.getValue(); 

            // Meter Machine Flag Add QC#15629
            String mtrMachFlg = ZYPConstant.FLG_OFF_N;

            // To check whether a serial specified
            String rwsLineNum = ZYPCommonFunc.leftPad(String.valueOf(orgOrdLineNum), 3, "0");
            RWS_SERTMsgArray rwsSerArr = getRwsSerArray(glblCmpyCd, wmsInbdPoHdrT.rwsNum.getValue(), rwsLineNum);

            if (rwsSerArr != null && rwsSerArr.length() > 0) {
                // This is serial specified count
                int serTakeCnt = rwsSerArr.length();

                // Repeat the split registration
                for (int i = 0; i < serTakeCnt; i++) {
                    wmsOrdLineNum++;
                    wmsQtyOrd = 1;

                    // QC#15629
                    String serNum = null;
                    if (ZYPCommonFunc.hasValue(rwsSerArr.no(i).serNum)) {
                        serNum = rwsSerArr.no(i).serNum.getValue();
                    }

                    // Create PoIf04
                    mtrMachFlg = getMtrMachFlg(glblCmpyCd, serNum);
                    poSeqNumber = createPoIf04(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), mtrMachFlg);

                    // Create PoIf06
                    poSeqNumber = createPoIf06(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId, i, serNum, new BigDecimal(wmsOrdLineNum));

                    // Create WMS_RWS_ORIG_LINE_SAVE
                    createWmsRwsSave(rwsNum, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), orgOrdLineNum);

                }
                // If the serial specified count and quantity are different
                if (orgQtyOrd.intValue() != serTakeCnt) {
                    // Register Together
                    wmsOrdLineNum++;
                    wmsQtyOrd = orgQtyOrd.intValue() - serTakeCnt;
                    poSeqNumber = createPoIf04(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), mtrMachFlg);

                    // Create WMS_RWS_ORIG_LINE_SAVE
                    createWmsRwsSave(rwsNum, new BigDecimal(wmsOrdLineNum), new BigDecimal(wmsQtyOrd), orgOrdLineNum);
                }
            } else {
                if(extSerSpec) {
                    wmsOrdLineNum++;
                    // Create PoIf04
                    poSeqNumber = createPoIf04(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId, new BigDecimal(wmsOrdLineNum), orgQtyOrd, mtrMachFlg);
                    // Create WMS_RWS_ORIG_LINE_SAVE
                    createWmsRwsSave(rwsNum, new BigDecimal(wmsOrdLineNum), orgQtyOrd, orgOrdLineNum);
                } else {
                    // Create PoIf04 (The serial specified does not exist, not registration To WMS_RWS_ORIG_LINE_SAVE)
                    poSeqNumber = createPoIf04(wmsInbdPoHdrT, wmsInbdPoDtlT, poSeqNumber, wmsPoDnldIntfcId, orgOrdLineNum, orgQtyOrd, mtrMachFlg);
                }
            }

        }
        return poSeqNumber;
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
//        ZYPEZDItemValueSetter.setValue(tMsg.rwsToWmsDtlLineNum, wmsOrdLineNum.toPlainString());
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
//        ZYPEZDItemValueSetter.setValue(tMsg.rwsOrigDtlLineNum, orgOrdLineNum.toPlainString());
        ZYPEZDItemValueSetter.setValue(tMsg.rwsOrigDtlLineNum, NLXC014001.nullToZero(orgOrdLineNum).multiply(VAL_ORD_LINE_TXT_MUL).toPlainString());

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_RWS_ORIG_LINE_SAVE, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM, COL_RWS_TO_WMS_DTL_LINE_NUM) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, rwsNum, wmsOrdLineNum.toPlainString()) });
        }
    }

    /**
     * Create and Insert NLGI2100_05.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param wmsInbdPoDtlT WMS_INBD_PO_DTLTMsg
     * @param poSeqNumber PO_SEQ_NUMBER
     * @return PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     */
    private BigDecimal createPoIf05(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId, BigDecimal ordLine) {

        NLGI2100_05TMsg algi210005T = new NLGI2100_05TMsg();
        ZYPEZDItemValueSetter.setValue(algi210005T.interfaceId, wmsPoDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(algi210005T.transactionId, poTrxId);
        ZYPEZDItemValueSetter.setValue(algi210005T.segmentId, VAL_SEGMENT_ID_1);
        ZYPEZDItemValueSetter.setValue(algi210005T.unitId, poUnitId);
        ZYPEZDItemValueSetter.setValue(algi210005T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_05);
        ZYPEZDItemValueSetter.setValue(algi210005T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
        ZYPEZDItemValueSetter.setValue(algi210005T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));
//        ZYPEZDItemValueSetter.setValue(algi210005T.ordLineTxt, NLXC014001.nullToZero(wmsInbdPoDtlT.wmsLineNum.getValue()).multiply(VAL_ORD_LINE_TXT_MUL).toString());
        ZYPEZDItemValueSetter.setValue(algi210005T.ordLineTxt, NLXC014001.nullToZero(ordLine).multiply(VAL_ORD_LINE_TXT_MUL).toString());

        // For WMS_DESC_TXT.
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.wmsDescTxt)) {
            int count = 1;
            String ordCmntTxt = wmsInbdPoDtlT.wmsDescTxt.getValue();
            while (ZYPCommonFunc.hasValue(ordCmntTxt)) {
                NLGI2100_05TMsg tmpT = (NLGI2100_05TMsg) algi210005T.clone();
                poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(tmpT.seqNumber, poSeqNumber);
                String tmp = "";
                if (ordCmntTxt.length() > VAL_ORD_CMNT_TXT_SIZE) {
                    tmp = ordCmntTxt.substring(0, VAL_ORD_CMNT_TXT_SIZE);
                    ordCmntTxt = ordCmntTxt.substring(VAL_ORD_CMNT_TXT_SIZE);
                } else {
                    tmp = ordCmntTxt;
                    ordCmntTxt = "";
                }
                ZYPEZDItemValueSetter.setValue(tmpT.ordCmntSqTxt, String.valueOf(count++));
                ZYPEZDItemValueSetter.setValue(tmpT.ordCmntTxt, tmp.replaceAll(VAL_APOS, VAL_BLANK));

                EZDTBLAccessor.insert(tmpT);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmpT.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_05, TBL_RWS_HDR //
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, tmpT.ordIdTxt.getValue()) });
                }
            }
        }

        // For WMS_INV_ID.
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.wmsInvId)) {
            NLGI2100_05TMsg tmpT = (NLGI2100_05TMsg) algi210005T.clone();
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tmpT.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(tmpT.ordCmntSqTxt, VAL_ORD_CMNT_SQ_TXT_INV);
            ZYPEZDItemValueSetter.setValue(tmpT.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX_INV + NLXC014001.nullToEmpty(wmsInbdPoDtlT.wmsInvId.getValue()).replaceAll(VAL_APOS, VAL_BLANK));

            EZDTBLAccessor.insert(tmpT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmpT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_05, TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, tmpT.ordIdTxt.getValue()) });
            }
        }

        // For WMS_DO_ID.
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.wmsDoId)) {
            NLGI2100_05TMsg tmpT = (NLGI2100_05TMsg) algi210005T.clone();
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tmpT.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(tmpT.ordCmntSqTxt, VAL_ORD_CMNT_SQ_TXT_DO);
            ZYPEZDItemValueSetter.setValue(tmpT.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX_DO + NLXC014001.nullToEmpty(wmsInbdPoDtlT.wmsDoId.getValue()).replaceAll(VAL_APOS, VAL_BLANK));

            EZDTBLAccessor.insert(tmpT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmpT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_05, TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, tmpT.ordIdTxt.getValue()) });
            }
        }

        // For CSE_FROM_NUM.
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.cseFromNum) && wmsInbdPoDtlT.cseFromNum.getValueInt() > 0) {
            NLGI2100_05TMsg tmpT = (NLGI2100_05TMsg) algi210005T.clone();
            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tmpT.seqNumber, poSeqNumber);
            ZYPEZDItemValueSetter.setValue(tmpT.ordCmntSqTxt, VAL_ORD_CMNT_SQ_TXT_CSE);
            ZYPEZDItemValueSetter.setValue(tmpT.ordCmntTxt, VAL_ORD_CMNT_TXT_PFX_CSE + wmsInbdPoDtlT.cseFromNum.getValue().toString() + VAL_ORD_CMNT_TXT_CON_CSE + wmsInbdPoDtlT.cseToNum.getValue().toString());

            EZDTBLAccessor.insert(tmpT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmpT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_05, TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, tmpT.ordIdTxt.getValue()) });
            }
        }

        return poSeqNumber;
    }
    /**
     * Create and Insert NLGI2100_06. Call create NLGI2100_06 process.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param wmsInbdPoDtlTList WMS_INBD_PO_DTLTMsg List
     * @param poSeqNumber PO_SEQ_NUMBER
     * @return PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     */
    private BigDecimal createPoIf06(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId, 
            int serSqTxt, String serNum, BigDecimal wmsOrdLineNum) {
        NLGI2100_06TMsg algi210006T = new NLGI2100_06TMsg();
        ZYPEZDItemValueSetter.setValue(algi210006T.interfaceId, wmsPoDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(algi210006T.transactionId, poTrxId);
        ZYPEZDItemValueSetter.setValue(algi210006T.segmentId, VAL_SEGMENT_ID_1);
        ZYPEZDItemValueSetter.setValue(algi210006T.unitId, poUnitId);
        poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(algi210006T.seqNumber, poSeqNumber);
        ZYPEZDItemValueSetter.setValue(algi210006T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_06);
        ZYPEZDItemValueSetter.setValue(algi210006T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
        ZYPEZDItemValueSetter.setValue(algi210006T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));

        // ZYPEZDItemValueSetter.setValue(algi210006T.ordLineTxt,
        // NLXC014001.nullToZero(wmsInbdPoDtlT.wmsLineNum.getValue()).multiply(VAL_ORD_LINE_TXT_MUL).toString());
//        ZYPEZDItemValueSetter.setValue(algi210006T.ordLineTxt, NLXC014001.nullToZero(wmsInbdPoDtlT.wmsLineNum.getValue()).multiply(VAL_ORD_LINE_TXT_MUL).toString());
        ZYPEZDItemValueSetter.setValue(algi210006T.ordLineTxt, NLXC014001.nullToZero(wmsOrdLineNum).multiply(VAL_ORD_LINE_TXT_MUL).toString());

        ZYPEZDItemValueSetter.setValue(algi210006T.serSqTxt, ZYPCommonFunc.leftPad(String.valueOf(serSqTxt + 1), 8, "0"));
        // ZYPEZDItemValueSetter.setValue(algi210006T.serNum,
        // rwsSerArr.no(i).serNum);
        ZYPEZDItemValueSetter.setValue(algi210006T.serNum, serNum);

        EZDTBLAccessor.insert(algi210006T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210006T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_06, TBL_RWS_HDR //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, algi210006T.ordIdTxt.getValue()) });
        }
        return poSeqNumber;

    }
    /**
     * Update PROC_STS_CD of RWS_HDR.
     * @param pkList RWS_HDR_PK List
     * @param procStsCd Proc Status Code
     * @return true / success, false / error
     */
    private boolean updateRwsHdr(String rwsNum, String procStsCd) {

        // Update PROC_STS_CD of RWS_HDR table.
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(rwsHdrT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsNum, rwsNum);

            rwsHdrT = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(rwsHdrT);
            if (rwsHdrT == null) {
                throw new S21AbendException(NLGM0044E, new String[] {TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, rwsNum) });
            }
            rwsHdrT.wmsDropStsCd.setValue(procStsCd);

            EZDTBLAccessor.update(rwsHdrT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrT.getReturnCode())) {
                throw new S21AbendException(NLGM0046E, new String[] {TBL_RWS_HDR //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, rwsNum) });
            }
        return true;
    }

    /**
     * New Item Download Process. Receive result set as parameter.
     * Call create WMS_INBD_MDSE tables process and Insert WMS_MDSE_LIST.
     * @param rwsNum String
     * @param wmsWhCd String
     * @param wmsItemDnldIntfcId String
     * @return isSuccess
     */
    private boolean doNewItemDnldProc(String rwsNum, String wmsWhCd, String wmsItemDnldIntfcId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("rwsNum", rwsNum);
            queryParam.put("wmsWhCd", wmsWhCd);
            queryParam.put("wmsItemDnldIntfcId", wmsItemDnldIntfcId);

            stmt = ssmLLClient.createPreparedStatement("getNewItem", queryParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!doCreateWmsInbdMdseProc(rs, true)) {
                    return false;
                }

                // Insert new data of WMS_MDSE_LIST.
                boolean insertWmsMdseListTbl = insertWmsMdseList(wmsWhCd, rs.getString(NLGC002001Constant.COL_WML_MDSE_CD), rs.getString(NLGC002001Constant.COL_WML_EZUPTIME));
                if (!insertWmsMdseListTbl) {
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
            return false;
        }
        return true;
    }

    /**
     * Force Item Download Process. Call create WMS_INBD_MDSE tables
     * process and Update WMS_MDSE_LIST.
     */
    private void doFrceItemDnldProc() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            queryParam.put("wmsItemDnldStsCd", WMS_ITEM_DNLD_STS.IN_COMPLETED);
            stmt = ssmLLClient.createPreparedStatement("getFrceItemList", queryParam);
            rs = stmt.executeQuery();

            boolean isRegistMdseTrxId = false;
            String prevWhCd = "";

            while (rs.next()) {
                String whCd = rs.getString(NLGC002001Constant.COL_WML_WH_CD);
                String mdseCd = rs.getString(NLGC002001Constant.COL_WML_MDSE_CD);
                //String ezuptime = rs.getString(NLGC002001Constant.COL_WML_EZUPTIME);
                String wmsItemDnldIntfcId = rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID);
                String sysDate = ZYPDateUtil.getSalesDate() + ZYPDateUtil.getCurrentSystemTime(FMT_HHMMSSSSS);
                boolean createWmsInbdMdse = true;
                boolean isFirstItemDnld = true;
                String updateWmsMdseErrMsgCd = null;

                // Key break by warehouse.
                if (!prevWhCd.equals(whCd)) {
                    // If warehouse is changed, reset data below.
                    mdseTrxId = null;
                    mdseUnitId = BigDecimal.ONE;
                    isRegistMdseTrxId = false;
                }
                prevWhCd = whCd;

                if (ZYPCommonFunc.hasValue(rs.getString(COL_LAST_INTFC_MDSE_UPD_TS))) {
                    isFirstItemDnld = false;
                }

                createWmsInbdMdse = doCreateWmsInbdMdseProc(rs, isFirstItemDnld);
                if (createWmsInbdMdse) {
                    updateWmsMdseErrMsgCd = NLGC002001.updateWmsMdseList(glblCmpyCd, whCd, mdseCd, WMS_ITEM_DNLD_STS.COMPLEATED, sysDate);
                } else {
                    rollback();
                    updateWmsMdseErrMsgCd = NLGC002001.updateWmsMdseList(glblCmpyCd, whCd, mdseCd, WMS_ITEM_DNLD_STS.ERROR);
                }

                if (!ZYPCommonFunc.hasValue(updateWmsMdseErrMsgCd)) {
                    // commit
                    commit();
                    if (createWmsInbdMdse) {
                        totalCommitCount++;

                        // If transaction id isn't registered,
                        // register transaction id.
                        if (!isRegistMdseTrxId && mdseUnitId.intValue() > 1) {
                            s21TrxTblAccessor.createIntegrationRecordForBatch(wmsItemDnldIntfcId, mdseTrxId);
                            isRegistMdseTrxId = true;
                            commit();
                        }
                    } else {
                        totalErrCount++;
                    }
                } else {
                    // rollback
                    rollback();
                    totalErrCount++;

                    if (NLGM0044E.equals(updateWmsMdseErrMsgCd)) {
                        throw new S21AbendException(NLGM0044E, new String[] {TBL_WMS_MDSE_LIST //
                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_MDSE_CD) //
                                , NLXCMsgHelper.toListedString(glblCmpyCd, whCd, mdseCd) });
                    } else if (NLGM0046E.equals(updateWmsMdseErrMsgCd)) {
                        throw new S21AbendException(NLGM0046E, new String[] {TBL_WMS_MDSE_LIST //
                                , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_MDSE_CD) //
                                , NLXCMsgHelper.toListedString(glblCmpyCd, whCd, mdseCd) });
                    }
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Create temporary data of WMS_INBD_ITEM_WRK,
     * WMS_INBD_ITEM_UPC_WRK, WMS_INBD_ITEM_SER_WRK. Create and Insert
     * WMS_INBD_MDSE, WMS_INBD_MDSE_UPC, WMS_INBD_MDSE_SER. Call MDSE
     * I/F create proc.
     * @param rs ResultSet
     * @param isFirstItemDnld Is First Item download
     * @return isSuccess
     */
    private boolean doCreateWmsInbdMdseProc(ResultSet rs, boolean isFirstItemDnld) {

        try {
            WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT = null;
            List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList = new ArrayList<WMS_INBD_ITEM_UPC_WRKTMsg>();
            List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList = new ArrayList<WMS_INBD_ITEM_SER_WRKTMsg>();

            String whCd = rs.getString(NLGC002001Constant.COL_WML_WH_CD);
            String mdseCd = rs.getString(NLGC002001Constant.COL_WML_MDSE_CD);
            String wmsItemDnldIntfcId = rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID);

            // Data check
            if (!ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_M_MDSE_CD))) {
                outputErr(NLGM0044E, new String[] {NLGC002001Constant.TBL_MDSE, NLGC002001Constant.COL_MDSE_CD, mdseCd });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_IPC_INTG_PROD_CATG_CD))) {
                outputErr(NLGM0044E, new String[] {NLGC002001Constant.TBL_INTG_PROD_CATG, NLGC002001Constant.COL_INTG_PROD_CATG_CD, rs.getString(NLGC002001Constant.COL_IPC_INTG_PROD_CATG_CD) });
                return false;
            }
            if (ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_M_FRT_CLS_CD)) && !ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_FC_FRT_CLS_CD))) {
                outputErr(NLGM0044E, new String[] {NLGC002001Constant.TBL_FRT_CLS, NLGC002001Constant.COL_FRT_CLS_CD, rs.getString(NLGC002001Constant.COL_M_FRT_CLS_CD) });
                return false;
            }

            // Call NLGC002001.createWmsInbdItemInfo method.
            // Then get WMS_INBD_ITEM_WRKTMsg,
            // WMS_INBD_ITEM_UPC_WRKTMsg List,
            // WMS_INBD_ITEM_SER_WRKTMsg List.
            String intfcRecActCd = NLGC002001.VAL_INTFC_REC_ACT_CD_CHNG;
            if (isFirstItemDnld) {
                intfcRecActCd = NLGC002001.VAL_INTFC_REC_ACT_CD_ADD;
            }
            NLGC002001 algc002001 = new NLGC002001(glblCmpyCd);
            wmsInbdItemWrkT = algc002001.createWmsInbdItemInfo(rs, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, intfcRecActCd);

            setWmsInbdItemData(wmsInbdItemWrkT, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, whCd);

            // Create WMS_INBD_MDSE, WMS_INBD_MDSE_UPC,
            // WMS_INBD_MDSE_SER.
            WMS_INBD_MDSETMsg wmsInbdMdseT = createWmsInbdMdse(wmsInbdItemWrkT);
            if (wmsInbdMdseT == null) {
                return false;
            }
            List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList = createWmsInbdMdseUpc(wmsInbdItemUpcWrkList);
            if (wmsInbdMdseUpcTList == null) {
                return false;
            }
            List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList = createWmsInbdMdseSer(wmsInbdItemSerWrkList);
            if (wmsInbdMdseSerTList == null) {
                return false;
            }

            return registerMdse(wmsInbdMdseT, wmsInbdMdseUpcTList, wmsInbdMdseSerTList, wmsItemDnldIntfcId, true);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
            return false;
        }
    }

    /**
     * Item Download Process. Create and Insert WMS_INBD_MDSE Tables,
     * MDSE I/F Table. Update WMS_MDSE_LIST.
     */
    private void doItemDnldProc() {

        doTrxItemDnldProc();

        // Force Item Download.
        doFrceItemDnldProc();

        // Get WMS_INBD_ITEM_WRK
        List wmsInbdItemWrkList = getWmsInbdItemWrkList();
        boolean isRegistMdseTrxId = false;
        String prevWhCd = "";

        if (wmsInbdItemWrkList != null) {
            for (int i = 0; i < wmsInbdItemWrkList.size(); i++) {
                WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT = (WMS_INBD_ITEM_WRKTMsg) wmsInbdItemWrkList.get(i);
                List wmsInbdItemUpcWrkList = getWmsInbdItemUpcWrkList(wmsInbdItemWrkT.whCd.getValue(), wmsInbdItemWrkT.wmsSqNum.getValue());
                List wmsInbdItemSerWrkList = getWmsInbdItemSerWrkList(wmsInbdItemWrkT.whCd.getValue(), wmsInbdItemWrkT.wmsSqNum.getValue());

                WMS_INBD_MDSETMsg wmsInbdMdseT = null;
                List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList = null;
                List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList = null;
                boolean isRegistMdse = false;
                boolean isUpdateWmsInbdItemWrkTbl = false;

                // Get WMS_WH
                WMS_WHTMsg wmsWhT = new WMS_WHTMsg();
                ZYPEZDItemValueSetter.setValue(wmsWhT.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wmsWhT.whCd, wmsInbdItemWrkT.whCd);
                wmsWhT = (WMS_WHTMsg) EZDTBLAccessor.findByKey(wmsWhT);
                String wmsItemDnldIntfcId = wmsWhT.wmsItemDnldIntfcId.getValue();

                // Key break by warehouse.
                if (!prevWhCd.equals(wmsInbdItemWrkT.whCd.getValue())) {
                    // If warehouse is changed, reset data below.
                    mdseTrxId = null;
                    mdseUnitId = BigDecimal.ONE;
                    isRegistMdseTrxId = false;
                }
                prevWhCd = wmsInbdItemWrkT.whCd.getValue();

                // Create WMS_INBD_MDSE, WMS_INBD_MDSE_UPC,
                // WMS_INBD_MDSE_SER.
                wmsInbdMdseT = createWmsInbdMdse(wmsInbdItemWrkT);
                if (wmsInbdMdseT != null) {
                    wmsInbdMdseUpcTList = createWmsInbdMdseUpc(wmsInbdItemUpcWrkList);
                }
                if (wmsInbdMdseUpcTList != null) {
                    wmsInbdMdseSerTList = createWmsInbdMdseSer(wmsInbdItemSerWrkList);
                }
                if (wmsInbdMdseSerTList != null) {
                    isRegistMdse = registerMdse(wmsInbdMdseT, wmsInbdMdseUpcTList, wmsInbdMdseSerTList, wmsItemDnldIntfcId, false);
                }

                // Update PROC_STS_CD of WMS_INBD_ITEM_WRK,
                // WMS_INBD_ITEM_UPC_WRK, WMS_INBD_ITEM_SER_WRK.
                if (isRegistMdse) {
                    isUpdateWmsInbdItemWrkTbl = updateWmsInbdItemWrk(wmsInbdItemWrkT, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, PROC_STS.COMPLEATED);
                } else {
                    rollback();
                    isUpdateWmsInbdItemWrkTbl = updateWmsInbdItemWrk(wmsInbdItemWrkT, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, PROC_STS.ERROR);
                }

                if (isUpdateWmsInbdItemWrkTbl) {
                    // commit
                    commit();
                    if (isRegistMdse) {
                        totalCommitCount++;

                        // If transaction id isn't registered,
                        // register transaction id.
                        if (!isRegistMdseTrxId && mdseUnitId.intValue() > 1) {
                            s21TrxTblAccessor.createIntegrationRecordForBatch(wmsItemDnldIntfcId, mdseTrxId);
                            isRegistMdseTrxId = true;
                            commit();
                        }
                    } else {
                        totalErrCount++;
                    }
                } else {
                    // rollback
                    rollback();
                    totalErrCount++;
                }
            }
        }
    }

    /**
     * Set common data as WMS_SQ_NUM, WH_CD to TMsg.
     * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRKTMsg
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRKTMsg List
     * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRKTMsg List
     * @param whCd WH_CD
     */
    private static void setWmsInbdItemData(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT, List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList, List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList, String whCd) {

        BigDecimal toWmsDataIfSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);

        // WMS_INBD_ITEM_WRK
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsSqNum, toWmsDataIfSq);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.whCd, whCd);

        // WMS_INBD_ITEM_UPC_WRK
        for (WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT : wmsInbdItemUpcWrkList) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsSqNum, toWmsDataIfSq);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.whCd, whCd);
        }

        // WMS_INBD_ITEM_SER_WRK
        for (WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT : wmsInbdItemSerWrkList) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.wmsSqNum, toWmsDataIfSq);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.whCd, whCd);
        }
    }

    /**
     * Get WMS_INBD_ITEM_WRKTMsg List
     * @return WMS_INBD_ITEM_WRKTMsg List
     */
    private List getWmsInbdItemWrkList() {

        // Get WMS_INBD_ITEM_WRK
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
        queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
        return this.ssmBatchClient.queryObjectList("getWmsInbdItemWrkList", queryParam);
    }

    /**
     * Get WMS_INBD_ITEM_UPC_WRKTMsg List
     * @param whCd WH_CD
     * @param wmsSqNum WMS_SQ_NUM
     * @return WMS_INBD_ITEM_UPC_WRKTMsg List
     */
    private List getWmsInbdItemUpcWrkList(String whCd, BigDecimal wmsSqNum) {

        // Get WMS_INBD_ITEM_UPC_WRK
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
        queryParam.put("whCd", whCd);
        queryParam.put("wmsSqNum", wmsSqNum);
        return this.ssmBatchClient.queryObjectList("getWmsInbdItemUpcWrkList", queryParam);
    }

    /**
     * Get WMS_INBD_ITEM_SER_WRKTMsg List
     * @param whCd WH_CD
     * @param wmsSqNum WMS_SQ_NUM
     * @return WMS_INBD_ITEM_SER_WRKTMsg List
     */
    private List getWmsInbdItemSerWrkList(String whCd, BigDecimal wmsSqNum) {

        // Get WMS_INBD_ITEM_SER_WRK
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
        queryParam.put("whCd", whCd);
        queryParam.put("wmsSqNum", wmsSqNum);
        return this.ssmBatchClient.queryObjectList("getWmsInbdItemSerWrkList", queryParam);
    }

    /**
     * Register MDSE I/F Tables
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
     * @param wmsItemDnldIntfcId Interface Id
     * @param abcAnlsRsltUpdateFlg Boolean
     * @return isSuccess
     */
    private boolean registerMdse(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList, String wmsItemDnldIntfcId, Boolean abcAnlsRsltUpdateFlg) {

        if (!ZYPCommonFunc.hasValue(mdseTrxId)) {
            mdseTrxId = s21TrxTblAccessor.getNextTransactionId();
        }

        List<Map<String, Object>> storageRuleList = getStorageRuleList(glblCmpyCd, wmsInbdMdseT.whCd.getValue(), wmsInbdMdseT.wmsMdseCd.getValue());

        for (Map<String, Object> storageRuleMap : storageRuleList) {
            String rtlWhCd = (String) storageRuleMap.get("RTL_WH_CD");
            String rtlSwhCd = (String) storageRuleMap.get("RTL_SWH_CD");
            String wmsStoreRuleCd = (String) storageRuleMap.get("WMS_STORE_RULE_CD");
            BigDecimal cycleCntFreqDaysAot = (BigDecimal) storageRuleMap.get("CYCLE_CNT_FREQ_DAYS_AOT");

            // Register MDSE IF data.
            if (!createMdseIf01(wmsInbdMdseT, wmsInbdMdseUpcTList, wmsInbdMdseSerTList, wmsItemDnldIntfcId, rtlWhCd, rtlSwhCd, wmsStoreRuleCd, cycleCntFreqDaysAot)) {
                return false;
            }
            mdseUnitId = mdseUnitId.add(BigDecimal.ONE);
        }

        if (abcAnlsRsltUpdateFlg == true && storageRuleList != null) {
            if (!updateAbcAnlsRsltTbl(glblCmpyCd, wmsInbdMdseT.wmsMdseCd.getValue(), wmsInbdMdseT.whCd.getValue())){
                return false;
            }
        }

        return true;
    }

    /**
     * Create WMS_INBD_MDSE
     * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRKTMsg
     * @return isSuccess
     */
    private WMS_INBD_MDSETMsg createWmsInbdMdse(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT) {

        WMS_INBD_MDSETMsg wmsInbdMdseT = new WMS_INBD_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.glblCmpyCd, wmsInbdItemWrkT.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.whCd, wmsInbdItemWrkT.whCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsSqNum, wmsInbdItemWrkT.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.intfcTpId, wmsInbdItemWrkT.intfcTpId);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.intfcRecTpId, wmsInbdItemWrkT.intfcRecTpId);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.intfcRecActCd, wmsInbdItemWrkT.intfcRecActCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsCmpyCd, wmsInbdItemWrkT.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseCd, wmsInbdItemWrkT.wmsItemCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsProdCd, wmsInbdItemWrkT.wmsProdCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseDescTxt_01, wmsInbdItemWrkT.wmsMdseDescTxt_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.vndCd, wmsInbdItemWrkT.vndCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsFrtClsCd, wmsInbdItemWrkT.wmsFrtClsCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMnfClsNum, wmsInbdItemWrkT.wmsMnfClsNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseNum_01, wmsInbdItemWrkT.mnfItemNum_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseDescTxt_01, wmsInbdItemWrkT.mnfItemDescTxt_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseNum_02, wmsInbdItemWrkT.mnfItemNum_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseDescTxt_02, wmsInbdItemWrkT.mnfItemDescTxt_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.unitCrtnQty, wmsInbdItemWrkT.unitCrtnQty);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.unitPltQty, wmsInbdItemWrkT.unitPltQty);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.serNumFlg, wmsInbdItemWrkT.serNumFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.serLgNum, wmsInbdItemWrkT.serLgNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsResrcTxt, VAL_WMS_RESRC_TXT_DEF);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.curStdCostNum, wmsInbdItemWrkT.curStdCostNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.futStdCostNum, wmsInbdItemWrkT.futStdCostNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsSerTpCd, wmsInbdItemWrkT.wmsSerTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_01, wmsInbdItemWrkT.wmsClsCd_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_02, wmsInbdItemWrkT.wmsClsCd_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_03, wmsInbdItemWrkT.wmsClsCd_03);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_04, wmsInbdItemWrkT.wmsClsCd_04);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_05, wmsInbdItemWrkT.wmsClsCd_05);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_06, wmsInbdItemWrkT.wmsClsCd_06);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsCntyOrgCd, wmsInbdItemWrkT.wmsCntyOrgCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.invtyCtrlCd, wmsInbdItemWrkT.invtyCtrlCd);
        if (ZYPCommonFunc.hasValue(wmsInbdItemWrkT.invtyCatgCountCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.invtyCatgCountCd, wmsInbdItemWrkT.invtyCatgCountCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.invtyCatgCountCd, VAL_INVTY_CATG_COUNT_CD_DEF);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.spclHdlgCd, wmsInbdItemWrkT.spclHdlgCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseDescTxt_02, wmsInbdItemWrkT.wmsMdseDescTxt_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseTpCd, wmsInbdItemWrkT.wmsItemTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mdseTpDescTxt, wmsInbdItemWrkT.mdseTpDescTxt);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsTrfNum, wmsInbdItemWrkT.wmsTrfNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsHazMatFlg, wmsInbdItemWrkT.wmsHazMatFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.whIntfcTxt, wmsInbdItemWrkT.whIntfcTxt);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.batCptrReqFlg, wmsInbdItemWrkT.batCptrReqFlg);

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.hazMatCd, wmsInbdItemWrkT.hazMatCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.hazClsNm, wmsInbdItemWrkT.hazClsNm);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.hazIdNum, wmsInbdItemWrkT.hazIdNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.lotCtrlFlg, wmsInbdItemWrkT.lotCtrlFlg);

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfItemCd, wmsInbdItemWrkT.mnfItemCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mdseItemTpCd, wmsInbdItemWrkT.mdseItemTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.swLicCtrlTpCd, wmsInbdItemWrkT.swLicCtrlTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mtrMachFlg, wmsInbdItemWrkT.mtrMachFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.serNumReqTpCd, wmsInbdItemWrkT.serNumReqTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.rtrnReqPrtFlg, wmsInbdItemWrkT.rtrnReqPrtFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mdseItemMnfTxt, wmsInbdItemWrkT.mdseItemMnfTxt);

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.backOrdImpctTpCd, wmsInbdItemWrkT.backOrdImpctTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.lbFmtTpTxt, wmsInbdItemWrkT.lbFmtTpTxt);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.cycleCntFreqNum, wmsInbdItemWrkT.cycleCntFreqNum);

        EZDTBLAccessor.insert(wmsInbdMdseT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdMdseT.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_MDSE, TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_WRK_PK, COL_WMS_MDSE_CD) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, NLXC014001.nullToZero(wmsInbdItemWrkT.wmsInbdItemWrkPk.getValue()).toString(), wmsInbdMdseT.wmsMdseCd.getValue()) });
        }
        return wmsInbdMdseT;
    }

    /**
     * Create WMS_INBD_MDSE_UPC
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRKTMsg List
     * @return isSuccess
     */
    private List<WMS_INBD_MDSE_UPCTMsg> createWmsInbdMdseUpc(List wmsInbdItemUpcWrkList) {

        List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList = new ArrayList<WMS_INBD_MDSE_UPCTMsg>();

        for (int i = 0; i < wmsInbdItemUpcWrkList.size(); i++) {
            WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT = (WMS_INBD_ITEM_UPC_WRKTMsg) wmsInbdItemUpcWrkList.get(i);
            WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcT = new WMS_INBD_MDSE_UPCTMsg();

            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.glblCmpyCd, wmsInbdItemUpcWrkT.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.whCd, wmsInbdItemUpcWrkT.whCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsSqNum, wmsInbdItemUpcWrkT.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsUomCd, wmsInbdItemUpcWrkT.wmsUomCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.intfcTpId, wmsInbdItemUpcWrkT.intfcTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.intfcRecTpId, wmsInbdItemUpcWrkT.intfcRecTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.intfcRecActCd, wmsInbdItemUpcWrkT.intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsCmpyCd, wmsInbdItemUpcWrkT.wmsCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseCd, wmsInbdItemUpcWrkT.wmsItemCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsUpcCd, wmsInbdItemUpcWrkT.wmsUpcCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsBaseUomQty, wmsInbdItemUpcWrkT.wmsBaseUomQty);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseWt, wmsInbdItemUpcWrkT.wmsItemWt);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseLgNum, wmsInbdItemUpcWrkT.wmsItemLgNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseWdtNum, wmsInbdItemUpcWrkT.wmsItemWdtNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseHgtNum, wmsInbdItemUpcWrkT.wmsItemHgtNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseVolNum, wmsInbdItemUpcWrkT.wmsItemVolNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsResrcTxt, VAL_WMS_RESRC_TXT_DEF);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.csePctTierNum, wmsInbdItemUpcWrkT.csePctTierNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.tierPctPltNum, wmsInbdItemUpcWrkT.tierPctPltNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsScc14Cd, wmsInbdItemUpcWrkT.wmsScc14Cd);

            EZDTBLAccessor.insert(wmsInbdMdseUpcT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdMdseUpcT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_MDSE_UPC, TBL_WMS_INBD_ITEM_UPC_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_UPC_WRK_PK, COL_WMS_MDSE_CD) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, NLXC014001.nullToZero(wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk.getValue()).toString(), wmsInbdMdseUpcT.wmsMdseCd.getValue()) });
            }
            wmsInbdMdseUpcTList.add(wmsInbdMdseUpcT);
        }
        return wmsInbdMdseUpcTList;
    }

    /**
     * Create WMS_INBD_ITEM_MDSE_SER
     * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRKTMsg List
     * @return isSuccess
     */
    private List<WMS_INBD_MDSE_SERTMsg> createWmsInbdMdseSer(List wmsInbdItemSerWrkList) {

        List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList = new ArrayList<WMS_INBD_MDSE_SERTMsg>();

        for (int i = 0; i < wmsInbdItemSerWrkList.size(); i++) {
            WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT = (WMS_INBD_ITEM_SER_WRKTMsg) wmsInbdItemSerWrkList.get(i);
            WMS_INBD_MDSE_SERTMsg wmsInbdMdseSerT = new WMS_INBD_MDSE_SERTMsg();

            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.glblCmpyCd, wmsInbdItemSerWrkT.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.whCd, wmsInbdItemSerWrkT.whCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsSqNum, wmsInbdItemSerWrkT.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.serLineNum, wmsInbdItemSerWrkT.serLineNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.intfcTpId, wmsInbdItemSerWrkT.intfcTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.intfcRecTpId, wmsInbdItemSerWrkT.intfcRecTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.intfcRecActCd, wmsInbdItemSerWrkT.intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsCmpyCd, wmsInbdItemSerWrkT.wmsCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsMdseCd, wmsInbdItemSerWrkT.wmsItemCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.fromSerNum, wmsInbdItemSerWrkT.fromSerNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.toSerNum, wmsInbdItemSerWrkT.toSerNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsResrcTxt, VAL_WMS_RESRC_TXT_DEF);

            EZDTBLAccessor.insert(wmsInbdMdseSerT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdMdseSerT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_MDSE_SER, TBL_WMS_INBD_ITEM_SER_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_SER_WRK_PK, COL_WMS_MDSE_CD) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, NLXC014001.nullToZero(wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk.getValue()).toString(), wmsInbdMdseSerT.wmsMdseCd.getValue()) });
            }
            wmsInbdMdseSerTList.add(wmsInbdMdseSerT);
        }
        return wmsInbdMdseSerTList;
    }

    /**
     * Create NLGI3100_01
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
     * @param wmsItemDnldIntfcId Interface Id
     * @return isSuccess
     */
    private boolean createMdseIf01(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, 
            List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList, String wmsItemDnldIntfcId, String rtlWhCd, String rtlSwhCd, String wmsStoreRuleCd, BigDecimal cycleCntFreqDaysAot) {

        WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcEa = null;
        WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcPl = null;
        WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcCa = null;
        WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcBx = null;
        for (WMS_INBD_MDSE_UPCTMsg tmpUpcT : wmsInbdMdseUpcTList) {
            if (WMS_UOM.EACH.equals(tmpUpcT.wmsUomCd.getValue())) {
                wmsInbdMdseUpcEa = tmpUpcT;
            } else if (WMS_UOM.PALLET.equals(tmpUpcT.wmsUomCd.getValue())) {
                wmsInbdMdseUpcPl = tmpUpcT;
            } else if (WMS_UOM.MASTER_CARTON.equals(tmpUpcT.wmsUomCd.getValue())) {
                wmsInbdMdseUpcCa = tmpUpcT;
            } else if (WMS_UOM.INNER_CARTON.equals(tmpUpcT.wmsUomCd.getValue())) {
                wmsInbdMdseUpcBx = tmpUpcT;
            }
        }

        boolean isBxWmsBaseUomQtyOver1 = false;
        boolean isPlWmsBaseUomQtyOver1 = false;
        boolean isCaWmsBaseUomQtyOver1 = false;
        if (wmsInbdMdseUpcBx != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcBx.wmsBaseUomQty) && BigDecimal.ONE.compareTo(wmsInbdMdseUpcBx.wmsBaseUomQty.getValue()) == -1) {
            isBxWmsBaseUomQtyOver1 = true;
        }
        if (wmsInbdMdseUpcCa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcCa.wmsBaseUomQty) && BigDecimal.ONE.compareTo(wmsInbdMdseUpcCa.wmsBaseUomQty.getValue()) == -1) {
            isCaWmsBaseUomQtyOver1 = true;
        }
        if (wmsInbdMdseUpcPl != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcPl.wmsBaseUomQty) && BigDecimal.ONE.compareTo(wmsInbdMdseUpcPl.wmsBaseUomQty.getValue()) == -1) {
            isPlWmsBaseUomQtyOver1 = true;
        }

        String uom2 = null;
        String uom3 = null;
        String uom4 = null;
        WMS_INBD_MDSE_UPCTMsg uom2T = null;
        WMS_INBD_MDSE_UPCTMsg uom3T = null;
        WMS_INBD_MDSE_UPCTMsg uom4T = null;

        if (isBxWmsBaseUomQtyOver1) {
            uom2 = WMS_UOM.INNER_CARTON;
            uom2T = wmsInbdMdseUpcBx;
        } else if (isCaWmsBaseUomQtyOver1) {
            uom2 = WMS_UOM.MASTER_CARTON;
            uom2T = wmsInbdMdseUpcCa;
        } else if (isPlWmsBaseUomQtyOver1) {
            uom2 = WMS_UOM.PALLET;
            uom2T = wmsInbdMdseUpcPl;
        }

        if (isBxWmsBaseUomQtyOver1) {
            if (wmsInbdMdseUpcCa != null && !wmsInbdMdseUpcBx.wmsBaseUomQty.getValue().equals(wmsInbdMdseUpcCa.wmsBaseUomQty.getValue())) {
                uom3 = WMS_UOM.MASTER_CARTON;
                uom3T = wmsInbdMdseUpcCa;
            } else if (wmsInbdMdseUpcPl != null && !wmsInbdMdseUpcBx.wmsBaseUomQty.getValue().equals(wmsInbdMdseUpcPl.wmsBaseUomQty.getValue())) {
                uom3 = WMS_UOM.PALLET;
                uom3T = wmsInbdMdseUpcPl;
            }
        } else if (isCaWmsBaseUomQtyOver1) {
            if (wmsInbdMdseUpcPl != null && !wmsInbdMdseUpcCa.wmsBaseUomQty.getValue().equals(wmsInbdMdseUpcPl.wmsBaseUomQty.getValue())) {
                uom3 = WMS_UOM.PALLET;
                uom3T = wmsInbdMdseUpcPl;
            }
        }

        if (isBxWmsBaseUomQtyOver1 && (wmsInbdMdseUpcCa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcCa.wmsBaseUomQty) && !wmsInbdMdseUpcBx.wmsBaseUomQty.getValue().equals(wmsInbdMdseUpcCa.wmsBaseUomQty.getValue()))) {
            if (wmsInbdMdseUpcPl != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcPl.wmsBaseUomQty) && !wmsInbdMdseUpcCa.wmsBaseUomQty.getValue().equals(wmsInbdMdseUpcPl.wmsBaseUomQty.getValue())) {
                uom4 = WMS_UOM.PALLET;
                uom4T = wmsInbdMdseUpcPl;
            }
        }

        // Create NLGI3100_01 data.
        NLGI3100_01TMsg algi310001T = new NLGI3100_01TMsg();
        ZYPEZDItemValueSetter.setValue(algi310001T.interfaceId, wmsItemDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(algi310001T.transactionId, mdseTrxId);
        ZYPEZDItemValueSetter.setValue(algi310001T.segmentId, VAL_SEGMENT_ID_1);
        ZYPEZDItemValueSetter.setValue(algi310001T.unitId, mdseUnitId);
        ZYPEZDItemValueSetter.setValue(algi310001T.seqNumber, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(algi310001T.itemCdTxt, wmsInbdMdseT.wmsMdseCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.wmsItemDescTxt_01, wmsInbdMdseT.wmsMdseDescTxt_01);
        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsUpcCd) && wmsInbdMdseUpcEa.wmsUpcCd.getValue().length() > 0) {
            ZYPEZDItemValueSetter.setValue(algi310001T.upcCdTxt, wmsInbdMdseUpcEa.wmsUpcCd);
        }
        ZYPEZDItemValueSetter.setValue(algi310001T.prodClsTxt, wmsInbdMdseT.wmsProdCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt, VAL_ITEM_UOM_CD_TXT_EA);

        if (wmsInbdMdseUpcEa != null) {
            setUomTxt(algi310001T.hgtUomTxt_01, wmsInbdMdseUpcEa.wmsMdseHgtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
            setUomTxt(algi310001T.wdtUomTxt_01, wmsInbdMdseUpcEa.wmsMdseWdtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
            setUomTxt(algi310001T.depthUomTxt_01, wmsInbdMdseUpcEa.wmsMdseLgNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
            setUomTxt(algi310001T.grsWtUomTxt_01, wmsInbdMdseUpcEa.wmsMdseWt.getValue(), VAL_BIGDECIMAL_0_001, VAL_STR_0_001, FMT_0_000);
        } else {
            setUomTxt(algi310001T.hgtUomTxt_01, null, VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
            setUomTxt(algi310001T.wdtUomTxt_01, null, VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
            setUomTxt(algi310001T.depthUomTxt_01, null, VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
            setUomTxt(algi310001T.grsWtUomTxt_01, null, VAL_BIGDECIMAL_0_001, VAL_STR_0_001, FMT_0_000);
        }

        if (ZYPConstant.FLG_ON_Y.equals(wmsInbdMdseT.serNumFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(algi310001T.serNumReqTxt, wmsInbdMdseT.serNumFlg);
            ZYPEZDItemValueSetter.setValue(algi310001T.serNumUomTxt, WMS_UOM.EACH);
        } else {
            ZYPEZDItemValueSetter.setValue(algi310001T.serNumReqTxt, ZYPConstant.FLG_OFF_N);
            algi310001T.serNumUomTxt.clear();
        }
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.wmsMnfClsNum)) {
            ZYPEZDItemValueSetter.setValue(algi310001T.frtClsTxt, decimalFormat(wmsInbdMdseT.wmsMnfClsNum.getValue(), FMT_0_S));
        }
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.mnfMdseNum_02)) {
            ZYPEZDItemValueSetter.setValue(algi310001T.nmfcItemTxt, NLXC014001.nullToEmpty(wmsInbdMdseT.mnfMdseNum_01.getValue()) + VAL_PERIOD //
                    + wmsInbdMdseT.mnfMdseNum_02.getValue().replaceFirst(VAL_NMFC_ITEM_SUB_NUM_PFC, ""));
        } else {
            ZYPEZDItemValueSetter.setValue(algi310001T.nmfcItemTxt, wmsInbdMdseT.mnfMdseNum_01);
        }
        ZYPEZDItemValueSetter.setValue(algi310001T.nmfcItemDescTxt_01, wmsInbdMdseT.mnfMdseDescTxt_01);
        ZYPEZDItemValueSetter.setValue(algi310001T.nmfcItemDescTxt_02, wmsInbdMdseT.mnfMdseDescTxt_02);

        if (wmsInbdMdseUpcPl != null) {
            if (ZYPCommonFunc.hasValue(wmsInbdMdseUpcPl.csePctTierNum) && BigDecimal.ZERO.compareTo(wmsInbdMdseUpcPl.csePctTierNum.getValue()) == -1) {
                ZYPEZDItemValueSetter.setValue(algi310001T.crtnPerTierTxt, wmsInbdMdseUpcPl.csePctTierNum.getValue().toString());
            }
            if (ZYPCommonFunc.hasValue(wmsInbdMdseUpcPl.tierPctPltNum) && BigDecimal.ZERO.compareTo(wmsInbdMdseUpcPl.tierPctPltNum.getValue()) == -1) {
                ZYPEZDItemValueSetter.setValue(algi310001T.tierPerPltTxt, wmsInbdMdseUpcPl.tierPctPltNum.getValue().toString());
            }
        }

        NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
        bean.setGlblCmpyCd(glblCmpyCd);
        bean.setMdseCd(wmsInbdMdseT.wmsMdseCd.getValue());
        bean.setInvtyLocCd(rtlWhCd.concat(rtlSwhCd));

        NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
        
        if (!bean.getErrList().isEmpty()) {
            if (ZYPCommonFunc.hasValue(wmsInbdMdseT.curStdCostNum) && BigDecimal.ZERO.compareTo(wmsInbdMdseT.curStdCostNum.getValue()) == -1) {
                ZYPEZDItemValueSetter.setValue(algi310001T.curStdCostTxt, wmsInbdMdseT.curStdCostNum.getValue().toString());
            }
        } else {
            ZYPEZDItemValueSetter.setValue(algi310001T.curStdCostTxt, getDecimalValue(wmsInbdMdseT, ATT_CUR_STD_COST_NUM, bean.getUnitPrcAmt()).toPlainString());
        }

        ZYPEZDItemValueSetter.setValue(algi310001T.trfNumTxt, wmsInbdMdseT.wmsTrfNum);
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.wmsMdseDescTxt_02) && !wmsInbdMdseT.wmsMdseDescTxt_02.getValue().equals(wmsInbdMdseT.wmsMdseDescTxt_01.getValue())) {
            ZYPEZDItemValueSetter.setValue(algi310001T.wmsItemDescTxt_02, wmsInbdMdseT.wmsMdseDescTxt_02);
        }
        if (VAL_WMS_CMPY_CD_02.equals(wmsInbdMdseT.wmsCmpyCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(algi310001T.defRelForPutAwayTxt, ZYPConstant.FLG_OFF_N);
        }

        // Set *_02.
        if (ZYPCommonFunc.hasValue(uom2)) {
            ZYPEZDItemValueSetter.setValue(algi310001T.qtyUomInUomTxt_02, decimalFormat(uom2T.wmsBaseUomQty.getValue(), FMT_0));
//            String qtyStr = uom2T.wmsBaseUomQty.getValue().toString();
//            if (qtyStr.length() > VAL_LEN_4) {
//                ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt_02, uom2 + qtyStr.substring(qtyStr.length() - VAL_LEN_4, qtyStr.length()));
//            } else {
//                ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt_02, uom2 + qtyStr);
//            }
//            setUomTxt(algi310001T.hgtUomTxt_02, uom2T.wmsMdseHgtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.wdtUomTxt_02, uom2T.wmsMdseWdtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.depthUomTxt_02, uom2T.wmsMdseLgNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.grsWtUomTxt_02, uom2T.wmsMdseWt.getValue(), VAL_BIGDECIMAL_0_001, VAL_STR_0_001, FMT_0_000);
            ZYPEZDItemValueSetter.setValue(algi310001T.hgtUomTxt_02, algi310001T.hgtUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.wdtUomTxt_02, algi310001T.wdtUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.depthUomTxt_02, algi310001T.depthUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.grsWtUomTxt_02, algi310001T.grsWtUomTxt_01);
        } else {
            // Set default value
            ZYPEZDItemValueSetter.setValue(algi310001T.qtyUomInUomTxt_02, BigDecimal.ZERO.toString());
            algi310001T.itemUomCdTxt_02.clear();
            ZYPEZDItemValueSetter.setValue(algi310001T.hgtUomTxt_02, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.wdtUomTxt_02, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.depthUomTxt_02, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.grsWtUomTxt_02, BigDecimal.ZERO.toString());
        }

        // Set *_03.
        if (ZYPCommonFunc.hasValue(uom3) && BigDecimal.ZERO.compareTo(uom3T.wmsBaseUomQty.getValue().remainder(uom2T.wmsBaseUomQty.getValue())) == 0) {
            ZYPEZDItemValueSetter.setValue(algi310001T.qtyUomInUomTxt_03, decimalFormat(uom3T.wmsBaseUomQty.getValue().divide(uom2T.wmsBaseUomQty.getValue(), WMS_BASE_UOM_QTY_NUM_OF_DECL_PLACE, BigDecimal.ROUND_HALF_UP), FMT_0));
//            String qtyStr = uom3T.wmsBaseUomQty.getValue().divide(uom2T.wmsBaseUomQty.getValue()).toString();
//            if (qtyStr.length() > VAL_LEN_4) {
//                ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt_03, uom3 + qtyStr.substring(qtyStr.length() - VAL_LEN_4, qtyStr.length()));
//            } else {
//                ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt_03, uom3 + qtyStr);
//            }
//            setUomTxt(algi310001T.hgtUomTxt_03, uom3T.wmsMdseHgtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.wdtUomTxt_03, uom3T.wmsMdseWdtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.depthUomTxt_03, uom3T.wmsMdseLgNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.grsWtUomTxt_03, uom3T.wmsMdseWt.getValue(), VAL_BIGDECIMAL_0_001, VAL_STR_0_001, FMT_0_000);
            ZYPEZDItemValueSetter.setValue(algi310001T.hgtUomTxt_03, algi310001T.hgtUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.wdtUomTxt_03, algi310001T.wdtUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.depthUomTxt_03, algi310001T.depthUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.grsWtUomTxt_03, algi310001T.grsWtUomTxt_01);
        } else {
            // Set default value
            ZYPEZDItemValueSetter.setValue(algi310001T.qtyUomInUomTxt_03, BigDecimal.ZERO.toString());
            algi310001T.itemUomCdTxt_03.clear();
            ZYPEZDItemValueSetter.setValue(algi310001T.hgtUomTxt_03, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.wdtUomTxt_03, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.depthUomTxt_03, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.grsWtUomTxt_03, BigDecimal.ZERO.toString());
        }
        // Set *_04.
        if (ZYPCommonFunc.hasValue(uom4) && ZYPCommonFunc.hasValue(algi310001T.itemUomCdTxt_03) && BigDecimal.ZERO.compareTo(uom4T.wmsBaseUomQty.getValue().remainder(uom3T.wmsBaseUomQty.getValue())) == 0) {
            ZYPEZDItemValueSetter.setValue(algi310001T.qtyUomInUomTxt_04, decimalFormat(uom4T.wmsBaseUomQty.getValue().divide(uom3T.wmsBaseUomQty.getValue(), WMS_BASE_UOM_QTY_NUM_OF_DECL_PLACE, BigDecimal.ROUND_HALF_UP), FMT_0));
//            String qtyStr = uom4T.wmsBaseUomQty.getValue().divide(uom3T.wmsBaseUomQty.getValue()).toString();
//            if (qtyStr.length() > VAL_LEN_4) {
//                ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt_04, uom4 + qtyStr.substring(qtyStr.length() - VAL_LEN_4, qtyStr.length()));
//            } else {
//                ZYPEZDItemValueSetter.setValue(algi310001T.itemUomCdTxt_04, uom4 + qtyStr);
//            }
//            setUomTxt(algi310001T.hgtUomTxt_04, uom4T.wmsMdseHgtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.wdtUomTxt_04, uom4T.wmsMdseWdtNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.depthUomTxt_04, uom4T.wmsMdseLgNum.getValue(), VAL_BIGDECIMAL_0_1, VAL_STR_0_1, FMT_0_0);
//            setUomTxt(algi310001T.grsWtUomTxt_04, uom4T.wmsMdseWt.getValue(), VAL_BIGDECIMAL_0_001, VAL_STR_0_001, FMT_0_000);
            ZYPEZDItemValueSetter.setValue(algi310001T.hgtUomTxt_04, algi310001T.hgtUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.wdtUomTxt_04, algi310001T.wdtUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.depthUomTxt_04, algi310001T.depthUomTxt_01);
            ZYPEZDItemValueSetter.setValue(algi310001T.grsWtUomTxt_04, algi310001T.grsWtUomTxt_01);
        } else {
            // Set default value
            ZYPEZDItemValueSetter.setValue(algi310001T.qtyUomInUomTxt_04, BigDecimal.ZERO.toString());
            algi310001T.itemUomCdTxt_04.clear();
            ZYPEZDItemValueSetter.setValue(algi310001T.hgtUomTxt_04, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.wdtUomTxt_04, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.depthUomTxt_04, BigDecimal.ZERO.toString());
            ZYPEZDItemValueSetter.setValue(algi310001T.grsWtUomTxt_04, BigDecimal.ZERO.toString());
        }

        ZYPEZDItemValueSetter.setValue(algi310001T.lotCtrlFlg, wmsInbdMdseT.lotCtrlFlg);
        if (ZYPConstant.FLG_ON_Y.equals(wmsInbdMdseT.lotCtrlFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(algi310001T.lotEntryOptCd, VAL_DEF_LOT_ENTRY_OPT_CD);
            ZYPEZDItemValueSetter.setValue(algi310001T.exprLtNum, VAL_DEF_EXPR_LT_NUM);
            ZYPEZDItemValueSetter.setValue(algi310001T.shelfLifeNum, VAL_DEF_SHELF_LIFE_NUM);
            ZYPEZDItemValueSetter.setValue(algi310001T.defExprPerFlg, VAL_DEF_DEF_EXPR_PER_FLG);
            ZYPEZDItemValueSetter.setValue(algi310001T.conslAgeLimitNum, VAL_DEF_CONSL_AGE_LIMIT_NUM);
        } else {
            ZYPEZDItemValueSetter.setValue(algi310001T.defExprPerFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(algi310001T.wmsHazMatFlg, wmsInbdMdseT.wmsHazMatFlg);
        ZYPEZDItemValueSetter.setValue(algi310001T.hazMatCd, wmsInbdMdseT.hazMatCd);
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.hazClsNm) && wmsInbdMdseT.hazClsNm.getValue().length() > VAL_HAZ_CLS_NM_SIZE) {
            ZYPEZDItemValueSetter.setValue(algi310001T.hazClsNm, wmsInbdMdseT.hazClsNm.getValue().substring(0, VAL_HAZ_CLS_NM_SIZE));
        } else {
            ZYPEZDItemValueSetter.setValue(algi310001T.hazClsNm, wmsInbdMdseT.hazClsNm);
        }
        if (NLXC014001.nullToEmpty(wmsInbdMdseT.hazIdNum.getValue()).startsWith(VAL_HAZ_ID_NUN_PFX)) {
            ZYPEZDItemValueSetter.setValue(algi310001T.hazIdNum, wmsInbdMdseT.hazIdNum.getValue().replaceFirst(VAL_HAZ_ID_NUN_PFX, ""));
        } else {
            ZYPEZDItemValueSetter.setValue(algi310001T.hazIdNum, wmsInbdMdseT.hazIdNum);
        }
        ZYPEZDItemValueSetter.setValue(algi310001T.nmfcItemNum, wmsInbdMdseT.mnfMdseNum_01);
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.mnfMdseNum_02)) {
            ZYPEZDItemValueSetter.setValue(algi310001T.nmfcItemSubNum, wmsInbdMdseT.mnfMdseNum_02.getValue().replaceFirst(VAL_NMFC_ITEM_SUB_NUM_PFC, ""));
        }

        ZYPEZDItemValueSetter.setValue(algi310001T.mnfItemCd, wmsInbdMdseT.mnfItemCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.mdseItemTpCd, wmsInbdMdseT.mdseItemTpCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.swLicCtrlTpTxt, wmsInbdMdseT.swLicCtrlTpCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.mtrMachFlg, wmsInbdMdseT.mtrMachFlg);
        ZYPEZDItemValueSetter.setValue(algi310001T.serNumReqTpCd, wmsInbdMdseT.serNumReqTpCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.rtrnReqPrtFlg, wmsInbdMdseT.rtrnReqPrtFlg);
        ZYPEZDItemValueSetter.setValue(algi310001T.mdseItemMnfTxt, wmsInbdMdseT.mdseItemMnfTxt);
        ZYPEZDItemValueSetter.setValue(algi310001T.backOrdImpctTpCd, wmsInbdMdseT.backOrdImpctTpCd);
        ZYPEZDItemValueSetter.setValue(algi310001T.lbFmtTpTxt, wmsInbdMdseT.lbFmtTpTxt);
//        ZYPEZDItemValueSetter.setValue(algi310001T.cycleCntFreqNum, wmsInbdMdseT.cycleCntFreqNum);
        ZYPEZDItemValueSetter.setValue(algi310001T.cycleCntFreqNum, cycleCntFreqDaysAot);
        ZYPEZDItemValueSetter.setValue(algi310001T.packCdTxt, getPackageCode(rtlWhCd, rtlSwhCd));
        ZYPEZDItemValueSetter.setValue(algi310001T.wmsStoreRuleCd, wmsStoreRuleCd);

        EZDTBLAccessor.insert(algi310001T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi310001T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI3100_01, TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_SQ_NUM, COL_WMS_ITEM_CD) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdMdseT.whCd.getValue(), wmsInbdMdseT.wmsSqNum.getValue().toString(), wmsInbdMdseT.wmsMdseCd.getValue()) });
        }
        return true;
    }

    /**
     * Set UomTxt data.
     * @param setTo Set to data
     * @param setFrom Set from data
     * @param cmpVal Compare value
     * @param defVal Default value
     * @param fmt Format
     */
    private void setUomTxt(EZDTStringItem setTo, BigDecimal setFrom, BigDecimal cmpVal, String defVal, String fmt) {

        if (!ZYPCommonFunc.hasValue(setFrom) || cmpVal.compareTo(setFrom) == 1) {
            ZYPEZDItemValueSetter.setValue(setTo, defVal);
        } else {
            ZYPEZDItemValueSetter.setValue(setTo, decimalFormat(setFrom, fmt));
        }
    }

    /**
     * Insert WMS_MDSE_LIST Table.
     * @param whCd WarehouseCode
     * @param mdseCd MerchandiseCode
     * @param lastIntfcMdseUpdTs LAST_INTFC_MDSE_UPD_TS
     * @return regist result
     */
    private boolean insertWmsMdseList(String whCd, String mdseCd, String lastIntfcMdseUpdTs) {

        WMS_MDSE_LISTTMsg wmsMdseListT = new WMS_MDSE_LISTTMsg();
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsMdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.lastIntfcMdseUpdTs, lastIntfcMdseUpdTs);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.COMPLEATED);

        WMS_MDSE_LISTTMsg outMsg = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKey(wmsMdseListT);
        if (outMsg != null) {
            return true;
        }
        EZDTBLAccessor.create(wmsMdseListT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsMdseListT.getReturnCode())) {
            throw new S21AbendException(NLGM0007E, new String[] {TBL_WMS_MDSE_LIST //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_MDSE_CD) //
                    , NLXCMsgHelper.toListedString(wmsMdseListT.glblCmpyCd, wmsMdseListT.whCd, wmsMdseListT.wmsMdseCd) });
        }
        return true;
    }

    /**
     * Update WMS_INBD_ITEM_WRK
     * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRKTMsg
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRKTMsg List
     * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRKTMsg List
     * @param procStsCd PROC_STS_CD
     * @return isSuccess
     */
    private boolean updateWmsInbdItemWrk(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT, List wmsInbdItemUpcWrkList, List wmsInbdItemSerWrkList, String procStsCd) {

        // WMS_INBD_ITEM_WRK
        String wmsInbdItemWkPk = wmsInbdItemWrkT.wmsInbdItemWrkPk.getValue().toString();
        wmsInbdItemWrkT = (WMS_INBD_ITEM_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdItemWrkT);
        if (wmsInbdItemWrkT == null) {
            throw new S21AbendException(NLGM0044E, new String[] {TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_WRK_PK) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdItemWkPk) });
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.procStsCd, procStsCd);

        EZDTBLAccessor.update(wmsInbdItemWrkT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdItemWrkT.getReturnCode())) {
            throw new S21AbendException(NLGM0046E, new String[] {TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_WRK_PK) //
                    , NLXCMsgHelper.toListedString(wmsInbdItemWrkT.glblCmpyCd, wmsInbdItemWrkT.wmsInbdItemWrkPk.getValue().toString()) });
        }

        // WMS_INBD_ITEM_UPC_WRK
        for (int i = 0; i < wmsInbdItemUpcWrkList.size(); i++) {
            WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT = (WMS_INBD_ITEM_UPC_WRKTMsg) wmsInbdItemUpcWrkList.get(i);
            String wmsInbdItemUpcWkPk = wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk.getValue().toString();
            wmsInbdItemUpcWrkT = (WMS_INBD_ITEM_UPC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdItemUpcWrkT);
            if (wmsInbdItemUpcWrkT == null) {
                throw new S21AbendException(NLGM0044E, new String[] {TBL_WMS_INBD_ITEM_UPC_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_UPC_WRK_PK) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdItemUpcWkPk) });
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.procStsCd, procStsCd);

            EZDTBLAccessor.update(wmsInbdItemUpcWrkT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdItemUpcWrkT.getReturnCode())) {
                throw new S21AbendException(NLGM0046E, new String[] {TBL_WMS_INBD_ITEM_UPC_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_UPC_WRK_PK) //
                        , NLXCMsgHelper.toListedString(wmsInbdItemUpcWrkT.glblCmpyCd, wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk.getValue().toString()) });
            }
        }

        // WMS_INBD_ITEM_SER_WRK
        for (int i = 0; i < wmsInbdItemSerWrkList.size(); i++) {
            WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT = (WMS_INBD_ITEM_SER_WRKTMsg) wmsInbdItemSerWrkList.get(i);
            String wmsInbdItemSerWkPk = wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk.getValue().toString();
            wmsInbdItemSerWrkT = (WMS_INBD_ITEM_SER_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdItemSerWrkT);
            if (wmsInbdItemSerWrkT == null) {
                throw new S21AbendException(NLGM0044E, new String[] {TBL_WMS_INBD_ITEM_SER_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_SER_WRK_PK) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdItemSerWkPk) });
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.procStsCd, procStsCd);

            EZDTBLAccessor.update(wmsInbdItemSerWrkT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdItemSerWrkT.getReturnCode())) {
                throw new S21AbendException(NLGM0046E, new String[] {TBL_WMS_INBD_ITEM_SER_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_SER_WRK_PK) //
                        , NLXCMsgHelper.toListedString(wmsInbdItemSerWrkT.glblCmpyCd, wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk.getValue().toString()) });
            }
        }
        return true;
    }

    /**
     * If numStr has value then return BigDecimal.
     * @param numStr number string
     * @return BigDecimal
     */
    private static BigDecimal getNum(String numStr) {

        if (!ZYPCommonFunc.hasValue(NLXC014001.nullToEmpty(numStr).trim())) {
            return null;
        }
        return new BigDecimal(numStr);
    }

    /**
     * Date format.
     * @param date target string
     * @param inFormat input format
     * @param outFormat output format
     * @return formatted string
     */
    private static String dateFormat(String date, String inFormat, String outFormat) {

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
    private static String decimalFormat(BigDecimal decimal, String outFormat) {

        if (!ZYPCommonFunc.hasValue(decimal)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(outFormat);
        return df.format(decimal);
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
     * getRwsDtl
     * @param String glblCmpyCd, String rwsNum
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getRwsDtl(String glblCmpyCd, String rwsNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rwsNum", rwsNum);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 START */
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 END */

        List<Map<String, Object>> rwsDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsDtl", ssmParam);

        return rwsDtlList;
    }

    /**
     * getRwsSerArray
     * @param String glblCmpyCd, String rwsNum
     * @return RWS_SERTMsg
     */
    private RWS_SERTMsgArray getRwsSerArray(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {
        RWS_SERTMsg inMsg = new RWS_SERTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("rwsNum01", rwsNum);
        inMsg.setConditionValue("rwsLineNum01", rwsDtlLineNum);
        RWS_SERTMsgArray rwsSerArray = (RWS_SERTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return rwsSerArray;
    }

    /**
     * getShpgPlnItemList
     * @param String glblCmpyCd, String rwsNum
     * @return List<String>
     */
    private List<String> getShpgPlnItemList(String glblCmpyCd, String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wmsWhCd", wmsWhCd);
        List<String> itemList = (List<String>) ssmBatchClient.queryObjectList("getShpgPlnItemList", ssmParam);
        
        return itemList;
    }

    /**
     * getMrpItemList
     * @param String glblCmpyCd, String rwsNum
     * @return List<String>
     */
    private List<String> getMrpItemList(String glblCmpyCd, String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wmsWhCd", wmsWhCd);
        List<String> itemList = (List<String>) ssmBatchClient.queryObjectList("getMrpItemList", ssmParam);
        
        return itemList;
    }

    /**
     * getPoItemList
     * @param String glblCmpyCd, String rwsNum
     * @return List<String>
     */
    private List<String> getPoItemList(String glblCmpyCd, String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wmsWhCd", wmsWhCd);
        List<String> itemList = (List<String>) ssmBatchClient.queryObjectList("getPoItemList", ssmParam);
        
        return itemList;
    }

    /**
     * Force Item Download Process. Call create WMS_INBD_MDSE tables
     * process and Update WMS_MDSE_LIST.
     */
    private void doTrxItemDnldProc() {
        String doShpgPlnItemDnld = ZYPCodeDataUtil.getVarCharConstValue("NLGB002001_SHPG_PLN_ITEM_DNLD", glblCmpyCd);
        String doMrpItemDnld = ZYPCodeDataUtil.getVarCharConstValue("NLGB002001_MRP_ITEM_DNLD", glblCmpyCd);
        String doPoItemDnld = ZYPCodeDataUtil.getVarCharConstValue("NLGB002001_PO_ITEM_DNLD", glblCmpyCd);

        for (int i = 0; i <trgtWhCdAry.length; i++) {
            String wmsWhCd = trgtWhCdAry[i];
            List<String> itemList = null; 

            if (ZYPCommonFunc.hasValue(doShpgPlnItemDnld) && doShpgPlnItemDnld.equals(ZYPConstant.FLG_ON_Y)) {
                itemList = getShpgPlnItemList(glblCmpyCd, wmsWhCd);
                for (String item : itemList) {
                    WMS_MDSE_LISTTMsg inMsg = new WMS_MDSE_LISTTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.wmsMdseCd, item);
                    ZYPEZDItemValueSetter.setValue(inMsg.whCd, wmsWhCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.wmsItemDnldStsCd, PROC_STS.IN_COMPLETED);
                    WMS_MDSE_LISTTMsg outMsg = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKey(inMsg);
                    
                    if (outMsg == null) {
                        EZDTBLAccessor.create(inMsg);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(doMrpItemDnld) && doMrpItemDnld.equals(ZYPConstant.FLG_ON_Y)) {
                itemList = getMrpItemList(glblCmpyCd, wmsWhCd);
                for (String item : itemList) {
                    WMS_MDSE_LISTTMsg inMsg = new WMS_MDSE_LISTTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.wmsMdseCd, item);
                    ZYPEZDItemValueSetter.setValue(inMsg.whCd, wmsWhCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.wmsItemDnldStsCd, PROC_STS.IN_COMPLETED);
                    WMS_MDSE_LISTTMsg outMsg = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKey(inMsg);
                    
                    if (outMsg == null) {
                        EZDTBLAccessor.create(inMsg);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(doPoItemDnld) && doPoItemDnld.equals(ZYPConstant.FLG_ON_Y)) {
                itemList = getPoItemList(glblCmpyCd, wmsWhCd);
                for (String item : itemList) {
                    WMS_MDSE_LISTTMsg inMsg = new WMS_MDSE_LISTTMsg();
                    ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.wmsMdseCd, item);
                    ZYPEZDItemValueSetter.setValue(inMsg.whCd, wmsWhCd);
                    ZYPEZDItemValueSetter.setValue(inMsg.wmsItemDnldStsCd, PROC_STS.IN_COMPLETED);
                    WMS_MDSE_LISTTMsg outMsg = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKey(inMsg);
                    
                    if (outMsg == null) {
                        EZDTBLAccessor.create(inMsg);
                    }
                }
            }
            commit();
        }
    }

    /**
     * Register WMS_INBD_SO_HDR.
     * @param hdrT WMS_INBD_SO_HDRTMsg
     * @return true / success, false / error
     */
    private boolean updateWmsIntfcCtrl(String procNm) {

        for (String whCd : trgtWhCdAry) {

            WMS_INTFC_CTRLTMsg inMsg = new WMS_INTFC_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.whCd, whCd);
            ZYPEZDItemValueSetter.setValue(inMsg.wmsIntfcTaskNm, procNm);

            WMS_INTFC_CTRLTMsg updMsg = (WMS_INTFC_CTRLTMsg) EZDTBLAccessor.findByKey(inMsg);
            String sysDate = ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS);
            if (updMsg == null) {
                ZYPEZDItemValueSetter.setValue(inMsg.taskDtTmTs, sysDate);
                EZDTBLAccessor.create(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {"WMS_INTFC_CTRL"});
                }
            } else {
                ZYPEZDItemValueSetter.setValue(updMsg.taskDtTmTs, sysDate);
                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {"WMS_INTFC_CTRL"});
                }
            }
        }
        return true;
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
     * getStorageRuleList
     * @param String glblCmpyCd, String rwsNum
     * @returnList<Map<String, Object>>
     */
    private List<Map<String, Object>> getStorageRuleList(String glblCmpyCd, String wmsWhCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wmsWhCd", wmsWhCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("defCoaMdseTpCd", "*");
        ssmParam.put("defVndRlnPk", BigDecimal.ZERO);

        List<Map<String, Object>> rtrnStoreList = new ArrayList<Map<String,Object>>();

        List<Map<String, Object>> storeList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getStorageRuleList", ssmParam);

        // QC#22477 Mod Start
        if (storeList != null && storeList.size() > 0) {
        	String curPriority = "";
            for (Map<String, Object> storageRuleMap : storeList) {
            	String priority = (String) storageRuleMap.get("PRIORITY");
            	if (ZYPCommonFunc.hasValue(curPriority) && !priority.equals(curPriority)) {
            		break ;
            	}
            	rtrnStoreList.add(storageRuleMap);
            	curPriority = priority ;
            }
        }
        // QC#22477 Mod End


        return rtrnStoreList;
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
            queryParam.put("inbound", INBD_OTBD.INBOUND);

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
    private String getWmsOrdTpXpndCdFromCache(String wmsPrchTpCd) {
        if (!ZYPCommonFunc.hasValue(wmsPrchTpCd)) {
            return null;
        }
        return this.cacheMapWmsOrdTpXpndCd.get(wmsPrchTpCd);
    }
    /**
     * isExistsSerialSpec
     * @param rwsNum
     * @return
     */
    private boolean isExistsSerialSpec(String rwsNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rwsNum", rwsNum);
        BigDecimal ret = (BigDecimal) ssmBatchClient.queryObject("isExistsSerialSpec", ssmParam);

        if (ret.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** QC#5141 Add.
     * Create and Insert NLGI2100_07.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param poSeqNumber PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     * @param rwsNum String
     */
    private BigDecimal createPoIf07(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId, String rwsNum) {

        // mod start 2022/03/14 QC#59780
        //List<String> proNumList = getRtrnProNumList(wmsInbdPoHdrT.glblCmpyCd.getValue(), rwsNum);
        List<Map<String, Object>> proNumList = getRtrnProNumList(wmsInbdPoHdrT.glblCmpyCd.getValue(), rwsNum);
        // mod end 2022/03/14 QC#59780

        NLGI2100_07TMsg algi210007T = new NLGI2100_07TMsg();
        // mod start 2022/03/14 QC#59780
        //for (String proNum : proNumList) {
        for (Map<String, Object> proNumMap : proNumList) {
            String proNum = (String) proNumMap.get("PRO_NUM");
            String clickToWmsTrkNum = (String) proNumMap.get("CLICK_TO_WMS_TRK_NUM");
        // mod end 2022/03/14 QC#59780
            if (ZYPCommonFunc.hasValue(proNum)) {
                algi210007T = new NLGI2100_07TMsg();
                ZYPEZDItemValueSetter.setValue(algi210007T.interfaceId, wmsPoDnldIntfcId);
                ZYPEZDItemValueSetter.setValue(algi210007T.transactionId, poTrxId);
                ZYPEZDItemValueSetter.setValue(algi210007T.segmentId, VAL_SEGMENT_ID_1);
                ZYPEZDItemValueSetter.setValue(algi210007T.unitId, poUnitId);
                // QC#25002 T.hakodate MOD START
                poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
                // QC#25002 T.hakodate MOD END
                ZYPEZDItemValueSetter.setValue(algi210007T.seqNumber, poSeqNumber);
                // QC#21166
//                ZYPEZDItemValueSetter.setValue(algi210007T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_01);
                ZYPEZDItemValueSetter.setValue(algi210007T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_07);

                ZYPEZDItemValueSetter.setValue(algi210007T.ordIdTxt, wmsInbdPoHdrT.wmsPoId);
                ZYPEZDItemValueSetter.setValue(algi210007T.ordTpTxt, this.getWmsOrdTpXpndCdFromCache(wmsInbdPoHdrT.wmsPrchTpCd.getValue()));
                ZYPEZDItemValueSetter.setValue(algi210007T.rtrnTrkSqTxt, String.format("%02d", poSeqNumber.intValue()));
                ZYPEZDItemValueSetter.setValue(algi210007T.rtrnTrkNum, proNum);
                // add start 2022/03/14 QC#59780
                // mod start 2022/05/13 QC#59780-1
//                 ZYPEZDItemValueSetter.setValue(algi210007T.clickToWmsTrkNum, clickToWmsTrkNum);
                if (ZYPCommonFunc.hasValue(clickToWmsTrkNum)) {
                    ZYPEZDItemValueSetter.setValue(algi210007T.rtrnTrkNum, clickToWmsTrkNum);
                }
                // mod end 2022/05/13 QC#59780-1
                // add end 2022/03/14 QC#59780

                EZDTBLAccessor.insert(algi210007T);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(algi210007T.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2100_07, TBL_RWS_HDR 
                            , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                            , NLXCMsgHelper.toListedString(glblCmpyCd, algi210007T.ordIdTxt.getValue()) });
                }
            }
        }
        // QC#25002 T.hakodate MOD START
        return poSeqNumber;
        // QC#25002 T.hakodate MOD END
    }

    /**
     * getRtrnProNumList
     * @param String glblCmpyCd
     * @param String rwsNum
     * @param String prchReqNum
     * @return RtrnProNumList
     */
    // mod start 2022/03/14 QC#59780
    //private List<String> getRtrnProNumList(String glblCmpyCd, String rwsNum) {
    private List<Map<String, Object>> getRtrnProNumList(String glblCmpyCd, String rwsNum) {
    // mod end 2022/03/14 QC#59780
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rwsNum", rwsNum);
        ssmParam.put("rtrnProSendFlg", ZYPConstant.FLG_OFF_N);
        // mod start 2022/03/14 QC#59780
        //List<String> proNumList = (List<String>) ssmBatchClient.queryObjectList("getRtrnProNumList", ssmParam);
        List<Map<String, Object>> proNumList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRtrnProNumList", ssmParam);
        // mod end 2022/03/14 QC#59780

        return proNumList;
    }

    /**
     * updateAbcAnlsRsltTbl
     * @param glblCmpyCd
     * @param mdseCd
     * @param wmsWhCd
     * @return true / success, false / error
     */
    private boolean updateAbcAnlsRsltTbl(String glblCmpyCd, String mdseCd, String wmsWhCd) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("wmsWhCd", wmsWhCd);
        queryParam.put("flag", ZYPConstant.FLG_ON_Y);

        BigDecimal abcAnlsRsltPk = (BigDecimal) ssmBatchClient.queryObject("getPrimaryKeyForAbcAnlsRslt", queryParam);

        if (abcAnlsRsltPk != null) {
            ABC_ANLS_RSLTTMsg abcAnlsRslt = new ABC_ANLS_RSLTTMsg();
            ZYPEZDItemValueSetter.setValue(abcAnlsRslt.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(abcAnlsRslt.abcAnlsRsltPk, abcAnlsRsltPk);

            abcAnlsRslt = (ABC_ANLS_RSLTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(abcAnlsRslt);

            if (abcAnlsRslt != null) {
                ZYPEZDItemValueSetter.setValue(abcAnlsRslt.intfcUpdTs, ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS));
                EZDTBLAccessor.update(abcAnlsRslt);
            } else {
                // Error
                throw new S21AbendException(NLGM0007E, new String[] {"ABC_ANLS_RSLT", "ABC_ANLS_RSLT_PK", abcAnlsRsltPk.toPlainString() });
            }
        }

        return true;
    }

    /**
     * getDecimalValue
     * @param tMsg WMS_INBD_MDSETMsg
     * @param attrName String
     * @param value BigDecimal
     * @return BigDecimal
     */
    private BigDecimal getDecimalValue(WMS_INBD_MDSETMsg tMsg, String attrName, BigDecimal value) {

        int decimalDigit= 0;
        int decimalScale= 0;

        // attributes max length 
        EZDItemAttribute decimalAttr = tMsg.getAttr(attrName);
        if (decimalAttr != null) {
            decimalScale = decimalAttr.getFracDigit();
            decimalDigit = decimalAttr.getDigit() - decimalScale;
        }

        BigDecimal newValueDecimal = value;
        if (ZYPCommonFunc.hasValue(newValueDecimal) && (!checkNumericLength(newValueDecimal, decimalDigit))) {
            String newValueStr = ZYPCommonFunc.leftPad("", decimalDigit, VAL_PADING);
            if (decimalScale > 0) {
                newValueStr = ZYPCommonFunc.concatString(newValueStr, VAL_PADING_SCALE, "");
            }
            newValueDecimal = new BigDecimal(newValueStr);
        }
        return newValueDecimal;
    }

    /**
     * checkNumericLength
     * @param checkValue BigDecimal
     * @param length int
     * @return true / success, false / error
     */
    private boolean checkNumericLength(BigDecimal checkValue, int length) {

        if (checkValue == null) {
            return true;
        }
        String[] args = checkValue.toPlainString().split(VAL_ESC_PERIOD);
        if (args != null && args.length > 0) {
            if (args[0].length() > length) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

    /**
     * checkNewItem
     * @param rwsNum String
     * @return true / exist new item, false / not exist new item
     */
    private boolean checkNewItem(String rwsNum, String wmsWhCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rwsNum", rwsNum);
        ssmParam.put("wmsWhCd", wmsWhCd);
        BigDecimal ret = (BigDecimal) ssmBatchClient.queryObject("isExistsWmsMdse", ssmParam);

        if (ret.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getRwsMsgArray
     * @param String glblCmpyCd, String rwsNum
     * @return RWS_MSGTMsgArray
     */
    private RWS_MSGTMsgArray getRwsMsgArray(String glblCmpyCd, String rwsNum) {
        RWS_MSGTMsg inMsg = new RWS_MSGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_MSGTMsgArray rwsMsgArray = (RWS_MSGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return rwsMsgArray;
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

}
