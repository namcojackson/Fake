/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2320.NWAL2320CMsg;
import business.servlet.NWAL2320.constant.NWAL2320Constant;
//import business.servlet.NWAL2320.constant.NWAL2320Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL2320Scrn00_OnChange_RowsPerPage extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;

        if (scrnMsg.xxPageShowOfNum_CM.getValueInt() == 0) {
            return null;
        }

        NWAL2320CMsg bizMsg = new NWAL2320CMsg();
        bizMsg.setBusinessID(NWAL2320Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;
        NWAL2320CMsg bizMsg  = (NWAL2320CMsg) cMsg;

        if (scrnMsg.xxPageShowOfNum_CM.getValueInt() == 0) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
