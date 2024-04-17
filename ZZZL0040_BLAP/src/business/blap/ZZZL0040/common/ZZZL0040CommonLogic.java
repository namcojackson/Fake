package business.blap.ZZZL0040.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZZL0040.ZZZL0040CMsg;
import business.blap.ZZZL0040.ZZZL0040Query;
import business.blap.ZZZL0040.ZZZL0040SMsg;
import business.blap.ZZZL0040.constant.ZZZL0040Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public class ZZZL0040CommonLogic implements ZZZL0040Constant {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    public static synchronized String getDateforCsvFile(String date) {
        
        String convertDate = "";
        
        if (S21StringUtil.isNotEmpty(date)) {
            Date tempDate;
            try {
                tempDate = dateFormat.parse(date);
                convertDate = dateFormat2.format(tempDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
        return convertDate;
    }

    public static void getAvgData (ZZZL0040CMsg cMsg, ZZZL0040SMsg data) {
        // Initialinzing CMsg.A
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        
        int totalCnt = data.A.getValidCount();
        int i = 0;
        
        Map<String, ArrayList<String>> bizIdBean = new HashMap<String, ArrayList<String>>();
        ArrayList<String> scrEvenIdList = null;
        
        Map<String, Double> avgBizProcTm = new HashMap<String, Double>(); 
        Map<String, Double> avgBizThroughput = new HashMap<String, Double>();
        Map<String, Double> avgGlblAreaSize = new HashMap<String, Double>();
        
        Map<String, Double> stdevBizProcTm = new HashMap<String, Double>();
        Map<String, Double> stdevGlblAreaSize= new HashMap<String, Double>();
        
        Map<String, Integer> avgBizProcTm_Cnt = new HashMap<String, Integer>(); 
        Map<String, Integer> avgBizThroughput_Cnt = new HashMap<String, Integer>();
        Map<String, Integer> avgGlblAreaSize_Cnt = new HashMap<String, Integer>();
        
        ArrayList<Double> bizProcTmSum = new ArrayList<Double>();
        ArrayList<Double> bizThroughputSum = new ArrayList<Double>();
        ArrayList<Double> glblAreaSizeSum = new ArrayList<Double>();
                
        String bizId = data.A.no(0).bizId_A.getValue();
        String scrnEventId = data.A.no(0).scrAppId_A.getValue();
        scrEvenIdList = new ArrayList<String>();
        bizIdBean.put(data.A.no(0).bizId_A.getValue(), scrEvenIdList);
        scrEvenIdList.add(scrnEventId);
        
        for (i = 0; i < totalCnt; i++) {

            double bizProcTm = Double.parseDouble(nullToZero(data.A.no(i).bizElpsTmTxt_A.getValue()));
            double glblAreaSize = Double.parseDouble(data.A.no(i).glblAreaDataCnt_A.getValue().toString());
            
            if (!data.A.no(i).bizId_A.getValue().equals(bizId)) {
                scrEvenIdList = new ArrayList<String>();
                bizIdBean.put(data.A.no(i).bizId_A.getValue(), scrEvenIdList);
            }
            
            if (data.A.no(i).scrAppId_A.getValue().equals(scrnEventId)) {
                bizProcTmSum.add(bizProcTm/1000000D);
                if (glblAreaSize != 0 && bizProcTm != 0) {
                    bizThroughputSum.add((glblAreaSize/bizProcTm)*1000000000D);
                }
                if (glblAreaSize != 0) {
                    glblAreaSizeSum.add(glblAreaSize);
                }
            } else {
                avgBizProcTm.put(scrnEventId, getAvgBySum(bizProcTmSum));
                avgBizThroughput.put(scrnEventId, getAvgBySum(bizThroughputSum));
                avgGlblAreaSize.put(scrnEventId, getAvgBySum(glblAreaSizeSum));
                avgBizProcTm_Cnt.put(scrnEventId, bizProcTmSum.size());
                avgBizThroughput_Cnt.put(scrnEventId, bizThroughputSum.size());
                avgGlblAreaSize_Cnt.put(scrnEventId, glblAreaSizeSum.size());
                stdevBizProcTm.put(scrnEventId, standardDeviation(bizProcTmSum));
                stdevGlblAreaSize.put(scrnEventId, standardDeviation(glblAreaSizeSum));
                scrEvenIdList.add(data.A.no(i).scrAppId_A.getValue());
                
                bizProcTmSum.clear();
                bizThroughputSum.clear();
                glblAreaSizeSum.clear();
                
                bizProcTmSum.add(bizProcTm/1000000D);
                if (glblAreaSize != 0 && bizProcTm != 0) {
                    bizThroughputSum.add((glblAreaSize/bizProcTm)*1000000000D);
                }
                if (glblAreaSize != 0) {
                    glblAreaSizeSum.add(glblAreaSize);
                }
            }
            bizId = data.A.no(i).bizId_A.getValue();
            scrnEventId = data.A.no(i).scrAppId_A.getValue();
            
            if (i == totalCnt-1) {
                avgBizProcTm.put(scrnEventId, getAvgBySum(bizProcTmSum));
                avgBizThroughput.put(scrnEventId, getAvgBySum(bizThroughputSum));
                avgGlblAreaSize.put(scrnEventId, getAvgBySum(glblAreaSizeSum));
                avgBizProcTm_Cnt.put(scrnEventId, bizProcTmSum.size());
                avgBizThroughput_Cnt.put(scrnEventId, bizThroughputSum.size());
                avgGlblAreaSize_Cnt.put(scrnEventId, glblAreaSizeSum.size());
                stdevBizProcTm.put(scrnEventId, standardDeviation(bizProcTmSum));
                stdevGlblAreaSize.put(scrnEventId, standardDeviation(glblAreaSizeSum));
            }
        }
        
        // Sorting
        TreeMap<String,Double> sorted_map = null;
        ValueComparatorDesc descComp = null;
        ValueComparatorAsc ascComp = null;
        
        int n = 0;
        int maxRank = 100;
        
        if (cMsg.jvmNm_S.isClear()) {
            cMsg.jvmNm_S.setValue("All");
        }
        // Sorting AvgData.
        switch (cMsg.xxRadioBtn.getValueInt()) {
            case BIZ_PROC_TIME_RANK:
                descComp =  new ValueComparatorDesc(avgBizProcTm); 
                sorted_map = new TreeMap(descComp);
                sorted_map.putAll(avgBizProcTm);
                // Mapping cMsg
                for (String key : sorted_map.keySet()) {
                    if (maxRank <= n){
                        break;
                    }
                    if (avgBizProcTm.get(key) != 0) {
                        cMsg.A.no(n).xxRowNum_A.setValue(n+1);
                        cMsg.A.no(n).glblCmpyCd_A.setValue(cMsg.glblCmpyCd.getValue());
                        cMsg.A.no(n).jvmNm_A.setValue(cMsg.jvmNm_S.getValue());
                        cMsg.A.no(n).bizId_A.setValue(getKeysFromValue(bizIdBean,key));
                        cMsg.A.no(n).scrAppId_A.setValue(key);
                        cMsg.A.no(n).xxBizProcAvgTmTxt_MS.setValue(round((avgBizProcTm.get(key)), 2));
                        cMsg.A.no(n).xxBizThrupTxt_A.setValue(round(avgBizThroughput.get(key), 2));
                        cMsg.A.no(n).xxGlblAreaAvgSizeTxt_A.setValue(round(avgGlblAreaSize.get(key), 0));
                        cMsg.A.no(n).xxBizProcAvgTmTxt_SD.setValue(round((stdevBizProcTm.get(key)), 2));
                        cMsg.A.no(n).xxGlblAreaAvgSizeStdevTxt_SD.setValue(round(stdevGlblAreaSize.get(key), 2));
                        cMsg.A.no(n).xxTotCnt_A.setValue(avgBizProcTm_Cnt.get(key));
                        
                        n++; 
                    }
                }
                cMsg.A.setValidCount(n);
                break;
            case BIZ_THROUGHPUT_RANK:
                ascComp =  new ValueComparatorAsc(avgBizThroughput); 
                sorted_map = new TreeMap(ascComp);
                sorted_map.putAll(avgBizThroughput);
                // Mapping cMsg
                for (String key : sorted_map.keySet()) {
                    if (maxRank <= n){
                        break;
                    }
                    if (avgBizThroughput.get(key) != 0) {
                        cMsg.A.no(n).xxRowNum_A.setValue(n+1);
                        cMsg.A.no(n).glblCmpyCd_A.setValue(cMsg.glblCmpyCd.getValue());
                        cMsg.A.no(n).jvmNm_A.setValue(cMsg.jvmNm_S.getValue());
                        cMsg.A.no(n).bizId_A.setValue(getKeysFromValue(bizIdBean,key));
                        cMsg.A.no(n).scrAppId_A.setValue(key);
                        cMsg.A.no(n).xxBizProcAvgTmTxt_MS.setValue(round(avgBizProcTm.get(key), 2));
                        cMsg.A.no(n).xxBizThrupTxt_A.setValue(round((avgBizThroughput.get(key)), 2));
                        cMsg.A.no(n).xxGlblAreaAvgSizeTxt_A.setValue(round(avgGlblAreaSize.get(key), 0));
                        cMsg.A.no(n).xxBizProcAvgTmTxt_SD.setValue(round((stdevBizProcTm.get(key)), 2));
                        cMsg.A.no(n).xxGlblAreaAvgSizeStdevTxt_SD.setValue(round(stdevGlblAreaSize.get(key), 2));
                        cMsg.A.no(n).xxTotCnt_A.setValue(avgBizProcTm_Cnt.get(key));
                        n++; 
                    }
                }
                cMsg.A.setValidCount(n);
                break;
            case GLBL_AREA_SIZE_RANK:
                descComp =  new ValueComparatorDesc(avgGlblAreaSize); 
                sorted_map = new TreeMap(descComp);
                sorted_map.putAll(avgGlblAreaSize);
                // Mapping cMsg
                for (String key : sorted_map.keySet()) {
                    if (maxRank <= n){
                        break;
                    }
                    if (avgGlblAreaSize.get(key) != 0) {
                        cMsg.A.no(n).xxRowNum_A.setValue(n+1);
                        cMsg.A.no(n).glblCmpyCd_A.setValue(cMsg.glblCmpyCd.getValue());
                        cMsg.A.no(n).jvmNm_A.setValue(cMsg.jvmNm_S.getValue());
                        cMsg.A.no(n).bizId_A.setValue(getKeysFromValue(bizIdBean,key));
                        cMsg.A.no(n).scrAppId_A.setValue(key);
                        cMsg.A.no(n).xxBizProcAvgTmTxt_MS.setValue(round(avgBizProcTm.get(key), 2));
                        cMsg.A.no(n).xxBizThrupTxt_A.setValue(round(avgBizThroughput.get(key), 2));
                        cMsg.A.no(n).xxGlblAreaAvgSizeTxt_A.setValue(round(avgGlblAreaSize.get(key), 0));
                        cMsg.A.no(n).xxBizProcAvgTmTxt_SD.setValue(round((stdevBizProcTm.get(key)), 2));
                        cMsg.A.no(n).xxGlblAreaAvgSizeStdevTxt_SD.setValue(round(stdevGlblAreaSize.get(key), 2));
                        cMsg.A.no(n).xxTotCnt_A.setValue(avgBizProcTm_Cnt.get(key));
                        n++;
                    }
                }
                cMsg.A.setValidCount(n);
                break;
        }
        return;
    }

    public static double standardDeviation(ArrayList<Double> list) {

        double sum = 0.0;
        double sd = 0.0;
        double diff;
        double avrgValue = getAvgBySum(list);
        
        if (list.size() <= 1) {
            return sd;
        }

        for (int i = 0; i < list.size(); i++) {
          diff = list.get(i) - avrgValue;
          sum += Math.pow(diff, 2);
        }
        sd = Math.sqrt(sum / (list.size() - 1));

        return sd;
      }
    
    public static double getAvgBySum (ArrayList<Double> list) {
        double sum = 0.0D;
        
        if (list.size() == 0) {
            return sum;
        }
        
        for(double data:list) {
            sum += data;
        }       

        return sum/list.size();
    }
    
    public static double maxVal(ArrayList<Number> n) {
        double max = n.get(0).doubleValue();

        for (int i = 1; i < n.size(); i++)
            if (n.get(i).doubleValue() > max) {
                max = n.get(i).doubleValue();
            }
        return max;
    }

    public static double minVal(ArrayList<Number> n) {
        double min = n.get(0).doubleValue();

        for (int i = 1; i < n.size(); i++)
            if (n.get(i).doubleValue() < min) {
                min = n.get(i).doubleValue();
            }
        return min;
    }
    
    public static String nullToZero (String val) {
        
        if (val == null || val.equals("")) {
            return "0.0";
        } 
        return val;
    }
    
    /**
     * Method name: nvl
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    public static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    
    public static BigDecimal round(double val, int digit) {
        String pattern = "";
        if (digit == 0) {
            pattern = "0";
        } else if (digit == 1) {
            pattern = "0.#";
        } else if (digit == 2) {
            pattern = "0.##";
        } else if (digit == 3) {
            pattern = "0.###";
        }
        DecimalFormat df =  new DecimalFormat(pattern);
        return new BigDecimal(df.format(val));
    }

    /**
     * Method name: chkGlbCmpCd
     * <dd>The method explanation: Search GLBL_CMPY table by Primary
     * Key
     * @param cMsg ZZPL0090CMsg
     * @return boolean true or false
     */
    public static boolean chkGlbCmpCd(ZZZL0040CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code" });
            return false;
        }
        return true;
    }
    
    public static void doClearMsgCarrior(ZZZL0040CMsg cMsg, ZZZL0040SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.jvmNm_S.clear();
    }
    
    public static String getKeysFromValue(Map<?, ?> hm, Object value){

        for(Object o:hm.keySet()){
            ArrayList<String> k = (ArrayList<String>)hm.get(o);
            if(k.contains(value)) {
                return o.toString();
            }
        }
        return "";
    }

    public static void getJvmNm(ZZZL0040CMsg cMsg) {
        
        S21SsmEZDResult ssmResult = ZZZL0040Query.getInstance().getJvmNm(cMsg);
        
        // Set default value.
        cMsg.jvmNm_C.no(0).setValue("All");
        cMsg.jvmNm_D.no(0).setValue("All");

        int i = 0;
        List resultList = (List) ssmResult.getResultObject();
        
        // Set jvm name to cMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            i++;
            Map resultMap = (Map) iterator.next();
            String jvm = ZZZL0040CommonLogic.nvl((String) resultMap.get("JVM_NM"));
            cMsg.jvmNm_C.no(i).setValue(jvm);
            cMsg.jvmNm_D.no(i).setValue(jvm);
        }
        return;
    }
    
    private static class ValueComparatorDesc implements Comparator {

        Map base;

        public ValueComparatorDesc(Map base) {
            this.base = base;
        }

        public int compare(Object a, Object b) {

            if ((Double) base.get(a) < (Double) base.get(b)) {
                return 1;
            } else if ((Double) base.get(a) == (Double) base.get(b)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private static class ValueComparatorAsc implements Comparator {

        Map base;

        public ValueComparatorAsc(Map base) {
            this.base = base;
        }

        public int compare(Object a, Object b) {

            if ((Double) base.get(a) < (Double) base.get(b)) {
                return -1;
            } else if ((Double) base.get(a) == (Double) base.get(b)) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
