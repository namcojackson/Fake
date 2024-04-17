package business.blap.ZZZL0040;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZZL0040.common.ZZZL0040CommonLogic;
import business.blap.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0040BL02 extends S21BusinessHandler implements ZZZL0040Constant {
    


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            if("ZZZL0040_INIT".equals(screenAplID)) {
                ((ZZZL0040CMsg)cMsg).glblCmpyCd.setValue(getGlobalCompanyCode());
                ZZZL0040CommonLogic.getJvmNm((ZZZL0040CMsg) cMsg);
            } else if ("ZZZL0040Scrn00_View".equals(screenAplID)) {
                do_ZZZL0040Scrn00_View((ZZZL0040CMsg) cMsg, (ZZZL0040SMsg) sMsg);
            } else if ("ZZZL0040Scrn00_CMN_Reset".equals(screenAplID)) {
                ZZZL0040CommonLogic.doClearMsgCarrior((ZZZL0040CMsg) cMsg, (ZZZL0040SMsg) sMsg);
            } else if ("ZZZL0040Scrn00_CMN_Download".equals(screenAplID)) {
                do_ZZZL0040Scrn00_CMN_Download((ZZZL0040CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void do_ZZZL0040Scrn00_View(ZZZL0040CMsg cMsg, ZZZL0040SMsg sMsg) {
        
        // Gathering data from DB.
        getData(cMsg);
        return;
    }
    
    private void do_ZZZL0040Scrn00_CMN_Download(ZZZL0040CMsg cMsg) {
        
        ZZZL0040Query.getInstance().createHistoryReport(cMsg);
        return;
    }
    
//    private int getDataForAvrg(ZZZL0040CMsg cMsg, ZZZL0040SMsg sMsg) {
//        
//        cMsg.A.clear();
//        cMsg.A.setValidCount(0);
//        sMsg.A.clear();
//        sMsg.A.setValidCount(0);
//        
//        S21SsmEZDResult ssmResult = ZZZL0040Query.getInstance().getDataForAvrg(cMsg);
//        
//        // No Data
//        if (!ssmResult.isCodeNormal()) {
//            cMsg.setMessageInfo("ZZZM9005W");
//            return 0;
//        }
//
//        int i = 0;
//        int max = sMsg.A.length();
//        String sMax = String.valueOf(max);
//
//        List resultList = (List) ssmResult.getResultObject();
//        
//        // Set result to SMsg
//        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
//            // MAX OVER
//            if (i >= max) {
//                cMsg.setMessageInfo("ZZZM9002W", new String[] {sMax});
//                break;
//            }
//            Map resultMap = (Map) iterator.next();
//            String glblCmpyCd = ZZZL0040CommonLogic.nvl((String) resultMap.get("GLBL_CMPY_CD"));
//            String jvm = ZZZL0040CommonLogic.nvl((String) resultMap.get("JVM_NM"));
//            String bizId = ZZZL0040CommonLogic.nvl((String) resultMap.get("BIZ_ID"));
//            String eventId = ZZZL0040CommonLogic.nvl((String) resultMap.get("SCR_APP_ID"));
//            String bizElpsTm = ZZZL0040CommonLogic.nvl((String) resultMap.get("BIZ_ELPS_TM_TXT"));
//            BigDecimal sMsgByte = (BigDecimal) resultMap.get("GLBL_AREA_DATA_CNT");
//            
//            sMsg.A.no(i).glblCmpyCd_A.setValue(glblCmpyCd);
//            sMsg.A.no(i).jvmNm_A.setValue(jvm);
//            sMsg.A.no(i).bizId_A.setValue(bizId);
//            sMsg.A.no(i).scrAppId_A.setValue(eventId);
//            sMsg.A.no(i).bizElpsTmTxt_A.setValue(bizElpsTm);
//            sMsg.A.no(i).glblAreaDataCnt_A.setValue(sMsgByte);
//            i++;
//        }
//        sMsg.A.setValidCount(i);
//        return i;
//    }

    private void getData (ZZZL0040CMsg cMsg) {
        
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        
        S21SsmEZDResult ssmResult = null;
        boolean avrgFlg = false;
        
        // Get Average data.
        if (cMsg.xxChkBox.getValue().equals("Y")) {
            ssmResult = ZZZL0040Query.getInstance().getDataForAvrg(cMsg);
            avrgFlg = true;
          // Get Unit data.
        } else {
            ssmResult = ZZZL0040Query.getInstance().getDataForUnit(cMsg);
        }


        // No Data
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W");
            return;
        }

        int i = 0;
        BigDecimal max = new BigDecimal("100");

        List resultList = (List) ssmResult.getResultObject();

        // Set result to SMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {

            Map resultMap = (Map) iterator.next();
            BigDecimal rank = (BigDecimal) resultMap.get("RANK");
            String glblCmpyCd = ZZZL0040CommonLogic.nvl((String) resultMap.get("GLBL_CMPY_CD"));
            String jvm = "";
            String opsUsrId = "N/A";
            if (!avrgFlg) {
                jvm = ZZZL0040CommonLogic.nvl((String) resultMap.get("JVM_NM"));
                opsUsrId = ZZZL0040CommonLogic.nvl((String) resultMap.get("OPS_USR_ID"));
            }
            String bizId = ZZZL0040CommonLogic.nvl((String) resultMap.get("BIZ_ID"));
            String eventId = ZZZL0040CommonLogic.nvl((String) resultMap.get("SCR_APP_ID"));
            BigDecimal bizElpsTm = (BigDecimal) resultMap.get("BIZ_ELPS_TM_TXT");
            BigDecimal throughtput = (BigDecimal) resultMap.get("TROUGHPUT");
            BigDecimal sMsgByte = (BigDecimal) resultMap.get("GLBL_AREA_DATA_CNT");
            BigDecimal sampleCnt = (BigDecimal) resultMap.get("SAMPLE_CNT");
            BigDecimal StdDev_bizElpsTm = (BigDecimal) resultMap.get("STDEV_BIZTM");
            BigDecimal StdDev_sMsgByte = (BigDecimal) resultMap.get("STDEV_SMSGSIZE");

            // MAX Rank Over
            if (rank.compareTo(max) > 0) {
                break;
            }

            cMsg.A.no(i).xxRowNum_A.setValue(rank);
            cMsg.A.no(i).glblCmpyCd_A.setValue(glblCmpyCd);
            if (avrgFlg) {
                if (cMsg.jvmNm_S.isClear()) {
                    cMsg.jvmNm_S.setValue("All");
                }
                cMsg.A.no(i).jvmNm_A.setValue(cMsg.jvmNm_S.getValue());
            } else {
                cMsg.A.no(i).jvmNm_A.setValue(jvm);
            }
            cMsg.A.no(i).opsUsrId_A.setValue(opsUsrId);
            cMsg.A.no(i).bizId_A.setValue(bizId);
            cMsg.A.no(i).scrAppId_A.setValue(eventId);
            cMsg.A.no(i).xxBizProcAvgTmTxt_MS.setValue(bizElpsTm);
            cMsg.A.no(i).xxBizThrupTxt_A.setValue(throughtput);
            cMsg.A.no(i).xxGlblAreaAvgSizeTxt_A.setValue(sMsgByte);
            cMsg.A.no(i).xxTotCnt_A.setValue(sampleCnt);
            cMsg.A.no(i).xxBizProcAvgTmTxt_SD.setValue(StdDev_bizElpsTm);
            cMsg.A.no(i).xxGlblAreaAvgSizeStdevTxt_SD.setValue(StdDev_sMsgByte);
            i++;
        }
        cMsg.A.setValidCount(i);
        return;
    }
}
