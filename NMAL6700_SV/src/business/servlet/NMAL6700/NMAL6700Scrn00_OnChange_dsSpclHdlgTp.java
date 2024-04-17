/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6700.NMAL6700CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/12   Fujitsu         C.Yokoi         Create          CSA-QC#12073
 *</pre>
 */
public class NMAL6700Scrn00_OnChange_dsSpclHdlgTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        scrnMsg.xxRowNum.setValue(getButtonSelectNumber());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID("NMAL6700");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg  = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
