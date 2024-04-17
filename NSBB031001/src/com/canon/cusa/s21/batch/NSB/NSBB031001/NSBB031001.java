/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB031001;

import static com.canon.cusa.s21.batch.NSB.NSBB031001.constant.NSBB031001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_TASK_DLY_RPTTMsg;
import business.db.SVC_TASK_DLY_RPTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STRU_DFN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;

/**
 * <pre>
 * Create Task Daily Report Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         M.Gotou         Create          N/A
 * 2016/03/18   Hitachi         K.Yamada        Update          CSA QC#5627
 * 2016/03/24   Hitachi         K.Yamada        Update          CSA QC#5948
 * 2016/03/29   Hitachi         K.Yamada        Update          CSA QC#6028
 * 2016/04/05   Hitachi         K.Yamada        Update          CSA QC#6104
 * 2016/07/05   Hitachi         O.Okuma         Update          CSA QC#10949
 * 2017/04/04   Hitachi         K.Kitachi       Update          CSA QC#18204
 * 2018/07/04   Hitachi         K.Kim           Update          CSA QC#26894
 * 2018/07/31   Hitachi         K.Kitachi       Update          CSA QC#26894
 * 2019/04/02   Hitachi         T.Tomita        Update          CSA QC#31013
 * </pre>
 */

public class NSBB031001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Process Time Stamp */
    private String procTs = null;

    /** Sales Date */
    private String salesDate = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        // Global company code
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NASM0010E);
        }

        // Sales date
        this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(salesDate)) {
            throw new S21AbendException(NASM0011E);
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {

        if (init_flg()) {
            doProcess();
        }
        if (errMsgList.isEmpty()) {
            commit();
        } else {
            termCd = TERM_CD.ABNORMAL_END;
            sendErrMail(errMsgList);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB031001().executeBatch(NSBB031001.class.getSimpleName());
    }

    private boolean init_flg() {
        try {
            // Report Latest Flag to N
            if (!clearSvcTaskDlyRpt()) {
                this.errorCount++;
                return false;
            } else {
                this.normalCount++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return true;
    }

    /**
     * clear SVC_TASK_DLY_RPT Latest Flag
     * @return boolean
     * @throws SQLException
     */
    private boolean clearSvcTaskDlyRpt() throws SQLException {
        SVC_TASK_DLY_RPTTMsg svcTaskDlyRptTMsg = new SVC_TASK_DLY_RPTTMsg();
        SVC_TASK_DLY_RPTTMsgArray result =  getSVC_TASK_DLY_RPT();
        for (int i = 0; i < result.getValidCount(); i++) {
            BigDecimal svcTaskDlyRptPk = result.no(i).svcTaskDlyRptPk.getValue();
            setValue(svcTaskDlyRptTMsg.glblCmpyCd, result.no(i).glblCmpyCd);
            setValue(svcTaskDlyRptTMsg.svcTaskDlyRptPk, svcTaskDlyRptPk);
            svcTaskDlyRptTMsg = (SVC_TASK_DLY_RPTTMsg) EZDTBLAccessor.findByKeyForUpdate(svcTaskDlyRptTMsg);
            if (svcTaskDlyRptTMsg == null) {
                rollback();
                addErrMsg(NSBM0120E, new String[] {"SVC_TASK_DLY_RPT", createFaileMsg("SVC_TASK_DLY_RPT_PK", svcTaskDlyRptPk) });
                return false;
            }

            setValue(svcTaskDlyRptTMsg.svcTaskLtstFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(svcTaskDlyRptTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskDlyRptTMsg.getReturnCode())) {
                rollback();
                addErrMsg(NSBM0120E, new String[] {"SVC_TASK_DLY_RPT", createFaileMsg("SVC_TASK_DLY_RPT_PK", svcTaskDlyRptPk) });
                return false;
            }
        }
        return true;
    }

    /**
     * get SVC_TASK_DLY_RPT
     * @return SVC_TASK_DLY_RPTTMsgArray
     */
    private SVC_TASK_DLY_RPTTMsgArray getSVC_TASK_DLY_RPT() {
        SVC_TASK_DLY_RPTTMsg param = new SVC_TASK_DLY_RPTTMsg();
        // SVC_TASK_LTST_FLG is Y
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("cratDt01", this.salesDate);
        param.setConditionValue("svcTaskLtstFlg01", ZYPConstant.FLG_ON_Y);
        SVC_TASK_DLY_RPTTMsgArray result =  (SVC_TASK_DLY_RPTTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }


    private void doProcess() {

        // 2.1 get Organization Code
        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(SEARCH_ORG_CD);
        if (taskSmryInfoValTxtList.isEmpty()) {
            return;
        }
        List<CreateTaskDailyReportBean> targetOrgCdList = getTargetOrgCd(taskSmryInfoValTxtList);

        CreateTaskDailyReportBean taskDailyReport = new CreateTaskDailyReportBean();

        // 2.2 (1) get Task Daily Report Info
        for (int i = 0; i < targetOrgCdList.size(); i++) {
            // (1)-1 TTL_IN_PROC_TASK_CNT
            taskDailyReport.setTtlInProcTaskCnt(ttlInProcTaskCnt(targetOrgCdList.get(i)));

            // (1)-2 PRT_WAIT_TASK_CNT
            taskDailyReport.setPrtWaitTaskCnt(prtWaitTaskCnt(targetOrgCdList.get(i)));

            // (1)-3 SPCL_WAIT_TASK_CNT
            taskDailyReport.setSpclWaitTaskCnt(scplWaitTaskCnt(targetOrgCdList.get(i)));

            // (1)-4 CUST_TASK_CNT
            taskDailyReport.setCustTaskCnt(custTaskCnt(targetOrgCdList.get(i)));

            // (1)-5 CRAT_TASK_CNT
            taskDailyReport.setCratTaskCnt(cratTaskCnt(targetOrgCdList.get(i)));

            // (1)-6 AVAL_TECH_CNT
            taskDailyReport.setAvalTechCnt(avalTechCnt(targetOrgCdList.get(i)));

            // (1)-7 AFT_HOUR_TASK_CNT
            taskDailyReport.setAftHourTaskCnt(aftHourTaskCnt(targetOrgCdList.get(i)));

            // (1)-8 AFT_HOUR_AVAL_TECH_CNT
            taskDailyReport.setAftHourAvalTechCnt(aftHourAvalTechCnt(targetOrgCdList.get(i)));

            // (1)-9 CLO_TASK_CNT
            taskDailyReport.setCloTaskCnt(cloTaskCnt(targetOrgCdList.get(i)));

            // (1)-10 PRT_FAIL_CNT
            taskDailyReport.setPrtFailCnt(prtFailCnt(targetOrgCdList.get(i)));

            // (1)-11 POST_ENTRY_TASK_CNT
            taskDailyReport.setPostEntryTaskCnt(postEntryTaskCnt(targetOrgCdList.get(i)));

            // (1)-12 RSP_TM_CUST_TASK_RATE
            taskDailyReport.setRspTmCustTaskRate(rspTmCustTaskRate(targetOrgCdList.get(i)));

            // (1)-13 RSP_TM_ALL_TASK_RATE
            taskDailyReport.setRspTmAllTaskRate(rspTmAllTaskRate(targetOrgCdList.get(i)));

            // (1)-14 CUST_TASK_TTL_RSP_AOT
            taskDailyReport.setCustTaskTtlRspAot(custTaskTtlRspAot(targetOrgCdList.get(i)));

            // (1)-15 CLO_CUST_TASK_CNT
            taskDailyReport.setCloCustTaskCnt(cloCustTaskCnt(targetOrgCdList.get(i)));

            // (1)-16 ALL_TASK_TTL_RSP_AOT
            taskDailyReport.setAllTaskTtlRspAot(allTaskTtlRspAot(targetOrgCdList.get(i)));

            // (1)-17 CLO_ALL_TASK_CNT
            taskDailyReport.setCloAllTaskCnt(cloAllTaskCnt(targetOrgCdList.get(i)));

            // START 2018/07/04 K.Kim [QC#26894, ADD]
            // (1)-18 ESCL_TASK_CNT
            taskDailyReport.setEsclTaskCnt(esclTaskCnt(targetOrgCdList.get(i)));
            // END 2018/07/04 K.Kim [QC#26894, ADD]esclTaskCnt

        // 2.2 (2) create Task Daily Report
            taskDailyReport.setOrgLayerNum(targetOrgCdList.get(i).getOrgLayerNum());
            taskDailyReport.setFirstOrgCd(targetOrgCdList.get(i).getFirstOrgCd());
            taskDailyReport.setFirstOrgNum(targetOrgCdList.get(i).getFirstOrgNum());
            taskDailyReport.setScdOrgCd(targetOrgCdList.get(i).getScdOrgCd());
            taskDailyReport.setScdOrgNum(targetOrgCdList.get(i).getScdOrgNum());
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > SCD_LAYERNUM) {
                taskDailyReport.setThirdOrgCd(targetOrgCdList.get(i).getThirdOrgCd());
                taskDailyReport.setThirdOrgNum(targetOrgCdList.get(i).getThirdOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > THIRD_LAYERNUM) {
                taskDailyReport.setFrthOrgCd(targetOrgCdList.get(i).getFrthOrgCd());
                taskDailyReport.setFrthOrgNum(targetOrgCdList.get(i).getFrthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > FRTH_LAYERNUM) {
                taskDailyReport.setFifthOrgCd(targetOrgCdList.get(i).getFifthOrgCd());
                taskDailyReport.setFifthOrgNum(targetOrgCdList.get(i).getFifthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > FIFTH_LAYERNUM) {
                taskDailyReport.setSixthOrgCd(targetOrgCdList.get(i).getSixthOrgCd());
                taskDailyReport.setSixthOrgNum(targetOrgCdList.get(i).getSixthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > SIXTH_LAYERNUM) {
                taskDailyReport.setSvnthOrgCd(targetOrgCdList.get(i).getSvnthOrgCd());
                taskDailyReport.setSvnthOrgNum(targetOrgCdList.get(i).getSvnthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > SVNTH_LAYERNUM) {
                taskDailyReport.setEighthOrgCd(targetOrgCdList.get(i).getEighthOrgCd());
                taskDailyReport.setEighthOrgNum(targetOrgCdList.get(i).getEighthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > EIGHTH_LAYERNUM) {
                taskDailyReport.setNinthOrgCd(targetOrgCdList.get(i).getNinthOrgCd());
                taskDailyReport.setNinthOrgNum(targetOrgCdList.get(i).getNinthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > NINTH_LAYERNUM) {
                taskDailyReport.setTenthOrgCd(targetOrgCdList.get(i).getTenthOrgCd());
                taskDailyReport.setTenthOrgNum(targetOrgCdList.get(i).getTenthOrgNum());
            }
            if (targetOrgCdList.get(i).getOrgLayerNum().intValue() > TENTH_LAYERNUM) {
                taskDailyReport.setElvthOrgCd(targetOrgCdList.get(i).getElvthOrgCd());
                taskDailyReport.setElvthOrgNum(targetOrgCdList.get(i).getElvthOrgNum());
            }
            if (!createTaskDailyReport(taskDailyReport)) {
                this.errorCount++;
                return;
            } else {
                this.normalCount++;
            }
        }
    }

    private List<String> getTaskSmryInfoValTxt(String searchCd) {

        List<String> txtValList = new ArrayList<String>();
        List<Map<String, Object>> txtList;
        try {
            txtList = getTaskSmryInfo(searchCd);
            if (txtList.size() == 0) {
                // del start 2016/03/18 CSA Defect#5627
                //addErrMsg(NSZM0401E, new String[] {"TASK_SMRY_INFO", createFaileMsg("TASK_SMRY_INFO_TP_CD", new BigDecimal(searchCd)) });
                // del end 2016/03/18 CSA Defect#5627
                return txtValList;
            }
            for (int i = 0; i < txtList.size(); i++) {
                Map<String, Object> txtVal = txtList.get(i);
                String val = setTaskSmryInfo(txtVal);
                txtValList.add(val);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return txtValList;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getTaskSmryInfo(String searchCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("taskSmryInfoTpCd", searchCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTaskSmryInfo", paramMap);
    }

    private String setTaskSmryInfo(Map<String, Object> txtVal) {
        return (String) txtVal.get("TASK_SMRY_INFO_VAL_TXT");
    }

    private List<CreateTaskDailyReportBean> getTargetOrgCd(List<String> taskSmryInfoValTxtList) {

        List<CreateTaskDailyReportBean> beanList = new ArrayList<CreateTaskDailyReportBean>();
        List<Map<String, Object>> orgCdList;

        try {
            orgCdList = getOrgCd(taskSmryInfoValTxtList);
            for (int i = 0; i < orgCdList.size(); i++) {
                Map<String, Object> orgCdInfo = orgCdList.get(i);
                CreateTaskDailyReportBean bean = setOrgCdBean(orgCdInfo);
                beanList.add(bean);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return beanList;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getOrgCd(List<String> taskSmryInfoValTxtList) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            // add start 2016/03/24 CSA Defect#5948
            replaceTxt = replaceTxt.replace("serviceOrg", ORG_CD_SERVICE);
            replaceTxt = replaceTxt.replace("bos", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            replaceTxt = replaceTxt.replace("csa", STRU_DFN_CSA);
            replaceTxt = replaceTxt.replace("lob", STRU_DFN_LOB);
            replaceTxt = replaceTxt.replace("zone", STRU_DFN_ZONE);
            replaceTxt = replaceTxt.replace("rg", STRU_DFN_RG);
            replaceTxt = replaceTxt.replace("brrollup", STRU_DFN_BRROLLUP);
            replaceTxt = replaceTxt.replace("br", STRU_DFN_BR);
            replaceTxt = replaceTxt.replace("team", STRU_DFN_TEAM);
            // add end 2016/03/24 CSA Defect#5948
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("searchCondition02", replaceTxtList.get(j++));
        paramMap.put("searchCondition03", replaceTxtList.get(j++));
        paramMap.put("searchCondition04", replaceTxtList.get(j++));
        paramMap.put("searchCondition05", replaceTxtList.get(j++));
        paramMap.put("searchCondition06", replaceTxtList.get(j++));
        paramMap.put("searchCondition07", replaceTxtList.get(j++));
        paramMap.put("searchCondition08", replaceTxtList.get(j++));
        paramMap.put("searchCondition09", replaceTxtList.get(j++));
        paramMap.put("searchCondition10", replaceTxtList.get(j++));
        paramMap.put("searchCondition11", replaceTxtList.get(j++));

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrgCode", paramMap);
    }

    private CreateTaskDailyReportBean setOrgCdBean(Map<String, Object> orgCdInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setOrgCd((String) orgCdInfo.get("ORG_CD"));
        bean.setOrgLayerNum((BigDecimal) orgCdInfo.get("ORG_LAYER_NUM"));
        bean.setFirstOrgCd((String) orgCdInfo.get("FIRST_ORG_CD"));
        bean.setFirstOrgNum((String) orgCdInfo.get("FIRST_ORG_NM"));
        bean.setScdOrgCd((String) orgCdInfo.get("SCD_ORG_CD"));
        bean.setScdOrgNum((String) orgCdInfo.get("SCD_ORG_NM"));
        bean.setThirdOrgCd((String) orgCdInfo.get("THIRD_ORG_CD"));
        bean.setThirdOrgNum((String) orgCdInfo.get("THIRD_ORG_NM"));
        bean.setFrthOrgCd((String) orgCdInfo.get("FRTH_ORG_CD"));
        bean.setFrthOrgNum((String) orgCdInfo.get("FRTH_ORG_NM"));
        bean.setFifthOrgCd((String) orgCdInfo.get("FIFTH_ORG_CD"));
        bean.setFifthOrgNum((String) orgCdInfo.get("FIFTH_ORG_NM"));
        bean.setSixthOrgCd((String) orgCdInfo.get("SIXTH_ORG_CD"));
        bean.setSixthOrgNum((String) orgCdInfo.get("SIXTH_ORG_NM"));
        bean.setSvnthOrgCd((String) orgCdInfo.get("SVNTH_ORG_CD"));
        bean.setSvnthOrgNum((String) orgCdInfo.get("SVNTH_ORG_NM"));
        bean.setEighthOrgCd((String) orgCdInfo.get("EIGHTH_ORG_CD"));
        bean.setEighthOrgNum((String) orgCdInfo.get("EIGHTH_ORG_NM"));
        bean.setNinthOrgCd((String) orgCdInfo.get("NINTH_ORG_CD"));
        bean.setNinthOrgNum((String) orgCdInfo.get("NINTH_ORG_NM"));
        bean.setTenthOrgCd((String) orgCdInfo.get("TENTH_ORG_CD"));
        bean.setTenthOrgNum((String) orgCdInfo.get("TENTH_ORG_NM"));
        bean.setElvthOrgCd((String) orgCdInfo.get("ELVTH_ORG_CD"));
        bean.setElvthOrgNum((String) orgCdInfo.get("ELVTH_ORG_NM"));
        // Add Start 2019/04/02 QC#31013
        bean.setStruDfnCd((String) orgCdInfo.get("STRU_DFN_CD"));
        // Add End 2019/04/02 QC#31013
        return bean;
    }

    private BigDecimal ttlInProcTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_TTL_IN_PROC_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> ttlInProcTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            ttlInProcTaskCntList = cntTtlInProcTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setTtlInProcTaskCnt(ttlInProcTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getTtlInProcTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntTtlInProcTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntTtlInProcTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntTtlInProcTask", paramMap);
    }

    private CreateTaskDailyReportBean setTtlInProcTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setTtlInProcTaskCnt((BigDecimal) taskCntInfo.get("TTL_IN_PROC_TASK_CNT"));
        return bean;
    }

    private BigDecimal prtWaitTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_PRT_WAIT_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> prtWaitTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            prtWaitTaskCntList = cntPrtWaitTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setPrtWaitTaskCnt(prtWaitTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getPrtWaitTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntPrtWaitTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntPrtWaitTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPrtWaitTask", paramMap);
    }

    private CreateTaskDailyReportBean setPrtWaitTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setPrtWaitTaskCnt((BigDecimal) taskCntInfo.get("PRT_WAIT_TASK_CNT"));
        return bean;
    }

    private BigDecimal scplWaitTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_SPCL_WAIT_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> scplWaitTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            scplWaitTaskCntList = cntScplWaitTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setScplWaitTaskCnt(scplWaitTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getSpclWaitTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntScplWaitTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntSpclWaitTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntSpclWaitTask", paramMap);
    }

    private CreateTaskDailyReportBean setScplWaitTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setSpclWaitTaskCnt((BigDecimal) taskCntInfo.get("SPCL_WAIT_TASK_CNT"));
        return bean;
    }

    private BigDecimal custTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CUST_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> custTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            custTaskCntList = cntCustTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setCustTaskCnt(custTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCustTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntCustTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntCustTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCustTask", paramMap);
    }

    private CreateTaskDailyReportBean setCustTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setCustTaskCnt((BigDecimal) taskCntInfo.get("CUST_TASK_CNT"));
        return bean;
    }

    private BigDecimal cratTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CRAT_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> cratTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            cratTaskCntList = cntCratTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setCratTaskCnt(cratTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCratTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntCratTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntCratTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCratTask", paramMap);
    }

    private CreateTaskDailyReportBean setCratTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setCratTaskCnt((BigDecimal) taskCntInfo.get("CRAT_TASK_CNT"));
        return bean;
    }

    private BigDecimal avalTechCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_AVAL_TECH);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> avalTechCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            avalTechCntList = cntavalTech(taskSmryInfoValTxtList, targetOrgCd);
            bean = setAvalTechCnt(avalTechCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAvalTechCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntavalTech(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAvalTech", paramMap);
    }

    private CreateTaskDailyReportBean setAvalTechCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setAvalTechCnt((BigDecimal) taskCntInfo.get("AVAL_TECH_CNT"));
        return bean;
    }

    private BigDecimal aftHourTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_AFT_HOUR_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> aftHourTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            aftHourTaskCntList = cntaftHourTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setAftHourTaskCnt(aftHourTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAftHourTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntaftHourTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntAftHourTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAftHourTask", paramMap);
    }

    private CreateTaskDailyReportBean setAftHourTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setAftHourTaskCnt((BigDecimal) taskCntInfo.get("AFT_HOUR_TASK_CNT"));
        return bean;
    }

    private BigDecimal aftHourAvalTechCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_AFT_HOUR_AVAL_TECH);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> aftHourAvalTechCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            aftHourAvalTechCntList = cntaftHourAvalTech(taskSmryInfoValTxtList, targetOrgCd);
            bean = setAftHourAvalTechCnt(aftHourAvalTechCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAftHourAvalTechCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntaftHourAvalTech(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAftHourAvalTech", paramMap);
    }

    private CreateTaskDailyReportBean setAftHourAvalTechCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setAftHourAvalTechCnt((BigDecimal) taskCntInfo.get("AFT_HOUR_AVAL_TECH_CNT"));
        return bean;
    }

    private BigDecimal cloTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CLO_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> cloTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            cloTaskCntList = cntCloTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setCloTaskCnt(cloTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCloTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntCloTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntCloTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloTask", paramMap);
    }

    private CreateTaskDailyReportBean setCloTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setCloTaskCnt((BigDecimal) taskCntInfo.get("CLO_TASK_CNT"));
        return bean;
    }

    private BigDecimal prtFailCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_PRT_FAIL);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> prtFailCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            prtFailCntList = cntPrtFail(taskSmryInfoValTxtList, targetOrgCd);
            bean = setPrtFailCnt(prtFailCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getPrtFailCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntPrtFail(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntPrtFailByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPrtFail", paramMap);
    }

    private CreateTaskDailyReportBean setPrtFailCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setPrtFailCnt((BigDecimal) taskCntInfo.get("PRT_FAIL_CNT"));
        return bean;
    }

    private BigDecimal postEntryTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_POST_ENTRY_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> postEntryTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            postEntryTaskCntList = cntPostEntryTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setPostEntryTaskCnt(postEntryTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getPostEntryTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntPostEntryTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntPostEntryTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPostEntryTask", paramMap);
    }

    private CreateTaskDailyReportBean setPostEntryTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setPostEntryTaskCnt((BigDecimal) taskCntInfo.get("POST_ENTRY_TASK_CNT"));
        return bean;
    }

    private BigDecimal rspTmCustTaskRate(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(RATE_RSP_TM_CUST_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> rspTmCustTaskRateList;
        CreateTaskDailyReportBean bean = null;
        try {
            rspTmCustTaskRateList = rateRspTmCustTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setRspTmCustTaskRate(rspTmCustTaskRateList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getRspTmCustTaskRate();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> rateRspTmCustTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return rateRspTmCustTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("rateRspTmCustTask", paramMap);
    }

    private CreateTaskDailyReportBean setRspTmCustTaskRate(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        // mod start 2016/03/29 CSA Defect#6028
        BigDecimal rate = BigDecimal.ZERO;
        // mod end 2016/03/29 CSA Defect#6028
        BigDecimal cnt = (BigDecimal) taskCntInfo.get("COUNT");
        BigDecimal num = (BigDecimal) taskCntInfo.get("SUM_SVC_RSP_TM_NUM");

        if (cnt.intValue() != 0) {
            rate = num.divide(cnt, 2, BigDecimal.ROUND_UP);
        }
        bean.setRspTmCustTaskRate(rate);
        return bean;
    }

    private BigDecimal rspTmAllTaskRate(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(RATE_RSP_TM_ALL_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> rspTmAllTaskRateList;
        CreateTaskDailyReportBean bean = null;
        try {
            rspTmAllTaskRateList = rateRspTmAllTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setRspTmAllTaskRate(rspTmAllTaskRateList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getRspTmAllTaskRate();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> rateRspTmAllTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return rateRspTmAllTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("rateRspTmAllTask", paramMap);
    }

    private CreateTaskDailyReportBean setRspTmAllTaskRate(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        // mod start 2016/03/29 CSA Defect#6028
        BigDecimal rate = BigDecimal.ZERO;
        // mod end 2016/03/29 CSA Defect#6028
        BigDecimal cnt = (BigDecimal) taskCntInfo.get("COUNT");
        BigDecimal num = (BigDecimal) taskCntInfo.get("SUM_SVC_RSP_TM_NUM");

        if (cnt.intValue() != 0) {
            rate = num.divide(cnt, 2, BigDecimal.ROUND_UP);
        }
        bean.setRspTmAllTaskRate(rate);
        return bean;
    }

    private BigDecimal custTaskTtlRspAot(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(AOT_CUST_TASK_TTL_RSP);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> custTaskTtlRspAotList;
        CreateTaskDailyReportBean bean = null;
        try {
            custTaskTtlRspAotList = aotCustTaskTtlRsp(taskSmryInfoValTxtList, targetOrgCd);
            bean = setCustTaskTtlRspAot(custTaskTtlRspAotList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCustTaskTtlRspAot();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> aotCustTaskTtlRsp(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return aotCustTaskTtlRspByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("aotCustTaskTtlRsp", paramMap);
    }

    private CreateTaskDailyReportBean setCustTaskTtlRspAot(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setCustTaskTtlRspAot((BigDecimal) taskCntInfo.get("CUST_TASK_TTL_RSP_AOT"));
        return bean;
    }

    private BigDecimal cloCustTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CLO_CUST_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> cloCustTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            cloCustTaskCntList = cntCloCustTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setCloCustTaskCnt(cloCustTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCloCustTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntCloCustTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntCloCustTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloCustTask", paramMap);
    }

    private CreateTaskDailyReportBean setCloCustTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setCloCustTaskCnt((BigDecimal) taskCntInfo.get("CLO_CUST_TASK_CNT"));
        return bean;
    }

    private BigDecimal allTaskTtlRspAot(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(AOT_ALL_TASK_TTL_RSP);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> allTaskTtlRspAotList;
        CreateTaskDailyReportBean bean = null;
        try {
            allTaskTtlRspAotList = aotAllTaskTtlRsp(taskSmryInfoValTxtList, targetOrgCd);
            bean = setAllTaskTtlRspAot(allTaskTtlRspAotList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAllTaskTtlRspAot();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> aotAllTaskTtlRsp(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return aotAllTaskTtlRspByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("aotAllTaskTtlRsp", paramMap);
    }

    private CreateTaskDailyReportBean setAllTaskTtlRspAot(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setAllTaskTtlRspAot((BigDecimal) taskCntInfo.get("ALL_TASK_TTL_RSP_AOT"));
        return bean;
    }

    private BigDecimal cloAllTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CLO_ALL_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> cloAllTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            cloAllTaskCntList = cntCloAllTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setCloAllTaskCnt(cloAllTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCloAllTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntCloAllTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntCloAllTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        // del start 2016/07/05 CSA Defect#10949
        // paramMap.put("techNoAsign01", replaceTxtList.get(j++));
        // paramMap.put("techNoAsign02", replaceTxtList.get(j++));
        // del end 2016/07/05 CSA Defect#10949

        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        // del start 2016/07/05 CSA Defect#10949
        // String techNoAsign03 = CMP_ORG_STRU_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        // paramMap.put("techNoAsign03", techNoAsign03);
        // del end 2016/07/05 CSA Defect#10949

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloAllTask", paramMap);
    }

    private CreateTaskDailyReportBean setCloAllTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setCloAllTaskCnt((BigDecimal) taskCntInfo.get("CLO_ALL_TASK_CNT"));
        return bean;
    }

    // START 2018/07/04 K.Kim [QC#26894, ADD]
    private BigDecimal esclTaskCnt(CreateTaskDailyReportBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_ESCL_TASK);
        BigDecimal ret = null;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> esclTaskCntList;
        CreateTaskDailyReportBean bean = null;
        try {
            esclTaskCntList = cntEsclTask(taskSmryInfoValTxtList, targetOrgCd);
            bean = setEsclTaskCnt(esclTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getEsclTaskCnt();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> cntEsclTask(List<String> taskSmryInfoValTxtList, CreateTaskDailyReportBean targetOrgCd) throws SQLException {
        // Add Start 2019/04/03 QC#31013
        if (STRU_DFN.TEAM.equals(targetOrgCd.getStruDfnCd())) {
            return cntEsclTaskByTeam(targetOrgCd.getSvnthOrgNum());
        }
        // Add End 2019/04/03 QC#31013
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < taskSmryInfoValTxtList.size(); i++) {
            String replaceTxt = taskSmryInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            // START 2018/07/31 K.Kitachi [QC#26894, ADD]
            replaceTxt = replaceTxt.replace("procTs", this.procTs);
            // END 2018/07/31 K.Kitachi [QC#26894, ADD]
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            replaceTxt = replaceTxt.replace("tbo", SVC_TASK_STS.TBO);
            replaceTxt = replaceTxt.replace("scheduled", SVC_TASK_STS.SCHEDULED);
            replaceTxt = replaceTxt.replace("assigned", SVC_TASK_STS.ASSIGNED);
            replaceTxtList.add(replaceTxt);
        }
        int j = 0;
        paramMap.put("techAsign01", replaceTxtList.get(j++));
        String techAsign02 = CMP_S21_ORG_ORG_CD[targetOrgCd.getOrgLayerNum().intValue()].replace("org_Cd", targetOrgCd.getOrgCd());
        paramMap.put("techAsign02", techAsign02);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntEsclTask", paramMap);
    }

    private CreateTaskDailyReportBean setEsclTaskCnt(Map<String, Object> taskCntInfo) {

        CreateTaskDailyReportBean bean = new CreateTaskDailyReportBean();
        bean.setEsclTaskCnt((BigDecimal) taskCntInfo.get("ESCL_TASK_CNT"));
        return bean;
    }
    // END 2018/07/04 K.Kim [QC#26894, ADD]

    private boolean createTaskDailyReport(CreateTaskDailyReportBean taskDailyReport) {

        BigDecimal svcTaskDlyRptPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_DLY_RPT_SQ);

        // START 2017/04/04 K.Kitachi [QC#18204, MOD]
        SVC_TASK_DLY_RPTTMsg svcTaskDlyRptTMsg = new SVC_TASK_DLY_RPTTMsg();
        setValue(svcTaskDlyRptTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTaskDlyRptTMsg.svcTaskDlyRptPk, svcTaskDlyRptPk);
        setValue(svcTaskDlyRptTMsg.cratDt, this.salesDate);
        setValue(svcTaskDlyRptTMsg.snapShotDtTmTs, this.procTs);
        setValue(svcTaskDlyRptTMsg.orgLayerNum, taskDailyReport.getOrgLayerNum());
        setValue(svcTaskDlyRptTMsg.firstOrgCd, taskDailyReport.getFirstOrgCd());
        setValue(svcTaskDlyRptTMsg.firstOrgNm, taskDailyReport.getFirstOrgNum());
        setValue(svcTaskDlyRptTMsg.scdOrgCd, taskDailyReport.getScdOrgCd());
        setValue(svcTaskDlyRptTMsg.scdOrgNm, taskDailyReport.getScdOrgNum());
        setValue(svcTaskDlyRptTMsg.thirdOrgCd, taskDailyReport.getThirdOrgCd());
        setValue(svcTaskDlyRptTMsg.thirdOrgNm, taskDailyReport.getThirdOrgNum());
        setValue(svcTaskDlyRptTMsg.frthOrgCd, taskDailyReport.getFrthOrgCd());
        setValue(svcTaskDlyRptTMsg.frthOrgNm, taskDailyReport.getFrthOrgNum());
        setValue(svcTaskDlyRptTMsg.fifthOrgCd, taskDailyReport.getFifthOrgCd());
        setValue(svcTaskDlyRptTMsg.fifthOrgNm, taskDailyReport.getFifthOrgNum());
        setValue(svcTaskDlyRptTMsg.sixthOrgCd, taskDailyReport.getSixthOrgCd());
        setValue(svcTaskDlyRptTMsg.sixthOrgNm, taskDailyReport.getSixthOrgNum());
        setValue(svcTaskDlyRptTMsg.svnthOrgCd, taskDailyReport.getSvnthOrgCd());
        setValue(svcTaskDlyRptTMsg.svnthOrgNm, taskDailyReport.getSvnthOrgNum());
        setValue(svcTaskDlyRptTMsg.eighthOrgCd, taskDailyReport.getEighthOrgCd());
        setValue(svcTaskDlyRptTMsg.eighthOrgNm, taskDailyReport.getEighthOrgNum());
        setValue(svcTaskDlyRptTMsg.ninthOrgCd, taskDailyReport.getNinthOrgCd());
        setValue(svcTaskDlyRptTMsg.ninthOrgNm, taskDailyReport.getNinthOrgNum());
        setValue(svcTaskDlyRptTMsg.tenthOrgCd, taskDailyReport.getTenthOrgCd());
        setValue(svcTaskDlyRptTMsg.tenthOrgNm, taskDailyReport.getTenthOrgNum());
        setValue(svcTaskDlyRptTMsg.elvthOrgCd, taskDailyReport.getElvthOrgCd());
        setValue(svcTaskDlyRptTMsg.elvthOrgNm, taskDailyReport.getElvthOrgNum());
        setValue(svcTaskDlyRptTMsg.totInProcTaskCnt, taskDailyReport.getTtlInProcTaskCnt());
        setValue(svcTaskDlyRptTMsg.prtWaitTaskCnt, taskDailyReport.getPrtWaitTaskCnt());
        setValue(svcTaskDlyRptTMsg.spclWaitTaskCnt, taskDailyReport.getSpclWaitTaskCnt());
        if ((taskDailyReport.getTtlInProcTaskCnt() != null)
                && (taskDailyReport.getPrtWaitTaskCnt() != null) && (taskDailyReport.getSpclWaitTaskCnt() != null)) {
            setValue(svcTaskDlyRptTMsg.othOpenTaskCnt,
                    taskDailyReport.getTtlInProcTaskCnt().subtract(taskDailyReport.getPrtWaitTaskCnt()).subtract(taskDailyReport.getSpclWaitTaskCnt()));
        } else {
            setValue(svcTaskDlyRptTMsg.othOpenTaskCnt, BigDecimal.ZERO);
        }
        setValue(svcTaskDlyRptTMsg.custTaskCnt, taskDailyReport.getCustTaskCnt());
        // START 2018/07/04 K.Kim [QC#26894, MOD]
        // setValue(svcTaskDlyRptTMsg.esclTaskCnt, BigDecimal.ZERO);
        setValue(svcTaskDlyRptTMsg.esclTaskCnt, taskDailyReport.getEsclTaskCnt());
        // END 2018/07/04 K.Kim [QC#26894, MOD]
        setValue(svcTaskDlyRptTMsg.cratTaskCnt, taskDailyReport.getCratTaskCnt());
        if ((taskDailyReport.getCratTaskCnt() != null) && (taskDailyReport.getAvalTechCnt().intValue() != 0)) {
//            setValue(svcTaskDlyRptTMsg.cratTaskPerTechRate,
//                    taskDailyReport.getCratTaskCnt().divide(taskDailyReport.getAvalTechCnt(), 2, BigDecimal.ROUND_UP));
            setValue(svcTaskDlyRptTMsg.cratTaskPerTechRate, convMaxVal(taskDailyReport.getCratTaskCnt().divide(taskDailyReport.getAvalTechCnt(), 2, BigDecimal.ROUND_UP), MAX_RATE_VAL));
        } else {
            setValue(svcTaskDlyRptTMsg.cratTaskPerTechRate, BigDecimal.ZERO);
        }
        // mod start 2016/04/05 CSA Defect#6104
        setValue(svcTaskDlyRptTMsg.aftHourTaskPerTechRate, BigDecimal.ZERO);
        // mod end 2016/04/05 CSA Defect#6104
        if ((taskDailyReport.getCloTaskCnt() != null) && (taskDailyReport.getAvalTechCnt().intValue() != 0)) {
//            setValue(svcTaskDlyRptTMsg.cloTaskPerTechRate,
//                    taskDailyReport.getCloTaskCnt().divide(taskDailyReport.getAvalTechCnt(), 2, BigDecimal.ROUND_UP));
            setValue(svcTaskDlyRptTMsg.cloTaskPerTechRate, convMaxVal(taskDailyReport.getCloTaskCnt().divide(taskDailyReport.getAvalTechCnt(), 2, BigDecimal.ROUND_UP), MAX_RATE_VAL));
        } else {
            setValue(svcTaskDlyRptTMsg.cloTaskPerTechRate, BigDecimal.ZERO);
        }
        setValue(svcTaskDlyRptTMsg.aftHourTaskCnt, taskDailyReport.getAftHourTaskCnt());
        setValue(svcTaskDlyRptTMsg.cloTaskCnt, taskDailyReport.getCloTaskCnt());
        setValue(svcTaskDlyRptTMsg.prtFailCnt, taskDailyReport.getPrtFailCnt());
        setValue(svcTaskDlyRptTMsg.postEntryTaskCnt, taskDailyReport.getPostEntryTaskCnt());
        setValue(svcTaskDlyRptTMsg.avalTechCnt, taskDailyReport.getAvalTechCnt());
//        setValue(svcTaskDlyRptTMsg.rspTmCustTaskRate, taskDailyReport.getRspTmCustTaskRate());
        setValue(svcTaskDlyRptTMsg.rspTmCustTaskRate, convMaxVal(taskDailyReport.getRspTmCustTaskRate(), MAX_RATE_VAL));
//        setValue(svcTaskDlyRptTMsg.rspTmAllTaskRate, taskDailyReport.getRspTmAllTaskRate());
        setValue(svcTaskDlyRptTMsg.rspTmAllTaskRate, convMaxVal(taskDailyReport.getRspTmAllTaskRate(), MAX_RATE_VAL));
        setValue(svcTaskDlyRptTMsg.aftHourAvalTechCnt, taskDailyReport.getAftHourAvalTechCnt());
//        setValue(svcTaskDlyRptTMsg.custTaskTotRspAot, taskDailyReport.getCustTaskTtlRspAot());
        setValue(svcTaskDlyRptTMsg.custTaskTotRspAot, convMaxVal(taskDailyReport.getCustTaskTtlRspAot(), MAX_AOT_VAL));
        setValue(svcTaskDlyRptTMsg.cloCustTaskCnt, taskDailyReport.getCloCustTaskCnt());
//        setValue(svcTaskDlyRptTMsg.allTaskTotRspAot, taskDailyReport.getAllTaskTtlRspAot());
        setValue(svcTaskDlyRptTMsg.allTaskTotRspAot, convMaxVal(taskDailyReport.getAllTaskTtlRspAot(), MAX_AOT_VAL));
        setValue(svcTaskDlyRptTMsg.cloAllTaskCnt, taskDailyReport.getCloAllTaskCnt());
        setValue(svcTaskDlyRptTMsg.svcTaskLtstFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/04/04 K.Kitachi [QC#18204, MOD]

        S21FastTBLAccessor.insert(svcTaskDlyRptTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcTaskDlyRptTMsg.getReturnCode())) {
            rollback();
            addErrMsg(NSBM0121E, new String[] {"SVC_TASK_DLY_RPT", createFaileMsg("SVC_TASK_DLY_RPT_PK", svcTaskDlyRptPk) });
            return false;
        }
        return true;
    }

    private String createFaileMsg(String keyName, BigDecimal keyValue) {

        StringBuffer sb = new StringBuffer(keyName);
        sb.append(STR_COLON);
        sb.append(keyValue.toString());
        return sb.toString();
    }

    private void addErrMsg(String msgId, String... param) {
        String errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        this.errMsgList.add(errMsg);
    }

    private void sendErrMail(List<String> errList) {
        NSXC001001SendMailForErrorInfo errorInfo = new NSXC001001SendMailForErrorInfo();
        errorInfo.addErrMsgList(errList);
        errorInfo.sendMail(this.glblCmpyCd, BATCH_ID);
    }

    // START 2017/04/04 K.Kitachi [QC#18204, ADD]
    private BigDecimal convMaxVal(BigDecimal inVal, BigDecimal maxVal) {
        if (!hasValue(inVal)) {
            return BigDecimal.ZERO;
        }
        if (inVal.compareTo(maxVal) > 0) {
            return maxVal;
        }
        return inVal;
    }
    // END 2017/04/04 K.Kitachi [QC#18204, ADD]
    // Add Start 2019/04/02 QC#31013
    private List<Map<String, Object>> cntTtlInProcTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("pendChrg", SVC_TASK_STS.PENDING_CHARGE);
        paramMap.put("dbrfErr", SVC_TASK_STS.DEBRIEF_ERROR);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntTtlInProcTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntPrtWaitTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("pendPart", SVC_TASK_STS.PENDING_PARTS);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPrtWaitTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntSpclWaitTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("highPart", DS_SVC_CALL_TP.HIGH_PART);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        paramMap.put("assigned", SVC_TASK_STS.ASSIGNED);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntSpclWaitTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntCustTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCustTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntCratTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCratTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntAftHourTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAftHourTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntCloTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntPrtFailByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dbrfErr", SVC_TASK_STS.DEBRIEF_ERROR);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPrtFailByTeam", paramMap);
    }

    private List<Map<String, Object>> cntPostEntryTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPostEntryTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> rateRspTmCustTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("rateRspTmCustTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> rateRspTmAllTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("rateRspTmAllTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> aotCustTaskTtlRspByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("aotCustTaskTtlRspByTeam", paramMap);
    }

    private List<Map<String, Object>> cntCloCustTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloCustTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> aotAllTaskTtlRspByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("aotAllTaskTtlRspByTeam", paramMap);
    }

    private List<Map<String, Object>> cntCloAllTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloAllTaskByTeam", paramMap);
    }

    private List<Map<String, Object>> cntEsclTaskByTeam(String svcTeamTxt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("procTs", this.procTs);
        paramMap.put("svcTeamTxt", svcTeamTxt);
        paramMap.put("tbo", SVC_TASK_STS.TBO);
        paramMap.put("scheduled", SVC_TASK_STS.SCHEDULED);
        paramMap.put("assigned", SVC_TASK_STS.ASSIGNED);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntEsclTaskByTeam", paramMap);
    }
    // Add End 2019/04/02 QC#31013
}
