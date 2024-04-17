/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB022001;

import static com.canon.cusa.s21.batch.NLG.NLGB022001.NLGB022001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ABC_ANLS_RSLTTMsg;
import business.db.NLGI2200_01TMsg;
import business.db.NLGI3200_01TMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_MSGTMsg;
import business.db.RWS_MSGTMsgArray;
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
import business.db.WMS_WHTMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001;
import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001Constant;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ITEM_DNLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
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
 * RWS and Item Download for DBS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/25/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 03/29/2016   CITS            N.Akaishi       Update          V1.0
 * 08/24/2016   CITS            T.Wada          Update          QC#13753
 * 01/18/2017   CITS            Y.Fujii         Update          QC#17074
 * 01/30/2017   CITS            R.Shimamoto     Update          QC#17315
 * 02/02/2017   CITS            K.Ogino         Update          QC#17396
 * 06/28/2017   CITS            R.Shimamoto     Update          QC#19627
 * 06/28/2017   CITS            T.Kikuhara      Update          QC#19630
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 * 07/03/2017   CITS            Y.Imazu         Update          QC#19720
 *</pre>
 */
public class NLGB022001 extends S21BatchMain {

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
    }

    @Override
    protected void mainRoutine() {

        try {
            trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd);
            if (trgtWhCdAry == null) {
                outputErr(NLGM0047E, new String[] {whGpCd });
                return;
            }

            if (VAL_PROC_TP_RWS.equals(procTp)) {
                // RWS Download
                doRwsDnldProc();
                // 2016/03/18 N.Akaishi [V1.0 Add] Start
                updateWmsIntfcCtrl(VAL_INTFC_TASK_NM_RWS);
            } else {
                // Item Download
                doItemDnldProc();
                // 2016/03/18 N.Akaishi [V1.0 Add] Start
                updateWmsIntfcCtrl(VAL_INTFC_TASK_NM_ITEM);
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
        new NLGB022001().executeBatch(NLGB022001.class.getSimpleName());
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
            queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(MAP_KEY_NOT_DROP, VAL_NOT_DROP);
            queryParam.put(MAP_KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            queryParam.put(MAP_KEY_LEN2, VAL_LEN_2);
            queryParam.put(MAP_KEY_LEN3, VAL_LEN_3);
            queryParam.put(MAP_KEY_WMS, WH_SYS_TP.WMS);
            queryParam.put(MAP_KEY_FLG_Y, ZYPConstant.FLG_ON_Y);
            queryParam.put(MAP_KEY_INBOUND, INBD_OTBD.INBOUND);
            queryParam.put(MAP_KEY_LEN_VND_NM, VAL_VND_NM_SIZE);
            /* 07/03/2017 CSAI Y.Imazu Del QC#19720 START */
            // // QC#19627
            // queryParam.put(MAP_KEY_RWS_STS_CD, RWS_STS.PRINTED);
            /* 07/03/2017 CSAI Y.Imazu Del QC#19720 END */

            stmt = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_RWS, queryParam);
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
                    isRegistNewItem = doNewItemDnldProc(rs.getString(COL_RWS_NUM), rs.getString(COL_WMS_WH_CD), rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID), rs.getString(COL_WMS_DESC_SHORT_NM));
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
                        if (!registerPo(wmsInbdPoHdrT, wmsInbdPoDtlTList, wmsInbdPoVndT, rs.getString(COL_WMS_PO_DNLD_INTFC_ID), rs.getString(COL_WMS_DESC_SHORT_NM))) {
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
            rollback();
            sqlExceptionHandler(e);
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

            String whInEtaDt=rs.getString(COL_WH_IN_ETA_DT);
            if(ZYPCommonFunc.hasValue(whInEtaDt)) {
                whInEtaDt = whInEtaDt + VAL_000000;
            }

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
            ZYPEZDItemValueSetter.setValue(hdrT.poFromDtTmTs, whInEtaDt);
            ZYPEZDItemValueSetter.setValue(hdrT.printSwthCd, VAL_PRINT_SWTH_CD_P);
            ZYPEZDItemValueSetter.setValue(hdrT.wmsVeslNm, rs.getString(COL_IMPT_INV_VESL_NM));
            ZYPEZDItemValueSetter.setValue(hdrT.wmsBolNum, rs.getString(COL_IMPT_INV_BOL_NUM));

            RWS_MSGTMsgArray rwsMsgArray = getRwsMsgArray(glblCmpyCd, rs.getString(COL_RWS_NUM));
            if (rwsMsgArray != null && rwsMsgArray.length() > 0) {
                // Repeat the split registration
                for (int i = 0; i < rwsMsgArray.length(); i++) {
                    switch (i) {
                        case 1:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_01, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        case 2:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_02, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        case 3:
                            ZYPEZDItemValueSetter.setValue(hdrT.inbdPoMsgTxt_03, rwsMsgArray.no(i).rwsMsgTxt.getValue());
                        break;
                        case 4:
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

                String whInEtaDt = (String) rwsDtl.get(COL_WH_IN_ETA_DT);
                if(ZYPCommonFunc.hasValue(whInEtaDt)) {
                    whInEtaDt = whInEtaDt + VAL_000000;
                }

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
                ZYPEZDItemValueSetter.setValue(dtlT.wmsEstDtTmTs, whInEtaDt);
                ZYPEZDItemValueSetter.setValue(dtlT.wmsInvId, (String) rwsDtl.get(COL_IMPT_INV_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.wmsDoId, (String) rwsDtl.get(COL_IMPT_INV_DO_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.cseFromNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_FROM_CSE_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.cseToNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_TO_CSE_NUM));
                ZYPEZDItemValueSetter.setValue(dtlT.rtlWhCd, hdrT.rtlWhCd.getValue());
                ZYPEZDItemValueSetter.setValue(dtlT.rtlSwhCd, (String) rwsDtl.get(COL_RTL_SWH_CD));

                String thirdPtyDspTpCd = (String) rwsDtl.get(COL_THIRD_PTY_DSP_TP_CD);
                String thirdPtyDspTpDescTxt = "";
                /*
                // Following logic comes from Tecsys.
                // But just set THIRD_PTY_DSP_TP_CD to THIRD_PTY_DSP_TP_DESC_TXT in DBS, as original implementation.
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
                */
                thirdPtyDspTpDescTxt = thirdPtyDspTpCd;
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
            ZYPEZDItemValueSetter.setValue(vndT.wmsCmpyCd, hdrT.wmsCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(vndT.wmsPoId, hdrT.wmsPoId.getValue());
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
     * @param wmsDescShortNm String WMS_WH.WMS_DESC_SHORT_NM
     * @return isSuccess
     */
    // 2016/03/18 N.Akaishi [V1.0 Mod] Start
    private boolean registerPo(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, List<WMS_INBD_PO_DTLTMsg> wmsInbdPoDtlTList, WMS_INBD_PO_VNDTMsg wmsInbdPoVndT, String wmsPoDnldIntfcId, String wmsDescShortNm) {

        if (!ZYPCommonFunc.hasValue(poTrxId)) {
            poTrxId = s21TrxTblAccessor.getNextTransactionId();
        }

        // 2016/03/18 N.Akaishi [V1.0 Mod] Start
        for (WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT : wmsInbdPoDtlTList) {
            BigDecimal poSeqNumber = BigDecimal.ZERO;
            // Register PO IF data.
            poSeqNumber = createPoIf01(wmsInbdPoHdrT, wmsInbdPoDtlT, wmsInbdPoVndT, poSeqNumber, wmsPoDnldIntfcId, wmsDescShortNm);
            if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
                return false;
            }
// 2016/03/18 N.Akaishi [V1.0 Del] Start
//        poSeqNumber = createPoIf02(wmsInbdPoHdrT, wmsInbdPoDtlTList, poSeqNumber, wmsPoDnldIntfcId);
//        if (!ZYPCommonFunc.hasValue(poSeqNumber)) {
//            return false;
//        }
// 2016/03/18 N.Akaishi [V1.0 Del] End
            poUnitId = poUnitId.add(BigDecimal.ONE);
        }
        // 2016/03/18 N.Akaishi [V1.0 Mod] End
        return true;
    }

    /**
     * Create and Insert NLGI2200_01.
     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
     * @param wmsInbdPoVndT WMS_INBD_PO_VNDTMsg
     * @param poSeqNumber PO_SEQ_NUMBER
     * @param wmsPoDnldIntfcId Interface ID
     * @param wmsDescShortNm String WMS_WH.WMS_DESC_SHORT_NM
     * @return PO_SEQ_NUMBER
     */
// 2016/03/18 N.Akaishi [V1.0 Mod] Start
//    private BigDecimal createPoIf01(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, WMS_INBD_PO_VNDTMsg wmsInbdPoVndT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId) {
    private BigDecimal createPoIf01(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT, WMS_INBD_PO_VNDTMsg wmsInbdPoVndT, BigDecimal poSeqNumber, String wmsPoDnldIntfcId, String wmsDescShortNm) {
// 2016/03/18 N.Akaishi [V1.0 Mod] End

        NLGI2200_01TMsg nlgi220001T = new NLGI2200_01TMsg();
        ZYPEZDItemValueSetter.setValue(nlgi220001T.interfaceId, wmsPoDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.transactionId, poTrxId);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.unitId, poUnitId);
        poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.seqNumber, poSeqNumber);
// 2016/03/18 N.Akaishi [V1.0 Del] Start
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_01);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsSqNum, wmsInbdPoHdrT.wmsSqNum);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.intfcRecActCd, wmsInbdPoHdrT.intfcRecActCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.whCd, wmsInbdPoHdrT.whCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsCmpyCd, wmsInbdPoHdrT.wmsCmpyCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsPoId, wmsInbdPoHdrT.wmsPoId);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.vndCd, wmsInbdPoHdrT.vndCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsOrdTpCd, wmsInbdPoHdrT.wmsPrchTpCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsTrxCd, wmsInbdPoHdrT.wmsTrxCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsPoStsCd, wmsInbdPoHdrT.wmsPoStsCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsRqstDtTmTs, wmsInbdPoHdrT.poFromDtTmTs);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsVeslNm, wmsInbdPoHdrT.wmsVeslNm);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsBolNum, wmsInbdPoHdrT.wmsBolNum);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.inbdPoMsgTxt_01, wmsInbdPoHdrT.inbdPoMsgTxt_01);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.inbdPoMsgTxt_02, wmsInbdPoHdrT.inbdPoMsgTxt_02);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.inbdPoMsgTxt_03, wmsInbdPoHdrT.inbdPoMsgTxt_03);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.inbdPoMsgTxt_04, wmsInbdPoHdrT.inbdPoMsgTxt_04);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsSerNum, wmsInbdPoHdrT.wmsSerNum);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsVndNm_01, wmsInbdPoVndT.wmsVndNm_01);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.wmsVndNm_02, wmsInbdPoVndT.wmsVndNm_02);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.firstLineAddr, wmsInbdPoVndT.firstLineAddr);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.scdLineAddr, wmsInbdPoVndT.scdLineAddr);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.thirdLineAddr, wmsInbdPoVndT.thirdLineAddr);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.frthLineAddr, wmsInbdPoVndT.frthLineAddr);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.ctyAddr, wmsInbdPoVndT.ctyAddr);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.stCd, wmsInbdPoVndT.stCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.postCd, wmsInbdPoVndT.postCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.ctryCd, wmsInbdPoVndT.ctryCd);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.vndToCtacNm, wmsInbdPoVndT.vndToCtacNm);
//        ZYPEZDItemValueSetter.setValue(NLGI220001T.vndToCtacNum, wmsInbdPoVndT.vndToCtacNum);
// 2016/03/18 N.Akaishi [V1.0 Del] End
// 2016/03/18 N.Akaishi [V1.0 Add] Start
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplOrdId, wmsInbdPoHdrT.wmsPoId);
        String tplOrdTpTxt = null;
        if (ZYPCommonFunc.hasValue(wmsInbdPoHdrT.wmsTrxCd)) {
            if (WMS_TRX.REGULAR_SALES.equals(wmsInbdPoHdrT.wmsTrxCd.getValue())) {
                tplOrdTpTxt = VAL_TPL_ORD_TP_TXT_RMA;
            } else if (WMS_TRX.WAREHOUSE_TRANSFER_STOCK_IN.equals(wmsInbdPoHdrT.wmsTrxCd.getValue())) {
                tplOrdTpTxt = VAL_TPL_ORD_TP_TXT_TRF;
            } else {
                tplOrdTpTxt = VAL_TPL_ORD_TP_TXT_PO;
            }
        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        } else {
            tplOrdTpTxt = VAL_TPL_ORD_TP_TXT_PO;
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END
        }
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplOrdTpTxt, tplOrdTpTxt);

        String tplStoreKeyTxt = new String(wmsDescShortNm);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplStoreKeyTxt, tplStoreKeyTxt);
        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        //if (ZYPCommonFunc.hasValue(wmsInbdPoHdrT.intfcRecActCd)) {
        //    if (VAL_INTFC_REC_ACT_CD_U.equals(wmsInbdPoHdrT.intfcRecActCd.getValue())) {
        //        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplStageActCd, VAL_TPL_STAGE_ACT_CD_UPDATE);
        //    } else {
        //        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplStageActCd, VAL_TPL_STAGE_ACT_CD_NEW);
        //    }
        //}
        if (ZYPCommonFunc.hasValue(wmsInbdPoHdrT.intfcRecActCd) && VAL_INTFC_REC_ACT_CD_U.equals(wmsInbdPoHdrT.intfcRecActCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(nlgi220001T.tplStageActCd, VAL_TPL_STAGE_ACT_CD_UPDATE);
        } else {
            ZYPEZDItemValueSetter.setValue(nlgi220001T.tplStageActCd, VAL_TPL_STAGE_ACT_CD_NEW);
        }
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END
        StringBuilder wkTplOrdCmntTxt = new StringBuilder();
        wkTplOrdCmntTxt.append(wmsInbdPoHdrT.inbdPoMsgTxt_01.getValue()).append(VAL_SPACE)
                       .append(wmsInbdPoHdrT.inbdPoMsgTxt_02.getValue()).append(VAL_SPACE)
                       .append(wmsInbdPoHdrT.inbdPoMsgTxt_03.getValue()).append(VAL_SPACE)
                       .append(wmsInbdPoHdrT.inbdPoMsgTxt_04.getValue());
        String tplOrdCmntTxt = wkTplOrdCmntTxt.toString().trim();
        tplOrdCmntTxt = tplOrdCmntTxt.replaceAll(VAL_REGEX_PTRN_ORD_CMT_FROM, VAL_REGEX_PTRN_ORD_CMT_TO);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplOrdCmntTxt, ZYPCommonFunc.subByteString(tplOrdCmntTxt, ORD_CMNT_BYTE_LENGTH));
        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        //ZYPEZDItemValueSetter.setValue(nlgi220001T.tplReqDateTxt, dateFormat(wmsInbdPoHdrT.poFromDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_YYYYMMDD000000)); // 
        if (ZYPCommonFunc.hasValue(wmsInbdPoHdrT.poFromDtTmTs)) {
            ZYPEZDItemValueSetter.setValue(nlgi220001T.tplReqDateTxt, dateFormat(wmsInbdPoHdrT.poFromDtTmTs.getValue(), FMT_YYYYMMDDHHMMSS, FMT_DDMMMYYYY).toUpperCase());
        }
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END
        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        //String tplOrdLineId = wmsInbdPoDtlT.wmsSqNum.getValue().toString() +  String.format(wmsInbdPoDtlT.wmsLineNum.getValue().toPlainString(), VAL_0000);
        String tplOrdLineId = wmsInbdPoDtlT.wmsSqNum.getValue().toPlainString() + ZYPCommonFunc.leftPad(wmsInbdPoDtlT.wmsLineNum.getValue().toPlainString(), LG_WMS_LINE_NUM, VAL_ZERO);
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplOrdLineId, tplOrdLineId);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplItemCd, wmsInbdPoDtlT.wmsMdseCd);

        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        //if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.s80StkStsCd) && !VAL_S80_STK_STS_CD_1.equals(wmsInbdPoDtlT.s80StkStsCd.getValue().substring(1, 2))) {
        //    ZYPEZDItemValueSetter.setValue(nlgi220001T.tplHldCd, wmsInbdPoDtlT.s80StkStsCd);
        //}
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.s80StkStsCd)) {
            ZYPEZDItemValueSetter.setValue(nlgi220001T.tplHldCd,
                ZYPCommonFunc.subByteString(wmsInbdPoDtlT.s80StkStsCd.getValue(), IDX_FROM_S80_STK_STS_CD_TPL_HLD_CD, LG_CUT_S80_STK_STS_CD_TPL_HLD_CD));
        }
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.wmsOpenQty)) {
            ZYPEZDItemValueSetter.setValue(nlgi220001T.tplQtyOrdTxt, wmsInbdPoDtlT.wmsOpenQty.getValue().toPlainString());
        }
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplItemUomCd, WMS_UOM.EACH);
        // 05/31/2016 CITS N.Akaishi Mod QC9036 START
        if (ZYPCommonFunc.hasValue(wmsInbdPoDtlT.ezInTime)) {
            ZYPEZDItemValueSetter.setValue(nlgi220001T.tplUpdDateTxt, dateFormat(wmsInbdPoDtlT.ezInTime.getValue(), FMT_YYYYMMDDHHMMSSSSS, FMT_DDMMMYYYYHHMMSS).toUpperCase());
        }
        // 05/31/2016 CITS N.Akaishi Mod QC9036 END

        // QC#17919 Mod.
//        if (ZYPCommonFunc.hasValue(wmsInbdPoHdrT.wmsTrxCd)) {
            
//            if (WMS_TRX.REGULAR_SALES.equals(wmsInbdPoHdrT.wmsTrxCd.getValue()) || WMS_TRX.WAREHOUSE_TRANSFER_STOCK_IN.equals(wmsInbdPoHdrT.wmsTrxCd.getValue())) {
        String rwsNum = wmsInbdPoHdrT.rwsNum.getValue();
        String rwsLineNum = VAL_000 + wmsInbdPoDtlT.wmsLineNum.getValue();
        rwsLineNum = rwsLineNum.substring(rwsLineNum.length() - IDX_3);
        String tplSerNum = getRwsSer(glblCmpyCd, rwsNum, rwsLineNum);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplSerNum, tplSerNum);
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplProBillTxt, wmsInbdPoHdrT.wmsBolNum);
//            }
//        }
        ZYPEZDItemValueSetter.setValue(nlgi220001T.tplSwhCd, wmsInbdPoDtlT.rtlSwhCd);
// 2016/03/18 N.Akaishi [V1.0 Add] End

        EZDTBLAccessor.insert(nlgi220001T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(nlgi220001T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2200_01, TBL_WMS_INBD_RWS_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdPoHdrT.wmsPoId.getValue()) });
        }
        return poSeqNumber;
    }

// 2016/03/18 N.Akaishi [V1.0 Del] Start
//    /**
//     * Create and Insert NLGI2200_02.
//     * @param wmsInbdPoHdrT WMS_INBD_PO_HDRTMsg
//     * @param wmsInbdPoDtlTList WMS_INBD_PO_DTLTMsg List
//     * @param poSeqNumber PO_SEQ_NUMBER
//     * @param wmsPoDnldIntfcId Interface ID
//     * @return PO_SEQ_NUMBER
//     */
//    private BigDecimal createPoIf02(WMS_INBD_PO_HDRTMsg wmsInbdPoHdrT, List<WMS_INBD_PO_DTLTMsg> wmsInbdPoDtlTList, BigDecimal poSeqNumber, String wmsPoDnldIntfcId) {
//
//        for (WMS_INBD_PO_DTLTMsg wmsInbdPoDtlT : wmsInbdPoDtlTList) {
//            NLGI2200_02TMsg NLGI220002T = new NLGI2200_02TMsg();
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.interfaceId, wmsPoDnldIntfcId);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.transactionId, poTrxId);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.segmentId, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.unitId, poUnitId);
//            poSeqNumber = poSeqNumber.add(BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.seqNumber, poSeqNumber);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_02);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsSqNum, wmsInbdPoHdrT.wmsSqNum);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsPoId, wmsInbdPoHdrT.wmsPoId);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsLineNum, wmsInbdPoDtlT.wmsLineNum);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsMdseCd, wmsInbdPoDtlT.wmsMdseCd);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.hldCdTxt, NLXC014001.nullToEmpty(wmsInbdPoDtlT.s80StkStsCd.getValue()).replaceAll(VAL_STK_STS_CD_S, ""));
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsLineStsCd, wmsInbdPoDtlT.wmsLineStsCd);
//            if (BigDecimal.ZERO.compareTo(wmsInbdPoDtlT.wmsOpenQty.getValue()) == -1) {
//                ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsOpenQty, wmsInbdPoDtlT.wmsOpenQty);
//            }
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsEstDtTmTs, wmsInbdPoDtlT.wmsEstDtTmTs);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsInvId, wmsInbdPoDtlT.wmsInvId);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsDoId, wmsInbdPoDtlT.wmsDoId);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.cseFromNum, wmsInbdPoDtlT.cseFromNum);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.cseToNum, wmsInbdPoDtlT.cseToNum);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsBatNum, wmsInbdPoDtlT.wmsBatNum);
//            ZYPEZDItemValueSetter.setValue(NLGI220002T.wmsColloNum, wmsInbdPoDtlT.wmsColloNum);
//
//            EZDTBLAccessor.insert(NLGI220002T);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(NLGI220002T.getReturnCode())) {
//                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI2200_02, TBL_WMS_INBD_RWS_WRK //
//                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_PO_NUM) //
//                        , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdPoHdrT.wmsPoId.getValue()) });
//            }
//        }
//        return poSeqNumber;
//    }
// 2016/03/18 N.Akaishi [V1.0 Del] End

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

            rwsHdrT = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(rwsHdrT);
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
    private boolean doNewItemDnldProc(String rwsNum, String wmsWhCd, String wmsItemDnldIntfcId, String wmsDescShortNm) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(MAP_KEY_RWS_NUM, rwsNum);
            queryParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
            queryParam.put(MAP_KEY_WMS_ITEM_DNLD_INTFC_ID, wmsItemDnldIntfcId);
            queryParam.put(MAP_KEY_WMS_DESC_SHORT_NM, wmsDescShortNm);
            queryParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);

            stmt = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_NEW_ITEM, queryParam);
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
            queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(MAP_KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
            queryParam.put(MAP_KEY_WMS_ITEM_DNLD_STS_CD, WMS_ITEM_DNLD_STS.IN_COMPLETED);
            queryParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);

            stmt = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_FRCE_ITEM_LIST, queryParam);
            rs = stmt.executeQuery();

            boolean isRegistMdseTrxId = false;
            String prevWhCd = VAL_EMPTY;

            while (rs.next()) {
                String whCd = rs.getString(NLGC002001Constant.COL_WML_WH_CD);
                String mdseCd = rs.getString(NLGC002001Constant.COL_WML_MDSE_CD);
                String ezuptime = rs.getString(NLGC002001Constant.COL_WML_EZUPTIME);
                if (ZYPCommonFunc.hasValue(ezuptime)) {
                    ezuptime = dateFormat(ezuptime, FMT_YYYYMMDDHHMMSSSSS, FMT_YYYYMMDDHHMMSS);
                }

                String wmsItemDnldIntfcId = rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID);
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
                    updateWmsMdseErrMsgCd = NLGC002001.updateWmsMdseList(glblCmpyCd, whCd, mdseCd, WMS_ITEM_DNLD_STS.COMPLEATED, ezuptime);
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

            String whCd = rs.getString(COL_WML_WH_CD);
            String mdseCd = rs.getString(COL_WML_MDSE_CD);
            String wmsItemDnldIntfcId = rs.getString(COL_WMS_ITEM_DNLD_INTFC_ID);
            String wmsDescShortNm = rs.getString(COL_WMS_DESC_SHORT_NM);

            String invtyCatgCountCd = rs.getString(COL_ABC_ANLS_CLS_TAG_CD);
            BigDecimal cycleCntFreqNum = rs.getBigDecimal(COL_CYCLE_CNT_FREQ_DAYS_AOT);

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

            setWmsInbdItemData(wmsInbdItemWrkT, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, whCd, invtyCatgCountCd, cycleCntFreqNum);

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

            // 2016/03/18 N.Akaishi [V1.0 Mod] Start
            if (!registerMdse(wmsInbdMdseT, wmsInbdMdseUpcTList, wmsInbdMdseSerTList, wmsItemDnldIntfcId, wmsDescShortNm)){
                return false;
            }

            return updateAbcAnlsRsltTbl(glblCmpyCd, mdseCd, whCd);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return true;
    }

    /**
     * Item Download Process. Create and Insert WMS_INBD_MDSE Tables,
     * MDSE I/F Table. Update WMS_MDSE_LIST.
     */
    private void doItemDnldProc() {

        // 2016/03/18 N.Akaishi [V1.0 Add] Start
        doTrxItemDnldProc();

        // Force Item Download.
        doFrceItemDnldProc();

        // Get WMS_INBD_ITEM_WRK
        List wmsInbdItemWrkList = getWmsInbdItemWrkList();
        boolean isRegistMdseTrxId = false;
        String prevWhCd = VAL_EMPTY;

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
                String wmsDescShortNm = wmsWhT.wmsDescShortNm.getValue();

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
                    isRegistMdse = registerMdse(wmsInbdMdseT, wmsInbdMdseUpcTList, wmsInbdMdseSerTList, wmsItemDnldIntfcId, wmsDescShortNm);
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
    private static void setWmsInbdItemData(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT, List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList, List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList, String whCd, String invtyCatgCountCd, BigDecimal cycleCntFreqNum) {

        BigDecimal toWmsDataIfSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);

        // WMS_INBD_ITEM_WRK
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsSqNum, toWmsDataIfSq);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.whCd, whCd);

        //set AbcInfo
        if (ZYPCommonFunc.hasValue(invtyCatgCountCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.invtyCatgCountCd, invtyCatgCountCd);
        }
        if (ZYPCommonFunc.hasValue(cycleCntFreqNum)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.cycleCntFreqNum, cycleCntFreqNum);
        }

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
        queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(MAP_KEY_PROC_STS_CD, PROC_STS.IN_COMPLETED);
        queryParam.put(MAP_KEY_WMS_TRGT_WH_CD, trgtWhCdAry);
        queryParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);
        return this.ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_WMS_INBD_ITEM_WRK_LIST, queryParam);
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
        queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(MAP_KEY_PROC_STS_CD, PROC_STS.IN_COMPLETED);
        queryParam.put(MAP_KEY_WH_CD, whCd);
        queryParam.put(MAP_KEY_WMS_SQ_NUM, wmsSqNum);
        return this.ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_WMS_INBD_ITEM_UPC_WRK_LIST, queryParam);
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
        queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(MAP_KEY_PROC_STS_CD, PROC_STS.IN_COMPLETED);
        queryParam.put(MAP_KEY_WH_CD, whCd);
        queryParam.put(MAP_KEY_WMS_SQ_NUM, wmsSqNum);
        return this.ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_WMS_INBD_ITEM_SER_WRK_LIST, queryParam);
    }

    /**
     * Register MDSE I/F Tables
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
     * @param wmsItemDnldIntfcId Interface Id
     * @return isSuccess
     */
// 2016/03/18 N.Akaishi [V1.0 Mod] Start
    private boolean registerMdse(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList, String wmsItemDnldIntfcId, String wmsDescShortNm) {

        if (!ZYPCommonFunc.hasValue(mdseTrxId)) {
            mdseTrxId = s21TrxTblAccessor.getNextTransactionId();
        }

        BigDecimal mdseSeqNumber = BigDecimal.ZERO;
        mdseSeqNumber = createMdseIf01(wmsInbdMdseT, wmsInbdMdseUpcTList, mdseSeqNumber, wmsItemDnldIntfcId, wmsDescShortNm);
        if (!ZYPCommonFunc.hasValue(mdseSeqNumber)) {
            return false;
        }
        mdseUnitId = mdseUnitId.add(BigDecimal.ONE);
        return true;
    }

    /**
     * Create WMS_INBD_MDSE
     * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRKTMsg
     * @return WMS_INBD_MDSETMsg
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
        // 2016/03/18 N.Akaishi [V1.0 Mod] Start
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
        // 2016/03/18 N.Akaishi [V1.0 Mod] Start
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

        // 2016/03/18 N.Akaishi [V1.0 Add] Start
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
        // 2016/03/18 N.Akaishi [V1.0 Add] End

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
     * @return List<WMS_INBD_MDSE_UPCTMsg>
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
     * @return List<WMS_INBD_MDSE_SERTMsg>
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
     * Create NLGI3200_01
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param mdseSeqNumber MDSE SEQ_NUMBER
     * @param wmsItemDnldIntfcId Interface Id
     * @param wmsDescShortNm String 
     * @return BigDecimal
     */
    // 2016/03/18 N.Akaishi [V1.0 Mod] Start
//    private BigDecimal createMdseIf01(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, BigDecimal mdseSeqNumber, String wmsItemDnldIntfcId) {
    private BigDecimal createMdseIf01(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, BigDecimal mdseSeqNumber, String wmsItemDnldIntfcId, String wmsDescShortNm) {

        // Find record has WMS_UOM_CD = "EA".
        WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcEa = null;
        for (WMS_INBD_MDSE_UPCTMsg upcT : wmsInbdMdseUpcTList) {
            if (WMS_UOM.EACH.equals(upcT.wmsUomCd.getValue())) {
                wmsInbdMdseUpcEa = upcT;
            }
        }



        // Create NLGI3200_01 data.
        NLGI3200_01TMsg nlgi320001T = new NLGI3200_01TMsg();
        ZYPEZDItemValueSetter.setValue(nlgi320001T.interfaceId, wmsItemDnldIntfcId);
        ZYPEZDItemValueSetter.setValue(nlgi320001T.transactionId, mdseTrxId);
        ZYPEZDItemValueSetter.setValue(nlgi320001T.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(nlgi320001T.unitId, mdseUnitId);
        mdseSeqNumber = mdseSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(nlgi320001T.seqNumber, mdseSeqNumber);


        // 2016/03/18 N.Akaishi [V1.0 Add] Start
        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplStoreKeyTxt, wmsDescShortNm);
        // QC#17315 Add.
//        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplItemCd, wmsInbdMdseT.wmsMdseCd);
//        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplItemDescTxt, wmsInbdMdseT.wmsMdseDescTxt_01);
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.wmsMdseCd)) {
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplItemCd, convertDbsCharacter(wmsInbdMdseT.wmsMdseCd.getValue()));
        }
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.wmsMdseDescTxt_01)) {
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplItemDescTxt, convertDbsCharacter(wmsInbdMdseT.wmsMdseDescTxt_01.getValue()));
        }

        // QC#17396
        String wmsHazMatFlg = wmsInbdMdseT.wmsHazMatFlg.getValue();
        String serNumReqTpCd = wmsInbdMdseT.serNumReqTpCd.getValue();

        String tplItemTpTxt = VAL_EMPTY;
        if (ZYPConstant.FLG_ON_Y.equals(wmsHazMatFlg)) {
            tplItemTpTxt = VAL_TPL_ITEM_TP_TXT_HAZMAT;
        } else if("2".equals(serNumReqTpCd)) {
            tplItemTpTxt = VAL_TPL_ITEM_TP_TXT_EQUIPMENT;
        } else if ("2P".equals(wmsInbdMdseT.whCd.getValue())) {
            tplItemTpTxt = VAL_TPL_ITEM_TP_TXT_PARTS;
        } else {
            tplItemTpTxt = VAL_TPL_ITEM_TP_TXT_SUPPLIES;
        }
        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplItemTpTxt, tplItemTpTxt);

        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsUpcCd) && wmsInbdMdseUpcEa.wmsUpcCd.getValue().length() > ZERO) {
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplUpcCd, wmsInbdMdseUpcEa.wmsUpcCd);
        }
        BigDecimal wmsMdseWt = null;
        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsMdseWt)) {
            wmsMdseWt = wmsInbdMdseUpcEa.wmsMdseWt.getValue();
        }
        if (!ZYPCommonFunc.hasValue(wmsMdseWt) || wmsMdseWt.compareTo(VAL_BIGDECIMAL_0_001) < ZERO) {
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplStdGrsTxt, VAL_0_001);
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplStdNetTxt, VAL_0_001);
        } else {
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplStdGrsTxt, decimalFormat(wmsMdseWt, FMT_0_000));
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplStdNetTxt, decimalFormat(wmsMdseWt, FMT_0_000));
        }
        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplActvTxt, VAL_TPL_ACTV_TXT_ACTIVE);
        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.ezInTime)) {
            ZYPEZDItemValueSetter.setValue(nlgi320001T.tplCratDateTxt, dateFormat(wmsInbdMdseT.ezInTime.getValue(), FMT_YYYYMMDDHHMMSSSSS, FMT_DDMMMYYYY).toUpperCase());
        }
        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplHazMatFlg, wmsInbdMdseT.wmsHazMatFlg);
        // 2016/03/18 N.Akaishi [V1.0 Add] End
        ZYPEZDItemValueSetter.setValue(nlgi320001T.tplAbcCd, wmsInbdMdseT.invtyCatgCountCd);
        EZDTBLAccessor.insert(nlgi320001T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(nlgi320001T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI3200_01, TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_SQ_NUM, COL_WMS_ITEM_CD) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdMdseT.whCd.getValue(), wmsInbdMdseT.wmsSqNum.getValue().toPlainString(), wmsInbdMdseT.wmsMdseCd.getValue()) });
        }
        return mdseSeqNumber;
    }

// 2016/03/18 N.Akaishi [V1.0 Del] Start
//    /**
//     * Create NLGI3200_02
//     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
//     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
//     * @param mdseSeqNumber MDSE SEQ_NUMBER
//     * @param wmsItemDnldIntfcId Interface Id
//     * @return mdseSeqNumber
//     */
//    private BigDecimal createMdseIf02(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList, BigDecimal mdseSeqNumber, String wmsItemDnldIntfcId) {
//
//        for (WMS_INBD_MDSE_SERTMsg wmsInbdMdseSerT : wmsInbdMdseSerTList) {
//            // Create NLGI3200_02 data.
//            NLGI3200_02TMsg NLGI320002T = new NLGI3200_02TMsg();
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.interfaceId, wmsItemDnldIntfcId);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.transactionId, mdseTrxId);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.segmentId, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.unitId, mdseUnitId);
//            mdseSeqNumber = mdseSeqNumber.add(BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.seqNumber, mdseSeqNumber);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.wmsIntfcRecId, VAL_WMS_INTFC_REC_ID_02);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.wmsSqNum, wmsInbdMdseT.wmsSqNum);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.wmsMdseCd, wmsInbdMdseSerT.wmsMdseCd);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.serLineNum, wmsInbdMdseSerT.serLineNum);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.fromSerNum, wmsInbdMdseSerT.fromSerNum);
//            ZYPEZDItemValueSetter.setValue(NLGI320002T.toSerNum, wmsInbdMdseSerT.toSerNum);
//
//            EZDTBLAccessor.insert(NLGI320002T);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(NLGI320002T.getReturnCode())) {
//                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLGI3200_02, TBL_WMS_INBD_ITEM_WRK //
//                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_SQ_NUM, COL_WMS_ITEM_CD) //
//                        , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdMdseT.whCd.getValue(), wmsInbdMdseT.wmsSqNum.getValue().toString(), wmsInbdMdseT.wmsMdseCd.getValue()) });
//            }
//        }
//        return mdseSeqNumber;
//    }
// 2016/03/18 N.Akaishi [V1.0 Del] End

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
        
        WMS_MDSE_LISTTMsg updTMsg = (WMS_MDSE_LISTTMsg)EZDTBLAccessor.findByKeyForUpdateWait(wmsMdseListT);
        if(updTMsg !=null) {
            wmsMdseListT=updTMsg;
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.lastIntfcMdseUpdTs, lastIntfcMdseUpdTs);
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.COMPLEATED);
            EZDTBLAccessor.update(wmsMdseListT);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.lastIntfcMdseUpdTs, lastIntfcMdseUpdTs);
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.COMPLEATED);
            EZDTBLAccessor.create(wmsMdseListT);
        }
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
        String wmsInbdItemWkPk = wmsInbdItemWrkT.wmsInbdItemWrkPk.getValue().toPlainString();
        wmsInbdItemWrkT = (WMS_INBD_ITEM_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(wmsInbdItemWrkT);
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
                    , NLXCMsgHelper.toListedString(wmsInbdItemWrkT.glblCmpyCd, wmsInbdItemWrkT.wmsInbdItemWrkPk.getValue().toPlainString()) });
        }

        // WMS_INBD_ITEM_UPC_WRK
        for (int i = 0; i < wmsInbdItemUpcWrkList.size(); i++) {
            WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT = (WMS_INBD_ITEM_UPC_WRKTMsg) wmsInbdItemUpcWrkList.get(i);
            String wmsInbdItemUpcWkPk = wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk.getValue().toPlainString();
            wmsInbdItemUpcWrkT = (WMS_INBD_ITEM_UPC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(wmsInbdItemUpcWrkT);
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
                        , NLXCMsgHelper.toListedString(wmsInbdItemUpcWrkT.glblCmpyCd, wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk.getValue().toPlainString()) });
            }
        }

        // WMS_INBD_ITEM_SER_WRK
        for (int i = 0; i < wmsInbdItemSerWrkList.size(); i++) {
            WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT = (WMS_INBD_ITEM_SER_WRKTMsg) wmsInbdItemSerWrkList.get(i);
            String wmsInbdItemSerWkPk = wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk.getValue().toPlainString();
            wmsInbdItemSerWrkT = (WMS_INBD_ITEM_SER_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(wmsInbdItemSerWrkT);
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
                        , NLXCMsgHelper.toListedString(wmsInbdItemSerWrkT.glblCmpyCd, wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk.getValue().toPlainString()) });
            }
        }
        return true;
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
        ssmParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MAP_KEY_RWS_NUM, rwsNum);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 START */
        ssmParam.put(MAP_KEY_FLG_Y, ZYPConstant.FLG_ON_Y);
        /* 07/03/2017 CSAI Y.Imazu Add QC#19720 END */

        List<Map<String, Object>> rwsDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsDtl", ssmParam);

        return rwsDtlList;
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
     * getShpgPlnItemList
     * @param String pGlblCmpyCd glblCmpyCd
     * @param String wmsWhCd
     * @return List<String>
     */
    private List<String> getShpgPlnItemList(String pGlblCmpyCd, String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(MAP_KEY_GLBL_CMPY_CD, pGlblCmpyCd);
        ssmParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
        ssmParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);
        List<String> itemList = (List<String>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_SHPG_PLN_ITEM_LIST, ssmParam);

        return itemList;
    }

    /**
     * getMrpItemList
     * @param String pGlblCmpyCd glblCmpyCd
     * @param String wmsWhCd
     * @return List<String>
     */
    private List<String> getMrpItemList(String glblCmpyCd, String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
        ssmParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);
        List<String> itemList = (List<String>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_MRP_ITEM_LIST, ssmParam);

        return itemList;
    }

    /**
     * getPoItemList
     * @param String glblCmpyCd
     * @param String wmsWhCd
     * @return List<String>
     */
    private List<String> getPoItemList(String glblCmpyCd, String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
        ssmParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);
        List<String> itemList = (List<String>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_PO_ITEM_LIST, ssmParam);

        return itemList;
    }

    /**
     * Force Item Download Process. Call create WMS_INBD_MDSE tables
     * process and Update WMS_MDSE_LIST.
     */
    private void doTrxItemDnldProc() {
        String doShpgPlnItemDnld = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NLGB022001_SHPG_PLN_ITEM_DNLD, glblCmpyCd);
        String doMrpItemDnld = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NLGB022001_MRP_ITEM_DNLD, glblCmpyCd);
        String doPoItemDnld = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NLGB022001_PO_ITEM_DNLD, glblCmpyCd);

        for (int i = 0; i < trgtWhCdAry.length; i++) {
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
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_MDSE_LIST, TBL_SHPG_PLN
                                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_MDSE_CD, COL_WH_CD)
                                    , NLXCMsgHelper.toListedString(glblCmpyCd, item, wmsWhCd) });
                        }
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
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_MDSE_LIST, TBL_MRP_INFO
                                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_MDSE_CD, COL_WH_CD)
                                    , NLXCMsgHelper.toListedString(glblCmpyCd, item, wmsWhCd) });
                        }
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
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_MDSE_LIST, TBL_PO
                                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_MDSE_CD, COL_WH_CD)
                                    , NLXCMsgHelper.toListedString(glblCmpyCd, item, wmsWhCd) });
                        }
                    }
                }
            }
            commit();
        }
    }

    /**
     * Register WMS_INBD_SO_HDR.
     * @param String procNm
     * @return true / success, false / error
     */
    private boolean updateWmsIntfcCtrl(String procNm) {

        for (String whCd : trgtWhCdAry) {

            WMS_INTFC_CTRLTMsg inMsg = new WMS_INTFC_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.whCd, whCd);
            ZYPEZDItemValueSetter.setValue(inMsg.wmsIntfcTaskNm, procNm);

            WMS_INTFC_CTRLTMsg updMsg = (WMS_INTFC_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            String sysDate = ZYPDateUtil.getCurrentSystemTime(FMT_YYYYMMDDHHMMSS);
            if (updMsg == null) {
                ZYPEZDItemValueSetter.setValue(inMsg.taskDtTmTs, sysDate);
                EZDTBLAccessor.create(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INTFC_CTRL});
                }
            } else {
                ZYPEZDItemValueSetter.setValue(updMsg.taskDtTmTs, sysDate);
                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INTFC_CTRL});
                }
            }
        }
        return true;
    }

    /**
     * get Serial# from RWS_SER
     * @param String glblCmpyCd
     * @param String rwsNum
     * @param String rwsLineNum
     * @return String RWS_SER.SER_NUM
     */
    private String getRwsSer(String glblCmpyCd, String rwsNum, String rwsLineNum) {

        String rwsSer = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(MAP_KEY_RWS_NUM, rwsNum);
        queryParam.put(MAP_KEY_RWS_LINE_NUM, rwsLineNum);

        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_RWS_SER, queryParam);
        if (resultMapList != null && !resultMapList.isEmpty()) {
            Map<String, Object> resultMap = (Map<String, Object>) resultMapList.get(0);
            rwsSer = (String) resultMap.get(COL_SER_NUM);
        }

        return rwsSer;
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
        queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(MAP_KEY_MDSE_CD, mdseCd);
        queryParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
        queryParam.put(MAP_KEY_FLAG, ZYPConstant.FLG_ON_Y);

        BigDecimal abcAnlsRsltPk = (BigDecimal) ssmBatchClient.queryObject(SQL_STMT_ID_GET_PRIMARY_KEY_FOR_ABC_ANLS_RSLT, queryParam);
        
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
                throw new S21AbendException(NLGM0007E, new String[] {TBL_ABC_ANLS_RSLT, COL_ABC_ANLS_RSLT_PK, abcAnlsRsltPk.toPlainString() });
            }
        }

        return true;
    }

    /**
     * convertDbsCharacter
     * 
     * @param str convertDbsCharacter
     * @return String
     */
    private String convertDbsCharacter(String str) {

        // 'É' replace to 'E'
        str = str.replaceAll(VAL_DBS_CHAR_01, VAL_DBS_CONV_CHAR_01);
        // 'é' replace to 'e'
        str = str.replaceAll(VAL_DBS_CHAR_02, VAL_DBS_CONV_CHAR_02);
        // TAB replace to ' ' (with one space)
        str = str.replaceAll(VAL_DBS_CHAR_03, VAL_DBS_CONV_CHAR_03);
        // LINE FEED(LF) replace to ' ' (with one space)
        str = str.replaceAll(VAL_DBS_CHAR_04, VAL_DBS_CONV_CHAR_03);
        // Carriage Return(CR) remove (with NULL)
        str = str.replaceAll(VAL_DBS_CHAR_05, VAL_DBS_CONV_CHAR_04);

        return str;
    }
    /**
     * checkNewItem
     * @param rwsNum String
     * @return true / exist new item, false / not exist new item
     */
    private boolean checkNewItem(String rwsNum, String wmsWhCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MAP_KEY_RWS_NUM, rwsNum);
        ssmParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
        BigDecimal ret = (BigDecimal) ssmBatchClient.queryObject(SQL_STMT_ID_IS_EXISTS_WMS_MDSE, ssmParam);

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
        inMsg.setConditionValue(MAP_KEY_GLBL_CMPY_CD_01, glblCmpyCd);
        inMsg.setConditionValue(MAP_KEY_RWS_NUM_01, rwsNum);
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

}
