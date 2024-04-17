/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB112001;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.LOC_TPTMsg;
import business.db.MRP_INFOTMsg;
import business.db.MRP_RPT_WRKTMsg;
import business.db.MRP_RUN_PRMTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.PRCH_REQ_INTFCTMsg;
import business.db.PRCH_REQ_TPTMsg;
import business.db.PROCR_TPTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.parts.NPZC101001PMsg;
import business.parts.NPZC136001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC101001.NPZC101001;
import com.canon.cusa.s21.api.NPZ.NPZC136001.NPZC136001;
import com.canon.cusa.s21.batch.NPA.NPAB112001.constant.NPAB112001Constant;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetMdseRelationshipData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_RUN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 *<pre>
 * NPAB1120:MRP Run Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/23/2016   CITS            Y.Nomura         Create          N/A
 * 10/20/2016   CITS            Y.Fujii          Update          R349
 * 11/17/2016   CITS            K.Ogino          Update          QC#16002
 * 02/14/2017   CITS            Y.IWASAKI        Update          QC#15946
 * 02/21/2017   CITS            Y.IWASAKI        Update          QC#17487
 * 07/06/2017   CITS            M.Naito          Update          QC#18019
 * 08/18/2017   CITS            S.Endo           Update          Sol#013(QC#18717)
 * 08/21/2017   CITS            H.Naoi           Update          Sol#097(QC#18398
 * 09/04/2017   CITS            T.Wada           Update          QC#20679
 * 09/05/2017   CITS            Y.Fujii          Update          QC#20509
 * 09/27/2017   CITS            K.Fukumura       Update          QC#21230
 * 03/02/2018   CITS            K.Ogino          Update          QC#21093
 * 05/23/2018   CITS            Y.Iwasaki        Update          QC#25442
 * 07/09/2018   CITS            Y.Iwasaki        Update          QC#27013
 * 07/24/2018   CITS            T,Hakodate       Update          QC#27012
 * 08/10/2018   CITS            K.Ogino          Update          QC#26761
 * 01/28/2019   CITS            K.Ogino          Update          QC#30086
 * 04/18/2019   CITS            T.Tokutomi       Update          QC#31199
 * 08/12/2019   CITS            R.Shimamoto      Update          QC#51642
 * 11/19/2019   CITS            R.Shimamoto      Update          QC#54719
 * 03/11/2020   CITS            K.Ogino          Update          QC#56196
 * 04/14/2020   CITS            K.Ogino          Update          QC#55711-1
 * 04/16/2020   Fujitsu         T.Ogura          Update          QC#56462
 * 10/31/2020   CITS            M.Furugoori      Update          QC#57939
 * 12/25/2020   CITS            M.Furugoori      Update          QC#57991
 * 06/13/2023   Hitachi         S.Dong           Update          QC#55629
 *</pre>
 */
public class NPAB112001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** prchReqApvlStsCd */
    private String prchReqApvlStsCd = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    // QC#27012 ADD START
    /** */
    private List<String> warningMsgList = new ArrayList<String>();

    // QC#27012 ADD END

    // QC#26761
    /** */
    private List<String> stkStsCd = new ArrayList<String>();

    // QC#30086
    /** */
    private BigDecimal dbWaitTime = BigDecimal.ZERO;

    // QC#55711
    private String[] exclWHs = null;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NPAB112001().executeBatch(NPAB112001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB112001Constant.NPAM0078E);
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB112001Constant.NPAM1480E);
        }
        // QC#26761
        stkStsCd = getStsCd();
        if (stkStsCd == null || stkStsCd.isEmpty()) {
            stkStsCd.add(STK_STS.GOOD);
            stkStsCd.add(STK_STS.RANK_B);
        }
        // QC#30086
        dbWaitTime = ZYPCodeDataUtil.getNumConstValue("NPAB1120_DB_WAIT_TIME", globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(dbWaitTime)) {
            dbWaitTime = BigDecimal.valueOf(60);
        }
        // QC#55711
        String varConst = ZYPCodeDataUtil.getVarCharConstValue("NPAB1120_DOWNITEM_EXCL_WH", globalCompanyCode);
        if (ZYPCommonFunc.hasValue(varConst)) {
            this.exclWHs = varConst.split(",");
        } else {
            this.exclWHs = new String[0];
        }
    }

    @Override
    protected void mainRoutine() {

        String mrpRunGrpId = getUserVariable1();

        if (!ZYPCommonFunc.hasValue(mrpRunGrpId)) {
            mrpRunGrpId = NPAB112001Constant.DEFAULT_MRP_RUN_GRP_ID;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB112001Constant.DB_PARAM_DEFAULT_MRP_RUN_GRP_ID, NPAB112001Constant.DEFAULT_MRP_RUN_GRP_ID);
            paramMap.put(NPAB112001Constant.DB_PARAM_MRP_RUN_GRP_ID, mrpRunGrpId);
            paramMap.put(NPAB112001Constant.DB_PARAM_MRP_RUN_STS_CD, MRP_RUN_STS.READY_FOR_RUN);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpRunParameter", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MRP_RUN_PRMTMsg mrpRunPrmInfo = setMrpRunPrmInfo(resultSet);
                mainProcess(mrpRunPrmInfo);
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        if (0 < errorCount) {
            termCd = TERM_CD.WARNING_END;

            // QC#27012 ADD START
            if (!this.warningMsgList.isEmpty()) {
                // Register Mail
                registerMail();

                // Commit
                commit();

            }
            // QC#27012 ADD END
        }
        setTermState(termCd, totalCount - errorCount, errorCount, totalCount);
    }

    /**
     * Main Process.
     * @param mrpRunPrmInfo MRP Information
     */
    private void mainProcess(MRP_RUN_PRMTMsg mrpRunPrmInfo) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> prchReqIntfcRemoveList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> prchReqRemoveList = new ArrayList<Map<String, Object>>();

        try {

            getPrchReqApvlStsCd();

            // update Processing
            updateMrpRunPrmProcessing(mrpRunPrmInfo.mrpRunPrmPk.getValue());
            // QC#21093
//            commit();

            // BEGIN: Physical remove
            // Remove PR I/F logically removed before.
            physicalRemovePrchReqInterface();
            // Remove PR, PR Detail logically removed before.
            physicalRemovePrchReq();
            // END: Physical remove

            // BEGIN: Locgical remove
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMapRemove = new HashMap<String, Object>();
            paramMapRemove.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMapRemove.put(NPAB112001Constant.DB_PARAM_MRP_RUN_PRM_PK, mrpRunPrmInfo.mrpRunPrmPk.getValue());

            S21SsmExecutionParameter execParamRemove = new S21SsmExecutionParameter();
            execParamRemove.setFetchSize(FETCH_SIZE);
            execParamRemove.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRemoveMrpInfo", paramMapRemove, execParamRemove);
            resultSet = preparedStatement.executeQuery();

            String invtyLocCd = null;
            String mrpPlnNm = null;

            while (resultSet.next()) {

                invtyLocCd = resultSet.getString(NPAB112001Constant.INVTY_LOC_CD);
                mrpPlnNm = resultSet.getString(NPAB112001Constant.MRP_PLN_NM);

                // Temporarily store records to be logically removed.
                prchReqIntfcRemoveList.addAll(getPrchReqInterfaceForLogicalRemove(invtyLocCd, mrpPlnNm));
                prchReqRemoveList.addAll(getPrchReqDtlForLogicalRemove(invtyLocCd, mrpPlnNm));
            }
            // END: Logical remove

            // QC#16002
            if (ZYPCommonFunc.hasValue(mrpRunPrmInfo.locTpCd) && LOC_TP.WAREHOUSE.equals(mrpRunPrmInfo.locTpCd.getValue())) {

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
                map.put(NPAB112001Constant.DB_PARAM_MRP_RUN_PRM_PK, mrpRunPrmInfo.mrpRunPrmPk.getValue());
                map.put(NPAB112001Constant.DB_PARAM_MRP_RUN_SCHD_ID, mrpRunPrmInfo.mrpRunSchdId.getValue());

                S21SsmExecutionParameter param = new S21SsmExecutionParameter();
                param.setFetchSize(FETCH_SIZE);
                param.setMaxRows(0);
                preparedStatement = ssmLlcClient.createPreparedStatement("getRtlWhList", map, param);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    // for Warehouse. Mod QC#26761
                    createMrpRptWrk(mrpRunPrmInfo, resultSet.getString(NPAB112001Constant.RTL_WH_CD), resultSet.getString(NPAB112001Constant.MRP_RUN_SCHD_ID), true, true);
                    createMrpRptWrk(mrpRunPrmInfo, resultSet.getString(NPAB112001Constant.RTL_WH_CD), resultSet.getString(NPAB112001Constant.MRP_RUN_SCHD_ID), true, false);
                }

            } else {

                // for Tech. Mod QC#26761
                createMrpRptWrk(mrpRunPrmInfo, null, null, false, false);

            }

            // Remove entered requests stored in beginning.
            for (Map<String, Object> rec : prchReqIntfcRemoveList) {
                BigDecimal prchReqIntfcPk = (BigDecimal) rec.get(NPAB112001Constant.PRCH_REQ_INTFC_PK);
                String ezUpTime = (String) rec.get(NPAB112001Constant.EZUPTIME);

                logicalRemovePrchReqInterface(prchReqIntfcPk, ezUpTime);
            }

            String lastPrchReqNum = "";
            boolean res = false;
            for (Map<String, Object> rec : prchReqRemoveList) {
                String prchReqNum = (String) rec.get(NPAB112001Constant.PRCH_REQ_NUM);
                String ezUpTime = (String) rec.get(NPAB112001Constant.EZUPTIME);
                String prchReqLineNum = (String) rec.get(NPAB112001Constant.PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = (BigDecimal) rec.get(NPAB112001Constant.PRCH_REQ_LINE_SUB_NUM);

                if (!lastPrchReqNum.equals(prchReqNum)) {
                    res = logicalRemovePrchReq(prchReqNum, ezUpTime);
                    lastPrchReqNum = prchReqNum;
                }
                if (res) {
                    logicalRemovePrchReqDtl(prchReqNum, prchReqLineNum, prchReqLineSubNum, ezUpTime);
                }
            }

            commit();

        } catch (S21AbendException e) {

            rollback();
            throw e;

        } catch (SQLException e) {

            rollback();
            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    /**
     * Update MRP Run Prm processing
     */
    private void updateMrpRunPrmProcessing(BigDecimal mrpRunPrmPk) {
        MRP_RUN_PRMTMsg tMsg = new MRP_RUN_PRMTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpRunPrmPk, mrpRunPrmPk);
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        tMsg = (MRP_RUN_PRMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        ZYPEZDItemValueSetter.setValue(tMsg.mrpRunStsCd, MRP_RUN_STS.PROCESSING);
        ZYPEZDItemValueSetter.setValue(tMsg.startTs, ZYPDateUtil.getCurrentSystemTime(NPAB112001Constant.DATE_TIME_FORMAT));

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            if (tMsg != null) {
                S21InfoLogOutput.println(tMsg.toString());
            }
            throw new S21AbendException(NPAB112001Constant.NPAM1171E, new String[] {"MRP_RUN_PRMT" });
        }
    }

    /**
     * Update MRP Run Prm processing
     */
    private void updateMrpRunPrmCompleted(BigDecimal mrpRunPrmPk, int reqCnt) {
        MRP_RUN_PRMTMsg tMsg = new MRP_RUN_PRMTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpRunPrmPk, mrpRunPrmPk);
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
        tMsg = (MRP_RUN_PRMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        ZYPEZDItemValueSetter.setValue(tMsg.mrpRunStsCd, MRP_RUN_STS.COMPLETED);
        ZYPEZDItemValueSetter.setValue(tMsg.startTs, ZYPDateUtil.getCurrentSystemTime(NPAB112001Constant.DATE_TIME_FORMAT));
        ZYPEZDItemValueSetter.setValue(tMsg.mrpRunPrmCmntTxt, "Request Item Count : " + reqCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpRptPrintProcStsCd, ZYPConstant.FLG_OFF_0);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            if (tMsg != null) {
                S21InfoLogOutput.println(tMsg.toString());
            }
            throw new S21AbendException(NPAB112001Constant.NPAM1171E, new String[] {"MRP_RUN_PRMT" });
        }
    }

    private MRP_INFOTMsg setDataMrpInfo(ResultSet rs) throws SQLException {
        MRP_INFOTMsg ret = new MRP_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(ret.mdseCd, rs.getString(NPAB112001Constant.MDSE_CD));
        //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(ret.mrpPlnNm, rs.getString(NPAB112001Constant.MRP_PLN_NM));
        //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(ret.invtyLocCd, rs.getString(NPAB112001Constant.INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.rtlWhCd, rs.getString(NPAB112001Constant.RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(ret.rtlSwhCd, rs.getString(NPAB112001Constant.RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(ret.srcLocCd, rs.getString(NPAB112001Constant.SRC_LOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.srcRtlWhCd, rs.getString(NPAB112001Constant.SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(ret.srcRtlSwhCd, rs.getString(NPAB112001Constant.SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(ret.ropQty, rs.getBigDecimal(NPAB112001Constant.ROP_QTY));
        ZYPEZDItemValueSetter.setValue(ret.maxInvtyQty, rs.getBigDecimal(NPAB112001Constant.MAX_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(ret.minOrdQty, rs.getBigDecimal(NPAB112001Constant.MIN_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.procrTpCd, rs.getString(NPAB112001Constant.PROCR_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.incrOrdQty, rs.getBigDecimal(NPAB112001Constant.INCR_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.locRoleTpCd, rs.getString(NPAB112001Constant.LOC_ROLE_TP_CD));
        return ret;
    }

    private MRP_RUN_PRMTMsg setMrpRunPrmInfo(ResultSet rs) throws SQLException {
        MRP_RUN_PRMTMsg ret = new MRP_RUN_PRMTMsg();
        ZYPEZDItemValueSetter.setValue(ret.mrpRunPrmPk, rs.getBigDecimal(NPAB112001Constant.MRP_RUN_PRM_PK));
        ZYPEZDItemValueSetter.setValue(ret.dmndCtoffDt, rs.getString(NPAB112001Constant.DMND_CTOFF_DT));
        ZYPEZDItemValueSetter.setValue(ret.dmndCtoffDaysAot, rs.getBigDecimal(NPAB112001Constant.DMND_CTOFF_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(ret.splyCtoffDt, rs.getString(NPAB112001Constant.SPLY_CTOFF_DT));
        ZYPEZDItemValueSetter.setValue(ret.splyCtoffDaysAot, rs.getBigDecimal(NPAB112001Constant.SPLY_CTOFF_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(ret.cratPrchReqFlg, rs.getString(NPAB112001Constant.CRAT_PRCH_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(ret.printItemStsFlg, rs.getString(NPAB112001Constant.PRINT_ITEM_STS_FLG));
        ZYPEZDItemValueSetter.setValue(ret.printItemDescFlg, rs.getString(NPAB112001Constant.PRINT_ITEM_DESC_FLG));
        ZYPEZDItemValueSetter.setValue(ret.mrpRunSchdId, rs.getString(NPAB112001Constant.MRP_RUN_SCHD_ID));
        ZYPEZDItemValueSetter.setValue(ret.locTpCd, rs.getString(NPAB112001Constant.LOC_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.mrpPlnNm, rs.getString(NPAB112001Constant.MRP_PLN_NM));
        ZYPEZDItemValueSetter.setValue(ret.rtlWhCd, rs.getString(NPAB112001Constant.RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(ret.rtlSwhCd, rs.getString(NPAB112001Constant.RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(ret.mrpRunGrpId, rs.getString(NPAB112001Constant.MRP_RUN_GRP_ID));
        // QC#21230 Start
        ZYPEZDItemValueSetter.setValue(ret.lineBizTpCd, rs.getString(NPAB112001Constant.LINE_BIZ_TP_CD));
        // QC#21230 End
        return ret;
    }

    private RTL_WHTMsg setRtlWhInfo(ResultSet rs) throws SQLException {
        RTL_WHTMsg ret = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(ret.rtlWhNm, rs.getString(NPAB112001Constant.RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(ret.techTocCd, rs.getString(NPAB112001Constant.TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(ret.prfCarrCd, rs.getString(NPAB112001Constant.PRF_CARR_CD));
        ZYPEZDItemValueSetter.setValue(ret.locTpCd, rs.getString(NPAB112001Constant.LOC_TP_CD));
        return ret;
    }

    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
    //private PRCH_REQ_INTFCTMsg setIFData(MRP_INFOTMsg mrpInfo, MRP_RUN_PRMTMsg mrpRunPrm, RTL_WHTMsg rtlWh, NPZC101001PMsg apiPMsg, boolean techFlag) {
    // QC#55711
    private PRCH_REQ_INTFCTMsg setIFData(MRP_INFOTMsg mrpInfo, MRP_RUN_PRMTMsg mrpRunPrm, RTL_WHTMsg rtlWh, NPZC101001PMsg apiPMsg, boolean techFlag, BigDecimal reqQty, String mdseCd) {
    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END
        PRCH_REQ_INTFCTMsg ret = new PRCH_REQ_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(ret.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ret.prchReqIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(NPAB112001Constant.PRCH_REQ_INTFC_SQ));
        ZYPEZDItemValueSetter.setValue(ret.procStsCd, PROC_STS.IN_COMPLETED);
        if (techFlag) {
            // Tech Request
            ZYPEZDItemValueSetter.setValue(ret.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_REQUEST);
            ZYPEZDItemValueSetter.setValue(ret.prchReqTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PRCH_REQ_TP_TECH_PLN, globalCompanyCode));
            ZYPEZDItemValueSetter.setValue(ret.rqstTechTocCd, rtlWh.techTocCd);
            ZYPEZDItemValueSetter.setValue(ret.prchReqApvlStsCd, prchReqApvlStsCd);
            ZYPEZDItemValueSetter.setValue(ret.prchReqSrcTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PRCH_REQ_SRC_TP_TECH_PLN, globalCompanyCode));
            // QC#18019
            DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(getGlobalCompanyCode(), NPAB112001Constant.GRP_ID_NPZC1170, NPAB112001Constant.REQ_TP_MINMAX);
            ZYPEZDItemValueSetter.setValue(ret.rqstRcvTm, convertRequestTime(dsCondConstTMsg.dsCondConstValTxt_03.getValue()));
            ZYPEZDItemValueSetter.setValue(ret.poSchdRelDt, salesDate);    // 04/16/2020 T.Ogura [QC#56462,ADD]
            ZYPEZDItemValueSetter.setValue(ret.shpgSvcLvlCd, dsCondConstTMsg.dsCondConstValTxt_06.getValue());    // 12/25/2020 [QC#57991,ADD]
        } else {
            // WH Transfer or PR/PO
            ZYPEZDItemValueSetter.setValue(ret.prchReqApvlStsCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PRCH_REQ_APVL_STS_WH_PLN, globalCompanyCode));
            ZYPEZDItemValueSetter.setValue(ret.prchReqSrcTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PRCH_REQ_SRC_TP_WH_PLN, globalCompanyCode));

            // QC#17487
            // Set Pre-Approved if target WH is included in specified WH owners.
            if (PRCH_REQ_APVL_STS.ENTERED.equals(ret.prchReqApvlStsCd.getValue()) || PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL.equals(ret.prchReqApvlStsCd.getValue())) {
                if (isPreApprovedWhOwnr(mrpInfo)) {
                    ZYPEZDItemValueSetter.setValue(ret.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
                    ZYPEZDItemValueSetter.setValue(ret.poSchdRelDt, salesDate);
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratDt, salesDate);
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime(NPAB112001Constant.TIME_FORMAT));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratByPsnCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.NPAB1120_PR_CRAT_BY_PSN_CD, globalCompanyCode));
        ZYPEZDItemValueSetter.setValue(ret.prchReqCratByNm, ret.prchReqCratByPsnCd);
        ZYPEZDItemValueSetter.setValue(ret.trxRefNum, mrpRunPrm.mrpRunPrmPk.getValue().toString());
        //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(ret.mrpPlnNm, mrpInfo.mrpPlnNm);
        //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        ZYPEZDItemValueSetter.setValue(ret.procrTpCd, mrpInfo.procrTpCd);
        ZYPEZDItemValueSetter.setValue(ret.srcInvtyLocCd, mrpInfo.srcLocCd);
        ZYPEZDItemValueSetter.setValue(ret.srcRtlWhCd, mrpInfo.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(ret.srcRtlSwhCd, mrpInfo.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(ret.destInvtyLocCd, mrpInfo.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(ret.destRtlWhCd, mrpInfo.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(ret.destRtlSwhCd, mrpInfo.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(ret.vndDropShipFlg, ZYPConstant.FLG_OFF_N);
        // QC#55711
//        ZYPEZDItemValueSetter.setValue(ret.mdseCd, mrpInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(ret.mdseCd, mdseCd);
        //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START        
        //ZYPEZDItemValueSetter.setValue(ret.prchReqQty, apiPMsg.scheduledOrdList.no(0).prchReqQty);
        if (reqQty.compareTo(BigDecimal.ZERO) > 0) {
            ZYPEZDItemValueSetter.setValue(ret.prchReqQty, reqQty);
        } else {
            ZYPEZDItemValueSetter.setValue(ret.prchReqQty, apiPMsg.scheduledOrdList.no(0).prchReqQty);
        }
        //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END
        ZYPEZDItemValueSetter.setValue(ret.ordQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(ret.ropQty, mrpInfo.ropQty);
        ZYPEZDItemValueSetter.setValue(ret.maxInvtyQty, mrpInfo.maxInvtyQty);
        ZYPEZDItemValueSetter.setValue(ret.minOrdQty, mrpInfo.minOrdQty);
        ZYPEZDItemValueSetter.setValue(ret.incrOrdQty, mrpInfo.incrOrdQty);

        ZYPEZDItemValueSetter.setValue(ret.curInvtyQty, apiPMsg.scheduledOrdList.no(0).curInvtyQty);
        ZYPEZDItemValueSetter.setValue(ret.curInvtyAvalQty, apiPMsg.scheduledOrdList.no(0).curInvtyAvalQty);
        ZYPEZDItemValueSetter.setValue(ret.schdInbdQty, apiPMsg.scheduledOrdList.no(0).schdInbdQty);
        ZYPEZDItemValueSetter.setValue(ret.schdOtbdQty, apiPMsg.scheduledOrdList.no(0).schdOtbdQty);

        ZYPEZDItemValueSetter.setValue(ret.lineBizTpCd, mrpRunPrm.lineBizTpCd);
        if (!techFlag) {
            // WH Planning

            // QC#17487
            // Set Service Level to Ground if target WH is included in specified WH owners.
            if (isSvcLvlWhOwnr(mrpInfo)) {
                ZYPEZDItemValueSetter.setValue(ret.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
            }
        }
        ZYPEZDItemValueSetter.setValue(ret.carrCd, rtlWh.prfCarrCd);

        return ret;
    }

    // START 2023/06/13 S.Dong [QC#55629, ADD]
    private MRP_RPT_WRKTMsg setReportData(MRP_INFOTMsg mrpInfo, MRP_RUN_PRMTMsg mrpRunPrm, RTL_WHTMsg rtlWh, NPZC101001PMsg apiPMsg, ResultSet rs, String mrpRunSchdId, boolean isDiscontinuedItem, BigDecimal reqQty) throws SQLException {
    // END 2023/06/13 S.Dong [QC#55629, ADD]
        MRP_RPT_WRKTMsg ret = new MRP_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ret.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ret.mrpRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(NPAB112001Constant.MRP_RPT_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(ret.mrpRunPrmPk, mrpRunPrm.mrpRunPrmPk);
        if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            // for Warehouse
            ZYPEZDItemValueSetter.setValue(ret.mrpRunSchdId, mrpRunSchdId);
        } else {
            // for Tech
            ZYPEZDItemValueSetter.setValue(ret.mrpRunSchdId, mrpRunPrm.mrpRunSchdId);
        }
        ZYPEZDItemValueSetter.setValue(ret.mrpRptPrintTxt, ZYPDateUtil.getCurrentSystemTime(NPAB112001Constant.DATE_TIME_PRINT_FORMAT));
        ZYPEZDItemValueSetter.setValue(ret.rtlWhCd, mrpInfo.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(ret.rtlWhNm, rtlWh.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(ret.rtlSwhCd, mrpInfo.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(ret.rtlSwhNm, rs.getString(NPAB112001Constant.RTL_SWH_NM));
        ZYPEZDItemValueSetter.setValue(ret.mdseCd, mrpInfo.mdseCd);
        if (ZYPConstant.FLG_ON_Y.equals(mrpRunPrm.printItemDescFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(ret.mdseDescShortTxt, rs.getString(NPAB112001Constant.MDSE_DESC_SHORT_TXT));
        }
        ZYPEZDItemValueSetter.setValue(ret.coaMdseTpCd, rs.getString(NPAB112001Constant.COA_MDSE_TP_CD));
        ZYPEZDItemValueSetter.setValue(ret.coaProdCd, rs.getString(NPAB112001Constant.COA_PROD_CD));
        if (ZYPConstant.FLG_ON_Y.equals(mrpRunPrm.printItemStsFlg.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NPAB112001Constant.PRCH_PLN_AVAL_FLG))) {
                ZYPEZDItemValueSetter.setValue(ret.prchPlnAvalTxt, NPAB112001Constant.PRCH_PLN_AVAL_TXT_ACT);
            } else {
                ZYPEZDItemValueSetter.setValue(ret.prchPlnAvalTxt, NPAB112001Constant.PRCH_PLN_AVAL_TXT_INACT);
            }
        }

        ZYPEZDItemValueSetter.setValue(ret.ropQtyTxt, toString(mrpInfo.ropQty.getValue()));
        ZYPEZDItemValueSetter.setValue(ret.maxInvtyQtyTxt, toString(mrpInfo.maxInvtyQty.getValue()));

        ZYPEZDItemValueSetter.setValue(ret.curInvtyQtyTxt, toString(apiPMsg.scheduledOrdList.no(0).curInvtyQty.getValue()));
        ZYPEZDItemValueSetter.setValue(ret.schdInbdQtyTxt, toString(apiPMsg.scheduledOrdList.no(0).schdInbdQty.getValue()));
        ZYPEZDItemValueSetter.setValue(ret.schdOtbdQtyTxt, toString(apiPMsg.scheduledOrdList.no(0).schdOtbdQty.getValue()));
        ZYPEZDItemValueSetter.setValue(ret.curInvtyAvalQtyTxt, toString(apiPMsg.scheduledOrdList.no(0).curInvtyAvalQty.getValue()));
        // QC#27012 MOD START
        if (isDiscontinuedItem) {
            ZYPEZDItemValueSetter.setValue(ret.prchReqQtyTxt, NPAB112001Constant.ZERO);
        } else {
            // START 2023/06/13 S.Dong [QC#55629, ADD]
            if (ZYPCommonFunc.hasValue(reqQty) && reqQty.compareTo(apiPMsg.scheduledOrdList.no(0).prchReqQty.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(ret.prchReqQtyTxt, toString(reqQty));
            } else {
                ZYPEZDItemValueSetter.setValue(ret.prchReqQtyTxt, toString(apiPMsg.scheduledOrdList.no(0).prchReqQty.getValue()));
            }
            // END 2023/06/13 S.Dong [QC#55629, ADD]
        }
        // QC#27012 MOD END
        ZYPEZDItemValueSetter.setValue(ret.procrTpDescTxt, rs.getString(NPAB112001Constant.PROCR_TP_DESC_TXT));

        //20170202
        if (ZYPCommonFunc.hasValue(mrpRunPrm.dmndCtoffDt)) {
            ZYPEZDItemValueSetter.setValue(ret.dmndCtoffDtTxt , ZYPDateUtil.formatEzd8ToSysDisp(mrpRunPrm.dmndCtoffDt.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(ret.dmndCtoffDaysAot , mrpRunPrm.dmndCtoffDaysAot);
        if (ZYPCommonFunc.hasValue(mrpRunPrm.splyCtoffDt)) {
            ZYPEZDItemValueSetter.setValue(ret.splyCtoffDtTxt , ZYPDateUtil.formatEzd8ToSysDisp(mrpRunPrm.splyCtoffDt.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(ret.splyCtoffDaysAot , mrpRunPrm.splyCtoffDaysAot);
        ZYPEZDItemValueSetter.setValue(ret.cratPrchReqFlg , mrpRunPrm.cratPrchReqFlg);
        ZYPEZDItemValueSetter.setValue(ret.printItemStsFlg , mrpRunPrm.printItemStsFlg);
        ZYPEZDItemValueSetter.setValue(ret.printItemDescFlg , mrpRunPrm.printItemDescFlg);
        //ONL_BAT_RQST_FLG
        if (ZYPCommonFunc.hasValue(ret.mrpRunSchdId)) {
            //N:Batch (MRP_RUN_SCHD_ID has Value)
            ZYPEZDItemValueSetter.setValue(ret.onlBatRqstFlg , ZYPConstant.FLG_OFF_N);
        } else {
            //Y:Online(MRP_RUN_SCHD_ID IS NULL)
            ZYPEZDItemValueSetter.setValue(ret.onlBatRqstFlg , ZYPConstant.FLG_ON_Y);
        }
        //PLN_LVL_DESC_TXT(Online Only)
        if (!ZYPCommonFunc.hasValue(ret.mrpRunSchdId)) {
            if (ZYPCommonFunc.hasValue(mrpRunPrm.mrpPlnNm)) {
                ZYPEZDItemValueSetter.setValue(ret.plnLvlDescTxt , "Plan Name");
            } else {
                ZYPEZDItemValueSetter.setValue(ret.plnLvlDescTxt , "Sub-Warehouse");
            }
        }

        // QC#20509
        String rtlWhCdSrchTxt  = mrpRunPrm.rtlWhCd.getValue();
        String rtlWhNmSrchTxt  = getWhName(mrpRunPrm.rtlWhCd.getValue());
        String rtlSwhCdSrchTxt = mrpRunPrm.rtlSwhCd.getValue();
        String rtlSwhNmSrchTxt = getSwhName(mrpRunPrm.rtlWhCd.getValue() , mrpRunPrm.rtlSwhCd.getValue());
        if (ZYPCommonFunc.hasValue(mrpRunPrm.mrpRunSchdId) && LOC_TP.WAREHOUSE.equals(mrpRunPrm.locTpCd.getValue())) {
            // update MRP_INFO RTL_WH/RTL_SWH
            rtlWhCdSrchTxt  = ret.rtlWhCd.getValue();
            rtlWhNmSrchTxt  = ret.rtlWhNm.getValue();
            rtlSwhCdSrchTxt = ret.rtlSwhCd.getValue();
            rtlSwhNmSrchTxt = ret.rtlSwhNm.getValue();
        }
        // QC#21230 Start
        // ****************************
        // Set MRP_PLN_NM_SRCH_TXT
        // ****************************
        // ZYPEZDItemValueSetter.setValue(ret.mrpPlnNmSrchTxt , mrpRunPrm.mrpPlnNm);
        String strMrpPlnNmSrchTxt = mrpRunPrm.mrpPlnNm.getValue();
        if (ZYPCommonFunc.hasValue(mrpRunPrm.mrpRunSchdId)) {
            // (Batch)
            if (LOC_TP.WAREHOUSE.equals(mrpRunPrm.locTpCd.getValue())) {
                // Wharehouse Search
                strMrpPlnNmSrchTxt = mrpInfo.mrpPlnNm.getValue();
            } else if (LOC_TP.TECHNICIAN.equals(mrpRunPrm.locTpCd.getValue())) {
                // Zone(Technician) Search
                strMrpPlnNmSrchTxt = ZYPCommonFunc.concatString(mrpRunPrm.lineBizTpCd.getValue(), " ", this.getLocTypeName(mrpRunPrm.locTpCd.getValue()));
            }
        } else if (ZYPCommonFunc.hasValue(mrpRunPrm.rtlWhCd.getValue())) {
            // (Online) and Whrehouse Search
            strMrpPlnNmSrchTxt = mrpInfo.mrpPlnNm.getValue();
        }
        ZYPEZDItemValueSetter.setValue(ret.mrpPlnNmSrchTxt, strMrpPlnNmSrchTxt);
        // QC#21230 End

        ZYPEZDItemValueSetter.setValue(ret.rtlWhCdSrchTxt , rtlWhCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(ret.rtlWhNmSrchTxt , rtlWhNmSrchTxt);
        ZYPEZDItemValueSetter.setValue(ret.rtlSwhCdSrchTxt , rtlSwhCdSrchTxt);
        ZYPEZDItemValueSetter.setValue(ret.rtlSwhNmSrchTxt , rtlSwhNmSrchTxt);

        return ret;
    }
    // QC#21230 Start
    private String getLocTypeName(String strLocTypeCd) {
        String strLocTypeName = "";
        //Get Name
        if (ZYPCommonFunc.hasValue(strLocTypeCd)) {
            LOC_TPTMsg locTPtMsg = new LOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(locTPtMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(locTPtMsg.locTpCd, strLocTypeCd);

            LOC_TPTMsg resultTMsg = (LOC_TPTMsg) EZDTBLAccessor.findByKey(locTPtMsg);
            if (resultTMsg != null) {
                strLocTypeName = resultTMsg.locTpDescTxt.getValue();
            }
        }
        return strLocTypeName;
    }
    // QC#21230 End

    private String toString(BigDecimal val) {
        if (val == null) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(val);
    }

    private void getPrchReqApvlStsCd() {
        PRCH_REQ_TPTMsg tMsg = new PRCH_REQ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PRCH_REQ_TP_TECH_PLN, globalCompanyCode));

        tMsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            prchReqApvlStsCd = tMsg.prchReqApvlStsCd.getValue();
        }
    }

    private NPZC101001PMsg callMRPCalcAPI(MRP_INFOTMsg mrpInfo, MRP_RUN_PRMTMsg mrpRunPrm) {

        NPZC101001PMsg mrpCalcMsg = new NPZC101001PMsg();
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.xxModeCd, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.mdseCd, mrpInfo.mdseCd);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.invtyLocCd, mrpInfo.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.ropQty, mrpInfo.ropQty);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.maxInvtyQty, mrpInfo.maxInvtyQty);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.dmndCtoffDt, mrpRunPrm.dmndCtoffDt);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.dmndCtoffDaysAot, mrpRunPrm.dmndCtoffDaysAot);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.splyCtoffDt, mrpRunPrm.splyCtoffDt);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.splyCtoffDaysAot, mrpRunPrm.splyCtoffDaysAot);
        ZYPEZDItemValueSetter.setValue(mrpCalcMsg.xxModeInd, ZYPConstant.FLG_OFF_0);

        NPZC101001 mrpCalcApi = new NPZC101001();
        mrpCalcApi.execute(mrpCalcMsg, ONBATCH_TYPE.BATCH);
        return mrpCalcMsg;
    }

    /**
     * Remove Purchase Request Interface
     * @param invtyLocCd String
     */
    private List<Map<String, Object>> getPrchReqInterfaceForLogicalRemove(String invtyLocCd, String mrpPlnNm) {

        String errMsgText = S21MessageFunc.clspGetMessage(NPAB112001Constant.NPAM1576E, null);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        queryParam.put(NPAB112001Constant.DB_PARAM_INVTY_LOC_CD, invtyLocCd);
        queryParam.put(NPAB112001Constant.DB_PARAM_TECH_PLANNING, PRCH_REQ_SRC_TP.TECH_PLANNING);
        queryParam.put(NPAB112001Constant.DB_PARAM_WH_PLANNING, PRCH_REQ_SRC_TP.WH_PLANNING);
        queryParam.put(NPAB112001Constant.DB_PARAM_IN_COMPLETED, PROC_STS.IN_COMPLETED);
        queryParam.put(NPAB112001Constant.DB_PARAM_ERROR, PROC_STS.ERROR);
        // QC#20679
        if (ZYPCommonFunc.hasValue(mrpPlnNm)) {
            queryParam.put(NPAB112001Constant.DB_PARAM_MRP_PLN_NM, mrpPlnNm);
        }

        return this.ssmBatchClient.queryObjectList("getPrchReqIntfcPkForLogicalRemove", queryParam);
    }

    /**
     * Remove Purchase Request Interface
     * @param invtyLocCd String
     */
    private boolean logicalRemovePrchReqInterface(BigDecimal prchReqIntfcPk, String ezUpTime) {

        String errMsgText = S21MessageFunc.clspGetMessage(NPAB112001Constant.NPAM1576E, null);

        PRCH_REQ_INTFCTMsg tMsg = new PRCH_REQ_INTFCTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqIntfcPk, prchReqIntfcPk);
        // QC#30086
        tMsg = (PRCH_REQ_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg, dbWaitTime.intValue());

        if (tMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }

        if (!ezUpTime.equals(tMsg.ezUpTime.getValue())) {
            // Updated by another user
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.intfcErrMsgTxt, errMsgText);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NPAB112001Constant.NPAM1171E, new String[] {"PRCH_REQ_INTFC" });
            S21InfoLogOutput.println(tMsg.toString());
        }

        EZDTBLAccessor.logicalRemove(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NPAB112001Constant.NPAM1171E, new String[] {"PRCH_REQ_INTFC" });
            S21InfoLogOutput.println(tMsg.toString());
            return false;
        }

        return true;
    }

    /**
     * Remove Purchase Request
     * @param invtyLocCd String
     */
    private List<Map<String, Object>> getPrchReqDtlForLogicalRemove(String invtyLocCd, String mrpPlnNm) {

        String errMsgText = S21MessageFunc.clspGetMessage(NPAB112001Constant.NPAM1576E, null);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        queryParam.put(NPAB112001Constant.DB_PARAM_INVTY_LOC_CD, invtyLocCd);
        queryParam.put(NPAB112001Constant.DB_PARAM_TECH_PLANNING, PRCH_REQ_SRC_TP.TECH_PLANNING);
        queryParam.put(NPAB112001Constant.DB_PARAM_WH_PLANNING, PRCH_REQ_SRC_TP.WH_PLANNING);
        queryParam.put(NPAB112001Constant.DB_PARAM_ENTERED, PRCH_REQ_APVL_STS.ENTERED);
        // QC#20679
        if (ZYPCommonFunc.hasValue(mrpPlnNm)) {
            queryParam.put(NPAB112001Constant.DB_PARAM_MRP_PLN_NM, mrpPlnNm);
        }
        // QC#56196
        queryParam.put(NPAB112001Constant.DB_PARAM_PRCH_REQ_TP_CD_MINMAX, ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PRCH_REQ_TP_TECH_PLN, globalCompanyCode));

        return this.ssmBatchClient.queryObjectList("getPrchReqPkForLogicalRemove", queryParam);
    }

    /**
     * Remove Purchase Request
     * @param invtyLocCd String
     */
    private boolean logicalRemovePrchReq(String prchReqNum, String ezUpTime) {

        String errMsgText = S21MessageFunc.clspGetMessage(NPAB112001Constant.NPAM1576E, null);

        PRCH_REQTMsg tMsgPrchReq = new PRCH_REQTMsg();

        ZYPEZDItemValueSetter.setValue(tMsgPrchReq.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsgPrchReq.prchReqNum, prchReqNum);
        // QC#30086
        tMsgPrchReq = (PRCH_REQTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsgPrchReq, dbWaitTime.intValue());

        if (tMsgPrchReq == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgPrchReq.getReturnCode())) {
            return false;
        }

        if (!ezUpTime.equals(tMsgPrchReq.ezUpTime.getValue())) {
            // Updated by another user
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tMsgPrchReq.batErrMsgTxt, errMsgText);

        EZDTBLAccessor.update(tMsgPrchReq);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgPrchReq.getReturnCode())) {
            S21InfoLogOutput.println(NPAB112001Constant.NPAM1171E, new String[] {"PRCH_REQ" });
            S21InfoLogOutput.println(tMsgPrchReq.toString());
        }

        EZDTBLAccessor.logicalRemove(tMsgPrchReq);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgPrchReq.getReturnCode())) {
            S21InfoLogOutput.println(NPAB112001Constant.NPAM1171E, new String[] {"PRCH_REQ" });
            S21InfoLogOutput.println(tMsgPrchReq.toString());
            return false;
        }

        return true;
    }

    private boolean logicalRemovePrchReqDtl(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum, String ezUpTime) {

        PRCH_REQ_DTLTMsg tMsgPrchReqDtl = new PRCH_REQ_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(tMsgPrchReqDtl.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsgPrchReqDtl.prchReqNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(tMsgPrchReqDtl.prchReqLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(tMsgPrchReqDtl.prchReqLineSubNum, prchReqLineSubNum);
        // QC#30086
        tMsgPrchReqDtl = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsgPrchReqDtl, dbWaitTime.intValue());

        if (tMsgPrchReqDtl == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgPrchReqDtl.getReturnCode())) {
            return false;
        }

        EZDTBLAccessor.logicalRemoveByPartialKey(tMsgPrchReqDtl);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgPrchReqDtl.getReturnCode())) {
            S21InfoLogOutput.println(NPAB112001Constant.NPAM1171E, new String[] {"PRCH_REQ_DTL" });
            S21InfoLogOutput.println(tMsgPrchReqDtl.toString());
            return false;
        }

        return true;
    }

    private void physicalRemovePrchReqInterface() {
        Map<String, Object> bindParams = new HashMap<String, Object>();
        bindParams.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, this.globalCompanyCode);
        bindParams.put(NPAB112001Constant.DB_PARAM_SALES_DATE, this.salesDate);
        bindParams.put(NPAB112001Constant.DB_PARAM_KEEP_PERIOD, NPAB112001Constant.LOGICAL_REMOVE_KEEP_PERIOD);

        List<Map<String, Object>> resultList = this.ssmBatchClient.queryObjectList("getPrchReqIntfcPkForPhysicalRemove", bindParams);

        for (Map<String, Object> result : resultList) {
            // Delete PRCH_REQ_INTFC
            PRCH_REQ_INTFCTMsg tMsg = new PRCH_REQ_INTFCTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqIntfcPk, (BigDecimal) result.get(NPAB112001Constant.PRCH_REQ_INTFC_PK));

            EZDDebugOutput.println(1, "Deleting PRCH_REQ_INTFC... Key=" + tMsg.prchReqIntfcPk.getValue().toPlainString(), this);
            EZDTBLAccessor.remove(tMsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(tMsg.getReturnCode()))) {
                S21InfoLogOutput.println(NPAB112001Constant.NPAM1298E);
            }
        }
    }

    private void physicalRemovePrchReq() {
        Map<String, Object> bindParams = new HashMap<String, Object>();
        bindParams.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, this.globalCompanyCode);
        bindParams.put(NPAB112001Constant.DB_PARAM_SALES_DATE, this.salesDate);
        bindParams.put(NPAB112001Constant.DB_PARAM_KEEP_PERIOD, NPAB112001Constant.LOGICAL_REMOVE_KEEP_PERIOD);

        List<Map<String, Object>> resultList = this.ssmBatchClient.queryObjectList("getPrchReqPkForPhysicalRemove", bindParams);

        String lastPrchReqNum = null;
        for (Map<String, Object> result : resultList) {

            if (lastPrchReqNum == null || !lastPrchReqNum.equals(result.get(NPAB112001Constant.PRCH_REQ_NUM))) {
                lastPrchReqNum = (String) result.get(NPAB112001Constant.PRCH_REQ_NUM);

                // Delete PRCH_REQ
                PRCH_REQTMsg prTMsg = new PRCH_REQTMsg();

                ZYPEZDItemValueSetter.setValue(prTMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(prTMsg.prchReqNum, (String) result.get(NPAB112001Constant.PRCH_REQ_NUM));

                EZDDebugOutput.println(1, "Deleting PRCH_REQ... Key=" + prTMsg.prchReqNum.getValue(), this);
                EZDTBLAccessor.remove(prTMsg);
                if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(prTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(prTMsg.getReturnCode()))) {
                    S21InfoLogOutput.println(NPAB112001Constant.NPAM1298E);
                    continue;
                }
            }

            // Delete PRCH_REQ_DTL
            PRCH_REQ_DTLTMsg prDtlTMsg = new PRCH_REQ_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(prDtlTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(prDtlTMsg.prchReqNum, (String) result.get(NPAB112001Constant.PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(prDtlTMsg.prchReqLineNum, (String) result.get(NPAB112001Constant.PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(prDtlTMsg.prchReqLineSubNum, (BigDecimal) result.get(NPAB112001Constant.PRCH_REQ_LINE_SUB_NUM));

            EZDDebugOutput.println(1, "Deleting PRCH_REQ_DTL... Key=" + prDtlTMsg.prchReqNum.getValue() + "," + prDtlTMsg.prchReqLineNum.getValue() + "," + prDtlTMsg.prchReqLineSubNum.getValue().toPlainString(), this);
            EZDTBLAccessor.remove(prDtlTMsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(prDtlTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(prDtlTMsg.getReturnCode()))) {
                S21InfoLogOutput.println(NPAB112001Constant.NPAM1298E);
            }
        }
    }

    /**
     * Mod QC#26761
     * Create MRP_RPT_WRK Process
     * @param mrpRunPrmInfo MRP_RUN_PRMTMsg
     * @param rtlWhCd String
     * @param mrpRunSchdId String
     * @param isWh boolean
     * @param isMinMaxZero boolean
     */
    public void createMrpRptWrk(MRP_RUN_PRMTMsg mrpRunPrmInfo, String rtlWhCd, String mrpRunSchdId, boolean isWh, boolean isMinMaxZero) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB112001Constant.DB_PARAM_MRP_RUN_PRM_PK, mrpRunPrmInfo.mrpRunPrmPk.getValue());
            if (ZYPCommonFunc.hasValue(mrpRunPrmInfo.locTpCd) && LOC_TP.WAREHOUSE.equals(mrpRunPrmInfo.locTpCd.getValue())) {
                paramMap.put(NPAB112001Constant.DB_PARAM_RTL_WH_CD, rtlWhCd);
            }
            // QC#54719 2019/11/19 ADD START
            paramMap.put(NPAB112001Constant.DB_PARAM_MERC_CLS_CD, "A");
            paramMap.put(NPAB112001Constant.DB_PARAM_SUBSTR_START, NPAB112001Constant.SUBSTR_STR);
            paramMap.put(NPAB112001Constant.DB_PARAM_SUBSTR_END, NPAB112001Constant.SUBSTR_END);
            // QC#54719 2019/11/19 ADD END

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            // QC#26761
            if (isWh && isMinMaxZero) {
                createQueyParams(paramMap, mrpRunPrmInfo);
                preparedStatement = ssmLlcClient.createPreparedStatement("getMrpInformationDmndInfo", paramMap, execParam);
            } else if (isWh && !isMinMaxZero) {
                preparedStatement = ssmLlcClient.createPreparedStatement("getMrpInformationWh", paramMap, execParam);
            } else {
                preparedStatement = ssmLlcClient.createPreparedStatement("getMrpInformation", paramMap, execParam);
            }
            resultSet = preparedStatement.executeQuery();

            int reqCnt = 0;
            String wh = null;
            String swh = null;
            //08/17/2017 CITS S.Endo Add Sol#013(QC#18717) START
            boolean isWarehouseSupplier = false;
            boolean createSupplierFlg = false;
            BigDecimal reqQty = BigDecimal.ZERO;
            BigDecimal restReqQty = BigDecimal.ZERO;
            //08/17/2017 CITS S.Endo Add Sol#013(QC#18717) END

            while (resultSet.next()) {

                // QC#27012 ADD START
                boolean isDiscontinuedItem = false;
                // QC#27012 ADD END

                totalCount++;
                MRP_INFOTMsg mrpInfo = setDataMrpInfo(resultSet);
                RTL_WHTMsg rtlWhInfo = setRtlWhInfo(resultSet);

                if (((wh != null) && (swh != null)) && ((!wh.equals(mrpInfo.rtlWhCd.getValue())) || (!swh.equals(mrpInfo.rtlSwhCd.getValue())))) {
                    commit();

                }

                wh = mrpInfo.rtlWhCd.getValue();
                swh = mrpInfo.rtlSwhCd.getValue();

                // MRP Calculation
                NPZC101001PMsg mrpApi = callMRPCalcAPI(mrpInfo, mrpRunPrmInfo);

                if (0 < mrpApi.xxMsgIdList.getValidCount()) {

                    errorCount++;

                    // log output
                    for (int i = 0; i < mrpApi.xxMsgIdList.getValidCount(); i++) {
                        String apiMessage = S21MessageFunc.clspGetMessage(mrpApi.xxMsgIdList.no(i).xxMsgId.getValue());
                        String msg = String.format("NPZC1010 MRP Calculation API error. API Message[%s]. WH Code = %s, MDSE Code = %s", apiMessage, mrpInfo.rtlWhCd.getValue(), mrpInfo.mdseCd.getValue());
                        S21InfoLogOutput.println(NPAB112001Constant.NLCM0047E, new String[] {msg });
                    }

                    continue;
                }

                if (mrpApi.scheduledOrdList.no(0).prchReqQty.getValueInt() <= 0) {
                    continue;
                }

                if (ZYPConstant.FLG_ON_Y.equals(mrpRunPrmInfo.cratPrchReqFlg.getValue())) {

                    // QC#27012 ADD START
                    if (isDiscontinuedItem(mrpInfo.mdseCd.getValue())) {
                        // Discontinued Item

                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAB112001Constant.NPAM1621W)));
                        sb.append(String.format("%-17s", nullToBlank(mrpInfo.mdseCd.getValue())));
                        sb.append(String.format("%-70s", nullToBlank(mrpInfo.mrpPlnNm.getValue())));

                        warningMsgList.add(sb.toString());
                        errorCount++;

                        isDiscontinuedItem = true;

                    } else {

                        // Create Purchase Requisition.
                        boolean isTechFlag = rtlWhInfo.locTpCd.getValue().equals(LOC_TP.TECHNICIAN);

                        // 08/17/2017 CITS S.Endo Add
                        // Sol#013(QC#18717) START
                        isWarehouseSupplier = false;
                        createSupplierFlg = false;
                        reqQty = BigDecimal.ZERO;
                        restReqQty = BigDecimal.ZERO;
                        // QC#55711
                        String origMdsecd = mrpInfo.mdseCd.getValue();
                        boolean isExclFlg = Arrays.asList(this.exclWHs).contains(mrpInfo.rtlWhCd.getValue());
                        boolean isWHOnly = PROCR_TP.WAREHOUSE.equals(mrpInfo.procrTpCd.getValue());

                        if (PROCR_TP.WAREHOUSE_SUPPLIER.equals(mrpInfo.procrTpCd.getValue())) {
                            isWarehouseSupplier = true;
                        }
                        // QC#55711 logic all changes Start
                        if (isWarehouseSupplier) {

                            BigDecimal calcQty = mrpApi.scheduledOrdList.no(0).prchReqQty.getValue();
                            // START 2023/06/13 S.Dong [QC#55629, ADD]
                            if (isWh) {
                                NPZC136001PMsg pMsg = getMinVndQty(resultSet, reqQty, isTechFlag);
                                if (pMsg != null  && reqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
                                    calcQty = pMsg.prchReqQty.getValue();
                                }
                            }
                            // END 2023/06/13 S.Dong [QC#55629, ADD]
                            // change order take item
                            if (origMdsecd.length() >= NPAB112001Constant.LENGTH_ORD_TAKE_MDSE) {
                                // START 2020/10/31 [QC#57939,MOD]
//                                origMdsecd = getOrdTakeMdse(origMdsecd.substring(0, NPAB112001Constant.LENGTH_ORD_TAKE_MDSE));
                                origMdsecd = getOrdTakeMdse(origMdsecd);
                                // END 2020/10/31 [QC#57939,MOD]
                            }

                            // get MDSE list. old item and original item list
                            List<String> itemList = getMdseItemFlip(origMdsecd, isTechFlag, isExclFlg);

                            for (int i = 0; i < itemList.size(); i++) {
                                String mdseCd = itemList.get(i);
                                // get Available Inventory
                                BigDecimal result = getAvalWhInvty(mdseCd, mrpInfo.srcLocCd.getValue());

                                // check available stock quantity
                                if (result != null && result.compareTo(BigDecimal.ZERO) > 0) {
                                    // if reqQty - availQty > 0 then
                                    // create PR IF use availQty
                                    if (calcQty.compareTo(result) > 0) {
                                        reqQty = result;
                                        createSupplierFlg = true;
                                        restReqQty = calcQty.subtract(result);
                                    } else {
                                        // if reqQty - availQty =< 0 then
                                        // create PR IF use reqQty
                                        reqQty = calcQty;
                                        createSupplierFlg = false;
                                        ZYPEZDItemValueSetter.setValue(mrpInfo.mdseCd, mdseCd);
                                    }
                                    ZYPEZDItemValueSetter.setValue(mrpInfo.procrTpCd, PROCR_TP.WAREHOUSE);
                                    calcQty = calcQty.subtract(reqQty);

                                    if (!isExclFlg && !isTechFlag && i + 1 < itemList.size() && checkCreateIF(mrpInfo) && BigDecimal.ZERO.compareTo(calcQty) < 0) {

                                        PRCH_REQ_INTFCTMsg ifTMsg = setIFData(mrpInfo, mrpRunPrmInfo, rtlWhInfo, mrpApi, isTechFlag, reqQty, mdseCd);
                                        EZDTBLAccessor.insert(ifTMsg);

                                        S21InfoLogOutput.println(ifTMsg.toString());

                                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ifTMsg.getReturnCode())) {

                                            if (ifTMsg != null) {
                                                S21InfoLogOutput.println(ifTMsg.toString());
                                            }
                                            throw new S21AbendException(NPAB112001Constant.NPAM1172E, new String[] {"PRCH_REQ_INTFC" });
                                        }
                                    }

                                } else {
                                    ZYPEZDItemValueSetter.setValue(mrpInfo.procrTpCd, PROCR_TP.SUPPLIER);
                                    if (origMdsecd.equals(mdseCd)) {
                                        createSupplierFlg = false;
                                        reqQty = restReqQty;
                                    }
                                }

                                if (isExclFlg) {
                                    break;
                                }
                                if (BigDecimal.ZERO.compareTo(calcQty) >= 0) {
                                    break;
                                }
                            }
                        }

                        if (!isExclFlg && !isTechFlag && isWHOnly) {

                            boolean isOldItem = false;
                            BigDecimal calcQty = mrpApi.scheduledOrdList.no(0).prchReqQty.getValue();
                            // START 2023/06/13 S.Dong [QC#55629, ADD]
                            if (isWh) {
                                NPZC136001PMsg pMsg = getMinVndQty(resultSet, reqQty, isTechFlag);
                                if (pMsg != null  && reqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
                                    calcQty = pMsg.prchReqQty.getValue();
                                }
                            }
                            // END 2023/06/13 S.Dong [QC#55629, ADD]
                            // change order take item
                            if (origMdsecd.length() >= NPAB112001Constant.LENGTH_ORD_TAKE_MDSE) {
                                origMdsecd = getOrdTakeMdse(origMdsecd);
                            }

                            // get MDSE list. old item and original item list
                            List<String> itemList = getMdseItemFlip(origMdsecd, isTechFlag, isExclFlg);

                            for (int i = 0; i < itemList.size(); i++) {

                                String mdseCd = itemList.get(i);
                                // get Available Inventory
                                BigDecimal result = getAvalWhInvty(mdseCd, mrpInfo.srcLocCd.getValue());

                                // check available stock quantity
                                if (result != null && result.compareTo(BigDecimal.ZERO) > 0) {
                                    // if reqQty - availQty > 0 then
                                    // create PR IF use availQty
                                    if (calcQty.compareTo(result) > 0) {
                                        reqQty = result;
                                        restReqQty = calcQty.subtract(result);
                                    } else {
                                        // if reqQty - availQty =< 0 then
                                        // create PR IF use reqQty
                                        reqQty = calcQty;
                                        ZYPEZDItemValueSetter.setValue(mrpInfo.mdseCd, mdseCd);
                                    }

                                    ZYPEZDItemValueSetter.setValue(mrpInfo.procrTpCd, PROCR_TP.WAREHOUSE);
                                    calcQty = calcQty.subtract(reqQty);

                                    if (!isExclFlg && !isTechFlag && i + 1 < itemList.size() && checkCreateIF(mrpInfo) && BigDecimal.ZERO.compareTo(calcQty) < 0) {

                                        PRCH_REQ_INTFCTMsg ifTMsg = setIFData(mrpInfo, mrpRunPrmInfo, rtlWhInfo, mrpApi, isTechFlag, reqQty, mdseCd);
                                        EZDTBLAccessor.insert(ifTMsg);

                                        S21InfoLogOutput.println(ifTMsg.toString());

                                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ifTMsg.getReturnCode())) {

                                            if (ifTMsg != null) {
                                                S21InfoLogOutput.println(ifTMsg.toString());
                                            }
                                            throw new S21AbendException(NPAB112001Constant.NPAM1172E, new String[] {"PRCH_REQ_INTFC" });
                                        }
                                        isOldItem = true;
                                    }

                                }

                                if (isExclFlg) {
                                    break;
                                }
                                if (BigDecimal.ZERO.compareTo(calcQty) >= 0) {
                                    break;
                                } else if (!isOldItem && origMdsecd.equals(mdseCd)) {
                                    reqQty = reqQty.add(calcQty);
                                } else if (result != null && result.compareTo(BigDecimal.ZERO) > 0 && isOldItem && origMdsecd.equals(mdseCd)) {
                                    reqQty = reqQty.add(restReqQty);
                                } else if (isOldItem && origMdsecd.equals(mdseCd)) {
                                    reqQty = restReqQty;
                                }
                            }
                        }
                        // QC#55711 logic all changes end
                        // QC:51642 Start
                        if (checkCreateIF(mrpInfo)) {
                            // QC#55711
                            PRCH_REQ_INTFCTMsg ifTMsg = setIFData(mrpInfo, mrpRunPrmInfo, rtlWhInfo, mrpApi, isTechFlag, reqQty, mrpInfo.mdseCd.getValue());

                            // insert data
                            EZDTBLAccessor.insert(ifTMsg);

                            S21InfoLogOutput.println(ifTMsg.toString());

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ifTMsg.getReturnCode())) {

                                if (ifTMsg != null) {
                                    S21InfoLogOutput.println(ifTMsg.toString());
                                }

                                throw new S21AbendException(NPAB112001Constant.NPAM1172E, new String[] {"PRCH_REQ_INTFC" });
                            }
                            // 08/17/2017 CITS S.Endo Add
                            // Sol#013(QC#18717) START
                            if (isWarehouseSupplier && createSupplierFlg) {
                                ZYPEZDItemValueSetter.setValue(mrpInfo.procrTpCd, PROCR_TP.SUPPLIER);
                                // // QC#55711
                                ifTMsg = setIFData(mrpInfo, mrpRunPrmInfo, rtlWhInfo, mrpApi, isTechFlag, restReqQty, mrpInfo.mdseCd.getValue());
                                EZDTBLAccessor.insert(ifTMsg);

                                S21InfoLogOutput.println(ifTMsg.toString());

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ifTMsg.getReturnCode())) {
                                    if (ifTMsg != null) {
                                        S21InfoLogOutput.println(ifTMsg.toString());
                                    }
                                    throw new S21AbendException(NPAB112001Constant.NPAM1172E, new String[] {"PRCH_REQ_INTFC" });
                                }
                            }
                            // 08/17/2017 CITS S.Endo Add
                            // Sol#013(QC#18717) END

                        }
                        // QC:51642 End
                        reqCnt++;
                    }
                }

                // add report work
                // QC#27012 MOD START
                // START 2023/06/13 S.Dong [QC#55629, ADD]
                if (isWh) {
                    NPZC136001PMsg pMsg = getMinVndQty(resultSet, reqQty, true);
                    if (pMsg != null  && reqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
                        reqQty = pMsg.prchReqQty.getValue();
                    }
                }
                MRP_RPT_WRKTMsg reportData = setReportData(mrpInfo, mrpRunPrmInfo, rtlWhInfo, mrpApi, resultSet, mrpRunSchdId, isDiscontinuedItem, reqQty);
                // END 2023/06/13 S.Dong [QC#55629, ADD]
                // QC#27012 MOD END

                // insert data
                EZDTBLAccessor.insert(reportData);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(reportData.getReturnCode())) {

                    if (reportData != null) {
                        S21InfoLogOutput.println(reportData.toString());
                    }

                    throw new S21AbendException(NPAB112001Constant.NPAM1172E, new String[] {"MRP_RPT_WRK" });
                }
            }

            // update Completed. Mod QC#26761
            if (!isMinMaxZero) {
                updateMrpRunPrmCompleted(mrpRunPrmInfo.mrpRunPrmPk.getValue(), reqCnt);
                commit();
            }

        } catch (S21AbendException e) {

            rollback();
            throw e;

        } catch (SQLException e) {

            rollback();
            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    private String getWhName(String inStrRtlWhCd) {
        String strWhName = "";
        //Get Name
        if (ZYPCommonFunc.hasValue(inStrRtlWhCd) && !inStrRtlWhCd.equals("*")) {
            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, inStrRtlWhCd);

            RTL_WHTMsg resultTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);
            if (resultTMsg != null) {
                strWhName = resultTMsg.rtlWhNm.getValue();
            }
        }
        return strWhName;
    }
    private String getSwhName(String inStrRtlWhCd , String inStrRtlSwhCd) {
        String strSwhName = "";
        //Get Name
        if (ZYPCommonFunc.hasValue(inStrRtlWhCd) && !inStrRtlWhCd.equals("*")) {
            RTL_SWHTMsg rtlSWhTMsg = new RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlSWhTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(rtlSWhTMsg.rtlWhCd, inStrRtlWhCd);
            ZYPEZDItemValueSetter.setValue(rtlSWhTMsg.rtlSwhCd, inStrRtlSwhCd);

            RTL_SWHTMsg resultTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSWhTMsg);
            if (resultTMsg != null) {
                strSwhName = resultTMsg.rtlSwhNm.getValue();
            }
        }
        return strSwhName;
    }

    // QC#31199 Update method. add wh transfer.
    /**
     * <pre>
     * isPreApprovedWhOwnr
     * Set Approval Status to Pre-Approved if target WH is included in specified WH owners.
     * Condition:
     * + REC_TP is "PO Req"
     * + APVL_STS is "Entered" or "Submit for approval"
     * + Target WH's owner is in "PR_PREAPVL_WH_OWNR_CD"
     * </pre>
     * @param mrpInfo MRP_INFOTMsg
     * @return boolean
     */
    private boolean isPreApprovedWhOwnr(MRP_INFOTMsg mrpInfo) {

        // Common Logic
        String glblCmpyCd = getGlobalCompanyCode();
        String prPreapvlWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PR_PREAPVL_WH_OWNR_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(prPreapvlWhOwnrCd)) {
            return false;
        }

        String rtlWhCd = mrpInfo.rtlWhCd.getValue();
        String[] whOwnrCdArray = prPreapvlWhOwnrCd.split(",");
        for (int n = 0; n < whOwnrCdArray.length; ++n) {
            if (ZYPCommonFunc.hasValue(whOwnrCdArray[n])) {
                whOwnrCdArray[n] = whOwnrCdArray[n].trim();
            }
        }

        int count = countRtlWhInWhOwnr(glblCmpyCd, rtlWhCd, whOwnrCdArray);

        if (PROCR_TP.SUPPLIER.equals(mrpInfo.procrTpCd.getValue())) {
            return (count > 0);
        } else if (PROCR_TP.WAREHOUSE.equals(mrpInfo.procrTpCd.getValue())) {
            // Warehouse Transfer.DBS => SATELLITE
            String srcRtlWhCd = mrpInfo.srcRtlWhCd.getValue();
            String[] whOwnrCdDBS = new String[] {WH_OWNR.DBS };

            int dbsCount = countRtlWhInWhOwnr(glblCmpyCd, srcRtlWhCd, whOwnrCdDBS);

            return (count > 0) && (dbsCount > 0);
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * isSvcLvlWhOwnr
     * Set Service Level to Ground if target WH is included in specified WH owners.
     * Condition:
     * + REC_TP is "PO Req"
     * + Target WH's owner is in "PR_SVC_LVL_WH_OWNR_CD"
     * </pre>
     * @param mrpInfo MRP_INFOTMsg
     * @return boolean
     */
    private boolean isSvcLvlWhOwnr(MRP_INFOTMsg mrpInfo) {
        if (!PROCR_TP.SUPPLIER.equals(mrpInfo.procrTpCd.getValue())) {
            return false;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String prSvcLvlWhOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB112001Constant.PR_SVC_LVL_WH_OWNR_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(prSvcLvlWhOwnrCd)) {
            return false;
        }

        String rtlWhCd = mrpInfo.rtlWhCd.getValue();
        String[] whOwnrCdArray = prSvcLvlWhOwnrCd.split(",");
        for (int n = 0; n < whOwnrCdArray.length; ++n) {
            if (ZYPCommonFunc.hasValue(whOwnrCdArray[n])) {
                whOwnrCdArray[n] = whOwnrCdArray[n].trim();
            }
        }
        int count = countRtlWhInWhOwnr(glblCmpyCd, rtlWhCd, whOwnrCdArray);
        return (count > 0);
    }

    /**
     * countRtlWhInWhOwnr
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param whOwnrCdArray String[]
     * @return int
     */
    private int countRtlWhInWhOwnr(String glblCmpyCd, String rtlWhCd, String[] whOwnrCdArray) {
        HashMap<String, Object> bindParams = new HashMap<String, Object>();
        bindParams.put("glblCmpyCd", glblCmpyCd);
        bindParams.put("rtlWhCd", rtlWhCd);
        bindParams.put("whOwnrCdList", whOwnrCdArray);

        return (Integer) this.ssmBatchClient.queryObject("countRtlWhInWhOwnr", bindParams);
    }

    // QC#18019
    /**
     * getDsCondConstTMsg
     * @param glblCmpyCd String
     * @param dsCondConstGrpId String
     * @param dsCondConstCd String
     * @return DS_COND_CONSTTMsg
     */
    private DS_COND_CONSTTMsg getDsCondConstTMsg(String glblCmpyCd, String dsCondConstGrpId, String dsCondConstCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, dsCondConstGrpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, dsCondConstCd);
        DS_COND_CONSTTMsg resultTMsg = (DS_COND_CONSTTMsg) EZDTBLAccessor.findByKey(dsCondConstTMsg);

        if (resultTMsg == null) {
            throw new S21AbendException(NPAB112001Constant.NZZM0010E, new String[] {"DS_COND_CONST" + dsCondConstCd});
        }

        return resultTMsg;
    }

    /**
     * convert Request Time
     * @param time String 
     * @return String
     */
    public static String convertRequestTime(String time) {

        try {
            SimpleDateFormat sfd = new SimpleDateFormat("hh:mm a", Locale.US);
            Date rqstRcvTm = sfd.parse(time);
            sfd = new SimpleDateFormat("HHmm");
            return sfd.format(rqstRcvTm);
        } catch (ParseException e) {
            return "0000";
        }
    }


    // QC#27012 ADD START
    /**
     * 
     */
    private void registerMail() {

        /*************************************************************
         * 1. Get From
         ************************************************************/
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPAB112001Constant.MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;

        if (!addrFromList.isEmpty()) {

            from = addrFromList.get(0);
        }

        /*************************************************************
         * 2. Get To
         ************************************************************/
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, NPAB112001Constant.MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {

            throw new S21AbendException(NPAB112001Constant.NWBM0092E);
        }

        /*************************************************************
         * 3. Create message for Body
         ************************************************************/
        StringBuilder msgBuf = new StringBuilder();

        for (int i = 0; i < warningMsgList.size(); i++) {

            msgBuf.append((String) warningMsgList.get(i));
            msgBuf.append(NPAB112001Constant.LINE_FEED_CODE);
        }

        String message = msgBuf.toString();

        /*************************************************************
         * 4. Create Subject and Body
         ************************************************************/
        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB112001Constant.MAIL_TEMPLATE_ID);

        if (template == null) {

            throw new S21AbendException(NPAB112001Constant.NWBM0092E);
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(NPAB112001Constant.DATE_TIME_PATTERN_FOR_MAIL);

        template.setTemplateParameter(NPAB112001Constant.MAIL_TEMPLATE_KEY_BATCH_ID, NPAB112001Constant.BUSINESS_ID);
        template.setTemplateParameter(NPAB112001Constant.MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(NPAB112001Constant.MAIL_TEMPLATE_KEY_MESSAGE, message);

        /*************************************************************
         * 5. Call Mail API
         ************************************************************/
        S21Mail mail = new S21Mail(globalCompanyCode);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    /**
     * @param mdseCd
     * @return
     */
    private boolean isDiscontinuedItem(String mdseCd) {

        HashMap<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", globalCompanyCode);
        queryParam.put("mdseCd", mdseCd);

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("isDiscontinuedItem", queryParam);

        if (result != null && ZYPConstant.FLG_ON_Y.equals((String) result.get("DSCTN_FLG"))) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Convert Null to Blank
     * @param val String
     * @return String
     */
    private String nullToBlank(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }

    // QC#27012 ADD END

    /**
     * Add QC#26761
     */
    private Map<String, Object> createQueyParams(HashMap<String, Object> paramMap, MRP_RUN_PRMTMsg mrpRunPrmInfo) {
        String thruDt = NPAB112001Constant.MAX_YMD;
        if (ZYPCommonFunc.hasValue(mrpRunPrmInfo.dmndCtoffDt) || ZYPCommonFunc.hasValue(mrpRunPrmInfo.dmndCtoffDaysAot)) {
            String wkDmndFrom = salesDate;
            if (ZYPCommonFunc.hasValue(mrpRunPrmInfo.dmndCtoffDt)) {
                wkDmndFrom = mrpRunPrmInfo.dmndCtoffDt.getValue();
            }
            if (ZYPCommonFunc.hasValue(mrpRunPrmInfo.dmndCtoffDaysAot)) {
                thruDt = ZYPDateUtil.addDays(wkDmndFrom, mrpRunPrmInfo.dmndCtoffDaysAot.getValue().intValue());
            } else {
                thruDt = wkDmndFrom;
            }
        } else if (!ZYPCommonFunc.hasValue(mrpRunPrmInfo.dmndCtoffDt) && !ZYPCommonFunc.hasValue(mrpRunPrmInfo.dmndCtoffDaysAot)) {
            thruDt = NPAB112001Constant.MAX_YMD;
        }

        paramMap.put("thruDt", thruDt);
        paramMap.put("stkstsCd", stkStsCd);
        paramMap.put("booked", ORD_HDR_DPLY_STS.BOOKED);
        paramMap.put("validatd", SHPG_STS.VALIDATED);
        paramMap.put("inshed", SHPG_STS.INSHED);
        paramMap.put("techRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
        paramMap.put("open", PRCH_REQ_STS.OPEN);
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("kitting", PRCH_REQ_TP.KITTING);
        paramMap.put("dsSoLineShipped", DS_SO_LINE_STS.SHIPPED);
        paramMap.put("dsSoLineCanceld", DS_SO_LINE_STS.CANCELLED);
        paramMap.put("saved", WRK_ORD_STS.SAVED);
        paramMap.put("kit", DS_WRK_ORD_TP.KIT);
        paramMap.put("shpgStsShipped", SHPG_STS.SHIPPED);

        return paramMap;
    }

    /**
     * Add QC#26761
     * get Stock Status List
     * @return
     */
    private List<String> getStsCd() {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("GLBL_CMPY_CD", globalCompanyCode);
        queryParam.put("BIZ_APP_ID", "NPZC1010");

        return (List<String>) ssmBatchClient.queryObjectList("getStsCd", queryParam);
    }

    /**
     * Add QC#51642
     * checkCreateIF
     * @param mrpInfo
     * @return boolean True:Create IF False:Not Create IF
     */
    private boolean checkCreateIF(MRP_INFOTMsg mrpInfo) {

        if (ZYPCommonFunc.hasValue(mrpInfo.procrTpCd)) {
            PROCR_TPTMsg procrTpTmsg = new PROCR_TPTMsg();
            ZYPEZDItemValueSetter.setValue(procrTpTmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(procrTpTmsg.procrTpCd, mrpInfo.procrTpCd);

            procrTpTmsg = (PROCR_TPTMsg) EZDTBLAccessor.findByKey(procrTpTmsg);
            if ( procrTpTmsg != null && !ZYPCommonFunc.hasValue(procrTpTmsg.prchReqRecTpCd) ) {
                return false;
            }
        }

        return true;
    }

    /**
     * QC#55711 get Item flip
     */
    private List<String> getMdseItemFlip(String origMdsecd, boolean isTechFlag, boolean isExclFlg) {

        String targetMdseRelnTpCsv = ZYPCodeDataUtil.getVarCharConstValue("NPZC1010_CMPT_MDSE_RELN_TP", this.globalCompanyCode);
        String[] targetMdseRelnTpList = null;
        if(targetMdseRelnTpCsv != null){
            targetMdseRelnTpList =targetMdseRelnTpCsv.split(",");
        }

        List<String> itemList = NPXC001001GetMdseRelationshipData.getMdseRelationshipData(this.globalCompanyCode, origMdsecd, targetMdseRelnTpList);

        return itemList;
    }

    /**
     * QC#55711 Get Available Inventory
     */
    private BigDecimal getAvalWhInvty(String mdseCd, String srcLocCd) {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(NPAB112001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        paramMap.put(NPAB112001Constant.DB_PARAM_MDSE_CD, mdseCd);
        paramMap.put(NPAB112001Constant.DB_PARAM_INVTY_LOC_CD, srcLocCd);
        paramMap.put(NPAB112001Constant.DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        paramMap.put(NPAB112001Constant.DB_PARAM_STK_STS_CD, STK_STS.GOOD);

        return (BigDecimal) this.ssmBatchClient.queryObject("getAvalWhInvty", paramMap);

    }

    /**
     * QC#55711 get Order Take Item Code
     */
    private String getOrdTakeMdse(String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdse = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.ordTakeMdseCd, mdseCd.substring(0, NPAB112001Constant.LENGTH_ORD_TAKE_MDSE));
        ordTakeMdse = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeMdse);

        if (ordTakeMdse == null) {
            return mdseCd;
        } else {
            return ordTakeMdse.mdseCd.getValue();
        }

    }
    // START 2023/06/13 S.Dong [QC#55629, ADD]
    // get Vendor min Qty from ASL
    private NPZC136001PMsg getMinVndQty(ResultSet rs, BigDecimal reqQty, boolean isTechFlag) throws SQLException {
        NPZC136001PMsg pMsg = getVndMinOrdQty(rs, reqQty, isTechFlag);
        return pMsg;
    }

    private NPZC136001PMsg getVndMinOrdQty(ResultSet rs, BigDecimal reqQty, boolean isTechFlag) throws SQLException {

        NPZC136001 api = new NPZC136001();
        NPZC136001PMsg param = new NPZC136001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(param.prchReqQty, reqQty);
        if (isTechFlag) {
            ZYPEZDItemValueSetter.setValue(param.prchReqRecTpCd, PRCH_REQ_REC_TP.TECH_REQUEST);
        }
        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    // END 2023/06/13 S.Dong [QC#55629, ADD]
}
