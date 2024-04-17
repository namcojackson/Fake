package business.blap.ZZZL0070;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jofc2.model.Chart;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZZL0070.common.ZZZL0070CommonLogic;
import business.blap.ZZZL0070.constant.ZZZL0070Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0070BL02 extends S21BusinessHandler implements ZZZL0070Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZZL0070_INIT".equals(screenAplID)) {

            } else if ("ZZZL0070Scrn00_View".equals(screenAplID)) {
                showChart((ZZZL0070CMsg) cMsg);
            } else if ("ZZZL0070_ZZZL0071".equals(screenAplID)) {
                showChart((ZZZL0070CMsg) cMsg);
            } else if ("ZZZL0070Scrn00_AddJobId".equals(screenAplID)) {
                getProgramId((ZZZL0070CMsg) cMsg);
            } else if ("ZZZL0070Scrn00_DelJob".equals(screenAplID)) {
                delJobId((ZZZL0070CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void showChart(ZZZL0070CMsg cMsg) {

        if (!ZZZL0070CommonLogic.chkGlbCmpCd(cMsg)) {
            return;
        }
        cMsg.delFlg.clear();

        boolean flagA = getDataForJobChart(cMsg);
        boolean flagB = getDataForTableChart(cMsg);
        
        if (!flagA && !flagB) {
            cMsg.setMessageInfo("ZZZM9005W");
        }
        return;
    }

    private boolean getDataForJobChart(ZZZL0070CMsg cMsg) {

        S21SsmEZDResult ssmResult = ZZZL0070Query.getInstance().getJobInfo(cMsg);

        // No Data
        if (!ssmResult.isCodeNormal()) {
            // cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }
        HashMap<String, ArrayList<Number>> lineVal_JobTm = new HashMap<String, ArrayList<Number>>();
        HashMap<String, ArrayList<Number>> lineVal_JobCnt = new HashMap<String, ArrayList<Number>>();
        ArrayList<Number> values = null;
        List<String> step = ZZZL0070CommonLogic.getXaxleStep(cMsg.xxFromDt.getValue(), cMsg.xxToDt.getValue());

        List resultList = (List) ssmResult.getResultObject();

        int preIndx = 0;
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {

            Map resultMap = (Map) iterator.next();
            String jobId = ZZZL0070CommonLogic.nvl((String) resultMap.get("JBID"));
            String date = ZZZL0070CommonLogic.nvl((String) resultMap.get("MDATE"));
            BigDecimal totalTm = (BigDecimal) resultMap.get("TOTALTIME");
            BigDecimal execCnt = (BigDecimal) resultMap.get("EXECNT");

            if (!lineVal_JobTm.containsKey(jobId)) {
                values = new ArrayList<Number>();
                lineVal_JobTm.put(jobId, values);
                preIndx = 0;
            }
            if (!lineVal_JobCnt.containsKey(jobId)) {
                values = new ArrayList<Number>();
                lineVal_JobCnt.put(jobId, values);
                preIndx = 0;
            }

            for (int i = preIndx; i < step.size(); i++) {

                if (step.get(i).equals(date)) {
                    lineVal_JobTm.get(jobId).add(totalTm);
                    lineVal_JobCnt.get(jobId).add(execCnt);
                    preIndx = i + 1;
                    break;
                } else {
                    lineVal_JobTm.get(jobId).add(BigDecimal.ZERO);
                    lineVal_JobCnt.get(jobId).add(BigDecimal.ZERO);
                }
            }
        }
        Iterator iterator_tm = lineVal_JobTm.entrySet().iterator();
        while (iterator_tm.hasNext()) {
            Entry JobIdList = (Entry) iterator_tm.next();
            ArrayList<Number> JobValue = (ArrayList<Number>)JobIdList.getValue();
            for (int i = JobValue.size(); i < step.size(); i++) {
                JobValue.add(i, BigDecimal.ZERO);
            }
            
        }
        Iterator iterator_cnt = lineVal_JobCnt.entrySet().iterator();
        while (iterator_cnt.hasNext()) {
            Entry JobIdList = (Entry) iterator_cnt.next();
            ArrayList<Number> JobValue = (ArrayList<Number>)JobIdList.getValue();
            for (int j = JobValue.size(); j < step.size(); j++) {
                JobValue.add(j, BigDecimal.ZERO);
            }
            
        }
        List<String> formattedStep = ZZZL0070CommonLogic.getXaxleStep2(cMsg.xxFromDt.getValue(), cMsg.xxToDt.getValue());

        Chart jobTimeChart = ZZZL0070CommonLogic.createNewChart(cMsg, lineVal_JobTm, formattedStep, 0);
        cMsg.setCustomAttribute("JobTmData", jobTimeChart);
        // System.out.println(chart.toDebugString());
        Chart jobCountChart = ZZZL0070CommonLogic.createNewChart(cMsg, lineVal_JobCnt, formattedStep, 2);
        cMsg.setCustomAttribute("JobCntData", jobCountChart);
        // System.out.println(chart.toDebugString());
        return true;
    }

    private boolean getDataForTableChart(ZZZL0070CMsg cMsg) {

        S21SsmEZDResult ssmResult = ZZZL0070Query.getInstance().getTableInfo(cMsg);

        // No Data
        if (!ssmResult.isCodeNormal()) {
            // cMsg.setMessageInfo("ZZZM9005W");
            return false;
        }

        HashMap<String, ArrayList<Number>> lineVal_TblSize = new HashMap<String, ArrayList<Number>>();
        HashMap<String, ArrayList<Number>> lineVal_TblCnt = new HashMap<String, ArrayList<Number>>();
        ArrayList<Number> values = null;
        List<String> step = ZZZL0070CommonLogic.getXaxleStep(cMsg.xxFromDt.getValue(), cMsg.xxToDt.getValue());

        List resultList = (List) ssmResult.getResultObject();

        int preIndx = 0;
        BigDecimal prekBytes = BigDecimal.ZERO;
        BigDecimal prerowCnt = BigDecimal.ZERO;    
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {

            Map resultMap = (Map) iterator.next();
            // String jobId = ZZZL0070CommonLogic.nvl((String)
            // resultMap.get("BAT_PROC_JOB_ID"));
            String tblNm = ZZZL0070CommonLogic.nvl((String) resultMap.get("BAT_TBL_NM"));
            String date = ZZZL0070CommonLogic.nvl((String) resultMap.get("BAT_TBL_EST_DT"));
            BigDecimal kBytes = (BigDecimal) resultMap.get("BAT_TBL_SIZE_NUM");
            BigDecimal rowCnt = (BigDecimal) resultMap.get("BAT_TBL_DATA_CNT");

            if (!lineVal_TblSize.containsKey(tblNm)) {
                values = new ArrayList<Number>();
                lineVal_TblSize.put(tblNm, values);
                preIndx = 0;
                prekBytes = BigDecimal.ZERO;
                
            }
            if (!lineVal_TblCnt.containsKey(tblNm)) {
                values = new ArrayList<Number>();
                lineVal_TblCnt.put(tblNm, values);
                preIndx = 0;
                prerowCnt = BigDecimal.ZERO;
            }

            for (int i = preIndx; i < step.size(); i++) {
                if (step.get(i).equals(date)) {
                    lineVal_TblSize.get(tblNm).add(kBytes);
                    lineVal_TblCnt.get(tblNm).add(rowCnt);
                    preIndx = i + 1;
                    break;
                } else {
                    if (prekBytes.equals(BigDecimal.ZERO)) {
                        lineVal_TblSize.get(tblNm).add(kBytes);
                    } else {
                        lineVal_TblSize.get(tblNm).add(prekBytes);
                    }
                    if (prerowCnt.equals(BigDecimal.ZERO)) {
                        lineVal_TblCnt.get(tblNm).add(rowCnt);
                    } else {
                        lineVal_TblCnt.get(tblNm).add(prerowCnt);
                    }
                }
            }
            prekBytes = kBytes;
            prerowCnt = rowCnt;
        }
        
        
        
        Iterator iterator_size = lineVal_TblSize.entrySet().iterator();
        while (iterator_size.hasNext()) {
            Entry JobIdList = (Entry) iterator_size.next();
            ArrayList<Number> JobValue = (ArrayList<Number>)JobIdList.getValue();
            for (int i = JobValue.size(); i < step.size(); i++) {
                for (int j = JobValue.size(); j < step.size(); j++) {
                    if (ZZZL0070CommonLogic.isBeforeToday(step.get(j))) {
                        JobValue.add(j, (BigDecimal)JobValue.get(JobValue.size() - 1));
                    } else {
                        JobValue.add(j, BigDecimal.ZERO);
                    } 
                }
            }
            
        }
        Iterator iterator_cnt = lineVal_TblCnt.entrySet().iterator();
        while (iterator_cnt.hasNext()) {
            Entry JobIdList = (Entry) iterator_cnt.next();
            ArrayList<Number> JobValue = (ArrayList<Number>)JobIdList.getValue();
            for (int j = JobValue.size(); j < step.size(); j++) {
                if (ZZZL0070CommonLogic.isBeforeToday(step.get(j))) {
                    JobValue.add(j, (BigDecimal)JobValue.get(JobValue.size() - 1));
                } else {
                    JobValue.add(j, BigDecimal.ZERO);
                } 
            }
            
        }
        List<String> formattedStep = ZZZL0070CommonLogic.getXaxleStep2(cMsg.xxFromDt.getValue(), cMsg.xxToDt.getValue());

        Chart jobTimeChart = ZZZL0070CommonLogic.createNewChart(cMsg, lineVal_TblSize, formattedStep, 1);
        cMsg.setCustomAttribute("TableSizeData", jobTimeChart);
        // System.out.println(chart.toDebugString());
        Chart jobCountChart = ZZZL0070CommonLogic.createNewChart(cMsg, lineVal_TblCnt, formattedStep, 3);
        cMsg.setCustomAttribute("TableCntData", jobCountChart);
        // System.out.println(chart.toDebugString());
        return true;
    }

    private void getProgramId(ZZZL0070CMsg cMsg) {

        S21SsmEZDResult ssmResult = ZZZL0070Query.getInstance().getProgramId(cMsg);

        // No Data
        if (!ssmResult.isCodeNormal()) {
            cMsg.batProcJobId.setErrorInfo(1, "ZZZM9006E", new String[] {"Job ID" });
            return;
        }

        List resultList = (List) ssmResult.getResultObject();

        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {

            Map resultMap = (Map) iterator.next();

            String jobId = ZZZL0070CommonLogic.nvl((String) resultMap.get("BAT_PROC_JOB_ID"));
            String pgmId = ZZZL0070CommonLogic.nvl((String) resultMap.get("BAT_PROC_PGM_ID"));

            int n = cMsg.A.getValidCount();
            boolean existFlg = false;
            for (int i = 0; i < n; i++) {
                if (cMsg.A.no(i).batProcJobId_A.getValue().equals(jobId)) {
                    existFlg = true;
                    break;
                }
            }
            if (!existFlg && n < cMsg.A.length()) {
                cMsg.A.no(n).batProcJobId_A.setValue(jobId);
                cMsg.A.no(n).batProcPgmId_A.setValue(pgmId);
                cMsg.A.setValidCount(n + 1);
            }
            
            int max = cMsg.A.length() - 5;
            if (cMsg.A.getValidCount() > max) {
                cMsg.setMessageInfo("ZZZM0005I", new String[] {"Job ID", String.valueOf(cMsg.A.length() - 5)});
                cMsg.A.setValidCount(max);
                for (int i = 0 ; i < cMsg.A.length(); i++) {
                    if (i >= max) {
                        cMsg.A.no(i).clear();
                    }
                }
            }
        }
//        showChart(cMsg);
        return;
    }
    
    private void delJobId(ZZZL0070CMsg cMsg) {
        
        ZZZL0070CMsg bizMsg  = (ZZZL0070CMsg) cMsg;
        ZZZL0070CMsg sortMsg = new ZZZL0070CMsg();

        int cnt = 0;
        for (int i = 0 ; i < bizMsg.A.getValidCount(); i++) {
            if (bizMsg.A.no(i).xxChkBox_A.isClear()) {
                EZDMsg.copy(bizMsg.A.no(i), null, sortMsg.A.no(cnt), null);
                cnt++;
            }
        }
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        sortMsg.A.setValidCount(cnt);
        
        EZDMsg.copy(sortMsg.A, null, bizMsg.A, null);
        
        if (bizMsg.A.getValidCount() > 0) {
            showChart(cMsg);
        }
        return;
    }
}
