/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB013001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;

import business.db.NLCI0050_01TMsg;
import business.db.STK_STSTMsg;
import business.db.WMS_INBD_INVTYTMsg;
import business.db.WMS_INBD_TRXTMsg;

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
 * Translate Inventory Reconciliation from WMS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 12/18/2015   CSAI            K.Lee           Update          NA Initial
 * 02/21/2017   CSAI            T.Kikuhara      Update          QC#17699
 * 03/06/2017   CITS            Y.Fujii         Update          QC#17916
 * 10/23/2017   CITS            T.Wada          Update          QC#21969
 * 02/26/2018   CITS            K.Ogino         Update          QC#24273
 * 06/05/2018   CITS            Y.Iwasaki       Update          QC#24273
 * 08/26/2019   CITS            M.Naito         Update          QC#53025
 * 10/13/2019   CITS            K.Ogino         Update          QC#57841
 *</pre>
 */
public class NLGB013001 extends S21BatchMain implements NLGB013001Constant {

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd = null;

    /** User Profile */
    private S21UserProfileService profile = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor s21TrxTblAccessor = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Process Date */
    private String procDate = null;

    /** Warehouse Group Code */
    private String whGpCd = null;
    
    /** Warehouse Code Prefix */
    private String prefixWhCd = null;

    /** Target Warehouse Code */
    private String[] trgtWhCdAry = null;

    /** Error Message List */
    private ArrayList<Map<String, String>> errMsgList = null;

    /**
     * WMS_PACK_CD_SET_OWNER_CD_FLG
     */
    private String wmsPackCdSetOwnerCdFlg;

    @Override
    protected void initRoutine() {

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        s21TrxTblAccessor = new S21TransactionTableAccessor();
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

        // Process Date
        procDate = S21BatchUtil.getUserVariable1();
        if (!ZYPCommonFunc.hasValue(procDate)) {
            procDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        } else if (!NLGC001001.isDateFormat(procDate, FMT_YYYYMMDD)) {
            throw new S21AbendException(NLGM0060E, new String[] {procDate, PRAM_NM_PROCESS_DATE });
        }

        whGpCd = S21BatchUtil.getUserVariable2();
        if (!ZYPCommonFunc.hasValue(whGpCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_WH_GRP_CD });
        }

        prefixWhCd = S21BatchUtil.getUserVariable3();

        wmsPackCdSetOwnerCdFlg = ZYPCodeDataUtil.getVarCharConstValue("WMS_PACK_CD_SET_OWNER_CD_FLG", glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {

        try {
            trgtWhCdAry = NLGC001001.getTrgtWhCd(glblCmpyCd, whGpCd, prefixWhCd);
            if (trgtWhCdAry == null) {
                outputErr(NLGM0047E, new String[] {whGpCd });
                return;
            }

            doProcInvty();
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
        new NLGB013001().executeBatch(NLGB013001.class.getSimpleName());
    }

    /**
     * Translate Inventory from WMS
     */
    private void doProcInvty() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Get Inventory data from WMS_INBD_TRX.
            // This sql ignore PROC_STS_CD for rerun.
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
            queryParam.put("wmsTaskCd", WMS_TASK.RCNR);
            queryParam.put("wmsTrgtWhCd", trgtWhCdAry);
            String condWmsTrxDtTmTs = procDate;
            String selectWmsTrxDtTmTs = procDate;
            if (MW_REPL_TRG_GRP_CD_TECSYS.equals(whGpCd)) {
                condWmsTrxDtTmTs = ZYPDateUtil.addDays(procDate, -VAL_TRGT_WMS_TRX_DT);
                selectWmsTrxDtTmTs = ZYPDateUtil.addDays(procDate, -VAL_TRGT_WMS_TRX_DT);
            } else if (MW_REPL_TRG_GRP_CD_DBS.equals(whGpCd)) {
                condWmsTrxDtTmTs = procDate;
                selectWmsTrxDtTmTs = ZYPDateUtil.addDays(procDate, -VAL_TRGT_WMS_TRX_DT);
            }
            queryParam.put("condWmsTrxDtTmTs", condWmsTrxDtTmTs);
            queryParam.put("selectWmsTrxDtTmTs", selectWmsTrxDtTmTs);
            stmt = ssmLLClient.createPreparedStatement("getProcInvty", queryParam);
            rs = stmt.executeQuery();

            BigDecimal invtyTrxId = null;
            BigDecimal invtyUnitId = BigDecimal.ONE;
            BigDecimal onHandQty = BigDecimal.ZERO;

            boolean isRegistInvtyIf = false;
            boolean isUpdateWmsInbdTrx = false;
            String prevPkgCd = null;
            String prevWmsMdseCd = null;
            String prevStkStsCd = null;
            WMS_INBD_INVTYTMsg prevWmsInbdInvtyT = null;
            List<WMS_INBD_TRXTMsg> condMsgList = new ArrayList<WMS_INBD_TRXTMsg>();

            while (rs.next()) {

                String curPkgCd = rs.getString(COL_WMS_PKG_CD);
                String curWmsMdseCd = rs.getString(COL_WMS_MDSE_CD);
                String curStkStsCd = rs.getString(COL_WMS_STK_STS_CD);

                // START 2019/08/26 M.Naito [QC#53025,ADD]
                // check stock status code
                if (!ZYPCommonFunc.hasValue(curStkStsCd) || !chkStkStsCd(curStkStsCd)) {
                    totalErrCount++;
                    updTrxProcSts(rs.getBigDecimal(COL_WMS_INBD_TRX_PK), PROC_STS.COMPLEATED, NLGM0086E);
                    outputErr(NLGM0086E, new String[] {rs.getBigDecimal(COL_WMS_INBD_TRX_PK).toString(), curWmsMdseCd, rs.getString(COL_WH_CD) });
                    continue;
                }
                // END 2019/08/26 M.Naito [QC#53025,ADD]

                // QC#21969
                String wmsCntnrId = rs.getString(COL_WMS_CNTNR_ID);
                if(ZYPCommonFunc.hasValue(wmsCntnrId) && !EZDCommonFunc.isNumberCheck(wmsCntnrId)){
                    WMS_INBD_TRXTMsg condMsg = new WMS_INBD_TRXTMsg();
                    ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, glblCmpyCd);
                    // QC#57841 Mod Start
                    ZYPEZDItemValueSetter.setValue(condMsg.wmsInbdTrxPk, rs.getBigDecimal(COL_WMS_INBD_TRX_PK));
//                    ZYPEZDItemValueSetter.setValue(condMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsRecId, rs.getString(COL_WMS_REC_ID));
//                    ZYPEZDItemValueSetter.setValue(condMsg.whCd, rs.getString(COL_WH_CD));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsTaskCd, WMS_TASK.RCNR);
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsMdseCd, rs.getString(COL_WMS_MDSE_CD));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsStkStsCd, rs.getString(COL_WMS_STK_STS_CD));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsResrcTxt, rs.getString(COL_WMS_RESRC_TXT));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsBatId, rs.getString(COL_WMS_BAT_ID));
                    // QC#57841 Mod End
                    totalErrCount++;
                    updateWmsInbdTrx(condMsg, DETAIL_ERROR);
                    outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_INVTY, TBL_WMS_INBD_TRX, COL_WMS_CNTNR_ID, wmsCntnrId});
                    continue;
                }

                // QC#17699 START
                if (!ZYPCommonFunc.hasValue(curPkgCd) || COL_WMS_PKG_CD_MIN_LENGTH > curPkgCd.length()) {
                    WMS_INBD_TRXTMsg condMsg = new WMS_INBD_TRXTMsg();
                    ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, glblCmpyCd);
                    // QC#57841 Mod Start
                    ZYPEZDItemValueSetter.setValue(condMsg.wmsInbdTrxPk, rs.getBigDecimal(COL_WMS_INBD_TRX_PK));
//                    ZYPEZDItemValueSetter.setValue(condMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsRecId, rs.getString(COL_WMS_REC_ID));
//                    ZYPEZDItemValueSetter.setValue(condMsg.whCd, rs.getString(COL_WH_CD));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsTaskCd, WMS_TASK.RCNR);
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsMdseCd, rs.getString(COL_WMS_MDSE_CD));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsStkStsCd, rs.getString(COL_WMS_STK_STS_CD));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsResrcTxt, rs.getString(COL_WMS_RESRC_TXT));
//                    ZYPEZDItemValueSetter.setValue(condMsg.wmsBatId, rs.getString(COL_WMS_BAT_ID));
                    // QC#57841 Mod End
                    totalErrCount++;
                    updateWmsInbdTrx(condMsg, DETAIL_ERROR);
                    outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_INVTY, TBL_WMS_INBD_TRX, COL_WMS_PKG_CD, curPkgCd});
                    continue;
                }
                // QC#17699 END

                if (ZYPCommonFunc.hasValue(prevPkgCd) && ZYPCommonFunc.hasValue(prevWmsMdseCd) && ZYPCommonFunc.hasValue(prevStkStsCd)) {
                    if ( !prevPkgCd.equals(curPkgCd) || !prevWmsMdseCd.equals(curWmsMdseCd) || !prevStkStsCd.equals(curStkStsCd)) {
                        isRegistInvtyIf = createAlci005001(rs, prevWmsInbdInvtyT, invtyTrxId, invtyUnitId, onHandQty);

                        for (int i = 0; i < condMsgList.size(); i++) {
                            if (isRegistInvtyIf) {
                                // Except condition of WMS_RESRC_TXT.
                                if (i==0 || isUpdateWmsInbdTrx) {
                                   isUpdateWmsInbdTrx = updateWmsInbdTrx(condMsgList.get(i), PROC_STS.COMPLEATED);
                                }
                            } else {
                                rollback();
                                isUpdateWmsInbdTrx = updateWmsInbdTrx(condMsgList.get(i), PROC_STS.ERROR);
                            }
                        }
                        condMsgList = new ArrayList<WMS_INBD_TRXTMsg>();
                        if (isUpdateWmsInbdTrx) {
                            commit();
                            if (isRegistInvtyIf) {
                                totalCommitCount++;
                            } else {
                                totalErrCount++;
                            }
                        } else {
                            rollback();
                            totalErrCount++;
                        }
                        onHandQty = BigDecimal.ZERO;
                    }
                }

                if (!ZYPCommonFunc.hasValue(invtyTrxId)) {
                    invtyTrxId = s21TrxTblAccessor.getNextTransactionId();
                }


                WMS_INBD_INVTYTMsg wmsInbdInvtyT = createWmsInbdInvty(rs);
                if (wmsInbdInvtyT != null) {
                    onHandQty = onHandQty.add(wmsInbdInvtyT.allOhQty.getValue());
                    prevWmsInbdInvtyT = wmsInbdInvtyT;
                }

                invtyUnitId = invtyUnitId.add(BigDecimal.ONE);

                // Update conditions.
                WMS_INBD_TRXTMsg condMsg = new WMS_INBD_TRXTMsg();
                ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, glblCmpyCd);
                // QC#57841 Mod Start
                ZYPEZDItemValueSetter.setValue(condMsg.wmsInbdTrxPk, rs.getBigDecimal(COL_WMS_INBD_TRX_PK));
//                ZYPEZDItemValueSetter.setValue(condMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
//                ZYPEZDItemValueSetter.setValue(condMsg.whCd, rs.getString(COL_WH_CD));
//                ZYPEZDItemValueSetter.setValue(condMsg.wmsTaskCd, WMS_TASK.RCNR);
//                ZYPEZDItemValueSetter.setValue(condMsg.wmsMdseCd, rs.getString(COL_WMS_MDSE_CD));
//                ZYPEZDItemValueSetter.setValue(condMsg.wmsStkStsCd, rs.getString(COL_WMS_STK_STS_CD));
//                ZYPEZDItemValueSetter.setValue(condMsg.wmsResrcTxt, rs.getString(COL_WMS_RESRC_TXT));
//                ZYPEZDItemValueSetter.setValue(condMsg.wmsBatId, rs.getString(COL_WMS_BAT_ID));
//                // QC#24273
//                ZYPEZDItemValueSetter.setValue(condMsg.wmsRecId, rs.getString(COL_WMS_REC_ID));
                // QC#57841 Mod End
                condMsgList.add(condMsg);

                prevPkgCd = curPkgCd;
                prevWmsMdseCd = curWmsMdseCd;
                prevStkStsCd = curStkStsCd;
            }

            if (prevWmsInbdInvtyT != null) {
                isRegistInvtyIf = createAlci005001(rs, prevWmsInbdInvtyT, invtyTrxId, invtyUnitId, onHandQty);
            }

            for (int i = 0; i < condMsgList.size(); i++) {
                if (isRegistInvtyIf) {
                    // Except condition of WMS_RESRC_TXT.
                    if (i==0 || isUpdateWmsInbdTrx) {
                        isUpdateWmsInbdTrx = updateWmsInbdTrx(condMsgList.get(i), PROC_STS.COMPLEATED);
                    }
                } else {
                    rollback();
                    isUpdateWmsInbdTrx = updateWmsInbdTrx(condMsgList.get(i), PROC_STS.ERROR);
                }
            }

            if (condMsgList.size() > 0 ) {
                if (isUpdateWmsInbdTrx) {
                    commit();
                    if (isRegistInvtyIf) {
                        totalCommitCount++;
                    } else {
                        totalErrCount++;
                    }
                } else {
                    rollback();
                    totalErrCount++;
                }
            }

            if (totalCommitCount > 0) {
                s21TrxTblAccessor.createIntegrationRecordForBatch(VAL_INTERFACE_ID_INVTY, invtyTrxId);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
            rollback();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Create and Insert WMS_INBD_INVTY.
     * @param rs ResultSet
     * @return WMS_INBD_INVTYTMsg
     * @throws SQLEXception
     */
    private WMS_INBD_INVTYTMsg createWmsInbdInvty(ResultSet rs) throws SQLException {

        WMS_INBD_INVTYTMsg wmsInbdInvtyT = new WMS_INBD_INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsInbdInvtyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_INVTY_SQ));
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsRecId, VAL_WMS_REC_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsRecTpId, VAL_WMS_REC_TP_ID);
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsRecSq, new BigDecimal(rs.getString(COL_WMS_REC_ID)));
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.whCd, rs.getString(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsInvtyDt, rs.getString(COL_INVTY_DT));
        wmsInbdInvtyT.wmsInvtyTm.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsMdseCd, rs.getString(COL_WMS_MDSE_CD));
        if (ZYPCommonFunc.hasValue(rs.getString(COL_WMS_STK_STS_CD))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.s80StkStsCd, VAL_S80_STK_STS_CD_PFX + rs.getString(COL_WMS_STK_STS_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.s80StkStsCd, VAL_S80_STK_STS_CD_PFX + VAL_S80_STK_STS_CD);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.allOhQty, rs.getBigDecimal(COL_WMS_ORD_QTY));
        wmsInbdInvtyT.allNohQty.clear();
        wmsInbdInvtyT.inShipQty.clear();
        wmsInbdInvtyT.inRcvQty.clear();
        wmsInbdInvtyT.ohHldQty.clear();
        wmsInbdInvtyT.ohNoHldQty.clear();
        wmsInbdInvtyT.wmsSpndQty.clear();
        wmsInbdInvtyT.ordNotAllocQty.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.intfcTpId, VAL_INTFC_TP_ID);
        wmsInbdInvtyT.allNohQty.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.wmsResrcTxt, rs.getString(COL_WMS_RESRC_TXT));
        if (ZYPCommonFunc.hasValue(rs.getString(COL_WHMM_WMS_MDSE_CD))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.hostMdseCd, rs.getString(COL_HOST_MDSE_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.hostMdseCd, rs.getString(COL_WMS_MDSE_CD));
        }
        String wmsPkgCd = rs.getString(COL_WMS_PKG_CD);

        String rtlWhCd = null;
        if (ZYPCommonFunc.hasValue(wmsPackCdSetOwnerCdFlg) && wmsPackCdSetOwnerCdFlg.equals(ZYPConstant.FLG_ON_Y)) {
            rtlWhCd = getRtlWhCdByOwnrCd(glblCmpyCd, wmsPkgCd.substring(0,3), wmsInbdInvtyT.whCd.getValue());
        } else {
            rtlWhCd = wmsPkgCd.substring(0,3);
        }

        String rtlSWhCd  = wmsPkgCd.substring(3,6);
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.rtlSwhCd, rtlSWhCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.serNum, rs.getString(COL_SER_NUM));
        // QC#21969
        ZYPEZDItemValueSetter.setValue(wmsInbdInvtyT.svcConfigMstrPk, rs.getBigDecimal(COL_WMS_CNTNR_ID));

        EZDTBLAccessor.insert(wmsInbdInvtyT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdInvtyT.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_INVTY, TBL_WMS_INBD_TRX //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_MDSE_CD, COL_WMS_STK_STS_CD, COL_WMS_RESRC_TXT) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, rs.getString(COL_WH_CD), rs.getString(COL_WMS_MDSE_CD), rs.getString(COL_WMS_STK_STS_CD), rs.getString(COL_WMS_RESRC_TXT)) });
        }

        return wmsInbdInvtyT;
    }

    /**
     * Create and Insert NLCI0050_01.
     * @param rs ResultSet
     * @param wmsInbdInvtyT WMS_INBD_INVTYTMsg
     * @param invtyTrxId Transaction Id
     * @param invtyUnitId Unit Id
     * @return isSuccess
     * @throws SQLEXception
     */
    private boolean createAlci005001(ResultSet rs, WMS_INBD_INVTYTMsg wmsInbdInvtyT, BigDecimal invtyTrxId, BigDecimal invtyUnitId, BigDecimal onHandQty) throws SQLException {

        NLCI0050_01TMsg alci005001T = new NLCI0050_01TMsg();
        ZYPEZDItemValueSetter.setValue(alci005001T.interfaceId, VAL_INTERFACE_ID_INVTY);
        ZYPEZDItemValueSetter.setValue(alci005001T.transactionId, invtyTrxId);
        ZYPEZDItemValueSetter.setValue(alci005001T.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(alci005001T.unitId, invtyUnitId);
        ZYPEZDItemValueSetter.setValue(alci005001T.seqNumber, BigDecimal.ONE);
        //ZYPEZDItemValueSetter.setValue(alci005001T.warehouseCodeIf, wmsInbdInvtyT.whCd);
        ZYPEZDItemValueSetter.setValue(alci005001T.warehouseCodeIf, wmsInbdInvtyT.rtlWhCd);
        String sqVal = VAL_TRANSACTION_SEQUENCE_IF_PFX + wmsInbdInvtyT.wmsRecSq.getValue().toString();
        ZYPEZDItemValueSetter.setValue(alci005001T.transactionSequenceIf, sqVal.substring(sqVal.length() - LEN_TRANSACTION_SEQUENCE_IF));
        ZYPEZDItemValueSetter.setValue(alci005001T.recordTypeIf, VAL_RECORD_TYPE_IF);
        ZYPEZDItemValueSetter.setValue(alci005001T.detailTypeIf, VAL_DETAIL_TYPE_IF);
        alci005001T.dataActionCodeIf.clear();
        ZYPEZDItemValueSetter.setValue(alci005001T.companyCodeIf, VAL_COMPANY_CODE_IF);
        //ZYPEZDItemValueSetter.setValue(alci005001T.warehouseCode2If, wmsInbdInvtyT.whCd);
        ZYPEZDItemValueSetter.setValue(alci005001T.warehouseCode2If, wmsInbdInvtyT.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(alci005001T.closedDateIf, ZYPDateUtil.DateFormatter(wmsInbdInvtyT.wmsInvtyDt.getValue(), FMT_YYYYMMDD, FMT_YYYY_MM_DD));
        if (ZYPCommonFunc.hasValue(wmsInbdInvtyT.hostMdseCd)) {
            ZYPEZDItemValueSetter.setValue(alci005001T.itemCodeIf, wmsInbdInvtyT.hostMdseCd);
        } else {
            ZYPEZDItemValueSetter.setValue(alci005001T.itemCodeIf, wmsInbdInvtyT.wmsMdseCd);
        }
        ZYPEZDItemValueSetter.setValue(alci005001T.stockStatusIf, wmsInbdInvtyT.s80StkStsCd);
        ZYPEZDItemValueSetter.setValue(alci005001T.onHandNoHoldIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(alci005001T.onHandHoldIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(alci005001T.receivingOnStageIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(alci005001T.shippingOnStageIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(alci005001T.allOfNoOnHandIf, BigDecimal.ZERO);
        //ZYPEZDItemValueSetter.setValue(alci005001T.allOfOnHandIf, wmsInbdInvtyT.allOhQty);
        ZYPEZDItemValueSetter.setValue(alci005001T.allOfOnHandIf, onHandQty);
        ZYPEZDItemValueSetter.setValue(alci005001T.orderNotAllocatedIf, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(alci005001T.suspendIf, BigDecimal.ZERO);

        EZDTBLAccessor.insert(alci005001T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(alci005001T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLCI0050_01, TBL_WMS_INBD_TRX //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_MDSE_CD, COL_WMS_STK_STS_CD, COL_WMS_RESRC_TXT) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, rs.getString(COL_WH_CD), rs.getString(COL_WMS_MDSE_CD), rs.getString(COL_WMS_STK_STS_CD), rs.getString(COL_WMS_RESRC_TXT)) });
        }
        return true;
    }

    /**
     * Update PROC_STS_CD of WMS_INBD_TRX.
     * @param rs ResultSet
     * @param procStsCd PROC_STS_CD
     * @return isSuccess
     * @throws SQLEXception
     */
    private boolean updateWmsInbdTrx(WMS_INBD_TRXTMsg condMsg, String procStsCd) throws SQLException {

        // Except condition of WMS_RESRC_TXT. mod QC#24273,
        // QC#57841 Mod Start
//        String[] condList = new String[] {KEY_GLBL_CMPY_CD, KEY_EZCANCELFLAG, KEY_WH_CD, KEY_WMS_TASK_CD, KEY_WMS_MDSE_CD, KEY_WMS_STK_STS_CD, KEY_WMS_BAT_ID, KEY_WMS_REC_ID };
        String[] condList = new String[] {KEY_GLBL_CMPY_CD, KEY_WMS_INBD_TRX_PK };

        WMS_INBD_TRXTMsg inMsg = new WMS_INBD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.procStsCd, procStsCd);
        String[] updtList = new String[] {KEY_PROC_STS_CD };

        // QC#17699 START
        if (DETAIL_ERROR.equals(procStsCd)) {
            ZYPEZDItemValueSetter.setValue(inMsg.procStsCd, PROC_STS.ERROR);
            ZYPEZDItemValueSetter.setValue(inMsg.errMsgCd, NLGM0045E);
            // QC#24273
//            condList = new String[] {KEY_GLBL_CMPY_CD, KEY_EZCANCELFLAG, KEY_WMS_REC_ID, KEY_WH_CD, KEY_WMS_TASK_CD, KEY_WMS_MDSE_CD, KEY_WMS_STK_STS_CD, KEY_WMS_BAT_ID, KEY_WMS_REC_ID };
            condList = new String[] {KEY_GLBL_CMPY_CD, KEY_WMS_INBD_TRX_PK };
            updtList = new String[] {KEY_PROC_STS_CD, KEY_ERR_MSG_CD};
        }
        // QC#17699 END
        // QC#57841 Mod End

        EZDTBLAccessor.updateByPartialValue(condMsg, condList, inMsg, updtList);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NLGM0046E, new String[] {TBL_WMS_INBD_TRX //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_TASK_CD, COL_WMS_MDSE_CD //
                            , COL_WMS_STK_STS_CD, COL_WMS_RESRC_TXT, COL_WMS_BAT_ID) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, condMsg.whCd.getValue(), WMS_TASK.RCNR, condMsg.wmsMdseCd.getValue() //
                            , condMsg.wmsStkStsCd.getValue(), condMsg.wmsResrcTxt.getValue(), condMsg.wmsBatId.getValue(), condMsg.wmsPkgCd.getValue()) });
        }
        return true;
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
     * getDsRwsDtl
     * @param String glblCmpyCd, String rwsNum
     * @return DS_RWS_DTLTMsg
     */
    private String getRtlWhCdByOwnrCd(String glblCmpyCd, String invtyOwnrCd,String wmsWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invtyOwnrCd", invtyOwnrCd);
        ssmParam.put("wmsWhCd", wmsWhCd);
        String rtlWhCd = (String) ssmBatchClient.queryObject("getRtlWhCdByOwnrCd", ssmParam);

        return rtlWhCd;
    }

    // START 2019/08/26 M.Naito [QC#53025,ADD]
    /**
     * chkStkStsCd
     * @param String stkStsCd
     * @return STK_STSTMsg
     */
    private boolean chkStkStsCd(String stkStsCd) {
        STK_STSTMsg stkStsTMsg = (STK_STSTMsg) ZYPCodeDataUtil.findByCode("STK_STS", glblCmpyCd, stkStsCd);
        if (stkStsTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * update WMS_INBD_TRX Table of PROC_STS_CD and ERR_MSG_CD
     * @param wmsInbdTrxPk BigDecimal
     * @param procStsCd String
     * @param errMsgCd String
     */
    private void updTrxProcSts(BigDecimal wmsInbdTrxPk, String procStsCd, String errMsgCd) {

        WMS_INBD_TRXTMsg wmsInbdTrxTMsg = new WMS_INBD_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdTrxTMsg.wmsInbdTrxPk, wmsInbdTrxPk);

        WMS_INBD_TRXTMsg updWmsInbdTrxTMsg = (WMS_INBD_TRXTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsInbdTrxTMsg);
        if (updWmsInbdTrxTMsg == null) {
            throw new S21AbendException(NLGM0044E, new String[] {"WMS_INBD_TRX", "WMS_INBD_TRX_PK", wmsInbdTrxPk.toString() });
        }

        ZYPEZDItemValueSetter.setValue(updWmsInbdTrxTMsg.procStsCd, procStsCd);

        if (ZYPCommonFunc.hasValue(errMsgCd)) {
            ZYPEZDItemValueSetter.setValue(updWmsInbdTrxTMsg.errMsgCd, errMsgCd);
            EZDTBLAccessor.updateSelectionField(updWmsInbdTrxTMsg, new String[] {KEY_PROC_STS_CD, KEY_ERR_MSG_CD });

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updWmsInbdTrxTMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0008E, new String[] {"WMS_INBD_TRX", "WMS_INBD_TRX_PK", wmsInbdTrxPk.toString() });
            }
        } else {
            EZDTBLAccessor.updateSelectionField(updWmsInbdTrxTMsg, new String[] {KEY_PROC_STS_CD });

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updWmsInbdTrxTMsg.getReturnCode())) {
                throw new S21AbendException(NLGM0008E, new String[] {"WMS_INBD_TRX", "WMS_INBD_TRX_PK", wmsInbdTrxPk.toString() });
            }
        }
    }
    // END 2019/08/26 M.Naito [QC#53025,ADD]

}
