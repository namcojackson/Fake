/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB113001;

import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.CRAT_PRCH_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_DEL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_FLG_VAL_Y;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_MRP_ENBL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_MRP_INFO_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_MRP_PLN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_MRP_RUN_LAST_RQST_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_MRP_RUN_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_PRCH_PLN_AVAL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_RPLSH_WEEK;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_RUN_TM;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DB_PARAM_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DMND_CTOFF_DAYS_AOT;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.DMND_CTOFF_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.ITM_CNT;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.LINE_FEED_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.LOC_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_MSG_HEADER;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_TEMPLATE_KEY_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_TEMPLATE_KEY_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MAIL_TEMPLATE_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_INFO;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_INFO_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_PLN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_GRP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_GRP_ITEM_CNT;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_PRM;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_PRM_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_PRM_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_SCHD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.MRP_RUN_SCHD_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.NASM0006E;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.NASM0007E;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.NPAM0078E;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.NWBM0092E;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.PRINT_ITEM_DESC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.SPLY_CTOFF_DAYS_AOT;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.SPLY_CTOFF_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.STAR;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.TIME_PATTERN_HH24MI;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.TIME_PATTERN_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.TIME_PATTERN_YYYYMMDD;
import static com.canon.cusa.s21.batch.NPA.NPAB113001.constant.NPAB113001Constant.VAR_CHAR_CONST_NM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MRP_INFOTMsg;
import business.db.MRP_RUN_MODETMsg;
import business.db.MRP_RUN_PRMTMsg;
import business.db.MRP_RUN_SCHDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_RUN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
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
 * <pre>
 * NPAB113001:MRP Run Pre-Process Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            K.Ogino         Create          N/A
 * 2016/04/11   CITS            K.Ogino         Update          QC#6837
 * 2016/04/13   CITS            K.Ogino         Update          QC#6572
 * 2016/04/14   CITS            K.Ogino         Update          QC#7024
 * 2016/05/16   CITS            K.Ogino         Update          QC#7025
 * 07/12/2017   CITS            Y.Imazu         Update          QC#19866
 * 07/12/2017   CITS            Y.Imazu         Update          QC#19865
 * 10/04/2017   CITS            K.Fukumura      Update          QC#21230
 *</pre>
 */
public class NPAB113001 extends S21BatchMain {

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** term code */
    private TERM_CD termCd;

    /** normal records count */
    private int normalCount = 0;

    /** error records count */
    private int errorCount = 0;

    /** Grouping data list */
    private List<Map<String, Object>> glGroupList = null;

    /* QC#21230 Start
    /** Grouping data pk list */
//    private List<Map<String, Object>> glGroupPkGrpIdList = null;

    /** Grouping data pk list */
    private List<Map<String, Object>> glGroupItemCoountArray = null;
    /* QC#21230 End

    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 START */
    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();
    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 END */

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB113001().executeBatch(NPAB113001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.profileService = S21UserProfileServiceFactory.getInstance().getService();
        this.globalCompanyCode = profileService.getGlobalCompanyCode();
        this.termCd = TERM_CD.ABNORMAL_END;

        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAM0078E, new String[] {GLBL_CMPY_CD });
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAM0078E, new String[] {SALES_DATE });
        }

    }

    @Override
    protected void mainRoutine() {

        getPlan(LOC_TP.TECHNICIAN);
        getPlan(LOC_TP.WAREHOUSE);

        if (errorCount > 0) {

            this.termCd = TERM_CD.WARNING_END;

            /* 07/12/2017 CSAI Y.Imazu Add QC#19865 START */
            if (!this.errMsgList.isEmpty()) {

                // Register Mail
                registerMail();

                // Commit
                commit();
            }
            /* 07/12/2017 CSAI Y.Imazu Add QC#19865 END */

        } else {

            this.termCd = TERM_CD.NORMAL_END;
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, normalCount, errorCount);
    }

    /**
     * find target plan
     * @param locTpCd String
     */
    private void getPlan(String locTpCd) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(DB_PARAM_LOC_TP_CD, locTpCd);
            paramMap.put(DB_PARAM_RUN_TM, ZYPDateUtil.getCurrentSystemTime(TIME_PATTERN_HH24MI));
            paramMap.put(DB_PARAM_MRP_RUN_LAST_RQST_TS, ZYPDateUtil.getCurrentSystemTime(TIME_PATTERN_YYYYMMDD));

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getTargetPlan", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            MRP_RUN_MODETMsg mrpTMsg = initGrouping(locTpCd);
            if (mrpTMsg != null) {
                // Grouping Ids
                ArrayList<String> groupingIdList = getGroupingId(locTpCd, mrpTMsg.mrpRunMultCntNum.getValueInt());
                // QC#21230 Start
                // Get MRP_RUN_PRM SQ Number
//                glGroupPkGrpIdList = new ArrayList<Map<String, Object>>(groupingIdList.size());
//                for (String mrpRunGrpId : groupingIdList) {
//                    BigDecimal mrpRunPrmSq = ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RUN_PRM_SQ);
//                    // MRP Parameter Pks
//                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put(MRP_RUN_GRP_ID, mrpRunGrpId);
//                    map.put(MRP_RUN_PRM_PK, mrpRunPrmSq);
//                    glGroupPkGrpIdList.add(map);
//                }
                glGroupItemCoountArray = new ArrayList<Map<String, Object>>(groupingIdList.size());
                for (String mrpRunGrpId : groupingIdList) {
                    // MRP Parameter Pks
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(MRP_RUN_GRP_ID, mrpRunGrpId);
                    map.put(MRP_RUN_GRP_ITEM_CNT, BigDecimal.ZERO);
                    glGroupItemCoountArray.add(map);
                }
                // QC#21230 End
            }

            while (resultSet.next()) {
                getTrnData(resultSet, locTpCd);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    /**
     * find grouping info
     * @param locTpCd String
     * @return MRP_RUN_MODETMsg
     */
    private MRP_RUN_MODETMsg initGrouping(String locTpCd) {
        MRP_RUN_MODETMsg mrpTMsg = new MRP_RUN_MODETMsg();
        mrpTMsg.glblCmpyCd.setValue(globalCompanyCode);
        mrpTMsg.locTpCd.setValue(locTpCd);
        mrpTMsg = (MRP_RUN_MODETMsg) EZDTBLAccessor.findByKey(mrpTMsg);
        if (mrpTMsg == null) {
            mrpTMsg = new MRP_RUN_MODETMsg();
            mrpTMsg.glblCmpyCd.setValue(globalCompanyCode);
            mrpTMsg.locTpCd.setValue(STAR);
            mrpTMsg = (MRP_RUN_MODETMsg) EZDTBLAccessor.findByKey(mrpTMsg);
        }
        return mrpTMsg;
    }

    /**
     * create grouping id
     * @param locTpCd String
     * @param groupingCount int
     * @return ArrayList<String>
     */
    private ArrayList<String> getGroupingId(String locTpCd, int groupingCount) {
        ArrayList<String> groupingIdList = new ArrayList<String>();
        for (int i = 0; i < groupingCount; i++) {
            groupingIdList.add(ZYPCommonFunc.concatString(locTpCd, String.valueOf(i), "0"));
        }
        return groupingIdList;
    }

    /**
     * find target transaction data
     * @param tecPlanResultSet ResultSet
     * @param locTpCd String
     */
    private void getTrnData(ResultSet tecPlanResultSet, String locTpCd) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            if (LOC_TP.TECHNICIAN.equals(locTpCd)) {
                paramMap.put(DB_PARAM_LINE_BIZ_TP_CD, tecPlanResultSet.getString(LINE_BIZ_TP_CD));
            }
            paramMap.put(DB_PARAM_DEL_FLG, ZYPConstant.FLG_OFF_N);
            paramMap.put(DB_PARAM_SALES_DATE, salesDate);
            paramMap.put(DB_PARAM_MRP_PLN_NM, tecPlanResultSet.getString(MRP_PLN_NM));
            paramMap.put(DB_PARAM_RTL_WH_CD, tecPlanResultSet.getString(RTL_WH_CD));
            paramMap.put(DB_PARAM_RTL_SWH_CD, tecPlanResultSet.getString(RTL_SWH_CD));
            paramMap.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(DB_PARAM_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
            int week = ZYPDateUtil.getDayOfWeek(salesDate);
            paramMap.put(DB_PARAM_RPLSH_WEEK, week);
            paramMap.put(DB_PARAM_FLG_VAL_Y, ZYPConstant.FLG_ON_Y);
            paramMap.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            paramMap.put(DB_PARAM_PRCH_PLN_AVAL_FLG, ZYPConstant.FLG_ON_Y);
            List<String> runStsCdList = new ArrayList<String>(2);
            runStsCdList.add(MRP_RUN_STS.READY_FOR_RUN);
            runStsCdList.add(MRP_RUN_STS.PROCESSING);
            paramMap.put(DB_PARAM_MRP_RUN_STS_CD_LIST, runStsCdList);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            if (LOC_TP.TECHNICIAN.equals(locTpCd)) {
                preparedStatement = ssmLlcClient.createPreparedStatement("getTrnDatForTec", paramMap, execParam);
            } else {
                preparedStatement = ssmLlcClient.createPreparedStatement("getTrnDatForWh", paramMap, execParam);
            }
            resultSet = preparedStatement.executeQuery();

            String mrpRunSchdId = tecPlanResultSet.getString(MRP_RUN_SCHD_ID);
            setGroup(tecPlanResultSet, mrpRunSchdId, resultSet);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

// QC#21230 Start
//    /**
//     * grouping process
//     * @param tecPlanResultSet ResultSet
//     * @param mrpRunSchdId String
//     * @param trnDataResultSet ResultSet
//     */
//    private void setGroup(ResultSet tecPlanResultSet, String mrpRunSchdId, ResultSet trnDataResultSet) {
//        int trnCount = 0;
//        int groupCount = 0;
//        int[] groupItemCounts = null;
//        glGroupList = new ArrayList<Map<String, Object>>();
//
//        try {
//            Map<String, Integer> containsRtlWhMap = new HashMap<String, Integer>();
//            if (glGroupPkGrpIdList != null && glGroupPkGrpIdList.size() > 0) {
//                groupItemCounts = new int[glGroupPkGrpIdList.size()];
//                while (trnDataResultSet.next()) {
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 START */
//                    // normalCount++;
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 END */
//                    String rtlWh = trnDataResultSet.getString(RTL_WH_CD);
//                    String rtlSwh = trnDataResultSet.getString(RTL_SWH_CD);
//                    BigDecimal itmCnt = trnDataResultSet.getBigDecimal(ITM_CNT);
//
//                    if (trnCount == 0 || containsRtlWhMap.containsKey(rtlWh)) {
//                        // 1st loop or Same group
//                        if (trnCount > 0) {
//                            groupCount = containsRtlWhMap.get(rtlWh);
//                        }
//                        Map<String, Object> groupPkMap = glGroupPkGrpIdList.get(groupCount);
//                        String mrpRunGrpId = (String) groupPkMap.get(MRP_RUN_GRP_ID);
//                        BigDecimal mrpRunPrmPk = (BigDecimal) groupPkMap.get(MRP_RUN_PRM_PK);
//                        Map<String, Object> groupMap = new HashMap<String, Object>();
//                        groupMap.put(RTL_WH_CD, rtlWh);
//                        groupMap.put(RTL_SWH_CD, rtlSwh);
//                        groupMap.put(ITM_CNT, itmCnt);
//                        groupMap.put(MRP_RUN_PRM_PK, mrpRunPrmPk);
//                        groupMap.put(MRP_RUN_GRP_ID, mrpRunGrpId);
//                        groupMap.put(MRP_RUN_SCHD_ID, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
//                        glGroupList.add(groupMap);
//                        groupItemCounts[groupCount] += itmCnt.intValue();
//                        containsRtlWhMap.put(rtlWh, groupCount);
//                    } else {
//                        int min = groupItemCounts[0];
//                        for (int i = 0; i < groupItemCounts.length; i++) {
//                            if (min > groupItemCounts[i]) {
//                                min = groupItemCounts[i];
//                                groupCount = i;
//                            }
//                        }
//                        // Other group
//                        Map<String, Object> groupPkMap = glGroupPkGrpIdList.get(groupCount);
//                        String mrpRunGrpId = (String) groupPkMap.get(MRP_RUN_GRP_ID);
//                        BigDecimal mrpRunPrmPk = (BigDecimal) groupPkMap.get(MRP_RUN_PRM_PK);
//                        Map<String, Object> groupMap = new HashMap<String, Object>();
//                        groupMap.put(RTL_WH_CD, rtlWh);
//                        groupMap.put(RTL_SWH_CD, rtlSwh);
//                        groupMap.put(ITM_CNT, itmCnt);
//                        groupMap.put(MRP_RUN_PRM_PK, mrpRunPrmPk);
//                        groupMap.put(MRP_RUN_GRP_ID, mrpRunGrpId);
//                        groupMap.put(MRP_RUN_SCHD_ID, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
//                        glGroupList.add(groupMap);
//                        groupItemCounts[groupCount] += itmCnt.intValue();
//                        containsRtlWhMap.put(rtlWh, groupCount);
//                    }
//                    trnCount++;
//                }
//                if (trnCount == 0) {
//                    updMrpRunSch(mrpRunSchdId, MRP_RUN_STS.NO_RECORD_FOUND);
//                    commit();
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 START */
//                    // normalCount++;
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 END */
//                    return;
//                }
//            } else {
//                while (trnDataResultSet.next()) {
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 START */
//                    // normalCount++;
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 END */
//                    String rtlWh = trnDataResultSet.getString(RTL_WH_CD);
//                    String rtlSwh = trnDataResultSet.getString(RTL_SWH_CD);
//                    BigDecimal itmCnt = trnDataResultSet.getBigDecimal(ITM_CNT);
//                    Map<String, Object> groupMap = new HashMap<String, Object>();
//                    BigDecimal mrpRunPrmSq = ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RUN_PRM_SQ);
//                    groupMap.put(RTL_WH_CD, rtlWh);
//                    groupMap.put(RTL_SWH_CD, rtlSwh);
//                    groupMap.put(ITM_CNT, itmCnt);
//                    groupMap.put(MRP_RUN_PRM_PK, mrpRunPrmSq);
//                    groupMap.put(MRP_RUN_GRP_ID, null);
//                    groupMap.put(MRP_RUN_SCHD_ID, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
//                    glGroupList.add(groupMap);
//                    trnCount++;
//                }
//                if (trnCount == 0) {
//                    updMrpRunSch(mrpRunSchdId, MRP_RUN_STS.NO_RECORD_FOUND);
//                    commit();
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 START */
//                    // normalCount++;
//                    /* 07/12/2017 CSAI Y.Imazu Del QC#19866 END */
//                    return;
//                }
//            }
//            updMrpInfoProc(tecPlanResultSet);
//
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        }
//    }
// QC#21230 End

// QC#21230 Start
    /**
     * grouping process
     * @param tecPlanResultSet ResultSet
     * @param mrpRunSchdId String
     * @param trnDataResultSet ResultSet
     */
    private void setGroup(ResultSet tecPlanResultSet, String mrpRunSchdId, ResultSet trnDataResultSet) {
        int trnCount = 0;

        glGroupList = new ArrayList<Map<String, Object>>();

        String strGrpKeyWhPlan = null; // TRL_WH_CD + PLN_NM
        String strGrpID = null;
        BigDecimal numGrpItemCount = BigDecimal.ZERO;
        BigDecimal numPrmPK = BigDecimal.ZERO;

        Map<String, Object> mapWhPlanToGrpId = new HashMap<String, Object>();
        Map<String, Object> mapWhPlanToPrmPK = new HashMap<String, Object>();

        try {

            if (glGroupItemCoountArray != null && glGroupItemCoountArray.size() > 0) {

                if (LOC_TP.TECHNICIAN.equals(tecPlanResultSet.getString(LOC_TP_CD))) {
                    // *******************************************
                    // * Technician Proc
                    // *******************************************
                    // Target Group Search
                    strGrpID = (String) glGroupItemCoountArray.get(0).get(MRP_RUN_GRP_ID);
                    numGrpItemCount = (BigDecimal) glGroupItemCoountArray.get(0).get(MRP_RUN_GRP_ITEM_CNT);
                    numPrmPK = ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RUN_PRM_SQ);
                    // Min(ItemCount) Group Search (Item Count Minimum)
                    for (Map<String, Object> groupItemCount : glGroupItemCoountArray) {
                        BigDecimal numTmpCnt = (BigDecimal) groupItemCount.get(MRP_RUN_GRP_ITEM_CNT);
                        
                        if (numGrpItemCount.compareTo(numTmpCnt) > 0 ) {
                            strGrpID = (String) groupItemCount.get(MRP_RUN_GRP_ID);
                            numGrpItemCount = numTmpCnt;
                        }
                    }
                    while (trnDataResultSet.next()) {
                        UpdateGlGroupItemCoountArray(strGrpID, trnDataResultSet.getBigDecimal(ITM_CNT));
                        // ************************************************
                        // MRP_INFO Search Parameter Set
                        // ************************************************
                        Map<String, Object> groupMap = new HashMap<String, Object>();
                        groupMap.put(MRP_PLN_NM, trnDataResultSet.getString(MRP_PLN_NM));
                        groupMap.put(RTL_WH_CD, trnDataResultSet.getString(RTL_WH_CD));
                        groupMap.put(RTL_SWH_CD, trnDataResultSet.getString(RTL_SWH_CD));
                        groupMap.put(ITM_CNT, trnDataResultSet.getBigDecimal(ITM_CNT));
                        groupMap.put(MRP_RUN_PRM_PK, numPrmPK);
                        groupMap.put(MRP_RUN_GRP_ID, strGrpID);
                        groupMap.put(MRP_RUN_SCHD_ID, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
                        glGroupList.add(groupMap);
                        trnCount++;
                    }

                } else if (LOC_TP.WAREHOUSE.equals(tecPlanResultSet.getString(LOC_TP_CD))) {
                    // *******************************************
                    // * Wharehouse Proc
                    // *******************************************
                    while (trnDataResultSet.next()) {

                        strGrpKeyWhPlan = trnDataResultSet.getString(MRP_PLN_NM) + trnDataResultSet.getString(RTL_WH_CD);

                        if (mapWhPlanToGrpId.containsKey((strGrpKeyWhPlan))) {
                            // ******************************
                            // Wharehouse + PlanName Exists
                            // ******************************
                            strGrpID = (String) mapWhPlanToGrpId.get(strGrpKeyWhPlan);
                            numPrmPK = (BigDecimal) mapWhPlanToPrmPK.get(strGrpKeyWhPlan);
                            // Group Search
                            UpdateGlGroupItemCoountArray(strGrpID, trnDataResultSet.getBigDecimal(ITM_CNT));
                        } else {
                            // ******************************
                            // Wharehouse + PlanName First Record
                            // ******************************
                            // Target Group Search
                            strGrpID = (String) glGroupItemCoountArray.get(0).get(MRP_RUN_GRP_ID);
                            numGrpItemCount = (BigDecimal) glGroupItemCoountArray.get(0).get(MRP_RUN_GRP_ITEM_CNT);
                            numPrmPK = ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RUN_PRM_SQ);
                            // Min(ItemCount) Group Search (Item Count Minimum)
                            for (Map<String, Object> groupItemCount : glGroupItemCoountArray) {
                                BigDecimal numTmpCnt = (BigDecimal) groupItemCount.get(MRP_RUN_GRP_ITEM_CNT);
                                
                                if (numGrpItemCount.compareTo(numTmpCnt) > 0 ) {
                                    strGrpID = (String) groupItemCount.get(MRP_RUN_GRP_ID);
                                    numGrpItemCount = numTmpCnt;
                                }
                            }
                            // Group Total Item Count Add
                            UpdateGlGroupItemCoountArray(strGrpID, trnDataResultSet.getBigDecimal(ITM_CNT));

                            mapWhPlanToGrpId.put(strGrpKeyWhPlan, strGrpID);
                            mapWhPlanToPrmPK.put(strGrpKeyWhPlan, numPrmPK);
                        }
                        // ************************************************
                        // MRP_INFO Search Parameter Set
                        // ************************************************
                        Map<String, Object> groupMap = new HashMap<String, Object>();
                        groupMap.put(MRP_PLN_NM, trnDataResultSet.getString(MRP_PLN_NM));
                        groupMap.put(RTL_WH_CD, trnDataResultSet.getString(RTL_WH_CD));
                        groupMap.put(RTL_SWH_CD, trnDataResultSet.getString(RTL_SWH_CD));
                        groupMap.put(ITM_CNT, trnDataResultSet.getBigDecimal(ITM_CNT));
                        groupMap.put(MRP_RUN_PRM_PK, numPrmPK);
                        groupMap.put(MRP_RUN_GRP_ID, strGrpID);
                        groupMap.put(MRP_RUN_SCHD_ID, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
                        glGroupList.add(groupMap);
                        
                        trnCount++;
                    }
                }
            } else {
                while (trnDataResultSet.next()) {
                    String rtlWh = trnDataResultSet.getString(RTL_WH_CD);
                    String rtlSwh = trnDataResultSet.getString(RTL_SWH_CD);
                    BigDecimal itmCnt = trnDataResultSet.getBigDecimal(ITM_CNT);
                    Map<String, Object> groupMap = new HashMap<String, Object>();
                    BigDecimal mrpRunPrmSq = ZYPOracleSeqAccessor.getNumberBigDecimal(MRP_RUN_PRM_SQ);
                    groupMap.put(MRP_PLN_NM, trnDataResultSet.getString(MRP_PLN_NM));
                    groupMap.put(RTL_WH_CD, trnDataResultSet.getString(RTL_WH_CD));
                    groupMap.put(RTL_SWH_CD, trnDataResultSet.getString(RTL_SWH_CD));
                    groupMap.put(ITM_CNT, trnDataResultSet.getBigDecimal(ITM_CNT));
                    groupMap.put(MRP_RUN_PRM_PK, mrpRunPrmSq);
                    groupMap.put(MRP_RUN_GRP_ID, null);
                    groupMap.put(MRP_RUN_SCHD_ID, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
                    glGroupList.add(groupMap);
                    trnCount++;
                }
            }

            if (trnCount == 0) {
                updMrpRunSch(mrpRunSchdId, MRP_RUN_STS.NO_RECORD_FOUND);
                commit();
                return;
            }
            updMrpInfoProc(tecPlanResultSet);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }
    /**
     * update GlGroupItemCoountArray (GroupID ItemCount Add)
     * @param strGrpId
     * @param numAddItemCount
     */
    private void UpdateGlGroupItemCoountArray(String strGrpId , BigDecimal numAddItemCount) {
        // Search Group Id
        for (Map<String, Object> groupItemCount : glGroupItemCoountArray) {
            if (strGrpId.equals((String) groupItemCount.get(MRP_RUN_GRP_ID))) {
                // Group Total Item Count Add
                BigDecimal numGrpItemCount = (BigDecimal) groupItemCount.get(MRP_RUN_GRP_ITEM_CNT);
                groupItemCount.put(MRP_RUN_GRP_ITEM_CNT, numGrpItemCount.add(numAddItemCount));
            }
        }
    }
// QC#21230 End

    /**
     * update MRP_INFO table
     * @param tecPlanResultSet
     */
    private void updMrpInfoProc(ResultSet tecPlanResultSet) {
        // QC#21230 Start
        insMrpRunPrm(tecPlanResultSet);
        // QC#21230 End
        for (Map<String, Object> groupDat : glGroupList) {
            getMrpInfoPk(groupDat);
        }
        // QC#21230 Start
        // insMrpRunPrm(tecPlanResultSet);
        // QC#21230 End
    }

    /**
     * find MRP_INFO Pk
     * @param groupDat Map<String, Object>
     */
    private void getMrpInfoPk(Map<String, Object> groupDat) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            // QC#21230 Start
            paramMap.put(DB_PARAM_MRP_PLN_NM, (String) groupDat.get(MRP_PLN_NM));
            // QC#21230 End
            paramMap.put(DB_PARAM_RTL_WH_CD, (String) groupDat.get(RTL_WH_CD));
            paramMap.put(DB_PARAM_RTL_SWH_CD, (String) groupDat.get(RTL_SWH_CD));
            paramMap.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(DB_PARAM_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
            int week = ZYPDateUtil.getDayOfWeek(salesDate);
            paramMap.put(DB_PARAM_RPLSH_WEEK, week);
            paramMap.put(DB_PARAM_FLG_VAL_Y, ZYPConstant.FLG_ON_Y);
            paramMap.put(DB_PARAM_PRCH_PLN_AVAL_FLG, ZYPConstant.FLG_ON_Y);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

            preparedStatement = ssmLlcClient.createPreparedStatement("getMrpInfoPk", paramMap, execParam);

            resultSet = preparedStatement.executeQuery();

            BigDecimal mrpRunPrmPk = (BigDecimal) groupDat.get(MRP_RUN_PRM_PK);
            updMrpInfo(resultSet, mrpRunPrmPk);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    /**
     * update MRP_INFO process
     * @param mrpInfoPkList ResultSet
     * @param mrpRunPrmPk BigDecimal
     */
    private void updMrpInfo(ResultSet mrpInfoPkList, BigDecimal mrpRunPrmPk) {
        try {
            while (mrpInfoPkList.next()) {
                BigDecimal mrpInfoPk = mrpInfoPkList.getBigDecimal(MRP_INFO_PK);

                MRP_INFOTMsg tMsg = new MRP_INFOTMsg();
                tMsg.glblCmpyCd.setValue(globalCompanyCode);
                tMsg.mrpInfoPk.setValue(mrpInfoPk);
                tMsg = (MRP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

                tMsg.mrpRunPrmPk.setValue(mrpRunPrmPk);

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    /* 07/12/2017 CSAI Y.Imazu Mod QC#19865 START */
                    setErrMsg(MRP_INFO, tMsg.mrpInfoPk.getValue().toString(), NASM0007E);
                    // S21InfoLogOutput.println(NLCM0047E, new String[] {tMsg.mrpInfoPk.getValue().toString() });
                    // errorCount++;
                    // rollback();
                    /* 07/12/2017 CSAI Y.Imazu Mod QC#19865 END */
                    return;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /**
     * update MRP_RUN_SCHD process
     * @param mrpRunSchdId String
     * @param mrpRunStsCd String
     */
    private void updMrpRunSch(String mrpRunSchdId, String mrpRunStsCd) {
        MRP_RUN_SCHDTMsg tMsg = new MRP_RUN_SCHDTMsg();
        tMsg.glblCmpyCd.setValue(globalCompanyCode);
        tMsg.ezCancelFlag.setValue(ZYPConstant.FLG_OFF_0);
        tMsg.mrpRunSchdId.setValue(mrpRunSchdId);
        tMsg = (MRP_RUN_SCHDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        tMsg.mrpRunLastRqstTs.setValue(ZYPDateUtil.getCurrentSystemTime(TIME_PATTERN_TS));
        tMsg.mrpRunStsCd.setValue(mrpRunStsCd);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            /* 07/12/2017 CSAI Y.Imazu Mod QC#19865 START */
            setErrMsg(MRP_RUN_SCHD, tMsg.mrpRunSchdId.getValue().toString(), NASM0007E);
            // S21InfoLogOutput.println(NLCM0047E, new String[] {tMsg.mrpRunSchdId.getValue().toString() });
            // errorCount++;
            // rollback();
            /* 07/12/2017 CSAI Y.Imazu Mod QC#19865 END */
            return;
        }
    }

    /**
     * insert MRP_RUN_PRM process
     * @param tecPlanResultSet ResultSet
     */
    private void insMrpRunPrm(ResultSet tecPlanResultSet) {
        try {
            for (Map<String, Object> groupDat : glGroupList) {
                MRP_RUN_PRMTMsg tMsg = new MRP_RUN_PRMTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(tMsg.mrpRunPrmPk, (BigDecimal) groupDat.get(MRP_RUN_PRM_PK));

                MRP_RUN_PRMTMsg outTMsg = (MRP_RUN_PRMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
                if (outTMsg != null) {
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(tMsg.mrpRunRqstTs, ZYPDateUtil.getCurrentSystemTime(TIME_PATTERN_TS));
                ZYPEZDItemValueSetter.setValue(tMsg.mrpRunRqstByCd, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM, globalCompanyCode));
                ZYPEZDItemValueSetter.setValue(tMsg.mrpRunSchdId, tecPlanResultSet.getString(MRP_RUN_SCHD_ID));
                ZYPEZDItemValueSetter.setValue(tMsg.mrpRunStsCd, MRP_RUN_STS.READY_FOR_RUN);
                ZYPEZDItemValueSetter.setValue(tMsg.mrpRunGrpId, (String) groupDat.get(MRP_RUN_GRP_ID));
                ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, tecPlanResultSet.getString(LINE_BIZ_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.mrpPlnNm, tecPlanResultSet.getString(MRP_PLN_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, tecPlanResultSet.getString(RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, tecPlanResultSet.getString(RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.dmndCtoffDt, tecPlanResultSet.getString(DMND_CTOFF_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.dmndCtoffDaysAot, tecPlanResultSet.getBigDecimal(DMND_CTOFF_DAYS_AOT));
                ZYPEZDItemValueSetter.setValue(tMsg.splyCtoffDt, tecPlanResultSet.getString(SPLY_CTOFF_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.splyCtoffDaysAot, tecPlanResultSet.getBigDecimal(SPLY_CTOFF_DAYS_AOT));
                ZYPEZDItemValueSetter.setValue(tMsg.cratPrchReqFlg, tecPlanResultSet.getString(CRAT_PRCH_REQ_FLG));
                ZYPEZDItemValueSetter.setValue(tMsg.printItemStsFlg, tecPlanResultSet.getString(PRINT_ITEM_DESC_FLG));
                ZYPEZDItemValueSetter.setValue(tMsg.printItemDescFlg, tecPlanResultSet.getString(PRINT_ITEM_DESC_FLG));
                ZYPEZDItemValueSetter.setValue(tMsg.locTpCd, tecPlanResultSet.getString(LOC_TP_CD));

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    /* 07/12/2017 CSAI Y.Imazu Mod QC#19865 START */
                    setErrMsg(MRP_RUN_PRM, tMsg.mrpRunPrmPk.getValue().toString(), NASM0006E);
                    // S21InfoLogOutput.println(NLCM0047E, new String[] {tMsg.mrpRunPrmPk.getValue().toString() });
                    // errorCount++;
                    // rollback();
                    continue;
                    /* 07/12/2017 CSAI Y.Imazu Mod QC#19865 END */
                }
                updMrpRunSch((String) groupDat.get(MRP_RUN_SCHD_ID), MRP_RUN_STS.PROCESSING);
                commit();
                normalCount++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 START */
    /**
     * Set Error Message
     * @param tableName String
     * @param tablePk String
     * @param errMsgId String
     */
    private void setErrMsg(String tableName, String tablePk, String errMsgId) {

        String[] errParam = new String[] {tableName, tablePk };

        S21InfoLogOutput.println(errMsgId, errParam);
        errMsgList.add(S21MessageFunc.clspGetMessage(errMsgId, errParam));

        errorCount++;
        rollback();
    }

    /**
     * Register Mail
     */
    private void registerMail() {

        /*************************************************************
         * 1. Get From
         ************************************************************/
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;

        if (!addrFromList.isEmpty()) {

            from = addrFromList.get(0);
        }

        /*************************************************************
         * 2. Get To
         ************************************************************/
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {

            throw new S21AbendException(NWBM0092E);
        }

        /*************************************************************
         * 3. Create message for Body
         ************************************************************/
        StringBuilder msgBuf = new StringBuilder();
        msgBuf.append(MAIL_MSG_HEADER);
        msgBuf.append(LINE_FEED_CODE);

        for (int i = 0; i < errMsgList.size(); i++) {

            msgBuf.append((String) errMsgList.get(i));
            msgBuf.append(LINE_FEED_CODE);
        }

        String message = msgBuf.toString();

        /*************************************************************
         * 4. Create Subject and Body
         ************************************************************/
        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, MAIL_TEMPLATE_ID);

        if (template == null) {

            throw new S21AbendException(NWBM0092E);
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_ID, BUSINESS_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        /*************************************************************
         * 5. Call Mail API
         ************************************************************/
        S21Mail mail = new S21Mail(globalCompanyCode);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
    /* 07/12/2017 CSAI Y.Imazu Add QC#19865 END */
}
