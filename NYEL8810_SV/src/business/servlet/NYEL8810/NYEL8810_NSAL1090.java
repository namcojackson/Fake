/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8810_NSAL1090 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        //NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        //bizMsg.setBusinessID("NYEL8810");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        //NYEL8810CMsg bizMsg  = (NYEL8810CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }

}