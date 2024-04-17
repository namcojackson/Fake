/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/01/14   Fujitsu         T.Tanaka        Create          N/A
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9452
 *</pre>
 */
public class NFCL3000Scrn00_DeleteLine_D extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        if(scrnMsg.D.getValidCount() < 1) {
            return;
        }

        int chkCount = 0;
        for( int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(scrnMsg.D.no(i).xxChkBox_D1.getValue())) {
                chkCount++;
            }
        }
        if(chkCount < 1) {
            scrnMsg.setMessageInfo("NFCM0058E", null);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/06/03 S.Fujita [QC#9452,DEL]
//        NFCL3000CommonLogic.addCheckItem_TAB(scrnMsg, false);
//        scrnMsg.putErrorScreen();
        // END   2016/06/03 S.Fujita [QC#9452,DEL]

        if(scrnMsg.D.getValidCount() < 1) {
            this.setButtonProperties("DeleteLine_D", "DeleteLine_D", "Delete Line" , 0, null);
        }

    }
}
