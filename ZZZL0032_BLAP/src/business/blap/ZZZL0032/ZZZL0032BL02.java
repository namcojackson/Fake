package business.blap.ZZZL0032;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZZL0032BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZZL0032_INIT".equals(screenAplID)) {
//                searchEventIdList((ZZZL0032CMsg) cMsg);
            } else if ("ZZZL0032Scrn00_Search".equals(screenAplID)) {
                searchEventIdList((ZZZL0032CMsg) cMsg, (ZZZL0032SMsg) sMsg);
            } else if ("ZZZL0032Scrn00_SelectAll".equals(screenAplID)) {
                clickCheckBox((ZZZL0032CMsg) cMsg, (ZZZL0032SMsg) sMsg);
            } else if ("ZZZL0032Scrn00_CMN_Clear".equals(screenAplID)) {
                clear((ZZZL0032CMsg) cMsg, (ZZZL0032SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void searchEventIdList(ZZZL0032CMsg cMsg, ZZZL0032SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        
        int maxRow = cMsg.A.length();
        maxRow = 150;
        int hitCount = ZZZL0032Query.getInstance().getEventIdList(cMsg);
        if (hitCount >= maxRow) {
            cMsg.setMessageInfo("ZZZM9002W", new String[] {String.valueOf(cMsg.A.length())});
        }
        cMsg.A.setValidCount(hitCount);
        
        for (int i=0;  i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxChkBox_01.setValue("Y");
            cMsg.A.no(i).xxChkBox_02.setValue("Y");
        }
        EZDMsg.copy(cMsg, null, sMsg, null);
        return;
    }
    
    private void clear(ZZZL0032CMsg cMsg, ZZZL0032SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        return;
    }
    
    private void clickCheckBox (ZZZL0032CMsg cMsg, ZZZL0032SMsg sMsg) {
                
        for (int i=0;  i < cMsg.A.getValidCount(); i++) {
            String bizId = cMsg.A.no(i).bizId_A.getValue();
            if (sMsg.A.no(i).xxChkBox_01.getValue().equals("") && cMsg.A.no(i).xxChkBox_01.getValue().equals("Y")) {
                for (int k=0;  k < sMsg.A.getValidCount(); k++) {
                    if (bizId.equals(sMsg.A.no(k).bizId_A.getValue())) {
                        sMsg.A.no(k).xxChkBox_01.setValue("Y");
                        sMsg.A.no(k).xxChkBox_02.setValue("Y");
                    }
                }
                break;
            } else if (sMsg.A.no(i).xxChkBox_01.getValue().equals("Y") && cMsg.A.no(i).xxChkBox_01.getValue().equals("")) {
                for (int k=0;  k < cMsg.A.getValidCount(); k++) {
                    if (bizId.equals(sMsg.A.no(k).bizId_A.getValue())) {
                        sMsg.A.no(k).xxChkBox_01.setValue("");
                        sMsg.A.no(k).xxChkBox_02.setValue("");
                    }
                }
                break;
            }
        }
        EZDMsg.copy(sMsg, null, cMsg, null);
        return;
    }
}
