/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB114001;

import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.CRAT_PRCH_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.DATE_TIME_PRINT_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.DMND_CTOFF_DAYS_AOT;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.DMND_CTOFF_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.HAIFUN;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.LOC_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_PLN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_RPT_PRINT_PROC_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_RPT_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_RPT_WRK_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_RUN_PRM_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.MRP_RUN_SCHD_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.NLCM0047E;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.NPAB1140_PRNT_NOFOUND_MSG;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.NPAM1172E;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.NPAM1173E;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.PRINT_ERROR_MSG;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.PRINT_ITEM_DESC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.PRINT_ITEM_STS_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.PRINT_PROC_STS_ERROR;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.PRINT_PROC_STS_SUCCESS;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.PRINT_PROC_STS_UNPROCESSED;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_MRP_ENBL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_MRP_INFO_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_MRP_RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_MRP_RUN_PRM_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_MRP_RUN_SCHD_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.P_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.REPORT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.REPORT_TITLE;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.RTL_SWH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.RTL_WH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.SPACE;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.SPLY_CTOFF_DAYS_AOT;
import static com.canon.cusa.s21.batch.NPA.NPAB114001.constant.NPAB114001Constant.SPLY_CTOFF_DT;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.LOC_TPTMsg;
import business.db.MRP_RPT_WRKTMsg;
import business.db.MRP_RUN_PRMTMsg;
import business.db.MRP_RUN_SCHDTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_RUN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * MRP Run Post-process Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/31/2016   CITS            K.Ogino         Create          N/A
 * 05/23/2016   CITS            K.Ogino         Create          QC#8226
 * 08/12/2016   CSAI            Y.Imazu         Update          QC#12751
 * 11/16/2016   CITS            K.Ogino         Update          QC#16002
 * 02/03/2017   CITS            K.Fukumura      Update          QC#17221
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19867
 * 10/04/2017   CITS            K.Fukumura      Update          QC#21230
 * 10/18/2017   CITS            K.Ogino         Update          QC#21230
 * 08/06/2018   CITS            T.Tokutomi      Update          QC#26898
 * 10/25/2018   CITS            K.Ogino         Update          QC#28919
 *</pre>
 */

public class NPAB114001 extends S21BatchMain {

    /** global company code */
    private String glblCmpyCd = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** lineBizTpCd */
    private String lineBizTpCd = null;

    /** rtlWhCd */
    private String rtlWhCd = null;
    // QC#21230 Start
    /** rtlWhCd */
    private String mrpPlnNm = null;
    // QC#21230 End
    /** rtlSwhCd */
    private String rtlSwhCd = null;

    /** locTpCd */
    private String locTpCd = null;

    /** term code */
    private TERM_CD termCd;

    /** normal records count */
    private int normalCount = 0;

    /** error records count */
    private int errorCount = 0;

    /** total records count */
    private int totalCount = 0;

    /** S21EIPPrintingService */
    private S21EIPPrintingService service = null;

    /** Printing Service count */
    private int printCount = 0;

    @Override
    protected void initRoutine() {
        // get global company code
        this.profileService = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = profileService.getGlobalCompanyCode();
        this.termCd = TERM_CD.ABNORMAL_END;

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {GLBL_CMPY_CD });
        }
    }

    @Override
    protected void mainRoutine() {
        getMrpRunPrm();
        if (errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        } else {
            this.termCd = TERM_CD.NORMAL_END;
        }
    }

    @Override
    protected void termRoutine() {
        if (printCount > 0) {
            // If success, activate Create Report Processing.
            this.service.activateAsyncReportJob();
        }
        // Set End Code and Commit Count
        setTermState(this.termCd, normalCount, errorCount, totalCount);
    }

    /**
     * @param args String
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB114001().executeBatch(NPAB114001.class.getSimpleName());
    }

    /**
     * find target data
     */
    private void getMrpRunPrm() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            List<String> mrpRptPrintProcStsCdList = new ArrayList<String>(2);
            mrpRptPrintProcStsCdList.add(PRINT_PROC_STS_UNPROCESSED);
            mrpRptPrintProcStsCdList.add(PRINT_PROC_STS_ERROR);
            paramMap.put(MRP_RPT_PRINT_PROC_STS_CD_LIST, mrpRptPrintProcStsCdList);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpRunPrm", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            // Initialize printing service object.
            this.service = new S21EIPPrintingService();

            while (resultSet.next()) {

                totalCount++;
                BigDecimal mrpRunPrmPk = resultSet.getBigDecimal(MRP_RUN_PRM_PK);
                String mrpRunSchdId = resultSet.getString(MRP_RUN_SCHD_ID);

                // QC#16002
                this.lineBizTpCd = resultSet.getString(LINE_BIZ_TP_CD);

                this.rtlWhCd = resultSet.getString(RTL_WH_CD);

                this.rtlSwhCd = resultSet.getString(RTL_SWH_CD);

                this.locTpCd = resultSet.getString(LOC_TP_CD);

                // QC#21230 Start
                this.mrpPlnNm = getMrpPlanName(mrpRunPrmPk);
                // QC#21230 End
                if (mrpRunSchdId == null) {
                    // online request
                    setMrpInfoWhInfo(mrpRunPrmPk);
                }

                if (requestPdfReportCreation(mrpRunPrmPk, mrpRunSchdId)) {
                    normalCount++;
                } else {
                    errorCount++;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    // QC#21230 Start
    private String getMrpPlanName(BigDecimal mrpRunPrmPk) {

        String strMrpPlanNm = null;

        if (!ZYPCommonFunc.hasValue(mrpRunPrmPk)) {
            return strMrpPlanNm;
        }
        S21SsmBatchClient glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(P_MRP_RUN_PRM_PK, mrpRunPrmPk);
        paramMap.put(P_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
        paramMap.put(P_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);


        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getMrpInfoPlanName", paramMap);

        if (resultList != null && resultList.size() > 0) {
            strMrpPlanNm = (String) resultList.get(0).get(MRP_PLN_NM);
        }
        return strMrpPlanNm;
    }
    // QC#21230 End
    /**
     * EIP Reporting process
     * @param mrpRunPrmPk BigDecimal
     * @param mrpRunSchdId String
     * @return boolean true:success/false:faild
     */
    private boolean requestPdfReportCreation(BigDecimal mrpRunPrmPk, String mrpRunSchdId) {

        // Update MRP_RPT_PRINT_RQS_SQ to MRP_RPT_WRK and MRP_RUN_PRM
        if (ZYPCommonFunc.hasValue(mrpRunPrmPk)) {

            if (!updateMrpRunRptPrintRqsSq(mrpRunPrmPk, mrpRunSchdId)) {

                rollback();
                return false;
            }

        } else {

            if (!updateMrpRunGrp(mrpRunSchdId, mrpRunSchdId)) {

                rollback();
                return false;
            }
        }

        commit();
        return true;
    }

    /**
     * update MRP_RPT_PRINT_RQS_SQ to MRP_RUN_PRM Table
     * @param mrpRunPrmPk BigDecimal
     * @param mrpRunSchdId String
     * @return boolean true:success/false:faild
     */
    private boolean updateMrpRunRptPrintRqsSq(BigDecimal mrpRunPrmPk, String mrpRunSchdId) {

        MRP_RUN_PRMTMsg tMsg = new MRP_RUN_PRMTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpRunPrmPk, mrpRunPrmPk);
        tMsg = (MRP_RUN_PRMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        boolean ret = false;

        if (tMsg != null) {

            if (ZYPCommonFunc.hasValue(tMsg.locTpCd) && LOC_TP.WAREHOUSE.equals(tMsg.locTpCd.getValue())) {

                ret = updateMrpRptWrkWh(tMsg, mrpRunPrmPk, mrpRunSchdId);

            } else {

                ret = updateMrpRptWrk(tMsg, mrpRunPrmPk, mrpRunSchdId, null, null);

            }

            // QC#28919 No Data Found Report
            createNoDataReport(tMsg, mrpRunPrmPk);

            return ret;
        }

        S21InfoLogOutput.println(NLCM0047E, new String[] {ZYPCommonFunc.concatString("MRP_RUN_PRMT PK=", tMsg.mrpRunPrmPk.getValue().toString(), null) });
        return false;
    }

    /**
     * update MRP_RPT_PRINT_RQS_SQ to MRP_RPT_WRK Table
     * @param mrpRptWrkPk BigDecimal
     * @param mrpRptPrintRqstSq BigDecimal
     * @param mrpRunPrmPk BigDecimal
     * @param mrpRunSchdId String
     * @return boolean true:success/false:faild
     */
    private boolean updateMrpRptWrkPk(BigDecimal mrpRptWrkPk, BigDecimal mrpRptPrintRqstSq, BigDecimal mrpRunPrmPk, String mrpRunSchdId) {

        MRP_RPT_WRKTMsg tMsg = new MRP_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mrpRptWrkPk, mrpRptWrkPk);
        tMsg = (MRP_RPT_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.mrpRptPrintRqstSq, mrpRptPrintRqstSq);
            EZDTBLAccessor.update(tMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                return true;

            } else if (EZDTBLAccessor.RTNCD_NOT_FOUND.equals(tMsg.getReturnCode())) {

                insertMrpRptWrk(mrpRptPrintRqstSq, mrpRunPrmPk, mrpRunSchdId);
                return true;
            }
        }

        S21InfoLogOutput.println(NLCM0047E, new String[] {ZYPCommonFunc.concatString("MRP_RPT_WRK PK=", tMsg.mrpRptWrkPk.getValue().toString(), null) });
        return false;
    }

    /**
     * Insert MRP_RPT_WRK
     * @param mrpRptPrintRqstSq BigDecimal
     * @param mrpRunPrmPk BigDecimal
     * @param mrpRunSchdId String
     */
    private void insertMrpRptWrk(BigDecimal mrpRptPrintRqstSq, BigDecimal mrpRunPrmPk, String mrpRunSchdId) {

        S21SsmBatchClient glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String wkRtlWhNm = null;
        String wkRtlSwhNm = null;

        if (ZYPCommonFunc.hasValue(this.rtlWhCd) && ZYPCommonFunc.hasValue(this.rtlSwhCd)) {

            paramMap.put(P_GLBL_CMPY_CD, this.glblCmpyCd);
            paramMap.put(P_RTL_WH_CD, this.rtlWhCd);
            paramMap.put(P_RTL_SWH_CD, this.rtlSwhCd);

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getDummyMrpPrtWrkInfo", paramMap);

            if (resultList != null && resultList.size() > 0) {

                for (Map<String, Object> resultRec : resultList) {

                    wkRtlWhNm = (String) resultRec.get(RTL_WH_NM);
                    wkRtlSwhNm = (String) resultRec.get(RTL_SWH_NM);
                }
            }
        }

        if (wkRtlWhNm == null && ZYPCommonFunc.hasValue(this.locTpCd)) {

            LOC_TPTMsg locTPtMsg = new LOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(locTPtMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(locTPtMsg.locTpCd, this.locTpCd);
            LOC_TPTMsg resultTMsg = (LOC_TPTMsg) EZDTBLAccessor.findByKey(locTPtMsg);

            if (resultTMsg != null) {

                wkRtlWhNm = this.lineBizTpCd + SPACE + HAIFUN + SPACE + resultTMsg.locTpDescTxt.getValue();
            }
        }

        // Insert the dummy record of MRP_RPT_WRK
        MRP_RPT_WRKTMsg mrpRptWrktMsg = new MRP_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RPT_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRunPrmPk, mrpRunPrmPk);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRunSchdId, mrpRunSchdId);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRptPrintTxt, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PRINT_FORMAT));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhCd, this.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhNm, wkRtlWhNm);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhCd, this.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhNm, wkRtlSwhNm);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mdseDescShortTxt, ZYPCodeDataUtil.getVarCharConstValue(NPAB1140_PRNT_NOFOUND_MSG, this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRptPrintRqstSq, mrpRptPrintRqstSq);

        // Data Set For Cover Page 
        MRP_RUN_PRMTMsg tMsgCoverPage = new MRP_RUN_PRMTMsg();
        if (!getMrpRunPrmForCoverPage(mrpRunPrmPk , tMsgCoverPage)) {
            throw new S21AbendException(NPAM1172E, new String[] {"MRP_RPT_WRK"});
        }
        if (ZYPCommonFunc.hasValue(tMsgCoverPage.dmndCtoffDt)) {
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.dmndCtoffDtTxt , ZYPDateUtil.formatEzd8ToSysDisp(tMsgCoverPage.dmndCtoffDt.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.dmndCtoffDaysAot , tMsgCoverPage.dmndCtoffDaysAot);
        if (ZYPCommonFunc.hasValue(tMsgCoverPage.splyCtoffDt)) {
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.splyCtoffDtTxt , ZYPDateUtil.formatEzd8ToSysDisp(tMsgCoverPage.splyCtoffDt.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.splyCtoffDaysAot , tMsgCoverPage.splyCtoffDaysAot);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.cratPrchReqFlg , tMsgCoverPage.cratPrchReqFlg);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.printItemStsFlg , tMsgCoverPage.printItemStsFlg);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.printItemDescFlg , tMsgCoverPage.printItemDescFlg);
        //ONL_BAT_RQST_FLG
        if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            //N:Batch (MRP_RUN_SCHD_ID has Value)
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.onlBatRqstFlg , ZYPConstant.FLG_OFF_N);
        } else {
            //Y:Online(MRP_RUN_SCHD_ID IS NULL)
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.onlBatRqstFlg , ZYPConstant.FLG_ON_Y);
        }
        //PLN_LVL_DESC_TXT(Online Only)
        if (!ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            if (ZYPCommonFunc.hasValue(tMsgCoverPage.mrpPlnNm)) {
                ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.plnLvlDescTxt , "Plan Name");
            } else {
                ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.plnLvlDescTxt , "Sub-Warehouse");
            }
        }

        // QC#21230 Start
        // ****************************
        // Set MRP_PLN_NM_SRCH_TXT
        // ****************************
        // ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpPlnNmSrchTxt , tMsgCoverPage.mrpPlnNm);
        String strMrpPlnNmSrchTxt = tMsgCoverPage.mrpPlnNm.getValue();
        if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            // (Batch)
            if (LOC_TP.WAREHOUSE.equals(this.locTpCd)) {
                // Wharehouse Search
                strMrpPlnNmSrchTxt = this.mrpPlnNm;
            } else if (LOC_TP.TECHNICIAN.equals(this.locTpCd)) {
                // Zone(Technician) Search
                strMrpPlnNmSrchTxt = ZYPCommonFunc.concatString(this.lineBizTpCd, " ", this.getLocTypeName(this.locTpCd));
            }
        } else if (ZYPCommonFunc.hasValue(tMsgCoverPage.rtlWhCd.getValue())) {
            // (Online) and Whrehouse Search
            strMrpPlnNmSrchTxt = this.mrpPlnNm;
        }
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpPlnNmSrchTxt , strMrpPlnNmSrchTxt);
        // QC#21230 End
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhCdSrchTxt , tMsgCoverPage.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhNmSrchTxt , getWhName(tMsgCoverPage.rtlWhCd.getValue()));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhCdSrchTxt , tMsgCoverPage.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhNmSrchTxt , getSwhName(tMsgCoverPage.rtlWhCd.getValue() , tMsgCoverPage.rtlSwhCd.getValue()));

        EZDTBLAccessor.insert(mrpRptWrktMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mrpRptWrktMsg.getReturnCode())) {

            throw new S21AbendException(NPAM1172E, new String[] {"MRP_RPT_WRK"});
        }
    }

    /**
     * Insert MRP_RPT_WRK
     * @param mrpRptPrintRqstSq BigDecimal
     * @param mrpRunPrmPk BigDecimal
     * @param mrpRunSchdId String
     * @param mrpPlnNmSrchTxt Sring
     */
    private void insertMrpRptWrk4Nm(BigDecimal mrpRptPrintRqstSq, BigDecimal mrpRunPrmPk, String mrpRunSchdId, String mrpPlnNmSrchTxt) {

        S21SsmBatchClient glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        Map<String, Object> paramMap = new HashMap<String, Object>();

        String wkRtlWhNm = null;
        String wkRtlSwhNm = null;

        if (ZYPCommonFunc.hasValue(this.rtlWhCd) && ZYPCommonFunc.hasValue(this.rtlSwhCd)) {

            paramMap.put(P_GLBL_CMPY_CD, this.glblCmpyCd);
            paramMap.put(P_RTL_WH_CD, this.rtlWhCd);
            paramMap.put(P_RTL_SWH_CD, this.rtlSwhCd);

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getDummyMrpPrtWrkInfo", paramMap);

            if (resultList != null && resultList.size() > 0) {

                for (Map<String, Object> resultRec : resultList) {

                    wkRtlWhNm = (String) resultRec.get(RTL_WH_NM);
                    wkRtlSwhNm = (String) resultRec.get(RTL_SWH_NM);
                }
            }
        }

        if (wkRtlWhNm == null && ZYPCommonFunc.hasValue(this.locTpCd)) {

            LOC_TPTMsg locTPtMsg = new LOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(locTPtMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(locTPtMsg.locTpCd, this.locTpCd);
            LOC_TPTMsg resultTMsg = (LOC_TPTMsg) EZDTBLAccessor.findByKey(locTPtMsg);

            if (resultTMsg != null) {

                wkRtlWhNm = this.lineBizTpCd + SPACE + HAIFUN + SPACE + resultTMsg.locTpDescTxt.getValue();
            }
        }

        // Insert the dummy record of MRP_RPT_WRK
        MRP_RPT_WRKTMsg mrpRptWrktMsg = new MRP_RPT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RPT_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRunPrmPk, mrpRunPrmPk);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRunSchdId, mrpRunSchdId);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRptPrintTxt, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PRINT_FORMAT));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhCd, this.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhNm, wkRtlWhNm);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhCd, this.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhNm, wkRtlSwhNm);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mdseDescShortTxt, ZYPCodeDataUtil.getVarCharConstValue(NPAB1140_PRNT_NOFOUND_MSG, this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpRptPrintRqstSq, mrpRptPrintRqstSq);

        // Data Set For Cover Page 
        MRP_RUN_PRMTMsg tMsgCoverPage = new MRP_RUN_PRMTMsg();
        if (!getMrpRunPrmForCoverPage(mrpRunPrmPk , tMsgCoverPage)) {
            throw new S21AbendException(NPAM1172E, new String[] {"MRP_RPT_WRK"});
        }
        if (ZYPCommonFunc.hasValue(tMsgCoverPage.dmndCtoffDt)) {
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.dmndCtoffDtTxt , ZYPDateUtil.formatEzd8ToSysDisp(tMsgCoverPage.dmndCtoffDt.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.dmndCtoffDaysAot , tMsgCoverPage.dmndCtoffDaysAot);
        if (ZYPCommonFunc.hasValue(tMsgCoverPage.splyCtoffDt)) {
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.splyCtoffDtTxt , ZYPDateUtil.formatEzd8ToSysDisp(tMsgCoverPage.splyCtoffDt.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.splyCtoffDaysAot , tMsgCoverPage.splyCtoffDaysAot);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.cratPrchReqFlg , tMsgCoverPage.cratPrchReqFlg);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.printItemStsFlg , tMsgCoverPage.printItemStsFlg);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.printItemDescFlg , tMsgCoverPage.printItemDescFlg);
        //ONL_BAT_RQST_FLG
        if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            //N:Batch (MRP_RUN_SCHD_ID has Value)
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.onlBatRqstFlg , ZYPConstant.FLG_OFF_N);
        } else {
            //Y:Online(MRP_RUN_SCHD_ID IS NULL)
            ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.onlBatRqstFlg , ZYPConstant.FLG_ON_Y);
        }
        //PLN_LVL_DESC_TXT(Online Only)
        if (!ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            if (ZYPCommonFunc.hasValue(mrpPlnNmSrchTxt)) {
                ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.plnLvlDescTxt , "Plan Name");
            } else if (ZYPCommonFunc.hasValue(tMsgCoverPage.mrpPlnNm)) {
                ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.plnLvlDescTxt , "Plan Name");
            } else {
                ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.plnLvlDescTxt , "Sub-Warehouse");
            }
        }

        // QC#21230 Start
        // ****************************
        // Set MRP_PLN_NM_SRCH_TXT
        // ****************************
        // ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpPlnNmSrchTxt , tMsgCoverPage.mrpPlnNm);
        String strMrpPlnNmSrchTxt = "";
        if (ZYPCommonFunc.hasValue(mrpPlnNmSrchTxt)) {
            strMrpPlnNmSrchTxt = mrpPlnNmSrchTxt;
        } else {
            strMrpPlnNmSrchTxt = tMsgCoverPage.mrpPlnNm.getValue();
        }
        if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            // (Batch)
            if (LOC_TP.WAREHOUSE.equals(this.locTpCd)) {
                // Wharehouse Search
                strMrpPlnNmSrchTxt = mrpPlnNmSrchTxt;
            } else if (LOC_TP.TECHNICIAN.equals(this.locTpCd)) {
                // Zone(Technician) Search
                strMrpPlnNmSrchTxt = ZYPCommonFunc.concatString(this.lineBizTpCd, " ", this.getLocTypeName(this.locTpCd));
            }
        } else if (ZYPCommonFunc.hasValue(tMsgCoverPage.rtlWhCd.getValue())) {
            // (Online) and Whrehouse Search
            strMrpPlnNmSrchTxt = this.mrpPlnNm;
        }
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.mrpPlnNmSrchTxt , strMrpPlnNmSrchTxt);
        // QC#21230 End
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhCdSrchTxt , tMsgCoverPage.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlWhNmSrchTxt , getWhName(tMsgCoverPage.rtlWhCd.getValue()));
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhCdSrchTxt , tMsgCoverPage.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(mrpRptWrktMsg.rtlSwhNmSrchTxt , getSwhName(tMsgCoverPage.rtlWhCd.getValue() , tMsgCoverPage.rtlSwhCd.getValue()));

        EZDTBLAccessor.insert(mrpRptWrktMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mrpRptWrktMsg.getReturnCode())) {

            throw new S21AbendException(NPAM1172E, new String[] {"MRP_RPT_WRK"});
        }
    }

    // QC#21230 Start
    private String getLocTypeName(String strLocTypeCd) {
        String strLocTypeName = "";
        //Get Name
        if (ZYPCommonFunc.hasValue(strLocTypeCd)) {
            LOC_TPTMsg locTPtMsg = new LOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(locTPtMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(locTPtMsg.locTpCd, strLocTypeCd);

            LOC_TPTMsg resultTMsg = (LOC_TPTMsg) EZDTBLAccessor.findByKey(locTPtMsg);
            if (resultTMsg != null) {
                strLocTypeName = resultTMsg.locTpDescTxt.getValue();
            }
        }
        return strLocTypeName;
    }
    // QC#21230 End

    /**
     * Update MRP_RPT_WRK
     * @param tMsg MRP_RUN_PRMTMsg
     * @param mrpRunPrmPk BigDecimal
     * @param mrpRunSchdId String
     * @param mrpRtlWhCd String
     * @param mrpPlnNmSrchTxt String
     * @return boolean true:success/false:faild
     */
    private boolean updateMrpRptWrk(MRP_RUN_PRMTMsg tMsg, BigDecimal mrpRunPrmPk, String mrpRunSchdId, String mrpRtlWhCd, String mrpPlnNmSrchTxt) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get MRP_RPT_PRINT_RQST_SQ Number
        BigDecimal mrpRptPrintRqstSq = ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RPT_PRINT_RQST_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.mrpRptPrintRqstSq, mrpRptPrintRqstSq);
        EZDTBLAccessor.update(tMsg);

        if (EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

            try {

                HashMap<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
                paramMap.put(P_MRP_RUN_PRM_PK, mrpRunPrmPk);
                if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
                    paramMap.put(P_MRP_RUN_SCHD_ID, mrpRunSchdId);
                }
                if (ZYPCommonFunc.hasValue(mrpRtlWhCd)) {
                    paramMap.put(P_RTL_WH_CD, mrpRtlWhCd);
                }

                S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                execParam.setFetchSize(FETCH_SIZE);
                execParam.setMaxRows(0);

                preparedStatement = ssmLlcClient.createPreparedStatement("getMrpRptWrkPks", paramMap, execParam);
                resultSet = preparedStatement.executeQuery();

                int rsltCnt = 0;

                while (resultSet.next()) {

                    BigDecimal mrpRptWrkPk = resultSet.getBigDecimal(MRP_RPT_WRK_PK);

                    if (!updateMrpRptWrkPk(mrpRptWrkPk, mrpRptPrintRqstSq, mrpRunPrmPk, mrpRunSchdId)) {

                        return false;
                    }

                    rsltCnt++;
                }

                // Create MRP_RPT_WRK
                if (rsltCnt == 0) {

                    insertMrpRptWrk4Nm(mrpRptPrintRqstSq, mrpRunPrmPk, mrpRunSchdId, mrpPlnNmSrchTxt);
                }

                // call EIP Common function
                if (!writeReport(mrpRptPrintRqstSq, mrpRunPrmPk, mrpRunSchdId, mrpRtlWhCd, mrpPlnNmSrchTxt)) {

                    rollback();
                    return false;
                }

            } catch (SQLException e) {

                sqlExceptionHandler(e);

            } finally {

                S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
            }
        }

        return true;
    }

    /**
     * find MRP_RUN_PRM PK
     * @param condMrpRunSchdId String
     * @param mrpRunSchdId String
     * @return boolean true:success/false:faild
     */
    private boolean updateMrpRunGrp(String condMrpRunSchdId, String mrpRunSchdId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RUN_SCHD_ID, condMrpRunSchdId);
            List<String> mrpRptPrintProcStsCdList = new ArrayList<String>(2);
            mrpRptPrintProcStsCdList.add(PRINT_PROC_STS_UNPROCESSED);
            mrpRptPrintProcStsCdList.add(PRINT_PROC_STS_ERROR);
            paramMap.put(MRP_RPT_PRINT_PROC_STS_CD_LIST, mrpRptPrintProcStsCdList);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpRunPrmGrpPks", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                BigDecimal mrpRunPrmPk = resultSet.getBigDecimal(MRP_RUN_PRM_PK);

                if (!updateMrpRunRptPrintRqsSq(mrpRunPrmPk, mrpRunSchdId)) {

                    return false;
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return true;
    }

    /**
     * writeReport
     * @param mrpRptPrintRqstSq BigDecimal
     * @param mrpRunSchdId String
     * @parma mrpRtlWhCd String
     * @param mrpPlnNmSrchTxt String
     * @return boolean true:success/false:faild
     */
    private boolean writeReport(BigDecimal mrpRptPrintRqstSq, BigDecimal mrpRunPrmPk , String mrpRunSchdId, String mrpRtlWhCd, String mrpPlnNmSrchTxt) {

        // Generate S21ReportRequestBean
        S21ReportRequestBean requestBean = new S21ReportRequestBean(REPORT_ID);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);

        // Data Set For Cover Page 
        MRP_RUN_PRMTMsg tMsgCoverPage = new MRP_RUN_PRMTMsg();
        if (!getMrpRunPrmForCoverPage(mrpRunPrmPk , tMsgCoverPage)) {
            return false;
        }

        String rptTtlNm = "";
        if (ZYPCommonFunc.hasValue(this.lineBizTpCd) && !this.lineBizTpCd.equals("*")) {
            rptTtlNm = ZYPCommonFunc.concatString(mrpRptPrintRqstSq.toPlainString(), SPACE, this.lineBizTpCd);
            // Get Name
            LOC_TPTMsg locTPtMsg = new LOC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(locTPtMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(locTPtMsg.locTpCd, this.locTpCd);

            LOC_TPTMsg resultTMsg = (LOC_TPTMsg) EZDTBLAccessor.findByKey(locTPtMsg);
            if (resultTMsg != null) {
                String strLineBizTpNm = resultTMsg.locTpDescTxt.getValue();
                rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm , SPACE, strLineBizTpNm);
            }
        } else {
            rptTtlNm = ZYPCommonFunc.concatString(mrpRptPrintRqstSq.toPlainString(), null, null);
        }

        if (ZYPCommonFunc.hasValue(mrpRtlWhCd)) {
            rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, mrpRtlWhCd);
        } else {
            if (ZYPCommonFunc.hasValue(this.rtlWhCd) && !this.rtlWhCd.equals("*")) {
                rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, this.rtlWhCd);
            } else {
                rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, null, null);
            }
        }

        // QC#21230
        String strMrpPlnNmSrchTxt = tMsgCoverPage.mrpPlnNm.getValue();
        if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {
            // (Batch)
            if (LOC_TP.WAREHOUSE.equals(this.locTpCd)) {
                // Wharehouse Search
                strMrpPlnNmSrchTxt = this.mrpPlnNm;
            }
        } else if (ZYPCommonFunc.hasValue(tMsgCoverPage.rtlWhCd.getValue())) {
            // (Online) and Whrehouse Search
            strMrpPlnNmSrchTxt = this.mrpPlnNm;
        }

        if (ZYPCommonFunc.hasValue(strMrpPlnNmSrchTxt) && !"*".equals(strMrpPlnNmSrchTxt)) {
            rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, strMrpPlnNmSrchTxt);
        } else {
            rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, mrpPlnNmSrchTxt);
        }

        // Request(Y/N)
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, "Req");
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, tMsgCoverPage.cratPrchReqFlg.getValue());
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, null);
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, REPORT_TITLE);

        // NoData
        if (getPrintNoDataCount(mrpRptPrintRqstSq)) {
            rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, SPACE, "NoData");
        }

        requestBean.setRptTtlNm(rptTtlNm);

        // Set Report Input Parameter
        // QC#26898 Update advanced search parameter.
        S21InputParameter inputParam = requestBean.getInputParamBeanInstance();
        inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
        inputParam.addReportParameter(MRP_RPT_PRINT_RQST_SQ, mrpRptPrintRqstSq);
        inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter(MRP_PLN_NM, tMsgCoverPage.mrpPlnNm.getValue());
        inputParam.addReportParameter(RTL_WH_CD, this.rtlWhCd);
        requestBean.setInputParamBean(inputParam);

        // Create Request
        long requestPk = this.service.createReportByAsync(requestBean);
        boolean result = completeWriteReportMrp(requestPk, mrpRptPrintRqstSq, mrpRunSchdId);

        if (result == true) {

            if (ZYPCommonFunc.hasValue(mrpRunSchdId)) {

                result = completeWriteReportMrs(requestPk, mrpRunSchdId);
            }
        }

        printCount++;
        return result;
    }

    /**
     * completeWriteReportMrp
     * @param requestPk long
     * @param mrpRptPrintRqstSq BigDecimal
     * @param mrpRunSchdId BigDecimal
     * @return boolean true:success/false:faild
     */
    private boolean completeWriteReportMrp(long requestPk, BigDecimal mrpRptPrintRqstSq, String mrpRunSchdId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RPT_PRINT_RQST_SQ, mrpRptPrintRqstSq);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpRunPrmWithSeq", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                BigDecimal mrpRunPrmPk = resultSet.getBigDecimal(MRP_RUN_PRM_PK);
                MRP_RUN_PRMTMsg tMsg = new MRP_RUN_PRMTMsg();
                tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
                tMsg.mrpRunPrmPk.setValue(mrpRunPrmPk);
                tMsg = (MRP_RUN_PRMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

                if (tMsg != null) {

                    if (requestPk != 0) {

                        tMsg.mrpRptPrintProcStsCd.setValue(PRINT_PROC_STS_SUCCESS);

                    } else {

                        tMsg.mrpRptPrintProcStsCd.setValue(PRINT_PROC_STS_ERROR);
                        tMsg.mrpRunPrmErrMsgTxt.setValue(PRINT_ERROR_MSG);
                    }

                    EZDTBLAccessor.update(tMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                        S21InfoLogOutput.println(NLCM0047E, new String[] {ZYPCommonFunc.concatString("MRP_RUN_PRM PK=", tMsg.mrpRunPrmPk.getValue().toString(), null) });
                        return false;
                    }

                } else {

                    S21InfoLogOutput.println(NLCM0047E, new String[] {ZYPCommonFunc.concatString("MRP_RUN_PRM PK=", mrpRunPrmPk.toString(), null) });
                    return false;
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return true;
    }

    /**
     * completeWriteReportMrs
     * @param requestPk long
     * @param mrpRunSchdId String
     * @return boolean true:success/false:faild
     */
    private boolean completeWriteReportMrs(long requestPk, String mrpRunSchdId) {

        MRP_RUN_SCHDTMsg tMsg = new MRP_RUN_SCHDTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.mrpRunSchdId.setValue(mrpRunSchdId);
        tMsg = (MRP_RUN_SCHDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        if (tMsg != null) {

            tMsg.mrpRunLastRqstTs.setValue(ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));

            if (requestPk != 0) {

                tMsg.mrpRunStsCd.setValue(MRP_RUN_STS.COMPLETED);

            } else {

                tMsg.mrpRunStsCd.setValue(MRP_RUN_STS.ERROR_OCCURRED);
            }

            EZDTBLAccessor.update(tMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {

                return true;
            }
        }

        S21InfoLogOutput.println(NLCM0047E, new String[] {ZYPCommonFunc.concatString("MRP_RUN_SCHD_ID=", mrpRunSchdId, null) });
        return false;
    }

    /**
     * getMrpInfoWhInfo
     * @parma mrpRunPrmPk BigDecimal
     * @return boolean true:success/false:faild
     */
    private void setMrpInfoWhInfo(BigDecimal mrpRunPrmPk) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RUN_PRM_PK, mrpRunPrmPk);
            paramMap.put(P_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
            paramMap.put(P_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpInfoWhInfo", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                this.rtlWhCd = resultSet.getString(RTL_WH_CD);
                this.rtlSwhCd = resultSet.getString(RTL_SWH_CD);
                // QC#21230 Start
                this.mrpPlnNm = resultSet.getString(MRP_PLN_NM);
                // QC#21230 End
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    private boolean updateMrpRptWrkWh(MRP_RUN_PRMTMsg tMsg, BigDecimal mrpRunPrmPk, String mrpRunSchdId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RUN_PRM_PK, mrpRunPrmPk);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getRtlWhList", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                boolean ret = updateMrpRptWrk(tMsg, mrpRunPrmPk, resultSet.getString(MRP_RUN_SCHD_ID), resultSet.getString(RTL_WH_CD), resultSet.getString("MRP_PLN_NM_SRCH_TXT"));

                if (!ret) {
                    return false;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return true;
    }

    private boolean getMrpRunPrmForCoverPage(BigDecimal mrpRunPrmPk , MRP_RUN_PRMTMsg tMsgCoverPage) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RUN_PRM_PK, mrpRunPrmPk);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            // Read MRP_RUN_PRM
            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpRunPrmCoverPageData", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();
            BigDecimal mrpRunPrmPkCheck = null;
            while (resultSet.next()) {
                // One Record
                mrpRunPrmPkCheck = resultSet.getBigDecimal(MRP_RUN_PRM_PK);
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.mrpPlnNm, resultSet.getString(MRP_PLN_NM));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.rtlWhCd, resultSet.getString(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.rtlSwhCd, resultSet.getString(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.dmndCtoffDt, resultSet.getString(DMND_CTOFF_DT));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.dmndCtoffDaysAot, resultSet.getBigDecimal(DMND_CTOFF_DAYS_AOT));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.splyCtoffDt, resultSet.getString(SPLY_CTOFF_DT));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.splyCtoffDaysAot, resultSet.getBigDecimal(SPLY_CTOFF_DAYS_AOT));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.cratPrchReqFlg, resultSet.getString(CRAT_PRCH_REQ_FLG));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.printItemStsFlg, resultSet.getString(PRINT_ITEM_STS_FLG));
                ZYPEZDItemValueSetter.setValue(tMsgCoverPage.printItemDescFlg, resultSet.getString(PRINT_ITEM_DESC_FLG));
            }
            if (mrpRunPrmPkCheck == null) {
                return false;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return true;
    }

    private String getWhName(String inStrRtlWhCd) {
        String strWhName = "";
        //Get Name
        if (ZYPCommonFunc.hasValue(inStrRtlWhCd) && !inStrRtlWhCd.equals("*")) {
            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, this.glblCmpyCd);
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
            ZYPEZDItemValueSetter.setValue(rtlSWhTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rtlSWhTMsg.rtlWhCd, inStrRtlWhCd);
            ZYPEZDItemValueSetter.setValue(rtlSWhTMsg.rtlSwhCd, inStrRtlSwhCd);

            RTL_SWHTMsg resultTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSWhTMsg);
            if (resultTMsg != null) {
                strSwhName = resultTMsg.rtlSwhNm.getValue();
            }
        }
        return strSwhName;
    }
    private boolean getPrintNoDataCount(BigDecimal mrpRptPrintRqstSq) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        boolean flgExist = false;

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RPT_PRINT_RQST_SQ, mrpRptPrintRqstSq);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getNoDataFoundCount", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBigDecimal("CNT").equals(resultSet.getBigDecimal("NO_DATA_FOUND_CNT"))) {
                    flgExist = true;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return flgExist;
    }

    private boolean createNoDataReport(MRP_RUN_PRMTMsg tMsg, BigDecimal mrpRunPrmPk) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(P_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(P_MRP_RUN_PRM_PK, mrpRunPrmPk);
            List<String> mrpRptPrintProcStsCdList = new ArrayList<String>(2);
            mrpRptPrintProcStsCdList.add(PRINT_PROC_STS_UNPROCESSED);
            mrpRptPrintProcStsCdList.add(PRINT_PROC_STS_ERROR);
            paramMap.put(MRP_RPT_PRINT_PROC_STS_CD_LIST, mrpRptPrintProcStsCdList);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getNoDataReport", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                boolean ret = updateMrpRptWrk(tMsg, mrpRunPrmPk, resultSet.getString(MRP_RUN_SCHD_ID), resultSet.getString(RTL_WH_CD), resultSet.getString("MRP_PLN_NM"));

                if (!ret) {
                    return false;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return true;
    }
}
