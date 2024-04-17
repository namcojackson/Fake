/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFAL0120.NFAL0120CMsg;
//import business.servlet.NFAL0120.constant.NFAL0120Constant;

import business.blap.NFAL0120.NFAL0120CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/01/17   Fujitsu         S.Fujita        Update          QC#16814
 *</pre>
 */
public class NFAL0120Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        NFAL0120CMsg bizMsg = new NFAL0120CMsg();
        bizMsg.setBusinessID("NFAL0120");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2016/01/17 S.Fujita [QC#16814,ADD]
        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg  = (NFAL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END   2016/01/17 S.Fujita [QC#16814,ADD]
    }
}
