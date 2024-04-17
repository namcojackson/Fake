package business.blap.ZZZL0060;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZZL0060.common.ZZZL0060CommonLogic;
import business.blap.ZZZL0060.constant.ZZZL0060Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0060BL02 extends S21BusinessHandler implements ZZZL0060Constant {
    


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZZL0060Scrn00_Search".equals(screenAplID)
                    || "ZZZL0060Scrn00_Add".equals(screenAplID)
                    || "ZZZL0060Scrn00_CMN_Submit".equals(screenAplID)
                    || "ZZZL0060Scrn00_CMN_Reset".equals(screenAplID)) {
                do_ZZZL0060Scrn00_Search((ZZZL0060CMsg) cMsg);
                
            } else if("ZZZL0060Scrn00_CMN_Delete".equals(screenAplID)) {
                
            }else{
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void do_ZZZL0060Scrn00_Search(ZZZL0060CMsg cMsg) {
        
        if (!ZZZL0060CommonLogic.chkGlbCmpCd(cMsg)) {
            return;  
        }
        searchTableList(cMsg);
        return;
    }
    
    /**
     * @param cMsg EZDCMsg
     */
    private void searchTableList(ZZZL0060CMsg cMsg) {
        
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = ZZZL0060Query.getInstance().getTableList(cMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9005W", null);
            return;
        }

        List resultList = (List) ssmResult.getResultObject();
        
        int i = 0;
        // Set result to CMsg
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Map resultMap = (Map) iterator.next();
            String jobId = ZZZL0060CommonLogic.nvl((String) resultMap.get("BAT_PROC_JOB_ID"));
            String tblNm = ZZZL0060CommonLogic.nvl((String) resultMap.get("BAT_TBL_NM"));
            String upTime = ZZZL0060CommonLogic.nvl((String) resultMap.get("EZUPTIME"));
            String upTimeZone = ZZZL0060CommonLogic.nvl((String) resultMap.get("EZUPTIMEZONE"));
            
            cMsg.A.no(i).batProcJobId_A.setValue(jobId);
            cMsg.A.no(i).batTblNm_A.setValue(tblNm);
            cMsg.A.no(i).ezUpTime_A.setValue(upTime);
            cMsg.A.no(i).ezUpTimeZone_A.setValue(upTimeZone);
            
            i++;
        }
        cMsg.A.setValidCount(i);
        return;
    }
}
