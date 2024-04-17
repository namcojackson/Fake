/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0420;


import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0420.common.NSBL0420CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0420BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0420CMsg cMsg = (NSBL0420CMsg) arg0;
        NSBL0420SMsg sMsg = (NSBL0420SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0420Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0420Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0420Scrn00_CMN_Submit(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
//        if (!NSBL0420CommonLogic.checkQty(cMsg)){
//            return;
//        }
        if (!NSBL0420CommonLogic.checkCall(cMsg, sMsg)) {
            return;
        } else {
            for (int h = 0; h < cMsg.A.getValidCount(); h++) {
                setValue(cMsg.A.no(h).xxRowNum_A, BigDecimal.valueOf(h + 1));
            }
            NSBL0420CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
            NSBL0420CommonLogic.updateDetailItem(cMsg, sMsg);
        }

    }

}
