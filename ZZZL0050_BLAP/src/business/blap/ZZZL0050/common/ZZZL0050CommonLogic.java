package business.blap.ZZZL0050.common;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZZL0050.ZZZL0050CMsg;
import business.blap.ZZZL0050.ZZZL0050Query;
import business.blap.ZZZL0050.constant.ZZZL0050Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public class ZZZL0050CommonLogic implements ZZZL0050Constant {

    /**
     * @param cMsg EZDCMsg
     */
    public static void search(ZZZL0050CMsg cMsg) {
        
        cMsg.curPldQueueNum.clear();
        cMsg.maxQueueNum.clear();
        cMsg.plngIntvlScd.clear();
        cMsg.startThrdNum.clear();
        cMsg.ezUpTime.clear();
        cMsg.ezUpTimeZone.clear();

        S21SsmEZDResult ssmResult = ZZZL0050Query.getInstance().getData(cMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W", null);
            return;
        }

        List resultList = (List) ssmResult.getResultObject();
        
        // Set result to CMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Map resultMap = (Map) iterator.next();
            String glblCmpyCd = ZZZL0050CommonLogic.nvl((String) resultMap.get("GLBL_CMPY_CD"));
            String jvm = ZZZL0050CommonLogic.nvl((String) resultMap.get("JVM_NM"));
            BigDecimal curPldQueNum = (BigDecimal) resultMap.get("CUR_PLD_QUEUE_NUM");
            BigDecimal startThreadNum = (BigDecimal) resultMap.get("START_THRD_NUM");
            String poolingInterval = ZZZL0050CommonLogic.nvl((String) resultMap.get("PLNG_INTVL_SCD"));
            String maxQueNum = ZZZL0050CommonLogic.nvl((String) resultMap.get("MAX_QUEUE_NUM"));
            String activeFlg = ZZZL0050CommonLogic.nvl((String) resultMap.get("ONL_PROC_ACTV_FLG"));
            String upTime = ZZZL0050CommonLogic.nvl((String) resultMap.get("EZUPTIME"));
            String upTimeZone = ZZZL0050CommonLogic.nvl((String) resultMap.get("EZUPTIMEZONE"));
            
            cMsg.glblCmpyCd.setValue(glblCmpyCd);
            cMsg.jvmNm_S.setValue(jvm);
            cMsg.curPldQueueNum.setValue(curPldQueNum);
            cMsg.startThrdNum.setValue(startThreadNum);
            cMsg.plngIntvlScd.setValue(poolingInterval);
            cMsg.maxQueueNum.setValue(maxQueNum);
            cMsg.onlProcActvFlg_S.setValue(activeFlg);
            cMsg.ezUpTime.setValue(upTime);
            cMsg.ezUpTimeZone.setValue(upTimeZone);
        }
        return;
    }
    
    public static void getJvmNm(ZZZL0050CMsg cMsg) {
        
        S21SsmEZDResult ssmResult = ZZZL0050Query.getInstance().getJvmNm(cMsg);
        
        int i = 0;
        List resultList = (List) ssmResult.getResultObject();
        
        // Set jvm name to cMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Map resultMap = (Map) iterator.next();
            String jvm = ZZZL0050CommonLogic.nvl((String) resultMap.get("JVM_NM"));
            cMsg.jvmNm_C.no(i).setValue(jvm);
            cMsg.jvmNm_D.no(i).setValue(jvm);
            i++;
        }
        if (i >= 0) {
            cMsg.jvmNm_S.setValue(cMsg.jvmNm_D.no(0).getValue());
        }
        return;
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
    
    /**
     * Setting data to all pulldowns for initializing.
     * @param handler EZDCommonHandler
     */
    public static void initPullDowns (ZZZL0050CMsg cMsg) {
        // JVM list
        for (int i =0; i < JVM.length; i++) {
            cMsg.jvmNm_C.no(i).setValue(JVM[i]);
            cMsg.jvmNm_D.no(i).setValue(JVM[i]);
        }
        // Active flag
        cMsg.onlProcActvFlg_C.no(0).setValue(OFF);
        cMsg.onlProcActvFlg_D.no(0).setValue(OFF);
        cMsg.onlProcActvFlg_C.no(1).setValue(ON);
        cMsg.onlProcActvFlg_D.no(1).setValue(ON);
    }

    /**
     * Method name: chkGlbCmpCd
     * <dd>The method explanation: Search GLBL_CMPY table by Primary
     * Key
     * @param cMsg ZZPL0090CMsg
     * @return boolean true or false
     */
    public static boolean chkGlbCmpCd(ZZZL0050CMsg cMsg) {

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
}
