package business.blap.ZZZL0060;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZZL0060.common.ZZZL0060CommonLogic;
import business.blap.ZZZL0060.constant.ZZZL0060Constant;
import business.db.BAT_TBL_RELTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0060BL06 extends S21BusinessHandler implements ZZZL0060Constant {
    


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZZL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                do_ZZZL0060Scrn00_Submit((ZZZL0060CMsg) cMsg);
            } else if ("ZZZL0060Scrn00_CMN_Delete".equals(screenAplID)) {
                do_ZZZL0060Scrn00_Delete((ZZZL0060CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
//            ZZZL0060CommonLogic.initPullDowns((ZZZL0060CMsg)cMsg);
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void do_ZZZL0060Scrn00_Submit(ZZZL0060CMsg cMsg) {
        
        BAT_TBL_RELTMsg batTblRelTMsg = new BAT_TBL_RELTMsg();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZZZL0060CommonLogic.isEmpty(cMsg.A.no(i).ezUpTime_A.getValue())) {
                batTblRelTMsg.clear();
                batTblRelTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                batTblRelTMsg.batProcJobId.setValue(cMsg.A.no(i).batProcJobId_A.getValue());
                batTblRelTMsg.batTblNm.setValue(cMsg.A.no(i).batTblNm_A.getValue());
                                        
                EZDTBLAccessor.create(batTblRelTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(batTblRelTMsg.getReturnCode())) {
                    if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(batTblRelTMsg.getReturnCode())){
                        cMsg.A.no(i).batTblNm_A.setErrorInfo(1, "ZZZM9015E", new String[] {batTblRelTMsg.getReturnCode()});
                        cMsg.setMessageInfo("ZZZM9015E");
                        return;
                    }
                    
                    cMsg.A.no(i).batTblNm_A.setErrorInfo(1, "ZZZM9012E", new String[] {batTblRelTMsg.getReturnCode()});
                    cMsg.setMessageInfo("ZZZM9012E");
                    return;
                }
            }
        }
        cMsg.setMessageInfo("ZZZM9003I", new String[]{"Submit"});
        return;
    }
    
    private void do_ZZZL0060Scrn00_Delete(ZZZL0060CMsg cMsg) {
        
        if (!ZZZL0060CommonLogic.chkGlbCmpCd(cMsg)) {
            return;
        }
        
        boolean selectFlg = false;
        BAT_TBL_RELTMsg batTblRelTMsg = new BAT_TBL_RELTMsg();
        
        int j = 0;
        ZZZL0060CMsg sortMsg = new ZZZL0060CMsg();
        
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (cMsg.A.no(i).xxChkBox_A.getValue().equals("Y")) {
                selectFlg = true;
                
                if (ZZZL0060CommonLogic.isEmpty(cMsg.A.no(i).ezUpTime_A.getValue())){
                    continue;
                }
                
                batTblRelTMsg.clear();
                
                batTblRelTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                batTblRelTMsg.batProcJobId.setValue(cMsg.A.no(i).batProcJobId_A.getValue());
                batTblRelTMsg.batTblNm.setValue(cMsg.A.no(i).batTblNm_A.getValue());

                batTblRelTMsg = (BAT_TBL_RELTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(batTblRelTMsg);
                if (batTblRelTMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return;
                }
                EZDTBLAccessor.remove(batTblRelTMsg);
                String sReturnCode = batTblRelTMsg.getReturnCode();
                if (!sReturnCode.equals(RTNCD_NORMAL)){
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                    return;
                }
                
            } else {
                EZDMsg.copy(cMsg.A.no(i), null, sortMsg.A.no(j), null);
                j++;
            }
        }
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sortMsg.A.setValidCount(j);
        
        EZDMsg.copy(sortMsg.A, null, cMsg.A, null);
        
        if (selectFlg == false) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX"});
        } else {
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete"});
        }
    }
}
