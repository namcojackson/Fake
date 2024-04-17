/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3020.NFCL3020CMsg;
import business.servlet.NFCL3020.common.NFCL3020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 2018/04/03   Fujitsu         H.Ikeda         UpDate          QC#21737-1
 *</pre>
 */
public class NFCL3020Scrn00_Click_Del extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        // START 2018/04/03 H.Ikeda [QC#21737,MOD]
        int chkCount = 0;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).funcRcptAmt_B);
            if(ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_B.getValue())) {
                chkCount++;
            }
        }
        if(chkCount < 1) {
            scrnMsg.setMessageInfo("NFCM0058E", null);
            throw new EZDFieldErrorException();
        }
        // END  2018/04/03 H.Ikeda [QC#21737,MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        NFCL3020CMsg bizMsg = new NFCL3020CMsg();
        bizMsg.setBusinessID("NFCL3020");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        NFCL3020CMsg bizMsg  = (NFCL3020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3020CommonLogic.initialize(this, scrnMsg, getUserProfileService());
    }
}
