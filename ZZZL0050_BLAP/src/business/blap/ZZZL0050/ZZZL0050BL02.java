package business.blap.ZZZL0050;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZZL0050.common.ZZZL0050CommonLogic;
import business.blap.ZZZL0050.constant.ZZZL0050Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0050BL02 extends S21BusinessHandler implements ZZZL0050Constant {
    


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZZL0050Scrn00_View".equals(screenAplID)) {
                do_ZZZL0050Scrn00_View((ZZZL0050CMsg) cMsg);
            } else if ("ZZZL0050_INIT".equals(screenAplID)) {
                ((ZZZL0050CMsg)cMsg).glblCmpyCd.setValue(getGlobalCompanyCode());
                ZZZL0050CommonLogic.getJvmNm((ZZZL0050CMsg) cMsg);
                if (((ZZZL0050CMsg) cMsg).jvmNm_S.getValue() != null && ((ZZZL0050CMsg) cMsg).jvmNm_S.getValue() != "") {
                    do_ZZZL0050Scrn00_View((ZZZL0050CMsg) cMsg);
                }
            } else if ("ZZZL0050Scrn00_CMN_Reset".equals(screenAplID)) {
                do_ZZZL0050Scrn00_View((ZZZL0050CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
//            ZZZL0050CommonLogic.initPullDowns((ZZZL0050CMsg)cMsg);
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void do_ZZZL0050Scrn00_View(ZZZL0050CMsg cMsg) {
        
        if (!ZZZL0050CommonLogic.chkGlbCmpCd(cMsg)) {
            return;  
        }
        ZZZL0050CommonLogic.search(cMsg);
        return;
    }
}
