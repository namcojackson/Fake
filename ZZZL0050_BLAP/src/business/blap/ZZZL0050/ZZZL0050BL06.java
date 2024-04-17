package business.blap.ZZZL0050;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZZL0050.common.ZZZL0050CommonLogic;
import business.blap.ZZZL0050.constant.ZZZL0050Constant;
import business.db.ONL_PROC_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0050BL06 extends S21BusinessHandler implements ZZZL0050Constant {
    


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZZL0050Scrn00_CMN_Submit".equals(screenAplID)) {
                do_ZZZL0050Scrn00_Submit((ZZZL0050CMsg) cMsg);
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
    
    private void do_ZZZL0050Scrn00_Submit(ZZZL0050CMsg cMsg) {
        ONL_PROC_CONFIGTMsg targetTMsg = new ONL_PROC_CONFIGTMsg();
        targetTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        targetTMsg.jvmNm.setValue(cMsg.jvmNm_S.getValue());
        ONL_PROC_CONFIGTMsg checkTMsg = (ONL_PROC_CONFIGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(targetTMsg);
        
        targetTMsg.plngIntvlScd.setValue(cMsg.plngIntvlScd.getValue());
        targetTMsg.startThrdNum.setValue(cMsg.startThrdNum.getValue());
        targetTMsg.maxQueueNum.setValue(cMsg.maxQueueNum.getValue());
        targetTMsg.onlProcActvFlg.setValue(cMsg.onlProcActvFlg_S.getValue());
        
        if (checkTMsg == null) {
            // In case of New record
            EZDTBLAccessor.create(targetTMsg);
            
            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Create"});
            } else {
                cMsg.setMessageInfo("ZZZM9012E", new String[] {targetTMsg.getReturnCode()});
                return;
            }

        } else {
            String timeBefore     = cMsg.ezUpTime.getValue();
            String timeZoneBefore = cMsg.ezUpTimeZone.getValue();
            String time     = checkTMsg.ezUpTime.getValue();
            String timeZone = checkTMsg.ezUpTimeZone.getValue();
            if(!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }
            // In case of update
            EZDTBLAccessor.update(targetTMsg);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(targetTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
            } else {
                cMsg.setMessageInfo("ZZZM9013E", new String[] {targetTMsg.getReturnCode()});
                return;
            }
        }
        ZZZL0050CommonLogic.search(cMsg);
        return;
    }
}
