/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8810.NYEL8810CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8810Scrn00_SelectAllRasg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/10   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_SelectAllRasg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}