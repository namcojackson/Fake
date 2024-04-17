package business.blap.ZZZL0030;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jofc2.model.Chart;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZZL0030.common.ZZZL0030ChartBean;
import business.blap.ZZZL0030.common.ZZZL0030CommonLogic;
import business.blap.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0030BL02 extends S21BusinessHandler implements ZZZL0030Constant {
    
    Chart chart;
    ZZZL0030ChartBean chartDataBean;
    
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            ZZZL0030ChartBean chartDatabeanInstance = (ZZZL0030ChartBean)cMsg.getCustomAttribute("ChartDataBean");
            if (chartDatabeanInstance != null) {
                chartDataBean = chartDatabeanInstance;
            }
            
            if ("ZZZL0030_INIT".equals(screenAplID)) {
                ((ZZZL0030CMsg)cMsg).glblCmpyCd.setValue(getGlobalCompanyCode());
                ZZZL0030CommonLogic.getJvmNm((ZZZL0030CMsg) cMsg);
                ZZZL0030CommonLogic.colorIndex = 0;
            } else if ("ZZZL0030Scrn00_View".equals(screenAplID)) {
                showChart((ZZZL0030CMsg) cMsg, (ZZZL0030SMsg) sMsg);
            } else if ("ZZZL0030Scrn00_Refresh".equals(screenAplID)) {
                refreshChart((ZZZL0030CMsg) cMsg, (ZZZL0030SMsg) sMsg);
            } else if ("ZZZL0030Scrn00_CMN_Reset".equals(screenAplID)) {
                reset((ZZZL0030CMsg) cMsg, (ZZZL0030SMsg) sMsg);
            } else if ("ZZZL0030Scrn00_DelBiz".equals(screenAplID)) {
                removeElement((ZZZL0030CMsg) cMsg, (ZZZL0030SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void showChart(ZZZL0030CMsg cMsg, ZZZL0030SMsg sMsg) {
        
        if (!ZZZL0030CommonLogic.chkGlbCmpCd(cMsg)) {
            return;
        }
        cMsg.delFlg.clear();
        //Gathering data from DB
        int resultData = getData(cMsg, sMsg);
        if (resultData == 0) {
            return;
        }
        
        try {
            // Get Average data
            ZZZL0030ChartBean newchartBean = ZZZL0030CommonLogic.getAverageData(cMsg, sMsg);
            // Add a specified content(BizId or EventId) to the chart instance.
            if (chartDataBean == null) {
                chart = ZZZL0030CommonLogic.createNewChart(cMsg, newchartBean);
                chartDataBean = newchartBean;
            } else {
                chart = ZZZL0030CommonLogic.addElement(chartDataBean, newchartBean, cMsg);
            }          
            cMsg.setCustomAttribute("ChartObj", chart);
            cMsg.setCustomAttribute("ChartDataBean", chartDataBean);
            System.out.println(chart.toDebugString());
            return;
        } catch (ParseException e) {
        }
    }
    
    private void removeElement(ZZZL0030CMsg cMsg, ZZZL0030SMsg sMsg) {
        
        ZZZL0030CMsg tempScrnMsg = (ZZZL0030CMsg)cMsg.clone();
        int cnt = tempScrnMsg.A.getValidCount();
        
        List<String> delElementsList = new ArrayList<String>();
        
        for (int k = 0; k < cnt; k++) {
            if (!tempScrnMsg.A.no(k).xxChkBox_A.isClear()) {
                delElementsList.add(tempScrnMsg.A.no(k).scrAppId_A.getValue());
            }
        }
        
        // Reset detail
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        int j=0;
        for (int i = 0 ; i < cnt ; i++) {
            if (tempScrnMsg.A.no(i).xxChkBox_A.isClear()) {
                cMsg.A.no(j).bizId_A.setValue(tempScrnMsg.A.no(i).bizId_A.getValue());
                cMsg.A.no(j).scrAppId_A.setValue(tempScrnMsg.A.no(i).scrAppId_A.getValue());
                j++;
            }
        }
        cMsg.A.setValidCount(j);
        
        if (cMsg.A.getValidCount() == 0) {
            reset(cMsg, sMsg);
            return;
        }
        
        //Gathering data from DB
        int resultData = getData(cMsg, sMsg);
        if (resultData == 0) {
//            return;
        }
        
        if (chartDataBean != null) {
            try {
                // Get Average data
                ZZZL0030ChartBean newchartBean = ZZZL0030CommonLogic.getAverageData(cMsg, sMsg);
                ZZZL0030CommonLogic.addElement(chartDataBean, newchartBean, cMsg);

            } catch (ParseException e) {
            }
            
            for (String delItem : delElementsList) {
                removeItem(chartDataBean, delItem);
            }
            if (chartDataBean.getLineValues_ProcTm().size() != 0) {
                chart = ZZZL0030CommonLogic.createNewChart(cMsg, chartDataBean);
            } else {
                chartDataBean = null;
            }
            cMsg.setCustomAttribute("ChartObj", chart);
            cMsg.setCustomAttribute("ChartDataBean", chartDataBean);
        }  
    }
    
    private void refreshChart(ZZZL0030CMsg cMsg, ZZZL0030SMsg sMsg) {
        if (chartDataBean != null) {
            chart = ZZZL0030CommonLogic.createNewChart(cMsg, chartDataBean);
        }
        cMsg.setCustomAttribute("ChartObj", chart);
        return;
    }
    
    private void reset(ZZZL0030CMsg cMsg, ZZZL0030SMsg sMsg) {
        cMsg.clearErrorInfo();
        cMsg.jvmNm_S.clear();
        cMsg.opsUsrId.clear();
        cMsg.xxYAxle.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        
        ZZZL0030CommonLogic.colorIndex = 0;
        cMsg.delFlg.setValue("Y");
        cMsg.setCustomAttribute("ChartObj", null);
        cMsg.setCustomAttribute("ChartDataBean", null);
    }
    
    private int getData(ZZZL0030CMsg cMsg, ZZZL0030SMsg sMsg) {
        
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        
        S21SsmEZDResult ssmResult = ZZZL0030Query.getInstance().getData(cMsg);
        
        // No Data
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W");
            return 0;
        }

        int i = 0;
        int max = sMsg.A.length();
        String sMax = String.valueOf(max);
        
        List resultList = (List) ssmResult.getResultObject();
        
        // Set result to SMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            // MAX OVER
            if (i >= max) {
                cMsg.setMessageInfo("ZZZM9002W", new String[] {sMax});
                break;
            }
            Map resultMap = (Map) iterator.next();
            String glblCmpyCd = ZZZL0030CommonLogic.nvl((String) resultMap.get("GLBL_CMPY_CD"));
            String opsUsrId = ZZZL0030CommonLogic.nvl((String) resultMap.get("OPS_USR_ID"));
            String jvm = ZZZL0030CommonLogic.nvl((String) resultMap.get("JVM_NM"));
            String opsTm = ZZZL0030CommonLogic.nvl((String) resultMap.get("OPS_TS"));
            String bizId = ZZZL0030CommonLogic.nvl((String) resultMap.get("BIZ_ID"));
            String eventId = ZZZL0030CommonLogic.nvl((String) resultMap.get("SCR_APP_ID"));
            String bizElpsTm = ZZZL0030CommonLogic.nvl((String) resultMap.get("BIZ_ELPS_TM_TXT"));
            BigDecimal sMsgByte = (BigDecimal) resultMap.get("GLBL_AREA_DATA_CNT");
            
            sMsg.A.no(i).glblCmpyCd_A.setValue(glblCmpyCd);
            sMsg.A.no(i).opsUsrId_A.setValue(opsUsrId);
            sMsg.A.no(i).jvmNm_A.setValue(jvm);
            sMsg.A.no(i).opsTs_A.setValue(opsTm);
            sMsg.A.no(i).bizId_A.setValue(bizId);
            sMsg.A.no(i).scrAppId_A.setValue(eventId);
            sMsg.A.no(i).bizElpsTmTxt_A.setValue(bizElpsTm);
            sMsg.A.no(i).glblAreaDataCnt_A.setValue(sMsgByte);
            i++;
        }
        sMsg.A.setValidCount(i);
        return i;
    }
    
    private void removeItem (ZZZL0030ChartBean data, String targetItem) {
        
        Pattern pattern = Pattern.compile("(" +  "^" + targetItem + ")(.*)");
        Iterator<String> iterator = new HashSet(data.getLineValues_ProcTm().keySet()).iterator();
        
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                data.getLineValues_ProcTm().remove(key);
                data.getLineValues_GlblSize().remove(key);
                data.getLineValues_Throughput().remove(key);
            }
        }        
    }
}
