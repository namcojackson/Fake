/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB308001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.RTL_WHTMsg;
import business.db.RWS_PUT_AWAY_WRKTMsg;
import business.db.RWS_SHTG_WRKTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC309001PMsg;
import business.parts.NLZC407001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC407001.NLZC407001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NLAB308001: Receiving Serial Update Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   CSAI            K.Lee        Create          N/A
 * 2016/01/06   CITS            K.Ogino      Update          QC#16556
 * 2017/03/23   CITS            R.Shimamoto  Update          QC#17919
 *</pre>
 */
public class NLAB308001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** total commit count */
    private int totalCommitCount = 0;

    /** total error count */
    private int totalErrorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** */
    private final List<String> skipSceTpList = Arrays.asList( //
            NLXSceConst.SCE_ORD_TP_CD_KT//
            , NLXSceConst.SCE_ORD_TP_CD_KU//
            , NLXSceConst.SCE_ORD_TP_CD_KC//
            , NLXSceConst.SCE_ORD_TP_CD_RP//
            , NLXSceConst.SCE_ORD_TP_CD_RM//
            , NLXSceConst.SCE_ORD_TP_CD_BB//
            , NLXSceConst.SCE_ORD_TP_CD_IT//
            , NLXSceConst.SCE_ORD_TP_CD_SC//
            , NLXSceConst.SCE_ORD_TP_CD_IC//
            , NLXSceConst.SCE_ORD_TP_CD_SW//
            );

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException("NPAM1173E", new String[] {"Global Company Code" });
        }

        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException("NPAM1173E", new String[] {"Sales Date" });
        }
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // get PrInterface Data
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("procStsCd", PROC_STS.COMPLEATED);
            paramMap.put("svcMachProcStsCd", SVC_MACH_PROC_STS.IN_COMPLETED);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRwsPutAwayWrk", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                totalCount++;
                String rwsNum = resultSet.getString("RWS_NUM");
                String wrkTrxId = resultSet.getString("WRK_TRX_ID");
                String sqId = resultSet.getString("SQ_ID");

                // check rws close.
                if (!hasRwsCloseForPutAwaySkipOuder(ssmLlcClient, rwsNum)) {
                    // Rws does not close. skip.
                    continue;
                }

                NLZC407001PMsg pMsg = new NLZC407001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).wrkTrxId, wrkTrxId);
                ZYPEZDItemValueSetter.setValue(pMsg.inputList.no(0).sqId, sqId);
                pMsg.inputList.setValidCount(1);

                NLZC407001 api = new NLZC407001();
                api.execute(pMsg, ONBATCH_TYPE.BATCH);

                RWS_PUT_AWAY_WRKTMsg inMsg = new RWS_PUT_AWAY_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.wrkTrxId, wrkTrxId);
                ZYPEZDItemValueSetter.setValue(inMsg.sqId, sqId);
                RWS_PUT_AWAY_WRKTMsg updMsg = (RWS_PUT_AWAY_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
                boolean errFlg = false;

                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                        ZYPEZDItemValueSetter.setValue(updMsg.errMsgCd, pMsg.xxMsgIdList.no(i).xxMsgId);
                        if (pMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                            errFlg = true;
                            rollback();
                            ZYPEZDItemValueSetter.setValue(updMsg.svcMachProcStsCd, SVC_MACH_PROC_STS.ERROR);
                            totalErrorCount++;
                            break;
                        }
                    }
                }

                if (!errFlg) {
                    ZYPEZDItemValueSetter.setValue(updMsg.svcMachProcStsCd, SVC_MACH_PROC_STS.COMPLETED);
                    totalCommitCount++;
                }

                EZDTBLAccessor.update(updMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    rollback();
                    throw new S21AbendException("NPAM1172E", new String[] {updMsg.getTableName() });
                }
            }

            insertRwsShtgWrk(ssmLlcClient, getRcvDateTime());

            // QC#17919 Add.
            closeRwsAllocationOff(ssmLlcClient);

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, totalCommitCount, totalErrorCount, totalCount);
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NLAB308001().executeBatch(NLAB308001.class.getSimpleName());
    }

    /**
     * hasRwsCloseForPutAwaySkipOuder
     * @param ssmLlcClient S21SsmLowLevelCodingClient
     * @param rwsNum String
     * @return True:PutAwaySkip and Close.
     * @throws SQLException
     */
    private boolean hasRwsCloseForPutAwaySkipOuder(S21SsmLowLevelCodingClient ssmLlcClient, String rwsNum) throws SQLException {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("rwsNum", rwsNum);
        List<String> closeStatus = Arrays.asList(RWS_STS.CANCELED, RWS_STS.RECEIVED);

        PreparedStatement preparedStatement = ssmLlcClient.createPreparedStatement("hasRwsCloseForPutAwaySkipOuder", paramMap);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String sceOrdTp = resultSet.getString("SCE_ORD_TP_CD");
            String rwsStsCd = resultSet.getString("RWS_STS_CD");

            if (skipSceTpList.contains(sceOrdTp)) {
                if (closeStatus.contains(rwsStsCd)) {
                    // Close
                    return true;
                } else {
                    // Not Close.
                    return false;
                }
            } else {
                // no target sce type
                return true;
            }
        } else {
            // not exist.
            return false;
        }
    }

    /**
     * insertRwsShtgWrk QC#16556
     * @param ssmLlcClient S21SsmLowLevelCodingClient
     * @param rcvDtTm String
     * @return boolean
     */
    private boolean insertRwsShtgWrk(S21SsmLowLevelCodingClient ssmLlcClient, String rcvDtTm) {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Get non received Machine Master
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("rcvFlgY", ZYPConstant.FLG_ON_Y);
            paramMap.put("rwsOpenFlgN", ZYPConstant.FLG_OFF_N);
            List<String> svcMachProcStsListStatus = Arrays.asList(SVC_MACH_PROC_STS.IN_COMPLETED, SVC_MACH_PROC_STS.ERROR);
            paramMap.put("svcMachProcStsListStatus", svcMachProcStsListStatus);
            // QC#17919 Add.
            paramMap.put("instlBaseCtrlFlgY", ZYPConstant.FLG_ON_Y);
            paramMap.put("rcvSerTakeFlgN", ZYPConstant.FLG_OFF_N);
            paramMap.put("shpgSerTakeFlgN", ZYPConstant.FLG_OFF_N);

            preparedStatement = ssmLlcClient.createPreparedStatement("getMachMstrNotRecieved", paramMap);
            rs = preparedStatement.executeQuery();

            // Create PMsg for Disposal
            List<NSZC001001PMsg> machMstrPMsgList = new ArrayList<NSZC001001PMsg>();
            List<NLZC309001PMsg> assetStgnApiPMsgList = new ArrayList<NLZC309001PMsg>();

            while (rs.next()) {

                SVC_MACH_MSTRTMsg svcMachMstrTMsg = getMachMastr(rs.getBigDecimal("SVC_MACH_MSTR_PK"));

                if (svcMachMstrTMsg == null) {

                    // WH Transfer Non Serial IB Item
                    if (SCE_ORD_TP.DC_TRANSFER.equals(rs.getString("SCE_ORD_TP_CD"))) {

                        int cnt = 0;
                        BigDecimal shtgQty = rs.getBigDecimal("RWS_QTY").subtract(rs.getBigDecimal("RWS_PUT_AWAY_QTY"));

                        // Get non Allocated Machine Master
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("glblCmpyCd", this.glblCmpyCd);
                        params.put("svcConfigMstrPk", rs.getBigDecimal("RWS_SVC_CONFIG_MSTR_PK"));
                        params.put("mdseCd", rs.getString("RWS_MDSE_CD"));
                        params.put("curlocNum", rs.getString("INVTY_LOC_CD"));
                        params.put("svcMachMstrLocStsCd", LOC_STS.IN_TRANSIT_WH);
                        params.put("stkStsCd", rs.getString("INVTY_STK_STS_CD"));
                        params.put("svcMachMstrStsTrmn", SVC_MACH_MSTR_STS.TERMINATED);
                        params.put("svcMachMstrStsDup", SVC_MACH_MSTR_STS.DUPLICATE);
                        params.put("flgY", ZYPConstant.FLG_ON_Y);

                        PreparedStatement whtPs = null;
                        ResultSet whtRs = null;
                        try {
                            whtPs = ssmLlcClient.createPreparedStatement("getNonAllocMachMstrWHT", params);
                            whtRs = whtPs.executeQuery();

                            while (whtRs.next()) {

                                svcMachMstrTMsg = getMachMastr(whtRs.getBigDecimal("SVC_MACH_MSTR_PK"));

                                machMstrPMsgList.add(createMachMstrPMsg(rcvDtTm, svcMachMstrTMsg, svcMachMstrTMsg.serNum.getValue(), ProcessMode.ALLOCATION_OFF.code));
                                machMstrPMsgList.add(createMachMstrPMsg(rcvDtTm, svcMachMstrTMsg, svcMachMstrTMsg.serNum.getValue(), ProcessMode.DISPOSAL.code));
                                cnt++;

                                // Create Shortage Data
                                RWS_SHTG_WRKTMsg rwsShtgWrk = new RWS_SHTG_WRKTMsg();
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsNum, rs.getString("RWS_NUM"));
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsLineNum, rs.getString("RWS_DTL_LINE_NUM"));
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.serNum, svcMachMstrTMsg.serNum);
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.mdseCd, svcMachMstrTMsg.mdseCd);
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.curLocNum, rs.getString("INVTY_LOC_CD"));
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsShtgQty, shtgQty);
                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.procStsCd, PROC_STS.COMPLEATED);

                                EZDTBLAccessor.insert(rwsShtgWrk);
                                String returnCode = rwsShtgWrk.getReturnCode();

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
                                    String message = S21MessageFunc.clspGetMessage("NLAM1295EE", new String[] {rwsShtgWrk.getTableName() });
                                    S21InfoLogOutput.println(message);
                                    return false;
                                }

                                if (cnt == shtgQty.intValue()) {

                                    break;
                                }
                            }
                        } catch (SQLException e) {
                            sqlExceptionHandler(e);
                        } finally {
                            S21SsmLowLevelCodingClient.closeResource(whtPs, whtRs);
                        }
                    } else {
                        continue;
                    }
                } else {

                    if (isShtgDpsl(rs)) {

                        machMstrPMsgList.add(createMachMstrPMsg(rcvDtTm, svcMachMstrTMsg, svcMachMstrTMsg.serNum.getValue(), ProcessMode.ALLOCATION_OFF.code));

                        if (isTargetShortageSerial(rs.getString("SCE_ORD_TP_CD"))) {

                            machMstrPMsgList.add(createMachMstrPMsg(rcvDtTm, svcMachMstrTMsg, svcMachMstrTMsg.serNum.getValue(), ProcessMode.DISPOSAL.code));

                            // Asset Return
                            if (TRX.RENTAL_SHIPMENT.equals(rs.getString("TRX_CD")) //
                                    || TRX.EMSD_SHIPMENT.equals(rs.getString("TRX_CD")) //
                                    || isAssetWh(rs.getString("RTL_WH_CD"))) {

                                NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
                                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt, this.salesDate);
                                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_ASSET_ADJ_OR_DISPOSAL);
                                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, svcMachMstrTMsg.mdseCd.getValue());
                                ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
                                assetStgnApiPMsgList.add(assetStgnApiPMsg);
                            }

                            // Create Shortage Data
                            RWS_SHTG_WRKTMsg rwsShtgWrk = new RWS_SHTG_WRKTMsg();
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.glblCmpyCd, this.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsNum, rs.getString("RWS_NUM"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsLineNum, rs.getString("RWS_DTL_LINE_NUM"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.serNum, rs.getString("SER_NUM"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.mdseCd, rs.getString("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.curLocNum, rs.getString("CUR_LOC_NUM"));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsShtgQty, (rs.getBigDecimal("RWS_QTY")).subtract(rs.getBigDecimal("RWS_PUT_AWAY_QTY")));
                            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.procStsCd, PROC_STS.COMPLEATED);

                            BigDecimal svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
                            BigDecimal mainSvcMachMstrPk = rs.getBigDecimal("MAIN_SVC_MACH_MSTR_PK");

                            // Main machine of Config
                            if (ZYPCommonFunc.hasValue(svcMachMstrPk) && ZYPCommonFunc.hasValue(mainSvcMachMstrPk) && svcMachMstrPk.compareTo(mainSvcMachMstrPk) == 0) {

                                ZYPEZDItemValueSetter.setValue(rwsShtgWrk.procStsCd, PROC_STS.IN_COMPLETED);
                            }

                            EZDTBLAccessor.insert(rwsShtgWrk);
                            String returnCode = rwsShtgWrk.getReturnCode();

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
                                String message = S21MessageFunc.clspGetMessage("NLAM1295EE", new String[] {rwsShtgWrk.getTableName() });
                                S21InfoLogOutput.println(message);
                                return false;
                            }
                        }
                    }
                }
            }

            // Machine Master Update for Disposal
            if (!machMstrPMsgList.isEmpty()) {

                // Call Machine Master API
                NSZC001001 machMstrAPI = new NSZC001001();
                machMstrAPI.execute(machMstrPMsgList, ONBATCH_TYPE.BATCH);

                if (!chkMachMstrApiResult(machMstrPMsgList)) {

                    return false;
                }
            }

            // Create Asset Staging for Disposal
            if (!assetStgnApiPMsgList.isEmpty()) {

                NLZC309001 assetStgnApi = new NLZC309001();
                assetStgnApi.execute(assetStgnApiPMsgList, ONBATCH_TYPE.BATCH);

                if (!chkAssetStaingAPIResult(assetStgnApiPMsgList)) {

                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }

        return true;
    }

    /**
     * getMachMastr
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getMachMastr(BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);

        return getSvcMachMstr(svcMachMstrTMsg);
    }

    /**
     * getSvcMachMstr
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        try {

            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(svcMachMstrTMsg);

        } catch (EZDDBRecordLockedException rle) {

            String msg = S21MessageFunc.clspGetMessage("NLBM1087E", null);
            S21InfoLogOutput.println(msg);
            return null;

        } catch (EZDDBRetryRequestException rre) {

            String msg = S21MessageFunc.clspGetMessage("NLBM1077E", null);
            S21InfoLogOutput.println(msg);
            return null;
        }

        if (svcMachMstrTMsg == null) {

            String msg = S21MessageFunc.clspGetMessage("NLBM1087E", null);
            S21InfoLogOutput.println(msg);
            return null;
        }

        return svcMachMstrTMsg;
    }

    /**
     * isShtgDpsl
     * @param rs ResultSet
     * @return boolean
     */
    private boolean isShtgDpsl(ResultSet rs) throws SQLException {

        if (!SCE_ORD_TP.RETURN.equals(rs.getString("SCE_ORD_TP_CD"))) {

            return true;
        }

        String trxCd = rs.getString("TRX_CD");
        String trxRsnCd = rs.getString("TRX_RSN_CD");
        BigDecimal svcConfigMstrPk = rs.getBigDecimal("SVC_CONFIG_MSTR_PK");
        BigDecimal rwsSvcConfigMstrPk = rs.getBigDecimal("RWS_SVC_CONFIG_MSTR_PK");

        // A part of Config
        if (ZYPCommonFunc.hasValue(rwsSvcConfigMstrPk) && ZYPCommonFunc.hasValue(svcConfigMstrPk)) {

            return true;

            // Rental Return
        } else if (TRX.RENTAL_SHIPMENT.equals(trxCd)) {

            return true;

            // Loan Return
        } else if (TRX.MOVEMENT.equals(trxCd) && TRX_RSN.LOAN_TO_INVENTORY_STOCK_IN.equals(trxRsnCd)) {

            return true;
        }

        return false;
    }

    /**
     * getRcvDateTime
     * @return String
     */
    private String getRcvDateTime() {

        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return this.salesDate + hhmmss;
    }

    /**
     * createMachMstrPMsg
     * @param rcvDtTm String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param serNum String
     * @param mode String
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg createMachMstrPMsg(String rcvDtTm, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String serNum, String mode) {

        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, mode);

        if (svcMachMstrTMsg != null) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, svcMachMstrTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, svcMachMstrTMsg.serNum.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd.getValue());
        }

        if (ProcessMode.ALLOCATION_OFF.code.equals(mode)) {

            // No Additional Parameter

        } else if (ProcessMode.DISPOSAL.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effThruDt, this.salesDate);

        }

        return machMstrPMsg;
    }

    /**
     * @param sceOrdTpCd
     * @return boolean
     */
    private boolean isTargetShortageSerial(String sceOrdTpCd) {

        if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN.equals(sceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * isAssetWh
     * @param rtlWhCd String
     * @return boolean true: Asset false: Inventory
     */
    private boolean isAssetWh(String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);
        rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg != null) {

            if (INVTY_ACCT.ASSET.equals(rtlWhTMsg.invtyAcctCd.getValue())) {

                return true;
            }
        }

        return false;
    }

    /**
     * chkMachMstrApiResult
     * @param machMstrPMsgList List<NSZC001001PMsg>
     * @return boolean
     */
    private boolean chkMachMstrApiResult(List<NSZC001001PMsg> machMstrPMsgList) {

        for (NSZC001001PMsg machMstrPMsg : machMstrPMsgList) {

            if (!chkApiRslt(S21ApiUtil.getXxMsgIdList(machMstrPMsg))) {

                return false;
            }
        }

        return true;
    }

    /**
     * chkAssetStaingAPIResult
     * @param assetStaingPMsgList List<NLZC309001PMsg>
     * @return boolean true : OK, false : NG
     */
    private boolean chkAssetStaingAPIResult(List<NLZC309001PMsg> assetStaingPMsgList) {

        for (NLZC309001PMsg assetStaingPMsg : assetStaingPMsgList) {

            if (!chkApiRslt(S21ApiUtil.getXxMsgIdList(assetStaingPMsg))) {

                return false;
            }
        }

        return true;
    }

    /**
     * chkApiRslt
     * @param msgIdList List<String>
     * @return boolean true : OK, false : NG
     */
    private boolean chkApiRslt(List<String> msgIdList) {

        if (!msgIdList.isEmpty()) {

            for (String msgId : msgIdList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    String message = S21MessageFunc.clspGetMessage(msgId);
                    S21InfoLogOutput.println(message);

                    if (msgId.endsWith("E")) {

                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    /** QC#17919 Add.
     * closeRwsAllocationOff
     * @param ssmLlcClient S21SsmLowLevelCodingClient
     * @return boolean
     */
    private boolean closeRwsAllocationOff(S21SsmLowLevelCodingClient ssmLlcClient) {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Get non received Machine Master
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("received", RWS_STS.RECEIVED);
            paramMap.put("canceled", RWS_STS.CANCELED);
            paramMap.put("terminated", SVC_MACH_MSTR_STS.TERMINATED);

            preparedStatement = ssmLlcClient.createPreparedStatement("closeRwsAllocationOff", paramMap);
            rs = preparedStatement.executeQuery();

            // Create PMsg for Disposal
            List<NSZC001001PMsg> machMstrPMsgList = new ArrayList<NSZC001001PMsg>();

            while (rs.next()) {

                NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, (BigDecimal) rs.getBigDecimal("SVC_MACH_MSTR_PK"));
                machMstrPMsgList.add(machMstrPMsg);
            }

            // Call Machine Master API
            NSZC001001 machMstrAPI = new NSZC001001();
            machMstrAPI.execute(machMstrPMsgList, ONBATCH_TYPE.ONLINE);

            if (!chkMachMstrApiResult(machMstrPMsgList)) {

                return false;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }

        return true;
    }
}
