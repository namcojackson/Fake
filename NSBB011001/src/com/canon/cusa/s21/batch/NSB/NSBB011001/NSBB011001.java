/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB011001;

import static com.canon.cusa.s21.batch.NSB.NSBB011001.constant.NSBB011001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.OPEN_SVC_TASK_DTLTMsg;
import business.db.OPEN_SVC_TASK_DTLTMsgArray;
import business.db.OPEN_SVC_TASK_SMRYTMsg;
import business.db.OPEN_SVC_TASK_SMRYTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MGR_TP;

/**
 * <pre>
 * Previous Open Task Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 07/22/2013   Hitachi         T.Yonekura      Create          N/A
 * 03/02/2016   Hitachi         M.Gotou         Update          N/A
 * 03/18/2016   Hitachi         K.Yamada        Update          CSA QC#5627
 * 03/24/2016   Hitachi         K.Yamada        Update          CSA QC#5664
 * 2016/03/28   Hitachi         K.Yamada        Update          CSA QC#6012,6021
 * 2017/12/14   Hitachi         T.Tomita        Update          CSA QC#23100
 * 2018/07/04   Hitachi         K.Kim           Update          CSA QC#26894
 * 2018/07/09   Hitachi         T.Tomita        Update          CSA QC#27017
 * 2018/07/31   Hitachi         K.Kitachi       Update          CSA QC#27519
 * 2019/03/28   Hitachi         K.Kitamura      Update          CSA QC#30906
 * 2020/04/16   Hitachi         A.Kohinata      Update          CSA QC#56379
 * 2020/12/07   CITS            R.Shimamoto     Update          CSA QC#58041
 *</pre>
 */
public class NSBB011001 extends S21BatchMain {

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

        if (init_purge()) {
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
        new NSBB011001().executeBatch(NSBB011001.class.getSimpleName());
    }

    private boolean init_purge() {
        try {
            // Task PURGE
            if (!purgeOpenSvcTask()) {
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
     * purge OPEN_SVC_TASK_DTL / OPEN_SVC_TASK_SMRY Latest Flag
     * @return boolean
     * @throws SQLException     */
    private boolean purgeOpenSvcTask() throws SQLException {
        List<OPEN_SVC_TASK_DTLTMsg> deleteDtlTMsgList = new ArrayList<OPEN_SVC_TASK_DTLTMsg>();
        OPEN_SVC_TASK_DTLTMsgArray resultdtl =  getOPEN_SVC_TASK_DTL();
        for (int i = 0; i < resultdtl.getValidCount(); i++) {
            deleteDtlTMsgList.add(resultdtl.no(i));
        }
        if (deleteDtlTMsgList.size() > 0) {
            int delCntdtl = S21FastTBLAccessor.removePhysical(deleteDtlTMsgList.toArray(new OPEN_SVC_TASK_DTLTMsg[deleteDtlTMsgList.size()]));
            if (delCntdtl != deleteDtlTMsgList.size()) {
                EZDConnectionMgr.getInstance().rollback();
                addErrMsg(NSZM0637E, new String[] {"OPEN_SVC_TASK_DTL"});
                return false;
            }
        }

        List<OPEN_SVC_TASK_SMRYTMsg> deleteSmryTMsgList = new ArrayList<OPEN_SVC_TASK_SMRYTMsg>();
        OPEN_SVC_TASK_SMRYTMsgArray resultsmry =  getOPEN_SVC_TASK_SMRY();
        for (int i = 0; i < resultsmry.getValidCount(); i++) {
            deleteSmryTMsgList.add(resultsmry.no(i));
        }
        if (deleteSmryTMsgList.size() > 0) {
            int delCntSmry = S21FastTBLAccessor.removePhysical(deleteSmryTMsgList.toArray(new OPEN_SVC_TASK_SMRYTMsg[deleteSmryTMsgList.size()]));
            if (delCntSmry != deleteSmryTMsgList.size()) {
                EZDConnectionMgr.getInstance().rollback();
                addErrMsg(NSZM0637E, new String[] {"OPEN_SVC_TASK_SMRY"});
                return false;
            }
        }

        return true;
    }

    /**
     * get OPEN_SVC_TASK_DTL
     * @return OPEN_SVC_TASK_DTLTMsgArray
     */
    private OPEN_SVC_TASK_DTLTMsgArray getOPEN_SVC_TASK_DTL() {
        OPEN_SVC_TASK_DTLTMsg param = new OPEN_SVC_TASK_DTLTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        OPEN_SVC_TASK_DTLTMsgArray result =  (OPEN_SVC_TASK_DTLTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }

    /**
     * get OPEN_SVC_TASK_SMRY
     * @return OPEN_SVC_TASK_SMRYTMsgArray
     */
    private OPEN_SVC_TASK_SMRYTMsgArray getOPEN_SVC_TASK_SMRY() {
        OPEN_SVC_TASK_SMRYTMsg param = new OPEN_SVC_TASK_SMRYTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        OPEN_SVC_TASK_SMRYTMsgArray result =  (OPEN_SVC_TASK_SMRYTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }

    private void doProcess() {

        // 2.1 (1) get Open Service Task
        List<String> openSvcTaskInfoValTxtList = getTaskSmryInfoValTxt(SEARCH_OPEN_SERVICE_TASK);
        if (openSvcTaskInfoValTxtList.isEmpty()) {
            return;
        }
        List<OpenSvcTaskDtlBean> openSvcTaskList = getTargetOpenSvcTask(openSvcTaskInfoValTxtList);

        // 2.1 (2) create Open Service Task Detail
        if (openSvcTaskList.size() == 0) {
            // del start 2016/03/18 CSA Defect#5627
            //addErrMsg(NSZM0401E, new String[] {"OPEN_SVC_TASK_DTL", "ALL"});
            // del end 2016/03/18 CSA Defect#5627
            return;
        }
        for (int i = 0; i < openSvcTaskList.size(); i++) {
            if (!createOpenSvcTaskDtl(openSvcTaskList.get(i))) {
                this.errorCount++;
                return;
            } else {
                this.normalCount++;
            }
        }

        // 3.1 get Organization Code
        List<String> targetOrgCdInfoValTxtList = getTaskSmryInfoValTxt(SEARCH_ORG_CD);
        if (targetOrgCdInfoValTxtList.isEmpty()) {
            return;
        }
        List<TargetOrgCdBean> targetOrgCdList = getTargetOrgCd(targetOrgCdInfoValTxtList);

        OpenSvcTaskSmryBean openSvcTaskSmry = new OpenSvcTaskSmryBean();
        List<String> mangerList = new ArrayList<String>();
        mangerList.add(SVC_MGR_TP.MACHINE_MANAGER);
        mangerList.add(SVC_MGR_TP.RESOURCE_MANAGER);

        // 3.2 (1) get Open Service Task Detail
        for (int mngCnt = 0; mngCnt < mangerList.size(); mngCnt++) {
            openSvcTaskSmry.setSvcMgrTpCd(mangerList.get(mngCnt));

            for (int i = 0; i < targetOrgCdList.size(); i++) {
                // add start 2016/03/24 CSA Defect#5664
                BigDecimal orgLayerNum = findOpenTaskDtlByPsnCd(targetOrgCdList.get(i).getPsnCd());
                if (!hasValue(orgLayerNum)) {
                        continue;
                }
                openSvcTaskSmry.setSvcMgrPsnCd(targetOrgCdList.get(i).getPsnCd());
                openSvcTaskSmry.setMgrOrgLayerNum(orgLayerNum);
                // add end 2016/03/24 CSA Defect#5664

                targetOrgCdList.get(i).setSvcMgrTpCd(mangerList.get(mngCnt));

                // (1)-2 PREV_OPEN_TASK_CNT
                openSvcTaskSmry.setPrevOpenTaskCnt(prevOpenTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 INPROC_TASK_CNT
                openSvcTaskSmry.setInprocTaskCnt(inprocTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 CLO_TASK_CNT
                openSvcTaskSmry.setCloTaskCnt(cloTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 CANC_TASK_CNT
                openSvcTaskSmry.setCancTaskCnt(cancTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 REJ_TASK_CNT
                openSvcTaskSmry.setRejTaskCnt(rejTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 AVG_TM_RSP_CNT
                openSvcTaskSmry.setAvgTmRspCnt(avgTmRspCnt(targetOrgCdList.get(i)));

                // (1)-2 SVC_RSP_TM_NUM
                openSvcTaskSmry.setSvcRspTmNum(svcRspTmNum(targetOrgCdList.get(i)));

                // START 2019/03/28 K.Kitamura [QC#30233, ADD]
                // (1)-2 TBO_TASK_CNT
                openSvcTaskSmry.setTboTaskCnt(tboTaskCnt(targetOrgCdList.get(i)));
                // (1)-2 SCHD_TASK_CNT
                openSvcTaskSmry.setSchdTaskCnt(schdTaskCnt(targetOrgCdList.get(i)));
                // END 2019/03/28 K.Kitamura [QC#30233, ADD]

                // (1)-2 ASG_TASK_CNT
                openSvcTaskSmry.setAsgTaskCnt(asgTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 OPEN_TASK_CNT
                openSvcTaskSmry.setOpenTaskCnt(openTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 HLD_TASK_CNT
                openSvcTaskSmry.setHldTaskCnt(hldTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 ESCL_TASK_CNT
                openSvcTaskSmry.setEsclTaskCnt(esclTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 VIP_TASK_CNT
                openSvcTaskSmry.setVipTaskCnt(vipTaskCnt(targetOrgCdList.get(i)));

                // (1)-2 ATTN_TASK_CNT
                openSvcTaskSmry.setAttnTaskCnt(attnTaskCnt(targetOrgCdList.get(i)));

                // 3.2 (1)-3 create Open Service Task Summary
                openSvcTaskSmry.setFirstOrgCd(targetOrgCdList.get(i).getFirstOrgCd());
                openSvcTaskSmry.setFirstOrgNum(targetOrgCdList.get(i).getFirstOrgNum());
                openSvcTaskSmry.setScdOrgCd(targetOrgCdList.get(i).getScdOrgCd());
                openSvcTaskSmry.setScdOrgNum(targetOrgCdList.get(i).getScdOrgNum());
                openSvcTaskSmry.setThirdOrgCd(targetOrgCdList.get(i).getThirdOrgCd());
                openSvcTaskSmry.setThirdOrgNum(targetOrgCdList.get(i).getThirdOrgNum());
                openSvcTaskSmry.setFrthOrgCd(targetOrgCdList.get(i).getFrthOrgCd());
                openSvcTaskSmry.setFrthOrgNum(targetOrgCdList.get(i).getFrthOrgNum());
                openSvcTaskSmry.setFifthOrgCd(targetOrgCdList.get(i).getFifthOrgCd());
                openSvcTaskSmry.setFifthOrgNum(targetOrgCdList.get(i).getFifthOrgNum());
                openSvcTaskSmry.setSixthOrgCd(targetOrgCdList.get(i).getSixthOrgCd());
                openSvcTaskSmry.setSixthOrgNum(targetOrgCdList.get(i).getSixthOrgNum());
                openSvcTaskSmry.setSvnthOrgCd(targetOrgCdList.get(i).getSvnthOrgCd());
                openSvcTaskSmry.setSvnthOrgNum(targetOrgCdList.get(i).getSvnthOrgNum());
                openSvcTaskSmry.setEighthOrgCd(targetOrgCdList.get(i).getEighthOrgCd());
                openSvcTaskSmry.setEighthOrgNum(targetOrgCdList.get(i).getEighthOrgNum());
                openSvcTaskSmry.setNinthOrgCd(targetOrgCdList.get(i).getNinthOrgCd());
                openSvcTaskSmry.setNinthOrgNum(targetOrgCdList.get(i).getNinthOrgNum());
                openSvcTaskSmry.setTenthOrgCd(targetOrgCdList.get(i).getTenthOrgCd());
                openSvcTaskSmry.setTenthOrgNum(targetOrgCdList.get(i).getTenthOrgNum());
                openSvcTaskSmry.setElvthOrgCd(targetOrgCdList.get(i).getElvthOrgCd());
                openSvcTaskSmry.setElvthOrgNum(targetOrgCdList.get(i).getElvthOrgNum());
                if (!createOpenSvcTaskSmry(openSvcTaskSmry)) {
                    this.errorCount++;
                    return;
                } else {
                    this.normalCount++;
                }
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

    private List<Map<String, Object>> getTaskSmryInfo(String searchCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("taskSmryInfoTpCd", searchCd);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTaskSmryInfo", paramMap);
    }

    private String setTaskSmryInfo(Map<String, Object> txtVal) {
        return (String) txtVal.get("TASK_SMRY_INFO_VAL_TXT");
    }

    private List<OpenSvcTaskDtlBean> getTargetOpenSvcTask(List<String> openSvcTaskInfoValTxtList) {

        List<OpenSvcTaskDtlBean> beanList = new ArrayList<OpenSvcTaskDtlBean>();
        List<Map<String, Object>> openSvcTaskList;

        try {
            openSvcTaskList = getOpenSvcTask(openSvcTaskInfoValTxtList);
            for (int i = 0; i < openSvcTaskList.size(); i++) {
                Map<String, Object> openSvcTaskInfo = openSvcTaskList.get(i);
                OpenSvcTaskDtlBean bean = setOpenSvcTaskBean(openSvcTaskInfo);
                beanList.add(bean);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return beanList;
    }

    private List<Map<String, Object>> getOpenSvcTask(List<String> openSvcTaskInfoValTxtList) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < openSvcTaskInfoValTxtList.size(); i++) {
            String replaceTxt = openSvcTaskInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            // add start 2016/03/28 CSA Defect#6012
            replaceTxt = replaceTxt.replace("eventCancel", SVC_DISPT_EVENT.CANCEL);
            // add end 2016/03/28 CSA Defect#6012
            replaceTxtList.add(replaceTxt);
        }
        for (int j = 0; j < replaceTxtList.size(); j++) {
            StringBuilder sb = new StringBuilder(SEARCH_PARAM);
            sb.append(String.format("%02d", j));
            paramMap.put(sb.toString(), replaceTxtList.get(j));
        }
        // add start 2020/04/16 QC#56379
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("bizAreaOrdCd", BIZ_AREA_ORG.SERVICE);
        paramMap.put("firstOrgCd", ORG_CD_SERVICE);
        paramMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        paramMap.put("pendingPo", SVC_TASK_STS.PENDING_PO);
        paramMap.put("creditHold", SVC_TASK_STS.CREDIT_HOLD);
        // add end 2020/04/16 QC#56379

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOpenSvcTask", paramMap);
    }

    private OpenSvcTaskDtlBean setOpenSvcTaskBean(Map<String, Object> openSvcTaskInfo) {

        OpenSvcTaskDtlBean bean = new OpenSvcTaskDtlBean();
        bean.setSvcTaskRcvDt((String) openSvcTaskInfo.get("SVC_TASK_RCV_DT"));
        bean.setSvcTaskCloDt((String) openSvcTaskInfo.get("SVC_TASK_CLO_DT"));
        bean.setSvcTaskStsCd((String) openSvcTaskInfo.get("SVC_TASK_STS_CD"));
        bean.setSvcTaskRejDt((String) openSvcTaskInfo.get("SVC_TASK_REJ_DT"));
        bean.setSvcTaskApvlDtDt((String) openSvcTaskInfo.get("SVC_TASK_APVL_DT"));
        bean.setSvcRspTmNum((BigDecimal) openSvcTaskInfo.get("SVC_RSP_TM_NUM"));
        bean.setTechCd((String) openSvcTaskInfo.get("TECH_CD"));
        bean.setOpenTaskFlg((String) openSvcTaskInfo.get("OPEN_TASK_FLG"));
        bean.setSvcTaskRelFlg((String) openSvcTaskInfo.get("SVC_TASK_REL_FLG"));
        bean.setDsKeyAcctFlg((String) openSvcTaskInfo.get("DS_KEY_ACCT_FLG"));
        bean.setSvcCallSrcTpCd((String) openSvcTaskInfo.get("SVC_CALL_SRC_TP_CD"));
        bean.setSvcMgrPsnCd((String) openSvcTaskInfo.get("SVC_MGR_PSN_CD"));
        bean.setMgrOrgLayerNum((BigDecimal) openSvcTaskInfo.get("MGR_ORG_LAYER_NUM"));
        bean.setFirstOrgCd((String) openSvcTaskInfo.get("FIRST_ORG_CD"));
        bean.setFirstOrgNum((String) openSvcTaskInfo.get("FIRST_ORG_NM"));
        bean.setScdOrgCd((String) openSvcTaskInfo.get("SCD_ORG_CD"));
        bean.setScdOrgNum((String) openSvcTaskInfo.get("SCD_ORG_NM"));
        bean.setThirdOrgCd((String) openSvcTaskInfo.get("THIRD_ORG_CD"));
        bean.setThirdOrgNum((String) openSvcTaskInfo.get("THIRD_ORG_NM"));
        bean.setFrthOrgCd((String) openSvcTaskInfo.get("FRTH_ORG_CD"));
        bean.setFrthOrgNum((String) openSvcTaskInfo.get("FRTH_ORG_NM"));
        bean.setFifthOrgCd((String) openSvcTaskInfo.get("FIFTH_ORG_CD"));
        bean.setFifthOrgNum((String) openSvcTaskInfo.get("FIFTH_ORG_NM"));
        bean.setSixthOrgCd((String) openSvcTaskInfo.get("SIXTH_ORG_CD"));
        bean.setSixthOrgNum((String) openSvcTaskInfo.get("SIXTH_ORG_NM"));
        bean.setSvnthOrgCd((String) openSvcTaskInfo.get("SVNTH_ORG_CD"));
        bean.setSvnthOrgNum((String) openSvcTaskInfo.get("SVNTH_ORG_NM"));
        bean.setEighthOrgCd((String) openSvcTaskInfo.get("EIGHTH_ORG_CD"));
        bean.setEighthOrgNum((String) openSvcTaskInfo.get("EIGHTH_ORG_NM"));
        bean.setNinthOrgCd((String) openSvcTaskInfo.get("NINTH_ORG_CD"));
        bean.setNinthOrgNum((String) openSvcTaskInfo.get("NINTH_ORG_NM"));
        bean.setTenthOrgCd((String) openSvcTaskInfo.get("TENTH_ORG_CD"));
        bean.setTenthOrgNum((String) openSvcTaskInfo.get("TENTH_ORG_NM"));
        bean.setElvthOrgCd((String) openSvcTaskInfo.get("ELVTH_ORG_CD"));
        bean.setElvthOrgNum((String) openSvcTaskInfo.get("ELVTH_ORG_NM"));
        bean.setFsrNum((String) openSvcTaskInfo.get("FSR_NUM"));
        bean.setSvcTaskNum((String) openSvcTaskInfo.get("SVC_TASK_NUM"));
        bean.setIttNum((String) openSvcTaskInfo.get("ITT_NUM"));
        bean.setFsrStsCd((String) openSvcTaskInfo.get("FSR_STS_CD"));
        bean.setFsrVisitStsCd((String) openSvcTaskInfo.get("FSR_VISIT_STS_CD"));
        bean.setSvcMachMstrPk((BigDecimal) openSvcTaskInfo.get("SVC_MACH_MSTR_PK"));
        bean.setSerNum((String) openSvcTaskInfo.get("SER_NUM"));
        bean.setMdlId((BigDecimal) openSvcTaskInfo.get("MDL_ID"));
        bean.setFirstProdCtrlCd((String) openSvcTaskInfo.get("FIRST_PROD_CTRL_CD"));
        bean.setShipToCustCd((String) openSvcTaskInfo.get("SHIP_TO_CUST_CD"));
        bean.setSoNum((String) openSvcTaskInfo.get("SO_NUM"));
        bean.setCustTelNum((String) openSvcTaskInfo.get("CUST_TEL_NUM"));
        bean.setBizHrsMonFriFromTm((String) openSvcTaskInfo.get("BIZ_HRS_MON_FRI_FROM_TM"));
        bean.setBizHrsMonFriToTm((String) openSvcTaskInfo.get("BIZ_HRS_MON_FRI_TO_TM"));
        bean.setSvcTaskRcvTm((String) openSvcTaskInfo.get("SVC_TASK_RCV_TM"));
        bean.setDsSvcCallTpCd((String) openSvcTaskInfo.get("DS_SVC_CALL_TP_CD"));
        bean.setSvcBillTpCd((String) openSvcTaskInfo.get("SVC_BILL_TP_CD"));
        bean.setSvcCmntTxt((String) openSvcTaskInfo.get("SVC_CMNT_TXT"));
        bean.setSvcTaskCpltDt((String) openSvcTaskInfo.get("SVC_TASK_CPLT_DT"));
        bean.setSvcTaskCpltTm((String) openSvcTaskInfo.get("SVC_TASK_CPLT_TM"));
        bean.setSvcPblmTpCd((String) openSvcTaskInfo.get("SVC_PBLM_TP_CD"));
        bean.setSvcPblmLocTpCd((String) openSvcTaskInfo.get("SVC_PBLM_LOC_TP_CD"));
        bean.setSvcPblmRsnTpCd((String) openSvcTaskInfo.get("SVC_PBLM_RSN_TP_CD"));
        bean.setSvcPblmCrctTpCd((String) openSvcTaskInfo.get("SVC_PBLM_CRCT_TP_CD"));
        bean.setCltrPsnCd((String) openSvcTaskInfo.get("CLTR_PSN_CD"));
        bean.setSvcMgrTpCd((String) openSvcTaskInfo.get("SVC_MGR_TP_CD"));
        // START 2018/07/04 K.Kim [QC#26894,ADD]
        bean.setLateStartTs((String) openSvcTaskInfo.get("LATE_START_TS"));
        // END 2018/07/04 K.Kim [QC#26894,ADD]
        return bean;
    }

    private boolean createOpenSvcTaskDtl(OpenSvcTaskDtlBean openSvcTaskList) {

        BigDecimal openSvcTaskDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.OPEN_SVC_TASK_DTL_SQ);

        OPEN_SVC_TASK_DTLTMsg openSvcTaskDtlTMsg = new OPEN_SVC_TASK_DTLTMsg();
        setValue(openSvcTaskDtlTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(openSvcTaskDtlTMsg.openSvcTaskDtlPk, openSvcTaskDtlPk);
        setValue(openSvcTaskDtlTMsg.openTaskTs, this.procTs);
        setValue(openSvcTaskDtlTMsg.svcCallSrcTpCd, openSvcTaskList.getSvcCallSrcTpCd());
        setValue(openSvcTaskDtlTMsg.svcMgrTpCd, openSvcTaskList.getSvcMgrTpCd());
        setValue(openSvcTaskDtlTMsg.svcMgrPsnCd, openSvcTaskList.getSvcMgrPsnCd());
        setValue(openSvcTaskDtlTMsg.mgrOrgLayerNum, openSvcTaskList.getMgrOrgLayerNum());

        setValue(openSvcTaskDtlTMsg.firstOrgCd, openSvcTaskList.getFirstOrgCd());
        setValue(openSvcTaskDtlTMsg.firstOrgNm, openSvcTaskList.getFirstOrgNum());
        setValue(openSvcTaskDtlTMsg.scdOrgCd, openSvcTaskList.getScdOrgCd());
        setValue(openSvcTaskDtlTMsg.scdOrgNm, openSvcTaskList.getScdOrgNum());
        setValue(openSvcTaskDtlTMsg.thirdOrgCd, openSvcTaskList.getThirdOrgCd());
        setValue(openSvcTaskDtlTMsg.thirdOrgNm, openSvcTaskList.getThirdOrgNum());
        setValue(openSvcTaskDtlTMsg.frthOrgCd, openSvcTaskList.getFrthOrgCd());
        setValue(openSvcTaskDtlTMsg.frthOrgNm, openSvcTaskList.getFrthOrgNum());
        setValue(openSvcTaskDtlTMsg.fifthOrgCd, openSvcTaskList.getFifthOrgCd());
        setValue(openSvcTaskDtlTMsg.fifthOrgNm, openSvcTaskList.getFifthOrgNum());
        setValue(openSvcTaskDtlTMsg.sixthOrgCd, openSvcTaskList.getSixthOrgCd());
        setValue(openSvcTaskDtlTMsg.sixthOrgNm, openSvcTaskList.getSixthOrgNum());
        setValue(openSvcTaskDtlTMsg.svnthOrgCd, openSvcTaskList.getSvnthOrgCd());
        setValue(openSvcTaskDtlTMsg.svnthOrgNm, openSvcTaskList.getSvnthOrgNum());
        setValue(openSvcTaskDtlTMsg.eighthOrgCd, openSvcTaskList.getEighthOrgCd());
        setValue(openSvcTaskDtlTMsg.eighthOrgNm, openSvcTaskList.getEighthOrgNum());
        setValue(openSvcTaskDtlTMsg.ninthOrgCd, openSvcTaskList.getNinthOrgCd());
        setValue(openSvcTaskDtlTMsg.ninthOrgNm, openSvcTaskList.getNinthOrgNum());
        setValue(openSvcTaskDtlTMsg.tenthOrgCd, openSvcTaskList.getTenthOrgCd());
        setValue(openSvcTaskDtlTMsg.tenthOrgNm, openSvcTaskList.getTenthOrgNum());
        setValue(openSvcTaskDtlTMsg.elvthOrgCd, openSvcTaskList.getElvthOrgCd());
        setValue(openSvcTaskDtlTMsg.elvthOrgNm, openSvcTaskList.getElvthOrgNum());

        setValue(openSvcTaskDtlTMsg.fsrNum, openSvcTaskList.getFsrNum());
        setValue(openSvcTaskDtlTMsg.svcTaskNum, openSvcTaskList.getSvcTaskNum());
        setValue(openSvcTaskDtlTMsg.ittNum, openSvcTaskList.getIttNum());
        setValue(openSvcTaskDtlTMsg.fsrStsCd, openSvcTaskList.getFsrStsCd());
        setValue(openSvcTaskDtlTMsg.fsrVisitStsCd, openSvcTaskList.getFsrVisitStsCd());
        setValue(openSvcTaskDtlTMsg.svcTaskStsCd, openSvcTaskList.getSvcTaskStsCd());
        setValue(openSvcTaskDtlTMsg.svcMachMstrPk, openSvcTaskList.getSvcMachMstrPk());
        // mod start 2016/03/28 CSA Defect#6021
        setValue(openSvcTaskDtlTMsg.serNum, openSvcTaskList.getSerNum());
        // mod end 2016/03/28 CSA Defect#6021
        setValue(openSvcTaskDtlTMsg.mdlId, openSvcTaskList.getMdlId());
        setValue(openSvcTaskDtlTMsg.firstProdCtrlCd, openSvcTaskList.getFirstProdCtrlCd());
        setValue(openSvcTaskDtlTMsg.shipToCustCd, openSvcTaskList.getShipToCustCd());
        setValue(openSvcTaskDtlTMsg.soNum, openSvcTaskList.getSoNum());
        setValue(openSvcTaskDtlTMsg.custTelNum, openSvcTaskList.getCustTelNum());
        setValue(openSvcTaskDtlTMsg.svcAvalTxt, setSvcAvalTxt(openSvcTaskList));
        setValue(openSvcTaskDtlTMsg.svcTaskRcvDt, openSvcTaskList.getSvcTaskRcvDt());
        setValue(openSvcTaskDtlTMsg.svcTaskRcvTm, openSvcTaskList.getSvcTaskRcvTm());
        setValue(openSvcTaskDtlTMsg.techCd, openSvcTaskList.getTechCd());
        setValue(openSvcTaskDtlTMsg.dsSvcCallTpCd, openSvcTaskList.getDsSvcCallTpCd());
        setValue(openSvcTaskDtlTMsg.svcBillTpCd, openSvcTaskList.getSvcBillTpCd());
        setValue(openSvcTaskDtlTMsg.svcCmntTxt, openSvcTaskList.getSvcCmntTxt());
        setValue(openSvcTaskDtlTMsg.svcTaskCpltDt, openSvcTaskList.getSvcTaskCpltDt());
        setValue(openSvcTaskDtlTMsg.svcTaskCpltTm, openSvcTaskList.getSvcTaskCpltTm());
        setValue(openSvcTaskDtlTMsg.svcRspTmNum, openSvcTaskList.getSvcRspTmNum());
        setValue(openSvcTaskDtlTMsg.svcPblmTpCd, openSvcTaskList.getSvcPblmTpCd());
        setValue(openSvcTaskDtlTMsg.svcPblmLocTpCd, openSvcTaskList.getSvcPblmLocTpCd());
        setValue(openSvcTaskDtlTMsg.svcPblmRsnTpCd, openSvcTaskList.getSvcPblmRsnTpCd());
        setValue(openSvcTaskDtlTMsg.svcPblmCrctTpCd, openSvcTaskList.getSvcPblmRsnTpCd());
        setValue(openSvcTaskDtlTMsg.cltrPsnCd, openSvcTaskList.getCltrPsnCd());

        if ((openSvcTaskList.getSvcTaskRcvDt() != null)
                && (openSvcTaskList.getSvcTaskRcvDt().compareTo(this.salesDate) < 0)) {
            setValue(openSvcTaskDtlTMsg.openTaskLtstFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.openTaskLtstFlg, ZYPConstant.FLG_OFF_N);
        }
        if ((openSvcTaskList.getSvcTaskRcvDt() != null)
                && (openSvcTaskList.getSvcTaskRcvDt().compareTo(this.salesDate) == 0)) {
            setValue(openSvcTaskDtlTMsg.inProcTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.inProcTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        if ((openSvcTaskList.getSvcTaskCloDt() != null)
                && (openSvcTaskList.getSvcTaskCloDt().compareTo(this.salesDate) == 0)) {
            setValue(openSvcTaskDtlTMsg.cloTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.cloTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        if (openSvcTaskList.getSvcTaskStsCd().equals(SVC_TASK_STS.CANCELLED)) {
            setValue(openSvcTaskDtlTMsg.cancTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.cancTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        if (((openSvcTaskList.getSvcTaskRejDt() != null) && (openSvcTaskList.getSvcTaskRejDt().compareTo(this.salesDate) == 0))
                && (openSvcTaskList.getSvcTaskApvlDtDt() == null)) {
            setValue(openSvcTaskDtlTMsg.rejTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.rejTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        // Mod Start 2018/07/09 QC#27017
        if (openSvcTaskList.getSvcRspTmNum() != null && ZYPConstant.FLG_ON_Y.equals(openSvcTaskDtlTMsg.cloTaskFlg.getValue())) {
            setValue(openSvcTaskDtlTMsg.tmRspFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.tmRspFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2019/03/28 K.Kitamura [QC#30233, ADD]
        if (openSvcTaskList.getFsrVisitStsCd().equals(SVC_TASK_STS.TBO)){
            setValue(openSvcTaskDtlTMsg.toBeOptmTaskFlg, ZYPConstant.FLG_ON_Y);
        } else{
            setValue(openSvcTaskDtlTMsg.toBeOptmTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        if (openSvcTaskList.getFsrVisitStsCd().equals(SVC_TASK_STS.SCHEDULED)){
            setValue(openSvcTaskDtlTMsg.schdTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.schdTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2019/03/28 K.Kitamura [QC#30233, ADD]
        // Mod End 2018/07/09 QC#27017
        // Mod Start 2017/12/13 QC#23100
        // START 2019/03/28 K.Kitamura [QC#30233, MOD]
        if (openSvcTaskList.getTechCd() != null
                // && openSvcTaskList.getSvcTaskStsCd() != null
                // && openSvcTaskList.getSvcTaskStsCd().compareTo(SVC_TASK_STS.ASSIGNED) >= 0
                // && !SVC_TASK_STS.CLOSED.equals(openSvcTaskList.getSvcTaskStsCd())
                // && !SVC_TASK_STS.CANCELLED.equals(openSvcTaskList.getSvcTaskStsCd())) {
                   && openSvcTaskList.getFsrVisitStsCd() != null
                   && openSvcTaskList.getFsrVisitStsCd().compareTo(SVC_TASK_STS.ASSIGNED) >= 0
                   && !SVC_TASK_STS.CLOSED.equals(openSvcTaskList.getFsrVisitStsCd())
                   && !SVC_TASK_STS.CANCELLED.equals(openSvcTaskList.getFsrVisitStsCd())) {
                // END 2019/03/28 K.Kitamura [QC#30233, MOD]
            setValue(openSvcTaskDtlTMsg.asgTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.asgTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        // Mod End 2017/12/13 QC#23100
        setValue(openSvcTaskDtlTMsg.openTaskFlg, openSvcTaskList.getOpenTaskFlg());
        // mod start 2016/03/28 CSA Defect#6012
        if ((openSvcTaskList.getSvcTaskRelFlg() != null)
                && (openSvcTaskList.getSvcTaskRelFlg().equals(ZYPConstant.FLG_OFF_N))
                && (openSvcTaskList.getOpenTaskFlg().equals(ZYPConstant.FLG_ON_Y))) {
            setValue(openSvcTaskDtlTMsg.hldTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.hldTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2018/07/04 K.Kim [QC#26894,ADD]
        // START 2018/07/31 K.Kitachi [QC#27519, MOD]
        if ((openSvcTaskList.getLateStartTs() != null)
//                && (openSvcTaskList.getLateStartTs().compareTo(this.salesDate) <= 0)) {
                && (openSvcTaskList.getLateStartTs().compareTo(this.procTs) <= 0)
                && (SVC_TASK_STS.TBO.equals(openSvcTaskList.getFsrVisitStsCd()) || SVC_TASK_STS.SCHEDULED.equals(openSvcTaskList.getFsrVisitStsCd()) || SVC_TASK_STS.ASSIGNED.equals(openSvcTaskList.getFsrVisitStsCd()))) {
        // END 2018/07/31 K.Kitachi [QC#27519, MOD]
            setValue(openSvcTaskDtlTMsg.esclTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.esclTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2018/07/04 K.Kim [QC#26894,ADD]
        if (openSvcTaskList.getDsKeyAcctFlg().equals(ZYPConstant.FLG_ON_Y)
                && openSvcTaskList.getOpenTaskFlg().equals(ZYPConstant.FLG_ON_Y)) {
            setValue(openSvcTaskDtlTMsg.vipTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.vipTaskFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod end 2016/03/28 CSA Defect#6012
        if (openSvcTaskList.getSvcTaskStsCd().equals(SVC_TASK_STS.DEBRIEF_ERROR)) {
            setValue(openSvcTaskDtlTMsg.attnTaskFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(openSvcTaskDtlTMsg.attnTaskFlg, ZYPConstant.FLG_OFF_N);
        }

        S21FastTBLAccessor.insert(openSvcTaskDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(openSvcTaskDtlTMsg.getReturnCode())) {
            rollback();
            addErrMsg(NSBM0121E, new String[] {"OPEN_SVC_TASK_DTL", createFaileMsg("OPEN_SVC_TASK_DTL_PK", openSvcTaskDtlPk) });
            return false;
        }
        return true;
    }

    private String setSvcAvalTxt(OpenSvcTaskDtlBean openSvcTaskList) {

        StringBuilder sb = new StringBuilder();
        if ((openSvcTaskList.getBizHrsMonFriFromTm() != null)
                && (openSvcTaskList.getBizHrsMonFriToTm() != null)) {
            sb.append(openSvcTaskList.getBizHrsMonFriFromTm().toString().substring(SUB_STR_POS_0, SUB_STR_POS_2));
            sb.append(STR_COLON);
            sb.append(openSvcTaskList.getBizHrsMonFriFromTm().toString().substring(SUB_STR_POS_2, SUB_STR_POS_4));
            sb.append(STR_HYPHEN);
            sb.append(openSvcTaskList.getBizHrsMonFriToTm().toString().substring(SUB_STR_POS_0, SUB_STR_POS_2));
            sb.append(STR_COLON);
            sb.append(openSvcTaskList.getBizHrsMonFriToTm().toString().substring(SUB_STR_POS_2, SUB_STR_POS_4));
        } else if ((openSvcTaskList.getBizHrsMonFriFromTm() != null)
                && (openSvcTaskList.getBizHrsMonFriToTm() == null)) {
            sb.append(openSvcTaskList.getBizHrsMonFriFromTm().toString().substring(SUB_STR_POS_0, SUB_STR_POS_2));
            sb.append(STR_COLON);
            sb.append(openSvcTaskList.getBizHrsMonFriFromTm().toString().substring(SUB_STR_POS_2, SUB_STR_POS_4));
            sb.append(STR_HYPHEN);
        } else if ((openSvcTaskList.getBizHrsMonFriFromTm() == null)
                && (openSvcTaskList.getBizHrsMonFriToTm() != null)) {
            sb.append(STR_HYPHEN);
            sb.append(openSvcTaskList.getBizHrsMonFriToTm().toString().substring(SUB_STR_POS_0, SUB_STR_POS_2));
            sb.append(STR_COLON);
            sb.append(openSvcTaskList.getBizHrsMonFriToTm().toString().substring(SUB_STR_POS_2, SUB_STR_POS_4));
        } else {
            return null;
        }
        return sb.toString();
    }

    private List<TargetOrgCdBean> getTargetOrgCd(List<String> targetOrgCdInfoValTxtList) {

        List<TargetOrgCdBean> beanList = new ArrayList<TargetOrgCdBean>();
        List<Map<String, Object>> orgCdList;

        try {
            orgCdList = getOrgCd(targetOrgCdInfoValTxtList);
            for (int i = 0; i < orgCdList.size(); i++) {
                Map<String, Object> orgCdInfo = orgCdList.get(i);
                TargetOrgCdBean bean = setOrgCdBean(orgCdInfo);
                beanList.add(bean);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return beanList;
    }

    private List<Map<String, Object>> getOrgCd(List<String> targetOrgCdInfoValTxtList) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> replaceTxtList = new ArrayList<String>();

        for (int i = 0; i < targetOrgCdInfoValTxtList.size(); i++) {
            String replaceTxt = targetOrgCdInfoValTxtList.get(i).replace("glblCmpyCd", this.glblCmpyCd);
            replaceTxt = replaceTxt.replace("slsDt", this.salesDate);
            // add start 2016/03/24 CSA Defect#5664
            replaceTxt = replaceTxt.replace("serviceOrg", ORG_CD_SERVICE);
            // add end 2016/03/24 CSA Defect#5664
            replaceTxtList.add(replaceTxt);
        }
        for (int j = 0; j < replaceTxtList.size(); j++) {
            StringBuilder sb = new StringBuilder(SEARCH_PARAM);
            sb.append(String.format("%02d", j));
            paramMap.put(sb.toString(), replaceTxtList.get(j));
        }

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrgCode", paramMap);
    }

    private TargetOrgCdBean setOrgCdBean(Map<String, Object> orgCdInfo) {

        TargetOrgCdBean bean = new TargetOrgCdBean();
        // mod start 2016/03/24 CSA Defect#5664
        bean.setPsnCd((String) orgCdInfo.get("PSN_CD"));
        // mod end 2016/03/24 CSA Defect#5664
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
        return bean;
    }

    private BigDecimal prevOpenTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_PREV_OPEN_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> prevOpenTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            prevOpenTaskCntList = cntPrevOpenTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setPrevOpenTaskCnt(prevOpenTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getPrevOpenTaskCnt();
    }

    private List<Map<String, Object>> cntPrevOpenTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntPrevOpenTask", paramMap);
    }

    private OpenSvcTaskSmryBean setPrevOpenTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setPrevOpenTaskCnt((BigDecimal) taskCntInfo.get("PREV_OPEN_TASK_CNT"));
        return bean;
    }

    private BigDecimal inprocTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_INPROC_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> inprocTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            inprocTaskCntList = cntInprocTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setInprocTaskCnt(inprocTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getInprocTaskCnt();
    }

    private List<Map<String, Object>> cntInprocTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntInProcTask", paramMap);
    }

    private OpenSvcTaskSmryBean setInprocTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setInprocTaskCnt((BigDecimal) taskCntInfo.get("INPROC_TASK_CNT"));
        return bean;
    }

    private BigDecimal cloTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CLO_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> cloTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            cloTaskCntList = cntCloTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setCloTaskCnt(cloTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCloTaskCnt();
    }

    private List<Map<String, Object>> cntCloTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCloTask", paramMap);
    }

    private OpenSvcTaskSmryBean setCloTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setCloTaskCnt((BigDecimal) taskCntInfo.get("CLO_TASK_CNT"));
        return bean;
    }

    private BigDecimal cancTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_CANC_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> cancTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            cancTaskCntList = cntCancTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setCancTaskCnt(cancTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getCancTaskCnt();
    }

    private List<Map<String, Object>> cntCancTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntCancTask", paramMap);
    }

    private OpenSvcTaskSmryBean setCancTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setCancTaskCnt((BigDecimal) taskCntInfo.get("CANC_TASK_CNT"));
        return bean;
    }

    private BigDecimal rejTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_REJ_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> rejTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            rejTaskCntList = cntRejTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setRejTaskCnt(rejTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getRejTaskCnt();
    }

    private List<Map<String, Object>> cntRejTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntRejTask", paramMap);
    }

    private OpenSvcTaskSmryBean setRejTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setRejTaskCnt((BigDecimal) taskCntInfo.get("REJ_TASK_CNT"));
        return bean;
    }

    private BigDecimal avgTmRspCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_AVG_TM_RSP);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> avgTmRspCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            avgTmRspCntList = cntAvgTmRsp(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setAvgTmRspCnt(avgTmRspCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAvgTmRspCnt();
    }

    private List<Map<String, Object>> cntAvgTmRsp(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAvgTmRsp", paramMap);
    }

    private OpenSvcTaskSmryBean setAvgTmRspCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setAvgTmRspCnt((BigDecimal) taskCntInfo.get("AVG_TM_RSP_CNT"));
        return bean;
    }

    private BigDecimal svcRspTmNum(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(NUM_SVC_RSP_TM);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> svcRspTmNumList;
        OpenSvcTaskSmryBean bean = null;
        try {
            svcRspTmNumList = numSvcRspTm(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setSvcRspTmNum(svcRspTmNumList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getSvcRspTmNum();
    }

    private List<Map<String, Object>> numSvcRspTm(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("numSvcRspTm", paramMap);
    }

    private OpenSvcTaskSmryBean setSvcRspTmNum(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setSvcRspTmNum((BigDecimal) taskCntInfo.get("SVC_RSP_TM_NUM"));
        return bean;
    }
    // START 2019/03/28 K.Kitamura [QC#30233, ADD]
    private BigDecimal tboTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_TBO_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> tboTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            tboTaskCntList = cntTboTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setTboTaskCnt(tboTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getTboTaskCnt();
    }

    private List<Map<String, Object>> cntTboTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntTboTask", paramMap);
    }

    private OpenSvcTaskSmryBean setTboTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setTboTaskCnt((BigDecimal) taskCntInfo.get("TBO_TASK_CNT"));
        return bean;
    }

    private BigDecimal schdTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_SCHD_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> schdTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            schdTaskCntList = cntSchdTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setSchdTaskCnt(schdTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getSchdTaskCnt();
    }

    private List<Map<String, Object>> cntSchdTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntSchdTask", paramMap);
    }

    private OpenSvcTaskSmryBean setSchdTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setSchdTaskCnt((BigDecimal) taskCntInfo.get("SCHD_TASK_CNT"));
        return bean;
    }
    // END 2019/03/28 K.Kitamura [QC#30233, ADD]
    private BigDecimal asgTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_ASG_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> asgTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            asgTaskCntList = cntAsgTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setAsgTaskCnt(asgTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAsgTaskCnt();
    }

    private List<Map<String, Object>> cntAsgTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAsgTask", paramMap);
    }

    private OpenSvcTaskSmryBean setAsgTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setAsgTaskCnt((BigDecimal) taskCntInfo.get("ASG_TASK_CNT"));
        return bean;
    }

    private BigDecimal openTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_OPEN_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> openTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            openTaskCntList = cntOpenTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setOpenTaskCnt(openTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getOpenTaskCnt();
    }

    private List<Map<String, Object>> cntOpenTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntOpenTask", paramMap);
    }

    private OpenSvcTaskSmryBean setOpenTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setOpenTaskCnt((BigDecimal) taskCntInfo.get("OPEN_TASK_CNT"));
        return bean;
    }

    private BigDecimal hldTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_HLD_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> hldTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            hldTaskCntList = cntHldTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setHldTaskCnt(hldTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getHldTaskCnt();
    }

    private List<Map<String, Object>> cntHldTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntHldTask", paramMap);
    }

    private OpenSvcTaskSmryBean setHldTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setHldTaskCnt((BigDecimal) taskCntInfo.get("HLD_TASK_CNT"));
        return bean;
    }

    private BigDecimal esclTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_ESCL_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> esclTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            esclTaskCntList = cntEsclTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setEsclTaskCnt(esclTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getEsclTaskCnt();
    }

    private List<Map<String, Object>> cntEsclTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntEsclTask", paramMap);
    }

    private OpenSvcTaskSmryBean setEsclTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setEsclTaskCnt((BigDecimal) taskCntInfo.get("ESCL_TASK_CNT"));
        return bean;
    }

    private BigDecimal vipTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_VIP_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> vipTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            vipTaskCntList = cntVipTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setVipTaskCnt(vipTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getVipTaskCnt();
    }

    private List<Map<String, Object>> cntVipTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntVipTask", paramMap);
    }

    private OpenSvcTaskSmryBean setVipTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setVipTaskCnt((BigDecimal) taskCntInfo.get("VIP_TASK_CNT"));
        return bean;
    }

    private BigDecimal attnTaskCnt(TargetOrgCdBean targetOrgCd) {

        List<String> taskSmryInfoValTxtList = getTaskSmryInfoValTxt(CNT_ATTN_TASK);
        BigDecimal ret = BigDecimal.ZERO;
        if (taskSmryInfoValTxtList.isEmpty()) {
            return ret;
        }

        List<Map<String, Object>> attnTaskCntList;
        OpenSvcTaskSmryBean bean = null;
        try {
            attnTaskCntList = cntAttnTask(taskSmryInfoValTxtList.get(0), targetOrgCd);
            bean = setAttnTaskCnt(attnTaskCntList.get(0));
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return bean.getAttnTaskCnt();
    }

    private List<Map<String, Object>> cntAttnTask(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) throws SQLException {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap = setParamMap(taskSmryInfoValTxt, targetOrgCd);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cntAttnTask", paramMap);
    }
    
    // add start 2016/03/24 CSA Defect#5664
    private BigDecimal findOpenTaskDtlByPsnCd(String psnCd) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("psnCd", psnCd);

        return (BigDecimal) ssmBatchClient.queryObject("findOpenTaskDtlByPsnCd", paramMap);
    }
    // add end 2016/03/24 CSA Defect#5664

    private OpenSvcTaskSmryBean setAttnTaskCnt(Map<String, Object> taskCntInfo) {

        OpenSvcTaskSmryBean bean = new OpenSvcTaskSmryBean();
        bean.setAttnTaskCnt((BigDecimal) taskCntInfo.get("ATTN_TASK_CNT"));
        return bean;
    }

    private Map<String, Object> setParamMap(String taskSmryInfoValTxt, TargetOrgCdBean targetOrgCd) {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        // TODO MOD START 2020/12/07 CSA Defect#58041
        /*
        String replaceTxt = taskSmryInfoValTxt.replace("glblCmpyCd", this.glblCmpyCd);
        replaceTxt = replaceTxt.replace("sysDt", this.procTs);
        replaceTxt = replaceTxt.replace("svcMnrTpCd", targetOrgCd.getSvcMgrTpCd());
        // add start 2016/03/24 CSA Defect#5664
        replaceTxt = replaceTxt.replace("svcMgrPsnCd", targetOrgCd.getPsnCd());
        // add end 2016/03/24 CSA Defect#5664
        replaceTxt = replaceTxt.replace("firstOrgCd", targetOrgCd.getFirstOrgCd());
        replaceTxt = replaceTxt.replace("scdOrgCd", targetOrgCd.getScdOrgCd());
        if (targetOrgCd.getThirdOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'thirdOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("thirdOrgCd", targetOrgCd.getThirdOrgCd());
        }
        if (targetOrgCd.getFrthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'frthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("frthOrgCd", targetOrgCd.getFrthOrgCd());
        }
        if (targetOrgCd.getFifthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'fifthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("fifthOrgCd", targetOrgCd.getFifthOrgCd());
        }
        if (targetOrgCd.getSixthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'sixthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("sixthOrgCd", targetOrgCd.getSixthOrgCd());
        }
        if (targetOrgCd.getSvnthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'svnthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("svnthOrgCd", targetOrgCd.getSvnthOrgCd());
        }
        if (targetOrgCd.getEighthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'eighthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("eighthOrgCd", targetOrgCd.getEighthOrgCd());
        }
        if (targetOrgCd.getNinthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'ninthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("ninthOrgCd", targetOrgCd.getNinthOrgCd());
        }
        if (targetOrgCd.getTenthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'tenthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("tenthOrgCd", targetOrgCd.getTenthOrgCd());
        }
        if (targetOrgCd.getElvthOrgCd() == null) {
            replaceTxt = replaceTxt.replace("= 'elvthOrgCd'", " is null");
        } else {
            replaceTxt = replaceTxt.replace("elvthOrgCd", targetOrgCd.getElvthOrgCd());
        }
        paramMap.put(SEARCH_PARAM, replaceTxt);
        */
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("sysDt", this.procTs);
        paramMap.put("svcMnrTpCd", targetOrgCd.getSvcMgrTpCd());
        paramMap.put("svcMgrPsnCd", targetOrgCd.getPsnCd());
        paramMap.put("firstOrgCd", targetOrgCd.getFirstOrgCd());
        paramMap.put("scdOrgCd", targetOrgCd.getScdOrgCd());
        paramMap.put("thirdOrgCd", targetOrgCd.getThirdOrgCd());
        paramMap.put("frthOrgCd", targetOrgCd.getFrthOrgCd());
        paramMap.put("fifthOrgCd", targetOrgCd.getFifthOrgCd());
        paramMap.put("sixthOrgCd", targetOrgCd.getSixthOrgCd());
        paramMap.put("svnthOrgCd", targetOrgCd.getSvnthOrgCd());
        paramMap.put("eighthOrgCd", targetOrgCd.getEighthOrgCd());
        paramMap.put("ninthOrgCd", targetOrgCd.getNinthOrgCd());
        paramMap.put("tenthOrgCd", targetOrgCd.getTenthOrgCd());
        paramMap.put("elvthOrgCd", targetOrgCd.getElvthOrgCd());
        // MOD END 2020/12/07 CSA Defect#58041

        return paramMap;
    }

   private boolean createOpenSvcTaskSmry(OpenSvcTaskSmryBean openSvcTaskSmry) {

        BigDecimal openSvcTaskSmryPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.OPEN_SVC_TASK_SMRY_SQ);

        OPEN_SVC_TASK_SMRYTMsg openSvcTaskSmryTMsg = new OPEN_SVC_TASK_SMRYTMsg();
        setValue(openSvcTaskSmryTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(openSvcTaskSmryTMsg.openSvcTaskSmryPk, openSvcTaskSmryPk);
        setValue(openSvcTaskSmryTMsg.openTaskTs, this.procTs);
        setValue(openSvcTaskSmryTMsg.svcMgrTpCd, openSvcTaskSmry.getSvcMgrTpCd());
        setValue(openSvcTaskSmryTMsg.svcMgrPsnCd, openSvcTaskSmry.getSvcMgrPsnCd());
        setValue(openSvcTaskSmryTMsg.mgrOrgLayerNum, openSvcTaskSmry.getMgrOrgLayerNum());

        setValue(openSvcTaskSmryTMsg.firstOrgCd, openSvcTaskSmry.getFirstOrgCd());
        setValue(openSvcTaskSmryTMsg.firstOrgNm, openSvcTaskSmry.getFirstOrgNum());
        setValue(openSvcTaskSmryTMsg.scdOrgCd, openSvcTaskSmry.getScdOrgCd());
        setValue(openSvcTaskSmryTMsg.scdOrgNm, openSvcTaskSmry.getScdOrgNum());
        setValue(openSvcTaskSmryTMsg.thirdOrgCd, openSvcTaskSmry.getThirdOrgCd());
        setValue(openSvcTaskSmryTMsg.thirdOrgNm, openSvcTaskSmry.getThirdOrgNum());
        setValue(openSvcTaskSmryTMsg.frthOrgCd, openSvcTaskSmry.getFrthOrgCd());
        setValue(openSvcTaskSmryTMsg.frthOrgNm, openSvcTaskSmry.getFrthOrgNum());
        setValue(openSvcTaskSmryTMsg.fifthOrgCd, openSvcTaskSmry.getFifthOrgCd());
        setValue(openSvcTaskSmryTMsg.fifthOrgNm, openSvcTaskSmry.getFifthOrgNum());
        setValue(openSvcTaskSmryTMsg.sixthOrgCd, openSvcTaskSmry.getSixthOrgCd());
        setValue(openSvcTaskSmryTMsg.sixthOrgNm, openSvcTaskSmry.getSixthOrgNum());
        setValue(openSvcTaskSmryTMsg.svnthOrgCd, openSvcTaskSmry.getSvnthOrgCd());
        setValue(openSvcTaskSmryTMsg.svnthOrgNm, openSvcTaskSmry.getSvnthOrgNum());
        setValue(openSvcTaskSmryTMsg.eighthOrgCd, openSvcTaskSmry.getEighthOrgCd());
        setValue(openSvcTaskSmryTMsg.eighthOrgNm, openSvcTaskSmry.getEighthOrgNum());
        setValue(openSvcTaskSmryTMsg.ninthOrgCd, openSvcTaskSmry.getNinthOrgCd());
        setValue(openSvcTaskSmryTMsg.ninthOrgNm, openSvcTaskSmry.getNinthOrgNum());
        setValue(openSvcTaskSmryTMsg.tenthOrgCd, openSvcTaskSmry.getTenthOrgCd());
        setValue(openSvcTaskSmryTMsg.tenthOrgNm, openSvcTaskSmry.getTenthOrgNum());
        setValue(openSvcTaskSmryTMsg.elvthOrgCd, openSvcTaskSmry.getElvthOrgCd());
        setValue(openSvcTaskSmryTMsg.elvthOrgNm, openSvcTaskSmry.getElvthOrgNum());

        setValue(openSvcTaskSmryTMsg.prevOpenTaskCnt, openSvcTaskSmry.getPrevOpenTaskCnt());
        setValue(openSvcTaskSmryTMsg.inprocTaskCnt, openSvcTaskSmry.getInprocTaskCnt());
        setValue(openSvcTaskSmryTMsg.cloTaskCnt, openSvcTaskSmry.getCloTaskCnt());
        setValue(openSvcTaskSmryTMsg.cancTaskCnt, openSvcTaskSmry.getCancTaskCnt());
        setValue(openSvcTaskSmryTMsg.rejTaskCnt, openSvcTaskSmry.getRejTaskCnt());
        setValue(openSvcTaskSmryTMsg.avgTmRspCnt, openSvcTaskSmry.getAvgTmRspCnt());
        // START 2019/03/28 K.Kitamura [QC#30233, ADD]
        setValue(openSvcTaskSmryTMsg.toBeOptmTaskCnt, openSvcTaskSmry.getTboTaskCnt());
        setValue(openSvcTaskSmryTMsg.schdTaskCnt, openSvcTaskSmry.getSchdTaskCnt());
        // END 2019/03/28 K.Kitamura [QC#30233, ADD]
        if ((openSvcTaskSmry.getSvcRspTmNum() != null) && (openSvcTaskSmry.getAvgTmRspCnt().intValue() != 0)) {
            setValue(openSvcTaskSmryTMsg.svcRspTmNum,
                    openSvcTaskSmry.getSvcRspTmNum().divide(openSvcTaskSmry.getAvgTmRspCnt(), 0, BigDecimal.ROUND_UP));
        } else {
            setValue(openSvcTaskSmryTMsg.svcRspTmNum, BigDecimal.ZERO);
        }
        setValue(openSvcTaskSmryTMsg.asgTaskCnt, openSvcTaskSmry.getAsgTaskCnt());
        setValue(openSvcTaskSmryTMsg.openTaskCnt, openSvcTaskSmry.getOpenTaskCnt());
        setValue(openSvcTaskSmryTMsg.hldTaskCnt, openSvcTaskSmry.getHldTaskCnt());
        setValue(openSvcTaskSmryTMsg.esclTaskCnt, openSvcTaskSmry.getEsclTaskCnt());
        setValue(openSvcTaskSmryTMsg.vipTaskCnt, openSvcTaskSmry.getVipTaskCnt());
        setValue(openSvcTaskSmryTMsg.attnTaskCnt, openSvcTaskSmry.getAttnTaskCnt());
        setValue(openSvcTaskSmryTMsg.openTaskLtstFlg, ZYPConstant.FLG_ON_Y);

        S21FastTBLAccessor.insert(openSvcTaskSmryTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(openSvcTaskSmryTMsg.getReturnCode())) {
            rollback();
            addErrMsg(NSBM0121E, new String[] {"OPEN_SVC_TASK_SMRY", createFaileMsg("OPEN_SVC_TASK_SMRY_PK", openSvcTaskSmryPk) });
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
}
