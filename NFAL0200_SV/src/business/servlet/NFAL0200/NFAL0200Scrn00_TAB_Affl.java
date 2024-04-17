/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0200.NFAL0200CMsg;
import business.blap.NFAL0200.constant.NFAL0200Constant;
import business.servlet.NFAL0200.common.NFAL0200CommonLogic;
//import business.servlet.NFAL0200.constant.NFAL0200Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFAL0200Scrn00_TAB_Affl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        if (NFAL0200CommonLogic.isNeedSearch(scrnMsg, NFAL0200Constant.AFFL_TAB)) {
            NFAL0200CMsg bizMsg = new NFAL0200CMsg();
            bizMsg.setBusinessID("NFAL0200");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {
            return null;
        }

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        
        if (NFAL0200CommonLogic.isNeedSearch(scrnMsg, NFAL0200Constant.AFFL_TAB)) {
            NFAL0200CMsg bizMsg  = (NFAL0200CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NFAL0200CommonLogic.submitBtnControl(this, false);
    }
}
